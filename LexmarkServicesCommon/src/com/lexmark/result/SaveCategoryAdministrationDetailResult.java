package com.lexmark.result;

import java.io.Serializable;

public class SaveCategoryAdministrationDetailResult implements Serializable {

	private static final long serialVersionUID = -3391978520789951524L;
	private Boolean result;
	
	public void setResult(Boolean result) {
		this.result = result;
	}
	public Boolean getResult() {
		return result;
	}
}
