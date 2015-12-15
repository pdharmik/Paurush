package com.lexmark.service.impl.real;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.AfterClass;
import org.junit.BeforeClass;

import util.TestSessionFactories;

import com.amind.session.SessionFactory;
import com.amind.session.StatelessSessionFactory;
import com.lexmark.domain.Account;

public abstract class GlobalServiceStatelessBase {

	protected static final Log logger = LogFactory.getLog(GlobalServiceStatelessBase.class);
	protected static AmindGlobalService globalService;
	protected static SessionFactory sessionFactory;

	@BeforeClass
	public static void setUpBeforeClass() {
		globalService = new AmindGlobalService();
		sessionFactory = TestSessionFactories.newStatelessSessionFactory();
		globalService.setStatelessSessionFactory(sessionFactory);
	}

	@AfterClass
	public static void tearDownAfterClass() {
		((StatelessSessionFactory) sessionFactory).releaseAllStatelessSessions();
	}
	
	protected void logAccountList(List<Account> accountList) {
		for (Account account : accountList) {
			logger.debug("Account Id " + account.getAccountId());
			logger.debug("Account Name " + account.getAccountName());
			logger.debug("Organization Id " + account.getOrganizationID());
			logger.debug("viewInvoiceFlag" + account.isViewInvoiceFlag());
			logger.debug("createClaimFlag" + account.isCreateClaimFlag());
			logger.debug("indirectPartnerFlag" + account.isIndirectPartnerFlag());
			logger.debug("directPartnerFlag" + account.isDirectPartnerFlag());
			logger.debug("logisticsPartnerFlag" + account.isLogisticsPartnerFlag());
		}
	}
	
}
