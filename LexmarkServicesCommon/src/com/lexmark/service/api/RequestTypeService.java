package com.lexmark.service.api;

import com.lexmark.contract.LocationReportingHierarchyContract;
import com.lexmark.contract.RequestContract;
import com.lexmark.contract.RequestListContract;
import com.lexmark.result.RequestListResult;
import com.lexmark.result.RequestLocationsResult;
import com.lexmark.result.RequestResult;

public interface RequestTypeService {
    
	public RequestListResult retrieveRequestList(RequestListContract contract) throws Exception;
	
	public RequestResult retrieveSupplyRequestDetail(RequestContract contract) throws Exception;
	
	public RequestLocationsResult retrieveRequestLocations(LocationReportingHierarchyContract contract) throws Exception;
	
	public RequestResult retrieveAccountContactDetail(RequestContract contract) throws Exception;
	
	public RequestResult retrieveSupplyRequestActivities(RequestContract contract) throws Exception;

	public RequestListResult retrieveMadcRequestList(
			RequestListContract contract) throws Exception;
}
