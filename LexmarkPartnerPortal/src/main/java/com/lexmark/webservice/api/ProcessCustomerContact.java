package com.lexmark.webservice.api;

import com.lexmark.contract.ProcessCustomerContactContract;
import com.lexmark.result.ProcessCustomerContactResult;

/**
 * @author wipro
 * @version 2.1
 *
 */

public interface ProcessCustomerContact {

	 ProcessCustomerContactResult processCustomerContact(ProcessCustomerContactContract contract) throws Exception;
}
