package com.lexmark.services.portlet;


import java.io.IOException;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletRequest;
import javax.portlet.PortletSession;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.lexmark.services.util.ChangeMgmtConstant;
import com.liferay.portal.kernel.util.JavaConstants;

@Controller
@RequestMapping("VIEW")
@SessionAttributes("serviceRequestConfirmationForm")
public class SimpleDeviceFinderController extends BaseController {

	private static Logger logger = LogManager.getLogger(SimpleDeviceFinderController.class);
	private static final String CREATE_MAP_REQUEST = "CREATE MAP REQUEST";
	@RequestMapping
	public String showDeviceListPage(Model model, RenderRequest request, RenderResponse response) throws Exception{
		logger.debug("-------------view simple device finder started---------");
		PortletSession session = request.getPortletSession();
		Map<?,?> requestAccessMap=(Map<?,?>)session.getAttribute(ChangeMgmtConstant.USERACCESSMAPATTRIBUTEFORSR, PortletSession.APPLICATION_SCOPE);
		Boolean simpleDeviceFinderFlag=false;
		if(requestAccessMap.get(CREATE_MAP_REQUEST) != null && "true".equalsIgnoreCase((String)requestAccessMap.get(CREATE_MAP_REQUEST)) ){
			simpleDeviceFinderFlag=true;
		}
		model.addAttribute("simpleDeviceFinderFlag", simpleDeviceFinderFlag);
		return "simpleDeviceFinder";
	}
	 
    @RequestMapping(params="action=doSearch")
    public void doSearch(ActionRequest request, ActionResponse response, Model model) throws IOException
    {
        setSessionSearchCriterias(request, response);
        response.sendRedirect(request.getParameter("deviceURL"));
    }
    
    private void setSessionSearchCriterias(ActionRequest request, ActionResponse response){
    	String searchName = request.getParameter("searchName");
    	String searchValue = request.getParameter("searchValue");
        PortletRequest portletRequest = (PortletRequest)request.getAttribute(JavaConstants.JAVAX_PORTLET_REQUEST);
        PortletSession portletSession = portletRequest.getPortletSession();
        portletSession.setAttribute("searchName", searchName, PortletSession.APPLICATION_SCOPE);
        portletSession.setAttribute("searchValue", searchValue, PortletSession.APPLICATION_SCOPE);
    }
}
