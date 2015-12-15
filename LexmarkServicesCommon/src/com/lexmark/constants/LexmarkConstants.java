package com.lexmark.constants;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lexmark.enums.CarrierEnum;

/**
 * @author Wipro
 *
 */
public class LexmarkConstants {

	//Update with each Deploy,  will presented by UserInterceptor when logging is in debug setting 
	//NOTE:  Only used by customer portal
	/**
	 * Variable Declaration
	 */
	public static final String PORTLET_BUILD_VERSION = "MAY 10  2014 10 PM V 1.83 (MPS 2.1 and CI 14.4 - Floor Support defect fix Release)";
	/**
	 * Variable Declaration
	 */
	//The guide to direct to different page
	public static final String PAGETYPE_RECENTSRLIST ="RecentSRList";
	/**
	 * Variable Declaration
	 */
	public static final String PAGETYPE_MYSRLIST ="MySRList";
	/**
	 * Variable Declaration
	 */
	public static final String VIEWTYPE_ALL = "ALL";
	/**
	 * Variable Declaration
	 */
	public static final String VIEWTYPE_MANAGED_DEVICES = "DFM";
	/**
	 * Variable Declaration
	 */
	public static final String VIEWTYPE_NON_MANAGED_DEVICES = "CSS";
	/**
	 * Variable Declaration
	 */
	public static final String ALL_PARTNER = "ALL_PARTNER";
	/**
	 * Variable Declaration
	 */
	public static final String DIRECT_PARTNER = "DIRECT_PARTNER";
	/**
	 * Variable Declaration
	 */
	public static final String INDIRECT_PARTNER = "INDIRECT_PARTNER";
	/**
	 * Variable Declaration
	 */
	public static final String TARGET_SYSTEM_SIEBEL = "Siebel";
	/**
	 * Variable Declaration
	 */
	//The check SR Status
	public static final String SRSTATUS_SCHEDULED ="Scheduled";
	/**
	 * Variable Declaration
	 */
	//The check Shipment Status
	public static final String SHIPMENTSTATUS_INPROCESS  ="In Process";
	/**
	 * Variable Declaration
	 */
	public static final String SHIPMENTSTATUS_SHIPPED  ="Shipped";
	/**
	 * Variable Declaration
	 */
	public static final String SHIPMENTSTATUS_DELIVERED  ="Delivered";
	/**
	 * Variable Declaration
	 */
	//The check Technician Status
	public static final String TECHNICIANSTATUS_INPROCESS  ="In Process";
	/**
	 * Variable Declaration
	 */
	public static final String TECHNICIANSTATUS_ASSIGNED  ="Assigned";
	/**
	 * Variable Declaration
	 */
	public static final String TECHNICIANSTATUS_COMPLETE  ="Complete";
	/**
	 * Variable Declaration
	 */
	public static final String TECHNICIANSTATUS_CANCELLED  ="Cancelled";
	/**
	 * Variable Declaration
	 */
	//Service Request Preview Count
	public static final int SERVICE_REQUEST_PREVIEW_COUNT = 10;
	/**
	 * Variable Declaration
	 */
	//Service Request List Page BreadCrumb
	public static final String SERVICE_REQUEST_BREADCRUMB_RECENT = "Recent Requests";
	/**
	 * Variable Declaration
	 */
	public static final String SERVICE_REQUEST_BREADCRUMB_MY = "My Service Requests";
	/**
	 * Variable Declaration
	 */
	//Service Request Page 2
	public static final String SERVICE_REQUEST_PORTLET_NAME = "ServiceRequest";
	/**
	 * Variable Declaration
	 */
	public static final String DEVICE_FINDER_PORTLET_NAME = "deviceFinder";
	/**
	 * Variable Declaration
	 */
	// Mdm Levels 
	public static final String MDM_LEVEL_GLOBAL = "Global";
	/**
	 * Variable Declaration
	 */
	public static final String MDM_LEVEL_DOMESTIC = "Domestic";
	/**
	 * Variable Declaration
	 */
	public static final String MDM_LEVEL_LEGAL = "Legal";
	/**
	 * Variable Declaration
	 */
	public static final String MDM_LEVEL_ACCOUNT = "Account";
	/**
	 * Variable Declaration
	 */
	public static final String MDM_LEVEL_SIEBEL = "Siebel";
	/**
	 * Variable Declaration
	 */
	//Popup of User Management
	public static final String IDM_MESSAGE_BUNDLE_NAME = "com.lexmark.resources.messages";
	/**
	 * Variable Declaration
	 */
	public static final String ROOT_NODE_ITEM_ID = "1";
	/**
	 * Variable Declaration
	 */
	public static final String GLOBAL = "Global";
	/**
	 * Variable Declaration
	 */
	public static final String DOMESTIC ="Domestic";
	/**
	 * Variable Declaration
	 */
	public static final String LEGAL ="Legal";
	/**
	 * Variable Declaration
	 */
	public static final String ACCOUNT ="Account";
	/**
	 * Variable Declaration
	 */
	public static final String CONTACTID = "CONTACTID";
	/**
	 * Variable Declaration
	 */
	public static final String MDMLEVEL = "MDMLEVEL";
	/**
	 * Variable Declaration
	 */
	public static final String MDMID = "MDMID";
	/**
	 * Variable Declaration
	 */
	public static final String USERNUMBER = "USERNUMBER";
	/**
	 * Variable Declaration
	 */
	public static final String FIRSTNAME = "FIRSTNAME";
	/**
	 * Variable Declaration
	 */
	public static final String LASTNAME = "LASTNAME";
	/**
	 * Variable Declaration
	 */
	public static final String WORKPHONE = "WORKPHONE";
	/**
	 * Variable Declaration
	 */
	public static final String ALTERNATEPHONE = "ALTERNATEPHONE";//Added by MPS offshore
	/**
	 * Variable Declaration
	 */
	public static final String LANGUAGE = "LANGUAGE";
	/**
	 * Variable Declaration
	 */
	public static final String COUNTRY = "COUNTRY";
	/**
	 * Variable Declaration
	 */
	public static final String EMAIL = "EMAIL";
	/**
	 * Variable Declaration
	 */
	public static final String USERROLES = "USERROLES";
	/**
	 * Variable Declaration
	 */
	public static final String USERSEGMENT = "USERSEGMENT";
	/**
	 * Variable Declaration
	 */
	public static final String SHORTID = "SHORTID";
	/**
	 * Variable Declaration
	 */
	public static final String COMPANYNAME = "COMPANYNAME";
	/**
	 * Variable Declaration
	 */
	

	public static final String SERVICES_USER_PHASE2 = "servicesUser_PHASE2";
	/**
	 * Variable Declaration
	 */
	public static final String LDAP_USER_DATA_PHASE2 = "ldapUserData_PHASE2";
	/**
	 * Variable Declaration
	 */
	public static final String SIEBEL_SESSION_ID_PHASE2 = "siebelSessionID_PHASE2";
	/**
	 * Variable Declaration
	 */
	public static final String DUNS_NUMBER_PHASE2 = "DunsNumber_PHASE2";
	/**
	 * Variable Declaration
	 */
	public static final String USERROLES_PHASE2 = "USERROLES_PHASE2";
	/**
	 * Variable Declaration
	 */

	
	public static final String SERVICES_USER = "servicesUser";
	/**
	 * Variable Declaration
	 */
	public static final String LDAP_USER_DATA = "ldapUserData";

	public static final String SERVICES_USER_COM = "servicesUserCom";
	/**
	 * Variable Declaration
	 */
	public static final String LDAP_USER_DATA_COM = "ldapUserDataCom";
	/**
	 * Variable Declaration
	 */
	/**
	 * Variable Declaration
	 */
	public static final String SIEBEL_SESSION_ID = "siebelSessionID";
	/**
	 * Variable Declaration
	 */
	public static final String DUNS_NUMBER = "DunsNumber";
	/**
	 * Variable Declaration
	 */
	
	public static final String OBIEE_SESSION = "obieeSession";
	/**
	 * Variable Declaration
	 */
	public static final String SIEBEL_ACCOUNT_ID = "siebelAccountId";
	/**
	 * Variable Declaration
	 */	
	public static final String SUBMIT_TOKEN = "globalsubmittoken";
	/**
	 * Variable Declaration
	 */
	//Path of JDBC properties
	public static final String JDBC_PROPERTIES_BUNDLE_NAME = "resource.jdbc";
	/**
	 * Variable Declaration
	 */
	public static final String PARTNER_PORTAL_CONTEXT_PATH = "/LexmarkPartnerPortal";
	/**
	 * Variable Declaration
	 */
	public static final String PRODUCT_NOT_FOUND_IMAGE_URL2 = "/images/printer_na_color.png";
	/**
	 * Variable Declaration
	 */
	public static final String PRODUCT_NOT_FOUND_IMAGE_URL = "/LexmarkServicesPortal/images/printer_na_color.png";
	/**
	 * Variable Declaration
	 */
	public static final String SERVICE_PORTAL_CONTEXT_PATH = "/LexmarkServicesPortal";
	/**
	 * Variable Declaration
	 */
	// User mode
	public static final String USER_MODE_CREATE = "CREATE";
	/**
	 * Variable Declaration
	 */
	public static final String USER_MODE_EDIT = "EDIT";
	/**
	 * Variable Declaration
	 */
	//User Roles
	public static final String ROLE_STANDARD_ACCESS = "Standard Access";
	/**
	 * Variable Declaration
	 */
	//New roles added by IDM
	public static final String ROLE_SERVICE_VIEW = "Service View";
	/**
	 * Variable Declaration
	 */
	public static final String ROLE_KEY_OPERATOR = "Key Operator";
	/**
	 * Variable Declaration
	 */
	public static final String ROLE_PARTNER_SECURE_SUPPORT = "Partner Secure Support";
	/**
	 * Variable Declaration
	 */
	public static final String ROLE_SERVICE_SUPPORT = "Service and Support";
	public static final String ROLE_SERVICE_SUPPORT_DUP = "service and support";   //added by sbag as the matched role in the LDAP is service and support defect #8925	
/**
	 * Variable Declaration
	 */
	public static final String ROLE_ACCOUNT_MANAGEMENT = "Account Management";
	/**
	 * Variable Declaration
	 */
	public static final String ROLE_BILLING = "Billing";
	/**
	 * Variable Declaration
	 */
	public static final String ROLE_PROJECT_MANAGEMENT = "Project Management";
	/**
	 * Variable Declaration
	 */
	public static final String ROLE_ANALYST = "Analyst";
	/**
	 * Variable Declaration
	 */
	public static final String ROLE_ACCOUNT_ADMINISTRATOR = "Account Administrator";
	/**
	 * Variable Declaration
	 */
	public static final String ROLE_SERVICES_PORTAL_ADMINISTRATOR = "Services Portal Administrator";
	/**
	 * Variable Declaration
	 */
	public static final String ROLE_PUBLISHING = "Publishing";
	/**
	 * Variable Declaration
	 */
	// add Roles for phase2
	public static final String ROLE_SERVICE_TECHNICIAN  = "Service Technician";
	/**
	 * Variable Declaration
	 */
	public static final String ROLE_SERVICE_MANAGER  = "Service Manager";
	/**
	 * Variable Declaration
	 */
	public static final String ROLE_ACCOUNTS_RECEIVABLE  = "Accounts Receivable";
	/**
	 * Variable Declaration
	 */
	public static final String ROLE_SERVICE_ADMINISTRATOR  = "Service Administrator";
	/**
	 * Variable Declaration
	 */
	public static final String ROLE_PARTNER_ADMINISTRATOR  = "Partner Administrator";
	/**
	 * Variable Declaration
	 */
	//Added roles for MPS 2.1
	public static final String ROLE_PURCHASING = "Purchasing";
	/**
	 * Variable Declaration
	 */
	//Dynamic roles for Claims and Request Uploader
	public static final String ROLE_UPLOAD_CLAIM = "Global Services Partner Upload Claim";
	/**
	 * Variable Declaration
	 */
	public static final String ROLE_UPLOAD_REQUEST = "Global Services Partner Upload Request";
	/**
	 * Variable Declaration
	 */
	public static final String ROLE_FULFILL_ORDERS = "Global Services Partner Consumables Order Fulfillment";
	/**
	 * Variable Declaration
	 */
	public static final String ROLE_CUSTREQ_ORDERS = "Global Services Partner Create Customer Orders";
	/**
	 * Variable Declaration
	 */
	// USER SEGMENT types
	public static final String USER_SEGMENT_PARTNER = "partner";
	/**
	 * Variable Declaration
	 */
	public static final String USER_SEGMENT_EMPLOYEE = "employee";
	/**
	 * Variable Declaration
	 */
	// Category Types
	public static final String CATEGORY_TYPE_REPORT = "R";
	/**
	 * Variable Declaration
	 */
	public static final String CATEGORY_TYPE_DOCUMENT = "D";
	/**
	 * Variable Declaration
	 */
	public static final String IDM_PROPERTIES_BUNDLE_NAME = "userservice.properties";
	/**
	 * Variable Declaration
	 */
	public static final String CHECKBOX_CHECKED_VALUE = "on";
	/**
	 * Variable Declaration
	 */
	public static final String METER_READ_MANUAL = "Manual";
	/**
	 * Variable Declaration
	 */
	// Exceptions
	public static final String DUPLICATED_CATEGORY_NAME = "The category name can not be duplicated.";
	/**
	 * Variable Declaration
	 */
	public static final String DUPLICATED_REPORT_NAME = "The report name can not be duplicated.";
	/**
	 * Variable Declaration
	 */
	public static final String REPORT_DEFINITION_ASSOCIATED = "Valid report definitions are associated";
	/**
	 * Variable Declaration
	 */
	public static final String DOC_DEFINITION_ASSOCIATED = "Valid document definitions are associated";
	/**
	 * Variable Declaration
	 */
	public static final int MILLISECONDS_IN_HOUR = 3600000;
	/**
	 * Variable Declaration
	 */
	public static final int MINUTES_IN_HOUR = 60;
	/**
	 * Variable Declaration
	 */
	public static final String XLS_TYPE = "xls";
	/**
	 * Variable Declaration
	 */
	public static final String EXCEL_TYPE = "excel";
	/**
	 * Variable Declaration
	 */
	public static final String PDF_TYPE = "pdf";
	/**
	 * Variable Declaration
	 */
	public static final String VM_THEME_MARKER = "LGS-CUSTOMER-";
	/**
	 * Variable Declaration
	 */
	public static final String PORTAL_CUSTOMER = "Customer";
	/**
	 * Variable Declaration
	 */
	public static final String PORTAL_PARTNER = "Partner";
	/**
	 * Variable Declaration
	 */
	public static final String ROLE_TYPE_INTERNAL = "Internal";
	/**
	 * Variable Declaration
	 */
	public static final String ROLE_TYPE_EXTERNAL = "External";
	/**
	 * Variable Declaration
	 */
	public static final String DATE_FORMAT_ENGLISH = "MM/dd/yyyy";
	/**
	 * Variable Declaration
	 */
	public static final String DATE_FORMAT_GERMAN = "dd.MM.yyyy";
	/**
	 * Variable Declaration
	 */
	public static final String DATE_FORMAT_SPANISH = "dd/MM/yyyy";
	/**
	 * Variable Declaration
	 */
	public static final String DATE_FORMAT_FRANCE = "dd/MM/yyyy";
	/**
	 * Variable Declaration
	 */
	public static final String DATE_FORMAT_ITALIAN = "dd/MM/yyyy";
	/**
	 * Variable Declaration
	 */
	public static final String DATE_FORMAT_TURKISH = "dd/MM/yyyy"; // Added by Arunava for LEX:AIR00071181
	/**
	 * Variable Declaration
	 */
	public static final String DATE_FORMAT_JAPAN = "yyyy/MM/dd";
	/**
	 * Variable Declaration
	 */
	public static final String DATE_FORMAT_KOREA = "yyyy.MM.dd";
	/**
	 * Variable Declaration
	 */
	public static final String DATE_FORMAT_PORTUGAL = "dd/MM/yyyy";
	/**
	 * Variable Declaration
	 */
	public static final String DATE_FORMAT_RUSSIAN = "dd-MM-yyyy";
	/**
	 * Variable Declaration
	 */
	public static final String DATE_FORMAT_CHINESE = "yyyy-MM-dd";
	/**
	 * Variable Declaration
	 */
	public static final String DATE_FORMAT_POLISH = "yyyy-MM-dd";//Added by sankha for LEX:AIR00066285
	/**
	 * Variable Declaration
	 */
	
	public static final List<String> CARRIER_LIST =
		Arrays.asList(CarrierEnum.FEDEX.getValue(), CarrierEnum.UPS.getValue(), CarrierEnum.DHL.getValue());
	/**
	 * Variable Declaration
	 */
	//used for address status
	public static final String ADDRESS_STATUS_DO_NOT_SHIP_PARTS = "Do Not Ship Parts";
	/**
	 * Variable Declaration
	 */
	public static final String ADDRESS_STATUS_SHIP_TO_CUSTOMER = "Ship to Customer";
	/**
	 * Variable Declaration
	 */
	public static final String ADDRESS_STATUS_SHIP_TO_SERVICE_PARTNER = "Ship to Service Partner";
	/**
	 * Variable Declaration
	 */
	public static final String ADDRESS_STATUS_PARTNER_TO_PROVIDE = "Partner to Provide";
	/**
	 * Variable Declaration
	 */
	// Partner Type
	public static final String PARTNER_TYPE_DIRECT = "DIRECT";
	/**
	 * Variable Declaration
	 */
	public static final String PARTNER_TYPE_INDIRECT = "INDIRECT";
	/**
	 * Variable Declaration
	 */
	public static final String PARTNER_TYPE_BOTH = "BOTH";
	/**
	 * Variable Declaration
	 */
	public static final SimpleDateFormat DEFAULT_DATE_FORMAT = new SimpleDateFormat("MM/dd/yyyy");
	/**
	 * Variable Declaration
	 */
	public static final String TIMEZONE_OFFSET = "timezoneOffset";
	/**
	 * Variable Declaration
	 */
	public static final String ZERO_SECOND = ":00";
	/**
	 * Variable Declaration
	 */
	//Added for constant for MPS
	public static final String NO_MATCH_ROW_ID = "No Match Row Id";
	/**
	 * Variable Declaration
	 */
	//Added for CI-6 Partha for Chart Portlet
	public static final String  REPORT_FAILURE_RATE  = "Failure Rate";
	/**
	 * Variable Declaration
	 */
	public static final String  REPORT_RESPONSESLAE_RATE  = "Response SLA Rate";
	/**
	 * Variable Declaration
	 */
	public static final String  REPORT_RESOLUTIONSLAE_RATE  = "Resolution SLA Rate"; 
	/**
	 * Variable Declaration
	 */
	public static final String  REPORT_PHONEFIXE_RATE  = "Phone Fix Rate";
	/**
	 * Variable Declaration
	 */
	public static final String  REPORT_SERVICEE_RATE  = "Service Rate";
	/**
	 * Variable Declaration
	 */
	public static final String  REPORT_NUMBEROFSR  = "Number of SR's"; 
	/**
	 * Variable Declaration
	 */
	public static final String  REPORT_FCCE_RATE  = "FCC Rate";
	/**
	 * Variable Declaration
	 */
	
	public static final String  LEFT_CHART_WINDOW_PORTLET  = "Chart_WAR_LexmarkServicesPortal_INSTANCE_KY7r";
	/**
	 * Variable Declaration
	 */
	public static final String  RIGHT_CHART_WINDOW_PORTLET  = "Chart_WAR_LexmarkServicesPortal_INSTANCE_8jpM";
	/**
	 * Variable Declaration
	 */
	
	public static final Map<String, String > DEFAULT_CHART_MAP = new HashMap<String, String>(){
																	{
																		put("LEFT_CHART", REPORT_PHONEFIXE_RATE);
																		put("RIGHT_CHART", REPORT_RESPONSESLAE_RATE);
																	}
																	};
	//Added for MPS Phase 2											
	/**
	 * Variable Declaration
	 */
    public static final String[] gridSavingParams={"colsOrder","colsWidth","colsSorting","colsHidden"};
    /**
	 * Variable Declaration
	 */
    public static final String SERVICE_ORDER_RESOURCE_URL_MAPPING="retrieveOrderRequestList";
	/**
	 * Variable Declaration
	 */
	public static final String gridConfigurationValues[]={"_Header","_Filter","_ColAlign","_ColWidth","_ColType","_ColSorting","_ColLOCKVisibility","_DefaultColSorting","_DefaultHiddenColumns"};
	/**
	 * Variable Declaration
	 */
	public static final String JSON_STATUS="status";
	/**
	 * Variable Declaration
	 */
	public static final String JSON_STATUS_AVAILABLE="available";
	/**
	 * Variable Declaration
	 */
	public static final String JSON_STATUS_FAILURE="failure";
	/**
	 * Variable Declaration
	 */
	public static final String JSON_RESOURCE_URL="ajaxURL";
	/**
	 * Variable Declaration
	 */
	public static final String JSON_COMBO_FILTER="comboFilter";
	/**
	 * Variable Declaration
	 */
	public static final String CONTRACT="massUpload_contract_";
	/**
	 * Variable Declaration
	 */
	public static final String CUSTOMERACCOUNTID="customerAccountId";
	
	public static final String addressArr[]={
		
		"addressId","addressName",
		"storeFrontName","addressLine1","addressLine2",
		"city","country","state","province",
		"postalCode",
		"county",
		"stateCode","stateFullName","district",
		"region","latitude","longitude",
		"countryISOCode","officeNumber","savedErrorMessage",
		"physicalLocation1","physicalLocation2","physicalLocation3"
		};
	
	/**
	 * Added for BRD 14-07-14
	 * */
	public static final String SIEBEL_PARTNER_COUNTRIES="siebel_partnerCountriesList";
	
	/**
	 * Added for BRD 14-07-14
	 * */
	public static final String SIEBEL_PARTNER_TYPES="siebel_partnerTypesList";
	
	
	/**
	 * Added for LBS
	 * */
	public static final String STATE="STATE";
	/**
	 * Added for LBS
	 * */
	public static final String CITY="CITY";
	

	public static final String OPS_ACCOUNT_SELECTED="ops_accountSelected";
	
	public static final String SIEBEL_SESSION_ID_OPS = "siebelSessionIDOPS";
	
	public static final String SIEBEL_SESSION_ID_B2B = "siebelSessionIDB2B";
}
