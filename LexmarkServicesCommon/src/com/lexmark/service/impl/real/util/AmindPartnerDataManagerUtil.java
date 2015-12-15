package com.lexmark.service.impl.real.util;

import static com.lexmark.service.impl.real.util.AmindServiceUtil.buildCriteria;
import static com.lexmark.service.impl.real.util.AmindServiceUtil.buildSortString;
import static com.lexmark.util.LangUtil.isNotEmpty;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.amind.common.service.SiebelBusinessServiceProxy;
import com.amind.data.service.ActionEvent;
import com.amind.data.service.ExecutionMode;
import com.amind.data.service.IDataManager;
import com.amind.data.service.QueryObject;
import com.amind.session.Session;
import com.lexmark.contract.ActivityDetailContract;
import com.lexmark.contract.ActivityListContract;
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
import com.lexmark.contract.PaymentDetailsContract;
import com.lexmark.contract.PaymentLineItemDetailsContract;
import com.lexmark.contract.PaymentListContract;
import com.lexmark.contract.PaymentRequestListContract;
import com.lexmark.contract.ServiceActivityHistoryDetailContract;
import com.lexmark.contract.ServiceActivityHistoryListContract;
import com.lexmark.contract.TechnicianListContract;
import com.lexmark.contract.ValidateInstalledPrinterSerialNumberContract;
import com.lexmark.domain.Account;
import com.lexmark.domain.Activity;
import com.lexmark.domain.Asset;
import com.lexmark.domain.Entitlement;
import com.lexmark.domain.EntitlementServiceDetail;
import com.lexmark.domain.GenericAddress;
import com.lexmark.result.ActivityDetailResult;
import com.lexmark.result.ActivityListResult;
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
import com.lexmark.result.PaymentDetailsResult;
import com.lexmark.result.PaymentLineItemDetailsResult;
import com.lexmark.result.PaymentListResult;
import com.lexmark.result.PaymentRequestListResult;
import com.lexmark.result.ServiceActivityHistoryDetailResult;
import com.lexmark.result.ServiceActivityHistoryListResult;
import com.lexmark.result.TechnicianListResult;
import com.lexmark.result.ValidateInstalledPrinterSerialNumberResult;
import com.lexmark.service.impl.real.AmindCrmSessionHandle;
import com.lexmark.service.impl.real.AmindSiebelCrmService;
import com.lexmark.service.impl.real.domain.AccountFlag;
import com.lexmark.service.impl.real.domain.AccountType;
import com.lexmark.service.impl.real.domain.AddressDo;
import com.lexmark.service.impl.real.domain.EntitlementTemplateServiceDetailsDo;
import com.lexmark.service.impl.real.domain.FavoriteAddressDo;
import com.lexmark.service.impl.real.domain.PartnerAccountDo;
import com.lexmark.service.impl.real.domain.PartnerAccountTypeDo;
import com.lexmark.service.impl.real.domain.PartnerActivityBase;
import com.lexmark.service.impl.real.domain.PartnerActivityDetailDo;
import com.lexmark.service.impl.real.domain.PartnerActivityHistory;
import com.lexmark.service.impl.real.domain.PartnerAssetDo;
import com.lexmark.service.impl.real.domain.PartnerClaimCreateIdDo;
import com.lexmark.service.impl.real.domain.PartnerClaimDetailDo;
import com.lexmark.service.impl.real.domain.PartnerCustomerAccountDo;
import com.lexmark.service.impl.real.domain.PartnerFruHardwarePartDetailDo;
import com.lexmark.service.impl.real.domain.PartnerFruHardwarePartListDo;
import com.lexmark.service.impl.real.domain.PartnerFruPartBaseDo;
import com.lexmark.service.impl.real.domain.PartnerFruPartDetailDo;
import com.lexmark.service.impl.real.domain.PartnerFruPartDo;
import com.lexmark.service.impl.real.domain.PartnerFruPartListDo;
import com.lexmark.service.impl.real.domain.PartnerHardwarePartDetailLocations;
import com.lexmark.service.impl.real.domain.PartnerNotificationDo;
import com.lexmark.service.impl.real.domain.PartnerOpenClaimDo;
import com.lexmark.service.impl.real.domain.PartnerPartDetailLocations;
import com.lexmark.service.impl.real.domain.PartnerPartLocations;
import com.lexmark.service.impl.real.domain.PartnerPaymentDo;
import com.lexmark.service.impl.real.domain.PartnerPaymentRequestDO;
import com.lexmark.service.impl.real.domain.PartnerTechnicianDo;
import com.lexmark.service.impl.real.domain.PartnerValidateAssetDo;
import com.lexmark.service.impl.real.domain.ServiceAgreementProductDo;
import com.lexmark.service.impl.real.domain.UserFavoriteAddressDo;
import com.lexmark.service.impl.real.domain.UserFavoriteContactDo;
import com.lexmark.service.impl.real.partnerActivityService.AmindPartnerActivitySearchSpecUtil;
import com.lexmark.service.impl.real.partnerActivityService.AmindPartnerActivityServiceUtil;
import com.lexmark.service.impl.real.partnerPaymentService.AmindPartnerPaymentRequestComparator;
import com.lexmark.util.LangUtil;

public class AmindPartnerDataManagerUtil extends AmindSiebelCrmService {
	
	public static final String FAVORITE_RELATIONSHIP_TYPE = "Favorite";
	
	private static final Map<String, String> ADDRESS_TYPE_FIELD_MAP = populateAddressTypeFieldMap();
	private static final Map<String, String> FAVORITEADDRESS_TYPE_FIELD_MAP = populateFavoriteAddressTypeFieldMap();
	
	@SuppressWarnings("unchecked")
	public static ActivityListResult queryAndGetActivityList(ActivityListContract contract, Session session, IDataManager dataManager, String searchSpec, AmindCrmSessionHandle crmSessionHandle, QueryObject criteria) {
		ActivityListResult result = new ActivityListResult();
		List<PartnerActivityBase> activityList = new ArrayList<PartnerActivityBase>();
		
		if(contract.isNewQueryIndicator()) {
			activityList = dataManager.query(criteria);

			/*Total Count Query*/
			logger.debug("[IN] Count Method");
			SiebelBusinessServiceProxy businessServiceProxy = session.getSiebelBusServiceProxy();
			int count = AmindServiceUtil.getTotalCount("LXK SW Partner Portal BO", "LXK SW Action - Partner Portal", searchSpec, businessServiceProxy);
			logger.debug("[OUT] Count Method");

			result.setTotalcount(count);
			crmSessionHandle.setPartnerActivityCount(count);
			
		} else {
			activityList = dataManager.queryNext(criteria);
			result.setTotalcount(crmSessionHandle.getPartnerActivityCount());
		}
		
		if (activityList != null && activityList.size() > 0) {
			result.setActivityList(AmindPartnerActivityServiceUtil
					.convertActivityDOListToActivityList(activityList, contract));
//			if (contract.getCreateChildSR() != null
//					&& !contract.getCreateChildSR().isEmpty()) {
//				result.setActivityList(AmindPartnerActivityServiceUtil
//						.filterCreateChildSRActivityList(result,
//								contract.getCreateChildSR()));
//
//				result.setTotalcount(result.getActivityList().size());
//
//			}
		} else {
			result.setActivityList(new ArrayList<Activity>());
			result.setTotalcount(0);
		}
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public static DownloadClaimListResult queryAndGetdonwloadClaimList(IDataManager dataManager, ActivityListContract contract,Map<String,String> activityFieldMap) throws Exception {
		DownloadClaimListResult result = new DownloadClaimListResult();
		List<PartnerClaimDetailDo> activityList = new ArrayList<PartnerClaimDetailDo>();
		StringBuilder sortSpec = new StringBuilder();
		
		QueryObject criteria = new QueryObject (PartnerClaimDetailDo.class, ActionEvent.QUERY);
		
		String searchSpec = AmindPartnerActivitySearchSpecUtil.buildActivityListSearchSpec(contract,dataManager,activityFieldMap, false);
		
		if(contract.getSortCriteria() != null && !contract.getSortCriteria().isEmpty()) {
			sortSpec.append(AmindServiceUtil.buildSortString(contract.getSortCriteria(), activityFieldMap));
		}
		
		criteria = AmindServiceUtil.buildBasicQueryObject(criteria, contract.getIncrement(),contract.getStartRecordNumber());
		criteria.addComponentSearchSpec(PartnerClaimDetailDo.class, searchSpec);
		criteria.setSortString(sortSpec.toString());
		criteria.setStartRowIndex(0);
		criteria.setNumRows(50);
		activityList = dataManager.query(criteria);
		if(activityList != null && activityList.size() > 0) {
			result.setActivityList(AmindPartnerActivityServiceUtil.convertActivityBaseListToDownloadClaimList(activityList));
			result.setTotalcount(activityList.size());
		} else {
			result.setActivityList(null);
			result.setTotalcount(0);
		}
		return result;
	}
	@SuppressWarnings("unchecked")
	public static DownloadRequestListResult queryAndGetdonwloadRequestList(IDataManager dataManager, ActivityListContract contract, Map<String,String> activityFieldMap) throws Exception {
		DownloadRequestListResult result = new DownloadRequestListResult();
		StringBuilder sortSpec = new StringBuilder();
		List<PartnerActivityDetailDo> activityList = new ArrayList<PartnerActivityDetailDo>();
		QueryObject criteria = new QueryObject (PartnerActivityDetailDo.class, ActionEvent.QUERY);
		StringBuilder searchSpec = new StringBuilder ();		
		searchSpec.append(AmindPartnerActivitySearchSpecUtil.buildActivityListSearchSpec(contract,dataManager,activityFieldMap, false));
		searchSpec.append(" AND [LXK C Service Activity] = 'Y' AND ([LXK SW Action Status] <> 'Validated' OR [LXK SW Action Status] IS NULL )");
		
		if(contract.getSortCriteria() != null && !contract.getSortCriteria().isEmpty()) {
			sortSpec.append(AmindServiceUtil.buildSortString(contract.getSortCriteria(), activityFieldMap));
		}
		
		criteria = AmindServiceUtil.buildBasicQueryObject(criteria, contract.getIncrement(),contract.getStartRecordNumber());
		criteria.addComponentSearchSpec(PartnerActivityDetailDo.class, searchSpec.toString());
		criteria.setSortString(sortSpec.toString());
		criteria.setStartRowIndex(0);
		criteria.setNumRows(50);
		activityList = dataManager.query(criteria);
		if(activityList != null && activityList.size() > 0) {
			result.setActivityList(AmindPartnerActivityServiceUtil.convertActivityBaseListToDownloadRequestList(activityList));
			result.setTotalcount(activityList.size());
		} else {
			result.setActivityList(null);
			result.setTotalcount(0);
		}
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public static ServiceActivityHistoryListResult queryandGetServiceHistoryList(IDataManager dataManager, ServiceActivityHistoryListContract contract) {
		ServiceActivityHistoryListResult result  = new ServiceActivityHistoryListResult();
		String searchExpression = "[Asset Id] = '" +  contract.getAssetId() + "' AND [Id] <> '" + contract.getServiceRequestId() + "'";
		List<PartnerActivityBase> activityHistoryList = dataManager.queryBySearchExpression(PartnerActivityHistory.class, searchExpression);

		if(activityHistoryList != null && activityHistoryList.size() > 0) {
			result.setActivityList(AmindPartnerActivityServiceUtil.convertActivityDOListToActivityList(activityHistoryList, null));
			result.setTotalcount(activityHistoryList.size());
		} else {
			result.setTotalcount(0);
		}
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public static ServiceActivityHistoryDetailResult queryandGetServiceHistoryDetail(IDataManager dataManager, ServiceActivityHistoryDetailContract contract ) {
		ServiceActivityHistoryDetailResult result  = new ServiceActivityHistoryDetailResult();
		List<PartnerActivityBase> activityHistoryList = dataManager.queryBySearchExpression
		(PartnerActivityHistory.class, "[serviceRequestId] = '" +  contract.getServiceRequestId().trim().toUpperCase() + "'");

		if(activityHistoryList != null && activityHistoryList.size() > 0) {
			result.setActivity(AmindPartnerActivityServiceUtil.convertActivityDOListToActivityList(activityHistoryList,null).get(0));
		}
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public static OpenClaimListResult queryandGetOpenClaimList(IDataManager dataManager, OpenClaimListContract contract ) {
		OpenClaimListResult result  = new OpenClaimListResult();
		String searchExpression = AmindServiceUtil.buildMdmSearchExpressionForMdmLevel(contract.getMdmId(), 
				contract.getMdmLevel(), null, false, "mdmLevel") + " AND [assetId] = '" + contract.getAssetId().trim().toUpperCase() + "'" +
						" AND ([activityStatus] = 'Pending Service Action' " +
						" OR [activityStatus] ='Claim Submitted' " +
						" OR [activityStatus] = 'Invalid Debrief'" +
						" OR [activityStatus] = 'Dispatched to SP')";
		
		List<PartnerOpenClaimDo> openClaimList = dataManager.queryBySearchExpression
			(PartnerOpenClaimDo.class,searchExpression);											

		if(openClaimList != null && openClaimList.size() > 0) {
			result.setClaimList(AmindPartnerDataCoversionUtil.convertOpenClaimToPortalActivity(openClaimList));
			result.setTotalCount(openClaimList.size());
		} else {
			result.setTotalCount(0);
		}

		return result;
	}
	
	@SuppressWarnings("unchecked")
	public static GlobalPartnerAssetListResult queryandGetPartnerAssetList(IDataManager dataManager, GlobalPartnerAssetListContract contract ) {
		GlobalPartnerAssetListResult result  = new GlobalPartnerAssetListResult();
		List<Asset> portalAssetList = new ArrayList<Asset> ();
		List<PartnerAssetDo> partnerAssetList = dataManager.queryBySearchExpression(PartnerAssetDo.class,
        		"[serialNumber] = '" + contract.getSerialNumber().trim().toUpperCase() + "'" );
		if(partnerAssetList != null && !partnerAssetList.isEmpty()){
			
			portalAssetList = AmindPartnerDataCoversionUtil.convertPartnerAssetDoToPartnerAssetList(partnerAssetList);
		} 
		
		result.setAssetList(portalAssetList);
		result.setTotalCount(portalAssetList.size());

		return result;
	}
	
	@SuppressWarnings("unchecked")
	public static GlobalAssetDetailResult queryandGetPartnerAssetDetail(IDataManager dataManager, GlobalAssetDetailContract contract ) {
		//added by ragesree -2098 start
		Asset asset=new Asset();
		PartnerAssetDo assetDo=null;

		GlobalAssetDetailResult result  = new GlobalAssetDetailResult();
		List<PartnerAssetDo> partnerAssetList = dataManager.queryBySearchExpression(PartnerAssetDo.class, 
        			"[assetId] = '" + contract.getAssetId().trim().toUpperCase() + "'");
       logger.info("*********************************************************************"+contract.getAssetId());
		if(partnerAssetList != null && partnerAssetList.size() > 0)
		{
	
			assetDo = partnerAssetList.get(0);
			asset=AmindPartnerDataCoversionUtil.convertPartnerAssetToAsset(assetDo, contract);
		}

		
				List<ServiceAgreementProductDo> serviceAggrements = dataManager.queryBySearchExpression(ServiceAgreementProductDo.class, 
                			"[assetId] = '" + contract.getAssetId().trim().toUpperCase() + "'");
				if(serviceAggrements!=null){
					Entitlement entitlement = new Entitlement();
					ServiceAgreementProductDo item=serviceAggrements.get(0);
					ArrayList<EntitlementTemplateServiceDetailsDo> entitlementList = item.getServiceDetails();
//					logger.info("========================>3<==================== "+entitlementList.size());
					if (entitlementList != null && entitlementList.size() > 0)
					{
						logger.debug("========================entitlementList size==================== "+entitlementList.size());
						List <EntitlementServiceDetail> entitlementServiceDetails = new ArrayList<EntitlementServiceDetail>();
						
						
						
							for(EntitlementTemplateServiceDetailsDo serviceDetailDo : entitlementList)
							{
								
								logger.info("========================>Entitlement Id<==================== "+serviceDetailDo.getEntitlementId());
								List<EntitlementTemplateServiceDetailsDo> entitlements = dataManager.queryBySearchExpression(EntitlementTemplateServiceDetailsDo.class, 
                                			"[Entitlement Tpl Id] = '" + serviceDetailDo.getEntitlementId() );
								
								if(entitlements!=null && entitlements.size()>0){
							for(EntitlementTemplateServiceDetailsDo activitytype : entitlements){
								logger.info("========================>4<==================== "+activitytype.isPartnerShowFlag());
								if(activitytype.isPartnerShowFlag())
								{
									
									EntitlementServiceDetail serviceDetail = new EntitlementServiceDetail();
									serviceDetail.setServiceDetailId(activitytype.getId());
									serviceDetail.setServiceDetailDescription(activitytype.getDescription());
									logger.info("========================>5<==================== "+activitytype.getDescription());
									serviceDetail.setPrimaryFlag(activitytype.getPrimaryFlag() == null ? false : activitytype.getPrimaryFlag());
									entitlementServiceDetails.add(serviceDetail);
								}
							}
									entitlement.setServiceDetails(entitlementServiceDetails);
								}
								
								logger.info("========================>6<==================== "+serviceDetailDo.getDescription());
								
								
									
							}
							
							asset.setEntitlement(entitlement);
							
					}

				}
				result.setAsset(asset);
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public static PartnerIndirectAccountListResult queryandGetPartnerIndirectAccountList(IDataManager dataManager, PartnerIndirectAccountListContract contract ) {
		PartnerIndirectAccountListResult result  = new PartnerIndirectAccountListResult();
		String searchSpec = AmindServiceUtil.buildMdmSearchExpressionForMdmLevel( 
				contract.getMdmId(), contract.getMdmLevel(), 
				null, false, "mdmLevel");

		List<PartnerAccountDo> partnerAccountDoList = dataManager.queryBySearchExpression
											(PartnerAccountDo.class, searchSpec );	
		result.setAccountList(convertAccountListDoToPortalAccountList(partnerAccountDoList,true));
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public static CustomerAccountListResult queryandGetCustomerAccountList(IDataManager dataManager, CustomerAccountListContract contract ) {
		CustomerAccountListResult result  = new CustomerAccountListResult();
		String searchSpec = AmindServiceUtil.buildMdmSearchExpressionForMdmLevel( 
				contract.getMdmId(), contract.getMdmLevel(), 
				null, false, "mdmLevel");	

		List<PartnerAccountDo> partnerAccountDoList = dataManager.queryBySearchExpression
											(PartnerCustomerAccountDo.class, searchSpec );	
		result.setAccountList(convertAccountListDoToPortalAccountList(partnerAccountDoList,false));
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public static FRUPartDetailResult queryandGetFruPartDetail(IDataManager dataManager, FRUPartDetailContract contract ) {
		FRUPartDetailResult result  = new FRUPartDetailResult();
		QueryObject criteria = null;
		if(contract.isReplacementFlag()) {
			criteria = new QueryObject (PartnerFruPartDo.class, ActionEvent.QUERY);
			criteria.addComponentSearchSpec(PartnerFruPartDo.class, "[partNumber] = '" + contract.getPartNumber().trim().toUpperCase() + "'");
			criteria.addComponentSearchSpec(PartnerPartLocations.class, "[id] = '" + contract.getPartnerOrg() + "'");
		} else  {
			if(contract.isHardwarePartRequest()) {
				criteria = new QueryObject (PartnerFruHardwarePartDetailDo.class, ActionEvent.QUERY);
				criteria.addComponentSearchSpec(PartnerFruHardwarePartDetailDo.class, "[partNumber] = '" + contract.getPartNumber().trim().toUpperCase() + "'"
						+ " AND [modelNumber] = '" + contract.getModelNumber().trim().toUpperCase() + "'");
				/*Org Id is to get replacement part only, is the org id doesn't match then give only Part Detail*/
				criteria.addComponentSearchSpec(PartnerHardwarePartDetailLocations.class, "[id] = '" + contract.getPartnerOrg() + "'");
			}
			else {
				criteria = new QueryObject (PartnerFruPartDetailDo.class, ActionEvent.QUERY);
				StringBuilder searchSpec = new StringBuilder();
				searchSpec.append("[partNumber] = '" + contract.getPartNumber().trim().toUpperCase() + "'"
						+ " AND [modelNumber] = '" + contract.getModelNumber().trim().toUpperCase() + "'");
				if (contract.isMaintenanceKitValidation()) {
					searchSpec
							.append(" and ([LXK Show Portal]<> 'Y' OR [LXK Show Portal] is null)");
				}

				criteria.addComponentSearchSpec(PartnerFruPartDetailDo.class, searchSpec.toString());
				/*Org Id is to get replacement part only, is the org id doesn't match then give only Part Detail*/
				criteria.addComponentSearchSpec(PartnerPartDetailLocations.class, "[id] = '" + contract.getPartnerOrg() + "'");
			}
		}
		List<PartnerFruPartBaseDo> fruPartList = dataManager.query(criteria);
		if(LangUtil.isNotEmpty(fruPartList)) {
			result.setPart(AmindPartnerDataCoversionUtil.convertFruPartDoToPortalFruPart(fruPartList, true).get(0));
		}
		return result;
	}
	
	
	@SuppressWarnings("unchecked")
	public static FRUPartListResult queryandGetFruPartList(IDataManager dataManager, FRUPartListContract contract ) {
		FRUPartListResult result  = new FRUPartListResult();
		String searchSpec = buildPartListSearchExpression(contract);
		
		List<PartnerFruPartBaseDo> fruPartList = null;
		if(contract.isHardwarePartsRequest()) {
			fruPartList = dataManager.queryBySearchExpression(PartnerFruHardwarePartListDo.class, searchSpec);
		}
		else {
			fruPartList = dataManager.queryBySearchExpression(PartnerFruPartListDo.class, searchSpec);
		}
		
		if(fruPartList != null && fruPartList.size() > 0) {
			result.setPartList(AmindPartnerDataCoversionUtil.convertFruPartDoToPortalFruPart(fruPartList, false));
			result.setTotalCount(result.getPartList().size());
		} else {
			result.setTotalCount(0);
		}
		return result;
	}
	
	private static String buildPartListSearchExpression(FRUPartListContract contract) {
		StringBuilder expr = new StringBuilder();
		expr.append("[modelNumber] = '" + contract.getModelNumber().trim().toUpperCase() + "'");
		if (contract.isHardwarePartsRequest()) {
			expr.append(" AND  [LXK SW Part List - Partner Portal.LXK SD Product Status] <> 'Obsolete' AND [LXK SW Part List - Partner Portal.LXK MPS Material Line] = 'Options'");
		}
		if (contract.isMaintenanceKitValidation()) {
			expr.append(" AND  ([LXK SW FRU Part List - Partner Portal.LXK Show Portal] <> 'Y' OR [LXK SW FRU Part List - Partner Portal.LXK Show Portal] is null)");
		}
		return expr.toString();
	}

	@SuppressWarnings("unchecked")
	public static TechnicianListResult queryandGetTechnicianList(IDataManager dataManager, TechnicianListContract contract ) {
		TechnicianListResult result  = new TechnicianListResult();
		String searchSpec = "EXISTS ([employeeOrgId] = '" + contract.getPartnerAccountId().trim().toUpperCase() +"')";	
		QueryObject criteria = new QueryObject(PartnerTechnicianDo.class, ActionEvent.QUERY);
		criteria.addComponentSearchSpec(PartnerTechnicianDo.class, searchSpec);

		List<PartnerTechnicianDo> partnerTechnicianDoList = dataManager.query(criteria);
		if(LangUtil.isNotEmpty(partnerTechnicianDoList)) {
			result.setAccountContactList(AmindPartnerDataCoversionUtil.convertTechnicianListDoToAccountContact(partnerTechnicianDoList));
		}
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public static PartnerAddressListResult queryandGetPartnerAddressList(final IDataManager dataManager, PartnerAddressListContract contract, AmindCrmSessionHandle amindCrmSessionHandle, Session totalCountSession, final Session favoritesSession) throws InterruptedException, ExecutionException {
		PartnerAddressListResult result  = new PartnerAddressListResult();
		Map<String, Object> filterCriteria = contract.getFilterCriteria();
		final Map<String, Object> sortCriteria = contract.getSortCriteria();
		final int startRecordNumber = contract.getStartRecordNumber();
		final int increment = contract.getIncrement();
		int totalCount = 0;
		
		List<AddressDo> addressDoList = null;
		List<GenericAddress> addressList = null;
		Set<String> favoriteSet = null;
		/* Added by sankha for LEX:AIR00065738 start*/
		final StringBuilder accountExpr = new StringBuilder();
		String markedForDeletion = "Marked For Deletion";
		String inactive = "Inactive";
//		String accountExpr = "[mdmLevel5AccountId] = '" + contract.getAccountID().trim().toUpperCase() + "'";
		accountExpr.append( "[Account Id] = '");
		accountExpr.append(contract.getAccountID().trim().toUpperCase() + "'");
		accountExpr.append(" AND [LXK R Account Address Status] <> '");
		accountExpr.append(markedForDeletion + "'");
		accountExpr.append(" AND [LXK R Account Address Status] <> '");
		accountExpr.append(inactive + "'");
		accountExpr.append(buildCriteria(filterCriteria, ADDRESS_TYPE_FIELD_MAP, true, true));
		
		final StringBuilder favSearchExp = new StringBuilder();
		favSearchExp.append( "[Favorite Address Account Id] = '");
		favSearchExp.append(contract.getAccountID().trim().toUpperCase() + "'");
		favSearchExp.append(" AND [LXK Status] <> '");
		favSearchExp.append(markedForDeletion + "'");
		favSearchExp.append(" AND [Contact Id] =  '");
		favSearchExp.append(contract.getContactId().trim().toUpperCase() + "'");
		favSearchExp.append(buildCriteria(filterCriteria, FAVORITEADDRESS_TYPE_FIELD_MAP, true, true));
		
		ExecutorService executor = null;
		
		try {
			if (contract.isFavoriteFlag())
			{
				if(contract.isNewQueryIndicator()) {
					
					final SiebelBusinessServiceProxy businessServiceProxy = totalCountSession.getSiebelBusServiceProxy();
					
					executor = Executors.newFixedThreadPool(2);
					
					Future<List<AddressDo>> addressListFuture = executor.submit(new Callable<List<AddressDo>>() {
						@Override
						public List<AddressDo> call() throws Exception {	
							return dataManager.query(buildFavoriteAddressTypeCriteria(favSearchExp.toString(), startRecordNumber, increment, sortCriteria));
						}
					});
					
					Future<Integer> totalCountFuture = executor.submit(new Callable<Integer>() {
						@Override
						public Integer call() throws Exception {
							return AmindServiceUtil.getTotalCount(FavoriteAddressDo.BO, FavoriteAddressDo.BC, favSearchExp.toString(), businessServiceProxy);
						}
					});
					
					addressDoList = addressListFuture.get();
					totalCount = totalCountFuture.get();
					
					executor.shutdown();
					
					amindCrmSessionHandle.setPartnerAddressCount(totalCount);
	
				}
				else {
					addressDoList = dataManager.queryNext(buildFavoriteAddressTypeCriteria(favSearchExp.toString(), startRecordNumber, increment, sortCriteria));
					totalCount = amindCrmSessionHandle.getPartnerAddressCount();
				}
				
				addressList = AmindPartnerDataCoversionUtil.convertAddressDoListToGenericAddress(addressDoList, null);
			} 
			else 
			{
				logger.info("All addresses Expression is : " + accountExpr.toString());
				
				if(contract.isNewQueryIndicator()) {
					
					final SiebelBusinessServiceProxy businessServiceProxy = totalCountSession.getSiebelBusServiceProxy();
					
					executor = Executors.newFixedThreadPool(3);
					
					Future<List<AddressDo>> addressListFuture = executor.submit(new Callable<List<AddressDo>>() {
						@Override
						public List<AddressDo> call() throws Exception {	
							return dataManager.query(buildAddressTypeCriteria(accountExpr.toString(), startRecordNumber, increment, sortCriteria));
						}
					});
					
					Future<Set<String>> favoritesFuture = executor.submit(new Callable<Set<String>>() {
						@Override
						public Set<String> call() throws Exception {
							List<FavoriteAddressDo> favoriteList = favoritesSession.getDataManager().queryBySearchExpression(FavoriteAddressDo.class, favSearchExp.toString());
							Set<String> favorites = new HashSet<String>();
							if (favoriteList != null)
							{
								for (FavoriteAddressDo addrDo : favoriteList) {
									favorites.add(addrDo.getFavoriteAddressId());
								}
							}
							
							return favorites;
						}
					});
					
					Future<Integer> totalCountFuture = executor.submit(new Callable<Integer>() {
						@Override
						public Integer call() throws Exception {
							return  AmindServiceUtil.getBCTotalCount(AddressDo.BO, AddressDo.BC, accountExpr.toString(), businessServiceProxy);
						}
					});
					
					addressDoList = addressListFuture.get();
					favoriteSet = favoritesFuture.get();
					totalCount = totalCountFuture.get();
					
					executor.shutdown();
					
					amindCrmSessionHandle.setPartnerAddressCount(totalCount);
					amindCrmSessionHandle.setPartnerAddressFavoriteSet(favoriteSet);
				}
				
				else {
					addressDoList = dataManager.queryNext(buildAddressTypeCriteria(accountExpr.toString(), startRecordNumber, increment, sortCriteria));
					totalCount = amindCrmSessionHandle.getPartnerAddressCount();
					favoriteSet = amindCrmSessionHandle.getPartnerAddressFavoriteSet();
				}
				
				addressList = AmindPartnerDataCoversionUtil.convertAddressDoListToGenericAddress(addressDoList, favoriteSet);
			}
			
			if(addressList != null) {
				result.setAddressList(addressList);
				result.setTotalCount(totalCount);
			} else {
				result.setTotalCount(0);
			}
			
			return result;
		}
		finally {
			if (executor!=null && !executor.isShutdown()) {
				executor.shutdown();
			}
		}
	}
	
	private static QueryObject buildAddressTypeCriteria(String accountExpr, int startRecordNumber, int increment, Map<String, Object> sortCriteria) {
		QueryObject criteria = new QueryObject(AddressDo.class, ActionEvent.QUERY);
		criteria.setExecutionMode(ExecutionMode.BiDirectional);
		criteria.setNumRows(increment);
		criteria.setStartRowIndex(startRecordNumber);
		criteria.addComponentSearchSpec(AddressDo.class, accountExpr);

		if (isNotEmpty(sortCriteria)) {
			criteria.setSortString(buildSortString(sortCriteria, ADDRESS_TYPE_FIELD_MAP));
		}

		return criteria;
	}

	private static QueryObject buildFavoriteAddressTypeCriteria(String favSearchExp, int startRecordNumber, int increment, Map<String, Object> sortCriteria) {
		QueryObject criteria = new QueryObject(FavoriteAddressDo.class, ActionEvent.QUERY);
		criteria.setExecutionMode(ExecutionMode.BiDirectional);
		criteria.setNumRows(increment);
		criteria.setStartRowIndex(startRecordNumber);
		criteria.addComponentSearchSpec(FavoriteAddressDo.class, favSearchExp);

		if (isNotEmpty(sortCriteria)) {
			criteria.setSortString(buildSortString(sortCriteria, ADDRESS_TYPE_FIELD_MAP));
		}

		return criteria;
	}
	
	
	
	@SuppressWarnings("unchecked")
	public static PartnerFavoriteAddressUpdateResult queryandUpdateUserFavoriteAddress(IDataManager dataManager, PartnerFavoriteAddressUpdateContract contract ) {
		PartnerFavoriteAddressUpdateResult result  = new PartnerFavoriteAddressUpdateResult();
			
		StringBuilder expr = new StringBuilder();
		expr.append("[favoriteAddressId]='");
		expr.append(contract.getFavoriteAddressId().trim().toUpperCase());
		expr.append("' AND [contactId]='");
		expr.append(contract.getContactId().trim().toUpperCase());
		expr.append("' AND [accountId]='");
		expr.append(contract.getPartnerAccountId().trim().toUpperCase());
		expr.append("' AND [relationshipType]='");
		expr.append(FAVORITE_RELATIONSHIP_TYPE);
		expr.append("'");
		
		List<UserFavoriteContactDo> adresses = dataManager.queryBySearchExpression(UserFavoriteAddressDo.class, expr.toString());

		if ((adresses != null && adresses.size() > 0 && !contract.isFavoriteFlag()) || 
				((adresses == null || adresses.size() == 0) && contract.isFavoriteFlag()))
		{
			UserFavoriteAddressDo addressDo = new UserFavoriteAddressDo();
			addressDo.setContactId(contract.getContactId());
			addressDo.setFavoriteAddressId(contract.getFavoriteAddressId());
			addressDo.setAccountId(contract.getPartnerAccountId());
			addressDo.setRelationshipType(FAVORITE_RELATIONSHIP_TYPE);
			
			//if favorite flag is true, then relation should be added, else deleted.
			if (contract.isFavoriteFlag())
			{
				dataManager.create(addressDo);
			}
			else
			{
				dataManager.delete(addressDo);
			}
			result.setResult(true);
		}
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public static ClaimDetailResult queryandGetClaimDetail(IDataManager dataManager, ClaimDetailContract contract ) throws ParseException {
		ClaimDetailResult result  = new ClaimDetailResult();
		List<PartnerActivityBase> activityDetailList = dataManager.queryBySearchExpression(PartnerClaimDetailDo.class, 
				" [activityId] = '" + contract.getActivityId().trim().toUpperCase() + "'");

		if(LangUtil.isNotEmpty(activityDetailList)) {
			result.setActivity(AmindPartnerActivityServiceUtil.convertActivityDetailDoToActivity(activityDetailList.get(0), contract.getDebriefFlag(), "temp", false));
		}
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public static ActivityDetailResult queryandGetActivityDetail(IDataManager dataManager, ActivityDetailContract contract ) throws ParseException {
		ActivityDetailResult result  = new ActivityDetailResult();
		List<PartnerActivityBase> activityDetailList = dataManager.queryBySearchExpression(PartnerActivityDetailDo.class, 
				" [activityId] = '" + contract.getActivityId().trim().toUpperCase() + "'");

		if(LangUtil.isNotEmpty(activityDetailList)) {
			result.setActivity(AmindPartnerActivityServiceUtil.convertActivityDetailDoToActivity(activityDetailList.get(0), contract.isDebriefFlag(), contract.getPageName(), false));
		}
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public static PartnerNotificationsResult queryandGetNotificationList(IDataManager dataManager, PartnerNotificationsContract contract ) {
		PartnerNotificationsResult result  = new PartnerNotificationsResult();
		List<PartnerNotificationDo> notificationList = dataManager.queryBySearchExpression(PartnerNotificationDo.class, 
				" [serviceRequestId] = '" + contract.getServiceRequestId().trim().toUpperCase() + "'" );
		if(LangUtil.isNotEmpty(notificationList)) {
			result.setServiceRequestActivityList(AmindPartnerDataCoversionUtil.convertNotificationToServiceRequest (notificationList, contract.getEmailAddress()));
		}
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public static ValidateInstalledPrinterSerialNumberResult queryandValidateInstalledPrinterSerialNumber(IDataManager dataManager, ValidateInstalledPrinterSerialNumberContract contract ) {
		ValidateInstalledPrinterSerialNumberResult result  = new ValidateInstalledPrinterSerialNumberResult();
		List<PartnerValidateAssetDo> validateAssetList = dataManager.queryBySearchExpression
		(PartnerAssetDo.class, " [serialNumber] = '" + contract.getSerialNumber().trim().toUpperCase()+ "' " +
				"AND [machineTypeModel] = '" + contract.getModelNumber().trim().toUpperCase()+ "'");

		if(validateAssetList != null && validateAssetList.size() > 0) {
			result.setSuccess(true);
		} else {
			result.setSuccess(false);
		}
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public static PaymentListResult queryandgetPaymentList (QueryObject criteria, AmindCrmSessionHandle crmSessionHandle, PaymentListContract contract, String searchExpression,
					Session session, IDataManager dataManager) throws ParseException {
		PaymentListResult result = new PaymentListResult();
		List<PartnerPaymentDo> paymentList = new ArrayList<PartnerPaymentDo>();
		SiebelBusinessServiceProxy businessServiceProxy = session.getSiebelBusServiceProxy();
		
		if(contract.isNewQueryIndicator()) {
			paymentList = dataManager.query(criteria);

			/*Total Count Query*/
			logger.debug("[IN] Count Method");
			int count = AmindServiceUtil.getTotalCount("LXK SW Partner Invoice - Partner Portal", "LXK SW Partner Invoice - Partner Portal", searchExpression, businessServiceProxy);
			logger.debug("[OUT] Count Method");
			result.setTotalcount(count);
			crmSessionHandle.setPartnerPaymentCount(count);
			
		} else {
			paymentList = dataManager.queryNext(criteria);
			result.setTotalcount(crmSessionHandle.getPartnerPaymentCount());
		}
		
		if(paymentList != null && paymentList.size() > 0) {
			result.setPaymentList(AmindPartnerDataCoversionUtil.convertPaymentDOListToPaymentList (paymentList));
		} else {
			result.setPaymentList(null);
			result.setTotalcount(0);
		}
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public static PaymentLineItemDetailsResult queryandgetPaymentLineItemList (QueryObject criteria, AmindCrmSessionHandle crmSessionHandle, PaymentLineItemDetailsContract contract, String searchExpression, Session session) throws ParseException {
		PaymentLineItemDetailsResult result = new PaymentLineItemDetailsResult();
		List<PartnerPaymentRequestDO> paymentLineItemList = new ArrayList<PartnerPaymentRequestDO>();
		SiebelBusinessServiceProxy businessServiceProxy = session.getSiebelBusServiceProxy();
		IDataManager dataManager = session.getDataManager();
		if(contract.isNewQueryIndicator()) {
			paymentLineItemList = dataManager.query(criteria);

			/*Total Count Query*/
			logger.debug("[IN] Count Method");
			int count = AmindServiceUtil.getTotalCount("LXK SW Partner Payment Details - Partner Portal", "LXK SW Partner Payment Details - Partner Portal", searchExpression.toString(), businessServiceProxy);
			logger.debug("[OUT] Count Method");
			result.setTotalCount(count);
			crmSessionHandle.setPartnerPaymentLineItemCount(count);
			
		} else {
			paymentLineItemList = dataManager.queryNext(criteria);
			result.setTotalCount(crmSessionHandle.getPartnerPaymentLineItemCount());
		}
		
		if(paymentLineItemList != null && paymentLineItemList.size() > 0) {
			List<Activity> activityList = AmindPartnerDataCoversionUtil.convertPaymentLineItemDOListToActivityList(paymentLineItemList);
			
			if(contract.getSortCriteria().containsKey("Activity.totalPayment") || contract.getSortCriteria().containsKey("Activity.additionalPayments")) {
					 Collections.sort(activityList, 
								new AmindPartnerPaymentRequestComparator(contract.getSortCriteria()));
				}
			result.setActivities(activityList);
		} else {
			result.setActivities(null);
			result.setTotalCount(0);
		}
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public static PaymentRequestListResult queryandgetPaymentRequestList (QueryObject criteria, AmindCrmSessionHandle crmSessionHandle, PaymentRequestListContract contract, String searchExpression,
			Session session, IDataManager dataManager) throws ParseException {
		List<PartnerPaymentRequestDO> paymentRequestList = new ArrayList<PartnerPaymentRequestDO>();
		PaymentRequestListResult result = new PaymentRequestListResult();
		if(contract.isNewQueryIndicator()) {
			paymentRequestList = dataManager.query(criteria);

			/*Total Count Query*/
			logger.debug("[IN] Count Method");
			SiebelBusinessServiceProxy businessServiceProxy = session.getSiebelBusServiceProxy();
			int count = AmindServiceUtil.getTotalCount("LXK SW Partner Payment Details - Partner Portal", "LXK SW Partner Payment Details - Partner Portal", searchExpression.toString(), businessServiceProxy);
			logger.debug("[OUT] Count Method");

			result.setTotalcount(count);
			crmSessionHandle.setPartnerPaymentRequestCount(count);
			
		} else {
			paymentRequestList = dataManager.queryNext(criteria);
			result.setTotalcount(crmSessionHandle.getPartnerPaymentRequestCount());
		}
		
		if(paymentRequestList != null && paymentRequestList.size() > 0) {
			List<Activity> activityList = AmindPartnerDataCoversionUtil.convertPaymentLineItemDOListToActivityList(paymentRequestList);
			
			if(contract.getSortCriteria().containsKey("Activity.totalPayment") || contract.getSortCriteria().containsKey("Activity.additionalPayments")) {
					 Collections.sort(activityList, 
								new AmindPartnerPaymentRequestComparator(contract.getSortCriteria()));
				}
			result.setPaymentRequestList(activityList);
		} else {
			result.setPaymentRequestList(null);
			result.setTotalcount(0);
		}
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public static PaymentDetailsResult queryandGetPaymentDetails(IDataManager dataManager, PaymentDetailsContract contract ) throws ParseException {
		PaymentDetailsResult result  = new PaymentDetailsResult();
		List<PartnerPaymentDo> paymentList = dataManager.queryBySearchExpression(PartnerPaymentDo.class, "[id] = '" + contract.getPaymentId() + "'");
		if(paymentList != null && paymentList.size() > 0) {
			result.setPayment(AmindPartnerDataCoversionUtil.convertPaymentDOListToPaymentList(paymentList).get(0));
		}

		return result;
	}
@SuppressWarnings("unchecked")
	public static PartnerClaimCreateIdResult queryandGetClaimId(IDataManager dataManager, String cliamNumber) throws ParseException {
		logger.debug("--------------------- Calling PartnerClaimCreateIdResult queryandGetClaimId---------------------------"+cliamNumber);
		PartnerClaimCreateIdResult result  = new PartnerClaimCreateIdResult();
		String inputStr="[claimNumber] = '" + cliamNumber + "'";
		logger.debug("--------------------- Calling inputStr inputStr---------------------------"+inputStr);

		List<PartnerClaimCreateIdDo> claimIdList = dataManager.queryBySearchExpression(PartnerClaimCreateIdDo.class,inputStr );
		logger.debug("--------------------- Calling PartnerClaimCreateIdResult complete---------------------------");
		logger.debug("--------------------- Calling claimIdList object is ---------------------------"+claimIdList);
		if(claimIdList != null && claimIdList.size() > 0) {
			logger.debug("--------------------- Inside claimIdList object is ---------------------------"+claimIdList);

			result.setClaimId((claimIdList).get(0).getClaimId());
			result.setClaimNumber(cliamNumber);
			logger.debug("--------------------- After setting partnerClaimCreateIdResult.getClaimId()---------------------------"+result.getClaimId());
			logger.debug("--------------------- After Setting partnerClaimCreateIdResult.getClaimNumber()---------------------------"+result.getClaimNumber());

		}
		logger.debug("--------------------- Returning claimIdList object is ---------------------------"+claimIdList);


		return result;
	}	
//
	private static Map<String, String> populateAddressTypeFieldMap() {
	Map<String, String> addressTypeFieldMap = new HashMap<String, String>();
	addressTypeFieldMap.put("addressName", "Custom Address Name");
	addressTypeFieldMap.put("storeFrontName", "LXK MPS Store Front Name");
	addressTypeFieldMap.put("addressLine1", "Street Address");
	addressTypeFieldMap.put("addressLine2", "Street Address 2");
	addressTypeFieldMap.put("officeNumber", "House #");
	addressTypeFieldMap.put("city", "City");
	addressTypeFieldMap.put("state", "State");
	addressTypeFieldMap.put("province", "Province");
	addressTypeFieldMap.put("county", "County");
	addressTypeFieldMap.put("District", "District");
	addressTypeFieldMap.put("postalCode", "Postal Code");
	addressTypeFieldMap.put("country", "Country");
	return Collections.unmodifiableMap(addressTypeFieldMap);
	}
	
	private static Map<String, String> populateFavoriteAddressTypeFieldMap() {
	Map<String, String> favoriteAddressTypeFieldMap = new HashMap<String, String>();
	favoriteAddressTypeFieldMap.put("addressName", "Address Name");
	favoriteAddressTypeFieldMap.put("storeFrontName", "LXK MPS Store Front Name");
	favoriteAddressTypeFieldMap.put("addressLine1", "Street Address");
	favoriteAddressTypeFieldMap.put("addressLine2", "Street Address 2");
	favoriteAddressTypeFieldMap.put("officeNumber", "LXK MPS Office#");
	favoriteAddressTypeFieldMap.put("city", "City");
	favoriteAddressTypeFieldMap.put("state", "State");
	favoriteAddressTypeFieldMap.put("province", "Province");
	favoriteAddressTypeFieldMap.put("county", "LXK MPS County");
	favoriteAddressTypeFieldMap.put("District", "LXK MPS District");
	favoriteAddressTypeFieldMap.put("postalCode", "Postal Code");
	favoriteAddressTypeFieldMap.put("country", "Country");
	return Collections.unmodifiableMap(favoriteAddressTypeFieldMap);
	}
	
	public static List<Account> convertAccountListDoToPortalAccountList(List<PartnerAccountDo> accountListDo, boolean indirectOnly) {
		List<Account> portalAccountList = new ArrayList<Account>();
		if(isNotEmpty(accountListDo)) {
			for(PartnerAccountDo partnerAccount : accountListDo) {
				Account portalAccount = new Account();
				
				if(partnerAccount.getAccountTypes() != null) {
					portalAccount = setPartnerTypes(partnerAccount.getAccountTypes(), portalAccount);
				}
				
				if(partnerAccount instanceof PartnerCustomerAccountDo) {
					PartnerCustomerAccountDo customerAccount = (PartnerCustomerAccountDo) partnerAccount;
					portalAccount.setAccountId(customerAccount.getAccountId());
				} else {
					portalAccount.setAccountId(partnerAccount.getMdmLevel5AccountId());
				}
				
				portalAccount.setAccountName(partnerAccount.getAccountName());
				portalAccount.setOrganizationID(partnerAccount.getOrganizationId());
				portalAccount.setDefaultCurrency(partnerAccount.getCurrency());
				
				portalAccount.setMassUploadFlag(partnerAccount.isMassUploadFlag());
				portalAccount.setPartnerCountry(partnerAccount.getPartnerCountry());				
				
				portalAccount.setPartnerTypeList(getPartnerTypeList(accountListDo));
				portalAccount.setShipToDefault(partnerAccount.getShipToDefault());
				
				GenericAddress address = new GenericAddress();
				
				if(partnerAccount.getPrimaryAddressID()!=null)
				{
					address.setPrimaryAddressID(partnerAccount.getPrimaryAddressID());
					address.setPrimaryAddressName(partnerAccount.getPrimaryAddressName());
					address.setPrimaryAddressLine1(partnerAccount.getPrimaryAddressLine1());
					address.setPrimaryAddressLine2(partnerAccount.getPrimaryAddressLine2());
					address.setPrimaryAddressLine3(partnerAccount.getPrimaryAddressLine3());
					address.setPrimaryCity(partnerAccount.getPrimaryCity());
					address.setPrimaryState(partnerAccount.getPrimaryState());
					address.setPrimaryPostalCode(partnerAccount.getPrimaryPostalCode());
					address.setPrimaryProvince(partnerAccount.getPrimaryProvince());
					address.setPrimaryCountry(partnerAccount.getPrimaryCountry());
					address.setPrimaryCounty(partnerAccount.getPrimaryCounty());
					address.setPrimaryDistrict(partnerAccount.getPrimaryDistrict());
					address.setPrimaryOfficeNumber(partnerAccount.getPrimaryOfficeNumber());
					
				}
				address.setAddressId(partnerAccount.getAddressId());
				address.setAddressName(partnerAccount.getAddressName());
				address.setAddressLine1(partnerAccount.getAddressLine1());
				address.setAddressLine2(partnerAccount.getAddressLine2());
				address.setAddressLine3(partnerAccount.getAddressLine3());
				address.setCity(partnerAccount.getCity());
				address.setState(partnerAccount.getState());
				address.setPostalCode(partnerAccount.getPostalCode());
				address.setProvince(partnerAccount.getProvince());
				address.setCountry(partnerAccount.getCountry());
				address.setCounty(partnerAccount.getCounty());
				address.setDistrict(partnerAccount.getDistrict());
				address.setOfficeNumber(partnerAccount.getOfficeNumber());
				portalAccount.setAddress(address);
				
				if(partnerAccount.getFlags() != null) {
					portalAccount = setPartnerFlags(partnerAccount.getFlags(),portalAccount);
				}
				
				if (partnerAccount.getAccountTypes() != null) {
					//portalAccount = setAccountTypesFlags(partnerAccount.getAccountTypes(), portalAccount);
				}
				
				if(indirectOnly && portalAccount.isIndirectPartnerFlag()) {
					portalAccountList.add(portalAccount);
				} else if (!indirectOnly) {
					portalAccountList.add(portalAccount);
				}
				
			}
			
		}
		return portalAccountList;
		
	}
	
	private static Account setAccountTypesFlags(ArrayList<PartnerAccountTypeDo> accountTypes, Account portalAccount) {
		for (PartnerAccountTypeDo partnerAccountTypeDo : accountTypes) {
			if ("Can Create Child SR".equalsIgnoreCase(partnerAccountTypeDo.getType())) {
				portalAccount.setCreateChildSR(true);
			}
		}
		return portalAccount;
	}

	public static List<PartnerAccountTypeDo> getPartnerTypeList(List<PartnerAccountDo> partnerAccountDoList) {
		Set<PartnerAccountTypeDo> partnerTypeHash = new HashSet<PartnerAccountTypeDo>();
		
		for (PartnerAccountDo partnerAccountDo : LangUtil.notNull(partnerAccountDoList)) {
			if (partnerAccountDo.getAccountTypes() != null) {
				partnerTypeHash.addAll(partnerAccountDo.getAccountTypes());
			}
		}

		return new ArrayList<PartnerAccountTypeDo>(partnerTypeHash);
	}

	
	public static Account setPartnerFlags (List<? extends AccountFlag> flagList, Account portalAccount) {
		for(AccountFlag partnerAccountFlag : flagList) {
			if("Can View Invoice".equalsIgnoreCase(partnerAccountFlag.getType())) {
				if(partnerAccountFlag.isValue()) {
					portalAccount.setViewInvoiceFlag(true);
				}
			}
			
			if("Can Create Claims".equalsIgnoreCase(partnerAccountFlag.getType())) {
				if(partnerAccountFlag.isValue()) {
					portalAccount.setCreateClaimFlag(true);
				}
			}
			if("Can Order Parts On Update".equalsIgnoreCase(partnerAccountFlag.getType())) {
				if(partnerAccountFlag.isValue()) {
					portalAccount.setOrderPartsFlag(true);
				}
			}
			if("Allow Additional Payments".equalsIgnoreCase(partnerAccountFlag.getType())) {
				if(partnerAccountFlag.isValue()) {
					portalAccount.setAllowAdditionalPaymentRequestFlag(true);
				}
			}
			if("Can Create Address".equalsIgnoreCase(partnerAccountFlag.getType())) {
				if(partnerAccountFlag.isValue()) {
					portalAccount.setCreateShipToAddressFlag("true");
				}
			}
			if("Can View Invoice".equalsIgnoreCase(partnerAccountFlag.getType())) {
				if(partnerAccountFlag.isValue()) {
					portalAccount.setViewInvoiceFlag(true);
				}
			}

			if("Can Upload Claims".equalsIgnoreCase(partnerAccountFlag.getType())) {
				if(partnerAccountFlag.isValue()) {
					portalAccount.setUploadClaimFlag(true);
				}
			}
			if("Can Upload Activity".equalsIgnoreCase(partnerAccountFlag.getType())) {
				if(partnerAccountFlag.isValue()) {
					portalAccount.setUploadRequestFlag(true);
				}
			}
			if("Can Order Parts On Debrief".equalsIgnoreCase(partnerAccountFlag.getType())) {
				if(partnerAccountFlag.isValue()) {
					portalAccount.setOrderPartsDebriefFlag(true);
				}
			}
			if("Can Fulfill Orders".equalsIgnoreCase(partnerAccountFlag.getType())) {
				if(partnerAccountFlag.isValue()) {
					portalAccount.setAccessServiceOrderFlag(true);
				}
			}
			if("Can Create Customer Orders".equalsIgnoreCase(partnerAccountFlag.getType())) {
				if(partnerAccountFlag.isValue()) {
					portalAccount.setCreateRequestsForCustomerFlag(true);
				}
			}
			
			if("Can Debrief Install".equalsIgnoreCase(partnerAccountFlag.getType())) {
				if(partnerAccountFlag.isValue()) {
					portalAccount.setDebriefInstallFlag(true);
				}
			}
			if("Mass Upload Consumables".equalsIgnoreCase(partnerAccountFlag.getType())) {
				if(partnerAccountFlag.isValue()) {
					portalAccount.setMassUploadConsumablesFlag(true);
				}
			}
			if("Mass Upload Install Debrief".equalsIgnoreCase(partnerAccountFlag.getType())) {
				if(partnerAccountFlag.isValue()) {
					//portalAccount.setMassUploadInstallDebrief(true);
					portalAccount.setMassUploadInstallDebriefFlag(true);
				}
			}if("Partner Request Tab Hide".equalsIgnoreCase(partnerAccountFlag.getType())) {
				if(partnerAccountFlag.isValue()) {
					portalAccount.setPartnerRequestTabHideFlag(true);
				}
			}if ("Create-View All Customer Order".equalsIgnoreCase(partnerAccountFlag.getType())) {
				if (partnerAccountFlag.isValue()) {
					portalAccount.setViewAllCustomerOrderFlag(true);
				}
			} 
			if ("Can Create Child SR".equalsIgnoreCase(partnerAccountFlag.getType())) {
				portalAccount.setCreateChildSR(true);
			}
			if ("Submitted as Claim".equalsIgnoreCase(partnerAccountFlag.getType())) {   //added as per tech doc 15.7 (CRM-AGriese201408081239)
				if (partnerAccountFlag.isValue()) {
					portalAccount.setMaintenanceKit(true);
				}
			} 
		
		}
		return portalAccount;
	}
	
	public static Account setPartnerTypes (List<? extends AccountType> typeList ,Account portalAccount) {
		for(AccountType partnerAccountType : typeList) {

			if(partnerAccountType != null ) {
				String partnerType = partnerAccountType.getType();
				if("Service Provider".equalsIgnoreCase(partnerType) || "Lexmark Technician".equalsIgnoreCase(partnerType)) {
					portalAccount.setDirectPartnerFlag(true);
				} else if ("Authorized Service Partner".equalsIgnoreCase(partnerType) ||
						"Self Maintainer".equalsIgnoreCase(partnerType)
						|| "Designated Service Partner".equalsIgnoreCase(partnerType) ) {
					portalAccount.setIndirectPartnerFlag(true);
				} else {
					portalAccount.setLogisticsPartnerFlag(true);
				}
			}
		}
		
		return portalAccount;
	}
}
