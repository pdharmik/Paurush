/**
 * Copyright@ 2012. This document has been created & written by
 * Wipro technologies for Lexmark and this is copyright to Lexmark
 *
 * File         	: AddressCleansingService
 * Package     		: com.lexmark.services.api.cm
 * Creation Date 	: 30th July 2012
 *
 * Modification History:
 *
 * ---------------------------------------------------------------------
 * Author 			Date				Version  		Comments
 * ---------------------------------------------------------------------
 * Sourav		30th July 2012 		1.0             Initial Version
 *
 */
package com.lexmark.service.api;

import java.util.Map;

import com.lexmark.domain.GenericAddress;
import com.lexmark.exceptionimpl.checked.business.LGSBusinessException;
import com.lexmark.exceptionimpl.runtime.LGSRuntimeException;

/**
 * @author Wipro
 *
 */
public interface AddressCleansingService {

	/**
	 * @param addressDataInput 
	 * @param accountDetails 
	 * @return GenericAddress 
	 * @throws LGSRuntimeException 
	 * @throws LGSBusinessException 
	 */
	public GenericAddress addressCleanse(GenericAddress addressDataInput, 
			Map<String,String> accountDetails)throws LGSRuntimeException,
	LGSBusinessException;
}
