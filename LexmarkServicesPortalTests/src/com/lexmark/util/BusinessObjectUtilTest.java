package com.lexmark.util;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.lexmark.domain.Asset;
import com.lexmark.domain.ServiceRequest;

public class BusinessObjectUtilTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testFormatColumn() {
		ServiceRequest sr = createSR();
		String ret = BusinessObjectUtil.formatColumn(sr, "serviceRequestDate", Locale.getDefault());
		Assert.assertEquals("08/04/2000", ret);
		
	}
	
	
	private ServiceRequest createSR() {
		String[] optionExchangeList = {"test","tst"};
		int i = 0;
		Calendar calendar = Calendar.getInstance();
		calendar.set(2000, 7, 4);
		calendar.set(Calendar.YEAR, 2000+ i%11);
		ServiceRequest sr = new ServiceRequest();
		Asset asset = new Asset();
		sr.setServiceRequestDate(calendar.getTime());
		asset.setSerialNumber("7923X9");
		asset.setModelNumber("Lexmark xd464dfe");
		asset.setPhysicalLocation1("Lexington.KY");
		if(i%2==1){
			sr.setServiceRequestStatus("Shiped");
		}else{
			sr.setServiceRequestStatus("Scheduled");
		}
		sr.setOptionExchangeOtherDescription("Exchange this printer for");
		sr.setProblemDescription("My printer is jammed");
		sr.setReferenceNumber("216" + Integer.toString(i));
		sr.setServiceRequestNumber("123456789" + Integer.toString(i));
		sr.setAsset(asset);
		return sr;
	}

}
