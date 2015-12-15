package com.lexmark.service.impl.real;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.amind.session.Session;
import com.amind.session.SessionFactory;
import com.lexmark.contract.AddressListContract;
import com.lexmark.contract.LBSAssetListContract;
import com.lexmark.result.AddressListResult;
import com.lexmark.result.LBSFloorPlanListResult;
import com.lexmark.service.api.LBSFloorPlanService;
import com.lexmark.service.api.LBSLocationService;
import com.lexmark.service.api.SiebelCrmServiceException;
import com.lexmark.service.impl.real.addressService.AddressListService;
import com.lexmark.service.impl.real.addressService.LBSFloorPlanAssetListService;
import com.lexmark.service.impl.real.util.AmindServiceUtil;
import com.lexmark.service.impl.real.util.LogUtil;
public class AmindLBSFloorPlanService extends AmindSiebelCrmService implements LBSFloorPlanService {

	private static final Map<String, String> ADDRESS_FIELD_MAP = populateAddressFieldMap();
	
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

	public AmindLBSFloorPlanService() {
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
	
	
	/**	 
	 * @author David Tsamalashvili 
	 * LBS Floor Plan Asset ;
	 */
	@Override
	public LBSFloorPlanListResult retrieveLBSFloorPlanAssetList(LBSAssetListContract contract)
			throws Exception {
		logger.debug("[IN] retrieveLBSFloorPlanAssetList");

		LBSFloorPlanListResult assetList = null;

		AmindCrmSessionHandle crmSessionHandle = (AmindCrmSessionHandle) contract.getSessionHandle();
		Session session = null;
		try {
			LBSFloorPlanAssetListService service = new LBSFloorPlanAssetListService(contract,ADDRESS_FIELD_MAP);
			service.checkLBSRequiredFields();
			service.buildLBSAssetSearchExpression();
			session = crmSessionHandle.acquireMultiple();
			service.setSession(session);

			assetList = service.queryAndGetLBSAssetResultList();

		} catch (Exception e) {
			LogUtil.logAmindServiceCallException(LogUtil.stackTraceExecutionPoint(), e, contract);
			throw new SiebelCrmServiceException("retrieveLBSLocationList Failed", e);
		} finally {
			AmindServiceUtil.releaseAmindCrmMultipleSessionHandle(crmSessionHandle, session);
			logger.debug("[OUT] retrieveLBSFloorPlanAssetList");
		}

		return assetList;
	}
	
}
