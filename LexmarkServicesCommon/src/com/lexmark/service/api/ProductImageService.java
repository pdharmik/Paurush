package com.lexmark.service.api;

import com.googlecode.ehcache.annotations.Cacheable;
import com.lexmark.contract.ProductImageContract;
import com.lexmark.result.ProductImageResult;

public interface ProductImageService {
	
	//@Cacheable(cacheName="productImageUrl", keyGeneratorName="myKeyGenerator")
	public ProductImageResult retrieveProductImageUrl (ProductImageContract contract)throws Exception;
	public ProductImageResult retrieveProductImageUrl (ProductImageContract contract,String portalContextPath)throws Exception;
	
}
