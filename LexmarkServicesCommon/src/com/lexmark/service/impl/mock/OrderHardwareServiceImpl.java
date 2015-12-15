package com.lexmark.service.impl.mock;

import java.util.ArrayList;
import java.util.List;

import com.lexmark.contract.HardwareCatalogContract;
import com.lexmark.domain.Bundle;
import com.lexmark.domain.HardwareCatalog;
import com.lexmark.domain.OrderPart;
import com.lexmark.domain.Part;
import com.lexmark.result.BundleListResult;
import com.lexmark.result.HardwareCatalogResult;
import com.lexmark.service.api.OrderHardwareService;

public class OrderHardwareServiceImpl implements OrderHardwareService {
	private static List<OrderPart> orderPart  = PartnerDomainMockDataGenerator.getCatalogPartList();
	private static List<OrderPart> selectedpartList  = PartnerDomainMockDataGenerator.getCatalogSelectedPartList();
	
	/*@Override
	public HardwareCatalogResult retrieveHardwareFieldList(HardwareCatalogContract hardwareListContract) throws Exception {
		
		HardwareCatalogResult result = new HardwareCatalogResult();
		List<ListOfValues> lovList = new ArrayList<ListOfValues>();
		//if (hardwareListContract.getAgreementId()== null) throw new Exception("Agreement id cannot be null");
		if(hardwareListContract.getProductType()!=null){		
				ListOfValues lov;
				lov = new ListOfValues();
				lov.setName("productModel");
				lov.setValue("X746de");				
				lovList.add(lov);
				
				lov = new ListOfValues();
				lov.setName("productModel");
				lov.setValue("X748de");				
				lovList.add(lov);
				
				lov = new ListOfValues();
				lov.setName("productModel");
				lov.setValue("X748dte");				
				lovList.add(lov);
				
				lov = new ListOfValues();
				lov.setName("productModel");
				lov.setValue("X746n");				
				lovList.add(lov);
				
				lov = new ListOfValues();
				lov.setName("productModel");
				lov.setValue("X746dn");				
				lovList.add(lov);
				
		}else{
				ListOfValues lov;
				lov = new ListOfValues();
				lov.setName("productType");
				lov.setValue("Laser Printer");
				lovList.add(lov);
				
				lov = new ListOfValues();
				lov.setName("productType");
				lov.setValue("Dotmatrix Printer");
				lovList.add(lov);
				
				lov = new ListOfValues();
				lov.setName("productType");
				lov.setValue("Multifunction Printer");
				lovList.add(lov);
		}
		result.setLovList(lovList);
		return result;
	}*/
	public HardwareCatalogResult retrieveHardwareList(HardwareCatalogContract hardwareCatalogContract) throws Exception{
		
		HardwareCatalogResult hardwareCatalogResult = new HardwareCatalogResult();
		
		
		String partNumber = "";
		String productModel = "";
		String productType = "";
		//String partNumber = hardwareCatalogContract.getPartNumber();
		
		if(hardwareCatalogContract.getProductModel()==null && hardwareCatalogContract.getProductType()==null){
			if(hardwareCatalogContract.getPartNumber()!=null){
				partNumber = hardwareCatalogContract.getPartNumber();
			}
		}else{
			productModel = hardwareCatalogContract.getProductModel();
			productType = hardwareCatalogContract.getProductType();
		}
		
		
		if(partNumber!=null && partNumber!=""){
			HardwareCatalog hardwareCatalog = new HardwareCatalog();
			hardwareCatalog.setProductModel(productModel+"productModel");
			hardwareCatalog.setDescription(productModel+" description");
			hardwareCatalog.setDeviceType(productModel+"device Type");
			hardwareCatalog.setColor_mono("Color");
			
			List<Bundle> bundleList  = new ArrayList<Bundle>();
			List<Part> partsList1 = new ArrayList<Part>();
			List<Part> partsList2 = new ArrayList<Part>();
			
			for(int i=1;i<3;i++){
				Part orderPart = new Part();
				orderPart.setPartNumber("1000number"+i);
				orderPart.setDescription("Description of the asset no ");
				orderPart.setCategory("category +i");
				orderPart.setOrderQuantity("1");
				orderPart.setContractNo("0005002509");
				if(i==1){
					orderPart.setContractLineItemId("0000001000");
				}else if(i==2){
					orderPart.setContractLineItemId("0000002000");
				}
				partsList1.add(orderPart);
				partsList2.add(orderPart);				
			}
			
			Bundle bundle1 = new Bundle();
			bundle1.setBundleId("bundle1");
			bundle1.setContractNumber("0005002509");
			bundle1.setContractLineItemId("0000001000");	
			bundle1.setBundleProductId("11D1001");
			bundle1.setSalesOrg("LXK United States");
			bundle1.setDescription("Lexmark C746dtn color laser printer delivers high-quality color printing and reliable performance. Features like the 550-sheet tray increase efficiency. Energy-saving features make responsible printing easy");
			bundle1.setPartList(partsList1);
			//bundle1.setDetails("Lexmark C746dtn color laser printer delivers high-quality color printing and reliable performance. Features like the 550-sheet tray increase efficiency. Energy-saving features make responsible printing easy.");
			
			Bundle bundle2 = new Bundle();
			bundle2.setBundleId("bundle2");
			bundle2.setContractNumber("0005002509");
			bundle2.setContractLineItemId("0000002000");	
			bundle2.setBundleProductId("11D1001");
			bundle2.setSalesOrg("LXK United States");
			bundle2.setPartList(partsList2);
			//bundle2.setDetails("Lexmark C746dn color laser printer delivers high-quality color printing and reliable performance. Features like the 550-sheet tray increase efficiency. Energy-saving features make responsible printing easy.");
			
			bundleList.add(bundle1);
			bundleList.add(bundle2);
			hardwareCatalog.setBundleList(bundleList);
			hardwareCatalogResult.setHardwareCatalog(hardwareCatalog);
			
		}else{
			HardwareCatalog hardwareCatalog = new HardwareCatalog();
			hardwareCatalog.setProductModel(productModel+"productModel1");
			hardwareCatalog.setDescription(productModel+" description2");
			hardwareCatalog.setDeviceType(productModel+"device Type3");
			hardwareCatalog.setColor_mono("Color");
			
			List<Part> accessoriesList  = new ArrayList<Part>();
			List<Part> suppliesList  = new ArrayList<Part>();
			List<Bundle> bundleList  = new ArrayList<Bundle>();
			List<Part> partsList1 = new ArrayList<Part>();
			List<Part> partsList2 = new ArrayList<Part>();
			
			for(int i=1;i<3;i++){
				Part orderPart = new Part();
				orderPart.setPartNumber("1000number"+i);
				orderPart.setDescription("Description of the asset no ");
				orderPart.setCategory("category +i");
				orderPart.setOrderQuantity("1");
				orderPart.setContractNo("0005002509");
				if(i==1){
					orderPart.setContractLineItemId("0000001000");
				}else if(i==2){
					orderPart.setContractLineItemId("0000002000");
				}
				partsList1.add(orderPart);
				partsList2.add(orderPart);				
			}
			
			Bundle bundle1 = new Bundle();
			bundle1.setBundleId("bundle1");
			bundle1.setContractNumber("0005002509");
			bundle1.setContractLineItemId("0000001000");	
			bundle1.setBundleProductId("11D1001");
			bundle1.setSalesOrg("LXK United States");
			bundle1.setDescription("Lexmark C746dtn color laser printer delivers high-quality color printing and reliable performance. Features like the 550-sheet tray increase efficiency. Energy-saving features make responsible printing easy");
			bundle1.setPartList(partsList1);
			//bundle1.setDetails("Lexmark C746dtn color laser printer delivers high-quality color printing and reliable performance. Features like the 550-sheet tray increase efficiency. Energy-saving features make responsible printing easy.");
			
			Bundle bundle2 = new Bundle();
			bundle2.setContractNumber("0005002509");
			bundle2.setContractLineItemId("0000002000");
			bundle2.setBundleProductId("11D1001");
			bundle2.setSalesOrg("LXK United States");
			bundle2.setBundleId("bundle2");
			bundle2.setPartList(partsList2);
			//bundle2.setDetails("Lexmark C746dn color laser printer delivers high-quality color printing and reliable performance. Features like the 550-sheet tray increase efficiency. Energy-saving features make responsible printing easy.");
			
			bundleList.add(bundle1);
			bundleList.add(bundle2);
			
			
			hardwareCatalog.setBundleList(bundleList);
			hardwareCatalogResult.setHardwareCatalog(hardwareCatalog);	
		}
		return hardwareCatalogResult;
	}

	@Override
	public BundleListResult retrievePrinterBundleListB2B(
			HardwareCatalogContract catalogListContract) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	//public hardwareListResult retrieveCatalogPartList (hardwareListContract hardwareListContract)throws Exception;

	
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
