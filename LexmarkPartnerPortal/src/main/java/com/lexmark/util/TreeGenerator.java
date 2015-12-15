package com.lexmark.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import com.lexmark.domain.CHLNode;
import com.lexmark.domain.DeviceLocationNode;
import com.lexmark.domain.GenericAddress;
import com.lexmark.constants.LexmarkPPConstants;
import com.lexmark.util.CollectionSorter;
import com.lexmark.util.PropertiesMessageUtil;
import com.lexmark.util.StringUtil;

/**
 * This is a util class to generate XML string for dhtmlx trees.
 * @author roger.lin
 *
 */
public class TreeGenerator {
	
	private static Logger logger = LogManager.getLogger(TreeGenerator.class);

	private static final int LOCATION_LEVEL_ROOT = 0;
	private static final int LOCATION_LEVEL_COUNTRY = 1;
	private static final int LOCATION_LEVEL_PROVINCE = 21;
	private static final int LOCATION_LEVEL_STATE = 22;
	private static final int LOCATION_LEVEL_CITY_IN_PROVINCE = 31;
	private static final int LOCATION_LEVEL_CITY_IN_STATE = 32;
	//CI-6 Chahges : PARTHA
	private static final int LOCATION_LEVEL_CITY_IN_COUNTRY = 33;
	
	private static final String ITEM_OPEN = "<item id=\"";
	private static final String ITEM_OPEN_END = "\">\n";
	private static final String ITEM_CLOSURE = "</item>\n";
	private static final String CHL_ITEMTEXT_OPEN =
            "<itemtext><![CDATA[<a href=\"###\" onClick=\"viewDeviceListByCHL('";
	private static final String ITEMTEXT_CLOSURE = "</a>]]></itemtext>\n";
	private static final String LOCATION_ITEMTEXT_OPEN =
            "<itemtext><![CDATA[<a href=\"###\" onClick=\"viewDeviceListByLocation(";
	private static final String JS_FUNCTION_CLOSURE = "')\">";
	private static final String JS_FUNCTION_COMMA = ",'";
	private static final String JS_FUNCTION_COMMA2 = "','";
	private static final String HAS_CHILD = "1";
	private static final String NO_CHILD = "0";
	private Locale locale;
	
	public TreeGenerator(Locale locale) {
		setLocale(locale);
	}
	@Deprecated
	public String generateReportingHierarchyXML(List<CHLNode> nodeList) {
		CHLNode rootNode = new CHLNode();
		rootNode.setCHLNodeId("0_0");
		rootNode.setChlNodeName(PropertiesMessageUtil.getPropertyMessage(
				LexmarkPPConstants.MESSAGE_BUNDLE_NAME,"tree.label.customerHierarchy",locale));
		StringBuffer chlNodeTreeXML = new StringBuffer("<?xml version=\"1.0\" ?>\n");
		chlNodeTreeXML.append("<tree id=\"0\">\n");
		if (nodeList != null && nodeList.size() > 0) {
			rootNode.setChildNodeList(nodeList);
		}
		
		String chlNodeTreeXMLData = generateReportingHierarchyXMLData(rootNode);
		
		chlNodeTreeXML.append(chlNodeTreeXMLData);
		chlNodeTreeXML.append("</tree>");
		return chlNodeTreeXML.toString();	
	}
	/**
	 * Generate XML string of customer hierarchy based on given CHLNode.
	 * @param node the given CHLNode
	 * @return XML string of customer hierarchy
	 */
	public String generateReportingHierarchyXML(List<CHLNode> nodeList, String treeId) {
		logger.debug("in tree generator");
		StringBuffer nodeTreeXML = new StringBuffer("<?xml version=\"1.0\" ?>\n");
		nodeTreeXML.append("<tree id=\""+treeId+"\">\n");
		nodeTreeXML.append(generateReportingHierarchyChildXML(nodeList));
		nodeTreeXML.append("</tree>");
		//logger.info(nodeTreeXML.toString());
		return nodeTreeXML.toString();
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
				childNodesXML.append(" <item child='"+hasChild+"' id='"+node.getCHLNodeId()+"'>");
				childNodesXML.append(CHL_ITEMTEXT_OPEN + formatNode(node.getCHLNodeId()) + JS_FUNCTION_COMMA2 
						+ formatNode(node.getChlNodeName()) + JS_FUNCTION_CLOSURE + formatNode(node.getChlNodeName())
						+ ITEMTEXT_CLOSURE);
				childNodesXML.append(" </item>\n");
			}
		}
		return childNodesXML;
	}
	
	/**
	 * Generate XML string of device location tree based on given address list.
	 * @param addressList the given list of device's install address
	 * @return XML string of device location tree
	 */
	public String generateDeviceLocationTreeXML(List<GenericAddress> addressList) {
		CollectionSorter sorter = new CollectionSorter();
		ArrayList<String> sortFields = new ArrayList<String>();
		sortFields.add("country");
		sortFields.add("state");
		sortFields.add("province");
		sortFields.add("city");
		List<GenericAddress> sortResult  =sorter.sort(addressList, sortFields);
		DeviceLocationNode deviceLocationRootNode = generateRootDeviceLocationNode(sortResult);
		
		StringBuffer deviceLocationTreeXML = new StringBuffer("<?xml version=\"1.0\" ?>\n");
		deviceLocationTreeXML.append("<tree id=\"0\">\n");
		String deviceLocationTreeXMLData = generateDeviceLocationXMLData(deviceLocationRootNode);
		
		deviceLocationTreeXML.append(deviceLocationTreeXMLData);
		deviceLocationTreeXML.append("</tree>");
		logger.debug(deviceLocationTreeXML);
		return deviceLocationTreeXML.toString();
	}
	
	public Locale getLocale() {
		if (locale == null) {
			locale = Locale.US;
		}
		return locale;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}

	private DeviceLocationNode generateRootDeviceLocationNode(List<GenericAddress> addressList) {
		DeviceLocationNode rootNode = new DeviceLocationNode();
		rootNode.setName(PropertiesMessageUtil.getPropertyMessage(
				LexmarkPPConstants.MESSAGE_BUNDLE_NAME,
				"tree.label.deviceLocation",
				locale));
		rootNode.setLevel(LOCATION_LEVEL_ROOT);
		
		// If address list is null or empty just return the empty rootnode
		if(addressList == null || addressList.isEmpty()) {
			return rootNode;
		}
		
		// Construct rootNode with the values in address, country, province and city.
		DeviceLocationNode tempCountryNode;
		DeviceLocationNode tempProvinceStateNode;
		DeviceLocationNode tempCityNode;
		String provinceState = null;
		String StateOrProvinceparent = null;//Added by sankha for LEX:AIR00065992
		String directCity = null;
		int locationSecondLevel = -1; // could be 21 (Province), or 22 (State), 33 (City, Country) CI-6 Changes
		int locationThirdLevel = -1; // could be 31 (City in Province), or 32 (City in State)
		for (GenericAddress address : addressList) {
			
			//logger.info("=====>   : "+address);
			
			// Handle country
			if (StringUtil.isStringEmpty(address.getCountry())) {
				continue;
			}
			tempCountryNode = getMatchedChildNode(rootNode, address.getCountry());
			//logger.info("generateRootDeviceLocationNode tempCountryNode="+tempCountryNode.getName());
			
			//logger.info("Country Node 1 : "+tempCountryNode);
			
			// create child node list, and add the country to the country list of root node
			if (tempCountryNode == null) {
				
				//logger.info("Country Node  : "+tempCountryNode);
				
				if (rootNode.getChildLocation() == null) {
					rootNode.setChildLocation(new ArrayList<DeviceLocationNode>());	
				}
				tempCountryNode = new DeviceLocationNode();
				tempCountryNode.setName(address.getCountry());
				tempCountryNode.setLevel(LOCATION_LEVEL_COUNTRY);
				rootNode.getChildLocation().add(tempCountryNode);
			}
			
			// Handle province or state
			if (address.getProvince() != null && address.getProvince().length() > 0) {
				//logger.info("********** PROVINCE ***********");
				provinceState = address.getProvince();
				StateOrProvinceparent = address.getProvince();//Added by sankha for LEX:AIR00065992
				locationSecondLevel = LOCATION_LEVEL_PROVINCE;
				locationThirdLevel = LOCATION_LEVEL_CITY_IN_PROVINCE;
			} 
			else if (address.getState() != null && address.getState().length() > 0) {
				//logger.info("********** STATE ***********");
				provinceState = address.getState();
				StateOrProvinceparent = address.getState();//Added by sankha for LEX:AIR00065992
				locationSecondLevel = LOCATION_LEVEL_STATE;
				locationThirdLevel = LOCATION_LEVEL_CITY_IN_STATE;
			}else{
				//logger.info("********** CITY ***********");
				provinceState = null;
				directCity = address.getCity();
				locationSecondLevel = LOCATION_LEVEL_CITY_IN_COUNTRY;
			}
			
			//PARTHA
			/*if (StringUtil.isStringEmpty(provinceState)) {
				continue;
			}*/
			if(provinceState != null){
				//logger.info("********** PROVINCE/STATE ***********");
					tempProvinceStateNode = getMatchedChildNode(tempCountryNode, provinceState);
					/*Add by MPS*/
					if(tempProvinceStateNode!=null)
					{
						tempProvinceStateNode.setParentname(address.getCountry());
					}
					/*Ends*/
					// create child node list, and add the province/state to
					// the province list of the matched country node
					if (tempProvinceStateNode == null) {
						if (tempCountryNode.getChildLocation() == null) {
							tempCountryNode.setChildLocation(new ArrayList<DeviceLocationNode>());
						}
						tempProvinceStateNode = new DeviceLocationNode();
						tempProvinceStateNode.setName(provinceState);
						tempProvinceStateNode.setLevel(locationSecondLevel);
						/*Add by MPS*/
						tempProvinceStateNode.setParentname(address.getCountry());
						/*Ends*/
						tempCountryNode.getChildLocation().add(tempProvinceStateNode);
					}
				// Handle city
				if (StringUtil.isStringEmpty(address.getCity())) {
					continue;
				}
				tempCityNode = getMatchedChildNode(tempProvinceStateNode, address.getCity());
				// create child node list, and add the province/state to
				// the province list of the matched country node
				if (tempCityNode == null) {
					if (tempProvinceStateNode.getChildLocation() == null) {
						tempProvinceStateNode.setChildLocation(new ArrayList<DeviceLocationNode>());
					}
					tempCityNode = new DeviceLocationNode();
					tempCityNode.setName(address.getCity());
					tempCityNode.setLevel(locationThirdLevel);
					/*Added by sankha for LEX:AIR00065992*/
					if(null != StateOrProvinceparent && StateOrProvinceparent != ""){
						tempCityNode.setParentname(StateOrProvinceparent);
					}
					else{
						tempCityNode.setParentname("default");
					}
					/*End*/
					tempProvinceStateNode.getChildLocation().add(tempCityNode);
				}
			}
			// City direct with in the country CHANGES FOR CI-6 Partha
			else{
				//logger.info("********** CITY *********** "+locationSecondLevel);
				tempCityNode = new DeviceLocationNode();
				tempCityNode.setName(address.getCity());
				tempCityNode.setLevel(locationSecondLevel);
				
				if (tempCountryNode.getChildLocation() == null) {
					tempCountryNode.setChildLocation(new ArrayList<DeviceLocationNode>());
				}
				tempCountryNode.getChildLocation().add(tempCityNode);
				//logger.info("---------------- MY COUNTRY : "+address.getCountry()+" <==> "+address.getCity());
			}
			//CHANGES FOR CI-6 Partha End
		}
		return rootNode;
	}
	@Deprecated
	private String generateReportingHierarchyXMLData(CHLNode node) {
		String item = null;
		item = ITEM_OPEN + node.getCHLNodeId() + ITEM_OPEN_END + CHL_ITEMTEXT_OPEN +
		        node.getCHLNodeId() + JS_FUNCTION_COMMA2 + formatNode(node.getChlNodeName()) +
                JS_FUNCTION_CLOSURE + node.getChlNodeName() + ITEMTEXT_CLOSURE ;
		if (node.getChildNodeList() != null) {
			for (CHLNode childNode : node.getChildNodeList()) {
				item = item + generateReportingHierarchyXMLData(childNode);
			}
		}
		return (item + ITEM_CLOSURE);
	}
	private String formatNode(String node){
		//if(node.contains("'")){
		if(node != null)
			node = node.replace("'", "&acute;");
		//}
		return node;
	}
	private DeviceLocationNode getMatchedChildNode(DeviceLocationNode node, String addressValue) {
		//logger.info("GetMatchedChildNode addressValue="+addressValue);
		if (node.getChildLocation() == null || node.getChildLocation().size() == 0) {
			return null;
		}
		
		// Loop childLocation of the given node, to find the matched child node.
		for (DeviceLocationNode childNode : node.getChildLocation()) {
			if (childNode.getName().equals(addressValue)) {
				return childNode;
			}
		}
		
		return null;
	}

		private String generateDeviceLocationXMLData(DeviceLocationNode node) {
		String item = null;
		
		item = ITEM_OPEN + node.getName().trim()+ formatNode(node.getParentname()) + ITEM_OPEN_END +
		        LOCATION_ITEMTEXT_OPEN + 
		        node.getLevel() + JS_FUNCTION_COMMA + formatNode(node.getName().trim()) + JS_FUNCTION_COMMA2 + 
				formatNode(node.getParentname()) + JS_FUNCTION_CLOSURE +
		        formatNode(node.getName().trim()) + ITEMTEXT_CLOSURE;
		//Added by sankha for LEX:AIR00065992
		/*
		item = ITEM_OPEN + node.getName() + ITEM_OPEN_END +
		        LOCATION_ITEMTEXT_OPEN + 
		        node.getLevel() + JS_FUNCTION_COMMA + formatNode(node.getName()) + JS_FUNCTION_CLOSURE +
		        formatNode(node.getName()) + ITEMTEXT_CLOSURE; */
		String tempChildNode="";
		String childNodeName=null;
		
		if (node.getChildLocation() != null) {
			for (DeviceLocationNode childNode : node.getChildLocation()) {
				childNodeName = childNode.getName().trim();				
				if(tempChildNode!=null && !tempChildNode.equalsIgnoreCase(childNodeName)){
					item = item + generateDeviceLocationXMLData(childNode);
				}
				tempChildNode = childNodeName;
			}
		}
		return (item + ITEM_CLOSURE);
	}
}
