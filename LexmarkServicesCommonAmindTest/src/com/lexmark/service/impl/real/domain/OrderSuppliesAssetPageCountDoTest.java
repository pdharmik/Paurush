package com.lexmark.service.impl.real.domain;


import static com.lexmark.service.impl.real.util.DoFileUtil.checkDoFile;

import org.junit.Test;

/**
 * @author vpetruchok
 * @version 1.0, 2012-06-06
 */
public class OrderSuppliesAssetPageCountDoTest {

    @Test
    public void testCheckFields() throws Exception {
        checkDoFile("../LexmarkServicesCommon/src/DoMappings/do-ordersuppliesassetpagecount-mapping.xml");
        checkDoFile("../LexmarkServicesCommon/src/DoMappings/do-ordersuppliesassetpagecountreading-mapping.xml");
    }
}
