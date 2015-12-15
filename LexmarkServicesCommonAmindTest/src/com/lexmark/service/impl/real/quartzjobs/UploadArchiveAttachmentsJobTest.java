package com.lexmark.service.impl.real.quartzjobs;

import org.junit.Test;
import com.lexmark.service.impl.real.AmindAttachmentService;

public class UploadArchiveAttachmentsJobTest {

	@Test
	public void testExecuteInternalJobExecutionContext() throws Exception {
		UploadArchiveAttachmentsJob job = new UploadArchiveAttachmentsJob();
		job.setAttachmentService(new AmindAttachmentService());
//		job.executeInternal(null);
	}

}
