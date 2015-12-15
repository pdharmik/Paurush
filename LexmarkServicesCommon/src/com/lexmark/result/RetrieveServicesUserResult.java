package com.lexmark.result;

import java.io.Serializable;

import com.lexmark.domain.ServicesUser;

public class RetrieveServicesUserResult implements Serializable {

	private static final long serialVersionUID = -8419062289341274423L;
	private ServicesUser servicesUser;
	public ServicesUser getServicesUser() {
		return servicesUser;
	}
	public void setServicesUser(ServicesUser servicesUser) {
		this.servicesUser = servicesUser;
	}
}
