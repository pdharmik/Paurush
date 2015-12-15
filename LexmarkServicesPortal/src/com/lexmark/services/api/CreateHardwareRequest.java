package com.lexmark.services.api;

import java.util.Map;

import com.lexmark.contract.CreateHardwareRequestContract;
import com.lexmark.result.CreateServiceRequestResult;

/**
 * @author wipro
 * @version 2.1
 *
 */

public interface CreateHardwareRequest {
	/**
	 * @param contract 
	 * @param accountDetails 
	 * @return CreateServiceRequestResult 
	 * @throws Exception 
	 */
	CreateServiceRequestResult createHardwareRequest(CreateHardwareRequestContract contract, Map<String,String> accountDetails) throws Exception;
	
}