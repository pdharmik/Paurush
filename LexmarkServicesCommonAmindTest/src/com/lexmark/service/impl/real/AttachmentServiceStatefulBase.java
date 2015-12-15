package com.lexmark.service.impl.real;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.AfterClass;
import org.junit.BeforeClass;

import util.TestSessionFactories;

import com.amind.session.Session;
import com.lexmark.service.api.AttachmentService;
import com.lexmark.service.api.CrmSessionHandle;

/**
 * @author pkozlov
 */
public abstract class AttachmentServiceStatefulBase {
	protected static final Log logger = LogFactory.getLog(AttachmentServiceStatefulBase.class);
	protected static CrmSessionHandle handle;
	protected static AttachmentService service;

	@BeforeClass
	public static void setUpBeforeClass() {
		AmindGlobalService globalService = new AmindGlobalService();
		globalService.setStatefulSessionFactory(TestSessionFactories.newStatefulSessionFactory());
		handle = globalService.initCrmSessionHandle(null);
		service = new AmindAttachmentService();
	}

	@AfterClass
	public static void tearDownAfterClass() {
		logger.debug("Releasing session...");
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
