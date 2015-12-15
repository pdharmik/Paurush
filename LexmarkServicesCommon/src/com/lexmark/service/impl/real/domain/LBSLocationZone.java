package com.lexmark.service.impl.real.domain;

import java.io.Serializable;

public class LBSLocationZone implements Serializable {
	private static final long serialVersionUID = 6483553507743496750L;

	private String zone;
	private String zoneId;

	public String getZone() {
		return zone;
	}
	public void setZone(String zone) {
		this.zone = zone;
	}
	public String getZoneId() {
		return zoneId;
	}
	public void setZoneId(String zoneId) {
		this.zoneId = zoneId;
	}

}
