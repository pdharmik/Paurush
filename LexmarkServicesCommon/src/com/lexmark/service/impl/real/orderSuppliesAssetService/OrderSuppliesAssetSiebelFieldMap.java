package com.lexmark.service.impl.real.orderSuppliesAssetService;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class OrderSuppliesAssetSiebelFieldMap {
	public static Map<String, String> allFieldMap() {
		Map<String, String> m = new HashMap<String, String>();
		m.put("assetTag", "Owner Asset Number");
		m.put("serialNumber", "Serial Number");
//		m.put("installAddress.addressName", "Install Address Name");
		m.put("installAddress.addressName", "LXK C Installed Address Name");
		m.put("installAddress.addressLine1", "LXK UP Install Address Line 1");
		m.put("installAddress.state", "Install State");
		m.put("installAddress.city", "Install City");
		m.put("installAddress.province", "Install Province");
		m.put("installAddress.postalCode", "Install Postal Code");
		m.put("installAddress.country", "Install Country");
		m.put("installAddress.county", "Install County");
		m.put("installAddress.district", "Install District");
		m.put("installAddress.officeNumber", "Install House #");
		m.put("installAddress.levelOfDetails", "LXK LBS Level of Detail");
		m.put("assetContact.firstName", "LXK SW Primary Contact First Name");
		m.put("assetContact.lastName", "LXK SW Primary Contact Last Name");
		m.put("assetContact.emailAddress", "LXK SW Primary Contact Email");
		m.put("assetContact.workPhone", "LXK SW Primary Contact Work Phone");
		m.put("productTLI", "Product Name");
		m.put("productLine", "LXK Product Model Name");
		m.put("descriptionLocalLang","LXK MPS Local Lang Description");
		m.put("mdmLevel1AccountId", "LXK SW Agreement Account Global DUNS");
		m.put("mdmLevel2AccountId", "LXK SW Agreement Account Domestic DUNS");
		m.put("mdmLevel3AccountId", "LXK SW Agreement Account LEGAL MDM ID");
		m.put("mdmLevel4AccountId", "LXK SW Agreement Account MDM Account");
		m.put("mdmLevel5AccountId", "LXK SW Agreement Account Id");
		m.put("accountTransFlag", "LXK L5 Account Transactable Flag");
		m.put("modelNumber", "LXK C MTM Name");
		m.put("hostName", "Host Name");
		m.put("ipAddress", "IP Address");
		m.put("deviceTag", "Device Tag Customer");
		m.put("devicePhase", "Device Phase");
		m.put("macAddress", "MAC Address");
		m.put("createDate", "LXK SW Asset Created Date");
		m.put("account.accountName", "Account Name");
		m.put("county", "LXK Install County");
		m.put("officeNumber", "LXK Install House #");
		m.put("district", "Install District");
		return Collections.unmodifiableMap(m);
	}

	public static Map<String, String> allFavoriteFieldMap() {
		Map<String, String> favFieldMap = new HashMap<String, String>();
		favFieldMap.put("assetTag", "Owner Asset Number");
		favFieldMap.put("serialNumber", "Serial Number");
		favFieldMap.put("installAddress.addressName", "Install Address Name");
		favFieldMap.put("installAddress.addressLine1", "Install Address Line 1");
		favFieldMap.put("installAddress.state", "Install Address State");
		favFieldMap.put("installAddress.city", "Install Address City");
		favFieldMap.put("installAddress.province", "Install Address Province");
		favFieldMap.put("installAddress.country", "Install Address Country");
		favFieldMap.put("assetContact.firstName", "Primary Contact First Name");
		favFieldMap.put("assetContact.lastName", "Primary Contact Last Name");
		favFieldMap.put("assetContact.emailAddress", "Primary Contact Email Address");
		favFieldMap.put("assetContact.workPhone", "Primary Contact Work Phone");
		favFieldMap.put("productTLI", "Product Name");
		favFieldMap.put("hostName", "Host Name");
		favFieldMap.put("modelNumber", "MTM Name");
		favFieldMap.put("ipAddress", "IP Address");
		favFieldMap.put("deviceTag", "Device Tag Customer");
		favFieldMap.put("devicePhase", "Device Phase");
		favFieldMap.put("macAddress", "MAC Address");
		favFieldMap.put("productLine", "LXK Product Model Name");
		favFieldMap.put("installAddress.postalCode", "Install Address Zip");
		favFieldMap.put("mdmLevel5AccountId", "LXK SW Agreement Account Id");
		favFieldMap.put("accountName", "Account Name");
		
		favFieldMap.put("county", "LXK MPS County");
		favFieldMap.put("district", "LXK MPS District");
		favFieldMap.put("officeNumber", "LXK MPS Office");
		
		return Collections.unmodifiableMap(favFieldMap);
	}


	public static Map<String, String> accountvaluedFieldMap() {
		Map<String, String> m = new HashMap<String, String>();
		m.put("mdmLevel1AccountId", "LXK MPS Ent Global DUNS");
		m.put("mdmLevel2AccountId", "LXK MPS Domestic DUNS");
		m.put("mdmLevel3AccountId", "LXK MPS Legal");
		m.put("mdmLevel4AccountId", "LXK MPS MDM");
		m.put("mdmLevel5AccountId", "LXK MPS Account Id");
		m.put("mdmLevel", "LXK MPS Ent Account Level");
		return Collections.unmodifiableMap(m);
	}

	public static Map<String, String> contractedvaluedFieldMap() {
		Map<String, String> m = new HashMap<String, String>();
		m.put("mdmLevel1AccountId", "LXK SW Agreement Account Global DUNS");
		m.put("mdmLevel2AccountId", "LXK SW Agreement Account Domestic DUNS");
		m.put("mdmLevel3AccountId", "LXK SW Agreement Account LEGAL MDM ID");
		m.put("mdmLevel4AccountId", "LXK SW Agreement Account MDM Account");
		m.put("mdmLevel5AccountId", "LXK SW Agreement Account Id");
		m.put("mdmLevel", "LXK SW Agreement Account MDM Level");
		return Collections.unmodifiableMap(m);
	}

	public static Map<String, String> favoriteAccountvaluedFieldMap() {
		Map<String, String> m = new HashMap<String, String>();
		m.put("mdmLevel1AccountId", "LXK MPS Ent Global DUNS");
		//m.put("mdmLevel2AccountId", "LXK MPS Ent Domestic DUNS");
		m.put("mdmLevel2AccountId", "LXK MPS Domestic DUNS");
		m.put("mdmLevel3AccountId", "LXK MPS Legal");
		m.put("mdmLevel4AccountId", "LXK MPS MDM");
		m.put("mdmLevel5AccountId", "LXK MPS Account Id");
		m.put("mdmLevel", "LXK MPS Ent MDM Level");
		return Collections.unmodifiableMap(m);
	}

	public static Map<String, String> favoriteContractedvaluedFieldMap() {
		Map<String, String> m = new HashMap<String, String>();
		m.put("mdmLevel1AccountId", "LXK SW Agreement Account Global DUNS");
		m.put("mdmLevel2AccountId", "LXK SW Agreement Account Domestic DUNS");
		m.put("mdmLevel3AccountId", "LXK SW Agreement Account LEGAL MDM ID");
		m.put("mdmLevel4AccountId", "LXK SW Agreement Account MDM Account");
		m.put("mdmLevel5AccountId", "LXK SW Agreement Account Id");
		m.put("mdmLevel", "LXK SW Agreement Account MDM Level");
		return Collections.unmodifiableMap(m);
	}
}
