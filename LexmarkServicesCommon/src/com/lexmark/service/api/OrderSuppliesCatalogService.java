package com.lexmark.service.api;

import com.lexmark.contract.CatalogListContract;
import com.lexmark.result.CatalogListResult;

public interface OrderSuppliesCatalogService {
    
    public CatalogListResult retrieveCatalogFieldList(CatalogListContract catalogListContract) throws Exception;//Retrieve the Product type, Product type and Part type
    
    public CatalogListResult retrieveCatalogList(CatalogListContract catalogListContract) throws Exception;//Retrieve the list of all products

	public CatalogListResult retrieveCatalogListWithContractNumber(CatalogListContract contract) throws Exception;

	public CatalogListResult retrievePrinterTypesB2B(CatalogListContract contract) throws Exception;

	public CatalogListResult retrieveAccessoriesB2b(CatalogListContract contract) throws Exception;
}