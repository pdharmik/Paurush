package com.lexmark.service.impl.real.domain;

import java.io.Serializable;
import java.util.ArrayList;

import com.amind.common.domain.BaseEntity;

/**
 * The mapping file: do-ordersuppliesassetagreemententitlement-mapping.xml
 * 
 */
public class OrderSuppliesAssetAgreementEntitlementDo extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private ArrayList<OrderSuppliesAssetEntitlementDetailDo> entitlementDetails;
    private String entitlementType;
    private String entitlementStatus;
    private String showPrice;
    private String expediteFlag;
    private String agreementId;
    private String quantity;

    public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}


    public ArrayList<OrderSuppliesAssetEntitlementDetailDo> getEntitlementDetails() {
        return entitlementDetails;
    }

    public void setEntitlementDetails(ArrayList<OrderSuppliesAssetEntitlementDetailDo> entitlementDetails) {
        this.entitlementDetails = entitlementDetails;
    }

    public String getEntitlementType() {
        return entitlementType;
    }

    public void setEntitlementType(String entitlementType) {
        this.entitlementType = entitlementType;
    }

    public String getEntitlementStatus() {
        return entitlementStatus;
    }

    public void setEntitlementStatus(String entitlementStatus) {
        this.entitlementStatus = entitlementStatus;
    }


	public String getShowPrice() {
		return showPrice;
	}

	public void setShowPrice(String showPrice) {
		this.showPrice = showPrice;
	}

	public String getExpediteFlag() {
		return expediteFlag;
	}

	public void setExpediteFlag(String expediteFlag) {
		this.expediteFlag = expediteFlag;
	}

	public String getAgreementId() {
		return agreementId;
	}

	public void setAgreementId(String agreementId) {
		this.agreementId = agreementId;
	}
}
