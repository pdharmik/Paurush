package com.lexmark.service.impl.real.requestService;

import static com.lexmark.service.impl.real.requestService.AmindRequestTypeConversionUtil.isCancelledPartsStatus;
import static com.lexmark.service.impl.real.requestService.AmindRequestTypeConversionUtil.processPendingShipments;
import static com.lexmark.service.impl.real.requestService.AmindRequestTypeConversionUtil.populateServerRequestActivities;
import static com.lexmark.service.impl.real.requestService.AmindRequestTypeConversionUtil.toServiceRequestOrderLineItem;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.Test;

import com.lexmark.domain.ServiceRequest;
import com.lexmark.domain.ServiceRequestOrderLineItem;
import com.lexmark.service.impl.real.MiscTest;
import com.lexmark.service.impl.real.domain.SupplyRequestDetailDo;
import com.lexmark.service.impl.real.domain.SupplyRequestDetailOrderItemDo;
import com.lexmark.service.impl.real.domain.SupplyRequestDetailPendingShipmentDo;
import com.lexmark.service.impl.real.domain.SupplyRequestDetailShipmentDo;
import com.lexmark.service.impl.real.domain.SupplyRequestDetailShipmentItemDo;

/**
 * @author vpetruchok
 * @version 1.0, 2012-07-10
 */
public class AmindRequestTypeConversionUtilTest {

    @Test
    public void testToServiceRequestOrderLineItem() throws Exception {
        {
            SupplyRequestDetailShipmentDo detailDo = new SupplyRequestDetailShipmentDo();
            toServiceRequestOrderLineItem(detailDo);
        }

        {
            SupplyRequestDetailShipmentDo detailDo = new SupplyRequestDetailShipmentDo();
            SupplyRequestDetailShipmentItemDo itemDo = new SupplyRequestDetailShipmentItemDo();
            itemDo.setTrackingNumber("1");
            SupplyRequestDetailShipmentItemDo itemDo2 = new SupplyRequestDetailShipmentItemDo();
            itemDo2.setTrackingNumber("2");
            
            detailDo.setShipmentLineItems(MiscTest.newArrayList(itemDo, itemDo2));
            List<ServiceRequestOrderLineItem> list = toServiceRequestOrderLineItem(detailDo);
            assertEquals(2, list.size());
        }

    }

    @Test
    public void testProcessPendingShipments() throws Exception {
        ServiceRequest request = new ServiceRequest();
        SupplyRequestDetailDo requestDetailDo = new SupplyRequestDetailDo();
        requestDetailDo.setOrderItems(null);
        processPendingShipments(request, requestDetailDo);
        assertEquals(0, request.getPendingShipments().size());
        assertEquals(0, request.getCancelledParts().size());

        SupplyRequestDetailPendingShipmentDo shipmentDo1 = new SupplyRequestDetailPendingShipmentDo();
        shipmentDo1.setStatus("cancelled");
        SupplyRequestDetailPendingShipmentDo shipmentDo2 = new SupplyRequestDetailPendingShipmentDo();
        SupplyRequestDetailPendingShipmentDo shipmentDo3 = new SupplyRequestDetailPendingShipmentDo();
        shipmentDo3.setStatus("cancelled");
        SupplyRequestDetailOrderItemDo orderItemDo = new SupplyRequestDetailOrderItemDo();
        orderItemDo.setPendingShipments(MiscTest.newArrayList(shipmentDo1, shipmentDo2, shipmentDo3));

        requestDetailDo.setOrderItems(MiscTest.newArrayList(orderItemDo));
        processPendingShipments(request, requestDetailDo);
        assertEquals(1, request.getPendingShipments().size());
        assertEquals(2, request.getCancelledParts().size());
    }

    @Test
    public void testPopulateServerRequestActivities() throws Exception {
        ServiceRequest request = new ServiceRequest();
        SupplyRequestDetailDo requestDetailDo = new SupplyRequestDetailDo();
        requestDetailDo.setOrderItems(null);
        requestDetailDo.setActions(null);
        populateServerRequestActivities(request, requestDetailDo);
    }

    @Test
    public void testIsCancelledPartsStatus() throws Exception {
        assertEquals(false, isCancelledPartsStatus(""));
        assertEquals(false, isCancelledPartsStatus("1"));
        assertEquals(true, isCancelledPartsStatus("Cancelled – Customer"));
        assertEquals(true, isCancelledPartsStatus("Cancelled – Authorization Rule"));
    }
}
