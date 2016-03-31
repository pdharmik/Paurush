package com.lexmark.service.api;

import com.googlecode.ehcache.annotations.Cacheable;
import com.lexmark.contract.HardwareCatalogContract;
import com.lexmark.result.BundleListResult;
import com.lexmark.result.HardwareCatalogResult;

public interface OrderHardwareService {
    public HardwareCatalogResult retrieveHardwareList(HardwareCatalogContract catalogListContract) throws Exception;
    @Cacheable(cacheName="retrieveBundleList", keyGeneratorName="myKeyGenerator")
    public BundleListResult retrievePrinterBundleListB2B(HardwareCatalogContract catalogListContract) throws Exception;
}