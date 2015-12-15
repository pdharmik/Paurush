package com.lexmark.service.impl.mock;

import com.lexmark.contract.ProductImageContract;
import com.lexmark.result.ProductImageResult;
import com.lexmark.service.api.ProductImageService;

public class ProductImageServiceImpl implements ProductImageService {

	public ProductImageResult retrieveProductImageUrl(
			ProductImageContract contract) throws Exception {
		String productImageUrl = "https://tportal.lexmark.com/LexmarkServicesPortal/images/printer_na_color.png";
		ProductImageResult productImageResult = new ProductImageResult();
		productImageResult.setProductImageUrl(productImageUrl);
		return productImageResult;
	}
	
	public ProductImageResult retrieveProductImageUrl(
			ProductImageContract contract,String portalContextPath) throws Exception {
		String productImageUrl = "https://tportal.lexmark.com/LexmarkServicesPortal/images/printer_na_color.png";
		ProductImageResult productImageResult = new ProductImageResult();
		productImageResult.setProductImageUrl(productImageUrl);
		return productImageResult;
	}


}
