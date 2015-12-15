package com.lexmark.domain;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author vpetruchok
 * 
 * @version 1.0, 2012-10-10
 */

public class AccountPayableDetailActivity implements Serializable {

    private static final long serialVersionUID = 1L;

    private String partNumber;
    private String partDescription;
    private int quantity;
    private BigDecimal partFee;
    private BigDecimal fulfillmentFee;
    private BigDecimal amount;
    private BigDecimal partnerFee;
    private String eligibilityStatus;

    public String getPartNumber() {
        return partNumber;
    }

    public void setPartNumber(String partNumber) {
        this.partNumber = partNumber;
    }

    public String getPartDescription() {
        return partDescription;
    }

    public void setPartDescription(String partDescription) {
        this.partDescription = partDescription;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getEligibilityStatus() {
        return eligibilityStatus;
    }

    public void setEligibilityStatus(String eligibilityStatus) {
        this.eligibilityStatus = eligibilityStatus;
    }

	public BigDecimal getPartFee() {
		return partFee;
	}

	public void setPartFee(BigDecimal partFee) {
		this.partFee = partFee;
	}

	public BigDecimal getFulfillmentFee() {
		return fulfillmentFee;
	}

	public void setFulfillmentFee(BigDecimal fulfillmentFee) {
		this.fulfillmentFee = fulfillmentFee;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getPartnerFee() {
		return partnerFee;
	}

	public void setPartnerFee(BigDecimal partnerFee) {
		this.partnerFee = partnerFee;
	}

   
    
}
