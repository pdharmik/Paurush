package com.lexmark.portlet;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.easymock.EasyMockSupport;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.portlet.MockRenderRequest;
import org.springframework.mock.web.portlet.MockRenderResponse;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;

import com.b.f;
import com.lexmark.enums.ActualFailureCodeEnum;
import com.lexmark.enums.DeviceConditionEnum;
import com.lexmark.enums.ResolutionCodeEnum;
import com.lexmark.form.ClaimRequestConfirmationForm;
import com.lexmark.service.api.ServiceRequestLocale;
import com.lexmark.service.impl.mock.PartnerDomainMockDataGenerator;

public class ClaimThankYouControllorTest extends EasyMockSupport{
	protected ClaimThankYouControllor controller;
	private static final float timezone = -8;
	@Before
	public void setUp() throws Exception {
		controller = new ClaimThankYouControllor();
	}
	@Test
	public void testRetrieveClaimThankYouPage() throws Exception {
		MockRenderRequest request = new MockRenderRequest();
		MockRenderResponse response = new MockRenderResponse();
		Model model = new ExtendedModelMap();
		ClaimRequestConfirmationForm claimRequestConfirmationForm = new ClaimRequestConfirmationForm();
		claimRequestConfirmationForm.setTimezoneOffset(timezone);
		claimRequestConfirmationForm.setActivity(PartnerDomainMockDataGenerator.getActivity(3));
		claimRequestConfirmationForm.setProblemCodeList(generateProbelmCodeList());
		controller.retrieveClaimThankYouPage(model, request, response, claimRequestConfirmationForm);
		ClaimRequestConfirmationForm form = (ClaimRequestConfirmationForm)model.asMap().get("claimRequestConfirmationForm");
		assertNotNull(form.getAdditionalPaymentRequestListXML());
		assertNotNull(form.getActivityNoteListXML());
		assertNotNull(form.getRequestedPartsListNOXML());
		assertNull(form.getRequestedPartsListXML());
		assertTrue("probelmCode1".equals(form.getProblemCodeLocalized()));
		assertTrue(timezone == form.getTimezoneOffset());
	}
	
	@Test
	public void testRetrieveClaimThankYouPageFlagTrue() throws Exception {
		MockRenderRequest request = new MockRenderRequest();
		MockRenderResponse response = new MockRenderResponse();
		Model model = new ExtendedModelMap();
		ClaimRequestConfirmationForm claimRequestConfirmationForm = new ClaimRequestConfirmationForm();
		claimRequestConfirmationForm.setTimezoneOffset(timezone);
		claimRequestConfirmationForm.setActivity(PartnerDomainMockDataGenerator.getActivity(3));
		claimRequestConfirmationForm.setProblemCodeList(generateProbelmCodeList());
		claimRequestConfirmationForm.getActivity().setRepairCompleteFlag(true);
		claimRequestConfirmationForm.getActivity().setOrderPartList(null);
		controller.retrieveClaimThankYouPage(model, request, response, claimRequestConfirmationForm);
		ClaimRequestConfirmationForm form = (ClaimRequestConfirmationForm)model.asMap().get("claimRequestConfirmationForm");
		assertNotNull(form.getAdditionalPaymentRequestListXML());
		assertNotNull(form.getActivityNoteListXML());
		assertNull(form.getRequestedPartsListNOXML());
		assertNotNull(form.getRequestedPartsListXML());
		assertTrue("probelmCode1".equals(form.getProblemCodeLocalized()));
		assertTrue(timezone == form.getTimezoneOffset());
	}
	
	@Test
	public void testRetrieveClaimThankYouPageProbelmCodeNull() throws Exception {
		MockRenderRequest request = new MockRenderRequest();
		MockRenderResponse response = new MockRenderResponse();
		Model model = new ExtendedModelMap();
		ClaimRequestConfirmationForm claimRequestConfirmationForm = new ClaimRequestConfirmationForm();
		claimRequestConfirmationForm.setTimezoneOffset(timezone);
		claimRequestConfirmationForm.setActivity(PartnerDomainMockDataGenerator.getActivity(3));
		claimRequestConfirmationForm.setProblemCodeList(new HashMap<String, String>());
		controller.retrieveClaimThankYouPage(model, request, response, claimRequestConfirmationForm);
		ClaimRequestConfirmationForm form = (ClaimRequestConfirmationForm)model.asMap().get("claimRequestConfirmationForm");
		assertNotNull(form.getAdditionalPaymentRequestListXML());
		assertNotNull(form.getActivityNoteListXML());
		assertNotNull(form.getRequestedPartsListNOXML());
		assertNull(form.getRequestedPartsListXML());
		assertTrue(timezone == form.getTimezoneOffset());
	}
	
	@Test
	public void testRetrieveClaimThankYouPageDebrief() throws Exception {
		MockRenderRequest request = new MockRenderRequest();
		MockRenderResponse response = new MockRenderResponse();
		Model model = new ExtendedModelMap();
		ClaimRequestConfirmationForm claimRequestConfirmationForm = new ClaimRequestConfirmationForm();
		claimRequestConfirmationForm.setTimezoneOffset(timezone);
		claimRequestConfirmationForm.setActivity(PartnerDomainMockDataGenerator.getActivity(2));
		claimRequestConfirmationForm.setProblemCodeList(generateProbelmCodeList());
		claimRequestConfirmationForm.setResolutionCodeList(generateResolutionCodeList());
		claimRequestConfirmationForm.setWorkingConditionList(generateWorkingConditionList());
		controller.retrieveClaimThankYouPage(model, request, response, claimRequestConfirmationForm);
		ClaimRequestConfirmationForm form = (ClaimRequestConfirmationForm)model.asMap().get("claimRequestConfirmationForm");
		assertNotNull(form.getAdditionalPaymentRequestListXML());
		assertNotNull(form.getActivityNoteListXML());
		assertNotNull(form.getRequestedPartsListNOXML());
		assertNull(form.getRequestedPartsListXML());
		assertTrue("probelmCode3".equals(form.getProblemCodeLocalized()));
		assertTrue(timezone == form.getTimezoneOffset());
		assertTrue("resolutionCode1".equals(form.getResolutionCode()));
		assertTrue("deviceCondition1".equals(form.getActivity().getDebrief().getDeviceCondition().getName()));
	}
	
	@Test
	public void testRetrieveClaimThankYouPageDebriefResolutionNull() throws Exception {
		MockRenderRequest request = new MockRenderRequest();
		MockRenderResponse response = new MockRenderResponse();
		Model model = new ExtendedModelMap();
		ClaimRequestConfirmationForm claimRequestConfirmationForm = new ClaimRequestConfirmationForm();
		claimRequestConfirmationForm.setTimezoneOffset(timezone);
		claimRequestConfirmationForm.setActivity(PartnerDomainMockDataGenerator.getActivity(2));
		claimRequestConfirmationForm.setProblemCodeList(generateProbelmCodeList());
		claimRequestConfirmationForm.setResolutionCodeList(new HashMap<String, String>());
		claimRequestConfirmationForm.setWorkingConditionList(new HashMap<String, String>());
		controller.retrieveClaimThankYouPage(model, request, response, claimRequestConfirmationForm);
		ClaimRequestConfirmationForm form = (ClaimRequestConfirmationForm)model.asMap().get("claimRequestConfirmationForm");
		assertNotNull(form.getAdditionalPaymentRequestListXML());
		assertNotNull(form.getActivityNoteListXML());
		assertNotNull(form.getRequestedPartsListNOXML());
		assertNull(form.getRequestedPartsListXML());
		assertTrue("probelmCode3".equals(form.getProblemCodeLocalized()));
		assertTrue(timezone == form.getTimezoneOffset());
	}
	private Map<String, String> generateProbelmCodeList(){
		Map<String, String> probelmCodeList = new HashMap<String, String>();
		probelmCodeList.put(ActualFailureCodeEnum.NO_COMMUNICATION.getValue(), "probelmCode1");
		probelmCodeList.put(ActualFailureCodeEnum.PAPER_JAM.getValue(), "probelmCode2");
		probelmCodeList.put(ActualFailureCodeEnum.CODE_TREE.getValue(), "probelmCode3");
		return probelmCodeList;
	}
	private Map<String, String> generateResolutionCodeList(){
		Map<String, String> resolutionCodeList = new HashMap<String, String>();
		resolutionCodeList.put(ResolutionCodeEnum.RESOLUTION_CODE_ONE.getValue(), "resolutionCode1");
		resolutionCodeList.put(ResolutionCodeEnum.RESOLUTION_CODE_TWO.getValue(), "resolutionCode2");
		return resolutionCodeList;
	}
	private Map<String, String> generateWorkingConditionList(){
		Map<String, String> workingConditionList = new HashMap<String, String>();
		workingConditionList.put(DeviceConditionEnum.NOT_WORKING.getValue(), "deviceCondition1");
		workingConditionList.put(DeviceConditionEnum.WORKING.getValue(), "deviceCondition2");
		return workingConditionList;
	}
}
