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
import com.lexmark.result.BulkUploadStatusFileResult;
import com.lexmark.service.impl.real.AmindPartnerBulkUploadService;

public class PartnerBulkRetrieveFileTest {
	
	protected static final Log logger = 
		LogFactory.getLog(PartnerBulkRetrieveFileTest.class);
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
	public void testBulkRetrieveFile()  throws Exception {
		
		testBulkRetrieveInputParameters();
		BulkUploadStatusFileResult fileResult = service.retrieveBulkUploadStatusFile(contract);
		testBulkRetrieveOutputParameters(fileResult);

	}
	
	public void testBulkRetrieveInputParameters() throws InterruptedException, ParseException, FileNotFoundException {
		
		setUp();
		String userFileName = "136592842~Claims(7)~20111027043555035";
		contract.setUserFileName(userFileName);

	}
		
	public void testBulkRetrieveOutputParameters(BulkUploadStatusFileResult result) {
	
		tearDown();
		Assert.assertNotNull(result.getFileStream());
	}
	
}


