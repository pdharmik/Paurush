package com.lexmark.service.impl.real.domain;


import static com.lexmark.service.impl.real.util.DoFileUtil.checkDoFile;

import org.junit.Test;

/**
 * @author vpetruchok
 * @version 1.0, 2012-05-29
 */
public class OrderSuppliesAssetPartDetailDoTest {

    @Test
    public void testCheckFields() throws Exception {
        checkDoFile("../LexmarkServicesCommon/src/DoMappings/orderSuppliesAssetService/do-ordersuppliesassetpartdetail-mapping.xml");
    }
}
