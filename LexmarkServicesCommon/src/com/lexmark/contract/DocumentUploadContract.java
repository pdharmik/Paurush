package com.lexmark.contract;

import java.io.Serializable;


public class DocumentUploadContract implements Serializable {
    
    private static final long serialVersionUID = 1L;
	private String locale;
	private String aContentType;

	private String fileName;
	private String objectName;
	private String rFolderPath;
	private String rObjectType;
	private String definitionId;

	private String mdmlevel;
	private String mdmId;
	private String legalName;
	
	private byte[] content;
	
	public String getLocale() {
		return locale;
	}
	public void setLocale(String locale) {
		this.locale = locale;
	}
	public String getaContentType() {
		return aContentType;
	}
	public void setaContentType(String aContentType) {
		this.aContentType = aContentType;
	}
	public String getObjectName() {
		return objectName;
	}
	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}
	public String getrFolderPath() {
		return rFolderPath;
	}
	public void setrFolderPath(String rFolderPath) {
		this.rFolderPath = rFolderPath;
	}
	public String getrObjectType() {
		return rObjectType;
	}
	public void setrObjectType(String rObjectType) {
		this.rObjectType = rObjectType;
	}
	public String getDefinitionId() {
		return definitionId;
	}
	public void setDefinitionId(String definitionId) {
		this.definitionId = definitionId;
	}
	public String getMdmlevel() {
		return mdmlevel;
	}
	public void setMdmlevel(String mdmlevel) {
		this.mdmlevel = mdmlevel;
	}
	public String getMdmId() {
		return mdmId;
	}
	public void setMdmId(String mdmId) {
		this.mdmId = mdmId;
	}
	public String getLegalName() {
		return legalName;
	}
	public void setLegalName(String legalName) {
		this.legalName = legalName;
	}
	public byte[] getContent() {
		return content;
	}
	public void setContent(byte[] content) {
		this.content = content;
	}

	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	@Override
	public String toString() {
		StringBuffer sbuff = new StringBuffer();
		sbuff.append("locale: ").append(locale).append("\n");
		sbuff.append("aContentType: ").append(aContentType).append("\n");
		sbuff.append("objectName: ").append(objectName).append("\n");
		sbuff.append("rFolderPath: ").append(rFolderPath).append("\n");
		sbuff.append("rObjectType: ").append(rObjectType).append("\n");
		sbuff.append("definitionId: ").append(definitionId).append("\n");
		sbuff.append("mdmlevel: ").append(mdmlevel).append("\n");
		sbuff.append("mdmId: ").append(mdmId).append("\n");
		sbuff.append("legalName: ").append(legalName).append("\n");
		sbuff.append("content: ").append(content.length).append("bytes.\n");
		return sbuff.toString();
	}
	

}
