package com.lexmark.service.impl.real;

import java.text.ParseException;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import util.TestSessionFactories;

import com.amind.session.SessionFactory;
import com.amind.session.StatelessSessionFactory;
import com.lexmark.contract.FRUPartListContract;
import com.lexmark.domain.Part;
import com.lexmark.result.FRUPartListResult;

public class PartnerFRUPartListTest {
	
	protected static final Log logger = 
		LogFactory.getLog(PartnerFRUPartListTest.class);
	FRUPartListContract contract;
	AmindPartnerRequestsService service;
	SessionFactory sessionFactory = TestSessionFactories.newStatelessSessionFactory();
	
	 @Before
     public void setUp() {
		service = new AmindPartnerRequestsService();
		service.setStatelessSessionFactory(sessionFactory);
		contract = new FRUPartListContract();
     }
	 
	 @After
	 public void tearDown() {
		 ((StatelessSessionFactory)sessionFactory).releaseAllStatelessSessions();
	 }
	
	@Test
	public void testRetrieveFruPartList() throws Exception {
		
		testRetrieveFRUPartInputParameters();
		FRUPartListResult result = service.retrieveFRUPartList(contract);
		
		System.out.println("Size: " + result.getPartList().size());
		System.out.println("Total count: " + result.getTotalCount());
		
//		testRetrieveFRUPartOutputParameters(result);
		
	}
	
	
	public void testRetrieveFRUPartInputParameters() throws InterruptedException, ParseException {
		String modelNumber = "4062-43A";
		contract.setModelNumber(modelNumber);
//		contract.setHardwarePartsRequest(true);
	}
	
	
	public void testRetrieveFRUPartOutputParameters(FRUPartListResult result) {
		Assert.assertNotNull(result);
		Assert.assertNotNull(result.getPartList());
		
	}
	
	
	@Test
	public void testRetrieveFruPartList_12200() throws Exception {
		
		contract.setModelNumber("5025-430");
		contract.setHardwarePartsRequest(true);
		FRUPartListResult result = service.retrieveFRUPartList(contract);
		
		System.out.println("Size: " + result.getPartList().size());
		System.out.println("Total count: " + result.getTotalCount());
		
		List<Part> parts = result.getPartList();
		
		for (Part part : parts) {
			System.out.println(part.getPartNumber());
		}
		
//		testRetrieveFRUPartOutputParameters(result);
		
	}
	
	
	@Test
	public void testRetrieveFruPartList_BRD14_06_06MaintenanceKitValidation() throws Exception {
		
		contract.setModelNumber("5025-430");
		contract.setHardwarePartsRequest(false);
		
		FRUPartListResult result = service.retrieveFRUPartList(contract);
		
		System.out.println("Size: " + result.getPartList().size());
		System.out.println("Total count: " + result.getTotalCount());
		
	}
	
}


