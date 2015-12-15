package com.lexmark.services.webService;

import java.net.URL;

import org.apache.axis.client.Stub;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.amind.common.util.StringUtils;
import com.lexmark.contract.PlacementContract;
import com.lexmark.lbs.directMove.LXK_spcLBS_spcUpdate_spcAsset_spcfrom_spcMap_PortType;
import com.lexmark.lbs.directMove.LXK_spcLBS_spcUpdate_spcAsset_spcfrom_spcMap_ServiceLocator;
import com.lexmark.lbs.directMove.Update_Asset_Input;
import com.lexmark.lbs.directMove.Update_Asset_Output;
import com.lexmark.lbs.placements.Create_Placements_Input;
import com.lexmark.lbs.placements.Create_Placements_Output;
import com.lexmark.lbs.placements.LXK_spcLBS_spcPlacement_spcCreation_spcfrom_spcMap_PortType;
import com.lexmark.lbs.placements.LXK_spcLBS_spcPlacement_spcCreation_spcfrom_spcMap_ServiceLocator;
import com.lexmark.services.api.PlacementService;
import com.lexmark.services.util.ObjectDebugUtil;

public class PlacementServiceImpl implements PlacementService{
	
private static Logger LOGGER = LogManager.getLogger(PlacementServiceImpl.class);
	
	
	private String username="PORTINTG";
	private String password="#123@Lex";
	private String url ="https://siebelqa.lexmark.com/eai_enu/start.swe?SWEExtSource=WebService&SWEExtCmd=Execute&WSSOAP=1";
	private String message="failure";
	
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
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}

	@SuppressWarnings("finally")
	@Override
	public String addPlacement(PlacementContract contract) throws Exception {		
		LOGGER.debug(" Enter add Placement");
		LXK_spcLBS_spcPlacement_spcCreation_spcfrom_spcMap_PortType port= webServiceInfo();
			Create_Placements_Input input = new Create_Placements_Input("", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "");
		try{	
			input.setPrinterModel(contract.getModelName());
			input.setPlacementIdentifier(contract.getPlacementName());
			input.setAccountId(contract.getAccount().getAccountId());
			input.setAccountName(contract.getAddressMap().get("storeFrontName"));
			input.setIPAddress(contract.getIpAddress());
			input.setExistingAddressId(contract.getAddressMap().get("extAddressId"));
			input.setStreetAddress(contract.getAddressMap().get("address"));
			input.setCity(contract.getAddressMap().get("city"));
			input.setState(contract.getAddressMap().get("state"));
			input.setCountry(contract.getAddressMap().get("country"));
			input.setBuilding(contract.getAddressMap().get("buildingName"));
			input.setBuildingId(contract.getAddressMap().get("buildingId"));
			input.setFloor(contract.getAddressMap().get("floorName"));
			input.setFloorId(contract.getAddressMap().get("floorId"));
			input.setZone(contract.getAddressMap().get("regionName"));
			input.setZoneId(contract.getAddressMap().get("regionId"));
			input.setGridCoordinatesX(contract.getAddressMap().get("gridX"));
			input.setGridCoordinatesY(contract.getAddressMap().get("gridY"));
			input.setLatitude(contract.getAddressMap().get("lat"));
			input.setLongitude(contract.getAddressMap().get("lng"));
			ObjectDebugUtil.printObjectContent(input,LOGGER);
			//need to set the address and IP address. siebel field missing
			
			Create_Placements_Output output = port.create_Placements(input);
			message = output.getError_spcMessage();
			if(StringUtils.isBlank(message)){
				message="success";
			}else{
				message="failure";
			}
		
   		}catch(Exception e){
   			LOGGER.debug("[Exception occured in WS Call]" + e.getCause() + e.getMessage());
   			e.printStackTrace();
   		}finally{
   			LOGGER.debug("message === "+message);
   			return message;
   		}
		
		
		
		
		
	}

	@Override
	public String changePlacement(PlacementContract contract) throws Exception {
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
			
			Update_Asset_Input input= new  Update_Asset_Input("","","","","",
					"","","","","",
					"","","","","",
					"","","","","",
					"","","","","");
			LOGGER.debug("----------->>"+contract.getPlacementId());
			input.setAssetId(contract.getPlacementId());
			input.setExtAddressId(contract.getAddressMap().get("extAddressId"));
			input.setStreetAddress(contract.getAddressMap().get("address"));
			input.setCity(contract.getAddressMap().get("city"));
			input.setState(contract.getAddressMap().get("state"));
			input.setCountry(contract.getAddressMap().get("country"));	
			input.setZipcode(contract.getAddressMap().get("zipCode"));
			input.setBuilding(contract.getAddressMap().get("buildingName"));
			input.setBuildingId(contract.getAddressMap().get("buildingId"));
			input.setFloor(contract.getAddressMap().get("floorName"));
			input.setFloorId(contract.getAddressMap().get("floorId"));
			input.setZone(contract.getAddressMap().get("regionName"));
			input.setZoneId(contract.getAddressMap().get("regionId"));
			input.setSiteId(contract.getAddressMap().get("campusId"));
			input.setSite(contract.getAddressMap().get("campusName"));
			input.setGridCoordinatesX(contract.getAddressMap().get("gridX"));
			input.setGridCoordinatesY(contract.getAddressMap().get("gridY"));
			input.setLatitude(contract.getAddressMap().get("lat"));
			input.setLongitude(contract.getAddressMap().get("lng"));
			
			
			Update_Asset_Output update_Asset_Output=	port.update_Asset(input);
			message=update_Asset_Output.getError_spcMessage();
			if(StringUtils.isBlank(message)){
				message="success";
			}else{
				message="failure";
			}
			
	   		}catch(Exception e){
	   			LOGGER.debug("[Exception occured in WS call Direct asset move]" + e.getCause() + e.getMessage());
	   			//e.printStackTrace();
	   		}finally{
	   			LOGGER.debug("message === "+message);
	   			return message;
	   		}
	}

	@Override
	public String removePlacement(PlacementContract contract) throws Exception {
		LXK_spcLBS_spcPlacement_spcCreation_spcfrom_spcMap_PortType port= webServiceInfo();
		Create_Placements_Input input = new Create_Placements_Input("", "", "", "", "", "", "", "", "", "", "",
				"", "", "", "", "", "", "", "", "", "", "", "", 
				"", "", "", "", "", "", "", "");
		input.setExistingPlacementId(contract.getPlacementId());
		input.setDevicePhase("Inactive");
		try{
			Create_Placements_Output output = port.create_Placements(input);
			message = output.getError_spcMessage();
			if(StringUtils.isBlank(message)){
				message="success";
			}else{
				message="failure";
			}
			
	   		}catch(Exception e){
	   			LOGGER.debug("[Exception occured in WS Call]" + e.getCause() + e.getMessage());
	   			e.printStackTrace();
	   		}finally{
	   			LOGGER.debug("message === "+message);
	   			return message;
	   		}
	}
	

	public LXK_spcLBS_spcPlacement_spcCreation_spcfrom_spcMap_PortType webServiceInfo(){
		String url=getUrl();
   		try{
   			
   			LXK_spcLBS_spcPlacement_spcCreation_spcfrom_spcMap_ServiceLocator locator= new LXK_spcLBS_spcPlacement_spcCreation_spcfrom_spcMap_ServiceLocator();
   			LXK_spcLBS_spcPlacement_spcCreation_spcfrom_spcMap_PortType port = locator.getLXK_spcLBS_spcPlacement_spcCreation_spcfrom_spcMap(new URL(url));
   			Stub stub = (Stub) port;
   			stub.setHeader("http://siebel.com/webservices","UsernameToken",getUsername());
			stub.setHeader("http://siebel.com/webservices","PasswordText", getPassword());
			stub.setHeader("http://siebel.com/webservices","SessionType", "None");
			return port;
   		}catch(Exception e){
   			e.printStackTrace();
   			return null;
   		}
   		
	}

}
