package com.lexmark.service.impl.real.attachmentService;

import static com.lexmark.service.impl.real.attachmentService.AmindAttachmentServiceUtil.*;
import static java.io.File.separatorChar;
import static org.apache.commons.lang.StringUtils.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.logging.log4j.LogManager;

import com.amind.session.Session;
import com.lexmark.contract.AttachmentContract;
import com.lexmark.service.impl.real.util.AttachmentProperties;
import com.siebel.data.SiebelBusComp;
import com.siebel.data.SiebelBusObject;
import com.siebel.data.SiebelException;
import org.apache.logging.log4j.Logger;


public class DownloadAttachmentService {
	//private static final Log logger = LogFactory.getLog(DownloadAttachmentService.class);
	private static final Logger logger = LogManager.getLogger(DownloadAttachmentService.class);
	
	private final String identifier;
	private final String userFileName;
	private final AttachmentProperties properties;
	private String searchExpression;
	private Session session;
	private SiebelBusComp busComp;
	private AmindAttachmentServiceUtil attachmentServiceUtil;
	private File destPath;
	
	public DownloadAttachmentService(AttachmentContract contract) {
		logger.debug("In DownloadAttachmentService");
		if (contract == null) {
			throw new IllegalStateException("contract can not be null");
		}
		identifier = contract.getIdentifier();
		logger.debug("In identifier = " + identifier);
		userFileName = contract.getUserFileName();
		logger.debug("In userFileName = " + userFileName);
		properties = new AttachmentProperties(contract.getRequestType());
		logger.debug("In properties");
		attachmentServiceUtil = new AmindAttachmentServiceUtil();
		logger.debug("In attachmentServiceUtil");
	}
	
	public void checkRequiredFields() {
		checkIdentifier(identifier);
		checkUserFileName(userFileName);
	}
	
	public void buildSearchExpression() {
		searchExpression = buildSingleAttachmentSearchExpression();
	}
	
	public void setUpBC() throws SiebelException {
		busComp = attachmentServiceUtil.setupBC(session.getDataSource(),properties);
		busComp.setSearchExpr(searchExpression);
	}
	
	public InputStream getInputStream() throws SiebelException, IOException {
		boolean bcresult = busComp.executeQuery2(true, true);
		FileInputStream fileInput = null;
		
		if (bcresult) {
			bcresult = busComp.firstRecord();
		}
		if (bcresult) {
			String[] parameters = { properties.getBcFileFieldName() };
			String resultString = busComp.invokeMethod("GetFile", parameters);
			logger.debug("retrieveAttachment()-invokeMethod returned:" + resultString);
			
			if (resultString != null && resultString.startsWith("Success")) {
				
				String[] resultParts = resultString.split("\\\\");
				if (resultParts.length > 1) {
					String localFileName = splitLastItem(resultParts[resultParts.length - 1]);
					try {
						String fileDestination = properties.getFileDownloadDestination();
						logger.debug("fileDestination: " + fileDestination);
						logger.debug("localFileName: " + localFileName);
						
						String retval = retrieveSiebelTempFile(identifier, localFileName, fileDestination);
						if(!isEmpty(retval)) {
							destPath = new File(composeFilePath(fileDestination, localFileName));
							fileInput = new FileInputStream(destPath);
//							destPath.delete();
						}

					} catch (FileNotFoundException fe) {
						logger.error("Could not load file: " + localFileName + " ", fe);
					}

				}
			}
		}
		return fileInput;
	}
	
	public void releaseBusComp() {
		if (busComp != null) {
			busComp.release();
		}
		SiebelBusObject siebelObject = attachmentServiceUtil.getSiebelBusObject();
		if( siebelObject != null){
			siebelObject.release();
			
		}
	}
	
	private String buildSingleAttachmentSearchExpression() {
		
		Iterator<String> fileNamePartsIterator = getFileNameParts(userFileName).iterator();
		String fileName = fileNamePartsIterator.next();
		String fileExt = "";
		if (fileNamePartsIterator.hasNext()) {
			fileExt = fileNamePartsIterator.next();
		}
		
		StringBuilder builder = new StringBuilder("[");
		builder.append(properties.getBcParentFieldName());
		builder.append("]='");
		builder.append(identifier);
		builder.append("' AND [");
		builder.append(properties.getBcFileFieldName());
		builder.append("]='");
		builder.append(fileName);
		if (isNotEmpty(fileExt)) {
			builder.append("' AND [");
			builder.append(properties.getBcFileNameExtensionFieldName());
			builder.append("]='");
			builder.append(fileExt);
		}
		builder.append("'");

		return builder.toString();
	}
	
	private List<String> getFileNameParts(String fileName) {
		return getFileNameParts(fileName, '.');
	}
	
	private List<String> getFileNameParts(String fileName, char delimiter) {
		int index = fileName.lastIndexOf(delimiter);
		List<String> parts = new ArrayList<String>(2);
		// index = 0 if 'delimiter' is the first symbol
		// index = 1 if there is no 'delimiter' in string 
		if (index < 1) {
			parts.add(fileName);
		} else {
			parts.add(fileName.substring(0, index));
			parts.add(fileName.substring(index + 1));
		}
		return parts;
	}
	
	private String retrieveSiebelTempFile(String parentId, String sourceFileName,
			String fileSource) throws IOException, FileNotFoundException {

		StringBuilder filePath = new StringBuilder();
		// Create temporary file path name - path is local to Siebel, and a
		// typical choice is to put this file on the gateway server.
		filePath.append(fileSource.trim());
		filePath.append(sourceFileName);
		logger.debug("SourceFileName = " + sourceFileName);
		String pathToFile = filePath.toString();
		logger.debug("retrieveSiebelTempFile = " + pathToFile);
		return pathToFile;
	}
	
	private String splitLastItem(String input) {
		String filePath = clearTail(input);
		int index = filePath.lastIndexOf(separatorChar);
		return filePath.substring(index + 1);
	}
	
	private String clearTail(String filePath) {
		int lastIndex = filePath.length();
		char lastChar;
		do {
			lastIndex--;
			lastChar = filePath.charAt(lastIndex);
		} while (lastChar == separatorChar && lastIndex != 0);
		return filePath.substring(0, lastIndex + 1);
	}

	public Session getSession() {
		if (session == null) {
			throw new IllegalStateException("session has not set!");
		}
		return session;
	}

	public void setSession(Session session) {
		if (session == null) {
			throw new IllegalArgumentException("session can not be null!");
		}
		this.session = session;
	}

	public File getDestPath() {
		return destPath;
	}
	
	public void removeTempFile() {
		if(destPath!=null) {
			try {
				destPath.delete();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
}
