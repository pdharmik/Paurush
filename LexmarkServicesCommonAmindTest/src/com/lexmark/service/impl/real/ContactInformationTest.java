package com.lexmark.service.impl.real;

import com.lexmark.domain.ContactInformation;
import static java.lang.System.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import util.TestSessionFactories;

import com.amind.session.SessionFactory;
import com.amind.session.StatelessSessionFactory;
import com.lexmark.contract.ContactInformationContract;
import com.lexmark.result.ContactInformationResult;
import com.lexmark.service.api.ContactInformationService;

@RunWith(Parameterized.class)
public class ContactInformationTest {

	protected static final Log logger = LogFactory.getLog(ContactInformationTest.class);

	private static SessionFactory sessionFactory;
	private static ContactInformationService service;

	private final ContactInformationContract contract;

	@BeforeClass
	public static void setUpBeforeClass() {
		sessionFactory = TestSessionFactories.newStatelessSessionFactory();
		service = new AmindContactInformationService();
		((AmindContactInformationService) service).setStatelessSessionFactory(sessionFactory);
	}

	@AfterClass
	public static void tearDownAfterClass() {
		((StatelessSessionFactory) sessionFactory).releaseAllStatelessSessions();
	}

	@Parameters
	public static List<Object[]> parameters() {
		List<Object[]> list = new ArrayList<Object[]>();
		ContactInformationContract contract;

		contract = new ContactInformationContract();
		contract.setMdmId("265776");
		contract.setMdmLevel("Siebel");
		contract.setRoleName("Service and Support");
		list.add(new Object[] { contract });

		return list;
	}
	
	public ContactInformationTest(ContactInformationContract contract) {
		this.contract = contract;
	}

	@Test
	public void testContactInformationList() throws Exception {
		int iterationCount = 0;
		long startTime = currentTimeMillis();
		ContactInformationResult result = service.retrieveContactInformation(contract);
		long endTime = currentTimeMillis();

		logger.info("\nRetrieveContactInformation() -- " + "iteration " + iterationCount + " "
				+ "elapsed Time(ms): " + (endTime - startTime));
		logContactInformation(result);
	}

	private void logContactInformation(ContactInformationResult result) {
		assertNotNull("result is null!", result);
		List<ContactInformation> contactList = result.getContactInfoList();
		assertNotNull("contact list is null!", contactList);
		assertFalse("contact list is empty!", contactList.isEmpty());

		for (com.lexmark.domain.ContactInformation contactInfo : contactList) {
			logger.info("contact data:" + contactInfo.getContactData());
			logger.info("Role Name:" + contactInfo.getRoleName());
		}
	}
}
