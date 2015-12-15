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
import com.lexmark.contract.ValidateInstalledPrinterSerialNumberContract;
import com.lexmark.result.ValidateInstalledPrinterSerialNumberResult;
import com.lexmark.service.impl.real.AmindPartnerRequestsService;

public class PartnerValidateInstalledSerialNumberTest {
	
	protected static final Log logger = 
		LogFactory.getLog(PartnerValidateInstalledSerialNumberTest.class);
	ValidateInstalledPrinterSerialNumberContract contract;
	AmindPartnerRequestsService service;
	SessionFactory sessionFactory = TestSessionFactories.newStatelessSessionFactory();
	
	 @Before
     public void setUp() {
		service = new AmindPartnerRequestsService();
		service.setStatelessSessionFactory(sessionFactory);
		contract = new ValidateInstalledPrinterSerialNumberContract();
     }
	 
	 @After
	 public void tearDown() {
		 ((StatelessSessionFactory)sessionFactory).releaseAllStatelessSessions();
	 }
	
	@Test
	public void testUserFavoriteAddress() throws Exception {
		
		testValidateSerialNumberInputParameters();
		ValidateInstalledPrinterSerialNumberResult result = service.validateInstalledPrinterSerialNumber(contract);
		testValidateSerialNumberOutputParameters(result);
		
	}
	
	
	public void testValidateSerialNumberInputParameters() throws InterruptedException, ParseException {
		String serialNumber = "SB12345";
		String modelNumber = "5021-000";
		
		contract.setSerialNumber(serialNumber);
		contract.setModelNumber(modelNumber);
	}
	
	
	public void testValidateSerialNumberOutputParameters(ValidateInstalledPrinterSerialNumberResult result) {
		Assert.assertNotNull(result);
		Assert.assertNotNull(result.getSuccess());
		
	}
	
}


