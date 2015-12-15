package com.lexmark.service.impl.real.domain;

import org.junit.Test;

import com.lexmark.service.impl.real.MiscTest;


/**
 * @author vpetruchok
 * @version 1.0, 2012-10-18
 */
public class AccountAssetFavoritesTest {
    
    
    @Test
    public void query1() throws Exception {
        MiscTest.sampleSiebelQuery(AccountAssetFavorites.class, "", 5);
    }
    
    @Test
    public void query_QA_Defect4143() throws Exception {
//        MiscTest.sampleSiebelQuery(AccountAssetFavorites.class, "[serialNumber] = '03SEP11'", 10);
        MiscTest.sampleSiebelQuery(AccountAssetFavorites.class, 
//                "[serialNumber] = '03SEP11'"
//                "EXISTS([LXK MPS Ent Global DUNS] = '023058159' AND [LXK MPS Ent MDM Level] = 'Siebel') AND [LXK SW Asset Favorite Flag] = 'Y' AND [Contact Id] = '1-42M3K0F' AND EXISTS ([LXK MPS Ent Type] ~LIKE 'Consumable*')"
//                "EXISTS([LXK MPS Ent Global DUNS] = '023058159' AND [LXK MPS Ent MDM Level] = 'Siebel') AND [LXK SW Asset Favorite Flag] = 'Y' AND EXISTS ([LXK MPS Ent Type] ~LIKE 'Consumable*')"
                "EXISTS([LXK MPS Ent Global DUNS] = '006961700' AND [LXK MPS Ent MDM Level] = 'Siebel') AND [LXK SW Asset Favorite Flag] = 'Y' AND [Contact Id] = '1-4ADJPG9' AND EXISTS ([LXK MPS Ent Type] ~LIKE 'Consumable*')"
                , 10);
    }
    
    @Test
    public void queryDefect4649() throws Exception {
        MiscTest.sampleSiebelQuery(AccountAssetFavorites.class, 
                "[ownerAccountId] <> '' AND [accountName] <> ''"
                , 10);
    }
    
}
