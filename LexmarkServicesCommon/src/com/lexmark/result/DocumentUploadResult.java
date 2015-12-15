package com.lexmark.result;

import java.io.Serializable;

public class DocumentUploadResult implements Serializable {
    
    private static final long serialVersionUID = 1L;
	private String rObjectId;

	public String getrObjectId() {
		return rObjectId;
	}

	public void setrObjectId(String rObjectId) {
		this.rObjectId = rObjectId;
	}

}
