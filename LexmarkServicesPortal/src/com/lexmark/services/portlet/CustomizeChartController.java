package com.lexmark.services.portlet;

import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.lexmark.constants.LexmarkConstants;
import com.lexmark.domain.ChartDetail;
import com.lexmark.domain.ChartInfo;
import com.lexmark.service.impl.real.jdbc.CustomizeChartServiceImpl;
import com.lexmark.services.util.LexmarkUserUtil;
import com.lexmark.services.util.PortalSessionUtil;


@Controller
@RequestMapping("VIEW")
public class CustomizeChartController extends BaseController{
	
	private static Logger logger = LogManager.getLogger(CustomizeChartController.class);
	
	/**
	 * @param model 
	 * @param request 
	 * @param response 
	 * @return String  
	 * @throws Exception 
	 * 
	 */
	@RequestMapping
	public String gotoChartCustomPage(Model model, RenderRequest request, RenderResponse response) throws Exception{
		logger.debug("--------------Entry to gotoChartCustomPage()------------------");
		List<ChartInfo> chartList = null;
		List<ChartInfo> leftChartList = null;
		List<ChartInfo> rightChartList = null;
		
		boolean isRoleAdmin = false;
		int noOfCharts = 0;
		
		List<String> userRoles = null;
		try {
			
			
			CustomizeChartServiceImpl customizeChartService = new CustomizeChartServiceImpl();
			userRoles = LexmarkUserUtil.getUserRoleNameList(request);
			String mdmId = (String)PortalSessionUtil.getMdmId(request.getPortletSession());
			
			//Set the "Account Administrator" Role
			for (Iterator iterator = userRoles.iterator(); iterator.hasNext();) {
				String role = (String) iterator.next();
				if(role != null && LexmarkConstants.ROLE_SERVICES_PORTAL_ADMINISTRATOR.equalsIgnoreCase(role.trim())){
					isRoleAdmin = true;
				}
			} // End of for
			
			if(isRoleAdmin){
				chartList = customizeChartService.getReportList();
				noOfCharts = chartList.size();
				ChartInfo contract = new ChartInfo();
					contract.setMdmId(mdmId);
					contract.setWindow("L");
						leftChartList = customizeChartService.getWindowBasedReportList(contract);
					contract.setWindow("R");
						rightChartList = customizeChartService.getWindowBasedReportList(contract);
				chartList = prepareChartOrganize(chartList, leftChartList, rightChartList);
				
			}
			logger.info("-----Inside gotoChartCustomPage() : userRoles ------------"+userRoles);
			logger.info("-----Inside gotoChartCustomPage() : mdmId -------------"+mdmId);
			
		} catch (Exception e) {
			logger.debug("Exception "+e.getMessage());
		}
		model.addAttribute("chartList", chartList);
		model.addAttribute("isRoleAdmin", isRoleAdmin);
		model.addAttribute("noOfCharts", noOfCharts);
		
		logger.debug("--------------Exit to gotoChartCustomPage()------------------");
		return "customizechart/showCustomChart";
	}
	
	/**
	 * @param request 
	 * @param response 
	 * @param model 
	 * @throws Exception 
	 */
	@ResourceMapping(value="saveCustomizedChartSetting")
	public void saveCustomization(ResourceRequest request,
			ResourceResponse response, Model model) throws Exception{
		logger.debug("--------------Entry to saveCustomization()------------------");
		boolean flag = false;
		
		try {
			CustomizeChartServiceImpl customizeChartService = new CustomizeChartServiceImpl();
			
			String mdmId = (String)PortalSessionUtil.getMdmId(request.getPortletSession());
			String dunsNumber = (String)PortalSessionUtil.getMdmIdOrDunsBasedOnLevel(request.getPortletSession());
			logger.info("====================================== user MdmId : "+mdmId);
			logger.info("====================================== duns : "+dunsNumber);
			if(dunsNumber != null && !"".equals(dunsNumber)){
				mdmId = dunsNumber;
			}
			/*
			String mdmLevel = (String)PortalSessionUtil.getMdmLevel(request.getPortletSession());
			GlobalServiceFacadeImpl globalServiceFacade = new GlobalServiceFacadeImpl();
			GlobalAccount account = globalServiceFacade.retriveGlobalAccount(mdmId, mdmLevel);
			if(account != null){
				mdmId = account.getMdmId();
				logger.debug("Inside account section");
			}
			logger.info("======================================Account MdmId : "+mdmId);
			*/
			ChartDetail contract = new ChartDetail();
				contract.setMdmId(mdmId);
				if(request.getParameter("leftChartId") != null && !"".equals(request.getParameter("leftChartId")) && !"undefined".equals(request.getParameter("leftChartId"))){
					contract.setLeftChartId(request.getParameter("leftChartId"));
				}
				else{
					/**
					 * If no chart selectetd then save the default chart into database
					 */
					ChartInfo innerContract = new ChartInfo();
					innerContract.setChartName(LexmarkConstants.DEFAULT_CHART_MAP.get("LEFT_CHART"));
					ChartInfo returnResult = customizeChartService.getChartDetailsByChartName(innerContract);
					contract.setLeftChartId(returnResult.getChartId());
				}
				
				
				if(request.getParameter("rightChartId") != null && !"".equals(request.getParameter("rightChartId"))&& !"undefined".equals(request.getParameter("rightChartId"))){
					contract.setRightChartId(request.getParameter("rightChartId"));
				}else{
					/**
					 * If no chart selectetd then save the default chart into database
					 */
					ChartInfo innerContract = new ChartInfo();
					innerContract.setChartName(LexmarkConstants.DEFAULT_CHART_MAP.get("RIGHT_CHART"));
					ChartInfo returnResult = customizeChartService.getChartDetailsByChartName(innerContract);
					contract.setRightChartId(returnResult.getChartId());
				}
			
				
			flag = customizeChartService.saveChartCustomization(contract);

			String xmlOutput = "<?xml version=\"1.0\" ?>\n";
			if(flag){
				xmlOutput+= "<ul class='serviceSuccess' id='message_banner_'><li class='portlet-msg-success'>Successfully Updated</li></ul>";
			}else{
				xmlOutput+= "<ul class='serviceError' id='error_banner_'><li class='portlet-msg-error'>Successfully not updated</li></ul>";
			}
			PrintWriter out = response.getWriter();
			response.setContentType("text/xml");
			out.print(xmlOutput);
			out.flush();
			out.close();
			
		} catch (Exception e) {
			logger.debug("Exception "+e.getMessage());
		}
		logger.debug("--------------Exit to saveCustomization()------------------");
		
	}
	
	/**
	 * @param chartList 
	 * @param leftChartList 
	 * @param rightChartList 
	 * @return List<ChartInfo> 
	 */
	private List<ChartInfo> prepareChartOrganize(List<ChartInfo> chartList, List<ChartInfo> leftChartList, List<ChartInfo> rightChartList){
		
		for (Iterator iterator = chartList.iterator(); iterator.hasNext();) {
			ChartInfo chartInfo = (ChartInfo) iterator.next();
			
			//Check whether List is null or not.
			if(leftChartList != null && !leftChartList.isEmpty()){
				for (Iterator leftIterator = leftChartList.iterator(); leftIterator.hasNext();) {
					ChartInfo leftChartInfo = (ChartInfo) leftIterator.next();
					if(leftChartInfo.getChartId().equals(chartInfo.getChartId())){
						chartInfo.setWindow("L");
						continue;
					}
				}
			}else{
				if(LexmarkConstants.DEFAULT_CHART_MAP.get("LEFT_CHART").equals(chartInfo.getChartName().trim())){
					chartInfo.setWindow("L");
				}
			}
			
			//Check whether List is null or not.
			if(rightChartList != null && !rightChartList.isEmpty()){
				for (Iterator rightIterator = rightChartList.iterator(); rightIterator.hasNext();) {
					ChartInfo rightChartInfo = (ChartInfo) rightIterator.next();
					if(rightChartInfo.getChartId().equals(chartInfo.getChartId())){
						chartInfo.setWindow("R");
					}
				}
			}else{
				if(LexmarkConstants.DEFAULT_CHART_MAP.get("RIGHT_CHART").equals(chartInfo.getChartName().trim())){
					chartInfo.setWindow("R");
				}
			}
			
		}
		return chartList;
	}

}
