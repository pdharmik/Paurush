package com.lexmark.service.impl.real.domain;

import java.io.Serializable;
import java.util.ArrayList;

import com.amind.common.domain.BaseEntity;

/**
 * @author Vano
// * mapping-file: "do-hardwaresapcatalog-mapping.xml"
 */

public class HardwareSapCatalogDo extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 800671678571764829L;
	
	private String id;
	private String agreementId;
	private String billingModel;
	private String sapContract;
	private String sapLine;
	private String sapSalesOrg;
	private String sapSoldTo;
	private String partDescription;
	private String partNumber;
	private String implicitFlag;
	private String portalFlag;
	private String bundleProductId;
	private String bundleId;
	private String componentPart;
	private String partType;
	private String productType;
	private String contractLineItemId;
	private String type;

	private String productModel;
	private String quantity;
	private ArrayList<HardwareCatalogPaymentTypeDo> pickSapCatalogPaymentType;
	private String bundleMaterialID;
	private String materialLine;
	private String modelColor;
	private String sapStatus;
	private String assetId;
	private String mpsDescription;
	private String partTypeB2b;
	private String configId;
	private String b2bMarketingShortDesc;
	private String b2bMarketingName;
	private String b2bModel;
	private String b2bMfgBrand;
	
	public String getB2bMarketingShortDesc() {
		return b2bMarketingShortDesc;
	}

	public void setB2bMarketingShortDesc(String b2bMarketingShortDesc) {
		this.b2bMarketingShortDesc = b2bMarketingShortDesc;
	}

	public String getB2bMarketingName() {
		return b2bMarketingName;
	}

	public void setB2bMarketingName(String b2bMarketingName) {
		this.b2bMarketingName = b2bMarketingName;
	}

	public String getB2bModel() {
		return b2bModel;
	}

	public void setB2bModel(String b2bModel) {
		this.b2bModel = b2bModel;
	}

	public String getB2bMfgBrand() {
		return b2bMfgBrand;
	}

	public void setB2bMfgBrand(String b2bMfgBrand) {
		this.b2bMfgBrand = b2bMfgBrand;
	}

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


	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAgreementId() {
		return agreementId;
	}

	public void setAgreementId(String agreementId) {
		this.agreementId = agreementId;
	}

	public String getBillingModel() {
		return billingModel;
	}

	public void setBillingModel(String billingModel) {
		this.billingModel = billingModel;
	}

	public String getSapContract() {
		return sapContract;
	}

	public void setSapContract(String sapContract) {
		this.sapContract = sapContract;
	}

	public String getSapLine() {
		return sapLine;
	}

	public void setSapLine(String sapLine) {
		this.sapLine = sapLine;
	}

	public String getSapSalesOrg() {
		return sapSalesOrg;
	}

	public void setSapSalesOrg(String sapSalesOrg) {
		this.sapSalesOrg = sapSalesOrg;
	}

	public String getSapSoldTo() {
		return sapSoldTo;
	}

	public void setSapSoldTo(String sapSoldTo) {
		this.sapSoldTo = sapSoldTo;
	}

	public String getPartDescription() {
		return partDescription;
	}

	public void setPartDescription(String partDescription) {
		this.partDescription = partDescription;
	}

	public String getPartNumber() {
		return partNumber;
	}

	public void setPartNumber(String partNumber) {
		this.partNumber = partNumber;
	}

	public String getImplicitFlag() {
		return implicitFlag;
	}

	public void setImplicitFlag(String implicitFlag) {
		this.implicitFlag = implicitFlag;
	}

	public String getPortalFlag() {
		return portalFlag;
	}

	public void setPortalFlag(String portalFlag) {
		this.portalFlag = portalFlag;
	}

	public String getBundleProductId() {
		return bundleProductId;
	}

	public void setBundleProductId(String bundleProductId) {
		this.bundleProductId = bundleProductId;
	}

	public ArrayList<HardwareCatalogPaymentTypeDo> getPickSapCatalogPaymentType() {
		return pickSapCatalogPaymentType;
	}

	public void setPickSapCatalogPaymentType(
			ArrayList<HardwareCatalogPaymentTypeDo> pickSapCatalogPaymentType) {
		this.pickSapCatalogPaymentType = pickSapCatalogPaymentType;
	}

	public String getBundleId() {
		return bundleId;
	}

	public void setBundleId(String bundleId) {
		this.bundleId = bundleId;
	}

	public String getComponentPart() {
		return componentPart;
	}

	public void setComponentPart(String componentPart) {
		this.componentPart = componentPart;
	}

	public String getPartType() {
		return partType;
	}

	public void setPartType(String partType) {
		this.partType = partType;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getProductModel() {
		return productModel;
	}

	public void setProductModel(String productModel) {
		this.productModel = productModel;
	}

	public String getContractLineItemId() {
		return contractLineItemId;
	}

	public void setContractLineItemId(String contractLineItemId) {
		this.contractLineItemId = contractLineItemId;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getBundleMaterialID() {
		return bundleMaterialID;
	}

	public void setBundleMaterialID(String bundleMaterialID) {
		this.bundleMaterialID = bundleMaterialID;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMaterialLine() {
		return materialLine;
	}

	public void setMaterialLine(String materialLine) {
		this.materialLine = materialLine;
	}

	public String getModelColor() {
		return modelColor;
	}

	public void setModelColor(String modelColor) {
		this.modelColor = modelColor;
	}

	public String getSapStatus() {
		return sapStatus;
	}

	public void setSapStatus(String sapStatus) {
		this.sapStatus = sapStatus;
	}


	public String getConfigId() {
		return configId;
	}

	public void setConfigId(String configId) {
		this.configId = configId;
	}

	/**
	 * @return the partTypeB2b
	 */
	public String getPartTypeB2b() {
		return partTypeB2b;
	}

	/**
	 * @param partTypeB2b the partTypeB2b to set
	 */
	public void setPartTypeB2b(String partTypeB2b) {
		this.partTypeB2b = partTypeB2b;
	}
	
}
