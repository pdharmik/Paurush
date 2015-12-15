package com.lexmark.service.impl.real;

import static com.lexmark.service.impl.real.MiscTest.print;
import static com.lexmark.service.impl.real.MiscTest.printIcFieldDefinitions;
import java.util.Collection;
import java.util.List;

import org.junit.Test;

import com.amind.data.service.ActionEvent;
import com.amind.data.service.ExecutionMode;
import com.amind.data.service.QueryObject;
import com.lexmark.service.impl.real.domain.OrderSuppliesAssetDetailDo;
import com.lexmark.service.impl.real.domain.OrderSuppliesAssetDo;
import com.lexmark.service.impl.real.domain.OrderSuppliesAssetFavoriteDo;
import com.lexmark.util.LangUtil;

/**
 * @see com.lexmark.service.impl.real.AmindOrderSuppliesAssetServiceTestSuite
 * 
 * @author vpetruchok
 * @version 1.0, 2012-04-19
 */
public class AmindOrderSuppliesAssetServiceDoClassesTest extends AmindSiebelQueryTest {

    @Test
    public void amindLab() {
        Class<?> doClass = OrderSuppliesAssetDo.class;
        QueryObject queryReq = new QueryObject(doClass, ActionEvent.QUERY);
        queryReq.setExecutionMode(ExecutionMode.BiDirectional);
        queryReq.setNumRows(2);
        queryReq.setStartRowIndex(1);
        List<? extends OrderSuppliesAssetDo> result = dataManager.query(queryReq);
        logger.info("result.size()=" + result.size());
        for (OrderSuppliesAssetDo obj : result) {
            logger.info("" + obj);
        }
    }

    @Test
    public void queryOrderSuppliesAssetDo() {
        List<?> result = sampleSiebelQuery(OrderSuppliesAssetDo.class, 2, 1);
    }
    
//    @Test // performance issue
    public void queryOrderSuppliesAssetDo_withSort() {
//        List<?> result = sampleSiebelQuery(OrderSuppliesAssetDo.class, 2, 1);
        QueryObject query = new QueryObject(OrderSuppliesAssetDo.class, ActionEvent.QUERY);
        query.setExecutionMode(ExecutionMode.BiDirectional);
        query.setNumRows(10);
        query.setStartRowIndex(1);
        query.setSortString("IP Address(ASCENDING)");   // performance bottleneck
//        query.setQueryString("[ownerAccountId]='1-4GVCA'");
        query.setQueryString("[ipAddress] <> '' AND [ipAddress] <> '-'");
        List<OrderSuppliesAssetDo> lst = dataManager.query(query); 
        for (OrderSuppliesAssetDo a: lst) {
            System.out.println("ipAddress=" + a.getIpAddress());
        }
        
    }

    @Test
    public void queryOrderSuppliesAssetDo2() {
        {
            List<?> r =
                    dataManager.queryBySearchExpression(OrderSuppliesAssetDo.class,
                            // "[ownerAccountId]='1-4GVCA'");
                            "[LXK MPS SW Consumable Asset List.Owner Account Id]='1-4GVCA'");

            // Owner Account Id
            System.out.println("********");
            System.out.println(str(r));
        }

        {
            QueryObject queryReq = new QueryObject(OrderSuppliesAssetDo.class, ActionEvent.QUERY);
            queryReq.setQueryString("[ownerAccountId]='1-4GVCA'");

            List<?> r = dataManager.query(queryReq);
            print(r);
//            System.out.println("********");
//            System.out.println(str(r));
        }

    }

    @Test
    public void queryOrderSuppliesAssetDo_province() {
        {
            List<?> r =
                    dataManager.queryBySearchExpression(OrderSuppliesAssetDo.class,
                            "[ownerAccountId] = '1-52Y7PN' AND ([installProvince] ~LIKE '*1*')"); 

            System.out.println("********");
            System.out.println(str(r));
        }
        {
            List<?> r =
                    dataManager.queryBySearchExpression(OrderSuppliesAssetDo.class,
                            "[ownerAccountId] = '1-52Y7PN' AND ([installProvince] ~LIKE '**')"); 

            System.out.println("********");
            System.out.println(str(r));
        }
    }
    
    

    @Test
    public void queryOrderSuppliesAssetFavoriteDo() {
        List<?> result = sampleSiebelQuery(OrderSuppliesAssetFavoriteDo.class, 2, 0);
    }

    @Test
    public void queryOrderSuppliesAssetDetailDo() {
        List<?> result = sampleSiebelQuery(OrderSuppliesAssetDetailDo.class, 2, 0);
    }
    
    @Test
    public void queryOrderSuppliesAssetDetailDo_bySearchExpressions() {
        {
           List<OrderSuppliesAssetDo> list = 
                dataManager.queryBySearchExpression(OrderSuppliesAssetDetailDo.class, "[assetId] = '1-BPRKV6'");
           println(list);
        }
        {
          List<OrderSuppliesAssetDo> list = 
                dataManager.queryBySearchExpression(OrderSuppliesAssetDetailDo.class, "[defaultSpecialInstruction] = '1-BPRKV6'");
           println(list);
        }
        {
          List<OrderSuppliesAssetDo> list = 
                dataManager.queryBySearchExpression(OrderSuppliesAssetDetailDo.class, "[assetId] = '1-GKTUZK'");
           println("[pageCounts, assetId='1-GKTUZK'] ", list);
        }
    }
    
    @Test
    public void queryOrderSuppliesAssetDetailDo_bySearchExpressions_QA() {
        {
           List<OrderSuppliesAssetDetailDo> list = 
                dataManager.queryBySearchExpression(OrderSuppliesAssetDetailDo.class, "[assetId] = '1-418G82S' AND [Asset Mgmt - Asset.LXK MPS Consumable Flag] = 'Y'");
//           println(list);
           for (OrderSuppliesAssetDetailDo assetDetail : list) {
               System.out.println("******************* parts");
               println(assetDetail.getParts());
           }
        }
    }
    

    private void println(Collection<?> coll) {
        println("", coll);
    }
    
    private void println(String ns, Collection<?> coll) {
        if (LangUtil.isEmpty(coll)) {
            System.out.println(ns + "<collection> is empty");
            return;
        } 
        
        for (Object obj : coll) {
            System.out.println(ns + str(obj));
        }
        
        System.out.println(ns + "coll.size=" + coll.size());
    }

    @Test
    public void queryBySearchExpressions_byContactId() {
        String contactId = "1-1SU00J3";
        List<OrderSuppliesAssetDo> list = dataManager.queryBySearchExpression(OrderSuppliesAssetDo.class, "[contactId] = '"
                + contactId + "' AND [assetFavFlag] = 'Y'");
    }

    @Test
    public void queryBySearchExpressions_byAgreementId() {
        String contactId = "1-1SU00J3";
        List<OrderSuppliesAssetDo> list = dataManager.queryBySearchExpression(OrderSuppliesAssetDo.class,
                // "[Owner Account Id] = '1-4IO9X' AND [Agreementment Id] = '1' AND ([Operating Status]= 'New' OR [Operating Status]= 'Created' OR [Operating Status] = 'Active')");
                "[operationalStatus] = 'notExistingStatus'");
        System.out.println("result = " + list);
    }

    @Test
    public void queryOrderSuppliesAssetDo_byAgreementId() {
        QueryObject query = new QueryObject(OrderSuppliesAssetDo.class, ActionEvent.QUERY);
        query.setExecutionMode(ExecutionMode.BiDirectional);
        query.setNumRows(1);
        query.setStartRowIndex(10);
        String searchSpec = "[Agreementment Id] = '-1'";
        query.addComponentSearchSpec(OrderSuppliesAssetDo.class, searchSpec);
        List<OrderSuppliesAssetDo> list = dataManager.query(query);
    }

    @Test
    public void queryOrderSuppliesAssetFavoriteDo_byAgreementId() {
        QueryObject query = new QueryObject(OrderSuppliesAssetDo.class, ActionEvent.QUERY);
        query.setExecutionMode(ExecutionMode.BiDirectional);
        query.setNumRows(1);
        query.setStartRowIndex(10);
        String searchSpec = "[Agreementment Id] = '-1'";
        query.addComponentSearchSpec(OrderSuppliesAssetDo.class, searchSpec);
        List<OrderSuppliesAssetDo> list = dataManager.query(query);
    }

    @Test
    public void queryOrderSuppliesAssetDo_byContactId() {
        QueryObject query = new QueryObject(OrderSuppliesAssetDo.class, ActionEvent.QUERY);
        query.setExecutionMode(ExecutionMode.BiDirectional);
        query.setNumRows(1);
        query.setStartRowIndex(10);
        String searchSpec = "[Contact Id] = '-1'";
//        query.addComponentSearchSpec(OrderSuppliesAssetDo.class, searchSpec);
        List<OrderSuppliesAssetDo> list = dataManager.query(query);
        System.out.println("result=" + list);
    }

    @Test
    public void queryConsumableAssetLocationDo() {
//        List<?> result = sampleSiebelQuery(ConsumableAssetLocationDo.class, 2, 1);
//        assertTrue(result.size() > 0);

        /*
         * { List<ConsumableAssetLocationDo> searchResult =
         * dataManager.queryBySearchExpression(ConsumableAssetLocationDo.class,
         * "[LXK SW Asset Account Global DUNS Number] IS NOT NULL");
         * System.out.println("searchResult=" + searchResult); }
         */
    }
    
    @Test
    public void objectNames_OrderSuppliesAssetDo() {
        String bo = "LXK MPS SW Consumable Asset List";
        String bc = "LXK MPS SW Consumable Asset List";
        printIcFieldDefinitions(dataManager, bo, bc);
    }
    
    
    @Test
    public void queryOrderSuppliesAssetDetailDo_withMiddleName() throws Exception {
        List<?> lst = MiscTest.querySiebel(OrderSuppliesAssetDetailDo.class, "[contactMiddleName] <> ''", 10, 1);
        MiscTest.print(lst);
    }

}
