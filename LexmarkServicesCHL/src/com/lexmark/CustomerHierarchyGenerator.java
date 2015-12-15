package com.lexmark;

import java.util.List;

import org.apache.log4j.Logger;

import com.lexmark.domain.AccountCHLRootNode;
import com.lexmark.domain.CHLNode;
import com.lexmark.domain.MDMAccountNode;
import com.lexmark.domain.SiebelAccountNode;
import com.lexmark.util.StringUtil;

public class CustomerHierarchyGenerator {
	private static Logger logger = Logger.getLogger(CustomerHierarchyGenerator.class);

	private static final String XML_START = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n";
	private static final String TREE_OPEN = "<tree id=\"0\">\n";
	private static final String TREE_CLOSURE = "</tree>";
	private static final String ITEM_OPEN = "<item id=\"";
	private static final String ITEM_OPEN_END = "\">\n";
	private static final String ITEM_CLOSURE = "</item>\n";
	private static final String ITEM_HAS_CHILD = "\" child=\"1";
	private static final String ITEMTEXT_OPEN = "<itemtext><![CDATA[";
	private static final String ITEMTEXT_CLOSURE = "]]></itemtext>\n";
	private static final String HAS_CHILD = "1";
	private static final String NO_CHILD = "0";

	/**
	 * Generate child nodes XML string of the node to be expanded.
	 * @param nodeList child nodes of the node with id as treeId
	 * @param treeId the id of node to be expanded
	 * @return XML string of child nodes
	 */
	public String generateChildNodesXML(List<CHLNode> nodeList, String treeId) {
		StringBuffer nodeTreeXML = new StringBuffer(XML_START);
		nodeTreeXML.append("<tree id=\"" + treeId + "\">\n");
		nodeTreeXML.append(generateReportingHierarchyChildXML(nodeList));
		nodeTreeXML.append(TREE_CLOSURE);
		return nodeTreeXML.toString();
	}

	/**
	 * Generate tree XML string through root node to all nodes
	 * at Account and Siebel level
	 * @param node contains all Accout, Siebel nodes info
	 * @param restrictedCHLNodeId id of pre-restricted node id, it could be
	 * chlNodeId, or mdmId_mdmLevel.
	 * @return tree XML string
	 */
	public String generateTreeXML(AccountCHLRootNode node, String restrictedCHLNodeId) {
		StringBuffer xmlString = new StringBuffer(XML_START);
		xmlString.append(TREE_OPEN);
		// Add item open for root node
		xmlString.append(generateNodeItemOpenXML(node.getNodeId(), node.getName()));
		if (node.getMdmAccountNodeList() != null) {
			if (logger.isDebugEnabled()) {
				logger.debug("Customer Hierarchy contains MDM Account Nodes.");
			}
			for (MDMAccountNode mdmAccountNode : node.getMdmAccountNodeList()) {
				// Add item open for MDMAccountNode
				xmlString.append(generateNodeItemOpenXML(mdmAccountNode.getNodeId(), mdmAccountNode.getName()));
				generateSiebelAccountNodeXML(xmlString, mdmAccountNode.getSiebelAccountNodeList());
				xmlString.append(ITEM_CLOSURE);
			}
		} else if (node.getSiebelAccountNodeList() != null) {
			if (logger.isDebugEnabled()) {
				logger.debug("Customer Hierarchy does not contain MDM Account Nodes.");
			}
			generateSiebelAccountNodeXML(xmlString, node.getSiebelAccountNodeList());
		}
		
		// Add item closure for root node
		xmlString.append(ITEM_CLOSURE);
		xmlString.append(TREE_CLOSURE);
		String xml = xmlString.toString();
		
		if (!StringUtil.isStringEmpty(restrictedCHLNodeId) &&
				xml.contains(restrictedCHLNodeId)) {
			xml = xml.replace("id=\"" + restrictedCHLNodeId + "\"", "id=\"" +
					restrictedCHLNodeId + "\" select=\"1\"");
		}
		return xml;
	}

	private StringBuffer generateReportingHierarchyChildXML(List<CHLNode> nodeList){
		String hasChild = NO_CHILD;
		StringBuffer childNodesXML = new StringBuffer("");
		if(nodeList != null){
			for(int i = 0; i < nodeList.size(); i ++){
				CHLNode node = nodeList.get(i);
				if(node.getHasChild())
					hasChild = HAS_CHILD;
				else 
					hasChild = NO_CHILD;
				childNodesXML.append(" <item child='" + hasChild + "' id='" +
						node.getCHLNodeId() + "'>");
				childNodesXML.append(ITEMTEXT_OPEN + formatNode(
						node.getChlNodeName()) + ITEMTEXT_CLOSURE);
				childNodesXML.append(ITEM_CLOSURE);
			}
		}
		return childNodesXML;
	}
	
	private void generateSiebelAccountNodeXML(StringBuffer xmlString, List<SiebelAccountNode> siebelAccountNodeList) {
		for (SiebelAccountNode siebelAccountNode : siebelAccountNodeList) {
			// Add item open for SiebelAccountNode
			xmlString.append(generateNodeItemOpenXML(siebelAccountNode.getNodeId(), siebelAccountNode.getName()));
			
			// Add item for CHLNode
			if (siebelAccountNode.getChlNodeList() != null && siebelAccountNode.getChlNodeList().size() > 0) {
    			for (CHLNode chlNode : siebelAccountNode.getChlNodeList()) {
    				xmlString.append(generateCHLNodeXML(chlNode));
			}
			}
			xmlString.append(ITEM_CLOSURE);
		}
	}
	
	private String generateCHLNodeXML(CHLNode node) {
		String item = null;
		item = ITEM_OPEN + node.getCHLNodeId() + ITEM_OPEN_END + ITEMTEXT_OPEN +
				node.getChlNodeName() + ITEMTEXT_CLOSURE ;
		if (node.getChildNodeList() != null) {
			for (CHLNode childNode : node.getChildNodeList()) {
				item = item + generateCHLNodeXML(childNode);
			}
		}
		return (item + ITEM_CLOSURE);
	}
	
	private String generateNodeItemOpenXML(String nodeId, String name) {
		return (ITEM_OPEN + nodeId + ITEM_HAS_CHILD + ITEM_OPEN_END + ITEMTEXT_OPEN + name + ITEMTEXT_CLOSURE);
	}

	private String formatNode(String node){
		//if(node.contains("'")){
		if(node != null)
			node = node.replace("'", "&acute;");
		//}
		return node;
	}
}
