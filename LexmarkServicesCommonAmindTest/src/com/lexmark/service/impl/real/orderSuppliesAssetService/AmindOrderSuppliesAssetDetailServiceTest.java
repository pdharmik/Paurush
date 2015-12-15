package com.lexmark.service.impl.real.orderSuppliesAssetService;

import static com.lexmark.service.impl.real.orderSuppliesAssetService.AmindOrderSuppliesAssetDetailService.filterOrderParts;
import static com.lexmark.service.impl.real.orderSuppliesAssetService.AmindOrderSuppliesAssetDetailService.populateLastOrderPartList;
import static com.lexmark.service.impl.real.orderSuppliesAssetService.AmindOrderSuppliesAssetDetailService.matchAny; 
import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Date;

import org.junit.Test;

import com.lexmark.contract.AssetContract;
import com.lexmark.domain.Asset;
import com.lexmark.service.impl.real.domain.OrderSuppliesAssetOrderPartDo;

/**
 * 
 * @see com.lexmark.service.impl.real.AmindOrderSuppliesAssetServiceTest#testRetrieveDeviceDetail_pageCounts_QA
 * @see com.lexmark.service.impl.real.AmindOrderSuppliesAssetServiceTestSuite
 * 
 * @author vpetruchok
 * @version 1.0, 2012-08-01
 */
public class AmindOrderSuppliesAssetDetailServiceTest {

    @SuppressWarnings("deprecation")
    @Test
    public void testFilterOrderParts() throws Exception {
        Date beginDate = new Date(2013, 2, 28);
        Date d1 = new Date(beginDate.getTime() - 1000);
        Date d2 = new Date(beginDate.getTime() + 1000);
        Date d3 = beginDate;
        Date d4 = new Date(beginDate.getTime() + 1000);;
        
        OrderSuppliesAssetOrderPartDo partDo1 = new OrderSuppliesAssetOrderPartDo(d1);
        OrderSuppliesAssetOrderPartDo partDo2 = new OrderSuppliesAssetOrderPartDo(d2);
        OrderSuppliesAssetOrderPartDo partDo3 = new OrderSuppliesAssetOrderPartDo(d3);
        OrderSuppliesAssetOrderPartDo partDo4 = new OrderSuppliesAssetOrderPartDo(d3);
        partDo2.setLineType("SHIP");
        partDo3.setLineType("Email Only");
        
        assertEquals(Arrays.asList(partDo2, partDo3), 
            filterOrderParts(Arrays.asList(partDo1, partDo2, partDo3, partDo4), beginDate)); 
    }

    @Test
    public void testPopulateLastOrderPartList() throws Exception {
        populateLastOrderPartList(new Asset(), new AssetContract(),  null);
    }

    @Test
    public void testMatchAny() throws Exception {
        assertEquals(false, matchAny(null, (String[]) null));
        assertEquals(false, matchAny(null, "a"));
        assertEquals(false, matchAny("1", "a"));
        assertEquals(true, matchAny("1", "a", "1"));
        assertEquals(true, matchAny("1", "a",  "b", "1"));
        assertEquals(true, matchAny("1", "a",  "b", "1", "c"));
        
        assertEquals(true, matchAny(null, Arrays.asList("a", null).toArray(new String[0])));
        
    }
    
}