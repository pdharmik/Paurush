package com.lexmark.domain;

import java.io.Serializable;
import java.util.List;

import com.google.gson.annotations.SerializedName;

public class RequestFilter implements Serializable {
	
	private static final long serialVersionUID = -1567980146289543332L;
	//Annotations For Service Request
	
			@SerializedName("reqNo")
			private String sRNumber;
			
			@SerializedName("reqType")
			private List<String> sRType;
			
			@SerializedName("reqStat")
			private List<String> webStatus;

			public String getsRNumber() {
				return sRNumber;
			}

			public void setsRNumber(String sRNumber) {
				this.sRNumber = sRNumber;
			}

			public List<String> getsRType() {
				return sRType;
			}

			public void setsRType(List<String> sRType) {
				this.sRType = sRType;
			}

			public List<String> getWebStatus() {
				return webStatus;
			}

			public void setWebStatus(List<String> webStatus) {
				this.webStatus = webStatus;
			}

			public String getsRCreatedStartDate() {
				return sRCreatedStartDate;
			}

			public void setsRCreatedStartDate(String sRCreatedStartDate) {
				this.sRCreatedStartDate = sRCreatedStartDate;
			}

			public String getsRCreatedEndDate() {
				return sRCreatedEndDate;
			}

			public void setsRCreatedEndDate(String sRCreatedEndDate) {
				this.sRCreatedEndDate = sRCreatedEndDate;
			}

			public String getHelpDeskRefNum() {
				return helpDeskRefNum;
			}

			public void setHelpDeskRefNum(String helpDeskRefNum) {
				this.helpDeskRefNum = helpDeskRefNum;
			}

			public List<String> getsRArea() {
				return sRArea;
			}

			public void setsRArea(List<String> sRArea) {
				this.sRArea = sRArea;
			}

			public List<String> getsRSubArea() {
				return sRSubArea;
			}

			public void setsRSubArea(List<String> sRSubArea) {
				this.sRSubArea = sRSubArea;
			}

			public List<String> getsRStatus() {
				return sRStatus;
			}

			public void setsRStatus(List<String> sRStatus) {
				this.sRStatus = sRStatus;
			}

			public List<String> getsRSubStatus() {
				return sRSubStatus;
			}

			public void setsRSubStatus(List<String> sRSubStatus) {
				this.sRSubStatus = sRSubStatus;
			}

			public List<String> getsRSource() {
				return sRSource;
			}

			public void setsRSource(List<String> sRSource) {
				this.sRSource = sRSource;
			}

			public static long getSerialversionuid() {
				return serialVersionUID;
			}

			@SerializedName("stDt")
			private String sRCreatedStartDate;
			
			@SerializedName("edDt")
			private String sRCreatedEndDate;
			
			@SerializedName("hpDskRf")
			private String helpDeskRefNum;
			
			@SerializedName("srAr")
			private List<String> sRArea;
			
			@SerializedName("srSuAr")
			private List<String> sRSubArea;
			
			
			@SerializedName("srSt")
			private List<String> sRStatus;
			
			@SerializedName("srSuSt")
			private List<String> sRSubStatus;
			
			@SerializedName("srSrc")
			private List<String> sRSource;

			
			
	}

