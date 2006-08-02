package org.eclipse.gmf.codegen.templates.lite.commands;

import org.eclipse.gmf.common.codegen.*;

public class CreateNotationalEdgeCommandGenerator
{
  protected static String nl;
  public static synchronized CreateNotationalEdgeCommandGenerator create(String lineSeparator)
  {
    nl = lineSeparator;
    CreateNotationalEdgeCommandGenerator result = new CreateNotationalEdgeCommandGenerator();
    nl = null;
    return result;
  }

  protected final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "";
  protected final String TEXT_2 = NL;
  protected final String TEXT_3 = NL + NL + "/**" + NL + " * @generated" + NL + " */" + NL + "public class CreateNotationalEdgeCommand extends CreateNotationalElementCommand {" + NL + "\t/**" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprivate final ";
  protected final String TEXT_4 = " source;" + NL + "" + NL + "\t/**" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprivate final ";
  protected final String TEXT_5 = " target;" + NL + "" + NL + "\t/**" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic CreateNotationalEdgeCommand(";
  protected final String TEXT_6 = " parent, ";
  protected final String TEXT_7 = " createdEdge, ";
  protected final String TEXT_8 = " source, ";
  protected final String TEXT_9 = " target) {" + NL + "\t\tsuper(parent);" + NL + "\t\tthis.source = source;" + NL + "\t\tthis.target = target;" + NL + "\t\tsetCreatedView(createdEdge);" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic boolean canExecute() {" + NL + "\t\treturn super.canExecute() && getParent() instanceof ";
  protected final String TEXT_10 = " && getCreatedView() instanceof ";
  protected final String TEXT_11 = " && this.source != null && this.target != null;" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic void execute() {" + NL + "\t\t";
  protected final String TEXT_12 = " createdEdge = (";
  protected final String TEXT_13 = ") getCreatedView();" + NL + "\t\t((";
  protected final String TEXT_14 = ")getParent()).insertEdge(createdEdge);" + NL + "\t\tcreatedEdge.setSource(source);" + NL + "\t\tcreatedEdge.setTarget(target);" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic void undo() {" + NL + "\t\t";
  protected final String TEXT_15 = " createdEdge = (";
  protected final String TEXT_16 = ") getCreatedView();" + NL + "\t\t((";
  protected final String TEXT_17 = ")getParent()).removeEdge(createdEdge);" + NL + "\t\tcreatedEdge.setSource(null);" + NL + "\t\tcreatedEdge.setTarget(null);" + NL + "\t}" + NL + "}";
  protected final String TEXT_18 = NL;

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
    stringBuffer.append(importManager.getImportedName("org.eclipse.gmf.runtime.notation.Edge"));
    stringBuffer.append(TEXT_7);
    stringBuffer.append(importManager.getImportedName("org.eclipse.gmf.runtime.notation.View"));
    stringBuffer.append(TEXT_8);
    stringBuffer.append(importManager.getImportedName("org.eclipse.gmf.runtime.notation.View"));
    stringBuffer.append(TEXT_9);
    stringBuffer.append(importManager.getImportedName("org.eclipse.gmf.runtime.notation.Diagram"));
    stringBuffer.append(TEXT_10);
    stringBuffer.append(importManager.getImportedName("org.eclipse.gmf.runtime.notation.Edge"));
    stringBuffer.append(TEXT_11);
    stringBuffer.append(importManager.getImportedName("org.eclipse.gmf.runtime.notation.Edge"));
    stringBuffer.append(TEXT_12);
    stringBuffer.append(importManager.getImportedName("org.eclipse.gmf.runtime.notation.Edge"));
    stringBuffer.append(TEXT_13);
    stringBuffer.append(importManager.getImportedName("org.eclipse.gmf.runtime.notation.Diagram"));
    stringBuffer.append(TEXT_14);
    stringBuffer.append(importManager.getImportedName("org.eclipse.gmf.runtime.notation.Edge"));
    stringBuffer.append(TEXT_15);
    stringBuffer.append(importManager.getImportedName("org.eclipse.gmf.runtime.notation.Edge"));
    stringBuffer.append(TEXT_16);
    stringBuffer.append(importManager.getImportedName("org.eclipse.gmf.runtime.notation.Diagram"));
    stringBuffer.append(TEXT_17);
    importManager.emitSortedImports();
    stringBuffer.append(TEXT_18);
    return stringBuffer.toString();
  }
}
