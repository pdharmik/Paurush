/**
 * Copyright@ 2013. This document has been created & written by
 * Wipro technologies for Lexmark and this is copyright to Lexmark
 *
 * File         	: ExcelDataExporter.java
 * Package     		: com.lexmark.exporter
 * Creation Date 	: 
 *
 * Modification History:
 *
 * ---------------------------------------------------------------------
 * Author 			Date				Version  		Comments
 * ---------------------------------------------------------------------
 *
 */
package com.lexmark.exporter;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.portlet.ResourceResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.hssf.usermodel.DVConstraint;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataValidation;
import org.apache.poi.hssf.usermodel.HSSFDataValidation.ErrorStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFName;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddressList;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;

import com.lexmark.util.BusinessObjectUtil;
import com.lexmark.util.PropertiesMessageUtil;
 


/**
 * @author Wipro 
 * @version 2.1 
 */
public class ExcelDataExporter {
	/**MESSAGE_BUNDLE_NAME */
	public static final String MESSAGE_BUNDLE_NAME = "com.lexmark.resources.messages";

	

	/**FILE_NAME_RESOURCE_LABELS_MAP */
	// key:dataType,value:the label name in the message.properties
	public static final Map<String, String> FILE_NAME_RESOURCE_LABELS_MAP = new HashMap<String, String>() {
		private static final long serialVersionUID = 1701211252019386862L;
		{		
			put("activity", "request.fileName");
			
			//Mass Upload
			put("massUpload_serviceOrder", "requestInfo.massUploadTemplate.serviceOrder.fileName");
			put("massUpload_hardwareOrder","requestInfo.massUploadTemplate.hardwareOrder.fileName");
		}
	};
	/**HEADER_RESOURCE_LABELS_MAP */	
	// key:dataType,value:the label name in the message.properties
	public static final Map<String, String> HEADER_RESOURCE_LABELS_MAP = new HashMap<String, String>() {
		private static final long serialVersionUID = 1701211252019386862L;
		{
			put("activity", "request.headers");
			
			//Mass Upload
			put("massUpload_serviceOrder", "serviceOrder_EXCELHeader");
			put("massUpload_hardwareOrder","hardwareOrder_EXCELHeader");
		}
	};
	/** Suffix declaration */
	private static final String SUFFIX_EXCEL = ".xls";
	/** logger declaration */
	private static Logger logger = LogManager.getLogger(ExcelDataExporter.class);
	/** locale declaration */
	private Locale locale;
	

	/**
	 * @param locale 
	 */
	public void setLocale(Locale locale) {
		this.locale = locale;
	}
	
	/**
	 * This method is used to upload MassUpload Service order list data to excel
	 * @param response 
	 * @param fileName_And_EXCELHeader_Key 
	 * @param columnPatterns 
	 * @param dataList 
	 * @param listExportColumnPatterns 
	 */
	public void exportMassUploadExcel(ResourceResponse response, String fileName_And_EXCELHeader_Key, String[] columnPatterns, List<?> dataList,String[] listExportColumnPatterns){
		
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
	response.setProperty("Content-disposition", "attachment; filename=\"" + fileName+"\"");
	
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
    	
		final String firstLine = PropertiesMessageUtil.getPropertyMessage(MESSAGE_BUNDLE_NAME, "serviceOrder_EXCELHeader_1stLine", locale);
		
		HSSFRow row0 = firstSheet.createRow(5);
		HSSFRow rowA = firstSheet.createRow(6);
		
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
    	/**
    	 * This is for setting the first row of the 
    	 * Excel row0 is the first row object
    	 * */
    	headerCellNo=0;
    	List<String> firstRow = Arrays.asList(firstLine.split(","));
    	for (String excelHeaderValue:firstRow) {
    		firstSheet.setColumnWidth(headerCellNo, 2900);
    		HSSFCell cellB = row0.createCell(headerCellNo);
    	    cellB.setCellValue(new HSSFRichTextString(excelHeaderValue));
    	    cellB.setCellStyle(headerstyle);
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
    	
    	int dataRowNo = 7;
    	int dataColumnNo=0;
    	HSSFRow rowB=null;
    	String excelDataValue=null;
    	for (Object object : dataList) {
    				
			List<String> withoutListValues=new ArrayList<String>();
			for(String exportColumn:columnPatterns){
				
				if(exportColumn.indexOf(":list")!=-1){
					
					
					List<?> listValues=(List<?>)BusinessObjectUtil.readPropertyList(object, exportColumn.substring(0,exportColumn.indexOf(":")));
					if(listValues!=null && !listValues.isEmpty()){
					for(Object obj:listValues){
						rowB = firstSheet.createRow(dataRowNo);    
						appendWithoutListValues(withoutListValues,rowB,hsfstyle,hsfstyle2,listExportColumnPatterns);
						
						dataColumnNo=7;
							for(String listExportColumn:listExportColumnPatterns){
								HSSFCell cellB = rowB.createCell(dataColumnNo);
								excelDataValue = readAndAppendToEXCEL(obj,listExportColumn);
								cellB.setCellValue(new HSSFRichTextString(excelDataValue));
								// Apply style for editable and non-editable fields
								cellB.setCellStyle(hsfstyle);
								 
								//logger.debug("dataRowNo="+dataRowNo+" dataColumnNo="+dataColumnNo+" excelDataValue="+excelDataValue);
								dataColumnNo++;
							}
							dataRowNo++;
					}
				}else{
					rowB = firstSheet.createRow(dataRowNo);    
					appendWithoutListValues(withoutListValues,rowB,hsfstyle,hsfstyle2,listExportColumnPatterns);
					dataColumnNo=7;
					for(String listExportColumn:listExportColumnPatterns){
						HSSFCell cellB = rowB.createCell(dataColumnNo);						
						cellB.setCellValue(new HSSFRichTextString(""));
						// Apply style for editable and non-editable fields
						cellB.setCellStyle(hsfstyle);						 
						//logger.debug("dataRowNo="+dataRowNo+" dataColumnNo="+dataColumnNo+" excelDataValue="+excelDataValue);
						dataColumnNo++;
					}
					dataRowNo++;
				}
					
					
				}else{
					withoutListValues.add(readAndAppendToEXCEL(object,exportColumn));
				}
			}		
		}
    	CellStyle unlockedCellStyle = workbook.createCellStyle();
		unlockedCellStyle.setLocked(false);
		
    	makeRestRowsEditable(firstSheet,dataRowNo,columnPatterns.length+listExportColumnPatterns.length,unlockedCellStyle);
    	
    	firstSheet.protectSheet("");
    	
    	//logger.debug("dataList size= "+dataList.size());
    	
        workbook.write(opStream);
    } catch (IOException e) {
    	logger.error("error occured "+e.getMessage());
    } catch (Exception e) {
    	logger.error("error occured "+e.getMessage());
    } finally {
        if (opStream != null) {
            try {
            	opStream.flush();
            	opStream.close();
            } catch (IOException e) {
            	logger.error("error occured "+e.getMessage());
            }
        }
    }
    
    logger.debug("exiting exportMassUploadExcel");
	}
	
	
	
	/**
	 * @param sheet 
	 * @param startRow 
	 * @param numberOfCols 
	 * @param unlockedCellStyle 
	 */
	private void makeRestRowsEditable(HSSFSheet sheet,int startRow,int numberOfCols,CellStyle unlockedCellStyle){
		logger.debug("making rows editable start row="+startRow+" end row="+numberOfCols);
		int additionalRows=Integer.parseInt(PropertiesMessageUtil.getCommonPorperty("com.lexmark.resources.common","excel.rows.editable"));
		logger.debug("additional Rows"+additionalRows);
		for(int i=startRow;i<startRow+additionalRows;i++){
			HSSFRow row = sheet.createRow(i);
			
			for(int j=0;j<numberOfCols;j++){
				HSSFCell cell=row.createCell(j);
				cell.setCellStyle(unlockedCellStyle);
			}
		}
		
		
	}
	
	/**
	 * Return the formatted values in String format 
	 * @param object  
	 * @param column 
	 * @return string 
	 */ 
	private String readAndAppendToEXCEL(Object object,String column){
		
		String columnValue=null;
		if(StringUtils.isBlank(column)){
			return "";
		}
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
	private void appendWithoutListValues(List<String>withoutListValues,HSSFRow rowB,HSSFCellStyle hsfstyle,HSSFCellStyle hsfstyle2,String[] listExportColumnPatterns){
		int dataColumnNo=0;
		for(String withoutListVal:withoutListValues){
		if(dataColumnNo<=4) {//here 4 refers to the non editable style uptil cell 4
			HSSFCell cellB = rowB.createCell(dataColumnNo);
		    cellB.setCellValue(new HSSFRichTextString(withoutListVal));
		    cellB.setCellStyle(hsfstyle2);
		}else if(dataColumnNo>=5 && dataColumnNo<=6){
			HSSFCell cellB = rowB.createCell(dataColumnNo);
		    cellB.setCellValue(new HSSFRichTextString(withoutListVal));
		    cellB.setCellStyle(hsfstyle);
		}else {
			HSSFCell cellB = rowB.createCell(dataColumnNo+listExportColumnPatterns.length);
		    cellB.setCellValue(new HSSFRichTextString(withoutListVal));
			cellB.setCellStyle(hsfstyle);
		}
		
			dataColumnNo++;
		}
	}
	
	
	/**
	 * @param response
	 * @param fileName_And_EXCELHeader_Key
	 * @param columnPatterns
	 * @param dataList
	 * @param listExportColumnPatterns
	 * @param dropDownValuesForExcel
	 */
	public void exportMassUploadHWExcel(ResourceResponse response, String fileName_And_EXCELHeader_Key, String[] columnPatterns, List<?> dataList,String[] listExportColumnPatterns,Map<Integer,String[]> dropDownValuesForExcel){
		
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
			//final String excelHeader = PropertiesMessageUtil.getPropertyMessage(MESSAGE_BUNDLE_NAME, excelHeaderLabel , locale);
			//logger.debug("excelHeader= "+excelHeader);
	    	
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
	    	//List<String> excelHeaderList = Arrays.asList(excelHeader.split(",")); 
	    	
	    	int headerCellNo =0;
	    	for (String excelHeaderValue:hardwareFieldMap.keySet()) {
	    		firstSheet.setColumnWidth(headerCellNo, 2900);
	    		HSSFCell cellA = rowA.createCell(headerCellNo);
	    	    cellA.setCellValue(new HSSFRichTextString(excelHeaderValue));
	    	    cellA.setCellStyle(headerstyle);
	    	    headerCellNo++;
	    	}
	    	firstSheet.setColumnWidth(2, 4500);
	    	
	    	// Creating styles for editable fields
	    	HSSFCellStyle editableStyle = workbook.createCellStyle();
	    	editableStyle.setLocked(false); //true or false based on the cell.
	    	editableStyle.setWrapText(true);
	    	editableStyle.setFillForegroundColor(HSSFColor.LIGHT_GREEN.index);
	    	editableStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
	    	editableStyle.setBorderTop((short) 1); 
	    	editableStyle.setBorderBottom((short) 1);
	    	editableStyle.setBorderLeft((short) 1);
	    	editableStyle.setBorderRight((short) 1);
	    	
	    	// Creating styles for non-editable fields
	    	HSSFCellStyle nonEditableStyle = workbook.createCellStyle();
	    	nonEditableStyle.setWrapText(true);
	    	nonEditableStyle.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
	    	nonEditableStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
	    	nonEditableStyle.setBorderTop((short) 1); 
	    	nonEditableStyle.setBorderBottom((short) 1);
	    	nonEditableStyle.setBorderLeft((short) 1);
	    	nonEditableStyle.setBorderRight((short) 1);
	    	
	    	int dataRowNo = 6;
	    	int dataColumnNo=0;
	    	HSSFRow rowB=null;
	    	String excelDataValue=null;
	    	for (Object object : dataList) {
	    				
				List<String> withoutListValues=new ArrayList<String>();
				for(String exportColumn:columnPatterns){
					
					
					if(exportColumn.indexOf(":list")>0){
						//logger.debug("in instance of list");
						
						List<?> listValues=(List<?>)BusinessObjectUtil.readPropertyList(object, exportColumn.substring(0,exportColumn.indexOf(":list")));
						if(listValues!=null){
							for(Object obj:listValues){
								rowB = firstSheet.createRow(dataRowNo);    
								appendHWWithoutListValues(withoutListValues,rowB,editableStyle,nonEditableStyle);
								
								dataColumnNo=27;
									for(String listExportColumn:listExportColumnPatterns){
										HSSFCell cellB = rowB.createCell(dataColumnNo);
										cellB.setCellType(HSSFCell.CELL_TYPE_STRING);
										excelDataValue = readAndAppendToHWEXCEL(obj,listExportColumn);
										
										cellB.setCellValue(new HSSFRichTextString(excelDataValue));
										// Apply style for editable and non-editable fields
										cellB.setCellStyle(editableStyle);
										//logger.debug("dataRowNo="+dataRowNo+" dataColumnNo="+dataColumnNo+" excelDataValue="+excelDataValue);
										dataColumnNo++;
									}
									dataRowNo++;
							}
						}else{
							rowB = firstSheet.createRow(dataRowNo);    
							appendHWWithoutListValues(withoutListValues,rowB,editableStyle,nonEditableStyle);
							dataRowNo++;
						}
						
						
					}else{
						//logger.debug("appending without list value="+readAndAppendToHWEXCEL(object,exportColumn) );
						withoutListValues.add(readAndAppendToHWEXCEL(object,exportColumn));
					}
				}		
			}
	    	
	    	CellStyle unlockedCellStyle = workbook.createCellStyle();
			unlockedCellStyle.setLocked(false);
	    	
	    	makeRestRowsEditable(firstSheet,dataRowNo,columnPatterns.length+listExportColumnPatterns.length,unlockedCellStyle);
	    	
	    	
	    	setDropDownValues(workbook,firstSheet,dropDownValuesForExcel);
	    	/*Commented as of now
	    	setNumericConstraints(firstSheet,"0",null,31);//Used
	    	setNumericConstraints(firstSheet,"0",null,32);//Not Used
	    	setNumericConstraints(firstSheet,"0",null,33);// DOA
	    	setNumericConstraints(firstSheet,"256",null,45);//Fax Port Number
	    	setNumericConstraints(firstSheet,"256",null,48);//Port Number
	    	setNumericConstraints(firstSheet,"0",null,62);//Page Count
	    	setNumericConstraints(firstSheet,"1",null,65);//Travel of Measure
	    	
	    	setDateConstraints(firstSheet,5,DateUtil.convertDateToGMTString(new Date()),"MM/dd/yyyy HH:mm:ss");//Asset Store Date
	    	setDateConstraints(firstSheet,34,DateUtil.convertDateToGMTString(new Date()),"MM/dd/yyyy HH:mm:ss");//Install Date
	    	*/
	    	firstSheet.protectSheet("");
	    	
	    	//logger.debug("dataList size= "+dataList.size());
	    	
	        workbook.write(opStream);
	       // logger.debug("after outputstream write");
	    } catch (IOException e) {
	    	logger.error("error occured "+e.getMessage());
	    } catch (Exception e) {
	    	logger.error("error occured "+e.getMessage());
	    } finally {
	        if (opStream != null) {
	            try {
	            	opStream.flush();
	            	opStream.close();
	            } catch (IOException e) {
	            	logger.error("error occured "+e.getMessage());
	            }
	        }
	    }
	    
	    logger.debug("exiting exportMassUploadExcel");
		}
		
		/**
		 * Return the formatted values in String format
		 * @param object 
		 * @param column 
		 * @return string 
		 */
		private String readAndAppendToHWEXCEL(Object object,String column){
			String columnValue=null;
			if(StringUtils.isBlank(column)){
				return "";
			}
			if(column.indexOf(":ignore")==-1){
				boolean isboolean=false;
				if(column.endsWith("faxConnected")){
					isboolean=true;
				}
				
				columnValue= BusinessObjectUtil.formatColumn(object,column , locale);
				if(isboolean){
					if(columnValue.equalsIgnoreCase("false")){
						columnValue="N";
					}else{
						columnValue="Y";
					}
				}
			}
			columnValue = columnValue == null ? "" : columnValue;
			
			//logger.debug("columnValue="+columnValue);
			return columnValue;
		}
		
		/**
		 * Add values for columnPatterns 
		 * @param withoutListValues 
		 * @param rowB 
		 * @param editableStyle   
		 * @param nonEditableStyle  
		 */
		private void appendHWWithoutListValues(List<String>withoutListValues,HSSFRow rowB,HSSFCellStyle editableStyle,HSSFCellStyle nonEditableStyle){
			int dataColumnNo=0;
		//	logger.debug("inside appendHWWithoutListValues");
			for(String withoutListVal:withoutListValues){
				//logger.debug("dataColumnNo="+dataColumnNo+"column value="+withoutListVal);
				if(dataColumnNo==27){
					dataColumnNo+=7;
				}
					HSSFCell cellB = rowB.createCell(dataColumnNo);
					cellB.setCellType(HSSFCell.CELL_TYPE_STRING);
					cellB.setCellValue(new HSSFRichTextString(withoutListVal));
					if(dataColumnNo>=0 && dataColumnNo<=2){
					//	logger.debug("style is style2");
						cellB.setCellStyle(nonEditableStyle);
					}
					else{
					//	logger.debug("style is style1");
						cellB.setCellStyle(editableStyle);
					}
				
				
				dataColumnNo++;
			}
		}
		
		
		/**
		 * @param workbook
		 * @param sheet
		 * @param dropDownValuesForExcel
		 */
		private void setDropDownValues(HSSFWorkbook workbook,HSSFSheet sheet,Map<Integer,String[]> dropDownValuesForExcel){
			
			int additionalRows=Integer.parseInt(PropertiesMessageUtil.getCommonPorperty("com.lexmark.resources.common","excel.rows.editable"));
			
			
			HSSFSheet hidden = workbook.createSheet("hidden");
			
			workbook.setSheetHidden(1, true);
			 
			int hiddenRowNumber=0;
			for(Integer key:dropDownValuesForExcel.keySet()){
				String[] arrayValues=dropDownValuesForExcel.get(key);
				int startRow=hiddenRowNumber+1;
				for (int i = 0, length= arrayValues.length; i < length; i++) {
					   String name = arrayValues[i];
					   HSSFRow row = hidden.createRow(hiddenRowNumber++);
					   HSSFCell cell = row.createCell(0);
					   cell.setCellValue(name);
					 }
				logger.debug("startRow="+startRow+"hiddenRowNumber="+hiddenRowNumber+"key="+key);
				 HSSFName namedCell = workbook.createName();
				 namedCell.setNameName("hidden"+key);
				 namedCell.setRefersToFormula("hidden!$A$"+startRow+":$A$" + hiddenRowNumber);
				
				/*CellRangeAddressList addressList = new CellRangeAddressList(0, additionalRows, key,key );
				
				  DVConstraint dvConstraint = DVConstraint.createExplicitListConstraint(dropDownValuesForExcel.get(key));
				  
				  HSSFDataValidation dataValidation = new HSSFDataValidation
				    (addressList, dvConstraint);
				  dataValidation.setSuppressDropDownArrow(false);
				
				  sheet.addValidationData(dataValidation);*/
				 DVConstraint constraint = DVConstraint.createFormulaListConstraint("hidden"+key);
				 CellRangeAddressList addressList = new CellRangeAddressList(6, additionalRows, key,key);
				 HSSFDataValidation validation = new HSSFDataValidation(addressList, constraint);
				 sheet.addValidationData(validation);
			}
			
		}
		
		/**
		 * @param sheet
		 * @param lowerLimit
		 * @param higherLimit
		 * @param columnIndex
		 */
		private void setNumericConstraints(HSSFSheet sheet,String lowerLimit,String higherLimit,int columnIndex){
			int additionalRows=Integer.parseInt(PropertiesMessageUtil.getCommonPorperty("com.lexmark.resources.common","excel.rows.editable"));
			DVConstraint	numericConstraint = DVConstraint.createNumericConstraint(
				    DVConstraint.ValidationType.INTEGER,
				    DVConstraint.OperatorType.GREATER_THAN, lowerLimit,higherLimit);
		 CellRangeAddressList addressList = new CellRangeAddressList(6, additionalRows,columnIndex, columnIndex);
		 HSSFDataValidation validation3 = new HSSFDataValidation(addressList, numericConstraint);
		 validation3.setErrorStyle(ErrorStyle.STOP);
		 validation3.createErrorBox("Error", "Cell Data Should Be Greater Than "+lowerLimit);
		 //workbook.setSheetHidden(1, true);
		 sheet.addValidationData(validation3);
		}
		
		/**
		 * @param sheet
		 * @param columnIndex
		 * @param currentDateTime
		 * @param dateFormatSiebel
		 */
		private void setDateConstraints(HSSFSheet sheet,int columnIndex, String currentDateTime , String dateFormatSiebel){
			int additionalRows=Integer.parseInt(PropertiesMessageUtil.getCommonPorperty("com.lexmark.resources.common","excel.rows.editable"));
			 DVConstraint	dateConstraint = DVConstraint.createDateConstraint(DVConstraint.OperatorType.GREATER_OR_EQUAL, currentDateTime, null, dateFormatSiebel);
			 CellRangeAddressList addressList = new CellRangeAddressList(6, additionalRows,columnIndex, columnIndex);
			 HSSFDataValidation validation = new HSSFDataValidation(addressList, dateConstraint);
			 validation.setErrorStyle(ErrorStyle.STOP);
			 validation.createErrorBox("Invalid Date", "Invalid Date");
			 validation.createPromptBox("Date Format", "Date Format should be "+dateFormatSiebel);
			 validation.setShowPromptBox(true);
			 //workbook.setSheetHidden(1, true);
			 sheet.addValidationData(validation);
		}
		
		
		
		public static Map<String,String> hardwareFieldMap= new LinkedHashMap<String,String>(){
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			{
			put("SR Number","");
			put("Activity Id" ,"\"# LXK MPS Action Import BC.Activity UID\"");
			put("Asset Description" ,"\"LXK MPS Action Import BC.LXK MPS Asset Desc PreDebriefRFV\"");
			put("Product" , "");
			
			
			put("Device Type Model Number","\"LXK MPS Action Import BC.LXK MPS Dev Type Model Num PreDebriefRFV\"");
			//put("Asset Stored Date (MM/dd/yyyy HH:mm:ss)" ,"LXK MPS Action Import BC.LXK MPS Asset Stored Date PreDebriefRFV");
			put("Customer Name" ,"\"LXK MPS Action Import BC.LXK MPS CHL Name PreDebriefRFV\"");
			
			put("Consumables Contact Last Name" , "\"LXK MPS Action Import BC_Contact.Contact Last Name\"");
			put("Consumables Contact First Name" ,"\"LXK MPS Action Import BC_Contact.Contact First Name\"");
			put("Contact Primary Phone #" ,"\"LXK MPS Action Import BC_Contact.Contact Work Phone #\"");
			put("Contact Secondary Phone #","\"LXK MPS Action Import BC_Contact.Contact Home Phone\"");
			put("Contact Email Address","\"LXK MPS Action Import BC_Contact.Contact Email Address\"");
			put("Contact type","\"LXK MPS Action Import BC_Contact.Contact Job Role\"");
			put("Contact country","\"LXK MPS Action Import BC_Contact.LXK MPS Uploader Country\"");
			
			
			put("Installed Address Name","\"LXK MPS Action Import BC.LXK C Installed Asset Address Name PreDebriefRFV\"");
			put("Installed Address Line 1","\"LXK MPS Action Import BC.LXK R Installed Address Line 1\"");
			put("Installed Address Line 2","\"LXK MPS Action Import BC.LXK R Installed Address Line 2\"");
			put("Installed City","\"LXK MPS Action Import BC.LXK R Installed City\"");
			put("Installed County","\"LXK MPS Action Import BC.LXK R Installed County\"");
			put("Installed State","\"LXK MPS Action Import BC.LXK R Installed State\"");
			put("Installed Province","\"LXK MPS Action Import BC.LXK R Installed Province\"");
			put("Installed Postal Code","\"LXK MPS Action Import BC.LXK R Installed Postal Code\"");
			put("Installed Country","\"LXK MPS Action Import BC.LXK R Installed Country\"");
			put("Building","\"LXK MPS Action Import BC.LXK C Installed Asset Physical Location 1 PreDebriefRFV\"");
			put("Floor","\"LXK MPS Action Import BC.LXK C Installed Asset Physical Location 2 PreDebriefRFV\"");
			put("Office","\"LXK MPS Action Import BC.LXK C Installed Asset Physical Location 3 PreDebriefRFV\"");
			put("Installed Address District","\"LXK MPS Action Import BC.LXK R Installed Address District\"");
			put("Installed Address Office#","\"LXK MPS Action Import BC.LXK R Installed Address Office#\"");
			
			put("Part Row Id","\"LXK MPS Parts & Tools Import BC.LXK MPS Seq Num\"");
			put("Part Number (s)","\"LXK MPS Parts & Tools Import BC.Product Name\"");
			put("Quantity","\"LXK MPS Parts & Tools Import BC.Recommended Quantity\"");
			put("Used Quantity","\"LXK MPS Parts & Tools Import BC.LXK MPS Used Qty\"");
			put("Not Used Quantity","\"LXK MPS Parts & Tools Import BC.LXK MPS Not Used Qty\"");
			put("DOA Quantity","\"LXK MPS Parts & Tools Import BC.LXK MPS DOA Qty\"");
			put("Part Type ( Recommended or Additional)","\"LXK MPS Parts & Tools Import BC.Relation Type\"");


			put("Install Date(MM/dd/yyyy HH:mm:ss)","\"LXK MPS Action Import BC.LXK MPS Install Date PreDebriefRFV\"");
			put("Serial Number","\"LXK MPS Action Import BC.LXK C Installed Asset Serial Number\"");
			put("Network Connected","\"LXK MPS Action Import BC.LXK R Network Connected\"");
			put("IP Address","\"LXK MPS Action Import BC.LXK R IP Address\"");
			put("IP Gateway","\"LXK MPS Action Import BC.LXK MPS IP Gateway PreDebriefRFV\"");
			put("IP Submask","\"LXK MPS Action Import BC.LXK MPS IP Submask PreDebriefRFV\"");
			put("IPv6","\"LXK MPS Action Import BC.LXK MPS IP V6 PreDebriefRFV\"");
			put("MAC Address","\"LXK MPS Action Import BC.LXK C Installed Asset MAC Address PreDebriefRFV\"");
			put("Device Condition","\"LXK MPS Action Import BC.LXK C Installed Asset Device Conditioin PreDebriefRFV\"");
			put("Fax Connected","\"LXK MPS Action Import BC.LXK MPS Fax Connected PreDebriefRFV\"");
			put("Fax Port Number","\"LXK MPS Action Import BC.LXK MPS Fax port num PreDebriefRFV\"");
			put("Host Name","\"LXK MPS Action Import BC.LXK MPS Host Name PreDebriefRFV\"");
			put("Computer Name","\"LXK MPS Action Import BC.LXKMPSComputerNamePreDebriefRFV\"");
			put("Port Number","\"LXK MPS Action Import BC.LXK MPS Port Num PreDebriefRFV\"");
			put("Wiring Closet Network Point","\"LXK MPS Action Import BC.LXK MPS Wiring Closet_Netwk Pt PreDebriefRFV\"");
			put("Account Name","\"LXK MPS Action Import BC.LXK MPS Account Name PreDebriefRFV\"");
			put("Cost Center","\"LXK MPS Action Import BC.LXK MPS Asset Cost Center PreDebriefRFV\"");
			put("Customer Device Tag","\"LXK MPS Action Import BC.LXK MPS Customer Device Tag PreDebriefRFV\"");
			put("Department Id","\"LXK MPS Action Import BC.LXK MPS Dept Id PreDebriefRFV\"");
			put("Department Name","\"LXK MPS Action Import BC.LXK MPS Dept Name PreDebriefRFV\"");
			put("Firmware","\"LXK MPS Action Import BC.LXK MPS Firmware PreDebriefRFV\"");
			put("Network Topology","\"LXK MPS Action Import BC.LXK MPS Network Topology PreDebriefRFV\"");
			put("Operating System","\"LXK MPS Action Import BC.LXK MPS OS PreDebriefRFV\"");
			put("Operating System Version","\"LXK MPS Action Import BC.LXK MPS OS Version PreDebriefRFV\"");
			put("TopBill","\"LXK MPS Action Import BC.LXK MPS Topbill Num  PreDebriefRFV\"");
			put("Special Usage","\"LXK MPS Action Import BC.LXK MPS Usage UOM PreDebriefRFV\"");
			
			put("Page Count Type","\"LXK MPS Activity Readings.LXK MPS Page Count Type\"");
			put("Page Count","\"LXK MPS Activity Readings.Page Count\"");
			
			put("Technician Name","\"LXK MPS Action Import BC.LXK SD Technician Name\"");
			put("Service Provider Reference #","\"LXK MPS Action Import BC.LXK R SP Reference #\"");
			//put("Travel Distance","LXK MPS Action Import BC.LXK R Travel Distance");
			//put("Travel Distance Unit of Measure","LXK MPS Action Import BC.LXK R Travel Distance UM");
			//put("Travel Duration","LXK MPS Action Import BC.LXK R Travel Duration");
			put("Action Status","\"LXK MPS Action Import BC.Status\"");
			put("Action Narrative","\"LXK MPS Action Import BC.LXK R Substatus\"");
			put("Debrief Status","\"LXK MPS Action Import BC.LXK R Debrief Status\"");
			}
		};
}

