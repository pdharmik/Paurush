package com.lexmark.service.impl.real;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.BeforeClass;
import org.junit.Test;

import com.lexmark.contract.AssetMeterReadContract;
import com.lexmark.contract.DeleteAttachmentContract;
import com.lexmark.contract.MeterReadAssetListContract;
import com.lexmark.contract.MeterReadStatusContract;
import com.lexmark.contract.UpdateAssetMeterReadContract;
import com.lexmark.domain.Asset;
import com.lexmark.domain.PageCounts;
import com.lexmark.result.AssetMeterReadResult;
import com.lexmark.result.MeterReadAssetListResult;
import com.lexmark.result.MeterReadStatusFileResult;
import com.lexmark.result.MeterReadStatusListResult;
import com.lexmark.result.UpdateAssetMeterReadResult;
import com.lexmark.service.impl.real.domain.MeterReadAsset;


/**
 * @author vpetruchok
 * @version 1.0, 2012-10-25
 */
public class AmindContractedMeterReadServiceTest extends AmindServiceTest {
    
    static AmindContractedMeterReadService service;
    
    @BeforeClass
    public static void setUp() throws Exception {
        service = new AmindContractedMeterReadService(); 
    }

    @Test
    public void testRetrieveMeterReadAssetList_QA_defect4339() throws Exception {
        MeterReadAssetListContract contract = new MeterReadAssetListContract();
        contract.setSessionHandle(crmSessionHandle);
        contract.setStartRecordNumber(0);
        contract.setIncrement(40);
        contract.setFavoriteFlag(false);
        contract.setNewQueryIndicator(true);
        
        contract.setMdmID("023058159");
        contract.setMdmLevel("Global");
        contract.setContactId("1-42NMBKT");
        contract.setMeterReadType("Manual");

        MeterReadAssetListResult r = service.retrieveMeterReadAssetList(contract);
        MiscTest.print("", r.getAssets(), "lastPageCount");
        MiscTest.print("totalCount = ", r.getTotalCount());
    }
    
    @Test
    public void testRetrieveMeterReadAssetList_QA_defect6812() throws Exception {
        MeterReadAssetListContract contract = new MeterReadAssetListContract();
        contract.setSessionHandle(crmSessionHandle);
        contract.setStartRecordNumber(0);
        contract.setIncrement(40);
        contract.setFavoriteFlag(false);
        contract.setNewQueryIndicator(true);
        
        contract.setMdmID("794");
        contract.setMdmLevel("Legal");
        contract.setContactId("mock-contactId");
        contract.setMeterReadType("Manual");

        MeterReadAssetListResult r = service.retrieveMeterReadAssetList(contract);
        MiscTest.print("", r.getAssets(), "serialNumber", "lastPageCount");
        MiscTest.print("totalCount = ", r.getTotalCount());
    }
    
    @Test
    public void testRetrieveMeterReadAssetList_QA_defect6812_query() throws Exception {
        MiscTest.sampleSiebelQuery(MeterReadAsset.class,
                "EXISTS ([LXK SW Agreement Account LEGAL MDM ID] = '794' AND [LXK SW Agreement Account MDM Level] ='Siebel') AND [Meter Type] = 'Manual'"
                ,100);
    }
    
    
    
    @Test
    public void testRetrieveMeterReadAssetList_QA_withFavoriteFlag() throws Exception {
//        MeterReadAssetListContract contract = new MeterReadAssetListContract();
//        contract.setSessionHandle(crmSessionHandle);
//        contract.setStartRecordNumber(0);
//        contract.setIncrement(40);
//        contract.setFavoriteFlag(true);
//        contract.setNewQueryIndicator(true);
//        
//        contract.setMdmID("023058159");
//        contract.setMdmLevel("Global");
////        contract.setContactId("1-42NMBKT");
////        contract.setMeterReadType("Manual");
//
//        MeterReadAssetListResult result = service.retrieveMeterReadAssetList(contract);
//        for (Asset asset  : result.getAssets()) {
//			for (PageCounts pageCount : asset.getPageCounts()) {
//				System.out.println("-------------------------");
//				System.out.println(pageCount.getName());
//				System.out.println("LTPC: " + pageCount.getLtpc());
//				System.out.println("Colorade: " + pageCount.getColordate());
//				System.out.println("Count: " + pageCount.getCount());
//			}
//		}
//        MiscTest.print("", result.getAssets(), "lastPageCount");
//        MiscTest.print("totalCount = ", result.getTotalCount());
    }
    

    @Test
    public void testRetrieveMeterReadStatusList_IOICDetails() throws Exception {
        MeterReadStatusContract contract = new MeterReadStatusContract();
        contract.setSessionHandle(crmSessionHandle);
        contract.setMdmId("623331717");
//        contract.setUserFileName("mock-userFileName");
        MeterReadStatusListResult r = service.retrieveMeterReadStatusList(contract);
        MiscTest.print(r.getMeterReadStatusList());
    }
    
    

    @Test
    public void testImportAssetMeterRead() throws Exception {
        AssetMeterReadContract contract = new AssetMeterReadContract();
        contract.setMdmId("236295");
        contract.setContactId("1-1LUIPWR");
        contract.setSessionHandle(crmSessionHandle);

        AssetMeterReadResult result = service.importAssetMeterRead(contract);
        System.out.println(result.isUpDateSuccess());
    }
    
	@Test
	public void testImportAssetMeterRead_defect11487() throws Exception {
		AssetMeterReadContract contract = new AssetMeterReadContract();
		File file1 = new File("D:\\Lexmark\\Defects\\Attachments\\Defect11487\\MeterReadList(2).csv");
		contract.setFileStream(new FileInputStream(file1));
		contract.setMdmId("046901799");
		contract.setMdmId("Global");
		contract.setContactId("1-783BNIL");
		contract.setUserFileName("MeterReadList(2).csv");
		contract.setSessionHandle(crmSessionHandle);
		System.out.println(file1.exists());
		AssetMeterReadResult result = service.importAssetMeterRead(contract);
		System.out.println(result.isUpDateSuccess());
	}
	
	@Test
	public void testImportAssetMeterRead_defect11487_upload() throws Exception {
		AssetMeterReadContract contract = new AssetMeterReadContract();
		File file1 = new File("E:\\Lexmark\\MeterReadList(5).csv");
		contract.setFileStream(new FileInputStream(file1));
		contract.setMdmId("046901799");
		contract.setMdmId("Global");
		contract.setContactId("1-7CXV7CT");
		contract.setUserFileName("MeterReadList(5).csv");
		contract.setSessionHandle(crmSessionHandle);
		System.out.println(file1.exists());
		AssetMeterReadResult result = service.importAssetMeterRead(contract);
		System.out.println(result.isUpDateSuccess());
	}

    @Test
    public void testUpdateAssetMeterRead_defect10673() throws Exception {
        UpdateAssetMeterReadContract c = new UpdateAssetMeterReadContract();
        c.setSessionHandle(crmSessionHandle);
        c.setAsset(new Asset());
        c.setContactId("1-7CUW7E9");
        UpdateAssetMeterReadResult r = service.updateAssetMeterRead(c);
        logger.debug("Result: " + r.getResult());
    }
    
    @Test
    public void testUpdateAssetMeterRead_defect16846() throws Exception {
    	UpdateAssetMeterReadContract c = new UpdateAssetMeterReadContract();
    	c.setSessionHandle(crmSessionHandle);
    	c.setAsset(new Asset());
    	c.setContactId("1-BS96OPR");
    	UpdateAssetMeterReadResult r = service.updateAssetMeterRead(c);
    	logger.debug("Result: " + r.getResult());
    }

    @Test
    public void testRetrieveMeterReadStatusFile() throws Exception {
        MeterReadStatusContract c = new MeterReadStatusContract();
        c.setSessionHandle(crmSessionHandle);
        MeterReadStatusFileResult r = service.retrieveMeterReadStatusFile(c);
    }
    
    @Test
    public void testRetrieveMeterReadStatusFile_defect11364_2() throws Exception {
    	MeterReadStatusContract c = new MeterReadStatusContract();
    	c.setSessionHandle(crmSessionHandle);
    	c.setUserFileName("046901799~List(1)~20140116075410054.csv");
    	MeterReadStatusFileResult r = service.retrieveMeterReadStatusFile(c);
    	logger.debug(r.toString());
    }
    
    @Test
    public void testRetrieveMeterReadAssetListAfterUpdate() throws Exception {
        MeterReadAssetListContract contract = new MeterReadAssetListContract();
        contract.setSessionHandle(crmSessionHandle);
        
        contract.setMdmID("205529410");
        contract.setMdmLevel("Global");
        contract.setContactId("1-50WCH05");
        contract.setMeterReadType("Manual");
        contract.setIncrement(40);
        contract.setStartRecordNumber(0);
        contract.setNewQueryIndicator(true);
        
        contract.setFavoriteFlag(false);
        contract.setEntitlementEndDate("10/31/2013");
        
        Map<String, Object> filterMap = new HashMap<String, Object>();
        filterMap.put("installAddress.state", "KY");
        contract.setFilterCriteria(filterMap);

        MeterReadAssetListResult result = service.retrieveMeterReadAssetList(contract);
        
//        for (Asset asset : result.getAssets()) {
//			if(asset.getAssetId().equalsIgnoreCase("1-4NMIDHX")) {
//				for (PageCounts pc : asset.getPageCounts()) {
//					System.out.println(pc.getName()+" "+pc.getCount());;
//				}
//			}
//		}
        
        System.out.println(result.getAssets().size());
        System.out.println(result.getTotalCount());
        
        for (Asset asset : result.getAssets()) {
			System.out.println("OfficeNumber: " + asset.getInstallAddress().getOfficeNumber());
			System.out.println("District: " + asset.getInstallAddress().getDistrict());
			System.out.println("County: " + asset.getInstallAddress().getCounty());
		}
        
        MiscTest.print("", result.getAssets(), "lastPageCount");
        MiscTest.print("totalCount = ", result.getTotalCount());
    }
    
    
    
    @Test
    public void testRetrieveMeterReadAssetListTest2() throws Exception {
        MeterReadAssetListContract contract = new MeterReadAssetListContract();
        contract.setSessionHandle(crmSessionHandle);
        
        contract.setMdmID("205529410");
        contract.setMdmLevel("Global");
        contract.setContactId("1-50WCH05");
        contract.setChlNodeId("1-4JP2TVW");
        contract.setFavoriteFlag(false);
        contract.setFavoriteContactId("1-50WCH05");
        contract.setSortCriteria((Map<String, Object>) MiscTest.newHashMap("serialNumber", "ASCENDING"));
        contract.setMeterReadType("Manual");
        contract.setIncrement(40);
        contract.setStartRecordNumber(0);
        contract.setNewQueryIndicator(true);

        MeterReadAssetListResult result = service.retrieveMeterReadAssetList(contract);
        MiscTest.print("", result.getAssets(), "lastPageCount");
        MiscTest.print("totalCount = ", result.getTotalCount());                    
    }
    
                                   
    @Test
    public void testRetrieveMeterReadAssetList_defect8406() throws Exception {
        MeterReadAssetListContract contract = new MeterReadAssetListContract();
        contract.setSessionHandle(crmSessionHandle);
        contract.setStartRecordNumber(0);
        contract.setIncrement(40);
//        contract.setFavoriteFlag(true);
        contract.setNewQueryIndicator(true);        
        contract.setMdmID("1-YFNVUX");
        contract.setMdmLevel("Siebel");
        contract.setContactId("1-5HR5CYV");
        contract.setMeterReadType("Manual");   
        contract.setEntitlementEndDate("10/31/2013");
        
		Map<String, Object> sortFilter = new HashMap<String, Object>();
		sortFilter.put("installAddress.county", "DESCENDING");
        contract.setSortCriteria(sortFilter);
        
        Map<String, Object> filterFilter = new HashMap<String, Object>();
        filterFilter.put("installAddress.county", "W");
        contract.setFilterCriteria(filterFilter);

        MeterReadAssetListResult result = service.retrieveMeterReadAssetList(contract);
        System.out.println("Size: " + result.getAssets().size());
        System.out.println("Total count: " + result.getTotalCount());
        
		for (Asset asset : result.getAssets()) {
				System.out.println("Office Number: " + asset.getInstallAddress().getOfficeNumber());
				System.out.println("County: " + asset.getInstallAddress().getCounty());
				System.out.println("District: " + asset.getInstallAddress().getDistrict());
		}
        
        System.out.println("END");
    }
    
    
    @Test
    public void testRetrieveMeterReadAssetList_defect9131() throws Exception {
        MeterReadAssetListContract contract = new MeterReadAssetListContract();
        contract.setMdmLevel("Legal");
        contract.setContactId("1-6DV03JT");
        contract.setFavoriteFlag(false);
        contract.setMdmID("59098");
        contract.setFavoriteContactId("1-6DV03JT");
        contract.setMeterReadType("Manual"); 
        contract.setNewQueryIndicator(true);
        contract.setIncrement(40);
        contract.setStartRecordNumber(0);
        contract.setSessionHandle(crmSessionHandle);
        contract.setEntitlementEndDate("10/27/2012 05:30:00");
        
//		Map<String, Object> sortFilter = new HashMap<String, Object>();
//		sortFilter.put("serialNumber", "ASCENDING");
//        contract.setSortCriteria(sortFilter);
        
//        Map<String, Object> sortFilter = new HashMap<String, Object>();
//		sortFilter.put("serialNumber", "94F7Y7R");
//        contract.setFilterCriteria(sortFilter);
        
        

        MeterReadAssetListResult result = service.retrieveMeterReadAssetList(contract);
        
        for (Asset asset : result.getAssets()) {
			System.out.println(asset.getSerialNumber());
		}
        System.out.println("Size: " + result.getAssets().size());
        System.out.println("Total count: " + result.getTotalCount());
        
        System.out.println("END");
    }
    
    @Test
    public void testRetrieveMeterReadAssetList_defect9131_NewIssue() throws Exception {
        MeterReadAssetListContract contract = new MeterReadAssetListContract();
        contract.setMdmLevel("Legal");
        contract.setContactId("1-6DV03JT");
        contract.setFavoriteFlag(false);
        contract.setMdmID("2216");
        contract.setFavoriteContactId("1-6DV03JT");
//        contract.setMeterReadType("Manual"); 
        contract.setNewQueryIndicator(true);
        contract.setIncrement(-1);
        contract.setStartRecordNumber(0);
        contract.setSessionHandle(crmSessionHandle);
        contract.setEntitlementEndDate("12/05/2013");
        
		Map<String, Object> sortCriteria = new HashMap<String, Object>();
		sortCriteria.put("serialNumber", "ASCENDING");
        contract.setSortCriteria(sortCriteria);
        
//        Map<String, Object> filterCriteria = new HashMap<String, Object>();
//        filterCriteria.put("serialNumber", "94F7Y7R");
//        contract.setFilterCriteria(filterCriteria);
        
        

        MeterReadAssetListResult result = service.retrieveMeterReadAssetList(contract);
        
//        for (Asset asset : result.getAssets()) {
//			System.out.println(asset.getSerialNumber());
//		}
        
        System.out.println(result.getAssets() == null ? "Null" : result.getAssets().size());
        System.out.println("Total count: " + result.getTotalCount());
        
        
        List<Asset> assets = result.getAssets();

        if(assets!=null) {
        	for (Asset asset : assets) {
        		if(asset.getAssetId().equalsIgnoreCase("1-BV0V-2020")) {
        			System.out.println("Asset id: " + asset.getAssetId());
					List<PageCounts> pageCounts = asset.getPageCounts();
					if(pageCounts!=null) {
						for (PageCounts pageCount : pageCounts) {
							if(pageCount.getName().equalsIgnoreCase("LTPC")) {
								System.out.println("Name: " + pageCount.getName());
								System.out.println("Count: " + pageCount.getCount());
							}
						}
					}
        		}
			}
        }
        
        System.out.println("END");
    }
    
    
    @Test
    public void testRetrieveMeterReadAssetList_defect9756() throws Exception {
    	MeterReadAssetListContract contract = new MeterReadAssetListContract();
        contract.setMdmLevel("Legal");
        contract.setContactId("1-6DV03JT");
        contract.setFavoriteFlag(false);
        contract.setMdmID("43800");
        contract.setFavoriteContactId("1-6DV03JT");
        contract.setEntitlementEndDate("12/05/2013");
//        contract.setMeterReadType("Manual"); 
        contract.setNewQueryIndicator(true);
        contract.setIncrement(-1);
        contract.setStartRecordNumber(0);
        contract.setSessionHandle(crmSessionHandle);
        
		Map<String, Object> sortCriteria = new HashMap<String, Object>();
		sortCriteria.put("serialNumber", "ASCENDING");
        contract.setSortCriteria(sortCriteria);
        

        MeterReadAssetListResult result = service.retrieveMeterReadAssetList(contract);
        
        List<Asset> assets = result.getAssets();
        System.out.println(assets == null ? "Null" : result.getAssets().size());
        System.out.println("Total count: " + result.getTotalCount());
      
        if(assets!=null) {
        	for (Asset asset : assets) {
        		if(asset.getAssetId().equalsIgnoreCase("2404230")) {
					List<PageCounts> pageCounts = asset.getPageCounts();
					System.out.println("Page counts is null: " + pageCounts == null);
					if(pageCounts!=null) {
						for (PageCounts pageCount : pageCounts) {
							System.out.println("Name: " + pageCount.getName());
							System.out.println("Type: " + pageCount.getType());
						}
					}
        		}
			}
        }
        
        
        System.out.println("END");
    }
    
    
    @Test
    public void testRetrieveMeterReadAssetList_newMdmLevels() throws Exception {
    	MeterReadAssetListContract contract = new MeterReadAssetListContract();
        contract.setMdmLevel("Global");
        contract.setContactId("1-6AH086J");
        contract.setFavoriteFlag(false);
        contract.setMdmID("205529410");
        contract.setFavoriteContactId("1-6AH086J");
        contract.setEntitlementEndDate("10/31/2013");
        contract.setMeterReadType("Manual"); 
        contract.setNewQueryIndicator(true);
        contract.setIncrement(40);
        contract.setStartRecordNumber(0);
        contract.setSessionHandle(crmSessionHandle);
        
		Map<String, Object> sortCriteria = new HashMap<String, Object>();
		sortCriteria.put("serialNumber", "ASCENDING");
        contract.setSortCriteria(sortCriteria);
        
        
//        Map<String, Object> filterMap = new HashMap<String, Object>();
//        filterMap.put("installAddress.state", "KY");
//        contract.setFilterCriteria(filterMap);

        MeterReadAssetListResult result = service.retrieveMeterReadAssetList(contract);
        
        List<Asset> assets = new ArrayList<Asset>();
        System.out.println(assets == null ? "Null" : result.getAssets().size());
        System.out.println("Total count: " + result.getTotalCount());
      
        if(assets!=null) {
        	for (Asset asset : assets) {
				List<PageCounts> pageCounts = asset.getPageCounts();
				if(pageCounts!=null) {
					for (PageCounts pageCount : pageCounts) {
						System.out.println("Name: " + pageCount.getName());
						System.out.println("Type: " + pageCount.getType());
					}
				}
			}
        }
        
        
        System.out.println("END");
    }
    
    
    @Test
    public void testRetrieveMeterReadAssetList_defect9756_NewIssue() throws Exception {
    	MeterReadAssetListContract contract = new MeterReadAssetListContract();
        contract.setMdmLevel("Global");
        contract.setFavoriteContactId("1-50WCH05");
        contract.setChlNodeId("1-17EKMXJ");
        contract.setFavoriteFlag(false);
        contract.setMdmID("205529410");
        contract.setEntitlementEndDate("11/07/2013");
        contract.setContactId("1-50WCH05");
        contract.setMeterReadType("Manual"); 
        contract.setNewQueryIndicator(true);
        contract.setIncrement(40);
        contract.setStartRecordNumber(0);
        contract.setSessionHandle(crmSessionHandle);
        
		Map<String, Object> sortCriteria = new HashMap<String, Object>();
		sortCriteria.put("serialNumber", "ASCENDING");
        contract.setSortCriteria(sortCriteria);
        

        MeterReadAssetListResult result = service.retrieveMeterReadAssetList(contract);
        
        List<Asset> assets = new ArrayList<Asset>();
        System.out.println(assets == null ? "Null" : result.getAssets().size());
        System.out.println("Total count: " + result.getTotalCount());
      
        if(assets!=null) {
        	for (Asset asset : assets) {
				List<PageCounts> pageCounts = asset.getPageCounts();
				if(pageCounts!=null) {
					for (PageCounts pageCount : pageCounts) {
						System.out.println("Name: " + pageCount.getName());
						System.out.println("Type: " + pageCount.getType());
					}
				}
			}
        }
        
        
        System.out.println("END");
    }
    
























    @Test
    public void testRetrieveMeterReadAssetList_QA_defect11643() throws Exception {
    	MeterReadAssetListContract contract = new MeterReadAssetListContract();
    	Map<String, Object> sortCriteria = new HashMap<String, Object>();
    	sortCriteria.put("serialNumber", "ASCENDING");
    	contract.setSessionHandle(crmSessionHandle);
    	contract.setStartRecordNumber(0);
    	contract.setIncrement(40);
    	contract.setFavoriteFlag(true);
    	contract.setNewQueryIndicator(true);        
    	contract.setMdmID("15313");
    	contract.setMdmLevel("Legal");
    	contract.setContactId("1-77X2453");
    	contract.setMeterReadType("Manual");        
    	contract.setSortCriteria(sortCriteria);
    	contract.setEntitlementEndDate("2/12/2014 05:30:00");
    	
    	MeterReadAssetListResult result = service.retrieveMeterReadAssetList(contract);
    	for (Asset asset : result.getAssets()) {
			System.out.println("FirstName: " + asset.getAssetContact().getFirstName());
			System.out.println("getLastName: " + asset.getAssetContact().getLastName());
			System.out.println("getWorkPhone: " + asset.getAssetContact().getWorkPhone());
			System.out.println("getEmailAddress: " + asset.getAssetContact().getEmailAddress());
			System.out.println("-------");
		}
    }

    @Test
    public void testRetrieveMeterReadAssetList_defect9756_NewestIssue() throws Exception {
    	MeterReadAssetListContract contract = new MeterReadAssetListContract();
        contract.setMdmLevel("Global");
        contract.setFavoriteContactId("1-50WCH05");
        contract.setFavoriteFlag(false);
        contract.setMdmID("205529410");
        contract.setEntitlementEndDate("11/12/2013");
        contract.setContactId("1-50WCH05");
        contract.setMeterReadType("Manual"); 
        contract.setNewQueryIndicator(true);
        contract.setIncrement(40);
        contract.setStartRecordNumber(0);
        contract.setSessionHandle(crmSessionHandle);
        
		Map<String, Object> sortCriteria = new HashMap<String, Object>();
		sortCriteria.put("serialNumber", "ASCENDING");
        contract.setSortCriteria(sortCriteria);
        
        Map<String, Object> filterCriteria = new HashMap<String, Object>();
//        filterCriteria.put("installAddress.province", "AB");
        filterCriteria.put("installAddress.country", "Canada");
        contract.setFilterCriteria(filterCriteria);
        
        MeterReadAssetListResult result = service.retrieveMeterReadAssetList(contract);
        
        List<Asset> assets = result.getAssets();
        System.out.println(assets == null ? "Null" : result.getAssets().size());
        System.out.println("Total count: " + result.getTotalCount());
      
        if(assets!=null) {
        	for (Asset asset : assets) {
//				List<PageCounts> pageCounts = asset.getPageCounts();
//				if(pageCounts!=null) {
//					for (PageCounts pageCount : pageCounts) {
//						System.out.println("Name: " + pageCount.getName());
//						System.out.println("Type: " + pageCount.getType());
//					}
//				}
        		
        		System.out.println("Provice: " + asset.getInstallAddress().getProvince());
        		System.out.println("Country: " + asset.getInstallAddress().getCountry());
			}
        }
        
        
        System.out.println("END");
    }
    
    
    @Test
    public void testRetrieveMeterReadAssetList_defect16210() throws Exception {
    	MeterReadAssetListContract contract = new MeterReadAssetListContract();
    	contract.setMdmLevel("Global");
    	contract.setMdmID("315000554");
    	contract.setFavoriteContactId("1-80J75U7");
    	contract.setFavoriteFlag(false);
    	contract.setEntitlementEndDate("02/02/2015");
    	contract.setContactId("1-80J75U7");
    	contract.setNumofDays(30);
    	contract.setMeterReadType("Missing Manual"); 
    	contract.setNewQueryIndicator(true);
    	contract.setIncrement(-1);
    	contract.setStartRecordNumber(0);
    	contract.setSessionHandle(crmSessionHandle);
    	Map<String, Object> sortCriteria = new HashMap<String, Object>();
    	sortCriteria.put("serialNumber", "ASCENDING");
    	contract.setSortCriteria(sortCriteria);
    	Map<String, Object> filterCriteria = new HashMap<String, Object>();
//        filterCriteria.put("installAddress.province", "AB");
    	filterCriteria.put("installAddress.country", "Canada");
    	contract.setFilterCriteria(filterCriteria);
    	
    	MeterReadAssetListResult result = service.retrieveMeterReadAssetList(contract);
    	System.out.println("Total count: " + result.getTotalCount());
    	System.out.println("END");
    }
    
    
    @Test
    public void testRetrieveMeterReadAssetList_BookmarkDevicesNotShowingInPageCount() throws Exception {
    	MeterReadAssetListContract contract = new MeterReadAssetListContract();
    	
        contract.setMdmLevel("Legal");
        contract.setContactId("1-6AH086J");
        contract.setFavoriteFlag(true);
        contract.setMdmID("43800");
        contract.setFavoriteContactId("1-6AH086J");
        contract.setEntitlementEndDate("11/12/2013");
        contract.setMeterReadType("Manual"); 
        contract.setNewQueryIndicator(true);
        contract.setIncrement(40);
        contract.setStartRecordNumber(0);
        contract.setSessionHandle(crmSessionHandle);
        
		Map<String, Object> sortCriteria = new HashMap<String, Object>();
		sortCriteria.put("serialNumber", "ASCENDING");
        contract.setSortCriteria(sortCriteria);
        

        MeterReadAssetListResult result = service.retrieveMeterReadAssetList(contract);
        
        List<Asset> assets = result.getAssets();
        System.out.println(assets == null ? "Null" : result.getAssets().size());
        System.out.println("Total count: " + result.getTotalCount());
      
        Set<String> set = crmSessionHandle.getMeterReadFavoriteSet();
        for (String string : set) {
			System.out.println("ASSET ID " + string);
		}
        
        
        for (Asset asset : assets) {
			if(asset.getUserFavoriteFlag()) {
				System.out.println("ASSET ID 2 : " + asset.getAssetId());
			}
		}
        
        System.out.println("END ");
    }
    
    @Test
    public void testRetrieveMeterReadAssetList_Defect16624() throws Exception {
    	MeterReadAssetListContract contract = new MeterReadAssetListContract();
    	contract.setMdmLevel("Legal");
    	contract.setMdmID("6757");
    	contract.setContactId("1-MMKSJW3");
    	contract.setFavoriteFlag(false);
    	contract.setFavoriteContactId("1-MMKSJW3");
    	contract.setEntitlementEndDate("02/06/2015");
    	contract.setMeterReadType("Manual");
    	contract.setNumofDays(0);
    	contract.setNewQueryIndicator(true);
    	contract.setIncrement(40);
    	contract.setStartRecordNumber(0);
    	contract.setSessionHandle(crmSessionHandle);
    	
    	Map<String, Object> sortCriteria = new HashMap<String, Object>();
    	sortCriteria.put("serialNumber", "ASCENDING");
    	contract.setSortCriteria(sortCriteria);
    	
    	
    	MeterReadAssetListResult result = service.retrieveMeterReadAssetList(contract);
    	
    	List<Asset> assets = result.getAssets();
    	System.out.println(assets == null ? "Null" : result.getAssets().size());
    	System.out.println("Total count: " + result.getTotalCount());
    }
    
    @Test
    public void testRetrieveMeterReadAssetList_Defect16809() throws Exception {
    	MeterReadAssetListContract contract = new MeterReadAssetListContract();
    	contract.setMdmLevel("Legal");
    	contract.setMdmID("14973");
    	contract.setContactId("1-N5IBB0X");
    	contract.setFavoriteFlag(false);
    	contract.setFavoriteContactId("1-N5IBB0X");
    	contract.setEntitlementEndDate("02/18/2015");
    	contract.setMeterReadType("Manual");
    	contract.setNumofDays(0);
    	contract.setNewQueryIndicator(true);
    	contract.setIncrement(40);
    	contract.setStartRecordNumber(0);
    	contract.setSessionHandle(crmSessionHandle);
    	
    	Map<String, Object> sortCriteria = new HashMap<String, Object>();
    	sortCriteria.put("serialNumber", "ASCENDING");
    	contract.setSortCriteria(sortCriteria);
    	
    	MeterReadAssetListResult result = service.retrieveMeterReadAssetList(contract);
    	
    	List<Asset> assets = result.getAssets();
    	System.out.println(assets == null ? "Null" : result.getAssets().size());
    	System.out.println("Total count: " + result.getTotalCount());
    }
    
    @Test
    public void testRetrieveMeterReadAssetList_Defect16832() throws Exception {
    	MeterReadAssetListContract contract = new MeterReadAssetListContract();
    	contract.setMdmLevel("Global");
    	contract.setMdmID("483419636");
    	contract.setContactId("1-BGXLRJJ");
    	contract.setFavoriteFlag(true);
    	contract.setFavoriteContactId("1-BGXLRJJ");
    	contract.setEntitlementEndDate("02/23/2015");
    	contract.setMeterReadType("Manual");
    	contract.setNumofDays(0);
    	contract.setNewQueryIndicator(true);
    	contract.setIncrement(40);
    	contract.setStartRecordNumber(0);
    	contract.setSessionHandle(crmSessionHandle);
    	
    	Map<String, Object> sortCriteria = new HashMap<String, Object>();
    	sortCriteria.put("serialNumber", "ASCENDING");
    	contract.setSortCriteria(sortCriteria);
    	
    	MeterReadAssetListResult result = service.retrieveMeterReadAssetList(contract);
    	
    	List<Asset> assets = result.getAssets();
    	System.out.println(assets == null ? "Null" : result.getAssets().size());
    	System.out.println("Total count: " + result.getTotalCount());
    }
    
    @Test
    public void testRetrieveMeterReadAssetList_FiltrationIsNotWorking() throws Exception {
    	MeterReadAssetListContract contract = new MeterReadAssetListContract();
    	contract.setMdmLevel("Global");
    	contract.setMdmID("315000554");
    	contract.setContactId("1-BS96OPR");
    	contract.setFavoriteFlag(true);
    	contract.setFavoriteContactId("1-BS96OPR");
    	contract.setEntitlementEndDate("02/26/2015");
    	contract.setMeterReadType("Manual");
    	contract.setNumofDays(0);
    	contract.setNewQueryIndicator(true);
    	contract.setIncrement(40);
    	contract.setStartRecordNumber(0);
    	contract.setSessionHandle(crmSessionHandle);
    	
    	Map<String, Object> filterCriteria = new HashMap<String, Object>();
    	filterCriteria.put("serialNumber", "0212144");
		contract.setFilterCriteria(filterCriteria );
    	
    	Map<String, Object> sortCriteria = new HashMap<String, Object>();
    	sortCriteria.put("serialNumber", "ASCENDING");
    	contract.setSortCriteria(sortCriteria);
    	
    	MeterReadAssetListResult result = service.retrieveMeterReadAssetList(contract);
    	
    	List<Asset> assets = result.getAssets();
    	System.out.println(assets == null ? "Null" : result.getAssets().size());
    	System.out.println("Total count: " + result.getTotalCount());
    }
    
    @Test
    public void testRetrieveMeterReadAssetList_propertySet() throws Exception {
    	MeterReadAssetListContract contract = new MeterReadAssetListContract();
    	contract.setMdmLevel("Global");
    	contract.setMdmID("483419636");
    	contract.setContactId("1-BGXLRJJ");
    	contract.setFavoriteFlag(true);
    	contract.setFavoriteContactId("1-BGXLRJJ");
    	contract.setEntitlementEndDate("02/23/2015");
    	contract.setMeterReadType("Manual");
    	contract.setNumofDays(0);
    	contract.setNewQueryIndicator(true);
    	contract.setIncrement(40);
    	contract.setStartRecordNumber(0);
    	contract.setSessionHandle(crmSessionHandle);
    	
    	Map<String, Object> sortCriteria = new HashMap<String, Object>();
    	sortCriteria.put("serialNumber", "ASCENDING");
    	contract.setSortCriteria(sortCriteria);
    	
    	MeterReadAssetListResult result = service.retrieveMeterReadAssetList(contract);
    	
    	List<Asset> assets = result.getAssets();
    	System.out.println(assets == null ? "Null" : result.getAssets().size());
    	System.out.println("Total count: " + result.getTotalCount());
    }
    
    @Test
    public void testRetrieveMeterReadAssetList_defect16830() throws Exception {
    	MeterReadAssetListContract contract = new MeterReadAssetListContract();
    	contract.setMdmLevel("Global");
    	contract.setMdmID("315000554");
    	contract.setContactId("1-BS96OPR");
    	contract.setFavoriteFlag(false);
    	contract.setFavoriteContactId("1-BS96OPR");
    	contract.setEntitlementEndDate("03/07/2015");
    	contract.setMeterReadType("Manual");
    	contract.setNumofDays(0);
    	contract.setNewQueryIndicator(true);
    	contract.setIncrement(40);
    	contract.setStartRecordNumber(40);
    	contract.setSessionHandle(crmSessionHandle);
    	
    	Map<String, Object> sortCriteria = new HashMap<String, Object>();
    	sortCriteria.put("serialNumber", "ASCENDING");
    	contract.setSortCriteria(sortCriteria);
    	
    	for (int i = 0; i < 20; i++) {
    		MeterReadAssetListResult result = service.retrieveMeterReadAssetList(contract);
    		List<Asset> assets = result.getAssets();
    		System.out.println(assets == null ? "Null" : result.getAssets().size());
    		System.out.println("Total count: " + result.getTotalCount());
		}
    }
    
    @Test
    public void testRetrieveMeterReadAssetList_INC0106042() throws Exception {
    	MeterReadAssetListContract contract = new MeterReadAssetListContract();
    	contract.setMdmLevel("Domestic");
    	contract.setMdmID("811959758");
    	contract.setContactId("1-5YILDZF");
    	contract.setFavoriteFlag(false);
    	contract.setFavoriteContactId("1-5YILDZF");
    	contract.setEntitlementEndDate("06/12/2015");
    	contract.setMeterReadType("Missing All");
    	contract.setNumofDays(0);
    	contract.setNewQueryIndicator(true);
    	contract.setIncrement(-1);
    	contract.setStartRecordNumber(0);
    	contract.setSessionHandle(crmSessionHandle);
    	
    	Map<String, Object> sortCriteria = new HashMap<String, Object>();
    	sortCriteria.put("serialNumber", "ASCENDING");
    	contract.setSortCriteria(sortCriteria);
    	
		MeterReadAssetListResult result = service.retrieveMeterReadAssetList(contract);
		List<Asset> assets = result.getAssets();
		System.out.println(assets == null ? "Null" : result.getAssets().size());
		System.out.println("Total count: " + result.getTotalCount());
    }
    
    @Test
    public void testRetrieveMeterReadAssetList_IOICDetails() throws Exception {
    	MeterReadAssetListContract contract = new MeterReadAssetListContract();
    	contract.setMdmLevel("Global");
    	contract.setMdmID("623331717");
    	contract.setContactId("1-16X93QX");
    	contract.setFavoriteFlag(false);
    	contract.setFavoriteContactId("1-16X93QX");
    	contract.setEntitlementEndDate("08/14/2015");
    	contract.setMeterReadType("Manual");
//    	contract.setChlNodeId("1-3Z7PNBR");
    	contract.setNumofDays(0);
    	contract.setNewQueryIndicator(true);
    	contract.setIncrement(40);
    	contract.setStartRecordNumber(0);
    	contract.setSessionHandle(crmSessionHandle);
    	
    	Map<String, Object> filterCriteria = new HashMap<String, Object>();
//    	filterCriteria.put("installAddress.country", "USA");
    	filterCriteria.put("installAddress.addressName", "Lex");
    	filterCriteria.put("physicalLocation1", "WI");
    	filterCriteria.put("productLine", "MS");
    	filterCriteria.put("assetContact.firstName", "DA");
    	filterCriteria.put("assetContact.lastName", "Re");
    	filterCriteria.put("productTLI", "40");
    	filterCriteria.put("serialNumber", "40");
    	filterCriteria.put("assetTag", "40");
    	filterCriteria.put("ipAddress", "10.");
		contract.setFilterCriteria(filterCriteria );
    	
    	Map<String, Object> sortCriteria = new HashMap<String, Object>();
    	sortCriteria.put("serialNumber", "ASCENDING");
    	contract.setSortCriteria(sortCriteria);
    	
    	MeterReadAssetListResult result = service.retrieveMeterReadAssetList(contract);
    	List<Asset> assets = result.getAssets();
    	System.out.println(assets == null ? "Null" : result.getAssets().size());
    	System.out.println("Total count: " + result.getTotalCount());
    }
    
    
    
    @Test
    public void testRetrieveMeterReadStatusFile_defect10076() throws Exception {
        MeterReadStatusContract contract = new MeterReadStatusContract();
        contract.setSessionHandle(crmSessionHandle);
        contract.setUserFileName("503671943~MeterReadList(2)~20131118073105031.csv");
        
        MeterReadStatusFileResult result = null;
        try {
         result = service.retrieveMeterReadStatusFile(contract);
        }
        catch(Exception e) {
        	e.printStackTrace();
        }
        
//        System.out.println(result.getFileStream() == null);
    }
    
    
    
    @Test
    public void testRetrieveMeterReadStatusFile_defect10239() throws Exception {
        MeterReadStatusContract contract = new MeterReadStatusContract();
        contract.setSessionHandle(crmSessionHandle);
        contract.setUserFileName("503671943~MeterReadList(2)~20131118073105031.csv");
        
        MeterReadStatusFileResult result = null;
        try {
         result = service.retrieveMeterReadStatusFile(contract);
        }
        catch(Exception e) {
        	e.printStackTrace();
        }
        
//        System.out.println(result.getFileStream() == null);
    }
    
    
    @Test
    public void testRetrieveMeterReadStatusFile_defect10076_NewIssue() throws Exception {
        MeterReadStatusContract contract = new MeterReadStatusContract();
        contract.setSessionHandle(crmSessionHandle);
//        contract.setUserFileName("004027900~MeterReadList(3)~20131204115426054.csv");
        contract.setUserFileName("01111TestFile111.csv");
        
        MeterReadStatusFileResult result = null;
        try {
         result = service.retrieveMeterReadStatusFile(contract);
        }
        catch(Exception e) {
        	e.printStackTrace();
        }
        
//        System.out.println(result.getFileStream() == null);
    }
    
    
    @Test
    public void testImportAssetMeterRead_defect10076() throws Exception {
		AssetMeterReadContract contract = new AssetMeterReadContract();
		File file1 = new File("D:\\Lexmark\\Files\\004027900~MeterReadList(3)~20131204115426054.csv");
		contract.setFileStream(new FileInputStream(file1));
		contract.setMdmId("236295");
		contract.setContactId("1-1LUIPWR");
		contract.setUserFileName("testCSV1.csv");
		contract.setSessionHandle(crmSessionHandle);
		
		AssetMeterReadResult result = service.importAssetMeterRead(contract);
		
        System.out.println(result.isUpDateSuccess());
    }

    @Test
    public void testImportAssetMeterRead_defect10677() throws Exception {
		AssetMeterReadContract contract = new AssetMeterReadContract();
		File file1 = new File("D:\\Lexmark\\Defects\\Attachments\\046901799~MeterReadList(1)~20131210081253012.csv");
		contract.setFileStream(new FileInputStream(file1));
		contract.setMdmId("046901799");
		contract.setMdmId("Global");
		contract.setContactId("1-783BNIL");
		contract.setUserFileName("046901799~MeterReadList(1)~20131210081253012.csv");
		contract.setSessionHandle(crmSessionHandle);
		
		System.out.println(file1.exists());
		
		AssetMeterReadResult result = service.importAssetMeterRead(contract);
		
        System.out.println(result.isUpDateSuccess());
    }
    
    @Test
    public void testRetrieveMeterReadStatusFile_defect11133() throws Exception {
        MeterReadStatusContract contract = new MeterReadStatusContract();
        contract.setSessionHandle(crmSessionHandle);
//        contract.setUserFileName("046901799~MeterReadList(1)~20131210081253012.csv");
        contract.setUserFileName("046901799~pagecount12132013~20131219052440024.csv");
//        lxkssbliapp06a_23889_1265_0_236295~AMIT_MeterReadList~20110216043252032
//        lxkssbliapp06a_5162_235_0_236295~MeterReadList234~20110224034355043
//        lxkssbliapp05a_3532_463_0_046901799~pagecount12132013~20131219052440024
        
        MeterReadStatusFileResult result = null;
        try {
         result = service.retrieveMeterReadStatusFile(contract);
        }
        catch(Exception e) {
        	e.printStackTrace();
        }
        
        System.out.println(result.getFileStream() == null);
    }
    
    @Test
    public void testRetrieveMeterReadStatusFile_defect11364() throws Exception {
        MeterReadStatusContract contract = new MeterReadStatusContract();
        contract.setSessionHandle(crmSessionHandle);
        contract.setUserFileName("046901799~pagecount12132013~20131219052440024.csv");
//        lxkssbliapp05a_3532_463_0_046901799~pagecount12132013~20131219052440024
        
        MeterReadStatusFileResult result = null;
        try {
         result = service.retrieveMeterReadStatusFile(contract);
        }
        catch(Exception e) {
        	e.printStackTrace();
        }
        
        System.out.println(result.getFileStream() == null);
    }
    
    @Test
    public void testImportAssetMeterRead_defect11487_3() throws Exception {
		AssetMeterReadContract contract = new AssetMeterReadContract();
		File file1 = new File("D:\\Lexmark\\Defects\\Attachments\\Defect11487\\MeterReadList(2).csv");
		contract.setFileStream(new FileInputStream(file1));
		contract.setMdmId("046901799");
		contract.setMdmId("Global");
		contract.setContactId("1-783BNIL");
		contract.setUserFileName("MeterReadList(2).csv");
		contract.setSessionHandle(crmSessionHandle);
		
		System.out.println(file1.exists());
		
		AssetMeterReadResult result = service.importAssetMeterRead(contract);
		
        System.out.println(result.isUpDateSuccess());
    }
    
    @Test
    public void testImportAssetMeterRead_defect11487_2() throws Exception {
		AssetMeterReadContract contract = new AssetMeterReadContract();
		File file1 = new File("D:\\Lexmark\\Defects\\Attachments\\Defect11487\\MeterReadListTest2.csv");
		contract.setFileStream(new FileInputStream(file1));
		contract.setMdmId("046901799");
		contract.setMdmId("Global");
		contract.setContactId("1-7CXV7CT");
		contract.setUserFileName("MeterReadListTest2.csv");
		contract.setSessionHandle(crmSessionHandle);
		
		System.out.println(file1.exists());
		
		AssetMeterReadResult result = service.importAssetMeterRead(contract);
		
        System.out.println(result.isUpDateSuccess());
    }
    
    
    @Test
    public void testImportAssetMeterRead_2() throws Exception {
		AssetMeterReadContract contract = new AssetMeterReadContract();
		File file1 = new File("D:\\Lexmark\\Defects\\Defect11315\\Defect11315_107Test.xlsx");
		contract.setFileStream(new FileInputStream(file1));
		contract.setMdmId("046901799");
		contract.setMdmId("Global");
		contract.setContactId("1-783BNIL");
		contract.setUserFileName("Defect11315_107Test.xlsx");
		contract.setSessionHandle(crmSessionHandle);
		
		System.out.println(file1.exists());
		
		AssetMeterReadResult result = service.importAssetMeterRead(contract);
		
        System.out.println(result.isUpDateSuccess());
    }
    
    @Test
    public void testImportAssetMeterRead_IOIC() throws Exception {
    	AssetMeterReadContract contract = new AssetMeterReadContract();
    	File file1 = new File("E:\\Lexmark\\Defects\\a.csv");
    	contract.setFileStream(new FileInputStream(file1));
    	contract.setMdmId("483419636");
    	contract.setMdmId("Global");
    	contract.setContactId("1-BGXLRJJ");
    	contract.setUserFileName("a.csv");
    	contract.setSessionHandle(crmSessionHandle);
    	System.out.println(file1.exists());
    	AssetMeterReadResult result = service.importAssetMeterRead(contract);
    	System.out.println(result.isUpDateSuccess());
    }
    
    
    @Test
    public void testRetrieveMeterReadStatusFile_defect12394() throws Exception {
        MeterReadStatusContract contract = new MeterReadStatusContract();
        contract.setSessionHandle(crmSessionHandle);
//        contract.setUserFileName("009420159~MeterReadList~20140307064413044.csv");
//        contract.setUserFileName("623331717~AUTO_DIST6~20150408023403034.csv");
        contract.setUserFileName("ÖmerMekkî3.csv");
//        contract.setUserFileName("009420159~Lista_de_Contadores_LeÃ-dos~20140227025759057.csv");
//        contract.setUserFileName("1-8ON4TPX");
        
        MeterReadStatusFileResult result = null;
//        try {
         result = service.retrieveMeterReadStatusFile(contract);
//        }
//        catch(Exception e) {
//        	e.printStackTrace();
//        }
        
        System.out.println(result.getFileStream() == null);
        
        InputStream stream = result.getFileStream();
        System.out.println(stream.available());
		OutputStream outputStream = new FileOutputStream(new File("E:\\Lexmark\\Defects\\a2.csv"));
		int read = 0;
		byte[] bytes = new byte[1024];

		while ((read = stream.read(bytes)) != -1) {
			outputStream.write(bytes, 0, read);
		}
		
        System.out.println("End");
    }
    
}
