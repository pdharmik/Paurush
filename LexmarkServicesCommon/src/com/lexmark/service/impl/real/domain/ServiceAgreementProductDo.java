package com.lexmark.service.impl.real.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import com.amind.common.domain.BaseEntity;

/**
 * Represents Asset Mgmt - Service Agreement Product child BC in retrieveDeviceDetail.
 * 
 * mapping file: "do-serviceagreementproductdo-mapping.xml"
 */
public class ServiceAgreementProductDo extends BaseEntity implements Serializable {
	
	private static final long serialVersionUID = 4849719004195843644L;
	
	private String assetId;
	private String serviceDetailsId;
	private Date startDate;
	private Date endDate;
	private ArrayList<EntitlementTemplateServiceDetailsDo> serviceDetails;

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public void setAssetId(String assetId) {
		this.assetId = assetId;
	}

	public String getAssetId() {
		return assetId;
	}

	public void setServiceDetailsId(String serviceDetailsId) {
		this.serviceDetailsId = serviceDetailsId;
	}

	public String getServiceDetailsId() {
		return serviceDetailsId;
	}

	public void setServiceDetails(ArrayList<EntitlementTemplateServiceDetailsDo> serviceDetails) {
		this.serviceDetails = serviceDetails;
	}

	public ArrayList<EntitlementTemplateServiceDetailsDo> getServiceDetails() {
		return serviceDetails;
	}
}
