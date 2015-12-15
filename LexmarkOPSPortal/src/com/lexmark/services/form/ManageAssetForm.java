package com.lexmark.services.form;


//import javax.validation.Valid;
//import javax.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.lexmark.domain.AccountContact;
import com.lexmark.domain.Asset;
import com.lexmark.domain.Attachment;
import com.lexmark.domain.AttachmentFile;
import com.lexmark.domain.GenericAddress;
import com.lexmark.domain.PageCounts;
import com.lexmark.domain.ServiceRequest;

/**
 * @author wipro 
 *
 */
public class ManageAssetForm extends BaseForm implements Serializable {
	
	/**
	 * Form Bean for Manage Asset
	 */
	private static final long serialVersionUID = -1095193936844451233L;

	private Asset assetDetail;
    private String copyFromConsContact;    

	private ServiceRequest serviceRequest;
    private String installAssetFlag;
	private String copyFromConsAddress;
	private String decommAssetFlag;	
	private AccountContact primarySiteContact;
	private AccountContact secondarySiteContact;
	private GenericAddress installAddress;
	private String addtnDockDetails;	
	private Asset oldAssetInfo;
	private Asset newAssetInfo;	
	private String typeOfRequest;
	private String prevSrNo;
	private Boolean updateSrFlag;	
	private String area;
	private String subArea;
	private String dateFormat;
	private String dateFormatWithTime;
	private String moveType;
	private String attachmentDescription;
	private String backToMap;
	private Map<String, Asset> assetDetailsMap;
	private List<Asset> assetDetailsList;
	
	/**
	 * @return String 
	 */
	public String getFleetManagementFlag() {
		return fleetManagementFlag;
	}

	/**
	 * @param fleetManagementFlag 
	 */
	public void setFleetManagementFlag(String fleetManagementFlag) {
		this.fleetManagementFlag = fleetManagementFlag;
	}

	private List<PageCounts> pageCountsdata;
	private String fleetManagementFlag;

	//added for page Counts end
	/**
	 * @return String 
	 */
	public String getDateFormatWithTime() {
		return dateFormatWithTime;
	}

	/**
	 * @param dateFormatWithTime 
	 */
	public void setDateFormatWithTime(String dateFormatWithTime) {
		this.dateFormatWithTime = dateFormatWithTime;
	}

	/**
	 * @return String 
	 */
	public String getDateFormat() {
		return dateFormat;
	}

	/**
	 * @param dateFormat 
	 */
	public void setDateFormat(String dateFormat) {
		this.dateFormat = dateFormat;
	}

	/**
	 * @return String 
	 */
	public String getArea() {
		return area;
	}

	/**
	 * @param area 
	 */
	public void setArea(String area) {
		this.area = area;
	}

	/**
	 * @return String 
	 */
	public String getSubArea() {
		return subArea;
	}

	/**
	 * @param subArea 
	 */
	public void setSubArea(String subArea) {
		this.subArea = subArea;
	}

	/**
	 * @return Boolean 
	 */
	public Boolean getUpdateSrFlag() {
		return updateSrFlag;
	}

	/**
	 * @param updateSrFlag 
	 */
	public void setUpdateSrFlag(Boolean updateSrFlag) {
		this.updateSrFlag = updateSrFlag;
	}

	/**
	 * @return String 
	 */
	public String getPrevSrNo() {
		return prevSrNo;
	}

	/**
	 * @param prevSrNo 
	 */
	public void setPrevSrNo(String prevSrNo) {
		this.prevSrNo = prevSrNo;
	}

	/**
	 * @return String 
	 */
	public String getTypeOfRequest() {
		return typeOfRequest;
	}

	/**
	 * @param typeOfRequest 
	 */
	public void setTypeOfRequest(String typeOfRequest) {
		this.typeOfRequest = typeOfRequest;
	}

	/**
	 * @return Asset 
	 */
	public Asset getOldAssetInfo() {
		return oldAssetInfo;
	}

	/**
	 * @param oldAssetInfo 
	 */
	public void setOldAssetInfo(Asset oldAssetInfo) {
		this.oldAssetInfo = oldAssetInfo;
	}

	/**
	 * @return Asset 
	 */
	public Asset getNewAssetInfo() {
		return newAssetInfo;
	}

	/**
	 * @param newAssetInfo 
	 */
	public void setNewAssetInfo(Asset newAssetInfo) {
		this.newAssetInfo = newAssetInfo;
	}

	/**
	 * @return String 
	 */
	public String getCopyFromConsContact() {
		return copyFromConsContact;
	}
	
	/**
	 * @param copyFromConsContact 
	 */
	public void setCopyFromConsContact(String copyFromConsContact) {
		this.copyFromConsContact = copyFromConsContact;
	}
	
	/** 
	 * @return String 
	 */
	public String getCopyFromConsAddress() {
		return copyFromConsAddress;
	}
	
	/**
	 * @param copyFromConsAddress 
	 */
	public void setCopyFromConsAddress(String copyFromConsAddress) {
		this.copyFromConsAddress = copyFromConsAddress;
	}

	/**
	 * @return String 
	 */
	public String getAddtnDockDetails() {
		return addtnDockDetails;
	}

	/**
	 * @param addtnDockDetails 
	 */
	public void setAddtnDockDetails(String addtnDockDetails) {
		this.addtnDockDetails = addtnDockDetails;
	}

	/**
	 * @return AccountContact 
	 */
	public AccountContact getPrimarySiteContact() {
		return primarySiteContact;
	}

	/**
	 * @param primarySiteContact 
	 */
	public void setPrimarySiteContact(AccountContact primarySiteContact) {
		this.primarySiteContact = primarySiteContact;
	}

	/**
	 * @return AccountContact 
	 */
	public AccountContact getSecondarySiteContact() {
		return secondarySiteContact;
	}

	/**
	 * @param secondarySiteContact 
	 */
	public void setSecondarySiteContact(AccountContact secondarySiteContact) {
		this.secondarySiteContact = secondarySiteContact;
	}

	/**
	 * @return String 
	 */
	public String getDecommAssetFlag() {
		return decommAssetFlag;
	}

	/**
	 * @param decommAssetFlag 
	 */
	public void setDecommAssetFlag(String decommAssetFlag) {
		this.decommAssetFlag = decommAssetFlag;
	}

	/**
	 * @return String 
	 */
	public String getInstallAssetFlag() {
		return installAssetFlag;
	}

	/**
	 * @param installAssetFlag 
	 */
	public void setInstallAssetFlag(String installAssetFlag) {
		this.installAssetFlag = installAssetFlag;
	}
	/**
	 * @return ServiceRequest 
	 */
	public ServiceRequest getServiceRequest() {
		return serviceRequest;
	}

	/**
	 * @param serviceRequest 
	 */
	public void setServiceRequest(ServiceRequest serviceRequest) {
		this.serviceRequest = serviceRequest;
	}

	/** 
	 * @return Asset 
	 */
	public Asset getAssetDetail() {
		return assetDetail;
	}
	
	/**
	 * @param assetDetail 
	 */
	public void setAssetDetail(Asset assetDetail) {
		this.assetDetail = assetDetail;
	}

	/**
	 * @return String 
	 */
	public String getMoveType() {
		return moveType;
	}

	/**
	 * @param moveType 
	 */
	public void setMoveType(String moveType) {
		this.moveType = moveType;
	}
	/**
	 * @return GenericAddress 
	 */
	public GenericAddress getInstallAddress() {
		return installAddress;
	}

	/**
	 * @param installAddress 
	 */
	public void setInstallAddress(GenericAddress installAddress) {
		this.installAddress = installAddress;
	}
	
	/**
	 * @param pageCountsdata the pageCountsdata to set
	 */
	public void setPageCountsdata(List<PageCounts> pageCountsdata) {
		this.pageCountsdata = pageCountsdata;
	}

	/**
	 * @return the pageCountsdata
	 */
	public List<PageCounts> getPageCountsdata() {
		return pageCountsdata;
	}

	/**
	 * @param attachmentDescription the attachmentDescription to set
	 */
	public void setAttachmentDescription(String attachmentDescription) {
		this.attachmentDescription = attachmentDescription;
	}

	/**
	 * @return the attachmentDescription
	 */
	public String getAttachmentDescription() {
		return attachmentDescription;
	}

	/**
	 * @param backToMap the backToMap to set
	 */
	public void setBackToMap(String backToMap) {
		this.backToMap = backToMap;
	}

	/**
	 * @return the backToMap
	 */
	public String getBackToMap() {
		return backToMap;
	}

	public Map<String, Asset> getAssetDetailsMap() {
		return assetDetailsMap;
	}
	public void setAssetDetailsMap(Map<String, Asset> assetDetailsMap) {
		this.assetDetailsMap = assetDetailsMap;
	}

	/**
	 * @param assetDetailsList the assetDetailsList to set
	 */
	public void setAssetDetailsList(List<Asset> assetDetailsList) {
		this.assetDetailsList = assetDetailsList;
	}

	/**
	 * @return the assetDetailsList
	 */
	public List<Asset> getAssetDetailsList() {
		return assetDetailsList;
	}
	
}
