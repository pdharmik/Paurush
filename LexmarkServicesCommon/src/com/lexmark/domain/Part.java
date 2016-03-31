package com.lexmark.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class Part implements Serializable {
    private static final long serialVersionUID = 4875370717811092763L;
    private String partId;
    private String partNumber;
    private String partName;
    private boolean returnRequiredFlag;
    private String replacementPartNumber;
    private String category;
    private String description;
    private String orderQuantity;
   
    //added 11.20.2013
    private String vendorPartNumber; 
    
	// added 05.05.2012
    private String partType;

    private String supplyType;
    private Boolean implicitFlag;
    private String catalogId;
    private String printerPartNumber;
    private String assetUsageType;
    private String assetSerialNumber;
    private String consumableType;
    private String yield;
    
    private String productId;
	private String supplyId;
	private String agreementId;
	private String model;
	private String status;
	private String cancelledQuantity;
	
	private Date shippedDate;
	private Date lastOrderDate;
	private String orderNumber;
	private BigDecimal price;
	private String deviceType;
	
	//Added for MPS 2.1 
	private String pageCountType;
	private List<String> soldToNumbers;

	private String unitPrice;
	private String totalPrice;
	private String totalLinePrice;
	
	private String taxAmount;
	private List<String> paymentTypes;
	private String currency;
	
	private String contractNo;
	private String contractLineItemId;
	private String salesOrg;
	
	/*Added for Hardware Details*/
	private String hardwareType;
	private List<BundlePart> bundlePartList;
	/*End Add*/
	private String bundleParentLineId;
	private boolean prefferedPartFlag;
    private String catalogType;
    
	//Updatd for #7423
	private String srNumber;
	
	private String materialLine;
	
	private String providerOrderNumber;
	
	private String parentLineItemNumber;
	
	private String machineTypeModel;
	private String relationType;
	
	//added for MPS2.1- Used,NotUsed,DOA
	
	private Integer usedQuantity;
	private Integer notUsedQuantity;
	private Integer doaQuantity;
	private Integer massuploadSequenceNumber;
	private String maxQuantity;

	//recommended
	private int recommendedQuantity;
	private String recommendedProductName;
    private String recommendedPartNumber;
    private String recommendedPartDisposition;
    private String recommendedReturnIfDefective;
    private String recommendedProductId;
    private String recommendedActivitySRId;
    private String recommendedRelationType;
    private String recommendedActivityId;
    private String recommendedPrimaryOrderId;
    private String recommendedPrimaryOrderItemId;
    private String recommendedRequiredFlag;
    private String installProduct;
    private String installSerialNumber;
    private String deinstallSerialNumber;
    private String deinstallProduct;
    private String deinstallModel;
    private String deinstallIPAddr;
    private String deinstallAssetTag;
    private String deinstallBrand;
    private String deinstallComments;
    private String deinstallHostName;
    private boolean typePrinter;
    private String partTypeMVFB2b;
    
    public String getInstallProduct() {
		return installProduct;
	}

	public void setInstallProduct(String installProduct) {
		this.installProduct = installProduct;
	}
    public String getInstallSerialNumber() {
		return installSerialNumber;
	}

	public void setInstallSerialNumber(String installSerialNumber) {
		this.installSerialNumber = installSerialNumber;
	}

	public String getDeinstallSerialNumber() {
		return deinstallSerialNumber;
	}

	public void setDeinstallSerialNumber(String deinstallSerialNumber) {
		this.deinstallSerialNumber = deinstallSerialNumber;
	}

	public String getDeinstallProduct() {
		return deinstallProduct;
	}

	public void setDeinstallProduct(String deinstallProduct) {
		this.deinstallProduct = deinstallProduct;
	}

	public String getDeinstallModel() {
		return deinstallModel;
	}

	public void setDeinstallModel(String deinstallModel) {
		this.deinstallModel = deinstallModel;
	}

	public String getDeinstallIPAddr() {
		return deinstallIPAddr;
	}

	public void setDeinstallIPAddr(String deinstallIPAddr) {
		this.deinstallIPAddr = deinstallIPAddr;
	}

	public String getDeinstallAssetTag() {
		return deinstallAssetTag;
	}

	public void setDeinstallAssetTag(String deinstallAssetTag) {
		this.deinstallAssetTag = deinstallAssetTag;
	}

	public String getDeinstallBrand() {
		return deinstallBrand;
	}

	public void setDeinstallBrand(String deinstallBrand) {
		this.deinstallBrand = deinstallBrand;
	}

	public String getDeinstallComments() {
		return deinstallComments;
	}

	public void setDeinstallComments(String deinstallComments) {
		this.deinstallComments = deinstallComments;
	}

	public String getDeinstallHostName() {
		return deinstallHostName;
	}

	public void setDeinstallHostName(String deinstallHostName) {
		this.deinstallHostName = deinstallHostName;
	}

	public String getRecommendedRequiredFlag() {
		return recommendedRequiredFlag;
	}
	public void setRecommendedRequiredFlag(String recommendedRequiredFlag) {
		this.recommendedRequiredFlag = recommendedRequiredFlag;
	}
	//ordered
    private String orderedCarrierCode;
	private String orderedExpediateShippingFlg;
    private String orderedPartDisposition;
    private String orderedShippedDate;
    private String orderedTrackingNumber;
    private String orderedPartNumber;
    private String orderedProduct;
    private String orderedQuantityRequested;
    private String orderedSerialNumber;
    private String orderedStatus;
    private String orderedParentPropductId;
    private String orderedProductId;
    
    //OPS
    private String authorizationCode;
    private String authorizationReason;
    
    public String getRecommendedReturnedFlag() {
		return recommendedRequiredFlag;
	}
	public void setRecommendedReturnedFlag(String recommendedRequiredFlag) {
		this.recommendedRequiredFlag = recommendedRequiredFlag;
	}
    public int getRecommendedQuantity() {
		return recommendedQuantity;
	}
	public void setRecommendedQuantity(int recommendedQuantity) {
		this.recommendedQuantity = recommendedQuantity;
	}
	public String getRecommendedProductName() {
		return recommendedProductName;
	}
	public void setRecommendedProductName(String recommendedproductName) {
		this.recommendedProductName = recommendedproductName;
	}
	public String getRecommendedPartNumber() {
		return recommendedPartNumber;
	}
	public void setRecommendedPartNumber(String recommendedpartNumber) {
		this.recommendedPartNumber = recommendedpartNumber;
	}
	public String getRecommendedPartDisposition() {
		return recommendedPartDisposition;
	}
	public void setRecommendedPartDisposition(String recommendedpartDisposition) {
		this.recommendedPartDisposition = recommendedpartDisposition;
	}
	public String getRecommendedReturnIfDefective() {
		return recommendedReturnIfDefective;
	}
	public void setRecommendedReturnIfDefective(String recommendedreturnIfDefective) {
		this.recommendedReturnIfDefective = recommendedreturnIfDefective;
	}
	public String getRecommendedProductId() {
		return recommendedProductId;
	}
	public void setRecommendedProductId(String recommendedproductId) {
		this.recommendedProductId = recommendedproductId;
	}
	public String getRecommendedActivitySRId() {
		return recommendedActivitySRId;
	}
	public void setRecommendedActivitySRId(String recommendedactivitySRId) {
		this.recommendedActivitySRId = recommendedactivitySRId;
	}
	public String getRecommendedRelationType() {
		return recommendedRelationType;
	}
	public void setRecommendedRelationType(String recommendedrelationType) {
		this.recommendedRelationType = recommendedrelationType;
	}
	public String getRecommendedActivityId() {
		return recommendedActivityId;
	}
	public void setRecommendedActivityId(String recommendedactivityId) {
		this.recommendedActivityId = recommendedactivityId;
	}
	public String getRecommendedPrimaryOrderId() {
		return recommendedPrimaryOrderId;
	}
	public void setRecommendedPrimaryOrderId(String recommendedprimaryOrderId) {
		this.recommendedPrimaryOrderId = recommendedprimaryOrderId;
	}
	public String getRecommendedPrimaryOrderItemId() {
		return recommendedPrimaryOrderItemId;
	}
	public void setRecommendedPrimaryOrderItemId(
			String recommendedprimaryOrderItemId) {
		this.recommendedPrimaryOrderItemId = recommendedprimaryOrderItemId;
	}
	
	public String getMaxQuantity() {
		return maxQuantity;
	}
	public void setMaxQuantity(String maxQuantity) {
		this.maxQuantity = maxQuantity;
	}
	public Integer getMassuploadSequenceNumber() {
		return massuploadSequenceNumber;
	}
	public void setMassuploadSequenceNumber(Integer massuploadSequenceNumber) {
		this.massuploadSequenceNumber = massuploadSequenceNumber;
	}
	public String getMassuploadRecommendedType() {
		return massuploadRecommendedType;
	}
	public void setMassuploadRecommendedType(String massuploadRecommendedType) {
		this.massuploadRecommendedType = massuploadRecommendedType;
	}
	private String massuploadRecommendedType;
	
   
	 public String getRelationType() {
		return relationType;
	}
	public void setRelationType(String relationType) {
		this.relationType = relationType;
	}
	public String getVendorPartNumber() {
			return vendorPartNumber;
	}
		public void setVendorPartNumber(String vendorPartNumber) {
			this.vendorPartNumber = vendorPartNumber;
	}
	public String getSrNumber() {
		return srNumber;
	}
	public void setSrNumber(String srNumber) {
		this.srNumber = srNumber;
	}
	//End #7423
	public String getHardwareType() {
		return hardwareType;
	}

	public void setHardwareType(String hardwareType) {
		this.hardwareType = hardwareType;
	}

	public List<BundlePart> getBundlePartList() {
		return bundlePartList;
	}

	public void setBundlePartList(List<BundlePart> bundlePartList) {
		this.bundlePartList = bundlePartList;
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

	public String getSalesOrg() {
		return salesOrg;
	}

	public void setSalesOrg(String salesOrg) {
		this.salesOrg = salesOrg;
	}

	public String getPartNumber() {
        return partNumber;
    }

    public void setPartNumber(String partNumber) {
        this.partNumber = partNumber;
    }

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }

    public boolean isReturnRequiredFlag() {
        return returnRequiredFlag;
    }

    public void setReturnRequiredFlag(boolean returnRequiredFlag) {
        this.returnRequiredFlag = returnRequiredFlag;
    }

    public String getReplacementPartNumber() {
        return replacementPartNumber;
    }

    public void setReplacementPartNumber(String replacementPartNumber) {
        this.replacementPartNumber = replacementPartNumber;
    }

    public void setPartId(String partId) {
        this.partId = partId;
    }

    public String getPartId() {
        return partId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setOrderQuantity(String orderQuantity) {
        this.orderQuantity = orderQuantity;
    }

    public String getOrderQuantity() {
        return orderQuantity;
    }

    public String getPartType() {
        return partType;
    }

    public void setPartType(String partType) {
        this.partType = partType;
    }

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

    public String getPrinterPartNumber() {
        return printerPartNumber;
    }

    public void setPrinterPartNumber(String printerPartNumber) {
        this.printerPartNumber = printerPartNumber;
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

    public String getConsumableType() {
        return consumableType;
    }

    public void setConsumableType(String consumableType) {
        this.consumableType = consumableType;
    }

    public String getYield() {
        return yield;
    }

    public void setYield(String yield) {
        this.yield = yield;
    }

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getSupplyId() {
		return supplyId;
	}

	public void setSupplyId(String supplyId) {
		this.supplyId = supplyId;
	}

	public String getAgreementId() {
		return agreementId;
	}

	public void setAgreementId(String agreementId) {
		this.agreementId = agreementId;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	public void setCancelledQuantity(String cancelledQuantity) {
		this.cancelledQuantity = cancelledQuantity;
	}

	public String getCancelledQuantity() {
		return cancelledQuantity;
	}
	

    public Date getShippedDate() {
        return shippedDate;
    }
    

    public void setShippedDate(Date shippedDate) {
        this.shippedDate = shippedDate;
    }
    

    public Date getLastOrderDate() {
        return lastOrderDate;
    }
    

    public void setLastOrderDate(Date lastOrderDate) {
        this.lastOrderDate = lastOrderDate;
    }
    

    public String getOrderNumber() {
        return orderNumber;
    }
    

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(String unitPrice) {
		this.unitPrice = unitPrice;
	}

	public String getTaxAmount() {
		return taxAmount;
	}

	public void setTaxAmount(String taxAmount) {
		this.taxAmount = taxAmount;
	}

	public List<String> getPaymentTypes() {
		return paymentTypes;
	}

	public void setPaymentTypes(List<String> paymentTypes) {
		this.paymentTypes = paymentTypes;
	}

	public String getPageCountType() {
		return pageCountType;
	}

	public void setPageCountType(String pageCountType) {
		this.pageCountType = pageCountType;
	}

	public List<String> getSoldToNumbers() {
		return soldToNumbers;
	}

	public void setSoldToNumbers(List<String> soldToNumbers) {
		this.soldToNumbers = soldToNumbers;
	}

	public String getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}
	public String getTotalLinePrice() {
		return totalLinePrice;
	}

	public void setTotalLinePrice(String totalLinePrice) {
		this.totalLinePrice = totalLinePrice;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public boolean isPrefferedPartFlag() {
		return prefferedPartFlag;
	}

	public void setPrefferedPartFlag(boolean prefferedPartFlag) {
		this.prefferedPartFlag = prefferedPartFlag;
	}

	public String getCatalogType() {
		return catalogType;
	}

	public void setCatalogType(String catalogType) {
		this.catalogType = catalogType;
	}
	public String getMaterialLine() {
		return materialLine;
	}
	public void setMaterialLine(String materialLine) {
		this.materialLine = materialLine;
	}
	public String getBundleParentLineId() {
		return bundleParentLineId;
	}
	public void setBundleParentLineId(String bundleParentLineId) {
		this.bundleParentLineId = bundleParentLineId;
	}
	public String getProviderOrderNumber() {
		return providerOrderNumber;
	}
	public void setProviderOrderNumber(String providerOrderNumber) {
		this.providerOrderNumber = providerOrderNumber;
	}
	public String getParentLineItemNumber() {
		return parentLineItemNumber;
	}
	public void setParentLineItemNumber(String parentLineItemNumber) {
		this.parentLineItemNumber = parentLineItemNumber;
	}
	public String getMachineTypeModel() {
		return machineTypeModel;
	}
	public void setMachineTypeModel(String machineTypeModel) {
		this.machineTypeModel = machineTypeModel;
	}
	public void setUsedQuantity(Integer usedQuantity) {
		this.usedQuantity = usedQuantity;
	}
	public Integer getUsedQuantity() {
		return usedQuantity;
	}
	public void setNotUsedQuantity(Integer notUsedQuantity) {
		this.notUsedQuantity = notUsedQuantity;
	}
	public Integer getNotUsedQuantity() {
		return notUsedQuantity;
	}
	public void setDoaQuantity(Integer doaQuantity) {
		this.doaQuantity = doaQuantity;
	}
	public Integer getDoaQuantity() {
		return doaQuantity;
	}
    public String getOrderedCarrierCode() {
		return orderedCarrierCode;
	}
	public void setOrderedCarrierCode(String orderedCarrierCode) {
		this.orderedCarrierCode = orderedCarrierCode;
	}
	public String getOrderedExpediateShippingFlg() {
		return orderedExpediateShippingFlg;
	}
	public void setOrderedExpediateShippingFlg(String orderedExpediateShippingFlg) {
		this.orderedExpediateShippingFlg = orderedExpediateShippingFlg;
	}
	public String getOrderedPartDisposition() {
		return orderedPartDisposition;
	}
	public void setOrderedPartDisposition(String orderedPartDisposition) {
		this.orderedPartDisposition = orderedPartDisposition;
	}
	public String getOrderedShippedDate() {
		return orderedShippedDate;
	}
	public void setOrderedShippedDate(String orderedShippedDate) {
		this.orderedShippedDate = orderedShippedDate;
	}
	public String getOrderedTrackingNumber() {
		return orderedTrackingNumber;
	}
	public void setOrderedTrackingNumber(String orderedTrackingNumber) {
		this.orderedTrackingNumber = orderedTrackingNumber;
	}
	public String getOrderedPartNumber() {
		return orderedPartNumber;
	}
	public void setOrderedPartNumber(String orderedPartNumber) {
		this.orderedPartNumber = orderedPartNumber;
	}
	public String getOrderedProduct() {
		return orderedProduct;
	}
	public void setOrderedProduct(String orderedProduct) {
		this.orderedProduct = orderedProduct;
	}
	public String getOrderedQuantityRequested() {
		return orderedQuantityRequested;
	}
	public void setOrderedQuantityRequested(String orderedQuantityRequested) {
		this.orderedQuantityRequested = orderedQuantityRequested;
	}
	public String getOrderedSerialNumber() {
		return orderedSerialNumber;
	}
	public void setOrderedSerialNumber(String orderedSerialNumber) {
		this.orderedSerialNumber = orderedSerialNumber;
	}
	public String getOrderedStatus() {
		return orderedStatus;
	}
	public void setOrderedStatus(String orderedStatus) {
		this.orderedStatus = orderedStatus;
	}
	public String getOrderedParentPropductId() {
		return orderedParentPropductId;
	}
	public void setOrderedParentPropductId(String orderedParentPropductId) {
		this.orderedParentPropductId = orderedParentPropductId;
	}
	public String getOrderedProductId() {
		return orderedProductId;
	}
	public void setOrderedProductId(String orderedProductId) {
		this.orderedProductId = orderedProductId;
	}
	public String getAuthorizationCode() {
		return authorizationCode;
	}
	public void setAuthorizationCode(String authorizationCode) {
		this.authorizationCode = authorizationCode;
	}
	public String getAuthorizationReason() {
		return authorizationReason;
	}
	public void setAuthorizationReason(String authorizationReason) {
		this.authorizationReason = authorizationReason;
	}

	public boolean isTypePrinter() {
		return typePrinter;
	}

	public void setTypePrinter(boolean typePrinter) {
		this.typePrinter = typePrinter;
	}

	/**
	 * @return the partTypeMVFB2b
	 */
	public String getPartTypeMVFB2b() {
		return partTypeMVFB2b;
	}

	/**
	 * @param partTypeMVFB2b the partTypeMVFB2b to set
	 */
	public void setPartTypeMVFB2b(String partTypeMVFB2b) {
		this.partTypeMVFB2b = partTypeMVFB2b;
	}

}
