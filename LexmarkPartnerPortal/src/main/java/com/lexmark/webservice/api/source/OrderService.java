package com.lexmark.webservice.api.source;

import com.lexmark.contract.source.OrderUpdateContract;
import com.lexmark.result.source.OrderUpdateResult;;

public interface OrderService {
	public OrderUpdateResult updateOrder(OrderUpdateContract contract) throws Exception;	
	
}
