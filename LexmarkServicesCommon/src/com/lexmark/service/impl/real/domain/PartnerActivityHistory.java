package com.lexmark.service.impl.real.domain;

import java.util.ArrayList;
import java.util.Date;

/**
 * mapping file: "do-partneractivityhistory-mapping.xml"
 */
public class PartnerActivityHistory extends PartnerActivityBase{
	private static final long serialVersionUID = 5566814707965778235L;
	
	private String serviceRequestor;
	private String repairDescription;
	private String startDate;
	private String endDate;
	private String serviceRequestDateWithOutMarker;
	private ArrayList<PartnerActivityHistoryParts> parts;
	
	public String getRepairDescription() {
		return repairDescription;
	}
	public void setRepairDescription(String repairDescription) {
		this.repairDescription = repairDescription;
	}
	public String getServiceRequestor() {
		return serviceRequestor;
	}
	public void setServiceRequestor(String serviceRequestor) {
		this.serviceRequestor = serviceRequestor;
	}
	public Date getStartDate() {
		return convertStringToDateWithOutMarker(startDate);
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return convertStringToDateWithOutMarker(endDate);
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public void setParts(ArrayList<PartnerActivityHistoryParts> parts) {
		this.parts = parts;
	}
	public ArrayList<PartnerActivityHistoryParts> getParts() {
		return parts;
	}
	public void setServiceRequestDateWithOutMarker(
			String serviceRequestDateWithOutMarker) {
		this.serviceRequestDateWithOutMarker = serviceRequestDateWithOutMarker;
	}
	public Date getServiceRequestDateWithOutMarker() {
		return convertStringToDateWithOutMarker(serviceRequestDateWithOutMarker);
	}
}
