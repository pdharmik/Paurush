package com.lexmark.service.impl.real;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import com.lexmark.service.impl.real.util.LogUtil;

public class AmindBatchService extends AmindSiebelCrmService implements Job {
	protected static final Logger logger = LogManager.getLogger(AmindBatchService.class);

	public void execute(JobExecutionContext context) throws JobExecutionException {
		AmindAttachmentService service = new AmindAttachmentService();
		try {
			service.uploadArchiveAttachments();
			logger.debug("attachments have uploaded! the job has finished");
		} catch (Exception e) {
		    LogUtil.logAmindServiceCallException(LogUtil.stackTraceExecutionPoint(), e);
			throw new JobExecutionException("an error occured when try to execute uploatArchiveAttachments()", e);
		}
	}
}