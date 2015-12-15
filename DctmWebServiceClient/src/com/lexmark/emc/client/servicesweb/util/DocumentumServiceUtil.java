package com.lexmark.emc.client.servicesweb.util;

import java.io.ByteArrayInputStream;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

//import org.apache.log4j.Logger;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import com.lexmark.properties.schema.sw.document.DocumentProperties;
import com.lexmark.properties.schema.sw.document.DocumentResponse;
import com.lexmark.properties.schema.sw.document.DocumentProperties.DocumentInfo;

public class DocumentumServiceUtil {
	//private static Logger logger = Logger.getLogger(DocumentumServiceUtil.class);
	private static Logger logger = LogManager.getLogger(DocumentumServiceUtil.class);
	public static  DocumentResponse parseResponse(String xmlContent) {
		try {
			ByteArrayInputStream xmlContentBytes = new ByteArrayInputStream (xmlContent.getBytes());
			JAXBContext context = JAXBContext.newInstance(DocumentResponse.class);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			unmarshaller.setSchema(null);
			DocumentResponse response = (DocumentResponse)unmarshaller.unmarshal(xmlContentBytes);
			return response;
		} catch(Exception ex) {
			return null;
		}
	}
	
	public static  String getDocumentPropertiesString(DocumentInfo oInfo) {
		StringWriter write = new StringWriter();
		try {
			JAXBContext jContext = JAXBContext
					.newInstance(DocumentProperties.class);

			Marshaller marshaller = jContext.createMarshaller();
            DocumentProperties oProperties= new DocumentProperties();
            oProperties.setDocumentInfo(oInfo);
            marshaller.marshal(oProperties, write);
			 
		} catch (JAXBException e) {
			throw new RuntimeException(e);
		}
		String ret = write.toString(); 
		logger.debug("document properties string-" + ret);
		return ret;
	}
}
