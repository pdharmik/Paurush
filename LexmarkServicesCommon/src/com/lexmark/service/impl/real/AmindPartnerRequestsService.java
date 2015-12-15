package com.lexmark.service.impl.real;

import static com.lexmark.service.impl.real.partnerActivityService.AmindPartnerActivitySearchSpecUtil.buildActivityListSearchSpec;
import static com.lexmark.service.impl.real.partnerActivityService.AmindPartnerActivitySearchSpecUtil.buildChildSRfilterSearchSpec;
import static com.lexmark.service.impl.real.partnerActivityService.AmindPartnerActivitySearchSpecUtil.buildRequestTypeSearchSpec;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.amind.common.service.SiebelBusinessServiceProxy;
import com.amind.data.service.ActionEvent;
import com.amind.data.service.IDataManager;
import com.amind.data.service.QueryObject;
import com.amind.session.Session;
import com.amind.session.SessionFactory;
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
import com.lexmark.service.api.PartnerRequestsService;
import com.lexmark.service.api.SiebelCrmServiceException;
import com.lexmark.service.impl.real.domain.PartnerActivityDo;
import com.lexmark.service.impl.real.domain.PartnerActivityGridDo;
import com.lexmark.service.impl.real.domain.PartnerActivityUpdateDO;
import com.lexmark.service.impl.real.partnerActivityService.AmindPartnerActivitySearchSpecUtil;
import com.lexmark.service.impl.real.requestService.AcceptRequestService;
import com.lexmark.service.impl.real.util.AmindPartnerDataManagerUtil;
import com.lexmark.service.impl.real.util.AmindServiceUtil;
import com.lexmark.service.impl.real.util.LogUtil;
import com.lexmark.util.LangUtil;
import com.siebel.data.SiebelPropertySet;

public class AmindPartnerRequestsService extends AmindSiebelCrmService
		implements PartnerRequestsService {

	/* used for Filter and Sort both */
	private static final Map<String, String> ACTIVITY_FIELD_MAP = populateActivityFieldMap();
	private  Class<?> DO_CLASS= null;
	
	private SessionFactory statelessSessionFactory;

	AmindPartnerRequestsService() {
	}

	public void setStatelessSessionFactory(
			SessionFactory statelessSessionFactory) {
		this.statelessSessionFactory = statelessSessionFactory;
	}

	public SessionFactory getStatelessSessionFactory() {
		return statelessSessionFactory;
	}

	@Override
	public ActivityListResult retrieveActivityList(ActivityListContract contract) throws Exception {     //two do's used in same method. Please keep that in mind when making changes to search spec.

		logger.debug("[IN] retrieveActivityList");
		AmindCrmSessionHandle crmSessionHandle = (AmindCrmSessionHandle) contract.getSessionHandle();
		ActivityListResult result = new ActivityListResult();
		
		StringBuilder sortSpec = new StringBuilder();
		Session session = null;
		try {
			AmindPartnerActivitySearchSpecUtil.checkActivityListRequiredFields(contract);

			session = crmSessionHandle.acquireMultiple();
			IDataManager dataManager = session.getDataManager();
			QueryObject criteria;
			if (contract.isRequestGridLoading()) {   // RequestGridLoading flag determines if the call is for grid loading or not. The DO changes based in this flag.
				DO_CLASS = PartnerActivityGridDo.class;
			} else {
				DO_CLASS = PartnerActivityDo.class;
			}
			criteria = new QueryObject(DO_CLASS, ActionEvent.QUERY);
			
			String requestTypeSearchSpec = buildRequestTypeSearchSpec(contract);
			
			String searchSpec = buildActivityListSearchSpec(contract, dataManager, ACTIVITY_FIELD_MAP, true);
			searchSpec += overrideOfferingFilter();
			searchSpec += requestTypeSearchSpec;
			
			if ("true".equalsIgnoreCase(contract.getCreateChildSR())) {
				String childSRfilterSearchSpec = buildChildSRfilterSearchSpec(contract);
				if (childSRfilterSearchSpec != null) {
					searchSpec += childSRfilterSearchSpec;
				}
			}
			
			if (contract.isCountFlag()) {
				SiebelBusinessServiceProxy businessServiceProxy = session.getSiebelBusServiceProxy();
				result.setCountFlag(AmindServiceUtil.isRecordExist(
						"LXK SW Partner Portal BO",
						"LXK SW Action - Partner Portal",
						searchSpec, businessServiceProxy));
			} else {
				if (contract.getSortCriteria() != null && !contract.getSortCriteria().isEmpty()) {
					sortSpec.append(AmindServiceUtil.buildSortString(contract.getSortCriteria(), ACTIVITY_FIELD_MAP));
				}

				criteria = AmindServiceUtil.buildBasicQueryObject(criteria,	contract.getIncrement(), contract.getStartRecordNumber());
				criteria.addComponentSearchSpec(DO_CLASS, searchSpec); //  DO used for only grid loading. Search specs are common for both DO's.
				criteria.setSortString(sortSpec.toString());

				result = AmindPartnerDataManagerUtil.queryAndGetActivityList(contract, session, dataManager, searchSpec, crmSessionHandle, criteria);
			}

		} catch (Exception e) {
		/*	LogUtil.logAmindServiceCallException(
					LogUtil.stackTraceExecutionPoint(), e, contract);*/
			throw new SiebelCrmServiceException("retrieveActivityList failed",
					e);
		} finally {
			if (session != null) {
				AmindServiceUtil.releaseAmindCrmMultipleSessionHandle(crmSessionHandle, session);
			}
		}

		logger.debug("[OUT] retrieveActivityList");
		return result;
	}

	private static String overrideOfferingFilter() {
		StringBuilder sb = new StringBuilder();
		sb.append(" AND (");
		sb.append("[LXK MPS Override Offering] <> 'Consumables Order and Activity'");
		sb.append(" AND [LXK MPS Override Offering] <> 'Consumables Order'");
		sb.append(" AND [LXK MPS Override Offering] <> 'Consumables Activity'");
		sb.append(" OR [LXK MPS Override Offering] IS NULL ");
		sb.append(")");
		return sb.toString();
	}

	public AssignedTechnicianUpdateResult updateAssignedTechnician(
			AssignedTechnicianUpdateContract contract) throws Exception {
		logger.debug("[IN] updateAssignedTechnician");

		Session session = null;
		AssignedTechnicianUpdateResult result = new AssignedTechnicianUpdateResult();
		try {
			session = getStatelessSessionFactory().attachSession();

			IDataManager dataManager = session.getDataManager();
			PartnerActivityUpdateDO activity = (PartnerActivityUpdateDO) dataManager
					.queryById(PartnerActivityUpdateDO.class,
							contract.getActivityId());
			/*
			 * if it tries to assign to the same person, its been assigned then
			 * don't do anything
			 */
			if (activity != null
					&& activity.getTechnicianId() != null
					&& !activity.getTechnicianId().equalsIgnoreCase(
							contract.getEmployeeId())) {
				SiebelPropertySet input = new SiebelPropertySet();
				input.setProperty("Activity Id", contract.getActivityId()
						.trim().toUpperCase());
				input.setProperty("Tech Id", contract.getEmployeeId().trim()
						.toUpperCase());
				SiebelBusinessServiceProxy businessServiceProxy = session
						.getSiebelBusServiceProxy();
				SiebelPropertySet output = businessServiceProxy.InvokeMethod(
						"LXK SW Activtiy Assign - Partner Portal",
						"AssignActivity", input);
				result.setResult(output.getProperty("Return Message")
						.equalsIgnoreCase("Success"));
			} else {
				result.setResult(true);
			}
		} catch (Exception e) {
			LogUtil.logAmindServiceCallException(
					LogUtil.stackTraceExecutionPoint(), e, contract);
			throw new SiebelCrmServiceException(
					"updateAssignedTechnician failed", e);
		} finally {
			AmindServiceUtil.releaseSession(session);
		}
		logger.debug("[OUT] updateAssignedTechnician");
		return result;
	}

	public ServiceActivityHistoryListResult retrieveServiceActivityHistoryList(
			ServiceActivityHistoryListContract contract) throws Exception {
		logger.debug("[IN] retrieveServiceActivityHistoryList");
		Session session = null;
		ServiceActivityHistoryListResult result = new ServiceActivityHistoryListResult();
		try {
			/* check for required fields */
			if (StringUtils.isEmpty(contract.getAssetId())) {
				throw new IllegalArgumentException(
						"Asset Id can not be empty or null");
			}

			session = getStatelessSessionFactory().attachSession();
			IDataManager dataManager = session.getDataManager();
			result = AmindPartnerDataManagerUtil.queryandGetServiceHistoryList(dataManager, contract);
			
		} catch (Exception e) {
			LogUtil.logAmindServiceCallException(
					LogUtil.stackTraceExecutionPoint(), e, contract);
			throw new SiebelCrmServiceException(
					"retrieveServiceActivityHistoryList failed", e);
		} finally {
			AmindServiceUtil.releaseSession(session);
		}
		logger.debug("[OUT] retrieveServiceActivityHistoryList");
		return result;
	}

	public ServiceActivityHistoryDetailResult retrieveServiceActivityHistoryDetail(
			ServiceActivityHistoryDetailContract contract) throws Exception {
		logger.debug("[IN] retrieveServiceActivityHistoryDetail");
		Session session = null;
		ServiceActivityHistoryDetailResult result = new ServiceActivityHistoryDetailResult();
		try {
			/* check for required fields */
			if (StringUtils.isEmpty(contract.getServiceRequestId())) {
				throw new IllegalArgumentException(
						"Service Request Id can not be empty or null");
			}

			session = getStatelessSessionFactory().attachSession();
			IDataManager dataManager = session.getDataManager();
			result = AmindPartnerDataManagerUtil
					.queryandGetServiceHistoryDetail(dataManager, contract);
		} catch (Exception e) {
			LogUtil.logAmindServiceCallException(
					LogUtil.stackTraceExecutionPoint(), e, contract);
			throw new SiebelCrmServiceException(
					"retrieveServiceActivityHistoryDetail failed", e);
		} finally {
			AmindServiceUtil.releaseSession(session);
		}
		logger.debug("[OUT] retrieveServiceActivityHistoryDetail");
		return result;
	}

	@Override
	public OpenClaimListResult retrieveOpenClaimList(
			OpenClaimListContract contract) throws Exception {
		logger.debug("[IN] retrieveOpenClaimList Detail");
		OpenClaimListResult claimResult = new OpenClaimListResult();
		Session session = null;
		try {
			if (StringUtils.isEmpty(contract.getAssetId())) {
				throw new IllegalArgumentException(
						"Asset Id can not be empty or null");
			}
			if (StringUtils.isEmpty(contract.getMdmId())) {
				throw new IllegalArgumentException(
						"MDM Id can not be empty or null");
			}
			if (StringUtils.isEmpty(contract.getMdmLevel())) {
				throw new IllegalArgumentException(
						"MDM Level can not be empty or null");
			}

			session = getStatelessSessionFactory().attachSession();
			IDataManager dataManager = session.getDataManager();
			claimResult = AmindPartnerDataManagerUtil.queryandGetOpenClaimList(
					dataManager, contract);
		} catch (Exception e) {
			LogUtil.logAmindServiceCallException(
					LogUtil.stackTraceExecutionPoint(), e, contract);
			throw new SiebelCrmServiceException("retrieveOpenClaimList failed",
					e);
		} finally {
			AmindServiceUtil.releaseSession(session);
		}

		logger.debug("[OUT ] retrieveOpenClaimList Detail");
		return claimResult;
	}

	public GlobalPartnerAssetListResult retrieveGlobalPartnerAssetList(
			GlobalPartnerAssetListContract contract) {
		logger.debug("[IN] retrieveGlobalPartnerAssetList");
		GlobalPartnerAssetListResult assetResult = new GlobalPartnerAssetListResult();
		Session session = null;
		try {
			if (StringUtils.isEmpty(contract.getSerialNumber())) {
				throw new IllegalArgumentException(
						"Serial Number can not be empty or null");
			}

			session = getStatelessSessionFactory().attachSession();
			IDataManager dataManager = session.getDataManager();
			assetResult = AmindPartnerDataManagerUtil
					.queryandGetPartnerAssetList(dataManager, contract);

		} catch (Exception e) {
			LogUtil.logAmindServiceCallException(
					LogUtil.stackTraceExecutionPoint(), e, contract);
			throw new SiebelCrmServiceException("retrieveDeviceDetail failed",
					e);
		} finally {
			AmindServiceUtil.releaseSession(session);
		}
		logger.debug("[OUT] retrieveGlobalPartnerAssetList");
		return assetResult;
	}

	public GlobalAssetDetailResult retrieveGlobalAssetDetail(
			GlobalAssetDetailContract contract) throws Exception {
		logger.debug("[IN] retrieveGlobalPartnerAsset Detail");
		GlobalAssetDetailResult assetResult = new GlobalAssetDetailResult();
		Session session = null;
		try {
			if (StringUtils.isEmpty(contract.getAssetId())) {
				throw new IllegalArgumentException(
						"Asset Id can not be empty or null");
			}

			session = getStatelessSessionFactory().attachSession();
			IDataManager dataManager = session.getDataManager();
			assetResult = AmindPartnerDataManagerUtil
					.queryandGetPartnerAssetDetail(dataManager, contract);
		} catch (Exception e) {
			LogUtil.logAmindServiceCallException(
					LogUtil.stackTraceExecutionPoint(), e, contract);
			throw new SiebelCrmServiceException(
					"retrieveGlobalPartnerAsset failed", e);
		} finally {
			AmindServiceUtil.releaseSession(session);
		}
		// logger.info("------------------WIPRO 2---------------");
		logger.debug("[OUT ] retrieveGlobalPartnerAsset Detail");
		return assetResult;
	}

	@Override
	public PartnerIndirectAccountListResult retrievePartnerIndirectAccountList(
			PartnerIndirectAccountListContract contract) throws Exception {

		logger.debug("[IN] retrievePartnerIndirectAccountList");
		PartnerIndirectAccountListResult result = new PartnerIndirectAccountListResult();
		Session session = null;
		try {
			if (StringUtils.isEmpty(contract.getMdmId())) {
				throw new IllegalArgumentException("Mdm Id is null or empty");

			}
			if (StringUtils.isEmpty(contract.getMdmLevel())) {
				throw new IllegalArgumentException("Mdm Level is null or empty");
			}

			session = getStatelessSessionFactory().attachSession();
			IDataManager dataManager = session.getDataManager();
			result = AmindPartnerDataManagerUtil
					.queryandGetPartnerIndirectAccountList(dataManager,
							contract);
		} catch (Exception e) {
			LogUtil.logAmindServiceCallException(
					LogUtil.stackTraceExecutionPoint(), e, contract);
			throw new SiebelCrmServiceException(
					"retrievePartnerAccountList failed.", e);
		} finally {
			AmindServiceUtil.releaseSession(session);
		}
		logger.debug("[OUT] retrievePartnerIndirectAccountList");
		return result;
	}

	@Override
	public CustomerAccountListResult retrieveCustomerAccountList(
			CustomerAccountListContract contract) throws Exception {
		logger.debug("[IN] retrieveCustomerAccountList");
		CustomerAccountListResult result = new CustomerAccountListResult();
		Session session = null;
		try {
			if (StringUtils.isEmpty(contract.getMdmId())) {
				throw new IllegalArgumentException("Mdm Id is null or empty");
			}
			if (StringUtils.isEmpty(contract.getMdmLevel())) {
				throw new IllegalArgumentException("Mdm Level is null or empty");
			}

			session = getStatelessSessionFactory().attachSession();
			IDataManager dataManager = session.getDataManager();
			result = AmindPartnerDataManagerUtil
					.queryandGetCustomerAccountList(dataManager, contract);
		} catch (Exception e) {
			LogUtil.logAmindServiceCallException(
					LogUtil.stackTraceExecutionPoint(), e, contract);
			throw new SiebelCrmServiceException(
					"retrieveCustomerAccountList failed.", e);
		} finally {
			AmindServiceUtil.releaseSession(session);
		}
		logger.debug("[OUT] retrieveCustomerAccountList");
		return result;
	}

	@Override
	/* modelNumber is not part of query to get FruPart Detail */
	public FRUPartDetailResult retrieveFRUPart(FRUPartDetailContract contract) {
		logger.debug("[IN] retrieveFRUPart");

		FRUPartDetailResult result = new FRUPartDetailResult();
		Session session = null;

		try {
			if (StringUtils.isEmpty(contract.getPartnerOrg())) {
				throw new IllegalArgumentException(
						"Partner Organization is null or empty");
			}
			if (StringUtils.isEmpty(contract.getPartNumber())) {
				throw new IllegalArgumentException(
						"Part Number is null or empty");
			}
			if (StringUtils.isEmpty(contract.getModelNumber())) {
				throw new IllegalArgumentException(
						"Model Number is null or empty");
			}

			session = getStatelessSessionFactory().attachSession();
			IDataManager dataManager = session.getDataManager();
			result = AmindPartnerDataManagerUtil.queryandGetFruPartDetail(
					dataManager, contract);
		} catch (Exception e) {
			LogUtil.logAmindServiceCallException(
					LogUtil.stackTraceExecutionPoint(), e, contract);
			throw new SiebelCrmServiceException("retrieveFRUPart failed.", e);
		} finally {
			AmindServiceUtil.releaseSession(session);
		}
		logger.debug("[OUT] retrieveFRUPart");
		return result;
	}

	@Override
	public FRUPartListResult retrieveFRUPartList(FRUPartListContract contract) {
		logger.debug("[IN] retrieveFRUPartList");
		FRUPartListResult result = new FRUPartListResult();
		Session session = null;
		try {
			if (StringUtils.isEmpty(contract.getModelNumber())) {
				throw new IllegalArgumentException(
						"Model Number is null or empty");
			}

			session = getStatelessSessionFactory().attachSession();
			IDataManager dataManager = session.getDataManager();
			result = AmindPartnerDataManagerUtil.queryandGetFruPartList(
					dataManager, contract);
		} catch (Exception e) {
			LogUtil.logAmindServiceCallException(
					LogUtil.stackTraceExecutionPoint(), e, contract);
			throw new SiebelCrmServiceException("retrieveFRUPartList failed.",
					e);
		} finally {
			AmindServiceUtil.releaseSession(session);
		}
		logger.debug("[OUT] retrieveFRUPartList");
		return result;
	}

	@Override
	public TechnicianListResult retrieveTechnicianList(
			TechnicianListContract contract) throws Exception {
		logger.debug("[IN] retrieveTechnicianList");
		TechnicianListResult result = new TechnicianListResult();
		Session session = null;
		try {
			if (StringUtils.isEmpty(contract.getPartnerAccountId())) {
				throw new IllegalArgumentException(
						"Partner Account Id is null or empty");
			}

			session = getStatelessSessionFactory().attachSession();
			IDataManager dataManager = session.getDataManager();
			result = AmindPartnerDataManagerUtil.queryandGetTechnicianList(
					dataManager, contract);
		} catch (Exception e) {
			LogUtil.logAmindServiceCallException(
					LogUtil.stackTraceExecutionPoint(), e, contract);
			throw new SiebelCrmServiceException(
					"retrieveTechnicianList failed.", e);
		} finally {
			AmindServiceUtil.releaseSession(session);
		}
		logger.debug("[OUT] retrieveTechnicianList");
		return result;
	}

	@Override
	public PartnerAddressListResult retrievePartnerAddressList(PartnerAddressListContract contract) throws Exception {
		logger.debug("[IN] retrievePartnerAddressList");
		PartnerAddressListResult result = new PartnerAddressListResult();
		Session session = null;
		AmindCrmSessionHandle amindCrmSessionHandle = null;
		Session totalCountSession = null;
	    Session favoritesSession = null;
		try {
			if (StringUtils.isEmpty(contract.getAccountID())) {
				throw new IllegalArgumentException(
						"Account Id is null or empty");
			}
			if (StringUtils.isEmpty(contract.getContactId())) {
				throw new IllegalArgumentException(
						"Contact Id is null or empty");
			}
			
			amindCrmSessionHandle = (AmindCrmSessionHandle) contract.getSessionHandle();
			session = amindCrmSessionHandle.acquireMultiple();
			IDataManager dataManager = session.getDataManager();
			
			totalCountSession = getStatelessSessionFactory().attachSession();
			
			if(!contract.isFavoriteFlag()) {
				favoritesSession = getStatelessSessionFactory().attachSession();
			}
			
			result = AmindPartnerDataManagerUtil.queryandGetPartnerAddressList(
					dataManager, contract, amindCrmSessionHandle,totalCountSession,favoritesSession);
			
		} catch (Exception e) {
			LogUtil.logAmindServiceCallException(
					LogUtil.stackTraceExecutionPoint(), e, contract);
			throw new SiebelCrmServiceException(
					"retrievePartnerAddressList failed.", e);
		} finally {
			AmindServiceUtil.releaseAmindCrmMultipleSessionHandle(amindCrmSessionHandle, session);
			AmindServiceUtil.releaseSession(totalCountSession);
			AmindServiceUtil.releaseSession(favoritesSession);
		}
		logger.debug("[OUT] retrievePartnerAddressList");
		return result;
	}

	public PartnerFavoriteAddressUpdateResult updatePartnerUserFavoriteAddress(
			PartnerFavoriteAddressUpdateContract contract) throws Exception {
		logger.debug("[IN] updatePartnerUserFavoriteAddress");
		PartnerFavoriteAddressUpdateResult result = new PartnerFavoriteAddressUpdateResult();
		Session session = null;
		try {
			if (StringUtils.isEmpty(contract.getPartnerAccountId())) {
				throw new IllegalArgumentException(
						"Partner Account Id is null or empty");
			}
			if (StringUtils.isEmpty(contract.getContactId())) {
				throw new IllegalArgumentException(
						"Contact Id is null or empty");
			}
			if (StringUtils.isEmpty(contract.getFavoriteAddressId())) {
				throw new IllegalArgumentException(
						"Address Id is null or empty");
			}

			session = getStatelessSessionFactory().attachSession();
			IDataManager dataManager = session.getDataManager();
			result = AmindPartnerDataManagerUtil
					.queryandUpdateUserFavoriteAddress(dataManager, contract);
		} catch (Exception e) {
			LogUtil.logAmindServiceCallException(
					LogUtil.stackTraceExecutionPoint(), e, contract);
			throw new SiebelCrmServiceException(
					"updatePartnerUserFavoriteAddress failed.", e);
		} finally {
			AmindServiceUtil.releaseSession(session);
		}
		logger.debug("[OUT] updatePartnerUserFavoriteAddress");
		return result;
	}

	@Override
	public ClaimDetailResult retrieveClaimDetail(ClaimDetailContract contract)
			throws Exception {
		logger.debug("[IN] retrieveClaimDetail");
		ClaimDetailResult result = new ClaimDetailResult();
		Session session = null;
		try {
			if (StringUtils.isEmpty(contract.getActivityId())) {
				throw new IllegalArgumentException(
						"Activity Id is null or empty");
			}

			session = getStatelessSessionFactory().attachSession();
			IDataManager dataManager = session.getDataManager();
			result = AmindPartnerDataManagerUtil.queryandGetClaimDetail(
					dataManager, contract);
		} catch (Exception e) {
			LogUtil.logAmindServiceCallException(
					LogUtil.stackTraceExecutionPoint(), e, contract);
			throw new SiebelCrmServiceException("retrieveClaimDetail failed.",
					e);
		} finally {
			AmindServiceUtil.releaseSession(session);

		}
		logger.debug("[OUT] retrieveClaimDetail");
		return result;
	}

	@Override
	public ActivityDetailResult retrieveActivityDetail(
			ActivityDetailContract contract) throws Exception {
		logger.debug("[IN] retrieveActivityDetail");
		ActivityDetailResult result = new ActivityDetailResult();
		Session session = null;
		try {
			if (StringUtils.isEmpty(contract.getActivityId())) {
				throw new IllegalArgumentException(
						"Activity Id is null or empty");
			}

			session = getStatelessSessionFactory().attachSession();
			IDataManager dataManager = session.getDataManager();
			result = AmindPartnerDataManagerUtil.queryandGetActivityDetail(
					dataManager, contract);
		} catch (Exception e) {
			LogUtil.logAmindServiceCallException(
					LogUtil.stackTraceExecutionPoint(), e, contract);
			throw new SiebelCrmServiceException(
					"retrieveActivityDetail failed.", e);
		} finally {
			AmindServiceUtil.releaseSession(session);

		}
		logger.debug("[OUT] retrieveActivityDetail");
		return result;
	}

	@Override
	public PartnerNotificationsResult retrievePartnerNotifications(
			PartnerNotificationsContract contract) throws Exception {
		logger.debug("[IN] retrievePartnerNotifications");
		PartnerNotificationsResult result = new PartnerNotificationsResult();
		if (!StringUtils.isEmpty(contract.getEmailAddress())) {
			Session session = null;
			try {
				if (StringUtils.isEmpty(contract.getServiceRequestId())) {
					throw new IllegalArgumentException(
							"Service Request Id is null or empty");
				}

				session = getStatelessSessionFactory().attachSession();
				IDataManager dataManager = session.getDataManager();
				result = AmindPartnerDataManagerUtil
						.queryandGetNotificationList(dataManager, contract);
			} catch (Exception e) {
				LogUtil.logAmindServiceCallException(
						LogUtil.stackTraceExecutionPoint(), e, contract);
				throw new SiebelCrmServiceException(
						"retrievePartnerNotifications failed.", e);
			} finally {
				AmindServiceUtil.releaseSession(session);

			}
		}
		logger.debug("[OUT] retrievePartnerNotifications");
		return result;
	}

	@Override
	public ValidateInstalledPrinterSerialNumberResult validateInstalledPrinterSerialNumber(
			ValidateInstalledPrinterSerialNumberContract contract)
			throws Exception {
		logger.debug("[IN] validateInstalledPrinterSerialNumber");
		ValidateInstalledPrinterSerialNumberResult result = new ValidateInstalledPrinterSerialNumberResult();
		Session session = null;
		try {
			if (StringUtils.isEmpty(contract.getModelNumber())) {
				throw new IllegalArgumentException(
						"Model Number is null or empty");
			}
			if (StringUtils.isEmpty(contract.getSerialNumber())) {
				throw new IllegalArgumentException(
						"Serial Number is null or empty");
			}

			session = getStatelessSessionFactory().attachSession();
			IDataManager dataManager = session.getDataManager();
			result = AmindPartnerDataManagerUtil
					.queryandValidateInstalledPrinterSerialNumber(dataManager,
							contract);
		} catch (Exception e) {
			LogUtil.logAmindServiceCallException(
					LogUtil.stackTraceExecutionPoint(), e, contract);
			throw new SiebelCrmServiceException(
					"validateInstalledPrinterSerialNumber failed.", e);
		} finally {
			AmindServiceUtil.releaseSession(session);

		}
		logger.debug("[OUT] validateInstalledPrinterSerialNumber");
		return result;
	}

	public DownloadClaimListResult retrieveDownloadClaimList(
			ActivityListContract contract) throws Exception {
		logger.debug("[IN] retrieveDownloadClaimList");
		DownloadClaimListResult result = new DownloadClaimListResult();
		Session session = null;
		try {
			AmindPartnerActivitySearchSpecUtil
					.checkActivityListRequiredFields(contract);

			session = getStatelessSessionFactory().attachSession();
			IDataManager dataManager = session.getDataManager();
			result = AmindPartnerDataManagerUtil.queryAndGetdonwloadClaimList(
					dataManager, contract, ACTIVITY_FIELD_MAP);

		} catch (Exception e) {
			LogUtil.logAmindServiceCallException(
					LogUtil.stackTraceExecutionPoint(), e, contract);
			throw new SiebelCrmServiceException(
					"retrieveDownloadClaimList failed", e);
		} finally {
			AmindServiceUtil.releaseSession(session);

		}
		logger.debug("[OUT] retrieveDownloadClaimList");
		return result;
	}

	public DownloadRequestListResult retrieveDownloadRequestList(
			ActivityListContract contract) throws Exception {
		logger.debug("[IN] retrieveDownloadClaimList");
		DownloadRequestListResult result = new DownloadRequestListResult();
		Session session = null;
		try {
			AmindPartnerActivitySearchSpecUtil
					.checkActivityListRequiredFields(contract);

			session = getStatelessSessionFactory().attachSession();
			IDataManager dataManager = session.getDataManager();
			result = AmindPartnerDataManagerUtil
					.queryAndGetdonwloadRequestList(dataManager, contract,
							ACTIVITY_FIELD_MAP);
		} catch (Exception e) {
			LogUtil.logAmindServiceCallException(
					LogUtil.stackTraceExecutionPoint(), e, contract);
			throw new SiebelCrmServiceException(
					"retrieveDownloadRequestList failed", e);
		} finally {
			AmindServiceUtil.releaseSession(session);

		}

		logger.debug("[OUT] retrieveDownloadClaimList");
		return result;
	}

	public PartnerClaimCreateIdResult retrieveCreateClaimId(String claimNumber)
			throws Exception {
		logger.debug("[IN] retrieveCreateClaimId");
		PartnerClaimCreateIdResult result = new PartnerClaimCreateIdResult();
		Session session = null;
		try {
			/* check for required fields */
			if (LangUtil.isBlank(claimNumber)) {
				throw new IllegalArgumentException(
						"claimNumber is null or blank");
			}

			session = getStatelessSessionFactory().attachSession();
			IDataManager dataManager = session.getDataManager();
			logger.debug(">>dataManager:" + dataManager);
			result = AmindPartnerDataManagerUtil.queryandGetClaimId(
					dataManager, claimNumber);

		} catch (Exception e) {
			LogUtil.logAmindServiceCallException(
					LogUtil.stackTraceExecutionPoint(), e, "claimNumber: "
							+ claimNumber);
			throw new SiebelCrmServiceException("retrieveCreateClaimId failed",
					e);
		} finally {
			AmindServiceUtil.releaseSession(session);

		}

		logger.debug("[OUT] retrieveCreateClaimId");
		return result;
	}
	
    @Override
    public RequestAcceptResult acceptRejectRequest(RequestAcceptContract contract) throws LGSCheckedException, LGSRuntimeException {
    	RequestAcceptResult result = new RequestAcceptResult();
        logger.debug("[IN] Accept Request ");
        result.setResult(true);
        Session session = statelessSessionFactory.attachSession();
        AcceptRequestService service = new AcceptRequestService(session, contract);
        try{
        	
//          service.buildSearchExpression();
//          service.updateAcceptRequest(service.queryAndGetAcceptRequests());
        	
        	service.updateAcceptRequest();

        }catch (Exception e) {
            result.setResult(false);
            LogUtil.logAmindServiceCallException(LogUtil.stackTraceExecutionPoint(), e, contract);
            throw new SiebelCrmServiceException("update accept request failed", e);
        }finally{
            if(session != null){
            	AmindServiceUtil.releaseSession(session);

            }
        }
        logger.debug("[OUT] Accept Request ");
        return result;
    }

	private static Map<String, String> populateActivityFieldMap() {
		Map<String, String> activityFieldMap = new HashMap<String, String>();
		activityFieldMap.put("Activity.activityDate", "LXK SW Activity Created");
		activityFieldMap.put("Activity.serviceRequest.serviceRequestNumber", "SR Number");
		activityFieldMap.put("Activity.serviceProviderReferenceNumber",	"LXK SD SP Reference");
		activityFieldMap.put("Activity.serviceRequest.asset.productLine", "LXK R SR Product Line");
		activityFieldMap.put("Activity.serviceRequest.asset.modelNumber", "LXK SD SR Product MTM Name");
		activityFieldMap.put("Activity.serviceRequest.asset.productTLI", "LXK SD SR Product TLI");
		activityFieldMap.put("Activity.serviceRequest.asset.serialNumber", "Serial Number");
		activityFieldMap.put("Activity.serviceRequest.asset.activitynumber", "Activity UID");
		activityFieldMap.put("Activity.serviceRequest.asset.installDate", "LXK MPS Install Date PreDebriefRFV");
		activityFieldMap.put("Activity.activityStatus", "Status");
		activityFieldMap.put("Activity.activitySubStatus", "LXK R Substatus");
		activityFieldMap.put("Activity.partnerAccount.accountName",	"LXK R Service Provider Name");
		activityFieldMap.put("Activity.technician.firstName", "LXL SW Activity Technician First Name"); 
		activityFieldMap.put("Activity.technician.lastName", "LXK SW Activity Technician Last Name"); 
		activityFieldMap.put("Activity.customerAccount.accountName", "Account Name");
		activityFieldMap.put("Activity.customerRequestedResponseDate", "LXK C New Intervention Date");
		activityFieldMap.put("Activity.serviceRequest.serviceAddress.addressLine1", "Service Street Address");
		activityFieldMap.put("Activity.serviceRequest.serviceAddress.city",	"Service City");
		activityFieldMap.put("Activity.serviceRequest.serviceAddress.state", "Service State");
		activityFieldMap.put("Activity.serviceRequest.serviceAddress.province",	"Service Province");
		activityFieldMap.put("Activity.serviceRequest.serviceAddress.postalCode", "Service Postal Code");
		activityFieldMap.put("Activity.serviceRequest.serviceAddress.officeNumber", "Service Office #");
		activityFieldMap.put("Activity.serviceRequest.serviceAddress.county", "Service County");
		activityFieldMap.put("Activity.serviceRequest.serviceAddress.district", "Service District");
		activityFieldMap.put("Activity.serviceRequest.serviceAddress.country", "Service Country");
		activityFieldMap.put("Activity.serviceRequest.primaryContact.firstName", "LXK SW SR Primary Contact First Name");
		activityFieldMap.put("Activity.serviceRequest.primaryContact.lastName",	"LXK SW SR Primary Contact Last Name");
		activityFieldMap.put("Activity.serviceRequest.serviceRequestDate",	"LXK SW SR Created Date");
		activityFieldMap.put("Activity.serviceRequest.requestType",	"LXK SW Service Request Type");
		activityFieldMap.put("Activity.serviceRequest.serviceRequestStatus", "SR Status");
		activityFieldMap.put("Activity.serviceRequest.projectName", "LXK MPS Project");
		activityFieldMap.put("Activity.serviceRequest.actualStartDate", "LXK R Project Item Actual Start");
		activityFieldMap.put("Activity.serviceRequest.storeFrontName", "LXK MPS Store Front Name");
		activityFieldMap.put("Activity.serviceRequest.statusDetail", "LXK MPS SR Sub Area"); 
		activityFieldMap.put("mdmLevel5AccountId", "LXK R Service Provider Id");
		activityFieldMap.put("mdmLevel1AccountId", "LXK SW SP Global Ultimate DUNS");
		activityFieldMap.put("mdmLevel2AccountId", "LXK SW SP Domestic Ultimate DUNS");
		activityFieldMap.put("mdmLevel3AccountId", "LXK SW SP Legal Entity Number");
		activityFieldMap.put("mdmLevel4AccountId", "LXK SW SP MDM Account Number");
		activityFieldMap.put("Activity.activityType", "Type");
		activityFieldMap.put("Activity.partnerAccountName", "LXK R Service Provider Name"); 
		activityFieldMap.put("Activity.responseMetric", "LXK SW Response Metric Calc"); 
		activityFieldMap.put("Activity.claimStatusDate", "LXK R Sub Status Update");
		activityFieldMap.put("Activity.activityId", "Id");
		activityFieldMap.put("Activity.serviceRequest.asset.productModelNumber", "LXK HW Install Model");
		activityFieldMap.put("Activity.agencyContactLastName", "Agency Contact Last Name");
		activityFieldMap.put("Activity.agencyContactFirstName", "Agency Contact First Name");
		activityFieldMap.put("Activity.agencyContacteMail", "LXK Agency Contact eMail");
		activityFieldMap.put("Activity.committedDate", "LXK R Committed Time");
		activityFieldMap.put("Activity.serviceProviderETA", "LXK R Service Provider ETA");
		activityFieldMap.put("Activity.parentSRNum", "Parent SR Number");
		activityFieldMap.put("Activity.expectedCompletionDate", "LXK MPS Expected End Date");
		activityFieldMap.put("Activity.dispatchDate", "LXK MPS Activity Dispatch Date");
		
		return activityFieldMap;
	}
	
}
