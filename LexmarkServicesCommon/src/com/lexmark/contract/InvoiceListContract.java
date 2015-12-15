package com.lexmark.contract;

import java.io.Serializable;
import java.util.Date;

import com.lexmark.contract.api.SearchContractBase;

public class InvoiceListContract extends SearchContractBase implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String vendorID;
	private String companyCode;
	private Date fromDate;
	private Date toDate;
	private String clearedItem;
	private String openItem;
	private String selectForPayments;
	private String onlyInvoice;
	private String parkedItems;
	private String checkDisputes;
	private String prevBalance;
	private boolean invoiceAP;
	
	private String soldToNumber;
	private String status;
	private String isInvoice;
	private String invoiceNum;
	
	
	public String getVendorID() {
		return vendorID;
	}
	public void setVendorID(String vendorID) {
		this.vendorID = vendorID;
	}
	public String getCompanyCode() {
		return companyCode;
	}
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}
	
	public String getOpenItem() {
		return openItem;
	}
	public void setOpenItem(String openItem) {
		this.openItem = openItem;
	}
	public String getClearedItem() {
		return clearedItem;
	}
	public void setClearedItem(String clearedItem) {
		this.clearedItem = clearedItem;
	}
	public String getSelectForPayments() {
		return selectForPayments;
	}
	public void setSelectForPayments(String selectForPayments) {
		this.selectForPayments = selectForPayments;
	}
	public String getOnlyInvoice() {
		return onlyInvoice;
	}
	public void setOnlyInvoice(String onlyInvoice) {
		this.onlyInvoice = onlyInvoice;
	}
	public Date getFromDate() {
		return fromDate;
	}
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}
	public Date getToDate() {
		return toDate;
	}
	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}
	public boolean isInvoiceAP() {
		return invoiceAP;
	}
	public void setInvoiceAP(boolean invoiceAP) {
		this.invoiceAP = invoiceAP;
	}
	public String getParkedItems() {
		return parkedItems;
	}
	public void setParkedItems(String parkedItems) {
		this.parkedItems = parkedItems;
	}
	public String getCheckDisputes() {
		return checkDisputes;
	}
	public void setCheckDisputes(String checkDisputes) {
		this.checkDisputes = checkDisputes;
	}
	public String getPrevBalance() {
		return prevBalance;
	}
	public void setPrevBalance(String prevBalance) {
		this.prevBalance = prevBalance;
	}
	public void setSoldToNumber(String soldToNumber) {
		this.soldToNumber = soldToNumber;
	}
	public String getSoldToNumber() {
		return soldToNumber;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getStatus() {
		return status;
	}
	public void setIsInvoice(String isInvoice) {
		this.isInvoice = isInvoice;
	}
	public String getIsInvoice() {
		return isInvoice;
	}
	public void setInvoiceNum(String invoiceNum) {
		this.invoiceNum = invoiceNum;
	}
	public String getInvoiceNum() {
		return invoiceNum;
	}
	

}
