package com.lexmark.service.impl.real.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;

import com.amind.common.domain.BaseEntity;
import com.lexmark.util.LangUtil;

/**
 * 
 * The mapping file: do-accountpayabledetail-mapping.xml
 * 
 * @author vpetruchok
 * @version 1.0, 2012-10-10
 */
public class AccountPayableDetailDo extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private String mdmLevel1AccountId;
    private String mdmLevel2AccountId;
    private String mdmLevel3AccountId;
    private String mdmLevel4AccountId;
    private String mdmLevel5AccountId;
    private String mdmLevel;

    private String serviceRequestNumber;
    private String vendorId;
    private String customerAccountName;
    
    private String partFee;
    private String fulfillmentFee;
    private String laborPayment;
    private String additionalPayments;
    private String extendedLineTotal;
    private ArrayList<AccountPayableDetailActivityDo> activities;
    private String lxkMpsType;

    public String getMdmLevel1AccountId() {
        return mdmLevel1AccountId;
    }

    public void setMdmLevel1AccountId(String mdmLevel1AccountId) {
        this.mdmLevel1AccountId = mdmLevel1AccountId;
    }

    public String getMdmLevel2AccountId() {
        return mdmLevel2AccountId;
    }

    public void setMdmLevel2AccountId(String mdmLevel2AccountId) {
        this.mdmLevel2AccountId = mdmLevel2AccountId;
    }

    public String getMdmLevel3AccountId() {
        return mdmLevel3AccountId;
    }

    public void setMdmLevel3AccountId(String mdmLevel3AccountId) {
        this.mdmLevel3AccountId = mdmLevel3AccountId;
    }

    public String getMdmLevel4AccountId() {
        return mdmLevel4AccountId;
    }

    public void setMdmLevel4AccountId(String mdmLevel4AccountId) {
        this.mdmLevel4AccountId = mdmLevel4AccountId;
    }

    public String getMdmLevel5AccountId() {
        return mdmLevel5AccountId;
    }

    public void setMdmLevel5AccountId(String mdmLevel5AccountId) {
        this.mdmLevel5AccountId = mdmLevel5AccountId;
    }

    public String getMdmLevel() {
        return mdmLevel;
    }

    public void setMdmLevel(String mdmLevel) {
        this.mdmLevel = mdmLevel;
    }

    public String getServiceRequestNumber() {
        return serviceRequestNumber;
    }

    public void setServiceRequestNumber(String serviceRequestNumber) {
        this.serviceRequestNumber = serviceRequestNumber;
    }

    public String getVendorId() {
        return vendorId;
    }

    public void setVendorId(String vendorId) {
        this.vendorId = vendorId;
    }

    public String getCustomerAccountName() {
        return customerAccountName;
    }

    public void setCustomerAccountName(String customerAccountName) {
        this.customerAccountName = customerAccountName;
    }

    public ArrayList<AccountPayableDetailActivityDo> getActivities() {
        return activities;
    }

    public void setActivities(ArrayList<AccountPayableDetailActivityDo> activities) {
        this.activities = activities;
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

	public BigDecimal getLaborPayment() {
		return LangUtil.convertStringToBigDecimal(laborPayment,2);
	}

	public void setLaborPayment(String laborPayment) {
		this.laborPayment = laborPayment;
	}

	public BigDecimal getAdditionalPayments() {
		return LangUtil.convertStringToBigDecimal(additionalPayments,2);
	}

	public void setAdditionalPayments(String additionalPayments) {
		this.additionalPayments = additionalPayments;
	}

	public void setExtendedLineTotal(String extendedLineTotal) {
		this.extendedLineTotal = extendedLineTotal;
	}

	public BigDecimal getExtendedLineTotal() {
		return LangUtil.convertStringToBigDecimal(extendedLineTotal,2);
	}
	
    public String getLxkMpsType() {
        return lxkMpsType;
    }
  
    public void setLxkMpsType(String lxkMpsType) {
        this.lxkMpsType = lxkMpsType;
    }
}
