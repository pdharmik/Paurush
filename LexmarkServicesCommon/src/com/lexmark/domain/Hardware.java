package com.lexmark.domain;

import java.io.Serializable;

public class Hardware implements Serializable{

	private static final long serialVersionUID = 1L;
	private String partId;
    private String partNumber;
    private String category;
    private String description;
    private String orderQuantity;
    private String partType;
    private String color_Mono;
    
    public String getPartId() {
		return partId;
	}
	public void setPartId(String partId) {
		this.partId = partId;
	}
	public String getPartNumber() {
		return partNumber;
	}
	public void setPartNumber(String partNumber) {
		this.partNumber = partNumber;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getOrderQuantity() {
		return orderQuantity;
	}
	public void setOrderQuantity(String orderQuantity) {
		this.orderQuantity = orderQuantity;
	}
	public String getPartType() {
		return partType;
	}
	public void setPartType(String partType) {
		this.partType = partType;
	}
	public String getColor_Mono() {
		return color_Mono;
	}
	public void setColor_Mono(String color_Mono) {
		this.color_Mono = color_Mono;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	private double price;
	

}
