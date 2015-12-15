package com.lexmark.service.impl.real.requestService;

import static com.lexmark.service.impl.real.requestService.AmindRequestDataConversionUtil.convertServiceRequestHistoryList;
import static com.lexmark.service.impl.real.util.AmindServiceUtil.buildSortString;
import static com.lexmark.service.impl.real.util.AmindServiceUtil.getTotalCount;
import static com.lexmark.util.LangUtil.isNotEmpty;
import static com.lexmark.util.LangUtil.notNull;

import java.util.List;
import java.util.Map;

import com.amind.data.service.ActionEvent;
import com.amind.data.service.ExecutionMode;
import com.amind.data.service.IDataManager;
import com.amind.data.service.QueryObject;
import com.amind.session.Session;
import com.lexmark.contract.ServiceRequestHistoryListContract;
import com.lexmark.service.impl.real.domain.ServiceRequest;
import com.lexmark.service.impl.real.domain.ServiceRequestHistory;
import com.lexmark.util.LangUtil;

public class ServiceRequestHistoryListService {

	private final ServiceRequestHistoryListContract contract;
	private final Map<String, String> fieldMap;
	private Session session;
	private String searchExpression;
	private QueryObject criteria;

	public ServiceRequestHistoryListService(ServiceRequestHistoryListContract contract,
			Map<String, String> fieldMap) {
		if(contract == null) {
			throw new IllegalStateException("contract can not be null");
		}
		this.contract = contract;
		this.fieldMap = fieldMap;
	}

	public void checkRequiredFields() {
		String accountId = contract.getAccountId();
		if (accountId == null) {
			throw new IllegalArgumentException("Account Id is empty");
		} else if (accountId.isEmpty()) {
			throw new IllegalArgumentException("Account Id is empty");
		}
		String assetId = contract.getAssetId();
		if (assetId == null) {
			throw new IllegalArgumentException("Asset Id is empty");
		} else if (assetId.isEmpty()) {
			throw new IllegalArgumentException("Asset Id is empty");
		}
	}

	public void buildSearchExpression() {
		searchExpression = buildServiceRequestHistorySearchExpression();
		criteria = buildServiceRequestCriteria();
	}

	public List<com.lexmark.domain.ServiceRequest> queryAndGetResultList() {
		List<ServiceRequest> serviceRequestList = queryServiceRequestHistoryList(getSession().getDataManager());
		return convertServiceRequestHistoryList(serviceRequestList);
	}

	public int processTotalCount() {
		return getTotalCount("LXK SD Service Request - Service Web", "LXK SD Service Request - Service Web",
				searchExpression, getSession().getSiebelBusServiceProxy());
	}

	private String buildServiceRequestHistorySearchExpression() {
		StringBuilder builder = new StringBuilder("[Account Id] = '");
		builder.append(contract.getAccountId());
		builder.append("' AND [LXK SW Latest Asset Id] = '");
		builder.append(contract.getAssetId());
		builder.append("'");

		String requestNumber = contract.getServiceRequestNumber();

		if (LangUtil.isNotBlank(requestNumber)) {
			builder.append(" AND [SR Number] <> '");
			builder.append(LangUtil.trim(requestNumber));
			builder.append("'");
		}

		return builder.toString();
	}

	private QueryObject buildServiceRequestCriteria() {
		QueryObject criteria = new QueryObject(ServiceRequestHistory.class, ActionEvent.QUERY);
		criteria.setExecutionMode(ExecutionMode.BiDirectional);
		criteria.setNumRows(contract.getIncrement());
		criteria.setStartRowIndex(contract.getStartRecordNumber());
		criteria.addComponentSearchSpec(ServiceRequestHistory.class, searchExpression);

		Map<String, Object> sortCriteria = contract.getSortCriteria();
		if (isNotEmpty(sortCriteria)) {
			criteria.setSortString(buildSortString(sortCriteria, fieldMap));
		}
		
		return criteria;
	}

	@SuppressWarnings("unchecked")
	private List<ServiceRequest> queryServiceRequestHistoryList(IDataManager dataManager) {

		List<ServiceRequest> serviceRequestList;
		if (contract.isNewQueryIndicator()) {
			serviceRequestList = dataManager.query(criteria);
		} else {
			serviceRequestList = dataManager.queryNext(criteria);
		}
		return notNull(serviceRequestList);
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
