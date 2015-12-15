package com.lexmark.service.impl.real.domain;

import java.io.Serializable;

import com.amind.common.domain.BaseEntity;


/**
 * 
 * @author David Tsamalashvili
 *
 * do-mapping: "do-partneractivitylistrecommpartdo-mapping.xml"
 */

public class PartnerActivityListRecommPartDo extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	private String partNumber;
	private String productDescription;
	private String recommendedQuantity;
	private String machineTypeModel;
	private String materialLineType;
	
	public String getPartNumber() {
		return partNumber;
	}
	public void setPartNumber(String partNumber) {
		this.partNumber = partNumber;
	}
	public String getProductDescription() {
		return productDescription;
	}
	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}
	public String getRecommendedQuantity() {
		return recommendedQuantity;
	}
	public void setRecommendedQuantity(String recommendedQuantity) {
		this.recommendedQuantity = recommendedQuantity;
	}
	public String getMachineTypeModel() {
		return machineTypeModel;
	}
	public void setMachineTypeModel(String machineTypeModel) {
		this.machineTypeModel = machineTypeModel;
	}
	public String getMaterialLineType() {
		return materialLineType;
	}
	public void setMaterialLineType(String materialLineType) {
		this.materialLineType = materialLineType;
	}
	
}
