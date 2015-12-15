package com.lexmark.service.impl.real.deviceService;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

import util.TestSessionFactories;

import com.amind.data.service.IDataManager;
import com.amind.session.Session;
import com.amind.session.StatelessSessionFactory;
import com.lexmark.contract.AssetListContract;
import com.lexmark.service.impl.real.domain.AccountAssetFavorites;

/**
 * 
 * @author vpetruchok
 * @version 1.0, 2012-04-11
 */
public class AmindContractedDeviceServiceUtilTest {

    @Test
    public void testQueryFavoriteList() throws Exception {
        Log logger = LogFactory.getLog(AmindContractedDeviceServiceUtil.class);
        Class<AccountAssetFavorites> doFavoriteClass = AccountAssetFavorites.class;
        AssetListContract contract = new AssetListContract();
        contract.setContactId("No Match Row Id");
        StatelessSessionFactory sessionFactory = TestSessionFactories.newStatelessSessionFactory();
        Session session = null;
        try {
            session = sessionFactory.attachSession();
            IDataManager dataManager = session.getDataManager();
            List<AccountAssetFavorites> favoriteList = AmindContractedDeviceServiceUtil.queryFavoriteList(contract, dataManager, doFavoriteClass, logger);
            System.out.println("**********");
            System.out.println(favoriteList);
        } finally {
            sessionFactory.detachSession(session);
        }
    }

}
