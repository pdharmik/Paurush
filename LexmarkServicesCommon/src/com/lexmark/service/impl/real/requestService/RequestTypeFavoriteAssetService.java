package com.lexmark.service.impl.real.requestService;

import static com.lexmark.service.impl.real.requestService.AmindRequestTypeConversionUtil.convertRequestTypeList;
import static com.lexmark.service.impl.real.util.AmindServiceUtil.buildCriteria;
import static com.lexmark.service.impl.real.util.AmindServiceUtil.buildSortString;
import static com.lexmark.service.impl.real.util.AmindServiceUtil.getTotalCount;
import static com.lexmark.util.LangUtil.isEmpty;
import static com.lexmark.util.LangUtil.isNotEmpty;
import static com.lexmark.util.LangUtil.notNull;

import java.util.List;
import java.util.Map;

import com.amind.common.service.SiebelBusinessServiceProxy;
import com.amind.data.service.ActionEvent;
import com.amind.data.service.ExecutionMode;
import com.amind.data.service.QueryObject;
import com.amind.session.Session;
import com.lexmark.contract.RequestListContract;
import com.lexmark.domain.ServiceRequest;
import com.lexmark.service.impl.real.domain.RequestTypeFavoriteAssetDo;
import com.lexmark.service.impl.real.util.AmindServiceUtil;
import com.lexmark.util.LangUtil;

public class RequestTypeFavoriteAssetService {

	private String searchExpression;
	private QueryObject criteria;
	private Session session;
	private Session totalCountSession;
    private RequestListContract contract;
    private int increment;
    private final int startRecordNumber;
    private final Map<String, Object> filterCriteria;
	private final Map<String, Object> sortCriteria;
	private final Map<String, String> fieldMap;
	boolean breakFix;
	boolean consumables;
	boolean showChangeRequests;
	boolean showHardwareRequests;

	public RequestTypeFavoriteAssetService(RequestListContract contract,Map<String, String> fieldMap) {
		this.contract = contract;
		this.filterCriteria = contract.getFilterCriteria();
		this.sortCriteria = contract.getSortCriteria();
		this.fieldMap = fieldMap;
		this.increment = contract.getIncrement();
		this.startRecordNumber = contract.getStartRecordNumber();
		
		breakFix = isBreakFix();
		consumables = isConsumables();
		showChangeRequests = isChangeRequest() || allRequestsChangeRequestsPermission();
		showHardwareRequests = isHardwareRequest() || allRequestHardware();
	}

	public void checkRequiredFields() {
		if (isEmpty(contract.getContactId())) {
			throw new IllegalArgumentException("contractId is null or empty!");
		} else if (isEmpty(contract.getMdmId())) {
			throw new IllegalArgumentException("mdmId is null or empty!");
		} else if (isEmpty(contract.getMdmLevel())) {
			throw new IllegalArgumentException("mdmLevel is null or empty!");
		}
	}

	public void buildSearchExpression() {
		searchExpression = buildRequestTypeFavoriteAssetSearchExpression();
		criteria = buildRequestTypeFavoriteAssetCriteria();
	}

	private QueryObject buildRequestTypeFavoriteAssetCriteria() {
		QueryObject criteria = new QueryObject(RequestTypeFavoriteAssetDo.class, ActionEvent.QUERY);
		criteria.setExecutionMode(ExecutionMode.BiDirectional);
		criteria.setNumRows(increment);
		criteria.setStartRowIndex(startRecordNumber);
		criteria.addComponentSearchSpec(RequestTypeFavoriteAssetDo.class, searchExpression);

		if (isNotEmpty(sortCriteria)) {
			criteria.setSortString(buildSortString(sortCriteria, fieldMap));
		}
		return criteria;
	}

	private String buildRequestTypeFavoriteAssetSearchExpression() {
		StringBuilder builder = new StringBuilder();
		
		builder.append(buildTypeExpr());
		builder.append(" AND ");
		builder.append("[LXK MPS SR Status] <> 'Archived'");
		builder.append(" AND ");
		buildMainSearchExpression(builder);
		appendFilterSearchExpression(builder);
		builder.append(" AND ");
		builder.append(buildAreaExpr());
		builder.append(" AND ");
		builder.append("[LXK Asset Record Flag] = 'Y'");
		
    	return  builder.toString();
	}
	
	private String buildAreaExpr() {
		StringBuilder areaBuilder = new StringBuilder();
		areaBuilder.append("(");
		
		if(breakFix || consumables) {
			areaBuilder.append("([LXK MPS SR Area] ~<> 'Adhoc' AND [LXK MPS SR Area] ~<> 'Install')");
		}
		if(showChangeRequests) {
			if(breakFix || consumables) {
				areaBuilder.append(" AND ");
			}
			areaBuilder.append("([LXK MPS SR Area] ~<> 'HW Order' AND [LXK MPS SR Area] ~<> 'Install')");
		}
		if(showHardwareRequests) {
			if(breakFix || consumables || showChangeRequests) {
				areaBuilder.append(" OR ");
			}
			areaBuilder.append("([LXK MPS SR Area] ~= 'HW Order' OR [LXK MPS SR Area] ~= 'Install')");
		}
		if(breakFix || consumables) {
			areaBuilder.append(" OR [LXK MPS SR Area] IS NULL");
		}
		areaBuilder.append(")");
		
		return areaBuilder.toString();
	}

	private String buildTypeExpr() {
		StringBuilder typeBuilder = new StringBuilder();
		typeBuilder.append("(");
		
		if(breakFix) {
			typeBuilder.append("[LXK MPS Type] IS NULL");
		}
		if(consumables || showChangeRequests || showHardwareRequests) {
			if(breakFix) {
				typeBuilder.append(" OR ");
			}
			typeBuilder.append("[LXK MPS Type] = 'MPS'");
		}
		
		typeBuilder.append(")");
			
		return typeBuilder.toString();
	}

	private boolean allRequestHardware() {
		return contract.isHardwareRequestsPermission() && isFleetManagement()
				&& !contract.isChangeRequestFlag() && !contract.isHardwareRequestFlag();
	}

	private boolean isHardwareRequest() {
		return contract.isHardwareRequestFlag();
	}

	private boolean allRequestsChangeRequestsPermission() {
		return contract.isChangeRequestsPermission() && isFleetManagement()
				&& !contract.isChangeRequestFlag() && !contract.isHardwareRequestFlag();
	}

	private boolean isFleetManagement() {
		List<String> requestType = null;
		if (isNotEmpty(filterCriteria) && filterCriteria.containsKey("requestType")) {
			requestType = (List<String>) filterCriteria.get("requestType");
			return requestType.contains("Fleet Management");
		}
		return false;
	}

	private boolean isChangeRequest() {
		return contract.isChangeRequestFlag();
	}

	private boolean isConsumables() {
		List<String> requestType = null;
		if (isNotEmpty(filterCriteria) && filterCriteria.containsKey("requestType")) {
			requestType = (List<String>) filterCriteria.get("requestType");
			return requestType.contains("Consumables Management");
		}
		return false;
	}

	private boolean isBreakFix() {
		List<String> requestType = null;
		if (isNotEmpty(filterCriteria) && filterCriteria.containsKey("requestType")) {
			requestType = (List<String>) filterCriteria.get("requestType");
			return requestType.contains("BreakFix");
		}
		return false;
	}

	private void buildMainSearchExpression(StringBuilder builder){
		builder.append(AmindServiceUtil.buildMdmSearchExpressionForMdmLevel
				(contract.getMdmId(), contract.getMdmLevel(), fieldMap, false, "LXK MPS Account Level"));
		builder.append(" AND ");
		builder.append("EXISTS([LXK MPS Asset Contact Id] = '");
		builder.append(contract.getContactId());
		builder.append("')");
	}

	private void appendFilterSearchExpression(StringBuilder builder) {
		
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
			List<String> values = (List<String>) filterCriteria.get("requestType");
			String fieldName = fieldMap.get("requestType");
			String requestTypeFilterExpr = RequestTypeListService.siebelFilterExpr(fieldName, values);
			if (LangUtil.isNotEmpty(requestTypeFilterExpr)) {
				builder.append(" AND ").append(requestTypeFilterExpr);
			}
			filterCriteria.remove("requestType");
		}

		if (filterCriteria.containsKey("area") && (filterCriteria.get("area") instanceof List<?>)) {
			String fieldName = fieldMap.get("area");
			List<String> allValues = (List<String>) filterCriteria.get("area");
			builder.append(AmindServiceUtil.buildMultipleFilterCriteria(fieldName, allValues, true, true));
			filterCriteria.remove("area");
		}
		
		builder.append(buildCriteria(filterCriteria, fieldMap, true, true));
	}

	@SuppressWarnings("unchecked")
	public List<ServiceRequest> queryAndGetResultList() {
		List<RequestTypeFavoriteAssetDo> requestDoList = notNull(getSession().getDataManager().query(criteria));
		return convertRequestTypeList(requestDoList);
	}

	public int processTotalCount() {
		int totalCount = 0;
		SiebelBusinessServiceProxy proxy = getTotalCountSession().getSiebelBusServiceProxy();
		totalCount = getTotalCount("LXK MPS Bookmark Asset Service Request", "LXK MPS Bookmark Asset Service Request",
				searchExpression, proxy);
		return totalCount;
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

}
