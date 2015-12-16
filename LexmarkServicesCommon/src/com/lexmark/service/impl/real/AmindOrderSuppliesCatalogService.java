package com.lexmark.service.impl.real;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.amind.session.Session;
import com.lexmark.contract.CatalogListContract;
import com.lexmark.contract.GlobalCatalogListContract;
import com.lexmark.result.CatalogListResult;
import com.lexmark.result.GlobalCatalogListResult;
import com.lexmark.service.api.OrderSuppliesCatalogService;
import com.lexmark.service.api.SiebelCrmServiceException;
import com.lexmark.service.impl.real.catalogService.CatalogFieldListService;
import com.lexmark.service.impl.real.catalogService.CatalogListService;
import com.lexmark.service.impl.real.catalogService.GlobalCatalogListService;
import com.lexmark.service.impl.real.util.AmindServiceUtil;
import com.lexmark.service.impl.real.util.LogUtil;

public class AmindOrderSuppliesCatalogService extends AmindSiebelCrmService implements
		OrderSuppliesCatalogService {

	@Override
	public CatalogListResult retrieveCatalogList(CatalogListContract contract) throws Exception {
		logger.debug("[IN] retrieveCatalogList");
		CatalogListResult result = new CatalogListResult();
		Session session = null;
		AmindCrmSessionHandle crmSessionHandle = (AmindCrmSessionHandle) contract.getSessionHandle();
		try {
            CatalogListService service = new CatalogListService(contract);
            service.checkRequiredFields();
            session = crmSessionHandle.acquireMultiple();
            service.setSession(session);
            service.buildSearchExpression();			
			result.setPartsList(service.queryAndGetResultList());	
			
			result.setTotalCount(service.getTotalCountOfOrderParts());
		} catch (Exception e) {
		    LogUtil.logAmindServiceCallException(LogUtil.stackTraceExecutionPoint(), e, contract);		    
			throw new SiebelCrmServiceException("retrieveCatalogList failed!", e);
		} finally {
			crmSessionHandle.releaseMultipleSession(session);
		}
		logger.debug("[OUT] retrieveCatalogList");
		return result;
	}
	
	
	@Override
	public CatalogListResult retrieveCatalogListWithContractNumber(CatalogListContract contract) throws Exception {
		logger.debug("[IN] retrieveCatalogListWithContractNumber");
		CatalogListResult result = new CatalogListResult();
		Session session = null;
		AmindCrmSessionHandle crmSessionHandle = (AmindCrmSessionHandle) contract.getSessionHandle();
		try {
			CatalogListService service = new CatalogListService(contract);
            service.checkRequiredFields();
            session = crmSessionHandle.acquireMultiple();
			service.setSession(session);
            if(contract.isHardwareSuppliesFlag()) {
            	service.buildSearchExpressionWithContractNumber(true, false);  
            	result.setSuppliesList(service.queryAndGetResultListWithContractNumber(false,false)); 
            }
            else if(contract.isHardwareAccessoriesFlag()) {
            	service.buildSearchExpressionWithContractNumber(false, true);
            	result.setAccessoriesList(service.queryAndGetResultListWithContractNumber(true,false));
            }
            else {
            service.buildSearchExpressionWithContractNumber(false, false);			
			result.setPartsList(service.queryAndGetResultListWithContractNumber(false,false));	
            }
			result.setTotalCount(service.getTotalCountOfOrderParts());
		} catch (Exception e) {
		    LogUtil.logAmindServiceCallException(LogUtil.stackTraceExecutionPoint(), e, contract);		    
			throw new SiebelCrmServiceException("retrieveCatalogListWithContractNumber failed!", e);
		} finally {
			 AmindServiceUtil.releaseAmindCrmMultipleSessionHandle(crmSessionHandle, session);
		}
		logger.debug("[OUT] retrieveCatalogListWithContractNumber");
		return result;
}
	

	/**
	 * TESTS: OrderSuppliesCatalogFieldListTest
	 */
	@Override
	public CatalogListResult retrieveCatalogFieldList(CatalogListContract contract) throws Exception {
		logger.debug("[IN] retrieveCatalogFieldList");
		Session session = null; 
		try {
		    session = getStatelessSessionFactory().attachSession();
		    
            CatalogFieldListService service = new CatalogFieldListService(contract);
            service.checkRequiredFields();
			service.setSession(session);
			service.buildSearchExpression();			
			
			CatalogListResult result = new CatalogListResult();
			result.setLovList(service.queryAndGetResultList());
			result.setAgreementId(service.getAgreementId());
			
    		return result;
		} catch (Exception e) { 
		    LogUtil.logAmindServiceCallException(LogUtil.stackTraceExecutionPoint(), e, contract);		    
			throw new SiebelCrmServiceException("retrieveCatalogFieldList failed!", e);
		} finally {
			AmindServiceUtil.releaseSession(session); 
		    logger.debug("[OUT] retrieveCatalogFieldList");
		}
	}
	
	@Override
	public CatalogListResult retrievePrinterTypesB2B(CatalogListContract contract) throws Exception {
		logger.debug("[IN] retrieveCatalogFieldList");
		Session session = null; 
		try {
		    session = getStatelessSessionFactory().attachSession();
		    
            CatalogFieldListService service = new CatalogFieldListService(contract);
            service.checkRequiredFields();
			service.setSession(session);
			service.buildSearchExpressionB2B();			
			
			CatalogListResult result = new CatalogListResult();
			result.setLovList(service.queryAndGetResultListB2B());
			result.setAgreementId(service.getAgreementId());
			
    		return result;
		} catch (Exception e) { 
		    LogUtil.logAmindServiceCallException(LogUtil.stackTraceExecutionPoint(), e, contract);		    
			throw new SiebelCrmServiceException("retrieveCatalogFieldList failed!", e);
		} finally {
			AmindServiceUtil.releaseSession(session); 
		    logger.debug("[OUT] retrieveCatalogFieldList");
		}
	}
	
	
	@Override
	public CatalogListResult retrieveAccessoriesB2b(CatalogListContract contract) throws Exception {
		logger.debug("[IN] retrieveCatalogListWithContractNumber");
		CatalogListResult result = new CatalogListResult();
		Session session = null;
		AmindCrmSessionHandle crmSessionHandle = (AmindCrmSessionHandle) contract.getSessionHandle();
		try {
			CatalogListService service = new CatalogListService(contract);
            service.checkRequiredFields();
            session = crmSessionHandle.acquireMultiple();
			service.setSession(session);
            if(contract.isHardwareSuppliesFlag()) {
            	service.buildSearchExpressionAccessoriesB2B(true, false);
            	result.setSuppliesList(service.queryAndGetResultListWithContractNumber(false, true));
            }
            else if(contract.isHardwareAccessoriesFlag()) {
            	service.buildSearchExpressionAccessoriesB2B(false, true);
            	result.setAccessoriesList(service.queryAndGetResultListWithContractNumber(true,true));
            }
            else {
            service.buildSearchExpressionAccessoriesB2B(false, false);			
			result.setPartsList(service.queryAndGetResultListWithContractNumber(false,true));	
            }
			result.setTotalCount(service.getTotalCountOfOrderParts());
		} catch (Exception e) {
		    LogUtil.logAmindServiceCallException(LogUtil.stackTraceExecutionPoint(), e, contract);		    
			throw new SiebelCrmServiceException("retrieveCatalogListWithContractNumber failed!", e);
		} finally {
			 AmindServiceUtil.releaseAmindCrmMultipleSessionHandle(crmSessionHandle, session);
		}
		logger.debug("[OUT] retrieveCatalogListWithContractNumber");
		return result;
	}
	
	
	@Override
	public GlobalCatalogListResult retrieveGlobalCatalogListB2B(GlobalCatalogListContract globalCatalogListContract) throws Exception {
		logger.debug("[IN] retrieveGlobalCatalogListB2B");
		GlobalCatalogListResult result = new GlobalCatalogListResult();
		ExecutorService executor = null;
		final GlobalCatalogListContract contract = globalCatalogListContract;
		try {
			final GlobalCatalogListService service = new GlobalCatalogListService();
				executor = Executors.newFixedThreadPool(3);
				
				//Supplies
				Future<GlobalCatalogListResult> supplies = executor.submit(new Callable <GlobalCatalogListResult>() {
					@Override
					public GlobalCatalogListResult call() throws Exception {
						return service.retrieveCatalogList(contract);
					}
				});
				
				//Accessories
				Future<GlobalCatalogListResult> accessories = executor.submit(new Callable <GlobalCatalogListResult>() {
					@Override
					public GlobalCatalogListResult call() throws Exception {
						return service.retrieveAccessoriesB2b(contract);
					}
				});
				
				//Bundles
				Future<GlobalCatalogListResult> bundles = executor.submit(new Callable <GlobalCatalogListResult>() {
					@Override
					public GlobalCatalogListResult call() throws Exception {
						return service.retrievePrinterBundleListB2B(contract);
					}
				});
				
				//Supplies
				result.setSuppliesPartsList(supplies.get().getSuppliesPartsList());
				result.setSuppliesTotalCount(supplies.get().getSuppliesTotalCount());
				//Accessories
				result.setPartsList(accessories.get().getPartsList());
				result.setAccessoriesList(accessories.get().getAccessoriesList());
				result.setSuppliesList(accessories.get().getSuppliesList());
				result.setAccessoriesTotalCount(accessories.get().getAccessoriesTotalCount());
				//Bundles
				result.setBundleList(bundles.get().getBundleList());
				result.setBundlesTotalCount(bundles.get().getBundlesTotalCount());
			
		} catch (Exception e) {
			LogUtil.logAmindServiceCallException(LogUtil.stackTraceExecutionPoint(), e, globalCatalogListContract);		    
			throw new SiebelCrmServiceException("retrieveGlobalCatalogListB2B failed!", e);
		} finally {
            if (executor!=null && !executor.isShutdown()) {
				executor.shutdown();
			}
			logger.debug("[OUT] retrieveGlobalCatalogListB2B");
		}
		
		return result;
	}
	
}
