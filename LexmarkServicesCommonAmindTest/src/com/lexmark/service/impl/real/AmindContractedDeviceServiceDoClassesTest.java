package com.lexmark.service.impl.real;

import static java.lang.String.format;

import java.util.Collection;
import java.util.List;

import org.junit.Test;

import com.amind.common.service.SpringDomainObjMappingService;
import com.amind.data.service.ActionEvent;
import com.amind.data.service.DomainObjBrokerDao;
import com.amind.data.service.QueryObject;
import com.amind.repository.domain.SiebelICFieldDefinition;
import com.lexmark.service.impl.real.deviceService.AmindContractedDeviceServiceUtil;
import com.lexmark.service.impl.real.domain.AccountAsset;
import com.lexmark.service.impl.real.domain.AccountAssetDetailDo;
import com.lexmark.service.impl.real.domain.AccountAssetFavorites;
import com.lexmark.service.impl.real.domain.OrderSuppliesAssetDo;
import com.siebel.data.SiebelPropertySet;

/**
 *
 * @see com.lexmark.service.impl.real.AmindContractedDeviceService
 * @see com.lexmark.service.impl.real.AmindContractedDeviceServiceTest
 * @see com.lexmark.service.impl.real.AmindContractedDeviceServiceTestSuite
 * 
 * @author vpetruchok
 * @version 1.0, 2012-03-05
 */
public class AmindContractedDeviceServiceDoClassesTest  extends AmindSiebelQueryTest {
    
    @Test
    public void queryAccountAssetDetailDo() {
        List<?> result = sampleSiebelQuery(AccountAssetDetailDo.class, 2, 1);
    }
    
    @Test
    public void queryAccountAsset() throws Exception {
        List<?> result = sampleSiebelQuery(AccountAsset.class, 2, 1);
    }
    
    @Test
    public void queryAccountAssetFavorites() throws Exception {
        List<?> result = sampleSiebelQuery(AccountAssetFavorites.class, 2, 1);
    }
    
    @Test
    public void queryBySearchExpressions() throws Exception {
        List<AccountAsset> favList = dataManager.queryBySearchExpression(AccountAsset.class,
                "[id] = '1-4IRYI'");
        if (favList == null) {
            System.out.println("no data");
        
        } else {
            for (AccountAsset  asset : favList) {
                System.out.println("=============================");
                MiscTest.showStructure(asset);
            }
        }
    }
    
    @Test
    public void queryBySearchExpressions2() throws Exception {
        List<AccountAsset> list = dataManager.queryBySearchExpression(AccountAsset.class,
//                "EXISTS ([Owner Account Id] = '1-52Y7PN' AND [LXK SW Agreement Account MDM Level] ='Siebel') AND ([Operating Status]= 'New' OR [Operating Status]= 'Created' OR [Operating Status] = 'Active')");
//                "[LXK SW Contracted Asset - Service Web.Owner Account Id] = '1-52Y7PN' AND [LXK SW Contracted Asset - Service Web.LXK MPS Consumable Flag] = 'Y'");
//                "[LXK SW Contracted Asset - Service Web.Owner Account Id] = '1-52Y7PN' ");
        "[LXK SW Contracted Asset - Service Web.Owner Account Id] = '1-52Y7PN' AND [LXK SW Contracted Asset - Service Web.LXK MPS Consumable Flag]='Y'");
//        "EXISTS ([LXK SW Contracted Asset - Service Web.LXK SW Agreement Account Id] = '1-52Y7PN' AND [LXK SW Contracted Asset - Service Web.LXK SW Agreement Account MDM Level] ='Siebel')");
        
        print(list);
    }
    
    @Test
    public void queryBySearchExpressions_consumableAssetFlag() throws Exception {
        List<AccountAsset> list = dataManager.queryBySearchExpression(AccountAsset.class,
                // from Kalidas Gurusamy 
                "[LXK SW Contracted Asset - Service Web.Owner Account Id] = '1-52Y7PN' AND [LXK SW Contracted Asset - Service Web.LXK MPS Consumable Flag]='Y'");
        print(list);
    }
    
    @Test
    public void queryBySearchExpressions_consumableAssetFlag2() throws Exception {
        String query = "[LXK SW Contracted Asset - Service Web.LXK MPS Consumable Flag]='Y'";
        QueryObject queryReq = new QueryObject(AccountAsset.class, ActionEvent.QUERY);
//        queryReq.setQueryString("[ownerAccountId]='1-4GVCA'");
        queryReq.setQueryString(query);
        queryReq.setNumRows(3);
//        queryReq.setVisibilityMode();
        List<?> list = dataManager.query(queryReq);        
        print(list);
    }
    

    @Test
    public void countRecords_consumableAssetFlag() throws Exception {
//        int count = AmindContractedDeviceServiceUtil.countRecords(session, 
//                "[LXK SW Contracted Asset - Service Web.LXK MPS Consumable Flag] <> 'not-existing'" , false);
        
        int count = AmindContractedDeviceServiceUtil.countRecords(session, 
//                "[LXK SW Contracted Asset - Service Web.Owner Account Id] = '1-52Y7PN' AND [LXK SW Contracted Asset - Service Web.LXK MPS Consumable Flag]='Y'", 
                "[Owner Account Id] = '1-52Y7PN' AND [LXK MPS Consumable Flag]='Y'", 
                false);
        
//        int count = AmindContractedDeviceServiceUtil.countRecords(session, 
//                "" ,  // all records
//                false); // = 21282687
        System.out.println("count = " + count); 
    }
    
    @Test
    public void countRecords_consumableAssetFlag2() throws Exception {
//        int count = AmindContractedDeviceServiceUtil.countRecords(session, 
//                "[LXK SW Contracted Asset - Service Web.LXK MPS Consumable Flag] <> 'not-existing'" , false);
        
        String bo = "LXK SW Contracted Asset - Service Web"; 
        String bc = "LXK SW Contracted Asset - Service Web"; 
//        String searchExpr =   "[LXK SW Contracted Asset - Service Web.Owner Account Id] = '1-52Y7PN' AND [LXK SW Contracted Asset - Service Web.LXK MPS Consumable Flag]='Y'";
        String searchExpr =   "[Owner Account Id] = '1-52Y7PN' AND [LXK MPS Consumable Flag]='Y'";
		SiebelPropertySet input = new SiebelPropertySet();
		input.setProperty("Business Object", bo);
		input.setProperty("Business Component", bc);
		input.setProperty("Search Expression", searchExpr);
		SiebelPropertySet output =  session.getSiebelBusServiceProxy().InvokeMethod("LXK Service Web Utilities","GetTotalCount", input);
        System.out.println("output = " + output);
    }
    
    @Test  //OMRPC Request 6 on connection 40000e9 was abandoned after 600250 ms because it timed out. (SBL-JCA-317) 
    public void queryConsumableAssetFlag() throws Exception {
        QueryObject req = new QueryObject(AccountAsset.class, ActionEvent.QUERY);
        req.setNumRows(3);
//        req.setQueryString("[LXK SW Contracted Asset - Service Web.LXK MPS Consumable Flag]='Y'");
        String searchSpec = "[LXK MPS Consumable Flag]='Y' AND  EXISTS ([LXK SW Agreement Account Id] = '1-52Y7PN' AND [LXK SW Agreement Account MDM Level] ='Siebel') AND ([Operating Status]= 'New' OR [Operating Status]= 'Created' OR [Operating Status] = 'Active')";
        req.setQueryString(searchSpec);
        req.addComponentSearchSpec(AccountAsset.class, searchSpec);
        List<AccountAsset> list;
        long t0 = System.currentTimeMillis();
        try {
            list = dataManager.query(req);
        } finally {
            System.out.printf("Excec time=%s sec. \n", (System.currentTimeMillis() - t0) / 1000.0);
        }
        print(list);
    }
    
    @Test
    public void queryBySearchExpressions_fromTestData() throws Exception {
        List<AccountAsset> favList = dataManager.queryBySearchExpression(AccountAsset.class,
                "[assetId] = '1-TY0FYQ'");
//                "[id] = '1-1QRZ7Z'");
        if (favList == null) {
            System.out.println("no data");
        
        } else {
            for (AccountAsset  asset : favList) {
                System.out.println("=============================");
                MiscTest.showStructure(asset);
            }
        }
    }
    
    
    @Test
    public void queryBySearchExpressions_noData() throws Exception {
        List<AccountAsset> favList = dataManager.queryBySearchExpression(AccountAsset.class,
                "[id] = '1-4IRYI-no-data'");
        if (favList == null) {
            System.out.println("no data");
        
        } else {
            for (AccountAsset  asset : favList) {
                System.out.println("=============================");
                MiscTest.showStructure(asset);
            }
        }
    }
    
    @Test
    public void objectNames_AccountAsset() {
        DomainObjBrokerDao.setDomainObjMappingService(new SpringDomainObjMappingService(
                "TestDoMappings\\do-mappings.xml"));

        String bo = "LXK SW Contracted Asset - Service Web";
        String bc = "LXK SW Contracted Asset - Service Web";
        String searchSpec = format(
                "[name] like '*' " +
                        " AND [grandParentName] = '%s'" +
                        " AND [parentName] = '%s' " +
                        " AND [repositoryName] = 'Siebel Repository'" +
                        " AND ([inactive] = 'N' " +
                        "       OR [inactive] IS NULL)", bo, bc);

        QueryObject queryReq = new QueryObject(SiebelICFieldDefinition.class, ActionEvent.QUERY);
        queryReq.setQueryString(searchSpec);
        //        queryReq.setSortString("type(ASCENDING)"); // seems not working
        //        queryReq.setSortString("type(DESCENDING)");
        //        queryReq.setSortString("name");
        //        queryReq.setSortString("name(DESCENDING)");

        long t0 = System.currentTimeMillis();
        try {
            List<?> r = dataManager.query(queryReq);
            //        List<?> r = dataManager.queryBySearchExpression(SiebelICFieldDefinition.class, searchSpec);
            System.out.println("********");
            for (Object obj : r) {
                //            System.out.println(str(obj));
                SiebelICFieldDefinition f = (SiebelICFieldDefinition) obj;
                System.out.printf("id=%s, name=%s, type=%s\n", f.getId(), f.getName(), f.getType());
            }
        } finally {
            System.out.printf("Excec time=%s sec. \n", (System.currentTimeMillis() - t0) / 1000.0);
        }
    } 
    
    private static void print(Collection<?> list) {
        if (list == null) {
            System.out.println("no data");
        
        } else {
            int i = 1;
            for (Object  obj : list) {
                System.out.printf("[%s] =============================\n", i++);
                System.out.println(str(obj));
//                MiscTest.showStructure(asset);
            }
        }
    }
}
