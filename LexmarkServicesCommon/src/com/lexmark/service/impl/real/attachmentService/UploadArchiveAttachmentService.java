package com.lexmark.service.impl.real.attachmentService;

import static java.util.concurrent.Executors.newFixedThreadPool;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.logging.log4j.LogManager;

import com.amind.session.SessionFactory;
import com.lexmark.domain.Attachment;
import com.lexmark.service.api.SiebelCrmServiceException;
import com.lexmark.service.impl.real.quartzjobs.RegexFileFilter;
import com.lexmark.service.impl.real.util.AttachmentProperties;

import org.apache.logging.log4j.Logger;


public class UploadArchiveAttachmentService {

	private SessionFactory statelessSessionFactory;
	//private static final Log logger = LogFactory.getLog(UploadArchiveAttachmentService.class);
	private static final Logger logger = LogManager.getLogger(UploadArchiveAttachmentService.class);
	private final String fileUploadDestination;
	private final AttachmentProperties properties;
	private static final int THREADS_COUNT = 2;
	private static final String spaceDelimeter = "~";
	private static final String visibilityDelimeter = "#";
	private static final String parentIdDelimeter = "@";
	private static final String extensionDelimeter = ".";
	public UploadArchiveAttachmentService(String fileUploadDestination, AttachmentProperties properties, SessionFactory statelessSessionFactory)
	{
		this.fileUploadDestination = fileUploadDestination;
		this.properties = properties;
		this.statelessSessionFactory = statelessSessionFactory;
	}
	
	public void uploadAttachment(){
		ExecutorService executor = newFixedThreadPool(THREADS_COUNT);
		int fileCount = 0;
		File[] fileList = new File(fileUploadDestination).listFiles(new RegexFileFilter());
		
		if (fileList == null) {
			logger.error("wrong upload file destination: '" + fileUploadDestination + "'");
			throw new SiebelCrmServiceException("wrong upload file destination: '" + fileUploadDestination + "'");
		}
		for (File file: fileList) {
			String fileName = file.getName();
			Attachment attachment = setFilePropertiesToAttachment(fileName);
			UploadRunnable worker = new UploadRunnable(attachment.getIdentifier(), attachment, properties);
			worker.setSessionFactory(this.statelessSessionFactory);
			executor.execute(worker);
			fileCount ++;
		}
			
		// Wait until all threads are finish
		int timeOut = fileCount * 60;
		try {
			if (executor.awaitTermination(timeOut, TimeUnit.SECONDS)) {
				executor.shutdown();
			}
		} catch (Exception e) {
			logger.error("upload archive attachment has failed", e);
			throw new SiebelCrmServiceException("upload archive attachment has failed", e);
		} finally {
			executor.shutdown();
		}
	}
	
	private Attachment setFilePropertiesToAttachment(String fileName)
	{
		int index = fileName.indexOf(parentIdDelimeter);
		int visibilityIndex = fileName.lastIndexOf(visibilityDelimeter) + 1;
		int extensionIndex = fileName.lastIndexOf(extensionDelimeter);
		logger.debug("File Name:" +  fileName);
		String visibility = "";
		String fileWOVisibility = "";
		String originalFileName = "";
		
		if(visibilityIndex > 0) {
			visibility = fileName.substring(visibilityIndex,extensionIndex);
			logger.debug("Visibility:" +  visibility);
			fileWOVisibility = fileName.replaceAll(visibilityDelimeter+visibility, "");
			RenameAttachmentUtil.exceuteRenameFile(fileUploadDestination, fileName, fileWOVisibility);
			if(fileWOVisibility.contains(spaceDelimeter)){
				originalFileName = fileWOVisibility.replaceAll(spaceDelimeter, " ");
			}else{
				originalFileName = fileWOVisibility;
			}
			
		}else {
			originalFileName = fileName;
		}
		
		String parentId = fileName.substring(0,index);
		
		Attachment attachment = new Attachment();
		attachment.setAttachmentName(fileWOVisibility);
		attachment.setVisibility(visibility);
		attachment.setDisplayAttachmentName(originalFileName);
		attachment.setIdentifier(parentId);
		return attachment;
	}

	
	
}