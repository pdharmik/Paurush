package com.lexmark.service.impl.real.orderSuppliesAssetService;


import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.amind.common.service.SiebelBusinessServiceProxy;
import com.amind.data.service.ActionEvent;
import com.amind.data.service.QueryObject;
import com.amind.session.Session;
import com.lexmark.service.impl.real.AmindServiceTest;
import com.lexmark.service.impl.real.domain.OrderSuppliesAssetFavoriteDo;
import com.lexmark.service.impl.real.util.AmindServiceUtil;

/**
 * @see com.lexmark.service.impl.real.AmindOrderSuppliesAssetServiceTestSuite
 * 
 * @author vpetruchok
 * @version 1.0, 2012-06-01
 */
public class AmindOrderSuppliesAssetDeviceListServiceTest extends  AmindServiceTest {

    private static Session session;
    
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        session = statelessSessionFactory.attachSession();
    }
    
    @AfterClass
    public static void tearDownBeforeClass() throws Exception {
        statelessSessionFactory.detachSession(session);
    }
    
    /**
     * The siebel object/component names are from MPS_OrderManagement_Consumables_Assets_Orders_05_07_12.docx
     * 
     * @see com.lexmark.service.impl.real.AmindOrderSuppliesAssetServiceDoClassesTest#queryOrderSuppliesAssetFavoriteDo()
     */
    @Test
    public void testGetTotalCount_consumableAssets_newMappging_since_2012_06_01() throws Exception {
        { // all assets
            SiebelBusinessServiceProxy businessServiceProxy = session.getSiebelBusServiceProxy();
            int count =  AmindServiceUtil.getTotalCount("LXK MPS SW Consumable Asset List",
                                                        "LXK MPS SW Consumable Asset List",
                                                         "[Owner Account Id]='1-4GVCA'", 
                                                         businessServiceProxy);
            System.out.println("LXK MPS SW Consumable Asset List / count = " + count);
        }
        
        { //favorite assets
            SiebelBusinessServiceProxy businessServiceProxy = session.getSiebelBusServiceProxy();
            int count =  AmindServiceUtil.getTotalCount("LXK MPS SW Consumable Asset List",
                                                        "LXK MPS SW Contact Favorite Entitled Assets",
                                                         "[Contact Id]='1-4GVCA'", 
                                                         businessServiceProxy);
            System.out.println("LXK MPS SW Contact Favorite Entitled Assets / count = " + count);
        }
    }
    
    
    @Test
    public void testGetTotalCount_emptySearchExpressions() throws Exception {
        SiebelBusinessServiceProxy businessServiceProxy = session.getSiebelBusServiceProxy();
        
        int count =  AmindServiceUtil.getTotalCount("LXK MPS SW Consumable Asset List",
                                                      "LXK MPS SW Consumable Asset List",
                                                         "", 
                                                           businessServiceProxy);
        System.out.println("LXK MPS SW Consumable Asset List / count = " + count); // = 21282655
         
        int count2 =  AmindServiceUtil.getTotalCount("LXK MPS SW Consumable Asset List",
                                                      "LXK MPS SW Contact Favorite Entitled Assets",
                                                         "", 
                                                         businessServiceProxy);
        System.out.println("LXK MPS SW Contact Favorite Entitled Assets / count = " + count2); // = 23
    }

    @Test
    public void testFavList() throws Exception {
//        String searchSpec = "[Favorite Asset Id]='1-5HSEYK'";
        String searchSpec = "[Contact Id]='1-BGJ7-129'";
        QueryObject queryReq = new QueryObject(OrderSuppliesAssetFavoriteDo.class, ActionEvent.QUERY);
        queryReq.setQueryString(searchSpec);
        queryReq.addComponentSearchSpec(OrderSuppliesAssetFavoriteDo.class, searchSpec);
        List<?> r = session.getDataManager().query(queryReq);
        System.out.println("********");
        for (Object obj : r) {
            System.out.println("result = " +  str(obj));
        }
        System.out.println("result.size()=" + r.size()); // = 19
    }
}
