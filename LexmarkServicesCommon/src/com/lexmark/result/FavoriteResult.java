package com.lexmark.result;

import java.io.Serializable;

public class FavoriteResult implements Serializable {
	private static final long serialVersionUID = -8673714178323375278L;
	private boolean result;	
	
	public boolean isResult() {
		return result;
	}
	
	public void setResult(boolean result) {
		this.result = result;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
