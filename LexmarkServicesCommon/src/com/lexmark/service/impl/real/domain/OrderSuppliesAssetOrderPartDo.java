package com.lexmark.service.impl.real.domain;

import java.io.Serializable;
import java.util.Date;

import com.amind.common.domain.BaseEntity;

/**
 * The mapping file: do-ordersuppliesassetorderpart-mapping.xml
 * 
 * @author vpetruchok
 * @version 1.0, 2013-02-27
 */
public class OrderSuppliesAssetOrderPartDo extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    private Date shippedDate;
    private String productName;
    private Date orderDate;
    private String orderNumber;
    private String description;
    private String partNo;
    private String partType;
    private String orderQuantity;
    private String status;
    private String lineType;
    private String srNumber;
   
    private String vendorPartNumber;
    
    public String getVendorPartNumber() {
		return vendorPartNumber;
	}

	public void setVendorPartNumber(String vendorPartNumber) {
		this.vendorPartNumber = vendorPartNumber;
	}

	public String getSrNumber() {
		return srNumber;
	}

	public void setSrNumber(String srNumber) {
		this.srNumber = srNumber;
	}

	// required
    public OrderSuppliesAssetOrderPartDo() {
    }

    public OrderSuppliesAssetOrderPartDo(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Date getShippedDate() {
        return shippedDate;
    }

    public void setShippedDate(Date shippedDate) {
        this.shippedDate = shippedDate;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String partNumber) {
        this.description = partNumber;
    }

    public String getPartNo() {
        return partNo;
    }

    public void setPartNo(String partNo) {
        this.partNo = partNo;
    }
    
    public String getPartType() {
        return partType;
    }

    public void setPartType(String partType) {
        this.partType = partType;
    }

    public String getOrderQuantity() {
        return orderQuantity;
    }

    public void setOrderQuantity(String orderQuantity) {
        this.orderQuantity = orderQuantity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLineType() {
        return lineType;
    }

    public void setLineType(String lineType) {
        this.lineType = lineType;
    }

}
