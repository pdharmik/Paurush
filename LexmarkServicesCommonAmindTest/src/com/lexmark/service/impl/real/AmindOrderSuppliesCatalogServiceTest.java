package com.lexmark.service.impl.real;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.apache.commons.lang.builder.CompareToBuilder;
import org.junit.Test;

import com.lexmark.contract.CatalogListContract;
import com.lexmark.domain.ListOfValues;
import com.lexmark.domain.OrderPart;
import com.lexmark.result.CatalogListResult;
import com.lexmark.service.impl.real.domain.OrderPartDo;
import com.lexmark.util.LangUtil;

/**
 * @author vpetruchok
 * @version 1.0, 2012-09-14
 */
public class AmindOrderSuppliesCatalogServiceTest extends AmindServiceTest {

	AmindOrderSuppliesCatalogService service = new AmindOrderSuppliesCatalogService();

	@Test
	public void testRetrieveCatalogList_QA() throws Exception {
		CatalogListContract contract = new CatalogListContract();
		contract.setSessionHandle(crmSessionHandle);
		contract.setAgreementId("1-41HXJGF");
		contract.setStartRecordNumber(0);
		contract.setIncrement(10);
		contract.setNewQueryIndicator(false);

		CatalogListResult result = service.retrieveCatalogList(contract);
		MiscTest.print("[lovList] ", result.getLovList());
		MiscTest.print("[partsList] ", result.getPartsList());
		assertEquals(contract.getIncrement(), result.getPartsList().size());
		assertEquals(0, result.getTotalCount());
	}

	@Test
	public void testRetrieveCatalogList_QA_Defect3166() throws Exception {
		CatalogListContract contract = new CatalogListContract();
		contract.setSessionHandle(crmSessionHandle);
		contract.setAgreementId("1-41HXJGF");
		contract.setStartRecordNumber(0);
		contract.setIncrement(10);
		contract.setNewQueryIndicator(false);
		contract.setPartNumber("   C9206YH   ");

		CatalogListResult result = service.retrieveCatalogList(contract);
		MiscTest.print("[partsList] ", result.getPartsList());
		assertTrue(result.getPartsList().size() > 0);
	}

	@Test
	public void testRetrieveCatalogList_QA_Defect4676() throws Exception {
		CatalogListContract contract = new CatalogListContract();
		contract.setSessionHandle(crmSessionHandle);
		contract.setAgreementId("1-42PVCHI");
		contract.setStartRecordNumber(0);
		contract.setIncrement(10);
		contract.setNewQueryIndicator(false);
		contract.setPartNumber("40x7562");

		CatalogListResult result = service.retrieveCatalogList(contract);
		MiscTest.print("[partsList] ", result.getPartsList());
		assertTrue(result.getPartsList().size() > 0);
	}

	@Test
	public void testRetrieveCatalogList_QA_Defect4773() throws Exception {
		CatalogListContract contract = new CatalogListContract();
		contract.setSessionHandle(crmSessionHandle);
		contract.setAgreementId("1-4I9QE91");
		contract.setStartRecordNumber(0);
		contract.setIncrement(10);
		// contract.setNewQueryIndicator(false);
		contract.setNewQueryIndicator(true);
		contract.setPartNumber("40X2665");

		CatalogListResult result = service.retrieveCatalogList(contract);
		Collections.sort(result.getPartsList(), new Comparator<OrderPart>() {

			@Override
			public int compare(OrderPart o1, OrderPart o2) {
				return LangUtil.equal(o1.getPartNumber(), o2.getPartNumber()) ? 0
						: o1.getPartNumber().compareTo(o2.getPartNumber());
			}
		});
		MiscTest.print("[partsList] ", result.getPartsList(), "partNumber",
				"description", "productId", "catalogId", "partType", "yield",
				"model");
		assertTrue(result.getPartsList().size() > 0);
	}

	@Test
	public void queryDefect4773() throws Exception {
		List<OrderPartDo> orderParts = MiscTest
				.sampleSiebelQuery(
						OrderPartDo.class,
						// "[LXK MPS Agreement Id] = '1-42PVCHI' AND [LXK MPS Supply Part#] ~= '40x7562' AND [LXK MPS Implicit Flag] <> 'Y' AND [LXK MPS Display On Portal] = 'Y'"
						"[LXK MPS Supply Part#] ~= '40X2665' AND [LXK MPS Implicit Flag] <> 'Y' AND [LXK MPS Display On Portal] = 'Y'",
						40);
		Collections.sort(orderParts, new Comparator<OrderPartDo>() {
			@Override
			public int compare(OrderPartDo o1, OrderPartDo o2) {
				return new CompareToBuilder().append(o1.getAgreementId(),
						o2.getAgreementId()).toComparison();
			}
		});

		System.out.println("===========");
		MiscTest.print(orderParts, "agreementId", "partNumber", "description",
				"productModel", "partType", "yield");
	}

	@Test
	public void queryDefect4576() throws Exception {
		MiscTest.sampleSiebelQuery(
				OrderPartDo.class,
				// "[LXK MPS Agreement Id] = '1-42PVCHI' AND [LXK MPS Supply Part#] ~= '40x7562' AND [LXK MPS Implicit Flag] <> 'Y' AND [LXK MPS Display On Portal] = 'Y'"
				"[LXK MPS Supply Part#] ~= '40x7562' AND [LXK MPS Implicit Flag] <> 'Y' AND [LXK MPS Display On Portal] = 'Y'",
				40);
	}

	@Test
	public void testRetrieveCatalogList_QA_withNewQueryIndicator()
			throws Exception {
		CatalogListContract contract = new CatalogListContract();
		contract.setSessionHandle(crmSessionHandle);
		contract.setAgreementId("1-5UNNARF");
		contract.setStartRecordNumber(0);
		contract.setIncrement(40);
		contract.setNewQueryIndicator(true);
		contract.setHardwareAccessoriesFlag(true);

		CatalogListResult result = service.retrieveCatalogList(contract);

		System.out.println(result.getAccessoriesList().size());
		System.out.println(result.getTotalCount());

		// for (OrderPart orderPart : result.getAccessoriesList()) {
		// System.out.println(orderPart.getSupplyId());
		// }
		//
		// MiscTest.print("[lovList] ", result.getLovList());
		// MiscTest.print("[partsList] ", result.getPartsList());
		// assertEquals(contract.getIncrement(), result.getPartsList().size());
		// assertEquals(30, result.getTotalCount());
	}

	/**
	 * @see AmindContractedServiceRequestServiceTest#testRetrieveSiebelAccountList_QA_Defect6362()
	 */
	@Test
	public void testRetrieveCatalogList_QA_Defect6362() throws Exception {
		CatalogListContract contract = new CatalogListContract();
		contract.setSessionHandle(crmSessionHandle);
		contract.setAgreementId("1-6R2PNQ");
		contract.setStartRecordNumber(0);
		contract.setIncrement(5);
		contract.setNewQueryIndicator(true);

		CatalogListResult result = service.retrieveCatalogList(contract);
		MiscTest.print("[lovList] ", result.getLovList());
		MiscTest.print("[partsList] ", result.getPartsList());
		// assertEquals(contract.getIncrement(), result.getPartsList().size());
		// assertEquals(30, result.getTotalCount());
	}

	@Test
	public void testRetrieveCatalogList_QA_Defect6362_query() throws Exception {
		MiscTest.sampleSiebelQuery(
				OrderPartDo.class,
				// "[LXK MPS Agreement Id] = '1-42PVCHI' AND [LXK MPS Display On Portal] = 'Y'"
				"[LXK MPS Agreement Id] <> '' AND [LXK MPS Display On Portal] = 'Y'",
				100);
	}

	@Test
	public void queryOrderPardDo() throws Exception {
		MiscTest.sampleSiebelQuery(OrderPartDo.class,
				"[agreementId] <> '' AND [portalFlag] = 'Y'", 5);
	}

	@Test
	public void testRetrieveCatalogFieldList() throws Exception {
		CatalogListContract contract = new CatalogListContract();
		contract.setSessionHandle(crmSessionHandle);
		contract.setAgreementId("1-41HXJGF");
		contract.setStartRecordNumber(0);
		contract.setIncrement(10);
		contract.setNewQueryIndicator(false);
		contract.setProductType("     mock-productType    \n");
		contract.setProductModel("   mock-productModel    \r");

		CatalogListResult result = service.retrieveCatalogFieldList(contract);
		MiscTest.print("[partsList] ", result.getPartsList());
	}

	@Test
	public void testRetrieveCatalogList_MPS_QA_Defect7846() throws Exception {
		CatalogListContract contract = new CatalogListContract();
		contract.setSoldToNumber("0000107608");
		contract.setSessionHandle(crmSessionHandle);
		contract.setAgreementId("1-5UNNARF");
		contract.setHardwareFlag(false);
		contract.setPaymentType("Ship and Bill");
		contract.setHardwareSuppliesFlag(false);
		contract.setHardwareAccessoriesFlag(false);
		contract.setProductType("Laser");
		contract.setProductModel("T652dn");
		contract.setPortalFlag(false);
		contract.setCatalogFlag(false);
		contract.setNewQueryIndicator(false);
		contract.setStartRecordNumber(0);
		contract.setIncrement(0);

		CatalogListResult result = service.retrieveCatalogList(contract);
		System.out.println(result.getTotalCount());
		System.out.println(result.getPartsList().size());
		// MiscTest.print("[partsList] ", result.getPartsList());
		// assertTrue(result.getPartsList().size() > 0);
	}

	@Test
	public void testRetrieveCatalogFieldList_MPS_QA_defect7825()
			throws Exception {
		CatalogListContract contract = new CatalogListContract();
		contract.setSessionHandle(crmSessionHandle);
		contract.setAgreementId("1-5UNNARF");
		contract.setStartRecordNumber(0);
		contract.setIncrement(0);
		contract.setNewQueryIndicator(false);
		contract.setSoldToNumber("0000107608");
		contract.setPaymentType("Ship and Bill");
		// contract.setProductType("Laser");
		// contract.setHardwareFlag(true);

		CatalogListResult result = service.retrieveCatalogFieldList(contract);
		MiscTest.print("[partsList] ", result.getPartsList());
	}

	@Test
	public void testRetrieveCatalogList_defect8052() throws Exception {
		CatalogListContract contract = new CatalogListContract();
		// contract.setSoldToNumber("0000107608");
		// contract.setSessionHandle(crmSessionHandle);
		// contract.setAgreementId("1-5UNNARF");
		// contract.setHardwareFlag(false);
		// contract.setPaymentType("Ship and Bill");
		// contract.setHardwareSuppliesFlag(false);
		// contract.setHardwareAccessoriesFlag(true);
		// contract.setProductType("Laser");
		// contract.setProductModel("T652dn");
		// contract.setPortalFlag(false);
		// contract.setCatalogFlag(false);
		// contract.setNewQueryIndicator(false);
		// contract.setStartRecordNumber(0);
		// contract.setIncrement(0);

		contract.setAgreementId("1-600MDLS");
		contract.setPortalFlag(true);
		contract.setSessionHandle(crmSessionHandle);

		CatalogListResult result = service.retrieveCatalogList(contract);

		System.out.println(result.getTotalCount());
		System.out.println(result.getPartsList().size());

		// MiscTest.print("[partsList] ", result.getPartsList());
		// assertTrue(result.getPartsList().size() > 0);
	}

	@Test
	public void testRetrieveCatalogFieldListFailure() throws Exception {
		CatalogListContract contract = new CatalogListContract();
		contract.setHardwareFlag(false);
		contract.setHardwareSuppliesFlag(false);
		contract.setHardwareAccessoriesFlag(false);
		contract.setPortalFlag(false);
		contract.setCatalogFlag(false);
		contract.setIncrement(0);
		contract.setStartRecordNumber(0);
		contract.setNewQueryIndicator(false);
		// contract.setAgreementId("1-41HXJGF");

		try {
			CatalogListResult result = service
					.retrieveCatalogFieldList(contract);
		} catch (Exception e) {
			System.out.println(e);
		}

		System.out.println("End");
	}

	@Test
	public void testRetrieveCatalog_defect8384() throws Exception {
		CatalogListContract contract = new CatalogListContract();
		contract.setSessionHandle(crmSessionHandle);
		contract.setPaymentType("Annuity Billing");
		contract.setSoldToNumber("0000222911");
		contract.setAgreementId("1-5UNNARF");
		contract.setHardwareSuppliesFlag(false);
		contract.setHardwareAccessoriesFlag(false);
		contract.setHardwareFlag(false);
		contract.setProductType("Laser");
		contract.setPortalFlag(false);
		contract.setCatalogFlag(false);
		contract.setIncrement(0);
		contract.setStartRecordNumber(0);
		contract.setNewQueryIndicator(false);
		contract.setProductModel("T652dn");
		contract.setContractNumber("0005001212");

		CatalogListResult result = service.retrieveCatalogFieldList(contract);

		List<ListOfValues> lovList = result.getLovList();

		System.out.println("Size: " + lovList.size());

		for (ListOfValues listOfValues : lovList) {
			System.out.println("Name: " + listOfValues.getName());
			System.out.println("Value: " + listOfValues.getValue());
		}
		System.out.println("End");
	}

	@Test
	public void testRetrieveCatalogList_2() throws Exception {
		CatalogListContract contract = new CatalogListContract();
		contract.setSessionHandle(crmSessionHandle);
		contract.setPaymentType("Annuity Billing");
		contract.setSoldToNumber("0000222911");
		contract.setAgreementId("1-5UNNARF");
		contract.setHardwareSuppliesFlag(false);
		contract.setHardwareAccessoriesFlag(false);
		contract.setHardwareFlag(true);
		contract.setProductType("Laser");
		contract.setPortalFlag(false);
		contract.setCatalogFlag(false);
		contract.setIncrement(0);
		contract.setStartRecordNumber(0);
		contract.setNewQueryIndicator(false);

		CatalogListResult result = service.retrieveCatalogList(contract);
		System.out.println(result.getPartsList().size());
		// System.out.println("Agreement id: " + result.getAgreementId());
		System.out.println("End");
	}

	@Test
	public void testRetrieveCatalogFieldList_ConsumablesSearch2()
			throws Exception {

		CatalogListContract contract = new CatalogListContract();
		contract.setPaymentType("Ship and Bill");
		contract.setSoldToNumber("0000105443");
		contract.setAgreementId("1-2NYIHIO");
		contract.setHardwareSuppliesFlag(false);
		contract.setHardwareAccessoriesFlag(false);
		contract.setContractNumber("0005001451");
		contract.setHardwareFlag(false);
		contract.setPortalFlag(false);
		contract.setCatalogFlag(false);
		contract.setIncrement(0);
		contract.setStartRecordNumber(0);
		contract.setNewQueryIndicator(false);
		contract.setSessionHandle(crmSessionHandle);

		CatalogListResult result = service.retrieveCatalogFieldList(contract);

		System.out.println("Size: " + result.getLovList().size());
		System.out.println("End");
	}

	@Test
	public void testRetrieveCatalogList_Failing() throws Exception {

		CatalogListContract contract = new CatalogListContract();
		contract.setPaymentType("Annuity Billing");
		contract.setSoldToNumber("0000105443");
		contract.setAgreementId("1-2NYIHIO");
		contract.setHardwareSuppliesFlag(true);
		contract.setHardwareAccessoriesFlag(false);
		contract.setContractNumber("0005001451");
		contract.setHardwareFlag(true);
		contract.setPortalFlag(false);
		contract.setCatalogFlag(false);
		contract.setIncrement(0);
		contract.setStartRecordNumber(0);
		contract.setNewQueryIndicator(false);
		contract.setProductType("Inkjet");
		contract.setProductModel("X364dn");
		contract.setSessionHandle(crmSessionHandle);

		CatalogListResult result = service.retrieveCatalogList(contract);

		System.out.println("Size: " + result.getPartsList().size());
		System.out.println("End");
	}

	@Test
	public void testRetrieveCatalogListWithContractNumber_HadrwareSuppliesList_Failing()
			throws Exception {

		CatalogListContract contract = new CatalogListContract();
		contract.setPaymentType("Annuity Billing");
		contract.setSoldToNumber("0000105443");
		contract.setAgreementId("1-2NYIHIO");
		contract.setHardwareSuppliesFlag(false);
		contract.setHardwareAccessoriesFlag(false);
		contract.setContractNumber("0005001451");
		contract.setHardwareFlag(false);
		contract.setPortalFlag(false);
		contract.setCatalogFlag(false);
		contract.setIncrement(0);
		contract.setStartRecordNumber(0);
		contract.setNewQueryIndicator(false);
		contract.setProductType("Inkjet");
		contract.setProductModel("X364dn");
		contract.setSessionHandle(crmSessionHandle);

		CatalogListResult result = service
				.retrieveCatalogListWithContractNumber(contract);

		// System.out.println("---------------------");
		// System.out.println(result.getSuppliesList().size());
		// System.out.println(result.getAccessoriesList().size());
		System.out.println(result.getPartsList().size());
		// System.out.println(result.getLovList().size());
		// System.out.println("---------------------");

		List<OrderPart> partsList = result.getPartsList();

		for (OrderPart orderPart : partsList) {
			System.out.println("New field: "
					+ orderPart.getProviderOrderNumber());
		}

		System.out.println("End");
	}
	
	@Test
	public void testRetrieveCatalogListWithContractNumber_defect14511()	throws Exception {
		CatalogListContract contract = new CatalogListContract();
		contract.setPaymentType("Ship and Bill");
		contract.setSoldToNumber("0000180030");
		contract.setAgreementId("1-80OJBVX");
		contract.setHardwareSuppliesFlag(false);
		contract.setHardwareAccessoriesFlag(true);
		contract.setContractNumber("0005005880");
		contract.setHardwareFlag(true);
		contract.setPortalFlag(false);
		contract.setCatalogFlag(false);
		contract.setIncrement(0);
		contract.setStartRecordNumber(0);
		contract.setNewQueryIndicator(true);
		contract.setProductType("Laser");
		contract.setProductModel("W850n");
		contract.setSessionHandle(crmSessionHandle);
		
		CatalogListResult result = service.retrieveCatalogListWithContractNumber(contract);
		
		List<OrderPart> partsList = result.getPartsList();
		System.out.println("End");
	}
	
	@Test
	public void testRetrieveCatalogListWithContractNumber_searchspec()	throws Exception {
		CatalogListContract contract = new CatalogListContract();
		contract.setPaymentType("Annuity Billing");
		contract.setSoldToNumber("0000215238");
		contract.setAgreementId("1-4NW9QDF");
		contract.setHardwareSuppliesFlag(false);
		contract.setHardwareAccessoriesFlag(true);
		contract.setContractNumber("0005006817");
		contract.setHardwareFlag(true);
		contract.setPortalFlag(false);
		contract.setCatalogFlag(false);
		contract.setIncrement(0);
		contract.setStartRecordNumber(0);
		contract.setNewQueryIndicator(true);
		contract.setProductType("Laser");
		contract.setProductModel("SP4400S");
		contract.setSessionHandle(crmSessionHandle);
		CatalogListResult result = service.retrieveCatalogListWithContractNumber(contract);
		System.out.println("End");
	}

	@Test
	public void testRetrieveCatalogListWithContractNumber_defect9033()
			throws Exception {

		CatalogListContract contract = new CatalogListContract();
		contract.setSessionHandle(crmSessionHandle);
		contract.setPaymentType("Consumable Purchase");
		contract.setSoldToNumber("0000319381");
		contract.setAgreementId("1-69NLZIF");
		contract.setHardwareSuppliesFlag(false);
		contract.setHardwareAccessoriesFlag(false);
		contract.setContractNumber("0005001453");
		contract.setHardwareFlag(false);
		contract.setPortalFlag(false);
		contract.setCatalogFlag(true);
		Map<String, Object> sortCriteria = new HashMap<String, Object>();
		sortCriteria.put("partImage", "ASCENDING");
		contract.setSortCriteria(sortCriteria);
		contract.setIncrement(40);
		contract.setStartRecordNumber(0);
		contract.setNewQueryIndicator(true);

		CatalogListResult result = service
				.retrieveCatalogListWithContractNumber(contract);

		System.out.println("---------------------");
		// System.out.println("Supplies list: " +
		// result.getSuppliesList().size());
		// System.out.println("Accessories list: " +
		// result.getAccessoriesList().size());
		System.out.println("Catalog list: " + result.getPartsList().size());
		System.out.println("---------------------");

		List<OrderPart> partsList = result.getPartsList();

		for (OrderPart orderPart : partsList) {
			System.out.println("Description: " + orderPart.getDescription());
			System.out.println("Part number: " + orderPart.getPartNumber());
			System.out.println("Product model: " + orderPart.getModel());
			System.out.println("---------------");
		}

		// for (OrderPart orderPart : partsList) {
		// System.out.println("New field: " +
		// orderPart.getProviderOrderNumber());
		// }

		System.out.println("End");
	}

	@Test
	public void testRetrieveCatalogList_defect9247() throws Exception {
		CatalogListContract contract = new CatalogListContract();
		contract.setSessionHandle(crmSessionHandle);
		contract.setAgreementId("1-2CDY4HA");
		contract.setPortalFlag(false);

		Map<String, Object> sortCriteria = new HashMap<String, Object>();
		sortCriteria.put("partImage", "ASCENDING");
		contract.setSortCriteria(sortCriteria);

		contract.setNewQueryIndicator(true);
		contract.setStartRecordNumber(0);
		contract.setIncrement(40);

		CatalogListResult result = service.retrieveCatalogList(contract);

		System.out.println(result.getTotalCount());
		System.out.println(result.getPartsList().size());

		// MiscTest.print("[partsList] ", result.getPartsList());
		// assertTrue(result.getPartsList().size() > 0);
	}

	@Test
	public void testRetrieveCatalog_Accessories() throws Exception {
		CatalogListContract contract = new CatalogListContract();
		contract.setPaymentType("Annuity Billing");
		contract.setSoldToNumber("0000198238");
		contract.setAgreementId("1-6DL8ZYF");
		contract.setHardwareSuppliesFlag(false);
		contract.setHardwareAccessoriesFlag(false);
		contract.setContractNumber("0005001808");
		contract.setHardwareFlag(true);
		contract.setPortalFlag(false);
		contract.setCatalogFlag(false);
		contract.setIncrement(0);
		contract.setStartRecordNumber(0);
		contract.setNewQueryIndicator(false);

		CatalogListResult result = service.retrieveCatalogFieldList(contract);

		List<OrderPart> partsList = result.getPartsList();

		System.out.println(partsList == null ? "Null" : partsList.size());
	}

	@Test
	public void testRetrieveCatalog_ProductModelAccessories() throws Exception {
		CatalogListContract contract = new CatalogListContract();
		contract.setPaymentType("Annuity Billing");
		contract.setSoldToNumber("0000198238");
		contract.setAgreementId("1-6DL8ZYF");
		contract.setHardwareSuppliesFlag(false);
		contract.setHardwareAccessoriesFlag(false);
		contract.setContractNumber("0005001808");
		contract.setHardwareFlag(true);
		contract.setProductType("Laser");
		contract.setPortalFlag(false);
		contract.setCatalogFlag(false);
		contract.setIncrement(0);
		contract.setStartRecordNumber(0);
		contract.setNewQueryIndicator(false);

		CatalogListResult result = service.retrieveCatalogFieldList(contract);

		List<OrderPart> partsList = result.getPartsList();

		System.out.println(partsList == null ? "Null" : partsList.size());
	}

	@Test
	public void testRetrieveCatalogFieldListFailure_defect9340_newIssue()
			throws Exception {
		CatalogListContract contract = new CatalogListContract();
		contract.setPaymentType("Ship and Bill");
		contract.setSoldToNumber("0000198238");
		contract.setAgreementId("1-6DL8ZYF");
		contract.setHardwareSuppliesFlag(false);
		contract.setHardwareAccessoriesFlag(false);
		contract.setContractNumber("0005001808");
		contract.setHardwareFlag(true);
		contract.setPortalFlag(false);
		contract.setCatalogFlag(false);
		contract.setIncrement(0);
		contract.setStartRecordNumber(0);
		contract.setNewQueryIndicator(false);
		contract.setSessionHandle(crmSessionHandle);

		CatalogListResult result = service.retrieveCatalogFieldList(contract);
		
		List<ListOfValues> lovList = result.getLovList();
		
		System.out.println(lovList.size());
		
		for (ListOfValues listOfValues : lovList) {
			System.out.println(listOfValues.getName());
			System.out.println(listOfValues.getValue());
		}
		
		System.out.println("End");
	}
	
	@Test
	public void testRetrieveCatalogFieldListFailure_defect9732()
			throws Exception {
		CatalogListContract contract = new CatalogListContract();
		contract.setSessionHandle(crmSessionHandle);
		contract.setSoldToNumber("0000223612");
		contract.setAgreementId("1-6NDOSU5");
		contract.setHardwareFlag(true);
		contract.setPaymentType("Delayed Purchase");
		contract.setHardwareSuppliesFlag(false);
		contract.setHardwareAccessoriesFlag(true);
		contract.setContractNumber("0005002035");
		contract.setProductType("Laser");
		contract.setProductModel("X792dte");
		contract.setPortalFlag(false);
		contract.setCatalogFlag(false);
		contract.setIncrement(0);
		contract.setStartRecordNumber(0);
		contract.setNewQueryIndicator(true);

		CatalogListResult result = service.retrieveCatalogListWithContractNumber(contract);
		
		List<ListOfValues> lovList = result.getLovList();
		
		System.out.println("Null: " + lovList == null);
		
		
		List<OrderPart> partsList = result.getPartsList();
		List<OrderPart> accessoriesList = result.getAccessoriesList();
		List<OrderPart> suppliesList = result.getSuppliesList();
		
		System.out.println("Parts list: " + (partsList == null ? "null" : partsList.size()));
		System.out.println("Accessories list: " + (accessoriesList == null ? "null" : accessoriesList.size()));		
		System.out.println("Supplies list: " + (suppliesList == null ? "null" : suppliesList.size()));	
		
		System.out.println("End");
	}
	
	
	@Test
	public void testRetrieveCatalog_defect9455() throws Exception {
		CatalogListContract contract = new CatalogListContract();
//		contract.setSessionHandle(crmSessionHandle);
		contract.setPaymentType("Ship and Bill");
		contract.setSoldToNumber("0000324040");
		contract.setAgreementId("1-2HKZEOQ");
		contract.setHardwareSuppliesFlag(false);
		contract.setHardwareAccessoriesFlag(false);
		contract.setHardwareFlag(true);
//		contract.setProductType("Laser");
		contract.setPortalFlag(false);
		contract.setCatalogFlag(false);
		contract.setIncrement(0);
		contract.setStartRecordNumber(0);
		contract.setNewQueryIndicator(false);
//		contract.setProductModel("T652dn");
		contract.setContractNumber("0005001856");

		CatalogListResult result = service.retrieveCatalogFieldList(contract);

		List<ListOfValues> lovList = result.getLovList();

		System.out.println("Size: " + lovList.size());

		for (ListOfValues listOfValues : lovList) {
			System.out.println("Name: " + listOfValues.getName());
			System.out.println("Value: " + listOfValues.getValue());
		}
		System.out.println("End");
	}
	
	
	
	
	@Test
	public void testRetrieveCatalogListWithContractNumber_defect10031() throws Exception {

		CatalogListContract contract = new CatalogListContract();
		contract.setSessionHandle(crmSessionHandle);
		contract.setPaymentType("Ship and Bill");
		contract.setSoldToNumber("0000227605");
		contract.setAgreementId("1-793S2JE");
		contract.setHardwareSuppliesFlag(false);
		contract.setHardwareAccessoriesFlag(false);
		contract.setContractNumber("0005002121");
		contract.setHardwareFlag(false);
		contract.setProductType("Laser");
		contract.setProductModel("X792dte");
		contract.setPortalFlag(false);
		contract.setCatalogFlag(true);
		
		Map<String, Object> sortCriteria = new HashMap<String, Object>();
		sortCriteria.put("partImage", "ASCENDING");
		contract.setSortCriteria(sortCriteria);
		
		contract.setIncrement(40);
		contract.setStartRecordNumber(0);
		contract.setNewQueryIndicator(true);

		CatalogListResult result = service.retrieveCatalogListWithContractNumber(contract);
		
		List<OrderPart> parts = result.getPartsList();
		
		System.out.println("Parts size: " + (parts == null ? "null" : parts.size()));
		System.out.println("Total count: " + result.getTotalCount());
		
		System.out.println("End");
	}
	
	
	
	@Test
    public void testRetrieveCatalogList_defect9455_withContractNumber() throws Exception {
        CatalogListContract contract = new CatalogListContract();
        contract.setSessionHandle(crmSessionHandle);

		contract.setPaymentType("Ship and Bill");
		// contract.setPartType("Service Parts");
		contract.setSoldToNumber("0000324040");
		contract.setAgreementId("1-2HKZEOQ");
		contract.setHardwareFlag(true);
		contract.setContractNumber("0005001856");
		contract.setHardwareSuppliesFlag(false);
		contract.setHardwareAccessoriesFlag(false);
		contract.setPortalFlag(false);
		contract.setCatalogFlag(false);
		contract.setProductType("Laser");
		contract.setProductModel("SPKM0014 ");
		contract.setStartRecordNumber(0);
		contract.setIncrement(100);
		contract.setNewQueryIndicator(true);

		CatalogListResult result = service
				.retrieveCatalogListWithContractNumber(contract);

		System.out.println(result.getTotalCount());
		System.out.println(result.getPartsList().size());

    }
	
	
	@Test
	public void testRetrieveCatalogFieldList_defect10341_ProductModel()
			throws Exception {
		
		ExecutorService executor = Executors.newFixedThreadPool(2);
		List<Future<?>> list = new ArrayList<Future<?>>();
		
		list.add(executor.submit(new Runnable() {

			@Override
			public void run() {
				
				CatalogListContract contract = new CatalogListContract();
				contract.setSoldToNumber("0000216725");
				contract.setAgreementId("1-77WNASE");
				contract.setHardwareFlag(false);
				contract.setPaymentType("Usage Based Billing");
				contract.setHardwareSuppliesFlag(false);
				contract.setHardwareAccessoriesFlag(false);
				contract.setContractNumber("0005004510");
				contract.setProductType("Laser");
				contract.setPortalFlag(false);
				contract.setCatalogFlag(false);
				contract.setIncrement(0);
				contract.setStartRecordNumber(0);
				contract.setNewQueryIndicator(false);

				CatalogListResult result = new CatalogListResult();
				try {
					result = service.retrieveCatalogFieldList(contract);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				List<ListOfValues> lovList = result.getLovList();
				
				System.out.println(lovList.size());
				
				for (ListOfValues listOfValues : lovList) {
					System.out.println(listOfValues.getName());
					System.out.println(listOfValues.getValue());
				}
				
			}
		}));

		list.add(executor.submit(new Runnable() {

			@SuppressWarnings("unchecked")
			@Override
			public void run() {

				CatalogListContract contract = new CatalogListContract();
				contract.setSoldToNumber("0000216725");
				contract.setSessionHandle(crmSessionHandle);
				contract.setAgreementId("1-77WNASE");
				contract.setHardwareFlag(false);
				contract.setPaymentType("Usage Based Billing");
				contract.setHardwareSuppliesFlag(false);
				contract.setHardwareAccessoriesFlag(false);
				contract.setContractNumber("0005004510");
				contract.setProductType("Laser");
				contract.setPortalFlag(false);
				contract.setCatalogFlag(true);
				contract.setIncrement(40);
				contract.setStartRecordNumber(0);
				contract.setNewQueryIndicator(true);
				contract.setSortCriteria((Map<String, Object>) new HashMap<String, Object>().put("partImage", "ASCENDING"));

				CatalogListResult result = new CatalogListResult();
				try {
					result = service.retrieveCatalogListWithContractNumber(contract);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				List<OrderPart> orderParts = result.getPartsList();
				
				System.out.println("Size: " + orderParts.size());
				System.out.println("Total count: " + result.getTotalCount());
				
				for (OrderPart part : orderParts) {
					System.out.println("Model: " + part.getModel());
				}
				
			}
		}));


		for (Future<?> future : list) {
			future.get();
		}
		
		executor.shutdown();
		
		System.out.println("End");
	}
	
	
	
	@Test
	public void testRetrieveCatalogList_defect10663() throws Exception {
		CatalogListContract contract = new CatalogListContract();
		contract.setSessionHandle(crmSessionHandle);
		contract.setAgreementId("1-4O0PJGX");
		contract.setHardwareFlag(false);
		contract.setProductModel("T654dn");
		contract.setHardwareSuppliesFlag(false);
		contract.setHardwareAccessoriesFlag(false);
		contract.setPartType("Black Cartridge");
		contract.setProductType("Laser");
		contract.setPortalFlag(false);
		contract.setCatalogFlag(true);

		Map<String, Object> sortCriteria = new HashMap<String, Object>();
		sortCriteria.put("partImage", "ASCENDING");
		contract.setSortCriteria(sortCriteria);

		contract.setNewQueryIndicator(true);
		contract.setStartRecordNumber(0);
		contract.setIncrement(40);

		CatalogListResult result = service.retrieveCatalogList(contract);

		System.out.println(result.getTotalCount());
		System.out.println(result.getPartsList().size());
		
		for (OrderPart  part : result.getPartsList()) {
			System.out.println("Part type: " + part.getPartType()) ;
			System.out.println("Part number: " + part.getPartNumber());
		}
	}
	
	
	@Test
	public void testRetrieveCatalogListWithContractNumber_defect10793() throws Exception {

		CatalogListContract contract = new CatalogListContract();
		contract.setSessionHandle(crmSessionHandle);
		contract.setPaymentType("Delayed Purchase");
		contract.setSoldToNumber("0000216725");
		contract.setAgreementId("1-77WNASE");
		contract.setHardwareSuppliesFlag(false);
		contract.setHardwareAccessoriesFlag(true);
		contract.setContractNumber("0005004510");
		contract.setHardwareFlag(true);
		contract.setProductType("Laser");
		contract.setProductModel("W850n");
		contract.setPortalFlag(false);
		contract.setCatalogFlag(false);
		contract.setIncrement(0);
		contract.setStartRecordNumber(0);
		contract.setNewQueryIndicator(true);

		CatalogListResult result = service.retrieveCatalogListWithContractNumber(contract);
		
		List<OrderPart> accessories = result.getAccessoriesList();
		
		System.out.println("Parts size: " + (accessories == null ? "null" : accessories.size()));
		System.out.println("Total count: " + result.getTotalCount());
		
		for (OrderPart orderPart : accessories) {
			System.out.println("Part number: " + orderPart.getPartNumber());
		}
		
		System.out.println("End");
	}
	
	
	
	@Test
	public void testRetrieveCatalogFieldList_defect10341_ProductType()
			throws Exception {
		
		ExecutorService executor = Executors.newFixedThreadPool(2);
		List<Future<?>> list = new ArrayList<Future<?>>();
		
		list.add(executor.submit(new Runnable() {

			@Override
			public void run() {
				
				CatalogListContract contract = new CatalogListContract();
				contract.setSoldToNumber("0000217139");
				contract.setAgreementId("1-784H5UU");
				contract.setHardwareFlag(false);
				contract.setPaymentType("Usage Based Billing");
				contract.setHardwareSuppliesFlag(false);
				contract.setHardwareAccessoriesFlag(false);
				contract.setContractNumber("0005004544");
				contract.setPortalFlag(false);
				contract.setCatalogFlag(false);
				contract.setIncrement(0);
				contract.setStartRecordNumber(0);
				contract.setNewQueryIndicator(false);

				CatalogListResult result = new CatalogListResult();
				try {
					result = service.retrieveCatalogFieldList(contract);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				List<ListOfValues> lovList = result.getLovList();
				
				System.out.println("LOV list size: " + lovList.size());
				
				for (ListOfValues listOfValues : lovList) {
					System.out.println(listOfValues.getName());
					System.out.println(listOfValues.getValue());
				}
				
			}
		}));

		list.add(executor.submit(new Runnable() {

			@SuppressWarnings("unchecked")
			@Override
			public void run() {

				CatalogListContract contract = new CatalogListContract();
				contract.setSoldToNumber("0000217139");
				contract.setSessionHandle(crmSessionHandle);
				contract.setAgreementId("1-784H5UU");
				contract.setHardwareFlag(false);
				contract.setPaymentType("Usage Based Billing");
				contract.setHardwareSuppliesFlag(false);
				contract.setHardwareAccessoriesFlag(false);
				contract.setContractNumber("0005004544");
				contract.setPortalFlag(false);
				contract.setCatalogFlag(true);
				contract.setIncrement(40);
				contract.setStartRecordNumber(0);
				contract.setNewQueryIndicator(true);
				contract.setSortCriteria((Map<String, Object>) new HashMap<String, Object>().put("partImage", "ASCENDING"));

				CatalogListResult result = new CatalogListResult();
				try {
					result = service.retrieveCatalogListWithContractNumber(contract);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				List<OrderPart> orderParts = result.getPartsList();
				
				System.out.println("Catalog list size: " + orderParts.size());
				System.out.println("Total count: " + result.getTotalCount());
				
				for (OrderPart part : orderParts) {
					System.out.println("Model: " + part.getModel());
				}
				
			}
		}));


		for (Future<?> future : list) {
			future.get();
		}
		
		executor.shutdown();
		
		System.out.println("End");
	}

	@Test
	public void testRetrieveCatalogListWithContractNumber_defect10680() throws Exception {

		CatalogListContract contract = new CatalogListContract();
		contract.setSessionHandle(crmSessionHandle);
		contract.setPaymentType("Consumable Purchase");
		contract.setSoldToNumber("0000223612");
		contract.setAgreementId("1-78GMB54");
		contract.setHardwareSuppliesFlag(false);
		contract.setHardwareAccessoriesFlag(false);
		contract.setContractNumber("0005004515");
		contract.setHardwareFlag(false);
		contract.setPortalFlag(false);
		contract.setCatalogFlag(true);
		
		Map<String, Object> sortCriteria = new HashMap<String, Object>();
		sortCriteria.put("partImage", "ASCENDING");
		contract.setSortCriteria(sortCriteria);
		
		contract.setIncrement(40);
		contract.setStartRecordNumber(0);
		contract.setNewQueryIndicator(true);

		CatalogListResult result = service.retrieveCatalogListWithContractNumber(contract);
		
		List<OrderPart> parts = result.getPartsList();
		
		System.out.println("Parts size: " + (parts == null ? "null" : parts.size()));
		System.out.println("Total count: " + result.getTotalCount());
		
		System.out.println("End");
	}
	
	
	@Test
	public void testRetrieveCatalogListWithContractNumber_defect10881() throws Exception {

		CatalogListContract contract = new CatalogListContract();
		contract.setSessionHandle(crmSessionHandle);
		contract.setPaymentType("Ship and Bill");
		contract.setSoldToNumber("0000214679");
		contract.setAgreementId("1-78G80CQ");
		contract.setHardwareSuppliesFlag(false);
		contract.setHardwareAccessoriesFlag(false);
		contract.setContractNumber("0005004503");
		contract.setHardwareFlag(false);
		contract.setPortalFlag(false);
		contract.setCatalogFlag(true);
		
		Map<String, Object> sortCriteria = new HashMap<String, Object>();
		sortCriteria.put("partImage", "ASCENDING");
		contract.setSortCriteria(sortCriteria);
		
		contract.setIncrement(40);
		contract.setStartRecordNumber(0);
		contract.setNewQueryIndicator(true);

		CatalogListResult result = service.retrieveCatalogListWithContractNumber(contract);
		
		List<OrderPart> parts = result.getPartsList();
		
		System.out.println("Parts size: " + (parts == null ? "null" : parts.size()));
		System.out.println("Total count: " + result.getTotalCount());
		
		System.out.println("End");
	}
	
	
    @Test
    public void testRetrieveCatalogList_QA_defect10380() throws Exception {
    	CatalogListContract contract = new CatalogListContract();
    	
    	contract.setAgreementId("1-DOZ7M7");
    	contract.setPartNumber("25A0013");
    	contract.setCatalogFlag(true);
    	contract.setIncrement(40);
    	contract.setStartRecordNumber(0);
    	contract.setNewQueryIndicator(true);
    	contract.setSessionHandle(crmSessionHandle);
    	
    	CatalogListResult result = service.retrieveCatalogList(contract);
    	
    	MiscTest.print("[partsList] ", result.getPartsList());
//        assertTrue(result.getPartsList().size()  > 0);
    }
    
    @Test
    public void testRetrieveCatalogList_Prod_defect17405() throws Exception {
    	CatalogListContract contract = new CatalogListContract();
    	contract.setAgreementId("1-5YV3Z4Y");
    	contract.setPartNumber("16M1216");
    	contract.setEffectiveDate(LangUtil.convertStringToGMTDate("04/1/2015 15:47:50"));
    	contract.setCatalogFlag(true);
    	contract.setIncrement(40);
    	contract.setStartRecordNumber(0);
    	contract.setNewQueryIndicator(true);
    	Map<String, Object> sortCriteria = new HashMap<String, Object>();
    	sortCriteria.put("partImage", "ASCENDING");
		contract.setSortCriteria(sortCriteria );
    	contract.setSessionHandle(crmSessionHandle);
    	
    	CatalogListResult result = service.retrieveCatalogList(contract);
    	MiscTest.print("[partsList] ", result.getPartsList());
    }
    
    @Test
    public void testRetrieveCatalogList_15_7_Inegration() throws Exception {
    	CatalogListContract contract = new CatalogListContract();
    	contract.setPaymentType("Ship and Bill");
    	contract.setSoldToNumber("0000105084");
    	contract.setAgreementId("1-2R1KM52");
    	contract.setContractNumber("0005006872");
    	contract.setEffectiveDate(LangUtil.convertStringToGMTDate("04/24/2015 14:43:33"));
    	contract.setCatalogFlag(true);
    	contract.setIncrement(40);
    	contract.setStartRecordNumber(0);
    	contract.setNewQueryIndicator(true);
    	Map<String, Object> sortCriteria = new HashMap<String, Object>();
    	sortCriteria.put("partImage", "ASCENDING");
    	contract.setSortCriteria(sortCriteria );
    	contract.setSessionHandle(crmSessionHandle);
    	
    	CatalogListResult result = service.retrieveCatalogList(contract);
    	MiscTest.print("[partsList] ", result.getPartsList());
    }

    @Test
    public void testRetrieveCatalogList_QA_withContractNumber_defect10832() throws Exception {
        
     CatalogListContract contract = new CatalogListContract();
        contract.setPaymentType("Delayed Purchase");
        contract.setSoldToNumber("0000101382");
        contract.setAgreementId("1-78GMAQI");
        contract.setHardwareFlag(true);
        contract.setContractNumber("0005004560");
        contract.setProductModel("C544dn");
        contract.setHardwareSuppliesFlag(false);
        contract.setHardwareAccessoriesFlag(true);
        contract.setProductType("Laser");
        contract.setPortalFlag(false);
        contract.setCatalogFlag(false);
        contract.setIncrement(0);
        contract.setStartRecordNumber(0);
        contract.setNewQueryIndicator(true);
        contract.setSessionHandle(crmSessionHandle);
        CatalogListResult result = service.retrieveCatalogListWithContractNumber(contract);
        
        List<OrderPart> parts = result.getAccessoriesList();
        
        System.out.println("Parts size: " + (parts == null ? "null" : parts.size()));
		System.out.println("Total count: " + result.getTotalCount());
		
		for (OrderPart orderPart : parts) {
			System.out.println(orderPart.getPartNumber());
			System.out.println(orderPart.getPartNumber().equalsIgnoreCase("13N1523"));
		}
		
		System.out.println("End");
    }
    
    @Test
    public void testRetrieveCatalogList_QA_withContractNumber_defect10680() throws Exception {
    	CatalogListContract contract = new CatalogListContract();
    	contract.setAgreementId("1-78GMB54");
    	contract.setSoldToNumber("0000223612");
    	contract.setPaymentType("Consumable Purchase");
    	contract.setContractNumber("0005004515");
    	contract.setCatalogFlag(true);
    	contract.setSortCriteria((Map<String, Object>) MiscTest.newHashMap("partImage", "ASCENDING"));
    	contract.setIncrement(0);
    	contract.setStartRecordNumber(0);
    	contract.setNewQueryIndicator(true);
    	contract.setSessionHandle(crmSessionHandle);
    	
    	CatalogListResult result = service.retrieveCatalogListWithContractNumber(contract);
    	
    	logger.debug("PartList: " + result.getPartsList());
    }
    
    @Test
    public void testRetrieveCatalogList_QA_withContractNumber_defect9515() throws Exception {
    	CatalogListContract contract = new CatalogListContract();
    	contract.setAgreementId("1-78GMAQI");
    	contract.setSoldToNumber("0000101382");
    	contract.setPaymentType("Ship and Bill");
    	contract.setContractNumber("0005004560");
    	contract.setProductModel("HDD080007");
    	contract.setProductType("Laser");
    	contract.setHardwareFlag(true);
    	contract.setIncrement(0);
    	contract.setStartRecordNumber(0);
    	contract.setNewQueryIndicator(true);
    	contract.setSessionHandle(crmSessionHandle);
    	
    	CatalogListResult result = service.retrieveCatalogListWithContractNumber(contract);
    	
    	logger.debug("PartList: " + result.getPartsList());
    }
    
    @Test
    public void testRetrieveCatalogList_Prod_withContractNumber_defect12988() throws Exception {
    	CatalogListContract contract = new CatalogListContract();
    	contract.setAgreementId("1-80OJBVX");
    	contract.setContractNumber("0005004735");
    	contract.setCatalogFlag(true);
    	contract.setIncrement(40);
    	contract.setStartRecordNumber(0);
    	contract.setNewQueryIndicator(true);
    	Map<String, Object> sortCriteria = new HashMap<String, Object>();
    	sortCriteria.put("partImage", "ASCENDING");
		contract.setSortCriteria(sortCriteria );
    	contract.setSessionHandle(crmSessionHandle);
    	
    	CatalogListResult result = service.retrieveCatalogListWithContractNumber(contract);
    	
    	logger.debug("PartList: " + result.getPartsList());
    }
	@Test
	public void testRetrieveCatalog_defect9340() throws Exception {
		CatalogListContract contract = new CatalogListContract();
		contract.setPaymentType("Ship and Bill");
		contract.setSoldToNumber("0000221384");
		contract.setAgreementId("1-78G80CQ");
		contract.setHardwareSuppliesFlag(false);
		contract.setHardwareAccessoriesFlag(false);
		contract.setHardwareFlag(true);
		contract.setPortalFlag(false);
		contract.setCatalogFlag(false);
		contract.setIncrement(0);
		contract.setStartRecordNumber(0);
		contract.setNewQueryIndicator(false);
		contract.setContractNumber("0005004502");

		CatalogListResult result = service.retrieveCatalogFieldList(contract);

		List<ListOfValues> lovList = result.getLovList();

		System.out.println("Size: " + lovList.size());

		for (ListOfValues listOfValues : lovList) {
			System.out.println("Name: " + listOfValues.getName());
			System.out.println("Value: " + listOfValues.getValue());
			System.out.println("---------");
		}
		System.out.println("End");
	}
	
	@Test
	public void testRetrieveCatalog_defect9340_Accessories() throws Exception {
		CatalogListContract contract = new CatalogListContract();
		contract.setPaymentType("Ship and Bill");
		contract.setSoldToNumber("0000221384");
		contract.setAgreementId("1-78G80CQ");
		contract.setHardwareSuppliesFlag(false);
		contract.setHardwareAccessoriesFlag(true);
		contract.setHardwareFlag(true);
		contract.setPortalFlag(false);
		contract.setCatalogFlag(false);
		contract.setIncrement(0);
		contract.setStartRecordNumber(0);
		contract.setNewQueryIndicator(false);
		contract.setContractNumber("0005004502");
		contract.setProductType("Laser");
		contract.setProductModel("C544dn");

		CatalogListResult result = service.retrieveCatalogFieldList(contract);

		List<ListOfValues> lovList = result.getLovList();

		System.out.println("Size: " + lovList.size());

		for (ListOfValues listOfValues : lovList) {
			System.out.println("Name: " + listOfValues.getName());
			System.out.println("Value: " + listOfValues.getValue());
			System.out.println("---------");
		}
		System.out.println("End");
	}
	
	
	@Test
	public void testRetrieveCatalogListWithContractNumber_defect10936() throws Exception {

		CatalogListContract contract = new CatalogListContract();
		contract.setSessionHandle(crmSessionHandle);
		contract.setPaymentType("Consumable Purchase");
		contract.setSoldToNumber("0000101382");
		contract.setAgreementId("1-78GMAQI");
		contract.setHardwareSuppliesFlag(false);
		contract.setHardwareAccessoriesFlag(false);
		contract.setContractNumber("0005004560");
		contract.setHardwareFlag(false);
		contract.setPortalFlag(false);
		contract.setCatalogFlag(false);
		contract.setIncrement(0);
		contract.setStartRecordNumber(0);
		contract.setNewQueryIndicator(false);

		CatalogListResult result = service.retrieveCatalogListWithContractNumber(contract);
		
		List<OrderPart> parts = result.getPartsList();
		
		System.out.println("Parts size: " + (parts == null ? "null" : parts.size()));
		System.out.println("Total count: " + result.getTotalCount());
		
		for (OrderPart orderPart : parts) {
			System.out.println(orderPart.getProviderContractNo());
		}
		
		System.out.println("End");
	}
	
	@Test
	public void testRetrieveCatalogFieldList_defect7697() throws Exception {
        CatalogListContract contract = new CatalogListContract();
        contract.setSessionHandle(crmSessionHandle);
        contract.setAgreementId("1-4MH4XSN");
        contract.setStartRecordNumber(0);
        contract.setHardwareFlag(false);
        contract.setHardwareAccessoriesFlag(false);
        contract.setHardwareSuppliesFlag(false);
        contract.setPortalFlag(false);
        contract.setCatalogFlag(false);
        contract.setIncrement(10);
        contract.setNewQueryIndicator(false);
        
        //contract.setProductType("     mock-productType    \n");
        //contract.setProductModel("   mock-productModel    \r");
        
        CatalogListResult result = service.retrieveCatalogFieldList(contract);
        MiscTest.print("[partsList] ", result.getPartsList());
    }
	
	 @Test
	 public void testRetrieveCatalogFieldList_SearchSpec() throws Exception {
	  CatalogListContract contract = new CatalogListContract();
	  contract.setSessionHandle(crmSessionHandle);
	  contract.setAgreementId("1-5YV3Z4Y");
	  contract.setSoldToNumber("0000166146");
	  contract.setPaymentType("Ship and Bill");
	  contract.setContractNumber("0040000157");
	  contract.setEffectiveDate(LangUtil.convertStringToGMTDate("06/1/2015 21:23:03"));
	  contract.setStartRecordNumber(0);
	  contract.setHardwareFlag(false);
	  contract.setHardwareAccessoriesFlag(false);
	  contract.setHardwareSuppliesFlag(false);
	  contract.setPortalFlag(false);
	  contract.setCatalogFlag(true);
	  contract.setIncrement(40);
	  contract.setNewQueryIndicator(true);
	  Map<String, Object> sortCriteria = new HashMap<String, Object>();
	  sortCriteria.put("partImage", "ASCENDING");
	  contract.setSortCriteria(sortCriteria );
	  
	  CatalogListResult result = service.retrieveCatalogFieldList(contract);
	  
	  System.out.println(result.getLovList().size());
	 }
	 
	@Test
	public void testRetrieveCatalogListWithContractNumber_defect11720()
			throws Exception {

		long t = System.currentTimeMillis();
		
		CatalogListContract contract = new CatalogListContract();
		contract.setSessionHandle(crmSessionHandle);
		contract.setPaymentType("Ship and Bill");
		contract.setSoldToNumber("0000126998");
		contract.setAgreementId("1-78IB4G0");
		contract.setHardwareSuppliesFlag(false);
		contract.setHardwareAccessoriesFlag(true);
		contract.setContractNumber("0005004508");
		contract.setHardwareFlag(true);
		contract.setProductType("Laser");
		contract.setProductModel("C782dn");
		contract.setPortalFlag(false);
		contract.setCatalogFlag(false);
		contract.setIncrement(0);
		contract.setStartRecordNumber(0);
		contract.setNewQueryIndicator(true);

		CatalogListResult result = service
				.retrieveCatalogListWithContractNumber(contract);

		List<OrderPart> accessories = result.getAccessoriesList();

		System.out.println("Parts size: "+ (accessories == null ? "null" : accessories.size()));
		System.out.println("Total count: " + result.getTotalCount());

		for (OrderPart orderPart : accessories) {
			System.out.println("Part number: "+ orderPart.getContractLineItemId());
		}

		System.out.println(("Exec time: " + (System.currentTimeMillis() - t) / 1000.0));
		System.out.println("End");
	}
	
	
	@Test
	public void testRetrieveCatalog_42() throws Exception {
		CatalogListContract contract = new CatalogListContract();
//		contract.setSessionHandle(crmSessionHandle);
		contract.setPaymentType("Delayed Purchase");
		contract.setSoldToNumber("0000216725");
		contract.setAgreementId("1-7FRXC0F");
		contract.setHardwareSuppliesFlag(false);
		contract.setHardwareAccessoriesFlag(false);
		contract.setHardwareFlag(true);
		contract.setProductType("Laser");
		contract.setPortalFlag(false);
		contract.setCatalogFlag(false);
		contract.setIncrement(0);
		contract.setStartRecordNumber(0);
		contract.setNewQueryIndicator(false);
		contract.setPortalFlag(false);
		contract.setEffectiveDate(new Date());
		contract.setCatalogFlag(false);
//		contract.setProductModel("T652dn");
		contract.setContractNumber("0005004590");

		CatalogListResult result = service.retrieveCatalogFieldList(contract);

		List<ListOfValues> lovList = result.getLovList();

		System.out.println("Size: " + lovList.size());

		for (ListOfValues listOfValues : lovList) {
			System.out.println("Name: " + listOfValues.getName());
			System.out.println("Value: " + listOfValues.getValue());
		}
		System.out.println("End");
	}
	
	@Test
	public void testRetrieveCatalogListWithContractNumber_defect12680() throws Exception {

		CatalogListContract contract = new CatalogListContract();
		contract.setSessionHandle(crmSessionHandle);
		contract.setPaymentType("Consumable Purchase");
		contract.setSoldToNumber("0000180030");
		contract.setAgreementId("1-80OJBVX");
		contract.setHardwareSuppliesFlag(false);
		contract.setHardwareAccessoriesFlag(false);
		contract.setContractNumber("0005004735");
		contract.setHardwareFlag(false);
		contract.setPortalFlag(false);
		contract.setCatalogFlag(true);
		contract.setIncrement(40);
		contract.setStartRecordNumber(0);
		contract.setNewQueryIndicator(true);
		
		Map<String,Object> sortCriteria = new HashMap<String, Object>();
		sortCriteria.put("partImage", "ASCENDING");
		contract.setSortCriteria(sortCriteria);

		CatalogListResult result = service.retrieveCatalogListWithContractNumber(contract);
		
		List<OrderPart> parts = result.getPartsList();
		
		for (OrderPart orderPart : parts) {
			System.out.println("Part number: " + orderPart.getPartNumber());
			System.out.println("Price: " + orderPart.getPrice());
			System.out.println("-----------------");
		}

		System.out.println("End");
	}
	
	@Test
	public void testRetrieveCatalogListWithContractNumber_defect12988() throws Exception {

		CatalogListContract contract = new CatalogListContract();
		contract.setSessionHandle(crmSessionHandle);
		contract.setPaymentType("Ship and Bill");
		contract.setSoldToNumber("0000180030");
		contract.setAgreementId("1-80OJBVX");
		contract.setHardwareSuppliesFlag(false);
		contract.setHardwareAccessoriesFlag(false);
		contract.setContractNumber("0005004735");
		contract.setHardwareFlag(false);
		contract.setPortalFlag(false);
		contract.setCatalogFlag(false);
		contract.setEffectiveDate(LangUtil.convertStringToGMTDate("04/21/2014 07:42:20"));
		contract.setIncrement(0);
		contract.setStartRecordNumber(0);
		contract.setNewQueryIndicator(false);

		CatalogListResult result = service.retrieveCatalogListWithContractNumber(contract);
		
		List<OrderPart> parts = result.getPartsList();
		
		for (OrderPart orderPart : parts) {
			System.out.println("Contract Line Number: " + orderPart.getContractLineItemId());
		}
		
		System.out.println("End");
	}
	
	@Test
	public void testRetrieveCatalogListWithContractNumber_defect17227() throws Exception {
		CatalogListContract contract = new CatalogListContract();
		contract.setSessionHandle(crmSessionHandle);
		contract.setPaymentType("Delayed Purchase");
		contract.setSoldToNumber("0000166146");
		contract.setAgreementId("1-5YV3Z4Y");
		contract.setHardwareSuppliesFlag(false);
		contract.setHardwareAccessoriesFlag(true);
		contract.setContractNumber("0040000157");
		contract.setProductModel("C792de");
		contract.setProductType("Laser");
		contract.setHardwareFlag(true);
		contract.setPortalFlag(false);
		contract.setCatalogFlag(false);
		contract.setEffectiveDate(LangUtil.convertStringToGMTDate("04/21/2014 07:42:20"));
		contract.setIncrement(0);
		contract.setStartRecordNumber(0);
		contract.setNewQueryIndicator(true);
		CatalogListResult result = service.retrieveCatalogListWithContractNumber(contract);
		System.out.println("End " + result.getTotalCount());
	}
	
	@Test
	public void testRetrievePrinterTypesB2B_PrinterList() throws Exception {
		CatalogListContract c = new CatalogListContract();
		c.setSessionHandle(crmSessionHandle);
		c.setBundleId("Bundle ID");
		c.setAccountId("Account ID");
		c.setEffectiveDate(LangUtil.convertStringToGMTDate("04/29/2014 11:27:03"));
		c.setHardwareSuppliesFlag(true);
		c.setHardwareAccessoriesFlag(false);
		c.setAgreementId("Agreement ID");
		c.setSoldToNumber("Sold To");
		c.setIncrement(0);
		c.setStartRecordNumber(0);
		c.setNewQueryIndicator(false);
		
		CatalogListResult result = service.retrievePrinterTypesB2B(c);
		
		logger.debug(result);
	}
	
	@Test
	public void testRetrievePrinterTypesB2B_PrinterList_duplicates() throws Exception {
		CatalogListContract c = new CatalogListContract();
		c.setSessionHandle(crmSessionHandle);
		c.setEffectiveDate(LangUtil.convertStringToGMTDate("05/22/2015 15:49:48"));
		c.setHardwareSuppliesFlag(false);
		c.setHardwareAccessoriesFlag(false);
		c.setHardwareFlag(true);
		c.setCatalogFlag(false);
		c.setAgreementId("1-4NW9QDF");
		c.setSoldToNumber("0000215238");
		c.setIncrement(0);
		c.setStartRecordNumber(0);
		c.setNewQueryIndicator(false);
		
		CatalogListResult result = service.retrievePrinterTypesB2B(c);
		
		for (ListOfValues lov : result.getLovList()) {
			System.out.println("Name: " + lov.getName() + " value: " + lov.getValue());
		}
		
		System.out.println("Size: " + result.getLovList().size());
	}
	
	@Test
	public void testRetrievePrinterTypesB2B_NoData() throws Exception {
		CatalogListContract c = new CatalogListContract();
		c.setSessionHandle(crmSessionHandle);
		c.setEffectiveDate(LangUtil.convertStringToGMTDate("06/18/2015 11:52:51"));
		c.setHardwareSuppliesFlag(false);
		c.setHardwareAccessoriesFlag(false);
		c.setHardwareFlag(true);
		c.setHardwareSuppliesFlag(false);
		c.setCatalogFlag(false);
		c.setAgreementId("1-H91ALA");
		c.setContractNumber("0005006808");
		c.setSoldToNumber("0000126159");
		c.setIncrement(0);
		c.setStartRecordNumber(0);
		c.setNewQueryIndicator(false);
		CatalogListResult result = service.retrievePrinterTypesB2B(c);
		for (ListOfValues lov : result.getLovList()) {
			System.out.println("Name: " + lov.getName() + " value: " + lov.getValue());
		}
		System.out.println("Size: " + result.getLovList().size());
	}
	
	@Test
	public void testRetrieveAccessoriesB2b_AccessoriesList() throws Exception {
		CatalogListContract c = new CatalogListContract();
		c.setSessionHandle(crmSessionHandle);
		c.setEffectiveDate(LangUtil.convertStringToGMTDate("04/29/2015 16:55:36"));
		c.setHardwareSuppliesFlag(false);
		c.setHardwareAccessoriesFlag(true);
		c.setAgreementId("Agreement ID");
		c.setSoldToNumber("Sold To");
		c.setBundleId("Bundle ID");
		c.setIncrement(0);
		c.setStartRecordNumber(0);
		c.setNewQueryIndicator(false);
		
		CatalogListResult result = service.retrieveAccessoriesB2b(c);
		
		logger.debug(result);
	}
	
	@Test
	public void testRetrieveAccessoriesB2b_AccessoriesList_error() throws Exception {
		CatalogListContract c = new CatalogListContract();
		c.setSessionHandle(crmSessionHandle);
		c.setEffectiveDate(LangUtil.convertStringToGMTDate("05/29/2015 18:36:33"));
		c.setHardwareSuppliesFlag(false);
		c.setHardwareAccessoriesFlag(false);
		c.setCatalogFlag(true);
		c.setAgreementId("1-4NW9QDF");
		c.setSoldToNumber("0000215238");
		c.setProductModel("X464de");
		c.setProductType("MFP Mono Laser Printer");
		c.setIncrement(40);
		c.setStartRecordNumber(0);
		c.setNewQueryIndicator(true);
		
		CatalogListResult result = service.retrieveAccessoriesB2b(c);
		logger.debug(result);
	}
	
	@Test
	public void testRetrieveAccessoriesB2b_BillingModel_for_HardwareBundles() throws Exception {
		CatalogListContract c = new CatalogListContract();
		c.setSessionHandle(crmSessionHandle);
		c.setEffectiveDate(LangUtil.convertStringToGMTDate("06/30/2015 18:36:33"));
		c.setHardwareSuppliesFlag(false);
		c.setHardwareAccessoriesFlag(false);
		c.setCatalogFlag(true);
		c.setAgreementId("1-P4I9ND0");
		c.setSoldToNumber("0000126159");
		c.setContractNumber("0040000406");
		c.setProductModel("C736dn");
		c.setProductType("Color Laser Printer");
		c.setIncrement(40);
		c.setStartRecordNumber(0);
		c.setNewQueryIndicator(true);
		
		CatalogListResult result = service.retrieveAccessoriesB2b(c);
		logger.debug(result);
	}
	
	@Test
	public void testRetrieveAccessoriesB2b_NoData_punchout() throws Exception {
		CatalogListContract c = new CatalogListContract();
		c.setSessionHandle(crmSessionHandle);
		c.setEffectiveDate(LangUtil.convertStringToISTDate("07/09/2015 18:36:39"));
		c.setHardwareSuppliesFlag(false);
		c.setHardwareAccessoriesFlag(false);
		c.setCatalogFlag(true);
		c.setContractNumber("0040000406");
		c.setAgreementId("1-P4I9ND0");
		c.setSoldToNumber("0000126159");
		c.setPartNumber("30G0802");
//		c.setPartNumber("T650H31E");
//		c.setProductModel("C736dn");
//		c.setProductType("Color Laser Printer");
		c.setIncrement(40);
		c.setStartRecordNumber(0);
		c.setNewQueryIndicator(true);
		
		CatalogListResult result = service.retrieveAccessoriesB2b(c);
		logger.debug(result);
	}
}
