package com.lexmark.services.util;

import java.util.List;
import java.util.Locale;

import javax.portlet.PortletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.lexmark.contract.LocalizedServiceActivityStatusContract;
import com.lexmark.contract.LocalizedServiceStatusContract;

import com.lexmark.domain.Part;
import com.lexmark.domain.ServiceRequestOrderLineItem;

import com.lexmark.result.LocalizedServiceActivityStatusResult;
import com.lexmark.result.LocalizedServiceStatusResult;

import com.lexmark.service.api.ServiceRequestLocale;

import com.lexmark.service.impl.real.jdbc.ServiceRequestLocaleImpl;
import com.lexmark.util.BusinessObjectUtil;
import com.lexmark.util.PropertiesMessageUtil;

public class XmlOutputGenerator {
	/**
	 * @param locale 
	 */
	public  XmlOutputGenerator(Locale locale){
		setLocale(locale);
	}
	
	/**
	 * Variable Declaration
	 */
	private static Logger logger = LogManager.getLogger(XmlOutputGenerator.class);
	
	/**
	 * Variable Declaration
	 */
    private Locale locale;
    
    /**
	 * Variable Declaration
	 */
	private ServiceRequestLocale  serviceRequestLocale;
    /**
	 * Variable Declaration
	 */
    private static String BUNDLENAME_BUTTON_MSG = "com.lexmark.services.resources.messages";
    /**
    
    /**
	 * @return ServiceRequestLocale 
	 */
	public ServiceRequestLocale getServiceRequestLocale() {
    	if(serviceRequestLocale == null) {
    		serviceRequestLocale = new ServiceRequestLocaleImpl();
    	}
		return serviceRequestLocale;
	}
	/**
	 * @param serviceRequestLocale 
	 */
	public void setServiceRequestLocale(ServiceRequestLocale serviceRequestLocale) {
		this.serviceRequestLocale = serviceRequestLocale;
	}
	
	/**
	 * @return Locale 
	 */
	public Locale getLocale() {
		if(locale ==null) {
			locale = Locale.US;
		}
		return locale;
	}

	/**
	 * @param locale 
	 */
	public void setLocale(Locale locale) {
		this.locale = locale;
	}
	/**
	 * @param items 
	 * @param totalCount 
	 * @param posStart 
	 * @param columnsPattern 
	 * @return String 
	 */
	public String generateXMLPendingShip(List<ServiceRequestOrderLineItem> items, int totalCount, int posStart, String[] columnsPattern) {
		logger.debug("-----------Step 4--convert2XML started---------["+System.nanoTime()+"]");
		StringBuffer xml = new StringBuffer("<?xml version=\"1.0\" ?>\n");
		int rowCount = 0;
		xml = xml.append("<rows total_count=\""+totalCount+"\" pos=\""+posStart+"\">\n");
		for(int i = 0; i < items.size(); i ++){
				rowCount += 1; 
				xml = xml.append("<row id=\""+ (posStart + i)+"\">\n");				
				for(String columnPattern: columnsPattern) {
				    xml = xml.append("<cell><![CDATA[");
				    
				    String value = "";
				    if(items.get(i)!=null){
				    	value = BusinessObjectUtil.formatColumn(items.get(i), columnPattern, locale);
				    }
				    value = localizeServiceRequestStatus(value, columnPattern, locale);
				    if(value == null){ value = "";}
				    //defect# 10059
				    if("price".equalsIgnoreCase(columnPattern)){
					   if(value!= null){
						   if("0".equals(value)|| "0.0".equals(value) || "0.00".equals(value) ||"".equals(value)){
					    		value = PropertiesMessageUtil.getPropertyMessage(BUNDLENAME_BUTTON_MSG, "requestInfo.error.priceUnavailable", locale);
					    	}else{
					    		value=String.format( "%.2f", Float.parseFloat(value)) ;
					    		if(!"".equals(items.get(i).getCurrency()) && items.get(i).getCurrency() != null){
					    		value=value + " ("+items.get(i).getCurrency()+")";
					    		}
					    	}
					   }
				   }
				   xml = xml.append(value);
				   xml = xml.append("]]></cell>\n");
				}
				xml = xml.append(" </row>\n");	
		}
		xml = xml.append("</rows>\n");
		if(rowCount == 0){
			logger.debug("-----------Step 4--convert2XML End---------["+System.nanoTime()+"]");
			return null;
		}
		logger.debug("-----------Step 4--convert2XML End---------["+System.nanoTime()+"]");
		return xml.toString();
	}
	
	
	/**
	 * @param value 
	 * @param columnPattern 
	 * @param locale 
	 * @return String 
	 */
	private String localizeServiceRequestStatus(String value, String columnPattern, Locale locale){
		  if(columnPattern.equalsIgnoreCase("serviceRequestStatus")){
		   if(value != null){
   
		    LocalizedServiceStatusResult result = new LocalizedServiceStatusResult();
		    LocalizedServiceStatusContract contract = new LocalizedServiceStatusContract();
		    ServiceRequestLocale srlocale = getServiceRequestLocale();
		    contract.setLocale(locale);
		    contract.setSiebelValue(value);
		    try{
		     result = srlocale.retrieveLocalizedServiceStatus(contract);
		     value = result.getLocalizedString();
		    }catch(Exception e){
		     //Do not throw out exception
		     logger.debug("Failed to retrieve localized service request status!"+e.getMessage());
		    }
		   }
		  } else if(columnPattern.equalsIgnoreCase("activityDescription")){
			  if(value != null){

				  LocalizedServiceActivityStatusResult result = new LocalizedServiceActivityStatusResult();
				  LocalizedServiceActivityStatusContract contract = new LocalizedServiceActivityStatusContract();
				  contract.setLocale(locale);
				  contract.setSiebelValue(value);
				  try{
					  result = serviceRequestLocale.retrieveLocalizedServiceActivityStatus(contract);
					  value = result.getLocalizedValue();
				  }catch(Exception e){
					  //Do not throw out exception
					  logger.debug("Failed to retrieve localized service request activity status!"+e.getMessage());
				  }
			  }
		  }
		  return value;
   }
	/**
	 * @param parts 
	 * @param size 
	 * @param posStart 
	 * @param patters 
	 * @param request 
	 * @return String 
	 */
	public String generatePendingSHipment(List<Part> parts, int size, int posStart,String patters[],PortletRequest request) {
		int sizeCount=0;
		StringBuffer xml = new StringBuffer();
		int i=0;
		for (int j=0;j<parts.size();j++) {
			Part parentPart=parts.get(j);
			String partBundleId=parentPart.getCatalogId();
			if(parentPart.getCatalogType()!=null){
				logger.debug("parentPart.getCatalogType()"+parentPart.getCatalogType());
				StringBuffer xmlRow=new StringBuffer();
				StringBuffer tempXmlRow=new StringBuffer();
				if(parentPart.getCatalogType().equalsIgnoreCase("Hardware Bundles")){
					
					if(parentPart.getBundleParentLineId()==null || parentPart.getBundleParentLineId().equals("")){
						//sizeCount++;
						boolean sectionAppear = false;
						logger.debug("Parent Bundle");
						
//							if(size>1){
//								xmlRow.append("  <cell><![CDATA[<div class=\"dhx_sub_row\"><table class=\"displayGrid\"><thead><tr><th>Part Number</th><th>Description</th><th>Part Type</th><th>Quantity</th></tr></thead>");
//								}
								for(int k=0;k<parts.size();k++){
									Part part=parts.get(k);
									if(partBundleId !=null && part.getBundleParentLineId()!=null && partBundleId.equalsIgnoreCase(part.getBundleParentLineId())){
										sectionAppear = true;
										logger.debug("sectionAppear ::: " + sectionAppear);
										tempXmlRow.append("<tr>");
										tempXmlRow.append("<td>"+part.getPartNumber() +"</td>");
										tempXmlRow.append("<td>"+part.getDescription() +"</td>");
										tempXmlRow.append("<td>"+part.getDeviceType() +"</td>");
										tempXmlRow.append("<td>"+Integer.parseInt(part.getOrderQuantity()) / Integer.parseInt(parentPart.getOrderQuantity()) +"</td>");								
										tempXmlRow.append("</tr>");
										part.setBundleParentLineId("donee");	
									}
								}

						if(sectionAppear){	
							sizeCount++;
							if(size>1){
								xmlRow.append("  <cell><![CDATA[<div class=\"dhx_sub_row\"><table class=\"displayGrid\"><thead><tr><th>Part Number</th><th>Description</th><th>Part Type</th><th>Quantity</th></tr></thead>");
								xmlRow.append(tempXmlRow.toString());
								xmlRow.append("</table></div>]]></cell>\n");
							}
							xmlRow.append("  <cell><![CDATA[" + parentPart.getPartNumber()+ "]]></cell>\n");
							xmlRow.append("  <cell><![CDATA[" + parentPart.getDescription()+ "]]></cell>\n");
							xmlRow.append("  <cell><![CDATA[" + parentPart.getDeviceType()+ "]]></cell>\n");
							xmlRow.append("  <cell><![CDATA[" + parentPart.getOrderQuantity()+ "]]></cell>\n");
							xmlRow.append("  <cell><![CDATA[" + parentPart.getDeviceType()+ "]]></cell>\n");
							if("0".equalsIgnoreCase(parentPart.getTotalPrice()) || parentPart.getTotalPrice() == null){
								xmlRow.append("  <cell><![CDATA[" + parentPart.getTotalPrice()+ "]]></cell>\n");
							}else{
								xmlRow.append("  <cell><![CDATA[" + String.format("%.2f",Float.parseFloat(parentPart.getTotalPrice()))+" ("+parentPart.getCurrency()+")"+ "]]></cell>\n");
							}
						}else{
							sizeCount++;
							xmlRow.append("  <cell><![CDATA[]]></cell>\n");
							xmlRow.append("  <cell><![CDATA[" + parentPart.getPartNumber()+ "]]></cell>\n");
							xmlRow.append("  <cell><![CDATA[" + parentPart.getDescription()+ "]]></cell>\n");
							xmlRow.append("  <cell><![CDATA[" + parentPart.getDeviceType()+ "]]></cell>\n");
							xmlRow.append("  <cell><![CDATA[" + parentPart.getOrderQuantity()+ "]]></cell>\n");
							xmlRow.append("  <cell><![CDATA[" + parentPart.getDeviceType()+ "]]></cell>\n");
							if("0".equalsIgnoreCase(parentPart.getTotalPrice()) || parentPart.getTotalPrice() == null){
								xmlRow.append("  <cell><![CDATA[" + parentPart.getTotalPrice()+ "]]></cell>\n");
							}else{
								xmlRow.append("  <cell><![CDATA[" + String.format("%.2f",Float.parseFloat(parentPart.getTotalPrice()))+" ("+ parentPart.getCurrency()+ ")"+ "]]></cell>\n");
							}
						}
						
					}						
				}else{
					sizeCount++;
					xmlRow.append("  <cell><![CDATA[]]></cell>\n");
					xmlRow.append("  <cell><![CDATA[" + parentPart.getPartNumber()+ "]]></cell>\n");
					xmlRow.append("  <cell><![CDATA[" + parentPart.getDescription()+ "]]></cell>\n");
					xmlRow.append("  <cell><![CDATA[" + parentPart.getDeviceType()+ "]]></cell>\n");
					xmlRow.append("  <cell><![CDATA[" + parentPart.getOrderQuantity()+ "]]></cell>\n");
					xmlRow.append("  <cell><![CDATA[" + parentPart.getDeviceType()+ "]]></cell>\n");
					if("0".equalsIgnoreCase(parentPart.getTotalPrice()) || parentPart.getTotalPrice() == null){
						xmlRow.append("  <cell><![CDATA[" + parentPart.getTotalPrice()+ "]]></cell>\n");
					}else{
						xmlRow.append("  <cell><![CDATA[" + String.format("%.2f",Float.parseFloat(parentPart.getTotalPrice()))+" ("+ parentPart.getCurrency()+ ")"+ "]]></cell>\n");
					}
				}
				
				if(xmlRow.length()!=0){
					xmlRow.insert(0,"<row id=\"" + (posStart + i++) + "\">\n");
					xmlRow.insert(xmlRow.length(),"</row>\n");
					
					xml.append(xmlRow);
				}
			}
		}
		xml.append("</rows>\n");
		xml.insert(0, "<?xml version=\"1.0\" ?>\n <rows total_count=\"" + sizeCount + "\" pos=\"" + posStart+ "\">\n");
		return xml.toString();
	}
}
