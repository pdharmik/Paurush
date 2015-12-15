package com.lexmark.service.impl.real.partnerHardwareInstallDebriefOfflineModeService;

import java.text.ParseException;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.amind.session.Session;
import com.lexmark.contract.ActivityDetailContract;
import com.lexmark.domain.Activity;
import com.lexmark.service.impl.real.domain.PartnerActivityBase;
import com.lexmark.service.impl.real.domain.PartnerActivityDetailDo;
import com.lexmark.util.LangUtil;

public class AmindPartnerHardwareInstallDebriefOfflineModeActivitiesDetailsService {

	private Session session;
	private ActivityDetailContract contract;
	private String searchExpression;
	
	public AmindPartnerHardwareInstallDebriefOfflineModeActivitiesDetailsService(ActivityDetailContract contract) {
		this.contract = contract;
	}

	public void checkRequiredFields() {
		if (StringUtils.isEmpty(contract.getActivityId())) {
			throw new IllegalArgumentException("Activity Id is null or empty");
		}
	}

	public void buildSearchExpression() {
		searchExpression =  "[activityId] = '" + contract.getActivityId() + "'";
	}

	@SuppressWarnings("unchecked")
	public Activity queryAndGetActivityDetails() throws ParseException {
		List<PartnerActivityBase> activityDetailList = session.getDataManager().queryBySearchExpression(PartnerActivityDetailDo.class, searchExpression);
		return AmindPartnerHardwareInstallDebriefOfflineModeServiceUtil.convertOfflineModeActivityDOToActivity(LangUtil.first(activityDetailList), contract.isDebriefFlag(), contract.getPageName());
	}
	
	public Session getSession() {
		if (session == null) {
			throw new IllegalStateException("session has not been set!");
		}
		return session;
	}

	public void setSession(Session session) {
		if (session == null) {
			throw new IllegalArgumentException("session can not be null!");
		}
		this.session = session;
	}
}
