package com.lexmark.service.impl.real;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.lexmark.service.impl.real.domain.ConsumableAssetLocationDoTest;
import com.lexmark.service.impl.real.domain.OrderSuppliesAssetDetailDoTest;
import com.lexmark.service.impl.real.domain.OrderSuppliesAssetDoTest;
import com.lexmark.service.impl.real.domain.OrderSuppliesAssetFavoriteDoTest;
import com.lexmark.service.impl.real.orderSuppliesAssetService.AmindOrderSuppliesAssetDeviceListServiceTest;
import com.lexmark.service.impl.real.orderSuppliesAssetService.AmindOrderSuppliesAssetServiceUtilTest;
import com.lexmark.service.impl.real.orderSuppliesAssetService.AssetListServiceFilterTest;
import com.lexmark.service.impl.real.orderSuppliesAssetService.AssetListServiceSortTest;

/**
 * 
 * @see com.lexmark.service.impl.real.AmindOrderSuppliesAssetService
 * 
 * @author vpetruchok
 * @version 1.0, 2012-04-24
 */
@RunWith(Suite.class)
@SuiteClasses({
    AmindOrderSuppliesAssetServiceDoClassesTest.class,
    AmindOrderSuppliesAssetServiceTest.class,
    AmindOrderSuppliesAssetServiceUtilTest.class,
    OrderSuppliesAssetDoTest.class,
    OrderSuppliesAssetFavoriteDoTest.class,
    OrderSuppliesAssetDetailDoTest.class,    
    ConsumableAssetLocationDoTest.class,
    AmindOrderSuppliesAssetServiceRetrieveDeviceListFilterTest.class,
    AmindOrderSuppliesAssetServiceRetrieveDeviceListSortTest.class,
    AmindOrderSuppliesAssetDeviceListServiceTest.class,
    AssetListServiceSortTest.class,
    AssetListServiceFilterTest.class,
})
public class AmindOrderSuppliesAssetServiceTestSuite {

}
