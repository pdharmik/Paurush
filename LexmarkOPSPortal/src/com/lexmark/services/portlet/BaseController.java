package com.lexmark.services.portlet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.portlet.PortletRequest;
import javax.portlet.PortletSession;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.lexmark.contract.UserGridSettingContract;
import com.lexmark.domain.EntitlementRoleMapping;
import com.lexmark.result.UserGridSettingResult;
import com.lexmark.service.api.EntitlementRoleMappingService;
import com.lexmark.service.api.ServiceRequestLocale;
import com.lexmark.service.impl.real.jdbc.UserGridSettingServiceImpl;
import com.lexmark.services.util.EntitlementRolesUtil;
import com.lexmark.services.util.LexmarkUserUtil;
import com.lexmark.services.util.MailUtil;
import com.lexmark.services.util.ServiceStatusUtil;
import com.lexmark.services.util.XmlOutputGenerator;
import com.lexmark.services.util.XmlOutputGeneratorForDocument;
import com.lexmark.services.util.cm.XMLOutputGeneratorUtil;

public class BaseController {

	private static Logger logger = LogManager.getLogger(BaseController.class);
	private static final String CATALINA_HOME = "catalina.home";
	private static final String URL_LEXMARK_CSS = File.separator + "webapps" + File.separator + "LexmarkOPSPortal"
			+ File.separator + "WEB-INF" + File.separator + "css" + File.separator + "lexmark.css";
	public static final String MINUES_ONE = "-1";
	
    @Autowired
    private ServiceRequestLocale serviceRequestLocale;
    
    @Autowired
    private EntitlementRoleMappingService entitlementRoleMappingService;

	@RequestMapping(params = "action=showPopupErrorPage")
	public String showPopupErrorPage(RenderRequest request, RenderResponse response, Model model) throws Exception {
		return "popupError";
	}

	@RequestMapping(params = "action=showEmailNotificationSendingPage")
	public String showEmailNotificationSendingPage(RenderRequest request, RenderResponse response, Model model)
			throws Exception {
		return "emailCommon";
	}

	@ResourceMapping("sendingEmail")
	public void sendingEmail(@RequestParam("content") String content, @RequestParam("subject") String subject,
			@RequestParam("toAddress") String toAddress, @RequestParam("isHTML") boolean isHTML,
			ResourceRequest request, ResourceResponse response) {
		boolean success = false;
		try {
			String string = makeHTMLContent(content);
			logger.debug("-------------sending Email---------" + isHTML);
			logger.debug("content: " +content +", subject: " + subject +", toAddress:" + toAddress);
			if (toAddress != null && toAddress.length() > 0) {
				String[] addresses = toAddress.split(";");
				int addressLen = addresses.length;
				for (int i = 0; i < addressLen; i++) {
					addresses[i] = addresses[i].trim();
				}
				MailUtil.sendEmail(addresses, subject, string, isHTML);
			}
			success = true;
		} catch (Exception e) {
			success = false;
		}
		logger.debug("success: " + success);
		String errorCode = success ? "message.mail.sendEmail"
				: "exception.mail.sendEmail";
		
		/**Changed for MPS***/
		ServiceStatusUtil.writeResponseResult(response, errorCode, request.getLocale());
	}
	
	@ResourceMapping("saveCustomizedGridSetting")
	public void saveGridSetting(ResourceRequest request, ResourceResponse response) {
		boolean success = true;
		String gridId = "";
		String valuetoSave = "";
		String actualValue = "";
		String colsOrder = "";
		String colsWidth = "";
		String colsHidden = "";
		String colsSorting = "";
		gridId = request.getParameter("gridId");
		colsOrder = request.getParameter("colsOrder");
		colsWidth = request.getParameter("colsWidth");
		colsHidden = request.getParameter("colsHidden");
		colsSorting = request.getParameter("colsSorting");
		UserGridSettingContract contract = new UserGridSettingContract();
		contract.setGridId("com_"+gridId);
		contract.setColsHidden(colsHidden);
		contract.setColsOrder(colsOrder);
		contract.setColsSorting("");  //changed for removing grid sorting from DB
		contract.setColsWidth(colsWidth);
		try {
			contract.setUserNumber(LexmarkUserUtil.getUserId(request));
			logger.debug("-------------saveCustomizedGridSetting---------");
			UserGridSettingResult result;
			UserGridSettingServiceImpl userGridSettingService = new UserGridSettingServiceImpl();
			userGridSettingService.saveUserGridSettings(contract);
			success = true;
		} catch (Exception e) {
			success = false;
			logger.debug("Error ------------------------"+e.getMessage());
		}
		logger.debug("success: " + success);
		ServiceStatusUtil.responseResult(response, "message.grid.saving", request.getLocale());
	}

	@ResourceMapping("resetCustomizedGridSetting")
	public void resetGridSetting(ResourceRequest request, ResourceResponse response) {
		boolean success = true;
		try{
			logger.debug("=======================Start resetGridSetting ================= ");
			UserGridSettingContract contract = new UserGridSettingContract();
			contract.setUserNumber(LexmarkUserUtil.getUserId(request));
			contract.setGridId("com_"+request.getParameter("gridId").toString());
			UserGridSettingServiceImpl userGridSettingService = new UserGridSettingServiceImpl();
			userGridSettingService.deleteUserGridSettings(contract);
			logger.debug("=======================End resetGridSetting ===================== ");
		}catch(Exception e){
			success = false;
			logger.debug("Error ------------------------"+e.getMessage());
		}
		logger.debug("success: " + success);
		ServiceStatusUtil.responseResult(response, "message.grid.resetting", request.getLocale());
	}
	
	/**
	 * This method is for reset grid setting response xml for MPS
	 * @param request
	 * @param response
	 */
	@ResourceMapping("resetGridSettingForDMCM")
	public void resetGridSettingForDMCM(ResourceRequest request, ResourceResponse response)
	{
		boolean success = true;
		try{
			logger.debug("=======================Start resetGridSettingForDMCM ================= ");
			UserGridSettingContract contract = new UserGridSettingContract();
			contract.setUserNumber(LexmarkUserUtil.getUserId(request));
			contract.setGridId(request.getParameter("gridId").toString());
			UserGridSettingServiceImpl userGridSettingService = new UserGridSettingServiceImpl();
			userGridSettingService.deleteUserGridSettings(contract);
			logger.debug("=======================End resetGridSettingForDMCM ===================== ");
		}catch(Exception e){
			success = false;
			logger.debug("Error ------------------------"+e.getMessage());
		}
		logger.debug("success: " + success);
		ServiceStatusUtil.writeResponseResult(response, "message.grid.resetting", request.getLocale());
	}
	
	
	public void retrieveGridSetting(String gridId, RenderRequest request, RenderResponse response,Model model) {
		boolean success = true;
		UserGridSettingContract contract = new UserGridSettingContract();
		contract.setGridId("com_"+gridId);
		UserGridSettingResult result = new UserGridSettingResult();
		try {
			logger.debug("-------------retrieveCustomizedGridSetting---------");
			logger.debug("-------gridId="+contract.getGridId());	
			contract.setUserNumber(LexmarkUserUtil.getUserId(request));
			UserGridSettingServiceImpl userGridSettingService = new UserGridSettingServiceImpl();
			result = userGridSettingService.retrieveUserGridSettings(contract);
			logger.debug("==========gridId"+result.getGridId());
			logger.debug("==========colsFilter"+result.getColsFilter());
			logger.debug("==========colsHidden"+result.getColsHidden());
			logger.debug("==========colsOrder"+result.getColsOrder());
			logger.debug("==========colsWidth"+result.getColsWidth());
			logger.debug("==========userNumber"+result.getUserNumber());
			success = true;
		} catch (Exception e) {
			success = false;
			logger.debug("Error ------------------------"+e.getMessage());
		}
		finally{
			logger.debug("success: " + success);
			model.addAttribute("gridSettings",result);
		}
	}
	// Added for MPS Phase 2.1
	
	@SuppressWarnings("finally")
	public UserGridSettingResult retrieveGridSettingForHistory(String gridId, PortletRequest request) {
		boolean success = true;
		UserGridSettingContract contract = new UserGridSettingContract();
		contract.setGridId("com_"+gridId);
		UserGridSettingResult result = null;
		try {
			logger.debug("-------------retrieveCustomizedGridSetting---------");
			logger.debug("-------gridId="+contract.getGridId());	
			contract.setUserNumber(LexmarkUserUtil.getUserId(request));
			UserGridSettingServiceImpl userGridSettingService = new UserGridSettingServiceImpl();
			result = userGridSettingService.retrieveUserGridSettings(contract);
			logger.debug("==========gridId"+result.getGridId());
			logger.debug("==========colsFilter"+result.getColsFilter());
			logger.debug("==========colsHidden"+result.getColsHidden());
			logger.debug("==========colsOrder"+result.getColsOrder());
			logger.debug("==========colsWidth"+result.getColsWidth());
			logger.debug("==========userNumber"+result.getUserNumber());
			success = true;
		} catch (Exception e) {
			success = false;
			logger.debug("Error ------------------------"+e.getMessage());
		}
		finally{
			logger.debug("success: " + success);
			return result;
		}
	}
	
	/**
	 * This method is used to retrieve settings for Device Management History
	 * @param gridId
	 * @param request
	 * @param response
	 * @param model
	 */
	public void retrieveGridSettingForDMHistory(String gridId, RenderRequest request, 
			RenderResponse response,Model model) {
		boolean success = true;
		UserGridSettingContract contract = new UserGridSettingContract();
		contract.setGridId(gridId);
		UserGridSettingResult result = new UserGridSettingResult();
		try {
			logger.debug("-------------retrieveCustomizedGridSetting---------");
			logger.debug("-------gridId= "+contract.getGridId());	
			contract.setUserNumber(LexmarkUserUtil.getUserId(request));
			UserGridSettingServiceImpl userGridSettingService = new UserGridSettingServiceImpl();
			result = userGridSettingService.retrieveUserGridSettingsForDM(contract);
			logger.debug("==========gridId "+result.getGridId());
			logger.debug("==========colsFilter "+result.getColsFilter());
			logger.debug("==========colsHidden "+result.getColsHidden());
			logger.debug("==========colsOrder "+result.getColsOrder());
			logger.debug("==========colsWidth "+result.getColsWidth());
			logger.debug("==========userNumber "+result.getUserNumber());
			success = true;
		} catch (Exception e) {
			success = false;
			logger.debug("Error ------------------------"+e.getMessage());
		}
		finally{
			logger.debug("success: " + success);
			request.setAttribute("gridSettings_"+gridId,result);
			
		}
	}
	
	public boolean sendingEmail(String toAddress, String subject, String content, boolean isHTML) {
		String string = makeHTMLContent(content);

		logger.debug("-------------sending Email---------" + isHTML);
		if (toAddress != null && toAddress.length() > 0) {
			String[] addresses = toAddress.split(";");
			int addressLen = addresses.length;
			for (int i = 0; i < addressLen; i++) {
				addresses[i] = addresses[i].trim();
			}
			try {
				MailUtil.sendEmail(addresses, subject, string, isHTML);
			} catch (Exception ex) {
				logger.error(ex.getMessage());
				return false;
			}
		}
		return true;
	}

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
	 * @return
	 */
	private String getLexmarkCssContent() {
		StringBuilder sb = new StringBuilder();
		try {
			FileReader fr = new FileReader(System.getProperty(CATALINA_HOME) + URL_LEXMARK_CSS);
			BufferedReader br = new BufferedReader(fr);
			String s;
			while ((s = br.readLine()) != null) {
				sb.append(s);
			}
		} catch (IOException e) {
			logger.debug("IO Exception message" + e.getMessage());
		}
		return sb.toString();
	}
	
	/**
	 * 
	 * @param locale 
	 * @return
	 */
	public XmlOutputGenerator getXmlOutputGenerator(Locale locale) {
		XmlOutputGenerator xmlOutputGenerator = new XmlOutputGenerator(locale);
		xmlOutputGenerator.setServiceRequestLocale(serviceRequestLocale);
		return xmlOutputGenerator;
	}
	
	
	public XmlOutputGeneratorForDocument getXmlOutputGeneratorForDocument(PortletRequest request) {
		return new XmlOutputGeneratorForDocument(request);
	}
	
	/**
	 * 
	 * Added for XML output Generator Util class for MPS
	 */
	public  XMLOutputGeneratorUtil getXmlOutputGeneratorUtil(Locale locale)
	{
		XMLOutputGeneratorUtil xmlOutputGeneratorUtil = new XMLOutputGeneratorUtil(locale);
		xmlOutputGeneratorUtil.setServiceRequestLocale(serviceRequestLocale);
		return xmlOutputGeneratorUtil;
	}
	
	/**
	 * This method will return "True"/"False" based on Entitlement Shore Desc
	 * Checks if object is in session. If not in session retrieves data from cache 
	 * or makes call to database and stores the data in cache and flags in session.
	 * 
	 * @param PortletRequest request
	 * @param String entitlementShortDesc 
	 * @return String  
	 * @throws Exception
	 */
	
	@SuppressWarnings({ "unchecked", "static-access" })
	public String getEntitlementStatus(PortletRequest request, String entitlementShortDesc) throws Exception{
		String entitlementStatus = "False";
		PortletSession portletSession =request.getPortletSession();
		
		Map<String, String> entitlementMap = (Map<String, String>)portletSession.getAttribute("ENTITLEMENT_FLAG", portletSession.APPLICATION_SCOPE);
		if(entitlementMap != null){
			entitlementStatus = (String)entitlementMap.get(entitlementShortDesc);
			if((entitlementStatus == null) || entitlementStatus.equals("")){
				entitlementStatus ="False";
			}
		}else{
			HashMap<String, EntitlementRoleMapping> roles4EntitlementMap =entitlementRoleMappingService.retrieveRoles4Entitlement();
			List<String> userRoleList = LexmarkUserUtil.getUserRoleNameList(request);
			entitlementMap = EntitlementRolesUtil.retrieveEntitlement4User(userRoleList, roles4EntitlementMap);	
			portletSession.setAttribute("ENTITLEMENT_FLAG", entitlementMap, portletSession.APPLICATION_SCOPE);
			entitlementStatus = (String)entitlementMap.get(entitlementShortDesc);
		}
				
		return entitlementStatus;
	}
	// Added by Wipro Offshore team for generic logger statement for stack trace
	protected void logStackTrace(Throwable throwable)
	{
		logger.debug("Inside logstack trace");
		StringWriter writer = new StringWriter();
		throwable.printStackTrace(new PrintWriter(writer));
		logger.debug(writer.getBuffer().toString());
	}
	
}