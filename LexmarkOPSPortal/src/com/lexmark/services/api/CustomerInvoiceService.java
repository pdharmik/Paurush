package com.lexmark.services.api;

import com.lexmark.contract.InvoiceListContract;
import com.lexmark.result.InvoiceListResult;

public interface CustomerInvoiceService {
	public InvoiceListResult retrieveInvoiceList(InvoiceListContract contract,String[] filterColumns) throws Exception;
}
