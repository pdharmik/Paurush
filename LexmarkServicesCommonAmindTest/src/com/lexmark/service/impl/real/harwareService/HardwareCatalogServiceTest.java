package com.lexmark.service.impl.real.harwareService;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.junit.Test;

import com.lexmark.contract.CatalogListContract;
import com.lexmark.contract.HardwareCatalogContract;
import com.lexmark.domain.Bundle;
import com.lexmark.domain.HardwareCatalog;
import com.lexmark.domain.Part;
import com.lexmark.result.BundleListResult;
import com.lexmark.result.HardwareCatalogResult;
import com.lexmark.service.impl.real.AmindOrderHardwareService;
import com.lexmark.service.impl.real.AmindOrderSuppliesCatalogService;
import com.lexmark.service.impl.real.GlobalServiceStatefulBase;
import com.lexmark.util.LangUtil;

public class HardwareCatalogServiceTest extends GlobalServiceStatefulBase {

	AmindOrderHardwareService service;
	HardwareCatalogContract contract;
	AmindOrderSuppliesCatalogService orderService;
	CatalogListContract orderContract;
	private int waitTime = 0;
	private int wait2Time = 1000;

	private int exceptionCounter = 0;
	
	@Test
	public void testRetrieveHardwareList() throws Exception {
		
		service = new AmindOrderHardwareService();
		
		contract = new HardwareCatalogContract();
		contract.setSessionHandle(handle);
		contract.setAgreementId("1-5UNNARF");
		contract.setStartRecordNumber(0);
		contract.setIncrement(10);
		contract.setNewQueryIndicator(false);

		HardwareCatalogResult result = service.retrieveHardwareList(contract);
		
		for (Bundle bundle : result.getHardwareCatalog().getBundleList()) {
			for (Part part : bundle.getPartList()) {
				for (String paymentType : part.getPaymentTypes()) {
					System.out.println("Payment type: " + paymentType);
				}
			}
		}
		
		assertNotNull("result is null!", result);
	}
	
	
	@Test
	public void testRetrieveHardwareList_defect8572() throws Exception {
		
		service = new AmindOrderHardwareService();
		
		contract = new HardwareCatalogContract();
		contract.setSessionHandle(handle);
		contract.setAgreementId("1-5WDM50T");
		contract.setSoldToNumber("0000319381");
		contract.setPaymentType("Ship and Bill");
		contract.setProductType("Laser");
		contract.setProductModel("C782n");
		contract.setStartRecordNumber(0);
		contract.setIncrement(0);
		contract.setNewQueryIndicator(false);

		HardwareCatalogResult result = service.retrieveHardwareList(contract);
		
		System.out.println(result.getHardwareCatalog() == null);
		
		assertNotNull("result is null!", result);
	}
	


	@Test
	public void testRetrieveHardwareList_QA_defect9955() throws Exception {
		
		service = new AmindOrderHardwareService();
		
		contract = new HardwareCatalogContract();
		contract.setSessionHandle(handle);
		contract.setAgreementId("1-793S2JE");
		contract.setSoldToNumber("0000227605");
		contract.setPaymentType("Delayed Purchase");
		contract.setContractNumber("0005002121");
		contract.setProductType("Laser");
		contract.setProductModel("X654de");
		contract.setStartRecordNumber(0);
		contract.setIncrement(0);
		contract.setNewQueryIndicator(false);
		
		HardwareCatalogResult result = service.retrieveHardwareList(contract);
		
		for (Bundle bundle : result.getHardwareCatalog().getBundleList()) {
			for (Part part : bundle.getPartList()) {
				for (String paymentType : part.getPaymentTypes()) {
					System.out.println("Payment type: " + paymentType);
				}
			}
		}
		
		assertNotNull("result is null!", result);
	}



	@Test
	public void testRetrieveHardwareList_defect8721() throws Exception {
		
		service = new AmindOrderHardwareService();
		
		contract = new HardwareCatalogContract();
		contract.setSessionHandle(handle);
		contract.setAgreementId("1-69NLZIF");
		contract.setSoldToNumber("0000319381");
		contract.setPaymentType("Ship and Bill");
		contract.setContractNumber("0005001453");
		contract.setProductType("Laser");
		contract.setProductModel("C792dte");
		contract.setStartRecordNumber(0);
		contract.setIncrement(0);
		contract.setNewQueryIndicator(false);
		contract.setEffectiveDate(new Date());

		HardwareCatalogResult result = service.retrieveHardwareList(contract);
		
		List<Bundle> bundleList = result.getHardwareCatalog().getBundleList();
		
		System.out.println("Bunde list size " + bundleList.size());
//		
//		for (Bundle bundle : bundleList) {
//			List<Part> parts = bundle.getPartList();
//			System.out.println("--------");
//			System.out.println("Size " + parts.size());
//			for (Part part : parts) {
//				System.out.println(part.getCatalogId() + " " + part.getSupplyId());
//			}
//		}
		
	}
	


	@Test
	public void testRetrieveHardwareList_HardwareAndConsumables() throws Exception {
		
		service = new AmindOrderHardwareService();
		
		contract = new HardwareCatalogContract();
		contract.setAgreementId("1-69NLZIF");
		contract.setSoldToNumber("0000319381");
		contract.setPaymentType("Ship and Bill");
		contract.setContractNumber("0005001453");
		contract.setProductType("Laser");
		contract.setProductModel("C792dte");
		contract.setStartRecordNumber(0);
		contract.setIncrement(0);
		contract.setNewQueryIndicator(false);
		contract.setSessionHandle(handle);
		
		HardwareCatalogResult result = service.retrieveHardwareList(contract);
		
		HardwareCatalog catalog = result.getHardwareCatalog();
		
		System.out.println("Start");
		
//		if(catalog!=null) {
//			for (Bundle bundle : catalog.getBundleList()) {
//				if(bundle!=null) {
//					for (Part part : bundle.getPartList()) {
//						System.out.println("Parent line item number: " + part.getParentLineItemNumber());
//					}
//				}
//			}
//		}
		
		System.out.println("End");
	}
	
	
	@Test
	public void testRetrieveHardwareList2() throws Exception {
		
		service = new AmindOrderHardwareService();
		
		contract = new HardwareCatalogContract();
		contract.setPaymentType("Annuity Billing");
		contract.setSoldToNumber("0000198238");
		contract.setAgreementId("1-6DL8ZYF");
		contract.setContractNumber("0005001808");
		contract.setProductType("Laser");
		contract.setProductModel("T620n");
		contract.setIncrement(0);
		contract.setStartRecordNumber(0);
		contract.setNewQueryIndicator(false);
		contract.setSessionHandle(handle);

		HardwareCatalogResult result = service.retrieveHardwareList(contract);
		
		System.out.println("Start");
		
//		for (Bundle bundle : result.getHardwareCatalog().getBundleList()) {
//			for (Part part : bundle.getPartList()) {
//					System.out.println("Parent line item number: " + part.getParentLineItemNumber());
//			}
//		}
//		
		System.out.println("End");
	}
	
	
	@Test
	public void testRetrieveHardwareList_defect9340_PreconfiguredSearchspecChange() throws Exception {
		
		service = new AmindOrderHardwareService();
		
		contract = new HardwareCatalogContract();
		contract.setPaymentType("Ship and Bill");
		contract.setSoldToNumber("0000324040");
		contract.setAgreementId("1-2HKZEOQ");
		contract.setContractNumber("0005001856");
//		contract.setProductType("Laser");
//		contract.setProductModel("FLA060021");
		contract.setStartRecordNumber(0);
		contract.setIncrement(0);
		contract.setNewQueryIndicator(false);
		contract.setSessionHandle(handle);

		HardwareCatalogResult result = service.retrieveHardwareList(contract);
		
		System.out.println(result.getHardwareCatalog() == null);
		
		System.out.println("End");
	}
	
	
	
	@Test
	public void testRetrieveHardwareList_defect9983() throws Exception {

		service = new AmindOrderHardwareService();
		orderService = new AmindOrderSuppliesCatalogService();

		ExecutorService executor = Executors.newFixedThreadPool(2);
		List<Future<?>> list = new ArrayList<Future<?>>();

//		for(int i = 0; i < 10; i++ ) {
		list.add(executor.submit(new Runnable() {

			@Override
			public void run() {

				System.out.println("FIRST STARTED");
				
//				contract = new HardwareCatalogContract();
//				contract.setAgreementId("1-69NLZIF");
//				contract.setSoldToNumber("0000319381");
//				contract.setPaymentType("Ship and Bill");
//				contract.setContractNumber("0005001809");
//				contract.setProductType("Laser");
//				contract.setProductModel("X546dtn");
//				contract.setStartRecordNumber(0);
//				contract.setIncrement(0);
//				contract.setNewQueryIndicator(false);
//				contract.setSessionHandle(handle);
//
//				HardwareCatalogResult result = null;
//
//				try {
//					result = service.retrieveHardwareList(contract);
//				} catch (Exception e) {
//					e.printStackTrace();
//					exceptionCounter++;
//				}
			}
		}));

		list.add(executor.submit(new Runnable() {

			@Override
			public void run() {
				
				System.out.println("SECOND STARTED");

//				try {
//					Thread.sleep(700);
//				} catch (InterruptedException e1) {
//					e1.printStackTrace();
//				}
//
//				orderContract = new CatalogListContract();
//				orderContract.setSessionHandle(handle);
//				orderContract.setPaymentType("Ship and Bill");
//				orderContract.setSoldToNumber("0000319381");
//				orderContract.setAgreementId("1-69NLZIF");
//				orderContract.setHardwareSuppliesFlag(false);
//				orderContract.setHardwareAccessoriesFlag(true);
//				orderContract.setContractNumber("0005001809");
//				orderContract.setHardwareFlag(true);
//				orderContract.setPortalFlag(false);
//				orderContract.setCatalogFlag(false);
//				orderContract.setProductType("Laser");
//				orderContract.setProductModel("X546dtn");
//				orderContract.setIncrement(0);
//				orderContract.setStartRecordNumber(0);
//				orderContract.setNewQueryIndicator(true);
//
//				CatalogListResult orderResult = null;
//
//				try {
//					orderResult = orderService
//							.retrieveCatalogListWithContractNumber(orderContract);
//				} catch (Exception e) {
//					e.printStackTrace();
//					exceptionCounter++;
//				}

			}
		}));

//		}
		
		for (Future<?> future : list) {
			System.out.println("MAIN");
			future.get();
		}
		
		executor.shutdown();
		
		
		System.out.println("EXCEPTION COUNT: " + exceptionCounter);
		System.out.println("End");
	}
	
	
	
	@Test
	public void testRetrieveHardwareList_QA_defect10596() throws Exception {
		service = new AmindOrderHardwareService();
		contract = new HardwareCatalogContract();
		contract.setSessionHandle(handle);
		contract.setAgreementId("1-78CCDQN");
		contract.setSoldToNumber("0000111589");
		contract.setPaymentType("Annuity Billing");
		contract.setPartNumber("8049865");
		contract.setContractNumber("0005004513");
		contract.setEffectiveDate(new Date());
		contract.setStartRecordNumber(0);
		contract.setIncrement(0);
		contract.setNewQueryIndicator(false);
		
		HardwareCatalogResult result = service.retrieveHardwareList(contract);
		System.out.println("Color/Mono: " + result.getHardwareCatalog().getColor_mono());
		
		assertNotNull("result is null!", result);
	}
	
	@Test
	 public void testRetrieveHardwareList_Defect_14511() throws Exception {
	  service = new AmindOrderHardwareService();
	  contract = new HardwareCatalogContract();
	  contract.setSessionHandle(handle);
	  contract.setAgreementId("1-80OJBVX");
	  contract.setSoldToNumber("0000180030");
	  contract.setPaymentType("Ship and Bill");
	  contract.setProductModel("W850n");
	  contract.setProductType("Laser");
	  contract.setContractNumber("0005005880");
	  contract.setEffectiveDate(LangUtil.convertStringToGMTDate("07/23/2014 20:39:06"));
	  contract.setStartRecordNumber(0);
	  contract.setIncrement(0);
	  contract.setNewQueryIndicator(false);
	  
	  HardwareCatalogResult result = service.retrieveHardwareList(contract);
	  System.out.println("Color/Mono: " + result.getHardwareCatalog().getColor_mono());
	  
	  assertNotNull("result is null!", result);
	 }
	
	@Test
	public void testRetrieveHardwareList_INC0104809_performance() throws Exception {
		service = new AmindOrderHardwareService();
		contract = new HardwareCatalogContract();
		contract.setSessionHandle(handle);
		contract.setAgreementId("1-1XAEMWV");
		contract.setSoldToNumber("0000335821");
		contract.setPaymentType("Annuity Billing");
		contract.setProductModel("C748dte");
		contract.setProductType("Laser");
		contract.setContractNumber("0040000275");
		contract.setEffectiveDate(LangUtil.convertStringToGMTDate("05/8/2015 05:51:33"));
		contract.setStartRecordNumber(0);
		contract.setIncrement(0);
		contract.setNewQueryIndicator(false);
		
		HardwareCatalogResult result = service.retrieveHardwareList(contract);
		System.out.println("Color/Mono: " + result.getHardwareCatalog().getColor_mono());
		
		assertNotNull("result is null!", result);
	}
	
	@Test
	public void testRetrieveHardwareList_noData() throws Exception {
		service = new AmindOrderHardwareService();
		contract = new HardwareCatalogContract();
		contract.setSessionHandle(handle);
		contract.setAgreementId("1-18KR2YT");
		contract.setContractNumber("0040000292");
		contract.setSoldToNumber("0000180030");
		contract.setPaymentType("Ship and Bill");
		contract.setPartNumber("22Z0012");
//		contract.setProductModel("C748dte");
//		contract.setProductType("Laser");
		contract.setEffectiveDate(LangUtil.convertStringToGMTDate("06/15/2015 10:47:36"));
		contract.setStartRecordNumber(0);
		contract.setIncrement(0);
		contract.setNewQueryIndicator(false);
		
		HardwareCatalogResult result = service.retrieveHardwareList(contract);
		assertNotNull("result is null!", result);
	}
	
	@Test
	public void testRetrieveHardwareList_defect18003() throws Exception {
		service = new AmindOrderHardwareService();
		contract = new HardwareCatalogContract();
		contract.setSessionHandle(handle);
		contract.setAgreementId("1-Z0BOE1");
		contract.setContractNumber("0040000221");
//		contract.setSoldToNumber("0000180030");
//		contract.setPaymentType("Ship and Bill");
//		contract.setPartNumber("22Z0012");
		contract.setProductModel("MS610dn");
		contract.setProductType("Laser");
		contract.setEffectiveDate(LangUtil.convertStringToGMTDate("06/9/2015 13:34:00"));
		contract.setStartRecordNumber(0);
		contract.setIncrement(0);
		contract.setNewQueryIndicator(false);
		
		long t0 = System.currentTimeMillis();
		HardwareCatalogResult result = service.retrieveHardwareList(contract);
		System.out.printf("Exec time=%s sec. \n", (System.currentTimeMillis() - t0) / 1000.0);
		assertNotNull("result is null!", result);
	}
	
	@Test
	public void testRetrieveHardwareList_NoData() throws Exception {
		service = new AmindOrderHardwareService();
		contract = new HardwareCatalogContract();
		contract.setSessionHandle(handle);
		contract.setContractNumber("0040000406");
		contract.setAgreementId("1-P4I9ND0");
		contract.setSoldToNumber("0000126159");
//		contract.setPaymentType("Ship and Bill");
//		contract.setPartNumber("22Z0012");
//		contract.setProductModel("MS610dn");
//		contract.setProductType("Laser");
		contract.setEffectiveDate(LangUtil.convertStringToGMTDate("07/13/2015 12:31:42"));
		contract.setStartRecordNumber(0);
		contract.setIncrement(0);
		contract.setNewQueryIndicator(false);
		
		long t0 = System.currentTimeMillis();
		HardwareCatalogResult result = service.retrieveHardwareList(contract);
		System.out.printf("Exec time=%s sec. \n", (System.currentTimeMillis() - t0) / 1000.0);
		assertNotNull("result is null!", result);
	}
	
	@Test
	public void testRetrievePrinterBundleListB2B() throws Exception {
		service = new AmindOrderHardwareService();
		contract = new HardwareCatalogContract();
		contract.setSessionHandle(handle);
		contract.setAgreementId("1-80OJBVX");
		contract.setSoldToNumber("0000180030");
		contract.setPaymentType("Ship and Bill");
		contract.setProductModel("W850n");
		contract.setProductType("Laser");
		contract.setContractNumber("0005005880");
		contract.setEffectiveDate(LangUtil.convertStringToGMTDate("07/23/2014 20:39:06"));
		contract.setStartRecordNumber(0);
		contract.setIncrement(40);
		contract.setNewQueryIndicator(true);
		
		BundleListResult result = service.retrievePrinterBundleListB2B(contract);
		System.out.println("TotalCount: " + result.getTotalCount());
		assertNotNull("result is null!", result);
	}
	
	@Test
	public void testRetrievePrinterBundleListB2B_nullPointer() throws Exception {
		service = new AmindOrderHardwareService();
		contract = new HardwareCatalogContract();
		contract.setSessionHandle(handle);
		contract.setAgreementId("1-80OJBVX");
		contract.setSoldToNumber("0000180030");
		contract.setPaymentType("Ship and Bill");
		contract.setProductModel("W850n");
		contract.setProductType("Laser");
		contract.setContractNumber("0005005880");
		contract.setEffectiveDate(LangUtil.convertStringToGMTDate("07/24/2014 02:09:06"));
		contract.setStartRecordNumber(0);
		contract.setIncrement(40);
		contract.setNewQueryIndicator(true);
		
		BundleListResult result = service.retrievePrinterBundleListB2B(contract);
		System.out.println("ListSize: " + result.getBundleList().size());
		System.out.println("TotalCount: " + result.getTotalCount());
		assertNotNull("result is null!", result);
	}
	
	@Test
	public void testRetrievePrinterBundleListB2B_Hardware_CatalogListContract() throws Exception {
		service = new AmindOrderHardwareService();
		contract = new HardwareCatalogContract();
		contract.setSessionHandle(handle);
		contract.setAgreementId("Agreeement ID");
		contract.setSoldToNumber("Sold To");
		contract.setProductModel("Product Model");
		contract.setProductType("Product Type");
		contract.setEffectiveDate(LangUtil.convertStringToGMTDate("04/29/2015 11:31:00"));
		contract.setStartRecordNumber(0);
		contract.setIncrement(40);
		contract.setNewQueryIndicator(true);
		
		BundleListResult result = service.retrievePrinterBundleListB2B(contract);
		System.out.println("ListSize: " + result.getBundleList().size());
		System.out.println("TotalCount: " + result.getTotalCount());
		assertNotNull("result is null!", result);
	}
	
	@Test
	public void testRetrievePrinterBundleListB2B_Hardware_CatalogListContract_() throws Exception {
		service = new AmindOrderHardwareService();
		contract = new HardwareCatalogContract();
		contract.setSessionHandle(handle);
		contract.setAgreementId("1-4NW9QDF");
		contract.setSoldToNumber("0000215238");
//		contract.setProductModel("Product Model");
		contract.setProductType("Inkjet");
		contract.setEffectiveDate(LangUtil.convertStringToGMTDate("05/22/2015 19:48:04"));
		contract.setStartRecordNumber(0);
		contract.setIncrement(40);
		contract.setNewQueryIndicator(true);
		
		BundleListResult result = service.retrievePrinterBundleListB2B(contract);
		System.out.println("ListSize: " + result.getBundleList().size());
		System.out.println("TotalCount: " + result.getTotalCount());
		assertNotNull("result is null!", result);
	}
	
	@Test
	public void testRetrievePrinterBundleListB2B_15_7Integration() throws Exception {
		service = new AmindOrderHardwareService();
		contract = new HardwareCatalogContract();
		contract.setSessionHandle(handle);
		contract.setAgreementId("1-4NW9QDF");
		contract.setSoldToNumber("0000215238");
//		contract.setProductModel("Product Model");
		contract.setProductType("Inkjet");
		contract.setEffectiveDate(LangUtil.convertStringToGMTDate("05/22/2015 19:48:04"));
		contract.setStartRecordNumber(0);
		contract.setIncrement(40);
		contract.setNewQueryIndicator(true);
		
		BundleListResult result = service.retrievePrinterBundleListB2B(contract);
		System.out.println("ListSize: " + result.getBundleList().size());
		System.out.println("TotalCount: " + result.getTotalCount());
		assertNotNull("result is null!", result);
	}
	
	@Test
	public void testRetrievePrinterBundleListB2B_PartTypeFilter() throws Exception {
		service = new AmindOrderHardwareService();
		contract = new HardwareCatalogContract();
		contract.setSessionHandle(handle);
		contract.setAgreementId("1-4NW9QDF");
		contract.setSoldToNumber("0000215238");
		contract.setContractNumber("0005006816");
//		contract.setProductType("Laser");
		contract.setPartType("MFP Mono Laser Printer");
		contract.setEffectiveDate(LangUtil.convertStringToGMTDate("06/16/2015 20:07:17"));
		contract.setStartRecordNumber(0);
		contract.setIncrement(40);
		contract.setNewQueryIndicator(true);
		
		BundleListResult result = service.retrievePrinterBundleListB2B(contract);
		System.out.println("ListSize: " + result.getBundleList().size());
		System.out.println("TotalCount: " + result.getTotalCount());
		assertNotNull("result is null!", result);
	}
	
	@Test
	public void testRetrievePrinterBundleListB2B_dotMatrix() throws Exception {
		service = new AmindOrderHardwareService();
		contract = new HardwareCatalogContract();
		contract.setSessionHandle(handle);
		contract.setAgreementId("1-4NW9QDF");
		contract.setSoldToNumber("0000215238");
		contract.setContractNumber("0005006816");
//		contract.setProductType("Dot Matrix");
		contract.setProductType("Laser");
//		contract.setPartType("MFP Mono Laser Printer");
		contract.setEffectiveDate(LangUtil.convertStringToGMTDate("06/16/2015 20:07:17"));
		contract.setStartRecordNumber(0);
		contract.setIncrement(40);
		contract.setNewQueryIndicator(true);
		
		BundleListResult result = service.retrievePrinterBundleListB2B(contract);
		System.out.println("ListSize: " + result.getBundleList().size());
		System.out.println("TotalCount: " + result.getTotalCount());
		assertNotNull("result is null!", result);
	}
	
	@Test
	public void testRetrievePrinterBundleListB2B_NoData() throws Exception {
		service = new AmindOrderHardwareService();
		contract = new HardwareCatalogContract();
		contract.setSessionHandle(handle);
		contract.setAgreementId("1-H91ALA");
		contract.setSoldToNumber("0000126159");
		contract.setContractNumber("0005006808");
//		contract.setProductType("Inkjet");
//		contract.setProductType("Mono Laser Printer");
//		contract.setPartType("MFP Color Laser Printer");
		contract.setEffectiveDate(LangUtil.convertStringToGMTDate("06/18/2015 12:50:03"));
		contract.setStartRecordNumber(0);
		contract.setIncrement(40);
		contract.setNewQueryIndicator(true);
		
		BundleListResult result = service.retrievePrinterBundleListB2B(contract);
		System.out.println("ListSize: " + result.getBundleList().size());
		System.out.println("TotalCount: " + result.getTotalCount());
		assertNotNull("result is null!", result);
	}
	
	@Test
	public void testRetrievePrinterBundleListB2B_DuplicateRecords() throws Exception {
		service = new AmindOrderHardwareService();
		contract = new HardwareCatalogContract();
		contract.setSessionHandle(handle);
		contract.setAgreementId("1-P4I9ND0");
		contract.setSoldToNumber("0000126159");
		contract.setContractNumber("0040000406");
		contract.setLocationType("NCAL");
//		contract.setProductType("Inkjet");
//		contract.setProductType("Mono Laser Printer");
//		contract.setPartType("MFP Color Laser Printer");
		contract.setEffectiveDate(LangUtil.convertStringToISTDate("06/30/2015 13:01:09"));
		contract.setStartRecordNumber(0);
		contract.setIncrement(40);
		contract.setNewQueryIndicator(true);
		
		BundleListResult result = service.retrievePrinterBundleListB2B(contract);
		System.out.println("ListSize: " + result.getBundleList().size());
		System.out.println("TotalCount: " + result.getTotalCount());
		System.out.println("PartList size: " + result.getBundleList().get(0).getPartList().size());
		for (Bundle bundle : result.getBundleList()) {
			for (Part part : bundle.getPartList()) {
				System.out.println("PartNumber: " + part.getPartNumber());
			}
		}
		assertNotNull("result is null!", result);
	}
	
	@Test
	public void testRetrievePrinterBundleListB2B_BillingModel_for_HardwareBundles() throws Exception {
		service = new AmindOrderHardwareService();
		contract = new HardwareCatalogContract();
		contract.setSessionHandle(handle);
		contract.setAgreementId("1-P4I9ND0");
		contract.setSoldToNumber("0000126159");
		contract.setContractNumber("0040000406");
		contract.setLocationType("NCAL");
//		contract.setProductType("Inkjet");
//		contract.setProductType("Mono Laser Printer");
//		contract.setPartType("MFP Color Laser Printer");
		contract.setEffectiveDate(LangUtil.convertStringToISTDate("06/30/2015 13:01:09"));
		contract.setStartRecordNumber(0);
		contract.setIncrement(40);
		contract.setNewQueryIndicator(true);
		
		BundleListResult result = service.retrievePrinterBundleListB2B(contract);
		System.out.println("ListSize: " + result.getBundleList().size());
		System.out.println("TotalCount: " + result.getTotalCount());
		System.out.println("Billing Model: " + result.getBundleList().get(0).getBillingModel());
		assertNotNull("result is null!", result);
	}
	
	@Test
	public void testRetrievePrinterBundleListB2B_defect18355_ProductType() throws Exception {
		service = new AmindOrderHardwareService();
		contract = new HardwareCatalogContract();
		contract.setSessionHandle(handle);
		contract.setSoldToNumber("0000126159");
		contract.setAgreementId("1-P4I9ND0");
		contract.setContractNumber("0040000406");
//		contract.setLocationType("NCAL");
		contract.setProductType("Dot Matrix");
//		contract.setProductType("Mono Laser Printer");
//		contract.setPartType("MFP Color Laser Printer");
		contract.setEffectiveDate(LangUtil.convertStringToISTDate("07/06/2015 20:13:51"));
		contract.setStartRecordNumber(0);
		contract.setIncrement(40);
		contract.setNewQueryIndicator(true);
		
		BundleListResult result = service.retrievePrinterBundleListB2B(contract);
		System.out.println("ListSize: " + result.getBundleList().size());
		System.out.println("TotalCount: " + result.getTotalCount());
		assertNotNull("result is null!", result);
	}
	
	@Test
	public void testRetrievePrinterBundleListB2B_defect18355_partType() throws Exception {
		service = new AmindOrderHardwareService();
		contract = new HardwareCatalogContract();
		contract.setSessionHandle(handle);
		contract.setSoldToNumber("0000126159");
		contract.setAgreementId("1-P4I9ND0");
		contract.setContractNumber("0040000406");
//		contract.setLocationType("NCAL");
//		contract.setProductType("Laser");
//		contract.setProductType("Mono Laser Printer");
//		contract.setPartType("MFP Mono Laser Printer");
		contract.setPartType("MFP Color Laser Printer");
		contract.setEffectiveDate(LangUtil.convertStringToISTDate("07/06/2015 20:13:51"));
		contract.setStartRecordNumber(0);
		contract.setIncrement(40);
		contract.setNewQueryIndicator(true);
		
		BundleListResult result = service.retrievePrinterBundleListB2B(contract);
		System.out.println("ListSize: " + result.getBundleList().size());
		System.out.println("TotalCount: " + result.getTotalCount());
		assertNotNull("result is null!", result);
	}
	
	@Test
	public void testRetrievePrinterBundleListB2B_ConfigId() throws Exception {
		service = new AmindOrderHardwareService();
		contract = new HardwareCatalogContract();
		contract.setSessionHandle(handle);
		contract.setSoldToNumber("0000126159");
		contract.setAgreementId("1-P4I9ND0");
		contract.setContractNumber("0040000406");
//		contract.setLocationType("NCAL");
//		contract.setProductType("Laser");
//		contract.setProductType("Mono Laser Printer");
//		contract.setPartType("MFP Mono Laser Printer");
//		contract.setPartType("MFP Color Laser Printer");
		contract.setEffectiveDate(LangUtil.convertStringToISTDate("06/29/2015 16:19:10"));
		contract.setStartRecordNumber(0);
		contract.setIncrement(40);
		contract.setNewQueryIndicator(true);
		
		BundleListResult result = service.retrievePrinterBundleListB2B(contract);
		System.out.println("ListSize: " + result.getBundleList().size());
		System.out.println("TotalCount: " + result.getTotalCount());
		assertNotNull("result is null!", result);
	}
	
	@Test
	public void testRetrievePrinterBundleListB2B_GlobalSearchBundles() throws Exception {
		service = new AmindOrderHardwareService();
		contract = new HardwareCatalogContract();
		contract.setSessionHandle(handle);
		contract.setSoldToNumber("0000126159");
		contract.setAgreementId("1-P4I9ND0");
		contract.setContractNumber("0040000406");
//		contract.setLocationType("NCAL");
		contract.setProductType("Laser");
//		contract.setProductType("Mono Laser Printer");
//		contract.setPartType("MFP Mono Laser Printer");
//		contract.setPartType("MFP Color Laser Printer");
		contract.setEffectiveDate(LangUtil.convertStringToGMTDate("09/03/2015 19:50:40"));
		contract.setStartRecordNumber(0);
		contract.setIncrement(40);
		contract.setNewQueryIndicator(true);
		
		long t0 = System.currentTimeMillis();
		BundleListResult result = service.retrievePrinterBundleListB2B(contract);
		System.out.printf("Exec time=%s sec. \n", (System.currentTimeMillis() - t0) / 1000.0);
		System.out.println("ListSize: " + result.getBundleList().size());
		System.out.println("TotalCount: " + result.getTotalCount());
		assertNotNull("result is null!", result);
	}
}
  