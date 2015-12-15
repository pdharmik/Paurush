package com.lexmark.service.impl.real.partnerActivityService;

import static com.lexmark.service.impl.real.partnerActivityService.AmindPartnerActivitySearchSpecUtil.buildBasicActivitySearchSpec;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.amind.data.service.DataManager;
import com.lexmark.contract.ActivityListContract;
import com.lexmark.service.impl.real.MiscTest;


/**
 * @author vpetruchok
 * @version 1.0, 2012-12-28
 */
public class AmindPartnerActivitySearchSpecUtilTest {

    @Test
    public void testBuildBasicActivitySearchSpec() throws Exception {
        ActivityListContract c = new ActivityListContract();
        c.setMdmLevel("Global");
        c.setQueryType("mock-queryType");
        assertEquals("[mdmLevel1AccountId]='null' AND [LXK SW SP Account Level] = 'Siebel'", 
                buildBasicActivitySearchSpec(c, null, (Map<String,String>) MiscTest.newHashMap()));
    }
    
    @Test
    public void testBuildBasicActivitySearchSpec_withEmpoyeeFlag() throws Exception {
        ActivityListContract c = new ActivityListContract();
        c.setMdmLevel("Global");
        c.setQueryType("mock-queryType");
        c.setEmployeeFlag(true);
        assertEquals("", 
                buildBasicActivitySearchSpec(c, new DataManager() {

                    @Override
                    public List queryBySearchExpression(Class doClass, String queryExpr) {
                        return new ArrayList();
                    }
                  
                }, (Map<String,String>) MiscTest.newHashMap()));
    }

}
