package com.lexmark.service.impl.real;

import org.junit.Test;

public class UploadArchiveAttachmentsTest extends AttachmentServiceStatelessBase {

	@Test
	public void testUploadArchiveAttachments() throws Exception {
		((AmindAttachmentService) service).uploadArchiveAttachments();
	}

}
