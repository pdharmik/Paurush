package com.lexmark.service.impl.real.jdbc;

import com.lexmark.constants.LexmarkConstants;
import com.lexmark.contract.ProductImageContract;
import com.lexmark.dao.ProductImage;
import com.lexmark.result.ProductImageResult;
import com.lexmark.service.api.ProductImageService;

public class ProductImageServiceImpl implements ProductImageService {

	public ProductImageResult retrieveProductImageUrl(
			ProductImageContract contract) throws Exception {
		String productImageUrl = null;
		ProductImageResult productImageResult = new ProductImageResult();
		if (contract.getPartNumber() != null && !contract.getPartNumber().equals("")){
			productImageUrl = ProductImage.retrieveProductImageUrl(contract.getPartNumber());
			if (productImageUrl == null || productImageUrl.equals("")){
				//Set product not found image
				productImageUrl = LexmarkConstants.PRODUCT_NOT_FOUND_IMAGE_URL;									
			}
		}else{
			//Set product not found image
			productImageUrl = LexmarkConstants.PRODUCT_NOT_FOUND_IMAGE_URL;									
		}
		productImageResult.setProductImageUrl(productImageUrl);
		return productImageResult;
	}

	public ProductImageResult retrieveProductImageUrl(
			ProductImageContract contract, String portalContextPath) throws Exception {
		String productImageUrl = null;
		ProductImageResult productImageResult = new ProductImageResult();
		if (contract.getPartNumber() != null && !contract.getPartNumber().equals("")){
			productImageUrl = ProductImage.retrieveProductImageUrl(contract.getPartNumber());
			if (productImageUrl == null || productImageUrl.equals("")){
				//Set product not found image
				productImageUrl = portalContextPath + LexmarkConstants.PRODUCT_NOT_FOUND_IMAGE_URL2;									
			}
		}else{
			//Set product not found image
			productImageUrl = portalContextPath + LexmarkConstants.PRODUCT_NOT_FOUND_IMAGE_URL2;									
		}
		productImageResult.setProductImageUrl(productImageUrl);
		return productImageResult;
	}

}
