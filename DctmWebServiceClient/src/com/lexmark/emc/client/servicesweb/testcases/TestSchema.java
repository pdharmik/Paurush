package com.lexmark.emc.client.servicesweb.testcases;

import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import com.lexmark.properties.schema.sw.document.DocumentProperties;
import com.lexmark.properties.schema.sw.document.DocumentProperties.DocumentInfo;
import com.lexmark.properties.schema.sw.document.DocumentProperties.DocumentInfo.Keywords;


public class TestSchema {
	
	public static void main(String[] args) {
		
	}
	
	private static String getInputParameter() {
		StringWriter write = new StringWriter();

		try {
			JAXBContext jContext = JAXBContext
					.newInstance(DocumentProperties.class);

			Marshaller marshaller = jContext.createMarshaller();

			DocumentInfo oInfo= new DocumentInfo();
			
			oInfo.setObjectName("Test.doc");
			
			oInfo.setAContentType("msword");
			
			Keywords keywords= new Keywords();
			
			keywords.getValues().add("keywords-1");
			keywords.getValues().add("keywords-2");
			oInfo.setKeywords( keywords);
			 

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
