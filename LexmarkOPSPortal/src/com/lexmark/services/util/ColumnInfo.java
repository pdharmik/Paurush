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
	public static final int DATA_TYPE_STRING = 1;
	public static final int DATA_TYPE_NUMBER = 2;
	public static final int DATA_TYPE_DATE = 3;
	public static final int DATA_TYPE_INT = 4;

	public static final int NECESSITY_OPTIONAL = 1;
	public static final int NECESSITY_RERUIRED = 2;
	public static final int NECESSITY_CONDITIONAL = 3;

	public static final String NOT_NULL = "NOT_NULL";

	public static final int CELL_INDEX_PARTNER_TYPE = 1000001;

	public String title;
	public String nameInDB;
	public int type;
	public int cellIdx;
	public int necessity;
	public int[] length = null;
	public int  maxLength;
	public int necessityConditionFiled;
	public String necessityConditionValue;
	private static ColumnInfo[] columnInfos = null;
	private static Map<String, ColumnInfo> nameColumnMap = new HashMap<String, ColumnInfo>();

	public static ColumnInfo[] getColumnInfos() {
		if (columnInfos != null)
			return columnInfos;
		init();
		return columnInfos;
	}
	
	public static ColumnInfo getColumnInfo(String _name){
		if(columnInfos == null){
			init();
		}
		return nameColumnMap.get(_name);
	}

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
	private static int parseType(String value) {
		int type;
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
	private static int parseNecessity(String value) {
		int necessity;
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