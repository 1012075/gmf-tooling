/*
 * Copyright (c) 2006, 2007 Borland Software Corporation.
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v1.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 *  
 *   Contributors:
 *      Richard Gronback (Borland) - initial API and implementation
 */

package org.eclipse.gmf.examples.mindmap.rcp.providers;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.Diagnostician;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.emf.validation.model.EvaluationMode;
import org.eclipse.emf.validation.model.IConstraintStatus;
import org.eclipse.emf.validation.service.IBatchValidator;
import org.eclipse.emf.validation.service.ModelValidationService;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gmf.examples.mindmap.rcp.part.Messages;
import org.eclipse.gmf.examples.mindmap.rcp.part.MindmapDiagramEditor;
import org.eclipse.gmf.examples.mindmap.rcp.part.MindmapDiagramEditorPlugin;
import org.eclipse.gmf.runtime.lite.edit.parts.update.canonical.EditPartRegistryBasedViewService;
import org.eclipse.gmf.runtime.lite.edit.parts.update.canonical.IViewService;
import org.eclipse.gmf.runtime.lite.edit.parts.update.canonical.NaiveViewService;
import org.eclipse.gmf.runtime.lite.validation.IValidationStateUpdater;
import org.eclipse.gmf.runtime.lite.validation.ValidationState;
import org.eclipse.gmf.runtime.lite.validation.ViolationDescriptor;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.ui.IWorkbenchPart;

/**
 * @generated
 */
public class ValidateAction extends Action {
	/**
	 * @generated
	 */
	public static final String VALIDATE_ACTION_KEY = "validateAction"; //$NON-NLS-1$

	/**
	 * @generated
	 */
	private IWorkbenchPart myWorkbenchPart;

	/**
	 * @generated
	 */
	public ValidateAction(IWorkbenchPart part) {
		setId(VALIDATE_ACTION_KEY);
		setText(Messages.ValidateAction_ActionName);
		myWorkbenchPart = part;
		setEnabled(myWorkbenchPart instanceof MindmapDiagramEditor);
	}

	/**
	 * @generated
	 */
	public void run() {
		if (!isEnabled()) {
			return;
		}
		final MindmapDiagramEditor part = (MindmapDiagramEditor) myWorkbenchPart;
		try {
			new IRunnableWithProgress() {
				public void run(IProgressMonitor monitor)
						throws InterruptedException, InvocationTargetException {
					GraphicalViewer viewer = (GraphicalViewer) part
							.getAdapter(GraphicalViewer.class);
					Diagram diagram = part.getDiagram();
					IViewService viewService;
					if (viewer == null) {
						viewService = new NaiveViewService(diagram);
					} else {
						viewService = new EditPartRegistryBasedViewService(
								viewer);
					}
					runValidation(diagram, viewService);
				}
			}.run(new NullProgressMonitor());
		} catch (Exception e) {
			MindmapDiagramEditorPlugin.getInstance().logError(
					"Validation action failed", e); //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	public static void runValidation(View view, IViewService service) {
		final View target = view;
		final IViewService viewService = service;
		TransactionalEditingDomain txDomain = TransactionUtil
				.getEditingDomain(target);
		MindmapValidationProvider.runWithConstraints(txDomain, new Runnable() {
			public void run() {
				validate(target, viewService);
			}
		});
	}

	/**
	 * @generated
	 */
	private static Diagnostic runEMFValidator(View target) {
		if (target.isSetElement() && target.getElement() != null) {
			return new Diagnostician().validate(target.getElement());
		}
		return Diagnostic.OK_INSTANCE;
	}

	/**
	 * @generated
	 */
	private static void validate(View target, IViewService viewService) {
		URI diagramUri = (target.getDiagram() != null) ? EcoreUtil
				.getURI(target.getDiagram()) : null;
		if (diagramUri != null) {
			ValidationState validationState = MindmapDiagramEditorPlugin
					.getInstance().getValidationStateManager()
					.getValidationState(diagramUri);
			IValidationStateUpdater updater = validationState.startUpdate();
			try {
				Diagnostic diagnostic = runEMFValidator(target);
				update(updater, diagnostic, viewService);
				IBatchValidator validator = (IBatchValidator) ModelValidationService
						.getInstance().newValidator(EvaluationMode.BATCH);
				validator.setIncludeLiveConstraints(true);
				if (target.isSetElement() && target.getElement() != null) {
					IStatus status = validator.validate(target.getElement());
					update(updater, status, viewService);
				}

				updater.commitChanges();
			} catch (Exception e) {
				updater.discardChanges();
			}
		}
	}

	/**
	 * @generated
	 */
	private static void update(IValidationStateUpdater updater,
			Diagnostic emfValidationStatus, IViewService viewService) {
		if (emfValidationStatus.getSeverity() == Diagnostic.OK) {
			return;
		}
		final Diagnostic rootStatus = emfValidationStatus;
		List allDiagnostics = new ArrayList();
		collectTargetElements(rootStatus, new HashSet(), allDiagnostics);
		for (Iterator it = emfValidationStatus.getChildren().iterator(); it
				.hasNext();) {
			Diagnostic nextDiagnostic = (Diagnostic) it.next();
			List data = nextDiagnostic.getData();
			if (data != null && !data.isEmpty()
					&& data.get(0) instanceof EObject) {
				EObject element = (EObject) data.get(0);
				View view = viewService.findView(element);
				if (view != null) {
					ViolationDescriptor next = ViolationDescriptor.create(view,
							nextDiagnostic);
					if (next != null) {
						updater.addViolation(next);
					}
				}
			}
		}
	}

	/**
	 * @generated
	 */
	private static Set collectTargetElements(Diagnostic diagnostic,
			Set targetElementCollector, List allDiagnostics) {
		List data = diagnostic.getData();
		EObject target = null;
		if (data != null && !data.isEmpty() && data.get(0) instanceof EObject) {
			target = (EObject) data.get(0);
			targetElementCollector.add(target);
			allDiagnostics.add(diagnostic);
		}
		if (diagnostic.getChildren() != null
				&& !diagnostic.getChildren().isEmpty()) {
			for (Iterator it = diagnostic.getChildren().iterator(); it
					.hasNext();) {
				collectTargetElements((Diagnostic) it.next(),
						targetElementCollector, allDiagnostics);
			}
		}
		return targetElementCollector;
	}

	/**
	 * @generated
	 */
	private static void update(IValidationStateUpdater updater,
			IStatus validationStatus, IViewService viewService) {
		if (validationStatus.isOK()) {
			return;
		}
		final IStatus rootStatus = validationStatus;
		List allStatuses = new ArrayList();
		collectTargetElements(rootStatus, new HashSet(), allStatuses);
		for (Iterator it = allStatuses.iterator(); it.hasNext();) {
			IConstraintStatus nextStatus = (IConstraintStatus) it.next();
			View view = viewService.findView(nextStatus.getTarget());
			if (view != null) {
				ViolationDescriptor next = ViolationDescriptor.create(view,
						nextStatus);
				if (next != null) {
					updater.addViolation(next);
				}
			}
		}
	}

	/**
	 * @generated
	 */
	private static Set collectTargetElements(IStatus status,
			Set targetElementCollector, List allConstraintStatuses) {
		if (status instanceof IConstraintStatus) {
			targetElementCollector
					.add(((IConstraintStatus) status).getTarget());
			allConstraintStatuses.add(status);
		}
		if (status.isMultiStatus()) {
			IStatus[] children = status.getChildren();
			for (int i = 0; i < children.length; i++) {
				collectTargetElements(children[i], targetElementCollector,
						allConstraintStatuses);
			}
		}
		return targetElementCollector;
	}

}
