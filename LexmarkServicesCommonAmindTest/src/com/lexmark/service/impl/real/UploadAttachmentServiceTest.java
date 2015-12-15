package com.lexmark.service.impl.real;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.junit.Test;

import com.amind.session.StatefulSessionFactory;
import com.lexmark.contract.AttachmentContract;
import com.lexmark.domain.Attachment;
import com.lexmark.domain.ServiceRequest;

public class UploadAttachmentServiceTest extends AttachmentServiceStatelessBase {
	
	@Test
	public void testUploadAttachment_defect8376() throws Exception {
//		List<Attachment> attachments = new ArrayList<Attachment>();
//		Attachment attachment = new Attachment();
//		attachment.setAttachmentName("TextFile1001.xlsx");
//		attachment.setSize(8746);
//		attachment.setSizeForDisplay("8.54");
//		attachment.setVisibility("Customer");
//		attachment.setDisplayAttachmentName("TextFile1001.xlsx");
//		
//		attachments.add(attachment);
//		
//		final AttachmentContract contract = new AttachmentContract();
//		contract.setIdentifier("1-6CSJH0T");
//		contract.setIncrement(0);
//		contract.setStartRecordNumber(0);
//		contract.setNewQueryIndicator(false);
//		contract.setRequestType("Service Request");
//		
//		contract.setAttachments(attachments);
//------------------------------------------------------------------------------		
//		List<Attachment> attachments2 = new ArrayList<Attachment>();
//		Attachment attachment2 = new Attachment();
//		attachment2.setAttachmentName("TestFile101.xlsx");
//		attachment2.setSize(8746);
//		attachment2.setSizeForDisplay("8.54");
//		attachment2.setVisibility("Customer");
//		attachment2.setDisplayAttachmentName("TestFile101.xlsx");
//		
//		attachments2.add(attachment2);
//		
//		final AttachmentContract contract2 = new AttachmentContract();
//		contract2.setIdentifier("1-U3M84E");
//		contract2.setIncrement(0);
//		contract2.setStartRecordNumber(0);
//		contract2.setNewQueryIndicator(false);
//		contract2.setRequestType("Service Request");
//		
//		contract2.setAttachments(attachments2);
		
// ------------------------------------------------------------------------------
//		List<Attachment> attachments3 = new ArrayList<Attachment>();
//		Attachment attachment3 = new Attachment();
//		attachment3.setAttachmentName("TestFile102.xlsx");
//		attachment3.setSize(8746);
//		attachment3.setSizeForDisplay("8.54");
//		attachment3.setVisibility("Customer");
//		attachment3.setDisplayAttachmentName("TestFile102.xlsx");
//
//		attachments3.add(attachment3);
//
//		final AttachmentContract contract3 = new AttachmentContract();
//		contract3.setIdentifier("mock-identifier");
//		contract3.setIncrement(0);
//		contract3.setStartRecordNumber(0);
//		contract3.setNewQueryIndicator(false);
//		contract3.setRequestType("Service Request");
//
//		contract3.setAttachments(attachments3);
		

//		ExecutorService executor = Executors.newFixedThreadPool(2);
//
//		Future<List<Integer>> first = executor.submit(new Callable<List<Integer>>() {
//			@Override
//			public List<Integer> call() throws Exception {
//				 service.uploadAttachments(contract);
//				 System.out.println("1st finished");
//				 return new ArrayList<Integer>();
//			}
//		});
		
//		Future<Integer> second = executor.submit(new Callable<Integer>() {
//			@Override
//			public Integer call() throws Exception {
//				service.uploadAttachments(contract2);
//				System.out.println("2nd finished");
//				return 0;
//			}
//		});
//		
//		Future<Integer> third = executor.submit(new Callable<Integer>() {
//			@Override
//			public Integer call() throws Exception {
//				service.uploadAttachments(contract3);
//				System.out.println("3nd finished");
//				return 0;
//			}
//		});
		
//		List<Integer> a = first.get();
		
//		Integer b = second.get();
//		
//		Integer c = third.get();
		
//		executor.shutdown();
		
//		System.out.println("1: " + a.size());
//		System.out.println("2: " + b);
//		System.out.println("3: " + c);
		
		
		
		ExecutorService executor = Executors.newFixedThreadPool(4);

		List<Future<?>> futures = new ArrayList<Future<?>>();

		for(int i = 50; i<= 55; i++) {
			
			final String fileName = "Test" + i + ".xlsx";
			final int j = i;
			
			futures.add(executor.submit(new Runnable() {
				
				@Override
				public void run() {
					
					System.out.println(j + " STARTED");
					
					List<Attachment> attachments = new ArrayList<Attachment>();
					Attachment attachment = new Attachment();
					attachment.setAttachmentName(fileName);
					attachment.setSize(8746);
					attachment.setSizeForDisplay("8.54");
					attachment.setVisibility("Customer");
					attachment.setDisplayAttachmentName(fileName);
					
					attachments.add(attachment);
					
					AttachmentContract contract = new AttachmentContract();
					contract.setIdentifier("1-6CSJH0T");
					contract.setIncrement(0);
					contract.setStartRecordNumber(0);
					contract.setNewQueryIndicator(false);
					contract.setRequestType("Service Request");
					
					contract.setAttachments(attachments);
					
					
					try {
						service.uploadAttachments(contract);
					} catch (Exception e) {
						e.printStackTrace();
					}
					
					System.out.println(j + " FINISHED");
					
				}
			}));
		}
		
		
		for (Future<?> future : futures) {
			future.get();
		}
		
		
		executor.shutdown();
		
		
		System.out.println("END");
		
		
	}
	
	
	@Test
	public void testUploadAttachment_defect9813() throws Exception {
		
		List<Attachment> attachments = new ArrayList<Attachment>();
		Attachment attachment = new Attachment();
		attachment.setAttachmentName("Test36.xlsx");
		attachment.setSize(8746);
		attachment.setSizeForDisplay("8.54");
		attachment.setVisibility("Customer");
		attachment.setDisplayAttachmentName("Test35.xlsx");

		attachments.add(attachment);

		final AttachmentContract contract = new AttachmentContract();
		contract.setIdentifier("1-7E1VJXV");
		contract.setIncrement(0);
		contract.setStartRecordNumber(0);
		contract.setNewQueryIndicator(false);
		contract.setRequestType("Service Request");
		
//		contract.setSessionHandle(crmSessionHandle);

		contract.setAttachments(attachments);

		service.uploadAttachments(contract);
		
		System.out.println("END");
		
		
		
//		List<Attachment> attachments = new ArrayList<Attachment>();
//		Attachment attachment = new Attachment();
//		attachment.setAttachmentName("Questions on Core Java_1383635751849.docx");
//		attachment.setSize(11642);
//		attachment.setVisibility("Customer");
//		attachment.setDisplayAttachmentName("Questions on Core Java_1383811395880.docx");
//
//		attachments.add(attachment);
//
//		final AttachmentContract contract = new AttachmentContract();
//		contract.setIdentifier("1-7GLPS45");
//		contract.setIncrement(0);
//		contract.setStartRecordNumber(0);
//		contract.setNewQueryIndicator(false);
//		contract.setRequestType("Service Request");
		
//		contract.setSessionHandle(crmSessionHandle);

//		contract.setAttachments(attachments);
		
//		service.uploadAttachments(contract);

//		System.out.println("END");
		
	}

	@Test
	public void testUploadAttachment_defect11155() throws Exception {
		
		List<Attachment> attachments = new ArrayList<Attachment>();
		Attachment attachment = new Attachment();
//		attachment.setAttachmentName("TextFile1001Ilia1(#).xlsx");
		attachment.setAttachmentName("MassUploadTemplateHardwareOrder#_Test.xls");
		attachment.setSize(1155072);
		attachment.setVisibility("Partner");
//		attachment.setDisplayAttachmentName("TextFile1001Ilia.xlsx");
		attachment.setDisplayAttachmentName("MassUploadTemplateHardwareOrder#_Test.xls");

		attachments.add(attachment);

		final AttachmentContract contract = new AttachmentContract();
		contract.setIdentifier("1-7XEPWRD");
		contract.setIncrement(0);
		contract.setStartRecordNumber(0);
		contract.setNewQueryIndicator(false);
		contract.setRequestType("Service Request");
		
		contract.setSessionHandle(crmSessionHandle);

		contract.setAttachments(attachments);

		service.uploadAttachments(contract);
		
		System.out.println("END");
		
	}
	
	
	@Test
	public void testUploadAttachment_MassUploadAttachmentCallFailing() throws Exception {
		
		List<Attachment> attachments = new ArrayList<Attachment>();
		Attachment attachment = new Attachment();
		attachment.setAttachmentName("MassUploadTemplateHardwareOrder#_1390211988205.xls");
		attachment.setSize(336384);
		attachment.setVisibility("Partner");
		attachment.setDisplayAttachmentName("MassUploadTemplateHardwareOrder#_1390211988205.xls");

		attachments.add(attachment);

		final AttachmentContract contract = new AttachmentContract();
		contract.setIdentifier("1-7Y921XP");
		contract.setIncrement(0);
		contract.setStartRecordNumber(0);
		contract.setNewQueryIndicator(false);
		contract.setRequestType("Service Request");
		contract.setSessionHandle(crmSessionHandle);
		contract.setAttachments(attachments);

		service.uploadAttachments(contract);
		
		System.out.println("END");
		
	}
	
	
	
	@Test
	public void testUploadAttachment_defect11453() throws Exception {
		
		List<Attachment> attachments = new ArrayList<Attachment>();
		Attachment attachment = new Attachment();
		attachment.setAttachmentName("Defect Status_1391463174415_Test_1.xls");
		attachment.setSize(28672);
		attachment.setSizeForDisplay("28.00");
		attachment.setVisibility("Customer");
		attachment.setDisplayAttachmentName("Defect Status_1391463174415_Test_1.xls");
		attachments.add(attachment);
		
		attachment = new Attachment();
		attachment.setAttachmentName("HardwareInstallTemplate_1391463193320_Test_1.xls");
		attachment.setSize(210432);
		attachment.setSizeForDisplay("205.50");
		attachment.setVisibility("Customer");
		attachment.setDisplayAttachmentName("HardwareInstallTemplate_1391463193320_Test_1.xls");
		attachments.add(attachment);

		final AttachmentContract contract = new AttachmentContract();
		contract.setIdentifier("1-847WKD7");
		contract.setIncrement(0);
		contract.setStartRecordNumber(0);
		contract.setNewQueryIndicator(false);
		contract.setRequestType("Service Request");
		
		contract.setSessionHandle(crmSessionHandle);

		contract.setAttachments(attachments);

		service.uploadAttachments(contract);
		
		System.out.println("END");
		
	}
	
	@Test
	public void testUploadAttachment_defect11453_2() throws Exception {
		
		List<Attachment> attachments = new ArrayList<Attachment>();
		Attachment attachment = new Attachment();
		attachment.setAttachmentName("HardwareInstallTemplate (1).xls");
		attachment.setSize(210432);
		attachment.setSizeForDisplay("205.50");
		attachment.setVisibility("Customer");
		attachment.setDisplayAttachmentName("HardwareInstallTemplate (1).xls");
		attachments.add(attachment);
		
		attachment = new Attachment();
		attachment.setAttachmentName("Invoice List (1).csv");
		attachment.setSize(4662);
		attachment.setSizeForDisplay("4.55");
		attachment.setVisibility("Customer");
		attachment.setDisplayAttachmentName("Invoice List (1).csv");
		attachments.add(attachment);
		
		attachment = new Attachment();
		attachment.setAttachmentName("Invoice List (2).csv");
		attachment.setSize(4662);
		attachment.setSizeForDisplay("4.55");
		attachment.setVisibility("Customer");
		attachment.setDisplayAttachmentName("Invoice List (2).csv");
		attachments.add(attachment);

		final AttachmentContract contract = new AttachmentContract();
		contract.setIdentifier("1-85SLJEB");
		contract.setIncrement(0);
		contract.setStartRecordNumber(0);
		contract.setNewQueryIndicator(false);
		contract.setRequestType("Service Request");
		
		contract.setSessionHandle(crmSessionHandle);

		contract.setAttachments(attachments);

		service.uploadAttachments(contract);
		
		System.out.println("END");
		
	}
	
	@Test
	public void testUploadAttachment_defect11642() throws Exception {
		
		List<Attachment> attachments = new ArrayList<Attachment>();
		Attachment attachment = new Attachment();
		attachment.setAttachmentName("MPS 2.1 Use Case List_3.xlsx");
		attachment.setSize(9700);
		attachment.setSizeForDisplay("9.47");
		attachment.setVisibility("Customer");
		attachment.setDisplayAttachmentName("MPS 2.1 Use Case List_3.xlsx");
		attachments.add(attachment);
		
//		Attachment attachment = new Attachment();
//		attachment.setAttachmentName("MeterReadList_3.csv");
//		attachment.setSize(1493);
//		attachment.setSizeForDisplay("1.46");
//		attachment.setVisibility("Customer");
//		attachment.setDisplayAttachmentName("MeterReadList_3.csv");
//		attachments.add(attachment);

		final AttachmentContract contract = new AttachmentContract();
		contract.setIdentifier("1-8AODID5");
		contract.setIncrement(0);
		contract.setStartRecordNumber(0);
		contract.setNewQueryIndicator(false);
		contract.setRequestType("Service Request");
		
		contract.setSessionHandle(crmSessionHandle);

		contract.setAttachments(attachments);

		service.uploadAttachments(contract);
		
		System.out.println("END");
		
	}
	
	
	@Test
	public void testUploadAttachment_defect11896() throws Exception {
		
		List<Attachment> attachments = new ArrayList<Attachment>();
		Attachment attachment = new Attachment();
		attachment.setAttachmentName("New Microsoft Word Document#22_1392880478345.docx");
		attachment.setSize(16384);
		attachment.setSizeForDisplay("16.00");
		attachment.setVisibility("Partner");
		attachment.setDisplayAttachmentName("New Microsoft Word Document#22_1392880478345.docx");
		attachments.add(attachment);
		
		attachment = new Attachment();
		attachment.setAttachmentName("NewMicrosoftWordDocument17_1392880478345.docx");
		attachment.setSize(16384);
		attachment.setSizeForDisplay("16.00");
		attachment.setVisibility("Partner");
		attachment.setDisplayAttachmentName("NewMicrosoftWordDocument17_1392880478345.docx");
		attachments.add(attachment);
		
		attachment = new Attachment();
		attachment.setAttachmentName("New Microsoft Word Document#23_1392880478345.docx");
		attachment.setSize(16384);
		attachment.setSizeForDisplay("16.00");
		attachment.setVisibility("Partner");
		attachment.setDisplayAttachmentName("New Microsoft Word Document#23_1392880478345.docx");
		attachments.add(attachment);
		

		final AttachmentContract contract = new AttachmentContract();
		contract.setIdentifier("1-86N0KZW");
		contract.setIncrement(0);
		contract.setStartRecordNumber(0);
		contract.setNewQueryIndicator(false);
		contract.setRequestType("Hardware Debrief");
		
		contract.setSessionHandle(crmSessionHandle);

		contract.setAttachments(attachments);

		File file1 = new File("\\\\157.184.96.191\\siebel_fs_b_siebelftp\\SWPORTAL\\ACTIMPORT\\New Microsoft Word Document#16_1392880478345.docx");
		System.out.println(file1.exists());
		
		service.uploadAttachments(contract);
		
		System.out.println("END");
		
	}
	
	
	@Test
	public void testUploadAttachment_defect11315() throws Exception {
		
		List<Attachment> attachments = new ArrayList<Attachment>();
		Attachment attachment = new Attachment();
		attachment.setAttachmentName("test201214_1393268017661_test26.xlsx");
		attachment.setSize(8746);
		attachment.setSizeForDisplay("8.54");
		attachment.setVisibility("Customer");
		attachment.setDisplayAttachmentName("test201214_1393268017661_test26.xlsx");
		attachments.add(attachment);
		
		attachment = new Attachment();
		attachment.setAttachmentName("MassUploadTemplateHardwareOrder26_1393268033120.xlsx");
		attachment.setSize(8746);
		attachment.setSizeForDisplay("8.54");
		attachment.setVisibility("Customer");
		attachment.setDisplayAttachmentName("MassUploadTemplateHardwareOrder26_1393268033120.xlsx");
		attachments.add(attachment);
		

		final AttachmentContract contract = new AttachmentContract();
		contract.setIdentifier("1-8MDCMSV");
		contract.setIncrement(0);
		contract.setStartRecordNumber(0);
		contract.setNewQueryIndicator(false);
		contract.setRequestType("Service Request");
		
		contract.setSessionHandle(crmSessionHandle);

		contract.setAttachments(attachments);

		service.uploadAttachments(contract);
		
		System.out.println("END");
		
	}
	
	
	@Test
	public void testUploadAttachment_defect11315_2() throws Exception {
		
		List<Attachment> attachments = new ArrayList<Attachment>();
		
		Attachment attachment = new Attachment();
		attachment.setAttachmentName("Defect11315_116Test.xlsx");
		attachment.setSize(8746);
		attachment.setSizeForDisplay("8.54");
		attachment.setVisibility("Partner");
		attachment.setDisplayAttachmentName("Defect11315_116Test.xlsx");
		attachments.add(attachment);
		
		attachment = new Attachment();
		attachment.setAttachmentName("Defect11315_117Test.xlsx");
		attachment.setSize(8746);
		attachment.setSizeForDisplay("8.54");
		attachment.setVisibility("Partner");
		attachment.setDisplayAttachmentName("Defect11315_117Test.xlsx");
		attachments.add(attachment);
		
		attachment = new Attachment();
		attachment.setAttachmentName("Defect11315_118Test.xlsx");
		attachment.setSize(8746);
		attachment.setSizeForDisplay("8.54");
		attachment.setVisibility("Partner");
		attachment.setDisplayAttachmentName("Defect11315_118Test.xlsx");
		attachments.add(attachment);

		final AttachmentContract contract = new AttachmentContract();
		contract.setIdentifier("1-86TEHV1");
		contract.setIncrement(0);
		contract.setStartRecordNumber(0);
		contract.setNewQueryIndicator(false);
		contract.setRequestType("Claim Update");
		
//		\\\\plexna2t1p001\\siebelftp\\SWPORTAL\\MRIMPORT\\Defect11315_107Test.xlsx
		
//		/siebelftp/SWPORTAL/MRIMPORT/
		
		File file1 = new File("\\\\157.184.96.191\\siebel_fs_b_siebelftp\\SWPORTAL\\ACTIMPORT\\Defect11315_111Test.xlsx");
		System.out.println(file1.exists());
		file1 = new File("\\\\157.184.96.191\\siebel_fs_b_siebelftp\\SWPORTAL\\ACTIMPORT\\Defect11315_112Test.xlsx");
		System.out.println(file1.exists());
		
		contract.setSessionHandle(crmSessionHandle);

		contract.setAttachments(attachments);

		service.uploadAttachments(contract);
		
		System.out.println("END");
		
	}
	
	
	@Test
	public void testUploadAttachment_Issue() throws Exception {
		
		List<Attachment> attachments = new ArrayList<Attachment>();
		Attachment attachment = new Attachment();
		attachment.setAttachmentName("UploadIssue_3.xlsx");
		attachment.setSize(8746);
		attachment.setSizeForDisplay("8.54");
		attachment.setVisibility("Customer");
		attachment.setDisplayAttachmentName("UploadIssue_3.xlsx");
		attachments.add(attachment);
		

		final AttachmentContract contract = new AttachmentContract();
		contract.setIdentifier("1-9C2SL8V");
		contract.setIncrement(0);
		contract.setStartRecordNumber(0);
		contract.setNewQueryIndicator(false);
		contract.setRequestType("Service Request");
		
		contract.setSessionHandle(crmSessionHandle);

		contract.setAttachments(attachments);

		service.uploadAttachments(contract);
		
		System.out.println("END");
		
	}
	
	
	@Test
	public void testUploadAttachment_defect12875() throws Exception {
		
		List<Attachment> attachments = new ArrayList<Attachment>();
		Attachment attachment = new Attachment();
		attachment.setAttachmentName("TestFile12785_test7.csv");
		
//		attachment.setSize(8741);
		attachment.setSize(0);
		
//		attachment.setSizeForDisplay("8.53");
		
		attachment.setVisibility("Customer");
		attachment.setDisplayAttachmentName("TestFile12785_test7.csv");
		attachments.add(attachment);
		
		final AttachmentContract contract = new AttachmentContract();
		contract.setIdentifier("1-8WK6VVN");
		contract.setIncrement(0);
		contract.setStartRecordNumber(0);
		contract.setNewQueryIndicator(false);
		contract.setRequestType("Service Request");
		
		contract.setSessionHandle(crmSessionHandle);

		contract.setAttachments(attachments);

		service.uploadAttachments(contract);
		
		System.out.println("END");
		
	}
	
	@Test
	public void testUploadAttachment_defect16358() throws Exception {
		
		List<Attachment> attachments = new ArrayList<Attachment>();
		Attachment attachment = new Attachment();
		attachment.setAttachmentName("TestFile12785_test7.csv");
		
//		attachment.setSize(8741);
		attachment.setSize(0);
		
//		attachment.setSizeForDisplay("8.53");
		
		attachment.setVisibility("Customer");
		attachment.setDisplayAttachmentName("TestFile12785_test7.csv");
		attachments.add(attachment);
		
		final AttachmentContract contract = new AttachmentContract();
		contract.setIdentifier("1-8WK6VVN");
		contract.setIncrement(0);
		contract.setStartRecordNumber(0);
		contract.setNewQueryIndicator(false);
		contract.setRequestType("Service Request");
		
		contract.setSessionHandle(crmSessionHandle);
		
		contract.setAttachments(attachments);
		
		service.uploadAttachments(contract);
		
		System.out.println("END");
		
	}
	
}
