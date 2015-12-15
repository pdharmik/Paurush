package com.lexmark.service.impl.real.domain;

import java.io.Serializable;

import com.amind.common.domain.BaseEntity;

/**
 * @author David Tsamalashvili
 * The mapping file: do-supplyrequestrecommendedpartsdo-mapping.xml
 */
public class SupplyRequestRecommendedPartsDo extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private int recommendedQuantity;
    private String productName;
    private String partNumber;
    private String partDisposition;
    private String returnIfDefective;
    private String productId;
    private String activitySRId;
    private String relationType;
    private String activityId;
    private String primaryOrderId;
    private String primaryOrderItemId;
    private String recommendedRequiredFlag;
    
    
    
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
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getPartNumber() {
		return partNumber;
	}
	public void setPartNumber(String partNumber) {
		this.partNumber = partNumber;
	}
	public String getPartDisposition() {
		return partDisposition;
	}
	public void setPartDisposition(String partDisposition) {
		this.partDisposition = partDisposition;
	}
	public String getReturnIfDefective() {
		return returnIfDefective;
	}
	public void setReturnIfDefective(String returnIfDefective) {
		this.returnIfDefective = returnIfDefective;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getActivitySRId() {
		return activitySRId;
	}
	public void setActivitySRId(String activitySRId) {
		this.activitySRId = activitySRId;
	}
	public String getRelationType() {
		return relationType;
	}
	public void setRelationType(String relationType) {
		this.relationType = relationType;
	}
	public String getActivityId() {
		return activityId;
	}
	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}
	public String getPrimaryOrderId() {
		return primaryOrderId;
	}
	public void setPrimaryOrderId(String primaryOrderId) {
		this.primaryOrderId = primaryOrderId;
	}
	public String getPrimaryOrderItemId() {
		return primaryOrderItemId;
	}
	public void setPrimaryOrderItemId(String primaryOrderItemId) {
		this.primaryOrderItemId = primaryOrderItemId;
	}
    
}
