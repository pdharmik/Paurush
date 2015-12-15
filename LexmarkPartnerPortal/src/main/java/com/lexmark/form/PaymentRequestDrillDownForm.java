package com.lexmark.form;

import java.math.BigDecimal;
import java.util.List;

import com.lexmark.domain.AccountPayableDetailActivity;

public class PaymentRequestDrillDownForm extends BaseForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1587823788309793822L;
	private String requestNumber;
    private String customerAccountName;
    private BigDecimal totalPartFee;
    private BigDecimal totalFulfillmentFee;
    private BigDecimal totalLabor;
    private BigDecimal totalAdditionalPayments;
    private String eligibilityStatus;
    private List<AccountPayableDetailActivity> activities;
	public String getRequestNumber() {
		return requestNumber;
	}
	public void setRequestNumber(String requestNumber) {
		this.requestNumber = requestNumber;
	}
	public String getCustomerAccountName() {
		return customerAccountName;
	}
	public void setCustomerAccountName(String customerAccountName) {
		this.customerAccountName = customerAccountName;
	}
	public BigDecimal getTotalPartFee() {
		return totalPartFee;
	}
	public void setTotalPartFee(BigDecimal totalPartFee) {
		this.totalPartFee = totalPartFee;
	}
	public BigDecimal getTotalFulfillmentFee() {
		return totalFulfillmentFee;
	}
	public void setTotalFulfillmentFee(BigDecimal totalFulfillmentFee) {
		this.totalFulfillmentFee = totalFulfillmentFee;
	}
	public BigDecimal getTotalLabor() {
		return totalLabor;
	}
	public void setTotalLabor(BigDecimal totalLabor) {
		this.totalLabor = totalLabor;
	}
	public BigDecimal getTotalAdditionalPayments() {
		return totalAdditionalPayments;
	}
	public void setTotalAdditionalPayments(BigDecimal totalAdditionalPayments) {
		this.totalAdditionalPayments = totalAdditionalPayments;
	}
	public String getEligibilityStatus() {
		return eligibilityStatus;
	}
	public void setEligibilityStatus(String eligibilityStatus) {
		this.eligibilityStatus = eligibilityStatus;
	}
	public List<AccountPayableDetailActivity> getActivities() {
		return activities;
	}
	public void setActivities(List<AccountPayableDetailActivity> activities) {
		this.activities = activities;
	}
    

}
