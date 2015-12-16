package com.lexmark.exporter;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.portlet.ResourceResponse;

import org.apache.commons.beanutils.NestedNullException;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.DVConstraint;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataValidation;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFName;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFDataValidation.ErrorStyle;
//import org.apache.poi.hssf.util.CellRangeAddressList;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;




import com.lexmark.domain.Field;
import com.lexmark.domain.ListFieldDetail;
import com.lexmark.domain.TemplateInformation;
import com.lexmark.framework.logging.LEXLogger;
import com.lexmark.util.BusinessObjectUtil;
import com.lexmark.util.DateUtil;
import com.lexmark.util.DownloadFileLocalizationUtil;
import com.lexmark.util.PropertiesMessageUtil;

public class ExcelExporter {
	private HSSFCellStyle editableCellStyle;
	private HSSFCellStyle nonEditableCellStyle;
	private HSSFWorkbook workbook = new HSSFWorkbook();
	private HSSFCellStyle headerstyle;
	private CellStyle unlockedCellStyle;
	private static LEXLogger LOGGER = LEXLogger.getLEXLogger(ExcelExporter.class);
	private static String rowsEditable=PropertiesMessageUtil.getCommonPorperty("com.lexmark.resources.common","excel.rows.editable");
	private Locale locale;
	private static String DEBRIEFSTATUS_SIEBELVALUE="LXK MPS Action Import BC.LXK MPS Debrief Method";
	public void setLocale(Locale locale) {
		this.locale = locale;
	}
	private Integer rowNumber=0;
	/**
	 * Excel Edporter
	 */
	public ExcelExporter(){
		LOGGER.enter("ExcelExporter.java", "ExcelExporter");
		this.editableCellStyle = this.workbook.createCellStyle();
		this.editableCellStyle.setLocked(false); //true or false based on the cell.
		this.editableCellStyle.setFillForegroundColor(HSSFColor.LIGHT_GREEN.index);
		this.editableCellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		setStyle(this.editableCellStyle);
		
		this.nonEditableCellStyle = this.workbook.createCellStyle();
		this.nonEditableCellStyle.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
		this.nonEditableCellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		setStyle(this.nonEditableCellStyle);
		
		HSSFFont headerFont = this.workbook.createFont();
		headerFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);	
		
		this.headerstyle=workbook.createCellStyle();
		this.headerstyle.setFont(headerFont);
		setStyle(this.headerstyle);
		
		this.unlockedCellStyle= this.workbook.createCellStyle();
		this.unlockedCellStyle.setLocked(false);
		LOGGER.exit("ExcelExporter.java", "ExcelExporter");
	}
	
	/**
	 * @param cellStyle 
	 */
	private void setStyle(HSSFCellStyle cellStyle){
		LOGGER.enter("ExcelExporter.java", "setStyle");
		cellStyle.setWrapText(true);
		cellStyle.setBorderTop((short) 1); 
		cellStyle.setBorderBottom((short) 1);
		cellStyle.setBorderLeft((short) 1);
		cellStyle.setBorderRight((short) 1);
		LOGGER.exit("ExcelExporter.java", "setStyle");
	}
	
	/**
	 * @param dataList 
	 * @param templateInformation 
	 * @param firstSheet 
	 */
	public void generateExcel(List<?> dataList,TemplateInformation templateInformation,HSSFSheet firstSheet){
		LOGGER.enter("ExcelExporter.java", "generateExcel");
		
		rowNumber++;
		
		for(Object obj:dataList){
			
			List<?> listFieldOfMaxSize=null;//this will be list field who has maximum length
			ListFieldDetail fieldDetailOfMaxSize=null;
			Integer rowNumberBackUp= new Integer(rowNumber);//its the starting row number need to be saved as for other lists it should start from here
			/*LOGGER.debug(" [Activity ID] ="+readFromObject(obj, "serviceRequest.asset.activityNumber"));
			if(BusinessObjectUtil.readPropertyList(obj,"serviceRequest.asset.pageCounts")==null){
				LOGGER.debug("[ page Counts =] 0");	
			}else{
				LOGGER.debug("[ page Counts else =]"+((List<?>)BusinessObjectUtil.readPropertyList(obj,"serviceRequest.asset.pageCounts")).size());
			}*/
			
			for(ListFieldDetail listFieldDetail:templateInformation.getListFieldDetail()){
				
				List<?> tempListField=(List<?>)BusinessObjectUtil.readPropertyList(obj,listFieldDetail.getBeanName());
				LOGGER.debug("listField Detail loop "+ listFieldDetail.getBeanName() +" value= "+tempListField);
				if(tempListField !=null && !tempListField.isEmpty()){
					LOGGER.debug("templist field is not empty ..."+tempListField.size());
					
					if(listFieldOfMaxSize==null){
						LOGGER.debug("in listFieldOfMaxSize is null");
						fieldDetailOfMaxSize=listFieldDetail;
						listFieldOfMaxSize=tempListField;
					}else if(listFieldOfMaxSize.size()<tempListField.size()){
						LOGGER.debug("listFieldOfMaxSize < tempListField"+ listFieldOfMaxSize.size()+ " " +tempListField.size());
						fieldDetailOfMaxSize=listFieldDetail;
						listFieldOfMaxSize=tempListField;//setting the list which has max size
						
					}
				}
				
			}
			LOGGER.debug("listFieldOfMaxSize="+listFieldOfMaxSize + "fieldDetailOfMaxSize= "+fieldDetailOfMaxSize);
			/**
			 * This condition will occur if 
			 * all the lists is null or empty
			 * then listFieldOfMaxSize will be null and 
			 * fieldDetailOfMaxSize will be null,
			 * So need to check and assign the first list type value to it,
			 * So that the template generates with empty cells @ServiceOrder Issue
			 * */
			if(fieldDetailOfMaxSize==null){
				LOGGER.debug("[Since all lists are null setting the first list to fieldDetailOfMaxSize]");
				fieldDetailOfMaxSize=templateInformation.getListFieldDetail().get(0);
			}
			/**
			 * List which has maximum size is written over here
			 * 
			 * */
			
			
			if(listFieldOfMaxSize!=null && !listFieldOfMaxSize.isEmpty()){
				for(Object objList:listFieldOfMaxSize){
					//LOGGER.debug("[in list field loop starts ]");
					
						HSSFRow row=firstSheet.createRow(rowNumber);
					/**
					 * This writes the non List fields
					 * */	
					writeValuesLoop(templateInformation.getField(),firstSheet,obj,row);
					
					//LOGGER.debug("[before writing list fields ]");
					
					/**
					 * This writes the list fields whose 
					 * list length is maximum that is available from the above 
					 * section  
					 * */
					writeValuesLoop(fieldDetailOfMaxSize.getField(),firstSheet,objList,row);
					
					//LOGGER.debug("[rowNumber = ]"+rowNumber);
					rowNumber++;
				}
				LOGGER.debug("[in list field loop ends ]");
			}else{
				HSSFRow row=firstSheet.createRow(rowNumber);
				
				/**
				 * This will create empty cells for the list Field 
				 * */
				writeValuesLoop(fieldDetailOfMaxSize.getField(),firstSheet,null,row);
				
				
				/*for(Field field:fieldDetailOfMaxSize.getField()){
					readValueAndCreateCell(firstSheet,field,null,row);//
				}*/
				
				/**
				 * This will write non list fields
				 * */	
				writeValuesLoop(templateInformation.getField(),firstSheet,obj,row);
				/*for(Field field:templateInformation.getField()){
					readValueAndCreateCell(firstSheet,field,obj,row);//
				}*/
				rowNumber++;
			}
			/**
			 * Lists of smaller size are written over here in excel
			 * */
			//This section is for writing the other cells of lists which is of lesser size 
			for(ListFieldDetail listFieldDetail:templateInformation.getListFieldDetail()){
				LOGGER.debug("listFieldDetail.getBeanName() = "+listFieldDetail.getBeanName());
				
				//The beanName should not be equal to the fieldDetailOfMaxSize since it will be processed after
				if(!(listFieldDetail.getBeanName().equalsIgnoreCase(fieldDetailOfMaxSize.getBeanName()))){
					
					List<?> listFieldObj=(List<?>)BusinessObjectUtil.readPropertyList(obj,listFieldDetail.getBeanName());
					
					LOGGER.debug("objList=="+listFieldObj);
					if(listFieldObj!=null && !listFieldObj.isEmpty()){
						for(Object objList:listFieldObj){
							HSSFRow row=getRow(rowNumberBackUp,firstSheet);
							
							LOGGER.debug("rowNumberBackUp = "+rowNumberBackUp);
							
							/**
							 * This will write list fields
							 * */	
							writeValuesLoop(listFieldDetail.getField(),firstSheet,objList,row);
							
							/*for(Field field:listFieldDetail.getField()){							
								readValueAndCreateCell(firstSheet,field,objList,row);
							}*/
							rowNumberBackUp++;
						}
					}else{
						LOGGER.debug("[ listFieldObj is null for field "+listFieldDetail.getBeanName()+" row Number ="+rowNumberBackUp+"]");
						LOGGER.debug("[need to create empty cells]");
						HSSFRow row=getRow(rowNumberBackUp,firstSheet);
						writeValuesLoop(listFieldDetail.getField(),firstSheet,null,row);
						rowNumberBackUp++;
					}
					
					// This will make rest of the fields editable for the listFieldObj for that particular list obj
					for(int i=0;i<(listFieldOfMaxSize==null?0:listFieldOfMaxSize.size()-(listFieldObj==null?0:listFieldObj.size()));i++){
						HSSFRow row=getRow(rowNumberBackUp,firstSheet);
						/**
						 * This will write list fields to equate 
						 * to the maximum list field so that the 
						 * cells gets created.
						 * */	
						writeValuesLoop(listFieldDetail.getField(),firstSheet,null,row);
						/*for(Field field:listFieldDetail.getField()){							
							readValueAndCreateCell(firstSheet,field,null,row);
						}*/
						rowNumberBackUp++;
					}
					
					
					
				}
			}
			
			
			
		}
		LOGGER.exit("ExcelExporter.java", "generateExcel");
	}
	
	
	/**
	 * @param fields 
	 * @param sheet 
	 * @param obj 
	 * @param row 
	 */
	private void writeValuesLoop(List<Field> fields,HSSFSheet sheet,Object obj,HSSFRow row){
		for(Field field:fields){
			readValueAndCreateCell(sheet,field,obj,row);
		}
	}
	
	/**
	 * @param rowNumber 
	 * @param firstSheet 
	 * @return row 
	 */
	private HSSFRow getRow(int rowNumber,HSSFSheet firstSheet){
		HSSFRow row=firstSheet.getRow(rowNumber);
		if(row==null){
			row=firstSheet.createRow(rowNumber);
		}
		return row;
	}
	
	/**
	 * @param firstSheet 
	 * @param field 
	 * @param obj 
	 * @param row 
	 */
	private void readValueAndCreateCell(HSSFSheet firstSheet,Field field,Object obj,HSSFRow row){
		
		String fieldValue=readFromObject(obj, field.getName());
			
		HSSFCell cell=row.createCell(field.getColumnNumber());
		/*if(field.getDisplayOption()!=null && field.getDisplayOption().getType().equalsIgnoreCase("boolean")){
			LOGGER.debug("it is boolean");
			List<DisplayValue> displayOptions=field.getDisplayOption().getDisplayValue();
			for(DisplayValue displayVal:displayOptions){
				LOGGER.debug("display options field Val="+ fieldValue+"bean vlaue="+displayVal.getBeanValue());
				if(displayVal.getBeanValue().equalsIgnoreCase(fieldValue)){
					LOGGER.debug("display value and bean value same");
					fieldValue=displayVal.getDisplayValue();
				}
			}
		}*/
	    cell.setCellValue(new HSSFRichTextString(fieldValue));
	    cell.setCellType(HSSFCell.CELL_TYPE_STRING);
	    cell.setCellStyle(field.isEditable()==true?editableCellStyle:nonEditableCellStyle);
	    //LOGGER.debug("cell created for "+field.getName()+" value="+fieldValue +" cell Number="+field.getColumnNumber());
	    
	}
	
	
	
	/**
	 * @param templateInformation 
	 * @param firstSheet 
	 * @param rowNumber 
	 * @param siebelORExcel - this will determine which to write Excel Header to template or Siebel Header to template
	 * 						  if true = Excel Header , false = Siebel Header
	 */
	private void writeExcelHeader(TemplateInformation templateInformation,
			HSSFSheet firstSheet,int rowNumber,boolean siebelORExcel){
		LOGGER.enter("ExcelExporter.java", "writeExcelHeader");
		HSSFRow row=firstSheet.createRow(rowNumber);
		for(Field field:templateInformation.getAllFields()){
			firstSheet.setColumnWidth(field.getColumnNumber(), 2900);
			
			if(field.getSiebelHeader().equalsIgnoreCase(DEBRIEFSTATUS_SIEBELVALUE)){
				firstSheet.setColumnWidth(field.getColumnNumber(), 0);
			}
					
			
			writeHeaderCell(firstSheet,row,field.getColumnNumber(),siebelORExcel==true?field.getCsvHeader():field.getSiebelHeader(),siebelORExcel);
		}
		/*for(Field field:templateInformation.getField()){
			firstSheet.setColumnWidth(field.getColumnNumber(), 2900);
			writeHeaderCell(firstSheet,row,field.getColumnNumber(),siebelORExcel==true?field.getCsvHeader():field.getSiebelHeader());
		}
		for(Field field:templateInformation.getListFieldDetail().getField()){
			firstSheet.setColumnWidth(field.getColumnNumber(), 2900);
			writeHeaderCell(firstSheet,row,field.getColumnNumber(),siebelORExcel==true?field.getCsvHeader():field.getSiebelHeader());			
		}*/
		LOGGER.exit("ExcelExporter.java", "writeExcelHeader");
		
	}
	
	/**
	 * @param firstSheet 
	 * @param row 
	 * @param columnNumber 
	 * @param columnValue 
	 * @param siebelORExcel 
	 */
	private void writeHeaderCell(HSSFSheet firstSheet,HSSFRow row,int columnNumber,String columnValue,Boolean siebelORExcel){
		LOGGER.enter("ExcelExporter.java", "writeHeaderCell");		
		HSSFCell cell=row.createCell(columnNumber);
		if(siebelORExcel)
		{  
			String columnValueTranslated=PropertiesMessageUtil.getPropertyMessage("com.lexmark.resources.messages",columnValue,locale);
			if(columnValueTranslated!=null){
			cell.setCellValue(new HSSFRichTextString(columnValueTranslated));
			}
		}
		else
		{
	    cell.setCellValue(new HSSFRichTextString(columnValue));
		}
	    cell.setCellStyle(headerstyle);
	    //LOGGER.exit("ExcelExporter.java", "writeHeaderCell");
	}
	
	/**
	 * Return the formatted values in String format 
	 * @param object  
	 * @param column 
	 * @return string 
	 */ 
	private String readFromObject(Object object,String column){
		//LOGGER.enter("ExcelExporter.java", "readFromObject");
		//LOGGER.debug("column = "+column);
		if(StringUtils.isBlank(column) || object == null){
			return "";
		}
		Object value=null;
		try {
			value=PropertyUtils.getProperty(object, column);
		} catch (IllegalAccessException e) {
			LOGGER.debug("Exception"+e.getMessage()); 
			// TODO Auto-generated catch block
			//LOGGER.error("[IllegalAccessException]"+column);
		} catch (InvocationTargetException e) {
			LOGGER.debug("Exception"+e.getMessage()); 
			/// TODO Auto-generated catch block
			//LOGGER.error("[InvocationTargetException]" +column);
		} catch (NoSuchMethodException e) {
			LOGGER.debug("Exception"+e.getMessage()); 
			// TODO Auto-generated catch block
			//LOGGER.error("[NoSuchMethodException]" + column);
		}catch (NestedNullException npe){
			LOGGER.debug("Exception"+npe.getMessage()); 
			//LOGGER.error("[NestedNullException]"+column);
		}
		
		//LOGGER.exit("ExcelExporter.java", "readFromObject");
		if(value==null){
			return "";
		}else if(value instanceof String){
			//LOGGER.debug("columnValue="+(String)value);
			return (String)value;
		}else if(value instanceof Date){
			return DateUtil.convertDateToGMTString((Date) value);			
		}else if (value instanceof Boolean){
			return (((Boolean)value)==true?"Y":"N");
		}
		else{
			return value.toString();
		}
		
		
		
	}
	
	
	
	/**
	 * @param sheet  
	 * @param startRow  
	 * @param templateInformation  
	 */
	private void makeRestRowsEditable(HSSFSheet sheet,int startRow,TemplateInformation templateInformation){
		LOGGER.enter("ExcelExporter.java", "makeRestRowsEditable");
		int numberOfCols=(templateInformation.getAllFields().size())-1;
		LOGGER.debug("making rows editable start row="+startRow+" number of Cols="+numberOfCols);
		int additionalRows=Integer.parseInt(rowsEditable);
		LOGGER.debug("additional Rows"+additionalRows);
		for(int i=startRow;i<startRow+additionalRows;i++){
			HSSFRow row = sheet.createRow(i);			
			for(int j=0;j<numberOfCols;j++){
				HSSFCell cell=row.createCell(j);
				cell.setCellStyle(unlockedCellStyle);
			}
		}
		
		LOGGER.exit("ExcelExporter.java", "makeRestRowsEditable");
	}
	
	/**
	 * @param firstSheet  
	 * @param response  
	 * @param information  
	 */
	private void writeExcelResponse(HSSFSheet firstSheet,ResourceResponse response,TemplateInformation information){
		LOGGER.enter("ExcelExporter.java", "writeExcelResponse");
		OutputStream opStream=null;
		firstSheet.protectSheet("");
		
		LOGGER.debug("file key= "+information.getFileName());
		response.setProperty("Content-disposition", "attachment; filename=\"" + DownloadFileLocalizationUtil.getPropertyLocaleValue(information.getFileName(),response.getLocale())+".xls"+"\"");
		response.setContentType("application/xls");
		try {
			opStream=response.getPortletOutputStream();
			workbook.write(opStream);
		} catch (IOException e) {
			LOGGER.debug("Exception"+e.getMessage()); 
			LOGGER.debug("In IOException");
		} finally {
	        if (opStream != null) {
	            try {
	            	opStream.flush();
	            	opStream.close();
	            } catch (IOException e) {
	            	LOGGER.error("error occured "+e.getMessage());
	            }
	        }
		}
		LOGGER.exit("ExcelExporter.java", "writeExcelResponse");
	}
	
	/**
	 * @param firstSheet 
	 * This method puts information about the color and which 
	 * color represents Editable field and which one is non editable.
	 */
	private void putHeaderInformationInTemplate(HSSFSheet firstSheet){
		LOGGER.enter("ExcelExporter.java", "putHeaderInformationInTemplate");
		HSSFRow noteEdit = firstSheet.createRow(1);
		HSSFCellStyle noteEditstyle = workbook.createCellStyle();
		noteEditstyle.setFillForegroundColor(HSSFColor.LIGHT_GREEN.index);
		noteEditstyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		setStyle(noteEditstyle);
		
		HSSFCell noteEditCell = noteEdit.createCell(1);
		noteEditCell.setCellStyle(noteEditstyle);
		
		HSSFCell noteEditCell2 = noteEdit.createCell(2);
		noteEditCell2.setCellValue(new HSSFRichTextString("Editable"));

	    // Creating note for Non-Editable fields
		HSSFRow noteNonEdit = firstSheet.createRow(2);
		HSSFCellStyle noteNonEditstyle = workbook.createCellStyle();
		noteNonEditstyle.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
		noteNonEditstyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		setStyle(noteNonEditstyle);
		
		HSSFCell noteNonEditCell = noteNonEdit.createCell(1);
		noteNonEditCell.setCellStyle(noteNonEditstyle);
		HSSFCell noteNonEditCell2 = noteNonEdit.createCell(2);
		noteNonEditCell2.setCellValue(new HSSFRichTextString("Non Editable"));
		LOGGER.exit("ExcelExporter.java", "putHeaderInformationInTemplate");
	}
	
	/**
	 * @param sheet 
	 * @param dropDownValuesForExcel 
	 */
	private void setDropDownValues(HSSFSheet sheet,Map<Integer,String[]> dropDownValuesForExcel){
		LOGGER.enter("ExcelExporter.java", "setDropDownValues");
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
			LOGGER.debug("startRow="+startRow+"hiddenRowNumber="+hiddenRowNumber+"key="+key);
			 HSSFName namedCell = workbook.createName();
			 namedCell.setNameName("hidden"+key);
			 namedCell.setRefersToFormula("hidden!$A$"+startRow+":$A$" + hiddenRowNumber);
			
		
			 DVConstraint constraint = DVConstraint.createFormulaListConstraint("hidden"+key);
			 //@SuppressWarnings("deprecation")
			CellRangeAddressList addressList = new CellRangeAddressList(7, Integer.parseInt(rowsEditable), key,key);
			 HSSFDataValidation validation = new HSSFDataValidation(addressList, constraint);
			 sheet.addValidationData(validation);
		}
		LOGGER.exit("ExcelExporter.java", "setDropDownValues");
		
	}
	
	/**
	 * @param dataList 
	 * @param templateInformation 
	 * @param response 
	 */
	public void generateServiceOrderTemplate(List<?> dataList,
			TemplateInformation templateInformation,ResourceResponse response){
		LOGGER.enter("ExcelExporter.java", "generateServiceOrderTemplate");
		HSSFSheet firstSheet = workbook.createSheet("ServiceOrder");
		try{
		putHeaderInformationInTemplate(firstSheet);
				
		//This is to write the Siebel Header
		rowNumber=5;
		writeExcelHeader(templateInformation,firstSheet,rowNumber,false);
		//This is to write the Excel Header
		writeExcelHeader(templateInformation,firstSheet,++rowNumber,true);
		generateExcel(dataList, templateInformation, firstSheet);
		makeRestRowsEditable(firstSheet,rowNumber,templateInformation);
		writeExcelResponse(firstSheet,response,templateInformation);
		}catch(Exception e){
			LOGGER.debug("In Exception"+e.getMessage());
		}
		LOGGER.exit("ExcelExporter.java", "generateServiceOrderTemplate");
	}
	
	/**
	 * @param dataList 
	 * @param templateInformation 
	 * @param dropDownValuesForExcel 
	 * @param response 
	 */
	public void generateHardwareDebriefTemplate(List<?> dataList,
			TemplateInformation templateInformation,Map<Integer,String[]> dropDownValuesForExcel,ResourceResponse response){
		LOGGER.enter("ExcelExporter.java", "generateHardwareDebriefTemplate");
		HSSFSheet firstSheet = workbook.createSheet("HardwareDebrief");
		try{
		putHeaderInformationInTemplate(firstSheet);
				
		//This is to write the Siebel Header
		rowNumber=5;
		//This is to write the Excel Header
		writeExcelHeader(templateInformation,firstSheet,++rowNumber,true);
		generateExcel(dataList, templateInformation, firstSheet);
		setDropDownValues(firstSheet,dropDownValuesForExcel);
		setNumericConstraints(firstSheet,"0",null,28);// Quantity
		setNumericConstraints(firstSheet,"0",null,29);// Used Quantity
		setNumericConstraints(firstSheet,"0",null,30);// Not Used Quantity
		setNumericConstraints(firstSheet,"0",null,31);// DOA Quantity
		setNumericConstraints(firstSheet,"0",null,62);// Page Count
		setNumericConstraints(firstSheet,"0",null,46);// Port Number
		setEmailConstraints(firstSheet,10);// Email Address
		setIPConstraints(firstSheet,"0","255",36);// IP Address
		makeRestRowsEditable(firstSheet,rowNumber,templateInformation);
		writeExcelResponse(firstSheet,response,templateInformation);
		}catch(Exception e){
			LOGGER.debug("In Exception"+e.getMessage());
		}
		LOGGER.exit("ExcelExporter.java", "generateHardwareDebriefTemplate");
	}
	
	/**
	 * @param sheet
	 * @param lowerLimit
	 * @param higherLimit
	 * @param columnIndex
	 */
	private void setNumericConstraints(HSSFSheet sheet,String lowerLimit,String higherLimit,int columnIndex){
		DVConstraint numericConstraint = DVConstraint.createNumericConstraint(
			    DVConstraint.ValidationType.INTEGER,DVConstraint.OperatorType.GREATER_THAN, lowerLimit,higherLimit);
		CellRangeAddressList addressList = new CellRangeAddressList(7, Integer.parseInt(rowsEditable),columnIndex, columnIndex);
		HSSFDataValidation validation1 = new HSSFDataValidation(addressList, numericConstraint);
		String errorMsg=PropertiesMessageUtil.getPropertyMessage("com.lexmark.resources.messages","exception.errorMsg.numeric",locale);
		validation1.setErrorStyle(ErrorStyle.STOP);
		validation1.createErrorBox("Error", errorMsg + lowerLimit);
		sheet.addValidationData(validation1);
	}
	
	/**
	 * @param sheet
	 * @param columnIndex
	 */
	private void setEmailConstraints(HSSFSheet sheet, int columnIndex){
		LOGGER.debug("setEmailAddressConstraints = " + columnIndex);
		for(Row r : sheet) {
			Cell c = r.getCell(10);
			if(c != null) {
		      if(c.getCellType() == Cell.CELL_TYPE_STRING) {
		    	 LOGGER.debug("In IF Condition === ");
		    	 String formula = "COUNTIF($K1,\"?*@?*.??*\")";
		         CellRangeAddressList addressList = new CellRangeAddressList(7, Integer.parseInt(rowsEditable),columnIndex, columnIndex);
				 DVConstraint	emailConstraint = DVConstraint.createCustomFormulaConstraint(formula);
				 HSSFDataValidation validation2 = new HSSFDataValidation(addressList, emailConstraint);
				 String errorMsg=PropertiesMessageUtil.getPropertyMessage("com.lexmark.resources.messages","exception.errorMsg.emailAddress",locale);
				 validation2.setErrorStyle(ErrorStyle.STOP);
				 validation2.createErrorBox("Error", errorMsg);
				 sheet.addValidationData(validation2);
		      } else if(c.getCellType() == Cell.CELL_TYPE_FORMULA && c.getCachedFormulaResultType() == Cell.CELL_TYPE_STRING) {
		    	  LOGGER.debug("In ELSE IF Condition === ");
		    	}
		   }
		}
	}
	
	/**
	 * @param sheet
	 * @param lowerLimit
	 * @param higherLimit
	 * @param columnIndex
	 */
	private void setIPConstraints(HSSFSheet sheet, String lowerLimit,String higherLimit, int columnIndex){
		LOGGER.debug("setIPAddressConstraints = " + columnIndex);
		for(Row r : sheet) {
			Cell c = r.getCell(36);
			if(c != null) {
		      if(c.getCellType() == Cell.CELL_TYPE_STRING) {
		    	 LOGGER.debug("In IF Condition === ");
		    	 String formula = "AND((LEN($AK1)-LEN(SUBSTITUTE($AK1,\".\",\"\")))=3,ISNUMBER(SUBSTITUTE($AK1,\".\",\"\")+0))";
		         CellRangeAddressList addressList = new CellRangeAddressList(7, Integer.parseInt(rowsEditable),columnIndex, columnIndex);
				 DVConstraint	ipConstraint = DVConstraint.createCustomFormulaConstraint(formula);
				 HSSFDataValidation validation2 = new HSSFDataValidation(addressList, ipConstraint);
				 String errorMsg=PropertiesMessageUtil.getPropertyMessage("com.lexmark.resources.messages","exception.errorMsg.ipAddress",locale);
				 validation2.setErrorStyle(ErrorStyle.STOP);
				 validation2.createErrorBox("Error", errorMsg);
				 sheet.addValidationData(validation2);
		      } else if(c.getCellType() == Cell.CELL_TYPE_FORMULA && c.getCachedFormulaResultType() == Cell.CELL_TYPE_STRING) {
		    	  LOGGER.debug("In ELSE IF Condition === ");
		    	}
		   }
		}
	}
	
}
