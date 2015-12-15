package com.lexmark.services.exporter;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.portlet.ResourceResponse;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;

import com.lexmark.domain.OrderPart;
import com.lexmark.util.BusinessObjectUtil;
import com.lexmark.util.PropertiesMessageUtil;
 

/**
 * Copyright@ 2013. This document has been created & written by
 * Wipro technologies for Lexmark and this is copyright to Lexmark
 * This controller is used upload data in Excel.
 *
 * File         	: ExcelDataExporter
 * Package     		: com.lexmark.exporter
 * 
 * @author wipro
 * @version 2.1
 */
public class ExcelDataExporter {
	
	/** SUFFIX_EXCEL */
	private static final String SUFFIX_EXCEL = ".xls";
	private Locale locale = null;
	/** Logger */
	private static Logger logger = LogManager.getLogger(ExcelDataExporter.class);
	/** Message bundle name */
	public static final String MESSAGE_BUNDLE_NAME = "com.lexmark.services.resources.messages";

	public void setLocale(Locale locale) {
		this.locale = locale;
	}
	
	/** key:dataType,value:the label name in the message.properties */
	public static final Map<String, String> FILE_NAME_RESOURCE_LABELS_MAP = new HashMap<String, String>() {
		private static final long serialVersionUID = 1701211252019386862L;
		{	
			put("hardwareInstall", "requestInfo.HWInstall.fileName");
		}
	};
	
	/** key:dataType,value:the label name in the message.properties */
	public static final Map<String, String> HEADER_RESOURCE_LABELS_MAP = new HashMap<String, String>() {
		private static final long serialVersionUID = 1701211252019386862L;
		{
			put("activity", "request.headers");
			put("hardwareInstall", "hardwareInstall_EXCELHeader");
		}
	};
	
	/**
	 * This method is used to upload MassUpload Service order list data to excel
	 * @param response
	 * @param fileName_And_EXCELHeader_Key
	 * @param columnPatterns
	 * @param dataList
	 * @param listExportColumnPatterns
	 */
	public void exportHWInstallExcel(ResourceResponse response, String fileName_And_EXCELHeader_Key, String[] columnPatterns, List<?> dataList){
		
	logger.debug("entering HW Install Excel");
	
	OutputStream opStream = null;
	
    try {
	
	if (!FILE_NAME_RESOURCE_LABELS_MAP.containsKey(fileName_And_EXCELHeader_Key)) {
		throw new IllegalArgumentException("The data type[" + fileName_And_EXCELHeader_Key + "] is not supported of ExcelDataExporter");
	}

	String resourceLabelName = FILE_NAME_RESOURCE_LABELS_MAP.get(fileName_And_EXCELHeader_Key);
	logger.debug("resourceLabelName= "+resourceLabelName);
	String fileName = PropertiesMessageUtil.getPropertyMessage(MESSAGE_BUNDLE_NAME, resourceLabelName , locale) + SUFFIX_EXCEL;
	logger.debug("file name= "+fileName);
	response.setProperty("Content-disposition", "attachment; filename=" + fileName);
	
	response.setContentType("application/xls");
	
    // Creating an instance of HSSFWorkbook.
    HSSFWorkbook workbook = new HSSFWorkbook();
    // Creating a sheet in workbook
    HSSFSheet firstSheet = workbook.createSheet(fileName);
    
    // Creating note for Editable fields
    HSSFRow noteEdit = firstSheet.createRow(1);
	HSSFCellStyle noteEditstyle = workbook.createCellStyle();
	noteEditstyle.setFillForegroundColor(HSSFColor.LIGHT_GREEN.index);
	noteEditstyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
	noteEditstyle.setBorderTop((short) 1); 
	noteEditstyle.setBorderBottom((short) 1);
	noteEditstyle.setBorderLeft((short) 1);
	noteEditstyle.setBorderRight((short) 1);
	
	HSSFCell noteEditCell = noteEdit.createCell(1);
	noteEditCell.setCellStyle(noteEditstyle);
	HSSFCell noteEditCell2 = noteEdit.createCell(2);
	noteEditCell2.setCellValue(new HSSFRichTextString("Editable"));

    // Creating note for Non-Editable fields
	HSSFRow noteNonEdit = firstSheet.createRow(2);
	HSSFCellStyle noteNonEditstyle = workbook.createCellStyle();
	noteNonEditstyle.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
	noteNonEditstyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
	noteNonEditstyle.setBorderTop((short) 1); 
	noteNonEditstyle.setBorderBottom((short) 1);
	noteNonEditstyle.setBorderLeft((short) 1);
	noteNonEditstyle.setBorderRight((short) 1);
	
	HSSFCell noteNonEditCell = noteNonEdit.createCell(1);
	noteNonEditCell.setCellStyle(noteNonEditstyle);
	HSSFCell noteNonEditCell2 = noteNonEdit.createCell(2);
	noteNonEditCell2.setCellValue(new HSSFRichTextString("Non Editable"));

    //
    // To write out the workbook into a file we need to create an output
    // stream where the workbook content will be written to.
    //
		opStream = response.getPortletOutputStream();
    	
		// Getting header values
    	final String excelHeaderLabel = HEADER_RESOURCE_LABELS_MAP.get(fileName_And_EXCELHeader_Key);
		logger.debug("excelHeaderLabel= "+excelHeaderLabel);
		final String excelHeader = PropertiesMessageUtil.getPropertyMessage(MESSAGE_BUNDLE_NAME, excelHeaderLabel , locale);
		    	
		HSSFRow rowA = firstSheet.createRow(5);
		
		// Setting styles for header
		HSSFFont headerFont = workbook.createFont();
		headerFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		
		HSSFCellStyle headerstyle=workbook.createCellStyle();
		headerstyle.setFont(headerFont);
		headerstyle.setWrapText(true);
		headerstyle.setBorderTop((short) 1); 
		headerstyle.setBorderBottom((short) 1);
		headerstyle.setBorderLeft((short) 1);
		headerstyle.setBorderRight((short) 1);
		
		// Creating header for data elements
    	List<String> excelHeaderList = Arrays.asList(excelHeader.split(",")); 
    	
    	int headerCellNo =0;
    	for (String excelHeaderValue:excelHeaderList) {
    		firstSheet.setColumnWidth(headerCellNo, 2900);
    		HSSFCell cellA = rowA.createCell(headerCellNo);
    	    cellA.setCellValue(new HSSFRichTextString(excelHeaderValue));
    	    cellA.setCellStyle(headerstyle);
    	    headerCellNo++;
    	}
    	firstSheet.setColumnWidth(2, 4500);
    	
    	// Creating styles for editable fields
    	HSSFCellStyle hsfstyle = workbook.createCellStyle();
    	hsfstyle.setLocked(false); //true or false based on the cell.
    	hsfstyle.setWrapText(true);
    	hsfstyle.setFillForegroundColor(HSSFColor.LIGHT_GREEN.index);
    	hsfstyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
    	hsfstyle.setBorderTop((short) 1); 
    	hsfstyle.setBorderBottom((short) 1);
    	hsfstyle.setBorderLeft((short) 1);
    	hsfstyle.setBorderRight((short) 1);
    	
    	// Creating styles for non-editable fields
    	HSSFCellStyle hsfstyle2 = workbook.createCellStyle();
    	hsfstyle2.setLocked(true);
    	hsfstyle2.setWrapText(true);
    	hsfstyle2.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
    	hsfstyle2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
    	hsfstyle2.setBorderTop((short) 1); 
    	hsfstyle2.setBorderBottom((short) 1);
    	hsfstyle2.setBorderLeft((short) 1);
    	hsfstyle2.setBorderRight((short) 1);
    	
    	int dataRowNo = 6;    	
    	HSSFRow rowB;
    	//String excelDataValue;
    	List<OrderPart> partList = (ArrayList<OrderPart>) dataList;
    	List<Integer> orderQuantity = new ArrayList<Integer>();
    	for(OrderPart part:partList){
    		logger.debug("Part Number Excel "+part.getPartNumber());	
    		logger.debug("Part Desc Excel "+part.getPartDesc());
    		orderQuantity.add(Integer.parseInt(part.getPartQuantity()));
		}
    	int count=0;
    	for (Object object : dataList) {    		
    		for(int j=0;j<orderQuantity.get(count);j++){
			List<String> withoutListValues=new ArrayList<String>();
			for(String exportColumn:columnPatterns){				
					withoutListValues.add(readAndAppendToEXCEL(object,exportColumn));					
				}
				rowB = firstSheet.createRow(dataRowNo);    
				appendWithoutListValues(withoutListValues,rowB,hsfstyle,hsfstyle2);
				int totalRowLength = excelHeaderList.size();
				int valueLength = columnPatterns.length;
				for(int i=valueLength;i<totalRowLength;i++){
					HSSFCell cellB = rowB.createCell(i);
				    cellB.setCellStyle(hsfstyle);
				}
				dataRowNo++;
    	}
    		count++;
		}	
    	
    	HSSFCellStyle unlockedCellStyle = workbook.createCellStyle();
		unlockedCellStyle.setLocked(false);
		
    	makeRestRowsEditable(firstSheet,dataRowNo,columnPatterns.length+27,unlockedCellStyle);
    	
    	firstSheet.protectSheet("");
    	
    	//logger.debug("dataList size= "+dataList.size());
    	
        workbook.write(opStream);
    } catch (IOException e) {
    	logger.debug("IO Exception");
    } catch (Exception e) {
    	logger.debug("Exception");
    } finally {
        if (opStream != null) {
            try {
            	opStream.flush();
            	opStream.close();
            } catch (IOException e) {
            	logger.debug("IO Exception");
            }
        }
    }
    
    logger.debug("exiting export HW Install Excel");
	}
	
	/**
	 * Return the formatted values in String format
	 * @param object
	 * @param column
	 * @return
	 */
	private String readAndAppendToEXCEL(Object object,String column){
		String columnValue=null;
		if(column.indexOf(":ignore")==-1){
			columnValue= BusinessObjectUtil.formatColumn(object,column , locale);
		}
		if(column.equalsIgnoreCase("partQuantity")){
			columnValue= "1";
		}
		columnValue = columnValue == null ? "" : columnValue;
		
		//logger.debug("columnValue="+columnValue);
		return columnValue;
	}
	
	/**
	 * Add values for columnPatterns
	 * @param withoutListValues
	 * @param rowB
	 * @param hsfstyle
	 * @param hsfstyle2
	 * @param listExportColumnPatterns
	 */
	private void appendWithoutListValues(List<String>withoutListValues,HSSFRow rowB,HSSFCellStyle hsfstyle,HSSFCellStyle hsfstyle2){
		int dataColumnNo=0;
		for(String withoutListVal:withoutListValues){
			if(dataColumnNo==0 || dataColumnNo==1) {
				HSSFCell cellB = rowB.createCell(dataColumnNo);
			    cellB.setCellValue(new HSSFRichTextString(withoutListVal));
			    cellB.setCellStyle(hsfstyle2);
			}
			else {
				HSSFCell cellB = rowB.createCell(dataColumnNo);
			    cellB.setCellValue(new HSSFRichTextString(withoutListVal));
				cellB.setCellStyle(hsfstyle);
			}
			dataColumnNo++;
		}		
		
	}
	
	public void exportMassUploadHWExcel(ResourceResponse response, String fileName_And_EXCELHeader_Key, String[] columnPatterns, List<?> dataList,String[] listExportColumnPatterns){
		
		logger.debug("entering exportMassUploadExcel");
		
		OutputStream opStream = null;
		
	    try {
		
		if (!FILE_NAME_RESOURCE_LABELS_MAP.containsKey(fileName_And_EXCELHeader_Key)) {
			throw new IllegalArgumentException("The data type[" + fileName_And_EXCELHeader_Key + "] is not supported of ExcelDataExporter");
		}

		String resourceLabelName = FILE_NAME_RESOURCE_LABELS_MAP.get(fileName_And_EXCELHeader_Key);
		logger.debug("resourceLabelName= "+resourceLabelName);
		String fileName = PropertiesMessageUtil.getPropertyMessage(MESSAGE_BUNDLE_NAME, resourceLabelName , locale) + SUFFIX_EXCEL;
		logger.debug("file name= "+fileName);
		response.setProperty("Content-disposition", "attachment; filename=" + fileName);
		
		response.setContentType("application/xls");
		
	    // Creating an instance of HSSFWorkbook.
	    HSSFWorkbook workbook = new HSSFWorkbook();
	    // Creating a sheet in workbook
	    HSSFSheet firstSheet = workbook.createSheet(fileName);
	    
	    // Creating note for Editable fields
	    HSSFRow noteEdit = firstSheet.createRow(1);
		HSSFCellStyle noteEditstyle = workbook.createCellStyle();
		noteEditstyle.setFillForegroundColor(HSSFColor.LIGHT_GREEN.index);
		noteEditstyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		noteEditstyle.setBorderTop((short) 1); 
		noteEditstyle.setBorderBottom((short) 1);
		noteEditstyle.setBorderLeft((short) 1);
		noteEditstyle.setBorderRight((short) 1);
		
		HSSFCell noteEditCell = noteEdit.createCell(1);
		noteEditCell.setCellStyle(noteEditstyle);
		HSSFCell noteEditCell2 = noteEdit.createCell(2);
		noteEditCell2.setCellValue(new HSSFRichTextString("Editable"));

	    // Creating note for Non-Editable fields
		HSSFRow noteNonEdit = firstSheet.createRow(2);
		HSSFCellStyle noteNonEditstyle = workbook.createCellStyle();
		noteNonEditstyle.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
		noteNonEditstyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		noteNonEditstyle.setBorderTop((short) 1); 
		noteNonEditstyle.setBorderBottom((short) 1);
		noteNonEditstyle.setBorderLeft((short) 1);
		noteNonEditstyle.setBorderRight((short) 1);
		
		HSSFCell noteNonEditCell = noteNonEdit.createCell(1);
		noteNonEditCell.setCellStyle(noteNonEditstyle);
		HSSFCell noteNonEditCell2 = noteNonEdit.createCell(2);
		noteNonEditCell2.setCellValue(new HSSFRichTextString("Non Editable"));

	    //
	    // To write out the workbook into a file we need to create an output
	    // stream where the workbook content will be written to.
	    //
			opStream = response.getPortletOutputStream();
	    	
			// Getting header values
	    	final String excelHeaderLabel = HEADER_RESOURCE_LABELS_MAP.get(fileName_And_EXCELHeader_Key);
			logger.debug("excelHeaderLabel= "+excelHeaderLabel);
			final String excelHeader = PropertiesMessageUtil.getPropertyMessage(MESSAGE_BUNDLE_NAME, excelHeaderLabel , locale);
			logger.debug("excelHeader= "+excelHeader);
	    	
			HSSFRow rowA = firstSheet.createRow(5);
			
			// Setting styles for header
			HSSFFont headerFont = workbook.createFont();
			headerFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			
			HSSFCellStyle headerstyle=workbook.createCellStyle();
			headerstyle.setFont(headerFont);
			headerstyle.setWrapText(true);
			headerstyle.setBorderTop((short) 1); 
			headerstyle.setBorderBottom((short) 1);
			headerstyle.setBorderLeft((short) 1);
			headerstyle.setBorderRight((short) 1);
			
			// Creating header for data elements
	    	List<String> excelHeaderList = Arrays.asList(excelHeader.split(",")); 
	    	
	    	int headerCellNo =0;
	    	for (String excelHeaderValue:excelHeaderList) {
	    		firstSheet.setColumnWidth(headerCellNo, 2900);
	    		HSSFCell cellA = rowA.createCell(headerCellNo);
	    	    cellA.setCellValue(new HSSFRichTextString(excelHeaderValue));
	    	    cellA.setCellStyle(headerstyle);
	    	    headerCellNo++;
	    	}
	    	firstSheet.setColumnWidth(2, 4500);
	    	
	    	// Creating styles for editable fields
	    	HSSFCellStyle hsfstyle = workbook.createCellStyle();
	    	hsfstyle.setLocked(false); //true or false based on the cell.
	    	hsfstyle.setWrapText(true);
	    	hsfstyle.setFillForegroundColor(HSSFColor.LIGHT_GREEN.index);
	    	hsfstyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
	    	hsfstyle.setBorderTop((short) 1); 
	    	hsfstyle.setBorderBottom((short) 1);
	    	hsfstyle.setBorderLeft((short) 1);
	    	hsfstyle.setBorderRight((short) 1);
	    	
	    	// Creating styles for non-editable fields
	    	HSSFCellStyle hsfstyle2 = workbook.createCellStyle();
	    	hsfstyle2.setWrapText(true);
	    	hsfstyle2.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
	    	hsfstyle2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
	    	hsfstyle2.setBorderTop((short) 1); 
	    	hsfstyle2.setBorderBottom((short) 1);
	    	hsfstyle2.setBorderLeft((short) 1);
	    	hsfstyle2.setBorderRight((short) 1);
	    	
	    	int dataRowNo = 6;
	    	int dataColumnNo;
	    	HSSFRow rowB;
	    	String excelDataValue;
	    	for (Object object : dataList) {
	    				
				List<String> withoutListValues=new ArrayList<String>();
				for(String exportColumn:columnPatterns){
					
					if(BusinessObjectUtil.readPropertyList(object, exportColumn) instanceof ArrayList<?> ){
						
						
						List<?> listValues=(List<?>)BusinessObjectUtil.readPropertyList(object, exportColumn);
						for(Object obj:listValues){
							rowB = firstSheet.createRow(dataRowNo);    
							appendHWWithoutListValues(withoutListValues,rowB,hsfstyle,hsfstyle2,listExportColumnPatterns);
							
							dataColumnNo=24;
								for(String listExportColumn:listExportColumnPatterns){
									HSSFCell cellB = rowB.createCell(dataColumnNo);
									excelDataValue = readAndAppendToHWEXCEL(obj,listExportColumn);
									cellB.setCellValue(new HSSFRichTextString(excelDataValue));
									// Apply style for editable and non-editable fields
									cellB.setCellStyle(hsfstyle);
									//logger.debug("dataRowNo="+dataRowNo+" dataColumnNo="+dataColumnNo+" excelDataValue="+excelDataValue);
									dataColumnNo++;
								}
								dataRowNo++;
						}
						
						
					}else{
						withoutListValues.add(readAndAppendToHWEXCEL(object,exportColumn));
					}
				}		
			}
	    	
	    	
	    	firstSheet.protectSheet("");
	    	
	    	//logger.debug("dataList size= "+dataList.size());
	    	
	        workbook.write(opStream);
	    } catch (IOException e) {
	    	logger.debug("IO Exception");
	    } catch (Exception e) {
	    	logger.debug("Exception");
	    } finally {
	        if (opStream != null) {
	            try {
	            	opStream.flush();
	            	opStream.close();
	            } catch (IOException e) {
	            	logger.debug("IO Exception");
	            }
	        }
	    }
	    
	    logger.debug("exiting exportMassUploadExcel");
		}
		
		/**
		 * Return the formatted values in String format
		 * @param object
		 * @param column
		 * @return
		 */
		private String readAndAppendToHWEXCEL(Object object,String column){
			String columnValue=null;
			if(column.indexOf(":ignore")==-1){
				columnValue= BusinessObjectUtil.formatColumn(object,column , locale);
			}
			columnValue = columnValue == null ? "" : columnValue;
			
			//logger.debug("columnValue="+columnValue);
			return columnValue;
		}
		
		/**
		 * Add values for columnPatterns
		 * @param withoutListValues
		 * @param rowB
		 * @param hsfstyle
		 * @param hsfstyle2
		 * @param listExportColumnPatterns
		 */
		private void appendHWWithoutListValues(List<String>withoutListValues,HSSFRow rowB,HSSFCellStyle hsfstyle,HSSFCellStyle hsfstyle2,String[] listExportColumnPatterns){
			int dataColumnNo=0;
			//logger.debug("inside appendHWWithoutListValues");
			for(String withoutListVal:withoutListValues){
			//	logger.debug("dataColumnNo="+dataColumnNo+"column value="+withoutListVal);
				if(dataColumnNo==24){
					dataColumnNo+=2;
				}
					HSSFCell cellB = rowB.createCell(dataColumnNo);
					cellB.setCellValue(new HSSFRichTextString(withoutListVal));
					if(dataColumnNo>=0 && dataColumnNo<=2){
					//	logger.debug("style is style2");
						cellB.setCellStyle(hsfstyle2);
					}
					else{
					//	logger.debug("style is style1");
						cellB.setCellStyle(hsfstyle);
					}
				
				
				dataColumnNo++;
			}
		}
		
		/**
		 * @param sheet 
		 * @param startRow 
		 * @param numberOfCols 
		 * @param unlockedCellStyle 
		 */
		private void makeRestRowsEditable(HSSFSheet sheet,int startRow,int numberOfCols,HSSFCellStyle unlockedCellStyle){
			logger.debug("making rows editable start row="+startRow+" end row="+numberOfCols);
			int maxRows=Integer.parseInt(PropertiesMessageUtil.getCommonPorperty("com.lexmark.services.resources.excelExporter","excel.rows.editable"));
			logger.debug("max rows"+maxRows);
			for(int i=startRow;i<maxRows;i++){
				HSSFRow row = sheet.createRow(i);
				
				for(int j=0;j<numberOfCols;j++){
					HSSFCell cell=row.createCell(j);
					cell.setCellStyle( unlockedCellStyle);
				}
			}
			
			
		}
}

