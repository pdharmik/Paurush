package com.lexmark.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import com.sun.org.apache.bcel.internal.generic.NEW;

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
	public static ColumnInfo[] ActivityColumnInfos = null;
	public static ColumnInfo[] ClaimColumnInfos = null;
	public static String activityHeader = null;
	public static String claimsHeader = null;
	private static Map<String, ColumnInfo> nameColumnMapActivity = new HashMap<String, ColumnInfo>();
	private static Map<String, ColumnInfo> nameColumnMapClaims = new HashMap<String, ColumnInfo>();

	
	
	public static ColumnInfo[] getColumnInfosActivity() {
		if (ActivityColumnInfos != null)
			return ActivityColumnInfos;
		init();
		return ActivityColumnInfos;
	}

	public static ColumnInfo getColumnInfoActivity(String _name){
		if(ActivityColumnInfos == null){
			init();
		}
		return nameColumnMapActivity.get(_name);
	}
	public static ColumnInfo[] getColumnInfosClaim() {
		if (ClaimColumnInfos != null)
			return ClaimColumnInfos;
		init();
		return ClaimColumnInfos;
	}

	public static ColumnInfo getColumnInfoClaim(String _name){
		if(ClaimColumnInfos == null){
			init();
		}
		return nameColumnMapClaims.get(_name);
	}


	private static void init() {
		ResourceBundle bundle = ResourceBundle
				.getBundle("com.lexmark.resources.assetUploadColumnsInfo");
		activityHeader = bundle.getString("ActivityHeader");
		LOGGER.info("activity header:" +activityHeader);
		claimsHeader = bundle.getString("ClaimsHeader");
		String[] activityColumns = bundle.getString("Activity").split(",");
		ActivityColumnInfos = new ColumnInfo[activityColumns.length];
		LOGGER.info("Activity file lengh:"+activityColumns.length);
		for (int i = 0; i < activityColumns.length; i++) {
			activityColumns[i] = activityColumns[i].trim();
			ActivityColumnInfos[i] = new ColumnInfo();
			ActivityColumnInfos[i].cellIdx = i;
			
		}
		String[] claimColumns = bundle.getString("Claims").split(",");
		ClaimColumnInfos = new ColumnInfo[claimColumns.length];
		LOGGER.info("Claim file lengh:"+claimColumns.length);
		for (int i = 0; i < claimColumns.length; i++) {
			claimColumns[i] = claimColumns[i].trim();
			ClaimColumnInfos[i] = new ColumnInfo();
			ClaimColumnInfos[i].cellIdx = i;
			
		}
	}
}
		
		/**for (int i = 0; i < ActivityColumnInfos.length; i++) {
			ColumnInfo column = ActivityColumnInfos[i];
			ColumnInfo columnInfo = columnInfos[i];

			String title = bundle.getString("Activity." + column + ".title");
			columnInfo.title = title;
		}
	}
}
			
			//String type = bundle.getString("column." + column + ".type");
			//columnInfo.type = parseType(type);

			
			/**String lengthString = null; 
			try {
				lengthString = bundle.getString("column." + column + ".Length");
			}catch(Exception e) {
				
			}
			if(lengthString != null && lengthString.length()>0) {
				String[] lengths= lengthString.split(",");
				columnInfo.length = new int[10];
				for(int j=0;j<lengths.length;j++){
					columnInfo.length[j] = Integer.parseInt(lengths[j]);
				}
			}

			String maxLength = null;
			try {
				maxLength = bundle.getString("column." + column
					+ ".maxLength");
			}catch(Exception e) {
				
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
	/**private static int parseType(String value) {
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
	*/
	
	
