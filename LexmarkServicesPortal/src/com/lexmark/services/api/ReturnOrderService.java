package com.lexmark.services.api;

import java.util.Map;

// import com.lexmark.contract.CreateConsumableServiceRequestContract;
import com.lexmark.contract.source.ReturnServiceRequestContract;
import com.lexmark.framework.exception.LGSServiceException;
import com.lexmark.result.CreateServiceRequestResult;

/**.
 * Service class for return requests.
 * @author gsarkar
 *
 */
public interface ReturnOrderService {

	/**
	 * @param contract 
	 * @param accountDetails 
	 * @param isVendorFlag 
	 * @return CreateServiceRequestResult 
	 * @throws LGSServiceException 
	 * @throws Exception 
	 */
	CreateServiceRequestResult createChangeManagementServiceRequest(ReturnServiceRequestContract contract,
			Map<String,String> accountDetails,boolean isVendorFlag) throws LGSServiceException,Exception;
	
}
