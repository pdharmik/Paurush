package com.lexmark.emc.client.servicesweb.testcases;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.StringWriter;
import java.net.URL;
import java.util.Date;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.soap.SOAPBinding;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lexmark.properties.schema.sw.document.DocumentProperties;
import com.lexmark.properties.schema.sw.document.DocumentProperties.DocumentInfo;
import com.lexmark.schemas.ecm.dctmwebservice.ecmschema._1.DctmWebService;
import com.lexmark.schemas.ecm.dctmwebservice.ecmschema._1.DctmWebService_Service;
import com.sun.xml.ws.api.message.Header;
import com.sun.xml.ws.api.message.Headers;
import com.sun.xml.ws.developer.WSBindingProvider;

public class TestCreateDocument {

	/*private static String sampleInputString = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>" +
			"<DocumentProperties>" +
				"<document_info>" +
					"<object_name>Test.doc</object_name>" +
					"<a_content_type>msword</a_content_type>" +
					//... other metadata information goes here
					"<keywords>" +
						"<values>keywords-1</values>" +
						"<values>keywords-2</values>" +
					"</keywords>" +
				"</document_info>" +
			"</DocumentProperties>";*/


	public static void main(String[] args) throws Exception {
		
		
		QName serviceName = new QName(
				"http://schemas.lexmark.com/ecm/dctmwebservice/ecmschema/1.0",
				"DctmWebService");

		URL baseUrl = DctmWebService_Service.class.getResource(".");
		URL url = new URL(baseUrl,
				DocumentumStringUtils.SERVICE_END_POINT);

		DctmWebService_Service service = new DctmWebService_Service(url,
				serviceName);

		DctmWebService port = service.getDctmWebServicePort();

		 
		((BindingProvider) port).getRequestContext().put(
				"com.sun.xml.ws.request.timeout", 100000);

		String token = port.authenticate(DocumentumStringUtils.REPOSITORY_NAME, DocumentumStringUtils.SUPERUSER_NAME,  DocumentumStringUtils.SUPERUSER_NAME,
				DocumentumStringUtils.PASSWORD, DocumentumStringUtils.APPLICATION_NAME);

	 

		WSBindingProvider bp = ((WSBindingProvider) port);
		SOAPBinding binding = (SOAPBinding) ((BindingProvider) port)
				.getBinding();
		binding.setMTOMEnabled(true);

		Document document = DocumentBuilderFactory.newInstance()
				.newDocumentBuilder().newDocument();

		Element documentumSecurityToken = document
				.createElement("DocumentumSecurityToken");
		Element eToken = document.createElement("token");
		eToken.setTextContent(token);

		documentumSecurityToken.appendChild(eToken);

		Header h = Headers.create(documentumSecurityToken);
		bp.setOutboundHeaders(h);

		
	}
	
	private static byte[] getContent() throws Exception {
		InputStream in = TestCreateDocument.class.getResourceAsStream("deviceList.pdf");
		ByteArrayOutputStream  outStream = new ByteArrayOutputStream();
		int readCount = 0; 
		byte[] buffer = new byte[1024];
		while( (readCount = in.read(buffer))> 0) {
			outStream.write(buffer, 0, readCount);
		}
		return outStream.toByteArray();
	}
	
	private static String getInputParameter() {
		StringWriter write = new StringWriter();

		try {
			JAXBContext jContext = JAXBContext
					.newInstance(DocumentProperties.class);

			Marshaller marshaller = jContext.createMarshaller();

			DocumentInfo oInfo= new DocumentInfo();
			
			String objectName = "devicelist-" + new Date().getTime() + ".xls";
			
			//set mandatory attributes
			oInfo.setObjectName(objectName);
			oInfo.setAContentType("excel");
			//path to the folder where document will be created and stored
			oInfo.setRFolderPath("/Services Web/servicesPortal/reports");
			oInfo.setRObjectType("portal_services_document");
			oInfo.setLocale("en");
			oInfo.setLegalName("123456");
		    oInfo.setScheduleFrequency("1");
			// end of mandatory attributes
			
			// set other attributes
			
			oInfo.setUsernumber("ecsb222");
			
			oInfo.setMdmId("11212");
			oInfo.setMdmlevel("1");
			
			/*oInfo.setObjectName("Test.doc");
			
			oInfo.setAContentType("msword");
			
			Keywords keywords= new Keywords();
			
			keywords.getValues().add("keywords-1");
			keywords.getValues().add("keywords-2");
			oInfo.setKeywords( keywords);*/
			 

            DocumentProperties oProperties= new DocumentProperties();
            
            oProperties.setDocumentInfo(oInfo);
            marshaller.marshal(oProperties, write);
			 
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return write.toString();

	}

	

}
