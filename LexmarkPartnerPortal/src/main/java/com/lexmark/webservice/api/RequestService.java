package com.lexmark.webservice.api;

import com.lexmark.contract.ActivityDebriefSubmitContract;
import com.lexmark.contract.ChildSRContract;
import com.lexmark.contract.RequestUpdateContract;
import com.lexmark.result.ActivityDebriefSubmitResult;
import com.lexmark.result.CreateChildSRResult;
import com.lexmark.result.RequestUpdateResult;

public interface RequestService {
	public RequestUpdateResult updateRequest(RequestUpdateContract contract) throws Exception;
	public ActivityDebriefSubmitResult submitActivityDebrief(ActivityDebriefSubmitContract contract) throws Exception;
	public CreateChildSRResult createChildSR(ChildSRContract contract) throws Exception;

}
