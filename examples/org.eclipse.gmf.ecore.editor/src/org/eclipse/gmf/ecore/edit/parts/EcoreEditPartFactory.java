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
package org.eclipse.gmf.ecore.edit.parts;

import org.eclipse.draw2d.FigureUtilities;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;
import org.eclipse.gef.tools.CellEditorLocator;
import org.eclipse.gmf.ecore.part.EcoreVisualIDRegistry;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ITextAwareEditPart;
import org.eclipse.gmf.runtime.draw2d.ui.figures.WrapLabel;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;

/**
 * @generated
 */
public class EcoreEditPartFactory implements EditPartFactory {

	/**
	 * @generated
	 */
	public EditPart createEditPart(EditPart context, Object model) {
		if (model instanceof View) {
			View view = (View) model;
			switch (EcoreVisualIDRegistry.getVisualID(view)) {

			case EPackageEditPart.VISUAL_ID:
				return new EPackageEditPart(view);

			case EClassEditPart.VISUAL_ID:
				return new EClassEditPart(view);

			case EClassNameEditPart.VISUAL_ID:
				return new EClassNameEditPart(view);

			case EPackage2EditPart.VISUAL_ID:
				return new EPackage2EditPart(view);

			case EPackageNameEditPart.VISUAL_ID:
				return new EPackageNameEditPart(view);

			case EAnnotation2EditPart.VISUAL_ID:
				return new EAnnotation2EditPart(view);

			case EAnnotationSourceEditPart.VISUAL_ID:
				return new EAnnotationSourceEditPart(view);

			case EDataType2EditPart.VISUAL_ID:
				return new EDataType2EditPart(view);

			case EDataTypeNameEditPart.VISUAL_ID:
				return new EDataTypeNameEditPart(view);

			case EEnum2EditPart.VISUAL_ID:
				return new EEnum2EditPart(view);

			case EEnumNameEditPart.VISUAL_ID:
				return new EEnumNameEditPart(view);

			case EAttributeEditPart.VISUAL_ID:
				return new EAttributeEditPart(view);

			case EOperationEditPart.VISUAL_ID:
				return new EOperationEditPart(view);

			case EAnnotationEditPart.VISUAL_ID:
				return new EAnnotationEditPart(view);

			case EClass2EditPart.VISUAL_ID:
				return new EClass2EditPart(view);

			case EPackage3EditPart.VISUAL_ID:
				return new EPackage3EditPart(view);

			case EDataTypeEditPart.VISUAL_ID:
				return new EDataTypeEditPart(view);

			case EEnumEditPart.VISUAL_ID:
				return new EEnumEditPart(view);

			case EStringToStringMapEntryEditPart.VISUAL_ID:
				return new EStringToStringMapEntryEditPart(view);

			case EEnumLiteralEditPart.VISUAL_ID:
				return new EEnumLiteralEditPart(view);

			case EClassAttributesEditPart.VISUAL_ID:
				return new EClassAttributesEditPart(view);

			case EClassOperationsEditPart.VISUAL_ID:
				return new EClassOperationsEditPart(view);

			case EClassClassAnnotationsEditPart.VISUAL_ID:
				return new EClassClassAnnotationsEditPart(view);

			case EPackageClassesEditPart.VISUAL_ID:
				return new EPackageClassesEditPart(view);

			case EPackagePackagesEditPart.VISUAL_ID:
				return new EPackagePackagesEditPart(view);

			case EPackageDataTypesEditPart.VISUAL_ID:
				return new EPackageDataTypesEditPart(view);

			case EPackageEnumsEditPart.VISUAL_ID:
				return new EPackageEnumsEditPart(view);

			case EPackagePackageAnnotationsEditPart.VISUAL_ID:
				return new EPackagePackageAnnotationsEditPart(view);

			case EAnnotationDetailsEditPart.VISUAL_ID:
				return new EAnnotationDetailsEditPart(view);

			case EDataTypeDataTypeAnnotationsEditPart.VISUAL_ID:
				return new EDataTypeDataTypeAnnotationsEditPart(view);

			case EEnumLiteralsEditPart.VISUAL_ID:
				return new EEnumLiteralsEditPart(view);

			case EEnumEnumAnnotationsEditPart.VISUAL_ID:
				return new EEnumEnumAnnotationsEditPart(view);

			case EAnnotationReferencesEditPart.VISUAL_ID:
				return new EAnnotationReferencesEditPart(view);

			case EReferenceEditPart.VISUAL_ID:
				return new EReferenceEditPart(view);

			case EReferenceNameEditPart.VISUAL_ID:
				return new EReferenceNameEditPart(view);

			case EReferenceLowerBoundUpperBoundEditPart.VISUAL_ID:
				return new EReferenceLowerBoundUpperBoundEditPart(view);

			case EReference2EditPart.VISUAL_ID:
				return new EReference2EditPart(view);

			case EReferenceName2EditPart.VISUAL_ID:
				return new EReferenceName2EditPart(view);

			case EReferenceLowerBoundUpperBound2EditPart.VISUAL_ID:
				return new EReferenceLowerBoundUpperBound2EditPart(view);

			case EClassESuperTypesEditPart.VISUAL_ID:
				return new EClassESuperTypesEditPart(view);
			}
		}
		return createUnrecognizedEditPart(context, model);
	}

	/**
	 * @generated
	 */
	private EditPart createUnrecognizedEditPart(EditPart context, Object model) {
		// Handle creation of unrecognized child node EditParts here
		return null;
	}

	/**
	 * @generated
	 */
	public static CellEditorLocator getTextCellEditorLocator(ITextAwareEditPart source) {
		if (source.getFigure() instanceof WrapLabel)
			return new TextCellEditorLocator((WrapLabel) source.getFigure());
		else {
			return new LabelCellEditorLocator((Label) source.getFigure());
		}
	}

	/**
	 * @generated
	 */
	static private class TextCellEditorLocator implements CellEditorLocator {

		/**
		 * @generated
		 */
		private WrapLabel wrapLabel;

		/**
		 * @generated
		 */
		public TextCellEditorLocator(WrapLabel wrapLabel) {
			this.wrapLabel = wrapLabel;
		}

		/**
		 * @generated
		 */
		public WrapLabel getWrapLabel() {
			return wrapLabel;
		}

		/**
		 * @generated
		 */
		public void relocate(CellEditor celleditor) {
			Text text = (Text) celleditor.getControl();
			Rectangle rect = getWrapLabel().getTextBounds().getCopy();
			getWrapLabel().translateToAbsolute(rect);
			if (getWrapLabel().isTextWrapped() && getWrapLabel().getText().length() > 0) {
				rect.setSize(new Dimension(text.computeSize(rect.width, SWT.DEFAULT)));
			} else {
				int avr = FigureUtilities.getFontMetrics(text.getFont()).getAverageCharWidth();
				rect.setSize(new Dimension(text.computeSize(SWT.DEFAULT, SWT.DEFAULT)).expand(avr * 2, 0));
			}
			if (!rect.equals(new Rectangle(text.getBounds()))) {
				text.setBounds(rect.x, rect.y, rect.width, rect.height);
			}
		}

	}

	/**
	 * @generated
	 */
	private static class LabelCellEditorLocator implements CellEditorLocator {

		/**
		 * @generated
		 */
		private Label label;

		/**
		 * @generated
		 */
		public LabelCellEditorLocator(Label label) {
			this.label = label;
		}

		/**
		 * @generated
		 */
		public Label getLabel() {
			return label;
		}

		/**
		 * @generated
		 */
		public void relocate(CellEditor celleditor) {
			Text text = (Text) celleditor.getControl();
			Rectangle rect = getLabel().getTextBounds().getCopy();
			getLabel().translateToAbsolute(rect);
			int avr = FigureUtilities.getFontMetrics(text.getFont()).getAverageCharWidth();
			rect.setSize(new Dimension(text.computeSize(SWT.DEFAULT, SWT.DEFAULT)).expand(avr * 2, 0));
			if (!rect.equals(new Rectangle(text.getBounds()))) {
				text.setBounds(rect.x, rect.y, rect.width, rect.height);
			}
		}
	}
}
