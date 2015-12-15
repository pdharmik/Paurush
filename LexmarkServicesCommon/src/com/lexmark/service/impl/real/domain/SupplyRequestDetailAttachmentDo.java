package com.lexmark.service.impl.real.domain;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;

import com.amind.common.domain.Attachment;

/**
 * The mapping file: do-supplyrequestdetailattachment-mapping.xml
 * 
 * @see com.lexmark.service.impl.real.domain.ServiceRequestDetailAttachmentDo
 * @see do-servicerequestdetailattachment-mapping.xml
 */
public class SupplyRequestDetailAttachmentDo extends Attachment implements Serializable {

    private static final long serialVersionUID = 4764630795319874544L;

    private String visibilityRole;
    private String identifier;
	private String size;
	private String displayFileName;
	
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

	public String getDisplayFileName() {
		return displayFileName;
	}

	public void setDisplayFileName(String displayFileName) {
		this.displayFileName = displayFileName;
	}
	
}
