/**
 * 
 */
package com.lexmark.contract.source;

import java.io.Serializable;
import java.util.Locale;

import com.lexmark.domain.GenericAddress;
import com.lexmark.domain.ServiceRequest;

/**
 * @author gsarkar
 *
 */
public class ReturnServiceRequestContract implements Serializable {
    
    private static final long serialVersionUID = 1L;

	private ServiceRequest serviceRequest; 
	private String contactId;
	private Locale locale;
	private String mdmId;
	private String mdmLevel;
	private String userType;
	private String vendorID;
	
	private GenericAddress returnAddress;
	
	/**
	 * @return the serviceRequest
	 */
	public ServiceRequest getServiceRequest() {
		return serviceRequest;
	}
	/**
	 * @param serviceRequest the serviceRequest to set
	 */
	public void setServiceRequest(ServiceRequest serviceRequest) {
		this.serviceRequest = serviceRequest;
	}
	/**
	 * @return the contactId
	 */
	public String getContactId() {
		return contactId;
	}
	/**
	 * @param contactId the contactId to set
	 */
	public void setContactId(String contactId) {
		this.contactId = contactId;
	}
	/**
	 * @return the locale
	 */
	public Locale getLocale() {
		return locale;
	}
	/**
	 * @param locale the locale to set
	 */
	public void setLocale(Locale locale) {
		this.locale = locale;
	}
	/**
	 * @return the mdmId
	 */
	public String getMdmId() {
		return mdmId;
	}
	/**
	 * @param mdmId the mdmId to set
	 */
	public void setMdmId(String mdmId) {
		this.mdmId = mdmId;
	}
	/**
	 * @return the mdmLevel
	 */
	public String getMdmLevel() {
		return mdmLevel;
	}
	/**
	 * @param mdmLevel the mdmLevel to set
	 */
	public void setMdmLevel(String mdmLevel) {
		this.mdmLevel = mdmLevel;
	}
	/**
	 * @return the returnAddress
	 */
	public GenericAddress getReturnAddress() {
		return returnAddress;
	}
	/**
	 * @param returnAddress the returnAddress to set
	 */
	public void setReturnAddress(GenericAddress returnAddress) {
		this.returnAddress = returnAddress;
	}
	/**
	 * @return the userType
	 */
	public String getUserType() {
		return userType;
	}
	/**
	 * @param userType the userType to set
	 */
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getVendorID() {
		return vendorID;
	}
	public void setVendorID(String vendorID) {
		this.vendorID = vendorID;
	}
	
	
}
