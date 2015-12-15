package com.lexmark.service.impl.real.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import com.amind.common.domain.BaseEntity;
import com.lexmark.util.LangUtil;

/**
 * 
 * The mapping file: do-accountpayabledetailactivity-mapping.xml
 * .
 * 
 * @see com.lexmark.service.impl.real.domain.AccountPayableDetailDo
 * 
 * 
 * @author vpetruchok
 * @version 1.0, 2012-10-10
 */
public class AccountPayableDetailActivityDo extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private String partNumber;
    private String partDescription;
    private String quantity;
    private String partFee;
    private String fulfillmentFee;
    private String amount;
    private String eligibilityStatus;
    private String partnerFee;
    private String recommendedQuantity;

    
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
    	if(quantity != null && !quantity.isEmpty())
    		return Integer.parseInt(quantity);
    	else
    		return 0;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPartFee() {
    	return LangUtil.convertStringToBigDecimal(partFee,2);
    }

    public void setPartFee(String partFee) {
        this.partFee = partFee;
    }

    public BigDecimal getFulfillmentFee() {
    	return LangUtil.convertStringToBigDecimal(fulfillmentFee,2);
        
    }

    public void setFulfillmentFee(String fulfillmentFee) {
        this.fulfillmentFee = fulfillmentFee;
    }

    public BigDecimal getAmount() {
    	return LangUtil.convertStringToBigDecimal(amount,2);
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getEligibilityStatus() {
        return eligibilityStatus;
    }

    public void setEligibilityStatus(String eligibilityStatus) {
        this.eligibilityStatus = eligibilityStatus;
    }

    public BigDecimal getPartnerFee() {
        return LangUtil.convertStringToBigDecimal(partnerFee,2);
    }

    public void setPartnerFee(String partnerFee) {
        this.partnerFee = partnerFee;
    }

    public String getRecommendedQuantity() {
        return recommendedQuantity;
    }

    public void setRecommendedQuantity(String recommendedQuantity) {
        this.recommendedQuantity = recommendedQuantity;
    }
}
