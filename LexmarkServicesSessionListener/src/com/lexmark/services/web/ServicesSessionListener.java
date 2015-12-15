/**
 * 
 */
package com.lexmark.services.web;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.lexmark.constants.LexmarkConstants;
import com.lexmark.service.api.CrmSessionHandle;
import com.lexmark.service.api.GlobalService;
import com.liferay.portal.kernel.events.ActionException;
import com.liferay.portal.kernel.events.SessionAction;

/**
 * Release the crm session when the web session is destroyed.
 *
 */
public class ServicesSessionListener extends SessionAction {
	protected static final Log logger = LogFactory.getLog(ServicesSessionListener.class);


	@Override
	public void run(HttpSession webSession) throws ActionException {
		logger.info("sessionDestroyed=" + webSession.getId());
		try {
			Object handleObject = webSession.getAttribute(LexmarkConstants.SIEBEL_SESSION_ID);
			WebApplicationContext springAppContext = WebApplicationContextUtils.getWebApplicationContext(webSession.getServletContext());
			if (handleObject != null && handleObject instanceof CrmSessionHandle && springAppContext != null) {
				GlobalService globalService = springAppContext.getBean("GlobalService", GlobalService.class);
				if (globalService != null) {
					globalService.releaseSessionHandle((CrmSessionHandle) handleObject);
				}
			}
		}
		catch (Exception e) {
			// Squash all
			logger.info("Squashed exception on session destroy", e);
		}
	}

}
