package com.lexmark.service.impl.real.domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import com.amind.common.domain.BaseEntity;
import com.lexmark.util.LangUtil;

public class PartnerDetailPartBase extends BaseEntity {
	
	private String partNumber;
	private String partName;
	private int quantity;
	private Boolean returnRequiredFlag;
	private String productId;
	private String lineStatus;
	private String errorCode1;
	private String errorCode2;
	private String trackingNumber;
	private Boolean orderFlag;
	private String partOrderedDate;
	private String carrier;
	private String recommendedType;
	private String orderLineStatus;
	private String source;
	private String shippingMethod;
	private String productDescription;
	private String machineTypeModel;
	private String relationType;
	private Integer usedQuantity;
	private Integer notUsedQuantity;
	private Integer doaQuantity;
	private String partStatus;
	private String materialLine;
	
	public String getRelationType() {
		return relationType;
	}
	public void setRelationType(String relationType) {
		this.relationType = relationType;
	}
	public String getRecommendedType() {
		return recommendedType;
	}
	public void setRecommendedType(String recommendedType) {
		this.recommendedType = recommendedType;
	}
	public String getOrderLineStatus() {
		return orderLineStatus;
	}
	public void setOrderLineStatus(String orderLineStatus) {
		this.orderLineStatus = orderLineStatus;
	}
	private int recommendedQuantity = 1;
	private String orderLineItemId;
	
	public String getCarrier() {
		return carrier;
	}
	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}
	public Boolean isOrderFlag() {
		if(orderFlag == null) {
			orderFlag = false;
		}
		return orderFlag;
	}
	public void setOrderFlag(Boolean orderFlag) {
		this.orderFlag = orderFlag;
	}
	public String getPartNumber() {
		return partNumber;
	}
	public void setPartNumber(String partNumber) {
		this.partNumber = partNumber;
	}
	public String getPartName() {
		return partName;
	}
	public void setPartName(String partName) {
		this.partName = partName;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public Boolean isReturnRequiredFlag() {
		if(returnRequiredFlag == null) {
			returnRequiredFlag = false;
		}
		return returnRequiredFlag;
	}
	public void setReturnRequiredFlag(Boolean returnRequiredFlag) {
		this.returnRequiredFlag = returnRequiredFlag;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getLineStatus() {
		return lineStatus;
	}
	public void setLineStatus(String lineStatus) {
		this.lineStatus = lineStatus;
	}
	public String getErrorCode1() {
		return errorCode1;
	}
	public void setErrorCode1(String errorCode1) {
		this.errorCode1 = errorCode1;
	}
	public String getErrorCode2() {
		return errorCode2;
	}
	public void setErrorCode2(String errorCode2) {
		this.errorCode2 = errorCode2;
	}
	public String getTrackingNumber() {
		return trackingNumber;
	}
	public void setTrackingNumber(String trackingNumber) {
		this.trackingNumber = trackingNumber;
	}
	public Date getPartOrderedDate() {
		return convertStringToDateWithMarker(partOrderedDate);
	}
	public void setPartOrderedDate(String partOrderedDate) {
		this.partOrderedDate = partOrderedDate;
	}
	public void setRecommendedQuantity(int recommendedQuantity) {
		this.recommendedQuantity = recommendedQuantity;
	}
	public int getRecommendedQuantity() {
		return recommendedQuantity;
	}
	public void setOrderLineItemId(String orderLineItemId) {
		this.orderLineItemId = orderLineItemId;
	}
	public String getOrderLineItemId() {
		return orderLineItemId;
	}
	protected static Date convertStringToDateWithMarker (String dateString)  {
		Date date =  null;
 		if(dateString != null && !dateString.isEmpty()) {
//			TimeZone.setDefault(TimeZone.getTimeZone("GMT"));
			SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a");
			formatter.setTimeZone(TimeZone.getTimeZone("GMT"));
			try {
				date = formatter.parse(dateString);
			} catch (ParseException e) {
				try {
					date = LangUtil.convertStringToGMTDate(dateString);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
		return date;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getSource() {
		return source;
	}
	public String getShippingMethod() {
		return shippingMethod;
	}
	public void setShippingMethod(String shippingMethod) {
		this.shippingMethod = shippingMethod;
	}
	public String getProductDescription() {
		return productDescription;
	}
	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}
	public String getMachineTypeModel() {
		return machineTypeModel;
	}
	public void setMachineTypeModel(String machineTypeModel) {
		this.machineTypeModel = machineTypeModel;
	}
	public Integer getUsedQuantity() {
		return usedQuantity;
	}
	public void setUsedQuantity(Integer usedQuantity) {
		this.usedQuantity = usedQuantity;
	}
	public Integer getNotUsedQuantity() {
		return notUsedQuantity;
	}
	public void setNotUsedQuantity(Integer notUsedQuantity) {
		this.notUsedQuantity = notUsedQuantity;
	}
	public Integer getDoaQuantity() {
		return doaQuantity;
	}
	public void setDoaQuantity(Integer doaQuantity) {
		this.doaQuantity = doaQuantity;
	}
	public String getPartStatus() {
		return partStatus;
	}
	public void setPartStatus(String partStatus) {
		this.partStatus = partStatus;
	}
	public String getMaterialLine() {
		return materialLine;
	}
	public void setMaterialLine(String materialLine) {
		this.materialLine = materialLine;
	}
}
