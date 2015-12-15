package com.lexmark.service.impl.real;

import junit.framework.TestCase;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.lexmark.service.impl.real.delegate.SiebelAccountListServiceDoClassesTest;
import com.lexmark.service.impl.real.delegate.SiebelAccountListServiceTest;
import com.lexmark.service.impl.real.domain.AccountByVendorIdDoTest;
import com.lexmark.service.impl.real.requestService.ServiceRequestDetailServiceTest;

/**
 * 
 * @see com.lexmark.service.impl.real.AmindContractedServiceRequestService
 * 
 * @author vpetruchok
 * @version 1.0, 2012-06-26
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
    AmindContractedServiceRequestServiceTest.class,
    SiebelAccountListServiceDoClassesTest.class,
    SiebelAccountListTest.class,    
    SiebelAccountListServiceTest.class,
    ServiceRequestDetailTest.class,    
    ServiceRequestDetailServiceTest.class,
    AccountByVendorIdDoTest.class,
})
public class AmindContractedServiceRequestServiceTestSuite extends TestCase {

}
