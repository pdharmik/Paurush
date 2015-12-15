package com.lexmark.service.impl.real.requestService;

import java.util.ArrayList;
import java.util.List;

import com.amind.common.domain.EntityState;
import com.amind.data.service.ActionEvent;
import com.amind.data.service.IDataManager;
import com.amind.data.service.QueryObject;
import com.amind.session.Session;
import com.lexmark.contract.source.OrderAcceptContract;
import com.lexmark.contract.source.RequestAcceptContract;
import com.lexmark.service.impl.real.domain.AcceptOrderDo;
import com.lexmark.service.impl.real.domain.AcceptOrderLineItemDo;
import com.lexmark.service.impl.real.domain.AcceptRequestDo;
import com.lexmark.util.LangUtil;

public class AcceptRequestService {
	private Session session;
	private RequestAcceptContract contract;
	private String searchExpression;
	private QueryObject criteria;
	
	public AcceptRequestService(Session session, RequestAcceptContract contract) {
		this.session = session;
		this.contract = contract;
	}
	
//	public void buildSearchExpression() {
//		searchExpression = buildAcceptOrderSearchExpression();
//		criteria = buildAcceptOrderCriteria();
//	}

//	private String buildAcceptOrderSearchExpression() {
//		StringBuilder result = new StringBuilder();
//		result.append("[requestNumber] = '"); 
//		result.append(contract.getActivityId());
//		result.append("'");
//
//		return result.toString();
//	}

//	private QueryObject buildAcceptOrderCriteria() {
//		QueryObject criteria = new QueryObject(AcceptRequestDo.class, ActionEvent.QUERY);
//		criteria.setQueryString(searchExpression);
//		return criteria;
//	}
	
//	@SuppressWarnings("unchecked")
//	public List<AcceptRequestDo> queryAndGetAcceptRequests() {
//		IDataManager dataManager = session.getDataManager();
//		List<AcceptRequestDo> result = dataManager.query(criteria);
//		if (result == null) {
//			result = new ArrayList<AcceptRequestDo>();
//		}
//		return result;
//	}
	
	public void updateAcceptRequest(){
//		if (!acceptRequestsList.isEmpty()) {
			AcceptRequestDo acceptRequest = new AcceptRequestDo();
			acceptRequest.setStatus(contract.getStatus());
			acceptRequest.setRequestNumber(contract.getActivityId());
			acceptRequest.setComment(contract.getComment());
			
			acceptRequest.setUpdateState(EntityState.UPDATED);
			session.getDataManager().update(acceptRequest);
//		}		
	}

}
