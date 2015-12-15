package com.lexmark.service.api;

import com.lexmark.contract.DAUserListContract;
import com.lexmark.contract.SaveServicesUserContract;
import com.lexmark.result.DAUserListResult;
import com.lexmark.result.SaveServicesUserResult;

public interface UserAdminService {

	public DAUserListResult retrieveDAUserList(DAUserListContract contract)
			throws Exception;
	public SaveServicesUserResult saveServicesUser(SaveServicesUserContract contract)
			throws Exception;
}
