package com.lexmark.services.api;

import com.lexmark.contract.PriceContract;
import com.lexmark.result.PriceResult;

/**
 * @author wipro
 * @version 2.1
 *
 */
public interface RetrievePriceService {
	/**.
	 * 
	 * This is the retrieve PriceList method if overriden 
	 * 
	 * @param contract 
	 * @return PriceResult 
	 * @throws Exception Exception 
	 */
	PriceResult retrievePriceList(PriceContract contract) throws Exception;

}
