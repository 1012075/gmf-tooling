package org.eclipse.gmf.codegen.templates.providers;

import org.eclipse.gmf.codegen.gmfgen.*;
import org.eclipse.gmf.common.codegen.*;

public class EditPartProviderGenerator
{
  protected static String nl;
  public static synchronized EditPartProviderGenerator create(String lineSeparator)
  {
    nl = lineSeparator;
    EditPartProviderGenerator result = new EditPartProviderGenerator();
    nl = null;
    return result;
  }

  protected final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "/*" + NL + " * ";
  protected final String TEXT_2 = NL + " */";
  protected final String TEXT_3 = NL + NL + "import java.lang.ref.WeakReference;" + NL + "" + NL + "import org.eclipse.gef.EditPart;" + NL + "import org.eclipse.gef.EditPartFactory;" + NL + "import org.eclipse.gmf.runtime.common.core.service.IOperation;" + NL + "import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;" + NL + "import org.eclipse.gmf.runtime.diagram.ui.services.editpart.AbstractEditPartProvider;" + NL + "import org.eclipse.gmf.runtime.diagram.ui.services.editpart.CreateGraphicEditPartOperation;" + NL + "import org.eclipse.gmf.runtime.diagram.ui.services.editpart.IEditPartOperation;" + NL + "import org.eclipse.gmf.runtime.notation.View;" + NL + "import ";
  protected final String TEXT_4 = ".";
  protected final String TEXT_5 = ";";
  protected final String TEXT_6 = NL + NL + "/**" + NL + " * @generated" + NL + " */" + NL + "public class ";
  protected final String TEXT_7 = " extends AbstractEditPartProvider {" + NL + "" + NL + "\t/**" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprivate EditPartFactory factory;" + NL + "" + NL + "\t/**" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprivate boolean allowCaching;" + NL + "" + NL + "\t/**" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprivate WeakReference cachedPart;" + NL + "" + NL + "\t/**" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprivate WeakReference cachedView;" + NL + "" + NL + "\t/**" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_8 = "() {" + NL + "\t\tsetFactory(new ";
  protected final String TEXT_9 = "());" + NL + "\t\tsetAllowCaching(true);" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic final EditPartFactory getFactory() {" + NL + "\t\treturn factory;" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprotected void setFactory(EditPartFactory factory) {" + NL + "\t\tthis.factory = factory;" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic final boolean isAllowCaching() {" + NL + "\t\treturn allowCaching;" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprotected synchronized void setAllowCaching(boolean allowCaching) {" + NL + "\t\tthis.allowCaching = allowCaching;" + NL + "\t\tif (!allowCaching) {" + NL + "\t\t\tcachedPart = null;" + NL + "\t\t\tcachedView = null;" + NL + "\t\t}" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprotected IGraphicalEditPart createEditPart(View view) {" + NL + "\t\tEditPart part = factory.createEditPart(null, view);" + NL + "\t\tif (part instanceof IGraphicalEditPart) {" + NL + "\t\t\treturn (IGraphicalEditPart) part;" + NL + "\t\t}" + NL + "\t\treturn null;" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprotected IGraphicalEditPart getCachedPart(View view) {" + NL + "\t\tif (cachedView != null && cachedView.get() == view) {" + NL + "\t\t\treturn (IGraphicalEditPart) cachedPart.get();" + NL + "\t\t}" + NL + "\t\treturn null;" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic synchronized IGraphicalEditPart createGraphicEditPart(View view) {" + NL + "\t\tif (isAllowCaching()) {" + NL + "\t\t\tIGraphicalEditPart part = getCachedPart(view);" + NL + "\t\t\tcachedPart = null;" + NL + "\t\t\tcachedView = null;" + NL + "\t\t\tif (part != null) {" + NL + "\t\t\t\treturn part;" + NL + "\t\t\t}" + NL + "\t\t}" + NL + "\t\treturn createEditPart(view);" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic synchronized boolean provides(IOperation operation) {" + NL + "\t\tif (operation instanceof CreateGraphicEditPartOperation) {" + NL + "\t\t\tView view = ((IEditPartOperation) operation).getView();" + NL + "\t\t\tif (!";
  protected final String TEXT_10 = ".MODEL_ID.equals(";
  protected final String TEXT_11 = ".getModelID(view))) {" + NL + "\t\t\t\treturn false;" + NL + "\t\t\t}" + NL + "\t\t\tif (isAllowCaching() && getCachedPart(view) != null) {" + NL + "\t\t\t\treturn true;" + NL + "\t\t\t}" + NL + "\t\t\tIGraphicalEditPart part = createEditPart(view);" + NL + "\t\t\tif (part != null) {" + NL + "\t\t\t\tif (isAllowCaching()) {" + NL + "\t\t\t\t\tcachedPart = new WeakReference(part);" + NL + "\t\t\t\t\tcachedView = new WeakReference(view);" + NL + "\t\t\t\t}" + NL + "\t\t\t\treturn true;" + NL + "\t\t\t}" + NL + "\t\t}" + NL + "\t\treturn false;" + NL + "\t}" + NL + "}";

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
final GenDiagram genDiagram = (GenDiagram) ((Object[]) argument)[0];
final ImportAssistant importManager = (ImportAssistant) ((Object[]) argument)[1];

    
String copyrightText = genDiagram.getEditorGen().getCopyrightText();
if (copyrightText != null && copyrightText.trim().length() > 0) {

    stringBuffer.append(TEXT_1);
    stringBuffer.append(copyrightText.replaceAll("\n", "\n * "));
    stringBuffer.append(TEXT_2);
    }
    importManager.emitPackageStatement(stringBuffer);
    stringBuffer.append(TEXT_3);
    stringBuffer.append(genDiagram.getEditPartsPackageName());
    stringBuffer.append(TEXT_4);
    stringBuffer.append(genDiagram.getEditPartFactoryClassName());
    stringBuffer.append(TEXT_5);
    importManager.markImportLocation(stringBuffer);
    stringBuffer.append(TEXT_6);
    stringBuffer.append(genDiagram.getEditPartProviderClassName());
    stringBuffer.append(TEXT_7);
    stringBuffer.append(genDiagram.getEditPartProviderClassName());
    stringBuffer.append(TEXT_8);
    stringBuffer.append(genDiagram.getEditPartFactoryClassName());
    stringBuffer.append(TEXT_9);
    stringBuffer.append(importManager.getImportedName(genDiagram.getEditPartQualifiedClassName()));
    stringBuffer.append(TEXT_10);
    stringBuffer.append(importManager.getImportedName(genDiagram.getVisualIDRegistryQualifiedClassName()));
    stringBuffer.append(TEXT_11);
    importManager.emitSortedImports();
    return stringBuffer.toString();
  }
}
