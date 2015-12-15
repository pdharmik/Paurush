/**
*
* Copyright 2012.  
* 
* Customer specific copyright notice     :Constants declared throughout the portal.
*
* File Name       : LexmarkPPConstants.java
*
* Description     :Project desc.
*
* Version         : 1.0
*
* Created Date    :2013
 
* Modification History:
*
* ---------------------------------------------------------------------
* Author 				Date				Version  		Comments
* ---------------------------------------------------------------------
*/

package com.lexmark.constants;

import java.util.ArrayList;
import java.util.List;

/**
 * Constant class for Partner Portal
 * @author Wipro 
 * @version 1.0 
 * */
public class LexmarkPPConstants {
	// Update with each Deploy, will presented by UserInterceptor when logging
	// is in debug setting

	/** Portlet Build Version */
	public static final String PORTLET_BUILD_VERSION = "MAY 10  2014 10 PM V 1.83 (MPS 2.1 and CI 14.4 - Floor Support defect fix Release)";

	/**
	 * public static final String OPTION_TYPE_ENTITLEMENT_SERVICE_DETAILS =
	 * "Entitlement Service Details";
	 **/
	public static final String ERROR_MESSAGE_BUNDLE = "com.lexmark.resources.messages";
	/** Message bundle name*/
	public static final String MESSAGE_BUNDLE_NAME = "com.lexmark.resources.messages";
	/** Message bundle key*/
	public static final String ERROR_CODE_SIEBEL_VALUE_IS_NULL = "srSave.siebelValueNull";
	/** Message bundle error code key*/
	public static final String ERROR_CODE_CONFIRM_LOCATION_DELETE = "serviceRequestLocaleListPage.text.confirmDelete";
	/** asset list type */
	public static final String ASSET_LIST_TYPE = "PrintSelection";
	/** root node id for tree*/
	public static final String ROOT_NODE_ID = "0";
	/** variable for tree */
	public static final String TREE_LEVEL_1 = "0_0";
	/** grid params in session*/
	public static final String SESSION_KEY_QUERY_PARAMS_PREFIX = "grid_query_params_";
	/** Session key for contract*/
	public static final String SESSION_KEY_REQUESTS_DOWNLOAD_CONTRACT = "request_downLoad_contract";
	
	/** grid id for request list*/
    public static final String GRIDORDER_REQUESTLIST = "gridOrderRequestList";
    /** request list form name */
    public static final String REQUESTLISTFORM = "requestListForm";
    //For Page redirections
    /** variable name for jsp redirection */
    public static final String ORDERREQUESTLISTVIEW = "source/orderRequestsListView";
    /** variable name for jsp redirection */
    public static final String ORDERREQUESTLISTXML = "source/orderRequestListXML";
    /** variable name for jsp redirection */
    public static final String ORDERDETAILVIEW = "source/orderDetailView";
    /** variable name for jsp redirection */
    public static final String ORDERDETAILPRINTVIEW = "source/orderDetailPrintView";
    /** variable name for jsp redirection */
    public static final String ORDERLISTPRINTVIEW = "source/orderListPrintView";

    //Exceptions
    /** variable name for Exception key */
    public static final String GENERAL_ERRORTITLE = "exception.generalError.title";
    /** variable name for Exception key */
    public static final String RETRIEVELISTEXCEPTION = "exception.siebel.retrieveListException";
    /** variable name for Exception key */
    public static final String EXCEPTIONOCCURED = "exceptionOccured";
    /** variable name for Exception key */
    public static final String DOWNLOADEXCEPTIONOCCURED = "downloadExceptionOccured";
    /** variable name for Exception key */

    //Attributes and Parameters
    /** variable name Direct partner */
    public static final String DIRECTPARTNER = "isDirectPartner";
    /** variable name technician */
    public static final String TECHNICIAN = "isTechnician";
    /** variable name startpos */
    public static final String STARTPOS = "startPos";
    /** variable name total count */
    public static final String TOTALCOUNT = "totalcount";
    /** variable name order list */
    public static final String ORDERLIST = "orderList";
    /** variable name true attribute */
    public static final String TRUE_ATTR = "true";
    /** variable name vendor account id */
    public static final String VENDORACC_ID = "vendorAccountId";
    /** variable name pending shipment flag */
    public static final String SHOW_PENDINGHIPMENTPARTSFLAG = "showPendingShipmentPartsFlag";
    /** variable name processed parts flag */
    public static final String SHOW_PROCESSEDPARTSFLAG = "showProcessedPartsFlag";
    /** variable name order detail form */
    public static final String ORDERDETAILFORM = "orderDetailForm";
    /** variable name */
    public static final String FULFILLMENTSTATUS = "fulfillmentStatus";
    /** variable name show partner flat */
    public static final String SHOWPROCESSEDUPDATEFLAG = "showProcessedUpdateFlag";
    /** variable name return url */
    public static final String RETURN_URL = "returnURL";
    /** variable name downoad type */
    public static final String DOWNLOADTYPE = "downloadType";
    /** variable name order */
    public static final String ORDER = "order";

    //content-type
    /** variable name xml content */
    public static final String XMLCONTENT = "text/xml";

	/*********FOR ORDERUPDATECONTROLLER***********/
    //Attributes and Parameters
    /** variable name error list */
    public static final String ERRORLIST = "errorList";
    /** variable name accept order */
    public static final String ACCEPTORDER = "acceptOrder";
    /** variable name accept */
    public static final String ACCEPT = "accept";
    /** variable name  ACCEPTFLAG*/
    public static final String ACCEPTFLAG = "acceptFlag";
    /** variable name  CARRIERDROPDOWN*/
    public static final String CARRIERDROPDOWN = "carrierDropdown";
    /** variable name  REQUESTNO*/
    public static final String REQUESTNO = "requestNumber";
    /** variable name  ORDERNO*/
    public static final String ORDERNO = "orderNumber";
    /** variable name  ACTION*/
    public static final String ACTION = "action";
    /** variable name SHOWUPDATEORDERREQUESTPAGE */
    public static final String SHOWUPDATEORDERREQUESTPAGE = "showUpdateOrderRequestPage";
    /** variable name  ORDERID*/
    public static final String ORDERID = "orderID";
    /** variable name  ACCEPTMSG */
    public static final String ACCEPTMSG = "acceptMessage";
    /** variable name  FAILURE */
    public static final String FAILURE = "failure";
    /** variable name  UPDATEMSG */
    public static final String UPDATEMSG = "updateMessage";
    /** variable name  RETRIEVEORDERREQUEST */
    public static final String RETRIEVEORDERREQUEST = "retrieveOrderRequest";
    /** variable name  SUCCESS */
    public static final String SUCCESS = "success";
    /** variable name  ERRORMSG */
    public static final String ERRORMSG = "errorMessage";
    /** variable name  SHOWPENDINGSHIPMENTPARTSFLAG */
    public static final String SHOWPENDINGSHIPMENTPARTSFLAG = "showPendingShipmentPartsFlag";
    /** variable name  UPDATE*/
    public static final String UPDATE = "update";
    /** variable name  NOTEDATE */
    public static final String NOTEDATE = "noteDate";
    /** variable name  NOTEAUTHOR */
    public static final String NOTEAUTHOR = "noteAuthor";
    /** variable name  NOTEDETAIL */
    public static final String NOTEDETAIL = "noteDetail";
    /** variable name  ROWID */
    public static final String ROWID = "rowId";
    /** variable name  HANDLEGRIDFLAG */
    public static final String HANDLEGRIDFLAG = "handleGridFlag";
    /** variable name  ATTACHMENTLIST */
    public static final String ATTACHMENTLIST = "attachmentList";
    /** variable name  ATTACHMENTFORMDISPLAY */
    public static final String ATTACHMENTFORMDISPLAY = "attachmentFormDisplay";
    /** variable name  ATTACH_DCMTTSTREQUPDATE */
    public static final String ATTACH_DCMTTSTREQUPDATE = "attachDocumentTestRequestUpdate";
    /** variable name  REMOVEDOC_RENDEREQUPDATE */
    public static final String REMOVEDOC_RENDEREQUPDATE = "removeDocumentRenderRequestUpdate";


    //For Page redirections
    /** variable name  ORDERUPDATEVIEW */
    public static final String ORDERUPDATEVIEW = "source/orderUpdateView";
    /** variable name  UPDATENOTE */
    public static final String UPDATENOTE = "source/updateNote";
    /** variable name  REQDETAILUPDATE */
    public static final String REQDETAILUPDATE = "serviceRequest/requestsDetailUpdate";
    
    //For Partner Payment
    /** variable name  APINVOICEFORM */
    public static final String APINVOICEFORM = "invoiceAPForm";
    /** variable name  APINVOICEVIEW */
    public static final String APINVOICEVIEW = "partnerPayments/APInvoiceHistory";
    /** variable name  ARINVOICEVIEW*/
    public static final String ARINVOICEVIEW = "partnerPayments/ARInvoiceHistory";
    /** variable name  APINVOICECURRENCY */
    public static final String APINVOICECURRENCY = "invoiceCurrency";
    /** variable name  SRVIEW */
    public static final String SRVIEW = "partnerPayments/ARSRHistory";
    /** variable name  SRCURRENCY */
    public static final String SRCURRENCY = "srCurrency";
    /** variable name  SRFORM */
    public static final String SRFORM = "srForm";
    /** variable name  SRDETAILSAR */
    public static final String SRDETAILSAR = "partnerPayments/details_payments_AR";
    /** variable name  SRDETAILSAP*/
    public static final String SRDETAILSAP = "partnerPayments/details_payments_AP";
    /** variable name  AP*/
    public static final String AP = "AP";
    /** variable name  AR*/
    public static final String AR = "AR";
    
    // added for request History : Dwijen
    /** variable name  REQ_HIS_AP*/
    public static final String REQ_HIS_AP = "reqHistoryAP";
    /** variable name  REQ_HIS_AR*/
    public static final String REQ_HIS_AR = "reqHistoryAR";

    //Exceptions
    /** variable name  FILESIZEEMPTY*/
    public static final String FILESIZEEMPTY = "File Size is empty!!!!!!!";
    /** variable name  COULDNOTWRITE*/
    public static final String COULDNOTWRITE = "Could not write file to drive!!!!!!!";

    //For MPS Phase 2 Mass Upload
    /** variable name  gridSavingParams*/
    public static  String[] gridSavingParams={"colsOrder","colsWidth","colsSorting","colsHidden"};
    /** variable name  SERVICE_ORDER_RESOURCE_URL_MAPPING*/
    public static final String SERVICE_ORDER_RESOURCE_URL_MAPPING="retrieveOrderRequestList";
    /** variable name  gridConfigurationValues*/
    public static  String gridConfigurationValues[]={"_Header","_Filter","_ColAlign","_ColWidth","_ColType","_ColSorting","_ColLOCKVisibility","_DefaultColSorting","_DefaultHiddenColumns"};
    /** variable name  JSON_STATUS*/
    public static final String JSON_STATUS="status";
    /** variable name  JSON_STATUS_AVAILABLE*/
    public static final String JSON_STATUS_AVAILABLE="available";
    /** variable name  JSON_STATUS_FAILURE*/
    public static final String JSON_STATUS_FAILURE="failure";
    /** variable name  JSON_RESOURCE_URL*/
    public static final String JSON_RESOURCE_URL="ajaxURL";
    /** variable name  JSON_COMBO_FILTER*/
    public static final String JSON_COMBO_FILTER="comboFilter";
    /** variable name  CONTRACT*/
    public static final String CONTRACT="massUpload_contract_";
    
    /** variable name  ACCOUNTSELECTIONLISTXML*/
    public static final String ACCOUNTSELECTIONLISTXML="accountSelection/accountSelectionXML";
    /** variable name  PARTNER_ACCOUNT_SELECTION_DETAILS*/
    public static final String PARTNER_ACCOUNT_SELECTION_DETAILS="accountCurrentDetails";
    /** variable name  VENDORACCID_AVAIL*/
    public static final String VENDORACCID_AVAIL="vendorAccIdAvaialable";
    /** variable name  ACCOUNT_SELECTION_DONE*/
    public static final String ACCOUNT_SELECTION_DONE="accountSelectionDone";
    /** variable name  ACCOUNT_COUNT*/
    public static final String ACCOUNT_COUNT="accCount";
    //Added for Massupload Web service call
    /** variable name  AREA*/
    public static final String AREA="area";
    /** variable name  SUBAREA*/
    public static final String SUBAREA="subArea";
    /** variable name  REQUESTERTYPE*/
    public static final String REQUESTERTYPE="requesterType";
    /** variable name  SERVICEREQUESTTYPE*/
    public static final String SERVICEREQUESTTYPE="serviceRequestType";
    /** variable name  service request number*/
    public static final String SERVICEREQUESTNUMBER="serviceRequestNumber";
    //Added for Mass upload
    /** variable name  constant -1*/
    public static final String MINUES_ONE = "-1";
	
	/** variable name for font path*/
	public  static final String FONT_PATH = LexmarkPPConstants.class.getResource("/").getPath() + "com/lexmark/resources/font/ARIALUNI.TTF";
	 /** variable name  SESSION_FILE_MAP*/
	public static final String SESSION_FILE_MAP = "fileMapInSession";
	/** variable name  SESSION_FILE_MAP_ForMassUpload */
	public static final String SESSION_FILE_MAP_ForMassUpload = "fileMapInSession_Massupload";
	/** variable name  ATTACHMENT_REQUEST_TYPE */
    public static final String ATTACHMENT_REQUEST_TYPE = "Service Request";
    /** variable name  LIST_EXCEPTION */
    public static final String LIST_EXCEPTION = "exceptionList";
    
    //Added for MassUpload
    /** variable name  ISMASSUPLOAD*/
    public static final String ISMASSUPLOAD = "isMassUpload";
	/** variable names for Service order*/
    
  //Added for MPS 2.1 wave 4
    /** variable name  service order grid id*/
    public static final String SERVICEORDERGRIDID="gridIDOrderRequestList";
    /** variable name  hardware order grid id*/
    public static final String HARDWAREORDERGRIDID="gridIDhardwareRequestList";
    /** variable name  hardware order*/
    public static final String HARDWAREORDER="hardwareOrder";
    /** variable name  hardware order offline*/
    public static final String HARDWAREORDER_OFFLINEDEBRIEFS="hardwareOrderOfflineDebriefs";
    /** variable name  hardware order offline*/
    public static final String MASSUPLOAD_EXPORT="massUpload";
    
    
    /** variable name  new line character*/
	public static final String NEWLINE="\n";
    
    
	/** PATTERNS_REQUEST_SORT_SERVICE_ORDER*/
	public  static String[] PATTERNS_REQUEST_SORT_SERVICE_ORDER = { "createdDate", "createdDate",
		"requestNumber","orderNumber","asset.SerialNumber", 
		"asset.ProductLine","asset.machineTypeModel","status", "customerAccount",
		//Changes for MPS 2.1 removed resonse metric
		"",
		//Ends Changes for MPS 2.1 removed resonse metric
		"customerResponseDate",
		"serviceProviderReferenceNumber", "customerAddress.StreetAddress",
		//Added for CI Changes House number , county , district
		"customerAddress.HouseNumber",
		"customerAddress.City", "customerAddress.State",
		"customerAddress.Province","customerAddress.County",
		"customerAddress.District","customerAddress.PostalCode",
		//Ends for CI Changes House number , county , district
		"customerContact.FirstName", "customerContact.LastName" };
	/** variable names for Service order filter*/
	public  static String[] PATTERNS_REQUEST_FILTER_SERVICE_ORDER = { "requestNumber", 
		"orderNumber","asset.SerialNumber",
		"asset.productLine","asset.machineTypeModel", "status",
		"customerAccount", 
		"responseMetric",
		"serviceProviderReferenceNumber",
		//Added for CI Changes House number , county , district
		"customerAddress.HouseNumber",
		"customerAddress.City","customerAddress.State",
		"customerAddress.Province","customerAddress.County",
		"customerAddress.District","customerAddress.PostalCode", "customerContact.FirstName",
		//Ends for CI Changes House number , county , district
		"customerContact.LastName" };
	/** variable names for claims download */
//  Down Load Claims and Downloads
	public  static  String[] PATTERNS_DONWLOAD_CLAIMS_EXPORT =  { "srId",
		"srNum",
		"activityId",
		"addlPaymentDesc1",
		"addlPaymentDesc2",
		"addlPaymentQty1",
		"addlPaymentQty2",
		"addlPaymentType1",
		"addlPaymentType2",
		"addlPaymentUnitPrice1",
		"addlPaymentUnitPrice2",
		"assetMTM",
		"assetProduct",
		"astSerialNumber",
		"newCustomerAccount",
		"newCustomerAddress",
		"newTechFirstName",
		"newTechLastName",
		"partnerAddress1",
		"partnerAddress2",
		"partnerAddress3",
		"partnerCity",
		"partnerCountry",
		"partnerName",
		"partnerOrg",
		"partnerPostal",
		"partnerProvince",
		"partnerRefNumber",
		"partnerSite",
		"partnerState",
		"prContactEmail",
		"prContactFN",
		"prContactLN",
		"prContactWorkPhone",
		"printerCondition",
		"problemCode",
		"problemDetails",
		"repairCompleteFlag",
		"repairDesc",
		"reqContactEmail",
		"reqContactFN",
		"reqContactLN",
		"reqContactWorkPhone",
		"requestReviewFlag",
		"resolutionCode",
		"reviewComments",
		"serviceAddress1",
		"serviceAddress2",
		"serviceAddress3",
		"serviceCity",
		"serviceCountry",
		"servicePostal",
		"serviceProvince",
		"serviceState",
		"srvcEndDate:showTime",
		"srvcStartDate:showTime",
		"techLoginName"
		};
	/** variable names for Claim export */
	public static  String[] PATTERNS_DONWLOAD_CLAIMS_EXPORT_1 =  {
		"recPartFlag",
		"recPartName",
		"recPartsErrCd1",
		"recPartsErrCd2",
		"recPartsQty",
		"recPartsRetCarrier",
		"recPartsRetTrackNum",
		"recPartsStatus"};
	/** variable names for Claim export */
	public static  String[] PATTERNS_DONWLOAD_REQUEST_EXPORT_1 = { "activityId" ,
		"srNum",
		"actionNarrative" ,
		"actionSubStatus" , 
		"actualEnd:showTime" ,
		"actualFailureCode" ,
		"actualStart:showTime" , 
		"addlPaymentDesc1" ,
		"addlPaymentDesc2" , 
		"addlPaymentQty1" , 
		"addlPaymentQty2" , 
		"addlPaymentType1" ,
		"addlPaymentType2" , 
		"addlPaymentUnitPrice1" , 
		"addlPaymentUnitPrice2" ,
		"addlServiceReq" ,
		"comments" , 
		"customerRequestResponse:showTime" , 
		"deInstalledAssetTag" , 
		"deInstalledPageCount" ,
		"estTimeArrival:showTime" ,
		"installedAssetTag" , 
		"installedDeviceCondition" ,
		"installedIPAddress" , 
		"installedMacAddress" ,
		"installedPageCount" , 
		"networkConnected" , 
		"newTechFirstName" ,
		"newTechLastName" ,
		"nonLexmarkSuppliesUsed" , 
		"repairCompleteFlag" , 
		"resolutionCode" ,
		"sPReferenceNum" ,
		"statusAsOf:showTime" , 
		"supplyManufacturer" ,
		"technician" ,
		"travelDistance" ,
		"travelDistanceUM" ,
		"travelDurationMin"};
	/** variable names for request download */
	public static  String[] PATTERNS_DONWLOAD_REQUEST_EXPORT_2 = {"recPartsDisp" , "recPartsErrCd1" , "recPartsErrCd2" , "recPartsName" , "recPartsQty" , "recPartsRetCarrier" , "recPartsRetTrackNum" };
	/** variable names for AP column pattern */
	//Added for CI-7 Defect #7854 :formatDate
	public static  String[] APCOLUMNSPATTERN={	"invoiceNumber","invoiceDate:formatDate",	"dueDate:formatDate",	"chequeNumber","paidDate:formatDate","amount"	};
	/** variable names for AR column pattern */
	public static  String[] ARCOLUMNSPATTERN={	"invoiceNumber","invoiceDate:formatDate",	"amount" };
	/** variable names for AP History list */
	public static  String[] APRequestHistoryList={	"requestNumber","amount",	"currencyType" };
	/*********FOR ORDERREQUESTCONTROLLER***********/
	
	/** variable names for request grid*/
	public static  String[] PATTERNS_REQUEST_EXPORT = { "activityDate:showTime",
		"serviceRequest.serviceRequestType", "activityId", "serviceRequest.serviceRequestNumber",
		"serviceRequest.asset.serialNumber", "serviceRequest.asset.productLine", "activityStatus",
		"customerAccount.accountName", "responseMetric", "parentSRNum", "serviceProviderReferenceNumber",
		"serviceRequest.asset.modelNumber", "serviceRequest.asset.productTLI", "activitySubStatus", "activityType",
		"partnerAccount.accountName", "technician.firstName", "technician.lastName",
		"customerRequestedResponseDate:showTime", "serviceAddress.addressLine1", "serviceAddress.officeNumber", "serviceAddress.city",
		"serviceAddress.state", "serviceAddress.province", "serviceAddress.county", "serviceAddress.district", "serviceAddress.postalCode","serviceAddress.country",
		"serviceRequest.primaryContact.firstName", "serviceRequest.primaryContact.lastName", "agencyContactFirstName", "agencyContactLastName",
		"agencyContacteMail", "localizedCreateChildSR", "serviceProviderETA", "committedDate" };
	
	//Added By MPS Offshore team for Service Order List PDF and csv download
	/** variable names for request order grid*/
	public static  String[] PATTERNS_ORDER_EXPORT = { "createdDate:showTime",
		//Changes for CI PDF/Excel download
		"requestNumber", "orderNumber","asset.serialNumber","asset.productLine", "asset.machineTypeModel", "status",
		"customerAccount", "responseMetric", "customerRequestedResponseDate:showTime","serviceProviderReferenceNumber",
	
		"customerAddress.addressLine1", "customerAddress.officeNumber","customerAddress.city",
		"customerAddress.state", "customerAddress.province", "customerAddress.county","customerAddress.district","customerAddress.postalCode",
		//ENDS Changes for CI PDF/Excel download
		"customerContact.firstName", "customerContact.lastName" };
	
	//End Add
	/** variable names for request grid sort */
	public  static String[] PATTERNS_REQUEST_SORT = { "Activity.activityDate", "Activity.activityDate",
			"Activity.serviceRequest.serviceRequestType", "Activity.activityId", "Activity.serviceRequest.serviceRequestNumber",
			"Activity.serviceRequest.asset.serialNumber", "Activity.serviceRequest.asset.productLine",
			"Activity.activityStatus", "Activity.customerAccount.accountName", "Activity.responseMetric", "Activity.parentSRNum",
			"Activity.serviceProviderReferenceNumber", "Activity.serviceRequest.asset.modelNumber",
			"Activity.serviceRequest.asset.productTLI", "Activity.activitySubStatus", "Activity.activityType",
			"Activity.partnerAccount.accountName", "Activity.technician.firstName", "Activity.technician.lastName",
			"Activity.customerRequestedResponseDate", "Activity.serviceRequest.serviceAddress.addressLine1","Activity.serviceRequest.serviceAddress.officeNumber",
			"Activity.serviceRequest.serviceAddress.city", "Activity.serviceRequest.serviceAddress.state",
			"Activity.serviceRequest.serviceAddress.province", "Activity.serviceRequest.serviceAddress.county", "Activity.serviceRequest.serviceAddress.district",
			"Activity.serviceRequest.serviceAddress.postalCode","Activity.serviceRequest.serviceAddress.country",
			"Activity.serviceRequest.primaryContact.firstName", "Activity.serviceRequest.primaryContact.lastName",
			"Activity.agencyContactFirstName","Activity.agencyContactLastName","Activity.agencyContacteMail", "Activity.createChildSR", "Activity.serviceProviderETA", "Activity.committedDate"};
	// service provider ETA and Committed Date added for CI 15.1
	/** variable names for request grid filter*/
	public  static String[] PATTERNS_REQUEST_FILTER = { "Activity.serviceRequest.serviceRequestType", "Activity.activityId",
			"Activity.serviceRequest.serviceRequestNumber", "Activity.serviceRequest.asset.serialNumber",
			"Activity.serviceRequest.asset.productLine", "Activity.activityStatus",
			"Activity.customerAccount.accountName", "Activity.responseMetric", "Activity.parentSRNum",
			"Activity.serviceProviderReferenceNumber", "Activity.serviceRequest.asset.modelNumber",
			"Activity.serviceRequest.asset.productTLI", "Activity.activitySubStatus", "Activity.activityType",
			"Activity.partnerAccount.accountName", "Activity.technician.firstName", "Activity.technician.lastName",
			"Activity.serviceRequest.serviceAddress.addressLine1", "Activity.serviceRequest.serviceAddress.officeNumber","Activity.serviceRequest.serviceAddress.city","Activity.serviceRequest.serviceAddress.state", 
			"Activity.serviceRequest.serviceAddress.province","Activity.serviceRequest.serviceAddress.county",
			"Activity.serviceRequest.serviceAddress.district","Activity.serviceRequest.serviceAddress.postalCode","Activity.serviceRequest.serviceAddress.country", "Activity.serviceRequest.primaryContact.firstName",
			"Activity.serviceRequest.primaryContact.lastName","Activity.agencyContactFirstName","Activity.agencyContactLastName","Activity.agencyContacteMail", "Activity.createChildSR"};
	// service provider ETA and Committed Date added for CI 15.1
	/** variable names for payment request grid*/
	public  static String[] PAYMENT_REQUEST_EXPORT = { "activityDate:showTime",
			"serviceRequest.serviceRequestNumber", "serviceProviderReferenceNumber", "paymentServiceType",
			"serviceRequest.serviceRequestStatus", "payment.paymentStatus.value", "payment.paymentNumber",
			"eligibleToPay", "payEligiblityOverride", "laborPayment", "partsPayment", "additionalPayments",
			"partnerFee", "totalPayment", "partnerAccount.accountName", "partnerAgreementName",
			"customerAccount.accountName", "serviceRequest.asset.serialNumber", "serviceRequest.asset.productTLI",
			"serviceRequest.asset.modelNumber", "serviceRequest.asset.productLine" };
	
	/** variable names for payment request grid sort*/
	public  static String[] PAYMENT_REQUEST_SORT = { "Activity.activityDate",
			"Activity.serviceRequest.serviceRequestNumber", "Activity.serviceProviderReferenceNumber",
			"Activity.paymentServiceType", "Activity.serviceRequest.serviceRequestStatus",
			"Activity.payment.paymentStatus", "Activity.payment.paymentNumber", "Activity.eligibleToPay",
			"Activity.payEligiblityOverride", "Activity.laborPayment", "Activity.partsPayment",
			"Activity.additionalPayments", "Activity.partnerFee", "Activity.suppliesFulfillmentFee", "Activity.totalPayment",
			"Activity.partnerAccount.accountName", "Activity.partnerAgreementName",
			"Activity.customerAccount.accountName", "Activity.serviceRequest.asset.serialNumber",
			"Activity.serviceRequest.asset.productTLI", "Activity.serviceRequest.asset.modelNumber",
			"Activity.serviceRequest.asset.productLine" };
	/** variable names for payment request filter grid*/
	public  static String[] PAYMENT_REQUEST_FILTER = { "Activity.serviceRequest.serviceRequestNumber",
			"Activity.serviceProviderReferenceNumber", "Activity.paymentServiceType",
			"Activity.serviceRequest.serviceRequestStatus", "Activity.payment.paymentStatus",
			"Activity.payment.paymentNumber", "Activity.eligibleToPay", "Activity.payEligiblityOverride",
			"Activity.partnerFee","Activity.suppliesFulfillmentFee", "Activity.partnerAccount.accountName", "Activity.partnerAgreementName",
			"Activity.customerAccount.accountName", "Activity.serviceRequest.asset.serialNumber",
			"Activity.serviceRequest.asset.productTLI", "Activity.serviceRequest.asset.modelNumber",
			"Activity.serviceRequest.asset.productLine" };
	/** variable names for payment details filter*/
	public  static String[] PAYMENT_DETAILS_FILTER = { "Activity.serviceRequest.serviceRequestNumber",
			"Activity.serviceProviderReferenceNumber", "Activity.paymentServiceType",
			"Activity.customerAccount.accountName", "Activity.serviceRequest.asset.serialNumber",
			"Activity.serviceRequest.asset.productTLI", "Activity.serviceRequest.asset.modelNumber",
			"Activity.serviceRequest.asset.productLine" };
	/** variable names for payment details sort*/
	public static String[] PAYMENT_DETAILS_SORT = { "Activity.activityDate",
			"Activity.serviceRequest.serviceRequestNumber", "Activity.serviceProviderReferenceNumber",
			"Activity.paymentServiceType", "Activity.laborPayment", "Activity.partsPayment",
			"Activity.additionalPayments", "Activity.partnerFee", "Activity.totalPayment",
			"Activity.customerAccount.accountName", "Activity.serviceRequest.asset.serialNumber",
			"Activity.serviceRequest.asset.productTLI", "Activity.serviceRequest.asset.modelNumber",
			"Activity.serviceRequest.asset.productLine" };
	/** variable names for payment export*/
	public static String[] PATTERNS_PAYMENT_PAYMENT_EXPORT = { "dateCreated", "paymentNumber",
			"partnerAgreement", "checkNumber", "paymentTotal", "partnerAccount.accountName", "payableToName",
			"partnerAccount.address.addressLine1", "partnerAccount.address.addressLine2",
			"partnerAccount.address.addressLine3", "partnerAccount.address.city", "partnerAccount.address.state",
			"partnerAccount.address.province", "partnerAccount.address.postalCode" };
	/** variable names for payment  filter*/
	public  static String[] PATTERNS_PAYMENT_PAYMENT_FILTER = { "Payment.paymentNumber",
			"Payment.partnerAgreement", "Payment.checkNumber", "Payment.paymentTotal",
			"Payment.partnerAccount.accountName", "Payment.payableToName",
			"Payment.partnerAccount.address.addressLine1", "Payment.partnerAccount.address.addressLine2",
			"Payment.partnerAccount.address.addressLine3", "Payment.partnerAccount.address.city",
			"Payment.partnerAccount.address.state", "Payment.partnerAccount.address.province",
			"Payment.partnerAccount.address.postalCode" };
	/** variable names for payment sort*/
	public  static String[] PATTERNS_PAYMENT_PAYMENT_SORT = { "Payment.dateCreated", "Payment.paymentNumber",
			"Payment.partnerAgreement", "Payment.checkNumber", "Payment.paymentTotal",
			"Payment.partnerAccount.accountName", "Payment.payableToName",
			"Payment.partnerAccount.address.addressLine1", "Payment.partnerAccount.address.addressLine2",
			"Payment.partnerAccount.address.addressLine3", "Payment.partnerAccount.address.city",
			"Payment.partnerAccount.address.state", "Payment.partnerAccount.address.province",
			"Payment.partnerAccount.address.postalCode" };
	
	
	
	
    
    
    //For Upload History Sort columns
    /** variable name  PATTERNS_SORT_UPLOAD_HISTORY */
    public static  String[] PATTERNS_SORT_UPLOAD_HISTORY = { "requestNumber","size","type","status","submittedOn","completedOn","fileName"};
    /** variable name  PATTERNS_FILTER_UPLOAD_HISTORY */
    public static  String[] PATTERNS_FILTER_UPLOAD_HISTORY = { "requestNumber","type","status","fileName"};
   
    /** variable name  PATTERNS_MASSUPLOAD_SERVICE_ORDER_EXPORT */
    public static  String[] PATTERNS_MASSUPLOAD_SERVICE_ORDER_EXPORT = {
    	"orderNumber",
    	"lineNumber",
    	"partNumber",
    	"partDescription",
    	"quantitiyRequested",
    	"remainingQuantity",
    	"backOrderQuantity",
    	"orderStatus",
    	"serialNumber",    	
    	"partnerOrderReferenceNumber",
    	/*"status",
    	"serviceProviderReferenceNumber",*/
    	"shipments:list"//Changes for MPS 2.1 defect 12265
     };
    /** variable name  PATTERNS_MASSUPLOAD_SERVICE_ORDER_pendingShipments*/
    public static  String[] PATTERNS_MASSUPLOAD_SERVICE_ORDER_pendingShipments={
    	"shippedQuantity",
    	"shippedDate",
    	"",//backorderdate
    	"deliveryDate:ignore",
    	"trackingNumber",
    	"carrier"
    	//"shipmentStatus"    	
   
    	};
    //Added for Account Selection
   
    /** variable name  hardware order sort*/
    public  static String[] PATTERNS_SORT_ORDER_HARDWARE = {
    	"",
      	"Activity.serviceRequest.serviceRequestNumber", 
       	"Activity.activityDate",
       	"Activity.serviceRequest.asset.serialNumber",
    	"Activity.activityType",
    	"Activity.debriefStatus",
    	"Activity.serviceRequest.asset.activitynumber",
    	"Activity.serviceRequest.asset.productModelNumber",
		"Activity.serviceRequest.asset.productLine",
		"Activity.serviceRequest.asset.productTLI",
		"Activity.activityStatus", 
		"Activity.activitySubStatus",
		"Activity.partnerAccount.accountName",
		"Activity.customerAccount.accountName",
		//Changes for MPS 2.1 removed resonse metric
		"",
		//Ends Changes for MPS 2.1 removed resonse metric
		"Activity.serviceRequest.projectName",
		"Activity.serviceRequest.actualStartDate",
		"Activity.serviceRequest.asset.installDate",
		
		"Activity.technician.firstName",
		"Activity.serviceRequest.storeFrontName",
		"Activity.serviceRequest.serviceAddress.addressLine1",
		"Activity.serviceRequest.serviceAddress.officeNumber",
		"Activity.serviceRequest.serviceAddress.county",
		"Activity.serviceRequest.serviceAddress.district",
		"Activity.serviceRequest.serviceAddress.city",
		"Activity.serviceRequest.serviceAddress.state",
		"Activity.serviceRequest.serviceAddress.province",
		"Activity.serviceRequest.serviceAddress.postalCode",
		"Activity.serviceRequest.serviceAddress.country",
		"Activity.serviceRequest.primaryContact.firstName",
		"Activity.serviceRequest.primaryContact.lastName",
		/*added for CR 12567*/
		"Activity.expectedCompletionDate",
		"Activity.dispatchDate"
		};
    
    /** variable name  Request hardware order debreif sort*/
    public  static String[] PATTERNS_SORT_ORDER_HARDWARE_DEBRIEF = {"","requestNumber", "requestNumber",
    	"createdDate","asset.SerialNumber",
    	"asset.activityNumber","asset.machineTypeModel",
		"asset.ProductLine","asset.ProductTLI",
		"status", "statusDetail",
		"partnerAccount","customerAccount",
		//Changes for MPS 2.1 removed resonse metric
		"",
		//Ends Changes for MPS 2.1 removed resonse metric
		"customerResponseDate","customerAddress.StreetAddress",
		"customerAddress.HouseNumber","customerAddress.County", 
		"customerAddress.District","customerAddress.PostalCode",
		"customerAddress.Country","customerContact.FirstName",
		"customerContact.LastName","expectedCompletionDate","dispatchDate" };
    
    
    /** variable name  filter order hardware*/
    public  static String[] PATTERNS_FILTER_ORDER_HARDWARE = {  
    	"Activity.serviceRequest.serviceRequestNumber",
    	"Activity.serviceRequest.asset.serialNumber",
    	"Activity.activityType",
    	"Activity.debriefStatus",
    	"Activity.serviceRequest.asset.activitynumber",
    	"Activity.serviceRequest.asset.productModelNumber",
		"Activity.serviceRequest.asset.productLine",
		"Activity.serviceRequest.asset.productTLI",
    	"Activity.activityStatus",
    	"Activity.activitySubStatus",
    	
    	"Activity.partnerAccount.accountName",
    	"Activity.customerAccount.accountName",
    	"Activity.responseMetric",
    		
		"Activity.serviceRequest.projectName",
		"Activity.technician.firstName",	
		"Activity.serviceRequest.storeFrontName",
		"Activity.serviceRequest.serviceAddress.addressLine1",
		"Activity.serviceRequest.serviceAddress.officeNumber",
		"Activity.serviceRequest.serviceAddress.county",
		"Activity.serviceRequest.serviceAddress.district",
		"Activity.serviceRequest.serviceAddress.city",
		"Activity.serviceRequest.serviceAddress.state",
		"Activity.serviceRequest.serviceAddress.province",
		"Activity.serviceRequest.serviceAddress.postalCode",
		"Activity.serviceRequest.serviceAddress.country",
		"Activity.serviceRequest.primaryContact.firstName",
		"Activity.serviceRequest.primaryContact.lastName" };
    
    /** variable name  filter Request order hardware Debreif*/
    public  static String[] PATTERNS_FILTER_ORDER_HARDWARE_DEBRIEF = { "requestNumber", 
		"asset.SerialNumber","asset.activityNumber",
		"asset.machineTypeModel","asset.productLine",
		"asset.productTLI", "status",
		"statusDetail","partnerAccount",
		"customerAccount", "responseMetric",
		"customerAddress.StreetAddress","customerAddress.HouseNumber",
		"customerAddress.County","customerAddress.District",
		"customerAddress.PostalCode","customerAddress.Country",
		"customerContact.FirstName", "customerContact.LastName" };
    
    
    /** variable name  hardware order export*/
	public static  String[] PATTERNS_HARDWAREORDER_EXPORT = { 
		"serviceRequest.serviceRequestNumber","serviceRequest.serviceRequestDate","serviceRequest.asset.serialNumber",
		"activityType.value",
		"debriefStatus",
		"serviceRequest.asset.activityNumber","serviceRequest.asset.productModelNumber","serviceRequest.asset.productLine",
		"serviceRequest.asset.productTLI","activityStatus.value","activitySubStatus.value","partnerAccount.accountName",
		"customerAccount.accountName","responseMetric","serviceRequest.projectName",
		"serviceRequest.actualStartDate","serviceRequest.asset.installDate","technician.firstName",
		"serviceRequest.asset.storeFrontName","serviceAddress.addressLine1","serviceAddress.officeNumber",
		"serviceAddress.county","serviceAddress.district","serviceAddress.city","serviceAddress.state",
		"serviceAddress.province","serviceAddress.postalCode","serviceAddress.country","serviceRequest.primaryContact.firstName",
		"serviceRequest.primaryContact.lastName","expectedCompletionDate","dispatchDate","expectedStartInterventionDate"};
	public static  String[] PATTERNS_HARDWAREORDER_EXPORT_OFFLINEDEBRIEFS = { 
		"serviceRequest.serviceRequestNumber","serviceRequest.serviceRequestDate","serviceRequest.asset.serialNumber",
		"activityType.value",
		"debriefStatus",
		"serviceRequest.asset.activityNumber","serviceRequest.asset.productModelNumber","serviceRequest.asset.productLine",
		"serviceRequest.asset.productTLI","activityStatus.value","activitySubStatus.value","partnerAccount.accountName",
		"customerAccount.accountName","responseMetric","serviceRequest.projectName",
		"serviceRequest.actualStartDate","serviceRequest.asset.installDate","technician.firstName",
		"serviceRequest.asset.storeFrontName","serviceAddress.addressLine1","serviceAddress.officeNumber",
		"serviceAddress.county","serviceAddress.district","serviceAddress.city","serviceAddress.state",
		"serviceAddress.province","serviceAddress.postalCode","serviceAddress.country","serviceRequest.primaryContact.firstName",
		"serviceRequest.primaryContact.lastName"};
	public static  String[] PATTERNS_MASSUPLOAD = { 
		"serviceRequest.serviceRequestNumber","serviceRequest.serviceRequestDate","serviceRequest.asset.serialNumber",
		"activityType.value",
		"debriefStatus",
		"serviceRequest.asset.activityNumber","serviceRequest.asset.productModelNumber","serviceRequest.asset.productLine",
		"serviceRequest.asset.productTLI","activityStatus.value","activitySubStatus.value","partnerAccount.accountName",
		"customerAccount.accountName","responseMetric","serviceRequest.projectName",
		"serviceRequest.actualStartDate","serviceRequest.asset.installDate","technician.firstName",
		"serviceRequest.asset.storeFrontName","serviceAddress.addressLine1","serviceAddress.officeNumber",
		"serviceAddress.county","serviceAddress.district","serviceAddress.city","serviceAddress.state",
		"serviceAddress.province","serviceAddress.postalCode","serviceAddress.country","serviceRequest.primaryContact.firstName",
		"serviceRequest.primaryContact.lastName","expectedStartInterventionDate","expectedCompletionDate"};
	
    //Ends changes MPS 2.1 wave 4
	
	/** Constants for Hardware Debrief*/
	public static final String SRTYPE="srType";
	
	/** Constants for Hardware Debrief*/
	public static final String SRNUMBER="srNumber";
	
	/** Constants for Hardware Debrief*/
	public static final String viewOrCloseout="viewOrCloseOut";
	
	/** Constants for Hardware Debrief*/
	public static final String MOVE="MOVE";
	/** Constants for Hardware Debrief*/
	public static final String INSTALL="INSTALL";
	/** Constants for Hardware Debrief*/
	public static final String DEINSTALL="DEINSTALL";
	
	/** Constants for Hardware Debrief Close Out Status*/
	public static final String CLOSEOUT_STATUS="Ready for Validation";
	
	/** Constants for Hardware Debrief save for later type*/
	public static final String DEBRIEF_TYPE_SAVE="SAVE FOR LATER";
	
	/** Constants for Hardware Debrief debrief type */
	public static final String DEBRIEF_TYPE_CLOSEOUT="CLOSEOUT";
	
	/** Constants for MAss Upload Service Order csv header siebel*/
	public static final String CSVHEADER_HARDWARE_ORDER_MASSUPLOAD="requestInfo.massUpload.hardwareOrder.siebelCSVHeader";
	
	/** Constant for MAssupload export for Hardware debreif*/
	public static final String[] MASSUPLOAD_TEMPLATE_HARDWAREORDER_EXPORTCOLUMN={
		"serviceRequest.serviceRequestNumber",//non editable
		"serviceRequest.asset.activityNumber",//non editable
		"serviceRequest.asset.description",//non editable
		"serviceRequest.asset.productTLI",		
		"serviceRequest.asset.productModelNumber",
		//"serviceRequest.asset.storeDate",
		"customerAccount.accountName",
		
		"serviceRequest.asset.consumableContact.lastName",
		"serviceRequest.asset.consumableContact.firstName",
		"serviceRequest.asset.consumableContact.workPhone",
		"serviceRequest.asset.consumableContact.homePhone",
		"serviceRequest.asset.consumableContact.emailAddress",
		"serviceRequest.asset.consumableContact.contactType",
		"serviceRequest.asset.consumableContact.country",
		
		"serviceRequest.asset.storeFrontName",
		"serviceAddress.addressLine1",
		"serviceAddress.addressLine2",
		"serviceAddress.city",
		"serviceAddress.county",
		"serviceAddress.state",
		"serviceAddress.province",
		"serviceAddress.postalCode",
		"serviceAddress.country",
		"serviceAddress.physicalLocation1",
		"serviceAddress.physicalLocation2",
		"serviceAddress.physicalLocation3",
		"serviceAddress.district",
		"serviceAddress.officeNumber",
		
		"serviceRequest.asset.installDate:gmtString",
		"serviceRequest.asset.serialNumber",
		"serviceRequest.asset.networkConnectedFlag",
		"serviceRequest.asset.ipAddress",
		"serviceRequest.asset.gateway",
		"serviceRequest.asset.subnet",
		"serviceRequest.asset.ipV6",
		"serviceRequest.asset.macAddress",
		"serviceRequest.asset.printerWorkingCondition",
		"serviceRequest.asset.faxConnectedValue",
		"serviceRequest.asset.faxPortNumber",
		"serviceRequest.asset.hostName",
		"serviceRequest.asset.computerName",
		"serviceRequest.asset.portNumber",
		"serviceRequest.asset.wiringClosestNetworkPoint",
		"serviceRequest.asset.account.accountName",
		"serviceRequest.asset.assetCostCenter",
		"serviceRequest.asset.deviceTag",
		"serviceRequest.asset.departmentId",
		"serviceRequest.asset.department",
		"serviceRequest.asset.firmware",
		"serviceRequest.asset.networkTopology",
		"serviceRequest.asset.operatingSystem",
		"serviceRequest.asset.operatingSystemVersion",
		"serviceRequest.asset.topBill",
		"serviceRequest.asset.specialUsage",
		
		/*"serviceRequest.asset.monoLPC",
		"serviceRequest.asset.colorLPC",
		"serviceRequest.asset.faxCount",
		"serviceRequest.asset.scanCount",
		"serviceRequest.asset.lastColorPageCount",*/
		"",// this is for pagecount type
		"",//this is for pagecount
		
		"technician.firstName",
		"serviceProviderReferenceNumber",
		//"serviceRequest.travelDistance",
		//"serviceRequest.travelDistanceUnitOfMeasure",
		//"serviceRequest.travelDuration",
		"activityStatus.value",
		"activitySubStatus.value",
		"",//Debrief Status
		"serviceRequest.asset.partList:list"//List value
		};
	/** Template constant for mass upload tab*/
	public static final String[] MASSUPLOAD_TEMPLATE_HARDWAREORDER_LIST_EXPORTCOLUMN={
		"partId",
		"partNumber",
		"orderQuantity",
		"usedQuantity",
		"notUsedQuantity",
		"doaQuantity",
		"relationType"
	};
	/** Following values are to be ignored when writing csv for siebel*/
	public static final String MASSUPLOAD_HARDWARE_IGNORE_CELLS="03";
	
	/**Mass Upload For hardware*/
	public static final String MASSUPLOADHW="massUploadHW";
	
	/**First line of CSV converting from EXCEL TO CSV*/
	public static final String FIRSTLINEOFSIEBELCSV="\"# LXK MPS Activity Import\"";
	/**session attribute*/
	public static final String SESSION_FILE_MAP_ForHWDebrief_CloseOut = "fileMapInSession_HWCloseOut";
	/**accept update key*/
	public static final String ACCEPTANDUPDATE="acceptUpdate";
	
	/** Constants for Hardware Debrief*/
	public static final String ACTIVITYID="activityId";
	
	/**Constants for Hardware Debrief*/
	public static final String HARDWARE_ACTIViTY_TYPES="hardwareActivityTypes";
	
	/**Constants for Mass upload left nav*/
	public static final String LINK_ID_HARDWAREORDER="link_hardwareOrder";
	
	/**Constants for Mass upload left nav*/
	public static final String LINK_ID_SERVICEORDER="link_serviceOrder";
	
	/**Constants for Mass upload left nav*/
	public static final String LINK_ID_="link_Id";
	
	/**Constants for Hardware debrief*/
	public static final String EXCEPTIONOCCURED_detail="exceptionOccured_Detail";
	
	/**Constants for Hardware debrief*/
	public static final String isHWINSTALL="isHwInstall";
	
	/**Constants for Hardware debrief*/
	public static final String isHWDEINSTALL="isHwDeInstall";
	
	/**Constants for Hardware debrief*/
	public static final String isHWMOVE="isHwMove";
	
	/**Constants for offline Hardware debrief*/
	public static final String OFFLINEDEBRIEF="offlineDebrief";
	
	/**Constants for offline Hardware debrief*/
	public static final String BACKURL="backURL";
	
	/**Constants for Hardware Debrief*/
	public static final List<String> installType=new ArrayList<String>(){/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

	{
		add("HW Install");
		add("HW BAU Install");
		add("HW MADC Install");
	}};
	
	
	/**Constants for Hardware Debrief*/
	public static final List<String> changeType=new ArrayList<String>(){/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

	{
		add("HW Install Change");
		add("HW BAU Install Change");
		add("HW MADC Change");
	}};
	
	
	/**Constants for Hardware Debrief*/
	public static final List<String> moveType=new ArrayList<String>(){/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

	{
		add("HW Install Move");
		add("HW BAU Install Move");
		add("HW MADC Move");
	}};
	
	/**Constants for Hardware Debrief*/
	public static final List<String> installdeinstalltype=new ArrayList<String>(){/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

	{
		
		

		add("HW MADC Install/Decommission");
		add("HW Install Install/Decommission");
		add("HW BAU Install/Decommission");
	}};
	/**Constants for Hardware Debrief*/
	public static final List<String> decommissionType=new ArrayList<String>(){/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

	{
		add("HW Install Decommission");
		add("HW BAU Install Decommission");
		add("HW MADC Decommission");
	}};
	
	
	/**Constants for Hardware Debrief*/
	public static final String INSTALLATION="Installation";
	/**Constants for Hardware Debrief*/
	public static final String CHANGE="Change";
	/**Constants for Hardware Debrief*/
	public static final String DECOMMISSION="Decommission";
	/**Constants for Hardware Debrief*/
	public static final String INSTALLDEINSTALL="HW MADC Install/Decommission";
	/**Constants for Hardware Debrief*/
	public static final String HW_MOVE="Move";
	/**Constants for Hardware Debrief*/
	public static final String HARDWARE_DEBRIEF="Hardware Debrief";//This will be used for amind attachment for hardware.
	 /** variable name  PATTERNS_FILTER_UPLOAD_HISTORY */
	/**Constants for Hardware Debrief*/
    public static  String[] PATTERNS_FILTER_UPLOAD_HISTORY_WITHOUT_TYPE = { "requestNumber","status","fileName"};
	
}
