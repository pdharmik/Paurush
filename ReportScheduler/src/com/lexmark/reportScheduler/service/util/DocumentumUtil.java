package com.lexmark.reportScheduler.service.util;

import java.util.Date;

import com.lexmark.domain.ReportProperties;
import com.lexmark.properties.schema.sw.document.DocumentProperties.DocumentInfo;

public class DocumentumUtil {
	private static final String XLS_CONTENT_TYPE = "xls";
	private static final String XLS_CONTENT_TYPE_USED_IN_DOCUMENTUM = "excel";
	private static final String WORD_CONTENT_TYPE = "doc";
	private static final String WORD_CONTENT_TYPE_USED_IN_DOCUMENTUM = "word";
	
	
	public static DocumentInfo  reportToDocument(ReportProperties reportInfo) {
		DocumentInfo oInfo= new DocumentInfo();
		
		//set mandatory attributes
		oInfo.setFileName(reportInfo.getFileName());
		oInfo.setObjectName(reportInfo.getObjectName());
		String contentType = reportInfo.getAContentType().toLowerCase();
		if(contentType.equals(XLS_CONTENT_TYPE)) {
			contentType = XLS_CONTENT_TYPE_USED_IN_DOCUMENTUM;
		} else if (contentType.equals(WORD_CONTENT_TYPE)) {
			contentType = WORD_CONTENT_TYPE_USED_IN_DOCUMENTUM;
		}
		oInfo.setAContentType(contentType);
		//path to the folder where document will be created and stored
		oInfo.setRFolderPath(reportInfo.getRFolderPath());
		oInfo.setRObjectType(reportInfo.getRObjectType());
		oInfo.setLocale(reportInfo.getLocale());

		// end of mandatory attributes
		
		// set other attributes
		oInfo.setUsernumber(reportInfo.getUsernumber());
		oInfo.setMdmId(reportInfo.getMdmid());
		oInfo.setMdmlevel(reportInfo.getMdmlevel());
		oInfo.setLegalName(reportInfo.getLegalName());
		oInfo.setFileDataLink(reportInfo.getFileDataLink());
		oInfo.setDefinitionId(reportInfo.getDefinitionId());
		oInfo.setJobRunlogId(reportInfo.getRunLogId());
		oInfo.setScheduleFrequency(reportInfo.getScheduleFrequency());
		oInfo.setFileContentDate(new Date());
		
		return oInfo;
	}
}
