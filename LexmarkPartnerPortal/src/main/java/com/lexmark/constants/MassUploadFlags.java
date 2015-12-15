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
package com.lexmark.constants;

public enum MassUploadFlags {
	
	/**Dont change the order of the declaration
	 * as this will hamper the left nav configuration.
	 * */
	/**Constants for Hardware Debrief*/
	MASSUPLOADHARDWARE("massUploadInstallDebriefFlag",LexmarkPPConstants.HARDWAREORDERGRIDID,LexmarkPPConstants.LINK_ID_HARDWAREORDER),
	/**Constants for Hardware Debrief*/
	MASSUPLOADCONSUMABLES("massUploadConsumablesFlag",LexmarkPPConstants.SERVICEORDERGRIDID,LexmarkPPConstants.LINK_ID_SERVICEORDER);
	/**Constants for Hardware Debrief*/
	private String flagName;// this flag value comes from account selection
	/**Constants for Hardware Debrief*/
	private String gridID;// grid id where the grid will be created
	/**Constants for Hardware Debrief*/
	private String linkID;//mass upload leftnav link id
	
	/**
	 * @param flagName 
	 * @param gridId 
	 * @param linkId 
	 */
	private MassUploadFlags(String flagName,String gridId,String linkId){
		this.flagName=flagName;
		this.gridID=gridId;
		this.linkID=linkId;
	}
	/**
	 * @return flagName 
	 */
	public String getFlagName(){
		return flagName;
	}
	/**
	 * @return gridId 
	 */
	public String getGridID(){
		return gridID;
	}
	/**
	 * @return  linkId
	 */
	public String getLinkID(){
		return linkID;
	}
}
