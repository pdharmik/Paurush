package com.lexmark.service.impl.real;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.lexmark.service.impl.real.deviceService.AmindContractedDeviceDataConversionUtilTest;
import com.lexmark.service.impl.real.deviceService.AmindContractedDeviceServiceUtilTest;

/**
 * @see com.lexmark.service.impl.real.AmindContractedDeviceService
 *
 * @author vpetruchok
 * @version 1.0, 2012-04-11
 */
@RunWith(Suite.class)
@SuiteClasses({
    AmindContractedDeviceDataConversionUtilTest.class,
    AmindContractedDeviceServiceUtilTest.class,
    AmindContractedDeviceServiceTest.class,
    AmindContractedDeviceServiceDoClassesTest.class,
    AmindContractedDeviceServiceDoClassMappingsTest.class,
    AccountAssetFavoriteTest.class,
    AccountAssetIncrementTest.class,
    AccountAssetTest.class,
    AccountAssetUpdateFavoriteTest.class,
    AccountGlobalListAssetTest.class,
    AmindDeviceServiceDetailTest.class,
    AssetLocationTest.class,
    AssetReportingHierarchyTest.class,
    ContractedAccountAssetTest.class,
    AmindContractedDeviceServiceRetrieveDeviceListSortTest.class,
    AmindContractedDeviceServiceRetrieveDeviceListFilterTest.class
})
public class AmindContractedDeviceServiceTestSuite {

}
