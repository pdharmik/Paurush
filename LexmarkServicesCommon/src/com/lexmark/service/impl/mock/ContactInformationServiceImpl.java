package com.lexmark.service.impl.mock;

import java.util.ArrayList;
import java.util.List;

import com.lexmark.constants.LexmarkConstants;
import com.lexmark.contract.ContactInformationContract;
import com.lexmark.domain.ContactInformation;
import com.lexmark.result.ContactInformationResult;
import com.lexmark.service.api.ContactInformationService;

public class ContactInformationServiceImpl implements ContactInformationService {

	public ContactInformationResult retrieveContactInformation(
			ContactInformationContract contract) throws Exception {
		List<ContactInformation> contactInfoList = DomainMockDataGenerator.getContactInformationList();
		List<ContactInformation> contactInformations = new ArrayList<ContactInformation>();
		for(ContactInformation contactInformation : contactInfoList){
			if(LexmarkConstants.ROLE_STANDARD_ACCESS.equals(contract.getRoleName()) && "standardaccess".equals(contactInformation.getRoleName())){
				contactInformations.add(contactInformation);
			}else if(LexmarkConstants.ROLE_ACCOUNT_MANAGEMENT.equals(contract.getRoleName()) && "accountmanagement".equals(contactInformation.getRoleName())){
				contactInformations.add(contactInformation);
			}else if(LexmarkConstants.ROLE_BILLING.equals(contract.getRoleName()) && "billing".equals(contactInformation.getRoleName())){
				contactInformations.add(contactInformation);
			}else{}
		}
		ContactInformationResult result = new ContactInformationResult();
		result.setContactInfoList(contactInformations);
		result.setTotalCount(contactInformations.size());
		return result;
	}

}
