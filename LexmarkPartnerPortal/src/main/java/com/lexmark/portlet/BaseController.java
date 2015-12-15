/*
 * Copyright@ 2012. Developed for Lexmark International
 *
 * File Name         	: BaseController.java
 * Description     		: MPS
 * 
 * Modification History:
 *
 *
 */


package com.lexmark.portlet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.lexmark.util.MailUtil;
import com.lexmark.util.ServiceStatusUtil;
/**
 * This Class contains common methods
 * @version 2012
 * @author Wipro
 * */
public class BaseController {
	/**
	 * final variable Minus one
	 * */
	public static final String MINUES_ONE = "-1";
	private static Logger logger = LogManager.getLogger(BaseController.class);
	private static final String CATALINA_HOME = "catalina.home";
	private static final String URL_LEXMARK_CSS = File.separator + "webapps" + File.separator + "LexmarkServicesPortal"
			+ File.separator + "WEB-INF" + File.separator + "css" + File.separator + "lexmark.css";

	/**
	 * @param  request renderrequest
	 * @param  response renderresponse
	 * @param model model
	 * @return  String jsppage
	 * @throws Exception 
	 * */
	@RequestMapping(params = "action=showPopupErrorPage")
	public String showPopupErrorPage(RenderRequest request, RenderResponse response, Model model) throws Exception {
		return "popupError";
	}
	/**
	 * @param  request renderrequest
	 * @param  response renderresponse
	 * @param model 
	 * @return  String jspPage
	 * @throws Exception 
	 * */
	@RequestMapping(params = "action=showEmailNotificationSendingPage")
	public String showEmailNotificationSendingPage(RenderRequest request, RenderResponse response, Model model)
			throws Exception {
		return "emailCommon";
	}
	/**
	 * @param  request resourceRequest
	 * @param  response resourceresponse
	 * @param content mail content
	 * @param subject mail subject
	 * @param toAddress mailing address
	 * @param isHTML  
	 * 
	 * */
	@ResourceMapping("sendingEmail")
	public void sendingEmail(@RequestParam("content") String content, @RequestParam("subject") String subject,
			@RequestParam("toAddress") String toAddress, @RequestParam("isHTML") boolean isHTML,
			ResourceRequest request, ResourceResponse response) {
		boolean success = false;
		try {
			logger.debug("[START] sendingEmail=" + isHTML);
			String string = makeHTMLContent(content);
			logger.debug("content: " + content + ", subject: " + subject + ", toAddress:" + toAddress);
			if (toAddress != null && toAddress.length() > 0) {
				String[] addresses = toAddress.split(";");
				int len=addresses.length;
				for (int i = 0; i < len; i++) {
					addresses[i] = addresses[i].trim();
				}
				MailUtil.sendEmail(addresses, subject, string, isHTML);
			}
			success = true;
		} catch (Exception e) {
			logger.error("Exception occured in sending mail"+e.getMessage());
			success = false;
		}
		logger.debug("success: " + success);
		String errorCode = success ? "message.mail.sendEmail" : "exception.mail.sendEmail";
		logger.debug("[END] sendingEmail");
		ServiceStatusUtil.responseResult(response, errorCode, request.getLocale());
	}
	/**
	 * @param  toAddress mailing address
	 * @param  subject mailing subject
	 * @param  content mailing content
	 * @param  isHTML 
	 * @return  boolean flag
	 * */
	public boolean sendingEmail(String toAddress, String subject, String content, boolean isHTML) {
		String string = makeHTMLContent(content);

		logger.debug("[START] Send Email" + isHTML);
		if (toAddress != null && toAddress.length() > 0) {
			String[] addresses = toAddress.split(";");
			int len=addresses.length;
			for (int i = 0; i < len; i++) {
				addresses[i] = addresses[i].trim();
			}
			try {
				MailUtil.sendEmail(addresses, subject, string, isHTML);
			} catch (Exception ex) {
				logger.error(ex.getMessage());
				return false;
			}
		}
		logger.debug("[END] Send Email" + isHTML);
		return true;
	}
	/**
	 * @param  content htmlcontent
	 * @return  string
	 * */
	private String makeHTMLContent(String content) {
		String template = "<html><head><style type=\"text/css\">";
		StringBuilder sb = new StringBuilder();
		sb.append(template);
		sb.append("<!-- \n");
		sb.append(getLexmarkCssContent());
		sb.append("-->\n</style>\n");
		sb.append("</head><body><table><tr><td>");
		sb.append(content);
		sb.append("</td></tr></table></body></html>");
		String string = sb.toString();
		return string;
	}

	/**
	 * get the lexmark.css content as String
	 * 
	 * @return string
	 */
	private String getLexmarkCssContent() {
		StringBuilder sb = new StringBuilder();
		FileReader fr =null;
		BufferedReader br=null;
		try {
			fr= new FileReader(System.getProperty(CATALINA_HOME) + URL_LEXMARK_CSS);
			br = new BufferedReader(fr);
			String s=null;
			while ((s = br.readLine()) != null) {
				sb.append(s);
			}
			br.close();
			fr.close();
		} catch (IOException e) {
			logger.error("error occured while getting css content"+e.getMessage());
			
		}
		return sb.toString();
	}

}
