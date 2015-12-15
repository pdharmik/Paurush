package com.lexmark.service.impl.real.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

public class AttachmentPropertiesTest {
	protected static final Log logger = LogFactory.getLog(AttachmentPropertiesTest.class);
	
	@Test
	public void test() throws IllegalArgumentException, IllegalAccessException {
		AttachmentProperties properties = new AttachmentProperties("Service Request");
		
		logger.debug(properties.getBcDescription());
		logger.debug(properties.getBcFileFieldName());
		logger.debug(properties.getBcFileNameExtensionFieldName());
		logger.debug(properties.getBcParentFieldName());
		logger.debug(properties.getBusCompName());
		logger.debug(properties.getBusObjectName());
		logger.debug(properties.getFileDownloadDestination());
		logger.debug(properties.getFileUploadDestination());
		logger.debug(properties.getSiebelFileDestination());

	}

}
