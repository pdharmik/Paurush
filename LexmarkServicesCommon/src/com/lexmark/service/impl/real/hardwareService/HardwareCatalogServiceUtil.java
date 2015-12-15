package com.lexmark.service.impl.real.hardwareService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.lexmark.domain.Bundle;
import com.lexmark.domain.HardwareCatalog;
import com.lexmark.domain.Part;
import com.lexmark.service.impl.real.domain.HardwareSapCatalogDo;
import com.lexmark.service.impl.real.domain.HardwareCatalogPaymentTypeDo;
import com.lexmark.util.LangUtil;

public class HardwareCatalogServiceUtil {

	public static HardwareCatalog convertPickSapCatalogDoToHardwareCatalog(List<HardwareSapCatalogDo> catalogDo) {
		
		HardwareCatalog hardwareCatalog = new HardwareCatalog();
		
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
					
					List<Part> parts = new ArrayList<Part>();
					
					if(LangUtil.isNotEmpty(pickSapCatalogDo.getPickSapCatalogPaymentType())) {
						for (HardwareCatalogPaymentTypeDo sapPaymentType : pickSapCatalogDo.getPickSapCatalogPaymentType()) {
							if (sapPaymentType != null) {
								if ("Printers".equalsIgnoreCase(sapPaymentType.getPartType())) {
									hardwareCatalog.setProductModel(sapPaymentType.getProductModel());
									hardwareCatalog.setDescription(sapPaymentType.getPartDescription());
									hardwareCatalog.setDeviceType(sapPaymentType.getProductType());
									hardwareCatalog.setColor_mono(sapPaymentType.getColorMono());
									
									parts.add(0,pupulatePart(sapPaymentType));
								}
								else {
									parts.add(pupulatePart(sapPaymentType));
								}
							}
						}
					}
					
					bundle.setPartList(parts);
					
					bundles.add(bundle);
				}
				
			}

		}
		
		hardwareCatalog.setBundleList(bundles);
		
		return hardwareCatalog;
	}

	private static Part pupulatePart(HardwareCatalogPaymentTypeDo sapPaymentType) {
		Part part = new Part();
		part.setPartNumber(sapPaymentType.getPartNumber());
		part.setDescription(sapPaymentType.getPartDescription());
		part.setOrderQuantity(sapPaymentType.getQuantity());
		part.setCatalogId(sapPaymentType.getCatalogId());
		part.setSupplyId(sapPaymentType.getMaterialId());
		part.setPaymentTypes(Arrays.asList(sapPaymentType.getBillingModel()));
		part.setParentLineItemNumber(sapPaymentType.getParentLineItemNumber());
		return part;
	}

}
