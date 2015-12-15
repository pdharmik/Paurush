package com.lexmark.service.impl.real.domain;

import java.io.Serializable;
import java.util.ArrayList;

import com.amind.common.domain.BaseEntity;

/**
 * @author i.prikhodko
 * 
 * do-mapping: "do-agreementsoldtonumberdo-mapping.xml"
 *
 */

public class AgreementSoldToNumberDo extends BaseEntity implements Serializable {

	private static final long serialVersionUID = -9049008894701807426L;
	
	private String agreementId;
	private ArrayList<SoldToNumberDo> soldToNumbers;
	
	public String getAgreementId() {
		return agreementId;
	}
	public void setAgreementId(String agreementId) {
		this.agreementId = agreementId;
	}
	public ArrayList<SoldToNumberDo> getSoldToNumbers() {
		return soldToNumbers;
	}
	public void setSoldToNumbers(ArrayList<SoldToNumberDo> soldToNumbers) {
		this.soldToNumbers = soldToNumbers;
	}
}
