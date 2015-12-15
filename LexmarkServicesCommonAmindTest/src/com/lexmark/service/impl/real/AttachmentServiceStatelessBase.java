package com.lexmark.service.impl.real;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.AfterClass;
import org.junit.BeforeClass;

import util.TestSessionFactories;

import com.amind.session.StatelessSessionFactory;

/**
 * @author pkozlov
 */
public abstract class AttachmentServiceStatelessBase extends AmindServiceTest {
	protected static final Log logger = LogFactory.getLog(AttachmentServiceStatelessBase.class);
	protected static StatelessSessionFactory statelessSessionFactory;
	protected static AmindAttachmentService service;

	@BeforeClass
	public static void setUpBeforeClass() {
		service = new AmindAttachmentService();
		statelessSessionFactory = TestSessionFactories.newStatelessSessionFactory();
		service.setStatelessSessionFactory(statelessSessionFactory);
	}

	@AfterClass
	public static void tearDownAfterClass() {
		statelessSessionFactory.releaseAllStatelessSessions();
	}
}
