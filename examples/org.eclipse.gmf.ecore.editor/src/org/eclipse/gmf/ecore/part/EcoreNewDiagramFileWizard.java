/*
 *  Copyright (c) 2006, 2007 Borland Software Corp.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Alexander Shatalin (Borland) - initial API and implementation
 */
package org.eclipse.gmf.ecore.part;

import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.operations.OperationHistoryFactory;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.edit.provider.IWrapperItemProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.ecore.edit.parts.EPackageEditPart;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.diagram.core.services.ViewService;
import org.eclipse.gmf.runtime.diagram.core.services.view.CreateDiagramViewOperation;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;
import org.eclipse.gmf.runtime.emf.core.util.EObjectAdapter;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.dialogs.WizardNewFileCreationPage;

/**
 * @generated
 */
public class EcoreNewDiagramFileWizard extends Wizard {

	/**
	 * @generated
	 */
	private TransactionalEditingDomain myEditingDomain;

	/**
	 * @generated
	 */
	private WizardNewFileCreationPage myFileCreationPage;

	/**
	 * @generated
	 */
	private org.eclipse.emf.common.util.URI domainModelURI;

	/**
	 * @generated
	 */
	private EObject myDiagramRoot;

	/**
	 * @generated
	 */
	public EcoreNewDiagramFileWizard(org.eclipse.emf.common.util.URI domainModelURI, EObject diagramRoot, TransactionalEditingDomain editingDomain) {
		assert domainModelURI != null : "Domain model uri must be specified"; //$NON-NLS-1$
		assert diagramRoot != null : "Null diagramRoot in EcoreNewDiagramFileWizard constructor"; //$NON-NLS-1$
		assert editingDomain != null : "Null editingDomain in EcoreNewDiagramFileWizard constructor"; //$NON-NLS-1$

		this.domainModelURI = domainModelURI;
		myDiagramRoot = diagramRoot;
		myEditingDomain = editingDomain;
	}

	/**
	 * @generated
	 */
	public void addPages() {
		myFileCreationPage = new WizardNewFileCreationPage("Initialize new diagram file", StructuredSelection.EMPTY);
		myFileCreationPage.setTitle("Diagram file");
		myFileCreationPage.setDescription("Create new diagram based on " + EPackageEditPart.MODEL_ID + " model content");
		IPath filePath;
		String fileName = domainModelURI.trimFileExtension().lastSegment();
		if (domainModelURI.isPlatformResource()) {
			filePath = new Path(domainModelURI.trimSegments(1).toPlatformString(true));
		} else if (domainModelURI.isFile()) {
			filePath = new Path(domainModelURI.trimSegments(1).toFileString());
		} else {
			// TODO : use some default path
			throw new IllegalArgumentException("Unsupported URI: " + domainModelURI);
		}
		myFileCreationPage.setContainerFullPath(filePath);
		myFileCreationPage.setFileName(EcoreDiagramEditorUtil.getUniqueFileName(filePath, fileName, "ecore_diagram")); //$NON-NLS-1$
		addPage(myFileCreationPage);
		addPage(new RootElementSelectorPage());
	}

	/**
	 * @generated
	 */
	public boolean performFinish() {
		List affectedFiles = new LinkedList();
		IFile diagramFile = myFileCreationPage.createNewFile();
		try {
			diagramFile.setCharset("UTF-8", new NullProgressMonitor()); //$NON-NLS-1$
		} catch (CoreException e) {
			EcoreDiagramEditorPlugin.getInstance().logError("Unable to set charset for diagram file", e); //$NON-NLS-1$
		}
		affectedFiles.add(diagramFile);
		org.eclipse.emf.common.util.URI diagramModelURI = org.eclipse.emf.common.util.URI.createPlatformResourceURI(diagramFile.getFullPath().toString(), true);
		ResourceSet resourceSet = myEditingDomain.getResourceSet();
		final Resource diagramResource = resourceSet.createResource(diagramModelURI);
		AbstractTransactionalCommand command = new AbstractTransactionalCommand(myEditingDomain, "Initializing diagram contents", affectedFiles) { //$NON-NLS-1$

			protected CommandResult doExecuteWithResult(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
				int diagramVID = EcoreVisualIDRegistry.getDiagramVisualID(myDiagramRoot);
				if (diagramVID != EPackageEditPart.VISUAL_ID) {
					return CommandResult.newErrorCommandResult("Incorrect model object stored as a root resource object"); //$NON-NLS-1$
				}
				Diagram diagram = ViewService.createDiagram(myDiagramRoot, EPackageEditPart.MODEL_ID, EcoreDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT);
				diagramResource.getContents().add(diagram);
				return CommandResult.newOKCommandResult();
			}
		};
		try {
			OperationHistoryFactory.getOperationHistory().execute(command, new NullProgressMonitor(), null);
			diagramResource.save(Collections.EMPTY_MAP);
			EcoreDiagramEditorUtil.openDiagram(diagramResource);
		} catch (ExecutionException e) {
			EcoreDiagramEditorPlugin.getInstance().logError("Unable to create model and diagram", e); //$NON-NLS-1$
		} catch (IOException ex) {
			EcoreDiagramEditorPlugin.getInstance().logError("Save operation failed for: " + diagramModelURI, ex); //$NON-NLS-1$
		} catch (PartInitException ex) {
			EcoreDiagramEditorPlugin.getInstance().logError("Unable to open editor", ex); //$NON-NLS-1$
		}
		return true;
	}

	/**
	 * @generated
	 */
	private class RootElementSelectorPage extends WizardPage implements ISelectionChangedListener {

		/**
		 * @generated
		 */
		protected RootElementSelectorPage() {
			super("Select diagram root element");
			setTitle("Diagram root element");
			setDescription("Select semantic model element to be depicted on diagram");
		}

		/**
		 * @generated
		 */
		public void createControl(Composite parent) {
			initializeDialogUnits(parent);
			Composite topLevel = new Composite(parent, SWT.NONE);
			topLevel.setLayout(new GridLayout());
			topLevel.setLayoutData(new GridData(GridData.VERTICAL_ALIGN_FILL | GridData.HORIZONTAL_ALIGN_FILL));
			topLevel.setFont(parent.getFont());
			setControl(topLevel);
			createModelBrowser(topLevel);
			setPageComplete(validatePage());
		}

		/**
		 * @generated
		 */
		private void createModelBrowser(Composite parent) {
			Composite panel = new Composite(parent, SWT.NONE);
			panel.setLayoutData(new GridData(GridData.FILL_BOTH));
			GridLayout layout = new GridLayout();
			layout.marginWidth = 0;
			panel.setLayout(layout);

			Label label = new Label(panel, SWT.NONE);
			label.setText("Select diagram root element:");
			label.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING));

			TreeViewer treeViewer = new TreeViewer(panel, SWT.SINGLE | SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);
			GridData layoutData = new GridData(GridData.FILL_BOTH);
			layoutData.heightHint = 300;
			layoutData.widthHint = 300;
			treeViewer.getTree().setLayoutData(layoutData);
			treeViewer.setContentProvider(new AdapterFactoryContentProvider(EcoreDiagramEditorPlugin.getInstance().getItemProvidersAdapterFactory()));
			treeViewer.setLabelProvider(new AdapterFactoryLabelProvider(EcoreDiagramEditorPlugin.getInstance().getItemProvidersAdapterFactory()));
			treeViewer.setInput(myDiagramRoot.eResource());
			treeViewer.setSelection(new StructuredSelection(myDiagramRoot));
			treeViewer.addSelectionChangedListener(this);
		}

		/**
		 * @generated
		 */
		public void selectionChanged(SelectionChangedEvent event) {
			myDiagramRoot = null;
			if (event.getSelection() instanceof IStructuredSelection) {
				IStructuredSelection selection = (IStructuredSelection) event.getSelection();
				if (selection.size() == 1) {
					Object selectedElement = selection.getFirstElement();
					if (selectedElement instanceof IWrapperItemProvider) {
						selectedElement = ((IWrapperItemProvider) selectedElement).getValue();
					}
					if (selectedElement instanceof FeatureMap.Entry) {
						selectedElement = ((FeatureMap.Entry) selectedElement).getValue();
					}
					if (selectedElement instanceof EObject) {
						myDiagramRoot = (EObject) selectedElement;
					}
				}
			}
			setPageComplete(validatePage());
		}

		/**
		 * @generated
		 */
		private boolean validatePage() {
			if (myDiagramRoot == null) {
				setErrorMessage("Diagram root element is not selected");
				return false;
			}
			boolean result = ViewService.getInstance().provides(
					new CreateDiagramViewOperation(new EObjectAdapter(myDiagramRoot), EPackageEditPart.MODEL_ID, EcoreDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT));
			setErrorMessage(result ? null : "Invalid diagram root element was selected");
			return result;
		}
	}
}
