package com.lexmark.service.impl.real.domain;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author pkozlov
 * mapping file: "do-parttype-mapping.xml"
 */
public class PartTypeDo extends ProductModelDo implements Serializable {
	private static final long serialVersionUID = -6469946143881025657L;

	private String partType;
	private ArrayList<SuppliesCatalogConsumablePartTypeDo> catalogPartType;

	public String getPartType() {
		return partType;
	}

	public void setPartType(String partType) {
		this.partType = partType;
	}

	public ArrayList<SuppliesCatalogConsumablePartTypeDo> getCatalogPartType() {
		return catalogPartType;
	}

	public void setCatalogPartType(
			ArrayList<SuppliesCatalogConsumablePartTypeDo> catalogPartType) {
		this.catalogPartType = catalogPartType;
	}

}
