package org.eclipse.gmf.graphdef.codegen.templates;

import org.eclipse.gmf.gmfgraph.*;
import org.eclipse.gmf.graphdef.codegen.GraphDefDispatcher;
import java.util.*;

public class FigureChildrenGenerator
{
  protected static String nl;
  public static synchronized FigureChildrenGenerator create(String lineSeparator)
  {
    nl = lineSeparator;
    FigureChildrenGenerator result = new FigureChildrenGenerator();
    nl = null;
    return result;
  }

  protected final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "";
  protected final String TEXT_2 = NL + "\t\t// FIXME instantiate - FigureRef - dispatch to 'instantiate' template?" + NL + "\t\t";
  protected final String TEXT_3 = NL + "\t\t";
  protected final String TEXT_4 = ".add(";
  protected final String TEXT_5 = ");";
  protected final String TEXT_6 = NL;

  public String generate(Object argument)
  {
    StringBuffer stringBuffer = new StringBuffer();
    
Object[] args = (Object[]) argument;
List/*<Figure>*/ figureChildren = (List) args[0];
final GraphDefDispatcher dispatcher = (GraphDefDispatcher) args[1];
String parentFigureVarName = (String) args[2];

    stringBuffer.append(TEXT_1);
    
LinkedList l = new LinkedList();
l.addAll(figureChildren);
final Object marker = new Object();
Stack figureVarNamesStack = new Stack();
int figureCount = 0;
while (!l.isEmpty()) {
	Object _nxt = l.removeFirst();
	if (_nxt == marker) {
		parentFigureVarName = (String) figureVarNamesStack.pop();
		continue;
	}
	final FigureMarker figureMarker = (FigureMarker) _nxt;
	if (figureMarker instanceof FigureRef) {
		throw new IllegalStateException("FIXME: sorry, don't support FigureRef for a while");
	}
	final String figureVarName = "fig" + (figureCount++);
    stringBuffer.append(TEXT_2);
    stringBuffer.append(dispatcher.dispatch("instantiate", dispatcher.create((Figure) figureMarker, figureVarName)));
    stringBuffer.append(TEXT_3);
    stringBuffer.append(parentFigureVarName);
    stringBuffer.append(TEXT_4);
    stringBuffer.append(figureVarName);
    stringBuffer.append(TEXT_5);
    
if (_nxt instanceof Figure && !((Figure) _nxt).getChildren().isEmpty()) {
	l.addFirst(marker);
	l.addAll(0, ((Figure) _nxt).getChildren());
	figureVarNamesStack.push(parentFigureVarName);
	parentFigureVarName = figureVarName; // go on processing children of new parentFigure
} // if
} // while

    stringBuffer.append(TEXT_6);
    return stringBuffer.toString();
  }
}
