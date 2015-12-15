package com.lexmark.util;
 
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

import javax.portlet.ActionRequest;
import javax.portlet.PortletRequest;
import javax.portlet.PortletSession;
import javax.portlet.ResourceRequest;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import com.lexmark.constants.LexmarkConstants;
import com.lexmark.constants.LexmarkPPConstants;
import com.lexmark.constants.MassUploadFlags;
import com.lexmark.contract.AccountPayableDetailContract;
import com.lexmark.contract.AccountPayableListContract;
import com.lexmark.contract.AccountReceivableListContract;
import com.lexmark.contract.ActivityDebriefSubmitContract;
import com.lexmark.contract.ActivityDetailContract;
import com.lexmark.contract.ActivityListContract;
import com.lexmark.contract.AddressListContract;
import com.lexmark.contract.AssignedTechnicianUpdateContract;
import com.lexmark.contract.AttachmentContract;
import com.lexmark.contract.BulkUploadStatusContract;
import com.lexmark.contract.ClaimDebriefSubmitContract;
import com.lexmark.contract.ClaimDetailContract;
import com.lexmark.contract.ClaimUpdateContract;
import com.lexmark.contract.ContactInformationContract;
import com.lexmark.contract.ContactListContract;
import com.lexmark.contract.CustomerAccountListContract;
import com.lexmark.contract.DeleteAttachmentContract;
import com.lexmark.contract.FRUPartDetailContract;
import com.lexmark.contract.FRUPartListContract;
import com.lexmark.contract.FavoriteAddressContract;
import com.lexmark.contract.FavoriteContract;
import com.lexmark.contract.GlobalAssetDetailContract;
import com.lexmark.contract.GlobalPartnerAssetListContract;
import com.lexmark.contract.InvoiceListContract;
import com.lexmark.contract.LdapUserDataContract;
import com.lexmark.contract.LocalizedSiebelLOVListContract;
import com.lexmark.contract.LocalizedSiebelValueContract;
import com.lexmark.contract.LocationReportingHierarchyContract;
import com.lexmark.contract.MassUploadTemplateContract;
import com.lexmark.contract.OpenClaimListContract;
import com.lexmark.contract.PartnerAccountListContract;
import com.lexmark.contract.PartnerAddressListContract;
import com.lexmark.contract.PartnerAgreementListContract;
import com.lexmark.contract.PartnerFavoriteAddressUpdateContract;
import com.lexmark.contract.PartnerIndirectAccountListContract;
import com.lexmark.contract.PaymentDetailsContract;
import com.lexmark.contract.PaymentLineItemDetailsContract;
import com.lexmark.contract.PaymentListContract;
import com.lexmark.contract.PaymentRequestListContract;
import com.lexmark.contract.SRListContract;
import com.lexmark.contract.ServiceActivityHistoryDetailContract;
import com.lexmark.contract.ServiceActivityHistoryListContract;
import com.lexmark.contract.ServicesUserContract;
import com.lexmark.contract.SiebelAccountListContract;
import com.lexmark.contract.SiebelLOVListContract;
import com.lexmark.contract.TechnicianListContract;
import com.lexmark.contract.ValidateInstalledPrinterSerialNumberContract;
import com.lexmark.contract.WarrantyClaimCreateContract;
import com.lexmark.contract.source.OrderAcceptContract;
import com.lexmark.contract.source.OrderDetailContract;
import com.lexmark.contract.source.OrderListContract;
import com.lexmark.contract.source.OrderUpdateContract;
import com.lexmark.contract.source.RequestAcceptContract;
import com.lexmark.domain.Activity;
import com.lexmark.domain.ListOfValues;
import com.lexmark.domain.Order;
import com.lexmark.domain.Pagination;
import com.lexmark.domain.ServiceRequestOrderLineItem;
import com.lexmark.form.source.OrderDetailForm;
import com.lexmark.result.AttachmentResult;
import com.lexmark.service.impl.real.enums.QueryType;
import com.lexmark.service.util.SearchContractUtil;
import com.lexmark.util.PortalSessionUtil;
import com.lexmark.util.ChangeMgmtConstant;
import com.lexmark.util.PaginationUtil;
import com.liferay.portal.util.PortalUtil;

/**
 * Factory to create contracts for service methods.
 *@author wipro 
 *@version 1.0 
 */

public final class ContractFactory {
	private static Logger LOGGER = LogManager.getLogger(ContractFactory.class);

   //private static List<String> errorList;
   private static List<String> quantity;
   private static List<String> partNumber;
   private static List<String> shipQuantity;
   private static List<String> carrier;
   private static List<String> tracking;
   private static List<String> actualShippedDate;
   private static List<String> backOrderedQty;
   private static List<String> ETA;
   private static List<String> serialNumber;
   //private static List<String> serialNumberList;
   private static List<String> lineId;

   private static List<String> status;
   private static List<String> deliveryDate;
   private static List<String> processedPart;
   private static List<String> processedTrackingList;
   private static List<String> processedLineId;
   
   private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
   private static final SimpleDateFormat DATE_FORMAT_PAYMENT = new SimpleDateFormat("MM/dd/yyyy");
   public static final String POS_START = "posStart";
   
   private static final String[] accountAddressPopupColumns = new String[] {"addressName","storeFrontName","addressLine1","addressLine2","officeNumber","city","state","province","county","district","region","postalCode","country"};
   private static final String[] accountAddressColumns = new String[] {"addressName","storeFrontName","addressLine1","addressLine2","officeNumber","city","state","province","county","district","region","postalCode","country"};

   /**
    * Constructor
    * */
   private ContractFactory() {}

	
	/** 
	 * @param assetId 
	 * @param request 
	 * @return  OpenClaimListContract 
	 */
	
	public static OpenClaimListContract crateOpenClaimListContract(
			String assetId, ActionRequest request) {
		OpenClaimListContract contract = new OpenClaimListContract();
		contract.setAssetId(assetId);
		PortletSession session = request.getPortletSession();
		contract.setMdmId(PortalSessionUtil.getMdmId(session));
		contract.setMdmLevel(PortalSessionUtil.getMdmLevel(session));
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
		contract.setNewQueryIndicator(true);
		return contract;
	}
	

	/**
	 * Creates contract when search asset list.
	 * @param serialNumberParam 
	 * @return GlobalPartnerAssetListContract 
	 */
	public static GlobalPartnerAssetListContract createGlobalPartnerAssetListContract(String serialNumberParam){
		GlobalPartnerAssetListContract contract = new GlobalPartnerAssetListContract();
		contract.setSerialNumber(serialNumberParam);
		return contract;
	}
	
	/**
	 * @param lovName 
	 * @param errorCode1 
	 * @return SiebelLOVListContract 
	 */
	public static SiebelLOVListContract createSiebelLOVListContract(String lovName, String errorCode1){
		SiebelLOVListContract contract = new SiebelLOVListContract();
		if(lovName.trim().equalsIgnoreCase("ACTUAL_FAIL_CD")){
			LOGGER.debug("in if ACTUAIL_FAIL_CD");
			contract.setErrorCode1("Image Quality Issues");
		}		
		else{
			LOGGER.debug("in else");
			contract.setErrorCode1(errorCode1);
		}
		
		contract.setParentName(errorCode1);
		contract.setLovName(lovName);	
		 //added for grouping of errorcode1 & errorcode2 BRD# 13-10-04
		return contract;
	}
	
	/**
	 * @param partnerAccountId 
	 * @return TechnicianListContract 
	 */
	public static TechnicianListContract createTechnicianListContract(String  partnerAccountId){
		TechnicianListContract technicianListContract = new TechnicianListContract();
		technicianListContract.setPartnerAccountId(partnerAccountId);
		return technicianListContract;
	}

	/**
	 * @param lov 
	 * @param locale 
	 * @return LocalizedSiebelValueContract 
	 */
	public static LocalizedSiebelValueContract createLocalizedSiebelValueContract(ListOfValues lov, Locale locale) {
		com.lexmark.contract.LocalizedSiebelValueContract contract = new LocalizedSiebelValueContract();
		contract.setLocaleName(LocaleUtil.getSupportLocaleCode(locale));
		contract.setLovListName(lov.getType());
		contract.setLovValue(lov.getValue());
		return contract;
	}

	/**
	 * @param request 
	 * @return ActivityListContract 
	 */
	public static ActivityListContract createActivityListContract(ResourceRequest request) {
		ActivityListContract contract = new ActivityListContract();
		Pagination pagination = PaginationUtil.getPainationFromRequest(request,
				LexmarkPPConstants.PATTERNS_REQUEST_SORT, LexmarkPPConstants.PATTERNS_REQUEST_FILTER);
		SearchContractUtil.copyPaginationToContract(pagination, contract);
		final PortletSession session = request.getPortletSession();

		String queryType = request.getParameter("queryType");
		queryType = StringUtils.isEmpty(queryType) ? "All" : queryType;
		contract.setQueryType(queryType);
		
		String serviceRequestType = request.getParameter("requestType");
		serviceRequestType = StringUtils.isEmpty(serviceRequestType) ? "All" : serviceRequestType;
		boolean isIndirectPartner = PortalSessionUtil.getIndirectPartnerFlag(session);
		boolean isDirectPartner = PortalSessionUtil.getDirectPartnerFlag(session);
		/*if (isIndirectPartner && !isDirectPartner) {
			serviceRequestType = "Claim Request";
		} else if (!isIndirectPartner && isDirectPartner) {
			serviceRequestType = "Service Request";
		}*/

		if (queryType.equals(QueryType.Unassigned.toString())) {
			serviceRequestType = "Service Request";
		}
		//Done for CI-7 Defect #12304 --STARTS
		Map<String,List<String>> activityTypeMap=new LinkedHashMap<String,List<String>>();
		List<String> serviceTypeList= new ArrayList<String>();
		if(contract.getFilterCriteria().get("Activity.activityType")!=null)
		{
			
			serviceTypeList.add((contract.getFilterCriteria().get("Activity.activityType")).toString());
			activityTypeMap.put("Activity.activityType", serviceTypeList);
			LOGGER.debug("Activity Type from contract-->>"+contract.getFilterCriteria().get("Activity.activityType"));
		}
		//Done for CI-7 Defect #12304 --STARTS
		
		//Changes for MPS 2.1 for REquest type Filter for change management request
		
		if(contract.getFilterCriteria().get("Activity.serviceRequest.serviceRequestType")!=null){
			String value=(String)contract.getFilterCriteria().get("Activity.serviceRequest.serviceRequestType");
			if("Change Request".equalsIgnoreCase(value)){
				contract.getFilterCriteria().put("Activity.serviceRequest.serviceRequestType","Fleet Management");
			}
		}
		//ENDS Changes for MPS 2.1 for REquest type Filter for change management request
		
		contract.setServiceRequestType(serviceRequestType);
		if((Boolean)request.getPortletSession().getAttribute("IS_CREATE_CHILD_SR", javax.portlet.PortletSession.APPLICATION_SCOPE)){
		contract.setCurrentTimeStamp(DateUtil.converCurrentTimeStampToEquivalentGMTTime(new Date()));
		}
		String status = request.getParameter("status");
		status = StringUtils.isEmpty(status) ? "All" : status;
		contract.setStatus(status);

		String startDateStr = request.getParameter("beginDate");
		String endDateStr = request.getParameter("endDate");
		float timezoneOffset = Float.valueOf(request.getParameter(LexmarkConstants.TIMEZONE_OFFSET));
		try {
			if (StringUtils.isNotEmpty(startDateStr)) {
				Date startDate = LexmarkConstants.DEFAULT_DATE_FORMAT.parse(startDateStr);
				TimezoneUtil.adjustDate(startDate, timezoneOffset);
				contract.getFilterCriteria().put("Activity.activityDate.startDate", startDate);
			}

			if (StringUtils.isNotEmpty(endDateStr)) {
				Date endDate = LexmarkConstants.DEFAULT_DATE_FORMAT.parse(endDateStr);
				TimezoneUtil.adjustDate(endDate, timezoneOffset + 24);
				contract.getFilterCriteria().put("Activity.activityDate.endDate", endDate);
			}
			//Done for CI-7 Defect #12304 
			if (activityTypeMap.get("Activity.activityType")!=null) {
				contract.getFilterCriteria().put("Activity.activityType",activityTypeMap.get("Activity.activityType"));
			}
			
		} catch (ParseException e) {
			throw new IllegalArgumentException("Illegal date format,startDate[" + startDateStr + "];endDate["
					+ endDateStr + "]"+e.getMessage());
		}
		
		LOGGER.debug("child sr in filter criteria in contract factory is ---------- "+contract.getFilterCriteria().get("Activity.createChildSR"));
		if(contract.getFilterCriteria().get("Activity.createChildSR") != null){
			LOGGER.debug(" contract value in filter "+contract.getFilterCriteria().get("Activity.createChildSR"));
			
		//Added for Child SR Filter
			Date CurrentTimeStamp = DateUtil.converCurrentTimeStampToEquivalentGMTTime(new Date());
			LOGGER.debug("CurrentTimeStamp = " + CurrentTimeStamp);
			Date statusLastUpdate = new Date(CurrentTimeStamp.getTime() + TimeUnit.HOURS.toMillis(-18)); // Subtracts 18 hours
			LOGGER.debug("StatusLastUpdate1 = " + statusLastUpdate);
			
			DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
			String statusLastUpdateStr = df.format(statusLastUpdate);
			LOGGER.debug("statusLastUpdateStr value = " + statusLastUpdateStr);
			contract.setStatusLastUpdate(statusLastUpdateStr);
			
			LOGGER.debug("Status Last Update2 = " + contract.getStatusLastUpdate());
		//END
			
			contract.setCreateChildSR(contract.getFilterCriteria().get("Activity.createChildSR").toString());
			contract.getFilterCriteria().put("Activity.createChildSR", null);
			contract.setIncrement(-1); // set increment to -1 to get all the data 
			
		}
		contract.setEmployeeId(PortalSessionUtil.getContactId(session));
		contract.setEmployeeFlag(LexmarkUserUtil.isTechnician(request) && PortalSessionUtil.getLexmarkEmployeeFlag(session));
		
		contract.setMdmId(PortalSessionUtil.getMdmId(session));
		contract.setMdmLevel(PortalSessionUtil.getMdmLevel(session));
		contract.setChangeManagementFlag(false);
		
		
		return contract;
	}
	
	
	//added for pending debrief requirement
	
	/**
	 * @param request 
	 * @return ActivityListContract 
	 */
	public static ActivityListContract pendingActivityListContract(ResourceRequest request) {
		ActivityListContract contract = new ActivityListContract();
		Pagination pagination = PaginationUtil.getPainationFromRequest(request,
				LexmarkPPConstants.PATTERNS_REQUEST_SORT, LexmarkPPConstants.PATTERNS_REQUEST_FILTER);
		SearchContractUtil.copyPaginationToContract(pagination, contract);
		final PortletSession session = request.getPortletSession();
		
		contract.setQueryType("All");		
		contract.setServiceRequestType("All");
		String status = request.getParameter("status");
		status = StringUtils.isEmpty(status) ? "All" : status;
		contract.setStatus("Open");
		if((Boolean)request.getPortletSession().getAttribute("IS_CREATE_CHILD_SR", javax.portlet.PortletSession.APPLICATION_SCOPE)){
		contract.setCurrentTimeStamp(DateUtil.converCurrentTimeStampToEquivalentGMTTime(new Date()));
		}
		String startDateStr = request.getParameter("beginDate");
		String endDateStr = request.getParameter("endDate");
		LOGGER.debug("startDateStr----->>>"+startDateStr+"----endDateStr--->>>"+endDateStr);
		try{
		long currentTime = System.currentTimeMillis();
		Date today = new Date(currentTime);
		float timezoneOffset = Float.valueOf(request.getParameter(LexmarkConstants.TIMEZONE_OFFSET));
		LOGGER.debug("timezoneOffset-->>"+timezoneOffset);
		Calendar cal = new GregorianCalendar();	
		cal.setTime(today);
		cal.add(Calendar.DAY_OF_MONTH, -10);
		String formatToday = LexmarkConstants.DEFAULT_DATE_FORMAT.format(cal.getTime());
		LOGGER.debug("After formatting-->>"+formatToday);
		Date tenDaysBeforedate = LexmarkConstants.DEFAULT_DATE_FORMAT.parse(formatToday);
		TimezoneUtil.adjustDate(tenDaysBeforedate, timezoneOffset);
		
		contract.getFilterCriteria().put("Activity.claimStatusDate", tenDaysBeforedate);
		//contract.getFilterCriteria().put("Activity.activityDate.startDate", tenDaysBeforedate); //commented
		//contract.getFilterCriteria().put("Activity.serviceRequest.serviceRequestType", "Claim Request");
		//contract.getFilterCriteria().put("Activity.activityStatus", "Completed");
		//contract.getFilterCriteria().put("Activity.activityStatus", "Claim Submitted"); //for testing
		contract.getFilterCriteria().put("Activity.activitySubStatus","Claim Accepted");
		//contract.getFilterCriteria().put("Activity.activitySubStatus","Claim Submitted");  //for testing
		} catch (Exception e) {
			LOGGER.debug("Exception"+e.getMessage()); 
			throw new IllegalArgumentException("Illegal date format,startDate & endDate");
		}
		contract.setEmployeeId(PortalSessionUtil.getContactId(session));
		contract.setEmployeeFlag(LexmarkUserUtil.isTechnician(request) && PortalSessionUtil.getLexmarkEmployeeFlag(session));
		contract.setMdmId(PortalSessionUtil.getMdmId(session));
		contract.setMdmLevel(PortalSessionUtil.getMdmLevel(session));
		contract.setNewQueryIndicator(true);
		contract.setChangeManagementFlag(false);
		String queryType = request.getParameter("queryType");
		if("Pending".equals(queryType))
		{
			contract.setCountFlag(false);
		}
		else
		{
		contract.setCountFlag(true);
		}
		return contract;
	}
	
	//ends here
	
	//Added By MPS Offshore Team for Order List Contract
	/**
	 * @param request 
	 * @return OrderListContract 
	 */
	public static OrderListContract createOrderListContract(ResourceRequest request) {
		OrderListContract contract = new OrderListContract();
		Pagination pagination = PaginationUtil.getPainationFromRequest(request,
				LexmarkPPConstants.PATTERNS_REQUEST_SORT_SERVICE_ORDER, LexmarkPPConstants.PATTERNS_REQUEST_FILTER_SERVICE_ORDER);
		SearchContractUtil.copyPaginationToContract(pagination, contract);
		final PortletSession session = request.getPortletSession();



//		String serviceRequestType = "Order Request";
//		contract.setServiceRequestType(serviceRequestType);

		String status = request.getParameter("status");
		status = StringUtils.isEmpty(status) ? "Show All" : status;
		contract.setStatus(status);

		String startDateStr = request.getParameter("beginDate");
		String endDateStr = request.getParameter("endDate");
		float timezoneOffset = Float.valueOf(request.getParameter(LexmarkConstants.TIMEZONE_OFFSET));
		try {
			if (StringUtils.isNotEmpty(startDateStr)) {
				Date startDate = LexmarkConstants.DEFAULT_DATE_FORMAT.parse(startDateStr);
				TimezoneUtil.adjustDate(startDate, timezoneOffset);
				
				contract.getFilterCriteria().put("order.startDate", DateUtil.convertDateToGMTString(startDate));
			}

			if (StringUtils.isNotEmpty(endDateStr)) {
				Date endDate = LexmarkConstants.DEFAULT_DATE_FORMAT.parse(endDateStr);
				TimezoneUtil.adjustDate(endDate, timezoneOffset + 24);
				contract.getFilterCriteria().put("order.endDate", DateUtil.convertDateToGMTString(endDate));
			}
		} catch (ParseException e) {
			throw new IllegalArgumentException("Illegal date format,startDate[" + startDateStr + "];endDate["
					+ endDateStr + "]"+e.getMessage());
		}
		//Changes for massupload mps 2.1
		if(request.getAttribute(LexmarkPPConstants.ISMASSUPLOAD)!=null){
			
			Map<?,?> accountSelectionDetails=(Map<?,?>)request.getPortletSession().getAttribute(LexmarkPPConstants.PARTNER_ACCOUNT_SELECTION_DETAILS, PortletSession.APPLICATION_SCOPE);
			LOGGER.debug("vendor account id is "+accountSelectionDetails.get(LexmarkPPConstants.VENDORACC_ID));
			contract.setMdmId((String)accountSelectionDetails.get(LexmarkPPConstants.VENDORACC_ID));
			contract.setMdmLevel("Siebel");
			contract.setMassUploadRequest(true);//Added for June Release 14.6 for Defect 12103
		}else{	
			contract.setMdmId(PortalSessionUtil.getMdmId(session));
			contract.setMdmLevel(PortalSessionUtil.getMdmLevel(session));
		}
		//ends
		
		//contract.setMdmId("1-1G0U13D");
		
		
		return contract;
	}
	
	/**
	 * @param request 
	 * @return PaymentRequestListContract 
	 */
	public static PaymentRequestListContract createPaymentRequestListContract(ResourceRequest request) {
		PaymentRequestListContract contract = new PaymentRequestListContract();
		Pagination pagination = PaginationUtil.getPainationFromRequest(request,
				LexmarkPPConstants.PAYMENT_REQUEST_SORT, LexmarkPPConstants.PAYMENT_REQUEST_FILTER);
		SearchContractUtil.copyPaginationToContract(pagination, contract);
		final PortletSession session = request.getPortletSession();
		
		String paymentStatus = request.getParameter("paymentStatus");
		paymentStatus = StringUtils.isEmpty(paymentStatus) ? "All" : paymentStatus;
		contract.setPaymentStatus(paymentStatus);
		
		String partnerAgreement = request.getParameter("partnerAgreement");
		if (StringUtils.isNotEmpty(partnerAgreement)) {
			contract.getFilterCriteria().put("Activity.partnerAgreementName", partnerAgreement);
		}

		String beginDateStr = request.getParameter("beginDate");
		String endDateStr = request.getParameter("endDate");
		float timezoneOffset = Float.valueOf(request.getParameter(LexmarkConstants.TIMEZONE_OFFSET));
		try {
			if (StringUtils.isNotEmpty(beginDateStr)) {
				Date startDate = LexmarkConstants.DEFAULT_DATE_FORMAT.parse(beginDateStr);
				TimezoneUtil.adjustDate(startDate, timezoneOffset);
				contract.getFilterCriteria().put("Activity.startDate", startDate);
			}

			if (StringUtils.isNotEmpty(endDateStr)) {
				Date endDate = LexmarkConstants.DEFAULT_DATE_FORMAT.parse(endDateStr);
				TimezoneUtil.adjustDate(endDate, timezoneOffset + 24);
				contract.getFilterCriteria().put("Activity.endDate", endDate);
			}
		} catch (ParseException e) {
			throw new IllegalArgumentException("Illegal date format,startDate[" + beginDateStr + "];endDate[" + endDateStr
					+ "]"+e.getMessage());
		}
		contract.setMdmId(PortalSessionUtil.getMdmId(session));
		contract.setMdmLevel(PortalSessionUtil.getMdmLevel(session));
		// contract.setMdmId("93256");
        // contract.setMdmLevel("Siebel");
		return contract;
	}
	
	/**
	 * @param request 
	 * @return ClaimDetailContract 
	 */
	public static ClaimDetailContract createClaimDetailContract(PortletRequest request) {
		String activityId = request.getParameter("activityId");
		String serviceRequestId = request.getParameter("serviceRequestId");
		ClaimDetailContract contract = new ClaimDetailContract();
		contract.setActivityId(activityId);
		contract.setServiceRequestId(serviceRequestId);
		contract.setServicesUser(PortalSessionUtil.getServiceUser(request.getPortletSession()));
		return contract;
	}
	
	/**
	 * @param assetId 
	 * @param request 
	 * @return GlobalAssetDetailContract 
	 */
	public static GlobalAssetDetailContract createGlobalAssetDetailContract(String assetId, PortletRequest request){
		GlobalAssetDetailContract contract = new GlobalAssetDetailContract();
		contract.setAssetId(assetId);	
		PortletSession session = request.getPortletSession();
		contract.setMdmId(PortalSessionUtil.getMdmId(session));
		contract.setMdmLevel(PortalSessionUtil.getMdmLevel(session));
		return contract;
	}
	
	/**
	 * @param request 
	 * @return CustomerAccountListContract 
	 */
	public static CustomerAccountListContract createCustomerAccountListContract(ResourceRequest request){
		CustomerAccountListContract contract = new CustomerAccountListContract();
		PortletSession session = request.getPortletSession();
		contract.setMdmId(PortalSessionUtil.getMdmId(session));
		contract.setMdmLevel(PortalSessionUtil.getMdmLevel(session));
		return contract;
	}
	
	/**
	 * @param request 
	 * @return FRUPartDetailContract 
	 */
	public static FRUPartDetailContract createFRUPartDetailContract( PortletRequest request){
		
		FRUPartDetailContract contract = new FRUPartDetailContract();
		String partNumber = request.getParameter("partNumber");
		String modelNumber = request.getParameter("modelNumber");
		String orgId =  request.getParameter("organizationId");
		if(request.getParameter("replacementFlag")==null){
			contract.setReplacementFlag(false);
		}else{
			contract.setReplacementFlag(Boolean.valueOf(request.getParameter("replacementFlag")));
		}
		contract.setPartNumber(partNumber);
		contract.setPartnerOrg(orgId);
		contract.setModelNumber(modelNumber);
		
		return contract;
	}
	
	/**
	 * @param modelNumber 
	 * @return FRUPartListContract 
	 */
	public static FRUPartListContract createFRUPartListContract(String modelNumber){
		FRUPartListContract contract = new FRUPartListContract();
		contract.setModelNumber(modelNumber);	
		return contract;
	}
	
	/**
	 * @param userNumber 
	 * @param emailAddress 
	 * @return ServicesUserContract 
	 */
	public static ServicesUserContract getServicesUserContract(
			String userNumber,
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
	public static LdapUserDataContract getLdapUserDataContract(
			String emailAddress) {
		LdapUserDataContract contract = new LdapUserDataContract();
		contract.setEmailAddress(emailAddress);
		return contract;
	}

	/**
	 * @param assetId 
	 * @param serviceRequestId 
	 * @return ServiceActivityHistoryListContract 
	 */
	public static ServiceActivityHistoryListContract createServiceActivityHistoryListContract(String assetId,String serviceRequestId ) {
		ServiceActivityHistoryListContract contract = new ServiceActivityHistoryListContract();
		contract.setAssetId(assetId);
		contract.setServiceRequestId(serviceRequestId);
		return contract;
	}
	
	/**
	 * @param request 
	 * @return PartnerAddressListContract 
	 */
	public static PartnerAddressListContract createPartnerAddressListContract(PortletRequest request){
		//TODO:get account ID
		
		//added
		String isPopup=request.getParameter(ChangeMgmtConstant.ISPOPUP);
		Pagination pagination=null;
		
		if(isPopup!=null && isPopup.equalsIgnoreCase("true")){
			pagination = PaginationUtil.getPainationFromRequest(request,accountAddressPopupColumns, accountAddressColumns);
		}else{
			pagination = PaginationUtil.getPainationFromRequest(request,accountAddressColumns , accountAddressColumns);
		}
		//ends here
		PartnerAddressListContract contract = new PartnerAddressListContract();
		SearchContractUtil.copyPaginationToContract(pagination, contract);
		String favoriteFlag = request.getParameter("favoriteFlag");
		String accountId = request.getParameter("accountId");
		contract.setAccountID(accountId);
		contract.setFavoriteFlag(Boolean.valueOf(favoriteFlag));
		PortletSession session = request.getPortletSession();
		contract.setContactId(PortalSessionUtil.getContactId(session));
		
		return contract;
	}
	
	/**
	 * @param request 
	 * @return PartnerFavoriteAddressUpdateContract 
	 */
	public static PartnerFavoriteAddressUpdateContract createPartnerFavoriteAddressUpdateContract(PortletRequest request){
		
		PartnerFavoriteAddressUpdateContract contract = new PartnerFavoriteAddressUpdateContract();
		PortletSession session = request.getPortletSession();
		contract.setContactId(PortalSessionUtil.getContactId(session));
		
		return contract;
	}
	
	/**
	 * @param request 
	 * @return PartnerIndirectAccountListContract 
	 */
	public static PartnerIndirectAccountListContract createPartnerIndirectAccountListContract(PortletRequest request){
		PartnerIndirectAccountListContract contract = new PartnerIndirectAccountListContract();
		PortletSession session = request.getPortletSession();
		contract.setMdmId(PortalSessionUtil.getMdmId(session));
		contract.setMdmLevel(PortalSessionUtil.getMdmLevel(session));
		return contract;
	}

	/**
	 * @param activityId 
	 * @param request 
	 * @return AssignedTechnicianUpdateContract 
	 */
	public static AssignedTechnicianUpdateContract createAssignedTechnicianUpdateContract(
			String activityId, ResourceRequest request) {
		AssignedTechnicianUpdateContract contract = new AssignedTechnicianUpdateContract();
		String employeeId = PortalSessionUtil.getContactId(request.getPortletSession());
		contract.setActivityId(activityId);
		contract.setEmployeeId(employeeId);
		return contract;
	}
	
	//Added By MPS Offshore Team for Order Accept Contract creation
	
	/**
	 * @param orderId 
	 * @param vendorId 
	 * @return OrderAcceptContract 
	 */
	public static OrderAcceptContract createOrderAcceptContract(String orderId,String vendorId) {
		OrderAcceptContract contract = new OrderAcceptContract();
		//StringTokenizer st=new StringTokenizer(vendorId, ",");
		//List<String> vendorIds = new ArrayList<String>();
		//List<String> repeatedVendorIds = new ArrayList<String>();
		//List<String> list = new ArrayList<String>();
		/*while(st.hasMoreTokens())
		{
			list.add(st.nextToken());
		}
		
		for(int i=0;i<list.size();i++)
		{			
			for(int j=i+1;j<list.size();j++)
			{
				if(list.get(i).equals(list.get(j)))
				{
					repeatedVendorIds.add(list.get(j));
				}
			}
		}
		
		if(repeatedVendorIds.size()>0)
		{
			for(int i=0;i<list.size();i++)
			{
				boolean repeated = false;
				for(int j=0;j<repeatedVendorIds.size();j++)
				{
					if(list.get(i).equals(repeatedVendorIds.get(j)))
					{
						repeated = true;
						break;
					}					
				}
				if(repeated == false)
				{
					vendorIds.add(list.get(i));
				}
			}	
			for(int i=0;i<repeatedVendorIds.size();i++)
			{
				vendorIds.add(repeatedVendorIds.get(i));
			}			
		}
		else{
			for(int i=0;i<list.size();i++)
			{
				vendorIds.add(list.get(i));
			}
		}
		
		if(vendorIds.size()>0)
		{
			for(int i=0;i<vendorIds.size();i++)
			{
				logger.info("************Vendor ID in the ultimate list************ "+vendorIds.get(i));
			}
		}*/
		
		
		String[] arrVendorIds =null;
		
		if(vendorId != null && vendorId.contains(",")){
			if(vendorId.endsWith(",")){
				vendorId = vendorId.substring(0, vendorId.length()-1);
			}
			arrVendorIds = vendorId.split(",");
		}
		
		
		
		if(arrVendorIds != null){
			contract.setVendorIds(Arrays.asList(arrVendorIds));
		}
		
//		Date statusUpdateDate = calendar.getTime();
		contract.setOrderId(orderId);
//		contract.setVendorIds(vendorIds);
//		contract.setVendorId(vendorId);
//		contract.setOrderLineItemId("1-2GOHCUU");
		contract.setStatus("Order Accepted");
		return contract;
	}
	
	/**
	 * @param request 
	 * @return ActivityDetailContract 
	 */
	public static ActivityDetailContract createActivityDetailContract(PortletRequest request) {
		String serviceRequestId = request.getParameter("serviceRequestId");
		String activityId = request.getParameter("activityId");
		ActivityDetailContract contract = new ActivityDetailContract();
		contract.setActivityId(activityId);
		contract.setServiceRequestId(serviceRequestId);
        contract.setServicesUser(PortalSessionUtil.getServiceUser(request.getPortletSession()));
		return contract;
	}

	/**
	 * @param activityId 
	 * @param serviceRequestId 
	 * @return ServiceActivityHistoryDetailContract 
	 */
	public static ServiceActivityHistoryDetailContract createServiceActivityHistoryDetailContract(String activityId, String serviceRequestId) {
		ServiceActivityHistoryDetailContract contract = new ServiceActivityHistoryDetailContract();
		contract.setActivityId(activityId);
		contract.setServiceRequestId(serviceRequestId);
		return contract;
	}
	
	/**
	 * @param activity 
	 * @return ClaimDebriefSubmitContract 
	 */
	public static ClaimDebriefSubmitContract createClaimDebriefSubmitContract(Activity activity){
		ClaimDebriefSubmitContract claimDebriefSubmitContract = new ClaimDebriefSubmitContract();
		claimDebriefSubmitContract.setActivity(activity);
		return claimDebriefSubmitContract;
	}

	/**
	 * @param request 
	 * @param activity 
	 * @return WarrantyClaimCreateContract 
	 */
	public static WarrantyClaimCreateContract createWarrantyClaimContract(
			ActionRequest request, Activity activity) {
		
		WarrantyClaimCreateContract contract = new WarrantyClaimCreateContract();
		//contract.setClaimDraftStatus("Draft");  //commented as while submitting the claim we are not passing the status as Draft
		contract.setActivity(activity);
		contract.setMdmId(PortalSessionUtil.getMdmId(request.getPortletSession()));
		contract.setMdmLevel(PortalSessionUtil.getMdmLevel(request.getPortletSession()));
		return contract;
	}

	/**
	 * @param request 
	 * @param activity 
	 * @return ClaimUpdateContract 
	 */
	public static ClaimUpdateContract createClaimUpdateContract(
			ActionRequest request, Activity activity) {
		ClaimUpdateContract contract = new ClaimUpdateContract();
		contract.setSrType("Draft"); //Done for CI BRD13-10-01
		contract.setActivity(activity);
		contract.setMdmId(PortalSessionUtil.getMdmId(request.getPortletSession()));
		contract.setMdmLevel(PortalSessionUtil.getMdmLevel(request.getPortletSession()));
		return contract;		
	}

	/**
	 * @param paymentId 
	 * @param request 
	 * @return PaymentDetailsContract 
	 */
	public static PaymentDetailsContract createPaymentDetailsContract(
			String paymentId, PortletRequest request) {
		PaymentDetailsContract contract = new PaymentDetailsContract();
		contract.setPaymentId(paymentId);
		contract.setServicesUser(PortalSessionUtil.getServiceUser(request.getPortletSession()));
		return contract;
	}

	/**
	 * @param request 
	 * @return PaymentLineItemDetailsContract 
	 */
	public static PaymentLineItemDetailsContract createPaymentLineItemDetailsContract(
			ResourceRequest request) {
		PaymentLineItemDetailsContract contract = new PaymentLineItemDetailsContract();
		Pagination pagination = PaginationUtil.getPainationFromRequest(request,
				LexmarkPPConstants.PAYMENT_DETAILS_SORT, LexmarkPPConstants.PAYMENT_DETAILS_FILTER);
		SearchContractUtil.copyPaginationToContract(pagination, contract);
		contract.setPaymentId(request.getParameter("paymentId"));
		return contract;
	}

	/**
	 * @param request 
	 * @return  PaymentListContract 
	 */
	public static PaymentListContract createPaymentListContract(ResourceRequest request) {
		PaymentListContract contract = new PaymentListContract();
		Pagination pagination = PaginationUtil.getPainationFromRequest(request,
				LexmarkPPConstants.PATTERNS_PAYMENT_PAYMENT_SORT, LexmarkPPConstants.PATTERNS_PAYMENT_PAYMENT_FILTER);
		SearchContractUtil.copyPaginationToContract(pagination, contract);

		String partnerAgreement = request.getParameter("partnerAgreement");
		if (StringUtils.isNotEmpty(partnerAgreement)) {
			contract.getFilterCriteria().put("Payment.partnerAgreement", partnerAgreement);
		}

		String beginDateStr = request.getParameter("beginDate");
		String endDateStr = request.getParameter("endDate");
		float timezoneOffset = Float.valueOf(request.getParameter(LexmarkConstants.TIMEZONE_OFFSET));
		try {
			if (StringUtils.isNotEmpty(beginDateStr)) {
				Date startDate = LexmarkConstants.DEFAULT_DATE_FORMAT.parse(beginDateStr);
				TimezoneUtil.adjustDate(startDate, timezoneOffset);
				contract.getFilterCriteria().put("Payment.startDate", startDate);
			}

			if (StringUtils.isNotEmpty(endDateStr)) {
				Date endDate = LexmarkConstants.DEFAULT_DATE_FORMAT.parse(endDateStr);
				TimezoneUtil.adjustDate(endDate, timezoneOffset + 24);
				contract.getFilterCriteria().put("Payment.endDate", endDate);
			}
		} catch (ParseException e) {
			throw new IllegalArgumentException("Illegal date format,startDate[" + beginDateStr + "];endDate[" + endDateStr
					+ "]"+e.getMessage());
		}

		contract.setMdmId(PortalSessionUtil.getMdmId(request.getPortletSession()));
		contract.setMdmLevel(PortalSessionUtil.getMdmLevel(request.getPortletSession()));
		return contract;
	}

	/**
	 * @param request 
	 * @return PartnerAgreementListContract 
	 */
	public static PartnerAgreementListContract createPartnerAgreementListContract(PortletRequest request) {
		PartnerAgreementListContract contract = new PartnerAgreementListContract();
		contract.setMdmId(PortalSessionUtil.getMdmId(request.getPortletSession()));
		contract.setMdmLevel(PortalSessionUtil.getMdmLevel(request.getPortletSession()));
		return contract;
	}
	
	/**
	 * @param request 
	 * @return ValidateInstalledPrinterSerialNumberContract 
	 */
	public static ValidateInstalledPrinterSerialNumberContract createValidateInstalledPrinterSerialNumberContract(PortletRequest request){
		ValidateInstalledPrinterSerialNumberContract contract = new ValidateInstalledPrinterSerialNumberContract();
		String activityId = request.getParameter("activityId");
		String modelNumber = request.getParameter("modelNumber");
		String serialNumber = request.getParameter("serialNumber");
		String serviceRequestId = request.getParameter("serviceRequestId");
		
		
		contract.setActivityId(activityId);
		contract.setModelNumber(modelNumber);
		contract.setSerialNumber(serialNumber);
		contract.setServiceRequestId(serviceRequestId);
		return contract;
	}
	
	/**
	 * @param activity 
	 * @return ActivityDebriefSubmitContract 
	 */
	public static ActivityDebriefSubmitContract createActivityDebriefSubmitContract(Activity activity){
		ActivityDebriefSubmitContract activityDebriefSubmitContract = new ActivityDebriefSubmitContract();
		activityDebriefSubmitContract.setActivity(activity);
		return activityDebriefSubmitContract;
	}
	
	/**
	 * @param request 
	 * @return  ContactInformationContract 
	 * @throws Exception 
	 */
	public static ContactInformationContract getContactInformationContract(PortletRequest request) throws Exception{
		ContactInformationContract contract = new ContactInformationContract();
		
		PortletSession session = request.getPortletSession();
		contract.setMdmId(PortalSessionUtil.getMdmId(session));
		contract.setMdmLevel(PortalSessionUtil.getMdmLevel(session));
		
		return contract;
	}
	
	/**
	 * @param lovType 
	 * @param partnerType 
	 * @param locale 
	 * @return  LocalizedSiebelLOVListContract 
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
	 * @return BulkUploadStatusContract 
	 */
	public static BulkUploadStatusContract getBulkUploadStatusContract(){
		BulkUploadStatusContract contract = new BulkUploadStatusContract();
		return contract;
	}

	//Added by MPS Offshore Team for Order Detail Contract Create
	/**
	 * @param request 
	 * @return OrderDetailContract 
	 */ 
	public static OrderDetailContract createOrderDetailContract(PortletRequest request) {
//		String activityId = request.getParameter("activityId");
//		String serviceRequestId = request.getParameter("serviceRequestId");
		
		String vendorAccountIds = request.getParameter("vendorAccountId");
		String orderNumber = request.getParameter("orderNumber");
		String requestNumber = request.getParameter("requestNumber");
		String[] arrVendorIds =null;
		
		/*Added for Phase 2 Mass upload 
		 * */
		if(request.getAttribute("fromMassUpload")!=null){
				HttpServletRequest httpServletReq = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(request));
				vendorAccountIds= httpServletReq.getParameter(LexmarkPPConstants.VENDORACC_ID);
				orderNumber= httpServletReq.getParameter("orderNumber");
				requestNumber = httpServletReq.getParameter("requestNumber");
		}
		/*Ends*/
		
		if(vendorAccountIds != null && vendorAccountIds.contains(",")){
			if(vendorAccountIds.endsWith(",")){
				vendorAccountIds = vendorAccountIds.substring(0, vendorAccountIds.length()-1);
			}
			arrVendorIds = vendorAccountIds.split(",");
		}
		
		OrderDetailContract contract = new OrderDetailContract();
		
		if(arrVendorIds != null){
			contract.setVendorAccountIds(Arrays.asList(arrVendorIds));
		}
		
		contract.setOrderNumber(orderNumber);
		contract.setRequestNumber(requestNumber);
		return contract;
	}
	
	//Added by MPS Offshore Team for Order Update Contract Create
	/**
	 * @param request 
	 * @param orderDetailForm 
	 * @return OrderUpdateContract 
	 * @throws Exception 
	 */
	public static OrderUpdateContract createOrderUpdateContract(PortletRequest request,OrderDetailForm orderDetailForm) throws Exception
	{

		quantity=new ArrayList<String>();
		partNumber=new ArrayList<String>();
		shipQuantity=new ArrayList<String>();
		carrier=new ArrayList<String>();
		tracking=new ArrayList<String>();
		actualShippedDate=new ArrayList<String>();
		backOrderedQty=new ArrayList<String>();
		ETA=new ArrayList<String>();
		serialNumber=new ArrayList<String>();
		lineId=new ArrayList<String>();
		
		status=new ArrayList<String>();
		deliveryDate=new ArrayList<String>();
		processedPart=new ArrayList<String>();
		processedLineId=new ArrayList<String>();
		processedTrackingList=new ArrayList<String>();
		
//			Pending Shipment Info
		putDataInList(quantity,orderDetailForm.getQuantityList());
		putDataInList(partNumber,orderDetailForm.getPartNumberList());
		putDataInList(shipQuantity,orderDetailForm.getShipQuantityList());
		putDataInList(carrier,orderDetailForm.getCarrierList());
		putDataInList(tracking,orderDetailForm.getTrackingList());
		putDataInList(actualShippedDate,orderDetailForm.getActualShippedDateList());
		putDataInList(backOrderedQty,orderDetailForm.getBackOrderedQtyList());
		putDataInList(ETA,orderDetailForm.getETAList());
		putDataInListforSN(serialNumber,orderDetailForm.getSerialNumberList());			
		putDataInList(lineId,orderDetailForm.getLineIdList());
		
		
//			Processed Part Info
		putDataInList(status,orderDetailForm.getStatusList());
		putDataInList(deliveryDate,orderDetailForm.getDeliveryDateList());
		putDataInList(processedPart,orderDetailForm.getProcessedPartList());
		putDataInList(processedLineId,orderDetailForm.getProcessedLineIdList());
		putDataInList(processedTrackingList,orderDetailForm.getProcessedTrackingList());
		
		
		
		OrderUpdateContract contract = new OrderUpdateContract();
		Order order = new Order();
			
		if(partNumber!=null && !(partNumber.isEmpty()))
		{	
			ServiceRequestOrderLineItem[] pendingShipmentList = new ServiceRequestOrderLineItem[partNumber.size()];
			
			LOGGER.info("outside partnumber in pending shipment");
			for (int i=0;i<partNumber.size();i++)
			{
				/*if(shipQuantity.get(i)== null || shipQuantity.get(i).equalsIgnoreCase("empty"))
				{
					continue;
				}*/
				
				/*if((shipQuantity.get(i) == null || shipQuantity.get(i).equalsIgnoreCase("null") || shipQuantity.get(i).equalsIgnoreCase("empty"))
						&& (backOrderedQty.get(i) == null || backOrderedQty.get(i).equalsIgnoreCase("null") || backOrderedQty.get(i).equalsIgnoreCase("empty") || backOrderedQty.get(i).equalsIgnoreCase("doesnotExist")))
			    {
				   continue;
			    }*/
				
				
				ServiceRequestOrderLineItem partLine = new ServiceRequestOrderLineItem();
				
				partLine.setId(lineId.get(i));
				if(quantity.get(i)!= null && !quantity.get(i).equalsIgnoreCase("empty")){
					partLine.setQuantity(quantity.get(i));
				}
				
				if(partNumber.get(i)!= null && !partNumber.get(i).equalsIgnoreCase("empty")){
					partLine.setProductTLI(partNumber.get(i));
				}
				else{
					partLine.setProductTLI("");
				}
				
				if(shipQuantity.get(i)!= null && !shipQuantity.get(i).equalsIgnoreCase("empty")){
					partLine.setShippedQuantity(Integer.valueOf(shipQuantity.get(i)));
				}else{
					partLine.setShippedQuantity(-999);
				}
				
				if(carrier.get(i)!= null && !carrier.get(i).equalsIgnoreCase("empty")){
					partLine.setCarrier(carrier.get(i));
				}
				
				if(tracking.get(i)!= null && !tracking.get(i).equalsIgnoreCase("empty")){
					partLine.setTrackingNumber(tracking.get(i));
				}
				
				if(actualShippedDate.get(i)!= null && !actualShippedDate.get(i).equalsIgnoreCase("empty")){	
					
					partLine.setActualShipDate(populateDate(actualShippedDate.get(i),false));
				}
				
				if(backOrderedQty.get(i)!= null && !backOrderedQty.get(i).equalsIgnoreCase("empty") && !backOrderedQty.get(i).equalsIgnoreCase("doesnotExist")){
					partLine.setBackOrderQuantity(Integer.valueOf(backOrderedQty.get(i)));
				}else{
					partLine.setBackOrderQuantity(Integer.valueOf(-999));
				}
				
				if(ETA!=null)
				{
					if(ETA.get(i)!= null && !ETA.get(i).equalsIgnoreCase("empty") && !ETA.get(i).equalsIgnoreCase("doesnotExist")){												
						partLine.setEta(ETA.get(i));
					}
				}
				
			
				if(serialNumber.get(i)!= null && !serialNumber.get(i).equalsIgnoreCase("empty") && !serialNumber.get(i).equalsIgnoreCase("doesnotExist")){
					partLine.setSerialNumber(serialNumber.get(i));
				}
				
				pendingShipmentList[i] = partLine;					
			}
			order.setPendingShipments(Arrays.asList(pendingShipmentList));	
			if(orderDetailForm.getServiceProviderOrderRefNo()!=null)
			{
				order.setServiceProviderReferenceNumber(orderDetailForm.getServiceProviderOrderRefNo());
			}
		}
				
		if(processedLineId!=null && !(processedLineId.isEmpty()))
		{	
			ServiceRequestOrderLineItem[] processedList = new ServiceRequestOrderLineItem[processedPart.size()];
			
			for (int i=0;i<processedPart.size();i++)
			{
				ServiceRequestOrderLineItem partLine = new ServiceRequestOrderLineItem();
				if(status.get(i)!= null && !status.get(i).equalsIgnoreCase("empty")){
					partLine.setStatus(status.get(i));
				}
				
				if(deliveryDate.get(i)!= null && !deliveryDate.get(i).equalsIgnoreCase("empty")){
					partLine.setActualDeliveryDate(populateDate(deliveryDate.get(i),false));
				}
				
				if(processedPart.get(i)!= null && !processedPart.get(i).equalsIgnoreCase("empty")){
					partLine.setProductTLI(processedPart.get(i));
				}
				
				
				if(processedLineId.get(i)!= null && !processedLineId.get(i).equalsIgnoreCase("empty")){
					partLine.setId(processedLineId.get(i));
				}
				
				if(processedTrackingList.get(i)!= null){
					partLine.setTrackingNumber(processedTrackingList.get(i));
				}
				
				processedList[i] = partLine;
				
			}		
			order.setProcessedParts(Arrays.asList(processedList));
		}
		
		order.setOrderNumber(orderDetailForm.getOrderDetail().getId());
		contract.setOrder(order);
		contract.setSourceReferenceNumber(orderDetailForm.getOrderDetail().getServiceRequestNumber());
//		contract.setVendorNumber("1-1G0U13D");
//		contract.setMdmId("1-1G0U13D");
//		contract.setMdmLevel("Siebel");
			
		return contract;
	}
		
		//Added by MPS Offshore Team for Order Update Contract Create
		/**
		 * @param list 
		 * @param data 
		 */
		private static void putDataInList(List<String> list,String data)
		{
			StringTokenizer st=new StringTokenizer(data, ",");
			while(st.hasMoreTokens())
				{
					list.add(st.nextToken());
				}
		}
		
		/**
		 * @param list 
		 * @param data 
		 */
		private static void putDataInListforSN(List<String> list,String data)
		{
			StringTokenizer st=new StringTokenizer(data, "|");
			while(st.hasMoreTokens())
				{
					list.add(st.nextToken());
				}
		}
		
		//Added by MPS Offshore Team for Order Update Contract Create
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
					Long microSeconds = Long.valueOf(24 * 60 * 60 * 1000 - 1);
					serverDate = new Date(serverDate.getTime() + microSeconds);
				}
				return serverDate;
			}
			return null;
		}
		
		/*Added by MPS Offshore team for AR CR implementation*/
		/**
		 * @param request 
		 * @return AccountReceivableListContract 
		 */
		public static AccountReceivableListContract getAccountRecievableListContract(PortletRequest request){
			AccountReceivableListContract accountRecievableListContract = new AccountReceivableListContract();
			PortletSession session = request.getPortletSession();
			accountRecievableListContract.setMdmId(PortalSessionUtil.getMdmId(session));
			accountRecievableListContract.setMdmLevel(PortalSessionUtil.getMdmLevel(session));
			
			/*accountRecievableListContract.setMdmId("018978783");
			accountRecievableListContract.setMdmLevel("Global");*/	
			
			return accountRecievableListContract;
		}
		
		/*Added by MPS Offshore team for AP CR implementation*/
		/**
		 * @param request 
		 * @return AccountPayableListContract 
		 */
		public static AccountPayableListContract getAccountPayableListContract(PortletRequest request){
			AccountPayableListContract accountPayableListContract = new AccountPayableListContract();
			PortletSession session = request.getPortletSession();
			accountPayableListContract.setMdmId(PortalSessionUtil.getMdmId(session));
			accountPayableListContract.setMdmLevel(PortalSessionUtil.getMdmLevel(session));
			
//			accountPayableListContract.setMdmId("253290464");
			
			/*accountPayableListContract.setMdmId("018978783");
			accountPayableListContract.setMdmLevel("Global");*/			
			
			return accountPayableListContract;
		}
		
		//Added By MPS Offshore Team for Invoice List Contract for AP
		/**
		 * @param request 
		 * @return  InvoiceListContract 
		 * @throws ParseException 
		 */
		public static InvoiceListContract createInvoiceListContractforAP(PortletRequest request) throws ParseException {
			InvoiceListContract contract = new InvoiceListContract();
			
			final PortletSession session = request.getPortletSession();
			Map<String,String> vendorDetailsMap=(Map<String,String>)session.getAttribute("vendorCurrentDetails",PortletSession.APPLICATION_SCOPE);		
			
			/*Date startDate = DATE_FORMAT_PAYMENT.parse("09/01/2010");
			Date endDate = DATE_FORMAT_PAYMENT.parse("10/15/2010");*/
			
			final Date fromDate;
			final Date endDate;
			/*if(request.getParameter("fromDate")!=null && request.getParameter("endDate")!=null && request.getParameter("fromDate")!="" && request.getParameter("endDate")!=""){
				
				//Partha added for localization
				SimpleDateFormat LOCALE_DATE_FORMAT_PAYMENT = new SimpleDateFormat(DateLocalizationUtil.getDateFormatByLanguage(request.getLocale().getLanguage()));
				
				endDate = LOCALE_DATE_FORMAT_PAYMENT.parse(request.getParameter("endDate"));
				if("05/01/2013".equals(request.getParameter("fromDate"))){
					fromDate = DATE_FORMAT_PAYMENT.parse(request.getParameter("fromDate"));
				}else{
					fromDate = LOCALE_DATE_FORMAT_PAYMENT.parse(request.getParameter("fromDate"));
				}
				
				LOGGER.info("from date from request " + fromDate + " end date from request " + endDate);				
			}
			else{
			Calendar currentDate = Calendar.getInstance();
			endDate = currentDate.getTime();
			currentDate.add(Calendar.MONTH, -6);
			fromDate = currentDate.getTime();
			}*/
			
			Calendar currentDate = Calendar.getInstance();
			endDate = currentDate.getTime();
			currentDate.add(Calendar.MONTH, -6);
			fromDate = currentDate.getTime();
			
			LOGGER.info("from date finally " + fromDate + " end date finally " + endDate);
			
			/* BRD-14-02-15 Implementation Start */ 
			
			String paymentStatus = request.getParameter("paymentStatus");
		
			if(paymentStatus==null || "".equals(paymentStatus)){
				paymentStatus = "Open";
			}
			
			if(vendorDetailsMap != null)
			{
				contract.setVendorID(vendorDetailsMap.get("vendorId"));
				contract.setCompanyCode(vendorDetailsMap.get("companyCode"));
				contract.setFromDate(fromDate);
				contract.setToDate(endDate);
				contract.setInvoiceAP(true);
				
				if("Open".equals(paymentStatus)){
					contract.setClearedItem(" ");	//1
					contract.setOpenItem("X");		//2
					contract.setSelectForPayments("X");	//3
					contract.setOnlyInvoice("X");		//4
				}else 
				if("Paid".equals(paymentStatus)){
					contract.setClearedItem("X");	//1
					contract.setOpenItem(" ");		//2
					contract.setSelectForPayments("X");	//3
					contract.setOnlyInvoice("X");		//4
				}else{
					contract.setClearedItem("X");	//1
					contract.setOpenItem("X");		//2
					contract.setSelectForPayments("X");	//3
					contract.setOnlyInvoice("X");		//4
				}
				
			}
			/* BRD-14-02-15 Implementation End */
			
			/*contract.setVendorID("20009450");
			contract.setCompanyCode("5050");*/
			
			/*contract.setVendorID("20013618");
			contract.setCompanyCode("5050");
			contract.setFromDate(fromDate);
			contract.setToDate(endDate);
			contract.setClearedItem("X");
			contract.setOpenItem("X");
			contract.setSelectForPayments("X");
			contract.setOnlyInvoice("X");*/			
			
			
			return contract;
		}
		/*End*/
		
		//Added By MPS Offshore Team for Invoice List Contract
		/**
		 * @param request 
		 * @return InvoiceListContract 
		 * @throws ParseException 
		 */
		@SuppressWarnings("unchecked")
		public static InvoiceListContract createInvoiceListContractforAR(
				PortletRequest request) throws ParseException {
			InvoiceListContract contract = new InvoiceListContract();
	
			final PortletSession session = request.getPortletSession();
			Map<String,String> vendorDetailsMap = (Map<String, String>) session
					.getAttribute("accRecivablDetails",
							PortletSession.APPLICATION_SCOPE);
			final Date fromDate;
			final Date endDate;
			/*if(request.getParameter("fromDate")!=null && request.getParameter("endDate")!=null && !"".equals(request.getParameter("fromDate")) && !"".equals(request.getParameter("endDate"))){
				
				//Partha added for localization
				SimpleDateFormat LOCALE_DATE_FORMAT_PAYMENT = new SimpleDateFormat(DateLocalizationUtil.getDateFormatByLanguage(request.getLocale().getLanguage()));
				endDate = LOCALE_DATE_FORMAT_PAYMENT.parse(request.getParameter("endDate"));
				
				if("05/01/2013".equals(request.getParameter("fromDate"))){
					
					fromDate = DATE_FORMAT_PAYMENT.parse(request.getParameter("fromDate"));
					
				}else{
					
					fromDate = LOCALE_DATE_FORMAT_PAYMENT.parse(request.getParameter("fromDate"));
					
				}
				LOGGER.info("from date from request " + fromDate + " end date from request " + endDate);				
			}
			else{
				
				Calendar currentDate = Calendar.getInstance();
				endDate = currentDate.getTime();
				currentDate.add(Calendar.MONTH, -6);
				fromDate = currentDate.getTime();
				
			}*/
			
			Calendar currentDate = Calendar.getInstance();
			endDate = currentDate.getTime();
			currentDate.add(Calendar.MONTH, -6);
			fromDate = currentDate.getTime();
			
			LOGGER.info("from date finally " + fromDate + " end date finally " + endDate);
			
	
			/* BRD-14-02-15 Implementation Start */
	
			String paymentStatus = request.getParameter("paymentStatus");
			
			if(paymentStatus==null || "".equals(paymentStatus)){
				paymentStatus = "Open";
			}
			
			 if(vendorDetailsMap != null) {
				contract.setVendorID(vendorDetailsMap.get("soldToNo"));
				contract.setCompanyCode(vendorDetailsMap.get("companyCode"));
				contract.setFromDate(fromDate); 
				contract.setToDate(endDate);
				contract.setInvoiceAP(false);
				
				if("Open".equals(paymentStatus)){
					contract.setClearedItem(" ");		//1
					contract.setOpenItem("X");			//2
					contract.setSelectForPayments("X");	//3
					contract.setOnlyInvoice("X");		//4
					contract.setParkedItems("X");		//5
					contract.setCheckDisputes("X");		//6
					contract.setPrevBalance("X");		//7
				}else 
				if("Paid".equals(paymentStatus)){
					contract.setClearedItem("X");		//1	
					contract.setOpenItem(" ");			//2
					contract.setSelectForPayments("X");	//3
					contract.setOnlyInvoice("X");		//4
					contract.setParkedItems(" ");		//5
					contract.setCheckDisputes(" ");		//6
					contract.setPrevBalance(" ");		//7
				}else{
					contract.setClearedItem("X");		//1
					contract.setOpenItem("X");			//2
					contract.setSelectForPayments("X");	//3
					contract.setOnlyInvoice("X");		//4
					contract.setParkedItems("X");		//5
					contract.setCheckDisputes("X");		//6
					contract.setPrevBalance("X");		//7
				}
			}
			 /* BRD-14-02-15 Implementation End */
	
			/*contract.setVendorID("143420");
			contract.setCompanyCode("5050");*/
			
			/*contract.setVendorID("222642");
			contract.setCompanyCode("5050");
			contract.setFromDate(fromDate);
			contract.setToDate(endDate);
			contract.setClearedItem("X");
			contract.setOpenItem("X");
			contract.setSelectForPayments("X");
			contract.setOnlyInvoice("X");
			contract.setInvoiceAP(false);
			contract.setParkedItems("X");
			contract.setCheckDisputes("X");
			contract.setPrevBalance("X");*/
			
			return contract;
		}
				/*End*/
		
		//Added By MPS Offshore Team for Invoice List Contract
		/**
		 * @param posStart 
		 * @param request 
		 * @return SRListContract 
		 * @throws ParseException 
		 */
		public static SRListContract createAPSRListContract(int posStart,ResourceRequest request) throws ParseException {
			SRListContract contract = new SRListContract();
			
			final PortletSession session = request.getPortletSession();
			Map<String,String> vendorDetailsMap=(Map<String,String>)session.getAttribute("vendorCurrentDetails",PortletSession.APPLICATION_SCOPE);		
			
//					Date startDate = DATE_FORMAT_PAYMENT.parse(request.getParameter("startDate"));
//					Date endDate = DATE_FORMAT_PAYMENT.parse(request.getParameter("endDate"));
			
			if(vendorDetailsMap != null)
			{
				contract.setVendorID(vendorDetailsMap.get("vendorId"));
				contract.setCompanyCode(vendorDetailsMap.get("companyCode"));
				contract.setInvoiceNumber(request.getParameter("invoiceNumber"));
				
			}
			
			/*contract.setVendorID("20009450");
			contract.setCompanyCode("5050");
			contract.setInvoiceNumber("004030000008");*/
			
			contract.setPosStart(posStart);
			return contract;
		}
				/*End*/
		
		//Added By MPS Offshore Team for Invoice List Contract
		/**
		 * @param posStart 
		 * @param request 
		 * @return SRListContract 
		 * @throws ParseException 
		 */
		public static SRListContract createARSRListContract(int posStart,ResourceRequest request) throws ParseException {
			SRListContract contract = new SRListContract();
			
			final PortletSession session = request.getPortletSession();
			Map<String,String> vendorDetailsMap=(Map<String,String>)session.getAttribute("accRecivablDetails",PortletSession.APPLICATION_SCOPE);		
			
//							Date startDate = DATE_FORMAT_PAYMENT.parse(request.getParameter("startDate"));
//							Date endDate = DATE_FORMAT_PAYMENT.parse(request.getParameter("endDate"));
			
			if(vendorDetailsMap != null)
			{
				contract.setVendorID(vendorDetailsMap.get("soldToNo"));
				contract.setCompanyCode(vendorDetailsMap.get("companyCode"));
				contract.setInvoiceNumber(request.getParameter("invoiceNumber"));
				
			}
			/*contract.setVendorID("222642");
			contract.setCompanyCode("5050");
			contract.setInvoiceNumber("7024358686");*/
			contract.setPosStart(posStart);
			return contract;
		}
						/*End*/

		/**
		 * @param request 
		 * @param session 
		 * @return AccountPayableDetailContract 
		 */
		public static AccountPayableDetailContract createSRDetailContract(ResourceRequest request, PortletSession session) {
			AccountPayableDetailContract srDetailContract = new AccountPayableDetailContract();
			final String serviceReqNo = request.getParameter("serviceReqNo");
			LOGGER.info("Service req no is " + serviceReqNo);
//			srDetailContract.setServiceRequestNumber(serviceReqNo);		
			srDetailContract.setMdmId(PortalSessionUtil.getMdmId(session));
			srDetailContract.setMdmLevel(PortalSessionUtil.getMdmLevel(session));
			srDetailContract.setServiceRequestNumber(serviceReqNo);
			/*srDetailContract.setServiceRequestNumber("1-478620759");
			srDetailContract.setMdmLevel("Global");
			srDetailContract.setMdmId("108023003");*/
			return srDetailContract;
		}
		
		/*
		 * Added for Upload History MPS 2.1
		 * */
		/**
		 * @param request 
		 * @return AttachmentContract 
		 */
		public static AttachmentContract getUploadHistoryAttachmentContract(PortletRequest request){
			AttachmentContract attachmentContract = new AttachmentContract();
			if(request.getAttribute(LexmarkPPConstants.VENDORACC_ID)!=null){
				attachmentContract.setIdentifier((String)request.getAttribute(LexmarkPPConstants.VENDORACC_ID));
			}
			PortletSession session=request.getPortletSession();
			
			Map<String,String> accountCurrentDetails=(Map<String,String>)session.getAttribute(LexmarkPPConstants.PARTNER_ACCOUNT_SELECTION_DETAILS, PortletSession.APPLICATION_SCOPE);
			Pagination pagination =null;
	
			if(StringUtils.isNotBlank(accountCurrentDetails.get(MassUploadFlags.MASSUPLOADHARDWARE.getFlagName())) && StringUtils.isNotBlank(accountCurrentDetails.get(MassUploadFlags.MASSUPLOADCONSUMABLES.getFlagName()))
					&& LexmarkPPConstants.TRUE_ATTR.equalsIgnoreCase((String)accountCurrentDetails.get(MassUploadFlags.MASSUPLOADHARDWARE.getFlagName())) 
					&& LexmarkPPConstants.TRUE_ATTR.equalsIgnoreCase((String)accountCurrentDetails.get(MassUploadFlags.MASSUPLOADCONSUMABLES.getFlagName()))){
				
				pagination = PaginationUtil.getPainationFromRequest(request,
						LexmarkPPConstants.PATTERNS_SORT_UPLOAD_HISTORY, LexmarkPPConstants.PATTERNS_FILTER_UPLOAD_HISTORY);	
			}else{
				
				pagination = PaginationUtil.getPainationFromRequest(request,
						LexmarkPPConstants.PATTERNS_SORT_UPLOAD_HISTORY, LexmarkPPConstants.PATTERNS_FILTER_UPLOAD_HISTORY_WITHOUT_TYPE);
			}
			
			
			SearchContractUtil.copyPaginationToContract(pagination, attachmentContract);
			return attachmentContract;
		}
		
		/*
		 * Added for AccountSelectionController MPS 2.1
		 * */
		/**
		 * @param request 
		 * @return PartnerAccountListContract 
		 */
		public static PartnerAccountListContract getPartnerAccountListContract(PortletRequest request){
			PartnerAccountListContract partnerAccountListContract = new PartnerAccountListContract();
			partnerAccountListContract.setMdmId(PortalSessionUtil.getMdmId(request.getPortletSession()));
			partnerAccountListContract.setMdmLevel(PortalSessionUtil.getMdmLevel(request.getPortletSession()));
			return partnerAccountListContract;
		}
		
		//Added for CI BRD13-10-08
		/**
		 * @return FavoriteAddressContract 
		 */ 
		public static FavoriteAddressContract getFavoriteAddressContract() {
			return new FavoriteAddressContract();
		}
		
		/**
		 * Added for hardware debrief
		 * MPS 2.1 Wave 4
		 * */
	
		/**
		 * @param request 
		 * @return RequestListContract 
		 */
		public static ActivityListContract getHardwareDebriefContract(PortletRequest request) {
			ActivityListContract contract = new ActivityListContract();
			Pagination pagination = PaginationUtil.getPainationFromRequest(request,
					LexmarkPPConstants.PATTERNS_SORT_ORDER_HARDWARE, LexmarkPPConstants.PATTERNS_FILTER_ORDER_HARDWARE);
			SearchContractUtil.copyPaginationToContract(pagination, contract);
			final PortletSession session = request.getPortletSession();
			
			/**
			 * Below map is for activity type
			 * the keys will be coming from the grid
			 * internally request types are going to be sent.
			 * */
			
			
			Map<String,List<String>> activityTypeMap=new LinkedHashMap<String,List<String>>();
			activityTypeMap.put(LexmarkPPConstants.INSTALLATION,LexmarkPPConstants.installType);
			activityTypeMap.put(LexmarkPPConstants.CHANGE, LexmarkPPConstants.changeType);
			activityTypeMap.put(LexmarkPPConstants.DECOMMISSION, LexmarkPPConstants.decommissionType);
			activityTypeMap.put(LexmarkPPConstants.HW_MOVE, LexmarkPPConstants.moveType);
			activityTypeMap.put(LexmarkPPConstants.INSTALLDEINSTALL, LexmarkPPConstants.installdeinstalltype);
			Map<String,Object> filterCriteria=contract.getFilterCriteria();
			
			if(filterCriteria.containsKey("Activity.activityType")){
				filterCriteria.put("Activity.activityType",activityTypeMap.get(filterCriteria.get("Activity.activityType")));
			}
			
			String queryType = request.getParameter("queryType");
			queryType = StringUtils.isEmpty(queryType) ? "All" : queryType;
			contract.setQueryType(queryType);
			
			contract.setServiceRequestType("Fleet Management");

			String status = request.getParameter("status");
			status = StringUtils.isEmpty(status) ? "All" : status;
			contract.setStatus(status);

			String startDateStr = request.getParameter("beginDate");
			String endDateStr = request.getParameter("endDate");
			float timezoneOffset = Float.valueOf(request.getParameter(LexmarkConstants.TIMEZONE_OFFSET));
			try {
				if (StringUtils.isNotEmpty(startDateStr)) {
					Date startDate = LexmarkConstants.DEFAULT_DATE_FORMAT.parse(startDateStr);
					TimezoneUtil.adjustDate(startDate, timezoneOffset);
					contract.getFilterCriteria().put("Activity.activityDate.startDate", startDate);
				}

				if (StringUtils.isNotEmpty(endDateStr)) {
					Date endDate = LexmarkConstants.DEFAULT_DATE_FORMAT.parse(endDateStr);
					TimezoneUtil.adjustDate(endDate, timezoneOffset + 24);
					contract.getFilterCriteria().put("Activity.activityDate.endDate", endDate);
				}
			} catch (ParseException e) {
				throw new IllegalArgumentException("Illegal date format,startDate[" + startDateStr + "];endDate["
						+ endDateStr + "]"+e.getMessage());
			}
			contract.setEmployeeId(PortalSessionUtil.getContactId(session));
			contract.setEmployeeFlag(LexmarkUserUtil.isTechnician(request) && PortalSessionUtil.getLexmarkEmployeeFlag(session));
			contract.setMdmId(PortalSessionUtil.getMdmId(session));
			contract.setMdmLevel(PortalSessionUtil.getMdmLevel(session));
			//LOGGER.debug("request.getAttribute(LexmarkPPConstants.ISMASSUPLOAD)"+request.getAttribute(LexmarkPPConstants.ISMASSUPLOAD));
			//Changes for massupload mps 2.1
			if(request.getParameter(LexmarkPPConstants.ISMASSUPLOAD)!=null){
				
				Map<?,?> accountSelectionDetails=(Map<?,?>)request.getPortletSession().getAttribute(LexmarkPPConstants.PARTNER_ACCOUNT_SELECTION_DETAILS, PortletSession.APPLICATION_SCOPE);
				LOGGER.debug("vendor account id is "+accountSelectionDetails.get(LexmarkPPConstants.VENDORACC_ID));
				contract.setMdmId((String)accountSelectionDetails.get(LexmarkPPConstants.VENDORACC_ID));
				contract.setMdmLevel("Siebel");
				contract.setMassUploadRequest(true);
			}
			//ends
			
			return contract;
		}
		
		
		/**
		 * @param request 
		 * @return ContactListContract 
		 */
		public static ContactListContract getContactListContractPopup(ResourceRequest request){
			final String[] accountContactColumnsPopup = new String[] { "contactId", "lastName", "firstName", "workPhone","alternatePhone", "emailAddress" };
			Pagination pagination = PaginationUtil.getPainationFromRequest(request, accountContactColumnsPopup, accountContactColumnsPopup);
			ContactListContract contract = new ContactListContract();
			SearchContractUtil.copyPaginationToContract(pagination, contract);
			contract.setLoadAllFlag(false);
			PortletSession session = request.getPortletSession();

//			contract.setMdmId(PortalSessionUtil.getMdmId(session));
			contract.setMdmId((String)session.getAttribute(LexmarkConstants.CUSTOMERACCOUNTID));
//			contract.setMdmLevel(PortalSessionUtil.getMdmLevel(session));
			contract.setMdmLevel("Siebel");
			contract.setLocale(request.getLocale());			
			contract.setContactId(PortalSessionUtil.getContactId(session));
			return contract;
		}
	// added for chl popup
		/**
		 * @param request 
		 * @return LocationReportingHierarchyContract 
		 */
		public static LocationReportingHierarchyContract getLocationReportingHierarchyContract(
				ResourceRequest request) {

			LocationReportingHierarchyContract contract = new LocationReportingHierarchyContract();
			PortletSession session = request.getPortletSession();
			contract.setMdmId(PortalSessionUtil.getMdmId(session));
			contract.setMdmLevel(PortalSessionUtil.getMdmLevel(session));
			contract.setChlNodeId(PortalSessionUtil.getChlNodeId(session));
			return contract;
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
		 * @param request 
		 * @return AddressListContract 
		 */
		public static AddressListContract getAddressListContract(ResourceRequest request){
			
			final String[] accountAddressPopupColumns = new String[] {"addressName","storeFrontName","addressLine1","addressLine2","officeNumber","city","state","province","county","district","region","postalCode","country"};
			
			
			//String isPopup=request.getParameter(ChangeMgmtConstant.ISPOPUP);
			
			Pagination pagination = PaginationUtil.getPainationFromRequest(request,accountAddressPopupColumns, accountAddressPopupColumns);
			AddressListContract contract = new AddressListContract();
			
			SearchContractUtil.copyPaginationToContract(pagination, contract);
			PortletSession session = request.getPortletSession();
			
			//Map<String,String> accDetails=(Map<String,String>)session.getAttribute("accountCurrentDetails",PortletSession.APPLICATION_SCOPE);
			
			/*
			 * THIS TO BE UNCOMMENTED LATER.
			 * contract.setMdmId(accDetails.get("accountId"));
			contract.setMdmLevel("Siebel");*/
			
//			contract.setMdmId(PortalSessionUtil.getMdmId(session));
//			contract.setMdmLevel(PortalSessionUtil.getMdmLevel(session));
			contract.setMdmId((String)session.getAttribute(LexmarkConstants.CUSTOMERACCOUNTID));
			contract.setMdmLevel("Siebel");
			contract.setContactId(PortalSessionUtil.getContactId(session));
			contract.setLocale(request.getLocale());
		
			/*contract.setMdmId("12864");
			contract.setMdmLevel("Legal");*/
			/*contract.setMdmId("2216");
			contract.setMdmLevel("Legal");
			contract.setContactId("1-6DV03JT");*/
			
			
			return contract;
		}
		

		/**
		 * @param request 
		 * @return AddressListContract 
		 */
		public static RequestAcceptContract getRequestAcceptContract(PortletRequest request){
			RequestAcceptContract requestContract=new RequestAcceptContract();
			requestContract.setActivityId(StringUtils.isNotBlank(request.getParameter("activityId"))?request.getParameter("activityId"):"");
			requestContract.setRequestNumber(StringUtils.isNotBlank(request.getParameter("srNumber"))?request.getParameter("srNumber"):"");
			requestContract.setStatus(StringUtils.isNotBlank(request.getParameter("status"))?request.getParameter("status"):"");
			requestContract.setComment(StringUtils.isNotBlank(request.getParameter("comments"))?request.getParameter("comments"):"");//Added for June release MPS 2.1 10913
			return requestContract;
			
		}
		

		
		/**
		 * @param request 
		 * @param portletRequest 
		 * @return ActivityDetailContract  
		 */
		public static ActivityDetailContract getHWDebriefDetailContract(HttpServletRequest request,PortletRequest portletRequest){
			ActivityDetailContract requestContract=new ActivityDetailContract();
			String srNumber=null;
			String activityID=null;
			boolean isOfflineDebrief=false;
			if(StringUtils.isNotBlank(request.getParameter(LexmarkPPConstants.SRNUMBER)) && StringUtils.isNotBlank(request.getParameter(LexmarkPPConstants.ACTIVITYID))){
				LOGGER.debug(" this is http request");
				LOGGER.debug(" sr number coming as "+request.getParameter(LexmarkPPConstants.SRNUMBER));
				LOGGER.debug(" activity id coming as "+request.getParameter(LexmarkPPConstants.ACTIVITYID));				
				srNumber=request.getParameter(LexmarkPPConstants.SRNUMBER);
				activityID=request.getParameter(LexmarkPPConstants.ACTIVITYID);
				isOfflineDebrief=StringUtils.isNotBlank(request.getParameter(LexmarkPPConstants.OFFLINEDEBRIEF))?true:false; 
			}else{
				LOGGER.debug(" this is portlet request");
				LOGGER.debug(" sr number coming as "+portletRequest.getParameter(LexmarkPPConstants.SRNUMBER));
				LOGGER.debug(" activity id coming as "+portletRequest.getParameter(LexmarkPPConstants.ACTIVITYID));	
				
				srNumber=portletRequest.getParameter(LexmarkPPConstants.SRNUMBER);
				activityID=portletRequest.getParameter(LexmarkPPConstants.ACTIVITYID);
			}
			requestContract.setPageName("Debrief");
			requestContract.setServiceRequestId(srNumber);
			requestContract.setActivityId(activityID);
			requestContract.setServicesUser(PortalSessionUtil.getServiceUser(portletRequest.getPortletSession()));
			requestContract.setDebriefFlag(isOfflineDebrief);
			return requestContract;
			
		}
		
		/**
		 * @param result 
		 * @return DeleteAttachmentContract 
		 */
		public static DeleteAttachmentContract getDeleteAttachmentContract(AttachmentResult result){
			DeleteAttachmentContract contract = new DeleteAttachmentContract();
			contract.setFile(result.getFile());
			contract.setFileStream(result.getFileStream());
			return contract;
		}
		
		
		/**
		 * @param orderContract 
		 * @return MassUploadTemplateContract 
		 */
		public static MassUploadTemplateContract getMassUploadTemplateContract(OrderListContract orderContract){
			MassUploadTemplateContract massUploadContract=new MassUploadTemplateContract();
			massUploadContract.setMdmId(orderContract.getMdmId());
			massUploadContract.setMdmLevel(orderContract.getMdmLevel());
			massUploadContract.setStatus(orderContract.getStatus());
			massUploadContract.setMassUploadRequest(true);
			for (Entry<String, Object> entry : orderContract.getFilterCriteria().entrySet()){
				massUploadContract.getFilterCriteria().put(entry.getKey(), entry.getValue());
			}
			for (Entry<String, Object> entry : orderContract.getSortCriteria().entrySet()){
				massUploadContract.getSortCriteria().put(entry.getKey(), entry.getValue());
			}
				return massUploadContract;
			
		}
		
		/**
		 * @param request 
		 * @return AddressListContract 
		 */
		public static AddressListContract getLBSAddressContract(PortletRequest request){
			AddressListContract contract=new AddressListContract();
			contract.setLbsFlag(true);
			//contract.setMdmId(PortalSessionUtil.getMdmId(request.getPortletSession()));
			//contract.setMdmLevel(PortalSessionUtil.getMdmLevel(request.getPortletSession()));
			PortletSession session = request.getPortletSession();
			contract.setMdmId((String)session.getAttribute(LexmarkConstants.CUSTOMERACCOUNTID));
			contract.setMdmLevel("Siebel");
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
		
}
