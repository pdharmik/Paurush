package com.lexmark.services.api.om;

import com.lexmark.contract.PriceContract;
import com.lexmark.result.PriceResult;

public interface RetrieveTonerPriceService {
	
	public PriceResult retrieveTonerPriceList(PriceContract contract) throws Exception;

}
