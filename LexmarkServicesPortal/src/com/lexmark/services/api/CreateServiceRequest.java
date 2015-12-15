package com.lexmark.services.api;

import com.lexmark.contract.CreateServiceRequestContract;
import com.lexmark.result.CreateServiceRequestResult;

public interface CreateServiceRequest {
	/**
	 * @param contract 
	 * @return CreateServiceRequestResult 
	 * @throws Exception 
	 */
	CreateServiceRequestResult createServiceRequest(CreateServiceRequestContract contract) throws Exception;
	//public CreateServiceRequestResult createServiceRequestForCHLOthers(CreateServiceRequestContract contract,String area,String subArea) throws Exception;
}
