package com.lexmark.services.portlet;

import java.util.List;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.lexmark.contract.UserFilterSettingContract;

import com.lexmark.domain.FilterPreferenceList;
import com.lexmark.service.api.UserFilterSettingService;
import com.lexmark.services.exporter.CreateExcel;
import com.lexmark.services.portlet.fleetmanagement.FleetManagementController;
import com.lexmark.services.util.ContractFactory;
import com.lexmark.services.util.ObjectDebugUtil;
import com.liferay.portal.service.UserLocalServiceUtil;

@Controller
@RequestMapping("VIEW")
public class FilterSettingsController {
	
	private static Logger LOGGER = LogManager.getLogger(FilterSettingsController.class);
	@Autowired
	private UserFilterSettingService filterFieldService;
	/**
	 * @param request 
	 * @return String 
	 */
	@RequestMapping
	public String showDefault(RenderRequest request,RenderResponse response){
		LOGGER.debug("[ in retrievePreferences]");
		LOGGER.debug("[ out retrievePreferences]");
		return "filterSettings/defaultView";
		
		
		//return "";
		
	}
	@ResourceMapping("retrieveList")
	public void retrieveAllPreferencesSaved(ResourceRequest request,ResourceResponse response){
		LOGGER.debug("[ in retrievePreferences]");
		
		LOGGER.debug("[DB Retrival Starts ]");
		
		UserFilterSettingContract contract=null;
		
		try {
			contract = ContractFactory.getFilterSettingContract(request);
			ObjectDebugUtil.printObjectContent(contract, LOGGER);
			List<FilterPreferenceList>listOfPreferences= filterFieldService.retrieveUserAllPreferences(contract);
			updateEmailAddress(listOfPreferences);
			CreateExcel createExcel= new CreateExcel();
	          boolean flag=createExcel.createExcelReport(listOfPreferences,response);
		} catch (Exception e) {
			LOGGER.error("[unable  to retrieve Filter Setting] "+ e.getMessage());			
		}
		
		
		LOGGER.debug("[ out retrievePreferences]");
		//return "";
		
	}
	
	private void updateEmailAddress(List<FilterPreferenceList> prefList){
		
		for(FilterPreferenceList pref:prefList){
			String email=null;
			try{
				email=UserLocalServiceUtil.getUserById((Long.valueOf(pref.getUserId()))).getEmailAddress();
			}catch(Exception e){
				LOGGER.error("[User email address not found]");
			}
			pref.setEmailAddress(email);
		}
		
		
	}
}
