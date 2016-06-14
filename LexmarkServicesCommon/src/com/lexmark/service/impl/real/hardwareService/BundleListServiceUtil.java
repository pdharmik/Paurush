package com.lexmark.service.impl.real.hardwareService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.lexmark.domain.Bundle;
import com.lexmark.domain.Part;
import com.lexmark.service.impl.real.domain.HardwareCatalogPaymentTypeDo;
import com.lexmark.service.impl.real.domain.HardwareSapCatalogDo;
import com.lexmark.util.LangUtil;

public class BundleListServiceUtil {

	public static List<Bundle> convertPickSapCatalogDoToHardwareCatalog(List<HardwareSapCatalogDo> catalogDo) {
		List<Bundle> bundles = new ArrayList<Bundle>();
		
		if(LangUtil.isNotEmpty(catalogDo)) {
			Bundle bundle;
			
			for (HardwareSapCatalogDo pickSapCatalogDo : catalogDo) {
				if(pickSapCatalogDo!=null){
					bundle = new Bundle();
					
					bundle.setBundleId(pickSapCatalogDo.getBundleId());
					bundle.setSapLineID(pickSapCatalogDo.getSapLine());
					bundle.setContractNumber(pickSapCatalogDo.getSapContract());
					bundle.setDescription(pickSapCatalogDo.getPartDescription());
					bundle.setSalesOrg(pickSapCatalogDo.getSapSalesOrg());
					bundle.setBundleProductId(pickSapCatalogDo.getBundleProductId());
					bundle.setBundleMaterialID(pickSapCatalogDo.getBundleMaterialID());
					bundle.setContractLineItemId(pickSapCatalogDo.getContractLineItemId());
					bundle.setAssetId(pickSapCatalogDo.getAssetId());
					bundle.setMpsDescription(pickSapCatalogDo.getMpsDescription());
					bundle.setBillingModel(pickSapCatalogDo.getBillingModel());
					bundle.setPartList(populateParts(pickSapCatalogDo));
					bundle.setConfigId(pickSapCatalogDo.getConfigId());
					bundle.setPartTypeB2b(pickSapCatalogDo.getPartTypeB2b());
					bundle.setB2bMarketingName(pickSapCatalogDo.getB2bMarketingName());
					bundle.setB2bMarketingShortDesc(pickSapCatalogDo.getB2bMarketingShortDesc());
					bundle.setB2bModel(pickSapCatalogDo.getB2bModel());
					bundle.setB2bMfgBrand(pickSapCatalogDo.getB2bMfgBrand());
					bundles.add(bundle);
				}
			}
		}
		return bundles;
	}

	private static List<Part> populateParts(HardwareSapCatalogDo pickSapCatalogDo) {
		List<Part> parts = new ArrayList<Part>();
		if (LangUtil.isNotEmpty(pickSapCatalogDo.getPickSapCatalogPaymentType())) {
			for (HardwareCatalogPaymentTypeDo sapPaymentType : pickSapCatalogDo.getPickSapCatalogPaymentType()) {
				Part part = new Part();
				part.setPartNumber(sapPaymentType.getPartNumber());
				part.setDescription(sapPaymentType.getPartDescription());
				part.setOrderQuantity(sapPaymentType.getQuantity());
				part.setCatalogId(sapPaymentType.getCatalogId());
				part.setSupplyId(sapPaymentType.getMaterialId());
				part.setPaymentTypes(Arrays.asList(sapPaymentType.getBillingModel()));
				part.setPartType(sapPaymentType.getPartType());
				part.setParentLineItemNumber(sapPaymentType.getParentLineItemNumber());
				part.setPartTypeMVFB2b(sapPaymentType.getPartTypeMVFB2b());
				part.setB2bMarketingNameMVF(sapPaymentType.getB2bMarketingNameMVF());
				part.setB2bMarketingShortDescMVF(sapPaymentType.getB2bMarketingShortDescMVF());
				part.setB2bMaterialDescMVF(sapPaymentType.getB2bMaterialDescMVF());
				part.setB2bMfgBrandMVF(sapPaymentType.getB2bMfgBrandMVF());
				part.setProductModel(sapPaymentType.getProductModel());
				parts.add(part);
			}
		}
		return parts;
	}

}
