package com.lexmark.service.api;

import java.util.List;
import java.util.Map;

import com.googlecode.ehcache.annotations.Cacheable;
import com.lexmark.contract.GeographyStateContract;
import com.lexmark.result.GeographyListResult;

public interface GeographyService {
	@Cacheable(cacheName="retrieveCountryDetails", keyGeneratorName="myKeyGenerator")
	public GeographyListResult getCountryDetails() throws Exception;
	@Cacheable(cacheName="retrieveStateDetails", keyGeneratorName="myKeyGenerator")
	public GeographyListResult getStateDetails(String countryCode) throws Exception;	
	@Cacheable(cacheName="retrieveAllStateDetails", keyGeneratorName="myKeyGenerator")
	public Map<String, List<GeographyStateContract>> getAllStateDetails() throws Exception;
}
