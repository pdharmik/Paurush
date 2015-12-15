package com.lexmark.service.impl.real.domain;

import java.io.Serializable;
import java.util.ArrayList;

import com.amind.common.domain.BaseEntity;

/**
 * The mapping file :  do-supplyrequestdetailorderlineshipmentitem-mapping.xml 
 * 
 * @author vpetruchok
 * @version 1.0, 2012-08-10
 */
public class SupplyRequestDetailOrderLineShipmentItemDo extends SupplyRequestDetailShipmentItemDo implements Serializable {

    private static final long serialVersionUID = 1L;
	private ArrayList<SupplyRequestDetailOrderLineShipmentItemOrderEntryDo> shipmentOrderLineItems;
	
	public ArrayList<SupplyRequestDetailOrderLineShipmentItemOrderEntryDo> getShipmentOrderLineItems() {
		return shipmentOrderLineItems;
	}
	public void setShipmentOrderLineItems(
			ArrayList<SupplyRequestDetailOrderLineShipmentItemOrderEntryDo> shipmentOrderLineItems) {
		this.shipmentOrderLineItems = shipmentOrderLineItems;
	}
	


}
