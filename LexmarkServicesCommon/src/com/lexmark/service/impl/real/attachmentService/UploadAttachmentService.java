package com.lexmark.service.impl.real.attachmentService;

import static com.lexmark.service.impl.real.attachmentService.AmindAttachmentServiceUtil.checkAttachments;
import static com.lexmark.service.impl.real.attachmentService.AmindAttachmentServiceUtil.checkIdentifier;
import static com.lexmark.util.LangUtil.isNotEmpty;
import static java.util.concurrent.Executors.callable;
import static java.util.concurrent.Executors.newFixedThreadPool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.logging.log4j.LogManager;

import com.amind.session.SessionFactory;
import com.lexmark.contract.AttachmentContract;
import com.lexmark.domain.Attachment;
import com.lexmark.service.api.SiebelCrmServiceException;
import com.lexmark.service.impl.real.util.AttachmentProperties;
import com.lexmark.util.LangUtil;
import org.apache.logging.log4j.Logger;

public class UploadAttachmentService {
	//private static final Log logger = LogFactory.getLog(UploadAttachmentService.class);
	private static final Logger logger = LogManager.getLogger(UploadAttachmentService.class);
	
	private final AttachmentProperties properties;
	private final String identifier; 
	private final List<Attachment> attachments;
	private final int size;
	private final ExecutorService executor; 
	
	public UploadAttachmentService(AttachmentContract contract) {
		this.properties = new AttachmentProperties(contract.getRequestType());
		this.identifier = contract.getIdentifier();
		this.attachments = contract.getAttachments(); 
		this.size = attachments.size();
		this.executor = newFixedThreadPool(size);
	}
	
	public void checkRequiredFields() {
		checkIdentifier(identifier);
		checkAttachments(attachments);
	}
	public void renameAttachmentsWithVisibility() {
	String fileDestination = properties.getFileUploadDestination();
		try{
			if (isNotEmpty(fileDestination)) {
				for (Attachment attachment: attachments) {
					StringBuilder fileName = new StringBuilder(attachment.getDisplayAttachmentName());
					if(LangUtil.isNotEmpty(attachment.getVisibility()))
					{
						int extensionIndex = fileName.lastIndexOf(".");
						fileName.insert(extensionIndex, "#" + attachment.getVisibility());
						StringBuilder renameFileName = new StringBuilder(identifier + "@" + fileName);
						RenameAttachmentUtil.exceuteRenameFile(fileDestination, attachment.getDisplayAttachmentName(), renameFileName.toString());
					}

				}
			}
		}catch(Exception e){
			logger.error("upload attachment has failed after calling rename attachment with Visibility", e);
		}

	}
	public void renameAttachments() {
		String fileDestination = properties.getFileUploadDestination();
		
		if (isNotEmpty(fileDestination)) {
			for (Attachment attachment: attachments) {
				String replaceFileName = replaceSpacesAndBrackets(attachment.getAttachmentName(), "~");
				//String renameFileName = identifier + "@" + replaceFileName;
				attachment.setDisplayAttachmentName(attachment.getAttachmentName());
				attachment.setAttachmentName(replaceFileName);
			}
		}
	}

	static String replaceSpacesAndBrackets(String s, String spaceReplacement) {
		String withoutSpace = s.replaceAll("\\s", spaceReplacement);

		String wbr = withoutSpace.replaceAll("[(|#|%|&|<|>|*|?|$|'|`|^|:|;|+|=|/|\\|||]", "~");
		String quote = Pattern.quote("0\\");
		wbr = wbr.replaceAll(quote, "~");
		String replacedBracket = wbr.replaceAll("[)]", "");

		return replacedBracket;
    }
	
	public void executeService(SessionFactory sessionFactory) throws InterruptedException, Exception {
		List<Callable<Object>> callables = new ArrayList<Callable<Object>>(size);
		try{
			for (Attachment attachment: attachments) {
				UploadRunnable worker = new UploadRunnable(identifier,attachment,properties);
				worker.setSessionFactory(sessionFactory);
				callables.add(callable(worker));
			}
			List<Future<Object>> futures = executor.invokeAll(callables);
			try{
				for(Future<Object> future : futures){
					future.get();
				}

			}catch (ExecutionException ex) {
				logger.error("Message:" + ex.getMessage());
				if(ex.getCause() != null && ex.getCause().getMessage() != null){
					logger.error("Cause :" + ex.getCause().getMessage());
				}
				throw new SiebelCrmServiceException(ex);
			}

		}catch(Exception e){
			logger.error(e);
			throw new SiebelCrmServiceException(e);
		}
	}
	
	public void shutdownService() {
		if (executor != null) {
			executor.shutdown();
		}
	}
	
}
