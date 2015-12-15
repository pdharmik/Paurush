package com.lexmark.service.impl.real.domain;

import static com.lexmark.service.impl.real.util.DoFileUtil.checkDoFile;

import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.MethodRule;

import com.amind.data.service.ActionEvent;
import com.amind.data.service.QueryObject;
import com.lexmark.service.impl.real.JUnitUtil;
import com.lexmark.service.impl.real.MiscTest;
import com.lexmark.service.impl.real.SortUtil;
import com.lexmark.service.impl.real.SortUtil.SortCriteria;


/**
 * @author vpetruchok
 * @version 1.0, 2012--07-30
 */
public class RequestTypeDoTest {
    
    @Rule
    public MethodRule methodTimeRule = new JUnitUtil.TestExecutionInfoRule();
    
    @Test
    public void testCheckFields() throws Exception {
        checkDoFile("../LexmarkServicesCommon/src/DoMappings/do-requesttype-mapping.xml");
    }
    
    @Test
    public void queryRequestTypeDoTest() throws Exception {
        MiscTest.sampleSiebelQuery(RequestTypeDo.class, 5);
    }
    
    @Test
    public void queryRequestTypeDoTest_ContactIdIsNotNull() throws Exception {
        List<RequestTypeDo> result = MiscTest.sampleSiebelQuery(RequestTypeDo.class, "[contactId] <> ''", 5);
        for (RequestTypeDo rtDo : result) {
            System.out.printf("id=%s, contactId=%s, requestNumber=%s, updatedSrNumber=%s\n", rtDo.getId(), rtDo.getContactId(), rtDo.getRequestNumber(), rtDo.getUpdatedSrNumber());
        }
//        MiscTest.sampleSiebelQuery(RequestTypeDo.class, "[contactId] is not null", 5);
    }    
    
    @Test
    public void queryRequestTypeDoTest_UpdatedSrNumberIsNotNull() throws Exception {
        List<RequestTypeDo> result = MiscTest.sampleSiebelQuery(RequestTypeDo.class, "[updatedSrNumber] <> '' AND [requestNumber] <> ''", 5);
//        List<RequestTypeDo> result = (List<RequestTypeDo>) MiscTest.sampleSiebelQuery(RequestTypeDo.class, "[requestNumber] = '1-1484085001'", 5);
        for (RequestTypeDo rtDo : result) {
            System.out.printf("requestNumber=%s, updatedSrNumber=%s\n",  rtDo.getRequestNumber(), rtDo.getUpdatedSrNumber());
        }
//        MiscTest.sampleSiebelQuery(RequestTypeDo.class, "[contactId] is not null", 5);
    }        
    
    @Test
    public void queryRequestTypeDoTest_QA() throws Exception {
//        List<RequestTypeDo> result = (List<RequestTypeDo>) MiscTest.sampleSiebelQuery(RequestTypeDo.class, "[updatedSrNumber] <> ''", 5);
//        List<RequestTypeDo> result = (List<RequestTypeDo>) MiscTest.sampleSiebelQuery(RequestTypeDo.class, "[requestNumber] = '1-9077385291'", 5);
//        List<RequestTypeDo> result = (List<RequestTypeDo>) MiscTest.sampleSiebelQuery(RequestTypeDo.class, "[LXK C Service Request (EAI).SR Number] = '1-9077385291'", 5);
//        List<RequestTypeDo> result = (List<RequestTypeDo>) MiscTest.sampleSiebelQuery(RequestTypeDo.class, "[SR Number] = '1-9077385291'", 5);
        List<RequestTypeDo> result = MiscTest.sampleSiebelQuery(RequestTypeDo.class, "[LXK MPS Updated SR Number] = '1-9077385291'", 5);
//        List<RequestTypeDo> result = (List<RequestTypeDo>) MiscTest.sampleSiebelQuery(RequestTypeDo.class, "[requestNumber] = '1-2107176411'", 5);
        for (RequestTypeDo rtDo : result) {
            System.out.printf("requestNumber=%s, updatedSrNumber=%s\n", rtDo.getRequestNumber(), rtDo.getUpdatedSrNumber());
        }
//        MiscTest.sampleSiebelQuery(RequestTypeDo.class, "[contactId] is not null", 5);
    }        
    
    @Test
    public void queryRequestTypeDo_QA_defect2348() throws Exception {
        // to slow
        List<RequestTypeDo> result = MiscTest.sampleSiebelQuery(RequestTypeDo.class, "[productModel] <> ''", 5, 0);
    }            
    
    @Test
    public void queryRequestTypeDo_QA_defect3564() throws Exception {
        // to slow
        List<RequestTypeDo> result = MiscTest.sampleSiebelQuery(RequestTypeDo.class, "[SR Number] = '1-9944153771'", 5, 0);
    }            
    
    
    @Test
    public void queryRequestTypeDoTest__sorting_QA() throws Exception {
        try {
            Class<?> class1 = RequestTypeDo.class;
//            String searchSpec = "[SR Number] <> ''";
            String searchSpec = "[LXK MPS Updated SR Number] = '1-9077385291'";
            QueryObject qo = new QueryObject(class1, ActionEvent.QUERY);
            qo.addComponentSearchSpec(class1, searchSpec);
            qo.setQueryString(searchSpec);
            qo.setNumRows(5);
            qo.setStartRowIndex(0);
//            qo.setSortString("SR Number(DESCENDING)");
            qo.setSortString("SR Number(DESCENDING)");
            List<RequestTypeDo> result = MiscTest.statelessSession().getDataManager().query(qo);
            
            for (RequestTypeDo rtDo : result) {
                System.out.printf("requestNumber=%s, updatedSrNumber=%s\n", rtDo.getRequestNumber(), rtDo.getUpdatedSrNumber());
            }
            SortUtil.checkSortOrder(result, new SortCriteria("requestNumber", false));
        } finally {
            MiscTest.releaseAllStatelessSessions();
        }
//        MiscTest.sampleSiebelQuery(RequestTypeDo.class, "[contactId] is not null", 5);
    }        
    
    
    @Test
    public void queryLegalIds_QA() throws Exception {
        String s = "([LXK MPS Legal Entity ID #] = '14788' " + 
        		"OR [LXK MPS Legal Entity ID #] = '7310' " + 
        		"OR [LXK MPS Legal Entity ID #] = '60872' " + 
        		"OR [LXK MPS Legal Entity ID #] = '32596' " + 
        		"OR [LXK MPS Legal Entity ID #] = '27086' " + 
        		"OR [LXK MPS Legal Entity ID #] = '15430' " + 
        		"OR [LXK MPS Legal Entity ID #] = '15320' " + 
        		"OR [LXK MPS Legal Entity ID #] = '34765' " + 
        		"OR [LXK MPS Legal Entity ID #] = '34763' " + 
        		"OR [LXK MPS Legal Entity ID #] = '35398' " + 
        		"OR [LXK MPS Legal Entity ID #] = '28035' " + 
        		"OR [LXK MPS Legal Entity ID #] = '27974' " + 
        		"OR [LXK MPS Legal Entity ID #] = '49344' " + 
        		"OR [LXK MPS Legal Entity ID #] = '14778' " + 
        		"OR [LXK MPS Legal Entity ID #] = '15401' " + 
        		"OR [LXK MPS Legal Entity ID #] = '15510' " + 
        		"OR [LXK MPS Legal Entity ID #] = '14854' " + 
        		"OR [LXK MPS Legal Entity ID #] = '14747' " + 
        		"OR [LXK MPS Legal Entity ID #] = '15374' " + 
        		"OR [LXK MPS Legal Entity ID #] = '15427' " + 
        		"OR [LXK MPS Legal Entity ID #] = '6604' " + 
        		"OR [LXK MPS Legal Entity ID #] = '63597' " + 
        		"OR [LXK MPS Legal Entity ID #] = '31416' " + 
        		"OR [LXK MPS Legal Entity ID #] = '11174' " + 
        		"OR [LXK MPS Legal Entity ID #] = '35379' " + 
        		"OR [LXK MPS Legal Entity ID #] = '35406' " + 
        		"OR [LXK MPS Legal Entity ID #] = '35410' " + 
        		"OR [LXK MPS Legal Entity ID #] = '34517' " + 
        		"OR [LXK MPS Legal Entity ID #] = '34519' " + 
        		"OR [LXK MPS Legal Entity ID #] = '36356' " + 
        		"OR [LXK MPS Legal Entity ID #] = '41684' " + 
        		"OR [LXK MPS Legal Entity ID #] = '68502' " + 
        		"OR [LXK MPS Legal Entity ID #] = '4162' " + 
        		"OR [LXK MPS Legal Entity ID #] = '15773' " + 
        		"OR [LXK MPS Legal Entity ID #] = '12094' " + 
        		"OR [LXK MPS Legal Entity ID #] = '14973' " + 
        		"OR [LXK MPS Legal Entity ID #] = '14806' " + 
        		"OR [LXK MPS Legal Entity ID #] = '69561' " + 
        		"OR [LXK MPS Legal Entity ID #] = '15481' " + 
        		"OR [LXK MPS Legal Entity ID #] = '15207')" + 
        		" AND ([LXK MPS SR Status] <>'Archived') AND ([LXK MPS SR Type] <> 'Claims') AND (([LXK MPS Source] <> 'LDCM') OR ([LXK MPS Source] ='LDCM' AND EXISTS ([LXK MPS Order Id] IS NOT NULL)))" + 
//        		" AND ([LXK MPS SR Status] <>'Archived') AND ([LXK MPS SR Type] <> 'Claims') AND (([LXK MPS Legal Entity ID #] = '14788') OR ([LXK MPS Agreement Account Legal Id] = '14788')) AND (([LXK MPS Source] <> 'LDCM') OR ([LXK MPS Source] ='LDCM' AND EXISTS ([LXK MPS Order Id] IS NOT NULL)))" +
        		"";
        MiscTest.sampleSiebelQuery(RequestTypeDo.class, s, 50);
    }
    
    @Test
    public void queryLegalIds_QA_2() throws Exception {
        String s = "[LXK MPS Legal Entity ID #] = '14788' "; 
        MiscTest.sampleSiebelQuery(RequestTypeDo.class, s, 50);
    } 
    
    @Test
    public void queryLegalIds_QA_3() throws Exception {
        String s = "([LXK MPS SR Status] <>'Archived') AND ([LXK MPS SR Type] <> 'Claims') AND (([LXK MPS Legal Entity ID #] = '14788') OR ([LXK MPS Agreement Account Legal Id] = '14788')) AND (([LXK MPS Source] <> 'LDCM') OR ([LXK MPS Source] ='LDCM' AND EXISTS ([LXK MPS Order Id] IS NOT NULL)))"; 
        MiscTest.sampleSiebelQuery(RequestTypeDo.class, s, 50);
    } 
    
    @Test
    public void queryLegalIds_QA_4() throws Exception {
        String s = "([LXK MPS Legal Entity ID #] = '14788' AND ([LXK MPS SR Status] <>'Archived') AND ([LXK MPS SR Type] <> 'Claims') AND (([LXK MPS Source] <> 'LDCM') OR ([LXK MPS Source] ='LDCM' AND EXISTS ([LXK MPS Order Id] IS NOT NULL)))" + 
                "OR ([LXK MPS Legal Entity ID #] = '7310')" + 
                "OR ([LXK MPS Legal Entity ID #] = '60872') " + 
                "OR ([LXK MPS Legal Entity ID #] = '60872') " + 
                "OR ([LXK MPS Legal Entity ID #] = '32596') " + 
                "OR ([LXK MPS Legal Entity ID #] = '32596') " + 
                "OR ([LXK MPS Legal Entity ID #] = '27086') " + 
                "OR ([LXK MPS Legal Entity ID #] = '15430') " + 
                "OR ([LXK MPS Legal Entity ID #] = '15320') " + 
                "OR ([LXK MPS Legal Entity ID #] = '34765') " + 
                "OR ([LXK MPS Legal Entity ID #] = '34763') " + 
                "OR ([LXK MPS Legal Entity ID #] = '35398') " + 
                "OR ([LXK MPS Legal Entity ID #] = '28035') " + 
                "OR ([LXK MPS Legal Entity ID #] = '27974') " + 
                "OR ([LXK MPS Legal Entity ID #] = '49344') " + 
                "OR ([LXK MPS Legal Entity ID #] = '14778') " + 
                "OR ([LXK MPS Legal Entity ID #] = '15401') " + 
                "OR ([LXK MPS Legal Entity ID #] = '15510') " + 
                "OR ([LXK MPS Legal Entity ID #] = '14854') " + 
                "OR ([LXK MPS Legal Entity ID #] = '14747') " + 
                "OR ([LXK MPS Legal Entity ID #] = '15374') " + 
                "OR ([LXK MPS Legal Entity ID #] = '15427') " + 
                "OR ([LXK MPS Legal Entity ID #] = '6604') " + 
                "OR ([LXK MPS Legal Entity ID #] = '63597') " + 
                "OR ([LXK MPS Legal Entity ID #] = '31416') " + 
                "OR ([LXK MPS Legal Entity ID #] = '11174') " + 
                "OR ([LXK MPS Legal Entity ID #] = '35379') " + 
                "OR ([LXK MPS Legal Entity ID #] = '35406') " + 
                "OR ([LXK MPS Legal Entity ID #] = '35410') " + 
                "OR ([LXK MPS Legal Entity ID #] = '34517') " + 
                "OR ([LXK MPS Legal Entity ID #] = '34519') " + 
                "OR ([LXK MPS Legal Entity ID #] = '36356') " + 
                "OR ([LXK MPS Legal Entity ID #] = '41684') " + 
                "OR ([LXK MPS Legal Entity ID #] = '68502') " + 
                "OR ([LXK MPS Legal Entity ID #] = '4162') " + 
                "OR ([LXK MPS Legal Entity ID #] = '15773') " + 
                "OR ([LXK MPS Legal Entity ID #] = '12094') " + 
                "OR ([LXK MPS Legal Entity ID #] = '14973') " + 
                "OR ([LXK MPS Legal Entity ID #] = '14806') " + 
                "OR ([LXK MPS Legal Entity ID #] = '69561') " + 
                "OR ([LXK MPS Legal Entity ID #] = '15481') " + 
                "OR ([LXK MPS Legal Entity ID #] = '15207')" + 
//              " AND ([LXK MPS SR Status] <>'Archived') AND ([LXK MPS SR Type] <> 'Claims') AND (([LXK MPS Source] <> 'LDCM') OR ([LXK MPS Source] ='LDCM' AND EXISTS ([LXK MPS Order Id] IS NOT NULL)))" + 
//                " AND ([LXK MPS SR Status] <>'Archived') AND ([LXK MPS SR Type] <> 'Claims') AND (([LXK MPS Legal Entity ID #] = '14788') OR ([LXK MPS Agreement Account Legal Id] = '14788')) AND (([LXK MPS Source] <> 'LDCM') OR ([LXK MPS Source] ='LDCM' AND EXISTS ([LXK MPS Order Id] IS NOT NULL)))" +
                "";
        MiscTest.sampleSiebelQuery(RequestTypeDo.class, s, 50);
    }   
    
    @Test
    public void queryLegalIds_QA_5() throws Exception {
        String s = "[LXK MPS Legal Entity ID #] = 'abc' and  ([LXK MPS Legal Entity ID #] = '14788' AND ([LXK MPS SR Status] <>'Archived') AND ([LXK MPS SR Type] <> 'Claims') AND (([LXK MPS Source] <> 'LDCM') OR ([LXK MPS Source] ='LDCM' AND EXISTS ([LXK MPS Order Id] IS NOT NULL))))" + 
                "OR ([LXK MPS Legal Entity ID #] = '7310' AND ([LXK MPS SR Status] <>'Archived') AND ([LXK MPS SR Type] <> 'Claims') AND (([LXK MPS Source] <> 'LDCM') OR ([LXK MPS Source] ='LDCM' AND EXISTS ([LXK MPS Order Id] IS NOT NULL))))" + 
                "OR ([LXK MPS Legal Entity ID #] = '60872' AND ([LXK MPS SR Status] <>'Archived') AND ([LXK MPS SR Type] <> 'Claims') AND (([LXK MPS Source] <> 'LDCM') OR ([LXK MPS Source] ='LDCM' AND EXISTS ([LXK MPS Order Id] IS NOT NULL)))) " + 
                "OR ([LXK MPS Legal Entity ID #] = '60872' AND ([LXK MPS SR Status] <>'Archived') AND ([LXK MPS SR Type] <> 'Claims') AND (([LXK MPS Source] <> 'LDCM') OR ([LXK MPS Source] ='LDCM' AND EXISTS ([LXK MPS Order Id] IS NOT NULL)))) " + 
                "OR ([LXK MPS Legal Entity ID #] = '32596' AND ([LXK MPS SR Status] <>'Archived') AND ([LXK MPS SR Type] <> 'Claims') AND (([LXK MPS Source] <> 'LDCM') OR ([LXK MPS Source] ='LDCM' AND EXISTS ([LXK MPS Order Id] IS NOT NULL)))) " + 
                "OR ([LXK MPS Legal Entity ID #] = '32596' AND ([LXK MPS SR Status] <>'Archived') AND ([LXK MPS SR Type] <> 'Claims') AND (([LXK MPS Source] <> 'LDCM') OR ([LXK MPS Source] ='LDCM' AND EXISTS ([LXK MPS Order Id] IS NOT NULL)))) " + 
                "OR ([LXK MPS Legal Entity ID #] = '27086' AND ([LXK MPS SR Status] <>'Archived') AND ([LXK MPS SR Type] <> 'Claims') AND (([LXK MPS Source] <> 'LDCM') OR ([LXK MPS Source] ='LDCM' AND EXISTS ([LXK MPS Order Id] IS NOT NULL)))) " + 
                "OR ([LXK MPS Legal Entity ID #] = '15430' AND ([LXK MPS SR Status] <>'Archived') AND ([LXK MPS SR Type] <> 'Claims') AND (([LXK MPS Source] <> 'LDCM') OR ([LXK MPS Source] ='LDCM' AND EXISTS ([LXK MPS Order Id] IS NOT NULL)))) " + 
                "OR ([LXK MPS Legal Entity ID #] = '15320' AND ([LXK MPS SR Status] <>'Archived') AND ([LXK MPS SR Type] <> 'Claims') AND (([LXK MPS Source] <> 'LDCM') OR ([LXK MPS Source] ='LDCM' AND EXISTS ([LXK MPS Order Id] IS NOT NULL)))) " + 
                "OR ([LXK MPS Legal Entity ID #] = '34765' AND ([LXK MPS SR Status] <>'Archived') AND ([LXK MPS SR Type] <> 'Claims') AND (([LXK MPS Source] <> 'LDCM') OR ([LXK MPS Source] ='LDCM' AND EXISTS ([LXK MPS Order Id] IS NOT NULL)))) " + 
                "OR ([LXK MPS Legal Entity ID #] = '34763' AND ([LXK MPS SR Status] <>'Archived') AND ([LXK MPS SR Type] <> 'Claims') AND (([LXK MPS Source] <> 'LDCM') OR ([LXK MPS Source] ='LDCM' AND EXISTS ([LXK MPS Order Id] IS NOT NULL)))) " + 
                "OR ([LXK MPS Legal Entity ID #] = '35398' AND ([LXK MPS SR Status] <>'Archived') AND ([LXK MPS SR Type] <> 'Claims') AND (([LXK MPS Source] <> 'LDCM') OR ([LXK MPS Source] ='LDCM' AND EXISTS ([LXK MPS Order Id] IS NOT NULL)))) " + 
                "OR ([LXK MPS Legal Entity ID #] = '28035' AND ([LXK MPS SR Status] <>'Archived') AND ([LXK MPS SR Type] <> 'Claims') AND (([LXK MPS Source] <> 'LDCM') OR ([LXK MPS Source] ='LDCM' AND EXISTS ([LXK MPS Order Id] IS NOT NULL)))) " + 
                "OR ([LXK MPS Legal Entity ID #] = '27974' AND ([LXK MPS SR Status] <>'Archived') AND ([LXK MPS SR Type] <> 'Claims') AND (([LXK MPS Source] <> 'LDCM') OR ([LXK MPS Source] ='LDCM' AND EXISTS ([LXK MPS Order Id] IS NOT NULL)))) " + 
                "OR ([LXK MPS Legal Entity ID #] = '49344' AND ([LXK MPS SR Status] <>'Archived') AND ([LXK MPS SR Type] <> 'Claims') AND (([LXK MPS Source] <> 'LDCM') OR ([LXK MPS Source] ='LDCM' AND EXISTS ([LXK MPS Order Id] IS NOT NULL)))) " + 
                "OR ([LXK MPS Legal Entity ID #] = '14778' AND ([LXK MPS SR Status] <>'Archived') AND ([LXK MPS SR Type] <> 'Claims') AND (([LXK MPS Source] <> 'LDCM') OR ([LXK MPS Source] ='LDCM' AND EXISTS ([LXK MPS Order Id] IS NOT NULL)))) " + 
                "OR ([LXK MPS Legal Entity ID #] = '15401' AND ([LXK MPS SR Status] <>'Archived') AND ([LXK MPS SR Type] <> 'Claims') AND (([LXK MPS Source] <> 'LDCM') OR ([LXK MPS Source] ='LDCM' AND EXISTS ([LXK MPS Order Id] IS NOT NULL)))) " + 
                "OR ([LXK MPS Legal Entity ID #] = '15510' AND ([LXK MPS SR Status] <>'Archived') AND ([LXK MPS SR Type] <> 'Claims') AND (([LXK MPS Source] <> 'LDCM') OR ([LXK MPS Source] ='LDCM' AND EXISTS ([LXK MPS Order Id] IS NOT NULL)))) " + 
                "OR ([LXK MPS Legal Entity ID #] = '14854' AND ([LXK MPS SR Status] <>'Archived') AND ([LXK MPS SR Type] <> 'Claims') AND (([LXK MPS Source] <> 'LDCM') OR ([LXK MPS Source] ='LDCM' AND EXISTS ([LXK MPS Order Id] IS NOT NULL)))) " + 
                "OR ([LXK MPS Legal Entity ID #] = '14747' AND ([LXK MPS SR Status] <>'Archived') AND ([LXK MPS SR Type] <> 'Claims') AND (([LXK MPS Source] <> 'LDCM') OR ([LXK MPS Source] ='LDCM' AND EXISTS ([LXK MPS Order Id] IS NOT NULL)))) " + 
                "OR ([LXK MPS Legal Entity ID #] = '15374' AND ([LXK MPS SR Status] <>'Archived') AND ([LXK MPS SR Type] <> 'Claims') AND (([LXK MPS Source] <> 'LDCM') OR ([LXK MPS Source] ='LDCM' AND EXISTS ([LXK MPS Order Id] IS NOT NULL)))) " + 
                "OR ([LXK MPS Legal Entity ID #] = '15427' AND ([LXK MPS SR Status] <>'Archived') AND ([LXK MPS SR Type] <> 'Claims') AND (([LXK MPS Source] <> 'LDCM') OR ([LXK MPS Source] ='LDCM' AND EXISTS ([LXK MPS Order Id] IS NOT NULL)))) " + 
                "OR ([LXK MPS Legal Entity ID #] = '6604' AND ([LXK MPS SR Status] <>'Archived') AND ([LXK MPS SR Type] <> 'Claims') AND (([LXK MPS Source] <> 'LDCM') OR ([LXK MPS Source] ='LDCM' AND EXISTS ([LXK MPS Order Id] IS NOT NULL)))) " + 
                "OR ([LXK MPS Legal Entity ID #] = '63597' AND ([LXK MPS SR Status] <>'Archived') AND ([LXK MPS SR Type] <> 'Claims') AND (([LXK MPS Source] <> 'LDCM') OR ([LXK MPS Source] ='LDCM' AND EXISTS ([LXK MPS Order Id] IS NOT NULL)))) " + 
                "OR ([LXK MPS Legal Entity ID #] = '31416' AND ([LXK MPS SR Status] <>'Archived') AND ([LXK MPS SR Type] <> 'Claims') AND (([LXK MPS Source] <> 'LDCM') OR ([LXK MPS Source] ='LDCM' AND EXISTS ([LXK MPS Order Id] IS NOT NULL)))) " + 
                "OR ([LXK MPS Legal Entity ID #] = '11174' AND ([LXK MPS SR Status] <>'Archived') AND ([LXK MPS SR Type] <> 'Claims') AND (([LXK MPS Source] <> 'LDCM') OR ([LXK MPS Source] ='LDCM' AND EXISTS ([LXK MPS Order Id] IS NOT NULL)))) " + 
                "OR ([LXK MPS Legal Entity ID #] = '35379' AND ([LXK MPS SR Status] <>'Archived') AND ([LXK MPS SR Type] <> 'Claims') AND (([LXK MPS Source] <> 'LDCM') OR ([LXK MPS Source] ='LDCM' AND EXISTS ([LXK MPS Order Id] IS NOT NULL)))) " + 
                "OR ([LXK MPS Legal Entity ID #] = '35406' AND ([LXK MPS SR Status] <>'Archived') AND ([LXK MPS SR Type] <> 'Claims') AND (([LXK MPS Source] <> 'LDCM') OR ([LXK MPS Source] ='LDCM' AND EXISTS ([LXK MPS Order Id] IS NOT NULL)))) " + 
                "OR ([LXK MPS Legal Entity ID #] = '35410' AND ([LXK MPS SR Status] <>'Archived') AND ([LXK MPS SR Type] <> 'Claims') AND (([LXK MPS Source] <> 'LDCM') OR ([LXK MPS Source] ='LDCM' AND EXISTS ([LXK MPS Order Id] IS NOT NULL)))) " + 
                "OR ([LXK MPS Legal Entity ID #] = '34517' AND ([LXK MPS SR Status] <>'Archived') AND ([LXK MPS SR Type] <> 'Claims') AND (([LXK MPS Source] <> 'LDCM') OR ([LXK MPS Source] ='LDCM' AND EXISTS ([LXK MPS Order Id] IS NOT NULL)))) " + 
                "OR ([LXK MPS Legal Entity ID #] = '34519' AND ([LXK MPS SR Status] <>'Archived') AND ([LXK MPS SR Type] <> 'Claims') AND (([LXK MPS Source] <> 'LDCM') OR ([LXK MPS Source] ='LDCM' AND EXISTS ([LXK MPS Order Id] IS NOT NULL)))) " + 
                "OR ([LXK MPS Legal Entity ID #] = '36356' AND ([LXK MPS SR Status] <>'Archived') AND ([LXK MPS SR Type] <> 'Claims') AND (([LXK MPS Source] <> 'LDCM') OR ([LXK MPS Source] ='LDCM' AND EXISTS ([LXK MPS Order Id] IS NOT NULL)))) " + 
                "OR ([LXK MPS Legal Entity ID #] = '41684' AND ([LXK MPS SR Status] <>'Archived') AND ([LXK MPS SR Type] <> 'Claims') AND (([LXK MPS Source] <> 'LDCM') OR ([LXK MPS Source] ='LDCM' AND EXISTS ([LXK MPS Order Id] IS NOT NULL)))) " + 
                "OR ([LXK MPS Legal Entity ID #] = '68502' AND ([LXK MPS SR Status] <>'Archived') AND ([LXK MPS SR Type] <> 'Claims') AND (([LXK MPS Source] <> 'LDCM') OR ([LXK MPS Source] ='LDCM' AND EXISTS ([LXK MPS Order Id] IS NOT NULL)))) " + 
                "OR ([LXK MPS Legal Entity ID #] = '4162' AND ([LXK MPS SR Status] <>'Archived') AND ([LXK MPS SR Type] <> 'Claims') AND (([LXK MPS Source] <> 'LDCM') OR ([LXK MPS Source] ='LDCM' AND EXISTS ([LXK MPS Order Id] IS NOT NULL)))) " + 
                "OR ([LXK MPS Legal Entity ID #] = '15773' AND ([LXK MPS SR Status] <>'Archived') AND ([LXK MPS SR Type] <> 'Claims') AND (([LXK MPS Source] <> 'LDCM') OR ([LXK MPS Source] ='LDCM' AND EXISTS ([LXK MPS Order Id] IS NOT NULL)))) " + 
                "OR ([LXK MPS Legal Entity ID #] = '12094' AND ([LXK MPS SR Status] <>'Archived') AND ([LXK MPS SR Type] <> 'Claims') AND (([LXK MPS Source] <> 'LDCM') OR ([LXK MPS Source] ='LDCM' AND EXISTS ([LXK MPS Order Id] IS NOT NULL)))) " + 
                "OR ([LXK MPS Legal Entity ID #] = '14973' AND ([LXK MPS SR Status] <>'Archived') AND ([LXK MPS SR Type] <> 'Claims') AND (([LXK MPS Source] <> 'LDCM') OR ([LXK MPS Source] ='LDCM' AND EXISTS ([LXK MPS Order Id] IS NOT NULL)))) " + 
                "OR ([LXK MPS Legal Entity ID #] = '14806' AND ([LXK MPS SR Status] <>'Archived') AND ([LXK MPS SR Type] <> 'Claims') AND (([LXK MPS Source] <> 'LDCM') OR ([LXK MPS Source] ='LDCM' AND EXISTS ([LXK MPS Order Id] IS NOT NULL)))) " + 
                "OR ([LXK MPS Legal Entity ID #] = '69561' AND ([LXK MPS SR Status] <>'Archived') AND ([LXK MPS SR Type] <> 'Claims') AND (([LXK MPS Source] <> 'LDCM') OR ([LXK MPS Source] ='LDCM' AND EXISTS ([LXK MPS Order Id] IS NOT NULL)))) " + 
                "OR ([LXK MPS Legal Entity ID #] = '15481' AND ([LXK MPS SR Status] <>'Archived') AND ([LXK MPS SR Type] <> 'Claims') AND (([LXK MPS Source] <> 'LDCM') OR ([LXK MPS Source] ='LDCM' AND EXISTS ([LXK MPS Order Id] IS NOT NULL)))) " + 
                "OR ([LXK MPS Legal Entity ID #] = '15207' AND ([LXK MPS SR Status] <>'Archived') AND ([LXK MPS SR Type] <> 'Claims') AND (([LXK MPS Source] <> 'LDCM') OR ([LXK MPS Source] ='LDCM' AND EXISTS ([LXK MPS Order Id] IS NOT NULL))))" + 
//              " AND ([LXK MPS SR Status] <>'Archived') AND ([LXK MPS SR Type] <> 'Claims') AND (([LXK MPS Source] <> 'LDCM') OR ([LXK MPS Source] ='LDCM' AND EXISTS ([LXK MPS Order Id] IS NOT NULL)))" + 
//                " AND ([LXK MPS SR Status] <>'Archived') AND ([LXK MPS SR Type] <> 'Claims') AND (([LXK MPS Legal Entity ID #] = '14788') OR ([LXK MPS Agreement Account Legal Id] = '14788')) AND (([LXK MPS Source] <> 'LDCM') OR ([LXK MPS Source] ='LDCM' AND EXISTS ([LXK MPS Order Id] IS NOT NULL)))" +
                "";
        MiscTest.sampleSiebelQuery(RequestTypeDo.class, s, 50);
    }       
    
}
