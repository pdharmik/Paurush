/**
 * Copyright@ 2012. This document has been created & written by
 * Wipro technologies for Lexmark and this is copyright to Lexmark
 *
 * File         	: AssetAcceptanceService.java
 * Package     		: com.lexmark.services.api.cm
 * Creation Date 	: 2013
 *
 * Modification History:
 *
 * ---------------------------------------------------------------------
 * Author 				Date				Version  		Comments
 * ---------------------------------------------------------------------
 * wipro		2013 		1.0             Initial Version
 * 
 */

package com.lexmark.services.api.cm;

import java.util.Map;
import com.lexmark.contract.CreateServiceRequestContract;
import com.lexmark.exceptionimpl.checked.business.LGSBusinessException;
import com.lexmark.exceptionimpl.runtime.LGSRuntimeException;
import com.lexmark.result.CreateServiceRequestResult;

/**
 * This is an interface class for webService call
 */
public interface AssetAcceptanceService {
	
	/**
	 * This method is implemented in AssetAcceptanceServiceImpl.java class
	 * @param contract
	 * @param accountDetails
	 * @return
	 * @throws LGSBusinessException
	 * @throws LGSRuntimeException
	 */
	 CreateServiceRequestResult createCMRequest(CreateServiceRequestContract contract,Map<String,String> accountDetails)
			throws LGSBusinessException, LGSRuntimeException;

}
