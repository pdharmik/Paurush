package com.lexmark.services.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;

import javax.portlet.ActionRequest;
import javax.portlet.PortletRequest;
import javax.portlet.PortletSession;
import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.ResourceRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.lexmark.constants.LexmarkConstants;
import com.lexmark.contract.AccountReceivableListContract;
import com.lexmark.contract.AddressListContract;
import com.lexmark.contract.AssetContract;
import com.lexmark.contract.AssetListContract;
import com.lexmark.contract.CatalogDetailContract;
import com.lexmark.contract.CatalogListContract;
import com.lexmark.contract.CategoryAdministrationDetailContract;
import com.lexmark.contract.CategoryAdministrationListContract;
import com.lexmark.contract.CategoryDeleteContract;
import com.lexmark.contract.CategoryDetailContract;
import com.lexmark.contract.CategoryHierarchyContract;
import com.lexmark.contract.CategorySaveContract;
import com.lexmark.contract.ContactInformationContract;
import com.lexmark.contract.ContactListContract;
import com.lexmark.contract.CreateConsumableServiceRequestContract;
import com.lexmark.contract.CreateHardwareRequestContract;
import com.lexmark.contract.DeleteNotificationContract;
import com.lexmark.contract.DeleteReportAdministrationContract;
import com.lexmark.contract.DocumentAdministrationListContract;
import com.lexmark.contract.DocumentDefinitionDeleteContract;
import com.lexmark.contract.DocumentDefinitionDetailsContract;
import com.lexmark.contract.DocumentDefinitionSaveContract;
import com.lexmark.contract.DocumentDeleteContract;
import com.lexmark.contract.DocumentUserListContract;
import com.lexmark.contract.EmailNotificationContract;
import com.lexmark.contract.EmailNotificationCreateContract;
import com.lexmark.contract.EmailNotificationDetailContract;
import com.lexmark.contract.EmailNotificationDetailForNameContract;
import com.lexmark.contract.FavoriteAddressContract;
import com.lexmark.contract.FavoriteContract;
import com.lexmark.contract.GlobalAssetListContract;
import com.lexmark.contract.GlobalLegalEntityByLegalNameContract;
import com.lexmark.contract.GlobalLegalEntityContract;
import com.lexmark.contract.GlobalLegalEntityListContract;
import com.lexmark.contract.InvoiceListContract;
import com.lexmark.contract.LBSAssetListContract;
import com.lexmark.contract.LBSCHLContract;
import com.lexmark.contract.LdapUserDataContract;
import com.lexmark.contract.LocalizedEntitlementServiceDetailContract;
import com.lexmark.contract.LocalizedSiebelLOVListContract;
import com.lexmark.contract.LocalizedSiebelValueContract;
import com.lexmark.contract.LocationReportingHierarchyContract;
import com.lexmark.contract.MeterReadAssetListContract;
import com.lexmark.contract.MeterReadStatusContract;
import com.lexmark.contract.NotificationDetailContract;
import com.lexmark.contract.NotificationDisplayOrderContract;
import com.lexmark.contract.ObieeReportParameterContract;
import com.lexmark.contract.PageCountsContract;
import com.lexmark.contract.PaymentListContract;
import com.lexmark.contract.PlacementContract;
import com.lexmark.contract.PriceContract;
import com.lexmark.contract.ProcessCustomerContactContract;
import com.lexmark.contract.ReportDefinitionDetailContract;
import com.lexmark.contract.ReportDefinitionDisplayContract;
import com.lexmark.contract.ReportHierarchyContract;
import com.lexmark.contract.ReportListContract;
import com.lexmark.contract.RequestListContract;
import com.lexmark.contract.SRAdministrationDetailContract;
import com.lexmark.contract.SRAdministrationListContract;
import com.lexmark.contract.SRAdministrationSaveContract;
import com.lexmark.contract.SRLocalizationContract;
import com.lexmark.contract.SaveCategoryAdministrationDetailContract;
import com.lexmark.contract.SaveNotificationContract;
import com.lexmark.contract.SaveReportDefinitionDetailContract;
import com.lexmark.contract.SaveServicesUserContract;
import com.lexmark.contract.ScheduleReportContract;
import com.lexmark.contract.ServiceRequestAssociatedListContract;
import com.lexmark.contract.ServiceRequestContract;
import com.lexmark.contract.ServiceRequestHistoryListContract;
import com.lexmark.contract.ServiceRequestListContract;
import com.lexmark.contract.ServicesUserContract;
import com.lexmark.contract.SiebelAccountListContract;
import com.lexmark.contract.SiebelLOVListContract;
import com.lexmark.contract.SwapCategoryOrderNumberContract;
import com.lexmark.contract.TaxContract;
import com.lexmark.contract.UpdateAssetMeterReadContract;
import com.lexmark.contract.UserFilterSettingContract;
import com.lexmark.contract.UserNotificationContract;
import com.lexmark.contract.UserNotificationListContract;
import com.lexmark.contract.api.SearchContractBase;
import com.lexmark.contract.source.ReturnServiceRequestContract;
import com.lexmark.domain.Account;
import com.lexmark.domain.AccountContact;
import com.lexmark.domain.Bundle;
import com.lexmark.domain.DocumentDefinition;
import com.lexmark.domain.EmailNotification;
import com.lexmark.domain.GenericAddress;
import com.lexmark.domain.GlobalAccount;
import com.lexmark.domain.ListOfValues;
import com.lexmark.domain.NotificationDetail;
import com.lexmark.domain.OPSUserFilterPopupSetting;
import com.lexmark.domain.ObieeReportParameter;
import com.lexmark.domain.OrderPart;
import com.lexmark.domain.PageCounts;
import com.lexmark.domain.Pagination;
import com.lexmark.domain.Part;
import com.lexmark.domain.Price;
import com.lexmark.domain.ReportDefinition;
import com.lexmark.domain.ReportJob;
import com.lexmark.domain.ReportSchedule;
import com.lexmark.domain.ReportScheduleParameter;
import com.lexmark.domain.RoleCategory;
import com.lexmark.domain.ServiceRequest;
import com.lexmark.domain.UserDetails;
import com.lexmark.domain.UserFieldsViewSetting;
import com.lexmark.domain.UserFieldsViewSettingOPS;
import com.lexmark.domain.UserFilterSetting;
import com.lexmark.enums.ReportParameterTypeEnum;
import com.lexmark.service.api.GlobalService;
import com.lexmark.service.impl.real.GlobalServiceFacadeImpl;
import com.lexmark.service.impl.real.jdbc.HibernateUtil;
import com.lexmark.service.impl.real.jdbc.InfrastructureException;
import com.lexmark.service.util.SearchContractUtil;
import com.lexmark.services.LexmarkSPConstants;
import com.lexmark.services.form.AssetDetailPageForm;
import com.lexmark.services.form.CatalogDetailPageForm;
import com.lexmark.services.form.HardwareDetailPageForm;
import com.lexmark.services.form.ManageAssetForm;
import com.lexmark.services.form.ManageReturnsForm;
import com.lexmark.services.form.ReportParameterForm;
import com.lexmark.services.form.ScheduleReportForm;
import com.lexmark.services.portlet.BaseController;
import com.lexmark.services.portlet.SharedPortletController;
import com.lexmark.util.DateUtil;
import com.lexmark.util.LocaleUtil;
import com.lexmark.util.StringUtil;
import com.lexmark.util.TimezoneUtil;


/**
 * @author wipro
 * @version 2.1
 *
 */

public class ContractFactory {
	public static final String SEARCH_CRITERIAS = "searchCriterias";
	public static final String SEARCH_CRITERIAS_SEPERATOR = ",";
	private static Logger LOGGER = LogManager.getLogger(ContractFactory.class);
	private static final String restrictMdmId = "RESTRICT_MDM_ID";
	private static final String restrictMdmLevel = "RESTRICT_MDM_LEVEL";
	private static final String userNumber = "USERNUMBER";
	private static final String servicesUserTable = "SERVICES_USER";
	/**
	 * Variable Declaration
	 */
	private static final String[] deviceColumns = new String[]{"","serialNumber", "deviceTag", "productLine", 
		"assetTag", "hostName", "ipAddress","account.accountName", "assetType","devicePhase", "assetCostCenter", "installAddress.addressName",  
		"installAddress.addressLine1","installAddress.officeNumber", "installAddress.city","installAddress.state",
		"installAddress.province","installAddress.county","installAddress.district","installAddress.country","installAddress.postalCode",
		"assetContact.firstName", "assetContact.lastName", "assetContact.emailAddress",
		"assetContact.workPhone","installAddress.levelOfDetails","lbsAddressFlag"};
	/**
	 * Variable Declaration
	 */
	private static final String[] consumableAssetColumns = new String[]{"","", "serialNumber","deviceTag", 
		"descriptionLocalLang", "assetTag", "hostName", "ipAddress","account.accountName", "installAddress.addressName",  
		"devicePhase","installAddress.addressLine1","officeNumber", "installAddress.city","installAddress.state","installAddress.province","county","district","installAddress.country",
		"installAddress.postalCode", "assetContact.firstName", "assetContact.lastName", "assetContact.emailAddress", "assetContact.workPhone"};
	/**
	 * Variable Declaration
	 */
	private static final String[] assetColumns=new String[]{"serialNumber","serialNumber","serialNumber",
		"serialNumber", "productLine", 
		"assetTag", "deviceTag", "hostName", "ipAddress", "devicePhase", "account.accountName", "installAddress.addressName",
		"installAddress.addressLine1", "installAddress.officeNumber", "installAddress.city","installAddress.state","installAddress.province",
		"installAddress.county", "installAddress.district", "installAddress.country", "installAddress.postalCode",
		"assetContact.firstName", "assetContact.lastName", "assetContact.emailAddress",
		"assetContact.workPhone"};

	/**
	 * Variable Declaration
	 */
    private static final String[] serviceRequestColumns = new String[] { "serviceRequestNumber", "serviceRequestNumber", "serviceRequestStatusDate", "asset.serialNumber", "asset.modelNumber","problemDescription","serviceAddress.addressName", "serviceRequestStatus", "serviceAddress.city", "serviceAddress.state","serviceAddress.province", "serviceAddress.postalCode", "serviceAddress.country", "custRefNumber", "primaryContact.firstName", "primaryContact.lastName", "primaryContact.emailAddress", "primaryContact.workPhone"};
    //End Dwijen

    /**
	 * Variable Declaration
	 */
	private static final String[] meterReadsColumns =  new String[]{"serialNumber","serialNumber","productLine","assetTag","ipAddress", "installAddress.addressName", "meterReadDate", "", "lastPageCount","newPageCount","lastColorPageCount","newColorPageCount","buttonSave","installAddress.officeNumber","installAddress.city","installAddress.state","installAddress.province","installAddress.county","installAddress.district","installAddress.country","hostName","macAddress","deviceTag","modelNumber","productTLI","physicalLocation1","assetContact.firstName","assetContact.lastName"};
	/**
	 * Variable Declaration
	 */
	private static final String[]  serviceRequestHistoryColumns = new String[]{"serviceRequestNumber","serviceRequestStatusDate","asset.serialNumber", "asset.modelNumber","problemDescription","serviceAddress.addressName","serviceRequestStatus","serviceAddress.city","serviceAddress.state","serviceAddress.province","serviceAddress.postalCode","serviceAddress.country", "primaryContact.firstName","primaryContact.lastName","primaryContact.emailAddress","primaryContact.workPhone"};
	/**
	 * Variable Declaration
	 */
	private static final String[] requestHistoryColumns=new String[]{"serviceRequestNumber","serviceRequestStatusDate","requestType","area","serviceRequestStatus"};
	private static final String[]  associatedRequestHistoryColumns=new String[]{"requestNumber","serviceRequestDate","requestType","area","statusType"};
    
	/**
	 * Variable Declaration
	 */
	private static final String[] accountContactColumns = new String[] { "contactId", "lastName", "firstName", "workPhone", "emailAddress", "contactId" };
	/**
	 * Variable Declaration
	 */
	//Added by MPS offshore team MPS2.1
	private static final String[] accountContactColumnsCM = new String[] {"lastName","firstName","workPhone","alternatePhone","emailAddress","addressLine1","addressLine2","officeNumber","city","state","province","county","district","country","postalCode"};
	/**
	 * Variable Declaration
	 */
	private static final String[] accountContactColumnsPopup = new String[] { "contactId", "lastName", "firstName", "workPhone","alternatePhone", "emailAddress" };
	/**
	 * Variable Declaration
	 */
	private static final String[] accountAddressPopupColumns = new String[] {"addressName","storeFrontName","addressLine1","addressLine2","officeNumber","city","state","province","county","district","region","postalCode","country","lbsAddressFlag","levelOfDetails"};
	/**
	 * Variable Declaration
	 */
	private static final String[] accountAddressColumns = new String[] {"addressName","storeFrontName","addressLine1","addressLine2","officeNumber","city","state","province","county","district","region","postalCode","country","lbsAddressFlag"};
	/**
	 * Variable Declaration
	 */
	private static final String EMPLOYEE = "employee"; // added for employee report
	/**
	 * Variable Declaration
	 */
	private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("MM/dd/yyyy");
	/**
	 * Variable Declaration
	 */
	private static final SimpleDateFormat DATE_FORMAT_PAYMENT = new SimpleDateFormat("MM/dd/yyyy"); //added for customer invoice
		
	/**
	 * Variable Declaration
	 */
	// Catalog Part List
	private static final String[] catalogPartColumns = new String[] {"partImage", "partno", "description", "partType", "yield", "Quantity"};
	
	/**
	 * @param request 
	 * @return CatalogListContract 
	 */
	public static CatalogListContract  getCatalogPartRequestListContract (ResourceRequest request){
		Pagination pagination = PaginationUtil.getPainationFromRequest(request, catalogPartColumns, "CatalogPartRequestList");
		CatalogListContract contract = new CatalogListContract();
		SearchContractUtil.copyPaginationToContract(pagination, contract);
		return contract;
	}  
	/**
	 * @return CatalogDetailContract
	 */
	public static CatalogDetailContract getCatalogOrderDetailContract(){
		CatalogDetailContract contract = new CatalogDetailContract();
		return contract;
	}
	
	/**
	 * @param request 
	 * @return ServiceRequestListContract 
	 */
	public static ServiceRequestListContract getServiceRequestListContract(ResourceRequest request) {
		Pagination pagination = PaginationUtil.getPainationFromRequest(request, serviceRequestColumns, "ServiceRequestList");
		ServiceRequestListContract contract =  new ServiceRequestListContract();
		SearchContractUtil.copyPaginationToContract(pagination, contract);
		PortletSession session = request.getPortletSession();
		contract.setMdmID(PortalSessionUtil.getMdmId(session));// commented by sagar for testing in dev
		//contract.setMdmID("1-3S-445");
		contract.setMdmLevel(PortalSessionUtil.getMdmLevel(session));
		contract.setChlNodeId(PortalSessionUtil.getChlNodeId(session));
		contract.setLocale(request.getLocale());
		return contract;
	}
	
	/**
	 * @param request 
	 * @return ServiceRequestListContract 
	 */
	public static ServiceRequestListContract getDownLoadServiceRequestListContract(ResourceRequest request) {
		ServiceRequestListContract contract = getServiceRequestListContract(request);
		contract.setStartRecordNumber(0);
		contract.setIncrement(Integer.valueOf(BaseController.MINUES_ONE));
		contract.setLocale(request.getLocale());
		return contract;
	}
	
	/**
	 * @param request 
	 * @return ServiceRequestListContract
	 */
	public static ServiceRequestListContract getServiceRequestPreviewListContract(ResourceRequest request) {
		ServiceRequestListContract contract = getServiceRequestListContract(request);
		PortletSession session = request.getPortletSession();
		contract.setMdmID(PortalSessionUtil.getMdmId(session));
		contract.setMdmLevel(PortalSessionUtil.getMdmLevel(session));
		contract.setChlNodeId(PortalSessionUtil.getChlNodeId(session));
		contract.setStartRecordNumber(0);
		contract.setIncrement(LexmarkConstants.SERVICE_REQUEST_PREVIEW_COUNT);
		contract.setLocale(request.getLocale());
		return contract;
	}
	
	/**.
	 * Contract for Consumable asset 
	 * @param request 
	 * @return AssetListContract 
	 */
	public static AssetListContract getAssetListContract(ResourceRequest request) {
			
			Pagination pagination = PaginationUtil.getPainationFromRequest(request, deviceColumns, "AssetList");
			AssetListContract contract =  new AssetListContract();
			SearchContractUtil.copyPaginationToContract(pagination, contract);
			contract.setLoadAllFlag(false);
			PortletSession session = request.getPortletSession();
			contract.setMdmId(PortalSessionUtil.getMdmId(session));
			contract.setMdmLevel(PortalSessionUtil.getMdmLevel(session));
			contract.setChlNodeId(PortalSessionUtil.getChlNodeId(session));
			contract.setLocale(request.getLocale());
			contract.setContactId(PortalSessionUtil.getContactId(session));
			
			return contract;
		}
	
	
	/**.
	 * Contract for Consumable asset 
	 * @param request 
	 * @return  AssetListContract 
	 */
	public static AssetListContract getConsumableAssetListContract(ResourceRequest request) {
			
			Pagination pagination = PaginationUtil.getPainationFromRequest(request, consumableAssetColumns, "ConsumableAssetList");
			AssetListContract contract =  new AssetListContract();
			SearchContractUtil.copyPaginationToContract(pagination, contract);
			//contract.setLoadAllFlag(false);
			PortletSession session = request.getPortletSession();
			
			contract.setChlNodeId(PortalSessionUtil.getChlNodeId(session));
			contract.setContactId(PortalSessionUtil.getContactId(session));
			
			
			Map<String,String> accDetails=(Map<String,String>)session.getAttribute("accountCurrentDetails",PortletSession.APPLICATION_SCOPE);
			if(accDetails != null){
				List<String> userRoleList = PortalSessionUtil.getUserRoles(session);
				if(!userRoleList.isEmpty() && accDetails.get("vendorAccountID")!=null && ((userRoleList.contains(LexmarkConstants.ROLE_SERVICE_MANAGER)) || (userRoleList.contains(LexmarkConstants.ROLE_SERVICE_TECHNICIAN))))
				{
					contract.setMdmId(accDetails.get("accountId"));
					contract.setMdmLevel("Siebel");
				}else {
					contract.setMdmId(PortalSessionUtil.getMdmId(session));
					contract.setMdmLevel(PortalSessionUtil.getMdmLevel(session));
				}
			}
			else {
				contract.setMdmId(PortalSessionUtil.getMdmId(session));
				contract.setMdmLevel(PortalSessionUtil.getMdmLevel(session));
			}
			
			return contract;
		}
	//Added for Asset-ChangeManagement MPS
	
	/**
	 * @param request 
	 * @return AssetListContract 
	 */
	public static AssetListContract getAssetListContractForCM(ResourceRequest request) {
		PortletSession session = request.getPortletSession();
		Map<String,String> accDetails=(Map<String,String>)session.getAttribute(ChangeMgmtConstant.ACNTCURRDETAILS,PortletSession.APPLICATION_SCOPE);
		//printing the account information of the user
		if(accDetails==null)
		{
			LOGGER.debug("acc details map is null, so creating an empty map");
			accDetails=new HashMap<String, String>();//Go for creation of an empty map			
		}



		if(accDetails!=null){
			LOGGER.debug("Account id ::::   "+accDetails.get(ChangeMgmtConstant.ACCOUNTID));
			LOGGER.debug("Account Name ::::   "+accDetails.get(ChangeMgmtConstant.ACCOUNTNAME));
		}
		Pagination pagination = PaginationUtil.getPaginationFromRequest(request, assetColumns, "AssetList");
		AssetListContract contract =  new AssetListContract();
		SearchContractUtil.copyPaginationToContract(pagination, contract);
		contract.setLoadAllFlag(false);
				
		contract.setMdmId(accDetails.get(ChangeMgmtConstant.ACCOUNTID));
		contract.setMdmLevel(LexmarkConstants.MDM_LEVEL_SIEBEL);
		contract.setChlNodeId(PortalSessionUtil.getChlNodeId(session));
		contract.setLocale(request.getLocale());
		contract.setContactId(PortalSessionUtil.getContactId(session));
		return contract;
	}
	/**
	 * @param request 
	 * @return AssetContract 
	 */
	public static AssetContract getAssetContract(RenderRequest request) {
		AssetContract contract =  new AssetContract();
		PortletSession session = request.getPortletSession();
		contract.setAssetId(request.getParameter("assetRowId"));
		contract.setContactId(PortalSessionUtil.getContactId(session));
		return contract;
	}
	
	
	/**
	 * @param request 
	 * @return AssetContract 
	 */
	public static AssetContract getAssetContract(ResourceRequest request) {
		AssetContract contract =  new AssetContract();
		PortletSession session = request.getPortletSession();
		contract.setAssetId(request.getParameter("assetRowId"));
		contract.setContactId(PortalSessionUtil.getContactId(session));
		//brmandal added the following for Page Count changes - MPS phase 2.1
		contract.setMdmId(PortalSessionUtil.getMdmId(session));
		contract.setMdmLevel(PortalSessionUtil.getMdmLevel(session));
		contract.setSerialNumber(request.getParameter("serialNumber"));
		//brmandal ended for Page count changes - MPS phase 2.1
		return contract;
	}

	//for global asset retrieving
	/**
	 * @param request 
	 * @return GlobalAssetListContract 
	 */
	public static GlobalAssetListContract getGlobalAssetListContract(ResourceRequest request) {		
		GlobalAssetListContract contract =  new GlobalAssetListContract();
		String serialNumber = request.getParameter(PaginationUtil.SEARCH_CRITERIAS);
		contract.setSerialNumber(serialNumber);		
		//initCrmSessionHandle(contract);
		contract.setLocale(request.getLocale());
		return contract;
	}
	
	/**
	 * @param request 
	 * @return MeterReadAssetListContract 
	 */
	public static MeterReadAssetListContract getMeterReadContract(
			ResourceRequest request) {

		
		Pagination pagination = PaginationUtil.getPainationFromRequest(request, meterReadsColumns, "PageCountsList");
		MeterReadAssetListContract contract =  new MeterReadAssetListContract();
		SearchContractUtil.copyPaginationToContract(pagination, contract);
		PortletSession session = request.getPortletSession();
		contract.setMdmID(PortalSessionUtil.getMdmId(session));
		contract.setMdmLevel(PortalSessionUtil.getMdmLevel(session));
		contract.setChlNodeId(PortalSessionUtil.getChlNodeId(session));
		contract.setMeterReadType(LexmarkConstants.METER_READ_MANUAL);
		contract.setFavoriteContactId(PortalSessionUtil.getContactId(session));
		final String DATEFORMAT = "MM/dd/yyyy" ;
		 final SimpleDateFormat sdf = new SimpleDateFormat(DATEFORMAT);
		    sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
		    final String utcTime = sdf.format(new Date());
				contract.setEntitlementEndDate(utcTime);
		return contract;
	}
	
	/**
	 * @return MeterReadStatusContract
	 */
	public static MeterReadStatusContract getMeterReadStatusContract(){
		MeterReadStatusContract contract = new MeterReadStatusContract();
		return contract;
	}
	
	/**
	 * @param request 
	 * @return UpdateAssetMeterReadContract 
	 */
	public static UpdateAssetMeterReadContract getUpdateAssetMeterReadContract(PortletRequest request) {
		UpdateAssetMeterReadContract contract = new UpdateAssetMeterReadContract();
		PortletSession session = request.getPortletSession();
		contract.setContactId(PortalSessionUtil.getContactId(session));
		return contract;
	}

	/**
	 * @param assetId 
	 * @return AssetContract 
	 */
	public static AssetContract getAssetContract(String assetId) {
		AssetContract contract = new AssetContract();
		contract.setAssetId(assetId);
		return contract;
	}
	
	/**
	 * @param request 
	 * @return LocationReportingHierarchyContract 
	 */
	public static LocationReportingHierarchyContract getLocationReportingHierarchyContract(
			ResourceRequest request) {

		LocationReportingHierarchyContract contract = new LocationReportingHierarchyContract();
		PortletSession session = request.getPortletSession();
		
		Map<String,String> accDetails=(Map<String,String>)session.getAttribute("accountCurrentDetails",PortletSession.APPLICATION_SCOPE);
		if(accDetails != null){
			List<String> userRoleList = PortalSessionUtil.getUserRoles(session);
			if(!userRoleList.isEmpty() && accDetails.get("vendorAccountID")!=null && ((userRoleList.contains(LexmarkConstants.ROLE_SERVICE_MANAGER)) || (userRoleList.contains(LexmarkConstants.ROLE_SERVICE_TECHNICIAN))))
			{
				contract.setMdmId(accDetails.get("accountId"));
				contract.setMdmLevel("Siebel");
			}else {
				contract.setMdmId(PortalSessionUtil.getMdmId(session));
				contract.setMdmLevel(PortalSessionUtil.getMdmLevel(session));
			}
		}
		else {
			contract.setMdmId(PortalSessionUtil.getMdmId(session));
			contract.setMdmLevel(PortalSessionUtil.getMdmLevel(session));
		}
		
		contract.setChlNodeId(PortalSessionUtil.getChlNodeId(session));
		return contract;
	}
	
	/**
	 * @param request 
	 * @return GlobalLegalEntityListContract 
	 */
	public static GlobalLegalEntityListContract getGlobalLegalEntityListContract(ResourceRequest request) {
		GlobalLegalEntityListContract contract = new GlobalLegalEntityListContract();
		return contract;		
	}
	
	/**
	 * @param notificationId 
	 * @return DeleteNotificationContract 
	 */
	public static DeleteNotificationContract getDeleteNotificationContract(Integer notificationId) {
		DeleteNotificationContract contract = new DeleteNotificationContract();
		contract.setNotificationId(notificationId);
		return contract;
	}
	
	/**
	 * @param notificationId 
	 * @return NotificationDetailContract 
	 */
	public static NotificationDetailContract getNotificationDetailContract(
			Integer notificationId) {

		NotificationDetailContract contract = new NotificationDetailContract();
		contract.setNotificationId(notificationId);
		return contract;
	}

	/**
	 * @param notificationDetail 
	 * @return SaveNotificationContract 
	 */
	public static SaveNotificationContract getSaveNotificationContract(
			NotificationDetail notificationDetail) {

		SaveNotificationContract contract = new SaveNotificationContract();
		contract.setNotificationDetail(notificationDetail);
		return contract;
	}

	/**
	 * @param notificationId 
	 * @param userId 
	 * @return UserNotificationContract 
	 */
	public static UserNotificationContract getHideUserNotificationContract(Integer notificationId , String userId){
		UserNotificationContract contract = new UserNotificationContract();
		contract.setNotificationId(notificationId);
		contract.setUserId(userId);
		
		return contract;
	}

	/**
	 * @param displayOrder 
	 * @param increment 
	 * @return NotificationDisplayOrderContract 
	 */
	public static NotificationDisplayOrderContract getNotificationDisplayOrderContract(
			Integer displayOrder, Integer increment) {

		NotificationDisplayOrderContract contract = new NotificationDisplayOrderContract();
		contract.setDisplayOrder(displayOrder);
		contract.setIncrement(increment);
		return contract;
	}

	/**
	 * @param request 
	 * @param session 
	 * @return UserNotificationListContract
	 */
	public static UserNotificationListContract getUserNotificationListContrac(RenderRequest request , PortletSession session){
		UserNotificationListContract contract = new UserNotificationListContract();
		contract.setLocale(request.getLocale());
		contract.setUserNumber(PortalSessionUtil.getServiceUser(session).getUserNumber());
		return contract;
	}
	
	/**
	 * @param request 
	 * @return ContactListContract 
	 */
	public static ContactListContract getContactListContract(ResourceRequest request){
		Pagination pagination = PaginationUtil.getPainationFromRequest(request, accountContactColumns, "ContactList");
		ContactListContract contract = new ContactListContract();
		SearchContractUtil.copyPaginationToContract(pagination, contract);
		contract.setLoadAllFlag(false);
		PortletSession session = request.getPortletSession();
		contract.setMdmId(PortalSessionUtil.getMdmId(session));
		contract.setMdmLevel(PortalSessionUtil.getMdmLevel(session));
		contract.setLocale(request.getLocale());
		contract.setContactId(PortalSessionUtil.getContactId(session));		
		
		return contract;
	}
	
	/*
	 * Added by MPS offshore team for contact information
	 */
	/**
	 * @param request 
	 * @return ContactListContract 
	 */
	public static ContactListContract getContactListContractCM(ResourceRequest request){
		Pagination pagination = PaginationUtil.getPainationFromRequest(request, accountContactColumnsCM, "ContactList");
		ContactListContract contract = new ContactListContract();
		SearchContractUtil.copyPaginationToContract(pagination, contract);
		contract.setLoadAllFlag(false);
		PortletSession session = request.getPortletSession();
		contract.setMdmId(PortalSessionUtil.getMdmId(session));
		contract.setMdmLevel(PortalSessionUtil.getMdmLevel(session));
		contract.setLocale(request.getLocale());
		contract.setContactId(PortalSessionUtil.getContactId(session));		
		
		LOGGER.debug("In ContractFactory MdmId ="+PortalSessionUtil.getMdmId(session));
		LOGGER.debug("In ContractFactory MdmLevel ="+PortalSessionUtil.getMdmLevel(session));
		LOGGER.debug("In ContractFactory ContactId ="+PortalSessionUtil.getContactId(session));
		
		return contract;
	}
	
	/**
	 * @param request 
	 * @return ContactListContract 
	 */
	public static ContactListContract getContactListContractPopup(ResourceRequest request){
		Pagination pagination = PaginationUtil.getPainationFromRequest(request, accountContactColumnsPopup, "ContactList");
		ContactListContract contract = new ContactListContract();
		SearchContractUtil.copyPaginationToContract(pagination, contract);
		contract.setLoadAllFlag(false);
		PortletSession session = request.getPortletSession();
		contract.setMdmId(PortalSessionUtil.getMdmId(session));
		contract.setMdmLevel(PortalSessionUtil.getMdmLevel(session));
		contract.setLocale(request.getLocale());
		contract.setContactId(PortalSessionUtil.getContactId(session));		
		
		LOGGER.debug("In ContractFactory MdmId in popup ="+PortalSessionUtil.getMdmId(session));
		LOGGER.debug("In ContractFactory MdmLevel in popup ="+PortalSessionUtil.getMdmLevel(session));
		LOGGER.debug("In ContractFactory ContactId in popup ="+PortalSessionUtil.getContactId(session));
		
		return contract;
	}

	/**
	 * @param request 
	 * @return AddressListContract 
	 */
	public static AddressListContract getAddressListContract(ResourceRequest request){
		/*
		 * Changes for Defect 8093 MPs 2.1
		 * */
		String isPopup=request.getParameter(ChangeMgmtConstant.ISPOPUP);
		Pagination pagination=null;
		
		if(isPopup!=null && isPopup.equalsIgnoreCase("true")){
			pagination = PaginationUtil.getPainationFromRequest(request,accountAddressPopupColumns, "AddressList");
		}else{
			pagination = PaginationUtil.getPainationFromRequest(request,accountAddressColumns , "AddressList");
		}
		/*
		 * ENDS Changes for Defect 8093 MPs 2.1
		 * */
		AddressListContract contract = new AddressListContract();
		SearchContractUtil.copyPaginationToContract(pagination, contract);
		PortletSession session = request.getPortletSession();
		Map<String,String> accDetails=(Map<String,String>)session.getAttribute("accountCurrentDetails",PortletSession.APPLICATION_SCOPE);
		if(accDetails != null){
			List<String> userRoleList = PortalSessionUtil.getUserRoles(session);
			if(!userRoleList.isEmpty() && accDetails.get("vendorAccountID")!=null && ((userRoleList.contains(LexmarkConstants.ROLE_SERVICE_MANAGER)) || (userRoleList.contains(LexmarkConstants.ROLE_SERVICE_TECHNICIAN))))
			{
				contract.setMdmId(accDetails.get("accountId"));
				contract.setMdmLevel("Siebel");
			}else {
				contract.setMdmId(PortalSessionUtil.getMdmId(session));
				contract.setMdmLevel(PortalSessionUtil.getMdmLevel(session));
			}
		}
		else {
			contract.setMdmId(PortalSessionUtil.getMdmId(session));
			contract.setMdmLevel(PortalSessionUtil.getMdmLevel(session));
		}
		contract.setLocale(request.getLocale());
	
		return contract;
	}

	/**
	 * @param request 
	 * @return SiebelAccountListContract 
	 */
	public static SiebelAccountListContract getSiebelAccountListContract(PortletRequest request){
		SiebelAccountListContract contract = new SiebelAccountListContract();
		PortletSession session = request.getPortletSession();
		contract.setMdmId(PortalSessionUtil.getMdmId(session));
		contract.setMdmLevel(PortalSessionUtil.getMdmLevel(session));
		
		List<String> userRoleList = PortalSessionUtil.getUserRoles(session);		
		
		if(!userRoleList.isEmpty() && ((userRoleList.contains(LexmarkConstants.ROLE_SERVICE_MANAGER)) || (userRoleList.contains(LexmarkConstants.ROLE_SERVICE_TECHNICIAN)) || (userRoleList.contains(LexmarkConstants.ROLE_SERVICE_VIEW)) || (userRoleList.contains(LexmarkConstants.ROLE_ACCOUNTS_RECEIVABLE)) || (userRoleList.contains(LexmarkConstants.ROLE_PARTNER_ADMINISTRATOR))))
		{
			contract.setVendorFlag(true);
		}else {
			contract.setVendorFlag(false);
		}
		contract.setNewQueryIndicator(true);
		return contract;
	}
	
	/**
	 * @return  SRAdministrationListContract 
	 */
	public static SRAdministrationListContract getSRAdministrationListContract(){
		return new SRAdministrationListContract();
	}
	
	/**
	 * @return SRAdministrationDetailContract 
	 */
	public static SRAdministrationDetailContract getSRAdministrationDetailContract(){
		return new SRAdministrationDetailContract();
	}
	
	/**
	 * @return SRAdministrationSaveContract 
	 */
	public static SRAdministrationSaveContract getSRAdministrationSaveContract(){
		return new SRAdministrationSaveContract();
	}
	
	/**
	 * @return SRLocalizationContract 
	 */
	public static SRLocalizationContract getSRLocalizationContract(){
		return new SRLocalizationContract();
	}

	/**
	 * @param request 
	 * @return FavoriteContract 
	 */
	public static FavoriteContract getFavoriteContract(ResourceRequest request) {
		FavoriteContract favoriteContract = new FavoriteContract();
		//PortletSession session = request.getPortletSession();

		return favoriteContract;
	}

	/**
	 * @return FavoriteAddressContract 
	 */
	public static FavoriteAddressContract getFavoriteAddressContract() {
		return new FavoriteAddressContract();
	}

	/**
	 * @param request 
	 * @return ContactInformationContract 
	 * @throws Exception 
	 */
	public static ContactInformationContract getContactInformationContract(PortletRequest request) throws Exception{
		ContactInformationContract contract = new ContactInformationContract();
		
		PortletSession session = request.getPortletSession();
		Map<String, Object> ldapUserData =  (Map)session.getAttribute(LexmarkConstants.LDAP_USER_DATA, session.APPLICATION_SCOPE);
		String mdmId = "";
		if (PortalSessionUtil.getMdmLevel(session).equalsIgnoreCase(LexmarkConstants.MDM_LEVEL_GLOBAL)|| 
				PortalSessionUtil.getMdmLevel(session).equalsIgnoreCase(LexmarkConstants.MDM_LEVEL_DOMESTIC)){
			if(ldapUserData != null){
				mdmId = (String)ldapUserData.get(LexmarkConstants.MDMID);
			}
		}else{
			mdmId = PortalSessionUtil.getMdmId(session);				
		}		
		contract.setMdmId(mdmId);
		contract.setMdmLevel(PortalSessionUtil.getMdmLevel(session));
		
		return contract;
	}

	/**
	 * @param request 
	 * @return ServiceRequestHistoryListContract 
	 */
	public static ServiceRequestHistoryListContract getServiceRequestHistoryListContract(
			ResourceRequest request) {
		

		
		Pagination pagination = PaginationUtil.getPainationFromRequest(request, requestHistoryColumns, "ServiceRequestHistoryList");
		
		ServiceRequestHistoryListContract contract =  new ServiceRequestHistoryListContract();
		SearchContractUtil.copyPaginationToContract(pagination, contract);
		PortletSession session = request.getPortletSession();
		contract.setMdmID(PortalSessionUtil.getMdmId(session));
		contract.setMdmLevel(PortalSessionUtil.getMdmLevel(session));
		contract.setLocale(request.getLocale());
		return contract;
	}
	
	//Added for MPS Device Management
	/**
	 * @param request 
	 * @param srHistoryCols 
	 * @return ServiceRequestHistoryListContract 
	 */
	public static ServiceRequestHistoryListContract getSRHistoryListContract(ResourceRequest request, String[] srHistoryCols)
	{
		Pagination pagination = PaginationUtil.getPainationFromRequest(request, srHistoryCols, "ServiceRequestHistoryList");
		ServiceRequestHistoryListContract contract =  new ServiceRequestHistoryListContract();
		SearchContractUtil.copyPaginationToContract(pagination, contract);
		PortletSession session = request.getPortletSession();
		contract.setMdmID(PortalSessionUtil.getMdmId(session));
		contract.setMdmLevel(PortalSessionUtil.getMdmLevel(session));
		contract.setLocale(request.getLocale());
		return contract;
	}

	/**
	 * @param request 
	 * @return ServiceRequestAssociatedListContract 
	 */
	public static ServiceRequestAssociatedListContract getAssociatedServiceRequestListContract(
			ResourceRequest request) {
		Pagination pagination = PaginationUtil.getPainationFromRequest(request, associatedRequestHistoryColumns, "AssociatedServiceRequestHistoryList");
		ServiceRequestAssociatedListContract contract = new ServiceRequestAssociatedListContract();
		SearchContractUtil.copyPaginationToContract(pagination, contract);
		PortletSession session = request.getPortletSession();
		contract.setMdmID(PortalSessionUtil.getMdmId(session));
		contract.setMdmLevel(PortalSessionUtil.getMdmLevel(session));
		contract.setLocale(request.getLocale());
		contract.setChlNodeId(PortalSessionUtil.getChlNodeId(session));
		return contract;
			
	}
	
	
	/**
	 * @param request 
	 * @param globalService 
	 * @return ReportHierarchyContract 
	 * @throws Exception 
	 */
	public static ReportHierarchyContract getReportHierarchyContract(
			PortletRequest request, GlobalService globalService ) throws Exception {

		
		ReportHierarchyContract contract = new ReportHierarchyContract();
		PortletSession session = request.getPortletSession();
		String mdmId = PortalSessionUtil.getMdmId(session);
		String mdmLevel = PortalSessionUtil.getMdmLevel(session);
		/* Added for (BRD 14-02-14) start*/
			// We are fetching the userNumber from Map which exist in Portal session
			//HashMap<String, String> ldapUserData = (HashMap<String, String>)session.getAttribute(LexmarkConstants.LDAP_USER_DATA, session.APPLICATION_SCOPE);
			//String userNo = ldapUserData.get(LexmarkConstants.USERNUMBER);
			// We can also get the userNumber from ServicesUser object in Portal Session
			String usrNo = PortalSessionUtil.getServiceUser(session).getUserNumber();
					
			StringBuffer sb = new StringBuffer();
	        ArrayList accountlist = new ArrayList();
	        sb.append(" SELECT ");
	        sb.append(restrictMdmId + ", " + restrictMdmLevel);
	        sb.append(" FROM ");
	        sb.append(servicesUserTable);
	        sb.append(" WHERE ");
	        sb.append(userNumber + "='" + usrNo + "'");
	        try {
				Query query = HibernateUtil.getSession().createSQLQuery(sb.toString());
				List list = query.list();
				int count = 0;
				String restrctMdmId="";
				String restrctMdmLevel="";
				if(list != null && list.size() > 0) {
					for(int i = 0; i < list.size(); i++){
						Object[] L4orBelowAccount = (Object[]) list.get(i);
						restrctMdmId = (String)L4orBelowAccount[0];
						restrctMdmLevel = (String)L4orBelowAccount[1];					
						if(restrctMdmLevel.equals("Account")){
							count++;
							break;
						}
					}				
					if(count>0){
						contract.setUserMdmId(restrctMdmId);
						contract.setUserMdmLevel(restrctMdmLevel);						
					}
					else{
						restrctMdmId="";
						restrctMdmLevel="";
						contract.setUserMdmId(restrctMdmId);
						contract.setUserMdmLevel(restrctMdmLevel);
					}
				}
				if(list.size() == 0){
					contract.setUserMdmId(mdmId);
					contract.setUserMdmLevel(mdmLevel);
				}
	        }				
	        catch (HibernateException ex) {
				throw new InfrastructureException(ex);
			}finally {
			    HibernateUtil.closeSession();
			}		
		
		
		/* End (BRD 14-02-14) */
		GlobalServiceFacadeImpl globalServiceFacade = new GlobalServiceFacadeImpl();
		globalServiceFacade.setGlobalService(globalService);
		
		GlobalAccount account = globalServiceFacade.retriveGlobalAccount(mdmId, mdmLevel);
		//PerformanceTracker.endTracking(lexmarkTran);
		if(account != null) {
			contract.setMdmID(account.getMdmId());
			contract.setMdmLevel(account.getMdmLevel());
		} else {
			contract.setMdmID(mdmId);
			contract.setMdmLevel(mdmLevel);
			
		}
		/* Added for (BRD 14-02-14) */
		//contract.setUserMdmId(mdmId);  // Commented this line for L4 change
		//contract.setUserMdmLevel(mdmLevel);	// Commented this line for L4 change
		/* End (BRD 14-02-14)*/	
		
		contract.setLocale(request.getLocale());	
		contract.setViewTypes(getReportViewType(request));
		contract.setUserRoleNames(LexmarkUserUtil.getUserRoleNameList(request));
		return contract;
	}
	
	/**
	 * @param request 
	 * @return getReportViewType 
	 */
	private static List<String> getReportViewType(PortletRequest request) {
		List<String> viewTypes = new ArrayList<String>();
		PortletSession session = request.getPortletSession();
		String accountTypeMPSFlag = (String) session
		.getAttribute(SharedPortletController.ACCOUNT_TYPE_MPS_FLAG);

		if(LexmarkConstants.VIEWTYPE_MANAGED_DEVICES.equalsIgnoreCase(accountTypeMPSFlag)) {
			addViewType(viewTypes, LexmarkConstants.VIEWTYPE_MANAGED_DEVICES);
			addViewType(viewTypes, LexmarkConstants.VIEWTYPE_ALL);
		}
		
		String accountTypeCSSFlag = (String) session
		.getAttribute(SharedPortletController.ACCOUNT_TYPE_CSS_FLAG);

		if(LexmarkConstants.VIEWTYPE_NON_MANAGED_DEVICES.equalsIgnoreCase(accountTypeCSSFlag)) {
			addViewType(viewTypes, LexmarkConstants.VIEWTYPE_NON_MANAGED_DEVICES);
			addViewType(viewTypes, LexmarkConstants.VIEWTYPE_ALL);
		}
		
		String directPartnerFlag = (String) session
		.getAttribute(SharedPortletController.DIRECT_PARTNER_FLAG);
		if (LexmarkConstants.DIRECT_PARTNER.equalsIgnoreCase(directPartnerFlag)) {
			addViewType(viewTypes, LexmarkConstants.DIRECT_PARTNER);
			addViewType(viewTypes, LexmarkConstants.ALL_PARTNER);
		}
		
		String indirectPartnerFlag = (String) session
		.getAttribute(SharedPortletController.INDIRECT_PARTNER_FLAG);
		if (LexmarkConstants.INDIRECT_PARTNER.equalsIgnoreCase(indirectPartnerFlag)) {
			addViewType(viewTypes, LexmarkConstants.INDIRECT_PARTNER);
			addViewType(viewTypes, LexmarkConstants.ALL_PARTNER);
		}
		
		return viewTypes;
	}
	
	/**
	 * @param viewTypes 
	 * @param newType 
	 */
	private static void addViewType(List<String> viewTypes, String newType) {
		if (newType == null) {
			return;
		}
		
		if (viewTypes.contains(newType)) {
			return;
		}
		
		viewTypes.add(newType);
	}
	
	/**
	 * @param request 
	 * @param globalService 
	 * @return ReportListContract 
	 * @throws Exception 
	 */
	public static ReportListContract getReportListContract(PortletRequest request, GlobalService globalService) throws Exception{
		ReportListContract contract = new ReportListContract();
		PortletSession session = request.getPortletSession();
		String mdmLevel = PortalSessionUtil.getMdmLevel(session);
		String mdmId = PortalSessionUtil.getMdmId(session);
		
		if(!LexmarkConstants.MDM_LEVEL_GLOBAL.equals(mdmLevel)){
			GlobalServiceFacadeImpl globalServiceFacade = new GlobalServiceFacadeImpl();
			globalServiceFacade.setGlobalService(globalService);
			GlobalAccount account = globalServiceFacade.retriveGlobalAccount(mdmId, mdmLevel);
			mdmLevel = account.getMdmLevel();
			mdmId = account.getMdmId();
		}
		
		contract.setViewTypes(getReportViewType(request));
		contract.setUserNumber(PortalSessionUtil.getServiceUser(session).getUserNumber());
		contract.setMdmId(mdmId);
		contract.setMdmLevel(mdmLevel);
		contract.setLocale(request.getLocale());
		contract.setUserRoleNames(LexmarkUserUtil.getUserRoleNameList(request));
		return contract;
	}
	
	
	/**
	 * @param categoryType 
	 * @return CategoryAdministrationListContract 
	 */
	public static CategoryAdministrationListContract
			getCategoryAdministrationListContract(String categoryType) {

		CategoryAdministrationListContract contract = new CategoryAdministrationListContract();
		contract.setCategoryType(categoryType);
		return contract;
	}

	/**
	 * @param categoryId 
	 * @return CategoryAdministrationDetailContract 
	 */
	public static CategoryAdministrationDetailContract getCategoryAdministrationDetailContract(
			Integer categoryId) {

		CategoryAdministrationDetailContract contract = new CategoryAdministrationDetailContract();
		contract.setCategoryId(categoryId);
		return contract;
	}

	/**
	 * @param reportDefinitionId 
	 * @return DeleteReportAdministrationContract 
	 */
	public static DeleteReportAdministrationContract getDeleteReportAdministrationContract(
			Integer reportDefinitionId) {

		DeleteReportAdministrationContract contract = new DeleteReportAdministrationContract();
		contract.setReportDefinitionId(reportDefinitionId);
		return contract;
	}

	/**
	 * @param emailNotificationId 
	 * @return EmailNotificationContract 
	 */
	public static EmailNotificationContract getEmailNotificationContract(Integer emailNotificationId){
		EmailNotificationContract contract = new EmailNotificationContract();
		contract.setEmailNotificationId(emailNotificationId);
		
		return contract;
	}

	/**
	 * @param emailNotificationId 
	 * @return EmailNotificationDetailContract 
	 */
	public static EmailNotificationDetailContract getEmailNotificationDetailContract(String emailNotificationId){
		EmailNotificationDetailContract contract = new EmailNotificationDetailContract();
		contract.setEmailNotificationId(emailNotificationId);
		return contract;
	}
	
	/**
	 * @param emailName 
	 * @return EmailNotificationDetailForNameContract
	 */
	public static EmailNotificationDetailForNameContract getEmailNotificationDetailForNameContract(String emailName){
		EmailNotificationDetailForNameContract contract = new EmailNotificationDetailForNameContract();
		contract.setEmailNotificationName(emailName);
		return contract;
	}
	
	/**
	 * @param emailNotification 
	 * @return EmailNotificationCreateContract 
	 */
	public static EmailNotificationCreateContract getEmailNotificationCreateContract(EmailNotification emailNotification){
		EmailNotificationCreateContract contract = new EmailNotificationCreateContract();
		contract.setEmailNotification(emailNotification);
		return contract;
	}

	/**
	 * @param userNumber 
	 * @param emailAddress 
	 * @return ServicesUserContract 
	 */
	public static ServicesUserContract getServicesUserContract(String userNumber,
			String emailAddress) {

		ServicesUserContract contract = new ServicesUserContract();
		contract.setUserNumber(userNumber);
		contract.setEmailAddress(emailAddress);
		return contract;
	}
	
	/**
	 * @param emailAddress 
	 * @return LdapUserDataContract 
	 */
	public static LdapUserDataContract getLdapUserDataContract(String emailAddress) {
		LdapUserDataContract contract = new LdapUserDataContract();
		contract.setEmailAddress(emailAddress);
		return contract;
	}
	
	/**
	 * @return DocumentAdministrationListContract 
	 */
	public static DocumentAdministrationListContract getDocumentAdministrationListContract() {
		return new DocumentAdministrationListContract();
	}

	/**
	 * @return CategoryAdministrationListContract 
	 */
	public static CategoryAdministrationListContract getDocumentCategoryAdministrationListContract() {
		return new CategoryAdministrationListContract();
	}

	/**
	 * @param request 
	 * @return ReportDefinitionDisplayContract 
	 */
	public static ReportDefinitionDisplayContract getDefinitionDisplayContract(PortletRequest request){
			ReportDefinitionDisplayContract contract = new ReportDefinitionDisplayContract();
			contract.setLocale(request.getLocale());
			return contract;
	}

	/**
	 * @param mdmId 
	 * @param mdmLevel 
	 * @param chlNodeId 
	 * @param userNumberList 
	 * @return SaveServicesUserContract 
	 */
	public static SaveServicesUserContract getSaveServicesUserContract(
			String mdmId, String mdmLevel, String chlNodeId, List<String> userNumberList) {

		SaveServicesUserContract contract = new SaveServicesUserContract();
		contract.setMdmId(mdmId);
		contract.setMdmLevel(mdmLevel);
		contract.setChlNodeId(chlNodeId);
		contract.setUserNumberList(userNumberList);
		return contract;
	}

	/**
	 * @param category 
	 * @return SaveCategoryAdministrationDetailContract 
	 */
	public static SaveCategoryAdministrationDetailContract getSaveCategoryAdministrationDetailContract(
			RoleCategory category) {

		SaveCategoryAdministrationDetailContract contract = new SaveCategoryAdministrationDetailContract();
		contract.setCategory(category);
		return contract;
	}

	/**
	 * @param scheduleReportForm 
	 * @param request 
	 * @return ScheduleReportContract 
	 * @throws Exception 
	 */
	public static ScheduleReportContract getScheduleReportContract(
			ScheduleReportForm scheduleReportForm, 
			PortletRequest request) throws Exception{

		ScheduleReportContract contract = new ScheduleReportContract();
		String MDMId = null;    // added for employee report
		String MDMLevel = null; // added for employee report
		//added
		List<ReportParameterForm> runNowParams = new ArrayList<ReportParameterForm>();
		List<ReportParameterForm> scheduleParams = new ArrayList<ReportParameterForm>();
		List<ReportParameterForm> toSaveParams = new ArrayList<ReportParameterForm>();
		if(!scheduleReportForm.getRunNowParameters().isEmpty()) {
			runNowParams = scheduleReportForm.getRunNowParameters();
		}
		if(!scheduleReportForm.getScheduleParameters().isEmpty()) {
			scheduleParams = scheduleReportForm.getScheduleParameters();
		}
		
		if(ScheduleReportForm.RUN_NOW.equals(scheduleReportForm.getRunType())) {
			toSaveParams = runNowParams;
		}
		else {
			toSaveParams = scheduleParams;
		}
//		end of addition
//		for(ReportParameterForm parameter : scheduleReportForm.getParameters()){	// commented
		for(ReportParameterForm parameter : toSaveParams) {   // changed
			if (parameter.getIsRequired() && StringUtil.isStringEmpty(parameter.getValue())) {
				String[] displayNameObject = {parameter.getDisplayName()};
				throw new Exception(ServiceStatusErrorMessageUtil.getErrorMessage(LexmarkSPConstants.ERROR_MESSAGE_BUNDLE, "exception.common.requiredException", displayNameObject, request.getLocale()));
			} else if (!parameter.getIsRequired() && StringUtil.isStringEmpty(parameter.getValue())) {
				continue;
			}
			ReportScheduleParameter parameterValue = new ReportScheduleParameter();
			parameterValue.setName(parameter.getName());
			if(!ReportParameterTypeEnum.DATE.getType().equals(parameter.getDataType())){
				parameterValue.setValue(parameter.getValue());
			}else{
				parameterValue.setValue(changeDateFormat(parameter.getValue()));
			}
			contract.getParameterValues().add(parameterValue);
		}
		ReportSchedule reportSchedule = new ReportSchedule();
		reportSchedule.setId(scheduleReportForm.getReportScheduleId());
		reportSchedule.setReportDefinitionId(scheduleReportForm.getReportDefinitionId());
		
		if(ScheduleReportForm.RUN_NOW.equals(scheduleReportForm.getRunType())){
			reportSchedule.setRunFrequency(ScheduleReportForm.RUN_NOW_FREQUENCY);
			reportSchedule.setRunInterval(1);
			reportSchedule.setEffectiveDate(new Date());
		}
		else{

			reportSchedule.setRunFrequency(scheduleReportForm.getScheduleFrequency());
		}
		if(ScheduleReportForm.DAILY_FREQUENCY.equals(scheduleReportForm.getScheduleFrequency())){
			reportSchedule.setRunInterval(scheduleReportForm.getRunIntervalDaily());
			reportSchedule.setEffectiveDate(populateDate(scheduleReportForm.getEffectiveDateDaily(), false));
			if (!StringUtil.isStringEmpty(scheduleReportForm.getExpirationDateDaily())) {
				reportSchedule.setExpirationDate(populateDate(scheduleReportForm.getExpirationDateDaily(), true));
			}
		}
		else
		if(ScheduleReportForm.WEEKLY_FREQUENCY.equals(scheduleReportForm.getScheduleFrequency())){
			reportSchedule.setRunInterval(scheduleReportForm.getRunIntervalWeekly());
			reportSchedule.setDayOfWeek(scheduleReportForm.getSpecificDayInWeek());
			reportSchedule.setEffectiveDate(populateDate(scheduleReportForm.getEffectiveDateWeekly(), false));
			if (!StringUtil.isStringEmpty(scheduleReportForm.getExpirationDateWeekly())) {
				reportSchedule.setExpirationDate(populateDate((scheduleReportForm.getExpirationDateWeekly()), true));				
			}
		}
		else
		if(ScheduleReportForm.MONTHLY_FREQUENCY.equals(scheduleReportForm.getScheduleFrequency())){
			reportSchedule.setRunInterval(scheduleReportForm.getRunIntervalMonthly());
			reportSchedule.setDayOfMonth(scheduleReportForm.getSpecificDayInMonth());
			reportSchedule.setEffectiveDate(populateDate(scheduleReportForm.getEffectiveDateMonthly(), false));
			if (!StringUtil.isStringEmpty(scheduleReportForm.getExpirationDateMonthly())) {
				reportSchedule.setExpirationDate(populateDate(scheduleReportForm.getExpirationDateMonthly(), true));
			}
		}
		
		reportSchedule.setPreferedTimezone(scheduleReportForm.getTimezone() != null ? scheduleReportForm.getTimezone().toString() : null);
		reportSchedule.setEmailReminderFlag("Y");
		reportSchedule.setCustomLeadMinutes(0);
		String country = LocaleUtil.getSupportLocaleCode(request.getLocale());
		reportSchedule.setCountry(country);
		reportSchedule.setRecepientEmail(PortalSessionUtil.getEmailAddress(request.getPortletSession()));
		reportSchedule.setUserNumber(PortalSessionUtil.getServiceUser(request.getPortletSession()).getUserNumber());
		
		//reportSchedule.setMdmId(PortalSessionUtil.getServiceUser(request.getPortletSession()).getMdmId());		// added for employee report
		//added for employee report
        MDMId = PortalSessionUtil.getServiceUser(request.getPortletSession()).getMdmId();
        MDMLevel  = PortalSessionUtil.getMdmLevel(request.getPortletSession());
        if(EMPLOYEE.equalsIgnoreCase(PortalSessionUtil.getUserSegment(request.getPortletSession()))) {
                MDMId = (String)request.getPortletSession().getAttribute("employeeReportMdmId", request.getPortletSession().APPLICATION_SCOPE);
                MDMLevel = (String)request.getPortletSession().getAttribute("employeeMdmLevel", request.getPortletSession().APPLICATION_SCOPE);
        }
        reportSchedule.setMdmId(MDMId);       // added for employee report
        reportSchedule.setMdmLevel(MDMLevel); // added for employee report
        //end of addition for employee report
		
		
		contract.setReportSchedule(reportSchedule);
		return contract;
	}
	
	/**
	 * @param definitionId 
	 * @param reportScheduleId 
	 * @param request 
	 * @param parameters 
	 * @return ScheduleReportContract
	 * @throws ParseException 
	 */
	public static ScheduleReportContract getRunThisReportContract(String definitionId, Integer reportScheduleId,
			PortletRequest request, Map<String, String> parameters) throws ParseException {

		ScheduleReportContract contract = new ScheduleReportContract();
		String mdmId = null;
		String mdmLevel = null;
		ReportSchedule reportSchedule = new ReportSchedule();
		reportSchedule.setId(reportScheduleId);
		//reportSchedule.setReportDefinitionId(new Integer(definitionId));
		reportSchedule.setReportDefinitionId(Integer.valueOf(definitionId));
		reportSchedule.setRunFrequency(ScheduleReportForm.RUN_NOW_FREQUENCY);
		reportSchedule.setRunInterval(1);
		reportSchedule.setEffectiveDate(new Date());
		//reportSchedule.setPreferedTimezone(preferedTimezone);
		reportSchedule.setEmailReminderFlag("Y");
		reportSchedule.setCustomLeadMinutes(0);

		String country = LocaleUtil.getSupportLocaleCode(request.getLocale());
		reportSchedule.setCountry(country);
		reportSchedule.setRecepientEmail(PortalSessionUtil.getEmailAddress(request.getPortletSession()));
		reportSchedule.setUserNumber(PortalSessionUtil.getServiceUser(request.getPortletSession()).getUserNumber());
		
		//reportSchedule.setMdmId(PortalSessionUtil.getServiceUser(request.getPortletSession()).getMdmId());	// added for employee report
		//added for employee report
        mdmId = PortalSessionUtil.getServiceUser(request.getPortletSession()).getMdmId();
        mdmLevel = PortalSessionUtil.getMdmLevel(request.getPortletSession());
        if(EMPLOYEE.equalsIgnoreCase(PortalSessionUtil.getUserSegment(request.getPortletSession()))) {
                mdmId = (String)request.getPortletSession().getAttribute("employeeReportMdmId", request.getPortletSession().APPLICATION_SCOPE);
                mdmLevel = (String)request.getPortletSession().getAttribute("employeeMdmLevel", request.getPortletSession().APPLICATION_SCOPE);
        }
        reportSchedule.setMdmId(mdmId);      
        reportSchedule.setMdmLevel(mdmLevel);
        //end of addition for employee report
		
		contract.setReportSchedule(reportSchedule);
		
		// populate report parameters
        for (Map.Entry<String, String> parameter : parameters.entrySet()) {
        	ReportScheduleParameter parameterValue = new ReportScheduleParameter();
        	parameterValue.setName(parameter.getKey());
        	parameterValue.setValue(parameter.getValue());
        	contract.getParameterValues().add(parameterValue);
        }
		
		return contract;
	}
	
	
	/**
	 * @param dateValue 
	 * @return String 
	 * @throws ParseException 
	 */
	private static String changeDateFormat(String dateValue) throws ParseException {
		// change the value form format MM/dd/yyyy to yyyy,MM,dd
		if(dateValue != null && dateValue.trim().length() > 0){
			try {
			Date date =  new SimpleDateFormat("MM/dd/yyyy").parse(dateValue);
			return 
				new SimpleDateFormat("yyyy,MM,dd").format(date);

			}catch(Exception e) {
				return null;
			}
		}
		return null;
	}	
	
	/**
	 * @param request 
	 * @param reportDefinitionId 
	 * @param reportScheduleId 
	 * @return ReportJob 
	 */
	public static ReportJob getReportJob(PortletRequest request, Integer reportDefinitionId, Integer reportScheduleId){
		PortletSession session = request.getPortletSession();
		ReportJob reportJob = new ReportJob();
		reportJob.setReportDefinitionId(reportDefinitionId.toString());
		reportJob.setMdmId(PortalSessionUtil.getMdmId(session));
		reportJob.setMdmLevel(PortalSessionUtil.getMdmLevel(session));	
		reportJob.setReportScheduleId(reportScheduleId);
		return reportJob;
	}










	/**
	 * @param categoryId 
	 * @return CategoryDetailContract 
	 */
	public static CategoryDetailContract getCategoryDetailContract(int categoryId) {
		CategoryDetailContract categoryDetailContract = new CategoryDetailContract();
		categoryDetailContract.setCategoryId(categoryId);
		return categoryDetailContract;
	}

	/**
	 * @param category 
	 * @return CategorySaveContract 
	 */
	public static CategorySaveContract getCategorySaveContract(RoleCategory category) {
		CategorySaveContract contract = new CategorySaveContract();
		contract.setCategory(category);
		return contract;
	}

	/**
	 * @param id 
	 * @return CategoryDeleteContract 
	 */
	public static CategoryDeleteContract getCategoryDeleteContract(int id) {
		CategoryDeleteContract categoryDeleteContract = new CategoryDeleteContract();
		categoryDeleteContract.setCategoryId(id);
		return categoryDeleteContract;
	}

	/**
	 * @param definitionId 
	 * @return DocumentDefinitionDetailsContract 
	 */
	public static DocumentDefinitionDetailsContract getDocumentDefinitionDetailsContract(int  definitionId) {
		DocumentDefinitionDetailsContract documentDefinitionDetailsContract = new DocumentDefinitionDetailsContract();
		documentDefinitionDetailsContract.setDefinitionId(definitionId);
		return documentDefinitionDetailsContract;
	}

	/**
	 * @param request 
	 * @param roles 
	 * @param locale 
	 * @param globalService 
	 * @return CategoryHierarchyContract 
	 */
	public static CategoryHierarchyContract getCategoryHierarchyContract(PortletRequest request, List<String> roles, List<String> partnerTypes, List<String> countryList, Locale locale, GlobalService globalService) {
  	    PortletSession session = request.getPortletSession();
		
		CategoryHierarchyContract contract = new CategoryHierarchyContract();
		String language = LocaleUtil.getSupportLocaleCode(locale);
		contract.setLocale(language);
		
		String mdmId = PortalSessionUtil.getMdmId(session);
	    String mdmLevel = PortalSessionUtil.getMdmLevel(session);
		
		GlobalServiceFacadeImpl globalServiceFacade = new GlobalServiceFacadeImpl();
		globalServiceFacade.setGlobalService(globalService);
		
		GlobalAccount account = globalServiceFacade.retriveGlobalAccount(mdmId, mdmLevel);
		//PerformanceTracker.endTracking(lexmarkTran);
		if(account != null) {
			contract.setMdmId(account.getMdmId());
			contract.setMdmLevel(account.getMdmLevel());
		} else {
			contract.setMdmId(mdmId);
			contract.setMdmLevel(mdmLevel);
		}
		
		LOGGER.debug("partner portal ="+request.getAttribute("partnerPortal"));
		LOGGER.debug("services portal ="+request.getAttribute("servicesPortal"));
		
		contract.setServicesPortal(request.getAttribute("servicesPortal")!=null?(Boolean)request.getAttribute("servicesPortal"):false);
		contract.setPartnerPortal(request.getAttribute("partnerPortal")!=null?(Boolean)request.getAttribute("partnerPortal"):false);
		
		contract.setRoles(roles);
		contract.setPartnerTypeList(partnerTypes);
		contract.setCountryList(countryList);
		return contract;
	}

	/**
	 * @param definitionId 
	 * @return DocumentUserListContract 
	 */
	public static DocumentUserListContract getDocumentUserListContract(int definitionId) {
		DocumentUserListContract documentUserListContract = new DocumentUserListContract();
		documentUserListContract.setDefinitionId(definitionId);
		return documentUserListContract;
	}

	/**
	 * @param legalName 
	 * @return GlobalLegalEntityByLegalNameContract 
	 */
	public static GlobalLegalEntityByLegalNameContract getGlobalLegalEntityByLegalNameContract(String legalName) {
		GlobalLegalEntityByLegalNameContract contract = new GlobalLegalEntityByLegalNameContract();
		contract.setLeagalName(legalName);
		return contract;
	}

	/**
	 * @param definition 
	 * @return DocumentDefinitionSaveContract 
	 */
	public static DocumentDefinitionSaveContract getDocumentDefinitionSaveContract(DocumentDefinition definition) {
		DocumentDefinitionSaveContract documentDefinitionSaveContract = new DocumentDefinitionSaveContract();
		documentDefinitionSaveContract.setDefinition(definition);
		return documentDefinitionSaveContract;
	}

	/**
	 * @param mdmId 
	 * @param mdmLevel 
	 * @return GlobalLegalEntityContract
	 */
	public static GlobalLegalEntityContract getGlobalLegalEntityContract(String mdmId, String mdmLevel) {
		GlobalLegalEntityContract globalLegalEntityContract = new GlobalLegalEntityContract();
		globalLegalEntityContract.setMdmId(mdmId);
		globalLegalEntityContract.setMdmLevel(mdmLevel);
		return globalLegalEntityContract;
	}

	/**
	 * @param reportDefinition 
	 * @return SaveReportDefinitionDetailContract 
	 */
	public static SaveReportDefinitionDetailContract getSaveReportDefinitionDetailContract(
			ReportDefinition reportDefinition) {

		SaveReportDefinitionDetailContract contract = new SaveReportDefinitionDetailContract();
		contract.setReportDefinition(reportDefinition);
		return contract;
	}

	/**
	 * @param reportDefinitionId 
	 * @return ReportDefinitionDetailContract 
	 */
	public static ReportDefinitionDetailContract getReportDefinitionDetailContract(
			Integer reportDefinitionId) {

		ReportDefinitionDetailContract contract = new ReportDefinitionDetailContract();
		contract.setReportDefinitionId(reportDefinitionId);
		return contract;
	}

	/**
	 * @param orderNumber 
	 * @param increment 
	 * @param categoryType 
	 * @return SwapCategoryOrderNumberContract
	 */
	public static SwapCategoryOrderNumberContract getSwapCategoryOrderNumberContract(
			int orderNumber, int increment, String categoryType) {

		SwapCategoryOrderNumberContract contract = new SwapCategoryOrderNumberContract();
		contract.setOrderNumber(orderNumber);
		contract.setIncrement(increment);
		contract.setCategoryType(categoryType);
		return contract;
	}

	/**
	 * @param definitionId 
	 * @return DocumentDefinitionDeleteContract 
	 */
	public static DocumentDefinitionDeleteContract getDocumentDefinitionDeleteContract(int definitionId) {
		DocumentDefinitionDeleteContract documentDefinitionDeleteContract = new DocumentDefinitionDeleteContract();
		documentDefinitionDeleteContract.setDefinitionId(definitionId);
		return documentDefinitionDeleteContract;
	}

	/**
	 * @param objectId 
	 * @return DocumentDeleteContract 
	 */
	public static DocumentDeleteContract getDocumentDeleteContract(String objectId) {
		DocumentDeleteContract documentDeleteContract = new DocumentDeleteContract();
		documentDeleteContract.setObjectId(objectId);
		return documentDeleteContract;
	}
	
	/**
	 * Populate Date in server by given date string and time zone offset.

	 * @throws ParseException  
	 */
	/**
	 * @param strDate 
	 * @param plus24Hours 
	 * @return Date 
	 * @throws ParseException 
	 */
	private static Date populateDate(String strDate, Boolean plus24Hours) throws ParseException {
		if(strDate != null){
			Date serverDate = DATE_FORMAT.parse(strDate);
			if(plus24Hours){
				Long microSeconds = new Long(24 * 60 * 60 * 1000 - 1);
				serverDate = new Date(serverDate.getTime() + microSeconds);
			}
			return serverDate;
		}
		return null;
	}
	
	/**
	 * @param obieeReportParameter 
	 * @param contactId 
	 * @return ObieeReportParameterContract 
	 */
	public static ObieeReportParameterContract getOracleReportContract(ObieeReportParameter obieeReportParameter, String contactId){
		ObieeReportParameterContract contract = new ObieeReportParameterContract();
		obieeReportParameter.setContactId(contactId);
		contract.setObieeReportParameter(obieeReportParameter);
		return contract;
	}

	/**
	 * @param siebelValue 
	 * @param locale 
	 * @return LocalizedEntitlementServiceDetailContract 
	 */
	public static LocalizedEntitlementServiceDetailContract getLocalizedEntitlementServiceDetailContract(
			String siebelValue, Locale locale) {

		LocalizedEntitlementServiceDetailContract contract = new LocalizedEntitlementServiceDetailContract();
		contract.setSiebelValue(siebelValue);
		contract.setLocale(locale);
		return contract;
	}

	
	
	/**
	 * @param request 
	 * @param historyPage 
	 * @param filterColumns 
	 * @return RequestListContract 
	 */
	@SuppressWarnings("unchecked")
	public static RequestListContract getHistoryListContract(ResourceRequest request, String historyPage, String[] filterColumns) {
		LOGGER.debug("ContractFactory.getHistoryListContract.historyPage:["+historyPage+"]");
		LOGGER.debug("fromFleetManager:["+request.getParameter("fromFleetManager")+"]");
		String searchCriterias = request.getParameter(SEARCH_CRITERIAS);
		PortletSession session = request.getPortletSession();
		RequestListContract requestListContract = new RequestListContract();
		
		
		requestListContract.setOpsPage(true);//Changes for only ops 
		Pagination pagination = PaginationUtil.getPainationFromRequest(request, filterColumns, historyPage);
      
		if(searchCriterias != null && searchCriterias.length()>0) {
			if(request.getParameter("fromFleetManager")!=null && request.getParameter("fromFleetManager").equalsIgnoreCase("true")){
				
				List<String> webStatus=new ArrayList<String>();
				String[] values = searchCriterias.split(SEARCH_CRITERIAS_SEPERATOR);
				for(int i=0;i<values.length;i++)
				{
					webStatus.add(values[i].trim());
					pagination.getFilterCriteria().put("webStatus", webStatus);
				}
			}
		}
				LOGGER.debug("PaginationUtil.SearchCriteriaMap FOR FLEET--->"+pagination.getSearchCriteria());
			
		
		SearchContractUtil.copyPaginationToContract(pagination, requestListContract);
		
		/**Added for 12366 MPS 2.1*/
		
		Map<String,String> accessMap = (Map<String, String>) session.getAttribute(ChangeMgmtConstant.USERACCESSMAPATTRIBUTEFORSR,PortletSession.APPLICATION_SCOPE);
		LOGGER.debug("ContractFactory.getHistoryListContract.accessMap--->"+accessMap);
		if(null != accessMap && !accessMap.isEmpty()){
			if(accessMap.containsKey("SHOW HARDWARE REQUEST") && accessMap.get("SHOW HARDWARE REQUEST").toString().equalsIgnoreCase("true")){
				requestListContract.setHardwareRequestsPermission(true);
			}
			if(accessMap.containsKey("SHOW CHANGE MGMT REQUEST") && accessMap.get("SHOW CHANGE MGMT REQUEST").toString().equalsIgnoreCase("true")){
				requestListContract.setChangeRequestsPermission(true);
			}
		}
	
		
	
		
		
		
		//Added By MPS Offshore Team for adding Vendor Flag
		Map<String,String> userDetailsMap=(Map<String,String>)session.getAttribute("accountCurrentDetails",PortletSession.APPLICATION_SCOPE);		
		
		//PARTHA ADDED for CI 7- Release : START
			requestListContract.setChlNodeId(PortalSessionUtil.getChlNodeId(session));
		//PARTHA ADDED for CI 7- Release : END
		
		
		//logger.info("isViewOrderFlag"+isViewOrderFlag);
		if(userDetailsMap != null)
		{
			List<String> userRoleList = PortalSessionUtil.getUserRoles(session);
		//	boolean isViewOrderFlag = Boolean.valueOf(userDetailsMap.get("isViewOrderFlag"));
			if(!userRoleList.isEmpty() && userDetailsMap.get("vendorAccountID")!=null && ((userRoleList.contains(LexmarkConstants.ROLE_SERVICE_MANAGER)) || (userRoleList.contains(LexmarkConstants.ROLE_SERVICE_TECHNICIAN)) || (userRoleList.contains(LexmarkConstants.ROLE_SERVICE_VIEW)) || (userRoleList.contains(LexmarkConstants.ROLE_ACCOUNTS_RECEIVABLE)) || (userRoleList.contains(LexmarkConstants.ROLE_PARTNER_ADMINISTRATOR))))
			{
				requestListContract.setVendorFlag(true);
				requestListContract.setVendorAccountId(userDetailsMap.get("vendorAccountID"));
				requestListContract.setMdmId(userDetailsMap.get("vendorAccountID"));
				requestListContract.setMdmLevel("Siebel");
				LOGGER.debug("MDM ID "+userDetailsMap.get("accountId"));
				LOGGER.debug("VendorAccountId "+userDetailsMap.get("vendorAccountID"));
			}else {
				requestListContract.setVendorFlag(false);
				requestListContract.setMdmId(PortalSessionUtil.getMdmId(session));
				//requestListContract.setMdmId("1-3S-445");// MDMID hard coded to test integration in siebel dev 
				
				requestListContract.setMdmLevel(PortalSessionUtil.getMdmLevel(session));
				
			}
		}else{
			requestListContract.setVendorFlag(false);
			requestListContract.setMdmId(PortalSessionUtil.getMdmId(session));
			//requestListContract.setMdmId("1-3S-445");// MDMID hard coded to test integration in siebel dev 
			
			requestListContract.setMdmLevel(PortalSessionUtil.getMdmLevel(session));
		}
		
		
		
		//End Add
		
		String pageType = request.getParameter("pageName");
		LOGGER.debug("ContractFactory.getHistoryListContract.pageType --->"+pageType);
		if (pageType != null) {
			if((LexmarkConstants.PAGETYPE_MYSRLIST).equalsIgnoreCase(pageType)) {
				requestListContract.setContactId(PortalSessionUtil.getContactId(session));
			}else	if ("MPS".equalsIgnoreCase(pageType)) {
				requestListContract.getFilterCriteria().put("assetType", "MPS");
			} else if ("CSS".equalsIgnoreCase(pageType)) {
				requestListContract.getFilterCriteria().put("assetType", "CSS");
			}
		}
		
		
		String queryType = request.getParameter("searchFilterCriteria");
		LOGGER.debug("ContractFactory.getHistoryListContract.queryType 1--->"+queryType);
		if(queryType == null || StringUtils.isEmpty(queryType) || queryType.equalsIgnoreCase("showall")){
			requestListContract.setShowAllFlag(true);	
		}else if(queryType.equalsIgnoreCase("myRequest")){
			requestListContract.setContactId(PortalSessionUtil.getContactId(session));			
		}else if(queryType.equalsIgnoreCase("bookmarkedAsset")){
			requestListContract.setAssetFavoriteFlag(true);
			requestListContract.setContactId(PortalSessionUtil.getContactId(request
					.getPortletSession()));
		}
		
		
		if (queryType != null) {
			if ((ChangeMgmtConstant.SEARCHTYPE_MYSRLIST)
					.equalsIgnoreCase(queryType)) {
				requestListContract.setContactId(PortalSessionUtil.getContactId(request
						.getPortletSession()));
			} 
		}
			
			
				String startDateStr = request.getParameter("startDate");
				String endDateStr = request.getParameter("endDate");
				//if(!StringUtil.isEmpty(startDateStr)&& !StringUtil.isEmpty(endDateStr)){
				LOGGER.debug("ContractFactory.getHistoryListContract.startDate--->"+startDateStr);
				LOGGER.debug("ContractFactory.getHistoryListContract.endDate--->"+endDateStr);
				
				float timezoneOffset = Float.valueOf(request.getParameter(LexmarkConstants.TIMEZONE_OFFSET));
				LOGGER.debug("timezoneOffset---->"+timezoneOffset);
				try {
					
					Date today = new Date();
					Calendar cal = new GregorianCalendar();
					cal.setTime(today);
					
					/* Added by Ranjan for LEX:AIR00074160 start */
					cal.add(Calendar.DAY_OF_MONTH, -15);
					Date before15Days = cal.getTime();
					//TimezoneUtil.adjustDate(before15Days, timezoneOffset);
					LOGGER.debug("ContractFactory.getHistoryListContract.before15Days--->"+before15Days);
					before15Days = LexmarkConstants.DEFAULT_DATE_FORMAT.parse(LexmarkConstants.DEFAULT_DATE_FORMAT.format(before15Days));
					/* Added by Ranjan for LEX:AIR00074160 end */


					LOGGER.debug("********* default date range End date ["+GetUTCdatetimeAsString()+"]");
//					LOGGER.debug("********* default date range Start date ["+before30Days+"]");  Commented by Ranjan for LEX:AIR00074160
					LOGGER.debug("********* default date range Start date ["+before15Days+"]");  //Added by Ranjan for LEX:AIR00074160
					
					LOGGER.debug("********* Setting default date range 15 days ******************");
					
//					TimezoneUtil.adjustDate(before30Days, timezoneOffset);  Commented by Ranjan for LEX:AIR00074160
					TimezoneUtil.adjustDate(before15Days, timezoneOffset);  //Added by Ranjan for LEX:AIR00074160
					TimezoneUtil.adjustDate(today, timezoneOffset);
					
//					LOGGER.debug("********* default date range Start date After Offset Adjustment ["+before30Days+"]");  Commented by Ranjan for LEX:AIR00074160
					LOGGER.debug("********* default date range Start date After Offset Adjustment ["+before15Days+"]");  //Added by Ranjan for LEX:AIR00074160
					LOGGER.debug("********* default date range End date After Offset Adjustment ["+GetUTCdatetimeAsString()+"]");
					Set<String> keySet=requestListContract.getFilterCriteria().keySet();

					if(keySet.size()==1){
						//This means only requestType filter is present
					/*Commented by Ranjan for LEX:AIR00074160
					requestListContract.getFilterCriteria().put(
							ChangeMgmtConstant.SEARCHTYPE_DATERANGE_STARTDATE,
							DateUtil.convertDateToGMTString(before30Days).toString()); */
					/*Added by Ranjan for LEX:AIR00074160 start */
					requestListContract.getFilterCriteria().put(ChangeMgmtConstant.SEARCHTYPE_DATERANGE_STARTDATE,DateUtil.convertDateToGMTString(before15Days).toString());
					/* Added by Ranjan for LEX:AIR00074160 */
					
					
					
					}else{
						//This means some other filter is also present
						cal.add(Calendar.DAY_OF_MONTH, 0);
						Date before90Days = cal.getTime();
						
						LOGGER.debug("ContractFactory.getHistoryListContract.before90Days--->"+before90Days);
						before90Days = LexmarkConstants.DEFAULT_DATE_FORMAT.parse(LexmarkConstants.DEFAULT_DATE_FORMAT.format(before90Days));
						LOGGER.debug("********* default date range Start date ["+before90Days+"]");  
						
						LOGGER.debug("********* Setting default date range 15 days ******************");
						

						TimezoneUtil.adjustDate(before90Days, timezoneOffset); 
						
						requestListContract.getFilterCriteria().put(ChangeMgmtConstant.SEARCHTYPE_DATERANGE_STARTDATE,DateUtil.convertDateToGMTString(before90Days).toString());
						
					}
					requestListContract.getFilterCriteria().put(ChangeMgmtConstant.SEARCHTYPE_DATERANGE_ENDDATE,GetUTCdatetimeAsString());
					
					LOGGER.debug("ContractFactory.FilterCriteriaMap for default dateRange---->"+requestListContract.getFilterCriteria());
					
					if (StringUtils.isNotEmpty(startDateStr)) {
						//Date startDate = LexmarkConstants.DEFAULT_DATE_FORMAT.parse(startDateStr);
						//TimezoneUtil.adjustDate(startDate, timezoneOffset);
						
						Date startDate = DateUtil.getStringToLocalizedDate(startDateStr, false, request.getLocale());//this is the locale specific date
						final String DATEFORMAT="MM/dd/yyyy";
						SimpleDateFormat dateFormatGMT = new SimpleDateFormat(DATEFORMAT);
						
						String gmtDateString=dateFormatGMT.format(startDate);						
						LOGGER.info("Gmt date string is " + gmtDateString);
						
						requestListContract.getFilterCriteria().put(ChangeMgmtConstant.SEARCHTYPE_DATERANGE_STARTDATE,gmtDateString);
					}

					if (StringUtils.isNotEmpty(endDateStr)) {
						/*Date endDate = LexmarkConstants.DEFAULT_DATE_FORMAT.parse(endDateStr);
						TimezoneUtil.adjustDate(endDate, timezoneOffset + 24);*/
						Date endDate = DateUtil.getStringToLocalizedDate(endDateStr, false, request.getLocale());
						final String DATEFORMAT="MM/dd/yyyy";
						SimpleDateFormat dateFormatGMT = new SimpleDateFormat(DATEFORMAT);
						
						//TimezoneUtil.adjustDate(endDate, timezoneOffset + 24);
						String gmtDateString=dateFormatGMT.format(endDate);						
						LOGGER.info("Gmt date string is " + gmtDateString);
						
						requestListContract.getFilterCriteria().put(ChangeMgmtConstant.SEARCHTYPE_DATERANGE_ENDDATE,gmtDateString);
						//LOGGER.debug("ContractFactory.getHistoryListContract.GMT endDate--->"+DateUtil.convertDateToGMTString(endDate).toString());
					}
				} catch (ParseException e) {
					throw new IllegalArgumentException("Illegal date format,startDate[" + startDateStr + "];endDate["
							+ endDateStr + "]");
				}
				
			List<String> areaList=new ArrayList<String>();
			if(historyPage.trim().equalsIgnoreCase(ChangeMgmtConstant.CHANGE_REQUESTS)){
				requestListContract.setChangeRequestFlag(true);
				requestListContract.setHardwareRequestFlag(false);
				}
			if(historyPage.trim().equalsIgnoreCase(ChangeMgmtConstant.SUPPLY_REQUESTS)){
				requestListContract.setChangeRequestFlag(false);
				requestListContract.setHardwareRequestFlag(false);
				}
			if(historyPage.trim().equalsIgnoreCase(ChangeMgmtConstant.HARDWARE_REQUESTS)){
				List<String> reqType=new ArrayList<String>();
				reqType.add(ChangeMgmtConstant.FLEET_MANAGEMENT);
				requestListContract.getFilterCriteria().put(ChangeMgmtConstant.REQUESTTYPE,reqType);
				
				
				if(requestListContract.getFilterCriteria().get(ChangeMgmtConstant.VALUE_AREA)!=null){
//					String area = (String)requestListContract.getFilterCriteria().get(ChangeMgmtConstant.VALUE_AREA);
//					if(ChangeMgmtConstant.HW_AREA.contains(area) || ChangeMgmtConstant.INSTALL_HW_AREA.contains(area)){
					areaList.add((String)requestListContract.getFilterCriteria().get(ChangeMgmtConstant.VALUE_AREA));
//					}
				}else{
					areaList.add(ChangeMgmtConstant.HW_AREA);
					areaList.add(ChangeMgmtConstant.INSTALL_HW_AREA);
				}
				requestListContract.getFilterCriteria().put(ChangeMgmtConstant.VALUE_AREA, areaList);
				requestListContract.setChangeRequestFlag(false);
				requestListContract.setHardwareRequestFlag(true);
			}
			
			if(requestListContract.getFilterCriteria().get(ChangeMgmtConstant.VALUE_AREA)!=null && !historyPage.trim().equalsIgnoreCase(ChangeMgmtConstant.HARDWARE_REQUESTS)){
				areaList.add((String)requestListContract.getFilterCriteria().get(ChangeMgmtConstant.VALUE_AREA));
				requestListContract.getFilterCriteria().put(ChangeMgmtConstant.VALUE_AREA, areaList);
			}
			
		//Added for LBS Request History
			String assId=request.getParameter(ChangeMgmtConstant.ASSETID);
			if(StringUtils.isNotBlank(assId)){
				requestListContract.getFilterCriteria().put(ChangeMgmtConstant.ASSETID, assId);	
			}
			if(request.getParameter("fromFleetManager")!=null && request.getParameter("fromFleetManager").equalsIgnoreCase("true")){
				LOGGER.debug("FROM FLEET MANAGEMENT NO DATE RANGE");
				if(StringUtils.isBlank(request.getParameter("searchCriterias"))){
					/* This denotes the request is NOT for Open Requests. Its for Request history.. So No Date Range.*/
					requestListContract.getFilterCriteria().remove(ChangeMgmtConstant.SEARCHTYPE_DATERANGE_STARTDATE);
					requestListContract.getFilterCriteria().remove(ChangeMgmtConstant.SEARCHTYPE_DATERANGE_ENDDATE);	
				}else{
					Date todayDt = new Date();
					Calendar dtCal = new GregorianCalendar();
					dtCal.setTime(todayDt);
					dtCal.add(Calendar.DAY_OF_MONTH, -90);
					Date before90Days = dtCal.getTime();
					TimezoneUtil.adjustDate(before90Days, timezoneOffset); 
					requestListContract.getFilterCriteria().put(ChangeMgmtConstant.SEARCHTYPE_DATERANGE_STARTDATE,DateUtil.convertDateToGMTString(before90Days));
					requestListContract.getFilterCriteria().put(ChangeMgmtConstant.SEARCHTYPE_DATERANGE_ENDDATE,DateUtil.convertDateToGMTString(todayDt));
							
				}
		
			}
		//End added for LBS	
			
			
		LOGGER.debug("Final ContractFactory.FilterCriteriaMap ---->"+requestListContract.getFilterCriteria());
		LOGGER.debug("Final ContractFactory.SearchCriteriaMap ---->"+requestListContract.getSearchCriteria());
		
		return requestListContract;
	}
	

	/**
	 * @return String 
	 */
	private static String GetUTCdatetimeAsString()
	{
		String DATEFORMAT = "MM/dd/yyyy HH:mm:ss" ;
	    final SimpleDateFormat sdf = new SimpleDateFormat(DATEFORMAT);
	    sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
	    final String utcTime = sdf.format(new Date());

	    return utcTime;
	}
	
	/**.
	 * This method returns SiebelLOVListContract object after setting the values.  
	 * @param lovName 
	 * @param errorCode 
	 * @param parentName 
	 * @return contract SiebelLOVListContract 
	 * @author Sagar Sarkar 
	 */
	public static SiebelLOVListContract createSiebelLOVListContract(
			String lovName, String errorCode, String parentName) {
		SiebelLOVListContract contract = new SiebelLOVListContract();
		contract.setLovName(lovName);
		contract.setErrorCode1(errorCode);
		contract.setParentName(parentName);
		
		return contract;
	}
	
	
	/**
	 * @param srNumber 
	 * @return ServiceRequestContract 
	 */
	public static ServiceRequestContract getServiceRequestDetailsContract(String srNumber){
		ServiceRequestContract contract = new ServiceRequestContract();
		LOGGER.debug("ContractFactory.getServiceRequestDetailsContract.requestNumber-->"+srNumber);
		contract.setRequestNumber(srNumber);
		
		
		
		return contract;
	}
	
	
	/**
	 * @param lovListName 
	 * @param lovValue 
	 * @param localeName 
	 * @return LocalizedSiebelValueContract 
	 */
	public static  LocalizedSiebelValueContract createLocalizedSiebelValueContract(String lovListName, String lovValue, Locale localeName){
		LocalizedSiebelValueContract contract = new LocalizedSiebelValueContract();
		contract.setLocaleName(LocaleUtil.getSupportLocaleCode(localeName));
		contract.setLovListName(lovListName);
		contract.setLovValue(lovValue);
		
		return contract;
		
	}
	
	
	/**
	 * @param lovType 
	 * @param partnerType 
	 * @param locale 
	 * @return LocalizedSiebelLOVListContract 
	 */
	public static LocalizedSiebelLOVListContract createLocalizedSiebelLOVListContract(
			String lovType, String partnerType, Locale locale) {
		LocalizedSiebelLOVListContract contract = new LocalizedSiebelLOVListContract();
		contract.setLocaleName(LocaleUtil.getSupportLocaleCode(locale));
		contract.setLovListName(lovType);
		contract.setPartnerType(partnerType);
		return contract;
	}
	
	/**
	 * @return AssetContract 
	 */
	public static AssetContract getAssetOrderDetailContract(){
		AssetContract contract = new AssetContract();
		return contract;
	}
	
	/**
	 * @param request 
	 * @return AssetContract 
	 */
	public static AssetContract getAssetOrderDetailContract(RenderRequest request){
		AssetContract contract = new AssetContract();
		return contract;
	}
	
	/**
	 * @param contractData 
	 */
	public static void printContractDetail(SearchContractBase contractData)
	{
		LOGGER.debug("\n \n \n \n \n");
		
		LOGGER.debug("****************Starting to Print the CONTRACT Data******************");
		if(contractData instanceof ServiceRequestHistoryListContract){
			LOGGER.debug("***************** REQUEST IS OF TYPE SR Request History **************");
			
			LOGGER.debug("MDM ID ----------------- " + ((ServiceRequestHistoryListContract)contractData).getMdmID());
			
			LOGGER.debug("MDM LEVEL --------------- " + ((ServiceRequestHistoryListContract)contractData).getMdmLevel());
				
			LOGGER.debug("LOCALE --------------- " + ((ServiceRequestHistoryListContract)contractData).getLocale());
			
			LOGGER.debug("SORT CRITERIA ------------- " + ((ServiceRequestHistoryListContract)contractData).getSortCriteria());
			
			LOGGER.debug("FILTER CRITERIA ------------- " + ((ServiceRequestHistoryListContract)contractData).getFilterCriteria());
			
			LOGGER.debug("START RECORD NO ------------- " + ((ServiceRequestHistoryListContract)contractData).getStartRecordNumber());
			
			LOGGER.debug("NEW QUERY INDICATOR ------------- " + ((ServiceRequestHistoryListContract)contractData).isNewQueryIndicator());
			
			LOGGER.debug("INCREMENT ------------- " + ((ServiceRequestHistoryListContract)contractData).getIncrement());
			
			LOGGER.debug("***************** Ends **************");
		}
		if(contractData instanceof ServiceRequestAssociatedListContract){
			LOGGER.debug("***************** REQUEST IS OF TYPE ServiceRequestAssociatedList **************");
			
			LOGGER.debug("MDM ID ----------------- " + ((ServiceRequestAssociatedListContract)contractData).getMdmID());
			
			LOGGER.debug("MDM LEVEL --------------- " + ((ServiceRequestAssociatedListContract)contractData).getMdmLevel());
				
			LOGGER.debug("LOCALE --------------- " + ((ServiceRequestAssociatedListContract)contractData).getLocale());
			
			LOGGER.debug("SORT CRITERIA ------------- " + ((ServiceRequestAssociatedListContract)contractData).getSortCriteria());
			
			LOGGER.debug("FILTER CRITERIA ------------- " + ((ServiceRequestAssociatedListContract)contractData).getFilterCriteria());
			
			LOGGER.debug("START RECORD NO ------------- " + ((ServiceRequestAssociatedListContract)contractData).getStartRecordNumber());
			
			LOGGER.debug("NEW QUERY INDICATOR ------------- " + ((ServiceRequestAssociatedListContract)contractData).isNewQueryIndicator());
			
			LOGGER.debug("INCREMENT ------------- " + ((ServiceRequestAssociatedListContract)contractData).getIncrement());
			
			LOGGER.debug("***************** Ends **************");
		}
		if(contractData instanceof AssetListContract)
		{
			LOGGER.debug("***************** REQUEST IS OF TYPE ASSET LIST **************");
			
			LOGGER.debug("MDM ID ----------------- " + ((AssetListContract)contractData).getMdmId());
			
			LOGGER.debug("MDM LEVEL --------------- " + ((AssetListContract)contractData).getMdmLevel());
			
			LOGGER.debug("CONTACT ID --------------- " + ((AssetListContract)contractData).getContactId());
			
			LOGGER.debug("CHL NODE ID --------------- " + ((AssetListContract)contractData).getChlNodeId());
			
			LOGGER.debug("LOCALE --------------- " + ((AssetListContract)contractData).getLocale());
			
			LOGGER.debug("SORT CRITERIA ------------- " + contractData.getSortCriteria());
			
			LOGGER.debug("FILTER CRITERIA ------------- " + contractData.getFilterCriteria());
			
			LOGGER.debug("START RECORD NO ------------- " + contractData.getStartRecordNumber());
		}
		
		if(contractData instanceof AddressListContract)
		{
			LOGGER.debug("***************** REQUEST IS OF TYPE ADDRESS LIST **************");
			
			LOGGER.debug("MDM ID ----------------- " + ((AddressListContract)contractData).getMdmId());
			
			LOGGER.debug("MDM LEVEL --------------- " + ((AddressListContract)contractData).getMdmLevel());
			
			LOGGER.debug("CONTACT ID --------------- " + ((AddressListContract)contractData).getContactId());
			
			LOGGER.debug("LOCALE --------------- " + ((AddressListContract)contractData).getLocale());
			
			LOGGER.debug("SORT CRITERIA ------------- " + ((AddressListContract)contractData).getSortCriteria());
			
			LOGGER.debug("FILTER CRITERIA ------------- " + ((AddressListContract)contractData).getFilterCriteria());
			
			LOGGER.debug("START RECORD NO ------------- " + ((AddressListContract)contractData).getStartRecordNumber());
			
			LOGGER.debug("NEW QUERY INDICATOR ------------- " + ((AddressListContract)contractData).isNewQueryIndicator());
			
			LOGGER.debug("INCREMENT ------------- " + ((AddressListContract)contractData).getIncrement());
			
		}
		
		if(contractData instanceof ContactListContract)
		{
			LOGGER.debug("***************** REQUEST IS OF TYPE CONTACT LIST **************");
			
			LOGGER.debug("MDM ID ----------------- " + ((ContactListContract)contractData).getMdmId());
			
			LOGGER.debug("MDM LEVEL --------------- " + ((ContactListContract)contractData).getMdmLevel());
			
			LOGGER.debug("CONTACT ID --------------- " + ((ContactListContract)contractData).getContactId());
			
			LOGGER.debug("LOCALE --------------- " + ((ContactListContract)contractData).getLocale());
			
			LOGGER.debug("SORT CRITERIA ------------- " + contractData.getSortCriteria());
			
			LOGGER.debug("FILTER CRITERIA ------------- " + contractData.getFilterCriteria());
			
			LOGGER.debug("START RECORD NO ------------- " + contractData.getStartRecordNumber());
		}
		
		LOGGER.debug("****************Ending printing of the CONTRACT Data******************");
		LOGGER.debug("\n \n \n \n \n");
	}
	
	
	/**
	 * @param manageReturnsForm 
	 * @param request 
	 * @return ReturnServiceRequestContract 
	 */
	public static ReturnServiceRequestContract getReturnOrderRequestContract(ManageReturnsForm manageReturnsForm, ActionRequest request){
		ReturnServiceRequestContract contract = new ReturnServiceRequestContract();
		
		
		contract.setMdmId(PortalSessionUtil.getMdmId(request.getPortletSession()));
		contract.setMdmLevel(PortalSessionUtil.getMdmLevel(request.getPortletSession()));
		
		List<String> userRoleList = PortalSessionUtil.getUserRoles(request.getPortletSession());		
		
		if(!userRoleList.isEmpty() && ((userRoleList.contains(LexmarkConstants.ROLE_SERVICE_MANAGER)) || (userRoleList.contains(LexmarkConstants.ROLE_SERVICE_TECHNICIAN))))
		{
			contract.setVendorID(PortalSessionUtil.getVendorAccountID(request.getPortletSession()));//Added By MPS Offshore team for adding Vendor ID
		}else {
			contract.setVendorID("");//Added By MPS Offshore team for adding Vendor ID
		}

		if(PortalSessionUtil.getUserType(request.getPortletSession()).equalsIgnoreCase("Vendor")){
			contract.setUserType(ChangeMgmtConstant.VENDORREQTYPE);
		}else if(PortalSessionUtil.getUserType(request.getPortletSession()).equalsIgnoreCase("Employee")){
			contract.setUserType(ChangeMgmtConstant.LEXMARKREQTYPE);
		}else{
			contract.setUserType(PortalSessionUtil.getUserType(request.getPortletSession()));
		}
		
		LOGGER.debug("[getReturnOrderRequestContract]User Type ----->"+contract.getUserType());
		
		LOGGER.debug("ContractFactory.getReturnOrderRequestContract--->"+manageReturnsForm.getReturnsSubArea());
		ServiceRequest serviceRequest = manageReturnsForm.getServiceRequest();
		
		
		AccountContact requestor = new AccountContact();
		
			requestor.setContactId(PortalSessionUtil.getContactId(request
				.getPortletSession()));
			requestor.setFirstName(PortalSessionUtil.getFirstName(request
				.getPortletSession()));
			requestor.setLastName(PortalSessionUtil.getLastName(request
				.getPortletSession()));
			requestor.setWorkPhone(PortalSessionUtil.getWorkPhone(request
				.getPortletSession()));
			requestor.setEmailAddress(PortalSessionUtil
				.getEmailAddress(request.getPortletSession()));
			
			serviceRequest.setRequestor(requestor);
			serviceRequest.setNotes(manageReturnsForm.getNotes());

			ListOfValues lovArea = new ListOfValues();
			ListOfValues lovSubArea = new ListOfValues();
			ListOfValues lovSRType = new ListOfValues();
				
			lovArea.setValue("Consumables Return");
			serviceRequest.setArea(lovArea);
				
			lovSubArea.setValue(manageReturnsForm.getReturnsSubArea());
			serviceRequest.setSubArea(lovSubArea);
			
			lovSRType.setValue("Consumables Management");
			serviceRequest.setServiceRequestType(lovSRType);
			
			contract.setReturnAddress(manageReturnsForm.getReturnAddress());
			
			
			
			contract.setServiceRequest(serviceRequest);
			
			
			
			
		return contract;
	}
	
	

	
	/**
	 * @param formData1 
	 * @param formData2 
	 * @param request 
	 * @return CreateConsumableServiceRequestContract 
	 */
	public static CreateConsumableServiceRequestContract getConsumableServiceReqContract(Object formData1, Object formData2, RenderRequest request) {
		CreateConsumableServiceRequestContract contract = new CreateConsumableServiceRequestContract();
		//Added for source by MPS offshore
		List<String> userRoleList = PortalSessionUtil.getUserRoles(request.getPortletSession());		
		
		if(!userRoleList.isEmpty() && ((userRoleList.contains(LexmarkConstants.ROLE_SERVICE_MANAGER)) || (userRoleList.contains(LexmarkConstants.ROLE_SERVICE_TECHNICIAN))))
		{
			contract.setVendorName(PortalSessionUtil.getVendorAccountID(request.getPortletSession()));//Added By MPS Offshore team for adding Vendor ID			
		}else {
			contract.setVendorName("");//Added By MPS Offshore team for adding Vendor ID
		}
		//End
		//LBS
		/*String fleetManagementFlag = (String)request.getAttribute("fleetManagementFlag");
		if(fleetManagementFlag != null && fleetManagementFlag.equalsIgnoreCase("true")){
			LOGGER.debug("Setting LBS Flag to true ");
			contract.setFleetManagementFlag(fleetManagementFlag);
		}
		else{
			LOGGER.debug("Setting LBS Flag to false");
			contract.setFleetManagementFlag("false");
		}*/
		if(((AssetDetailPageForm)formData1).getFleetManagementFlag()!=null&&((AssetDetailPageForm)formData1).getFleetManagementFlag()!=""&&((AssetDetailPageForm)formData1).getFleetManagementFlag().equalsIgnoreCase("true")){
			LOGGER.debug("Setting LBS Flag to true ");
			contract.setFleetManagementFlag(((AssetDetailPageForm)formData1).getFleetManagementFlag());
		}
		else{
			LOGGER.debug("Setting LBS Flag to false");
			contract.setFleetManagementFlag("false");
		
		}
		PortletSession session = request.getPortletSession();
		Map<String,String> assetDetails=(Map<String,String>)session.getAttribute(ChangeMgmtConstant.ASSETCURRDETAILS,PortletSession.APPLICATION_SCOPE);
		String soldToNumber = "";
		String  billToNumber = "";
		List<Part> assetPartList= ((AssetDetailPageForm)formData2).getAssetPartList();
		List<Part> modifiedAssetPartList= new ArrayList<Part>();
		 for (Part part : assetPartList) {
			if(!"".equalsIgnoreCase(part.getOrderQuantity().trim()) && !"0".equalsIgnoreCase(part.getOrderQuantity())  && part.getOrderQuantity() != null){
				modifiedAssetPartList.add(part);
				
			}
		}
		if(assetDetails != null){
			soldToNumber = assetDetails.get("soldToNumber");
			billToNumber = assetDetails.get("billToNumber");			
			contract.setSoldToNumber(soldToNumber);
			contract.setBillToNumber(billToNumber);
		}else{//Populating Sold To for pay later/Consumable purchase
			String soldToNumberCP=(String)session.getAttribute(ChangeMgmtConstant.PAY_LATER_SOLDTONUMBER,PortletSession.APPLICATION_SCOPE);
			contract.setSoldToNumber(soldToNumberCP);
			LOGGER.debug("Sold To for pay later "+ soldToNumberCP);
		}
		
				
		contract.setConsumableOrderType("assetbased");//This is used only to differentiate between asset and catalog based order
		contract.setContactId(PortalSessionUtil.getContactId(request.getPortletSession()));
		contract.setMdmId(PortalSessionUtil.getMdmId(request.getPortletSession()));
		contract.setMdmLevel(PortalSessionUtil.getMdmLevel(request.getPortletSession()));
		
		
		
		contract.setVendorName(PortalSessionUtil.getVendorAccountID(request.getPortletSession()));//Added By MPS Offshore team for adding Vendor ID
		contract.setServiceRequest(((AssetDetailPageForm)formData1).getServiceRequest());
		contract.setAssetSerialNumber(((AssetDetailPageForm)formData1).getAsset().getSerialNumber());
		contract.setPrinterProductNumber(((AssetDetailPageForm)formData1).getAsset().getProductTLI());
		contract.setPageCountList(sendAssetPageCount(((AssetDetailPageForm)formData2).getPageCountsList()));
		//contract.setAssetPartList(((AssetDetailPageForm)formData2).getAssetPartList());
		// Changed for Blank check in order qty
		contract.setAssetPartList(modifiedAssetPartList);
		//added To convert the date in MM/dd/yyyy format
				List<PageCounts> newAssetPageCountList = new ArrayList<PageCounts>();
				for(int i=0;i<contract.getPageCountList().size();i++)
				{
					String date = contract.getPageCountList().get(i).getDate();
					LOGGER.debug("the date is --->>>"+date);
					PageCounts pageCounts = new PageCounts();
					pageCounts.setName(contract.getPageCountList().get(i).getName());
					pageCounts.setCount(contract.getPageCountList().get(i).getCount());
					if(date !=null  && !("".equals(date))){  //identified
						try{
						String dateFormatterLocaleSpecific = DateUtil.getDateFormatByLang(request.getLocale().getLanguage());
						LOGGER.debug("The dateFormatterLocaleSpecific is ---->>>"+dateFormatterLocaleSpecific);
						dateFormatterLocaleSpecific= dateFormatterLocaleSpecific +" HH:mm:ss";
						DateFormat formatter = null;
				        formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss", Locale.ENGLISH);
				        date=formatter.format(new SimpleDateFormat(dateFormatterLocaleSpecific).parse(date));
				        LOGGER.debug("date after---->>>"+date);							
						pageCounts.setDate(DateUtil.converDateToGMTString((Date) formatter.parse(date),Float.parseFloat(request.getParameter(LexmarkConstants.TIMEZONE_OFFSET))));
						pageCounts.setSiebelName(contract.getPageCountList().get(i).getSiebelName());
						LOGGER.debug("The page GMT Date is---->>>"+DateUtil.converDateToGMTString((Date) formatter.parse(date),Float.parseFloat(request.getParameter(LexmarkConstants.TIMEZONE_OFFSET))));						
						newAssetPageCountList.add(pageCounts);
						}
						catch(Exception e)
						{
							LOGGER.debug("Exception occured");							
						}
						}else{
							pageCounts.setDate("");
						}
				}
				contract.setPageCountList(newAssetPageCountList);
				
				//ends here

		///contract.setAssetPartList(((AssetDetailPageForm)formData2).getAssetPartList());
			LOGGER.debug("Asset Installation true");
		
		/*Changes for JAN release Parts to be
		 * installed flag
		 * */
		String area = null;
		if(!((AssetDetailPageForm)formData1).getAsset().getInstallationOnlyFlag()){
			/** Area populated by offshore **/
			LOGGER.debug("Asset Installation true");
			if(!contract.getAssetPartList().isEmpty()){
				LOGGER.debug("AssetPart List not empty");
				/* Added by sankha for LEX:AIR00076981 */
				for(Iterator<Part> j = contract.getAssetPartList().iterator(); j.hasNext();){
					Part part = j.next();
					area = part.getSupplyType();
					LOGGER.debug("@@ Area :: "+area);
					if(null == area){
						LOGGER.debug("Need to replace Area with blank value");
						area = "";						
					}
					if(area != "" && area.length()>0){
						break;
					}
					else if(area == ""){
						continue;
					}
				}
				
				LOGGER.error("Area value:::"+ area);
			}
			
			/* Added by sankha for LEX:AIR00076981 */
			for(Iterator<Part> k = contract.getAssetPartList().iterator(); k.hasNext();){
				
				Part part = k.next();
				String area_temp = part.getSupplyType();
				LOGGER.debug("@@ Temp Area :: "+area_temp);
				if("".equals(area_temp) || null==area_temp){
					continue;	
				}
				LOGGER.debug("Temp Area is : "+area_temp);
				LOGGER.debug("area value for previous one is "+area);
				LOGGER.debug("Area value coming for other than first part "+area_temp);
				if(area_temp!=null && !"".equals(area_temp)){
					LOGGER.debug("area_temp value:::"+ area_temp);
					if(!area_temp.equalsIgnoreCase(area)){//that means area should be sent as Consumable Mix Parts Request break the loop
						LOGGER.debug("Area should be sent as Consumable Mix Parts Request");
						area = ChangeMgmtConstant.OM_AREA_MIX_PARTS;
						LOGGER.debug("Area now is :: "+area);
						LOGGER.debug("do not need any more iteration breaking the loop");
						break;
					}else{
						//area value should remain same so do nothing
						LOGGER.debug("Else part");
					}
				}
				LOGGER.debug("Area value:::"+ area);
			}
			/*End LEX:AIR00076981*/
		}else{
			LOGGER.debug("Consumables Install Request ONLY");
			area=ChangeMgmtConstant.OM_SR_TYPE_PARTS_INSTALLED;
			LOGGER.debug("** area IS :::"+area);
		}
		//Ends
		
		
		LOGGER.error("Final Area value:::"+ area);
		contract.setSrArea(area);
		/** Area populated by offshore **/
		contract.setSrType(ChangeMgmtConstant.OM_SR_TYPE);//consumable
		contract.setSrSubArea(ChangeMgmtConstant.OM_SUB_AREA_ASSET);		
		contract.setShipToAddress(((AssetDetailPageForm)formData1).getShipToAddress());
		contract.setSpecialInstruction(((AssetDetailPageForm)formData1).getSpecialInstruction());
		contract.setExpediteOrder((((AssetDetailPageForm)formData1).isRequestExpediteOrder())?"Y":"N");
		
		
		contract.setRequestedDeliveryDate(((AssetDetailPageForm)formData1).getRequestedDeliveryDate());
		if(request.getLocale()!=null){
			contract.setRequestedDeliveryDate(DateUtil.convertDateStringToGMTDateString(contract.getRequestedDeliveryDate(), request.getLocale().getLanguage()));
		}
		
		if(((AssetDetailPageForm)formData1).getAsset().getPoNumber()!=null && ((AssetDetailPageForm)formData1).getAsset().getPoNumber()!="" && ((AssetDetailPageForm)formData1).getAsset().getPoNumber()!=","){
			LOGGER.error("PO Number :::"+ ((AssetDetailPageForm)formData1).getAsset().getPoNumber());
			contract.setPoNumber(((AssetDetailPageForm)formData1).getAsset().getPoNumber());
		}
		
		if(((AssetDetailPageForm)formData1).getPaymentMethod()!=null && ((AssetDetailPageForm)formData1).getPaymentMethod()!=""){
			contract.setPaymentMethod(((AssetDetailPageForm)formData1).getPaymentMethod());
		}
		
		if(((AssetDetailPageForm)formData1).getCreditCardToken()!=null && ((AssetDetailPageForm)formData1).getCreditCardToken()!="" && ((AssetDetailPageForm)formData1).getCreditCardToken()!=","){
			contract.setCreditCardToken(((AssetDetailPageForm)formData1).getCreditCardToken());
			contract.setCreditCardEncryptedNo(((AssetDetailPageForm)formData1).getCreditCardEncryptedNo());
			contract.setCreditCardExpirationDate(((AssetDetailPageForm)formData1).getCreditCardExpirationDate());
			contract.setCreditCardType(((AssetDetailPageForm)formData1).getCreditCardType());
			contract.setCardHolderName(((AssetDetailPageForm)formData1).getCardHoldername());
			contract.setTransactionId(((AssetDetailPageForm)formData1).getTransactionId());
		}
		//contract.setPoNumber(((AssetDetailPageForm)formData1).getAsset().getPoNumber());
		contract.setAttachmentNotes(((AssetDetailPageForm)formData1).getAttachmentDescription());
		//Added for JAN release MPS 
		//Y means checkbox selected N means checkbox not selected
		contract.setInstallationOnlyFlag((((AssetDetailPageForm)formData1).getAsset().getInstallationOnlyFlag())?"Y":"N");
		contract.setPartsToBeInstalledDescription(((AssetDetailPageForm)formData1).getAsset().getPartsToBeInstalled());
		//Jan release ends
		
		//Added for source by MPS offshore
		if(PortalSessionUtil.getUserType(request.getPortletSession()).equalsIgnoreCase("Vendor")){
			contract.setUserType(ChangeMgmtConstant.VENDORREQTYPE);
		}else if(PortalSessionUtil.getUserType(request.getPortletSession()).equalsIgnoreCase("Employee")){
			contract.setUserType(ChangeMgmtConstant.LEXMARKREQTYPE);
		}else{
			contract.setUserType(PortalSessionUtil.getUserType(request.getPortletSession()));
		}
		
		LOGGER.debug("[getConsumableServiceReqContract]User Type ----->"+contract.getUserType());
		
		//End
		contract.setSRStatus(ChangeMgmtConstant.OM_SR_STATUS_OPEN);//SaveDraft/CreateNew
		
		LOGGER.debug("getConsumableServiceReqContract.Related Service request number --->"+((AssetDetailPageForm)formData1).getRelatedServiceRequestedNumber());
		contract.setRelatedServiceRequestedNumber(((AssetDetailPageForm)formData1).getRelatedServiceRequestedNumber());
		contract.setRelatedServiceRequestedRowId(((AssetDetailPageForm)formData1).getRelatedServiceRequestedRowId());
		contract.setBillToAddress(((AssetDetailPageForm)formData1).getBillToAddress());
		contract.setBillingModel(((AssetDetailPageForm)formData1).getSelectedPaymentTypeId());
		contract.setContractNumber(((AssetDetailPageForm)formData1).getContractNumber());
		contract.setAssetId(((AssetDetailPageForm)formData1).getAsset().getAssetId());
		return contract;
	}	
	
	
	
	/**
	 * @param formData1 
	 * @param formData2 
	 * @param request 
	 * @return CreateConsumableServiceRequestContract 
	 */
	public static CreateConsumableServiceRequestContract getCatalogServiceReqContract(Object formData1, Object formData2, RenderRequest request) {
		CreateConsumableServiceRequestContract contract = new CreateConsumableServiceRequestContract();
		//Added for source by MPS offshore
		List<String> userRoleList = PortalSessionUtil.getUserRoles(request.getPortletSession());		
		
		if(!userRoleList.isEmpty() && ((userRoleList.contains(LexmarkConstants.ROLE_SERVICE_MANAGER)) || (userRoleList.contains(LexmarkConstants.ROLE_SERVICE_TECHNICIAN))))
		{
			contract.setVendorName(PortalSessionUtil.getVendorAccountID(request.getPortletSession()));//Added By MPS Offshore team for adding Vendor ID			
		}else {
			contract.setVendorName("");//Added By MPS Offshore team for adding Vendor ID
		}
		//End
		contract.setCatalogPartList(((CatalogDetailPageForm)formData1).getCatalogPartList());
		/** Area populated by offshore **/
		String area = null;
		if(!contract.getCatalogPartList().isEmpty()){
			area = contract.getCatalogPartList().get(0).getConsumableType();
			if(area == null){
				area = "";
			}
			LOGGER.debug("Area value:::"+ area);
		}
		
		for(OrderPart orderPart : contract.getCatalogPartList()){
			
			String area_temp = orderPart.getConsumableType();
			
			if(area_temp!=null && !"".equals(area_temp)){
				LOGGER.debug("area_temp value:::"+ area_temp);
				if(!area_temp.equalsIgnoreCase(area)){//that means area should be sent as Consumable Mix Parts Request break the loop
					area = ChangeMgmtConstant.OM_AREA_MIX_PARTS;
					LOGGER.debug("do not need any more iteration breaking the loop");
					break;
				}else{
					//area value should remain same so do nothing
				}
			}
			LOGGER.debug("Area value:::"+ area);
		}
		LOGGER.debug("Final Area value:::"+ area);
		
		PortletSession session = request.getPortletSession();
		Map<String,String> catalogDetails=(Map<String,String>)session.getAttribute("catalogCurrentDetails",PortletSession.APPLICATION_SCOPE);
		String soldToNumber = "";
		String  billToNumber = "";
		if(catalogDetails != null){
			soldToNumber = catalogDetails.get("soldToNumber");
			billToNumber = catalogDetails.get("billToNumber");			
			contract.setSoldToNumber(soldToNumber);
			contract.setBillToNumber(billToNumber);
		}
		
		
		contract.setSrArea(area);
		/** Area populated by offshore **/
		contract.setConsumableOrderType("catalogbased");//This is used only to differentiate between asset and catalog based order
		contract.setContactId(PortalSessionUtil.getContactId(request.getPortletSession()));
		contract.setMdmId(PortalSessionUtil.getMdmId(request.getPortletSession()));
		contract.setMdmLevel(PortalSessionUtil.getMdmLevel(request.getPortletSession()));
		contract.setSrType(ChangeMgmtConstant.OM_SR_TYPE);//consumable
		contract.setSrSubArea(ChangeMgmtConstant.OM_SUB_AREA_CATALOG);//
		contract.setServiceRequest(((CatalogDetailPageForm)formData1).getServiceRequest());
		contract.setShipToAddress(((CatalogDetailPageForm)formData1).getShipToAddress());
		contract.setSpecialInstruction(((CatalogDetailPageForm)formData1).getSpecialInstruction());
		contract.setExpediteOrder((((CatalogDetailPageForm)formData1).isRequestExpediteOrder())?"Y":"N");
		contract.setRequestedDeliveryDate(((CatalogDetailPageForm)formData1).getRequestedDeliveryDate());
		if(request.getLocale()!=null){
			contract.setRequestedDeliveryDate(DateUtil.convertDateStringToGMTDateString(contract.getRequestedDeliveryDate(), request.getLocale().getLanguage()));
		}
		
		if(((CatalogDetailPageForm)formData1).getPoNumber()!=null && ((CatalogDetailPageForm)formData1).getPoNumber()!="" && ((CatalogDetailPageForm)formData1).getPoNumber()!=","){
			contract.setPoNumber(((CatalogDetailPageForm)formData1).getPoNumber());
		}
		if(((CatalogDetailPageForm)formData1).getPaymentMethod()!=null && ((CatalogDetailPageForm)formData1).getPaymentMethod()!=""){
			contract.setPaymentMethod(((CatalogDetailPageForm)formData1).getPaymentMethod());
		}
		if(((CatalogDetailPageForm)formData1).getCreditCardToken()!=null && ((CatalogDetailPageForm)formData1).getCreditCardToken()!="" && ((CatalogDetailPageForm)formData1).getCreditCardToken()!=","){
			contract.setCreditCardToken(((CatalogDetailPageForm)formData1).getCreditCardToken());
			contract.setCreditCardEncryptedNo(((CatalogDetailPageForm)formData1).getCreditCardEncryptedNo());
			contract.setCreditCardExpirationDate(((CatalogDetailPageForm)formData1).getCreditCardExpirationDate());
			contract.setCreditCardType(((CatalogDetailPageForm)formData1).getCreditCardType());
			contract.setCardHolderName(((CatalogDetailPageForm)formData1).getCardHoldername());
			contract.setTransactionId(((CatalogDetailPageForm)formData1).getTransactionId());
		}
		contract.setAttachmentNotes(((CatalogDetailPageForm)formData1).getAttachmentDescription());
		//Added for source by MPS offshore
		if(PortalSessionUtil.getUserType(request.getPortletSession()).equalsIgnoreCase("Vendor")){
			contract.setUserType(ChangeMgmtConstant.VENDORREQTYPE);
		}else if(PortalSessionUtil.getUserType(request.getPortletSession()).equalsIgnoreCase("Employee")){
			contract.setUserType(ChangeMgmtConstant.LEXMARKREQTYPE);
		}else{
			contract.setUserType(PortalSessionUtil.getUserType(request.getPortletSession()));
		}
		
		LOGGER.debug("[getCatalogServiceReqContract]User Type ----->"+contract.getUserType());
		
		//End
		contract.setSRStatus(ChangeMgmtConstant.OM_SR_STATUS_OPEN);
		LOGGER.debug("getCatalogServiceReqContract.Related Service request number --->"+((CatalogDetailPageForm)formData1).getRelatedServiceRequestedNumber());
		contract.setRelatedServiceRequestedNumber(((CatalogDetailPageForm)formData1).getRelatedServiceRequestedNumber());
		contract.setRelatedServiceRequestedRowId(((CatalogDetailPageForm)formData1).getRelatedServiceRequestedRowId());
		if(((CatalogDetailPageForm)formData1).getRelatedServiceRequestedRowId() != null){
			contract.getServiceRequest().setId(((CatalogDetailPageForm)formData1).getRelatedServiceRequestedRowId());	
		}else{
			contract.getServiceRequest().setId("");
		}
		
		///add bill To address
		if(((CatalogDetailPageForm)formData1).getCreditFlag()!=null && ((CatalogDetailPageForm)formData1).getCreditFlag().equalsIgnoreCase("true")){
			if(((CatalogDetailPageForm)formData1).getBillToAddress()!=null){
				contract.setBillToAddress(((CatalogDetailPageForm)formData1).getBillToAddress());
			}
		}
		contract.setBillingModel(((CatalogDetailPageForm)formData1).getSelectedPaymentType());
		
		return contract;
	}	
	
	
	/**
	 * @param formData1 
	 * @param formData2 
	 * @param request 
	 * @return CreateConsumableServiceRequestContract 
	 */
	public static CreateConsumableServiceRequestContract getConsumableDraftReqContract(Object formData1, Object formData2, PortletRequest request) {
		CreateConsumableServiceRequestContract contract = new CreateConsumableServiceRequestContract();
		//contract.setAssetPartList(((AssetDetailPageForm)formData2).getAssetPartList());
		String area = null;
		/*Changes for JAN release Parts to be
		 * installed flag
		 * */
		List<Part> assetPartList= ((AssetDetailPageForm)formData2).getAssetPartList();
		List<Part> modifiedAssetPartList= new ArrayList<Part>();
		 for (Part part : assetPartList) {
			if(!"".equalsIgnoreCase(part.getOrderQuantity().trim()) && !"0".equalsIgnoreCase(part.getOrderQuantity())  && part.getOrderQuantity() != null){
				modifiedAssetPartList.add(part);
			}
		}
		 contract.setAssetPartList(modifiedAssetPartList);
		if(!((AssetDetailPageForm)formData1).getAsset().getInstallationOnlyFlag()){
		
			if(!contract.getAssetPartList().isEmpty()){
				area = contract.getAssetPartList().get(0).getSupplyType();
				if(area == null){
					area = "";
				}
				LOGGER.debug("Area value:::"+ area);
			}
			
			for(Part part : contract.getAssetPartList()){
				
				String area_temp = part.getSupplyType();
				
				if(area_temp!=null && !"".equals(area_temp)){
					LOGGER.debug("area_temp value:::"+ area_temp);
					if(!area_temp.equalsIgnoreCase(area)){//that means area should be sent as Consumable Mix Parts Request break the loop
						area = ChangeMgmtConstant.OM_AREA_MIX_PARTS;
						LOGGER.debug("do not need any more iteration breaking the loop");
						break;
					}else{
						//area value should remain same so do nothing
					}
				}
				LOGGER.debug("Area value:::"+ area);
			}
		}else{
			area=ChangeMgmtConstant.OM_SR_TYPE_PARTS_INSTALLED;
		}
		//Ends JAN release
		
		
		LOGGER.debug("Final Area value:::"+ area);
		
		List<String> userRoleList = PortalSessionUtil.getUserRoles(request.getPortletSession());		
		
		if(!userRoleList.isEmpty() && ((userRoleList.contains(LexmarkConstants.ROLE_SERVICE_MANAGER)) || (userRoleList.contains(LexmarkConstants.ROLE_SERVICE_TECHNICIAN))))
		{
			contract.setVendorName(PortalSessionUtil.getVendorAccountID(request.getPortletSession()));//Added By MPS Offshore team for adding Vendor ID
		}else {
			contract.setVendorName("");//Added By MPS Offshore team for adding Vendor ID
		}
		contract.setSrArea(area);
		contract.setConsumableOrderType("assetbased");//This is used only to differentiate between asset and catalog based order
		contract.setContactId(PortalSessionUtil.getContactId(request.getPortletSession()));
		contract.setMdmId(PortalSessionUtil.getMdmId(request.getPortletSession()));
		contract.setMdmLevel(PortalSessionUtil.getMdmLevel(request.getPortletSession()));
		
		contract.setVendorName(PortalSessionUtil.getVendorAccountID(request.getPortletSession()));//Added By MPS Offshore team for adding Vendor ID
		
		contract.setSrType(ChangeMgmtConstant.OM_SR_TYPE);//consumable
		contract.setSrSubArea(ChangeMgmtConstant.OM_SUB_AREA_ASSET);//
		contract.setServiceRequest(((AssetDetailPageForm)formData1).getServiceRequest());
		contract.setAssetSerialNumber(((AssetDetailPageForm)formData1).getAsset().getSerialNumber());
		contract.setPrinterProductNumber(((AssetDetailPageForm)formData1).getAsset().getProductTLI());
		contract.setPageCountList(sendAssetPageCount(((AssetDetailPageForm)formData1).getPageCountsList()));
		contract.setShipToAddress(((AssetDetailPageForm)formData1).getShipToAddress());
		contract.setSpecialInstruction(((AssetDetailPageForm)formData1).getSpecialInstruction());
		contract.setExpediteOrder((((AssetDetailPageForm)formData1).isRequestExpediteOrder())?"Y":"N");
		contract.setRequestedDeliveryDate(((AssetDetailPageForm)formData1).getRequestedDeliveryDate());
		if(request.getLocale()!=null){
			contract.setRequestedDeliveryDate(DateUtil.convertDateStringToGMTDateString(contract.getRequestedDeliveryDate(), request.getLocale().getLanguage()));
		}
		
		
		contract.setPoNumber(((AssetDetailPageForm)formData1).getAsset().getPoNumber());
		contract.setAttachmentNotes(((AssetDetailPageForm)formData1).getAttachmentDescription());
		//Added for JAN release MPS 
		//Y means checkbox selected N means checkbox not selected
		contract.setInstallationOnlyFlag((((AssetDetailPageForm)formData1).getAsset().getInstallationOnlyFlag())?"Y":"N");
		contract.setPartsToBeInstalledDescription(((AssetDetailPageForm)formData1).getAsset().getPartsToBeInstalled());
		//Jan release ends
		
		//Updated By MPS Offshore Team for creating SR on behalf of Customer
		if(PortalSessionUtil.getUserType(request.getPortletSession()).equalsIgnoreCase("Vendor")){
			contract.setUserType(ChangeMgmtConstant.VENDORREQTYPE);
		}else if(PortalSessionUtil.getUserType(request.getPortletSession()).equalsIgnoreCase("Employee")){
			contract.setUserType(ChangeMgmtConstant.LEXMARKREQTYPE);
		}else{
			contract.setUserType(PortalSessionUtil.getUserType(request.getPortletSession()));
		}
		
		LOGGER.debug("[getConsumableDraftReqContract]User Type ----->"+contract.getUserType());
		
		//Update Done
		
		contract.setSRStatus(ChangeMgmtConstant.OM_SR_STATUS_DRAFT);
		
		LOGGER.debug("getConsumableDraftReqContract Service request number --->"+((AssetDetailPageForm)formData1).getRelatedServiceRequestedNumber());
		contract.setRelatedServiceRequestedNumber(((AssetDetailPageForm)formData1).getRelatedServiceRequestedNumber());
		contract.setRelatedServiceRequestedRowId(((AssetDetailPageForm)formData1).getRelatedServiceRequestedRowId());
		
		contract.setBillToAddress(((AssetDetailPageForm)formData1).getBillToAddress());
		contract.setBillingModel(((AssetDetailPageForm)formData1).getSelectedPaymentTypeId());
		contract.setContractNumber(((AssetDetailPageForm)formData1).getContractNumber());
		contract.setAssetId(((AssetDetailPageForm)formData1).getAsset().getAssetId());
		return contract;
	}
	
	/**
	 * @param formData1 
	 * @param request 
	 * @return CreateConsumableServiceRequestContract 
	 */
	public static CreateConsumableServiceRequestContract getCatalogDraftReqContract(Object formData1, PortletRequest request) {
		CreateConsumableServiceRequestContract contract = new CreateConsumableServiceRequestContract();
		//Added for source by MPS offshore
		List<String> userRoleList = PortalSessionUtil.getUserRoles(request.getPortletSession());		
		
		if(!userRoleList.isEmpty() && ((userRoleList.contains(LexmarkConstants.ROLE_SERVICE_MANAGER)) || (userRoleList.contains(LexmarkConstants.ROLE_SERVICE_TECHNICIAN))))
		{
			contract.setVendorName(PortalSessionUtil.getVendorAccountID(request.getPortletSession()));//Added By MPS Offshore team for adding Vendor ID			
		}else {
			contract.setVendorName("");//Added By MPS Offshore team for adding Vendor ID
		}
		//End
		contract.setCatalogPartList(((CatalogDetailPageForm)formData1).getCatalogPartList());
		String area = null;
		if(!contract.getCatalogPartList().isEmpty()){
			area = contract.getCatalogPartList().get(0).getConsumableType();
			if(area == null){
				area = "";
			}
			LOGGER.debug("Area value:::"+ area);
		}
		
		for(OrderPart orderPart : contract.getCatalogPartList()){
			
			String area_temp = orderPart.getConsumableType();
			
			if(area_temp!=null && !"".equals(area_temp)){
				LOGGER.debug("area_temp value:::"+ area_temp);
				if(!area_temp.equalsIgnoreCase(area)){//that means area should be sent as Consumable Mix Parts Request break the loop
					area = ChangeMgmtConstant.OM_AREA_MIX_PARTS;
					LOGGER.debug("do not need any more iteration breaking the loop");
					break;
				}else{
					//area value should remain same so do nothing
				}
			}
			LOGGER.debug("Area value:::"+ area);
		}
		LOGGER.debug("Final Area value:::"+ area);
		contract.setSrArea(area);
		contract.setConsumableOrderType("catalogbased");//This is used only to differentiate between asset and catalog based order
		contract.setContactId(PortalSessionUtil.getContactId(request.getPortletSession()));
		contract.setMdmId(PortalSessionUtil.getMdmId(request.getPortletSession()));
		contract.setMdmLevel(PortalSessionUtil.getMdmLevel(request.getPortletSession()));
		contract.setSrType(ChangeMgmtConstant.OM_SR_TYPE);//consumable
		contract.setSrSubArea(ChangeMgmtConstant.OM_SUB_AREA_CATALOG);//
		contract.setServiceRequest(((CatalogDetailPageForm)formData1).getServiceRequest());
		//contract.setAssetSerialNumber(((CatalogDetailPageForm)formData1).getAsset().getSerialNumber());
		//contract.setPrinterProductNumber(((CatalogDetailPageForm)formData1).getAsset().getProductTLI());
		//contract.setPageCountList(((CatalogDetailPageForm)formData1).getPageCountsList());
		contract.setShipToAddress(((CatalogDetailPageForm)formData1).getShipToAddress());
		contract.setSpecialInstruction(((CatalogDetailPageForm)formData1).getSpecialInstruction());
		contract.setExpediteOrder((((CatalogDetailPageForm)formData1).isRequestExpediteOrder())?"Y":"N");
		contract.setRequestedDeliveryDate(((CatalogDetailPageForm)formData1).getRequestedDeliveryDate());
		if(request.getLocale()!=null){
			contract.setRequestedDeliveryDate(DateUtil.convertDateStringToGMTDateString(contract.getRequestedDeliveryDate(), request.getLocale().getLanguage()));
		}
		
		contract.setPoNumber(((CatalogDetailPageForm)formData1).getPoNumber());
		contract.setAttachmentNotes(((CatalogDetailPageForm)formData1).getAttachmentDescription());
		
		//Updated By MPS Offshore Team for creating SR on behalf of Customer
		if(PortalSessionUtil.getUserType(request.getPortletSession()).equalsIgnoreCase("Vendor")){
			contract.setUserType(ChangeMgmtConstant.VENDORREQTYPE);
		}else if(PortalSessionUtil.getUserType(request.getPortletSession()).equalsIgnoreCase("Employee")){
			contract.setUserType(ChangeMgmtConstant.LEXMARKREQTYPE);
		}else{
			contract.setUserType(PortalSessionUtil.getUserType(request.getPortletSession()));
		}
		
		LOGGER.debug("[getCatalogDraftReqContract]User Type ----->"+contract.getUserType());
		
		//Update Done
		contract.setSRStatus(ChangeMgmtConstant.OM_SR_STATUS_DRAFT);
		
		LOGGER.debug("getConsumableDraftReqContract Service request number --->"+((CatalogDetailPageForm)formData1).getRelatedServiceRequestedNumber());
		contract.setRelatedServiceRequestedNumber(((CatalogDetailPageForm)formData1).getRelatedServiceRequestedNumber());
		contract.setRelatedServiceRequestedRowId(((CatalogDetailPageForm)formData1).getRelatedServiceRequestedRowId());
		if(((CatalogDetailPageForm)formData1).getRelatedServiceRequestedRowId() != null){
			contract.getServiceRequest().setId(((CatalogDetailPageForm)formData1).getRelatedServiceRequestedRowId());	
		}else{
			contract.getServiceRequest().setId("");
		}
		///add bill To address
		contract.setBillToAddress(((CatalogDetailPageForm)formData1).getBillToAddress());
		//Set billing Model
		contract.setBillingModel(((CatalogDetailPageForm)formData1).getSelectedPaymentType());
		PortletSession session = request.getPortletSession();
		Map<String,String> catalogDetails=(Map<String,String>)session.getAttribute("catalogCurrentDetails",PortletSession.APPLICATION_SCOPE);
		String soldToNumber = "";
		String  billToNumber = "";
		if(catalogDetails != null){
			soldToNumber = catalogDetails.get("soldToNumber");
			billToNumber = catalogDetails.get("billToNumber");			
			contract.setSoldToNumber(soldToNumber);
			contract.setBillToNumber(billToNumber);
		}
		
		
		return contract;
	}
	/**
	 * @param assetPageCountList 
	 * @return sendAssetPageCount 
	 */
	private static List<PageCounts> sendAssetPageCount(List<PageCounts> assetPageCountList){
		Date dateNow = new Date ();
		SimpleDateFormat dateformatMMDDYYYY = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        StringBuilder nowMMDDYYYY = new StringBuilder( dateformatMMDDYYYY.format( dateNow ) );
        LOGGER.info( "DEBUG: Today in MM/dd/yyyy HH:mm:ss: " + nowMMDDYYYY.toString() + "'");
		List<PageCounts> newAssetPageCountList = new ArrayList<PageCounts>();
		if (assetPageCountList!=null){
			for (int i=0;i<assetPageCountList.size();i++){
				LOGGER.debug("Page counts for "+i+"  is "+assetPageCountList.get(i).getName());
				String countName = assetPageCountList.get(i).getName();
				String countValue = assetPageCountList.get(i).getCount();
				String countDate = assetPageCountList.get(i).getDate();
				PageCounts pageCounts = new PageCounts();
				if(assetPageCountList.get(i).isBothValueBlank()){
					if(("".equals(countValue)||countValue==null)&&("".equals(countValue)||countDate==null)){
						LOGGER.debug(" Breaking the iteration We are not adding the value to the list");
						continue;
					}
				}
				
				pageCounts.setSiebelName(assetPageCountList.get(i).getSiebelName());
				pageCounts.setName(assetPageCountList.get(i).getName());
				pageCounts.setCount(countValue);
				pageCounts.setDate(countDate);
				newAssetPageCountList.add(pageCounts);
				LOGGER.debug("Inside the for loop countName is "+countName+" countValue is "+assetPageCountList.get(i).getCount()+
						" countDate is "+assetPageCountList.get(i).getDate()+
						" is both  value blank "+assetPageCountList.get(i).isBothValueBlank());
				LOGGER.debug("count value we are sending "+pageCounts.getCount());
			}
		}
		return newAssetPageCountList;
	}
	
	/*Added for MPS 2.1 Payment Type Contract*/
	/**
	 * @param request 
	 * @param isHardwareFlag 
	 * @return PaymentListContract 
	 */
	public static PaymentListContract getPaymentListContract(PortletRequest request, boolean isHardwareFlag){
		PaymentListContract contract = new PaymentListContract();
		PortletSession session = request.getPortletSession();
		String soldTo = request.getParameter("soldTo");
		String agreementId = (String) session.getAttribute("agreementId");
		contract.setMdmId(PortalSessionUtil.getMdmId(session));
		contract.setMdmLevel(PortalSessionUtil.getMdmLevel(session));
		contract.setSoldToNumber(soldTo);
		contract.setAgreementId(agreementId);
		contract.setHardwareFlag(isHardwareFlag);
		return contract;
	}
	
	/**
	 * @param catalogPartList 
	 * @param session 
	 * @param contractNo 
	 * @return PriceContract 
	 */
	public static PriceContract getCatalogPriceContract(List<OrderPart> catalogPartList,PortletSession session,String contractNo){
		PriceContract priceContract = new PriceContract();
		List<Price> priceInputList = new ArrayList<Price>();	
		for(OrderPart part:catalogPartList){
			Price price = new Price();
			String contractLineItemId = part.getContractLineItemId();
			price.setContractLineItemId(contractLineItemId);
			priceInputList.add(price);
		}
		priceContract.setContractNumber(contractNo);
		priceContract.setPriceList(priceInputList);
		return priceContract;	
	}
	
	/**
	 * @param catalogPartList 
	 * @param session 
	 * @param contractNo 
	 * @return PriceContract 
	 */
	public static PriceContract getTonerPriceContract(List<OrderPart> catalogPartList,PortletSession session){
		PriceContract priceContract = new PriceContract();
		List<Price> priceInputList = new ArrayList<Price>();	
		String poNumber = "";
		for(OrderPart part:catalogPartList){
			Price price = new Price();
			price.setPartNumber(part.getPartNumber());
			priceInputList.add(price);
			if(part.getProviderContractNo()!=null && !part.getProviderContractNo().equals("")){
				poNumber = part.getProviderContractNo();
				LOGGER.debug("PO Number inside loop is "+poNumber);
			}else{
				LOGGER.debug("PO Number inside loop is blank for Part Number "+part.getPartNumber());
			}
		}
		priceContract.setPoNumber(poNumber);
		LOGGER.debug("PO Number is "+priceContract.getPoNumber());
		priceContract.setPriceList(priceInputList);
		return priceContract;	
	}
	
	/**
	 * @param hardwarePartList 
	 * @param session 
	 * @param contractNo 
	 * @return PriceContract 
	 */
	public static PriceContract getHardwarePriceContract(List<Part> hardwarePartList,PortletSession session,String contractNo){
		PriceContract priceContract = new PriceContract();
		List<Price> priceInputList = new ArrayList<Price>();	
		for(Part part:hardwarePartList){
			Price price = new Price();
			String contractLineItemId = part.getContractLineItemId();
			price.setContractLineItemId(contractLineItemId);
			priceInputList.add(price);
		}
		priceContract.setContractNumber(contractNo);
		priceContract.setPriceList(priceInputList);
		LOGGER.debug("End of Price Contract");
		return priceContract;	
	}
	
	/**
	 * @param hardwarePartList 
	 * @param session 
	 * @param contractNo 
	 * @return PriceContract 
	 */
	public static PriceContract getHardwareBundlePriceContract(List<Bundle> hardwarePartList,PortletSession session,String contractNo){
		PriceContract priceContract = new PriceContract();
		List<Price> priceInputList = new ArrayList<Price>();	
		for(Bundle part:hardwarePartList){
			Price price = new Price();
			price.setContractLineItemId(part.getContractLineItemId());
			priceInputList.add(price);
		}
		priceContract.setContractNumber(contractNo);
		priceContract.setPriceList(priceInputList);
		LOGGER.debug("End of Price Contract");
		return priceContract;	
	}
	
	/**
	 * @param orderPartList 
	 * @param shipToAddress 
	 * @param session 
	 * @param isHardwareFlag 
	 * @return TaxContract 
	 */
	public static TaxContract getOrderTaxContract(List<OrderPart> orderPartList,GenericAddress shipToAddress,PortletSession session, boolean isHardwareFlag){
		TaxContract taxContract = new TaxContract();
		List<Price> priceInputList = new ArrayList<Price>();
		String soldToNumber = "";
		String salesOrg = "";
		if(isHardwareFlag){
			Map<String,String> hardwareDetails=(Map<String,String>)session.getAttribute("hardwareCurrentDetails",PortletSession.APPLICATION_SCOPE);
			if(hardwareDetails != null){
				soldToNumber = hardwareDetails.get("soldToNumber");
			}
		}else{
			Map<String,String> catalogDetails=(Map<String,String>)session.getAttribute("catalogCurrentDetails",PortletSession.APPLICATION_SCOPE);			
			if(catalogDetails != null){
				soldToNumber = catalogDetails.get("soldToNumber");
				LOGGER.debug("Sold To ::::   "+catalogDetails.get("soldToNumber"));
				
			}
		}
		
		Map<String,String> accDetails=(Map<String,String>)session.getAttribute("accountCurrentDetails",PortletSession.APPLICATION_SCOPE);			
		salesOrg = accDetails.get("salesOrg");
		
		for(OrderPart part:orderPartList){
			if(part.getUnitPrice() != null){
				if(part.getUnitPrice() != "0" || part.getUnitPrice() != ""){
					Price price = new Price();
					LOGGER.debug("Unit Price "+part.getUnitPrice());
					price.setPrice(part.getTotal());
					price.setPartNumber(part.getPartNumber());
					price.setSourceReferenceLineId(part.getCatalogId());
					priceInputList.add(price);
				}
			}
			
		}
		
		LOGGER.debug("Country "+shipToAddress.getCountryISOCode());
		LOGGER.debug("City "+shipToAddress.getCity());
		LOGGER.debug("Region "+shipToAddress.getRegion());
		LOGGER.debug("PostalCode "+shipToAddress.getPostalCode());
		
		taxContract.setSalesOrganization(salesOrg);
		taxContract.setSoldToNumber(soldToNumber);
		taxContract.setCountry(shipToAddress.getCountryISOCode());
		taxContract.setCity(shipToAddress.getCity());
		taxContract.setRegion(shipToAddress.getRegion());
		taxContract.setPostalCode(shipToAddress.getPostalCode());
		taxContract.setLineInformationList(priceInputList);
		LOGGER.debug("End of Tax Contract");
		return taxContract;	
	}
	
	
	/**
	 * @param partList 
	 * @param assetDetailPageForm 
	 * @return TaxContract 
	 */
	public static TaxContract getAssetOrderTaxContract(List<Part> partList,AssetDetailPageForm assetDetailPageForm){
		TaxContract taxContract = new TaxContract();
		List<Price> priceInputList = new ArrayList<Price>();
		String soldToNumber = "";
		//String salesOrg = "";
		
		if(partList.get(0).getSoldToNumbers() != null){
			int count = 0;
			for(String soldTo: partList.get(0).getSoldToNumbers()){
				if(count==0){
					soldToNumber = soldTo;
					LOGGER.debug("Sold To Number:: " + soldToNumber);
				}else{
					break;
				}
				count ++;
			}
		}
		
		for(Part part:partList){
			if(part.getUnitPrice()!= null){
				if( (!part.getUnitPrice().equals("0")) && (!part.getUnitPrice().equals(""))){
					Price price = new Price();
					LOGGER.debug("Unit Price "+part.getUnitPrice());
					price.setPrice(part.getTotalPrice());
					price.setPartNumber(part.getPartNumber());
					price.setSourceReferenceLineId(part.getCatalogId());
					LOGGER.debug("Part num:: " + part.getPartNumber());
					LOGGER.debug("Line Id:: " + soldToNumber);
					//salesOrg = part.getSalesOrg();
					//LOGGER.debug("Sales Org:: " + salesOrg);
					priceInputList.add(price);
					
				}
			}
			
			
		}
		
		taxContract.setSalesOrganization(assetDetailPageForm.getSalesOrganisation());
		LOGGER.debug("Sales Org:: " + assetDetailPageForm.getSalesOrganisation());
		taxContract.setSoldToNumber(soldToNumber);
		taxContract.setCountry(assetDetailPageForm.getShipToAddress().getCountryISOCode());
		LOGGER.debug("Country:: " + assetDetailPageForm.getShipToAddress().getCountryISOCode());
		taxContract.setCity(assetDetailPageForm.getShipToAddress().getCity());
		LOGGER.debug("Country:: " + assetDetailPageForm.getShipToAddress().getCity());
		taxContract.setRegion(assetDetailPageForm.getShipToAddress().getRegion());
		LOGGER.debug("Region:: " + assetDetailPageForm.getShipToAddress().getRegion());
		taxContract.setPostalCode(assetDetailPageForm.getShipToAddress().getPostalCode());
		LOGGER.debug("Postal Code:: " + assetDetailPageForm.getShipToAddress().getPostalCode());
		taxContract.setLineInformationList(priceInputList);
		LOGGER.debug("End of Tax Contract");
		return taxContract;	
	}
	
	/**
	 * Method to set HardwareRequestContract object with Hardware Request Data from the form bean for create Hardware Orders through WM
	 * @param formData as hardwareDetailPageForm
	 * @param request
	 * @return
	 */
	/**
	 * @param hardwareDetailPageForm 
	 * @param request 
	 * @return CreateHardwareRequestContract 
	 */
	public static CreateHardwareRequestContract getHardwareContract(HardwareDetailPageForm hardwareDetailPageForm, RenderRequest request) {
		CreateHardwareRequestContract contract = new CreateHardwareRequestContract();
		PortletSession session = request.getPortletSession();
		Map<String,String> hardwareDetails=(Map<String,String>)session.getAttribute("hardwareCurrentDetails",PortletSession.APPLICATION_SCOPE);
		String soldToNumber = "";
		String paymentType = "";
		String  billToNumber = "";
		if(hardwareDetails != null){
			soldToNumber = hardwareDetails.get("soldToNumber");
			billToNumber = hardwareDetails.get("billToNumber");
			paymentType = hardwareDetails.get("paymentType");
		}
		contract.setSoldToNumber(soldToNumber);
		if(paymentType!=null && !paymentType.equals("") && paymentType.equalsIgnoreCase(ChangeMgmtConstant.PAYMENT_TYPE_PAY_NOW)){
			contract.setBillToNumber(billToNumber);
		}
		contract.setPaymentType(paymentType);
		
		
		contract.setContactId(PortalSessionUtil.getContactId(request.getPortletSession()));
		contract.setMdmId(PortalSessionUtil.getMdmId(request.getPortletSession()));
		contract.setMdmLevel(PortalSessionUtil.getMdmLevel(request.getPortletSession()));
		LOGGER.debug(" hardwareDetailPageForm.isInstallationOnlyFlag() "+ hardwareDetailPageForm.isInstallationOnlyFlag());
		contract.setSrArea(hardwareDetailPageForm.isInstallationOnlyFlag()?ChangeMgmtConstant.INSTALL_HW_AREA:ChangeMgmtConstant.HW_AREA);
		contract.setSrType(ChangeMgmtConstant.HW_SR_TYPE);
		contract.setSrSubArea(ChangeMgmtConstant.HW_SUB_AREA);
		contract.setServiceRequest(hardwareDetailPageForm.getServiceRequest());
		contract.setShipToAddress(hardwareDetailPageForm.getShipToAddress());
		contract.setSpecialInstruction(hardwareDetailPageForm.getSpecialInstruction());
		contract.setInstallationOnlyFlag(hardwareDetailPageForm.isInstallationOnlyFlag()?"Y":"N");
		contract.setBundleList(hardwareDetailPageForm.getBundleList());		
		contract.setAccessoriesList(hardwareDetailPageForm.getAccessoriesList());
		
		if(hardwareDetailPageForm.getPoNumber()!=null && hardwareDetailPageForm.getPoNumber()!="" && hardwareDetailPageForm.getPoNumber()!=","){
			contract.setPoNumber(hardwareDetailPageForm.getPoNumber());
		}
		if(hardwareDetailPageForm.getCreditCardToken()!=null && hardwareDetailPageForm.getCreditCardToken()!="" && hardwareDetailPageForm.getCreditCardToken()!=","){
			contract.setCreditCardToken(hardwareDetailPageForm.getCreditCardToken());
			contract.setCreditCardEncryptedNo(hardwareDetailPageForm.getCreditCardEncryptedNo());
			contract.setCreditCardExpirationDate(hardwareDetailPageForm.getCreditCardExpirationDate());
			contract.setCreditCardType(hardwareDetailPageForm.getCreditCardType());
			contract.setCardHolderName(hardwareDetailPageForm.getCardHoldername());
			contract.setTransactionId(hardwareDetailPageForm.getTransactionId());
		}
		if(hardwareDetailPageForm.getPaymentMethod()!=null && hardwareDetailPageForm.getPaymentMethod()!=""){
			contract.setPaymentMethod(hardwareDetailPageForm.getPaymentMethod());
		}
		contract.setAttachmentNotes(hardwareDetailPageForm.getAttachmentDescription());
		if(PortalSessionUtil.getUserType(request.getPortletSession()).equalsIgnoreCase("Employee")){
			contract.setUserType(ChangeMgmtConstant.LEXMARKREQTYPE);
		}else{
			contract.setUserType(PortalSessionUtil.getUserType(request.getPortletSession()));
		}
		
		contract.setSRStatus(ChangeMgmtConstant.OM_SR_STATUS_OPEN);
		contract.setRelatedServiceRequestedNumber(hardwareDetailPageForm.getRelatedServiceRequestedNumber());
		contract.setRelatedServiceRequestedRowId(hardwareDetailPageForm.getRelatedServiceRequestedRowId());
		if(hardwareDetailPageForm.getRelatedServiceRequestedRowId() != null){
			contract.getServiceRequest().setId(hardwareDetailPageForm.getRelatedServiceRequestedRowId());
		}else{
			contract.getServiceRequest().setId("");
		}
		
		if(hardwareDetailPageForm.getCreditFlag()!=null && (hardwareDetailPageForm.getCreditFlag().equalsIgnoreCase("true"))){
			if(hardwareDetailPageForm.getBillToAddress()!=null){
				contract.setBillToAddress(hardwareDetailPageForm.getBillToAddress());
			}
		}
		
contract.setPlacementId(hardwareDetailPageForm.getPlacementId());
		return contract;
	}	
	
	/**
	 * @param hardwareDetailPageForm 
	 * @param request 
	 * @return CreateHardwareRequestContract 
	 */
	public static CreateHardwareRequestContract getHardwareDraftReqContract(HardwareDetailPageForm hardwareDetailPageForm, PortletRequest request) {
		CreateHardwareRequestContract contract = new CreateHardwareRequestContract();	
		PortletSession session = request.getPortletSession();
		Map<String,String> hardwareDetails=(Map<String,String>)session.getAttribute("hardwareCurrentDetails",PortletSession.APPLICATION_SCOPE);
		String soldToNumber = "";
		String paymentType = "";
		String  billToNumber = "";
		if(hardwareDetails != null){
			soldToNumber = hardwareDetails.get("soldToNumber");
			paymentType = hardwareDetails.get("paymentType");
			billToNumber = hardwareDetails.get("billToNumber");
		}
		contract.setSoldToNumber(soldToNumber);
		contract.setPaymentType(paymentType);
		contract.setBillToNumber(billToNumber);
		
		contract.setContactId(PortalSessionUtil.getContactId(request.getPortletSession()));
		contract.setMdmId(PortalSessionUtil.getMdmId(request.getPortletSession()));
		contract.setMdmLevel(PortalSessionUtil.getMdmLevel(request.getPortletSession()));
		contract.setSrArea(hardwareDetailPageForm.isInstallationOnlyFlag()?ChangeMgmtConstant.INSTALL_HW_AREA:ChangeMgmtConstant.HW_AREA);
		contract.setSrType(ChangeMgmtConstant.HW_SR_TYPE);		
		contract.setSrSubArea(ChangeMgmtConstant.HW_SUB_AREA);
		contract.setServiceRequest(hardwareDetailPageForm.getServiceRequest());
		contract.setShipToAddress(hardwareDetailPageForm.getShipToAddress());
		contract.setSpecialInstruction(hardwareDetailPageForm.getSpecialInstruction());
		contract.setInstallationOnlyFlag(hardwareDetailPageForm.isInstallationOnlyFlag()?"Y":"N");
		contract.setBundleList(hardwareDetailPageForm.getBundleList());		
		contract.setAccessoriesList(hardwareDetailPageForm.getAccessoriesList());
		
		if(hardwareDetailPageForm.getPoNumber()!=null && hardwareDetailPageForm.getPoNumber()!="" && hardwareDetailPageForm.getPoNumber()!=","){
			contract.setPoNumber(hardwareDetailPageForm.getPoNumber());
		}
		if(hardwareDetailPageForm.getCreditCardToken()!=null && hardwareDetailPageForm.getCreditCardToken()!="" && hardwareDetailPageForm.getCreditCardToken()!=","){
			contract.setCreditCardToken(hardwareDetailPageForm.getCreditCardToken());
		}
		if(hardwareDetailPageForm.getBillToAddress()!=null){
			contract.setBillToAddress(hardwareDetailPageForm.getBillToAddress());
		}
		contract.setAttachmentNotes(hardwareDetailPageForm.getAttachmentDescription());
		if(PortalSessionUtil.getUserType(request.getPortletSession()).equalsIgnoreCase("Employee")){
			contract.setUserType(ChangeMgmtConstant.LEXMARKREQTYPE);
		}else{
			contract.setUserType(PortalSessionUtil.getUserType(request.getPortletSession()));
		}		
		contract.setSRStatus(ChangeMgmtConstant.OM_SR_STATUS_DRAFT);
		contract.setRelatedServiceRequestedNumber(hardwareDetailPageForm.getRelatedServiceRequestedNumber());
		contract.setRelatedServiceRequestedRowId(hardwareDetailPageForm.getRelatedServiceRequestedRowId());		
		if(hardwareDetailPageForm.getRelatedServiceRequestedRowId() != null){
			contract.getServiceRequest().setId(hardwareDetailPageForm.getRelatedServiceRequestedRowId());
		}else{
			contract.getServiceRequest().setId("");
		}
		contract.setPlacementId(hardwareDetailPageForm.getPlacementId());
		return contract;
	}
	
	/*
	 * Added for MPS 2.1
	 * */
	/**
	 * @param request 
	 * @return ProcessCustomerContactContract 
	 */
	public static ProcessCustomerContactContract getProcessCustomerContactContract(PortletRequest request){
		
		ProcessCustomerContactContract contract=new ProcessCustomerContactContract();
		PortletSession session=request.getPortletSession();
		Map<?, ?> accDetails = (Map<?, ?>) session.getAttribute(ChangeMgmtConstant.ACNTCURRDETAILS,
				PortletSession.APPLICATION_SCOPE);
		
		contract.setMdmId(String.valueOf(accDetails.get("accountId")));
		contract.setMdmLevel("Siebel");
		contract.setUserContactId(PortalSessionUtil.getContactId(session));
		//contract.setMdmId("DUN1-L4BTZC");
		//contract.setUserContactId("1-1DTJMA");
		
		return contract;
	}
	//added for customerARInvoice-Parth

	/**
	 * @param request 
	 * @return InvoiceListContract 
	 * @throws ParseException 
	 */
	public static InvoiceListContract createInvoiceListContractforAR(
			PortletRequest request,String[]filterColumns) throws ParseException {
		
		
		InvoiceListContract contract = new InvoiceListContract();

		final PortletSession session = request.getPortletSession();
		Map<String,String> accRecivablDetails = (Map<String, String>) session
				.getAttribute("accRecivablDetails",
						PortletSession.APPLICATION_SCOPE);
		Date fromDate, endDate;
		
		
		
		
		
		if(StringUtils.isNotBlank(request.getParameter("fromDate"))&& StringUtils.isNotBlank(request.getParameter("endDate"))){
			
			fromDate = DateUtil.getStringToLocalizedDate(request.getParameter("fromDate"), false, request.getLocale());//this is the locale specific date
			endDate =DateUtil.getStringToLocalizedDate(request.getParameter("endDate"), false, request.getLocale());//this is the locale specific date
			
			
				
		}else{
			Calendar currentDate = Calendar.getInstance();
			endDate = currentDate.getTime();
			currentDate.add(Calendar.MONTH, -6);
			fromDate = currentDate.getTime();
		}
		
		LOGGER.info("from date finally " + fromDate + " end date finally " + endDate);
		
		contract.setCompanyCode((String)accRecivablDetails.get("customerNumber"));//CustomerNumber
		contract.setFromDate(fromDate);
		contract.setToDate(endDate);
		contract.setStatus(StringUtils.isNotBlank(request.getParameter("status"))==true?request.getParameter("status"):"OPEN");
		contract.setSoldToNumber((String)accRecivablDetails.get("companycode"));//Sold To number
		
		if(StringUtils.isNotBlank((String)accRecivablDetails.get("invoiceNumber"))){
			contract.setInvoiceNum((String)accRecivablDetails.get("invoiceNumber"));//Invoice Number
			accRecivablDetails.put("invoiceNumber", null);
			session.setAttribute("accRecivablDetails",accRecivablDetails,
				PortletSession.APPLICATION_SCOPE);
			LOGGER.debug("after removing "+accRecivablDetails);
		}
		
		
		if(StringUtils.isNotBlank(request.getParameter("filterCriterias"))){
			LOGGER.debug("request.getParameter filterCriterias"+request.getParameter("filterCriterias"));
			String[] splitFilter=request.getParameter("filterCriterias").split(",");
			
			int i=0;
			for(String filterVal:splitFilter){
				LOGGER.debug("column "+filterColumns[i]+ "val="+splitFilter[i]);
				
				contract.getFilterCriteria().put(filterColumns[i], filterVal);
				LOGGER.debug("after contrat get filter criteria");
				i++;
			}
			
			
		}
		
		return contract;
	}
			/*End*/
	//added for customerARInvoice-Parth
	/**
	 * @param request 
	 * @return AccountReceivableListContract 
	 */
	public static AccountReceivableListContract getAccountRecievableListContract(PortletRequest request){
		AccountReceivableListContract accountRecievableListContract = new AccountReceivableListContract();
		PortletSession session = request.getPortletSession();
		accountRecievableListContract.setMdmId(PortalSessionUtil.getMdmId(session));
		accountRecievableListContract.setMdmLevel(PortalSessionUtil.getMdmLevel(session));
		String accountColumns[]={"accountName","billTo","soldTo"};
		Pagination pagination = PaginationUtil.getPainationFromRequest((ResourceRequest)request, accountColumns, "accountListColumns");
		SearchContractUtil.copyPaginationToContract(pagination, accountRecievableListContract);
		
		return accountRecievableListContract;
	}
	
	/**
	 * @param request 
	 * @return PageCountsContract 
	 */
	public static PageCountsContract getPageCountsContract(ResourceRequest request) {
		PageCountsContract contract =  new PageCountsContract();
		contract.setAssetId(request.getParameter("assetRowId"));
		return contract;
	}
	
	/**
	 * @param request
	 * @return
	 */
	public static AddressListContract getLBSAddressContract(PortletRequest request){
		AddressListContract contract=new AddressListContract();
		contract.setLbsFlag(true);
		contract.setMdmId(PortalSessionUtil.getMdmId(request.getPortletSession()));
		contract.setMdmLevel(PortalSessionUtil.getMdmLevel(request.getPortletSession()));
		contract.setNewQueryIndicator(true);
		contract.setLbsFlag(true);
		contract.setIncrement(40);
		if(StringUtils.isNotBlank(request.getParameter("ctry"))){
			contract.setCountry(request.getParameter("ctry"));
		}
		if(StringUtils.isNotBlank(request.getParameter("state"))){
			String region=request.getParameter("state");
			String regFinal=region.substring(0, region.length()-2);
			if(region.indexOf("^s")!=-1){
				contract.setState(regFinal);	
			}else if(region.indexOf("^p")!=-1){
				contract.setProvince(regFinal);	
			}else if(region.indexOf("^c")!=-1){
				contract.setCounty(regFinal);	
			}else if(region.indexOf("^d")!=-1){
				contract.setDistrict(regFinal);	
			}
			
		}
		
		
		if(StringUtils.isNotBlank(request.getParameter("cty"))){
			contract.setCity(request.getParameter("cty"));
		}
		
		if(StringUtils.isNotBlank(request.getParameter("site"))){
			contract.setLocationId(request.getParameter("site"));
			contract.setLocationType("Building");
		}
		if(StringUtils.isNotBlank(request.getParameter("bldng"))){
						
			if(request.getParameter("extra").equalsIgnoreCase("site")){
				contract.setLocationId(request.getParameter("bldng"));
				contract.setLocationType("Site");
			}else{
				contract.setLocationId(request.getParameter("bldng"));
				contract.setLocationType("Floor");
			}
			
			//contract.setLocationId(request.getParameter("bldng"));
			//contract.setLocationType("Floor");
		}
		if(StringUtils.isNotBlank(request.getParameter("flr"))){
			contract.setLocationId(request.getParameter("flr"));
			contract.setLocationType("Zone");
		}
		if(StringUtils.isNotBlank(request.getParameter("frCal"))){
			contract.setFirstLoccationCall(true);
		}
		if(StringUtils.isNotBlank(request.getParameter("aid"))){
			contract.setLbsAddressId(request.getParameter("aid"));
			
		}
		
		/*
		 * Hard coded as of now
		 * */
		
		/*contract.setMdmId("007915069");
		contract.setMdmLevel("Global");*/
		
		return contract;
	}
	
	/**
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	public static UserFilterSettingContract getFilterSettingContract(PortletRequest request) throws Exception{
		UserFilterSettingContract contract=new UserFilterSettingContract();
		
		String address=request.getParameter("address");
		String device=request.getParameter("device");
		String srRequest=request.getParameter("srRequest");
		String accountInfo=request.getParameter("account");
		String ispref=StringUtils.isNotBlank(request.getParameter("isPreference"))==true?request.getParameter("isPreference"):"N";
		
		LOGGER.debug(String.format("[address length =%s , value= %s \n]",(address==null?0:address.length()),address));
		LOGGER.debug(String.format("[device length =%s , value= %s \n]",(device==null?0:device.length()),device));
		LOGGER.debug(String.format("[srRequest length =%s , value= %s \n]",(srRequest==null?0:srRequest.length()),srRequest));
		LOGGER.debug(String.format("[accountInfo length =%s , value= %s \n]",(accountInfo==null?0:accountInfo.length()),accountInfo));
		LOGGER.debug(String.format("[isPreference length =%s , value= %s \n]",(ispref==null?0:ispref.length()),ispref));
		
		UserFilterSetting userFilterSetting=new UserFilterSetting();
		userFilterSetting.setUserId(LexmarkUserUtil.getUserId(request));
		userFilterSetting.setAddress(address);
		userFilterSetting.setDevice(device);
		userFilterSetting.setServiceRequest(srRequest);
		userFilterSetting.setAccount(accountInfo);
		userFilterSetting.setDefaultPref(ispref.charAt(0));
		contract.setFilterSetting(userFilterSetting);
		
		return contract;
	}
	/**
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	public static UserFilterSettingContract getFilterSettingOPSContract(PortletRequest request) throws Exception{
		UserFilterSettingContract contract=new UserFilterSettingContract();
		
		String address=request.getParameter("address");
		String device=request.getParameter("device");
		String srRequest=request.getParameter("srRequest");
		String preferenceName=request.getParameter("prefName");
		String isDefaultpref="N";
		if(StringUtils.isNotBlank(request.getParameter("isDefaultPref"))==true){
			if("Y".equalsIgnoreCase(request.getParameter("isDefaultPref"))){
				isDefaultpref="Y";
			}
		}
		String id=request.getParameter("id");
		
		LOGGER.debug(String.format("[id length =%s , value= %s \n]",(id==null?0:id.length()),id));
		LOGGER.debug(String.format("[address length =%s , value= %s \n]",(address==null?0:address.length()),address));
		LOGGER.debug(String.format("[device length =%s , value= %s \n]",(device==null?0:device.length()),device));
		LOGGER.debug(String.format("[srRequest length =%s , value= %s \n]",(srRequest==null?0:srRequest.length()),srRequest));
		LOGGER.debug(String.format("[preferenceName length =%s , value= %s \n]",(preferenceName==null?0:preferenceName.length()),preferenceName));
		LOGGER.debug(String.format("[isDefaultpref length =%s , value= %s \n]",(isDefaultpref==null?0:isDefaultpref.length()),isDefaultpref));
		
		OPSUserFilterPopupSetting opsUserFilterSetting=new OPSUserFilterPopupSetting();
		opsUserFilterSetting.setUserId(LexmarkUserUtil.getUserId(request));
		opsUserFilterSetting.setAddress(address);
		opsUserFilterSetting.setDevice(device);
		opsUserFilterSetting.setServiceRequest(srRequest);
		opsUserFilterSetting.setPrefName(preferenceName);
		opsUserFilterSetting.setDefaultPref(isDefaultpref.charAt(0));
		if(StringUtils.isNotBlank(id)==true){
			opsUserFilterSetting.setRowId(id);
		}		
		contract.setOpsFilterSetting(opsUserFilterSetting);
		
		return contract;
	}
	/**
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	public static UserFilterSettingContract getFieldSettingContract(PortletRequest request) throws Exception{
		UserFilterSettingContract contract=new UserFilterSettingContract();
		String indexes=request.getParameter("indexes");
		LOGGER.debug(String.format("[indexes length =%s , value= %s \n]",(indexes==null?0:indexes.length()),indexes));
		
		UserFieldsViewSetting userFieldViewSetting=new UserFieldsViewSetting();
		userFieldViewSetting.setUserNumber(LexmarkUserUtil.getUserId(request));
		userFieldViewSetting.setFieldsDisplayed(indexes);
		userFieldViewSetting.setPrefId(request.getParameter("id"));
		userFieldViewSetting.setPortalName("IT");
		contract.setFieldsViewSetting(userFieldViewSetting);
		return contract;
	}
	
	/**
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	public static UserFilterSettingContract getFieldSettingContractOPS(PortletRequest request) throws Exception{
		UserFilterSettingContract contract=new UserFilterSettingContract();
		String indexes=request.getParameter("indexes");
		LOGGER.debug(String.format("[indexes length =%s , value= %s \n]",(indexes==null?0:indexes.length()),indexes));
		
		UserFieldsViewSettingOPS userFieldViewSetting=new UserFieldsViewSettingOPS();
		userFieldViewSetting.setUserNumber(LexmarkUserUtil.getUserId(request));
		userFieldViewSetting.setFieldsDisplayed(indexes);
		userFieldViewSetting.setPrefId(request.getParameter("id")==null?null:request.getParameter("id"));
		contract.setOpsfieldsViewSetting(userFieldViewSetting);
		return contract;
	}
	
	public static LBSAssetListContract getLBSAssetListContract(PortletRequest request){
		LBSAssetListContract contract = new LBSAssetListContract();
		//contract.setMdmId(PortalSessionUtil.getMdmId(request.getPortletSession()));
		//contract.setMdmLevel(PortalSessionUtil.getMdmLevel(request.getPortletSession()));
		contract.setNewQueryIndicator(true);
		contract.setLbsFlag(true);
		if(StringUtils.isNotBlank(request.getParameter("deviceId"))){
			contract.setAssetIds(Arrays.asList((request.getParameter("deviceId").split(","))));
		}
		return contract;
	}
	public static LBSCHLContract getLBSCHLContract(PortletRequest request){
		LBSCHLContract contract=new LBSCHLContract();
		contract.setChildID(PortalSessionUtil.getChlNodeId(request.getPortletSession()));
		return contract;
	}
	public static LocationReportingHierarchyContract getCustomerHierarchyChildNodeContract() {
		LocationReportingHierarchyContract contract = new LocationReportingHierarchyContract();
		return contract;
	}
	
	/**
	 * @return 
	 */
	public static PlacementContract getPlacementContract(PortletRequest request, String action) {
		PlacementContract contract = new PlacementContract();
		PortletSession session=request.getPortletSession();
		if("addPlacement".equalsIgnoreCase(action)){

			GenericAddress address = new GenericAddress();
			String addressArray[] = {"lat","lng","floorNumber","id","buildingId","buildingName","regionId","regionName",
					"address","city","state","country","zipCode",
					"extAddressId","campusId","campusName","floorId","floorName","placementId","gridX","gridY","storeFrontName"}; 
			 Map<String,String> addressMap= new HashMap<String, String>();
			for (int i=0;i<addressArray.length;i++){
				String val=request.getParameter(addressArray[i]);				
				addressMap.put(addressArray[i], val==null?"":val);				
				LOGGER.debug("array Values for Add--- "+addressArray[i]+"--- "+request.getParameter(addressArray[i]));
			}
			
			contract.setAddressMap(addressMap);
			Account acc= new Account();
			acc.setAccountId(request.getParameter("accountId"));
			contract.setAccount(acc);
			contract.setPlacementName(request.getParameter("plcName"));
			contract.setModelName(request.getParameter("plcModel"));
			contract.setIpAddress(request.getParameter("plcIp"));
			
		}
		else if("changePlacement".equalsIgnoreCase(action)){
			GenericAddress address = new GenericAddress();
			String addressArray[] = {"lat","lng","floorNumber","id","buildingId","buildingName","regionId","regionName",
					"address","city","state","country","zipCode",
					"extAddressId","campusId","campusName","floorId","floorName","placementId","gridX","gridY"}; 
			 Map<String,String> addressMap= new HashMap<String, String>();
			for (int i=0;i<addressArray.length;i++){
				String val=request.getParameter(addressArray[i]);				
				addressMap.put(addressArray[i], val==null?"":val);				
				LOGGER.debug("array Values--- "+addressArray[i]+"--- "+request.getParameter(addressArray[i]));
			}
			
			contract.setAddressMap(addressMap);
			contract.setPlacementId(request.getParameter("placementId"));
			contract.setPlacementName(request.getParameter(""));
			contract.setModelName(request.getParameter(""));
			contract.setIpAddress(request.getParameter(""));
		}
		else if("removePlacement".equalsIgnoreCase(action)){
		contract.setPlacementId(request.getParameter("placementId"));
		}
		return contract;
	}
	public static LBSAssetListContract getLBSPlacementContract(ManageAssetForm masForm) {
		LBSAssetListContract contract = new LBSAssetListContract();
		contract.setNewQueryIndicator(true);
		contract.setLbsFlag(true);
		contract.setSerialNumber(masForm.getAssetDetail().getSerialNumber());
		contract.setProductName(masForm.getAssetDetail().getProductLine());
		return contract;
		
	}
}