package com.lexmark.services.portlet;

import java.util.List;

import javax.portlet.PortletSession;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.lexmark.constants.LexmarkConstants;
import com.lexmark.contract.UserNotificationContract;
import com.lexmark.contract.UserNotificationListContract;
import com.lexmark.domain.LexmarkTransaction;
import com.lexmark.domain.NotificationDetail;
import com.lexmark.result.UserNotificationListResult;
import com.lexmark.result.UserNotificationResult;
import com.lexmark.service.api.NotificationService;
import com.lexmark.services.form.NotificationForm;
import com.lexmark.services.util.ContractFactory;
import com.lexmark.services.util.PortalSessionUtil;
import com.lexmark.services.util.ServiceStatusUtil;
import com.lexmark.util.PerformanceTracker;

@Controller
@RequestMapping("VIEW")
public class SimpleNotificationController {
	private static Logger logger = LogManager.getLogger(SimpleNotificationController.class);

	@Autowired
	private NotificationService notificationService;
	
	/**
	 * @param model 
	 * @param request 
	 * @param response 
	 * @return String 
	 * @throws Exception 
	 */
	@RequestMapping
	public String showNotificationPage(Model model, RenderRequest request,
            RenderResponse response) throws Exception {
		
		logger.debug("------------- Step 1---showNotificationPage started---------["+System.nanoTime()+"]");
		PortletSession session = request.getPortletSession();
		LexmarkTransaction lexmarkTran = PerformanceTracker.startTracking(LexmarkConstants.TARGET_SYSTEM_SIEBEL, "retrieveUserNotificationList", 
				PortalSessionUtil.getMdmId(session), PortalSessionUtil.getServiceUser(session).getEmailAddress());
		
		UserNotificationListContract contract = ContractFactory.getUserNotificationListContrac(request, session);
		UserNotificationListResult notificationListResult = notificationService.retrieveUserNotificationList(contract);
		PerformanceTracker.endTracking(lexmarkTran);
		
		List<NotificationDetail>  notificationDetailList = notificationListResult.getNotificationDetailList();
		
		NotificationForm notificationForm = new NotificationForm();
		notificationForm.setUserId(PortalSessionUtil.getServiceUser(session).getUserNumber());
		notificationForm.setNotificationDetailList(notificationDetailList);
		
		model.addAttribute("notificationForm", notificationForm);
		
		logger.debug("------------- Step 1---showNotificationPage ended---------["+System.nanoTime()+"]");
		return "notificationAdmin/showNotificationPage";
	}
	
	/**
	 * @param notificationId 
	 * @param userId 
	 * @param request 
	 * @param response 
	 * @throws Exception 
	 */
	@ResourceMapping("hideUserNotification")
	public void hideUserNotification(@RequestParam("notificationId") Integer notificationId,
			@RequestParam("userId") String userId,
			ResourceRequest request, ResourceResponse response) throws Exception {
		boolean success = false;
		try {
			logger.debug("-------------hideUserNotification started---------");
			UserNotificationContract contract = ContractFactory.
					getHideUserNotificationContract(notificationId,userId);
			UserNotificationResult result = notificationService.hideUserNotification(contract);
			logger.debug("-------------hideUserNotification end---------");
			success = result.getResult();
		} catch (Exception e) {
			success = false;
		}
		logger.debug("success: "+success);
		String errorCode = success?"message.notification.hideUserNotification"
				:"exception.notification.hideUserNotification";
		ServiceStatusUtil.responseResult(response, errorCode, request.getLocale());
	}
}
