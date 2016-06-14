package com.lexmark.service.impl.real.domain;

/**
 * @author Vano
 * Mapping file: do-cataloglistwithcontractnumberdo-mapping.xml
 */

import java.io.Serializable;
import java.util.ArrayList;

import com.amind.common.domain.BaseEntity;

public class CatalogListWithContractNumberDo extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1832240959761402374L;

	public static final String BO = "LXK MPS Supplies Catalog Splitter";
    public static final String BC = "LXK MPS Supplies Catalog Splitter";

	private String partNumber;
	private String description;
	private Boolean implicitFlag;
	private String printerPartNo;
	private String partType;
	private String yield;
	private String agreementId;
	private String productType ;
	private String productModel;
	private String catalogId;
	private String consumableType;
	private boolean portalFlag;
	private String supplyId;
	private String productId;
	private String price;
	private String materialLine;
	private ArrayList<SuppliesSplitterCatalogDo> suppliesSplitterCatalog;
	private String providerOrderNumber;
	private String suppliesModel;
	private String printerModel;
	private String printerName;
	private String printerDescription;
	private String productTypeNonB2B;
	private String mpsQuantity;
	private String b2bProductFamilyName;
	private String b2bMarketingShortDesc;
	private String b2bMarketingName;
	private String b2bModel;
	private String b2bMfgBrand;

	public String getB2bProductFamilyName() {
		return b2bProductFamilyName;
	}

	public void setB2bProductFamilyName(String b2bProductFamilyName) {
		this.b2bProductFamilyName = b2bProductFamilyName;
	}

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

	public String getMpsQuantity() {
		return mpsQuantity;
	}

	public void setMpsQuantity(String mpsQuantity) {
		this.mpsQuantity = mpsQuantity;
	}
	
	public String getProductTypenonB2B() {
		return productTypeNonB2B;
	}

	public void setProductTypenonB2B(String productTypenonB2B) {
		this.productTypeNonB2B = productTypenonB2B;
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

	public void setPartNumber(String partNumber) {
		this.partNumber = partNumber;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPrinterPartNo() {
		return printerPartNo;
	}

	public void setPrinterPartNo(String printerPartNo) {
		this.printerPartNo = printerPartNo;
	}

	public String getPartType() {
		return partType;
	}

	public void setPartType(String partType) {
		this.partType = partType;
	}

	public String getYield() {
		return yield;
	}

	public void setYield(String yield) {
		this.yield = yield;
	}

	public Boolean getImplicitFlag() {
		return implicitFlag;
	}

	public void setImplicitFlag(Boolean implicitFlag) {
		this.implicitFlag = implicitFlag;
	}

	public void setAgreementId(String agreementId) {
		this.agreementId = agreementId;
	}

	public String getAgreementId() {
		return agreementId;
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

	public void setCatalogId(String catalogId) {
		this.catalogId = catalogId;
	}

	public String getCatalogId() {
		return catalogId;
	}

	public void setConsumableType(String consumableType) {
		this.consumableType = consumableType;
	}

	public String getConsumableType() {
		return consumableType;
	}

	public void setPortalFlag(boolean portalFlag) {
		this.portalFlag = portalFlag;
	}

	public boolean isPortalFlag() {
		return portalFlag;
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

	public String getPrice() {
		if(price == null || price.isEmpty()) return "0";
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getMaterialLine() {
		return materialLine;
	}

	public void setMaterialLine(String materialLine) {
		this.materialLine = materialLine;
	}

	public ArrayList<SuppliesSplitterCatalogDo> getSuppliesSplitterCatalog() {
		return suppliesSplitterCatalog;
	}

	public void setSuppliesSplitterCatalog(
			ArrayList<SuppliesSplitterCatalogDo> suppliesSplitterCatalog) {
		this.suppliesSplitterCatalog = suppliesSplitterCatalog;
	}

	public String getProviderOrderNumber() {
		return providerOrderNumber;
	}

	public void setProviderOrderNumber(String providerOrderNumber) {
		this.providerOrderNumber = providerOrderNumber;
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
