package com.lexmark.service.impl.real.domain;

import java.io.Serializable;

import com.amind.common.domain.BaseEntity;


/**
 * The mapping file: do-ordersuppliesassetentitlementdetail-mapping.xml
 *  
 */
public class OrderSuppliesAssetEntitlementDetailDo extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    private String activityType;

	public void setActivityType(String activityType) {
		this.activityType = activityType;
	}

	public String getActivityType() {
		return activityType;
	}
	
}
