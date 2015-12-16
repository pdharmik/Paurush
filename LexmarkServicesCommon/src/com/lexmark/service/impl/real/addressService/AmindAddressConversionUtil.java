package com.lexmark.service.impl.real.addressService;

import static com.lexmark.util.LangUtil.isEmpty;
import static com.lexmark.util.LangUtil.isNotEmpty;
import static com.lexmark.util.LangUtil.notNull;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.lexmark.domain.GenericAddress;
import com.lexmark.result.AddressListResult;
import com.lexmark.result.LBSCHLListResult;
import com.lexmark.result.LBSFloorPlanListResult;
import com.lexmark.service.impl.real.domain.AddressDo;
import com.lexmark.service.impl.real.domain.FavoriteAddressDo;
import com.lexmark.service.impl.real.domain.LBSAddress;
import com.lexmark.service.impl.real.domain.LBSAsset;
import com.lexmark.service.impl.real.domain.LBSCHLMapping;
import com.lexmark.service.impl.real.domain.LBSCHLMappingDo;
import com.lexmark.service.impl.real.domain.LBSFloorPlanAssetDo;
import com.lexmark.service.impl.real.domain.LBSLocationBuilding;
import com.lexmark.service.impl.real.domain.LBSLocationBuildingType;
import com.lexmark.service.impl.real.domain.LBSLocationDo;
import com.lexmark.service.impl.real.domain.LBSLocationFloor;
import com.lexmark.service.impl.real.domain.LBSLocationSite;
import com.lexmark.service.impl.real.domain.LBSLocationZone;
import com.lexmark.service.impl.real.domain.SapAccountDo;
import com.lexmark.util.LangUtil;

public class AmindAddressConversionUtil {

	public static List<GenericAddress> convertAddressDoListToGenericAddress(
			List<? extends AddressDo> addressDoList,
			List<FavoriteAddressDo> favAddressDoList) {

		if (addressDoList == null) {
			return new ArrayList<GenericAddress>();
		}

		Set<String> favoriteSet = getFavoriteAddressSet(favAddressDoList);

		List<GenericAddress> addressList = new ArrayList<GenericAddress>(
				addressDoList.size());

		for (AddressDo addressDo : addressDoList) {
			GenericAddress address = new GenericAddress();

			if (addressDo instanceof FavoriteAddressDo) {
				address.setUserFavorite(true);
			}

			address.setAddressId(addressDo.getAddressId());
			address.setAddressName(addressDo.getAddressName());
			address.setAddressLine1(addressDo.getAddressLine1());
			address.setAddressLine2(addressDo.getAddressLine2());
			address.setAddressLine3(addressDo.getAddressLine3());
			address.setStoreFrontName(addressDo.getStoreFrontName());
			address.setCity(addressDo.getCity());
			address.setState(addressDo.getState());
			address.setPostalCode(addressDo.getZip());
			address.setCountry(addressDo.getCountry());
			address.setProvince(addressDo.getProvince());

			address.setCounty(addressDo.getCounty());
			address.setDistrict(addressDo.getDistrict());
			address.setOfficeNumber(addressDo.getOfficeNumber());
			address.setCountryISOCode(addressDo.getCountyCode());
			address.setRegion(addressDo.getRegion());
			address.setLbsAddressFlag(addressDo.isLbsAddressFlag());
			
			address.setLevelOfDetails(addressDo.getLevelOfDetails());

			if (favoriteSet != null && favoriteSet.contains(addressDo.getId())) {
				address.setUserFavorite(true);
			}

			addressList.add(address);
		}

		return addressList;
	}

	private static Set<String> getFavoriteAddressSet(
			List<FavoriteAddressDo> favAddressDoList) {
		Set<String> favoriteSet = new HashSet<String>();
		if (favAddressDoList == null) {
			return favoriteSet;
		}
		for (FavoriteAddressDo addrDo : favAddressDoList) {
			favoriteSet.add(addrDo.getFavoriteAddressId());
		}
		return favoriteSet;
	}

	private AmindAddressConversionUtil() {
	}

	public static List<GenericAddress> convertSapBusinessAddressToGenericAddress(List<SapAccountDo> sapAccountDos) {

		if (LangUtil.isEmpty(sapAccountDos)) {
			return new ArrayList<GenericAddress>();
		}

		List<GenericAddress> addresses = new ArrayList<GenericAddress>();

		for (SapAccountDo businessAddress : sapAccountDos) {
			if (businessAddress != null) {
				GenericAddress address = new GenericAddress();
				address.setCountry(businessAddress.getCountry());
				address.setCity(businessAddress.getCity());
				address.setState(businessAddress.getState());
				address.setAddressName(businessAddress.getAddressName());
				address.setAddressLine1(businessAddress.getStreetAddress());
				address.setAddressLine2(businessAddress.getStreetAddress2());
				address.setProvince(businessAddress.getProvince());
				address.setCounty(businessAddress.getCounty());
				address.setDistrict(businessAddress.getDistrict());
				address.setOfficeNumber(businessAddress.getOfficeNumber());
				address.setPostalCode(businessAddress.getPostalCode());
				address.setCountryISOCode(businessAddress.getCountryIsoCode());
				address.setRegion(businessAddress.getRegion());
				address.setSoldToNumber(businessAddress.getSoldTo());
				address.setAddressId(businessAddress.getPrimaryAddressId());
				address.setBillToNumber(businessAddress.getBillToNumber());
				addresses.add(address);
			}
		}

		return addresses;
	}
	
	

	/**	 
	 * @author David Tsamalashvili 
	 */
	public static List<LBSAddress> convertLBSAddress(List<AddressDo> address,
			String convertionType) {
		List<LBSAddress> convertedAddress = new ArrayList<LBSAddress>();
		boolean distinctTest = false;
		for (AddressDo lbsAddress : LangUtil.notNull(address)) {
			LBSAddress addressObj = new LBSAddress();
			if (convertionType != null && convertionType.equalsIgnoreCase("Country")) {
				distinctTest = checkdistinc(convertedAddress, lbsAddress.getCountry(), convertionType);
				if (distinctTest) {
					addressObj.setCountry(lbsAddress.getCountry());
					convertedAddress.add(addressObj);
				}
			} else if (convertionType != null && convertionType.equalsIgnoreCase("State")) {
				if (isNotEmpty(lbsAddress.getState())) {
					distinctTest = checkdistinc(convertedAddress, lbsAddress.getState(), convertionType);
					if (distinctTest) {
						addressObj.setState(lbsAddress.getState());
					}
				}
				else if(isNotEmpty(lbsAddress.getProvince())){
					distinctTest = checkdistinc(convertedAddress, lbsAddress.getProvince(), convertionType);
					if (distinctTest) {
						addressObj.setProvince(lbsAddress.getProvince());
					}
				}
				else if(isNotEmpty(lbsAddress.getCounty())){
					distinctTest = checkdistinc(convertedAddress, lbsAddress.getCounty(), convertionType);
					if (distinctTest) {
						addressObj.setCounty(lbsAddress.getCounty());
					}		
				}
				else if(isNotEmpty(lbsAddress.getDistrict())){
					distinctTest = checkdistinc(convertedAddress, lbsAddress.getDistrict(), convertionType);
					if (distinctTest) {
						addressObj.setDistrict(lbsAddress.getDistrict());
					}
				}
				if(checkCity(lbsAddress)){
					distinctTest = checkdistinc(convertedAddress, lbsAddress.getCity(), "City");
					if (distinctTest) {
						addressObj.setCity(lbsAddress.getCity());
					}
				}
				convertedAddress.add(addressObj);
			} else if (convertionType != null && convertionType.equalsIgnoreCase("City")) {
				distinctTest = checkdistinc(convertedAddress, lbsAddress.getCity(), convertionType);
				if (distinctTest) {
					addressObj.setCity(lbsAddress.getCity());
					convertedAddress.add(addressObj);
				}
			}
		}
		return convertedAddress;
	}
	
	/**	 
	 * @author David Tsamalashvili 
	 */
	public static AddressListResult convertLBSLocation(List<LBSLocationDo> address, String locationType) {
		AddressListResult convertedAddress = new AddressListResult();
		List<LBSLocationSite> convertedSite = new ArrayList<LBSLocationSite>();
		List<LBSLocationBuilding> convertedBuilding = new ArrayList<LBSLocationBuilding>();
		List<LBSLocationFloor> convertedFloor = new ArrayList<LBSLocationFloor>();
		List<LBSLocationZone> convertedZone = new ArrayList<LBSLocationZone>();
		boolean distinctTest = false;
		boolean distinctSite = false;
					
					if(locationType != null && locationType.equalsIgnoreCase("Building")){
							for (LBSLocationDo lbsAddress : LangUtil.notNull(address)) {
								LBSLocationBuilding lbsLocationBuildingObj = new LBSLocationBuilding();
								distinctTest = checkedistincBuilding(convertedBuilding, lbsAddress.getLocationName(), lbsAddress.getLocationId());
									if(distinctTest){
										lbsLocationBuildingObj.setBuilding(lbsAddress.getLocationName());
										lbsLocationBuildingObj.setBuildingId(lbsAddress.getLocationId());
										convertedBuilding.add(lbsLocationBuildingObj);
									}
							}
					}
					if(locationType != null && locationType.equalsIgnoreCase("Floor")){
						for (LBSLocationDo lbsAddress : LangUtil.notNull(address)) {
							LBSLocationFloor lbsLocationFloorObj = new LBSLocationFloor();
							distinctTest = checkedistincFloor(convertedFloor, lbsAddress.getLocationName(), lbsAddress.getLocationId());
								if(distinctTest){
									lbsLocationFloorObj.setFloor(lbsAddress.getLocationName());
									lbsLocationFloorObj.setFloorId(lbsAddress.getLocationId());
									lbsLocationFloorObj.setFloorLevelOfDetails(lbsAddress.getFloorLevelOfDetails());
									convertedFloor.add(lbsLocationFloorObj);
								}						
						}
					}
					if(locationType != null && locationType.equalsIgnoreCase("Site")){
						for (LBSLocationDo lbsAddress : LangUtil.notNull(address)) {
							LBSLocationSite lbsLocationSiteObj = new LBSLocationSite();
							distinctSite = checkedistincSite(convertedSite, lbsAddress.getSite(), lbsAddress.getSiteId());
							if (distinctSite) {
								lbsLocationSiteObj.setSite(lbsAddress.getSite());
								lbsLocationSiteObj.setSiteId(lbsAddress.getSiteId());
								convertedSite.add(lbsLocationSiteObj);
							}
						}
					}
					if(locationType != null && locationType.equalsIgnoreCase("Zone")){
						for (LBSLocationDo lbsAddress : LangUtil.notNull(address)) {
							LBSLocationZone lbsLocationZoneObj = new LBSLocationZone();
							distinctTest = checkedistincZone(convertedZone, lbsAddress.getLocationName(), lbsAddress.getLocationId());
								if(distinctTest){
									lbsLocationZoneObj.setZone(lbsAddress.getLocationName());
									lbsLocationZoneObj.setZoneId(lbsAddress.getLocationId());
									convertedZone.add(lbsLocationZoneObj);
								}
						}
					}
								
		convertedAddress.setLbsLocationSiteList(convertedSite);
		convertedAddress.setLbsLocationBuildingList(convertedBuilding);
		convertedAddress.setLbsLocationFloorList(convertedFloor);
		convertedAddress.setLbsLocationZoneList(convertedZone);
		
		return convertedAddress;
	}
	
	public static List<LBSLocationBuildingType> convertLBSBuildingTypeList(List<LBSLocationDo> lbsLocationDoList) {
		List<LBSLocationBuildingType> buildingTypes = new ArrayList<LBSLocationBuildingType>();
		
		for (LBSLocationDo lbsLocationDo : notNull(lbsLocationDoList)) {
			LBSLocationBuildingType buildingType = new LBSLocationBuildingType();
			if (distinctBuildingTypes(lbsLocationDo, buildingTypes)) {
				buildingType.setBuildingType(lbsLocationDo.getBuildingType());
				buildingTypes.add(buildingType);
			}
		}
		
		return buildingTypes;
	}
	
	private static boolean distinctBuildingTypes(LBSLocationDo lbsLocationDo, List<LBSLocationBuildingType> buildingTypes) {
		for (LBSLocationBuildingType buildingType : notNull(buildingTypes)) {
			if (lbsLocationDo.getBuildingType() != null && buildingType.getBuildingType() != null) {
				if (lbsLocationDo.getBuildingType().equalsIgnoreCase(buildingType.getBuildingType())) {
					return false;
				}
			}
		}
		return true;
	}

	/**	 
	 * @author David Tsamalashvili 
	 */
	public static AddressListResult convertLBSLocationForTwoCalls(List<LBSLocationDo> address, List<LBSLocationDo> addressTwo, List<LBSLocationDo> addressThree, boolean firstCall, String queryType) {
		AddressListResult convertedAddress = new AddressListResult();
		List<LBSLocationSite> convertedSite = new ArrayList<LBSLocationSite>();
		List<LBSLocationBuilding> convertedBuilding = new ArrayList<LBSLocationBuilding>();
		List<LBSLocationZone> convertedZone = new ArrayList<LBSLocationZone>();		
		List<LBSAddress> convertedCoverage = new ArrayList<LBSAddress>();
		boolean distinctTest = false;		

		for (LBSLocationDo lbsAddress : LangUtil.notNull(address)) {
			LBSLocationSite lbsLocationSiteObj = new LBSLocationSite(); 
			distinctTest = checkedistincSite(convertedSite, lbsAddress.getLocationName(), lbsAddress.getLocationId());
				if(distinctTest){
					lbsLocationSiteObj.setSite(lbsAddress.getLocationName());
					lbsLocationSiteObj.setSiteId(lbsAddress.getLocationId());
					convertedSite.add(lbsLocationSiteObj);
				}
		}		
			for (LBSLocationDo lbsBuildings : LangUtil.notNull(addressTwo)) {
				LBSLocationBuilding lbsLocationBuildingObj = new LBSLocationBuilding(); 
				distinctTest = checkedistincBuilding(convertedBuilding, lbsBuildings.getLocationName(), lbsBuildings.getLocationId());
					if(distinctTest){
						lbsLocationBuildingObj.setBuilding(lbsBuildings.getLocationName());
						lbsLocationBuildingObj.setBuildingId(lbsBuildings.getLocationId());				
						convertedBuilding.add(lbsLocationBuildingObj);
					}
			}
		for (LBSLocationDo lbsZone : LangUtil.notNull(addressThree)) {
			LBSLocationZone lbsLocationZoneObj = new LBSLocationZone(); 
			distinctTest = checkedistincZone(convertedZone, lbsZone.getLocationName(), lbsZone.getLocationId());
				if(distinctTest){
					lbsLocationZoneObj.setZone(lbsZone.getLocationName());
					lbsLocationZoneObj.setZoneId(lbsZone.getLocationId());				
					convertedZone.add(lbsLocationZoneObj);
				}
			}
			
				if(queryType.equalsIgnoreCase("withAddress")){
						for (LBSLocationDo lbsAddress : LangUtil.notNull(addressTwo)) {
							LBSAddress lbsLocationAddressObj = new LBSAddress(); 
							distinctTest = checkedistincCoverage(convertedCoverage, lbsAddress.getCountry(), lbsAddress.getState(), lbsAddress.getCity());
								if(distinctTest){
									lbsLocationAddressObj.setCity(lbsAddress.getCity());
									lbsLocationAddressObj.setState(lbsAddress.getState());
									lbsLocationAddressObj.setCountry(lbsAddress.getCountry());
									lbsLocationAddressObj.setProvince(lbsAddress.getProvince());
									lbsLocationAddressObj.setCounty(lbsAddress.getCounty());
									lbsLocationAddressObj.setDistrict(lbsAddress.getDistrict());
									convertedCoverage.add(lbsLocationAddressObj);
								}
						}						
				}
		
		convertedAddress.setLbsLocationSiteList(convertedSite);
		convertedAddress.setLbsLocationBuildingList(convertedBuilding);
		convertedAddress.setLbsLocationZoneList(convertedZone);
		convertedAddress.setLbsAddressList(convertedCoverage);
		
		return convertedAddress;
	}
	
	
	/**	 
	 * @author David Tsamalashvili 
	 */
	public static LBSFloorPlanListResult convertLBSAsset(List<LBSFloorPlanAssetDo> asset) {
		LBSFloorPlanListResult convertedAssetReturn = new LBSFloorPlanListResult();
		List<LBSAsset> convertedAsset = new ArrayList<LBSAsset>();

		boolean check = LangUtil.isNotEmpty(asset);
		if (check) {
			for (LBSFloorPlanAssetDo item : asset) {
				LBSAsset converted = new LBSAsset();
				converted.setAccountName(item.getAccountName());
				converted.setAccountId(item.getAccountId());
				converted.setAssetCostCenter(item.getAssetCostCenter());
				converted.setAssetNumber(item.getAssetNumber());
				converted.setBrandMFGProduct(item.getBrandMFGProduct());
				converted.setDevicePhase(item.getDevicePhase());
				converted.setHostName(item.getHostName());
				converted.setId(item.getId());
				converted.setInstallDate(item.getInstallDate());
				converted.setDepartmentName(item.getDepartmentName());
				converted.setProductFamily(item.getProductFamily());
				converted.setProductModel(item.getProductModel());
				converted.setProductSeries(item.getProductSeries());
				converted.setProductModelName(item.getProductModelName());
				converted.setPrimaryContactFirstName(item.getPrimaryContactFirstName());
				converted.setPrimaryContactLastName(item.getPrimaryContactLastName());
				converted.setPrimaryContactWorkPhone(item.getPrimaryContactWorkPhone());
				converted.setMeterSource(item.getMeterSource());
				converted.setOwnership(item.getOwnership());
				converted.setProductName(item.getProductName());
				converted.setProductType(item.getProductType());
				converted.setPrimaryContactAlternatePhone(item.getPrimaryContactAlternatePhone());
				converted.setPrimaryContactEmail(item.getPrimaryContactEmail());
				converted.setAssetTag(item.getAssetTag());
				converted.setLxkAssetTag(item.getLxkAssetTag());
				converted.setAssetLifeCycle(item.getAssetLifeCycle());
				converted.setHardwareStatus(item.getHardwareStatus());
				converted.setPartType(item.getPartType());
				converted.setLevelOfDetails(item.getLevelOfDetails());
				converted.setFloorLevelDetails(item.getFloorLevelDetails());
				converted.setAddressLevelDetails(item.getAddressLevelDetails());
				converted.setBuildingType(item.getBuildingType());
				converted.setSerialNumber(item.getSerialNumber());
				
				if (LangUtil.isNotEmpty(item.getMaterialPartType())) {
					if (item.getMaterialPartType().contains("Mono")) {
						converted.setMonoColor("Mono");
					} else if (item.getMaterialPartType().contains("Color")) {
						converted.setMonoColor("Color");
					} else {
						converted.setMonoColor("Mono");
					}

					if (item.getMaterialPartType().contains("Single")) {
						converted.setFuntionPrinterType("Single");
					} else if (item.getMaterialPartType().contains("MFP")) {
						converted.setFuntionPrinterType("MFP");
					} else {
						converted.setFuntionPrinterType("Single");
					}
				}
				converted.setMaterialPartType(item.getMaterialPartType());
				convertedAsset.add(converted);
			}
		}

		convertedAssetReturn.setAssetList(convertedAsset);

		return convertedAssetReturn;
	}
	
	/**	 
	 * @author David Tsamalashvili 
	 */
	public static LBSCHLListResult convertLBSCHLMapping(List<LBSCHLMappingDo> chlArray) {

		LBSCHLListResult convertedCHLReturn = new LBSCHLListResult();
		List<LBSCHLMapping> convertedCHL = new ArrayList<LBSCHLMapping>();
		if(LangUtil.isNotEmpty(chlArray)){
			for (LBSCHLMappingDo lbschlMapping : chlArray) {
				LBSCHLMapping topLevelAcount = new LBSCHLMapping();
				
				topLevelAcount.setL5AccoountId(lbschlMapping.getL5AccoountId());
				topLevelAcount.setHeirarchyParentChain(lbschlMapping.getHeirarchyParentChain());
				convertedCHL.add(topLevelAcount);
			}
			
			convertedCHLReturn.setChlAccount(convertedCHL);
		}
		return convertedCHLReturn;
	}

	/**	 
	 * @author David Tsamalashvili 
	 */
	private static boolean checkdistinc(List<LBSAddress> address,
			String currentValue, String convertionType) {
		boolean distinctResult = true;
		for (LBSAddress lbsAddress : LangUtil.notNull(address)) {
			if (lbsAddress.getCountry() != null) {
				if (lbsAddress.getCountry().equalsIgnoreCase(currentValue) && convertionType.equalsIgnoreCase("Country")) {
					distinctResult = false;
				}
			} else if (lbsAddress.getState() != null) {
				if (lbsAddress.getState().equalsIgnoreCase(currentValue) && convertionType.equalsIgnoreCase("State")) {
					distinctResult = false;
				}
			} else if (lbsAddress.getCity() != null) {
				if (lbsAddress.getCity().equalsIgnoreCase(currentValue) && convertionType.equalsIgnoreCase("City")) {
					distinctResult = false;
				}
			}
			else if (lbsAddress.getProvince() != null) {
				if (lbsAddress.getProvince().equalsIgnoreCase(currentValue) && convertionType.equalsIgnoreCase("State")) {
					distinctResult = false;
				}
			}else if (lbsAddress.getCounty() != null) {
				if (lbsAddress.getCounty().equalsIgnoreCase(currentValue) && convertionType.equalsIgnoreCase("State")) {
					distinctResult = false;
				}
			}else if (lbsAddress.getDistrict() != null) {
				if (lbsAddress.getDistrict().equalsIgnoreCase(currentValue) && convertionType.equalsIgnoreCase("State")) {
					distinctResult = false;
				}
			}
		}
		return distinctResult;
	}
	
	private static boolean checkCity(AddressDo lbsAddress) {
		boolean passCity = false;
		if(isEmpty(lbsAddress.getState()) && isEmpty(lbsAddress.getCounty())){
			passCity= true;
			// && LangUtil.isEmpty(lbsAddress.getProvince()) && LangUtil.isEmpty(lbsAddress.getDistrict())
		}
		return passCity;
	}
	
	/**	 
	 * @author David Tsamalashvili 
	 */
	private static boolean checkedistincSite(List<LBSLocationSite> address, String currentValueName, String currentValueId) {
		boolean distinctResult = true;	
		
		for (LBSLocationSite lbsAddress : LangUtil.notNull(address)) {
			if (lbsAddress.getSite() != null && lbsAddress.getSiteId() != null) {
				if (lbsAddress.getSite().equalsIgnoreCase(currentValueName) && lbsAddress.getSiteId().equalsIgnoreCase(currentValueId)) {
					distinctResult = false;
				}
			}
		}
		return distinctResult;
	}
	
	/**	 
	 * @author David Tsamalashvili 
	 */
	private static boolean checkedistincBuilding(List<LBSLocationBuilding> address, String currentValueName, String currentValueId) {
		boolean distinctResult = true;	
		
		for (LBSLocationBuilding lbsAddress : LangUtil.notNull(address)) {
			if (lbsAddress.getBuilding() != null && lbsAddress.getBuildingId() != null) {
				if (lbsAddress.getBuilding().equalsIgnoreCase(currentValueName) && lbsAddress.getBuildingId().equalsIgnoreCase(currentValueId)) {
					distinctResult = false;
				}
			}
		}
		return distinctResult;
	}
	
	/**	 
	 * @author David Tsamalashvili 
	 */
	private static boolean checkedistincFloor(List<LBSLocationFloor> address, String currentValueName, String currentValueId) {
		boolean distinctResult = true;
		
		for (LBSLocationFloor lbsAddress : LangUtil.notNull(address)) {
			if (lbsAddress.getFloor() != null && lbsAddress.getFloorId() != null) {
				if (lbsAddress.getFloor().equalsIgnoreCase(currentValueName) && lbsAddress.getFloorId().equalsIgnoreCase(currentValueId)) {
					distinctResult = false;
				}
			}
		}
		return distinctResult;
	}
	
	/**	 
	 * @author David Tsamalashvili 
	 */
	private static boolean checkedistincZone(List<LBSLocationZone> address, String currentValueName, String currentValueId) {
		boolean distinctResult = true;	
		
		for (LBSLocationZone lbsAddress : LangUtil.notNull(address)) {
			if (lbsAddress.getZone() != null && lbsAddress.getZoneId() != null) {
				if (lbsAddress.getZone().equalsIgnoreCase(currentValueName) && lbsAddress.getZoneId().equalsIgnoreCase(currentValueId)) {
					distinctResult = false;
				}
			}
		}
		return distinctResult;
	}
	
	/**	 
	 * @author David Tsamalashvili 
	 */
	private static boolean checkedistincCoverage(List<LBSAddress> address, String currentCountry, String currentState, String currentCity) {
		boolean distinctResult = true;	
		
		for (LBSAddress lbsAddress : LangUtil.notNull(address)) {
			if (lbsAddress.getCountry() != null && lbsAddress.getState() != null && lbsAddress.getCity() != null) {
				if (lbsAddress.getCountry().equalsIgnoreCase(currentCountry) && lbsAddress.getState().equalsIgnoreCase(currentState) && lbsAddress.getCity().equalsIgnoreCase(currentCity)) {
					distinctResult = false;
				}
			}
		}
		return distinctResult;
	}
	
}