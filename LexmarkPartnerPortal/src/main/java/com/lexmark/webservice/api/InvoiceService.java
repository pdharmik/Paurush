package com.lexmark.webservice.api;

import com.lexmark.contract.InvoiceListContract;
import com.lexmark.result.InvoiceListResult;

public interface InvoiceService {
		public InvoiceListResult retrieveInvoiceList(InvoiceListContract contract) throws Exception;		
}


