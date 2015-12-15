package com.lexmark.service.impl.real.partnerOrderService;

import static com.lexmark.service.impl.real.partnerOrderService.OrderServiceList.totalOrderSatus;
import static com.lexmark.util.LangUtil.notNull;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.amind.session.Session;
import com.lexmark.contract.source.OrderListContract;
import com.lexmark.domain.Order;
import com.lexmark.domain.ServiceRequestOrderLineItem;
import com.lexmark.result.source.OrderListResult;
import com.lexmark.service.impl.real.AmindServiceTest;
import com.lexmark.service.impl.real.MiscTest;
import com.lexmark.service.impl.real.domain.OrderLineItemDo;
import com.lexmark.service.impl.real.domain.PartnerRequestOrderDo;

/**
 * 
 * @see com.lexmark.service.impl.real.AmindPartnerOrderServiceTestSuite
 * @see com.lexmark.service.impl.real.domain.PartnerRequestOrderDo
 * 
 * @author vpetruchok
 * @version 1.0, 2012-06-25
 */
public class OrderServiceListTest extends AmindServiceTest {
    
    static OrderServiceList orderService;
    static Session session;

    @BeforeClass
    public static void setUp() throws Exception {
        session = statefulSessionFactory.attachSession();
        orderService = new OrderServiceList(session, session, crmSessionHandle); 
    }
    
    @AfterClass
    public  static void tearDown() throws Exception {
        session.release(); 
    }
    
    @Test
    public void testRetrieveOrderList() throws Exception {
        OrderListContract contract =  new OrderListContract();
//        contract.setNewQueryIndicator(true);
        contract.setMdmLevel("Global");
        contract.setMdmId("4537264");
        OrderListResult result = orderService.retrieveOrderList(contract);
        print(result);
    }
    
    @Test
    public void queryPartnerRequestOrderDo() throws Exception {
       long t0 = System.currentTimeMillis();
       try {
        //       MiscTest.sampleSiebelQuery(PartnerRequestOrderDo.class, "EXISTS ([mdmLevel1AccountId] <> '' OR [mdmLevel2AccountId] <> '' OR [mdmLevel3AccountId] <> '' OR [mdmLevel4AccountId] <> '' OR [mdmLevel5AccountId] <> '')", 5);
//           MiscTest.sampleSiebelQuery(PartnerRequestOrderDo.class, "[serialNumber] <> ''", 5);  
//           MiscTest.sampleSiebelQuery(PartnerRequestOrderDo.class, "[id] = '1-13S6CLL'", 5);  
//           MiscTest.sampleSiebelQuery(PartnerRequestOrderDo.class, "[id] = [id]", 5);  
           MiscTest.sampleSiebelQuery(PartnerRequestOrderDo.class, "1 = 1", 5);  
       } finally {
          System.out.printf("Excec time=%s sec. \n", (System.currentTimeMillis() - t0) / 1000.0);
       }
    }
    
    @Test
    public void queryPartnerRequestOrderDo_QA() throws Exception {
        MiscTest.sampleSiebelQuery(PartnerRequestOrderDo.class, "EXISTS ([LXK MPS Account Id] = '1-1G0U13D' AND [LXK MPS Account Level] = 'Siebel')  AND  ([Status] <> 'Ready for Order' AND [Status] <> 'Pending')" , 5);
    } 
    
    @Test
    public void testRetrieveOrderList_withStatus() throws Exception {
        OrderListContract contract =  new OrderListContract();
//        contract.setNewQueryIndicator(true);
        contract.setMdmLevel("Global");
        contract.setMdmId("4537264");
        contract.setStatus("Open");
        OrderListResult result = orderService.retrieveOrderList(contract);
        print(result);
    } 

    /**
     * @see com.lexmark.service.impl.real.AmindPartnerOrderServiceTest#testRetrieveOrderDetail_QA_2()
     */
    @Test
    public void testRetrieveOrderList_QA() throws Exception {
        OrderListContract contract =  new OrderListContract();
//        contract.setNewQueryIndicator(true);
        contract.setMdmLevel("Siebel");
        contract.setMdmId("1-1G0U13D");
        OrderListResult result = orderService.retrieveOrderList(contract);
//        print(result);
        System.out.println("result = " + result);
        System.out.println("totalCount=" + result.getTotalCount());
        for (Order order  : result.getOrderList()) {
            System.out.println("order.vendorId = " + order.getVendorId());
            System.out.println("order.orderNumber = " + order.getOrderNumber());
            System.out.println("order.requestNumber = " + order.getRequestNumber());
            MiscTest.print("\t[pendingShipments] ", order.getPendingShipments());
           System.out.println("\n ---> order.getProcessedParts() = " + order.getProcessedParts()); 
        }
    }
    
    @Test
    public void testRetrieveOrderList_QA2() throws Exception {
        OrderListContract contract =  new OrderListContract();
        contract.setMdmLevel("Siebel");
        contract.setMdmId("1-1G0U13D");
        OrderListResult result = orderService.retrieveOrderList(contract);
        MiscTest.print(result.getOrderList(), "vendorId", "orderNumber", "shippingAddress", "customerContact.contactId");
    }

    @Test
    public void testRetrieveOrderList_QA_defect896() throws Exception {
        OrderListContract contract =  new OrderListContract();
//        contract.setNewQueryIndicator(true);
        contract.setMdmLevel("Siebel");
        contract.setMdmId("1-1G0U13D");
        OrderListResult result = orderService.retrieveOrderList(contract);
        for (Order o : result.getOrderList()) {
            System.out.printf("status=%s, serviceRequestNumber=%s, requestNumber=%s\n", o.getStatus(), o.getServiceRequestNumber(), o.getRequestNumber());
        }
        
    }
    
    @Test
    public void testRetrieveOrderList_QA_defect1091() throws Exception {
        OrderListContract contract =  new OrderListContract();
//        contract.setNewQueryIndicator(true);
        contract.setMdmLevel("Global"); 
        contract.setMdmId("50901");
        contract.setStatus("closed"); 
        OrderListResult result = orderService.retrieveOrderList(contract);
        for (Order o : result.getOrderList()) {
            System.out.printf("status=%s, serviceRequestNumber=%s, requestNumber=%s\n", o.getStatus(), o.getServiceRequestNumber(), o.getRequestNumber());
        }
        
    }
    
    @Test
    public void testRetrieveOrderList_QA_defect12499() throws Exception {
    	OrderListContract contract =  new OrderListContract();
    	contract.setMdmLevel("Legal"); 
    	contract.setMdmId("2809");
    	contract.setStatus("Open");
    	contract.setIncrement(40);
    	contract.setStartRecordNumber(0);
    	contract.setNewQueryIndicator(true);
    	Map<String, Object> filterCriteria = new HashMap<String, Object>();
    	filterCriteria.put("order.startDate", "02/23/2014 09:00:00");
    	filterCriteria.put("order.endDate", "03/26/2014 08:00:00");
		contract.setFilterCriteria(filterCriteria );
    	OrderListResult result = orderService.retrieveOrderList(contract);
    	for (Order o : result.getOrderList()) {
    		System.out.printf("status=%s, serviceRequestNumber=%s, requestNumber=%s\n", o.getStatus(), o.getServiceRequestNumber(), o.getRequestNumber());
    	}
    	
    }
    
    @Test
    public void testRetrieveOrderList_defect12499()  throws Exception {
     OrderListContract contract = new OrderListContract();
     contract.setStatus("Open");
     contract.setMdmLevel("Legal");
     contract.setMdmId("2809");
     contract.setEmployeeFlag(false);
     contract.setPendingDebriefFlag(false);
     contract.setNewQueryIndicator(true);
     contract.setStartRecordNumber(0);
     contract.setIncrement(40);
     contract.setSessionHandle(crmSessionHandle);
     
     Map<String,Object> filterCriteria = new HashMap<String, Object>();
     filterCriteria.put("order.startDate", "02/23/2014 09:00:00");
     filterCriteria.put("order.endDate", "03/26/2014 08:00:00");
     contract.setFilterCriteria(filterCriteria);
     
     Map<String,Object> sortCriteria = new HashMap<String, Object>();
     sortCriteria.put("createdDate", "DESCENDING");
     contract.setSortCriteria(sortCriteria);
     
     OrderListResult activityResult = orderService.retrieveOrderList(contract);
     
     List<Order> orderList = activityResult.getOrderList();
     int totalCount = activityResult.getTotalCount();
     
     System.out.println(orderList.size());
     System.out.println(totalCount);
     
     for (Order order : orderList) {
      System.out.println("Status: " + order.getStatus());
      System.out.println("Fulfillment Status: " + order.getFulfillmentStatus());
      System.out.println("Pending shipments size: " + order.getPendingShipments().size());
     }
     System.out.println("End");
    }
    
    /**
     * OrderLineItemDo / do-orderlineitem-mapping.xml
     */
    @SuppressWarnings("unchecked")
    @Test
    public void queryPartnerRequestOrder_QA_defect896() throws Exception {
        String query = "EXISTS ([LXK MPS Account Id] = '1-1G0U13D' AND [LXK MPS Account Level] = 'Siebel'  AND [LXK SD LP Contact Method] = 'eServices')";
        List<PartnerRequestOrderDo> list = (List<PartnerRequestOrderDo>) MiscTest.querySiebel(PartnerRequestOrderDo.class, query);
        System.out.println("list.size() = " + list.size());
    }
    
    
    /**
     * OrderLineItemDo / do-orderlineitem-mapping.xml
     */
    @SuppressWarnings("unchecked")
    @Test
    public void queryPartnerRequestOrder_QA() throws Exception {
        String query = "EXISTS ([LXK MPS Account Id] = '1-1G0U13D' AND [LXK MPS Account Level] = 'Siebel'  AND [LXK SD LP Contact Method] = 'eServices')";
        List<PartnerRequestOrderDo> list = (List<PartnerRequestOrderDo>) MiscTest.querySiebel(PartnerRequestOrderDo.class, query);
        System.out.println("list.size() = " + list.size());
        for (PartnerRequestOrderDo orderDo : list) {
            for (OrderLineItemDo lineItemDo : notNull(orderDo.getOrderLineItems())) {
                System.out.printf("order.orderNumber=%s, order.id=%s, orderLineItem.id=%s, orderLineItem.status=%s, \n", 
                        orderDo.getOrderNumber(), orderDo.getId(), lineItemDo.getId(), lineItemDo.getStatus());
            }
        }
    }
    
    
    
    @Test(timeout = 1000 * 60 /* 1 min*/)
    public void countRecords_QA() throws Exception { // too long
//        int count = OrderService.countRecords(session, ""); 
        int count = OrderServiceList.countRecords(session, "EXISTS ([LXK MPS Account Id] = '1-1G0U13D' AND [LXK MPS Account Level] = 'Siebel'  AND [LXK SD LP Contact Method] = 'eServices')"); 
        System.out.println("count = " + count);
    }
    

    private void print(OrderListResult result) {
        System.out.println("result = " + result);
        System.out.println("totalCount=" + result.getTotalCount());
        MiscTest.print(result.getOrderList());
        System.out.println("========= orderLineItems");
//        for (Order order : result.getOrderList()) {
//           MiscTest.print(order.getOrderLineItems()); 
//        }
    }
    
    @Test
    public void testTotalOrderSatus() throws Exception {
        assertEquals("Assigned to LP", totalOrderSatus(Arrays.asList(lineItem("Shipped"), lineItem("Assigned to LP"))));
        assertEquals(null, totalOrderSatus(Arrays.asList(lineItem("Shipped1"), lineItem("Assigned to LP1"))));
        assertEquals("Cancelled", totalOrderSatus(Arrays.asList(lineItem("Shipped1"), lineItem("Cancelled"))));
    }

    private static ServiceRequestOrderLineItem lineItem(String status) {
        ServiceRequestOrderLineItem item = new ServiceRequestOrderLineItem();
        item.setStatus(status);
        return item;
    }
    
    @Test
    public void queryPartnerRequestOrderDoWithVendorId() throws Exception {
        MiscTest.sampleSiebelQuery(PartnerRequestOrderDo.class, "EXISTS([vendorId] <> '')", 5);
        
    }
    
    @SuppressWarnings("unchecked")
    @Test
    public void testRetrieveOrderList_defect2350_QA() throws Exception {
        OrderListContract contract =  new OrderListContract();
        contract.setStartRecordNumber(0);
        contract.setIncrement(40);
        contract.setSortCriteria((Map<String, Object>) MiscTest.newHashMap("orderNumber", "ASCENDING"));
        contract.setFilterCriteria((Map<String, Object>) 
                MiscTest.newHashMap("order.startDate", "04/01/2012 18:30:00",
//                                    "orderNumber", "1-9187103271", 
                                    "order.endDate", "09/03/2012 18:30:00"));
//        contract.setNewQueryIndicator(true);
        contract.setMdmLevel("Global");
        contract.setMdmId("018978783");
        OrderListResult result = orderService.retrieveOrderList(contract);
        MiscTest.print(result.getOrderList());
    }
    
    
    @SuppressWarnings("unchecked")
    @Test
    public void testRetrieveOrderList_QA_totalCount() throws Exception {
        OrderListContract contract =  new OrderListContract();
        contract.setStatus("Show All");
        contract.setStartRecordNumber(0);
        contract.setIncrement(40);
//        contract.setSortCriteria((Map<String, Object>) MiscTest.newHashMap("orderNumber", "ASCENDING"));
        contract.setFilterCriteria((Map<String, Object>) 
                MiscTest.newHashMap("order.startDate", "09/08/2012 13:00:00",
                                    "status", "Delivered", 
                                    "order.endDate", "10/09/2012 13:00:00"));
        contract.setNewQueryIndicator(true);
        contract.setMdmLevel("Global");
        contract.setMdmId("018978783");
        {
            OrderListResult result = orderService.retrieveOrderList(contract);
            MiscTest.print(result.getOrderList());
            System.out.println("totalCount (NewQueryIndicator=true) = " + result.getTotalCount());
        }
        
        {
            contract.setNewQueryIndicator(false);
            contract.setStartRecordNumber(60);
            contract.setIncrement(40);

            OrderListResult result = orderService.retrieveOrderList(contract);
            MiscTest.print(result.getOrderList());
            System.out.println("totalCount (NewQueryIndicator=false) = " + result.getTotalCount());
        }
        
        
    }
    
    @Test
    public void testCountRecords() throws Exception {
        String q = "EXISTS ([LXK MPS Global Account #] = '018978783' AND [LXK MPS Account Level] = 'Siebel' AND [LXK MPS Status] ~LIKE '*Delivered*') AND ([LXK MPS Created] >= '09/08/2012 13:00:00') AND ([LXK MPS Created] <= '10/09/2012 13:00:00') AND ([Status] <> 'Ready for Order' OR [Status] <> 'Pending' OR [Status] <> 'Assignment Failed')";
        int count = OrderServiceList.countRecords(session, q);
        System.out.println(count);
    }
    
    @Test
    public void query() throws Exception {
        MiscTest.sampleSiebelQuery(PartnerRequestOrderDo.class,
                "EXISTS ([LXK MPS Global Account #] = '018978783' AND [LXK MPS Account Level] = 'Siebel' AND [LXK MPS Status] ~LIKE '*Delivered*') AND ([LXK MPS Created] >= '09/08/2012 13:00:00') AND ([LXK MPS Created] <= '10/09/2012 13:00:00') AND ([Status] <> 'Ready for Order' OR [Status] <> 'Pending' OR [Status] <> 'Assignment Failed')"
                , 100) ; 
    }
    
    
    
    @Test
    public void queryPartnerRequestOrderDoByOrderNumber_defect_2350_QA() throws Exception {
//        MiscTest.sampleSiebelQuery(PartnerRequestOrderDo.class, "[orderNumber] = '1-9187103271'", 5, 0);
//        String s = "EXISTS ([LXK MPS Global Account #] = '18429' AND [LXK MPS Account Level] = 'Siebel' )  AND EXISTS (([LXK MPS Status]= 'Assigned to LP' OR  [LXK MPS Status] = 'Order Accepted' OR  [LXK MPS Status] = 'Back Ordered')) AND ([LXK MPS Created] >= '08/11/2012 18:30:00') AND ([LXK MPS Created] <= '09/11/2012 18:30:00')";
        String s = "EXISTS ([LXK MPS Global Account #] = '18429' AND [LXK MPS Account Level] = 'Siebel' )  AND EXISTS (([LXK MPS Status]= 'Assigned to LP' OR  [LXK MPS Status] = 'Order Accepted' OR  [LXK MPS Status] = 'Back Ordered')) AND ([LXK MPS Created] >= '08/11/2011 18:30:00') AND ([LXK MPS Created] <= '09/11/2012 18:30:00')";
        MiscTest.sampleSiebelQuery(PartnerRequestOrderDo.class, s, 5, 0);
         
    }
    
    @SuppressWarnings("unchecked")
    @Test
    public void testRetrieveOrderList_defect2251_QA() throws Exception {
        OrderListContract contract =  new OrderListContract();
        contract.setStartRecordNumber(0);
        contract.setIncrement(40);
        contract.setSortCriteria((Map<String, Object>) MiscTest.newHashMap("createdDate", "DESCENDING"));
        contract.setFilterCriteria((Map<String, Object>) 
                MiscTest.newHashMap("order.startDate", "08/03/2012 13:00:00",
//                                    "orderNumber", "1-9187103271", 
                                     "status", "Cancelled",
                                    "order.endDate", "09/03/2012 13:00:00"));
//        contract.setNewQueryIndicator(true);
        contract.setMdmLevel("Siebel");
        contract.setMdmId("1-1G0U13D");
        OrderListResult result = orderService.retrieveOrderList(contract);
        for (Order o : result.getOrderList()) {
            System.out.printf("[order] status=%s, serviceRequestNumber=%s, requestNumber=%s\n", o.getStatus(), o.getServiceRequestNumber(), o.getRequestNumber());
        }
        for (Order o : result.getOrderList()) {
            for (ServiceRequestOrderLineItem item : o.getPendingShipments()) {
                System.out.printf("[pendingShipment] status=%s\n", item.getStatus());
            }
        }
    }
        
    @SuppressWarnings("unchecked")
    @Test
    public void testRetrieveOrderList_defect3034_QA() throws Exception {
        OrderListContract contract =  new OrderListContract();
        contract.setStartRecordNumber(0);
        contract.setIncrement(40);
        contract.setSortCriteria((Map<String, Object>) MiscTest.newHashMap("createdDate", "DESCENDING"));
//        String status = "Delivered";
//        String status = "Order accepted";
        String status = "Shipped";
//        String status = "Cancelled";
        contract.setFilterCriteria((Map<String, Object>) 
                MiscTest.newHashMap("order.startDate", "08/21/2012 13:00:00",
//                                    "orderNumber", "1-9187103271", 
                                     "status", status,
                                    "order.endDate", "09/20/2012 13:00:00"));
//        contract.setNewQueryIndicator(true);
        contract.setMdmLevel("Siebel");
        contract.setMdmId("1-1G0U13D");
        OrderListResult result = orderService.retrieveOrderList(contract);
        for (Order o : result.getOrderList()) {
            for (ServiceRequestOrderLineItem item : o.getPendingShipments()) {
                System.out.printf("[pendingShipment] status=%s\n", item.getStatus());
            }
        }
        for (Order o : result.getOrderList()) {
            System.out.printf("[order] status=%s, serviceRequestNumber=%s, requestNumber=%s\n", o.getStatus(), o.getServiceRequestNumber(), o.getRequestNumber());
            assertEquals(status, o.getStatus());
        }
    }
    
    
    @SuppressWarnings("unchecked")
    @Test
    public void testRetrieveOrderList_QA_Defect2136() throws Exception {
        OrderListContract contract =  new OrderListContract();
        contract.setStartRecordNumber(0);
        contract.setIncrement(40);
        contract.setSortCriteria((Map<String, Object>) MiscTest.newHashMap("orderNumber", "ASCENDING"));
        contract.setFilterCriteria((Map<String, Object>) 
                MiscTest.newHashMap("order.startDate", "04/01/2012 18:30:00",
//                                    "orderNumber", "1-9187103271", 
                                    "order.endDate", "11/03/2012 18:30:00"));
//        contract.setNewQueryIndicator(true);
        contract.setMdmLevel("Global");
        contract.setMdmId("018978783");
        OrderListResult result = orderService.retrieveOrderList(contract);
        for (Order order : result.getOrderList()) {
            System.out.println("status = " + order.getStatus());
//            MiscTest.print(order.getPendingShipments(), "id", "orderFultillmentStatus", "portalFulfillmentStatus");
        }
    }
    
    
    @Test
    public void testRetrieveOrderList_Production_defect5502() throws Exception {
        OrderListContract contract =  new OrderListContract();
        contract.setStartRecordNumber(0);
        contract.setIncrement(40);
        contract.setNewQueryIndicator(true);
        contract.setMdmLevel("Legal");
        contract.setMdmId("65500");
        OrderListResult result = orderService.retrieveOrderList(contract);
        MiscTest.print(result.getOrderList(), "asset.serialNumber", "asset.machineTypeModel", "asset.productLine" );
        MiscTest.print("totalCount = ", result.getTotalCount());
    }
    
    @Test
    public void testRetrieveOrderList_Production_defect5502_query() {
        List<?> r = MiscTest.sampleSiebelQuery(PartnerRequestOrderDo.class,
                
                    " EXISTS ([LXK MPS Legal Account #] = '65500' AND [LXK MPS Account Level] = 'Siebel' AND ([LXK MPS Status] = 'Routed' OR [LXK MPS Status] = 'Order Accepted'  OR [LXK MPS Status] = 'Shipped' OR [LXK MPS Status] = 'Delivered'  OR [LXK MPS Status] = 'Back Ordered' OR [LXK MPS Status] = 'Cancelled' )) AND ([Status] <> 'Ready for Order' OR [Status] <> 'Pending' OR [Status] <> 'Assignment Failed') AND [LXK MPS SR Flag] ='MPS'"
                + 
                   " AND [serialNumber] <> '' and EXISTS([machineTypeModel] <> '' and [productLine] <> '')"
                  , 10);
        MiscTest.print(r, "serialNumber", "machineTypeModel", "productLine");
    }
    
    @SuppressWarnings("unchecked")
    @Test
    public void testRetrieveOrderList_QA_defect5502() throws Exception {
        OrderListContract contract =  new OrderListContract();
        contract.setStartRecordNumber(0);
        contract.setIncrement(40);
        contract.setSortCriteria((Map<String, Object>) MiscTest.newHashMap("createdDate", "DESCENDING"));
//        String status = "Delivered";
//        String status = "Order accepted";
        String status = "Shipped";
//        String status = "Cancelled";
        contract.setFilterCriteria((Map<String, Object>) 
                MiscTest.newHashMap("order.startDate", "08/21/2012 13:00:00",
//                                    "orderNumber", "1-9187103271", 
                                     "status", status,
                                    "order.endDate", "09/20/2012 13:00:00"));
//        contract.setNewQueryIndicator(true);
        contract.setMdmLevel("Siebel");
        contract.setMdmId("1-1G0U13D");
        OrderListResult result = orderService.retrieveOrderList(contract);
        
        MiscTest.print(result.getOrderList(), "asset.serialNumber", "asset.machineTypeModel", "asset.productLine" );
        MiscTest.print("totalCount = ", result.getTotalCount());
    }
    
    
    
}
