package com.lexmark.webservice.impl.real;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import com.lexmark.SiebelShared.debriefs.ActivityDetails;
import com.lexmark.SiebelShared.debriefs.AdditionalAddresses;
import com.lexmark.SiebelShared.debriefs.AdditionalContacts;
import com.lexmark.SiebelShared.debriefs.AdditionalPartsList;
import com.lexmark.SiebelShared.debriefs.AssetManagementStatus;
import com.lexmark.SiebelShared.debriefs.AssetManagementStatus2;
import com.lexmark.SiebelShared.debriefs.AssetManagementStatusDetails;
import com.lexmark.SiebelShared.debriefs.AssetManagementStatus_PortType;
import com.lexmark.SiebelShared.debriefs.DebriefDetails;
import com.lexmark.SiebelShared.debriefs.LXKAssetManagementStatusWebServicesProviderAssetManagementStatusLocator;
import com.lexmark.SiebelShared.debriefs.LXKAssetManagementStatus_webServices_provider_AssetManagementStatus_BinderStub;
import com.lexmark.SiebelShared.debriefs.NoteAuthor;
import com.lexmark.SiebelShared.debriefs.RecommendedPartsList;
import com.lexmark.SiebelShared.debriefs.RelatedActivityInformation;
import com.lexmark.SiebelShared.debriefs.SiebelActivityNotes;
import com.lexmark.SiebelShared.debriefs.SiebelAddress;
import com.lexmark.SiebelShared.debriefs.SiebelAssetManagementInformation;
import com.lexmark.SiebelShared.debriefs.SiebelContact;
import com.lexmark.SiebelShared.debriefs.SiebelMeterReadInformation;
import com.lexmark.SiebelShared.debriefs.WebServiceDocumentMetaData;
import com.lexmark.constants.LexmarkConstants;
import com.lexmark.contract.HardwareDebriefContract;
import com.lexmark.contract.api.ContractBase;
import com.lexmark.domain.AccountContact;
import com.lexmark.domain.Activity;
import com.lexmark.domain.ActivityNote;
import com.lexmark.domain.Asset;
import com.lexmark.domain.GenericAddress;
import com.lexmark.domain.PageCounts;
import com.lexmark.domain.Part;
import com.lexmark.domain.PartLineItem;
import com.lexmark.domain.ServiceRequest;
import com.lexmark.framework.logging.LEXLogger;
import com.lexmark.result.HardwareDebriefResult;
import com.lexmark.webservice.api.HardwareDebriefService;

/**
* This is the implementation class for HArdwareDebriefService 
*
* @version 1.10 12 Nov 2014
* @author Wipro Technologies 
*/
public class HardwareDebriefServiceImpl implements HardwareDebriefService{
	
	private static final LEXLogger LOGGER = LEXLogger.getLEXLogger(HardwareDebriefServiceImpl.class);

	private static String CLASS_NAME = "HardwareDebriefServiceImpl" ;
	
	private String userName;
	private String password;
	private String endpointURL;
	private String sourceSystem;
	private String businessProcess;
	private String objectActiontypeChange;
	private String integrationFrequency;
	private String senderID;
	private String senderName;
	private String receiverId;
	private final String DATEFORMAT="MM/dd/yyyy HH:mm:ss";
	private final BigDecimal millsecondsInHour = new BigDecimal(LexmarkConstants.MILLISECONDS_IN_HOUR);
	private String srType;
	@Override
	public HardwareDebriefResult saveDebriefRquest(HardwareDebriefContract contract) throws Exception {
		
		String METHOD_NAME="saveDebriefRquest";
		LOGGER.enter(CLASS_NAME, METHOD_NAME);
		HardwareDebriefResult result = new HardwareDebriefResult();
		try{
		LXKAssetManagementStatusWebServicesProviderAssetManagementStatusLocator wsLocator = new LXKAssetManagementStatusWebServicesProviderAssetManagementStatusLocator();		 
		AssetManagementStatus_PortType port = wsLocator.getLXKAssetManagementStatus_webServices_provider_AssetManagementStatus_Port(new URL(getEndpointURL()));		
		
		LXKAssetManagementStatus_webServices_provider_AssetManagementStatus_BinderStub stub = ((LXKAssetManagementStatus_webServices_provider_AssetManagementStatus_BinderStub) port);
		stub.setUsername(getUserName());//"ServicesWebPortal");
		stub.setPassword(getPassword());//"Services@123");
		if(LOGGER.isDebugEnabled()){
			LOGGER.debug(" after setting user name password");
			LOGGER.debug(" after setting user name password"+ getUserName() +"  "+getPassword());
		}
		LOGGER.debug(" endpoint "+ getEndpointURL());
		
		
		WebServiceDocumentMetaData documentMetaData = new WebServiceDocumentMetaData();
		
		
		LOGGER.debug(" getSourceSystem "+ getSourceSystem());
		/*documentMetaData.setSourceSystem(getSourceSystem());
		
		documentMetaData.setBusinessProcess(getBusinessProcess());
		
		documentMetaData.setObjectChangeActionType(ObjectChangeActionType.fromString(getObjectActiontypeChange()));
		
		documentMetaData.setIntegrationCreationDateTime(getCurrentDateTime());
		
		documentMetaData.setIntegrationFrequency(IntegrationFrequency.fromString(getIntegrationFrequency()));*/
		
		
		
		//TradingPartnerMetaData tradingpartnerMetadata=new TradingPartnerMetaData();
		
		documentMetaData.setSenderId(getSenderID());
		
		documentMetaData.setSenderName(getSenderName());
		
		documentMetaData.setReceiverId(getReceiverId());
		
		//documentMetaData.setTradingPartnerMetaData(tradingpartnerMetadata);
		
		
		
		
		Activity oldActivity = contract.getActivity();
		Activity userEnteredActivity = contract.getUserEnteredActivity();
		String debriefStatus = "";
		if(contract.getDebriefStatus()!=null){
			debriefStatus = contract.getDebriefStatus();
		}		
		setSrType(contract.getSrType());
		
		
		AssetManagementStatus2 assetManagementStatus2=new AssetManagementStatus2();
		assetManagementStatus2.setDocumentMetaData(documentMetaData);
		assetManagementStatus2.setAssetManagementStatusDetails(populateAssetManagementDetails(oldActivity,userEnteredActivity,debriefStatus,contract.getTimeZoneOffset()));
		
		AssetManagementStatus assetManagementStatus = new AssetManagementStatus();
		assetManagementStatus.setAssetManagementStatus(assetManagementStatus2);
		
		LOGGER.debug("before port call");
		String returnResult =port.processAssetManagementStatus(assetManagementStatus,"$null");
		LOGGER.debug("after port call");
		LOGGER.debug("result is "+returnResult );
		if(StringUtils.isBlank(returnResult)){
			throw new Exception("result is null");
		}
		result.setReturnResult(returnResult);
		}catch(Exception e){
			e.printStackTrace();
		}
		LOGGER.exit(CLASS_NAME, METHOD_NAME);
		return result;
	}


	/**
	 * @param userName 
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}


	/**
	 * @return string 
	 */
	public String getUserName() {
		return userName;
	}


	/**
	 * @param password 
	 */
	public void setPassword(String password) {
		this.password = password;
	}


	/**
	 * @return string 
	 */
	public String getPassword() {
		return password;
	}


	/**
	 * @param endpointURL 
	 */
	public void setEndpointURL(String endpointURL) {
		this.endpointURL = endpointURL;
	}


	/**
	 * @return string 
	 */
	public String getEndpointURL() {
		return endpointURL;
	}
	
	/**
	 * @param userEnteredactivity 
	 * @param oldActivity 
	 * @param debriefStatus 
	 * @param timezoneOffset 
	 * @return string 
	 */
	private DebriefDetails populateDebriefDetails(Activity userEnteredactivity,Activity oldActivity,String debriefStatus,float timezoneOffset){
		LOGGER.debug(" enter populateDebriefDetails");
		ServiceRequest sr=userEnteredactivity.getServiceRequest();
		
		DebriefDetails wmDebriefDetails=new DebriefDetails();

		if(debriefStatus!=null && !debriefStatus.equalsIgnoreCase("")){
			wmDebriefDetails.setDebriefStatus(debriefStatus);
		}
	
		wmDebriefDetails.setActualStartDate(converDateToGMTString(userEnteredactivity.getDebrief().getServiceStartDate(),timezoneOffset));

	    wmDebriefDetails.setActualEndDate(converDateToGMTString(userEnteredactivity.getDebrief().getServiceEndDate(),timezoneOffset));

	    wmDebriefDetails.setTechnicianName(userEnteredactivity.getTechnician()!=null?checkStringBlank(userEnteredactivity.getTechnician().getTechnicianName()):"");
	    /*if(userEnteredactivity.getServiceRequest().getAsset().getMoveToAddress()!=null){
	    wmDebriefDetails.setMoveToAddressComment(generateAddressString(userEnteredactivity.getServiceRequest().getAsset().getMoveToAddress()));
	    }*/
	   // wmDebriefDetails.setTechnicianComments(checkStringBlank(userEnteredactivity.getDebrief().getProblemDescription()));

	    /* wmDebriefDetails.setTravelDistance("");

	    wmDebriefDetails.TravelDistanceUnitOfMeasure("");

	    wmDebriefDetails.TravelDuration("");

	    wmDebriefDetails.TravelDurationUnitOfMeasure;

	    wmDebriefDetails.ResponseMetricUnitOfMeasure;

	    wmDebriefDetails.AdditionalServiceRequiredFlag;

	    wmDebriefDetails.AssetStoredDate;*/
	   
	    int i=0;
	     
		if(sr!=null && sr.getParts()!=null){
			LOGGER.debug(" recommended part list is not null");
			List<Part> userenteredpartList=sr.getParts();
			List<Part> oldPartList=oldActivity.getServiceRequest().getAsset().getPartList();
			RecommendedPartsList[] recommendedPartsList = new RecommendedPartsList[oldPartList.size()];// To be populated
			
			
			
			for(Part part:oldPartList){
					recommendedPartsList[i]=new RecommendedPartsList();
					//recommendedPartsList[i].setActivityLineNumber(checkStringBlank(userEnteredactivity.getActivityId()));
					recommendedPartsList[i].setPartDescription(checkStringBlank(part.getDescription()));
					
					
					recommendedPartsList[i].setPartNumber(checkStringBlank(part.getPartNumber()));
					//recommendedPartsList[i].setQuantity(part.getOrderQuantity());
					if(oldActivity.getActivityType().getValue()!=null){
						LOGGER.debug("Activity Type in Impl "+oldActivity.getActivityType().getValue());
						if(oldActivity.getActivityType().getValue().toUpperCase().contains("MOVE") || oldActivity.getActivityType().getValue().toUpperCase().contains("DECOMMISSION")){
							recommendedPartsList[i].setUsedQuantity(part.getOrderQuantity());
							recommendedPartsList[i].setNotUsedQuantity("0");
							recommendedPartsList[i].setDOAQuantity("0");
							LOGGER.debug(" for part ["+part.getPartNumber()+"] used="+part.getOrderQuantity()+" total="+part.getOrderQuantity());
						}else{
							int statusArr[]=getPartStatus(userenteredpartList,part.getPartNumber());
							
							LOGGER.debug(" for part 1 ["+part.getPartNumber()+"] used="+statusArr[0]+" not used="+statusArr[1]+" defective on Arrival=" + statusArr[2]);
							recommendedPartsList[i].setUsedQuantity(String.valueOf(statusArr[0]));
							recommendedPartsList[i].setNotUsedQuantity(String.valueOf(statusArr[1]));
							recommendedPartsList[i].setDOAQuantity(String.valueOf(statusArr[2]));
						}
					}else{
						int statusArr[]=getPartStatus(userenteredpartList,part.getPartNumber());
						
						LOGGER.debug(" for part 2 ["+part.getPartNumber()+"] used="+statusArr[0]+" not used="+statusArr[1]+" defective on Arrival=" + statusArr[2]);
						recommendedPartsList[i].setUsedQuantity(String.valueOf(statusArr[0]));
						recommendedPartsList[i].setNotUsedQuantity(String.valueOf(statusArr[1]));
						recommendedPartsList[i].setDOAQuantity(String.valueOf(statusArr[2]));
					}
					
					
					i++;
					
				}
				
				
				
			wmDebriefDetails.setRecommendedPartsList(recommendedPartsList);
				
				
			}
			
		
		
		if(userEnteredactivity!=null && userEnteredactivity.getAdditionalPartList()!=null){
			LOGGER.debug(" additional part list is not null");
			
			List<PartLineItem> lastorderPartlist=userEnteredactivity.getAdditionalPartList();
			LOGGER.debug("Additional part list size "+lastorderPartlist.size());
			List<PartLineItem> addedOrderPartlist=new ArrayList<PartLineItem>();
			for(PartLineItem part:lastorderPartlist){
				if(addedOrderPartlist==null||!addedOrderPartlist.isEmpty()){
					LOGGER.debug("1st Addition to added order part list");
					PartLineItem newPart = new PartLineItem();
					newPart.setPartNumber(part.getPartNumber());
					newPart.setDescription(part.getDescription());
					addedOrderPartlist.add(newPart);
				}else if(!addedOrderPartlist.isEmpty()){
					int partCount = 0;
					for(PartLineItem addedPart:addedOrderPartlist){
						if(addedPart.getPartNumber().equalsIgnoreCase(part.getPartNumber())){
							partCount++;
						}
					}
					if(partCount==0){
						LOGGER.debug("New Addition to added order part list");
						PartLineItem newPart = new PartLineItem();
						newPart.setPartNumber(part.getPartNumber());
						newPart.setDescription(part.getDescription());
						addedOrderPartlist.add(newPart);
					}
				}
			}			
			LOGGER.debug("Added order Part List size 1 "+lastorderPartlist.size());
			AdditionalPartsList[] additionalPartsList= new AdditionalPartsList[lastorderPartlist.size()];
			int additionalCount=0;
			for(PartLineItem partToAdd:lastorderPartlist){
				LOGGER.debug("Added order Part List size 2 "+lastorderPartlist.size());
				int statusArr[]=getPartLineItemStatus(lastorderPartlist,partToAdd.getPartNumber());
				LOGGER.debug("status Arr Length"+statusArr.length);
				for(int k=0;k<statusArr.length;k++){
					LOGGER.debug("statusArr "+k+" value"+ statusArr[k]);
				}
				additionalPartsList[additionalCount]=new AdditionalPartsList();
				LOGGER.debug("Additional part partnumber "+partToAdd.getPartNumber());
				LOGGER.debug("Additional part Description "+checkStringBlank(partToAdd.getDescription()));
				LOGGER.debug("Additional Type Printer =" + partToAdd.isTypePrinter());
				additionalPartsList[additionalCount].setPartDescription((checkStringBlank(partToAdd.getDescription())));
				//additionalPartsList[i].setPartDisposition("");//Don't know what to send
				additionalPartsList[additionalCount].setPartNumber(checkStringBlank(partToAdd.getPartNumber()));
				int totalQty = statusArr[0]+statusArr[1]+statusArr[2];
				LOGGER.debug(" for part ["+partToAdd.getPartNumber()+"] used="+statusArr[0]+" not used="+statusArr[1]+" defective on Arrival=" + statusArr[2]+" Total="+totalQty+" Type Printer="+partToAdd.isTypePrinter());					
				additionalPartsList[additionalCount].setUsedQuantity(String.valueOf(statusArr[0]));
				additionalPartsList[additionalCount].setNotUsedQuantity(String.valueOf(statusArr[1]));
				additionalPartsList[additionalCount].setDOAQuantity(String.valueOf(statusArr[2]));
				additionalPartsList[additionalCount].setQuantity(String.valueOf(totalQty));
				additionalCount++;
			}
				
				
				/*try{
					orderQty=Integer.valueOf(part.getOrderQuantity());
				}catch(Exception e){
					LOGGER.debug(" error occured while parsing "+e.getCause());
				}
					
				for(int j=0;j<orderQty;j++){
					PartLineItem newPart=new PartLineItem();
					newPart.setDescription(part.getDescription());
					newPart.setPartNumber(part.getPartNumber());
					newPart.setStatus(part.getStatus());
					newPart.setOrderQuantity(part.getOrderQuantity());
					finalorderPartlist.add(newPart);
				}
				
			}
			Map<String,PartLineItem> partIdmap=new HashMap<String,PartLineItem>();
			for(PartLineItem part:finalorderPartlist){
				
				partIdmap.put(part.getPartNumber(),part);
			}
			
			Set<String> keys=partIdmap.keySet();
			AdditionalPartsList[] additionalPartsList= new AdditionalPartsList[keys.size()];
			i=0;
			for(String partNumber:keys){
				int statusArr[]=getPartLineItemStatus(finalorderPartlist,partNumber);
				additionalPartsList[i]=new AdditionalPartsList();
				LOGGER.debug("Additional part partnumber "+partNumber);
				LOGGER.debug("Additional part Description "+checkStringBlank(partIdmap.get(partNumber).getDescription()));
				additionalPartsList[i].setPartDescription(checkStringBlank(partIdmap.get(partNumber).getDescription()));
				//additionalPartsList[i].setPartDisposition("");//Don't know what to send
				additionalPartsList[i].setPartNumber(checkStringBlank(partIdmap.get(partNumber).getPartNumber()));
				
				LOGGER.debug(" for part ["+partNumber+"] used="+statusArr[0]+" not used="+statusArr[1]+" defective on Arrival=" + statusArr[2] +" total order quantity ="+statusArr[3]);
				additionalPartsList[i].setUsedQuantity(String.valueOf(statusArr[0]));
				additionalPartsList[i].setNotUsedQuantity(String.valueOf(statusArr[1]));
				additionalPartsList[i].setDOAQuantity(String.valueOf(statusArr[2]));
				additionalPartsList[i].setQuantity(String.valueOf(statusArr[3]));
				i++;
			}*/
			
			
			wmDebriefDetails.setAdditionalPartsList(additionalPartsList);
		}
		LOGGER.debug(" Edit populateDebriefDetails");
		return wmDebriefDetails;
	}
	
	/**
	 * @param oldActivity 
	 * @param userEnteredActivity 
	 * @param debriefStatus 
	 * @param timezoneOffset 
	 * @return string 
	 */
	private AssetManagementStatusDetails populateAssetManagementDetails(Activity oldActivity,Activity userEnteredActivity,String debriefStatus,float timezoneOffset){
		
		LOGGER.debug(" Enter populateAssetManagementDetails");
		AssetManagementStatusDetails assetManagementStatusDetails = new AssetManagementStatusDetails();
		ServiceRequest oldServiceRequest=oldActivity.getServiceRequest();
		ServiceRequest userEnteredServiceRequest=userEnteredActivity.getServiceRequest();
		
		LOGGER.debug("oldServiceRequest service request number " +oldServiceRequest.getServiceRequestNumber());
		assetManagementStatusDetails.setServiceRequestNumber(checkStringBlank(oldServiceRequest.getServiceRequestNumber()));
		LOGGER.debug(" oldActivity.getActivityId()" +oldActivity.getActivityId());
		assetManagementStatusDetails.setActivityID(checkStringBlank(oldActivity.getActivityId()));
		LOGGER.debug(" oldServiceRequest.getAccountName()" +oldServiceRequest.getAccountName());
		assetManagementStatusDetails.setAccountName(checkStringBlank(userEnteredActivity.getCustomerAccount().getAccountName()));
		
		assetManagementStatusDetails.setStoreFrontName(oldServiceRequest.getAsset()!=null?checkStringBlank(oldServiceRequest.getAsset().getStoreFrontName()):"");
		//below mapping changed for 12185
		assetManagementStatusDetails.setServiceRequestStatusDetail(checkStringBlank(userEnteredActivity.getActivitySubStatus().getValue()));
		//assetManagementStatusDetails.setServiceRequestStatusOverall("");//need to ask what to send.
		
		assetManagementStatusDetails.setEstimatedTimeOfArrivalDate(converDateToGMTString(userEnteredActivity.getEstimatedArrivalTime(),timezoneOffset));
		LOGGER.debug("userEnteredActivity.getEstimatedArrivalTime()" +userEnteredActivity.getEstimatedArrivalTime());
		assetManagementStatusDetails.setComments(checkStringBlank(userEnteredActivity.getDebrief().getProblemDescription()));
		LOGGER.debug("userEnteredActivity.getDebrief().getProblemDescription())" +userEnteredActivity.getDebrief().getProblemDescription());
		/*assetManagementStatusDetails.setStatusDateTime("");
		assetManagementStatusDetails.setReasonForStatus("");
		assetManagementStatusDetails.setEstimatedTimeOfArrivalDate("");
		assetManagementStatusDetails.setDeferredAppointmentDate("");
		assetManagementStatusDetails.setEstimatedCallbackDate("");
		assetManagementStatusDetails.setShipmentTrackingNumber("");
		assetManagementStatusDetails.setServiceProviderName("");*/
		assetManagementStatusDetails.setServiceRequestType(oldActivity.getActivityType()!=null?checkStringBlank(oldActivity.getActivityType().getValue()):"");
		/*assetManagementStatusDetails.setRequestedService("");
		assetManagementStatusDetails.setCustomerReferenceNumber("");*/
		assetManagementStatusDetails.setServicePartnerReferenceNumber(checkStringBlank(userEnteredActivity.getServiceProviderReferenceNumber()));
		/*assetManagementStatusDetails.setProjectName("");
		assetManagementStatusDetails.setProjectPhase("");
		assetManagementStatusDetails.setComments("");*/
		//assetManagementStatusDetails.setAssetInformation(populateAssetInformation(userEnteredServiceRequest.getAsset(),timezoneOffset));
		ActivityDetails[] activityDetailsList = new ActivityDetails[1];
		ActivityDetails activityDetails = new ActivityDetails();
		activityDetails.setAssetInformation(populateAssetInformation(userEnteredServiceRequest.getAsset(),timezoneOffset));
	

		LOGGER.debug("SR TYPE IS-----------?>"+getSrType());
		activityDetails.setActivityType(getSrType());
		if(getSrType()!=null && getSrType().toUpperCase().contains("INSTALL/DECOMMISSION")){
			LOGGER.debug("");
		SiebelAssetManagementInformation deinstallInfo = new SiebelAssetManagementInformation();
		LOGGER.debug("Asset tag--->>>"+userEnteredActivity.getServiceRequest().getAsset().getDeinstAssetTag());
		LOGGER.debug("Asset sn--->>>"+userEnteredActivity.getServiceRequest().getAsset().getDeinstSerialNumber());
		LOGGER.debug("Asset model--->>>"+userEnteredActivity.getServiceRequest().getAsset().getDeinstModel());
		LOGGER.debug("Asset part--->>>"+userEnteredActivity.getServiceRequest().getAsset().getDeinstPartNumber());
		LOGGER.debug("Asset brand--->>>"+userEnteredActivity.getServiceRequest().getAsset().getDeinstBrand());
		
		LOGGER.debug("Asset wc--->>>"+userEnteredActivity.getServiceRequest().getAsset().getDeviceCondition());
		LOGGER.debug("Asset date--->>>"+userEnteredActivity.getServiceRequest().getAsset().getDeinstRemovalDate());
		LOGGER.debug("Asset comments--->>>"+userEnteredActivity.getServiceRequest().getAsset().getDeinstComments());
		LOGGER.debug("Asset ip add--->>>"+userEnteredActivity.getServiceRequest().getAsset().getDeinstIpAddress());
		LOGGER.debug("Asset ip add--->>>"+userEnteredActivity.getServiceRequest().getAsset().getDeinstHostName());
		RelatedActivityInformation[] relatedActivityInformationList = new RelatedActivityInformation[1];
		RelatedActivityInformation relatedActivityInformation = new RelatedActivityInformation();
		/* Adding DeInstalled Asset Info Starts */
		Asset deinstalledAsset=userEnteredActivity.getServiceRequest().getAsset();
			deinstallInfo.setSerialNumber((deinstalledAsset.getDeinstSerialNumber()!=null || deinstalledAsset.getDeinstSerialNumber()!="") ? deinstalledAsset.getDeinstSerialNumber():"");
			deinstallInfo.setProductModel((deinstalledAsset.getDeinstModel()!=null || deinstalledAsset.getDeinstModel()!="") ? deinstalledAsset.getDeinstModel():"");
			deinstallInfo.setBrand((deinstalledAsset.getDeinstBrand()!=null || deinstalledAsset.getDeinstBrand()!="") ? deinstalledAsset.getDeinstBrand():"");
			deinstallInfo.setProductNumber((deinstalledAsset.getDeinstPartNumber()!=null || deinstalledAsset.getDeinstPartNumber()!="") ? deinstalledAsset.getDeinstPartNumber():"");
			deinstallInfo.setHostName((deinstalledAsset.getDeinstHostName()!=null || deinstalledAsset.getDeinstHostName()!="") ? deinstalledAsset.getDeinstHostName():"");
			deinstallInfo.setAssetTag((deinstalledAsset.getDeinstAssetTag()!=null || deinstalledAsset.getDeinstAssetTag()!="") ? deinstalledAsset.getDeinstAssetTag():"");
			deinstallInfo.setIPAddress((deinstalledAsset.getDeinstIpAddress()!=null || deinstalledAsset.getDeinstIpAddress()!="") ? deinstalledAsset.getDeinstIpAddress():"");
			deinstallInfo.setWorkingCondition((deinstalledAsset.getDeviceCondition()!=null || deinstalledAsset.getDeviceCondition()!="") ? deinstalledAsset.getDeviceCondition():"");
			deinstallInfo.setInstallDate(converDateToGMTString(deinstalledAsset.getDeinstRemovalDate(),timezoneOffset));
			
			List<PageCounts> portalDeInstPageCounts=deinstalledAsset.getDeInstAssetPageCounts();
			
			
			if(portalDeInstPageCounts!=null && !portalDeInstPageCounts.isEmpty()){
				int pageCountSize=0;
				for(PageCounts pc:portalDeInstPageCounts){
					if(StringUtils.isNotBlank(pc.getType())){
						pageCountSize++;
					}
				}
				SiebelMeterReadInformation[] wmMeterReadInformationDeinst=new SiebelMeterReadInformation[pageCountSize];
				int i=0;
				for(PageCounts pc:portalDeInstPageCounts){
					
					if(StringUtils.isNotBlank(pc.getType())){
						wmMeterReadInformationDeinst[i]=new SiebelMeterReadInformation();
						wmMeterReadInformationDeinst[i].setMeterType(checkStringBlank(pc.getType()));
						String pageCount = "";
						if(pc.getCount()!=null){
							if(pc.getCount().contains(",")){				
								pageCount = pc.getCount().replaceAll(",","");
							}else{
								pageCount = pc.getCount();
							}
							LOGGER.debug("Count "+pageCount);
						}
						wmMeterReadInformationDeinst[i].setMeterValue(checkStringReturnZero(pageCount));
						i++;
					}
				}
				deinstallInfo.setMeterReadInformation(wmMeterReadInformationDeinst);
			}
			relatedActivityInformation.setAssetInformation(deinstallInfo);
			relatedActivityInformation.setActivityComments(deinstalledAsset.getDeinstComments());
			relatedActivityInformation.setActivityType(getSrType());
			relatedActivityInformationList[0]=relatedActivityInformation;	
		activityDetails.setRelatedActivityInformation(relatedActivityInformationList);
		
	}
		activityDetailsList[0]=activityDetails;
		assetManagementStatusDetails.setActivityDetails(activityDetailsList);
		/* Adding DeInstalled Asset Info Ends */
		
		//assetManagementStatusDetails.setPrimaryContact(populateContact(oldServiceRequest.getPrimaryContact()));
		//assetManagementStatusDetails.setSecondaryContact(populateContact(oldServiceRequest.getSecondaryContact()));
		assetManagementStatusDetails.setAdditionalAddresses(populateAdditionalAddresses(userEnteredServiceRequest.getContactInfoForDevice()));
		assetManagementStatusDetails.setAdditionalContacts(populateAdditionalContacts(userEnteredServiceRequest.getContactInfoForDevice()));
		//assetManagementStatusDetails.setAdditionalAddresses(populateAdditionalAddresses(userEnteredServiceRequest.getContactInfoForDevice()));
		assetManagementStatusDetails.setActivityNotes(populateActivityNotes(userEnteredActivity.getActivityNoteList(),timezoneOffset));
		assetManagementStatusDetails.setDebriefDetails(populateDebriefDetails(userEnteredActivity,oldActivity,debriefStatus,timezoneOffset));
		
		
		
		LOGGER.debug(" Exit populateAssetManagementDetails");
		
		
		return assetManagementStatusDetails;
	}
	
	/**
	 * @param asset 
	 * @param timezoneOffset 
	 * @return string 
	 */
	private SiebelAssetManagementInformation populateAssetInformation(Asset asset,float timezoneOffset){
		LOGGER.debug(" Enter populateAssetInformation");
		
		if(asset==null){
			LOGGER.debug("asset is nulll ");
			return null;
		}
		SiebelAssetManagementInformation wmAssetInformation=new SiebelAssetManagementInformation();
		//wmAssetInformation.setAssetId(checkStringBlank(asset.getAssetId()));

		wmAssetInformation.setSerialNumber(checkStringBlank(asset.getSerialNumber()).toUpperCase());//

		//wmAssetInformation.setAssetTag(asset.getAssetTag());

		wmAssetInformation.setDeviceTag(checkStringBlank(asset.getDeviceTag()));//
		
		wmAssetInformation.setProductDescription(checkStringBlank(asset.getDescription()));


	/*	wmAssetInformation.setProductModel(asset.getProductTLI());

		wmAssetInformation.setProductNumber(asset.getProductNo());

		wmAssetInformation.setProductLine(asset.getProductLine());

		wmAssetInformation.setMachineTypeModel(asset.getMachineTypeModel());

		wmAssetInformation.setAssetType(asset.getAssetType());*/

		wmAssetInformation.setAssetCostCenter(checkStringBlank(asset.getAssetCostCenter()));//

		wmAssetInformation.setAssetDepartmentName(checkStringBlank(asset.getDepartment()));//

		wmAssetInformation.setAssetDepartmentNumber(checkStringBlank(asset.getDepartmentId()));
		
			wmAssetInformation.setIPAddress(checkStringBlank(asset.getIpAddress()));//
		

		wmAssetInformation.setIPSubMask(checkStringBlank(asset.getSubnet()));//

		wmAssetInformation.setIPGateway(checkStringBlank(asset.getGateway()));//
		
		
		wmAssetInformation.setIPV6(checkStringBlank(asset.getIpV6()));
		
		

		wmAssetInformation.setMACAddress(checkStringBlank(asset.getMacAddress()));//

		wmAssetInformation.setHostName(checkStringBlank(asset.getHostName()));//

		//wmAssetInformation.setComputerName(asset.getComputerName());

		wmAssetInformation.setNetworkConnected(asset.getNetworkConnectedFlag()==true?"Y":"N");//

		wmAssetInformation.setPortNumber(checkStringBlank(asset.getPortNumber()));//

		wmAssetInformation.setWiringClosetNumber(checkStringBlank(asset.getWiringClosestNetworkPoint()));//

		wmAssetInformation.setFaxConnected(checkStringBlank(asset.getFaxConnectedValue()));//

		wmAssetInformation.setFaxPortNumber(checkStringBlank(asset.getFaxPortNumber()));//

		wmAssetInformation.setOperatingSystem(checkStringBlank(asset.getOperatingSystem()));
		wmAssetInformation.setOperatingSystemVersion(checkStringBlank(asset.getOperatingSystemVersion()));//

		wmAssetInformation.setFirmware(checkStringBlank(asset.getFirmware()));//

		wmAssetInformation.setWorkingCondition(checkStringBlank(asset.getPrinterWorkingCondition()));//

		wmAssetInformation.setNetworkTopology(checkStringBlank(asset.getNetworkTopology()));//
/*
		wmAssetInformation.setPrintServer("");*/

		wmAssetInformation.setSpecialUsage(checkStringBlank(asset.getSpecialUsage()));

		//wmAssetInformation.setRelatedCustomerHierarchyLevel(checkStringBlank(asset.getChlNodeValue()));//

		wmAssetInformation.setInstallDate(converDateToGMTString(asset.getInstallDate(),timezoneOffset));//
		
		wmAssetInformation.setInstalledAtAddress(populateInstallAddress(asset.getInstallAddress()));

	    //private com.lexmark.SiebelShared.debriefs.MeterReadInformation[] meterReadInformation;
		
		/*Changes for 11599 MPS 2.1
		 *Asset field 1 won't be sent as per Joan.
		 * */
		//wmAssetInformation.setCustomerAssetData1(checkStringBlank(asset.getAssetField1()));
		wmAssetInformation.setCustomerAssetData2(checkStringBlank(asset.getAssetField2()));
		wmAssetInformation.setCustomerAssetData3(checkStringBlank(asset.getAssetField3()));
		/*Ends Changes for 11599 MPS 2.1*/
		
		List<PageCounts> portalPageCounts=asset.getPageCounts();
		
		
		if(portalPageCounts!=null && !portalPageCounts.isEmpty()){
			int pageCountSize=0;
			for(PageCounts pc:portalPageCounts){
				if(StringUtils.isNotBlank(pc.getType())){
					pageCountSize++;
				}
			}
			SiebelMeterReadInformation[] wmMeterReadInformation=new SiebelMeterReadInformation[pageCountSize];
			int i=0;
			for(PageCounts pc:portalPageCounts){
				
				if(StringUtils.isNotBlank(pc.getType())){
					wmMeterReadInformation[i]=new SiebelMeterReadInformation();
					wmMeterReadInformation[i].setMeterType(checkStringBlank(pc.getType()));
					String pageCount = "";
					if(pc.getCount()!=null){
						if(pc.getCount().contains(",")){				
							pageCount = pc.getCount().replaceAll(",","");
						}else{
							pageCount = pc.getCount();
						}
						LOGGER.debug("Count "+pageCount);
					}
					wmMeterReadInformation[i].setMeterValue(checkStringReturnZero(pageCount));
					i++;
				}
			}
			wmAssetInformation.setMeterReadInformation(wmMeterReadInformation);
		}
		
		LOGGER.debug(" Exit populateAssetInformation");
		
		return wmAssetInformation;
		
	}
	
	/**
	 * @param portalContacts 
	 * @return object 
	 */
	private AdditionalContacts[] populateAdditionalContacts(List<AccountContact> portalContacts){
		LOGGER.debug(" Enter populateAdditionalContacts");
		if(portalContacts==null){
			LOGGER.debug("portalContacts is null");
			
			return null;
		}else if (portalContacts.isEmpty()){
			LOGGER.debug("portalContacts is empty");
			
			return null;
		}
		int originalcount=0;
		for(AccountContact contact:portalContacts){
		
			if(StringUtils.isNotBlank(contact.getFirstName()) && StringUtils.isNotBlank(contact.getDeviceContactType())){
				originalcount++;
			}
		}
		
		
		AdditionalContacts[] wmAdditionalContacts=new AdditionalContacts[originalcount];
		int i=0;
		for(AccountContact contact:portalContacts){
			if(StringUtils.isNotBlank(contact.getFirstName()) && StringUtils.isNotBlank(contact.getDeviceContactType())){
				wmAdditionalContacts[i]=new AdditionalContacts();
				wmAdditionalContacts[i].setAddressId(contact.getAddress()!=null?checkStringBlank(contact.getAddress().getAddressId()):"");
				wmAdditionalContacts[i].setContactType(checkStringBlank(contact.getDeviceContactType()));
				wmAdditionalContacts[i].setContactId(checkStringBlank(contact.getContactId()));
				wmAdditionalContacts[i].setFirstName(checkStringBlank(contact.getFirstName()));
				wmAdditionalContacts[i].setLastName(checkStringBlank(contact.getLastName()));
				wmAdditionalContacts[i].setDepartment(checkStringBlank(contact.getDepartment()));
				wmAdditionalContacts[i].setWorkPhone(checkStringBlank(contact.getWorkPhone()));
				wmAdditionalContacts[i].setAlternatePhone(checkStringBlank(contact.getAlternatePhone()));
				wmAdditionalContacts[i].setEmailAddress(checkStringBlank(contact.getEmailAddress()));
				i++;
			}
		}
		LOGGER.debug(" Exit populateAdditionalContacts");
		return wmAdditionalContacts;
	}
	
	/*private AdditionalAddresses[] populateAdditionalAddress(List<GenericAddress> portalAddresses){
		LOGGER.debug(" Enter populateAdditionalAddresses");
		if(portalAddresses==null){
			LOGGER.debug("portalAddresses is null");
			
			return null;
		}else if (portalAddresses.isEmpty()){
			LOGGER.debug("portalAddresses is empty");
			
			return null;
		}
		AdditionalAddresses[] wmAdditionalAdresses=new AdditionalAddresses[portalAddresses.size()];
		
		int i=0;
		for(GenericAddress address:portalAddresses){
			wmAdditionalAdresses[i]=new AdditionalAddresses();
			SiebelAddress addressDetails = new SiebelAddress();
			addressDetails.setAddressId(address.getAddressId()!=null?checkStringBlank(address.getAddressId()):"");
			addressDetails.setPhysicalLocation1(address.getPhysicalLocation1()!=null?checkStringBlank(address.getPhysicalLocation1()):"");
			addressDetails.setPhysicalLocation2(address.getPhysicalLocation2()!=null?checkStringBlank(address.getPhysicalLocation2()):"");
			addressDetails.setPhysicalLocation3(address.getPhysicalLocation3()!=null?checkStringBlank(address.getPhysicalLocation3()):"");
			addressDetails.setAddressLine1("");
			addressDetails.setCity("");
			wmAdditionalAdresses[i].setAddressDetails(addressDetails);
			i++;
		}
		LOGGER.debug(" Exit populateAdditionalContacts");
		return wmAdditionalAdresses;
	}*/
	
	
	
	/**
	 * @param activityNote 
	 * @param timezoneOffset 
	 * @return array 
	 */
	private SiebelActivityNotes[] populateActivityNotes(List<ActivityNote> activityNote,float timezoneOffset){
		LOGGER.debug(" Enter populateActivityNotes");
		if(activityNote==null){
			LOGGER.debug("activity notes is null");
			
			return null;
		}else if(activityNote.isEmpty()){
			LOGGER.debug("activity notes is empty");
			
			return null;
		}
		SiebelActivityNotes[] actNotes=new SiebelActivityNotes[activityNote.size()];
		int i=0;
		for(ActivityNote notePortal:activityNote){
			SiebelActivityNotes notes=new SiebelActivityNotes();
			NoteAuthor author=new NoteAuthor();
			AccountContact authorPortal=notePortal.getNoteAuthor();
			author.setContactId(checkStringBlank(authorPortal.getContactId()));
			author.setEmailAddress(checkStringBlank(authorPortal.getEmailAddress()));
			author.setFirstName(checkStringBlank(authorPortal.getFirstName()));
			author.setLastName(checkStringBlank(authorPortal.getLastName()));
			notes.setNoteAuthor(author);
			notes.setNoteDate(converDateToGMTString(notePortal.getNoteDate(),timezoneOffset));
			notes.setNoteId(checkStringReturnNew(notePortal.getNoteId()));
			notes.setNoteDetails(checkStringBlank(notePortal.getNoteDetails()));
			actNotes[i++]=notes;
			
		}
		LOGGER.debug(" Exit populateActivityNotes");
		return actNotes;
	}
	
	
	
	/**
	 * @param contact 
	 * @return object 
	 */
	private SiebelContact populateContact(AccountContact contact){
		
		LOGGER.debug(" Enter populatePrimaryContact");
		
		
		if(contact==null){
			LOGGER.debug("contact is null");
			return null;
		}else if(StringUtils.isBlank(contact.getContactId()) || StringUtils.isBlank(contact.getFirstName())){
			LOGGER.debug("contact ID or firstname is null");
			return null;
		}
		SiebelContact pc=new SiebelContact();	
		pc.setContactId(checkStringBlank(contact.getContactId()));
		pc.setFirstName(checkStringBlank(contact.getFirstName()));
		pc.setLastName(checkStringBlank(contact.getLastName()));
		pc.setDepartment(checkStringBlank(contact.getDepartment()));
		pc.setWorkPhone(checkStringBlank(contact.getWorkPhone()));
		pc.setAlternatePhone(checkStringBlank(contact.getAlternatePhone()).replace("undefined", ""));
		pc.setEmailAddress(checkStringBlank(contact.getEmailAddress()));
		
		LOGGER.debug(" Exit populatePrimaryContact");
		return pc;

	}
	
	/**
	 * @param contact 
	 * @return object 
	 */
	private SiebelContact populateSecondaryContact(AccountContact contact){
		
		LOGGER.debug(" Enter populateSecondaryContact");
		
		if(contact==null){
			LOGGER.debug("contact is null");
			return null;
		}else if(StringUtils.isBlank(contact.getContactId()) || StringUtils.isBlank(contact.getFirstName())){
			LOGGER.debug("contact ID or firstname is null");
			return null;
		}
		SiebelContact pc=new SiebelContact();
		
		pc.setContactId(checkStringBlank(contact.getContactId()));
		pc.setFirstName(checkStringBlank(contact.getFirstName()));
		pc.setLastName(checkStringBlank(contact.getLastName()));
		pc.setDepartment(checkStringBlank(contact.getDepartment()));
		pc.setWorkPhone(checkStringBlank(contact.getWorkPhone()));
		pc.setAlternatePhone(checkStringBlank(contact.getAlternatePhone()));
		pc.setEmailAddress(checkStringBlank(contact.getEmailAddress()));
		LOGGER.debug(" Exit populateSecondaryContact");
		return pc;

	}
	
	/**
	 * @param contacts 
	 * @return object 
	 */
	private AdditionalAddresses[] populateAdditionalAddresses(List<AccountContact> contacts){
		LOGGER.debug(" Enter populateAdditionalAddresses");
		if(contacts==null){
			LOGGER.debug(" list of contacts is null ");
			return null;
		}
		int originalcount=0;
		for(AccountContact contact:contacts){
		
			if(StringUtils.isNotBlank(contact.getFirstName()) && StringUtils.isNotBlank(contact.getDeviceContactType())){
				originalcount++;
			}
		}
	
		AdditionalAddresses[] additionalAddresses = new AdditionalAddresses[originalcount];
		int i=0;
		for(AccountContact contact:contacts){
			if(StringUtils.isNotBlank(contact.getFirstName())&& StringUtils.isNotBlank(contact.getDeviceContactType())){
				
				GenericAddress address=contact.getAddress();
				SiebelAddress wmAddressDetails=populateAddress(address);
				AdditionalAddresses wmAdditionalAddress=new AdditionalAddresses();
				wmAdditionalAddress.setAddressDetails(wmAddressDetails);
				additionalAddresses[i++]=wmAdditionalAddress;
			}
		}
		LOGGER.debug(" Exit populateAdditionalAddresses");
		return additionalAddresses;
		
	}
	
	/**
	 * @param address 
	 * @return object 
	 */ 
	private SiebelAddress populateInstallAddress(GenericAddress address){
		LOGGER.debug(" Enter populateInstallAddress");
		
		
		if(address==null){
			LOGGER.debug("address is null");
			return null;
		}else if(StringUtils.isBlank(address.getAddressLine1())){
			LOGGER.debug("address line 1 is null");
			return null;
		}
		SiebelAddress addressDetails=new SiebelAddress();
		
		addressDetails.setAddressCleansedFlag(address.getIsAddressCleansed()==true?"Y":"N");
		addressDetails.setAddressId(checkStringBlank(address.getAddressId()));
		addressDetails.setAddressLine1(checkStringBlank(address.getAddressLine1()));
		addressDetails.setAddressLine2(checkStringBlank(address.getAddressLine2()));
		addressDetails.setAddressMessage(checkStringBlank(address.getSavedErrorMessage()));
		addressDetails.setCity(checkStringBlank(address.getCity()));
		addressDetails.setCountry(checkStringBlank(address.getCountry()));
		addressDetails.setCountryCode(checkStringBlank(address.getCountryISOCode()));
		addressDetails.setCounty(checkStringBlank(address.getCounty()));
		addressDetails.setDistrict(checkStringBlank(address.getDistrict()));
		addressDetails.setHouseNumber(checkStringBlank(address.getOfficeNumber()));
		addressDetails.setLatitude(checkStringBlank(address.getLatitude()));
		addressDetails.setLongitude(checkStringBlank(address.getLongitude()));
		addressDetails.setPhysicalLocation1(checkStringBlank(address.getPhysicalLocation1()));
		addressDetails.setPhysicalLocation2(checkStringBlank(address.getPhysicalLocation2()));
		addressDetails.setPhysicalLocation3(checkStringBlank(address.getPhysicalLocation3()));
		addressDetails.setPostalCode(checkStringBlank(address.getPostalCode()));
		addressDetails.setProvince(checkStringBlank(address.getProvince()));
		addressDetails.setRegion(checkStringBlank(address.getRegion()));
		addressDetails.setStateCode(checkStringBlank(address.getStateCode()));
		addressDetails.setStateFullName(checkStringBlank(address.getStateFullName()));
		
		addressDetails.setGridCoordinateX(checkStringBlank(address.getCoordinatesXPreDebriefRFV()));
		addressDetails.setGridCoordinateY(checkStringBlank(address.getCoordinatesYPreDebriefRFV()));
		
		addressDetails.setBuildingId(checkStringBlank(address.getBuildingId()));
		addressDetails.setFloorId(checkStringBlank(address.getFloorId()));
		
		
		
		LOGGER.debug(" Exit populateInstallAddress");
		return addressDetails;
	}
	
	/**
	 * @param address 
	 * @return object 
	 */ 
	private SiebelAddress populateAddress(GenericAddress address){
		LOGGER.debug(" Enter  populateAddress");
		SiebelAddress addressDetails=new SiebelAddress();
		
		if(address==null){
			LOGGER.debug("address is null");
			return addressDetails;
		}
	addressDetails.setAddressLine1("");
	addressDetails.setCity("");	
	addressDetails.setPhysicalLocation1(address.getPhysicalLocation1()!=null?checkStringBlank(address.getPhysicalLocation1()):"");
	addressDetails.setPhysicalLocation2(address.getPhysicalLocation2()!=null?checkStringBlank(address.getPhysicalLocation2()):"");
	addressDetails.setPhysicalLocation3(address.getPhysicalLocation3()!=null?checkStringBlank(address.getPhysicalLocation3()):"");
	LOGGER.debug(" Exit populateAddress");
	
	return addressDetails;
	}
	
	/**
	 * @param val 
	 * @return object 
	 */
	private String checkStringBlank(String val){
		if(StringUtils.isNotBlank(val)){
			return val;
		}else{
			return "";
		}
	}
	
	/**
	 * @param val 
	 * @return object 
	 */
	private String checkStringReturnNew(String val){
		if(StringUtils.isNotBlank(val)){
			return val;
		}else{
			return "NEW";
		}
	}
	
	/**
	 * @param val 
	 * @return object 
	 */
	private String checkStringReturnZero(String val){
		if(StringUtils.isNotBlank(val)){
			return val;
		}else{
			return "0";
		}
	}
	
	/**
	 * @param userenteredpartList 
	 * @param partNumber 
	 * @return int 
	 */
	private int[] getPartStatus(List<Part> userenteredpartList,String partNumber){
		int used=0;//used - status 
		int notUsed=0;//not used - status
		int doA=0;//defective on arrival - status
		int orderQuantity=0;
		for(Part userEnteredPart:userenteredpartList){
			LOGGER.debug(" userenteredpartList Size "+userenteredpartList.size());
			if(userEnteredPart.getPartNumber().equalsIgnoreCase(partNumber)){
				try{
					orderQuantity+=Integer.parseInt(userEnteredPart.getOrderQuantity());
				}catch(Exception e ){
					LOGGER.debug(" [ Exception occured on parsing Integer quantity]"+e.getCause());
					orderQuantity+=0;
				}
				
				if("used".equalsIgnoreCase(userEnteredPart.getStatus())){
					used = used+Integer.parseInt(userEnteredPart.getOrderQuantity());
				}else if("not used".equalsIgnoreCase(userEnteredPart.getStatus())){
					notUsed=notUsed+Integer.parseInt(userEnteredPart.getOrderQuantity());						
				}else if("defective on arrival".equalsIgnoreCase(userEnteredPart.getStatus())){
					doA=doA+Integer.parseInt(userEnteredPart.getOrderQuantity());
				}else{
					LOGGER.debug(" part status is coming blank");
				}
			}
		}
		int statusArr[]=new int[4];
		statusArr[0]=used;
		statusArr[1]=notUsed;
		statusArr[2]=doA;
		statusArr[3]=orderQuantity;
		LOGGER.debug("total orderQuantity "+orderQuantity+"for part "+partNumber);
		return statusArr;
		
	}
	
	/**
	 * @param userenteredpartList 
	 * @param partNumber 
	 * @return int 
	 */
	private int[] getPartLineItemStatus(List<PartLineItem> userenteredpartList,String partNumber){
		int used=0;//used - status 
		int notUsed=0;//not used - status
		int doA=0;//defective on arrival - status
		for(PartLineItem userEnteredPart:userenteredpartList){
			LOGGER.debug(" userenteredpartList Size "+userenteredpartList.size());
			if(userEnteredPart.getPartNumber().equalsIgnoreCase(partNumber)){
				
				LOGGER.debug("userEnteredPart.getStatus()"+userEnteredPart.getStatus());
				if("used".equalsIgnoreCase(userEnteredPart.getStatus())){
					used = used+Integer.parseInt(userEnteredPart.getOrderQuantity());
				}else if("not used".equalsIgnoreCase(userEnteredPart.getStatus())){
					notUsed=notUsed+Integer.parseInt(userEnteredPart.getOrderQuantity());						
				}else if("defective on arrival".equalsIgnoreCase(userEnteredPart.getStatus())){
					doA=doA+Integer.parseInt(userEnteredPart.getOrderQuantity());	
				}else{
					LOGGER.debug(" part status is coming blank");
				}
			}
		}
		
		LOGGER.debug(" Used"+used+" not used"+notUsed+"DOA "+doA);
		int statusArr[]=new int[3];
		statusArr[0]=used;
		statusArr[1]=notUsed;
		statusArr[2]=doA;
		return statusArr;
		
	}
	
	
	/**
	 * @return string 
	 */
	private Calendar getCurrentDateTime() {
		LOGGER.debug(" Enter  getCurrentDateTime");
		Calendar cal = Calendar.getInstance();
		try {
			final String DATE_FORMAT_NOW = "yyyy-MM-dd HH:mm:ss";
			DateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
			String currentDate = sdf.format(cal.getTime());
			Date date=null;
			date = (Date) sdf.parse(currentDate);
			cal.setTime(date);
		} catch (Exception e) {
			
			throw new RuntimeException(e);
		}
		LOGGER.debug(" Exit  getCurrentDateTime");
		return cal;
	}

	/**
	 * @param address 
	 * @return string 
	 */
	private String generateAddressString(GenericAddress address){
		
		StringBuffer addressString=new StringBuffer();
		for(String s:LexmarkConstants.addressArr){
			Object obj=null;
			try {
				obj = PropertyUtils.getProperty(address, s);
			} catch (IllegalAccessException e) {
				LOGGER.debug(" Exception occured "+e.getMessage());
				LOGGER.error("error occured while reading the address field "+s);
			} catch (InvocationTargetException e) {
				LOGGER.debug(" Exception occured "+e.getMessage());
				LOGGER.error("error occured while reading the address field "+s);
			} catch (NoSuchMethodException e) {
				LOGGER.debug(" Exception occured "+e.getMessage());
				LOGGER.error("error occured while reading the address field "+s);
			}
			if(obj!=null){
				addressString.append((String)obj).append("|");
			}else{
				addressString.append("|");
			}
		}
		return addressString.toString();
	}
	/**
	 * @param localizedDate 
	 * @param timezoneOffset 
	 * @return string 
	 */
	private String converDateToGMTString(final Date localizedDate,float timezoneOffset){
		LOGGER.enter("HardwareDebriefServiceImpl.java", "converDateToGMTString");
		if(localizedDate==null){
			return "";
		}
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATEFORMAT);
		String dateString=simpleDateFormat.format(new Date(localizedDate.getTime() + 
				new BigDecimal(timezoneOffset).multiply(millsecondsInHour).intValue()));
		//String dateString=simpleDateFormat.format(localizedDate);
		LOGGER.debug("date String ="+(dateString));
		LOGGER.exit("HardwareDebriefServiceImpl.java", "converDateToGMTString");
		return dateString;
		
	}
	
	/**
	 * @param sourceSystem 
	 */
	public void setSourceSystem(String sourceSystem) {
		this.sourceSystem = sourceSystem;
	}


	/** 
	 * @return string 
	 */
	public String getSourceSystem() {
		return sourceSystem;
	}


	/**
	 * @param businessProcess 
	 */
	public void setBusinessProcess(String businessProcess) {
		this.businessProcess = businessProcess;
	}


	/**
	 * @return string 
	 */
	public String getBusinessProcess() {
		return businessProcess;
	}


	/**
	 * @param objectActiontypeChange 
	 */
	public void setObjectActiontypeChange(String objectActiontypeChange) {
		this.objectActiontypeChange = objectActiontypeChange;
	}


	/**
	 * @return string 
	 */
	public String getObjectActiontypeChange() {
		return objectActiontypeChange;
	}


	/**
	 * @param integrationFrequency 
	 */
	public void setIntegrationFrequency(String integrationFrequency) {
		this.integrationFrequency = integrationFrequency;
	}


	/**
	 * @return string 
	 */
	public String getIntegrationFrequency() {
		return integrationFrequency;
	}


	/**
	 * @param senderID 
	 */
	public void setSenderID(String senderID) {
		this.senderID = senderID;
	}


	/**
	 * @return string 
	 */
	public String getSenderID() {
		return senderID;
	}


	/**
	 * @param senderName 
	 */
	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}


	/**
	 * @return string 
	 */
	public String getSenderName() {
		return senderName;
	}


	/**
	 * @param receiverId 
	 */
	public void setReceiverId(String receiverId) {
		this.receiverId = receiverId;
	}


	/**
	 * @return string 
	 */
	public String getReceiverId() {
		return receiverId;
	}


	public void setSrType(String srType) {
		this.srType = srType;
	}


	public String getSrType() {
		return srType;
	}
	

}
