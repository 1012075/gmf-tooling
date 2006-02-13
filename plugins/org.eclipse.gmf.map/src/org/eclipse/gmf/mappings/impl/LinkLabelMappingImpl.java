/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.gmf.mappings.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EcoreUtil;

import org.eclipse.gmf.mappings.GMFMapPackage;
import org.eclipse.gmf.mappings.LinkLabelAlignment;
import org.eclipse.gmf.mappings.LinkLabelMapping;
import org.eclipse.gmf.mappings.LinkMapping;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Link Label Mapping</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.gmf.mappings.impl.LinkLabelMappingImpl#getAlignment <em>Alignment</em>}</li>
 *   <li>{@link org.eclipse.gmf.mappings.impl.LinkLabelMappingImpl#getLinkMapping <em>Link Mapping</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class LinkLabelMappingImpl extends LabelMappingImpl implements LinkLabelMapping {
	/**
	 * The default value of the '{@link #getAlignment() <em>Alignment</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAlignment()
	 * @generated
	 * @ordered
	 */
	protected static final LinkLabelAlignment ALIGNMENT_EDEFAULT = LinkLabelAlignment.MIDDLE_LITERAL;

	/**
	 * The cached value of the '{@link #getAlignment() <em>Alignment</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAlignment()
	 * @generated
	 * @ordered
	 */
	protected LinkLabelAlignment alignment = ALIGNMENT_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected LinkLabelMappingImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return GMFMapPackage.eINSTANCE.getLinkLabelMapping();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LinkLabelAlignment getAlignment() {
		return alignment;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAlignment(LinkLabelAlignment newAlignment) {
		LinkLabelAlignment oldAlignment = alignment;
		alignment = newAlignment == null ? ALIGNMENT_EDEFAULT : newAlignment;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GMFMapPackage.LINK_LABEL_MAPPING__ALIGNMENT, oldAlignment, alignment));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LinkMapping getLinkMapping() {
		if (eContainerFeatureID != GMFMapPackage.LINK_LABEL_MAPPING__LINK_MAPPING) return null;
		return (LinkMapping)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLinkMapping(LinkMapping newLinkMapping) {
		if (newLinkMapping != eInternalContainer() || (eContainerFeatureID != GMFMapPackage.LINK_LABEL_MAPPING__LINK_MAPPING && newLinkMapping != null)) {
			if (EcoreUtil.isAncestor(this, newLinkMapping))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newLinkMapping != null)
				msgs = ((InternalEObject)newLinkMapping).eInverseAdd(this, GMFMapPackage.LINK_MAPPING__LABEL_MAPPINGS, LinkMapping.class, msgs);
			msgs = eBasicSetContainer((InternalEObject)newLinkMapping, GMFMapPackage.LINK_LABEL_MAPPING__LINK_MAPPING, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GMFMapPackage.LINK_LABEL_MAPPING__LINK_MAPPING, newLinkMapping, newLinkMapping));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case GMFMapPackage.LINK_LABEL_MAPPING__LINK_MAPPING:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return eBasicSetContainer(otherEnd, GMFMapPackage.LINK_LABEL_MAPPING__LINK_MAPPING, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case GMFMapPackage.LINK_LABEL_MAPPING__LINK_MAPPING:
				return eBasicSetContainer(null, GMFMapPackage.LINK_LABEL_MAPPING__LINK_MAPPING, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
		switch (eContainerFeatureID) {
			case GMFMapPackage.LINK_LABEL_MAPPING__LINK_MAPPING:
				return eInternalContainer().eInverseRemove(this, GMFMapPackage.LINK_MAPPING__LABEL_MAPPINGS, LinkMapping.class, msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case GMFMapPackage.LINK_LABEL_MAPPING__ALIGNMENT:
				return getAlignment();
			case GMFMapPackage.LINK_LABEL_MAPPING__LINK_MAPPING:
				return getLinkMapping();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case GMFMapPackage.LINK_LABEL_MAPPING__ALIGNMENT:
				setAlignment((LinkLabelAlignment)newValue);
				return;
			case GMFMapPackage.LINK_LABEL_MAPPING__LINK_MAPPING:
				setLinkMapping((LinkMapping)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eUnset(int featureID) {
		switch (featureID) {
			case GMFMapPackage.LINK_LABEL_MAPPING__ALIGNMENT:
				setAlignment(ALIGNMENT_EDEFAULT);
				return;
			case GMFMapPackage.LINK_LABEL_MAPPING__LINK_MAPPING:
				setLinkMapping((LinkMapping)null);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case GMFMapPackage.LINK_LABEL_MAPPING__ALIGNMENT:
				return alignment != ALIGNMENT_EDEFAULT;
			case GMFMapPackage.LINK_LABEL_MAPPING__LINK_MAPPING:
				return getLinkMapping() != null;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (alignment: ");
		result.append(alignment);
		result.append(')');
		return result.toString();
	}

} //LinkLabelMappingImpl
