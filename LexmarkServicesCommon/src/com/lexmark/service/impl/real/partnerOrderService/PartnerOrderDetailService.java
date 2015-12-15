package com.lexmark.service.impl.real.partnerOrderService;

import static com.lexmark.service.impl.real.partnerOrderService.AmindPartnerOrderDetailConversionUtil.toOrder;
import static com.lexmark.util.LangUtil.isEmpty;
import static java.lang.String.format;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.logging.log4j.LogManager;

import com.amind.session.Session;
import com.lexmark.contract.source.OrderDetailContract;
import com.lexmark.domain.Order;
import com.lexmark.result.source.OrderDetailResult;
import com.lexmark.service.impl.real.domain.SupplyRequestDetailDo;
import com.lexmark.service.impl.real.domain.SupplyRequestDetailOrderItemDo;
import com.lexmark.util.LangUtil;
import org.apache.logging.log4j.Logger;

public class PartnerOrderDetailService {

	//protected static final Log logger = LogFactory.getLog(PartnerOrderDetailService.class);
	protected static final Logger logger = LogManager.getLogger(PartnerOrderDetailService.class);

	private String searchExpression;
	private Session session;
	private OrderDetailContract contract;

	public PartnerOrderDetailService(OrderDetailContract contract) {
		if (contract == null) {
			throw new IllegalArgumentException("contract can not be null!");
		}
		this.contract = contract;
	}

	public void checkRequiredFields() {
        if (isEmpty(contract.getRequestNumber())) {
            throw new IllegalArgumentException("requestNumber is empty or null!");
        }		
        if (isEmpty(contract.getOrderNumber())) {
            throw new IllegalArgumentException("orderNumber is empty or null!");
        }		
	}

	public void buildSearchExpression() {
		searchExpression = format("[serviceRequestNumber]='%s'", contract.getRequestNumber());
	}

	public OrderDetailResult  queryAndGetResult() {
		List<SupplyRequestDetailDo> queryResult =  queryBySearchExpression(SupplyRequestDetailDo.class, searchExpression);
		OrderDetailResult result = new OrderDetailResult();
        result.setOrder(matchOrder(queryResult));
        return result;
	}

    private Order matchOrder(List<SupplyRequestDetailDo> detailDos) {
    	for (SupplyRequestDetailDo supplyRequestDetailDo : LangUtil.notNull(detailDos)) {
            for (SupplyRequestDetailOrderItemDo orderItemDo : LangUtil.notNull(supplyRequestDetailDo.getOrderItems())) {
               if (LangUtil.equal(contract.getOrderNumber(), orderItemDo.getOrderNumber())) {
            	    return toOrder(supplyRequestDetailDo, orderItemDo, contract);
               }
            }
        }
        return new Order();
    }

    

    public Session getSession() {
		if (session == null) {
			throw new IllegalStateException("session has not initialized!");
		}
		return session;
	}

	public void setSession(Session session) {
		if (session == null) {
			throw new IllegalArgumentException("session can not be null!");
		}
		this.session = session;
	}
	
	@SuppressWarnings("unchecked")
    private <T> List<T> queryBySearchExpression(Class<T> class1, String searchExpression2) {
		return getSession().getDataManager().queryBySearchExpression(SupplyRequestDetailDo.class, searchExpression);
    }
}
