package com.lexmark.service.impl.real.domain;

/**
 * @author Vano
 * Mapping file: do-cataloglistdo-mapping.xml
 */

import java.io.Serializable;
import java.util.ArrayList;

import com.amind.common.domain.BaseEntity;

public class CatalogListDo extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1346682442420256007L;
	
	public static final String BO = "LXK MPS Supplies Catalog Distinct";
    public static final String BC = "LXK MPS Supplies Catalog Distinct";
    public static final String GLOBAL_BO = "LXK MPS Supplies Catalog";
    public static final String GLOBAL_BC = "LXK MPS Supplies Catalog";

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
	private String mpsQuantity;
	
	public String getMpsQuantity() {
		return mpsQuantity;
	}

	public void setMpsQuantity(String mpsQuantity) {
		this.mpsQuantity = mpsQuantity;
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

}
