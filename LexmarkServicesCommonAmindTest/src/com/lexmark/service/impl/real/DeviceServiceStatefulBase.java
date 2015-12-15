package com.lexmark.service.impl.real;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.AfterClass;
import org.junit.BeforeClass;

import util.TestSessionFactories;

import com.amind.session.Session;
import com.lexmark.service.api.CrmSessionHandle;
import com.lexmark.service.api.DeviceService;

public abstract class DeviceServiceStatefulBase {
	protected static final Log logger = LogFactory.getLog(DeviceServiceStatefulBase.class);
	protected static DeviceService service;
	protected static CrmSessionHandle handle;

	@BeforeClass
	public static void setUpBeforeClass() {
		AmindGlobalService globalService = new AmindGlobalService();
		globalService.setStatefulSessionFactory(TestSessionFactories.newStatefulSessionFactory());
		service = new AmindContractedDeviceService();
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

}
