package com.lexmark;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.lexmark.constants.LexmarkConstants;
import com.lexmark.contract.DAUserListContract;
import com.lexmark.contract.ServicesUserContract;
import com.lexmark.domain.ServicesUser;
import com.lexmark.result.DAUserListResult;
import com.lexmark.service.api.UserAdminService;
import com.lexmark.service.api.UserService;
import com.lexmark.util.ContractFactory;
import com.lexmark.util.StringUtil;

/**
 * This service is used to retrieve mdm id list, mdm level, user number list.
 *
 */
public class IDMUserService {
	private static Logger logger = Logger.getLogger(IDMUserService.class);

	private String operId;
	private String sessionNum;
	private String nodeId;
	
	private UserAdminService userAdminService;
	private UserService userService;
	private HttpSession webSession;
	
	public DAUserListResult getDAUser() {
		
		logger.debug("********* IDM CHL BUILD_VERSION = " + 
				 LexmarkConstants.PORTLET_BUILD_VERSION + " ***************");
		
		DAUserListContract contract = ContractFactory.getDAUserListContract(Integer.valueOf(operId), sessionNum);
		DAUserListResult result = null;
		try {
			result = getUserAdminService().retrieveDAUserList(contract);

			if (result != null && result.getMdmLevel() != null) {
				
				List<String> userNumberList = result.getUserNumberList();
    			// retrieve services user only when one usernumber returned which is in Edit mode
    			if (result.getUserMode().equalsIgnoreCase(LexmarkConstants.USER_MODE_EDIT) &&
    					userNumberList.size() == 1) {
    				ServicesUserContract servicesUserContract = ContractFactory.getServicesUserContract(userNumberList.get(0));
    				//servicesUserContract.setEmailAddress("test@liferay.com");
    				ServicesUser user = getUserService().retrieveServicesUser(servicesUserContract).getServicesUser();
    				if (!StringUtil.isStringEmpty(user.getChlNodeId())) {
    					nodeId = user.getChlNodeId();
    				} else if (!StringUtil.isStringEmpty(user.getMdmId()) && !StringUtil.isStringEmpty(user.getMdmLevel())){
    					nodeId = user.getMdmId() + "_" + user.getMdmLevel();
    				}
    				result.setNodeId(nodeId);
    				if (logger.isDebugEnabled()) {
    					logger.debug("UserNumber" + result.getUserNumberList().get(0) + "'s current authorization is " + nodeId);
    				}
    			}
			} else {
				logger.error("Can not find any record with jsession_num:" +
						contract.getJSessionNum() + ", operid:" + contract.getOperId());
				throw new Exception("Can not find any info by the given parameters!");
			}
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
		}
		return result;
	}
	public void setOperId(String operId) {
		this.operId = operId;
	}
	public void setSessionNum(String sessionNum) {
		this.sessionNum = sessionNum;
	}
	public void setWebSession(HttpSession webSession) {
		this.webSession = webSession;
	}
	
	private UserAdminService getUserAdminService() {
		if (userAdminService == null) {
			WebApplicationContext springAppContext = WebApplicationContextUtils.getWebApplicationContext(webSession.getServletContext());
			userAdminService = springAppContext.getBean("userAdminService", UserAdminService.class);
		}
		return userAdminService;
	}
	
	private UserService getUserService() {
		if (userService == null) {
			WebApplicationContext springAppContext = WebApplicationContextUtils.getWebApplicationContext(webSession.getServletContext());
			userService = springAppContext.getBean("userService", UserService.class);
		}
		return userService;
	}
}
