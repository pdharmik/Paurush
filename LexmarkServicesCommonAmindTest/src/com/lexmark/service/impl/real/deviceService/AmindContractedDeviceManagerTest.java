package com.lexmark.service.impl.real.deviceService;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import util.TestSessionFactories;

import com.amind.session.Session;
import com.amind.session.StatelessSessionFactory;
import com.lexmark.constants.LexmarkConstants;
import com.lexmark.contract.AssetContract;
import com.lexmark.contract.AssetListContract;
import com.lexmark.result.AssetListResult;
import com.lexmark.result.AssetResult;
import com.lexmark.service.impl.real.AmindCrmSessionHandle;
import com.lexmark.service.impl.real.MiscTest;
import com.lexmark.util.LangUtil;

/**
 * @author vpetruchok
 * @version 1.0, 2012-05-14
 */
public class AmindContractedDeviceManagerTest {

    private static AmindCrmSessionHandle crmSessionHandle;
    private static StatelessSessionFactory statelessSessionFactory;
    private Session session;

    @BeforeClass
    public static void createAmindContractedDeviceManager() throws Exception {
        statelessSessionFactory = TestSessionFactories.newStatelessSessionFactory();
        crmSessionHandle = new AmindCrmSessionHandle();
    }

    @Before
    public void setUp() throws Exception {
    }

//    @Test
//    public void testRetrieveDeviceDetail_withData() throws InterruptedException {
//        try {
//            session = statelessSessionFactory.attachSession();
//
//            String ns = "[retrieveDeviceDetail] ";
//            String assetId = "1-2YANT7";
//            AssetContract contract = new AssetContract();
//            contract.setAssetId(assetId);
//
//            AssetResult r = new DeviceDetailService(session).retrieveDeviceDetail(contract);
//
//            System.out.println(ns + "result=" + r);
//            System.out.println(ns + "result.getAsset()=" + r.getAsset());
//            assertTrue(r.getAsset() != null);
//            assertEquals(assetId, r.getAsset().getAssetId());
//            MiscTest.showStructure(r.getAsset());
//        } finally {
//            statelessSessionFactory.detachSession(session);
//        }
//    }

    private void logAssetList(String ns, AssetListResult result) {
        System.out.println(ns + "[IN] logAssetList");
        List<com.lexmark.domain.Asset> assetList = result.getAssets();
        System.out.println(ns + "result=" + result);
        System.out.println(ns + "result.getAssets()=" + result.getAssets());
        System.out.println(ns + "result.getTotalCount()=" + result.getTotalCount());

        int i = 0;
        if (LangUtil.isNotEmpty(assetList)) {
            for (com.lexmark.domain.Asset asset : assetList) {
                System.out.printf("%d:\n", ++i);
                System.out.println(ns + "Asset ID: " + asset.getAssetId());
                System.out.println(ns + "Mac Address: " + asset.getMacAddress());
                System.out.println(ns + "Favorite: " + asset.getUserFavoriteFlag());
                System.out.println(ns + "Machine Model: " + asset.getModelNumber());
                System.out.println(ns + "Asset tag: " + asset.getAssetTag());
                System.out.println(ns + "SerialNumber: " + asset.getSerialNumber());
            }
        }
    }

}
