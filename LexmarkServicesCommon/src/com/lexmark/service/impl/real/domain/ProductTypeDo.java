package com.lexmark.service.impl.real.domain;

import java.io.Serializable;
import java.util.ArrayList;

import com.amind.common.domain.BaseEntity;

/**
 * @author pkozlov
 * mapping file: "do-producttype-mapping.xml"
 */
public class ProductTypeDo extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 4562739423679147783L;

	private String agreementId;
	private String productType;
	private ArrayList<SuppliesCatalogProductTypeDo> catalogProductType;
	
	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getAgreementId() {
		return agreementId;
	}

	public void setAgreementId(String agreementId) {
		this.agreementId = agreementId;
	}

	public ArrayList<SuppliesCatalogProductTypeDo> getCatalogProductType() {
		return catalogProductType;
	}

	public void setCatalogProductType(
			ArrayList<SuppliesCatalogProductTypeDo> catalogProductType) {
		this.catalogProductType = catalogProductType;
	}
}
