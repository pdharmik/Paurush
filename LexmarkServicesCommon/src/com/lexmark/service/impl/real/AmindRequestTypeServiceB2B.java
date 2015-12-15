package com.lexmark.service.impl.real;

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
import com.lexmark.contract.RequestContract;
import com.lexmark.contract.RequestListContract;
import com.lexmark.domain.ServiceRequest;
import com.lexmark.result.RequestListResult;
import com.lexmark.result.RequestResult;
import com.lexmark.service.api.RequestTypeServiceB2B;
import com.lexmark.service.api.SiebelCrmServiceException;
import com.lexmark.service.impl.real.requestService.RequestTypeDetailService;
import com.lexmark.service.impl.real.requestService.RequestTypeFavoriteAssetService;
import com.lexmark.service.impl.real.requestService.RequestTypeListServiceB2B;
import com.lexmark.service.impl.real.util.AmindServiceUtil;
import com.lexmark.service.impl.real.util.LogUtil;
import com.lexmark.util.LangUtil;


public class AmindRequestTypeServiceB2B extends AmindSiebelCrmService implements RequestTypeServiceB2B {

	private static final Map<String, String> REQUEST_TYPE_FIELD_MAP = populateRequestTypeFieldMap();
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
	public RequestListResult retrieveRequestListB2B(RequestListContract contract) throws Exception {
		logger.debug("[IN] retrieveRequestListB2B");
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
				
				final RequestTypeListServiceB2B service = new RequestTypeListServiceB2B(contract, REQUEST_TYPE_FIELD_MAP);
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
			throw new SiebelCrmServiceException("retrieveRequestListB2B failed", e);
		} finally {
            AmindServiceUtil.releaseAmindCrmMultipleSessionHandle(crmSessionHandle, session);
            AmindServiceUtil.releaseSession(chldSession);
            AmindServiceUtil.releaseSession(totalCountSession);
            if (executor!=null && !executor.isShutdown()) {
				executor.shutdown();
			}
		}

		logger.debug("[OUT] retrieveRequestListB2B");
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

    public RequestResult retrieveSupplyRequestDetailB2B(RequestContract contract) throws Exception {
        logger.debug("[IN] retrieveSupplyRequestDetailB2B");
        RequestResult result = new RequestResult();
        AmindCrmSessionHandle crmSessionHandle = null;
        Session session = null;
        try {
            RequestTypeDetailService service = new RequestTypeDetailService(contract, REQUEST_TYPE_FIELD_MAP);
            service.checkRequiredFields();
            service.buildSearchExpression();
            crmSessionHandle = (AmindCrmSessionHandle) contract.getSessionHandle();
            session = crmSessionHandle.acquireMultiple();
            service.setSession(session);
            result.setServiceRequest(service.queryAndGetRequestTypeDetail());
            return result;
        } catch (Exception e) {
            LogUtil.logAmindServiceCallException(LogUtil.stackTraceExecutionPoint(), e, contract);
            throw new SiebelCrmServiceException("retrieveSupplyRequestDetailB2B failed", e);
        } finally {
            AmindServiceUtil.releaseAmindCrmMultipleSessionHandle(crmSessionHandle, session);
            logger.debug("[OUT] retrieveSupplyRequestDetailB2B");
        }
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
		requestTypeFieldMap.put("serviceAddress.county", "LXK MPS Service County");
		requestTypeFieldMap.put("serviceAddress.district", "LXK MPS District");
		requestTypeFieldMap.put("serviceAddress.officeNumber", "LXK MPS House Number");
		requestTypeFieldMap.put("coveredService", "Covered Service Type");
		
		return Collections.unmodifiableMap(requestTypeFieldMap);
	}
}
