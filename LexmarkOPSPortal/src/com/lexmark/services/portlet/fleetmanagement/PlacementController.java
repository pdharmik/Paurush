package com.lexmark.services.portlet.fleetmanagement;

import java.io.IOException;
import java.io.PrintWriter;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.lexmark.services.api.PlacementService;
import com.lexmark.services.util.ContractFactory;



@Controller
@RequestMapping("VIEW")
public class PlacementController {
	
	private static Logger LOGGER = LogManager.getLogger(PlacementController.class);
	

	@Autowired
	private PlacementService placementService;
	
	/**
	 * @param request
	 * @param response
	 */
	@ResourceMapping("addPlacement")
	public void addPlacement(ResourceRequest request,ResourceResponse response){
		
		//Create contract for Siebel call Placement Contract 
		String success="";
		try{
			success= placementService.addPlacement(ContractFactory.getPlacementContract(request,"addPlacement"));
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			StringBuffer resp=new StringBuffer("{");
			resp.append("\"message\":\"").append(success).append("\"}");
			writeResponse(response, resp.toString());	
		}
		
		
		
	}
	/**
	 * @param request
	 * @param response
	 */
	@ResourceMapping("changePlacement")
	public void changePlacement(ResourceRequest request,ResourceResponse response){
		//Create contract for Siebel call Placement Contract 
		String success="";
		try{
			success= placementService.changePlacement(ContractFactory.getPlacementContract(request,"changePlacement"));
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			StringBuffer resp=new StringBuffer("{");
			resp.append("\"message\":\"").append(success).append("\"}");
			writeResponse(response, resp.toString());	
		}
		
	}
	/**
	 * @param request
	 * @param response
	 */
	@ResourceMapping("removePlacement")
	public void removePlacement(ResourceRequest request,ResourceResponse response){
		//Create contract for Siebel call Placement Contract
		String success="";
		try{
			success=placementService.removePlacement(ContractFactory.getPlacementContract(request,"removePlacement"));
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			StringBuffer resp=new StringBuffer("{");
			resp.append("\"message\":\"").append(success).append("\"}");
			writeResponse(response, resp.toString());	
		}
		
	}
	
	
	/**
	 * @param response 
	 * @param val 
	 */
	private void writeResponse(ResourceResponse response,String val){
		try {
			final PrintWriter out = response.getWriter();
			response.setProperty("Cache-Control", "max-age=0,no-cache,no-store");
			response.setProperty("Expires", "max-age=0,no-cache,no-store");
			response.setContentType("text/html");
			out.write(val);
			out.flush();
			out.close();
		} catch (IOException e) {
			LOGGER.error("IOException while invoking response#getWriter(),"
					+ e.getMessage());
		}
	}
	
}