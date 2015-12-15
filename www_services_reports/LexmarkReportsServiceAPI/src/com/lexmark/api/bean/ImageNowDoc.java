/**
 * 
 */
package com.lexmark.api.bean;


/**
 * This is the bean for deleting/downloading document from ImageNow pageId,
 * fileContent, pageName are for downloading document documentId used for both
 */
public class ImageNowDoc extends LexmarkReportServiceBean {

	private String documentId;

	private String pageId;

	private byte[] fileContent;

	private String pageName;
/**
 * 
 * @return String 
 */
	public String getDocumentId() {
		return documentId;
	}
/**
 * 
 * @param documentId 
 */
	public void setDocumentId(String documentId) {
		this.documentId = documentId;
	}
	/**
	 * 
	 * @return String 
	 */
	public String getPageId() {
		return pageId;
	}
/**
 * 
 * @param pageId 
 */
	public void setPageId(String pageId) {
		this.pageId = pageId;
	}
	/**
	 * 
	 * @return byte[] 
	 */
	public byte[] getFileContent() {
		return fileContent;
	}
/**
 * 
 * @param fileContent  
 */
	public void setFileContent(byte[] fileContent) {
		this.fileContent = fileContent;
	}
	/**
	 * 
	 * @return String 
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
}
