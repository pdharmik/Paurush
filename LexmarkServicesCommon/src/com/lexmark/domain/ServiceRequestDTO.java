package com.lexmark.domain;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ServiceRequestDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7785281089712533687L;
	
	//Holds the old asset identifiers in CM-Manage Asset (Change Asset) & New in Add Asset
	private Asset assetDetail;
	private AccountContact contact;
	private GenericAddress address;
	private ServiceRequest serviceRequest;
	
	private AccountContact consumablesContact;//Holds the value for consumables contact on CM-Manage Asset 
	private GenericAddress consumablesAddress;//Holds the value for consumables address on CM-Manage Asset
	
	private Asset assetDetailForChange;//Holds the new asset identifiers in CM-Manage Asset (Change Asset)
	
	//This section is for decommission asset
	private GenericAddress pickupAddress;
	private AccountContact primarySiteContact;
	private AccountContact secondarySiteContact;
	
	//This section is for old address in Manage Address
	private GenericAddress oldAddress;	
	private String addtnDockDetails;
	private AccountContact oldContactData;
	private String decommAssetFlag;
	private String installAssetFlag;
	private String pageNameForContact;
	private String moveType;
	
	public String getPageNameForContact() {
		return pageNameForContact;
	}
	public void setPageNameForContact(String pageNameForContact) {
		this.pageNameForContact = pageNameForContact;
	}
	public String getDecommAssetFlag() {
		return decommAssetFlag;
	}
	public void setDecommAssetFlag(String decommAssetFlag) {
		this.decommAssetFlag = decommAssetFlag;
	}
	public String getInstallAssetFlag() {
		return installAssetFlag;
	}
	public void setInstallAssetFlag(String installAssetFlag) {
		this.installAssetFlag = installAssetFlag;
	}
	
	public AccountContact getOldContactData() {
		return oldContactData;
	}
	public void setOldContactData(AccountContact oldContactData) {
		this.oldContactData = oldContactData;
	}
	public String getAddtnDockDetails() {
		return addtnDockDetails;
	}
	public void setAddtnDockDetails(String addtnDockDetails) {
		this.addtnDockDetails = addtnDockDetails;
	}
	public GenericAddress getOldAddress() {
		return oldAddress;
	}
	public void setOldAddress(GenericAddress oldAddress) {
		this.oldAddress = oldAddress;
	}
	public GenericAddress getPickupAddress() {
		return pickupAddress;
	}
	public void setPickupAddress(GenericAddress pickupAddress) {
		this.pickupAddress = pickupAddress;
	}
	public AccountContact getPrimarySiteContact() {
		return primarySiteContact;
	}
	public void setPrimarySiteContact(AccountContact primarySiteContact) {
		this.primarySiteContact = primarySiteContact;
	}
	public AccountContact getSecondarySiteContact() {
		return secondarySiteContact;
	}
	public void setSecondarySiteContact(AccountContact secondarySiteContact) {
		this.secondarySiteContact = secondarySiteContact;
	}
	public Asset getAssetDetailForChange() {
		return assetDetailForChange;
	}
	public void setAssetDetailForChange(Asset assetDetailForChange) {
		this.assetDetailForChange = assetDetailForChange;
	}
	public AccountContact getConsumablesContact() {
		return consumablesContact;
	}
	public void setConsumablesContact(AccountContact consumablesContact) {
		this.consumablesContact = consumablesContact;
	}
	public GenericAddress getConsumablesAddress() {
		return consumablesAddress;
	}
	public void setConsumablesAddress(GenericAddress consumablesAddress) {
		this.consumablesAddress = consumablesAddress;
	}
	public Asset getAssetDetail() {
		return assetDetail;
	}
	public void setAssetDetail(Asset assetDetail) {
		this.assetDetail = assetDetail;
	}
	public AccountContact getContact() {
		return contact;
	}
	public void setContact(AccountContact contact) {
		this.contact = contact;
	}
	public GenericAddress getAddress() {
		return address;
	}
	public void setAddress(GenericAddress address) {
		this.address = address;
	}
	public ServiceRequest getServiceRequest() {
		return serviceRequest;
	}
	public void setServiceRequest(ServiceRequest serviceRequest) {
		this.serviceRequest = serviceRequest;
	}
	public String getMoveType() {
		return moveType;
	}
	public void setMoveType(String moveType) {
		this.moveType = moveType;
	}
	
	
	//CHL yet to be done;
	/*//private Asset assetDetail;
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
		DateFormat dFormat=new SimpleDateFormat("dd/mm/yyyy hh:mm:ss z");
		try {
			this.installDate=dFormat.parse(installDate);
		} catch (ParseException e) {
			
			e.printStackTrace();
		}
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
	public Asset getAssetDetail() {
		return assetDetail;
	}
	
	public void setAssetDetail(Asset assetDetail) {
		this.assetDetail = assetDetail;
	}
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
*/}
