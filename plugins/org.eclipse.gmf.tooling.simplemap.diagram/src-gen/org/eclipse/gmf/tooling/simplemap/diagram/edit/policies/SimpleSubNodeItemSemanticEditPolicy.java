/**
 * Copyright (c) 2010-2012 ISBAN S.L
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 * 		Ruben De Dios (ISBAN S.L)
 * 		Andrez Alvarez Mattos (ISBAN S.L)
 */
package org.eclipse.gmf.tooling.simplemap.diagram.edit.policies;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.common.core.command.ICompositeCommand;
import org.eclipse.gmf.runtime.diagram.core.commands.DeleteCommand;
import org.eclipse.gmf.runtime.emf.commands.core.command.CompositeTransactionalCommand;
import org.eclipse.gmf.runtime.emf.type.core.commands.DestroyElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyElementRequest;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.gmf.tooling.simplemap.diagram.edit.commands.SimpleChildReferenceCreateCommand;
import org.eclipse.gmf.tooling.simplemap.diagram.edit.commands.SimpleCompartmentCreateCommand;
import org.eclipse.gmf.tooling.simplemap.diagram.edit.commands.SimpleLabelNodeCreateCommand;
import org.eclipse.gmf.tooling.simplemap.diagram.edit.commands.SimpleSubNodeCreateCommand;
import org.eclipse.gmf.tooling.simplemap.diagram.edit.parts.SimpleChildReferenceEditPart;
import org.eclipse.gmf.tooling.simplemap.diagram.edit.parts.SimpleCompartmentEditPart;
import org.eclipse.gmf.tooling.simplemap.diagram.edit.parts.SimpleLabelNodeEditPart;
import org.eclipse.gmf.tooling.simplemap.diagram.edit.parts.SimpleSubNodeEditPart;
import org.eclipse.gmf.tooling.simplemap.diagram.part.SimplemapVisualIDRegistry;
import org.eclipse.gmf.tooling.simplemap.diagram.providers.SimplemapElementTypes;

/**
 * @generated
 */
public class SimpleSubNodeItemSemanticEditPolicy extends SimplemapBaseItemSemanticEditPolicy {

	/**
	 * @generated
	 */
	public SimpleSubNodeItemSemanticEditPolicy() {
		super(SimplemapElementTypes.SimpleSubNode_2003);
	}

	/**
	 * @generated
	 */
	protected Command getCreateCommand(CreateElementRequest req) {
		if (SimplemapElementTypes.SimpleLabelNode_2001 == req.getElementType()) {
			return getGEFWrapper(new SimpleLabelNodeCreateCommand(req));
		}
		if (SimplemapElementTypes.SimpleCompartment_2002 == req.getElementType()) {
			return getGEFWrapper(new SimpleCompartmentCreateCommand(req));
		}
		if (SimplemapElementTypes.SimpleChildReference_2004 == req.getElementType()) {
			return getGEFWrapper(new SimpleChildReferenceCreateCommand(req));
		}
		if (SimplemapElementTypes.SimpleSubNode_2003 == req.getElementType()) {
			return getGEFWrapper(new SimpleSubNodeCreateCommand(req));
		}
		return super.getCreateCommand(req);
	}

	/**
	 * @generated
	 */
	protected Command getDestroyElementCommand(DestroyElementRequest req) {
		View view = (View) getHost().getModel();
		CompositeTransactionalCommand cmd = new CompositeTransactionalCommand(getEditingDomain(), null);
		cmd.setTransactionNestingEnabled(false);
		EAnnotation annotation = view.getEAnnotation("Shortcut"); //$NON-NLS-1$
		if (annotation == null) {
			// there are indirectly referenced children, need extra commands: false
			addDestroyChildNodesCommand(cmd);
			addDestroyShortcutsCommand(cmd, view);
			// delete host element
			cmd.add(new DestroyElementCommand(req));
		} else {
			cmd.add(new DeleteCommand(getEditingDomain(), view));
		}
		return getGEFWrapper(cmd.reduce());
	}

	/**
	 * @generated
	 */
	private void addDestroyChildNodesCommand(ICompositeCommand cmd) {
		View view = (View) getHost().getModel();

		for (int i = view.getChildren().size() - 1; i >= 0; i--) {
			Node node = (Node) view.getChildren().get(i);

			switch (SimplemapVisualIDRegistry.getVisualID(node)) {
			case SimpleLabelNodeEditPart.VISUAL_ID:
				cmd.add(new DestroyElementCommand(new DestroyElementRequest(getEditingDomain(), node.getElement(), false))); // directlyOwned: true
				// don't need explicit deletion of node as parent's view deletion would clean child views as well 
				// cmd.add(new org.eclipse.gmf.runtime.diagram.core.commands.DeleteCommand(getEditingDomain(), node));
				break;
			case SimpleCompartmentEditPart.VISUAL_ID:
				cmd.add(new DestroyElementCommand(new DestroyElementRequest(getEditingDomain(), node.getElement(), false))); // directlyOwned: true
				// don't need explicit deletion of node as parent's view deletion would clean child views as well 
				// cmd.add(new org.eclipse.gmf.runtime.diagram.core.commands.DeleteCommand(getEditingDomain(), node));
				break;
			case SimpleChildReferenceEditPart.VISUAL_ID:
				cmd.add(new DestroyElementCommand(new DestroyElementRequest(getEditingDomain(), node.getElement(), false))); // directlyOwned: true
				// don't need explicit deletion of node as parent's view deletion would clean child views as well 
				// cmd.add(new org.eclipse.gmf.runtime.diagram.core.commands.DeleteCommand(getEditingDomain(), node));
				break;
			case SimpleSubNodeEditPart.VISUAL_ID:
				cmd.add(new DestroyElementCommand(new DestroyElementRequest(getEditingDomain(), node.getElement(), false))); // directlyOwned: true
				// don't need explicit deletion of node as parent's view deletion would clean child views as well 
				// cmd.add(new org.eclipse.gmf.runtime.diagram.core.commands.DeleteCommand(getEditingDomain(), node));
				break;
			}
		}
	}

}
