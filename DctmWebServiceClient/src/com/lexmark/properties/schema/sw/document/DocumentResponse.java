package com.lexmark.properties.schema.sw.document;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "response")
 @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "statusCode",
        "rObjectId",
        "rFolderPath"
    })
public class DocumentResponse {
    @XmlElement(name = "status_code", required = true)
    protected int statusCode;

    @XmlElement(name = "r_object_id", required = true)
    protected String rObjectId;

    @XmlElement(name = "r_folder_path", required = true)
    protected String rFolderPath;

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getRObjectId() {
		return rObjectId;
	}

	public void setRObjectId(String objectId) {
		rObjectId = objectId;
	}

	public String getRFolderPath() {
		return rFolderPath;
	}

	public void setRFolderPath(String folderPath) {
		rFolderPath = folderPath;
	}
}
