package com.lexmark.service.impl.mock;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.lexmark.constants.LexmarkConstants;
import com.lexmark.contract.DunsNumberContract;
import com.lexmark.contract.SiebelLOVListContract;
import com.lexmark.result.DunsNumberResult;
import com.lexmark.result.SiebelLOVListResult;
import com.lexmark.service.api.GlobalService;

public class GlobalServiceImplTest {
	private GlobalService  service;
	
	@Before
	public void setup() {
		service = new GlobalServiceImpl();
	}
	
	@After
	public void destroy() {
		service = null;
	}
	
	@Test
	public void testRetrieveSiebelLOVList() throws Exception {
		
		SiebelLOVListContract contract = new SiebelLOVListContract();
		contract.setLovName("LXK_SERVICE_ERR_CODE_2");
		contract.setErrorCode1("PaperFeed1");
		
		SiebelLOVListResult result = service.retrieveSiebelLOVList(contract);
		Assert.assertTrue(result!= null && result.getLovList().size() == 3);
	}
	
	@Test(expected=IllegalArgumentException.class) 
	public void testRetrieveDunsNumber() {
		DunsNumberContract contract = new DunsNumberContract();
		contract.setMdmId("123");
		contract.setMdmLevel(LexmarkConstants.MDM_LEVEL_LEGAL);
		DunsNumberResult result = service.retrieveDunsNumber(contract);
	}
}
