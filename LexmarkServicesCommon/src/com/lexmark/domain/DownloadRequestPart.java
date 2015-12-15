package com.lexmark.domain;

import java.io.Serializable;
public class DownloadRequestPart implements Serializable {
	
	
	private static final long serialVersionUID = 9190085155439361594L;
	
	private String recPartsDisp ;
	/**
	 * @return the recPartsDisp
	 */
	public String getRecPartsDisp() {
		return recPartsDisp;
	}
	/**
	 * @param recPartsDisp the recPartsDisp to set
	 */
	public void setRecPartsDisp(String recPartsDisp) {
		this.recPartsDisp = recPartsDisp;
	}
	/**
	 * @return the recPartsName
	 */
	public String getRecPartsName() {
		return recPartsName;
	}
	/**
	 * @param recPartsName the recPartsName to set
	 */
	public void setRecPartsName(String recPartsName) {
		this.recPartsName = recPartsName;
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
	private String recPartsName ;
	private String recPartsErrCd1 ;
	private String recPartsErrCd2 ;
	private String recPartsQty ;
	private String recPartsRetCarrier;
	private String recPartsRetTrackNum ;
	
		
	
}
