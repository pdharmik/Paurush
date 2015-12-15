package com.lexmark.reportScheduler.service.impl.mock;

import com.lexmark.domain.GlobalAccount;
import com.lexmark.service.api.GlobalServiceFacade;

public class GlobalServiceFacadeMock implements GlobalServiceFacade {

	@Override
	public GlobalAccount retriveGlobalAccount(String mdmId, String mdmLevel) {
		GlobalAccount account = new GlobalAccount();
		account.setMdmId("123456789");
		account.setMdmLevel("Global");
		account.setLegalName("LegalName");
		return account;
	}

}
