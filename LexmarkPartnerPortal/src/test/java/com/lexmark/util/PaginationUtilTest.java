package com.lexmark.util;

import java.util.List;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.portlet.MockResourceRequest;

import com.lexmark.domain.Pagination;

public class PaginationUtilTest {
	private String[] sortedColumns;
	private String[] filterColumns;
	@Before
	public void setUp() throws Exception {
		sortedColumns = new String[]{"sortColumnOne","sortColumnTwo","sortColumnThree","sortColumnFour"};
	}
	
	@Test
	public void testGetPainationFromRequest(){
		filterColumns = new String[]{"filterColumnOne","filterColumnTwo","filterColumnThree","filterColumnFour"};;
		String filterCriterias = "filterValueOne,filterValueTwo,filterValueThree,filterValueFour";
		String searchCriterias = "searchColumnOne^searchValueOne"+PaginationUtil.SEARCH_CRITERIAS_SEPERATOR
			+"searchColumnTwo^searchValueTwo"+PaginationUtil.SEARCH_CRITERIAS_SEPERATOR+"searchColumnThree^searchValueThree"
			+PaginationUtil.SEARCH_CRITERIAS_SEPERATOR+"searchColumnFour^searchValueFour"+PaginationUtil.SEARCH_CRITERIAS_SEPERATOR+"noSeperator";
		MockResourceRequest request = new MockResourceRequest();
		request.setParameter(PaginationUtil.FILTER_CRITERIAS, filterCriterias);
		request.setParameter(PaginationUtil.SEARCH_CRITERIAS, searchCriterias);
		request.setParameter(PaginationUtil.ORDER_BY, "2");
		request.setParameter(PaginationUtil.DIRECTION, Pagination.ORDER_DESC);
		Pagination page = PaginationUtil.getPainationFromRequest(request, sortedColumns, filterColumns);
		assertTrue(Pagination.ORDER_DESC.equals(page.getDirection()));
		assertTrue("sortColumnThree".equals(page.getOrderBy()));
		assertTrue("searchValueThree".equals(page.getSearchCriteria().get("searchColumnThree")));
		assertTrue("filterValueTwo".equals(page.getFilterCriteria().get("filterColumnTwo")));
	}
	
	@Test
	public void testGetPainationFromRequest1(){
		filterColumns = new String[]{"filterColumnOne","filterColumnTwo","filterColumnThree","filterColumnFour"};;
		String filterCriterias = "filterValueOne,filterValueTwo,filterValueThree,filterValueFour";
		String searchCriterias = "searchColumnOne^searchValueOne"+PaginationUtil.SEARCH_CRITERIAS_SEPERATOR
			+"searchColumnTwo^searchValueTwo"+PaginationUtil.SEARCH_CRITERIAS_SEPERATOR+"searchColumnThree^searchValueThree"
			+PaginationUtil.SEARCH_CRITERIAS_SEPERATOR+"searchColumnFour^searchValueFour"+PaginationUtil.SEARCH_CRITERIAS_SEPERATOR+"noSeperator";
		MockResourceRequest request = new MockResourceRequest();
		request.setParameter(PaginationUtil.FILTER_CRITERIAS, filterCriterias);
		request.setParameter(PaginationUtil.SEARCH_CRITERIAS, searchCriterias);
		request.setParameter(PaginationUtil.ORDER_BY, "1");
		request.setParameter(PaginationUtil.DIRECTION, Pagination.ORDER_ASC);
		Pagination page = PaginationUtil.getPainationFromRequest(request, sortedColumns, filterColumns);
		assertTrue(Pagination.ORDER_ASC.equals(page.getDirection()));
		assertTrue("sortColumnTwo".equals(page.getOrderBy()));
		assertTrue("searchValueThree".equals(page.getSearchCriteria().get("searchColumnThree")));
		assertTrue("filterValueTwo".equals(page.getFilterCriteria().get("filterColumnTwo")));
	}
	
	@Test
	public void testGetPainationFromRequestSearchCriteriaForOneKey(){
		filterColumns = new String[]{"filterColumnOne","filterColumnTwo","filterColumnThree","filterColumnFour"};;
		String filterCriterias = "filterValueOne,filterValueTwo,filterValueThree,filterValueFour";
		String searchCriterias = "searchColumnOne^searchValueOne"+PaginationUtil.SEARCH_CRITERIAS_SEPERATOR
			+"searchColumnTwo^searchValueTwo"+PaginationUtil.SEARCH_CRITERIAS_SEPERATOR+"searchColumnThree^searchValueThree"
			+PaginationUtil.SEARCH_CRITERIAS_SEPERATOR+"searchColumnFour^searchValueFour"+PaginationUtil.SEARCH_CRITERIAS_SEPERATOR+"searchColumnFour^searchValueFive";
		MockResourceRequest request = new MockResourceRequest();
		request.setParameter(PaginationUtil.FILTER_CRITERIAS, filterCriterias);
		request.setParameter(PaginationUtil.SEARCH_CRITERIAS, searchCriterias);
		request.setParameter(PaginationUtil.ORDER_BY, "3");
		request.setParameter(PaginationUtil.DIRECTION, Pagination.ORDER_DESC);
		
		Pagination page = PaginationUtil.getPainationFromRequest(request, sortedColumns, filterColumns);
		List<String> searchCriteriaForOneKey = null;
		searchCriteriaForOneKey = (List<String>)page.getSearchCriteria().get("searchColumnFour");
		assertTrue(Pagination.ORDER_DESC.equals(page.getDirection()));
		assertTrue("sortColumnFour".equals(page.getOrderBy()));
		assertTrue(searchCriteriaForOneKey.size() == 2);
		assertTrue("searchValueFour".equals(searchCriteriaForOneKey.get(0)));
		assertTrue("searchValueFive".equals(searchCriteriaForOneKey.get(1)));
		assertTrue("filterValueTwo".equals(page.getFilterCriteria().get("filterColumnTwo")));
	}
	@Test
	public void testGetPainationFromRequestSearchCriteriaForOneKey2(){
		filterColumns = new String[]{"filterColumnOne","filterColumnTwo","filterColumnThree","filterColumnFour"};;
		String filterCriterias = "filterValueOne,filterValueTwo,filterValueThree,filterValueFour";
		String searchCriterias = "searchColumnOne^searchValueOne"+PaginationUtil.SEARCH_CRITERIAS_SEPERATOR
			+"searchColumnTwo^searchValueTwo"+PaginationUtil.SEARCH_CRITERIAS_SEPERATOR+"searchColumnThree^searchValueThree"
			+PaginationUtil.SEARCH_CRITERIAS_SEPERATOR+"searchColumnFour^searchValueFour"+PaginationUtil.SEARCH_CRITERIAS_SEPERATOR+"searchColumnFour^searchValueFive";
		MockResourceRequest request = new MockResourceRequest();
		request.setParameter(PaginationUtil.FILTER_CRITERIAS, filterCriterias);
		request.setParameter(PaginationUtil.SEARCH_CRITERIAS, searchCriterias);
		request.setParameter(PaginationUtil.ORDER_BY, "0");
		request.setParameter(PaginationUtil.DIRECTION, Pagination.ORDER_ASC);
		
		Pagination page = PaginationUtil.getPainationFromRequest(request, sortedColumns, filterColumns);
		List<String> searchCriteriaForOneKey = null;
		searchCriteriaForOneKey = (List<String>)page.getSearchCriteria().get("searchColumnFour");
		assertTrue(Pagination.ORDER_ASC.equals(page.getDirection()));
		assertTrue("sortColumnOne".equals(page.getOrderBy()));
		assertTrue(searchCriteriaForOneKey.size() == 2);
		assertTrue("searchValueFour".equals(searchCriteriaForOneKey.get(0)));
		assertTrue("searchValueFive".equals(searchCriteriaForOneKey.get(1)));
		assertTrue("filterValueTwo".equals(page.getFilterCriteria().get("filterColumnTwo")));
	}

}
