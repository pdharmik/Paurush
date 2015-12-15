package com.lexmark.servlet;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.lexmark.contract.SaveServicesUserContract;
import com.lexmark.result.DAUserListResult;
import com.lexmark.result.SaveServicesUserResult;
import com.lexmark.service.api.UserAdminService;
import com.lexmark.util.ContractFactory;

/**
 * The servlet to save services user, after administrator selects a MDM ID/Level, or CHL Node.
 * @author roger.lin
 *
 */
public class SaveServicesUser extends HttpServlet {

	private static final long serialVersionUID = -1752878732668489277L;
	private static final Boolean SAVE_SUCCESS = Boolean.valueOf(true);
	private static final Boolean SAVE_FAILURE = Boolean.valueOf(false);
	
	private UserAdminService userAdminService;

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String mdmId = request.getParameter("mdmId");
		String mdmLevel = request.getParameter("mdmLevel");
		String chlNodeId = request.getParameter("chlNodeId");
		String nodeText = request.getParameter("nodeText");
		HttpSession session = request.getSession();
		DAUserListResult daUserListResult = (DAUserListResult)session.getAttribute("daUserListResult");
		if (daUserListResult.getUserNumberList()==null) {
			session.setAttribute("saveResult", SAVE_SUCCESS);
		}
		SaveServicesUserContract contract = ContractFactory.getSaveServicesUserContract(
				mdmId, mdmLevel, chlNodeId, daUserListResult.getUserNumberList());
		session.removeAttribute("daUserListResult");
		
		String url = "accountCHLView.jsp?operid=" + request.getParameter("operid") + "&type=" + request.getParameter("type") + "&jsession_num=" + request.getParameter("jsession_num");
		try {
			synchronized (this) {
				SaveServicesUserResult result = getUserAdminService().saveServicesUser(contract);
    			if (result.getResult()) {
    				session.setAttribute("saveResult", SAVE_SUCCESS);
    				session.setAttribute("nodeText", nodeText);
    			} else {
    				session.setAttribute("saveResult", SAVE_FAILURE);
    			}
			}
			response.sendRedirect(url);
		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("saveResult", SAVE_FAILURE);
			response.sendRedirect(url);
		}
	}
	
	private UserAdminService getUserAdminService() {
		if (userAdminService == null) {
			WebApplicationContext springAppContext = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
			userAdminService = springAppContext.getBean("userAdminService", UserAdminService.class);
		}
		return userAdminService;
	}
}
