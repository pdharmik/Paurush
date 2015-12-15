package com.lexmark.services.tests;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.lexmark.domain.Asset;
import com.lexmark.domain.ServiceRequest;
import com.lexmark.services.util.XmlOutputGenerator;

public class XMLOutputGeneratorTest {
	private static Logger logger = LogManager.getLogger(XMLOutputGeneratorTest.class);

	private XmlOutputGenerator target;
	List<Asset> assetList;
	/**
	 * @throws Exception 
	 */
	@Before 
	public void setUp() throws Exception {
		target = new XmlOutputGenerator(Locale.US);
		assetList = getLargeDeviceList();
	}

	/**
	 * @throws Exception 
	 */
	@After 
	public void tearDown() throws Exception {
	}

	/**
	 * 
	 */
	@Test 
	public void testGenerate() {
		String output = target.generate(assetList, 10000, 100, 
				new String[]{"assetId", "ipAddress", "deviceName", "{assetId}^javascript:onDeviceNameClick();^_self{ipAddress}"});
	//	Assert.isTrue(output.indexOf("<cell>1234567896javascript:192.168.1.6</cell>")>0);
		logger.info(output);
	}

	/**
	 * 
	 */
	@Test 
	public void testFormatColumn() {
		Asset aa = new Asset();
		aa.setAssetId("123456789");
		aa.setIpAddress("192.168.1.1");

		
		ServiceRequest sr = new ServiceRequest();
		aa.setSerialNumber("79233X");
		sr.setAsset(aa);
		
		
		
		List<ServiceRequest> list = new ArrayList<ServiceRequest>();
		list.add(sr);
//		String s = target.generate(list, 100, 0, new String[]{"asset.ipAddress", "{shipDate, date, yyyy-MM-dd}"});
//		logger.info(s);
		
	}
	
	/**
	 * @return List<Asset> 
	 */
	private List<Asset> getLargeDeviceList(){
		
		List<Asset> assets = new ArrayList<Asset>();
		for (int i = 0; i < 10; i++) {
			Asset aa = new Asset();
			aa.setAssetId("123456789" + Integer.toString(i));
			aa.setSerialNumber("79233X" + Integer.toString(i));
			aa.setAssetTag("assetTag");
			aa.setAssetType("assetType");
			aa.setControlPanelURL("lexmark.printers.lisitng/");
			aa.setDeviceName("deviceName");
			aa.setDeviceTag("deviceTag");
			aa.setHostName("hostName");
			aa.setIpAddress("192.168.1." + Integer.toString(i));
			aa.setProductLine("Lexmark 952P");
			aa.setNotMyPrinter(false);
			//aa.setPhysicalLocation1("3rd floor, east building");
			assets.add(aa);
		}
		
		return assets;
	}

}
