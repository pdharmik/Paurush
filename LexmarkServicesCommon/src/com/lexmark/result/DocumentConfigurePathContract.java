package com.lexmark.result;

import java.io.Serializable;

public class DocumentConfigurePathContract implements Serializable {
    
    private static final long serialVersionUID = 1L;
	private String path;

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
}
