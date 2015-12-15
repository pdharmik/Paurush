package com.lexmark.service.impl.real.util;

import java.util.ArrayList;
import java.util.List;

import com.lexmark.domain.ContactInformation;
import com.lexmark.service.impl.real.domain.GlobalContactDO;

public class AmindContactInformationConversionUtil {

	/** util class */
	private AmindContactInformationConversionUtil() {
	}

	public static List<ContactInformation> convertGlobalContactDoListToContactInformationList(
			List<GlobalContactDO> globalContactList) {

		List<ContactInformation> contactInfoList = new ArrayList<ContactInformation>();

		if (globalContactList == null) {
			return contactInfoList;
		}

		for (GlobalContactDO contacDo : globalContactList) {
			ContactInformation contactInfo = new ContactInformation();
			contactInfo.setContactData(contacDo.getNote());
			contactInfo.setRoleName(contacDo.getNoteType());
			contactInfoList.add(contactInfo);
		}

		return contactInfoList;
	}
}
