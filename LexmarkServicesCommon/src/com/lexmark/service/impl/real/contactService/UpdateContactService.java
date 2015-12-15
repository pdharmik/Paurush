package com.lexmark.service.impl.real.contactService;

import static com.lexmark.util.LangUtil.isEmpty;
import static com.lexmark.util.LangUtil.isNotEmpty;
import static com.lexmark.util.LangUtil.notNull;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.logging.log4j.LogManager;

import com.amind.data.service.IDataManager;
import com.amind.session.Session;
import com.lexmark.contract.FavoriteContract;
import com.lexmark.service.impl.real.domain.UserFavoriteContactDo;
import org.apache.logging.log4j.Logger;


public class UpdateContactService {

	//protected static final Log logger = LogFactory.getLog(UpdateContactService.class);
	protected static final Logger logger = LogManager.getLogger(UpdateContactService.class);
	private final FavoriteContract contract;
	private Session session;
	private String searchExpression;
	
	public UpdateContactService(FavoriteContract contract) {
		if(contract == null) {
			throw new IllegalStateException("contract can not be null");
		} 
		this.contract = contract;
	}
	
	public void checkRequiredFields() {
		checkUpdateUserFavoriteFields();
	}
	
	public void buildSearchExpression()
	{
		buildUpdateUserFavoriteContactSearchExpression();
	}
	
	public boolean queryAndUpdate() {
		IDataManager dataManager = getSession().getDataManager();
		List<UserFavoriteContactDo> contactList = queryUserFavoriteContactDoList(dataManager);
		boolean contactFlag;
		 if (contract.isFavoriteFlag()) {
			 contactFlag =  createUserFavoriteContact(contactList, dataManager);
		} else {
			contactFlag =  deleteUserFavoriteContact(contactList, dataManager);
		}
		 return contactFlag;
	}
	
	private void buildUpdateUserFavoriteContactSearchExpression() {
		StringBuilder expr = new StringBuilder();
		expr.append("[favoriteContactId]='");
		expr.append(contract.getFavoriteContactId());
		expr.append("' AND [contactId]='");
		expr.append(contract.getContactId());
		expr.append("'");
		this.setSearchExpression(expr.toString());
	}
	
	private void checkUpdateUserFavoriteFields() {
		String contactId = contract.getContactId();
		if (contactId == null) {
			throw new IllegalArgumentException("Contract Id is null");
		} else if (contactId.isEmpty()) {
			throw new IllegalArgumentException("Contract Id is Empty");
		}
		String favContactId = contract.getFavoriteContactId();
		if (favContactId == null) {
			throw new IllegalArgumentException("Favorite Contract Id is null");
		} else if (favContactId.isEmpty()) {
			throw new IllegalArgumentException("Favorite Contract Id is Empty");
		}
	}
	
	@SuppressWarnings("unchecked")
	private List<UserFavoriteContactDo> queryUserFavoriteContactDoList(IDataManager dataManager) {

		List<UserFavoriteContactDo> contacts = dataManager.queryBySearchExpression(
				UserFavoriteContactDo.class, this.searchExpression);
		return notNull(contacts);
	}
	
	private boolean createUserFavoriteContact(List<UserFavoriteContactDo> contacts, IDataManager dataManager) {
		boolean contactFlag;
		if (isEmpty(contacts)) {
			UserFavoriteContactDo contactDo = new UserFavoriteContactDo();
			contactDo.setFavoriteContactId(contract.getFavoriteContactId());
			contactDo.setContactId(contract.getContactId());

			dataManager.create(contactDo);
			contactFlag =  true;
		} else {
			logger.warn("Favorite record with contact Id: " + contract.getContactId()
					+ " and favorite contact Id: " + contract.getFavoriteContactId() + " already exists");
			contactFlag = false;
		}
		return contactFlag;
	}
	
	private  boolean deleteUserFavoriteContact(List<UserFavoriteContactDo> contacts, IDataManager dataManager) {
		boolean contactFlag;
		if (isNotEmpty(contacts)) {

			UserFavoriteContactDo contactDo = new UserFavoriteContactDo();
			contactDo.setFavoriteContactId(contract.getFavoriteContactId());
			contactDo.setContactId(contract.getContactId());
			dataManager.delete(contactDo);
		
			contactFlag = true;
		} else {
			logger.warn("Favorite record with contact Id: " + contract.getContactId()
					+ " and favorite contact Id: " + contract.getFavoriteContactId() + " doesn't exist");
			contactFlag = false;
		}
		return contactFlag;
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
	
	public void setSearchExpression(String searchExpression) {
		this.searchExpression = searchExpression;
	}
	
}
