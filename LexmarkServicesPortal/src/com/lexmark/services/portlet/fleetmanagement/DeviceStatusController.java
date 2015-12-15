package com.lexmark.services.portlet.fleetmanagement;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.portlet.PortletRequest;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.lexmark.domain.SiebelLocalization.SiebelLocalizationOptionEnum;
import com.lexmark.framework.exception.LGSDBException;
import com.lexmark.services.api.DeviceStatusService;
import com.lexmark.services.portlet.common.CommonController;

@Controller
@RequestMapping("VIEW")
public class DeviceStatusController {
	
	@Autowired
	private CommonController commonController;
	
	@Autowired
	private DeviceStatusService deviceStatusService;
	private static Logger LOGGER = LogManager.getLogger(DeviceStatusController.class); 
	
	@ResourceMapping("showDeviceStatus")
	public String showDeviceStatus(ResourceRequest request,
			ResourceResponse response,Model model){
		LOGGER.debug("[In show Device Status]");
		LOGGER.debug("[Out show Device Status]");
		
		
		retrieveDropdownValues(request,model);
		response.setContentType("text/html");
		return "fleetmanagement/deviceStatus/deviceStatus";
	}
	
	
	/**
	 * @param request
	 * @param id
	 * @param response
	 */
	@ResourceMapping("deviceStatusInfor")
	public void retrieveDeviceStatuspopupInformation(ResourceRequest request,
			@RequestParam("id") String id, ResourceResponse response){
		String result = null;
		try{
			result=deviceStatusService.retrieveDeviceStatusInformation(id);
		}catch(Exception e){
			
		}finally{
			writeResonse(response,result==null?"":result);
		}
		
		
	}
	

	/**
	 * @param response 
	 * @param val 
	 */
	private void writeResonse(ResourceResponse response,String val){
		try {
			final PrintWriter out = response.getWriter();
			response.setProperty("Cache-Control", "max-age=0,no-cache,no-store");
			response.setProperty("Expires", "max-age=0,no-cache,no-store");
			response.setContentType("application/json");
			out.write(val);
			out.flush();
			out.close();
		} catch (IOException e) {
			LOGGER.error("IOException while invoking response#getWriter(),"
					+ e.getMessage());
		}
	}
	
	/**
	 * @param request 
	 * @param model 
	 */
	public void retrieveDropdownValues(PortletRequest request,Model model){
		
		
		
		
		Map<String, String> alertSupplies=null;
		Map<String, String> alertDevice=null;
		Map<String, String> utilization=null;		
		Map<String, String> reportingStatus=null;		
		try {
			
			
			alertSupplies = commonController.retrieveLocalizedLOVMap(SiebelLocalizationOptionEnum.LBS_DEVICE_STATUS_SUPPLES.getValue(), request.getLocale());			
			alertDevice = commonController.retrieveLocalizedLOVMap(SiebelLocalizationOptionEnum.LBS_DEVICE_STATUS_DEVICES.getValue(), request.getLocale());
			utilization = commonController.retrieveLocalizedLOVMap(SiebelLocalizationOptionEnum.LBS_DEVICE_STATUS_UTILIZATION.getValue(), request.getLocale());			
			reportingStatus = commonController.retrieveLocalizedLOVMap(SiebelLocalizationOptionEnum.LBS_DEVICE_STATUS_REPROTINGSTATUS.getValue(), request.getLocale());
			
		}catch (LGSDBException e) {
			// TODO Auto-generated catch block
			LOGGER.debug("Exception"+e.getMessage());
		}catch (Exception e) {
			LOGGER.debug("Exception"+e.getMessage());
		}
	
		model.addAttribute("alertSupplies",alertSupplies);
		model.addAttribute("alertDevice",alertDevice);		
		model.addAttribute("utilization",utilization);		
		model.addAttribute("reportingStatus",reportingStatus);
		
		
		 	
	}
}
