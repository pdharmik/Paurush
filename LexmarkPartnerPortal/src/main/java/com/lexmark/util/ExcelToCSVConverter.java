package com.lexmark.util;



import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.util.NumberToTextConverter;

import com.lexmark.domain.Constraint;
import com.lexmark.domain.Field;
import com.lexmark.domain.TemplateInformation;
import com.lexmark.domain.Value;
import com.lexmark.framework.logging.LEXLogger;


/**
 * @author wipro
 * @version 2.1
 *
 */

public class ExcelToCSVConverter {
	
	private static final LEXLogger LOGGER = LEXLogger
	.getLEXLogger(ExcelToCSVConverter.class);
	private static final String CLASSNAME="ExcelToCSVConverter.java";
	private static final String CSV=".csv";
	private static String DEBRIEFSTATUS_SIEBELVALUE="LXK MPS Action Import BC.LXK MPS Debrief Method";
	private static String DEBRIEFSTATUS_VALUE="Ready for Validation";
	private static String PORTALSTATUS="Portal Uploader";
	private static int DEBRIEFSTATUS_COL_NO=68;

	/**
	 * @param destinationPath 
	 * @param inputStream 
	 * @param templateInformation 
	 * @param ignoreRows 
	 * @throws IOException 
	 */
	public static void convert(String destinationPath,
			InputStream inputStream,
			TemplateInformation templateInformation,int ignoreRows)throws IOException{
		LOGGER.enter(CLASSNAME, "convert");
		try{
		//File file=new File("C:\\MassUploadTemplate.xls");
		BufferedInputStream bufferedInputStream=null;
		bufferedInputStream= new BufferedInputStream(inputStream);
		
		//Get the workbook instance for XLS file
		HSSFWorkbook workbook=null;
	
			workbook = new HSSFWorkbook(bufferedInputStream);
	
			// TODO Auto-generated catch block
	
		 
		//Get first sheet from the workbook
		HSSFSheet sheet = workbook.getSheetAt(0);
		
		
		//Get iterator to all the rows in current sheet
		Iterator<?> rowIterator = sheet.rowIterator();
		
		List<Field> fields=templateInformation.getAllFields();
		
		StringBuffer sb=new StringBuffer(templateInformation.getFirstLineCSV());
		sb.append("\n");
		///sb.append(csvHeader);
		sb.append(getCSVHeader(fields));
		sb.append("\n");
		int i=0;
		while(rowIterator.hasNext()){
			if(i++<=ignoreRows){
				rowIterator.next();
				continue;
			}
			
			HSSFRow row=(HSSFRow)rowIterator.next();
			HSSFCell firstCell=row.getCell(0);//this contains the SR Number
			//LOGGER.debug("firstCell"+firstCell);
			if(firstCell != null && StringUtils.isNotBlank(firstCell.getRichStringCellValue().getString())){
				for(Field field:fields){
					
					if(StringUtils.isNotBlank(field.getSiebelHeader())){
						//LOGGER.debug("field.getColumnNumber()->" +field.getColumnNumber());
						HSSFCell cellObj= row.getCell(field.getColumnNumber());
						String finalValue="";
						double value;
						if(cellObj.getCellType()==HSSFCell.CELL_TYPE_NUMERIC){
							//LOGGER.debug("cell type is numeric");
							if(DateUtil.isCellDateFormatted(cellObj)){
								Date date=cellObj.getDateCellValue();	
								finalValue=com.lexmark.util.DateUtil.convertDateToGMTString(date);
							}else{
								value=cellObj.getNumericCellValue();
								finalValue=NumberToTextConverter.toText(value);
							}
						
							
						}else{
							HSSFRichTextString cellValue;
							cellValue=cellObj.getRichStringCellValue();
							Constraint constraint=field.getConstraint();
							if(constraint !=null && constraint.getValue()!=null){
							//	LOGGER.debug("constraint is not null ");
								//LOGGER.debug("list val="+listVal+"index of ="+listVal.indexOf(String.valueOf(cellInd))+" cell ind="+cellInd);
								List<Value> vals=field.getConstraint().getValue();
								finalValue=constraint.getDefaultCSVValue();
								for(Value val:vals){
									if(val.getValue().equalsIgnoreCase(cellValue.getString())){
										finalValue=val.getCsvValue();
										break;
									}
								}
								
							}else{
							//	LOGGER.debug("constraint is null ");
								
								//Added for CR 16358-Starts
								if(field.getSiebelHeader().equalsIgnoreCase(DEBRIEFSTATUS_SIEBELVALUE))
								{
									cellObj= row.getCell(DEBRIEFSTATUS_COL_NO);
									cellValue=cellObj.getRichStringCellValue();
									String stringValue=cellValue.getString();
									LOGGER.debug("DEBRIEF STATUS"+stringValue);
									if(stringValue.equalsIgnoreCase(DEBRIEFSTATUS_VALUE))
									{
										finalValue=PORTALSTATUS;
										
									}
									else{
										finalValue="";
										
										
									}
									
									
								}
								else{
									finalValue=cellValue.getString();
								}
								//Added for CR 16358-Ends
								
							}
							
						}
						
						fillValue(sb,finalValue);
						
					}
					
				}
			//	LOGGER.debug("after end of for ");
				sb.deleteCharAt(sb.length()-1);
				sb.append("\n");
				//LOGGER.debug("deleting character and added line break");
			}
			
			
			//Get iterator to all cells of current row
			///Iterator<?> cellIterator = row.cellIterator();
			
			/*int cellInd=0;
			boolean ignoreRow=false;
			do{
				if(!ignoreCells.contains(String.valueOf(cellInd))){
					HSSFCell cell=(HSSFCell)cellIterator.next();
					double value;
					HSSFRichTextString cellValue;
					String finalValue="";
					//LOGGER.debug("cell type="+cell.getCellType());
					
					if(cell.getCellType()==HSSFCell.CELL_TYPE_NUMERIC){
						if(DateUtil.isCellDateFormatted(cell)){
							Date date=cell.getDateCellValue();	
							finalValue=com.lexmark.util.DateUtil.convertDateToGMTString(date);
						}else{
							value=cell.getNumericCellValue();
							finalValue=NumberToTextConverter.toText(value);
						}
					
						
					}else{
						cellValue=cell.getRichStringCellValue();
						
						if(listVal.equalsIgnoreCase(String.valueOf(cellInd))){
							//LOGGER.debug("list val="+listVal+"index of ="+listVal.indexOf(String.valueOf(cellInd))+" cell ind="+cellInd);
							String tempValue=cellValue.getString();
							finalValue="Recommended1";
							if(tempValue.equalsIgnoreCase("Additional")){
								finalValue="Associated";
							}
								
							
							
						}else{
												
							finalValue=cellValue.getString();
						}
						
					}
					ignoreRow=false;
					if(StringUtils.isBlank(finalValue) && cellInd == 1){
						//CellInd==1 represents Activity Id it should not be blank So ignore the row.
						ignoreRow=true;
						break;
					}
					fillValue(sb,finalValue);
				}else{
					cellIterator.next();
				}
			cellInd++;
				
				
			
			}while(cellIterator.hasNext());
			if(!ignoreRow){
				sb.deleteCharAt(sb.length()-1);
				sb.append("\n");
			}*/
			
			
		}
	
		BufferedWriter bufferedWriter;
		//LOGGER.debug("destination path="+destinationPath);
		OutputStreamWriter outputWriter=new OutputStreamWriter(new FileOutputStream(destinationPath+CSV), "UTF-8");
		
			bufferedWriter = new BufferedWriter(outputWriter);
			bufferedWriter.write(sb.toString());
			bufferedWriter.flush();
			bufferedWriter.close();
		
		}catch(Exception e){
			LOGGER.debug("exception occured "+e.getCause());
		}
		
	}
	
	/**
	 * @param sb 
	 * @param value 
	 */
	private static void fillValue(StringBuffer sb, String value){
		if (value != null) {
			sb.append(processBeforeFilling(value));
		}
		sb.append(",");
	}
	
	/**
	 * Process before filling, append one more " before ", and enclose with "".

	 * @param value 
	 * @return processed string 
	 */
	private static String processBeforeFilling(String value) {
		return StringUtil.encloseWithDoubleQuote(StringUtil.appendDoubleQuote(value));
	}
	
	
	
	
	/**
	 * @param fields 
	 * @return String 
	 */
	private static String getCSVHeader(List<Field> fields){
		
		StringBuffer sb=new StringBuffer();
		for(Field field:fields){
			if(StringUtils.isNotBlank(field.getSiebelHeader())){
				fillValue(sb,field.getSiebelHeader());
			}
		}
		sb.deleteCharAt(sb.length()-1);
		//LOGGER.debug("csv header "+sb.toString());
		return sb.toString();
	}
	/*public static void main(String args[]){
		
		String header="Order Number,Part Number,Part Description,Quantity Requested,Remaining Quantity," +
				"Back Order Qty,Shipped Quantity,Shipped Date,Back Ordered Date,Delivery date,Tracking #,Carrier,Shipment Status,Serial Number,Order Status,Partner Order Ref Number";
		String destinationPath="C:\\test.csv";
		//ExcelToCSVConverter.convert(header, destinationPath);
	}*/

}
