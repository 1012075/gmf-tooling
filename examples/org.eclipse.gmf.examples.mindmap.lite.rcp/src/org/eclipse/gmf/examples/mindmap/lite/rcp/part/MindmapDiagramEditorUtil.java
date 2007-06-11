package org.eclipse.gmf.examples.mindmap.lite.rcp.part;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.common.ui.URIEditorInput;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.gmf.examples.mindmap.Map;
import org.eclipse.gmf.examples.mindmap.Relationship;
import org.eclipse.gmf.examples.mindmap.Resource;
import org.eclipse.gmf.examples.mindmap.Thread;
import org.eclipse.gmf.examples.mindmap.ThreadItem;
import org.eclipse.gmf.examples.mindmap.Topic;
import org.eclipse.gmf.examples.mindmap.lite.rcp.edit.parts.MapEditPart;
import org.eclipse.gmf.examples.mindmap.lite.rcp.edit.parts.Relationship2EditPart;
import org.eclipse.gmf.examples.mindmap.lite.rcp.edit.parts.Relationship3EditPart;
import org.eclipse.gmf.examples.mindmap.lite.rcp.edit.parts.RelationshipEditPart;
import org.eclipse.gmf.examples.mindmap.lite.rcp.edit.parts.ResourceEditPart;
import org.eclipse.gmf.examples.mindmap.lite.rcp.edit.parts.ThreadEditPart;
import org.eclipse.gmf.examples.mindmap.lite.rcp.edit.parts.ThreadItemEditPart;
import org.eclipse.gmf.examples.mindmap.lite.rcp.edit.parts.ThreadThreadItemCompartmentEditPart;
import org.eclipse.gmf.examples.mindmap.lite.rcp.edit.parts.TopicEditPart;
import org.eclipse.gmf.examples.mindmap.lite.rcp.edit.parts.TopicSubtopicsEditPart;
import org.eclipse.gmf.examples.mindmap.lite.rcp.edit.parts.TopicThreadCompartmentEditPart;
import org.eclipse.gmf.examples.mindmap.lite.rcp.view.factories.MapViewFactory;
import org.eclipse.gmf.examples.mindmap.lite.rcp.view.factories.Relationship2ViewFactory;
import org.eclipse.gmf.examples.mindmap.lite.rcp.view.factories.Relationship3ViewFactory;
import org.eclipse.gmf.examples.mindmap.lite.rcp.view.factories.RelationshipViewFactory;
import org.eclipse.gmf.examples.mindmap.lite.rcp.view.factories.ResourceViewFactory;
import org.eclipse.gmf.examples.mindmap.lite.rcp.view.factories.ThreadItemViewFactory;
import org.eclipse.gmf.examples.mindmap.lite.rcp.view.factories.ThreadViewFactory;
import org.eclipse.gmf.examples.mindmap.lite.rcp.view.factories.TopicSubtopicsViewFactory;
import org.eclipse.gmf.examples.mindmap.lite.rcp.view.factories.TopicViewFactory;
import org.eclipse.gmf.runtime.lite.commands.CreateNotationalEdgeCommand;
import org.eclipse.gmf.runtime.lite.commands.CreateNotationalElementCommand;
import org.eclipse.gmf.runtime.lite.commands.CreateNotationalNodeCommand;
import org.eclipse.gmf.runtime.lite.edit.parts.update.canonical.ElementDescriptor;
import org.eclipse.gmf.runtime.lite.edit.parts.update.canonical.IViewService;
import org.eclipse.gmf.runtime.lite.edit.parts.update.canonical.LinkDescriptor;
import org.eclipse.gmf.runtime.lite.edit.parts.update.canonical.NaiveViewService;
import org.eclipse.gmf.runtime.lite.services.IViewDecorator;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.NotationFactory;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorDescriptor;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

/**
 * @generated
 */
public class MindmapDiagramEditorUtil {
	/**
	 * @generated
	 */
	public static String openFilePathDialog(Shell shell,
			String fileExtensionFilter, int style) {
		FileDialog fileDialog = new FileDialog(shell, style);
		fileDialog.setFilterExtensions(new String[] { fileExtensionFilter });

		fileDialog.open();
		if (fileDialog.getFileName() != null
				&& fileDialog.getFileName().length() > 0) {
			return fileDialog.getFilterPath() + File.separator
					+ fileDialog.getFileName();
		} else {
			return null;
		}
	}

	/**
	 * @generated
	 */
	public static IEditorPart openEditor(URI fileURI) {
		IWorkbench workbench = PlatformUI.getWorkbench();
		IWorkbenchWindow workbenchWindow = workbench.getActiveWorkbenchWindow();
		IWorkbenchPage page = workbenchWindow.getActivePage();

		IEditorDescriptor editorDescriptor = workbench.getEditorRegistry()
				.getDefaultEditor(fileURI.lastSegment());
		if (editorDescriptor == null) {
			MessageDialog.openError(workbenchWindow.getShell(), "Error",
					"There is no editor registered for the file "
							+ fileURI.toFileString());
			return null;
		} else {
			try {
				return page.openEditor(new URIEditorInput(fileURI),
						editorDescriptor.getId());
			} catch (PartInitException exception) {
				MessageDialog.openError(workbenchWindow.getShell(),
						"Error Opening Editor", exception.getMessage());
				return null;
			}
		}
	}

	/**
	 * @generated
	 */
	public static Diagram createDiagramFor(EObject diagramRoot) {
		if (MapEditPart.VISUAL_ID != MindmapVisualIDRegistry
				.getDiagramVisualID(diagramRoot)) {
			return null;
		}
		Diagram result = NotationFactory.eINSTANCE.createDiagram();
		result.setElement(diagramRoot);
		MapViewFactory.INSTANCE.decorateView(result);
		new DiagramInitializer().initDiagramContents(result);
		return result;
	}

	/**
	 * @generated
	 */
	private static class DiagramInitializer {
		/**
		 * @generated
		 */
		private void initDiagramContents(Diagram diagram) {
			createMap_1000Children(diagram);
			myViewService = new NaiveViewService(diagram);
			createMap_1000ChildLinks(diagram);
		}

		/**
		 * @generated
		 */
		private void createTopicThreadCompartment_7001Children(View viewObject) {
			List semanticChildren = getTopicThreadCompartment_7001SemanticChildNodes(viewObject);
			for (Iterator it = semanticChildren.iterator(); it.hasNext();) {
				ElementDescriptor nextDescriptor = (ElementDescriptor) it
						.next();
				CreateNotationalElementCommand createCommand = getCreateTopicThreadCompartment_7001NotationalChildNodeCommand(
						viewObject, nextDescriptor);
				if (createCommand != null && createCommand.canExecute()) {
					createCommand.execute();
					View nextChild = createCommand.getCreatedView();
					int childVisualID = nextDescriptor.getVisualID();
					switch (childVisualID) {
					case ThreadEditPart.VISUAL_ID:
						createThread_3001Children(nextChild);
						break;
					}
				}
			}
		}

		/**
		 * @generated
		 */
		private List getTopicThreadCompartment_7001SemanticChildNodes(
				View parentView) {
			List result = new LinkedList();
			View viewObject = parentView;
			EObject modelObject = viewObject.getElement();
			EObject nextValue;
			int nodeVID;
			for (Iterator it = ((Topic) modelObject).getComments().iterator(); it
					.hasNext();) {
				nextValue = (EObject) it.next();
				nodeVID = MindmapVisualIDRegistry.getNodeVisualID(viewObject,
						nextValue);
				if (ThreadEditPart.VISUAL_ID == nodeVID) {
					result.add(new ElementDescriptor(nextValue, nodeVID));
				}
			}
			return result;
		}

		/**
		 * @generated
		 */
		protected CreateNotationalElementCommand getCreateTopicThreadCompartment_7001NotationalChildNodeCommand(
				View parentView, ElementDescriptor descriptor) {
			EObject domainElement = descriptor.getElement();
			int nodeVisualID = descriptor.getVisualID();
			switch (nodeVisualID) {
			case ThreadEditPart.VISUAL_ID:
				if (domainElement instanceof Thread) {
					return new CreateNotationalNodeCommand(parentView,
							domainElement, new Rectangle(0, 0, 40, 40),
							ThreadViewFactory.INSTANCE, false);
				}
				return null;
			default:
				return null;
			}
		}

		/**
		 * @generated
		 */
		private void createThreadThreadItemCompartment_7002Children(
				View viewObject) {
			List semanticChildren = getThreadThreadItemCompartment_7002SemanticChildNodes(viewObject);
			for (Iterator it = semanticChildren.iterator(); it.hasNext();) {
				ElementDescriptor nextDescriptor = (ElementDescriptor) it
						.next();
				CreateNotationalElementCommand createCommand = getCreateThreadThreadItemCompartment_7002NotationalChildNodeCommand(
						viewObject, nextDescriptor);
				if (createCommand != null && createCommand.canExecute()) {
					createCommand.execute();
				}
			}
		}

		/**
		 * @generated
		 */
		private List getThreadThreadItemCompartment_7002SemanticChildNodes(
				View parentView) {
			List result = new LinkedList();
			View viewObject = parentView;
			EObject modelObject = viewObject.getElement();
			EObject nextValue;
			int nodeVID;
			for (Iterator it = ((Thread) modelObject).getItems().iterator(); it
					.hasNext();) {
				nextValue = (EObject) it.next();
				nodeVID = MindmapVisualIDRegistry.getNodeVisualID(viewObject,
						nextValue);
				if (ThreadItemEditPart.VISUAL_ID == nodeVID) {
					result.add(new ElementDescriptor(nextValue, nodeVID));
				}
			}
			return result;
		}

		/**
		 * @generated
		 */
		protected CreateNotationalElementCommand getCreateThreadThreadItemCompartment_7002NotationalChildNodeCommand(
				View parentView, ElementDescriptor descriptor) {
			EObject domainElement = descriptor.getElement();
			int nodeVisualID = descriptor.getVisualID();
			switch (nodeVisualID) {
			case ThreadItemEditPart.VISUAL_ID:
				if (domainElement instanceof ThreadItem) {
					return new CreateNotationalNodeCommand(parentView,
							domainElement, null,
							ThreadItemViewFactory.INSTANCE, false);
				}
				return null;
			default:
				return null;
			}
		}

		/**
		 * @generated
		 */
		private void createMap_1000Children(View viewObject) {
			List semanticChildren = getMap_1000SemanticChildNodes(viewObject);
			for (Iterator it = semanticChildren.iterator(); it.hasNext();) {
				ElementDescriptor nextDescriptor = (ElementDescriptor) it
						.next();
				CreateNotationalElementCommand createCommand = getCreateMap_1000NotationalChildNodeCommand(
						viewObject, nextDescriptor);
				if (createCommand != null && createCommand.canExecute()) {
					createCommand.execute();
					View nextChild = createCommand.getCreatedView();
					int childVisualID = nextDescriptor.getVisualID();
					switch (childVisualID) {
					case TopicEditPart.VISUAL_ID:
						createTopic_2001Children(nextChild);
						break;
					}
				}
			}
		}

		/**
		 * @generated
		 */
		private List getMap_1000SemanticChildNodes(View parentView) {
			List result = new LinkedList();
			View viewObject = parentView;
			EObject modelObject = viewObject.getElement();
			EObject nextValue;
			int nodeVID;
			for (Iterator it = ((Map) modelObject).getRootTopics().iterator(); it
					.hasNext();) {
				nextValue = (EObject) it.next();
				nodeVID = MindmapVisualIDRegistry.getNodeVisualID(viewObject,
						nextValue);
				if (TopicEditPart.VISUAL_ID == nodeVID) {
					result.add(new ElementDescriptor(nextValue, nodeVID));
				}
			}
			for (Iterator it = ((Map) modelObject).getResources().iterator(); it
					.hasNext();) {
				nextValue = (EObject) it.next();
				nodeVID = MindmapVisualIDRegistry.getNodeVisualID(viewObject,
						nextValue);
				if (ResourceEditPart.VISUAL_ID == nodeVID) {
					result.add(new ElementDescriptor(nextValue, nodeVID));
				}
			}
			return result;
		}

		/**
		 * @generated
		 */
		protected CreateNotationalElementCommand getCreateMap_1000NotationalChildNodeCommand(
				View parentView, ElementDescriptor descriptor) {
			EObject domainElement = descriptor.getElement();
			int nodeVisualID = descriptor.getVisualID();
			switch (nodeVisualID) {
			case TopicEditPart.VISUAL_ID:
				if (domainElement instanceof Topic) {
					return new CreateNotationalNodeCommand(parentView,
							domainElement, new Rectangle(0, 0, 40, 40),
							TopicViewFactory.INSTANCE, false);
				}
				return null;
			case ResourceEditPart.VISUAL_ID:
				if (domainElement instanceof Resource) {
					return new CreateNotationalNodeCommand(parentView,
							domainElement, new Rectangle(0, 0, 40, 60),
							ResourceViewFactory.INSTANCE, false);
				}
				return null;
			default:
				return null;
			}
		}

		/**
		 * @generated
		 */
		private void createTopic_2001Children(View viewObject) {
			for (Iterator it = viewObject.getChildren().iterator(); it
					.hasNext();) {
				View next = (View) it.next();
				int visualID = MindmapVisualIDRegistry.getVisualID(next);
				switch (visualID) {
				case TopicThreadCompartmentEditPart.VISUAL_ID:
					createTopicThreadCompartment_7001Children(next);
					break;
				}
			}
		}

		/**
		 * @generated
		 */
		private void createThread_3001Children(View viewObject) {
			for (Iterator it = viewObject.getChildren().iterator(); it
					.hasNext();) {
				View next = (View) it.next();
				int visualID = MindmapVisualIDRegistry.getVisualID(next);
				switch (visualID) {
				case ThreadThreadItemCompartmentEditPart.VISUAL_ID:
					createThreadThreadItemCompartment_7002Children(next);
					break;
				}
			}
		}

		/**
		 * @generated
		 */
		private void createTopic_2001ChildLinks(View viewObject) {
			List semanticChildLinks = getTopic_2001SemanticChildLinks(viewObject);
			for (Iterator it = semanticChildLinks.iterator(); it.hasNext();) {
				LinkDescriptor nextDescriptor = (LinkDescriptor) it.next();
				CreateNotationalElementCommand createCommand = getCreateTopic_2001NotationalChildLinkCommand(
						viewObject, nextDescriptor);
				if (createCommand != null && createCommand.canExecute()) {
					createCommand.execute();
				}
			}
		}

		/**
		 * @generated
		 */
		private List getTopic_2001SemanticChildLinks(View parentView) {
			List result = new LinkedList();
			EObject modelObject = parentView.getElement();
			EObject nextValue;
			for (Iterator it = ((Topic) modelObject).getSubtopics().iterator(); it
					.hasNext();) {
				nextValue = (EObject) it.next();
				if (nextValue != null) {
					result.add(new LinkDescriptor(modelObject, nextValue, null,
							TopicSubtopicsEditPart.VISUAL_ID));
				}
			}
			return result;
		}

		/**
		 * @generated
		 */
		protected CreateNotationalElementCommand getCreateTopic_2001NotationalChildLinkCommand(
				View parentView, LinkDescriptor linkDescriptor) {
			View sourceView = getViewService().findView(
					linkDescriptor.getSource());
			View targetView = getViewService().findView(
					linkDescriptor.getDestination());
			IViewDecorator decorator = null;
			if (sourceView == null || targetView == null) {
				return null;
			}
			String sourceModelID = MindmapVisualIDRegistry
					.getModelID(sourceView);
			if (!MapEditPart.MODEL_ID.equals(sourceModelID)) {
				return null;
			}
			String targetModelID = MindmapVisualIDRegistry
					.getModelID(targetView);
			if (!MapEditPart.MODEL_ID.equals(targetModelID)) {
				return null;
			}
			switch (linkDescriptor.getVisualID()) {
			case TopicSubtopicsEditPart.VISUAL_ID:
				if (linkDescriptor.getElement() == null) {
					decorator = TopicSubtopicsViewFactory.INSTANCE;
				}
				break;
			}
			if (decorator == null) {
				return null;
			}
			return new CreateNotationalEdgeCommand(parentView.getDiagram(),
					linkDescriptor.getElement(), sourceView, targetView,
					decorator);
		}

		/**
		 * @generated
		 */
		private void createMap_1000ChildLinks(View viewObject) {
			List semanticChildLinks = getMap_1000SemanticChildLinks(viewObject);
			for (Iterator it = semanticChildLinks.iterator(); it.hasNext();) {
				LinkDescriptor nextDescriptor = (LinkDescriptor) it.next();
				CreateNotationalElementCommand createCommand = getCreateMap_1000NotationalChildLinkCommand(
						viewObject, nextDescriptor);
				if (createCommand != null && createCommand.canExecute()) {
					createCommand.execute();
				}
			}
			for (Iterator it = viewObject.getChildren().iterator(); it
					.hasNext();) {
				View next = (View) it.next();
				int visualID = MindmapVisualIDRegistry.getVisualID(next);
				switch (visualID) {
				case TopicEditPart.VISUAL_ID:
					createTopic_2001ChildLinks(next);
					break;
				}
			}
		}

		/**
		 * @generated
		 */
		private List getMap_1000SemanticChildLinks(View parentView) {
			List result = new LinkedList();
			EObject modelObject = parentView.getElement();
			EObject nextValue;
			int linkVID;
			for (Iterator it = ((Map) modelObject).getRelations().iterator(); it
					.hasNext();) {
				nextValue = (EObject) it.next();
				linkVID = MindmapVisualIDRegistry
						.getLinkWithClassVisualID(nextValue);
				switch (linkVID) {
				case RelationshipEditPart.VISUAL_ID: {
					EObject source = (EObject) ((Relationship) nextValue)
							.getSource();
					EObject target = (EObject) ((Relationship) nextValue)
							.getTarget();
					if (source != null && target != null) {
						result.add(new LinkDescriptor(source, target,
								nextValue, linkVID));
					}
					break;
				}
				case Relationship2EditPart.VISUAL_ID: {
					EObject source = (EObject) ((Relationship) nextValue)
							.getSource();
					EObject target = (EObject) ((Relationship) nextValue)
							.getTarget();
					if (source != null && target != null) {
						result.add(new LinkDescriptor(source, target,
								nextValue, linkVID));
					}
					break;
				}
				case Relationship3EditPart.VISUAL_ID: {
					EObject source = (EObject) ((Relationship) nextValue)
							.getSource();
					EObject target = (EObject) ((Relationship) nextValue)
							.getTarget();
					if (source != null && target != null) {
						result.add(new LinkDescriptor(source, target,
								nextValue, linkVID));
					}
					break;
				}
				}
			}
			return result;
		}

		/**
		 * @generated
		 */
		protected CreateNotationalElementCommand getCreateMap_1000NotationalChildLinkCommand(
				View parentView, LinkDescriptor linkDescriptor) {
			View sourceView = getViewService().findView(
					linkDescriptor.getSource());
			View targetView = getViewService().findView(
					linkDescriptor.getDestination());
			IViewDecorator decorator = null;
			if (sourceView == null || targetView == null) {
				return null;
			}
			String sourceModelID = MindmapVisualIDRegistry
					.getModelID(sourceView);
			if (!MapEditPart.MODEL_ID.equals(sourceModelID)) {
				return null;
			}
			String targetModelID = MindmapVisualIDRegistry
					.getModelID(targetView);
			if (!MapEditPart.MODEL_ID.equals(targetModelID)) {
				return null;
			}
			switch (linkDescriptor.getVisualID()) {
			case RelationshipEditPart.VISUAL_ID:
				if (linkDescriptor.getElement() instanceof Relationship) {
					decorator = RelationshipViewFactory.INSTANCE;
				}
				break;
			case Relationship2EditPart.VISUAL_ID:
				if (linkDescriptor.getElement() instanceof Relationship) {
					decorator = Relationship2ViewFactory.INSTANCE;
				}
				break;
			case Relationship3EditPart.VISUAL_ID:
				if (linkDescriptor.getElement() instanceof Relationship) {
					decorator = Relationship3ViewFactory.INSTANCE;
				}
				break;
			}
			if (decorator == null) {
				return null;
			}
			return new CreateNotationalEdgeCommand(parentView.getDiagram(),
					linkDescriptor.getElement(), sourceView, targetView,
					decorator);
		}

		/**
		 * @generated
		 */
		private IViewService myViewService;

		/**
		 * @generated
		 */
		private IViewService getViewService() {
			return myViewService;
		}
	}

	/**
	 * @generated
	 */
	public static URI getDiagramFileURI(URI modelFileURI) {
		if (modelFileURI == null || !modelFileURI.isFile()) {
			return null;
		}
		return modelFileURI.trimFileExtension().appendFileExtension("mmd"); //$NON-NLS-1$
	}

	/**
	 * @generated
	 */
	public static URI getModelFileURI(URI diagramFileURI) {
		if (diagramFileURI == null || !diagramFileURI.isFile()) {
			return null;
		}
		return diagramFileURI.trimFileExtension()
				.appendFileExtension("mindmap"); //$NON-NLS-1$
	}

	/**
	 * Returns whether the given element is read only in its editing domain.
	 * @generated
	 */
	public static boolean isReadOnly(EObject element) {
		if (element == null) {
			return true;
		}
		org.eclipse.emf.ecore.resource.Resource resource = element.eResource();
		if (resource == null) {
			return false;
		}
		return isReadOnly(resource);
	}

	/**
	 * @generated
	 */
	public static boolean isReadOnly(
			org.eclipse.emf.ecore.resource.Resource resource) {
		TransactionalEditingDomain editingDomain = TransactionUtil
				.getEditingDomain(resource);
		if (editingDomain == null) {
			return false;
		}
		return editingDomain.isReadOnly(resource);
	}

	/**
	 * @generated
	 */
	public static java.util.Map getSaveOptions() {
		java.util.Map saveOptions = new HashMap();
		saveOptions.put(XMLResource.OPTION_ENCODING, "UTF-8"); //$NON-NLS-1$
		saveOptions
				.put(XMLResource.OPTION_RECORD_UNKNOWN_FEATURE, Boolean.TRUE);
		saveOptions
				.put(
						org.eclipse.emf.ecore.resource.Resource.OPTION_SAVE_ONLY_IF_CHANGED,
						org.eclipse.emf.ecore.resource.Resource.OPTION_SAVE_ONLY_IF_CHANGED_MEMORY_BUFFER);
		return saveOptions;
	}
}
