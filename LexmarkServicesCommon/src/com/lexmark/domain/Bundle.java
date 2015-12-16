package com.lexmark.domain;

import java.math.BigDecimal;
import java.util.List;

public class Bundle {
	private String bundleId;
	private String bundleProductId;
	private BigDecimal price;	
	private List<Part> partList;
	private String description;
	private String contractNumber;
	private String contractLineItemId;
	private String salesOrg;
	private String bundleMaterialID;
	private String sapLineID;
	private String assetId;
	private String mpsDescription;
	// Added for B2B release
	private String bundleName;
	private String bundleQty;
	private String currency;
	private String billingModel;
	private String configId;
	private List<OrderPart> orderParts;//This will contain details of order part added to session B2B
	private String unspscCode;
	
	public String getAssetId() {
		return assetId;
	}

	public void setAssetId(String assetId) {
		this.assetId = assetId;
	}

	public String getMpsDescription() {
		return mpsDescription;
	}

	public void setMpsDescription(String mpsDescription) {
		this.mpsDescription = mpsDescription;
	}
	
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public List<Part> getPartList() {
		return partList;
	}
	public void setPartList(List<Part> partList) {
		this.partList = partList;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getBundleId() {
		return bundleId;
	}
	public void setBundleId(String bundleId) {
		this.bundleId = bundleId;
	}
	public String getContractNumber() {
		return contractNumber;
	}
	public void setContractNumber(String contractNumber) {
		this.contractNumber = contractNumber;
	}
	public String getContractLineItemId() {
		return contractLineItemId;
	}
	public void setContractLineItemId(String contractLineItemId) {
		this.contractLineItemId = contractLineItemId;
	}
	public String getSalesOrg() {
		return salesOrg;
	}
	public void setSalesOrg(String salesOrg) {
		this.salesOrg = salesOrg;
	}
	public String getBundleProductId() {
		return bundleProductId;
	}
	public void setBundleProductId(String bundleProductId) {
		this.bundleProductId = bundleProductId;
	}
	public String getBundleMaterialID() {
		return bundleMaterialID;
	}
	public void setBundleMaterialID(String bundleMaterialID) {
		this.bundleMaterialID = bundleMaterialID;
	}
	public String getSapLineID() {
		return sapLineID;
	}
	public void setSapLineID(String sapLineID) {
		this.sapLineID = sapLineID;
	}
	@Override
	public String toString() {
		return "Bundle [bundleId=" + bundleId + ", bundleProductId="
				+ bundleProductId + ", price=" + price + ", partList="
				+ partList + ", description=" + description
				+ ", contractNumber=" + contractNumber
				+ ", contractLineItemId=" + contractLineItemId + ", salesOrg="
				+ salesOrg + ", bundleMaterialID=" + bundleMaterialID
				+ ", sapLineID=" + sapLineID 
				+ ", bundleQty="+bundleQty+
				"]";
	}
	// Added for B2B release 
	
	public void setBundleName(String bundleName) {
		this.bundleName = bundleName;
	}

	public String getBundleName() {
		return bundleName;
	}
	public String getBundleQty() {
		return bundleQty;
	}

	public void setBundleQty(String bundleQty) {
		this.bundleQty = bundleQty;
	}

	public void setOrderParts(List<OrderPart> orderParts) {
		this.orderParts = orderParts;
	}

	public List<OrderPart> getOrderParts() {
		return orderParts;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getBillingModel() {
		return billingModel;
	}

	public void setBillingModel(String billingModel) {
		this.billingModel = billingModel;
	}

	public String getConfigId() {
		return configId;
	}

	public void setConfigId(String configId) {
		this.configId = configId;
	}

	public String getUnspscCode() {
		return unspscCode;
	}

	public void setUnspscCode(String unspscCode) {
		this.unspscCode = unspscCode;
	}
	
}
