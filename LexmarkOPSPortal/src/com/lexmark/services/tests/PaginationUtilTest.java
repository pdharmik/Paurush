package com.lexmark.services.tests;

import java.util.List;

import junit.framework.TestCase;

import org.junit.Assert;
import org.junit.Before;
import org.springframework.mock.web.portlet.MockResourceRequest;

import com.lexmark.domain.Pagination;
import com.lexmark.services.util.PaginationUtil;

public class PaginationUtilTest extends TestCase {
	String[] filterColumns = new String[]{"assetId", "serialNumber", "assetTag", "ipAddress", "physicalLocation1"};
	MockResourceRequest request;
	
	@Before
	protected void setUp() throws Exception {
		super.setUp();
	}


	public void testGetPainationFromRequest() {
		request = new MockResourceRequest();
		request.setParameter(PaginationUtil.SEARCH_CRITERIAS, "col1^abc" + PaginationUtil.SEARCH_CRITERIAS_SEPERATOR + "col2^1" + PaginationUtil.SEARCH_CRITERIAS_SEPERATOR + "col2^3");
		request.setParameter(PaginationUtil.FILTER_CRITERIAS, ",,tag,,");
		request.setParameter(PaginationUtil.POS_START, "1234");
		request.setParameter(PaginationUtil.ORDER_BY, "2");
		request.setParameter(PaginationUtil.DIRECTION, "des");
		
		Pagination p = PaginationUtil.getPainationFromRequest(request, filterColumns, "");
		Assert.assertTrue(p.getSearchCriteria().size()==2);
		Assert.assertTrue(p.getFilterCriteria().size()==1);
		String l1 = (String) p.getSearchCriteria().get("col1");
		Assert.assertNotNull(l1);
		Assert.assertEquals("abc", l1);
		
		List<String> l2 = (List<String>) p.getSearchCriteria().get("col2");
		Assert.assertNotNull(l2);
		Assert.assertEquals(2, l2.size());
		
	}

}
