package com.lexmark.service.impl.real.domain;

import java.io.Serializable;

import com.amind.common.domain.BaseEntity;


/**
 * @author i.prikhodko
 *
 * do-mapping file: "do-partneractivitylistpart-mapping.xml" 
 */
public class PartnerActivityListPartDo extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 2387350211874831274L;
	
	private String partNumber;
	private String productDescription;
	private String recommendedQuantity;
	private String machineTypeModel;
	
	//added for CR 12032
	private Integer massuploadSequenceNumber;
	private Integer massuploadUsedQuantity;
	private Integer massuploadUnusedQuantity;
	private Integer massuploadDOAQuantity;
	private String massuploadRecommendedType;
	

	
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
	
	
	public Integer getMassuploadSequenceNumber() {
		return massuploadSequenceNumber;
	}
	public void setMassuploadSequenceNumber(Integer massuploadSequenceNumber) {
		this.massuploadSequenceNumber = massuploadSequenceNumber;
	}
	public Integer getMassuploadUsedQuantity() {
		return massuploadUsedQuantity;
	}
	public void setMassuploadUsedQuantity(Integer massuploadUsedQuantity) {
		this.massuploadUsedQuantity = massuploadUsedQuantity;
	}
	public Integer getMassuploadUnusedQuantity() {
		return massuploadUnusedQuantity;
	}
	public void setMassuploadUnusedQuantity(Integer massuploadUnusedQuantity) {
		this.massuploadUnusedQuantity = massuploadUnusedQuantity;
	}
	public Integer getMassuploadDOAQuantity() {
		return massuploadDOAQuantity;
	}
	public void setMassuploadDOAQuantity(Integer massuploadDOAQuantity) {
		this.massuploadDOAQuantity = massuploadDOAQuantity;
	}
	public String getMassuploadRecommendedType() {
		return massuploadRecommendedType;
	}
	public void setMassuploadRecommendedType(String massuploadRecommendedType) {
		this.massuploadRecommendedType = massuploadRecommendedType;
	}
	
}
