/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.gmf.mappings;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.gmf.gmfgraph.Connection;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Link Mapping</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Connection element
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.gmf.mappings.LinkMapping#getDiagramLink <em>Diagram Link</em>}</li>
 *   <li>{@link org.eclipse.gmf.mappings.LinkMapping#getLabelEditFeature <em>Label Edit Feature</em>}</li>
 *   <li>{@link org.eclipse.gmf.mappings.LinkMapping#getLabelDisplayFeature <em>Label Display Feature</em>}</li>
 *   <li>{@link org.eclipse.gmf.mappings.LinkMapping#getLabelMappings <em>Label Mappings</em>}</li>
 *   <li>{@link org.eclipse.gmf.mappings.LinkMapping#getSourceMetaFeature <em>Source Meta Feature</em>}</li>
 *   <li>{@link org.eclipse.gmf.mappings.LinkMapping#getLinkMetaFeature <em>Link Meta Feature</em>}</li>
 *   <li>{@link org.eclipse.gmf.mappings.LinkMapping#getCreationConstraints <em>Creation Constraints</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.gmf.mappings.GMFMapPackage#getLinkMapping()
 * @model annotation="http://www.eclipse.org/gmf/2005/constraints ocl='domainMetaElement.oclIsUndefined() implies containmentFeature.oclIsUndefined() and sourceMetaFeature.oclIsUndefined()' description='Link mapping without \'Domain Element\' class does not use \'Containment Feature\' and \'Source Feature\''"
 *        annotation="http://www.eclipse.org/gmf/2005/constraints ocl='not domainMetaElement.oclIsUndefined()  implies containmentFeature.eReferenceType.isSuperTypeOf(domainMetaElement)' description='\'Containment Feature\' must reference the same class or super type of \'Domain Element\' EClass'"
 * @generated
 */
public interface LinkMapping extends MappingEntry, MenuOwner, ToolOwner, AppearanceSteward {
	/**
	 * Returns the value of the '<em><b>Diagram Link</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Diagram Link</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Diagram Link</em>' reference.
	 * @see #setDiagramLink(Connection)
	 * @see org.eclipse.gmf.mappings.GMFMapPackage#getLinkMapping_DiagramLink()
	 * @model required="true"
	 * @generated
	 */
	Connection getDiagramLink();

	/**
	 * Sets the value of the '{@link org.eclipse.gmf.mappings.LinkMapping#getDiagramLink <em>Diagram Link</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Diagram Link</em>' reference.
	 * @see #getDiagramLink()
	 * @generated
	 */
	void setDiagramLink(Connection value);

	/**
	 * Returns the value of the '<em><b>Label Edit Feature</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Label Edit Feature</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Label Edit Feature</em>' reference.
	 * @see #setLabelEditFeature(EAttribute)
	 * @see org.eclipse.gmf.mappings.GMFMapPackage#getLinkMapping_LabelEditFeature()
	 * @model annotation="http://www.eclipse.org/gmf/2005/constraints ocl='not labelEditFeature.oclIsUndefined() implies getDomainContext().oclIsUndefined() or labelEditFeature.eContainingClass.isSuperTypeOf(getDomainContext())' description='\'Label Edit Feature\' must be owned by the domain context EClass or its super type'"
	 * @generated
	 */
	EAttribute getLabelEditFeature();

	/**
	 * Sets the value of the '{@link org.eclipse.gmf.mappings.LinkMapping#getLabelEditFeature <em>Label Edit Feature</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Label Edit Feature</em>' reference.
	 * @see #getLabelEditFeature()
	 * @generated
	 */
	void setLabelEditFeature(EAttribute value);

	/**
	 * Returns the value of the '<em><b>Label Display Feature</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Label Display Feature</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * By default, same as editFeature, once latter is set
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Label Display Feature</em>' reference.
	 * @see #setLabelDisplayFeature(EAttribute)
	 * @see org.eclipse.gmf.mappings.GMFMapPackage#getLinkMapping_LabelDisplayFeature()
	 * @model annotation="http://www.eclipse.org/gmf/2005/constraints ocl='not labelDisplayFeature.oclIsUndefined() implies getDomainContext().oclIsUndefined() or labelDisplayFeature.eContainingClass.isSuperTypeOf(getDomainContext())' description='\'Label Display Feature\' must be owned by the domain context EClass or its super type'"
	 * @generated
	 */
	EAttribute getLabelDisplayFeature();

	/**
	 * Sets the value of the '{@link org.eclipse.gmf.mappings.LinkMapping#getLabelDisplayFeature <em>Label Display Feature</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Label Display Feature</em>' reference.
	 * @see #getLabelDisplayFeature()
	 * @generated
	 */
	void setLabelDisplayFeature(EAttribute value);

	/**
	 * Returns the value of the '<em><b>Label Mappings</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.gmf.mappings.LinkLabelMapping}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.gmf.mappings.LinkLabelMapping#getLinkMapping <em>Link Mapping</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Label Mappings</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Label Mappings</em>' containment reference list.
	 * @see org.eclipse.gmf.mappings.GMFMapPackage#getLinkMapping_LabelMappings()
	 * @see org.eclipse.gmf.mappings.LinkLabelMapping#getLinkMapping
	 * @model type="org.eclipse.gmf.mappings.LinkLabelMapping" opposite="linkMapping" containment="true"
	 * @generated
	 */
	EList getLabelMappings();

	/**
	 * Returns the value of the '<em><b>Source Meta Feature</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Source Meta Feature</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Source Meta Feature</em>' reference.
	 * @see #setSourceMetaFeature(EStructuralFeature)
	 * @see org.eclipse.gmf.mappings.GMFMapPackage#getLinkMapping_SourceMetaFeature()
	 * @model annotation="http://www.eclipse.org/gmf/2005/constraints ocl='not domainMetaElement.oclIsUndefined() implies sourceMetaFeature.oclIsUndefined() or sourceMetaFeature.eContainingClass.isSuperTypeOf(domainMetaElement)' description='\'Source Feature\' must be owned by the link \'Domain Element\' or its super type'"
	 * @generated
	 */
	EStructuralFeature getSourceMetaFeature();

	/**
	 * Sets the value of the '{@link org.eclipse.gmf.mappings.LinkMapping#getSourceMetaFeature <em>Source Meta Feature</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Source Meta Feature</em>' reference.
	 * @see #getSourceMetaFeature()
	 * @generated
	 */
	void setSourceMetaFeature(EStructuralFeature value);

	/**
	 * Returns the value of the '<em><b>Link Meta Feature</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Link Meta Feature</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Link Meta Feature</em>' reference.
	 * @see #setLinkMetaFeature(EStructuralFeature)
	 * @see org.eclipse.gmf.mappings.GMFMapPackage#getLinkMapping_LinkMetaFeature()
	 * @model required="true"
	 *        annotation="http://www.eclipse.org/gmf/2005/constraints ocl='not(domainMetaElement.oclIsUndefined() or linkMetaFeature.oclIsUndefined()) implies linkMetaFeature.eContainingClass.isSuperTypeOf(domainMetaElement)' description='\'Target Feature\' must be owned by the link \'Domain Element\' or its super type'"
	 * @generated
	 */
	EStructuralFeature getLinkMetaFeature();

	/**
	 * Sets the value of the '{@link org.eclipse.gmf.mappings.LinkMapping#getLinkMetaFeature <em>Link Meta Feature</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Link Meta Feature</em>' reference.
	 * @see #getLinkMetaFeature()
	 * @generated
	 */
	void setLinkMetaFeature(EStructuralFeature value);

	/**
	 * Returns the value of the '<em><b>Creation Constraints</b></em>' containment reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.gmf.mappings.LinkConstraints#getLinkMapping <em>Link Mapping</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Specifies additional restrictions on link creation
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Creation Constraints</em>' containment reference.
	 * @see #setCreationConstraints(LinkConstraints)
	 * @see org.eclipse.gmf.mappings.GMFMapPackage#getLinkMapping_CreationConstraints()
	 * @see org.eclipse.gmf.mappings.LinkConstraints#getLinkMapping
	 * @model opposite="linkMapping" containment="true"
	 * @generated
	 */
	LinkConstraints getCreationConstraints();

	/**
	 * Sets the value of the '{@link org.eclipse.gmf.mappings.LinkMapping#getCreationConstraints <em>Creation Constraints</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Creation Constraints</em>' containment reference.
	 * @see #getCreationConstraints()
	 * @generated
	 */
	void setCreationConstraints(LinkConstraints value);

} // LinkMapping
