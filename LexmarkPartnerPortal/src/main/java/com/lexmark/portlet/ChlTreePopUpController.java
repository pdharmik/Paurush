package com.lexmark.portlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.lexmark.constants.LexmarkPPConstants;
import com.lexmark.contract.LocationReportingHierarchyContract;
import com.lexmark.domain.CHLNode;
import com.lexmark.framework.logging.LEXLogger;
import com.lexmark.result.AssetReportingHierarchyResult;
import com.lexmark.service.api.DeviceService;
import com.lexmark.util.ChangeMgmtConstant;
import com.lexmark.util.ContractFactory;
import com.lexmark.util.ObjectDebugUtil;
import com.lexmark.util.PerformanceConstant;
import com.lexmark.util.PerformanceUtil;
import com.lexmark.util.PropertiesMessageUtil;
import com.lexmark.util.TreeGenerator;

/**
 * @author Wipro 
 * @version 2.1 
 *
 */

@Controller
@RequestMapping("VIEW")
public class ChlTreePopUpController {

	
	@Autowired
	private DeviceService deviceService;
	
	
	
	private static LEXLogger LOGGER = LEXLogger.getLEXLogger(ChlTreePopUpController.class);
	private static Logger perfLogger = LogManager.getLogger("performance");
	private static String CLASS_NAME = "ChlTreePopUpController.java" ;
	private static LEXLogger LEXLOGGER = LEXLogger.getLEXLogger(ChlTreePopUpController.class);
	
	private static final String METH_SHOWCHLTREEPOPUP ="showCHLTreePopUp";
	private static final String METH_RETRIEVECHLTREEXML ="retrieveCHLTreeXML";
	private TreeGenerator treeGenerator;
	
	
	/**
	 * This method let us open CHL tree popup
	 * @param request 
	 * @param response 
	 * @return String 
	 */
	
	@RequestMapping(params = "action=showCHLTreePopUp")
	public String showCHLTreePopUp(RenderRequest request,
			RenderResponse response) {
		LOGGER.enter(CLASS_NAME, METH_SHOWCHLTREEPOPUP);
		LOGGER.exit(CLASS_NAME, METH_SHOWCHLTREEPOPUP);
		response.setContentType("UTF-8");
		return ChangeMgmtConstant.CHLTREEPATH;
		
	}
	
	/**
	 * This method calls retrieveCHLTreeXML
	 * @param request 
	 * @param response 
	 * @param model 
	 * @throws Exception 
	 */
	
	@ResourceMapping("chlTreeXMLURL")
	public void CHLTreeXML(ResourceRequest request,
			ResourceResponse response, Model model) throws Exception {
		
		LOGGER.enter(CLASS_NAME, METH_RETRIEVECHLTREEXML);
		retrieveCHLTreeXML(request, response, model);
		LOGGER.exit(CLASS_NAME, METH_RETRIEVECHLTREEXML);
	}
	
	// sharedportletcontroller methods
	/**
	 * This method retrieves chl tree XML
	 * @param request 
	 * @param response 
	 * @param model 
	 */
	public void retrieveCHLTreeXML(ResourceRequest request,
			ResourceResponse response, Model model) {
		String nodeId = request.getParameter("id");
		boolean isRootRequired = true;
		LOGGER.debug("-------------retrieveCHLTreeXML started---------NodeId="+nodeId);
		LocationReportingHierarchyContract contract = ContractFactory.getLocationReportingHierarchyContract(request);
		LEXLOGGER.info("start printing lex logger");		
		ObjectDebugUtil.printObjectContent(contract,LEXLOGGER );
		LEXLOGGER.info("end printing lex loggger");
		if(nodeId == null){
			//Amind will retrieve the top level
			nodeId = LexmarkPPConstants.ROOT_NODE_ID;
			isRootRequired = true;
		}else if(nodeId.equals(LexmarkPPConstants.TREE_LEVEL_1)){
			//Amind will retrieve the top level
			isRootRequired = false;
		}else{
			//Amind will perform siebel search to retrieve the child node list of given nodeId
			contract.setChlNodeId(nodeId);
			isRootRequired = false;
		}
			
		
		
		Long startTime = System.currentTimeMillis();
		AssetReportingHierarchyResult result=null;
		try{
		 result= deviceService.retrieveAssetReportingHierarchy(contract);
		}catch(Exception e){
			LOGGER.debug("In Exception");
		}
		
		PerformanceUtil.calcTime(perfLogger, PerformanceConstant.SHAREDPORTLET_MSG_RETRIEVEASSETREPORTINGHIERARCHY, startTime,System.currentTimeMillis(), PerformanceConstant.SYSTEM_SIEBEL,contract);
		

		if(result == null || result.getChlNodeList() == null || result.getChlNodeList().size() == 0){
			LOGGER.debug("result is null");
			isRootRequired = false;
		}
		if(isRootRequired){
			LOGGER.debug("2ND IF");
			result.setChlNodeList(generateRootNode(result, request.getLocale()));
		}
		String chlNodeTreeXML = getTreeGenerator(request.getLocale()).generateReportingHierarchyXML(result.getChlNodeList(),nodeId);
		LOGGER.debug("after tree generator");
		PrintWriter out=null;;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			LOGGER.debug("In Exception");
		}
		response.setContentType("text/xml");
		out.print(chlNodeTreeXML);
		out.flush();
		out.close();
		LOGGER.debug("-------------retrieveCHLTreeXML end---------");
	}
	
	/**
	 * This method retrieves chl tree XML
	 * @param result 
	 * @param locale 
	 * @return CHLNode 
	 */
	
	private List<CHLNode> generateRootNode(AssetReportingHierarchyResult result, Locale locale){
		LOGGER.debug("in generateRootNode");
		boolean hasChild = false;
		List<CHLNode> chlNodeList = new ArrayList<CHLNode>();
		CHLNode rootNode = new CHLNode();
		if(result != null && result.getChlNodeList() != null && result.getChlNodeList().size() > 0){
			hasChild = true;
		}
		
		rootNode.setCHLNodeId("0_0");
		LOGGER.debug("after setCHLNodeId");
		rootNode.setChlNodeName(PropertiesMessageUtil.getPropertyMessage(
				LexmarkPPConstants.MESSAGE_BUNDLE_NAME,"tree.label.customerHierarchy",locale));	
			
		rootNode.setHasChild(hasChild);
		chlNodeList.add(rootNode);
		LOGGER.debug("b4 return generateRootNode");
		return chlNodeList;
	}
	
	/**
	 * This method forms the tree for Chl pop up
	 * @param locale 
	 * @return TreeGenerator 
	 */
	private TreeGenerator getTreeGenerator(Locale locale) {
		if (treeGenerator == null) {
			treeGenerator = new TreeGenerator(locale);
		} else {
			treeGenerator.setLocale(locale);
		}
		return treeGenerator;
	}
}
