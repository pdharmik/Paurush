package com.lexmark.service.impl.real.addressService;

import static com.lexmark.util.LangUtil.isEmpty;
import static com.lexmark.util.LangUtil.isNotEmpty;
import static com.lexmark.util.LangUtil.notNull;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.logging.log4j.LogManager;

import com.amind.data.service.IDataManager;
import com.amind.session.Session;
import com.lexmark.contract.FavoriteAddressContract;
import com.lexmark.service.impl.real.domain.UserFavoriteAddressDo;
import com.lexmark.util.LangUtil;
import org.apache.logging.log4j.Logger;


public class UpdateAddressService {
	//private static final Log logger = LogFactory.getLog(UpdateAddressService.class);
	private static final Logger logger = LogManager.getLogger(UpdateAddressService.class);
	private static final String FAVORITE_RELATIONSHIP_TYPE = "Favorite";
	private static final String DELETE_STATUS = "Marked For Deletion";
	private final FavoriteAddressContract contract;
	private final boolean favFlag;
	private Session session;
	private String searchExpression;

	public UpdateAddressService(FavoriteAddressContract contract) {
		if (contract == null) {
			throw new IllegalStateException("contract can not be null");
		}
		this.contract = contract;
		this.favFlag = contract.isFavoriteFlag();
	}

	public void checkRequiredFields() {
		String favoriteAddressId = contract.getFavoriteAddressId();
		if (favoriteAddressId == null) {
			throw new IllegalArgumentException("favoriteAddressId is null!");
		} else if (favoriteAddressId.isEmpty()) {
			throw new IllegalArgumentException("favoriteAddressId is empty!");
		}
		String contactId = contract.getContactId();
		if (contactId == null) {
			throw new IllegalArgumentException("contactId is null!");
		} else if (contactId.isEmpty()) {
			throw new IllegalArgumentException("contactId is empty!");
		}
	}

	public void buildSearchExpression() {
		
		StringBuilder builder = new StringBuilder("[favoriteAddressId]='");
		builder.append(contract.getFavoriteAddressId());
		builder.append("' AND ([accountId]='");
		builder.append(contract.getAccountId()  + "'");
		builder.append(" OR [accountId] ");
		builder.append( "IS NULL)");
		builder.append(" AND ([contactId]='");
		builder.append(contract.getContactId()  + "'");
		builder.append(" OR [contactId] IS NULL)");
		
		//builder.append(" AND [relationshipType]=");
		//builder.append("Favorite");
		searchExpression = builder.toString();
	}

	public boolean queryAndUpdate() {
		IDataManager dataManager = getSession().getDataManager();
		List<UserFavoriteAddressDo> addresses = queryUserFavoriteContactList(dataManager);
		boolean successFlag;
		if (favFlag) {
			successFlag =  createUserFavoriteAddress(dataManager, addresses);
		} else {
			successFlag = deleteUserFavoriteAddress(dataManager, addresses);
		}
		return successFlag;
	}

	@SuppressWarnings("unchecked")
	private List<UserFavoriteAddressDo> queryUserFavoriteContactList(IDataManager dataManager) {

		List<UserFavoriteAddressDo> addresses = dataManager.queryBySearchExpression(
				UserFavoriteAddressDo.class, searchExpression);

		return notNull(addresses);
	}

	private boolean createUserFavoriteAddress(IDataManager dataManager, List<UserFavoriteAddressDo> addresses) {
		boolean addressFlag;
			if (isEmpty(addresses)) {
				UserFavoriteAddressDo addressDo = new UserFavoriteAddressDo();
				addressDo.setContactId(contract.getContactId());
				addressDo.setAccountId(contract.getAccountId());
				addressDo.setFavoriteAddressId(contract.getFavoriteAddressId());
				addressDo.setRelationshipType(FAVORITE_RELATIONSHIP_TYPE);
				addressDo.setStatus("Favorite");
				dataManager.create(addressDo);
				addressFlag = true;
			} else {
//				if(addresses.get(0).getStatus().equalsIgnoreCase(FAVORITE_RELATIONSHIP_TYPE) && (!LangUtil.isBlank(addresses.get(0).getAccountId()) || addresses.get(0).getAccountId()!= null) 
//						&& addresses.get(0).getContactId()!= null) {
//					logger.warn("Favorite record with contact Id: " + contract.getContactId()
//							+ " and favorite address Id: " + contract.getFavoriteAddressId() + " already exists");
//					addressFlag = false;
//				} 
//				else{
//					if(addresses.get(0) != null && addresses.get(0).getStatus() != null  
//							&& !addresses.get(0).getStatus().equalsIgnoreCase("Favorite")  )  {
						UserFavoriteAddressDo addressDo = addresses.get(0);
						addressDo.setRelationshipType(FAVORITE_RELATIONSHIP_TYPE);
						addressDo.setStatus("Favorite");
						addressDo.setPrimaryId(addressDo.getId());
						if(LangUtil.isBlank(addresses.get(0).getAccountId()) || addresses.get(0).getAccountId()== null)
						{
							addressDo.setAccountId(contract.getAccountId());
						}
						dataManager.update(addressDo);
						addressFlag = true;
//					} else {
//						logger.warn("Favorite record with contact Id: " + contract.getContactId()
//								+ " and favorite address Id: " + contract.getFavoriteAddressId() + " already exists>>>>>>");
//						addressFlag = false;
//					}
//			}
		}
		return addressFlag;
	}

	private boolean deleteUserFavoriteAddress(IDataManager dataManager, List<UserFavoriteAddressDo> addresses) {
		boolean addressFlag;
		if (isNotEmpty(addresses) && addresses.get(0).getStatus() != null && 
				!addresses.get(0).getStatus().equalsIgnoreCase(DELETE_STATUS) ) {
			UserFavoriteAddressDo addressDo = addresses.get(0);
			addressDo.setStatus(DELETE_STATUS);
			addressDo.setPrimaryId(addressDo.getId());
			dataManager.update(addressDo);
			addressFlag = true;
		} else {
			logger.warn("Favorite record with contact Id: " + contract.getContactId()
					+ " and favorite address Id: " + contract.getFavoriteAddressId() + " doesn't exist");
			addressFlag = false;
		}
		return addressFlag;
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

}
