package com.lexmark.service.impl.real.requestService;

import static com.lexmark.service.impl.real.util.AmindServiceUtil.buildSortString;
import static com.lexmark.util.LangUtil.isNotEmpty;
import static org.apache.commons.lang.StringUtils.isNotEmpty;

import java.util.List;
import java.util.Map;

import com.amind.data.service.ActionEvent;
import com.amind.data.service.IDataManager;
import com.amind.data.service.QueryObject;
import com.amind.session.Session;
import com.lexmark.contract.ServiceRequestAssociatedListContract;
import com.lexmark.service.impl.real.domain.RequestTypeDo;
import com.lexmark.service.impl.real.requestService.AmindRequestTypeConversionUtil;
import com.lexmark.service.impl.real.util.AmindServiceUtil;

public class AssociatedServiceRequestListService {
	
	private final ServiceRequestAssociatedListContract contract;
	private String searchExpression;
	private Session session;
	private Map<String, String>fieldMap;
	
	public AssociatedServiceRequestListService(ServiceRequestAssociatedListContract contract, Map<String, String> fieldMap) 
	{
		if (contract == null) {
			throw new IllegalStateException("contract can not be null!");
		}
		this.contract = contract;
		this.fieldMap = fieldMap;
		
	}
	public void buildSearchExpression() {
		buildSearchExpressionForAssociatedServiceRequest();
		
	}

	public void checkRequiredFields() {
		String serviceRequestNumber = contract.getServiceRequestNumber();
		if (serviceRequestNumber == null) {
			throw new IllegalArgumentException("Service Request Number is null");
		} else if (serviceRequestNumber.isEmpty()) {
			throw new IllegalArgumentException("Service Request Number is Empty");
		}
		
	}

	public List<com.lexmark.domain.ServiceRequest> queryAndGetResultList() {
		List<RequestTypeDo> serviceRequestList = queryForServiceRequestList();
	
		if (isNotEmpty(serviceRequestList)) {
			// given Sr is child SR
			String updateSrNumber = serviceRequestList.get(0).getUpdatedSrNumber();
			if (isNotEmpty(updateSrNumber)) {
				buildSearchExpressionForChild(updateSrNumber);
				serviceRequestList = queryForServiceRequestList();
				
			}else {
				buildSearchExpressionForParent(serviceRequestList.get(0).getRequestNumber());
				serviceRequestList = queryForServiceRequestList();
			}
		}
		
		return AmindRequestTypeConversionUtil.convertRequestTypeList(serviceRequestList);
	}

	@SuppressWarnings("unchecked")
	private List<RequestTypeDo> queryForServiceRequestList() {
		IDataManager dataManager = session.getDataManager();
		QueryObject criteria = new QueryObject(RequestTypeDo.class, ActionEvent.QUERY);
        AmindServiceUtil.buildBasicQueryObject(criteria, contract.getIncrement(), contract.getStartRecordNumber());
		criteria.setQueryString(searchExpression);
		
		Map<String, Object> sortCriteria = contract.getSortCriteria();
		if (isNotEmpty(sortCriteria)) {
			criteria.setSortString(buildSortString(sortCriteria, fieldMap));
		}
		
		List<RequestTypeDo> serviceRequestList = dataManager.query(criteria);
		return serviceRequestList;
	}
	private void buildSearchExpressionForAssociatedServiceRequest(){
		searchExpression = "[requestNumber] = '" + contract.getServiceRequestNumber() + "'";
	}
	
	private void buildSearchExpressionForChild(String updatedSrNumber) {
		searchExpression = "[requestNumber] = '" + updatedSrNumber + "'";
	}
	
	private void buildSearchExpressionForParent(String serviceRequestNumber) {
		searchExpression = "[updatedSrNumber] = '" + serviceRequestNumber + "'";

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
