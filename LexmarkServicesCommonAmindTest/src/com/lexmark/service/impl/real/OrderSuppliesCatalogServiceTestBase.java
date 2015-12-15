package com.lexmark.service.impl.real;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.AfterClass;
import org.junit.BeforeClass;

import util.TestSessionFactories;

import com.amind.session.Session;
import com.amind.session.SessionFactory;
import com.amind.session.StatelessSessionFactory;
import com.lexmark.service.api.CrmSessionHandle;
import com.lexmark.service.api.OrderSuppliesCatalogService;

public abstract class OrderSuppliesCatalogServiceTestBase {
	protected static final Log logger = LogFactory.getLog(OrderSuppliesCatalogServiceTestBase.class);
	
	protected static SessionFactory sessionFactory;
	protected static OrderSuppliesCatalogService service;
	static CrmSessionHandle handle;
	static AmindGlobalService globalService;
	@BeforeClass
	public static void setUpBeforeClass() {
		globalService = new AmindGlobalService();
		sessionFactory = TestSessionFactories.newStatelessSessionFactory();
		service = new AmindOrderSuppliesCatalogService();
		((AmindOrderSuppliesCatalogService) service).setStatelessSessionFactory(sessionFactory);
		handle = globalService.initCrmSessionHandle(null);
	}

	@AfterClass
	public static void tearDownAfterClass() {
		((StatelessSessionFactory) sessionFactory).releaseAllStatelessSessions();
		if (handle != null) {
			try {
				Session session = ((AmindCrmSessionHandle) handle).acquire();
				if (session != null) {
					session.release();
				}
				((AmindCrmSessionHandle) handle).release();
			}
			catch (InterruptedException e) {
				//squash
			}
		}
	}
}

