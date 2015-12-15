package com.lexmark.service.impl.real;

import com.amind.session.Session;
import com.amind.session.SessionFactory;
import com.lexmark.contract.LBSCHLContract;
import com.lexmark.result.LBSCHLListResult;
import com.lexmark.service.api.LBSCHLMappingService;
import com.lexmark.service.api.SiebelCrmServiceException;
import com.lexmark.service.impl.real.addressService.LBSCHLMappingListService;
import com.lexmark.service.impl.real.util.AmindServiceUtil;
import com.lexmark.service.impl.real.util.LogUtil;

public class AmindCHLMappingService extends AmindSiebelCrmService implements LBSCHLMappingService {
	
	private SessionFactory sessionFactory;
	public SessionFactory getSessionFactory() {
		if (sessionFactory == null) {
			throw new SiebelCrmServiceException("SessionFactory not initialized");
		}
		return sessionFactory;
	}
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public AmindCHLMappingService() {
	}

	/**	 
	 * @author David Tsamalashvili 
	 * LBS CHL Mapping ;
	 */
	@Override
	public LBSCHLListResult retrieveCHLtList(LBSCHLContract contract)
			throws Exception {
		logger.debug("[IN] retrieveLBSFloorPlanAssetList");
		
		LBSCHLListResult chlAccount = null;
		//AmindCrmSessionHandle crmSessionHandle = (AmindCrmSessionHandle) contract.getSessionHandle();
		Session session = getStatelessSessionFactory().attachSession();;
		try {
			LBSCHLMappingListService service = new LBSCHLMappingListService(contract);
			service.checkLBSCHLRequiredFields();
			service.buildLBSCHLSearchExpression();
			//session = crmSessionHandle.acquireMultiple();
			service.setSession(session);
			chlAccount = service.queryAndGetLBSCHLList();

		} catch (Exception e) {
			LogUtil.logAmindServiceCallException(LogUtil.stackTraceExecutionPoint(), e, contract);
			throw new SiebelCrmServiceException("retrieveLBSLocationList Failed", e);
		} finally {
			//AmindServiceUtil.releaseAmindCrmMultipleSessionHandle(crmSessionHandle, session);
			AmindServiceUtil.releaseSession(session);
            logger.debug("[OUT] retrieveLBSFloorPlanAssetList");
		}

		return chlAccount;
	}
	
}
