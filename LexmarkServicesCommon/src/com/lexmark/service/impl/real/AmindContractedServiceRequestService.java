package com.lexmark.service.impl.real;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.amind.session.Session;
import com.amind.session.SessionFactory;
import com.lexmark.contract.AddressListContract;
import com.lexmark.contract.CreateServiceRequestContract;
import com.lexmark.contract.FavoriteAddressContract;
import com.lexmark.contract.ManualAssetContract;
import com.lexmark.contract.ModifyContactContract;
import com.lexmark.contract.ServiceRequestAssociatedListContract;
import com.lexmark.contract.ServiceRequestHistoryListContract;
import com.lexmark.contract.ServiceRequestListContract;
import com.lexmark.contract.SiebelAccountListContract;
import com.lexmark.domain.GenericAddress;
import com.lexmark.result.AddressListResult;
import com.lexmark.result.CreateServiceRequestResult;
import com.lexmark.result.FavoriteResult;
import com.lexmark.result.ManualAssetResult;
import com.lexmark.result.ModifyContactResult;
import com.lexmark.result.ServiceRequestListResult;
import com.lexmark.result.SiebelAccountListResult;
import com.lexmark.service.api.ServiceRequestService;
import com.lexmark.service.api.SiebelCrmServiceException;
import com.lexmark.service.impl.real.addressService.AddressListService;
import com.lexmark.service.impl.real.addressService.UpdateAddressService;
import com.lexmark.service.impl.real.delegate.SiebelAccountListService;
import com.lexmark.service.impl.real.delegate.ValidateManualAssetService;
import com.lexmark.service.impl.real.domain.LBSAddress;
import com.lexmark.service.impl.real.requestService.AssociatedServiceRequestListService;
import com.lexmark.service.impl.real.requestService.ServiceRequestHistoryListService;
import com.lexmark.service.impl.real.requestService.ServiceRequestListService;
import com.lexmark.service.impl.real.util.AmindServiceUtil;
import com.lexmark.service.impl.real.util.LogUtil;

/**
 * do-classes:
 * ServiceRequest: "do-contractedservicerequest-mapping.xml"
 * ServiceRequestDetail: "do-servicerequestdetail-mapping.xml"
 * AddressDo: "do-addressdo-mapping.xml"
 * FavoriteAddressDo: "do-favoriteaddressdo-mapping.xml"
 * UserFavoriteContactDo: "do-userfavoriteaddressdo-mapping.xml"
 * AccountDo: "do-accountdo-mapping.xml"
 * InternalProductDo: "do-internalproductdo-mapping.xml"
 */
public class AmindContractedServiceRequestService extends AmindSiebelCrmService implements
		ServiceRequestService {

	private static final Map<String, String> REQUEST_FIELD_MAP = populateRequestFieldMap();
	private static final Map<String, String> REQUEST_TYPE_FIELD_MAP = populateRequestTypeFieldMap();
	private static final Map<String, String> ADDRESS_FIELD_MAP = populateAddressFieldMap();
	private static final Map<String, String> FAVORITE_FIELD_MAP = populateFavoriteFieldMap();
	
	private SessionFactory sessionFactory;
	public SessionFactory getSessionFactory() {
		if (sessionFactory == null) {
			throw new SiebelCrmServiceException("SessionFactory not initialized");
		}
		return sessionFactory;
	}
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public AmindContractedServiceRequestService() {
	}

	@Override
	public CreateServiceRequestResult createServiceRequest(CreateServiceRequestContract contract)
			throws Exception {

		return null;
	}

	@Override
	public ModifyContactResult modifyContact(ModifyContactContract modifyContactContract) throws Exception {
		return new ModifyContactResult();
	}

	/**
	 * TESTS suite: AddressServiceTestSuite.class;
	 * 
	 * TESTS: AddressFavoriteListTest.class, AddressListFilterTest.class,
	 * AddressListPaginationTest.class, AddressListSortTest.class;
	 */
	@Override
	public AddressListResult retrieveAddressList(AddressListContract contract) throws Exception {
		logger.debug("[IN] retrieveAddressList");

		AddressListResult result = new AddressListResult();

		AmindCrmSessionHandle crmSessionHandle = (AmindCrmSessionHandle) contract.getSessionHandle();
		Session session = null;
		Session favoritesSession = null;
        try {
            AddressListService service = new AddressListService(contract, ADDRESS_FIELD_MAP, FAVORITE_FIELD_MAP);
            service.checkRequiredFields();
            service.buildSearchExpression();

            session = crmSessionHandle.acquireMultiple();
            service.setSession(session);
            
        	if(!contract.isFavoriteFlag()) {
				favoritesSession = getStatelessSessionFactory().attachSession();
				service.setFavoritesSession(favoritesSession);
			}
			
            List<GenericAddress> addressList = service.queryAndGetResultList();

            int addressCount;
            if (contract.isNewQueryIndicator()) {
                addressCount = service.processTotalCount();
                crmSessionHandle.setAddressCount(addressCount);
            } else {
                addressCount = crmSessionHandle.getAddressCount();
            }

            result.setAddressList(addressList);
            result.setTotalCount(addressCount);

        } catch (Exception e) {
            LogUtil.logAmindServiceCallException(LogUtil.stackTraceExecutionPoint(), e, contract);
            throw new SiebelCrmServiceException("retrieveAddressList failed", e);
        } finally {
            AmindServiceUtil.releaseAmindCrmMultipleSessionHandle(crmSessionHandle,session);
            AmindServiceUtil.releaseSession(favoritesSession);
            logger.debug("[OUT] retrieveAddressList");
        }

		return result;
	}
	
	/**
	 * TESTS: AssociatedServiceRequestListTest.class
	 */
	@Override
	public ServiceRequestListResult retrieveAssociatedServiceRequestList(
			ServiceRequestAssociatedListContract contract) throws Exception {

		logger.debug("[IN] retrieveAssociatedServiceRequestList");
		ServiceRequestListResult serviceListResult = new ServiceRequestListResult();
		Session session = null;
		try {
		    AssociatedServiceRequestListService listService = new AssociatedServiceRequestListService(contract, REQUEST_TYPE_FIELD_MAP);
		    listService.checkRequiredFields();
		    
			session = getSessionFactory().attachSession();
			listService.setSession(session);
			
			listService.buildSearchExpression();
			List<com.lexmark.domain.ServiceRequest> serviceRequestList = listService.queryAndGetResultList();
			serviceListResult.setServiceRequests(serviceRequestList);
			serviceListResult.setTotalCount(serviceRequestList.size());
		
		} catch (Exception e) {
		    LogUtil.logAmindServiceCallException(LogUtil.stackTraceExecutionPoint(), e, contract);
			throw new SiebelCrmServiceException("retrieveAssociatedServiceRequestList failed", e);
		} finally {
			AmindServiceUtil.detachSession(session);
			logger.debug("[OUT] retrieveAssociatedServiceRequestList");
		}
		
		return serviceListResult;
	}
	
	/**
	 * TESTS: ServiceRequestHistoryListTest.class
	 * 
	 * @param contract
	 * @return
	 * @throws Exception
	 */
	@Override
	public ServiceRequestListResult retrieveServiceRequestHistoryList(
			ServiceRequestHistoryListContract contract) throws Exception {

		logger.debug("[IN] retrieveServiceRequestHistoryList");

		ServiceRequestListResult serviceListResult = new ServiceRequestListResult();
		ServiceRequestHistoryListService service = new ServiceRequestHistoryListService(contract, REQUEST_FIELD_MAP);
		Session session = null;
        try {
            service.checkRequiredFields();
            service.buildSearchExpression();
            session = getSessionFactory().attachSession();

            service.setSession(session);
            serviceListResult.setServiceRequests(service.queryAndGetResultList());
            serviceListResult.setTotalCount(service.processTotalCount());
        } catch (Exception e) {
            LogUtil.logAmindServiceCallException(LogUtil.stackTraceExecutionPoint(), e, contract);
            throw new SiebelCrmServiceException("retrieveServiceRequestHistoryList failed", e);
        } finally {
            AmindServiceUtil.detachSession(session);
            logger.debug("[OUT] retrieveServiceRequestHistoryList");
        }

		return serviceListResult;
	}

	/**
	 * TESTS: ServiceRequestListMdmTest:
	 */
	@Override
	public ServiceRequestListResult retrieveServiceRequestList(ServiceRequestListContract contract)
			throws Exception {

		logger.debug("[IN] retrieveServiceRequestList");

		AmindCrmSessionHandle crmSessionHandle = (AmindCrmSessionHandle) contract.getSessionHandle();
		ServiceRequestListResult result = new ServiceRequestListResult();
		Session session = null;
		try {
			session = crmSessionHandle.acquireMultiple();
			ServiceRequestListService service = new ServiceRequestListService(contract, REQUEST_FIELD_MAP, session);
			result.setServiceRequests(service.queryAndGetResultList());
			
			if (contract.isNewQueryIndicator()) {
				int count = service.processTotalCount();
				result.setTotalCount(count);
				crmSessionHandle.setServiceRequestCount(count);
			} else {
				result.setTotalCount(crmSessionHandle.getServiceRequestCount());
			}
			
		} catch (Exception e) {
		    LogUtil.logAmindServiceCallException(LogUtil.stackTraceExecutionPoint(), e, contract);
			throw new SiebelCrmServiceException("retrieveServiceRequestList failed", e);
		} finally {
			AmindServiceUtil.releaseAmindCrmMultipleSessionHandle(crmSessionHandle, session);
		}

		logger.debug("[OUT] retrieveServiceRequestList");
		return result;
	}

	/**
	 * TESTS: SiebelAccountListTest.class
	 */
	@Override
	public SiebelAccountListResult retrieveSiebelAccountList(SiebelAccountListContract contract) {
		logger.debug("[IN] retrieveSiebelAccountList");

		SiebelAccountListResult result = new SiebelAccountListResult();
        Session session = null;
        try {
            SiebelAccountListService service = new SiebelAccountListService(contract);
            service.checkRequiredFields();
            service.buildSearchExpression();
            session = getSessionFactory().attachSession();
            service.setSession(session);
            result.setAccountList(service.queryAndGetResultList());
        } catch (Exception e) {
            LogUtil.logAmindServiceCallException(LogUtil.stackTraceExecutionPoint(), e, contract);
            throw new SiebelCrmServiceException("retrieveSiebelAccountList failed", e);
        } finally {
            AmindServiceUtil.detachSession(session);
            logger.debug("[OUT] retrieveSiebelAccountList");
        }
		return result;
	}
	
	/**
	 * Creates/deletes record in 'LXK SW Contact Favorite Addresses' IC.
	 * Creation/deletion logic is based on favoriteFlag in
	 * {@link FavoriteAddressContract}, if true, then record is created, if
	 * false - deleted.
	 * 
	 * TESTS: AddressUpdateFavoriteTest.class
	 */
	
	
	
	
	@Override
	public FavoriteResult updateUserFavoriteAddress(FavoriteAddressContract contract) throws Exception {
		logger.debug("[IN] updateUserFavoriteAddress");

		FavoriteResult result = new FavoriteResult();
		AmindCrmSessionHandle crmSessionHandle = (AmindCrmSessionHandle) contract.getSessionHandle();
		Session session = null; 
        try {
            UpdateAddressService service = new UpdateAddressService(contract);
            service.checkRequiredFields();
            service.buildSearchExpression();;
            session = crmSessionHandle.acquireMultiple();
            service.setSession(session);
            
            result.setResult(service.queryAndUpdate());
        } catch (Exception e) {
            LogUtil.logAmindServiceCallException(LogUtil.stackTraceExecutionPoint(), e, contract);
            throw new SiebelCrmServiceException("updateUserFavoriteAddress failed", e);
        } finally {
//            AmindServiceUtil.detachSession(session);
        	AmindServiceUtil.releaseAmindCrmMultipleSessionHandle(crmSessionHandle, session);
        }

		return result;
	}

	/**
	 * TESTS: ServiceValidateManualAssetTest.class
	 */
	@Override
	public ManualAssetResult validateManualAsset(ManualAssetContract contract) throws Exception {
		logger.debug("[IN] validateManualAsset");

		ManualAssetResult result = new ManualAssetResult();
		Session session = null;
        try {
            ValidateManualAssetService service = new ValidateManualAssetService(contract);
            service.checkRequiredFields();
            service.buildSearchExpression();

            session = getSessionFactory().attachSession();
            service.setSession(session);
            result.setResult(service.queryAndGetResult());
        } catch (Exception e) {
            LogUtil.logAmindServiceCallException(LogUtil.stackTraceExecutionPoint(), e, contract); 
            throw new SiebelCrmServiceException("validateManualAsset failed", e);
        } finally {
            AmindServiceUtil.detachSession(session);
            logger.debug("[OUT] validateManualAsset");
        }

		return result;
	}
	
	private static Map<String, String> populateRequestFieldMap() {

		Map<String, String> requestFieldMap = new HashMap<String, String>(); 
		requestFieldMap.put("serviceRequestStatusDate", "LXK SW Web SR Status Date");
		requestFieldMap.put("serviceRequestNumber", "SR Number");
		requestFieldMap.put("asset.serialNumber", "Serial Number");
		requestFieldMap.put("asset.modelNumber", "LXK SD Product MTM Name");
		requestFieldMap.put("serviceRequestStatus", "LXK SW Web SR Status");
		requestFieldMap.put("serviceAddress.addressName", "LXK UP Service Address Name");
		requestFieldMap.put("serviceAddress.addressLine1", "LXK UP Service Street Address");
		requestFieldMap.put("serviceAddress.city", "Service City");
		requestFieldMap.put("serviceAddress.state", "Service State");
		requestFieldMap.put("serviceAddress.province", "LXK SW Service Province");
		requestFieldMap.put("serviceAddress.postalCode", "Service Postal Code");
		requestFieldMap.put("serviceAddress.country", "Service Country");
		requestFieldMap.put("custRefNumber", "LXK R SR Customer Ref Number");
		requestFieldMap.put("primaryContact.lastName", "LXK R SR Contact Last Name");
		requestFieldMap.put("primaryContact.firstName", "LXK R SR Contact First Name");
		requestFieldMap.put("primaryContact.workPhone", "LXK SW SR Primary Contact Work Phone");
		requestFieldMap.put("primaryContact.emailAddress", "LXK SW SR Primary Contact Email Address");
		requestFieldMap.put("asset.productLine", "LXK SD Product Line");
		requestFieldMap.put("serviceAddress.addressLine2", "LXK UP Service Street Address2");
		requestFieldMap.put("serviceAddress.addressLine3", "LXK UP Service Street Address3");
		requestFieldMap.put("mdmLevel5AccountId", "Account Id");
		requestFieldMap.put("mdmLevel1AccountId", "LXK SW Agreement Account Global DUNS");
		requestFieldMap.put("mdmLevel2AccountId", "LXK SW Agreement Account Domestic DUNS");
		requestFieldMap.put("mdmLevel3AccountId", "LXK SW Agreement Account LEGAL MDM ID");
		requestFieldMap.put("mdmLevel4AccountId", "LXK SW Agreement Account MDM Account");
		requestFieldMap.put("accountTransFlag", "LXK SW Agreement Account Trans Flag");
		requestFieldMap.put("problemDescription", "Abstract");
		requestFieldMap.put("serviceRequestETA", "LXK R Service Provider ETA");
		requestFieldMap.put("serviceRequestSLA", "LXK R Met SLA");
		requestFieldMap.put("serviceRequestDate", "LXK C Customer Request Date");
		return Collections.unmodifiableMap(requestFieldMap);
	}

	private static Map<String, String> populateRequestTypeFieldMap() {
		Map<String, String> requestTypeFieldMap = new HashMap<String, String>(); 
		requestTypeFieldMap.put("requestNumber", "SR Number");

		return Collections.unmodifiableMap(requestTypeFieldMap);
	}

	
	private static Map<String, String> populateAddressFieldMap() {
		Map<String, String> addressFieldMap = new HashMap<String, String>();
		// for query
		addressFieldMap.put("mdmLevel1AccountId", "LXK SW Account Global DUNS Number");
		addressFieldMap.put("mdmLevel2AccountId", "LXK SW Account Domestic DUNS Number");
		addressFieldMap.put("mdmLevel3AccountId", "LXK SW Account MDM Legal Entity ID #");
		addressFieldMap.put("mdmLevel4AccountId", "LXK SW Account MDM Account #");
		addressFieldMap.put("mdmLevel5AccountId", "Account Id");
		addressFieldMap.put("accountTransFlag", "LXK SW L5 Account Transactable Flag");
		addressFieldMap.put("lbsAddressFlag", "LXK LBS Flag");
		
		addressFieldMap.put("addressName", "Address Name");
		addressFieldMap.put("storeFrontName", "LXK MPS Store Front Name");
		addressFieldMap.put("addressLine1", "Street Address");
		addressFieldMap.put("addressLine2", "Street Address 2");
		addressFieldMap.put("city", "City");
		addressFieldMap.put("state", "State");
		addressFieldMap.put("postalCode", "Postal Code");
		addressFieldMap.put("country", "Country");
		addressFieldMap.put("addressLine3", "Street Address 3");
		addressFieldMap.put("province", "Province");
		addressFieldMap.put("region", "LXK Region");
		
		addressFieldMap.put("county", "County");
		addressFieldMap.put("district", "District");
		addressFieldMap.put("officeNumber", "House #");
		
		return Collections.unmodifiableMap(addressFieldMap);
	}
	
	private static Map<String, String> populateFavoriteFieldMap() {
		Map<String, String> addressFieldMap = new HashMap<String, String>();
		// for query
		addressFieldMap.put("mdmLevel1AccountId", "LXK SW Account Global DUNS Number");
		addressFieldMap.put("mdmLevel2AccountId", "LXK SW Account Domestic DUNS Number");
		addressFieldMap.put("mdmLevel3AccountId", "LXK SW Account MDM Legal Entity ID #");
		addressFieldMap.put("mdmLevel4AccountId", "LXK SW Account MDM Account #");
		addressFieldMap.put("mdmLevel5AccountId", "Account Id");
		addressFieldMap.put("accountTransFlag", "LXK SW L5 Account Transactable Flag");
		//
		
		addressFieldMap.put("addressName", "Address Name");
		addressFieldMap.put("storeFrontName", "LXK MPS Store Front Name");
		addressFieldMap.put("addressLine1", "Street Address");
		addressFieldMap.put("addressLine2", "Street Address 2");
		addressFieldMap.put("city", "City");
		addressFieldMap.put("state", "State");
		addressFieldMap.put("postalCode", "Postal Code");
		addressFieldMap.put("country", "Country");
		addressFieldMap.put("addressLine3", "Street Address 3");
		addressFieldMap.put("province", "Province");
		
		addressFieldMap.put("county", "LXK MPS County");
		addressFieldMap.put("district", "LXK MPS District");
		addressFieldMap.put("officeNumber", "LXK MPS Office#");
		
		return Collections.unmodifiableMap(addressFieldMap);
	}
	
	/**	 
	 * LBS Address Filter;
	 */
	@Override
	public AddressListResult retrieveLBSAddressList(AddressListContract contract)
			throws Exception {
		logger.debug("[IN] retrieveLBSAddressList");

		AddressListResult result = new AddressListResult();

		AmindCrmSessionHandle crmSessionHandle = (AmindCrmSessionHandle) contract.getSessionHandle();
		Session session = null;
		try {
			AddressListService service = new AddressListService(contract,ADDRESS_FIELD_MAP, FAVORITE_FIELD_MAP);
			service.checkLBSRequiredFields();
			service.buildLBSAddressSearchExpression();
			session = crmSessionHandle.acquireMultiple();
			service.setSession(session);

			List<LBSAddress> addressList = service.queryAndGetLBSResultList();
			result.setLbsAddressList(addressList);

		} catch (Exception e) {
			LogUtil.logAmindServiceCallException(LogUtil.stackTraceExecutionPoint(), e, contract);
			throw new SiebelCrmServiceException("retrieveAddressList failed", e);
		} finally {
			AmindServiceUtil.releaseAmindCrmMultipleSessionHandle(crmSessionHandle, session);
			logger.debug("[OUT] retrieveLBSAddressList");
		}

		return result;
	}
}
