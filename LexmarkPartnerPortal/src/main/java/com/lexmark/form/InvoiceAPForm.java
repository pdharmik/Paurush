package com.lexmark.form;

import java.io.Serializable;

import com.lexmark.domain.Invoice;
import com.lexmark.domain.Order;
import com.lexmark.form.BaseForm;

public class InvoiceAPForm extends BaseForm implements Serializable{
	private static final long serialVersionUID = -2540522215207793737L;
	private Invoice invoiceDetail;	
	private String invoiceListXML;
	public Invoice getInvoiceDetail() {
		return invoiceDetail;
	}
	public void setInvoiceDetail(Invoice invoiceDetail) {
		this.invoiceDetail = invoiceDetail;
	}
	public String getInvoiceListXML() {
		return invoiceListXML;
	}
	public void setInvoiceListXML(String invoiceListXML) {
		this.invoiceListXML = invoiceListXML;
	}
}
