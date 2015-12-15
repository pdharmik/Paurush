package com.lexmark.service.impl.real.orderSuppliesAssetService;

import static com.lexmark.util.LangUtil.isEmpty;
import static com.lexmark.util.LangUtil.isNotEmpty;
import static org.apache.commons.lang.StringUtils.isNotEmpty;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.lexmark.contract.AssetContract;
import com.lexmark.domain.Account;
import com.lexmark.domain.AccountContact;
import com.lexmark.domain.Asset;
import com.lexmark.domain.GenericAddress;
import com.lexmark.domain.PageCounts;
import com.lexmark.domain.Part;
import com.lexmark.service.impl.real.domain.AssetDetailContactDo;
import com.lexmark.service.impl.real.domain.MPSMeterRead;
import com.lexmark.service.impl.real.domain.OrderSuppliesAssetAgreementEntitlementDo;
import com.lexmark.service.impl.real.domain.OrderSuppliesAssetDetailDo;
import com.lexmark.service.impl.real.domain.OrderSuppliesAssetEntitlementDetailDo;
import com.lexmark.service.impl.real.domain.OrderSuppliesAssetPageCountDo;
import com.lexmark.service.impl.real.domain.OrderSuppliesAssetPageCountReadingDo;
import com.lexmark.service.impl.real.domain.OrderSuppliesAssetPartDetailDo;
import com.lexmark.service.impl.real.domain.OrderSuppliesAssetPartPaymentDetailDo;
import com.lexmark.service.impl.real.util.AmindServiceUtil;
import com.lexmark.util.LangUtil;

/**
 * @author vpetruchok
 * @version 1.0, 2012-03-28
 */
public class AmindOrderSuppliesAssetServiceUtil {
  
	//private static Log logger = LogFactory.getLog(AmindOrderSuppliesAssetDetailService.class);
	private static Logger logger = LogManager.getLogger(AmindOrderSuppliesAssetDetailService.class);
    
	 public static void toAsset(Asset asset, OrderSuppliesAssetDetailDo item,AssetContract assetContract) {
		//Asset asset = new Asset();
		asset.setAssetId(item.getAssetId());
        asset.setSerialNumber(item.getSerialNumber());
        asset.setAssetTag(item.getAssetTag());
        asset.setModelNumber(item.getModelNumber());
        asset.setIpAddress(item.getIpAddress());
        asset.setHostName(item.getHostName());
        asset.setDeviceTag(item.getDeviceTagCustomer());
        asset.setProductTLI(item.getProductTLI());
        asset.setProductLine(item.getProductLine());
        asset.setDevicePhase(item.getDevicePhase());
        asset.setMacAddress(item.getMacAddress());
        asset.setInstallDate(item.getInstallDate());
        GenericAddress physicalLocationAddress = new GenericAddress();
        physicalLocationAddress.setPhysicalLocation1(item.getPhysicalLocation1());
        physicalLocationAddress.setPhysicalLocation2(item.getPhysicalLocation2());
        physicalLocationAddress.setPhysicalLocation3(item.getPhysicalLocation3());;
		physicalLocationAddress.setLbsAddressFlag(item.isLbsAddressFlag());
		physicalLocationAddress.setCounty(item.getCounty());
		physicalLocationAddress.setDistrict(item.getDistrict());
		physicalLocationAddress.setOfficeNumber(item.getOfficeNumber());
		asset.setPhysicalLocationAddress(physicalLocationAddress);
		//asset.setDescriptionLocalLang(item.getDescriptionLocalLang());
		if (LangUtil.isBlank(item.getDescriptionLocalLang())						//changes here for 13403
				&& LangUtil.isBlank(item.getProductLine())) {
			asset.setDescriptionLocalLang(item.getModelMDM());
		} else {
			asset.setDescriptionLocalLang(item.getProductLine());
			if (LangUtil.isNotBlank(item.getDescriptionLocalLang())) {
				asset.setDescriptionLocalLang(item.getDescriptionLocalLang());
			}
		}
		asset.setDefaultSpecialInstruction(item.getDefaultSpecialInstruction());
		asset.setAssetCostCenter(item.getAssetCostCenter());
		asset.setDescription(item.getDescription());
		asset.setInstallationOnlyFlag(installationOnlyFlag(item));
		asset.setAgreementId(populateAgreementId(item));
		asset.setDeviceContact(populateDeviceContacts(item.getDeviceContacts()));
		asset.setChlNodeId(item.getChlNodeId());
		asset.setChlNodeValue(item.getChlNodeValue());
		asset.setNotes(item.getNotes());

		if (isNotEmpty(item.getAddressFlag()) && "Y".equalsIgnoreCase(item.getAddressFlag())) {
			asset.setAddressFlag(false);
		} else {
			asset.setAddressFlag(true);
		}

		Account account = new Account();
		account.setAccountId(item.getAccountId());
		account.setAccountName(item.getAccountName());
		account.setAssetExpediteFlag(assetExpediteFlag(item));
		account.setAccountSplitterFlag(convertStringToBoolean(item.getSplitterFlag()));
		account.setShowPriceFlag(setShowPriceFlag(item));
		account.setOrganizationName(item.getOrganizationName());
		AmindServiceUtil.populatePaymentMethod(account, item.getPaymentMethod());
		asset.setAccount(account);

		AccountContact contact = new AccountContact();
		if (item.getContactId() != null	&& !item.getContactId().equalsIgnoreCase("No match row Id")) {
			contact.setContactId(item.getContactId());
			contact.setFirstName(item.getContactFirstName());
			contact.setLastName(item.getContactLastName());
			contact.setEmailAddress(item.getContactEmailAddress());
			contact.setWorkPhone(item.getContactWorkPhone());
			contact.setMiddleName(item.getContactMiddleName());
			contact.setAlternatePhone(item.getContactAlteratePhone());
		}
		asset.setAssetContact(contact);
		asset.setConsumableAssetFlag(processEntitlmentType(item));
		asset.setContractNumber(item.getContractNumber());

		GenericAddress installAddress = new GenericAddress();
		installAddress.setAddressId(item.getAddressId());
		installAddress.setAddressLine1(item.getAddress1());
		installAddress.setAddressLine2(item.getAddress2());
		installAddress.setAddressLine3(item.getAddress3());
		installAddress.setCity(item.getInstallCity());
		installAddress.setCountry(item.getInstallCountry());
		installAddress.setPostalCode(item.getInstallPostalCode());
		installAddress.setProvince(item.getInstallProvince());
		installAddress.setState(item.getInstallState());
		installAddress.setAddressName(item.getAddressName());
		installAddress.setStoreFrontName(item.getStoreFrontName());
		installAddress.setCounty(item.getCounty());
		installAddress.setDistrict(item.getDistrict());
		installAddress.setOfficeNumber(item.getOfficeNumber());
		installAddress.setCountryISOCode(item.getCountryIsoCode());
		installAddress.setRegion(item.getRegion());
		installAddress.setLbsAddressFlag(item.isLbsAddressFlag());
		installAddress.setPhysicalLocation1(item.getPhysicalLocation1());
		installAddress.setPhysicalLocation2(item.getPhysicalLocation2());
		installAddress.setPhysicalLocation3(item.getPhysicalLocation3());
		installAddress.setZoneName(item.getZone());
		installAddress.setBuildingId(item.getBuildingId());
		installAddress.setCampusName(item.getCampusName());
		installAddress.setCampusId(item.getCampusId());
		installAddress.setLbsLatitude(item.getLbsLatitude());
		installAddress.setLbsLongitude(item.getLbsLongitude());
		installAddress.setCoordinatesXPreDebriefRFV(item.getCoordinatesXPreDebriefRFV());
		installAddress.setCoordinatesYPreDebriefRFV(item.getCoordinatesYPreDebriefRFV());
		installAddress.setLevelOfDetails(item.getLevelOfDetails());
		asset.setInstallAddress(installAddress);
		asset.setColorCapableFlag(false);
		if (item.getAgreementEntitlements() != null) {
			for (OrderSuppliesAssetAgreementEntitlementDo agreement : item
					.getAgreementEntitlements()) {
				if ("Consumables Mgmt Supply".equalsIgnoreCase(agreement
						.getEntitlementType())) {
					asset.setTempSupplies(agreement.getQuantity());
				} else if ("Consumables Mgmt Services"
						.equalsIgnoreCase(agreement.getEntitlementType())) {
					asset.setTempServices(agreement.getQuantity());
				}

			}
		}
		// processPartDetailDoList(asset, item,assetContract);

		// return asset;
	}

	private static String setShowPriceFlag(OrderSuppliesAssetDetailDo item) {
		if (item.getAgreementEntitlements() != null) {
			for (OrderSuppliesAssetAgreementEntitlementDo agreement : item.getAgreementEntitlements())
				if (agreement != null && agreement.getShowPrice() != null) {
					return agreement.getShowPrice();
				}
		}
		return null;

	}

	private static boolean installationOnlyFlag(OrderSuppliesAssetDetailDo itemDo) {
		for (OrderSuppliesAssetAgreementEntitlementDo entDo : LangUtil.notNull(itemDo.getAgreementEntitlements())) {
			if ("Consumables Mgmt Services".equalsIgnoreCase(entDo.getEntitlementType())
					&& "Valid".equalsIgnoreCase(entDo.getEntitlementStatus())) {

				for (OrderSuppliesAssetEntitlementDetailDo entDetailDo : LangUtil.notNull(entDo.getEntitlementDetails())) {
					if ("Install consumable SVC Parts".equalsIgnoreCase(entDetailDo.getActivityType())) {
						return true;
					}
				}
			} else if ("Consumables Mgmt Supply".equalsIgnoreCase(entDo.getEntitlementType())
					&& "Valid".equalsIgnoreCase(entDo.getEntitlementStatus())) {

				for (OrderSuppliesAssetEntitlementDetailDo entDetailDo : LangUtil.notNull(entDo.getEntitlementDetails())) {
					if ("Install Consumable Supplies".equalsIgnoreCase(entDetailDo.getActivityType())) {
						return true;
					}
				}
			}
		}
		return false;
	}

	private static boolean assetExpediteFlag(OrderSuppliesAssetDetailDo itemDo) {
		for (OrderSuppliesAssetAgreementEntitlementDo serviceAgreementDo : LangUtil.notNull(itemDo.getAgreementEntitlements())) {
			if ("Y".equalsIgnoreCase(serviceAgreementDo.getExpediteFlag())) {
				return true;
			}
		}
		return false;
	}

	static boolean booleanValue(Boolean b) {
		if (b == null)
			return false;

		return b.booleanValue();
	}

	// public static Asset processPartDetailDoList(OrderSuppliesAssetDetailDo
	// assetDetailDo ,AssetContract assetContract) {
	public static Asset processPartDetailDoList(Asset asset, OrderSuppliesAssetDetailDo assetDetailDo, AssetContract assetContract) {
		List<OrderSuppliesAssetPartDetailDo> partDetails = assetDetailDo.getParts();

		if (isEmpty(partDetails)) {
			return null;
		}

		List<OrderSuppliesAssetPartDetailDo> partDetailsToProcess = new ArrayList<OrderSuppliesAssetPartDetailDo>();
		// List<OrderSuppliesAssetPartDetailDo> effectiveDates = new
		// ArrayList<OrderSuppliesAssetPartDetailDo>();
		// for (OrderSuppliesAssetPartDetailDo item : partDetails) {
		// if (item.getEffectiveEndDate() == null
		// || (item.getEffectiveEndDate().compareTo(
		// assetContract.getCurrentDate()) >= 0))
		// effectiveDates.add(item);
		// }
		List<? extends OrderSuppliesAssetPartDetailDo> withoutImpilcitFlags = selectWithoutImplicitFlag(partDetails);
		List<? extends OrderSuppliesAssetPartDetailDo> withImplicitFlags = selectWithImplicitFlag(partDetails);
		List<? extends OrderSuppliesAssetPartDetailDo> selectedPartDetails = selectMaxGroupPartDetailDo(withImplicitFlags);

		partDetailsToProcess.addAll(withoutImpilcitFlags);
		partDetailsToProcess.addAll(selectedPartDetails);

		List<Part> parts = new ArrayList<Part>();
		for (OrderSuppliesAssetPartDetailDo partDo : partDetailsToProcess) {
			if (partDo != null && partDistinct(partDo, parts)) {
				Part part = toPart(partDo, assetDetailDo, asset);
				parts.add(part);
			}
		}
		asset.setPartList(parts);
		return asset;
	}

	private static boolean partDistinct(OrderSuppliesAssetPartDetailDo partDo,
			List<Part> parts) {
		for (Part part2 : parts) {
			if (partDo.getPartNumber().equalsIgnoreCase(part2.getPartNumber())) {
				return false;
			}
		}
		return true;
	}

	private static Part toPart(OrderSuppliesAssetPartDetailDo partDo, OrderSuppliesAssetDetailDo assetDetailDo, Asset asset) {
		Part part = new Part();
		if (LangUtil.isNotBlank(assetDetailDo.getAccountSplitterFlag())	&& assetDetailDo.getAccountSplitterFlag().equalsIgnoreCase("y")) 
		{
			for (OrderSuppliesAssetPartPaymentDetailDo result : LangUtil
					.notNull(partDo.getPartPaymentList())) {
				if (!result.getPaymentType().contains("Adhoc")) {
					part.setPartNumber(partDo.getPartNumber());
					part.setDescription(partDo.getDescription());
					part.setConsumableType(partDo.getConsumableType());
					part.setPartType(partDo.getPartType());
					part.setImplicitFlag(partDo.getImplicitFlag());
					part.setCatalogId(partDo.getCatalogId());
					part.setPrinterPartNumber(partDo.getPrinterPartNumber());
					part.setAssetUsageType(partDo.getAssetUsageType());
					part.setAssetSerialNumber(partDo.getAssetSerialNumber());
					part.setYield(partDo.getYield());
					part.setSupplyId(partDo.getSupplyId());
					part.setProductId(partDo.getProductId());
					part.setPrefferedPartFlag(convertIntegerStringToBoolean(partDo
							.getPreferenceExplicit()));
					part.setProviderOrderNumber(partDo.getProviderOrderNumber());
					part.setVendorPartNumber(partDo.getVendorPartNumber());
					if ("Consumables Supplies Request".equalsIgnoreCase(partDo
							.getConsumableType())) {
						part.setMaxQuantity(asset.getTempSupplies());

					} else if ("Consumable SVC Parts Request"
							.equalsIgnoreCase(partDo.getConsumableType())) {
						part.setMaxQuantity(asset.getTempServices());
					}
					populatePartPaymentDetails(part, partDo.getPartPaymentList());
				}

			}
			return part;
		} else {
			part.setPartNumber(partDo.getPartNumber());
			part.setDescription(partDo.getDescription());
			part.setConsumableType(partDo.getConsumableType());
			part.setPartType(partDo.getPartType());
			part.setImplicitFlag(partDo.getImplicitFlag());
			part.setCatalogId(partDo.getCatalogId());
			part.setPrinterPartNumber(partDo.getPrinterPartNumber());
			part.setAssetUsageType(partDo.getAssetUsageType());
			part.setAssetSerialNumber(partDo.getAssetSerialNumber());
			part.setYield(partDo.getYield());
			part.setSupplyId(partDo.getSupplyId());
			part.setProductId(partDo.getProductId());
			part.setPrefferedPartFlag(convertIntegerStringToBoolean(partDo
					.getPreferenceExplicit()));
			part.setProviderOrderNumber(partDo.getProviderOrderNumber());
			part.setVendorPartNumber(partDo.getVendorPartNumber());
			populatePartPaymentDetails(part, partDo.getPartPaymentList());
			if ("Consumables Supplies Request".equalsIgnoreCase(partDo
					.getConsumableType())) {
				part.setMaxQuantity(asset.getTempSupplies());

			} else if ("Consumable SVC Parts Request".equalsIgnoreCase(partDo.getConsumableType())) {
				part.setMaxQuantity(asset.getTempServices());

			}
			return part;
		}
	}

	static List<? extends OrderSuppliesAssetPartDetailDo> selectMaxGroupPartDetailDo(
			List<? extends OrderSuppliesAssetPartDetailDo> partDetails) {
		if (isEmpty(partDetails)) {
			return partDetails;
		}
		List<OrderSuppliesAssetPartDetailDo> result = new ArrayList<OrderSuppliesAssetPartDetailDo>();
		Map<Object, List<OrderSuppliesAssetPartDetailDo>> consumableTypeMap = groupByConsumableType(partDetails);
		for (Entry<Object, List<OrderSuppliesAssetPartDetailDo>> entry : consumableTypeMap
				.entrySet()) {
			List<OrderSuppliesAssetPartDetailDo> partDetailsWithinGroup = entry
					.getValue();
			OrderSuppliesAssetPartDetailDo groupMax = maxByAssetSerialNumber(partDetailsWithinGroup);
			if (groupMax == null) {
				groupMax = maxByAssetUsageType(partDetailsWithinGroup);
			}
			if (groupMax == null) {
				groupMax = partDetailsWithinGroup.get(0); // first element
			}
			result.add(groupMax);
		}
		return result;
	}

	private static OrderSuppliesAssetPartDetailDo maxByAssetUsageType(
			List<OrderSuppliesAssetPartDetailDo> partDetails) {
		OrderSuppliesAssetPartDetailDo result = null;
		for (OrderSuppliesAssetPartDetailDo obj : partDetails) {
			String val = obj.getAssetUsageType();
			if (isEmpty(val)) {
				continue;
			}
			if (result == null) {
				result = obj;
				continue;
			}
			if (val.compareTo(result.getAssetUsageType()) > 0) {
				result = obj;
			}
		}
		return result;
	}

	private static OrderSuppliesAssetPartDetailDo maxByAssetSerialNumber(List<OrderSuppliesAssetPartDetailDo> partDetails) {
		OrderSuppliesAssetPartDetailDo result = null;
		for (OrderSuppliesAssetPartDetailDo obj : partDetails) {
			String val = obj.getAssetSerialNumber();
			if (isEmpty(val)) {
				continue;
			}
			if (result == null) {
				result = obj;
				continue;
			}
			if (val.compareTo(result.getAssetSerialNumber()) > 0) {
				result = obj;
			}
		}
		return result;
	}

	private static Map<Object, List<OrderSuppliesAssetPartDetailDo>> groupByConsumableType(List<? extends OrderSuppliesAssetPartDetailDo> partDetails) {
		Map<Object, List<OrderSuppliesAssetPartDetailDo>> m = new HashMap<Object, List<OrderSuppliesAssetPartDetailDo>>();
		for (OrderSuppliesAssetPartDetailDo pageCountDo : partDetails) {
			put(m, pageCountDo);
		}
		return m;
	}

	private static void put(Map<Object, List<OrderSuppliesAssetPartDetailDo>> m, OrderSuppliesAssetPartDetailDo partDetailDo) {
		/* For Display Part Type and Consumabel Type values have been swaped */
		Object key = partDetailDo.getPartType();
		List<OrderSuppliesAssetPartDetailDo> list = m.get(key);
		if (list == null) {
			list = new ArrayList<OrderSuppliesAssetPartDetailDo>();
			m.put(key, list);
		}
		list.add(partDetailDo);
	}

	static List<? extends OrderSuppliesAssetPartDetailDo> selectWithImplicitFlag(List<? extends OrderSuppliesAssetPartDetailDo> partDetails) {
		if (isEmpty(partDetails)) {
			return Collections.emptyList();
		}
		List<OrderSuppliesAssetPartDetailDo> result = new ArrayList<OrderSuppliesAssetPartDetailDo>();
		for (OrderSuppliesAssetPartDetailDo item : partDetails) {
			if (Boolean.TRUE.equals(item.getImplicitFlag())) {
				result.add(item);
			}
		}
		return result;
	}

	static List<? extends OrderSuppliesAssetPartDetailDo> selectWithoutImplicitFlag(List<? extends OrderSuppliesAssetPartDetailDo> partDetails) {
		if (isEmpty(partDetails)) {
			return Collections.emptyList();
		}
		List<OrderSuppliesAssetPartDetailDo> result = new ArrayList<OrderSuppliesAssetPartDetailDo>();
		for (OrderSuppliesAssetPartDetailDo item : partDetails) {
			if (item.getImplicitFlag() == null || Boolean.FALSE.equals(item.getImplicitFlag())) {
				result.add(item);
			}
		}
		return result;
	}

	static List<PageCounts> processMpsMeterReads(Asset asset, List<MPSMeterRead> mpsMeterReads) {
		List<PageCounts> pageCounts = new ArrayList<PageCounts>();
		if (!isEmpty(mpsMeterReads)) {
			for (MPSMeterRead readDo : mpsMeterReads) {
				if (!"Mono".equalsIgnoreCase(readDo.getType()) && !"0".equals(readDo.getValue()) && !readDo.getType().toLowerCase().contains("Mono".toLowerCase())) {
					PageCounts pc = new PageCounts();
					pc.setName(readDo.getType());
					pc.setCount(readDo.getValue());
					pc.setDate(readDo.getDate());
					pageCounts.add(pc);
				}

			}
		}
		return pageCounts;
	}

	static List<PageCounts> processPageCounts(Asset asset, Collection<? extends OrderSuppliesAssetPageCountDo> pageCountDos) {
		List<PageCounts> pageCountsList = new ArrayList<PageCounts>();
		if (isNotEmpty(pageCountDos)) {
			for (OrderSuppliesAssetPageCountDo pageCountDo : pageCountDos) {
				if (pageCountDo.getName() != null && (pageCountDo.getName().toLowerCase().contains("mono"))) {
					continue;
				}
				PageCounts pageCounts = new PageCounts();
				pageCounts.setName(pageCountDo.getName());
				OrderSuppliesAssetPageCountReadingDo latestReading = LangUtil.first(pageCountDo.getLatestReadings());
				if (latestReading != null) {
					pageCounts.setCount(latestReading.getReading());
					pageCounts.setDate(latestReading.getTimestamp());
				}
				pageCountsList.add(pageCounts);
			}
		}
		return pageCountsList;
	}

	private static boolean convertStringToBoolean(String str) {

		if (isEmpty(str)) {
			return false;
		} else if (str.equalsIgnoreCase("Y")) {
			return true;

		} else {
			return Boolean.parseBoolean(str);
		}

	}

	/*
	 * private static void populateSapCatalogInfo(Account account,
	 * ArrayList<OrderSupplyAssetDetailCatalogDo> sapCatalogs) {
	 * 
	 * if(isEmpty(sapCatalogs)) { account.setSoldToNumbers(new
	 * ArrayList<String>()); account.setSalesOrgs(new ArrayList<String>());
	 * return; }
	 * 
	 * List<String> soldToNumbers = new ArrayList<String>(sapCatalogs.size());
	 * List<String> salesOrgs = new ArrayList<String>(sapCatalogs.size());
	 * 
	 * for (OrderSupplyAssetDetailCatalogDo sapCatalogDo : sapCatalogs) {
	 * soldToNumbers.add(sapCatalogDo.getSoldToNumber());
	 * salesOrgs.add(sapCatalogDo.getSalesOrg()); }
	 * 
	 * account.setSoldToNumbers(soldToNumbers); account.setSalesOrgs(salesOrgs);
	 * }
	 */

	private static void populateShowPrice(Account account,
			OrderSuppliesAssetDetailDo item) {
		  for (OrderSuppliesAssetAgreementEntitlementDo entitlement : LangUtil.notNull(item.getAgreementEntitlements())) {
			  if(entitlement!=null) {
				
				  return;
			  }
		  }
	}
	
	private static void populatePartPaymentDetails(Part part,ArrayList<OrderSuppliesAssetPartPaymentDetailDo> partPaymentList) {

		if (partPaymentList == null) {
			return;
		}

		List<String> soldToNumbers = new ArrayList<String>();
		List<String> paymentTypes = new ArrayList<String>();

		for (OrderSuppliesAssetPartPaymentDetailDo partDetail : partPaymentList) {
			if (partDetail != null  &&  !partDetail.getPaymentType().contains("Adhoc")) {
				if (!AmindServiceUtil.listContainsItemOrItemIsBlank(
						soldToNumbers, partDetail.getSoldToNumber())) {
					soldToNumbers.add(partDetail.getSoldToNumber());
				}
				if (!AmindServiceUtil.listContainsItemOrItemIsBlank(
						paymentTypes, partDetail.getPaymentType())) {
					paymentTypes.add(partDetail.getPaymentType());
				}
			}
		}

		part.setSoldToNumbers(soldToNumbers);
		part.setPaymentTypes(paymentTypes);

		OrderSuppliesAssetPartPaymentDetailDo paymentDetail = partPaymentList.get(0);

		if (paymentDetail != null) {
			part.setSalesOrg(paymentDetail.getSalesOrg());
			part.setContractLineItemId(paymentDetail.getContractLineItem());
			part.setContractNo(paymentDetail.getContractNumber());
		}

	}

	private static boolean convertIntegerStringToBoolean(String preferenceExplicit) {

		if (preferenceExplicit.equalsIgnoreCase("1")) {
			return true;
		}

		return false;
	}

	private static String populateAgreementId(OrderSuppliesAssetDetailDo item) {
		if (item != null && isNotEmpty(item.getAgreementEntitlements())) {
			return item.getAgreementEntitlements().get(0).getAgreementId();
		}
		return null;
	}
	
	private static List<AccountContact> populateDeviceContacts(ArrayList<AssetDetailContactDo> deviceContacts) {

		List<AccountContact> contacts = new ArrayList<AccountContact>();
		
		if(LangUtil.isNotEmpty(deviceContacts)) {
			for (AssetDetailContactDo contact : deviceContacts) {
				AccountContact accountContact = new AccountContact();

				accountContact.setDeviceContactType(contact.getDeviceContactType());
				accountContact.setFirstName(contact.getFirstName());
				accountContact.setLastName(contact.getLastName());
				accountContact.setWorkPhone(contact.getWorkPhone());
				accountContact.setEmailAddress(contact.getEmailAddress());
				accountContact.setHomePhone(contact.getHomePhone());
				accountContact.setContactId(contact.getContactId());
				GenericAddress contactAddress = new GenericAddress();
				contactAddress.setAddressLine1(contact.getAddressLine1());
				contactAddress.setAddressLine2(contact.getAddressLine2());
				contactAddress.setAddressLine3(contact.getAddressLine3());
				contactAddress.setCity(contact.getCity());
				contactAddress.setState(contact.getState());
				contactAddress.setPostalCode(contact.getPostalCode());
				contactAddress.setProvince(contact.getProvince());
				contactAddress.setCountry(contact.getCountry());
				contactAddress.setAddressName(contact.getAddressName());
				contactAddress.setAddressId(contact.getAddressId());
				contactAddress.setStoreFrontName(contact.getStoreFrontName());
				contactAddress.setCounty(contact.getCounty());
				contactAddress.setDistrict(contact.getDistrict());
				contactAddress.setOfficeNumber(contact.getOfficeNumber());
				contactAddress.setCountryISOCode(contact.getCountryIsoCode());
				contactAddress.setRegion(contact.getRegion());
				contactAddress.setAddressId(contact.getAddressId());
				contactAddress.setPhysicalLocation2(contact.getBuilding());
				contactAddress.setPhysicalLocation3(contact.getFloor());

				accountContact.setAddress(contactAddress);
				contacts.add(accountContact);
			}
		}
		return contacts;
	}
	
	
	private static Boolean processEntitlmentType(OrderSuppliesAssetDetailDo asset) {
		for (OrderSuppliesAssetAgreementEntitlementDo type : LangUtil.notNull(asset.getAgreementEntitlements())) {
			if (type != null && type.getEntitlementStatus() != null && type.getEntitlementStatus().equalsIgnoreCase("Valid")) {
				if (type.getEntitlementType() != null) {
					if (type.getEntitlementType().startsWith("Consumable")) {
						return Boolean.TRUE;
					}
				}
			}

		}
		return Boolean.FALSE;
	}


}
