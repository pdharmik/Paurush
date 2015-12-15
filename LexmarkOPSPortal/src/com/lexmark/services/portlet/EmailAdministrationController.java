package com.lexmark.services.portlet;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.portlet.ResourceURL;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.lexmark.contract.EmailNotificationContract;
import com.lexmark.contract.EmailNotificationCreateContract;
import com.lexmark.contract.EmailNotificationDetailContract;
import com.lexmark.contract.EmailNotificationLocaleContract;
import com.lexmark.domain.EmailNotification;
import com.lexmark.domain.EmailNotificationLocale;
import com.lexmark.domain.SupportedLocale;
import com.lexmark.result.EmailNotificationDetailResult;
import com.lexmark.result.EmailNotificationListResult;
import com.lexmark.result.EmailNotificationLocaleResult;
import com.lexmark.result.EmailNotificationResult;
import com.lexmark.service.api.EmailNotificationService;
import com.lexmark.services.form.EmailNotificationDetailForm;
import com.lexmark.services.util.ContractFactory;
import com.lexmark.services.util.ServiceStatusUtil;

@Controller
@RequestMapping("VIEW")
public class EmailAdministrationController extends BaseController {
	private static Logger logger = LogManager.getLogger(EmailAdministrationController.class);
	private EmailNotificationDetailResult result;
	@Autowired
	private EmailNotificationService emailNotificationService;
	
	@RequestMapping
	public String showEmailAdministrationPage(Model model, RenderRequest request,
            RenderResponse response) throws Exception {
		return "emailAdministration/emailAdministrationPage";
	}
	
	@RequestMapping(params="action=viewEmailNotificationDetail")
	public String viewEmailNotificationDetail(Model model, RenderRequest request,
            RenderResponse response) throws Exception {
		
		EmailNotificationDetailContract contract = ContractFactory.getEmailNotificationDetailContract(request.getParameter("emailNotificationId").toString());
		result = emailNotificationService.retrieveEmailNotificationDetail(contract);
		
		EmailNotificationDetailForm emailNotificationDetailForm = new EmailNotificationDetailForm();
		
		emailNotificationDetailForm.setEmailNotificationId(result.getEmailNotification().getEmailNotificationId());
		emailNotificationDetailForm.setEmailName(result.getEmailNotification().getEmailName());
		emailNotificationDetailForm.setEmailDescription(result.getEmailNotification().getEmailDescription());
	
		EmailNotificationLocale emailNotificationLocale = new EmailNotificationLocale();
		
		EmailNotification emailnotification = new EmailNotification();
		SupportedLocale locale = new SupportedLocale();
		emailNotificationLocale.setLocale(locale);
		emailNotificationLocale.setEmailNotification(emailnotification);
		
		emailNotificationDetailForm.setEmailNotificationLocale(emailNotificationLocale);
		emailNotificationDetailForm.setEmailNotificationLocaleList(result.getEmailNotification().getEmailNotificationLocaleList());
		
		model.addAttribute("emailNotificationDetailForm", emailNotificationDetailForm);
		
		ResourceURL url = response.createResourceURL();
		url.setResourceID("showEmailNotificationDetailURL");
		
		model.addAttribute("showEmailNotificationDetailURL", url.toString());
		
		return "emailAdministration/emailNotificationDetailPage";
	}
	@RequestMapping(params="action=createEmailNotification")
	public String createEmailNotification(Model model, RenderRequest request,
            RenderResponse response) throws Exception {
		
		EmailNotificationDetailForm emailNotificationDetailForm = new EmailNotificationDetailForm();
		EmailNotificationLocale emailNotificationLocale = new EmailNotificationLocale();
		
		EmailNotification emailnotification = new EmailNotification();
		SupportedLocale locale = new SupportedLocale();
		emailNotificationLocale.setLocale(locale);
		emailNotificationLocale.setEmailNotification(emailnotification);
		
		emailNotificationDetailForm.setEmailNotificationLocale(emailNotificationLocale);
		
		EmailNotificationDetailContract contract = ContractFactory.getEmailNotificationDetailContract(null);
		result = emailNotificationService.retrieveEmailNotificationDetail(contract);
		emailNotificationDetailForm.setEmailNotificationLocaleList(result.getEmailNotification().getEmailNotificationLocaleList());
		model.addAttribute("emailNotificationDetailForm", emailNotificationDetailForm);
		
		ResourceURL url = response.createResourceURL();
		url.setResourceID("showEmailNotificationDetailURL");
		
		model.addAttribute("showEmailNotificationDetailURL", url.toString());
		
		return "emailAdministration/emailNotificationDetailPage";
	}
	
	
	@ResourceMapping
	public void retrieveEmailNotificationList(ResourceRequest request, ResourceResponse response) throws Exception{
		logger.debug("------------- Step 1---retrieveEmailNotificationList started---------["+System.nanoTime()+"]");

		EmailNotificationListResult emailNotificationListResult = emailNotificationService.retrieveEmailNotificationList(null);
		String content = getXmlOutputGenerator(request.getLocale()).convertEmailNotificationListToXML(emailNotificationListResult.getEmailNotificationList(),request.getContextPath());
		
		PrintWriter out = response.getWriter();
		response.setContentType("text/xml");
		out.print(content);
		out.flush();
		out.close();
		logger.debug("------------- Step 1---retrieveEmailNotificationList end---------["+System.nanoTime()+"]");
	}
	
	@ResourceMapping("deleteEmailNotification")
	public void deleteEmailNotification(@RequestParam("emailNotificationId") Integer emailNotificationId,
			ResourceRequest request, ResourceResponse response) throws Exception {
		boolean success = false;
		try {
			logger.debug("-------------deleteEmailNotificationDisplay started---------");
			EmailNotificationContract contract = ContractFactory.
			        getEmailNotificationContract(emailNotificationId);
			EmailNotificationResult result = emailNotificationService.deleteEmailNotificationDisplay(contract); 
			logger.debug("-------------deleteEmailNotificationDisplay end---------");
			success = result.getResult();
		} catch (Exception e) {
			success = false;
			throw new Exception(e);
		}finally{
			logger.debug("success: "+success);
			String errorCode = success?"message.emailNotification.deleteEmailNotification"
					:"exception.emailNotification.deleteEmailNotification";
			ServiceStatusUtil.responseResult(response, errorCode, request.getLocale());
		}
	}
	@ResourceMapping("deleteEmailNotificationLocale")
	public void deleteEmailNotificationLocale(@RequestParam("emailNotificationLocaleId") Integer emailNotificationLocaleId,
			ResourceRequest request, ResourceResponse response) throws Exception {
		boolean success = false;
		try {
			logger.debug("-------------deleteEmailNotificationDetailDisplay started---------");
			EmailNotificationLocaleContract contract = new EmailNotificationLocaleContract();
			contract.setEmailNotificationLocaleId(emailNotificationLocaleId);
			EmailNotificationLocaleResult result = emailNotificationService.deleteEmailNotificationLocale(contract); 
			logger.debug("-------------deleteEmailNotificationDetailDisplay end---------");
			success = result.getResult();
		} catch (Exception e) {
			success = false;
			throw new Exception(e);
		}finally{
			logger.debug("success: "+success);
			String errorCode = success?"message.emailNotification.deleteEmailNotificationLocale"
					:"exception.emailNotification.deleteEmailNotificationLocale";
			ServiceStatusUtil.responseResult(response, errorCode, request.getLocale());
		}
	}
	@ResourceMapping("showEmailNotificationDetailURL")
	public void showEmailNotificationDetailURL(ResourceRequest request, ResourceResponse response,Model model) throws Exception {
		logger.debug("------------- Step 1---showEmailNotificationDetailURL started---------["+System.nanoTime()+"]");
		
		String content = "";
		EmailNotificationDetailContract contract = ContractFactory.getEmailNotificationDetailContract(request.getParameter("emailNotificationId"));
		EmailNotificationDetailResult result = emailNotificationService.retrieveEmailNotificationDetail(contract);
		content = getXmlOutputGenerator(request.getLocale()).convertEmailNotificationDetailToXML(result.getEmailNotification().getEmailNotificationLocaleList(),request.getContextPath());
		
		PrintWriter out = response.getWriter();
		response.setContentType("text/xml");
		out.print(content);
		out.flush();
		out.close();
		logger.debug("------------- Step 1---showEmailNotificationDetailURL end---------["+System.nanoTime()+"]");
	}
	@RequestMapping(params="action=saveEmailNotificationDetail")
	public void saveEmailNotificationDetail(ActionRequest request, ActionResponse response ,@ModelAttribute("emailNotificationDetailForm") EmailNotificationDetailForm
			emailNotificationDetailForm, Model model) throws Exception{
		logger.debug("------------- Step 1---saveEmailNotificationDetail started---------["+System.nanoTime()+"]");
		boolean success = false;
		List<String> errors = new ArrayList<String>();
		EmailNotification emailNotification = new EmailNotification();
		emailNotification.setEmailName(emailNotificationDetailForm.getEmailName());
		emailNotification.setEmailDescription(emailNotificationDetailForm.getEmailDescription());
		try{
			if(null==emailNotificationDetailForm.getEmailNotificationId()){
				for(EmailNotificationLocale emailNotificationLocale: result.getEmailNotification().getEmailNotificationLocaleList()){
					EmailNotificationLocale locale = new EmailNotificationLocale();
					if(emailNotificationLocale.getLocale().getSupportedLocaleId().equals(emailNotificationDetailForm.getEmailNotificationLocale().getLocale().getSupportedLocaleId())){
						locale =emailNotificationDetailForm.getEmailNotificationLocale();
						locale.setEmailNotification(emailNotification);
					}else{
						locale = emailNotificationLocale;
						locale.setEmailNotification(emailNotification);
					}
					emailNotification.getEmailNotificationLocaleList().add(locale);
				}
				EmailNotificationCreateContract createContract =ContractFactory.getEmailNotificationCreateContract(emailNotification);
				EmailNotificationLocaleResult result = emailNotificationService.createEmailNotification(createContract);
				success = result.getResult();
				logger.debug("------------- Step 1---createEmailNotificationResult---------["+System.nanoTime()+"]");
			}else{
				EmailNotificationLocaleContract contract = new EmailNotificationLocaleContract();
				emailNotification.setEmailNotificationId(emailNotificationDetailForm.getEmailNotificationId());
					
				if(emailNotificationDetailForm.getEditFlag()==true){
					EmailNotificationLocale emailNotificationLocale = emailNotificationDetailForm.getEmailNotificationLocale();
					emailNotificationLocale.setEmailNotification(emailNotification);
					emailNotification.getEmailNotificationLocaleList().add(emailNotificationLocale);
				}
				contract.setEmailNotification(emailNotification);
				EmailNotificationLocaleResult result = emailNotificationService.saveEmailNotificationLocale(contract);
				success = result.getResult();
				logger.debug("------------- Step 1---saveEmailNotificationResult---------["+System.nanoTime()+"]");
			}
		}catch(Exception e){
			success = false;
			throw new Exception(e);
		}finally{
			if(success == false){
				errors.add("exception.emailNotification.submit");
				for (String status : errors) {
					ServiceStatusUtil.checkServiceStatus(model, status, request.getLocale(), true);
				}
			}
			response.setRenderParameter("action", "");
			logger.debug("------------- Step 1---saveEmailNotificationDetail ended---------["+System.nanoTime()+"]");
		}
	}
}
