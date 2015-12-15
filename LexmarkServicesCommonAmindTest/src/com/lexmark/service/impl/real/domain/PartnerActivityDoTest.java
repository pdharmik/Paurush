package com.lexmark.service.impl.real.domain;

import java.util.List;

import org.junit.Test;

import com.lexmark.service.impl.real.MiscTest;


/**
 * @author vpetruchok
 * @version 1.0, 2012-10-12
 */
public class PartnerActivityDoTest {
    
    @Test
    public void query() throws Exception {
        MiscTest.sampleSiebelQuery(PartnerActivityDo.class, 
                ""
//                "[activityType] = 'Consumable Supplies & Install'"
                , 10
                );
    }
    
    @Test
    public void query2() throws Exception {
        List list = MiscTest.sampleSiebelQuery(PartnerActivityDo.class, 
//                "[activityType] = 'Consumables Order and Activity'"
//                "[activityType] = 'Consumable Supplies & Install'"
                   "[activityType] = 'Consumables Order and Activity'" +
                   "or [activityType] = 'Consumables Order'" +
                    "or [activityType] = 'Consumables Activity'"
                
                , 10
                );
        
        MiscTest.print(list, "activityType");
    }
    
    
    @Test
    public void query3() throws Exception {
        List list = MiscTest.sampleSiebelQuery(PartnerActivityDo.class, 
//                "[activityId]='1-46UIZVB'"  
//                 "[serviceRequestNumber] = '1-9120280052'"
//                 "[LXK MPS Override Offering]  = 'Consumables Order and Activity'"
//                 "[LXK MPS Override Offering]  = 'Consumables Order'"
//                 "[LXK MPS Override Offering]  = 'Consumables Activity'"
//                 "[serviceRequestNumber] = '1-9120280052' AND [LXK MPS Override Offering]  <> 'Consumables Order and Activity'"
//                   "[LXK SW SP Global Ultimate DUNS] = '018978783' AND [LXK SW SP Account Level] = 'Siebel' AND [LXK R Tech Id] IS NULL  AND ([Status] = 'Pending Service Action'  OR [Status] = 'Claim Submitted'  OR [Status] = 'Invalid Debrief'  OR [Status] = 'Dispatched to SP')" 
                "[LXK SW SP Global Ultimate DUNS] = '018978783' AND [LXK SW SP Account Level] = 'Siebel' AND [LXK R Tech Id] IS NULL  AND ([Status] = 'Pending Service Action'  OR [Status] = 'Claim Submitted'  OR [Status] = 'Invalid Debrief'  OR [Status] = 'Dispatched to SP') AND ([LXK MPS Override Offering] <> 'Consumables Order and Activity' OR [LXK MPS Override Offering] <> 'Consumables Order' OR [LXK MPS Override Offering] <> 'Consumables Activity')"
                , 10
                , 0
                );
        
        MiscTest.print(list,  "activityId", "activityType", "overrideOffering" ); // Consumables Order and Activity
    }

}
