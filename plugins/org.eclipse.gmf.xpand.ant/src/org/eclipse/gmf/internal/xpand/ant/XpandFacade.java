/*
 * Copyright (c) 2007, 2008 Borland Software Corporation
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.gmf.internal.xpand.ant;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.impl.EPackageRegistryImpl;
import org.eclipse.gmf.internal.xpand.Activator;
import org.eclipse.gmf.internal.xpand.BufferOutput;
import org.eclipse.gmf.internal.xpand.BuiltinMetaModel;
import org.eclipse.gmf.internal.xpand.ResourceManager;
import org.eclipse.gmf.internal.xpand.StreamsHolder;
import org.eclipse.gmf.internal.xpand.model.EvaluationException;
import org.eclipse.gmf.internal.xpand.model.ExecutionContext;
import org.eclipse.gmf.internal.xpand.model.Variable;
import org.eclipse.gmf.internal.xpand.model.XpandDefinition;
import org.eclipse.gmf.internal.xpand.util.BundleResourceManager;
import org.eclipse.gmf.internal.xpand.util.ClassLoadContext;
import org.eclipse.gmf.internal.xpand.util.ContextFactory;
import org.eclipse.gmf.internal.xpand.xtend.ast.QvtResource;
import org.eclipse.m2m.internal.qvt.oml.expressions.Module;
import org.eclipse.m2m.qvt.oml.runtime.util.OCLEnvironmentWithQVTAccessFactory;
import org.eclipse.ocl.ParserException;
import org.eclipse.ocl.ecore.EcoreEvaluationEnvironment;
import org.eclipse.ocl.ecore.EcoreFactory;
import org.eclipse.ocl.ecore.OCL;
import org.eclipse.ocl.ecore.OCLExpression;
import org.eclipse.ocl.ecore.OCL.Helper;
import org.eclipse.ocl.ecore.OCL.Query;
import org.osgi.framework.Bundle;

/**
 * Redistributable API for Xpand evaluation
 * @author artem
 */
public final class XpandFacade {
	private final LinkedList<Variable> myGlobals = new LinkedList<Variable>();
	private final LinkedList<URL> myLocations = new LinkedList<URL>();
	private final LinkedList<String> myImportedModels = new LinkedList<String>();
	private final LinkedList<String> myExtensionFiles = new LinkedList<String>();
	private boolean myEnforceReadOnlyNamedStreamsAfterAccess = false;
	
	private ExecutionContext myXpandCtx;
	private BufferOutput myBufferOut;
	private HashMap<Object, StreamsHolder> myStreamsHolders;

	private final StringBuilder myOut = new StringBuilder();

	public XpandFacade() {
	}

	/**
	 * Sort of copy constructor, create a new facade pre-initialized with values 
	 * of existing one.
	 * @param chain facade to copy settings (globals, locations, metamodels, extensions, loaders) from, can't be <code>null</code>.
	 */
	public XpandFacade(XpandFacade chain) {
		assert chain != null;
		myGlobals.addAll(chain.myGlobals);
		myLocations.addAll(chain.myLocations);
		myImportedModels.addAll(chain.myImportedModels);
		myExtensionFiles.addAll(chain.myExtensionFiles);
		//
		// not necessary, but doesn't seem to hurt
		myXpandCtx = chain.myXpandCtx; // new state is formed with cloning
	}

	/**
	 * Named streams (those created by <<FILE file slotName>> syntax) may be put into a strict mode that prevents write operations
	 * after the contents of the stream have been accessed. By default, named streams are not in strict mode.
	 * @param value
	 */
	public void setEnforceReadOnlyNamedStreamsAfterAccess(boolean value) {
		myEnforceReadOnlyNamedStreamsAfterAccess = value;
	}

	public void addGlobal(String name, Object value) {
		assert name != null;
		for (Iterator<Variable> it = myGlobals.listIterator(); it.hasNext();) {
			if (it.next().getName().equals(name)) {
				it.remove();
			}
		}
		if (name == null || value == null) {
			return;
		}
		myGlobals.addFirst(new Variable(name, null, value));
		clearAllContexts();
	}

	public void addLocation(String url) throws MalformedURLException {
		addLocation(new URL(url));
	}

	public void addLocation(URL url) {
		assert url != null;
		myLocations.addLast(url);
		clearAllContexts();
	}

	/**
	 * Registers a class loader to load Java classes accessed from templates and/or expressions. 
	 * @param loader ClassLoader to load classes though
	 * @deprecated QVT-based dialect of Xpand does not use classload contexts.
	 */
	@Deprecated
	public void addClassLoadContext(ClassLoader loader) {
		//do nothing
	}

	/**
	 * Register a bundle to load Java classes from (i.e. JAVA functions in Xtend)
	 * @param bundle - generally obtained from {@link org.eclipse.core.runtime.Platform#getBundle(String)}, should not be null.
	 * @deprecated QVT-based dialect of Xpand does not use classload contexts.
	 */
	@Deprecated
	public void addClassLoadContext(Bundle bundle) {
		//do nothing
	}
	
	public void addMetamodel(String metamodel) {
		if (myImportedModels.contains(metamodel)) {
			return;
		}
		myImportedModels.add(metamodel);
	}

	/**
	 * @param extensionFile double-colon separated qualified name of qvto file
	 */
	public void addExtensionFile(String extensionFile) {
		if (myExtensionFiles.contains(extensionFile)) {
			return;
		}
		myExtensionFiles.add(extensionFile);
	}

	public <T> T evaluate(String expression, Object target) {
		// XXX perhaps, need to check for target == null and do not set 'this' then
		return evaluate(expression, Collections.singletonMap("self", target));
	}
	
	/**
	 * @param expression xtend expression to evaluate
	 * @param context should not be <code>null</code>
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T> T evaluate(String expression, Map<String,?> context) {
		assert context != null; // nevertheless, prevent NPE.
		ResourceManager rm;
		if (myLocations.isEmpty()) {
			try {
				// use current default path as root
				// use canonicalFile to get rid of dot after it get resolved to
				// current dir
				rm = new BundleResourceManager(new File(".").getCanonicalFile().toURI().toURL());
			} catch (IOException ex) {
				// should not happen
				rm = null;
			}
		} else {
			rm = new BundleResourceManager(myLocations.toArray(new URL[myLocations.size()]));
		}

		Set<Module> importedModules = getImportedModules(rm);
		OCLEnvironmentWithQVTAccessFactory factory = new OCLEnvironmentWithQVTAccessFactory(importedModules, getAllVisibleModels());
		OCL ocl = OCL.newInstance(factory);
		Object thisValue = null;
		if (context != null) {
			for (Map.Entry<String, ?> nextEntry : context.entrySet()) {
				String varName = nextEntry.getKey();
				Object varValue = nextEntry.getValue();
				if (IMPLICIT_VAR_NAME.equals(varName) || IMPLICIT_VAR_NAME_BACKWARD_COMPATIBILITY.equals(varName)) {
					assert thisValue == null;	//prevent simultaneous this and self
					thisValue = varValue;
					continue;
				}
				EClassifier varType = BuiltinMetaModel.getType(getXpandContext(), varValue);
    			org.eclipse.ocl.ecore.Variable oclVar = EcoreFactory.eINSTANCE.createVariable();
    			oclVar.setName(varName);
    			oclVar.setType(varType);
				ocl.getEnvironment().addElement(varName, oclVar, true);
			}
		}
		Helper oclHelper = ocl.createOCLHelper();
		if (thisValue != null) {
			oclHelper.setContext(BuiltinMetaModel.getType(getXpandContext(), thisValue));
		} else {
			oclHelper.setContext(ocl.getEnvironment().getOCLStandardLibrary().getOclVoid());
		}

		OCLExpression exp;
		try {
			exp = oclHelper.createQuery(expression);
		} catch (ParserException e) {
//			e.printStackTrace();
			throw new EvaluationException(e, null);
		}
		Query query = ocl.createQuery(exp);
		EcoreEvaluationEnvironment ee = (EcoreEvaluationEnvironment) query.getEvaluationEnvironment();
		if (context != null) {
	    	for (Map.Entry<String, ?> nextEntry : context.entrySet()) {
				String varName = nextEntry.getKey();
				Object varValue = nextEntry.getValue();
				if (!IMPLICIT_VAR_NAME.equals(varName) && !IMPLICIT_VAR_NAME_BACKWARD_COMPATIBILITY.equals(varName)) {
	    			ee.add(varName, varValue);
	    		}
	    	}
    	}
    	Object result;
    	if (thisValue != null) {
    		result = query.evaluate(thisValue);
    	} else {
    		result = query.evaluate();
    	}
    	if (result == ocl.getEnvironment().getOCLStandardLibrary().getOclInvalid()) {
    		return null;	//XXX: or throw an exception?
    	}
    	return (T) result;
	}

	private Set<Module> getImportedModules(ResourceManager rm) {
		Set<Module> result = new HashSet<Module>();
		for (String extensionFile : myExtensionFiles) {
			QvtResource qvtResource = rm.loadQvtResource(extensionFile);
			result.addAll(qvtResource.getModules());
		}
		return result;
	}

	private EPackage.Registry getAllVisibleModels() {
		assert myImportedModels != null;
		// TODO respect meta-models imported not only with nsURI
		EPackage.Registry result = new EPackageRegistryImpl();
		for (String namespace : myImportedModels) {
			EPackage pkg = Activator.findMetaModel(namespace);
			if (pkg != null) {
				result.put(namespace, pkg);
			}
		}
		if (result.isEmpty()) {
			// hack for tests
			result.put(EcorePackage.eNS_URI, EcorePackage.eINSTANCE);
		}
		return result;
	}

	public String xpand(String templateName, Object target, Object... arguments) {
		if (target == null) {
			return null;
		}
		clearOut();
		ExecutionContext ctx = getXpandContext();
		new org.eclipse.gmf.internal.xpand.XpandFacade(ctx).evaluate(templateName, target, arguments);
		return myOut.toString();
	}

	public Map<Object, String> xpand(String templateName, Collection<?> target, Object... arguments) {
		// though it's reasonable to keep original order of input elements,
		// is it worth declaring in API?
		LinkedHashMap<Object, String> inputToResult = new LinkedHashMap<Object, String>();
        boolean invokeForCollection = findDefinition(templateName, target, arguments) != null;
        if (invokeForCollection) {
			inputToResult.put(target, xpand(templateName, (Object)target, arguments));
	        return inputToResult;
        }
		myStreamsHolders = new HashMap<Object, StreamsHolder>();
        for (Object nextInput: target) {
        	if (nextInput == null) {
        		continue;
        	}
        	String result = xpand(templateName, nextInput, arguments);
			inputToResult.put(nextInput, result);
			myStreamsHolders.put(nextInput, myBufferOut.getNamedStreams());
        }
        return inputToResult;
	}

	/**
	 * Returns names of named streams that were created during the most recent {@link #xpand(String, Object, Object...) operation and have non-empty contents.
	 * @return
	 */
	public Collection<String> getNamedStreams() {
		assert myStreamsHolders == null;	//if invoked for several elements separately, another version of this method should be used.
		if (myBufferOut == null) {
			return Collections.emptyList();
		}
		return myBufferOut.getNamedStreams().getSlotNames();
	}

	/**
	 * Returns contents of the named stream that was created during the most recent {@link #xpand(String, Object, Object...) operation.
	 * If the stream with the given name does not exist, the operation will throw an exception.
	 * @param streamName
	 * @return
	 */
	public String getNamedStreamContents(String streamName) {
		assert myStreamsHolders == null;	//if invoked for several elements separately, another version of this method should be used.
		if (myBufferOut == null) {
			throw new UnsupportedOperationException("Stream with the given name does not exist", null);
		}
		return myBufferOut.getNamedStreams().getStreamContents(streamName);
	}

	/**
	 * Returns names of non-empty named streams that were created during the most recent {@link #xpand(String, Collection, Object...)} operation for the given input.
	 * @return
	 */
	public Collection<String> getNamedStreams(Object input) {
		if (myStreamsHolders == null) {
			//assume this is the input that was used during the last invocation, but do not enforce this.
			return getNamedStreams();
		}
		StreamsHolder streamsHolder = myStreamsHolders.get(input);
		if (streamsHolder == null) {
			return Collections.emptyList();
		}
		return streamsHolder.getSlotNames();
	}

	/**
	 * Returns contents of the named stream that was created during the most recent {@link #xpand(String, Collection, Object...) operation.
	 * If the stream with the given name does not exist, the operation will throw an exception.
	 * @param streamName
	 * @return
	 */
	public String getNamedStreamContents(Object input, String streamName) {
		if (myStreamsHolders == null) {
			//assume this is the input that was used during the last invocation, but do not enforce this.
			return getNamedStreamContents(streamName);
		}
		StreamsHolder streamsHolder = myStreamsHolders.get(input);
		if (streamsHolder == null) {
			throw new UnsupportedOperationException("Stream with the given name does not exist", null);
		}
		return streamsHolder.getStreamContents(streamName);
	}

	private XpandDefinition findDefinition(String templateName, Object target, Object[] arguments) {
		EClassifier targetType = BuiltinMetaModel.getType(getXpandContext(), target);
		final EClassifier[] paramTypes = new EClassifier[arguments == null ? 0 : arguments.length];
		for (int i = 0; i < paramTypes.length; i++) {
		    paramTypes[i] = BuiltinMetaModel.getType(getXpandContext(), arguments[i]);
		}
		return getXpandContext().findDefinition(templateName, targetType, paramTypes);
	}

	private void clearAllContexts() {
		myXpandCtx = null;
	}
	
	private void clearOut() {
		myOut.setLength(200);
		myOut.trimToSize();
		myOut.setLength(0);
		//To clear streams, we have no other option but to reset the xpand context
		myXpandCtx = null;
		myBufferOut = null;
	}

	private ExecutionContext getXpandContext() {
		if (myXpandCtx == null) {
			BundleResourceManager rm = new BundleResourceManager(myLocations.toArray(new URL[myLocations.size()]));
			myBufferOut = new BufferOutput(myOut, myEnforceReadOnlyNamedStreamsAfterAccess);
			myXpandCtx = ContextFactory.createXpandContext(rm, myBufferOut, myGlobals, (ClassLoadContext) null);
		}
		return myXpandCtx;
	}

	private static final String IMPLICIT_VAR_NAME = "self";	//$NON-NLS-1$
	private static final String IMPLICIT_VAR_NAME_BACKWARD_COMPATIBILITY = "this";	//$NON-NLS-1$
}
