package com.lexmark.service.api;

import com.googlecode.ehcache.annotations.Cacheable;
import com.lexmark.contract.CatalogListContract;
import com.lexmark.contract.GlobalCatalogListContract;
import com.lexmark.result.CatalogListResult;
import com.lexmark.result.GlobalCatalogListResult;

public interface OrderSuppliesCatalogService {
    
    public CatalogListResult retrieveCatalogFieldList(CatalogListContract catalogListContract) throws Exception;//Retrieve the Product type, Product type and Part type
    
    public CatalogListResult retrieveCatalogList(CatalogListContract catalogListContract) throws Exception;//Retrieve the list of all products

	public CatalogListResult retrieveCatalogListWithContractNumber(CatalogListContract contract) throws Exception;

	public CatalogListResult retrievePrinterTypesB2B(CatalogListContract contract) throws Exception;
	
	@Cacheable(cacheName="retrieveOptionsAndWarranties", keyGeneratorName="myKeyGenerator")
	public CatalogListResult retrieveAccessoriesB2b(CatalogListContract contract) throws Exception;

	public GlobalCatalogListResult retrieveGlobalCatalogListB2B(GlobalCatalogListContract globalCatalogListContract) throws Exception;
}