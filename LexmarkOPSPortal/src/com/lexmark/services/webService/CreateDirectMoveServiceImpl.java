package com.lexmark.services.webService;
import java.net.URL;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import javax.xml.rpc.holders.StringHolder;

import com.amind.common.util.StringUtils;
import com.lexmark.lbs.directMove.LXK_spcLBS_spcUpdate_spcAsset_spcfrom_spcMap_PortType;
import com.lexmark.lbs.directMove.LXK_spcLBS_spcUpdate_spcAsset_spcfrom_spcMap_ServiceLocator;
import com.lexmark.lbs.directMove.Update_Asset_Input;
import com.lexmark.lbs.directMove.Update_Asset_Output;
import com.lexmark.services.api.CreateDirectMoveService;
import com.lexmark.services.portlet.fleetmanagement.FilterManagementController;



public class CreateDirectMoveServiceImpl implements CreateDirectMoveService{
	
	private static Logger LOGGER = LogManager.getLogger(CreateDirectMoveServiceImpl.class);
	
	
	private String username;
	private String password;
	private String url;
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String doDirectMove(Map<String,String> parametermap){
		

   		LOGGER.debug(" Enter doDirectMove");
   		//String url="https://siebelqa.lexmark.com/eai_enu/start.swe?SWEExtSource=WebService&amp;SWEExtCmd=Execute";
   		String url=getUrl();
   		String message="failure";
   		try{
		
   			LXK_spcLBS_spcUpdate_spcAsset_spcfrom_spcMap_ServiceLocator locator = new LXK_spcLBS_spcUpdate_spcAsset_spcfrom_spcMap_ServiceLocator();		 
   			LXK_spcLBS_spcUpdate_spcAsset_spcfrom_spcMap_PortType port = locator.getLXK_spcLBS_spcUpdate_spcAsset_spcfrom_spcMap(new URL(url));
   			org.apache.axis.client.Stub stub = ((org.apache.axis.client.Stub) port);
		LOGGER.debug("UserName:-- "+ getUsername());
		LOGGER.debug("Password:-- "+ getPassword());
		LOGGER.debug("URL:-- "+ getUrl());
		
		stub.setHeader("http://siebel.com/webservices","UsernameToken",getUsername());
			stub.setHeader("http://siebel.com/webservices","PasswordText", getPassword());
			stub.setHeader("http://siebel.com/webservices","SessionType", "None");
		
		
		/*port.update_Asset(zoneId, longitude, SRId, process_spcInstance_spcId,
				gridCoordinatesX, siteId, floor, installAddressId, city, 
				zone, state, object_spcId, latitude, gridCoordinatesY, 
				buildingId, siebel_spcOperation_spcObject_spcId, extAddressId, 
				streetAddress, site, country, zipcode, floorId, assetId, accountId, 
				building, error_spcCode, error_spcMessage);*/
		/*StringHolder zoneId=new StringHolder(""); StringHolder longitude=new StringHolder(""); StringHolder SRId=new StringHolder(""); StringHolder process_spcInstance_spcId=new StringHolder("");
		StringHolder gridCoordinatesX=new StringHolder(""); StringHolder siteId=new StringHolder(""); StringHolder floor=new StringHolder(""); StringHolder installAddressId=new StringHolder("");StringHolder city=new StringHolder(""); 
		StringHolder zone=new StringHolder(""); StringHolder state=new StringHolder(""); StringHolder object_spcId=new StringHolder(""); StringHolder latitude=new StringHolder(""); StringHolder gridCoordinatesY=new StringHolder(""); 
		StringHolder buildingId=new StringHolder(""); StringHolder siebel_spcOperation_spcObject_spcId=new StringHolder(""); StringHolder extAddressId=new StringHolder(""); 
		StringHolder streetAddress=new StringHolder(""); StringHolder site=new StringHolder(""); StringHolder country=new StringHolder(""); StringHolder zipcode=new StringHolder(""); StringHolder floorId=new StringHolder(""); 
		StringHolder assetId=new StringHolder(""); StringHolder accountId=new StringHolder(""); 
		StringHolder building=new StringHolder(""); StringHolder error_spcCode=new StringHolder(""); StringHolder error_spcMessage=new StringHolder("");*/
		
			
		// Setting all fields blank other wise non nillable exception will occur	
		Update_Asset_Input update_Asset_Input= new  Update_Asset_Input("","","","","",
				"","","","","",
				"","","","","",
				"","","","","",
				"","","","","",
				"","","","","", "","","","","", "","","","","",
				"","");
		
		
		
		update_Asset_Input.setAssetId(parametermap.get("assetId"));
		
		update_Asset_Input.setStreetAddress(parametermap.get("address"));
		update_Asset_Input.setCountry(parametermap.get("country"));
		update_Asset_Input.setState(parametermap.get("state"));
		update_Asset_Input.setCity(parametermap.get("city"));
		update_Asset_Input.setZipcode(parametermap.get("zipCode"));
		
		update_Asset_Input.setExtAddressId(parametermap.get("extAddressId"));
		
		update_Asset_Input.setLatitude(parametermap.get("lat"));
		update_Asset_Input.setLongitude(parametermap.get("lng"));
		
		update_Asset_Input.setBuildingId(parametermap.get("buildingId"));
		update_Asset_Input.setBuilding(parametermap.get("buildingName"));
		
		update_Asset_Input.setSiteId(parametermap.get("campusId"));
		update_Asset_Input.setSite(parametermap.get("campusName"));
		
		
		update_Asset_Input.setFloorId(parametermap.get("floorId"));
		update_Asset_Input.setFloor(parametermap.get("floorName"));
		
		update_Asset_Input.setZoneId(parametermap.get("regionId"));
		update_Asset_Input.setZone(parametermap.get("regionName"));
		
		
		Update_Asset_Output update_Asset_Output=	port.update_Asset(update_Asset_Input);
		message=update_Asset_Output.getError_spcMessage();
		if(StringUtils.isBlank(message)){
			message="success";
		}
		
   		}catch(Exception e){
   			LOGGER.debug("[Exception occured in WS call Direct asset move]" + e.getCause() + e.getMessage());
   			e.printStackTrace();
   		}finally{
   			LOGGER.debug("message === "+message);
   			return message;
   		}
   	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	
	/*public static void main(String args[]){
		DirectMoveClient m=new DirectMoveClient();
		m.doDirectMove();
	}*/
	
}
