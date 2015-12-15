package com.lexmark.services.util;

//import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.lang.StringEscapeUtils;

import com.amind.common.util.StringUtils;
import com.lexmark.constants.LexmarkConstants;
import com.lexmark.domain.Asset;
import com.lexmark.domain.PageCounts;

import com.lexmark.service.impl.real.domain.LBSAddress;
import com.lexmark.service.impl.real.domain.LBSAsset;
import com.lexmark.service.impl.real.domain.LBSLocationBuilding;
import com.lexmark.service.impl.real.domain.LBSLocationBuildingType;
import com.lexmark.service.impl.real.domain.LBSLocationFloor;
import com.lexmark.service.impl.real.domain.LBSLocationSite;
import com.lexmark.service.impl.real.domain.LBSLocationZone;
import com.lexmark.util.BusinessObjectUtil;

public class HTMLOutputGenerator {
	/**
	 * @param address 
	 * @param type 
	 * @return String 
	 */
	public static String convertToOptions(List<LBSAddress> address,String type){
		
		StringBuffer html = new StringBuffer();
		if(address==null){
			return html.toString();
		}
		for(LBSAddress location:address){
			
			if(LexmarkConstants.COUNTRY.equalsIgnoreCase(type)){
				html.append("<option value=\""+location.getCountry()+"\">"+StringEscapeUtils.escapeHtml(location.getCountry())+"</option>\n");
			}/*else if(LexmarkConstants.STATE.equalsIgnoreCase(type)){
				html.append("<option value=\""+(location.getStateId()==null?location.getState():location.getStateId())+"\">"+StringEscapeUtils.escapeHtml(location.getState())+"</option>\n");
			}*/else if (LexmarkConstants.CITY.equalsIgnoreCase(type) && StringUtils.isNotBlank(location.getCity())){				
				html.append("<option value=\""+location.getCity()+"\">"+StringEscapeUtils.escapeHtml(location.getCity())+"</option>\n");
			}
		}
		return html.toString();
		
	}
	/**
	 * @param address 
	 * @return String 
	 */
	public static String convertBuildingToOptions(List<LBSLocationBuilding> address){
		
		StringBuffer html = new StringBuffer();
		if(address==null){
			return html.toString();
		}
		for(LBSLocationBuilding location:address){
			html.append("<option value=\""+(location.getBuildingId()==null?location.getBuilding():location.getBuildingId())+"\">"+StringEscapeUtils.escapeHtml(location.getBuilding())+"</option>\n");			
		}
		return html.toString();
		
	}
	/**
	 * @param address 
	 * @return String 
	 */
	public static String convertFloorToOptions(List<LBSLocationFloor> address){
		
		StringBuffer html = new StringBuffer();
		if(address==null){
			return html.toString();
		}
		for(LBSLocationFloor location:address){
			html.append("<option value=\""+(location.getFloorId()==null?location.getFloor():location.getFloorId())+"\" lod=\""+(location.getFloorLevelOfDetails()==null?"":location.getFloorLevelOfDetails())+"\">"+StringEscapeUtils.escapeHtml(location.getFloor())+"</option>\n");			
		}
		return html.toString();
		
	}
	/**
	 * @param address 
	 * @return String 
	 */
	public static String convertZoneToOptions(List<LBSLocationZone> address){
	
		StringBuffer html = new StringBuffer();
		if(address==null){
			return html.toString();
		}
		for(LBSLocationZone location:address){
			html.append("<option value=\""+(location.getZoneId()==null?location.getZone():location.getZoneId())+"\">"+StringEscapeUtils.escapeHtml(location.getZone())+"</option>\n");			
		}
		return html.toString();
	
	}
	/**
	 * @param address 
	 * @return String 
	 */
	public static String convertSiteToOptions(List<LBSLocationSite> address){
	
		StringBuffer html = new StringBuffer();
		if(address==null){
			return html.toString();
		}
		for(LBSLocationSite location:address){
			html.append("<option value=\""+(location.getSiteId()==null?location.getSite():location.getSiteId())+"\">"+StringEscapeUtils.escapeHtml(location.getSite())+"</option>\n");			
		}
		return html.toString();
	
	}
	/**
	 * @param assets 
	 * @return String 
	 */
	public static String convertAsset(List<Asset> assets){
		
		StringBuffer html = new StringBuffer("[");
		if(assets==null){
			html.append("]");
			return html.toString();
		}
		boolean atleastOneAsset=false;
		for(Asset asset:assets){
			if(asset.getUserFavoriteFlag()){
				atleastOneAsset=true;
				html.append("\"");
				html.append(asset.getAssetId());
				html.append("\"");
				html.append(",");
			}
		}
		if(atleastOneAsset){
			html.deleteCharAt(html.length()-1);
		}
		html.append("]");
		return html.toString();
	
	}
	
	/**
	 * @param assets 
	 * @param locale 
	 * @return String 
	 */
	public static String convertAssetDetails(List<LBSAsset> assets,Locale locale){
		StringBuffer json = new StringBuffer("["); 
		if(assets==null){
			json.append("]");
			return json.toString();
		}
		
		for(LBSAsset asset:assets){
			json.append("{");
			for(JSONBeanFieldMapping value:JSONBeanFieldMapping.values()){
				json.append("\"");
				json.append(value.getJsonParam());
				json.append("\":\"");
				String objValue=BusinessObjectUtil.formatColumn(asset,value.getBeanField(),locale);
				
				if (StringUtils.isNotBlank(objValue)){
				json.append(objValue);
				}
				else{
					json.append("");
					}
				json.append("\",");
			}
			json.deleteCharAt(json.length()-1);
			json.append("},");
		}
		if(!assets.isEmpty()){
			json.deleteCharAt(json.length()-1);
		}
		json.append("]");
		
		return json.toString();
		
	}
	/**
	 * This method is to check whether 
	 * state , or county or province or district is 
	 * present in list. On that basis the state/region will be generated.
	 * @param address 
	 * @return String 
	 * */
	public static String generateRegions(List<LBSAddress> address){
		StringBuffer regionHtml=new StringBuffer();
		
		Map<String,String> sortLocations=new TreeMap<String,String>();
		for(LBSAddress location:address){
			if(StringUtils.isNotBlank(location.getState())){
				sortLocations.put(location.getState()+"^s",StringEscapeUtils.escapeHtml(location.getState()));				
			}
			if(StringUtils.isNotBlank(location.getCounty())){
				sortLocations.put(location.getCounty()+"^c",StringEscapeUtils.escapeHtml(location.getCounty()));				
			}if(StringUtils.isNotBlank(location.getProvince())){
				sortLocations.put(location.getProvince()+"^p",StringEscapeUtils.escapeHtml(location.getProvince()));				
			}if(StringUtils.isNotBlank(location.getDistrict())){
				sortLocations.put(location.getDistrict()+"^d",StringEscapeUtils.escapeHtml(location.getDistrict()));				
			}
		}
		
		
		 for(Map.Entry<String,String> entry : sortLocations.entrySet()) {
			 regionHtml.append("<option value=\""+entry.getKey()+"\">"+entry.getValue()+"</option>\n");
		    } 
		
		/*for(LBSAddress location:address){
			
			
			
			/*if(StringUtils.isNotBlank(location.getState())){
				regionHtml.append("<option value=\""+(location.getStateId()==null?location.getState():location.getStateId())+"^s\">"+StringEscapeUtils.escapeHtml(location.getState())+"</option>\n");
			}
			if(StringUtils.isNotBlank(location.getCounty())){
				regionHtml.append("<option value=\""+location.getCounty()+"^c\">"+StringEscapeUtils.escapeHtml(location.getCounty())+"</option>\n");
			}if(StringUtils.isNotBlank(location.getProvince())){
				regionHtml.append("<option value=\""+location.getProvince()+"^p\">"+StringEscapeUtils.escapeHtml(location.getProvince())+"</option>\n");
			}if(StringUtils.isNotBlank(location.getDistrict())){
				regionHtml.append("<option value=\""+location.getDistrict()+"^d\">"+StringEscapeUtils.escapeHtml(location.getDistrict())+"</option>\n");
			}
		}*/
		
		
		return regionHtml.toString();
	}
	public static String generateBuildingTypes(List<LBSLocationBuildingType> buildingType){
		StringBuffer sb=new StringBuffer();
		for(LBSLocationBuildingType bType:buildingType){
			if(StringUtils.isNotBlank(bType.getBuildingType())){
				sb.append("<option value=\"").append(bType.getBuildingType()).append("\">").append(bType.getBuildingType()).append("</option>\n");	
			}
			
		}
		return sb.toString();		
	}
	public static String generatePageCountsJson(List<PageCounts> pageCounts,String id){
		StringBuffer sb=new StringBuffer("{");
		sb.append("\"id\":").append("\"").append(id).append("\",");
		sb.append("\"pageCount\":{");
		int index=0;
		for(PageCounts count:pageCounts){
			sb.append("\"").append(count.getName()).append("\":");
			sb.append("{\"name\":\"").append(count.getName()).append("\",");
			sb.append("\"index\":").append(index++).append(",");
			String dt=count.getDate();String val=count.getCount();
			sb.append("\"date\":\"").append(dt==null?"":dt).append("\",");
			sb.append("\"count\":\"").append(val==null?"":val).append("\"},");
		}
		int listCount = pageCounts.size();
		if(listCount>0){
			
		sb.deleteCharAt(sb.length()-1);
		}
		sb.append("}}");
		return sb.toString();		
	}
}
