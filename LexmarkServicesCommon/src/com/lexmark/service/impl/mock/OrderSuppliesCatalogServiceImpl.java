package com.lexmark.service.impl.mock;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import com.lexmark.contract.CatalogListContract;
import com.lexmark.domain.ListOfValues;
import com.lexmark.domain.OrderPart;
import com.lexmark.result.CatalogListResult;
import com.lexmark.service.api.OrderSuppliesCatalogService;

/**
 * Implementation class of DeviceService.
 * @author Roger.Lin
 *
 */
public class OrderSuppliesCatalogServiceImpl implements OrderSuppliesCatalogService {
	private static Logger logger = LogManager.getLogger(OrderSuppliesCatalogServiceImpl.class);

	private static List<OrderPart> orderPart  = PartnerDomainMockDataGenerator.getCatalogPartList();
	private static List<OrderPart> selectedpartList  = PartnerDomainMockDataGenerator.getCatalogSelectedPartList();
	
	@Override
	public CatalogListResult retrieveCatalogFieldList(CatalogListContract catalogListContract) throws Exception {
		// TODO Auto-generated method stub
		CatalogListResult result = new CatalogListResult();
		List<ListOfValues> lovList = new ArrayList<ListOfValues>();
		if (catalogListContract.getAgreementId()== null) throw new Exception("Agreement id cannot be null");
		if(catalogListContract.getProductModel()!=null){
			logger.info("This call is for Part type");
			for (int i=0;i<5;i++){
				ListOfValues lov = new ListOfValues();
				lov.setName("partType");
				lov.setValue(catalogListContract.getProductModel()+" "+i);
				logger.info("product model value setting is "+catalogListContract.getProductModel()+" "+i);
				lovList.add(lov);
			}
		}else if(catalogListContract.getProductType()!=null){
			logger.info("This call is for Product Model");
			for (int i=11;i<18;i++){
				ListOfValues lov = new ListOfValues();
				lov.setName("productModel");
				lov.setValue(catalogListContract.getProductType()+i);
				logger.info("product model value is "+catalogListContract.getProductType()+i);
				lovList.add(lov);
			}
		}else{
			logger.info("This call is for Product type");
			for (int i=0;i<5;i++){
				ListOfValues lov = new ListOfValues();
				lov.setName("productType");
				lov.setValue("proType"+i);
				lovList.add(lov);
			}
		}
		result.setLovList(lovList);
		return result;
	}
	public CatalogListResult retrieveCatalogList(CatalogListContract catalogListContract) throws Exception{
		
		CatalogListResult catalogListResult = new CatalogListResult();
		List<OrderPart> newOrderPart  = new ArrayList<OrderPart>();
		String productModel = catalogListContract.getProductModel();
		
		if(catalogListContract.isHardwareAccessoriesFlag()==true){
			for (int i=0;i<3;i++){
				OrderPart orderPart = new OrderPart();
				orderPart.setPartNumber("11D1001");
				orderPart.setPartDesc(productModel+"accessoriesPartdesc "+i);
				orderPart.setPartType(productModel+"accessoriesParttype"+i);
				orderPart.setCatalogId(productModel+"accessoriesCatalogId"+i);
				orderPart.setSalesOrg("LXK United States");
				orderPart.setContractNo("0005002509");
				if(i==0){
					orderPart.setContractLineItemId("0000001000");
				}else if(i==1){
					orderPart.setContractLineItemId("0000002000");
				}else if(i==2){
					orderPart.setContractLineItemId("0000003000");
				}
				newOrderPart.add(orderPart);
			}
			catalogListResult.setAccessoriesList(newOrderPart);
			catalogListResult.setTotalCount(newOrderPart.size());
		}else if(catalogListContract.isHardwareSuppliesFlag()==true){
			for (int i=0;i<3;i++){
				OrderPart orderPart = new OrderPart();
				orderPart.setPartNumber("11D1001");
				orderPart.setSalesOrg("LXK United States");
				orderPart.setPartDesc(productModel+"suppliesPartdesc "+i);
				orderPart.setPartType(productModel+"suppliesParttype"+i);
				orderPart.setCatalogId(productModel+"suppliesCatalogId"+i);
				orderPart.setContractNo("0005002509");
				if(i==0){
					orderPart.setContractLineItemId("0000001000");
				}else if(i==1){
					orderPart.setContractLineItemId("0000002000");
				}else if(i==2){
					orderPart.setContractLineItemId("0000003000");
				}
				newOrderPart.add(orderPart);
			}
			catalogListResult.setSuppliesList(newOrderPart);
			catalogListResult.setTotalCount(newOrderPart.size());
		}else{
			for (int i=0;i<3;i++){
				OrderPart orderPart = new OrderPart();
				orderPart.setPartNumber("11D1001");
				orderPart.setSalesOrg("LXK United States");
				orderPart.setPartDesc(productModel+" partdesc "+i);
				orderPart.setPartType(productModel+"parttype"+i);
				orderPart.setYield(productModel+"yield"+i);
				orderPart.setCatalogId(productModel+"catalogId"+i);
				orderPart.setModel(productModel+"model"+i);
				orderPart.setImplicitFlag(false);
				orderPart.setContractNo("0005002509");
				if(i==0){
					orderPart.setContractLineItemId("0000001000");
				}else if(i==1){
					orderPart.setContractLineItemId("0000002000");
				}else if(i==2){
					orderPart.setContractLineItemId("0000003000");
				}
				newOrderPart.add(orderPart);
			}
			catalogListResult.setPartsList(newOrderPart);
			catalogListResult.setTotalCount(newOrderPart.size());
		}
		
		
		return catalogListResult;
	}
	@Override
	public CatalogListResult retrieveCatalogListWithContractNumber(
			CatalogListContract contract) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public CatalogListResult retrievePrinterTypesB2B(
			CatalogListContract contract) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public CatalogListResult retrieveAccessoriesB2b(CatalogListContract contract)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	//public CatalogListResult retrieveCatalogPartList (CatalogListContract catalogListContract)throws Exception;

	
	}
		
		
	
	
//	public AssetListResult retrieveDeviceList(AssetListContract contract)
//			throws Exception {
//		AssetListResult deviceListResult = new AssetListResult();
//
//		String sortCriteria=null;
//		for(String sort : contract.getSortCriteria().keySet()) {
//			sortCriteria = sort + ":" + contract.getSortCriteria().get(sort);
//			break;
//		}
//
//		CollectionFilter filter = new CollectionFilter(Locale.getDefault());
//		CollectionSorter sorter = new CollectionSorter();
//		List<Asset> filterResult = filter.filter(devices, contract.getFilterCriteria(), contract.getSearchCriteria());
//		List<Asset> sortResult = sorter.sort(filterResult, sortCriteria);
//		
//		if(contract.isFavoriteFlag()){
//			List<Asset> tempResult= new ArrayList<Asset>();
//			for(Asset asset:sortResult){
//				if(asset.getUserFavoriteFlag())
//					tempResult.add(asset);
//			}
//			sortResult =tempResult;
//		} else if(contract.getAssetType()!= null){
//			List<Asset> tempResult= new ArrayList<Asset>();
//			for(Asset asset:sortResult){
//				if(contract.getAssetType().equalsIgnoreCase(asset.getAssetType()))
//					tempResult.add(asset);
//			}
//			sortResult =tempResult;
//		} 
//		logger.info("++++++++++++++++++++++++++++++++size:+++++++++++++++"+sortResult.size());
//		if (contract.getLoadAllFlag()) {
//			deviceListResult.setAccountAssets(sortResult);
//		}else if(contract.getIncrement() == -1){
//			int start = 0;
//			int end = sortResult.size();
//			deviceListResult.setAccountAssets(sortResult.subList(start, end));
//		} else {
//			int start = contract.getStartRecordNumber();
//			int end = (contract.getIncrement()+ start)> sortResult.size()? sortResult.size(): (contract.getIncrement()+start);
//
//			deviceListResult.setAccountAssets(sortResult.subList(start, end));
//		}
//		deviceListResult.setTotalCount(sortResult.size());
//		return deviceListResult;
//	}
//
//	public AssetResult retrieveDeviceDetail(AssetContract contract) throws Exception {
//		if(contract.getAssetId().equals("-1")){
//			return null;
//		}
//		Asset matchedAsset = null;
//		String assetId = contract.getAssetId();
//		for (Asset asset : DomainMockDataGenerator.getDeviceList()) {
//			if (assetId.equals(asset.getAssetId())) {
//				matchedAsset = asset;
//				break;
//			}
//		}
//		AssetResult assetResult = new AssetResult();
//		assetResult.setAsset(matchedAsset);
//		return assetResult;
//	}
//
//	public FavoriteResult updateUserFavoriteAsset(
//			UserFavoriteAssetContract contract) throws Exception {
//		FavoriteResult favouriteResult = new FavoriteResult();
//		//the result only indicates if the webservice call is successful or not
//		favouriteResult.setResult(true);
//		return favouriteResult;
//	}
//
//	public AssetReportingHierarchyResult retrieveAssetReportingHierarchy(LocationReportingHierarchyContract contract) throws Exception {
//		AssetReportingHierarchyResult result = new AssetReportingHierarchyResult();
//		
//		if (contract != null) {
//			if (contract.getChlNodeId() != null) {
//				result.setChlNodeList(DomainMockDataGenerator.getCHLNodeListByNodeId(contract.getChlNodeId()));	
//			} else {
//				result.setChlNodeList(DomainMockDataGenerator.getCHLNodeListByNodeId(contract.getMdmId()));
//			}
//		}
//		return result;
//	}
//
//	public AssetLocationsResult retrieveAssetLocations(
//			LocationReportingHierarchyContract contract) throws Exception {
//		AssetLocationsResult result = new AssetLocationsResult();
//		result.setAddressList(DomainMockDataGenerator.getGenericAddressList());
//		return result;
//	}
//
//	//warning , you get the same thing every time you shoot a search. MOCK ONLY!!! 
//	public AssetListResult retrieveGlobalAssetList(
//			GlobalAssetListContract contract) {		
//		AssetListResult alr = new AssetListResult();
//		List<Asset> resultList = new ArrayList<Asset>();
//		Asset matchedAsset = null;
//		String assetId = "10216";
//		for (Asset asset : DomainMockDataGenerator.getDeviceList()) {
//			if (assetId.equals(asset.getAssetId())) {
//				matchedAsset = asset;
//				break;
//			}
//		}		
//		resultList.add(matchedAsset);
//		alr.setAccountAssets(resultList);
//		alr.setTotalCount(1);
//		return alr;
//	}

	


