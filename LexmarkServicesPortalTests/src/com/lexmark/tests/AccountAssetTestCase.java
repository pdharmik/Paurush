package com.lexmark.tests;

import static org.junit.Assert.assertNotNull;

import org.junit.BeforeClass;
import org.junit.Test;

import com.lexmark.contract.AssetListContract;
import com.lexmark.result.AssetListResult;
import com.lexmark.service.api.DeviceService;
import com.lexmark.service.impl.mock.DeviceServiceImpl;
import com.lexmark.services.util.XmlOutputGenerator;



public class AccountAssetTestCase {

	protected static DeviceService deviceService;
	protected static AssetListContract assetListContract;
	protected static AssetListResult deviceListResult;

	@BeforeClass
	public static void setUp() throws Exception{
		deviceService = new DeviceServiceImpl();
		assetListContract = new AssetListContract();
		deviceListResult = deviceService.retrieveDeviceList(assetListContract);
	}
	
	@Test
	public void checkDevicesValues(){
    	XmlOutputGenerator jg = new XmlOutputGenerator(null);
		
	}
	
	@Test
	public void checkTotalCount() {
		assertNotNull("Device List is null", deviceListResult.getTotalCount());
		
	}

}
