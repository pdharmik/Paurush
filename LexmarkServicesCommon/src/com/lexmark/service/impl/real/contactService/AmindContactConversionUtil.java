package com.lexmark.service.impl.real.contactService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.lexmark.domain.AccountContact;
import com.lexmark.domain.GenericAddress;
import com.lexmark.service.impl.real.domain.AccountContactDo;
import com.lexmark.service.impl.real.domain.AccountFavouriteContactDo;
import com.lexmark.util.LangUtil;

public class AmindContactConversionUtil {

	public static List<AccountContact> convertContactDoListToAccountContactList(
			List<? extends AccountContactDo> contactDoList, List<AccountFavouriteContactDo> favContactDoList) {

		Set<String> favoriteSet = getFavoriteSet(favContactDoList);

		List<AccountContact> contactList = new ArrayList<AccountContact>(contactDoList.size());

		for (AccountContactDo contactDo : contactDoList) {
			AccountContact contact = new AccountContact();
			if (contactDo instanceof AccountFavouriteContactDo) {
				contact.setContactId(((AccountFavouriteContactDo) contactDo).getFavoriteContactId());
				contact.setUserFavorite(true);
			} else {
				contact.setContactId(contactDo.getId());
				if (favoriteSet.contains(contactDo.getId())) {
					contact.setUserFavorite(true);
				}
			}
			contact.setFirstName(contactDo.getFirstName());
			contact.setLastName(contactDo.getLastName());
			contact.setEmailAddress(contactDo.getEmailAddress());
			contact.setWorkPhone(contactDo.getWorkPhone());
			contact.setDepartment(contactDo.getDepartment());
			contact.setMiddleName(contactDo.getMiddleName());
			contact.setAlternatePhone(contactDo.getAlternatePhone());
			if(!LangUtil.isEmpty(contactDo.getJobLevel()) && "DFM Supplies Specialist".equalsIgnoreCase(contactDo.getJobLevel()))
			{
				contact.setManageContactFlag(true);
			} else
			{
				contact.setManageContactFlag(false);
			}
			contact.setAddress(convertAddress(contactDo));

			contactList.add(contact);
		}

		return contactList;
	}

	private static Set<String> getFavoriteSet(List<AccountFavouriteContactDo> favContactDoList) {
		Set<String> favoriteSet = new HashSet<String>();
		if (favContactDoList != null) {
			for (AccountFavouriteContactDo acDo : favContactDoList) {
				favoriteSet.add(acDo.getFavoriteContactId());
			}
		}
		return favoriteSet;
	}

	private static GenericAddress convertAddress(AccountContactDo contactDo) {
		GenericAddress address = new GenericAddress();
		address.setAddressId(contactDo.getAddressId());
		address.setAddressLine1(contactDo.getAddressLine1());
		address.setAddressLine2(contactDo.getAddressLine2());
		address.setAddressLine3(contactDo.getAddressLine3());
		address.setAddressName(contactDo.getAddressName());
		address.setCity(contactDo.getCity());
		address.setCountry(contactDo.getCountry());
		address.setPostalCode(contactDo.getPostalCode());
		address.setProvince(contactDo.getProvince());
		address.setStateProvince(contactDo.getProvince());
		address.setState(contactDo.getState());
		address.setPhysicalLocation1(contactDo.getPhysicalLocation1());
		address.setPhysicalLocation2(contactDo.getPhysicalLocation2());
		address.setPhysicalLocation3(contactDo.getPhysicalLocation3());
		
		address.setCounty(contactDo.getCounty());
		address.setDistrict(contactDo.getDistrict());
		address.setOfficeNumber(contactDo.getOfficeNumber());
		return address;
	}

}
