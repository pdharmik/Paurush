package com.lexmark.service.impl.real.catalogService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.lexmark.domain.ListOfValues;
import com.lexmark.domain.OrderPart;
import com.lexmark.domain.SuppliesSplitterCatalog;
import com.lexmark.service.impl.real.domain.CatalogListDo;
import com.lexmark.service.impl.real.domain.CatalogListWithContractNumberDo;
import com.lexmark.service.impl.real.domain.HardwareCatalogPaymentTypeDo;
import com.lexmark.service.impl.real.domain.HardwareSapCatalogDo;
import com.lexmark.service.impl.real.domain.PartTypeDo;
import com.lexmark.service.impl.real.domain.ProductModelDo;
import com.lexmark.service.impl.real.domain.ProductTypeDo;
import com.lexmark.service.impl.real.domain.SuppliesSplitterCatalogDo;
import com.lexmark.util.LangUtil;

public class AmindOrderSuppliesCatalogConversionUtil {
	
	public static List<ListOfValues> convertProductDoToListOfValues(List<? extends ProductTypeDo> productsDo) {
		List<ListOfValues> lovList = new ArrayList<ListOfValues>();
		
		if (productsDo == null) {
			return lovList;
		}
		
		for (ProductTypeDo productDo: productsDo) {
			ListOfValues lov = new ListOfValues();
			
			if (productDo instanceof PartTypeDo) {
				if (!lovExist(lovList, ((PartTypeDo) productDo).getPartType())) {
					lov.setName("partType");
					lov.setValue(((PartTypeDo) productDo).getPartType());
				}
			} else if (productDo instanceof ProductModelDo) {
				if (!lovExist(lovList, ((ProductModelDo) productDo).getProductModel())) {
					lov.setName("productModel");
					lov.setValue(((ProductModelDo) productDo).getProductModel());
				}
			} else {
				if (!lovExist(lovList, productDo.getProductType())) {
					lov.setName("productType");
					lov.setValue(productDo.getProductType());
				}
			}
			
			if (StringUtils.isNotBlank(lov.toString())) {
				lovList.add(lov);
			}
		}
		
		return lovList;
	}
	
	public static List<ListOfValues> convertProductDoToListOfValuesB2B(List<? extends ProductTypeDo> productsDo) {
		List<ListOfValues> lovList = new ArrayList<ListOfValues>();
		
		if (productsDo == null) {
			return lovList;
		}
		
		for (ProductTypeDo productDo: productsDo) {
			ListOfValues lov = new ListOfValues();
			
			if (productDo instanceof PartTypeDo) {
				if (!lovExist(lovList, ((PartTypeDo) productDo).getPartType())) {
					lov.setName("partType");
					lov.setValue(((PartTypeDo) productDo).getPartType());
				}
			} else if (productDo instanceof ProductModelDo) {
				if (!lovExist(lovList, ((ProductModelDo) productDo).getProductModel())) {
					lov.setName("productModel");
					lov.setValue(((ProductModelDo) productDo).getProductModel());
				}
			} else {
				if (!lovExist(lovList, productDo.getProductType())) {
					lov.setName("productType");
					lov.setValue(productDo.getProductType());
				}
			}
			
			if (StringUtils.isNotBlank(lov.toString())) {
				lovList.add(lov);
			}
		}
		
		return lovList;
	}
	
	
	
	public static List<ListOfValues> convertHardwareCatalogDoToListOfValues(List<HardwareSapCatalogDo> hardwareSapCatalogDos, String productType, String productModel){
	List<ListOfValues> lovList = new ArrayList<ListOfValues>();
		
		if (hardwareSapCatalogDos == null) {
			return lovList;
		}
		
		ListOfValues lov = new ListOfValues();
		
		for (HardwareSapCatalogDo hardwareSapCatalogDo : hardwareSapCatalogDos) {
			
			if(hardwareSapCatalogDo.getPickSapCatalogPaymentType() != null) {
				
				if(!"Hardware Bundles".equalsIgnoreCase(hardwareSapCatalogDo.getType())) {
					
				if(StringUtils.isNotBlank(productType)) {
					if(!lovExist(lovList,hardwareSapCatalogDo.getProductModel()) && StringUtils.isNotEmpty(hardwareSapCatalogDo.getProductModel())) {
						lov = new ListOfValues();
						lov.setName("productModel");
						lov.setValue(hardwareSapCatalogDo.getProductModel());
						lovList.add(lov);
					}
				} else {
					if(!lovExist(lovList,hardwareSapCatalogDo.getProductType()) && StringUtils.isNotEmpty(hardwareSapCatalogDo.getProductType())) {
						lov = new ListOfValues();
						lov.setName("productType");
						lov.setValue(hardwareSapCatalogDo.getProductType());
						lovList.add(lov);
						}
					}
				}
				for (HardwareCatalogPaymentTypeDo hardwareChild : hardwareSapCatalogDo.getPickSapCatalogPaymentType()) {
					if(StringUtils.isNotBlank(productType)) {
						if(!lovExist(lovList,hardwareChild.getProductModel()) && StringUtils.isNotEmpty(hardwareChild.getProductModel())) {
							lov = new ListOfValues();
							lov.setName("productModel");
							lov.setValue(hardwareChild.getProductModel());
							lovList.add(lov);
						}
					} else {
						if(!lovExist(lovList,hardwareChild.getProductType()) && StringUtils.isNotEmpty(hardwareChild.getProductType())) {
							lov = new ListOfValues();
							lov.setName("productType");
							lov.setValue(hardwareChild.getProductType());
							lovList.add(lov);
						}
					}
				}
			}
			else {
				if("Hardware Components".equalsIgnoreCase(hardwareSapCatalogDo.getType()) && ("Options".equalsIgnoreCase(hardwareSapCatalogDo.getMaterialLine()) || "Service Parts".equalsIgnoreCase(hardwareSapCatalogDo.getMaterialLine()))) {
					if (StringUtils.isNotBlank(productType)) {
						if (!lovExist(lovList,hardwareSapCatalogDo.getProductModel())&& StringUtils.isNotEmpty(hardwareSapCatalogDo.getProductModel())) {
							lov = new ListOfValues();
							lov.setName("productModel");
							lov.setValue(hardwareSapCatalogDo.getProductModel());
							lovList.add(lov);
						}
					} else {
						if (!lovExist(lovList,hardwareSapCatalogDo.getProductType()) && StringUtils.isNotEmpty(hardwareSapCatalogDo.getProductType())) {
							lov = new ListOfValues();
							lov.setName("productType");
							lov.setValue(hardwareSapCatalogDo.getProductType());
							lovList.add(lov);
						}
					}
				}
			}

		}
		
		return lovList;
	}
	
	public static List<ListOfValues> convertHardwareCatalogDoToListOfValuesB2B(List<HardwareSapCatalogDo> hardwareSapCatalogDos, String productType, String productModel){
		List<ListOfValues> lovList = new ArrayList<ListOfValues>();
			
			if (hardwareSapCatalogDos == null) {
				return lovList;
			}
			
			ListOfValues lov = new ListOfValues();
			
			for (HardwareSapCatalogDo hardwareSapCatalogDo : hardwareSapCatalogDos) {
				
				if(hardwareSapCatalogDo.getPickSapCatalogPaymentType() != null) {
					
					if(!"Hardware Bundles".equalsIgnoreCase(hardwareSapCatalogDo.getType())) {
						
					if(StringUtils.isNotBlank(productType)) {
						if(!lovExist(lovList,hardwareSapCatalogDo.getProductModel()) && StringUtils.isNotEmpty(hardwareSapCatalogDo.getProductModel())) {
							lov = new ListOfValues();
							lov.setName("productModel");
							lov.setValue(hardwareSapCatalogDo.getProductModel());
							lovList.add(lov);
						}
					} else {
//						if(!lovExist(lovList,hardwareSapCatalogDo.getProductType()) && StringUtils.isNotEmpty(hardwareSapCatalogDo.getProductType())) {
//							lov = new ListOfValues();
//							lov.setName("productType");
//							lov.setValue(hardwareSapCatalogDo.getPartTypeB2b()); 
//							lovList.add(lov);
//							}
						if(!lovExist(lovList,hardwareSapCatalogDo.getPartTypeB2b()) && StringUtils.isNotEmpty(hardwareSapCatalogDo.getPartTypeB2b())) {
							lov = new ListOfValues();
							lov.setName("productType");
							lov.setValue(hardwareSapCatalogDo.getPartTypeB2b()); 
							lovList.add(lov);
						}
						}
					}
					for (HardwareCatalogPaymentTypeDo hardwareChild : hardwareSapCatalogDo.getPickSapCatalogPaymentType()) {
						if(StringUtils.isNotBlank(productType)) {
							if(!lovExist(lovList,hardwareChild.getProductModel()) && StringUtils.isNotEmpty(hardwareChild.getProductModel())) {
								lov = new ListOfValues();
								lov.setName("productModel");
								lov.setValue(hardwareChild.getProductModel());
								lovList.add(lov);
							}
						} else {
//							if(!lovExist(lovList,hardwareChild.getProductType()) && StringUtils.isNotEmpty(hardwareChild.getProductType())) {
//								lov = new ListOfValues();
//								lov.setName("productType");
//								lov.setValue(hardwareChild.getPartTypeMVFB2b());
//								lovList.add(lov);
//							}
							if(!lovExist(lovList,hardwareChild.getPartTypeMVFB2b()) && StringUtils.isNotEmpty(hardwareChild.getPartTypeMVFB2b())) {
								lov = new ListOfValues();
								lov.setName("productType");
								lov.setValue(hardwareChild.getPartTypeMVFB2b());
								lovList.add(lov);
							}
						}
					}
				}
				else {
					if("Hardware Components".equalsIgnoreCase(hardwareSapCatalogDo.getType()) && ("Options".equalsIgnoreCase(hardwareSapCatalogDo.getMaterialLine()) || "Service Parts".equalsIgnoreCase(hardwareSapCatalogDo.getMaterialLine()))) {
						if (StringUtils.isNotBlank(productType)) {
							if (!lovExist(lovList,hardwareSapCatalogDo.getProductModel())&& StringUtils.isNotEmpty(hardwareSapCatalogDo.getProductModel())) {
								lov = new ListOfValues();
								lov.setName("productModel");
								lov.setValue(hardwareSapCatalogDo.getProductModel());
								lovList.add(lov);
							}
						} else {
							if (!lovExist(lovList,hardwareSapCatalogDo.getProductType()) && StringUtils.isNotEmpty(hardwareSapCatalogDo.getProductType())) {
								lov = new ListOfValues();
								lov.setName("productType");
								lov.setValue(hardwareSapCatalogDo.getPartTypeB2b());
								lovList.add(lov);
							}
						}
					}
				}

			}
			
			return lovList;
		}

	private static boolean lovExist(List<ListOfValues> list, String lovType) {	
		if(list != null){
			for (ListOfValues lov : list) {
				if(lov != null && lov.getValue() != null && lov.getValue().equalsIgnoreCase(lovType)) {
					return true;
				}
				
			}	
		}
	
		return false;
	}
	
	public static List<OrderPart> convertPartsDoListToPartsList(List<CatalogListDo> partsDoList) {
		List<OrderPart> orderParts = new ArrayList<OrderPart>();
		
		if (partsDoList == null) {
			return orderParts;
		}

		for (CatalogListDo partDo : partsDoList) {
			OrderPart part = new OrderPart();
			part.setPartNumber(partDo.getPartNumber());
			part.setDescription(partDo.getDescription());
			part.setPartType(partDo.getPartType());
			part.setImplicitFlag(partDo.getImplicitFlag());
			part.setPrinterPartNo(partDo.getPrinterPartNo());
			part.setYield(partDo.getYield());
			part.setCatalogId(partDo.getCatalogId());
			part.setConsumableType(partDo.getConsumableType());
			part.setModel(partDo.getProductModel());
			part.setSupplyId(partDo.getSupplyId());
			part.setProductId(partDo.getProductId());
			part.setPrice(new BigDecimal(partDo.getPrice()));
			part.setMpsQuantity(partDo.getMpsQuantity());
			orderParts.add(part);
		}

		return orderParts;
	}
	
	public static List<OrderPart> convertPartsDoListToPartsListWithContractNumber(List<CatalogListWithContractNumberDo> partsDoList, boolean hardwareFlag, String paymentType) {
		List<OrderPart> orderParts = new ArrayList<OrderPart>();
		
		if (partsDoList == null) {
			return orderParts;
		}
		
		for (CatalogListWithContractNumberDo partDo : partsDoList) {
			int i=-1;
			OrderPart part = new OrderPart();
			part.setPartNumber(partDo.getPartNumber());
			part.setDescription(partDo.getDescription());
			part.setPartType(partDo.getPartType());
			part.setImplicitFlag(partDo.getImplicitFlag());
			part.setPrinterPartNo(partDo.getPrinterPartNo());
			part.setYield(partDo.getYield());
			part.setCatalogId(partDo.getCatalogId());
			part.setConsumableType(partDo.getConsumableType());
			part.setModel(partDo.getProductModel());
			part.setSupplyId(partDo.getSupplyId());
			part.setProductId(partDo.getProductId());
			part.setPrice(new BigDecimal(partDo.getPrice()));
			part.setPrinterModel(partDo.getPrinterModel());
			part.setPrinterName(partDo.getPrinterName());
			part.setPrinterDescription(partDo.getPrinterDescription());
			part.setMpsQuantity(partDo.getMpsQuantity());
    		if (LangUtil.isNotEmpty(partDo.getSuppliesSplitterCatalog())) {
				
				for (SuppliesSplitterCatalogDo suppCatalog : partDo.getSuppliesSplitterCatalog())
					{
					
					if (paymentType != null && paymentType.equalsIgnoreCase(suppCatalog.getBillingModel())) {
						i=-1;
						part.setContractNo(suppCatalog.getContractNumber());
						part.setContractLineItemId(suppCatalog.getContractLineItemId());
						part.setBillingModel(suppCatalog.getBillingModel());
						part.setSoldToNumber(suppCatalog.getSoldToNumber());
						part.setSalesOrg(suppCatalog.getSalesOrg());
						part.setProviderContractNo(suppCatalog.getProviderContractNo());
						
						if (!hardwareFlag && ("Consumable Purchase".equalsIgnoreCase(suppCatalog.getBillingModel()))) {
							part.setProviderOrderNumber(suppCatalog.getProviderContractNo());
						}
						
						break;
						
					} else if (LangUtil.isBlank(suppCatalog.getBillingModel())) {
						
						i=  partDo.getSuppliesSplitterCatalog().indexOf(suppCatalog);
					}
				}
				if(i!=-1)
				{
					SuppliesSplitterCatalogDo suppCatalog  = partDo.getSuppliesSplitterCatalog().get(i);
					part.setContractNo(suppCatalog.getContractNumber());
					part.setContractLineItemId(suppCatalog.getContractLineItemId());
					part.setBillingModel(suppCatalog.getBillingModel());
					part.setSoldToNumber(suppCatalog.getSoldToNumber());
					part.setSalesOrg(suppCatalog.getSalesOrg());
					part.setProviderContractNo(suppCatalog.getProviderContractNo());
					
					if (!hardwareFlag && ("Consumable Purchase".equalsIgnoreCase(suppCatalog.getBillingModel()))) {
						part.setProviderOrderNumber(suppCatalog.getProviderContractNo());
					}
					//i=-1;
					
				}
				
			}
			orderParts.add(part);
		}

		return orderParts;
	}
	
	public static List<OrderPart> convertPartsDoListToPartsListWithContractNumberB2b(
			List<CatalogListWithContractNumberDo> partsDoList,
			boolean hardwareFlag, String paymentType) {
		{

			List<OrderPart> orderParts = new ArrayList<OrderPart>();

			if (partsDoList == null) {
				return orderParts;
			}

			for (CatalogListWithContractNumberDo partDo : partsDoList) {

				OrderPart part = new OrderPart();
				part.setPartNumber(partDo.getPartNumber());
				part.setDescription(partDo.getDescription());
				part.setPartType(partDo.getPartType());
				part.setImplicitFlag(partDo.getImplicitFlag());
				part.setPrinterPartNo(partDo.getPrinterPartNo());
				part.setYield(partDo.getYield());
				part.setCatalogId(partDo.getCatalogId());
				part.setConsumableType(partDo.getConsumableType());
				part.setModel(partDo.getProductModel());
				part.setSupplyId(partDo.getSupplyId());
				part.setProductId(partDo.getProductId());
				part.setPrice(new BigDecimal(partDo.getPrice()));
				part.setPrinterModel(partDo.getPrinterModel());
				part.setPrinterName(partDo.getPrinterName());
				part.setPrinterDescription(partDo.getPrinterDescription());
				part.setMpsQuantity(partDo.getMpsQuantity());
				part.setSuppliesCatalogList(populateSuppliesCatalog(partDo));
				orderParts.add(part);
			}
			return orderParts;
		}

	}	
	
	private static List<SuppliesSplitterCatalog> populateSuppliesCatalog(CatalogListWithContractNumberDo partDo){
		List<SuppliesSplitterCatalog> suppCatalogList = new ArrayList<SuppliesSplitterCatalog>();
		if (LangUtil.isNotEmpty(partDo.getSuppliesSplitterCatalog())) {
			
			for (SuppliesSplitterCatalogDo suppCatalog : partDo.getSuppliesSplitterCatalog())
				{
					SuppliesSplitterCatalog suppClg = new SuppliesSplitterCatalog();
					suppClg.setContractNumber(suppCatalog.getContractNumber());
					suppClg.setContractLineItemId(suppCatalog.getContractLineItemId());
					suppClg.setBillingModel(suppCatalog.getBillingModel());
					suppClg.setSoldToNumber(suppCatalog.getSoldToNumber());
					suppClg.setSalesOrg(suppCatalog.getSalesOrg());
					suppClg.setProviderContractNo(suppCatalog.getProviderContractNo());
					suppClg.setProviderOrderNumber(suppCatalog.getProviderContractNo());
					suppCatalogList.add(suppClg);
				}
		}
		return suppCatalogList;
	}
    private static boolean distinctPartNumber(List<OrderPart> orderParts, OrderPart part) {
		for (OrderPart orderPart : orderParts) {
			if (orderPart != null && LangUtil.isNotBlank(orderPart.getPartNumber()) && orderPart.getPartNumber().equalsIgnoreCase(part.getPartNumber())) {
				return true;
			}
		}
		return false;
	}


	public static List<OrderPart> convertPartsDoListToAccessoriesList(List<CatalogListWithContractNumberDo> doList) {
    	List<OrderPart> accessoriesList = new ArrayList<OrderPart>(0);
    	
    	if(doList == null) {
    		return accessoriesList;
    	}
    	for (CatalogListWithContractNumberDo partDo : doList) {
//    		if(accessoriesListUnique(partDo , accessoriesList))   //removing filter for duplicates accessories as per email Defect 17405 and 17227
//    		{
	    		OrderPart accessories = new OrderPart();
	    		accessories.setPartNumber(partDo.getPartNumber());
	    		accessories.setDescription(partDo.getDescription());
	    		accessories.setDeviceType(partDo.getProductType());
	    		accessories.setCatalogId(partDo.getCatalogId());
	    		accessories.setModel(partDo.getProductModel());
	    		accessories.setSupplyId(partDo.getSupplyId());
	    		accessories.setProductId(partDo.getProductId());
				accessories.setProductModel(partDo.getProductModel());
				accessories.setSuppliesModel(partDo.getSuppliesModel());
				accessories.setMpsQuantity(partDo.getMpsQuantity());
	    		if(LangUtil.isNotEmpty(partDo.getSuppliesSplitterCatalog()) && partDo.getSuppliesSplitterCatalog().get(0)!=null) {
					SuppliesSplitterCatalogDo suppCatalog  = partDo.getSuppliesSplitterCatalog().get(0);
					accessories.setContractNo(suppCatalog.getContractNumber());
					accessories.setContractLineItemId(suppCatalog.getContractLineItemId());
					accessories.setBillingModel(suppCatalog.getBillingModel());
					accessories.setSoldToNumber(suppCatalog.getSoldToNumber());
					accessories.setSalesOrg(suppCatalog.getSalesOrg());	
					accessories.setProviderContractNo(suppCatalog.getProviderContractNo());
					
	    		}
				accessoriesList.add(accessories);
			
//    		}
    	}
		return accessoriesList;
	}
	public static List<OrderPart> convertPartsDoListToAccessoriesListB2b(List<CatalogListWithContractNumberDo> doList) {
    	List<OrderPart> accessoriesList = new ArrayList<OrderPart>(0);
    	
    	if(doList == null) {
    		return accessoriesList;
    	}
    	for (CatalogListWithContractNumberDo partDo : doList) {
    		
    		if(accessoriesListUnique(partDo , accessoriesList))
    		{
	    		OrderPart accessories = new OrderPart();
	    		accessories.setPartNumber(partDo.getPartNumber());
	    		accessories.setDescription(partDo.getDescription());
	    		accessories.setDeviceType(partDo.getProductType());
	    		accessories.setCatalogId(partDo.getCatalogId());
	    		accessories.setModel(partDo.getProductModel());
	    		accessories.setSupplyId(partDo.getSupplyId());
	    		accessories.setProductId(partDo.getProductId());
				accessories.setProductModel(partDo.getProductModel());
				accessories.setSuppliesModel(partDo.getSuppliesModel());
	    		if(LangUtil.isNotEmpty(partDo.getSuppliesSplitterCatalog()) && partDo.getSuppliesSplitterCatalog().get(0)!=null) {
					SuppliesSplitterCatalogDo suppCatalog  = partDo.getSuppliesSplitterCatalog().get(0);
					accessories.setContractNo(suppCatalog.getContractNumber());
					accessories.setContractLineItemId(suppCatalog.getContractLineItemId());
					accessories.setBillingModel(suppCatalog.getBillingModel());
					accessories.setSoldToNumber(suppCatalog.getSoldToNumber());
					accessories.setSalesOrg(suppCatalog.getSalesOrg());	
					accessories.setProviderContractNo(suppCatalog.getProviderContractNo());
					
	    		}
				accessoriesList.add(accessories);
			
    		}
    	}
		return accessoriesList;
	}
	
	 private static boolean accessoriesListUnique(CatalogListWithContractNumberDo partDo, List<OrderPart> accessoriesList) {
	    	
		 	
	    	for (OrderPart orderPart : accessoriesList) {
	   	    	if(orderPart.getPartNumber().equalsIgnoreCase(partDo.getPartNumber()) && (orderPart.getProductModel().equalsIgnoreCase(partDo.getProductModel()) || orderPart.getSuppliesModel().equalsIgnoreCase(partDo.getSuppliesModel())))
	    		{
		    		return false;
		    	}
	    	}
	    	return true;
	}

	private AmindOrderSuppliesCatalogConversionUtil() {
	}

	public static List<OrderPart> convertPartsDoListToSuppliesList(List<CatalogListWithContractNumberDo> doList) {
		List<OrderPart> suppliesList = new ArrayList<OrderPart>(0);
		
		if(doList == null) {
			return suppliesList;
		}
		
		for (CatalogListWithContractNumberDo partDo : doList) {
			OrderPart supplies = new OrderPart();
			supplies.setPartNumber(partDo.getPartNumber());
			supplies.setDescription(partDo.getDescription());
//			supplies.setDeviceType(partDo.getd)
			supplies.setModel(partDo.getProductModel());
			supplies.setCatalogId(partDo.getCatalogId());
			supplies.setSupplyId(partDo.getSupplyId());
			supplies.setConsumableType(partDo.getConsumableType());
			supplies.setMpsQuantity(partDo.getMpsQuantity());
			if(LangUtil.isNotEmpty(partDo.getSuppliesSplitterCatalog()) && partDo.getSuppliesSplitterCatalog().get(0)!=null) {
				SuppliesSplitterCatalogDo suppCatalog  = partDo.getSuppliesSplitterCatalog().get(0);
				supplies.setContractNo(suppCatalog.getContractNumber());
				supplies.setContractLineItemId(suppCatalog.getContractLineItemId());
				supplies.setBillingModel(suppCatalog.getBillingModel());
				supplies.setSoldToNumber(suppCatalog.getSoldToNumber());
				supplies.setSalesOrg(suppCatalog.getSalesOrg());
				supplies.setProviderContractNo(suppCatalog.getProviderContractNo());
			}
			suppliesList.add(supplies);
		}
		
		return suppliesList;
	}
}
