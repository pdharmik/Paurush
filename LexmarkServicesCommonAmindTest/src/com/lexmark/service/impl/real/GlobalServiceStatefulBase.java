package com.lexmark.service.impl.real;

import java.lang.reflect.Field;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.AfterClass;
import org.junit.BeforeClass;

import util.TestSessionFactories;

import com.amind.session.Session;
import com.lexmark.domain.GenericAddress;
import com.lexmark.service.api.CrmSessionHandle;


public class GlobalServiceStatefulBase {

	
	protected static final Log logger = LogFactory.getLog(ServiceRequestListSearchTest.class);
	protected static AmindGlobalService globalService;
	protected static CrmSessionHandle handle;

	@BeforeClass
	public static void setUpBeforeClass() {
		globalService = new AmindGlobalService();
		globalService.setStatefulSessionFactory(TestSessionFactories.newStatefulSessionFactory());
		handle = globalService.initCrmSessionHandle(null);
	}

	@AfterClass
	public static void tearDownAfterClass() {
		if (handle != null) {
			try {
				Session session = ((AmindCrmSessionHandle) handle).acquire();
				if (session != null) {
					session.release();
				}
				((AmindCrmSessionHandle) handle).release();
			} catch (InterruptedException e) {
				// squash
			}
		}
	}
	
	protected void debugAddress(GenericAddress address) throws IllegalArgumentException, IllegalAccessException {
		StringBuilder debugString = new StringBuilder();
		for (Field field : address.getClass().getDeclaredFields()) {
			field.setAccessible(true);
			String fieldName = field.getName();
			if ("serialVersionUID".equals(fieldName)) {
				continue;
			}
			debugString.append(field.getName());
			debugString.append(" = ");
			debugString.append(field.get(address));
			debugString.append("; ");
		}
		logger.debug(debugString);
	}


}
