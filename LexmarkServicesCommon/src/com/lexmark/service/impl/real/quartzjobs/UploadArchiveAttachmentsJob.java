package com.lexmark.service.impl.real.quartzjobs;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.logging.log4j.LogManager;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.apache.logging.log4j.Logger;

import com.lexmark.service.impl.real.AmindAttachmentService;

public class UploadArchiveAttachmentsJob extends QuartzJobBean {

	//protected static final Log logger = LogFactory.getLog(UploadArchiveAttachmentsJob.class);
	protected static final Logger logger = LogManager.getLogger(UploadArchiveAttachmentsJob.class);

	private AmindAttachmentService attachmentService;

	public void setAttachmentService(AmindAttachmentService attachmentService) {
		this.attachmentService = attachmentService;
	}

	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		logger.debug("[IN] executeInternal");
		if (attachmentService == null) {
			logger.debug("attachmentService has not set!");
			throw new JobExecutionException("An error occured when try to execute uploadArchiveAttachments(): attachmentService has not set");
		}
		try {
			attachmentService.uploadArchiveAttachments();
			logger.debug("attachments have uploaded! the job has finished");
		} catch (Exception e) {
			logger.error("an error occured when try to execute uploatArchiveAttachments()", e);
			throw new JobExecutionException("An error occured when try to execute uploadArchiveAttachments()", e);
		} finally {
			logger.debug("[OUT] executeInternal");
		}
	}
}
