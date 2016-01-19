package com.lexmark.services.util;
public interface ChangeMgmtConstant {
	/*********FALSE  ***********/
	String FALSE = "false";

     /*********TRUE  ***********/

	String TRUE = "true";
	
     /*********userAccessMap  ***********/

	String USERACCESSMAPATTRIBUTE = "userAccessMap" ;
	
	/*********Account name added for MPS Phase 2  ***********/
	String[] ALLHISTORYCOLUMNS = new String[] { "serviceRequestNumber", "serviceRequestDate",
			"serviceRequestType", "area","serviceRequestStatus", "asset.serialNumber","costCenter","accountName", "primaryContact.firstName",
			"primaryContact.lastName", "primaryContact.emailAddress", "primaryContact.workPhone", "requestor.firstName", "requestor.lastName", 
			"requestor.emailAddress", "requestor.workPhone"};
	  // *office number , county, district added for MPS Phase 2
	  //coveredService has been added for CI Defect 11270

         /*********for SUPPLYHISTORYCOLUMNS  ***********/

	String[] SUPPLYHISTORYCOLUMNS = new String[] {"expediteOrder","", "serviceRequestNumber", "serviceRequestDate",
			 "area","subArea", "asset.serialNumber","asset.deviceTag","serviceRequestStatus","productModel","poNumber","costCenter",
			"accountName","serviceAddress.addressName", "serviceAddress.storeFrontName","serviceAddress.addressLine1","serviceAddress.officeNumber",
			"serviceAddress.city","serviceAddress.state","serviceAddress.province","serviceAddress.county","serviceAddress.district", 
			"serviceAddress.country","serviceAddress.postalCode", 
			"primaryContact.firstName",	"primaryContact.lastName", "primaryContact.emailAddress", "primaryContact.workPhone", 
			"requestor.firstName", "requestor.lastName","requestor.emailAddress", "requestor.workPhone","requestStatus","subStatus","severity","agreementName","agreementNumber"};
	
	/*********Changes MPS 2.1for headers for supply request  ***********/
	String[] SUPPLYHISTORYPDFCOLUMNS = new String[] {"expediteOrder","serviceRequestNumber", "serviceRequestDate",
			 "area","subArea", "asset.serialNumber","asset.deviceTag","serviceRequestStatus","asset.modelNumber","poNumber","costCenter","accountName",
			"serviceAddress.addressName", "serviceAddress.storeFrontName","serviceAddress.addressLine1","serviceAddress.officeNumber",
			"serviceAddress.city","serviceAddress.state","serviceAddress.province","serviceAddress.county","serviceAddress.district",
			"serviceAddress.country","serviceAddress.postalCode", 
			"primaryContact.firstName",	"primaryContact.lastName", "primaryContact.emailAddress", "primaryContact.workPhone", 
			"requestor.firstName", "requestor.lastName","requestor.emailAddress", "requestor.workPhone","requestStatus","subStatus","severity","agreementName","agreementNumber"};

	//Ends Changes MPS 2.1for headers for supply request

	
         


       /*********for CHANGEHISTORYCOLUMNS ***********/
	String[] CHANGEHISTORYCOLUMNS = new String[] { "serviceRequestNumber", "serviceRequestDate",
			 "area", "subArea","asset.serialNumber","asset.deviceTag", "serviceRequestStatus","asset.modelNumber",
			"referenceNumber","costCenter","accountName",
			"serviceAddress.addressName", "serviceAddress.storeFrontName","serviceAddress.addressLine1","serviceAddress.officeNumber",
			"serviceAddress.city","serviceAddress.state","serviceAddress.province","serviceAddress.county","serviceAddress.district",
			"serviceAddress.country","serviceAddress.postalCode",
			"primaryContact.firstName","primaryContact.lastName", "primaryContact.emailAddress", "primaryContact.workPhone",
			"requestor.firstName", "requestor.lastName","requestor.emailAddress", "requestor.workPhone","coveredService","requestStatus","subStatus","severity","projectName","projectPhase","agreementName","agreementNumber"};
	//coveredService has been added for CI Defect 11270
	//Added for CI 14-02-01
	/*********for CHANGEHISTORYCOLUMNS ***********/
	String[] CHANGEHISTORYPDFCOLUMNS = new String[] { "serviceRequestNumber", "serviceRequestDate",
			 "area.value", "subArea.value","asset.serialNumber", "asset.deviceTag","serviceRequestStatus","asset.modelNumber",
			"referenceNumber","costCenter","accountName",
			"serviceAddress.addressName", "serviceAddress.storeFrontName","serviceAddress.addressLine1","serviceAddress.officeNumber",
			"serviceAddress.city","serviceAddress.state","serviceAddress.province","serviceAddress.county","serviceAddress.district",
			"serviceAddress.country","serviceAddress.postalCode",
			"primaryContact.firstName","primaryContact.lastName", "primaryContact.emailAddress", "primaryContact.workPhone",
			"requestor.firstName", "requestor.lastName","requestor.emailAddress", "requestor.workPhone","coveredService","requestStatus","subStatus","severity","projectName","projectPhase","agreementName","agreementNumber"};
	
	
	/*********Added for Device Management MPS	  ***********/
	String [] BREAKFIXHISTCOLUMNS = new String[]{"serviceRequestNumber","serviceRequestNumber","serviceRequestStatusDate",
			"asset.serialNumber", "asset.productLine","problemDescription", "resolutionCode", "serviceRequestStatus","accountName",
			"serviceAddress.addressName","serviceAddress.officeNumber","serviceAddress.city",
			"serviceAddress.state","serviceAddress.province","serviceAddress.county",
			"serviceAddress.district","serviceAddress.postalCode","serviceAddress.country","custRefNumber",
			"primaryContact.firstName","primaryContact.lastName","primaryContact.emailAddress","primaryContact.workPhone",
			"contractType", "asset.deviceTag", "asset.hostName", "costCenter"};
	
	
	/*********CI-6 Changes Start Partha***********/
	String [] SERVICEREQUESTSCOLUMNS = new String[]{"serviceRequestNumber","serviceRequestDate",
			"asset.serialNumber", "productModel","problemDescription","resolutionCode",
			"serviceAddress.addressName","serviceRequestStatus","serviceAddress.city","serviceAddress.state",
			"serviceAddress.province","serviceAddress.county", "serviceAddress.district","serviceAddress.postalCode","serviceAddress.country","helpDeskReferenceNumber","primaryContact.firstName",
			"primaryContact.lastName","primaryContact.emailAddress","primaryContact.workPhone","assetType","asset.deviceTag", "asset.hostName", "asset.assetCostCenter","requestStatus","subStatus","severity"};
	//CI-6 Changes End Partha
	/*********CI-14-02-01 Changes Start***********/
	/*********Changed for CI Defect 10242***********/
	String [] SERVICEHISTORYPDFCOLUMNS = new String[]{"serviceRequestNumber","serviceRequestDate","asset.serialNumber", 
			"asset.modelNumber","problemDescription","resolutionCode", 
			"serviceRequestStatus","serviceAddress.addressName", 
			"serviceAddress.officeNumber","serviceAddress.city","serviceAddress.state",
			"serviceAddress.province","serviceAddress.county","serviceAddress.district",
			"serviceAddress.postalCode","serviceAddress.country","custRefNumber", 
			"primaryContact.firstName","primaryContact.lastName","primaryContact.emailAddress", 
			"primaryContact.workPhone","contractType","asset.deviceTag","asset.hostName","costCenter","accountName","requestStatus","subStatus","severity"};
	
	
	/*********changes for hardware for mps2.1***********/
	//officeNumber added for CI Defect #9183
	String [] HARDWAREQUESTSCOLUMNS = new String[]{"","serviceRequestNumber","serviceRequestDate","area","subArea","serviceRequestStatus","poNumber","costCenter","accountName",
				"serviceAddress.addressName","serviceAddress.officeNumber","serviceAddress.storeFrontName","serviceAddress.addressLine1","serviceAddress.city","serviceAddress.state",
				"serviceAddress.province","serviceAddress.county","serviceAddress.district","serviceAddress.country","serviceAddress.postalCode","primaryContact.firstName","primaryContact.lastName","primaryContact.emailAddress",
				"primaryContact.workPhone","requestor.firstName","requestor.lastName","requestor.emailAddress","requestor.workPhone","projectName","projectPhase"};
	
	/*********Added for HARDWAREQUESTSCOLUMNSPDF 	  ***********/
	//officeNumber added for CI Defect #9183
	String [] HARDWAREQUESTSCOLUMNSPDF = new String[]{"serviceRequestNumber","serviceRequestDate","area","subArea","serviceRequestStatus","poNumber","costCenter","accountName",
			"serviceAddress.addressName","serviceAddress.officeNumber","serviceAddress.storeFrontName","serviceAddress.addressLine1","serviceAddress.city","serviceAddress.state",
			"serviceAddress.province","serviceAddress.county","serviceAddress.district","serviceAddress.country","serviceAddress.postalCode","primaryContact.firstName","primaryContact.lastName","primaryContact.emailAddress",
			"primaryContact.workPhone","requestor.firstName","requestor.lastName","requestor.emailAddress","requestor.workPhone","projectName","projectPhase"};
	//end
	
	/*********Added for MYREQUESTLIST ***********/
	String SEARCHTYPE_MYSRLIST = "MYREQUESTLIST";

	/*********Added for MYBOOKMARKEDASSETSLIST ***********/
	String SEARCHTYPE_MYBOOKMARKEDASSETLIST = "MYBOOKMARKEDASSETSLIST";

	/*********Added for MYDRAFTREQUESTSLIST***********/
	String SEARCHTYPE_MYDRAFTREQUESTLIST = "MYDRAFTREQUESTSLIST";

	/*********Added for DATERANGE***********/
	String SEARCHTYPE_DATERANGE = "DATERANGE";

        /*********Added for serviceRequest.startDate***********/
	String SEARCHTYPE_DATERANGE_STARTDATE = "serviceRequest.startDate";

        /*********Added for serviceRequest.endDate***********/
	String SEARCHTYPE_DATERANGE_ENDDATE = "serviceRequest.endDate";

	/*********Added for CSV_FILETYPE_EXTN***********/
	String CSV_FILETYPE_EXTN = "csv";

	/*********Added for  PDF_FILETYPE_EXTN***********/
	String PDF_FILETYPE_EXTN = "pdf";

	
	/*********Added for  EMPTYSTRING ***********/
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
	//Added for CI 14-02-01
	/**. csv file header to be downloaded for change management history */ 
	String serviceReqHistoryCSVHeader = "serviceRequestHistory.csv.header";
	/**. csv file header to be downloaded for change management history */ 
	String serviceReqHistoryPDFHeader = "serviceRequestHistory.pdf.header";
	
	
	/**. pdf file header to be downloaded for change management history */ 
	String allHistoryPDFHeader = "allHistory.pdf.header";
	/**. csv file header to be downloaded for change management history */ 
	String allHistoryCSVHeader = "allHistory.csv.header";
	
	
	
	/**. csv file name change management history */ 
	String CMHISTORYDOWNLOADFILENAME = "changemanagementHistory.download.fileName";
	/**. csv file name change management history */ 
	String SUPPLYHISTORYDOWNLOADFILENAME = "sypplymanagementHistory.download.fileName";
	//Added for CI 14-02-01
	/**. csv file name change management history */ 
	String SERVICEREQHISTORYDOWNLOADFILENAME = "servicerequestHistory.download.fileName";
	/**. csv file name change management history */ 
	String ALLHISTORYDOWNLOADFILENAME = "Allistory.download.fileName";
	
        /*********Added for  allRequestHistory***********/
	String CMHISTORY_DEFAULTPAGE = "allRequestHistory";
	//String CMHISTORY_DEFAULTRESOURCETYPE ="allRequestHistory";

	//Constants added for CHL and Others

	 /*********Added for /serviceRequestExt.properties***********/
	String CONFIG_FILE_PATH = "/serviceRequestExt.properties";
	
	/*********Added for /performance.properties***********/
	String PERF_FILE_PATH = "/performance.properties";

	 /*********Added for requestType.admin***********/

	String REQUEST_TYPE_LIST_ADMIN = "requestType.admin";
     
	/*********Added for requestType.user***********/
	String REQUEST_TYPE_LIST_USER = "requestType.user";

	
	/*********Added for requestSubType.add.admin***********/
	String REQUEST_SUB_TYPE_ADD_LIST_ADMIN = "requestSubType.add.admin";

        /*********Added for requestSubType.change.admin***********/
	String REQUEST_SUB_TYPE_CHANGE_LIST_ADMIN = "requestSubType.change.admin";
 
       /*********Added for requestSubType.remove.admin***********/
	String REQUEST_SUB_TYPE_REMOVE_LIST_ADMIN = "requestSubType.remove.admin";
	
	 /*********Added for requestSubType.user***********/
	String REQUEST_SUB_TYPE_LIST_USER = "requestSubType.user";

       /*********Added for ITEMS_DELIMITER***********/
	String ITEMS_DELIMITER = ":";

      /*********Added for fileList***********/
	String SESSION_FILE_LIST = "fileList";

      /*********Added for  REQUEST_TYPE__ADD***********/
	String REQUEST_TYPE__ADD = "101";

      /*********Added for REQUEST_TYPE__CHANGE ***********/
	String REQUEST_TYPE__CHANGE = "102";

      /*********Added for REQUEST_TYPE__REMOVE***********/
	String REQUEST_TYPE__REMOVE = "103";

      /*********Added for  fileMapInSession***********/
	String SESSION_FILE_MAP = "fileMapInSession";

	/*********Added for  ATTACHMENT_MAX_SIZE***********/	
	String ATTACHMENT_MAX_SIZE = "attachment.maxsize.oneMb";

       /*********Added for ATTACHMENT_MAX_COUNT***********/
	String ATTACHMENT_MAX_COUNT = "attachment.maxcount";

       /*********Added for   ATTACHMENT_REQUEST_TYPE ***********/
	String ATTACHMENT_REQUEST_TYPE = "Service Request";

	  /*********Added for  CM_AREA_NAME ***********/
	String LOV_TYPE_CM_AREA = "CM_AREA_NAME";

       /*********Added for  CM_SUB_AREA_NAME ***********/
	String LOV_TYPE_CM_SUBAREA = "CM_SUB_AREA_NAME";

        /*********Added for  SR_TYPE ***********/
	String LOV_SR_TYPE = "SR_TYPE";

       /*********Added for   pop_up***********/
	String DETAIL_VIEW_POPUP = "pop_up";

	//Added by sagar
	// - Added For Mps Phase 2

   /*********Added for ALL_REQUEST_HISTORY_FILTER_COLUMNS ***********/
     String[] ALL_REQUEST_HISTORY_FILTER_COLUMNS = new String[] { "serviceRequestNumber", "area","serviceRequestStatus", "asset.serialNumber","costCenter","accountName","primaryContact.firstName", "primaryContact.lastName", "primaryContact.emailAddress", "primaryContact.workPhone","requestor.firstName", "requestor.lastName", "requestor.emailAddress", "requestor.workPhone"};
    //Added by sagar
    // *office number , county, district added for MPS Phase 2
   /*********Added for SUPPLY_REQUEST_HISTORY_FILTER_COLUMNS  ***********/

     String[] SUPPLY_REQUEST_HISTORY_FILTER_COLUMNS = new String[] { "serviceRequestNumber", "area","subArea", "asset.serialNumber","asset.deviceTag","serviceRequestStatus", "productModel","poNumber","costCenter","accountName","serviceAddress.addressName","serviceAddress.storeFrontName","serviceAddress.addressLine1","serviceAddress.officeNumber", "serviceAddress.city", "serviceAddress.state", "serviceAddress.province", "serviceAddress.county", "serviceAddress.district", "serviceAddress.country","serviceAddress.postalCode",  "primaryContact.firstName", "primaryContact.lastName", "primaryContact.emailAddress", "primaryContact.workPhone","requestor.firstName", "requestor.lastName", "requestor.emailAddress", "requestor.workPhone","requestStatus","subStatus","severity","agreementName","agreementNumber"};
    //Added by sagar

   /*********Added for CHANGE_REQUEST_HISTORY_FILTER_COLUMNS  ***********/
     String[] CHANGE_REQUEST_HISTORY_FILTER_COLUMNS = new String[] {"serviceRequestNumber", "area","subArea", "asset.serialNumber","asset.deviceTag","serviceRequestStatus","productModel","helpDeskReferenceNumber","costCenter","accountName","serviceAddress.addressName","serviceAddress.storeFrontName","serviceAddress.addressLine1","serviceAddress.officeNumber","serviceAddress.city", "serviceAddress.state", "serviceAddress.province","serviceAddress.county","serviceAddress.district","serviceAddress.country","serviceAddress.postalCode",  "primaryContact.firstName", "primaryContact.lastName", "primaryContact.emailAddress", "primaryContact.workPhone","requestor.firstName", "requestor.lastName", "requestor.emailAddress", "requestor.workPhone","coveredService","requestStatus","subStatus","severity","projectName","projectPhase","agreementName","agreementNumber"};
     
      /*********Added for SUBAREA_ ***********/
	String LOV_TYPE_CM_SUBAREA_PREFIX = "SUBAREA_";

     /*********Added for ATTACHMENT_FILE  ***********/
	String ATTACHMENT_FILE="attachment.properties";

    /*********Added for userAccessMapForSr  ***********/	
	String USERACCESSMAPATTRIBUTEFORSR = "userAccessMapForSr" ;

     /*********Added for userAccessMapForArea ***********/	
	String USER_ACCESS_MAP_FOR_AREA = "userAccessMapForArea" ;
  	
     /*********Added for Consumables Management  ***********/
	String SRTYPE_SUPPLYMANAGEMENT = "Consumables Management";

     /*********Added for Fleet Management  ***********/
	String SRTYPE_CHANGEMANAGEMENT = "Fleet Management";

     /*********Added for BreakFix ***********/
	String SRTYPE_BREAKFIX = "BreakFix";

     /*********Added for Consumables Return***********/
	String SRTYPE_RETURNSUPPLIES = "Consumables Return";
	
	
	//Added by Arup
	//For XMLoutputGeneratorUtil

        /*********Added for CHANGE_IMAGE***********/
	 String CHANGE_IMAGE="edit.gif";

         /*********Added for REMOVE_IMAGE***********/
	 String REMOVE_IMAGE="delete.gif";

        /*********Added for FAVORIATE_IMAGE_FILE_NAME***********/
	 String FAVORIATE_IMAGE_FILE_NAME = "favorite.png";

       /*********Added for UNFAVORIATE_IMAGE_FILE_NAME***********/
	 String UNFAVORIATE_IMAGE_FILE_NAME = "unfavorite.png";

       /*********Added for PENCIL_IMAGE_FILE_NAME ***********/
     
	 String PENCIL_IMAGE_FILE_NAME = "pencil.png";

     /*********Added for IMAGE_PATH ***********/
	 String IMAGE_PATH = "/images/";
	
	//For CommonValidator

     /*********Added for NAMEPATTERN ***********/
	// String NAMEPATTERN = "[a-zA-Z.\\s-]{1,50}";
	 String NAMEPATTERN =  "[0-9]{1,50}";
	//private static final String STOREFRONTNAMEPATTERN = "[a-zA-Z]{1,30}";

      /*********Added for PHONEPATTERN ***********/
	 String PHONEPATTERN = "^[+]{0,1}([0-9]){1,3}[ ]?([-]?(([0-9])|[ ]){1,12})+$";

    /*********Added for EMAILPATTERN***********/
	 String EMAILPATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*" + "((\\.[A-Za-z]{2,}){1}$)";
	
	
	/********Added as part of Common *******/

         /********Added for ACTION *******/
	 String ACTION = "action";
         /********Added for accountCurrentDetails *******/
	 String ACNTCURRDETAILS = "accountCurrentDetails";
	 String ACNTCURRDETAILSPARTNER = "accountCurrentDetailsPartner"; //added
         /********Added for timeZoneOffset *******/
	 String TIMEZNOFFSET = "timeZoneOffset";
         /********Added for HOURFORMAT *******/
	 String HOURFORMAT = " HH:mm:ss";
         /********Added for deviceListPopulate *******/
	 String DEVICELISTRESURL = "deviceListPopulate";
         /********Added for assetResultList *******/
	 String ASSETRESULTLIST = "assetResultList";
         /********Added for YES *******/
	 String YES = "yes";
	
	/*********Added for Manage Asset ***********/
	//Model Attributes

           /********Added for ADDASSETFORM *******/
	 String ADDASSETFORM = "manageAssetForm";
           /********Added for CHANGEASSETFORM  *******/
	 String CHANGEASSETFORM = "manageAssetFormForChange";
           /********Added for DECOMMASSETFORM  *******/
	 String DECOMMASSETFORM = "manageAssetFormForDecommission";
           /********Added for  MODELATTRSRNO *******/
	 String MODELATTRSRNO = "srNo";
          /********Added for SRNUMBER *******/
	 String SRNUMBER = "srNumber";

	
	 /*********For Page redirection ***********/

  /********Added for ASSETLISTPATH *******/
	 String ASSETLISTPATH = "changemanagement/manageAsset/assetList";

  /********Added for ADDASSETPATH *******/
	 String ADDASSETPATH = "changemanagement/manageAsset/addAsset";

  /********Added for ASSETSUMMARYPATH *******/
	 String ASSETSUMMARYPATH = "changemanagement/manageAsset/assetSummary";
	 
  /********Added for MULTIASSETSUMMARYPATH *******/
	 String MULTIASSETSUMMARYPATH = "changemanagement/manageMultiAsset/multiAssetSummary";

  /********Added for ADDASSETCONFPATH *******/
	 String ADDASSETCONFPATH = "changemanagement/manageAsset/addAssetConfirmation";

  /********Added for CHANGEASSETPATH *******/
	 String CHANGEASSETPATH = "changemanagement/manageAsset/changeAsset";
	 /********Added for ADDASSETPATH *******/
	 String ADDMULTIASSETPATH = "changemanagement/manageMultiAsset/addMultiAsset";
	 
	 /********Added for ADDMULTIASSETCONFPATH *******/
     String ADDMULTIASSETCONFPATH = "changemanagement/manageMultiAsset/addMultiAssetConfirmation";
	 
  /********Added for CHANGEMULTIASSETPATH *******/
	 String CHANGEMULTIASSETPATH = "changemanagement/manageMultiAsset/changeMultiAsset";
	 
  /********Added for DECOMMULTIASSETPATH *******/
	 String DECOMMULTIASSETPATH = "changemanagement/manageMultiAsset/decomMultiAsset";
	 
  /********Added for CHANGEASSETCONFPATH *******/
	 String CHANGEASSETCONFPATH = "changemanagement/manageAsset/changeAssetConfirmation";
	 
  /********Added for CHANGEASSETCONFPATH *******/
     String CHANGEMULTIASSETCONFPATH = "changemanagement/manageMultiAsset/changeMultiAssetConfirmation";

  /********Added for DECOMMASSETPATH *******/
	 String DECOMMASSETPATH = "changemanagement/manageAsset/decommissionAsset";

  /********Added for DECOMMASSETCONFPATH *******/
	 String DECOMMASSETCONFPATH = "changemanagement/manageAsset/decommissionAssetConfirmation";
	 
	 /********Added for DECOMMASSETCONFPATH *******/
	 String DECOMMMULTIASSETCONFPATH = "changemanagement/manageMultiAsset/decommissionMultiAssetConfirmation";

  /********Added for PRINTASSETPATH *******/
	 String PRINTASSETPATH = "changemanagement/manageAsset/printAsset";

  /********Added for ASSETEMAILPATH*******/
	 String ASSETEMAILPATH = "changemanagement/manageAsset/AssetEmail";


  /********Added for PRINTMULTIASSETPATH *******/
	 String PRINTMULTIASSETPATH = "changemanagement/manageMultiAsset/printMultiAsset";
	 
  /********Added for MULTIASSETEMAILPATH*******/
	 String MULTIASSETEMAILPATH = "changemanagement/manageMultiAsset/multiAssetEmail";
	 
  /********Added for ALLREQHISTPAGENM *******/
	 String ALLREQHISTPAGENM = "lgsHistoryDetails/allRequestHistory";

  /********Added for SELECTCONTACTPATH  *******/
	 String SELECTCONTACTPATH = "common/selectContact";

  /********Added for ADDRLISTPOPUPPATH*******/
	 String ADDRLISTPOPUPPATH = "changemanagement/addressListPopup";

  /********Added for  CHLTREEPATH*******/
	 String CHLTREEPATH = "common/displayCHLTree";

  /********Added for common/createNewRequestPopUp *******/
	 String CRREQPOPUPPATH = "common/createNewRequestPopUp";

  /********Added for ATTACHMENTPATH *******/
	 String ATTACHMENTPATH = "changemanagement/attachment";

	
	
	 /*********Request Attributes & Parameters ***********/

	 
  /********Added for ADDONE *******/
	 String ADDONE = "addone";
	 /********Added for ADDMULTIPLE *******/
	 String ADDMULTIPLE = "addMultiple";
  /********Added for PREVSRNO*******/
	 String PREVSRNO = "prevSrNo";
  /********Added for ISUPDATEFLAG *******/
	 String ISUPDATEFLAG = "isUpdateFlag";
  /********Added for exceptn *******/
	 String EXCEPTNATTR = "exceptn";
  /********Added for errorOccurred *******/
	 String ERRORATTR = "errorOccurred";
  /********Added for SERVICEREQNO *******/
	 String SERVICEREQNO = "serviceRequestNumber";
  /********Added for UPDATEFLAG*******/
	 String UPDATEFLAG = "updateFlag"; 
  /********Added for SELECTEDVALUE*******/
	 String SELECTEDVALUE = "selectedVal";
  /********Added for ASSETLISTGRIDID  *******/
	 String ASSETLISTGRIDID = "assetListGrid";
  /********Added for FORWARDTOPAGE *******/
	 String FORWARDTOPAGE = "forwardtoJSP";
 /********Added for ASSETID  *******/
	 String ASSETID = "assetId";
  /********Added for REQUESTTYPE *******/
	 String REQUESTTYPE = "requestType";
 /********Added for true*******/
	 String TRUEATTR = "true";

 /********Added for REQ_TYPE_ASSETS *******/
	 String REQ_TYPE_ASSETS= "Assets";
 /********Added for REQ_TYPE_ADDRESS *******/
	 String REQ_TYPE_ADDRESS= "Addresses";
 /********Added for REQ_TYPE_CONTACTS *******/
	 String REQ_TYPE_CONTACTS= "Contacts";

 /********Added for REQ_TYPE_ADD *******/
	 String REQ_TYPE_ADD = "Add";
 /********Added for REQ_TYPE_CHANGE  *******/
	 String REQ_TYPE_CHANGE = "Change";
 /********Added for REQ_TYPE_REMOVE *******/
	 String REQ_TYPE_REMOVE = "Remove";
 /********Added for REQ_TYPE_DECOMM *******/
	 String REQ_TYPE_DECOMM = "Decommission";
 /********Added for REQ_TYPE_SWAP *******/
	 String REQ_TYPE_SWAP = "Swap";
	
	
	 /*********Area & Sub Area values to be sent in webservice call ***********/

/********Added for Add Asset Data *******/
	 String ADDASSETAREA = "Add Asset Data";
/********Added for Add Asset *******/
	 //String ADDASSETAREA_YES = "Add Asset";
	 String ADDASSETAREA_YES = "Install";
/********Added for Register Asset *******/
	 String ADDASSETSUBAREA = "Register Asset";
/********Added for Install Asset *******/
	 //String ADDASSETSUBAREA_YES = "Install Asset";
	 String ADDASSETSUBAREA_YES = "BAU";
/********Added for Change Asset*******/
	 String CHANGEASSETAREA = "Change Asset";
/********Added for Change Asset Data*******/
	 String CHANGEASSETSUBAREA = "Change Asset Data";

	
	 /*********for Asset Move Type Requirement-- ***********/

/********Added for Move Physical *******/
	 String MOVEPHYSICAL = "Move Physical";
/********Added for Move Install Locations *******/
	 String CHANGEASSETSUBAREA_MOVEINSTALLLOCATIONS = "Move Install Locations";
/********Added for Move to Storage *******/
	 String CHANGEASSETSUBAREA_MOVETOSTORAGE = "Move to Storage";
/********Added for Install from Storage *******/
	 String CHANGEASSETSUBAREA_INSTALLFROMSTORAGE = "Install from Storage";
/********Added for Move Storage Locations *******/
	 String CHANGEASSETSUBAREA_MOVESTORAGELOCATIONS = "Move Storage Locations";
	
/********Added for Decommission Asset *******/
	 String DECOMMASSETAREA_YES = "Decommission Asset";
/********Added for Decommission Asset Data *******/	
	 String DECOMMASSETAREA = "Decommission Asset Data";
/********Added for DeInstall Asset *******/
	 //String DECOMMASSETSUBAREA_YES = "DeInstall Asset";
	 String DECOMMASSETSUBAREA_YES = "BAU";
/********Added for Deregister Asset *******/
	 String DECOMMASSETSUBAREA = "Deregister Asset";
/********Added for SWAPASSETAREA *******/	
	 String SWAPASSETAREA = "Change Asset";
/********Added for SWAPASSETSUBAREA *******/
	 String SWAPASSETSUBAREA = "Register Asset Swap";
	
/********Added for Add Account Data*******/
	 String ADDACCNTDATA = "Add Account Data";
 /********Added for Change Account Data *******/
	 String CHANGEACCNTDATA = "Change Account Data";
/********Added for Decommission Account Data *******/
	 String DECOMMACCNTDATA = "Decommission Account Data";
	
/********Added for ADDADDRESSSUBAREA *******/
	 String ADDADDRESSSUBAREA = "Add Address";
/********Added for CHANGEADDRESSSUBAREA *******/	
	 String CHANGEADDRESSSUBAREA = "Change Address";
/********Added for REMOVEADDRESSSUBAREA  *******/	
	 String REMOVEADDRESSSUBAREA = "Deactivate Address";
/********Added for ADDCONTACTSUBAREA*******/	
	 String ADDCONTACTSUBAREA = "Add Contact";
/********Added for CHANGECONTACTSUBAREA  *******/	
	 String CHANGECONTACTSUBAREA = "Change Contact";
/********Added for REMOVECONTACTSUBAREA *******/	
	 String REMOVECONTACTSUBAREA = "Deactivate Contact";
	
/********Added for Add Customer Hierarchy*******/
	 String SIEBEL_VALUE_CHL_SUBAREA_ADD = "Add Customer Hierarchy";
/********Added for Change Customer Hierarchy  *******/	
	 String SIEBEL_VALUE_CHL_SUBAREA_CHANGE = "Change Customer Hierarchy";
/********Added for Deactivate Customer Hierarchy*******/	
	 String SIEBEL_VALUE_CHL_SUBAREA_DECOMMISSION = "Deactivate Customer Hierarchy";
	
	
	 /*********Page Names to be set as request attributes for redirection ***********/

/********Added for addAssetConfirmation*******/
	 String ADDASSETCONFPGNM = "addAssetConfirmation";
/********Added for changeAssetConfirmation*******/
	 String CHNGASSETCONFPGNM = "changeAssetConfirmation";
/********Added for decommissionAssetConfirmation*******/
	 String DECOMMASSETCONFPGNM = "decommissionAssetConfirmation";

/********Added for assetSummary*******/
	 String ASSETSUMMARY = "assetSummary";
/********Added for multiAssetSummary*******/
	 String MULTIASSETSUMMARY = "multiAssetSummary";
/********Added for isLexmarkEmployee*******/
	 String ISLEXEMPLOYEE = "isLexmarkEmployee";
/********Added for requestorContact*******/ 
	 String REQUESTORCONT = "requestorContact";
/********Added for COUNTRYCODE*******/
	 String COUNTRYCODE = "countryCode";
	
	
	 /*********Others ***********/
/********Added for CSVFAILURE*******/
	 String CSVFAILURE = "failure";
/********Added for CSVSUCCESS*******/
	 String CSVSUCCESS = "success";
/********Added for typeOfRequest*******/
	 String TYPEOFREQ = "typeOfRequest";//Required for asset summary
/********Added for Add_Asset*******/
	 String TYPEOFREQ_ADD = "Add_Asset";//Required for asset summary
/********Added for Change_Asset*******/
	 String TYPEOFREQ_CHANGE = "Change_Asset";//Required for asset summary
/********Added for Decomm_Asset*******/
	 String TYPEOFREQ_DECOMM = "Decomm_Asset";//Required for asset summary
/********Added for changeAssetRequestE*******/
	 String CHANGEASSETREQTYPE = "changeAssetRequest";
/********Added for decommissionAssetRequest*******/
	 String DECOMMASSETREQTYPE = "decommissionAssetRequest";
/********Added for ManageAsset*******/
	 String CSVTYPEMANAGEASSET = "ManageAsset";
/********Added for ManageAddress******/
	 String CSVTYPEMANAGEADDRESS = "ManageAddress";
/********Added for ManageContact*******/
	 String CSVTYPEMANAGECONTACT = "ManageContact";
	 
/********Added for ManageMultiAsset*******/
	 String CSVTYPEMANAGEMULTIASSET = "multi_asset_info";
	
/********Added for SERVICEREQTYPE*******/
	 String SERVICEREQTYPE = "Fleet Management";
/********Added for VENDORREQTYPE*******/
	 String VENDORREQTYPE = "Vendor";
/********Added for  LEXMARKREQTYPE *******/
	 String LEXMARKREQTYPE = "Lexmark";
/********Added for VIEWPARAM*******/
	 String VIEWPARAM = "view";
/********Added for CONTENTTYPEXML*******/
	 String CONTENTTYPEXML = "text/xml";
/********Added for CONTENTTYPEHTML*******/
	 String CONTENTTYPEHTML = "text/html";
/********Added for ispopup*******/
	 String ISPOPUP = "ispopup";
/********Added for isContactPopUp*******/
	 String ISCONTACTPOPUP = "isContactPopUp";
/********Added for chkConsElgFlag*******/
	 String CHECKCONSELGFLAG = "chkConsElgFlag";
/********Added for createServiceRequestFlag*******/
	 String CREATESERVICEREQFLAG = "createServiceRequestFlag";
/********Added for USERTYPE_PARTNER*******/

	 String USERTYPE_PARTNER = "Partner";
	
	/*********Added for Manage Contact ***********/
	
	 /*********Page Names to be set as request attributes for redirection ***********/

	/*********Added for MANAGECONTACTFORM  ***********/
	 String MANAGECONTACTFORM = "manageContactForm";
	/*********Added for OLDCONTACTREMOVE  ***********/
	 String OLDCONTACTREMOVE = "oldContactRemove";
	/*********Added for OLDCONTACTCHANGE ***********/
	 String OLDCONTACTCHANGE = "oldContactChange";
	
	 /*********Page name for Redirection ***********/


	/*********Added for PRINTCONTACTPATH  ***********/
	 String PRINTCONTACTPATH = "changemanagement/manageContact/printContact";

	/*********Added for CONTACTEMAILPATH   ***********/
	 String CONTACTEMAILPATH = "changemanagement/manageContact/ContactEmail";

	/*********Added for  CONTACTLISTPATH ***********/
	 String CONTACTLISTPATH = "changemanagement/manageContact/contactList";

	/*********Added for ADDCONTACTPATH  ***********/
	 String ADDCONTACTPATH = "changemanagement/manageContact/addContact";

	/*********Added for CHANGECONTACTPATH ***********/
	 String CHANGECONTACTPATH = "changemanagement/manageContact/changeContact";

	/*********Added for REMOVECONTACTPATH  ***********/
	 String REMOVECONTACTPATH = "changemanagement/manageContact/removeContact";

	/*********Added for ADDCONTACTREVIEWPATH  ***********/
	 String ADDCONTACTREVIEWPATH = "changemanagement/manageContact/addContactReview";

	/*********Added for CHANGECONTACTREVIEWPATH  ***********/
	 String CHANGECONTACTREVIEWPATH = "changemanagement/manageContact/changeContactReview";

	/*********Added for REMOVECONTACTREVIEWPATH  ***********/
	 String REMOVECONTACTREVIEWPATH = "changemanagement/manageContact/removeContactReview";

	/*********Added for CONTACTSUMMARYPATH   ***********/
	 String CONTACTSUMMARYPATH = "changemanagement/manageContact/contactSummary";
	
	
	
	/*********Added for manageContactReview ***********/
	 String MANAGECONTACTREVIEW = "manageContactReview";

	/*********Added for contactSummaryShow  ***********/
	 String CONTACTSUMMARYSHOW = "contactSummaryShow";
	
	/*********Added for EXCEPTION   ***********/
	 String EXCEPTION = "exception";

	/*********Added for contactResultList   ***********/
	 String CONTACTRESULTLIST = "contactResultList";
	
	/*********Added for addContactRequest   ***********/
	 String ADDCONTACTRQST = "addContactRequest";

	/*********Added for removeContactRequest  ***********/
	 String REMOVECONTACTRQST = "removeContactRequest";

	/*********Added for CHANGECONTACTRQST  ***********/
	 String CHANGECONTACTRQST = "changeContactRequest";
	
	/*********Added for FLOWDIRECTION  ***********/
	 String FLOWDIRECTION = "flowDirection";

	/*********Added for FORWARD ***********/
	 String FORWARD = "forward";
	
	/*********Added for ERROR   ***********/
	 String ERROR = "error";
	
	 /*********Changes for MPS 2.1 Contact list ***********/

/*********Added for contactGrid_containerNew   ***********/
	 String CONTACTGRIDCONTAINER = "contactGrid_containerNew";
	//Ends sChanges for MPS 2.1 Contact list
/*********Added for FIRSTNAME    ***********/
	 String FIRSTNAME = "firstName";
/*********Added for LASTNAME   ***********/
	 String LASTNAME = "lastName";
/*********Added for WORKPHONE   ***********/
	 String WORKPHONE = "workPhone";
/*********Added for alternatePhone   ***********/
	 String ALTPHONE = "alternatePhone";
/*********Added for EMAILID   ***********/
	 String EMAILID = "emailId";


	
	 /*********validations ***********/

/*********Added for AC_FIRSTNAME  ***********/
	 String AC_FIRSTNAME = "accountContact.firstName";
/*********Added for  AC_FIRSTNAMEEMPTY  ***********/
	 String AC_FIRSTNAMEEMPTY = "contact.firstname.empty";
/*********Added for AC_LASTNAME   ***********/
	 String AC_LASTNAME = "accountContact.lastName";
/*********Added for AC_LASTNAMEEMPTY   ***********/
	 String AC_LASTNAMEEMPTY = "contact.lastname.empty";
/*********Added for  AC_WORKPHN    ***********/
	 String AC_WORKPHN = "accountContact.workPhone";
/*********Added for AC_WORKPHNEMPTY    ***********/
	 String AC_WORKPHNEMPTY = "contact.workphone.empty";
/*********Added for AC_EMAILID  ***********/
	 String AC_EMAILID = "accountContact.emailAddress";
/*********Added for AC_EMAILIDEMPTY   ***********/
	 String AC_EMAILIDEMPTY = "contact.emailaddress.empty";
/*********Added for  AC_ADDLINE1 ***********/
	 String AC_ADDLINE1 = "accountContact.address.addressLine1";
/*********Added for AC_ADDLINE1EMPTY   ***********/
	 String AC_ADDLINE1EMPTY = "contact.addrline1.empty";
/*********Added for AC_CITY  ***********/
	 String AC_CITY = "accountContact.address.city";
/*********Added for AC_CITYEMPTY    ***********/
	 String AC_CITYEMPTY = "contact.city.empty";
/*********Added for AC_COUNTRY   ***********/
	 String AC_COUNTRY = "accountContact.address.country";
/*********Added for AC_COUNTRYEMPTY   ***********/	 
         String AC_COUNTRYEMPTY = "contact.country.empty";
/*********Added for AC_POSTALCODE   ***********/
	 String AC_POSTALCODE = "accountContact.address.postalCode";
/*********Added for AC_POSTALCODEEMPTY  ***********/
	 String AC_POSTALCODEEMPTY = "contact.zip.empty";

	//exceptions
/*********Added for SAVEDATA_ERROR   ***********/
	 String SAVEDATA_ERROR = "Error while saving data";



	/********Added for Manage Address ***********/

/*********Added for ADDRESSDETAILSOLD   ***********/
	 String ADDRESSDETAILSOLD = "addressDetailsOld";
/*********Added for ADDRESSFORM   ***********/
	 String ADDRESSFORM = "addressForm";
/*********Added for CONTACTID    ***********/
	 String CONTACTID = "contactid";
/*********Added for  ADDRESSID   ***********/
	 String ADDRESSID = "addressId";
/*********Added for ADDRESSNAME   ***********/
	 String ADDRESSNAME = "addressName";
/*********Added for ADDRESSLINE1  ***********/
	 String ADDRESSLINE1 = "addressLine1";
/*********Added for ADDRESSLINE2   ***********/
	 String ADDRESSLINE2 = "addressLine2";	
/*********Added for COUNTRY   ***********/
	 String COUNTRY = "country";
/*********Added for STATEGRID   ***********/
	 String STATEGRID = "stateGrid";
/*********Added for PROVINCE   ***********/
	 String PROVINCE = "province";
/*********Added for zipPostal   ***********/
	 String ZIP = "zipPostal";
/*********Added for STOREFRONTNAME   ***********/
	 String STOREFRONTNAME = "storeFrontName";
/*********Added for ACCOUNTID   ***********/
	 String ACCOUNTID = "accountId";
	
	 /********- Added For Mps Phase 2 ********

/*********Added for  ACCOUNTNAME  ***********/
	 String ACCOUNTNAME = "accountName";
/*********Added for accountOrganization   ***********/
	 String ACCOUNTORG = "accountOrganization";
/*********Added for AGREEMENTID   ***********/
	 String AGREEMENTID = "agreementId";
/*********Added for AGREEMENTNAME   ***********/
	 String AGREEMENTNAME = "agreementName";
	



	
	 /********For Page redirection ***********/

/*********Added for ADDRESSEMAIL   ***********/
	 String VIEWADDRESSLIST = "changemanagement/manageAddress/addressList";
/*********Added for ADDRESSEMAIL    ***********/
	 String PRINTADDRESS = "changemanagement/manageAddress/printAddress";
/*********Added for ADDRESSEMAIL   ***********/
	 String ADDRESSEMAIL = "changemanagement/manageAddress/AddressEmail";
/*********Added for ADDADDRESSPAGE   ***********/
	 String ADDADDRESSPAGE = "changemanagement/manageAddress/addAddress";
/*********Added for ADDRESSUMMARY   ***********/
	 String ADDRESSUMMARY = "changemanagement/manageAddress/addressSummary";
/*********Added for MANAGEADDRESS  ***********/
	 String MANAGEADDRESS = "changemanagement/manageAddress/";
/*********Added for CHANGEADDRESSCONFIRMATION   ***********/
	 String CHANGEADDRESSCONFIRMATION = "changeAddressConfirmation";
/*********Added for CHANGEADDRESSPAGE   ***********/
	 String CHANGEADDRESSPAGE = "changemanagement/manageAddress/changeAddress";
/*********Added for REMOVEADDRESSPAGE   ***********/
	 String REMOVEADDRESSPAGE = "changemanagement/manageAddress/removeAddress";


	
	 /********Request Attributes & Parameters ***********/

/*********Added for EXCEPTIONOCCURED ***********/
	 String EXCEPTIONOCCURED = "exceptionOccured";
/*********Added for ISBACK ***********/
	 String ISBACK = "isback";
/*********Added for ISCHANGE ***********/
	 String ISCHANGE = "ischange";
/*********Added for ISREMOVE ***********/
	 String ISREMOVE = "isremove";
/*********Added for CHANGEADDRESS ***********/
	 String CHANGEADDRESS = "changeAddress";
/*********Added for REMOVEADDRESS ***********/
	 String REMOVEADDRESS = "removeAddress";
/*********Added for ADDADDRESS ***********/
	 String ADDADDRESS = "addAddress";
/*********Added for DUPLICATESUBMISSION ***********/
	 String DUPLICATESUBMISSION = "exception.serviceRequest.duplicateSubmission";
/*********Added for ISCHANGEADDRESS ***********/
	 String ISCHANGEADDRESS = "isChangeAddress";
/*********Added for SUMMARYPAGE ***********/
	 String SUMMARYPAGE = "showSummaryPage";
/*********Added for ERROROCCURED ***********/
	 String ERROROCCURED = "errorOccured";
/*********Added for CONFIRMADDRESS ***********/
	 String CONFIRMADDRESS = "addressConfirm";
/*********Added for ADDADDRESSCONFIRMATION   ***********/
	 String ADDADDRESSCONFIRMATION = "addAddressConfirmation";
/*********Added for removeAddressConfirmation***********/
	 String REMOVEADDRESSCONFIRMATION = "removeAddressConfirmation";
/*********Added for DELETEADDRESS ***********/
	 String DELETEADDRESS = "deleteAddress";
/*********Added for STATE ***********/
	 String STATE = "state";
/*********Added for CITY ***********/
	 String CITY = "city";
/*********Added for CHLNODEID    ***********/
	 String CHLNODEID = "chlNodeId";
/*********Added for ATTACHMENTREQTYPE   ***********/
	 String ATTACHMENTREQTYPE ="Service Request";
/*********Added for CONTENTTYPE  ***********/
	 String CONTENTTYPE = "text/plain";
/*********Added for GENERICADDRESSNAME ***********/
	 String GENERICADDRESSNAME = "genericAddress.addressName";
/*********Added for GENERICADDRESSLINE1  ***********/
	 String GENERICADDRESSLINE1 = "genericAddress.addressLine1";
/*********Added for GENERICCITY   ***********/
	 String GENERICCITY = "genericAddress.city";
/*********Added for GENERICCOUNTRY   ***********/
	 String GENERICCOUNTRY = "genericAddress.country";
/*********Added for GENERICPOSTALCODE   ***********/
	 String GENERICPOSTALCODE = "genericAddress.postalCode";
/*********Added for  EMPTYADDRNAME   ***********/
	 String EMPTYADDRNAME = "validation.address.addrName.empty";
/*********Added for EMPTYADDRLINE1   ***********/
	 String EMPTYADDRLINE1 = "contact.addrline1.empty";
/*********Added for  EMPTYCITY    ***********/
	 String EMPTYCITY = "contact.city.empty";
/*********Added for EMPTYCOUNTRY   ***********/
	 String EMPTYCOUNTRY = "contact.country.empty";
/*********Added for EMPTYZIP    ***********/
	 String EMPTYZIP = "contact.zip.empty";
/*********Added for  BOOKMARKED  ***********/
	 String BOOKMARKED = "bookmarked";
	
	 /********Added for CHL & Others***********/

/*********Added for  TYPE_OF_FLOW ***********/
	 String TYPE_OF_FLOW = "typeOfFlow";
/*********Added for  CHL_FLOW ***********/
	 String CHL_FLOW = "CHL";
/*********Added for  OTHERS_FLOW ***********/
	 String OTHERS_FLOW = "Others";
	
/*********Added for  SRTYPE_CANCEL_REQUEST ***********/
	 String SRTYPE_CANCEL_REQUEST = "Inquiry";
/*********Added for  SR_SUBTYPE_CANCEL_REQUEST ***********/
	 String SR_SUBTYPE_CANCEL_REQUEST = "Cancel Request";
	
/*********Added for  VALUE_AREA ***********/
	 String VALUE_AREA = "area";
/*********Added for  VALUE_SUBAREA ***********/
	 String VALUE_SUBAREA = "subArea";
/*********Added for  FLOW_TYPE_UPDATE ***********/
	 String FLOW_TYPE_UPDATE = "update";
/*********Added for  FLOW_TYPE_CANCEL ***********/
	 String FLOW_TYPE_CANCEL = "cancel";
	
/*********Added for  SUBAREA_INITIATE_PO ***********/
	 String SUBAREA_INITIATE_PO = "Initiate PO";
/*********Added for  LIST_EXCEPTION ***********/	
	 String LIST_EXCEPTION = "exceptionList";
	
	
	 /********CHL AREA SUB AREA VALUES***********/

/*********Added for  CHL_AREA_ADD ***********/
	 String CHL_AREA_ADD = "requestInfo.cm.chl.area.add";
/*********Added for  CHL_AREA_CHANGE ***********/
	 String CHL_AREA_CHANGE = "requestInfo.cm.chl.area.change";
/*********Added for  CHL_AREA_DECOMMISSION ***********/
	 String CHL_AREA_DECOMMISSION = "requestInfo.cm.chl.area.decommission";

	/*********Added for  ALL_REQUESTS ***********/
	 String ALL_REQUESTS = "ALL_REQUESTS";
/*********Added for  SUPPLY_REQUESTS ***********/
	 String SUPPLY_REQUESTS = "SUPPLY_REQUESTS";
/*********Added for  CHANGE_REQUESTS ***********/
	 String CHANGE_REQUESTS = "CHANGE_REQUESTS";
/*********Added for  SERVICE_REQUESTS ***********/
	 String SERVICE_REQUESTS = "SERVICE_REQUESTS";
/*********Added for  HARDWARE_REQUESTS ***********/
	 String HARDWARE_REQUESTS = "HARDWARE_REQUESTS";
/*********Added for  lgsHistoryDetails/hwDetails ***********/
	 String HARDWAREREQUESTDETAILS="lgsHistoryDetails/hwDetails";
/*********Added for  HARDWARE***********/
	 String HARDWARE="hardware";
	
	/********** Added for Device Mgmt *******/

/*********Added for  ISDEVICE_BOOKMARKED ***********/
	 String ISDEVICE_BOOKMARKED = "isDeviceBookmarked";
/*********Added for  ASSETROWID ***********/
	 String ASSETROWID = "assetRowId";
/*********Added for  DWNLD_DEVICELIST_URL ***********/		
	 String DWNLD_DEVICELIST_URL = "downloadDeviceListURL";
/*********Added for DEVICELIST_GRID ***********/
	 String DEVICELIST_GRID = "devicelistGrid";
/*********Added for  DEVICELOCTREE_URL ***********/
	 String DEVICELOCTREE_URL = "deviceLocationTreeXMLURL";
/*********Added for  CHLTREE_URL ***********/
	 String CHLTREE_URL = "chlTreeXMLURL";
/*********Added for SEARCH_NAME ***********/
	 String SEARCH_NAME = "searchName";
/*********Added for  SEARCH_VALUE ***********/
	 String SEARCH_VALUE = "searchValue";
/*********Added for  ASSET_ROWID ***********/
	 String ASSET_ROWID = "assetRowId";
/*********Added for  SRHISTORY_TYPE ***********/
	 String SRHISTORY_TYPE = "srHistoryType";
/*********Added for  SERVICEREQ_LIST ***********/
	 String SERVICEREQ_LIST = "ServiceRequestList";
/*********Added for  LTPCSUCCESS E***********/
	 String LTPCSUCCESS = "ltpcSuccess";
/*********Added for LTPCVALUE ***********/
	 String LTPCVALUE = "ltpcvalue";
/*********Added for COLORVALUE***********/
	 String COLORVALUE = "colorvalue";
/*********Added for LTPCDATE***********/
	 String LTPCDATE = "ltpcdate";
/*********Added for  COLORDATE***********/
	 String COLORDATE = "colordate";
/*********Added for  SUCCESS E***********/	
	 String SUCCESS = "success";
/*********Added for  newPageCount***********/
	 String NEWPAGECOUNT = "newPageCount";
/*********Added for  newColorPageCount***********/
	 String NEWCOLORPGCNT = "newColorPageCount";
/*********Added for  NEWPGREADDT ***********/
	 String NEWPGREADDT = "newPageReadDate";
/*********Added for NEWCOLORPGREADDT ***********/
	 String NEWCOLORPGREADDT = "newColorPageReadDate";
/*********Added for SELECTEDASSETID***********/
	 String SELECTEDASSETID = "selectedAssetId";	
/*********Added for  DEVICEFINDER_PATH ***********/
	 String DEVICEFINDER_PATH = "deviceFinder/deviceFinder";
/*********Added for  DEVICEDETAILS_PATH***********/
	 String DEVICEDETAILS_PATH = "deviceFinder/deviceInfoPage";
/*********Added for  deviceFinder/deviceListPrint***********/
	 String DEVICE_LIST_PRNT_PATH = "deviceFinder/deviceListPrint";
/*********Added for  deviceFinder/deviceDetailsPrintPage***********/
	 String DEVICE_DTLS_PRNT_PG = "deviceFinder/deviceDetailsPrintPage";
/*********Added for  deviceFinder/pageCountPopUp***********/
	 String DEVICE_PGCNTPOP_PATH = "deviceFinder/pageCountPopUp";
/*********Added for  deviceFinder/deviceDetailsPrintPage***********/
	 String DEVICE_DTLS_REQ_PRNT_PG = "deviceFinder/deviceDetailsReqPrintPage";
	
	
	 /********ORDER MANAGEMENT AREA SUB AREA VALUE***********/

/*********Added for  Consumable Mix Parts Request***********/
	 String OM_AREA_MIX_PARTS = "Consumable Mix Parts Request";
/*********Added for  Asset-Specific Request**********/
	 String OM_SUB_AREA_ASSET = "Asset-Specific Request";
/*********Added for  OM_SUB_AREA_CATALOG ***********/
	 String OM_SUB_AREA_CATALOG = "Catalog Request";
/*********Added for  OM_SR_STATUS_OPEN ***********/
	 String OM_SR_STATUS_OPEN = "Open";
/*********Added for  OM_SR_STATUS_DRAFT **********/
	 String OM_SR_STATUS_DRAFT = "Draft";
/*********Added for  Consumables Management***********/
	 String OM_SR_TYPE = "Consumables Management";
	
	 /********Added for JAN release PARTS to be installed **********/

	 String OM_SR_TYPE_PARTS_INSTALLED = "Consumables Install Request";
	
	
	/********Added for History Controller ***********/

/*********Added for  FLEETMGMT  ***********/
	 String FLEETMGMT = "Fleet_Management";
/*********Added for  CONSUMABLESMGMT  ***********/
	 String CONSUMABLESMGMT = "Consumables_Management";
/*********Added for  CLAIMS ***********/
	 String CLAIMS = "Claims";
/*********Added for ALLREQUESTS  ***********/
	 String ALLREQUESTS = "ALL_REQUESTS";
/*********Added for SUPPLYREQUESTS  ***********/
	 String SUPPLYREQUESTS = "SUPPLY_REQUESTS";
/*********Added for CHANGEREQUESTS  ***********/
	 String CHANGEREQUESTS = "CHANGE_REQUESTS";
/*********Added for DOWNLOADCONTRACT  ***********/
	 String DOWNLOADCONTRACT = "downLoadContract";
/*********Added for DOWNLOADATTACHMENT  ***********/
	 String DOWNLOADATTACHMENT = "downloadAttachment";
/*********Added for  ACTIVITYDATE ***********/
	 String ACTIVITYDATE = "activityDate";
/*********Added for  ACTIVITYSTATUS ***********/
	 String ACTIVITYSTATUS = "activityStatus";
/*********Added for  activityDescription ***********/
	 String ACTIVITYDESCRIPTION = "activityDescription";


	
	 /********for page redirection ***********/
/*********Added for  CHANGEREQUESTDETAILSPOPUP ***********/
	 String CHANGEREQUESTDETAILSPOPUP = "lgsHistoryDetails/changeRequestDetailsPopup";
/*********Added for  CHANGEREQUESTDETAILS ***********/
	 String CHANGEREQUESTDETAILS = "lgsHistoryDetails/changeRequestDetails";
/*********Added for  SUPPLYREQUESTDETAILSPOPUP ***********/
	 String SUPPLYREQUESTDETAILSPOPUP = "lgsHistoryDetails/supplyRequestDetailsPopup";
/*********Added for  VIEWALLREQUESTHISTORY ***********/
	 String VIEWALLREQUESTHISTORY ="lgsHistoryDetails/allRequestHistoryHome";
/*********Added for SERVICEREQUESTLISTPAGE***********/
	 String SERVICEREQUESTLISTPAGE ="serviceRequest/serviceRequestListPage";
/*********Added for  lgsHistoryDetails***********/
	 String LGSHISTORYDETAILS = "lgsHistoryDetails/";
/*********Added for  SUPPLYREQUESTDETAILS ***********/
	 String SUPPLYREQUESTDETAILS = "lgsHistoryDetails/supplyRequestDetails";
/*********Added for  RETURNSREQUESTDETAILS ***********/
	 String RETURNSREQUESTDETAILS = "lgsHistoryDetails/returnsRequestDetails";
/*********Added for  SERVICEREQUESTSPRINT ***********/
	 String SERVICEREQUESTSPRINT = "lgsHistoryDetails/serviceRequestsPrint";
	
	
	 /********Added for MPS 2.1 for HardwareRequestPopUp***********/

	 String HARDWAREDETAILSPOPUP = "lgsHistoryDetails/hwDetailsPopup";
	//END

	
	 /********Request Attributes & Parameters***********/

/*********Added for  TIMEZONEOFFSET_POPUP ***********/
	 String TIMEZONEOFFSET_POPUP = "timeZoneOffsetPopup";
/*********Added for  SRAREA ***********/
	 String SRAREA = "srArea";
/*********Added for  REQUESTFORM_POPUP ***********/
	 String REQUESTFORM_POPUP = "requestFormPopup";
/*********Added for  REQUESTFORM ***********/
	 String REQUESTFORM = "requestForm";
/*********Added for  DOWNLOADHISTORYLIST ***********/
	 String DOWNLOADHISTORYLIST = "downloadHistoryList";
/*********Added for  REQUESTYPEPAGE ***********/
	 String REQUESTYPEPAGE = "requestTypePage";
/*********Added for  SERVICEREQUESTS ***********/
	 String SERVICEREQUESTS = "SERVICE_REQUESTS";
/*********Added for  retrieveCMHistoryList***********/
	 String RETRIEVECMHISTORYLIST = "retrieveCMHistoryList";
/*********Added for  REQUESTTYPELOVMAP ***********/
	 String REQUESTTYPELOVMAP = "requestTypeLOVMap";
/*********Added for  SOURCEPAGE ***********/
	 String SOURCEPAGE = "sourcePage";

	// String ASSETROWID = "assetRowId";

/*********Added for  ACCOUNTROWID ***********/
	 String ACCOUNTROWID = "accountRowId";
/*********Added for  MDMLEVELFROMDETAILS ***********/
	 String MDMLEVELFROMDETAILS = "mdmLevelFromDetails";
/*********Added for  REQUESTTYPESTR ***********/
	 String REQUESTTYPESTR = "requestTypeStr";
/*********Added for  PAGEVIEW ***********/
	 String PAGEVIEW = "pageView";
/*********Added for  UPDATETYPEFLAG ***********/
	 String UPDATETYPEFLAG = "updateTypeFlag";
/*********Added for  USERSEGMENT ***********/
	 String USERSEGMENT = "userSegment";
/*********Added for  RETRIEVESRHISTORYLIST ***********/
	 String RETRIEVESRHISTORYLIST = "retrieveSRHistoryListXML";
/*********Added for  RETRIEVEASSOC_SRHISTORYLIST ***********/
	 String RETRIEVEASSOC_SRHISTORYLIST = "retrieveAssociatedSRHistoryListXML";
/*********Added for  SHIPMENTFORM ***********/
	 String SHIPMENTFORM = "shipmentForm";
/*********Added for  INPROCESSFORM ***********/
	 String INPROCESSFORM = "inProcessForm";
/*********Added for  SHIPMENT ***********/
	 String SHIPMENT = "shipment";
/*********Added for  ATTACHMENT_NAME ***********/
	 String ATTACHMENT_NAME = "attachmentName";
/*********Added for  DISP_ATTACHMENT_NAME ***********/
	 String DISP_ATTACHMENT_NAME = "dispAttachmentName";
/*********Added for  FILE_EXTENSION ***********/
	 String FILE_EXTENSION = "fileExtension";
/*********Added for  IDENTIFIER ***********/
	 String IDENTIFIER = "identifier";
/*********Added for  CONTROLPANELURL ***********/
	 String CONTROLPANELURL = "controlPanelURL";
/*********Added for  PAGENAME ***********/
	 String PAGENAME = "pageName";
/*********Added for  REQUESTDETAILS ***********/
	 String REQUESTDETAILS = "Request Details";
/*********Added for  CONTROLPANELPAGE ***********/
	 String CONTROLPANELPAGE = "controlPanelPage";
/*********Added for  SUBMITTED ***********/
	 String SUBMITTED = "Submitted";
/*********Added for  INPROGRESS ***********/
	 String INPROGRESS = "In Progess";
/*********Added for  INPROCESS ***********/
	 String INPROCESS = "Inprocess";
/*********Added for  COMPLETED ***********/
	 String SHIPPED = "Shipped";
/*********Added for  SHIPPED ***********/
	 String COMPLETED = "Completed";
/*********Added for  REQNO ***********/
	 String REQNO = "requestNumber";
/*********Added for  DOWNLOADTYPE ***********/
	 String DOWNLOADTYPE = "downloadType";
/*********Added for  PAGETYPE ***********/
	 String PAGETYPE = "pageType";

/*********Added for  LEXMARK_RECOMMENTED ***********/	
	 String LEXMARK_RECOMMENTED = "Lexmark Recommended";
/*********Added for  Authorized***********/
	 String STATUS_ATHORIZED = "Authorized";
	
	
	
	 /********Added for JAN release Cancel request***********/

	 String SUPPLIES_REQ = "Supplies";
	
	
	 /********Added for JAN release DeviceFinder back***********/

/*********Added for  backHistory***********/
	 String BACKFROMHISTORY = "backHistory";
/*********Added for  "lclick";***********/
	 String LINKCLICKED = "lclick";
/*********Added for  linkClicked***********/
	 String MODELLINKCLICKED ="linkClicked";
/*********Added for  CATALOG_ENTITLEMENT_FLAG ***********/
	
	 String CATALOG_ENTITLEMENT_FLAG = "CATALOG_ENTITLEMENT";
/*********Added for  ASSET_ENTITLEMENT_FLAG ***********/
	 String ASSET_ENTITLEMENT_FLAG = "ASSET_ENTITLEMENT";
/*********Added for  ISPARTNERPORTAL***********/
	
	 String ISPARTNERPORTAL="/group/partner-portal";
/*********Added for  requestExpedite***********/
	 String REQUESTEXPEDITE="requestExpedite";
/*********Added for  ACCOUNTLISTMAP***********/
	 String ACCOUNTLISTMAP="accountListMap";
/*********Added for  ACCOUNTLIST***********/
	 String ACCOUNTLIST="accountList";
/*********Added for  SIEBEL_ENTITLEMENT***********/
	 String SIEBEL_ENTITLEMENT_ACCOUNTS_ASSET_AND_CATALOG="SIEBEL_ENTITLEMENT_ACCOUNTS_ASSET_AND_CATALOG";
/*********Added for  GRID_CREATION_IDS_FOR_HISTORY***********/
	
	 String[] GRID_CREATION_IDS_FOR_HISTORY=new String[] {"allRequestHistory","changeRequestHistory","supplyRequestHistory","serviceRequestListGrid","hardwareRequestListGrid"};
/*********Added for  GRIDTYPE***********/
	 String GRIDTYPE="gridType";

	
	 /********Added for Asset Acceptance***********/

	 String ASSET_ACCEPTANCE = "AssetAcceptance"; 
 /********Added for ATTACHMENT_MAX_SIZE_ACCEPT ***********/
	String ATTACHMENT_MAX_SIZE_ACCEPT = "attachment.maxsize.twoMb";
 /********Added for ATTACHMENT_MAX_COUNT_ACCEPT ***********/
	String ATTACHMENT_MAX_COUNT_ACCEPT = "attachment.acceptmaxcount";
 /********Added for CUSTOMER_ACCEPTANCE **********/
	 String CUSTOMER_ACCEPTANCE = "Customer Acceptance";
	
	
	
	 /********Added for MPS 2.1 Wave 1 Consumables change***********/

 /********Added for hardwareCurrentDetails**********/
	 String HWCURRDETAILS = "hardwareCurrentDetails";
 /********Added for catalogCurrentDetails**********/
	 String CATCURRDETAILS = "catalogCurrentDetails";
 /********Added for assetCurrentDetails**********/
	 String ASSETCURRDETAILS = "assetCurrentDetails";
 /********Added for catalogFinalFlags**********/
	 String CATFINALFLAGS = "catalogFinalFlags";
 /********Added for catalogPriceMap**********/
	 String CATPRICEMAP = "catalogPriceMap";
 /********Added for hardwareFinalFlags**********/
	 String HWFINALFLAGS = "hardwareFinalFlags";
 /********Added for hardwarePriceMap**********/
	 String HWPRICEMAP = "hardwarePriceMap";
 /********Added for ASSET_PART_LIST **********/	
	 String ASSET_PART_LIST = "assetPartList";
 /********Added for ASSET_PART_LIST_FILTERED **********/
	 String ASSET_PART_LIST_FILTERED = "assetPartListFiltered";
/********Added for PAY_LATER_SOLDTO **********/	 
	 String PAY_LATER_SOLDTONUMBER = "soldToNumberForPayLaterCall";

/********Added for Ship and Bill**********/	
	 String PAYMENT_TYPE_PAY_NOW = "Ship and Bill";
	 /********Added for Indirect Billing**********/	
	 String PAYMENT_TYPE_INDIRECT_INVOICE = "Indirect Billing";
/********Added for Usage Based Billing**********/
	 String CONSUMABLE_PAYMENT_TYPE_UBB = "Usage Based Billing";
/********Added for Consumable Purchase**********/
	 String CONSUMABLE_PAYMENT_TYPE_PAY_LATER = "Consumable Purchase";
/********Added for Delayed Purchase**********/
	 String HW_PAYMENT_TYPE_PAY_LATER = "Delayed Purchase";
/********Added for Annuity Billing**********/
	 String HW_PAYMENT_TYPE_UBB = "Annuity Billing";
	
/********Added for ADDRESSCLEANSEFIELDS_OUTPUT**********/
	 String ADDRESSCLEANSEFIELDS_OUTPUT[]={
		"county","officeNumber",
		"stateCode","stateFullName","district",
		"region","latitude","longitude",
		"countryISOCode","savedErrorMessage"
		};
/********Added for ADDRESSCLEANSEFIELDS_INPUT**********/
	 String ADDRESSCLEANSEFIELDS_INPUT[]={
		"storeFrontName","addressLine1","addressLine2",
		"city","country","state",
		"postalCode","officeNumber"
		};
/********Added for  ADD_ASSET=**********/
	 String ADD_ASSET="Add Asset";
/********Added for FLEET_MANAGEMENT**********/
	 String FLEET_MANAGEMENT="Fleet Management";
	
	
	 /********ORDER MANAGEMENT HARDWARE ORDER AREA SUB AREA VALUE***********/

/********Added for Add Asset**********/
	 String HW_AREA = "HW Order";//updated for changed AREA values
/********Added for Hardware Shipment**********/
	 String HW_SUB_AREA = "BAU";
/********Added for HW_SR_TYPE **********/
	 String HW_SR_TYPE = "Fleet Management";
/********Added for Add Asset**********/
	 String INSTALL_HW_AREA = "Hardware-Ship and Install";//added for changed AREA values
	 
	
	
	 /********Added for ASset Acceptance Access MPS 2.1***********/

/********for ACCOUNT_MANAGEMENT**********/
	 String ACCOUNT_MANAGEMENT = "Account Management";
/********for SHOW_ASSET_ACCEPTANCE**********/
	 String SHOW_ASSET_ACCEPTANCE = "Show Asset Acceptance";
/********for ONLY_ASSET_ACCEPTANCE**********/
	 String ONLY_ASSET_ACCEPTANCE = "Only Asset Acceptance";
	
	 /********Added for Hardware link MPS 2.1***********/
	 String SHOW_HARDWARE_LINK = "ShowHardwareLink";
	
	
	
	 /********added for hardware csv and pdf***********/

/********for HARDWAREDOWNLOADFILENAME **********/
	String HARDWAREDOWNLOADFILENAME = "hardware.download.fileName";
/********for cmHardwareheader**********/
	String cmHardwareheader = "hardwareManagementHistory.csv.header";
/********for cmHardwareheaderpdf**********/
	String cmHardwareheaderpdf = "hardwareManagementHistory.pdf.header";
	/** File name for Customer invoice list*/
	String invoiceFileName="invoice.grid.download.fileName";
	/** CSV File header for Customer invoice list*/
	String downloadFildeHeader="customerInvoicePayments.listHeader.download.headerinvoiceLists";
	/** PDF File Generation columns*/
	String[] invoiceListColumns={"invoiceNumber","invoiceDate:formatDate" ,"dueDate:formatDate","paidDate:formatDate", "status", "amount:currencyType"};
	
	/** Constants for Customer In voice filter column*/
	String[] CUSTOMER_INVOICE_FILTER_COLUMN={"invoiceNumber","status"};	
	
	/***** Added for MADC Add asset  *****/
	 String ADDMADCASSETAREA = "Install";
	 String ADDMADCASSETSUBAREA = "BAU";
	 /***** Added for MADC Change asset  *****/
	 String CHANGEMADCASSETAREA = "Change";
	 String CHANGEMADCASSETSUBAREA = "BAU";
	 /***** Added for MADC Decommission asset  *****/
	 String DECOMMMADCASSETAREA = "Decommission";
	 String DECOMMMADCASSETSUBAREA = "BAU";
	 
	 /** Changed area/sub area for MADC asset*/
	 String AREA_ADDASSET_YES="MADC";
	 /** Asset Area*/
	 String AREA_ADDASSET_NO="Data Management";
	 /** Asset Area*/
	 String AREA_CHANGEASSET_YES="MADC";
	 /** Asset Area*/
	 String AREA_CHANGEASSET_NO="Data Management";
	 /** Asset Area*/
	 String AREA_DECOMMASSET_YES="MADC";
	 /** Asset Area*/
	 String AREA_DECOMMASSET_NO="Data Management";
	 
	 /** Asset Sub-Area*/
	 String SUBAREA_ADDASSET_YES="Install Asset";
	 /** Asset Sub-Area*/
	 String SUBAREA_ADDASSET_NO="Register Asset";
	 /** Asset Sub-Area*/
	 String SUBAREA_CHANGEASSET_YES="Move Asset";
	 /** Asset Sub-Area*/
	 String SUBAREA_CHANGEASSET_NO="Change Asset Data";
	 /** Asset Sub-Area*/
	 String SUBAREA_DECOMMASSET_YES="Decommission Asset";
	 /** Asset Sub-Area*/
	 String SUBAREA_DECOMMASSET_NO="Deregister Asset";
	 
	/** Changed Area for MADC Address/Contact/CHL/AssetAcceptance */
	String DATAMANAGEMENTAREA = "Data Management";
	
	String DATAMANAGEMENTSUBAREA = "Change Asset Data";
	
	/********Added for MULTIASSETLIST  *******/
	 String ASSETLIST = "multiAssetList";
	/********Added for changeMultiAssetRequest*******/
	 String CHANGEMULTIASSETREQTYPE = "changeMultiAssetRequest";
	 String DECOMMMULTIASSETREQTYPE = "decomMultiAssetRequest";
	
	/** added to improve performance*/
	String DEVICE_DETAIL = "DeviceDetail";
	String CONSUMABLE_DEVICE_DETAIL = "ConsumableDeviceDetail";
	String CM_DEVICE_DETAIL = "CmDeviceDetail";
	String CM_DECOMMISSION_DEVICE_DETAIL = "CmDecommissionDeviceDetail";
	String BREAKFIX_DEVICE_DETAIL = "BreakfixDeviceDetail";
	
	/** Hardware Area */
	String INSTALL="Install";
	/*********Added for  ACTIVITYSERIALNUMBER ***********/
	 String ACTIVITYSERIALNUMBER = "activitySerialNumber";
	 String INQUERYAREA = "Upload";
	 String UPLOADSUPPLIESREQUESTS="Upload Supplies Request";
	 String UPLOADCONSUMABLESRSREQUEST="Upload Consumable SRs Request";
	 String UPLOADEDCONSUMABLEREQUEST="Uploaded Consumable Request";
	 String ALLIANCE_PARTNER = "IS_ALLIANCE_PARTNER";
	 
	 
	 String MAPS_REQUEST = "MapsRequest";
	 String SHOW_MAPS_REQUEST = "LBS";	 
	 String SUBAREA_INSTALLDEINSTALL = "HW MADC Install/Decommission";
	 
	 String ADDRESSCLEANSEDREGION = "region";
}
