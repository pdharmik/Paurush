package com.lexmark.services.intercepter;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import javax.portlet.PortletSession;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.web.portlet.handler.HandlerInterceptorAdapter;

import com.lexmark.constants.LexmarkConstants;
import com.lexmark.domain.ServicesUser;
import com.lexmark.services.exception.AccountNotAssociatedException;
import com.lexmark.services.util.PortalSessionUtil;

public class AccountAssociateInterceptor extends HandlerInterceptorAdapter {
	private static Logger logger = LogManager.getLogger(AccountAssociateInterceptor.class);
	
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
		handleAccountAssociate(request);
		return true;
	}
	
	/**
	 * 
	 * @param portletRequest 
	 * @throws Exception 
	 */
	private void handleAccountAssociate(PortletRequest portletRequest)throws Exception{
		PortletSession portletSession =portletRequest.getPortletSession();
		
		if (PortalSessionUtil.isAccountAssociated(portletSession)){
			if(logger.isDebugEnabled()) {
				logger.debug("*********MdmId:"+ PortalSessionUtil.getMdmId(portletSession));
				logger.debug("*********MdmLevel:"+PortalSessionUtil.getMdmLevel(portletSession));
			}			
		}else{
			throw new AccountNotAssociatedException();
		}
		ServicesUser servicesUser  = (ServicesUser)portletSession.getAttribute(LexmarkConstants.SERVICES_USER, portletSession.APPLICATION_SCOPE);	
		if(servicesUser == null) {
			throw new AccountNotAssociatedException();
		}
	}


	
}
