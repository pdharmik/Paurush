package com.lexmark.service.impl.real.domain;

import org.junit.Test;

import util.TestSessionFactories;

import com.amind.data.service.ActionEvent;
import com.amind.data.service.QueryObject;
import com.amind.session.Session;
import com.lexmark.service.impl.real.MiscTest;
import com.lexmark.service.impl.real.util.AmindServiceUtil;

/**
 * @author vpetruchok
 * @version 1.0, 2013-03-14
 */
public class PartnerFruPartDoTest {

    @Test
    public void query1() throws Exception {
        MiscTest.sampleSiebelQuery(PartnerFruPartDo.class, "", 5);
    }

    /**
     * @see com.lexmark.service.impl.real.PartnerFRUPartDetailTest#testRetrieveFruPartDetail()
     * @throws Exception
     */
    @Test
    public void query2() throws Exception {
        MiscTest.sampleSiebelQuery(PartnerFruPartDo.class, "[Part Name] = '56P4385'", 5);
    }

    @Test
    public void query3() throws Exception {
        QueryObject criteria = null;
        String searchSpec = "[partNumber] = '56P4385'";
        criteria = new QueryObject(PartnerFruPartDo.class, ActionEvent.QUERY);
        criteria.setStartRowIndex(0);
//        criteria.setNumRows(5);   // will cause an issue(IC is not configured for pagination)
        criteria.setQueryString(searchSpec); 
//        criteria.addComponentSearchSpec(PartnerFruPartDo.class, searchSpec);
//        criteria.addComponentSearchSpec(PartnerPartLocations.class, "[id] = '" + "" + "'");
        
        Session session = null;
        try {
             session = TestSessionFactories.newStatelessSessionFactory().attachSession();
             session.getDataManager().query(criteria);
        } finally {
            AmindServiceUtil.detachSession(session);
        }

//        MiscTest.sampleSiebelQuery(PartnerFruPartDo.class, "[Part Name] = '~~not-exists~~'", 5);
    }
}
