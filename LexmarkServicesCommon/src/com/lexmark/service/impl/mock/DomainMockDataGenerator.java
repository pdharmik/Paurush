package com.lexmark.service.impl.mock;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.Vector;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import com.lexmark.domain.Account;
import com.lexmark.domain.AccountContact;
import com.lexmark.domain.Asset;
import com.lexmark.domain.BulkUploadStatus;
import com.lexmark.domain.CHLNode;
import com.lexmark.domain.ContactInformation;
import com.lexmark.domain.Document;
import com.lexmark.domain.DocumentDefinition;
import com.lexmark.domain.EmailNotification;
import com.lexmark.domain.EmailNotificationLocale;
import com.lexmark.domain.Entitlement;
import com.lexmark.domain.EntitlementServiceDetail;
import com.lexmark.domain.GenericAddress;
import com.lexmark.domain.MeterReadStatus;
import com.lexmark.domain.Notification;
import com.lexmark.domain.NotificationDetail;
import com.lexmark.domain.NotificationLocale;
import com.lexmark.domain.OrderPart;
import com.lexmark.domain.Part;
import com.lexmark.domain.Role;
import com.lexmark.domain.RoleCategory;
import com.lexmark.domain.RoleCategoryLocale;
import com.lexmark.domain.ServiceRequest;
import com.lexmark.domain.ServiceRequestActivity;
import com.lexmark.domain.ServiceRequestOrderLineItem;
import com.lexmark.domain.SupportedLocale;
import com.lexmark.util.StringUtil;

public class DomainMockDataGenerator {
	
	private static Logger LOGGER = LogManager.getLogger(DomainMockDataGenerator.class);
	
	private static final List<AccountContact> accountContacts = new Vector<AccountContact>();
	private static List<Notification> notifications;
	private static List<SupportedLocale> supportedLocales;
	private static int INDEX_LOCALE_US = 0;
	private static int INDEX_LOCALE_ITALY = 1;
	private static int INDEX_LOCALE_FRANCE = 2;
	private static int INDEX_LOCALE_GERMAN = 3;
	private static int INDEX_LOCALE_CHINESE = 4;
		
	static {		
		for (int i = 0; i < 5; i++) {
			accountContacts.add(getAccountContact(i));
		}		
	}
	
	public static List<Notification> getNotifications() {
		if (notifications == null) {
			notifications = getNotificationList();
		}
		return notifications;
	}

	public static List<AccountContact> getAccountContactList() {
		return accountContacts;
	}
	
	public static List<Account> getAccountList() {
		List<Account> accounts = new ArrayList<Account>();
		for (int i = 0; i < 5; i++) {
			accounts.add(getAccount(i));
		}
		return accounts;
	}
	public static Account getAccount(int i){
		Account account = new Account();
		account.setAccountId(String.valueOf(i));
		if (i%2 == 0)
			account.setCreateServiceRequest("update");
		else{
			account.setCreateServiceRequest("update");
		}
		return account;
	}
	public static AccountContact getAccountContact(int i){
		AccountContact ac = new AccountContact();	
		if(i == 1)
			ac.setContactId("1-P3YKE"+i);
		else
			ac.setContactId("1-P0091"+i);
		ac.setFirstName("Alex"+Integer.toString(i));
		ac.setLastName("zhou");
		ac.setEmailAddress("takethat"+Integer.toString(i)+"@gmail.com");
		ac.setWorkPhone("135888888"+Integer.toString(i));
		if (i%2 == 0)
			ac.setUserFavorite(true);
		else{
			ac.setUserFavorite(false);
		}
		return ac;
	}
	
	public static List<ServiceRequest> getServiceRequestsList() {
		List<ServiceRequest> serviceRequests = new ArrayList<ServiceRequest>();
		int totalCount = 0;
		Calendar calendar = Calendar.getInstance();
		for (int i = 0; i < 5000; i++) {
			calendar.set(Calendar.YEAR, 2000+ i%11);
			ServiceRequest sr = new ServiceRequest();
			// TODO carrier is move to ServiceRequestOrderLineItem
			//sr.setCarrier("Federal Express");
			sr.setServiceRequestDate(calendar.getTime());
			if(i%2==1){
				sr.setServiceRequestStatus("Inprocess");
			}else 
				sr.setServiceRequestStatus("Completed");
		
			if(i<2000)
			{
				sr.setRequestor(getAccountContactList().get(0));
			}else if(i<4000)
			{
				sr.setRequestor(getAccountContactList().get(1));
			}else if(i<6000)
			{
				sr.setRequestor(getAccountContactList().get(2));
			}else if(i<8000)
			{
				sr.setRequestor(getAccountContactList().get(3));
			}else {
				sr.setRequestor(getAccountContactList().get(4));
			}
			//sr.setShipDate(calendar.getTime());
			//sr.setOptionExchangeList("optionExchangeList");
			sr.setOptionExchangeOtherDescription("Exchange this printer for");
			sr.setProblemDescription("My printer is jammed");
			sr.setReferenceNumber("216" + Integer.toString(i));
			sr.setOtherRequestedService("exchange");
			//sr.setReturnCarrier("Federal Express");
			//sr.setReturnShipDate(calendar.getTime());
			sr.setServiceRequestNumber("123" + Integer.toString(i));
			sr.setAsset(getDevice(i/8));
			sr.setPrimaryContact(getAccountContactList().get(1));
			sr.setSecondaryContact(getAccountContactList().get(2));
			sr.setServiceAddress(getGenericAddress(1));
			sr.setServicewebUpdateActivities(getWebUpdateActivities(1));
			sr.setEmailActivities(getEmailActivities(1));
			serviceRequests.add(sr);
			totalCount++;
		}
		return serviceRequests;
	}
	private static List<ServiceRequestActivity> getWebUpdateActivities(int i) {
		List<ServiceRequestActivity> webUpdateActivities = new ArrayList<ServiceRequestActivity>();
		Calendar calendar = Calendar.getInstance();
		for(int j=0;j<8;j++){
			ServiceRequestActivity serviceRequestActivity = new ServiceRequestActivity();
			if(j%8==1){
				serviceRequestActivity.setActivityStatus("complete");
				serviceRequestActivity.setActivityDescription("Your Service Request has been completed.");
			}else if(j%8==2){
				serviceRequestActivity.setActivityStatus("Delivered");
				serviceRequestActivity.setActivityDescription("Your item has been delivered.");
			}else if(j%8==3){
				serviceRequestActivity.setActivityStatus("In Transit");
				serviceRequestActivity.setActivityDescription("Your item is in transit for delivery.");
			}else if(j%8==4){
				serviceRequestActivity.setActivityStatus("test");
				serviceRequestActivity.setActivityDescription("Your item has been shipped.");
			}else if(j%8==5){
				serviceRequestActivity.setActivityStatus("Processed");
				serviceRequestActivity.setActivityDescription("Your request for an exchange has been processed.");
			}else if(j%8==6){
				serviceRequestActivity.setActivityStatus("scheduled");
				serviceRequestActivity.setActivityDescription("Your request for a technician has been scheduled.");
			}else if(j%8==7){
				serviceRequestActivity.setActivityStatus("In Process");
				serviceRequestActivity.setActivityDescription("Your request for serivice is in process.");
			}else{
				serviceRequestActivity.setActivityStatus("Submitted");
				serviceRequestActivity.setActivityDescription("Your request for service has been submitted.");
			}
			serviceRequestActivity.setActivityDate(calendar.getTime());
			serviceRequestActivity.setRecipientEmail("kemololler@lexmark.com");
			webUpdateActivities.add(serviceRequestActivity);
		}
		return webUpdateActivities;
	}

	private static List<ServiceRequestActivity> getEmailActivities(int i) {
		// TODO Auto-generated method stub
		return getWebUpdateActivities(1);
	}

	public static ServiceRequest getServiceRequest(int i) {
		Calendar calendar = Calendar.getInstance();
			calendar.set(Calendar.YEAR, 2000+ i%11);
			ServiceRequest sr = new ServiceRequest();
			sr.setAsset(getDevice(i));
			sr.setServiceRequestStatus("In Process");
			sr.setServiceRequestDate(calendar.getTime());
//			if(i%6==1){
//				sr.setCarrier("UPS");
//				sr.setReturnCarrier("UPS");
//				sr.setServiceRequestStatus("Shiped");
//				sr.setTrackingNumber("1Z78E26E0110184612");
//				sr.setReturnTrackingNumber("1Z78E26E0110181660");
//			}else if(i%6==2){
//				sr.setCarrier("Fed Ex");
//				sr.setReturnCarrier("Fed Ex");
//				sr.setServiceRequestStatus("Scheduled");
//				sr.setTrackingNumber("468086197421");
//				sr.setReturnTrackingNumber("468086194970");
//			}else if(i%6==3){
//				sr.setCarrier("Fed Ex");
//				sr.setReturnCarrier("Fed Ex");
//				sr.setServiceRequestStatus("Scheduled");
//				sr.setTrackingNumber("468086197020");
//				sr.setReturnTrackingNumber("468086196252");
//			}else if(i%6==4){
//				sr.setCarrier("Fed Ex");
//				sr.setReturnCarrier("Fed Ex");
//				sr.setServiceRequestStatus("Scheduled");
//				sr.setTrackingNumber("468086197465");
//				sr.setReturnTrackingNumber("468086196252");
//			}else if(i%6==5){
//				sr.setCarrier("UPS");
//				sr.setReturnCarrier("UPS");
//				sr.setServiceRequestStatus("Scheduled");
//				sr.setTrackingNumber("1Z78E26E0110185326");
//				sr.setReturnTrackingNumber("1Z78E26E0110180161");
//			}else{
//				sr.setCarrier("UPS");
//				sr.setReturnCarrier("UPS");
//				sr.setServiceRequestStatus("Delivered");
//				sr.setTrackingNumber("1Z78E26E0110179717");
//				sr.setReturnTrackingNumber("1Z78E26E0110180974");
//			}
//			sr.setShipDate(calendar.getTime());
			//sr.setOptionExchangeList("optionExchangeList");
			sr.setOptionExchangeOtherDescription("Exchange this printer for");
			sr.setProblemDescription("My printer is jammed");
			sr.setReferenceNumber("216" + Integer.toString(i));
			sr.setOtherRequestedService("exchange");
			sr.setServiceRequestNumber("123" + Integer.toString(i));
			sr.setRequestor(getAccountContactList().get(0));
			sr.setPrimaryContact(getAccountContactList().get(1));
			sr.setSecondaryContact(getAccountContactList().get(2));
			sr.setServiceAddress(getGenericAddress(1));
			sr.setAsset(getDevice(2));
			sr.setActivitywebUpdateActivities(getWebUpdateActivities(1));
			sr.setServicewebUpdateActivities(getWebUpdateActivities(1));
			sr.setSelectedServiceDetails(getEntitlementServiceDetails());
			sr.setEmailActivities(getEmailActivities(1));
			sr.setReturnOrderLines(getServiceRequestOrderLines("return"));
			sr.setShipmentOrderLines(getServiceRequestOrderLines("ship"));
		return sr;
	}
	public static List<ServiceRequestOrderLineItem> getServiceRequestOrderLines(String derection){
		List<ServiceRequestOrderLineItem> returnSROrderList = new ArrayList<ServiceRequestOrderLineItem>();
		Calendar calendar = Calendar.getInstance();
		for(int i=0;i<36;i++){
			calendar.set(Calendar.YEAR, 2000+ i%11);
			ServiceRequestOrderLineItem orderLine = new ServiceRequestOrderLineItem();
			orderLine.setStatus("Shipped");
			orderLine.setProductTLI("part number "+i);
			orderLine.setSerialNumber("serialNumber"+i);
			orderLine.setProductDescription("productDescriptions"+i);
			orderLine.setStatusDate(calendar.getTime());
			if (i % 2 == 0) {
				orderLine.setCarrier("UPS");
			} else {
				orderLine.setCarrier("Fed Ex");
			}
			if("return".equals(derection)){
				if (i%3 == 0) {
					orderLine.setTrackingNumber(null);
					orderLine.setStatus("In Process");
				} else if (i%3 == 1) {
					orderLine.setStatus("Delivered");
					if (i % 2 == 0) {
						orderLine.setTrackingNumber("1Z78E26E0110179717");
					} else {
						orderLine.setTrackingNumber("468086197421");
					}
				} else {
					orderLine.setTrackingNumber(null);
					orderLine.setStatus("Delivered");
				}
				orderLine.setSerialNumber(derection+" serialNumber"+i);
			} else {
				if(i%7==1){
					orderLine.setTrackingNumber("1Z78E26E0110179717");
				}else if(i%7==2){
					orderLine.setTrackingNumber("468086197421");
				}else if(i%7==3){
					orderLine.setTrackingNumber("468086197020");
				}else if(i%7==4){
					orderLine.setTrackingNumber("468086197465");
				}else if(i%7==5){
					orderLine.setTrackingNumber("1Z78E26E0110185326");
				}else if(i%7==6){
					orderLine.setTrackingNumber("");
				}else{
					orderLine.setTrackingNumber("1Z78E26E0110179717");
				}
			}
			returnSROrderList.add(orderLine);
		}
		return returnSROrderList;
	}
	
	public static Asset getDevice(int i){
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, 2000+ i%11);
		Asset aa = new Asset();
		String str = Integer.toString(i);
		aa.setAssetTag("23431325135" + str);
		aa.setModelNumber("Lexmark xd464dfe");
		//Commented because physicallocation1, 2 and 3 have been removed from Asset bean
		//aa.setPhysicalLocation2("Bentonville Sales Office");		
		//aa.setPhysicalLocation3("Lexington.KY3");
		aa.setAssetId(Integer.toString(10000 + i));
		aa.setSerialNumber("99999999"+Integer.toString(10000 + i));
		aa.setDeviceName("deviceName" + str);
		aa.setDeviceTag("deviceTag"+  str);
		if (i % 2 == 0) {
			aa.setHostName("lexmark");
		}else  {
			aa.setHostName("lexmark.dev");
		}
		aa.setIpAddress("192.168.1." + str);
		aa.setMeterReadDate(calendar.getTime());
		aa.setLastPageCount("500"+ str);
		aa.setLastPageReadDate(calendar.getTime());
		aa.setNotMyPrinter(false);
		aa.setProductTLI(Integer.toString(10000 + i));
		//Commented because physicallocation1, 2 and 3 have been removed from Asset bean
		//aa.setPhysicalLocation1("3rd floor, east building");
		aa.setProductLine("Product Line");
		Entitlement e = new Entitlement();
	    e.setServiceDetails(getEntitlementServiceDetails());	    
		aa.setEntitlement(e);
		if(i % 2 ==1){
			aa.setColorCapableFlag(true);
			aa.setLastColorPageCount("600"+  str);
			aa.setProductImageURL("http://www.downloaddelivery.com/webcontent/images/product/75x75/MONOLASER.gif");
			aa.setLastColorPageReadDate(calendar.getTime());
		}else{
			aa.setColorCapableFlag(false);
			aa.setLastColorPageCount("0");
			aa.setProductImageURL("http://www.downloaddelivery.com/webcontent/images/product/75x75/ALLINONE.gif");
		}
		//if(i % 3 == 2)
		//	aa.setProductImageURL("http://www.downloaddelivery.com/webcontent/images/product/75x75/MULTIFUNCTION_LASER.gif");
		aa.setInstallAddress(getGenericAddress(i));
		aa.setAssetContact(getAccountContact(i));
		if(i % 4 ==0) {	
			aa.setUserFavoriteFlag(false);

		}else if(i % 4 ==1) {
			aa.setAssetType("DFM");
		}else if(i % 4 ==2) {
			aa.setAssetType("CSS");
		} else if (i % 4 ==3) {
			aa.setUserFavoriteFlag(true);
		}
		aa.setAccount(getAccount(i));
		return aa;
	}
	public static List<EntitlementServiceDetail> getEntitlementServiceDetails(){
		EntitlementServiceDetail esd = new EntitlementServiceDetail();
		esd.setServiceDetailId("1");
		esd.setServiceDetailDescription("Onsite Repair");
		EntitlementServiceDetail esd2 = new EntitlementServiceDetail();
		esd2.setServiceDetailId("2");
		esd2.setServiceDetailDescription("Onsite Exchange of Option");
		EntitlementServiceDetail esd3 = new EntitlementServiceDetail();
		esd3.setServiceDetailId("3");
		esd3.setServiceDetailDescription("Exchange of Device");
		EntitlementServiceDetail esd4 = new EntitlementServiceDetail();
		esd4.setServiceDetailId("4");
		esd4.setServiceDetailDescription("Exchange of Option");
	    ArrayList<EntitlementServiceDetail> esds = new  ArrayList<EntitlementServiceDetail>();
	    esds.add(esd);
	    esds.add(esd2);
	    esds.add(esd3);
	   // esds.add(esd4);
	    return esds;
	}
	public static List<GenericAddress> getGenericAddressList() {
		List<GenericAddress> genericAddresses = new ArrayList<GenericAddress>();
		int totalCount = 0;		
		for (int i = 0; i < 5; i++) {			
			genericAddresses.add(getGenericAddress(i));
			totalCount++;
		}
		return genericAddresses;
	}

	public static GenericAddress getGenericAddress(int i){
		LOGGER.debug("in generic Address");
		LOGGER.debug("i is "+i);
		GenericAddress ga = new GenericAddress();
		switch (i) {
		case 0:
			ga = populateGenericAddress(String.valueOf(i), "16th Street",
                    "Department A", "1st Floor", "--", "Axis Cart Rent",
                    "80202", "USA", "ZJ", "Colorado", "Denver","100091");
			LOGGER.debug("in case 0");
			LOGGER.debug("Mock Address Sold To "+ga.getSoldToNumber());
			LOGGER.debug("Mock Address Line 1 "+ga.getAddressLine1());
			break;
		case 1:
			ga = populateGenericAddress(String.valueOf(i), "15th Street",
					"Department B", "1st Floor", "--", "Company B",
                    "80903", "USA", "ZJ", "Colorado", "Colorado Springs","100092");
			ga.setUserFavorite(true);
			LOGGER.debug("in case 1");
			LOGGER.debug("Mock Address Sold To "+ga.getSoldToNumber());
			LOGGER.debug("Mock Address Line 1 "+ga.getAddressLine1());
			break;
		case 2:
			ga = populateGenericAddress(String.valueOf(i), "14th Street",
                    "Department C", "1st Floor", "--", "Company C",
                    "81501", "USA", "ZJ", "Colorado", "Grand Junction","100094");
			LOGGER.debug("in case 2");
			LOGGER.debug("Mock Address Sold To "+ga.getSoldToNumber());
			LOGGER.debug("Mock Address Line 1 "+ga.getAddressLine1());
			break;
		case 3:
			ga = populateGenericAddress(String.valueOf(i), "13th Street",
                    "Department D", "1st Floor", "--", "Company D",
                    "73301", "USA", "ZJ", "Texas", "Austin","100095");
			break;
		case 4:
			ga = populateGenericAddress(String.valueOf(i), "12th Street",
                    "Department E", "1st Floor", "--", "Company E",
                    "78201", "USA", "ZJ", "Texas", "San Antonio","100096");
			ga.setUserFavorite(true);
			break;
		case 5:
			ga = populateGenericAddress(String.valueOf(i), "11th Street",
                    "Department E", "1st Floor", "--", "Company E",
                    "77001", "USA", "ZJ", "Texas", "Houston","100097");
			ga.setUserFavorite(true);
			break;
		case 6:
			ga = populateGenericAddress(String.valueOf(i), "10th Street",
                    "Department F", "1st Floor", "--", "Company F",
                    "75201", "USA", "ZJ", "Texas", "Dallas","100098");
			break;
		case 7:
			ga = populateGenericAddress(String.valueOf(i), "9th Street",
                    "Department G", "1st Floor", "--", "Company G",
                    "M4B 1T4", "Canada", "Ontario", "ZJ", "Toronto","100099");
			ga.setUserFavorite(true);
			break;
		case 8:
			ga = populateGenericAddress(String.valueOf(i), "8th Street",
                    "Department H", "1st Floor", "--", "Company H",
                    "K1A 0C1", "Canada", "Ontario", "ZJ", "Ottawa","100093");
			break;
		}
		LOGGER.debug("Mock Address Sold To "+ga.getSoldToNumber());
		LOGGER.debug("Mock Address Line 1 "+ga.getAddressLine1());
		return ga;
	}
	
	//product 10,000 device data
	public static List<Asset> getDeviceList(){
		List<Asset> assets = new ArrayList<Asset>();
		for (int i = 0; i < 200; i++) {
			Asset aa = new Asset();
			aa = getDevice(i);
			assets.add(aa);
		}
		return assets;
	}
	/**
	 * generate Node list based on the input chlNodeId.
	 * If chlNodeId = 0, return one Node which is root
	 * else generate child node for the given nodeId
	 * @param nodeId
	 * @return
	 */
	public static List<CHLNode> getCHLNodeListByNodeId(String nodeId){
		List<CHLNode> chlNodeList = new ArrayList<CHLNode>();
		CHLNode node = new CHLNode();
		//if(rootFlag){
		//	node = populateCHLNodeWithChild(nodeId, "CHL Root Node" + nodeId);
		//	chlNodeList.add(node);
		//}else{
			for(int i=0; i < 5; i++){
				node = new CHLNode();
				if(i == 1 || i == 2 || i == 3)
					node = populateCHLNodeWithChild(nodeId+"-"+i, "CHL Node(" + nodeId+"-"+i+")");
				else
					node = populateCHLNodeWithoutChild(nodeId+"-"+i, "CHL Node(" + nodeId+"-"+i+")");
				chlNodeList.add(node);
			}
		//}
		return chlNodeList;
	}
	@Deprecated
	public static List<CHLNode> getCHLNodeList(String chlNodeId) {
		List<CHLNode> chlNodeList = new ArrayList<CHLNode>();
		if (StringUtil.isStringEmpty(chlNodeId)) {
			for (int i = 1; i <= 3; i ++) {
				chlNodeList.add(getCHLNode(chlNodeId + i));
			}
		} else {
			chlNodeList.add(getCHLNode(chlNodeId));
		}
		return chlNodeList;
	}
	@Deprecated
	private static CHLNode getCHLNode(String chlNodeId) {
		String nodeId = chlNodeId + "0";
		CHLNode rootNode = populateCHLNodeWithoutChild(nodeId, "CHL Root Node" + nodeId);
		CHLNode node1_1 = populateCHLNodeWithoutChild(nodeId + "1", "Node " + nodeId +"_1");
		CHLNode node1_2 = populateCHLNodeWithoutChild(nodeId + "2", "Node " + nodeId +"_2");
		CHLNode node1_1_1 = populateCHLNodeWithoutChild(nodeId + "11", "Node " + nodeId +"_1_1");
		CHLNode node1_1_2 = populateCHLNodeWithoutChild(nodeId + "12", "Node " + nodeId +"_1_2");
		CHLNode node1_1_3 = populateCHLNodeWithoutChild(nodeId + "13", "Node " + nodeId +"_1_3");
		CHLNode node1_2_1 = populateCHLNodeWithoutChild(nodeId + "21", "Node " + nodeId +"_2_1");
		CHLNode node1_2_2 = populateCHLNodeWithoutChild(nodeId + "22", "Node " + nodeId +"_2_2");
		
		List<CHLNode> list1 = new ArrayList<CHLNode>();
		list1.add(node1_2_1);
		list1.add(node1_2_2);
		node1_2.setChildNodeList(list1);
		
		List<CHLNode> list2 = new ArrayList<CHLNode>();
		list2.add(node1_1_1);
		list2.add(node1_1_2);
		list2.add(node1_1_3);
		node1_1.setChildNodeList(list2);
		
		List<CHLNode> list3 = new ArrayList<CHLNode>();
		list3.add(node1_1);
		list3.add(node1_2);
		rootNode.setChildNodeList(list3);
		
		return rootNode;
	}
	
	private static List<Notification> getNotificationList() {
		List<Notification> notificationList = new ArrayList<Notification>();
		for (int i = 0; i < 40; i ++) {
			notificationList.add(getNotification(i, 100000 + i));
		}
		return notificationList;
	}
	
	private static CHLNode populateCHLNodeWithoutChild(String chlNodeId, String chlNodeName) {
		CHLNode newNode = new CHLNode();
		newNode.setCHLNodeId(chlNodeId);
		newNode.setChlNodeName(chlNodeName);
		newNode.setHasChild(false);
		return newNode;
	}
	private static CHLNode populateCHLNodeWithChild(String chlNodeId, String chlNodeName) {
		CHLNode newNode = new CHLNode();
		newNode.setCHLNodeId(chlNodeId);
		newNode.setChlNodeName(chlNodeName);
		newNode.setHasChild(true);
		return newNode;
	}
	private static GenericAddress populateGenericAddress(
			String addressId, String addressLine1, String addressLine2,
			String addressLine3, String addressLine4, String addressName,
			String postalCode,String country, String province, String state, String city,String soldTo) {
		GenericAddress address = new GenericAddress();
		address.setAddressId(addressId);
		address.setAddressLine1(addressLine1);
		address.setAddressLine2(addressLine2);
		address.setAddressLine3(addressLine3);
		address.setAddressLine4(addressLine4);
		address.setAddressName(addressName);
		address.setPostalCode(postalCode);
		address.setCountry(country);
		address.setProvince(province);
		address.setState(state);
		address.setCity(city);
		address.setSoldToNumber(soldTo);
		return address;
	}
	
	private static Notification getNotification(int index, Integer notificationId) {
		Notification notification = new Notification();
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		int dayOfYear = calendar.get(Calendar.DAY_OF_YEAR);
		
		calendar.set(Calendar.DAY_OF_YEAR, dayOfYear - 3 + index);
		
		Date displayDate = calendar.getTime();
		calendar.set(Calendar.DAY_OF_YEAR, dayOfYear - 3 + index + 30 + index % 2);
		Date removeDate = calendar.getTime();
		
		notification.setDisplayDate(displayDate);
		notification.setRemoveDate(removeDate);
		notification.setDisplayOrder(index + 1);
		notification.setAdminName("FirmWare update " + Integer.toString(index));
		notification.setNotificationId(notificationId);
		if((notificationId-100000)%3 == 0){
			notification.setDisplayURL("http://www.google.com/");
		}else if((notificationId-100000)%3 == 1){
			notification.setDisplayURL("http://www.lexmark.com/");
		}else {
		}
		return notification;
	}
	
	private static NotificationLocale generateNotificationLocale(
			Integer notificationLocaleId, Integer notifictionId, int localeIndex, String displayDescription) {
		NotificationLocale notificationLocale = new NotificationLocale();
		notificationLocale.setDisplayDescription(displayDescription);
		notificationLocale.setNotificationLocaleId(notificationLocaleId);
		notificationLocale.setNotificationId(notifictionId);
		notificationLocale.setSupportedLocale(getSupportedLocales().get(localeIndex));
		return notificationLocale;
	}
	
	public static List<NotificationLocale> getNotificationLocaleList(Integer notificationId, Locale locale) {
		List<NotificationLocale> notificationLocaleList = new ArrayList<NotificationLocale>();
		// TODO: add more detailed precondition to get specific NotificationLocale based on locale
		if (locale!= null) {
			int localeIndex = 0;
			for (SupportedLocale supportedLocale : getSupportedLocales()) {
				if (supportedLocale.getSupportedLocaleCode().equals(locale.getLanguage())) {
					break;
				}
				localeIndex ++;
			}
			if((notificationId-100000)%3 == 0){
				notificationLocaleList.add(generateNotificationLocale(notificationId * 10 + 1, notificationId, localeIndex, "This is the Notification call back" + notificationId + " in " + locale.getDisplayLanguage()));
			}else if((notificationId-100000)%3 == 1){
				notificationLocaleList.add(generateNotificationLocale(notificationId * 10 + 1, notificationId, localeIndex, "This is the Notification from lexmark" + notificationId + " in " + locale.getDisplayLanguage()));
			}else {
				notificationLocaleList.add(generateNotificationLocale(notificationId * 10 + 1, notificationId, localeIndex, "This is the Notification for note" + notificationId + " in " + locale.getDisplayLanguage()));
			}
		} else {
			notificationLocaleList.add(generateNotificationLocale(notificationId * 10 + 1, notificationId, INDEX_LOCALE_US, "Notification Description" + notificationId + " in English"));
			notificationLocaleList.add(generateNotificationLocale(notificationId * 10 + 2, notificationId, INDEX_LOCALE_ITALY, "Notification Description" + notificationId + " in Italy"));
			notificationLocaleList.add(generateNotificationLocale(notificationId * 10 + 3, notificationId, INDEX_LOCALE_FRANCE, "Notification Description" + notificationId + " in French"));
//			notificationLocaleList.add(generateNotificationLocale(notificationId * 10 + 4, notificationId, INDEX_LOCALE_GERMAN, "Notification Description" + notificationId + " in Germany"));
			notificationLocaleList.add(generateNotificationLocale(notificationId * 10 + 5, notificationId, INDEX_LOCALE_CHINESE, "Notification Description" + notificationId + " in Chinese"));
		}
		return notificationLocaleList;
	}
	
	private static List<NotificationLocale> getNotificationLocaleListEmptyDescription() {
		List<NotificationLocale> notificationLocaleList = new ArrayList<NotificationLocale>();
		notificationLocaleList.add(generateNotificationLocale(null, null, INDEX_LOCALE_US, null));
		notificationLocaleList.add(generateNotificationLocale(null, null, INDEX_LOCALE_ITALY, null));
		notificationLocaleList.add(generateNotificationLocale(null, null, INDEX_LOCALE_FRANCE, null));
		notificationLocaleList.add(generateNotificationLocale(null, null, INDEX_LOCALE_GERMAN, null));
		notificationLocaleList.add(generateNotificationLocale(null, null, INDEX_LOCALE_CHINESE, null));
		return notificationLocaleList;
	}
	
	public static List<NotificationDetail> getNotificationDetailList(Locale locale) {
		// TODO: add mock data here. notificationLocaleList contains only one
		// NotificationLocale object which is based on locale.
		List<NotificationDetail> notificationDetailList = new ArrayList<NotificationDetail>();
		for (int i = 0; i < 15; i ++) {
			notificationDetailList.add(getNotificationDetail(i, 100000 + i, locale));
		}
		return notificationDetailList;
	}
	
	private static NotificationDetail getNotificationDetail(int index, Integer notificationId, Locale locale) {
		NotificationDetail notificationDetail = new NotificationDetail();
		notificationDetail.setNotification(getNotification(index, notificationId));
		notificationDetail.setNotificationLocaleList(getNotificationLocaleList(notificationId, locale));
		return notificationDetail;
	}
	
	public static NotificationDetail getEmptyNotificationDetail() {
		NotificationDetail detail = new NotificationDetail();
		detail.setNotificationLocaleList(getNotificationLocaleListEmptyDescription());
		Notification emptyNotification = new Notification();
		Calendar calendar = Calendar.getInstance();
		emptyNotification.setDisplayDate(calendar.getTime());
		emptyNotification.setRemoveDate(calendar.getTime());
		detail.setNotification(emptyNotification);
		return detail;
	}

	public static List<SupportedLocale> getSupportedLocales() {
		if (supportedLocales == null) {
			supportedLocales = new ArrayList<SupportedLocale>();
			final Locale[] locales = { Locale.US, Locale.FRANCE, Locale.ITALY, Locale.GERMAN, new Locale("es"), new Locale("pt"),
					Locale.TRADITIONAL_CHINESE, Locale.SIMPLIFIED_CHINESE };
			for (int i = 0; i < locales.length; i++) {
				supportedLocales.add(generateSupportedLocale(i, locales[i]));
			}
		}
		return supportedLocales;
	}

	private static SupportedLocale generateSupportedLocale(Integer id, Locale locale) {
		SupportedLocale supportedLocale = new SupportedLocale();
		supportedLocale.setSupportedLocaleCode(locale.getLanguage());
		supportedLocale.setSupportedLocaleId(id);
		supportedLocale.setSupportedLocaleName(locale.getDisplayName());
		return supportedLocale;
	}
	public static List<ContactInformation> getContactInformationList(){
		List<ContactInformation> contactInfoList = new ArrayList<ContactInformation>();
		
		ContactInformation contactInformation2= new ContactInformation();
		contactInformation2.setRoleName("standardaccess");
		contactInformation2.setContactData("Micheal Jordan<br>(231)-331-1006<br>micheal@lexmark.com");
		contactInfoList.add(contactInformation2);
		
		ContactInformation contactInformation= new ContactInformation();
		contactInformation.setRoleName("accountmanagement");
		contactInformation.setContactData("Jay Michaels<br>(231)-331-1004<br>jay@lexmark.com");
		contactInfoList.add(contactInformation);
		
		ContactInformation contactInformation1= new ContactInformation();
		contactInformation1.setRoleName("billing");
		contactInformation1.setContactData("Adam Moore<br>(231)-331-1005<br>adam@lexmark.com");
		contactInfoList.add(contactInformation1);
		
		return contactInfoList;
	}

	public static List<Document> generateDocumentList() {
		List<Document> documents = new ArrayList<Document>();

		DocumentDefinition[] definitions = generateDocumentDefinition();

		String[] exts = { ".pdf", ".doc", ".pdf", ".xls", ".txt", ".docx", ".docx", ".aaa", ".xls", ".pdf", ".xlsx" };
		Calendar cal = Calendar.getInstance();
		final int NUM_FISRT_LEVEL = 7;
		int idxDoc = 0;
		for (int i = 0; i < NUM_FISRT_LEVEL; i++) {
			String level1Name =  "/Level 1 Node " + i + "/";
			final int NUM_SECOND_LEVEL = 4;
			for (int j = 0; j < NUM_SECOND_LEVEL; j++) {
				String level2Name = level1Name + "Level 2 Node " + i + "." + j + "/";
				final int NUM_THIRD_LEVEL = 5;
				for (int k = 0; k < NUM_THIRD_LEVEL; k++) {
					String level3Name = level2Name + "Level 3 Node " + i + "." + j + "." + k + "/";
					final int NUM_FILES = 35;
					for (int l = 0; l < NUM_FILES; l++) {
						String ext = exts[idxDoc % exts.length];
						String fileName = "File " + i + "." + j + "." + k + "." + l+ext;
						String path = level3Name + fileName;
						LOGGER.info(path);
						Document document = new Document();
						document.setFileName(fileName);
						document.setFiledataLink(0L);
						document.setFilePath(path);
						document.setFileSize(3000 + idxDoc);
						document.setFileType(ext.substring(1));
						document.setDefinition(definitions[idxDoc % definitions.length]);
						cal.set(Calendar.DAY_OF_MONTH, idxDoc%29+1);
						document.setLastUpdateTime(cal.getTime());
						documents.add(document);
						document.setFileObjectId(idxDoc+1+"");
						idxDoc++;
					}
				}
			}
		}
		return documents;
	}

	public static List<EmailNotification> getEmailNotificationList(){
		List<EmailNotification> list = new ArrayList<EmailNotification>();
		for(int i = 0;i < 10;i++){
			EmailNotification emailNotification = new EmailNotification();
			emailNotification.setEmailNotificationId(new Integer("123456789"+i));
			emailNotification.setEmailName("Report Share"+i);
			emailNotification.setEmailDescription("Report Share Email Description"+i);
			List<EmailNotificationLocale> emailNotificationLocaleList = new ArrayList<EmailNotificationLocale>();
			for(int num = 0;num<5;num++){
				emailNotificationLocaleList.add(getEmailNotificationLocale(num,i));
			}
			emailNotification.setEmailNotificationLocaleList(emailNotificationLocaleList);
			
			list.add(emailNotification);
		}
		return list;
	}
	public static EmailNotificationLocale getEmailNotificationLocale(int i,int idAtrr){
		String country[] = {"English","Spanish","French","German","Japanse"};
		EmailNotificationLocale emailNotificationLocale = new EmailNotificationLocale();
		if(new Random().nextInt(3)== 0){
			//emailNotificationLocale.setEmailNotificationId("123456789"+idAtrr);
			emailNotificationLocale.setEmailNotificationLocaleId(new Integer("987654321"+idAtrr+i));
			//emailNotificationLocale.setLocaleId(new Integer("1000"+i));
			//emailNotificationLocale.setLocaleName(country[i]);
			emailNotificationLocale.setEmailSubject("emailSubject"+idAtrr+i);
			emailNotificationLocale.setEmailHeader("emailHeader"+idAtrr+i);
			emailNotificationLocale.setEmailBody("emailBody"+idAtrr+i);
			emailNotificationLocale.setEmailFooter("emailFooter"+idAtrr+i);
		}else{
			//emailNotificationLocale.setEmailNotificationId("123456789"+idAtrr);
			emailNotificationLocale.setEmailNotificationLocaleId(new Integer("987654321"+idAtrr+i));
			//emailNotificationLocale.setLocaleId(new Integer("1000"+i));
			//emailNotificationLocale.setLocaleName(country[i]);
		}
		return emailNotificationLocale;
	}
	public static EmailNotificationLocale getEmailNotificationLocaleFields(int i){
		String country[] = {"English","Spanish","French","German","Japanse"};
		EmailNotificationLocale emailNotificationLocale = new EmailNotificationLocale();
		//emailNotificationLocale.setLocaleId(new Integer("1000"+i));
	//	emailNotificationLocale.setLocaleName(country[i]);
	
		return emailNotificationLocale;
	}
	
	private static DocumentDefinition[] generateDocumentDefinition() {
		Role[] roles = generateRoles();
		SupportedLocale[] locales = generateSupportedLocales();
		RoleCategory[] categories = generateRoleCategories(roles, locales);
		String[] mdmids = { "Global", "Domestic", "Legal", "Account", "Siebel" };
		DocumentDefinition[] definitions = new DocumentDefinition[100];
		for (int i = 0; i < definitions.length; i++) {
			DocumentDefinition definition = definitions[i] = new DocumentDefinition();
			definition.setId(i+1);
			definition.setMdmId(mdmids[i % mdmids.length].toUpperCase());
			definition.setMdmLevel(mdmids[i % mdmids.length]);
			definition.setName("Document Name " + i);
			definition.setRoleCategory(categories[i % categories.length]);
		}
		return definitions;
	}

	private static SupportedLocale[] generateSupportedLocales() {
		String[][] localesStrs = { { "English", "en" }, { "French", "fr" }, { "Italian", "it" }, { "German", "de" },
				{ "Spanish", "es" }, { "Portuguese", "pt" }, { "Traditional Chinese", "zh_TW" },
				{ "Simplified Chinese", "zh_CN" }, };
		SupportedLocale[] locales = new SupportedLocale[localesStrs.length];
		for (int i = 0; i < locales.length; i++) {
			SupportedLocale locale = locales[i] = new SupportedLocale();
			locale.setSupportedLocaleCode(localesStrs[i][1]);
			locale.setSupportedLocaleName(localesStrs[i][0]);
		}
		return locales;
	}

	private static RoleCategory[] generateRoleCategories(Role[] roles, SupportedLocale[] supportedLocales) {
		int[][] maps = { { 5, 0, }, { 8, 2, 7, 3, 6, }, { 3, 8, }, { 2, 5, 7, 4, }, { 0, 5, }, { 3, 0, 2, }, { 4, 2, }, { 1, },
				{ 5, 7, 0, }, { 0, 2, 3, }, };
		RoleCategory[] categories = new RoleCategory[maps.length];
		for (int i = 0; i < categories.length; i++) {
			RoleCategory roleCategory = categories[i] = new RoleCategory();
			roleCategory.setName("Category " + (i + 1));
			roleCategory.setOrderNumber(i);

			List<RoleCategoryLocale> localeList = new ArrayList<RoleCategoryLocale>();
			for (SupportedLocale supportedLocale : supportedLocales) {
				RoleCategoryLocale categoryLocale = new RoleCategoryLocale();
				categoryLocale.setName(roleCategory.getName() + " - " + supportedLocale.getSupportedLocaleCode());
				categoryLocale.setSupportedLocale(supportedLocale);
			}
			roleCategory.setLocaleList(localeList);
			roleCategory.setType("D");
			List<Role> listroles = new ArrayList<Role>();
			for (int roleId : maps[i]) {
				listroles.add(roles[roleId % roles.length]);
			}
			roleCategory.setRoles(listroles);

		}
		return categories;
	}

	private static Role[] generateRoles() {
		String[] roleNames = { "Standard Access", "Service and Support", "Account Management", "Billing", "Project Management",
				"Analyst", "Account Administrator", "Services Portal Administrator", "Publishing" };
		Role[] roles = new Role[roleNames.length];
		for (int i = 0; i < roleNames.length; i++) {
			roles[i] = new Role();
			roles[i].setName(roleNames[i]);
		}
		return roles;
	}
	
	public static List<MeterReadStatus> getMeterReadStatusList(){
		List<MeterReadStatus> list =  new ArrayList<MeterReadStatus>();
		for(int i=1;i<500;i++){
			MeterReadStatus meterReadStatus = new MeterReadStatus();
			meterReadStatus.setAttachmentName("123456~filename"+i+"~"+formatDateString());
			meterReadStatus.setSize(5500);
			meterReadStatus.setSubmittedOn(formatDate());
			meterReadStatus.setCompletedOn(formatDate());
			if(i%9==0){
				meterReadStatus.setStatus("Error");
				meterReadStatus.setComment("Error on import:20 rows failed");
			}
			else{
				meterReadStatus.setStatus("Successful");
				meterReadStatus.setComment("File imported successfully");
			}
			
			list.add(meterReadStatus);
		}
		return list;
	}
	
	public static List<BulkUploadStatus> getBulkUploadStatusList(){
		List<BulkUploadStatus> list =  new ArrayList<BulkUploadStatus>();
		for(int i=1;i<500;i++){
			BulkUploadStatus bulkUploadStatus = new BulkUploadStatus();
			bulkUploadStatus.setAttachmentName("123456~filename"+i+"~"+formatDateString());
			bulkUploadStatus.setSize(5500);
			bulkUploadStatus.setSubmittedOn(formatDate());
			bulkUploadStatus.setCompletedOn(formatDate());
			if(i%9==0){
				bulkUploadStatus.setStatus("Error");
				bulkUploadStatus.setComment("Error on import:20 rows failed");
			}
			else{
				bulkUploadStatus.setStatus("Successful");
				bulkUploadStatus.setComment("File imported successfully");
			}
			
			list.add(bulkUploadStatus);
		}
		return list;
	}
	
	private static String formatDate(){
		Calendar currentDate = GregorianCalendar.getInstance();
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	    return dateFormat.format(currentDate.getTime());
	}
	private static String formatDateString(){
		Calendar currentDate = GregorianCalendar.getInstance();
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMddhhmmssmmm");
	    return dateFormat.format(currentDate.getTime());
	}
	
	public static List<Part> getAssetOrderPartsList(){
		List<Part> list =  new ArrayList<Part>();
		for(int i=1;i<10;i++){
			Part orderPart = new Part();
			orderPart.setPartNumber("1000number"+i);
			orderPart.setDescription("Description of the asset no ");
			orderPart.setCategory("category +i");
			List<String> paymentTypeList =  new ArrayList<String>();
			paymentTypeList.add("Ship and Bill");
			paymentTypeList.add("Usage Based Billing");
			orderPart.setPaymentTypes(paymentTypeList);
			//to be commented ::: Scenario 1 only one payment type.
			//orderPart.setPaymentType("Pay Now");
			//to be commented ::: Scenario 2, 4 types of payment type.
			if(i==1 || i==6){
				//orderPart.setPaymentType("Pay Now");
			}
			else if(i==2 || i==5){
				//orderPart.setPaymentType("Pay Later");
			}
			else if(i==3 || i==4){
				//orderPart.setPaymentType("UBB");
			}else{
				//orderPart.setPaymentType("");
			}
		
			list.add(orderPart);
		}
		return list;
	}
	
	public static void main(String[] args) {

		generateDocumentList();
		// Random r = new Random();
		// int[] nums = { 1, 2, 2, 3, 3, 3, 3, 3, 4, 5, 6 };
		// int N = 9;
		// for (int i = 0; i < 10; i++) {
		// int num = nums[r.nextInt(nums.length)];
		// Set<Integer> ints = new HashSet<Integer>();
		// logger.info.print("{");
		// for (int j = 0; j < num; j++) {
		// int n = r.nextInt(N);
		// if (ints.contains(n)) {
		// j--;
		// continue;
		// }
		// logger.info.print(n + ",");
		// ints.add(n);
		// }
		// logger.info("},");
		// }
	}
}
