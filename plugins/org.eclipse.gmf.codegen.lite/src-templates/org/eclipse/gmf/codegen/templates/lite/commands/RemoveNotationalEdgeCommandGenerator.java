package org.eclipse.gmf.codegen.templates.lite.commands;

import org.eclipse.gmf.common.codegen.*;

public class RemoveNotationalEdgeCommandGenerator
{
  protected static String nl;
  public static synchronized RemoveNotationalEdgeCommandGenerator create(String lineSeparator)
  {
    nl = lineSeparator;
    RemoveNotationalEdgeCommandGenerator result = new RemoveNotationalEdgeCommandGenerator();
    nl = null;
    return result;
  }

  protected final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "";
  protected final String TEXT_2 = NL;
  protected final String TEXT_3 = NL + NL + "/**" + NL + " * @generated" + NL + " */" + NL + "public class RemoveNotationalEdgeCommand extends RemoveNotationalElementCommand {" + NL + "\t/**" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprivate ";
  protected final String TEXT_4 = " source;" + NL + "" + NL + "\t/**" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprivate ";
  protected final String TEXT_5 = " target;" + NL + "" + NL + "\t/**" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic RemoveNotationalEdgeCommand(";
  protected final String TEXT_6 = " parentView, ";
  protected final String TEXT_7 = " childView) {" + NL + "\t\tsuper(parentView, childView);" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic boolean canExecute() {" + NL + "\t\treturn getParent() instanceof ";
  protected final String TEXT_8 = " && getChildView() instanceof ";
  protected final String TEXT_9 = NL + "\t\t\t&& ((";
  protected final String TEXT_10 = ") getParent()).getEdges().contains(getChildView());" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic void execute() {" + NL + "\t\t";
  protected final String TEXT_11 = " edgeToRemove = (";
  protected final String TEXT_12 = ")getChildView();" + NL + "\t\tsource = edgeToRemove.getSource();" + NL + "\t\ttarget = edgeToRemove.getTarget();" + NL + "\t\tedgeToRemove.setSource(null);" + NL + "\t\tedgeToRemove.setTarget(null);" + NL + "\t\t((";
  protected final String TEXT_13 = ")getParent()).removeEdge(edgeToRemove);" + NL + "\t\tsweepElement();" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic void undo() {" + NL + "\t\trestoreElement();" + NL + "\t\t";
  protected final String TEXT_14 = " edge = (";
  protected final String TEXT_15 = ")getChildView();" + NL + "\t\t((";
  protected final String TEXT_16 = ")getParent()).insertEdge(edge);" + NL + "\t\tedge.setSource(source);" + NL + "\t\tedge.setTarget(target);" + NL + "\t}" + NL + "}";
  protected final String TEXT_17 = NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append(TEXT_1);
    
ImportAssistant importManager = (ImportAssistant) ((Object[]) argument)[1];

    stringBuffer.append(TEXT_2);
    
importManager.emitPackageStatement(stringBuffer);
importManager.markImportLocation(stringBuffer);

    stringBuffer.append(TEXT_3);
    stringBuffer.append(importManager.getImportedName("org.eclipse.gmf.runtime.notation.View"));
    stringBuffer.append(TEXT_4);
    stringBuffer.append(importManager.getImportedName("org.eclipse.gmf.runtime.notation.View"));
    stringBuffer.append(TEXT_5);
    stringBuffer.append(importManager.getImportedName("org.eclipse.gmf.runtime.notation.View"));
    stringBuffer.append(TEXT_6);
    stringBuffer.append(importManager.getImportedName("org.eclipse.gmf.runtime.notation.View"));
    stringBuffer.append(TEXT_7);
    stringBuffer.append(importManager.getImportedName("org.eclipse.gmf.runtime.notation.Diagram"));
    stringBuffer.append(TEXT_8);
    stringBuffer.append(importManager.getImportedName("org.eclipse.gmf.runtime.notation.Edge"));
    stringBuffer.append(TEXT_9);
    stringBuffer.append(importManager.getImportedName("org.eclipse.gmf.runtime.notation.Diagram"));
    stringBuffer.append(TEXT_10);
    stringBuffer.append(importManager.getImportedName("org.eclipse.gmf.runtime.notation.Edge"));
    stringBuffer.append(TEXT_11);
    stringBuffer.append(importManager.getImportedName("org.eclipse.gmf.runtime.notation.Edge"));
    stringBuffer.append(TEXT_12);
    stringBuffer.append(importManager.getImportedName("org.eclipse.gmf.runtime.notation.Diagram"));
    stringBuffer.append(TEXT_13);
    stringBuffer.append(importManager.getImportedName("org.eclipse.gmf.runtime.notation.Edge"));
    stringBuffer.append(TEXT_14);
    stringBuffer.append(importManager.getImportedName("org.eclipse.gmf.runtime.notation.Edge"));
    stringBuffer.append(TEXT_15);
    stringBuffer.append(importManager.getImportedName("org.eclipse.gmf.runtime.notation.Diagram"));
    stringBuffer.append(TEXT_16);
    importManager.emitSortedImports();
    stringBuffer.append(TEXT_17);
    return stringBuffer.toString();
  }
}
