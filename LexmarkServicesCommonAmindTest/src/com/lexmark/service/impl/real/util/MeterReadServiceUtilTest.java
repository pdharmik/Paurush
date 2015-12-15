package com.lexmark.service.impl.real.util;

import static org.junit.Assert.*;

import java.util.*;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.*;
import org.junit.*;

import util.TestSessionFactories;

import com.amind.data.service.ActionEvent;
import com.amind.data.service.ExecutionMode;
import com.amind.data.service.IDataManager;
import com.amind.data.service.QueryObject;
import com.amind.session.Session;
import com.lexmark.service.api.CrmSessionHandle;
import com.lexmark.service.impl.real.AmindCrmSessionHandle;
import com.lexmark.service.impl.real.AmindGlobalService;
import com.lexmark.service.impl.real.domain.MeterReadAsset;

@RunWith(Parameterized.class)
public class MeterReadServiceUtilTest {

	protected static final Log logger = LogFactory.getLog(MeterReadServiceUtilTest.class);
	protected static CrmSessionHandle handle;

	private final String searchSpec;

	@BeforeClass
	public static void setUpBeforeClass() {
		logger.debug("globalService init");
		AmindGlobalService globalService = new AmindGlobalService();
		globalService.setStatefulSessionFactory(TestSessionFactories.newStatefulSessionFactory());
		logger.debug("handle init");
		handle = globalService.initCrmSessionHandle(null);
		logger.debug("MeterReadService init");
	}

	@AfterClass
	public static void tearDownBeforeClass() {
		logger.debug("releasing the handle");
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

	@Parameters
	public static List<Object[]> parameters() {
		List<Object[]> list = new ArrayList<Object[]>();

		list.add(new Object[] { "EXISTS ([LXK SW Agreement Account LEGAL MDM ID] = '236295'AND [LXK SW Agreement Account MDM Level] ='Siebel') AND ([Serial Number] ~ LIKE '3500*') AND [Meter Type]= 'Manual'" });

		// EXISTS ([LXK SW Agreement Account LEGAL MDM ID] = '236295'AND [LXK SW
		// Agreement Account MDM Level] ='Siebel') AND ([Serial Number] ~ LIKE
		// '3500*') AND [Meter Type]= 'Manual'
		return list;
	}

	public MeterReadServiceUtilTest(String searchSpec) {
		this.searchSpec = searchSpec;
	}

	@Ignore
	@SuppressWarnings("unchecked")
	@Test
	public void testSimpleQuery() throws InterruptedException {
		AmindCrmSessionHandle crmSessionHandle = (AmindCrmSessionHandle) handle;
		Session session = crmSessionHandle.acquire();
		IDataManager dataManager = session.getDataManager();
		
		QueryObject criteria = new QueryObject(MeterReadAsset.class, ActionEvent.QUERY);
		criteria.addComponentSearchSpec(MeterReadAsset.class, searchSpec);
		criteria.setStartRowIndex(0);
		criteria.setNumRows(10);
		criteria.setExecutionMode(ExecutionMode.BiDirectional);
		
		List<MeterReadAsset> list = dataManager.query(criteria);

		assertNull("list is null!", list);
		assertFalse("list is empty!", list.isEmpty());

		for (MeterReadAsset asset : list) {
			logger.debug("id=" + asset.getId());
		}
	}

}
