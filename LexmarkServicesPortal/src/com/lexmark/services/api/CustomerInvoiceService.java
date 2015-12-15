package com.lexmark.services.api;

import com.lexmark.contract.InvoiceListContract;
import com.lexmark.result.InvoiceListResult;

public interface CustomerInvoiceService {
	/**
	 * @param contract 
	 * @param filterColumns 
	 * @return InvoiceListResult 
	 * @throws Exception 
	 */
	InvoiceListResult retrieveInvoiceList(InvoiceListContract contract,String[] filterColumns) throws Exception;
}
