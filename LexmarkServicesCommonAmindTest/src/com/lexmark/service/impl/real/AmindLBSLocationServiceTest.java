package com.lexmark.service.impl.real;



import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.lexmark.contract.AddressListContract;
import com.lexmark.contract.SiebelAccountListContract;
import com.lexmark.result.AddressListResult;
import com.lexmark.service.impl.real.domain.LBSLocationBuildingType;
import com.lexmark.service.impl.real.domain.LBSLocationFloor;
import com.lexmark.util.LangUtil;


/**
 * @see com.lexmark.service.impl.real.AmindContractedServiceRequestServiceTestSuite
 *
 * @author David Tsamalashvili
 * @version 1.0, 2012-07-10
 */
public class AmindLBSLocationServiceTest extends AmindServiceTest {

	AmindLBSLocationService service;
	SiebelAccountListContract contract;

	@Before
	public void setUp() throws Exception {
		com.lexmark.service.impl.real.AmindServiceTest.setUp_AmindServerTest_static();
		service = new AmindLBSLocationService();
		service.setSessionFactory(statelessSessionFactory);
		contract = new SiebelAccountListContract();
	}

	@After
	public void tearDown() throws Exception {
		com.lexmark.service.impl.real.AmindServiceTest.tearDown_AmindServerTest_static();
	}

	
	@Test
	public void testRetrieveAddressList_LBSAddressTest_Location_with_city() throws Exception {
		AddressListContract contract = new AddressListContract();
		contract.setSessionHandle(crmSessionHandle);
		contract.setMdmId("007915069");
		contract.setMdmLevel("Global");
		contract.setNewQueryIndicator(true);
		contract.setStartRecordNumber(0);
		contract.setIncrement(40);
		contract.setLbsFlag(true);
		contract.setCountry("USA");
		contract.setState("KY");
		contract.setCity("Lexington");
		contract.setFirstLoccationCall(false);
		
		//To get Building
//		contract.setLocationId("1-JTKIR3P");
//		contract.setLocationType("Building");
		
		//To get Floor
//		contract.setLocationId("1-JUMHB2M");
//		contract.setLocationType("Floor");
		
		//To get Zone
		contract.setLocationId("1-JU4F4FL");		
		contract.setLocationType("Zone");


		AddressListResult result = service.retrieveLBSLocationList(contract);
		MiscTest.print(result.getLbsLocationSiteList());
	}
	
	@Test
	public void testRetrieveAddressList_LBSAddressTest_Location_with_address() throws Exception {
		AddressListContract contract = new AddressListContract();
		contract.setSessionHandle(crmSessionHandle);
//		contract.setMdmId("007915069");
		contract.setMdmId("007915069");
		contract.setMdmLevel("Global");
		contract.setNewQueryIndicator(true);
		contract.setStartRecordNumber(0);
		contract.setIncrement(40);
		contract.setLbsFlag(true);
		contract.setFirstLoccationCall(true);
		contract.setLbsAddressId("1-JTKIR30");
//		contract.setLbsAddressId("1-1232");
		
		//To get Building
//		contract.setLocationId("1-JTKIR3P");
//		contract.setLocationType("Building");
		
		//To get Floor
//		contract.setLocationId("1-JUMHB2M");
//		contract.setLocationType("Floor");
		
		//To get Zone
//		contract.setLocationId("1-JU4F4FL");		
//		contract.setLocationType("Zone");


		AddressListResult result = service.retrieveLBSLocationList(contract);
		MiscTest.print(result.getLbsLocationSiteList());
	}
	
	@Test
	public void testRetrieveLBSLocationsList_defect16311_SB() throws Exception {
		AddressListContract contract = new AddressListContract();
		contract.setSessionHandle(crmSessionHandle);
		contract.setMdmId("1-ZIFBCQ");
		contract.setMdmLevel("Siebel");
		contract.setNewQueryIndicator(true);
		contract.setStartRecordNumber(0);
		contract.setIncrement(40);
		contract.setLbsFlag(true);
		contract.setFirstLoccationCall(true);
		contract.setLbsAddressId("1-AKI6223");
		
		AddressListResult result = service.retrieveLBSLocationList(contract);
		MiscTest.print(result.getLbsLocationSiteList());
	}
	
	@Test
	public void testRetrieveLBSLocationsList_defect16281_SB() throws Exception {
		AddressListContract contract = new AddressListContract();
		contract.setSessionHandle(crmSessionHandle);
		contract.setMdmId("082130378");
		contract.setMdmLevel("Global");
		contract.setNewQueryIndicator(true);
		contract.setStartRecordNumber(0);
		contract.setIncrement(40);
		contract.setLbsFlag(true);
		contract.setFirstLoccationCall(true);
		contract.setLbsAddressId("1-IYJQXYC");
		
		AddressListResult result = service.retrieveLBSLocationList(contract);
		MiscTest.print(result.getLbsLocationSiteList());
	}
	
	@Test
	public void testRetrieveLBSLocationsList_defect16090_SB() throws Exception {
		AddressListContract contract = new AddressListContract();
		contract.setSessionHandle(crmSessionHandle);
		contract.setMdmId("1-I73AVJC");
		contract.setMdmLevel("Siebel");
		contract.setNewQueryIndicator(true);
		contract.setStartRecordNumber(0);
		contract.setIncrement(40);
		contract.setLbsFlag(true);
		contract.setFirstLoccationCall(true);
		contract.setLbsAddressId("1-IYJQXYC");
		
		AddressListResult result = service.retrieveLBSLocationList(contract);
		MiscTest.print(result.getLbsLocationSiteList());
	}
	@Test
	public void testRetrieveLBSLocationsList_defectTest() throws Exception {
		AddressListContract contract = new AddressListContract();
		contract.setSessionHandle(crmSessionHandle);
		contract.setMdmId("1-ZIFBCQ");
		contract.setMdmLevel("Siebel");
		contract.setNewQueryIndicator(true);
		contract.setStartRecordNumber(0);
		contract.setIncrement(40);
		contract.setLbsFlag(true);
		contract.setFirstLoccationCall(true);
		contract.setLbsAddressId("1-AKI6223");
		
		AddressListResult result = service.retrieveLBSLocationList(contract);
		MiscTest.print(result.getLbsLocationSiteList());
	}
	
	@Test
	public void testRetrieveLBSLocationsList_defect16583() throws Exception {
		AddressListContract contract = new AddressListContract();
		contract.setSessionHandle(crmSessionHandle);
		contract.setMdmId("6757");
		contract.setMdmLevel("Legal");
		contract.setNewQueryIndicator(true);
		contract.setStartRecordNumber(0);
		contract.setIncrement(40);
		contract.setLbsFlag(true);
		contract.setFirstLoccationCall(true);
		contract.setLbsAddressId("1-MHL1QXQ");
		
		AddressListResult result = service.retrieveLBSLocationList(contract);
		MiscTest.print(result.getLbsLocationSiteList());
	}
	
	@Test
	public void testRetrieveLBSLocationsList_defect16907() throws Exception {
		AddressListContract contract = new AddressListContract();
		contract.setSessionHandle(crmSessionHandle);
		contract.setMdmId("109994");
		contract.setMdmLevel("Legal");
		contract.setLbsAddressId("1-N6CVYLU");
		contract.setLbsFlag(true);
		contract.setFirstLoccationCall(true);
		contract.setNewQueryIndicator(true);
		contract.setStartRecordNumber(0);
		contract.setIncrement(40);
		
		AddressListResult result = service.retrieveLBSLocationList(contract);
		MiscTest.print(result.getLbsLocationSiteList());
	}
	
	@Test
	public void testRetrieveLBSLocationsList_defect16859() throws Exception {
		AddressListContract contract = new AddressListContract();
		contract.setSessionHandle(crmSessionHandle);
		contract.setMdmId("62117");
		contract.setMdmLevel("Legal");
		contract.setCountry("Ireland");
		contract.setCity("Galway");
		contract.setLocationId("1-NGOTE5P");
		contract.setLocationType("Floor");
		contract.setCounty("Galway");
		contract.setLbsFlag(true);
		contract.setFirstLoccationCall(false);
		contract.setNewQueryIndicator(true);
		contract.setStartRecordNumber(0);
		contract.setIncrement(40);
		
		AddressListResult result = service.retrieveLBSLocationList(contract);
		for (LBSLocationFloor floor : result.getLbsLocationFloorList()) {
			System.out.println("Floor: " + floor.getFloor());
		}
		MiscTest.print(result.getLbsLocationSiteList());
	}
	
	@Test
	public void testRetrieveLBSLocationsList_defect16859_2() throws Exception {
		AddressListContract contract = new AddressListContract();
		contract.setSessionHandle(crmSessionHandle);
		contract.setMdmId("275242212");
		contract.setMdmLevel("Global");
		contract.setCountry("France");
		contract.setProvince("Œle-de-France");
		contract.setCity("Paris");
//		contract.setCounty("Galway");
		contract.setLocationId("1-MHL1QXG");
		contract.setLocationType("Floor");
		contract.setLbsFlag(true);
		contract.setFirstLoccationCall(false);
		contract.setNewQueryIndicator(true);
		contract.setStartRecordNumber(0);
		contract.setIncrement(40);
		
		AddressListResult result = service.retrieveLBSLocationList(contract);
		for (LBSLocationFloor floor : result.getLbsLocationFloorList()) {
			System.out.println("Floor: " + floor.getFloor());
		}
		MiscTest.print(result.getLbsLocationSiteList());
	}
	
	@Test
	public void testRetrieveLBSLocationsList_defect16859_3_workingfine() throws Exception {
		AddressListContract contract = new AddressListContract();
		contract.setSessionHandle(crmSessionHandle);
		contract.setMdmId("17803");
		contract.setMdmLevel("Legal");
		contract.setCountry("USA");
		contract.setState("NY");
		contract.setCity("Albany");
//		contract.setCounty("Galway");
		contract.setLocationId("1-MDYDYYE");
		contract.setLocationType("Floor");
		contract.setLbsFlag(true);
		contract.setFirstLoccationCall(false);
		contract.setNewQueryIndicator(true);
		contract.setStartRecordNumber(0);
		contract.setIncrement(40);
		
		AddressListResult result = service.retrieveLBSLocationList(contract);
		for (LBSLocationFloor floor : result.getLbsLocationFloorList()) {
			System.out.println("Floor: " + floor.getFloor());
		}
		MiscTest.print(result.getLbsLocationSiteList());
	}
	
	@Test
	public void testRetrieveLBSLocationsList_Moverequest_Sitefield() throws Exception {
		AddressListContract contract = new AddressListContract();
		contract.setSessionHandle(crmSessionHandle);
		contract.setMdmId("17803");
		contract.setMdmLevel("Legal");
		contract.setCountry("USA");
		contract.setState("NY");
		contract.setCity("Albany");
//		contract.setCounty("Galway");
		contract.setLocationId("1-MDYDYYD");
		contract.setLocationType("Site");
		contract.setLbsFlag(true);
		contract.setFirstLoccationCall(false);
		contract.setNewQueryIndicator(true);
		contract.setStartRecordNumber(0);
		contract.setIncrement(40);
		
		AddressListResult result = service.retrieveLBSLocationList(contract);
		MiscTest.print(result.getLbsLocationSiteList());
	}
	
	@Test
	public void testRetrieveLBSLocationsList_defect16983() throws Exception {
		AddressListContract contract = new AddressListContract();
		contract.setSessionHandle(crmSessionHandle);
		contract.setMdmId("51103");
		contract.setMdmLevel("Legal");
		contract.setLbsAddressId("1-NNHNOP2");
		contract.setLbsFlag(true);
		contract.setFirstLoccationCall(true);
		contract.setNewQueryIndicator(true);
		contract.setStartRecordNumber(0);
		contract.setIncrement(40);
		
		AddressListResult result = service.retrieveLBSLocationList(contract);
		MiscTest.print(result.getLbsLocationSiteList());
	}
	
	@Test
	public void testRetrieveLBSLocationsList_properySetTest() throws Exception {
		AddressListContract contract = new AddressListContract();
		contract.setSessionHandle(crmSessionHandle);
		contract.setMdmId("17803");
		contract.setMdmLevel("Legal");
		contract.setLbsAddressId("1-AKI6223");
		contract.setLbsFlag(true);
		contract.setFirstLoccationCall(true);
		contract.setNewQueryIndicator(true);
		contract.setStartRecordNumber(0);
		contract.setIncrement(40);
		
		AddressListResult result = service.retrieveLBSLocationList(contract);
		MiscTest.print(result.getLbsLocationSiteList());
	}
	
	@Test
	public void testRetrieveLBSLocationsList_defect17090() throws Exception {
		AddressListContract contract = new AddressListContract();
		contract.setSessionHandle(crmSessionHandle);
		contract.setMdmId("51103");
		contract.setMdmLevel("Legal");
		contract.setCountry("Austria");
		contract.setCity("Salzburg");
		contract.setProvince("Salzburg");
		contract.setLbsAddressId("1-AKI6223");
		contract.setLbsFlag(true);
		contract.setFirstLoccationCall(true);
		contract.setNewQueryIndicator(true);
		contract.setStartRecordNumber(0);
		contract.setIncrement(40);
		
		AddressListResult result = service.retrieveLBSLocationList(contract);
		MiscTest.print(result.getLbsLocationSiteList());
	}
	
	@Test
	public void testRetrieveLBSLocationsList_defect17090_2() throws Exception {
		AddressListContract contract = new AddressListContract();
		contract.setSessionHandle(crmSessionHandle);
		contract.setMdmId("51103");
		contract.setMdmLevel("Legal");
		contract.setCountry("Austria");
		contract.setCity("Ried im Innkreis");
		contract.setProvince("Ober√∂sterreich");
		contract.setLocationId("1-NIUGPZA");
		contract.setLocationType("Building");
//		contract.setLbsAddressId("1-AKI6223");
		contract.setLbsFlag(true);
		contract.setFirstLoccationCall(false);
		contract.setNewQueryIndicator(true);
		contract.setStartRecordNumber(0);
		contract.setIncrement(40);
		
		AddressListResult result = service.retrieveLBSLocationList(contract);
		MiscTest.print(result.getLbsLocationSiteList());
	}
	
	@Test
	public void testRetrieveLBSLocationsList_defect16824() throws Exception {
		AddressListContract contract = new AddressListContract();
		contract.setSessionHandle(crmSessionHandle);
		contract.setMdmId("17051");
		contract.setMdmLevel("Legal");
//		contract.setLbsAddressId("1420WVS");
		contract.setLbsFlag(true);
		contract.setFirstLoccationCall(true);
		contract.setNewQueryIndicator(true);
		contract.setStartRecordNumber(0);
		contract.setIncrement(40);
		
		AddressListResult result = service.retrieveLBSLocationList(contract);
		MiscTest.print(result.getLbsLocationSiteList());
	}
	
	@Test
	public void testRetrieveLBSBuildingTypes() throws Exception {
		AddressListContract contract = new AddressListContract();
		contract.setSessionHandle(crmSessionHandle);
		contract.setMdmId("023058159");
		contract.setMdmLevel("Global");
		contract.setNewQueryIndicator(true);
		contract.setStartRecordNumber(0);
		contract.setIncrement(40);
		
		AddressListResult result = service.retrieveLBSBuildingTypes(contract);
		for (LBSLocationBuildingType buildingType : LangUtil.notNull(result.getLbsLocationBuildingType())) {
			System.out.println("buildingType: " + buildingType.getBuildingType());
		}
	}

}
