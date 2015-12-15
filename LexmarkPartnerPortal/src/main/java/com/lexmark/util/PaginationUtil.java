/*
 * Copyright@ 2012. Developed for Lexmark International
 * File Name         	: PaginationUtil.java
 * Description     		: MPS
 * 
 * Version         : 1.0
 *
 * Created Date    :2013
 * Modification History:
 *
 *
 */
package com.lexmark.util;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.PortletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;

import com.lexmark.domain.Pagination;
/**
* Class used for Pagination of Grids 
*
* @version 1.10 10 Nov 2014 
* @author Wipro technologies  
*/
public class PaginationUtil {
	/** variable count*/
	public static final String COUNT = "count";
	/** variable position start*/
	public static final String POS_START = "posStart";
	/** variable direction*/
	public static final String DIRECTION = "direction";
	/** variable order by*/
	public static final String ORDER_BY = "orderBy";
	/** variable filter criterias*/
	public static final String FILTER_CRITERIAS = "filterCriterias";
	/** variable filter criteria separator*/
	public static final String FILTER_CRITERIAS_SEPERATOR = ",";
	/** variable serarch criteria separator*/
	public static final String SEARCH_CRITERIAS = "searchCriterias";
	/** variable search criteria separator */
	public static final String SEARCH_CRITERIAS_SEPERATOR = "__";
	/** variable search criteria value separator */
	public static final String SEARCH_CRITERIA_NAME_VALUE_SPERATOR = "^";
	// Utility class can not be instantiated
	/** constacnt variable*/
	public static final String decodedAnd = "&";
	/** constacnt variable*/
	public static final String encodedAnd = "encodedAnd";
	/**
	 * Constructor default 
	 * */
	private PaginationUtil() {
	}
 
	
	/**
	 * @param request portletrequest
	 * @param sortedColumns column names
	 * @param filterColumns column names
	 * @return pagination object
	 * */
	public static Pagination getPainationFromRequest(PortletRequest request, String[] sortedColumns,
			String[] filterColumns) {
		final Pagination page = new Pagination();

		addFilterCriterias(request, filterColumns, page);
		addSearchCriterias(request, page);
		addSortField(request, sortedColumns, page);

		int posStart = NumberUtils.isNumber(request.getParameter(POS_START)) ? Integer.parseInt(request
				.getParameter(POS_START)) : 0;
		page.setStartPosition(posStart);
		page.setCount(Pagination.DEFAULT_COUNT);

		return page;
	}
	/**
	 * @param request portletrequest
	 * @param sortedColumns sorted columns
	 * @param page pagination
	 * */
	private static void addSortField(PortletRequest request, String[] sortedColumns, final Pagination page) {
		int orderByIndex = NumberUtils.isNumber(request.getParameter(ORDER_BY)) ? Integer.parseInt(request
				.getParameter(ORDER_BY)) : 0;
		String orderByField = sortedColumns[orderByIndex];
		page.setOrderBy(orderByField);

		String direction = request.getParameter(DIRECTION);
		if (direction == null || !direction.substring(0, 3).equalsIgnoreCase(Pagination.ORDER_DESC.substring(0, 3))) {
			page.setDirection(Pagination.ORDER_ASC);
		} else {
			page.setDirection(Pagination.ORDER_DESC);
		}
	}
	/**
	 * @param request portletrequest
	 * @param page pagination object
	 * 
	 * */
	@SuppressWarnings("unchecked")
	private static void addSearchCriterias(PortletRequest request, final Pagination page) {
		String searchCriterias = request.getParameter(SEARCH_CRITERIAS);
		if (StringUtils.isNotEmpty(searchCriterias)) {
			String[] values = searchCriterias.split(SEARCH_CRITERIAS_SEPERATOR);
			int len=values.length;
			for (int i = 0; i < len; i++) {
				String value = values[i];
				if (value == null || value.indexOf(SEARCH_CRITERIA_NAME_VALUE_SPERATOR) < 0){
					continue;
				}

				String searchKey = value.substring(0, value.indexOf(SEARCH_CRITERIA_NAME_VALUE_SPERATOR));
				String searchValue = value.substring(value.indexOf(SEARCH_CRITERIA_NAME_VALUE_SPERATOR) + 1);
				searchValue = searchValue == null ? searchValue : searchValue.trim();
				Object valueObject = page.getSearchCriteria().get(searchKey);
				if (valueObject == null) {
					page.getSearchCriteria().put(searchKey, searchValue);
				} else {
					List<String> searchCriteriaForOneKey = null;
					if (valueObject instanceof String) {
						searchCriteriaForOneKey = new ArrayList<String>();
						searchCriteriaForOneKey.add((String) valueObject);
					} else {
						searchCriteriaForOneKey = (List<String>) page.getSearchCriteria().get(searchKey);
					}
					searchCriteriaForOneKey.add(searchValue);
					page.getSearchCriteria().put(searchKey, searchCriteriaForOneKey);
				}
			}
		}
	}
	/**
	 *@param request portletrequest 
	 * @param filterColumns filter columns
	 * @param page pagination
	 * */
	private static void addFilterCriterias(PortletRequest request, String[] filterColumns, final Pagination page) {
		String filterCriterias = request.getParameter(FILTER_CRITERIAS);
		if(null !=filterCriterias || StringUtils.isNotEmpty(filterCriterias) ){
		if(filterCriterias.contains(encodedAnd)){
			filterCriterias=filterCriterias.replaceAll(encodedAnd, decodedAnd);
		}
		}
		if (StringUtils.isNotEmpty(filterCriterias)) {
			int i = 0;
			for (String value : filterCriterias.split(FILTER_CRITERIAS_SEPERATOR)) {
				value = value.trim();
				if (StringUtils.isNotEmpty(value) && i < filterColumns.length) {
					page.getFilterCriteria().put(filterColumns[i], value);
				}
				i++;
			}
		}
	}
}
