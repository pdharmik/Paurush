package com.lexmark.services.util;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

import com.lexmark.domain.Bundle;
import com.lexmark.domain.ListOfValues;
import com.lexmark.domain.OrderPart;
import com.lexmark.domain.Part;
import com.lexmark.domain.Price;
import com.lexmark.services.constants.PunchoutConstants;

/**
 * @author wipro
 * @version 2.1
 * This class appends values to Json String
 * 
 * */
public class JsonUtil {
	
	private static Logger LOGGER = LogManager.getLogger(ControllerUtil.class);
	/**
	 * @param key 
	 * @param value 
	 * @param StringBuilder 
	 * @param isArray 
	 * */
	public static void appendToJSON(String key,String value,StringBuilder stringBuilder,boolean isArray){
		if(isArray){
			stringBuilder.append("\""+key+"\":["+value+"],");
		}
		else{
			stringBuilder.append("\""+key+"\":\""+value+"\",");
		}
		
	}
	
	/**
	 * @param map 
	 * @param stringBuilder  
	 * */
	public static void converMapToComboSelect(Map<String,String> map,StringBuilder stringBuilder){
		Set<String> keySet=map.keySet();
		int keyIndex=0;
		for(String key:keySet){
			stringBuilder.append("[\""+key+"\",\""+map.get(key).replaceAll(",", "&#44;")+"\"],");
				if(keyIndex==keySet.size()-1){
					stringBuilder.delete(stringBuilder.length()-1,stringBuilder.length());
				}
				
			keyIndex++;
		}
		
	}
	
	/**
	 * @param lovList 
	 * @return String 
	 */
	public static String generateProductModelJSON(List<ListOfValues> lovList){
		StringBuilder json=new StringBuilder("[");
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
		
		StringBuilder json=new StringBuilder("{");
		appendToJSON(PunchoutConstants.CUR_TIME,String.valueOf(System.currentTimeMillis()),json,false);
		for(String key:params.keySet()){
			appendToJSON(key,params.get(key),json,false);
		}	
		json.deleteCharAt(json.length()-1);
		json.append("}");
		return json.toString();
	}
	
	
	
	/**
	 * @param bundleItems
	 * @return
	 */
	public static String convertBundleToJson(List<Bundle> bundleItems){
		StringBuilder json=new StringBuilder();
		json.append("{");
		for(Bundle bundle:bundleItems){
			json.append("\"").append(bundle.getBundleId()).append("\":{");
			json.append("\"bundleId\":\"").append(bundle.getBundleId()).append("\",");
			json.append("\"configId\":\"").append(bundle.getConfigId()).append("\",");
			json.append("\"mpsdesc\":\"").append(bundle.getMpsDescription()).append("\",");
			json.append("\"name\":\"").append(StringUtils.isNotBlank(bundle.getBundleName())==true?bundle.getBundleName():"").append("\",");
			json.append("\"desc\":\"").append(bundle.getDescription()).append("\",");
			json.append("\"contractNo\":\"").append(bundle.getContractNumber()).append("\",");
			json.append("\"parts\":[");
			
			String partNumber="";//this will contain the partnumber of the partType printer
			String url="";
			String bundleType="";
			List<Part> parts=bundle.getPartList();
				for(Part part:parts){
					
					if("Printers".equalsIgnoreCase(part.getPartType())){
						partNumber=part.getPartNumber();
					}
					if(StringUtils.isNotBlank(part.getPartTypeMVFB2b())){
						bundleType=part.getPartTypeMVFB2b();
					}
					json.append("{");
					json.append("\"no\":\"").append(part.getPartNumber()).append("\",");
					json.append("\"pdesc\":\"").append(part.getDescription()).append("\",");
					json.append("\"qty\":").append(StringUtils.isNotBlank(part.getOrderQuantity())==true?part.getOrderQuantity():0);
					json.append("},");
					
				}
				if(parts.size()!=0){
					json.deleteCharAt(json.length()-1);	
				}
				
			json.append("],");
			json.append("\"bundleType\":\"").append(bundleType).append("\",");
			json.append("\"printerPartNo\":\"").append(partNumber).append("\",");
			json.append("\"price\":\"").append(bundle.getPrice()!=null?String.valueOf(bundle.getPrice()):"").append("\",");
			json.append("\"curr\":\"").append(StringUtils.isNotBlank(bundle.getCurrency())==true?bundle.getCurrency():"").append("\",");
			json.append("\"billingModel\":\"").append(bundle.getBillingModel()).append("\",");
			
			if(StringUtils.isNotBlank(partNumber)){
				try {
					url=URLImageUtil.getPartImageFromLocal(partNumber);
				} catch (SAXException  | IOException  | ParserConfigurationException e ) {
					LOGGER.error(" Image URL not found "+e.getMessage());
				}
			}
			
			json.append("\"img\":\"").append(url).append("\",");
			json.append("\"bQty\":").append(StringUtils.isNotBlank(bundle.getBundleQty())==true?bundle.getBundleQty():0);
			json.append("},");
			
		}
		if(bundleItems.size()>0){
			json.deleteCharAt(json.length()-1);
		}		
		json.append("}");
		return json.toString();
		
	}
	
	
	/**
	 * @param priceMap
	 * @param bundles
	 */
	public static String updateAndGenerateAccessories(List<OrderPart> parts, String bundleId){
		 StringBuilder sb=new StringBuilder();
		 sb.append("[");
		for(OrderPart part:parts){
			//update with Image
			if(part.getPartNumber()!=null){
				try {
					part.setPartImg(URLImageUtil.getPartImageFromLocal(part.getPartNumber()));
				} catch (SAXException  | IOException  | ParserConfigurationException e ) {
					LOGGER.error(" Image URL not found "+e.getMessage());
				}
			}
			
			sb.append("{\"img\":\"").append(StringUtils.isNotBlank(part.getPartImg())==true?part.getPartImg():"").append("\",")
			.append("\"desc\":\"")
			.append(StringUtils.isNotBlank(part.getDescription())==true?part.getDescription():"").append("\",")
			.append("\"pNo\":\"").append(part.getPartNumber()).append("\",")
			.append("\"bId\":\"").append(bundleId).append("\",")
			.append("\"qty\":\"").append(StringUtils.isNotBlank(part.getOrderQuantity())==true?part.getOrderQuantity():"").append("\",")
			.append("\"price\":\"").append(part.getPrice()==null?"":part.getPrice()).append("\"},");
			
		}
		if(parts.size()>0){
			sb.deleteCharAt(sb.length()-1);	
		}		
		sb.append("]");
		return sb.toString();
			 
	}
	/**
	 * @param printerMap
	 * @return
	 */
	public static String convertPrinterListJSON(Map<String,List<String>> printerMap){
		StringBuilder sb=new StringBuilder();
		sb.append("{");
		Set<String> keys=printerMap.keySet();
		for(String key:keys){
			sb.append("\"").append(key).append("\":[");
			List<String> printers=printerMap.get(key);
			for(String printer:printers){
				sb.append("\"").append(printer).append("\",");
			}
			if(printers.size()!=0){
				sb.deleteCharAt(sb.length()-1);
			}
			sb.append("],");
			
		}
		if(keys.size()!=0){
			sb.deleteCharAt(sb.length()-1);
		}
		sb.append("}");
		return sb.toString();
		
	}
}
