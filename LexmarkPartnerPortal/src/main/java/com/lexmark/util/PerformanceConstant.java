package com.lexmark.util;
public interface PerformanceConstant {
	/*********Added for performance log***********/	
	String DEBUG_TIME_LIMIT = "performance.debug.level";
	String ERROR_TIME_LIMIT = "performance.error.level";
	String PERF_DB_FLAG = "performance.db.log";
	String PERF_DEBUG_CONTRACT = "performance.debug.contractFlag";
	String PERF_ERROR_CONTRACT = "performance.error.contractFlag";
	String PERF_DEBUG = "performance.debug.log";
	String PERF_ERROR = "performance.error.log";
	String PERF_FILE_PATH = "/performance.properties";
	//Added for Performance testing source
	String SYSTEM_WEBMETHODS = "WEBMETHODS";
	String SYSTEM_SIEBEL = "Siebel";
	String SYSTEM_AMIND = "aMind";
	String SYSTEM_SAP = "SAP";
	String SYSTEM_PORTALIMAGEDB = "PortalImageDB";
	
	//Added for Performance testing message
	
	String PARTNER_REQUEST_GRID = "** MPS PERFORMANCE TESTING RETRIEVE Request Grid ==>:";
	String PARTNER_CLAIM_DETAIL = "** MPS PERFORMANCE TESTING RETRIEVE Claim Details Page ==>: ";
	String PARTNER_ASSIGN_TO_ME = "** MPS PERFORMANCE TESTING for Assigning Technician (Assign To Me) ==>:";
	String SERVICE_REQUEST_UPDATE_PAGE = "** MPS PERFORMANCE TESTING RETRIEVE Service Request Update Page ==>:";
	String SERVICE_REQUEST_CLOSE_OUT = "** MPS PERFORMANCE TESTING RETRIEVE Service Request CloseOut Page ==>:";
	String PARTNER_SERVICE_REQUEST_DETAILS = "** MPS PERFORMANCE TESTING RETRIEVE Service Request Detail Page";
	String PARTNER_WEBMETHOD_MASS_UPLOAD = "** MPS PERFORMANCE TESTING WEBMETHODS CALL MASS UPLOAD SR CREATE ==>:";
	String PARTNER_ATTACHMENT_UPLOAD = "** MPS PERFORMANCE TESTING ATTACHMENT UPLOAD ==>:";
	
	String MANAGEADDRESSES_MSG_CREATECMREQUESTSERVICE = "MPS PERFORMANCE TESTING FOR CREATE ADDRESS SR";
	String MSG_ATTACHMENT = "MPS PERFORMANCE TESTING FOR UPLOAD ATTACHMENTS";
	
	String MANAGEASSETS_MSG_CREATECMREQUESTSERVICE = "MPS PERFORMANCE TESTING WEBMETHODS CALL FOR CREATE CM ASSET SR";
	String MANAGEASSETS_MSG_RETRIEVEASSETDETAIL = "MPS PERFORMANCE TESTING RETRIEVE ASSET FOR CHANGE AND DECOMM";
	String MANAGEASSETS_MSG_RETRIEVEPRODUCTIMAGEURL = "MPS PERFORMANCE TESTING RETRIEVE IMAGE FOR ASSET";
	
	String MANAGECHLOTHERS_MSG_MANAGECHLOTHERSSERVICE = "MPS PERFORMANCE TESTING WEBMETHODS CALL FOR CREATE CHLOTHERS SR";
	
	String MANAGECONTACTS_MSG_CREATECMREQUESTSERVICE_CONTACT = "MPS PERFORMANCE TESTING WEBMETHODS CALL FOR CREATE CONTACT SR";
	String MANAGECONTACTS_MSG_ADDANDUPDATECONTACTSERVICE = "MPS PERFORMANCE TESTING WEBMETHODS CALL FOR ADD AND UPDATE CONTACT SERVICE";
	
	String MANAGETEMPLATE_MSG_CREATECHANGEMANAGEMENTSERVICEREQUEST = "MPS PERFORMANCE TESTING FOR CREATE MULTIPLE SR";
	
	String COMMON_MSG_RETRIEVEALLDEVICELIST = "MPS PERFORMANCE TESTING FOR RETRIEVE ALL DEVICE LIST";
	String COMMON_MSG_RETRIEVEADDRESSLIST = "MPS PERFORMANCE TESTING FOR RETRIEVE ADDRESS LIST";
	String COMMON_MSG_UPDATEUSERFAVORITEADDRESS = "MPS PERFORMANCE TESTING FOR SETTING ADDRESS FAVOURITE/UNFAVORITE";
	String COMMON_MSG_RETRIEVEENTITELMENTFLAGS = "MPS PERFORMANCE TESTING FOR RETRIEVE ENTITLEMENT FLAGS";
	String COMMON_MSG_RETRIEVEDEVICEDETAIL = "MPS PERFORMANCE TESTING FOR RETRIEVE ASSET DETAIL";
	String COMMON_MSG_RETRIEVECATALOGAGREEMENTLIST = "MPS PERFORMANCE TESTING FOR RETRIEVE CATALOG AGREEMENT LIST";
	String COMMON_MSG_RETRIEVESIEBELACCOUNTLIST = "MPS PERFORMANCE TESTING FOR RETRIEVE ACCOUNT LIST";
	String COMMON_MSG_UPLOADATTACHMENTS = "MPS PERFORMANCE TESTING FOR UPLOAD ATTACHMENT";
	
	String CONTACT_MSG_RETRIEVECONTACTLIST = "MPS PERFORMANCE FOR TESTING RETRIEVE CONTACT LIST";
	String CONTACT_MSG_UPDATEUSERFAVORITECONTACT = "MPS PERFORMANCE TESTING FOR; UPDATE USER FAVOURITE CONTACT";
	
	String HISTORY_MSG_RETRIEVEREQUESTLIST = "MPS PERFORMANCE TESTING FOR RETRIEVE HISTORYLIST";
	String HISTORY_MSG_DOWNLOADHISTORYLIST = "MPS PERFORMANCE TESTING FOR DOWNLOADHISTORYLIST";
	String HISTORY_MSG_RETRIEVECMDETAIL = "MPS PERFORMANCE TESTING FOR RETRIEVE CM Detail";
	String HISTORY_MSG_RETRIEVESUPPLYREQUESTDETAIL = "MPS PERFORMANCE TESTING FOR RETRIEVE Supply Request Detail";
	String HISTORY_MSG_DOWNLOADATTACHMENT = "MPS PERFORMANCE TESTING DOWNLOAD ATTACHMENT";
	String HISTORY_MSG_SERVICEREQUESTSERVICE = "MPS PERFORMANCE TESTING RETRIEVE SERVICE REQUEST HISTORY LIST";
	String HISTORY_MSG_RETRIEVEASSOCIATEDSERVICEREQUESTLIST = "MPS PERFORMANCE TESTING RETRIEVE ASSOCIATED SERVICE REQUEST LIST";
	
	
	String CUSTOMERINVOICE_MSG_RETRIEVEACCOUNTRECEIVABLELIST = "MPS PERFORMANCE TESTING RETRIEVE ACCOUNT RECEIVABLE LIST";
	String CUSTOMERINVOICE_MSG_RETRIEVEINVOICELIST = "MPS PERFORMANCE TESTING SAP CALL RETRIEVE INVOICE LIST";
	
	String ORDERHARDWARE_MSG_RETRIEVEBILLTOADDRESSLIST = "MPS PERFORMANCE TESTING RETRIEVE BILL TO ADDRESS LIST";
	String ORDERHARDWARE_MSG_RETRIEVECATALOGFIELDLIST = "MPS PERFORMANCE TESTING RETRIEVE CATALOG FIELD LIST";
	String ORDERHARDWARE_MSG_RETRIEVEHARDWARELIST = "MPS PERFORMANCE TESTING RETRIEVE HARDWARE PART LIST";
	String ORDERHARDWARE_MSG_RETRIEVEPRICELIST = "MPS PERFORMANCE TESTING SAP CALL RETRIEVE PRICE LIST";
	String ORDERHARDWARE_MSG_RETRIEVESIEBELACCOUNTLIST = "MPS PERFORMANCE TESTING RETRIEVE SIEBEL ACCOUNT LIST";
	String ORDERHARDWARE_MSG_RETRIEVETAXLIST = "MPS PERFORMANCE TESTING CALL RETRIEVE TAX LIST";
	String ORDERHARDWARE_MSG_CREATEHARDWAREREQUEST = "MPS PERFORMANCE TESTING CREATE HARDWARE REQUEST";
	String ORDERHARDWARE_MSG_RETRIEVEPAYMENTTYPELIST = "MPS PERFORMANCE TESTING RETRIEVE PAYMENT TYPE LIST";
	String ORDERHARDWARE_MSG_RETRIEVESUPPLYREQUESTDETAIL = "MPS PERFORMANCE TESTING HARDWARE ORDER DETAILS";
	String ORDERHARDWARE_MSG_RETRIEVECATALOGAGREEMENTLIST = "MPS PERFORMANCE TESTING RETRIEVE CATALOG AGREEMENT LIST";
	
	String MANAGERETURNS_MSG_CREATECHANGEMANAGEMENTSERVICEREQUEST = "MPS PERFORMANCE TESTING MANAGE RETURNS SR";
	
	String DEVICEFINDER_MSG_RETRIEVEALLDEVICELIST = "MPS PERFORMANCE TESTING FOR RETRIEVE ALL DEVICE LIST FOR DEVICE FINDER";
	String DEVICEFINDER_MSG_RETRIEVEDEVICEDETAIL = "MPS PERFORMANCE TESTING FOR RETRIEVE ALL DEVICE DETAIL";
	String DEVICEFINDER_MSG_RETRIEVEPRODUCTIMAGEURL = "MPS PERFORMANCE TESTING FOR RETRIEVE PRODUCT IMAGE URL";
	String DEVICEFINDER_MSG_RETRIEVESIEBELLOVLIST = "MPS PERFORMANCE TESTING FOR RETRIEVE SIEBEL LOV LIST FOR REQUEST TYPE AND STATUS";
	String DEVICEFINDER_MSG_RETRIEVEREQUESTLIST = "MPS PERFORMANCE TESTING FOR RETRIEVE REQUEST LIST";
	String DEVICEFINDER_MSG_UPDATEUSERFAVORITECONTACT = "MPS PERFORMANCE TESTING TO UPDATE USER FAVOURITE CONTRACT";
	String DEVICEFINDER_MSG_UPDATEUSERFAVORITEADDRESS = "MPS PERFORMANCE TESTING TO UPDATE USER FAVOURITE ADDRESS";
	
	String ORDERSUPPLIES_MSG_RETRIEVEORDERHISTORY = "MPS PERFORMANCE TESTING FOR RETRIEVE ORDER HISTORY";
	String ORDERSUPPLIES_MSG_RETRIEVEDEVICEDETAIL = "MPS PERFORMANCE TESTING FOR RETRIEVE  DEVICE DETAILS";
	
	String ORDERSUPPLIES_MSG_RETRIEVESUPPLYREQUESTDETAIL = "MPS PERFORMANCE TESTING FOR RETRIEVE  ASSET  DETAIL FOR DRAFT";
	String ORDERSUPPLIES_MSG_RETRIEVEDEVICELIST = "MPS PERFORMANCE TESTING FOR RETRIEVE  DEVICE LIST";
	String ORDERSUPPLIES_MSG_RETRIEVETAXLIST = "MPS PERFORMANCE TESTING FOR RETRIEVE PART TAX";
	String ORDERSUPPLIES_MSG_CREATEDRAFTSR = "MPS PERFORMANCE TESTING FOR CREATE DRAFT SR";
	String ORDERSUPPLIES_MSG_UPLOADATTACHMENTS = "MPS PERFORMANCE TESTING FOR UPLOADING ATTACHMENT FOR DRAFT";
	String ORDERSUPPLIES_MSG_CREATEASSETORDER = "MPS PERFORMANCE TESTING FOR CREATING SR FOR ASSET ORDER";
	String ORDERSUPPLIES_MSG_RETRIEVETONERPRICELIST = "MPS PERFORMANCE TESTING SAP CALL TO RETRIVE TONER PRICE LIST (Consumable Purchase)";
	String ORDERSUPPLIES_MSG_RETRIEVEPRICELIST = "MPS PERFORMANCE TESTING SAP CALL TO RETRIVE TONER PRICE LIST (Ship and Bill )";
	String ORDERSUPPLIES_MSG_RETRIEVESOLDTOLIST = "MPS PERFORMANCE TESTING RETRIEVE SOLDTO LIST";
	String ORDERSUPPLIES_MSG_RETRIEVEBILLTOADDRESSLIST = "MPS PERFORMANCE TESTING RETRIEVE BILLTO ADDRESS LIST";
	
	String ORDERSUPPLIESCATALOG_MSG_RETRIEVESUPPLYREQUESTDETAIL = "MPS PERFORMANCE TESTING FOR RETRIEVE DEVICE DETAILS FOR DRAFT";
	String ORDERSUPPLIES_MSG_ORDERSUPPLIESCATALOGSERVICE = "MPS PERFORMANCE TESTING FOR RETRIEVE CATALOG FIELD LIST (PRODUCT TYPE)";
	String ORDERSUPPLIES_MSG_RETRIEVECATALOGFIELDLIST = "MPS PERFORMANCE TESTING FOR RETRIEVE CATALOG FIELD LIST (PRODUCT MODEL)";
	String ORDERSUPPLIES_MSG_RETRIEVECATALOGFIELDLIST_PT = "MPS PERFORMANCE TESTING FOR RETRIEVE CATALOG FIELD LIST (PART TYPE)";
	String ORDERSUPPLIES_MSG_RETRIEVECATALOGLISTWITHCONTRACTNUMBER = "MPS PERFORMANCE TESTING FOR RETRIEVE CATALOG LIST WITH CONTRACT NO (FOR SPLITTER FLAG IS TRUE)";
	String ORDERSUPPLIES_MSG_RETRIEVECATALOGLIST = "MPS PERFORMANCE TESTING FOR RETRIEVE CATALOG LIST (FOR SPLITTER FLAG IS FALSE)";
	String ORDERSUPPLIESCATALOG_MSG_RETRIEVETAXLIST = "MPS PERFORMANCE TESTING SAP TAX CALL";
	String ORDERSUPPLIES_MSG_CREATECONSUMABLESERVICEREQUEST_SR ="MPS PERFORMANCE TESTING CONSUMABLES SERVICE REQUEST (DRAFT SR)";
	String ORDERSUPPLIES_MSG_CREATECONSUMABLESERVICEREQUEST = "MPS PERFORMANCE TESTING WEBMETHODS CALL CREATE CONSUMABLE SERVICE REQUEST";
	
	String SERVICEREQUEST_MSG_RETRIEVEGLOBALASSETLIST ="MPS PERFORMANCE TO RETRIEVE GLOBAL ASSET LIST";
	String SERVICEREQUEST_MSG_VALIDATEMANUALASSET = "MPS PERFORMANCE TESTING FOR VALIDATING MANUAL ASSET";
	String SERVICEREQUEST_MSG_RETRIEVESIEBELLOVLISTRT = "MPS PERFORMANCE TO RETRIEVE SIEBEL LOV LIST FOR REQUEST TYPE";
	String SERVICEREQUEST_MSG_RETRIEVESIEBELLOVLISTSTATUS = "MPS PERFORMANCE TO RETRIEVE SIEBEL LOV LIST FOR REQUEST Status";
	
	String SHAREDPORTLET_MSG_CREATESERVICEREQUEST = "MPS PERFORMANCE CREATE SERVICE REQUEST";
	String SHAREDPORTLET_MSG_RETRIEVEREQUESTLIST = "MPS PERFORMANCE RETRIEVE SERVICE REQUEST LIST";
	String SHAREDPORTLET_MSG_UPDATEUSERFAVORITEASSET = "MPS PERFORMANCE UPADTE USER FAVOURITE ASSET";
	String SHAREDPORTLET_MSG_RETRIEVESERVICEREQUESTHISTORYLIST = "MPS PERFORMANCE RETRIEVE SERVICE REQUEST HISTORY LIST FOR DRILL DOWN";
	String SHAREDPORTLET_MSG_RETRIEVEASSETREPORTINGHIERARCHY = "MPS PERFORMANCE RETRIEVE CHL TREE XML";
	String SHAREDPORTLET_MSG_RETRIEVEASSETLOCATIONS = "MPS PERFORMANCE RETRIEVE DEVICE LOCATION TREE" ;
	String SHAREDPORTLET_MSG_RETRIEVEREQUESTLOCATIONS = "MPS PERFORMANCE RETRIEVE DEVICE LOCATIONS";
	String ASSETACCEPTANCE_MSG_CREATECHANGEMANAGEMENTSERVICEREQUEST ="MPS PERFORMANCE TESTING CREATE ASSET ACCEPTANCE SR";
    String MANAGECHLOTHERS_MSG_CREATECHANGEMANAGEMENTSERVICEREQUEST="MPS PERFORMANCE TESTING CREATE CHL / OTHERS SR";
    String MANAGECONTACT_MSG_CREATECHANGEMANAGEMENTSERVICEREQUEST="MPS PERFORMANCE TESTING CREATE CONTACT SERVICE CALL";
    String CREATECONSUMABLES_MSG_UPDATECONSUMABLESSERVICEREQUEST="MPS PERFORMANCE TESTING UPDATE CONSUMABLE SR";
    String CREATECONSUMABLES_MSG_CREATECONSUMABLESSERVICEREQUEST="MPS PERFORMANCE TESTING CREATE CONSUMABLE SR";
    String CREATEHARDWARE_MSG_UPDATESELLABLEITEMSSERVICEREQUEST="MPS PERFORMANCE TESTING UPDATE HARDWARE SR";
    String CREATEHARDWARE_MSG_CREATESELLABLEITEMSSERVICEREQUEST="MPS PERFORMANCE TESTING CREATE HARDWARE SR";
    String CREATESERVICEREQUEST_MSG_CREATESERVICEREQUEST="MPS PERFORMANCE TESTING CREATE BREAKFIX SR";
    String CUSTOMERINVOICE_MSG_ZGRFC_MPS_INVOICE_LIST="MPS PERFORMANCE TESTING CUSTOMER INVOICE SERVICE CALL";
    String PROCESSCUSTOMERCONTACT_MSG_PROCESSCUSTOMERCONTACT="MPS PERFORMANCE TESTING PROCESS CUSTOMER CONTACT SERVICE CALL";
    String RETURNORDER_MSG_CREATECONSUMABLESSERVICEREQUEST="MPS PERFORMANCE TESTING CREATE RETURN ORDER SR";
    
    //For Partner Portal
    String MSG_RETRIEVE_PARTNERACCOUNTLIST="MPS PERFORMANCE TESTING TO RETRIVE PARTNER ACCOUNT LIST";
    String MSG_RETRIEVE_VENDORLIST="MPS PERFORMANCE TESTING TO RETRIVE VENDOR LIST";
    String MSG_RETRIEVE_INVOICELIST="MPS PERFORMANCE TESTING TO RETRIVE INVOICE LIST";
    String MSG_RETRIEVE_SRLISTFORAR="MPS PERFORMANCE TESTING FOR RETRIEVE SR LIST FOR AR";
    String MSG_RETRIEVE_SRLISTFORAP="MPS PERFORMANCE TESTING FOR RETRIEVE SR LIST FOR AP";
    String MSG_RETRIEVE_ACCOUNTPAYABLEDETAIL="MPS PERFORMANCE TESTING FOR RETRIEVE AR LIST";
    String MSG_RETRIEVE_ORDERLIST="MPS PERFORMANCE TESTING RETRIEVE ORDER LIST";
    String MSG_RETRIEVE_ORDERDETAILS="MPS PERFORMANCE TESTING RETRIEVE ORDER DETAILS";
    String MSG_ACCEPTSERVICEORDERREQUEST="MPS PERFORMANCE TESTING ACCEPT ORDER REQUEST";
    String MSG_RETRIEVEMASSUPLOADTEMPLATELIST="MPS PERFORMANCE TESTING RETRIEVE MASS UPLOAD TEMPLATE LIST";
    String MSG_UPDATEORDER="MPS PERFORMANCE TESTING UPDATE ORDER";
    String MSG_RETRIEVE_ACTIVITYDETAILS="MPS PERFORMANCE TESTING RETRIEVE ACTIVITY DETAILS";
    String MSG_SUBMITACTIVITYDEBRIEF="MPS PERFORMANCE TESTING TO SUBMIT ACTIVITY DEBRIEF";
    String MSG_SUBMITCLAIMDEBRIEF="MPS PERFORMANCE TESTING TO SUBMIT CLAIM DEBRIEF";
    String MSG_RETRIEVE_FRUPARTLIST="MPS PERFORMANCE TESTING TO RETRIVE FRU PART LIST";
    String MSG_SAVEDEBRIEF="MPS PERFORMANCE TESTING TO SAVE DEBRIEF";
    String MSG_CREATE_CHILD_SR = "MPS PERFORMANCE TESTING TO CREATE CHILD SR";
    
    

}
