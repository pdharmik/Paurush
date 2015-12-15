package com.lexmark.portlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;

import javax.portlet.PortletRequest;
import javax.portlet.PortletSession;
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
import com.lexmark.contract.UserGridSettingContract;
import com.lexmark.result.UserGridSettingResult;
import com.lexmark.service.api.UserGridSettingService;
import com.lexmark.service.impl.real.jdbc.UserGridSettingServiceImpl;
import com.lexmark.util.JsonUtil;
import com.lexmark.util.LexmarkUserUtil;
import com.lexmark.util.PortalSessionUtil;
import com.lexmark.util.ServiceStatusUtil;

/**
 * @author wipro
 * @version 2.1 
 */

@Controller
@RequestMapping("VIEW")
public class GridSettingController {
	private static Logger logger = LogManager.getLogger(GridSettingController.class);

	@Autowired
	private UserGridSettingService userGridSettingService;

	/**
	 * @param request 
	 * @param response 
	 */
	@ResourceMapping("saveCustomizedGridSetting")
	public void saveGridSetting(ResourceRequest request, ResourceResponse response) {
		logger.debug("[START] saveGridSetting");
		
		//Code change start by CI-5 for req. ID-2124
		String gridId = request.getParameter("gridId");
		UserGridSettingContract contract = new UserGridSettingContract();
		contract.setGridId(request.getParameter("gridId"));
		contract.setColsHidden(request.getParameter("colsHidden"));
		contract.setColsOrder(request.getParameter("colsOrder"));
		if("gridRLVRequestList".equalsIgnoreCase(gridId)) {
			contract.setColsSorting("1,des");
		}
		else {
			contract.setColsSorting(request.getParameter("colsSorting"));
		}
		
		contract.setColsWidth(request.getParameter("colsWidth"));
       //Code change End by CI-5 for req. ID-2124
		
		boolean success = true;
		try {
			contract.setUserNumber(LexmarkUserUtil.getUserId(request));
			userGridSettingService.saveUserGridSettings(contract);
			success = true;
		} catch (Exception e) {
			success = false;
			logger.debug("[!!!!!!ERROR !!!!!!!!!!]" + e.getMessage());
		}

		logger.debug("success = " + success);
		logger.debug("[END] saveGridSetting");
		ServiceStatusUtil.responseResult(response, "message.grid.saving", request.getLocale());
	}

	/**
	 * @param request 
	 * @param response 
	 */
	@ResourceMapping("resetCustomizedGridSetting")
	public void resetGridSetting(ResourceRequest request, ResourceResponse response) {
		logger.debug("[START] resetCustomizedGridSetting");
		boolean success = true;
		UserGridSettingContract contract = new UserGridSettingContract();

		try {
			contract.setUserNumber(LexmarkUserUtil.getUserId(request));
			contract.setGridId(request.getParameter("gridId"));
			userGridSettingService.deleteUserGridSettings(contract);
		} catch (Exception e) {
			success = false;
			logger.debug("[!!!!!!ERROR !!!!!!!!!!" + e.getMessage() + "]");
		}
		logger.debug("success = " + success);
		logger.debug("[END] resetGridSetting");

		clearGridQueryParams(request);
		ServiceStatusUtil.responseResult(response, "message.grid.resetting", request.getLocale());
	}

	/**
	 * @param request 
	 * @param response 
	 */
	@ResourceMapping("retrieveQueryParams")
	public void retrieveQueryParams(ResourceRequest request, ResourceResponse response) {
		String gridId = request.getParameter("gridId");
		String sessionKey = generateQueryParamsKey(request.getLocale(), gridId);
		String queryParams = getQueryParams(request.getPortletSession(), sessionKey);
		StringBuilder json = new StringBuilder();
		json.append("{\"status\":\"success\"");
		if (queryParams != null){
			json.append(",\"data\":" + queryParams + "");
		}
		json.append("}");
		try {
			final PrintWriter out = response.getWriter();
			out.write(json.toString());
			out.flush();
			out.close();
		} catch (IOException e) {
			logger.error("IOException while invoking response#getWriter()," + e.getMessage());
		}
	}

	/**
	 * @param session 
	 * @param sessionKey 
	 * @return   string 
	 */
	private String getQueryParams(PortletSession session, String sessionKey) {
		if (session.getAttribute(sessionKey) != null){
			return (String) session.getAttribute(sessionKey);
		}
		return null;
	}

	/**
	 * @param request 
	 * @param response 
	 */
	@ResourceMapping("saveGridQueryParams")
	public void saveGridQueryParams(ResourceRequest request, ResourceResponse response) {
		String queryParams = request.getParameter("queryParams");
		String gridId = request.getParameter("gridId");
		response.setContentType("text/plain");
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			logger.error("IOException while invoking response#getWriter()," + e.getMessage());
			return;
		}
		if (StringUtils.isEmpty(queryParams)) {
			out.write("{error:\"queryParams required\"}");
			return;
		}
		if (StringUtils.isEmpty(gridId)) {
			out.write("{error:\"gridId required\"}");
			return;
		}
		String sessionKey = generateQueryParamsKey(request.getLocale(), gridId);
		PortletSession session = request.getPortletSession();

		session.setAttribute(sessionKey, queryParams);
		out.write("success");
		out.flush();
		out.close();
		logger.info("Grid query params of sessionKey[" + sessionKey
				+ "] was successfully saved, The associated account is " + PortalSessionUtil.getEmailAddress(session));
	}

	/**
	 * @param locale 
	 * @param gridId 
	 * @return  string 
	 */
	private String generateQueryParamsKey(Locale locale, String gridId) {
		return LexmarkPPConstants.SESSION_KEY_QUERY_PARAMS_PREFIX + locale.getDisplayName() + "_" + gridId;
	}

	/**
	 * @param request 
	 */
	public void clearGridQueryParams(ResourceRequest request) {
		String gridId = request.getParameter("gridId");
		String sessionKey = generateQueryParamsKey(request.getLocale(), gridId);
		request.getPortletSession().removeAttribute(sessionKey);
	}

	/**
	 * @param gridId 
	 * @param request 
	 * @param model 
	 */
	public void retrieveGridSetting(String gridId, RenderRequest request, Model model) {
		logger.debug("[START] retrieve Grid Setting");
		UserGridSettingContract contract = new UserGridSettingContract();
		contract.setGridId(gridId);

		UserGridSettingResult result = new UserGridSettingResult();
		boolean success = true;
		try {
			logger.debug("[=============== GRID ID = " + contract.getGridId() + " ]");
			contract.setUserNumber(LexmarkUserUtil.getUserId(request));
			result = userGridSettingService.retrieveUserGridSettings(contract);
			logger.debug("[===============GRID ID=" + result.getGridId() + " ]");
			logger.debug("[===============COLSFILTERS=" + result.getColsFilter() + " ]");
			logger.debug("[===============COLSHIDDEN=" + result.getColsHidden() + " ]");
			logger.debug("[===============COLSORDER=" + result.getColsOrder() + " ]");
			logger.debug("[===============COLSWIDTH=" + result.getColsWidth() + " ]");
			logger.debug("[===============USERNUMBER=" + result.getUserNumber() + " ]");
			success = true;
		} catch (Exception e) {
			success = false;
			logger.debug("[!!!!!!ERROR !!!!!!!!!!]" + e.getMessage());
		} finally {
			logger.debug("success: " + success);
			model.addAttribute("gridSettings", result);
			request.setAttribute("gridSettingList", result);
		}
	}
	
	//Added
	/**
	 * @param gridId  
	 * @param request 
	 * @param response 
	 * @param model 
	 */
	public void retrieveGridSetting(String gridId, RenderRequest request, RenderResponse response,Model model) {
		boolean success = true;
		UserGridSettingContract contract = new UserGridSettingContract();
		contract.setGridId(gridId);
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
	// MPS 2.1 changes
	/**
	 * @param gridId 
	 * @param portletRequest 
	 * @return   string 
	 */
	@SuppressWarnings("finally")
	public UserGridSettingResult retrieveGridSettingForMassUpload(String gridId,PortletRequest portletRequest) {
		logger.debug("[START] retrieve retrieve GridSetting For Mass Upload");
		UserGridSettingContract contract = new UserGridSettingContract();
		contract.setGridId(gridId);

		UserGridSettingResult result = new UserGridSettingResult();
		boolean success = true;
		try {
			logger.debug("[=============== GRID ID = " + contract.getGridId() + " ]");
			contract.setUserNumber(LexmarkUserUtil.getUserId(portletRequest));
			result = userGridSettingService.retrieveUserGridSettings(contract);
			logger.debug("[===============GRID ID=" + result.getGridId() + " ]");
			logger.debug("[===============COLSFILTERS=" + result.getColsFilter() + " ]");
			logger.debug("[===============COLSHIDDEN=" + result.getColsHidden() + " ]");
			logger.debug("[===============COLSORDER=" + result.getColsOrder() + " ]");
			logger.debug("[===============COLSWIDTH=" + result.getColsWidth() + " ]");
			logger.debug("[===============USERNUMBER=" + result.getUserNumber() + " ]");
			success = true;
		} catch (Exception e) {
			success = false;
			logger.debug("[!!!!!!ERROR !!!!!!!!!!]" + e.getMessage());
			result=null;
			
		} finally {
			logger.debug("success: " + success);
			return result;
			
		}
	}
	
	/**
	 * @param resourceRequest  
	 * @param resourceResponse 
	 * @param gridId  
	 * This method retrieves grid setting for the hardware debrief grid 
	 */
	@ResourceMapping("getGridSettings")
	public void getGridSetting(ResourceRequest resourceRequest, ResourceResponse resourceResponse,
			@RequestParam("gridId") String gridId){
		
		UserGridSettingResult result =retrieveGridSettingForMassUpload(gridId, resourceRequest);
		String json=JsonUtil.generateJSONForGridSetting(result,resourceRequest.getLocale());
		
		try {
			final PrintWriter out = resourceResponse.getWriter();
			resourceResponse.setProperty("Cache-Control", "max-age=0,no-cache,no-store");
			resourceResponse.setProperty("Expires", "max-age=0,no-cache,no-store");
			resourceResponse.setContentType("UTF-8");
			out.write(json);
			out.flush();
			out.close();
		} catch (IOException e) {
			logger.error("IOException while invoking response#getWriter(),"
					+ e.getMessage());
		}
		
		
		
		
		
	}
}
