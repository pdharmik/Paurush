package com.lexmark.domain;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/*
 *  Pagination is used to pass to the search service to search the  
 */
public class Pagination implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
	public static final String  ORDER_ASC = "ASCENDING";
	public static final String  ORDER_DESC = "DESCENDING";
	public static final int DEFAULT_COUNT = 40;
	public static final int DEFAULT_START_POSITION = 0;

	private Map<String, Object>  searchCriteria = new HashMap<String, Object>();
	private Map<String, Object>  filterCriteria = new HashMap<String, Object>();
	private String orderBy;
	private String direction;
	private int startPosition = DEFAULT_START_POSITION;
	private int count = DEFAULT_COUNT;
	
	public Map<String, Object> getSearchCriteria() {
		return searchCriteria;
	}
	public void setSearchCriteria(Map<String, Object> searchCriteria) {
		this.searchCriteria = searchCriteria;
	}
	public Map<String, Object> getFilterCriteria() {
		return filterCriteria;
	}
	public void setFilterCriteria(Map<String, Object> filterCriteria) {
		this.filterCriteria = filterCriteria;
	}
	
	public String getOrderBy() {
		return orderBy;
	}
	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}
	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}
	public int getStartPosition() {
		return startPosition;
	}
	public void setStartPosition(int startPosition) {
		this.startPosition = startPosition;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}

	
	
}
