package org.eclipse.gmf.examples.mindmap.lite.rcp.edit.commands;

import org.eclipse.emf.common.command.AbstractCommand;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.command.UnexecutableCommand;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.gmf.examples.mindmap.Map;
import org.eclipse.gmf.examples.mindmap.MindmapFactory;
import org.eclipse.gmf.examples.mindmap.MindmapPackage;
import org.eclipse.gmf.examples.mindmap.Relationship;
import org.eclipse.gmf.examples.mindmap.lite.rcp.view.factories.DomainElementInitializer;
import org.eclipse.gmf.examples.mindmap.lite.rcp.view.factories.Relationship2ViewFactory;
import org.eclipse.gmf.runtime.lite.requests.CreateConnectionRequestEx;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.NotationFactory;
import org.eclipse.gmf.runtime.notation.View;

/**
 * @generated
 */
public class CreateRelationship4003Command extends AbstractCommand {
	/**
	 * @generated
	 */
	private final View source;

	/**
	 * @generated
	 */
	private final View target;

	/**
	 * @generated
	 */
	private final Edge createdEdge;

	/**
	 * @generated
	 */
	private Command domainModelAddCommand;

	/**
	 * @generated
	 */
	public CreateRelationship4003Command(CreateConnectionRequestEx requestEx) {
		if (requestEx.getSourceEditPart().getModel() instanceof View) {
			source = (View) requestEx.getSourceEditPart().getModel();
		} else {
			source = null;
		}
		if (requestEx.getTargetEditPart().getModel() instanceof View) {
			target = (View) requestEx.getTargetEditPart().getModel();
		} else {
			target = null;
		}
		if (source == null || target == null) {
			createdEdge = null;
			return;
		}
		createdEdge = NotationFactory.eINSTANCE.createEdge();
		Relationship createdDomainElement = MindmapFactory.eINSTANCE
				.createRelationship();
		createdEdge.setElement(createdDomainElement);
		DomainElementInitializer.Relationship_4003
				.initializeElement(createdDomainElement);
		Relationship2ViewFactory.INSTANCE.decorateView(createdEdge);
	}

	/**
	 * @generated
	 */
	protected boolean prepare() {
		if (source == null || target == null || createdEdge == null) {
			return false;
		}
		domainModelAddCommand = initializeDomainModelCommand();
		if (domainModelAddCommand == null
				|| !domainModelAddCommand.canExecute()) {
			return false;
		}
		return true;
	}

	/**
	 * @generated
	 */
	private Command initializeDomainModelCommand() {
		Map container = (Map) getRelationshipContainer(source.getElement(),
				MindmapPackage.eINSTANCE.getMap());
		if (container == null) {
			return UnexecutableCommand.INSTANCE;
		}
		if (((Relationship) createdEdge.getElement()).getSource() != null) {
			return UnexecutableCommand.INSTANCE;
		}
		if (((Relationship) createdEdge.getElement()).getTarget() != null) {
			return UnexecutableCommand.INSTANCE;
		}
		TransactionalEditingDomain domainModelEditDomain = TransactionUtil
				.getEditingDomain(source.getDiagram().getElement());
		CompoundCommand addLinkEndsCommand = new CompoundCommand();
		addLinkEndsCommand.append(AddCommand.create(domainModelEditDomain,
				container, MindmapPackage.eINSTANCE.getMap_Relations(),
				createdEdge.getElement()));
		addLinkEndsCommand.append(SetCommand.create(domainModelEditDomain,
				createdEdge.getElement(), MindmapPackage.eINSTANCE
						.getRelationship_Source(), source.getElement()));
		addLinkEndsCommand.append(SetCommand.create(domainModelEditDomain,
				createdEdge.getElement(), MindmapPackage.eINSTANCE
						.getRelationship_Target(), target.getElement()));
		return addLinkEndsCommand;
	}

	/**
	 * @generated
	 */
	public boolean canUndo() {
		return source != null && target != null && createdEdge != null
				&& domainModelAddCommand != null
				&& domainModelAddCommand.canUndo();
	}

	/**
	 * @generated
	 */
	public void execute() {
		domainModelAddCommand.execute();
		source.getDiagram().insertEdge(createdEdge);
		createdEdge.setSource(source);
		createdEdge.setTarget(target);
	}

	/**
	 * @generated
	 */
	public void undo() {
		domainModelAddCommand.undo();
		source.getDiagram().removeEdge(createdEdge);
		createdEdge.setSource(null);
		createdEdge.setTarget(null);
	}

	/**
	 * @generated
	 */
	public void redo() {
		execute();
	}

	/**
	 * Finds container element for the relationship of the specified type.
	 * Default implementation goes up by containment hierarchy starting from
	 * the specified element and returns the first element that is instance of
	 * the specified container class.
	 * @generated
	 */
	protected EObject getRelationshipContainer(EObject element,
			EClass containerClass) {
		for (; element != null; element = element.eContainer()) {
			if (containerClass.isSuperTypeOf(element.eClass())) {
				return element;
			}
		}
		return null;
	}
}
