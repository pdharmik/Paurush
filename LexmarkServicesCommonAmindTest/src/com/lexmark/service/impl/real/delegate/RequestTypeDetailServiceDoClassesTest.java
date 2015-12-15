package com.lexmark.service.impl.real.delegate;

import org.junit.Test;

import com.lexmark.service.impl.real.AmindSiebelQueryTest;
import com.lexmark.service.impl.real.domain.SupplyRequestDetailActivityDo;
import com.lexmark.service.impl.real.domain.SupplyRequestDetailAttachmentDo;
import com.lexmark.service.impl.real.domain.SupplyRequestDetailDo;
import com.lexmark.service.impl.real.domain.SupplyRequestDetailPendingShipmentDo;
import com.lexmark.service.impl.real.domain.SupplyRequestDetailShipmentDo;
import com.lexmark.service.impl.real.domain.SupplyRequestDetailShipmentItemDo;
import com.lexmark.service.impl.real.domain.SupplyRequestDetailShipmentItemOrderEntryDo;

/**
 * @author vpetruchok
 * @version 1.0, 2012-07-03
 */
public class RequestTypeDetailServiceDoClassesTest extends AmindSiebelQueryTest {
    
    @Test
    public void querySupplyRequestDetailDo() throws Exception {
        sampleSiebelQuery(SupplyRequestDetailDo.class, 10, 0);
    }
    
    @Test
    public void querySupplyRequestDetailActivityDo() throws Exception {
        sampleSiebelQuery(SupplyRequestDetailActivityDo.class, 10, 0);
    }
    
    @Test
    public void querySupplyRequestDetailAttachmentDo() throws Exception {
        sampleSiebelQuery(SupplyRequestDetailAttachmentDo.class, 10, 0);
    }
    
    @Test
    public void querySupplyRequestDetailPendingShipmentDo() throws Exception {
//        sampleSiebelQuery(SupplyRequestDetailOrderLineItemDo.class, 10, 0);
        sampleSiebelQuery(SupplyRequestDetailPendingShipmentDo.class, 10, 0);
    }
    
    @Test
    public void querySupplyRequestDetailShipmentDo() throws Exception {
        sampleSiebelQuery(SupplyRequestDetailShipmentDo.class, 10, 0);
    }
    
    @Test
    public void querySupplyRequestDetailPendingShipmentItemDo() throws Exception {
        sampleSiebelQuery(SupplyRequestDetailShipmentItemDo.class, 10, 0);
    }
    
    @Test
    public void querySupplyRequestDetailShipmentItemOrderEntryDo() throws Exception {
//        sampleSiebelQuery(SupplyRequestDetailPendingShipmentItemOrderEntryDo.class, 10, 0);
        sampleSiebelQuery(SupplyRequestDetailShipmentItemOrderEntryDo.class, 10, 0);
    }
}
