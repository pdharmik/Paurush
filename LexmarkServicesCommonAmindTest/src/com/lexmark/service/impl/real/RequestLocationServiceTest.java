package com.lexmark.service.impl.real;

import static org.junit.Assert.assertNull;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.lexmark.contract.LocationReportingHierarchyContract;
import com.lexmark.result.RequestLocationsResult;
import com.lexmark.service.impl.real.requestService.RequestTypeServiceBase;

public class RequestLocationServiceTest extends RequestTypeServiceBase{

	@Test
	public void testRetrieveRequestLocations()throws Exception{
		LocationReportingHierarchyContract contract = new LocationReportingHierarchyContract();
		contract.setMdmId("1-101474S");
		contract.setMdmLevel("Siebel");

		RequestLocationsResult result = service.retrieveRequestLocations(contract);
		assertNull(result);
	}
	
	
	@Test
	public void testRetrieveRequestLocations_withVendorFlag()throws Exception{
		LocationReportingHierarchyContract contract = new LocationReportingHierarchyContract();
		contract.setMdmId("1-101474S");
		contract.setMdmLevel("Siebel");
		contract.setVendorFlag(true);

		RequestLocationsResult result = service.retrieveRequestLocations(contract);
		assertNull(result);
	}
	
	@Test
	public void testRetrieveRequestLocations_defect9523() throws Exception{
		LocationReportingHierarchyContract contract = new LocationReportingHierarchyContract();
		contract.setMdmLevel("Legal");
		contract.setMdmId("43800");
		contract.setVendorFlag(false);
		contract.setEntitlementEndDate("10/23/2013");
		contract.setIncrement(0);
		contract.setStartRecordNumber(0);
		contract.setNewQueryIndicator(false);
		contract.setSessionHandle(handle);
		
		Map<String,Object> filterCriteria = new HashMap<String,Object>();
		filterCriteria.put("serviceRequest.endDate", "10/23/2013 23:15:59");
		filterCriteria.put("serviceRequest.startDate", "10/20/2013 00:00:00");
		contract.setFilterCriteria(filterCriteria);
		
		RequestLocationsResult result = service.retrieveRequestLocations(contract);
		
		System.out.println(result.getAddressList() == null ? "Address list is null" : "Size: " + result.getAddressList().size());
	}
	
	
	@Test
	public void testRetrieveRequestLocations_defect10661() throws Exception{
		LocationReportingHierarchyContract contract = new LocationReportingHierarchyContract();
		contract.setMdmLevel("Legal");
		contract.setMdmId("43800");
		contract.setVendorFlag(false);
		contract.setEntitlementEndDate("12/13/2013");
		contract.setIncrement(0);
		contract.setStartRecordNumber(0);
		contract.setNewQueryIndicator(false);
//		contract.setSessionHandle(handle);
		
		Map<String,Object> filterCriteria = new HashMap<String,Object>();
		filterCriteria.put("serviceRequest.endDate", "12/13/2013 13:08:05");
		filterCriteria.put("serviceRequest.startDate", "11/13/2013 00:00:00");
		contract.setFilterCriteria(filterCriteria);
		
		RequestLocationsResult result = service.retrieveRequestLocations(contract);
		
		System.out.println(result.getAddressList() == null ? "Address list is null" : "Size: " + result.getAddressList().size());
	}
	
	
	@Test
	public void testRetrieveRequestLocations_defect11708() throws Exception{
		LocationReportingHierarchyContract contract = new LocationReportingHierarchyContract();
		contract.setMdmLevel("Global");
		contract.setMdmId("315000554");
		contract.setVendorFlag(false);
		contract.setEntitlementEndDate("02/12/2014");
		contract.setIncrement(0);
		contract.setStartRecordNumber(0);
		contract.setNewQueryIndicator(false);
		
//		Map<String,Object> filterCriteria = new HashMap<String,Object>();
//		filterCriteria.put("serviceRequest.endDate", "10/23/2013 23:15:59");
//		filterCriteria.put("serviceRequest.startDate", "09/23/2013 00:00:00");
//		contract.setFilterCriteria(filterCriteria);
		
		RequestLocationsResult result = service.retrieveRequestLocations(contract);
		
		System.out.println(result.getAddressList() == null ? "Address list is null" : "Size: " + result.getAddressList().size());
	}
	
	
	@Test
	public void testRetrieveRequestLocations_25() throws Exception{
		LocationReportingHierarchyContract contract = new LocationReportingHierarchyContract();
		contract.setMdmLevel("Legal");
		contract.setMdmId("50801");
		contract.setVendorFlag(false);
		contract.setEntitlementEndDate("02/17/2014");
		contract.setIncrement(0);
		contract.setStartRecordNumber(0);
		contract.setNewQueryIndicator(false);
		
		Map<String,Object> filterCriteria = new HashMap<String,Object>();
		filterCriteria.put("serviceRequest.endDate", "02/17/2014 16:56:24");
		filterCriteria.put("serviceRequest.startDate", "01/18/2014 05:30:00");
		contract.setFilterCriteria(filterCriteria);
		
		RequestLocationsResult result = service.retrieveRequestLocations(contract);
		
		System.out.println(result.getAddressList() == null ? "Address list is null" : "Size: " + result.getAddressList().size());
	}
	
	
	@Test
	public void testRetrieveRequestLocations_26() throws Exception{
		LocationReportingHierarchyContract contract = new LocationReportingHierarchyContract();
		contract.setMdmLevel("Legal");
		contract.setMdmId("50801");
		contract.setVendorFlag(false);
		contract.setIncrement(0);
		contract.setStartRecordNumber(0);
		contract.setNewQueryIndicator(false);
		
		RequestLocationsResult result = service.retrieveRequestLocations(contract);
		
		System.out.println(result.getAddressList() == null ? "Address list is null" : "Size: " + result.getAddressList().size());
	}
	
	@Test
	public void testRetrieveRequestLocations_29() throws Exception{
		LocationReportingHierarchyContract contract = new LocationReportingHierarchyContract();
		contract.setMdmLevel("Legal");
		contract.setMdmId("50801");
		contract.setVendorFlag(false);
		contract.setEntitlementEndDate("02/17/2014");
		contract.setIncrement(0);
		contract.setStartRecordNumber(0);
		contract.setNewQueryIndicator(false);
		
		RequestLocationsResult result = service.retrieveRequestLocations(contract);
		
		System.out.println(result.getAddressList() == null ? "Address list is null" : "Size: " + result.getAddressList().size());
	}

}
