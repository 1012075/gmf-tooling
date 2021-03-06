/*
 * <copyright>
 *
 * Copyright (c) 2005, 2008 Sven Efftinge and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Sven Efftinge - Initial API and implementation
 *     Artem Tikhomirov - LPG lexer/parser and error reporting
 *
 * </copyright>
 */
package org.eclipse.gmf.internal.xpand.util;

import java.io.IOException;
import java.io.Reader;

import org.eclipse.gmf.internal.xpand.util.ParserException.ErrorLocationInfo;
import org.eclipse.gmf.internal.xpand.xtend.ast.ExtensionFile;
import org.eclipse.gmf.internal.xpand.xtend.ast.XtendResource;
import org.eclipse.gmf.internal.xpand.xtend.parser.XtendLexer;
import org.eclipse.gmf.internal.xpand.xtend.parser.XtendParser;

public class XtendResourceParser {

    public XtendResource parse(final Reader source, final String name) throws IOException, ParserException {
        ExtensionFile tpl = null;
        XtendLexer scanner = null;
        XtendParser parser = null;
        final char[] buffer = new StreamConverter().toCharArray(source);
        try {
            scanner = new XtendLexer(buffer, name);
            parser = new XtendParser(scanner);
            scanner.lexer(parser);
            tpl = parser.parser();
			// FIXME handle errors - override Lexer#reportErrors, collect and 
        } catch (final Exception e) {
			ParserException.ErrorLocationInfo[] errors = extractErrors(scanner, parser);
        	if (errors.length == 0) {
        		throw new IOException("Unexpected exception while parsing");
        	} else {
        		throw new ParserException(name, errors);
        	}
        }
        if (tpl != null) {
            tpl.setFullyQualifiedName(name);
            return tpl;
        }
		ParserException.ErrorLocationInfo[] errors = extractErrors(scanner, parser);
		assert errors.length > 0 : "otherwise, no reason not to get template";
		throw new ParserException(name, errors);
    }

    private static ErrorLocationInfo[] extractErrors(XtendLexer scanner, XtendParser parser) {
		ErrorLocationInfo[] e1 = scanner.getErrors();
		ErrorLocationInfo[] e2 = parser.getErrors();
		ErrorLocationInfo[] res = new ErrorLocationInfo[e1.length + e2.length];
		System.arraycopy(e1, 0, res, 0, e1.length);
		System.arraycopy(e2, 0, res, e1.length, e2.length);
		return res;
	}
}
