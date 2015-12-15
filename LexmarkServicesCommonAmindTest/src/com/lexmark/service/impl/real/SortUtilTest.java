package com.lexmark.service.impl.real;


import static com.lexmark.service.impl.real.SortUtil.checkSortOrder;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.lexmark.domain.Account;
import com.lexmark.domain.Asset;
import com.lexmark.service.impl.real.SortUtil.SortCriteria;

/**
 * @author vpetruchok
 * @version 1.0, 2012-05-18
 */
public class SortUtilTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testCheckSortOrder() throws IllegalAccessException {
        Asset a1 = new Asset();
        a1.setAssetId("assetId1");
        a1.setSerialNumber("1");
        
        Asset a2 = new Asset();
        a2.setAssetId("assetId1");
        a2.setSerialNumber("2");
        
        checkSortOrder(Arrays.asList(a1, a2), new SortCriteria("serialNumber", true));
        try {
            checkSortOrder(Arrays.asList(a2, a1), new SortCriteria("serialNumber", true));
            fail();
        } catch (Error ok) {
        }
        checkSortOrder(Arrays.asList(a2, a1), new SortCriteria("serialNumber", false));
    }
    
    @Test
    public void testCheckSortOrder_case() throws IllegalAccessException {
        Asset a1 = new Asset();
        a1.setAssetId("assetId1");
        a1.setSerialNumber("a");
        
        Asset a2 = new Asset();
        a2.setAssetId("assetId2");
        a2.setSerialNumber("A");
        
        Asset a3 = new Asset();
        a3.setAssetId("assetId3");
        a3.setSerialNumber("B");
        
        checkSortOrder(Arrays.asList(a2, a3, a1), new SortCriteria("serialNumber", true));
        checkSortOrder(Arrays.asList(a1, a2, a3), new SortCriteria("serialNumber", true, true), true);
    }

    @Test
    public void testReadField() throws Exception {
       Account account = new Account();
       account.setAccountId("account1");
       Asset asset = new Asset();
       asset.setAssetId("asset1");
       asset.setAccount(account);
       assertEquals("asset1", SortUtil.readField(asset , "assetId", null));
       assertEquals("account1", SortUtil.readField(asset , "account.accountId", "."));
       assertEquals("account1", SortUtil.readField(asset , "account/accountId", "/"));
    }
    
    
    @Test
    public void testCheckSortOrder_SortCriteriaWithEmptyFieldname() throws Exception {
          List<Asset> assets = Arrays.asList(new Asset(), new Asset());
          checkSortOrder(assets, new SortCriteria("", true));
    } 
        
}
