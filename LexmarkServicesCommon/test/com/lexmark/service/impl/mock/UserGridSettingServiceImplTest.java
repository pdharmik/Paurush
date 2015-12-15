package com.lexmark.service.impl.mock;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.lexmark.contract.UserGridSettingContract;
import com.lexmark.result.UserGridSettingResult;

public class UserGridSettingServiceImplTest {

	UserGridSettingServiceImpl  target;
	
	@Before
	public void setup() {
		target = new UserGridSettingServiceImpl();
	}
	@After
	public void cleanup() {
		target = null;
	}
	


	@Test
	public void testSaveUserGridSettings() throws Exception{
		UserGridSettingContract contract = new UserGridSettingContract();
		contract.setUserNumber("123");
		contract.setGridId("1");
		contract.setColsFilter("filter");
		contract.setColsHidden("hidden");
		contract.setColsOrder("order");
		contract.setColsSorting("sort");
		
		target.saveUserGridSettings(contract);
		
		UserGridSettingResult result = target.retrieveUserGridSettings(contract);
		Assert.assertNotNull(result);
		Assert.assertEquals("filter", result.getColsFilter());
		Assert.assertEquals("hidden", result.getColsHidden());
		Assert.assertEquals("order", result.getColsOrder());
		Assert.assertEquals("sort", result.getColsSorting());
		
		target.deleteUserGridSettings(contract);
		
		result = target.retrieveUserGridSettings(contract);
		
		Assert.assertNotNull(result);
		Assert.assertNull(result.getColsFilter());
	}


}
