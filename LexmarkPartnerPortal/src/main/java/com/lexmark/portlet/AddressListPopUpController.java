/**
 * Copyright@ 2013. This document has been created & written by 
 * Wipro technologies for Lexmark and this is copyright to Lexmark 
 *
 * File         	: AddressListPopUpController.java 
 * Package     		: com.lexmark.portlet 
 * Creation Date 	: 
 *
 * Modification History: 
 *
 * ---------------------------------------------------------------------
 * Author  			Date		 		Version  	 	Comments 
 * ---------------------------------------------------------------------
 *
 */
package com.lexmark.portlet;

import java.io.PrintWriter;
import java.util.List;
import java.util.Locale;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.lexmark.constants.LexmarkPPConstants;
import com.lexmark.contract.AddressListContract;
import com.lexmark.contract.GeographyCountryContract;
import com.lexmark.contract.GeographyStateContract;
import com.lexmark.framework.logging.LEXLogger;
import com.lexmark.result.AddressListResult;
import com.lexmark.result.GeographyListResult;
import com.lexmark.service.api.CrmSessionHandle;
import com.lexmark.service.api.GeographyService;
import com.lexmark.service.api.GlobalService;
import com.lexmark.service.api.ServiceRequestService;
import com.lexmark.util.ChangeMgmtConstant;
import com.lexmark.util.ContractFactory;
import com.lexmark.util.ObjectDebugUtil;
import com.lexmark.util.PerformanceConstant;
import com.lexmark.util.PerformanceUtil;
import com.lexmark.util.PortalSessionUtil;
import com.lexmark.util.XmlOutputGenerator;
import com.lexmark.util.PortalSessionUtil;



/**
* Class used for Address list popup
*
* @version 1.10 10 Nov 2014
* @author Wipro technologies 
*/
@Controller
@RequestMapping("VIEW")
public class AddressListPopUpController {
	
	private static LEXLogger LOGGER = LEXLogger.getLEXLogger(AddressListPopUpController.class);
	private static Logger perfLogger = LogManager.getLogger("performance");
	
	@Autowired
	private GlobalService globalService;
	@Autowired
	private ServiceRequestService serviceRequestService;
	@Autowired
	private GridSettingController gridSettingController;
	@Autowired 
	private GeographyService geographyService; 
	

	/**
	 * @param request 
	 * @param response 
	 * @param model 
	 * @param showCreateNew 
	 * @return string 
	 * @throws Exception 
	 */
	@RequestMapping(params = "action=showAddressList")
	public String showSelectAddressPage(RenderRequest request,RenderResponse response,Model model,@RequestParam("shwCrN")boolean showCreateNew)throws Exception {
		LOGGER.enter("AddressListPopUpController", "showAddressList");
				
		
		gridSettingController.retrieveGridSetting("serviceAddressGridbox", request, response, model);
		model.addAttribute("showCreateNew", showCreateNew);
		LOGGER.exit("AddressListPopUpController", "showSelectAddressPage");
		response.setContentType("UTF-8");
		return "common/addressPopup/addressListPopup";
	}

	
	/**
	 * @param request 
	 * @param response 
	 * @param model 
	 * @param isFavorite 
	 * @return string 
	 */
	@ResourceMapping(value="retrievePartnerAddressList")
	public String retrieveServiceAddressList(ResourceRequest request,
			ResourceResponse response, Model model,@RequestParam("isFavorite")String isFavorite){
		
		LOGGER.debug("--------Entering retrieveServiceAddressList---------");
		
		
		
		AddressListContract contract = ContractFactory.getAddressListContract(request);
		
		CrmSessionHandle crmSessionHandle = globalService.initCrmSessionHandle(
		PortalSessionUtil.getSiebelCrmSessionHandle(request));
	
		contract.setSessionHandle(crmSessionHandle);
		

	
		
		if (StringUtils.isNotBlank(isFavorite)&& "true".equalsIgnoreCase(isFavorite)) {
			contract.setFavoriteFlag(true);
		} else {
			contract.setFavoriteFlag(false);
		}
		
		
		
		if(LOGGER.isInfoEnabled()){
		LOGGER.info("start printing lex LOGGER");
		ObjectDebugUtil.printObjectContent(contract, LOGGER);
		LOGGER.info("end printing lex loggger");
		}
		AddressListResult addressListResult=null;
		try {
			
			Long startTime = System.currentTimeMillis();
			addressListResult = serviceRequestService.retrieveAddressList(contract);
			PerformanceUtil.calcTime(perfLogger, PerformanceConstant.COMMON_MSG_RETRIEVEADDRESSLIST, startTime,System.currentTimeMillis(), PerformanceConstant.SYSTEM_SIEBEL,contract);
			
			if(LOGGER.isDebugEnabled()){
			LOGGER.logTime("** MPS PERFORMANCE TESTING RETRIEVE ADDRESS LIST ==>: " + (System.currentTimeMillis()- startTime)/1000.0);
			LOGGER.debug("List Total Count is "+ addressListResult.getTotalCount());
			LOGGER.debug("List Total Count is "+ addressListResult.getAddressList().size());
			}
				
		} catch (Exception e) {
			
			LOGGER.error("Error occured while retrieving address list"+e.getMessage());
		}finally {
			globalService.releaseSessionHandle(crmSessionHandle);
		}
		model.addAttribute("addressList", addressListResult.getAddressList());
		model.addAttribute(LexmarkPPConstants.TOTALCOUNT, addressListResult.getTotalCount());
		model.addAttribute(LexmarkPPConstants.STARTPOS, contract.getStartRecordNumber());
		
		
		LOGGER.exit(this.getClass().getSimpleName(), "retrieveServiceAddressList");
		
		response.setContentType("text/xml");
		return "common/addressPopup/addressListxml";
		
		
		
		
		
	}
	
	/**
	 * @param resourceRequest 
	 * @param resourceResponse 
	 * @throws Exception 
	 */
	@ResourceMapping("countryDropDownPopulate")
	public void populateCountryList(ResourceRequest resourceRequest,ResourceResponse resourceResponse)throws Exception{
		final String METH_POPULATECOUNTRYLIST="populateCountryList";
		LOGGER.enter(this.getClass().getSimpleName(), METH_POPULATECOUNTRYLIST);
		List<GeographyCountryContract> countries =null;
		PrintWriter out =null;
		String htmlContentCountryList=null;
		try{
		
			LOGGER.debug("There has been an error in fetching country details");
			GeographyListResult countryListResult = geographyService.getCountryDetails();
			countries = countryListResult.getCountryList();
			htmlContentCountryList = getXmlOutputGenerator(resourceRequest.getLocale()).convertCountriesToHTML(countries);
			
		}catch(Exception e){
			LOGGER.debug("Exception occured "+e.getMessage());
			htmlContentCountryList="<option></option>";
		}
		finally{
			out = resourceResponse.getWriter();
			resourceResponse.setContentType(ChangeMgmtConstant.CONTENTTYPEHTML);
			out.print(htmlContentCountryList);
			if(out!=null)
			{
				out.flush();
				out.close();	
			}
		}
		LOGGER.exit(this.getClass().getSimpleName(), METH_POPULATECOUNTRYLIST);
	}
	
	/**
	 * @param resourceRequest 
	 * @param resourceResponse 
	 * @param countryCodeVal 
	 * @throws Exception 
	 */
	@ResourceMapping("stateDropDownPopulate")
	public void populateStateList(ResourceRequest resourceRequest,ResourceResponse resourceResponse,
			@RequestParam(ChangeMgmtConstant.COUNTRYCODE) String countryCodeVal)throws Exception{
		final String METH_POPULATESTATELIST="populateStateList";
		LOGGER.enter(this.getClass().getSimpleName(), METH_POPULATESTATELIST);
		
		LOGGER.debug("countryCodeVal " + countryCodeVal);
		GeographyListResult stateListResult= new GeographyListResult();
		List<GeographyStateContract> stateList= null;
		String htmlContentStateList = null;
		PrintWriter out = null;
		try{
		
		stateListResult = geographyService.getStateDetails(countryCodeVal);
		stateList = stateListResult.getStateList();
		LOGGER.debug("================state list has been populated===============" + stateList.size());
		htmlContentStateList=getXmlOutputGenerator(resourceRequest.getLocale()).convertStateToHTML(stateList);
		LOGGER.debug("=============state list has been converted to xml============");
		
		}catch(Exception ex){
			LOGGER.debug("Exception occured "+ex.getMessage());
			htmlContentStateList="<option></option>";
		}
		finally{
			out = resourceResponse.getWriter();
			resourceResponse.setContentType(ChangeMgmtConstant.CONTENTTYPEHTML);
			out.print(countryCodeVal+htmlContentStateList);
			out.flush();
			out.close();	
		}
		LOGGER.exit(this.getClass().getSimpleName(), METH_POPULATESTATELIST);
	}
	
	/**
	 * 
	 * @param locale 
	 * @return xmlOutputGenerator 
	 */
	public XmlOutputGenerator getXmlOutputGenerator(Locale locale) {
		XmlOutputGenerator xmlOutputGenerator = new XmlOutputGenerator(locale);
		return xmlOutputGenerator;
	}
	
}
