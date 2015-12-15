package com.lexmark.service.api;

import com.lexmark.contract.RequestContract;
import com.lexmark.contract.RequestListContract;
import com.lexmark.result.RequestListResult;
import com.lexmark.result.RequestResult;

public interface RequestTypeServiceB2B {
    
	public RequestListResult retrieveRequestListB2B(RequestListContract contract) throws Exception;
	
	public RequestResult retrieveSupplyRequestDetailB2B(RequestContract contract) throws Exception;
	
}
