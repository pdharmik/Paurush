package com.lexmark.service.impl.real.domain;

import java.io.Serializable;

public class LBSLocationBuildingType implements Serializable {
	private static final long serialVersionUID = 6483553507743496750L;

	private String buildingType;
	
	public String getBuildingType() {
		return buildingType;
	}
	public void setBuildingType(String buildingType) {
		this.buildingType = buildingType;
	}

}
