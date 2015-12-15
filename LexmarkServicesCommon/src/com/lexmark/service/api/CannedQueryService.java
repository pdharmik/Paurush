package com.lexmark.service.api;

import com.googlecode.ehcache.annotations.Cacheable;
import com.lexmark.contract.CannedQueryContract;
import com.lexmark.result.CannedQueriesResult;

public interface CannedQueryService {
	@Cacheable(cacheName="cannedQueries", keyGeneratorName="myKeyGenerator")
	public CannedQueriesResult retrieveCannedQueries (CannedQueryContract contract);
}
