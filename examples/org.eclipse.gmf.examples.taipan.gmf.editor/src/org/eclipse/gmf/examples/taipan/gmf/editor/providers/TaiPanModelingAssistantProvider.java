/*
 * Copyright (c) 2006, 2007 Borland Software Corporation
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *    Dmitry Stadnik (Borland) - initial API and implementation
 */
package org.eclipse.gmf.examples.taipan.gmf.editor.providers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.gmf.examples.taipan.gmf.editor.edit.parts.AquatoryEditPart;
import org.eclipse.gmf.examples.taipan.gmf.editor.edit.parts.PortEditPart;
import org.eclipse.gmf.examples.taipan.gmf.editor.edit.parts.ShipEditPart;
import org.eclipse.gmf.examples.taipan.gmf.editor.edit.parts.ShipLargeCargoEditPart;
import org.eclipse.gmf.examples.taipan.gmf.editor.edit.parts.WarshipEditPart;
import org.eclipse.gmf.examples.taipan.gmf.editor.edit.parts.WarshipLargeCargoEditPart;
import org.eclipse.gmf.examples.taipan.gmf.editor.part.Messages;
import org.eclipse.gmf.examples.taipan.gmf.editor.part.TaiPanDiagramEditorPlugin;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.emf.type.core.ElementTypeRegistry;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.emf.ui.services.modelingassistant.ModelingAssistantProvider;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.dialogs.ElementListSelectionDialog;

/**
 * @generated
 */
public class TaiPanModelingAssistantProvider extends ModelingAssistantProvider {

	/**
	 * @generated
	 */
	public List getTypesForPopupBar(IAdaptable host) {
		IGraphicalEditPart editPart = (IGraphicalEditPart) host.getAdapter(IGraphicalEditPart.class);
		if (editPart instanceof ShipEditPart) {
			ArrayList types = new ArrayList(1);
			types.add(TaiPanElementTypes.SmallItems_3001);
			return types;
		}
		if (editPart instanceof WarshipEditPart) {
			ArrayList types = new ArrayList(1);
			types.add(TaiPanElementTypes.SmallItems_3001);
			return types;
		}
		if (editPart instanceof ShipLargeCargoEditPart) {
			ArrayList types = new ArrayList(2);
			types.add(TaiPanElementTypes.LargeItem_3002);
			types.add(TaiPanElementTypes.EmptyBox_3003);
			return types;
		}
		if (editPart instanceof WarshipLargeCargoEditPart) {
			ArrayList types = new ArrayList(2);
			types.add(TaiPanElementTypes.LargeItem_3002);
			types.add(TaiPanElementTypes.EmptyBox_3003);
			return types;
		}
		if (editPart instanceof AquatoryEditPart) {
			ArrayList types = new ArrayList(3);
			types.add(TaiPanElementTypes.Port_2001);
			types.add(TaiPanElementTypes.Ship_2002);
			types.add(TaiPanElementTypes.Warship_2003);
			return types;
		}
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public List getRelTypesOnSource(IAdaptable source) {
		IGraphicalEditPart sourceEditPart = (IGraphicalEditPart) source.getAdapter(IGraphicalEditPart.class);
		if (sourceEditPart instanceof PortEditPart) {
			return ((PortEditPart) sourceEditPart).getMARelTypesOnSource();
		}
		if (sourceEditPart instanceof ShipEditPart) {
			return ((ShipEditPart) sourceEditPart).getMARelTypesOnSource();
		}
		if (sourceEditPart instanceof WarshipEditPart) {
			return ((WarshipEditPart) sourceEditPart).getMARelTypesOnSource();
		}
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public List getRelTypesOnTarget(IAdaptable target) {
		IGraphicalEditPart targetEditPart = (IGraphicalEditPart) target.getAdapter(IGraphicalEditPart.class);
		if (targetEditPart instanceof PortEditPart) {
			return ((PortEditPart) targetEditPart).getMARelTypesOnTarget();
		}
		if (targetEditPart instanceof ShipEditPart) {
			return ((ShipEditPart) targetEditPart).getMARelTypesOnTarget();
		}
		if (targetEditPart instanceof WarshipEditPart) {
			return ((WarshipEditPart) targetEditPart).getMARelTypesOnTarget();
		}
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public List getRelTypesOnSourceAndTarget(IAdaptable source, IAdaptable target) {
		IGraphicalEditPart sourceEditPart = (IGraphicalEditPart) source.getAdapter(IGraphicalEditPart.class);
		IGraphicalEditPart targetEditPart = (IGraphicalEditPart) target.getAdapter(IGraphicalEditPart.class);
		if (sourceEditPart instanceof PortEditPart) {
			return ((PortEditPart) sourceEditPart).getMARelTypesOnSourceAndTarget(targetEditPart);
		}
		if (sourceEditPart instanceof ShipEditPart) {
			return ((ShipEditPart) sourceEditPart).getMARelTypesOnSourceAndTarget(targetEditPart);
		}
		if (sourceEditPart instanceof WarshipEditPart) {
			return ((WarshipEditPart) sourceEditPart).getMARelTypesOnSourceAndTarget(targetEditPart);
		}
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public List getTypesForSource(IAdaptable target, IElementType relationshipType) {
		IGraphicalEditPart targetEditPart = (IGraphicalEditPart) target.getAdapter(IGraphicalEditPart.class);
		if (targetEditPart instanceof PortEditPart) {
			return ((PortEditPart) targetEditPart).getMATypesForSource(relationshipType);
		}
		if (targetEditPart instanceof ShipEditPart) {
			return ((ShipEditPart) targetEditPart).getMATypesForSource(relationshipType);
		}
		if (targetEditPart instanceof WarshipEditPart) {
			return ((WarshipEditPart) targetEditPart).getMATypesForSource(relationshipType);
		}
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public List getTypesForTarget(IAdaptable source, IElementType relationshipType) {
		IGraphicalEditPart sourceEditPart = (IGraphicalEditPart) source.getAdapter(IGraphicalEditPart.class);
		if (sourceEditPart instanceof PortEditPart) {
			return ((PortEditPart) sourceEditPart).getMATypesForTarget(relationshipType);
		}
		if (sourceEditPart instanceof ShipEditPart) {
			return ((ShipEditPart) sourceEditPart).getMATypesForTarget(relationshipType);
		}
		if (sourceEditPart instanceof WarshipEditPart) {
			return ((WarshipEditPart) sourceEditPart).getMATypesForTarget(relationshipType);
		}
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public EObject selectExistingElementForSource(IAdaptable target, IElementType relationshipType) {
		return selectExistingElement(target, getTypesForSource(target, relationshipType));
	}

	/**
	 * @generated
	 */
	public EObject selectExistingElementForTarget(IAdaptable source, IElementType relationshipType) {
		return selectExistingElement(source, getTypesForTarget(source, relationshipType));
	}

	/**
	 * @generated
	 */
	protected EObject selectExistingElement(IAdaptable host, Collection types) {
		if (types.isEmpty()) {
			return null;
		}
		IGraphicalEditPart editPart = (IGraphicalEditPart) host.getAdapter(IGraphicalEditPart.class);
		if (editPart == null) {
			return null;
		}
		Diagram diagram = (Diagram) editPart.getRoot().getContents().getModel();
		Collection elements = new HashSet();
		for (Iterator it = diagram.getElement().eAllContents(); it.hasNext();) {
			EObject element = (EObject) it.next();
			if (isApplicableElement(element, types)) {
				elements.add(element);
			}
		}
		if (elements.isEmpty()) {
			return null;
		}
		return selectElement((EObject[]) elements.toArray(new EObject[elements.size()]));
	}

	/**
	 * @generated
	 */
	protected boolean isApplicableElement(EObject element, Collection types) {
		IElementType type = ElementTypeRegistry.getInstance().getElementType(element);
		return types.contains(type);
	}

	/**
	 * @generated
	 */
	protected EObject selectElement(EObject[] elements) {
		Shell shell = Display.getCurrent().getActiveShell();
		ILabelProvider labelProvider = new AdapterFactoryLabelProvider(TaiPanDiagramEditorPlugin.getInstance().getItemProvidersAdapterFactory());
		ElementListSelectionDialog dialog = new ElementListSelectionDialog(shell, labelProvider);
		dialog.setMessage(Messages.TaiPanModelingAssistantProviderMessage);
		dialog.setTitle(Messages.TaiPanModelingAssistantProviderTitle);
		dialog.setMultipleSelection(false);
		dialog.setElements(elements);
		EObject selected = null;
		if (dialog.open() == Window.OK) {
			selected = (EObject) dialog.getFirstResult();
		}
		return selected;
	}
}
