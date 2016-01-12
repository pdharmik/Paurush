/**
 * Copyright@ 2012. This document has been created & written by
 * Wipro technologies for Lexmark and this is copyright to Lexmark
 *
 * File         	: ManageAssetServiceImpl
 * Package     		: com.lexmark.services.webService.cm
 * Creation Date 	: 
 *
 * Modification History:
 *
 * ---------------------------------------------------------------------
 * Author 			Date				Version  		Comments
 * ---------------------------------------------------------------------
 * Sourav						 		1.0             Initial Version
 *
 */

package com.lexmark.services.webService.cm;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.List;
import java.util.Map;

import javax.xml.rpc.holders.StringHolder;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.lexmark.SiebelShared.createServiceRequest.client.ActivityDetails2;
import com.lexmark.SiebelShared.createServiceRequest.client.AssetContacts;
import com.lexmark.SiebelShared.createServiceRequest.client.ChangeManagementServiceRequestData;
import com.lexmark.SiebelShared.createServiceRequest.client.ChangeManagementServiceRequestWSInput;
import com.lexmark.SiebelShared.createServiceRequest.client.ChangeManagementServiceRequestWSInput2;
import com.lexmark.SiebelShared.createServiceRequest.client.RelatedActivityInformation;
import com.lexmark.SiebelShared.createServiceRequest.client.ServiceRequestWS;
import com.lexmark.SiebelShared.createServiceRequest.client.ServiceRequestWSLocator;
import com.lexmark.SiebelShared.createServiceRequest.client.ServiceRequestWS_PortType;
import com.lexmark.SiebelShared.createServiceRequest.client.SiebelAccount;
import com.lexmark.SiebelShared.createServiceRequest.client.SiebelAddress;
import com.lexmark.SiebelShared.createServiceRequest.client.SiebelAssetManagementInformation;
import com.lexmark.SiebelShared.createServiceRequest.client.SiebelContact;
import com.lexmark.SiebelShared.createServiceRequest.client.SiebelMeterReadInformation;
import com.lexmark.SiebelShared.createServiceRequest.client.WebServiceDocumentMetaData;
import com.lexmark.SiebelShared.createServiceRequest.client.holders.ServiceRequestDetailsOutputHolder;
import com.lexmark.constants.LexmarkConstants;
import com.lexmark.contract.CreateServiceRequestContract;
import com.lexmark.domain.AccountContact;
import com.lexmark.domain.Asset;
import com.lexmark.domain.GenericAddress;
import com.lexmark.domain.PageCounts;
import com.lexmark.domain.ServiceRequest;
import com.lexmark.exceptionimpl.checked.business.LGSBusinessException;
import com.lexmark.exceptionimpl.runtime.LGSRuntimeException;
import com.lexmark.framework.logging.LEXLogger;
import com.lexmark.result.CreateServiceRequestResult;
import com.lexmark.services.LexmarkSPConstants;
import com.lexmark.services.api.cm.ManageAssetService;
import com.lexmark.services.util.ChangeMgmtConstant;
import com.lexmark.services.util.ObjectDebugUtil;
import com.lexmark.services.util.PerformanceConstant;
import com.lexmark.services.util.PerformanceUtil;
import com.lexmark.util.DateUtil;
import com.lexmark.util.StringUtil;

public class ManageAssetServiceImpl implements ManageAssetService {

	/**. Instance variable of wrapper logger class **/
	private static LEXLogger LOGGER = LEXLogger.getLEXLogger(ManageAssetServiceImpl.class);
	private static Logger logger = LogManager.getLogger(ManageAssetServiceImpl.class);
	private static Logger perfLogger = LogManager.getLogger("performance"); //Added for performance logging

	private String address;
	private String senderId;
	private String senderName;
	private String receiverId;
	private String userName;
	private String password;
	
	private static final String CREATECMREQSERV = "createCMRequestService";
	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getSenderId() {
		return senderId;
	}

	public void setSenderId(String senderId) {
		this.senderId = senderId;
	}

	public String getSenderName() {
		return senderName;
	}

	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}

	public String getReceiverId() {
		return receiverId;
	}

	public void setReceiverId(String receiverId) {
		this.receiverId = receiverId;
	}

	public ManageAssetServiceImpl() {

	}

	public ManageAssetServiceImpl(String address) {
		this.address = address;

	}
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	/**	  
	 * This method is used for the service calls - Manage Asset 
	 * @param serviceReqContract
	 * @param accountDetails
	 * @throws LGSRuntimeException
	 * @throws LGSBusinessException
	 */
   	public CreateServiceRequestResult createCMRequestService(CreateServiceRequestContract serviceReqContract, Map<String,String> accountDetails)
			throws LGSRuntimeException, LGSBusinessException
	{
   		LOGGER.enter(this.getClass().getSimpleName(), CREATECMREQSERV);
   		CreateServiceRequestResult result = null;
   		try{
		LOGGER.debug("Beginning Service call for CreateService Request for Manage Asset");
		ServiceRequestWS locator = new ServiceRequestWSLocator();		 
		ServiceRequestWS_PortType port = locator.getServiceRequest_serviceRequestWS_Port(new URL(getAddress()));
		org.apache.axis.client.Stub stub = ((org.apache.axis.client.Stub) port);
		stub.setUsername(getUserName());
		stub.setPassword(getPassword());
		final String synchOrAsynch = "asynch";
		final String debug="$null";
		ServiceRequest srData=null;
		String serviceRequestNumber =null;
		String sourceSystem=null;
		if(serviceReqContract.getFleetManagementFlag()!=null && serviceReqContract.getFleetManagementFlag().equalsIgnoreCase("true")){
		LOGGER.debug("Setting Map-Web");
			sourceSystem = "Map-Web";
		}
		else{
			LOGGER.debug("Setting Web");
		sourceSystem = "Web";
		}
		LOGGER.debug("Initialising Metadata object");
		WebServiceDocumentMetaData documentMetaData = new WebServiceDocumentMetaData();
		documentMetaData.setSenderId(getSenderId());
		documentMetaData.setSenderName(getSenderName());
		documentMetaData.setReceiverId(getReceiverId());
		
		if(serviceReqContract.getServiceRequest()!=null){
			
		srData = serviceReqContract.getServiceRequest();
		}
				
		SiebelAccount accInformation=new SiebelAccount();
		
		if (accountDetails.get(ChangeMgmtConstant.ACCOUNTID)==null){
			accInformation.setAccountId("");
			accInformation.setAccountName("");
		}else{
			LOGGER.debug("Account id :::: " + accountDetails.get("accountId")+ "Account Name :::: " + accountDetails.get("accountName") + "Account Organization :::: " + accountDetails.get("accountOrganization")	+ "Agreement Id :::: " + accountDetails.get("agreementId") + "Agreement Name :::: "	+ accountDetails.get("agreementName"));
			accInformation.setAccountId(accountDetails.get(ChangeMgmtConstant.ACCOUNTID));
			accInformation.setAccountName(accountDetails.get(ChangeMgmtConstant.ACCOUNTNAME));
			accInformation.setAccountCountry(accountDetails.get(ChangeMgmtConstant.COUNTRY));
		}
		
		ChangeManagementServiceRequestData cmServiceReqData= new ChangeManagementServiceRequestData();		
		cmServiceReqData.setAccountInformation(accInformation);
		// changes for CR 13558, July release
		if(serviceReqContract.getPrevSrNo()!=null){
			cmServiceReqData.setRelatedServiceRequestNumber(serviceReqContract.getPrevSrNo());
		}else{
			cmServiceReqData.setRelatedServiceRequestNumber("");
		}
		
		LOGGER.debug("Previous service request number is "+cmServiceReqData.getRelatedServiceRequestNumber());
		cmServiceReqData.setServiceRequestType(ChangeMgmtConstant.SERVICEREQTYPE);		
		cmServiceReqData.setRequestedService(serviceReqContract.getServiceRequest().getArea().getValue());
		cmServiceReqData.setRequestedServiceAction(serviceReqContract.getServiceRequest().getSubArea().getValue());
		cmServiceReqData.setServiceRequestDate(DateUtil.convertDateToGMTString(srData.getRequestedEffectiveDate()));
		cmServiceReqData.setServiceRequestDescription(srData.getAddtnlDescription());
		cmServiceReqData.setCustomerReferenceNumber(srData.getCustomerReferenceId());
		cmServiceReqData.setCostCenter(srData.getCostCenter());
		cmServiceReqData.setServiceRequestSource(sourceSystem);
		//adding move type
		//commented for WM changes
		//cmServiceReqData.setMoveType(serviceReqContract.getMoveType());
		LOGGER.debug("before MPS2.1 start");
	
		/* Added for MPS2.1 Wave4 */
		
		SiebelAssetManagementInformation siebelAssetManagementInformation = new SiebelAssetManagementInformation();
		
		Asset srDataAsset = serviceReqContract.getServiceRequest().getAsset();
		if (srDataAsset!=null) {		
		LOGGER.debug("ServiceRequest().getAsset is not null");
		siebelAssetManagementInformation.setAssetId(srDataAsset.getAssetId());
		siebelAssetManagementInformation.setSerialNumber(srDataAsset.getSerialNumber());
		siebelAssetManagementInformation.setProductNumber(srDataAsset.getProductLine());
		LOGGER.debug("srDataAsset.getProductLine()" + srDataAsset.getProductLine());
		siebelAssetManagementInformation.setInstallDate(DateUtil.convertDateToGMTString(srDataAsset.getInstallDate()));
		siebelAssetManagementInformation.setIPAddress(srDataAsset.getIpAddress());
		siebelAssetManagementInformation.setHostName(srDataAsset.getHostName());
		LOGGER.debug("srDataAsset.getDeviceTag()" + srDataAsset.getDeviceTag());
		siebelAssetManagementInformation.setAssetTag(srDataAsset.getDeviceTag());
		siebelAssetManagementInformation.setAssetCostCenter(srDataAsset.getAssetCostCenter());
		LOGGER.debug("ChlNodeId in Impl="+srDataAsset.getChlNodeId());
		siebelAssetManagementInformation.setRelatedCustomerHierarchyLevel(srDataAsset.getChlNodeId());
		
		LOGGER.debug("After setting asset info");
		
		// Page Counts
		List<PageCounts> pageCounts = srDataAsset.getPageCounts();
		int pageCountsSize = pageCounts.size();
		int  setArrayCount=0; 
		for(int i=0;i<pageCountsSize;i++) {
			if((pageCounts.get(i).getDate() !=null && !"".equals(pageCounts.get(i).getDate().trim()))&& (pageCounts.get(i).getCount()!=null && !"".equals(pageCounts.get(i).getCount().trim()))){
				setArrayCount++;
			}
		}
		
		SiebelMeterReadInformation[] siebelMeterReadInformationList = new SiebelMeterReadInformation[setArrayCount];
		
		int  count=0; 
		for(int i=0;i<pageCountsSize;i++) {
			SiebelMeterReadInformation siebelMeterReadInformation = new SiebelMeterReadInformation();
			if((pageCounts.get(i).getDate() !=null && !"".equals(pageCounts.get(i).getDate().trim()))&& (pageCounts.get(i).getCount()!=null && !"".equals(pageCounts.get(i).getCount().trim()))){
			siebelMeterReadInformation.setMeterType(pageCounts.get(i).getName());
			siebelMeterReadInformation.setMeterDateTime(pageCounts.get(i).getDate());
			siebelMeterReadInformation.setMeterValue(pageCounts.get(i).getCount());
			siebelMeterReadInformationList[count] = siebelMeterReadInformation;
			count++;			
			}
			
		}
		
		if(siebelMeterReadInformationList.length > 0){
			siebelAssetManagementInformation.setMeterReadInformation(siebelMeterReadInformationList);
		}
				
		LOGGER.debug("After setting page counts info");
		
		// Install Address
		
		GenericAddress SiebelServiceAddr;
		GenericAddress SiebelInstallAddr=null;
		/*if ("Move Asset".equalsIgnoreCase(serviceReqContract.getServiceRequest().getSubArea().getValue()) || "Map-Web".equalsIgnoreCase(sourceSystem)) {
			SiebelServiceAddr = srDataAsset.getMoveToAddress();
			//String installAddrforChange=createInstallChangeAddress(srDataAsset.getInstallAddress());	
			//cmServiceReqData.setServiceRequestComments(installAddrforChange);
			SiebelInstallAddr=srDataAsset.getInstallAddress();
		} else {
			SiebelServiceAddr = srDataAsset.getInstallAddress();
		}*/
		
		//if ("Move Asset".equalsIgnoreCase(serviceReqContract.getServiceRequest().getSubArea().getValue())|| (srDataAsset.getMoveToAddress().getAddressLine1()!=null&&srDataAsset.getMoveToAddress().getAddressLine1()!="")) {
		if ("Move Asset".equalsIgnoreCase(serviceReqContract.getServiceRequest().getSubArea().getValue())|| (srDataAsset.getMoveToAddress()!=null && "Map-Web".equalsIgnoreCase(sourceSystem)) ) {
            LOGGER.debug("MOVE asset");
            SiebelServiceAddr = srDataAsset.getMoveToAddress();
            LOGGER.debug("MOVE to asset");
            //String installAddrforChange=createInstallChangeAddress(srDataAsset.getInstallAddress());        
            //cmServiceReqData.setServiceRequestComments(installAddrforChange);
            SiebelInstallAddr=srDataAsset.getInstallAddress();
            LOGGER.debug("install asset");
    } else {
            SiebelServiceAddr = srDataAsset.getInstallAddress();
            LOGGER.debug("elase install asset");
    }
		if(SiebelInstallAddr!=null){
			SiebelAddress moveFromAddr = new SiebelAddress();
			
			if(SiebelInstallAddr.getAddressId() !=null){
				moveFromAddr.setAddressId(SiebelInstallAddr.getAddressId());
				}else{
					moveFromAddr.setAddressId("");
				}
				
			moveFromAddr.setAddressName(SiebelInstallAddr.getStoreFrontName());
				if(SiebelInstallAddr.getAddressLine1() !=null){
					moveFromAddr.setAddressLine1(SiebelInstallAddr.getAddressLine1());
				}else{
					moveFromAddr.setAddressLine1("");
				}
				moveFromAddr.setHouseNumber(SiebelInstallAddr.getOfficeNumber());
				moveFromAddr.setAddressLine2(SiebelInstallAddr.getAddressLine2());
				if(SiebelInstallAddr.getCity() != null){
					moveFromAddr.setCity(SiebelInstallAddr.getCity());
				}else{
					moveFromAddr.setCity("");
				}
				moveFromAddr.setCounty(SiebelInstallAddr.getCounty());
				moveFromAddr.setStateFullName(SiebelInstallAddr.getState());
				moveFromAddr.setProvince(SiebelInstallAddr.getProvince());
				moveFromAddr.setDistrict(SiebelInstallAddr.getDistrict());
				moveFromAddr.setPostalCode(SiebelInstallAddr.getPostalCode());
				moveFromAddr.setCountry(SiebelInstallAddr.getCountry());
				moveFromAddr.setRegion(SiebelInstallAddr.getRegion());
				moveFromAddr.setStateCode(SiebelInstallAddr.getStateCode());
				moveFromAddr.setPhysicalLocation1(SiebelInstallAddr.getPhysicalLocation1());
				moveFromAddr.setPhysicalLocation2(SiebelInstallAddr.getPhysicalLocation2());
				moveFromAddr.setPhysicalLocation3(SiebelInstallAddr.getPhysicalLocation3());
				if(SiebelInstallAddr.getIsAddressCleansed()){
					moveFromAddr.setAddressCleansedFlag("Y");
				}else{
					moveFromAddr.setAddressCleansedFlag("N");
				}		
				moveFromAddr.setAddressMessage(SiebelInstallAddr.getSavedErrorMessage());
				/*if("Decommission Asset".equalsIgnoreCase(serviceReqContract.getServiceRequest().getSubArea().getValue()) || "Deregister Asset".equalsIgnoreCase(serviceReqContract.getServiceRequest().getSubArea().getValue())){
					cmServiceReqData.setPickUpAddress(installAddr);
				}else{*/
				
				
				//LBS
				if(SiebelInstallAddr.getLbsAddressFlag()!=null&&SiebelInstallAddr.getLbsAddressFlag().equals(true)){
					LOGGER.debug("Setting LBS install address");
				}
				else{
							LOGGER.debug("Setting non LBS install address");
							//moveFromAddr.setPhysicalLocation1(SiebelInstallAddr.getPhysicalLocation1());
							//moveFromAddr.setPhysicalLocation2(SiebelInstallAddr.getPhysicalLocation2());
							
					}
					//moveFromAddr.setPhysicalLocation1(SiebelInstallAddr.getBuildingName());
					moveFromAddr.setBuildingId(SiebelInstallAddr.getBuildingId());
					//moveFromAddr.setPhysicalLocation2(SiebelInstallAddr.getFloorName());
					moveFromAddr.setFloorId(SiebelInstallAddr.getFloorId());
					moveFromAddr.setZone(SiebelInstallAddr.getZoneName());
					moveFromAddr.setZoneId(SiebelInstallAddr.getZoneId());
					moveFromAddr.setLatitude(SiebelInstallAddr.getLatitude());
					moveFromAddr.setLongitude(SiebelInstallAddr.getLongitude());
					
					moveFromAddr.setGridCoordinateX(SiebelInstallAddr.getCoordinatesXPreDebriefRFV());
					//LOGGER.debug("CoordinatesXmoveFromAddr"+SiebelInstallAddr.getCoordinatesXPreDebriefRFV());
					moveFromAddr.setGridCoordinateY(SiebelInstallAddr.getCoordinatesYPreDebriefRFV());
					//LOGGER.debug("CoordinatesYmoveFromAddr"+SiebelInstallAddr.getCoordinatesYPreDebriefRFV());
					
					
					//Added for LBS
					if(StringUtils.isNotBlank(SiebelInstallAddr.getCampusId())){
						LOGGER.debug("CampusID move from");
						moveFromAddr.setSiteId(SiebelInstallAddr.getCampusId());
					}
					if(StringUtils.isNotBlank(SiebelInstallAddr.getCampusName())){
						LOGGER.debug("CampusName move from");
						moveFromAddr.setSite(SiebelInstallAddr.getCampusName());
					}
					
					if(!moveFromAddr.getAddressLine1().equals("")){
						cmServiceReqData.setMoveToAddress(moveFromAddr);
					}
			
			
			}
		
		
		if(SiebelServiceAddr!=null){
		SiebelAddress installAddr = new SiebelAddress();
		if(SiebelServiceAddr.getAddressId() !=null && !"-1".equalsIgnoreCase(SiebelServiceAddr.getAddressId())){
		installAddr.setAddressId(SiebelServiceAddr.getAddressId());
		}else{
		installAddr.setAddressId("");
		}
		
		installAddr.setAddressName(SiebelServiceAddr.getStoreFrontName());
		if(SiebelServiceAddr.getAddressLine1() !=null){
		installAddr.setAddressLine1(SiebelServiceAddr.getAddressLine1());
		}else{
			installAddr.setAddressLine1("");
		}
		installAddr.setHouseNumber(SiebelServiceAddr.getOfficeNumber());
		installAddr.setAddressLine2(SiebelServiceAddr.getAddressLine2());
		if(SiebelServiceAddr.getCity() != null){
		installAddr.setCity(SiebelServiceAddr.getCity());
		}else{
			installAddr.setCity("");
		}
		installAddr.setCounty(SiebelServiceAddr.getCounty());
		installAddr.setStateFullName(SiebelServiceAddr.getState());
		installAddr.setProvince(SiebelServiceAddr.getProvince());
		installAddr.setDistrict(SiebelServiceAddr.getDistrict());
		installAddr.setPostalCode(SiebelServiceAddr.getPostalCode());
		installAddr.setCountry(SiebelServiceAddr.getCountry());
		installAddr.setRegion(SiebelServiceAddr.getRegion());
		installAddr.setStateCode(SiebelServiceAddr.getStateCode());
		installAddr.setPhysicalLocation1(SiebelServiceAddr.getPhysicalLocation1());
		installAddr.setPhysicalLocation2(SiebelServiceAddr.getPhysicalLocation2());
		installAddr.setPhysicalLocation3(SiebelServiceAddr.getPhysicalLocation3());
		//Added for LBS
		if(StringUtils.isNotBlank(SiebelServiceAddr.getCampusId())){
			LOGGER.debug("CampusID install");
			installAddr.setSiteId(SiebelServiceAddr.getCampusId());
		}
		if(StringUtils.isNotBlank(SiebelServiceAddr.getCampusName())){
			LOGGER.debug("CampusName install");
			installAddr.setSite(SiebelServiceAddr.getCampusName());
		}
		
		//For new Lbs fields
		LOGGER.debug("Before Setting LBS move to address"+SiebelServiceAddr.getLbsAddressFlag());
		//LBS
		//if(SiebelServiceAddr.getLbsAddressFlag()!=null&&SiebelServiceAddr.getLbsAddressFlag().equals(true)){
			LOGGER.debug("Setting LBS move to address");
			//installAddr.setPhysicalLocation1(SiebelServiceAddr.getBuildingName());
			installAddr.setBuildingId(SiebelServiceAddr.getBuildingId());
			//installAddr.setPhysicalLocation2(SiebelServiceAddr.getFloorName());
			installAddr.setFloorId(SiebelServiceAddr.getFloorId());
			installAddr.setZone(SiebelServiceAddr.getZoneName());
			installAddr.setZoneId(SiebelServiceAddr.getZoneId());
			installAddr.setLatitude(SiebelServiceAddr.getLatitude());
			installAddr.setLongitude(SiebelServiceAddr.getLongitude());
			
			installAddr.setGridCoordinateX(SiebelServiceAddr.getCoordinatesXPreDebriefRFV());
			//LOGGER.debug("CoordinatesYinstallAddr"+SiebelServiceAddr.getCoordinatesYPreDebriefRFV());
			installAddr.setGridCoordinateY(SiebelServiceAddr.getCoordinatesYPreDebriefRFV());
			//LOGGER.debug("CoordinatesYinstallAddr"+SiebelServiceAddr.getCoordinatesYPreDebriefRFV());
			
			
	//	}
		//else{
			//LOGGER.debug("Setting non LBS move to address");
			//installAddr.setPhysicalLocation1(SiebelServiceAddr.getPhysicalLocation1());
			//installAddr.setPhysicalLocation2(SiebelServiceAddr.getPhysicalLocation2());
			
	//	}
		
		//Ends Added for LBS
		
		if(SiebelServiceAddr.getIsAddressCleansed()){
			installAddr.setAddressCleansedFlag("Y");
		}else{
			installAddr.setAddressCleansedFlag("N");
		}		
		installAddr.setAddressMessage(SiebelServiceAddr.getSavedErrorMessage());
		
		
		/*if("Decommission Asset".equalsIgnoreCase(serviceReqContract.getServiceRequest().getSubArea().getValue()) || "Deregister Asset".equalsIgnoreCase(serviceReqContract.getServiceRequest().getSubArea().getValue())){
			cmServiceReqData.setPickUpAddress(installAddr);
		}else{*/
		
		if(!installAddr.getAddressLine1().equals("")){
			siebelAssetManagementInformation.setInstalledAtAddress(installAddr);
		}
		/*}*/
	}
		
		ActivityDetails2[] activityDetailsList = new ActivityDetails2[1];
		ActivityDetails2 activityDetails = new ActivityDetails2();
		activityDetails.setAssetInformation(siebelAssetManagementInformation);
		activityDetails.setActivityType("Install");
		RelatedActivityInformation[] relatedActivityInformationList = new RelatedActivityInformation[1];
		RelatedActivityInformation relatedActivityInformation = new RelatedActivityInformation();
		relatedActivityInformation.setActivityType("Deinstall");
		SiebelAssetManagementInformation deinstallInfo = new SiebelAssetManagementInformation();
		logger.debug("subarea--->>>"+serviceReqContract.getServiceRequest().getSubArea().getValue());
		if(serviceReqContract.getServiceRequest().getSubArea().getValue() != null && "HW MADC Install/Decommission".equalsIgnoreCase(serviceReqContract.getServiceRequest().getSubArea().getValue())){
			deinstallInfo.setSerialNumber(srDataAsset.getDeinstSerialNumber());
			deinstallInfo.setProductModel(srDataAsset.getDeinstModel());
			deinstallInfo.setBrand(srDataAsset.getDeinstBrand());
			deinstallInfo.setProductNumber(srDataAsset.getDeinstPartNumber());
			deinstallInfo.setHostName(srDataAsset.getDeinstHostName());
			deinstallInfo.setAssetTag(srDataAsset.getDeinstAssetTag());
			deinstallInfo.setIPAddress(srDataAsset.getDeinstIpAddress());
			relatedActivityInformation.setAssetInformation(deinstallInfo);
			relatedActivityInformation.setActivityComments(srDataAsset.getDeinstComments());
			relatedActivityInformationList[0]=relatedActivityInformation;
		}else{			
			deinstallInfo.setSerialNumber("");
			deinstallInfo.setProductModel("");
			deinstallInfo.setBrand("");
			deinstallInfo.setProductNumber("");
			deinstallInfo.setHostName("");
			deinstallInfo.setAssetTag("");
			deinstallInfo.setIPAddress("");
			relatedActivityInformation.setAssetInformation(deinstallInfo);
			relatedActivityInformation.setActivityComments("");
			relatedActivityInformationList[0]=relatedActivityInformation;
		}
		activityDetails.setRelatedActivityInformation(relatedActivityInformationList);
		activityDetailsList[0]=activityDetails;
		cmServiceReqData.setActivityDetails(activityDetailsList);
		LOGGER.debug("After setting install address info");
		
		// Contact information for Device			
		List<AccountContact> deviceContact = srDataAsset.getDeviceContact();
		// changes for defect 13865
		if(deviceContact!=null && (!"Decommission Asset".equalsIgnoreCase(serviceReqContract.getServiceRequest().getSubArea().getValue()) && !"Deregister Asset".equalsIgnoreCase(serviceReqContract.getServiceRequest().getSubArea().getValue()))) {
		int deviceContactSize = deviceContact.size();

		AssetContacts[] assetContactsList = new AssetContacts[deviceContactSize];
		
		for(int i=0;i<deviceContactSize;i++) {
			AssetContacts assetContacts = new AssetContacts();
			
			SiebelContact assetContactsValue = new SiebelContact();
			SiebelAddress assetContactsAddr = new SiebelAddress();
			
			assetContacts.setContactType(deviceContact.get(i).getDeviceContactType());
			
			assetContactsValue.setContactId(deviceContact.get(i).getContactId());
			assetContactsValue.setFirstName(deviceContact.get(i).getFirstName());
			assetContactsValue.setLastName(deviceContact.get(i).getLastName());
			assetContactsValue.setWorkPhone(deviceContact.get(i).getWorkPhone());
			assetContactsValue.setAlternatePhone(deviceContact.get(i).getAlternatePhone());
			assetContactsValue.setEmailAddress(deviceContact.get(i).getEmailAddress());
			
			assetContacts.setContactDetails(assetContactsValue);
			if(deviceContact.get(i).getAddress().getAddressId() != null && !"-1".equalsIgnoreCase(deviceContact.get(i).getAddress().getAddressId())){
			assetContactsAddr.setAddressId(deviceContact.get(i).getAddress().getAddressId());
			}else{
			assetContactsAddr.setAddressId("");
			}
			assetContactsAddr.setAddressName(deviceContact.get(i).getAddress().getStoreFrontName());
			if(deviceContact.get(i).getAddress().getAddressLine1() !=null){
				assetContactsAddr.setAddressLine1(deviceContact.get(i).getAddress().getAddressLine1());
			}else{
				assetContactsAddr.setAddressLine1("");
			}
			assetContactsAddr.setHouseNumber(deviceContact.get(i).getAddress().getOfficeNumber());
			assetContactsAddr.setAddressLine2(deviceContact.get(i).getAddress().getAddressLine2());
			if(deviceContact.get(i).getAddress().getCity() !=null){
				assetContactsAddr.setCity(deviceContact.get(i).getAddress().getCity());
			}else{
				assetContactsAddr.setCity("");
			}
			assetContactsAddr.setCounty(deviceContact.get(i).getAddress().getCounty());
			assetContactsAddr.setStateFullName(deviceContact.get(i).getAddress().getState());
			assetContactsAddr.setProvince(deviceContact.get(i).getAddress().getProvince());
			assetContactsAddr.setDistrict(deviceContact.get(i).getAddress().getDistrict());
			assetContactsAddr.setPostalCode(deviceContact.get(i).getAddress().getPostalCode());
			assetContactsAddr.setCountry(deviceContact.get(i).getAddress().getCountry());
			assetContactsAddr.setRegion(deviceContact.get(i).getAddress().getRegion());
			assetContactsAddr.setCountryCode(deviceContact.get(i).getAddress().getCountryISOCode());
			assetContactsAddr.setStateCode(deviceContact.get(i).getAddress().getStateCode());
			assetContactsAddr.setPhysicalLocation1(deviceContact.get(i).getAddress().getPhysicalLocation1());
			assetContactsAddr.setPhysicalLocation2(deviceContact.get(i).getAddress().getPhysicalLocation2());
			assetContactsAddr.setPhysicalLocation3(deviceContact.get(i).getAddress().getPhysicalLocation3());
			if(deviceContact.get(i).getAddress().getIsAddressCleansed()){
				assetContactsAddr.setAddressCleansedFlag("Y");
			}else{
				assetContactsAddr.setAddressCleansedFlag("N");
			}	
			assetContactsAddr.setAddressMessage(deviceContact.get(i).getAddress().getSavedErrorMessage());
			
			assetContacts.setContactAddress(assetContactsAddr);
			
			assetContactsList[i] = assetContacts;
		}
		if(assetContactsList.length > 0){
			cmServiceReqData.setAssetContacts(assetContactsList);
		}
		
		
		LOGGER.debug("After setting device contact info");
		}
		
		// Attachment notes
		cmServiceReqData.setAttachmentNotes(srDataAsset.getNotes());
		
		}
		
		/* End Mps2.1 Wave4 */
		if(LOGGER.isDebugEnabled()){
			LOGGER.debug("Area is " + serviceReqContract.getServiceRequest().getArea().getValue());
			LOGGER.debug("Sub area is " + serviceReqContract.getServiceRequest().getSubArea().getValue());
		}
		
		SiebelContact requestor=null;		
		if(srData.getRequestor()!=null)
		{
			requestor= new SiebelContact();
			requestor.setContactId(srData.getRequestor().getContactId());
			requestor.setEmailAddress(srData.getRequestor().getEmailAddress());
			requestor.setFirstName(srData.getRequestor().getFirstName());
			requestor.setLastName(srData.getRequestor().getLastName());
			requestor.setWorkPhone(srData.getRequestor().getWorkPhone());
			LOGGER.debug("user type is "+serviceReqContract.getUserType());
			if(LexmarkConstants.USER_SEGMENT_PARTNER.equalsIgnoreCase(serviceReqContract.getUserType()))
				requestor.setContactType(ChangeMgmtConstant.VENDORREQTYPE);
			else{
				if(serviceReqContract.getUserType().equalsIgnoreCase(LexmarkConstants.USER_SEGMENT_EMPLOYEE))
				{
					LOGGER.debug("User type is employee");
					requestor.setContactType(ChangeMgmtConstant.LEXMARKREQTYPE);
				}else{
					requestor.setContactType(serviceReqContract.getUserType());
				}
			}
			LOGGER.info("Setting in cm service req data requestor info");
			cmServiceReqData.setRequester(requestor);
		}				
		LOGGER.info("Setting info of the requestor Contact ID: " + srData.getPrimaryContact().getContactId() + "First Name :" + srData.getPrimaryContact().getFirstName() + "Lst Name: " + srData.getPrimaryContact().getLastName() + "Email ID: " +  srData.getPrimaryContact().getEmailAddress()+"Ph no is"+srData.getPrimaryContact().getWorkPhone());
				
		SiebelContact primaryContact=null;		
		if(srData.getPrimaryContact()!=null)
		{
			primaryContact=new SiebelContact();
			primaryContact.setContactId(srData.getPrimaryContact().getContactId());
			primaryContact.setEmailAddress(srData.getPrimaryContact().getEmailAddress());
			primaryContact.setFirstName(srData.getPrimaryContact().getFirstName());
			primaryContact.setLastName(srData.getPrimaryContact().getLastName());
			primaryContact.setWorkPhone(srData.getPrimaryContact().getWorkPhone());
			primaryContact.setAlternatePhone(srData.getPrimaryContact().getAlternatePhone());
			
			LOGGER.info("Setting in cm service req data primary contact alternate phone"+srData.getPrimaryContact().getAlternatePhone());
			
			cmServiceReqData.setPrimaryContact(primaryContact);
		}		
		LOGGER.info("Setting secondary contact ");
		SiebelContact secContact=null;		
		if(srData.getSecondaryContact()!=null)
		{
			secContact=new SiebelContact();//factory.createSecondaryContact2();
			secContact.setContactId(srData.getSecondaryContact().getContactId());
			secContact.setEmailAddress(srData.getSecondaryContact().getEmailAddress());
			secContact.setFirstName(srData.getSecondaryContact().getFirstName());
			secContact.setLastName(srData.getSecondaryContact().getLastName());
			secContact.setWorkPhone(srData.getSecondaryContact().getWorkPhone());
			secContact.setAlternatePhone(srData.getSecondaryContact().getAlternatePhone());
			
			LOGGER.info("Setting in cm service req data secondary contact alternate Phone no is" +srData.getSecondaryContact().getAlternatePhone());			
			cmServiceReqData.setSecondaryContact(secContact);
		}
		
		// Setting the previous Service Request No for the Update call
		LOGGER.debug("The previous service request no is -->" +serviceReqContract.getPrevSrNo());
		
		if(serviceReqContract.getPrevSrNo()!=null){
			 LOGGER.debug("Inside If block while previous service request no is not null-->"+serviceReqContract.getPrevSrNo() );
			 serviceRequestNumber= serviceReqContract.getPrevSrNo(); 
			 cmServiceReqData.setServiceRequestNumber(serviceRequestNumber);
		 }		 
		ChangeManagementServiceRequestWSInput2 cmServiceReqWSInputTwo=new ChangeManagementServiceRequestWSInput2();
		cmServiceReqWSInputTwo.setChangeManagementServiceRequestData(cmServiceReqData);
		cmServiceReqWSInputTwo.setDocumentMetaData(documentMetaData);		
		ChangeManagementServiceRequestWSInput cmServiceReqWSInput=new ChangeManagementServiceRequestWSInput();
		cmServiceReqWSInput.setChangeManagementServiceRequestWSInput(cmServiceReqWSInputTwo);		
		StringHolder srNumber = new StringHolder();
		StringHolder srRowId = new StringHolder();
		ServiceRequestDetailsOutputHolder serviceRequestDetailsOutPutHolder = new ServiceRequestDetailsOutputHolder();	
		
		ObjectDebugUtil.printMultiObjectContent(cmServiceReqWSInput,logger);
		if(LOGGER.isDebugEnabled()){
			LOGGER.debug("Calling WM endpoint URL -->"+getAddress());
		}
		LOGGER.debug(" New Webservice Call started ----->"+System.currentTimeMillis());	
		long timeBeforeCall = System.currentTimeMillis();
		port.createChangeManagementServiceRequest(debug, cmServiceReqWSInput, synchOrAsynch, serviceRequestDetailsOutPutHolder, srRowId, srNumber);
		long timeAfterCall=System.currentTimeMillis();
		
		PerformanceUtil.calcTime(perfLogger, PerformanceConstant.MANAGEASSETS_MSG_CREATECMREQUESTSERVICE, timeBeforeCall,timeAfterCall, PerformanceConstant.SYSTEM_WEBMETHODS,serviceReqContract);
		//LOGGER.debug("After the ws call " + System.currentTimeMillis());
		result = new CreateServiceRequestResult();
		if(LOGGER.isDebugEnabled()){
			LOGGER.debug("SR Number CM Req is "+ srNumber.value);
			LOGGER.debug("SR Row ID CM Req is " + srRowId.value);
		}
		
		if(!StringUtil.isStringEmpty(srNumber.value) && !StringUtil.isStringEmpty(srRowId.value)){
			result.setServiceRequestNumber(srNumber.value);
			result.setServiceRequestRowId(srRowId.value);
		} 
		else {
				throw new LGSBusinessException("Error creating service request - SR Number not received");
			}
		
		}
		catch (Exception ex) {
			LOGGER.debug("Exception while saving data. ");
			ex.printStackTrace();
			throw new LGSRuntimeException("Error while saving data");
		}
   		LOGGER.exit(this.getClass().getSimpleName(), CREATECMREQSERV);
		return result;
		
	}

	private String createInstallChangeAddress(GenericAddress installAddress) {
					
			StringBuffer addressString=new StringBuffer();
			for(String s:LexmarkConstants.addressArr){
				Object obj=null;
				try {
					obj = PropertyUtils.getProperty(installAddress, s);
				} catch (IllegalAccessException e) {
					LOGGER.error("error occured while reading the address field "+s);
				} catch (InvocationTargetException e) {
					LOGGER.error("error occured while reading the address field "+s);
				} catch (NoSuchMethodException e) {
					LOGGER.error("error occured while reading the address field "+s);
				}
				if(obj!=null){
					addressString.append((String)obj).append("|");
				}else{
					addressString.append("|");
				}
			}
			return addressString.toString();
	
		/*
		 * Commented for 11578
		 * 
		 * if(moveToAddress.getAddressId()!=null && !"".equals(moveToAddress.getAddressId().trim())){
			moveAddrforChange.append(moveToAddress.getAddressId() +"|");
		}
		if(moveToAddress.getStoreFrontName()!=null && !"".equals(moveToAddress.getStoreFrontName().trim())){
			moveAddrforChange.append(moveToAddress.getStoreFrontName() +"|");
		}
		if(moveToAddress.getAddressLine1()!=null && !"".equals(moveToAddress.getAddressLine1().trim())){
			moveAddrforChange.append(moveToAddress.getAddressLine1() +"|");
		}
		if(moveToAddress.getOfficeNumber()!=null && !"".equals(moveToAddress.getOfficeNumber().trim())){
			moveAddrforChange.append(moveToAddress.getOfficeNumber() +"|");
		}
		if(moveToAddress.getAddressLine2()!=null && !"".equals(moveToAddress.getAddressLine2().trim())){
			moveAddrforChange.append(moveToAddress.getAddressLine2() +"|");
		}
		if(moveToAddress.getCity()!=null && !"".equals(moveToAddress.getCity().trim())){
			moveAddrforChange.append(moveToAddress.getCity() +"|");
		}
		if(moveToAddress.getCounty()!=null && !"".equals(moveToAddress.getCounty().trim())){
			moveAddrforChange.append(moveToAddress.getCounty() +"|");
		}
		if(moveToAddress.getProvince()!=null && !"".equals(moveToAddress.getProvince().trim())){
			moveAddrforChange.append(moveToAddress.getProvince() +"|");
		}
		if(moveToAddress.getDistrict()!=null && !"".equals(moveToAddress.getDistrict().trim())){
			moveAddrforChange.append(moveToAddress.getDistrict() +"|");
		}
		if(moveToAddress.getPostalCode()!=null && !"".equals(moveToAddress.getPostalCode().trim())){
			moveAddrforChange.append(moveToAddress.getPostalCode() +"|");
		}
		if(moveToAddress.getCountry()!=null && !"".equals(moveToAddress.getCountry().trim())){
			moveAddrforChange.append(moveToAddress.getCountry() +"|");
		}
		if(moveToAddress.getRegion()!=null && !"".equals(moveToAddress.getRegion().trim())){
			moveAddrforChange.append(moveToAddress.getRegion() +"|");
		}
		if(moveToAddress.getStateCode()!=null && !"".equals(moveToAddress.getStateCode().trim())){
			moveAddrforChange.append(moveToAddress.getStateCode() +"|");
		}
		if(moveToAddress.getPhysicalLocation1()!=null && !"".equals(moveToAddress.getPhysicalLocation1().trim())){
			moveAddrforChange.append(moveToAddress.getPhysicalLocation1() +"|");
		}
		if(moveToAddress.getPhysicalLocation2()!=null && !"".equals(moveToAddress.getPhysicalLocation2().trim())){
			moveAddrforChange.append(moveToAddress.getPhysicalLocation2() +"|");
		}
		if(moveToAddress.getPhysicalLocation3()!=null && !"".equals(moveToAddress.getPhysicalLocation3().trim())){
			moveAddrforChange.append(moveToAddress.getPhysicalLocation3());
		}
		return moveAddrforChange.toString();*/
	}
   	
}
