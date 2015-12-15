package com.lexmark.reportScheduler.service.impl;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import com.lexmark.domain.ReportProperties;
import com.lexmark.emc.client.servicesweb.impl.DocumentumWebServiceFacadeImpl;
import com.lexmark.properties.schema.sw.document.DocumentProperties.DocumentInfo;
import com.lexmark.reportScheduler.service.DocumentumService;
import com.lexmark.reportScheduler.service.util.DocumentumUtil;
import com.lexmark.util.report.PropertiesUtil;

public class DocumentumServiceImpl implements DocumentumService {
	private static Logger logger = LogManager.getLogger(DocumentumServiceImpl.class);
	private DocumentumWebServiceFacadeImpl target;
	
	public DocumentumServiceImpl() {
		target = new DocumentumWebServiceFacadeImpl(PropertiesUtil.DOCUMENTUM_SERVICE_END_POINT,
				PropertiesUtil.DOCUMENTUM_REPOSITORYNAME, PropertiesUtil.DOCUMENTUM_USERID,
				PropertiesUtil.DOCUMENTUM_PASSWORD, PropertiesUtil.DOCUMENTUM_APPLICATIONNAME		
		);
	}

	public String authenticate() {
		return target.authenticate();
	}

	public String createDocument(ReportProperties reportProperties,  byte[] content){
		DocumentInfo oInfo = DocumentumUtil.reportToDocument(reportProperties);
		return target.createDocument(oInfo, content);
		
	}
}
