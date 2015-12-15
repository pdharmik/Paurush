package com.lexmark.contract;

import static org.junit.Assert.assertEquals;

import org.junit.Test;


/**
 * @author vpetruchok
 * @version 1.0, 2012-04-09
 */
public class ActivityListContractTest {

    @Test
     public void testClone() throws Exception {
         ActivityListContract alc = new ActivityListContract();
         alc.setMdmLevel("Global");
         ActivityListContract alc2 = alc.clone();
         assertEquals(alc.getMdmLevel(), alc2.getMdmLevel());
        
    }
}
