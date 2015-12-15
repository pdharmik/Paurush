/**
 * Copyright@ 2012. This document has been created & written by
 * Wipro technologies for Lexmark and this is copyright to Lexmark
 *
 * File         	: ManageContactForm.java
 * Package     		: com.lexmark.services.form
 * Creation Date 	: 2013
 *
 * Modification History:
 *
 * ---------------------------------------------------------------------
 * Author 				Date				Version  		Comments
 * ---------------------------------------------------------------------
 * wipro		2013 		2.1             Initial Version
 * 
 */

package com.lexmark.services.form;


//import javax.validation.Valid;
//import javax.validation.constraints.NotNull;

import java.io.Serializable;

import com.lexmark.domain.AccountContact;
import com.lexmark.domain.ServiceRequest;

/**
 * This class is used for beans reference in manageContactController  
 * @author wipro
 * @version 2.1
 */
public class ManageContactForm extends BaseForm implements Serializable{
	
	
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
    private ServiceRequest serviceRequest;

	private AccountContact accountContact;
	
	private String pageName; // Added to identify the form in Add, Change or Remove Contact
	
	private String prevSRNumber; //Added on 15 May to identify the previous SR no while updating contact
	
	private String attachmentDescription;//Added for CI 14-02-03
	
	private Boolean updateSrFlag;
	
	/*private String requestType;
	
	public String getRequestType() {
		return requestType;
	}

	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}*/

	private String area;
	private String subArea;
	
	/**
	 * 
	 * @return
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
	 * @return
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
	 * @return
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
	 * @return
	 */
    public AccountContact getAccountContact() {
		return accountContact;
	}
    /**
     * 
     * @param accountContact
     */
	public void setAccountContact(AccountContact accountContact) {
		this.accountContact = accountContact;
	}
   /**
    * 
    * @return
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
	 * @return
	 */
	public String getPageName() {
		return pageName;
	}
	/**
	 * 
	 * @param pageName
	 */
	public void setPageName(String pageName) {
		this.pageName = pageName;
	}
	/**
	 * 
	 * @return
	 */
	public String getPrevSRNumber() {
		return prevSRNumber;
	}
	/**
	 * 
	 * @param prevSRNumber
	 */
	public void setPrevSRNumber(String prevSRNumber) {
		this.prevSRNumber = prevSRNumber;
	}
		//Added for CI 14-02-03 STARTS
	public String getAttachmentDescription() {
		return attachmentDescription;
	}

	public void setAttachmentDescription(String attachmentDescription) {
		this.attachmentDescription = attachmentDescription;
	}
		//Added for CI 14-02-03 ENDS
	

}