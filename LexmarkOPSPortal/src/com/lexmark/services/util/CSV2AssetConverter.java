package com.lexmark.services.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

import com.lexmark.framework.logging.LEXLogger;

public class CSV2AssetConverter {

	public static final boolean HAS_HEADER_LINE = true; 
	private static DateFormat destDateFormat = new SimpleDateFormat("yyyy/MM/dd");
	public static String UP_HEADER_ONE = "#LXK UP MeterRead,,,,,,,,,,";
	private static final LEXLogger LOGGER = LEXLogger.getLEXLogger(CSV2AssetConverter.class);
	
	//brmandal modified for page count changes - MPS phase 2.1
	
	
	public static String UP_HEADER_TWO = "#MeterRead.Product,MeterRead.SerialNumber,MeterRead.LTPC,MeterRead.Color," +
			"MeterRead.A4LTPC,MeterRead.A4Color,MeterRead.A5LTPC,MeterRead.A5Color,MeterRead.Scans,MeterRead.TimeTaken";
	

	public static DateFormat getDestDateFormat() {
		return destDateFormat;
	}

	public static void setDestDateFormat(DateFormat _destDateFormat) {
		destDateFormat = _destDateFormat;
	}

	/**
	 *used for logic
	 * */
	private CSVErrorHanlder errorHanlder = new CSVErrorHanlder();
	private BufferedReader reader;
	private Locale locale;
	private ColumnInfo[] columnInfos;
	private List<String[]> results = new ArrayList<String[]>();
	private int rowCount = 1;
	public List<String[]> getResults() {
		return results;
	}

	public CSV2AssetConverter(BufferedReader _reader, Locale _locale) {
		this.columnInfos = ColumnInfo.getColumnInfos();
		this.reader = _reader;
		locale = _locale;
	}

	public boolean processData(){
		String line;
		try {
			// Right now, we have to set HEADERLINE true,  the testers are using file with header 
			boolean headerLine = true;
			while ((line = reader.readLine()) != null) {
				if(line.indexOf(UP_HEADER_ONE) > -1) {
					continue;
				}
				StringTokenizer stokenizer = new StringTokenizer(line, "\\" );
			    while( stokenizer.hasMoreElements() ){
			    	if(HAS_HEADER_LINE && headerLine ==true){
			    		headerLine = false;
			    		if(processHead(stokenizer.nextToken())){
			    			rowCount++;
				    		continue;
			    		}else {
							return false;
						}
			    	}
			    	String[] cellArray =  stokenizer.nextToken().split("[,]");
			    	boolean validation = processRow(cellArray);
			    	if(!validation)
			    		return false;
			    	
			    	rowCount++;
			    }
			}
		} catch (IOException e) {
			LOGGER.debug("IO Exception "+e.getMessage());
			return false;
		}
		return true;
	}
	private boolean processHead(String header){
		if(header != null && header.indexOf(UP_HEADER_TWO) > -1) {
			return true;
		}
		String[] headerArray =  header.split("[,]");
		if(columnInfos.length == headerArray.length ||columnInfos.length == (headerArray.length+1) || headerArray.length >= 10)
			return true;
		else if(headerArray.length < (columnInfos.length-1)){
			addError(CSVErrorHanlder.MSG_KEY_TOO_FEW_COLUMNS);
			return false;
		}else {
			addError(CSVErrorHanlder.MSG_KEY_TOO_MUCH_COLUMNS);
			return false;
		}
		
	}
	private boolean processRow(String[] cellArray) {
		String[] tempCellList = new String[columnInfos.length];
		if(columnInfos.length<cellArray.length){
			addError(CSVErrorHanlder.MSG_KEY_TOO_MUCH_COLUMNS);
			return false;
		}
		int cellArrLen = cellArray.length;
		for(int cellIndex = 0;cellIndex < cellArrLen;cellIndex++){
			tempCellList[cellIndex]=cellArray[cellIndex];
		}
		for (ColumnInfo columnInfo : columnInfos) {
			String cell = tempCellList[columnInfo.cellIdx];
			if(!validateNecessary(cell,columnInfo))
				return false;
			String sValue = CSVUtil.getCellValueAsString(cell,
					columnInfo.type, locale);
			sValue = sValue == null ? "" : sValue.trim();
			
			if(!validateValue(sValue , columnInfo,cell)){
    			return false;
    		}
			tempCellList[columnInfo.cellIdx] = sValue;
		}
		results.add(tempCellList);
		return true;
	}
	private boolean validateValue(String _value,ColumnInfo columnInfo,String _cell){
		
		if(Pattern.compile("\"").matcher(_value).find() ||_value.equals(CSVUtil.INVALID_CELL_TYPE_OR_VALUE)){
			addError(CSVErrorHanlder.MSG_KEY_INVALID_CELL_VALUE,_cell ,columnInfo.cellIdx);
			return false;
		}
		switch (columnInfo.type) {
		case ColumnInfo.DATA_TYPE_NUMBER:{
			if(columnInfo.length != null) {
				for(int length:columnInfo.length){
					if( length ==_value.length()){
						return true;
					}
				}
				addError(CSVErrorHanlder.MSG_KEY_VALUE_MUST_BE_71113,_cell,columnInfo.cellIdx);
				return false;
			}
		}
		case ColumnInfo.DATA_TYPE_DATE:
			return true;
		case ColumnInfo.DATA_TYPE_STRING:{
			if(columnInfo.length != null) {
				for(int length:columnInfo.length){
					//" if "added for LEX:AIR00068536 by AMS
					if(null != columnInfo.title || columnInfo.title != ""){
						if(columnInfo.title.equals("Serial Number"))
						{
							if( _value.length() >=4){
								return true;
						
							}
						
						else { 
							if( length ==_value.length()){
								return true;
							}
						}
							addError(CSVErrorHanlder.MSG_KEY_VALUE_MUST_BE_71113,_cell,columnInfo.cellIdx);
							return false;
						}
						if(columnInfo.title.equals("Product Line"))
						{
							if( _value.length() >=4){
								return true;
						
							}
						
						else { 
							if( length ==_value.length()){
								return true;
							}
						}
							addError(CSVErrorHanlder.MSG_KEY_VALUE_MUST_BE_BELOW_64000,_cell,columnInfo.cellIdx);
							return false;
						}
						
						
						
					}
				}
				
			}
			
			if(_value.length()<=columnInfo.maxLength)
				return true;
			else{
				addError(CSVErrorHanlder.MSG_KEY_VALUE_TOO_LONG , _value ,columnInfo.cellIdx);
				return false;
			}
			}
		case ColumnInfo.DATA_TYPE_INT:
			if(columnInfo.maxLength >_value.length())
				return true;
			else{
				addError(CSVErrorHanlder.MSG_KEY_VALUE_TOO_LONG,_cell ,columnInfo.cellIdx);
				return false;
			}
		}
		return false;
	}
	
	private boolean validateNecessary(String _value,ColumnInfo columnInfo){
		if((null==_value||_value.isEmpty())&&columnInfo.necessity ==ColumnInfo.NECESSITY_RERUIRED){
			addError(CSVErrorHanlder.MSG_KEY_EMPTY_CELL,columnInfo.title ,columnInfo.cellIdx);
			return false;
		}
		else 
			return true;
	}
	
	private void addError(String code,String value ,int column) {
		errorHanlder.addError(code, value ,rowCount,(column+1));
	}
	private void addError(String code) {
		errorHanlder.addError(code);
	}
	public ArrayList<CSVError> getErrors(){
		return errorHanlder.getErrors();
	}
}
