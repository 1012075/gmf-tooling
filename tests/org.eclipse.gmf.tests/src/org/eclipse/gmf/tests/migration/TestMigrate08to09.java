/*
 * Copyright (c) 2009 Borland Software Corporation
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Artem Tikhomirov (Borland) - initial API and implementation
 */
package org.eclipse.gmf.tests.migration;

import junit.framework.TestCase;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.gmf.codegen.gmfgen.CreateShortcutAction;
import org.eclipse.gmf.codegen.gmfgen.CustomParser;
import org.eclipse.gmf.codegen.gmfgen.ExternalParser;
import org.eclipse.gmf.codegen.gmfgen.GMFGenPackage;
import org.eclipse.gmf.codegen.gmfgen.GenContextMenu;
import org.eclipse.gmf.codegen.gmfgen.GenContributionItem;
import org.eclipse.gmf.codegen.gmfgen.GenEditorGenerator;
import org.eclipse.gmf.codegen.gmfgen.GenParserImplementation;
import org.eclipse.gmf.codegen.gmfgen.GenParsers;
import org.eclipse.gmf.codegen.gmfgen.LabelTextAccessMethod;
import org.eclipse.gmf.codegen.gmfgen.LoadResourceAction;
import org.eclipse.gmf.codegen.gmfgen.PredefinedParser;
import org.eclipse.gmf.tests.Plugin;

/**
 * No tests for added FeatureLabelModelFacet#editableMetaFeatures, as empty value is ok (see AbstractParser).
 * @author artem
 */
public class TestMigrate08to09 extends TestCase {
	private GenEditorGenerator editorGen;

	@Override
	protected void setUp() throws Exception {
		EObject root = new ResourceSetImpl().getEObject(Plugin.createURI("/models/migration/08to09.gmfgen#/"), true);
		assertNotNull(root);
		assertEquals(GMFGenPackage.eNS_URI, root.eClass().getEPackage().getNsURI());
		assertTrue(root instanceof GenEditorGenerator /*root.eClass() == genEditorGenEClass */); // XXX how about next year's changes?
		editorGen = (GenEditorGenerator) root;
	}

	/**
	 * Concept of GenParser and ParserImplementation introduced to replace edit/view methods of FeatureLabelModelFacet 
	 */
	public void testGenParsers() {
		GenParsers genParsers = editorGen.getLabelParsers();
		assertNotNull(genParsers);
		assertEquals(editorGen.getDiagram().getParsersPackageName(), genParsers.getImplPackageName()); // ok to remove when parserPackageName is gone
		assertEquals(editorGen.getDiagram().getParserProviderPriority(), genParsers.getProviderPriority());
		assertEquals(editorGen.getDiagram().getParserProviderQualifiedClassName(), genParsers.getQualifiedClassName());
		assertFalse(genParsers.getImplementations().isEmpty());
		boolean customParserFound = false, externalParserFound = false;
		PredefinedParser predefinedParser = null;
		for (GenParserImplementation gpi : genParsers.getImplementations()) {
			if (gpi instanceof CustomParser) {
				customParserFound = true;
			} else if (gpi instanceof ExternalParser) {
				externalParserFound = true;
			} else if (gpi instanceof PredefinedParser) {
				predefinedParser = (PredefinedParser) gpi;
			}
		}
		assertFalse("Old models had no means to define CustomParser", customParserFound);
		assertTrue("Missing ExternalParser use ", externalParserFound);
		assertTrue("Missing PredefinedParser use ", predefinedParser != null);
		assertFalse(predefinedParser.getUses().isEmpty());
		assertEquals(LabelTextAccessMethod.MESSAGE_FORMAT, predefinedParser.getEditMethod());
		assertEquals(LabelTextAccessMethod.PRINTF, predefinedParser.getViewMethod());
	}

	/**
	 * LoadResourceAction, CreateShortcutAction are now distinct classes, explicitly inserted into diagram's context menu
	 */
	public void testActions() {
		assertFalse(editorGen.getContextMenus().isEmpty());
		GenContextMenu diagramMenu = null;
		for (GenContextMenu cm : editorGen.getContextMenus()) {
			if (cm.getContext().size() == 1 && cm.getContext().get(0) == editorGen.getDiagram()) {
				diagramMenu = cm;
			}
		}
		assertNotNull(diagramMenu);
		LoadResourceAction loadResAction = null;
		CreateShortcutAction createShortcutAction = null;
		for (GenContributionItem ci : diagramMenu.getItems()) {
			if (ci instanceof CreateShortcutAction) {
				createShortcutAction = (CreateShortcutAction) ci;
			} else if (ci instanceof LoadResourceAction) {
				loadResAction = (LoadResourceAction) ci;
			}
		}
		assertNotNull(loadResAction);
		assertNotNull(createShortcutAction);
		assertNotNull(loadResAction.getQualifiedClassName());
		assertNotNull(createShortcutAction.getQualifiedClassName());
	}
}
