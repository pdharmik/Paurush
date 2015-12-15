package com.lexmark.reportScheduler.service.impl;

import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.lexmark.domain.ReportProperties;
import com.lexmark.util.report.PropertiesUtil;

public class DocumentumServiceImplTest {

	private DocumentumServiceImpl target;
	@Before
	public void setup() {
		target = new DocumentumServiceImpl();
	}
	@Test
	public void testAuthenticate() {
		String token = target.authenticate();
		
		
	}

	@Test
	public void testCreateDocument() {
		
		byte[] content = new byte[32];
		for(int i=0; i< 32; i++) {
			content[i] = 'a';
		}
		String ret = target.createDocument(initProperties(), content);
		Assert.assertNotNull(ret);
	}
	
	private ReportProperties initProperties() {
		ReportProperties metaData = new ReportProperties();
		// documentum needed
		metaData.setLocale("en");
		metaData.setAContentType("pdf");
		
		metaData.setObjectName("stock-" + new Date().getTime() + ".pdf");
		metaData.setRFolderPath(PropertiesUtil.DOCUMENTUM_REPORTFOLDER);
        // metaData.setRObjectType  default in the ReportProperties class definition
		
		// Report mandetory
		metaData.setRunLogId("1100000");
		metaData.setDefinitionId("1000000");
		metaData.setFileDataLink("1000000");
		metaData.setLegalName("LEGAL_NAME");
		metaData.setMdmid("123456789");
		metaData.setMdmlevel("Global");
		metaData.setScheduleFrequency("D");
		metaData.setUsernumber(null);
		return metaData;
	}

}
