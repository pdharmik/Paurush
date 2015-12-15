package com.lexmark.service.impl.real;

import java.util.List;

import static junit.framework.Assert.*;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

import com.lexmark.contract.GlobalLegalEntityListContract;
import com.lexmark.domain.GlobalAccount;

// TODO (pkozlov) this test uses the GlobalLegalEntityServiceImpl
// I am not really sure what to do with it - optimize it?
public class GlobalEntityListTest extends GlobalServiceStatelessBase {

	protected static final Log logger = LogFactory.getLog(GlobalEntityListTest.class);

	@Test
	public void testGlobalEntityList() {

		GlobalLegalEntityServiceImpl globalLegalEntityService = new GlobalLegalEntityServiceImpl();
		globalLegalEntityService.setGlobalService(globalService);

		GlobalLegalEntityListContract contract = new GlobalLegalEntityListContract();
		contract.setSessionHandle(null);
		List<GlobalAccount> entityList = globalLegalEntityService.getGlobalAccounts();
		assertNotNull("entityList is null", entityList);
		assertTrue("entityList is empty", entityList.size() > 0);

		int rowsToShow = entityList.size() < 50 ? entityList.size() : 50;
		GlobalAccount entity = null;
		for (int i = 0; i < rowsToShow; i++) {
			entity = entityList.get(i);
			logger.info("Entity: mdmId=" + entity.getMdmId() + " , mdmLevel=" + entity.getMdmLevel()
					+ ", Legal Name=" + entity.getLegalName());
			assertNotNull("mdmId is null", entity.getMdmId());
			assertNotNull("mdmLevel is null", entity.getMdmLevel());
		}

	}

}
