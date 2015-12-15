package com.lexmark.result;

import java.io.Serializable;
import java.util.List;

import com.lexmark.domain.MassUploadTemplateOrderLineItem;

public class MassUploadTemplateResult implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<MassUploadTemplateOrderLineItem> orderLineItemsList;

	public List<MassUploadTemplateOrderLineItem> getOrderLineItemsList() {
		return orderLineItemsList;
	}

	public void setOrderLineItemsList(List<MassUploadTemplateOrderLineItem> orderLineItemsList) {
		this.orderLineItemsList = orderLineItemsList;
	}
	
}
