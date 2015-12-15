package com.lexmark.service.impl.real.catalogService;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.amind.session.Session;
import com.amind.session.SessionFactory;
import com.amind.session.StatelessSessionFactory;
import com.lexmark.contract.GlobalCatalogListContract;
import com.lexmark.domain.Bundle;
import com.lexmark.result.GlobalCatalogListResult;
import com.lexmark.service.api.SiebelCrmServiceException;
import com.lexmark.service.impl.real.AmindCrmSessionHandle;
import com.lexmark.service.impl.real.hardwareService.GlobalBundleListService;
import com.lexmark.service.impl.real.util.AmindServiceUtil;
import com.lexmark.service.impl.real.util.LogUtil;

/**
 * This class provides methods for Global search parallel calls (Supplies, Bundles, Accessories)
 * 
 * @author Ivan Mdzeluri
 * @since 9/16/15
 */

public class GlobalCatalogListService {
	private static final Logger logger = LogManager.getLogger(GlobalCatalogListService.class);
	
	private StatelessSessionFactory statelessSessionFactory;

	public SessionFactory getStatelessSessionFactory() {
		if (statelessSessionFactory == null) {
			statelessSessionFactory = new StatelessSessionFactory();
		}
		return statelessSessionFactory;
	}

	public void setStatelessSessionFactory(
			StatelessSessionFactory statelessSessionFactory) {
		this.statelessSessionFactory = statelessSessionFactory;
	}
	
	
	public GlobalCatalogListResult retrieveCatalogList(GlobalCatalogListContract contract) throws Exception {
		logger.debug("[IN] retrieveCatalogList");
		GlobalCatalogListResult result = new GlobalCatalogListResult();
		Session session = null;
		AmindCrmSessionHandle crmSessionHandle = (AmindCrmSessionHandle) contract.getSessionHandle();
		try {
			GlobalSuppliesListService service = new GlobalSuppliesListService(contract);
            service.checkRequiredFields();
            session = crmSessionHandle.acquireMultiple();
            service.setSession(session);
            service.buildSearchExpression();			
			result.setSuppliesPartsList(service.queryAndGetResultList());
			
			result.setSuppliesTotalCount(service.getTotalCountOfOrderParts());
		} catch (Exception e) {
		    LogUtil.logAmindServiceCallException(LogUtil.stackTraceExecutionPoint(), e, contract);		    
			throw new SiebelCrmServiceException("retrieveCatalogList failed!", e);
		} finally {
			crmSessionHandle.releaseMultipleSession(session);
		}
		logger.debug("[OUT] retrieveCatalogList");
		return result;
	}
	
	
	public GlobalCatalogListResult retrieveAccessoriesB2b(GlobalCatalogListContract contract) throws Exception {
		logger.debug("[IN] retrieveCatalogListWithContractNumber");
		GlobalCatalogListResult result = new GlobalCatalogListResult();
		Session session = null;
		AmindCrmSessionHandle crmSessionHandle = (AmindCrmSessionHandle) contract.getSessionHandle();
		try {
			GlobalAccessoriesListService service = new GlobalAccessoriesListService(contract);
            service.checkRequiredFields();
            session = crmSessionHandle.acquireMultiple();
			service.setSession(session);
            if(contract.isAccHardwareSuppliesFlag()) {
            	service.buildSearchExpressionAccessoriesB2B(true, false);
            	result.setSuppliesList(service.queryAndGetResultListWithContractNumber(false, true));
            }
            else if(contract.isAccHardwareAccessoriesFlag()) {
            	service.buildSearchExpressionAccessoriesB2B(false, true);
            	result.setAccessoriesList(service.queryAndGetResultListWithContractNumber(true,true));
            }
            else {
            service.buildSearchExpressionAccessoriesB2B(false, false);			
			result.setPartsList(service.queryAndGetResultListWithContractNumber(false,true));	
            }
			result.setAccessoriesTotalCount(service.getTotalCountOfOrderParts());
		} catch (Exception e) {
		    LogUtil.logAmindServiceCallException(LogUtil.stackTraceExecutionPoint(), e, contract);		    
			throw new SiebelCrmServiceException("retrieveCatalogListWithContractNumber failed!", e);
		} finally {
			 AmindServiceUtil.releaseAmindCrmMultipleSessionHandle(crmSessionHandle, session);
		}
		logger.debug("[OUT] retrieveCatalogListWithContractNumber");
		return result;
	}
	
	
	public GlobalCatalogListResult retrievePrinterBundleListB2B(GlobalCatalogListContract contract) throws Exception {
		logger.debug("[IN] retrievePrinterBundleListB2B");
		GlobalCatalogListResult result = new GlobalCatalogListResult();
		Session session = null;
		AmindCrmSessionHandle crmSessionHandle = (AmindCrmSessionHandle) contract.getSessionHandle();
		ExecutorService executor = null;
		Session totalCountSession = null;
		
		try {
			final GlobalBundleListService service = new GlobalBundleListService(contract);
			service.checkRequiredFields();
			session = crmSessionHandle.acquireMultiple();
			service.setSession(session);
			totalCountSession = getStatelessSessionFactory().attachSession();
			service.setTotalCountSession(totalCountSession);
			service.buildSearchExpression();
			
			if (contract.isBunNewQueryIndicator()) {
				executor = Executors.newFixedThreadPool(2);
				
				Future<List<Bundle>> bundleList = executor.submit(new Callable<List<Bundle>>() {
					@Override
					public List<Bundle> call() throws Exception {
						return service.queryAndGetResultList();
					}
				});
				
				Future<Integer> totalCountFuture = executor.submit(new Callable<Integer>() {
					@Override
					public Integer call() throws Exception {
						return service.processTotalCount();
					}
				});
				
				result.setBundleList(bundleList.get());
				result.setBundlesTotalCount(totalCountFuture.get());
			} else {
				result.setBundleList(service.queryAndGetResultList());
			}
			
		} catch (Exception e) {
			LogUtil.logAmindServiceCallException(LogUtil.stackTraceExecutionPoint(), e, contract);		    
			throw new SiebelCrmServiceException("retrievePrinterBundleListB2B failed!", e);
		} finally {
			AmindServiceUtil.releaseAmindCrmMultipleSessionHandle(crmSessionHandle, session);
			AmindServiceUtil.releaseSession(session);
			AmindServiceUtil.releaseSession(totalCountSession);
            if (executor!=null && !executor.isShutdown()) {
				executor.shutdown();
			}
			logger.debug("[OUT] retrievePrinterBundleListB2B");
		}
		
		return result;
	}

}
