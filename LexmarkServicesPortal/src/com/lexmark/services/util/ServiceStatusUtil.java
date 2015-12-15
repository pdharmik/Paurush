package com.lexmark.services.util;

import static com.lexmark.services.LexmarkSPConstants.ERROR_MESSAGE_BUNDLE;

import java.io.IOException;
import java.io.OutputStream;

import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import javax.portlet.ResourceResponse;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.ui.Model;

import com.lexmark.services.util.XMLEncodeUtil;

public class ServiceStatusUtil {
	private static Logger logger = LogManager.getLogger(ServiceStatusUtil.class);
/**
 * 
 * @param model 
 * @param serviceStatus 
 * @param locale 
 * @param returnSuccessMessages 
 */
	public static void checkServiceStatus(Model model, String serviceStatus, Locale locale,
			boolean returnSuccessMessages) {
		checkServiceStatus(model, serviceStatus, locale, null, returnSuccessMessages);
	}
/**
 * 
 * @param serviceStatus 
 * @param locale 
 * @param params 
 * @return ServiceStatus 
 */
	private static ServiceStatus strToStatus(String serviceStatus, Locale locale, Object params[]) {
		boolean succeed = serviceStatus.startsWith("message.");
		int idxOfDot1 = serviceStatus.indexOf('.');
		int idxOfDot2 = idxOfDot1 < 0 ? -1 : serviceStatus.indexOf('.', idxOfDot1+1);
		String groupCode = idxOfDot2 < 0 ? null : serviceStatus.substring(0, idxOfDot2);
		String message = ServiceStatusErrorMessageUtil.getErrorMessage(ERROR_MESSAGE_BUNDLE, serviceStatus, params,
				locale);
		String groupMessage = "";
		if (groupCode != null) {
			groupMessage = ServiceStatusErrorMessageUtil.getErrorMessage(ERROR_MESSAGE_BUNDLE, groupCode, params, locale);
			if (groupMessage.startsWith("!! Error Code: ") && groupMessage.endsWith(" not found !!")) {
				groupMessage = "";
			}

		}
		ServiceStatus status = new ServiceStatus();
		status.setSucceed(succeed);
		status.setMessage(message);
		status.setGroup(groupMessage);
		return status;
	}
/**
 * 
 * @param model 
 * @param serviceStatus 
 * @param locale 
 * @param params 
 * @param returnSuccessMessages 
 */
	@SuppressWarnings("unchecked")
	public static void checkServiceStatus(Model model, String serviceStatus, Locale locale, Object params[],
			boolean returnSuccessMessages) {
		logger.debug("entered");
		ServiceStatus status = strToStatus(serviceStatus, locale, params);
		String message = status.group == null ? status.getGroup() + " - " + status.getMessage() : status.getMessage();
		boolean succeed = status.isSucceed();

		if (!succeed) {

			Set<String> serviceErrors = (Set<String>) model.asMap().get("serviceErrors");
			if (serviceErrors == null) {
				serviceErrors = new HashSet<String>();
			}
			serviceErrors.add(message);
			model.addAttribute("serviceErrors", serviceErrors);
		} else {
			if (returnSuccessMessages) {
				Set<String> serviceSuccessMessages = (Set<String>) model.asMap().get("serviceSuccessMessages");
				if (serviceSuccessMessages == null) {
					serviceSuccessMessages = new HashSet<String>();
				}
				serviceSuccessMessages.add(message);
				model.addAttribute("serviceSuccessMessages", serviceSuccessMessages);
			}
		}
	}
	
	/**
	 * This method is specifically written for MPS response xml  
	 * @param response 
	 * @param errorCode 
	 * @param locale 
	 */
	public static void writeResponseResult(ResourceResponse response, String errorCode, Locale locale)
	{
		response.setProperty("Cache-Control", "max-age=0,no-cache,no-store");
		response.setProperty("Expires", "max-age=0,no-cache,no-store");
		ServiceStatus status = strToStatus(errorCode, locale, null);
		StringBuffer sbuffer = new StringBuffer();
		sbuffer.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
		sbuffer.append("<result succeed=\"").append(status.isSucceed()).append("\">\n");
		sbuffer.append("\t<group>").append(status.group).append("</group>\n");

		sbuffer.append("\t<message>").append(XMLEncodeUtil.escapeXMLForDMCM(status.getMessage())).append("</message>\n");
		
		/*if(returnData!=null){
			sbuffer.append("\t<data>").append(XMLEncodeUtil.escapeXMLForDMCM(returnData)).append("</data>\n");
		}*/
		sbuffer.append("</result>");
		//response.setCharacterEncoding("utf-8");
		response.setProperty(ResourceResponse.HTTP_STATUS_CODE, status.isSucceed() ? "200" : "500");
		response.setContentType("text/xml");
		OutputStream portletOutputStream=null;
		try {
			portletOutputStream = response.getPortletOutputStream();
			String messageXML = sbuffer.toString();
			logger.debug(messageXML);
			portletOutputStream.write(messageXML.getBytes());
		} catch (IOException e) {
			throw new RuntimeException("Failed to write response!", e);
		}
	}
	/**
	 * 
	 * @param response 
	 * @param errorCode 
	 * @param locale 
	 */
	public static void responseResult(ResourceResponse response, String errorCode, Locale locale) {
		responseResult(response, errorCode, locale,null);
	}
	/**
	 * 
	 * @param response 
	 * @param errorCode 
	 * @param locale 
	 * @param returnData 
	 */
	public static void responseResult(ResourceResponse response, String errorCode, Locale locale, String returnData) {
		response.setProperty("Cache-Control", "max-age=0,no-cache,no-store");
		response.setProperty("Expires", "max-age=0,no-cache,no-store");
		ServiceStatus status = strToStatus(errorCode, locale, null);
		StringBuffer sbuffer = new StringBuffer();
		sbuffer.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
		sbuffer.append("<result succeed=\"").append(status.isSucceed()).append("\">\n");
		sbuffer.append("\t<group>").append(status.group).append("</group>\n");
//		sbuffer.append("\t<message>").append(status.getMessage()).append("</message>\n");
		// Fix by Service Portal AMS LEX:AIR00062572
		sbuffer.append("\t<message>").append(XMLEncodeUtil.escapeXML(status.getMessage())).append("</message>\n");
		if(returnData!=null){
			sbuffer.append("\t<data>").append(XMLEncodeUtil.escapeXML(returnData)).append("</data>\n");
		}
		sbuffer.append("</result>");
		response.setCharacterEncoding("utf-8");
		response.setProperty(ResourceResponse.HTTP_STATUS_CODE, status.isSucceed() ? "200" : "500");
		response.setContentType("text/xml");
		OutputStream portletOutputStream=null;
		try {
			portletOutputStream = response.getPortletOutputStream();
			String messageXML = sbuffer.toString();
			logger.debug(messageXML);
			portletOutputStream.write(messageXML.getBytes());
		} catch (IOException e) {
			throw new RuntimeException("Failed to write response!", e);
		}

	}

	private static class ServiceStatus {
		private boolean succeed;
		private String group;
		private String message;
/**
 * 
 * @return Boolean 
 */
		public boolean isSucceed() {
			return succeed;
		}
/**
 * 
 * @param succeed 
 */
		public void setSucceed(boolean succeed) {
			this.succeed = succeed;
		}
/**
 * 
 * @return String 
 */
		public String getGroup() {
			return group;
		}
/**
 * 
 * @param group 
 */
		public void setGroup(String group) {
			this.group = group;
		}
/**
 * 
 * @return String 
 */ 
		public String getMessage() {
			return message;
		}
/**
 * 
 * @param message 
 */
		public void setMessage(String message) {
			this.message = message;
		}

	}
	
}
