package com.lexmark.service.impl.mock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import com.lexmark.contract.ActivityDetailContract;
import com.lexmark.contract.ActivityListContract;
import com.lexmark.contract.AssignedTechnicianUpdateContract;
import com.lexmark.contract.ClaimDetailContract;
import com.lexmark.contract.CustomerAccountListContract;
import com.lexmark.contract.FRUPartDetailContract;
import com.lexmark.contract.FRUPartListContract;
import com.lexmark.contract.GlobalAssetDetailContract;
import com.lexmark.contract.GlobalPartnerAssetListContract;
import com.lexmark.contract.OpenClaimListContract;
import com.lexmark.contract.PartnerAddressListContract;
import com.lexmark.contract.PartnerFavoriteAddressUpdateContract;
import com.lexmark.contract.PartnerIndirectAccountListContract;
import com.lexmark.contract.PartnerNotificationsContract;
import com.lexmark.contract.ServiceActivityHistoryDetailContract;
import com.lexmark.contract.ServiceActivityHistoryListContract;
import com.lexmark.contract.TechnicianListContract;
import com.lexmark.contract.ValidateInstalledPrinterSerialNumberContract;
import com.lexmark.contract.source.OrderListContract;
import com.lexmark.contract.source.RequestAcceptContract;
import com.lexmark.domain.Account;
import com.lexmark.domain.AccountContact;
import com.lexmark.domain.Activity;
import com.lexmark.domain.Asset;
import com.lexmark.domain.DownloadClaim;
import com.lexmark.domain.DownloadRequest;
import com.lexmark.domain.GenericAddress;
import com.lexmark.domain.ListOfValues;
import com.lexmark.domain.Part;
import com.lexmark.domain.ServiceRequest;
import com.lexmark.domain.ServiceRequestActivity;
import com.lexmark.enums.RequestStatusEnum;
import com.lexmark.exceptionimpl.checked.LGSCheckedException;
import com.lexmark.exceptionimpl.runtime.LGSRuntimeException;
import com.lexmark.result.ActivityDetailResult;
import com.lexmark.result.ActivityListResult;
import com.lexmark.result.AssignedTechnicianUpdateResult;
import com.lexmark.result.ClaimDetailResult;
import com.lexmark.result.CustomerAccountListResult;
import com.lexmark.result.DownloadClaimListResult;
import com.lexmark.result.DownloadRequestListResult;
import com.lexmark.result.FRUPartDetailResult;
import com.lexmark.result.FRUPartListResult;
import com.lexmark.result.GlobalAssetDetailResult;
import com.lexmark.result.GlobalPartnerAssetListResult;
import com.lexmark.result.OpenClaimListResult;
import com.lexmark.result.PartnerAddressListResult;
import com.lexmark.result.PartnerClaimCreateIdResult;
import com.lexmark.result.PartnerFavoriteAddressUpdateResult;
import com.lexmark.result.PartnerIndirectAccountListResult;
import com.lexmark.result.PartnerNotificationsResult;
import com.lexmark.result.ServiceActivityHistoryDetailResult;
import com.lexmark.result.ServiceActivityHistoryListResult;
import com.lexmark.result.TechnicianListResult;
import com.lexmark.result.ValidateInstalledPrinterSerialNumberResult;
import com.lexmark.result.source.RequestAcceptResult;
import com.lexmark.service.api.PartnerRequestsService;
import com.lexmark.service.impl.real.enums.QueryType;
import com.lexmark.util.CollectionFilter;
import com.lexmark.util.CollectionSorter;

public class PartnerRequestsServiceImpl implements PartnerRequestsService {
	private static Logger logger = LogManager.getLogger(PartnerRequestsServiceImpl.class);


	private List<Part> PART_LIST = new ArrayList<Part>();
	private static List<GenericAddress> genericAddresses = new ArrayList<GenericAddress>();

	static {
		genericAddresses = PartnerDomainMockDataGenerator.getGenericAddressList();
	}

	@Override
	public ActivityListResult retrieveActivityList(ActivityListContract contract) throws Exception {
		final ActivityListResult activityListResult = new ActivityListResult();
		final List<Activity> activities = new ArrayList<Activity>();
		for (Activity activity : PartnerDomainMockDataGenerator.getActivities()) {
			if (isMatchCondition(contract, activity)) {
				activities.add(activity);
			}
		}

		final List<Activity> sortedActivities = sortAndFilter(activities, contract);
		if (contract.getIncrement() == -1) {
			activityListResult.setActivityList(sortedActivities);
			activityListResult.setTotalcount(sortedActivities.size());
			return activityListResult;
		}

		int toIndex = contract.getStartRecordNumber() + contract.getIncrement();
		toIndex = toIndex < sortedActivities.size() ? toIndex : sortedActivities.size();
		List<Activity> subActivities = sortedActivities.subList(contract.getStartRecordNumber(), toIndex);
		activityListResult.setActivityList(subActivities);
		activityListResult.setTotalcount(sortedActivities.size());
		return activityListResult;
	}

	@SuppressWarnings("unchecked")
	private List<Activity> sortAndFilter(List<Activity> activities, ActivityListContract contract) {
		String sortCriteriaStr = null;
		List<String> lovAttributeList = Arrays.asList("Activity.serviceRequest.serviceRequestType", "Activity.activityStatus", "Activity.activityType", "Activity.activitySubStatus");
		
		for (String sort : contract.getSortCriteria().keySet()) {
			sortCriteriaStr = updateSortingFieldForLOV(sort, lovAttributeList) +
					":" + contract.getSortCriteria().get(sort);
			break;
		}
		
		Date tempStartDate = (Date)contract.getFilterCriteria().get("Activity.activityDate.startDate");
		Date tempEndDate = (Date)contract.getFilterCriteria().get("Activity.activityDate.endDate");
		contract.getFilterCriteria().remove("Activity.activityDate.startDate");
		contract.getFilterCriteria().remove("Activity.activityDate.endDate");

		CollectionFilter filter = new CollectionFilter(Locale.getDefault());
		CollectionSorter sorter = new CollectionSorter();
		Map<String, Object> filterCriteria = new HashMap<String, Object>();
		filterCriteria = filter.updateFilterCriteriaForLOV(contract.getFilterCriteria(), lovAttributeList);
		
		List<Activity> filterResult = filter.filter(activities, filterCriteria, contract
				.getSearchCriteria());
		
		contract.getFilterCriteria().put("Activity.activityDate.startDate", tempStartDate);
		contract.getFilterCriteria().put("Activity.activityDate.endDate", tempEndDate);

		return sorter.sort(filterResult, sortCriteriaStr);
	}
	
	//Added By MPS Offshore Team
	@SuppressWarnings("unchecked")
	private List<Activity> sortAndFilterForOrder(List<Activity> activities, OrderListContract contract) {
		String sortCriteriaStr = null;
		List<String> lovAttributeList = Arrays.asList("Activity.serviceRequest.serviceRequestType", "Activity.activityStatus", "Activity.activityType", "Activity.activitySubStatus");
		
		for (String sort : contract.getSortCriteria().keySet()) {
			sortCriteriaStr = updateSortingFieldForLOV(sort, lovAttributeList) +
					":" + contract.getSortCriteria().get(sort);
			break;
		}
		
		Date tempStartDate = (Date)contract.getFilterCriteria().get("Activity.activityDate.startDate");
		Date tempEndDate = (Date)contract.getFilterCriteria().get("Activity.activityDate.endDate");
		contract.getFilterCriteria().remove("Activity.activityDate.startDate");
		contract.getFilterCriteria().remove("Activity.activityDate.endDate");

		CollectionFilter filter = new CollectionFilter(Locale.getDefault());
		CollectionSorter sorter = new CollectionSorter();
		Map<String, Object> filterCriteria = new HashMap<String, Object>();
		filterCriteria = filter.updateFilterCriteriaForLOV(contract.getFilterCriteria(), lovAttributeList);
		
		List<Activity> filterResult = filter.filter(activities, filterCriteria, contract
				.getSearchCriteria());
		
		contract.getFilterCriteria().put("Activity.activityDate.startDate", tempStartDate);
		contract.getFilterCriteria().put("Activity.activityDate.endDate", tempEndDate);

		return sorter.sort(filterResult, sortCriteriaStr);
	}
	
	private String updateSortingFieldForLOV(String sortingField, List<String> lovAttributeList) {
		if (lovAttributeList.contains(sortingField)) {
			return sortingField + ".value";
		}
		return sortingField;
	}
	
	private boolean isMatchCondition(ActivityListContract contract, Activity activity) {
		boolean matchStatus = true;
		if (StringUtils.isNotEmpty(contract.getStatus()) && !"All".equalsIgnoreCase(contract.getStatus()))
			matchStatus = contract.getStatus().equalsIgnoreCase(activity.getActivityStatus().getValue());
		boolean matchRequestType = true;
		if (StringUtils.isNotEmpty(contract.getServiceRequestType())
				&& !"All".equalsIgnoreCase(contract.getServiceRequestType())) {
			matchRequestType = contract.getServiceRequestType().equalsIgnoreCase(
					activity.getServiceRequest().getServiceRequestType().getValue());
		}

		boolean matchQueryType = true;
		if (QueryType.Unassigned.name().equalsIgnoreCase(contract.getQueryType())) {
			matchQueryType = activity.getTechnician() == null;
		} else if (QueryType.My.name().equalsIgnoreCase(contract.getQueryType())) {
			// TODO
		}
		
		boolean matchActivityDate = true;
		if (activity.getActivityDate().before((Date)contract.getFilterCriteria().get("Activity.activityDate.startDate")) ||
				activity.getActivityDate().after((Date)contract.getFilterCriteria().get("Activity.activityDate.endDate"))) {
			matchActivityDate = false;
		}

		return matchStatus && matchRequestType && matchQueryType && matchActivityDate;
	}
	
	@Override
	public AssignedTechnicianUpdateResult updateAssignedTechnician(AssignedTechnicianUpdateContract contract)
			throws Exception {
		// TODO: get Siebel Employee ID from ldapUserResult.contactId
		final String activityId = contract.getActivityId();
		for (Activity activity : PartnerDomainMockDataGenerator.getActivities()) {
			if (activityId.equals(activity.getActivityId())) {
				AccountContact accountContact = new AccountContact();
				accountContact.setFirstName("ye");
				accountContact.setLastName("malachi");
				activity.setTechnician(accountContact);
				break;
			}
		}
		AssignedTechnicianUpdateResult result = new AssignedTechnicianUpdateResult();
		result.setResult(true);
		return result;
	}
	
	private static ListOfValues createListOfValues(String type, String value) {
		ListOfValues listOfValues = new ListOfValues();
		listOfValues.setType(type);
		listOfValues.setValue(value);
		return listOfValues;
	}

	@Override
	public OpenClaimListResult retrieveOpenClaimList(OpenClaimListContract contract) throws Exception {
		OpenClaimListResult result = new OpenClaimListResult();
		String assetId = contract.getAssetId();

		for (Activity claim : PartnerDomainMockDataGenerator.getActivities()) {
			if (assetId.equals(claim.getServiceRequest().getAsset().getAssetId()) &&
					!RequestStatusEnum.COMPLETED.getValue().equals(claim.getActivityStatus().getValue())) {
				result.getClaimList().add(claim);
			}
		}
		result.setTotalCount(result.getClaimList().size());
		return result;
	}

	@Override
	public ServiceActivityHistoryDetailResult retrieveServiceActivityHistoryDetail(
			ServiceActivityHistoryDetailContract contract) throws Exception {
		ServiceActivityHistoryDetailResult result = new ServiceActivityHistoryDetailResult();
		for (Activity activity : PartnerDomainMockDataGenerator.getActivities()) {
			if (activity.getActivityId().equals(contract.getActivityId())) {
				result.setActivity(activity);
				break;
			}
		}
		return result;
	}

	@Override
	public ServiceActivityHistoryListResult retrieveServiceActivityHistoryList(
			ServiceActivityHistoryListContract contract) throws Exception {
		ServiceActivityHistoryListResult result = new ServiceActivityHistoryListResult();
		List<Activity> activities = new ArrayList<Activity>(0);
		for (Activity activity : PartnerDomainMockDataGenerator.getActivities()) {
			if (contract.getAssetId().equals(activity.getServiceRequest().getAsset().getAssetId())
					&& activity.getActivityStatus().getValue().equals(RequestStatusEnum.COMPLETED.getValue())) {
				activities.add(activity);
			}
		}
		result.setActivityList(activities);
		result.setTotalcount(activities.size());
		return result;
	}

	@Override
	public GlobalAssetDetailResult retrieveGlobalAssetDetail(GlobalAssetDetailContract contract) throws Exception {
		Integer assetIdInt = Integer.valueOf(contract.getAssetId());
		List<Asset> devices = PartnerDomainMockDataGenerator.getDevices();
		GlobalAssetDetailResult result = new GlobalAssetDetailResult();
		for(Asset asset: devices){
			if(asset.getAssetId().equals(contract.getAssetId())){
				result.setAsset(asset);
			}
		}
//		result.setAsset(devices.get(assetIdInt % devices.size()));
		return result;
	}

	@Override
	public GlobalPartnerAssetListResult retrieveGlobalPartnerAssetList(GlobalPartnerAssetListContract contract) {

		GlobalPartnerAssetListResult result = new GlobalPartnerAssetListResult();
		List<Asset> assetList = PartnerDomainMockDataGenerator.getDevices();
		String serialNumber = contract.getSerialNumber();
		for (int i = 0; i < assetList.size(); i++) {
			if (serialNumber.equals(assetList.get(i).getSerialNumber())) {
				result.getAssetList().add(assetList.get(i));
			}
		}
		result.setTotalCount(result.getAssetList().size());
		return result;
	}

	@Override
	public CustomerAccountListResult retrieveCustomerAccountList(CustomerAccountListContract contract) throws Exception {
		CustomerAccountListResult result = new CustomerAccountListResult();
		List<Account> accountList = PartnerDomainMockDataGenerator.getCustomerAccountList();
		int totalCount = accountList.size();
		result.setAccountList(accountList);
		result.setTotalCount(totalCount);

		return result;
	}

	@Override
	public PartnerIndirectAccountListResult retrievePartnerIndirectAccountList(
			PartnerIndirectAccountListContract contract) throws Exception {
		PartnerIndirectAccountListResult result = new PartnerIndirectAccountListResult();
		for (Account account : PartnerDomainMockDataGenerator.getPartnerAccountList()) {
			if (account.isIndirectPartnerFlag()) {
				result.getAccountList().add(account);
			}
		}
		result.setTotalCount(result.getAccountList().size());

		return result;
	}

	@Override
	public FRUPartDetailResult retrieveFRUPart(FRUPartDetailContract contract) {
		// TODO
		PART_LIST = PartnerDomainMockDataGenerator.getPartList();
		FRUPartDetailResult result = new FRUPartDetailResult();
		for (Part part : PART_LIST) {
			if ((part.getPartNumber()).equals(contract.getPartNumber())) {
				result.setPart(part);
				break;
			}
		}
		return result;
	}

	@Override
	public FRUPartListResult retrieveFRUPartList(FRUPartListContract contract) {
		// TODO
		PART_LIST = PartnerDomainMockDataGenerator.getPartList();
		FRUPartListResult result = new FRUPartListResult();
		result.setPartList(PART_LIST);
		result.setTotalCount(PART_LIST.size());
		return result;
	}

	@Override
	public TechnicianListResult retrieveTechnicianList(TechnicianListContract contract) throws Exception {

		List<AccountContact> accountContactList = PartnerDomainMockDataGenerator.getAccountContactList();
		List<AccountContact> tempAccountContactList = new ArrayList<AccountContact>();
		TechnicianListResult result = new TechnicianListResult();

		for (AccountContact contact : accountContactList) {
			if (Math.random() < 0.6) {
				tempAccountContactList.add(contact);
			} else {
				continue;
			}
		}

		result.setAccountContactList(tempAccountContactList);
		return result;
	}

	@Override
	public PartnerAddressListResult retrievePartnerAddressList(PartnerAddressListContract contract) throws Exception {
		PartnerAddressListResult result = new PartnerAddressListResult();
		if(contract.isFavoriteFlag()){
			List<GenericAddress> addressList = new ArrayList<GenericAddress>();
			for(GenericAddress address:genericAddresses){
				if(address.getUserFavorite()){
					addressList.add(address);
				}
			}
			result.setAddressList(addressList);
			result.setTotalCount(addressList.size());
			return result;
		}
		result.setAddressList(genericAddresses);
		result.setTotalCount(genericAddresses.size());
		return result;
	}

	@Override
	public ClaimDetailResult retrieveClaimDetail(ClaimDetailContract contract) throws Exception {
		ClaimDetailResult claimDetailResult = new ClaimDetailResult();
		String activityId = contract.getActivityId();
		List<Activity> activityList = PartnerDomainMockDataGenerator.getActivities();

		for (Activity activity : activityList) {
			if (activityId.equals(activity.getActivityId())) {
				claimDetailResult.setActivity(activity);
				return claimDetailResult;
			}
		}

		return claimDetailResult;
	}

			
	@Override
	public PartnerFavoriteAddressUpdateResult updatePartnerUserFavoriteAddress(
			PartnerFavoriteAddressUpdateContract contract) throws Exception {
		PartnerFavoriteAddressUpdateResult result = new PartnerFavoriteAddressUpdateResult();
		result.setResult(false);
		for (GenericAddress address : genericAddresses) {
			if ((address.getAddressId()).equals(contract.getFavoriteAddressId())) {
				address.setUserFavorite(contract.isFavoriteFlag());
				result.setResult(true);

				return result;
			}
		}
		return result;
	}

	@Override
	public ActivityDetailResult retrieveActivityDetail(ActivityDetailContract contract) throws Exception {
		ActivityDetailResult activityDetailResult = new ActivityDetailResult();
		String serviceRequestId = contract.getServiceRequestId();
		int i = Integer.valueOf(contract.getActivityId());
		Activity activity = PartnerDomainMockDataGenerator.getActivity(i);
		//Commented for MPS 2.1 wave 4 initial development by portal
		//for (Activity activity : activityList) {
			//if (serviceRequestId.equals(activity.getServiceRequest().getId())) {
				activityDetailResult.setActivity(activity);
				return activityDetailResult;
			//}
		//}
		//return activityDetailResult;
	}

	@Override
	public PartnerNotificationsResult retrievePartnerNotifications(
			PartnerNotificationsContract contract) throws Exception {
		List<ServiceRequest> serviceRequestList = PartnerDomainMockDataGenerator.getServiceRequests();
		PartnerNotificationsResult result = new PartnerNotificationsResult();
		List<ServiceRequestActivity> serviceRequestActivityList = new ArrayList<ServiceRequestActivity>();
		for(ServiceRequest serviceRequest : serviceRequestList){
			if(serviceRequest.getId().equals(contract.getServiceRequestId())){
				serviceRequestActivityList = serviceRequest.getServicewebUpdateActivities();
				result.setServiceRequestActivityList(serviceRequestActivityList);
				return result;
			}
		}
		return result;
	}

	@Override
	public ValidateInstalledPrinterSerialNumberResult validateInstalledPrinterSerialNumber(
			ValidateInstalledPrinterSerialNumberContract contract)
			throws Exception {
		ValidateInstalledPrinterSerialNumberResult validateResult = new ValidateInstalledPrinterSerialNumberResult();
		String serialNumber = contract.getSerialNumber();	
		if(serialNumber.length()==7 || serialNumber.length()==9 || serialNumber.length()==11){
			validateResult.setSuccess(true);
		}else{
			validateResult.setSuccess(false);
		}
		return validateResult;
	}

	
    // Claims & Request Down Load
	// Venkat
	public DownloadClaimListResult retrieveDownloadClaimList(ActivityListContract contract) throws Exception
	{
		logger.info("  Inside method retrieveDownloadClaimList");
		
		final DownloadClaimListResult downloadClaimListResult = new DownloadClaimListResult();
		final List<DownloadClaim> downloadClaims = new ArrayList<DownloadClaim>();
		for (DownloadClaim downloadClaim : PartnerDomainMockDataGenerator.getDownloadClaims()) {
				downloadClaims.add(downloadClaim);
		}

		if (contract.getIncrement() == -1) {
			downloadClaimListResult.setActivityList(downloadClaims);
			downloadClaimListResult.setTotalcount(downloadClaims.size());
			return downloadClaimListResult;
		}

		return downloadClaimListResult;
	}

	
	public DownloadRequestListResult retrieveDownloadRequestList(ActivityListContract contract) throws Exception
	{
		logger.info("  Inside method retrieveDownloadRequestList");
		
		final DownloadRequestListResult downloadRequestListResult = new DownloadRequestListResult();
		final List<DownloadRequest> downloadRequests = new ArrayList<DownloadRequest>();
		for (DownloadRequest downloadRequest : PartnerDomainMockDataGenerator.getDownloadRequests()) {
				downloadRequests.add(downloadRequest);
		}

		if (contract.getIncrement() == -1) {
			downloadRequestListResult.setActivityList(downloadRequests);
			downloadRequestListResult.setTotalcount(downloadRequests.size());
			return downloadRequestListResult;
		}

		return downloadRequestListResult;
	}
	
	public PartnerClaimCreateIdResult retrieveCreateClaimId(String claimNumber) throws Exception{
		PartnerClaimCreateIdResult partnerClaimCreateIdResult=new PartnerClaimCreateIdResult();
		return partnerClaimCreateIdResult;

	}
// Claims and Request Download
// Venkat	

@Override
public RequestAcceptResult acceptRejectRequest(RequestAcceptContract contract)
		throws LGSCheckedException, LGSRuntimeException {
	// TODO Auto-generated method stub
	return null;
}
	
}
