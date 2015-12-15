package com.lexmark.service.impl.real;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.AfterClass;
import org.junit.BeforeClass;

import util.TestSessionFactories;

import com.amind.session.SessionFactory;
import com.amind.session.StatelessSessionFactory;
import com.lexmark.service.api.ContactService;

/**
 * @author pkozlov
 */
public abstract class ContactServiceStatelessBase {
	protected static final Log logger = LogFactory.getLog(ContactServiceStatelessBase.class);
	protected static ContactService service;
	protected static SessionFactory sessionFactory;

	@BeforeClass
	public static void setUpBeforeClass() {
		sessionFactory = TestSessionFactories.newStatelessSessionFactory();
		service = new AmindContactService();
		((AmindContactService) service).setSessionFactory(sessionFactory);
	}

	@AfterClass
	public static void tearDownAfterClass() {
		((StatelessSessionFactory) sessionFactory).releaseAllStatelessSessions();
	}
}
