package com.lexmark.services.servlet;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import com.lexmark.contract.EmailNotificationDetailForNameContract;
import com.lexmark.domain.EmailNotification;
import com.lexmark.domain.EmailNotificationLocale;
import com.lexmark.domain.ReportNotifierTO;
import com.lexmark.result.EmailNotificationDetailResult;
import com.lexmark.service.api.EmailNotificationService;
import com.lexmark.service.api.ReportScheduleService;
import com.lexmark.services.util.ContractFactory;
import com.lexmark.services.util.MailUtil;
import com.lexmark.util.LocaleUtil;
import com.lexmark.util.PropertiesMessageUtil;
import com.lexmark.util.URLEncryptUtil;


public class ReportNotifierServletService {
	private static final String EMAIL_NOTIFICATION_FOR_REPORT_NOTIFIER = "PortalPublishNotification";
	private static final String DEFAULT_LOCALE_CODE = "en";
	private static Logger logger = LogManager.getLogger(ReportNotifierServlet.class);
	private static final String PDF_TYPE = "PDF";

	private static final long serialVersionUID = -1752878732668489277L;
	private ReportScheduleService  reportScheduleService;
	private EmailNotificationService  emailNotificationService;
	
	/**
	 * 
	 */
	public void notifyPublishing() {
		Map<String, StringBuilder> userReportList = new HashMap<String, StringBuilder>();
		Map<String, ReportFileUrlPair>  reportFiles = new HashMap<String, ReportFileUrlPair>();
		Map<Integer, String>  reportDefinitionNames = new HashMap<Integer, String>();
		List<ReportNotifierTO> reportList = reportScheduleService.queryPublishedReports();
		Map<String, String> userEmails = new HashMap<String, String>();
		
		for(ReportNotifierTO r : reportList) {
			
			// update status
			try {
				reportScheduleService.updateJobPublishedStatus(Long.valueOf(r.getRunlogId()));
			} catch (Exception ex) {
				logger.error(ex.getMessage());
			}
			
			// get localized report name
			Integer definitionId = r.getDefinitionId();
			if(reportDefinitionNames.get(definitionId) == null) {
				String reportName = reportScheduleService.getLocalizedReportName(definitionId, r.getLocaleCode());
				reportDefinitionNames.put(definitionId, reportName);
			}
			
			ReportFileUrlPair pair = reportFiles.get(r.getFileDataLink()); 
			if(pair == null) {
				pair = new ReportFileUrlPair();
				pair.setUserId(r.getUserNumber());
				pair.setDefinitionId(definitionId);
				
				reportFiles.put(r.getFileDataLink(), pair);
			}
			if(PDF_TYPE.equalsIgnoreCase(r.getFileType())) {
				pair.setPdfUrl(getEncryptedReportURL(r.getAWebUrl()));
			} else {
				pair.setXlsUrl(getEncryptedReportURL(r.getAWebUrl()));
			}
			
			if(userEmails.get(r.getUserNumber()) == null) {
				userEmails.put(r.getUserNumber(), r.getUserEmail());
			}
			
		}

		for(ReportFileUrlPair pair : reportFiles.values()) {
			StringBuilder sb = userReportList.get(pair.getUserId());
			if(sb == null) {
				sb = new StringBuilder();
				userReportList.put(pair.getUserId(), sb);
				// 
				//<table><tr><td>{0}</td><td>{1}</td><td>{2}</td><td>{3}</td></tr></table>
				sb.append("<table>");
			}
			//"<a href='{0}'>" + IMG_GRAYED_OPEN + contextPath + IMAGE_PATH + PDF_FILE_NAME + "\"/>" + "</a>"
			
			sb.append("<tr><td>")
			   .append("<a href='")
			   .append(pair.getPdfUrl())
			   .append("'/></td>");
			
			sb.append("<td>")
			   .append("<a href='")
			   .append(pair.getXlsUrl())
			   .append("'/></td>");
			
			sb.append("<td>")
			  .append(reportDefinitionNames.get(pair.getDefinitionId()))
			  .append("</td>");
			
			sb.append("</tr>");
			
		}
		
		for(String userNumber : userReportList.keySet()) {
			StringBuilder sb = userReportList.get(userNumber);
			sb.append("</table>");
		
			String email = userEmails.get(userNumber);
			if(email == null) {
				continue;
			} 
			Locale locale = Locale.ENGLISH;
			if(logger.isDebugEnabled()) {
				logger.debug("to user " + userNumber + " email address : " + email);
				logger.debug(" using locale " + LocaleUtil.getSupportLocaleCode(locale));
			}
			EmailNotificationLocale notification = getEmailNotificationLocale(locale);
			// If found the email template, then send the mail,  if not do not send the mail.
			if(notification != null) {
				sendEmail(notification, sb.toString(), email);
			}
		}
	}
	
	/**
	 * @param filePath 
	 * @return String 
	 */
	private String getEncryptedReportURL(String filePath) {
		String server = PropertiesMessageUtil.getPropertyMessage("com.lexmark.services.resources.reportURL", 
				"report_server", Locale.US);

		if(!server.endsWith("/")){
			server = server + "/";
		}

		if(filePath.startsWith("/")){
			filePath = filePath.substring(1);
		}

		String reportURL = server + filePath;

		return URLEncryptUtil.encrypt(reportURL, true);
	}
	
	/**
	 * @param emailNotificationLocale 
	 * @param reportList 
	 * @param toAddress 
	 */
	public void sendEmail(EmailNotificationLocale emailNotificationLocale, String reportList, String toAddress) {
		String content = emailNotificationLocale.getEmailHeader() + emailNotificationLocale.getEmailBody()
		  + reportList + emailNotificationLocale.getEmailFooter();

		
		String subject = emailNotificationLocale.getEmailSubject();
		MailUtil.sendEmail(toAddress, subject, content, true);
	}
	
	private EmailNotification emailNotification;
	/**
	 * @param locale 
	 * @return EmailNotificationLocale 
	 */
	public EmailNotificationLocale getEmailNotificationLocale(Locale locale) {
		
		if(emailNotification == null) {
			try {
				EmailNotificationDetailForNameContract contract = 
					ContractFactory.getEmailNotificationDetailForNameContract(EMAIL_NOTIFICATION_FOR_REPORT_NOTIFIER);
				EmailNotificationDetailResult result = emailNotificationService.retrieveEmailNotificationDetailByName(contract);
				emailNotification = result.getEmailNotification();
			} catch(Exception ex) {
				throw new RuntimeException(ex);
			}
		}
		if(emailNotification == null) {
			return null;
		}
		String localeCode = LocaleUtil.getSupportLocaleCode(locale);
		EmailNotificationLocale emailNotificationLocale = getEmailNotificationLocale(emailNotification.getEmailNotificationLocaleList(), localeCode);
		if(emailNotificationLocale == null) {
			emailNotificationLocale = getEmailNotificationLocale(emailNotification.getEmailNotificationLocaleList(), DEFAULT_LOCALE_CODE);
		}
		return emailNotificationLocale;
	}
	
	/**
	 * @param localeList 
	 * @param localeCode 
	 * @return EmailNotificationLocale 
	 */
	private EmailNotificationLocale getEmailNotificationLocale(List<EmailNotificationLocale> localeList, String localeCode) {
		for(EmailNotificationLocale l : emailNotification.getEmailNotificationLocaleList()) {
			if(localeCode.equalsIgnoreCase(l.getLocale().getSupportedLocaleCode()) &&
				l.getEmailSubject()!= null 	) {
				return l;
			}
		}
		return null;
	}
	
	
	/**
	 * @return ReportScheduleService 
	 */
	public ReportScheduleService getReportScheduleService() {
		return reportScheduleService;
	}
	/**
	 * @param reportScheduleService 
	 */
	public void setReportScheduleService(ReportScheduleService reportScheduleService) {
		this.reportScheduleService = reportScheduleService;
	}

	
	/**
	 * @return EmailNotificationService 
	 */
	public EmailNotificationService getEmailNotificationService() {
		return emailNotificationService;
	}

	/**
	 * @param emailNotificationService 
	 */
	public void setEmailNotificationService(
			EmailNotificationService emailNotificationService) {
		this.emailNotificationService = emailNotificationService;
	}


	private  class ReportFileUrlPair {
		String userId;
		String pdfUrl;
		String xlsUrl;
		Integer definitionId;
	
		
		/**
		 * @return Integer 
		 */
		public Integer getDefinitionId() {
			return definitionId;
		}
		/**
		 * @param definitionId 
		 */
		public void setDefinitionId(Integer definitionId) {
			this.definitionId = definitionId;
		}
		/**
		 * @return String 
		 */
		public String getUserId() {
			return userId;
		}
		/**
		 * @param userId 
		 */
		public void setUserId(String userId) {
			this.userId = userId;
		}
		/**
		 * @return String 
		 */
		public String getPdfUrl() {
			return pdfUrl;
		}
		/**
		 * @param pdfUrl 
		 */
		public void setPdfUrl(String pdfUrl) {
			this.pdfUrl = pdfUrl;
		}
		/**
		 * @return String 
		 */
		public String getXlsUrl() {
			return xlsUrl;
		}
		/**
		 * @param xlsUrl 
		 */
		public void setXlsUrl(String xlsUrl) {
			this.xlsUrl = xlsUrl;
		}
	}
}
