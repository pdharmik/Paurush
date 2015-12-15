package com.lexmark.services.form;

import java.io.Serializable;
import com.lexmark.domain.GenericAddress;
import com.lexmark.domain.ServiceRequest;

/**
 * @author Wipro 
 * @version 2.1 
 *
 */

public class ManageAddressForm extends BaseForm implements Serializable {

	/**
	 * Form bean for Manage Address
	 */
	private static final long serialVersionUID = 1019475489685274062L;
	private ServiceRequest serviceRequest;
	private GenericAddress genericAddress;
	private String requestName;
	private String prevSrNo;
	
	private Boolean updateSrFlag;
	
	private String area;
	private String subArea;
	private String attachmentDescription;//Added for CI 14-02-03
	
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
	
	public Boolean getUpdateSrFlag() {
		return updateSrFlag;
	}

	public void setUpdateSrFlag(Boolean updateSrFlag) {
		this.updateSrFlag = updateSrFlag;
	}
	
	public String getPrevSrNo() {
		return prevSrNo;
	}

	public void setPrevSrNo(String prevSrNo) {
		this.prevSrNo = prevSrNo;
	}

	public GenericAddress getGenericAddress() {
		return genericAddress;
	}

	public void setGenericAddress(GenericAddress genericAddress) {
		this.genericAddress = genericAddress;
	}

	public ServiceRequest getServiceRequest() {
		return serviceRequest;
	}

	public void setServiceRequest(ServiceRequest serviceRequest) {
		this.serviceRequest = serviceRequest;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setRequestName(String requestName) {
		this.requestName = requestName;
	}

	public String getRequestName() {
		return requestName;
	}
	//Added for CI 14-02-03
	public String getAttachmentDescription() {
		return attachmentDescription;
	}

	public void setAttachmentDescription(String attachmentDescription) {
		this.attachmentDescription = attachmentDescription;
	}
	//Added for CI 14-02-03
	
	
}
