package com.lexmark.services.api.om;

import com.lexmark.contract.PriceContract;
import com.lexmark.result.PriceResult;

/**
 * @author wipro
 * @version 2.1
 *
 */
public interface RetrievePriceService {
	
	PriceResult retrievePriceList(PriceContract contract) throws Exception;

}
