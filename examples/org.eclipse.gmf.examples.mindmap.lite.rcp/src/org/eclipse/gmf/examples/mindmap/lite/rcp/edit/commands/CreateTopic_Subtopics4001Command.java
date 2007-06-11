package org.eclipse.gmf.examples.mindmap.lite.rcp.edit.commands;

import org.eclipse.emf.common.command.AbstractCommand;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.gmf.examples.mindmap.MindmapPackage;
import org.eclipse.gmf.examples.mindmap.lite.rcp.view.factories.DomainElementInitializer;
import org.eclipse.gmf.examples.mindmap.lite.rcp.view.factories.TopicSubtopicsViewFactory;
import org.eclipse.gmf.runtime.lite.requests.CreateConnectionRequestEx;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.NotationFactory;
import org.eclipse.gmf.runtime.notation.View;

/**
 * @generated
 */
public class CreateTopic_Subtopics4001Command extends AbstractCommand {
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
	public CreateTopic_Subtopics4001Command(CreateConnectionRequestEx requestEx) {
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
		createdEdge.setElement(null);
		TopicSubtopicsViewFactory.INSTANCE.decorateView(createdEdge);
	}

	/**
	 * @generated
	 */
	protected boolean prepare() {
		if (source == null || target == null || createdEdge == null) {
			return false;
		}
		if (!DomainElementInitializer.LinkConstraints.TopicSubtopics_4001
				.canCreateLink(source.getElement(), target.getElement(), false)) {
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
		TransactionalEditingDomain domainModelEditDomain = TransactionUtil
				.getEditingDomain(source.getDiagram().getElement());
		return AddCommand.create(domainModelEditDomain, source.getElement(),
				MindmapPackage.eINSTANCE.getTopic_Subtopics(), target
						.getElement());
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
}
