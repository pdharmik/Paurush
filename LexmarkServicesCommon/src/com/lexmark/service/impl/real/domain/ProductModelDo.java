package com.lexmark.service.impl.real.domain;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author pkozlov
 * mapping file: "do-productmodel-mapping.xml"
 */
public class ProductModelDo extends ProductTypeDo implements Serializable {
	private static final long serialVersionUID = 6296550748954101975L;

	private String productModel;
	private ArrayList<SuppliesCatalogProductModelDo> catalogProductModel;


	public String getProductModel() {
		return productModel;
	}

	public void setProductModel(String productModel) {
		this.productModel = productModel;
	}

	public ArrayList<SuppliesCatalogProductModelDo> getCatalogProductModel() {
		return catalogProductModel;
	}

	public void setCatalogProductModel(
			ArrayList<SuppliesCatalogProductModelDo> catalogProductModel) {
		this.catalogProductModel = catalogProductModel;
	}
	
}
