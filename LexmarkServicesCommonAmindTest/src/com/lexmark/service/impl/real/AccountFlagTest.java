package com.lexmark.service.impl.real;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.lexmark.contract.SiebelAccountListContract;
import com.lexmark.domain.Account;
import com.lexmark.result.AccountFlagResult;
import com.lexmark.service.impl.real.util.AmindServiceUtil;

@RunWith(Parameterized.class)
public class AccountFlagTest extends GlobalServiceStatefulBase{

	private final SiebelAccountListContract contract;
	
	@Parameters
	public static List<Object[]> parameters() {
		List<Object[]> list = new ArrayList<Object[]>();

		list.add(new Object[] { "315000554", AmindServiceUtil.GLOBAL_MDMLEVEL });

		return list;
	}

	public AccountFlagTest(String mdmId, String mdmLevel) {
		contract = new SiebelAccountListContract();
//		contract.setMdmId(mdmId);
//		contract.setMdmLevel(mdmLevel);
//		contract.setSessionHandle(handle);
	}
	
	@Test
	public void testRetrieveEntitelmentFlags() throws Exception {
//		AccountFlagResult result = globalService.retrieveEntitelmentFlags(contract);
//		assertNotNull("result is null!", result);
//		List<Account> accounts = result.getAccountList();
//		assertNotNull("list is null!", accounts);
		
		contract.setMdmLevel("Legal");
		contract.setMdmId("28647");
		contract.setNewQueryIndicator(true);
		contract.setVendorFlag(false);
		contract.setAgreementFlag(false);
		
		AccountFlagResult result = globalService.retrieveEntitelmentFlags(contract);
		
		for (Account account : result.getAccountList()) {
			System.out.println("'CatalogEntitlementFlag': " + account.isCatalogEntitlementFlag());
		}
	}

	@Test
	public void testRetrieveEntitelmentFlags_defect11844() throws Exception {
		SiebelAccountListContract contract = new SiebelAccountListContract();
		contract.setMdmLevel("Legal");
		contract.setMdmId("110899");
		contract.setNewQueryIndicator(true);
		contract.setVendorFlag(false);
		contract.setAgreementFlag(false);
		contract.setHardwareFlag(false);
		
		AccountFlagResult result = globalService.retrieveEntitelmentFlags(contract);
		
		System.out.println("CatalogEntitlementFlag: " + result.isCatalogEntitlementFlag());
		System.out.println("AssetEntitlementFlag: " + result.isAssetEntitlementFlag());
		
//		for (Account account : result.getAccountList()) {
//			System.out.println("'CatalogEntitlementFlag': " + account.isCatalogEntitlementFlag());
//		}
	}
}
