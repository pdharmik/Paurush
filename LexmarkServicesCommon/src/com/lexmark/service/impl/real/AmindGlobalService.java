package com.lexmark.service.impl.real;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.amind.session.Session;
import com.amind.session.SessionFactory;
import com.amind.session.StatefulSessionFactory;
import com.lexmark.contract.AddressListContract;
import com.lexmark.contract.AgreementSoldToNumberContract;
import com.lexmark.contract.DunsNumberContract;
import com.lexmark.contract.FSEAccountListContract;
import com.lexmark.contract.GlobalLegalEntityByLegalNameContract;
import com.lexmark.contract.GlobalLegalEntityContract;
import com.lexmark.contract.LegalNameContract;
import com.lexmark.contract.PartnerAccountListContract;
import com.lexmark.contract.PartnerAgreementListContract;
import com.lexmark.contract.PaymentListContract;
import com.lexmark.contract.SiebelAccountIdContract;
import com.lexmark.contract.SiebelAccountListContract;
import com.lexmark.contract.SiebelLOVListContract;
import com.lexmark.domain.GlobalAccount;
import com.lexmark.result.AccountFlagResult;
import com.lexmark.result.AddressListResult;
import com.lexmark.result.AgreementSoldToNumberResult;
import com.lexmark.result.DunsNumberResult;
import com.lexmark.result.FSEAccountListResult;
import com.lexmark.result.GlobalLegalEntityResult;
import com.lexmark.result.LegalNameResult;
import com.lexmark.result.PartnerAccountListResult;
import com.lexmark.result.PartnerAgreementListResult;
import com.lexmark.result.PaymentTypeListResult;
import com.lexmark.result.SiebelAccountIdResult;
import com.lexmark.result.SiebelAccountListResult;
import com.lexmark.result.SiebelLOVListResult;
import com.lexmark.service.api.CrmSessionHandle;
import com.lexmark.service.api.GlobalService;
import com.lexmark.service.api.SiebelCrmServiceException;
import com.lexmark.service.impl.real.contactService.SapAccountService;
import com.lexmark.service.impl.real.delegate.AccountFlagService;
import com.lexmark.service.impl.real.delegate.DunsNumberService;
import com.lexmark.service.impl.real.delegate.FSEAccountListService;
import com.lexmark.service.impl.real.delegate.GlobalLegalEntityListService;
import com.lexmark.service.impl.real.delegate.GlobalLegalEntityService;
import com.lexmark.service.impl.real.delegate.LegalNameService;
import com.lexmark.service.impl.real.delegate.PartnerAccountListService;
import com.lexmark.service.impl.real.delegate.PartnerAgreementListService;
import com.lexmark.service.impl.real.delegate.PaymentTypeListService;
import com.lexmark.service.impl.real.delegate.SiebelAccountIdService;
import com.lexmark.service.impl.real.delegate.SiebelAgreementListService;
import com.lexmark.service.impl.real.delegate.SiebelLovListService;
import com.lexmark.service.impl.real.orderSuppliesAssetService.AmindAgreementSoldToNumberService;
import com.lexmark.service.impl.real.util.AmindServiceUtil;
import com.lexmark.service.impl.real.util.LogUtil;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

/**
 * Implementation of the global service. This class must be initialized with a
 * session factory class.
 * 
 * Do-classes: GlobalLegalEntityDO / do-globalLegalEntity-mapping.xml
 * ListOfValues / do-listofvalues-mapping.xml PartnerAccountDo /
 * do-partneraccount-mapping.xml PartnerAgreementDo /
 * do-partneragreement-mapping.xml PartnerDirectAccountDo /
 * do-partnerdirectaccount-mapping.xml
 * 
 * @author Mike Martus
 * 
 */
public class AmindGlobalService implements GlobalService {

	public static final String PROVINCE_LOV_TYPE = "LXK_PROVINCE";
	public static final String STATE_LOV_TYPE = "STATE_ABBREV";
	public static final String LANGUAGE_ENU = "ENU";
	protected static final Logger logger = LogManager.getLogger(AmindGlobalService.class);
	private SessionFactory statefulSessionFactory;
	private SessionFactory statelessSessionFactory;

	@Override
	public CrmSessionHandle initCrmSessionHandle(CrmSessionHandle handle) {
		AmindCrmSessionHandle amindHandle = (AmindCrmSessionHandle) handle;
		if (amindHandle == null) {
			amindHandle = new AmindCrmSessionHandle();
		}
		try {
			amindHandle.acquireSimple();
			amindHandle.setSessionFactory(statefulSessionFactory);
			amindHandle.releaseSimple();
//		} catch (InterruptedException e) {
		} catch (Exception e) {
		    LogUtil.logAmindServiceCallException(LogUtil.stackTraceExecutionPoint(), e, handle);
			throw new SiebelCrmServiceException("Failed to initialize session", e);
		}
		return amindHandle;
	}

	/**
	 * Releases session of passed handle. Warns if handle is null or not and
	 * instance of required class.
	 * 
	 * @param handle
	 */
	@Override
	public void releaseSessionHandle(CrmSessionHandle handle) {
	    if (handle instanceof AmindCrmSessionHandle) { // TODO(Viktor) need to add methods to  CrmSessionHandle 
	        AmindServiceUtil.releaseAmindCrmSessionHandle((AmindCrmSessionHandle) handle);
	    }
	}

	@Override
	public GlobalLegalEntityResult retrieveGlobalLegalEntityByLegalName(
			GlobalLegalEntityByLegalNameContract contract) {
		throw new RuntimeException("Not implemented yet.");
	}

	/**
	 * TESTS: DunsNumberTest.class
	 */
	@Override
	public DunsNumberResult retrieveDunsNumber(DunsNumberContract contract) {
		logger.debug("[IN] retrieveDunsNumber");
		DunsNumberResult result = new DunsNumberResult();
		Session session = null;
		try {
            session = getStatelessSessionFactory().attachSession();
		    
            DunsNumberService service = new DunsNumberService(contract);
            service.checkRequiredFields();
            service.buildSearchExpression();
			service.setSession(session);
			
			result.setDunsNumber(service.queryAndGetResult());
		} catch (Exception e) {
		    LogUtil.logAmindServiceCallException(LogUtil.stackTraceExecutionPoint(), e, contract);
			throw new SiebelCrmServiceException("Retrieve Duns Number Failed.", e);
		} finally {
			 AmindServiceUtil.releaseSession(session);
		}

		logger.debug("[OUT] retrieveDunsNumber");
		return result;
	}

	/**
	 * TESTS: GlobalEntityListTest.class
	 */
	@Override
	public List<GlobalAccount> retrieveGlobalLegalEntityList() {
		logger.debug("[IN] retrieveGlobalLegalEntityList");
		List<GlobalAccount> globalLegalEntityList;
		GlobalLegalEntityListService service = new GlobalLegalEntityListService();
		Session session = null;
		try {
		    session = getStatelessSessionFactory().attachSession();
			service.setSession(session);
			globalLegalEntityList = service.queryAndGetResultList();
		} catch (Exception e) {
		    LogUtil.logAmindServiceCallException(LogUtil.stackTraceExecutionPoint(), e);
			throw new SiebelCrmServiceException("Retrieve Global Legal Entity List Failed.", e);
		} finally {
			 AmindServiceUtil.releaseSession(session);
		}
		logger.debug("[OUT] retrieveGlobalLegalEntityList");
		return globalLegalEntityList;
	}

	/**
	 * TESTS: SiebelLovListTest.class
	 */
	@Override
	public SiebelLOVListResult retrieveSiebelLOVList(SiebelLOVListContract contract) throws Exception {
		logger.debug("[IN] retrieveSiebelLOVList");
		SiebelLOVListResult result = new SiebelLOVListResult();
		Session session = null; 
		try {
            session = getStatelessSessionFactory().attachSession();
            SiebelLovListService service = new SiebelLovListService(contract, LANGUAGE_ENU);
            service.checkRequiredFields();
            service.buildSearchExpression();
			service.setSession(session);
			result.setLovList(service.queryAndGetResultList());
		} catch (Exception e) {
		    LogUtil.logAmindServiceCallException(LogUtil.stackTraceExecutionPoint(), e, contract);
			throw new SiebelCrmServiceException("retrieveSiebelLOVList failed.", e);
		} finally {
			 AmindServiceUtil.releaseSession(session);
		}
		logger.debug("[OUT] retrieveSiebelLOVList");
		return result;
	}
	
	@Override
	public SiebelLOVListResult retrieveNonCacheableSiebelLOVList(SiebelLOVListContract contract) throws Exception {
		logger.debug("[IN] retrieveSiebelLOVList");
		SiebelLOVListResult result = new SiebelLOVListResult();
		Session session = null; 
		try {
			session = getStatelessSessionFactory().attachSession();
			SiebelLovListService service = new SiebelLovListService(contract, LANGUAGE_ENU);
			service.checkRequiredFields();
			service.buildSearchExpression();
			service.setSession(session);
			result.setLovList(service.queryAndGetResultList());
		} catch (Exception e) {
			LogUtil.logAmindServiceCallException(LogUtil.stackTraceExecutionPoint(), e, contract);
			throw new SiebelCrmServiceException("retrieveSiebelLOVList failed.", e);
		} finally {
			AmindServiceUtil.releaseSession(session);
		}
		logger.debug("[OUT] retrieveSiebelLOVList");
		return result;
	}

	/**
	 * TESTS: GlobalLegalEntityTest.class
	 */
	@Override
	public GlobalLegalEntityResult retrieveGlobalLegalEntity(GlobalLegalEntityContract contract) {
		logger.debug("[IN] retrieveGlobalLegalEntity");
		GlobalLegalEntityResult result = new GlobalLegalEntityResult();
		Session session = null;
		try {
            session = getStatelessSessionFactory().attachSession();
            
            GlobalLegalEntityService service = new GlobalLegalEntityService(contract);
            service.checkRequiredFields();
            service.buildSearchExpression();
			service.setSession(session);
			
			result.setAccount(service.queryAndGetResult());
		} catch (Exception e) {
		    LogUtil.logAmindServiceCallException(LogUtil.stackTraceExecutionPoint(), e, contract);
			throw new SiebelCrmServiceException("retrieveGlobalLegalEntity failed.", e);
		} finally {
			 AmindServiceUtil.releaseSession(session);
		}
		logger.debug("[OUT] retrieveGlobalLegalEntity");
		return result;
	}

	/**
	 * TESTS: GlobalLegalNameTest.class
	 */
	@Override
	public LegalNameResult retrieveLegalName(LegalNameContract contract) {
		logger.debug("[IN] retrieveLegalName");
		LegalNameResult result = new LegalNameResult();
		Session session = null;
		try {
            session = getStatelessSessionFactory().attachSession();
		    
            LegalNameService service = new LegalNameService(contract);
            service.checkRequiredFields();
            service.buildSearchExpression();
			service.setSession(session);
			
			result.setLegalName(service.queryAndGetResult());
		} catch (Exception e) {
		    LogUtil.logAmindServiceCallException(LogUtil.stackTraceExecutionPoint(), e, contract);
			throw new SiebelCrmServiceException("retrieveLegalName failed.", e);
		} finally {
			 AmindServiceUtil.releaseSession(session);
		}
		logger.debug("[OUT] retrieveLegalName");
		return result;
	}

	/**
	 * TESTS: PartnerAccountListTest.class
	 */
	@Override
	public PartnerAccountListResult retrievePartnerAccountList(PartnerAccountListContract contract)
			throws Exception {
		logger.debug("[IN] retrievePartnerAccountList");
		PartnerAccountListResult result = new PartnerAccountListResult();
        Session session = null;
		try {
	        session = getStatelessSessionFactory().attachSession();		    
		    
	        PartnerAccountListService service = new PartnerAccountListService(contract);
	        service.checkRequiredFields();
	        service.buildSearchExpression();
			service.setSession(session);
			
			return service.queryAndGetResultList();
		} catch (Exception e) {
		    LogUtil.logAmindServiceCallException(LogUtil.stackTraceExecutionPoint(), e, contract);
			throw new SiebelCrmServiceException("retrievePartnerAccountList failed.", e);
		} finally {
			 AmindServiceUtil.releaseSession(session);
			logger.debug("[OUT] retrievePartnerAccountList");
		}
	}

	/**
	 * TESTS: PartnerDirectAccountListTest.class
	 */
	@Override
	public FSEAccountListResult retrieveFSEAccountList(FSEAccountListContract contract) throws Exception {
		logger.debug("[IN] retrievePartnerAccountList");
		FSEAccountListResult result = new FSEAccountListResult();
		Session session = null;
		try {
	        session = getStatelessSessionFactory().attachSession();		    
		    
	        FSEAccountListService service = new FSEAccountListService(contract);
	        service.checkRequiredFields();
	        service.buildSearchExpression();
			service.setSession(session);
			
			result.setAccountList(service.queryAndGetResultList());
		} catch (Exception e) {
			LogUtil.logAmindServiceCallException(LogUtil.stackTraceExecutionPoint(), e, contract);
			throw new SiebelCrmServiceException("retrieveFSEAccountList failed.", e);
		} finally {
			 AmindServiceUtil.releaseSession(session);
		}
		logger.debug("[OUT] retrievePartnerAccountList");
		return result;
	}

	/**
	 * TESTS: PartnerAgreementListTest.class
	 */
	@Override
	public PartnerAgreementListResult retrievePartnerAgreementList(PartnerAgreementListContract contract)
			throws Exception {
		logger.debug("[IN] retrievePartnerAgreementList");
		PartnerAgreementListResult result = new PartnerAgreementListResult();
		Session session = null;
		try {
	        session = getStatelessSessionFactory().attachSession();		    
		    
	        PartnerAgreementListService service = new PartnerAgreementListService(contract);
	        service.checkRequiredFields();
	        service.buildSearchExpression();
			service.setSession(session);
			
			result.setPartnerAgreementList(service.queryAndGetResultList());
		} catch (Exception e) {
		    LogUtil.logAmindServiceCallException(LogUtil.stackTraceExecutionPoint(), e, contract);
			throw new SiebelCrmServiceException("retrievePartnerAgreementList failed.", e);
		} finally {
			AmindServiceUtil.detachSession(session);
		}
		logger.debug("[OUT] retrievePartnerAgreementList");
		return result;
	}

	/**
	 * TESTS: SiebelAccountIdTest.class
	 */
	@Override
	public SiebelAccountIdResult retrieveSiebelAccountId(SiebelAccountIdContract contract) throws Exception {
		logger.debug("[IN] retrieveSiebelAccountId");
		SiebelAccountIdResult result = new SiebelAccountIdResult();
		Session session = null;
		try {
	        session = getStatelessSessionFactory().attachSession();		    
	        
	        SiebelAccountIdService service = new SiebelAccountIdService(contract);
	        service.checkRequiredFields();
	        service.buildSearchExpression();
			service.setSession(session);
			
			result.setSiebelAccountId(service.queryAndGetResult());
		} catch (Exception e) {
		    LogUtil.logAmindServiceCallException(LogUtil.stackTraceExecutionPoint(), e, contract);
			throw new SiebelCrmServiceException("retrievePartnerAccountList failed.", e);
		} finally {
			 AmindServiceUtil.releaseSession(session);
		}
		logger.debug("[OUT] retrieveSiebelAccountId");
		return result;
	}

	/**
	 * TESTS: SiebelAgreementListTest.class
	 */
	@Override
	public SiebelAccountListResult retrieveSiebelAgreementList(SiebelAccountListContract contract) throws Exception {
		logger.debug("[IN] retrieveSiebelAgreementList");
		SiebelAccountListResult result = new SiebelAccountListResult();
		Session session = null;
		try {
	        session = getStatelessSessionFactory().attachSession(); 
	        SiebelAgreementListService service = new SiebelAgreementListService(contract);
	        service.checkRequiredFields();
	        service.buildSearchExpression();
			service.setSession(session);
			
			result.setAccountList(service.queryAndGetResultList());
		} catch (Exception e) {
		    LogUtil.logAmindServiceCallException(LogUtil.stackTraceExecutionPoint(), e, contract);
			throw new SiebelCrmServiceException("retrieveSiebelAgreementList failed",e);
		} finally {
			 AmindServiceUtil.releaseSession(session);
		}
		logger.debug("[OUT] retrieveSiebelAgreementList");
		return result;
	}

	/**
	 * 
	 * 
	 */
	@Override
	public AccountFlagResult retrieveEntitelmentFlags(SiebelAccountListContract contract) throws Exception {
		logger.debug("[IN] retrieveEntitelmentFlags");
		AccountFlagResult result = new AccountFlagResult();
		Session session = null;
		try {
			session = getStatelessSessionFactory().attachSession();
			AccountFlagService service = new AccountFlagService(contract);
			service.checkRequiredFields();
			service.buildSearchExpression();
			service.setSession(session);
		
			result = service.queryAndGetResult();
		} catch (Exception e) {
			throw new SiebelCrmServiceException("retrieveEntitelmentFlags failed",e);
		} finally {
			 AmindServiceUtil.releaseSession(session);
		}
		logger.debug("[OUT] retrieveEntitelmentFlags");
		return result;
	}
	
	public SessionFactory getStatelessSessionFactory() {
		return statelessSessionFactory;
	}

	public void setStatelessSessionFactory(SessionFactory statelessSessionFactory) {
		this.statelessSessionFactory = statelessSessionFactory;
	}

	public SessionFactory getStatefulSessionFactory() {
		if (statefulSessionFactory == null) {
			statefulSessionFactory = new StatefulSessionFactory();
		}
		return statefulSessionFactory;
	}

	public void setStatefulSessionFactory(SessionFactory sessionFactory) {
		this.statefulSessionFactory = sessionFactory;
	}

	@Override
	public SiebelAccountListResult retrieveCatalogAgreementList(
			SiebelAccountListContract contract) throws Exception {
		logger.debug("[IN] retrieveCatalogAgreementList");
		SiebelAccountListResult result = new SiebelAccountListResult();
		Session session = null;
		try {
			session = getStatelessSessionFactory().attachSession();

			AccountFlagService service = new AccountFlagService(contract);
			service.checkRequiredFields();
			service.buildSearchExpressionForAccountList();
			service.setSession(session);

			result.setAccountList(service.queryAndGetResultCatalogAgreement());
		} catch (Exception e) {
			throw new SiebelCrmServiceException("retrieveCatalogAgreementList failed",
					e);
		} finally {
			 AmindServiceUtil.releaseSession(session);
		}
		logger.debug("[OUT] retrieveCatalogAgreementList");
		return result;
	}
	
	@Override
	public PaymentTypeListResult retrievePaymentTypeList (PaymentListContract contract) throws Exception {
		logger.debug("[IN] retrievePaymentTypeList");
		PaymentTypeListResult result = new PaymentTypeListResult();
		AmindCrmSessionHandle crmSessionHandle = (AmindCrmSessionHandle) contract.getSessionHandle();
		Session session = null;
		try {
			session = crmSessionHandle.acquireMultiple();
						
			PaymentTypeListService service = new PaymentTypeListService(contract);
			service.checkRequiredFields();
			service.buildSearchExpression();
			service.setSession(session);
			result = service.queryAndGetResult();
		} catch (Exception e) {
			throw new SiebelCrmServiceException("retrievePaymentTypeList failed",
					e);
		} finally {
			AmindServiceUtil.releaseAmindCrmMultipleSessionHandle(crmSessionHandle, session);
			logger.debug("[OUT] retrievePaymentTypeList");
		}
		return result;
	}


	@Override
	public AddressListResult retrieveBillToAddressList(
			AddressListContract contract) throws Exception {
		logger.debug("[IN] retrieveBillToAddressList");
		AddressListResult result = new AddressListResult();
		AmindCrmSessionHandle crmSessionHandle = (AmindCrmSessionHandle) contract
				.getSessionHandle();
		Session session = null;
		try {

			session = AmindServiceUtil.acquireMultipleSession(crmSessionHandle);
			
			SapAccountService service = new SapAccountService(contract);
			service.checkRequiredFields();
			service.buildSearchExpression();
			service.setSession(session);

			result.setAddressList(service.queryAndGetAddressList());
			
		} catch (Exception e) {
			throw new SiebelCrmServiceException("retrieveBillToAddressList failed",
					e);
		} finally {
			AmindServiceUtil.releaseAmindCrmMultipleSessionHandle(crmSessionHandle, session);
		}
		logger.debug("[OUT] retrieveBillToAddressList");
		
		return result;
	}

	@Override
	public AgreementSoldToNumberResult retrieveSoldToList(AgreementSoldToNumberContract contract) throws Exception {
		logger.debug("[IN] retrieveSoldToList");
		
		AgreementSoldToNumberResult result = new AgreementSoldToNumberResult();
		Session session = null;
		
		try {
			AmindAgreementSoldToNumberService service = new AmindAgreementSoldToNumberService(contract);
			service.checkRequiredFields();
			session = getStatelessSessionFactory().attachSession();
			service.setSession(session);
			service.buildSoldToNumberListSearchExpression();

			result.setSoldToNumbers(service.queryAndGetSoldToNumberList());
			
		} catch (Exception e) {
			throw new SiebelCrmServiceException("retrieveSoldToList failed",e);
		} finally {
			AmindServiceUtil.detachSession(session);
		}
		
		logger.debug("[OUT] retrieveSoldToList");
		return result;
	}

}
