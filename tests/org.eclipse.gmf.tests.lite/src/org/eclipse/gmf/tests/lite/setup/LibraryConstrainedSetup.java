/*
 * Copyright (c) 2006, 2008 Borland Software Corporation
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    bblajer - initial API and implementation
 */
package org.eclipse.gmf.tests.lite.setup;

import java.io.IOException;

import junit.framework.Assert;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.gmf.tests.EPath;
import org.eclipse.gmf.tests.lite.Activator;
import org.eclipse.gmf.tests.lite.gen.LiteGeneratorConfiguration;
import org.eclipse.gmf.tests.setup.DiaGenFileSetup;
import org.eclipse.gmf.tests.setup.DiaGenSource;
import org.eclipse.gmf.tests.setup.DomainModelFileSetup;
import org.eclipse.gmf.tests.setup.DomainModelSource;
import org.eclipse.gmf.tests.setup.GeneratorConfiguration;
import org.eclipse.gmf.tests.setup.MapDefFileSetup;
import org.eclipse.gmf.tests.setup.MapDefSource;
import org.eclipse.gmf.tests.setup.SessionSetup;

public class LibraryConstrainedSetup extends SessionSetup {
	private static String modelURI = "/models/library/library.ecore"; //$NON-NLS-1$
	private static String mapURI = "/models/library/library_constrained.gmfmap"; //$NON-NLS-1$
	private static String genURI = "/models/library/library_constrained.gmfgen"; //$NON-NLS-1$

	protected LibraryConstrainedSetup(GeneratorConfiguration genConfig) {
		super(genConfig);
	}

	public static LibraryConstrainedSetup getInstance() {
		if (factoryClosed) {
			return null;
		}
		return new LibraryConstrainedSetup(new LiteGeneratorConfiguration());
	}

	protected DomainModelSource createDomainModel() {
		DomainModelFileSetup modelSetup = new DomainModelFileSetup() {
			private EPath modelAccess;
			@Override
			public DomainModelSource init(URI sourceURI) {
				final DomainModelSource r = super.init(sourceURI);
				modelAccess = new EPath(getModel().eResource());
				return r;
			}
			public EClass getDiagramElement() {
				return modelAccess.findClass("//Library"); //$NON-NLS-1$
			}
			public NodeData getNodeA() {
				EClass n = modelAccess.findClass("//Writer"); //$NON-NLS-1$
				EReference c = modelAccess.findReference("//Library/writers"); //$NON-NLS-1$
				return new NodeData(n, null, c);
			}
			public NodeData getNodeB() {
				EClass n = modelAccess.findClass("//Book"); //$NON-NLS-1$
				EReference c = modelAccess.findReference("//Library/books"); //$NON-NLS-1$
				return new NodeData(n, null, c);
			}
			public EReference getLinkAsRef() {
				return modelAccess.findReference("//Book/author"); //$NON-NLS-1$
			}
			public LinkData getLinkAsClass() {
				EClass l = modelAccess.findClass("//Opinion"); //$NON-NLS-1$
				EReference t = modelAccess.findReference("//Opinion/book"); //$NON-NLS-1$
				EReference c = modelAccess.findReference("//Writer/opinions"); //$NON-NLS-1$
				return new LinkData(l, t, c);
			}
		};
		try {
			modelSetup.init(Activator.createURI(modelURI));
		} catch (IOException e) {
			e.printStackTrace();
			Assert.fail("Failed to setup the domain model. " + e.getLocalizedMessage()); //$NON-NLS-1$
		}
		return modelSetup;
	}

	protected MapDefSource createMapModel() {
		MapDefFileSetup mapSetup = new MapDefFileSetup() {
		};
		try {
			mapSetup.init(Activator.createURI(mapURI));
		} catch (IOException e) {
			e.printStackTrace();
			Assert.fail("Failed to setup the gmfmap. " + e.getLocalizedMessage()); //$NON-NLS-1$
		}
		return mapSetup;
	}

	protected DiaGenSource createGenModel() {
		DiaGenFileSetup genSetup = new DiaGenFileSetup() {
		};
		try {
			genSetup.init(Activator.createURI(genURI));
		} catch (IOException e) {
			e.printStackTrace();
			Assert.fail("Failed to setup the gmfmap. " + e.getLocalizedMessage()); //$NON-NLS-1$
		}
		return genSetup;
	}
}
