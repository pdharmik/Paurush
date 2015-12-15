package com.lexmark.service.impl.real.requestService;

import static com.lexmark.service.impl.real.requestService.AmindRequestTypeConversionUtil.convertRequestTypeActivitiesDotoRequestTypeActivities;
import static com.lexmark.service.impl.real.util.AmindServiceUtil.buildCriteria;
import static com.lexmark.service.impl.real.util.AmindServiceUtil.buildSearchCriteria;
import static com.lexmark.service.impl.real.util.AmindServiceUtil.buildSortString;
import static com.lexmark.service.impl.real.util.AmindServiceUtil.getTotalCount;
import static com.lexmark.util.LangUtil.isNotEmpty;

import java.util.List;
import java.util.Map;

import com.amind.common.service.SiebelBusinessServiceProxy;
import com.amind.data.service.ActionEvent;
import com.amind.data.service.ExecutionMode;
import com.amind.data.service.QueryObject;
import com.amind.session.Session;
import com.lexmark.contract.RequestContract;
import com.lexmark.domain.ServiceRequest;
import com.lexmark.service.impl.real.domain.RequestTypeActivitiesDo;

public class RequestTypeActivitiesService {
	private final RequestContract contract;
	private String searchExpression;
	private QueryObject criteria;
	private Session session;
	private final Map<String, String> fieldMap;
	
	public RequestTypeActivitiesService(RequestContract contract, Map<String, String> fieldMap) {
		if (contract == null) {
			throw new IllegalStateException("contract can not be null");
		}
		this.contract = contract;
		this.fieldMap = fieldMap;
	}
	
	public void checkRequiredFields() {
		String requestNumber = contract.getServiceRequestNumber();
		if (requestNumber == null) {
			throw new IllegalArgumentException("serviceRequestNumber is null");
		} else if (requestNumber.isEmpty()) {
			throw new IllegalArgumentException("serviceRequestNumber is empty");
		}
	}
	
	public void buildSearchExpression() {
		searchExpression = buildRequestTypeActivitiesSearchExpression();
		criteria = buildRequestTypeActivitiesCriteria();
	}
	
	private String buildRequestTypeActivitiesSearchExpression() {
		StringBuilder builder = new StringBuilder("[SR Number] = '");
		builder.append(contract.getServiceRequestNumber());
		builder.append("'");
		
		Map<String, Object> searchCriteria = contract.getSearchCriteria();
		if (isNotEmpty(searchCriteria)) {
			builder.append(buildSearchCriteria(searchCriteria, fieldMap));
		}

		Map<String, Object> filterCriteria = contract.getFilterCriteria();
		if (isNotEmpty(filterCriteria)) {
			builder.append(buildCriteria(filterCriteria, fieldMap, true, true));
		}
		
		return builder.toString();
	}
	
	private QueryObject buildRequestTypeActivitiesCriteria() {
		QueryObject criteria = new QueryObject(RequestTypeActivitiesDo.class, ActionEvent.QUERY);
		criteria.setExecutionMode(ExecutionMode.BiDirectional);
		criteria.setNumRows(contract.getIncrement());
		criteria.setStartRowIndex(contract.getStartRecordNumber());
		criteria.addComponentSearchSpec(RequestTypeActivitiesDo.class, searchExpression);	
		
		Map<String, Object> sortCriteria = contract.getSortCriteria();
		if (isNotEmpty(sortCriteria)) {
			criteria.setSortString(buildSortString(sortCriteria, fieldMap));
		}
		
		return criteria;
	}
	
	public int processTotalCount() {
		int totalCount = 0;
		SiebelBusinessServiceProxy proxy = getSession().getSiebelBusServiceProxy();
		totalCount = getTotalCount("Action", "Action", searchExpression, proxy); 
		return totalCount;
	}

	
	@SuppressWarnings("unchecked")
	public ServiceRequest queryAndGetRequestTypeActivites() {
		List<RequestTypeActivitiesDo> requestTypeActivities = getSession().getDataManager().query(criteria);
		
		return convertRequestTypeActivitiesDotoRequestTypeActivities(requestTypeActivities);
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

}
