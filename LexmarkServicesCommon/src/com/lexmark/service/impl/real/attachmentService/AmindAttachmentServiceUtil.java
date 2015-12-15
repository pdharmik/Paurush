package com.lexmark.service.impl.real.attachmentService;

import static java.io.File.separatorChar;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.amind.data.source.ISiebelDataSource;
import com.lexmark.domain.Attachment;
import com.lexmark.service.impl.real.util.AttachmentProperties;
import com.siebel.data.SiebelBusComp;
import com.siebel.data.SiebelBusObject;
import com.siebel.data.SiebelException;

public class AmindAttachmentServiceUtil {
	
	private static final Logger logger = LogManager.getLogger(AmindAttachmentServiceUtil.class);
	
	private SiebelBusObject siebelObject;
	
	public static void checkIdentifier(String identifier) {
		if (identifier == null) {
			throw new IllegalArgumentException("identifier is null");
		} else if (identifier.isEmpty()) {
			throw new IllegalArgumentException("identifier is Empty");
		}
	}
	
	public static void checkAttachments(List<Attachment> attachments) {
		if (attachments == null) {
			throw new IllegalArgumentException("Attachment List is null");
		} else if (attachments.isEmpty()) {
			throw new IllegalArgumentException("Attachment List is empty");
		}
		for (Attachment attachment : attachments) {
			String name = attachment.getAttachmentName();
			logger.debug("checkAttachments = " + name);
			if (name == null) {
				throw new IllegalArgumentException("File Name is null");
			} else if (name.isEmpty()) {
				throw new IllegalArgumentException("File Name is empty");
			}
		}
	}
	
	public static void checkUserFileName(String userFileName) {
		logger.debug("checkUserFileName = " + userFileName);
		if (userFileName == null) {
			throw new IllegalArgumentException("userFileName is null");
		} else if (userFileName.isEmpty()) {
			throw new IllegalArgumentException("userFileName is empty");
		}
	}

	public SiebelBusComp setupBC(ISiebelDataSource dataSource, AttachmentProperties properties)
			throws SiebelException {

		String busObjectName = properties.getBusObjectName();
		String busCompName = properties.getBusCompName();

		SiebelBusObject busObject = dataSource.getBusObject(busObjectName);
		
		if (busObject == null) {
			throw new IllegalArgumentException("Failed to find business object:" + busObjectName);
		}
		
		SiebelBusComp busComp = busObject.getBusComp(busCompName);

		if (busComp == null) {
			throw new IllegalArgumentException("Failed to find business component:" + busCompName);
		}

		busComp.setViewMode(3);
		busComp.clearToQuery();
		setSiebelBusObject(busObject);
		return busComp;
	}

	public static String composeFilePath(String path, String fileName) {
		return composeFilePath(path, fileName, separatorChar);
	}
	
	public static String composeFilePath(String path, String fileName, char separator) {
		StringBuilder filePathBuilder = new StringBuilder();
		filePathBuilder.append(path);
		filePathBuilder.append(fileName);
		return filePathBuilder.toString();
	}

	public void setSiebelBusObject(SiebelBusObject busObject){
		siebelObject = busObject;
	}
	
	public SiebelBusObject getSiebelBusObject(){
		return siebelObject;
	}

}
