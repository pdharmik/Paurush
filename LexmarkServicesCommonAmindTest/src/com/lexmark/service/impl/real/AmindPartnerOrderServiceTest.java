package com.lexmark.service.impl.real;

import static org.junit.Assert.assertNotNull;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.amind.session.StatefulSessionFactory;
import com.lexmark.contract.AttachmentContract;
import com.lexmark.contract.MassUploadTemplateContract;
import com.lexmark.contract.source.OrderAcceptContract;
import com.lexmark.contract.source.OrderDetailContract;
import com.lexmark.contract.source.OrderListContract;
import com.lexmark.domain.Attachment;
import com.lexmark.domain.MassUploadTemplateOrderLineItem;
import com.lexmark.domain.Order;
import com.lexmark.result.AttachmentListResult;
import com.lexmark.result.MassUploadTemplateResult;
import com.lexmark.result.source.OrderAcceptResult;
import com.lexmark.result.source.OrderDetailResult;
import com.lexmark.result.source.OrderListResult;
import com.lexmark.service.impl.real.domain.AcceptOrderDo;
import com.lexmark.service.impl.real.domain.PartnerRequestOrderDo;
import com.lexmark.service.impl.real.domain.SupplyRequestDetailDo;
import com.lexmark.service.impl.real.domain.SupplyRequestDetailOrderItemDo;
import com.lexmark.util.LangUtil;

/**
 * @see com.lexmark.service.impl.real.AmindPartnerOrderServiceTestSuite
 * 
 * @author vpetruchok
 * @version 1.0, 2012-07-17
 */
public class AmindPartnerOrderServiceTest extends AmindServiceTest {

	AmindPartnerOrderService service;

	@Before
	public void setUp() throws Exception {
		service = new AmindPartnerOrderService();
		service.setStatelessSessionFactory(statelessSessionFactory);
	}

	@Test
	public void testAcceptServiceOrderRequest_QA_bug() throws Exception {
		OrderAcceptContract contract = new OrderAcceptContract();
		contract.setOrderLineItemId("1-43NPBTX");
		// contract.setOrderId("1-8928340500");
		contract.setOrderId("1-43NPBMC");
		contract.setVendorIds(Arrays.asList("1-1G0U13D"));
		// contract.setStatus("Assigned to LP");
		contract.setStatus("Order Accepted");
		OrderAcceptResult result = service.acceptServiceOrderRequest(contract);
		System.out.println("result = " + str(result));
		System.out.println("End");
	}

	@Test
	public void testAcceptServiceOrderRequest_DEV() throws Exception {
		OrderAcceptContract contract = new OrderAcceptContract();
		contract.setOrderId("1-1UYHHK");
		// contract.setVendorId("1-G6RYR1");
		contract.setStatus("Order Accepted");
		OrderAcceptResult result = service.acceptServiceOrderRequest(contract);
		System.out.println("result = " + str(result));
		System.out.println("End");
	}

	@Test
	public void queryAcceptOrderDo() throws Exception {
		MiscTest.sampleSiebelQuery(AcceptOrderDo.class, "", 5);
	}

	@Test
	public void testRetrieveOrderList_QA() throws Exception {
		OrderListContract contract = new OrderListContract();
		contract.setMdmId("1-1G0U13D");
		contract.setMdmLevel("Siebel");
		contract.setSessionHandle(crmSessionHandle);
		OrderListResult r = service.retrieveOrderList(contract);
		MiscTest.print(r.getOrderList());

	}

	@Test
	public void queryPartnerRequestOrderDo() throws Exception {
		MiscTest.sampleSiebelQuery(
				PartnerRequestOrderDo.class,
				"EXISTS ([LXK MPS Account Id] = '1-1G0U13D' AND [LXK MPS Account Level] = 'Siebel' AND ([LXK MPS Status] = 'Routed' OR [LXK MPS Status] = 'Order Accepted'  OR [LXK MPS Status] = 'Shipped' OR [LXK MPS Status] = 'Delivered'  OR [LXK MPS Status] = 'Back Ordered' OR [LXK MPS Status] = 'Cancelled' )) AND ([Status] <> 'Ready for Order' OR [Status] <> 'Pending' OR [Status] <> 'Assignment Failed')"
				// "EXISTS ([LXK MPS Service Provider Id] = '1-1G0U13D' AND [LXK MPS Account Level] = 'Siebel' AND ([LXK MPS Status] = 'Routed' OR [LXK MPS Status] = 'Order Accepted'  OR [LXK MPS Status] = 'Shipped' OR [LXK MPS Status] = 'Delivered'  OR [LXK MPS Status] = 'Back Ordered' OR [LXK MPS Status] = 'Cancelled' )) AND ([Status] <> 'Ready for Order' OR [Status] <> 'Pending' OR [Status] <> 'Assignment Failed')"
				, 10);
	}

	@Test
	public void testRetrieveOrderDetail_QA() throws Exception {
		OrderDetailContract contract = new OrderDetailContract();
		contract.setRequestNumber("1-8847254711");
		contract.setOrderNumber("1-8848451381");

		OrderDetailResult result = service.retrieveOrderDetail(contract);
		MiscTest.print("[pendingShipment] ", result.getOrder()
				.getPendingShipments());
		MiscTest.print("[processeedParts] ", result.getOrder()
				.getProcessedParts());

		assertNotNull(result);
	}

	/**
	 * @see com.lexmark.service.impl.real.partnerRequest.OrderServiceListTest#testRetrieveOrderList_QA()
	 */
	@Test
	public void testRetrieveOrderDetail_QA_2() throws Exception {
		OrderDetailContract contract = new OrderDetailContract();
		contract.setOrderNumber("1-8848451072");
		contract.setRequestNumber("1-8846641893");

		OrderDetailResult r = service.retrieveOrderDetail(contract);
		MiscTest.print("[pendingShipment] ", r.getOrder().getPendingShipments());
		MiscTest.print("[processeedParts] ", r.getOrder().getProcessedParts());
		MiscTest.print("[emailActivities] ", r.getOrder().getEmailActivities());
		// System.out.println(str(r.getOrder()));
	}

	@Test
	public void testRetrieveOrderDetail_QA_3() throws Exception {
		OrderDetailContract contract = new OrderDetailContract();
		contract.setOrderNumber("1-9185122669");
		contract.setRequestNumber("1-9184953043");
		contract.setVendorAccountIds(Arrays.asList("1-5HGJ05"));

		OrderDetailResult r = service.retrieveOrderDetail(contract);
		System.out.println(str(r.getOrder()));
		MiscTest.print("[pendingShipment] ",
				r.getOrder().getPendingShipments(), "portalFulfillmentStatus");
		MiscTest.print("[processeedParts] ", r.getOrder().getProcessedParts(),
				"portalFulfillmentStatus");
		MiscTest.print("[emailActivities] ", r.getOrder().getEmailActivities());
		System.out.println("shippingAddress: "
				+ str(r.getOrder().getShippingAddress()));
		System.out.println("shippingAddress: storeFrontName = "
				+ r.getOrder().getShippingAddress().getStoreFrontName());
		// System.out.println(str(r.getOrder()));
	}

	/**
	 * @see AmindRequestTypeServiceTest#testRetrieveSupplyRequestDetail_QA_Defect4026()
	 */
	@Test
	public void querySupplyRequestDetailDo_QA_Defect4206() throws Exception {
		// 1-9959130052
		List<SupplyRequestDetailDo> r = MiscTest.sampleSiebelQuery(
				SupplyRequestDetailDo.class,
				"[serviceRequestNumber] = '1-9959130052'", 10);
		for (SupplyRequestDetailDo itemDo : r) {
			System.out.println("serviceAddress2 = "
					+ itemDo.getServiceAddress2());
			for (SupplyRequestDetailOrderItemDo orderItemDo : LangUtil
					.notNull(itemDo.getOrderItems())) {
				System.out.println("storeFrontName = "
						+ orderItemDo.getStoreFrontName());
			}
		}
	}

	@Test
	public void testRetrieveOrderDetail_QA_Defect2111() throws Exception {
		// OrderDetailContract contract = new OrderDetailContract();
		// contract.setOrderNumber("1-9933445076");
		// contract.setRequestNumber("1-9932421669");
		// contract.setVendorAccountIds(Arrays.asList("1-EZU50M"));

		OrderDetailContract contract = new OrderDetailContract();
		contract.setDebriefFlag(false);
		contract.setVendorAccountIds(Arrays.asList("1-4LLYQJK"));
		contract.setOrderNumber("1-12696580091");
		contract.setRequestNumber("1-12696281091");
		contract.setFilterCriteria(new HashMap<String, Object>());
		contract.setSearchCriteria(new HashMap<String, Object>());
		contract.setRequestNumber("0");
		contract.setIncrement(0);
		contract.setNewQueryIndicator(false);
		contract.setSortCriteria(new HashMap<String, Object>());

		OrderDetailResult r = service.retrieveOrderDetail(contract);
		System.out.println(str(r.getOrder()));
		// MiscTest.print("[pendingShipment] ",
		// r.getOrder().getPendingShipments());
		// MiscTest.print("[processeedParts] ",
		// r.getOrder().getProcessedParts());
		// MiscTest.print("[emailActivities] ",
		// r.getOrder().getEmailActivities());
		//
		// MiscTest.print("asset.serialNumber = ",
		// r.getOrder().getAsset().getSerialNumber());
	}

	/**
	 * 
	 * @see AmindRequestTypeServiceTest#testRetrieveSupplyRequestDetail_QA_Defect4212()
	 */
	@Test
	public void testRetrieveOrderDetail_QA_Defect4212() throws Exception {
		OrderDetailContract contract = new OrderDetailContract();
		contract.setOrderNumber("1-9930485857");
		contract.setRequestNumber("1-9929687226");
		contract.setVendorAccountIds(Arrays.asList("1-EZU50M"));

		OrderDetailResult r = service.retrieveOrderDetail(contract);
		System.out.println(str(r.getOrder()));
		MiscTest.print("[processeedParts] ", r.getOrder().getProcessedParts(),
				"partnumber", "partName", "shippedQuantity", "status");
	}

	@Test
	public void testRetrieveOrderDetail_QA_Defect5558() throws Exception {
		OrderDetailContract contract = new OrderDetailContract();
		contract.setOrderNumber("1-9930485857");
		contract.setRequestNumber("1-9929687226");
		contract.setVendorAccountIds(Arrays.asList("1-EZU50M"));

		OrderDetailResult r = service.retrieveOrderDetail(contract);
		// System.out.println(str(r.getOrder()));
		MiscTest.print("asset.serialNumber = ", r.getOrder().getAsset()
				.getSerialNumber());
	}

	@Test
	public void testRetrieveOrderDetail_QA_Defect4869() throws Exception {
		OrderDetailContract contract = new OrderDetailContract();
		contract.setOrderNumber("1-9930485857");
		contract.setRequestNumber("1-9929687226");
		contract.setVendorAccountIds(Arrays.asList("1-EZU50M"));

		OrderDetailResult r = service.retrieveOrderDetail(contract);
		MiscTest.print("order: ", r.getOrder(), "requestType", "orderType");
	}

	@Test
	public void testRetrieveOrderDetail_Production_Defect5501()
			throws Exception {
		OrderDetailContract contract = new OrderDetailContract();
		contract.setOrderNumber("1-10224508935");
		contract.setRequestNumber("1-10210671238");
		// contract.setVendorAccountIds(Arrays.asList("1-EZU50M"));

		OrderDetailResult r = service.retrieveOrderDetail(contract);
		System.out.println(str(r.getOrder().getShippingAddress()));
	}

	@Test
	public void testRetrieveOrderDetail_Production_Defect5572()
			throws Exception {
		OrderDetailContract contract = new OrderDetailContract();
		contract.setOrderNumber("1-10253825760");
		contract.setRequestNumber("1-10250065941");
		// contract.setVendorAccountIds(Arrays.asList("1-EZU50M"));

		OrderDetailResult r = service.retrieveOrderDetail(contract);
		System.out.println(str(r.getOrder()));
		System.out.println("getSpecialHandlingInstructions = "
				+ r.getOrder().getSpecialHandlingInstructions());
		System.out.println("getSpecialHandling = "
				+ r.getOrder().getSpecialHandling()); // TODO(Viktor): discuss:
														// this field is not
														// populated
		System.out.println("getInsideLocation = "
				+ r.getOrder().getInsideLocation());
		System.out.println("getAttentionTo = " + r.getOrder().getAttentionTo());
		System.out.println("getAssetIdentifier = "
				+ r.getOrder().getAssetIdentifier());

	}

	@Test
	public void testRetrieveRequevstAttachmentList_defect7719()
			throws Exception {

		// DESCENDING //ASCENDING
		long t = System.currentTimeMillis();

		AttachmentContract contract = new AttachmentContract();
		contract.setIdentifier("1-4LLYQJK");
		contract.setSortCriteria((Map<String, Object>) MiscTest.newHashMap(
				"completedOn", "ASCENDING"));
		contract.setSessionHandle(crmSessionHandle);
		contract.setNewQueryIndicator(true);
		contract.setIncrement(40);
		contract.setStartRecordNumber(0);

		// Map<String,Object> filterCriteria = new HashMap<String, Object>();
		// filterCriteria.put("fileName", "massUpload");
		// filterCriteria.put("requestNumber", "1-12822119981");
		// filterCriteria.put("status", "submitted");
		// contract.setFilterCriteria(filterCriteria);

		AttachmentListResult result = service
				.retrieveRequestAttachmentList(contract);

		System.out.println((System.currentTimeMillis() - t) / 1000.0);

		System.out.println("Size: " + result.getAttachmentList().size());
		System.out.println("Total count: " + result.getTotalCount());
	}

	@Test
	public void testRetrieveOrderList_massUpload() throws Exception {

		OrderListContract contract = new OrderListContract();
		contract.setStatus("Open");
		contract.setMdmLevel("Siebel");
		contract.setMdmId("1-4LLYQJK");
		contract.setEmployeeFlag(false);
		contract.setSortCriteria((Map<String, Object>) MiscTest.newHashMap(
				"createdDate", "DESCENDING"));
		contract.setIncrement(100);
		contract.setStartRecordNumber(0);
		contract.setNewQueryIndicator(true);
		contract.setSessionHandle(crmSessionHandle);
		contract.setFilterCriteria((Map<String, Object>) MiscTest.newHashMap(
				"order.startDate", "04/30/2013 13:00:00", "order.endDate",
				"05/31/2013 13:00:00", "status", "Shipped"));

		OrderListResult result = service.retrieveOrderList(contract);
		// System.out.println(result.getTotalCount());

	}

	@Test
	public void testRetrieveRequevstAttachmentList_defect7743()
			throws Exception {

		long t = System.currentTimeMillis();

		AttachmentContract contract = new AttachmentContract();
		contract.setIdentifier("1-4LLYQJK");

		// contract.setSortCriteria((Map<String, Object>)
		// MiscTest.newHashMap("requestNumber", "DESCENDING"));

		contract.setFilterCriteria((Map<String, Object>) MiscTest.newHashMap(
				"fileName", "~"));
		contract.setNewQueryIndicator(true);

		AmindCrmSessionHandle handle = new AmindCrmSessionHandle();
		handle.setSessionFactory(new StatefulSessionFactory());
		contract.setSessionHandle(handle);

		AttachmentListResult result = service
				.retrieveRequestAttachmentList(contract);

		System.out.println((System.currentTimeMillis() - t) / 1000.0);
		System.out.println(result.getAttachmentList().size());
		;
		System.out.println(result.getTotalCount());
	}

	@Test
	public void testRetrieveRequevstAttachmentList_RetrieveUploadHistory()
			throws Exception {
		AttachmentContract contract = new AttachmentContract();
		contract.setIdentifier("1-4LLYQJK");
		contract.setSortCriteria((Map<String, Object>) MiscTest.newHashMap(
				"status", "DESCENDING"));
		contract.setSessionHandle(crmSessionHandle);

		contract.setFilterCriteria((Map<String, Object>) MiscTest.newHashMap(
				"fileName", "test"));

		AttachmentListResult result = service
				.retrieveRequestAttachmentList(contract);

		for (Attachment att : result.getAttachmentList()) {
			System.out.println(att.getFileName());
			// System.out.println(att.getRequestNumber());
			// System.out.println(att.getCompletedOn());
			// System.out.println(att.getSubmittedOn());
			// System.out.println(att.getStatus());
			// System.out.println(att.getStatus());
			// System.out.println(att.getSize());
		}

		System.out.println(result.getAttachmentList().size());

		System.out.println("result = " + str(result));
	}

    @Test
    public void testRetrieveOrderList_QA_defect12584()throws Exception{
    	OrderListContract contract = new OrderListContract();
    	contract.setStatus("Closed");
    	contract.setMdmId("13475");
    	contract.setMdmLevel("Legal");
    	contract.setNewQueryIndicator(true);
    	Map<String, Object> filterCriteria = new HashMap<String, Object>();
    	filterCriteria.put("order.startDate", "03/02/2014 09:00:00");
    	filterCriteria.put("order.endDate", "04/02/2014 08:00:00");
    	filterCriteria.put("requestNumber", "1-20017253297");
    	contract.setFilterCriteria(filterCriteria );
    	Map<String, Object> sortCriteria = new HashMap<String, Object>();
    	sortCriteria.put("createdDate", "DESCENDING");
    	contract.setSortCriteria(sortCriteria );
    	contract.setSessionHandle(crmSessionHandle);
    	OrderListResult r = service.retrieveOrderList(contract);
    	
    	MiscTest.print(r.getOrderList());
    	int i = 0;
    	System.out.println("count: " + i);
    	logger.debug("END");
    }

	@Test
	public void testRetrieveOrderList_defect7724() throws Exception {

		OrderListContract contract = new OrderListContract();
		contract.setStatus("Open");
		contract.setMdmLevel("Siebel");
		contract.setMdmId("1-4LLYQJK");
		contract.setEmployeeFlag(false);
		contract.setPendingDebriefFlag(false);
		contract.setNewQueryIndicator(true);
		contract.setFilterCriteria((Map<String, Object>) MiscTest.newHashMap(
				"order.startDate", "04/30/2013 22:30:00", "order.endDate",
				"05/31/2013 22:30:00"
		// ,"status", "Order Accepted"
				));
		contract.setStartRecordNumber(0);
		contract.setIncrement(40);
		contract.setSortCriteria((Map<String, Object>) MiscTest.newHashMap(
				"createdDate", "DESCENDING"));
		contract.setSessionHandle(crmSessionHandle);

		OrderListResult result = service.retrieveOrderList(contract);

		System.out.println("Total count: " + result.getTotalCount());

	}

	@Test
	public void testRetrieveOrderDetail_Defect10721() throws Exception {
		OrderDetailContract contract = new OrderDetailContract();
		contract.setOrderNumber("1-15672159044");
		contract.setRequestNumber("1-15671823799");
		contract.setVendorAccountIds(Arrays.asList("1-4M1MF19"));

		OrderDetailResult result = service.retrieveOrderDetail(contract);

		System.out.println("end");
	}


	@Test
	public void testRetrieveRequevstAttachmentList_defect12269_CorrectContract()
			throws Exception {

		AttachmentContract contract = new AttachmentContract();
		contract.setIdentifier("1-IOI0EO");
		contract.setSortCriteria((Map<String, Object>) MiscTest.newHashMap(
				"requestNumber", "DESCENDING"));
		contract.setIncrement(-1);
		contract.setStartRecordNumber(0);
		contract.setNewQueryIndicator(true);
		contract.setSessionHandle(crmSessionHandle);

		AttachmentListResult result = service
				.retrieveRequestAttachmentList(contract);

		List<Attachment> list = result.getAttachmentList();

		System.out.println(result.getAttachmentList().size());
		;
		System.out.println(result.getTotalCount());

		for (Attachment attachment : list) {
			System.out.println(attachment.getFileName());
			System.out.println(attachment.getRequestNumber());
			System.out.println(attachment.getStatus());
			System.out.println("------------------");
		}
	}

	@Test
	public void testRetrieveRequevstAttachmentList_defect12235_2()
			throws Exception {
		AttachmentContract contract = new AttachmentContract();
		contract.setIdentifier("1-IOI0EO");
		contract.setIncrement(-1);
		contract.setStartRecordNumber(0);
		contract.setNewQueryIndicator(true);
		contract.setSortCriteria((Map<String, Object>) MiscTest.newHashMap(
				"requestNumber", "DESCENDING"));
		contract.setFilterCriteria((Map<String, Object>) MiscTest.newHashMap(
				"type", "Mass Update of HW Install"));
		contract.setSessionHandle(crmSessionHandle);
		AttachmentListResult result = service
				.retrieveRequestAttachmentList(contract);
		for (Attachment att : result.getAttachmentList()) {
			System.out.println("Type: " + att.getType());
		}
	}

	@Test
	public void testRetrieveRequevstAttachmentList_defect12284()
			throws Exception {

		AttachmentContract contract = new AttachmentContract();
		contract.setIdentifier("1-NMCFPJ");
		contract.setSortCriteria((Map<String, Object>) MiscTest.newHashMap(
				"requestNumber", "DESCENDING"));
		contract.setIncrement(40);
		contract.setStartRecordNumber(0);
		contract.setNewQueryIndicator(true);
		contract.setSessionHandle(crmSessionHandle);

		AttachmentListResult result = service
				.retrieveRequestAttachmentList(contract);

		List<Attachment> list = result.getAttachmentList();

		System.out.println(result.getAttachmentList().size());
		;
		System.out.println(result.getTotalCount());

		for (Attachment attachment : list) {
			System.out.println(attachment.getSubArea());
		}
	}

	@Test
	public void testRetrieveRequevstAttachmentList_defect12269_PreCall()
			throws Exception {

		AttachmentContract contract = new AttachmentContract();
		contract.setIdentifier("1-IOI0EO");
		contract.setSortCriteria((Map<String, Object>) MiscTest.newHashMap(
				"requestNumber", "DESCENDING"));
		contract.setIncrement(40);
		contract.setStartRecordNumber(0);
		contract.setNewQueryIndicator(true);
		contract.setSessionHandle(crmSessionHandle);

		contract.setFilterCriteria((Map<String, Object>) MiscTest.newHashMap(
				"type", "Consumable Mass Order Upload"));

		AttachmentListResult result = service
				.retrieveRequestAttachmentList(contract);

		List<Attachment> list = result.getAttachmentList();

		System.out.println(result.getAttachmentList().size());
		System.out.println(result.getTotalCount());

		for (Attachment attachment : list) {
			System.out.println(attachment.getSrRowId());
		}
	}

	@Test
	public void testRetrieveOrderList_defect12468() throws Exception {

		OrderListContract contract = new OrderListContract();
		long t0 = System.currentTimeMillis();

		contract.setStatus("Open");
		contract.setMdmLevel("Global");
		contract.setMdmId("966331451");
		contract.setEmployeeFlag(false);
		contract.setPendingDebriefFlag(false);

		contract.setSortCriteria((Map<String, Object>) MiscTest.newHashMap(
				"createdDate", "DESCENDING"));
		contract.setIncrement(40);
		contract.setStartRecordNumber(0);
		contract.setNewQueryIndicator(true);
		contract.setSessionHandle(crmSessionHandle);
		contract.setFilterCriteria((Map<String, Object>) MiscTest.newHashMap(
				"order.startDate", "02/19/2014 09:00:00", "order.endDate",
				"03/22/2014 08:00:00"));
		OrderListResult result = service.retrieveOrderList(contract);
		System.out.printf("Exec time=%s sec. \n",
				(System.currentTimeMillis() - t0) / 1000.0);
		// System.out.println(result.getTotalCount());

	}
	
	
	@Test
	public void testRetrieveRequevstAttachmentList_defect12269_ExtensionIssue()
			throws Exception {

		AttachmentContract contract = new AttachmentContract();
		contract.setIdentifier("1-NMCFPJ");
		contract.setSortCriteria((Map<String, Object>) MiscTest.newHashMap(
				"requestNumber", "DESCENDING"));
		contract.setIncrement(40);
		contract.setStartRecordNumber(0);
		contract.setNewQueryIndicator(true);
		contract.setSessionHandle(crmSessionHandle);

		AttachmentListResult result = service
				.retrieveRequestAttachmentList(contract);

		List<Attachment> list = result.getAttachmentList();

		System.out.println(result.getAttachmentList().size());
		System.out.println(result.getTotalCount());

		for (Attachment attachment : list) {
			System.out.println(attachment.getFileName());
			System.out.println(attachment.getExtension());
			System.out.println("------------------");
		}
	}
	
	@Test
	public void testRetrieveOrderList_defect12265_4() throws Exception {

		MassUploadTemplateContract contract = new MassUploadTemplateContract();
		contract.setStatus("Open");
		contract.setMdmLevel("Global");
		contract.setMdmId("151064821");

		Map<String, Object> filterCriteria = new HashMap<String, Object>();
		filterCriteria.put("order.startDate", "02/04/2014 18:30:00");
		filterCriteria.put("order.endDate", "02/18/2014 18:30:00");
		filterCriteria.put("status", "Shipped");
		contract.setFilterCriteria(filterCriteria);

		Map<String, Object> sortCriteria = new HashMap<String, Object>();
		sortCriteria.put("customerAddress.County", "ASCENDING");
		contract.setSortCriteria(sortCriteria);

		MassUploadTemplateResult result = service.retrieveMassUploadTemplateList(contract);
		
		List<MassUploadTemplateOrderLineItem> list = result.getOrderLineItemsList();
		
		for (MassUploadTemplateOrderLineItem massUploadTemplateOrder : list) {
			System.out.println("A");
		}

		System.out.println("End");

	}
	
	
	@Test
	public void testRetrieveRequevstAttachmentList_defect12557_() throws Exception {

		AttachmentContract contract = new AttachmentContract();
		contract.setIdentifier("1-IOI0EO");
		contract.setSortCriteria((Map<String, Object>) MiscTest.newHashMap(
				"requestNumber", "DESCENDING"));
		contract.setIncrement(40);
		contract.setStartRecordNumber(0);
		contract.setNewQueryIndicator(true);
		contract.setSessionHandle(crmSessionHandle);

		
//		contract.setFilterCriteria((Map<String, Object>) MiscTest.newHashMap(
//				"requestNumber", "1-984654293"));
//		contract.setFilterCriteria((Map<String, Object>) MiscTest.newHashMap(
//				"requestNumber", "1-19846542931"));
//		contract.setFilterCriteria((Map<String, Object>) MiscTest.newHashMap(
//				"status", "Submitted"));
//		contract.setFilterCriteria((Map<String, Object>) MiscTest.newHashMap(
//				"fileName", "ActivityUploadTemplates38"));
//		contract.setFilterCriteria((Map<String, Object>) MiscTest.newHashMap(
//				"type", "Mass Update of HW Install"));
		
		AttachmentListResult result = service
				.retrieveRequestAttachmentList(contract);

		List<Attachment> list = result.getAttachmentList();

		System.out.println(result.getAttachmentList().size());
		System.out.println(result.getTotalCount());

		for (Attachment attachment : list) {
			System.out.println(attachment.getFileName());
			System.out.println(attachment.getRequestNumber());
			System.out.println(attachment.getStatus());
			System.out.println(attachment.getSubArea());
			System.out.println("------------------");
		}
	}

    @Test
    public void testRetrieveRequevstAttachmentList_defect10863() throws Exception {
    	AttachmentContract contract = new AttachmentContract();
    	contract.setIdentifier("1-4M1MF19");
    	contract.setSortCriteria((Map<String, Object>) MiscTest.newHashMap("requestNumber", "DESCENDING"));
    	contract.setIncrement(40);
    	contract.setStartRecordNumber(0);
    	contract.setNewQueryIndicator(true);
    	AmindCrmSessionHandle handle = new AmindCrmSessionHandle();
    	handle.setSessionFactory(new StatefulSessionFactory());
    	contract.setSessionHandle(handle);
    	
    	AttachmentListResult result = service.retrieveRequestAttachmentList(contract);
    	logger.debug("AttachmentList: " + result.getAttachmentList().size());
    }
    
    @Test
    public void testRetrieveRequevstAttachmentList_defect10821() throws Exception {
    	AttachmentContract contract = new AttachmentContract();
    	contract.setIdentifier("1-4M1MF19");
    	
    	Map<String, Object> sortCriteria = new HashMap<String, Object>();
    	sortCriteria.put("requestNumber", "DESCENDING");
		contract.setSortCriteria(sortCriteria );
    	contract.setIncrement(40);
    	contract.setStartRecordNumber(0);
    	contract.setNewQueryIndicator(true);
    	AmindCrmSessionHandle handle = new AmindCrmSessionHandle();
    	handle.setSessionFactory(new StatefulSessionFactory());
    	contract.setSessionHandle(handle);
    	
    	AttachmentListResult result = service.retrieveRequestAttachmentList(contract);
    	logger.debug("AttachmentList: " + result.getAttachmentList().size());
    }
	
	
	@Test
	public void testRetrieveRequevstAttachmentList_defect12557_SortingIssue()
			throws Exception {

		AttachmentContract contract = new AttachmentContract();
		contract.setIdentifier("1-NMCFPJ");
		contract.setIncrement(40);
		contract.setStartRecordNumber(0);
		contract.setNewQueryIndicator(true);
		contract.setSessionHandle(crmSessionHandle);

//		contract.setSortCriteria((Map<String, Object>) MiscTest.newHashMap(
//				"status", "DESCENDING"));
		
//		contract.setSortCriteria((Map<String, Object>) MiscTest.newHashMap(
//				"fileName", "ASCENDING"));
		
		contract.setSortCriteria((Map<String, Object>) MiscTest.newHashMap(
				"size", "ASCENDING"));
		
		AttachmentListResult result = service.retrieveRequestAttachmentList(contract);

		List<Attachment> list = result.getAttachmentList();

		System.out.println(result.getAttachmentList().size());
		System.out.println(result.getTotalCount());

		for (Attachment attachment : list) {
//			System.out.println(attachment.getFileName());
//			System.out.println(attachment.getStatus());
			System.out.println(attachment.getSize());
			System.out.println("------------------");
		}
	}
	
   @Test
    public void testRetrieveRequevstAttachmentList_defect12235() throws Exception {
    	AttachmentContract contract = new AttachmentContract();
    	contract.setIdentifier("1-IOI0EO");
    	contract.setIncrement(0);
    	contract.setStartRecordNumber(40);
    	contract.setNewQueryIndicator(true);
    	contract.setSortCriteria((Map<String, Object>) MiscTest.newHashMap("requestNumber", "DESCENDING"));  
    	contract.setFilterCriteria((Map<String, Object>) MiscTest.newHashMap("type", "Mass Update of HW Install"));  
    	contract.setSessionHandle(crmSessionHandle);
    	AttachmentListResult result = service.retrieveRequestAttachmentList(contract);
    	for (Attachment att : result.getAttachmentList()) {
    		System.out.println("SubArea: " + att.getSubArea());
    	}
    }
    
    @Test
    public void testRetrieveRequevstAttachmentList_defect12557() throws Exception {
    	AttachmentContract contract = new AttachmentContract();
    	contract.setIdentifier("1-NMCFPJ");
    	contract.setIncrement(0);
    	contract.setStartRecordNumber(40);
    	contract.setNewQueryIndicator(true);
    	contract.setSortCriteria((Map<String, Object>) MiscTest.newHashMap("requestNumber", "DESCENDING"));  
    	contract.setSessionHandle(crmSessionHandle);
    	AttachmentListResult result = service.retrieveRequestAttachmentList(contract);
    	for (Attachment att : result.getAttachmentList()) {
    		System.out.println("SubmittedOn: " + att.getSubmittedOn());
    		System.out.println("CompletedOn: " + att.getCompletedOn());
    	}
    }
    
    @Test
    public void testRetrieveRequevstAttachmentList_defect12269() throws Exception {
    	AttachmentContract contract = new AttachmentContract();
    	contract.setIdentifier("1-IOI0EO");
    	contract.setIncrement(0);
    	contract.setStartRecordNumber(40);
    	contract.setNewQueryIndicator(true);
    	contract.setSortCriteria((Map<String, Object>) MiscTest.newHashMap("requestNumber", "DESCENDING"));  
    	contract.setSessionHandle(crmSessionHandle);
    	AttachmentListResult result = service.retrieveRequestAttachmentList(contract);
    	for (Attachment att : result.getAttachmentList()) {
    		System.out.println("Status: " + att.getStatus());
    	}
    }
    
    @Test
    public void testRetrieveRequevstAttachmentList_NoData() throws Exception {
    	AttachmentContract contract = new AttachmentContract();
    	contract.setIdentifier("1-NMCFPJ");
    	contract.setIncrement(0);
    	contract.setStartRecordNumber(40);
    	contract.setNewQueryIndicator(true);
    	contract.setSortCriteria((Map<String, Object>) MiscTest.newHashMap("requestNumber", "DESCENDING"));  
    	contract.setSessionHandle(crmSessionHandle);
    	AttachmentListResult result = service.retrieveRequestAttachmentList(contract);
    	logger.debug("AttachmentList: " + result.getAttachmentList().size());
    }
    
    @Test
    public void testRetrieveRequevstAttachmentList_defect18330() throws Exception {
    	AttachmentContract contract = new AttachmentContract();
    	contract.setIdentifier("1-9LM1DLZ");
    	contract.setIncrement(0);
    	contract.setStartRecordNumber(40);
    	contract.setNewQueryIndicator(true);
    	contract.setSortCriteria((Map<String, Object>) MiscTest.newHashMap("requestNumber", "DESCENDING"));  
    	contract.setSessionHandle(crmSessionHandle);
    	AttachmentListResult result = service.retrieveRequestAttachmentList(contract);
    	logger.debug("AttachmentList: " + result.getAttachmentList().size());
    }
    
    @Test
    public void testRetrieveMassUploadTemplateList_defect12265() throws Exception {
    	MassUploadTemplateContract contract = new MassUploadTemplateContract();
    	contract.setStatus("Open");
    	contract.setMdmLevel("Global");
    	contract.setMdmId("151064821");
    	Map<String, Object> sortCriteria = new HashMap<String, Object>();
    	sortCriteria.put("createdDate", "DESCENDING");
		contract.setSortCriteria(sortCriteria );
		Map<String, Object> filterCriteria = new HashMap<String, Object>();
		filterCriteria.put("order.startDate", "12/31/2013 13:00:00");
		filterCriteria.put("order.endDate", "04/03/2014 13:00:00");
		contract.setFilterCriteria(filterCriteria );
    	MassUploadTemplateResult result = service.retrieveMassUploadTemplateList(contract);
    	for (MassUploadTemplateOrderLineItem lineItem : result.getOrderLineItemsList()) {
			System.out.println("OrderNumber: " + lineItem.getOrderNumber());
		}
    	System.out.println("size: " + result.getOrderLineItemsList().size());
    }
    
	
	
	@Test
	public void testRetrieveOrderList_defect_BDR14_06_01ServicecOrderGridLatencyIssue() throws Exception {
		
		long t = System.currentTimeMillis();
		 
		OrderListContract contract = new OrderListContract();
		contract.setStatus("Open");
		contract.setMdmLevel("Global");
		contract.setMdmId("519926455");
		contract.setEmployeeFlag(false);
		contract.setPendingDebriefFlag(false);
		contract.setNewQueryIndicator(true);
		contract.setStartRecordNumber(0);
		contract.setIncrement(40);
		contract.setSessionHandle(crmSessionHandle);

		Map<String, Object> filterCriteria = new HashMap<String, Object>();
		filterCriteria.put("order.startDate", "03/25/2014 08:00:00");
		filterCriteria.put("order.endDate", "04/25/2014 08:00:00");
		contract.setFilterCriteria(filterCriteria);

		Map<String, Object> sortCriteria = new HashMap<String, Object>();
		sortCriteria.put("createdDate", "DESCENDING");
		contract.setSortCriteria(sortCriteria);

		OrderListResult activityResult = service.retrieveOrderList(contract);

		List<Order> orderList = activityResult.getOrderList();
		int totalCount = activityResult.getTotalCount();

		System.out.println(orderList.size());
		System.out.println(totalCount);

		System.out.println("Total time: " + (System.currentTimeMillis() - t)/1000.0);
		   
		System.out.println("End");

	}
	
	@Test
	public void testRetrieveOrderList_defect_() throws Exception {
		OrderListContract contract = new OrderListContract();
		contract.setStatus("Open");
		contract.setMdmLevel("Global");
		contract.setMdmId("118552798");
		contract.setEmployeeFlag(false);
		contract.setPendingDebriefFlag(false);
		contract.setNewQueryIndicator(true);
		contract.setStartRecordNumber(0);
		contract.setIncrement(40);
		contract.setSessionHandle(crmSessionHandle);
		
		Map<String, Object> filterCriteria = new HashMap<String, Object>();
		filterCriteria.put("order.startDate", "06/07/2015 18:30:00");
		filterCriteria.put("order.endDate", "07/08/2015 18:30:00");
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

}
