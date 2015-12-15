package com.lexmark.service.impl.real;

import static com.lexmark.service.impl.real.util.AmindServiceUtil.detachSession;

import com.amind.session.Session;
import com.amind.session.StatelessSessionFactory;
import com.lexmark.contract.GlobalAssetListContract;
import com.lexmark.contract.LocationReportingHierarchyContract;
import com.lexmark.contract.UserFavoriteAssetContract;
import com.lexmark.result.AssetListResult;
import com.lexmark.result.AssetLocationsResult;
import com.lexmark.result.AssetReportingHierarchyResult;
import com.lexmark.result.FavoriteResult;
import com.lexmark.service.api.DeviceService;
import com.lexmark.service.api.SiebelCrmServiceException;
import com.lexmark.service.impl.real.deviceService.AssetLocationService;
import com.lexmark.service.impl.real.deviceService.AssetReportingHierarchyService;
import com.lexmark.service.impl.real.deviceService.FullAssetReportingHierarchyService;
import com.lexmark.service.impl.real.deviceService.GlobalAssetListService;
import com.lexmark.service.impl.real.deviceService.UpdateUserFavoriteAssetService;
import com.lexmark.service.impl.real.util.AmindServiceUtil;
import com.lexmark.service.impl.real.util.LogUtil;


/**
 * See MPS_OrderManagement_ChangeManagement_<...>.docx .
 *  
 * <pre>
 * Siebel  do-classes:
 *   AccountAsset          / do-contractedasset-mapping.xml
 *   AccountAssetFavorites / do-assetFavorites-mapping.xml
 *   AccountAssetDetailDo  / do-accountassetdetaildo-mapping.xml
 * </pre>
 * 
 * 
 * @see com.lexmark.domain.Asset
 */
public class AmindContractedDeviceService extends AmindSiebelCrmService implements DeviceService {

    private StatelessSessionFactory statelessSessionFactory;

    AmindContractedDeviceService() {
    }

    public StatelessSessionFactory getSessionFactory() {
        if (statelessSessionFactory == null) {
            throw new SiebelCrmServiceException("SessionFactory not initialized");
        }
        return statelessSessionFactory;
    }

    public void setSessionFactory(StatelessSessionFactory sessionFactory) {
        this.statelessSessionFactory = sessionFactory;
    }

    @Override
    public AssetLocationsResult retrieveAssetLocations(LocationReportingHierarchyContract contract) {
        Session session = null;  
        try {
            session = getSessionFactory().attachSession();
            return new AssetLocationService(session).retrieveAssetLocations(contract);
        } catch (Exception e) {
            LogUtil.logAmindServiceCallException(LogUtil.stackTraceExecutionPoint(), e, contract);
            throw new SiebelCrmServiceException("retrieveAssetLocations failed", e);
        } finally {
        	AmindServiceUtil.releaseSession(session);
        }
    }

    /**
     * VL 09/28/2010: Initial implementation.
     *
     */
    @Override
    public AssetReportingHierarchyResult retrieveAssetReportingHierarchy(LocationReportingHierarchyContract contract) {
        Session session = null;
        try {
            session = getSessionFactory().attachSession();
            return new AssetReportingHierarchyService(session).retrieveAssetReportingHierarchy(contract);
        } catch (Exception e) {
            LogUtil.logAmindServiceCallException(LogUtil.stackTraceExecutionPoint(), e, contract);
            throw new SiebelCrmServiceException("retrieveAssetReportingHierarchy failed", e);
        } finally {
        	AmindServiceUtil.releaseSession(session);
        }
    }

    @Override
    public FavoriteResult updateUserFavoriteAsset(UserFavoriteAssetContract contract) {
        Session session = null; 
        try {
            session = getSessionFactory().attachSession();
            return new UpdateUserFavoriteAssetService(session).updateUserFavoriteAsset(contract);
        } catch (Exception e) {
            LogUtil.logAmindServiceCallException(LogUtil.stackTraceExecutionPoint(), e, contract);
            throw new SiebelCrmServiceException("updateUserFavoriteAsset failed", e);
        } finally {
        	AmindServiceUtil.releaseSession(session);
        }
    }


    @Override
    public AssetListResult retrieveGlobalAssetList(GlobalAssetListContract contract) {
        Session session = null;
        try {
            session = getSessionFactory().attachSession();
            return new GlobalAssetListService(session).retrieveGlobalAssetList(contract);
        } catch (Exception e) {
            LogUtil.logAmindServiceCallException(LogUtil.stackTraceExecutionPoint(), e, contract);
            throw new SiebelCrmServiceException("retrieveGlobalAssetList failed", e);
        } finally {
        	AmindServiceUtil.releaseSession(session);
        }
    }

    /**
     * VL 09/28/2010: Initial implementation.
     *
     */
    @Override
    public AssetReportingHierarchyResult retrieveFullAssetReportingHierarchy(LocationReportingHierarchyContract contract) {
        Session session = null; 
        try {
            session = getSessionFactory().attachSession();
            return new FullAssetReportingHierarchyService(session).retrieveFullAssetReportingHierarchy(contract);
        } catch (Exception e) {
            LogUtil.logAmindServiceCallException(LogUtil.stackTraceExecutionPoint(), e, contract);
            throw new SiebelCrmServiceException("retrieveAssetReportingHierarchy failed", e);
        } finally {
        	AmindServiceUtil.releaseSession(session);
        }
    }

}
