/*
 * Copyright (c) 2008 Borland Software Corporation
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Artem Tikhomirov (Borland) - initial API and implementation
 */
package org.eclipse.gmf.internal.xpand.ocl;

import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.gmf.internal.xpand.model.ExecutionContext;
import org.eclipse.ocl.cst.CollectionTypeCS;
import org.eclipse.ocl.cst.PathNameCS;
import org.eclipse.ocl.cst.PrimitiveTypeCS;
import org.eclipse.ocl.cst.TypeCS;

/**
 * FIXME Definition.toString gives native java string for TypeHelper 
 * @author artem
 */
public class TypeHelper {

	private final TypeCS typeCS;

	public TypeHelper(TypeCS typeCS) {
		this.typeCS = typeCS;
	}

	public EClassifier getTypeForName(ExecutionContext ctx) {
		return new EmbeddedOCLAnalyzer(ctx.getOCLEnvironment()).typeForName(typeCS);
	}

	public String getName() {
		if (typeCS instanceof PrimitiveTypeCS) {
			return ((PrimitiveTypeCS) typeCS).getValue();
		} else if (typeCS instanceof PathNameCS) {
			return toString((PathNameCS) typeCS);
		} else if (typeCS instanceof CollectionTypeCS) {
			CollectionTypeCS collTypeCS = (CollectionTypeCS) typeCS;
			return collTypeCS.getCollectionTypeIdentifier().getName() + '[' + new TypeHelper(collTypeCS.getTypeCS()).getName() + ']';
		}
		return typeCS.toString();
	}

	public static String toString(PathNameCS pathName) {
        StringBuilder sb = new StringBuilder();
        for (String s : pathName.getSequenceOfNames()) {
        	if (sb.length() > 0) {
        		sb.append("::");
        	}
        	sb.append(s);
        }
        return sb.toString();
	}
}
