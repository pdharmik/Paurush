package com.lexmark.service.impl.mock;

import java.util.ArrayList;
import java.util.List;

import com.lexmark.constants.LexmarkConstants;
import com.lexmark.contract.DAUserListContract;
import com.lexmark.contract.RetrieveServicesUserContract;
import com.lexmark.contract.SaveServicesUserContract;
import com.lexmark.domain.ServicesUser;
import com.lexmark.result.DAUserListResult;
import com.lexmark.result.RetrieveServicesUserResult;
import com.lexmark.result.SaveServicesUserResult;
import com.lexmark.service.api.UserAdminService;

public class UserAdminServiceImpl implements UserAdminService {

	public DAUserListResult retrieveDAUserList(DAUserListContract contract)
			throws Exception {
		DAUserListResult result = new DAUserListResult();
		List<String> userNumberList = new ArrayList<String>();
		if (contract.getOperId().equals("100002")) {
			userNumberList.add("user1");
			userNumberList.add("user2");
			userNumberList.add("user3");
		} else {
			userNumberList.add("user1");
		}
		result.setUserNumberList(userNumberList);
		List<String> mdmIdList = new ArrayList<String>();
		if (contract.getOperId().equals("10001")) {
			mdmIdList.add("100001");
			mdmIdList.add("100002");
			mdmIdList.add("100003");
			result.setMdmLevel(LexmarkConstants.MDM_LEVEL_ACCOUNT);
		} else {
			result.setMdmLevel(LexmarkConstants.MDM_LEVEL_DOMESTIC);
			mdmIdList.add("100001");
		}
		result.setMdmIdList(mdmIdList);
		return result;
	}

	public SaveServicesUserResult saveServicesUser(
			SaveServicesUserContract contract) throws Exception {
		SaveServicesUserResult result = new SaveServicesUserResult();
		result.setResult(Boolean.TRUE);
		return result;
	}

	public RetrieveServicesUserResult retrieveServicesUser(
			RetrieveServicesUserContract contract) throws Exception {
		RetrieveServicesUserResult result = new RetrieveServicesUserResult();
		String userNumber = contract.getUserNumber();
		ServicesUser user = new ServicesUser();
		user.setUserNumber(userNumber);
		user.setChlNodeId("102001");
		result.setServicesUser(user);
		return result;
	}

}
