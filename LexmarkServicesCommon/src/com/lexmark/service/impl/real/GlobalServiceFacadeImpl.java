package com.lexmark.service.impl.real;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import com.amind.session.SessionFactory;
import com.amind.session.StatelessSessionFactory;
import com.lexmark.contract.GlobalLegalEntityContract;
import com.lexmark.domain.GlobalAccount;
import com.lexmark.result.GlobalLegalEntityResult;
import com.lexmark.service.api.GlobalService;
import com.lexmark.service.api.GlobalServiceFacade;
import com.lexmark.service.api.SiebelCrmServiceException;
import com.lexmark.service.impl.real.util.LogUtil;
import com.lexmark.util.StringUtil;

public class GlobalServiceFacadeImpl implements GlobalServiceFacade {
	private static Logger logger = LogManager.getLogger(GlobalServiceFacadeImpl.class);
	private GlobalService globalService = null;
	
	public GlobalService getGlobalService() {
		if(globalService == null) {
			globalService = new AmindGlobalService();
			SessionFactory sessionFactory = new StatelessSessionFactory();
			((AmindGlobalService)globalService).setStatelessSessionFactory(sessionFactory);
		}
		return globalService;

	}
	public void setGlobalService(GlobalService globalService) {
		this.globalService = globalService;
	}

	@Override
	public GlobalAccount retriveGlobalAccount(String mdmId, String mdmLevel) {
		if(StringUtil.isStringEmpty(mdmId)|| StringUtil.isStringEmpty(mdmLevel)) {
			return new GlobalAccount();
		}
		GlobalLegalEntityContract contract = new GlobalLegalEntityContract();
		contract.setMdmId(mdmId);
		contract.setMdmLevel(mdmLevel);
		try {
			GlobalLegalEntityResult result = getGlobalService().retrieveGlobalLegalEntity(contract);
			GlobalAccount account = result.getAccount();
			if(account == null) {
				throw new RuntimeException("Retrive null global entity");
			}
			return account;
		}
		catch (Exception e) {
		    LogUtil.logAmindServiceCallException(LogUtil.stackTraceExecutionPoint(), e, contract);
			throw new SiebelCrmServiceException("Retrieve GlobalEntity caught exception", e);
		}
	}

}
