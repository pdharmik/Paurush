/**
 * Copyright@ 2013. This document has been created & written by
 * Wipro technologies for Lexmark and this is copyright to Lexmark
 *
 * File         	: ContactController.java
 * Package     		: com.lexmark.portlet
 * Creation Date 	: 
 *
 * Modification History:
 *
 * ---------------------------------------------------------------------
 * Author 			Date				Version  		Comments
 * ---------------------------------------------------------------------
 *
 */
package com.lexmark.portlet;

import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.lexmark.constants.LexmarkPPConstants;
import com.lexmark.contract.ContactListContract;
import com.lexmark.contract.FavoriteContract;
import com.lexmark.framework.logging.LEXLogger;
import com.lexmark.result.ContactListResult;
import com.lexmark.result.FavoriteResult;
import com.lexmark.service.api.ContactService;
import com.lexmark.service.api.CrmSessionHandle;
import com.lexmark.service.api.GlobalService;
import com.lexmark.util.ChangeMgmtConstant;
import com.lexmark.util.ContractFactory;
import com.lexmark.util.ObjectDebugUtil;
import com.lexmark.util.PortalSessionUtil;
import com.lexmark.util.ServiceStatusUtil;

/**
 * @author wipro 
 * @version 2.1 
 */
@Controller
@RequestMapping("VIEW")
public class ContactController extends BaseController {

	
	private static LEXLogger LOGGER = LEXLogger.getLEXLogger(ContactController.class);

	
	private static final String METH_RETRIEVECONTACTLIST ="retrieveContactList";
	private static final String METH_SHOWCONTACTLISTPOPUP ="showContactListPopUp";
	
	/**. This variable holds AmindGlobalService bean reference **/ 
	@Autowired
	private GlobalService globalService;
	
	@Autowired
	private ContactService contactService;

	
	/**
	 * This method let us retrieve contact list for MPS
	 * @param request 
	 * @param response 
	 * @param model 
	 * @return string 
	 */
	@ResourceMapping(value="primarycontactListPopulate")
	public String retrieveContactList (ResourceRequest request, ResourceResponse response, Model model){
		LOGGER.enter(this.getClass().getSimpleName(), METH_RETRIEVECONTACTLIST);
		
		
		ContactListContract contactListContract=ContractFactory.getContactListContractPopup(request);
		ContactListResult contactListResult=null;
		ObjectDebugUtil.printObjectContent(contactListContract, LOGGER);
		CrmSessionHandle crmSessionHandle = globalService.initCrmSessionHandle(PortalSessionUtil.getSiebelCrmSessionHandle(request));
		contactListContract.setSessionHandle(crmSessionHandle);
		try {
			contactListResult= contactService.retrieveContactList(contactListContract);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			LOGGER.error("error occured"+e.getMessage());
		}finally {
			globalService.releaseSessionHandle(crmSessionHandle);
		}
		
		
		
		
		LOGGER.debug("List size------->>>>>>>> "+contactListResult.getContacts().size());
		model.addAttribute("contactList", contactListResult.getContacts());
		model.addAttribute(LexmarkPPConstants.TOTALCOUNT, contactListResult.getTotalCount());
		model.addAttribute(LexmarkPPConstants.STARTPOS, contactListContract.getStartRecordNumber());
		
		
		LOGGER.exit(this.getClass().getSimpleName(), METH_RETRIEVECONTACTLIST);
		response.setContentType("text/xml");
		return "common/contactPopup/convertContactToXML";
	}
	
	/**
	 * This method let us open contact list popup
	 * @param resp 
	 * @param model 
	 * @return string 
	 */
	@RequestMapping(params = "action=showContactListPopUp")
	public String showContactListPopUp(RenderResponse resp, Model model) {
		LOGGER.debug("............... in CONTACT CONTROLLER ................. ");
		LOGGER.enter(this.getClass().getSimpleName(), METH_SHOWCONTACTLISTPOPUP);
		
		model.addAttribute("contactid","1-687WSZZ");
		
		LOGGER.exit(this.getClass().getSimpleName(), METH_SHOWCONTACTLISTPOPUP);
		resp.setContentType("UTF-8");
		return ChangeMgmtConstant.SELECTCONTACTPATH;
	}
	
	/**
	 * Set the contact favourite flag
	 * @param contactId 
	 * @param favoriteContactId 
	 * @param flagStatus 
	 * @param request 
	 * @param response 
	 * @throws Exception 
	 */
	@ResourceMapping("setContactFavouriteFlag")
	public void setContactFavouriteFlag(@RequestParam("contactId") String contactId,
		@RequestParam("favoriteContactId") String favoriteContactId,
		@RequestParam("flagStatus") boolean flagStatus,
		ResourceRequest request, ResourceResponse response) throws Exception {
		final String METH_SETCONTACTFAVOURITEFLAG ="setContactFavouriteFlag";
		LOGGER.enter(this.getClass().getSimpleName(), METH_SETCONTACTFAVOURITEFLAG);
	boolean success = false;
	
	CrmSessionHandle crmSessionHandle = globalService.initCrmSessionHandle(PortalSessionUtil.getSiebelCrmSessionHandle(request));
	try {
				
		
		FavoriteContract favoriteContract = ContractFactory.getFavoriteContract(request);
		favoriteContract.setSessionHandle(crmSessionHandle);
		FavoriteResult favoriteResult=null;
		favoriteContract.setFavoriteContactId(favoriteContactId);
		favoriteContract.setContactId(contactId);
		favoriteContract.setFavoriteFlag(!flagStatus);
		
		

		LOGGER.debug("Favourite CONTACT ID" + favoriteContract.getFavoriteContactId() + 
				"Favourite CONTACT ID" + favoriteContract.getContactId() + 
				"Favorite CONTACT FLAG" + favoriteContract.isFavoriteFlag());
		
		
		
		

		
		ObjectDebugUtil.printObjectContent(favoriteContract, LOGGER);
		
		
		
		favoriteResult = contactService.updateUserFavoriteContact(favoriteContract);
		
		
		
		success = favoriteResult.isResult();
	} catch (Exception e) {
		LOGGER.error("exception occured"+e.getMessage());
		success = false;
	}finally {
		globalService.releaseSessionHandle(crmSessionHandle);
	}
	
	String errorCode = success?"message.servicerequest.setContactFavouriteFlag"
			:"exception.servicerequest.setContactFavouriteFlag";
	ServiceStatusUtil.responseResult(response, errorCode, request.getLocale(),"\""+favoriteContactId+"\"");
	LOGGER.exit(this.getClass().getSimpleName(), METH_SETCONTACTFAVOURITEFLAG);
	}
	
	
}
