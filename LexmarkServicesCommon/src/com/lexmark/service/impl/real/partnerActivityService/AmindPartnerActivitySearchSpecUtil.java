package com.lexmark.service.impl.real.partnerActivityService;

import static com.lexmark.service.impl.real.util.AmindServiceUtil.buildMdmSearchExpressionForMdmLevel;
import static com.lexmark.service.impl.real.util.AmindServiceUtil.buildMdmSearchExpressionForMdmLevelSR;
import static com.lexmark.util.LangUtil.isNotBlank;
import static com.lexmark.util.LangUtil.isNotEmpty;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.amind.data.service.IDataManager;
import com.lexmark.contract.ActivityListContract;
import com.lexmark.service.impl.real.domain.PartnerActivityEmployeeAccountDo;
import com.lexmark.service.impl.real.enums.ActivityStatus;
import com.lexmark.service.impl.real.enums.QueryType;
import com.lexmark.service.impl.real.enums.RequestType;
import com.lexmark.service.impl.real.util.AmindServiceUtil;
import com.lexmark.util.LangUtil;

public class AmindPartnerActivitySearchSpecUtil {

	public static String buildActivityListSearchSpec(ActivityListContract contract, IDataManager dataManager, 
			Map<String, String> activityFieldMap, boolean activityListCall)	throws Exception {
		StringBuilder searchSpec = new StringBuilder();
		searchSpec.append(buildBasicActivitySearchSpec(contract, dataManager, activityFieldMap));
		if (LangUtil.isEmpty(searchSpec)) {
			searchSpec.append("1 = 1"); // zero query part
		}
		String statusSearchSpec = buildStatusSearchSpec(contract.getStatus(), contract);
		if (LangUtil.isNotEmpty(statusSearchSpec)) {
			searchSpec.append(" AND ");
			searchSpec.append(statusSearchSpec);
		}
		if (contract.isMassUploadRequest()) {
			searchSpec.append(" AND ([LXK R Substatus] <>'Not Accepted' OR [LXK R Substatus] IS NULL)");
		}
		searchSpec.append(buildRequestTypeSearchSpec(contract.getServiceRequestType()));
		boolean containsActivitiesTypes = false;
		if (contract.getFilterCriteria() != null && !contract.getFilterCriteria().isEmpty()) {
			containsActivitiesTypes = filterCriteriaContainsActivitiesTypes(contract.getFilterCriteria());
			searchSpec.append(buildActivityListFilterCriteria(contract.getFilterCriteria(), activityFieldMap, activityListCall, contract.getCurrentTimeStamp()));
		}
		if (contract.isChangeManagementFlag() && !containsActivitiesTypes) {
			searchSpec.append(" AND ([Type] = 'HW Install' OR [Type] = 'HW Install Move' OR [Type] = 'HW Install Change' OR [Type] = 'HW Install Decommission' OR [Type] = 'HW BAU Install' OR [Type] = 'HW BAU Install Move' OR [Type] = 'HW BAU Install Change' OR [Type] = 'HW BAU Install Decommission' OR [Type] = 'HW MADC Install' OR [Type] = 'HW MADC Move' OR [Type] = 'HW MADC Change' OR [Type] = 'HW MADC Decommission')");
		}
		return searchSpec.toString();
	}

	public static String buildActivityListSearchSpecServiceRequest(ActivityListContract contract, IDataManager dataManager,
			Map<String, String> activityFieldMap, boolean activityListCall)	throws Exception {
		StringBuilder searchSpec = new StringBuilder();
		searchSpec.append(buildBasicActivitySearchSpecSR(contract, dataManager,	activityFieldMap));
		if (LangUtil.isEmpty(searchSpec)) {
			searchSpec.append("1 = 1"); // zero query part
		}
		String statusSearchSpec = buildStatusSearchSpecSR(contract.getStatus(),	contract);
		if (LangUtil.isNotEmpty(statusSearchSpec)) {
			searchSpec.append(" AND ");
			searchSpec.append(statusSearchSpec);
		}
		searchSpec.append(buildRequestTypeSRSearchSpec(contract.getServiceRequestType()));
		boolean containsActivitiesTypes = false;
		if (contract.getFilterCriteria() != null && !contract.getFilterCriteria().isEmpty()) {
			containsActivitiesTypes = filterCriteriaContainsActivitiesTypes(contract.getFilterCriteria());
			searchSpec.append(buildActivityListFilterCriteriaSR(contract.getFilterCriteria(), activityFieldMap,	activityListCall, contract.getCurrentTimeStamp()));
		}
		if (contract.isChangeManagementFlag() && !containsActivitiesTypes) {
			searchSpec.append(" AND EXISTS([Act Type] = 'HW Install' OR [Act Type] = 'HW Install Move' OR [Act Type] = 'HW Install Change' OR [Act Type] = 'HW Install Decommission' OR [Act Type] = 'HW BAU Install' OR [Act Type] = 'HW BAU Install Move' OR [Act Type] = 'HW BAU Install Change' OR [Act Type] = 'HW BAU Install Decommission' OR [Act Type] = 'HW MADC Install' OR [Act Type] = 'HW MADC Move' OR [Act Type] = 'HW MADC Change' OR [Act Type] = 'HW MADC Decommission')");
		}
		return searchSpec.toString();
	}

	private static boolean filterCriteriaContainsActivitiesTypes(
			Map<String, Object> filterCriteria) {
		return filterCriteria.containsKey("Activity.activityType");
	}

	public static void checkActivityListRequiredFields(ActivityListContract contract) {
		/* check for required fields */
		if (contract.getSessionHandle() != null	&& StringUtils.isEmpty(contract.getSessionHandle().toString())) {
			throw new IllegalArgumentException("Session Handle can not be empty or null");
		}
		if (StringUtils.isEmpty(contract.getStatus())) {
			throw new IllegalArgumentException("Status can not be empty or null");
		}

		if (StringUtils.isEmpty(contract.getQueryType())) {
			throw new IllegalArgumentException("Query Type can not be empty or null");
		}
		if (StringUtils.isEmpty(contract.getServiceRequestType())) {
			throw new IllegalArgumentException("Request Type can not be empty or null");
		}
		if (contract.isEmployeeFlag()) {
			if (StringUtils.isEmpty(contract.getEmployeeId())) {
				throw new IllegalArgumentException("Employee Flag is true so Employee Id can not be empty or null");
			}
		} else {
			if (StringUtils.isEmpty(contract.getMdmId())) {
				throw new IllegalArgumentException("Employee Flag is false so MDM Id can not be empty or null");
			}
			if (StringUtils.isEmpty(contract.getMdmLevel())) {
				throw new IllegalArgumentException("Employee Flag is false so MDM Level can not be empty or null");
			}
		}
	}

	public static String buildActivityListFilterCriteria(Map<String, Object> filterMap,	Map<String, String> activityFieldMap, 
			boolean activityListCall,	Date portalDate) {
		StringBuilder searchSpec = new StringBuilder();
		String serviceRequestValue = (String) filterMap.get("Activity.serviceRequest.serviceRequestType");
		if (serviceRequestValue != null) {
			searchSpec.append(buildRequestTypeSearchSpec(serviceRequestValue));
			filterMap.remove("Activity.serviceRequest.serviceRequestType");
		}
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		Date activityStartDate = (Date) filterMap.get("Activity.activityDate.startDate");
		if (activityStartDate != null) {
			searchSpec.append(buildActivityStartDate(dateFormat.format(activityStartDate)));
			filterMap.remove("Activity.activityDate.startDate");
		}
		Date activityEndDate = (Date) filterMap.get("Activity.activityDate.endDate");
		if (activityEndDate != null) {
			searchSpec.append(buildActivityEndDate(dateFormat.format(activityEndDate)));
			filterMap.remove("Activity.activityDate.endDate");
		}
		Date claimStatusDate = (Date) filterMap.get("Activity.claimStatusDate");
		if (claimStatusDate != null) {
			searchSpec.append(buildClaimStatusDate(dateFormat.format(claimStatusDate)));
			filterMap.remove("Activity.claimStatusDate");
		}

		Date expectedCompletionStartDate = (Date) filterMap.get("Activity.expectedCompletionDate.startDate");
		if (expectedCompletionStartDate != null) {
			searchSpec.append(buildExpectedCompletionStartDate(dateFormat.format(expectedCompletionStartDate)));
			filterMap.remove("Activity.expectedCompletionDate.startDate");
		}
		Date expectedCompletionEndDate = (Date) filterMap.get("Activity.expectedCompletionDate.endDate");
		if (expectedCompletionEndDate != null) {
			searchSpec.append(buildExpectedCompletionEndDate(dateFormat.format(expectedCompletionEndDate)));
			filterMap.remove("Activity.expectedCompletionDate.endDate");
		}
		String serialNumer = (String) filterMap.get("Activity.serviceRequest.asset.serialNumber");
		if (serialNumer != null) {
			searchSpec.append(buildSerialNumberSearchSpec(serialNumer));
			filterMap.remove("Activity.serviceRequest.asset.serialNumber");
		}
		String responseMetric = (String) filterMap.get("Activity.responseMetric");
		if (responseMetric != null) {
			searchSpec.append(buildResponseMatricSearchSpec(responseMetric));
			filterMap.remove("Activity.responseMetric");
		}
		if (filterMap.containsKey("Activity.customerAccount.accountName") && activityListCall) {
			String siebelFieldName = (String) activityFieldMap.get("Activity.customerAccount.accountName");
			String value = (String) filterMap.get("Activity.customerAccount.accountName");
			addAccountNameFilter(searchSpec, siebelFieldName, value);
			filterMap.remove("Activity.customerAccount.accountName");
		}
		if (filterMap.containsKey("Activity.activitySubStatus")) {
			String siebelFieldName = (String) activityFieldMap.get("Activity.activitySubStatus");
			String value = (String) filterMap.get("Activity.activitySubStatus");
			addactivitySubStatus(searchSpec, siebelFieldName, value);
			filterMap.remove("Activity.activitySubStatus");
		}
		if (filterMap.containsKey("Activity.activityType")) {
			String siebelFieldName = (String) activityFieldMap.get("Activity.activityType");
			List<String> values = (List<String>) filterMap.get("Activity.activityType");
			if (LangUtil.isNotEmpty(values) && values != null) {
				String activityTypeFilterExpr = addActivityTypeList(siebelFieldName, values);
				if (LangUtil.isNotEmpty(activityTypeFilterExpr)) {
					searchSpec.append(" AND ").append(activityTypeFilterExpr);
				}
			}
			filterMap.remove("Activity.activityType");
		}
		String childSR = (String) filterMap.get("Activity.createChildSR");
		if (childSR != null) {
			if (!"All".equalsIgnoreCase(childSR)) {
				long timeDifferance = (long) 6.48e+7;
				long timeStemp = portalDate.getTime() - timeDifferance;
				Date d = new Date(timeStemp);
				dateFormat.format(d);
				searchSpec.append(" AND [LXK R Debrief Status] IS NOT NULL ");
				searchSpec.append(" AND ").append("LXK R Last Status Update >= '" + dateFormat.format(d) + "'");
				searchSpec.append(" AND ([LXK SW Service Request Type] <> 'Fleet Management' AND [LXK SW Service Request Type] <> 'Consumables Management' AND [LXK SW Service Request Type] <> 'Claims')");
			}
			filterMap.remove("Activity.createChildSR");
		}

		if (filterMap.containsKey("Activity.activityStatus")) {
			String siebelFieldName = (String) activityFieldMap.get("Activity.activityStatus");
			String value = (String) filterMap.get("Activity.activityStatus");
			addActivityStatus(searchSpec, siebelFieldName, value);
			filterMap.remove("Activity.activityStatus");
		}
		String agencyFirstName = (String) filterMap.get("Activity.agencyContactFirstName");
		if (agencyFirstName != null) {
			searchSpec.append(buildAgencyFirstNameSearchSpec(agencyFirstName));
			filterMap.remove("Activity.agencyContactFirstName");
		}
		String agencyLastName = (String) filterMap.get("Activity.agencyContactLastName");
		if (agencyLastName != null) {
			searchSpec.append(buildAgencyLastNameSearchSpec(agencyLastName));
			filterMap.remove("Activity.agencyContactLastName");
		}
		String agencyEmailName = (String) filterMap.get("Activity.agencyContacteMail");
		if (agencyEmailName != null) {
			searchSpec.append(buildAgencyMailSearchSpec(agencyEmailName));
			filterMap.remove("Activity.agencyContacteMail");
		}
		searchSpec.append(AmindServiceUtil.buildCriteriaForRequestFilter(filterMap, activityFieldMap, true, true));
		return searchSpec.toString();
	}

	public static String buildActivityListFilterCriteriaSR(Map<String, Object> filterMap, Map<String, String> activityFieldMap, 
			boolean activityListCall, Date portalDate) {
		StringBuilder searchSpec = new StringBuilder();
		String serviceRequestValue = (String) filterMap.get("Activity.serviceRequest.serviceRequestType");
		if (serviceRequestValue != null) {
			searchSpec.append(buildRequestTypeSRSearchSpec(serviceRequestValue));
			filterMap.remove("Activity.serviceRequest.serviceRequestType");
		}
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		Date activityStartDate = (Date) filterMap.get("Activity.activityDate.startDate");
		if (activityStartDate != null) {
			searchSpec.append(buildActivityStartDateSR(dateFormat.format(activityStartDate)));
			filterMap.remove("Activity.activityDate.startDate");
		}
		Date activityEndDate = (Date) filterMap.get("Activity.activityDate.endDate");
		if (activityEndDate != null) {
			searchSpec.append(buildActivityEndDateSR(dateFormat.format(activityEndDate)));
			filterMap.remove("Activity.activityDate.endDate");
		}
		Date claimStatusDate = (Date) filterMap.get("Activity.claimStatusDate");
		if (claimStatusDate != null) {
			searchSpec.append(buildClaimStatusDateSR(dateFormat.format(claimStatusDate)));
			filterMap.remove("Activity.claimStatusDate");
		}
		String serialNumer = (String) filterMap.get("Activity.serviceRequest.asset.serialNumber");
		if (serialNumer != null) {
			searchSpec.append(buildSerialNumberSRSearchSpec(serialNumer));
			filterMap.remove("Activity.serviceRequest.asset.serialNumber");
		}
		String responseMetric = (String) filterMap.get("Activity.responseMetric");
		if (responseMetric != null) {
			searchSpec.append(buildResponseMatricSearchSpec(responseMetric));
			filterMap.remove("Activity.responseMetric");
		}
		if (filterMap.containsKey("Activity.customerAccount.accountName") && activityListCall) {
			String siebelFieldName = (String) activityFieldMap.get("Activity.customerAccount.accountName");
			String value = (String) filterMap.get("Activity.customerAccount.accountName");
			addAccountNameFilter(searchSpec, siebelFieldName, value);
			filterMap.remove("Activity.customerAccount.accountName");
		}
		if (filterMap.containsKey("Activity.activitySubStatus")) {
			String siebelFieldName = (String) activityFieldMap.get("Activity.activitySubStatus");
			String value = (String) filterMap.get("Activity.activitySubStatus");
			addactivitySubStatus(searchSpec, siebelFieldName, value);
			filterMap.remove("Activity.activitySubStatus");
		}
		if (filterMap.containsKey("Activity.activityType")) {
			String siebelFieldName = (String) activityFieldMap.get("Activity.activityType");
			List<String> values = (List<String>) filterMap.get("Activity.activityType");
			if (LangUtil.isNotEmpty(values) && values != null) {
				String activityTypeFilterExpr = addActivityTypeList(siebelFieldName, values);
				if (LangUtil.isNotEmpty(activityTypeFilterExpr)) {
					searchSpec.append(" AND ").append(activityTypeFilterExpr);
				}
			}
			filterMap.remove("Activity.activityType");
		}
		String childSR = (String) filterMap.get("Activity.createChildSR");
		if (childSR != null) {
			if (!"All".equalsIgnoreCase(childSR)) {
				long timeDifferance = (long) 6.48e+7;
				long timeStemp = portalDate.getTime() - timeDifferance;
				Date d = new Date(timeStemp);
				dateFormat.format(d);
				searchSpec.append(" AND [LXK R Debrief Status] IS NOT NULL ");
				searchSpec.append(" AND ").append("LXK R Last Status Update >= '" + dateFormat.format(d) + "'");
				searchSpec.append(" AND ([LXK SW Service Request Type] <> 'Fleet Management' AND [LXK SW Service Request Type] <> 'Consumables Management' AND [LXK SW Service Request Type] <> 'Claims')");
			}
			filterMap.remove("Activity.createChildSR");
		}

		if (filterMap.containsKey("Activity.activityStatus")) {
			String siebelFieldName = (String) activityFieldMap.get("Activity.activityStatus");
			String value = (String) filterMap.get("Activity.activityStatus");
			addActivityStatus(searchSpec, siebelFieldName, value);
			filterMap.remove("Activity.activityStatus");
		}
		String agencyFirstName = (String) filterMap.get("Activity.agencyContactFirstName");
		if (agencyFirstName != null) {
			searchSpec.append(buildAgencyFirstNameSearchSpec(agencyFirstName));
			filterMap.remove("Activity.agencyContactFirstName");
		}
		String agencyLastName = (String) filterMap.get("Activity.agencyContactLastName");
		if (agencyLastName != null) {
			searchSpec.append(buildAgencyLastNameSearchSpec(agencyLastName));
			filterMap.remove("Activity.agencyContactLastName");
		}
		String agencyEmailName = (String) filterMap.get("Activity.agencyContacteMail");
		if (agencyEmailName != null) {
			searchSpec.append(buildAgencyMailSearchSpec(agencyEmailName));
			filterMap.remove("Activity.agencyContacteMail");
		}
		
		searchSpec.append(AmindServiceUtil.buildCriteriaForRequestFilter(filterMap, activityFieldMap, true, true));
		return searchSpec.toString();
	}

	private static String addActivityTypeList(String siebelFieldName, List<String> values) {
		boolean firstRecord = true;
		StringBuilder builder = new StringBuilder();
		builder.append("(");
		for (String value : values) {
			if (!firstRecord) {
				builder.append(" OR ");
			}
			builder.append("[");
			builder.append(siebelFieldName);
			builder.append("]= '");
			builder.append(value);
			builder.append("'");
			if (firstRecord) {
				firstRecord = false;
			}
		}
		builder.append(")");
		return builder.toString();
	}

	private static void addActivityStatus(StringBuilder searchSpec,	String siebelFieldName, String value) {
		searchSpec.append(" AND ([");
		searchSpec.append(siebelFieldName);
		searchSpec.append("]= '");
		searchSpec.append(value);
		searchSpec.append("') ");
	}

	private static void addServiceRequestStatusDetail(StringBuilder searchSpec,	String siebelFieldName, String value) {
		searchSpec.append(" AND ([");
		searchSpec.append(siebelFieldName);
		searchSpec.append("]= '");
		searchSpec.append(value);
		searchSpec.append("') ");
	}

	private static void addAccountNameFilter(StringBuilder searchSpec, String siebelFieldName, String value) {
		searchSpec.append(" AND ([");
		searchSpec.append(siebelFieldName);
		searchSpec.append("] ~LIKE '*");
		searchSpec.append(value);
		searchSpec.append("*' OR ([");
		searchSpec.append(siebelFieldName);
		searchSpec.append("] IS NULL AND [LXK SD New Customer Acct Name] ~LIKE '*");
		searchSpec.append(value);
		searchSpec.append("*'))");
	}

	private static void addactivitySubStatus(StringBuilder searchSpec, String siebelFieldName, String value) {
		searchSpec.append(" AND ([");
		searchSpec.append(siebelFieldName);
		searchSpec.append("]= '");
		searchSpec.append(value);
		searchSpec.append("') ");
	}

	public static String buildResponseMatricSearchSpec(String responseMatric) {
		String searchSpec = "";
		searchSpec = " AND EXISTS ([LXK SW Response Metric] LIKE '*" + responseMatric + "*') ";
		return searchSpec;
	}

	public static String buildSerialNumberSearchSpec(String serialNumber) {
		String searchSpec = "";
		searchSpec = " AND ([Serial Number] ~LIKE '*" + serialNumber + "*' "
				+ "OR [LXK SD Serial Number] ~LIKE '*" + serialNumber + "*' )";
		return searchSpec;
	}

	public static String buildSerialNumberSRSearchSpec(String serialNumber) {
		String searchSpec = "";
		searchSpec = " AND EXISTS([Serial Number] ~LIKE '*" + serialNumber
				+ "*' " + "OR EXISTS[LXK SD Serial Number] ~LIKE '*"
				+ serialNumber + "*' )";
		return searchSpec;
	}

	public static String buildAgencyFirstNameSearchSpec(String agencyFirstName) {
		String searchSpec = "";
		searchSpec = " AND ([Agency Contact First Name] ~LIKE '*"
				+ agencyFirstName + "*') ";
		return searchSpec;
	}

	public static String buildAgencyLastNameSearchSpec(String agencyLastName) {
		String searchSpec = "";
		searchSpec = " AND ([Agency Contact Last Name] ~LIKE '*"
				+ agencyLastName + "*') ";
		return searchSpec;
	}

	public static String buildAgencyMailSearchSpec(String agencyEmailName) {
		String searchSpec = "";
		searchSpec = " AND ([LXK Agency Contact eMail] ~LIKE '*"
				+ agencyEmailName + "*') ";
		return searchSpec;
	}

	public static String buildStatusSearchSpec(String status, ActivityListContract contract) {
		StringBuilder searchSpec = new StringBuilder();
		if ("Fleet Management".equalsIgnoreCase(contract.getServiceRequestType())
				|| filterCriteriaContainsRequestType(contract.getFilterCriteria(), RequestType.Fleet.toString())) {
			if (status.contains(ActivityStatus.All.toString())) {
				searchSpec.append("([Status] = 'Cancelled Service' "
						+ " OR [Status] = 'Invalid Debrief'"
						+ " OR [Status] = 'Dispatched to SP'"
						+ " OR [Status] = 'Cancelled Parts without Visit'");
				if (contract.isMassUploadRequest()) {
					searchSpec.append(" AND [Status]<>'Completed')");
				} else {
					searchSpec.append(" OR [Status] = 'Completed')");
				}
			} else if (status.contains(ActivityStatus.Open.toString())) {
				searchSpec.append("([Status] = 'Invalid Debrief' "
						+ " OR [Status] = 'Dispatched to SP'");
				if (contract.isMassUploadRequest()) {
					searchSpec.append(" AND [Status]<>'Completed'");
				}
				searchSpec.append(")");
			} else if (status.contains(ActivityStatus.Closed.toString())) {
				searchSpec.append("( [Status] = 'Cancelled Service'"
						+ " OR [Status] = 'Cancelled Parts without Visit'");
				searchSpec.append(" OR [Status] = 'Completed')");
			}
		} else {
			if (status.contains(ActivityStatus.All.toString())) {
				searchSpec.append("([Status] = 'Cancelled Service' "
						+ " OR [Status] = 'Pending Service Action'"
						+ " OR [Status] = 'Completed' "
						+ " OR [Status] = 'Claim Submitted' "
						+ " OR [Status] = 'Invalid Debrief'"
						+ " OR [Status] = 'Dispatched to SP'"
						+ " OR [Status] = 'Cancelled Parts without Visit')");
			} else if (status.contains(ActivityStatus.Open.toString())) {
				searchSpec.append("([Status] = 'Pending Service Action' "
						+ " OR [Status] = 'Claim Submitted' "
						+ " OR [Status] = 'Invalid Debrief' "
						+ " OR [Status] = 'Dispatched to SP')");
			} else if (status.contains(ActivityStatus.Closed.toString())) {
				searchSpec.append("( [Status] = 'Completed' "
						+ " OR [Status] = 'Cancelled Service'"
						+ " OR [Status] = 'Cancelled Parts without Visit')");
			}
		}
		return searchSpec.toString();
	}

	public static String buildStatusSearchSpecSR(String status,	ActivityListContract contract) {
		StringBuilder searchSpec = new StringBuilder();
		if ("Fleet Management".equalsIgnoreCase(contract.getServiceRequestType())
				|| filterCriteriaContainsRequestType(contract.getFilterCriteria(), RequestType.Fleet.toString())) {
			if (status.contains(ActivityStatus.All.toString())) {
				searchSpec.append("EXISTS([Act Status] = 'Cancelled Service' "
						+ " OR [Act Status] = 'Invalid Debrief'"
						+ " OR [Act Status] = 'Dispatched to SP'"
						+ " OR [Act Status] = 'Cancelled Parts without Visit'");
				if (contract.isMassUploadRequest()) {
					searchSpec.append(" AND EXISTS[Act Status]<>'Completed')");
				} else {
					searchSpec.append(" OR [Act Status] = 'Completed')");
				}
			} else if (status.contains(ActivityStatus.Open.toString())) {
				searchSpec.append("EXISTS([Act Status] = 'Invalid Debrief' "
						+ " OR [Act Status] = 'Dispatched to SP'");
				if (contract.isMassUploadRequest()) {
					searchSpec.append(" AND EXISTS[Act Status]<>'Completed'");
				}
				searchSpec.append(")");
				// else {
				// searchSpec.append(" OR [Status] = 'Completed')");
				// }
			} else if (status.contains(ActivityStatus.Closed.toString())) {
				searchSpec.append("EXISTS([Act Status] = 'Cancelled Service'"
						+ " OR [Act Status] = 'Cancelled Parts without Visit'");
				searchSpec.append(" OR [Act Status] = 'Completed')");
			}
		} else {
			if (status.contains(ActivityStatus.All.toString())) {
				searchSpec.append("EXISTS ([Act Status] = 'Cancelled Service' "
								+ " OR [Act Status] = 'Pending Service Action'"
								+ " OR [Act Status] = 'Completed' "
								+ " OR [Act Status] = 'Claim Submitted' "
								+ " OR [Act Status] = 'Invalid Debrief'"
								+ " OR [Act Status] = 'Dispatched to SP'"
								+ " OR [Act Status] = 'Cancelled Parts without Visit')");
			} else if (status.contains(ActivityStatus.Open.toString())) {
				searchSpec.append("EXISTS ([Act Status] = 'Pending Service Action' "
								+ " OR [Act Status] = 'Claim Submitted' "
								+ " OR [Act Status] = 'Invalid Debrief' "
								+ " OR [Act Status] = 'Dispatched to SP')");
			} else if (status.contains(ActivityStatus.Closed.toString())) {
				searchSpec.append("EXISTS ([Act Status] = 'Completed' "
								+ " OR [Act Status] = 'Cancelled Service'"
								+ " OR [Act Status] = 'Cancelled Parts without Visit')");
			}
		}
		return searchSpec.toString();
	}

	public static String buildRequestTypeSearchSpec(String requestType) {
		String searchSpec = "";
		if (requestType.contains(RequestType.Claim.toString())) {
			searchSpec = " AND [LXK SW Service Request Type] = 'Claims'";
		} else if (requestType.contains(RequestType.Service.toString())) {
			searchSpec = " AND [LXK SW Service Request Type] <> 'Claims'";
		}
		return searchSpec;
	}

	public static String buildRequestTypeSRSearchSpec(String requestType) {
		String searchSpec = "";
		if (requestType.contains(RequestType.Claim.toString())) {
			searchSpec = " AND EXISTS[LXK SW Service Request Type] = 'Claims'";
		} else if (requestType.contains(RequestType.Service.toString())) {
			searchSpec = " AND EXISTS[LXK SW Service Request Type] <> 'Claims'";
		}
		return searchSpec;
	}

	public static String buildRequestTypeSearchSpecSR(String requestType) {
		String searchSpec = "";
		if (requestType.contains(RequestType.Claim.toString())) {
			searchSpec = " AND EXISTS[LXK SW Service Request Type] = 'Claims'";
		} else if (requestType.contains(RequestType.Service.toString())) {
			searchSpec = " AND EXISTS[LXK SW Service Request Type] <> 'Claims'";
		}
		return searchSpec;
	}

	public static String buildBasicActivitySearchSpec(ActivityListContract contract, IDataManager dataManager, Map<String, String> activityFieldMap) throws Exception {
		StringBuilder searchSpec = new StringBuilder();

		try {
			if (contract.isEmployeeFlag()) {
				searchSpec.append(buildSearchSpecForPartnerEmployee(contract.getEmployeeId(), dataManager));
			} else {
				searchSpec.append(buildMdmSearchExpressionForMdmLevel(
						contract.getMdmId(), contract.getMdmLevel(),
						activityFieldMap, false, "LXK SW SP Account Level"));
			}
			if (contract.getQueryType().equals(QueryType.My.toString())) {
				if (!StringUtils.isEmpty(searchSpec.toString())) {
					searchSpec.append(" AND ");
				}
				searchSpec.append(" [LXK R Tech Id] = '"
						+ contract.getEmployeeId() + "'");
			} else if (contract.getQueryType().equals(QueryType.Unassigned.toString())) {
				if (!StringUtils.isEmpty(searchSpec.toString())) {
					searchSpec.append(" AND ");
				}
				searchSpec.append("[LXK R Tech Id] IS NULL ");
			}
		} catch (Exception e) {
			throw e;
		}
		return searchSpec.toString();
	}

	public static String buildBasicActivitySearchSpecSR(ActivityListContract contract, IDataManager dataManager,
			Map<String, String> activityFieldMap) throws Exception {
		StringBuilder searchSpec = new StringBuilder();

		try {
			if (contract.isEmployeeFlag()) {
				searchSpec.append(buildSearchSpecForPartnerEmployee(contract.getEmployeeId(), dataManager));
			} else {
				searchSpec.append(buildMdmSearchExpressionForMdmLevelSR(
						contract.getMdmId(), contract.getMdmLevel(),
						activityFieldMap, false, "LXK SW SP Account Level"));
			}
			if (contract.getQueryType().equals(QueryType.My.toString())) {
				if (!StringUtils.isEmpty(searchSpec.toString())) {
					searchSpec.append(" AND ");
				}
				searchSpec.append(" [LXK R Tech Id] = '"
						+ contract.getEmployeeId() + "'");
			} else if (contract.getQueryType().equals(
					QueryType.Unassigned.toString())) {
				if (!StringUtils.isEmpty(searchSpec.toString())) {
					searchSpec.append(" AND ");
				}
				searchSpec.append("[LXK R Tech Id] IS NULL ");
			}
		} catch (Exception e) {
			throw e;
		}
		return searchSpec.toString();
	}

	public static String buildActivityStartDateSR(String activityStartDate) {
		String searchSpec = "";
		searchSpec = " AND EXISTS([LXK SW Activity Created] >= '"
				+ activityStartDate + "'";
		return searchSpec;
	}

	public static String buildActivityStartDate(String activityStartDate) {
		String searchSpec = "";
		searchSpec = " AND [LXK SW Activity Created] >= '" + activityStartDate
				+ "'";
		return searchSpec;
	}

	public static String buildExpectedCompletionStartDate(String activityStartDate) {
		String searchSpec = "";
		searchSpec = " AND [LXK MPS Expected End Date] >= '"
				+ activityStartDate + "'";
		return searchSpec;
	}

	public static String buildActivityEndDate(String activityEndDate) {
		String searchSpec = "";
		searchSpec = " AND [LXK SW Activity Created] <= '" + activityEndDate
				+ "'";
		return searchSpec;
	}

	public static String buildActivityEndDateSR(String activityEndDate) {
		String searchSpec = "";
		searchSpec = " AND [LXK SW Activity Created] <= '" + activityEndDate
				+ "')";
		return searchSpec;
	}

	public static String buildExpectedCompletionEndDate(String activityEndDate) {
		String searchSpec = "";
		searchSpec = " AND [LXK MPS Expected End Date] <= '" + activityEndDate
				+ "'";
		return searchSpec;
	}

	public static String buildClaimStatusDate(String activityEndDate) {
		String searchSpec = "";
		searchSpec = " AND [LXK R Sub Status Update] IS NOT NULL AND [LXK R Sub Status Update] <= '"
				+ activityEndDate + "'";
		return searchSpec;
	}

	public static String buildClaimStatusDateSR(String activityEndDate) {
		String searchSpec = "";
		searchSpec = " AND [LXK R Sub Status Update] IS NOT NULL AND EXISTS [LXK R Sub Status Update] <= '"
				+ activityEndDate + "'";
		return searchSpec;
	}

	@SuppressWarnings("unchecked")
	private static String buildSearchSpecForPartnerEmployee(String employeeId, IDataManager dataManager) throws Exception {
		StringBuilder searchSpec = new StringBuilder();
		List<PartnerActivityEmployeeAccountDo> partnerAccountList = dataManager.queryBySearchExpression(PartnerActivityEmployeeAccountDo.class,	"[employeeId] = '" + employeeId + "'");
		boolean firstAccountRecord = false;
		if (isNotEmpty(partnerAccountList)) {
			for (PartnerActivityEmployeeAccountDo partnerAccount : partnerAccountList) {
				if (firstAccountRecord) {
					searchSpec.append(" OR ");
				} else {
					searchSpec.append(" ( ");
				}
				searchSpec.append("[LXK R Service Provider Id] = '");
				searchSpec.append(partnerAccount.getAccountId());
				searchSpec.append("'");
				firstAccountRecord = true;
			}
			searchSpec.append(")");
		}
		return searchSpec.toString();
	}

	public static String buildRequestTypeSearchSpec(ActivityListContract contract) {
		StringBuilder requestTypeBuilder = new StringBuilder("");

		if ("Service Request".equalsIgnoreCase(contract.getServiceRequestType())
				|| filterCriteriaContainsRequestType(contract.getFilterCriteria(), RequestType.Service.toString())) {
			requestTypeBuilder.append(" AND [Type] <> 'HW Install' AND [Type] <> 'HW Install Move' AND [Type] <> 'HW Install Change' "
							+ "AND [Type] <> 'HW Install Decommission' AND [Type] <> 'HW BAU Install' AND [Type] <> 'HW BAU Install Move' "
							+ "AND [Type] <> 'HW BAU Install Change' AND [Type] <> 'HW BAU Install Decommission' "
							+ "AND [Type] <> 'HW MADC Install' AND [Type] <> 'HW MADC Move' AND [Type] <> 'HW MADC Change' AND [Type] <> 'HW MADC Decommission'"
							+ "AND [Type] <> 'HW Proj Install/Decommission' AND [Type] <> 'HW BAU Install/Decommission' AND [Type] <> 'HW MADC Install/Decommission'");
		}
		if ("Fleet Management".equalsIgnoreCase(contract.getServiceRequestType())
				|| filterCriteriaContainsRequestType(contract.getFilterCriteria(), RequestType.Fleet.toString())) {
			requestTypeBuilder.append(" AND ([Type] = 'HW Install' OR [Type] = 'HW Install Move' OR [Type] = 'HW Install Change' "
							+ "OR [Type] = 'HW Install Decommission' OR [Type] = 'HW BAU Install' OR [Type] = 'HW BAU Install Move' "
							+ "OR [Type] = 'HW BAU Install Change' OR [Type] = 'HW BAU Install Decommission' OR [Type] = 'HW MADC Install' "
							+ "OR [Type] = 'HW MADC Move' OR [Type] = 'HW MADC Change' OR [Type] = 'HW MADC Decommission' "
							+ "OR [Type] = 'HW Proj Install/Decommission' OR [Type] = 'HW BAU Install/Decommission' OR [Type] = 'HW MADC Install/Decommission')");
		}
		return requestTypeBuilder.toString();
	}

	private static boolean filterCriteriaContainsRequestType(Map<String, Object> filterCriteria, String requestType) {
		if (filterCriteria != null
				&& !filterCriteria.isEmpty()
				&& filterCriteria.containsKey("Activity.serviceRequest.serviceRequestType")
				&& ((String) filterCriteria.get("Activity.serviceRequest.serviceRequestType")).contains(requestType)) {
			return true;
		}
		return false;
	}

	public static String buildChildSRfilterSearchSpec(ActivityListContract contract) {
		if (isNotBlank(contract.getStatusLastUpdate())) {
			StringBuilder builder = new StringBuilder();
			builder.append(" AND ([LXK R Debrief Status] IS NOT NULL AND [LXK R Last Status Update] >= '");
			builder.append(contract.getStatusLastUpdate());
			builder.append("'");
			builder.append(" AND ([LXK SW Service Request Type] <> 'Fleet Management' AND [LXK SW Service Request Type] <> 'Consumables Management' AND [LXK SW Service Request Type] <> 'Claims'))");
			return builder.toString();
		}
		return null;
	}
}
