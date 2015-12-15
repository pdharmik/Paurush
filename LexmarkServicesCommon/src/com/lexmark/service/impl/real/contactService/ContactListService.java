package com.lexmark.service.impl.real.contactService;

import static com.lexmark.service.impl.real.contactService.AmindContactConversionUtil.convertContactDoListToAccountContactList;
import static com.lexmark.service.impl.real.util.AmindServiceUtil.SIEBEL_MDMLEVEL;
import static com.lexmark.service.impl.real.util.AmindServiceUtil.buildBasicQueryObject;
import static com.lexmark.service.impl.real.util.AmindServiceUtil.buildCriteria;
import static com.lexmark.service.impl.real.util.AmindServiceUtil.buildSearchCriteria;
import static com.lexmark.service.impl.real.util.AmindServiceUtil.buildSortString;
import static com.lexmark.service.impl.real.util.AmindServiceUtil.buildmdmSearchExpression;
import static com.lexmark.service.impl.real.util.AmindServiceUtil.getTotalCount;
import static com.lexmark.util.LangUtil.isEmpty;
import static com.lexmark.util.LangUtil.isNotEmpty;
import static com.lexmark.util.LangUtil.notNull;
import static com.lexmark.util.LangUtil.isNotBlank;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.amind.common.service.SiebelBusinessServiceProxy;
import com.amind.data.service.ActionEvent;
import com.amind.data.service.IDataManager;
import com.amind.data.service.QueryObject;
import com.amind.session.Session;
import com.lexmark.contract.ContactListContract;
import com.lexmark.domain.AccountContact;
import com.lexmark.service.impl.real.domain.AccountContactDo;
import com.lexmark.service.impl.real.domain.AccountFavouriteContactDo;

public class ContactListService {
	private final ContactListContract contract;
	private final Map<String, String> allContactFieldMap;
	private final Map<String, String> favoriteFieldMap;
	private final boolean favoriteFlag;
	private Session session;
	private String favoriteSearchExpression;
	private String allContactSearchExpression;
	private QueryObject favCriteria;
	private QueryObject allContactCriteria;
	private Session favoritesSession;
	
	public ContactListService(ContactListContract contract, Map<String, String> allContactFieldMap, Map<String, String> favoriteFieldMap){
		if(contract == null) {
			throw new IllegalStateException("contract can not be null");
		}
		this.contract = contract;
		this.allContactFieldMap = allContactFieldMap;
		this.favoriteFieldMap = favoriteFieldMap;
		this.favoriteFlag = contract.isFavoriteFlag();
	}
	
	public void checkRequiredFields() {
		checkFavoriteContactListFields(this.contract);
		if(!this.favoriteFlag){
			checkContactListFields(this.contract);
		}		
	}

	public void buildSearchExpression(){
		buildFavoriteContactSearchExpression();
		buildFavoriteContactCriteria();
		if(!this.favoriteFlag){
			buildAllContactSearchExpression();
			buildAllContactCriteria();
		}
		
	}
	
	public List<AccountContact> queryAndGetResultList() {
		IDataManager dataManager = getSession().getDataManager();
		List<AccountContact> contactList = new ArrayList<AccountContact>();
		List<AccountFavouriteContactDo> favContactList = new ArrayList<AccountFavouriteContactDo>();
		if(!this.favoriteFlag){
			favContactList = queryFavoriteContactList(getFavoritesSession().getDataManager());
			List<AccountContactDo> contactDoList = queryContactList(dataManager);
			contactList = convertContactDoListToAccountContactList(contactDoList, favContactList);
		} else {
			favContactList = queryFavoriteContactList(dataManager);
			contactList = convertContactDoListToAccountContactList(favContactList, null);
		}
		return contactList;
	}

	public int processTotalCount() {
		int totalCount = 0;
		SiebelBusinessServiceProxy proxy = getSession().getSiebelBusServiceProxy();
			
		if(favoriteFlag) {
			totalCount = getTotalCount("LXK SW Person Favorite Contacts",
					"LXK SW Person Favorite Contacts", this.favoriteSearchExpression, proxy);
		} else {
			totalCount = getTotalCount("LXK SW Contact - Service Web",
					"LXK SW Contact - Service Web", this.allContactSearchExpression, proxy);
		}
		return totalCount;

	}
	
	private void buildAllContactSearchExpression() {
		StringBuilder builder = new StringBuilder();

		if (!SIEBEL_MDMLEVEL.equals(contract.getMdmLevel())) {
			builder.append("EXISTS ");
		}
		
		builder.append(" ( ");
		builder.append(buildmdmSearchExpression(contract.getMdmId(),
				contract.getMdmLevel(), allContactFieldMap, true, false));
		builder.append(") ");
		builder.append("AND");

		builder.append(" [Id] = [LXK Contact Alias Id]");

		Map<String, Object> searchCriteria = contract.getSearchCriteria();
		if (isNotEmpty(searchCriteria)) {
			builder.append(buildSearchCriteria(searchCriteria, allContactFieldMap));
		}

		Map<String, Object> filterCriteria = contract.getFilterCriteria();
		if (isNotEmpty(filterCriteria)) {
		builder.append(buildCriteria(filterCriteria, allContactFieldMap, true, true));
	
		}
		this.allContactSearchExpression = builder.toString();
	}
	
	@SuppressWarnings("unchecked")
	private List<AccountFavouriteContactDo> queryFavoriteContactList(IDataManager dataManager) {

		List<AccountFavouriteContactDo> favContactDoList ;
		if (contract.isNewQueryIndicator()) {
			favContactDoList = dataManager.query(favCriteria);
		} else {
			favContactDoList = dataManager.queryNext(favCriteria);
		}
		return notNull(favContactDoList);
	}
	
	@SuppressWarnings("unchecked")
	private List<AccountContactDo> queryContactList(IDataManager dataManager) {
		List<AccountContactDo> contactDoList;
		if (contract.isNewQueryIndicator()) {
			contactDoList = dataManager.query(allContactCriteria);
		} else {
			contactDoList = dataManager.queryNext(allContactCriteria);
		}
		return notNull(contactDoList);
	}
	

	private void buildFavoriteContactCriteria() {

		QueryObject criteria = new QueryObject(AccountFavouriteContactDo.class, ActionEvent.QUERY);
		if(favoriteFlag) {
			buildBasicQueryObject(criteria, contract.getIncrement(), contract.getStartRecordNumber());
		}
		
		criteria.addComponentSearchSpec(AccountFavouriteContactDo.class, favoriteSearchExpression);
		criteria.setSortString(buildSortString(contract.getSortCriteria(), this.favoriteFieldMap));
		this.favCriteria = criteria;
	}
	
	private void buildAllContactCriteria() {

		QueryObject criteria = new QueryObject(AccountContactDo.class, ActionEvent.QUERY);
		buildBasicQueryObject(criteria, contract.getIncrement(), contract.getStartRecordNumber());
		criteria.addComponentSearchSpec(AccountContactDo.class, this.allContactSearchExpression);
		criteria.setSortString(buildSortString(contract.getSortCriteria(), this.allContactFieldMap));
		this.allContactCriteria = criteria;
	}

	private void buildFavoriteContactSearchExpression() {

		StringBuilder result = new StringBuilder();
		result.append("[");
		result.append(this.favoriteFieldMap.get("contactId"));
		result.append("] = '");
		result.append(contract.getContactId());
		result.append("'");

		Map<String, Object> searchCriteria = contract.getSearchCriteria();
		if (isNotEmpty(searchCriteria)) {
			result.append(buildSearchCriteria(searchCriteria, this.favoriteFieldMap));
		}

		Map<String, Object> filterCriteria = contract.getFilterCriteria();
		if (isNotEmpty(filterCriteria)) {
			result.append(buildCriteria(filterCriteria, this.favoriteFieldMap, true, true));
		}

		this.favoriteSearchExpression = result.toString();
	}
	
	private void checkContactListFields(ContactListContract contract) {
		String mdmId = contract.getMdmId();
		if (mdmId == null) {
			throw new IllegalArgumentException("MDM Id is null");
		} else if (mdmId.isEmpty()) {
			throw new IllegalArgumentException("MDM Id is empty");
		}
		String mdmLevel = contract.getMdmLevel();
		if (mdmLevel == null) {
			throw new IllegalArgumentException("MDM Level is null");
		} else if (mdmLevel.isEmpty()) {
			throw new IllegalArgumentException("MDM Level is empty");
		}
	}

	private void checkFavoriteContactListFields(ContactListContract contract) {
		String contactId = contract.getContactId();
		if (contactId == null) {
			throw new IllegalArgumentException("Contract Id is null");
		} else if (contactId.isEmpty()) {
			throw new IllegalArgumentException("Contract Id is Empty");
		}
	}
	
	public Session getSession() {
		if (session == null) {
			throw new IllegalStateException("session has not set!");
		} else {
			return session;
		}
	}

	public void setSession(Session session) {
		if (session == null) {
			throw new IllegalArgumentException("session can not be null!");
		} else {
			this.session = session;
		}
	}
	public Session getFavoritesSession() {
		if (favoritesSession == null) {
			throw new IllegalStateException("favorites session has not been set!");
		} else {
			return favoritesSession;
		}
	}

	public void setFavoritesSession(Session favoritesSession) {
		if (favoritesSession == null) {
			throw new IllegalStateException("favorites session can not be null!");
		} else {
			this.favoritesSession = favoritesSession;
		}
	}
}
