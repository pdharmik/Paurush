package com.lexmark.service.impl.real;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.lexmark.contract.ActivityListContract;
import com.lexmark.domain.Activity;
import com.lexmark.domain.Asset;
import com.lexmark.domain.ServiceRequest;
import com.lexmark.result.ActivityListResult;
import com.lexmark.util.LangUtil;

public class AmindPartnerHardwareInstallDebriefOfflineModeServiceTest extends AmindServiceTest {

	AmindPartnerHardwareInstallDebriefOfflineModeService service = null;
	
	@Before
	public void setUp() throws Exception {
		service = new AmindPartnerHardwareInstallDebriefOfflineModeService();
	}

	@Test
	public void testRetrieveOfflineModeActivitiesList() throws Exception {
		ActivityListContract contract = new ActivityListContract();
		contract.setStatus("Open");
		contract.setMdmLevel("Legal");
		contract.setMdmId("30145");
		contract.setEmployeeFlag(false);
		contract.setServiceRequestType("Fleet Management");
		contract.setCountFlag(false);
		contract.setEmployeeId("1-73O5NHR");
		contract.setQueryType("All");
		contract.setChangeManagementFlag(true);
		contract.setOfflineInstallDebrief(true);
		contract.setNewQueryIndicator(true);
		contract.setStartRecordNumber(0);
		contract.setIncrement(40);
		contract.setSessionHandle(crmSessionHandle);

		Map<String, Object> filterCriteria = new HashMap<String, Object>();
		filterCriteria.put("Activity.activityDate.endDate",LangUtil.convertStringToGMTDate("02/12/2014 18:30:00"));
		filterCriteria.put("Activity.activityDate.startDate", LangUtil.convertStringToGMTDate("01/12/2013 18:30:00"));
		contract.setFilterCriteria(filterCriteria);

		Map<String, Object> sortCriteria = new HashMap<String, Object>();
		sortCriteria.put("Activity.serviceRequest.serviceRequestNumber", "DESCENDING");
		contract.setSortCriteria(sortCriteria);
		
		ActivityListResult result = service.retrieveOfflineModeActivitiesList(contract);

		List<Activity> activities = result.getActivityList();
		
		System.out.println("Size: " + activities.size());
		System.out.println("Total count: " + result.getTotalcount());
		
		for (Activity activity : activities) {
			System.out.println("Service request number: " + activity.getServiceRequest().getServiceRequestNumber());
			System.out.println("Comments " + activity.getOfflineModeAttachment().getComments());
			System.out.println("File name: " + activity.getOfflineModeAttachment().getFileName());
			System.out.println("----------------");
		}

	}
	
	
	@Test
	public void testRetrieveOfflineModeActivitiesList_defect10088() throws Exception {
		ActivityListContract contract = new ActivityListContract();
		contract.setStatus("Open");
		contract.setMdmLevel("Legal");
		contract.setMdmId("30145");
		contract.setEmployeeFlag(false);
		contract.setServiceRequestType("Fleet Management");
		contract.setCountFlag(false);
		contract.setEmployeeId("1-73O5NHR");
		contract.setQueryType("All");
		contract.setChangeManagementFlag(true);
		contract.setOfflineInstallDebrief(true);
		contract.setNewQueryIndicator(true);
		contract.setStartRecordNumber(0);
		contract.setIncrement(40);
		contract.setSessionHandle(crmSessionHandle);

		Map<String, Object> filterCriteria = new HashMap<String, Object>();
		filterCriteria.put("Activity.activityDate.endDate",LangUtil.convertStringToGMTDate("02/12/2014 18:30:00"));
		filterCriteria.put("Activity.activityDate.startDate", LangUtil.convertStringToGMTDate("01/12/2013 18:30:00"));
		contract.setFilterCriteria(filterCriteria);

		Map<String, Object> sortCriteria = new HashMap<String, Object>();
		sortCriteria.put("Activity.serviceRequest.serviceRequestNumber", "DESCENDING");
		contract.setSortCriteria(sortCriteria);
		
		ActivityListResult result = service.retrieveOfflineModeActivitiesList(contract);
		
//		ActivityListResult result = new AmindPartnerRequestsService().retrieveActivityList(contract);
		
		List<Activity> activities = result.getActivityList();
		
		System.out.println("Size: " + activities.size());
		System.out.println("Total count: " + result.getTotalcount());
		
		for (Activity activity : activities) {
			ServiceRequest serviceRequest = activity.getServiceRequest();
			Asset asset = serviceRequest.getAsset();
			System.out.println("Model number: " + asset.getProductModelNumber());
			
//			ServiceRequest serviceRequest = activity.getServiceRequest();
//			Asset asset = serviceRequest.getAsset();
//			System.out.println("Model number: " + asset.getPartList().get(0).getModel());
		}
	}
	
	
	@Test
	public void testRetrieveActivityList_defect490() throws Exception {

		ActivityListContract contract = new ActivityListContract();
		contract.setStatus("Open");
		contract.setMdmLevel("Global");
		contract.setMdmId("422524798");
		contract.setCountFlag(false);
		contract.setEmployeeId("1-7ZLX8AH");
		contract.setServiceRequestType("Fleet Management");
		contract.setEmployeeFlag(false);
		contract.setQueryType("All");
		contract.setChangeManagementFlag(true);
		contract.setOfflineInstallDebrief(true);
		contract.setNewQueryIndicator(true);
		contract.setIncrement(40);
		contract.setStartRecordNumber(0);
		contract.setSessionHandle(crmSessionHandle);

		Map<String, Object> filterCriteria = new HashMap<String, Object>();
		filterCriteria.put("Activity.activityDate.endDate",
				LangUtil.convertStringToGMTDate("03/01/2014 05:00:00"));
		filterCriteria.put("Activity.activityDate.startDate",
				LangUtil.convertStringToGMTDate("01/29/2014 05:00:00"));
		contract.setFilterCriteria(filterCriteria);
		
//		Map<String, Object> sortCriteria = new HashMap<String, Object>();
//		sortCriteria.put("Activity.serviceRequest.serviceRequestNumber",
//				"DESCENDING");
//		contract.setSortCriteria(sortCriteria);
		
		Map<String, Object> sortCriteria = new HashMap<String, Object>();
		sortCriteria.put("Activity.activityType", "DESCENDING");
		contract.setSortCriteria(sortCriteria);

		ActivityListResult result = service.retrieveOfflineModeActivitiesList(contract);

		List<Activity> activities = result.getActivityList();
		int totalCount = result.getTotalcount();

		System.out.println("Size: " + activities.size());
		System.out.println("Total count: " + totalCount);
		
		for (Activity activity : activities) {
//			System.out.println("SR #: " + activity.getServiceRequest().getServiceRequestNumber());
			System.out.println("Activity type: " + activity.getActivityType());
		}
		
		System.out.println("END");

	}

	@Test
	public void testRetrieveOfflineModeActivityDetails() {
	}

	@Test
	public void testGenerateInstallationDoc() {
	}

	
	
	@Test
	public void testRetrieveActivityList_OfflineDebrief() throws Exception {

		ActivityListContract contract = new ActivityListContract();
		contract.setStatus("Open");
		contract.setMdmLevel("Global");
		contract.setMdmId("966331451");
		contract.setCountFlag(false);
		contract.setEmployeeId("1-73O5NHR");
		contract.setServiceRequestType("Fleet Management");
		contract.setEmployeeFlag(false);
		contract.setQueryType("All");
		contract.setChangeManagementFlag(true);
		contract.setOfflineInstallDebrief(true);
		contract.setNewQueryIndicator(true);
		contract.setIncrement(40);
		contract.setStartRecordNumber(0);
		contract.setSessionHandle(crmSessionHandle);

		Map<String, Object> filterCriteria = new HashMap<String, Object>();
		filterCriteria.put("Activity.activityDate.endDate",
				LangUtil.convertStringToISTDate("03/21/2014 18:30:00"));
		filterCriteria.put("Activity.activityDate.startDate",
				LangUtil.convertStringToISTDate("02/18/2014 18:30:00"));
//		filterCriteria.put("Activity.serviceRequest.serviceRequestNumber", "1-18371677711");
		contract.setFilterCriteria(filterCriteria);

		Map<String, Object> sortCriteria = new HashMap<String, Object>();
		sortCriteria.put("Activity.serviceRequest.serviceRequestNumber",
				"DESCENDING");
		contract.setSortCriteria(sortCriteria);

		ActivityListResult result = service.retrieveOfflineModeActivitiesList(contract);

		List<Activity> activities = result.getActivityList();
		int totalCount = result.getTotalcount();

		System.out.println("Size: " + activities.size());
		System.out.println("Total count: " + totalCount);
		
		System.out.println("------------------");
		for (Activity activity : activities) {
			ServiceRequest sr = activity.getServiceRequest();
			System.out.println("Status type: " + sr.getStatusType());
			System.out.println("Activty type: " + activity.getActivityStatus());
			System.out.println("------------------");
		}
		
		System.out.println("END");

	}
	
	
	@Test
	public void testRetrieveActivityList_OfflineDebrief_2() throws Exception {

		ActivityListContract contract = new ActivityListContract();
		contract.setStatus("Open");
		contract.setMdmLevel("Global");
		contract.setMdmId("422524798");
		contract.setCountFlag(false);
		contract.setEmployeeId("1-73O5NHR");
		contract.setServiceRequestType("Fleet Management");
		contract.setEmployeeFlag(false);
		contract.setQueryType("All");
		contract.setChangeManagementFlag(true);
		contract.setOfflineInstallDebrief(true);
		contract.setNewQueryIndicator(true);
		contract.setIncrement(40);
		contract.setStartRecordNumber(0);
		contract.setSessionHandle(crmSessionHandle);

		Map<String, Object> filterCriteria = new HashMap<String, Object>();
		filterCriteria.put("Activity.serviceRequest.statusType", "Completed");
		filterCriteria.put("Activity.activityDate.endDate",
				LangUtil.convertStringToISTDate("03/25/2014 18:30:00"));
		filterCriteria.put("Activity.activityDate.startDate",
				LangUtil.convertStringToISTDate("02/22/2014 18:30:00"));
		contract.setFilterCriteria(filterCriteria);

		Map<String, Object> sortCriteria = new HashMap<String, Object>();
		sortCriteria.put("Activity.serviceRequest.serviceRequestNumber",
				"DESCENDING");
		contract.setSortCriteria(sortCriteria);

		ActivityListResult result = service.retrieveOfflineModeActivitiesList(contract);

		List<Activity> activities = result.getActivityList();
		int totalCount = result.getTotalcount();

		System.out.println("Size: " + activities.size());
		System.out.println("Total count: " + totalCount);
		
		System.out.println("------------------");
		for (Activity activity : activities) {
			ServiceRequest sr = activity.getServiceRequest();
			System.out.println("Status type: " + sr.getStatusType());
			System.out.println("Activty type: " + activity.getActivityStatus());
			System.out.println("------------------");
		}
		
		System.out.println("END");

	}
	
	@Test
	public void testRetrieveActivityList_PerformanceIssue() throws Exception {

		ActivityListContract contract = new ActivityListContract();
		contract.setStatus("Open");
		contract.setMdmLevel("Global");
		contract.setMdmId("966331451");
		contract.setCountFlag(false);
		contract.setEmployeeId("1-73O5NHR");
		contract.setServiceRequestType("Fleet Management");
		contract.setEmployeeFlag(false);
		contract.setQueryType("All");
		contract.setChangeManagementFlag(true);
		contract.setOfflineInstallDebrief(true);
		contract.setNewQueryIndicator(true);
		contract.setIncrement(40);
		contract.setStartRecordNumber(0);
		contract.setSessionHandle(crmSessionHandle);

		Map<String, Object> filterCriteria = new HashMap<String, Object>();
		filterCriteria.put("Activity.activityDate.endDate",
				LangUtil.convertStringToGMTDate("04/11/2014 04:00:00"));
		filterCriteria.put("Activity.activityDate.startDate",
				LangUtil.convertStringToGMTDate("02/03/2014 04:00:00"));
		contract.setFilterCriteria(filterCriteria);
		
		Map<String, Object> sortCriteria = new HashMap<String, Object>();
		sortCriteria.put("Activity.serviceRequest.serviceRequestNumber","DESCENDING");
		contract.setSortCriteria(sortCriteria);
		
		ActivityListResult result = service.retrieveOfflineModeActivitiesList(contract);

		List<Activity> activities = result.getActivityList();
		int totalCount = result.getTotalcount();

		System.out.println("Size: " + activities.size());
		System.out.println("Total count: " + totalCount);
		
		System.out.println("END");

	}
}
