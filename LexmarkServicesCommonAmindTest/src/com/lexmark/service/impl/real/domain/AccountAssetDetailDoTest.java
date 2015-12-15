package com.lexmark.service.impl.real.domain;

import java.util.List;

import org.junit.Test;

import com.lexmark.service.impl.real.MiscTest;


/**
 * @author vpetruchok
 * @version 1.0, 2012-10-12
 */
public class AccountAssetDetailDoTest {
    
    @Test
    public void query1() throws Exception {
        MiscTest.sampleSiebelQuery(AccountAssetDetailDo.class, "", 10); 
    }
    
    @Test
    public void query2() throws Exception {
//        MiscTest.sampleSiebelQuery(AccountAssetDetailDo.class, "[consumableAssetFlag] = ''", 10); 
//        MiscTest.sampleSiebelQuery(AccountAssetDetailDo.class, "[consumableAssetFlag] is null", 10); 
        MiscTest.sampleSiebelQuery(AccountAssetDetailDo.class, 
                "[consumableAssetFlag] <> 'true' AND [consumableAssetFlag] <> 'false'"
                , 10); 
    }
    
    @Test
    public void query3() throws Exception {
//        MiscTest.sampleSiebelQuery(AccountAssetDetailDo.class, "[consumableAssetFlag] = ''", 10); 
//        MiscTest.sampleSiebelQuery(AccountAssetDetailDo.class, "[consumableAssetFlag] is null", 10); 
//        MiscTest.sampleSiebelQuery(AccountAssetDetailDo.class, 
//                "[assetId] = '1-AJXB-926'"
//                , 1); 
        
        
        MiscTest.sampleSiebelQuery(AccountAssetDetailDo.class, 
                "[LXK MPS Consumable Flag] <> 'Y' AND [LXK MPS Consumable Flag] <> 'N'"
                , 1); 
    }
    
    
    
    // Email subject: LEX:IN001261320 CUSTOMER PORTAL: Show Duplicate Listing of Maintenance Kit Install.
    @Test
    public void testLEX_IN001261320() throws Exception {
        List<AccountAssetDetailDo> r = MiscTest.sampleSiebelQuery(AccountAssetDetailDo.class, 
                "[Asset Id]= '1-6UOH-1384'"
                , 50); 
        
        System.out.println();
//        MiscTest.print(r, "entitlements");
        
        System.out.println("\n## entitlements ##"); 
        for (AccountAssetDetailDo detDo: r) {
            for (ServiceAgreementProductDo prodDo : detDo.getEntitlements()) {
                System.out.printf("assetId=%s, serviceDetailsId=%s\n", prodDo.getAssetId(), prodDo.getServiceDetailsId());
                for (EntitlementTemplateServiceDetailsDo  serDetailDo : prodDo.getServiceDetails()) {
//                    System.out.println(MiscTest.str(serDetailDo));
                    MiscTest.print("", serDetailDo, "entitlementId", "description", "customerShowFlag");
                }
                System.out.println("== size = " + detDo.getEntitlements().size());
            }
        }
        
        System.out.println("\n## meterReads ##");
        for (AccountAssetDetailDo detDo: r) {
            for (AssetMeasurementCharacteristicsBCDo meterReadDo : detDo.getMeterReads()) {
                System.out.printf("assetId=%s, latestReading=%s\n", meterReadDo.getAssetId(), meterReadDo.getLatestReading());
                for (AssetReadingLatestBcDo readingDo : meterReadDo.getAssetReading())  {
                    System.out.println(MiscTest.str(readingDo));
                }
            }
        }
        
    }

}
