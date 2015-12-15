package com.lexmark.util;


import static org.easymock.EasyMock.expect;

import java.util.ArrayList;
import java.util.List;

import org.easymock.EasyMockSupport;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import com.lexmark.domain.GlobalAccount;
import com.lexmark.service.api.GlobalLegalEntityService;

public class AccountUtilTest extends EasyMockSupport{
	private GlobalLegalEntityService globalLegalEntityService;
	private String legalNameOne;
	private String legalNameTwo;
	private String legalNameThree;
	@Before
	public void setUp() throws Exception {
		globalLegalEntityService = createMock(GlobalLegalEntityService.class);
		legalNameOne = "legalOne";
		legalNameTwo = "legalTwo";
		legalNameThree = "legalThree";
	}

	@Test
	public void testRetrieveAccountByLegalName() {
		List<GlobalAccount> entityList = generateGlobalAccounts();
		resetAll();
		expect(globalLegalEntityService.getGlobalAccounts()).andReturn(entityList);
		replayAll();
		String returnLegalName = AccountUtil.retrieveAccountByLegalName(globalLegalEntityService, legalNameTwo).getLegalName();
		assertTrue(legalNameTwo.equals(returnLegalName));
	}
	
	@Test
	public void testRetrieveAccountByLegalNameResultNull() {
		List<GlobalAccount> entityList = generateGlobalAccounts();
		resetAll();
		expect(globalLegalEntityService.getGlobalAccounts()).andReturn(entityList);
		replayAll();
		assertNull(AccountUtil.retrieveAccountByLegalName(globalLegalEntityService, legalNameThree));
	}
	
	private List<GlobalAccount> generateGlobalAccounts(){
		List<GlobalAccount> entityList = new ArrayList<GlobalAccount>();
		GlobalAccount globalAccountOne = new GlobalAccount();
		globalAccountOne.setLegalName(legalNameOne);
		GlobalAccount globalAccountTwo = new GlobalAccount();
		globalAccountTwo.setLegalName(legalNameTwo);
		entityList.add(globalAccountOne);
		entityList.add(globalAccountTwo);
		return entityList;
	}
}
