package com.lexmark.service.impl.real.domain;

import java.io.Serializable;

import com.amind.common.domain.BaseEntity;

import java.util.ArrayList;
import java.util.Date;
/**
 * The mapping file: do-ordersuppliesassetpartdetail-mapping.xml
 * 
 * @see com.lexmark.service.impl.real.domain#OrderSuppliesAssetDetailDo
 * @see com.lexmark.service.impl.real.orderSuppliesAsset.AmindOrderSuppliesAssetServiceUtil#toAsset(OrderSuppliesAssetDetailDo)
 */
public class OrderSuppliesAssetPartDetailDo extends BaseEntity implements Serializable {
	private static final long serialVersionUID = -7824721088426974811L;

	private String partNumber;
	private String description;
	private String partType;
	private Boolean implicitFlag;
	private String catalogId;
	private String printerPartNumber;
	private String assetUsageType;
	private String assetSerialNumber;
	private String consumableType;
	private String yield;
	private String supplyId;
	private String productId;
	
	private String assetId;
	private String area;
	private String subArea;
	private String specialUsage;
	private String printerMaterialNumber;
	
	private String preference; // TODO(Viktor) no such field yet
	private Date effectiveEndDate;
	private ArrayList<OrderSuppliesAssetPartPaymentDetailDo> partPaymentList;
	private String preferenceExplicit;
	private String providerOrderNumber;
	private String vendorPartNumber;

	public String getVendorPartNumber() {
		return vendorPartNumber;
	}

	public void setVendorPartNumber(String vendorPartNumber) {
		this.vendorPartNumber = vendorPartNumber;
	}

	public String getPartNumber() {
		return partNumber;
	}

	public void setPartNumber(String partNumber) {
		this.partNumber = partNumber;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPartType() {
        return partType;
    }

    public void setPartType(String partType) {
        this.partType = partType;
    }

    public Boolean getImplicitFlag() {
        return implicitFlag;
    }
    
    public Boolean isImplicitFlag() {
        return implicitFlag;
    }

    public void setImplicitFlag(Boolean implicitFlag) {
        this.implicitFlag = implicitFlag;
    }

    public String getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(String catalogId) {
        this.catalogId = catalogId;
    }

    public String getPrinterPartNumber() {
        return printerPartNumber;
    }

    public void setPrinterPartNumber(String printerPartNumber) {
        this.printerPartNumber = printerPartNumber;
    }

    public String getAssetUsageType() {
        return assetUsageType;
    }

    public void setAssetUsageType(String assetUsageType) {
        this.assetUsageType = assetUsageType;
    }

    public String getAssetSerialNumber() {
        return assetSerialNumber;
    }

    public void setAssetSerialNumber(String assetSerialNumber) {
        this.assetSerialNumber = assetSerialNumber;
    }

    public String getConsumableType() {
        return consumableType;
    }

    public void setConsumableType(String consumableType) {
        this.consumableType = consumableType;
    }

    public String getYield() {
        return yield;
    }

    public void setYield(String yield) {
        this.yield = yield;
    }

	public String getSupplyId() {
		return supplyId;
	}

	public void setSupplyId(String supplyId) {
		this.supplyId = supplyId;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

    public String getAssetId() {
        return assetId;
    }

    public void setAssetId(String assetId) {
        this.assetId = assetId;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getSubArea() {
        return subArea;
    }

    public void setSubArea(String subArea) {
        this.subArea = subArea;
    }

    public String getSpecialUsage() {
        return specialUsage;
    }

    public void setSpecialUsage(String specialUsage) {
        this.specialUsage = specialUsage;
    }

    public String getPrinterMaterialNumber() {
        return printerMaterialNumber;
    }

    public void setPrinterMaterialNumber(String printerMaterialNumber) {
        this.printerMaterialNumber = printerMaterialNumber;
    }

    public String getPreference() {
        return preference;
    }

    public void setPreference(String preference) {
        this.preference = preference;
    }

	public Date getEffectiveEndDate() {
		return effectiveEndDate;
	}

	public void setEffectiveEndDate(Date effectiveEndDate) {
		this.effectiveEndDate = effectiveEndDate;
	}

	public ArrayList<OrderSuppliesAssetPartPaymentDetailDo> getPartPaymentList() {
		return partPaymentList;
	}

	public void setPartPaymentList(ArrayList<OrderSuppliesAssetPartPaymentDetailDo> partPaymentList) {
		this.partPaymentList = partPaymentList;
	}

	public String getPreferenceExplicit() {
		return preferenceExplicit;
	}

	public void setPreferenceExplicit(String preferenceExplicit) {
		this.preferenceExplicit = preferenceExplicit;
	}

	public String getProviderOrderNumber() {
		return providerOrderNumber;
	}

	public void setProviderOrderNumber(String providerOrderNumber) {
		this.providerOrderNumber = providerOrderNumber;
	}
}
