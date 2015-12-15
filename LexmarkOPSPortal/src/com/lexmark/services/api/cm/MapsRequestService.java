package com.lexmark.services.api.cm;

import java.util.Map;

import com.lexmark.contract.CreateServiceRequestContract;
import com.lexmark.exceptionimpl.checked.business.LGSBusinessException;
import com.lexmark.exceptionimpl.runtime.LGSRuntimeException;
import com.lexmark.result.CreateServiceRequestResult;

/**
 * @author Wipro 
 * @version 2.1 
 *
 */

public interface MapsRequestService {
	
	/**
	 * 
	 * @param contract
	 * @return
	 * @throws LGSBusinessException
	 * @throws LGSRuntimeException
	 */
	 CreateServiceRequestResult createCMRequest(CreateServiceRequestContract contract,Map<String,String> accountDetails) throws LGSBusinessException, LGSRuntimeException;
}
