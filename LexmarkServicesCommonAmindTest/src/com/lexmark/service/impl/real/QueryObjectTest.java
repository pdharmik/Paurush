package com.lexmark.service.impl.real;

import static java.lang.String.format;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import com.amind.common.service.SpringDomainObjMappingService;
import com.amind.data.service.ActionEvent;
import com.amind.data.service.DomainObjBrokerDao;
import com.amind.data.service.QueryObject;
import com.amind.repository.domain.SiebelICFieldDefinition;
import com.lexmark.service.impl.real.domain.AccountAsset;
import com.lexmark.service.impl.real.domain.OrderSuppliesAssetDetailDo;
import com.lexmark.service.impl.real.domain.OrderSuppliesAssetDo;
import com.lexmark.service.impl.real.domain.RequestTypeActivitiesDo;
import com.lexmark.service.impl.real.domain.RequestTypeDo;

/**
 * @author vpetruchok
 * @version 1.0, 2012-05-17
 */
public class QueryObjectTest extends AmindSiebelQueryTest {

    /*
     * OrderSuppliesAssetDo.class, dataSourceName=EAI Siebel Adapter
     */
    @Test
    public void testQueryBySearchExpression() {
        {
            List<?> r = dataManager.queryBySearchExpression(OrderSuppliesAssetDo.class,
                    "[ownerAccountId]='1-4GVCA'");
            System.out.println("********");
            System.out.println(str(r));

            // The code above is equivalent  to
            QueryObject queryReq = new QueryObject(OrderSuppliesAssetDo.class, ActionEvent.QUERY);
            queryReq.setQueryString("[ownerAccountId]='1-4GVCA'");

            List<?> r2 = dataManager.query(queryReq);
            System.out.println("********");
            System.out.println(str(r2));
        }

        {// with component`s name
            List<?> r = dataManager.queryBySearchExpression(OrderSuppliesAssetDo.class,
                    "[LXK MPS SW Consumable Asset List.Owner Account Id]='1-4GVCA'");

            System.out.println("********");
            System.out.println(str(r));
        }

        try {
            List<?> r = dataManager.queryBySearchExpression(OrderSuppliesAssetDo.class,
                    "[LXK MPS SW Consumable Asset List.LXK MPS SW Consumable Asset List.Owner Account Id]='1-4GVCA'");
            fail();
        } catch (Exception ok) {
        }

        try {
            List<?> r = dataManager.queryBySearchExpression(OrderSuppliesAssetDo.class,
                    "[Owner Account Id]='1-4GVCA'");
            fail(); // because there is no aMind field  `Owner Account Id' in the mapping file
        } catch (Exception ok) {
        }

    }

    /*
     * OrderSuppliesAssetDo.class, dataSourceName=EAI Siebel Adapter - what
     * about other dataSourceName ?
     */
    @Test
    public void testQuery() throws Exception {
        {
            QueryObject queryReq = new QueryObject(OrderSuppliesAssetDo.class, ActionEvent.QUERY);
            queryReq.setQueryString("[ownerAccountId]='1-4GVCA'");

            List<?> r = dataManager.query(queryReq);
            System.out.println("********");
            System.out.println(str(r));
        }

        {
            String searchSpec = "[Owner Account Id]='1-4GVCA'";
            QueryObject queryReq = new QueryObject(OrderSuppliesAssetDo.class, ActionEvent.QUERY);
            queryReq.setQueryString(searchSpec);
            queryReq.addComponentSearchSpec(OrderSuppliesAssetDo.class, searchSpec);

            List<?> r = dataManager.query(queryReq);
            System.out.println("********");
            System.out.println(str(r));
        }

        {
            String searchSpec = "[ownerAccountId]='1-4GVCA'";
            QueryObject queryReq = new QueryObject(OrderSuppliesAssetDo.class, ActionEvent.QUERY);
            queryReq.setQueryString(searchSpec);
            queryReq.addComponentSearchSpec(OrderSuppliesAssetDo.class, searchSpec);

            List<?> r = dataManager.query(queryReq);
            System.out.println("********");
            System.out.println(str(r));
        }

        {
            String searchSpec = "[LXK MPS SW Consumable Asset List.Owner Account Id]='1-4GVCA'";
            QueryObject queryReq = new QueryObject(OrderSuppliesAssetDo.class, ActionEvent.QUERY);
            queryReq.setQueryString(searchSpec);
            //            queryReq.addComponentSearchSpec(OrderSuppliesAssetDo.class, searchSpec);

            List<?> r = dataManager.query(queryReq);
            System.out.println("********");
            System.out.println(str(r));
        }

        try {
            String searchSpec = "[LXK MPS SW Consumable Asset List.Owner Account Id]='1-4GVCA'";
            QueryObject queryReq = new QueryObject(OrderSuppliesAssetDo.class, ActionEvent.QUERY);
            queryReq.setQueryString(searchSpec);
            queryReq.addComponentSearchSpec(OrderSuppliesAssetDo.class, searchSpec);

            List<?> r = dataManager.query(queryReq);
            System.out.println("********");
            System.out.println(str(r));
            fail();
        } catch (Exception ok) {
        }
    }

    @Test
    public void testQueryWithQualifiedNamesAndComponentSearchSpec() throws Exception {
        try {
            String searchSpec = "[LXK MPS SW Consumable Asset List.Owner Account Id]='1-4GVCA'";
            QueryObject queryReq = new QueryObject(OrderSuppliesAssetDo.class, ActionEvent.QUERY);
            queryReq.setQueryString(searchSpec);
            queryReq.addComponentSearchSpec(OrderSuppliesAssetDo.class, searchSpec);

            List<?> r = dataManager.query(queryReq);
            System.out.println("********");
            System.out.println(str(r));
            fail();
        } catch (Exception ok) {
        }
    }

    @Test
    public void testFilter() throws Exception {
        String searchSpec = "[ownerAccountId]='1-4GVCA'"
                //                 ; 
                + " AND [serialNumber] = '7929RNB'";
        QueryObject queryReq = new QueryObject(AccountAsset.class, ActionEvent.QUERY);
        queryReq.setQueryString(searchSpec);
        //            queryReq.addComponentSearchSpec(OrderSuppliesAssetDo.class, searchSpec);

        List<?> r = dataManager.query(queryReq);
        System.out.println("********");
        //        System.out.println(str(r));
        for (Object obj : r) {
            System.out.println(str(obj));
        }
    }

    @Test
    public void objectNames() {
        DomainObjBrokerDao.setDomainObjMappingService(new SpringDomainObjMappingService(
                "TestDoMappings\\do-mappings.xml"));

        String bo = "LXK MPS SW Consumable Asset List";
        String bc = "LXK MPS SW Consumable Asset List";
        String searchSpec = format(
                "[name] like '*' " +
                        " AND [grandParentName] = '%s'" +
                        " AND [parentName] = '%s'" +
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
    
    /**
     * @see do-ordersuppliesassetpagecount-mapping.xml
     */
    @Test
    public void pageCountNames() {
        DomainObjBrokerDao.setDomainObjMappingService(new SpringDomainObjMappingService(
                "TestDoMappings\\do-mappings.xml"));

        String bo = "LXK MPS SW Consumable Asset Detail";
        String bc = "LXK SW FS Asset Measurement Characteristics - Service Web";
        String searchSpec = format(
                "[name] like '*' " +
                        " AND [grandParentName] = '%s'" +
                        " AND [parentName] = '%s'" +
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
    
    /**
     *  
     * @see com.lexmark.service.impl.real.RequestTypeListFilterTest
     * @see "do-requesttype-mapping.xml"
     */
    @Test
    public void testQueryRequestTypeDo() throws Exception {
//        sampleSiebelQuery(RequestTypeDo.class, 5, 1);
        
        String searchSpec = "[contractType] <> ''";
        QueryObject queryReq = new QueryObject(RequestTypeDo.class, ActionEvent.QUERY);
        queryReq.addComponentSearchSpec(RequestTypeDo.class, searchSpec);
        queryReq.setQueryString(searchSpec);

        List<?> r = dataManager.query(queryReq);
        System.out.println("********");
        System.out.println(str(r));
    }
    
//    @Test
//    public void testQueryStringAndComponentSearchSpecMap() throws Exception {
//        Class<?> doClass = ConsumableAssetLocationDo.class; // need Siebel BO
//        String searchSpec = "[id]='1-BTMCU9'";
//        
//        QueryObject qo1 = new QueryObject(doClass, ActionEvent.QUERY);
//        qo1.addComponentSearchSpec(doClass, searchSpec);
//        qo1.setQueryString(searchSpec);
//        List<?> r1 = dataManager.query(qo1);                
//        
//        QueryObject qo2 = new QueryObject(doClass, ActionEvent.QUERY);
//        qo2.addComponentSearchSpec(doClass, searchSpec);
////        qo2.setQueryString(searchSpec);
//        List<?> r2 = dataManager.query(qo2);                
//        
//        System.out.println("r1.size()=" + r1.size());
//        System.out.println("r2.size()=" + r2.size());
//        assertEquals(r1.size(), r2.size()); 
//    }
    
    @Test
    public void testQueryStringAndComponentSearchSpecMap_EAI() throws Exception {
        Class<?> doClass = OrderSuppliesAssetDetailDo.class; // EAI Siebel Adapter
        String searchSpec = "[assetId]='1-GKTUZK'";
        
        QueryObject qo1 = new QueryObject(doClass, ActionEvent.QUERY);
        qo1.addComponentSearchSpec(doClass, searchSpec);
        qo1.setQueryString(searchSpec);
        List<?> r1 = dataManager.query(qo1);                
        
        QueryObject qo2 = new QueryObject(doClass, ActionEvent.QUERY);
        qo2.addComponentSearchSpec(doClass, searchSpec);
//        qo2.setQueryString(searchSpec);
        List<?> r2 = dataManager.query(qo2);                
        
        System.out.println("r1.size()=" + r1.size());
        System.out.println("r2.size()=" + r2.size());
        assertEquals(r1.size(), r2.size());
    }
    
	@Test
    public void testSearchSpec() throws Exception {
        String searchSpec = "[SR Number] = '1-16148528218'";
        List<RequestTypeDo> r1 = (List<RequestTypeDo>)dataManager.queryBySearchExpression(RequestTypeActivitiesDo.class, searchSpec);
        
        Assert.assertNotNull(r1);
    }
	
    @Test
    public void testSearch() throws Exception {
        String searchSpec = "[SR Number] = '1-16148528218'";
        QueryObject queryReq = new QueryObject(RequestTypeActivitiesDo.class, ActionEvent.QUERY);
        queryReq.addComponentSearchSpec(RequestTypeActivitiesDo.class, searchSpec);
        queryReq.setQueryString(searchSpec);
        List<?> r = dataManager.query(queryReq);
        
        Assert.assertNotNull(r);
    }
}
