package com.lexmark.util;

import static org.easymock.EasyMock.anyObject;
import static org.easymock.EasyMock.expect;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;

import org.easymock.EasyMock;
import org.easymock.EasyMockSupport;
import org.easymock.IArgumentMatcher;
import org.junit.Test;

import com.lexmark.constants.LexmarkPPConstants;
import com.lexmark.contract.FRUPartDetailContract;
import com.lexmark.contract.LocalizedSiebelLOVListContract;
import com.lexmark.contract.LocalizedSiebelValueContract;
import com.lexmark.contract.ProductImageContract;
import com.lexmark.contract.SiebelLOVListContract;
import com.lexmark.contract.TechnicianListContract;
import com.lexmark.domain.AccountContact;
import com.lexmark.domain.Activity;
import com.lexmark.domain.ActivityNote;
import com.lexmark.domain.AdditionalPaymentRequest;
import com.lexmark.domain.Debrief;
import com.lexmark.domain.ListOfValues;
import com.lexmark.domain.Part;
import com.lexmark.domain.PartLineItem;
import com.lexmark.domain.Payment;
import com.lexmark.domain.ServiceRequest;
import com.lexmark.domain.SiebelLocalization.SiebelLocalizationOptionEnum;
import com.lexmark.result.FRUPartDetailResult;
import com.lexmark.result.LocalizedSiebelLOVListResult;
import com.lexmark.result.LocalizedSiebelValueResult;
import com.lexmark.result.ProductImageResult;
import com.lexmark.result.SiebelLOVListResult;
import com.lexmark.result.TechnicianListResult;
import com.lexmark.service.api.GlobalService;
import com.lexmark.service.api.PartnerRequestsService;
import com.lexmark.service.api.ProductImageService;
import com.lexmark.service.api.ServiceRequestLocale;

public class ControllerUtilTest extends EasyMockSupport{
	
	/**
	 * test retrieveLocalizedLOVMapForSelection when retrieve empty Siebel LOV List (listResult == null)
	 * @throws Exception 
	 */
	@Test
	public void testRetrieveLocalizedLOVMapForSelectionWhenRetrieveEmptySiebelLOVList1() throws Exception {
		GlobalService globalService = createMock(GlobalService.class);
		expect(globalService.retrieveSiebelLOVList((SiebelLOVListContract)anyObject())).andReturn(null);
		replayAll();
		try {
			Map<String, String> valuePairs = 
					ControllerUtil.retrieveLocalizedLOVMapForSelection("ACTUAL_FAIL_CD", "code1", globalService, null, null);
			assertNotNull(valuePairs);
			assertTrue(valuePairs.isEmpty());
			verifyAll();
		} catch (Exception e) {
			fail("Unexpected exception throws out");
		}
	}
	
	
	/**
	 * test retrieveLocalizedLOVMapForSelection when retrieve empty Siebel LOV List (listResult.getLovList() == null)
	 * @throws Exception 
	 */
	@Test
	public void testRetrieveLocalizedLOVMapForSelectionWhenRetrieveEmptySiebelLOVList2() throws Exception {
		SiebelLOVListResult listResult = new SiebelLOVListResult();
		GlobalService globalService = createMock(GlobalService.class);
		expect(globalService.retrieveSiebelLOVList((SiebelLOVListContract)anyObject())).andReturn(listResult);
		replayAll();
		try {
			Map<String, String> valuePairs = 
					ControllerUtil.retrieveLocalizedLOVMapForSelection("ACTUAL_FAIL_CD", "code1", globalService, null, null);
			assertNotNull(valuePairs);
			assertTrue(valuePairs.isEmpty());
			verifyAll();
		} catch (Exception e) {
			fail("Unexpected exception throws out");
		}
	}
	
	
	
	/**
	 * test retrieveLocalizedLOVMapForSelection when retrieve empty Siebel LOV List (listResult.getLovList().size() == 0)
	 * @throws Exception 
	 */
	@Test
	public void testRetrieveLocalizedLOVMapForSelectionWhenRetrieveEmptySiebelLOVList3() throws Exception {
		SiebelLOVListResult listResult = new SiebelLOVListResult();
		List<ListOfValues> lovList = new ArrayList<ListOfValues>();
		listResult.setLovList(lovList);
		GlobalService globalService = createMock(GlobalService.class);
		expect(globalService.retrieveSiebelLOVList((SiebelLOVListContract)anyObject())).andReturn(listResult);
		replayAll();
		try {
			Map<String, String> valuePairs = 
					ControllerUtil.retrieveLocalizedLOVMapForSelection("ACTUAL_FAIL_CD", "code1", globalService, null, null);
			assertNotNull(valuePairs);
			assertTrue(valuePairs.isEmpty());
			verifyAll();
		} catch (Exception e) {
			fail("Unexpected exception throws out");
		}
	}
	
	
	/**
	 * test retrieveLocalizedLOVMapForSelection when serviceRequestLocaleService.retrieveLocalizedSiebelLOVList returns null
	 * @throws Exception 
	 */
	@Test
	public void testRetrieveLocalizedLOVMapForSelectionWhenRetrieveEmptyLocalizedSiebelLOVList() throws Exception{
		GlobalService globalService = createMock(GlobalService.class);
		expect(globalService.retrieveSiebelLOVList((SiebelLOVListContract)anyObject())).andReturn(getSiebelLOVListResultWithValues());
		ServiceRequestLocale serviceRequestLocaleService = createMock(ServiceRequestLocale.class);
		expect(serviceRequestLocaleService.retrieveLocalizedSiebelLOVList((LocalizedSiebelLOVListContract)(anyObject()))).andReturn(null);
		replayAll();
		try {
			Map<String, String> valuePairs = 
					ControllerUtil.retrieveLocalizedLOVMapForSelection("ACTUAL_FAIL_CD", "code1", globalService, serviceRequestLocaleService, Locale.US);
			assertNotNull(valuePairs);
			assertTrue(!valuePairs.isEmpty());
			Iterator<Entry<String,String>> iter = valuePairs.entrySet().iterator();
			Entry<String, String> entry = iter.next();
			assertEquals(entry.getKey(), "value1");
			assertEquals(entry.getValue(), "value1");
			entry = iter.next();
			assertEquals(entry.getKey(), "value2");
			assertEquals(entry.getValue(), "value2");			
			verifyAll();
		} catch (Exception e) {
			fail("Unexpected exception throws out");
		}
	}
	
	
	
	/**
	 * test retrieveLocalizedLOVMapForSelection when serviceRequestLocaleService.retrieveLocalizedSiebelLOVList returns null
	 * @throws Exception 
	 */
	@Test
	public void testRetrieveLocalizedLOVMapForSelectionWhenRetrieveLocalizedSiebelLOVList() throws Exception{
		GlobalService globalService = createMock(GlobalService.class);
		expect(globalService.retrieveSiebelLOVList((SiebelLOVListContract)anyObject())).andReturn(getSiebelLOVListResultWithValues());
		ServiceRequestLocale serviceRequestLocaleService = createMock(ServiceRequestLocale.class);
		expect(serviceRequestLocaleService.retrieveLocalizedSiebelLOVList((LocalizedSiebelLOVListContract)(anyObject()))).andReturn(getLocalizedSiebelLOVListResult());
		replayAll();
		try {
			Map<String, String> valuePairs = 
					ControllerUtil.retrieveLocalizedLOVMapForSelection("ACTUAL_FAIL_CD", "code1", globalService, serviceRequestLocaleService, Locale.US);
			assertNotNull(valuePairs);
			assertTrue(!valuePairs.isEmpty());
			Iterator<Entry<String,String>> iter = valuePairs.entrySet().iterator();
			Entry<String, String> entry = iter.next();
			assertEquals(entry.getKey(), "value1");
			assertEquals(entry.getValue(), "localized_name1");
			entry = iter.next();
			assertEquals(entry.getKey(), "value2");
			assertEquals(entry.getValue(), "localized_name2");			
			verifyAll();
		} catch (Exception e) {
			fail("Unexpected exception throws out");
		}
	}
	
	
	/**
	 * test getLocalizedSiebelValue when retrieveLocalizedSiebelValue returns null 
	 */
	@Test
	public void testGetLocalizedSiebelValueWhenRetrieveLocalizedSiebelValueReturnsNull(){
		ServiceRequestLocale serviceRequestLocaleService = createMock(ServiceRequestLocale.class);
		expect(serviceRequestLocaleService.retrieveLocalizedSiebelValue((LocalizedSiebelValueContract)(anyObject()))).andReturn(null);
		replayAll();
		ListOfValues lov = new ListOfValues();
		lov.setValue("value1");
		ListOfValues returnLov = 
				ControllerUtil.getLocalizedSiebelValue(lov, serviceRequestLocaleService, null);
		assertNotNull(returnLov);	
		assertEquals(returnLov.getName(), returnLov.getValue());
		verifyAll();
	}
	
	
	/**
	 * test getLocalizedSiebelValue when retrieveLocalizedSiebelValue returns empty result 
	 */
	@Test
	public void testGetLocalizedSiebelValueWhenRetrieveLocalizedSiebelValueReturnsEmpty(){
		ServiceRequestLocale serviceRequestLocaleService = createMock(ServiceRequestLocale.class);
		expect(serviceRequestLocaleService.retrieveLocalizedSiebelValue((LocalizedSiebelValueContract)(anyObject()))).andReturn(new LocalizedSiebelValueResult());
		replayAll();
		ListOfValues lov = new ListOfValues();
		lov.setValue("value1");
		ListOfValues returnLov = 
				ControllerUtil.getLocalizedSiebelValue(lov, serviceRequestLocaleService, null);
		assertNotNull(returnLov);	
		assertEquals(returnLov.getName(), returnLov.getValue());
		verifyAll();
	}
	
	
	/**
	 * test getLocalizedSiebelValue when retrieveLocalizedSiebelValue returns empty lov name 
	 */
	@Test
	public void testGetLocalizedSiebelValueWhenRetrieveLocalizedSiebelValueReturnsEmptyLOVName(){
		LocalizedSiebelValueResult result = new LocalizedSiebelValueResult();
		ListOfValues resultLov = new ListOfValues();
		resultLov.setValue("value1");
		result.setLovValue(resultLov);
		ServiceRequestLocale serviceRequestLocaleService = createMock(ServiceRequestLocale.class);
		expect(serviceRequestLocaleService.retrieveLocalizedSiebelValue((LocalizedSiebelValueContract)(anyObject()))).andReturn(result);
		replayAll();
		ListOfValues lov = new ListOfValues();
		lov.setValue("value1");
		ListOfValues returnLov = 
				ControllerUtil.getLocalizedSiebelValue(lov, serviceRequestLocaleService, null);
		assertNotNull(returnLov);	
		assertEquals(returnLov.getName(), returnLov.getValue());
		verifyAll();
	}
	
	
	
	/**
	 * test getLocalizedSiebelValue when retrieveLocalizedSiebelValue returns empty lov name 
	 */
	@Test
	public void testGetLocalizedSiebelValueWhenRetrieveLocalizedSiebelValue(){
		LocalizedSiebelValueResult result = new LocalizedSiebelValueResult();
		ListOfValues resultLov = new ListOfValues();
		resultLov.setValue("value1");
		resultLov.setName("name1");
		result.setLovValue(resultLov);
		ServiceRequestLocale serviceRequestLocaleService = createMock(ServiceRequestLocale.class);
		expect(serviceRequestLocaleService.retrieveLocalizedSiebelValue((LocalizedSiebelValueContract)(anyObject()))).andReturn(result);
		replayAll();
		ListOfValues lov = new ListOfValues();
		lov.setValue("value1");
		ListOfValues returnLov = 
				ControllerUtil.getLocalizedSiebelValue(lov, serviceRequestLocaleService, null);
		assertNotNull(returnLov);	
		assertEquals(returnLov.getName(), "name1");
		assertEquals(returnLov.getValue(), "value1");
		verifyAll();
	}

	
	@Test
	public void testRetrieveLocalizedLOVMap(){
		ServiceRequestLocale serviceRequestLocaleService = createMock(ServiceRequestLocale.class);
		expect(serviceRequestLocaleService.retrieveLocalizedSiebelLOVList((LocalizedSiebelLOVListContract)(anyObject()))).andReturn(getLocalizedSiebelLOVListResult());
		replayAll();
		Map<String, String> result = ControllerUtil.retrieveLocalizedLOVMap(null, null, serviceRequestLocaleService, null);
		Iterator<Entry<String,String>> iter = result.entrySet().iterator();
		Entry<String, String> entry = iter.next();
		assertEquals(entry.getKey(), "value1");
		assertEquals(entry.getValue(), "localized_name1");
		entry = iter.next();
		assertEquals(entry.getKey(), "value2");
		assertEquals(entry.getValue(), "localized_name2");			
		verifyAll();		
	}
	
	
	@Test
	public void testRetrieveProductImageUrl() throws Exception{
		ProductImageResult productImageResult = new ProductImageResult();
		productImageResult.setProductImageUrl("url1");
		ProductImageService productImageService = createMock(ProductImageService.class);
		expect(productImageService.retrieveProductImageUrl((ProductImageContract)anyObject(), (String)anyObject())).andReturn(productImageResult);
		replayAll();
		String url = ControllerUtil.retrieveProductImageUrl(productImageService, "productTLI");
		assertEquals(url, "url1");
		verifyAll();
	}
	
	
	
	/**
	 * test retrieveProductImageUrl when exceptions throws out
	 * @throws Exception
	 */
	@Test
	public void testRetrieveProductImageUrlWhenExceptions() throws Exception{
		ProductImageService productImageService = createMock(ProductImageService.class);
		expect(productImageService.retrieveProductImageUrl((ProductImageContract)anyObject(), (String)anyObject())).andThrow(new Exception());
		replayAll();
		try{
			String url = ControllerUtil.retrieveProductImageUrl(productImageService, "productTLI");
			fail("failed here, a runtime exception should be throw out");
		}
		catch(RuntimeException e){
			assertTrue(e.getMessage().startsWith("Can not get imageURL by part number:"));
		}
		verifyAll();
	}
	
	
	/**
	 * test retrieveFRUPartPRF when retrieveFRUPart returns empty part
	 * @throws Exception 
	 */
	@Test
	public void testRetrieveFRUPartPRFWhenRetrieveFRUPartReturnsEmptyPart() throws Exception{
		FRUPartDetailContract contract = new FRUPartDetailContract();
		FRUPartDetailResult result = new FRUPartDetailResult();
		PartnerRequestsService requestService = createMock(PartnerRequestsService.class);
		expect(requestService.retrieveFRUPart(contract)).andReturn(result);
		replayAll();
		FRUPartDetailResult retResult = ControllerUtil.retrieveFRUPartPRF(requestService, contract);
		assertNotNull(retResult);
		assertNull(retResult.getPart());
		verifyAll();
	}
	
	
	/**
	 * test retrieveFRUPartPRF when retrieveFRUPart returns part
	 * @throws Exception 
	 */
	@Test
	public void testRetrieveFRUPartPRFWhenRetrieveFRUPartReturnsPart() throws Exception{
		FRUPartDetailContract contract = new FRUPartDetailContract();
		PartnerRequestsService requestService = createMock(PartnerRequestsService.class);
		expect(requestService.retrieveFRUPart(contract)).andReturn(getFRUPartDetailResult());
		replayAll();
		FRUPartDetailResult retResult = ControllerUtil.retrieveFRUPartPRF(requestService, contract);
		assertNotNull(retResult);
		assertNotNull(retResult.getPart());
		assertEquals(retResult.getPart().getPartId(), "partId1");
		verifyAll();
	}	
	
	
	/**
	 * test retrieveFRUPartPRF when retrieveFRUPart returns part with replacement Part Number
	 * @throws Exception 
	 */
	@Test
	public void testRetrieveFRUPartPRFWhenRetrieveFRUPartReturnsPartWithReplacementPartNumber() throws Exception{
		FRUPartDetailContract contract1 = new FRUPartDetailContract();
		contract1.setPartNumber("0001");
		contract1.setReplacementFlag(true);
		PartnerRequestsService requestService = createMock(PartnerRequestsService.class);
		expect(requestService.retrieveFRUPart(matchPartNumberAndReplacementFlag(contract1))).andReturn(getFRUPartDetailResultWithReplacementPartNumber());
		FRUPartDetailContract contract2 = new FRUPartDetailContract();
		contract2.setPartNumber("replacementPart1");
		contract2.setReplacementFlag(true);		
		expect(requestService.retrieveFRUPart(matchPartNumberAndReplacementFlag(contract2))).andReturn(getFRUPartDetailResultWithReplacementPartNumber2());
		replayAll();
		FRUPartDetailResult retResult = ControllerUtil.retrieveFRUPartPRF(requestService, contract1);
		assertNotNull(retResult);
		assertNotNull(retResult.getPart());
		assertEquals(retResult.getPart().getPartId(), "partId2");
		verifyAll();
	}
	
	
	@Test
	public void testLocalizeMessage(){
		String result = ControllerUtil.localizeMessage("button.submit", null, Locale.US);
		assertEquals("SUBMIT", result);
		String result2 = ControllerUtil.localizeMessage("button.submit", LexmarkPPConstants.MESSAGE_BUNDLE_NAME, Locale.US);
		assertEquals("SUBMIT", result2);
	}
	
	
	@Test
	public void testLocalizeActivity(){
		Activity activity = new Activity();
		activity.setActivityStatus(new ListOfValues());
		activity.setActivitySubStatus(new ListOfValues());
		ServiceRequest serviceRequest = new ServiceRequest();
		serviceRequest.setServiceRequestType(new ListOfValues());
		activity.setServiceRequest(serviceRequest);
		activity.setActualFailureCode(new ListOfValues());
		activity.setAdditionalPaymentRequestList(new ArrayList<AdditionalPaymentRequest>());
		activity.setDebrief(new Debrief());
		
		LocalizedSiebelValueResult result = new LocalizedSiebelValueResult();
		ListOfValues resultLov = new ListOfValues();
		resultLov.setValue("value1");
		resultLov.setName("name1");
		result.setLovValue(resultLov);
		ServiceRequestLocale serviceRequestLocaleService = createMock(ServiceRequestLocale.class);
		expect(serviceRequestLocaleService.retrieveLocalizedSiebelValue((LocalizedSiebelValueContract)(anyObject()))).andReturn(result).anyTimes();
		expect(serviceRequestLocaleService.retrieveLocalizedSiebelLOVList((LocalizedSiebelLOVListContract)anyObject())).andReturn(new LocalizedSiebelLOVListResult());
		replayAll();
		
		ControllerUtil.localizeActivity(activity, serviceRequestLocaleService, Locale.US);
		assertEquals(activity.getActivityStatus(), result.getLovValue());
		assertEquals(activity.getActivitySubStatus(), result.getLovValue());
		assertEquals(activity.getServiceRequest().getServiceRequestType(), result.getLovValue());
		assertEquals(activity.getActualFailureCode(),result.getLovValue());
		verifyAll();
	}
	
	
	@Test
	public void testLocalizeDebriefInfoActivity(){
		Activity activity = new Activity();
		Debrief debrief = new Debrief();
		ListOfValues dc = new ListOfValues();
		debrief.setDeviceCondition(dc);
		ListOfValues rc = new ListOfValues();
		debrief.setResolutionCode(rc);
		ListOfValues pc = new ListOfValues();
		debrief.setActualFailureCode(pc);
		ListOfValues tu = new ListOfValues();
		debrief.setTravelUnitOfMeasure(tu);		
		activity.setDebrief(debrief);
		
		LocalizedSiebelValueResult result = new LocalizedSiebelValueResult();
		ListOfValues resultLov = new ListOfValues();
		resultLov.setValue("value1");
		resultLov.setName("name1");
		result.setLovValue(resultLov);
		ServiceRequestLocale serviceRequestLocaleService = createMock(ServiceRequestLocale.class);
		expect(serviceRequestLocaleService.retrieveLocalizedSiebelValue((LocalizedSiebelValueContract)(anyObject()))).andReturn(result).anyTimes();
		replayAll();	
		
		ControllerUtil.localizeDebriefInfoActivity(activity, serviceRequestLocaleService, Locale.US);
		assertEquals(activity.getDebrief().getDeviceCondition(), result.getLovValue());
		assertEquals(activity.getDebrief().getResolutionCode(), result.getLovValue());
		assertEquals(activity.getDebrief().getActualFailureCode(), result.getLovValue());
		assertEquals(activity.getDebrief().getTravelUnitOfMeasure(),result.getLovValue());
		verifyAll();
	}
	
	
	@Test
	public void testBatchLocalizeActivityStatusWhenMapNotEmpty(){
		List<Activity> activityList = new ArrayList<Activity>();
		Activity activity = new Activity();
		activityList.add(activity);
		ListOfValues activityStatus = new ListOfValues();
		activityStatus.setValue("value1");
		activity.setActivityStatus(activityStatus);
		Map<String, String> activityStatusMap = new HashMap<String, String>();
		activityStatusMap.put("value1", "value2");
		ControllerUtil.batchLocalizeActivityStatus(activityList, activityStatusMap);
		assertEquals(activity.getActivityStatus().getName(), "value2");
	}


	@Test
	public void testBatchLocalizeActivityStatusWhenMapEmpty(){
		List<Activity> activityList = new ArrayList<Activity>();
		Activity activity = new Activity();
		activityList.add(activity);
		ListOfValues activityStatus = new ListOfValues();
		activityStatus.setValue("value1");
		activity.setActivityStatus(activityStatus);
		Map<String, String> activityStatusMap = new HashMap<String, String>();
		ControllerUtil.batchLocalizeActivityStatus(activityList, activityStatusMap);
		assertEquals(activity.getActivityStatus().getName(), "value1");
	}
	
	
	@Test
	public void testBatchLocalizeActivitySubStatusWhenMapNotEmpty(){
		List<Activity> activityList = new ArrayList<Activity>();
		Activity activity = new Activity();
		activityList.add(activity);
		ListOfValues activityStatus = new ListOfValues();
		activityStatus.setValue("value1");
		activity.setActivitySubStatus(activityStatus);
		Map<String, String> activityStatusMap = new HashMap<String, String>();
		activityStatusMap.put("value1", "value2");
		ControllerUtil.batchLocalizeActivitySubStatus(activityList, activityStatusMap);
		assertEquals(activity.getActivitySubStatus().getName(), "value2");		
	}
	
	
	@Test
	public void testBatchLocalizeActivitySubStatusWhenMapEmpty(){
		List<Activity> activityList = new ArrayList<Activity>();
		Activity activity = new Activity();
		activityList.add(activity);
		ListOfValues activityStatus = new ListOfValues();
		activityStatus.setValue("value1");
		activity.setActivitySubStatus(activityStatus);
		Map<String, String> activityStatusMap = new HashMap<String, String>();
		ControllerUtil.batchLocalizeActivitySubStatus(activityList, activityStatusMap);
		assertEquals(activity.getActivitySubStatus().getName(), "value1");
	}
	
	

	@Test
	public void testBatchLocalizeActivityProblemCodeWhenMapNotEmpty(){
		List<Activity> activityList = new ArrayList<Activity>();
		Activity activity = new Activity();
		activityList.add(activity);
		ListOfValues activityStatus = new ListOfValues();
		activityStatus.setValue("value1");
		activity.setActualFailureCode(activityStatus);
		Map<String, String> activityStatusMap = new HashMap<String, String>();
		activityStatusMap.put("value1", "value2");
		ControllerUtil.batchLocalizeActivityProblemCode(activityList, activityStatusMap);
		assertEquals(activity.getActualFailureCode().getName(), "value2");		
	}
	
	
	@Test
	public void testBatchLocalizeActivityProblemCodeWhenMapEmpty(){
		List<Activity> activityList = new ArrayList<Activity>();
		Activity activity = new Activity();
		activityList.add(activity);
		ListOfValues activityStatus = new ListOfValues();
		activityStatus.setValue("value1");
		activity.setActualFailureCode(activityStatus);
		Map<String, String> activityStatusMap = new HashMap<String, String>();
		ControllerUtil.batchLocalizeActivityProblemCode(activityList, activityStatusMap);
		assertEquals(activity.getActualFailureCode().getName(), "value1");
	}	
	
	
	
	@Test
	public void testBatchLocalizeServiceTypeWhenMapEmpty(){
		List<Activity> activityList = new ArrayList<Activity>();
		Activity activity = new Activity();
		activityList.add(activity);
		ListOfValues activityStatus = new ListOfValues();
		activityStatus.setValue("value1");
		activity.setActivityType(activityStatus);
		Map<String, String> activityStatusMap = new HashMap<String, String>();
		ControllerUtil.batchLocalizeServiceType(activityList, activityStatusMap);
		assertEquals(activity.getActivityType().getName(), "value1");
	}

	
	
	@Test
	public void testBatchLocalizeServiceTypeWhenMapNotEmpty(){
		List<Activity> activityList = new ArrayList<Activity>();
		Activity activity = new Activity();
		activityList.add(activity);
		ListOfValues activityStatus = new ListOfValues();
		activityStatus.setValue("value1");
		activity.setActivityType(activityStatus);
		Map<String, String> activityStatusMap = new HashMap<String, String>();
		activityStatusMap.put("value1", "value2");
		ControllerUtil.batchLocalizeServiceType(activityList, activityStatusMap);
		assertEquals(activity.getActivityType().getName(), "value2");		
	}
	
	
	
	@Test
	public void testBatchLocalizeServiceRequestTypeWhenMapEmpty(){
		List<Activity> activityList = new ArrayList<Activity>();
		Activity activity = new Activity();
		activityList.add(activity);
		ListOfValues serviceRequestType = new ListOfValues();
		serviceRequestType.setValue("value1");
		ServiceRequest serviceRequest = new ServiceRequest();
		serviceRequest.setServiceRequestType(serviceRequestType);
		activity.setServiceRequest(serviceRequest);
		Map<String, String> activityStatusMap = new HashMap<String, String>();
		ControllerUtil.batchLocalizeServiceRequestType(activityList, activityStatusMap);
		assertEquals(activity.getServiceRequest().getServiceRequestType().getName(), "value1");
	}

	
	
	@Test
	public void testBatchLocalizeServiceRequestTypeWhenMapNotEmpty(){
		List<Activity> activityList = new ArrayList<Activity>();
		Activity activity = new Activity();
		activityList.add(activity);
		ListOfValues serviceRequestType = new ListOfValues();
		serviceRequestType.setValue("value1");
		ServiceRequest serviceRequest = new ServiceRequest();
		serviceRequest.setServiceRequestType(serviceRequestType);
		activity.setServiceRequest(serviceRequest);
		Map<String, String> activityStatusMap = new HashMap<String, String>();
		activityStatusMap.put("value1", "value2");
		ControllerUtil.batchLocalizeServiceRequestType(activityList, activityStatusMap);
		assertEquals(activity.getServiceRequest().getServiceRequestType().getName(), "value2");				
	}	

	
	@Test
	public void testBatchLocalizePart(){
		List<PartLineItem> partLineItemList = new ArrayList<PartLineItem>();
		PartLineItem part = new PartLineItem();
		partLineItemList.add(part);
		ListOfValues carrier = new ListOfValues();
		carrier.setValue("carrierValue");
		part.setCarrier(carrier);
		ListOfValues partDisposition = new ListOfValues();
		partDisposition.setValue("partDispositionValue");
		part.setPartDisposition(partDisposition);
		ListOfValues lineStatus = new ListOfValues();
		lineStatus.setValue("lineStatusValue");
		part.setLineStatus(lineStatus);
		ListOfValues errorCode1 = new ListOfValues();
		errorCode1.setValue("errorCode1Value");
		part.setErrorCode1(errorCode1);		
		ListOfValues errorCode2 = new ListOfValues();
		errorCode2.setValue("errorCode2Value");
		part.setErrorCode2(errorCode2);	
		ServiceRequestLocale serviceRequestLocaleService = createMock(ServiceRequestLocale.class);
		
		LocalizedSiebelLOVListResult carrierResult = new LocalizedSiebelLOVListResult();
		List<ListOfValues> carrierLocalizedSiebelLOVList = new ArrayList<ListOfValues>();
		ListOfValues carrierLov = new ListOfValues();
		carrierLov.setName("carrierName");
		carrierLov.setValue("carrierValue");
		carrierLocalizedSiebelLOVList.add(carrierLov);
		carrierResult.setLocalizedSiebelLOVList(carrierLocalizedSiebelLOVList);
		LocalizedSiebelLOVListContract carrierContract = new LocalizedSiebelLOVListContract();
		carrierContract.setLovListName(SiebelLocalizationOptionEnum.PARTNER_CARRIER.getValue());
		expect(serviceRequestLocaleService.retrieveLocalizedSiebelLOVList(matchLocalizedSiebelLOVListContractByLov(carrierContract))).andReturn(carrierResult);
		
		LocalizedSiebelLOVListResult partDispositionResult = new LocalizedSiebelLOVListResult();
		List<ListOfValues> partDispositionLocalizedSiebelLOVList = new ArrayList<ListOfValues>();
		ListOfValues partDispositionLov = new ListOfValues();
		partDispositionLov.setName("partDispositionName");
		partDispositionLov.setValue("partDispositionValue");
		partDispositionLocalizedSiebelLOVList.add(partDispositionLov);
		partDispositionResult.setLocalizedSiebelLOVList(partDispositionLocalizedSiebelLOVList);
		LocalizedSiebelLOVListContract partDispositionContract = new LocalizedSiebelLOVListContract();
		partDispositionContract.setLovListName(SiebelLocalizationOptionEnum.PARTNER_PART_STATUS.getValue());
		expect(serviceRequestLocaleService.retrieveLocalizedSiebelLOVList(matchLocalizedSiebelLOVListContractByLov(partDispositionContract))).andReturn(partDispositionResult);		
		
		LocalizedSiebelLOVListResult lineStatusResult = new LocalizedSiebelLOVListResult();
		List<ListOfValues> lineStatusLocalizedSiebelLOVList = new ArrayList<ListOfValues>();
		ListOfValues lineStatusLov = new ListOfValues();
		lineStatusLov.setName("lineStatusName");
		lineStatusLov.setValue("lineStatusValue");
		lineStatusLocalizedSiebelLOVList.add(lineStatusLov);
		lineStatusResult.setLocalizedSiebelLOVList(lineStatusLocalizedSiebelLOVList);
		LocalizedSiebelLOVListContract lineStatusContract = new LocalizedSiebelLOVListContract();
		lineStatusContract.setLovListName(SiebelLocalizationOptionEnum.PARTNER_ORDER_LINE_ITEM_STATUS.getValue());
		expect(serviceRequestLocaleService.retrieveLocalizedSiebelLOVList(matchLocalizedSiebelLOVListContractByLov(lineStatusContract))).andReturn(lineStatusResult);
		
		LocalizedSiebelLOVListResult partSourceResult = new LocalizedSiebelLOVListResult();
		List<ListOfValues> partSourceLocalizedSiebelLOVList = new ArrayList<ListOfValues>();
		ListOfValues partSourceLov = new ListOfValues();
		partSourceLov.setName("partSourceName");
		partSourceLov.setValue("partSourceValue");
		partSourceLocalizedSiebelLOVList.add(partSourceLov);
		partSourceResult.setLocalizedSiebelLOVList(partSourceLocalizedSiebelLOVList);
		LocalizedSiebelLOVListContract partSourceContract = new LocalizedSiebelLOVListContract();
		partSourceContract.setLovListName(SiebelLocalizationOptionEnum.PARTNER_DEBRIEF_PART_SOURCE.getValue());
		expect(serviceRequestLocaleService.retrieveLocalizedSiebelLOVList(matchLocalizedSiebelLOVListContractByLov(partSourceContract))).andReturn(partSourceResult);
		
		LocalizedSiebelLOVListResult errorCodeOneResult = new LocalizedSiebelLOVListResult();
		List<ListOfValues> errorCodeOneLocalizedSiebelLOVList = new ArrayList<ListOfValues>();
		ListOfValues errorCodeOneLov = new ListOfValues();
		errorCodeOneLov.setName("errorCode1Name");
		errorCodeOneLov.setValue("errorCode1Value");
		errorCodeOneLocalizedSiebelLOVList.add(errorCodeOneLov);
		errorCodeOneResult.setLocalizedSiebelLOVList(errorCodeOneLocalizedSiebelLOVList);
		LocalizedSiebelLOVListContract errorCodeOneContract = new LocalizedSiebelLOVListContract();
		errorCodeOneContract.setLovListName(SiebelLocalizationOptionEnum.PARTNER_DEBRIEF_ERROR_CODE_1.getValue());
		expect(serviceRequestLocaleService.retrieveLocalizedSiebelLOVList(matchLocalizedSiebelLOVListContractByLov(errorCodeOneContract))).andReturn(errorCodeOneResult);
		
		LocalizedSiebelLOVListResult errorCodeTwoResult = new LocalizedSiebelLOVListResult();
		List<ListOfValues> errorCodeTwoLocalizedSiebelLOVList = new ArrayList<ListOfValues>();
		ListOfValues errorCodeTwoLov = new ListOfValues();
		errorCodeTwoLov.setName("errorCode2Name");
		errorCodeTwoLov.setValue("errorCode2Value");
		errorCodeTwoLocalizedSiebelLOVList.add(errorCodeTwoLov);
		errorCodeTwoResult.setLocalizedSiebelLOVList(errorCodeTwoLocalizedSiebelLOVList);
		LocalizedSiebelLOVListContract errorCodeTwoContract = new LocalizedSiebelLOVListContract();
		errorCodeTwoContract.setLovListName(SiebelLocalizationOptionEnum.PARTNER_DEBRIEF_ERROR_CODE_2.getValue());
		expect(serviceRequestLocaleService.retrieveLocalizedSiebelLOVList(matchLocalizedSiebelLOVListContractByLov(errorCodeTwoContract))).andReturn(errorCodeTwoResult);
		replayAll();
		
		try {
			ControllerUtil.batchLocalizePart(partLineItemList, serviceRequestLocaleService, Locale.US);
		} catch (Exception e) {
			fail("unexpected exception throws out");
		}
		assertEquals(part.getCarrier().getName(), "carrierName");
		assertEquals(part.getPartDisposition().getName(), "partDispositionName");
		assertEquals(part.getLineStatus().getName(), "lineStatusName");
		assertEquals(part.getErrorCode1().getName(), "errorCode1Name");
		assertEquals(part.getErrorCode2().getName(), "errorCode2Name");
		verifyAll();
	}
	
	
	
	@Test
	public void testBatchLocalizePaymentRequestWhenNoMatch(){
		List<Activity> paymentRequestList = new ArrayList<Activity>();
		Activity activity = new Activity();
		Payment payment = new Payment();
		activity.setPayment(payment);
		paymentRequestList.add(activity);
		ListOfValues paymentStatus = new ListOfValues();
		paymentStatus.setValue("value1");
		payment.setPaymentStatus(paymentStatus);
		ServiceRequest serviceRequest = new ServiceRequest();
		serviceRequest.setServiceRequestStatus("value1");
		activity.setServiceRequest(serviceRequest);
		
		ServiceRequestLocale serviceRequestLocaleService = createMock(ServiceRequestLocale.class);
		LocalizedSiebelLOVListResult result = new LocalizedSiebelLOVListResult();
		List<ListOfValues> localizedSiebelLOVList = new ArrayList<ListOfValues>();
		result.setLocalizedSiebelLOVList(localizedSiebelLOVList);
		expect(serviceRequestLocaleService.retrieveLocalizedSiebelLOVList((LocalizedSiebelLOVListContract)anyObject())).andReturn(result).times(2);
		
		replayAll();
		ControllerUtil.batchLocalizePaymentRequest(paymentRequestList, serviceRequestLocaleService, Locale.US);
		assertEquals(paymentStatus.getName(), paymentStatus.getValue());
		assertEquals(serviceRequest.getServiceRequestStatus(), "value1");
		verifyAll();
	}
	
	
	@Test
	public void testBatchLocalizePaymentRequestWhenMatch(){
		List<Activity> paymentRequestList = new ArrayList<Activity>();
		Activity activity = new Activity();
		Payment payment = new Payment();
		activity.setPayment(payment);
		paymentRequestList.add(activity);
		ListOfValues paymentStatus = new ListOfValues();
		paymentStatus.setValue("psValue1");
		payment.setPaymentStatus(paymentStatus);
		ServiceRequest serviceRequest = new ServiceRequest();
		serviceRequest.setServiceRequestStatus("srsvalue1");
		activity.setServiceRequest(serviceRequest);
		
		ServiceRequestLocale serviceRequestLocaleService = createMock(ServiceRequestLocale.class);
		LocalizedSiebelLOVListResult result = new LocalizedSiebelLOVListResult();
		List<ListOfValues> localizedSiebelLOVList = new ArrayList<ListOfValues>();
		ListOfValues lov1 = new ListOfValues();
		lov1.setValue("psValue1");
		lov1.setName("psName1");
		ListOfValues lov2 = new ListOfValues();
		lov2.setValue("srsvalue1");
		lov2.setName("srsName1");		
		localizedSiebelLOVList.add(lov1);
		localizedSiebelLOVList.add(lov2);
		result.setLocalizedSiebelLOVList(localizedSiebelLOVList);
		expect(serviceRequestLocaleService.retrieveLocalizedSiebelLOVList((LocalizedSiebelLOVListContract)anyObject())).andReturn(result).times(2);
		
		replayAll();
		ControllerUtil.batchLocalizePaymentRequest(paymentRequestList, serviceRequestLocaleService, Locale.US);
		assertEquals(paymentStatus.getName(), "psName1");
		assertEquals(serviceRequest.getServiceRequestStatus(), "srsName1");
		verifyAll();
	}	
	
	
	@Test
	public void testRetrieveTechnicianListSorted() throws Exception{
		TechnicianListResult technicianListResult = new TechnicianListResult();
		List<AccountContact> accountContactList = new ArrayList<AccountContact>();
		AccountContact contact1 = new AccountContact();
		contact1.setFirstName("b");
		contact1.setLastName("a");
		
		AccountContact contact2 = new AccountContact();
		contact2.setFirstName("c");
		contact2.setLastName("a");
		
		AccountContact contact3 = new AccountContact();
		contact3.setFirstName("b");
		contact3.setLastName("c");
		
		AccountContact contact4 = new AccountContact();
		contact4.setFirstName("c");
		contact4.setLastName("c");
		
		accountContactList.add(contact1);
		accountContactList.add(contact2);
		accountContactList.add(contact3);
		accountContactList.add(contact4);
		technicianListResult.setAccountContactList(accountContactList);
		
		PartnerRequestsService partnerRequestsService = createMock(PartnerRequestsService.class);
		expect(partnerRequestsService.retrieveTechnicianList((TechnicianListContract)anyObject())).andReturn(technicianListResult);
		replayAll();
		List<AccountContact> results = ControllerUtil.retrieveTechnicianListSorted(null, partnerRequestsService);
		assertEquals(results.get(0).getLastName(), "a");
		assertEquals(results.get(0).getFirstName(), "b");
		assertEquals(results.get(1).getLastName(), "a");
		assertEquals(results.get(1).getFirstName(), "c");
		assertEquals(results.get(2).getLastName(), "c");
		assertEquals(results.get(2).getFirstName(), "b");
		assertEquals(results.get(3).getLastName(), "c");
		assertEquals(results.get(3).getFirstName(), "c");
		
	}
	
	
	
	@Test
	public void testBatchLocalizeAdditionPaymentsWhenNoMatch(){
		List<AdditionalPaymentRequest> additionalPaymentRequestList = new ArrayList<AdditionalPaymentRequest>();
		AdditionalPaymentRequest payment = new AdditionalPaymentRequest();
		ListOfValues paymentType = new ListOfValues();
		paymentType.setValue("value1");
		payment.setPaymentType(paymentType);
		additionalPaymentRequestList.add(payment);
		
		ServiceRequestLocale serviceRequestLocaleService = createMock(ServiceRequestLocale.class);
		LocalizedSiebelLOVListResult result = new LocalizedSiebelLOVListResult();
		expect(serviceRequestLocaleService.retrieveLocalizedSiebelLOVList((LocalizedSiebelLOVListContract)anyObject())).andReturn(result);
		replayAll();
		ControllerUtil.batchLocalizeAdditionPayments(additionalPaymentRequestList, serviceRequestLocaleService, Locale.US);
		assertEquals(paymentType.getName(), "value1");
		verifyAll();
	}
	
	
	
	@Test
	public void testBatchLocalizeAdditionPaymentsWhenMatch(){
		List<AdditionalPaymentRequest> additionalPaymentRequestList = new ArrayList<AdditionalPaymentRequest>();
		AdditionalPaymentRequest payment = new AdditionalPaymentRequest();
		ListOfValues paymentType = new ListOfValues();
		paymentType.setValue("value1");
		payment.setPaymentType(paymentType);
		additionalPaymentRequestList.add(payment);
		
		ServiceRequestLocale serviceRequestLocaleService = createMock(ServiceRequestLocale.class);
		LocalizedSiebelLOVListResult result = new LocalizedSiebelLOVListResult();
		List<ListOfValues> localizedSiebelLOVList = new ArrayList<ListOfValues>(0);
		ListOfValues lov =  new ListOfValues();
		lov.setValue("value1");
		lov.setName("name1");
		localizedSiebelLOVList.add(lov);
		result.setLocalizedSiebelLOVList(localizedSiebelLOVList);
		expect(serviceRequestLocaleService.retrieveLocalizedSiebelLOVList((LocalizedSiebelLOVListContract)anyObject())).andReturn(result);
		replayAll();
		ControllerUtil.batchLocalizeAdditionPayments(additionalPaymentRequestList, serviceRequestLocaleService, Locale.US);
		assertEquals(paymentType.getName(), "name1");
		verifyAll();
	}
	
	
	@Test
	public void testFormatHTMLTagForNotesWhenDetailsEmpty(){
		List<ActivityNote> activityNoteList = new ArrayList<ActivityNote>();
		ActivityNote note = new ActivityNote();
		note.setNoteDetails("");
		activityNoteList.add(note);
		ControllerUtil.formatHTMLTagForNotes(activityNoteList);
		assertEquals(note.getNoteDetails(), "");
	}
	
	
	@Test
	public void testFormatHTMLTagForNotesWhenDetailsNeedsToBeReplaced(){
		List<ActivityNote> activityNoteList = new ArrayList<ActivityNote>();
		ActivityNote note = new ActivityNote();
		note.setNoteDetails("\\\\rabc\\\\rdef\\\\nghi\\\\njk");
		activityNoteList.add(note);
		ControllerUtil.formatHTMLTagForNotes(activityNoteList);
		assertEquals(note.getNoteDetails(), "\\\rabc\\\rdef\\\nghi\\\njk");
	}
	
	
	@Test
	public void testFormatHTMLTagForNotesWhenIDIsEmptyString(){
		List<ActivityNote> activityNoteList = new ArrayList<ActivityNote>();
		ActivityNote note = new ActivityNote();
		note.setNoteId("");
		activityNoteList.add(note);
		ControllerUtil.formatHTMLTagForNotes(activityNoteList);
		assertNull(note.getNoteId(), null);
	}
	
	
	@Test
	public void testCheckAdditionalPaymentsWithEmptyIDWhenEmptyString(){
		List<AdditionalPaymentRequest> payments = new ArrayList<AdditionalPaymentRequest>();
		AdditionalPaymentRequest payment = new AdditionalPaymentRequest();
		payment.setPaymentRequestId("");
		payments.add(payment);
		ControllerUtil.checkAdditionalPaymentsWithEmptyID(payments);
		assertNull(payment.getPaymentRequestId());
	}
	
	
	@Test
	public void testCheckAdditionalPaymentsWithEmptyIDWhenNullLiteralString(){
		List<AdditionalPaymentRequest> payments = new ArrayList<AdditionalPaymentRequest>();
		AdditionalPaymentRequest payment = new AdditionalPaymentRequest();
		payment.setPaymentRequestId("null");
		payments.add(payment);
		ControllerUtil.checkAdditionalPaymentsWithEmptyID(payments);
		assertNull(payment.getPaymentRequestId());
	}
	
	
	@Test
	public void testLocalizeActivityListForServiceHistory(){
		List<Activity> activityList = new ArrayList<Activity>();
		Activity activity = new Activity();
		activityList.add(activity);
		ListOfValues activityStatus = new ListOfValues();
		activityStatus.setValue("value1");
		activity.setActualFailureCode(activityStatus);
		Map<String, String> activityStatusMap = new HashMap<String, String>();
		activityStatusMap.put("value1", "value2");
			
	}
	
	
	private FRUPartDetailResult getFRUPartDetailResultWithReplacementPartNumber(){
		FRUPartDetailResult result = new FRUPartDetailResult();
		Part part1 = new Part(); 
		part1.setPartId("partId1");
		part1.setReplacementPartNumber("replacementPart1");
		result.setPart(part1);
		return result;		
	}
	
	
	private FRUPartDetailResult getFRUPartDetailResultWithReplacementPartNumber2(){
		FRUPartDetailResult result = new FRUPartDetailResult();
		Part part1 = new Part(); 
		part1.setPartId("partId2");
		result.setPart(part1);
		return result;		
	}
	
	private FRUPartDetailContract matchPartNumberAndReplacementFlag(FRUPartDetailContract contract){
		EasyMock.reportMatcher(new FRUPartDetailContractMatcher(contract)); 
		return null;
	}
	
	
	private LocalizedSiebelLOVListContract matchLocalizedSiebelLOVListContractByLov(LocalizedSiebelLOVListContract contract){
		EasyMock.reportMatcher(new LocalizedSiebelLOVListContractMatcher(contract)); 
		return null;		
	}
	
	class LocalizedSiebelLOVListContractMatcher implements IArgumentMatcher{
		private LocalizedSiebelLOVListContract expect;
		
		public LocalizedSiebelLOVListContractMatcher(LocalizedSiebelLOVListContract contract){
			this.expect = contract;
		}
		
		@Override
		public void appendTo(StringBuffer append) {
			append.append("in matchLocalizedSiebelLOVListContractByLov");
		}
		@Override
		public boolean matches(Object obj) {
		    if(!(obj instanceof LocalizedSiebelLOVListContract)){
		    	return false; 
		    }  
		    LocalizedSiebelLOVListContract contract = (LocalizedSiebelLOVListContract) obj;   
			if(contract.getLovListName().equals(expect.getLovListName())){
				return true;
			}
			return false;				
		}		
	}
	
	
	class FRUPartDetailContractMatcher implements IArgumentMatcher{
		private FRUPartDetailContract expected;   
		public FRUPartDetailContractMatcher(FRUPartDetailContract expected) {   
		    this.expected = expected;   
		}
		
		@Override
		public void appendTo(StringBuffer buffer) {
			buffer.append("in FRUPartDetailContractMatcher");
		}

		@Override
		public boolean matches(Object obj) {
		    if(!(obj instanceof FRUPartDetailContract)){
		    	return false; 
		    }  
		    FRUPartDetailContract contract = (FRUPartDetailContract) obj;   
			if((contract.getPartNumber().equals(expected.getPartNumber())) && (contract.isReplacementFlag() == expected.isReplacementFlag())){
				return true;
			}
			return false;
		}
		
	}
	
	private FRUPartDetailResult getFRUPartDetailResult(){
		FRUPartDetailResult result = new FRUPartDetailResult();
		Part part1 = new Part(); 
		part1.setPartId("partId1");
		result.setPart(part1);
		return result;
	}

	private SiebelLOVListResult getSiebelLOVListResultWithValues(){
		SiebelLOVListResult listResult = new SiebelLOVListResult();
		List<ListOfValues> lovList = new ArrayList<ListOfValues>();
		ListOfValues lov1 = new ListOfValues();
		lov1.setId("1");
		lov1.setType("type");
		lov1.setValue("value1");
		
		ListOfValues lov2 = new ListOfValues();
		lov2.setId("2");
		lov2.setType("type");
		lov2.setValue("value2");
		
		lovList.add(lov1);
		lovList.add(lov2);
		listResult.setLovList(lovList);
		return listResult;
	}
	
	
	private LocalizedSiebelLOVListResult getLocalizedSiebelLOVListResult(){
		LocalizedSiebelLOVListResult localizedLOVListResult = new LocalizedSiebelLOVListResult();
		List<ListOfValues> localizedSiebelLOVList = new ArrayList<ListOfValues>();
		ListOfValues lov1 = new ListOfValues();
		lov1.setName("localized_name1");
		lov1.setValue("value1");
		ListOfValues lov2 = new ListOfValues();
		lov2.setName("localized_name2");
		lov2.setValue("value2");
		ListOfValues lov3 = new ListOfValues();
		lov3.setName("localized_name3");
		lov3.setValue("value3");
		localizedSiebelLOVList.add(lov1);
		localizedSiebelLOVList.add(lov2);
		localizedSiebelLOVList.add(lov3);
		localizedLOVListResult.setLocalizedSiebelLOVList(localizedSiebelLOVList);
		return localizedLOVListResult;
	}
}
