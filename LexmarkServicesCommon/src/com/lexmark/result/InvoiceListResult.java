package com.lexmark.result;

import java.io.Serializable;
import java.util.List;

import com.lexmark.domain.Invoice;

public class InvoiceListResult implements Serializable {
	
    private static final long serialVersionUID = 1L;
	private List<Invoice> invoice;

	public List<Invoice> getInvoice() {
		return invoice;
	}

	public void setInvoice(List<Invoice> invoice) {
		this.invoice = invoice;
	}

	
}
