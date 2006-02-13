/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.gmf.mappings;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.AbstractEnumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Link Label Alignment</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * <!-- begin-model-doc -->
 * Constants from {@link org.eclipse.draw2d.ConnectionLocator}
 * <!-- end-model-doc -->
 * @see org.eclipse.gmf.mappings.GMFMapPackage#getLinkLabelAlignment()
 * @model
 * @generated
 */
public final class LinkLabelAlignment extends AbstractEnumerator {
	/**
	 * The '<em><b>MIDDLE</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>MIDDLE</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #MIDDLE_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int MIDDLE = 4;

	/**
	 * The '<em><b>TARGET</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>TARGET</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #TARGET_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int TARGET = 3;

	/**
	 * The '<em><b>SOURCE</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>SOURCE</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #SOURCE_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int SOURCE = 2;

	/**
	 * The '<em><b>MIDDLE</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #MIDDLE
	 * @generated
	 * @ordered
	 */
	public static final LinkLabelAlignment MIDDLE_LITERAL = new LinkLabelAlignment(MIDDLE, "MIDDLE", "MIDDLE");

	/**
	 * The '<em><b>TARGET</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #TARGET
	 * @generated
	 * @ordered
	 */
	public static final LinkLabelAlignment TARGET_LITERAL = new LinkLabelAlignment(TARGET, "TARGET", "TARGET");

	/**
	 * The '<em><b>SOURCE</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SOURCE
	 * @generated
	 * @ordered
	 */
	public static final LinkLabelAlignment SOURCE_LITERAL = new LinkLabelAlignment(SOURCE, "SOURCE", "SOURCE");

	/**
	 * An array of all the '<em><b>Link Label Alignment</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final LinkLabelAlignment[] VALUES_ARRAY =
		new LinkLabelAlignment[] {
			MIDDLE_LITERAL,
			TARGET_LITERAL,
			SOURCE_LITERAL,
		};

	/**
	 * A public read-only list of all the '<em><b>Link Label Alignment</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Link Label Alignment</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static LinkLabelAlignment get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			LinkLabelAlignment result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Link Label Alignment</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static LinkLabelAlignment getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			LinkLabelAlignment result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Link Label Alignment</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static LinkLabelAlignment get(int value) {
		switch (value) {
			case MIDDLE: return MIDDLE_LITERAL;
			case TARGET: return TARGET_LITERAL;
			case SOURCE: return SOURCE_LITERAL;
		}
		return null;	
	}

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private LinkLabelAlignment(int value, String name, String literal) {
		super(value, name, literal);
	}

} //LinkLabelAlignment
