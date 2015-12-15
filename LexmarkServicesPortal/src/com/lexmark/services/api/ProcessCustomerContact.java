package com.lexmark.services.api;

import com.lexmark.contract.ProcessCustomerContactContract;
import com.lexmark.result.ProcessCustomerContactResult;

/**
 * @author wipro
 * @version 2.1
 *
 */

public interface ProcessCustomerContact {

	 /**
	 * @param contract 
	 * @return ProcessCustomerContactResult  
	 * @throws Exception 
	 */
	ProcessCustomerContactResult processCustomerContact(ProcessCustomerContactContract contract) throws Exception;
}
