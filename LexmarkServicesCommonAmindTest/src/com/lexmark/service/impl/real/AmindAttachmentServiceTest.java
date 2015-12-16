package com.lexmark.service.impl.real;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import org.junit.BeforeClass;
import org.junit.Test;

import com.lexmark.contract.AttachmentContract;
import com.lexmark.contract.DeleteAttachmentContract;
import com.lexmark.domain.Attachment;
import com.lexmark.result.AttachmentListResult;
import com.lexmark.result.AttachmentResult;


/**
 * @author vpetruchok
 * @version 1.0, 2012-11-07
 */
public class AmindAttachmentServiceTest extends AmindServiceTest {
	
	private static AmindAttachmentService service;
	
    @BeforeClass
    public static void setUpBeforClass() throws Exception {
    	service = new AmindAttachmentService();
    	service.setStatelessSessionFactory(statelessSessionFactory);
    }

    /**
     * @see UploadAttachmentServiceTest#testUploadAttachment()
     */
    @Test
    public void testUploadAttachments() throws Exception {
    	AttachmentContract c = new AttachmentContract();
    	c.setRequestType("Service Request");
    	c.setIdentifier("mock-identifier");
    	c.setUserFileName("mock-userFileName");
    	Attachment a = new Attachment();
    	a.setAttachmentName("mock-attachmentName");
		c.setAttachments(MiscTest.newArrayList(a));
		service.uploadAttachments(c);
    }
    
    @Test
    public void testUploadAttachments_QA() throws Exception {
    	AttachmentContract c = new AttachmentContract();
    	c.setRequestType("Service Request");
    	c.setIdentifier("1-78OHCMB");
    	Attachment a = new Attachment();
    	a.setAttachmentName("Attachy2~[;]{},+=_~&^%$#@`~!_1380091280129_1386600396282.docx");
    	a.setVisibility("Customer");
    	a.setSize(7962);
    	c.setAttachments(MiscTest.newArrayList(a));
    	service.uploadAttachments(c);
    }
    
//    @Test
//    public void testUploadAttachments_Prod_defect12875() throws Exception {
//    	AttachmentContract c = new AttachmentContract();
//    	c.setRequestType("Service Request");
//    	c.setIdentifier("1-8WK6VVN");
//    	Attachment a = new Attachment();
//    	a.setAttachmentName("ManageAddress_1398440755927.csv");
//    	a.setVisibility("Customer");
//    	a.setSize(0);
//    	c.setAttachments(MiscTest.newArrayList(a));
//    	service.uploadAttachments(c);
//    }

    @SuppressWarnings("unused")
	@Test
	public void testDownloadAttachment() throws Exception {
		AttachmentContract c = new AttachmentContract();
		c.setRequestType("Service Request");
		c.setIdentifier("mock-identifier");
		c.setUserFileName("mock-userFileName");
		AttachmentResult r = service.downloadAttachment(c);
	}
    
//	@Test
//	public void testDownloadAttachment_defect8980() throws Exception {
//		AttachmentContract c = new AttachmentContract();
//		c.setRequestType("Service Request");
////		c.setIdentifier("1-6VTHLM5");
//		c.setIdentifier("1-6ZZC0MB");
////		c.setUserFileName("15.xlsx");
////		c.setUserFileName("AttachmentTestIvan1.doc");
////		c.setUserFileName("Attachy2~[;]{},+=_~&^%$#@`~!_1380091280129.doc");
////		c.setUserFileName("Attachy2~[.doc;]{},+=_~&^%$#@`~!_1380886231451.doc");
////		c.setUserFileName("DefectStatus.xls");
//		c.setUserFileName("4132_QA_1350553047241.docx");
//		
//		AttachmentResult r = service.downloadAttachment(c);
//	}
	
	@Test
	public void testDownloadAttachment_regular_defect8980() throws Exception {
		AttachmentContract c = new AttachmentContract();
		c.setRequestType("Service Request");
		c.setIdentifier("1-8PCS3C3");
		c.setUserFileName("Portal_MassUploadTemplateServiceOrder_1393615827629.xls");
		
		AttachmentResult r = service.downloadAttachment(c);
		if (r != null) {
			System.out.println("File name: " + (r.getFileName() == null ? "null" : r.getFileName()));
			System.out.println("Input stream: " + (r.getFileStream() == null ? "null" : "not null"));
		} else {
			System.out.println("Result is null!");
		}
	}

    @Test
	public void testDeleteAttachment() throws Exception {
    	AttachmentContract c = new AttachmentContract();
    	c.setSessionHandle(crmSessionHandle);
    	c.setIdentifier("mock-identifier");
		service.deleteAttachment(c);
	
	}
    
    
    @Test
    public void testDownloadAttachment_defect8980() throws Exception {
     AttachmentContract c = new AttachmentContract();
     c.setRequestType("Service Request");
   //  c.setIdentifier("1-6VTHLM5");
     c.setIdentifier("1-6ZZC0MB");
   //  c.setUserFileName("15.xlsx");
   //  c.setUserFileName("AttachmentTestIvan1.doc");
   //  c.setUserFileName("Attachy2~[;]{},+=_~&^%$#@`~!_1380091280129.doc");
   //  c.setUserFileName("Attachy2~[.doc;]{},+=_~&^%$#@`~!_1380886231451.doc");
   //  c.setUserFileName("DefectStatus.xls");
     c.setUserFileName("4132_QA_1350553047241.docx");
     
     AttachmentResult r = service.downloadAttachment(c);
    }
    
    @Test
    public void testDownloadAttachment_regular_defect8980_2() throws Exception {
     AttachmentContract c = new AttachmentContract();
     c.setRequestType("Service Request");
     c.setIdentifier("1-6VTHLM5");
     c.setUserFileName("Attachy2~[;]{},+=_~&^%$#@`~!_1380091280129.doc");
     
     AttachmentResult r = service.downloadAttachment(c);
     
     if(r!=null) {
    	 System.out.println("File name: " + (r.getFileName() == null ? "null" : r.getFileName()));
    	 System.out.println("Input stream: " + (r.getFileStream()==null ? "null" : "not null"));
     }
     else {
    	 System.out.println("Result is null!");
     }
     
    }

    @Test
	public void testDownloadAttachment_defect11315() throws Exception {
		AttachmentContract contract = new AttachmentContract();
		contract.setIdentifier("1-6ZOIZUB");
		contract.setUserFileName("01_26_2014.xls");
//		contract.setUserFileName("Accounts.xls");
//		contract.setUserFileName("Test1.xls");
		contract.setRequestType("Claim Update");
		contract.setIncrement(0);
		contract.setStartRecordNumber(0);
		contract.setNewQueryIndicator(false);
		contract.setSessionHandle(crmSessionHandle);
		
//		Meter Read Status

		AttachmentResult result = service.downloadAttachment(contract);
		
		System.out.println(result == null);
		System.out.println("File name: " + result.getFileName());
		System.out.println("File stream: " + (result.getFileStream() == null));
	}
    
    @Test
    public void testDownloadAttachment_WeirdName_defect8980() throws Exception {
    	AttachmentContract c = new AttachmentContract();
    	c.setRequestType("Service Request");
    	c.setIdentifier("1-7C6YZDJ");
    	c.setUserFileName("Attachy2~[;]{},+=_~&^%$#@`~!_1380091280129_1386600396282.docx");
    	
    	AttachmentResult r = service.downloadAttachment(c);
    	
    	if(r!=null) {
    		System.out.println("File name: " + (r.getFileName() == null ? "null" : r.getFileName()));
    		System.out.println("Input stream: " + (r.getFileStream()==null ? "null" : "not null"));
    	}
    	else {
    		System.out.println("Result is null!");
    	}
    	
    }
    
    @Test
	public void testDownloadAttachment_defect8980_2() throws Exception {
		AttachmentContract c = new AttachmentContract();
		c.setRequestType("Service Request");
		c.setIdentifier("1-8N2ZJEF");
		c.setUserFileName("Attachy3~[~]{},~~_~~~~~@~~!~-~1234567890_1393311596220.docx");
		c.setSessionHandle(crmSessionHandle);

		AttachmentResult r = service.downloadAttachment(c);

		System.out.println(r.getFileStream() == null);
		System.out.println(r.getFileName());
	}
    
    @Test
  	public void testDownloadAttachment_defect8980_2_Working() throws Exception {
  		AttachmentContract c = new AttachmentContract();
  		c.setRequestType("Service Request");
  		c.setIdentifier("1-8PCS3C3");
  		c.setUserFileName("Portal_MassUploadTemplateServiceOrder_1393615827629.xls");
  		c.setSessionHandle(crmSessionHandle);

  		AttachmentResult r = service.downloadAttachment(c);

  		System.out.println(r.getFileStream() == null);
  		System.out.println(r.getFileName());
  	}
    

    @Test
	public void testDownloadAttachment_defect11896_DownloadFileIssue()
			throws Exception {
		AttachmentContract contract = new AttachmentContract();
		contract.setIdentifier("1-8AFYLTM");
		contract.setUserFileName("MassUploadTemplateMADC~4~_1394603147337.xls");
		contract.setRequestType("Hardware Debrief");
		contract.setIncrement(0);
		contract.setStartRecordNumber(0);
		contract.setNewQueryIndicator(false);
		contract.setSessionHandle(crmSessionHandle);

		AttachmentResult result = service.downloadAttachment(contract);

		System.out.println(result == null);
		System.out.println("File name: " + result.getFileName());
		System.out.println("File stream: " + (result.getFileStream() == null));
//
		InputStream stream = result.getFileStream();
		System.out.println(stream.available());

		OutputStream outputStream = new FileOutputStream(new File("D:\\Lexmark\\Defects\\Defect11896\\TestDownload\\TestDownload.xls"));
		int read = 0;
		byte[] bytes = new byte[1024];

		while ((read = stream.read(bytes)) != -1) {
			outputStream.write(bytes, 0, read);
		}
		
		DeleteAttachmentContract DAContract = new DeleteAttachmentContract(); 
		DAContract.setFileStream(result.getFileStream());
		DAContract.setFile(result.getFile());
		service.deleteTempFileAfterDownload(DAContract);
		
		System.out.println("END");
	}
    
    
    @Test
	public void testDownloadAttachment_defect12269() throws Exception {
		AttachmentContract contract = new AttachmentContract();
//		contract.setIdentifier("1-8TF9QVN");
//		contract.setUserFileName("MassUploadTemplateServiceOrder~3~_1394445440732");
		
//		contract.setUserFileName("009420159~Lista_de_Contadores_LeÃƒ-dos~20140227025759057.csv");
//		contract.setUserFileName("009420159~MeterReadList~20140307064413044.csv");
//		contract.setUserFileName("1-8ON4TPX");
		contract.setUserFileName("1-92K04V5");
		contract.setIdentifier("1-2CFDZM7");
		
		contract.setRequestType("Meter Read Status");
		contract.setIncrement(40);
		contract.setStartRecordNumber(0);
		contract.setNewQueryIndicator(true);
		contract.setSessionHandle(crmSessionHandle);
	
		Map<String,Object> sortCriteria = new HashMap<String, Object>();
		sortCriteria.put("requestNumber", "ASCENDING");
		contract.setSortCriteria(sortCriteria);

		AttachmentResult result = service.downloadAttachment(contract);
		
		OutputStream outputStream = new FileOutputStream(new File("D:\\Lexmark\\Defects\\Defect12394\\DownloadIssue14.csv"));
		int read = 0;
		byte[] bytes = new byte[1024];

		InputStream stream = result.getFileStream();
		while ((read = stream.read(bytes)) != -1) {
			outputStream.write(bytes, 0, read);
		}
		
		System.out.println(result == null);
		System.out.println("File name: " + result.getFileName());
		System.out.println("File stream: " + (result.getFileStream() == null));
	}
    
    @Test
    public void testDownloadAttachment_INC0089985() throws Exception {
    	AttachmentContract contract = new AttachmentContract();
    	contract.setUserFileName("2015 02 18 Postbank FIS 45561173");
    	contract.setIdentifier("1-NMUZ232");
    	contract.setRequestType("Hardware Debrief");
    	contract.setIncrement(0);
    	contract.setStartRecordNumber(0);
    	contract.setNewQueryIndicator(false);
    	contract.setSessionHandle(crmSessionHandle);
    	
    	AttachmentResult result = service.downloadAttachment(contract);
    	
    	OutputStream outputStream = new FileOutputStream(new File("E:\\Lexmark\\Defects\\DownloadIssue.csv"));
    	int read = 0;
    	byte[] bytes = new byte[1024];
    	
    	InputStream stream = result.getFileStream();
    	while ((read = stream.read(bytes)) != -1) {
    		outputStream.write(bytes, 0, read);
    	}
    	
    	System.out.println(result == null);
    	System.out.println("File name: " + result.getFileName());
    	System.out.println("File stream: " + (result.getFileStream() == null));
    }
    
    @Test
    public void testDownloadAttachment_defect17687() throws Exception {
    	AttachmentContract contract = new AttachmentContract();
    	contract.setUserFileName("2015 02 18 Postbank FIS 45561173 1-NMUZ232.xlsx");
    	contract.setIdentifier("1-NMUZ232");
    	contract.setRequestType("Hardware Debrief");
    	contract.setIncrement(0);
    	contract.setStartRecordNumber(0);
    	contract.setNewQueryIndicator(false);
    	contract.setSessionHandle(crmSessionHandle);
    	
    	AttachmentResult result = service.downloadAttachment(contract);
    	
//    	OutputStream outputStream = new FileOutputStream(new File("E:\\Lexmark\\Defects\\DownloadIssue.csv"));
//    	int read = 0;
//    	byte[] bytes = new byte[1024];
//    	
//    	InputStream stream = result.getFileStream();
//    	while ((read = stream.read(bytes)) != -1) {
//    		outputStream.write(bytes, 0, read);
//    	}
    	
    	System.out.println("File name: " + result.getFileName());
    	System.out.println("File stream: " + (result.getFileStream() == null));
    }
    
    @Test
    public void testDownloadAttachment_defect18211() throws Exception {
    	AttachmentContract contract = new AttachmentContract();
    	contract.setUserFileName("RFC_~~~~~~~~~~~@~_Template_Move_or_Change@~~~~~~~~.xls");
    	contract.setIdentifier("1-PNVU7SH");
    	contract.setRequestType("Claim Create");
    	contract.setIncrement(0);
    	contract.setStartRecordNumber(0);
    	contract.setNewQueryIndicator(false);
    	contract.setSessionHandle(crmSessionHandle);
    	
    	AttachmentResult result = service.downloadAttachment(contract);
    	
//    	OutputStream outputStream = new FileOutputStream(new File("E:\\Lexmark\\Defects\\DownloadIssue.csv"));
//    	int read = 0;
//    	byte[] bytes = new byte[1024];
//    	
//    	InputStream stream = result.getFileStream();
//    	while ((read = stream.read(bytes)) != -1) {
//    		outputStream.write(bytes, 0, read);
//    	}
    	
    	System.out.println("File name: " + result.getFileName());
    	System.out.println("File stream: " + (result.getFileStream() == null));
    }
    
    
    @Test
    public void testUploadAttachments_Prod_defect12875() throws Exception {
     AttachmentContract c = new AttachmentContract();
     c.setRequestType("Service Request");
     c.setIdentifier("1-PBPVSFB");
     Attachment a = new Attachment();
     a.setAttachmentName("ContÃƒÂ¡ctos_new_testing_now_14328390204100.xlsx");
     a.setVisibility("Employee");
     a.setSize(0);
     c.setAttachments(MiscTest.newArrayList(a));
     service.uploadAttachments(c);
    }
    
    @Test
    public void testUploadAttachments_Prod_defect18268() throws Exception {
    	AttachmentContract c = new AttachmentContract();
    	c.setRequestType("Service Request");
    	c.setIdentifier("1-PPNYERX");
    	Attachment a = new Attachment();
    	a.setAttachmentName("èµ„äº§æ•°æ�®æ��äº¤æ¨¡æ�¿~2_1435297651675.xls");
    	a.setDisplayAttachmentName("èµ„äº§æ•°æ�®æ��äº¤æ¨¡æ�¿(2)_1435297651675.xls");
    	a.setVisibility("Customer");
    	a.setSize(35328);
    	a.setSizeForDisplay("34.50");
    	c.setAttachments(MiscTest.newArrayList(a));
    	service.uploadAttachments(c);
    }
    
    @Test
    public void testUploadAttachments_Prod_defect19628() throws Exception {
    	AttachmentContract c = new AttachmentContract();
    	c.setRequestType("Service Request");
    	c.setIdentifier("1-RKXGT33");
    	Attachment a = new Attachment();
    	a.setAttachmentName("MassUploadTemplateMADC(3).xls");
    	a.setDisplayAttachmentName("MassUploadTemplateMADC(3).xls");
    	a.setVisibility("Employee");
    	a.setSize(430);
    	a.setSizeForDisplay("0.43");
    	c.setAttachments(MiscTest.newArrayList(a));
    	service.uploadAttachments(c);
    }
    
}
