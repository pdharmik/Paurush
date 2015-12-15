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
	/**
	 * 
	 * @return String 
	 */
	public String getArea() {
		return area;
	}
	/**
	 * 
	 * @param area 
	 */

	public void setArea(String area) {
		this.area = area;
	}
/**
 * 
 * @return String 
 */
	public String getSubArea() {
		return subArea;
	}
/**
 * 
 * @param subArea 
 */
	public void setSubArea(String subArea) {
		this.subArea = subArea;
	}
	/**
	 * 
	 * @return Boolean 
	 */
	public Boolean getUpdateSrFlag() {
		return updateSrFlag;
	}
/**
 * 
 * @param updateSrFlag 
 */
	public void setUpdateSrFlag(Boolean updateSrFlag) {
		this.updateSrFlag = updateSrFlag;
	}
	/**
	 * 
	 * @return String 
	 */
	public String getPrevSrNo() {
		return prevSrNo;
	}
/**
 * 
 * @param prevSrNo 
 */
	public void setPrevSrNo(String prevSrNo) {
		this.prevSrNo = prevSrNo;
	}
/**
 * 
 * @return genericAddress 
 */
	public GenericAddress getGenericAddress() {
		return genericAddress;
	}
/**
 * 
 * @param genericAddress 
 */
	public void setGenericAddress(GenericAddress genericAddress) {
		this.genericAddress = genericAddress;
	}
/**
 * 
 * @return serviceRequest 
 */

	public ServiceRequest getServiceRequest() {
		return serviceRequest;
	}
/**
 * 
 * @param serviceRequest 
 */
	public void setServiceRequest(ServiceRequest serviceRequest) {
		this.serviceRequest = serviceRequest;
	}
/**
 * 
 * @return Long 
 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
/**
 * 
 * @param requestName 
 */
	public void setRequestName(String requestName) {
		this.requestName = requestName;
	}
/**
 * 
 * @return String 
 */
	public String getRequestName() {
		return requestName;
	}
	//Added for CI 14-02-03
	/**
	 * 
	 * @return String 
	 */
	public String getAttachmentDescription() {
		return attachmentDescription;
	}
/**
 * 
 * @param attachmentDescription 
 */
	public void setAttachmentDescription(String attachmentDescription) {
		this.attachmentDescription = attachmentDescription;
	}
	//Added for CI 14-02-03
	
	
}
