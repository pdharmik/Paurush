package com.lexmark.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class OrderPart implements Serializable {
	private static final long serialVersionUID = -6323900787987985163L;

	private String partNumber;
	private String description;
	private String yield;
	private String model;

	private String partQuantity;
	private String partDesc;
	private String localDesc;
	private String productTli;
	private String productLine;
	private String costCenter;
	private String poNumber;

	private String defaultSpecialInstructions;
	
	// used by PartnerDomainMockDataGenerator
	private String partType;
	private String category;
	private String orderQuantity;
	
	//private String partNumber;
	//private String description;
	private String supplyType;
	private Boolean implicitFlag;
	private String catalogId;
	private String printerPartNo;
	private String consumableType;
	//private String yield;
	private String assetUsageType;
	private String assetSerialNumber;
	private String supplyId;
	private String productId;
	private BigDecimal price;
	
	private String hardwareType;
	private String currency;
	private String contractNo;	
	private String contractLineItemId;
	private String unitPrice;
	private String total;
	private String tax;
	private String salesOrg;
	private String soldToNumber;
	private String billingModel;
	private String partImg;
	private String deviceType;
	private List<BundlePart> bundlePartList;
	private String providerContractNo;
	/*End*/
	private String providerOrderNumber;
	private String productModel;
	private String suppliesModel;
	private String printerModel;
	private String printerName;
	private String printerDescription;
	private String mpsQuantity;
	private List<SuppliesSplitterCatalog> suppliesCatalogList;
	private String unspscCode;
	
	public String getUnspscCode() {
		return unspscCode;
	}

	public void setUnspscCode(String unspscCode) {
		this.unspscCode = unspscCode;
	}

	public List<SuppliesSplitterCatalog> getSuppliesCatalogList() {
		return suppliesCatalogList;
	}

	public void setSuppliesCatalogList(
			List<SuppliesSplitterCatalog> suppliesCatalogList) {
		this.suppliesCatalogList = suppliesCatalogList;
	}

	public String getMpsQuantity() {
		return mpsQuantity;
	}

	public void setMpsQuantity(String mpsQuantity) {
		this.mpsQuantity = mpsQuantity;
	}

	public String getProductModel() {
		return productModel;
	}

	public void setProductModel(String productModel) {
		this.productModel = productModel;
	}

	public String getSuppliesModel() {
		return suppliesModel;
	}

	public void setSuppliesModel(String suppliesModel) {
		this.suppliesModel = suppliesModel;
	}

	
	

	public String getPartNumber() {
		return partNumber;
	}

	public List<BundlePart> getBundlePartList() {
		return bundlePartList;
	}

	public void setBundlePartList(List<BundlePart> bundlePartList) {
		this.bundlePartList = bundlePartList;
	}

	public void setPartNumber(String partNumber) {
		this.partNumber = partNumber;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	
	public boolean isImplicitFlag() {
		return implicitFlag;
	}

	public String getPartQuantity() {
		return partQuantity;
	}
	public void setPartQuantity(String partQuantity) {
		this.partQuantity = partQuantity;
	}
	public String getPartDesc() {
		return partDesc;
	}
	public void setPartDesc(String partDesc) {
		this.partDesc = partDesc;
	}
	

	public String getYield() {
		return yield;
	}

	public void setYield(String yield) {
		this.yield = yield;
	}
	
	

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getDefaultSpecialInstructions() {
		return defaultSpecialInstructions;
	}

	public void setDefaultSpecialInstructions(String defaultSpecialInstructions) {
		this.defaultSpecialInstructions = defaultSpecialInstructions;
	}

	
	public String getPartType() {
		return partType;
	}

	public void setPartType(String partType) {
		this.partType = partType;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getOrderQuantity() {
		return orderQuantity;
	}

	public void setOrderQuantity(String orderQuantity) {
		this.orderQuantity = orderQuantity;
	}
//Actual values Starts
	public String getSupplyType() {
		return supplyType;
	}

	public void setSupplyType(String supplyType) {
		this.supplyType = supplyType;
	}

	public Boolean getImplicitFlag() {
		return implicitFlag;
	}

	public void setImplicitFlag(Boolean implicitFlag) {
		this.implicitFlag = implicitFlag;
	}

	public String getCatalogId() {
		return catalogId;
	}

	public void setCatalogId(String catalogId) {
		this.catalogId = catalogId;
	}

	public String getPrinterPartNo() {
		return printerPartNo;
	}

	public void setPrinterPartNo(String printerPartNo) {
		this.printerPartNo = printerPartNo;
	}

	public String getConsumableType() {
		return consumableType;
	}

	public void setConsumableType(String consumableType) {
		this.consumableType = consumableType;
	}

	public String getAssetUsageType() {
		return assetUsageType;
	}

	public void setAssetUsageType(String assetUsageType) {
		this.assetUsageType = assetUsageType;
	}

	public String getAssetSerialNumber() {
		return assetSerialNumber;
	}

	public void setAssetSerialNumber(String assetSerialNumber) {
		this.assetSerialNumber = assetSerialNumber;
	}
	
	public String getSupplyId() {
		return supplyId;
	}

	public void setSupplyId(String supplyId) {
		this.supplyId = supplyId;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public String getHardwareType() {
		return hardwareType;
	}

	public void setHardwareType(String hardwareType) {
		this.hardwareType = hardwareType;
	}

	public String getContractNo() {
		return contractNo;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

	public String getContractLineItemId() {
		return contractLineItemId;
	}

	public void setContractLineItemId(String contractLineItemId) {
		this.contractLineItemId = contractLineItemId;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(String unitPrice) {
		this.unitPrice = unitPrice;
	}

	public String getTax() {
		return tax;
	}

	public void setTax(String tax) {
		this.tax = tax;
	}

	public String getSalesOrg() {
		return salesOrg;
	}

	public void setSalesOrg(String salesOrg) {
		this.salesOrg = salesOrg;
	}

	public String getSoldToNumber() {
		return soldToNumber;
	}

	public void setSoldToNumber(String soldToNumber) {
		this.soldToNumber = soldToNumber;
	}

	public String getBillingModel() {
		return billingModel;
	}

	public void setBillingModel(String billingModel) {
		this.billingModel = billingModel;
	}

	public String getPartImg() {
		return partImg;
	}

	public void setPartImg(String partImg) {
		this.partImg = partImg;
	}

	public String getProviderContractNo() {
		return providerContractNo;
	}

	public void setProviderContractNo(String providerContractNo) {
		this.providerContractNo = providerContractNo;
	}
	
	//Actual values Ends
	

	public String getProviderOrderNumber() {
		return providerOrderNumber;
	}

	public void setProviderOrderNumber(String providerOrderNumber) {
		this.providerOrderNumber = providerOrderNumber;
	}

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	public String getLocalDesc() {
		return localDesc;
	}

	public void setLocalDesc(String localDesc) {
		this.localDesc = localDesc;
	}

	public String getPrinterModel() {
		return printerModel;
	}

	public void setPrinterModel(String printerModel) {
		this.printerModel = printerModel;
	}

	public String getPrinterName() {
		return printerName;
	}

	public void setPrinterName(String printerName) {
		this.printerName = printerName;
	}

	public String getPrinterDescription() {
		return printerDescription;
	}

	public void setPrinterDescription(String printerDescription) {
		this.printerDescription = printerDescription;
	}
	
}
