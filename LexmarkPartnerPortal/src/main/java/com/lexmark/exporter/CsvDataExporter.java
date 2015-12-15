package com.lexmark.exporter;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.portlet.ResourceResponse;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import com.lexmark.constants.LexmarkPPConstants;
import com.lexmark.domain.DownloadClaim;
import com.lexmark.domain.DownloadClaimPart;
import com.lexmark.domain.DownloadRequest;
import com.lexmark.domain.DownloadRequestPart;
import com.lexmark.util.BusinessObjectUtil;
import com.lexmark.util.DownloadFileLocalizationUtil;
import com.lexmark.util.ServiceStatusUtil;
import com.lexmark.util.StringUtil;

public class CsvDataExporter implements DataExporter {

	private static final String SUFFIX_CSV = ".csv";
	private Locale locale = null;
	private static Logger logger = LogManager.getLogger(CsvDataExporter.class);
	@Override
	public void setLocale(Locale locale) {
		this.locale = locale;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void export(ResourceResponse response, String dataType, String[] columnPatterns, List dataList)
			throws IOException {
		if (!FILE_NAME_RESOURCE_LABELS_MAP.containsKey(dataType)) {
			throw new IllegalArgumentException("The data type[" + dataType + "] is not supported of CsvDataExporter");
		}

		String resourceLabelName = FILE_NAME_RESOURCE_LABELS_MAP.get(dataType);
		String fileName = DownloadFileLocalizationUtil.getPropertyLocaleValue(resourceLabelName, locale) + SUFFIX_CSV;
		response.setProperty("Content-disposition", "attachment; filename=\"" + fileName+"\"");

		response.setContentType("text/csv;charset=UTF-8");
		response.setCharacterEncoding("utf-8");
		OutputStream outputStream = response.getPortletOutputStream();
		outputStream.write(0xEF); // 1st byte of BOM
		outputStream.write(0xBB);
		outputStream.write(0xBF); // last byte of BOM
		// now get a PrintWriter to stream the chars.
		PrintWriter out = new PrintWriter(new OutputStreamWriter(outputStream, "UTF-8"));

		final String csvHeaderLabel = HEADER_RESOURCE_LABELS_MAP.get(dataType);
		final String csvHeader = DownloadFileLocalizationUtil.getPropertyNormal("com.lexmark.resources.messages",csvHeaderLabel, locale);
		StringBuilder cvsContent = new StringBuilder();
		cvsContent.append(csvHeader);
		cvsContent.append(LexmarkPPConstants.NEWLINE);
		int len=columnPatterns.length;
		for (Object object : dataList) {
			
			for (int i = 0; i < len; i++) {
				String columnValue = BusinessObjectUtil.formatColumn(object, columnPatterns[i], locale);
				columnValue = columnValue == null ? "" : columnValue;
				cvsContent.append(processBeforeFilling(columnValue));
				boolean isLast = i == columnPatterns.length - 1;
				cvsContent.append(isLast ? '\n' : ',');
			}
		}
		out.write(cvsContent.toString());
		out.flush();
		out.close();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void exportOrder(ResourceResponse response, String dataType, String[] columnPatterns, List dataList)
			throws IOException {
		if (!FILE_NAME_RESOURCE_LABELS_MAP.containsKey(dataType)) {
			throw new IllegalArgumentException("The data type[" + dataType + "] is not supported of CsvDataExporter");
		}

		String resourceLabelName = FILE_NAME_RESOURCE_LABELS_MAP.get(dataType);
		String fileName = DownloadFileLocalizationUtil.getPropertyLocaleValue(resourceLabelName, locale) + SUFFIX_CSV;
		response.setProperty("Content-disposition", "attachment; filename=\""
				+ fileName+"\"");
		//Character encoding attached for Defect #7854
		response.setContentType("text/csv;charset=UTF-8");
		response.setCharacterEncoding("utf-8");
		OutputStream outputStream = response.getPortletOutputStream();
		outputStream.write(0xEF); // 1st byte of BOM
		outputStream.write(0xBB);
		outputStream.write(0xBF); // last byte of BOM
		// now get a PrintWriter to stream the chars.
		PrintWriter out = new PrintWriter(new OutputStreamWriter(outputStream, "UTF-8"));

		final String csvHeaderLabel = HEADER_RESOURCE_LABELS_MAP.get(dataType);
		final String csvHeader = DownloadFileLocalizationUtil.getPropertyNormal("com.lexmark.resources.messages",csvHeaderLabel, locale);
		StringBuilder cvsContent = new StringBuilder();
		cvsContent.append(csvHeader);
		cvsContent.append("\n");
		for (Object object : dataList) {
			for (int i = 0; i < columnPatterns.length; i++) {
				String columnValue = BusinessObjectUtil.formatColumn(object, columnPatterns[i], locale);
				columnValue = columnValue == null ? "" : columnValue;
				cvsContent.append(processBeforeFillingForInvoice(columnValue));
				boolean isLast = i == columnPatterns.length - 1;
				cvsContent.append(isLast ? '\n' : ',');
			}
		}
		out.write(cvsContent.toString());
		out.flush();
		out.close();
	}

	@SuppressWarnings("unchecked")
	@Override
	public void exportRequestClaims(ResourceResponse response, String dataType, String[] columnPatterns, List dataList)
			throws IOException {

		if (!FILE_NAME_RESOURCE_LABELS_MAP.containsKey(dataType)) {
			throw new IllegalArgumentException("The data type[" + dataType + "] is not supported of CsvDataExporter");
		}

		logger.debug("[START] downloadClaimsRequestsView service dataType = " + dataType);

		String resourceLabelName = FILE_NAME_RESOURCE_LABELS_MAP.get(dataType);
		String fileName = DownloadFileLocalizationUtil.getPropertyLocaleValue(resourceLabelName, locale) + SUFFIX_CSV;
		response.setProperty("Content-disposition", "attachment; filename=" + fileName);

		response.setContentType("text/csv;charset=UTF-8");
		response.setCharacterEncoding("utf-8");
		OutputStream outputStream = response.getPortletOutputStream();
		outputStream.write(0xEF); // 1st byte of BOM
		outputStream.write(0xBB);
		outputStream.write(0xBF); // last byte of BOM

		// now get a PrintWriter to stream the chars.
		PrintWriter out = new PrintWriter(new OutputStreamWriter(outputStream, "UTF-8"));

		final String csvHeaderLabel = HEADER_RESOURCE_LABELS_MAP.get(dataType);
		final String csvHeader = DownloadFileLocalizationUtil.getPropertyNormal("com.lexmark.resources.messages",csvHeaderLabel, locale);
		StringBuffer cvsContent = new StringBuffer();

		final String strCsvHeaderTop = DownloadFileLocalizationUtil.getPropertyNormal("com.lexmark.resources.messages",dataType, locale);
		logger.debug("strCsvHeaderTop value is  "+strCsvHeaderTop.toString());
		
		logger.debug("csvHeader value is  "+csvHeader.toString());
		
		
    	cvsContent.append(strCsvHeaderTop);
		cvsContent.append("\n");
		cvsContent.append(csvHeader);
		cvsContent.append("\n");
		// If the data list is null , Write the CVS with no data
		if(dataList!=null){
		// Download Claims
			if(dataType.equalsIgnoreCase("downloadClaim")){
			logger.debug("dataList.size() for Claims is  "+dataList.size());
	    	for (int k = 0; k < dataList.size(); k++) {			
			
			DownloadClaim downloadClaim =(DownloadClaim)dataList.get(k);
			String strDebriefStatus=downloadClaim.getDebriefStatus();
			String strActivitySubStatus=downloadClaim.getActivitySubStatus();
			String strSRNUM=downloadClaim.getSrNum();
			
			boolean appendRowsToCSVUpdate=ServiceStatusUtil.isRequestAbleToBeUpdate(downloadClaim.getActivitySubStatus(),downloadClaim.getDebriefStatus(),"Claim Request");
			boolean appendRowsToCSVDebrief=ServiceStatusUtil.isRequestAbleToBeDebrief(downloadClaim.getActivitySubStatus(),
					downloadClaim.getDebriefStatus());
			String strActivityId=downloadClaim.getActivityId();
			logger.debug("Claims strSRNUM :: "+strSRNUM+" ,strActivityId of Claim :: "+strActivityId+" , strDebriefStatus of claim is  :: "+strDebriefStatus+" , strActivitySubStatus to Claims is :: "+strActivitySubStatus);
			logger.debug(" Claims appendRowsToCSVUpdate :: "+appendRowsToCSVUpdate+" , appendRowsToCSVDebrief "+appendRowsToCSVDebrief);
			
			// Append Rows to CSV file if SubStatus validation is true
			//if(appendRowsToCSV){
			if(appendRowsToCSVUpdate ||appendRowsToCSVDebrief ){
			for (int i = 0; i < columnPatterns.length; i++) {
				String columnValue = BusinessObjectUtil.formatColumn(downloadClaim, columnPatterns[i], locale);
				columnValue = columnValue == null ? "" : columnValue;
				columnValue=columnValue.replaceAll("\\r\\n|\\r|\\n", " ");
				columnValue=columnValue.replaceAll("," , " ");
				logger.debug("ColumnValue Claims is ::  "+columnValue);
				cvsContent.append(columnValue);
				boolean isLast1 = false;					
				cvsContent.append(isLast1 ? '\n' : ',');
			}
			
			 // This is the logic to write the Parts details from the DownloadClaimPartList object
    		// Currently these fields are blank and adding the blank fields
			 
			String[] columnPatterns_1 = LexmarkPPConstants.PATTERNS_DONWLOAD_CLAIMS_EXPORT_1; 
			DownloadClaimPart downloadClaimPart = new DownloadClaimPart();
			for (int a = 0; a <5; a++) {
				for (int p = 0; p < columnPatterns_1.length; p++) {
						String columnValue_1 = BusinessObjectUtil.formatColumn(downloadClaimPart, columnPatterns_1[p], locale);	
						columnValue_1 = columnValue_1 == null ? "" : columnValue_1;
						//logger.debug("columnValue_1 is ::  "+columnValue_1);
						// Writing Blank values to the CSV File
						cvsContent.append("");
						boolean isLast2 = false;					
						cvsContent.append(isLast2 ? '\n' : ',');
					}
				
			}
			
			
			boolean isLast3 =true;					
			cvsContent.append(isLast3 ? '\n' : ',');
			
			}
			

		}
 }
			// Down load Requests
 else{
		logger.info("dataList.size() for request is  "+dataList.size());

		for (int k = 0; k < dataList.size(); k++) {
			
			DownloadRequest downloadRequest =(DownloadRequest)dataList.get(k);
			
			List downloadRequestPartList=downloadRequest.getDownloadRequestPart();
			 int noOfRows=downloadRequestPartList.size();
			 
			 logger.debug("downloadRequestPartList list size () is  :: "+downloadRequestPartList.size());
			 
			
				String strDebriefStatus=downloadRequest.getDebriefStatus();
				String strActivitySubStatus=downloadRequest.getActivitySubStatus();
				String strSRNUM=downloadRequest.getSrNum();
				boolean appendRowsToCSVUpdate=ServiceStatusUtil.isRequestAbleToBeUpdate(downloadRequest.getActivitySubStatus(),downloadRequest.getDebriefStatus(),"Service Request");
				boolean appendRowsToCSVDebrief=ServiceStatusUtil.isRequestAbleToBeDebrief(downloadRequest.getActivitySubStatus(),
						downloadRequest.getDebriefStatus());
				
				String strActivityId=downloadRequest.getActivityId();
				logger.debug("Requests strSRNUM :: "+strSRNUM+" ,strActivityId of Request :: "+strActivityId+" , strDebriefStatus of Request is  :: "+strDebriefStatus+" , strActivitySubStatus of Request is :: "+strActivitySubStatus);				
				logger.debug(" Requests appendRowsToCSVUpdate :: "+appendRowsToCSVUpdate+" , appendRowsToCSVDebrief "+appendRowsToCSVDebrief);
				// Append Rows to CSV file if SubStatus validation is true
				// if(appendRowsToCSV){
				if(appendRowsToCSVUpdate || appendRowsToCSVDebrief){
				 
			 String[] columnPatterns_2 = LexmarkPPConstants.PATTERNS_DONWLOAD_REQUEST_EXPORT_2;
			   if(noOfRows==0){
								for (int s = 0; s < columnPatterns.length; s++) {
										String columnValue = BusinessObjectUtil.formatColumn(downloadRequest, columnPatterns[s], locale);
										columnValue = columnValue == null ? "" : columnValue;
										columnValue=columnValue.replaceAll("\\r\\n|\\r|\\n", " ");
										columnValue=columnValue.replaceAll("," , " ");
										logger.debug("columnValue is request ::  "+columnValue);
										cvsContent.append(columnValue);
										boolean isLast1= false;
										cvsContent.append(isLast1 ? '\n' : ',');
								}
								// Writing Blank Spaces in CSV
								DownloadRequestPart downloadRequestPart1= new DownloadRequestPart();
						        for (int p1 = 0; p1 < columnPatterns_2.length; p1++) {
									String columnValue_1 = BusinessObjectUtil.formatColumn(downloadRequestPart1, columnPatterns_2[p1], locale);	
									logger.debug("columnValue_1 is request ::  "+columnValue_1);
									//columnValue_1 = columnValue_1 == null ? "" : columnValue_1;
									// Writing Blank values to the CSV File
									cvsContent.append("");
									boolean isLast2 = p1 == columnPatterns_2.length - 1;
									cvsContent.append(isLast2 ? '\n' : ',');
								}

							}
				else{
							 for(int y = 0; y < noOfRows; y++) {
						
										 for (int i = 0; i < columnPatterns.length; i++) {
										String columnValue = BusinessObjectUtil.formatColumn(downloadRequest, columnPatterns[i], locale);
										columnValue = columnValue == null ? "" : columnValue;
										columnValue=columnValue.replaceAll("\\r\\n|\\r|\\n", " ");
										columnValue=columnValue.replaceAll("," , " ");
										logger.debug("columnValue of request ::  "+columnValue);
										cvsContent.append(columnValue);
										boolean isLast1 = false;					
										cvsContent.append(isLast1 ? '\n' : ',');
									}
									DownloadRequestPart downloadRequestPart=(DownloadRequestPart)downloadRequestPartList.get(y);
							        for (int p = 0; p < columnPatterns_2.length; p++) {
												String columnValue_1 = BusinessObjectUtil.formatColumn(downloadRequestPart, columnPatterns_2[p], locale);	
												columnValue_1 = columnValue_1 == null ? "" : columnValue_1;
												columnValue_1=columnValue_1.replaceAll("\\r\\n|\\r|\\n", " ");
												columnValue_1=columnValue_1.replaceAll("," , " ");
												logger.debug("columnValue_1 Request is ::  "+columnValue_1);
												cvsContent.append(columnValue_1);
												boolean isLast2 = p == columnPatterns_2.length - 1;
												cvsContent.append(isLast2 ? '\n' : ',');
											}
										
									}
					}
			   
			 }
			 }
		
}
		out.write(cvsContent.toString());
		out.flush();
		out.close();
		}
		else{
			out.write(cvsContent.toString());
			out.flush();
			out.close();
			logger.debug(" No data in CSV as downloadClaimsRequestsView List is Null !!! ");
		}
		
		logger.debug("[END] CSV downloadClaimsRequestsView service");
	}


	/**
	 * Process before filling, append one more " before ", and enclose with "".
	 * 
	 * @param value
	 * @return processed string
	 */
	private static String processBeforeFilling(String value) {
		return StringUtil.encloseWithDoubleQuote(StringUtil.appendDoubleQuote(value));
	}
	
	//Added for CI-7 Defect 7854
	private static String processBeforeFillingForInvoice(String value) {
		logger.debug("Processed Column Value------->>"+StringUtil.encloseWithDoubleQuoteWithSpace(StringUtil.appendDoubleQuote(value)));
		return StringUtil.encloseWithDoubleQuoteWithSpace(StringUtil.appendDoubleQuote(value));
	}
	
	
	/*
	 * Added for Mass upload Service Orders
	 * MPS 2.1
	 * */
	public void exportMassUploadTemplate(ResourceResponse response, String fileName_And_CSVHeader_Key, String[] columnPatterns, List<?> dataList,String[] listExportColumnPatterns){
		
		if (!FILE_NAME_RESOURCE_LABELS_MAP.containsKey(fileName_And_CSVHeader_Key)) {
			throw new IllegalArgumentException("The data type[" + fileName_And_CSVHeader_Key + "] is not supported of CsvDataExporter");
		}

		String resourceLabelName = FILE_NAME_RESOURCE_LABELS_MAP.get(fileName_And_CSVHeader_Key);
		logger.debug("resourceLabelName"+resourceLabelName);
		String fileName = DownloadFileLocalizationUtil.getPropertyLocaleValue(resourceLabelName, locale) + SUFFIX_CSV;
		logger.debug("file name"+fileName);
		response.setProperty("Content-disposition", "attachment; filename=\"" + fileName+"\"");

		response.setContentType("text/csvcharset=UTF-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out=null;
		StringBuilder cvsContent = new StringBuilder();
		try{
		OutputStream outputStream = response.getPortletOutputStream();
		outputStream.write(0xEF); // 1st byte of BOM
		outputStream.write(0xBB);
		outputStream.write(0xBF); // last byte of BOM
		// now get a PrintWriter to stream the chars.
		out = new PrintWriter(new OutputStreamWriter(outputStream, "UTF-8"));

		final String csvHeaderLabel = HEADER_RESOURCE_LABELS_MAP.get(fileName_And_CSVHeader_Key);
		logger.debug("csvHeaderLabel"+csvHeaderLabel);
		final String csvHeader = DownloadFileLocalizationUtil.getPropertyNormal("com.lexmark.resources.messages",csvHeaderLabel, locale);
		logger.debug("csvHeader"+csvHeader);
		
		cvsContent.append(csvHeader);
		cvsContent.append("\n");
		
		for (Object object : dataList) {
			StringBuilder withoutListContent=new StringBuilder();
			for(String exportColumn:columnPatterns){
				if(BusinessObjectUtil.readPropertyList(object, exportColumn) instanceof ArrayList<?> ){
					List<?> listValues=(List<?>)BusinessObjectUtil.readPropertyList(object, exportColumn);
					for(Object obj:listValues){
							int colIndex=0;
							cvsContent.append(withoutListContent);
							for(String listExportColumn:listExportColumnPatterns){
								readAndAppendToCSV(cvsContent,obj,listExportColumn);
								boolean isLast = colIndex == listExportColumnPatterns.length - 1;
								cvsContent.append(isLast ? '\n' : ',');		
								colIndex++;
							}
					}
					
					
				}else{
				    readAndAppendToCSV(withoutListContent,object,exportColumn);
				    withoutListContent.append(',');
				}
			}
			
		}
		
		out.write(cvsContent.toString());
		
		}catch(Exception exception){
			
			logger.error("Exception occured while writing the csv"+ exception.getMessage());
		}finally{
			
			out.flush();
			out.close();
			
		}
	 }
	
	/*MPS 2.1 Changes for MAss upload
	 * */
	private void readAndAppendToCSV(StringBuilder cvsContent,Object object,String column){
		String columnValue=null;
		if(column.indexOf(":ignore")==-1)
			columnValue= BusinessObjectUtil.formatColumn(object,column , locale);
		columnValue = columnValue == null ? "" : columnValue;
		cvsContent.append(processBeforeFilling(columnValue));
	}
}
