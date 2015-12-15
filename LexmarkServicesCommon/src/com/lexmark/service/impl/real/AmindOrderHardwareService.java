package com.lexmark.service.impl.real;

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
import com.lexmark.contract.HardwareCatalogContract;
import com.lexmark.domain.Bundle;
import com.lexmark.result.BundleListResult;
import com.lexmark.result.HardwareCatalogResult;
import com.lexmark.service.api.OrderHardwareService;
import com.lexmark.service.api.SiebelCrmServiceException;
import com.lexmark.service.impl.real.hardwareService.BundleListService;
import com.lexmark.service.impl.real.hardwareService.HardwareCatalogService;
import com.lexmark.service.impl.real.util.AmindServiceUtil;
import com.lexmark.service.impl.real.util.LogUtil;

public class AmindOrderHardwareService implements OrderHardwareService {

	public static final Logger logger = LogManager.getLogger(AmindOrderHardwareService.class);
	
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
	
	@Override
	public HardwareCatalogResult retrieveHardwareList(
			HardwareCatalogContract catalogListContract) throws Exception {
		logger.debug("[IN] retrieveHardwareList");
		HardwareCatalogResult result = new HardwareCatalogResult();
		Session session = null;
		
		//AmindCrmSessionHandle crmSessionHandle = (AmindCrmSessionHandle) catalogListContract.getSessionHandle();
		try {
            HardwareCatalogService service = new HardwareCatalogService(catalogListContract);
            service.checkRequiredFields();
            //session = crmSessionHandle.acquireMultiple();
            session = getStatelessSessionFactory().attachSession();
            
			service.setSession(session);
			
			result.setHardwareCatalog(service.queryAndGetResultList());
			
		} catch (Exception e) {
		    LogUtil.logAmindServiceCallException(LogUtil.stackTraceExecutionPoint(), e, catalogListContract);		    
			throw new SiebelCrmServiceException("retrieveHardwareList failed!", e);
		} finally {
			AmindServiceUtil.releaseSession(session);                         
			//AmindServiceUtil.releaseAmindCrmMultipleSessionHandle(crmSessionHandle, session);
		}
		logger.debug("[OUT] retrieveHardwareList");
		if(result.getHardwareCatalog() != null) {
			logger.debug("[Number Of Records Found:] "  + result.getHardwareCatalog().toString());
			if(result.getHardwareCatalog().getBundleList() != null && !result.getHardwareCatalog().getBundleList().isEmpty()) {
				for(Bundle bundle : result.getHardwareCatalog().getBundleList()) {
					logger.debug("Bundle:  " +  bundle.toString());
					
				}
			}
		}
		return result;
	}
	
	@Override
	public BundleListResult retrievePrinterBundleListB2B(HardwareCatalogContract catalogListContract) throws Exception {
		logger.debug("[IN] retrievePrinterBundleListB2B");
		BundleListResult result = new BundleListResult();
		Session session = null;
		AmindCrmSessionHandle crmSessionHandle = (AmindCrmSessionHandle) catalogListContract.getSessionHandle();
		ExecutorService executor = null;
		Session totalCountSession = null;
		
		try {
			final BundleListService service = new BundleListService(catalogListContract);
			service.checkRequiredFields();
			session = crmSessionHandle.acquireMultiple();
			service.setSession(session);
			totalCountSession = getStatelessSessionFactory().attachSession();
			service.setTotalCountSession(totalCountSession);
			service.buildSearchExpression();
			
			if (catalogListContract.isNewQueryIndicator()) {
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
				result.setTotalCount(totalCountFuture.get());
			} else {
				result.setBundleList(service.queryAndGetResultList());
			}
			
		} catch (Exception e) {
			LogUtil.logAmindServiceCallException(LogUtil.stackTraceExecutionPoint(), e, catalogListContract);		    
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
