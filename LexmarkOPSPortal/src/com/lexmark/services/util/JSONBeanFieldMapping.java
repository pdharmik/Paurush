/**
*
* Copyright 2014.   
* 
* Customer specific copyright notice     :Constants declared throughout the portal.
*
* File Name       : MassUploadFlags.java
*
* Description     :Project desc.
*
* Version         : 1.0
*
* Created Date    :2014
 
* Modification History:
*
* ---------------------------------------------------------------------
* Author 				Date				Version  		Comments
* ---------------------------------------------------------------------
*/
package com.lexmark.services.util;

public enum JSONBeanFieldMapping {
	
	
	DEVICEID("deviceId","id"),
	ASSETTAG("assettag","assetTag"),
	ACCOUNTNAME("accountName","accountName"),DEVICEHOSTNAME("deviceHostname","hostName"),
	DEVICEPHASE("devicePhase","devicePhase"),
	COSTCENTER("costCenter","assetCostCenter"),ASSETPRODUCT("assetProduct","productName"),
    ASSETINSTALLDATE("assetInstallDate","installDate:formatDate"),MODELTYPEFAMILY("modelTypeFamily","productFamily"),
   // TRDPARTYDEVICES("trdPartyDevices"),
   // PRODUCTTYPE("productType","productType"), //Commented for defect Inter portal 17367
    PRODUCTSERIES("productSeries","productSeries"),BRAND("brand","brandMFGProduct"),
    COLORMONO("colorMono","monoColor"),SINGLEMFP("singleMFP","funtionPrinterType"),
    OWNERSHIP("ownership","ownership"),
    DEPARTMENT("department","departmentName"),METERREADTYPE("meterReadType","meterSource"),
	CONTACTFIRSTNAME("firstName","primaryContactFirstName"),CONTACTLASTNAME("lastName","primaryContactLastName"),
	CONTACTEMAILADDRESS("email","primaryContactEmail"),
	CONTACTWORKPHONE("workPhone","primaryContactWorkPhone"),CONTACTALTERNATEPHONE("alternatePhone","primaryContactAlternatePhone"),
	LXKASSETTAG("lxkAssetTag","lxkAssetTag"),ASSETLIFECYCLE("assetLifeCycle","assetLifeCycle"),
	HARDWARESTATUS("hardwareStatus","hardwareStatus"),PARTTYPE("partType","partType"),ADDRESSLEVELLOD("addressLevelLOD","addressLevelDetails"),FLOORLEVELLOD("floorLevelLOD","floorLevelDetails"),BUILDINGTYPE("buildingType","buildingType");
	//MODELTYPE("modelType","modelType");
	
	
	private String jsonParam;
	
	private String beanField;
	
	
	
	/**
	 * @param flagName 
	 * @param gridId 
	 * @param linkId 
	 */
	private JSONBeanFieldMapping(String jsonParam,String beanField){
		this.jsonParam=jsonParam;
		this.beanField=beanField;
	}


	/**
	 * @return the jsonParam
	 */
	public String getJsonParam() {
		return jsonParam;
	}



	/**
	 * @return the beanField
	 */
	public String getBeanField() {
		return beanField;
	}
	
}
