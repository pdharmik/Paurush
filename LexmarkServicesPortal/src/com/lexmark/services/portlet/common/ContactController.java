/**
* Copyright@ 2012. This document has been created & written by
 * Wipro technologies for Lexmark and this is copyright to Lexmark
 *
 * File         	: ContactController
 * Package     		: com.lexmark.services.portlet.common
 * Creation Date 	: 17th June 2013
 *
 * Modification History:
 *
 * ---------------------------------------------------------------------
 * Author 			Date				Version  		Comments
 * ---------------------------------------------------------------------
 * Wipro						 		1.0             Initial Version
 *
 */

package com.lexmark.services.portlet.common;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
//import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import javax.portlet.PortletSession;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.lexmark.contract.ContactListContract;
import com.lexmark.contract.FavoriteContract;
import com.lexmark.domain.AccountContact;
import com.lexmark.domain.GenericAddress;
import com.lexmark.domain.SiebelLocalization.SiebelLocalizationOptionEnum;
import com.lexmark.exceptionimpl.runtime.LGSRuntimeException;
import com.lexmark.framework.exception.LGSDBException;
import com.lexmark.framework.logging.LEXLogger;
import com.lexmark.result.ContactListResult;
import com.lexmark.result.FavoriteResult;
import com.lexmark.service.api.ContactService;
import com.lexmark.service.api.CrmSessionHandle;
import com.lexmark.service.api.GlobalService;
import com.lexmark.services.LexmarkSPConstants;
import com.lexmark.services.form.validator.CommonValidator;
import com.lexmark.services.portlet.BaseController;
import com.lexmark.services.util.ChangeMgmtConstant;
import com.lexmark.services.util.ContractFactory;
import com.lexmark.services.util.ObjectDebugUtil;
import com.lexmark.services.util.PaginationUtil;
import com.lexmark.services.util.PerformanceConstant;
import com.lexmark.services.util.PortalSessionUtil;
import com.lexmark.services.util.ServiceStatusUtil;
import com.lexmark.services.util.PerformanceUtil; //Added for performance logging
import com.lexmark.util.PropertiesMessageUtil;

/**
 * this class is used for the contact section in change management
 * @author wipro
 * @version 2.1 
 */
@SuppressWarnings("unused")
@Controller
@RequestMapping("VIEW")
@SessionAttributes(value="newContactPopupError")

public class ContactController extends BaseController {
	

	/**. This variable holds AmindGlobalService bean reference **/ 
	@Autowired
	private GlobalService globalService;
	/** holds AmindContactService bean reference **/
	@Autowired
	private ContactService contactService;
	/** holds bean reference of CommonValidator **/
	@Autowired
	private CommonValidator commonValidator;
	@Autowired
	private CommonController commonController;
	/**. Instance variable of wrapper logger class **/
	private static LEXLogger LOGGER = LEXLogger.getLEXLogger(ContactController.class);
	private static Logger perfLogger = LogManager.getLogger("performance"); //Added for performance logging
	
	
	private static final String METH_RETRIEVECONTACTLIST ="retrieveContactList";
	private static final String METH_SETCONTACTFAVOURITEFLAG ="setContactFavouriteFlag";
	private static final String METH_SHOWCONTACTLISTPOPUP ="showContactListPopUp";
	private static final String METH_GETCONTACTINFO ="getContactInfo";
	private static final String METH_CONTACTPOPUPREVIEW ="contactPopupReview";
	
	/**
	 * This method is used to populate the Contact List
	 * @param request 
	 * @param response 
	 * @param model 
	 * @param favFlag 
	 */
	@ResourceMapping(value="primarycontactListPopulate")
	public void retrieveContactList (ResourceRequest request, ResourceResponse response, Model model, @RequestParam(value="fav", required=false) String favFlag)
	{
		LOGGER.enter(this.getClass().getSimpleName(), METH_RETRIEVECONTACTLIST);
				
		
		Boolean isPopUp=Boolean.valueOf(request.getParameter(ChangeMgmtConstant.ISCONTACTPOPUP));		
		ContactListContract contactListContract=null;
		if (!isPopUp) {
			contactListContract = ContractFactory.getContactListContractCM(request);
			//This is added for printing contract details for contact
			ContractFactory.printContractDetail(contactListContract);
		}
		else {
			contactListContract = ContractFactory.getContactListContractPopup(request);
			//This is added for printing contract details for contact popup
			ContractFactory.printContractDetail(contactListContract);
		}
		
		

		PortletSession session = request.getPortletSession();
		
		CrmSessionHandle crmSessionHandle = globalService.initCrmSessionHandle(PortalSessionUtil.getSiebelCrmSessionHandle(request));
		try {
		    contactListContract.setSessionHandle(crmSessionHandle);
		/*
		 * we'll set the favorite to true if the criteria is available. which
		 * will allow us to get the favorite contact list.
		 */
		String searchCriterias = request.getParameter(PaginationUtil.SEARCH_CRITERIAS);
		
		if (searchCriterias != null) {
			
			String contactId = searchCriterias;
			contactListContract.setContactId(contactId);
			contactListContract.setFavoriteFlag(true);
		} else {
					
						
			contactListContract.setContactId(PortalSessionUtil.getContactId(session));
			
			
			
			if(favFlag!=null)
			{
				contactListContract.setFavoriteFlag(true);
			}
			else {
			contactListContract.setFavoriteFlag(false);
			}
		}
		/*LexmarkTransaction lexmarkTran = PerformanceTracker.startTracking(LexmarkConstants.TARGET_SYSTEM_SIEBEL,
				"retrieveContactList", PortalSessionUtil.getMdmId(session),
				PortalSessionUtil.getServiceUser(session).getEmailAddress());*/
		
		
		ObjectDebugUtil.printObjectContent(contactListContract, LOGGER);
		
		/*
		 * Retrieve the contact list from Siebel
		 */
		Long startTime = System.currentTimeMillis();
		
	    ContactListResult contactListResult = contactService.retrieveContactList(contactListContract);
	    long endTime=System.currentTimeMillis();
		LOGGER.info("start printing lex logger");
		
		PerformanceUtil.calcTime(perfLogger, PerformanceConstant.CONTACT_MSG_RETRIEVECONTACTLIST, startTime,endTime, PerformanceConstant.SYSTEM_SIEBEL,contactListContract);
		
		LOGGER.info("end printing lex loggger");

		String contextPath = request.getContextPath();
		
		
		/*
		 * Get the contact list content as xml from XmlOutputGeneratorUtil
		 */
		String content = getXmlOutputGeneratorUtil(request.getLocale())
					.convertContactListToXML(contactListResult.getContacts(),
						 contactListResult.getTotalCount(), contactListContract.getStartRecordNumber(), contextPath,isPopUp);
		
		PrintWriter out = response.getWriter();
		response.setContentType(ChangeMgmtConstant.CONTENTTYPEXML);

		out.print(content);
		out.flush();
		out.close();
		} 
		catch (LGSRuntimeException ex) {
			throw new LGSRuntimeException(ex.getMessage());
		}
		catch (Exception _ignore) {
			
			LOGGER.error(_ignore);
		} finally {
			globalService.releaseSessionHandle(crmSessionHandle);
		}
		
		LOGGER.exit(this.getClass().getSimpleName(), METH_RETRIEVECONTACTLIST);
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
		
		Long startTime = System.currentTimeMillis();
		
		favoriteResult = contactService.updateUserFavoriteContact(favoriteContract);
		long endTime=System.currentTimeMillis();
		LOGGER.info("start printing lex logger");
		
		PerformanceUtil.calcTime(perfLogger, PerformanceConstant.CONTACT_MSG_UPDATEUSERFAVORITECONTACT, startTime,endTime, PerformanceConstant.SYSTEM_AMIND,favoriteContract);
		
		LOGGER.info("end printing lex loggger");
		
		
		
		success = favoriteResult.isResult();
	} catch (Exception e) {
		success = false;
		e.getMessage();
	}finally {
		globalService.releaseSessionHandle(crmSessionHandle);
	}
	
	String errorCode = success?"message.servicerequest.setContactFavouriteFlag"
			:"exception.servicerequest.setContactFavouriteFlag";
	ServiceStatusUtil.responseResult(response, errorCode, request.getLocale(),"\""+favoriteContactId+"\"");
	LOGGER.exit(this.getClass().getSimpleName(), METH_SETCONTACTFAVOURITEFLAG);
	}
	
	/**
	 * Show the popup for contact list
	 * @param req 
	 * @param resp 
	 * @param model 
	 * @return String 
	 */
	@RequestMapping(params = "action=showContactListPopUp")
	public String showContactListPopUp(RenderRequest req, RenderResponse resp, Model model) {
		LOGGER.enter(this.getClass().getSimpleName(), METH_SHOWCONTACTLISTPOPUP);
		model.addAttribute("contactid",PortalSessionUtil.getContactId(req.getPortletSession()));
		
		LOGGER.exit(this.getClass().getSimpleName(), METH_SHOWCONTACTLISTPOPUP);
		resp.setContentType("UTF-8");
		return ChangeMgmtConstant.SELECTCONTACTPATH;
	}
	
	/**
	 * This method is used to get the contact information
	 * @param request 
	 * @param response 
	 * @return AccountContact
	 * @throws LGSRuntimeException 
	 */
	public AccountContact getContactInfo(PortletRequest request,
			PortletResponse response) throws LGSRuntimeException{
		LOGGER.enter(this.getClass().getSimpleName(), METH_GETCONTACTINFO);
		AccountContact accountContact = new AccountContact();
		try{
		accountContact.setContactId(PortalSessionUtil.getContactId(request
				.getPortletSession()));
		accountContact.setFirstName(PortalSessionUtil.getFirstName(request
				.getPortletSession()));
		accountContact.setLastName(PortalSessionUtil.getLastName(request
				.getPortletSession()));
		accountContact.setWorkPhone(PortalSessionUtil.getWorkPhone(request
				.getPortletSession()));
		accountContact.setAlternatePhone(PortalSessionUtil.getAlternatePhone(request
				.getPortletSession()));
		accountContact.setEmailAddress(PortalSessionUtil
				.getEmailAddress(request.getPortletSession()));
		
		request.setAttribute(ChangeMgmtConstant.REQUESTORCONT, accountContact);
		}catch (RuntimeException e) {
			throw new LGSRuntimeException(e.getMessage());
		}
		catch(Exception ex)
		{
			throw new LGSRuntimeException(ex.getMessage());
		}
		LOGGER.exit(this.getClass().getSimpleName(), METH_GETCONTACTINFO);
		return accountContact;
	}
	
	/**
	 * This method is used to validate the new contact information in popup
	 * @param model 
	 * @param request 
	 * @param response 
	 * @throws Exception 
	 */
	@ResourceMapping(value="contactPopupReview")
	public void contactPopupReview(Model model,ResourceRequest request,
			ResourceResponse response)throws Exception{
		/*
		 * Get the new contact information from popup
		 */
		LOGGER.enter(this.getClass().getSimpleName(), METH_CONTACTPOPUPREVIEW);		
	
		StringBuffer responseBody=new StringBuffer();		
		List<String> contactPopupErrorList = new ArrayList<String>();
		List<Object> contactPopupList=new ArrayList<Object>();
		Map<String,String[]> contactPopupValuesMap=request.getParameterMap();
		
		if(contactPopupValuesMap.get("firstName")[0]=="" || contactPopupValuesMap.get("firstName")[0]==null){//firstName
			responseBody.append("<li><strong>"+PropertiesMessageUtil.getPropertyMessage(LexmarkSPConstants.MESSAGE_BUNDLE_NAME,
					"contact.firstname.empty", request.getLocale())+"</strong></li>");
		}
		if(contactPopupValuesMap.get("lastName")[0]=="" || contactPopupValuesMap.get("lastName")[0]==null){//lastName
			responseBody.append("<li><strong>"+PropertiesMessageUtil.getPropertyMessage(LexmarkSPConstants.MESSAGE_BUNDLE_NAME,
					"contact.lastname.empty", request.getLocale())+"</strong></li>");
		}
		if(contactPopupValuesMap.get("workPhone")[0]=="" || contactPopupValuesMap.get("workPhone")[0]==null){//workPhone
			responseBody.append("<li><strong>"+PropertiesMessageUtil.getPropertyMessage(LexmarkSPConstants.MESSAGE_BUNDLE_NAME,
					"contact.workphone.empty", request.getLocale())+"</strong></li>");
		}
		if(contactPopupValuesMap.get("emailId")[0]=="" || contactPopupValuesMap.get("emailId")[0]==null){//emailId
			responseBody.append("<li><strong>"+PropertiesMessageUtil.getPropertyMessage(LexmarkSPConstants.MESSAGE_BUNDLE_NAME,
					"contact.emailaddress.empty", request.getLocale())+"</strong></li>");
		}
		
		contactPopupList.add(contactPopupValuesMap);
		contactPopupList.add(contactPopupErrorList);
		Errors errors=null;
			
			/*
			 * Validate the new contact information
			 */
		commonValidator.validate(contactPopupList, errors);
			
		if(!contactPopupErrorList.isEmpty()){
			for(String index:contactPopupErrorList){
					responseBody.append("<li><strong>"+PropertiesMessageUtil.getPropertyMessage(LexmarkSPConstants.MESSAGE_BUNDLE_NAME,
							index, request.getLocale())+"</strong></li>");
					
				}
			}
		
		
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		out.print(responseBody);
		out.flush();
		out.close();
		responseBody.delete(0, responseBody.length());
		LOGGER.exit(this.getClass().getSimpleName(), METH_CONTACTPOPUPREVIEW);
	}
	
	/**
	 * This method is used to get the device contact information
	 * @param renderRequest 
	 * @param renderResponse 
	 * @param model 
	 * @return AccountContact 
	 * @throws LGSRuntimeException  
	 * @throws LGSDBException  
	 */
	public List<AccountContact> getDeviceContactInfo(PortletRequest renderRequest,
			PortletResponse renderResponse, Model model) throws LGSRuntimeException, LGSDBException{
		LOGGER.enter(this.getClass().getSimpleName(), METH_GETCONTACTINFO);
		
		List<AccountContact> deviceContactDetailList = new ArrayList<AccountContact>();	
		try{
			Map<String, String> deviceContactTypeMap=null;
			try{
				deviceContactTypeMap = commonController.retrieveLocalizedLOVMap(SiebelLocalizationOptionEnum.CONTACT_TYPE_FOR_ASSET.getValue(), renderRequest.getLocale());
				LOGGER.debug("deviceContactTypeMap = "+ deviceContactTypeMap);
			}catch (Exception e) {
				LOGGER.debug("catch"+ e.getMessage());
			}
			model.addAttribute("deviceContactTypeList", deviceContactTypeMap);
			/* added for Device Contact Info start */
			
			LOGGER.debug("deviceContactTypeList at first="+renderRequest.getParameter("deviceContactTypeList"));
			if(!renderRequest.getParameter("deviceContactTypeList").equalsIgnoreCase("NoData")) {
			
			List<String> deviceContactTypeList = Arrays.asList(renderRequest.getParameter("deviceContactTypeList").split(","));
			
			List<String> deviceContactIdList = Arrays.asList(renderRequest.getParameter("deviceContactIdList").split(","));			
			List<String> deviceFNameList = Arrays.asList(renderRequest.getParameter("deviceFNameList").split(","));
			List<String> deviceLNameList = Arrays.asList(renderRequest.getParameter("deviceLNameList").split(","));
			List<String> devicePhoneList = Arrays.asList(renderRequest.getParameter("devicePhoneList").split(","));
			List<String> deviceAltPhoneList = Arrays.asList(renderRequest.getParameter("deviceAltPhoneList").split(","));
			List<String> deviceEmailList = Arrays.asList(renderRequest.getParameter("deviceEmailList").split(","));
				
			List<String> deviceAddressIdList = Arrays.asList(renderRequest.getParameter("deviceAddressIdList").split(","));		
			LOGGER.debug("deviceStoreFrontNameList before split="+renderRequest.getParameter("deviceStoreFrontNameList"));
			List<String> deviceStoreFrontNameList = Arrays.asList(renderRequest.getParameter("deviceStoreFrontNameList").split(","));
			List<String> deviceAddressLine1List = Arrays.asList(renderRequest.getParameter("deviceAddressLine1List").split(","));
			List<String> deviceAddressofficeNumberList = Arrays.asList(renderRequest.getParameter("deviceAddressofficeNumberList").split(","));
			List<String> deviceAddressLine2List = Arrays.asList(renderRequest.getParameter("deviceAddressLine2List").split(","));
			List<String> deviceAddressCityList = Arrays.asList(renderRequest.getParameter("deviceAddressCityList").split(","));
			List<String> deviceAddressStateList = Arrays.asList(renderRequest.getParameter("deviceAddressStateList").split(","));
			List<String> deviceAddresscountyList = Arrays.asList(renderRequest.getParameter("deviceAddresscountyList").split(","));
			List<String> deviceAddressProvinceList = Arrays.asList(renderRequest.getParameter("deviceAddressProvinceList").split(","));
			List<String> deviceAddressDistrictList = Arrays.asList(renderRequest.getParameter("deviceAddressDistrictList").split(","));
			List<String> deviceAddressPostCodeList = Arrays.asList(renderRequest.getParameter("deviceAddressPostCodeList").split(","));
			List<String> deviceAddressCountryList = Arrays.asList(renderRequest.getParameter("deviceAddressCountryList").split(","));
			List<String> deviceAddressregionList = Arrays.asList(renderRequest.getParameter("deviceAddressregionList").split(","));
			List<String> deviceAddresscountryISOCodeList = Arrays.asList(renderRequest.getParameter("deviceAddresscountryISOCodeList").split(","));
			List<String> deviceAddressstateCodeList = Arrays.asList(renderRequest.getParameter("deviceAddressstateCodeList").split(","));
			List<String> physicalLocation1List = Arrays.asList(renderRequest.getParameter("physicalLocation1List").split(","));
			List<String> physicalLocation2List = Arrays.asList(renderRequest.getParameter("physicalLocation2List").split(","));
			List<String> physicalLocation3List = Arrays.asList(renderRequest.getParameter("physicalLocation3List").split(","));
			
			
			LOGGER.debug(" deviceFNameList="+deviceFNameList+" deviceAltPhoneList size="+deviceAltPhoneList.size());
			
			LOGGER.debug("deviceAddressLine1List="+deviceAddressLine1List+" deviceStoreFrontNameList size="+deviceStoreFrontNameList.size());
			int deviceContactLen = deviceFNameList.size();
			LOGGER.debug("deviceContactLen="+deviceContactLen);							
			
			for(int i=0;i<deviceContactLen;i++) {
				LOGGER.debug("i="+i+" deviceFName="+deviceFNameList.get(i));
				if(deviceFNameList.get(i)!=null && !deviceFNameList.get(i).equalsIgnoreCase("deletedContact")) {
				LOGGER.debug("inside deviceFNameList if");
				AccountContact deviceContactDetail = new AccountContact();
				
				deviceContactDetail.setDeviceContactType(deviceContactTypeList.get(i).equalsIgnoreCase("invalid")?"":deviceContactTypeList.get(i));
				
				deviceContactDetail.setContactId(deviceContactIdList.get(i).equalsIgnoreCase("invalid")?"":deviceContactIdList.get(i));
				deviceContactDetail.setFirstName(deviceFNameList.get(i).equalsIgnoreCase("invalid")?"":deviceFNameList.get(i));
				deviceContactDetail.setLastName(deviceLNameList.get(i).equalsIgnoreCase("invalid")?"":deviceLNameList.get(i));
				deviceContactDetail.setWorkPhone(devicePhoneList.get(i).equalsIgnoreCase("invalid")?"":devicePhoneList.get(i));
				deviceContactDetail.setAlternatePhone(deviceAltPhoneList.get(i).equalsIgnoreCase("invalid")?"":deviceAltPhoneList.get(i));
				deviceContactDetail.setEmailAddress(deviceEmailList.get(i).equalsIgnoreCase("invalid")?"":deviceEmailList.get(i));
				
				LOGGER.debug("after setting contact");
				
				GenericAddress deviceAddressDetail = new GenericAddress();
				
				deviceAddressDetail.setAddressId(deviceAddressIdList.get(i).equalsIgnoreCase("invalid")?"":deviceAddressIdList.get(i));
				LOGGER.debug("StoreFrontName="+deviceStoreFrontNameList.get(i));
				deviceAddressDetail.setStoreFrontName(deviceStoreFrontNameList.get(i).equalsIgnoreCase("invalid")?"":deviceStoreFrontNameList.get(i));
				deviceAddressDetail.setAddressLine1(deviceAddressLine1List.get(i).equalsIgnoreCase("invalid")?"":deviceAddressLine1List.get(i));
				deviceAddressDetail.setOfficeNumber(deviceAddressofficeNumberList.get(i).equalsIgnoreCase("invalid")?"":deviceAddressofficeNumberList.get(i));
				deviceAddressDetail.setAddressLine2(deviceAddressLine2List.get(i).equalsIgnoreCase("invalid")?"":deviceAddressLine2List.get(i));
				deviceAddressDetail.setCity(deviceAddressCityList.get(i).equalsIgnoreCase("invalid")?"":deviceAddressCityList.get(i));
				deviceAddressDetail.setState(deviceAddressStateList.get(i).equalsIgnoreCase("invalid")?"":deviceAddressStateList.get(i));
				deviceAddressDetail.setCounty(deviceAddresscountyList.get(i).equalsIgnoreCase("invalid")?"":deviceAddresscountyList.get(i));
				deviceAddressDetail.setProvince(deviceAddressProvinceList.get(i).equalsIgnoreCase("invalid")?"":deviceAddressProvinceList.get(i));
				deviceAddressDetail.setDistrict(deviceAddressDistrictList.get(i).equalsIgnoreCase("invalid")?"":deviceAddressDistrictList.get(i));
				deviceAddressDetail.setPostalCode(deviceAddressPostCodeList.get(i).equalsIgnoreCase("invalid")?"":deviceAddressPostCodeList.get(i));
				deviceAddressDetail.setCountry(deviceAddressCountryList.get(i).equalsIgnoreCase("invalid")?"":deviceAddressCountryList.get(i));
				deviceAddressDetail.setRegion(deviceAddressregionList.get(i).equalsIgnoreCase("invalid")?"":deviceAddressregionList.get(i));
				deviceAddressDetail.setCountryISOCode(deviceAddresscountryISOCodeList.get(i).equalsIgnoreCase("invalid")?"":deviceAddresscountryISOCodeList.get(i));
				deviceAddressDetail.setStateCode(deviceAddressstateCodeList.get(i).equalsIgnoreCase("invalid")?"":deviceAddressstateCodeList.get(i));
				deviceAddressDetail.setPhysicalLocation1(physicalLocation1List.get(i).equalsIgnoreCase("invalid")?"":physicalLocation1List.get(i));
				deviceAddressDetail.setPhysicalLocation2(physicalLocation2List.get(i).equalsIgnoreCase("invalid")?"":physicalLocation2List.get(i));
				deviceAddressDetail.setPhysicalLocation3(physicalLocation3List.get(i).equalsIgnoreCase("invalid")?"":physicalLocation3List.get(i));
				
				LOGGER.debug("after setting address");
				deviceContactDetail.setAddress(deviceAddressDetail);
				
				deviceContactDetailList.add(deviceContactDetail);
				}
			}
			
			int deviceContactDetailListSize = deviceContactDetailList.size();
			LOGGER.debug("after for loop deviceContactDetailList size="+deviceContactDetailListSize);	
			
			
			
			/* added for Device Contact Info end */
			}
		}catch (RuntimeException e) {
			throw new LGSRuntimeException(e.getMessage());
		}
		catch(Exception ex)
		{
			throw new LGSRuntimeException(ex.getMessage());
		}
		LOGGER.exit(this.getClass().getSimpleName(), METH_GETCONTACTINFO);
		return deviceContactDetailList;
	}

}
