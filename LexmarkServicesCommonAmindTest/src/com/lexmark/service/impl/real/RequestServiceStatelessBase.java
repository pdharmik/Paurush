package com.lexmark.service.impl.real;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.AfterClass;
import org.junit.BeforeClass;

import util.TestSessionFactories;

import com.amind.session.SessionFactory;
import com.amind.session.StatelessSessionFactory;
import com.lexmark.service.api.ServiceRequestService;

public abstract class RequestServiceStatelessBase extends AmindServiceTest {

	protected static final Log logger = LogFactory.getLog(RequestServiceStatelessBase.class);
	protected static ServiceRequestService service;
	protected static SessionFactory sessionFactory;

	@BeforeClass
	public static void setUpBeforeClass() {
		service = new AmindContractedServiceRequestService();
		sessionFactory = TestSessionFactories.newStatelessSessionFactory();
		((AmindContractedServiceRequestService) service).setSessionFactory(sessionFactory);
	}

	@AfterClass
	public static void tearDownAfterClass() {
		((StatelessSessionFactory) sessionFactory).releaseAllStatelessSessions();
	}

}
