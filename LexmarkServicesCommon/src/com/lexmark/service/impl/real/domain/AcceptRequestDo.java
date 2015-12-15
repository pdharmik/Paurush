package com.lexmark.service.impl.real.domain;

/**
 * @author imdzeluri
 * mapping-file: "do-acceptrequestdo-mapping.xml"
 *
 */

import java.io.Serializable;

import com.amind.common.domain.BaseEntity;

public class AcceptRequestDo extends BaseEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String status;
	private String requestNumber;
	private String comment;
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRequestNumber() {
		return requestNumber;
	}

	public void setRequestNumber(String requestNumber) {
		this.requestNumber = requestNumber;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}
