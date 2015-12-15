package com.lexmark.services.intercepter;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import javax.portlet.PortletSession;

//import org.apache.logging.log4j.Logger;
//import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.web.portlet.handler.HandlerInterceptorAdapter;

//import com.lexmark.constants.LexmarkConstants;
//import com.lexmark.domain.ServicesUser;
//import com.lexmark.services.exception.AccountNotAssociatedException;
import com.lexmark.services.util.PortalSessionUtil;

public class PerformanceInterceptor extends HandlerInterceptorAdapter {
	
	
	/**
	 * 
	 * @param request 
	 * @param response 
	 * @param handler 
	 * @return Boolean 
	 * @throws Exception 
	 */
	protected boolean preHandle(PortletRequest request, PortletResponse response, Object handler)
			throws Exception {
		handlePerformance(request);
		return true;
	}
	
	/**
	 * 
	 * @param portletRequest 
	 * @throws Exception 
	 */
	private void handlePerformance(PortletRequest portletRequest)throws Exception{
		PortletSession portletSession =portletRequest.getPortletSession();
		try{			
			if(portletSession!=null){
				if(PortalSessionUtil.getMdmId(portletSession)!=null){
					ThreadContext.put("mdmID", PortalSessionUtil.getMdmId(portletSession));
				}
				if(PortalSessionUtil.getMdmLevel(portletSession)!=null){
					ThreadContext.put("mdmLevel", PortalSessionUtil.getMdmLevel(portletSession));
				}
				if(PortalSessionUtil.getServiceUser(portletSession)!=null){
					ThreadContext.put("loginUser", PortalSessionUtil.getServiceUser(portletSession).getEmailAddress());
				}else if(PortalSessionUtil.getEmailAddress(portletSession)!=null){
					ThreadContext.put("loginUser", PortalSessionUtil.getEmailAddress(portletSession));
				}
				if(PortalSessionUtil.getServiceUser(portletSession)!=null){
					ThreadContext.put("userNumber", PortalSessionUtil.getServiceUser(portletSession).getUserNumber());
				}
			}
		}finally{
			
		}
	}	
}
