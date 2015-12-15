package com.lexmark.contract;

import java.io.Serializable;

public class DocumentViewContract implements Serializable{
    
    private static final long serialVersionUID = 1L;
	private String path;

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
}
