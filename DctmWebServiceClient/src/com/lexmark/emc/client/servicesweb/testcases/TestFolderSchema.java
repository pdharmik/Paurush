package com.lexmark.emc.client.servicesweb.testcases;

import java.io.StringWriter;
import java.util.Iterator;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.xml.sax.InputSource;

import com.documentum.fc.client.IDfSysObject;
import com.lexmark.properties.schema.sw.folder.FolderProperties;
import com.lexmark.properties.schema.sw.folder.FolderProperties.FolderInfo;

public class TestFolderSchema {
	
	public static void main(String[] args) {
		
		
		
		
		
		
		 
		FolderProperties properties = null;
		JAXBContext jContext = null;
		Unmarshaller unmarshaller = null;
		IDfSysObject sysObj = null;
		String sysObjId = null;
		FolderInfo folderInfo=null;
		
	
		try {
			jContext = JAXBContext
					.newInstance(FolderProperties.class);
			unmarshaller = jContext.createUnmarshaller();
			
			

			

			properties = (FolderProperties) unmarshaller
			.unmarshal(new InputSource(new java.io.StringReader(
					getInputParameter())));

			
			
		
		 folderInfo= properties.getFolderInfo();
		  
		 
 		
		
		
	
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
	}
	
	
	private static String getInputParameter() {
		StringWriter write = new StringWriter();

		try {
			JAXBContext jContext = JAXBContext
					.newInstance(FolderProperties.class);

			Marshaller marshaller = jContext.createMarshaller();

			FolderInfo folderInfo = new FolderInfo();

			/*
			 * folderInfo.setDestinationPath("/Temp/Example/folder-will-be-create-under-me"
			 * ); folderInfo.setObjectName("new-folder-name");
			 */
			folderInfo.setDestinationPath("/Temp");
			folderInfo.setObjectName("services-web");
			
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
