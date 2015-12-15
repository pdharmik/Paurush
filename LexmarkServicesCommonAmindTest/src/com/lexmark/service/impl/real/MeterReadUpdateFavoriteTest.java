package com.lexmark.service.impl.real;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import org.junit.Test;

import com.lexmark.contract.UpdateAssetMeterReadContract;
import com.lexmark.domain.Asset;
import com.lexmark.domain.PageCounts;
import com.lexmark.result.UpdateAssetMeterReadResult;

public class MeterReadUpdateFavoriteTest extends MeterReadServiceTestBase {

	@Test
	public void testUpdateAssetMeterRead() throws Exception {
		UpdateAssetMeterReadContract contract = new UpdateAssetMeterReadContract();
		contract.setSessionHandle(handle);
		Asset asset = new Asset();
		asset.setAssetId("1-Q6P-2079");
		asset.setMeterReadDate(Calendar.getInstance().getTime());
		asset.setNewPageCount("17000");
		asset.setNewColorPageCount("450");
		contract.setAsset(asset);
		contract.setContactId("1-PKJY3");

		UpdateAssetMeterReadResult result = service.updateAssetMeterRead(contract);

		assertNotNull("Result is null", result);
		assertTrue("Result status is false", result.getResult());
	}
	
	
	@Test
	public void testUpdateAssetMeterReadUpdatePageCounts() throws Exception {
		UpdateAssetMeterReadContract contract = new UpdateAssetMeterReadContract();
		contract.setSessionHandle(handle);
		contract.setContactId("1-50WCH05");
		
		Asset asset = new Asset();
		asset.setAssetId("1-4NMIDP7");
		PageCounts pageCount = new PageCounts();
		pageCount.setName("Mono");
		pageCount.setCount("700");
		pageCount.setDate("7/31/2013 13:28:19");
		asset.setPageCounts(Arrays.asList(pageCount));
		contract.setAsset(asset);

		UpdateAssetMeterReadResult result = service.updateAssetMeterRead(contract);

		assertNotNull("Result is null", result);
		assertTrue("Result status is false", result.getResult());
	}
	
	
	
	@Test
	public void testUpdateAssetMeterRead_defect10023() throws Exception {
		UpdateAssetMeterReadContract contract = new UpdateAssetMeterReadContract();
		contract.setSessionHandle(handle);
		contract.setContactId("1-526QV0B");
		
		Asset asset = new Asset();
		asset.setAssetId("1-9XMW-4462");
		PageCounts pageCount = new PageCounts();
		pageCount.setName("LTPC");
		pageCount.setCount("1000000");
		pageCount.setDate("12/13/2013 08:00:00");
		asset.setPageCounts(Arrays.asList(pageCount));
		contract.setAsset(asset);

		UpdateAssetMeterReadResult result = new UpdateAssetMeterReadResult(); 
		try {
			result = service.updateAssetMeterRead(contract);
		}
		catch(Exception ex) {
			
		}
		
		System.out.println(result.getResult());

	}	
	
	
	@Test
	public void testUpdateAssetMeterRead_defect10673() throws Exception {
		UpdateAssetMeterReadContract contract = new UpdateAssetMeterReadContract();
		contract.setSessionHandle(handle);
		contract.setContactId("1-7CUW7E9");
		
		Asset asset = new Asset();
		asset.setAssetId("1-7CCLT37");
		List<PageCounts> list = new ArrayList<PageCounts>();
		
		PageCounts pageCount = new PageCounts();
		pageCount.setName("StatementColor");
		pageCount.setCount("50");
		pageCount.setDate("12/25/2013 14:53:00");
		list.add(pageCount);
		
		pageCount = new PageCounts();
		pageCount.setName("StatementLTPC");
		pageCount.setCount("50");
		pageCount.setDate("12/25/2013 14:53:00");
		list.add(pageCount);
		
		pageCount = new PageCounts();
		pageCount.setName("Tabloid  Color");
		pageCount.setCount("50");
		pageCount.setDate("12/25/2013 14:53:00");
		list.add(pageCount);
		
		pageCount = new PageCounts();
		pageCount.setName("Tabloid  LTPC");
		pageCount.setCount("50");
		pageCount.setDate("12/25/2013 14:53:00");
		list.add(pageCount);
		
		pageCount = new PageCounts();
		pageCount.setName("Total Scans");
		pageCount.setCount("50");
		pageCount.setDate("12/25/2013 14:53:00");
		list.add(pageCount);
		
		asset.setPageCounts(list);
		contract.setAsset(asset);

		UpdateAssetMeterReadResult result = new UpdateAssetMeterReadResult(); 
		try {
			result = service.updateAssetMeterRead(contract);
		}
		catch(Exception ex) {
			
		}
		
		System.out.println(result.getResult());

	}
	
}
