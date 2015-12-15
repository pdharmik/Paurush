package com.lexmark.service.impl.real.partnerHardwareInstallDebriefOfflineModeService;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import com.lexmark.contract.ActivityListContract;
import com.lexmark.domain.Activity;
import com.lexmark.service.impl.real.domain.PartnerActivityBase;
import com.lexmark.service.impl.real.partnerActivityService.AmindPartnerActivityServiceUtil;
import com.lexmark.util.LangUtil;

public class AmindPartnerHardwareInstallDebriefOfflineModeServiceUtil {

	public static List<Activity> convertOfflineModeActivitiesDOsListToActivitiesList(List<PartnerActivityBase> activitiesDOsList) {

		List<Activity> activities = new ArrayList<Activity>(0);

		if (LangUtil.isEmpty(activitiesDOsList)) {
			return activities;
		}
		return AmindPartnerActivityServiceUtil.convertActivityDOListToActivityList(activitiesDOsList, null);
	}

	public static Activity convertOfflineModeActivityDOToActivity(PartnerActivityBase activityDO, boolean debriefFlag, String pageName) throws ParseException {

		Activity activity = new Activity();

		if (activityDO == null) {
			return activity;
		}

		activity = AmindPartnerActivityServiceUtil.convertActivityDetailDoToActivity(activityDO, debriefFlag, pageName, true);
		
		return activity;
	}
	
}
