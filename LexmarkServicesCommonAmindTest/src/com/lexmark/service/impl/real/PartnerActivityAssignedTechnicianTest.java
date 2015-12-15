package com.lexmark.service.impl.real;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import util.TestSessionFactories;

import com.amind.session.SessionFactory;
import com.amind.session.StatelessSessionFactory;
import com.lexmark.contract.AssignedTechnicianUpdateContract;
import com.lexmark.service.api.CrmSessionHandle;
import com.lexmark.service.impl.real.AmindGlobalService;
import com.lexmark.service.impl.real.AmindPartnerRequestsService;

public class PartnerActivityAssignedTechnicianTest {
	
	protected static final Log logger = 
		LogFactory.getLog(PartnerActivityAssignedTechnicianTest.class);
	AmindPartnerRequestsService service;
	AssignedTechnicianUpdateContract contract;
	SessionFactory sessionFactory = TestSessionFactories.newStatelessSessionFactory();
	
	 @Before
     public void setUp() {
		service = new AmindPartnerRequestsService();
		service.setStatelessSessionFactory(sessionFactory);
		contract = new AssignedTechnicianUpdateContract();
     }
	 
	 @After
	 public void tearDown() {
		 ((StatelessSessionFactory)sessionFactory).releaseAllStatelessSessions();
	 }
	
	@Test
	public void testActivityAssignedTechnician() throws Exception {
		
		testActivityAssignedTechnicianInputParameters();
		Assert.assertTrue(service.updateAssignedTechnician(contract).getResult());
	}
	
	
	public void testActivityAssignedTechnicianInputParameters() throws InterruptedException {
		
		AmindGlobalService globalService = new AmindGlobalService();
		globalService.setStatefulSessionFactory(TestSessionFactories.newStatefulSessionFactory());
		CrmSessionHandle handle = globalService.initCrmSessionHandle(null);

		String employeeId = "1-2Q9FF59";
		String activityId = "1-2PQOYZX";
	
		contract.setEmployeeId(employeeId);
		contract.setActivityId(activityId);
		
		
	}
	

	
}


