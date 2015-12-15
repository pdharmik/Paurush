package com.lexmark.service.impl.real;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.lexmark.service.impl.real.domain.PartnerRequestOrderDoTest;
import com.lexmark.service.impl.real.partnerOrderService.OrderServiceListFilterTest;
import com.lexmark.service.impl.real.partnerOrderService.OrderServiceListSortTest;
import com.lexmark.service.impl.real.partnerOrderService.OrderServiceListTest;
import com.lexmark.service.impl.real.partnerOrderService.PartnerOrderDetailServiceTest;

/**
 * @see com.lexmark.service.impl.real.AmindPartnerOrderService
 * 
 * @author vpetruchok
 * @version 1.0, 2012-07-17
 */
@RunWith(Suite.class)
@SuiteClasses({
    AmindPartnerOrderServiceTest.class,
    OrderServiceListTest.class,
    PartnerRequestOrderDoTest.class,
    PartnerOrderDetailServiceTest.class,
    OrderServiceListFilterTest.class,
    OrderServiceListSortTest.class 
})
public class AmindPartnerOrderServiceTestSuite  {

}
