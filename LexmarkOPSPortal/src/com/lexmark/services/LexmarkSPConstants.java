package com.lexmark.services;

/**
 * Constant class for Services Portal
 * @author Wipro 
 * @version 2.1 
 * */

public class LexmarkSPConstants {
	/**public static final String OPTION_TYPE_ENTITLEMENT_SERVICE_DETAILS = "Entitlement Service Details"; **/
	/** Message bundle error code key*/
	public static final String ERROR_MESSAGE_BUNDLE = "com.lexmark.services.resources.messages";
	/** Message bundle key*/
	public static final String MESSAGE_BUNDLE_NAME = "com.lexmark.services.resources.messages";
	/** Message bundle error code key*/
	public static final String ERROR_CODE_SIEBEL_VALUE_IS_NULL = "srSave.siebelValueNull";	
	/** Message bundle error code key*/
	public static final String ERROR_CODE_CONFIRM_LOCATION_DELETE = "serviceRequestLocaleListPage.text.confirmDelete";
	/** asset list type */
	public static final String ASSET_LIST_TYPE = "PrintSelection";
	/** root node id for tree*/
	public static final String ROOT_NODE_ID = "0";
	/** variable for tree */
	public static final String TREE_LEVEL_1 = "0_0";
	 /** variable name  hardware install export*/
	public static  String[] PATTERNS_HARDWARE_INSTALL_EXPORT = {
    	"partNumber",
    	"partDesc",
    	"partType",
    	"model",
    	"partQuantity"
     };
	
}
