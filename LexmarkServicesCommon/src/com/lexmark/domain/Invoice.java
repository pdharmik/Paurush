package com.lexmark.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Invoice implements Serializable{
	
	private static final long serialVersionUID = 877635713641960634L;
	private String invoiceNumber;
	private Date invoiceDate;
	private Date dueDate;
	private String paymentNumber;
	private String chequeNumber;
	private Date paidDate;
	private BigDecimal amount;
	private String currencyType;
	//added field status for customerARInvoice-Parth
	private String status;
	//Customer Invoice MPS 2.1
	private String sapDocId1;
	private String sapDocId2;
	private String invoiceDetail;
	//Ends MPS 2.1
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getInvoiceNumber() {
		return invoiceNumber;
	}
	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}
	public Date getInvoiceDate() {
		return invoiceDate;
	}
	public void setInvoiceDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}
	public Date getDueDate() {
		return dueDate;
	}
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	public String getPaymentNumber() {
		return paymentNumber;
	}
	public void setPaymentNumber(String paymentNumber) {
		this.paymentNumber = paymentNumber;
	}
	public String getChequeNumber() {
		return chequeNumber;
	}
	public void setChequeNumber(String chequeNumber) {
		this.chequeNumber = chequeNumber;
	}
	public Date getPaidDate() {
		return paidDate;
	}
	public void setPaidDate(Date paidDate) {
		this.paidDate = paidDate;
	}	
	public String getCurrencyType() {
		return currencyType;
	}
	public void setCurrencyType(String currencyType) {
		this.currencyType = currencyType;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public void setInvoiceDetail(String invoiceDetail) {
		this.invoiceDetail = invoiceDetail;
	}
	public String getInvoiceDetail() {
		return invoiceDetail;
	}
	public void setSapDocId1(String sapDocId1) {
		this.sapDocId1 = sapDocId1;
	}
	public String getSapDocId1() {
		return sapDocId1;
	}
	public void setSapDocId2(String sapDocId2) {
		this.sapDocId2 = sapDocId2;
	}
	public String getSapDocId2() {
		return sapDocId2;
	}
	

}
