package com.lexmark.domain;

import java.io.Serializable;
import java.util.List;

import com.google.gson.annotations.SerializedName;

public class DeviceFilter implements Serializable{

	private static final long serialVersionUID = -1583823709482322332L;
	
	//Device Fields Annotations
		@SerializedName("srNm")
		private String assetSerialNumber;
		
		@SerializedName("ipAddr")
		private String iPAddress;
		
		@SerializedName("prmdl")
		private String productModel;
		
		@SerializedName("prNm")
		private String productName;
		
		@SerializedName("instDtF")
		private String installStartDate;
		
		@SerializedName("instDtT")
		private String installEndDate;
		
		@SerializedName("mdTyp")
		private String productModelType;
		
		@SerializedName("prTyp")
		private List<String> productType;
		
		@SerializedName("prSr")
		private List<String> productSeries;
		
		@SerializedName("br")
		private String productBrand;
		
		@SerializedName("coctr")
		private String costCenter;
		
		@SerializedName("dpt")
		private String departmentName;
		
		public String getAssetSerialNumber() {
			return assetSerialNumber;
		}

		public void setAssetSerialNumber(String assetSerialNumber) {
			this.assetSerialNumber = assetSerialNumber;
		}

		public String getiPAddress() {
			return iPAddress;
		}

		public void setiPAddress(String iPAddress) {
			this.iPAddress = iPAddress;
		}

		public String getProductModel() {
			return productModel;
		}

		public void setProductModel(String productModel) {
			this.productModel = productModel;
		}

		public String getProductName() {
			return productName;
		}

		public void setProductName(String productName) {
			this.productName = productName;
		}

		public String getInstallStartDate() {
			return installStartDate;
		}

		public void setInstallStartDate(String installStartDate) {
			this.installStartDate = installStartDate;
		}

		public String getInstallEndDate() {
			return installEndDate;
		}

		public void setInstallEndDate(String installEndDate) {
			this.installEndDate = installEndDate;
		}

		public String getProductModelType() {
			return productModelType;
		}

		public void setProductModelType(String productModelType) {
			this.productModelType = productModelType;
		}

		public List<String> getProductType() {
			return productType;
		}

		public void setProductType(List<String> productType) {
			this.productType = productType;
		}

		public List<String> getProductSeries() {
			return productSeries;
		}

		public void setProductSeries(List<String> productSeries) {
			this.productSeries = productSeries;
		}

		public String getProductBrand() {
			return productBrand;
		}

		public void setProductBrand(String productBrand) {
			this.productBrand = productBrand;
		}

		public String getCostCenter() {
			return costCenter;
		}

		public void setCostCenter(String costCenter) {
			this.costCenter = costCenter;
		}

		public String getDepartmentName() {
			return departmentName;
		}

		public void setDepartmentName(String departmentName) {
			this.departmentName = departmentName;
		}

		public String getCustomerAssetTag() {
			return customerAssetTag;
		}

		public void setCustomerAssetTag(String customerAssetTag) {
			this.customerAssetTag = customerAssetTag;
		}

		public String getHostName() {
			return hostName;
		}

		public void setHostName(String hostName) {
			this.hostName = hostName;
		}

		public List<String> getAssetLifeCycle() {
			return assetLifeCycle;
		}

		public void setAssetLifeCycle(List<String> assetLifeCycle) {
			this.assetLifeCycle = assetLifeCycle;
		}

		public List<String> getDevicePhase() {
			return devicePhase;
		}

		public void setDevicePhase(List<String> devicePhase) {
			this.devicePhase = devicePhase;
		}

		public String getAssetMeterSource() {
			return assetMeterSource;
		}

		public void setAssetMeterSource(String assetMeterSource) {
			this.assetMeterSource = assetMeterSource;
		}

		public String getlXKAssetTag() {
			return lXKAssetTag;
		}

		public void setlXKAssetTag(String lXKAssetTag) {
			this.lXKAssetTag = lXKAssetTag;
		}

		public List<String> getHardwareStatus() {
			return hardwareStatus;
		}

		public void setHardwareStatus(List<String> hardwareStatus) {
			this.hardwareStatus = hardwareStatus;
		}

		public String getMdmID() {
			return mdmID;
		}

		public void setMdmID(String mdmID) {
			this.mdmID = mdmID;
		}

		public static long getSerialversionuid() {
			return serialVersionUID;
		}

		@SerializedName("astg")
		private String customerAssetTag;
		
		@SerializedName("hstNm")
		private String hostName;
		
		@SerializedName("asLf")
		private List<String> assetLifeCycle;
		
		@SerializedName("dvPh")
		private List<String> devicePhase;
		
		@SerializedName("mRdType")
		private String assetMeterSource;
		
		@SerializedName("lAstg")
		private String lXKAssetTag;
		
		@SerializedName("hwSt")
		private List<String> hardwareStatus;
		
		@SerializedName("mId")
		private String mdmID;

		
}
