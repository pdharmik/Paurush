package com.lexmark.domain;

import java.io.Serializable;

public class ReportProperties implements Serializable{
	private static final long serialVersionUID = 944692186925514120L;
	private static final String DEFAULT_OJBECT_TYPE = "portal_services_document";
	
	protected String fileName;
    protected String objectName;
    protected String ownerName;
    protected String locale;
    protected String portalUid;
    protected String mdmlevel;
    protected String mdmid;
    protected String legalName;
    protected String accountName;
    protected String usernumber;
    
    protected String definitionId;
    protected String runLogId;
    protected String subcategoryName;
    protected String scheduleFrequency;

    protected String isoverwrite;
    protected String aContentType;
    protected String aclName;
    protected String aclDomain;
    protected String rFolderPath;
    protected String rObjectType = DEFAULT_OJBECT_TYPE;
    protected String rObjectId;
    protected String fileDataLink;
    
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFileDataLink() {
		return fileDataLink;
	}
	public void setFileDataLink(String fileDataLink) {
		this.fileDataLink = fileDataLink;
	}
	public String getObjectName() {
		return objectName;
	}
	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}
	public String getOwnerName() {
		return ownerName;
	}
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}
	public String getLocale() {
		return locale;
	}
	public void setLocale(String locale) {
		this.locale = locale;
	}
	public String getPortalUid() {
		return portalUid;
	}
	public void setPortalUid(String portalUid) {
		this.portalUid = portalUid;
	}
	public String getMdmlevel() {
		return mdmlevel;
	}
	public void setMdmlevel(String mdmlevel) {
		this.mdmlevel = mdmlevel;
	}
	public String getMdmid() {
		return mdmid;
	}
	public void setMdmid(String mdmid) {
		this.mdmid = mdmid;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public String getUsernumber() {
		return usernumber;
	}
	public void setUsernumber(String usernumber) {
		this.usernumber = usernumber;
	}
	public String getDefinitionId() {
		return definitionId;
	}
	public void setDefinitionId(String definitionId) {
		this.definitionId = definitionId;
	}
	public String getSubcategoryName() {
		return subcategoryName;
	}
	public void setSubcategoryName(String subcategoryName) {
		this.subcategoryName = subcategoryName;
	}
	public String getScheduleFrequency() {
		return scheduleFrequency;
	}
	public void setScheduleFrequency(String scheduleFrequency) {
		this.scheduleFrequency = scheduleFrequency;
	}
	public String getIsoverwrite() {
		return isoverwrite;
	}
	public void setIsoverwrite(String isoverwrite) {
		this.isoverwrite = isoverwrite;
	}
	public String getAContentType() {
		return aContentType;
	}
	public void setAContentType(String contentType) {
		aContentType = contentType;
	}
	public String getAclName() {
		return aclName;
	}
	public void setAclName(String aclName) {
		this.aclName = aclName;
	}
	public String getAclDomain() {
		return aclDomain;
	}
	public void setAclDomain(String aclDomain) {
		this.aclDomain = aclDomain;
	}
	public String getRFolderPath() {
		return rFolderPath;
	}
	public void setRFolderPath(String folderPath) {
		rFolderPath = folderPath;
	}
	public String getRObjectType() {
		return rObjectType;
	}
	public void setRObjectType(String objectType) {
		rObjectType = objectType;
	}
	public String getRObjectId() {
		return rObjectId;
	}
	public void setRObjectId(String objectId) {
		rObjectId = objectId;
	}
	public String getLegalName() {
		return legalName;
	}
	public void setLegalName(String legalName) {
		this.legalName = legalName;
	}
	public String getRunLogId() {
		return runLogId;
	}
	public void setRunLogId(String runLogId) {
		this.runLogId = runLogId;
	}
	
	
}
