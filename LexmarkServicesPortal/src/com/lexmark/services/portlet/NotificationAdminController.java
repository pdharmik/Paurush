package com.lexmark.services.portlet;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.lexmark.constants.LexmarkConstants;
import com.lexmark.contract.DeleteNotificationContract;
import com.lexmark.contract.NotificationDetailContract;
import com.lexmark.contract.NotificationDisplayOrderContract;
import com.lexmark.contract.SaveNotificationContract;
import com.lexmark.domain.LexmarkTransaction;
import com.lexmark.domain.Notification;
import com.lexmark.domain.SupportedLocale;
import com.lexmark.result.AdminNotificationListResult;
import com.lexmark.result.DeleteNotificationResult;
import com.lexmark.result.NotificationDetailResult;
import com.lexmark.result.NotificationDisplayOrderResult;
import com.lexmark.result.SaveNotificationResult;
import com.lexmark.result.SupportedLocaleListResult;
import com.lexmark.service.api.NotificationService;
import com.lexmark.service.api.ServiceRequestLocale;
import com.lexmark.services.form.NotificationDetailForm;
import com.lexmark.services.util.ContractFactory;
import com.lexmark.services.util.PortalSessionUtil;
import com.lexmark.services.util.ServiceStatusUtil;
import com.lexmark.util.CollectionSorter;
import com.lexmark.util.PerformanceTracker;

@Controller
@RequestMapping("VIEW")
public class NotificationAdminController extends BaseController {
	private static Logger logger = LogManager.getLogger(NotificationAdminController.class);
	private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("MM/dd/yyyy");
	private static final String ERROR_PREFIX = "exception.notification.";
	private static final String MESSAGE_PREFIX = "message.notification.";

	@Autowired
	private NotificationService notificationService;
	
	@Autowired
	private ServiceRequestLocale serviceRequestLocaleService;
	
	/**
	 * @param model 
	 * @param request 
	 * @param response 
	 * @return String 
	 * @throws Exception 
	 */
	@RequestMapping
	public String showNotificationAdminDefaultPage(Model model, RenderRequest request,
            RenderResponse response) throws Exception {
		return "notificationAdmin/notificationAdminList";
	}
	
	/**
	 * @param model 
	 * @param request 
	 * @param response 
	 * @return String 
	 * @throws Exception 
	 */
	@RequestMapping(params = "action=showNotificationDetail")
	public String showNotificationDetail(Model model, RenderRequest request,
			RenderResponse response) throws Exception {
		logger.debug("------------- Step 1---showNotificationDetail started---------["+System.nanoTime()+"]");
		if (!model.containsAttribute("notificationDetailForm")) {
    		NotificationDetailForm form = new NotificationDetailForm();
    		NotificationDetailContract contract;
    		String notificationId = request.getParameter("notificationId");
    		if ( notificationId != null && !notificationId.trim().equals("")) {
    			contract = ContractFactory.
    			        getNotificationDetailContract(Integer.valueOf(notificationId));
    		} else {
    			contract = new NotificationDetailContract();
    		}
			NotificationDetailResult result = notificationService.retrieveNotificationDetail(contract);
			SupportedLocaleListResult supportedLocaleListResult = serviceRequestLocaleService
					.retrieveSupportedLocaleList();
			List<SupportedLocale> supportedLocales = supportedLocaleListResult.getSupportedLocales();
			form.assemble(result.getNotificationDetail(), supportedLocales);
    		model.addAttribute("notificationDetailForm", form);
    		form.refreshSubmitToken(request);
		}
		else{
			NotificationDetailForm form = (NotificationDetailForm)model.asMap().get("notificationDetailForm");
			form.refreshSubmitToken(request);
		}
		
		logger.debug("------------- Step 1---showNotificationDetail end---------["+System.nanoTime()+"]");
		return "notificationAdmin/notificationDetail";
	}
	
	/**
	 * @param model 
	 * @param request 
	 * @param response 
	 * @param notificationDetailForm 
	 * @throws Exception 
	 */
	@RequestMapping(params = "action=saveNotificationDetail")
	public void saveNotificationDetail(Model model, ActionRequest request,
			ActionResponse response, @ModelAttribute("notificationDetailForm")
			NotificationDetailForm notificationDetailForm) throws Exception{
		boolean success = false;
		if(PortalSessionUtil.isDuplicatedSubmit(request, notificationDetailForm)){
			response.setRenderParameter("action", "");
		}
		else{
			try {
	    		Notification notification = notificationDetailForm.getNotificationDetail().getNotification();
	    		notification.setDisplayDate(DATE_FORMAT.parse(request.getParameter("displayDate")));
	    		notification.setRemoveDate(DATE_FORMAT.parse(request.getParameter("removeDate")));
	 //   		notificationDetailForm.removeEmptyDescription();
	    		SaveNotificationContract contract = ContractFactory.getSaveNotificationContract(
	    				notificationDetailForm.getNotificationDetail());
				SaveNotificationResult result = notificationService.saveNotificationDetail(contract);
				
				success = result.getSuccess();
			} catch (Exception e) {
				logger.debug(e.getMessage());
				success = false;
			}
			
			String status = (success ? MESSAGE_PREFIX : ERROR_PREFIX) + "submitNotification";
			ServiceStatusUtil.checkServiceStatus(model, status, request.getLocale(), true);
			
			if (success) {
				response.setRenderParameter("action", "");
			} else {
				response.setRenderParameter("action", "showNotificationDetail");
			}			
		}
	}
	
	/**
	 * @param request 
	 * @param response 
	 * @throws Exception 
	 */
	@ResourceMapping
	public void retrieveAdminNotificationList(ResourceRequest request, ResourceResponse response) throws Exception{
		logger.debug("------------- Step 1---retrieveAdminNotificationList started---------["+System.nanoTime()+"]");
		
		PortletSession session = request.getPortletSession();
		LexmarkTransaction lexmarkTran = PerformanceTracker.startTracking(LexmarkConstants.TARGET_SYSTEM_SIEBEL, "retrieveAdminNotificationList", 
				PortalSessionUtil.getMdmId(session), PortalSessionUtil.getServiceUser(session).getEmailAddress());
		AdminNotificationListResult notificationListResult = notificationService.retrieveAdminNotificationList();
		PerformanceTracker.endTracking(lexmarkTran);
		
		CollectionSorter sorter = new CollectionSorter();
		List<Notification> notificationList = sorter.sort(notificationListResult.getNotificationList(), "displayOrder:" + CollectionSorter.SORT_ASCENDING);
		
		String content = getXmlOutputGenerator(request.getLocale()).convertNotificationListToXML(notificationList,
				notificationList.size(), 0, request.getContextPath());
		
		PrintWriter out = response.getWriter();
		response.setContentType("text/xml");
		out.print(content);
		out.flush();
		out.close();
		logger.debug("------------- Step 1---retrieveAdminNotificationList end---------["+System.nanoTime()+"]");
	}
	
	/**
	 * @param notificationId 
	 * @param request 
	 * @param response 
	 * @throws Exception 
	 */
	@ResourceMapping("deleteNotificationDisplay")
	public void deleteNotificationDisplay(@RequestParam("notificationId") Integer notificationId,
			ResourceRequest request, ResourceResponse response) throws Exception {
		boolean success = false;
		try {
			logger.debug("-------------deleteNotificationDisplay started---------");
			DeleteNotificationContract contract = ContractFactory.
			        getDeleteNotificationContract(Integer.valueOf(notificationId));
			DeleteNotificationResult result = notificationService.deleteNotificationDisplay(contract); 
			logger.debug("-------------deleteNotificationDisplay end---------");
			success = result.getResult();
		} catch (Exception e) {
			success = false;
		}
		logger.debug("success: "+success);
		String errorCode = success?"message.notification.deleteNotificationDisplay"
				:"exception.notification.deleteNotificationDisplay";
		ServiceStatusUtil.responseResult(response, errorCode, request.getLocale());
	}
	
	/**
	 * @param displayOrder 
	 * @param increment 
	 * @param request 
	 * @param response 
	 * @throws Exception 
	 */
	@ResourceMapping("updateNotificationDisplayOrder")
	public void updateNotificationDisplayOrder(@RequestParam("displayOrder") Integer displayOrder,
			@RequestParam("increment") Integer increment,
			ResourceRequest request, ResourceResponse response) throws Exception {
		boolean success = false;
		try {
			logger.debug("-------------updateNotificationDisplayOrder started---------");
			NotificationDisplayOrderContract contract = ContractFactory.
					getNotificationDisplayOrderContract(displayOrder, increment);
			NotificationDisplayOrderResult result = notificationService.updateNotificationDisplayOrder(contract);
			logger.debug("-------------updateNotificationDisplayOrder end---------");
			success = result.getSuccessfulFlag();
		} catch (Exception e) {
			success = false;
		}
		logger.debug("success: "+success);
		String errorCode = success?"message.notification.updateNotificationDisplayOrder"
				:"exception.notification.updateNotificationDisplayOrder";
		ServiceStatusUtil.responseResult(response, errorCode, request.getLocale());
	}
}
