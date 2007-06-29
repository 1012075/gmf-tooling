/*
 *  Copyright (c) 2006, 2007 Borland Software Corporation
 *  
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v1.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 *  
 *  Contributors:
 *     Dmitry Stadnik (Borland) - initial API and implementation
 */
package org.eclipse.gmf.examples.design2d.part;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.gef.Tool;
import org.eclipse.gef.palette.PaletteContainer;
import org.eclipse.gef.palette.PaletteDrawer;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.palette.ToolEntry;
import org.eclipse.gmf.examples.design2d.providers.DesignElementTypes;
import org.eclipse.gmf.runtime.diagram.ui.tools.UnspecifiedTypeConnectionTool;
import org.eclipse.gmf.runtime.diagram.ui.tools.UnspecifiedTypeCreationTool;

/**
 * @generated
 */
public class DesignPaletteFactory {

	/**
	 * @generated
	 */
	public void fillPalette(PaletteRoot paletteRoot) {
		paletteRoot.add(createShapesGroup());
	}

	/**
	 * Creates "Shapes" palette tool group
	 * @generated
	 */
	private PaletteContainer createShapesGroup() {
		PaletteDrawer paletteContainer = new PaletteDrawer(Messages.ShapesGroup_title);
		paletteContainer.add(createSolidRectangleCreationTool());
		paletteContainer.add(createSolidEllipseCreationTool());
		paletteContainer.add(createSolidLineCreationTool());
		paletteContainer.add(createCustomNodeTool());
		return paletteContainer;
	}

	/**
	 * @generated
	 */
	private ToolEntry createSolidRectangleCreationTool() {
		List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(2);
		types.add(DesignElementTypes.Node_2001);
		types.add(DesignElementTypes.Node_3001);
		NodeToolEntry entry = new NodeToolEntry(Messages.SolidRectangleCreationTool_title, null, types);
		entry.setSmallIcon(DesignElementTypes.getImageDescriptor(DesignElementTypes.Node_2001));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createSolidEllipseCreationTool() {
		List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(2);
		types.add(DesignElementTypes.Node_3002);
		types.add(DesignElementTypes.Node_2002);
		NodeToolEntry entry = new NodeToolEntry(Messages.SolidEllipseCreationTool_title, null, types);
		entry.setSmallIcon(DesignElementTypes.getImageDescriptor(DesignElementTypes.Node_3002));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createSolidLineCreationTool() {
		List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(1);
		types.add(DesignElementTypes.Link_4001);
		LinkToolEntry entry = new LinkToolEntry(Messages.SolidLineCreationTool_title, null, types);
		entry.setSmallIcon(DesignElementTypes.getImageDescriptor(DesignElementTypes.Link_4001));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createCustomNodeTool() {
		List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(1);
		types.add(DesignElementTypes.Node_2003);
		NodeToolEntry entry = new NodeToolEntry(Messages.CustomNodeTool_title, null, types);
		entry.setSmallIcon(DesignElementTypes.getImageDescriptor(DesignElementTypes.Node_2003));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private static class NodeToolEntry extends ToolEntry {

		/**
		 * @generated
		 */
		private final List elementTypes;

		/**
		 * @generated
		 */
		private NodeToolEntry(String title, String description, List elementTypes) {
			super(title, description, null, null);
			this.elementTypes = elementTypes;
		}

		/**
		 * @generated
		 */
		public Tool createTool() {
			Tool tool = new UnspecifiedTypeCreationTool(elementTypes);
			tool.setProperties(getToolProperties());
			return tool;
		}
	}

	/**
	 * @generated
	 */
	private static class LinkToolEntry extends ToolEntry {

		/**
		 * @generated
		 */
		private final List relationshipTypes;

		/**
		 * @generated
		 */
		private LinkToolEntry(String title, String description, List relationshipTypes) {
			super(title, description, null, null);
			this.relationshipTypes = relationshipTypes;
		}

		/**
		 * @generated
		 */
		public Tool createTool() {
			Tool tool = new UnspecifiedTypeConnectionTool(relationshipTypes);
			tool.setProperties(getToolProperties());
			return tool;
		}
	}
}
