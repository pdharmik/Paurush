package com.lexmark.service.impl.real.domain;

import java.io.Serializable;

public class LBSLocationFloor implements Serializable {
	private static final long serialVersionUID = 6483553507743496750L;

	private String floor;
	private String floorId;

	public String getFloor() {
		return floor;
	}
	public void setFloor(String floor) {
		this.floor = floor;
	}
	public String getFloorId() {
		return floorId;
	}
	public void setFloorId(String floorId) {
		this.floorId = floorId;
	}

}
