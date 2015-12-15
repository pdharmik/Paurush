package com.lexmark.service.impl.real.requestService;

import static com.lexmark.service.impl.real.requestService.AmindRequestTypeConversionUtil.convertRequestTypeList;
import static com.lexmark.service.impl.real.util.AmindServiceUtil.buildCriteria;
import static com.lexmark.service.impl.real.util.AmindServiceUtil.buildMdmSearchExpressionForMdmLevel;
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
import com.lexmark.contract.AssetListContract;
import com.lexmark.contract.RequestListContract;
import com.lexmark.domain.ServiceRequest;
import com.lexmark.service.api.SiebelCrmServiceException;
import com.lexmark.service.impl.real.domain.CHLDo;
import com.lexmark.service.impl.real.domain.RequestTypeDo;
import com.lexmark.service.impl.real.util.AmindServiceUtil;
import com.lexmark.util.LangUtil;

public class RequestTypeListService {

	private final String mdmId;
	private final String mdmLevel;
	private final String contactId;
	private final String chlNodeId;
	private final String vendorAccountId;
	private final int startRecordNumber;
	private final int increment;
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

	public RequestTypeListService(RequestListContract contract, Map<String, String> fieldMap) {
		if (contract == null) {
			throw new IllegalStateException("contract can not be null");
		}
		contactId = contract.getContactId();
		chlNodeId = contract.getChlNodeId();
		vendorAccountId = contract.getVendorAccountId();
		mdmId = contract.getMdmId();
		mdmLevel = contract.getMdmLevel();
		startRecordNumber = contract.getStartRecordNumber();
		increment = contract.getIncrement();
		sortCriteria = contract.getSortCriteria();
		filterCriteria = contract.getFilterCriteria();
		this.contract = contract;

		this.fieldMap = fieldMap;
		
		this.isChangeRequests = contract.isChangeRequestFlag();
	}

	public void checkRequiredFields() {
		if (isEmpty(contactId) && (isEmpty(chlNodeId))) {
			if (isEmpty(mdmId)) {
				throw new IllegalArgumentException("mdmId is null or empty!");
			} else if (isEmpty(mdmLevel)) {
				throw new IllegalArgumentException("mdmLevel is null or empty!");
			}
		}
	}

	public void buildSearchExpression() {
		searchExpression = buildRequestTypeSearchExpression();
		criteria = buildRequestTypeCriteria();
	}
	
	@SuppressWarnings("unchecked")
	public List<ServiceRequest> queryAndGetResultList() {
	/*	class NewThread implements Runnable {
			   Thread t;
			   NewThread() {
			      // Create a new, second thread
			      t = new Thread(this, "Demo Thread");to
			      
			      t.start(); // Start the thread
			   }
			   
			   // This is the entry point for the second thread.
			   public void run() {
				   queryAndGetResultList();
				   queryAndGetResultList();
			     
			   }
			}*/
	
		List<RequestTypeDo> requestDoList = notNull(getSession().getDataManager().query(criteria));
		/* new NewThread();
		 new NewThread();*/
		return convertRequestTypeList(requestDoList);
	}

	public int processTotalCount() {
		int totalCount = 0;
		SiebelBusinessServiceProxy proxy = getTotalCountSession().getSiebelBusServiceProxy();
		totalCount = AmindServiceUtil.getBCTotalCount("LXK C Service Request (EAI)", "LXK C Service Request (EAI)",
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
		
//		boolean isAllRequests = isAllRequests();
		
//		boolean hardwareRequests = isHardwareRequests();
		boolean hardwareRequests = contract.isHardwareRequestFlag();
		boolean breakfix= isBreakfixRequest();
		boolean allRequestWithBreakfix =isAllRequestWithBreakfix();
		boolean viewAllCustomerOrder = contract.isViewAllCustomerOrder();
		
		StringBuilder builder = new StringBuilder();
		//builder.append("([LXK MPS SR Status] <>'Archived') AND ");  
 	    if(!contract.isVendorFlag() && !hardwareRequests && !contract.isChangeRequestFlag()){
	    	builder.append("([LXK MPS SR Type] <> 'Claims') AND ");  
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

			//Added for BRD 09, 
			if(viewAllCustomerOrder && contract.getAccountId()!= null){
				builder.append("([LXK MPS SR Type] <> 'Claims') AND ");  
				builder.append("([Account Id] ='"+ contract.getAccountId() +"') ");
				builder.append("AND ([LXK MPS Source] <> 'LDCM' OR ([LXK MPS Source] ='LDCM' AND [LXK MPS Portal Order Id] IS NOT NULL))");
				
			}
			
			if ((contract.getAccountId() == null && !viewAllCustomerOrder) || (!viewAllCustomerOrder && contract.getAccountId()!= null)) {
				//Vendor
				if (contract.isVendorFlag()) {
					builder.append("(");
					builder.append(AmindServiceUtil
							.buildVendorMdmSearchExpression(mdmId, mdmLevel,
									fieldMap));
					builder.append(")");
				}
				// Customer
				else if (!contract.isVendorFlag()) {
					builder.append("((");
					builder.append(buildMdmSearchExpressionForMdmLevel(mdmId,
							mdmLevel, fieldMap, false, null));
					//add the condition for alliance partner
					builder.append(")");
					if (contract.isAlliancePartner()) {
						builder.append("OR (");
						builder.append(AmindServiceUtil
								.buildAgreementMdmSearchExpression(mdmId,
										mdmLevel, fieldMap));
						builder.append(")");
					}
					builder.append(")");
				}
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
			if (filterCriteria.containsKey("serviceRequest.startDate")) {
				String activityStartDate = (String) filterCriteria.get("serviceRequest.startDate");
				if (isNotEmpty(activityStartDate)) {
					builder.append(" AND ([Created] >= '").append(activityStartDate).append("')");
				}
			}
			if (filterCriteria.containsKey("serviceRequest.endDate")) {
				String activityEndDate = (String) filterCriteria.get("serviceRequest.endDate");
				if (isNotEmpty(activityEndDate)) {
					builder.append(" AND ([Created] <= '").append(activityEndDate).append("')");
				}
			}
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
			if(filterCriteria.containsKey("webStatus")) {
				String fieldName = fieldMap.get("webStatus");
				List<String> values = (List<String>) filterCriteria.get("webStatus");
				if(LangUtil.isNotEmpty(values) && values != null)
				{
					String webStatusSearchSpec = siebelFilterExpr(fieldName, values);
					if (LangUtil.isNotEmpty(webStatusSearchSpec)) {
	     				builder.append(" AND ").append(webStatusSearchSpec);
					}
				}

				filterCriteria.remove("webStatus");

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
			//searchSpec.append("] ~LIKE '*");
//			if (!contract.isChangeRequestFlag() || !contract.isHardwareRequestFlag()) {
//				filterCriteria.remove("area");
//			}
			
//			if (isChangeRequests && !allRequestWithBreakfix) {
//				builder.append(" AND ([");
//				builder.append(fieldMap.get("area"));
//				builder.append("] ~<> 'HW Order')");
//				builder.append(" AND ([");
//				builder.append(fieldMap.get("area"));
//				builder.append("] ~<> 'Hardware-Ship and Install')");
//			
////				filterCriteria.remove("area");
//			}
			
			//Checking for instanceof List in order to not force to change type from string to list in other places where method is being called
			if(filterCriteria.containsKey("area") && (filterCriteria.get("area") instanceof List<?>)) {
				String fieldName = fieldMap.get("area");
				List<String> allValues = (List<String>)filterCriteria.get("area");
				
				builder.append(AmindServiceUtil.buildMultipleFilterCriteria(fieldName,allValues,true,true));
				
				filterCriteria.remove("area");
			}
			
//			if (filterCriteria.containsKey("requestStatus")) {
//				String fieldName = fieldMap.get("requestStatus");
//				List<String> allValues = (List<String>)filterCriteria.get("requestStatus");
//				
//				builder.append(AmindServiceUtil.buildMultipleFilterCriteria(fieldName,allValues,true,true));
//				
//				filterCriteria.remove("requestStatus");
//			}
			
			if (!isChangeRequests && !contract.isHardwareRequestFlag() && !allRequestWithBreakfix) {
				builder.append(" AND ([LXK MPS SR Area] ~<> 'Adhoc' OR [LXK MPS SR Area] IS NULL)");
			}
			//if(isAllRequests || isChangeRequests) {
			if (!breakfix && isEmpty(chlNodeId)) {
				if (!hardwareRequests) {
//				builder.append(" AND (([LXK MPS SR Area] ~<> 'Install' AND [LXK MPS SR Sub Area] ~<> 'Project Based') OR ([LXK MPS SR Area] ~= 'Install' AND [LXK MPS SR Sub Area] ~= 'BAU') OR [LXK MPS SR Area] IS NULL)");
				} else {
//					builder.append(" AND (([LXK MPS SR Area] ~<> 'Install' AND [LXK MPS SR Sub Area] ~<> 'Project Based') OR [LXK MPS SR Area] IS NULL)");
				}
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
			
			//OPS filter
			if (filterCriteria.containsKey("requestStatus")) {
				String requestStatus = (String) filterCriteria.get("requestStatus");
				builder.append(buildRequestStatusSearchSpec(requestStatus));
				filterCriteria.remove("requestStatus");
			}
			if (filterCriteria.containsKey("subStatus")) {
				String subStatus = (String) filterCriteria.get("subStatus");
				builder.append(buildSubStatusSearchSpec(subStatus));
				filterCriteria.remove("subStatus");
			}
			if (filterCriteria.containsKey("severity")) {
				String severity = (String) filterCriteria.get("severity");
				builder.append(buildSeveritySearchSpec(severity));
				filterCriteria.remove("severity");
			}
			if (filterCriteria.containsKey("projectName")) {
				String projectName = (String) filterCriteria.get("projectName");
				builder.append(buildProjectNameSearchSpec(projectName));
				filterCriteria.remove("projectName");
			}
			if (filterCriteria.containsKey("projectPhase")) {
				String projectPhase = (String) filterCriteria.get("projectPhase");
				builder.append(buildProjectPhaseSearchSpec(projectPhase));
				filterCriteria.remove("projectPhase");
			}
			if (filterCriteria.containsKey("agreementNumber")) {
				String agreementNumber = (String) filterCriteria.get("agreementNumber");
				builder.append(buildAgreementNumberSearchSpec(agreementNumber));
				filterCriteria.remove("agreementNumber");
			}
			if (filterCriteria.containsKey("agreementName")) {
				String agreementName = (String) filterCriteria.get("agreementName");
				builder.append(buildAgreementNameSearchSpec(agreementName));
				filterCriteria.remove("agreementName");
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
//			builder.append(" AND ([LXK MPS SR Area] <>'Adhoc' )  AND ([LXK MPS SR Sub Area] <> 'Project Based') AND ([LXK MPS SR Area] = 'MADC'" +
//					" OR [LXK MPS SR Area] = 'Install' OR [LXK MPS SR Area] = 'Update Request' OR [LXK MPS SR Area] = 'Cancel Request' OR [LXK MPS SR Area] = 'Data Management')");
			builder.append(" AND ([LXK MPS SR Area] <>'Adhoc' OR [LXK MPS SR Area] IS NULL OR [LXK MPS SR Sub Area] IS NULL) AND ([LXK MPS SR Area] <>'HW Order' AND [LXK MPS SR Area] <>'Hardware-Ship and Install') AND ([LXK MPS SR Type] = 'Fleet Management') AND ([LXK MPS SR Sub Area] <> 'Project Based' OR [LXK MPS SR Area] IS NULL OR [LXK MPS SR Sub Area] IS NULL)");
			
		}
		
//		if (isEmpty(chlNodeId)) {
		if (breakfix) {
			builder.append("AND (([LXK MPS SR Area] <> 'Install' AND [LXK MPS SR Area] <> 'MADC' AND [LXK MPS SR Area] <> 'Data Management'");
			builder.append(" AND [LXK MPS SR Area] <> 'HW Order' AND [LXK MPS SR Area] <> 'Hardware-Ship and Install') OR [LXK MPS SR Area] IS NULL)");
		} else if (allRequestWithBreakfix) {
			if(contract.isOpsPage())
			{
				builder.append(" AND "
						+ "(([LXK MPS SR Area] ~<> 'Adhoc' OR [LXK MPS SR Area] IS NULL OR [LXK MPS SR Sub Area] IS NULL))  AND (([LXK MPS Source] <> 'LDCM') OR ([LXK MPS Source] ='LDCM' AND ([LXK MPS Portal Order Id] IS NOT NULL)))");
		
			}
			else{
				builder.append(" AND (([LXK MPS SR Sub Area] ~<> 'Project Based') OR [LXK MPS SR Area] IS NULL OR [LXK MPS SR Sub Area] IS NULL) AND "
						+ "(([LXK MPS SR Area] ~<> 'Adhoc' OR [LXK MPS SR Area] IS NULL OR [LXK MPS SR Sub Area] IS NULL))  AND (([LXK MPS Source] <> 'LDCM') OR ([LXK MPS Source] ='LDCM' AND ([LXK MPS Portal Order Id] IS NOT NULL)))");
			}
			// builder.append("AND ((([LXK MPS SR Area] ~<> 'Adhoc' ) AND ([LXK MPS SR Area] ~<> 'Install' AND [LXK MPS SR Sub Area] ~<> 'Project Based') AND ([LXK MPS SR Area] <> 'HW Order' AND [LXK MPS SR Area] <> 'Hardware-Ship and Install')) OR ([LXK MPS SR Area] IS NULL OR [LXK MPS SR Sub Area] IS NULL) ) AND (([LXK MPS Source] <> 'LDCM') OR ([LXK MPS Source] ='LDCM' AND ([LXK MPS Portal Order Id] IS NOT NULL)))");
			} else {
			if (!contract.isChangeRequestsPermission()) {
				builder.append(addChangeRequestsFilter());
			}

			if (!contract.isHardwareRequestsPermission()) {
				builder.append(addHardwareRequestsFilter());
			}
		}
//		}
//		else
//		{
//			builder.append(" AND (([LXK MPS SR Area] <> 'HW Order' AND [LXK MPS SR Area] <> 'Hardware-Ship and Install'AND [LXK MPS SR Area] ~<> 'Adhoc') OR [LXK MPS SR Area] IS NULL)");
//		}
		
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
    
    private Object buildRequestStatusSearchSpec(String requestStatus) {
    	StringBuilder searchSpec = new StringBuilder();
    	searchSpec.append(" AND ([");
    	searchSpec.append("LXK MPS SR Status");
    	searchSpec.append("] ='");
    	searchSpec.append(requestStatus);
    	searchSpec.append("')");
    	return searchSpec;
    }
    
    private Object buildSubStatusSearchSpec(String subStatus) {
    	StringBuilder searchSpec = new StringBuilder();
    	searchSpec.append(" AND ([");
    	searchSpec.append("LXK MPS Sub-Status");
    	searchSpec.append("] ='");
    	searchSpec.append(subStatus);
    	searchSpec.append("')");
    	return searchSpec;
    }
    
    private Object buildSeveritySearchSpec(String severity) {
    	StringBuilder searchSpec = new StringBuilder();
    	searchSpec.append(" AND ([");
    	searchSpec.append("LXK MPS Severity");
    	searchSpec.append("] ='");
    	searchSpec.append(severity);
    	searchSpec.append("')");
    	return searchSpec;
    }
    
    private Object buildProjectNameSearchSpec(String projectName) {
    	StringBuilder searchSpec = new StringBuilder();
    	searchSpec.append(" AND ([");
    	searchSpec.append("LXK MPS Project");
    	searchSpec.append("] ='");
    	searchSpec.append(projectName);
    	searchSpec.append("')");
    	return searchSpec;
    }
    
    private Object buildProjectPhaseSearchSpec(String projectPhase) {
    	StringBuilder searchSpec = new StringBuilder();
    	searchSpec.append(" AND ([");
    	searchSpec.append("LXK MPS Project Phase");
    	searchSpec.append("] ='");
    	searchSpec.append(projectPhase);
    	searchSpec.append("')");
    	return searchSpec;
    }
    private Object buildAgreementNumberSearchSpec(String projectPhase) {
    	StringBuilder searchSpec = new StringBuilder();
    	searchSpec.append(" AND ([");
    	searchSpec.append("Agreement Number");
    	searchSpec.append("] ='");
    	searchSpec.append(projectPhase);
    	searchSpec.append("')");
    	return searchSpec;
    }
    private Object buildAgreementNameSearchSpec(String projectPhase) {
    	StringBuilder searchSpec = new StringBuilder();
    	searchSpec.append(" AND ([");
    	searchSpec.append("LXK MPS Agreement Name");
    	searchSpec.append("] ='");
    	searchSpec.append(projectPhase);
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
	public static String webStatusFilterExpr(String fieldName, List<String> values) {
		boolean firstFlag = true;
		StringBuilder webStatusSearchSpec = new StringBuilder();
		webStatusSearchSpec.append("(");
		for (String requestValue : values) {
			if (firstFlag) {
				
				webStatusSearchSpec.append("[" + fieldName + "] = '"
							+ requestValue + "'");			
				
				firstFlag = false;
			} else {
				webStatusSearchSpec.append(" OR ");
				webStatusSearchSpec.append("[" + fieldName + "] = '"
							+ requestValue + "'");
				}

			}
		webStatusSearchSpec.append(")");

		return webStatusSearchSpec.toString();
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
	

//    private boolean isAllRequests() {
//    	
//    	List<String> requestType = null;
//    	
//    	try {
//    	    requestType = (List<String>) filterCriteria.get("requestType"); 
//    	}
//    	catch(ClassCastException e) {
//    		throw new ClassCastException("'requestType' must be List in the filter criteria!");
//    	}
//    	
//    	if(LangUtil.isNotEmpty(requestType)) {
//    		//means it is all requests
//    		if(requestType.size() > 1) {
//    			return true;
//    		}
//    		//means it is Consumables
//    		else if (requestType.get(0).equalsIgnoreCase("Consumables Management")){
//    			return false;
//    		}
//    		//means it is Service Requests
//    		else if (requestType.get(0).equalsIgnoreCase("BreakFix")){
//    			return false;
//    		}
//    		//means it is Hardware
//    		else if (requestType.get(0).equalsIgnoreCase("Fleet Management") && !isChangeRequests){
//    			return false;
//    		}
//    	}
//    	
//		return true;
//	}
	
//	private boolean isHardwareRequests() {
//
//		List<String> requestType = null;
//		List<String> areas = null;
//		if (isNotEmpty(filterCriteria) &&  filterCriteria.containsKey("area")
//				&& filterCriteria.containsKey("requestType")) {
//			try {
//				requestType = (List<String>) filterCriteria.get("requestType");
//				areas = (List<String>) filterCriteria.get("area");
//			} catch (ClassCastException e) {
//				throw new ClassCastException(
//						"Both, 'requestType' and 'area' must be Lists in the filter criteria!");
//			}
//
//			if (isFleedManagementRequestType(requestType)
//					&& isHardwareArea(areas)) {
//				return true;
//			}
//		}
//		return false;
//	}

	
//	private boolean isFleedManagementRequestType(List<String> requestType) {
//		return (isNotEmpty(requestType) && requestType.size() == 1 && requestType.get(0).equalsIgnoreCase("Fleet Management"));
//	}

//	private boolean isHardwareArea(List<String> areas) {
//		
//		if(isEmpty(areas)) {
//			return false;
//		}
//		
////		if(isEmpty(areas) || areas.size() != 2) {
////			return false;
////		}
//		
//		for (String area : areas) {
//			if(!"HW Order".equalsIgnoreCase(area) && !"Hardware-Ship and Install".equalsIgnoreCase(area)) {
//				return false;
//			}
//		}
//		return true;
//	}
	
	

	private String addChangeRequestsFilter() {
		String changeRequestsFilter; 
		return changeRequestsFilter = " AND (([LXK MPS SR Area] <> 'Install' AND [LXK MPS SR Area] <> 'MADC' AND [LXK MPS SR Area] <> 'Data Management'))";
		//return changeRequestsFilter;		
	}
	
	
	private String addHardwareRequestsFilter() {
		String hardwareRequestsFiler; 
		return hardwareRequestsFiler = " AND (([LXK MPS SR Area] <> 'HW Order' AND [LXK MPS SR Area] <> 'Hardware-Ship and Install'))";
	}
}