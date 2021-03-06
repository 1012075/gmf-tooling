/*
 * <copyright>
 *
 * Copyright (c) 2005-2006 Sven Efftinge and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Sven Efftinge - Initial API and implementation
 *
 * </copyright>
 */
package org.eclipse.gmf.internal.xpand.expression.ast;

import java.util.Set;

import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.gmf.internal.xpand.BuiltinMetaModel;
import org.eclipse.gmf.internal.xpand.expression.AnalysationIssue;
import org.eclipse.gmf.internal.xpand.expression.ExecutionContext;

/**
 * @author Sven Efftinge
 * @author Arno Haase
 */
public class NullLiteral extends Literal {
    public NullLiteral(final int start, final int end, final int startOffset, final int endOffset, final int line) {
        super(start, end, line, startOffset, endOffset, "null");
    }

    @Override
    public Object evaluateInternal(final ExecutionContext ctx) {
        return null;
    }

    public EClassifier analyze(final ExecutionContext ctx, final Set<AnalysationIssue> issues) {
        return BuiltinMetaModel.VOID;
    }
}
