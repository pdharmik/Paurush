package com.lexmark.contract;

import java.io.Serializable;

import com.lexmark.contract.api.ContractBase;

public class SiebelAccountListContract extends ContractBase implements
        Serializable {

    private static final long serialVersionUID = 4091606442276979875L;

    private String mdmId;
    private String mdmLevel;
    private boolean newQueryIndicator;
    private boolean vendorFlag;
    private boolean agreementFlag;
    private String accountId;
	/*Added for MPS 2.1 Payment Type mock call
	  Needs to be removed*/
    private String soldToNumber;
    private String agreementId;
    private boolean hardwareFlag;
    private String contractNumber;
    private boolean lbsFlag;
    private boolean partnerPortal;
    
    public boolean isPartnerPortal() {
		return partnerPortal;
	}

	public void setPartnerPortal(boolean partnerPortal) {
		this.partnerPortal = partnerPortal;
	}

	public boolean isNewQueryIndicator() {
        return newQueryIndicator;
    }

    public void setNewQueryIndicator(boolean newQueryIndicator) {
        this.newQueryIndicator = newQueryIndicator;
    }

    public String getMdmId() {
        return mdmId;
    }

    public void setMdmId(String mdmId) {
        this.mdmId = mdmId;
    }

    public String getMdmLevel() {
        return mdmLevel;
    }

    public void setMdmLevel(String mdmLevel) {
        this.mdmLevel = mdmLevel;
    }
    
	public void setVendorFlag(boolean vendorFlag) {
		this.vendorFlag = vendorFlag;
	}

	public boolean isVendorFlag() {
		return vendorFlag;
	}

	public void setAgreementFlag(boolean agreementFlag) {
		this.agreementFlag = agreementFlag;
	}

	public boolean isAgreementFlag() {
		return agreementFlag;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	
	/*Added for MPS 2.1 Payment Type mock call
	  Needs to be removed*/
	public String getSoldToNumber() {
		return soldToNumber;
	}

	public void setSoldToNumber(String soldToNumber) {
		this.soldToNumber = soldToNumber;
	}

	public String getAgreementId() {
		return agreementId;
	}

	public void setAgreementId(String agreementId) {
		this.agreementId = agreementId;
	}

	public boolean isHardwareFlag() {
		return hardwareFlag;
	}

	public void setHardwareFlag(boolean hardwareFlag) {
		this.hardwareFlag = hardwareFlag;
	}

	public String getContractNumber() {
		return contractNumber;
	}

	public void setContractNumber(String contractNumber) {
		this.contractNumber = contractNumber;
	}

	/**
	 * @param lbsFlag the lbsFlag to set
	 */
	public void setLbsFlag(boolean lbsFlag) {
		this.lbsFlag = lbsFlag;
	}

	/**
	 * @return the lbsFlag
	 */
	public boolean isLbsFlag() {
		return lbsFlag;
	}
}
