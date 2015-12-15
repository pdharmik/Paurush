package com.lexmark.services.portlet.fleetmanagement;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.PortletRequest;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.lexmark.constants.LexmarkConstants;
import com.lexmark.contract.AddressListContract;
import com.lexmark.contract.CannedQueryContract;
import com.lexmark.domain.CannedQuery;
import com.lexmark.result.AddressListResult;
import com.lexmark.result.CannedQueriesResult;
import com.lexmark.service.api.CannedQueryService;
import com.lexmark.service.api.CrmSessionHandle;
import com.lexmark.service.api.GlobalService;
import com.lexmark.service.api.LBSLocationService;
import com.lexmark.service.api.ServiceRequestService;
import com.lexmark.service.impl.real.domain.LBSAddress;
import com.lexmark.service.impl.real.domain.LBSLocationBuildingType;
import com.lexmark.services.api.CreateDirectMoveService;
import com.lexmark.services.util.ChangeMgmtConstant;
import com.lexmark.services.util.ContractFactory;
import com.lexmark.services.util.HTMLOutputGenerator;
import com.lexmark.services.util.ObjectDebugUtil;
import com.lexmark.services.util.PortalSessionUtil;

import com.lexmark.services.webService.CreateDirectMoveServiceImpl;
//import com.lexmark.services.webService.DirectMoveClient;
/**
 * @author wipro  
 *
 */

@Controller
@RequestMapping("VIEW")
public class FilterManagementController {
	
	private static Logger LOGGER = LogManager.getLogger(FilterManagementController.class);
	

	private CannedQueryService cannedQueryService;
	
	@Autowired
	private ServiceRequestService serviceRequestService;
	
	private CreateDirectMoveService createMoveService;
	
	/**
	 * @param createMoveService the createMoveService to set
	 */
	public void setCreateMoveService(CreateDirectMoveService createMoveService) {
		this.createMoveService = createMoveService;
	}

	@Autowired
	private GlobalService globalService;
	
	@Autowired
	private LBSLocationService lbsLocationService;
	
	
	
	/**
	 * @param request 
	 * @return  List<LBSAddress> 
	 */
	public List<LBSAddress> getCountry(PortletRequest request) {
		
		AddressListResult result=getAddressListLocations(request);
		return result.getLbsAddressList();
		
	}
	
	/**
	 * @param request 
	 * @return 
	 */
	public List<LBSLocationBuildingType> getBuildingType(PortletRequest request){
		AddressListContract contract=ContractFactory.getLBSAddressContract(request);
		CrmSessionHandle crmSessionHandle = globalService.initCrmSessionHandle(PortalSessionUtil
				.getSiebelCrmSessionHandle(request));
		contract.setSessionHandle(crmSessionHandle);
		ObjectDebugUtil.printObjectContent(contract, LOGGER);
		try {
			
			AddressListResult result=lbsLocationService.retrieveLBSBuildingTypes(contract);
			return result.getLbsLocationBuildingType();
		} catch (Exception e) {
			LOGGER.error("[ error while retrieving building types ]"+e.getMessage());
			return null;
		}
	}
	
	/**
	 * @param request 
	 * @param response 
	 */
	@ResourceMapping("getStateList")
	public void getState(ResourceRequest request,ResourceResponse response) {
		
		AddressListResult result=getAddressListLocations(request);
		
		/*for(LBSAddress a:result.getLbsAddressList()){
			LOGGER.debug(" county "+a.getCounty());
			LOGGER.debug(" district "+a.getDistrict());
			LOGGER.debug(" province "+a.getProvince());
		}*/
		StringBuffer sb=new StringBuffer("{\"state\":\"");
		sb.append(StringEscapeUtils.escapeJavaScript(HTMLOutputGenerator.generateRegions(result.getLbsAddressList())));
		sb.append("\",\"city\":\"");
		sb.append(StringEscapeUtils.escapeJavaScript(HTMLOutputGenerator.convertToOptions(result.getLbsAddressList(),LexmarkConstants.CITY)));
		sb.append("\"}");
		writeResonse(response,sb.toString());
		
	}
	
	/**
	 * @param request 
	 * @param response 
	 */
	@ResourceMapping("getCity")
	public void getCity(ResourceRequest request,ResourceResponse response) {
		
		AddressListResult result=getAddressListLocations(request);
		writeResonse(response,HTMLOutputGenerator.convertToOptions(result.getLbsAddressList(), LexmarkConstants.CITY));
		
	}
	
	/**
	 * @param request 
	 * @param response 
	 */
	@ResourceMapping("getSiteBuildingZone")
	public void getSite(ResourceRequest request,ResourceResponse response) {
		
		
		AddressListResult result=getSiteBuildingFloorZone(request);
		StringBuffer sb=new StringBuffer("{\"site\":\"");
		sb.append(StringEscapeUtils.escapeJavaScript(HTMLOutputGenerator.convertSiteToOptions(result.getLbsLocationSiteList())));
		sb.append("\",\"building\":\"");
		sb.append(StringEscapeUtils.escapeJavaScript(HTMLOutputGenerator.convertBuildingToOptions(result.getLbsLocationBuildingList())));
		sb.append("\",\"zone\":\"");
		sb.append(StringEscapeUtils.escapeJavaScript(HTMLOutputGenerator.convertZoneToOptions(result.getLbsLocationZoneList())));
		sb.append("\"}");
		writeResonse(response,sb.toString());
	}
	
	/**
	 * @param request 
	 * @param response 
	 */
	@ResourceMapping("getBuilding")
	public void getBuilding(ResourceRequest request,ResourceResponse response) {
		
		
		AddressListResult result=getSiteBuildingFloorZone(request);
		writeResonse(response,HTMLOutputGenerator.convertBuildingToOptions(result.getLbsLocationBuildingList()));
	}
	
	/**
	 * @param request 
	 * @param response 
	 */
	@ResourceMapping("getFloor")
	public void getFloor(ResourceRequest request,ResourceResponse response) {
		
		AddressListResult result=getSiteBuildingFloorZone(request);
		writeResonse(response,HTMLOutputGenerator.convertFloorToOptions(result.getLbsLocationFloorList()));
		/*LOGGER.debug("site"+HTMLOutputGenerator.convertSiteToOptions(result.getLbsLocationSiteList()));
		StringBuffer sb=new StringBuffer("{\"site\":\"");
		sb.append(StringEscapeUtils.escapeJavaScript(HTMLOutputGenerator.convertSiteToOptions(result.getLbsLocationSiteList())));
		sb.append("\",\"floor\":\"");
		sb.append(StringEscapeUtils.escapeJavaScript(HTMLOutputGenerator.convertFloorToOptions(result.getLbsLocationFloorList())));
		LOGGER.debug("floor"+HTMLOutputGenerator.convertFloorToOptions(result.getLbsLocationFloorList()));
		//sb.append("\",\"zone\":\"");
		//sb.append(StringEscapeUtils.escapeJavaScript(HTMLOutputGenerator.convertZoneToOptions(result.getLbsLocationZoneList())));
		sb.append("\"}");
		writeResonse(response,sb.toString());*/
		
		
	}
	/**
	 * @param request 
	 * @param response 
	 */
	@ResourceMapping("getSite")
	public void getSiteList(ResourceRequest request,ResourceResponse response) {
		
		AddressListResult result=getSiteBuildingFloorZone(request);
		writeResonse(response,HTMLOutputGenerator.convertSiteToOptions(result.getLbsLocationSiteList()));
	}
	/**
	 * @param request 
	 * @param response 
	 */
	@ResourceMapping("getZone")
	public void getZone(ResourceRequest request,ResourceResponse response) {
		AddressListResult result=getSiteBuildingFloorZone(request);
		writeResonse(response,HTMLOutputGenerator.convertZoneToOptions(result.getLbsLocationZoneList()));		
	}
	
	/**
	 * @param request 
	 * @param response 
	 */ 
	@ResourceMapping("getAllLocation")
	public void getCountryStateCityBuildingSite(ResourceRequest request,ResourceResponse response) {
		AddressListResult result=getSiteBuildingFloorZone(request);
		StringBuffer sb=new StringBuffer("{\"country\":\"");
		sb.append(StringEscapeUtils.escapeJavaScript(HTMLOutputGenerator.convertToOptions(result.getLbsAddressList(),LexmarkConstants.COUNTRY)));
		sb.append("\",\"state\":\"");
		sb.append(StringEscapeUtils.escapeJavaScript(HTMLOutputGenerator.generateRegions(result.getLbsAddressList())));
		sb.append("\",\"city\":\"");
		sb.append(StringEscapeUtils.escapeJavaScript(HTMLOutputGenerator.convertToOptions(result.getLbsAddressList(),LexmarkConstants.CITY)));
		sb.append("\",\"site\":\"");
		sb.append(StringEscapeUtils.escapeJavaScript(HTMLOutputGenerator.convertSiteToOptions(result.getLbsLocationSiteList())));
		sb.append("\",\"building\":\"");
		sb.append(StringEscapeUtils.escapeJavaScript(HTMLOutputGenerator.convertBuildingToOptions(result.getLbsLocationBuildingList())));
		sb.append("\",\"zone\":\"");
		sb.append(StringEscapeUtils.escapeJavaScript(HTMLOutputGenerator.convertZoneToOptions(result.getLbsLocationZoneList())));
		sb.append("\",\"floor\":\"");
		sb.append(StringEscapeUtils.escapeJavaScript(HTMLOutputGenerator.convertFloorToOptions(result.getLbsLocationFloorList())));
		sb.append("\"}");
		writeResonse(response,sb.toString());
	}
	
	/*
	 * This method will be called when settings is load
	 * from DB and from db if country state city is present 
	 * This method uses one session and do not acquire multiple sessions
	 * 
	 * */
	/*@ResourceMapping("getFilterValues")
	public void getFilterDropDownValues(ResourceRequest request,@RequestParam("ctry") String country,
			@RequestParam("state") String state,@RequestParam("cty") String city,
			@RequestParam("bldng") String building,ResourceResponse response){
		
		String[] paramNames={"ctry","state","cty","bldng"};
		int countThread=0;
		for(String param:paramNames){
			if(request.getParameter(param)!=null){
				countThread++;
			}
		}
		
		
		CrmSessionHandle crmSessionHandle = globalService.initCrmSessionHandle(PortalSessionUtil
				.getSiebelCrmSessionHandle(request));
		try{
		ExecutorService executor = Executors.newFixedThreadPool(countThread);
		Future<AddressListResult> stateResultFuture =null;
		if(StringUtils.isNotBlank(country)){	
			final AddressListContract contract =ContractFactory.getLBSAddressContractSession(request);
			contract.setSessionHandle(crmSessionHandle);
			contract.setCountry(country);
			stateResultFuture= executor.submit(new Callable<AddressListResult>() {
					public AddressListResult call() throws Exception {
						return amindAddresslistLocation(contract);
				}
				
			});
		}
		Future<AddressListResult> cityResultFuture=null;
		if(StringUtils.isNotBlank(state)){
			final AddressListContract contract =ContractFactory.getLBSAddressContractSession(request);
			contract.setState(state);
			contract.setSessionHandle(crmSessionHandle);
			cityResultFuture = executor.submit(new Callable<AddressListResult>() {
					public AddressListResult call() throws Exception {
						return amindAddresslistLocation(contract);
				
				}
			});
		}
		Future<AddressListResult> siteBuildingZoneResultFuture=null;
		if(StringUtils.isNotBlank(city)){
			final AddressListContract contract =ContractFactory.getLBSAddressContractSession(request);
			contract.setCity(city);
			contract.setSessionHandle(crmSessionHandle);
			siteBuildingZoneResultFuture = executor.submit(new Callable<AddressListResult>() {
				public AddressListResult call() throws Exception {
				
					return amindSiteBuildingFloorZone(contract);
				}
			
			});
		}
		Future<AddressListResult> floor=null;
		if(StringUtils.isNotBlank(building)){
			final AddressListContract contract =ContractFactory.getLBSAddressContractSession(request);
			contract.setCity(city);
			contract.setSessionHandle(crmSessionHandle);
			floor= executor.submit(new Callable<AddressListResult>() {
				public AddressListResult call() throws Exception {
				
					return amindSiteBuildingFloorZone(contract);
				}
			
			});
		}
		LOGGER.debug("Before getting result");
		AddressListResult resultState=stateResultFuture.get();
		AddressListResult resultCity=cityResultFuture.get();
		AddressListResult resultSiteBuildingZone=siteBuildingZoneResultFuture.get();
		AddressListResult resultFloor=floor.get();
		StringBuffer sb=new StringBuffer("{");
		
		
		sb.append("\"state\":\"");
		if(resultState!=null){
			LOGGER.debug(HTMLOutputGenerator.convertToOptions(resultState.getLbsAddressList(),LexmarkConstants.STATE));
			sb.append(StringEscapeUtils.escapeJavaScript(HTMLOutputGenerator.convertToOptions(resultState.getLbsAddressList(),LexmarkConstants.STATE)));
		}
		sb.append("\",");
		
		if(resultCity!=null){
			LOGGER.debug(HTMLOutputGenerator.convertToOptions(resultCity.getLbsAddressList(),LexmarkConstants.CITY));
			sb.append("\"city\":\"");
			sb.append(StringEscapeUtils.escapeJavaScript(HTMLOutputGenerator.convertToOptions(resultCity.getLbsAddressList(),LexmarkConstants.CITY)));
		}
		sb.append("\",");
		
		if(resultSiteBuildingZone!=null){
			LOGGER.debug(HTMLOutputGenerator.convertSiteToOptions(resultSiteBuildingZone.getLbsLocationSiteList()));
			sb.append("\"site\":\"");
			sb.append(StringEscapeUtils.escapeJavaScript(HTMLOutputGenerator.convertSiteToOptions(resultSiteBuildingZone.getLbsLocationSiteList())));
			sb.append("\",\"building\":\"");
			sb.append(StringEscapeUtils.escapeJavaScript(HTMLOutputGenerator.convertBuildingToOptions(resultSiteBuildingZone.getLbsLocationBuildingList())));
			sb.append("\",\"zone\":\"");
			sb.append(StringEscapeUtils.escapeJavaScript(HTMLOutputGenerator.convertZoneToOptions(resultSiteBuildingZone.getLbsLocationZoneList())));
		}else{
			sb.append("\"site\":\"");
			sb.append("\",\"building\":\"");
			sb.append("\",\"zone\":\"\"");
		}
		
		sb.append("\"floor\":\"");
		if(resultFloor!=null){			
			sb.append(StringEscapeUtils.escapeJavaScript(HTMLOutputGenerator.convertFloorToOptions(resultFloor.getLbsLocationFloorList())));
		}
		sb.append("\"");
		sb.append("}");
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			globalService.releaseSessionHandle(crmSessionHandle);
		}
		
	}*/
	
	
	/**
	 * @param request 
	 * @return List<CannedQuery> 
	 */
	public List<CannedQuery> retrieveCannedQueriesList(PortletRequest request){
		
		CannedQueryContract contract=new CannedQueryContract();
		contract.setCannedId(-1);		
		
		CannedQueriesResult result=cannedQueryService.retrieveCannedQueries(contract);
		List<CannedQuery> queries=result.getCannedQueries();
		for(CannedQuery query:queries){
			LOGGER.debug(query.getJsonString());
			LOGGER.debug(query.getName());
			LOGGER.debug(query.getId());
		}
		return result.getCannedQueries();
	}
	/**
	 * @param request 
	 * @return AddressListResult 
	 */
	private AddressListResult getSiteBuildingFloorZone(PortletRequest request){
		AddressListContract contract=ContractFactory.getLBSAddressContract(request);
		CrmSessionHandle crmSessionHandle = globalService.initCrmSessionHandle(PortalSessionUtil
				.getSiebelCrmSessionHandle(request));

		contract.setSessionHandle(crmSessionHandle);
		ObjectDebugUtil.printObjectContent(contract, LOGGER);
		AddressListResult result=null;
		try{
			result=lbsLocationService.retrieveLBSLocationList(contract);
			LOGGER.debug("res"+result.getLbsLocationSiteList());
		}catch(Exception exception){
			LOGGER.debug("Exception"+exception.getMessage());
			result=new AddressListResult();
			LOGGER.debug("[ Exception occured while retrieving locations ]");
		}/*finally{
			globalService.releaseSessionHandle(crmSessionHandle);
		}*/
		return result;
		
	}
	
	
	/**
	 * @param request 
	 * @return AddressListResult  
	 */
	private AddressListResult getAddressListLocations(PortletRequest request){
		AddressListContract contract=ContractFactory.getLBSAddressContract(request);
		CrmSessionHandle crmSessionHandle = globalService.initCrmSessionHandle(PortalSessionUtil
				.getSiebelCrmSessionHandle(request));

		contract.setSessionHandle(crmSessionHandle);
		ObjectDebugUtil.printObjectContent(contract, LOGGER);
		AddressListResult result=null;
		try{
			result=serviceRequestService.retrieveLBSAddressList(contract);
		}catch(Exception exception){
			LOGGER.debug("Exception"+exception.getMessage());
			result=new AddressListResult();
			LOGGER.debug("[ Exception occured while retrieving locations ]");
		}/*finally{
			globalService.releaseSessionHandle(crmSessionHandle);
		}*/
		return result;
	}
	
	/**
	 * @param response 
	 * @param val 
	 */
	private void writeResonse(ResourceResponse response,String val){
			try {
				final PrintWriter out = response.getWriter();
				response.setProperty("Cache-Control", "max-age=0,no-cache,no-store");
				response.setProperty("Expires", "max-age=0,no-cache,no-store");
				response.setContentType(ChangeMgmtConstant.CONTENTTYPEHTML);
				out.write(val);
				out.flush();
				out.close();
			} catch (IOException e) {
				LOGGER.error("IOException while invoking response#getWriter(),"
						+ e.getMessage());
		}
	}
	
	/*private AddressListResult amindAddresslistLocation(AddressListContract contract){
		ObjectDebugUtil.printObjectContent(contract, LOGGER);
		AddressListResult result=null;
		try{
			result=serviceRequestService.retrieveLBSAddressList(contract);
		}catch(Exception exception){
			result=new AddressListResult();
			LOGGER.debug("[ Exception occured while retrieving locations ]");
		}
		return result;
	}
	
	private AddressListResult amindSiteBuildingFloorZone(AddressListContract contract){
		ObjectDebugUtil.printObjectContent(contract, LOGGER);
		AddressListResult result=null;
		try{
			result=lbsLocationService.retrieveLBSLocationList(contract);
		}catch(Exception exception){
			result=new AddressListResult();
			LOGGER.debug("[ Exception occured while retrieving locations ]");
		}
		return result;
		
	}*/
	
	/**
	 * @param service 
	 */
	public void setCannedQueryService(CannedQueryService service){
		this.cannedQueryService=service;
	}

	/**
	 * @param request 
	 * @param response 
	 */
	@ResourceMapping("getDirectMoveUrl")
	public void getDirectMoveUrl(ResourceRequest request, ResourceResponse response){
		String array[]={"lat","lng","floorNumber","id","buildingId","buildingName","regionId","regionName",
				"address","city","state","country","zipCode",
				"extAddressId","campusId","campusName","floorId","floorName","assetId"};
		Map<String,String> parametermap=new LinkedHashMap<String, String>(); 
		for (int i=0;i<array.length;i++){
			String val=request.getParameter(array[i]);
			parametermap.put(array[i],val==null?"":val);
			LOGGER.debug("array Values--- "+array[i]+"--- "+request.getParameter(array[i]));
		}
		/*DirectMoveClient directMoveClient=new DirectMoveClient();
		directMoveClient.doDirectMove(parametermap);*/
		//CreateDirectMoveService createMove= new CreateDirectMoveServiceImpl();
		String message=createMoveService.doDirectMove(parametermap);
		StringBuffer resp=new StringBuffer("{");
		resp.append("\"message\":\"").append(message).append("\"}");
		writeResonse(response, resp.toString());
		
	}
	
	
}
