package com.lexmark.webservice.impl.real;

import java.net.URL;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import com.lexmark.contract.ClaimDebriefSubmitContract;
import com.lexmark.contract.ClaimUpdateContract;
import com.lexmark.contract.WarrantyClaimCreateContract;
import com.lexmark.domain.ActivityNote;
import com.lexmark.domain.AdditionalPaymentRequest;
import com.lexmark.domain.PartLineItem;
import com.lexmark.result.ClaimDebriefSubmitResult;
import com.lexmark.result.ClaimUpdateResult;
import com.lexmark.result.WarrantyClaimCreateResult;
import com.lexmark.util.XMLEncodeUtil;
import com.lexmark.webServices.warrantyClaimsServiceRequest.client1.Account;
import com.lexmark.webServices.warrantyClaimsServiceRequest.client1.ClaimsServiceRequestData;
import com.lexmark.webServices.warrantyClaimsServiceRequest.client1.ClaimsServiceRequestData2;
import com.lexmark.webServices.warrantyClaimsServiceRequest.client1.ClaimsServiceRequestData3;
import com.lexmark.webServices.warrantyClaimsServiceRequest.client1.CreateClaimsServiceRequestInput;
import com.lexmark.webServices.warrantyClaimsServiceRequest.client1.CreateClaimsServiceRequestWSInput;
import com.lexmark.webServices.warrantyClaimsServiceRequest.client1.DebriefClaimsServiceRequestInput;
import com.lexmark.webServices.warrantyClaimsServiceRequest.client1.DebriefClaimsServiceRequestWSInput;
import com.lexmark.webServices.warrantyClaimsServiceRequest.client1.NoteAuthor;
import com.lexmark.webServices.warrantyClaimsServiceRequest.client1.PartInformation;
import com.lexmark.webServices.warrantyClaimsServiceRequest.client1.PartInfromation;
import com.lexmark.webServices.warrantyClaimsServiceRequest.client1.SiebelActivityNotes;
import com.lexmark.webServices.warrantyClaimsServiceRequest.client1.SiebelAddress;
import com.lexmark.webServices.warrantyClaimsServiceRequest.client1.SiebelAssetInformation;
import com.lexmark.webServices.warrantyClaimsServiceRequest.client1.SiebelContact;
import com.lexmark.webServices.warrantyClaimsServiceRequest.client1.SiebelDebriefInformation;
import com.lexmark.webServices.warrantyClaimsServiceRequest.client1.SiebelMDMCustomerInformation;
import com.lexmark.webServices.warrantyClaimsServiceRequest.client1.SiebelPaymentRequestList;
import com.lexmark.webServices.warrantyClaimsServiceRequest.client1.SiebelRecommendedPartsList;
import com.lexmark.webServices.warrantyClaimsServiceRequest.client1.SiebelReturnPartsList;
import com.lexmark.webServices.warrantyClaimsServiceRequest.client1.SiebelServiceProviderInformation;
import com.lexmark.webServices.warrantyClaimsServiceRequest.client1.SiebelServiceRequestDetails;
import com.lexmark.webServices.warrantyClaimsServiceRequest.client1.UpdateClaimsServiceRequestInput;
import com.lexmark.webServices.warrantyClaimsServiceRequest.client1.UpdateClaimsServiceRequestWSInput;
import com.lexmark.webServices.warrantyClaimsServiceRequest.client1.WarrantyClaimsServiceRequestWS;
import com.lexmark.webServices.warrantyClaimsServiceRequest.client1.WarrantyClaimsServiceRequestWSLocator;
import com.lexmark.webServices.warrantyClaimsServiceRequest.client1.WarrantyClaimsServiceRequestWS_PortType;
import com.lexmark.webServices.warrantyClaimsServiceRequest.client1.WebServiceDocumentMetaData;
import com.lexmark.webservice.api.ClaimService;
import javax.xml.rpc.holders.StringHolder;

public class ClaimServiceImpl implements ClaimService {
	private static Logger logger = LogManager.getLogger(ClaimServiceImpl.class);
	
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
	public WarrantyClaimCreateResult createWarrantyClaim(
			WarrantyClaimCreateContract contract) throws Exception {
		logger.debug("************ CREATE WARRANTY CLAIM - REAL ************");
		logger.debug("address: " + address);
		logger.debug("senderId: " + senderId);
		logger.debug("senderName: " + senderName);
		logger.debug("receiverId: " + receiverId);
		logger.debug("claimSource: " + claimSource);
		logger.debug("synchOrAsynch: " + synchOrAsynch);
		
		WarrantyClaimsServiceRequestWS locator = new WarrantyClaimsServiceRequestWSLocator();
		logger.debug("WSDL Endpoint Address is: " +  address );
		WarrantyClaimsServiceRequestWS_PortType port = locator.getServiceRequest_warrantyClaimsServiceRequestWS_Port(new URL(getAddress()));

		org.apache.axis.client.Stub stub = ((org.apache.axis.client.Stub) port);
		stub.setUsername(getUserName());
		stub.setPassword(getPassword());
		
		WebServiceDocumentMetaData documentMetaData = new WebServiceDocumentMetaData();
		documentMetaData.setSenderId(senderId);
		documentMetaData.setSenderName(senderName);
		documentMetaData.setReceiverId(receiverId);
		
		SiebelAddress accountAddress = null;
		SiebelAddress installedAtAddress = null;
		logger.debug("		contract.getCreateNewCustomerAddressFlag() :================ " +  		contract.getCreateNewCustomerAddressFlag() );
		if(contract.getCreateNewCustomerAddressFlag().equalsIgnoreCase("true")){  //changed for CI-5 2098
			logger.debug("Inside contract.getCreateNewCustomerAddressFlag() IF ================: " );

			
			accountAddress = new SiebelAddress();
			accountAddress.setAddressId(contract.getActivity().getShipToAddress().getAddressId());
			logger.debug(contract.getActivity().getShipToAddress().getAddressId());
			accountAddress.setAddressLine1(contract.getActivity().getNewCustomerAddressCombined());//Added for CI Defect #7973
			accountAddress.setCity(" ");//Added for CI Defect #7973
			logger.debug("accountAddress.getAddressLine1() ================: "+accountAddress.getAddressLine1());
			
			}else{
				logger.debug("Inside contract.getCreateNewCustomerAddressFlag() ELSE ================: ");
			String installStateProvince = null;
			if(contract.getActivity().getServiceRequest().getAsset().getInstallAddress().getStateProvince() != null && 
					!contract.getActivity().getServiceRequest().getAsset().getInstallAddress().getStateProvince().equals("") && 
					!contract.getActivity().getServiceRequest().getAsset().getInstallAddress().getStateProvince().equals("null")){
				
				installStateProvince = contract.getActivity().getServiceRequest().getAsset().getInstallAddress().getStateProvince();
				logger.debug("Inside contract.getCreateNewCustomerAddressFlag() ELSE 111111 ================: "+installStateProvince);
			}else if(contract.getActivity().getServiceRequest().getAsset().getInstallAddress().getState() != null && 
					!contract.getActivity().getServiceRequest().getAsset().getInstallAddress().getState().equals("") && 
					!contract.getActivity().getServiceRequest().getAsset().getInstallAddress().getState().equals("null")){
				
				installStateProvince = contract.getActivity().getServiceRequest().getAsset().getInstallAddress().getState();
				logger.debug("Inside contract.getCreateNewCustomerAddressFlag() ELSE 222222 ================: "+installStateProvince);
			}else{
				
				installStateProvince = contract.getActivity().getServiceRequest().getAsset().getInstallAddress().getProvince();
				logger.debug("Inside contract.getCreateNewCustomerAddressFlag() ELSE 3333333333333================: "+installStateProvince);
			}
						//new fields added for CI BRD 13-10-08 STARTS
						installedAtAddress = new SiebelAddress();
						if(contract.getActivity().getServiceRequest().getAsset().getInstallAddress().getAddressId()=="No Match Row Id"||contract.getActivity().getServiceRequest().getAsset().getInstallAddress().getAddressId().equalsIgnoreCase("No Match Row Id"))
						{
							logger.debug("heree address id is "+contract.getActivity().getServiceRequest().getAsset().getInstallAddress().getAddressId());
						installedAtAddress.setAddressId("");
						}else
						{
						installedAtAddress.setAddressId(contract.getActivity().getServiceRequest().getAsset().getInstallAddress().getAddressId());
						}
						installedAtAddress.setAddressName(contract.getActivity().getServiceRequest().getAsset().getInstallAddress().getAddressName());
						installedAtAddress.setAddressLine1(contract.getActivity().getServiceRequest().getAsset().getInstallAddress().getAddressLine1());
						installedAtAddress.setAddressLine2(contract.getActivity().getServiceRequest().getAsset().getInstallAddress().getAddressLine2());
						installedAtAddress.setFloorId(contract.getActivity().getServiceRequest().getAsset().getInstallAddress().getOfficeNumber());
						installedAtAddress.setCity(contract.getActivity().getServiceRequest().getAsset().getInstallAddress().getCity());
						installedAtAddress.setStateCode(contract.getActivity().getServiceRequest().getAsset().getInstallAddress().getStateCode());
						installedAtAddress.setProvince(installStateProvince);
						installedAtAddress.setDistrict(contract.getActivity().getServiceRequest().getAsset().getInstallAddress().getDistrict());
						installedAtAddress.setCounty(contract.getActivity().getServiceRequest().getAsset().getInstallAddress().getCounty());
						installedAtAddress.setRegion(contract.getActivity().getServiceRequest().getAsset().getInstallAddress().getRegion());
						installedAtAddress.setCountry(contract.getActivity().getServiceRequest().getAsset().getInstallAddress().getCountry());
						installedAtAddress.setCountryCode(contract.getActivity().getServiceRequest().getAsset().getInstallAddress().getCountryISOCode());
						installedAtAddress.setPostalCode(contract.getActivity().getServiceRequest().getAsset().getInstallAddress().getPostalCode());
						installedAtAddress.setStateFullName(contract.getActivity().getServiceRequest().getAsset().getInstallAddress().getStateFullName());
						installedAtAddress.setLatitude(contract.getActivity().getServiceRequest().getAsset().getInstallAddress().getLatitude());
						installedAtAddress.setLongitude(contract.getActivity().getServiceRequest().getAsset().getInstallAddress().getLongitude());
						
						installedAtAddress.setPhysicalLocation1(contract.getActivity().getServiceRequest().getAsset().getInstallAddress().getPhysicalLocation1());
						installedAtAddress.setPhysicalLocation2(contract.getActivity().getServiceRequest().getAsset().getInstallAddress().getPhysicalLocation2());
						installedAtAddress.setPhysicalLocation3(contract.getActivity().getServiceRequest().getAsset().getInstallAddress().getPhysicalLocation3());
						
						installedAtAddress.setAddressMessage(contract.getActivity().getServiceRequest().getAsset().getInstallAddress().getErrorMsgForCleansing());
						
						if(contract.getActivity().getServiceRequest().getAsset().getInstallAddress().getIsAddressCleansed()){
							installedAtAddress.setAddressCleansedFlag("Y");
						}else{
							installedAtAddress.setAddressCleansedFlag("N");
						}
						installedAtAddress.setNewAddressFlag(contract.getActivity().getServiceRequest().getAsset().getInstallAddress().getNewAddressFlag());
						//new fields added for CI BRD 13-10-08 ENDS
			}
		
		
		Account account = new Account();
		account.setAccountId(contract.getActivity().getCustomerAccount().getAccountId());
		account.setAccountName(contract.getActivity().getCustomerAccount().getAccountName());
		account.setAccountAddress(accountAddress);
		
		SiebelAssetInformation assetInformation = new SiebelAssetInformation();
		assetInformation.setAssetId(contract.getActivity().getServiceRequest().getAsset().getAssetId());
		assetInformation.setSerialNumber(contract.getActivity().getServiceRequest().getAsset().getSerialNumber());
		assetInformation.setAssetTag(contract.getActivity().getServiceRequest().getAsset().getAssetTag());
		assetInformation.setProductModel(contract.getActivity().getServiceRequest().getAsset().getModelNumber().toUpperCase());
		assetInformation.setProductNumber(contract.getActivity().getServiceRequest().getAsset().getProductTLI().toUpperCase());
		assetInformation.setProductLine(contract.getActivity().getServiceRequest().getAsset().getProductLine());
		assetInformation.setInstalledAtAddress(installedAtAddress);
		assetInformation.setAccount(account);
		assetInformation.setNotMyPrinterFlag(booleanToString(contract.getActivity().getServiceRequest().getAsset().isNotMyPrinter()));

		SiebelContact primaryContact = new SiebelContact();
		primaryContact.setFirstName(contract.getActivity().getServiceRequest().getPrimaryContact().getFirstName());
		primaryContact.setLastName(contract.getActivity().getServiceRequest().getPrimaryContact().getLastName());
		primaryContact.setWorkPhone(contract.getActivity().getServiceRequest().getPrimaryContact().getWorkPhone());
		primaryContact.setEmailAddress(contract.getActivity().getServiceRequest().getPrimaryContact().getEmailAddress());

		
		SiebelServiceRequestDetails claimDetails = new SiebelServiceRequestDetails();
		claimDetails.setFailureCode(contract.getActivity().getActualFailureCode().getValue());
		claimDetails.setProblemDescription(contract.getActivity().getServiceRequest().getProblemDescription());
		claimDetails.setRequestLexmarkReviewFlag(booleanToString(contract.getActivity().isRequestLexmarkReviewFlag()));
		claimDetails.setReviewComments(contract.getActivity().getReviewComments());

		
		SiebelServiceProviderInformation serviceProviderInformation = new SiebelServiceProviderInformation();
		serviceProviderInformation.setServiceProviderAccountId(contract.getActivity().getPartnerAccount().getAccountId());
		serviceProviderInformation.setServiceProviderName(contract.getActivity().getPartnerAccount().getAccountName());
		serviceProviderInformation.setServiceProviderOrganization(contract.getActivity().getPartnerAccount().getOrganizationID());
		serviceProviderInformation.setServiceProviderReferenceNumber(contract.getActivity().getServiceProviderReferenceNumber());

		SiebelRecommendedPartsList[] recommendedPartsList = null;
		
	   if(contract.getActivity().getOrderPartList() != null && contract.getActivity().getOrderPartList().size() > 0){
		   	
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
			
			SiebelAddress shipToAddress = new SiebelAddress();
			shipToAddress.setAddressId(contract.getActivity().getShipToAddress().getAddressId());
			shipToAddress.setAddressName(contract.getActivity().getShipToAddress().getAddressName().replaceAll(",", ""));
			shipToAddress.setAddressLine1(contract.getActivity().getShipToAddress().getAddressLine1().replaceAll(",", ""));
			shipToAddress.setAddressLine2(contract.getActivity().getShipToAddress().getAddressLine2().replaceAll(",", ""));
			if((contract.getActivity().getShipToAddress().getOfficeNumber() != null) || (contract.getActivity().getShipToAddress().getOfficeNumber()) != ""){
			shipToAddress.setFloorId((contract.getActivity().getShipToAddress().getOfficeNumber().replaceAll(",", "")));
			}
			shipToAddress.setCity(contract.getActivity().getShipToAddress().getCity().replaceAll(",", ""));
			shipToAddress.setProvince(shipToStateProvince.replaceAll(",", ""));
			shipToAddress.setPostalCode(contract.getActivity().getShipToAddress().getPostalCode().replaceAll(",", ""));
			shipToAddress.setCountry(contract.getActivity().getShipToAddress().getCountry().replaceAll(",", ""));
			shipToAddress.setNewAddressFlag(contract.getActivity().getShipToAddress().getNewAddressFlag());
			if(contract.getActivity().getShipToAddress().getIsAddressCleansed()){
				shipToAddress.setAddressCleansedFlag("Y");
			}else{
				shipToAddress.setAddressCleansedFlag("N");
			}			
			shipToAddress.setRegion(contract.getActivity().getShipToAddress().getRegion().replaceAll(",", ""));
			

		   recommendedPartsList = new SiebelRecommendedPartsList[contract.getActivity().getOrderPartList().size()];
			int i = 0;	
		   for (PartLineItem lineItem : contract.getActivity().getOrderPartList()) { 	

			   PartInformation partInformation = new PartInformation();
			   partInformation.setPartNumber(lineItem.getPartNumber());
			   partInformation.setPartId(lineItem.getPartId());
			   partInformation.setPartName(lineItem.getPartName());
			   
			   SiebelRecommendedPartsList recommendedPart = null;
			   String errorCode1=null;
			   String errorCode2=null;
			   //CI-6 Start Partha
			   String expedite = contract.getActivity().getExpedite();
			   //CI-6 End Partha
			   // CI-6 Changes for Expedite Flag for Claim Create
			   if(expedite == null){
				   expedite = "N"; 
			   }
			   String carrier = (lineItem.isReturnRequiredFlag()?lineItem.getCarrier().getValue():"");	
			   String trackingNumber = (lineItem.isReturnRequiredFlag()?lineItem.getTrackingNumber():"");
			   String lineStatus = (contract.getActivity().isRepairCompleteFlag()?lineItem.getPartDisposition().getValue():null);
			   if (lineStatus != null){
				   errorCode1 = (lineStatus.equals("Used")?lineItem.getErrorCode1().getValue():"");
	               errorCode2 = (lineStatus.equals("Used")?lineItem.getErrorCode2().getValue():"");
			   }
				   			   	          
			   
			   recommendedPart = new SiebelRecommendedPartsList();
			   recommendedPart.setOrderDate(dateToString(lineItem.getPartOrderedDate()));
			   recommendedPart.setQuantity(Integer.toString(lineItem.getQuantity()));
			   recommendedPart.setPartInformation(partInformation);
			   recommendedPart.setReturnRequiredFlag(booleanToString(lineItem.isReturnRequiredFlag()));
			   recommendedPart.setShipToAddress(shipToAddress);
			   recommendedPart.setStatus(lineStatus);
			   recommendedPart.setErrorCode1(errorCode1);
			   recommendedPart.setErrorCode2(errorCode2);
			   recommendedPart.setCarrier(carrier);
			   recommendedPart.setReturnTrackingNumber(trackingNumber);
			   recommendedPart.setExpediteShipmentFlag(expedite);
					
		        recommendedPartsList[i] = recommendedPart;
		        i++;
		   }
		   
	   }
	   /*Added by sankha for LEX:AIR00069190 start*/
	   else{
		   
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
			
			SiebelAddress shipToAddress = new SiebelAddress();
			shipToAddress.setAddressId(contract.getActivity().getShipToAddress().getAddressId());
			shipToAddress.setAddressName(contract.getActivity().getShipToAddress().getAddressName().replaceAll(",", ""));
			shipToAddress.setAddressLine1(contract.getActivity().getShipToAddress().getAddressLine1().replaceAll(",", ""));
			shipToAddress.setAddressLine2(contract.getActivity().getShipToAddress().getAddressLine2().replaceAll(",", ""));
			if((contract.getActivity().getShipToAddress().getOfficeNumber() != null) || (contract.getActivity().getShipToAddress().getOfficeNumber()) != ""){
				shipToAddress.setFloorId((contract.getActivity().getShipToAddress().getOfficeNumber().replaceAll(",", "")));
				}
			shipToAddress.setCity(contract.getActivity().getShipToAddress().getCity().replaceAll(",", ""));
			shipToAddress.setProvince(shipToStateProvince.replaceAll(",", ""));
			shipToAddress.setPostalCode(contract.getActivity().getShipToAddress().getPostalCode().replaceAll(",", ""));
			shipToAddress.setCountry(contract.getActivity().getShipToAddress().getCountry().replaceAll(",", ""));
			shipToAddress.setNewAddressFlag(contract.getActivity().getShipToAddress().getNewAddressFlag());
			if(contract.getActivity().getShipToAddress().getIsAddressCleansed()){
				shipToAddress.setAddressCleansedFlag("Y");
			}else{
				shipToAddress.setAddressCleansedFlag("N");
			}
			shipToAddress.setRegion(contract.getActivity().getShipToAddress().getRegion().replaceAll(",", ""));
			recommendedPartsList = new SiebelRecommendedPartsList[1];
			PartInformation partInformation = new PartInformation();
			
			SiebelRecommendedPartsList recommendedPart = null;
			String expedite = contract.getActivity().getExpedite();
			if(expedite == null){
				   expedite = "N"; 
			}
			
			recommendedPart = new SiebelRecommendedPartsList();
			recommendedPart.setQuantity(Integer.toString(0));
			recommendedPart.setPartInformation(partInformation);
			recommendedPart.setReturnRequiredFlag(booleanToString(false));
			recommendedPart.setShipToAddress(shipToAddress);
			recommendedPart.setExpediteShipmentFlag(expedite);
			
			recommendedPartsList[0] = recommendedPart;		   
	   }
	   /*Added by sankha for LEX:AIR00069190 end*/
	   
	   SiebelContact technician = new SiebelContact();
	   technician.setContactId(contract.getActivity().getTechnician().getContactId());
	   technician.setFirstName(contract.getActivity().getTechnician().getFirstName());
	   technician.setLastName(contract.getActivity().getTechnician().getLastName());
	   technician.setNewContactFlag(booleanToString(contract.getActivity().getTechnician().getNewContactFlag()));

	   SiebelPaymentRequestList[] paymentRequestList = null;
	   if (contract.getActivity().isRepairCompleteFlag() && contract.getActivity().getAdditionalPaymentRequestList() != null){
		   paymentRequestList = new SiebelPaymentRequestList[contract.getActivity().getAdditionalPaymentRequestList().size()];
			int p = 0;	   
		   for (AdditionalPaymentRequest paymentLineItem : contract.getActivity().getAdditionalPaymentRequestList()) { 	
			   SiebelPaymentRequestList paymentRequest = new SiebelPaymentRequestList();
			   paymentRequest.setPaymentType(paymentLineItem.getPaymentType().getValue());
			   paymentRequest.setQuantity(Integer.toString(paymentLineItem.getQuantity()));
			   paymentRequest.setUnitPrice(Double.toString(paymentLineItem.getUnitPrice()));
			   paymentRequest.setTotalAmount(Double.toString(paymentLineItem.getTotalAmount()));
			   paymentRequest.setCurrency(paymentLineItem.getPaymentCurrency());
			   paymentRequest.setDescription(paymentLineItem.getDescription());
			
			   paymentRequestList[p] = paymentRequest;
		        p++;
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
			activityNote.setNoteDate(dateToString(activityNoteLineItem.getNoteDate()));
			activityNote.setNoteAuthor(noteAuthor);
			activityNote.setNoteDetails(activityNoteLineItem.getNoteDetails());
			
			   activityNotes[n] = activityNote;
		        n++;
		   }
		   
	   }

	   SiebelDebriefInformation debriefInformation = null;
	   if (contract.getActivity().isRepairCompleteFlag()){
		   
		   debriefInformation = new SiebelDebriefInformation();
		   //Commented for Floor Support Defect 13318
		   //debriefInformation.setRepairDescription(XMLEncodeUtil.escapeXML(contract.getActivity().getDebrief().getRepairDescription()));
		   debriefInformation.setRepairDescription(contract.getActivity().getDebrief().getRepairDescription());
		   debriefInformation.setResolutionCode(contract.getActivity().getDebrief().getResolutionCode().getValue());
		   debriefInformation.setServiceStartDate(dateToString(contract.getActivity().getDebrief().getServiceStartDate()));
		   debriefInformation.setServiceEndDate(dateToString(contract.getActivity().getDebrief().getServiceEndDate()));
		   //ServiceRequestedDate added for CI BRD13-10-07
		   debriefInformation.setServiceRequestedDate(dateToString(contract.getActivity().getDebrief().getServiceRequestedDate()));
		   debriefInformation.setDebriefPartStatus(contract.getActivity().getDebrief().getDeviceCondition().getValue());
		   debriefInformation.setPageCount(contract.getActivity().getDebrief().getPageCountAll());
	   }
	   
	   SiebelContact requester = new SiebelContact();
	   requester.setContactId(contract.getActivity().getServiceRequest().getRequestor().getContactId());
	   requester.setFirstName(contract.getActivity().getServiceRequest().getRequestor().getFirstName());
	   requester.setLastName(contract.getActivity().getServiceRequest().getRequestor().getLastName());
	   requester.setDepartment(contract.getActivity().getServiceRequest().getRequestor().getDepartment());
	   requester.setWorkPhone(contract.getActivity().getServiceRequest().getRequestor().getWorkPhone());
	   requester.setEmailAddress(contract.getActivity().getServiceRequest().getRequestor().getEmailAddress());
	   
	   
	   SiebelMDMCustomerInformation customerInformation = new SiebelMDMCustomerInformation(contract.getMdmId(), contract.getMdmLevel());

		
	   ClaimsServiceRequestData3 requestData = new ClaimsServiceRequestData3();
	   requestData.setClaimSource(claimSource);
	   requestData.setRequester(requester);
	   requestData.setCustomerInformation(customerInformation);
	   requestData.setPrimaryContact(primaryContact);
	   requestData.setAssetInformation(assetInformation);
	   requestData.setServiceProviderInformation(serviceProviderInformation);
	   requestData.setClaimsDetails(claimDetails);
	   requestData.setTechnician(technician);
	   requestData.setRecommendedPartsList(recommendedPartsList);
	   requestData.setActivityNotes(activityNotes);
	   requestData.setRepairCompleteFlag(booleanToString(contract.getActivity().isRepairCompleteFlag()));
	   requestData.setDebriefInformation(debriefInformation);
	   requestData.setPaymentRequestList(paymentRequestList);
	   
	   requestData.setClaimCoveredService(contract.getActivity().getClaimCoveredService());
	   
	   if(contract.getClaimDraftStatus() != null || contract.getClaimDraftStatus() != "")
	   {
	   requestData.setServiceRequestType(contract.getClaimDraftStatus());
	   }
		CreateClaimsServiceRequestInput createClaimsServiceRequestInput = new CreateClaimsServiceRequestInput();
		CreateClaimsServiceRequestWSInput inputWS = new CreateClaimsServiceRequestWSInput(documentMetaData, requestData);
		createClaimsServiceRequestInput.setCreateClaimsServiceRequestWSInput(inputWS);
		// Getting Claim number and Claim Id for CI6 Create Claim Attachment
		StringHolder claimNumber = new StringHolder("");
		StringHolder claimId = new StringHolder("");
		
		port.createWarrantyClaim("$null",synchOrAsynch, createClaimsServiceRequestInput,claimNumber,claimId);
		
	WarrantyClaimCreateResult result = new WarrantyClaimCreateResult();
	   if (claimId != null && !claimId.value.trim().equals("")){
		   result.setRequestId(claimId.value);		
		   
	   }else{
		   throw new Exception("Error creating claim - Claim Id not received");
	   }
	   
	   if (claimNumber != null && !claimNumber.value.trim().equals("")){
		   result.setRequestNumber(claimNumber.value);
	   }else{
		   throw new Exception("Error creating claim - Claim Number not received");
	   }
		return result;
	}

	@Override
	public ClaimUpdateResult updateWarrantyClaim(ClaimUpdateContract contract)
			throws Exception {
		
		WarrantyClaimsServiceRequestWS locator = new WarrantyClaimsServiceRequestWSLocator();
		WarrantyClaimsServiceRequestWS_PortType port = locator.getServiceRequest_warrantyClaimsServiceRequestWS_Port(new URL(getAddress()));

		org.apache.axis.client.Stub stub = ((org.apache.axis.client.Stub) port);
		stub.setUsername(getUserName());
		stub.setPassword(getPassword());
		WebServiceDocumentMetaData documentMetaData = new WebServiceDocumentMetaData();
		documentMetaData.setSenderId(senderId);
		documentMetaData.setSenderName(senderName);
		documentMetaData.setReceiverId(receiverId);
		
		SiebelContact primaryContact = new SiebelContact();
		primaryContact.setContactId(contract.getActivity().getServiceRequest().getPrimaryContact().getContactId());
		primaryContact.setFirstName(contract.getActivity().getServiceRequest().getPrimaryContact().getFirstName());
		primaryContact.setLastName(contract.getActivity().getServiceRequest().getPrimaryContact().getLastName());
		primaryContact.setWorkPhone(contract.getActivity().getServiceRequest().getPrimaryContact().getWorkPhone());
		primaryContact.setEmailAddress(contract.getActivity().getServiceRequest().getPrimaryContact().getEmailAddress());
		primaryContact.setUpdateContactFlag(booleanToString(contract.getActivity().getServiceRequest().getPrimaryContact().getUpdateContactFlag()));
		
		//added by sbag BRD #BRD 14-06-03
		SiebelServiceRequestDetails claimDetails = new SiebelServiceRequestDetails();
		claimDetails.setRequestLexmarkReviewFlag(booleanToString(contract.getActivity().isRequestLexmarkReviewFlag()));
		claimDetails.setReviewComments(XMLEncodeUtil.escapeXML(contract.getActivity().getReviewComments()));
		claimDetails.setFailureCode(contract.getActivity().getActualFailureCode().getValue()); 
		claimDetails.setProblemDescription(contract.getActivity().getServiceRequest().getProblemDescription()); 
		logger.debug("The details are below----->>>>>>"+contract.getActivity().isRequestLexmarkReviewFlag()+"-----"+contract.getActivity().getReviewComments()+"---"+contract.getActivity().getServiceRequest().getProblemDescription()+"----"+contract.getActivity().getActualFailureCode().getValue());
		
		//Ends here
		
		SiebelContact requester = new SiebelContact();
		requester.setContactId(contract.getActivity().getServiceRequest().getRequestor().getContactId());
		requester.setFirstName(contract.getActivity().getServiceRequest().getRequestor().getFirstName());
		requester.setLastName(contract.getActivity().getServiceRequest().getRequestor().getLastName());
		requester.setDepartment(contract.getActivity().getServiceRequest().getRequestor().getDepartment());
		requester.setWorkPhone(contract.getActivity().getServiceRequest().getRequestor().getWorkPhone());
		requester.setEmailAddress(contract.getActivity().getServiceRequest().getRequestor().getEmailAddress());

		SiebelServiceProviderInformation serviceProviderInformation = new SiebelServiceProviderInformation();
		serviceProviderInformation.setServiceProviderAccountId(contract.getActivity().getPartnerAccount().getAccountId());
		serviceProviderInformation.setServiceProviderName(contract.getActivity().getPartnerAccount().getAccountName());
		serviceProviderInformation.setServiceProviderOrganization(contract.getActivity().getPartnerAccount().getOrganizationID());
		serviceProviderInformation.setServiceProviderReferenceNumber(contract.getActivity().getServiceProviderReferenceNumber());

		SiebelContact technician = new SiebelContact();
		   technician.setContactId(contract.getActivity().getTechnician().getContactId());
		   technician.setFirstName(contract.getActivity().getTechnician().getFirstName());
		   technician.setLastName(contract.getActivity().getTechnician().getLastName());
		   technician.setUpdateContactFlag(booleanToString(contract.getActivity().getTechnician().getUpdateContactFlag()));
		   technician.setNewContactFlag(booleanToString(contract.getActivity().getTechnician().getNewContactFlag()));

		   SiebelRecommendedPartsList[] recommendedPartsList = null;
		   if(contract.getActivity().getPendingPartList() != null){
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

				SiebelAddress shipToAddress = new SiebelAddress();
				shipToAddress.setAddressId(contract.getActivity().getShipToAddress().getAddressId());
				shipToAddress.setAddressName(contract.getActivity().getShipToAddress().getAddressName());
				shipToAddress.setAddressLine1(contract.getActivity().getShipToAddress().getAddressLine1());
				shipToAddress.setAddressLine2(contract.getActivity().getShipToAddress().getAddressLine2());
				shipToAddress.setCity(contract.getActivity().getShipToAddress().getCity());
				shipToAddress.setProvince(shipToStateProvince);
				shipToAddress.setPostalCode(contract.getActivity().getShipToAddress().getPostalCode());
				shipToAddress.setCountry(contract.getActivity().getShipToAddress().getCountry());
				shipToAddress.setNewAddressFlag(contract.getActivity().getShipToAddress().getNewAddressFlag());
			   
			   //CI-6 Changes
			   String expedite = contract.getActivity().getExpedite();
			   if(expedite == null){
				   expedite = "N"; 
			   }
			   
			   recommendedPartsList = new SiebelRecommendedPartsList[contract.getActivity().getPendingPartList().size()];
				int i = 0;	   
			   for (PartLineItem lineItem : contract.getActivity().getPendingPartList()) { 	
				   PartInformation partInformation = new PartInformation();
				   partInformation.setPartNumber(lineItem.getPartNumber());
				   partInformation.setPartId(lineItem.getPartId());
				   partInformation.setPartName(lineItem.getPartName());
				   
				   boolean partUpdateFlag;
				   if(lineItem.getPartLineItemId()== null)
					   partUpdateFlag = false;
				   else
					   partUpdateFlag = true;
				   SiebelRecommendedPartsList recommendedPart = null;
				   recommendedPart = new SiebelRecommendedPartsList();
				   recommendedPart.setOrderDate(dateToString(lineItem.getPartOrderedDate()));
				   recommendedPart.setQuantity(Integer.toString(lineItem.getQuantity()));
				   recommendedPart.setPartInformation(partInformation);
				   recommendedPart.setReturnRequiredFlag(booleanToString(lineItem.isReturnRequiredFlag()));
				   recommendedPart.setShipToAddress(shipToAddress);
				   recommendedPart.setRecommendedPartUpdatedFlag(booleanToString(partUpdateFlag));
				   recommendedPart.setExpediteShipmentFlag(expedite);
									   		
			        recommendedPartsList[i] = recommendedPart;
			        i++;
			   }		   
		   }
				
		   SiebelReturnPartsList[] returnPartsList = null;
		   if(contract.getActivity().getReturnPartList() != null){
			   returnPartsList = new SiebelReturnPartsList[contract.getActivity().getReturnPartList().size()];
				int i = 0;	   
			   for (PartLineItem lineItem : contract.getActivity().getReturnPartList()) { 	
				   PartInfromation partInfromation = new PartInfromation();
				   partInfromation.setPartNumber(lineItem.getPartNumber());
				   partInfromation.setPartId(lineItem.getPartId());
				   
				   SiebelReturnPartsList returnPart = null;
				   returnPart = new SiebelReturnPartsList();
				   returnPart.setLineItemId(lineItem.getPartLineItemId());
				   returnPart.setPartInfromation(partInfromation);
				   returnPart.setCarrier(lineItem.getCarrier().getValue());
				   returnPart.setReturnTrackingNumber(lineItem.getTrackingNumber());
				   returnPart.setReturnPartUpdatedFlag(booleanToString(lineItem.isPartLineItemUpdateFlag()));
									   		
			        returnPartsList[i] = returnPart;
			        i++;
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
		  
		   ClaimsServiceRequestData2 requestData = new ClaimsServiceRequestData2();
		   requestData.setClaimId(contract.getActivity().getServiceRequest().getId());
		   requestData.setServiceRequestNumber(contract.getActivity().getServiceRequest().getServiceRequestNumber());
		   requestData.setActivityId(contract.getActivity().getActivityId());
		   requestData.setClaimSource(claimSource);
		   requestData.setRequester(requester);
		   requestData.setPrimaryContact(primaryContact);
		   requestData.setServiceProviderInformation(serviceProviderInformation);
		   requestData.setTechnician(technician);
		   requestData.setRecommendedPartsList(recommendedPartsList);
		   requestData.setReturnPartsList(returnPartsList);
		   requestData.setActivityNotes(activityNotes);
		   requestData.setPaymentRequestList(paymentRequestList);
		   requestData.setServiceRequestType(contract.getSrType()); //Added for CI BRD13-10-01
		   requestData.setClaimsDetails(claimDetails); //BRD 14-06-03
		   UpdateClaimsServiceRequestInput updateClaimsServiceRequestInput = new UpdateClaimsServiceRequestInput();
		   UpdateClaimsServiceRequestWSInput inputWS = new UpdateClaimsServiceRequestWSInput(documentMetaData, requestData);
		   updateClaimsServiceRequestInput.setUpdateClaimsServiceRequestWSInput(inputWS);
		   //String Status = port.updateWarrantyClaim("$null",synchOrAsynch, updateClaimsServiceRequestInput);
		   StringHolder status = new StringHolder();
		   StringHolder claimNumber = new StringHolder();
		   StringHolder claimId = new StringHolder();
		   port.updateWarrantyClaim("$null", synchOrAsynch, updateClaimsServiceRequestInput, status, claimNumber, claimId);
		   
		   boolean successFlag = true;
		   /*if(Status.equalsIgnoreCase("success"))
			   successFlag = true;*/
		   
		ClaimUpdateResult result = new ClaimUpdateResult();
		result.setSuccess(successFlag);
		return result;
		
	}

	@Override
	public ClaimDebriefSubmitResult submitClaimDebrief(
			ClaimDebriefSubmitContract contract) throws Exception {

			WarrantyClaimsServiceRequestWS locator = new WarrantyClaimsServiceRequestWSLocator();
			WarrantyClaimsServiceRequestWS_PortType port = locator.getServiceRequest_warrantyClaimsServiceRequestWS_Port(new URL(getAddress()));
		
			org.apache.axis.client.Stub stub = ((org.apache.axis.client.Stub) port);
			stub.setUsername(getUserName());
			stub.setPassword(getPassword());
			WebServiceDocumentMetaData documentMetaData = new WebServiceDocumentMetaData();
			documentMetaData.setSenderId(senderId);
			documentMetaData.setSenderName(senderName);
			documentMetaData.setReceiverId(receiverId);
			SiebelContact requester = new SiebelContact();
			requester.setContactId(contract.getActivity().getServiceRequest().getRequestor().getContactId());
			requester.setFirstName(contract.getActivity().getServiceRequest().getRequestor().getFirstName());
			requester.setLastName(contract.getActivity().getServiceRequest().getRequestor().getLastName());
			requester.setDepartment(contract.getActivity().getServiceRequest().getRequestor().getDepartment());
			requester.setWorkPhone(contract.getActivity().getServiceRequest().getRequestor().getWorkPhone());
			requester.setEmailAddress(contract.getActivity().getServiceRequest().getRequestor().getEmailAddress());
			
			SiebelServiceProviderInformation serviceProviderInformation = new SiebelServiceProviderInformation();
			serviceProviderInformation.setServiceProviderAccountId(contract.getActivity().getPartnerAccount().getAccountId());
			serviceProviderInformation.setServiceProviderName(contract.getActivity().getPartnerAccount().getAccountName());
			serviceProviderInformation.setServiceProviderOrganization(contract.getActivity().getPartnerAccount().getOrganizationID());
			serviceProviderInformation.setServiceProviderReferenceNumber(contract.getActivity().getServiceProviderReferenceNumber());
			SiebelContact technician = new SiebelContact();
			   technician.setContactId(contract.getActivity().getTechnician().getContactId());
			   technician.setFirstName(contract.getActivity().getTechnician().getFirstName());
			   technician.setLastName(contract.getActivity().getTechnician().getLastName());
			   technician.setUpdateContactFlag(booleanToString(contract.getActivity().getTechnician().getUpdateContactFlag()));
			   technician.setNewContactFlag(booleanToString(contract.getActivity().getTechnician().getNewContactFlag()));
		
			SiebelRecommendedPartsList[] recommendedPartsList = null;
			if(contract.getActivity().getDebrief().getPartDebriefList() != null){
				SiebelAddress shipToAddress = new SiebelAddress();
				shipToAddress.setAddressLine1(""); // These are mandatory fields
				shipToAddress.setCity("");  // these are mandatory fields
				//CI-6 Changes
				String expedite = contract.getActivity().getExpedite();
				if(expedite == null){
					   expedite = "N"; 
				}
			   recommendedPartsList = new SiebelRecommendedPartsList[contract.getActivity().getDebrief().getPartDebriefList().size()];
				int i = 0;	   
			   for (PartLineItem lineItem : contract.getActivity().getDebrief().getPartDebriefList()) { 	
				   PartInformation partInformation = new PartInformation();
				   partInformation.setPartNumber(lineItem.getPartNumber());
				   partInformation.setPartId(lineItem.getPartId());
				   partInformation.setPartName(lineItem.getPartName());
				   
					   String carrier = "";
					   String trackingNumber = "";
					   if(lineItem.getCarrier() != null && lineItem.getCarrier().getValue()!= null && !"".equals(lineItem.getCarrier().getValue())){ //PARTHA CHANGED
						   carrier = lineItem.getCarrier().getValue();
					   }
					   else{
						   carrier = "";
					   }
					   if(null != lineItem.getTrackingNumber() && !"".equals(lineItem.getTrackingNumber())){ //PARTHA CHANGED
						   trackingNumber = lineItem.getTrackingNumber();
					   }
					   else{
						   trackingNumber = "";
					   }
					   logger.info("***Carrier is : " + carrier); //Added by sankha for testing
					   logger.info("***Tracking number is : " + trackingNumber); //Added by sankha for testing
					   String lineStatus = lineItem.getPartDisposition().getValue();
					   String errorCode1 = (lineStatus.equals("Used")?lineItem.getErrorCode1().getValue():"");
			           String errorCode2 = (lineStatus.equals("Used")?lineItem.getErrorCode2().getValue():"");
			           String lineItemId = (lineItem.getPartLineItemId()!= null?lineItem.getPartLineItemId():"");
				   
				   
			       SiebelRecommendedPartsList recommendedPart = null;
			       recommendedPart = new SiebelRecommendedPartsList();
			       recommendedPart.setLineItemId(lineItemId);
			       recommendedPart.setOrderDate(dateToString(lineItem.getPartOrderedDate()));
				   recommendedPart.setQuantity(Integer.toString(lineItem.getQuantity()));
				   recommendedPart.setPartInformation(partInformation);
				   recommendedPart.setReturnRequiredFlag(booleanToString(lineItem.isReturnRequiredFlag()));
				   recommendedPart.setShipToAddress(shipToAddress);
				   recommendedPart.setStatus(lineStatus);
				   recommendedPart.setErrorCode1(errorCode1);
				   recommendedPart.setErrorCode2(errorCode2);
				   recommendedPart.setCarrier(carrier);
				   recommendedPart.setReturnTrackingNumber(trackingNumber);
				   recommendedPart.setRecommendedPartUpdatedFlag(booleanToString(lineItem.isPartLineItemUpdateFlag()));
				   recommendedPart.setExpediteShipmentFlag(expedite);
									   		
			        recommendedPartsList[i] = recommendedPart;
			        i++;
			   }		   
		   }
			SiebelDebriefInformation debriefInformation = null;  
			debriefInformation = new SiebelDebriefInformation();
			   //debriefInformation.setRepairDescription(XMLEncodeUtil.escapeXML(contract.getActivity().getDebrief().getRepairDescription()));
			   //Changed for CI Defect # 13318
			   debriefInformation.setRepairDescription(contract.getActivity().getDebrief().getRepairDescription());
			   debriefInformation.setResolutionCode(contract.getActivity().getDebrief().getResolutionCode().getValue());
			   debriefInformation.setServiceStartDate(dateToString(contract.getActivity().getDebrief().getServiceStartDate()));
			   debriefInformation.setServiceEndDate(dateToString(contract.getActivity().getDebrief().getServiceEndDate()));
			   debriefInformation.setServiceRequestedDate(dateToString(contract.getActivity().getDebrief().getServiceRequestedDate()));
			   debriefInformation.setDebriefPartStatus(contract.getActivity().getDebrief().getDeviceCondition().getValue());
			   debriefInformation.setPageCount(contract.getActivity().getDebrief().getPageCountAll().replaceAll(",", ""));

		   SiebelServiceRequestDetails claimDetails = new SiebelServiceRequestDetails();
		    claimDetails.setFailureCode(contract.getActivity().getActualFailureCode().getValue());
			claimDetails.setProblemDescription(contract.getActivity().getServiceRequest().getProblemDescription());
			claimDetails.setRequestLexmarkReviewFlag(booleanToString(contract.getActivity().isRequestLexmarkReviewFlag()));
			claimDetails.setReviewComments(contract.getActivity().getReviewComments());
		
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
	
		   ClaimsServiceRequestData requestData = new ClaimsServiceRequestData();
		   requestData.setClaimId(contract.getActivity().getServiceRequest().getId());
		   requestData.setServiceRequestNumber(contract.getActivity().getServiceRequest().getServiceRequestNumber());
		   requestData.setActivityId(contract.getActivity().getActivityId());
		   requestData.setClaimSource(getClaimSource());
		   requestData.setRequester(requester);
		   requestData.setServiceProviderInformation(serviceProviderInformation);
		   requestData.setClaimDetails(claimDetails);
		   requestData.setTechnician(technician);
		   requestData.setRecommendPartsList(recommendedPartsList);
		   requestData.setActivityNotes(activityNotes);
		   requestData.setDebriefInformation(debriefInformation);
		   requestData.setPaymentRequestList(paymentRequestList);
		   requestData.setClaimStatus(contract.getDebriefStatus());
				
		DebriefClaimsServiceRequestInput debriefClaimsServiceRequestInput = new DebriefClaimsServiceRequestInput();
		DebriefClaimsServiceRequestWSInput inputWS = new DebriefClaimsServiceRequestWSInput(documentMetaData, requestData);
		debriefClaimsServiceRequestInput.setDebriefClaimsServiceRequestWSInput(inputWS);
		logger.debug("WSDL Endpoint Address for Claim Debrief is: " +  address );
		String Status = port.debriefWarrantyClaim("$null",synchOrAsynch, debriefClaimsServiceRequestInput);
		
		boolean successFlag = false;
		   if(Status.equalsIgnoreCase("success"))
			   successFlag = true;
		
		ClaimDebriefSubmitResult result = new ClaimDebriefSubmitResult();
		result.setSuccess(successFlag);
		return result;
		
		
	}
	
	private String booleanToString(Boolean b) {
		//logger.info("---------------inside boolean--------------- : "+b);
		if (b == null) return "false";
		return b ? "true" : "false";
	}
	
	private String dateToString(Date d) {
		if (d == null) return "";
		Format formatter;
		formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		String date = formatter.format(d);
		return date;
	}
	
}
