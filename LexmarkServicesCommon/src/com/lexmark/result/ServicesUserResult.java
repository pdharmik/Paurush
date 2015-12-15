package com.lexmark.result;

import java.io.Serializable;

import com.lexmark.domain.ServicesUser;

public class ServicesUserResult implements Serializable {

	private static final long serialVersionUID = 1990335098014213828L;
	private ServicesUser servicesUser;
	
	public void setServicesUser(ServicesUser servicesUser) {
		this.servicesUser = servicesUser;
	}
	public ServicesUser getServicesUser() {
		return servicesUser;
	}
}
