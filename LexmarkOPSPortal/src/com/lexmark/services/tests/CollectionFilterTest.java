package com.lexmark.services.tests;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.lexmark.domain.AccountContact;
import com.lexmark.domain.Asset;
import com.lexmark.domain.Pagination;
import com.lexmark.util.CollectionFilter;
import com.lexmark.util.CollectionSorter;

public class CollectionFilterTest {
	List<Asset> assets = new ArrayList<Asset>();
	CollectionFilter filter;
	CollectionSorter sorter;
	

	@Before
	public void setUp() throws Exception {
		filter = new CollectionFilter(Locale.getDefault());
		sorter = new CollectionSorter();
		createList();
	}
	
	private void createList() {
		for (int i = 0; i < 10; i++) {
			Asset aa = new Asset();
			aa.setAssetId("123456789" + Integer.toString(i));
			aa.setSerialNumber("" + Integer.toString(i)+ "79233X" );
			aa.setAssetTag("assetTag");
			aa.setAssetType("assetType");
			aa.setControlPanelURL("lexmark.printers.lisitng/");
			aa.setDeviceName("deviceName");
			aa.setDeviceTag("deviceTag");
			aa.setHostName("hostName");
			aa.setIpAddress("192.168.1." + Integer.toString(i));
			aa.setProductLine("Lexmark 952P");
			aa.setNotMyPrinter(false);
			//aa.setPhysicalLocation1("3rd floor, east building");
			assets.add(aa);
		}
	}

	@After
	public void tearDown() throws Exception {
	}
	

	
	@Test
	public void testMultiConditionFilter() {
		Map<String , Object>  noFuzzyFilter = new HashMap<String, Object>();
		noFuzzyFilter.put("assetId", "123");
		noFuzzyFilter.put("serialNumber", "abc");
		
		List<Asset> result = filter.filter(assets, noFuzzyFilter, false);
		Assert.assertEquals(0, result.size());
		
		noFuzzyFilter = new HashMap<String, Object>();
		noFuzzyFilter.put("assetId", "123");
		noFuzzyFilter.put("ipAddress", "192.168.1");
		
		 result = filter.filter(assets, noFuzzyFilter, false);
		Assert.assertEquals(10, result.size());
	}
	
	
	@Test
	public void testFilter() {
		List<Asset> result = filter.filter(assets, "assetId:3456789");
		Assert.assertEquals(10, result.size());
		
		
		result = sorter.sort(result, "assetId:desc");
		
		result = sorter.sort(result, (String)null);
		
		Assert.assertEquals(10, result.size());
		
		Map<String , Object>  noFuzzyFilter = new HashMap<String, Object>();
		noFuzzyFilter.put("assetId", "3456789");
		 result = filter.filter(assets, noFuzzyFilter, false);

		 Assert.assertEquals(0, result.size());
		 

		 noFuzzyFilter = new HashMap<String, Object>();
		 noFuzzyFilter.put("assetId", "3456789");
		 result = filter.filter(assets, noFuzzyFilter, true);

		 Assert.assertEquals(10, result.size());

		 // filter ciriteria  is fuzzy search
		 Pagination p1 = new Pagination();
		 p1.getFilterCriteria().put("assetId", "3456789");
		 result = filter.filter(assets,p1);
		 Assert.assertEquals(10, result.size());
		 
		 // filter ciriteria  is NOT fuzzy search
		 Pagination p2 = new Pagination();
		 p2.getSearchCriteria().put("assetId", "3456789");
		 result = filter.filter(assets,p2);
		 Assert.assertEquals(0, result.size());
		 
		 Pagination p3 = new Pagination();
		 p3.setOrderBy("assetId");
		 p3.setDirection(CollectionSorter.SORT_DESCENDING);
		 
		 result =  sorter.sort(assets, p3);
		 Assert.assertEquals("1234567899", result.get(0).getAssetId());
	}
	
	@Test
	public void testFilterBoolean() {
		List<AccountContact> contactList = createAccountContactList();
		List<Asset> result = filter.filter(contactList, "userFavorite:true");
		Assert.assertEquals(5, result.size());
		
		result = filter.filter(contactList, "userFavorite:TRUE");
		Assert.assertEquals(5, result.size());
		
		result = filter.filter(contactList, "userFavorite:ALSE");
		Assert.assertEquals(5, result.size());
	
		Map<String , Object>  noFuzzyFilter = new HashMap<String, Object>();
		noFuzzyFilter.put("userFavoriteFlag", "rue");
		result = filter.filter(assets, noFuzzyFilter, false);
		Assert.assertEquals(0, result.size()); 
	
	}
	
	private List<AccountContact> createAccountContactList() {
		List<AccountContact> contactList = new ArrayList<AccountContact>();
		for (int i = 0; i < 10; i++) {
			AccountContact ac = new AccountContact();
			if(i%2==0) {
				ac.setUserFavorite(true);
			} else {
				ac.setUserFavorite(false);
			}
			contactList.add(ac);
		}
		return contactList;
	}
	

}
