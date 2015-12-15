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
public class OrderServiceTest extends AmindServiceTest {
    
    static OrderServiceList orderService;
    static Session session;

    @BeforeClass
    public static void setUp() throws Exception {
        session = statefulSessionFactory.attachSession();
        orderService = new OrderServiceList(session, crmSessionHandle); 
    }
    
    @AfterClass
    public  static void tearDown() throws Exception {
        session.release(); 
    }
    
    @Test
    public void testRetrieveOrderList() {
        OrderListContract contract =  new OrderListContract();
        contract.setNewQueryIndicator(true);
        contract.setMdmLevel("Legal");
        contract.setMdmId("86006");
        Map<String,Object> filterCriteria = new HashMap<String,Object>();
        filterCriteria.put("orderNumber", "1-9916833703");
        contract.setFilterCriteria(filterCriteria);
        OrderListResult result = orderService.retrieveOrderList(contract);
        print(result);
    }
    
    @Test
    public void queryPartnerRequestOrderDo() throws Exception {
//        MiscTest.sampleSiebelQuery(PartnerRequestOrderDo.class, "EXISTS([LXK MPS Global Account #] = '4537264' AND [LXK MPS Account Level] = 'Siebel' AND [LXK SD LP Contact Method] = 'Portal')", 5);
        MiscTest.sampleSiebelQuery(PartnerRequestOrderDo.class, "EXISTS ([LXK MPS Global Account #] <> '' AND [LXK MPS Account Level] = 'Siebel'  AND [LXK SD LP Contact Method] = 'eServices')", 5);
    }
    
    @Test
    public void testRetrieveOrderList_withStatus() {
        OrderListContract contract =  new OrderListContract();
//        contract.setNewQueryIndicator(true);
        contract.setMdmLevel("Global");
        contract.setMdmId("4537264");
        contract.setStatus("Open");
        OrderListResult result = orderService.retrieveOrderList(contract);
        print(result);
    } 

    @Test
    public void testRetrieveOrderList_QA() {
        OrderListContract contract =  new OrderListContract();
//        contract.setNewQueryIndicator(true);
        contract.setMdmLevel("Siebel");
        contract.setMdmId("1-1G0U13D");
        contract.setNewQueryIndicator(true);
        OrderListResult result = orderService.retrieveOrderList(contract);
//        print(result);
        System.out.println("result = " + result);
        System.out.println("totalCount=" + result.getTotalCount());
        for (Order order  : result.getOrderList()) {
          //System.out.println("order.getShippingAddress() = " + order.getShippingAddress()); 
           System.out.println("order.getPendingShipments() = " + order.getPendingShipments()); 
           System.out.println("\torder.getProcessedParts() = " + order.getProcessedParts()); 
        }
    }

    @Test
    public void testRetrieveOrderList_QA_defect896() {
        OrderListContract contract =  new OrderListContract();
//        contract.setNewQueryIndicator(true);
        contract.setMdmLevel("Siebel");
        contract.setMdmId("1-1G0U13D");
        OrderListResult result = orderService.retrieveOrderList(contract);
//        Order order = result.getOrderList().get(0);
//        System.out.println("order= " + str(order));
//        System.out.println("order.serviceRequestNumber=" + order.getServiceRequestNumber());
//        System.out.println("order.status=" + order.getStatus());
//        print(result);
        for (Order o : result.getOrderList()) {
            System.out.printf("status=%s, serviceRequestNumber=%s, requestNumber=%s\n", o.getStatus(), o.getServiceRequestNumber(), o.getRequestNumber());
        }
        
    }
    
    @Test
    public void testRetrieveOrderList_QA_defect1091() {
        OrderListContract contract =  new OrderListContract();
//        contract.setNewQueryIndicator(true);
        contract.setMdmLevel("Global"); 
        contract.setMdmId("50901");
        contract.setStatus("closed"); //cause an error
        OrderListResult result = orderService.retrieveOrderList(contract);
        for (Order o : result.getOrderList()) {
            System.out.printf("status=%s, serviceRequestNumber=%s, requestNumber=%s\n", o.getStatus(), o.getServiceRequestNumber(), o.getRequestNumber());
        }
        
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
        for (PartnerRequestOrderDo orderDo : list) {
//           System.out.println("order.getPendingShipments() = " + orderDo.getPendingShipments()); 
//           System.out.println("\torder.getProcessedParts() = " + orderDo.getProcessedParts()); 
        }
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
        MiscTest.sampleSiebelQuery(PartnerRequestOrderDo.class, "[vendorId] <> ''", 5);
        
    }
}
