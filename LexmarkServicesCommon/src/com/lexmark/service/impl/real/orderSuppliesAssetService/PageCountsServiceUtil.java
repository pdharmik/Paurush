package com.lexmark.service.impl.real.orderSuppliesAssetService;

import java.util.ArrayList;
import java.util.List;

import com.lexmark.domain.PageCounts;
import com.lexmark.service.impl.real.domain.AssetDetailsPageCountsDO;
import com.lexmark.service.impl.real.domain.AssetDetailsPageCountsReadingsDO;
import com.lexmark.service.impl.real.domain.OrderSuppliesAssetDetailsPageCountsDO;
import com.lexmark.util.LangUtil;


public class PageCountsServiceUtil {
	
	public static List<PageCounts> populatePageCounts(OrderSuppliesAssetDetailsPageCountsDO pageCountsDO) {
		
		List<PageCounts> pageCountsList = new ArrayList<PageCounts>();
		
		List<AssetDetailsPageCountsDO> pageCountsDOs = pageCountsDO.getPageCounts();
		
		if(pageCountsDO != null && LangUtil.isNotEmpty(pageCountsDOs)) {
		  	for (AssetDetailsPageCountsDO pageCountDo : pageCountsDOs) {
	            PageCounts pageCounts = new PageCounts();
	            pageCounts.setName(pageCountDo.getName());
	            AssetDetailsPageCountsReadingsDO latestReading = LangUtil.first(pageCountDo.getLatestReadings());
	            if (latestReading != null) {
	                pageCounts.setCount(latestReading.getReading());
	                pageCounts.setDate(latestReading.getTimestamp());
	            }
	            pageCountsList.add(pageCounts);
	        }
		}
		
		return pageCountsList;
	}

}
