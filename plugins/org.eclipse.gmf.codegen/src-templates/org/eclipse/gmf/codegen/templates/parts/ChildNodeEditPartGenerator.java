package org.eclipse.gmf.codegen.templates.parts;

import org.eclipse.emf.codegen.ecore.genmodel.*;
import org.eclipse.gmf.codegen.gmfgen.*;
import org.eclipse.gmf.codegen.util.*;

public class ChildNodeEditPartGenerator
{
  protected static String nl;
  public static synchronized ChildNodeEditPartGenerator create(String lineSeparator)
  {
    nl = lineSeparator;
    ChildNodeEditPartGenerator result = new ChildNodeEditPartGenerator();
    nl = null;
    return result;
  }

  protected final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "package ";
  protected final String TEXT_2 = ";" + NL;
  protected final String TEXT_3 = NL + "import java.util.Collections;" + NL + "import java.util.List;" + NL + "" + NL + "import org.eclipse.gmf.runtime.common.ui.services.parser.IParser;" + NL + "import org.eclipse.gmf.runtime.diagram.ui.editparts.ListItemEditPart;" + NL + "import org.eclipse.gmf.runtime.diagram.ui.editpolicies.CanonicalEditPolicy;" + NL + "import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;" + NL + "import org.eclipse.gmf.runtime.notation.View;" + NL + "import ";
  protected final String TEXT_4 = ".StructuralFeatureParser;";
  protected final String TEXT_5 = NL + NL + "/**" + NL + " * @generated" + NL + " */" + NL + "public class ";
  protected final String TEXT_6 = " extends ListItemEditPart {" + NL + "" + NL + "\t/**" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_7 = "(View view) {" + NL + "\t\tsuper(view);" + NL + "\t}";
  protected final String TEXT_8 = NL + NL + "\t/**" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic IParser getParser() {" + NL + "\t\tif (parser == null) {" + NL + "\t\t\tparser = new StructuralFeatureParser(";
  protected final String TEXT_9 = ".eINSTANCE.get";
  protected final String TEXT_10 = "().getEStructuralFeature(\"";
  protected final String TEXT_11 = "\"));" + NL + "\t\t}" + NL + "\t\treturn parser;" + NL + "\t}" + NL + "\t" + NL + "\t/**" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprotected void createDefaultEditPolicies() {" + NL + "\t\tsuper.createDefaultEditPolicies();" + NL + "\t\tinstallEditPolicy(EditPolicyRoles.CANONICAL_ROLE, new ";
  protected final String TEXT_12 = "CanonicalEditPolicy());" + NL + "\t}" + NL + "\t" + NL + "\t/**" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprivate class ";
  protected final String TEXT_13 = "CanonicalEditPolicy extends CanonicalEditPolicy {" + NL + "\t" + NL + "\t\t/**" + NL + "\t\t * @generated" + NL + "\t\t */" + NL + "\t\tprotected List getSemanticChildrenList() {" + NL + "\t\t\treturn Collections.EMPTY_LIST;" + NL + "\t\t}" + NL + "\t" + NL + "\t}" + NL + "\t" + NL + "}";
  protected final String TEXT_14 = NL;

  public String generate(Object argument)
  {
    StringBuffer stringBuffer = new StringBuffer();
    
GenChildNode genChildNode = (GenChildNode) argument;
GenDiagram genDiagram = genChildNode.getDiagram();

    stringBuffer.append(TEXT_1);
    stringBuffer.append(genDiagram.getEditPartsPackageName());
    stringBuffer.append(TEXT_2);
    ImportUtil importManager = new ImportUtil(genDiagram.getEditPartsPackageName());
    stringBuffer.append(TEXT_3);
    stringBuffer.append(genDiagram.getProvidersPackageName());
    stringBuffer.append(TEXT_4);
    importManager.markImportLocation(stringBuffer);
    stringBuffer.append(TEXT_5);
    stringBuffer.append(genChildNode.getEditPartClassName());
    stringBuffer.append(TEXT_6);
    stringBuffer.append(genChildNode.getEditPartClassName());
    stringBuffer.append(TEXT_7);
    
GenPackage genPackage = genDiagram.getDomainMetaModel();
String semanticPackageInterfaceName = importManager.getImportedName(genPackage.getQualifiedPackageInterfaceName());
String semanticChildNodeInterfaceName = genChildNode.getDomainMetaClass().getName();
GenNodeLabel label = (GenNodeLabel) genChildNode.getLabels().get(0);

    stringBuffer.append(TEXT_8);
    stringBuffer.append(semanticPackageInterfaceName);
    stringBuffer.append(TEXT_9);
    stringBuffer.append(semanticChildNodeInterfaceName);
    stringBuffer.append(TEXT_10);
    stringBuffer.append(label.getMetaFeature().getName());
    stringBuffer.append(TEXT_11);
    stringBuffer.append(genChildNode.getEditPartClassName());
    stringBuffer.append(TEXT_12);
    stringBuffer.append(genChildNode.getEditPartClassName());
    stringBuffer.append(TEXT_13);
    importManager.emitSortedImports();
    stringBuffer.append(TEXT_14);
    return stringBuffer.toString();
  }
}
