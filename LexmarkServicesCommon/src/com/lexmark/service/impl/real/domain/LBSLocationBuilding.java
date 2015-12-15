package com.lexmark.service.impl.real.domain;

import java.io.Serializable;

public class LBSLocationBuilding implements Serializable {
	private static final long serialVersionUID = 6483553507743496750L;

	private String building;
	private String buildingId;

	public String getBuilding() {
		return building;
	}
	public void setBuilding(String building) {
		this.building = building;
	}
	public String getBuildingId() {
		return buildingId;
	}
	public void setBuildingId(String buildingId) {
		this.buildingId = buildingId;
	}

}
