package com.lexmark.result;

import java.util.List;

import com.lexmark.domain.CannedQuery;

public class CannedQueriesResult {
	private List<CannedQuery> cannedQueries;

	/**
	 * @param cannedQueries the cannedQueries to set
	 */
	public void setCannedQueries(List<CannedQuery> cannedQueries) {
		this.cannedQueries = cannedQueries;
	}

	/**
	 * @return the cannedQueries
	 */
	public List<CannedQuery> getCannedQueries() {
		return cannedQueries;
	}
}
