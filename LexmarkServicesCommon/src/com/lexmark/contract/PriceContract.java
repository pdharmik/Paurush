package com.lexmark.contract;

import java.util.List;

import com.lexmark.domain.Price;

public class PriceContract {
	private String contractNumber;	
	private String poNumber;
	private List<Price> priceList;
	
	

	public String getContractNumber() {
		return contractNumber;
	}

	public void setContractNumber(String contractNumber) {
		this.contractNumber = contractNumber;
	}

	
	
	public List<Price> getPriceList() {
		return priceList;
	}

	public void setPriceList(List<Price> priceList) {
		this.priceList = priceList;
	}

	public String getPoNumber() {
		return poNumber;
	}

	public void setPoNumber(String poNumber) {
		this.poNumber = poNumber;
	}
	
	public String toString(){
		StringBuffer sb=new StringBuffer();
		if(this.getContractNumber()!=null){
			sb.append(this.getContractNumber());
		}
		if(this.getPoNumber()!=null){
			sb.append(this.getPoNumber());
		}
		List<Price> prices=this.getPriceList();
		if(prices!=null){
			for(Price price:prices){
				sb.append(price.getContractLineItemId());
			}
		}
		return sb.toString();
	}
}
