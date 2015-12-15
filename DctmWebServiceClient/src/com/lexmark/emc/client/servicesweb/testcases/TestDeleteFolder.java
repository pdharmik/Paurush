package com.lexmark.emc.client.servicesweb.testcases;
import java.io.File;
import java.io.FileInputStream;
import java.io.StringWriter;
import java.net.URL;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.soap.SOAPBinding;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lexmark.properties.schema.sw.folder.FolderProperties;
import com.lexmark.properties.schema.sw.folder.FolderProperties.FolderInfo;
import com.lexmark.schemas.ecm.dctmwebservice.ecmschema._1.DctmWebService;
import com.lexmark.schemas.ecm.dctmwebservice.ecmschema._1.DctmWebService_Service;
import com.sun.xml.ws.api.message.Header;
import com.sun.xml.ws.api.message.Headers;
import com.sun.xml.ws.developer.WSBindingProvider;

public class TestDeleteFolder {

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



		

	}
	
	
	private static String getInputParameter() {
		StringWriter write = new StringWriter();

		try {
			JAXBContext jContext = JAXBContext
					.newInstance(FolderProperties.class);

			Marshaller marshaller = jContext.createMarshaller();

			FolderInfo folderInfo = new FolderInfo();

			/*folderInfo.setRFolderPath("/Temp/Example/folder-to-be-deleted");*/
		 
			
			folderInfo.setRFolderPath("/Temp/services-web/services-web11");
		
			FolderProperties prop = new FolderProperties();
			prop.setFolderInfo(folderInfo);

			marshaller.marshal(prop, write);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return write.toString();

	}

}
