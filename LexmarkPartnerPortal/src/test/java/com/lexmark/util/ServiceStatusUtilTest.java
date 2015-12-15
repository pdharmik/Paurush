package com.lexmark.util;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.portlet.ResourceResponse;

import org.junit.Test;
import org.springframework.mock.web.portlet.MockResourceResponse;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;

import com.lexmark.enums.DebriefStatusEnum;
import com.lexmark.enums.ErrorCodeTwoEnum;
import com.lexmark.enums.RequestSubStatusEnum;
import com.lexmark.enums.ServiceRequestTypeEnum;


public class ServiceStatusUtilTest {
	private final String successMessage = "message.claim.updateSuccess";
	private final String errorMessage = "exception.siebel.crmServiceException";
	Locale locale = Locale.ENGLISH;
	
	@Test
	public void testCheckServiceStatusSucceed(){
		String serviceStatus = successMessage;
		boolean returnSuccessMessages = true;
		Model model = new ExtendedModelMap();
		ServiceStatusUtil.checkServiceStatus(model, serviceStatus, locale, returnSuccessMessages);
		assertNotNull(model.asMap().get("serviceSuccessMessages"));
	}
	
	@Test
	public void testCheckServiceStatusError(){
		String serviceStatus = errorMessage;
		boolean returnSuccessMessages = true;
		Model model = new ExtendedModelMap();
		ServiceStatusUtil.checkServiceStatus(model, serviceStatus, locale, returnSuccessMessages);
		assertNotNull(model.asMap().get("serviceErrors"));
	}
	
	/* 
	 * debriefStatus: *
	 * requestSubStatus:PROCESSING_REQUEST
	 * requestType:*
	 * 
	 * isRequestAbleToBeUpdate return false
	 */
	@Test
	public void testIsRequestAbleToBeUpdate1(){
		String requestSubStatus = RequestSubStatusEnum.PROCESSING_REQUEST.getValue();
		String debriefStatus = DebriefStatusEnum.FAILED_VALIDATION.getValue();
		String requestType = ServiceRequestTypeEnum.CLAIM_REQUEST.getValue();
		boolean result = ServiceStatusUtil.isRequestAbleToBeUpdate(requestSubStatus, debriefStatus, requestType);
		assertTrue(!result);
	}
	
	/* 
	 * debriefStatus: VALIDATED
	 * requestSubStatus: *
	 * requestType:*
	 */
	@Test
	public void testIsRequestAbleToBeUpdate2(){
		String requestSubStatus = RequestSubStatusEnum.ACCEPTED.getValue();
		String debriefStatus = DebriefStatusEnum.VALIDATED.getValue();
		String requestType = ServiceRequestTypeEnum.CLAIM_REQUEST.getValue();
		boolean result = ServiceStatusUtil.isRequestAbleToBeUpdate(requestSubStatus, debriefStatus, requestType);
		assertTrue(!result);
	}
	
	/* 
	 * debriefStatus: FAILED_VALIDATION
	 * requestSubStatus: ACCEPTED
	 * requestType:*
	 */
	@Test
	public void testIsRequestAbleToBeUpdate3(){
		String requestSubStatus = RequestSubStatusEnum.ACCEPTED.getValue();
		String debriefStatus = DebriefStatusEnum.FAILED_VALIDATION.getValue();
		String requestType = ServiceRequestTypeEnum.CLAIM_REQUEST.getValue();
		boolean result = ServiceStatusUtil.isRequestAbleToBeUpdate(requestSubStatus, debriefStatus, requestType);
		assertTrue(result);
	}
	
	/* 
	 * debriefStatus: FAILED_VALIDATION
	 * requestSubStatus: PENDING_SP_ACKNOWLEDGEMENT
	 * requestType:CLAIM_REQUEST
	 */
	@Test
	public void testIsRequestAbleToBeUpdate4(){
		String requestSubStatus = RequestSubStatusEnum.PENDING_SP_ACKNOWLEDGEMENT.getValue();
		String debriefStatus = DebriefStatusEnum.FAILED_VALIDATION.getValue();
		String requestType = ServiceRequestTypeEnum.CLAIM_REQUEST.getValue();
		boolean result = ServiceStatusUtil.isRequestAbleToBeUpdate(requestSubStatus, debriefStatus, requestType);
		assertTrue(!result);
	}
	
	/* 
	 * debriefStatus: NULl or Empty
	 * requestSubStatus: *
	 * requestType:*
	 */
	@Test
	public void testIsRequestAbleToBeUpdate5(){
		String requestSubStatus = "";
		String debriefStatus = "";
		String requestType = ServiceRequestTypeEnum.CLAIM_REQUEST.getValue();
		boolean result = ServiceStatusUtil.isRequestAbleToBeUpdate(requestSubStatus, debriefStatus, requestType);
		assertTrue(!result);
	}
	
	/* 
	 * debriefStatus: Other
	 * requestSubStatus: *
	 * requestType:*
	 */
	@Test
	public void testIsRequestAbleToBeUpdate6(){
		String requestSubStatus = RequestSubStatusEnum.ACCEPTED.getValue();
		String debriefStatus = "*@*@";
		String requestType = ServiceRequestTypeEnum.CLAIM_REQUEST.getValue();
		boolean result = ServiceStatusUtil.isRequestAbleToBeUpdate(requestSubStatus, debriefStatus, requestType);
		assertTrue(!result);
	}
	
	/* 
	 * debriefStatus: *
	 * requestSubStatus:PROCESSING_REQUEST
	 * requestType:*
	 * 
	 * isRequestAbleToBeUpdate return false
	 */
	@Test
	public void testIsRequestAbleToBeDebrief1(){
		String requestSubStatus = RequestSubStatusEnum.PROCESSING_REQUEST.getValue();
		String debriefStatus = DebriefStatusEnum.FAILED_VALIDATION.getValue();
		boolean result = ServiceStatusUtil.isRequestAbleToBeDebrief(requestSubStatus, debriefStatus);
		assertTrue(!result);
	}
	
	/* 
	 * debriefStatus: VALIDATED
	 * requestSubStatus: *
	 * requestType:*
	 */
	@Test
	public void testIsRequestAbleToBeDebrief2(){
		String requestSubStatus = RequestSubStatusEnum.ACCEPTED.getValue();
		String debriefStatus = DebriefStatusEnum.VALIDATED.getValue();
		boolean result = ServiceStatusUtil.isRequestAbleToBeDebrief(requestSubStatus, debriefStatus);
		assertTrue(!result);
	}
	
	/* 
	 * debriefStatus: FAILED_VALIDATION
	 * requestSubStatus: ACCEPTED
	 * 
	 */
	@Test
	public void testIsRequestAbleToBeDebrief3(){
		String requestSubStatus = RequestSubStatusEnum.ACCEPTED.getValue();
		String debriefStatus = DebriefStatusEnum.FAILED_VALIDATION.getValue();
		boolean result = ServiceStatusUtil.isRequestAbleToBeDebrief(requestSubStatus, debriefStatus);
		assertTrue(result);
	}
	
	/* 
	 * debriefStatus: FAILED_VALIDATION
	 * requestSubStatus: PENDING_SP_ACKNOWLEDGEMENT
	 * 
	 */
	@Test
	public void testIsRequestAbleToBeDebrief4(){
		String requestSubStatus = RequestSubStatusEnum.PENDING_SP_ACKNOWLEDGEMENT.getValue();
		String debriefStatus = DebriefStatusEnum.FAILED_VALIDATION.getValue();
		boolean result = ServiceStatusUtil.isRequestAbleToBeDebrief(requestSubStatus, debriefStatus);
		assertTrue(!result);
	}
	
	/* 
	 * debriefStatus: NULl or Empty
	 * requestSubStatus: *
	 * 
	 */
	@Test
	public void testIsRequestAbleToBeDebrief5(){
		String requestSubStatus = "";
		String debriefStatus = "";
		boolean result = ServiceStatusUtil.isRequestAbleToBeDebrief(requestSubStatus, debriefStatus);
		assertTrue(!result);
	}
	
	/* 
	 * debriefStatus: Other
	 * requestSubStatus: *
	 * 
	 */
	@Test
	public void testIsRequestAbleToBeDebrief6(){
		String requestSubStatus = RequestSubStatusEnum.ACCEPTED.getValue();
		String debriefStatus = "*@*@";
		boolean result = ServiceStatusUtil.isRequestAbleToBeDebrief(requestSubStatus, debriefStatus);
		assertTrue(!result);
	}
	

	@Test
	public void responseResultSuccess()throws Exception{
		MockResourceResponse response = new MockResourceResponse();
		ServiceStatusUtil.responseResult(response, successMessage, locale);
		Pattern pattern = Pattern.compile("succeed=\"true\"");
		Matcher matcher = pattern.matcher(response.getContentAsString());
		assertTrue(matcher.find());
	}
	
	@Test
	public void responseResultError()throws Exception{
		MockResourceResponse response = new MockResourceResponse();
		ServiceStatusUtil.responseResult(response, errorMessage, locale);
		Pattern pattern = Pattern.compile("succeed=\"false\"");
		Matcher matcher = pattern.matcher(response.getContentAsString());
		assertTrue(matcher.find());
	}
	
	@Test
	public void responseResultReturnData()throws Exception{
		MockResourceResponse response = new MockResourceResponse();
		String returnData = "returnData";
		ServiceStatusUtil.responseResult(response, errorMessage, locale ,returnData);
		Pattern pattern = Pattern.compile("returnData");
		Matcher matcher = pattern.matcher(response.getContentAsString());
		assertTrue(matcher.find());
	}
	
	@Test
	public void responseResultGroupCode()throws Exception{
		MockResourceResponse response = new MockResourceResponse();
		String groupCode = "groupCode";
		ServiceStatusUtil.responseResult(response,groupCode, errorMessage, locale);
		Pattern pattern = Pattern.compile("groupCode");
		Matcher matcher = pattern.matcher(response.getContentAsString());
		assertTrue(matcher.find());
	}
}
