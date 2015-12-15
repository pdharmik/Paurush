package com.lexmark.services.util;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.lexmark.domain.ListOfValues;
import com.lexmark.services.constants.PunchoutConstants;

/**
 * @author wipro
 * @version 2.1
 * This class appends values to Json String
 * 
 * */
public class JsonUtil {
	/**
	 * @param key 
	 * @param value 
	 * @param stringBuffer 
	 * @param isArray 
	 * */
	public static void appendToJSON(String key,String value,StringBuffer stringBuffer,boolean isArray){
		if(isArray){
			stringBuffer.append("\""+key+"\":["+value+"],");
		}
		else{
			stringBuffer.append("\""+key+"\":\""+value+"\",");
		}
		
	}
	
	/**
	 * @param map 
	 * @param stringBuffer  
	 * */
	public static void converMapToComboSelect(Map<String,String> map,StringBuffer stringBuffer){
		Set<String> keySet=map.keySet();
		int keyIndex=0;
		for(String key:keySet){
			stringBuffer.append("[\""+key+"\",\""+map.get(key).replaceAll(",", "&#44;")+"\"],");
				if(keyIndex==keySet.size()-1){
					stringBuffer.delete(stringBuffer.length()-1,stringBuffer.length());
				}
				
			keyIndex++;
		}
		
	}
	
	/**
	 * @param lovList 
	 * @return String 
	 */
	public static String generateProductModelJSON(List<ListOfValues> lovList){
		StringBuffer json=new StringBuffer("[");
		if(lovList==null){
			json.append("]");
			return json.toString();
		}
		
		for(ListOfValues value:lovList){
			json.append("{");
			appendToJSON("name",value.getName(),json,false);
			appendToJSON("value",value.getValue(),json,false);
			json.deleteCharAt(json.length()-1);
			json.append("},");
		}
		json.deleteCharAt(json.length()-1);
		json.append("]");
		return json.toString();
	}
	
	/**
	 * @param params 
	 * @return String 
	 */
	public static String generateCartJSON(Map<String,String> params){
		
		StringBuffer json=new StringBuffer("{");
		appendToJSON(PunchoutConstants.CUR_TIME,String.valueOf(System.currentTimeMillis()),json,false);
		//appendToJSON(PunchoutConstants.SIZE,String.valueOf(cartSize),json,false);
		for(String key:params.keySet()){
			appendToJSON(key,params.get(key),json,false);
		}	
		json.deleteCharAt(json.length()-1);
		json.append("}");
		return json.toString();
	}
	
	
	
	/**
	 * @param result  
	 * *//*
	public static String generateJSONForGridSetting(UserGridSettingResult result,Locale locale){
			
		
			StringBuffer json=new StringBuffer("{");
			if(result==null){
				appendToJSON(LexmarkPPConstants.JSON_STATUS,LexmarkPPConstants.FAILURE,json,false);				
			}else{
				appendToJSON(LexmarkPPConstants.JSON_STATUS,LexmarkPPConstants.JSON_STATUS_AVAILABLE,json,false);
				for(String gridParamFromDB:LexmarkPPConstants.gridSavingParams){					
					String columnValue = BusinessObjectUtil.formatColumn(result, gridParamFromDB, locale);
					//LOGGER.debug("field name "+gridParamFromDB+" result "+columnValue);
					appendToJSON(gridParamFromDB,columnValue==null?"":columnValue,json,false);				
				}		
				json.deleteCharAt(json.length()-1);
				json.append("}");
			}
			return json.toString();
	}*/
}
