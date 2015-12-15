package com.lexmark.service.util;

import com.lexmark.contract.api.SearchContractBase;
import com.lexmark.domain.Pagination;

public class SearchContractUtil {
	public static void copyPaginationToContract(Pagination pagination, SearchContractBase contract) {
		contract.setFilterCriteria(pagination.getFilterCriteria());
		contract.setSearchCriteria(pagination.getSearchCriteria());
		contract.setStartRecordNumber(pagination.getStartPosition());
		contract.setIncrement(pagination.getCount());
		contract.setNewQueryIndicator(pagination.getStartPosition()==0);
		if(pagination.getOrderBy()!=null)
		{
			contract.getSortCriteria().put(pagination.getOrderBy(), pagination.getDirection());
		}
	}
}
