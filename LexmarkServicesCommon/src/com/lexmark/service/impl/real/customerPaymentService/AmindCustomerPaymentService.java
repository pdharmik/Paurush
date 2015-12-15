package com.lexmark.service.impl.real.customerPaymentService;


import static com.lexmark.service.impl.real.util.AmindServiceUtil.acquireMultipleSession;
import static com.lexmark.service.impl.real.util.AmindServiceUtil.releaseAmindCrmMultipleSessionHandle;

import com.amind.session.Session;
import com.amind.session.SessionFactory;
import com.lexmark.contract.AccountAgreementSoldToContract;
import com.lexmark.contract.AccountReceivableListContract;
import com.lexmark.result.AccountAgreementSoldToResult;
import com.lexmark.result.AccountCustomerReceivableListResult;
import com.lexmark.service.api.CustomerPaymentsService;
import com.lexmark.service.api.SiebelCrmServiceException;
import com.lexmark.service.impl.real.AmindCrmSessionHandle;
import com.lexmark.service.impl.real.AmindSiebelCrmService;
import com.lexmark.service.impl.real.util.AmindServiceUtil;
import com.lexmark.service.impl.real.util.LogUtil;

public class AmindCustomerPaymentService extends AmindSiebelCrmService implements CustomerPaymentsService {

	private SessionFactory statelessSessionFactory;
   
    public AccountCustomerReceivableListResult retrieveAccountReceivableList(AccountReceivableListContract contract) {
        AmindCrmSessionHandle crmSessionHandle = null;
        Session session = null;
        try {
            crmSessionHandle = (AmindCrmSessionHandle) contract.getSessionHandle();
            session = acquireMultipleSession(crmSessionHandle);
            return new AccountCustomerReceivableListService(session).retrieveAccountReceivableList(contract); 
        } catch (Exception e) {
            LogUtil.logAmindServiceCallException(LogUtil.stackTraceExecutionPoint(), e, contract);            
            throw new SiebelCrmServiceException("retrieveCustomerAccountReceivableList failed", e);
        } finally {
        	releaseAmindCrmMultipleSessionHandle(crmSessionHandle, session);
        }
    }
   
	public void setStatelessSessionFactory(SessionFactory statelessSessionFactory) {
		this.statelessSessionFactory = statelessSessionFactory;
	}

	public SessionFactory getStatelessSessionFactory() {
		return statelessSessionFactory;
	}
	
	
    public AccountAgreementSoldToResult retrieveMPSB2BList(AccountAgreementSoldToContract contract) {
        Session session = null;
        try {
            session = getStatelessSessionFactory().attachSession();
            
            return new AccountAgreementSoldToService(session).retrieveMPSB2BList(contract); 
        } catch (Exception e) {
            LogUtil.logAmindServiceCallException(LogUtil.stackTraceExecutionPoint(), e, contract);            
            throw new SiebelCrmServiceException("retrieveAccountAgreementSoldTo failed", e);
        } finally {
        	AmindServiceUtil.releaseSession(session);
        }
    }
	
  
}
