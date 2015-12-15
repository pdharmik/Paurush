package com.lexmark.services.api.om;

import com.lexmark.contract.TaxContract;
import com.lexmark.result.TaxResult;

/**
 * @author wipro
 * @version 2.1
 *
 */
public interface RetrieveTaxService {
	
	/**
	 * @param contract
	 * @return
	 * @throws Exception
	 */
	TaxResult retrieveTaxList(TaxContract contract) throws Exception;
	
}
