/*
 * Copyright (c) 2008 Borland Software Corporation
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Artem Tikhomirov (Borland) - initial API and implementation
 */
package org.eclipse.gmf.internal.xpand.ant;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;

/**
 * <p>... xmlns:xpt=<em>&quot;eclipse.org/gmf/2008/xpand&quot;</em>...
 * <p>
 * &lt;xpt:template name=&quot;a::b::Main&quot;/&gt;
 *
 * @author artem
 */
public class InvokeTemplateTask extends Task {

	private String myTemplateName;
	private String myInputURI;
	private String[] myTemplateRoots;
	private String myOutFile;
	private XpandFacade myFacade;
	private Object myInputObject;
	private ResourceSetImpl myResourceSet;

	public void setName(String name) {
		myTemplateName = name;
	}

	public void setBareInput(String input) {
		myInputObject = input;
	}

	public void setInputURI(String uri) {
		myInputURI = uri;
	}

	public void setOutFile(String uri) {
		myOutFile = uri;
	}

	public void setTemplateRoot(String root) {
		ArrayList<String> roots = new ArrayList<String>();
		for (StringTokenizer st = new StringTokenizer(root, ";, "); st.hasMoreTokens(); ) {
			roots.add(st.nextToken().trim());
		}
		myTemplateRoots = roots.toArray(new String[roots.size()]);
	}

	@Override
	public void execute() throws BuildException {
		IProgressMonitor pm = new ProgressSupport(this);
		pm.beginTask(getTaskName(), 3);
		validate();
		pm.worked(1);
		//
		doExecute();
		//
		pm.done();
	}

	protected void doExecute() throws BuildException {
		XpandFacade xf = createExecFacade();
		String result = xf.xpand(myTemplateName, getTemplateTarget(), getTemplateArguments());
		if (myOutFile == null) {
			System.err.println(result);
		} else {
			try {
				File f = getProject().resolveFile(myOutFile);
				FileOutputStream os = new FileOutputStream(f);
				os.write(result.getBytes());
				os.close();
			} catch (IOException ex) {
				throw new BuildException("Can't write to " + myOutFile, ex, getLocation());
			}
		}
	}
	
	protected void validate() throws BuildException {
		if (myTemplateName == null) {
			throw new BuildException("Template name is missing", getLocation());
		}
		if (myInputURI == null && myInputObject == null) {
			throw new BuildException("Target object is missing", getLocation());
		}
		if (myFacade == null && (myTemplateRoots == null || myTemplateRoots.length == 0)) {
			throw new BuildException("No template root specified", getLocation());
		}
	}

	protected Object getTemplateTarget() {
		if (myInputURI != null) {
			return getResourceSet().getEObject(URI.createURI(myInputURI), true);
		}
		return myInputObject;
	}

	protected Object[] getTemplateArguments() {
		return null;
	}

	protected void setInputObject(Object input) {
		myInputObject = input;
	}

	protected void setFacade(XpandFacade xf) {
		myFacade = xf;
	}

	protected XpandFacade createExecFacade() throws BuildException {
		try {
			if (myTemplateRoots != null && myTemplateRoots.length > 0) {
				XpandFacade xf;
				if (myFacade != null) {
					xf = new XpandFacade(myFacade);
				} else {
					xf = new XpandFacade();
				}
				for (String r : myTemplateRoots) {
					xf.addLocation(r);
				}
				return xf;
			} else {
				return myFacade == null ? new XpandFacade() : myFacade;
			}
		} catch (MalformedURLException ex) {
			throw new BuildException(ex, getLocation());
		}
	}

	protected ResourceSet getResourceSet() {
		if (myResourceSet == null) {
			myResourceSet = new ResourceSetImpl();
			myResourceSet.getURIConverter().getURIMap().putAll(EcorePlugin.computePlatformURIMap());
		}
		return myResourceSet;
	}
}
