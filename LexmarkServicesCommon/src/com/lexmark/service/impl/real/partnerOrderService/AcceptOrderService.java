package com.lexmark.service.impl.real.partnerOrderService;

import java.util.ArrayList;
import java.util.List;

import com.amind.common.domain.EntityState;
import com.amind.data.service.ActionEvent;
import com.amind.data.service.IDataManager;
import com.amind.data.service.QueryObject;
import com.amind.session.Session;
import com.lexmark.contract.source.OrderAcceptContract;
import com.lexmark.service.impl.real.domain.AcceptOrderDo;
import com.lexmark.service.impl.real.domain.AcceptOrderLineItemDo;
import com.lexmark.util.LangUtil;

public class AcceptOrderService {
	private Session session;
	private OrderAcceptContract contract;
	private String searchExpression;
	private QueryObject criteria;

	public AcceptOrderService(Session session, OrderAcceptContract contract) {
		this.session = session;
		this.contract = contract;
	}

	public void buildSearchExpression() {
		searchExpression = buildAcceptOrderSearchExpression();
		criteria = buildAcceptOrderCriteria();
	}

	private String buildAcceptOrderSearchExpression() {
		StringBuilder result = new StringBuilder();
		result.append("[id] = '");
		result.append(contract.getOrderId());
		result.append("'");

		return result.toString();
	}

	private QueryObject buildAcceptOrderCriteria() {
		QueryObject criteria = new QueryObject(AcceptOrderDo.class,
				ActionEvent.QUERY);
		criteria.setQueryString(searchExpression);
		return criteria;
	}

	@SuppressWarnings("unchecked")
	public List<AcceptOrderDo> queryAndGetAcceptOrders() {
		IDataManager dataManager = session.getDataManager();
		List<AcceptOrderDo> result = dataManager.query(criteria);
		if (result == null) {
			result = new ArrayList<AcceptOrderDo>();
		}
		return result;
	}
	public void updateAcceptOrders(List<AcceptOrderDo> acceptOrdersList){
		if (!acceptOrdersList.isEmpty()) {
			AcceptOrderDo acceptOrder = acceptOrdersList.get(0);
			List<String> contractVendorIds = contract.getVendorIds();
		
			if (contractVendorIds != null) {
				String lineItemVendorId = null;
		
				for (AcceptOrderLineItemDo lineItem : LangUtil.notNull(acceptOrder.getOrderLineItems())) {
					for (String vendorId : contractVendorIds) {
						if ((lineItemVendorId = lineItem.getVendorId()) != null
								&& lineItemVendorId.equalsIgnoreCase(vendorId)) {
							lineItem.setStatus(contract.getStatus());
							lineItem.setId(lineItem.getId());
							lineItem.setOrderHeaderId(lineItem.getOrderHeaderId());
							lineItem.setUpdateState(EntityState.UPDATED);
						}
					}

				}
			}
			acceptOrder.setUpdateState(EntityState.UPDATED);
			session.getDataManager().update(acceptOrder);
		}		
	}

}
