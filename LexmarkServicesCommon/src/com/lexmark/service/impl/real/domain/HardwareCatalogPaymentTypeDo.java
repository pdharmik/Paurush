package com.lexmark.service.impl.real.domain;

import java.io.Serializable;

import com.amind.common.domain.BaseEntity;

/**
 * @author Vano
 * mapping-file: "do-hardwarecatalogpaymenttype-mapping.xml"
 */

public class HardwareCatalogPaymentTypeDo extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1237179258227057603L;
	
	private String quantity;
	private String partNumber;
	private String partDescription;
	private String productModel;
	private String productType;
	private String colorMono;
	private String componentPart;
	private String partType;
	private String billingModel;
	private String catalogId;
	private String materialId;
	private String parentLineItemNumber;
	private String partTypeMVFB2b;
	

	
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public String getPartNumber() {
		return partNumber;
	}
	public void setPartNumber(String partNumber) {
		this.partNumber = partNumber;
	}
	public String getPartDescription() {
		return partDescription;
	}
	public void setPartDescription(String partDescription) {
		this.partDescription = partDescription;
	}
	public String getProductModel() {
		return productModel;
	}

	public void setProductModel(String productModel) {
		this.productModel = productModel;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}
	public String getColorMono() {
		return colorMono;
	}
	public void setColorMono(String colorMono) {
		this.colorMono = colorMono;
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
	public String getBillingModel() {
		return billingModel;
	}
	public void setBillingModel(String billingModel) {
		this.billingModel = billingModel;
	}
	public String getCatalogId() {
		return catalogId;
	}
	public void setCatalogId(String catalogId) {
		this.catalogId = catalogId;
	}
	public String getMaterialId() {
		return materialId;
	}
	public void setMaterialId(String materialId) {
		this.materialId = materialId;
	}
	public String getParentLineItemNumber() {
		return parentLineItemNumber;
	}
	public void setParentLineItemNumber(String parentLineItemNumber) {
		this.parentLineItemNumber = parentLineItemNumber;
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
