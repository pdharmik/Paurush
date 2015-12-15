package com.lexmark.service.impl.real.partnerOrderService;

import static com.lexmark.service.impl.real.MiscTest.str;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.lexmark.contract.source.OrderDetailContract;
import com.lexmark.domain.Order;
import com.lexmark.domain.ServiceRequestOrderLineItem;
import com.lexmark.result.source.OrderDetailResult;
import com.lexmark.service.impl.real.MiscTest;
import com.lexmark.service.impl.real.domain.SupplyRequestDetailDo;
import com.lexmark.service.impl.real.domain.SupplyRequestDetailDoTest;
import com.lexmark.service.impl.real.domain.SupplyRequestDetailOrderItemDo;
import com.lexmark.util.LangUtil;


/**
 * 
 * @see com.lexmark.service.impl.real.AmindPartnerOrderServiceTestSuite
 * 
 * @author vpetruchok
 * @version 1.0, 2012-07-19
 */
public class PartnerOrderDetailServiceTest {
    
    
    @Test
    public void testCheckFields() throws Exception {
        new SupplyRequestDetailDoTest().testCheckFields();  // do-supplyrequestdetail-mapping.xml
    }
    
    @Test
    public void querySupplyRequestDetailDo() throws Exception {
        MiscTest.sampleSiebelQuery(SupplyRequestDetailDo.class, 5);
    }
    
    @Test
    public void querySupplyRequestDetailDo_by_ServiceRequestNumber() throws Exception {
//        MiscTest.sampleSiebelQuery(SupplyRequestDetailDo.class, "[serviceRequestNumber]='1-33783029'", 5);
        MiscTest.sampleSiebelQuery(SupplyRequestDetailDo.class, "[serviceRequestNumber] <> ''", 5);
    }
    
    @Test
    public void querySupplyRequestDetailDo_by_ServiceRequestNumber_defect663() throws Exception {
//        MiscTest.sampleSiebelQuery(SupplyRequestDetailDo.class, "[serviceRequestNumber]='1-33783029'", 5);
        SupplyRequestDetailDo detailDo = LangUtil.first(MiscTest.sampleSiebelQuery(SupplyRequestDetailDo.class, "[serviceRequestNumber] = '1-8884760071'", 5));
        System.out.println("status = " + detailDo.getStatus());
        System.out.println("ProductLine = " + detailDo.getProductLine());
        System.out.println("ProductTLI = " + detailDo.getProductTLI());
        System.out.println("CustomerPONumber = " + detailDo.getCustomerPONumber());
    }
    
    @Test
    public void querySupplyRequestDetailOrderItemDo() throws Exception {
        MiscTest.sampleSiebelQuery(SupplyRequestDetailOrderItemDo.class, 5);
    }

    @Test
    public void testQueryAndGetResult() throws Exception {
        try {
            OrderDetailContract contract = new OrderDetailContract();
            contract.setRequestNumber("1-9145635713");
            contract.setOrderNumber("1-9147806629");
            List<String> vendorIds = new ArrayList<String>();
            vendorIds.add("1-EZU50M");
            contract.setVendorAccountIds(vendorIds);
            PartnerOrderDetailService srv = new PartnerOrderDetailService(contract);
            srv.setSession(MiscTest.statelessSession());
            srv.checkRequiredFields();
            srv.buildSearchExpression();

            OrderDetailResult result = srv.queryAndGetResult();
            print(result);
        } finally {
            MiscTest.releaseAllStatelessSessions();
        }
    }
    
    
    @Test
    public void testQueryAndGetResult_QA_processedParts() throws Exception {
        try {
            OrderDetailContract contract = new OrderDetailContract();
            contract.setRequestNumber("1-8819446578");
            contract.setOrderNumber("1-8819483262");
           
            PartnerOrderDetailService srv = new PartnerOrderDetailService(contract);
            srv.setSession(MiscTest.statelessSession());
            srv.checkRequiredFields();
            srv.buildSearchExpression();
            OrderDetailResult result = srv.queryAndGetResult();
//            print(result);
//            MiscTest.print("pendingShipments: ", result.getOrder().getPendingShipments());
            MiscTest.print("processedParts: ", result.getOrder().getProcessedParts());            
            
        } finally {
            MiscTest.releaseAllStatelessSessions();
        }
    }
    
    @Test
    public void testQueryAndGetResult_QA_processedParts_2() throws Exception {
        try {
            OrderDetailContract contract = new OrderDetailContract();
            contract.setRequestNumber("1-8847254711");
            contract.setOrderNumber("1-8848451381");
            PartnerOrderDetailService srv = new PartnerOrderDetailService(contract);
            srv.setSession(MiscTest.statelessSession());
            srv.checkRequiredFields();
            srv.buildSearchExpression();
            OrderDetailResult result = srv.queryAndGetResult();
//            print(result);
//            MiscTest.print("pendingShipments: ", result.getOrder().getPendingShipments());
            MiscTest.print("processedParts: ", result.getOrder().getProcessedParts());            
            
        } finally {
            MiscTest.releaseAllStatelessSessions();
        }
    }
    
    @Test
    public void testQueryAndGetResult_QA_pendingShipments_2() throws Exception {
        try {
            OrderDetailContract contract = new OrderDetailContract();
            contract.setRequestNumber("1-8847254711");
            contract.setOrderNumber("1-8848451381");
            PartnerOrderDetailService srv = new PartnerOrderDetailService(contract);
            srv.setSession(MiscTest.statelessSession());
            srv.checkRequiredFields();
            srv.buildSearchExpression();
            OrderDetailResult result = srv.queryAndGetResult();
//            print(result);
            MiscTest.print("pendingShipments: ", result.getOrder().getPendingShipments());
//            MiscTest.print("processedParts: ", result.getOrder().getProcessedParts());            
            
        } finally {
            MiscTest.releaseAllStatelessSessions();
        }
    }
    
    
    @Test
    public void testQueryAndGetResult_QA_pendingShipmentVendorId() throws Exception {
        try {
            OrderDetailContract contract = new OrderDetailContract();
            contract.setRequestNumber("1-8847254711");
            contract.setOrderNumber("1-8848451381");

            PartnerOrderDetailService srv = new PartnerOrderDetailService(contract);
            srv.setSession(MiscTest.statelessSession());
            srv.checkRequiredFields();
            srv.buildSearchExpression();

            OrderDetailResult result = srv.queryAndGetResult();
            print(result);
            for (ServiceRequestOrderLineItem item : result.getOrder().getPendingShipments()) {
                System.out.println("pendingShipment.vendorId = " + item.getVendorId());
            }
            
            for (ServiceRequestOrderLineItem item : result.getOrder().getProcessedParts()) {
                System.out.println("processedParts.vendorId = " + item.getVendorId());
            }
//            MiscTest.print("pendingShipments: ", result.getOrder().getPendingShipments());
//            MiscTest.print("processedParts: ", result.getOrder().getProcessedParts());
        } finally {
            MiscTest.releaseAllStatelessSessions();
        }
    }
    
    
    @Test
    public void querySupplyRequestDetailDo_QA() throws Exception {
//        MiscTest.sampleSiebelQuery(SupplyRequestDetailDo.class, "[serviceRequestNumber]='1-33783029'", 5);
        List<SupplyRequestDetailDo> list = (List<SupplyRequestDetailDo>) MiscTest.sampleSiebelQuery(SupplyRequestDetailDo.class, "[serviceRequestNumber] = '1-8819446578'", 5);
        for (SupplyRequestDetailDo detailDo : list) {
//            System.out.println("" + detailDo.getOrderItems());
            for (SupplyRequestDetailOrderItemDo itemDo : detailDo.getOrderItems()) {
               System.out.println("itemDo.getPendingShipments()="  + itemDo.getPendingShipments()); 
               System.out.println("itemDo.getShippedItems()="  + itemDo.getShippedItems()); 
            }
//            System.out.println("" + detailDo.getOrderItems());
        }
        
    }
    
    
    

    private void print(OrderDetailResult result) {
       if (result == null) {
           System.out.println("result = null");
           return;
       } 
       Order order = result.getOrder();
       
       if (order == null) {
           System.out.println("result.order = null");
           return;
       }
       
       System.out.println("vendorId = " + order.getVendorId());
       System.out.println("status = " + order.getStatus());
       System.out.println("contactMethod = " + order.getContactMethod());
       System.out.println("orderNumber = " + order.getOrderNumber());
       System.out.println("requestedDeliveryDate = " + order.getRequestedDeliveryDate());
       System.out.println("customerAccount = " + order.getCustomerAccount());
       System.out.println("orderDate = " + order.getOrderDate());
       System.out.println("customerContact = " + order.getCustomerContact());
       System.out.println("responseMetric = " + order.getResponseMetric());
       System.out.println("customerRequestedResponseDate = " + order.getCustomerRequestedResponseDate());
       System.out.println("serviceProviderReferenceNumber = " + order.getServiceProviderReferenceNumber());
       System.out.println("requestNumber = " + order.getRequestNumber());
       System.out.println("serviceRequestNumber = " + order.getServiceRequestNumber());
       System.out.println("asset = " + str(order.getAsset()));
       System.out.println("customerAddress = " + str(order.getCustomerAddress()));
//       System.out.println(" = " + order.getPendingShipments());
//       System.out.println(" = " + order.getProcessedParts());
       System.out.println("id = " + order.getId());
//       MiscTest.showStructure(order);
       
       
//       System.out.println("result.order = " + MiscTest.str(result.getOrder()));  
    }

    @Test
    public void testFirstContainingOrderNumber() throws Exception {
        String orderNumber = "a11";
        
        SupplyRequestDetailDo detailDo1 = new SupplyRequestDetailDo();
        SupplyRequestDetailDo detailDo2 = new SupplyRequestDetailDo();
        
        SupplyRequestDetailOrderItemDo itemDo1 = new SupplyRequestDetailOrderItemDo();
        SupplyRequestDetailOrderItemDo itemDo2 = new SupplyRequestDetailOrderItemDo();
        SupplyRequestDetailOrderItemDo itemDo3 = new SupplyRequestDetailOrderItemDo();
        itemDo3.setOrderNumber(orderNumber);
        
        ArrayList<SupplyRequestDetailOrderItemDo> orderItems1 = new ArrayList<SupplyRequestDetailOrderItemDo>();
        orderItems1.add(itemDo1);
        orderItems1.add(itemDo2);
        
        ArrayList<SupplyRequestDetailOrderItemDo> orderItems2 = new ArrayList<SupplyRequestDetailOrderItemDo>();
        orderItems2.add(itemDo1);
        orderItems2.add(itemDo2);
        orderItems2.add(itemDo3);
        
        detailDo1.setOrderItems(orderItems1);
        detailDo2.setOrderItems(orderItems2);
        
//        assertEquals(detailDo2, firstContainingOrderNumber(Arrays.asList(detailDo1, detailDo2), orderNumber));
    }
    
    @Test
    public void testQueryAndGetResult_QA_Defect3089() throws Exception {
        try {
            OrderDetailContract contract = new OrderDetailContract();
            contract.setOrderNumber("1-9586419020");
            contract.setRequestNumber("1-9585474791");
            contract.setVendorAccountIds(Arrays.asList(
                    "1-EZU50M" ,
                    "1-1BFFTAF"
                    ));
            
            PartnerOrderDetailService srv = new PartnerOrderDetailService(contract);
            srv.setSession(MiscTest.statelessSession());
            srv.checkRequiredFields();
            srv.buildSearchExpression();
            OrderDetailResult result = srv.queryAndGetResult();
//            print(result);
            MiscTest.print("pendingShipments: ", result.getOrder().getPendingShipments(),
                    "orderedDate", "partnumber", "partName", "partType", "quantity", "status");
            
        } finally {
            MiscTest.releaseAllStatelessSessions();
        }
    }
        
    
}
