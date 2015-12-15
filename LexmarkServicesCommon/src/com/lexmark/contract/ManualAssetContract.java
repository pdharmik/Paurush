package com.lexmark.contract;

import java.io.Serializable;

import com.lexmark.contract.api.ContractBase;

public class ManualAssetContract extends ContractBase implements Serializable {

	private static final long serialVersionUID = 3438250893252552846L;
	
	private String modelNumber;
	private String productTLI;
	
	public void setModelNumber(String modelNumber) {
		this.modelNumber = modelNumber;
	}
	public String getModelNumber() {
		return modelNumber;
	}
	public void setProductTLI(String productTLI) {
		this.productTLI = productTLI;
	}
	public String getProductTLI() {
		return productTLI;
	}
}
