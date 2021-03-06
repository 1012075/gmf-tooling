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
package org.eclipse.gmf.tooling.simplemap.diagram.providers;

import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.gmf.runtime.emf.type.core.ElementTypeRegistry;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.tooling.simplemap.diagram.edit.parts.SimpleChildReferenceEditPart;
import org.eclipse.gmf.tooling.simplemap.diagram.edit.parts.SimpleCompartmentEditPart;
import org.eclipse.gmf.tooling.simplemap.diagram.edit.parts.SimpleLabelNodeEditPart;
import org.eclipse.gmf.tooling.simplemap.diagram.edit.parts.SimpleLinkMappingEditPart;
import org.eclipse.gmf.tooling.simplemap.diagram.edit.parts.SimpleMappingEditPart;
import org.eclipse.gmf.tooling.simplemap.diagram.edit.parts.SimpleSubNodeEditPart;
import org.eclipse.gmf.tooling.simplemap.diagram.edit.parts.SimpleTopNodeEditPart;
import org.eclipse.gmf.tooling.simplemap.diagram.part.SimplemapDiagramEditorPlugin;
import org.eclipse.gmf.tooling.simplemap.simplemappings.SimplemappingsPackage;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.swt.graphics.Image;

/**
 * @generated
 */
public class SimplemapElementTypes {

	/**
	 * @generated
	 */
	private SimplemapElementTypes() {
	}

	/**
	 * @generated
	 */
	private static Map<IElementType, ENamedElement> elements;

	/**
	 * @generated
	 */
	private static ImageRegistry imageRegistry;

	/**
	 * @generated
	 */
	private static Set<IElementType> KNOWN_ELEMENT_TYPES;

	/**
	 * @generated
	 */
	public static final IElementType SimpleMapping_79 = getElementType("org.eclipse.gmf.tooling.simplemap.diagram.SimpleMapping_79"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType SimpleTopNode_1001 = getElementType("org.eclipse.gmf.tooling.simplemap.diagram.SimpleTopNode_1001"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType SimpleLinkMapping_1002 = getElementType("org.eclipse.gmf.tooling.simplemap.diagram.SimpleLinkMapping_1002"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType SimpleLabelNode_2001 = getElementType("org.eclipse.gmf.tooling.simplemap.diagram.SimpleLabelNode_2001"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType SimpleCompartment_2002 = getElementType("org.eclipse.gmf.tooling.simplemap.diagram.SimpleCompartment_2002"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType SimpleSubNode_2003 = getElementType("org.eclipse.gmf.tooling.simplemap.diagram.SimpleSubNode_2003"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType SimpleChildReference_2004 = getElementType("org.eclipse.gmf.tooling.simplemap.diagram.SimpleChildReference_2004"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	private static ImageRegistry getImageRegistry() {
		if (imageRegistry == null) {
			imageRegistry = new ImageRegistry();
		}
		return imageRegistry;
	}

	/**
	 * @generated
	 */
	private static String getImageRegistryKey(ENamedElement element) {
		return element.getName();
	}

	/**
	 * @generated
	 */
	private static ImageDescriptor getProvidedImageDescriptor(ENamedElement element) {
		if (element instanceof EStructuralFeature) {
			EStructuralFeature feature = ((EStructuralFeature) element);
			EClass eContainingClass = feature.getEContainingClass();
			EClassifier eType = feature.getEType();
			if (eContainingClass != null && !eContainingClass.isAbstract()) {
				element = eContainingClass;
			} else if (eType instanceof EClass && !((EClass) eType).isAbstract()) {
				element = eType;
			}
		}
		if (element instanceof EClass) {
			EClass eClass = (EClass) element;
			if (!eClass.isAbstract()) {
				return SimplemapDiagramEditorPlugin.getInstance().getItemImageDescriptor(eClass.getEPackage().getEFactoryInstance().create(eClass));
			}
		}
		// TODO : support structural features
		return null;
	}

	/**
	 * @generated
	 */
	public static ImageDescriptor getImageDescriptor(ENamedElement element) {
		String key = getImageRegistryKey(element);
		ImageDescriptor imageDescriptor = getImageRegistry().getDescriptor(key);
		if (imageDescriptor == null) {
			imageDescriptor = getProvidedImageDescriptor(element);
			if (imageDescriptor == null) {
				imageDescriptor = ImageDescriptor.getMissingImageDescriptor();
			}
			getImageRegistry().put(key, imageDescriptor);
		}
		return imageDescriptor;
	}

	/**
	 * @generated
	 */
	public static Image getImage(ENamedElement element) {
		String key = getImageRegistryKey(element);
		Image image = getImageRegistry().get(key);
		if (image == null) {
			ImageDescriptor imageDescriptor = getProvidedImageDescriptor(element);
			if (imageDescriptor == null) {
				imageDescriptor = ImageDescriptor.getMissingImageDescriptor();
			}
			getImageRegistry().put(key, imageDescriptor);
			image = getImageRegistry().get(key);
		}
		return image;
	}

	/**
	 * @generated
	 */
	public static ImageDescriptor getImageDescriptor(IAdaptable hint) {
		ENamedElement element = getElement(hint);
		if (element == null) {
			return null;
		}
		return getImageDescriptor(element);
	}

	/**
	 * @generated
	 */
	public static Image getImage(IAdaptable hint) {
		ENamedElement element = getElement(hint);
		if (element == null) {
			return null;
		}
		return getImage(element);
	}

	/**
	 * Returns 'type' of the ecore object associated with the hint.
	 * 
	 * @generated
	 */
	public static ENamedElement getElement(IAdaptable hint) {
		Object type = hint.getAdapter(IElementType.class);
		if (elements == null) {
			elements = new IdentityHashMap<IElementType, ENamedElement>();

			elements.put(SimpleMapping_79, SimplemappingsPackage.eINSTANCE.getSimpleMapping());

			elements.put(SimpleTopNode_1001, SimplemappingsPackage.eINSTANCE.getSimpleTopNode());

			elements.put(SimpleLinkMapping_1002, SimplemappingsPackage.eINSTANCE.getSimpleLinkMapping());

			elements.put(SimpleLabelNode_2001, SimplemappingsPackage.eINSTANCE.getSimpleLabelNode());

			elements.put(SimpleCompartment_2002, SimplemappingsPackage.eINSTANCE.getSimpleCompartment());

			elements.put(SimpleSubNode_2003, SimplemappingsPackage.eINSTANCE.getSimpleSubNode());

			elements.put(SimpleChildReference_2004, SimplemappingsPackage.eINSTANCE.getSimpleChildReference());
		}
		return (ENamedElement) elements.get(type);
	}

	/**
	 * @generated
	 */
	private static IElementType getElementType(String id) {
		return ElementTypeRegistry.getInstance().getType(id);
	}

	/**
	 * @generated
	 */
	public static boolean isKnownElementType(IElementType elementType) {
		if (KNOWN_ELEMENT_TYPES == null) {
			KNOWN_ELEMENT_TYPES = new HashSet<IElementType>();
			KNOWN_ELEMENT_TYPES.add(SimpleMapping_79);
			KNOWN_ELEMENT_TYPES.add(SimpleTopNode_1001);
			KNOWN_ELEMENT_TYPES.add(SimpleLinkMapping_1002);
			KNOWN_ELEMENT_TYPES.add(SimpleLabelNode_2001);
			KNOWN_ELEMENT_TYPES.add(SimpleCompartment_2002);
			KNOWN_ELEMENT_TYPES.add(SimpleSubNode_2003);
			KNOWN_ELEMENT_TYPES.add(SimpleChildReference_2004);
		}
		return KNOWN_ELEMENT_TYPES.contains(elementType);
	}

	/**
	 * @generated
	 */
	public static IElementType getElementType(int visualID) {
		switch (visualID) {
		case SimpleMappingEditPart.VISUAL_ID:
			return SimpleMapping_79;
		case SimpleTopNodeEditPart.VISUAL_ID:
			return SimpleTopNode_1001;
		case SimpleLinkMappingEditPart.VISUAL_ID:
			return SimpleLinkMapping_1002;
		case SimpleLabelNodeEditPart.VISUAL_ID:
			return SimpleLabelNode_2001;
		case SimpleCompartmentEditPart.VISUAL_ID:
			return SimpleCompartment_2002;
		case SimpleSubNodeEditPart.VISUAL_ID:
			return SimpleSubNode_2003;
		case SimpleChildReferenceEditPart.VISUAL_ID:
			return SimpleChildReference_2004;
		}
		return null;
	}

}
