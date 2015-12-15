package com.lexmark.services.util;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

/**
 * Pure Value-Object. All fields are immutable
 * 
 * @author Elmar.Chen
 * 
 */
public class ColumnInfo {
	private static Logger LOGGER = LogManager.getLogger(ColumnInfo.class);
	/** constant for ColumnInfo */
	public static final int DATA_TYPE_STRING = 1;
	/** constant for ColumnInfo */
	public static final int DATA_TYPE_NUMBER = 2;
	/** constant for ColumnInfo */
	public static final int DATA_TYPE_DATE = 3;
	/** constant for ColumnInfo */
	public static final int DATA_TYPE_INT = 4;
	/** constant for ColumnInfo */
	public static final int NECESSITY_OPTIONAL = 1;
	/** constant for ColumnInfo */
	public static final int NECESSITY_RERUIRED = 2;
	/** constant for ColumnInfo */
	public static final int NECESSITY_CONDITIONAL = 3;
	/** constant for ColumnInfo */
	public static final String NOT_NULL = "NOT_NULL";
	/** constant for ColumnInfo */
	public static final int CELL_INDEX_PARTNER_TYPE = 1000001;
	/** properties for ColumnInfo */
	public String title;
	/** properties for ColumnInfo */
	public String nameInDB;
	/** properties for ColumnInfo */
	public int type;
	/** properties for ColumnInfo */
	public int cellIdx;
	/** properties for ColumnInfo */
	public int necessity;
	/** properties for ColumnInfo */
	public int[] length;
	/** properties for ColumnInfo */
	public int  maxLength;
	/** properties for ColumnInfo */
	public int necessityConditionFiled;
	/** properties for ColumnInfo */
	public String necessityConditionValue;
	/** properties for ColumnInfo */
	private static ColumnInfo[] columnInfos ;
	/** Map for ColumnInfo */
	private static Map<String, ColumnInfo> nameColumnMap = new HashMap<String, ColumnInfo>();

	/**
	 * @return ColumnInfo[]  
	 */
	public static ColumnInfo[] getColumnInfos() {
		if (columnInfos != null){
			return columnInfos;
		}
		init();
		return columnInfos;
	}
	
	/**
	 * @param _name 
	 * @return ColumnInfo 
	 */
	public static ColumnInfo getColumnInfo(String _name){
		if(columnInfos == null){
			init();
		}
		return nameColumnMap.get(_name);
	}

	/**
	 * 
	 */
	private static void init() {
		ResourceBundle bundle = ResourceBundle
				.getBundle("com.lexmark.services.resources.assetUploadColumnsInfo");

		String[] columns = bundle.getString("columns").split(",");
		columnInfos = new ColumnInfo[columns.length];
		LOGGER.info("lengh:"+columns.length);
		int columnLen = columns.length;
		for (int i = 0; i < columnLen; i++) {
			columns[i] = columns[i].trim();
			columnInfos[i] = new ColumnInfo();
			columnInfos[i].cellIdx = i;
			LOGGER.info("cellIdx:"+columnInfos[i].cellIdx);
		}
		for (int i = 0; i < columnLen; i++) {
			String column = columns[i];
			ColumnInfo columnInfo = columnInfos[i];

			String title = bundle.getString("column." + column + ".title");
			columnInfo.title = title;
			
			String type = bundle.getString("column." + column + ".type");
			columnInfo.type = parseType(type);

			
			String lengthString = null; 
			try {
				lengthString = bundle.getString("column." + column + ".Length");
			}catch(Exception e) {
				LOGGER.info("Exception occured");
			}
			if(lengthString != null && lengthString.length()>0) {
				String[] lengths= lengthString.split(",");
				columnInfo.length = new int[10];
				int lengthLen = lengths.length;
				for(int j=0;j<lengthLen;j++){
					columnInfo.length[j] = Integer.parseInt(lengths[j]);
				}
			}

			String maxLength = null;
			try {
				maxLength = bundle.getString("column." + column
					+ ".maxLength");
			}catch(Exception e) {
				LOGGER.debug("Excetion occured");
			}
			if(maxLength != null && maxLength.length() > 0) {
				columnInfo.maxLength = Integer.parseInt(maxLength);
			} else {
				columnInfo.maxLength = Integer.MAX_VALUE;
			}

			String necessity = bundle.getString("column." + column
					+ ".necessity");
			columnInfo.necessity = parseNecessity(necessity);
		}
	}
	/**
	 * @param value 
	 * @return int 
	 */
	private static int parseType(String value) {
		int type =0;
		if (value.equals("string")) {
			type = DATA_TYPE_STRING;
		} else if (value.equals("number")) {
			type = DATA_TYPE_NUMBER;
		} else if (value.equals("date")) {
			type = DATA_TYPE_DATE;
		} else if (value.equals("int")) {
			type = DATA_TYPE_INT;
		} else {
			throw new RuntimeException("Invalid value for necessity.");
		}
		return type;
	}
	
	/**
	 * @param value 
	 * @return int 
	 */
	private static int parseNecessity(String value) {
		int necessity=0;
		if (value.equals("conditional")) {
			necessity = NECESSITY_CONDITIONAL;
		} else if (value.equals("required")) {
			necessity = NECESSITY_RERUIRED;
		} else if (value.equals("optional")) {
			necessity = NECESSITY_OPTIONAL;
		} else {
			throw new RuntimeException("Invalid value for necessity.");
		}
		return necessity;
	}
	
}