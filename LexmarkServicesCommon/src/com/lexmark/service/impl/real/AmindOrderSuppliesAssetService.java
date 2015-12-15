package com.lexmark.service.impl.real;

import static com.lexmark.service.impl.real.util.AmindServiceUtil.acquireMultipleSession;
import static com.lexmark.service.impl.real.util.AmindServiceUtil.detachSession;
import static com.lexmark.service.impl.real.util.AmindServiceUtil.releaseAmindCrmMultipleSessionHandle;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.amind.data.service.IDataManager;
import com.amind.session.Session;
import com.amind.session.StatelessSessionFactory;
import com.lexmark.contract.AssetContract;
import com.lexmark.contract.AssetListContract;
import com.lexmark.contract.LocationReportingHierarchyContract;
import com.lexmark.contract.PageCountsContract;
import com.lexmark.contract.UserFavoriteAssetContract;
import com.lexmark.domain.Asset;
import com.lexmark.result.AssetListResult;
import com.lexmark.result.AssetLocationsResult;
import com.lexmark.result.AssetResult;
import com.lexmark.result.FavoriteResult;
import com.lexmark.result.PageCountsResult;
import com.lexmark.service.api.OrderSuppliesAssetService;
import com.lexmark.service.api.SiebelCrmServiceException;
import com.lexmark.service.impl.real.domain.ProductSerialNumberDetailDo;
import com.lexmark.service.impl.real.orderSuppliesAssetService.AmindOrderSuppliesAssetDetailService;
import com.lexmark.service.impl.real.orderSuppliesAssetService.AmindOrderSuppliesAssetDeviceListService;
import com.lexmark.service.impl.real.orderSuppliesAssetService.AmindOrderSuppliesAssetLocationService;
import com.lexmark.service.impl.real.orderSuppliesAssetService.AssetListService;
import com.lexmark.service.impl.real.orderSuppliesAssetService.ConsumableAssetListService;
import com.lexmark.service.impl.real.orderSuppliesAssetService.PageCountsService;
import com.lexmark.service.impl.real.util.AmindServiceUtil;
import com.lexmark.service.impl.real.util.LogUtil;
import com.lexmark.util.LangUtil;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
/**
 * See MPS_OrderManagement_Consumables_Assets_Orders_<...>.docx .
 * 
 * <pre>
 * Siebel do-classes:
 *    OrderSuppliesAssetDo         / do-ordersuppliesassetdo-mapping.xml
 *    OrderSuppliesAssetFavoriteDo / do-ordersuppliesassetfavoritedo-mapping.xml
 *    OrderSuppliesAssetDetailDo   / do-ordersuppliesassetdetaildo-mapping.xml
 *    ConsumableAssetLocationDo    / do-consumableassetlocationdo-mapping.xml
 * </pre>
 *
 * 
 * @author vpetruchok
 * @version 1.0, 2012-03-15
 */
public class AmindOrderSuppliesAssetService extends AmindSiebelCrmService implements OrderSuppliesAssetService {

    private static final Logger logger = LogManager.getLogger(AmindOrderSuppliesAssetService.class);
    private StatelessSessionFactory statelessSessionFactory = null;
    
    public AmindOrderSuppliesAssetService() {
    }

    public StatelessSessionFactory getStatelessSessionFactory() {
        return this.statelessSessionFactory;
    }

    public void setStatelessSessionFactory(StatelessSessionFactory statelessSessionFactory) {
        if (statelessSessionFactory == null) {
            throw new IllegalArgumentException("sessionFactory cannot be null");
        }
        this.statelessSessionFactory = statelessSessionFactory;
    }

    public AssetListResult retrieveDeviceList(AssetListContract contract) {
        logger.debug("[IN] retrieveDeviceList");
        AmindCrmSessionHandle crmSessionHandle = null;
        Session session = null;
        Session chldSession = null;
        Session totalCountSession = null;
    	Session favoritesSession = null;
    	AmindOrderSuppliesAssetDeviceListService assetDeviceListService = null;
        try {
            crmSessionHandle = (AmindCrmSessionHandle) contract.getSessionHandle();
            session = acquireMultipleSession(crmSessionHandle);
            
            if(LangUtil.isNotBlank(contract.getChlNodeId()))
            {
               chldSession = getStatelessSessionFactory().attachSession();
         	}
            totalCountSession = getStatelessSessionFactory().attachSession();
            if(!contract.isFavoriteFlag()) {
            	favoritesSession = getStatelessSessionFactory().attachSession();
            }
            assetDeviceListService = new AmindOrderSuppliesAssetDeviceListService(session, chldSession, crmSessionHandle);
            assetDeviceListService.setTotalCountSession(totalCountSession);
            assetDeviceListService.setFavoritesSession(favoritesSession);
            return assetDeviceListService.retrieveDeviceList(contract);
        } catch (Exception ex) {
            LogUtil.logAmindServiceCallException(LogUtil.stackTraceExecutionPoint(), ex, contract);
            throw new SiebelCrmServiceException("retrieveDeviceList failed", ex);
        } finally {
        	releaseAmindCrmMultipleSessionHandle(crmSessionHandle, session);
        	AmindServiceUtil.releaseSession(chldSession);
        	AmindServiceUtil.releaseSession(totalCountSession);
        	AmindServiceUtil.releaseSession(favoritesSession);
        	if(assetDeviceListService!=null) {
        		assetDeviceListService.shutDownExecutor();
            }
            logger.debug("[OUT] retrieveDeviceList");
        }
    }

    public AssetResult retrieveDeviceDetail(AssetContract contract) {
        logger.debug("[IN] retrieveDeviceDetail");
        Session session = null; 
        Session sessionConsumableDD = null;
        AmindOrderSuppliesAssetDetailService service = null;
        try {
            session = this.statelessSessionFactory.attachSession();
            sessionConsumableDD = this.statelessSessionFactory.attachSession();
            service = new AmindOrderSuppliesAssetDetailService(session, sessionConsumableDD);
            return service.retrieveDeviceDetail(contract);
        } catch (Exception e) {
            LogUtil.logAmindServiceCallException(LogUtil.stackTraceExecutionPoint(), e, contract);
            throw new SiebelCrmServiceException("retrieveDeviceDetail failed", e);
        } finally {
        	AmindServiceUtil.releaseSession(session);
        	AmindServiceUtil.releaseSession(sessionConsumableDD);
            if(service!=null) {
            	service.shutDownExecutor();
            }
            logger.debug("[OUT] retrieveDeviceDetail");
        }
    }
    
    /**
     * @see com.lexmark.service.impl.real.AmindContractedDeviceService#retrieveAssetLocations(LocationReportingHierarchyContract)
     */
    public AssetLocationsResult retrieveAssetLocations(LocationReportingHierarchyContract contract) {
        Session session = null;
        Session chldSession = null;
        try {
            session = this.statelessSessionFactory.attachSession();
            if(LangUtil.isNotBlank(contract.getChlNodeId()))
            {
            	chldSession = getStatelessSessionFactory().attachSession();
            }
            return new AmindOrderSuppliesAssetLocationService(session, chldSession).retrieveAssetLocations(contract);
        } catch (Exception e) {
            LogUtil.logAmindServiceCallException(LogUtil.stackTraceExecutionPoint(), e, contract);
            throw new SiebelCrmServiceException("retrieveAssetLocations failed", e);
        } finally {
        	AmindServiceUtil.releaseSession(session);
            AmindServiceUtil.releaseSession(chldSession);
        }
    }

	@Override
	public FavoriteResult updateUserFavoriteAsset(
			UserFavoriteAssetContract contract) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
    public AssetListResult retrieveAllDeviceList(AssetListContract contract) {
        AmindCrmSessionHandle crmSessionHandle = null; 
        Session session = null;
    	Session totalCountSession = null;
    	Session favoritesSession = null;
    	Session chldSession= null;
        try {
        	long t = System.currentTimeMillis();
            crmSessionHandle = (AmindCrmSessionHandle) contract.getSessionHandle();
            session = acquireMultipleSession(crmSessionHandle);
            totalCountSession = getStatelessSessionFactory().attachSession();
            if(!contract.isFavoriteFlag()) {
            	favoritesSession = getStatelessSessionFactory().attachSession();
            }
            if(LangUtil.isNotBlank(contract.getChlNodeId()))
            {
         	   chldSession = getStatelessSessionFactory().attachSession();
         	  // assetListService.
            }
            AssetListService assetListService = new AssetListService(session, chldSession, crmSessionHandle,contract);
            assetListService.setTotalCountSession(totalCountSession);
            assetListService.setFavoritesSession(favoritesSession);
            
            AssetListResult result = assetListService.retrieveAllDeviceList();
            logger.debug("[OUT] Exec time AMIND+SIEBEL.. "+ + (System.currentTimeMillis() - t)
     				/ 1000.0);
            return result;
        } catch (Exception ex) {
            LogUtil.logAmindServiceCallException(LogUtil.stackTraceExecutionPoint(), ex, contract);
            throw new SiebelCrmServiceException("retrieveAllDeviceList failed", ex);
        } finally {
        	releaseAmindCrmMultipleSessionHandle(crmSessionHandle, session);
        	AmindServiceUtil.releaseSession(totalCountSession);
        	AmindServiceUtil.releaseSession(favoritesSession);
        	AmindServiceUtil.releaseSession(chldSession);
         }
    }
    
    public AssetListResult retrieveConsumableAssetList (AssetListContract contract) {
        AmindCrmSessionHandle crmSessionHandle = null; 
        Session session = null;
        try {
            crmSessionHandle = (AmindCrmSessionHandle) contract.getSessionHandle();
            session = acquireMultipleSession(crmSessionHandle);
            return new ConsumableAssetListService(session, crmSessionHandle,contract).retrieveConsumableAssetList();
        } catch (Exception ex) {
            LogUtil.logAmindServiceCallException(LogUtil.stackTraceExecutionPoint(), ex, contract);
            throw new SiebelCrmServiceException("retrieveConsumableAssetList failed", ex);
        } finally {
            releaseAmindCrmMultipleSessionHandle(crmSessionHandle, session);
        }
    }
    
    public AssetResult retrieveProducts(AssetContract contract) {
    	logger.debug("[IN] retrieveProducts");
        Session session = null; 
        try {
            session = this.statelessSessionFactory.attachSession();
           //TODO: query serialNumber and populate product no under Result
            AssetResult assetResult = new AssetResult();
            IDataManager dataManager = session.getDataManager();
            String searchExpression = String.format("[serialNumber]='%s'", contract.getSerialNumber());
            List<ProductSerialNumberDetailDo> list = dataManager.queryBySearchExpression(ProductSerialNumberDetailDo.class, searchExpression);
            List<Asset> assetList = new ArrayList<Asset>();
            for(ProductSerialNumberDetailDo  productSerialDetailDoList: list)
	        {
	            Asset asset = new Asset();
				asset.setModelNumber(productSerialDetailDoList.getproductNo());
				asset.setProductNo(productSerialDetailDoList.getproductNo());
				asset.setSerialNumber(productSerialDetailDoList.getserialNumber());
				assetList.add(asset);
			}
            assetResult.setAssetlist(assetList);
            return assetResult;
            
           } catch (Exception e) {
            LogUtil.logAmindServiceCallException(LogUtil.stackTraceExecutionPoint(), e, contract);
            throw new SiebelCrmServiceException("retrieveDeviceDetail failed", e);
        } finally {
        	AmindServiceUtil.releaseSession(session);
            logger.debug("[OUT] retrieveProducts");
        }
    }
    
    @Override
    public PageCountsResult retrievePageCounts(PageCountsContract contract) {
    	 logger.debug("[IN] retrievePageCounts");
         Session session = null; 
         try {
             session = this.statelessSessionFactory.attachSession();
             
             PageCountsResult result = new PageCountsResult();
             
             PageCountsService service = new PageCountsService(contract);
             service.checkRequiredFields();
             service.buildSearchExpression();
             service.setSession(session);
             result.setPageCounts(service.queryAndGetPageCounts());
             
             return result;
             
         } catch (Exception e) {
             LogUtil.logAmindServiceCallException(LogUtil.stackTraceExecutionPoint(), e, contract);
             throw new SiebelCrmServiceException("retrievePageCounts failed", e);
         } finally {
        	 AmindServiceUtil.releaseSession(session);
             logger.debug("[OUT] retrievePageCounts");
         }
    }
    

}
