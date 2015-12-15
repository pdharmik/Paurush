/**
 * Copyright@ 2012. This document has been created & written by
 * Wipro technologies for Lexmark and this is copyright to Lexmark
 *
 * File         	: ManageContactService.java
 * Package     		: com.lexmark.services.api.cm
 * Creation Date 	: 2013
 *
 * Modification History:
 *
 * ---------------------------------------------------------------------
 * Author 				Date				Version  		Comments
 * ---------------------------------------------------------------------
 * Wipro		2013 		2.1             Initial Version
 * 
 */

package com.lexmark.services.api.cm;

import java.util.Map;

import com.lexmark.contract.CreateServiceRequestContract;
import com.lexmark.contract.ProcessCustomerContactContract;
import com.lexmark.exceptionimpl.checked.business.LGSBusinessException;
import com.lexmark.exceptionimpl.runtime.LGSRuntimeException;
import com.lexmark.result.CreateServiceRequestResult;
import com.lexmark.result.ProcessCustomerContactResult;

/**
 * this is the interface class for ManageContactController
 * @version 2.1
 * @author wipro
 */
public interface ManageContactService {

//	public CreateServiceRequestResult createManageContactRequest(CreateServiceRequestContract serviceReqContract) throws LGSRuntimeException,
//	LGSCheckedException, Exception;
	/**
	 * 
	 * @param serviceReqContract
	 * @param accountDetails
	 * @return
	 * @throws LGSRuntimeException
	 * @throws LGSBusinessException
	 */
	CreateServiceRequestResult createCMRequestService(CreateServiceRequestContract serviceReqContract,Map<String,String> accountDetails)
			throws LGSRuntimeException,
	LGSBusinessException;
	//Changes for MPS 2
	/**
	 * 
	 * @param contract
	 * @return
	 * @throws LGSRuntimeException
	 * @throws LGSBusinessException
	 */
	ProcessCustomerContactResult addAndUpdateContactService(ProcessCustomerContactContract contract)
			throws LGSRuntimeException,LGSBusinessException;
	//Ends Phase 2
}
