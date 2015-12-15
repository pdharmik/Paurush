package com.lexmark.service.api;

import com.lexmark.contract.ContactListContract;
import com.lexmark.contract.FavoriteContract;
import com.lexmark.result.ContactListResult;
import com.lexmark.result.FavoriteResult;


public interface ContactService {

	
	public FavoriteResult updateUserFavoriteContact(FavoriteContract favoriteContract)
	throws Exception;
	
	public ContactListResult retrieveContactList(ContactListContract contactListContract)
	throws Exception;	

}
