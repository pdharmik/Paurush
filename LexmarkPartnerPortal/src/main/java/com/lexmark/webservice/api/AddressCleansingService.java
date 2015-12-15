package com.lexmark.webservice.api;

import java.util.Map;

import com.lexmark.domain.GenericAddress;
import com.lexmark.exceptionimpl.checked.business.LGSBusinessException;
import com.lexmark.exceptionimpl.runtime.LGSRuntimeException;

/**
 * @author Wipro
 * @version 2.1
 */

public interface AddressCleansingService {
	 GenericAddress addressCleanse(GenericAddress addressDataInput, Map<String,String> accountDetails)
			throws LGSRuntimeException, LGSBusinessException;
}
