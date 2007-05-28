/*
 * Copyright (c) 2006 Borland Software Corporation
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Michael Golubev (Borland) - initial API and implementation
 */
package org.eclipse.gmf.tests.gen;

import junit.framework.TestCase;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.draw2d.Triangle;
import org.eclipse.gmf.gmfgraph.Alignment;
import org.eclipse.gmf.gmfgraph.BasicFont;
import org.eclipse.gmf.gmfgraph.BorderLayout;
import org.eclipse.gmf.gmfgraph.BorderLayoutData;
import org.eclipse.gmf.gmfgraph.ColorConstants;
import org.eclipse.gmf.gmfgraph.ConstantColor;
import org.eclipse.gmf.gmfgraph.CustomFigure;
import org.eclipse.gmf.gmfgraph.Dimension;
import org.eclipse.gmf.gmfgraph.FigureGallery;
import org.eclipse.gmf.gmfgraph.RealFigure;
import org.eclipse.gmf.gmfgraph.FontStyle;
import org.eclipse.gmf.gmfgraph.GMFGraphFactory;
import org.eclipse.gmf.gmfgraph.Label;
import org.eclipse.gmf.gmfgraph.Layout;
import org.eclipse.gmf.gmfgraph.LayoutData;
import org.eclipse.gmf.gmfgraph.RGBColor;
import org.eclipse.gmf.graphdef.codegen.StandaloneGenerator;
import org.eclipse.gmf.internal.graphdef.codegen.GalleryProcessor;

public class RTFigureTest extends TestCase {

	public void testRTGeneration() {
		StandaloneGenerator.Config config = new StandaloneGenerator.ConfigImpl(
				"com.test.plugin." + getName() + ".t" + System.currentTimeMillis(), 
				"com.test.figures");
		FigureGallery fg = GMFGraphFactory.eINSTANCE.createFigureGallery();
		fg.setName("fg");
		fg.getFigures().add(createSampleFigure());
		StandaloneGenerator generator = new StandaloneGenerator(new GalleryProcessor(fg), config, null);
		generator.run();
		IStatus status = generator.getRunStatus();
		assertTrue(status.getMessage(), status.isOK());
	}
	
	private RealFigure createSampleFigure() {
		RealFigure parent = GMFGraphFactory.eINSTANCE.createRectangle();
		parent.setName("Parent");
		parent.setLayout(createLayout());

		RealFigure leftGreenFilled = GMFGraphFactory.eINSTANCE.createRectangle();
		leftGreenFilled.setName("LeftGreen");
		RGBColor green = GMFGraphFactory.eINSTANCE.createRGBColor();
		green.setGreen(255);
		leftGreenFilled.setBackgroundColor(green);
		leftGreenFilled.setLayoutData(createLayoutData(Alignment.BEGINNING_LITERAL, false));

		RealFigure rightRedOutline = GMFGraphFactory.eINSTANCE.createRectangle();
		rightRedOutline.setName("CenterRed");
		RGBColor red = GMFGraphFactory.eINSTANCE.createRGBColor();
		red.setRed(255);
		rightRedOutline.setForegroundColor(green);
		rightRedOutline.setLayoutData(createLayoutData(Alignment.FILL_LITERAL, true));
		
		CustomFigure bottomCustom = GMFGraphFactory.eINSTANCE.createCustomFigure();
		bottomCustom.setName("BottomCustom");
		bottomCustom.setQualifiedClassName(Triangle.class.getName());
		RGBColor blue = GMFGraphFactory.eINSTANCE.createRGBColor();
		blue.setBlue(255);
		bottomCustom.setForegroundColor(blue);

		Label topLabel = GMFGraphFactory.eINSTANCE.createLabel();
		topLabel.setText("aaaaa");
		topLabel.setName("L1");
		BasicFont f1 = GMFGraphFactory.eINSTANCE.createBasicFont();
		f1.setFaceName("Arial");
		f1.setHeight(9);
		f1.setStyle(FontStyle.ITALIC_LITERAL);
		topLabel.setFont(f1);
		ConstantColor c = GMFGraphFactory.eINSTANCE.createConstantColor();
		c.setValue(ColorConstants.CYAN_LITERAL);
		topLabel.setForegroundColor(c);
		topLabel.setLayoutData(createLayoutData(Alignment.CENTER_LITERAL, false));

		parent.getChildren().add(topLabel);
		parent.getChildren().add(leftGreenFilled);
		parent.getChildren().add(rightRedOutline);
		parent.getChildren().add(bottomCustom);
		
		return parent;
	}

	private Layout createLayout() {
		BorderLayout layout = GMFGraphFactory.eINSTANCE.createBorderLayout();
		Dimension spacing = GMFGraphFactory.eINSTANCE.createDimension();
		spacing.setDx(7);
		spacing.setDy(8);
		layout.setSpacing(spacing);
		return layout;
	}

	private LayoutData createLayoutData(Alignment alignment, boolean isVerticalAlignment) {
		BorderLayoutData data = GMFGraphFactory.eINSTANCE.createBorderLayoutData();
		data.setAlignment(alignment);
		data.setVertical(isVerticalAlignment);
		return data;
	}

}
