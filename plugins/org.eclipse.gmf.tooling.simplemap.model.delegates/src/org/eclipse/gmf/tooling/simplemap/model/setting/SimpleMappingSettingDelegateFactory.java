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
package org.eclipse.gmf.tooling.simplemap.model.setting;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EStructuralFeature.Internal.SettingDelegate;
import org.eclipse.emf.ecore.EStructuralFeature.Internal.SettingDelegate.Factory;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.BasicSettingDelegate.Stateless;
import org.eclipse.gmf.tooling.simplemap.simplemappings.SimplemappingsPackage;

public class SimpleMappingSettingDelegateFactory implements Factory {

	@Override
	public SettingDelegate createSettingDelegate(EStructuralFeature eStructuralFeature) {

		if (eStructuralFeature.getEContainingClass() == SimplemappingsPackage.Literals.SIMPLE_NODE)
			return new SimpleNodeSettingDelegate(eStructuralFeature);

		if (eStructuralFeature.getEContainingClass() == SimplemappingsPackage.Literals.SIMPLE_NODE_REFERENCE)
			return new SimpleNodeReferenceSettingDelegate(eStructuralFeature);

		if (eStructuralFeature.getEContainingClass() == SimplemappingsPackage.Literals.SIMPLE_CHILD_REFERENCE)
			return new SimpleChildReferenceSettingDelegate(eStructuralFeature);

		if (eStructuralFeature.getEContainingClass() == SimplemappingsPackage.Literals.SIMPLE_COMPARTMENT)
			return new SimpleCompartmentSettingDelegate(eStructuralFeature);

		if (eStructuralFeature.getEContainingClass() == SimplemappingsPackage.Literals.SIMPLE_LINK_MAPPING)
			return new SimpleLinkMappingSettingDelegate(eStructuralFeature);

		if (eStructuralFeature.getEContainingClass() == SimplemappingsPackage.Literals.SIMPLE_CHILD_NODE)
			return new SimpleChildNodeSettingDelegate(eStructuralFeature);

		if (eStructuralFeature.getEContainingClass() == SimplemappingsPackage.Literals.SIMPLE_MAPPING_ELEMENT_WITH_FIGURE)
			return new SimpleMappingElementWithFigureSettingDelegate(eStructuralFeature);

		if (eStructuralFeature.getEContainingClass() == SimplemappingsPackage.Literals.SIMPLE_MAPPING)
			return new SimpleMappingSettingDelegate(eStructuralFeature);

		return new Stateless(eStructuralFeature) {

			@Override
			protected boolean isSet(InternalEObject owner) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			protected Object get(InternalEObject owner, boolean resolve, boolean coreType) {
				// TODO Auto-generated method stub
				return null;
			}
		};
	}

}
