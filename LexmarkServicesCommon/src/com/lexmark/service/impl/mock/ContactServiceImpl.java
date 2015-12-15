package com.lexmark.service.impl.mock;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import com.lexmark.contract.ContactListContract;
import com.lexmark.contract.CreateServiceRequestContract;
import com.lexmark.contract.FavoriteAddressContract;
import com.lexmark.contract.FavoriteContract;
import com.lexmark.contract.ModifyContactContract;
import com.lexmark.contract.ServiceAddressListContract;
import com.lexmark.contract.ServiceRequestContract;
import com.lexmark.contract.ServiceRequestListContract;
import com.lexmark.domain.Account;
import com.lexmark.domain.AccountContact;
import com.lexmark.result.ContactListResult;
import com.lexmark.result.CreateServiceRequestResult;
import com.lexmark.result.FavoriteResult;
import com.lexmark.result.ModifyContactResult;
import com.lexmark.result.ServiceAddressListResult;
import com.lexmark.result.ServiceRequestListResult;
import com.lexmark.result.ServiceRequestResult;
import com.lexmark.service.api.ContactService;


public class ContactServiceImpl implements ContactService{
	private static Logger LOGGER = LogManager.getLogger(ContactServiceImpl.class);
	
	public ContactListResult retrieveContactList (ContactListContract contactListContract)throws Exception{
		ContactListResult clr = new ContactListResult();
		List<AccountContact> accountContacts = DomainMockDataGenerator.getAccountContactList();				
			clr.setTotalCount(accountContacts.size());
			clr.setContacts(accountContacts);
		return  clr;
	}
	
	public FavoriteResult updateUserFavoriteContact(FavoriteContract favoriteContract)
	throws Exception{
		LOGGER.info("********************hey, let's check now* setContactFavourite***********");
		FavoriteResult favouriteResult = new FavoriteResult();
		List<AccountContact> accountContacts = DomainMockDataGenerator.getAccountContactList();
		Iterator<AccountContact> it = accountContacts.iterator();
		while(it.hasNext()){
			AccountContact ac = it.next();
			String favContactId = favoriteContract.getFavoriteContactId();
			if(favContactId.equals(ac.getContactId())){
				LOGGER.info("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
				LOGGER.info("fav contact id " + favContactId + "set flag" +  favoriteContract.isFavoriteFlag());
				ac.setUserFavorite(favoriteContract.isFavoriteFlag());
			}
		}
		//the result only indicates if the webservice call is successful or not
		favouriteResult.setResult(true);
		return favouriteResult;
	}
}