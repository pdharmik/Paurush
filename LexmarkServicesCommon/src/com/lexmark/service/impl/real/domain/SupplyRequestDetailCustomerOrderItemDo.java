package com.lexmark.service.impl.real.domain;

import java.io.Serializable;
import java.util.ArrayList;

import com.amind.common.domain.BaseEntity;

/**
 * 
 * @author vshynkarenko
 *         mapping file: "do-supplyrequestdetailcustomerorderitem-mapping.xml"
 */
public class SupplyRequestDetailCustomerOrderItemDo extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private String catalogId;
    private String partDescriptionExplicit;
    private String productId;
    private String supplyId;
    private String productName;
    private String supplyPartId;
    private String partNumber;
    private String printerMaterial;
    private String quantity;
    private String partType;
    private String consumableType;
    private boolean implicitFlag;
    private String productModel;
    private String yield;
    private String agreementId;
    private String status;
    private String orderLineType;
    private String cancellationReason;
    private String unitPrice;
    private String currency;
    private String deviceType;
    private String catalogType;
    private String tax;
    private String totalLinePrice;
    private String totalPrice;
    private String materialLine;
    private String sapParentLineNumber;
    private String vendorPartNumber;
    private String descriptionLocalLang;
    private String installSerialNumber;
    private String deinstallSerialNumber;
    private String deinstallProduct;
    private String deinstallModel;
    private String deinstallIPAddr;
    private String deinstallAssetTag;
    private String deinstallBrand;
    private String deinstallComments;
    private String deinstallHostName;

    public String getInstallSerialNumber() {
		return installSerialNumber;
	}

	public void setInstallSerialNumber(String installSerialNumber) {
		this.installSerialNumber = installSerialNumber;
	}

	public String getDeinstallSerialNumber() {
		return deinstallSerialNumber;
	}

	public void setDeinstallSerialNumber(String deinstallSerialNumber) {
		this.deinstallSerialNumber = deinstallSerialNumber;
	}

	public String getDeinstallProduct() {
		return deinstallProduct;
	}

	public void setDeinstallProduct(String deinstallProduct) {
		this.deinstallProduct = deinstallProduct;
	}

	public String getDeinstallModel() {
		return deinstallModel;
	}

	public void setDeinstallModel(String deinstallModel) {
		this.deinstallModel = deinstallModel;
	}

	public String getDeinstallIPAddr() {
		return deinstallIPAddr;
	}

	public void setDeinstallIPAddr(String deinstallIPAddr) {
		this.deinstallIPAddr = deinstallIPAddr;
	}

	public String getDeinstallAssetTag() {
		return deinstallAssetTag;
	}

	public void setDeinstallAssetTag(String deinstallAssetTag) {
		this.deinstallAssetTag = deinstallAssetTag;
	}

	public String getDeinstallBrand() {
		return deinstallBrand;
	}

	public void setDeinstallBrand(String deinstallBrand) {
		this.deinstallBrand = deinstallBrand;
	}

	public String getDeinstallComments() {
		return deinstallComments;
	}

	public void setDeinstallComments(String deinstallComments) {
		this.deinstallComments = deinstallComments;
	}

	public String getDeinstallHostName() {
		return deinstallHostName;
	}

	public void setDeinstallHostName(String deinstallHostName) {
		this.deinstallHostName = deinstallHostName;
	}

	public String getVendorPartNumber() {
		return vendorPartNumber;
	}

	public void setVendorPartNumber(String vendorPartNumber) {
		this.vendorPartNumber = vendorPartNumber;
	}

	public String getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(String catalogId) {
        this.catalogId = catalogId;
    }

    public String getPartDescriptionExplicit() {
        return partDescriptionExplicit;
    }

    public void setPartDescriptionExplicit(String partDescriptionExplicit) {
        this.partDescriptionExplicit = partDescriptionExplicit;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getSupplyId() {
        return supplyId;
    }

    public void setSupplyId(String supplyId) {
        this.supplyId = supplyId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getSupplyPartId() {
        return supplyPartId;
    }

    public void setSupplyPartId(String supplyPartId) {
        this.supplyPartId = supplyPartId;
    }

    public String getPartNumber() {
        return partNumber;
    }

    public void setPartNumber(String partNumber) {
        this.partNumber = partNumber;
    }

    public String getPrinterMaterial() {
        return printerMaterial;
    }

    public void setPrinterMaterial(String printerMaterial) {
        this.printerMaterial = printerMaterial;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setPartType(String partType) {
        this.partType = partType;
    }

    public String getPartType() {
        return partType;
    }

    public String getConsumableType() {
        return consumableType;
    }

    public void setConsumableType(String consumableType) {
        this.consumableType = consumableType;
    }

    public boolean isImplicitFlag() {
        return implicitFlag;
    }

    public void setImplicitFlag(boolean implicitFlag) {
        this.implicitFlag = implicitFlag;
    }

    public String getProductModel() {
        return productModel;
    }

    public void setProductModel(String productModel) {
        this.productModel = productModel;
    }

    public String getYield() {
        return yield;
    }

    public void setYield(String yield) {
        this.yield = yield;
    }

    public String getAgreementId() {
        return agreementId;
    }

    public void setAgreementId(String agreementId) {
        this.agreementId = agreementId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOrderLineType() {
        return orderLineType;
    }

    public void setOrderLineType(String orderLineType) {
        this.orderLineType = orderLineType;
    }

	public void setCancellationReason(String cancellationReason) {
		this.cancellationReason = cancellationReason;
	}

	public String getCancellationReason() {
		return cancellationReason;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getUnitPrice() {
		if(unitPrice == null || unitPrice.isEmpty()) return "0";
		return unitPrice;
	}

	public void setUnitPrice(String unitPrice) {
		this.unitPrice = unitPrice;
	}

	public String getTax() {
		if(tax == null || tax.isEmpty()) return "0";
		return tax;
	}

	public void setTax(String tax) {
		this.tax = tax;
	}

	public String getTotalLinePrice() {
		if(totalLinePrice == null || totalLinePrice.isEmpty()) return "0";
		return totalLinePrice;
	}

	public void setTotalLinePrice(String totalLinePrice) {
		this.totalLinePrice = totalLinePrice;
	}
	
	  public String getTotalPrice() {
			if(totalPrice == null || totalPrice.isEmpty()) return "0";
		  return totalPrice;
		}

		public void setTotalPrice(String totalPrice) {
			this.totalPrice = totalPrice;
		}

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	public String getCatalogType() {
		return catalogType;
	}

	public void setCatalogType(String catalogType) {
		this.catalogType = catalogType;
	}

	public String getMaterialLine() {
		return materialLine;
	}

	public void setMaterialLine(String materialLine) {
		this.materialLine = materialLine;
	}

	public String getSapParentLineNumber() {
		return sapParentLineNumber;
	}

	public void setSapParentLineNumber(String sapParentLineNumber) {
		this.sapParentLineNumber = sapParentLineNumber;
	}
	
	public String getDescriptionLocalLang() {
		return descriptionLocalLang;
	}

	public void setDescriptionLocalLang(String descriptionLocalLang) {
		this.descriptionLocalLang = descriptionLocalLang;
	}
}
