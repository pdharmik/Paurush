package com.lexmark.service.impl.real.domain;

import java.io.Serializable;

import com.amind.common.domain.BaseEntity;
import java.util.Date;
/**
 * The mapping file: do-ordersuppliesassetpartpaymentdetail-mapping.xml
 * 
 * @see com.lexmark.service.impl.real.domain#OrderSuppliesAssetDetailDo
 * @see com.lexmark.service.impl.real.orderSuppliesAsset.AmindOrderSuppliesAssetServiceUtil#toAsset(OrderSuppliesAssetDetailDo)
 */
public class OrderSuppliesAssetPartPaymentDetailDo extends BaseEntity implements Serializable {
	private static final long serialVersionUID = -7824721088426974811L;

	private String soldToNumber;
	private String paymentType;
	private String salesOrg;
	private String contractNumber;
	private String contractLineItem;
	
	
	public String getSoldToNumber() {
		return soldToNumber;
	}

	public void setSoldToNumber(String soldToNumber) {
		this.soldToNumber = soldToNumber;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public String getSalesOrg() {
		return salesOrg;
	}

	public void setSalesOrg(String salesOrg) {
		this.salesOrg = salesOrg;
	}

	public String getContractNumber() {
		return contractNumber;
	}

	public void setContractNumber(String contractNumber) {
		this.contractNumber = contractNumber;
	}

	public String getContractLineItem() {
		return contractLineItem;
	}

	public void setContractLineItem(String contractLineItem) {
		this.contractLineItem = contractLineItem;
	}
	
	

}
