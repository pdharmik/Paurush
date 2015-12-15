package com.lexmark.service.impl.real;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import util.TestSessionFactories;

import com.amind.session.Session;
import com.lexmark.contract.source.OrderListContract;
import com.lexmark.domain.GenericAddress;
import com.lexmark.domain.Order;
import com.lexmark.domain.ServiceRequestOrderLineItem;
import com.lexmark.result.source.OrderListResult;
import com.lexmark.service.api.CrmSessionHandle;

public class PartnerOrderListTest {

	protected static final Log logger = LogFactory
			.getLog(PartnerOrderListTest.class);
	AmindPartnerOrderService service;
	OrderListContract contract;
	AmindGlobalService globalService;
	CrmSessionHandle handle;

	@Before
	public void setUp() {
		service = new AmindPartnerOrderService();
		contract = new OrderListContract();
		globalService = new AmindGlobalService();
		globalService.setStatefulSessionFactory(TestSessionFactories
				.newStatefulSessionFactory());
		handle = globalService.initCrmSessionHandle(null);

	}

	public void tearDown() {
		if (contract.getSessionHandle() != null) {
			try {
				Session session = ((AmindCrmSessionHandle) contract
						.getSessionHandle()).acquire();
				if (session != null) {
					session.release();
				}
				((AmindCrmSessionHandle) contract.getSessionHandle()).release();
			} catch (InterruptedException e) {
				// squash
			}
		}
	}

	@Test
	public void testRetrieveOrderList() throws Exception {

		testRetrieveOrderInputParameters();
		OrderListResult activityResult = service.retrieveOrderList(contract);
		testRetrieveOrderOutputParameters(activityResult);

	}

	public void testRetrieveOrderInputParameters() throws InterruptedException,
			ParseException {

		setUp();

		// String mdmId = "12348789";
		// String mdmLevel = "Global";
		// String status = "Assigned to LP";
		// int startRecordNumber = 0;
		// int increment = 50;
		// CrmSessionHandle sessionHandle = handle;
		// boolean newQueryIndicator = true;
		// boolean lexmarkEmployeeFlag = false;

		String mdmId = "107998";
		String mdmLevel = "Legal";
		String status = "Open";
		int startRecordNumber = 0;
		int increment = 40;
		CrmSessionHandle sessionHandle = handle;
		boolean newQueryIndicator = true;
		boolean lexmarkEmployeeFlag = false;

		contract.setMdmId(mdmId);
		contract.setMdmLevel(mdmLevel);
		contract.setStatus(status);
		contract.setStartRecordNumber(startRecordNumber);
		contract.setIncrement(increment);
		contract.setSessionHandle(sessionHandle);
		contract.setNewQueryIndicator(newQueryIndicator);
		contract.setEmployeeFlag(lexmarkEmployeeFlag);

	}

	public void testRetrieveOrderOutputParameters(OrderListResult activityResult) {
		Assert.assertNotNull(activityResult);

		tearDown();

	}

	@Test
	public void testRetrieveOrderList_defect7664() throws Exception {

		OrderListContract contract = new OrderListContract();
		contract.setStatus("Open");
		contract.setMdmLevel("Siebel");
		contract.setMdmId("1-4LLYQJK");
		contract.setEmployeeFlag(false);
		contract.setSortCriteria((Map<String, Object>) MiscTest.newHashMap(
				"createdDate", "DESCENDING"));
		contract.setIncrement(40);
		contract.setStartRecordNumber(0);
		contract.setFilterCriteria((Map<String, Object>) MiscTest.newHashMap(
				"order.startDate", "06/09/2013 13:00:00", "order.endDate",
				"07/10/2013 13:00:00"));
		contract.setNewQueryIndicator(true);
		contract.setSessionHandle(handle);

		OrderListResult activityResult = service.retrieveOrderList(contract);
		MiscTest.print(activityResult.getOrderList());

	}

	@Test
	public void testRetrieveOrderList_defect7693() throws Exception {

		OrderListContract contract = new OrderListContract();
		contract.setStatus("Open");
		contract.setMdmLevel("Siebel");
		contract.setMdmId("1-4LLYQJK");
		contract.setEmployeeFlag(false);
		contract.setPendingDebriefFlag(false);
		contract.setSortCriteria((Map<String, Object>) MiscTest.newHashMap(
				"serviceProviderReferenceNumber", "ASCENDING"));
		contract.setIncrement(40);
		contract.setStartRecordNumber(0);
		// contract.setFilterCriteria((Map<String, Object>) MiscTest.newHashMap(
		// "order.startDate", "04/30/2013 13:00:00",
		// "order.endDate", "07/30/2013 13:00:00"));
		contract.setNewQueryIndicator(true);
		contract.setSessionHandle(handle);

		OrderListResult result = service.retrieveOrderList(contract);
		for (Order order : result.getOrderList()) {
			System.out.println(order.getAsset().getSerialNumber());
		}
		MiscTest.print(result.getOrderList());

	}

	@Test
	public void testRetrieveOrderList_defect8622() throws Exception {

		OrderListContract contract = new OrderListContract();
		contract.setStatus("Show All");
		contract.setMdmLevel("Legal");
		contract.setMdmId("133574");
		contract.setEmployeeFlag(false);
		contract.setPendingDebriefFlag(false);
		contract.setNewQueryIndicator(true);
		contract.setStartRecordNumber(0);
		contract.setIncrement(40);
		contract.setSessionHandle(handle);

		Map<String, Object> filterCriteria = new HashMap<String, Object>();
		filterCriteria.put("order.startDate", "08/04/2013 22:30:00");
		filterCriteria.put("order.endDate", "09/04/2013 22:30:00");
		contract.setFilterCriteria(filterCriteria);

		Map<String, Object> sortCriteria = new HashMap<String, Object>();
		sortCriteria.put("requestNumber", "DESCENDING");
		contract.setSortCriteria(sortCriteria);

		OrderListResult activityResult = service.retrieveOrderList(contract);

		List<Order> orderList = activityResult.getOrderList();
		int totalCount = activityResult.getTotalCount();

		System.out.println(orderList.size());
		System.out.println(totalCount);

	}

	@Test
	public void testRetrieveOrderList_defect9156() throws Exception {

		OrderListContract contract = new OrderListContract();
		contract.setStatus("Show All");
		contract.setMdmLevel("Legal");
		contract.setMdmId("107998");
		contract.setEmployeeFlag(false);
		contract.setPendingDebriefFlag(false);
		contract.setNewQueryIndicator(true);
		contract.setStartRecordNumber(0);
		contract.setIncrement(40);
		contract.setSessionHandle(handle);

		Map<String, Object> filterCriteria = new HashMap<String, Object>();
		filterCriteria.put("order.startDate", "09/01/2013 08:00:00");
		filterCriteria.put("order.endDate", "10/02/2013 08:00:00");
		contract.setFilterCriteria(filterCriteria);

		Map<String, Object> sortCriteria = new HashMap<String, Object>();
		sortCriteria.put("requestNumber", "ASCENDING");
		contract.setSortCriteria(sortCriteria);

		OrderListResult activityResult = service.retrieveOrderList(contract);

		List<Order> orderList = activityResult.getOrderList();
		int totalCount = activityResult.getTotalCount();

		System.out.println(orderList.size());
		System.out.println(totalCount);

		for (Order order : orderList) {
			System.out.println(order.getOrderNumber());
		}

		for (Order order : orderList) {
			GenericAddress customerAddress = order.getCustomerAddress();
			if (customerAddress != null) {
				System.out.println("Office number: "
						+ customerAddress.getOfficeNumber());
				System.out
						.println("District: " + customerAddress.getDistrict());
				System.out.println("County: " + customerAddress.getCounty());
			}
		}

	}

	@Test
	public void testRetrieveOrderList_defect9293() throws Exception {

		OrderListContract contract = new OrderListContract();
		contract.setStatus("Open");
		contract.setMdmLevel("Legal");
		contract.setMdmId("107998");
		contract.setEmployeeFlag(false);
		contract.setPendingDebriefFlag(false);
		contract.setNewQueryIndicator(true);
		contract.setStartRecordNumber(0);
		contract.setIncrement(40);
		contract.setSessionHandle(handle);

		Map<String, Object> filterCriteria = new HashMap<String, Object>();
		filterCriteria.put("order.startDate", "09/07/2013 08:00:00");
		filterCriteria.put("order.endDate", "10/08/2013 08:00:00");
		contract.setFilterCriteria(filterCriteria);

		Map<String, Object> sortCriteria = new HashMap<String, Object>();
		sortCriteria.put("orderNumber", "ASCENDING");
		contract.setSortCriteria(sortCriteria);

		OrderListResult activityResult = service.retrieveOrderList(contract);

		List<Order> orderList = activityResult.getOrderList();
		int totalCount = activityResult.getTotalCount();

		System.out.println(orderList.size());
		System.out.println(totalCount);

		// for (Order order : orderList) {
		// System.out.println(order.getOrderNumber());
		// }

		for (Order order : orderList) {
			GenericAddress customerAddress = order.getCustomerAddress();
			if (customerAddress != null) {
				System.out.println("Office number: "
						+ customerAddress.getOfficeNumber());
				System.out
						.println("District: " + customerAddress.getDistrict());
				System.out.println("County: " + customerAddress.getCounty());
			}
		}

	}

	@Test
	public void testRetrieveOrderList_NewAddressFields() throws Exception {

		OrderListContract contract = new OrderListContract();
		contract.setStatus("Open");
		contract.setMdmLevel("Legal");
		contract.setMdmId("23259");
		contract.setEmployeeFlag(false);
		contract.setPendingDebriefFlag(false);
		contract.setNewQueryIndicator(true);
		contract.setStartRecordNumber(0);
		contract.setIncrement(40);
		contract.setSessionHandle(handle);

		Map<String, Object> filterCriteria = new HashMap<String, Object>();
		filterCriteria.put("order.startDate", "09/24/2013 13:00:00");
		filterCriteria.put("order.endDate", "10/25/2013 13:00:00");
		contract.setFilterCriteria(filterCriteria);

		Map<String, Object> sortCriteria = new HashMap<String, Object>();
		sortCriteria.put("createdDate", "DESCENDING");
		contract.setSortCriteria(sortCriteria);

		OrderListResult activityResult = service.retrieveOrderList(contract);

		List<Order> orderList = activityResult.getOrderList();
		int totalCount = activityResult.getTotalCount();

		System.out.println(orderList.size());
		System.out.println(totalCount);

		for (Order order : orderList) {
			GenericAddress customerAddress = order.getCustomerAddress();
			if (customerAddress != null) {
				System.out.println("Office number: "
						+ customerAddress.getOfficeNumber());
				System.out
						.println("District: " + customerAddress.getDistrict());
				System.out.println("County: " + customerAddress.getCounty());
			}
		}

	}

	@Test
	public void testRetrieveOrderList_defect12265() throws Exception {

		OrderListContract contract = new OrderListContract();
		contract.setStatus("Open");
		contract.setMdmLevel("Global");
		contract.setMdmId("151064821");
		contract.setEmployeeFlag(false);
		contract.setPendingDebriefFlag(false);
		contract.setNewQueryIndicator(true);
		contract.setStartRecordNumber(0);
		contract.setIncrement(-1);
		contract.setSessionHandle(handle);

		Map<String, Object> filterCriteria = new HashMap<String, Object>();
		filterCriteria.put("order.startDate", "02/04/2014 18:30:00");
		filterCriteria.put("order.endDate", "03/07/2014 18:30:00");
		filterCriteria.put("status", "Shipped");
		contract.setFilterCriteria(filterCriteria);

		Map<String, Object> sortCriteria = new HashMap<String, Object>();
		sortCriteria.put("customerAddress.County", "ASCENDING");
		contract.setSortCriteria(sortCriteria);

		OrderListResult activityResult = service.retrieveOrderList(contract);

		List<Order> orderList = activityResult.getOrderList();
		int totalCount = activityResult.getTotalCount();

		System.out.println(orderList.size());
		System.out.println(totalCount);

		// for (Order order : orderList) {
		// List<ServiceRequestOrderLineItem> list = order.getPendingShipments();
		// System.out.println("List size: " + list.size());
		// for (ServiceRequestOrderLineItem serviceRequestOrderLineItem : list)
		// {
		// System.out.println("Status: " +
		// serviceRequestOrderLineItem.getStatus());
		// }
		// }

		for (Order order : orderList) {
			// System.out.println(order.getOrderShipments().size());
		}

	}

	@Test
	public void testRetrieveOrderList_defect12265_2() throws Exception {

		OrderListContract contract = new OrderListContract();
		contract.setStatus("Open");
		contract.setMdmLevel("Global");
		contract.setMdmId("151064821");
		contract.setEmployeeFlag(false);
		contract.setPendingDebriefFlag(false);
		contract.setNewQueryIndicator(true);
		contract.setStartRecordNumber(0);
		contract.setIncrement(40);
		contract.setSessionHandle(handle);

		Map<String, Object> filterCriteria = new HashMap<String, Object>();
		filterCriteria.put("order.startDate", "02/04/2014 18:30:00");
		filterCriteria.put("order.endDate", "02/18/2014 18:30:00");
		filterCriteria.put("status", "Shipped");
		contract.setFilterCriteria(filterCriteria);

		Map<String, Object> sortCriteria = new HashMap<String, Object>();
		sortCriteria.put("customerAddress.County", "ASCENDING");
		contract.setSortCriteria(sortCriteria);

		OrderListResult activityResult = service.retrieveOrderList(contract);

		List<Order> orderList = activityResult.getOrderList();
		int totalCount = activityResult.getTotalCount();

		System.out.println(orderList.size());
		System.out.println(totalCount);

		for (Order order : orderList) {
			// System.out.println(order.getOrderShipments().size());
		}

		System.out.println("End");

	}

	@Test
	public void testRetrieveOrderList_defect12368() throws Exception {

		OrderListContract contract = new OrderListContract();
		contract.setStatus("Open");
		contract.setMdmLevel("Global");
		contract.setMdmId("519926455");
		contract.setEmployeeFlag(false);
		contract.setPendingDebriefFlag(false);
		contract.setNewQueryIndicator(true);
		contract.setStartRecordNumber(0);
		contract.setIncrement(40);
		contract.setSessionHandle(handle);

		Map<String, Object> filterCriteria = new HashMap<String, Object>();
		filterCriteria.put("order.startDate", "02/10/2014 09:00:00");
		filterCriteria.put("order.endDate", "03/13/2014 08:00:00");
		contract.setFilterCriteria(filterCriteria);

		Map<String, Object> sortCriteria = new HashMap<String, Object>();
		sortCriteria.put("createdDate", "DESCENDING");
		contract.setSortCriteria(sortCriteria);

		OrderListResult activityResult = service.retrieveOrderList(contract);

		List<Order> orderList = activityResult.getOrderList();
		int totalCount = activityResult.getTotalCount();

		System.out.println(orderList.size());
		System.out.println(totalCount);

	}

	@Test
	public void testRetrieveOrderList_defect12265_3() throws Exception {
		OrderListContract contract = new OrderListContract();
		contract.setStatus("Open");
		contract.setMdmId("151064821");
		contract.setMdmLevel("Global");
		contract.setNewQueryIndicator(true);
		Map<String, Object> filterCriteria = new HashMap<String, Object>();
		filterCriteria.put("order.startDate", "02/04/2014 18:30:00");
		filterCriteria.put("order.endDate", "03/07/2014 18:30:00");
		filterCriteria.put("status", "Shipped");
		contract.setFilterCriteria(filterCriteria);
		Map<String, Object> sortCriteria = new HashMap<String, Object>();
		sortCriteria.put("customerAddress.County", "ASCENDING");
		contract.setSortCriteria(sortCriteria);
		contract.setSessionHandle(handle);
		OrderListResult r = service.retrieveOrderList(contract);

		MiscTest.print(r.getOrderList());
		int i = 0;
		for (Order order : r.getOrderList()) {
			for (ServiceRequestOrderLineItem serviceRequestOrderLineItem : order
					.getPendingShipments()) {
				// System.out.println("BackOrderQuantity: " +
				// serviceRequestOrderLineItem.getBackOrderQuantity());
				// System.out.println("ShippedQuantity: " +
				// serviceRequestOrderLineItem.getShippedQuantity());
				// System.out.println("PendingQuantity: " +
				// serviceRequestOrderLineItem.getPendingQuantity());
				System.out.println("Status: "
						+ serviceRequestOrderLineItem.getStatus());
				i++;
			}
		}
		System.out.println("count: " + i);
		logger.debug("END");
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
		contract.setSessionHandle(handle);
		
		Map<String,Object> filterCriteria = new HashMap<String, Object>();
		filterCriteria.put("order.startDate", "02/23/2014 09:00:00");
		filterCriteria.put("order.endDate", "03/26/2014 08:00:00");
		contract.setFilterCriteria(filterCriteria);
		
		Map<String,Object> sortCriteria = new HashMap<String, Object>();
		sortCriteria.put("createdDate", "DESCENDING");
		contract.setSortCriteria(sortCriteria);
		
		OrderListResult activityResult = service.retrieveOrderList(contract);
		
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
	
	@Test
	public void testRetrieveOrderList_defect12265_ResultMismatch() throws Exception {

		OrderListContract contract = new OrderListContract();
		contract.setStatus("Open");
		contract.setMdmLevel("Global");
		contract.setMdmId("151064821");
		contract.setEmployeeFlag(false);
		contract.setPendingDebriefFlag(false);
		contract.setNewQueryIndicator(true);
		contract.setStartRecordNumber(0);
		contract.setIncrement(40);
		contract.setSessionHandle(handle);

		Map<String, Object> filterCriteria = new HashMap<String, Object>();
		filterCriteria.put("order.startDate", "03/01/2014 13:00:00");
		filterCriteria.put("order.endDate", "04/01/2014 13:00:00");
		contract.setFilterCriteria(filterCriteria);

		Map<String, Object> sortCriteria = new HashMap<String, Object>();
		sortCriteria.put("createdDate", "DESCENDING");
		contract.setSortCriteria(sortCriteria);

		OrderListResult activityResult = service.retrieveOrderList(contract);

		List<Order> orderList = activityResult.getOrderList();
		int totalCount = activityResult.getTotalCount();

		System.out.println(orderList.size());
		System.out.println(totalCount);

		for (Order order : orderList) {
			// System.out.println(order.getOrderShipments().size());
		}

		System.out.println("End");

	}
	
	@Test
	public void testRetrieveOrderList_defect12593() throws Exception {

		OrderListContract contract = new OrderListContract();
		contract.setStatus("Closed");
		contract.setMdmLevel("Legal");
		contract.setMdmId("13475");
		contract.setEmployeeFlag(false);
		contract.setPendingDebriefFlag(false);
		contract.setNewQueryIndicator(true);
		contract.setStartRecordNumber(0);
		contract.setIncrement(40);
		contract.setSessionHandle(handle);

		Map<String, Object> filterCriteria = new HashMap<String, Object>();
		filterCriteria.put("order.startDate", "03/03/2014 18:30:00");
		filterCriteria.put("order.endDate", "04/03/2014 18:30:00");
		filterCriteria.put("requestNumber", "1-20017253297");
//		filterCriteria.put("status", "Delivered");
		contract.setFilterCriteria(filterCriteria);

		Map<String, Object> sortCriteria = new HashMap<String, Object>();
		sortCriteria.put("createdDate", "DESCENDING");
		contract.setSortCriteria(sortCriteria);

		OrderListResult activityResult = service.retrieveOrderList(contract);

		List<Order> orderList = activityResult.getOrderList();
		int totalCount = activityResult.getTotalCount();

		System.out.println(orderList.size());
		System.out.println(totalCount);

		for (Order order : orderList) {
			// System.out.println(order.getOrderShipments().size());
			System.out.println(order.getStatus());
		}

		System.out.println("End");

	}
	
	@Test
	public void testRetrieveOrderList_defect12265_ResultMismatch_2() throws Exception {

		OrderListContract contract = new OrderListContract();
		contract.setStatus("Open");
		contract.setMdmLevel("Global");
		contract.setMdmId("151064821");
		contract.setEmployeeFlag(false);
		contract.setPendingDebriefFlag(false);
		contract.setNewQueryIndicator(true);
		contract.setStartRecordNumber(0);
		contract.setIncrement(40);
		contract.setSessionHandle(handle);

		Map<String, Object> filterCriteria = new HashMap<String, Object>();
		filterCriteria.put("order.startDate", "03/03/2014 13:00:00");
		filterCriteria.put("order.endDate", "04/03/2014 13:00:00");
		contract.setFilterCriteria(filterCriteria);

		Map<String, Object> sortCriteria = new HashMap<String, Object>();
		sortCriteria.put("createdDate", "DESCENDING");
		contract.setSortCriteria(sortCriteria);

		OrderListResult activityResult = service.retrieveOrderList(contract);

		List<Order> orderList = activityResult.getOrderList();
		int totalCount = activityResult.getTotalCount();

		System.out.println(orderList.size());
		System.out.println(totalCount);

		for (Order order : orderList) {
			System.out.println(order.getPendingShipments().size());
			System.out.println("--------------");
			List<ServiceRequestOrderLineItem> list = order.getPendingShipments();
			for (ServiceRequestOrderLineItem item : list) {
				System.out.println(item.getShippedQuantity());
				System.out.println(item.getShippedDate());
				System.out.println(item.getTrackingNumber());
				System.out.println("Tracking: " + item.getTracking());
				System.out.println(item.getCarrier());
				System.out.println(item.getShipmentStatus());
				System.out.println(item.getDeliveryDate());
			}
		}

		System.out.println("End");

	}

}
