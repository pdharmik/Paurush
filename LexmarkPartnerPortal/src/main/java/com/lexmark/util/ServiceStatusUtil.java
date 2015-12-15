package com.lexmark.util;

import static com.lexmark.constants.LexmarkPPConstants.ERROR_MESSAGE_BUNDLE;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import javax.portlet.ResourceResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.ui.Model;

import com.lexmark.enums.DebriefStatusEnum;
import com.lexmark.enums.RequestSubStatusEnum;
import com.lexmark.enums.ServiceRequestTypeEnum;

public class ServiceStatusUtil {
	private static Logger logger = LogManager.getLogger(ServiceStatusUtil.class);

	public static void checkServiceStatus(Model model, String serviceStatus, Locale locale,
			boolean returnSuccessMessages) {
		checkServiceStatus(model, serviceStatus, locale, null, returnSuccessMessages);
	}

	public static boolean isRequestAbleToBeUpdate(String requestSubStatus, String debriefStatus, String requestType) {
		if (RequestSubStatusEnum.PROCESSING_REQUEST.getValue().equals(requestSubStatus)) {
			return false;
		}
		//Changes in MPS 2.1
		if (StringUtils.isBlank((requestSubStatus))) {
			return true;
		}
		//Ends changes MPS 2.1
		boolean result = true;
		if (DebriefStatusEnum.FAILED_VALIDATION.getValue().equals(debriefStatus)) {
			result = checkRequestSubStatusForUpdate(requestSubStatus, requestType);
		} else if (DebriefStatusEnum.VALIDATED.getValue().equals(debriefStatus)) {
			result = false;
		} else if (StringUtil.isStringEmpty(debriefStatus)) {
			if (RequestSubStatusEnum.PROCESSING_REQUEST.getValue().equals(requestSubStatus)) {
				result = false;
			} else {
				result = checkRequestSubStatusForUpdate(requestSubStatus, requestType);
			}
		} else {
			result = false;
		}
		return result;
	}

	public static boolean isRequestAbleToBeDebrief(String requestSubStatus, String debriefStatus) {
		if (RequestSubStatusEnum.PROCESSING_REQUEST.getValue().equals(requestSubStatus)) {
			return false;
		}
		boolean result = true;
		if (DebriefStatusEnum.FAILED_VALIDATION.getValue().equals(debriefStatus)) {
			result = checkRequestSubStatusForDebrief(requestSubStatus);
		} else if (DebriefStatusEnum.VALIDATED.getValue().equals(debriefStatus)) {
			result = false;
		} else if (StringUtil.isStringEmpty(debriefStatus)) {
			if (RequestSubStatusEnum.PROCESSING_REQUEST.getValue().equals(requestSubStatus)) {
				result = false;
			} else {
				result = checkRequestSubStatusForDebrief(requestSubStatus);
			}
		} else {
			result = false;
		}
		return result;
	}

	public static boolean checkRequestSubStatusForUpdate(String requestSubStatus, String requestType) {
		if (StringUtil.isStringEmpty(requestSubStatus)) {
			return false;
		} else if (RequestSubStatusEnum.CLAIM_DENIED.getValue().equals(requestSubStatus)
				|| RequestSubStatusEnum.SERVICE_ACTION_COMPLETE.getValue().equals(requestSubStatus)
				|| RequestSubStatusEnum.SURVEY_1ST_ATTEMPT_NOT_SUCCESS.getValue().equals(requestSubStatus)
				|| RequestSubStatusEnum.SURVEY_1ST_ATTEMPT_SUCCESS.getValue().equals(requestSubStatus)
				|| RequestSubStatusEnum.SURVEY_2ND_ATTEMPT_NOT_SUCCESS.getValue().equals(requestSubStatus)
				|| RequestSubStatusEnum.SURVEY_2ND_ATTEMPT_SUCCESS.getValue().equals(requestSubStatus)
				|| RequestSubStatusEnum.SURVEY_READY.getValue().equals(requestSubStatus)
				|| RequestSubStatusEnum.WRONG_PARTS_RECOMMENDED.getValue().equals(requestSubStatus)
				|| RequestSubStatusEnum.CLAIM_SUBMITTED.getValue().equals(requestSubStatus)
				|| RequestSubStatusEnum.CLAIM_CANCELLED.getValue().equals(requestSubStatus)
				|| RequestSubStatusEnum.PENDING_LEXMARK_REVIEW.getValue().equals(requestSubStatus)
				|| (ServiceRequestTypeEnum.CLAIM_REQUEST.getValue().equals(requestType) && RequestSubStatusEnum.PENDING_SP_ACKNOWLEDGEMENT
						.getValue().equals(requestSubStatus))) {
			return false;
		} else {
			return true;
		}
	}

	public static boolean checkRequestSubStatusForDebrief(String requestSubStatus) {
		if (StringUtil.isStringEmpty(requestSubStatus)) {
			return false;
		} else if (RequestSubStatusEnum.CANCELLATION_ACKNOWLEDGED.getValue().equals(requestSubStatus)
				|| RequestSubStatusEnum.CLAIM_DENIED.getValue().equals(requestSubStatus)
				|| RequestSubStatusEnum.SURVEY_READY.getValue().equals(requestSubStatus)
				|| RequestSubStatusEnum.NOT_ACCEPTED.getValue().equals(requestSubStatus)
				|| RequestSubStatusEnum.SERVICE_ACTION_COMPLETE.getValue().equals(requestSubStatus)
				|| RequestSubStatusEnum.SURVEY_1ST_ATTEMPT_NOT_SUCCESS.getValue().equals(requestSubStatus)
				|| RequestSubStatusEnum.SURVEY_1ST_ATTEMPT_SUCCESS.getValue().equals(requestSubStatus)
				|| RequestSubStatusEnum.SURVEY_2ND_ATTEMPT_NOT_SUCCESS.getValue().equals(requestSubStatus)
				|| RequestSubStatusEnum.SURVEY_2ND_ATTEMPT_SUCCESS.getValue().equals(requestSubStatus)
				|| RequestSubStatusEnum.CLAIM_SUBMITTED.getValue().equals(requestSubStatus)
				|| RequestSubStatusEnum.CLAIM_CANCELLED.getValue().equals(requestSubStatus)
				|| RequestSubStatusEnum.PENDING_LEXMARK_REVIEW.getValue().equals(requestSubStatus)
				|| RequestSubStatusEnum.PENDING_SP_ACKNOWLEDGEMENT.getValue().equals(requestSubStatus)) {
			return false;
		} else {
			return true;
		}
	}

	private static ServiceStatus strToStatus(String serviceStatus, Locale locale, Object params[]) {
		boolean succeed = serviceStatus.startsWith("message.");
		int idxOfDot1 = serviceStatus.indexOf('.');
		int idxOfDot2 = idxOfDot1 < 0 ? -1 : serviceStatus.indexOf('.', idxOfDot1 + 1);
		String groupCode = idxOfDot2 < 0 ? null : serviceStatus.substring(0, idxOfDot2);
		String message = ErrorMessageUtil.getErrorMessage(ERROR_MESSAGE_BUNDLE, serviceStatus, params, locale);
		String groupMessage = "";
		if (groupCode != null) {
			groupMessage = ErrorMessageUtil.getErrorMessage(ERROR_MESSAGE_BUNDLE, groupCode, params, locale);
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

	public static void responseResult(ResourceResponse response, String errorCode, Locale locale) {
		responseResult(response, errorCode, locale, null);
	}

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
		if (returnData != null) {
			sbuffer.append("\t<data>").append(XMLEncodeUtil.escapeXML(returnData)).append("</data>\n");
		}
		sbuffer.append("</result>");
		response.setCharacterEncoding("utf-8");
		response.setProperty(ResourceResponse.HTTP_STATUS_CODE, status.isSucceed() ? "200" : "500");
		response.setContentType("text/xml");
		OutputStream portletOutputStream;
		try {
			portletOutputStream = response.getPortletOutputStream();
			String messageXML = sbuffer.toString();
			logger.debug(messageXML);
			portletOutputStream.write(messageXML.getBytes());
		} catch (IOException e) {
			throw new RuntimeException("Failed to write response!", e);
		}

	}

	public static void responseResult(ResourceResponse response, String groupCode, String messageCode, Locale locale) {
		response.setProperty("Cache-Control", "max-age=0,no-cache,no-store");
		response.setProperty("Expires", "max-age=0,no-cache,no-store");

		String group = ErrorMessageUtil.getErrorMessage(ERROR_MESSAGE_BUNDLE, groupCode, locale);
		String message = ErrorMessageUtil.getErrorMessage(ERROR_MESSAGE_BUNDLE, messageCode, locale);
		StringBuffer sbuffer = new StringBuffer();
		sbuffer.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
		boolean isSuccess = messageCode.startsWith("message.");
		sbuffer.append("<result succeed=\"").append(isSuccess).append("\">\n");
		sbuffer.append("\t<group>").append(group).append("</group>\n");
//		sbuffer.append("\t<message>").append(message).append("</message>\n");
		// Fix by Service Portal AMS LEX:AIR00062572
		sbuffer.append("\t<message>").append(XMLEncodeUtil.escapeXML(message)).append("</message>\n");
		sbuffer.append("</result>");
		response.setCharacterEncoding("utf-8");
		response.setProperty(ResourceResponse.HTTP_STATUS_CODE, isSuccess ? "200" : "500");
		response.setContentType("text/xml");
		OutputStream portletOutputStream;
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

		public boolean isSucceed() {
			return succeed;
		}

		public void setSucceed(boolean succeed) {
			this.succeed = succeed;
		}

		public String getGroup() {
			return group;
		}

		public void setGroup(String group) {
			this.group = group;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}

	}

}
