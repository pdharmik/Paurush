package com.lexmark.service.impl.real.util;

import static com.lexmark.service.impl.real.attachmentService.AmindAttachmentServiceUtil.composeFilePath;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

public class AmindAttachmentServiceUtilTest {

	protected static final Log logger = LogFactory.getLog(AmindAttachmentServiceUtilTest.class);

	@Test
	public void testComposeFilePath() {
		AttachmentProperties properties = new AttachmentProperties("Service Request");
		String downloadPath = properties.getFileDownloadDestination();
		String uploadPath = properties.getFileUploadDestination();
		String siebelPath = properties.getSiebelFileDestination();

		String fileName = "fileName";

		logger.debug("download: " + composeFilePath(downloadPath, fileName));
		logger.debug("upload: " + composeFilePath(uploadPath, fileName));
		logger.debug("siebel: " + composeFilePath(siebelPath, fileName, '/'));

		downloadPath = downloadPath.substring(0, downloadPath.length() - 1);
		uploadPath = uploadPath.substring(0, uploadPath.length() - 1);
		siebelPath = siebelPath.substring(0, siebelPath.length() - 1);
		
		logger.debug("download: " + downloadPath);
		logger.debug("upload: " + uploadPath);
		logger.debug("siebel: " + siebelPath);
		
		logger.debug("download: " + composeFilePath(downloadPath, fileName));
		logger.debug("upload: " + composeFilePath(uploadPath, fileName));
		logger.debug("siebel: " + composeFilePath(siebelPath, fileName, '/'));

	}
	

}
