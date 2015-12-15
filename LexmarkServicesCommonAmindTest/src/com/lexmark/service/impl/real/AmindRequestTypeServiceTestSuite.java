package com.lexmark.service.impl.real;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.lexmark.service.impl.real.domain.SupplyRequestDetailDoTest;
import com.lexmark.service.impl.real.domain.SupplyRequestDetailPendingShipmentDoTest;
import com.lexmark.service.impl.real.requestService.RequestTypeListDateFilterTest;
import com.lexmark.service.impl.real.requestService.RequestTypeListFilterTest;
import com.lexmark.service.impl.real.requestService.RequestTypeListPaginationTest;
import com.lexmark.service.impl.real.requestService.RequestTypeListSortTest;
import com.lexmark.service.impl.real.requestService.RequestTypeListTest;

/**
 * @author vpetruchok
 * @version 1.0, 2012-07-03
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
    AmindRequestTypeServiceTest.class,
    SupplyRequestDetailDoTest.class,
    SupplyRequestDetailPendingShipmentDoTest.class,
    RequestTypeListDateFilterTest.class,
    RequestTypeListFilterTest.class,
    RequestTypeListPaginationTest.class,
    RequestTypeListSortTest.class,
    RequestTypeListTest.class
})
public class AmindRequestTypeServiceTestSuite {

}
