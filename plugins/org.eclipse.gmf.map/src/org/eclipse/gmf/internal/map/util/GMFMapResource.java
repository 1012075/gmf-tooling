/*
 * Copyright (c) 2007 Borland Software Corporation
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Borland - initial API and implementation
 */
package org.eclipse.gmf.internal.map.util;

import java.util.Arrays;
import java.util.Collection;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.gmf.internal.common.ToolingResourceFactory;
import org.eclipse.gmf.internal.common.migrate.MigrationHelperDelegate;
import org.eclipse.gmf.internal.common.migrate.MigrationResource;
import org.eclipse.gmf.mappings.GMFMapPackage;

public class GMFMapResource extends MigrationResource {
	private Collection<String> myBackwardSupportedURIs;
	
	public static class Factory extends ToolingResourceFactory {
		@Override
		public Resource createResource(URI uri) {
			return new GMFMapResource(uri);
		}
	}

	private GMFMapResource(URI uri) {
		super(uri);
	}

	@Override
	protected MigrationHelperDelegate createDelegate() {
		MigrationDelegate migrationHelper = new MigrationDelegate();
		migrationHelper.init();
		return migrationHelper;
	}

	@Override
	protected Collection<String> getBackwardSupportedURIs() {
		if (myBackwardSupportedURIs == null) {
			myBackwardSupportedURIs = Arrays.asList(new String[] {
					"http://www.eclipse.org/gmf/2005/mappings", //$NON-NLS-1$
			});
		}
		return myBackwardSupportedURIs;
	}

	@Override
	protected String getMetamodelNsURI() {
		return GMFMapPackage.eNS_URI;
	}

}
