package com.lexmark.service.impl.real;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import com.amind.data.service.ActionEvent;
import com.amind.data.service.ExecutionMode;
import com.amind.data.service.QueryObject;
import com.lexmark.service.impl.real.domain.GlobalLegalEntityDO;

/**
 * @author vpetruchok
 * @version 1.0, 2012-04-19
 */
public class AmindGlobalServiceDoClassesTest extends AmindSiebelQueryTest {

    @Test
    public void queryGlobalLegalEntityDO() throws Exception {
        Class<?> class1 = GlobalLegalEntityDO.class;
        List<?> result = sampleSiebelQuery(class1, 2, 1);
        assertEquals(2, result.size());
        
        QueryObject query = new QueryObject(class1, ActionEvent.QUERY);
        query.setExecutionMode(ExecutionMode.BiDirectional);
        query.setNumRows(2);
        query.setStartRowIndex(1);
//        query.setQueryString("[accountLevel] = 'Global' and [mdmLevel1AccountId] <> ''");
        query.setQueryString("[accountLevel] = 'Global' AND [mdmLevel1AccountId] IS NOT NULL");
        List<?> r = dataManager.query(query); 
        System.out.println("==");
        for (Object obj : r) {
            System.out.println("obj = " + str(obj));
        }
    }

    @Test
    public void queryListOfValues() throws Exception {
        List<?> result = sampleSiebelQuery(com.lexmark.service.impl.real.domain.ListOfValues.class, 2, 1);
        assertEquals(2, result.size());
    }

    @Test
    public void queryPartnerAccountDo() throws Exception {
        List<?> result = sampleSiebelQuery(com.lexmark.service.impl.real.domain.PartnerAccountDo.class, 2, 1);
        assertEquals(2, result.size());
    }

    @Test
    public void queryPartnerAgreementDo() throws Exception {
        List<?> result = sampleSiebelQuery(com.lexmark.service.impl.real.domain.PartnerAgreementDo.class, 2, 1);
        assertEquals(2, result.size());
    }

    @Test
    public void queryPartnerDirectAccountDo() throws Exception {
        List<?> result = sampleSiebelQuery(com.lexmark.service.impl.real.domain.PartnerDirectAccountDo.class, 2, 1);
        assertEquals(2, result.size());
    }
}
