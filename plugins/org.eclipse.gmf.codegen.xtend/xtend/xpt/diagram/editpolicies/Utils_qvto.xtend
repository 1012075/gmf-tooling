/**
 * Copyright (c) 2007-2013 Borland Software Corporation && others
 * 
 * All rights reserved. This program && the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, && is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Alexander Shatalin (Borland) - initial API && implementation
 *    Michael Golubev (Borland) - [243151] explicit source/target for links
 *  							- #386838 - migrate to Xtend2
 */
package xpt.diagram.editpolicies

import com.google.inject.Inject
import org.eclipse.gmf.codegen.gmfgen.GenChildNode
import org.eclipse.gmf.codegen.gmfgen.GenDiagram
import org.eclipse.gmf.codegen.gmfgen.GenLink
import org.eclipse.gmf.codegen.gmfgen.GenLinkConstraints
import org.eclipse.gmf.codegen.gmfgen.GenLinkEnd
import org.eclipse.gmf.codegen.gmfgen.GenNode
import org.eclipse.gmf.codegen.gmfgen.TypeLinkModelFacet
import xpt.GenModelUtils_qvto

@com.google.inject.Singleton class Utils_qvto {
	@Inject extension LinkUtils_qvto;
	@Inject extension GenModelUtils_qvto;

	def boolean hasChildrenOrCompartments(GenNode node) {
		return !node.childNodes.empty || !node.compartments.empty
	}

	def String getContainerVariable(TypeLinkModelFacet modelFacet) {
		if(modelFacet.sourceMetaFeature != null) 'container' else 'source'
	}

	def Iterable<GenLinkConstraints> getValidLinkConstraints(GenDiagram diagram) {
		var goodLinks = diagram.links.filter[l|l.creationConstraints != null && l.creationConstraints.isValid()]; 
		return goodLinks.map[l | l.creationConstraints];
	}

	def Iterable<GenLink> getAllPotentialLinks(GenLinkEnd linkEnd) {
		return getAllRelatedLinks(linkEnd).filter[link|isCreationAllowed(link)]
	}

	def Iterable<GenLink> getReroutableTypeLinks(GenLinkEnd linkEnd) {
		return getAllRelatedReroutableLinks(linkEnd).filter[link|isTypeLink(link)]
	}

	def Iterable<GenLink> getReroutableRefLinks(GenLinkEnd linkEnd) {
		return getAllRelatedReroutableLinks(linkEnd).filter[link|isRefLink(link)]
	}

	/**
	 * XXX: [MG] Revisit for xtend: 
	 * XXX [MG]: again, it would be better to use linkEnd.incomingLinks.union(linkEnd.outgoingLinks).toList() 
	 * but it will change the ordering && produce meaningless diff in the generated code
	 */
	def Iterable<GenLink> getAllRelatedLinks(GenLinkEnd linkEnd) {
		return linkEnd.diagram.links.filter[link|canBeSource(link, linkEnd) || canBeTarget(link, linkEnd)]
	}

	def Iterable<GenLink> getAllRelatedReroutableLinks(GenLinkEnd linkEnd) {
		return linkEnd.diagram.links.filter [ link | // 
			(canBeSource(link, linkEnd) && link.sourceReorientingAllowed) ||
				(canBeTarget(link, linkEnd) && link.targetReorientingAllowed)
		]
	}

	def boolean isCreationAllowed(GenLink link) {
		link.modelFacet != null && (link.outgoingCreationAllowed || link.incomingCreationAllowed)
	}

	def boolean createStartLinkCommand(GenLink link, GenLinkEnd linkEnd) {
		createStartOutgoingLinkCommand(link, linkEnd) || createStartIncomingLinkCommand(link, linkEnd)
	}

	def boolean createStartOutgoingLinkCommand(GenLink link, GenLinkEnd linkEnd) {
		return isSelf(link, linkEnd) || (isOutgoing(link, linkEnd) && link.outgoingCreationAllowed)
	}

	def boolean createStartIncomingLinkCommand(GenLink link, GenLinkEnd linkEnd) {
		return isIncoming(link, linkEnd) && link.incomingCreationAllowed
	}

	def boolean createCompleteLinkCommand(GenLink link, GenLinkEnd linkEnd) {
		return createCompleteIncomingLinkCommand(link, linkEnd) || createCompleteOutgoingLinkCommand(link, linkEnd)
	}

	def boolean createCompleteIncomingLinkCommand(GenLink link, GenLinkEnd linkEnd) {
		return isSelf(link, linkEnd) || (isIncoming(link, linkEnd) && link.outgoingCreationAllowed)
	}

	def boolean createCompleteOutgoingLinkCommand(GenLink link, GenLinkEnd linkEnd) {
		return isOutgoing(link, linkEnd) && link.incomingCreationAllowed
	}

	def boolean checkSource(boolean reversedRequest, boolean isCompleteCommand) {
		return !reversedRequest || isCompleteCommand
	}

	def boolean checkTarget(boolean reversedRequest, boolean isCompleteCommand) {
		return reversedRequest || isCompleteCommand
	}

	private def boolean isSelf(GenLink link, GenLinkEnd linkEnd) {
		return canBeSource(link, linkEnd) && canBeTarget(link, linkEnd)
	}

	private def boolean isOutgoing(GenLink link, GenLinkEnd linkEnd) {
		return canBeSource(link, linkEnd) && !canBeTarget(link, linkEnd)
	}

	private def boolean isIncoming(GenLink link, GenLinkEnd linkEnd) {
		return canBeTarget(link, linkEnd) && !canBeSource(link, linkEnd)
	}

	/**
	 * if child's containment feature comes from the node, assume deletion of the parent would delete the child.
	 */
	def boolean isDirectlyOwned(GenChildNode child, GenNode genNode) {
		if(child.modelFacet == null || genNode.modelFacet == null) return false;
		return child.modelFacet.containmentMetaFeature.genClass.isSuperTypeOf(genNode.modelFacet.metaClass)
	}

}
