package com.lexmark.service.impl.real.partnerPaymentService;

import java.io.FileNotFoundException;
import java.text.ParseException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Assert;
import org.junit.Test;

import util.TestSessionFactories;

import com.amind.session.SessionFactory;
import com.amind.session.StatelessSessionFactory;
import com.lexmark.contract.BulkUploadStatusContract;
import com.lexmark.result.BulkUploadStatusListResult;
import com.lexmark.service.impl.real.AmindPartnerBulkUploadService;

public class PartnerBulkRetrieveStatusListTest {
	
	protected static final Log logger = 
		LogFactory.getLog(PartnerBulkRetrieveStatusListTest.class);
	AmindPartnerBulkUploadService service;
	BulkUploadStatusContract contract;
	SessionFactory sessionFactory = TestSessionFactories.newStatelessSessionFactory();
	
	
     public void setUp() {
		service = new AmindPartnerBulkUploadService();
		contract = new BulkUploadStatusContract();
		service.setStatelessSessionFactory(sessionFactory);
		
     }
	 
	 public void tearDown() {
		 ((StatelessSessionFactory)sessionFactory).releaseAllStatelessSessions();
	 }
	
	@Test
	public void testBulkUploadImportFile()  throws Exception {
		
		testBulkUploadInputParameters();
		BulkUploadStatusListResult fileResult = service.retrieveBulkUploadStatusList(contract);
		testBulkUploadOutputParameters(fileResult);

	}
	
	public void testBulkUploadInputParameters() throws InterruptedException, ParseException, FileNotFoundException {
		
		setUp();
		String mdmId = "136592842~1106_uploader_Debrief";
		contract.setMdmId(mdmId);
		
		
	}
		
	public void testBulkUploadOutputParameters(BulkUploadStatusListResult result) {
	
		tearDown();
		Assert.assertNotNull(result.getBulkUploadStatusList());
	}
	
}


