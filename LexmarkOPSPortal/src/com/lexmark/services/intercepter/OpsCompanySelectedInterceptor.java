package com.lexmark.services.intercepter;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

import org.springframework.web.portlet.handler.HandlerInterceptorAdapter;

import com.lexmark.services.exception.AccountNotAssociatedException;
import com.lexmark.services.exception.OPSCompanyNotAssociatedException;
import com.lexmark.services.util.PortalSessionUtil;

public class OpsCompanySelectedInterceptor extends HandlerInterceptorAdapter {
	
	protected boolean preHandle(PortletRequest request, PortletResponse response, Object handler)
	throws Exception {
		
		checkUserCompanySelected(request);
		return true;
	}
	
	private void checkUserCompanySelected(PortletRequest request){
		
		if(PortalSessionUtil.getOPSAccountSelected(request.getPortletSession())==null){
			throw new OPSCompanyNotAssociatedException();
		}	
	}

	
}
