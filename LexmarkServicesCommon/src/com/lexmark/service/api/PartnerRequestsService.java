package com.lexmark.service.api;

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
import com.lexmark.contract.source.RequestAcceptContract;
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

public interface PartnerRequestsService {
	
	public ActivityListResult retrieveActivityList(ActivityListContract contract) throws Exception;
	public AssignedTechnicianUpdateResult updateAssignedTechnician(AssignedTechnicianUpdateContract contract) throws Exception;
	public ServiceActivityHistoryListResult retrieveServiceActivityHistoryList(ServiceActivityHistoryListContract contract) throws Exception;
	public ServiceActivityHistoryDetailResult retrieveServiceActivityHistoryDetail (ServiceActivityHistoryDetailContract contract) throws Exception;
	public OpenClaimListResult retrieveOpenClaimList(OpenClaimListContract contract) throws Exception;
	public PartnerIndirectAccountListResult retrievePartnerIndirectAccountList (PartnerIndirectAccountListContract contract) throws Exception;
	public GlobalAssetDetailResult retrieveGlobalAssetDetail(GlobalAssetDetailContract contract) throws Exception;
	public GlobalPartnerAssetListResult retrieveGlobalPartnerAssetList(GlobalPartnerAssetListContract contract);
	public FRUPartDetailResult retrieveFRUPart(FRUPartDetailContract contract)throws Exception;
	public FRUPartListResult retrieveFRUPartList(FRUPartListContract contract)throws Exception;
	public CustomerAccountListResult retrieveCustomerAccountList (CustomerAccountListContract contract) throws Exception ;
	public TechnicianListResult retrieveTechnicianList (TechnicianListContract contract) throws Exception ;
	public PartnerAddressListResult retrievePartnerAddressList (PartnerAddressListContract contract) throws Exception ;
	public PartnerFavoriteAddressUpdateResult updatePartnerUserFavoriteAddress (PartnerFavoriteAddressUpdateContract contract) throws Exception; 
	public ClaimDetailResult retrieveClaimDetail(ClaimDetailContract contract) throws Exception;
	public ActivityDetailResult retrieveActivityDetail(ActivityDetailContract contract) throws Exception;
	public PartnerNotificationsResult retrievePartnerNotifications(PartnerNotificationsContract contract) throws Exception;
	public ValidateInstalledPrinterSerialNumberResult validateInstalledPrinterSerialNumber(ValidateInstalledPrinterSerialNumberContract contract) throws Exception;
	
	// Download Request & Claims
	public DownloadRequestListResult retrieveDownloadRequestList(ActivityListContract contract) throws Exception;
	public DownloadClaimListResult retrieveDownloadClaimList(ActivityListContract contract) throws Exception;
	public PartnerClaimCreateIdResult retrieveCreateClaimId(String claimNumber) throws Exception;
	public RequestAcceptResult acceptRejectRequest(RequestAcceptContract contract) throws LGSCheckedException, LGSRuntimeException;

}
