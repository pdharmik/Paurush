package com.lexmark.service.impl.real.domain;

import java.io.Serializable;

import com.amind.common.domain.BaseEntity;

/**
 * The mapping file: do-consumableassettype-mapping.xml
 * 
 * @author vpetruchok
 * @version 1.0, 2012-08-31
 */
public class ConsumableAssetTypeDo extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private String consumableType;
    private String entitlementStatus;
    public String getConsumableType() {
        return consumableType;
    }

    public void setConsumableType(String consumableType) {
        this.consumableType = consumableType;
    }

	public void setEntitlementStatus(String entitlementStatus) {
		this.entitlementStatus = entitlementStatus;
	}

	public String getEntitlementStatus() {
		return entitlementStatus;
	}
}
