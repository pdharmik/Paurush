package com.lexmark.service.impl.real;

import junit.framework.Assert;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

import util.TestSessionFactories;

import com.amind.session.StatelessSessionFactory;
import com.lexmark.contract.UserFavoriteAssetContract;
import com.lexmark.result.FavoriteResult;
import com.lexmark.service.api.CrmSessionHandle;
import com.lexmark.service.api.DeviceService;

public class AccountAssetUpdateFavoriteTest {
	
	protected static final Log logger = 
		LogFactory.getLog(AccountAssetUpdateFavoriteTest.class);

	@Test
	public void testUpdateUserFavoriteAsset()
	{
		//input params
		 
		String contactId = "1-41HXBC3";
		Boolean favoriteFlag = false;
		String favoriteAssetId = "1-3J2IVYA";

		//test both creation and deletion
		updateUserFavoriteAsset(contactId, favoriteFlag, favoriteAssetId);
		updateUserFavoriteAsset(contactId, !favoriteFlag, favoriteAssetId);
	}
	
	private void updateUserFavoriteAsset(String contactId, boolean favoriteFlag, String favoriteAssetId)
	{
		CrmSessionHandle handle = null;
		StatelessSessionFactory sessionFactory = TestSessionFactories.newStatelessSessionFactory();
		
		try 
		{
			DeviceService service = new AmindContractedDeviceService();
			((AmindContractedDeviceService)service).setSessionFactory(sessionFactory);
			UserFavoriteAssetContract contract = new UserFavoriteAssetContract();
			contract.setSessionHandle(handle);
			contract.setContactId(contactId);
			contract.setFavoriteFlag(favoriteFlag);
			contract.setFavoriteAssetId(favoriteAssetId);
			
			FavoriteResult result = service.updateUserFavoriteAsset(contract);
			Assert.assertTrue(result.isResult());
		}
		catch (Exception e) 
		{
			logger.error("testUpdateUserFavoriteAsset caught exception", e);
			throw new RuntimeException(e);
		}
		finally 
		{
			sessionFactory.releaseAllStatelessSessions();
		}	
	}
}
