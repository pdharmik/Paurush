package com.lexmark.service.impl.real;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;


@RunWith(Suite.class)
@SuiteClasses({ AddressListFilterTest.class, AddressListPaginationTest.class, AddressListSortTest.class,
		AddressFavoriteListTest.class })
public class AddressServiceTestSuite {

}
