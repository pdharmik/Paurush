package com.lexmark.webservice.impl.real;

import java.net.URL;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import com.lexmark.SiebelShared.createServiceRequest.client.DebriefDetails;
import com.lexmark.SiebelShared.createServiceRequest.client.DebriefServiceRequestActivityInput;
import com.lexmark.SiebelShared.createServiceRequest.client.DebriefServiceRequestActivityWSInput;
import com.lexmark.SiebelShared.createServiceRequest.client.Entitlement5;
import com.lexmark.SiebelShared.createServiceRequest.client.EntitlementServiceDetails5;
import com.lexmark.SiebelShared.createServiceRequest.client.NoteAuthor;
import com.lexmark.SiebelShared.createServiceRequest.client.OriginalDeviceInformation;
import com.lexmark.SiebelShared.createServiceRequest.client.PartInformation;
import com.lexmark.SiebelShared.createServiceRequest.client.ReplacementDeviceInformation;
import com.lexmark.SiebelShared.createServiceRequest.client.SelectedServiceDetails2;
import com.lexmark.SiebelShared.createServiceRequest.client.ServiceRequestData2;
import com.lexmark.SiebelShared.createServiceRequest.client.ServiceRequestData3;
import com.lexmark.SiebelShared.createServiceRequest.client.ServiceRequestData4;
import com.lexmark.SiebelShared.createServiceRequest.client.ServiceRequestInput;
import com.lexmark.SiebelShared.createServiceRequest.client.ServiceRequestWS;
import com.lexmark.SiebelShared.createServiceRequest.client.ServiceRequestWSInput;
import com.lexmark.SiebelShared.createServiceRequest.client.ServiceRequestWSLocator;
import com.lexmark.SiebelShared.createServiceRequest.client.ServiceRequestWS_PortType;
import com.lexmark.SiebelShared.createServiceRequest.client.SiebelActivityNotes;
import com.lexmark.SiebelShared.createServiceRequest.client.SiebelAddress;
import com.lexmark.SiebelShared.createServiceRequest.client.SiebelContact;
import com.lexmark.SiebelShared.createServiceRequest.client.SiebelPaymentRequestList;
import com.lexmark.SiebelShared.createServiceRequest.client.SiebelServiceProviderInformation;
import com.lexmark.SiebelShared.createServiceRequest.client.UpdateServiceRequestActivityInput;
import com.lexmark.SiebelShared.createServiceRequest.client.UpdateServiceRequestActivityWSInput;
import com.lexmark.SiebelShared.createServiceRequest.client.WebServiceDocumentMetaData;
import com.lexmark.contract.ActivityDebriefSubmitContract;
import com.lexmark.contract.ChildSRContract;
import com.lexmark.contract.RequestUpdateContract;
import com.lexmark.domain.ActivityNote;
import com.lexmark.domain.AdditionalPaymentRequest;
import com.lexmark.domain.EntitlementServiceDetail;
import com.lexmark.domain.PartLineItem;
import com.lexmark.domain.ServiceRequest;
import com.lexmark.result.ActivityDebriefSubmitResult;
import com.lexmark.result.CreateChildSRResult;
import com.lexmark.result.RequestUpdateResult;
import com.lexmark.util.XMLEncodeUtil;
import com.lexmark.webservice.api.RequestService;
import com.lexmark.SiebelShared.createServiceRequest.client.SiebelRecommendedPartsList;

public class RequestServiceImpl implements RequestService {
	private static Logger logger = LogManager.getLogger(RequestServiceImpl.class);
	
	private String address;
	private String senderId; 
	private String senderName;
	private String receiverId;
	private String claimSource;
	private String synchOrAsynch;
	private String userName;
	private String password;
	
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

	public String getClaimSource() {
		return claimSource;
	}

	public void setClaimSource(String claimSource) {
		this.claimSource = claimSource;
	}

	public String getSynchOrAsynch() {
		return synchOrAsynch;
	}

	public void setSynchOrAsynch(String synchOrAsynch) {
		this.synchOrAsynch = synchOrAsynch;
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

	@Override
	public RequestUpdateResult updateRequest(RequestUpdateContract contract)
			throws Exception {
		logger.debug("************ UPDATE REQUEST - REAL ************");
		logger.debug("address: " + address);
		logger.debug("senderId: " + senderId);
		logger.debug("senderName: " + senderName);
		logger.debug("receiverId: " + receiverId);
		logger.debug("claimSource: " + claimSource);
		logger.debug("synchOrAsynch: " + synchOrAsynch);
		
		
		ServiceRequestWS locator = new ServiceRequestWSLocator();
		logger.debug("WSDL Endpoint Address is: " +  address );		
		ServiceRequestWS_PortType port = locator.getServiceRequest_serviceRequestWS_Port(new URL(getAddress()));
		logger.debug("the port address is--->>>"+port);
		org.apache.axis.client.Stub stub = ((org.apache.axis.client.Stub) port);
		stub.setUsername(getUserName());
		stub.setPassword(getPassword());
		WebServiceDocumentMetaData documentMetaData = new WebServiceDocumentMetaData(senderId, senderName, null, null, receiverId, null);
		
		
		//Primary Contact--START
		SiebelContact primaryContact = new SiebelContact();
		primaryContact.setFirstName(contract.getActivity().getServiceRequest().getPrimaryContact().getFirstName());
		primaryContact.setLastName(contract.getActivity().getServiceRequest().getPrimaryContact().getLastName());
		primaryContact.setWorkPhone(contract.getActivity().getServiceRequest().getPrimaryContact().getWorkPhone());
		primaryContact.setEmailAddress(contract.getActivity().getServiceRequest().getPrimaryContact().getEmailAddress());
		//Primary Contact--ENDS
		
		//Technician --START
		SiebelContact technician = new SiebelContact();
		technician.setContactId(contract.getActivity().getTechnician().getContactId());
		technician.setFirstName(contract.getActivity().getTechnician().getFirstName());
		technician.setLastName(contract.getActivity().getTechnician().getLastName());
		technician.setUpdateContactFlag(booleanToString(contract.getActivity().getTechnician().getUpdateContactFlag()));
		technician.setNewContactFlag(booleanToString(contract.getActivity().getTechnician().getNewContactFlag()));
		//Technician --ENDS
		
		SiebelActivityNotes[] activityNotes = null;
		   if (contract.getActivity().getActivityNoteList() != null){
			   activityNotes = new SiebelActivityNotes[contract.getActivity().getActivityNoteList().size()];
				int n = 0;	   
			   for (ActivityNote activityNoteLineItem : contract.getActivity().getActivityNoteList()) { 	
				   NoteAuthor noteAuthor = new NoteAuthor(
						   activityNoteLineItem.getNoteAuthor().getContactId(),
						   activityNoteLineItem.getNoteAuthor().getFirstName(),
						   activityNoteLineItem.getNoteAuthor().getLastName(),
						   null);

				   SiebelActivityNotes activityNote = new SiebelActivityNotes();
				   activityNote.setNoteId(activityNoteLineItem.getNoteId());
				   activityNote.setNoteDate(dateToString(activityNoteLineItem.getNoteDate()));
				   activityNote.setNoteAuthor(noteAuthor);
				   activityNote.setNoteDetails(activityNoteLineItem.getNoteDetails());
				   activityNote.setNoteUpdateFlag(booleanToString(activityNoteLineItem.isActivityUpdateFlag()));
				
				   activityNotes[n] = activityNote;
			        n++;
			   }
			   
		   }
				  
		   SiebelServiceProviderInformation serviceProviderInformation = new SiebelServiceProviderInformation();
		   serviceProviderInformation.setServiceProviderAccountId(contract.getActivity().getPartnerAccount().getAccountId());
		   serviceProviderInformation.setServiceProviderReferenceNumber(contract.getActivity().getServiceProviderReferenceNumber());
		   serviceProviderInformation.setServiceProviderName(contract.getActivity().getPartnerAccount().getAccountName());
		   serviceProviderInformation.setServiceProviderTimeOfArrival(dateToString(contract.getActivity().getEstimatedArrivalTime()));
		   serviceProviderInformation.setServiceProviderRequestedResponseDate(dateToString(contract.getActivity().getCustomerRequestedResponseDate()));
		   serviceProviderInformation.setServiceProviderStatus(contract.getActivity().getServiceProviderStatus());
		   serviceProviderInformation.setServiceProviderStatusAsOfDate(dateToString(contract.getActivity().getStatusUpdateDate()));
		   serviceProviderInformation.setServiceProviderComments(contract.getActivity().getServicerComments());
		   //loggers to test BODS Address Cleansing Parameters--CI 7
		    logger.debug("shipToAddress.getAddressId()"+contract.getActivity().getShipToAddress().getAddressId());
			logger.debug("shipToAddress.getAddressLine1()"+contract.getActivity().getShipToAddress().getAddressLine1());
			logger.debug("shipToAddress.getAddressLine2()"+contract.getActivity().getShipToAddress().getAddressLine2());
			logger.debug("shipToAddress.getOfficeNumber()"+contract.getActivity().getShipToAddress().getOfficeNumber());
			logger.debug("shipToAddress.getCity()"+contract.getActivity().getShipToAddress().getCity());
			logger.debug("shipToAddress.getStateCode()"+contract.getActivity().getShipToAddress().getStateCode());
			logger.debug("shipToAddress.getProvince()"+contract.getActivity().getShipToAddress().getProvince());
			logger.debug("shipToAddress.getRegion()"+contract.getActivity().getShipToAddress().getRegion());
			logger.debug("shipToAddress.getCountry()"+contract.getActivity().getShipToAddress().getCountry());
			logger.debug("shipToAddress.getCountryCode()"+contract.getActivity().getShipToAddress().getCountryISOCode());
			logger.debug("shipToAddress.getPostalCode()"+contract.getActivity().getShipToAddress().getPostalCode());
			logger.debug("shipToAddress.getPhysicalLocation1()"+contract.getActivity().getShipToAddress().getPhysicalLocation1());
			logger.debug("shipToAddress.getPhysicalLocation2()"+contract.getActivity().getShipToAddress().getPhysicalLocation2());
			logger.debug("shipToAddress.getPhysicalLocation3()"+contract.getActivity().getShipToAddress().getPhysicalLocation3());
			logger.debug("shipToAddress.getStateFullName()"+contract.getActivity().getShipToAddress().getStateFullName());
			logger.debug("shipToAddress.getLatitude()"+contract.getActivity().getShipToAddress().getLatitude());
			logger.debug("shipToAddress.getLongitude()"+contract.getActivity().getShipToAddress().getLongitude());
			logger.debug("shipToAddress.getAddressCleansedFlag()"+contract.getActivity().getShipToAddress().getIsAddressCleansed());
			logger.debug("shipToAddress.getAddressMessage()"+contract.getActivity().getShipToAddress().getErrorMsgForCleansing());
			logger.debug("shipToAddress.getNewAddressFlag()"+contract.getActivity().getShipToAddress().getNewAddressFlag());
			
		   SiebelRecommendedPartsList [] recommendedPartsList = null;
		   //SiebelRecommendedPartsList [] recommendedPartsListEmpty = null;
		   if(contract.getActivity().getOrderPartList() != null){

			   SiebelAddress shipToAddress = null;
			   if (contract.getActivity().getShipToAddress()== null || 
					   contract.getActivity().getShipToAddress().getAddressLine1() == null  || 
					   contract.getActivity().getShipToAddress().getAddressLine1().equals("")){

				   shipToAddress = new SiebelAddress();
			   } else {
				   String shipToStateProvince = null;
					if(contract.getActivity().getShipToAddress().getStateProvince() != null && 
							!contract.getActivity().getShipToAddress().getStateProvince().equals("") && 
							!contract.getActivity().getShipToAddress().getStateProvince().equals("null")){
						shipToStateProvince = contract.getActivity().getShipToAddress().getStateProvince();
					}else if(contract.getActivity().getShipToAddress().getState() != null && 
							!contract.getActivity().getShipToAddress().getState().equals("") && 
							!contract.getActivity().getShipToAddress().getState().equals("null")){
						shipToStateProvince = contract.getActivity().getShipToAddress().getState();
					}else{
						shipToStateProvince = contract.getActivity().getShipToAddress().getProvince();
					}
	   
					shipToAddress = new SiebelAddress();
					shipToAddress.setAddressId("");
					if(contract.getActivity().getShipToAddress().getAddressId()!=null){
					shipToAddress.setAddressId(contract.getActivity().getShipToAddress().getAddressId().replaceAll(",", ""));
					}
					/*else
					{
						
					shipToAddress.setAddressId("null");	
					}*/
					shipToAddress.setAddressLine1(contract.getActivity().getShipToAddress().getAddressLine1().replaceAll(",", ""));
					shipToAddress.setAddressLine2(contract.getActivity().getShipToAddress().getAddressLine2().replaceAll(",", ""));
					shipToAddress.setHouseNumber(contract.getActivity().getShipToAddress().getOfficeNumber().replaceAll(",", ""));
					shipToAddress.setCity(contract.getActivity().getShipToAddress().getCity().replaceAll(",", ""));
					// print the state in XML  Manish
					if(contract.getActivity().getShipToAddress().getStateCode()!=null && !"".equals(contract.getActivity().getShipToAddress().getStateCode().toString().trim())){
					shipToAddress.setStateCode(contract.getActivity().getShipToAddress().getStateCode().replaceAll(",", ""));
					}
					else{
						shipToAddress.setStateCode(contract.getActivity().getShipToAddress().getState().replaceAll(",", ""));	
					}
					//shipToAddress.setStateCode(contract.getActivity().getShipToAddress().getStateCode().replaceAll(",", ""));
					shipToAddress.setProvince(contract.getActivity().getShipToAddress().getProvince().replaceAll(",", ""));
					shipToAddress.setDistrict(contract.getActivity().getShipToAddress().getDistrict().replaceAll(",", ""));
					shipToAddress.setCountry(contract.getActivity().getShipToAddress().getCountry().replaceAll(",", ""));
					shipToAddress.setRegion(contract.getActivity().getShipToAddress().getRegion().replaceAll(",", ""));
					shipToAddress.setCountry(contract.getActivity().getShipToAddress().getCountry().replaceAll(",", ""));
					shipToAddress.setCountryCode(contract.getActivity().getShipToAddress().getCountryISOCode().replaceAll(",", ""));
					shipToAddress.setPostalCode(contract.getActivity().getShipToAddress().getPostalCode().replaceAll(",", ""));
					shipToAddress.setPhysicalLocation1(contract.getActivity().getShipToAddress().getPhysicalLocation1());
					shipToAddress.setPhysicalLocation2(contract.getActivity().getShipToAddress().getPhysicalLocation2());
					shipToAddress.setPhysicalLocation3(contract.getActivity().getShipToAddress().getPhysicalLocation3());
					shipToAddress.setStateFullName(contract.getActivity().getShipToAddress().getStateFullName());
					shipToAddress.setLatitude(contract.getActivity().getShipToAddress().getLatitude());
					shipToAddress.setLongitude(contract.getActivity().getShipToAddress().getLongitude());
					if(contract.getActivity().getShipToAddress().getIsAddressCleansed()){
					shipToAddress.setAddressCleansedFlag("Y");
					}
					else
					{
					shipToAddress.setAddressCleansedFlag("N");	
					}
					//shipToAddress.setAddressCleansedFlag(booleanToString(contract.getActivity().getShipToAddress().getIsAddressCleansed()));
					shipToAddress.setAddressMessage(contract.getActivity().getShipToAddress().getErrorMsgForCleansing());
					shipToAddress.setNewAddressFlag(contract.getActivity().getShipToAddress().getNewAddressFlag());
			   }
			   
			   // get the recommended parts count which has quantity greater than zero
			   int nonZeroQtyParts = 0;
			   for (PartLineItem getLineItemCount : contract.getActivity().getRecommendedPartList()){
				   int qty = getLineItemCount.getQuantity();
				   if ( qty >0)
					   nonZeroQtyParts++;
			   }
			   	   
			   recommendedPartsList = new SiebelRecommendedPartsList[nonZeroQtyParts];
				int i = 0;	   
			   for (PartLineItem lineItem : contract.getActivity().getRecommendedPartList()) { 	
				   PartInformation partInformation = new PartInformation();
				   partInformation.setPartNumber( lineItem.getPartNumber());
				   partInformation.setPartId(lineItem.getPartId());
				   partInformation.setPartName(lineItem.getPartName());
				   
				   
				   
						 
				   
				   int Quantity = lineItem.getQuantity();
				   if (Quantity != 0){ 
					   
					   SiebelRecommendedPartsList recommendedPart = null;

					   recommendedPart = new SiebelRecommendedPartsList();
					   
					   recommendedPart.setOrderDate(dateToString(lineItem.getPartOrderedDate()));
					   recommendedPart.setQuantity(Integer.toString(lineItem.getQuantity()));
					   recommendedPart.setPartInformation(partInformation);
					   recommendedPart.setReturnRequiredFlag(booleanToString(lineItem.isReturnRequiredFlag()));
					   recommendedPart.setShipToAddress(shipToAddress);
					   recommendedPart.setRecommendedPartUpdatedFlag(booleanToString(lineItem.isPartLineItemUpdateFlag()));
					
						recommendedPartsList[i] = recommendedPart;
				        i++;	
					}				  
			        
			   }
			   
			   // The below is added as part of BRD #14-07-02 where we need to pass the ship-to address even if there is no recommended parts
			   if(nonZeroQtyParts==0)
			   {
				   logger.debug("No of recommended part is zero");
				   recommendedPartsList = new SiebelRecommendedPartsList[1];
				   SiebelRecommendedPartsList recommendedPart = null; 				  
				   recommendedPart = new SiebelRecommendedPartsList();
				   recommendedPart.setQuantity("");
				   recommendedPart.setPartInformation(new PartInformation());
				   recommendedPart.setShipToAddress(shipToAddress);
				   recommendedPart.setReturnRequiredFlag("");
				   recommendedPartsList[0] = recommendedPart;
			   }
			}
		   
		   SiebelContact requester = new SiebelContact();
		   requester.setContactId(contract.getActivity().getServiceRequest().getRequestor().getContactId());
		   requester.setFirstName(contract.getActivity().getServiceRequest().getRequestor().getFirstName());
		   requester.setLastName(contract.getActivity().getServiceRequest().getRequestor().getLastName());
		   requester.setDepartment(contract.getActivity().getServiceRequest().getRequestor().getDepartment());
		   requester.setWorkPhone(contract.getActivity().getServiceRequest().getRequestor().getWorkPhone());
		   requester.setEmailAddress(contract.getActivity().getServiceRequest().getRequestor().getEmailAddress());
		
		   ServiceRequestData2 requestData2 = new ServiceRequestData2();
		   
		   requestData2.setServiceRequestNumber(contract.getActivity().getServiceRequest().getServiceRequestNumber());
		   requestData2.setServiceRequestId(contract.getActivity().getServiceRequest().getId());
		   requestData2.setActivityId(contract.getActivity().getActivityId());
		   requestData2.setServiceRequestDate(dateToString(contract.getActivity().getServiceRequest().getServiceRequestDate()));
		   requestData2.setServiceRequestStatus(contract.getActivity().getServiceRequest().getServiceRequestStatus());
		   requestData2.setServiceRequestSource(getClaimSource());
		   requestData2.setActivityStatus(contract.getActivity().getActivityStatus().getValue());
		   requestData2.setRequester(requester);
		   requestData2.setPrimaryContact(primaryContact);
		   requestData2.setServiceProviderInformation(serviceProviderInformation);
		   requestData2.setTechnician(technician);
		   requestData2.setRecommendedPartsList(recommendedPartsList);
		   requestData2.setActivityNotes(activityNotes);
		   
		UpdateServiceRequestActivityInput updateServiceRequestActivityInput = new UpdateServiceRequestActivityInput();
		UpdateServiceRequestActivityWSInput inputWS = new UpdateServiceRequestActivityWSInput(documentMetaData, requestData2);
		updateServiceRequestActivityInput.setUpdateServiceRequestActivityWSInput(inputWS);
		String Status = port.updateServiceRequestActivity("$null",synchOrAsynch, updateServiceRequestActivityInput);
		
		boolean successFlag = false;
		if(Status.equalsIgnoreCase("success"))
			successFlag = true;
		
		RequestUpdateResult result = new RequestUpdateResult();
		result.setSuccess(successFlag);
		return result;
	}

	@Override
	public ActivityDebriefSubmitResult submitActivityDebrief(
			ActivityDebriefSubmitContract contract) throws Exception {
		logger.debug("************ SUBMIT ACTIVITY DEBRIEF - REAL ************");
		logger.debug("address: " + address);
		logger.debug("senderId: " + senderId);
		logger.debug("senderName: " + senderName);
		logger.debug("receiverId: " + receiverId);
		logger.debug("claimSource: " + claimSource);
		logger.debug("synchOrAsynch: " + synchOrAsynch);
				
		ServiceRequestWS locator = new ServiceRequestWSLocator();
		logger.debug("WSDL Endpoint Address is: " +  address );
		ServiceRequestWS_PortType port = locator.getServiceRequest_serviceRequestWS_Port(new URL(getAddress()));
		org.apache.axis.client.Stub stub = ((org.apache.axis.client.Stub) port);
		stub.setUsername(getUserName());
		stub.setPassword(getPassword());
		WebServiceDocumentMetaData documentMetaData = new WebServiceDocumentMetaData(
				senderId, senderName, null, null, receiverId, null);

		SiebelContact primaryContact= new SiebelContact();
		primaryContact.setFirstName(contract.getActivity().getServiceRequest().getPrimaryContact().getFirstName());
		primaryContact.setLastName(contract.getActivity().getServiceRequest().getPrimaryContact().getLastName());
		primaryContact.setWorkPhone(contract.getActivity().getServiceRequest().getPrimaryContact().getWorkPhone());
		primaryContact.setEmailAddress(contract.getActivity().getServiceRequest().getPrimaryContact().getEmailAddress());

		SiebelContact requester = new SiebelContact();
		requester.setContactId(contract.getActivity().getServiceRequest().getRequestor().getContactId());
		requester.setFirstName(contract.getActivity().getServiceRequest().getRequestor().getFirstName());
		requester.setLastName(contract.getActivity().getServiceRequest().getRequestor().getLastName());
		requester.setDepartment(contract.getActivity().getServiceRequest().getRequestor().getDepartment());
		requester.setWorkPhone(contract.getActivity().getServiceRequest().getRequestor().getWorkPhone());
		requester.setEmailAddress(contract.getActivity().getServiceRequest().getRequestor().getEmailAddress());   
		
		SiebelContact technician = new SiebelContact();
		technician.setContactId(contract.getActivity().getTechnician().getContactId());
		technician.setFirstName(contract.getActivity().getTechnician().getFirstName());
		technician.setLastName(contract.getActivity().getTechnician().getLastName());
		technician.setUpdateContactFlag(booleanToString(contract.getActivity().getTechnician().getUpdateContactFlag()));
		technician.setNewContactFlag(booleanToString(contract.getActivity().getTechnician().getNewContactFlag()));
		
		OriginalDeviceInformation originalDeviceInformation = new OriginalDeviceInformation(
				contract.getActivity().getServiceRequest().getAsset().getAssetId(),
				contract.getActivity().getDebrief().getDeviceCondition().getValue(),
				contract.getActivity().getServiceRequest().getAsset().getNewPageCount(),
				contract.getActivity().getServiceRequest().getAsset().getAssetTag(),
				booleanToString(contract.getActivity().getServiceRequest().getAsset().getNetworkConnectedFlag()),
				contract.getActivity().getServiceRequest().getAsset().getIpAddress(),
				contract.getActivity().getServiceRequest().getAsset().getMacAddress());
		
		ReplacementDeviceInformation replacementDeviceInformation = new ReplacementDeviceInformation(
				contract.getActivity().getDebrief().getInstalledAsset().getAssetId(),
				contract.getActivity().getDebrief().getInstalledAsset().getNewPageCount(),
				contract.getActivity().getDebrief().getInstalledAsset().getAssetTag(),
				booleanToString(contract.getActivity().getDebrief().getInstalledAsset().getNetworkConnectedFlag()),
				contract.getActivity().getDebrief().getInstalledAsset().getIpAddress(),
				contract.getActivity().getDebrief().getInstalledAsset().getMacAddress(),
				contract.getActivity().getDebrief().getInstalledAsset().getModelNumber(),
				contract.getActivity().getDebrief().getInstalledAsset().getSerialNumber());
		
		DebriefDetails debriefDetails = new DebriefDetails(
				dateToString(contract.getActivity().getDebrief().getServiceStartDate()),
				dateToString(contract.getActivity().getDebrief().getServiceEndDate()),
				contract.getActivity().getDebrief().getTravelDistance(),
				contract.getActivity().getDebrief().getTravelUnitOfMeasure().getValue(),
				contract.getActivity().getDebrief().getTravelDuration(),
				null,
				contract.getActivity().getDebrief().getActualFailureCode().getValue(),
				contract.getActivity().getDebrief().getProblemDescription(),
				contract.getActivity().getDebrief().getResolutionCode().getValue(),
				//Changed for CI Defect # 13318
				contract.getActivity().getDebrief().getRepairDescription(),
				contract.getActivity().getDebrief().getDebriefActionStatus(),
				booleanToString(contract.getActivity().getDebrief().isGenuineLexmarkSuppliesUsedFlag()),
				contract.getActivity().getDebrief().getSupplyManufacturer(),
				originalDeviceInformation,
				replacementDeviceInformation);
		
		SiebelRecommendedPartsList[] recommendedPartsList = null;
			if(contract.getActivity().getDebrief().getPartDebriefList() != null){
				   
//				ShipToAddress2 shipToAddress = new ShipToAddress2(
//						   "",
//						   "",
//						   "",
//						   "",
//						   "",
//						   "",
//						   "",
//						   "",
//						   "",
//						   "",
//				           "",
//				           "",
//				           "");	
				SiebelAddress shipToAddress = new SiebelAddress();
				//added to fulfill AddressLine1 criterion in webmethods
				if(contract.getActivity().getShipToAddress() != null && contract.getActivity().getShipToAddress().getAddressLine1() != null && contract.getActivity().getShipToAddress().getAddressLine1() !=" " 
						&& contract.getActivity().getShipToAddress().getAddressLine1() != ""){
				shipToAddress.setAddressLine1(contract.getActivity().getShipToAddress().getAddressLine1());	
				}
				else
				{
				shipToAddress.setAddressLine1("");	
				}
				if(contract.getActivity().getShipToAddress() != null && contract.getActivity().getShipToAddress().getCity() != null && contract.getActivity().getShipToAddress().getCity() !=" " 
						&& contract.getActivity().getShipToAddress().getCity() != ""){
				shipToAddress.setCity(contract.getActivity().getShipToAddress().getCity());
				}
				else
				{
					shipToAddress.setCity("");
				}
				//ends here
			   recommendedPartsList = new SiebelRecommendedPartsList[contract.getActivity().getDebrief().getPartDebriefList().size()];
				int i = 0;	   
			   for (PartLineItem lineItem : contract.getActivity().getDebrief().getPartDebriefList()) { 	
				   PartInformation partInfromation = new PartInformation();
				   partInfromation.setPartNumber(lineItem.getPartNumber());
				   partInfromation.setPartId(lineItem.getPartId());
				   partInfromation.setPartName(lineItem.getPartName());
				   
				   String carrier = "";
				   String trackingNumber = "";
				   if(lineItem.getCarrier() != null && null != lineItem.getCarrier().getValue() && !"".equals(lineItem.getCarrier().getValue())){
					   carrier = lineItem.getCarrier().getValue();
				   }
				   else{
					   carrier = "";
				   }
				   if(null != lineItem.getTrackingNumber() && !"".equals(lineItem.getTrackingNumber())){
					   trackingNumber = lineItem.getTrackingNumber();
				   }
				   else{
					   trackingNumber = "";
				   }
				   logger.info("***Carrier is : " + carrier); //Added by sankha for testing
				   logger.info("***Tracking number is : " + trackingNumber); //Added by sankha for testing
//				   String carrier = (lineItem.isReturnRequiredFlag()?lineItem.getCarrier().getValue():"");	
//				   String trackingNumber = (lineItem.isReturnRequiredFlag()?lineItem.getTrackingNumber():"");
				   String lineStatus = lineItem.getPartDisposition().getValue();
				   String errorCode1 = (lineStatus.equals("Used")?lineItem.getErrorCode1().getValue():"");
		           String errorCode2 = (lineStatus.equals("Used")?lineItem.getErrorCode2().getValue():"");
		           String lineItemId = (lineItem.getPartLineItemId()!= null?lineItem.getPartLineItemId():"");
		           String partSource = (lineItem.getPartSource()!= null?lineItem.getPartSource().getValue():"");
		           
		           SiebelRecommendedPartsList recommendedPart = null;
		           
		           recommendedPart = new SiebelRecommendedPartsList();
		           recommendedPart.setLineItemId(lineItemId);
				   recommendedPart.setOrderDate(dateToString(lineItem.getPartOrderedDate()));
				   recommendedPart.setQuantity(Integer.toString(lineItem.getQuantity()));
				   recommendedPart.setPartInformation(partInfromation);
				   recommendedPart.setReturnRequiredFlag(booleanToString(lineItem.isReturnRequiredFlag()));
				   recommendedPart.setShipToAddress(shipToAddress);
				   recommendedPart.setStatus(lineStatus);
				   recommendedPart.setRecommendedPartUpdatedFlag(booleanToString(lineItem.isPartLineItemUpdateFlag()));
				   recommendedPart.setErrorCode1(errorCode1);
				   recommendedPart.setErrorCode2(errorCode2);
				   //added by sbag defect #13023
				   recommendedPart.setPartSource(partSource); 
				   recommendedPart.setReturnRequiredFlag(booleanToString(lineItem.isReturnRequiredFlag()));
				   recommendedPart.setCarrier(carrier);
				   recommendedPart.setReturnTrackingNumber(trackingNumber);
				   //ends here defect #13023
			        recommendedPartsList[i] = recommendedPart;
			        i++;
			   }		   
		   }
		

			SiebelActivityNotes[] activityNotes = null;
		   if (contract.getActivity().getActivityNoteList() != null){
			   activityNotes = new SiebelActivityNotes[contract.getActivity().getActivityNoteList().size()];
				int n = 0;	   
			   for (ActivityNote activityNoteLineItem : contract.getActivity().getActivityNoteList()) { 	

				   NoteAuthor noteAuthor = new NoteAuthor();
				   noteAuthor.setContactId(activityNoteLineItem.getNoteAuthor().getContactId());
				   noteAuthor.setFirstName(activityNoteLineItem.getNoteAuthor().getFirstName());
				   noteAuthor.setLastName(activityNoteLineItem.getNoteAuthor().getLastName());

				   SiebelActivityNotes activityNote = new SiebelActivityNotes();
				   activityNote.setNoteId(activityNoteLineItem.getNoteId());
				   activityNote.setNoteDate(dateToString(activityNoteLineItem.getNoteDate()));
				   activityNote.setNoteAuthor(noteAuthor);
				   activityNote.setNoteDetails(activityNoteLineItem.getNoteDetails());
				   activityNote.setNoteUpdateFlag(booleanToString(activityNoteLineItem.isActivityUpdateFlag()));
				
				   activityNotes[n] = activityNote;
			        n++;
			   }   
		   }
		   SiebelPaymentRequestList[] paymentRequestList = null;
		   if (contract.getActivity().getAdditionalPaymentRequestList() != null){
			   paymentRequestList = new SiebelPaymentRequestList[contract.getActivity().getAdditionalPaymentRequestList().size()];
				int p = 0;	   
			   for (AdditionalPaymentRequest paymentLineItem : contract.getActivity().getAdditionalPaymentRequestList()) { 	

				   
				   SiebelPaymentRequestList paymentRequest = new SiebelPaymentRequestList();
				   paymentRequest.setPaymentRequestId(paymentLineItem.getPaymentRequestId());
				   paymentRequest.setPaymentType(paymentLineItem.getPaymentType().getValue());
				   paymentRequest.setQuantity(Integer.toString(paymentLineItem.getQuantity()));
				   paymentRequest.setUnitPrice(Double.toString(paymentLineItem.getUnitPrice()));
				   paymentRequest.setTotalAmount(Double.toString(paymentLineItem.getTotalAmount()));
				   paymentRequest.setCurrency(paymentLineItem.getPaymentCurrency());
				   paymentRequest.setDescription(paymentLineItem.getDescription());
				   paymentRequest.setPaymentRequestUpdateFlag(booleanToString(paymentLineItem.isPaymentRequestUpdateFlag()));
				
				   paymentRequestList[p] = paymentRequest;
			        p++;
			   }		   
		   }

		   ServiceRequestData4 requestData = new ServiceRequestData4();
			requestData.setServiceRequestNumber(contract.getActivity().getServiceRequest().getServiceRequestNumber());
			requestData.setServiceRequestId(contract.getActivity().getServiceRequest().getId());
			requestData.setActivityId(contract.getActivity().getActivityId());
			requestData.setServiceRequestDate(dateToString(contract.getActivity().getServiceRequest().getServiceRequestDate()));
			requestData.setServiceRequestStatusOverall(contract.getActivity().getActivityStatus().getValue());
			requestData.setServiceRequestStatusDetail(contract.getActivity().getActivitySubStatus().getValue());
			requestData.setServiceRequestSource(contract.getActivity().getServiceRequest().getServiceRequestor());
			requestData.setRelatedSourceReferenceNumber(contract.getActivity().getDebrief().getServiceProviderReferenceNumber());
			requestData.setRequester(requester);
			requestData.setPrimaryContact(primaryContact);
			requestData.setTechnician(technician);
			requestData.setDebriefDetails(debriefDetails);
			requestData.setRecommendedPartsList(recommendedPartsList);
			requestData.setActivityNotes(activityNotes);
			requestData.setPaymentRequestList(paymentRequestList);
			
		DebriefServiceRequestActivityInput debriefServiceRequestActivityInput = new DebriefServiceRequestActivityInput();
		DebriefServiceRequestActivityWSInput inputWS = new DebriefServiceRequestActivityWSInput(documentMetaData, requestData);
		debriefServiceRequestActivityInput.setDebriefServiceRequestActivityWSInput(inputWS);
		String Status = port.debriefServiceRequestActivity("$null",synchOrAsynch, debriefServiceRequestActivityInput);
		
		boolean successFlag = false;
		if(Status.equalsIgnoreCase("success"))
			   successFlag = true;
		
		ActivityDebriefSubmitResult result = new ActivityDebriefSubmitResult();
		result.setSuccess(successFlag);
		return result;
			
	}
	
	@Override
	public CreateChildSRResult createChildSR(ChildSRContract contract)
			throws Exception {
		logger.debug("************ Inside Create Child SR - REAL ************");
		boolean successFlag = false;		
		ServiceRequestWS locator = new ServiceRequestWSLocator();
		logger.debug("WSDL Endpoint Address is: " +  address );		
		ServiceRequestWS_PortType port = locator.getServiceRequest_serviceRequestWS_Port(new URL(getAddress()));
		logger.debug("the port address is--->>>"+port);
		org.apache.axis.client.Stub stub = ((org.apache.axis.client.Stub) port);
		stub.setUsername(getUserName());
		stub.setPassword(getPassword());
		WebServiceDocumentMetaData documentMetaData = new WebServiceDocumentMetaData(senderId, senderName, null, null, receiverId, null);
		
		
		    logger.debug("shipToAddress.getAddressId()"+contract.getActivity().getShipToAddress().getAddressId());
			logger.debug("shipToAddress.getAddressLine1()"+contract.getActivity().getShipToAddress().getAddressLine1());
			logger.debug("shipToAddress.getAddressLine2()"+contract.getActivity().getShipToAddress().getAddressLine2());
			logger.debug("shipToAddress.getOfficeNumber()"+contract.getActivity().getShipToAddress().getOfficeNumber());
			logger.debug("shipToAddress.getCity()"+contract.getActivity().getShipToAddress().getCity());
			logger.debug("shipToAddress.getStateCode()"+contract.getActivity().getShipToAddress().getStateCode());
			logger.debug("shipToAddress.getProvince()"+contract.getActivity().getShipToAddress().getProvince());
			logger.debug("shipToAddress.getRegion()"+contract.getActivity().getShipToAddress().getRegion());
			logger.debug("shipToAddress.getCountry()"+contract.getActivity().getShipToAddress().getCountry());
			logger.debug("shipToAddress.getCountryCode()"+contract.getActivity().getShipToAddress().getCountryISOCode());
			logger.debug("shipToAddress.getPostalCode()"+contract.getActivity().getShipToAddress().getPostalCode());
			logger.debug("shipToAddress.getPhysicalLocation1()"+contract.getActivity().getShipToAddress().getPhysicalLocation1());
			logger.debug("shipToAddress.getPhysicalLocation2()"+contract.getActivity().getShipToAddress().getPhysicalLocation2());
			logger.debug("shipToAddress.getPhysicalLocation3()"+contract.getActivity().getShipToAddress().getPhysicalLocation3());
			logger.debug("shipToAddress.getStateFullName()"+contract.getActivity().getShipToAddress().getStateFullName());
			logger.debug("shipToAddress.getLatitude()"+contract.getActivity().getShipToAddress().getLatitude());
			logger.debug("shipToAddress.getLongitude()"+contract.getActivity().getShipToAddress().getLongitude());
			logger.debug("shipToAddress.getAddressCleansedFlag()"+contract.getActivity().getShipToAddress().getIsAddressCleansed());
			logger.debug("shipToAddress.getAddressMessage()"+contract.getActivity().getShipToAddress().getErrorMsgForCleansing());
			logger.debug("shipToAddress.getNewAddressFlag()"+contract.getActivity().getShipToAddress().getNewAddressFlag());
			
		   SiebelRecommendedPartsList [] recommendedPartsList = null;
		  

			   SiebelAddress shipToAddress = null;
			   if (contract.getActivity().getShipToAddress()== null || 
					   contract.getActivity().getShipToAddress().getAddressLine1() == null  || 
					   contract.getActivity().getShipToAddress().getAddressLine1().equals("")){

				   shipToAddress = new SiebelAddress();
			   } else {
				   String shipToStateProvince = null;
					if(contract.getActivity().getShipToAddress().getStateProvince() != null && 
							!contract.getActivity().getShipToAddress().getStateProvince().equals("") && 
							!contract.getActivity().getShipToAddress().getStateProvince().equals("null")){
						shipToStateProvince = contract.getActivity().getShipToAddress().getStateProvince();
					}else if(contract.getActivity().getShipToAddress().getState() != null && 
							!contract.getActivity().getShipToAddress().getState().equals("") && 
							!contract.getActivity().getShipToAddress().getState().equals("null")){
						shipToStateProvince = contract.getActivity().getShipToAddress().getState();
					}else{
						shipToStateProvince = contract.getActivity().getShipToAddress().getProvince();
					}
	   
					shipToAddress = new SiebelAddress();
					shipToAddress.setAddressId("");
					if(contract.getActivity().getShipToAddress().getAddressId()!=null){
					shipToAddress.setAddressId(contract.getActivity().getShipToAddress().getAddressId().replaceAll(",", ""));
					}
					
					shipToAddress.setAddressLine1(contract.getActivity().getShipToAddress().getAddressLine1().replaceAll(",", ""));
					shipToAddress.setAddressLine2(contract.getActivity().getShipToAddress().getAddressLine2().replaceAll(",", ""));
					shipToAddress.setHouseNumber(contract.getActivity().getShipToAddress().getOfficeNumber().replaceAll(",", ""));
					shipToAddress.setCity(contract.getActivity().getShipToAddress().getCity().replaceAll(",", ""));
					shipToAddress.setStateCode(contract.getActivity().getShipToAddress().getStateCode().replaceAll(",", ""));
					shipToAddress.setProvince(contract.getActivity().getShipToAddress().getProvince().replaceAll(",", ""));
					shipToAddress.setDistrict(contract.getActivity().getShipToAddress().getDistrict().replaceAll(",", ""));
					shipToAddress.setCountry(contract.getActivity().getShipToAddress().getCountry().replaceAll(",", ""));
					shipToAddress.setRegion(contract.getActivity().getShipToAddress().getRegion().replaceAll(",", ""));
					shipToAddress.setCountry(contract.getActivity().getShipToAddress().getCountry().replaceAll(",", ""));
					shipToAddress.setCountryCode(contract.getActivity().getShipToAddress().getCountryISOCode().replaceAll(",", ""));
					shipToAddress.setPostalCode(contract.getActivity().getShipToAddress().getPostalCode().replaceAll(",", ""));
					shipToAddress.setPhysicalLocation1(contract.getActivity().getShipToAddress().getPhysicalLocation1());
					shipToAddress.setPhysicalLocation2(contract.getActivity().getShipToAddress().getPhysicalLocation2());
					shipToAddress.setPhysicalLocation3(contract.getActivity().getShipToAddress().getPhysicalLocation3());
					shipToAddress.setStateFullName(contract.getActivity().getShipToAddress().getStateFullName());
					shipToAddress.setLatitude(contract.getActivity().getShipToAddress().getLatitude());
					shipToAddress.setLongitude(contract.getActivity().getShipToAddress().getLongitude());
					if(contract.getActivity().getShipToAddress().getIsAddressCleansed()){
					shipToAddress.setAddressCleansedFlag("Y");
					}
					else
					{
					shipToAddress.setAddressCleansedFlag("N");	
					}
					//shipToAddress.setAddressCleansedFlag(booleanToString(contract.getActivity().getShipToAddress().getIsAddressCleansed()));
					shipToAddress.setAddressMessage(contract.getActivity().getShipToAddress().getErrorMsgForCleansing());
					shipToAddress.setNewAddressFlag(contract.getActivity().getShipToAddress().getNewAddressFlag());
			   }
			   
			   // get the recommended parts count which has quantity greater than zero
			   int nonZeroQtyParts = 0;
			   if(null !=contract.getActivity().getRecommendedPartList() && contract.getActivity().getRecommendedPartList().size()>0){
			   for (PartLineItem getLineItemCount : contract.getActivity().getRecommendedPartList()){
				   int qty = getLineItemCount.getQuantity();
				   if ( qty >0)
					   nonZeroQtyParts++;
			   }
			   	   
			   recommendedPartsList = new SiebelRecommendedPartsList[nonZeroQtyParts];
				int i = 0;	   
			   for (PartLineItem lineItem : contract.getActivity().getRecommendedPartList()) { 	
				   PartInformation partInformation = new PartInformation();
				   partInformation.setPartNumber( lineItem.getPartNumber());
				   partInformation.setPartId(lineItem.getPartId());
				   partInformation.setPartName(lineItem.getPartName());
				   				   
				   int Quantity = lineItem.getQuantity();
				   if (Quantity != 0){ 
					   SiebelRecommendedPartsList recommendedPart = null;

					   recommendedPart = new SiebelRecommendedPartsList();
					   
					   recommendedPart.setOrderDate(dateToString(lineItem.getPartOrderedDate()));
					   recommendedPart.setQuantity(Integer.toString(lineItem.getQuantity()));
					   recommendedPart.setPartInformation(partInformation);
					   recommendedPart.setReturnRequiredFlag(booleanToString(lineItem.isReturnRequiredFlag()));
					  // recommendedPart.setShipToAddress(shipToAddress);
					   recommendedPart.setRecommendedPartUpdatedFlag(booleanToString(lineItem.isPartLineItemUpdateFlag()));
					
						recommendedPartsList[i] = recommendedPart;
				        i++;	
					}				  
			        
			   }
			   }
		
		   ServiceRequestData3 requestData3 = new ServiceRequestData3();
		   requestData3.setServiceRequestNumber(contract.getServiceRequest().getServiceRequestNumber());
		   requestData3.setServiceRequestDate(dateToString(contract.getServiceRequest().getServiceRequestDate()));
		   requestData3.setServiceRequestStatus(contract.getServiceRequest().getServiceRequestStatus());
		  requestData3.setServiceRequestSource(getClaimSource());
	
		  if(nonZeroQtyParts>0){
		   requestData3.setRecommendedPartsList(recommendedPartsList);
		   requestData3.setServiceAddress(shipToAddress);
		  }
		   requestData3.setFirstCallCompletionFlag((contract.getFccCodeListLOV()));
		   requestData3.setProblemDescription(XMLEncodeUtil.escapeXML(contract.getServiceRequest().getProblemDescription()));
		   requestData3.setRequestedService((null !=contract.getServiceRequest().getOtherRequestedService())?(contract.getServiceRequest().getOtherRequestedService()):"");		   
		   SelectedServiceDetails2[] selectedServiceDetails = new SelectedServiceDetails2[0];
		   SelectedServiceDetails2 selectedServiceDetail = new SelectedServiceDetails2();

		   EntitlementServiceDetails5 entitlementServiceDetails2 = new EntitlementServiceDetails5();
		   entitlementServiceDetails2.setPrimaryFlag("");
	
		   entitlementServiceDetails2.setServiceDetailId("");
		   entitlementServiceDetails2.setServiceDetailsDescription("");
		   Entitlement5 entitlement2 = new Entitlement5();
		   entitlement2.setEntitlementId("");
		   entitlement2.setEntitlementName("");
		                                                                  			
		   entitlement2.setEntitlementId("");
		   entitlement2.setEntitlementName("");
		   entitlement2.setEntitlementServiceDetails(entitlementServiceDetails2);	                                                                  			
		   selectedServiceDetail.setEntitlement(entitlement2);
		                                                                  		
		   requestData3.setServiceRequestStatus(contract.getChildSRStatus());                                                      		                                                            		
		   requestData3.setSelectedServiceDetails(selectedServiceDetails);                                                                		
		   ServiceRequestInput serviceRequest = new ServiceRequestInput();
		  ServiceRequestWSInput inputSR = new ServiceRequestWSInput(documentMetaData,requestData3);		  
		  serviceRequest.setServiceRequestWSInput(inputSR);
		  try{
		  port.updateServiceRequest("$null", serviceRequest);		  
		  successFlag = true;
		  }
		  catch(Exception e)
		  {
			  logger.debug("Exception in updating ServiceRequest");			  
		  }
		  finally
		  {
			CreateChildSRResult result = new CreateChildSRResult();
			result.setSuccess(successFlag);
			return result;
		  }						
	}

	private String booleanToString(Boolean b) {
		if (b == null) return "false";
		return b ? "true" : "false";
	}
	

	 	final String nullToString(String s) {
  			if (s == null)
  				{return "";}
  			return s;
  		}
	
	private String dateToString(Date d) {
		if (d == null) return "";
		Format formatter;
		formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		String date = formatter.format(d);
		return date;
	}
}
