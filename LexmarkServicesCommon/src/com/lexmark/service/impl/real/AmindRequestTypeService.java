package com.lexmark.service.impl.real;

import static com.lexmark.service.impl.real.deviceService.AmindContractedDeviceDataConversionUtil.requesLocationDotoGenericAddress;
import static com.lexmark.util.LangUtil.isEmpty;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.amind.session.Session;
import com.amind.session.SessionFactory;
import com.amind.session.StatelessSessionFactory;
import com.lexmark.contract.LocationReportingHierarchyContract;
import com.lexmark.contract.RequestContract;
import com.lexmark.contract.RequestListContract;
import com.lexmark.domain.GenericAddress;
import com.lexmark.domain.ServiceRequest;
import com.lexmark.result.RequestListResult;
import com.lexmark.result.RequestLocationsResult;
import com.lexmark.result.RequestResult;
import com.lexmark.service.api.RequestTypeService;
import com.lexmark.service.api.SiebelCrmServiceException;
import com.lexmark.service.impl.real.domain.RequestLocationDo;
import com.lexmark.service.impl.real.requestService.MadcRequestListService;
import com.lexmark.service.impl.real.requestService.RequestLocationService;
import com.lexmark.service.impl.real.requestService.RequestTypeActivitiesService;
import com.lexmark.service.impl.real.requestService.RequestTypeDetailService;
import com.lexmark.service.impl.real.requestService.RequestTypeFavoriteAssetService;
import com.lexmark.service.impl.real.requestService.RequestTypeListService;
import com.lexmark.service.impl.real.util.AmindServiceUtil;
import com.lexmark.service.impl.real.util.LogUtil;
import com.lexmark.util.LangUtil;
import com.siebel.data.SiebelPropertySet;


public class AmindRequestTypeService extends AmindSiebelCrmService implements RequestTypeService {

	private static final Map<String, String> REQUEST_TYPE_FIELD_MAP = populateRequestTypeFieldMap();
	private static final Map<String, String> REQUEST_TYPE_ACTIVITIES_FIELD_MAP = populateRequestTypeActivitiesFieldMap();
	private static final Map<String, String> REQUEST_LOCATION_FIELD_MAP = populateRequestLocationFieldMap();
	private static final Map<String, String> VENDOR_REQUEST_LOCATION_FIELD_MAP = populateVendorRequestLocationFieldMap();
	private static final Map<String, String> MADC_REQUEST_FIELD_MAP = populateMadcRequestFieldMap();

	/**
	 * TESTS: RequestTypeListDateFilterTest
	 * RequestTypeListFilterTest
	 * RequestTypeListPaginationTest
	 * RequestTypeListSortTest
	 * RequestTypeListTest
	 */
	private StatelessSessionFactory statelessSessionFactory;

	public SessionFactory getStatelessSessionFactory() {
		if (statelessSessionFactory == null) {
			statelessSessionFactory = new StatelessSessionFactory();
		}
		return statelessSessionFactory;
	}

	public void setStatelessSessionFactory(
			StatelessSessionFactory statelessSessionFactory) {
		this.statelessSessionFactory = statelessSessionFactory;
	}

	
	@Override
	public RequestListResult retrieveRequestList(RequestListContract contract) throws Exception {
		logger.debug("[IN] retrieveRequestTypeList");
		RequestListResult result = new RequestListResult();
		AmindCrmSessionHandle crmSessionHandle = (AmindCrmSessionHandle) contract.getSessionHandle();
		Session session = null;
		Session chldSession = null;
		Session totalCountSession = null;
		ExecutorService executor = null;
		try {
			session = crmSessionHandle.acquireMultiple();
			if(LangUtil.isNotBlank(contract.getChlNodeId()))
            {
               chldSession = getStatelessSessionFactory().attachSession();
         	}
			totalCountSession = getStatelessSessionFactory().attachSession();
			if (contract.isAssetFavoriteFlag()) {
				
				updateRequestListResultWithFavoriteAssetsAndTotal(result,contract,crmSessionHandle, session, totalCountSession);
				
			} else {
				
				final RequestTypeListService service = new RequestTypeListService(contract, REQUEST_TYPE_FIELD_MAP);
				service.checkRequiredFields();
				service.setSession(session);
				service.setChldSession(chldSession);
				service.setTotalCountSession(totalCountSession);
				service.buildSearchExpression();

				if(contract.isNewQueryIndicator()) {
					executor = Executors.newFixedThreadPool(2);
					
					Future<List<ServiceRequest>> requestsListFuture = executor.submit(new Callable<List<ServiceRequest>>() {
						@Override
						public List<ServiceRequest> call() throws Exception {
							return service.queryAndGetResultList();
						}
					});
					
					Future<Integer> totalCountFuture = executor.submit(new Callable<Integer>() {
						@Override
						public Integer call() throws Exception {
							return service.processTotalCount();
						}
					});
		
					result.setRequestList(requestsListFuture.get());
					
					int totalCount = totalCountFuture.get();
					result.setTotalCount(totalCount);
					crmSessionHandle.setRequestTypeCount(totalCount);
					
					executor.shutdown();
					
				} else {
					result.setRequestList(service.queryAndGetResultList());
					result.setTotalCount(crmSessionHandle.getRequestTypeCount());
				}
			}
			
		} catch (Exception e) {
		    LogUtil.logAmindServiceCallException(LogUtil.stackTraceExecutionPoint(), e, contract); 		    
			throw new SiebelCrmServiceException("retrieveRequestTypeList failed", e);
		} finally {
            AmindServiceUtil.releaseAmindCrmMultipleSessionHandle(crmSessionHandle, session);
            AmindServiceUtil.releaseSession(chldSession);
            AmindServiceUtil.releaseSession(totalCountSession);
            if (executor!=null && !executor.isShutdown()) {
				executor.shutdown();
			}
		}

		logger.debug("[OUT] retrieveRequestList");
		return result;
	}
	
	private void updateRequestListResultWithFavoriteAssetsAndTotal(
			RequestListResult result, RequestListContract contract,
			AmindCrmSessionHandle crmSessionHandle, Session session, Session totalCountSession) {
		
		ExecutorService executor = null;
		
		try {
			final RequestTypeFavoriteAssetService service = new RequestTypeFavoriteAssetService(contract,REQUEST_TYPE_FIELD_MAP);
			service.checkRequiredFields();
			service.setSession(session);
			service.setTotalCountSession(totalCountSession);
			service.buildSearchExpression();
			
			if (contract.isNewQueryIndicator()) {
				
				executor = Executors.newFixedThreadPool(2);
				
				Future<List<ServiceRequest>> requestsListFuture = executor.submit(new Callable<List<ServiceRequest>>() {
					@Override
					public List<ServiceRequest> call() throws Exception {
						return service.queryAndGetResultList();
					}
				});
				
				Future<Integer> totalCountFuture = executor.submit(new Callable<Integer>() {
					@Override
					public Integer call() throws Exception {
						return service.processTotalCount();
					}
				});
	
				result.setRequestList(requestsListFuture.get());
				
				int totalCount = totalCountFuture.get();
				result.setTotalCount(totalCount);
				crmSessionHandle.setRequestTypeCount(totalCount);
				
				executor.shutdown();
				
			} else {
				result.setRequestList(service.queryAndGetResultList());
				result.setTotalCount(crmSessionHandle.getRequestTypeCount());
			}

		} catch (Exception e) {
			logger.error(
					"updateRequestListResultWithFavoriteAssetsAndTotal failed",
					e);
			throw new SiebelCrmServiceException(
					"updateRequestListResultWithFavoriteAssetsAndTotal failed",
					e);
		}
		finally {
			if (executor!=null && !executor.isShutdown()) {
				executor.shutdown();
			}
		}

	}

    public RequestResult retrieveSupplyRequestDetail(RequestContract contract) throws Exception {
        logger.debug("[IN] retrieveSupplyRequestDetail");
        RequestResult result = new RequestResult();
        AmindCrmSessionHandle crmSessionHandle = null;
        Session session = null;
        try {
            RequestTypeDetailService service = new RequestTypeDetailService(contract, REQUEST_TYPE_FIELD_MAP);
            service.checkRequiredFields();
            crmSessionHandle = (AmindCrmSessionHandle) contract.getSessionHandle();
            session = crmSessionHandle.acquireMultiple();
            service.setSession(session);
            
            if(contract.isCreateChildSR()){
	            SiebelPropertySet childSRNumber = service.createChildSR();
	            if(childSRNumber != null){
	            	contract.setServiceRequestNumber(childSRNumber.getProperty("ChildSRNum"));
	            	result.setNewChildSR((childSRNumber).getProperty("NewChildSR"));
	            }
            }
            
            service.buildSearchExpression();
            result.setServiceRequest(service.queryAndGetRequestTypeDetail());
            return result;
        } catch (Exception e) {
            LogUtil.logAmindServiceCallException(LogUtil.stackTraceExecutionPoint(), e, contract);
            throw new SiebelCrmServiceException("retrieveRequestDetail failed", e);
        } finally {
            AmindServiceUtil.releaseAmindCrmMultipleSessionHandle(crmSessionHandle, session);
            logger.debug("[OUT] retrieveSupplyRequestDetail");
        }
    }
    
    
    public RequestResult retrieveAccountContactDetail(RequestContract contract) throws Exception
        {
    	logger.debug("[IN] retrieveAccountContactDetail");
    	
        	RequestResult result = new RequestResult();
        	AmindCrmSessionHandle crmSessionHandle = null;
            Session session = null;  
        	try{
        		RequestTypeDetailService service = new RequestTypeDetailService(contract);
                service.checkRequiredFields();
                service.buildSearchExpressionAccountContact();
                crmSessionHandle = (AmindCrmSessionHandle) contract.getSessionHandle();
                session = crmSessionHandle.acquireMultiple();
                service.setSession(session);
                result.setServiceRequest(service.queryAndGetAccountContactTypeDetail());
                return result;
        		
        	}
        	catch(Exception e)
        	{
        		 throw new SiebelCrmServiceException("retrieveSupplyRequestActivities failed", e);
        	}
        	finally
        	{
        		 AmindServiceUtil.releaseAmindCrmMultipleSessionHandle(crmSessionHandle, session);
                 logger.debug("[OUT] retrieveAccountContactDetail");
            	
        	}
        
        }  /**/
    
    
    public RequestResult retrieveSupplyRequestActivities(RequestContract contract) throws Exception {
        logger.debug("[IN] retrieveSupplyRequestActivities");
        RequestResult result = new RequestResult();
        AmindCrmSessionHandle crmSessionHandle = null;
        Session session = null;
        try {
        	RequestTypeActivitiesService service = new RequestTypeActivitiesService(contract, REQUEST_TYPE_ACTIVITIES_FIELD_MAP);
            service.checkRequiredFields();
            service.buildSearchExpression();
            crmSessionHandle = (AmindCrmSessionHandle) contract.getSessionHandle();
            session = crmSessionHandle.acquireMultiple();
            service.setSession(session);
            result.setServiceRequest(service.queryAndGetRequestTypeActivites());
            result.setTotalCount(service.processTotalCount());
            return result;
        } catch (Exception e) {
            LogUtil.logAmindServiceCallException(LogUtil.stackTraceExecutionPoint(), e, contract);
            throw new SiebelCrmServiceException("retrieveSupplyRequestActivities failed", e);
        } finally {
            AmindServiceUtil.releaseAmindCrmMultipleSessionHandle(crmSessionHandle, session);
            logger.debug("[OUT] retrieveSupplyRequestActivities");
        }
    }
    
    public RequestListResult retrieveMadcRequestList(RequestListContract contract) throws Exception {
    	logger.debug("[IN] retrieveMadcRequestList");
    	RequestListResult result = new RequestListResult();
		AmindCrmSessionHandle crmSessionHandle = (AmindCrmSessionHandle) contract.getSessionHandle();
		Session session = null;
		Session chldSession = null;
		Session totalCountSession = null;
		ExecutorService executor = null;
		try {
			session = crmSessionHandle.acquireMultiple();
			if(LangUtil.isNotBlank(contract.getChlNodeId()))
            {
               chldSession = getStatelessSessionFactory().attachSession();
         	}
			totalCountSession = getStatelessSessionFactory().attachSession();
			if (contract.isAssetFavoriteFlag()) {
				updateRequestListResultWithFavoriteAssetsAndTotal(result,contract,crmSessionHandle, session, totalCountSession);
			} else {
				final MadcRequestListService service = new MadcRequestListService(contract, MADC_REQUEST_FIELD_MAP);
				service.checkRequiredFields();
				service.setSession(session);
				service.setChldSession(chldSession);
				service.setTotalCountSession(totalCountSession);
				service.buildSearchExpression();
				if(contract.isNewQueryIndicator()) {
					executor = Executors.newFixedThreadPool(2);
					Future<List<ServiceRequest>> requestsListFuture = executor.submit(new Callable<List<ServiceRequest>>() {
						@Override
						public List<ServiceRequest> call() throws Exception {
							return service.queryAndGetMadcRequestList();
						}
					});
					Future<Integer> totalCountFuture = executor.submit(new Callable<Integer>() {
						@Override
						public Integer call() throws Exception {
							return service.processTotalCount();
						}
					});
					result.setRequestList(requestsListFuture.get());
					int totalCount = totalCountFuture.get();
					result.setTotalCount(totalCount);
					crmSessionHandle.setRequestTypeCount(totalCount);
					executor.shutdown();
				} else {
					result.setRequestList(service.queryAndGetMadcRequestList());
					result.setTotalCount(crmSessionHandle.getRequestTypeCount());
				}
			}
		} catch (Exception e) {
            LogUtil.logAmindServiceCallException(LogUtil.stackTraceExecutionPoint(), e, contract);
            throw new SiebelCrmServiceException("retrieveMadcRequestList failed", e);
		} finally {
            AmindServiceUtil.releaseAmindCrmMultipleSessionHandle(crmSessionHandle, session);
            AmindServiceUtil.releaseSession(chldSession);
            AmindServiceUtil.releaseSession(totalCountSession);
            if (executor!=null && !executor.isShutdown()) {
				executor.shutdown();
			}
		}
		logger.debug("[OUT] retrieveMadcRequestList");
		return result;
    }
    

	private static Map<String, String> populateRequestTypeFieldMap() {
		Map<String, String> requestTypeFieldMap = new HashMap<String, String>();
		
		// for query
		requestTypeFieldMap.put("mdmLevel1AccountId", "LXK MPS Global Ultimate DUNS");
		requestTypeFieldMap.put("mdmLevel2AccountId", "LXK MPS Domestic Ultimate DUNS");
		requestTypeFieldMap.put("mdmLevel3AccountId", "LXK MPS Legal Entity ID #");
		requestTypeFieldMap.put("mdmLevel4AccountId", "LXK MPS MDM Account #");
		requestTypeFieldMap.put("mdmLevel5AccountId", "Account Id");
		requestTypeFieldMap.put("agreementMdmLevel1AccountId", "LXK MPS Agreement Account Global DUNS");
		requestTypeFieldMap.put("agreementMdmLevel2AccountId", "LXK MPS Agreement Account Domestic DUNS");
		requestTypeFieldMap.put("agreementMdmLevel3AccountId", "LXK MPS Agreement Account Legal Id");
		requestTypeFieldMap.put("agreementMdmLevel4AccountId", "LXK MPS Agreement Account MDM#");
		requestTypeFieldMap.put("agreementMdmLevel5AccountId", "LXK MPS Agreement Account Id");
		requestTypeFieldMap.put("vendorMdmLevel1AccountId", "LXK MPS Vendor Global DUNS");
		requestTypeFieldMap.put("vendorMdmLevel2AccountId", "LXK MPS Vendor Domestic DUNS");
		requestTypeFieldMap.put("vendorMdmLevel3AccountId", "LXK MPS Vendor Legal Id");
		requestTypeFieldMap.put("vendorMdmLevel4AccountId", "LXK MPS Vendor MDM#");
		requestTypeFieldMap.put("vendorMdmLevel5AccountId", "LXK MPS Vendor Account Id");
		requestTypeFieldMap.put("chlNodeId", "LXK Asset CHL ID");
		requestTypeFieldMap.put("chlNodeName", "LXK Asset CHL Name");
		requestTypeFieldMap.put("assetId", "Asset Id");
		requestTypeFieldMap.put("contactId", "Contact Id");
		requestTypeFieldMap.put("serviceRequestNumber", "SR Number");
		requestTypeFieldMap.put("serviceRequestDate", "LXK MPS Created Date");
		requestTypeFieldMap.put("requestType", "LXK MPS SR Type");
		requestTypeFieldMap.put("area", "LXK MPS SR Area");
		requestTypeFieldMap.put("subArea", "LXK MPS SR Sub Area");
		//requestTypeFieldMap.put("asset.serialNumber", "Serial Number");
		requestTypeFieldMap.put("serviceRequestStatus", "LXK MPS Web Status");
		requestTypeFieldMap.put("productModel", "LXK R Product Primary Product Line Name");
		requestTypeFieldMap.put("resolutionCode", "LXK R Resolution Code");   // Added for LEX:AIR00075895
		requestTypeFieldMap.put("problemDescription", "LXK MPS Abstract");
		requestTypeFieldMap.put("helpDeskReferenceNumber", "LXK MPS Customer Ref Number");
		requestTypeFieldMap.put("contractType", "LXK SW Agreement Type");
		requestTypeFieldMap.put("serviceAddress.addressName","LXK MPS Service Address Name");
		/*no siebel mapping available*/
		requestTypeFieldMap.put("serviceAddress.storeFrontName","LXK MPS Store Front Name");
		
		requestTypeFieldMap.put("serviceAddress.addressLine1", "Service Address 1");
		requestTypeFieldMap.put("poNumber", "LXK MPS Customer PO Number");
		requestTypeFieldMap.put("serviceAddress.city", "Service City");
		requestTypeFieldMap.put("serviceAddress.state", "LXK MPS Service State");
		requestTypeFieldMap.put("serviceAddress.province", "Service Province");
		requestTypeFieldMap.put("serviceAddress.country", "Service Country");
		requestTypeFieldMap.put("serviceAddress.postalCode", "Service Postal Code");
		requestTypeFieldMap.put("primaryContact.firstName", "LXK MPS Primary Contact First Name");
		requestTypeFieldMap.put("primaryContact.lastName", "LXK MPS Primary Contact Last Name");
		requestTypeFieldMap.put("primaryContact.emailAddress", "LXK MPS Primary Contact Email Address");
		requestTypeFieldMap.put("primaryContact.workPhone", "LXK MPS Primary Contact Work Phone");
		requestTypeFieldMap.put("requestor.firstName", "LXK MPS Requester Contact First Name");
		requestTypeFieldMap.put("requestor.lastName", "LXK MPS Requester Contact Last Name");
		requestTypeFieldMap.put("requestor.emailAddress", "LXK MPS Requester Contact Email Address");
		requestTypeFieldMap.put("requestor.workPhone", "LXK MPS Requester Contact Work Phone");
		requestTypeFieldMap.put("eta", "Effective Date");
		requestTypeFieldMap.put("costCenter", "LXK MPS Cost center");
		requestTypeFieldMap.put("poNumber", "LXK MPS Customer PO Number");
		requestTypeFieldMap.put("asset.deviceTag", "LXK Device Tag Customer");
		requestTypeFieldMap.put("asset.hostName", "LXK MPS Host Name");
		requestTypeFieldMap.put("asset.assetCostCenter", "LXK Asset Cost Center");
		requestTypeFieldMap.put("accountName", "Account Name");
		// 
		requestTypeFieldMap.put("webStatus", "LXK MPS Web Status");
		
		requestTypeFieldMap.put("serviceAddress.county", "LXK MPS Service County");
		requestTypeFieldMap.put("serviceAddress.district", "LXK MPS District");
		requestTypeFieldMap.put("serviceAddress.officeNumber", "LXK MPS House Number");
		requestTypeFieldMap.put("coveredService", "Covered Service Type");
		
		//ops filter 
		requestTypeFieldMap.put("requestStatus", "LXK MPS SR Status");
		requestTypeFieldMap.put("subStatus", "LXK MPS Sub-Status");
		requestTypeFieldMap.put("severity", "LXK MPS Severity");
		requestTypeFieldMap.put("projectName", "LXK MPS Project");
		requestTypeFieldMap.put("projectPhase", "LXK MPS Project Phase");
		requestTypeFieldMap.put("agreementName", "LXK MPS Agreement Name");
		requestTypeFieldMap.put("agreementNumber", "Agreement Number");
		
		
		return Collections.unmodifiableMap(requestTypeFieldMap);
	}
	
	private static Map<String, String> populateRequestTypeActivitiesFieldMap() {
		Map<String, String> requestTypeFieldMap = new HashMap<String, String>();
		
		requestTypeFieldMap.put("activitywebUpdateActivities.assetDetails.serialNumber", "Serial Number"); 
		requestTypeFieldMap.put("activitywebUpdateActivities.assetDetails.deviceType", "RC Product Type Code");
		requestTypeFieldMap.put("activitywebUpdateActivities.assetDetails.activityNumber", "Activity UID");
		requestTypeFieldMap.put("activitywebUpdateActivities.assetDetails.statusDetail", "LXK R Service Additional Details");
		requestTypeFieldMap.put("activitywebUpdateActivities.assetDetails.building", "LXK R Physical Location 1");
		requestTypeFieldMap.put("activitywebUpdateActivities.assetDetails.floor", "LXK R Physical Location 2");
		requestTypeFieldMap.put("activitywebUpdateActivities.assetDetails.office", "LXK R Physical Location 3");
		
		return Collections.unmodifiableMap(requestTypeFieldMap);
	}
	
	private static Map<String, String> populateRequestLocationFieldMap() {
		Map<String, String> requestLocationFieldMap = new HashMap<String, String>();
		
		// for query
		requestLocationFieldMap.put("mdmLevel1AccountId", "LXK MPS Service Account Global DUNS Number");
		requestLocationFieldMap.put("mdmLevel2AccountId", "LXK MPS Service Account Domestic DUNS Number");
		requestLocationFieldMap.put("mdmLevel3AccountId", "LXK MPS Service Account Legal Entity Id");
		requestLocationFieldMap.put("mdmLevel4AccountId", "LXK MPS Service Account MDM #");
		requestLocationFieldMap.put("mdmLevel5AccountId", "LXK MPS Service Account Id");
		requestLocationFieldMap.put("serviceRequest.startDate", "Created");
		requestLocationFieldMap.put("serviceRequest.endDate", "Created");
		requestLocationFieldMap.put("agreementMdmLevel1AccountId", "LXK MPS SVC Agree Account Global DUNS#");
		requestLocationFieldMap.put("agreementMdmLevel2AccountId", "LXK MPS SVC Agree Account Domestic DUNS#");
		requestLocationFieldMap.put("agreementMdmLevel3AccountId", "LXK MPS SVC Account Legal Entity#");
		requestLocationFieldMap.put("agreementMdmLevel4AccountId", "LXK MPS SVC Agree Account MDM#");
		requestLocationFieldMap.put("agreementMdmLevel5AccountId", "LXK MPS SVC Agreement Account Id");
		return Collections.unmodifiableMap(requestLocationFieldMap);
	}
	
	private static Map<String, String> populateVendorRequestLocationFieldMap() {
		Map<String, String> m = new HashMap<String, String>();
		// for query
		m.put("vendorMdmLevel1AccountId", "LXK MPS Vendor Account Domestic DUNS Number");
		m.put("vendorMdmLevel2AccountId", "LXK MPS Vendor Account Global DUNS Number");
		m.put("vendorMdmLevel3AccountId", "LXK MPS Vendor Account Legal Entity Id");
		m.put("vendorMdmLevel4AccountId", "LXK MPS Vendor Account MDM #");
		m.put("vendorMdmLevel5AccountId", "LXK MPS Vendor Account Id");
		m.put("vendorMdmLevel", "LXK MPS Vendor Account Level");
		m.put("serviceRequest.startDate", "Created");
		m.put("serviceRequest.endDate", "Created");
		return Collections.unmodifiableMap(m);
	}
	
	private static Map<String, String> populateMadcRequestFieldMap() {
		Map<String, String> m = new HashMap<String, String>();
		m.put("assetId", "LXK MADC Asset Id");
		m.put("webStatus", "LXK SR Web Status");
		return Collections.unmodifiableMap(m);
	}

	
	@Override
	public RequestLocationsResult retrieveRequestLocations(
			LocationReportingHierarchyContract contract) throws Exception {
		logger.debug("[IN] retrieveRequestLocations");
		
		RequestLocationsResult result = new RequestLocationsResult();
		Session session = getStatelessSessionFactory().attachSession();
		
		try{
		    Map<String, String> fieldMap = contract.isVendorFlag() ? VENDOR_REQUEST_LOCATION_FIELD_MAP: REQUEST_LOCATION_FIELD_MAP; 
			RequestLocationService service = new RequestLocationService(contract, fieldMap, session);
			service.checkRequiredFields();
			service.buildSearchExpression();
			List<RequestLocationDo> locationList = service.queryAndGetRequestLocations();
			
			if (!isEmpty(locationList)) {
				result.setAddressList(requesLocationDotoGenericAddress(locationList));
			}else{
				result.setAddressList(new ArrayList<GenericAddress>());			
			}
		
		}catch(Exception e){
            LogUtil.logAmindServiceCallException(LogUtil.stackTraceExecutionPoint(), e, contract);
			throw new SiebelCrmServiceException("retrieveRequestLocation failed", e);
		} finally {
			AmindServiceUtil.releaseSession(session);
		}
		logger.debug("[OUT] retrieveRequestLocations");

		return result;
	}
}
