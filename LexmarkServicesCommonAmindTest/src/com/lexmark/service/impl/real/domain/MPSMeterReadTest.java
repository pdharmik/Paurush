package com.lexmark.service.impl.real.domain;

import java.util.List;

import org.junit.Test;

import com.lexmark.service.impl.real.MiscTest;


/**
 * @author vpetruchok
 * @version 1.0, 2012-07-16
 */
public class MPSMeterReadTest {
    
    @Test
    public void queryRecords() throws Exception {
        List<?> lst =  MiscTest.querySiebel(MPSMeterRead.class, "", 5, 0);
        MiscTest.print(lst);
    }
    
    @Test
    public void queryRecordsByAssetId() throws Exception {
        List<?> lst =  MiscTest.querySiebel(MPSMeterRead.class, "[assetId] = '1-AJXB-12264'", 2 , 0);
//        List<?> lst =  MiscTest.querySiebel(MPSMeterRead.class, "[assetId] = '1-363P26'", 2 , 0);  // no data
        MiscTest.print(lst);
    }
    
    
    @Test
    public void queryRecordsByAssetId2() throws Exception {
        List<?> lst =  MiscTest.queryBySearchExpression(MPSMeterRead.class, "[Id]= '2986457'");
        MiscTest.print(lst);
    }

}
