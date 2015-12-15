/**
* Copyright@ 2012. This document has been created & written by
 * Wipro technologies for Lexmark and this is copyright to Lexmark
 *
 * File         	: ClaimThankYouController
 * Package     		: com.lexmark.portlet
 * Creation Date 	: 
 *
 * Modification History:
 *
 * ---------------------------------------------------------------------
 * Author 			Date				Version  		Comments
 * ---------------------------------------------------------------------
 * Wipro						 		1.0             Initial Version
 *
 */
package com.lexmark.portlet;

import java.util.Locale;
import java.util.Map;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lexmark.constants.LexmarkConstants;
import com.lexmark.domain.Activity;
import com.lexmark.domain.SiebelLocalization.SiebelLocalizationOptionEnum;
import com.lexmark.form.ClaimRequestConfirmationForm;
import com.lexmark.service.api.ServiceRequestLocale;
import com.lexmark.util.ControllerUtil;
import com.lexmark.util.XmlOutputGenerator;

@Controller
@RequestMapping("VIEW")
public class ClaimThankYouControllor {
		
	@Autowired
	private ServiceRequestLocale serviceRequestLocaleService;
	
	
	@RequestMapping(params = "action=retrieveClaimThankYouPage")
	public String retrieveClaimThankYouPage(Model model,RenderRequest request, RenderResponse response,
			@ModelAttribute("claimRequestConfirmationForm") ClaimRequestConfirmationForm claimRequestConfirmationForm) throws Exception{
		
		Activity activity = claimRequestConfirmationForm.getActivity();
		
		Locale locale = request.getLocale();
		XmlOutputGenerator xmlOutputGenerator = new XmlOutputGenerator(locale);	
		
		claimRequestConfirmationForm.setActivity(activity);
		
		claimRequestConfirmationForm.setAdditionalPaymentRequestListXML(xmlOutputGenerator
				.convertAdditionalPaymentListToXML(activity.getAdditionalPaymentRequestList()));
		claimRequestConfirmationForm.setActivityNoteListXML(xmlOutputGenerator.convertActivityNoteListToXML(
				activity.getActivityNoteList()));
		if(activity.isRepairCompleteFlag()){
			claimRequestConfirmationForm.setRequestedPartsListXML(xmlOutputGenerator
					.convertThankYouReturnPartListToXML(activity.getOrderPartList()));
		}else{
			claimRequestConfirmationForm.setRequestedPartsListNOXML(xmlOutputGenerator
					.convertThankYouReturnPartListNOToXML(activity.getOrderPartList(), activity.getExpedite())); //CI-6 Changes Made
		}
		
		// TODO: localization should be done when submit a claim (see TODO in ClaimRequestCreateController.transformClaimForm)
		String problemCodeLocalized = claimRequestConfirmationForm.getProblemCodeList().get(activity.getActualFailureCode().getValue());
		if(problemCodeLocalized == null){
			activity.getActualFailureCode().setName(activity.getActualFailureCode().getValue());
		}
		claimRequestConfirmationForm.setProblemCodeLocalized(problemCodeLocalized);
	
		if(activity.getDebrief() != null && activity.getDebrief().getResolutionCode() != null){
			String resolutionCodeLocalized = claimRequestConfirmationForm.getResolutionCodeList().get(activity.getDebrief().getResolutionCode().getValue());
			if(resolutionCodeLocalized == null){
				activity.getDebrief().getResolutionCode().setName(activity.getDebrief().getResolutionCode().getValue());
			}
			claimRequestConfirmationForm.setResolutionCode(resolutionCodeLocalized);
			
			String workingConditionLocalized = claimRequestConfirmationForm.getWorkingConditionList().get(activity.getDebrief().getDeviceCondition().getValue());
			if(workingConditionLocalized == null){
				workingConditionLocalized = activity.getDebrief().getDeviceCondition().getValue();
			}
			activity.getDebrief().getDeviceCondition().setName(workingConditionLocalized);
		}
		
		model.addAttribute("claimRequestConfirmationForm", claimRequestConfirmationForm);
		return "claims/claimCreateThankYou";	
	}
	
	@RequestMapping(params = "action=printThankYouPage")
	public String showClaimThankYouPrintPage(RenderRequest request, RenderResponse response, Model model) throws Exception{
		return "claims/claimThankYouPrint";
	}

}
