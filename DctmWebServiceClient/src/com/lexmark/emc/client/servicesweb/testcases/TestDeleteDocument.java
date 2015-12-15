package com.lexmark.emc.client.servicesweb.testcases;
import java.io.StringWriter;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.SOAPException;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.soap.SOAPBinding;
import javax.xml.ws.soap.SOAPFaultException;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lexmark.properties.schema.sw.document.DocumentProperties;
import com.lexmark.properties.schema.sw.document.DocumentProperties.DocumentInfo;
import com.lexmark.properties.schema.sw.folder.FolderProperties;
import com.lexmark.properties.schema.sw.folder.FolderProperties.FolderInfo;
import com.lexmark.schemas.ecm.dctmwebservice.ecmschema._1.DctmWebService;
import com.lexmark.schemas.ecm.dctmwebservice.ecmschema._1.DctmWebService_Service;
import com.lexmark.schemas.ecm.dctmwebservice.ecmschema._1.ECMServiceException_Exception;
import com.sun.xml.ws.api.message.Header;
import com.sun.xml.ws.api.message.Headers;
import com.sun.xml.ws.developer.WSBindingProvider;

public class TestDeleteDocument {

	private static String inputString = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
			+ "<dctm-properties xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">"
			+ "<object_name>Test1212</object_name>"
			/*
			 * + "<acl_name>asas</acl_name>" + "<acl_domain>dfsd</acl_domain>"
			 */
			+ "<r_folder_path>/Temp</r_folder_path>"
			+ "<r_object_type>dm_document</r_object_type>"
			/*
			 * + "<locale>dsfdsf</locale>" + "<portal_uid>dsfds</portal_uid>" +
			 * "<mdmlevel>dsfdsf</mdmlevel>" +
			 * "<report_name>sdfdsf</report_name>" +
			 * "<report_admin_id>sdfdsf</report_admin_id>" +
			 * "<portal_userid>dsfdsf</portal_userid>" +
			 * "<account_name>sdfdsf</account_name>" +
			 * "<owner_name></owner_name>" + "<keywords>" +
			 * "<values>qwewqewq</values>" + "<values>fsdf</values>" +
			 * "</keywords>"
			 */
			+ "</dctm-properties>";

	public static void main(String[] args)  {

		try {
			QName serviceName = new QName(
					"http://schemas.lexmark.com/ecm/dctmwebservice/ecmschema/1.0",
					"DctmWebService");

			URL baseUrl = DctmWebService_Service.class.getResource(".");
			URL url = new URL(baseUrl,
					DocumentumStringUtils.SERVICE_END_POINT);
			
			DctmWebService_Service service = new DctmWebService_Service(url,
					serviceName);

			DctmWebService port = service.getDctmWebServicePort();

			// ((BindingProvider)port).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY,"http://new/endpointaddress");
			// ((BindingProvider)
			// port).getRequestContext().put(JAXWSProperties.CONNECT_TIMEOUT,
			// TIME_OUT_IN_MILLIS/1000000);
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
 

			try {
				
			} catch (SOAPFaultException e) {
				
			
			}
		} catch (Exception e) {
			
			e.printStackTrace();
			 
		} 

	}
	private static String getInputParameter() {
		StringWriter write = new StringWriter();

		try {
			JAXBContext jContext = JAXBContext
					.newInstance(DocumentProperties.class);

			Marshaller marshaller = jContext.createMarshaller();

			DocumentInfo oInfo= new DocumentInfo();
			
			oInfo.setRObjectId("0901f3f5800794e3");
			
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
