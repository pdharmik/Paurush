package com.lexmark.result;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.lexmark.domain.ListOfValues;
import com.lexmark.domain.OrderPart;

public class CatalogListResult implements Serializable {
	private static final long serialVersionUID = 8022882274067805950L;
	private int totalCount;
	private List<OrderPart> partsList = new ArrayList<OrderPart>();
	private List<OrderPart> accessoriesList = new ArrayList<OrderPart>();
	private List<OrderPart> suppliesList = new ArrayList<OrderPart>();
	private List<ListOfValues> lovList = new ArrayList<ListOfValues>();
	private String agreementId;
	
	
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public List<OrderPart> getPartsList() {
		return partsList;
	}
	public void setPartsList(List<OrderPart> partsList) {
		this.partsList = partsList;
	}
	public List<OrderPart> getAccessoriesList() {
		return accessoriesList;
	}
	public void setAccessoriesList(List<OrderPart> accessoriesList) {
		this.accessoriesList = accessoriesList;
	}
	public List<OrderPart> getSuppliesList() {
		return suppliesList;
	}
	public void setSuppliesList(List<OrderPart> suppliesList) {
		this.suppliesList = suppliesList;
	}
	public List<ListOfValues> getLovList() {
		return lovList;
	}
	public void setLovList(List<ListOfValues> lovList) {
		this.lovList = lovList;
	}
	public void setAgreementId(String agreementId) {
		this.agreementId = agreementId;
	}
	public String getAgreementId() {
		return agreementId;
	}
}
