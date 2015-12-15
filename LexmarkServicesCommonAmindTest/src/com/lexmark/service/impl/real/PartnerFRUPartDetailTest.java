package com.lexmark.service.impl.real;

import java.text.ParseException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import util.TestSessionFactories;

import com.amind.session.SessionFactory;
import com.amind.session.StatelessSessionFactory;
import com.lexmark.contract.FRUPartDetailContract;
import com.lexmark.result.FRUPartDetailResult;
import com.lexmark.service.impl.real.AmindPartnerRequestsService;

public class PartnerFRUPartDetailTest {
	
	protected static final Log logger = 
		LogFactory.getLog(PartnerFRUPartDetailTest.class);
	FRUPartDetailContract contract;
	AmindPartnerRequestsService service;
	SessionFactory sessionFactory =  TestSessionFactories.newStatelessSessionFactory();
	
	 @Before
     public void setUp() {
		service = new AmindPartnerRequestsService();
		service.setStatelessSessionFactory(sessionFactory);
		contract = new FRUPartDetailContract();
     }
	 
	 @After
	 public void tearDown() {
		 ((StatelessSessionFactory)sessionFactory).releaseAllStatelessSessions();
	 }
	
	@Test
	public void testRetrieveFruPartDetail() throws Exception {
		
		testRetrieveFRUPartInputParameters();
		FRUPartDetailResult result = service.retrieveFRUPart(contract);
		testRetrieveFRUPartOutputParameters(result);
		
	}
	
	@Test
	public void testRetrieveFruPartDetail_defect12200() throws Exception {
		
		contract.setPartNumber("30G0806");
		contract.setPartnerOrg("1-EK5NO");
		contract.setModelNumber("4062-01A");
		contract.setHardwarePartRequest(true);
		
		FRUPartDetailResult result = service.retrieveFRUPart(contract);
		
		System.out.println(result.getPart().getPartNumber());
		
		System.out.println("End");
		
	}
	
	
	public void testRetrieveFRUPartInputParameters() throws InterruptedException, ParseException {
		String partNumber ="56P4385";// "56P3857";
		String partnerOrg = "1-4ED";
		String modelNumber = "4048-111";//"7014-431";
		Boolean replacementFlag = true;
		/*34S7812*/
		
		contract.setPartNumber(partNumber);
		contract.setPartnerOrg(partnerOrg);
		contract.setModelNumber(modelNumber);
		contract.setReplacementFlag(replacementFlag);
	}
	
	
	public void testRetrieveFRUPartOutputParameters(FRUPartDetailResult result) {
		Assert.assertNotNull(result);
		Assert.assertNotNull(result.getPart());
		
	}
	
	
	
	@Test
	public void testRetrieveFruPartDetail_defect12406() throws Exception {
		
		contract.setPartNumber("23A0393");
		contract.setPartnerOrg("1-42GYT");
		contract.setModelNumber("4115-001");
		contract.setHardwarePartRequest(true);
		
		FRUPartDetailResult result = service.retrieveFRUPart(contract);
		
		System.out.println(result.getPart().getPartNumber());
		
		System.out.println("End");
		
	}
	
	
	@Test
	public void testRetrieveFruPartDetail_defect12200_2() throws Exception {
		
		contract.setPartNumber("1021208");
		contract.setPartnerOrg("1-EK5NO");
		contract.setModelNumber("5025-430");
		contract.setReplacementFlag(false);
		contract.setHardwarePartRequest(true);
		
		FRUPartDetailResult result = service.retrieveFRUPart(contract);
		
//		System.out.println(result.getPart().getPartNumber());
		System.out.println(result.getPart() == null);
		if(result.getPart() != null) {
			System.out.println(result.getPart().getPartNumber());
		}
		
		System.out.println("End");
		
	}
}


