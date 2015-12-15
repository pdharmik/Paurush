package com.lexmark.service.impl.real;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.amind.session.Session;
import com.amind.session.SessionFactory;
import com.lexmark.contract.ContactListContract;
import com.lexmark.contract.FavoriteContract;
import com.lexmark.result.ContactListResult;
import com.lexmark.result.FavoriteResult;
import com.lexmark.service.api.ContactService;
import com.lexmark.service.api.SiebelCrmServiceException;
import com.lexmark.service.impl.real.contactService.ContactListService;
import com.lexmark.service.impl.real.contactService.UpdateContactService;
import com.lexmark.service.impl.real.util.AmindServiceUtil;
import com.lexmark.service.impl.real.util.LogUtil;

public class AmindContactService extends AmindSiebelCrmService implements ContactService {

	private SessionFactory sessionFactory;
	private static final Map<String, String> FIELD_MAP = fieldMap();
	private static final Map<String, String> FAVORITE_FIELD_MAP = favoriteFieldMap();

	AmindContactService() {
	}

	/**
	 * TESTS: ContactListFilterTest.class, ContactListSortTest.class,
	 * ContactListPaginationTest.class, ContactListFavoriteTest.class,
	 * 
	 * 
	 * Do-objects: 
	 *   AccountContactDo / do-accountcontactdo-mapping.xml
	 *   AccountFavouriteContactDo / do-accountfavouritecontactdo-mapping.xml
	 */
	public ContactListResult retrieveContactList(ContactListContract contract) throws Exception {
		logger.debug("[IN] retrieveContactList");

		ContactListResult result = new ContactListResult();
		AmindCrmSessionHandle crmSessionHandle = (AmindCrmSessionHandle) contract.getSessionHandle();
		Session favoritesSession = null;
		Session session = null;
		try {
			ContactListService service = new ContactListService(contract,FIELD_MAP,FAVORITE_FIELD_MAP);
			service.checkRequiredFields();
	
			session = crmSessionHandle.acquireMultiple();
			service.setSession(session);
	
			service.buildSearchExpression();

			if(!contract.isFavoriteFlag()) {
				favoritesSession = getStatelessSessionFactory().attachSession();
				service.setFavoritesSession(favoritesSession);
			}
			
			result.setContacts(service.queryAndGetResultList());
			if(contract.isNewQueryIndicator()) {
				int count = service.processTotalCount();
				result.setTotalCount(count);
				crmSessionHandle.setContactCount(count);
			} else {
				result.setTotalCount(crmSessionHandle.getContactCount());
			}

		} catch (Exception e) {
		    LogUtil.logAmindServiceCallException(LogUtil.stackTraceExecutionPoint(), e, contract);
			throw new SiebelCrmServiceException("retrieveContactList failed", e);
		} finally {
			AmindServiceUtil.releaseAmindCrmMultipleSessionHandle(crmSessionHandle, session);
			AmindServiceUtil.releaseSession(favoritesSession);
		}

		logger.debug("[OUT] retrieveContatList");
		return result;
	}

    /**
     * based on {@link FavoriteContract} isFavoriteFlag creates or deletes a
     * record on LXK SW Person Favorite Contacts IO/IC.
     * 
     * TESTS: ContactUpdateFavoriteTest.class
     * 
     * 
     * Do-objects: 
     *   UserFavoriteContactDo / do-userfavoritecontactdo-mapping.xml
     */

	public FavoriteResult updateUserFavoriteContact(FavoriteContract contract) throws Exception {
		logger.debug("[IN] updateUserFavoriteContact");
		FavoriteResult result = new FavoriteResult();
//		AmindCrmSessionHandle crmSessionHandle = (AmindCrmSessionHandle) contract.getSessionHandle();
		Session session = null;
		try {
			UpdateContactService contactService = new UpdateContactService(contract);
			contactService.checkRequiredFields();
			contactService.buildSearchExpression();
			session = getSessionFactory().attachSession();
//			session = crmSessionHandle.acquireMultiple();
			contactService.setSession(session);
			
			result.setResult(contactService.queryAndUpdate());
		} catch (Exception e) {
		    LogUtil.logAmindServiceCallException(LogUtil.stackTraceExecutionPoint(), e, contract);
			throw new SiebelCrmServiceException("updateUserFavoriteContact failed", e);
		} finally {
			AmindServiceUtil.detachSession(session);
//			AmindServiceUtil.releaseAmindCrmMultipleSessionHandle(crmSessionHandle, session);
		}
		
		logger.debug("[OUT] updateUserFavoriteContact");
		return result;
	}

	private static Map<String, String> favoriteFieldMap() {
        Map<String, String> fieldMap = new HashMap<String, String>();
        // for query
        fieldMap.put("contactId", "Person Id");
        //
        
        // for sorting/filtering
        fieldMap.put("firstName", "First Name");
        fieldMap.put("lastName", "Last Name");
        fieldMap.put("workPhone", "Work Phone #");
        fieldMap.put("alternatePhone", "Alternate Phone #");
        fieldMap.put("emailAddress", "Email Address");
        fieldMap.put("addressLine1", "LXK MPS Personal Street Address");
        fieldMap.put("addressLine2", "LXK MPS Personal Street Address 2");
        fieldMap.put("addressLine3", "LXK MPS Street Address 3");
        fieldMap.put("city", "LXK MPS Personal City");
        fieldMap.put("state", "LXK MPS Personal State");
        fieldMap.put("country", "LXK MPS Personal Country");
        fieldMap.put("province", "LXK MPS Personal Province");
        fieldMap.put("postalCode", "LXK MPS Personal Postal Code");
        
        // TODO doesn't need for sorting/filtering, remove?
        fieldMap.put("id", "Id");
        fieldMap.put("name", "Name");
        fieldMap.put("favoriteContactId", "Favorite Contact Id");
        fieldMap.put("favoriteFlagType", "Party Relationship Type");
        fieldMap.put("addressName", "LXK MPS Personal Address Name");
        fieldMap.put("addressId", "LXK MPS Primary Personal Address Id");
        fieldMap.put("phisycalLocation1", "LXK MPS Physical Location 1");
        fieldMap.put("phisycalLocation2", "LXK MPS Physical Location 2");
        fieldMap.put("phisycalLocation3", "LXK MPS Physical Location 3");
        fieldMap.put("jobLevel", "LXK MPS Job Level Lexmark");
        //
        
        return Collections.unmodifiableMap(fieldMap);
    }

    private static Map<String, String> fieldMap() {
        Map<String, String> fieldMap = new HashMap<String, String>();
        
        // for query
        fieldMap.put("mdmLevel1AccountId", "LXK SW Account Global DUNS Number");
        fieldMap.put("mdmLevel2AccountId", "LXK SW Account Domestic DUNS Number");
        fieldMap.put("mdmLevel3AccountId", "LXK SW Account MDM Legal Entity ID #");
        fieldMap.put("mdmLevel4AccountId", "LXK SW Account MDM Account #");
        fieldMap.put("mdmLevel5AccountId", "LXK SW Account Id");
        fieldMap.put("accountTransFlag", "LXK SW L5 Account Transactable Flag");
        //
        
        // for sorting/filtering
        fieldMap.put("firstName", "First Name");
        fieldMap.put("lastName", "Last Name");
        fieldMap.put("emailAddress", "Email Address");
        fieldMap.put("workPhone", "Work Phone #");
        fieldMap.put("alternatePhone", "LXK SW Alternate Primary Phone Number");
        fieldMap.put("addressLine1", "LXK MPS Personal Street Address");
        fieldMap.put("addressLine2", "LXK MPS Personal Street Address 2");
        fieldMap.put("addressLine3", "LXK MPS Street Address 3");
        fieldMap.put("city", "LXK MPS Personal City");
        fieldMap.put("state", "LXK MPS Personal State");
        fieldMap.put("country", "LXK MPS Personal Country");
        fieldMap.put("province", "LXK MPS Personal Province");
        fieldMap.put("postalCode", "LXK MPS Personal Postal Code");
        fieldMap.put("county", "LXK MPS Personal County");
        //
        
        // TODO doesn't need for sorting/filtering, remove?
        fieldMap.put("id", "Id");
        fieldMap.put("contactAliasId", "LXK Contact Alias Id");
        fieldMap.put("addressName", "LXK MPS Personal Address Name");
        fieldMap.put("addressId", "LXK MPS Primary Personal Address Id");
        fieldMap.put("phisycalLocation1", "LXK MPS Physical Location 1");
        fieldMap.put("phisycalLocation2", "LXK MPS Physical Location 2");
        fieldMap.put("phisycalLocation3", "LXK MPS Physical Location 3");
        fieldMap.put("jobLevel", "Job Level Lexmark");
        //
        
        return Collections.unmodifiableMap(fieldMap);
    }

	public SessionFactory getSessionFactory() {
		if (sessionFactory == null) {
			throw new SiebelCrmServiceException("SessionFactory not initialized");
		}
	    return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
	    this.sessionFactory = sessionFactory;
	}

}
