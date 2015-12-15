package com.lexmark.reportScheduler.service.impl.mock;

import java.util.Date;

import org.apache.log4j.Logger;

import com.lexmark.domain.ReportProperties;
import com.lexmark.emc.client.servicesweb.util.DocumentumServiceUtil;
import com.lexmark.properties.schema.sw.document.DocumentProperties.DocumentInfo;
import com.lexmark.reportScheduler.service.DocumentumService;
import com.lexmark.reportScheduler.service.util.DocumentumUtil;

public class DocumentumServiceMock implements DocumentumService {
	private static Logger logger = Logger.getLogger(DocumentumServiceMock.class);
	@Override
	public String authenticate() {
		
		return "token";
	}

	@Override
	public String createDocument(ReportProperties reportProperties,
			byte[] content) {
		DocumentInfo oInfo = DocumentumUtil.reportToDocument(reportProperties);
		String ret = DocumentumServiceUtil.getDocumentPropertiesString(oInfo);
		logger.debug("Report Info " + ret);
		return "" + new Date().getTime();
	}
}
