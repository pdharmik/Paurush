package com.lexmark.services.api;

import java.util.Map;

import com.lexmark.contract.CreateConsumableServiceRequestContract;
import com.lexmark.result.CreateServiceRequestResult;

/**
 * @author wipro
 * @version 2.1
 *
 */
public interface CreateConsumableRequest {
	CreateServiceRequestResult createConsumableServiceRequest(CreateConsumableServiceRequestContract contract, Map<String,String> accountDetails, boolean isVendorFlag) throws Exception;
	
	//CreateServiceRequestResult updateConsumableServiceRequest(CreateConsumableServiceRequestContract contract, Map<String,String> accountDetails, boolean isVendorFlag) throws Exception;
}
