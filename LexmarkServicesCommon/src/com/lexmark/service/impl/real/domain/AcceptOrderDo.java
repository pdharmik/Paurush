package com.lexmark.service.impl.real.domain;

import java.io.Serializable;
import java.util.ArrayList;

import com.amind.common.domain.BaseEntity;

/**
 * 
 * @author vshynkarenko
 * mapping-file: "do-acceptorder-mapping.xml"
 *
 */
public class AcceptOrderDo extends BaseEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	private ArrayList<AcceptOrderLineItemDo>orderLineItems;
	
	public ArrayList<AcceptOrderLineItemDo> getOrderLineItems() {
		return orderLineItems;
	}
	public void setOrderLineItems(ArrayList<AcceptOrderLineItemDo> orderLineItems) {
		this.orderLineItems = orderLineItems;
	}
}
