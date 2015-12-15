package com.lexmark.result;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.lexmark.domain.PartnerAgreement;

public class PartnerAgreementListResult implements Serializable {
    
    private static final long serialVersionUID = 1L;
	private List<PartnerAgreement> partnerAgreementList = new ArrayList<PartnerAgreement>();

	public List<PartnerAgreement> getPartnerAgreementList() {
		return partnerAgreementList;
	}

	public void setPartnerAgreementList(List<PartnerAgreement> partnerAgreementList) {
		this.partnerAgreementList = partnerAgreementList;
	}
}
