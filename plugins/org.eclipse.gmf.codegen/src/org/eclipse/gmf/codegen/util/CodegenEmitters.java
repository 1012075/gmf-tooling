/*
 * Copyright (c) 2005, 2010 Borland Software Corporation and others
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Artem Tikhomirov (Borland) - initial API and implementation
 */
package org.eclipse.gmf.codegen.util;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.codegen.merge.java.JControlModel;
import org.eclipse.emf.codegen.merge.java.JMerger;
import org.eclipse.emf.codegen.util.CodeGenUtil;
import org.eclipse.emf.common.util.URI;
import org.eclipse.gmf.common.UnexpectedBehaviourException;
import org.eclipse.gmf.internal.common.codegen.DefaultTextMerger;
import org.eclipse.gmf.internal.common.codegen.JavaClassEmitter;
import org.eclipse.gmf.internal.common.codegen.TextEmitter;
import org.eclipse.gmf.internal.common.codegen.TextMerger;
import org.eclipse.gmf.internal.common.codegen.XpandClassEmitter;
import org.eclipse.gmf.internal.xpand.ResourceManager;
import org.eclipse.gmf.internal.xpand.util.BundleResourceManager;
import org.osgi.framework.Bundle;

/**
 * @author artem
 */
public class CodegenEmitters {

	protected static final String PATH_SEPARATOR = "::"; //$NON-NLS-1$

	private final ResourceManager myResourceManager;

	private final URL[] myLocations;

	private Map<String, Object> myGlobals;

	public CodegenEmitters(boolean useBaseTemplatesOnly, String templateDirectory, boolean includeDynamicModelTemplates) {
		ArrayList<URL> urls = new ArrayList<URL>(5);
		if (!useBaseTemplatesOnly) {
			urls.add(getDynamicTemplatesURL(templateDirectory));
		}
		if (includeDynamicModelTemplates) {
			urls.add(getTemplatesBundle().getEntry("/templates-dynmodel/")); //$NON-NLS-1$
		}
		urls.add(getTemplatesBundle().getEntry("/templates/")); //$NON-NLS-1$

		urls.add(Platform.getBundle("org.eclipse.gmf.graphdef.codegen").getEntry("/templates/")); //$NON-NLS-1$ //$NON-NLS-2$

		myLocations = urls.toArray(new URL[urls.size()]);
		myResourceManager = new BundleResourceManager(myLocations);
	}

	/* package */void setGlobals(Map<String, Object> globals) {
		myGlobals = globals;
	}

	/**
	 * @return null if no merger is needed
	 */
	public TextMerger createMergeService() {
		URL controlFile = getJMergeControlFile();
		if (controlFile != null) {
			JControlModel controlModel = new JControlModel();
			controlModel.initialize(CodeGenUtil.instantiateFacadeHelper(JMerger.DEFAULT_FACADE_HELPER_CLASS), controlFile.toString());
			if (!controlModel.canMerge()) {
				throw new IllegalStateException("Can not initialize JControlModel");
			}
			return new DefaultTextMerger(controlModel);
		}
		return null;
	}

	private static Bundle getTemplatesBundle() {
		return Platform.getBundle("org.eclipse.gmf.codegen"); //$NON-NLS-1$
	}

	private static URL getDynamicTemplatesURL(String templateDirectory) {
		if (templateDirectory != null) {
			URI templatesURI = templateDirectory.indexOf(":") == -1 ? URI.createPlatformResourceURI(templateDirectory, true) : URI.createURI(templateDirectory); //$NON-NLS-1$
			try {
				return new URL(templatesURI.toString());
			} catch (MalformedURLException e) {
				Platform.getLog(getTemplatesBundle()).log(new Status(IStatus.ERROR, getTemplatesBundle().getSymbolicName(), 0, "Incorrecct dynamic templates location", e)); //$NON-NLS-1$
			}
		}
		return null;
	}

	public URL getJMergeControlFile() {
		return getTemplatesBundle().getEntry("/templates/emf-merge.xml"); //$NON-NLS-1$
	}

	// commands

	public JavaClassEmitter getCreateNodeCommandEmitter() throws UnexpectedBehaviourException {
		return createPrimaryJavaClassEmitter("xpt::diagram::commands::CreateNodeCommand"); //$NON-NLS-1$
	}

	public JavaClassEmitter getCreateLinkCommandEmitter() throws UnexpectedBehaviourException {
		return createJavaClassEmitter("xpt::diagram::commands::CreateLinkCommand", "Main"); //$NON-NLS-1$
	}

	public JavaClassEmitter getReorientLinkCommandEmitter() throws UnexpectedBehaviourException {
		return createPrimaryJavaClassEmitter("xpt::diagram::commands::ReorientLinkCommand"); //$NON-NLS-1$
	}

	public JavaClassEmitter getReorientRefLinkCommandEmitter() throws UnexpectedBehaviourException {
		return createPrimaryJavaClassEmitter("xpt::diagram::commands::ReorientRefLinkCommand"); //$NON-NLS-1$
	}

	public JavaClassEmitter getReorientLinkViewCommandEmitter() throws UnexpectedBehaviourException {
		return createPrimaryJavaClassEmitter("xpt::diagram::commands::ReorientLinkViewCommand"); //$NON-NLS-1$
	}

	public JavaClassEmitter getCreateShortcutDecorationsCommandEmitter() throws UnexpectedBehaviourException {
		return createPrimaryJavaClassEmitter("xpt::diagram::commands::CreateShortcutDecorationsCommand"); //$NON-NLS-1$
	}

	// helpers

	public JavaClassEmitter getBaseEditHelperEmitter() throws UnexpectedBehaviourException {
		return createPrimaryJavaClassEmitter("xpt::diagram::edithelpers::BaseEditHelper"); //$NON-NLS-1$
	}

	public JavaClassEmitter getEditHelperEmitter() throws UnexpectedBehaviourException {
		return createPrimaryJavaClassEmitter("xpt::diagram::edithelpers::EditHelper"); //$NON-NLS-1$
	}

	public JavaClassEmitter getEditHelperAdviceEmitter() throws UnexpectedBehaviourException {
		return createPrimaryJavaClassEmitter("xpt::diagram::edithelpers::EditHelperAdvice"); //$NON-NLS-1$
	}

	// parts

	public JavaClassEmitter getDiagramEditPartEmitter() throws UnexpectedBehaviourException {
		return getMainEmitter("diagram::editparts::DiagramEditPart"); //$NON-NLS-1$
	}

	public JavaClassEmitter getNodeEditPartEmitter() throws UnexpectedBehaviourException {
		return getMainEmitter("diagram::editparts::NodeEditPart"); //$NON-NLS-1$
	}

	public JavaClassEmitter getNodeLabelEditPartEmitter() throws UnexpectedBehaviourException {
		return getMainEmitter("diagram::editparts::NodeLabelEditPart"); //$NON-NLS-1$
	}

	public JavaClassEmitter getExternalNodeLabelEditPartEmitter() throws UnexpectedBehaviourException {
		return getMainEmitter("diagram::editparts::ExternalNodeLabelEditPart"); //$NON-NLS-1$
	}

	public JavaClassEmitter getChildNodeLabelEditPartEmitter() throws UnexpectedBehaviourException {
		return getMainEmitter("diagram::editparts::ChildNodeLabelEditPart"); //$NON-NLS-1$
	}

	public JavaClassEmitter getCompartmentEditPartEmitter() throws UnexpectedBehaviourException {
		return getMainEmitter("diagram::editparts::CompartmentEditPart"); //$NON-NLS-1$
	}

	public JavaClassEmitter getLinkEditPartEmitter() throws UnexpectedBehaviourException {
		return getMainEmitter("diagram::editparts::LinkEditPart"); //$NON-NLS-1$
	}

	public JavaClassEmitter getLinkLabelEditPartEmitter() throws UnexpectedBehaviourException {
		return getMainEmitter("diagram::editparts::LinkLabelEditPart"); //$NON-NLS-1$
	}

	public JavaClassEmitter getEditPartFactoryEmitter() throws UnexpectedBehaviourException {
		return createPrimaryJavaClassEmitter("xpt::diagram::editparts::EditPartFactory"); //$NON-NLS-1$
	}

	// policies

	public JavaClassEmitter getBaseItemSemanticEditPolicyEmitter() {
		return createPrimaryJavaClassEmitter("xpt::diagram::editpolicies::BaseItemSemanticEditPolicy"); //$NON-NLS-1$
	}

	public JavaClassEmitter getOpenDiagramEditPolicyEmitter() throws UnexpectedBehaviourException {
		return createPrimaryJavaClassEmitter("xpt::diagram::editpolicies::OpenDiagram"); //$NON-NLS-1$
	}

	public JavaClassEmitter getDiagramCanonicalEditPolicyEmitter() {
		return createJavaClassEmitter("diagram::editpolicies::DiagramCanonicalEditPolicy","Main"); //$NON-NLS-1$
	}

	public JavaClassEmitter getChildContainerCanonicalEditPolicyEmitter() {
		return createJavaClassEmitter("diagram::editpolicies::ChildContainerCanonicalEditPolicy", "Main"); //$NON-NLS-1$
	}

	public JavaClassEmitter getDiagramItemSemanticEditPolicyEmitter() {
		return createPrimaryJavaClassEmitter("xpt::diagram::editpolicies::DiagramItemSemanticEditPolicy"); //$NON-NLS-1$
	}

	public JavaClassEmitter getCompartmentItemSemanticEditPolicyEmitter() {
		return createPrimaryJavaClassEmitter("xpt::diagram::editpolicies::CompartmentItemSemanticEditPolicy"); //$NON-NLS-1$
	}

	public JavaClassEmitter getGraphicalNodeEditPolicyEmitter() throws UnexpectedBehaviourException {
		return createPrimaryJavaClassEmitter("xpt::diagram::editpolicies::GraphicalNodeEditPolicy"); //$NON-NLS-1$
	}

	public JavaClassEmitter getNodeItemSemanticEditPolicyEmitter() {
		return createPrimaryJavaClassEmitter("xpt::diagram::editpolicies::NodeItemSemanticEditPolicy"); //$NON-NLS-1$
	}

	public JavaClassEmitter getLinkItemSemanticEditPolicyEmitter() {
		return createPrimaryJavaClassEmitter("xpt::diagram::editpolicies::LinkItemSemanticEditPolicy"); //$NON-NLS-1$
	}

	public JavaClassEmitter getTextSelectionEditPolicyEmitter() throws UnexpectedBehaviourException {
		return createJavaClassEmitter("xpt::diagram::editpolicies::TextFeedback", "TextSelectionEditPolicy"); //$NON-NLS-1$
	}

	public JavaClassEmitter getTextNonResizableEditPolicyEmitter() throws UnexpectedBehaviourException {
		return createJavaClassEmitter("xpt::diagram::editpolicies::TextFeedback", "TextNonResizableEditPolicy"); //$NON-NLS-1$
	}

	public JavaClassEmitter getVisualEffectEditPolicyEmitter() {
		return createPrimaryJavaClassEmitter("xpt::diagram::editpolicies::VisualEffectEditPolicy"); //$NON-NLS-1$
	}

	// updater

	public JavaClassEmitter getDiagramUpdaterEmitter() {
		return createPrimaryJavaClassEmitter("xpt::diagram::updater::DiagramUpdater"); //$NON-NLS-1$
	}

	public JavaClassEmitter getUpdateCommandEmitter() {
		return createPrimaryJavaClassEmitter("xpt::diagram::updater::UpdateCommand"); //$NON-NLS-1$
	}

	public JavaClassEmitter getNodeDescriptorEmitter() {
		return createPrimaryJavaClassEmitter("xpt::diagram::updater::NodeDescriptor"); //$NON-NLS-1$
	}

	public JavaClassEmitter getLinkDescriptorEmitter() {
		return createPrimaryJavaClassEmitter("xpt::diagram::updater::LinkDescriptor"); //$NON-NLS-1$
	}

	// parsers

	public JavaClassEmitter getAbstractParserEmitter() throws UnexpectedBehaviourException {
		return createJavaClassEmitter("impl::parsers::AbstractParser", "Main"); //$NON-NLS-1$
	}

	/**
	 * @deprecated we don't want to generate this class anymore, but the changes in i18n may create compilation errors if we don't explicitly clear the contents of generated file. 
	 * @see comments in template impl::parsers::AbstractParser for details 
	 */
	public String getAbstractParserName(Object... input) throws UnexpectedBehaviourException {
		return createPrimaryJavaClassEmitter("impl::parsers::AbstractParser").getQualifiedClassName("deprecatedQualifiedClassName", input); //$NON-NLS-1$
	}

	public JavaClassEmitter getPredefinedParserEmitter() throws UnexpectedBehaviourException {
		return createJavaClassEmitter("parsers::PredefinedParser", "Main"); //$NON-NLS-1$
	}

	public JavaClassEmitter getCustomParserEmitter() throws UnexpectedBehaviourException {
		return createJavaClassEmitter("parsers::CustomParser", "Main"); //$NON-NLS-1$
	}

	public JavaClassEmitter getExpressionLabelParserEmitter() throws UnexpectedBehaviourException {
		return createJavaClassEmitter("parsers::ExpressionLabelParser", "Main"); //$NON-NLS-1$
	}

	// providers

	public JavaClassEmitter getParserProviderEmitter() throws UnexpectedBehaviourException {
		return createJavaClassEmitter("parsers::ParserProvider", "Main"); //$NON-NLS-1$
	}

	public JavaClassEmitter getElementInitializersEmitter() throws UnexpectedBehaviourException {
		return createPrimaryJavaClassEmitter("xpt::providers::ElementInitializers"); //$NON-NLS-1$
	}

	public JavaClassEmitter getElementTypesEmitter() throws UnexpectedBehaviourException {
		return createPrimaryJavaClassEmitter("xpt::providers::ElementTypes"); //$NON-NLS-1$
	}

	public JavaClassEmitter getViewProviderEmitter() {
		return createPrimaryJavaClassEmitter("xpt::providers::ViewProvider"); //$NON-NLS-1$
	}

	public JavaClassEmitter getEditPartProviderEmitter() throws UnexpectedBehaviourException {
		return createPrimaryJavaClassEmitter("xpt::providers::EditPartProvider"); //$NON-NLS-1$
	}

	public JavaClassEmitter getModelingAssistantProviderEmitter() throws UnexpectedBehaviourException {
		return createPrimaryJavaClassEmitter("xpt::providers::ModelingAssistantProvider"); //$NON-NLS-1$
	}

	/**
	 * @since 2.11
	 */
	public JavaClassEmitter getNodeEditPartModelingAssistantProviderEmitter() {
		return createPrimaryJavaClassEmitter("xpt::providers::EditPartModelingAssistantProvider"); //$NON-NLS-1$
	}

	public JavaClassEmitter getIconProviderEmitter() throws UnexpectedBehaviourException {
		return createPrimaryJavaClassEmitter("xpt::providers::IconProvider"); //$NON-NLS-1$
	}

	public JavaClassEmitter getMarkerNavigationProviderEmitter() throws UnexpectedBehaviourException {
		return createPrimaryJavaClassEmitter("xpt::providers::MarkerNavigationProvider"); //$NON-NLS-1$
	}

	public JavaClassEmitter getValidationProviderEmitter() throws UnexpectedBehaviourException {
		return createPrimaryJavaClassEmitter("xpt::providers::ValidationProvider"); //$NON-NLS-1$
	}

	public JavaClassEmitter getValidationDecoratorProviderEmitter() throws UnexpectedBehaviourException {
		return createPrimaryJavaClassEmitter("xpt::providers::ValidationDecoratorProvider"); //$NON-NLS-1$
	}

	public JavaClassEmitter getShortcutsDecoratorProviderEmitter() {
		return createPrimaryJavaClassEmitter("xpt::providers::ShortcutsDecoratorProvider"); //$NON-NLS-1$
	}

	public JavaClassEmitter getMetricProviderEmitter() throws UnexpectedBehaviourException {
		return createPrimaryJavaClassEmitter("xpt::providers::MetricProvider"); //$NON-NLS-1$
	}

	public JavaClassEmitter getAbstractExpressionEmitter() throws UnexpectedBehaviourException {
		return createPrimaryJavaClassEmitter("xpt::expressions::AbstractExpression"); //$NON-NLS-1$
	}

	public JavaClassEmitter getOCLExpressionFactoryEmitter() throws UnexpectedBehaviourException {
		return createPrimaryJavaClassEmitter("xpt::expressions::OCLExpressionFactory"); //$NON-NLS-1$
	}

	public JavaClassEmitter getRegexpExpressionFactoryEmitter() throws UnexpectedBehaviourException {
		return createPrimaryJavaClassEmitter("xpt::expressions::RegexpExpressionFactory"); //$NON-NLS-1$
	}

	// property sheet

	public JavaClassEmitter getPropertySheetLabelProviderEmitter() throws UnexpectedBehaviourException {
		return createJavaClassEmitter("xpt::propsheet::LabelProvider", "Class"); //$NON-NLS-1$
	}

	public JavaClassEmitter getPropertySectionEmitter() throws UnexpectedBehaviourException {
		return createJavaClassEmitter("xpt::propsheet::PropertySection", "Class"); //$NON-NLS-1$
	}

	// editor

	public JavaClassEmitter getValidateActionEmitter() throws UnexpectedBehaviourException {
		return createPrimaryJavaClassEmitter("xpt::editor::ValidateAction"); //$NON-NLS-1$
	}

	public JavaClassEmitter getValidationMarkerEmitter() throws UnexpectedBehaviourException {
		return createPrimaryJavaClassEmitter("xpt::editor::ValidationMarker"); //$NON-NLS-1$
	}

	public JavaClassEmitter getDiagramContentInitializerEmitter() {
		return createPrimaryJavaClassEmitter("xpt::editor::DiagramContentInitializer"); //$NON-NLS-1$
	}

	public JavaClassEmitter getShortcutPropertyTesterEmitter() {
		return createPrimaryJavaClassEmitter("xpt::editor::ShortcutPropertyTester"); //$NON-NLS-1$
	}

	public JavaClassEmitter getShortcutCreationWizardEmitter() throws UnexpectedBehaviourException {
		return createPrimaryJavaClassEmitter("xpt::editor::ShortcutCreationWizard"); //$NON-NLS-1$
	}

	public JavaClassEmitter getModelElementSelectionPageEmitter() throws UnexpectedBehaviourException {
		return createPrimaryJavaClassEmitter("xpt::editor::ModelElementSelectionPage"); //$NON-NLS-1$
	}

	public JavaClassEmitter getNewDiagramFileWizardEmitter() {
		return createPrimaryJavaClassEmitter("xpt::editor::NewDiagramFileWizard"); //$NON-NLS-1$
	}

	public JavaClassEmitter getPaletteEmitter() throws UnexpectedBehaviourException {
		return createJavaClassEmitter("xpt::editor::palette::PaletteFactory", "Factory"); //$NON-NLS-1$
	}

	public JavaClassEmitter getDiagramEditorUtilEmitter() throws UnexpectedBehaviourException {
		return createPrimaryJavaClassEmitter("xpt::editor::DiagramEditorUtil"); //$NON-NLS-1$
	}

	public JavaClassEmitter getVisualIDRegistryEmitter() {
		return createPrimaryJavaClassEmitter("xpt::editor::VisualIDRegistry"); //$NON-NLS-1$
	}

	public JavaClassEmitter getCreationWizardEmitter() throws UnexpectedBehaviourException {
		return createPrimaryJavaClassEmitter("xpt::editor::CreationWizard"); //$NON-NLS-1$
	}

	public JavaClassEmitter getCreationWizardPageEmitter() throws UnexpectedBehaviourException {
		return createPrimaryJavaClassEmitter("xpt::editor::CreationWizardPage"); //$NON-NLS-1$
	}

	public JavaClassEmitter getDeleteElementActionEmitter() {
		return createPrimaryJavaClassEmitter("xpt::editor::DeleteElementAction"); //$NON-NLS-1$
	}

	public JavaClassEmitter getDiagramEditorContextMenuProviderEmitter() {
		return createPrimaryJavaClassEmitter("xpt::editor::DiagramEditorContextMenuProvider"); //$NON-NLS-1$
	}

	public JavaClassEmitter getEditorEmitter() {
		return createPrimaryJavaClassEmitter("xpt::editor::Editor"); //$NON-NLS-1$
	}

	public JavaClassEmitter getElementChooserEmitter() {
		return createPrimaryJavaClassEmitter("xpt::editor::ElementChooser"); //$NON-NLS-1$
	}

	public JavaClassEmitter getDocumentProviderEmitter() {
		return createPrimaryJavaClassEmitter("xpt::editor::DocumentProvider"); //$NON-NLS-1$
	}

	public JavaClassEmitter getActionBarContributorEmitter() throws UnexpectedBehaviourException {
		return createPrimaryJavaClassEmitter("xpt::editor::ActionBarContributor"); //$NON-NLS-1$
	}

	public JavaClassEmitter getMatchingStrategyEmitter() {
		return createPrimaryJavaClassEmitter("xpt::editor::MatchingStrategy"); //$NON-NLS-1$
	}

	public JavaClassEmitter getURIEditorInputTesterEmitter() {
		return createPrimaryJavaClassEmitter("xpt::editor::UriEditorInputTester"); //$NON-NLS-1$
	}

	public JavaClassEmitter getModelAccessFacilityEmitter() {
		return createJavaClassEmitter("Facility", "Main"); //$NON-NLS-1$
	}

	// navigator

	public JavaClassEmitter getNavigatorContentProviderEmitter() {
		return createPrimaryJavaClassEmitter("xpt::navigator::NavigatorContentProvider"); //$NON-NLS-1$
	}

	public JavaClassEmitter getDomainNavigatorContentProviderEmitter() {
		return createPrimaryJavaClassEmitter("xpt::navigator::DomainNavigatorContentProvider"); //$NON-NLS-1$
	}

	public JavaClassEmitter getDomainNavigatorLabelProviderEmitter() {
		return createPrimaryJavaClassEmitter("xpt::navigator::DomainNavigatorLabelProvider"); //$NON-NLS-1$
	}

	public JavaClassEmitter getDomainNavigatorItemEmitter() {
		return createPrimaryJavaClassEmitter("xpt::navigator::DomainNavigatorItem"); //$NON-NLS-1$
	}

	public JavaClassEmitter getNavigatorLabelProviderEmitter() {
		return createPrimaryJavaClassEmitter("xpt::navigator::NavigatorLabelProvider"); //$NON-NLS-1$
	}

	public JavaClassEmitter getNavigatorLinkHelperEmitter() {
		return createPrimaryJavaClassEmitter("xpt::navigator::NavigatorLinkHelper"); //$NON-NLS-1$
	}

	public JavaClassEmitter getNavigatorSorterEmitter() {
		return createPrimaryJavaClassEmitter("xpt::navigator::NavigatorSorter"); //$NON-NLS-1$
	}

	public JavaClassEmitter getNavigatorActionProviderEmitter() {
		return createPrimaryJavaClassEmitter("xpt::navigator::NavigatorActionProvider"); //$NON-NLS-1$
	}

	public JavaClassEmitter getAbstractNavigatorItemEmitter() {
		return createPrimaryJavaClassEmitter("xpt::navigator::AbstractNavigatorItem"); //$NON-NLS-1$
	}

	public JavaClassEmitter getNavigatorGroupEmitter() {
		return createPrimaryJavaClassEmitter("xpt::navigator::NavigatorGroup"); //$NON-NLS-1$
	}

	public JavaClassEmitter getNavigatorItemEmitter() {
		return createPrimaryJavaClassEmitter("xpt::navigator::NavigatorItem"); //$NON-NLS-1$
	}

	// preferences

	public JavaClassEmitter getPreferenceInitializerEmitter() throws UnexpectedBehaviourException {
		return createPrimaryJavaClassEmitter("xpt::diagram::preferences::PreferenceInitializer"); //$NON-NLS-1$
	}

	public JavaClassEmitter getStandardPreferencePageEmitter() throws UnexpectedBehaviourException {
		return createJavaClassEmitter("impl::preferences::StandardPage", "Main"); //$NON-NLS-1$
	}

	public JavaClassEmitter getCustomPreferencePageEmitter() throws UnexpectedBehaviourException {
		return createJavaClassEmitter("impl::preferences::CustomPage", "Main"); //$NON-NLS-1$
	}

	// plugin

	public JavaClassEmitter getActivatorEmitter() throws UnexpectedBehaviourException {
		return createJavaClassEmitter("plugin::Activator", "Main"); //$NON-NLS-1$
	}

	public TextEmitter getBundleManifestEmitter() throws UnexpectedBehaviourException {
		return createTextEmitter("xpt::plugin::manifest", "manifest"); //$NON-NLS-1$
	}

	public TextEmitter getPluginXmlEmitter() throws UnexpectedBehaviourException {
		return createTextEmitter("xpt::plugin::plugin", "plugin"); //$NON-NLS-1$
	}

	public TextEmitter getPluginPropertiesEmitter() throws UnexpectedBehaviourException {
		return createTextEmitter("xpt::plugin::properties", "properties"); //$NON-NLS-1$
	}

	public TextEmitter getBuildPropertiesEmitter() throws UnexpectedBehaviourException {
		return createTextEmitter("xpt::plugin::build", "build"); //$NON-NLS-1$
	}

	public TextEmitter getOptionsFileEmitter() throws UnexpectedBehaviourException {
		return createTextEmitter("xpt::plugin::options", "options"); //$NON-NLS-1$
	}

	public JavaClassEmitter getExternalizeEmitter() {
		return createJavaClassEmitter("xpt::Externalizer","Access"); //$NON-NLS-1$
	}

	public JavaClassEmitter getMessagesEmitter() {
		return createJavaClassEmitter("xpt::Externalizer", "Values"); //$NON-NLS-1$
	}

	// application

	public JavaClassEmitter getApplicationEmitter() throws UnexpectedBehaviourException {
		return createPrimaryJavaClassEmitter("xpt::application::Application"); //$NON-NLS-1$
	}

	public JavaClassEmitter getActionBarAdvisorEmitter() throws UnexpectedBehaviourException {
		return createPrimaryJavaClassEmitter("xpt::application::ActionBarAdvisor"); //$NON-NLS-1$
	}

	public JavaClassEmitter getPerspectiveEmitter() throws UnexpectedBehaviourException {
		return createPrimaryJavaClassEmitter("xpt::application::Perspective"); //$NON-NLS-1$
	}

	public JavaClassEmitter getWorkbenchAdvisorEmitter() throws UnexpectedBehaviourException {
		return createPrimaryJavaClassEmitter("xpt::application::WorkbenchAdvisor"); //$NON-NLS-1$
	}

	public JavaClassEmitter getWorkbenchWindowAdvisorEmitter() throws UnexpectedBehaviourException {
		return createPrimaryJavaClassEmitter("xpt::application::WorkbenchWindowAdvisor"); //$NON-NLS-1$
	}

	public JavaClassEmitter getWizardNewFileCreationPageEmitter() throws UnexpectedBehaviourException {
		return createPrimaryJavaClassEmitter("xpt::application::WizardNewFileCreationPage"); //$NON-NLS-1$
	}

	// actions

	public JavaClassEmitter getCustomActionEmitter() {
		return createJavaClassEmitter("impl::actions::CustomAction", "Main"); //$NON-NLS-1$
	}

	public JavaClassEmitter getPredefinedActionEmitter() {
		return createJavaClassEmitter("impl::actions::PredefinedAction", "Main"); //$NON-NLS-1$
	}

	// util

	protected JavaClassEmitter createJavaClassEmitter(String templateName, String mainMethod) {
		return new XpandClassEmitter(myResourceManager, templateName, mainMethod, myGlobals);
	}

	public TextEmitter getQualifiedClassNameEmitterForPrimaryTemplate(String templateFQN) {
		return createJavaClassEmitter(getTemplateFQNWithoutLastSegment(templateFQN), "qualifiedClassName");
	}
	
	/**
	 * Use when template name equals main method
	 * @param templateName
	 * @return
	 */
	protected JavaClassEmitter createPrimaryJavaClassEmitter(String templateName) {
		String parts[] = templateName.split(PATH_SEPARATOR);
		return createJavaClassEmitter(templateName, parts[parts.length-1]);
	}
	
	public JavaClassEmitter createFullTemplateInvocation(String templateFQN) {
		String[] parts = templateFQN.split(PATH_SEPARATOR);
		return createJavaClassEmitter(getTemplateFQNWithoutLastSegment(templateFQN), parts[parts.length - 1]);
	}
	
	private TextEmitter createTextEmitter(String templateName, String mainMethod) {
		return createJavaClassEmitter(templateName, mainMethod); 
	}
	
	private String getTemplateFQNWithoutLastSegment(String templateFQN) {
		String[] parts = templateFQN.split(PATH_SEPARATOR);
		int methodNamePartLength = parts[parts.length-1].length() + PATH_SEPARATOR.length();
		return templateFQN.substring(0, templateFQN.length() - methodNamePartLength);
	}
	
	/**
	 * Returns "Main" emitter for the specified template file.
	 */
	private JavaClassEmitter getMainEmitter(String templateFilePath) {
		return createJavaClassEmitter(templateFilePath, "Main"); //$NON-NLS-1$
	}

}
