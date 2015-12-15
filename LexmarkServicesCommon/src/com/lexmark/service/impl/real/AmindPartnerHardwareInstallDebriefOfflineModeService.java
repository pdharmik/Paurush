package com.lexmark.service.impl.real;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.amind.session.Session;
import com.lexmark.contract.ActivityDetailContract;
import com.lexmark.contract.ActivityListContract;
import com.lexmark.contract.OfflineModeAttachmentContract;
import com.lexmark.result.ActivityDetailResult;
import com.lexmark.result.ActivityListResult;
import com.lexmark.service.api.PartnerHardwareInstallDebriefOfflineModeService;
import com.lexmark.service.api.SiebelCrmServiceException;
import com.lexmark.service.impl.real.partnerHardwareInstallDebriefOfflineModeService.AmindPartnerHardwareInstallDebriefOfflineModeActivitiesDetailsService;
import com.lexmark.service.impl.real.partnerHardwareInstallDebriefOfflineModeService.AmindPartnerHardwareInstallDebriefOfflineModeActivitiesListService;
import com.lexmark.service.impl.real.partnerHardwareInstallDebriefOfflineModeService.AmindPartnerHardwareInstallDebriefOfflineModeAttachmentsService;
import com.lexmark.service.impl.real.util.AmindServiceUtil;
import com.lexmark.service.impl.real.util.LogUtil;

public class AmindPartnerHardwareInstallDebriefOfflineModeService extends AmindSiebelCrmService implements PartnerHardwareInstallDebriefOfflineModeService {

	private static final Map<String, String> OFFLINE_MODE_ACTIVITIES_FIELD_MAP = populateOfflineModeActivitiesFieldMap();
	
	@Override
	public ActivityListResult retrieveOfflineModeActivitiesList(
			ActivityListContract contract) throws Exception {
		logger.debug("[IN] retrieveOfflineModeActivitiesList");

		final ActivityListResult result = new ActivityListResult();
//		final AmindCrmSessionHandle crmSessionHandle = (AmindCrmSessionHandle) contract
//				.getSessionHandle();
		Session session = null;
//		Session totalCountSession = null;
//		ExecutorService executor = null;

		try {
//			session = crmSessionHandle.acquireMultiple();
//			if (contract.isCountFlag()) {
//				totalCountSession = getStatelessSessionFactory().attachSession();
//			}
			
			session = getStatelessSessionFactory().attachSession();

			final AmindPartnerHardwareInstallDebriefOfflineModeActivitiesListService service = new AmindPartnerHardwareInstallDebriefOfflineModeActivitiesListService(
					contract, OFFLINE_MODE_ACTIVITIES_FIELD_MAP);
			service.checkRequiredFields();
			service.setSession(session);
//			service.setTotalCountSession(totalCountSession);
			service.buildSearchExpression();

			if (contract.isCountFlag()) {
				result.setCountFlag(service.isRecordExist());
			}
			else {
				result.setActivityList(service.queryAndGetActivitiesList());
				result.setTotalcount(result.getActivityList().size());
			}
			
//			else {
//				if (contract.isNewQueryIndicator()) {
//					executor = Executors.newFixedThreadPool(2);
//					List<Future<?>> list = new ArrayList<Future<?>>();
//
//					list.add(executor.submit(new Runnable() {
//
//						@Override
//						public void run() {
//							result.setActivityList(service.queryAndGetActivitiesList());
//						}
//					}));
//
//					list.add(executor.submit(new Runnable() {
//
//						@Override
//						public void run() {
//							int totalCount = service.processTotalCount();
//							result.setTotalcount(totalCount);
//							crmSessionHandle.setOfflineModeActivitiesCount(totalCount);
//						}
//					}));
//
//					for (Future<?> future : list) {
//						future.get();
//					}
//
//					executor.shutdown();
//				} else {
//					result.setActivityList(service.queryAndGetActivitiesList());
//					result.setTotalcount(crmSessionHandle.getOfflineModeActivitiesCount());
//				}
//
//			}

		} catch (Exception e) {
			LogUtil.logAmindServiceCallException(
					LogUtil.stackTraceExecutionPoint(), e, contract);
			throw new SiebelCrmServiceException(
					"retrieveOfflineModeActivitiesList failed", e);
		} finally {
//			AmindServiceUtil.releaseAmindCrmMultipleSessionHandle(
//					crmSessionHandle, session);
//			AmindServiceUtil.detachSession(totalCountSession);
			
			AmindServiceUtil.releaseSession(session);
			
//			if (executor!=null && !executor.isShutdown()) {
//				executor.shutdown();
//			}
		}

		logger.debug("[OUT] retrieveOfflineModeActivitiesList");
		return result;
	}

	public ActivityListResult retrieveServiceRequestOfflineModeActivitiesList(ActivityListContract contract) throws Exception {
		logger.debug("[IN] retrieveServiceRequestOfflineModeActivitiesList");

		final ActivityListResult result = new ActivityListResult();
		final AmindCrmSessionHandle crmSessionHandle = (AmindCrmSessionHandle) contract.getSessionHandle();
		Session session = null;
		Session totalCountSession = null;
		ExecutorService executor = null;

		try {
//			session = crmSessionHandle.acquireMultiple();
//			if (contract.isCountFlag()) {
			totalCountSession = getStatelessSessionFactory().attachSession();
//			}
//			
			session = getStatelessSessionFactory().attachSession();

			final AmindPartnerHardwareInstallDebriefOfflineModeActivitiesListService service = new AmindPartnerHardwareInstallDebriefOfflineModeActivitiesListService(
					contract, OFFLINE_MODE_ACTIVITIES_FIELD_MAP);
			service.checkRequiredFields();
			service.setSession(session);
			service.setTotalCountSession(totalCountSession);
			service.buildSearchExpressionServiceRequest();

			if (contract.isCountFlag()) {
				result.setCountFlag(service.isRecordExistSR());
			}
//			else {
//				result.setActivityList(service.queryAndGetActivitiesList());
//				result.setTotalcount(result.getActivityList().size());
//			}
//			
			else {
				if (contract.isNewQueryIndicator()) {
					executor = Executors.newFixedThreadPool(2);
					List<Future<?>> list = new ArrayList<Future<?>>();

					list.add(executor.submit(new Runnable() {

						@Override
						public void run() {
							result.setActivityList(service.queryAndGetActivitiesList());
						}
					}));

					list.add(executor.submit(new Runnable() {

						@Override
						public void run() {
							int totalCount = service.processTotalCount();
							result.setTotalcount(totalCount);
							crmSessionHandle.setOfflineModeActivitiesCount(totalCount);
						}
					}));

					for (Future<?> future : list) {
						future.get();
					}

					executor.shutdown();
				} else {
					result.setActivityList(service.queryAndGetActivitiesList());
					result.setTotalcount(crmSessionHandle.getOfflineModeActivitiesCount());
				}

			}

		} catch (Exception e) {
			LogUtil.logAmindServiceCallException(
					LogUtil.stackTraceExecutionPoint(), e, contract);
			throw new SiebelCrmServiceException(
					"retrieveOfflineModeActivitiesList failed", e);
		} finally {
			AmindServiceUtil.releaseAmindCrmMultipleSessionHandle(crmSessionHandle, session);
			AmindServiceUtil.releaseSession(totalCountSession);
			
			if (executor!=null && !executor.isShutdown()) {
			    executor.shutdown();
			   }
			
//			if (executor!=null && !executor.isShutdown()) {
//				executor.shutdown();
//			}
		}

		logger.debug("[OUT] retrieveServiceRequestOfflineModeActivitiesList");
		return result;
	}
	@Override
	public ActivityDetailResult retrieveOfflineModeActivityDetails(ActivityDetailContract contract) throws Exception {
		logger.debug("[IN] retrieveOfflineModeActivityDetails");

		ActivityDetailResult result = new ActivityDetailResult();
		Session session = null;

		try {
			session = getStatelessSessionFactory().attachSession();
			
			AmindPartnerHardwareInstallDebriefOfflineModeActivitiesDetailsService service = new AmindPartnerHardwareInstallDebriefOfflineModeActivitiesDetailsService(contract);
			service.checkRequiredFields();
			service.buildSearchExpression();
			service.setSession(session);
			result.setActivity(service.queryAndGetActivityDetails());
		} catch (Exception e) {
			LogUtil.logAmindServiceCallException(LogUtil.stackTraceExecutionPoint(), e, contract);
			throw new SiebelCrmServiceException("retrieveOfflineModeActivityDetails failed.", e);
		} finally {
			AmindServiceUtil.releaseSession(session);
		}

		logger.debug("[OUT] retrieveOfflineModeActivityDetails");
		return result;
	}
	
	
	@Override
	public void generateInstallationDoc(OfflineModeAttachmentContract contract) throws Exception {
		logger.debug("[IN] retrieveOfflineModeAttachmentLink");

		Session session = null; 
		
		try {
			session = getStatelessSessionFactory().attachSession();
			
			AmindPartnerHardwareInstallDebriefOfflineModeAttachmentsService service = new AmindPartnerHardwareInstallDebriefOfflineModeAttachmentsService(contract);
			service.checkRequiredFields();
			service.setSession(session);
			service.generateInstallationDoc();
		} catch (Exception e) {
			LogUtil.logAmindServiceCallException(LogUtil.stackTraceExecutionPoint(), e, contract);
//			throw new SiebelCrmServiceException("retrieveOfflineModeAttachmentLink failed.", e);
		}
		finally {
			AmindServiceUtil.releaseSession(session);
		}
		
		logger.debug("[OUT] retrieveOfflineModeAttachmentLink");
	}
	
	
	private static Map<String, String> populateOfflineModeActivitiesFieldMap() {
		Map<String, String> offlineModeActivitiesFieldMap = new HashMap<String, String>();
		offlineModeActivitiesFieldMap.put("Activity.activityDate", "LXK SW Activity Created");
		offlineModeActivitiesFieldMap.put("Activity.serviceRequest.serviceRequestNumber", "SR Number");
		offlineModeActivitiesFieldMap.put("Activity.serviceProviderReferenceNumber",	"LXK SD SP Reference");
		offlineModeActivitiesFieldMap.put("Activity.serviceRequest.asset.productLine", "LXK R SR Product Line");
		offlineModeActivitiesFieldMap.put("Activity.serviceRequest.asset.productTLI", "LXK SD SR Product TLI");
		offlineModeActivitiesFieldMap.put("Activity.serviceRequest.asset.serialNumber", "Serial Number");
		offlineModeActivitiesFieldMap.put("Activity.serviceRequest.asset.activitynumber", "Activity UID");
		offlineModeActivitiesFieldMap.put("Activity.serviceRequest.asset.installDate", "LXK MPS Install Date PreDebriefRFV");
		offlineModeActivitiesFieldMap.put("Activity.activityStatus", "Status");
		offlineModeActivitiesFieldMap.put("Activity.activitySubStatus", "LXK R Substatus");
		offlineModeActivitiesFieldMap.put("Activity.partnerAccount.accountName",	"LXK R Service Provider Name");
		offlineModeActivitiesFieldMap.put("Activity.technician.firstName", "LXL SW Activity Technician First Name"); 
		offlineModeActivitiesFieldMap.put("Activity.technician.lastName", "LXK SW Activity Technician Last Name"); 
		offlineModeActivitiesFieldMap.put("Activity.customerAccount.accountName", "Account Name");
		offlineModeActivitiesFieldMap.put("Activity.customerRequestedResponseDate", "LXK C New Intervention Date");
		offlineModeActivitiesFieldMap.put("Activity.serviceRequest.serviceAddress.addressLine1", "Service Street Address");
		offlineModeActivitiesFieldMap.put("Activity.serviceRequest.serviceAddress.city",	"Service City");
		offlineModeActivitiesFieldMap.put("Activity.serviceRequest.serviceAddress.state", "Service State");
		offlineModeActivitiesFieldMap.put("Activity.serviceRequest.serviceAddress.province",	"Service Province");
		offlineModeActivitiesFieldMap.put("Activity.serviceRequest.serviceAddress.postalCode", "Service Postal Code");
		offlineModeActivitiesFieldMap.put("Activity.serviceRequest.serviceAddress.officeNumber", "Service Office #");
		offlineModeActivitiesFieldMap.put("Activity.serviceRequest.serviceAddress.county", "Service County");
		offlineModeActivitiesFieldMap.put("Activity.serviceRequest.serviceAddress.district", "Service District");
		offlineModeActivitiesFieldMap.put("Activity.serviceRequest.serviceAddress.country", "Service Country");
		offlineModeActivitiesFieldMap.put("Activity.serviceRequest.primaryContact.firstName", "LXK SW SR Primary Contact First Name");
		offlineModeActivitiesFieldMap.put("Activity.serviceRequest.primaryContact.lastName",	"LXK SW SR Primary Contact Last Name");
		offlineModeActivitiesFieldMap.put("Activity.serviceRequest.serviceRequestDate",	"LXK SW SR Created Date");
		offlineModeActivitiesFieldMap.put("Activity.serviceRequest.requestType",	"LXK SW Service Request Type");
		offlineModeActivitiesFieldMap.put("Activity.serviceRequest.serviceRequestStatus", "SR Status");
		offlineModeActivitiesFieldMap.put("Activity.serviceRequest.projectName", "LXK MPS Project");
		offlineModeActivitiesFieldMap.put("Activity.serviceRequest.actualStartDate", "LXK R Project Item Actual Start");
		offlineModeActivitiesFieldMap.put("Activity.serviceRequest.storeFrontName", "LXK MPS Store Front Name");
		offlineModeActivitiesFieldMap.put("Activity.serviceRequest.statusDetail", "LXK MPS SR Sub Area"); 
		offlineModeActivitiesFieldMap.put("mdmLevel5AccountId", "LXK R Service Provider Id");
		offlineModeActivitiesFieldMap.put("mdmLevel1AccountId", "LXK SW SP Global Ultimate DUNS");
		offlineModeActivitiesFieldMap.put("mdmLevel2AccountId", "LXK SW SP Domestic Ultimate DUNS");
		offlineModeActivitiesFieldMap.put("mdmLevel3AccountId", "LXK SW SP Legal Entity Number");
		offlineModeActivitiesFieldMap.put("mdmLevel4AccountId", "LXK SW SP MDM Account Number");
		offlineModeActivitiesFieldMap.put("Activity.activityType", "Type");
		offlineModeActivitiesFieldMap.put("Activity.partnerAccountName", "LXK R Service Provider Name"); 
		offlineModeActivitiesFieldMap.put("Activity.responseMetric", "LXK SW Response Metric Calc"); 
		offlineModeActivitiesFieldMap.put("Activity.claimStatusDate", "LXK R Sub Status Update");
		offlineModeActivitiesFieldMap.put("Activity.activityId", "Id");
		offlineModeActivitiesFieldMap.put("Activity.serviceRequest.asset.productModelNumber", "LXK HW Install Model");
		offlineModeActivitiesFieldMap.put("Activity.serviceRequest.statusType", "LXK SW Web SR Status");
		offlineModeActivitiesFieldMap.put("Activity.debriefStatus", "LXK R Debrief Status");
		return Collections.unmodifiableMap(offlineModeActivitiesFieldMap);
	}

}
