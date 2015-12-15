package com.lexmark.service.impl.real;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.amind.session.Session;
import com.amind.session.SessionFactory;
import com.lexmark.contract.AddressListContract;
import com.lexmark.result.AddressListResult;
import com.lexmark.service.api.LBSLocationService;
import com.lexmark.service.api.SiebelCrmServiceException;
import com.lexmark.service.impl.real.addressService.AddressListService;
import com.lexmark.service.impl.real.util.AmindServiceUtil;
import com.lexmark.service.impl.real.util.LogUtil;
public class AmindLBSLocationService extends AmindSiebelCrmService implements
		LBSLocationService {

	private static final Map<String, String> ADDRESS_FIELD_MAP = populateAddressFieldMap();
	private static final Map<String, String> LBS_FIELD_MAP = populateLBSFieldMap();
	
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

	public AmindLBSLocationService() {
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
		
		addressFieldMap.put("county", "County");
		addressFieldMap.put("district", "District");
		addressFieldMap.put("officeNumber", "House #");
		
		return Collections.unmodifiableMap(addressFieldMap);
	}
	
	private static Map<String, String> populateLBSFieldMap() {
		Map<String, String> addressFieldMap = new HashMap<String, String>();
		// for query
		addressFieldMap.put("mdmLevel1AccountId", "LXK LBS Global DUNS Account Number");
		addressFieldMap.put("mdmLevel2AccountId", "LXK LBS Domestic DUNS Account Number");
		addressFieldMap.put("mdmLevel3AccountId", "LXK LBS Legal Account Number");
		addressFieldMap.put("mdmLevel4AccountId", "LXK LBS MDM Account Number");
		addressFieldMap.put("mdmLevel5AccountId", "LXK LBS L5 Account Id");
		
		return Collections.unmodifiableMap(addressFieldMap);
	}
	
	/**	 
	 * @author David Tsamalashvili 
	 * LBS Location Filter;
	 */
	@Override
	public AddressListResult retrieveLBSLocationList(AddressListContract contract)
			throws Exception {
		logger.debug("[IN] retrieveLBSLocationList");

		AddressListResult addressList = null;

		AmindCrmSessionHandle crmSessionHandle = (AmindCrmSessionHandle) contract.getSessionHandle();
		Session session = null;
		try {
			AddressListService service = new AddressListService(contract,ADDRESS_FIELD_MAP, LBS_FIELD_MAP);
			service.checkLBSRequiredFields();
			service.buildLBSLocationSearchExpression();
			session = crmSessionHandle.acquireMultiple();
			service.setSession(session);

			addressList = service.queryAndGetLBSLocationResultList();

		} catch (Exception e) {
			LogUtil.logAmindServiceCallException(LogUtil.stackTraceExecutionPoint(), e, contract);
			throw new SiebelCrmServiceException("retrieveLBSLocationList Failed", e);
		} finally {
			AmindServiceUtil.releaseAmindCrmMultipleSessionHandle(crmSessionHandle, session);
			logger.debug("[OUT] retrieveLBSLocationList");
		}

		return addressList;
	}
	
	@Override
	public AddressListResult retrieveLBSBuildingTypes(AddressListContract contract) throws Exception {
		logger.debug("[IN] retrieveLBSBuildingTypes");
		AddressListResult result = new AddressListResult();
		AmindCrmSessionHandle crmSessionHandle = (AmindCrmSessionHandle) contract.getSessionHandle();
		Session session = null;
		try {
			AddressListService service = new AddressListService(contract, ADDRESS_FIELD_MAP, LBS_FIELD_MAP);
			service.checkLBSRequiredFields();
			service.buildBuildingTypeSearchSpec();
			session = crmSessionHandle.acquireMultiple();
			service.setSession(session);
			
			result.setLbsLocationBuildingType(service.queryAndGetLBSBuildingTypes());
			
		} catch (Exception e) {
			LogUtil.logAmindServiceCallException(LogUtil.stackTraceExecutionPoint(), e, contract);
			throw new SiebelCrmServiceException("retrieveLBSBuildingTypes Failed", e);
		} finally {
			AmindServiceUtil.releaseAmindCrmMultipleSessionHandle(crmSessionHandle, session);
			logger.debug("[OUT] retrieveLBSBuildingTypes");
		}
		
		return result;
	}
	
}
