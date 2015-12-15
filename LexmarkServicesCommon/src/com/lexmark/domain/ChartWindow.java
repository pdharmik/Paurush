package com.lexmark.domain;

import java.io.Serializable;

public class ChartWindow implements Serializable {
    
    private static final long serialVersionUID = 1L;
 
	private String windowPosition;
	private String portletInstanceId;
	
	public String getWindowPosition() {
		return windowPosition;
	}
	public void setWindowPosition(String windowPosition) {
		this.windowPosition = windowPosition;
	}
	public String getPortletInstanceId() {
		return portletInstanceId;
	}
	public void setPortletInstanceId(String portletInstanceId) {
		this.portletInstanceId = portletInstanceId;
	}
	
	

}
