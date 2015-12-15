package com.lexmark.service.impl.real.domain;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;

import com.amind.common.domain.Attachment;

/**
 * The mapping file: do-servicerequestdetailattachment-mapping.xml
 */
public class ServiceRequestDetailAttachmentDo extends Attachment implements Serializable {

    private static final long serialVersionUID = 4764630795319874544L;

    private String visibilityRole;
    private String identifier;
	private String displayFileName;
	private String size;
    public String getVisibilityRole() {
        return visibilityRole;
    }

    public void setVisibilityRole(String visibilityRole) {
        this.visibilityRole = visibilityRole;
    }

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public String getIdentifier() {
		return identifier;
	}
	public void setDisplayFileName(String displayFileName) {
		this.displayFileName = displayFileName;
	}

	public String getDisplayFileName() {
		return displayFileName;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getSize() {
		if(StringUtils.isEmpty(size))
		{
			size = "0";
		}
		return size;
	}
}
