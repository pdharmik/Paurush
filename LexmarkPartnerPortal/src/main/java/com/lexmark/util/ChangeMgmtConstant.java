/*
* Constants for Lexmark Partner Portal
*/
package com.lexmark.util;

/** interface ChangeMgmtConstant
 * @author Wipro 
 * @version 1.0 
 * */
public interface ChangeMgmtConstant {
	/**False*/
	String FALSE = "false";
	/**True*/
	String TRUE = "true";
	/**User access map for session*/
	String USERACCESSMAPATTRIBUTE = "userAccessMap" ;
	
	// Account name added for MPS Phase 2 
	
	/** ALLHISTORYCOLUMNS */
	String[] ALLHISTORYCOLUMNS = new String[] { "serviceRequestNumber", "serviceRequestDate",
			"serviceRequestType", "area","serviceRequestStatus", "asset.serialNumber","costCenter", "primaryContact.firstName",
			"primaryContact.lastName", "primaryContact.emailAddress", "primaryContact.workPhone", "requestor.firstName", 
			"requestor.lastName","requestor.emailAddress", "requestor.workPhone","accountName"};
	  // *office number , county, district added for MPS Phase 2
	 /** SUPPLYHISTORYCOLUMNS */
	String[] SUPPLYHISTORYCOLUMNS = new String[] {"expediteOrder","", "serviceRequestNumber", "serviceRequestDate",
			 "area","subArea", "asset.serialNumber","serviceRequestStatus","asset.modelNumber","poNumber","costCenter",
			"serviceAddress.addressName", "serviceAddress.storeFrontName","serviceAddress.addressLine1","serviceAddress.officeNumber",
			"serviceAddress.city","serviceAddress.state","serviceAddress.province","serviceAddress.county","serviceAddress.district", 
			"serviceAddress.country","serviceAddress.postalCode", 
			"primaryContact.firstName",	"primaryContact.lastName", "primaryContact.emailAddress", "primaryContact.workPhone", 
			"requestor.firstName", "requestor.lastName","requestor.emailAddress", "requestor.workPhone","accountName","asset.deviceTag"};
	
	/** SUPPLYHISTORYPDFCOLUMNS */
	String[] SUPPLYHISTORYPDFCOLUMNS = new String[] {"expediteOrder","serviceRequestNumber", "serviceRequestDate",
			 "area","subArea", "asset.serialNumber","serviceRequestStatus","asset.modelNumber","costCenter","poNumber",
			"serviceAddress.addressName", "serviceAddress.storeFrontName","serviceAddress.addressLine1","serviceAddress.officeNumber",
			"serviceAddress.city","serviceAddress.state","serviceAddress.province","serviceAddress.county","serviceAddress.district",
			"serviceAddress.country","serviceAddress.postalCode", 
			"primaryContact.firstName",	"primaryContact.lastName", "primaryContact.emailAddress", "primaryContact.workPhone", 
			"requestor.firstName", "requestor.lastName","requestor.emailAddress", "requestor.workPhone","accountName","asset.deviceTag"};
	
	/** CHANGEHISTORYCOLUMNS */
	String[] CHANGEHISTORYCOLUMNS = new String[] { "serviceRequestNumber", "serviceRequestDate",
			 "area", "subArea","asset.serialNumber", "serviceRequestStatus","asset.modelNumber",
			"customerReferenceNumber","costCenter",
			"serviceAddress.addressName", "serviceAddress.storeFrontName","serviceAddress.addressLine1","serviceAddress.officeNumber",
			"serviceAddress.city","serviceAddress.state","serviceAddress.province","serviceAddress.county","serviceAddress.district",
			"serviceAddress.country","serviceAddress.postalCode",
			"primaryContact.firstName","primaryContact.lastName", "primaryContact.emailAddress", "primaryContact.workPhone",
			"requestor.firstName", "requestor.lastName","requestor.emailAddress", "requestor.workPhone","accountName","asset.deviceTag"};
	
	//Added for Device Management MPS
	/** BREAKFIXHISTCOLUMNS */
	String [] BREAKFIXHISTCOLUMNS = new String[]{"serviceRequestNumber","serviceRequestStatusDate",
			"asset.serialNumber", "asset.productLine","problemDescription",
			"serviceAddress.addressName","serviceAddress.officeNumber","serviceRequestStatus","serviceAddress.city","serviceAddress.state",
			"serviceAddress.province","serviceAddress.county","serviceAddress.district","serviceAddress.postalCode","serviceAddress.country", "primaryContact.firstName",
			"primaryContact.lastName","primaryContact.emailAddress","primaryContact.workPhone"};
	
	//CI-6 Changes Start Partha
	/** SERVICEREQUESTSCOLUMNS */
	String [] SERVICEREQUESTSCOLUMNS = new String[]{"serviceRequestNumber","serviceRequestDate",
			"asset.serialNumber", "asset.modelNumber","problemDescription","resolutionCode",
			"serviceAddress.addressName","serviceRequestStatus","serviceAddress.city","serviceAddress.state",
			"serviceAddress.province","serviceAddress.postalCode","serviceAddress.country", "primaryContact.firstName",
			"primaryContact.lastName","primaryContact.emailAddress","primaryContact.workPhone","assetType","asset.deviceTag", "asset.hostName", "asset.assetCostCenter"};
	//CI-6 Changes End Partha
	//changes for hardware
	/** HARDWAREQUESTSCOLUMNS */
	String [] HARDWAREQUESTSCOLUMNS = new String[]{"serviceRequestNumber","serviceRequestNumber","serviceRequestDate","area","subArea","statusType.value","poNumber","costCenter",
				"serviceAddress.addressName","serviceAddress.storeFrontName","serviceAddress.addressLine1","serviceAddress.city","serviceAddress.state",
				"serviceAddress.province","serviceAddress.country","serviceAddress.postalCode","primaryContact.firstName","primaryContact.lastName","primaryContact.emailAddress",
				"primaryContact.workPhone","requestor.firstName","requestor.lastName","requestor.emailAddress","requestor.workPhone","accountName"};
	
	//end
	
	
	/** SEARCHTYPE_MYSRLIST */
	String SEARCHTYPE_MYSRLIST = "MYREQUESTLIST";
	/** SEARCHTYPE_MYBOOKMARKEDASSETLIST */
	String SEARCHTYPE_MYBOOKMARKEDASSETLIST = "MYBOOKMARKEDASSETSLIST";
	/** SEARCHTYPE_MYDRAFTREQUESTLIST */
	String SEARCHTYPE_MYDRAFTREQUESTLIST = "MYDRAFTREQUESTSLIST";
	/** SEARCHTYPE_DATERANGE */
	String SEARCHTYPE_DATERANGE = "DATERANGE";
	/** SEARCHTYPE_DATERANGE_STARTDATE */
	String SEARCHTYPE_DATERANGE_STARTDATE = "serviceRequest.startDate";
	/** SEARCHTYPE_DATERANGE_ENDDATE */
	String SEARCHTYPE_DATERANGE_ENDDATE = "serviceRequest.endDate";
	
	/** CSV_FILETYPE_EXTN */
	String CSV_FILETYPE_EXTN = "csv";
	/** PDF_FILETYPE_EXTN */
	String PDF_FILETYPE_EXTN = "pdf";
	
	/** EMPTYSTRING */ 
	String EMPTYSTRING = "";
	
	/**. pdf file header to be downloaded for change management history */ 
	String DefaultHistoryPDFHeader = "defaultHistory.pdf.header";
	/**. csv file header to be downloaded for change management history */ 
	String DefaultHistoryCSVHeader = "defaultHistory.csv.header";
	
	
	/**. pdf file header to be downloaded for change management history */ 
	String cmHistoryPDFHeader = "changeManagementHistory.pdf.header";
	/**. csv file header to be downloaded for change management history */ 
	String cmHistoryCSVHeader = "changeManagementHistory.csv.header";
	
	/**. pdf file header to be downloaded for change management history */ 
	String supplyHistoryPDFHeader = "supplyManagementHistory.pdf.header";
	/**. csv file header to be downloaded for change management history */ 
	String supplyHistoryCSVHeader = "supplyManagementHistory.csv.header";
	
	
	/**. pdf file header to be downloaded for change management history */ 
	String allHistoryPDFHeader = "allHistory.pdf.header";
	/**. csv file header to be downloaded for change management history */ 
	String allHistoryCSVHeader = "allHistory.csv.header";
	
	
	
	/**. csv file name change management history */ 
	String CMHISTORYDOWNLOADFILENAME = "changemanagementHistory.download.fileName";
	/**. csv file name change management history */ 
	String SUPPLYHISTORYDOWNLOADFILENAME = "sypplymanagementHistory.download.fileName";
	/**. csv file name change management history */ 
	String ALLHISTORYDOWNLOADFILENAME = "Allistory.download.fileName";
	/**CMHISTORY_DEFAULTPAGE*/
	String CMHISTORY_DEFAULTPAGE = "allRequestHistory";
	//String CMHISTORY_DEFAULTRESOURCETYPE ="allRequestHistory";
	
	//Constants added for CHL and Others
	/**CONFIG_FILE_PATH*/
	String CONFIG_FILE_PATH = "/serviceRequestExt.properties";
	/**REQUEST_TYPE_LIST_ADMIN*/
	String REQUEST_TYPE_LIST_ADMIN = "requestType.admin";
	/**REQUEST_TYPE_LIST_USER*/
	String REQUEST_TYPE_LIST_USER = "requestType.user";
	/**REQUEST_SUB_TYPE_ADD_LIST_ADMIN*/
	String REQUEST_SUB_TYPE_ADD_LIST_ADMIN = "requestSubType.add.admin";
	/**REQUEST_SUB_TYPE_CHANGE_LIST_ADMIN*/
	String REQUEST_SUB_TYPE_CHANGE_LIST_ADMIN = "requestSubType.change.admin";
	/**REQUEST_SUB_TYPE_REMOVE_LIST_ADMIN*/
	String REQUEST_SUB_TYPE_REMOVE_LIST_ADMIN = "requestSubType.remove.admin";
	
	/**REQUEST_SUB_TYPE_LIST_USER*/
	String REQUEST_SUB_TYPE_LIST_USER = "requestSubType.user";
	/**ITEMS_DELIMITER*/
	String ITEMS_DELIMITER = ":";
	/**SESSION_FILE_LIST*/
	String SESSION_FILE_LIST = "fileList";
	/**REQUEST_TYPE__ADD*/
	String REQUEST_TYPE__ADD = "101";
	/**REQUEST_TYPE__CHANGE*/
	String REQUEST_TYPE__CHANGE = "102";
	/**REQUEST_TYPE__REMOVE*/
	String REQUEST_TYPE__REMOVE = "103";
	/**SESSION_FILE_MAP*/
	String SESSION_FILE_MAP = "fileMapInSession";
	/**ATTACHMENT_MAX_SIZE*/	
	String ATTACHMENT_MAX_SIZE = "attachment.maxsize";
	/**ATTACHMENT_MAX_COUNT*/
	String ATTACHMENT_MAX_COUNT = "attachment.maxcount";
	/**ATTACHMENT_REQUEST_TYPE*/
	String ATTACHMENT_REQUEST_TYPE = "Service Request";
	/**LOV_TYPE_CM_AREA*/
	String LOV_TYPE_CM_AREA = "CM_AREA_NAME";
	/**LOV_TYPE_CM_SUBAREA*/
	String LOV_TYPE_CM_SUBAREA = "CM_SUB_AREA_NAME";
	/**LOV_SR_TYPE*/
	String LOV_SR_TYPE = "SR_TYPE";
	/**DETAIL_VIEW_POPUP*/
	String DETAIL_VIEW_POPUP = "pop_up";

	//Added by sagar
	// - Added For Mps Phase 2
	/**ALL_REQUEST_HISTORY_FILTER_COLUMNS*/
     String[] ALL_REQUEST_HISTORY_FILTER_COLUMNS = new String[] { "serviceRequestNumber", "area","serviceRequestStatus", "asset.serialNumber","costCenter","primaryContact.firstName", "primaryContact.lastName", "primaryContact.emailAddress", "primaryContact.workPhone","requestor.firstName", "requestor.lastName", "requestor.emailAddress", "requestor.workPhone","accountName"};
    //Added by sagar
    // *office number , county, district added for MPS Phase 2
     /**SUPPLY_REQUEST_HISTORY_FILTER_COLUMNS*/
     String[] SUPPLY_REQUEST_HISTORY_FILTER_COLUMNS = new String[] { "serviceRequestNumber", "area","subArea", "asset.serialNumber","serviceRequestStatus", "asset.modelNumber","poNumber","costCenter","serviceAddress.addressName","serviceAddress.storeFrontName","serviceAddress.addressLine1","serviceAddress.officeNumber", "serviceAddress.city", "serviceAddress.state", "serviceAddress.province", "serviceAddress.county", "serviceAddress.district", "serviceAddress.country","serviceAddress.postalCode",  "primaryContact.firstName", "primaryContact.lastName", "primaryContact.emailAddress", "primaryContact.workPhone","requestor.firstName", "requestor.lastName", "requestor.emailAddress", "requestor.workPhone","accountName","asset.deviceTag"};
     /**CHANGE_REQUEST_HISTORY_FILTER_COLUMNS*/
    //Added by sagar
     String[] CHANGE_REQUEST_HISTORY_FILTER_COLUMNS = new String[] {"serviceRequestNumber", "area","subArea", "asset.serialNumber","serviceRequestStatus", "asset.modelNumber","helpDeskReferenceNumber","costCenter","serviceAddress.addressName","serviceAddress.storeFrontName","serviceAddress.addressLine1","serviceAddress.officeNumber","serviceAddress.city", "serviceAddress.state", "serviceAddress.province","serviceAddress.county","serviceAddress.district","serviceAddress.country","serviceAddress.postalCode",  "primaryContact.firstName", "primaryContact.lastName", "primaryContact.emailAddress", "primaryContact.workPhone","requestor.firstName", "requestor.lastName", "requestor.emailAddress", "requestor.workPhone","accountName","asset.deviceTag"};
     /**LOV_TYPE_CM_SUBAREA_PREFIX*/
	String LOV_TYPE_CM_SUBAREA_PREFIX = "SUBAREA_";
	/**ATTACHMENT_FILE*/
	String ATTACHMENT_FILE="attachment.properties";
	/**USERACCESSMAPATTRIBUTEFORSR*/
	String USERACCESSMAPATTRIBUTEFORSR = "userAccessMapForSr" ;
	
	/**USER_ACCESS_MAP_FOR_AREA*/
	String USER_ACCESS_MAP_FOR_AREA = "userAccessMapForArea" ;
	/**SRTYPE_SUPPLYMANAGEMENT*/
	String SRTYPE_SUPPLYMANAGEMENT = "Consumables Management";
	/**SRTYPE_CHANGEMANAGEMENT*/
	String SRTYPE_CHANGEMANAGEMENT = "Fleet Management";
	/**SRTYPE_BREAKFIX*/
	String SRTYPE_BREAKFIX = "BreakFix";
	/**SRTYPE_RETURNSUPPLIES*/
	String SRTYPE_RETURNSUPPLIES = "Consumables Return";
	
	
	//Added by Arup
	//For XMLoutputGeneratorUtil
	/**CHANGE_IMAGE*/
	 String CHANGE_IMAGE="edit.gif";
	 /**REMOVE_IMAGE*/
	 String REMOVE_IMAGE="delete.gif";
	 /**FAVORIATE_IMAGE_FILE_NAME*/
	 String FAVORIATE_IMAGE_FILE_NAME = "favorite.png";
	 /**UNFAVORIATE_IMAGE_FILE_NAME*/
	 String UNFAVORIATE_IMAGE_FILE_NAME = "unfavorite.png";
	 /**PENCIL_IMAGE_FILE_NAME*/
	 String PENCIL_IMAGE_FILE_NAME = "pencil.png";
	 /**IMAGE_PATH*/
	 String IMAGE_PATH = "/images/";
	
	//For CommonValidator
	 /**NAMEPATTERN*/
	 String NAMEPATTERN = "[a-zA-Z.\\s-]{1,50}";
	 /**STOREFRONTNAMEPATTERN*/
	//private static final String STOREFRONTNAMEPATTERN = "[a-zA-Z]{1,30}";
	 /**PHONEPATTERN*/
	 String PHONEPATTERN = "^[+]{0,1}([0-9]){1,3}[ ]?([-]?(([0-9])|[ ]){1,12})+$";
	 /**EMAILPATTERN*/
	 String EMAILPATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*" + "((\\.[A-Za-z]{2,}){1}$)";
	
	
	/********Added as part of Common *******/
	 /**ACTION*/
	 String ACTION = "action";
	 /**ACNTCURRDETAILS*/
	 String ACNTCURRDETAILS = "accountCurrentDetails";
	 /**TIMEZNOFFSET*/
	 String TIMEZNOFFSET = "timeZoneOffset";
	 /**HOURFORMAT*/
	 String HOURFORMAT = " HH:mm:ss";
	 /**DEVICELISTRESURL*/
	 String DEVICELISTRESURL = "deviceListPopulate";
	 /**ASSETRESULTLIST*/
	 String ASSETRESULTLIST = "assetResultList";
	 /**YES*/
	 String YES = "yes";
	
	/*********Added for Manage Asset ***********/
	//Model Attributes
	 /**ADDASSETFORM*/
	 String ADDASSETFORM = "manageAssetForm";
	 /**CHANGEASSETFORM*/
	 String CHANGEASSETFORM = "manageAssetFormForChange";
	 /**DECOMMASSETFORM*/
	 String DECOMMASSETFORM = "manageAssetFormForDecommission";
	 /**MODELATTRSRNO*/
	 String MODELATTRSRNO = "srNo";
	 /**SRNUMBER*/
	 String SRNUMBER = "srNumber";
	 

	
	//For Page redirection
	 /**ASSETLISTPATH*/
	 String ASSETLISTPATH = "changemanagement/manageAsset/assetList";
	 /**ADDASSETPATH*/
	 String ADDASSETPATH = "changemanagement/manageAsset/addAsset";
	 /**ASSETSUMMARYPATH*/
	 String ASSETSUMMARYPATH = "changemanagement/manageAsset/assetSummary";
	 /**ADDASSETCONFPATH*/
	 String ADDASSETCONFPATH = "changemanagement/manageAsset/addAssetConfirmation";
	 /**CHANGEASSETPATH*/
	 String CHANGEASSETPATH = "changemanagement/manageAsset/changeAsset";
	 /**CHANGEASSETCONFPATH*/
	 String CHANGEASSETCONFPATH = "changemanagement/manageAsset/changeAssetConfirmation";
	 /**DECOMMASSETPATH*/
	 String DECOMMASSETPATH = "changemanagement/manageAsset/decommissionAsset";
	 /**DECOMMASSETCONFPATH*/
	 String DECOMMASSETCONFPATH = "changemanagement/manageAsset/decommissionAssetConfirmation";
	 /**PRINTASSETPATH*/
	 String PRINTASSETPATH = "changemanagement/manageAsset/printAsset";
	 /**ASSETEMAILPATH*/
	 String ASSETEMAILPATH = "changemanagement/manageAsset/AssetEmail";
	 /**ALLREQHISTPAGENM*/
	 String ALLREQHISTPAGENM = "lgsHistoryDetails/allRequestHistory";
	 /**SELECTCONTACTPATH*/
	 String SELECTCONTACTPATH = "common/contactPopup/selectContact";
	 /**ADDRLISTPOPUPPATH*/
	 String ADDRLISTPOPUPPATH = "changemanagement/addressListPopup";
	 /**CHLTREEPATH*/
	 String CHLTREEPATH = "common/chlPopUp/displayCHLTree";
	 /**CRREQPOPUPPATH*/
	 String CRREQPOPUPPATH = "common/createNewRequestPopUp";
	 /**ATTACHMENTPATH*/
	 String ATTACHMENTPATH = "changemanagement/attachment";
	
	//Request Attributes & Parameters
	 /**ADDONE*/
	 String ADDONE = "addone";
	 /**PREVSRNO*/
	 String PREVSRNO = "prevSrNo";
	 /**ISUPDATEFLAG*/
	 String ISUPDATEFLAG = "isUpdateFlag";
	 /**EXCEPTNATTR*/
	 String EXCEPTNATTR = "exceptn";
	 /**ERRORATTR*/
	 String ERRORATTR = "errorOccurred";
	 /**SERVICEREQNO*/
	 String SERVICEREQNO = "serviceRequestNumber";
	 /**UPDATEFLAG*/
	 String UPDATEFLAG = "updateFlag";
	 /**SELECTEDVALUE*/
	 String SELECTEDVALUE = "selectedVal";
	 /**ASSETLISTGRIDID*/
	 String ASSETLISTGRIDID = "assetListGrid";
	 /**FORWARDTOPAGE*/
	 String FORWARDTOPAGE = "forwardtoJSP";
	 /**ASSETID*/
	 String ASSETID = "assetId";
	 /**REQUESTTYPE*/
	 String REQUESTTYPE = "requestType";
	 /**TRUEATTR*/
	 String TRUEATTR = "true";
	 /**REQ_TYPE_ASSETS*/
	 String REQ_TYPE_ASSETS= "Assets";
	 /**REQ_TYPE_ADDRESS*/
	 String REQ_TYPE_ADDRESS= "Addresses";
	 /**REQ_TYPE_CONTACTS*/
	 String REQ_TYPE_CONTACTS= "Contacts";
	 /**REQ_TYPE_ADD*/
	 String REQ_TYPE_ADD = "Add";
	 /**REQ_TYPE_CHANGE*/
	 String REQ_TYPE_CHANGE = "Change";
	 /**REQ_TYPE_REMOVE*/
	 String REQ_TYPE_REMOVE = "Remove";
	 /**REQ_TYPE_DECOMM*/
	 String REQ_TYPE_DECOMM = "Decommission";
	 /**REQ_TYPE_SWAP*/
	 String REQ_TYPE_SWAP = "Swap";
	
	//Area & Sub Area values to be sent in webservice call
	 /**ADDASSETAREA*/
	 String ADDASSETAREA = "Add Asset Data";
	 /**ADDASSETAREA_YES*/
	 String ADDASSETAREA_YES = "Add Asset";
	 /**ADDASSETSUBAREA*/
	 String ADDASSETSUBAREA = "Register Asset";
	 /**ADDASSETSUBAREA_YES*/
	 String ADDASSETSUBAREA_YES = "Install Asset";
	 /**CHANGEASSETAREA*/
	 String CHANGEASSETAREA = "Change Asset";
	 /**CHANGEASSETSUBAREA*/
	 String CHANGEASSETSUBAREA = "Change Asset Data";
	 /**DECOMMASSETAREA_YES*/
	 String DECOMMASSETAREA_YES = "Decommission Asset";
	 /**DECOMMASSETAREA*/
	 String DECOMMASSETAREA = "Decommission Asset Data";
	 /**DECOMMASSETSUBAREA_YES*/
	 String DECOMMASSETSUBAREA_YES = "DeInstall Asset";
	 /**DECOMMASSETSUBAREA*/
	 String DECOMMASSETSUBAREA = "Deregister Asset";
	 /**SWAPASSETAREA*/
	 String SWAPASSETAREA = "Change Asset";
	 /**SWAPASSETSUBAREA*/
	 String SWAPASSETSUBAREA = "Register Asset Swap";
	 
	 /**ADDACCNTDATA*/
	 String ADDACCNTDATA = "Add Account Data";
	 /**CHANGEACCNTDATA*/
	 String CHANGEACCNTDATA = "Change Account Data";
	 /**DECOMMACCNTDATA*/
	 String DECOMMACCNTDATA = "Decommission Account Data";
	 /**ADDADDRESSSUBAREA*/
	 String ADDADDRESSSUBAREA = "Add Address";
	 /**CHANGEADDRESSSUBAREA*/
	 String CHANGEADDRESSSUBAREA = "Change Address";
	 /**REMOVEADDRESSSUBAREA*/
	 String REMOVEADDRESSSUBAREA = "Deactivate Address";
	 /**ADDCONTACTSUBAREA*/
	 String ADDCONTACTSUBAREA = "Add Contact";
	 /**CHANGECONTACTSUBAREA*/
	 String CHANGECONTACTSUBAREA = "Change Contact";
	 /**REMOVECONTACTSUBAREA*/
	 String REMOVECONTACTSUBAREA = "Deactivate Contact";
	 /**SIEBEL_VALUE_CHL_SUBAREA_ADD*/
	 String SIEBEL_VALUE_CHL_SUBAREA_ADD = "Add Customer Hierarchy";
	 /**SIEBEL_VALUE_CHL_SUBAREA_CHANGE*/
	 String SIEBEL_VALUE_CHL_SUBAREA_CHANGE = "Change Customer Hierarchy";
	 /**SIEBEL_VALUE_CHL_SUBAREA_DECOMMISSION*/
	 String SIEBEL_VALUE_CHL_SUBAREA_DECOMMISSION = "Deactivate Customer Hierarchy";
	
	//Page Names to be set as request attributes for redirection
	 /**ADDASSETCONFPGNM*/
	 String ADDASSETCONFPGNM = "addAssetConfirmation";
	 /**CHNGASSETCONFPGNM*/
	 String CHNGASSETCONFPGNM = "changeAssetConfirmation";
	 /**DECOMMASSETCONFPGNM*/
	 String DECOMMASSETCONFPGNM = "decommissionAssetConfirmation";
	 /**ASSETSUMMARY*/
	 String ASSETSUMMARY = "assetSummary";
	 /**ISLEXEMPLOYEE*/
	 String ISLEXEMPLOYEE = "isLexmarkEmployee";
	 /**REQUESTORCONT*/
	 String REQUESTORCONT = "requestorContact";
	 /**COUNTRYCODE*/
	 String COUNTRYCODE = "countryCode";
	 
	
	//Others
	 /**CSVFAILURE*/
	 String CSVFAILURE = "failure";
	 /**CSVSUCCESS*/
	 String CSVSUCCESS = "success";
	 /**TYPEOFREQ*/
	 String TYPEOFREQ = "typeOfRequest";//Required for asset summary
	 /**TYPEOFREQ_ADD*/
	 String TYPEOFREQ_ADD = "Add_Asset";//Required for asset summary
	 /**TYPEOFREQ_CHANGE*/
	 String TYPEOFREQ_CHANGE = "Change_Asset";//Required for asset summary
	 /**TYPEOFREQ_DECOMM*/
	 String TYPEOFREQ_DECOMM = "Decomm_Asset";//Required for asset summary
	 /**CHANGEASSETREQTYPE*/
	 String CHANGEASSETREQTYPE = "changeAssetRequest";
	 /**DECOMMASSETREQTYPE*/
	 String DECOMMASSETREQTYPE = "decommissionAssetRequest";
	 /**CSVTYPEMANAGEASSET*/
	 String CSVTYPEMANAGEASSET = "ManageAsset";
	 /**CSVTYPEMANAGEADDRESS*/
	 String CSVTYPEMANAGEADDRESS = "ManageAddress";
	 /**CSVTYPEMANAGECONTACT*/
	 String CSVTYPEMANAGECONTACT = "ManageContact";
	 /**SERVICEREQTYPE*/
	 String SERVICEREQTYPE = "Fleet Management";
	 /**VENDORREQTYPE*/
	 String VENDORREQTYPE = "Vendor";
	 /**LEXMARKREQTYPE*/
	 String LEXMARKREQTYPE = "Lexmark";
	 /**VIEWPARAM*/
	 String VIEWPARAM = "view";
	 /**CONTENTTYPEXML*/
	 String CONTENTTYPEXML = "text/xml";
	 /**CONTENTTYPEHTML*/
	 String CONTENTTYPEHTML = "text/html";
	 /**ISPOPUP*/
	 String ISPOPUP = "ispopup";
	 /**ISCONTACTPOPUP*/
	 String ISCONTACTPOPUP = "isContactPopUp";
	 /**CHECKCONSELGFLAG*/
	 String CHECKCONSELGFLAG = "chkConsElgFlag";
	 /**CREATESERVICEREQFLAG*/
	 String CREATESERVICEREQFLAG = "createServiceRequestFlag";
	 /**USERTYPE_PARTNER*/
	 String USERTYPE_PARTNER = "Partner";
	
	/*********Added for Manage Contact ***********/
	//Page Names to be set as request attributes for redirection
	 /**MANAGECONTACTFORM*/
	 String MANAGECONTACTFORM = "manageContactForm";
	 /**OLDCONTACTREMOVE*/
	 String OLDCONTACTREMOVE = "oldContactRemove";
	 /**OLDCONTACTCHANGE*/
	 String OLDCONTACTCHANGE = "oldContactChange";
	 
	//Page name for Redirection
	 /**PRINTCONTACTPATH*/
	 String PRINTCONTACTPATH = "changemanagement/manageContact/printContact";
	 /**CONTACTEMAILPATH*/
	 String CONTACTEMAILPATH = "changemanagement/manageContact/ContactEmail";
	 /**CONTACTLISTPATH*/
	 String CONTACTLISTPATH = "changemanagement/manageContact/contactList";
	 /**ADDCONTACTPATH*/
	 String ADDCONTACTPATH = "changemanagement/manageContact/addContact";
	 /**CHANGECONTACTPATH*/ 
	 String CHANGECONTACTPATH = "changemanagement/manageContact/changeContact";
	 /**REMOVECONTACTPATH*/
	 String REMOVECONTACTPATH = "changemanagement/manageContact/removeContact";
	 /**ADDCONTACTREVIEWPATH*/
	 String ADDCONTACTREVIEWPATH = "changemanagement/manageContact/addContactReview";
	 /**CHANGECONTACTREVIEWPATH*/
	 String CHANGECONTACTREVIEWPATH = "changemanagement/manageContact/changeContactReview";
	 /**REMOVECONTACTREVIEWPATH*/
	 String REMOVECONTACTREVIEWPATH = "changemanagement/manageContact/removeContactReview";
	 /**CONTACTSUMMARYPATH*/
	 String CONTACTSUMMARYPATH = "changemanagement/manageContact/contactSummary";
	
	
	 /**MANAGECONTACTREVIEW*/
	 String MANAGECONTACTREVIEW = "manageContactReview";
	 /**CONTACTSUMMARYSHOW*/
	 String CONTACTSUMMARYSHOW = "contactSummaryShow";
	 
	 /**EXCEPTION*/	
	 String EXCEPTION = "exception";
	 /**CONTACTRESULTLIST*/
	 String CONTACTRESULTLIST = "contactResultList";
	 
	 /**ADDCONTACTRQST*/		
	 String ADDCONTACTRQST = "addContactRequest";
	 /**REMOVECONTACTRQST*/
	 String REMOVECONTACTRQST = "removeContactRequest";
	 /**CHANGECONTACTRQST*/		
	 String CHANGECONTACTRQST = "changeContactRequest";
	 
	 /**FLOWDIRECTION*/
	 String FLOWDIRECTION = "flowDirection";
	 /**FORWARD*/
	 String FORWARD = "forward";
	
	/**ERROR*/
	 String ERROR = "error";
	 /**CONTACTGRIDCONTAINER*/
	 String CONTACTGRIDCONTAINER = "contactGrid_container";
	 /**FIRSTNAME*/
	 String FIRSTNAME = "firstName";
	 /**LASTNAME*/
	 String LASTNAME = "lastName";
	 /**WORKPHONE*/
	 String WORKPHONE = "workPhone";
	 /**ALTPHONE*/
	 String ALTPHONE = "alternatePhone";
	 /**EMAILID*/
	 String EMAILID = "emailId";


	//validations
	/**AC_FIRSTNAME*/
	 String AC_FIRSTNAME = "accountContact.firstName";
	 /**AC_FIRSTNAMEEMPTY*/
	 String AC_FIRSTNAMEEMPTY = "contact.firstname.empty";
	 /**AC_LASTNAME*/
	 String AC_LASTNAME = "accountContact.lastName";
	 /**AC_LASTNAMEEMPTY*/
	 String AC_LASTNAMEEMPTY = "contact.lastname.empty";
	 /**AC_WORKPHN*/
	 String AC_WORKPHN = "accountContact.workPhone";
	 /**AC_WORKPHNEMPTY*/
	 String AC_WORKPHNEMPTY = "contact.workphone.empty";
	 /**AC_EMAILID*/
	 String AC_EMAILID = "accountContact.emailAddress";
	 /**AC_EMAILIDEMPTY*/
	 String AC_EMAILIDEMPTY = "contact.emailaddress.empty";
	 /**AC_ADDLINE1*/
	 String AC_ADDLINE1 = "accountContact.address.addressLine1";
	 /**AC_ADDLINE1EMPTY*/
	 String AC_ADDLINE1EMPTY = "contact.addrline1.empty";
	 /**AC_CITY*/
	 String AC_CITY = "accountContact.address.city";
	 /**AC_CITYEMPTY*/
	 String AC_CITYEMPTY = "contact.city.empty";
	 /**AC_COUNTRY*/
	 String AC_COUNTRY = "accountContact.address.country";
	 /**AC_COUNTRYEMPTY*/
	 String AC_COUNTRYEMPTY = "contact.country.empty";
	 /**AC_POSTALCODE*/
	 String AC_POSTALCODE = "accountContact.address.postalCode";
	 /**AC_POSTALCODEEMPTY*/
	 String AC_POSTALCODEEMPTY = "contact.zip.empty";
	//exceptions
	/**SAVEDATA_ERROR*/
	 String SAVEDATA_ERROR = "Error while saving data";



	/********Added for Manage Address ***********/
	/**ADDRESSDETAILSOLD*/
	 String ADDRESSDETAILSOLD = "addressDetailsOld";
	 /**ADDRESSFORM*/
	 String ADDRESSFORM = "addressForm";
	 /**CONTACTID*/
	 String CONTACTID = "contactid";
	 /**ADDRESSID*/
	 String ADDRESSID = "addressId";
	 /**ADDRESSNAME*/
	 String ADDRESSNAME = "addressName";
	 /**ADDRESSLINE1*/
	 String ADDRESSLINE1 = "addressLine1";
	 /**ADDRESSLINE2*/
	 String ADDRESSLINE2 = "addressLine2";
	 /**COUNTRY*/	 
	 String COUNTRY = "country";
	 /**STATEGRID*/
	 String STATEGRID = "stateGrid";
	 /**PROVINCE*/
	 String PROVINCE = "province";
	 /**ZIP*/
	 String ZIP = "zipPostal";
	 /**STOREFRONTNAME*/
	 String STOREFRONTNAME = "storeFrontName";
	 /**ACCOUNTID*/
	 String ACCOUNTID = "accountId";
	// - Added For Mps Phase 2
	 /**ACCOUNTNAME*/
	 String ACCOUNTNAME = "accountName";
	 /**ACCOUNTORG*/
	 String ACCOUNTORG = "accountOrganization";
	 /**AGREEMENTID*/
	 String AGREEMENTID = "agreementId";
	 /**AGREEMENTNAME*/
	 String AGREEMENTNAME = "agreementName";
	



	//For Page redirection
	 /**VIEWADDRESSLIST*/
	 String VIEWADDRESSLIST = "changemanagement/manageAddress/addressList";
	 /**PRINTADDRESS*/
	 String PRINTADDRESS = "changemanagement/manageAddress/printAddress";
	 /**ADDRESSEMAIL*/
	 String ADDRESSEMAIL = "changemanagement/manageAddress/AddressEmail";
	 /**ADDADDRESSPAGE*/
	 String ADDADDRESSPAGE = "changemanagement/manageAddress/addAddress";
	 /**ADDRESSUMMARY*/
	 String ADDRESSUMMARY = "changemanagement/manageAddress/addressSummary";
	 /**MANAGEADDRESS*/
	 String MANAGEADDRESS = "changemanagement/manageAddress/";
	 /**CHANGEADDRESSCONFIRMATION*/
	 String CHANGEADDRESSCONFIRMATION = "changeAddressConfirmation";
	 /**CHANGEADDRESSPAGE*/
	 String CHANGEADDRESSPAGE = "changemanagement/manageAddress/changeAddress";
	 /**REMOVEADDRESSPAGE*/
	 String REMOVEADDRESSPAGE = "changemanagement/manageAddress/removeAddress";


	//Request Attributes & Parameters
	 /**EXCEPTIONOCCURED*/
	 String EXCEPTIONOCCURED = "exceptionOccured";
	 /**ISBACK*/
	 String ISBACK = "isback";
	 /**ISCHANGE*/
	 String ISCHANGE = "ischange";
	 /**ISREMOVE*/
	 String ISREMOVE = "isremove";
	 /**CHANGEADDRESS*/
	 String CHANGEADDRESS = "changeAddress";
	 /**REMOVEADDRESS*/
	 String REMOVEADDRESS = "removeAddress";
	 /**ADDADDRESS*/
	 String ADDADDRESS = "addAddress";
	 /**DUPLICATESUBMISSION*/
	 String DUPLICATESUBMISSION = "exception.serviceRequest.duplicateSubmission";
	 /**ISCHANGEADDRESS*/
	 String ISCHANGEADDRESS = "isChangeAddress";
	 /**SUMMARYPAGE*/
	 String SUMMARYPAGE = "showSummaryPage";
	 /**ERROROCCURED*/
	 String ERROROCCURED = "errorOccured";
	 /**CONFIRMADDRESS*/
	 String CONFIRMADDRESS = "addressConfirm";
	 /**ADDADDRESSCONFIRMATION*/
	 String ADDADDRESSCONFIRMATION = "addAddressConfirmation";
	 /**REMOVEADDRESSCONFIRMATION*/
	 String REMOVEADDRESSCONFIRMATION = "removeAddressConfirmation";
	 /**DELETEADDRESS*/
	 String DELETEADDRESS = "deleteAddress";
	 /**STATE*/
	 String STATE = "state";
	 /**CITY*/
	 String CITY = "city";
	 /**CHLNODEID*/
	 String CHLNODEID = "chlNodeId";
	 /**ATTACHMENTREQTYPE*/
	 String ATTACHMENTREQTYPE ="Service Request";
	 /**CONTENTTYPE*/
	 String CONTENTTYPE = "text/plain";
	 /**GENERICADDRESSNAME*/
	 String GENERICADDRESSNAME = "genericAddress.addressName";
	 /**GENERICADDRESSLINE1*/
	 String GENERICADDRESSLINE1 = "genericAddress.addressLine1";
	 /**GENERICCITY*/
	 String GENERICCITY = "genericAddress.city";
	 /**GENERICCOUNTRY*/
	 String GENERICCOUNTRY = "genericAddress.country";
	 /**GENERICPOSTALCODE*/
	 String GENERICPOSTALCODE = "genericAddress.postalCode";
	 /**EMPTYADDRNAME*/
	 String EMPTYADDRNAME = "validation.address.addrName.empty";
	 /**EMPTYADDRLINE1*/
	 String EMPTYADDRLINE1 = "contact.addrline1.empty";
	 /**EMPTYCITY*/
	 String EMPTYCITY = "contact.city.empty";
	 /**EMPTYCOUNTRY*/
	 String EMPTYCOUNTRY = "contact.country.empty";
	 /**EMPTYZIP*/
	 String EMPTYZIP = "contact.zip.empty";
	 /**BOOKMARKED*/
	 String BOOKMARKED = "bookmarked";
	//Added for CHL & Others
	 /**TYPE_OF_FLOW*/
	 String TYPE_OF_FLOW = "typeOfFlow";
	 /**CHL_FLOW*/
	 String CHL_FLOW = "CHL";
	 /**OTHERS_FLOW*/
	 String OTHERS_FLOW = "Others";
	 
	 /**SRTYPE_CANCEL_REQUEST*/
	 String SRTYPE_CANCEL_REQUEST = "Inquiry";
	 /**SR_SUBTYPE_CANCEL_REQUEST*/
	 String SR_SUBTYPE_CANCEL_REQUEST = "Cancel Request";
	 
	 /**VALUE_AREA*/
	 String VALUE_AREA = "area";
	 /**VALUE_SUBAREA*/
	 String VALUE_SUBAREA = "subArea";
	 /**FLOW_TYPE_UPDATE*/
	 String FLOW_TYPE_UPDATE = "update";
	 /**FLOW_TYPE_CANCEL*/
	 String FLOW_TYPE_CANCEL = "cancel";
	 
	 /**SUBAREA_INITIATE_PO*/
	 String SUBAREA_INITIATE_PO = "Initiate PO";
	
	 /**LIST_EXCEPTION*/
	 String LIST_EXCEPTION = "exceptionList";
	
	//CHL AREA SUB AREA VALUES
	 /**CHL_AREA_ADD*/
	 String CHL_AREA_ADD = "requestInfo.cm.chl.area.add";
	 /**CHL_AREA_CHANGE*/
	 String CHL_AREA_CHANGE = "requestInfo.cm.chl.area.change";
	 /**CHL_AREA_DECOMMISSION*/
	 String CHL_AREA_DECOMMISSION = "requestInfo.cm.chl.area.decommission";

	
	 /**ALL_REQUESTS*/
	 String ALL_REQUESTS = "ALL_REQUESTS";
	 /**SUPPLY_REQUESTS*/
	 String SUPPLY_REQUESTS = "SUPPLY_REQUESTS";
	 /**CHANGE_REQUESTS*/
	 String CHANGE_REQUESTS = "CHANGE_REQUESTS";
	 /**SERVICE_REQUESTS*/
	 String SERVICE_REQUESTS = "SERVICE_REQUESTS";
	 /**HARDWARE_REQUESTS*/
	 String HARDWARE_REQUESTS = "HARDWARE_REQUESTS";
	 /**HARDWAREREQUESTDETAILS*/
	 String HARDWAREREQUESTDETAILS="lgsHistoryDetails/hardwareDetails";
	 /**HARDWARE*/
	 String HARDWARE="hardware";
	
	/********** Added for Device Mgmt *******/
	 /**ISDEVICE_BOOKMARKED*/
	 String ISDEVICE_BOOKMARKED = "isDeviceBookmarked";
	 /**ASSETROWID*/
	 String ASSETROWID = "assetRowId";		
	 /**DWNLD_DEVICELIST_URL*/
	 String DWNLD_DEVICELIST_URL = "downloadDeviceListURL";
	 /**DEVICELIST_GRID*/
	 String DEVICELIST_GRID = "devicelistGrid";
	 /**DEVICELOCTREE_URL*/
	 String DEVICELOCTREE_URL = "deviceLocationTreeXMLURL";
	 /**CHLTREE_URL*/
	 String CHLTREE_URL = "chlTreeXMLURL";
	 /**SEARCH_NAME*/
	 String SEARCH_NAME = "searchName";
	 /**SEARCH_VALUE*/
	 String SEARCH_VALUE = "searchValue";
	 /**ASSET_ROWID*/
	 String ASSET_ROWID = "assetRowId";
	 /**SRHISTORY_TYPE*/
	 String SRHISTORY_TYPE = "srHistoryType";
	 /**SERVICEREQ_LIST*/
	 String SERVICEREQ_LIST = "ServiceRequestList";
	 /**LTPCSUCCESS*/
	 String LTPCSUCCESS = "ltpcSuccess";
	 /**LTPCVALUE*/
	 String LTPCVALUE = "ltpcvalue";
	 /**COLORVALUE*/
	 String COLORVALUE = "colorvalue";
	 /**LTPCDATE*/
	 String LTPCDATE = "ltpcdate";
	 /**COLORDATE*/
	 String COLORDATE = "colordate";	
	 /**SUCCESS*/
	 String SUCCESS = "success";
	 /**NEWPAGECOUNT*/
	 String NEWPAGECOUNT = "newPageCount";
	 /**NEWCOLORPGCNT*/
	 String NEWCOLORPGCNT = "newColorPageCount";
	 /**NEWPGREADDT*/
	 String NEWPGREADDT = "newPageReadDate";
	 /**NEWCOLORPGREADDT*/
	 String NEWCOLORPGREADDT = "newColorPageReadDate";
	 /**SELECTEDASSETID*/
	 String SELECTEDASSETID = "selectedAssetId";	
	 /**DEVICEFINDER_PATH*/
	 String DEVICEFINDER_PATH = "deviceFinder/deviceFinder";
	 /**DEVICEDETAILS_PATH*/
	 String DEVICEDETAILS_PATH = "deviceFinder/deviceInfoPage";
	 /**DEVICE_LIST_PRNT_PATH*/
	 String DEVICE_LIST_PRNT_PATH = "deviceFinder/deviceListPrint";
	 /**DEVICE_DTLS_PRNT_PG*/
	 String DEVICE_DTLS_PRNT_PG = "deviceFinder/deviceDetailsPrintPage";
	 /**DEVICE_PGCNTPOP_PATH*/
	 String DEVICE_PGCNTPOP_PATH = "deviceFinder/pageCountPopUp";
	
	//ORDER MANAGEMENT AREA SUB AREA VALUE
	 /**OM_AREA_MIX_PARTS*/
	 String OM_AREA_MIX_PARTS = "Consumable Mix Parts Request";
	 /**OM_SUB_AREA_ASSET*/
	 String OM_SUB_AREA_ASSET = "Asset-Specific Request";
	 /**OM_SUB_AREA_CATALOG*/
	 String OM_SUB_AREA_CATALOG = "Catalog Request";
	 /**OM_SR_STATUS_OPEN*/
	 String OM_SR_STATUS_OPEN = "Open";
	 /**OM_SR_STATUS_DRAFT*/
	 String OM_SR_STATUS_DRAFT = "Draft";
	 /**OM_SR_TYPE*/
	 String OM_SR_TYPE = "Consumables Management";
	/*
	 * Added for JAN release PARTS to be installed
	 * */
	 /**OM_SR_TYPE_PARTS_INSTALLED*/
	 String OM_SR_TYPE_PARTS_INSTALLED = "Consumables Install Request";
	
	
	/********Added for History Controller ***********/
	 /**FLEETMGMT*/
	 String FLEETMGMT = "Fleet_Management";
	 /**CONSUMABLESMGMT*/
	 String CONSUMABLESMGMT = "Consumables_Management";
	 /**CLAIMS*/
	 String CLAIMS = "Claims";
	 /**ALLREQUESTS*/
	 String ALLREQUESTS = "ALL_REQUESTS";
	 /**SUPPLYREQUESTS*/
	 String SUPPLYREQUESTS = "SUPPLY_REQUESTS";
	 /**CHANGEREQUESTS*/
	 String CHANGEREQUESTS = "CHANGE_REQUESTS";
	 /**DOWNLOADCONTRACT*/
	 String DOWNLOADCONTRACT = "downLoadContract";
	 /**DOWNLOADATTACHMENT*/
	 String DOWNLOADATTACHMENT = "downloadAttachment";
	 /**ACTIVITYDATE*/
	 String ACTIVITYDATE = "activityDate";
	 /**ACTIVITYSTATUS*/
	 String ACTIVITYSTATUS = "activityStatus";
	 /**ACTIVITYDESCRIPTION*/
	 String ACTIVITYDESCRIPTION = "activityDescription";


	//for page redirection
	 /**CHANGEREQUESTDETAILSPOPUP*/
	 String CHANGEREQUESTDETAILSPOPUP = "lgsHistoryDetails/changeRequestDetailsPopup";
	 /**CHANGEREQUESTDETAILS*/
	 String CHANGEREQUESTDETAILS = "lgsHistoryDetails/changeRequestDetails";
	 /**SUPPLYREQUESTDETAILSPOPUP*/
	 String SUPPLYREQUESTDETAILSPOPUP = "lgsHistoryDetails/supplyRequestDetailsPopup";
	 /**VIEWALLREQUESTHISTORY*/
	 String VIEWALLREQUESTHISTORY ="lgsHistoryDetails/allRequestHistoryHome";
	 /**SERVICEREQUESTLISTPAGE*/
	 String SERVICEREQUESTLISTPAGE ="serviceRequest/serviceRequestListPage";
	 /**LGSHISTORYDETAILS*/
	 String LGSHISTORYDETAILS = "lgsHistoryDetails/";
	 /**SUPPLYREQUESTDETAILS*/
	 String SUPPLYREQUESTDETAILS = "lgsHistoryDetails/supplyRequestDetails";
	 /**RETURNSREQUESTDETAILS*/
	 String RETURNSREQUESTDETAILS = "lgsHistoryDetails/returnsRequestDetails";
	 /**SERVICEREQUESTSPRINT*/
	 String SERVICEREQUESTSPRINT = "lgsHistoryDetails/serviceRequestsPrint";

	//Request Attributes & Parameters
	 /**TIMEZONEOFFSET_POPUP*/
	 String TIMEZONEOFFSET_POPUP = "timeZoneOffsetPopup";
	 /**SRAREA*/
	 String SRAREA = "srArea";
	 /**REQUESTFORM_POPUP*/
	 String REQUESTFORM_POPUP = "requestFormPopup";
	 /**REQUESTFORM*/
	 String REQUESTFORM = "requestForm";
	 /**DOWNLOADHISTORYLIST*/
	 String DOWNLOADHISTORYLIST = "downloadHistoryList";
	 /**REQUESTYPEPAGE*/
	 String REQUESTYPEPAGE = "requestTypePage";
	 /**SERVICEREQUESTS*/
	 String SERVICEREQUESTS = "SERVICE_REQUESTS";
	 /**RETRIEVECMHISTORYLIST*/
	 String RETRIEVECMHISTORYLIST = "retrieveCMHistoryList";
	 /**REQUESTTYPELOVMAP*/
	 String REQUESTTYPELOVMAP = "requestTypeLOVMap";
	 /**SOURCEPAGE*/
	 String SOURCEPAGE = "sourcePage";
	 /**ASSETROWID*/
	// String ASSETROWID = "assetRowId";
	 /**ACCOUNTROWID*/
	 String ACCOUNTROWID = "accountRowId";
	 /**MDMLEVELFROMDETAILS*/
	 String MDMLEVELFROMDETAILS = "mdmLevelFromDetails";
	 /**REQUESTTYPESTR*/
	 String REQUESTTYPESTR = "requestTypeStr";
	 /**PAGEVIEW*/
	 String PAGEVIEW = "pageView";
	 /**UPDATETYPEFLAG*/
	 String UPDATETYPEFLAG = "updateTypeFlag";
	 /**USERSEGMENT*/
	 String USERSEGMENT = "userSegment";
	 /**RETRIEVESRHISTORYLIST*/
	 String RETRIEVESRHISTORYLIST = "retrieveSRHistoryListXML";
	 /**RETRIEVEASSOC_SRHISTORYLIST*/
	 String RETRIEVEASSOC_SRHISTORYLIST = "retrieveAssociatedSRHistoryListXML";
	 /**SHIPMENTFORM*/
	 String SHIPMENTFORM = "shipmentForm";
	 /**INPROCESSFORM*/
	 String INPROCESSFORM = "inProcessForm";
	 /**SHIPMENT*/
	 String SHIPMENT = "shipment";
	 /**ATTACHMENT_NAME*/
	 String ATTACHMENT_NAME = "attachmentName";
	 /**DISP_ATTACHMENT_NAME*/
	 String DISP_ATTACHMENT_NAME = "dispAttachmentName";
	 /**FILE_EXTENSION*/
	 String FILE_EXTENSION = "fileExtension";
	 /**IDENTIFIER*/
	 String IDENTIFIER = "identifier";
	 /**CONTROLPANELURL*/
	 String CONTROLPANELURL = "controlPanelURL";
	 /**PAGENAME*/
	 String PAGENAME = "pageName";
	 /**REQUESTDETAILS*/
	 String REQUESTDETAILS = "Request Details";
	 /**CONTROLPANELPAGE*/
	 String CONTROLPANELPAGE = "controlPanelPage";
	 /**SUBMITTED*/
	 String SUBMITTED = "Submitted";
	 /**INPROGRESS*/
	 String INPROGRESS = "In Progess";
	 /**INPROCESS*/
	 String INPROCESS = "Inprocess";
	 /**COMPLETED*/
	 String COMPLETED = "Completed";
	 /**REQNO*/
	 String REQNO = "requestNumber";
	 /**DOWNLOADTYPE*/
	 String DOWNLOADTYPE = "downloadType";
	 /**PAGETYPE*/
	 String PAGETYPE = "pageType";
	
	 /**LEXMARK_RECOMMENTED*/
	 String LEXMARK_RECOMMENTED = "Lexmark Recommended";
	 /**STATUS_ATHORIZED*/
	 String STATUS_ATHORIZED = "Authorized";
	
	
	//Added for JAN release Cancel request
	 /**SUPPLIES_REQ*/
	 String SUPPLIES_REQ = "Supplies";
	
	//Added for JAN release DeviceFinder back
	 /**BACKFROMHISTORY*/
	 String BACKFROMHISTORY = "backHistory";
	 /**LINKCLICKED*/
	 String LINKCLICKED = "lclick";
	 /**MODELLINKCLICKED*/
	 String MODELLINKCLICKED ="linkClicked";
	 
	 /**CATALOG_ENTITLEMENT_FLAG*/
	 String CATALOG_ENTITLEMENT_FLAG = "CATALOG_ENTITLEMENT";
	 /**ASSET_ENTITLEMENT_FLAG*/
	 String ASSET_ENTITLEMENT_FLAG = "ASSET_ENTITLEMENT";
	 
	 /**ISPARTNERPORTAL*/
	 String ISPARTNERPORTAL="/group/partner-portal";
	
	 /**REQUESTEXPEDITE*/
	 String REQUESTEXPEDITE="requestExpedite";
	
	 /**ACCOUNTLISTMAP*/
	 String ACCOUNTLISTMAP="accountListMap";
	 /**ACCOUNTLIST*/
	 String ACCOUNTLIST="accountList";
	 /**SIEBEL_ENTITLEMENT_ACCOUNTS_ASSET_AND_CATALOG*/
	 String SIEBEL_ENTITLEMENT_ACCOUNTS_ASSET_AND_CATALOG="SIEBEL_ENTITLEMENT_ACCOUNTS_ASSET_AND_CATALOG";
	
	 /**GRID_CREATION_IDS_FOR_HISTORY*/
	 String[] GRID_CREATION_IDS_FOR_HISTORY=new String[] {"allRequestHistory","changeRequestHistory","supplyRequestHistory","serviceRequestListGrid","hardwareRequestListGrid"};
	 /**GRIDTYPE*/
	 String GRIDTYPE="gridType";

	//Added for Asset Acceptance
	 /**ASSET_ACCEPTANCE*/
	 String ASSET_ACCEPTANCE = "AssetAcceptance"; 
	 /**ATTACHMENT_MAX_SIZE_ACCEPT*/
	String ATTACHMENT_MAX_SIZE_ACCEPT = "attachment.acceptmaxsize";
	 /**ATTACHMENT_MAX_COUNT_ACCEPT*/
	String ATTACHMENT_MAX_COUNT_ACCEPT = "attachment.acceptmaxcount";
	
	//Added for MPS 2.1 Wave 1 Consumables change
	 /**CATCURRDETAILS*/
	 String CATCURRDETAILS = "catalogCurrentDetails";
	  /**CATFINALFLAGS*/
	 String CATFINALFLAGS = "catalogFinalFlags";
	
	 /**ASSET_PART_LIST*/
	 String ASSET_PART_LIST = "assetPartList";
	  /**ASSET_PART_LIST_FILTERED*/
	 String ASSET_PART_LIST_FILTERED = "assetPartListFiltered";
	
	 /**PAYMENT_TYPE_PAY_NOW*/
	 String PAYMENT_TYPE_PAY_NOW = "Pay Now";
	  /**PAYMENT_TYPE_PAY_LATER*/
	 String PAYMENT_TYPE_PAY_LATER = "Pay Later";
	  /**PAYMENT_TYPE_UBB*/
	 String PAYMENT_TYPE_UBB = "UBB";
	
	 /**ADDRESSCLEANSEFIELDS_OUTPUT*/
	 String ADDRESSCLEANSEFIELDS_OUTPUT[]={
		"county","officeNumber",
		"stateCode","stateFullName","district",
		"region","latitude","longitude",
		"countryISOCode","savedErrorMessage"
		};
	 /**ADDRESSCLEANSEFIELDS_INPUT*/	
	 String ADDRESSCLEANSEFIELDS_INPUT[]={
			 "storeFrontName","addressLine1","addressLine2",
				"city","country","state",
				"postalCode","officeNumber"
		};
	 /**ADDRESSCLEANSEFIELDS_DISPLAY HTML*/	
	 String DISPLAYHTML="displayHTML";
	 /********for ACCOUNT_MANAGEMENT**********/
	 String ACCOUNT_MANAGEMENT = "Account Management";
	 
	 /********for SHOW_ASSET_ACCEPTANCE**********/
	 String SHOW_ASSET_ACCEPTANCE = "Show Asset Acceptance";
	 /********for ONLY_ASSET_ACCEPTANCE**********/
	 String ONLY_ASSET_ACCEPTANCE = "Only Asset Acceptance";
}
