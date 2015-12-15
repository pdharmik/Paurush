package com.lexmark.services.webService.api;

import com.lexmark.contract.CreateServiceRequestB2bContract;
import com.lexmark.result.CreateServiceRequestResult;
import com.lexmark.services.domain.SessionInformation;

public interface CreateServiceRequestB2B {
	/**
	 * @param information 
	 * @param contract 
	 * @return CreateServiceRequestResult 
	 * @throws Exception 
	 */
	 CreateServiceRequestResult createServiceRequestB2B(SessionInformation information,CreateServiceRequestB2bContract contract)throws Exception;
}
