package com.lexmark.services.hook;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringEscapeUtils;

import com.lexmark.constants.LexmarkConstants;
import com.lexmark.contract.LdapSecurityData;
import com.lexmark.util.JndiLookupUtil;
import com.lexmark.util.PropertiesMessageUtil;
import com.liferay.portal.kernel.events.Action;
import com.liferay.portal.kernel.events.ActionException;
import com.liferay.portal.kernel.util.ReleaseInfo;
import com.liferay.portal.model.User;
import com.liferay.portal.util.PortalUtil;

public class ServicePreVelocityVariables extends Action {

	// This hook is used to set velocity variables needed for Mark Direct redirection for pages and
	// Omniture variables.  Both are set in theme.
	@Override
	public void run(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
		LOG.log(Level.WARNING,"in here");
		HttpSession session = req.getSession();
		Map<String, Object> vmVariables;
		synchronized (session) {
			vmVariables = (Map)session.getAttribute("VM_VARIABLES");
			if(vmVariables==null) {
				vmVariables = new HashMap<String, Object>();
				session.setAttribute("VM_VARIABLES", vmVariables);
			}
		}
		req.setAttribute("VM_VARIABLES", vmVariables);
		
		if(vmVariables.containsKey(this.getClass().getName())) return;

		User user = null;
		try {
			user = PortalUtil.getUser(req);
		} catch (Exception e) {
			LOG.log(Level.WARNING, "Can't get User after login.", e);
			return;
		}
		if(user == null || user.isDefaultUser()) return;
		try {			
			//set roles that apply based on Siebel data
			LdapSecurityData ldapServiceResult = JndiLookupUtil.getLdapServiceResult();
			com.lexmark.userservice.UserService ldapUserService = new com.lexmark.userservice.UserService(ldapServiceResult.getUserName(), ldapServiceResult.getPassword(), ldapServiceResult.getLdapUrl(), ldapServiceResult.getJdbcJndiUrl());

			com.lexmark.userservice.User ldapuser = null;
			//Check to see if Lexmark DEV/Test admin user because it is not in LDAP
			if (!user.getEmailAddress().equalsIgnoreCase("test@liferay.com")){
				ldapuser = ldapUserService.getUserByEmail(user.getEmailAddress());
			}
			if(ldapuser==null || ldapuser.getEmail() == null) {
				LOG.log(Level.WARNING, "User not found in LDAP! " + user.getEmailAddress());
				vmVariables.put(this.getClass().getName(), Boolean.TRUE);
				return;
			}
			
			String roleString = "";
			if (ldapuser.getUserSegment().equalsIgnoreCase("customer")||ldapuser.getUserSegment().equalsIgnoreCase("partner")){
				vmVariables.put(LexmarkConstants.VM_THEME_MARKER + "ldapUserCountry", ldapuser.getCountry());
				vmVariables.put(LexmarkConstants.VM_THEME_MARKER + "ldapUserCompanyName", StringEscapeUtils.escapeJavaScript(ldapuser.getCompanyName()));
				vmVariables.put(LexmarkConstants.VM_THEME_MARKER + "ldapUserMdmId", ldapuser.getCompanyId());
				vmVariables.put(LexmarkConstants.VM_THEME_MARKER + "ldapUserMdmLevel", ldapuser.getUserLevel());
				for (String ldapRole : ldapuser.getRoles()){
					if (roleString.equals("")){
						roleString = ldapRole;
					}else{
						roleString = roleString + "," + ldapRole;
					}
				}
				vmVariables.put(LexmarkConstants.VM_THEME_MARKER + "ldapUserSegment", roleString);
			}else if (ldapuser.getUserSegment().equalsIgnoreCase("employee")){
				vmVariables.put(LexmarkConstants.VM_THEME_MARKER + "ldapUserCountry", ldapuser.getCountry());
				vmVariables.put(LexmarkConstants.VM_THEME_MARKER + "ldapUserCompanyName", "Lexmark");
				vmVariables.put(LexmarkConstants.VM_THEME_MARKER + "ldapUserMdmId", "Lexmark");
				vmVariables.put(LexmarkConstants.VM_THEME_MARKER + "ldapUserMdmLevel", "Employee");
				for (String ldapRole : ldapuser.getGroups()){
					if (roleString.equals("")){
						roleString = ldapRole;
					}else{
						roleString = roleString + "," + ldapRole;
					}						
				}
				vmVariables.put(LexmarkConstants.VM_THEME_MARKER + "ldapUserSegment", roleString);
			}else{
				//not sure what this would be if you are not customer, partner, or employee
				vmVariables.put(LexmarkConstants.VM_THEME_MARKER + "ldapUserCountry", "");
				vmVariables.put(LexmarkConstants.VM_THEME_MARKER + "ldapUserCompanyName", "");
				vmVariables.put(LexmarkConstants.VM_THEME_MARKER + "ldapUserMdmId", "");
				vmVariables.put(LexmarkConstants.VM_THEME_MARKER + "ldapUserMdmLevel", "");
				vmVariables.put(LexmarkConstants.VM_THEME_MARKER + "ldapUserSegment", ldapuser.getUserSegment());
			}
			vmVariables.put(LexmarkConstants.VM_THEME_MARKER + "markDirectHost",
					PropertiesMessageUtil.getPropertyMessage(
					"com.lexmark.services.resources.hostConfig",
					"markDirectHost", Locale.US));
			/*Changes added for MPS phase 2 to 
			 * send Server Instance and Server Name to Omniture
			 * */
			vmVariables.put(LexmarkConstants.VM_THEME_MARKER + "serverName", req.getServerName());
			vmVariables.put(LexmarkConstants.VM_THEME_MARKER + "instanceName", ReleaseInfo.getServerInfo());
			LOG.log(Level.INFO,"ServerName is="+req.getServerName()+"InstanceName is ="+ReleaseInfo.getServerInfo());
			vmVariables.put(LexmarkConstants.VM_THEME_MARKER + "userAuthenticated", true);
			vmVariables.put(this.getClass().getName(), Boolean.TRUE);
		} catch (Exception e) {
			Throwable t = e;
			while(t.getCause()!=null) t = t.getCause();
			throw new RuntimeException("Unable to set theme velocity variables",e);
		}
	}

	private static final Logger LOG = Logger.getLogger(ServicePreVelocityVariables.class.getName());
}
