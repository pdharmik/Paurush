package com.lexmark.services.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.portlet.PortletSession;
import javax.portlet.ResourceRequest;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import com.lexmark.domain.Pagination;

public class PaginationUtil {

	/**
	 * 
	 */
	public static final String COUNT = "count";
	/**
	 * 
	 */
	public static final String POS_START = "posStart";
	/**
	 * 
	 */
	public static final String DIRECTION = "direction";
	/**
	 * 
	 */
	public static final String ORDER_BY = "orderBy";
	/**
	 * 
	 */
	public static final String FILTER_CRITERIAS = "filterCriterias";
	/**
	 * 
	 */
	public static final String FILTER_CRITERIAS_SEPERATOR = ",";
	/**
	 * 
	 */
	public static final String SEARCH_CRITERIAS = "searchCriterias";
	/**
	 * 
	 */
	public static final String SEARCH_CRITERIAS_SEPERATOR = "__";
	/**
	 * 
	 */
	public static final String SEARCH_CRITERIA_NAME_VALUE_SPERATOR = "^";
	
	private static Logger LOGGER = LogManager.getLogger(PaginationUtil.class);

	//private static final String[] SERVICE_REQUEST_FILTER_COLUMNS = new String[]{"serviceRequestNumber","serviceRequestNumber","asset.serialNumber","asset.productLine","problemDescription","serviceAddress.addressName","serviceRequestStatus","serviceAddress.city","serviceAddress.state","serviceAddress.province","serviceAddress.postalCode","serviceAddress.country", "primaryContact.firstName","primaryContact.lastName","primaryContact.emailAddress","primaryContact.workPhone"};
    // Start Dwijen
    //private static final String[] SERVICE_REQUEST_FILTER_COLUMNS = new String[] { "serviceRequestNumber", "serviceRequestNumber", "asset.serialNumber", "asset.productLine","problemDescription","serviceAddress.addressName", "serviceRequestStatus", "serviceAddress.city", "serviceAddress.state", "serviceAddress.province", "serviceAddress.postalCode", "serviceAddress.country", "custRefNumber", "primaryContact.firstName", "primaryContact.lastName", "primaryContact.emailAddress", "primaryContact.workPhone","contractType", "asset.deviceTag", "asset.hostName", "costCenter"};
// Change CI -6   
//For MPS Phase 2.1 , // Changed for LEX:AIR00075895
	private static final String[] SERVICE_REQUEST_FILTER_COLUMNS = new String[] { "serviceRequestNumber", "asset.serialNumber", "productModel","problemDescription", "resolutionCode", "serviceRequestStatus","accountName", "serviceAddress.addressName", "serviceAddress.officeNumber", "serviceAddress.city", "serviceAddress.state", "serviceAddress.province", "serviceAddress.county", "serviceAddress.district", "serviceAddress.postalCode", "serviceAddress.country", "helpDeskReferenceNumber", "primaryContact.firstName", "primaryContact.lastName", "primaryContact.emailAddress", "primaryContact.workPhone", "contractType", "asset.deviceTag", "asset.hostName", "asset.assetCostCenter"};
    //productLine,assetTag,deviceTag,devicePhase, hostName, ipAddress,
    //For MPS Phase 2.1 
    private static final String[] CONSUMABLE_ASSET_REQUEST_FILTER_COLUMNS = new String[]{"", "serialNumber","deviceTag", 
		"descriptionLocalLang", "assetTag", "hostName", "ipAddress" ,"account.accountName", "installAddress.addressName", 
		"devicePhase","installAddress.addressLine1","installAddress.officeNumber", "installAddress.city","installAddress.state","installAddress.province","installAddress.county","installAddress.district","installAddress.country",
		"installAddress.postalCode", "assetContact.firstName", "assetContact.lastName", "assetContact.emailAddress", "assetContact.workPhone"};
    
    //Added by Sourav For Asset List- Change Management
    private static final String[] ASSET_REQUEST_FILTER_COLUMNS = new String[] {"serialNumber","serialNumber","serialNumber",
		"serialNumber", "productLine", 
		"assetTag", "deviceTag", "hostName", "ipAddress", "devicePhase", "account.accountName", "installAddress.addressName"
		,"installAddress.addressLine1", "installAddress.officeNumber", "installAddress.city","installAddress.state",
		"installAddress.province","installAddress.county", "installAddress.district", "installAddress.country", "installAddress.postalCode",
		"assetContact.firstName", "assetContact.lastName", "assetContact.emailAddress",
		"assetContact.workPhone"};
    
	private static final String[] PAGE_COUNT_FILTER_COLUMNS =  new String[]{"serialNumber","productLine","assetTag","ipAddress", "installAddress.addressName","installAddress.officeNumber","installAddress.city","installAddress.state","installAddress.province","installAddress.county","installAddress.district","installAddress.country","hostName","macAddress","deviceTag","modelNumber","productTLI","physicalLocation1","assetContact.firstName","assetContact.lastName"};
	//For MPS Phase 2.1 	
	//Office No. added for CI Defect #9183
	private static final String [] HARDWAREQUESTS_FILTER_COLUMNS = new String[]{"serviceRequestNumber","area","subArea","serviceRequestStatus","poNumber","costCenter","accountName",
			"serviceAddress.addressName","serviceAddress.officeNumber","serviceAddress.storeFrontName","serviceAddress.addressLine1","serviceAddress.city","serviceAddress.state",
			"serviceAddress.province","serviceAddress.county","serviceAddress.district","serviceAddress.country","serviceAddress.postalCode","primaryContact.firstName","primaryContact.lastName","primaryContact.emailAddress",
			"primaryContact.workPhone","requestor.firstName","requestor.lastName","requestor.emailAddress","requestor.workPhone"};
	/*//Added by Sourav For Device Management
    
    private static final String[] DEVICE_REQUEST_FILTER_COLUMNS = new String[] {"serialNumber","serialNumber", "productLine", 
		"assetTag", "hostName", "deviceTag", "ipAddress", "installAddress.addressName", "contractType", 
		"devicePhase", "installAddress.addressLine1", "installAddress.city","installAddress.state",
		"installAddress.province","installAddress.country","installAddress.postalCode",
		"assetContact.firstName", "assetContact.lastName", "assetContact.emailAddress",
		"assetContact.workPhone"};*/
    
	/**
	 * 
	 * @param request 
	 * @param filterColumns 
	 * @param requestedFrom 
	 * @return Pagination 
	 */
	@SuppressWarnings("unchecked")
	public static Pagination getPainationFromRequest(ResourceRequest request, String[] filterColumns, String requestedFrom) {
		Pagination page = new Pagination();
		String filterCriterias = request.getParameter(FILTER_CRITERIAS);
		LOGGER.debug("PaginationUtil.getPainationFromRequest.requestedFrom-->"+requestedFrom);
		LOGGER.debug("PaginationUtil.getPainationFromRequest.filterCriterias-->"+filterCriterias);
		if(filterCriterias != null && filterCriterias.length()>0) {
			String[] values = filterCriterias.split(FILTER_CRITERIAS_SEPERATOR);
			int lovLen = values.length;
			for(int i=0; i< lovLen; i++) {
				String v = values[i].trim();				
				LOGGER.debug(i+"---PaginationUtil.getPainationFromRequest.v ---->"+v);				
				if (requestedFrom.equalsIgnoreCase("ServiceRequestList")){
					if(v != null&& v.length()>0 && i< SERVICE_REQUEST_FILTER_COLUMNS.length) {
						page.getFilterCriteria().put(SERVICE_REQUEST_FILTER_COLUMNS[i] , v);;

					}					
				}else if (requestedFrom.equalsIgnoreCase("PageCountsList")){
					if(v != null&& v.length()>0 && i< PAGE_COUNT_FILTER_COLUMNS.length) {
						page.getFilterCriteria().put(PAGE_COUNT_FILTER_COLUMNS[i] , v);;

					}										
				}else if(requestedFrom.equalsIgnoreCase("ALL_REQUESTS")){
					//LOGGER.debug("PaginationUtil.getPainationFromRequest.ALL_REQUESTS......:"+ChangeMgmtConstant.ALL_REQUEST_HISTORY_FILTER_COLUMNS[i]);	
					if(v != null && v.length()>0 && i< ChangeMgmtConstant.ALL_REQUEST_HISTORY_FILTER_COLUMNS.length) {
						page.getFilterCriteria().put(ChangeMgmtConstant.ALL_REQUEST_HISTORY_FILTER_COLUMNS[i] , v);
						LOGGER.debug(i+"--XXXXXXXXXXXX page.getFilterCriteria() --->"+page.getFilterCriteria());
					}	
					
				}else if(requestedFrom.equalsIgnoreCase("SUPPLY_REQUESTS")){
					LOGGER.debug("PaginationUtil.getPainationFromRequest.SUPPLY_REQUESTS.....:"+ChangeMgmtConstant.SUPPLY_REQUEST_HISTORY_FILTER_COLUMNS[i]);
					if(v != null&& v.length()>0 && i< ChangeMgmtConstant.SUPPLY_REQUEST_HISTORY_FILTER_COLUMNS.length) {
						page.getFilterCriteria().put(ChangeMgmtConstant.SUPPLY_REQUEST_HISTORY_FILTER_COLUMNS[i] , v);
						LOGGER.debug(i+"--YYYYYY page.getFilterCriteria() --->"+page.getFilterCriteria());
					}
					
				}else if(requestedFrom.equalsIgnoreCase("CHANGE_REQUESTS")){
					LOGGER.debug("PaginationUtil.getPainationFromRequest.CHANGE_REQUESTS.....:"+ChangeMgmtConstant.CHANGE_REQUEST_HISTORY_FILTER_COLUMNS[i]);
					if(v != null&& v.length()>0 && i< ChangeMgmtConstant.CHANGE_REQUEST_HISTORY_FILTER_COLUMNS.length) {
						page.getFilterCriteria().put(ChangeMgmtConstant.CHANGE_REQUEST_HISTORY_FILTER_COLUMNS[i] , v);
						LOGGER.debug(i+"--ZZZZZ page.getFilterCriteria() --->"+page.getFilterCriteria());
					}
				}else if(requestedFrom.equalsIgnoreCase("ConsumableAssetList")){	
					LOGGER.debug("PaginationUtil.getPainationFromRequest.ConsumableAssetList.....:"+CONSUMABLE_ASSET_REQUEST_FILTER_COLUMNS[i]);
					if(v != null&& v.length()>0 && i< CONSUMABLE_ASSET_REQUEST_FILTER_COLUMNS.length) {
						page.getFilterCriteria().put(CONSUMABLE_ASSET_REQUEST_FILTER_COLUMNS[i] , v);
						LOGGER.debug(i+"--OOOOOOOOO page.getFilterCriteria() --->"+page.getFilterCriteria());
					}
				}
				else if(requestedFrom.equals(ChangeMgmtConstant.HARDWARE_REQUESTS)){
					LOGGER.debug("PaginationUtil.getPainationFromRequest.HARDWARELIST.....:"+HARDWAREQUESTS_FILTER_COLUMNS[i]);
					if(v != null&& v.length()>0 && i< HARDWAREQUESTS_FILTER_COLUMNS.length) {
						page.getFilterCriteria().put(HARDWAREQUESTS_FILTER_COLUMNS[i] , v);
						LOGGER.debug(i+"hardwarelist page.getFilterCriteria() --->"+page.getFilterCriteria());
					}
					
				}else{
					LOGGER.debug("NO requestedFrom--->"+requestedFrom);
					if(v != null&& v.length()>0 && i< filterColumns.length) {
						page.getFilterCriteria().put(filterColumns[i] , v);

					}					
				}
			}
		}
		
		// role based access for requestType is implemented 
		LOGGER.debug("Retuest Type already set from grid Request Type dropdown :"+page.getFilterCriteria().get("requestType"));
		List<String> reqTypeValues = new ArrayList<String>();
		if(requestedFrom.equalsIgnoreCase("ALL_REQUESTS") ){
			if(page.getFilterCriteria().get("requestType") != null){
				String reqType = (String) page.getFilterCriteria().get("requestType");
				reqTypeValues.add(reqType);
				page.getFilterCriteria().put("requestType", reqTypeValues);
			}else{
			
				Map<String,String> accessMap = (Map<String, String>) request.getPortletSession().getAttribute(ChangeMgmtConstant.USERACCESSMAPATTRIBUTEFORSR,PortletSession.APPLICATION_SCOPE);
				LOGGER.debug("ContractFactory.getHistoryListContract.accessMap--->"+accessMap);
				if(null != accessMap && !accessMap.isEmpty()){
					
					if(accessMap.containsKey("SHOW SUPPLIES REQUEST") && accessMap.get("SHOW SUPPLIES REQUEST").toString().equalsIgnoreCase("true")){
						reqTypeValues.add(ChangeMgmtConstant.SRTYPE_SUPPLYMANAGEMENT);
					}if(accessMap.containsKey("SHOW CHANGE MGMT REQUEST") && accessMap.get("SHOW CHANGE MGMT REQUEST").toString().equalsIgnoreCase("true")){
						reqTypeValues.add(ChangeMgmtConstant.SRTYPE_CHANGEMANAGEMENT);
					}if(accessMap.containsKey("SHOW SERVICE REQUEST") && accessMap.get("SHOW SERVICE REQUEST").toString().equalsIgnoreCase("true")){
						reqTypeValues.add(ChangeMgmtConstant.SRTYPE_BREAKFIX);
					}if(accessMap.containsKey("SHOW HARDWARE REQUEST") && accessMap.get("SHOW HARDWARE REQUEST").toString().equalsIgnoreCase("true")){
						reqTypeValues.add(ChangeMgmtConstant.FLEET_MANAGEMENT);
					}
					
					page.getFilterCriteria().put("requestType", reqTypeValues);
				}
			}
		}else if(requestedFrom.equalsIgnoreCase("SUPPLY_REQUESTS")){
			reqTypeValues.add(ChangeMgmtConstant.SRTYPE_SUPPLYMANAGEMENT);
			page.getFilterCriteria().put("requestType", reqTypeValues);
		}else if(requestedFrom.equalsIgnoreCase("CHANGE_REQUESTS")){
			reqTypeValues.add(ChangeMgmtConstant.SRTYPE_CHANGEMANAGEMENT);
			 page.getFilterCriteria().put("requestType", reqTypeValues);
		}else if(requestedFrom.equalsIgnoreCase("ServiceRequestList")){
			reqTypeValues.add(ChangeMgmtConstant.SRTYPE_BREAKFIX);
			page.getFilterCriteria().put("requestType", reqTypeValues);
		}
		
		
		
		String searchCriterias = request.getParameter(SEARCH_CRITERIAS);
		LOGGER.debug("PaginationUtil.getPainationFromRequest.searchCriterias-->"+searchCriterias);
		if(searchCriterias != null && searchCriterias.length()>0) {
			String[] values = searchCriterias.split(SEARCH_CRITERIAS_SEPERATOR);
			int lovLen = values.length;
			for(int i=0; i< lovLen; i++) {
				String v = values[i].trim();
				if(v == null || v.indexOf(SEARCH_CRITERIA_NAME_VALUE_SPERATOR)<0) {
					continue;
				} else {
					String searchKey = v.substring(0, v.indexOf(SEARCH_CRITERIA_NAME_VALUE_SPERATOR));
					String searchValue = v.substring(v.indexOf(SEARCH_CRITERIA_NAME_VALUE_SPERATOR)+1);
					Object valueObject = page.getSearchCriteria().get(searchKey);
					if(valueObject == null) {
						page.getSearchCriteria().put(searchKey, searchValue);
					} else {
						List<String> searchCriteriaForOneKey = null;	
					if(valueObject instanceof String) {
						searchCriteriaForOneKey = new ArrayList<String>();
						searchCriteriaForOneKey.add((String)valueObject);
					} else {
						searchCriteriaForOneKey = (List<String>) page.getSearchCriteria().get(searchKey);
					}
					searchCriteriaForOneKey.add(searchValue);
					page.getSearchCriteria().put(searchKey, searchCriteriaForOneKey);
					}
				}
			}
		}
		
		LOGGER.debug("PaginationUtil.FilterCriteriaMap--->"+page.getFilterCriteria());
		LOGGER.debug("PaginationUtil.SearchCriteriaMap--->"+page.getSearchCriteria());
		
		String orderBy = request.getParameter(ORDER_BY);
		if(orderBy == null || orderBy.equals("")) {
			orderBy = "0";
		}
		orderBy = filterColumns[Integer.valueOf(orderBy)];
		page.setOrderBy(orderBy);

		String direction = request.getParameter(DIRECTION);
		
		if(direction == null || !direction.substring(0,3).equalsIgnoreCase(Pagination.ORDER_DESC.substring(0,3))) {
			page.setDirection(Pagination.ORDER_ASC);
		} else {
			page.setDirection(Pagination.ORDER_DESC);
		}
		
		
		String posStart = request.getParameter(POS_START);
		if(posStart == null){
			posStart = "0";}
		page.setStartPosition(Integer.valueOf(posStart));
		page.setCount(Pagination.DEFAULT_COUNT);
		return page;
	}
	/**
	 * 
	 * @param request 
	 * @param filterColumns 
	 * @param requestedFrom 
	 * @return Pagination 
	 */
public static Pagination getPaginationFromRequest(ResourceRequest request, String[] filterColumns, String requestedFrom) {
	
		Pagination page = new Pagination();
		String filterCriterias = request.getParameter(FILTER_CRITERIAS);
		LOGGER.debug("PaginationUtil.getPaginationFromRequest.requestedFrom-->"+requestedFrom);
		LOGGER.debug("PaginationUtil.getPaginationFromRequest.filterCriterias-->"+filterCriterias);
		
		
		if(filterCriterias != null && filterCriterias.length()>0) {
			String[] values = filterCriterias.split(FILTER_CRITERIAS_SEPERATOR);
			int lovLen = values.length;
			for(int i=0; i< lovLen; i++) {
				String v = values[i].trim();
				LOGGER.debug(i+"---PaginationUtil.getPaginationFromRequest.v ---->"+v);				
				
				if (requestedFrom.equalsIgnoreCase("AssetList")){
					if(v != null&& v.length()>0 && i< ASSET_REQUEST_FILTER_COLUMNS.length) {
						
						LOGGER.debug("ASSET_REQUEST_FILTER_COLUMNS[i] "+ ASSET_REQUEST_FILTER_COLUMNS[i]);
						
						page.getFilterCriteria().put(ASSET_REQUEST_FILTER_COLUMNS[i] , v);

					}
			}
		}
	}			
			
		LOGGER.debug("PaginationUtil.FilterCriteriaMap--->"+page.getFilterCriteria());
		LOGGER.debug("PaginationUtil.SearchCriteriaMap--->"+page.getSearchCriteria());
		
		String orderBy = request.getParameter(ORDER_BY);
		LOGGER.debug("Order by is " + orderBy);
		
		if(orderBy == null || orderBy.equals("")) {
			orderBy = "0";
		}
		orderBy = filterColumns[Integer.valueOf(orderBy)];
		page.setOrderBy(orderBy);

		String direction = request.getParameter(DIRECTION);
		LOGGER.debug("direction is " + direction);
		
		if(direction == null || !direction.substring(0,3).equalsIgnoreCase(Pagination.ORDER_DESC.substring(0,3))) {
			page.setDirection(Pagination.ORDER_ASC);
		} else {
			page.setDirection(Pagination.ORDER_DESC);
		}
		
		String posStart = request.getParameter(POS_START);
		LOGGER.debug("start position is " + posStart);
		if(posStart == null){
			posStart = "0";}
		page.setStartPosition(Integer.valueOf(posStart));
		page.setCount(Pagination.DEFAULT_COUNT);
		return page;
	}
}
