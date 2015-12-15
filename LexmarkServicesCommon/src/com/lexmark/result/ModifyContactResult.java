package com.lexmark.result;

import java.io.Serializable;

public class ModifyContactResult implements Serializable {
	private static final long serialVersionUID = 4476343329870281377L;
	private Boolean modified;
	public Boolean getModified() {
		return modified;
	}
	public void setModified(Boolean modified) {
		this.modified = modified;
	}

}
