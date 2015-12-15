package com.lexmark.service.api;

import com.lexmark.contract.ActivityDetailContract;
import com.lexmark.contract.ActivityListContract;
import com.lexmark.contract.OfflineModeAttachmentContract;
import com.lexmark.result.ActivityDetailResult;
import com.lexmark.result.ActivityListResult;

public interface PartnerHardwareInstallDebriefOfflineModeService {
	
	public ActivityListResult retrieveOfflineModeActivitiesList(ActivityListContract contract) throws Exception;
	public ActivityListResult retrieveServiceRequestOfflineModeActivitiesList(ActivityListContract contract) throws Exception;

	public ActivityDetailResult retrieveOfflineModeActivityDetails(ActivityDetailContract contract) throws Exception;
	
	public void generateInstallationDoc(OfflineModeAttachmentContract contract) throws Exception;
	
}
