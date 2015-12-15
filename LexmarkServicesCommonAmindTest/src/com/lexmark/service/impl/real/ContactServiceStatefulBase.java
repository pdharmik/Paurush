package com.lexmark.service.impl.real;

import java.lang.reflect.Field;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.AfterClass;
import org.junit.BeforeClass;

import util.TestSessionFactories;

import com.amind.session.Session;
import com.lexmark.domain.AccountContact;
import com.lexmark.service.api.ContactService;
import com.lexmark.service.api.CrmSessionHandle;

/**
 * @author pkozlov
 */
public abstract class ContactServiceStatefulBase {

	protected static final Log logger = LogFactory.getLog(ContactServiceStatefulBase.class);
	protected static CrmSessionHandle handle;
	protected static ContactService service;

	@BeforeClass
	public static void setUpBeforeClass() {
		AmindGlobalService globalService = new AmindGlobalService();
		globalService.setStatefulSessionFactory(TestSessionFactories.newStatefulSessionFactory());
		handle = globalService.initCrmSessionHandle(null);
		service = new AmindContactService();
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

	protected void debugContact(AccountContact contact) throws IllegalArgumentException,
			IllegalAccessException {
		logger.debug("========begin========");
		for (Field contactField : contact.getClass().getDeclaredFields()) {
			contactField.setAccessible(true);
			String fieldName = contactField.getName();
			if (fieldName.equalsIgnoreCase("serialVersionUID")) {
				continue;
			} else if (fieldName.equalsIgnoreCase("address")) {
				Object address = contactField.get(contact);
				for (Field addressField : address.getClass().getDeclaredFields()) {
					addressField.setAccessible(true);
					fieldName = addressField.getName();
					if (fieldName.equalsIgnoreCase("serialVersionUID")) {
						continue;
					} else {
						logger.debug(fieldName + ": " + addressField.get(address));
					}
				}
			} else {
				logger.debug(fieldName + ": " + contactField.get(contact));
			}
		}
		logger.debug("=========end=========");
	}

}
