package com.lexmark.service.impl.real.partnerHardwareInstallDebriefOfflineModeService;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.aspectj.util.LangUtil;

import com.amind.common.service.SiebelBusinessServiceProxy;
import com.amind.data.service.ActionEvent;
import com.amind.data.service.ExecutionMode;
import com.amind.data.service.IDataManager;
import com.amind.data.service.QueryObject;
import com.amind.session.Session;
import com.lexmark.contract.ActivityListContract;
import com.lexmark.domain.Activity;
import com.lexmark.service.impl.real.domain.OfflineModeRequestAttachmentDO;
import com.lexmark.service.impl.real.domain.PartnerActivityBase;
import com.lexmark.service.impl.real.domain.PartnerOfflineModeActivityDo;
import com.lexmark.service.impl.real.domain.ServiceRequestPartnerOfflineModeActivityDo;
import com.lexmark.service.impl.real.partnerActivityService.AmindPartnerActivitySearchSpecUtil;
import com.lexmark.service.impl.real.util.AmindServiceUtil;

public class AmindPartnerHardwareInstallDebriefOfflineModeActivitiesListService {
	
	private Session session;
	private Session totalCountSession;
	private ActivityListContract contract;
	private Map<String, String> fieldMap;
	private String searchExpression;
	private QueryObject criteria;
	private String childSearchExpression;

	public AmindPartnerHardwareInstallDebriefOfflineModeActivitiesListService(ActivityListContract contract, Map<String, String> fieldMap) {
		this.contract = contract;
		this.fieldMap = fieldMap;
	}
	
	public void checkRequiredFields() {
		if(contract.getSessionHandle() != null && StringUtils.isEmpty(contract.getSessionHandle().toString())) {
			throw new IllegalArgumentException("Session Handle can not be empty or null");
		}
		
		if(StringUtils.isEmpty(contract.getStatus())) {
			throw new IllegalArgumentException("Status can not be empty or null");
		}

		if(StringUtils.isEmpty(contract.getQueryType())) {
			throw new IllegalArgumentException("Query Type can not be empty or null");
		}
		
		if(StringUtils.isEmpty(contract.getServiceRequestType())) {
			throw new IllegalArgumentException("Request Type can not be empty or null");
		}
		
		if(contract.isEmployeeFlag()) {
			if(StringUtils.isEmpty(contract.getEmployeeId())) {
				throw new IllegalArgumentException("Employee Flag is true so Employee Id can not be empty or null");
			}
		} else {
			if(StringUtils.isEmpty(contract.getMdmId()) ) {
				throw new IllegalArgumentException("Employee Flag is false so MDM Id can not be empty or null");
			}
			if(StringUtils.isEmpty(contract.getMdmLevel()) ) {
				throw new IllegalArgumentException("Employee Flag is false so MDM Level can not be empty or null");
			}
		}
	}
	
	public void buildSearchExpression() throws Exception {
		IDataManager dataManager = session.getDataManager();
		StringBuilder builder = new StringBuilder();
		builder.append(AmindPartnerActivitySearchSpecUtil.buildActivityListSearchSpec(contract, dataManager, fieldMap, true));
		builder.append(overrideOfferingFilter());
		searchExpression = builder.toString();
		
		childSearchExpression = "[ActivityFileExt]='URL' AND [ActivityFileName] LIKE 'Install_Debrief*' AND [ActivityComments]='Updated URL from Modus.'";
		
		buildQueryObject();
	}
	
	private void buildQueryObject() {
//		criteria = new QueryObject(PartnerOfflineModeActivityDo.class,ActionEvent.QUERY);
//		criteria.setExecutionMode(ExecutionMode.BiDirectional);
//		criteria.setNumRows(contract.getIncrement());
//		criteria.setStartRowIndex(contract.getStartRecordNumber());
//		if (contract.getSortCriteria() != null && !contract.getSortCriteria().isEmpty()) {
//			criteria.setSortString(AmindServiceUtil.buildSortString(contract.getSortCriteria(), fieldMap));
//		}
//		criteria.addComponentSearchSpec(PartnerOfflineModeActivityDo.class, searchExpression);
		
		criteria = new QueryObject(PartnerOfflineModeActivityDo.class,ActionEvent.QUERY);
		criteria.setExecutionMode(ExecutionMode.ForwardOnly);
		criteria.setNumRows(-1);
		criteria.setStartRowIndex(0);
		if (contract.getSortCriteria() != null && !contract.getSortCriteria().isEmpty()) {
			criteria.setSortString(AmindServiceUtil.buildSortString(contract.getSortCriteria(), fieldMap));
		}
		criteria.addComponentSearchSpec(PartnerOfflineModeActivityDo.class, searchExpression);
		
		criteria.addComponentSearchSpec(OfflineModeRequestAttachmentDO.class, childSearchExpression);
	}
	
	public void buildSearchExpressionServiceRequest() throws Exception {
		IDataManager dataManager = session.getDataManager();
		StringBuilder builder = new StringBuilder();
		builder.append(AmindPartnerActivitySearchSpecUtil.buildActivityListSearchSpecServiceRequest(contract, dataManager, fieldMap, true));
		builder.append(overrideOfferingFilterServiceRequest());
		searchExpression = builder.toString();
		
		childSearchExpression = "[ActivityFileExt]='URL' AND [ActivityFileName] LIKE 'Install_Debrief*' AND [ActivityComments]='Updated URL from Modus.'";
		
		buildQueryObjectServiceRequest();
	}
	
	
	
	private void buildQueryObjectServiceRequest() {
//		criteria = new QueryObject(PartnerOfflineModeActivityDo.class,ActionEvent.QUERY);
//		criteria.setExecutionMode(ExecutionMode.BiDirectional);
//		criteria.setNumRows(contract.getIncrement());
//		criteria.setStartRowIndex(contract.getStartRecordNumber());
//		if (contract.getSortCriteria() != null && !contract.getSortCriteria().isEmpty()) {
//			criteria.setSortString(AmindServiceUtil.buildSortString(contract.getSortCriteria(), fieldMap));
//		}
//		criteria.addComponentSearchSpec(PartnerOfflineModeActivityDo.class, searchExpression);
		
		criteria = new QueryObject(ServiceRequestPartnerOfflineModeActivityDo.class,ActionEvent.QUERY);
		criteria.setExecutionMode(ExecutionMode.ForwardOnly);
		criteria.setNumRows(-1);
		criteria.setStartRowIndex(0);
		if (contract.getSortCriteria() != null && !contract.getSortCriteria().isEmpty()) {
			criteria.setSortString(AmindServiceUtil.buildSortString(contract.getSortCriteria(), fieldMap));
		}
		criteria.addComponentSearchSpec(ServiceRequestPartnerOfflineModeActivityDo.class, searchExpression);
		
//		/criteria.addComponentSearchSpec(ServiceRequestPartnerOfflineModeActivityDo.class, childSearchExpression);
	}


	@SuppressWarnings("unchecked")
	public List<Activity> queryAndGetActivitiesList() {
		List<PartnerActivityBase> activitiesList = null;
//		if(contract.isNewQueryIndicator()) {
//			activityList = session.getDataManager().query(criteria);
//		} else {
//			activityList = session.getDataManager().queryNext(criteria);
//		}
		
		activitiesList = session.getDataManager().query(criteria);
		
		if(LangUtil.isEmpty(activitiesList)) {
			return new ArrayList<Activity>();
		}
		
		Set<PartnerActivityBase> activitiesSet = new LinkedHashSet<PartnerActivityBase>(activitiesList);
		List<PartnerActivityBase> filteredActivitiesList = new ArrayList<PartnerActivityBase>(activitiesSet);
		
		return AmindPartnerHardwareInstallDebriefOfflineModeServiceUtil.convertOfflineModeActivitiesDOsListToActivitiesList(filteredActivitiesList);
	}
	
	public int processTotalCount() {
		SiebelBusinessServiceProxy businessServiceProxy = totalCountSession.getSiebelBusServiceProxy();
		return AmindServiceUtil.getTotalCount("LXK SD Service Request_Offline", "LXK SD Service Request Portal Offline", searchExpression, businessServiceProxy);
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

	public Session getTotalCountSession() {
		if (totalCountSession == null) {
			throw new IllegalStateException("total count session has not been set!");
		}
		return totalCountSession;
	}

	public void setTotalCountSession(Session totalCountSession) {
		if (totalCountSession == null) {
			throw new IllegalStateException("total count session can not be null!");
		}
		this.totalCountSession = totalCountSession;
	}

	public boolean isRecordExist() {
//		SiebelBusinessServiceProxy businessServiceProxy = session.getSiebelBusServiceProxy();
		SiebelBusinessServiceProxy businessServiceProxy = session.getSiebelBusServiceProxy();
		boolean  exists = AmindServiceUtil.isRecordExist(
				"LXK SW Partner Portal BO",
				"LXK SW Action - Partner Portal",
				searchExpression, businessServiceProxy);
		return exists;
	}
	public boolean isRecordExistSR() {
//		SiebelBusinessServiceProxy businessServiceProxy = session.getSiebelBusServiceProxy();
		SiebelBusinessServiceProxy businessServiceProxy = session.getSiebelBusServiceProxy();
		boolean  exists = AmindServiceUtil.isRecordExist(
				"LXK SD Service Request_Offline",
				"LXK SD Service Request Portal Offline",
				searchExpression, businessServiceProxy);
		return exists;
	}
	private static String overrideOfferingFilter() {
		StringBuilder sb = new StringBuilder();
		sb.append(" AND (");
		sb.append("[LXK MPS Override Offering] <> 'Consumables Order and Activity'");
		sb.append(" AND [LXK MPS Override Offering] <> 'Consumables Order'");
		sb.append(" AND [LXK MPS Override Offering] <> 'Consumables Activity'");
		sb.append(" OR [LXK MPS Override Offering] IS NULL ");
		sb.append(")");
		return sb.toString();
	}
	private static String overrideOfferingFilterServiceRequest() {
		StringBuilder sb = new StringBuilder();
		sb.append(" AND (EXISTS(");
		sb.append("[LXK MPS Override Offering] <> 'Consumables Order and Activity'");
		sb.append(" AND [LXK MPS Override Offering] <> 'Consumables Order'");
		sb.append(" AND [LXK MPS Override Offering] <> 'Consumables Activity')");
		sb.append(" OR EXISTS([LXK MPS Override Offering] IS NULL ");
		sb.append("))");
		return sb.toString();
	}
}
