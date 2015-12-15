package com.lexmark.service.api;

import com.lexmark.contract.HardwareCatalogContract;
import com.lexmark.result.BundleListResult;
import com.lexmark.result.HardwareCatalogResult;

public interface OrderHardwareService {
    public HardwareCatalogResult retrieveHardwareList(HardwareCatalogContract catalogListContract) throws Exception;
    public BundleListResult retrievePrinterBundleListB2B(HardwareCatalogContract catalogListContract) throws Exception;
}