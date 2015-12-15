package com.lexmark.service.api;

import com.lexmark.contract.ContactInformationContract;
import com.lexmark.result.ContactInformationResult;

public interface ContactInformationService {

	public ContactInformationResult retrieveContactInformation(ContactInformationContract contract)
			throws Exception;

}
