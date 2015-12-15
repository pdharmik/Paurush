package com.lexmark.services.portlet.fleetmanagement;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.portlet.PortletRequest;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
//import org.hibernate.HibernateException;
//import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;


import com.amind.common.util.StringUtils;
import com.lexmark.contract.UserFilterSettingContract;
//import com.lexmark.domain.OPSUserFilterPopupSetting;
import com.lexmark.domain.UserFieldsViewSetting;
import com.lexmark.domain.UserFilterSetting;
import com.lexmark.service.api.UserFilterSettingService;
//import com.lexmark.service.impl.real.jdbc.HibernateUtil;
//import com.lexmark.service.impl.real.jdbc.InfrastructureException;
import com.lexmark.services.util.ContractFactory;
//import com.lexmark.services.util.LexmarkUserUtil;
import com.lexmark.services.util.ObjectDebugUtil;
@Controller
@RequestMapping("VIEW")
public class PreferenceManagementController {
	private static Logger LOGGER = LogManager.getLogger(PreferenceManagementController.class);
	
	@Autowired
	private UserFilterSettingService filterFieldService;
	
	
	/**
	 * @param request 
	 */
	/*@ResourceMapping("saveFilter")
	public void saveFilterSetting(ResourceRequest request) {
		
		LOGGER.debug("[ in saveFilterSetting]");
		
		UserFilterSettingContract contract=null;
		try {
			contract = ContractFactory.getFilterSettingContract(request);
			ObjectDebugUtil.printObjectContent(contract, LOGGER);
			UserFilterSetting userFilterSetting=filterFieldService.saveUserPreferences(contract);
			
		} catch (Exception e) {
			LOGGER.error("[unable  to save Filter] "+ e.getMessage());			
		}
		LOGGER.debug("[ out saveFilterSetting]");
		
	}*/
	
	/**
	 * @param response 
	 * @param request 
	 * @throws Exception 
	 */
	@ResourceMapping("retrieveFilter")
	public void retrieveFilterSetting(ResourceResponse response,ResourceRequest request) throws Exception{
		
		LOGGER.debug("[ in retrieveFilterSetting]");
		String preferences=retrievePreferences(request);
		
		try {
			final PrintWriter out = response.getWriter();
			response.setProperty("Cache-Control", "max-age=0,no-cache,no-store");
			response.setProperty("Expires", "max-age=0,no-cache,no-store");
			response.setContentType("UTF-8");
			out.write(preferences);
			out.flush();
			out.close();
		} catch (IOException e) {
			LOGGER.error("IOException while invoking response#getWriter(),"
					+ e.getMessage());
		}
		
		LOGGER.debug("[DB Retrival Ends ]");
		LOGGER.debug("[ OUT retrieveFilterSetting]");
	}
	
	/**
	 * @param request 
	 * @return String 
	 */
	public String retrievePreferences(PortletRequest request){/*
		LOGGER.debug("[ in retrievePreferences]");
		
		LOGGER.debug("[DB Retrival Starts ]");
		
		UserFilterSettingContract contract=null;
		UserFilterSetting userFilterSetting=null;
		try {
			contract = ContractFactory.getFilterSettingContract(request);
			ObjectDebugUtil.printObjectContent(contract, LOGGER);
			userFilterSetting=filterFieldService.retrieveUserPreferences(contract);
			
		} catch (Exception e) {
			LOGGER.error("[unable  to retrieve Filter Setting] "+ e.getMessage());			
		}
		
		
		StringBuffer sb=new StringBuffer();
		sb.append("{\"address\":");
		sb.append(StringUtils.isNotBlank(userFilterSetting.getAddress())==true?userFilterSetting.getAddress():"{}");
		sb.append(",\"device\":");
		sb.append(StringUtils.isNotBlank(userFilterSetting.getDevice())==true?userFilterSetting.getDevice():"{}");
		sb.append(",\"srRequest\":");
		sb.append(StringUtils.isNotBlank(userFilterSetting.getServiceRequest())==true?userFilterSetting.getServiceRequest():"{}");
		sb.append(",\"account\":");
		sb.append(StringUtils.isNotBlank(userFilterSetting.getAccount())==true?userFilterSetting.getAccount():"{}");
		sb.append(",\"isPref\":\"");
		sb.append(Character.isLetter(userFilterSetting.getIsPreference())==false?"":userFilterSetting.getIsPreference());
		sb.append("\"}");
		
		LOGGER.debug("[ out retrievePreferences]");
		return sb.toString();
		
	*/

		LOGGER.debug("[ in retrievePreferences]");
		
		LOGGER.debug("[DB Retrival Starts ]");
		
		UserFilterSettingContract contract=null;
		List<UserFilterSetting> userFilterSettings=null;
		try {
			contract = ContractFactory.getFilterSettingContract(request);
			ObjectDebugUtil.printObjectContent(contract, LOGGER);
			userFilterSettings=filterFieldService.retrieveUserPreferences(contract);
			
		} catch (Exception e) {
			LOGGER.error("[unable  to retrieve Filter Setting] "+ e.getMessage());			
		}
		
		
		StringBuffer sb=new StringBuffer();
		for(UserFilterSetting userFilterSetting:userFilterSettings){
			sb.append("{\"id\":");
			sb.append(StringUtils.isNotBlank(userFilterSetting.getRowId())==true?userFilterSetting.getRowId():"");
			sb.append(",\"address\":");
			sb.append(StringUtils.isNotBlank(userFilterSetting.getAddress())==true?userFilterSetting.getAddress():"{}");
			sb.append(",\"fieldDisp\":\"");
			sb.append(StringUtils.isNotBlank(userFilterSetting.getFieldPrefernce())==true?userFilterSetting.getFieldPrefernce():"");
			sb.append("\",\"device\":");
			sb.append(StringUtils.isNotBlank(userFilterSetting.getDevice())==true?userFilterSetting.getDevice():"{}");
			sb.append(",\"srRequest\":");
			sb.append(StringUtils.isNotBlank(userFilterSetting.getServiceRequest())==true?userFilterSetting.getServiceRequest():"{}");
			sb.append(",\"prefName\":\"");
			sb.append(StringUtils.isNotBlank(userFilterSetting.getPrefName())==true?userFilterSetting.getPrefName():"{}");
			sb.append("\",\"account\":");
			sb.append(StringUtils.isNotBlank(userFilterSetting.getAccount())==true?userFilterSetting.getAccount():"{}");
			sb.append(",\"isDefault\":\"");
			sb.append(Character.isLetter(userFilterSetting.getDefaultPref())==false?"":userFilterSetting.getDefaultPref());
			sb.append("\"},");	
		}
		if(sb.length()>0){
			sb.deleteCharAt(sb.length()-1);
		}		
		sb.insert(0, '[');
		sb.append("]");
		
		
		LOGGER.debug("[ out retrievePreferences]");
		return sb.toString();
		
		
	
	}
	
	/**
	 * @param indexes 
	 * @param request 
	 * @throws Exception 
	 */
	@ResourceMapping("saveCustomzieSettingsPopup")
	public void savePopupSettings(@RequestParam(value="indexes") String indexes,ResourceRequest request) throws Exception{
		LOGGER.debug("[ in savePopupSettings]");
		UserFilterSettingContract contract=null;
		try {
			contract = ContractFactory.getFieldSettingContract(request);
			ObjectDebugUtil.printObjectContent(contract, LOGGER);
			boolean flag=filterFieldService.saveUserFieldPreferences(contract);
			LOGGER.debug("[ success "+flag+"]");
		} catch (Exception e) {
			LOGGER.error("[unable  to save Popup settings] "+ e.getMessage());			
		}
		
		LOGGER.debug("[ OUT savePopupSettings]");
	}
	
	
	/**
	 * @param request 
	 * @return UserFieldsViewSetting 
	 */
	@SuppressWarnings("finally")
	public UserFieldsViewSetting retrievePopupSettings(PortletRequest request) {
		LOGGER.debug("[ in retrievePopupSettings]");
		UserFilterSettingContract contract=null;
		UserFieldsViewSetting viewSettings=null;
		try {
			contract = ContractFactory.getFieldSettingContract(request);
			ObjectDebugUtil.printObjectContent(contract, LOGGER);
			viewSettings=filterFieldService.retrieveUserFieldPreferences(contract);
			
		} catch (Exception e) {
			LOGGER.debug("Exception"+e.getMessage());
			LOGGER.error("[unable  to retrieve Popup Settings] "+ e.getCause());			
		}finally{
			LOGGER.debug("[ OUT retrievePopupSettings]");
			return viewSettings;
		}
		
		
		
	}
	/**
	 * @param request 
	 * @param response 
	 */
	@ResourceMapping("saveFilterPrefPopup")
	public void saveFilterSettingPopup(ResourceRequest request,ResourceResponse response) {
		
		LOGGER.debug("[ in saveFilterSetting]");
		
		UserFilterSettingContract contract=null;
		try {
			contract = ContractFactory.getFilterSettingContract(request);
			ObjectDebugUtil.printObjectContent(contract, LOGGER);
			UserFilterSetting filterSetting=filterFieldService.saveUserPreferences(contract);
			
			StringBuffer resp=new StringBuffer("{");
			resp.append("\"id\":\""+filterSetting.getRowId()+"\",");
			resp.append("\"prefName\":\""+filterSetting.getPrefName()+"\"");
			resp.append("}");
			final PrintWriter out = response.getWriter();
			response.setProperty("Cache-Control", "max-age=0,no-cache,no-store");
			response.setProperty("Expires", "max-age=0,no-cache,no-store");
			response.setContentType("UTF-8");
			out.write(resp.toString());
			out.flush();
			out.close();
			LOGGER.debug("[ success "+filterSetting.getRowId()+"]");
		} catch (Exception e) {
			LOGGER.error("[unable  to save Filter] "+ e.getMessage());			
		}
		LOGGER.debug("[ out saveFilterSetting]");
		
	}
	
	/**
	 * @param indexes 
	 * @param id 
	 * @param request 
	 * @throws Exception 
	 */
	@ResourceMapping("saveCustomzieSettingsPopupOPS")
	public void savePopupSettingsOPS(@RequestParam(value="indexes") String indexes,@RequestParam(value="id") String id,
			ResourceRequest request) throws Exception{
		LOGGER.debug("[ in savePopupSettings OPS]");
		UserFilterSettingContract contract=null;
		try {
			contract = ContractFactory.getFieldSettingContractOPS(request);
			ObjectDebugUtil.printObjectContent(contract, LOGGER);
			boolean flag=filterFieldService.saveUserFieldPreferences(contract);
			LOGGER.debug("[ success "+flag+"]");
		} catch (Exception e) {
			LOGGER.error("[unable  to save Popup settings OPS ] "+ e.getMessage());			
		}
		
		LOGGER.debug("[ OUT savePopupSettings OPS]");
	}
	/**
	 * @param index 
	 * @param resp 
	 * @throws IOException 
	 */
	@ResourceMapping("removePrefOPS")
	public void removePreference(@RequestParam(value="index") String index,ResourceResponse resp) throws IOException{
		boolean success=false;
		try {
			success=filterFieldService.removeUserPreference(index);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			LOGGER.debug("Exception"+e.getMessage());
			LOGGER.error("[unable  to delete preference] "+ e.getCause());
		}finally{
			StringBuffer sb=new StringBuffer();
			sb.append("{\"success\":\"").append(success).append("\",\"id\":").append(index).append("}");
			final PrintWriter out = resp.getWriter();
			resp.setProperty("Cache-Control", "max-age=0,no-cache,no-store");
			resp.setProperty("Expires", "max-age=0,no-cache,no-store");
			resp.setContentType("UTF-8");
			out.write(sb.toString());
			out.flush();
			out.close();
		}
	}

}
