package com.lexmark.service.impl.real;

import java.io.File;
import java.io.FileInputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Assert;
import org.junit.Test;

import util.TestSessionFactories;

import com.amind.session.Session;
import com.lexmark.contract.AssetMeterReadContract;
import com.lexmark.result.AssetMeterReadResult;
import com.lexmark.service.api.CrmSessionHandle;
import com.lexmark.service.api.MeterReadService;

public class ImportMeterReadServiceFile 
{
	protected static final Log logger = LogFactory.getLog(ImportMeterReadServiceFile.class);
	@Test
	public void importMeterReadServiceFile()
	{
		AmindGlobalService globalService = new AmindGlobalService();
		globalService.setStatefulSessionFactory(TestSessionFactories.newStatefulSessionFactory());
		CrmSessionHandle handle = null;

		try {
			handle = globalService.initCrmSessionHandle(null);
			
			MeterReadService service = new AmindContractedMeterReadService();
			AssetMeterReadContract contract = new AssetMeterReadContract();
			File file1 = new File("C:\\Documents and Settings\\Mansi\\My Documents\\Downloads\\file1.csv");
			contract.setFileStream(new FileInputStream(file1));
			contract.setMdmId("236295");
			contract.setContactId("1-1LUIPWR");
			contract.setUserFileName("test23.csv");
			contract.setSessionHandle(handle);
			AssetMeterReadResult result = service.importAssetMeterRead(contract);
			Assert.assertNotNull(result);
			Assert.assertTrue(result.isUpDateSuccess());
			}
		catch (Exception e) {
			logger.error("retrieveMeterReadAssetList caught exception", e);
			throw new RuntimeException(e);
		}
		finally {
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
	

}
