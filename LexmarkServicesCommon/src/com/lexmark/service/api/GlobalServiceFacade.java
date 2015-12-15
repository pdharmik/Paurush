package com.lexmark.service.api;

import com.lexmark.domain.GlobalAccount;

public interface GlobalServiceFacade {
	public GlobalAccount retriveGlobalAccount(String mdmId, String mdmLevel);
}
