package com.lexmark.domain;

import java.io.Serializable;
public class DownloadClaimPart implements Serializable {
	
	private static final long serialVersionUID = -6543145986196131918L;
	
	
	private String recPartFlag ;
	private String recPartName ;
	private String recPartsErrCd1 ;
	private String recPartsErrCd2 ;
	private String recPartsQty ;
	private String recPartsRetCarrier;
	private String recPartsRetTrackNum ;
	private String recPartsStatus;
	
	
			    
	/**
	 * @return the recPartFlag
	 */
	public String getRecPartFlag() {
		return recPartFlag;
	}
	/**
	 * @param recPartFlag the recPartFlag to set
	 */
	public void setRecPartFlag(String recPartFlag) {
		this.recPartFlag = recPartFlag;
	}
	/**
	 * @return the recPartName
	 */
	public String getRecPartName() {
		return recPartName;
	}
	/**
	 * @param recPartName the recPartName to set
	 */
	public void setRecPartName(String recPartName) {
		this.recPartName = recPartName;
	}
	/**
	 * @return the recPartsErrCd1
	 */
	public String getRecPartsErrCd1() {
		return recPartsErrCd1;
	}
	/**
	 * @param recPartsErrCd1 the recPartsErrCd1 to set
	 */
	public void setRecPartsErrCd1(String recPartsErrCd1) {
		this.recPartsErrCd1 = recPartsErrCd1;
	}
	/**
	 * @return the recPartsErrCd2
	 */
	public String getRecPartsErrCd2() {
		return recPartsErrCd2;
	}
	/**
	 * @param recPartsErrCd2 the recPartsErrCd2 to set
	 */
	public void setRecPartsErrCd2(String recPartsErrCd2) {
		this.recPartsErrCd2 = recPartsErrCd2;
	}
	/**
	 * @return the recPartsQty
	 */
	public String getRecPartsQty() {
		return recPartsQty;
	}
	/**
	 * @param recPartsQty the recPartsQty to set
	 */
	public void setRecPartsQty(String recPartsQty) {
		this.recPartsQty = recPartsQty;
	}
	/**
	 * @return the recPartsRetCarrier
	 */
	public String getRecPartsRetCarrier() {
		return recPartsRetCarrier;
	}
	/**
	 * @param recPartsRetCarrier the recPartsRetCarrier to set
	 */
	public void setRecPartsRetCarrier(String recPartsRetCarrier) {
		this.recPartsRetCarrier = recPartsRetCarrier;
	}
	/**
	 * @return the recPartsRetTrackNum
	 */
	public String getRecPartsRetTrackNum() {
		return recPartsRetTrackNum;
	}
	/**
	 * @param recPartsRetTrackNum the recPartsRetTrackNum to set
	 */
	public void setRecPartsRetTrackNum(String recPartsRetTrackNum) {
		this.recPartsRetTrackNum = recPartsRetTrackNum;
	}
	/**
	 * @return the recPartsStatus
	 */
	public String getRecPartsStatus() {
		return recPartsStatus;
	}
	/**
	 * @param recPartsStatus the recPartsStatus to set
	 */
	public void setRecPartsStatus(String recPartsStatus) {
		this.recPartsStatus = recPartsStatus;
	}
	
	
}
