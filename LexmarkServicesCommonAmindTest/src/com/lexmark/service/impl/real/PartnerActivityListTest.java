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

import com.amind.session.Session;
import com.lexmark.contract.ActivityListContract;
import com.lexmark.result.ActivityListResult;
import com.lexmark.service.api.CrmSessionHandle;
import com.lexmark.service.impl.real.AmindCrmSessionHandle;
import com.lexmark.service.impl.real.AmindGlobalService;
import com.lexmark.service.impl.real.AmindPartnerRequestsService;
import com.lexmark.service.impl.real.enums.ActivityStatus;
import com.lexmark.service.impl.real.enums.QueryType;
import com.lexmark.service.impl.real.enums.RequestType;

public class PartnerActivityListTest {
	
	protected static final Log logger = 
		LogFactory.getLog(PartnerActivityListTest.class);
	AmindPartnerRequestsService service;
	ActivityListContract contract;
	AmindGlobalService globalService;
	CrmSessionHandle handle;
	
	
     public void setUp() {
		service = new AmindPartnerRequestsService();
		contract = new ActivityListContract();
		globalService = new AmindGlobalService();
		globalService.setStatefulSessionFactory(TestSessionFactories.newStatefulSessionFactory());
		handle = globalService.initCrmSessionHandle(null);
		
     }
	 
	
	 public void tearDown() {
		if (contract.getSessionHandle() != null) {
					try {
						Session session = ((AmindCrmSessionHandle) contract.getSessionHandle()).acquire();
						if (session != null) {
							session.release();
						}
						((AmindCrmSessionHandle) contract.getSessionHandle()).release();
					}
					catch (InterruptedException e) {
						//squash
					}
				}
			}
	
	@Test
	public void testRetrieveActivityList()  throws Exception {
		
		testRetrieveActivityInputParameters();
		ActivityListResult activityResult = service.retrieveActivityList(contract);
		testRetrieveActivityOutputParameters(activityResult);

	}

	
	public void testRetrieveActivityInputParameters() throws InterruptedException, ParseException {
		
		setUp();

		String mdmId = "50901";
		String mdmLevel = "Legal";
		String status = ActivityStatus.Open.toString();
		String employeeId = "1-2SFOPRF";
		Map<String,Object> filterMap = new HashMap<String,Object>();
		DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.SHORT, Locale.US);
		String date = "11/3/2011";
		Date startDate = dateFormat.parse(date);
		filterMap.put("Activity.activityDate.startDate",startDate);
		String endStringdate = "11/5/2011";
		Date endDate = dateFormat.parse(endStringdate);
		filterMap.put("Activity.activityDate.endDate",endDate);
		Map<String,Object> searchMap = null;
		Map<String,Object> sortMap = null;
		int startRecordNumber = 0;
		int increment = 50;
		CrmSessionHandle sessionHandle = handle;
		boolean newQueryIndicator = true;
		boolean lexmarkEmployeeFlag = false;
		String queryType = QueryType.All.toString();
		String requestType = RequestType.All.toString();
		
		contract.setMdmId(mdmId);
		contract.setMdmLevel(mdmLevel);
		contract.setStatus(status);
		contract.setServiceRequestType(requestType);
		contract.setEmployeeId(employeeId);
		//1-6055885711
		filterMap.put("Activity.serviceRequest.serviceRequestNumber", "1-6085887836");
		contract.setFilterCriteria(filterMap);
		contract.setSearchCriteria(searchMap);
		contract.setSortCriteria(sortMap);
		contract.setStartRecordNumber(startRecordNumber);
		contract.setIncrement(increment);
		contract.setSessionHandle(sessionHandle);
		contract.setNewQueryIndicator(newQueryIndicator);
		contract.setEmployeeFlag(lexmarkEmployeeFlag);
		contract.setQueryType(queryType);
		
	}
	
	
	public void testRetrieveActivityOutputParameters(ActivityListResult activityResult) {
		Assert.assertNotNull(activityResult);
		Assert.assertNotNull(activityResult.getActivityList());
		Assert.assertNotNull(activityResult.getTotalcount());
		logger.debug("Total count :" + activityResult.getTotalcount());
		logger.debug("Activity List:" + activityResult.getActivityList());
		tearDown();
		
	}
	
}


