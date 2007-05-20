package org.eclipse.gmf.codegen.templates.editor;

import org.eclipse.gmf.codegen.gmfgen.*;
import org.eclipse.gmf.common.codegen.*;

public class CreationWizardGenerator
{
  protected static String nl;
  public static synchronized CreationWizardGenerator create(String lineSeparator)
  {
    nl = lineSeparator;
    CreationWizardGenerator result = new CreationWizardGenerator();
    nl = null;
    return result;
  }

  protected final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "/*" + NL + " * ";
  protected final String TEXT_2 = NL + " */";
  protected final String TEXT_3 = NL + NL + "import java.lang.reflect.InvocationTargetException;" + NL + "" + NL + "import org.eclipse.core.runtime.CoreException;" + NL + "import org.eclipse.core.runtime.IProgressMonitor;" + NL + "import org.eclipse.emf.ecore.resource.Resource;" + NL + "import org.eclipse.jface.dialogs.ErrorDialog;" + NL + "import org.eclipse.jface.operation.IRunnableWithProgress;" + NL + "import org.eclipse.jface.viewers.IStructuredSelection;" + NL + "import org.eclipse.jface.wizard.Wizard;" + NL + "import org.eclipse.ui.INewWizard;" + NL + "import org.eclipse.ui.IWorkbench;" + NL + "import org.eclipse.ui.PartInitException;";
  protected final String TEXT_4 = NL + NL + "/**" + NL + " * @generated" + NL + " */" + NL + "public class ";
  protected final String TEXT_5 = " extends Wizard implements INewWizard {" + NL + "" + NL + "\t/**" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprivate IWorkbench workbench;" + NL + "" + NL + "\t/**" + NL + "\t * @generated" + NL + "\t */" + NL + "    protected IStructuredSelection selection;" + NL + "" + NL + "\t/**" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprotected ";
  protected final String TEXT_6 = " diagramModelFilePage;";
  protected final String TEXT_7 = NL + NL + "\t/**" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprotected ";
  protected final String TEXT_8 = " domainModelFilePage;";
  protected final String TEXT_9 = NL + NL + "\t/**" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprotected Resource diagram;" + NL + "" + NL + "\t/**" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprivate boolean openNewlyCreatedDiagramEditor = true;" + NL + "" + NL + "\t/**" + NL + "\t * @generated" + NL + "\t */" + NL + "    public IWorkbench getWorkbench() {" + NL + "        return workbench;" + NL + "    }" + NL + "" + NL + "\t/**" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic IStructuredSelection getSelection() {" + NL + "        return selection;" + NL + "    }" + NL + "" + NL + "\t/**" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic final Resource getDiagram() {" + NL + "\t\treturn diagram;" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic final boolean isOpenNewlyCreatedDiagramEditor() {" + NL + "\t\treturn openNewlyCreatedDiagramEditor;" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic void setOpenNewlyCreatedDiagramEditor(boolean openNewlyCreatedDiagramEditor) {" + NL + "\t\tthis.openNewlyCreatedDiagramEditor = openNewlyCreatedDiagramEditor;" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic void init(IWorkbench workbench, IStructuredSelection selection) {" + NL + "        this.workbench = workbench;" + NL + "        this.selection = selection;" + NL + "\t\tsetWindowTitle(\"New ";
  protected final String TEXT_10 = " Diagram\");";
  protected final String TEXT_11 = NL + "\t\tsetDefaultPageImageDescriptor(";
  protected final String TEXT_12 = ".getBundledImageDescriptor(\"icons/wizban/New";
  protected final String TEXT_13 = "Wizard.gif\")); //$NON-NLS-1$" + NL + "\t\tsetNeedsProgressMonitor(true);" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic void addPages() {" + NL + "\t\tdiagramModelFilePage = new ";
  protected final String TEXT_14 = "(\"DiagramModelFile\", getSelection(), \"";
  protected final String TEXT_15 = "\"); //$NON-NLS-1$ //$NON-NLS-2$" + NL + "\t\tdiagramModelFilePage.setTitle(\"Create ";
  protected final String TEXT_16 = " Diagram\");";
  protected final String TEXT_17 = NL + "\t\tdiagramModelFilePage.setDescription(\"Select file that will contain diagram model.\");";
  protected final String TEXT_18 = NL + "\t\tdiagramModelFilePage.setDescription(\"Select file that will contain diagram and domain models.\");";
  protected final String TEXT_19 = NL + "\t\taddPage(diagramModelFilePage);";
  protected final String TEXT_20 = NL + NL + "\t\tdomainModelFilePage = new ";
  protected final String TEXT_21 = "(\"DomainModelFile\", getSelection(), \"";
  protected final String TEXT_22 = "\"); //$NON-NLS-1$ //$NON-NLS-2$" + NL + "\t\tdomainModelFilePage.setTitle(\"Create ";
  protected final String TEXT_23 = " Diagram\");" + NL + "\t\tdomainModelFilePage.setDescription(\"Select file that will contain domain model.\");" + NL + "\t\taddPage(domainModelFilePage);";
  protected final String TEXT_24 = NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic boolean performFinish() {" + NL + "\t\tIRunnableWithProgress op =";
  protected final String TEXT_25 = NL + "\t\t\t\tnew ";
  protected final String TEXT_26 = "(null) {" + NL + "" + NL + "\t\t\tprotected void execute(IProgressMonitor monitor) throws CoreException, InterruptedException {";
  protected final String TEXT_27 = NL + "\t\t\t\tnew IRunnableWithProgress() {" + NL + "" + NL + "\t\t\tpublic void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {";
  protected final String TEXT_28 = NL + "\t\t\t\tdiagram = ";
  protected final String TEXT_29 = ".createDiagram(diagramModelFilePage.getURI(),";
  protected final String TEXT_30 = NL + "\t\t\t\t\t\tdomainModelFilePage.getURI(),";
  protected final String TEXT_31 = NL + "\t\t\t\t\t\tmonitor);" + NL + "\t\t\t\tif (isOpenNewlyCreatedDiagramEditor() && diagram != null) {" + NL + "\t\t\t\t\ttry {" + NL + "\t\t\t\t\t\t";
  protected final String TEXT_32 = ".openDiagram(diagram);" + NL + "\t\t\t\t\t} catch (PartInitException e) {" + NL + "\t\t\t\t\t\tErrorDialog.openError(getContainer().getShell(), \"Error opening diagram editor\", null, e.getStatus());" + NL + "\t\t\t\t\t}" + NL + "\t\t\t\t}" + NL + "\t\t\t}" + NL + "\t\t};" + NL + "\t\ttry {" + NL + "\t\t\tgetContainer().run(false, true, op);" + NL + "\t\t} catch (InterruptedException e) {" + NL + "\t\t\treturn false;" + NL + "\t\t} catch (InvocationTargetException e) {" + NL + "\t\t\tif (e.getTargetException() instanceof CoreException) {" + NL + "\t\t\t\tErrorDialog.openError(getContainer().getShell(), \"Creation Problems\", null, ((CoreException) e.getTargetException()).getStatus());" + NL + "\t\t\t} else {" + NL + "\t\t\t\t";
  protected final String TEXT_33 = ".getInstance().logError(\"Error creating diagram\", e.getTargetException()); //$NON-NLS-1$" + NL + "\t\t\t}" + NL + "\t\t\treturn false;" + NL + "\t\t}" + NL + "\t\treturn diagram != null;" + NL + "\t}" + NL + "}";
  protected final String TEXT_34 = NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
final GenDiagram genDiagram = (GenDiagram) ((Object[]) argument)[0];
final ImportAssistant importManager = (ImportAssistant) ((Object[]) argument)[1];
final boolean standaloneDomainModel = !genDiagram.getEditorGen().isSameFileForDiagramAndModel() && genDiagram.getDomainDiagramElement() != null;

    
String copyrightText = genDiagram.getEditorGen().getCopyrightText();
if (copyrightText != null && copyrightText.trim().length() > 0) {

    stringBuffer.append(TEXT_1);
    stringBuffer.append(copyrightText.replaceAll("\n", "\n * "));
    stringBuffer.append(TEXT_2);
    }
    importManager.emitPackageStatement(stringBuffer);
    stringBuffer.append(TEXT_3);
    importManager.markImportLocation(stringBuffer);
    stringBuffer.append(TEXT_4);
    stringBuffer.append(importManager.getCompilationUnitName());
    stringBuffer.append(TEXT_5);
    stringBuffer.append(importManager.getImportedName(genDiagram.getCreationWizardPageQualifiedClassName()));
    stringBuffer.append(TEXT_6);
    if (standaloneDomainModel) {
    stringBuffer.append(TEXT_7);
    stringBuffer.append(importManager.getImportedName(genDiagram.getCreationWizardPageQualifiedClassName()));
    stringBuffer.append(TEXT_8);
    }
    stringBuffer.append(TEXT_9);
    stringBuffer.append(genDiagram.getEditorGen().getModelID());
    stringBuffer.append(TEXT_10);
    
final String iconNameStem;
// @see Generator#generateWizardBanner
if (genDiagram.getDomainDiagramElement() != null) {
	iconNameStem = genDiagram.getDomainDiagramElement().getGenPackage().getPrefix();
} else {
	iconNameStem = ""; //$NON-NLS-1$
}
final String pluginClassName = importManager.getImportedName(genDiagram.getEditorGen().getPlugin().getActivatorQualifiedClassName());

    stringBuffer.append(TEXT_11);
    stringBuffer.append(pluginClassName);
    stringBuffer.append(TEXT_12);
    stringBuffer.append(iconNameStem);
    stringBuffer.append(TEXT_13);
    stringBuffer.append(importManager.getImportedName(genDiagram.getCreationWizardPageQualifiedClassName()));
    stringBuffer.append(TEXT_14);
    stringBuffer.append(genDiagram.getEditorGen().getDiagramFileExtension());
    stringBuffer.append(TEXT_15);
    stringBuffer.append(genDiagram.getEditorGen().getModelID());
    stringBuffer.append(TEXT_16);
    if (standaloneDomainModel) {
    stringBuffer.append(TEXT_17);
    } else {
    stringBuffer.append(TEXT_18);
    }
    stringBuffer.append(TEXT_19);
    if (standaloneDomainModel) {
    stringBuffer.append(TEXT_20);
    stringBuffer.append(importManager.getImportedName(genDiagram.getCreationWizardPageQualifiedClassName()));
    stringBuffer.append(TEXT_21);
    stringBuffer.append(genDiagram.getEditorGen().getDomainFileExtension());
    stringBuffer.append(TEXT_22);
    stringBuffer.append(genDiagram.getEditorGen().getModelID());
    stringBuffer.append(TEXT_23);
    }
    stringBuffer.append(TEXT_24);
    if (genDiagram.getEditorGen().getApplication() == null) {
    stringBuffer.append(TEXT_25);
    stringBuffer.append(importManager.getImportedName("org.eclipse.ui.actions.WorkspaceModifyOperation"));
    stringBuffer.append(TEXT_26);
    } else {
    stringBuffer.append(TEXT_27);
    }
    stringBuffer.append(TEXT_28);
    stringBuffer.append(importManager.getImportedName(genDiagram.getDiagramEditorUtilQualifiedClassName()));
    stringBuffer.append(TEXT_29);
    if (standaloneDomainModel) {
    stringBuffer.append(TEXT_30);
    }
    stringBuffer.append(TEXT_31);
    stringBuffer.append(importManager.getImportedName(genDiagram.getDiagramEditorUtilQualifiedClassName()));
    stringBuffer.append(TEXT_32);
    stringBuffer.append(pluginClassName);
    stringBuffer.append(TEXT_33);
    importManager.emitSortedImports();
    stringBuffer.append(TEXT_34);
    return stringBuffer.toString();
  }
}
