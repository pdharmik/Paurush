package com.lexmark.contract;

import com.lexmark.contract.api.CachingContract;


public class ProductImageContract extends CachingContract {
    
    private static final long serialVersionUID = 1L;
    
    private String partNumber;

	@Override
	public String getCacheKey() {
		return partNumber;
	}

	public String getPartNumber() {
		return partNumber;
	}

	public void setPartNumber(String partNumber) {
		this.partNumber = partNumber;
	}
	
}
