package com.lexmark.service.impl.real.domain;

/**
 * @author imdzeluri
 * Mapping file: do-servicerequestactivitiesattachmentdo-mapping.xml
 */

import java.io.Serializable;

import com.amind.common.domain.BaseEntity;

public class ServiceRequestActivitiesAttachmentDo extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1706904373334843119L;
	
	private String attachmentName;

	public String getAttachmentName() {
		return attachmentName;
	}

	public void setAttachmentName(String attachmentName) {
		this.attachmentName = attachmentName;
	}

}
