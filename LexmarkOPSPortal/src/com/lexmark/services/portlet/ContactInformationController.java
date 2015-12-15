/**
 * Copyright@ 2012. This document has been created & written by
 * Wipro technologies for Lexmark and this is copyright to Lexmark
 *
 * File         	: ContactInformationController.java
 * Package     		: com.lexmark.services.portlet
 * Creation Date 	: 2013
 *
 * Modification History:
 *
 * ---------------------------------------------------------------------
 * Author 				Date				Version  		Comments
 * ---------------------------------------------------------------------
 * wipro		2013 		2.1             Initial Version
 * 
 */

package com.lexmark.services.portlet;

import java.util.List;
import java.util.StringTokenizer;

import javax.portlet.PortletSession;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lexmark.constants.LexmarkConstants;
import com.lexmark.contract.ContactInformationContract;
import com.lexmark.domain.ContactInformation;
import com.lexmark.domain.LexmarkTransaction;
import com.lexmark.domain.ServicesUser;
import com.lexmark.result.ContactInformationResult;
import com.lexmark.service.api.ContactInformationService;
import com.lexmark.services.form.ContactInformationForm;
import com.lexmark.services.util.ContractFactory;
import com.lexmark.services.util.LexmarkUserUtil;
import com.lexmark.services.util.PortalSessionUtil;
import com.lexmark.util.PerformanceTracker;
import com.lexmark.util.PropertiesMessageUtil;

/**
 * This class is used for contact information
 * @author wipro
 *
 */
@Controller
@RequestMapping("VIEW")
public class ContactInformationController {
	
	/**. Instance variable of wrapper logger class **/
	private static Logger logger = LogManager.getLogger(ContactInformationController.class);

	public static final String CONTACTINFORMATION ="com.lexmark.services.resources.messages";
	/**
	 * holds AmindContactInformationService bean reference
	 */
	@Autowired
	private ContactInformationService contactInformationService;
	
	/**
	 * this method is used to show lexmarkContacts
	 * @param model 
	 * @param request 
	 * @param response 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping
	public String showLexmarkContacts(Model model, RenderRequest request,
            RenderResponse response) throws Exception {
		
		
		
		PortletSession session = request.getPortletSession();
		List<String> userRoleNameList = LexmarkUserUtil.getUserRoleNameList(request);
		ContactInformationForm contactInformationForm = new ContactInformationForm();
		ServicesUser servicesuser = PortalSessionUtil.getServiceUser(session);
		String mdmId = servicesuser.getMdmId();
		
		ContactInformationContract contract = ContractFactory.getContactInformationContract(request);
		if(null == contract.getMdmId() || contract.getMdmId()==""){
			contract.setMdmId(mdmId);
		}
		for(String roleName : userRoleNameList){
			contract.setRoleName(roleName);
			LexmarkTransaction lexmarkTran = PerformanceTracker.startTracking(LexmarkConstants.TARGET_SYSTEM_SIEBEL, "retrieveContactInformation", 
					PortalSessionUtil.getMdmId(session), PortalSessionUtil.getServiceUser(session).getEmailAddress());
			ContactInformationResult result = contactInformationService.retrieveContactInformation(contract);
			PerformanceTracker.endTracking(lexmarkTran);
			
			for(int index = 0;index < result.getContactInfoList().size() ; index++){
				String siebelRoleName = result.getContactInfoList().get(index).getRoleName();
				String localizedRoleName = PropertiesMessageUtil.getLocalizedRoleName(CONTACTINFORMATION, "rolename." + removeSpaces(siebelRoleName.toLowerCase()), request.getLocale());
				result.getContactInfoList().get(index).setRoleName(localizedRoleName);
				contactInformationForm.getContactInformations().add(separateContactData(result.getContactInfoList().get(index)));
			}
	}
		
		model.addAttribute("contactInformationForm", contactInformationForm);
		
		
		return "contactInformation";
	}
	
	/**
	 * This method is used to remove blank spaces
	 * @param s 
	 * @return 
	 */
	public String removeSpaces(String s) {
		  StringTokenizer st = new StringTokenizer(s," ",false);
		  String t="";
		  while (st.hasMoreElements()){
			  t += st.nextElement();
		  }
		  return t;
		  
		}
	
	/**
	 * this method is used to separate contact data from others
	 * @param contactInformation 
	 * @return 
	 */
	private ContactInformation separateContactData(ContactInformation contactInformation){
		if(contactInformation.getContactData() != null){
			contactInformation.setContactData(contactInformation.getContactData().replaceAll("\r\n", "<br>"));
		}else{
			contactInformation.setContactData("");
		}
		return contactInformation;
	}
}
