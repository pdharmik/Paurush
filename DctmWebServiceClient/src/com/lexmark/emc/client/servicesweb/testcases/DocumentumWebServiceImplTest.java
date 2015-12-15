package com.lexmark.emc.client.servicesweb.testcases;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.regex.Pattern;

import javax.xml.ws.soap.SOAPFaultException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.lexmark.emc.client.servicesweb.impl.DocumentumWebServiceFacadeImpl;
import com.lexmark.properties.schema.sw.document.DocumentProperties.DocumentInfo;
import com.lexmark.properties.schema.sw.document.DocumentProperties.DocumentInfo.Keywords;


public class DocumentumWebServiceImplTest {

	private DocumentumWebServiceFacadeImpl target;
	@Before
	public void setup() {
		//DocumentumWebServiceImpl(String endPoint, String repositoryName, String userId,
		//String password, String applicationName) 
		target = new DocumentumWebServiceFacadeImpl(DocumentumStringUtils.SERVICE_END_POINT,
				DocumentumStringUtils.REPOSITORY_NAME, DocumentumStringUtils.SUPERUSER_NAME,
				DocumentumStringUtils.PASSWORD, DocumentumStringUtils.APPLICATION_NAME		
		);
	}
	@Test
	public void testAuthenticate() {
		String token = target.authenticate();
		
		Assert.assertNotNull(token);
		
		
	}

	@Test
	public void testCreateDocument() throws Exception {
		
		try {
			String rObjectId = target.createDocument(initProperties(), getContent());
			Assert.assertNotNull(rObjectId);
			
		} catch(RuntimeException ex) {
			if(ex.getCause() instanceof SOAPFaultException) {
				SOAPFaultException soapException = (SOAPFaultException) ex.getCause();
				if(isFormatWrong(soapException.getMessage())) {
					
				}
			}
		}
		
	//	Boolean isSuccess = target.deleteDocument(rObjectId);
		
	//	Assert.assertTrue(isSuccess);
	}
	
	private boolean isFormatWrong(String message) {
		String regEx="Format .*is invalid";
		return Pattern.compile(regEx).matcher(message).find();
	}
	
	private static byte[] getContent() throws Exception {
		InputStream in = DocumentumWebServiceImplTest.class.getResourceAsStream("CrystalViewer.xls");
		ByteArrayOutputStream  outStream = new ByteArrayOutputStream();
		int readCount = 0; 
		byte[] buffer = new byte[1024];
		while( (readCount = in.read(buffer))> 0) {
			outStream.write(buffer, 0, readCount);
		}
		return outStream.toByteArray();
	}
	
	private DocumentInfo initProperties() {
		DocumentInfo metaData = new DocumentInfo();
		// documentum needed
		metaData.setLocale("en");
		metaData.setAContentType("powerpoint");
		metaData.setFileName("abc.ppt");
		metaData.setObjectName(new Date().getTime() + "-" + metaData.getFileName());
		metaData.setRFolderPath("/Services Web/servicesPortal/securedDocuments");
		metaData.setRObjectType("portal_services_document");
		metaData.setScheduleFrequency("O");
		metaData.setDefinitionId("5");
		metaData.setLegalName("CUMMINS INC");
		metaData.setMdmId("6681");
		metaData.setMdmlevel("Global");
		metaData.setUsernumber("BP10000591");
		metaData.setFileContentDate(new Date());
		
//		 - <single>
//		  <object_name>RichardUploadFilePdf.pdf</object_name> 
//		  <locale>en</locale> 
//		  <mdmlevel>Legal</mdmlevel> 
//		  <mdm_id>236295</mdm_id> 
//		  <usernumber>BP10000227</usernumber> 
//		  <definition_id>2</definition_id> 
//		  <legal_name>CUMMINS INC</legal_name> 
//		  <a_content_type>pdf</a_content_type> 
//		  <r_folder_path>/ServicesWeb/servicesPortal/reports</r_folder_path> 
//		  <r_object_type>portal_services_document</r_object_type> 
//		  </single>
//		Keywords keywords= new Keywords();
//		
//		keywords.getValues().add("keywords-1");
//		metaData.setKeywords( keywords);

		return metaData;
	}
	
	public static void main(String[] args) throws Exception {
		DocumentumWebServiceImplTest test = new DocumentumWebServiceImplTest();
		test.setup();
		test.testCreateDocument();
	}

}
