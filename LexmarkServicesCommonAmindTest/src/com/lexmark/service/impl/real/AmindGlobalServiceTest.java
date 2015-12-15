package com.lexmark.service.impl.real;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import junit.framework.Assert;

import org.junit.Test;

import com.lexmark.contract.AddressListContract;
import com.lexmark.contract.AgreementSoldToNumberContract;
import com.lexmark.contract.AssetListContract;
import com.lexmark.contract.DunsNumberContract;
import com.lexmark.contract.FSEAccountListContract;
import com.lexmark.contract.GlobalLegalEntityContract;
import com.lexmark.contract.LegalNameContract;
import com.lexmark.contract.PartnerAccountListContract;
import com.lexmark.contract.PartnerAgreementListContract;
import com.lexmark.contract.PaymentListContract;
import com.lexmark.contract.RequestListContract;
import com.lexmark.contract.SiebelAccountIdContract;
import com.lexmark.contract.SiebelAccountListContract;
import com.lexmark.contract.SiebelLOVListContract;
import com.lexmark.domain.Account;
import com.lexmark.domain.GenericAddress;
import com.lexmark.domain.GlobalAccount;
import com.lexmark.result.AccountFlagResult;
import com.lexmark.result.AddressListResult;
import com.lexmark.result.AgreementSoldToNumberResult;
import com.lexmark.result.AssetListResult;
import com.lexmark.result.DunsNumberResult;
import com.lexmark.result.FSEAccountListResult;
import com.lexmark.result.GlobalLegalEntityResult;
import com.lexmark.result.LegalNameResult;
import com.lexmark.result.PartnerAccountListResult;
import com.lexmark.result.PartnerAgreementListResult;
import com.lexmark.result.PaymentTypeListResult;
import com.lexmark.result.RequestListResult;
import com.lexmark.result.SiebelAccountIdResult;
import com.lexmark.result.SiebelAccountListResult;
import com.lexmark.result.SiebelLOVListResult;
import com.lexmark.service.impl.real.domain.AccountAgreementDo;
import com.lexmark.service.impl.real.domain.AgreementFlagsDo;

/**
 * @author vpetruchok
 * @version 1.0, 2012-04-09
 */

public class AmindGlobalServiceTest extends AmindServiceTest {

	int exceptionCounter = 0;

	@Test
	public void testRetrieveFSEAccountList() throws Exception {
		FSEAccountListContract contract = new FSEAccountListContract();
		contract.setSiebelEmployeeId("id1");
		FSEAccountListResult r = amindGlobalService
				.retrieveFSEAccountList(contract);
		System.out.println("result=" + r.getAccountList());
	}
	
	@Test
	public void testRetrieveFSEAccountList_defect16916() throws Exception {
		FSEAccountListContract contract = new FSEAccountListContract();
		contract.setSiebelEmployeeId("1-BGMOE09");
		FSEAccountListResult r = amindGlobalService.retrieveFSEAccountList(contract);
		System.out.println("result = " + r.getAccountList());
	}
	
	@Test
	public void testRetrieveFSEAccountList_Prod_nodata() throws Exception {
		FSEAccountListContract contract = new FSEAccountListContract();
		contract.setSiebelEmployeeId("1-SX1BFEK");
		FSEAccountListResult r = amindGlobalService.retrieveFSEAccountList(contract);
		System.out.println("result = " + r.getAccountList().size());
	}

	@Test
	public void testRetrieveDunsNumber() throws Exception {
		String ns = "[testRetrieveDunsNumber] ";
		DunsNumberContract contract = new DunsNumberContract();
		contract.setMdmId("50901");
		contract.setMdmLevel("Global");
		DunsNumberResult result = amindGlobalService
				.retrieveDunsNumber(contract);
		System.out.println(ns + "DUNS Number:" + result.getDunsNumber());
		assertEquals("DUN9876545", result.getDunsNumber());
	}

	/**
	 * Do-class: GlobalLegalEntityDO.class
	 */
	@Test
	public void testRetrieveLegalName() throws Exception {
		String ns = "[testRetrieveLegalName] ";
		LegalNameContract contract = new LegalNameContract();
		contract.setMdmLevel("Global");
		contract.setLegalMdmId("020354718");
		LegalNameResult result = amindGlobalService.retrieveLegalName(contract);
		System.out.println(ns + "Legal Name: " + result.getLegalName());
		assertEquals("Bryan Cave L.L.P.", result.getLegalName());
	}

	/**
	 * Do-class: GlobalLegalEntityDO.class
	 */
	@Test
	public void testRetrieveGlobalLegalEntity_QA() throws Exception {
		String ns = "[testRetrieveGlobalLegalEntity] ";
		GlobalLegalEntityContract contract = new GlobalLegalEntityContract();
		contract.setMdmLevel("Global");
		contract.setMdmId("020354718");
		GlobalLegalEntityResult result = amindGlobalService
				.retrieveGlobalLegalEntity(contract);
		Assert.assertNotNull(result);
		Assert.assertNotNull(result.getAccount());
		System.out.println(ns + str(result.getAccount()));
	}
	
	/**
	 * Do-class: GlobalLegalEntityDO.class
	 */
	@Test
	public void testRetrieveGlobalLegalEntity_Prod() throws Exception {
		String ns = "[testRetrieveGlobalLegalEntity] ";
		GlobalLegalEntityContract contract = new GlobalLegalEntityContract();
		contract.setMdmLevel("Legal");
		contract.setMdmId("46999");
		GlobalLegalEntityResult result = amindGlobalService.retrieveGlobalLegalEntity(contract);
		System.out.println(ns + str(result.getAccount()));
	}

	/**
	 * Do-class: GlobalLegalEntityDO.class
	 */
	@Test
	public void testRetrieveGlobalLegalEntity() throws Exception {
		String ns = "[testRetrieveGlobalLegalEntity] ";
		GlobalLegalEntityContract contract = new GlobalLegalEntityContract();
		contract.setMdmLevel("Global");
		contract.setMdmId("DUN987789");
		GlobalLegalEntityResult result = amindGlobalService
				.retrieveGlobalLegalEntity(contract);
		System.out.println(ns + str(result.getAccount()));
		assertEquals("222111", result.getAccount().getDisplayMdmId());
		assertEquals("BancTec Inc USA Global", result.getAccount()
				.getLegalName());
	}

	@Test
	public void testRetrieveGlobalLegalEntityList() throws Exception {
		long t0 = System.currentTimeMillis();
		try {
			List<GlobalAccount> r = amindGlobalService
					.retrieveGlobalLegalEntityList(); // TODO(Viktor) very slow
														// operation
			assertNotNull(r);
			for (GlobalAccount globalAccount : r) {
				System.out.println(str(globalAccount));
			}
		} finally {
			System.out.printf("Excec time=%s sec. \n",
					(System.currentTimeMillis() - t0) / 1000.0);
		}
	}
	
	@Test
	public void testRetrieveGlobalLegalEntity_15_7_Global() throws Exception {
		GlobalLegalEntityContract contract = new GlobalLegalEntityContract();
		contract.setMdmLevel("Global");
		contract.setMdmId("006942395");
		GlobalLegalEntityResult result = amindGlobalService.retrieveGlobalLegalEntity(contract);
		System.out.println(result.getAccount().getLegalName());
		System.out.println(result.getAccount().getDisplayMdmId());
	}
	
	@Test
	public void testRetrieveGlobalLegalEntity_15_7_Legal() throws Exception {
		GlobalLegalEntityContract contract = new GlobalLegalEntityContract();
		contract.setMdmLevel("Legal");
		contract.setMdmId("155727");
		GlobalLegalEntityResult result = amindGlobalService.retrieveGlobalLegalEntity(contract);
		System.out.println(result.getAccount().getLegalName());
		System.out.println(result.getAccount().getDisplayMdmId());
	}

   @Test
    public void testRetrievePartnerAccountList_defect10123() throws Exception {
    	PartnerAccountListContract contract = new PartnerAccountListContract();
    	contract.setMdmId("30145"); 
    	contract.setMdmLevel("Legal");
    	contract.setMassUploadFlag(true);
    	PartnerAccountListResult result = amindGlobalService.retrievePartnerAccountList(contract);
    	MiscTest.print(result.getAccountList(), "accountId");
    }
    
    @Test
    public void testRetrievePartnerAccountList_nodata() throws Exception {
    	PartnerAccountListContract contract = new PartnerAccountListContract();
    	contract.setMdmId("1-4AUAPWN"); 
    	contract.setMdmLevel("Siebel");
    	PartnerAccountListResult result = amindGlobalService.retrievePartnerAccountList(contract);
    	MiscTest.print(result.getAccountList(), "accountId");
    }
    
    @Test
    public void testRetrievePartnerAccountList_defect12860_user1() throws Exception {
    	PartnerAccountListContract contract = new PartnerAccountListContract();
    	contract.setMdmId("1-4IG6Q8D"); 
    	contract.setMdmLevel("Siebel");
    	PartnerAccountListResult result = amindGlobalService.retrievePartnerAccountList(contract);
    	MiscTest.print(result.getAccountList(), "accountId");
    }
    
    @Test
    public void testRetrievePartnerAccountList_defect12860_user2() throws Exception {
    	PartnerAccountListContract contract = new PartnerAccountListContract();
    	contract.setMdmId("138475"); 
    	contract.setMdmLevel("Legal");
    	PartnerAccountListResult result = amindGlobalService.retrievePartnerAccountList(contract);
    	MiscTest.print(result.getAccountList(), "accountId");
    }
    
    @Test
    public void testRetrievePartnerAccountList_defect12860_user3() throws Exception {
    	PartnerAccountListContract contract = new PartnerAccountListContract();
    	contract.setMdmId("3671"); 
    	contract.setMdmLevel("Account");
    	PartnerAccountListResult result = amindGlobalService.retrievePartnerAccountList(contract);
    	MiscTest.print(result.getAccountList(), "accountId");
    }
    
    @Test
    public void testRetrievePartnerAccountList_defect12860_user4() throws Exception {
    	PartnerAccountListContract contract = new PartnerAccountListContract();
    	contract.setMdmId("41647"); 
    	contract.setMdmLevel("Legal");
    	PartnerAccountListResult result = amindGlobalService.retrievePartnerAccountList(contract);
    	MiscTest.print(result.getAccountList(), "accountId");
    }

	@Test
	public void testRetrievePartnerAccountList() throws Exception {
		PartnerAccountListContract contract = new PartnerAccountListContract();
		// contract.setMdmId("50901");
		contract.setMdmId("68724");
		contract.setMdmLevel("Legal");
		PartnerAccountListResult result = amindGlobalService
				.retrievePartnerAccountList(contract);
		MiscTest.print(result.getAccountList());
	}

	/**
	 * Bienvenido Jonathan Castrejon Salas, Juan Carlos Gonzalez have the same
	 * mdmId, mdmLevel .
	 * 
	 */
	@Test
	public void testRetrievePartnerAccountList_Defect6622_Production()
			throws Exception {
		PartnerAccountListContract contract = new PartnerAccountListContract();
		// contract.setMdmId("7417");
		// contract.setMdmLevel("Legal");
		contract.setMdmId("68724");
		contract.setMdmLevel("Legal");
		PartnerAccountListResult result = amindGlobalService
				.retrievePartnerAccountList(contract);
		MiscTest.print(result.getAccountList(), "accountId", "accountName",
				"accessServiceOrderFlag" /* Can Fulfill Orders */,
				"createRequestsForCustomerFlag" /* Can Create Customer Orders */);
	}

	@Test
	public void testRetrieveSiebelAccountId() throws Exception {
		SiebelAccountIdContract contract = new SiebelAccountIdContract();
		contract.setMdmId("50901");
		contract.setMdmLevel("Legal");
		SiebelAccountIdResult r = amindGlobalService
				.retrieveSiebelAccountId(contract);
		System.out.println("siebelAccountId = " + r.getSiebelAccountId());
	}

	@Test
	public void testRetrieveSiebelLOVList() throws Exception {
		SiebelLOVListContract contract = new SiebelLOVListContract();
		contract.setErrorCode1("mock-errorCod1");
		contract.setLovName("mock-LovName");
		SiebelLOVListResult result = amindGlobalService
				.retrieveSiebelLOVList(contract);
		MiscTest.print(result.getLovList());
	}

	@SuppressWarnings("serial")
	@Test
	public void testInitCrmSessionHandle_logging() throws Exception {
		amindGlobalService.initCrmSessionHandle(new AmindCrmSessionHandle() {

			@Override
			public void acquireSimple() throws InterruptedException {
				throw new RuntimeException("logging test");
			}
		});
	}

	@Test
	public void testReleaseSessionHandle() throws Exception {
		AmindCrmSessionHandle handle = new AmindCrmSessionHandle();
		amindGlobalService.releaseSessionHandle(handle);
		amindGlobalService.releaseSessionHandle(handle);
	}

	@Test
	public void testRetrievePartnerAgreementList() throws Exception {
		PartnerAgreementListContract c = new PartnerAgreementListContract();
		c.setMdmId("mock-mdmId");
		c.setMdmLevel("Domestic");
		PartnerAgreementListResult r = amindGlobalService
				.retrievePartnerAgreementList(c);
		MiscTest.print(r.getPartnerAgreementList());

	}

	@Test
	public void testRetrieveSiebelAgreementList_QA() throws Exception {
		SiebelAccountListContract c = new SiebelAccountListContract();
		c.setSessionHandle(AmindServiceTest.crmSessionHandle);
		c.setMdmId("4479");
		c.setMdmLevel("Legal");
		long t0 = System.currentTimeMillis();
		try {
			SiebelAccountListResult r = amindGlobalService
					.retrieveSiebelAgreementList(c);
			MiscTest.print(r.getAccountList());
		} finally {
			System.out.printf("Exec time=%s sec. \n",
					(System.currentTimeMillis() - t0) / 1000.0);
		}
	}

	@Test
	public void testRetrieveSiebelAgreementList_QA_byAccountId()
			throws Exception {
		SiebelAccountListContract c = new SiebelAccountListContract();
		c.setSessionHandle(AmindServiceTest.crmSessionHandle);
		c.setAccountId("1-P8GS10");
		long t0 = System.currentTimeMillis();
		try {
			SiebelAccountListResult r = amindGlobalService
					.retrieveSiebelAgreementList(c);
			MiscTest.print(r.getAccountList());
		} finally {
			System.out.printf("Exec time=%s sec. \n",
					(System.currentTimeMillis() - t0) / 1000.0);
		}
	}

	@Test
	public void testRetrieveSiebelAgreementList_queryAccountAgreementDo()
			throws Exception {
		List<AccountAgreementDo> r = MiscTest.sampleSiebelQuery(
				AccountAgreementDo.class,
				// ""
				// "[Id] <> ''"
				"[Id] = '1-P8GS10'", 10);

		MiscTest.print(r, "id", "accountId", "accountName");
	}

	@Test
	public void testRetrieveSiebelAgreementList_queryAgreementFlagsDo()
			throws Exception {
		List<?> r = MiscTest.sampleSiebelQuery(AgreementFlagsDo.class,
		// ""
		// "[Id] <> ''"
				"[Id] = '1-P8GS10'", 10);

		MiscTest.print(r, "id", "name");
	}

	@Test
	public void testRetrieveCatalogAgreementList_blankValue() throws Exception {
		SiebelAccountListContract contract = new SiebelAccountListContract();
		contract.setMdmLevel("Siebel");
		contract.setMdmId("1-YFNVUX");
		contract.setNewQueryIndicator(true);
		contract.setVendorFlag(false);
		contract.setAgreementFlag(true);
		contract.setHardwareFlag(false);
		contract.setSessionHandle(crmSessionHandle);

		SiebelAccountListResult result = amindGlobalService
				.retrieveCatalogAgreementList(contract);
		for (Account acc : result.getAccountList()) {
			System.out.println("Organization name: "
					+ acc.getOrganizationName());
		}
		MiscTest.print("Result: ", result);
	}

    @Test
    public void testRetrieveCatalogAgreementList_defect13145() throws Exception {
    	SiebelAccountListContract contract = new SiebelAccountListContract();
    	contract.setMdmLevel("Legal");
    	contract.setMdmId("65706");
    	contract.setNewQueryIndicator(true);
    	contract.setVendorFlag(false);
    	contract.setAgreementFlag(true);
    	contract.setHardwareFlag(true);
    	contract.setSessionHandle(crmSessionHandle);
    	
    	SiebelAccountListResult result = amindGlobalService.retrieveCatalogAgreementList(contract);
    	logger.debug(result.getAccountList());
    }
    
    @Test
    public void testRetrieveCatalogAgreementList_defect13148() throws Exception {
    	SiebelAccountListContract contract = new SiebelAccountListContract();
    	contract.setMdmLevel("Global");
    	contract.setMdmId("411189202");
    	contract.setNewQueryIndicator(true);
    	contract.setVendorFlag(false);
    	contract.setAgreementFlag(true);
    	contract.setHardwareFlag(true);
    	contract.setSessionHandle(crmSessionHandle);
    	SiebelAccountListResult result = amindGlobalService.retrieveCatalogAgreementList(contract);
    	
    	for (Account account : result.getAccountList()) {
			String agreementName = account.getAgreementName();
			List<String> soldToNumbers = account.getSoldToNumbers();
			if ("STMicroelectronics GmbH".equalsIgnoreCase(account.getAccountName()) 
					&& "STMicroelectronics GmbH_MSA 2012_MPS-DE".equalsIgnoreCase(agreementName)) {
				for (String soldTo : soldToNumbers) {
					System.out.println("Soldto: " + soldTo);
				}
			}
		}
    	logger.debug(result.getAccountList());
    }

	@Test
	public void testRetrievePaymentTypeListhrows() throws Exception {
		PaymentListContract contract = new PaymentListContract();
		contract.setMdmLevel("Siebel");
		contract.setMdmId("1-YFNVUX");
		contract.setAgreementId("1-5UNNARF");
		contract.setSoldToNumber("0000222911");
		contract.setNewQueryIndicator(false);
		contract.setIncrement(0);
		contract.setStartRecordNumber(0);
		contract.setSessionHandle(crmSessionHandle);
		contract.setHardwareFlag(true);

		PaymentTypeListResult result = amindGlobalService
				.retrievePaymentTypeList(contract);

		ArrayList<String> paymentTypes = result.getPaymentType();

		System.out.println("Starting...");

		System.out.println("Size: " + paymentTypes.size());

		for (String paymentType : paymentTypes) {
			System.out.println("Payment type: " + paymentType);
		}

		System.out.println("End.");

	}

	@Test
	public void testRetrievePaymentTypeList() throws Exception {
		PaymentListContract contract = new PaymentListContract();
		contract.setAgreementId("1-5UNNARF");
		contract.setSoldToNumber("0000107608");
		contract.setHardwareFlag(false);
		contract.setSessionHandle(crmSessionHandle);

		PaymentTypeListResult result = amindGlobalService
				.retrievePaymentTypeList(contract);

		ArrayList<String> paymentTypes = result.getPaymentType();

		System.out.println("Starting...");

		System.out.println("Size: " + paymentTypes.size());

		for (String paymentType : paymentTypes) {
			System.out.println("Payment type: " + paymentType);
		}

		System.out.println("End.");
	}

	@Test
	public void testRetrieveBillToAddressList_MPS_QA_defect7788()
			throws Exception {
		AddressListContract c = new AddressListContract();
		c.setSessionHandle(AmindServiceTest.crmSessionHandle);
		List<String> soldToNymbers = Arrays.asList("0000107608", "012345",
				"0000222911", "123", "00001076084", "217722", "0000164168");
		c.setSoldToNumbers(soldToNymbers);
		long t0 = System.currentTimeMillis();
		try {
			AddressListResult r = amindGlobalService
					.retrieveBillToAddressList(c);

			for (GenericAddress add : r.getAddressList()) {
				System.out.println(add.getAddressName());
			}
			MiscTest.print(r.getAddressList());
		} finally {
			System.out.printf("Exec time=%s sec. \n",
					(System.currentTimeMillis() - t0) / 1000.0);
		}
	}

	@Test
	public void testRetrieveSiebelAgreementList_RetrievingSiebelAgreementListFailed()
			throws Exception {
		// SiebelAccountListContract contract = new SiebelAccountListContract();
		// contract.setMdmLevel("Siebel");
		// contract.setMdmId("1-3HPF0KS");
		// contract.setNewQueryIndicator(true);
		// contract.setVendorFlag(false);
		// contract.setAgreementFlag(false);
		// contract.setHardwareFlag(false);
		// contract.setSessionHandle(crmSessionHandle);
		//
		// SiebelAccountListResult result =
		// amindGlobalService.retrieveSiebelAgreementList(contract);
		//
		// System.out.println(result.getAccountList().size());
		//
		// MiscTest.print(result.getAccountList());

		SiebelAccountListContract contract = new SiebelAccountListContract();
		contract.setMdmLevel("Legal");
		contract.setMdmId("43800");
		contract.setNewQueryIndicator(true);
		contract.setVendorFlag(false);
		contract.setAgreementFlag(true);
		contract.setHardwareFlag(true);
		contract.setSessionHandle(crmSessionHandle);

		SiebelAccountListResult result = amindGlobalService
				.retrieveCatalogAgreementList(contract);

		System.out.println(result.getAccountList().size());

		for (Account acc : result.getAccountList()) {
			if (acc.getSoldToNumbers() != null) {
				System.out.println(acc.getSoldToNumbers().size());
			}
		}
	}
	
	@Test
	public void testRetrieveSiebelAgreementList_defect18129() throws Exception {
		SiebelAccountListContract contract = new SiebelAccountListContract();
		contract.setMdmLevel("Siebel");
		contract.setMdmId("1-8OH-4730");
		contract.setNewQueryIndicator(true);
		contract.setVendorFlag(false);
		contract.setPartnerPortal(false);
		contract.setAgreementFlag(false);
		contract.setHardwareFlag(false);
		contract.setSessionHandle(crmSessionHandle);
		
		SiebelAccountListResult result = amindGlobalService.retrieveSiebelAgreementList(contract);
		System.out.println(result.getAccountList().size());
		for (Account account : result.getAccountList()) {
			System.out.println(account.getQuantityServices());
			System.out.println(account.getQuantitySupplies());
		}
	}

	@Test
	public void testRetrieveBillToAddressList_QA_defect11909()	throws Exception {
		AddressListContract c = new AddressListContract();
		c.setSessionHandle(AmindServiceTest.crmSessionHandle);
		List<String> soldToNymbers = Arrays.asList("0000180030", "0000222971");
		c.setSoldToNumbers(soldToNymbers);
		long t0 = System.currentTimeMillis();
		try {
			AddressListResult r = amindGlobalService.retrieveBillToAddressList(c);
			MiscTest.print(r.getAddressList());
		} finally {
			System.out.printf("Exec time=%s sec. \n", (System.currentTimeMillis() - t0) / 1000.0);
		}
	}

	@Test
	public void testRetrieveBillToAddressList_QA_defect11909_error()	throws Exception {
		AddressListContract c = new AddressListContract();
		c.setSessionHandle(AmindServiceTest.crmSessionHandle);
		List<String> soldToNymbers = Arrays.asList("0000180030", "0000222971");
		c.setSoldToNumbers(soldToNymbers);
		long t0 = System.currentTimeMillis();
		try {
			AddressListResult r = amindGlobalService.retrieveBillToAddressList(c);
			MiscTest.print(r.getAddressList());
		} finally {
			System.out.printf("Exec time=%s sec. \n", (System.currentTimeMillis() - t0) / 1000.0);
		}
	}

	@Test
	public void testRetrieveCatalogAgreementList_defect8576() throws Exception {
		SiebelAccountListContract contract = new SiebelAccountListContract();
		contract.setMdmLevel("Legal");
		contract.setMdmId("59098");
		contract.setNewQueryIndicator(true);
		contract.setVendorFlag(false);
		contract.setAgreementFlag(true);
		contract.setHardwareFlag(true);
		contract.setSessionHandle(crmSessionHandle);

		SiebelAccountListResult result = amindGlobalService
				.retrieveCatalogAgreementList(contract);

		List<Account> list = result.getAccountList();

		System.out.println(list.size());

		for (Account account : list) {
			System.out.println("SoldToNumber size: "
					+ account.getSoldToNumbers().size());
			for (String soldTo : account.getSoldToNumbers()) {
				System.out.println(soldTo);
			}

			// System.out.println(account.getContractLine());
			// System.out.println(account.getContractNumber());
			//
			// System.out.println(account.getOrganizationName());
		}
	}

	@Test
	public void testRetrieveCatalogAgreementList_ContractNumber()
			throws Exception {
		SiebelAccountListContract contract = new SiebelAccountListContract();
		contract.setMdmLevel("Legal");
		contract.setMdmId("2216");
		contract.setNewQueryIndicator(true);
		contract.setVendorFlag(false);
		contract.setAgreementFlag(true);
		contract.setHardwareFlag(true);
		contract.setSessionHandle(crmSessionHandle);

		SiebelAccountListResult result = amindGlobalService
				.retrieveCatalogAgreementList(contract);

		List<Account> list = result.getAccountList();

		System.out.println(list.size());

		// for (Account account : list) {
		// System.out.println("Account id: " + account.getAccountId());
		// System.out.println("Agreement id: " + account.getAgreementId());

		// if(account.getAgreementId().equalsIgnoreCase("1-69NLZIF")) {
		// System.out.println("Account id: " + account.getAccountId());
		// System.out.println(account.getSoldToNumbers().size());
		// System.out.println("Agreement id: " + account.getAgreementId());
		// System.out.println("Contract line: " + account.getContractLine());
		// }
		// }

		// for (Account account : list) {
		// System.out.println("SoldToNumber size: " +
		// account.getSoldToNumbers().size());
		// for (String soldTo : account.getSoldToNumbers()) {
		// System.out.println(soldTo);
		// }

		// System.out.println(account.getContractLine());
		// System.out.println(account.getContractNumber());
		//
		// System.out.println(account.getOrganizationName());
		// }
	}

	@Test
	public void testRetrievePaymentTypeList_defect8846() throws Exception {
		PaymentListContract contract = new PaymentListContract();
		contract.setMdmLevel("Legal");
		contract.setMdmId("2216");
		contract.setSoldToNumber("0000319381");
		contract.setAgreementId("1-69NLZIF");
		contract.setContractNumber("0005001453");
		contract.setHardwareFlag(false);
		contract.setIncrement(0);
		contract.setStartRecordNumber(0);
		contract.setNewQueryIndicator(false);
		contract.setSessionHandle(crmSessionHandle);

		PaymentTypeListResult result = amindGlobalService
				.retrievePaymentTypeList(contract);

		ArrayList<String> paymentTypes = result.getPaymentType();
	}

	@Test
	public void testRetrieveCatalogAgreementList_defect9056() throws Exception {
		SiebelAccountListContract contract = new SiebelAccountListContract();
		contract.setMdmLevel("Legal");
		contract.setMdmId("59098");
		contract.setNewQueryIndicator(true);
		contract.setVendorFlag(false);
		contract.setAgreementFlag(true);
		contract.setHardwareFlag(true);
		contract.setSessionHandle(crmSessionHandle);

		SiebelAccountListResult result = amindGlobalService
				.retrieveCatalogAgreementList(contract);

		List<Account> list = result.getAccountList();

		System.out.println(list.size());
	}

	@Test
	public void testRetrieveBillToAddressList_HardwareAndConsumables()
			throws Exception {
		AddressListContract contract = new AddressListContract();
		contract.setSessionHandle(crmSessionHandle);
		contract.setFavoriteFlag(false);
		List<String> soldToNymbers = Arrays.asList("0000319381", "0000319381");
		contract.setSoldToNumbers(soldToNymbers);
		contract.setIncrement(0);
		contract.setStartRecordNumber(0);
		contract.setNewQueryIndicator(false);

		AddressListResult result = amindGlobalService
				.retrieveBillToAddressList(contract);

		for (GenericAddress add : result.getAddressList()) {
			System.out.println("Bill to number: " + add.getBillToNumber());
		}
	}

	@Test
	public void testRetrieveBillToAddressList_QA_defect12271()	throws Exception {
		AddressListContract c = new AddressListContract();
		c.setSessionHandle(AmindServiceTest.crmSessionHandle);
		List<String> soldToNymbers = Arrays.asList("0000220903", "0000222971");
		c.setSoldToNumbers(soldToNymbers);
		long t0 = System.currentTimeMillis();
		try {
			AddressListResult r = amindGlobalService.retrieveBillToAddressList(c);
			MiscTest.print(r.getAddressList());
		} finally {
			System.out.printf("Exec time=%s sec. \n", (System.currentTimeMillis() - t0) / 1000.0);
		}
	}

	@Test
	public void testRetrieveCatalogAgreementList_defect9222() throws Exception {
		SiebelAccountListContract contract = new SiebelAccountListContract();
		contract.setMdmLevel("Legal");
		contract.setMdmId("43800");
		contract.setNewQueryIndicator(true);
		contract.setVendorFlag(false);
		contract.setAgreementFlag(true);
		contract.setHardwareFlag(true);
		contract.setSessionHandle(crmSessionHandle);

		SiebelAccountListResult result = amindGlobalService
				.retrieveCatalogAgreementList(contract);

		List<Account> list = result.getAccountList();

		System.out.println(list.size());

		for (Account account : list) {
			System.out.println(account.getAgreementName());
		}
	}

	@Test
	public void testRetrieveCatalogAgreementList_defect9247() throws Exception {

		SiebelAccountListContract contract2 = new SiebelAccountListContract();

		contract2.setMdmLevel("Legal");
		contract2.setMdmId("28647");
		contract2.setNewQueryIndicator(true);
		contract2.setVendorFlag(false);
		contract2.setAgreementFlag(false);

		AccountFlagResult result = amindGlobalService
				.retrieveEntitelmentFlags(contract2);

		System.out.println(result.getAccountList() == null);

		// for (Account account : result.getAccountList()) {
		// System.out.println("'CatalogEntitlementFlag': " +
		// account.isCatalogEntitlementFlag());
		// }
	}

	@Test
	public void testRetrievePaymentTypeList_Accessories() throws Exception {
		PaymentListContract contract = new PaymentListContract();
		contract.setMdmLevel("Legal");
		contract.setMdmId("61544");
		contract.setSoldToNumber("0000198238");
		contract.setAgreementId("1-6DL8ZYF");
		contract.setContractNumber("0005001808");
		contract.setHardwareFlag(true);
		contract.setIncrement(0);
		contract.setStartRecordNumber(0);
		contract.setNewQueryIndicator(false);
		contract.setSessionHandle(crmSessionHandle);

		PaymentTypeListResult result = amindGlobalService
				.retrievePaymentTypeList(contract);

		ArrayList<String> paymentTypes = result.getPaymentType();

		System.out.println(paymentTypes == null ? "Null" : paymentTypes.size());
	}

	@Test
	public void testRetrievePaymentTypeList_defect9340() throws Exception {
		PaymentListContract contract = new PaymentListContract();
		contract.setMdmLevel("Legal");
		contract.setMdmId("61544");
		contract.setSoldToNumber("0000198238");
		contract.setAgreementId("1-6DL8ZYF");
		contract.setContractNumber("0005001808");
		contract.setHardwareFlag(true);
		contract.setIncrement(0);
		contract.setStartRecordNumber(0);
		contract.setNewQueryIndicator(false);
		contract.setSessionHandle(crmSessionHandle);

		PaymentTypeListResult result = amindGlobalService
				.retrievePaymentTypeList(contract);

		ArrayList<String> paymentTypes = result.getPaymentType();

		System.out.println(paymentTypes == null ? "Null" : paymentTypes.size());

		for (String paymentType : paymentTypes) {
			System.out.println(paymentType);
		}
	}

	@Test
	public void testRetrieveBillToAddressList_defect9534() throws Exception {
		AddressListContract contract = new AddressListContract();

		contract.setSessionHandle(crmSessionHandle);
		contract.setFavoriteFlag(false);
		List<String> soldToNumbers = new ArrayList<String>();
		soldToNumbers.add("0000221998");
		soldToNumbers.add("0000221998");
		contract.setSoldToNumbers(soldToNumbers);
		contract.setNewQueryIndicator(false);
		contract.setIncrement(0);
		contract.setStartRecordNumber(0);

		AddressListResult result = amindGlobalService
				.retrieveBillToAddressList(contract);

		System.out.println("totalCount=" + result.getTotalCount());
		System.out.println(result.getAddressList().size());
	}

	@Test
	public void test_defect9534_Multithreading() throws Exception {

		final AssetListContract contract = new AssetListContract();
		contract.setMdmLevel("Legal");
		contract.setMdmId("9510");
		contract.setContactId("1-6ENUA8P");
		contract.setFavoriteFlag(false);
		contract.setLoadAllFlag(false);
		contract.setEntitlementEndDate("10/18/2013");
		Map<String, Object> sortMap = new HashMap<String, Object>();
		sortMap.put("serialNumber", "ASCENDING");
		contract.setSortCriteria(sortMap);
		contract.setIncrement(40);
		contract.setStartRecordNumber(0);
		contract.setNewQueryIndicator(true);
		contract.setSessionHandle(crmSessionHandle);

		final AmindOrderSuppliesAssetService amindOrderSuppliesAssetService = new AmindOrderSuppliesAssetService();
		amindOrderSuppliesAssetService
				.setStatelessSessionFactory(statelessSessionFactory);

		final SiebelAccountListContract contract2 = new SiebelAccountListContract();
		contract2.setMdmLevel("Legal");
		contract2.setMdmId("9510");
		contract2.setNewQueryIndicator(true);
		contract2.setVendorFlag(false);
		contract2.setAgreementFlag(true);
		contract2.setHardwareFlag(false);
		contract2.setSessionHandle(crmSessionHandle);

		final AddressListContract contract3 = new AddressListContract();

		contract3.setFavoriteFlag(false);
		List<String> soldToNumbers = new ArrayList<String>();
		soldToNumbers.add("0000221998");
		soldToNumbers.add("0000221998");
		contract3.setSoldToNumbers(soldToNumbers);
		contract3.setNewQueryIndicator(false);
		contract3.setIncrement(0);
		contract3.setStartRecordNumber(0);
		contract3.setSessionHandle(crmSessionHandle);

		ExecutorService executor = Executors.newScheduledThreadPool(3);

		Future<AssetListResult> first = executor
				.submit(new Callable<AssetListResult>() {
					public AssetListResult call() throws Exception {
						System.out.println("First started");
						AssetListResult result = amindOrderSuppliesAssetService
								.retrieveDeviceList(contract);
						System.out.println("First finished");
						return result;

						// return null;
					}
				});

		Future<SiebelAccountListResult> second = executor
				.submit(new Callable<SiebelAccountListResult>() {
					public SiebelAccountListResult call() throws Exception {

						Thread.sleep(1000);

						System.out.println("Second started");
						SiebelAccountListResult result = amindGlobalService
								.retrieveCatalogAgreementList(contract2);
						System.out.println("Second finished");
						return result;

						// return null;
					}
				});

		Future<AddressListResult> third = executor
				.submit(new Callable<AddressListResult>() {
					public AddressListResult call() throws Exception {

						Thread.sleep(11000);

						System.out.println("Third started");
						AddressListResult result = amindGlobalService
								.retrieveBillToAddressList(contract3);
						System.out.println("Third finished");
						return result;

						// return null;
					}
				});

		executor.shutdown();

		try {
			AssetListResult res1 = first.get();
		} catch (Exception ex) {
			System.out.println(ex);
		}

		try {
			SiebelAccountListResult res2 = second.get();
		} catch (Exception ex) {
			System.out.println(ex);
		}

		try {
			AddressListResult res3 = third.get();
		} catch (Exception ex) {
			System.out.println(ex);
		}

		System.out.println("End");
	}

	@Test
	public void testRetrieveSiebelLOVList_defect8163() throws Exception {
		SiebelLOVListContract contract = new SiebelLOVListContract();
		contract.setParentName("Carrier Travel");
		contract.setErrorCode1("Carrier Travel");
		contract.setLovName("LXK_SERVICE_ERR_CODE_2");

		SiebelLOVListResult result = amindGlobalService
				.retrieveSiebelLOVList(contract);

		System.out.println("Size: " + result.getLovList().size());
	}
	
	@Test
	public void testRetrieveSiebelLOVList_listCountIssue() throws Exception {
		SiebelLOVListContract contract = new SiebelLOVListContract();
//		contract.setParentName("Abnormal Noises / Sounds");
//		contract.setErrorCode1("Image Quality Issues");
		contract.setLovName("LXK_PART_DISPOSITION");
		SiebelLOVListResult result = amindGlobalService.retrieveSiebelLOVList(contract);
		System.out.println("Size: " + result.getLovList().size());
	}
	
	@Test
	public void testRetrieveSiebelLOVList_defect17541() throws Exception {
		SiebelLOVListContract contract = new SiebelLOVListContract();
//		contract.setParentName("Abnormal Noises / Sounds");
//		contract.setErrorCode1("Image Quality Issues");
		contract.setLovName("LXK_SERVICE_ERR_CODE_2");
		SiebelLOVListResult result = amindGlobalService.retrieveSiebelLOVList(contract);
		System.out.println("Size: " + result.getLovList().size());
	}

	@Test
	public void testRetrieveSoldToList() throws Exception {
		AgreementSoldToNumberContract contract = new AgreementSoldToNumberContract();
		contract.setAgreementId("1-2NYIHIO");
		contract.setContractNumber("0005001855");
		contract.setIncrement(0);
		contract.setStartRecordNumber(0);
		contract.setNewQueryIndicator(false);
		contract.setSessionHandle(crmSessionHandle);

		// try {
		AgreementSoldToNumberResult result = amindGlobalService
				.retrieveSoldToList(contract);
		System.out.println(result.getSoldToNumbers() == null ? "Null"
				: "Size: " + result.getSoldToNumbers().size());

		for (String soldToNumber : result.getSoldToNumbers()) {
			System.out.println(soldToNumber);
		}
		// }
		// catch(Exception ex) {
		// System.out.println(ex.getMessage());
		// }

	}

	@Test
	public void testRetrievePartnerAccountList_defect10083() throws Exception {
		PartnerAccountListContract contract = new PartnerAccountListContract();
		contract.setMdmId("1-4LLYQJK");
		contract.setMdmLevel("Siebel");
		contract.setMassUploadFlag(true);

		PartnerAccountListResult result = amindGlobalService
				.retrievePartnerAccountList(contract);

		System.out.println(result.getAccountList().size());
		for (Account account : result.getAccountList()) {
			System.out.println("Account name: " + account.getAccountName());
			System.out.println(account.getAgreementName());
		}
	}

	@Test
	public void testRetrievePartnerAccountList_defect10315() throws Exception {
		PartnerAccountListContract contract = new PartnerAccountListContract();
		contract.setMdmId("23259");
		contract.setMdmLevel("Legal");

		PartnerAccountListResult result = amindGlobalService
				.retrievePartnerAccountList(contract);

		System.out.println(result.getAccountList().size());
		for (Account account : result.getAccountList()) {
			System.out.println("Account ID: " + account.getAccountId());
		}
	}

	@Test
	public void testRetrieveBillToAddressList_defect9732() throws Exception {
		AddressListContract contract = new AddressListContract();

		contract.setSessionHandle(crmSessionHandle);
		contract.setFavoriteFlag(false);
		List<String> soldToNumbers = new ArrayList<String>();
		soldToNumbers.add("0000227605");
		soldToNumbers.add("0000227605");
		contract.setSoldToNumbers(soldToNumbers);
		contract.setNewQueryIndicator(false);
		contract.setIncrement(0);
		contract.setStartRecordNumber(0);

		AddressListResult result = amindGlobalService
				.retrieveBillToAddressList(contract);

		System.out.println("totalCount=" + result.getTotalCount());
		System.out.println(result.getAddressList().size());
	}

	@Test
	public void testRetrievePartnerAccountList_defect10486() throws Exception {
		PartnerAccountListContract contract = new PartnerAccountListContract();
		contract.setMdmId("65500");
		contract.setMdmLevel("Legal");

		PartnerAccountListResult result = amindGlobalService
				.retrievePartnerAccountList(contract);

		System.out.println(result.getAccountList().size());
		for (Account account : result.getAccountList()) {
			System.out.println(account.isPartnerRequestTabHideFlag());
			System.out.println("Account Id: " + account.getAccountId());
		}
	}

	@Test
	public void testRetrieveCatalogAgreementList_defect10944_1()
			throws Exception {
		SiebelAccountListContract contract = new SiebelAccountListContract();
		contract.setMdmLevel("Legal");
		contract.setMdmId("7816");
		contract.setNewQueryIndicator(true);
		contract.setVendorFlag(false);
		contract.setAgreementFlag(true);
		contract.setHardwareFlag(true);
		contract.setSessionHandle(crmSessionHandle);

		SiebelAccountListResult result = amindGlobalService
				.retrieveCatalogAgreementList(contract);

		List<Account> list = result.getAccountList();

		System.out.println(list.size());

		for (Account account : list) {
			// System.out.println(account.getAgreementName());
			System.out.println("Contract name: " + account.getContractLine());
		}
	}

	@Test
	public void testRetrieveCatalogAgreementList_defect10944_2()
			throws Exception {
		SiebelAccountListContract contract = new SiebelAccountListContract();
		contract.setMdmLevel("Legal");
		contract.setMdmId("10020");
		contract.setNewQueryIndicator(true);
		contract.setVendorFlag(false);
		contract.setAgreementFlag(true);
		contract.setHardwareFlag(false);
		contract.setSessionHandle(crmSessionHandle);

		SiebelAccountListResult result = amindGlobalService
				.retrieveCatalogAgreementList(contract);

		List<Account> list = result.getAccountList();

		System.out.println(list.size());

		for (Account account : list) {
			// System.out.println(account.getAgreementName());
			System.out.println("Contract name: " + account.getContractLine());
		}
	}

	@Test
	public void testRetrieveHardwareList_defect11130() throws Exception {

		final AmindRequestTypeService requestService = new AmindRequestTypeService();
		final AmindGlobalService globalService = new AmindGlobalService();

		// ExecutorService executor = Executors.newFixedThreadPool(20);
		ExecutorService executor = Executors.newFixedThreadPool(2);
		List<Future<?>> list = new ArrayList<Future<?>>();

		for (int i = 0; i < 10; i++) {
			list.add(executor.submit(new Runnable() {

				@Override
				public void run() {

					System.out.println("First Started");

					RequestListContract contract = new RequestListContract();
					contract.setVendorFlag(false);
					contract.setAssetFavoriteFlag(false);
					contract.setShowAllFlag(true);
					contract.setChangeRequestFlag(false);
					contract.setHardwareRequestFlag(false);
					contract.setMdmLevel("Legal");
					contract.setMdmId("50801");

					Map<String, Object> sortCriteria = new HashMap<String, Object>();
					sortCriteria.put("serviceRequestNumber", "ASCENDING");
					contract.setSortCriteria(sortCriteria);

					contract.setIncrement(40);
					contract.setStartRecordNumber(0);
					contract.setNewQueryIndicator(true);

					Map<String, Object> filterCriteria = new HashMap<String, Object>();
					filterCriteria.put("serviceRequest.endDate",
							"01/14/2014 20:18:23");
					filterCriteria.put("requestType", Arrays.asList(
							"Consumables Management", "Fleet Management",
							"BreakFix"));
					filterCriteria.put("serviceRequest.startDate",
							"12/30/2013 05:00:00");
					contract.setFilterCriteria(filterCriteria);

					contract.setSessionHandle(crmSessionHandle);

					try {
						RequestListResult result = requestService
								.retrieveRequestList(contract);
					} catch (Exception e) {
						e.printStackTrace();
						exceptionCounter++;
					}

					System.out.println("First Finished");
				}
			}));

			list.add(executor.submit(new Runnable() {

				@Override
				public void run() {

					try {
						Thread.sleep(2000);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}

					System.out.println("Second Started");

					AddressListContract contract = new AddressListContract();

					contract.setSessionHandle(crmSessionHandle);
					contract.setFavoriteFlag(false);
					List<String> soldToNumbers = new ArrayList<String>();
					soldToNumbers.add("0000126998");
					soldToNumbers.add("0000126998");
					soldToNumbers.add("0000216997");
					soldToNumbers.add("0000126998");
					contract.setSoldToNumbers(soldToNumbers);
					contract.setNewQueryIndicator(false);
					contract.setIncrement(0);
					contract.setStartRecordNumber(0);

					try {
						AddressListResult result = globalService
								.retrieveBillToAddressList(contract);
					} catch (Exception e) {
						e.printStackTrace();
						exceptionCounter++;
					}

					System.out.println("Second Finished");

				}
			}));

		}

		for (Future<?> future : list) {
			future.get();
		}

		executor.shutdown();

		System.out.println("EXCEPTION COUNT: " + exceptionCounter);
		System.out.println("End");
	}

	@Test
	public void testRetrievePaymentTypeList_defect10950() throws Exception {
		PaymentListContract contract = new PaymentListContract();
		contract.setMdmLevel("Legal");
		contract.setMdmId("10326");
		contract.setHardwareFlag(false);
		contract.setContractNumber("0005004560");
		contract.setIncrement(0);
		contract.setStartRecordNumber(0);
		contract.setNewQueryIndicator(false);
		contract.setAgreementId("1-78GMAQI");
		contract.setSoldToNumber("0000101382");
		contract.setSessionHandle(crmSessionHandle);

		PaymentTypeListResult result = amindGlobalService
				.retrievePaymentTypeList(contract);

		List<String> paymentTypes = result.getPaymentType();

		for (String string : paymentTypes) {
			System.out.println("Payment type: " + string);
		}

	}
	
	@Test
	public void testRetrievePaymentTypeList_defect16484() throws Exception {
		PaymentListContract contract = new PaymentListContract();
		contract.setMdmLevel("Global");
		contract.setMdmId("483419636");
		contract.setHardwareFlag(true);
		contract.setContractNumber("0005006872");
		contract.setIncrement(0);
		contract.setStartRecordNumber(0);
		contract.setNewQueryIndicator(false);
		contract.setAgreementId("1-2R1KM52");
		contract.setSoldToNumber("0000341778");
		contract.setSessionHandle(crmSessionHandle);
		
		PaymentTypeListResult result = amindGlobalService.retrievePaymentTypeList(contract);
		
		System.out.println(result.getPaymentType().size());
	}
	
	@Test
	public void testRetrievePaymentTypeList_defect17084() throws Exception {
		PaymentListContract contract = new PaymentListContract();
		contract.setMdmLevel("Legal");
		contract.setMdmId("69316");
		contract.setHardwareFlag(true);
		contract.setContractNumber("0040000062");
		contract.setIncrement(0);
		contract.setStartRecordNumber(0);
		contract.setNewQueryIndicator(false);
		contract.setAgreementId("1-4NW9QDF");
		contract.setSoldToNumber("0000215238");
		contract.setSessionHandle(crmSessionHandle);
		
		PaymentTypeListResult result = amindGlobalService.retrievePaymentTypeList(contract);
		System.out.println(result.getPaymentType().size());
	}
	
	@Test
	public void testRetrievePaymentTypeList_INC0102855() throws Exception {
		PaymentListContract contract = new PaymentListContract();
		contract.setMdmLevel("Global");
		contract.setMdmId("483419636");
		contract.setSoldToNumber("0000113712");
		contract.setHardwareFlag(true);
		contract.setContractNumber("0040000305");
		contract.setIncrement(0);
		contract.setStartRecordNumber(0);
		contract.setNewQueryIndicator(false);
		contract.setAgreementId("1-1ATENU9");
		contract.setSessionHandle(crmSessionHandle);
		
		PaymentTypeListResult result = amindGlobalService.retrievePaymentTypeList(contract);
		System.out.println(result.getPaymentType().size());
	}
	
	@Test
	public void testRetrievePaymentTypeList_INC0146472() throws Exception {
		PaymentListContract contract = new PaymentListContract();
		contract.setMdmLevel("Global");
		contract.setMdmId("161906193");
		contract.setSoldToNumber("0000219524");
		contract.setHardwareFlag(false);
		contract.setContractNumber("0040000150");
		contract.setIncrement(0);
		contract.setStartRecordNumber(0);
		contract.setNewQueryIndicator(false);
		contract.setAgreementId("1-7PZOL4X");
		contract.setSessionHandle(crmSessionHandle);
		
		PaymentTypeListResult result = amindGlobalService.retrievePaymentTypeList(contract);
		for (String paymentType : result.getPaymentType()) {
			System.out.println("paymentTyoe: " + paymentType);
		}
		System.out.println("Size: " + result.getPaymentType().size());
	}

	@Test
	public void testRetrieveCatalogAgreementList_defect11383() throws Exception {
		SiebelAccountListContract contract = new SiebelAccountListContract();
		contract.setMdmLevel("Legal");
		contract.setMdmId("14788");
		contract.setNewQueryIndicator(true);
		contract.setVendorFlag(false);
		contract.setAgreementFlag(true);
		contract.setHardwareFlag(true);
		contract.setSessionHandle(crmSessionHandle);

		SiebelAccountListResult result = amindGlobalService
				.retrieveCatalogAgreementList(contract);

		List<Account> list = result.getAccountList();

		System.out.println(list.size());

		for (Account account : list) {
			System.out.println("-----------");
			System.out.println("Agreement name: " + account.getAgreementName());
			System.out.println("Account name: " + account.getAccountName());
			System.out.println("Country: " + account.getAddress().getCountry());
			List<String> soldTos = account.getSoldToNumbers();
			System.out.println(account.getOrganizationName());
			List<String> salesOrgs = account.getSalesOrgs();
			System.out.println(salesOrgs == null);
			int i = 0;
			for (String soldTo : soldTos) {
				// System.out.println(soldTo + " " + salesOrgs.get(i));
				System.out.println(soldTo);
				i++;
			}
		}
	}

	@Test
	public void testRetrieveSoldToList_defect11352() throws Exception {
		AgreementSoldToNumberContract contract = new AgreementSoldToNumberContract();
		contract.setAgreementId("1-77WNASE");
		contract.setContractNumber("0005001855");
		contract.setIncrement(0);
		contract.setStartRecordNumber(0);
		contract.setNewQueryIndicator(false);
		contract.setSessionHandle(crmSessionHandle);

		AgreementSoldToNumberResult result = amindGlobalService
				.retrieveSoldToList(contract);
		System.out.println(result.getSoldToNumbers() == null ? "Null"
				: "Size: " + result.getSoldToNumbers().size());

		for (String soldToNumber : result.getSoldToNumbers()) {
			System.out.println(soldToNumber);
		}

	}

	@Test
	public void testRetrieveBillToAddressList_defect11385() throws Exception {
		AddressListContract contract = new AddressListContract();
		contract.setSessionHandle(AmindServiceTest.crmSessionHandle);
		contract.setFavoriteFlag(false);
		contract.setSoldToNumbers(Arrays.asList("0000131360", "0000131360",
				"0000132040", "0000132040"));
		contract.setCleansedAddress(false);
		contract.setNewQueryIndicator(false);
		contract.setStartRecordNumber(0);
		contract.setIncrement(0);

		AddressListResult result = amindGlobalService
				.retrieveBillToAddressList(contract);

		List<GenericAddress> addresses = result.getAddressList();

		System.out.println("Size: " + addresses.size());
		System.out.println("Total count: " + result.getTotalCount());
	}

	@Test
	public void testRetrieveCatalogAgreementList_defect11385() throws Exception {
		SiebelAccountListContract contract = new SiebelAccountListContract();
		contract.setMdmLevel("Legal");
		contract.setMdmId("14788");
		contract.setNewQueryIndicator(true);
		contract.setVendorFlag(false);
		contract.setAgreementFlag(true);
		contract.setHardwareFlag(true);
		contract.setSessionHandle(crmSessionHandle);
		// contract.setContractNumber("0005004739");
		contract.setContractNumber("0005004760");

		SiebelAccountListResult result = amindGlobalService
				.retrieveCatalogAgreementList(contract);

		List<Account> list = result.getAccountList();

		System.out.println(list.size());

		for (Account account : list) {
			System.out.println("Contract number: "
					+ account.getContractNumber());
			System.out.println("Account id: " + account.getAccountId());
			System.out.println("Agreement id: " + account.getAgreementId());
			List<String> soldTos = account.getSoldToNumbers();
			for (String soldTo : soldTos) {
				System.out.println("Sold to: " + soldTo);
			}
			System.out.println("----------");
		}
	}

	@Test
	public void testRetrieveCatalogAgreementList_defect11784() throws Exception {

		long t = System.currentTimeMillis();

		SiebelAccountListContract contract = new SiebelAccountListContract();
		contract.setMdmLevel("Global");
		contract.setMdmId("315000554");
		contract.setNewQueryIndicator(true);
		contract.setVendorFlag(false);
		contract.setAgreementFlag(true);
		contract.setHardwareFlag(false);
		contract.setSessionHandle(crmSessionHandle);

		SiebelAccountListResult result = amindGlobalService
				.retrieveCatalogAgreementList(contract);

		List<Account> list = result.getAccountList();

		System.out.println("Total size: " + list.size());
		System.out.println("----------");

		for (int i = 0; i < list.size(); i++) {
			System.out.println((i + 1) + ") Contract number: "
					+ list.get(i).getContractNumber());
			System.out.println("Account id: " + list.get(i).getAccountId());
			System.out.println("Agreement id: "
					+ list.get(i).getAgreementName());
			System.out.println("Country: "
					+ list.get(i).getAddress().getCountry());
			// List<String> soldTos = account.getSoldToNumbers();
			// for (String soldTo : soldTos) {
			// System.out.println("Sold to: " + soldTo);
			// }
			System.out.println("----------");
		}

		System.out.println("Exac time: " + (System.currentTimeMillis() - t)
				/ 1000.0);
	}

	@Test
	public void testRetrieveEntitelmentFlags_defect11844() throws Exception {
		SiebelAccountListContract contract = new SiebelAccountListContract();
		contract.setMdmLevel("Legal");
		contract.setMdmId("110899");
		contract.setNewQueryIndicator(true);
		contract.setVendorFlag(false);
		contract.setAgreementFlag(false);
		contract.setHardwareFlag(true);

		AccountFlagResult result = amindGlobalService
				.retrieveEntitelmentFlags(contract);

		System.out.println("CatalogEntitlementFlag: "
				+ result.isCatalogEntitlementFlag());
		System.out.println("AssetEntitlementFlag: "
				+ result.isAssetEntitlementFlag());
		System.out.println(result.getAccountList().size());

		// for (Account account : result.getAccountList()) {
		// System.out.println("'CatalogEntitlementFlag': " +
		// account.isCatalogEntitlementFlag());
		// System.out.println("'AssetEntitlementFlag': " +
		// account.isAssetEntitlementFlag());
		// }
	}

	@Test
	public void testRetrieveCatalogAgreementList_defect11828() throws Exception {

		long t = System.currentTimeMillis();

		SiebelAccountListContract contract = new SiebelAccountListContract();
		contract.setMdmLevel("Legal");
		contract.setMdmId("50801");
		contract.setNewQueryIndicator(true);
		contract.setVendorFlag(false);
		contract.setAgreementFlag(true);
		contract.setHardwareFlag(true);
		contract.setSessionHandle(crmSessionHandle);

		SiebelAccountListResult result = amindGlobalService
				.retrieveCatalogAgreementList(contract);

		List<Account> list = result.getAccountList();

		System.out.println(list.size());

		for (Account account : list) {
			System.out.println("Contract number: "
					+ account.getContractNumber());
			System.out.println("Account id: " + account.getAccountId());
			System.out.println("Agreement id: " + account.getAgreementId());
			System.out.println("Type: " + account.getAccountType());
			List<String> soldTos = account.getSoldToNumbers();
			if (soldTos != null) {
				for (int i = 0; i < soldTos.size(); i++) {
					System.out.println("Sold to: " + soldTos.get(i));
				}
			}
			System.out.println("----------");
		}

		System.out.println("Exac time: " + (System.currentTimeMillis() - t)
				/ 1000.0);
	}

	@Test
	public void testRetrieveCatalogAgreementList_defect11827() throws Exception {

		long t = System.currentTimeMillis();

		SiebelAccountListContract contract = new SiebelAccountListContract();
		contract.setMdmLevel("Legal");
		contract.setMdmId("50801");
		contract.setNewQueryIndicator(true);
		contract.setVendorFlag(false);
		contract.setAgreementFlag(true);
		contract.setHardwareFlag(true);
		contract.setSessionHandle(crmSessionHandle);

		SiebelAccountListResult result = amindGlobalService
				.retrieveCatalogAgreementList(contract);

		List<Account> list = result.getAccountList();

		System.out.println(list.size());

		for (Account account : list) {
			System.out.println("Contract number: "
					+ account.getContractNumber());
			System.out.println("Account id: " + account.getAccountId());
			System.out.println("Agreement id: " + account.getAgreementId());
			System.out.println("----------");
		}

		System.out.println("Exac time: " + (System.currentTimeMillis() - t)
				/ 1000.0);
	}

	@Test
	public void testRetrieveCatalogAgreementList_32() throws Exception {

		long t = System.currentTimeMillis();

		SiebelAccountListContract contract = new SiebelAccountListContract();
		contract.setMdmLevel("Legal");
		contract.setMdmId("50801");
		contract.setNewQueryIndicator(true);
		contract.setVendorFlag(false);
		contract.setAgreementFlag(true);
		contract.setHardwareFlag(false);
		contract.setSessionHandle(crmSessionHandle);

		SiebelAccountListResult result = amindGlobalService
				.retrieveCatalogAgreementList(contract);

		List<Account> list = result.getAccountList();

		System.out.println(list.size());

		System.out.println("Exac time: " + (System.currentTimeMillis() - t)
				/ 1000.0);
	}

	@Test
	public void testRetrieveCatalogAgreementList_39() throws Exception {

		long t = System.currentTimeMillis();

		SiebelAccountListContract contract = new SiebelAccountListContract();
		contract.setMdmLevel("Legal");
		contract.setMdmId("50801");
		contract.setNewQueryIndicator(true);
		contract.setVendorFlag(false);
		contract.setAgreementFlag(true);
		contract.setHardwareFlag(true);
		contract.setSessionHandle(crmSessionHandle);

		SiebelAccountListResult result = amindGlobalService
				.retrieveCatalogAgreementList(contract);

		List<Account> list = result.getAccountList();

		System.out.println(list.size());

		System.out.println("Exac time: " + (System.currentTimeMillis() - t)
				/ 1000.0);
	}

	@Test
	public void testRetrieveCatalogAgreementList_defect12007() throws Exception {

		SiebelAccountListContract contract = new SiebelAccountListContract();
		contract.setMdmLevel("Siebel");
		contract.setMdmId("1-RORPIA");
		contract.setNewQueryIndicator(true);
		contract.setVendorFlag(false);
		contract.setAgreementFlag(true);
		contract.setHardwareFlag(true);
		contract.setSessionHandle(crmSessionHandle);

		SiebelAccountListResult result = amindGlobalService
				.retrieveCatalogAgreementList(contract);

		List<Account> list = result.getAccountList();

		System.out.println("Total size: " + list.size());
		System.out.println("----------");

		for (Account account : list) {
			System.out.println("Country: " + account.getAddress().getCountry());
		}
	}

	@Test
	public void testRetrieveCatalogAgreementList_8925() throws Exception {
		SiebelAccountListContract contract = new SiebelAccountListContract();
		contract.setMdmLevel("Global");
		contract.setMdmId("006987135");
		contract.setNewQueryIndicator(true);
		contract.setVendorFlag(false);
		contract.setAgreementFlag(true);
		contract.setHardwareFlag(false);
		contract.setSessionHandle(crmSessionHandle);
		SiebelAccountListResult result = amindGlobalService
				.retrieveCatalogAgreementList(contract);
		System.out
				.println("ACOUNT NAME IS >>" + result.getAccountList().size());

		for (Account acc : result.getAccountList()) {
			System.out.println(">>Agreement name is  >>"
					+ acc.getAgreementName());
			System.out.println("Account Id IS >>" + acc.getAccountId());
			System.out.println(">>Account name is  >>" + acc.getAccountName());
			System.out.println(">> CATALOG FLAG >>"
					+ acc.isCatalogExpediteFlag());
		}
	}

	@Test
	public void testRetrieveCatalogAgreementList_8925_3() throws Exception {
		SiebelAccountListContract contract = new SiebelAccountListContract();
		contract.setMdmLevel("Domestic");
		contract.setMdmId("428201529");
		contract.setNewQueryIndicator(true);
		contract.setVendorFlag(false);
		contract.setAgreementFlag(false);
		contract.setHardwareFlag(false);
		contract.setSessionHandle(crmSessionHandle);
		SiebelAccountListResult result = amindGlobalService
				.retrieveCatalogAgreementList(contract);
		System.out
				.println("ACOUNT NAME IS >>" + result.getAccountList().size());

		for (Account acc : result.getAccountList()) {
			System.out.println(">>Agreement name is  >>"
					+ acc.getAgreementName());
			System.out.println("Account Id IS >>" + acc.getAccountId());
			System.out.println(">>Account name is  >>" + acc.getAccountName());
			System.out.println(">> CATALOG FLAG >>"
					+ acc.isCatalogExpediteFlag());
		}
	}
	
	@Test
	public void testRetrieveCatalogAgreementList_defect14959() throws Exception {
		SiebelAccountListContract contract = new SiebelAccountListContract();
		contract.setMdmLevel("Global");
		contract.setMdmId("489334806");
		contract.setNewQueryIndicator(true);
		contract.setVendorFlag(false);
		contract.setAgreementFlag(true);
		contract.setHardwareFlag(true);
		contract.setSessionHandle(crmSessionHandle);
		SiebelAccountListResult result = amindGlobalService.retrieveCatalogAgreementList(contract);
		System.out.println("ACOUNT NAME IS >>" + result.getAccountList().size());
	}
	
	@Test
	public void testRetrieveCatalogAgreementList_defect17084() throws Exception {
		SiebelAccountListContract contract = new SiebelAccountListContract();
		contract.setMdmLevel("Legal");
		contract.setMdmId("69316");
		contract.setNewQueryIndicator(true);
		contract.setVendorFlag(false);
		contract.setAgreementFlag(true);
		contract.setHardwareFlag(true);
		contract.setSessionHandle(crmSessionHandle);
		SiebelAccountListResult result = amindGlobalService.retrieveCatalogAgreementList(contract);
		System.out.println("ACOUNT NAME IS >>" + result.getAccountList().size());
	}
	
	@Test
	public void testRetrieveCatalogAgreementList_INC0114487() throws Exception {
		SiebelAccountListContract contract = new SiebelAccountListContract();
		contract.setMdmLevel("Global");
		contract.setMdmId("103391843");
		contract.setNewQueryIndicator(true);
		contract.setVendorFlag(false);
		contract.setAgreementFlag(true);
		contract.setHardwareFlag(true);
		contract.setSessionHandle(crmSessionHandle);
		SiebelAccountListResult result = amindGlobalService.retrieveCatalogAgreementList(contract);
		for (Account account : result.getAccountList()) {
			System.out.println("AgreementName: " + account.getAgreementName());
		}
		System.out.println();
	}
	
	@Test
	public void testRetrieveCatalogAgreementList_INC0137727() throws Exception {
		SiebelAccountListContract contract = new SiebelAccountListContract();
		contract.setMdmLevel("Global");
		contract.setMdmId("739088818");
		contract.setNewQueryIndicator(true);
		contract.setVendorFlag(false);
		contract.setAgreementFlag(true);
		contract.setHardwareFlag(true);
		contract.setSessionHandle(crmSessionHandle);
		SiebelAccountListResult result = amindGlobalService.retrieveCatalogAgreementList(contract);
		for (Account account : result.getAccountList()) {
			System.out.println("AgreementID: " + account.getAgreementId());
		}
		System.out.println();
	}
	
	@Test
	public void testRetrieveCatalogAgreementList_INC0144014() throws Exception {
		SiebelAccountListContract contract = new SiebelAccountListContract();
		contract.setMdmLevel("Global");
		contract.setMdmId("077583318");
		contract.setNewQueryIndicator(true);
		contract.setVendorFlag(false);
		contract.setAgreementFlag(false);
		contract.setHardwareFlag(false);
		contract.setSessionHandle(crmSessionHandle);
		SiebelAccountListResult result = amindGlobalService.retrieveCatalogAgreementList(contract);
		for (Account account : result.getAccountList()) {
			System.out.println("AgreementID: " + account.getAgreementId());
		}
		System.out.println();
	}
	
	@Test
	public void testRetrieveCatalogAgreementList_INC0147452() throws Exception {
		SiebelAccountListContract contract = new SiebelAccountListContract();
		contract.setMdmLevel("Global");
		contract.setMdmId("738801005");
		contract.setNewQueryIndicator(true);
		contract.setVendorFlag(false);
		contract.setAgreementFlag(true);
		contract.setHardwareFlag(true);
		contract.setSessionHandle(crmSessionHandle);
		SiebelAccountListResult result = amindGlobalService.retrieveCatalogAgreementList(contract);
		System.out.println(result.getAccountList().size());
	}
	
	@Test
	public void testRetrieveCatalogAgreementList_INC0159273() throws Exception {
		SiebelAccountListContract contract = new SiebelAccountListContract();
		contract.setMdmLevel("Global");
		contract.setMdmId("461064446");
//		contract.setAccountId("1-LBTR7B");
		contract.setNewQueryIndicator(true);
		contract.setAgreementFlag(true);
		contract.setHardwareFlag(false);
		contract.setSessionHandle(crmSessionHandle);
		SiebelAccountListResult result = amindGlobalService.retrieveCatalogAgreementList(contract);
		for (Account account : result.getAccountList()) {
			System.out.println("AccountId: " + account.getAccountId());
			System.out.println("AccountName: " + account.getAccountName());
			for (String soldto : account.getSoldToNumbers()) {
				System.out.println("Soldto: " + soldto);
			}
		}
		System.out.println("size: " + result.getAccountList().size());
	}	
	
	@Test
	public void testRetrieveBillToAddressList_defect12215() throws Exception {
		AddressListContract contract = new AddressListContract();
		contract.setSessionHandle(AmindServiceTest.crmSessionHandle);
		contract.setFavoriteFlag(false);
		contract.setSoldToNumbers(Arrays.asList("0000180030", "0000222971",
				"0000180030", "0000222971"));
		contract.setCleansedAddress(false);
		contract.setNewQueryIndicator(false);
		contract.setStartRecordNumber(0);
		contract.setIncrement(0);

		AddressListResult result = amindGlobalService
				.retrieveBillToAddressList(contract);

		List<GenericAddress> addresses = result.getAddressList();

		System.out.println("Size: " + addresses.size());
		System.out.println("Total count: " + result.getTotalCount());

		for (GenericAddress genericAddress : addresses) {
			System.out.println(genericAddress.getBillToNumber());
		}
	}

	@Test
	public void testRetrieveBillToAddressList_defect12205() throws Exception {
		AddressListContract contract = new AddressListContract();
		contract.setSessionHandle(AmindServiceTest.crmSessionHandle);
		contract.setFavoriteFlag(false);
		contract.setSoldToNumbers(Arrays.asList("0000180030", "0000222971",
				"0000180030", "0000222971"));
		contract.setCleansedAddress(false);
		contract.setNewQueryIndicator(false);
		contract.setStartRecordNumber(0);
		contract.setIncrement(0);

		AddressListResult result = amindGlobalService
				.retrieveBillToAddressList(contract);

		List<GenericAddress> addresses = result.getAddressList();

		System.out.println("Size: " + addresses.size());
		System.out.println("Total count: " + result.getTotalCount());

		for (GenericAddress genericAddress : addresses) {
			System.out.println("Country iso code: "
					+ genericAddress.getCountryISOCode());
		}
	}
	
	@Test
	public void testRetrieveBillToAddressList_defect14959() throws Exception {
		AddressListContract contract = new AddressListContract();
		contract.setSessionHandle(AmindServiceTest.crmSessionHandle);
		contract.setFavoriteFlag(false);
		contract.setSoldToNumbers(Arrays.asList("0000126159", "0000126159"));
		contract.setCleansedAddress(false);
		contract.setNewQueryIndicator(false);
		contract.setStartRecordNumber(0);  
		contract.setIncrement(0);
		
		AddressListResult result = amindGlobalService.retrieveBillToAddressList(contract);
		
		List<GenericAddress> addresses = result.getAddressList();
		System.out.println("Size: " + addresses.size());
		System.out.println("Total count: " + result.getTotalCount());
	}
	
	@Test
	public void testRetrieveBillToAddressList_defect17084() throws Exception {
		AddressListContract contract = new AddressListContract();
		contract.setSessionHandle(AmindServiceTest.crmSessionHandle);
		contract.setFavoriteFlag(false);
		contract.setSoldToNumbers(Arrays.asList("0000215238"));
		contract.setCleansedAddress(false);
		contract.setNewQueryIndicator(false);
		contract.setStartRecordNumber(0);  
		contract.setIncrement(0);
		
		AddressListResult result = amindGlobalService.retrieveBillToAddressList(contract);
		
		List<GenericAddress> addresses = result.getAddressList();
		System.out.println("Size: " + addresses.size());
		System.out.println("Total count: " + result.getTotalCount());
	}
	
	@Test
	public void testRetrieveBillToAddressList_SearchSpec() throws Exception {
		AddressListContract contract = new AddressListContract();
		contract.setSessionHandle(AmindServiceTest.crmSessionHandle);
		contract.setFavoriteFlag(false);
		contract.setSoldToNumbers(Arrays.asList("0000166146", "0000166146"));
		contract.setCleansedAddress(false);
		contract.setNewQueryIndicator(false);
		contract.setStartRecordNumber(0);  
		contract.setIncrement(0);
		AddressListResult result = amindGlobalService.retrieveBillToAddressList(contract);
		List<GenericAddress> addresses = result.getAddressList();
		System.out.println("Size: " + addresses.size());
		System.out.println("Total count: " + result.getTotalCount());
	}
	
	@Test
	public void testRetrieveBillToAddressList_INC0159273() throws Exception {
		AddressListContract contract = new AddressListContract();
		contract.setSessionHandle(AmindServiceTest.crmSessionHandle);
		contract.setFavoriteFlag(false);
		contract.setSoldToNumbers(Arrays.asList("0000270014", "0000270014"));
		contract.setCleansedAddress(false);
		contract.setNewQueryIndicator(false);
		contract.setStartRecordNumber(0);  
		contract.setIncrement(0);
		AddressListResult result = amindGlobalService.retrieveBillToAddressList(contract);
		List<GenericAddress> addresses = result.getAddressList();
		System.out.println("Size: " + addresses.size());
		System.out.println("Total count: " + result.getTotalCount());
	}

	@Test
	public void testRetrieveEntitelmentFlags_PerformanceIssue()
			throws Exception {

		long t = System.currentTimeMillis();

		SiebelAccountListContract contract = new SiebelAccountListContract();
		contract.setMdmLevel("Global");
		contract.setMdmId("315000554");
		contract.setNewQueryIndicator(true);
		contract.setVendorFlag(false);
		contract.setAgreementFlag(false);
		contract.setHardwareFlag(false);

		AccountFlagResult result = amindGlobalService
				.retrieveEntitelmentFlags(contract);

		System.out.println("Exec time: " + (System.currentTimeMillis() - t)
				/ 1000.0);
		System.out.println("END");
	}

	@Test
	public void testRetrieveEntitelmentFlags_PerformanceIssue2()
			throws Exception {

		long t = System.currentTimeMillis();

		SiebelAccountListContract contract = new SiebelAccountListContract();
		contract.setMdmLevel("Legal");
		contract.setMdmId("66868");
		contract.setNewQueryIndicator(true);
		contract.setVendorFlag(false);
		contract.setAgreementFlag(false);
		contract.setHardwareFlag(false);

		AccountFlagResult result = amindGlobalService
				.retrieveEntitelmentFlags(contract);

		System.out.println("Exec time: " + (System.currentTimeMillis() - t)
				/ 1000.0);
		System.out.println("END");
	}

	@Test
	public void testRetrievePartnerAccountList_defect12860_1() throws Exception {
		PartnerAccountListContract contract = new PartnerAccountListContract();
		contract.setMdmId("42825");
		contract.setMdmLevel("Legal");

		PartnerAccountListResult result = amindGlobalService.retrievePartnerAccountList(contract);

		System.out.println(result.getAccountList().size());
	}
	
	@Test
	public void testRetrievePartnerAccountList_defect12860_2() throws Exception {
		PartnerAccountListContract contract = new PartnerAccountListContract();
		contract.setMdmId("41647");
		contract.setMdmLevel("Legal");

		PartnerAccountListResult result = amindGlobalService.retrievePartnerAccountList(contract);

		System.out.println(result.getAccountList().size());
	}
	
	@Test
	public void testRetrievePartnerAccountList_defect13018() throws Exception {
		PartnerAccountListContract contract = new PartnerAccountListContract();
		contract.setMdmId("65500");
		contract.setMdmLevel("Legal");

		PartnerAccountListResult result = amindGlobalService
				.retrievePartnerAccountList(contract);

		System.out.println(result.getAccountList().size());
		for (Account account : result.getAccountList()) {
			System.out.println(account.isPartnerRequestTabHideFlag());
			System.out.println("Account Id: " + account.getAccountId());
			System.out.println("-------------");
		}
		
	}
	
	@Test
	public void testRetrievePartnerAccountList_defect14589() throws Exception {
		PartnerAccountListContract contract = new PartnerAccountListContract();
		contract.setMdmId("60067");
		contract.setMdmLevel("Legal");
		
		PartnerAccountListResult result = amindGlobalService.retrievePartnerAccountList(contract);
		System.out.println(result.getAccountList().size());
	}
	
	@Test
	public void testRetrievePartnerAccountList_properData() throws Exception {
		PartnerAccountListContract contract = new PartnerAccountListContract();
		contract.setMdmId("118552798");
		contract.setMdmLevel("Global");
		
		PartnerAccountListResult result = amindGlobalService.retrievePartnerAccountList(contract);
		System.out.println(result.getAccountList().size());
	}
	
	@Test
	public void testRetrievePartnerAccountList_LBS1_5() throws Exception {
		PartnerAccountListContract contract = new PartnerAccountListContract();
		contract.setMdmId("118552798");
		contract.setMdmLevel("Global");
		
		PartnerAccountListResult result = amindGlobalService.retrievePartnerAccountList(contract);
		System.out.println(result.getAccountList().size());
	}
	
	@Test
	public void testRetrievePartnerAccountList_DR_Env() throws Exception {
		PartnerAccountListContract contract = new PartnerAccountListContract();
		contract.setMdmId("18380");
		contract.setMdmLevel("Legal");
		
		PartnerAccountListResult result = amindGlobalService.retrievePartnerAccountList(contract);
		System.out.println(result.getAccountList().size());
	}
	

    @Test
    public void testretrieveEntitelmentFlags_QA() throws Exception {
    	SiebelAccountListContract contract = new SiebelAccountListContract();
        contract.setMdmLevel("Legal");
        contract.setMdmId("28647");
        contract.setNewQueryIndicator(true);
        contract.setVendorFlag(false);
        contract.setAgreementFlag(false);
        contract.setSessionHandle(crmSessionHandle);
        
        AccountFlagResult result = amindGlobalService.retrieveEntitelmentFlags(contract);
		
		System.out.println("Accounts List: " + result.getAccountList());
		System.out.println("EntitlementFlag: " + result.isAssetEntitlementFlag());
		System.out.println("CatalogEntitlementFlag: " + result.isCatalogEntitlementFlag());
		
		MiscTest.print(result.getAccountList());
    }
    
    @Test
    public void testretrieveEntitelmentFlags_QA_defect11844() throws Exception {
    	SiebelAccountListContract contract = new SiebelAccountListContract();
    	contract.setMdmLevel("Legal");
    	contract.setMdmId("110899");
    	contract.setNewQueryIndicator(true);
    	contract.setVendorFlag(false);
    	contract.setAgreementFlag(false);
    	contract.setSessionHandle(crmSessionHandle);
    	
    	AccountFlagResult result = amindGlobalService.retrieveEntitelmentFlags(contract);
    	for (Account account : result.getAccountList()) {
			System.out.println("CatalogEntitlementFlag : " + result.isCatalogEntitlementFlag());
			System.out.println("AssetEntitlementFlag : " + result.isAssetEntitlementFlag());
		}
    	MiscTest.print(result.getAccountList());
    }
    
    @Test
    public void testretrieveEntitelmentFlags_QA_performanceTest() throws Exception {
    	SiebelAccountListContract contract = new SiebelAccountListContract();
    	contract.setMdmLevel("Global");
    	contract.setMdmId("315000554");
//    	contract.setMdmId("009420159");
    	contract.setNewQueryIndicator(true);
    	contract.setVendorFlag(false);
    	contract.setAgreementFlag(false);
    	contract.setSessionHandle(crmSessionHandle);
    	
    	AccountFlagResult result = amindGlobalService.retrieveEntitelmentFlags(contract);
    	MiscTest.print(result.getAccountList());
    }
    
	@Test
	public void testRetrieveEntitelmentFlags_defect13189() throws Exception {
		SiebelAccountListContract contract = new SiebelAccountListContract();
		contract.setMdmLevel("Legal");
		contract.setMdmId("55838");
		contract.setNewQueryIndicator(true);
		contract.setVendorFlag(false);
		contract.setAgreementFlag(false);
		contract.setHardwareFlag(false);
		AccountFlagResult result = amindGlobalService.retrieveEntitelmentFlags(contract);

		System.out.println(result.getAccountList() == null);
		System.out.println("CatalogEntitlementFlag: " + result.isCatalogEntitlementFlag());
	}
	
	@Test
	public void testRetrieveEntitelmentFlags_defect14550() throws Exception {
		SiebelAccountListContract contract = new SiebelAccountListContract();
		contract.setMdmLevel("Global");
		contract.setMdmId("007915069");
		contract.setNewQueryIndicator(true);
		contract.setVendorFlag(false);
		contract.setAgreementFlag(true);
		contract.setHardwareFlag(false);
		AccountFlagResult result = amindGlobalService.retrieveEntitelmentFlags(contract);
		
		System.out.println(result.getAccountList() == null);
		System.out.println("CatalogEntitlementFlag: " + result.isCatalogEntitlementFlag());
	}
	
	@Test
	public void testRetrieveEntitelmentFlags_ArkoException() throws Exception {
		SiebelAccountListContract contract = new SiebelAccountListContract();
		contract.setMdmLevel("Global");
		contract.setMdmId("494170124");
		contract.setNewQueryIndicator(true);
		contract.setVendorFlag(false);
		contract.setAgreementFlag(false);
		contract.setHardwareFlag(false);
		AccountFlagResult result = amindGlobalService.retrieveEntitelmentFlags(contract);
		
		System.out.println(result.getAccountList() == null);
		System.out.println("CatalogEntitlementFlag: " + result.isCatalogEntitlementFlag());
	}
	
	@Test
	public void testRetrieveEntitelmentFlags_defect16850() throws Exception {
		SiebelAccountListContract contract = new SiebelAccountListContract();
		contract.setMdmLevel("Global");
		contract.setMdmId("519926455");
		contract.setNewQueryIndicator(true);
		contract.setVendorFlag(false);
		contract.setAgreementFlag(false);
		contract.setHardwareFlag(false);
		AccountFlagResult result = amindGlobalService.retrieveEntitelmentFlags(contract);
		
		System.out.println(result.getAccountList() == null);
		System.out.println("CatalogEntitlementFlag: " + result.isCatalogEntitlementFlag());
	}
	
	@Test
	public void testRetrieveEntitelmentFlags_defect17313() throws Exception {
		SiebelAccountListContract contract = new SiebelAccountListContract();
		contract.setMdmLevel("Siebel");
		contract.setMdmId("1-2WG5XQK");
		contract.setNewQueryIndicator(true);
		contract.setVendorFlag(true);
		contract.setAgreementFlag(false);
		contract.setHardwareFlag(false);
		AccountFlagResult result = amindGlobalService.retrieveEntitelmentFlags(contract);
		
		System.out.println(result.getAccountList() == null);
		System.out.println("CatalogEntitlementFlag: " + result.isCatalogEntitlementFlag());
	}
	
	@Test
	public void testRetrieveEntitelmentFlags_INC0106674() throws Exception {
		SiebelAccountListContract contract = new SiebelAccountListContract();
		contract.setMdmLevel("Global");
		contract.setMdmId("487201915");
		contract.setAccountId("1-LBTR7B");
		contract.setNewQueryIndicator(true);
		contract.setVendorFlag(false);
		contract.setAgreementFlag(false);
		contract.setHardwareFlag(false);
		AccountFlagResult result = amindGlobalService.retrieveEntitelmentFlags(contract);
		
		System.out.println(result.getAccountList() == null);
		System.out.println("CatalogEntitlementFlag: " + result.isCatalogEntitlementFlag());
		System.out.println("AssetEntitlementFlag: " + result.isAssetEntitlementFlag());
	}
	
	@Test
	public void testRetrieveEntitelmentFlags_defect18129() throws Exception {
		SiebelAccountListContract contract = new SiebelAccountListContract();
		contract.setMdmLevel("Global");
		contract.setMdmId("519926455");
//		contract.setAccountId("1-LBTR7B");
		contract.setNewQueryIndicator(true);
		contract.setVendorFlag(true);
		contract.setPartnerPortal(true);
		contract.setAgreementFlag(false);
		contract.setHardwareFlag(false);
		AccountFlagResult result = amindGlobalService.retrieveEntitelmentFlags(contract);
		
		System.out.println(result.getAccountList() == null);
		System.out.println("CatalogEntitlementFlag: " + result.isCatalogEntitlementFlag());
		System.out.println("AssetEntitlementFlag: " + result.isAssetEntitlementFlag());
	}
    
}
