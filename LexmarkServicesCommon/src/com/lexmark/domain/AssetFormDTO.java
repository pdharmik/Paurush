package com.lexmark.domain;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AssetFormDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7785281089712533687L;
	//private Asset assetDetail;
	private String customerCostCode;
    private String department;
    private String specialInstruction;    
    private String custReferenceId;
    private String costCenter;
    private String addtnlDescription;
    
    private String productNo;	
	private Date installDate;
	private String customerAssetTag;
	private int dataPort;
	private int faxPort;
	private String printQueue;
	private String serialNumber;
	private String assetTag;
	private String ipAddress;
	private String deviceName;
	private String modelType;
	private int monoPageCount;
	private String ipGateway;
	private String ipMask;
		
	/********************Added for Manage Asset Toner Delivery Address and Contact*************/
    private GenericAddress tonerDeliveryAddress;
    private AccountContact tonerDeliveryContact;
    
    
	public String getProductNo() {
		return productNo;
	}
	public void setProductNo(String productNo) {
		this.productNo = productNo;
	}
	public Date getInstallDate() {
		return installDate;
	}
	public void setInstallDate(Date installDate) {
		/*DateFormat dFormat=new SimpleDateFormat("dd/mm/yyyy hh:mm:ss z");
		try {
			this.installDate=dFormat.parse(installDate);
		} catch (ParseException e) {
			
			e.printStackTrace();
		}*/
		this.installDate=installDate;
	}
	public String getCustomerAssetTag() {
		return customerAssetTag;
	}
	public void setCustomerAssetTag(String customerAssetTag) {
		this.customerAssetTag = customerAssetTag;
	}
	public int getDataPort() {
		return dataPort;
	}
	public void setDataPort(int dataPort) {
		this.dataPort = dataPort;
	}
	public int getFaxPort() {
		return faxPort;
	}
	public void setFaxPort(int faxPort) {
		this.faxPort = faxPort;
	}
	public String getPrintQueue() {
		return printQueue;
	}
	public void setPrintQueue(String printQueue) {
		this.printQueue = printQueue;
	}
	public String getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	public String getAssetTag() {
		return assetTag;
	}
	public void setAssetTag(String assetTag) {
		this.assetTag = assetTag;
	}
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	public String getDeviceName() {
		return deviceName;
	}
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	public String getModelType() {
		return modelType;
	}
	public void setModelType(String modelType) {
		this.modelType = modelType;
	}
	public int getMonoPageCount() {
		return monoPageCount;
	}
	public void setMonoPageCount(int monoPageCount) {
		this.monoPageCount = monoPageCount;
	}
	public String getIpGateway() {
		return ipGateway;
	}
	public void setIpGateway(String ipGateway) {
		this.ipGateway = ipGateway;
	}
	public String getIpMask() {
		return ipMask;
	}
	public void setIpMask(String ipMask) {
		this.ipMask = ipMask;
	}
	public GenericAddress getTonerDeliveryAddress() {
		return tonerDeliveryAddress;
	}
	public void setTonerDeliveryAddress(GenericAddress tonerDeliveryAddress) {
		this.tonerDeliveryAddress = tonerDeliveryAddress;
	}
	public AccountContact getTonerDeliveryContact() {
		return tonerDeliveryContact;
	}
	public void setTonerDeliveryContact(AccountContact tonerDeliveryContact) {
		this.tonerDeliveryContact = tonerDeliveryContact;
	}
	/*public Asset getAssetDetail() {
		return assetDetail;
	}
	
	public void setAssetDetail(Asset assetDetail) {
		this.assetDetail = assetDetail;
	}*/
	public String getCustomerCostCode() {
		return customerCostCode;
	}
	public void setCustomerCostCode(String customerCostCode) {
		this.customerCostCode = customerCostCode;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getSpecialInstruction() {
		return specialInstruction;
	}
	public void setSpecialInstruction(String specialInstruction) {
		this.specialInstruction = specialInstruction;
	}
	public String getCustReferenceId() {
		return custReferenceId;
	}
	public void setCustReferenceId(String custReferenceId) {
		this.custReferenceId = custReferenceId;
	}
	public String getCostCenter() {
		return costCenter;
	}
	public void setCostCenter(String costCenter) {
		this.costCenter = costCenter;
	}
	public String getAddtnlDescription() {
		return addtnlDescription;
	}
	public void setAddtnlDescription(String addtnlDescription) {
		this.addtnlDescription = addtnlDescription;
	}
}
