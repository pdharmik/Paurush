package com.lexmark.service.impl.real.partnerOrderService;

import static com.lexmark.service.impl.real.SortUtil.checkSortOrder;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.amind.session.Session;
import com.lexmark.contract.source.OrderListContract;
import com.lexmark.domain.Order;
import com.lexmark.result.source.OrderListResult;
import com.lexmark.service.impl.real.AmindServiceTest;
import com.lexmark.service.impl.real.MiscTest;
import com.lexmark.service.impl.real.SortUtil.SortCriteria;
import com.lexmark.service.impl.real.util.TestErrors;

/**
 * 
 * @see com.lexmark.service.impl.real.AmindPartnerOrderServiceTestSuite
 * @see com.lexmark.service.impl.real.partnerRequest.OrderServiceListFilterTest 
 * 
 * @author vpetruchok
 * @version 1.0, 2012-07-26
 */
public class OrderServiceListSortTest extends AmindServiceTest {

    static OrderServiceList orderService;
    static Session session;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        session = statefulSessionFactory.attachSession();
        orderService = new OrderServiceList(session, crmSessionHandle); 
    }
    
    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        statefulSessionFactory.releaseSession(session);
    }
    
    @Test
    public void testSortOrder_QA_AllFields() throws Exception {
        String[][] sortFields = {
                // format: <portalField>, <aMind field xpath> 
                {"responseMetric",     "responseMetric"},  
                {"asset.SerialNumber", "asset.serialNumber"},
                {"asset.ProductLine",  "asset.productLine"}, 
                {"asset.machineTypeModel",         "asset.machineTypeModel"},
                {"serviceProviderReferenceNumber", "serviceProviderReferenceNumber"},
                {"customerAddress.StreetAddress",  "customerAddress.addressName"}, 
                {"customerAddress.City",       "customerAddress.city"}, 
                {"customerAddress.State",      "customerAddress.state"},
                {"customerAddress.Province",   "customerAddress.province"},
                {"customerAddress.PostalCode", "customerAddress.postalCode"},
                {"customerContact.FirstName",  "customerContact.firstName"},
                {"customerContact.LastName",   "customerContact.lastName"},
                {"order.startDate",            "createdDate"},  
                {"order.endDate",              "createdDate"},   
                {"status",                     "status"},
        };
        
        TestErrors errors = new TestErrors();
        for (String[] field:  sortFields) {
            for (Boolean ascendingOrder : Arrays.asList(Boolean.TRUE, Boolean.FALSE)) {
                SortCriteria sortCriteria = new SortCriteria(field[0], field[1] ,ascendingOrder, true);
                System.out.printf("***** processing %s\n",  str(field));
                try {
                     OrderListResult result = retrieveOrderList_QA(sortCriteria.toMap());
                    checkSortOrder(result.getOrderList(), sortCriteria);
                } catch (Throwable ex) {
                    errors.add(ex, "" + sortCriteria);
                }
            }
        }
        errors.check();
    }
    
    @Test
    public void testSortOrder_QA_SingleValuedFields() throws Exception {
        String[][] sortFields = {
                // format: <portalField>, <aMind field xpath> 
//                {"responseMetric",     "responseMetric"},  // calcualted field
                {"asset.SerialNumber", "asset.serialNumber"},
//                {"asset.ProductLine",  "asset.productLine"},  //MVF
//                {"asset.machineTypeModel",         "asset.machineTypeModel"},  // MVF
//                {"serviceProviderReferenceNumber", "serviceProviderReferenceNumber"}, // MVF
                {"customerAddress.StreetAddress",  "customerAddress.addressName"}, 
                {"customerAddress.City",       "customerAddress.city"}, 
                {"customerAddress.State",      "customerAddress.state"},
                {"customerAddress.Province",   "customerAddress.province"},
                {"customerAddress.PostalCode", "customerAddress.postalCode"},
                {"customerContact.FirstName",  "customerContact.firstName"},
                {"customerContact.LastName",   "customerContact.lastName"},
//                {"order.startDate",            "createdDate"},  
//                {"order.endDate",              "createdDate"},   
                {"createdDate",              "createdDate"},   
//                {"status",                     "status"},  // MVF
        };
        
        TestErrors errors = new TestErrors();
        for (String[] field:  sortFields) {
            for (Boolean ascendingOrder : Arrays.asList(Boolean.TRUE, Boolean.FALSE)) {
                SortCriteria sortCriteria = new SortCriteria(field[0], field[1] ,ascendingOrder, true);
                System.out.printf("***** processing %s\n",  str(field));
                try {
                     OrderListResult result = retrieveOrderList_QA(sortCriteria.toMap());
                    checkSortOrder(result.getOrderList(), sortCriteria);
                } catch (Throwable ex) {
                    errors.add(ex, "" + sortCriteria);
                }
            }
        }
        errors.check();
    }
    
    @Test
    public void testSortOrder_QA_nonSingleValuedFields() throws Exception {
        String[][] sortFields = {
                // format: <portalField>, <aMind field xpath> 
                {"responseMetric",     "responseMetric"},  // calcualted field
                {"asset.ProductLine",  "asset.productLine"}, 
                {"asset.machineTypeModel",         "asset.machineTypeModel"},
                {"serviceProviderReferenceNumber", "serviceProviderReferenceNumber"},
                {"status",                     "status"},
        };
        
        TestErrors errors = new TestErrors();
        for (String[] field:  sortFields) {
            for (Boolean ascendingOrder : Arrays.asList(Boolean.TRUE, Boolean.FALSE)) {
                SortCriteria sortCriteria = new SortCriteria(field[0], field[1] ,ascendingOrder, true);
                System.out.printf("***** processing %s\n",  str(field));
                try {
                     OrderListResult result = retrieveOrderList_QA(sortCriteria.toMap());
                    checkSortOrder(result.getOrderList(), sortCriteria);
                } catch (Throwable ex) {
                    errors.add(ex, "" + sortCriteria);
                }
            }
        }
        errors.check();
    }
    
    @Test
    public void printMvfFields() throws Exception {
        System.out.println(OrderServiceList.fieldCriteria.multiValuedFieldMap()); 
    }
    
    @Test
    public void testSortOrder_QA_bugs() throws Exception {
        String[][] sortFields = {
                // format: <portalField>, <aMind field xpath> 
//                {"responseMetric",      "responseMetric"},  
                {"customerAddress.StreetAddress",   "customerAddress.addressName"},
        };
        
        for (String[] field : sortFields) {
            for (Boolean ascendingOrder : Arrays.asList(Boolean.TRUE, Boolean.FALSE)) {
                SortCriteria sortCriteria = new SortCriteria(field[0], field[1], ascendingOrder, true);
                System.out.printf("***** processing %s\n", sortCriteria);
                OrderListResult result = retrieveOrderList_QA(sortCriteria.toMap());
                checkSortOrder(result.getOrderList(), sortCriteria);
            }
        }
    }    
    
    
    public OrderListResult retrieveOrderList_QA(Map<String, Object> sortCriteria) {
        OrderListContract contract =  new OrderListContract();
//        contract.setNewQueryIndicator(true);
        contract.setMdmLevel("Siebel");
        contract.setMdmId("1-1G0U13D");
        contract.setSortCriteria(sortCriteria);
        contract.setStartRecordNumber(0);
        contract.setIncrement(40);
        return orderService.retrieveOrderList(contract);
    }
    
    @Test
    public void testRetrieveOrderList_QA() throws Exception {
        Map<String, Object> m = new HashMap<String, Object>();
        OrderListResult r = retrieveOrderList_QA(m);
        MiscTest.print(r.getOrderList());
    }   

    @Test
    public void testRetrieveOrderList_createdDate_QA() throws Exception {
        {
            Map<String, Object> m = new HashMap<String, Object>();
            m.put("createdDate", "ASCENDING");
            OrderListResult r = retrieveOrderList_QA(m);
            List<Order> orderList = r.getOrderList();
            List<Date> dates = new ArrayList<Date>();
            for (Order order : orderList) {
                dates.add(order.getCreatedDate());
            }
            List<Date> dates2 = new ArrayList<Date>(dates);
            Collections.sort(dates);
            
            for (Date d : dates2) {
                System.out.println(d);
            }
            assertEquals(dates, dates2);
        }
        {
            Map<String, Object> m = new HashMap<String, Object>();
            m.put("createdDate", "DESCENDING");
            OrderListResult r = retrieveOrderList_QA(m);
            List<Order> orderList = r.getOrderList();
            List<Date> dates = new ArrayList<Date>();
            for (Order order : orderList) {
                dates.add(order.getCreatedDate());
            }
            List<Date> dates2 = new ArrayList<Date>(dates);
            Collections.sort(dates, new Comparator<Date> () {

                @Override
                public int compare(Date d1, Date d2) {
                    return -(d1.compareTo(d2));
                }
                
            });
            
            for (Date d : dates2) {
                System.out.println(d);
            }
            assertEquals(dates, dates2);
        }        
        
        { // without sorting
            Map<String, Object> m = new HashMap<String, Object>();
//            m.put("createdDate", "DESCENDING");
            OrderListResult r = retrieveOrderList_QA(m);
            List<Order> orderList = r.getOrderList();
            List<Date> dates = new ArrayList<Date>();
            for (Order order : orderList) {
                dates.add(order.getCreatedDate());
            }
            List<Date> dates2 = new ArrayList<Date>(dates);
            Collections.sort(dates, new Comparator<Date> () {

                @Override
                public int compare(Date d1, Date d2) {
                    return -(d1.compareTo(d2));
                }
                
            });
            for (Date d : dates2) {
                System.out.println(d);
            }
            try {
                assertEquals(dates, dates2);
            } catch (AssertionError e) {
                e.printStackTrace();
            }
        }         
        
    }
    
    @Test
    public void test1() throws Exception {
        Map<String, String> m = new HashMap<String, String>();
        m.put("1", "one");
        m.put("2", "two");
        System.out.println(m);
        
        
        
    }
    
}
