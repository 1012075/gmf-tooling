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
package org.eclipse.gmf.tooling.simplemap.simplemappings.provider;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.emf.edit.provider.ViewerNotification;
import org.eclipse.gmf.tooling.simplemap.model.edit.IItemPropertyDescriptorProvider;
import org.eclipse.gmf.tooling.simplemap.simplemappings.SimpleCompartment;
import org.eclipse.gmf.tooling.simplemap.simplemappings.SimplemappingsPackage;

/**
 * This is the item provider adapter for a {@link org.eclipse.gmf.tooling.simplemap.simplemappings.SimpleCompartment} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class SimpleCompartmentItemProvider extends ItemProviderAdapter implements IEditingDomainItemProvider, IStructuredItemContentProvider, ITreeItemContentProvider, IItemLabelProvider,
		IItemPropertySource {

	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SimpleCompartmentItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/**
	 * This returns the property descriptors for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<IItemPropertyDescriptor> getPropertyDescriptors(Object object) {
		if (itemPropertyDescriptors == null) {
			super.getPropertyDescriptors(object);

			addNodeFigurePropertyDescriptor(object);
			addLabelFigurePropertyDescriptor(object);
			addNamePropertyDescriptor(object);
			addCompartmentPropertyDescriptor(object);
			addCompartmentLabelPropertyDescriptor(object);
			addNeedsTitlePropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Node Figure feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addNodeFigurePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_SimpleMappingElementWithFigure_nodeFigure_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_SimpleMappingElementWithFigure_nodeFigure_feature", "_UI_SimpleMappingElementWithFigure_type"),
				 SimplemappingsPackage.Literals.SIMPLE_MAPPING_ELEMENT_WITH_FIGURE__NODE_FIGURE,
				 false,
				 false,
				 true,
				 null,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Label Figure feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addLabelFigurePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_SimpleMappingElementWithFigure_labelFigure_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_SimpleMappingElementWithFigure_labelFigure_feature", "_UI_SimpleMappingElementWithFigure_type"),
				 SimplemappingsPackage.Literals.SIMPLE_MAPPING_ELEMENT_WITH_FIGURE__LABEL_FIGURE,
				 false,
				 false,
				 true,
				 null,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Name feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addNamePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_SimpleCompartment_name_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_SimpleCompartment_name_feature", "_UI_SimpleCompartment_type"),
				 SimplemappingsPackage.Literals.SIMPLE_COMPARTMENT__NAME,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Compartment feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addCompartmentPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_SimpleCompartment_compartment_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_SimpleCompartment_compartment_feature", "_UI_SimpleCompartment_type"),
				 SimplemappingsPackage.Literals.SIMPLE_COMPARTMENT__COMPARTMENT,
				 false,
				 false,
				 true,
				 null,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Compartment Label feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addCompartmentLabelPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_SimpleCompartment_compartmentLabel_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_SimpleCompartment_compartmentLabel_feature", "_UI_SimpleCompartment_type"),
				 SimplemappingsPackage.Literals.SIMPLE_COMPARTMENT__COMPARTMENT_LABEL,
				 false,
				 false,
				 false,
				 null,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Needs Title feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addNeedsTitlePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_SimpleCompartment_needsTitle_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_SimpleCompartment_needsTitle_feature", "_UI_SimpleCompartment_type"),
				 SimplemappingsPackage.Literals.SIMPLE_COMPARTMENT__NEEDS_TITLE,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This returns SimpleCompartment.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/SimpleCompartment"));
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getText(Object object) {
		String label = ((SimpleCompartment)object).getName();
		return label == null || label.length() == 0 ?
			getString("_UI_SimpleCompartment_type") :
			label;
	}

	/**
	 * This handles model notifications by calling {@link #updateChildren} to update any cached
	 * children and by creating a viewer notification, which it passes to {@link #fireNotifyChanged}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void notifyChanged(Notification notification) {
		updateChildren(notification);

		switch (notification.getFeatureID(SimpleCompartment.class)) {
			case SimplemappingsPackage.SIMPLE_COMPARTMENT__CHILDREN:
			case SimplemappingsPackage.SIMPLE_COMPARTMENT__NAME:
			case SimplemappingsPackage.SIMPLE_COMPARTMENT__NEEDS_TITLE:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
				return;
		}
		super.notifyChanged(notification);
	}

	/**
	 * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s describing the children
	 * that can be created under this object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object) {
		super.collectNewChildDescriptors(newChildDescriptors, object);
	}

	/**
	 * Return the resource locator for this item provider's resources.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ResourceLocator getResourceLocator() {
		return SimplemapEditPlugin.INSTANCE;
	}

	/**
	 * @generated
	 */
	protected ItemPropertyDescriptor createItemPropertyDescriptor(AdapterFactory adapterFactory, ResourceLocator resourceLocator, String displayName, String description, EStructuralFeature feature, boolean isSettable, boolean multiLine, boolean sortChoices, Object staticImage, String category, String [] filterFlags) {
		
		ComposedAdapterFactory composedAdapterFactory = new ComposedAdapterFactory(
				ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
		
		IItemPropertyDescriptorProvider descriptorProvider = (IItemPropertyDescriptorProvider)composedAdapterFactory.adapt(getTarget(), IItemPropertyDescriptorProvider.class);
		
		if(descriptorProvider!=null)
			return descriptorProvider.createItemPropertyDescriptor(
					adapterFactory, 
					resourceLocator, 
					displayName, 
					description, 
					feature, 
					isSettable, 
					multiLine, 
					sortChoices, 
					staticImage, 
					category, 
					filterFlags);

		return super.createItemPropertyDescriptor(
				adapterFactory, 
				resourceLocator, 
				displayName, 
				description, 
				feature, 
				isSettable, 
				multiLine, 
				sortChoices, 
				staticImage, 
				category, 
				filterFlags);
	}
}
