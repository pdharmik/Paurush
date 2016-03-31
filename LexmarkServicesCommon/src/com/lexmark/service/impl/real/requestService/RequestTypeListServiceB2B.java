package com.lexmark.service.impl.real.requestService;

import static com.lexmark.service.impl.real.requestService.AmindRequestTypeConversionUtil.convertRequestTypeList;
import static com.lexmark.service.impl.real.util.AmindServiceUtil.buildCriteria;
import static com.lexmark.service.impl.real.util.AmindServiceUtil.buildSortString;
import static com.lexmark.service.impl.real.util.AmindServiceUtil.getParentChainFromCHLNodeId;
import static com.lexmark.util.LangUtil.isEmpty;
import static com.lexmark.util.LangUtil.isNotEmpty;
import static com.lexmark.util.LangUtil.notNull;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.amind.common.service.SiebelBusinessServiceProxy;
import com.amind.data.service.ActionEvent;
import com.amind.data.service.ExecutionMode;
import com.amind.data.service.QueryObject;
import com.amind.session.Session;
import com.lexmark.contract.RequestListContract;
import com.lexmark.domain.ServiceRequest;
import com.lexmark.service.api.SiebelCrmServiceException;
import com.lexmark.service.impl.real.domain.CHLDo;
import com.lexmark.service.impl.real.domain.RequestTypeDo;
import com.lexmark.service.impl.real.util.AmindServiceUtil;
import com.lexmark.util.LangUtil;

public class RequestTypeListServiceB2B {

	private final String contactId;
	private final String chlNodeId;
	private final String vendorAccountId;
	private final int startRecordNumber;
	private final int increment;
	private final String soldTo;
	private final Map<String, Object> filterCriteria;
	private final Map<String, Object> sortCriteria;
	private final Map<String, String> fieldMap;

	private String searchExpression;
	private QueryObject criteria;
	private Session session;
	private Session chldSession;  // for chldNodeID
	private Session totalCountSession;
    private RequestListContract contract;
    private boolean isChangeRequests;

	public RequestTypeListServiceB2B(RequestListContract contract, Map<String, String> fieldMap) {
		if (contract == null) {
			throw new IllegalStateException("contract can not be null");
		}
		contactId = contract.getContactId();
		chlNodeId = contract.getChlNodeId();
		vendorAccountId = contract.getVendorAccountId();
		soldTo = contract.getSoldTo();
		startRecordNumber = contract.getStartRecordNumber();
		increment = contract.getIncrement();
		sortCriteria = contract.getSortCriteria();
		filterCriteria = contract.getFilterCriteria();
		this.contract = contract;

		this.fieldMap = fieldMap;
		
		this.isChangeRequests = contract.isChangeRequestFlag();
	}

	public void buildSearchExpression() {
		searchExpression = buildRequestTypeSearchExpression();
		criteria = buildRequestTypeCriteria();
	}
	
	@SuppressWarnings("unchecked")
	public List<ServiceRequest> queryAndGetResultList() {
		List<RequestTypeDo> requestDoList = notNull(getSession().getDataManager().query(criteria));
		return convertRequestTypeList(requestDoList);
	}

	public int processTotalCount() {
		int totalCount = 0;
		SiebelBusinessServiceProxy proxy = getTotalCountSession().getSiebelBusServiceProxy();
		totalCount = AmindServiceUtil.getTotalCount("LXK C Service Request (EAI)", "LXK C Service Request (EAI)",
				searchExpression, proxy);
		return totalCount;
	}

	private QueryObject buildRequestTypeCriteria() {
		QueryObject criteria = new QueryObject(RequestTypeDo.class, ActionEvent.QUERY);
		criteria.setExecutionMode(ExecutionMode.BiDirectional);
		criteria.setNumRows(increment);
		criteria.setStartRowIndex(startRecordNumber);
		criteria.addComponentSearchSpec(RequestTypeDo.class, searchExpression);

		if (isNotEmpty(sortCriteria)) {
			if(sortCriteria.containsKey("serviceRequestNumber")) {
				Map<String, String> fieldMapaReplacement = new HashMap<String, String>();
				fieldMapaReplacement.putAll(fieldMap);
				fieldMapaReplacement.put("serviceRequestNumber", "Created");
				criteria.setSortString(buildSortString(sortCriteria, Collections.unmodifiableMap(fieldMapaReplacement)));
			}
			else {
				criteria.setSortString(buildSortString(sortCriteria, fieldMap));
			}
		}

		return criteria;
	}

	private boolean isBreakfixRequest()
	{
		List<String> requestType = null;
		if (isNotEmpty(filterCriteria) && filterCriteria.containsKey("requestType")) {
			requestType = (List<String>) filterCriteria.get("requestType");
			if ( isNotEmpty(requestType) && requestType.size() == 1 && requestType.get(0).equalsIgnoreCase("breakfix")) {
				return true;
			}
			
		}
		return false;
	}
	
	private boolean isAllRequestWithBreakfix()
	{
		List<String> requestType = null;
		if (isNotEmpty(filterCriteria) && filterCriteria.containsKey("requestType")) {
			requestType = (List<String>) filterCriteria.get("requestType");
			if (isNotEmpty(requestType) && requestType.size() > 1) {
				return true;
			}
		}
		return false;
	}
	String buildRequestTypeSearchExpression() {
		boolean hardwareRequests = contract.isHardwareRequestFlag();
		boolean breakfix= isBreakfixRequest();
		boolean allRequestWithBreakfix =isAllRequestWithBreakfix();
		StringBuilder builder = new StringBuilder();
		
 	    if(!contract.isVendorFlag() && !hardwareRequests && !contract.isChangeRequestFlag()){
	    	builder.append("([LXK MPS SR Type] <> 'Claims')");  
	    }
		if (isNotEmpty(chlNodeId)) {
			CHLDo chldo = getParentChainFromCHLNodeId(chlNodeId,
					getChldSession().getDataManager()); // chldSession used for
														// chNodeId
			if (chldo == null) {
				throw new SiebelCrmServiceException("Chl Domian Object is null");
			}
			String topLevelAccountId = chldo.getTopLevelAccountId();
			String parentChain = chldo.getParentChain();

			if (isNotEmpty(topLevelAccountId)) {
				if (isNotEmpty(builder)) {
					builder.append(" AND ");
				}
				builder.append("[");
				builder.append("Account Id");
				builder.append("] = '");
				builder.append(topLevelAccountId);
				builder.append("'");
			}

			if (isNotEmpty(parentChain) && isNotEmpty(topLevelAccountId)) {
				builder.append(" AND ");
			}

			if (isNotEmpty(parentChain)) {
				builder.append("[");
				builder.append(fieldMap.get("chlNodeName"));
				builder.append("] LIKE '");
				builder.append(parentChain);
				builder.append("*'");
			}

		} else {
			if (isNotEmpty(contactId)) {
	            builder.append(contactIdSearchExpr(contactId) +  " AND ");
			} 
		}
		
		 String assetType = contract.getAssetType();
		 String siebelAssetTypeField = "LXK MPS Agreement Type";
		 String siebelAssetTypeValue = null;
		 if ("MPS".equalsIgnoreCase(assetType)) {
		   siebelAssetTypeValue = "MPS Agreement";
		 } else if ("CSS".equalsIgnoreCase(assetType)) {
		     siebelAssetTypeValue = "CSS Agreement";
		 }

		if (isNotEmpty(siebelAssetTypeValue)) {
		    builder.append(String.format(" AND [%s] = '%s'", siebelAssetTypeField, siebelAssetTypeValue));
	    }

		if (isNotEmpty(filterCriteria)) {
			if (filterCriteria.containsKey("requestType")) {
				String fieldName = fieldMap.get("requestType");
				List<String> values = (List<String>) filterCriteria.get("requestType");

				if(LangUtil.isNotEmpty(values) && values != null)
				{
					String requestTypeFilterExpr = siebelFilterExpr(fieldName, values);
					if (LangUtil.isNotEmpty(requestTypeFilterExpr)) {
	     				builder.append(" AND ").append(requestTypeFilterExpr);
					}
				}

				filterCriteria.remove("requestType");
			}
			if (filterCriteria.containsKey("serviceRequestNumber")) {
				String fieldName = fieldMap.get("serviceRequestNumber");
				String value = (String) filterCriteria.get("serviceRequestNumber");

				if(LangUtil.isNotEmpty(value))
				{
					String requestTypeFilterExpr = siebelFilterExpr_SRN(fieldName, value);
					if (LangUtil.isNotEmpty(requestTypeFilterExpr)) {
	     				builder.append(" AND ").append(requestTypeFilterExpr);
					}
				}

				filterCriteria.remove("serviceRequestNumber");
			}
			
			if(filterCriteria.containsKey("assetId")) {
				builder.append("AND ([" + fieldMap.get("assetId") + "] = '"
						+ filterCriteria.get("assetId") + "')");
				filterCriteria.remove("assetId");
			}
			if(filterCriteria.containsKey("asset.serialNumber")) {
				
					builder.append("AND ([" + "Serial Number" + "]" );
					builder.append("~LIKE '*"
							+ filterCriteria.get("asset.serialNumber") + "*' OR");
					
					builder.append(" [" + "LXK MPS SR Serial Number"+"]");
							builder.append("~LIKE'*"
							+ filterCriteria.get("asset.serialNumber") + "*')");
					filterCriteria.remove("asset.serialNumber");
			}
		
			//Checking for instanceof List in order to not force to change type from string to list in other places where method is being called
			if(filterCriteria.containsKey("area") && (filterCriteria.get("area") instanceof List<?>)) {
				String fieldName = fieldMap.get("area");
				List<String> allValues = (List<String>)filterCriteria.get("area");
				
				builder.append(AmindServiceUtil.buildMultipleFilterCriteria(fieldName,allValues,true,true));
				
				filterCriteria.remove("area");
			}
			
			if (!isChangeRequests && !contract.isHardwareRequestFlag() && !allRequestWithBreakfix) {
				builder.append(" AND ([LXK MPS SR Area] ~<> 'Adhoc' OR [LXK MPS SR Area] IS NULL)");
			}

			if (filterCriteria.containsKey("coveredService")) {
				String coveredService = (String) filterCriteria.get("coveredService");
				builder.append(buildCoveredServiceSearchSpec(coveredService));
				filterCriteria.remove("coveredService");
			}
			
			if (filterCriteria.containsKey("serviceRequestStatus")) {
				String serviceRequestStatus = (String) filterCriteria.get("serviceRequestStatus");
				builder.append(buildServiceRequestStatusSearchSpec(serviceRequestStatus));
				filterCriteria.remove("serviceRequestStatus");
			}
			
			builder.append(buildCriteria(filterCriteria, fieldMap, true, true));
		}
		if(!contract.isVendorFlag() && !allRequestWithBreakfix && !hardwareRequests && !contract.isChangeRequestFlag()){
			 builder.append(" AND (([LXK MPS Source] <> 'LDCM') " +
			 		"OR ([LXK MPS Source] ='LDCM'" +
			 		" AND ([LXK MPS Portal Order Id] IS NOT NULL)))");
		}
		
		if(hardwareRequests) {
			builder.append(" AND ([LXK MPS SR Area] <>'Adhoc' ) AND ([LXK MPS SR Sub Area] <> 'Project Based' ) " +
					"AND ([LXK MPS SR Area] = 'HW Order' OR [LXK MPS SR Area] = 'Hardware-Ship and Install')");
		}
		
		if(contract.isChangeRequestFlag()) {
			builder.append(" AND ([LXK MPS SR Area] <>'Adhoc' OR [LXK MPS SR Area] IS NULL OR [LXK MPS SR Sub Area] IS NULL) AND ([LXK MPS SR Area] <>'HW Order' AND [LXK MPS SR Area] <>'Hardware-Ship and Install') AND ([LXK MPS SR Type] = 'Fleet Management') AND ([LXK MPS SR Sub Area] <> 'Project Based' OR [LXK MPS SR Area] IS NULL OR [LXK MPS SR Sub Area] IS NULL)");
		}
		

		if (breakfix) {
			builder.append("AND (([LXK MPS SR Area] <> 'Install' AND [LXK MPS SR Area] <> 'MADC' AND [LXK MPS SR Area] <> 'Data Management'");
			builder.append(" AND [LXK MPS SR Area] <> 'HW Order' AND [LXK MPS SR Area] <> 'Hardware-Ship and Install') OR [LXK MPS SR Area] IS NULL)");
		} else if (allRequestWithBreakfix) {
			
			builder.append(" AND (([LXK MPS SR Sub Area] ~<> 'Project Based') OR [LXK MPS SR Area] IS NULL OR [LXK MPS SR Sub Area] IS NULL) AND "
					+ "(([LXK MPS SR Area] ~<> 'Adhoc' OR [LXK MPS SR Area] IS NULL OR [LXK MPS SR Sub Area] IS NULL))  AND (([LXK MPS Source] <> 'LDCM') OR ([LXK MPS Source] ='LDCM' AND ([LXK MPS Portal Order Id] IS NOT NULL)))");
		} else {
			if (!contract.isChangeRequestsPermission()) {
				builder.append(addChangeRequestsFilter());
			}

			if (!contract.isHardwareRequestsPermission()) {
				builder.append(addHardwareRequestsFilter());
			}
		}

		builder.append(" AND [LXK MPS Source] = 'B2B'");
		builder.append(" AND ([Created] >= '07/22/2014 04:00:00')");
		
		if (isNotEmpty(contract.getSoldTo())) {
			builder.append(" AND ");
			builder.append("[LXK MPS Sold To]='");
			builder.append(contract.getSoldTo());
			builder.append("'");
		}
		
		return builder.toString();
	}

	private Object buildCoveredServiceSearchSpec(String coveredService) {
    	StringBuilder searchSpec = new StringBuilder();
		searchSpec.append(" AND ([");
		searchSpec.append("Covered Service Type");
		searchSpec.append("] ~LIKE '*");
		searchSpec.append(coveredService);
		searchSpec.append("*' OR ([");
		searchSpec.append("Service Override Type");
		searchSpec.append("] IS NULL AND [Service Override Type] ~LIKE '*");
		searchSpec.append(coveredService);
		searchSpec.append("*'))");
		return searchSpec;
	}
    
    private Object buildServiceRequestStatusSearchSpec(String serviceRequstStatus) {
    	StringBuilder searchSpec = new StringBuilder();
		searchSpec.append(" AND ([");
		searchSpec.append("LXK MPS Web Status");
		searchSpec.append("] ='");
		searchSpec.append(serviceRequstStatus);
		searchSpec.append("')");
		return searchSpec;
	}

	public static String siebelFilterExpr(String fieldName, List<String> values) {
		boolean firstFlag = true;
		StringBuilder requestTypeSearchSpec = new StringBuilder();
		requestTypeSearchSpec.append("(");
		for (String requestValue : values) {
			if (firstFlag) {
				if ("BreakFix".equals(requestValue)) {
					requestTypeSearchSpec.append(breakFixSearchSpec(fieldName));
				} else {
					requestTypeSearchSpec.append("[" + fieldName + "] = '"
							+ requestValue + "'");			
				}
				firstFlag = false;
			} else {
				requestTypeSearchSpec.append(" OR ");
				if ("BreakFix".equals(requestValue)) {
					requestTypeSearchSpec.append(breakFixSearchSpec(fieldName));
				}else{
					requestTypeSearchSpec.append("[" + fieldName + "] = '"
							+ requestValue + "'");
					
				}

			}
		}
		requestTypeSearchSpec.append(")");

		return requestTypeSearchSpec.toString();
	}

    public static String siebelFilterExpr_SRN(String fieldName, String value) {
		StringBuilder requestTypeSearchSpec = new StringBuilder();
		requestTypeSearchSpec.append("(");
		requestTypeSearchSpec.append("[");
		requestTypeSearchSpec.append(fieldName);
		requestTypeSearchSpec.append("]  ~LIKE '*");
		requestTypeSearchSpec.append(value);
		requestTypeSearchSpec.append("*'");		
		requestTypeSearchSpec.append(")");
		return requestTypeSearchSpec.toString();
	}

    static String breakFixSearchSpec (String fieldName) {
		StringBuilder breakFixSearchSpec = new StringBuilder();

		breakFixSearchSpec.append("([");
		breakFixSearchSpec.append(fieldName);
		breakFixSearchSpec.append("]='Diagnosis' OR [");
		breakFixSearchSpec.append(fieldName);
		breakFixSearchSpec.append("]='Service' OR [");
		breakFixSearchSpec.append(fieldName);
		breakFixSearchSpec.append("]='Dispatch' OR [");
		breakFixSearchSpec.append(fieldName);
		breakFixSearchSpec.append("]='Presales' OR [");
		breakFixSearchSpec.append(fieldName);
		breakFixSearchSpec.append("]='Complaint - Formal' OR [");
		breakFixSearchSpec.append(fieldName);
		breakFixSearchSpec.append("]='Complaint - Informal' OR [");
		breakFixSearchSpec.append(fieldName);
		breakFixSearchSpec.append("]='Customer Does Not Wish to Pay' OR [");
		breakFixSearchSpec.append(fieldName);
		breakFixSearchSpec.append("]='Dealer Location Queries' OR [");
		breakFixSearchSpec.append(fieldName);
		breakFixSearchSpec.append("]='Email' OR [");
		breakFixSearchSpec.append(fieldName);
		breakFixSearchSpec.append("]='Entitlement Check' OR [");
		breakFixSearchSpec.append(fieldName);
		breakFixSearchSpec.append("]='Install/De-Install' OR [");
		breakFixSearchSpec.append(fieldName);
		breakFixSearchSpec.append("]='LXK Internet Site Query' OR [");
		breakFixSearchSpec.append(fieldName);
		breakFixSearchSpec.append("]='Lack of Customer Info' OR [");
		breakFixSearchSpec.append(fieldName);
		breakFixSearchSpec.append("]='Non-supported Product' OR [");
		breakFixSearchSpec.append(fieldName);
		breakFixSearchSpec.append("]='OEM Import' OR [");
		breakFixSearchSpec.append(fieldName);
		breakFixSearchSpec.append("]='Other LXK Dept (Sales, Office)' OR [");
		breakFixSearchSpec.append(fieldName);
		breakFixSearchSpec.append("]='Other TSC Dept (CPD or PSSD)' OR [");
		breakFixSearchSpec.append(fieldName);
		breakFixSearchSpec.append("]='Schedule Callback' OR [");
		breakFixSearchSpec.append(fieldName);
		breakFixSearchSpec.append("]='Web Account' OR [");
		breakFixSearchSpec.append(fieldName);
		breakFixSearchSpec.append("]='CFL Store' OR [");
		breakFixSearchSpec.append(fieldName);
		breakFixSearchSpec.append("]='Others')");
		return breakFixSearchSpec.toString();
    }


    static String contactIdSearchExpr(String contactId) {
        String tmpl = "([contactId] =':contactId'" +
                " OR (([primaryContactId] = ':contactId'" +
                "     AND [contactId] <> ':contactId'))" +
                " OR (([alternateContactId] = ':contactId'" +
                "     AND [contactId] <>':contactId'" +
                "     AND [primaryContactId] <> ':contactId')))";

        // we are using actual Siebel fields to be able to use this searchSpec for pageCount as well
        return tmpl
                  .replace("[contactId]",          "[LXK R SR Contact Id]")
                  .replace("[primaryContactId]",   "[Contact Id]")
                  .replace("[alternateContactId]", "[LXK MPS Alternate Contact Id]")
                  .replace(":contactId",            contactId)
                  ;
    }

  

	public Session getSession() {
		if (session == null) {
			throw new IllegalStateException("session has not set!");
		} else {
			return session;
		}
	}

	public void setSession(Session session) {
		if (session == null) {
			throw new IllegalArgumentException("session can not be null!");
		} else {
			this.session = session;
		}
	}
	
	public Session getChldSession() {
		if (chldSession == null) {
			throw new IllegalStateException("chldSession has not set!");
		} else {
			return chldSession;
		}
	}

	public void setChldSession(Session chldSession) {
		this.chldSession = chldSession;
	}



	public Session getTotalCountSession() {
		if (totalCountSession == null) {
			throw new IllegalStateException("total count session has not been set!");
		} else {
			return totalCountSession;
		}
	}

	public void setTotalCountSession(Session totalCountSession) {
		if (totalCountSession == null) {
			throw new IllegalStateException("total count session can not be null!");
		} else {
			this.totalCountSession = totalCountSession;
		}
	}
	

	private String addChangeRequestsFilter() {
		String changeRequestsFilter; 
		return changeRequestsFilter = " AND (([LXK MPS SR Area] <> 'Install' AND [LXK MPS SR Area] <> 'MADC' AND [LXK MPS SR Area] <> 'Data Management'))";
		//return changeRequestsFilter;		
	}
	
	
	private String addHardwareRequestsFilter() {
		String hardwareRequestsFiler; 
		return hardwareRequestsFiler = " AND (([LXK MPS SR Area] = 'HW Order' OR [LXK MPS SR Area] = 'Hardware-Ship and Install'))";
	}
}