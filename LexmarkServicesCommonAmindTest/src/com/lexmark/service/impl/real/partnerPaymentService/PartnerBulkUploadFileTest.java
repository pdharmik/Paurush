package com.lexmark.service.impl.real.partnerPaymentService;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.ParseException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Assert;
import org.junit.Test;

import util.TestSessionFactories;

import com.amind.session.SessionFactory;
import com.amind.session.StatelessSessionFactory;
import com.lexmark.contract.BulkUploadContract;
import com.lexmark.result.BulkUploadFileResult;
import com.lexmark.service.impl.real.AmindPartnerBulkUploadService;

public class PartnerBulkUploadFileTest {
	
	protected static final Log logger = 
		LogFactory.getLog(PartnerBulkUploadFileTest.class);
	AmindPartnerBulkUploadService service;
	BulkUploadContract contract;
	SessionFactory sessionFactory = TestSessionFactories.newStatelessSessionFactory();
	
	
     public void setUp() {
		service = new AmindPartnerBulkUploadService();
		contract = new BulkUploadContract();
		service.setStatelessSessionFactory(sessionFactory);
		
     }
	 
	 public void tearDown() {
		 ((StatelessSessionFactory)sessionFactory).releaseAllStatelessSessions();
	 }
	
	@Test
	public void testBulkUploadImportFile()  throws Exception {
		
		testBulkUploadInputParameters();
		BulkUploadFileResult fileResult = service.bulkUploadFile(contract);
		testBulkUploadOutputParameters(fileResult);

	}
	
	public void testBulkUploadInputParameters() throws InterruptedException, ParseException, FileNotFoundException {
		
		setUp();
		File file1 = new File("C:\\Documents and Settings\\Mansi\\My Documents\\Downloads\\136592842~1106_uploader_Debrief_txt_to_csv~20111107102930029.csv");
		contract.setFileStream(new FileInputStream(file1));
		String mdmId = "00000";
		String contactId = "1-abceef";
		String userFileName = "Data_Mapping.csv";
		contract.setContactId(contactId);
		contract.setUserFileName(userFileName);
		contract.setMdmId(mdmId);
		contract.setUploadFileType("Request Upload");
		
	}
		
	public void testBulkUploadOutputParameters(BulkUploadFileResult result) {
	
		tearDown();
		Assert.assertTrue(result.isUpDateSuccess());
	}
	
}


