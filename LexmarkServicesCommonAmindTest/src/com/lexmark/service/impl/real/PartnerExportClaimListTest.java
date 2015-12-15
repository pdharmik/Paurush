package com.lexmark.service.impl.real;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Assert;
import org.junit.Test;

import util.TestSessionFactories;

import com.amind.session.SessionFactory;
import com.amind.session.StatelessSessionFactory;
import com.lexmark.contract.ActivityListContract;
import com.lexmark.result.DownloadClaimListResult;
import com.lexmark.service.impl.real.AmindPartnerRequestsService;
import com.lexmark.service.impl.real.enums.ActivityStatus;
import com.lexmark.service.impl.real.enums.QueryType;
import com.lexmark.service.impl.real.enums.RequestType;

public class PartnerExportClaimListTest {
	
	protected static final Log logger = 
		LogFactory.getLog(PartnerExportClaimListTest.class);
	AmindPartnerRequestsService service;
	ActivityListContract contract;
	SessionFactory sessionFactory = TestSessionFactories.newStatelessSessionFactory();
	
	
     public void setUp() {
		service = new AmindPartnerRequestsService();
		contract = new ActivityListContract();
		service.setStatelessSessionFactory(sessionFactory);
		
     }
	 
	 public void tearDown() {
		 ((StatelessSessionFactory)sessionFactory).releaseAllStatelessSessions();
	 }
	
	@Test
	public void testRetrieveActivityList()  throws Exception {
		
		testRetrieveActivityInputParameters();
		DownloadClaimListResult activityResult = service.retrieveDownloadClaimList(contract);
		
		System.out.println("Size: " + activityResult.getActivityList().size());
		System.out.println("Total count: " + activityResult.getTotalcount());
//		testRetrieveActivityOutputParameters(activityResult);

	}

	
	public void testRetrieveActivityInputParameters() throws InterruptedException, ParseException {
		
		setUp();
		String mdmId = "50901";
		String mdmLevel = "Legal";
		String status = ActivityStatus.All.toString();
		String employeeId = "1-2QXRCC2";
		Map<String,Object> filterMap = new HashMap<String,Object>();
		DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.SHORT, Locale.US);
		String date = "09/8/2011";
		Date startDate = dateFormat.parse(date);
		filterMap.put("Activity.activityDate.startDate", startDate);
		String endStringdate = "09/14/2011";
		Date endDate = dateFormat.parse(endStringdate);
		filterMap.put("Activity.activityDate.endDate", endDate);
		Map<String,Object> searchMap = null;
		Map<String,Object> sortMap = null;
		int startRecordNumber = 0;
		int increment = 0;
		boolean newQueryIndicator = true;
		boolean lexmarkEmployeeFlag = true;
		String queryType = QueryType.All.toString();
		String requestType = RequestType.All.toString();
		
		contract.setMdmId(mdmId);
		contract.setMdmLevel(mdmLevel);
		contract.setStatus(status);
		contract.setServiceRequestType(requestType);
		contract.setEmployeeId(employeeId);
	//	filterMap.put("Activity.serviceRequest.serviceRequestNumber", "1-6004048396");
		contract.setFilterCriteria(filterMap);
		contract.setSearchCriteria(searchMap);
		contract.setSortCriteria(sortMap);
		contract.setStartRecordNumber(startRecordNumber);
		contract.setIncrement(increment);
		contract.setNewQueryIndicator(newQueryIndicator);
		contract.setEmployeeFlag(lexmarkEmployeeFlag);
		contract.setQueryType(queryType);
		
	}
	
	
	public void testRetrieveActivityOutputParameters(DownloadClaimListResult activityResult) {
		Assert.assertNotNull(activityResult);
		Assert.assertNotNull(activityResult.getActivityList());
		Assert.assertNotNull(activityResult.getTotalcount());
		logger.debug("Total count :" + activityResult.getTotalcount());
		tearDown();
		
	}
	
}


