package com.lexmark.services.portlet.common;

import static com.lexmark.services.util.ChangeMgmtConstant.ADDRESSCLEANSEFIELDS_INPUT;
import static com.lexmark.services.util.ChangeMgmtConstant.ADDRESSCLEANSEFIELDS_OUTPUT;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import javax.portlet.PortletSession;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.lexmark.domain.GenericAddress;
import com.lexmark.framework.logging.LEXLogger;
import com.lexmark.service.api.AddressCleansingService;
import com.lexmark.services.LexmarkSPConstants;
import com.lexmark.services.portlet.BaseController;
import com.lexmark.util.PropertiesMessageUtil;

/**
 * @author Wipro
 * @version 2.1
 */

@SuppressWarnings("unused")
@Controller
@RequestMapping("VIEW")
@SessionAttributes(value="newContactPopupError")

public class AddressCleansingController extends BaseController{
	
	@Autowired
	private AddressCleansingService addressCleanseService;
	
	
	
	/**. Instance variable of wrapper logger class **/
	private static LEXLogger LOGGER = LEXLogger.getLEXLogger(AddressCleansingController.class);
	private static String CLASS_NAME = "AddressCleansing.java" ;
	private static final String METH_NEWADDRESSVALIDATEFROMPOPUP ="newAddressValidateFromPopup";
	private static String ADDRESSCLEANSINGERROR = "com.lexmark.services.resources.AddressCleansingError";
	
	
	/**
	 * This method is used to validate the new Address information in popup
	 * @param model
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@ResourceMapping(value="newAddressValidate")
	public void newAddressValidateFromPopup(ResourceRequest resourceRequest,
			ResourceResponse resourceResponse, Model model)throws Exception{
		
		/*
		 * Get the new Address information from popup
		 */
		LOGGER.enter(CLASS_NAME,METH_NEWADDRESSVALIDATEFROMPOPUP);
			
		
		Map<String,String[]> addAddressValues=resourceRequest.getParameterMap();
		
		StringBuffer responseBody=new StringBuffer();
		//Mandatory fields validation
		if(addAddressValues.get(ADDRESSCLEANSEFIELDS_INPUT[1])[0]==""){//AddressLine1
			responseBody.append("<li><strong>"+PropertiesMessageUtil.getPropertyMessage(LexmarkSPConstants.MESSAGE_BUNDLE_NAME,
					"contact.addrline1.empty", resourceRequest.getLocale())+"</strong></li>");
		}
		if(addAddressValues.get(ADDRESSCLEANSEFIELDS_INPUT[3])[0]==""){//city
			responseBody.append("<li><strong>"+PropertiesMessageUtil.getPropertyMessage(LexmarkSPConstants.MESSAGE_BUNDLE_NAME,
					"contact.city.empty", resourceRequest.getLocale())+"</strong></li>");	
		}
		if(addAddressValues.get(ADDRESSCLEANSEFIELDS_INPUT[4])[0]==""){//country
			responseBody.append("<li><strong>"+PropertiesMessageUtil.getPropertyMessage(LexmarkSPConstants.MESSAGE_BUNDLE_NAME,
					"contact.country.empty", resourceRequest.getLocale())+"</strong></li>");
		}
				//Validation Changes For address Cleansing - Phase 2
		if((addAddressValues.get(ADDRESSCLEANSEFIELDS_INPUT[6])[0]=="" )&& (addAddressValues.get(ADDRESSCLEANSEFIELDS_INPUT[5])[0].trim()=="")){ //state or zip
			responseBody.append("<li><strong>"+PropertiesMessageUtil.getPropertyMessage(LexmarkSPConstants.MESSAGE_BUNDLE_NAME,
					"validation.address.ziporstate.empty", resourceRequest.getLocale())+"</strong></li>");
		}
		LOGGER.debug("addAddressValues.get(ADDRESSCLEANSEFIELDS_INPUT[4])>>>>>>>>>>>>" + addAddressValues.get(ADDRESSCLEANSEFIELDS_INPUT[4]));
		
		LOGGER.debug("response body if empty fields there "+responseBody);
		
		if(responseBody.length()==0){
			GenericAddress addressDataInput = new GenericAddress();
			GenericAddress addressDataOutput=null;
			for(String inputFields:ADDRESSCLEANSEFIELDS_INPUT){
				PropertyUtils.setProperty(addressDataInput, inputFields, addAddressValues.get(inputFields)[0].trim());
			}
			
			
			Map<String,String> accountDetails=(Map<String,String>)resourceRequest.getPortletSession().getAttribute
					("accountCurrentDetails",PortletSession.APPLICATION_SCOPE);
			try{	
			addressDataOutput=addressCleanseService.addressCleanse(addressDataInput, accountDetails);			
			LOGGER.debug("Generating the json response...");			
			if(addressDataOutput!=null)
			{//Changes for Address Cleansing Popup
				if(addressDataOutput.getErrorMsgForCleansing()!=null)
				{	String error_mesg;
					LOGGER.debug("Going for writing the error");
					responseBody.append("\"error\":\"cleanseError\",");
					//Changed the below line for the defect 1059 last part
					error_mesg=AddressCleansingController.codeReturn(addressDataOutput,resourceRequest);
					LOGGER.debug("errors>>>>>>>>>>>>" + error_mesg);
					responseBody.append("\"cleansedError\":\""+error_mesg+ "\"");
					
				}
				else{
				LOGGER.debug("Going for writing the cleansed address");
				responseBody.append("\"error\":\"none\",");
				for(String inputFields:ADDRESSCLEANSEFIELDS_INPUT){
					responseBody.append("\""+inputFields+"\":\""+PropertyUtils.getProperty(addressDataOutput, inputFields)+"\",");
					
				}
				for(String inputFields:ADDRESSCLEANSEFIELDS_OUTPUT){
					responseBody.append("\""+inputFields+"\":\""+PropertyUtils.getProperty(addressDataOutput, inputFields)+"\",");
				}
				responseBody.deleteCharAt(responseBody.length()-1);
				
				
				}
			}
			else{
				LOGGER.debug("Cleansing error else addressoutput is null");
				
			}
		}catch(Exception ex)
			{
			// changes for address cleansing error show
				String errors;
				LOGGER.debug("Cleansing error catch block");
				responseBody.append("\"error\":\"cleanseError\",");	
				errors=PropertiesMessageUtil.getAddressCleansingError(ADDRESSCLEANSINGERROR,"404", resourceRequest.getLocale());
				LOGGER.debug("errors>>>>>>>>>>>>" + errors);
				responseBody.append("\"cleansedError\":\""+errors+ "\"");
				
			
				
			}
			finally
			{
				responseBody.insert(0, "{");
				responseBody.insert(responseBody.length(), "}");
			}
			
			LOGGER.debug("Response body is " + responseBody.toString());
		}
		
		PrintWriter out = resourceResponse.getWriter();
		resourceResponse.setContentType("text/html");
		out.print(responseBody);
		out.flush();
		out.close();
		responseBody.delete(0, responseBody.length());
		LOGGER.exit(this.getClass().getSimpleName(),METH_NEWADDRESSVALIDATEFROMPOPUP);
}
	public static String codeReturn(GenericAddress addressDataOutput,ResourceRequest resourceRequest){
		String error_index;
		String errors;
		String error_message=addressDataOutput.getErrorMsgForCleansing();
		error_index=(error_message.substring(0,error_message.indexOf("-"))).trim();
		LOGGER.debug("index code >>>>>>>>>>>>>>>>>>>"+error_index);
		LOGGER.debug("resourceRequest.getLocale() >>>>>>>>>>>>>>>>>>>"+resourceRequest.getLocale());
		errors=PropertiesMessageUtil.getAddressCleansingError(ADDRESSCLEANSINGERROR,error_index, resourceRequest.getLocale());
		LOGGER.debug("errors code >>>>>>>>>>>>>>>>>>>"+errors);
		return errors;
	}
}
