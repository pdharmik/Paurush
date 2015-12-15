package com.lexmark.result;

import java.io.Serializable;

public class ServiceResult  implements Serializable{
	
	private static final long serialVersionUID = 5674219707790244872L;
	
	private Boolean succeed = true;

	public Boolean isSucceed() {
		return succeed;
	}

	public void setSucceed(Boolean succeed) {
		this.succeed = succeed;
	}
}
