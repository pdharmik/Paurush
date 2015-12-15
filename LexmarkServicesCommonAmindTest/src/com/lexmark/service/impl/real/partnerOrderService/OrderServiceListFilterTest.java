package com.lexmark.service.impl.real.partnerOrderService;

import static com.lexmark.service.impl.real.MiscTest.checkFilterResult;
import static com.lexmark.service.impl.real.MiscTest.toMap;
import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.BeforeClass;
import org.junit.Test;

import com.amind.session.Session;
import com.lexmark.contract.source.OrderListContract;
import com.lexmark.domain.Order;
import com.lexmark.result.source.OrderListResult;
import com.lexmark.service.impl.real.AmindServiceTest;
import com.lexmark.service.impl.real.MiscTest;
import com.lexmark.service.impl.real.domain.PartnerRequestOrderDo;
import com.lexmark.service.impl.real.util.TestErrors;

/**
 * @see com.lexmark.service.impl.real.AmindPartnerOrderServiceTestSuite
 * @see com.lexmark.service.impl.real.partnerRequest.OrderServiceListTest
 * 
 * @author vpetruchok
 * @version 1.0, 2012-07-23
 */
public class OrderServiceListFilterTest extends AmindServiceTest {
    
    static OrderServiceList orderService;
    static Session session;

    @BeforeClass
    public static void setUp() throws Exception {
    	session = statelessSessionFactory.attachSession();
        orderService = new OrderServiceList(session, crmSessionHandle); 
    }

    @Test
    public void testFilter_QA() throws Exception {
        Map<String, Object> filters = new LinkedHashMap<String, Object>();
        filters.put("requestNumber", "SR Number");
        filters.put("orderNumber", "Order Number");
        filters.put("customerAccount", "Account Name");
        filters.put("responseMetric", "LXK MPS Response Time");   
        filters.put("asset.SerialNumber", "LXK MPS Serial Number");                    // MVF , will be join field
        filters.put("asset.ProductLine", "LXK MPS Product Line");                      // MVF
        filters.put("asset.machineTypeModel", "LXK MPS Machine Type Model");           // MVF
        filters.put("serviceProviderReferenceNumber", "LXK MPS Customer Ref Number");  // MVF
        filters.put("customerAddress.StreetAddress", "aa");                            // no mapping
        filters.put("customerAddress.City", "LXK MPS City");                           // MVF, will be join field 
        filters.put("customerAddress.State", "LXK MPS State");                         // MVF, will be join field
        filters.put("customerAddress.Province", "LXK MPS Province");                   // MVF , will be join field
        filters.put("customerAddress.PostalCode", "LXK MPS Postal Code");              // MVF , will be join field 
        filters.put("customerContact.FirstName", "LXK MPS Requestor First Name");
        filters.put("customerContact.LastName", "LXK MPS Requestor Last Name");
        filters.put("order.startDate", "06/01/2012 08:00:00");  
        filters.put("order.endDate", "07/26/2012 00:00:00");   
        filters.put("status", "LXK MPS Status");                                               // MVF  
        
        TestErrors errors  = new TestErrors();
        for (Entry<String, Object> e : filters.entrySet()) {
            Map<String, Object> oneFilter = toMap(e);
            try {
                OrderListResult r = retrieveOrderList_QA(oneFilter);
                for (Order order : r.getOrderList()) {
                    System.out.println("***** checking " + e.getValue());
                    checkFilterResult(oneFilter, "requestNumber",   order.getRequestNumber());
                    checkFilterResult(oneFilter, "orderNumber",     order.getOrderNumber());
                    checkFilterResult(oneFilter, "customerAccount", order.getCustomerAccount());
                    checkFilterResult(oneFilter, "responseMetric",  order.getResponseMetric());
                    checkFilterResult(oneFilter, "asset.SerialNumber", order.getAsset().getSerialNumber());
                    checkFilterResult(oneFilter, "asset.ProductLine", order.getAsset().getProductLine());
                    checkFilterResult(oneFilter, "asset.machineTypeModel", order.getAsset().getMachineTypeModel());
                    checkFilterResult(oneFilter, "serviceProviderReferenceNumber", order.getServiceProviderReferenceNumber());
                    checkFilterResult(oneFilter, "customerAddress.StreetAddress", order.getCustomerAddress().getAddressName()); // ?
                    checkFilterResult(oneFilter, "customerAddress.City", order.getCustomerAddress().getCity());
                    checkFilterResult(oneFilter, "customerAddress.State", order.getCustomerAddress().getState());
                    checkFilterResult(oneFilter, "customerAddress.Province", order.getCustomerAddress().getProvince());
                    checkFilterResult(oneFilter, "customerAddress.PostalCode", order.getCustomerAddress().getPostalCode());
                    checkFilterResult(oneFilter, "customerContact.FirstName", order.getCustomerContact().getFirstName());
                    checkFilterResult(oneFilter, "customerContact.LastName", order.getCustomerContact().getLastName());
                    //                checkFilterResult(filters, "order.startDate", order.get);
                    //                checkFilterResult(filters, "order.endDate", order.get);
                    checkFilterResult(oneFilter, "status", order.getStatus());
                }
            } catch (Throwable ex) {
                errors.add(ex, "" + oneFilter);
            }
        }        
        
        errors.check();
    }
    
    @Test
    public void testFilter_QA_bugs() throws Exception {
        Map<String, Object> filters = new LinkedHashMap<String, Object>();
//        filters.put("responseMetric", "1");   // TODO(Viktor) check
        filters.put("asset.SerialNumber", "LXK MPS Serial Number");                    // MVF , will be join field
        filters.put("asset.ProductLine", "LXK MPS Product Line");                      // MVF
        filters.put("asset.machineTypeModel", "LXK MPS Machine Type Model");           // MVF
//        filters.put("customerAddress.StreetAddress", "aa");                            // does not work 
        TestErrors errors  = new TestErrors();
        for (Entry<String, Object> e : filters.entrySet()) {
            Map<String, Object> oneFilter = toMap(e);
            try {
                OrderListResult r = retrieveOrderList_QA(oneFilter);
                for (Order order : r.getOrderList()) {
                    System.out.println("***** checking " + e.getValue());
                    checkFilterResult(oneFilter, "requestNumber",   order.getRequestNumber());
                    checkFilterResult(oneFilter, "orderNumber",     order.getOrderNumber());
                    checkFilterResult(oneFilter, "customerAccount", order.getCustomerAccount());
                    checkFilterResult(oneFilter, "responseMetric",  order.getResponseMetric());
                    checkFilterResult(oneFilter, "asset.SerialNumber", order.getAsset().getSerialNumber());
                    checkFilterResult(oneFilter, "asset.ProductLine", order.getAsset().getProductLine());
                    checkFilterResult(oneFilter, "asset.machineTypeModel", order.getAsset().getMachineTypeModel());
                    checkFilterResult(oneFilter, "serviceProviderReferenceNumber", order.getServiceProviderReferenceNumber());
                    checkFilterResult(oneFilter, "customerAddress.StreetAddress", order.getCustomerAddress().getAddressName()); // ?
                    checkFilterResult(oneFilter, "customerAddress.City", order.getCustomerAddress().getCity());
                    checkFilterResult(oneFilter, "customerAddress.State", order.getCustomerAddress().getState());
                    checkFilterResult(oneFilter, "customerAddress.Province", order.getCustomerAddress().getProvince());
                    checkFilterResult(oneFilter, "customerAddress.PostalCode", order.getCustomerAddress().getPostalCode());
                    checkFilterResult(oneFilter, "customerContact.FirstName", order.getCustomerContact().getFirstName());
                    checkFilterResult(oneFilter, "customerContact.LastName", order.getCustomerContact().getLastName());
                    //                checkFilterResult(filters, "order.startDate", order.get);
                    //                checkFilterResult(filters, "order.endDate", order.get);
                    checkFilterResult(oneFilter, "status", order.getStatus());
                }
            } catch (Throwable ex) {
                errors.add(ex, "" + oneFilter);
            }
        }        
        
        errors.check();
    }    
    
    
    @Test
    public void testFilter_QA_allFitlersAtOnce() throws Exception {
        Map<String, Object> filters = new LinkedHashMap<String, Object>();
        filters.put("requestNumber", "SR Number");
        filters.put("orderNumber", "Order Number");
        filters.put("customerAccount", "Account Name");
        filters.put("responseMetric", "1"); // TODO(Viktor) check
        filters.put("asset.SerialNumber", "LXK MPS Serial Number"); // MVF , will be join field
        filters.put("asset.ProductLine", "LXK MPS Product Line"); // MVF
        filters.put("asset.machineTypeModel", "LXK MPS Machine Type Model"); // MVF
        filters.put("serviceProviderReferenceNumber", "LXK MPS Customer Ref Number"); // MVF
        filters.put("customerAddress.StreetAddress", ""); // 
        filters.put("customerAddress.City", "LXK MPS City"); // MVF, will be join field 
        filters.put("customerAddress.State", "LXK MPS State"); // MVF, will be join field
        filters.put("customerAddress.Province", "LXK MPS Province"); // MVF , will be join field
        filters.put("customerAddress.PostalCode", "LXK MPS Postal Code"); // MVF , will be join field 
        filters.put("customerContact.FirstName", "LXK MPS Requestor First Name");
        filters.put("customerContact.LastName", "LXK MPS Requestor Last Name");
        filters.put("order.startDate", "07/01/2012 00:00:00");  
        filters.put("order.endDate", "07/26/2012 00:00:00");   
        filters.put("status", "Status"); // MVF  

        OrderListResult r = retrieveOrderList_QA(filters);
        for (Order order : r.getOrderList()) {
            checkFilterResult(filters, "requestNumber", order.getRequestNumber());
            checkFilterResult(filters, "orderNumber", order.getOrderNumber());
            checkFilterResult(filters, "customerAccount", order.getCustomerAccount());
            checkFilterResult(filters, "responseMetric", order.getResponseMetric());
            checkFilterResult(filters, "asset.SerialNumber", order.getAsset().getSerialNumber());
            checkFilterResult(filters, "asset.ProductLine", order.getAsset().getProductLine());
            checkFilterResult(filters, "asset.machineTypeModel", order.getAsset().getMachineTypeModel());
            checkFilterResult(filters, "serviceProviderReferenceNumber", order.getServiceProviderReferenceNumber());
            checkFilterResult(filters, "customerAddress.StreetAddress", order.getCustomerAddress().getAddressName()); // ?
            checkFilterResult(filters, "customerAddress.City", order.getCustomerAddress().getCity());
            checkFilterResult(filters, "customerAddress.State", order.getCustomerAddress().getState());
            checkFilterResult(filters, "customerAddress.Province", order.getCustomerAddress().getProvince());
            checkFilterResult(filters, "customerAddress.PostalCode", order.getCustomerAddress().getPostalCode());
            checkFilterResult(filters, "customerContact.FirstName", order.getCustomerContact().getFirstName());
            checkFilterResult(filters, "customerContact.LastName", order.getCustomerContact().getLastName());
            //                checkFilterResult(filters, "order.startDate", order.get);
            //                checkFilterResult(filters, "order.endDate", order.get);
            checkFilterResult(filters, "status", order.getStatus());
        }
    }


    
    @Test
    public void testFilter_QA_status() throws Exception {
        Map<String, Object> filters = new LinkedHashMap<String, Object>();
//        filters.put("status", "Status");
        filters.put("responseMetric", "1"); 
        OrderListContract contract =  new OrderListContract();
//        contract.setNewQueryIndicator(true);
        contract.setMdmLevel("Siebel");
        contract.setMdmId("1-1G0U13D");
        contract.setFilterCriteria(filters);
        contract.setStatus("Open");
        OrderListResult r = orderService.retrieveOrderList(contract);
        for (Order order : r.getOrderList()) {
            checkFilterResult(filters, "status", order.getStatus());
        } 
    }
    
    @Test
    public void testFilter_QA_dateFilter() throws Exception {
        Map<String, Object> filters = new LinkedHashMap<String, Object>();
        filters.put("order.startDate", "08/01/2012 00:00:00");  
        filters.put("order.endDate", "08/02/2012 23:00:00");   
        
        OrderListContract contract =  new OrderListContract();
//        contract.setNewQueryIndicator(true);
        contract.setMdmLevel("Siebel");
        contract.setMdmId("1-1G0U13D");
        contract.setFilterCriteria(filters);
        OrderListResult r = orderService.retrieveOrderList(contract);
        for (Order o : r.getOrderList()) {
            System.out.printf("createdDate=%s\n", o.getCreatedDate());
        }
        assertEquals(1, r.getOrderList().size());
    }
    
    @Test
    public void testFilter_QA_statuses() throws Exception {
        Map<String, Object> filters = new LinkedHashMap<String, Object>();
        filters.put("order.startDate", "04/09/2012 13:00:00");  
        filters.put("order.endDate", "08/21/2012 13:00:00");   
        filters.put("status", "Order Accepted");   
        
        OrderListContract contract =  new OrderListContract();
        contract.setStatus("Open");
//        contract.setNewQueryIndicator(true);
        contract.setMdmLevel("Siebel");
        contract.setMdmId("1-1G0U13D");
        contract.setFilterCriteria(filters);
        OrderListResult r = orderService.retrieveOrderList(contract);
        for (Order o : r.getOrderList()) {
            System.out.printf("createdDate=%s, status=%s\n", o.getCreatedDate(), o.getStatus());
//            MiscTest.print("[pendingShipments]", o.getPendingShipments());
        }
        assertEquals(1, r.getOrderList().size());
    }    
    
    @Test
    public void testQuery() throws Exception {
        MiscTest.sampleSiebelQuery(PartnerRequestOrderDo.class, 
                " EXISTS ([LXK MPS Account Id] = '1-1G0U13D' AND [LXK MPS Account Level] = 'Siebel'  AND [LXK SD LP Contact Method] = 'eServices') AND ([LXK MPS Created] >= '04/09/2012 13:00:00') AND ([LXK MPS Created] <= '08/21/2012 13:00:00') AND EXISTS([LXK MPS Status] ~LIKE '*Order Accepted*') AND EXISTS (([LXK MPS Status]= 'Assigned to LP' OR  [LXK MPS Status] = 'Order Accepted' OR  [LXK MPS Status] = 'Back Ordered'))", 5); 
    }
    
    @Test
    public void testQueryWith2ExistsEpxr() throws Exception {
        MiscTest.sampleSiebelQuery(PartnerRequestOrderDo.class, 
                "EXISTS ([LXK MPS Account Id] = '1-1G0U13D' AND [LXK MPS Account Level] = 'Siebel'  AND [LXK SD LP Contact Method] = 'eServices' AND ([LXK MPS Status] ~LIKE '*Status*')  AND EXISTS (([LXK MPS Status]= 'Assigned to LP' OR  [LXK MPS Status] = 'Order Accepted' OR  [LXK MPS Status] = 'Back Ordered')) )", 5); 
    }
    
    public OrderListResult retrieveOrderList_QA(Map<String, Object> filterCriteria) {
        OrderListContract contract =  new OrderListContract();
//        contract.setNewQueryIndicator(true);
        contract.setMdmLevel("Siebel");
        contract.setMdmId("1-1G0U13D");
        contract.setFilterCriteria(filterCriteria);
        return orderService.retrieveOrderList(contract);
    }
    
    public OrderListResult retrieveOrderList(Map<String, Object> filterCriteria) {
        OrderListContract contract =  new OrderListContract();
//        contract.setNewQueryIndicator(true);
        contract.setMdmLevel("Global");
        contract.setMdmId("4537264");
        contract.setFilterCriteria(filterCriteria);
        return orderService.retrieveOrderList(contract);
    }
    
    @Test
    public void testRetrieveOrderList() throws Exception {
        Map<String, Object> filters = new HashMap<String, Object>();
        filters.put("order.startDate", "11/01/2008 00:00:00");  
        filters.put("order.endDate", "07/26/2012 00:00:00");   
        OrderListResult result = retrieveOrderList(filters);
        MiscTest.print(result.getOrderList());
        assertEquals(2, result.getOrderList().size());
    }
    
    @Test
    public void queryRetrieveOrderList() throws Exception {
        MiscTest.sampleSiebelQuery(PartnerRequestOrderDo.class, 
                 "EXISTS ([LXK MPS Global Account #] = '4537264' AND [LXK MPS Account Level] = 'Siebel')" 
                 , 5);
    }
    
    @Test
    public void queryResponseMetric() throws Exception {
        MiscTest.sampleSiebelQuery(PartnerRequestOrderDo.class, 
                 "EXISTS ([LXK MPS Account Id] = '1-1G0U13D' AND [LXK MPS Account Level] = 'Siebel'  AND [LXK SD LP Contact Method] = 'eServices') AND ([LXK MPS Response Time] ~LIKE '*1*')" 
                , 5);
    }
    
    @Test
    public void queryPartnerRequestOrderDo() throws Exception {
        List<PartnerRequestOrderDo> orderDos = MiscTest.sampleSiebelQuery(PartnerRequestOrderDo.class, "[createdDate] <> ''",  5);
        for (PartnerRequestOrderDo orderDo : orderDos) {
            System.out.printf("id=%s, createdDate=%s\n", orderDo.getId(), orderDo.getCreatedDate());
        }
        
    }
}
