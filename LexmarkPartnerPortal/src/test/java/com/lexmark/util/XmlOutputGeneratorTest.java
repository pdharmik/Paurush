package com.lexmark.util;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.lexmark.constants.LexmarkPPConstants;
import com.lexmark.domain.Activity;
import com.lexmark.domain.ActivityNote;
import com.lexmark.domain.AdditionalPaymentRequest;
import com.lexmark.domain.BulkUploadStatus;
import com.lexmark.domain.GenericAddress;
import com.lexmark.domain.Part;
import com.lexmark.domain.PartLineItem;
import com.lexmark.domain.PartnerAgreement;
import com.lexmark.domain.ServiceRequestActivity;
import com.lexmark.domain.TechnicianInstruction;
import com.lexmark.enums.CarrierEnum;
import com.lexmark.enums.LineStatusEnum;
import com.lexmark.service.impl.mock.PartnerDomainMockDataGenerator;


public class XmlOutputGeneratorTest {
	
	private XmlOutputGenerator xmlOutputGenerator;
	private final String emptyXMLA = "<?xml version=\\\"1.0\\\" ?><rows></rows>";
	private final String emptyXMLB = "<?xml version=\"1.0\" ?>\n<rows>\n</rows>\n";
	private final String emptyXMLC = "<?xml version=\"1.0\" ?><rows></rows>";
	private final String yesMessage = "claim.lebel.returnRequired.yes";
	private final String noMessage = "claim.lebel.returnRequired.no";
	@Before
	public void setUp() {
		xmlOutputGenerator = new XmlOutputGenerator(Locale.ENGLISH);
	}

	@Test
	public void testGetLocalNULL() {
		XmlOutputGenerator newXmlOutputGenerator = new XmlOutputGenerator(Locale.ENGLISH);
		newXmlOutputGenerator.setLocale(null);
		Assert.assertNotNull(newXmlOutputGenerator.getLocale());
	}
	
	@Test
	public void testConvertPendingPartListToXMLEmpty() {
		String result = xmlOutputGenerator.convertPendingPartListToXML(null);
		Assert.assertTrue(emptyXMLA.equals(result));
		List<PartLineItem> list = new ArrayList<PartLineItem>();
		result = xmlOutputGenerator.convertPendingPartListToXML(list);
		Assert.assertTrue(emptyXMLA.equals(result));
	}
	
	@Test
	public void testConvertPendingPartListToXML() {
		List<PartLineItem> partList = PartnerDomainMockDataGenerator.getPartLineItemListWithoutRandom();
		partList.get(0).setReturnRequiredFlag(false);
		String result = xmlOutputGenerator.convertPendingPartListToXML(partList);
		assetStringExist(result,"row");
		assetStringExist(result,localizeMessage(yesMessage));
		assetStringExist(result,localizeMessage(noMessage));
	}
	
	
	@Test
	public void testConvertPartListToXMLEmpty(){
		String result = xmlOutputGenerator.convertPartListToXML(null);
		Assert.assertTrue(emptyXMLB.equals(result));
	}
	
	@Test
	public void testConvertPartListToXML(){
		List<Part> partList = PartnerDomainMockDataGenerator.getPartList();
		String result = xmlOutputGenerator.convertPartListToXML(partList);
		assetStringExist(result,"cell");
	}
	
	
	@Test
	public void testConvertOrderPartListToXMLEmpty(){
		String result = xmlOutputGenerator.convertOrderPartListToXML(null);
		Assert.assertTrue(emptyXMLA.equals(result));
	}
	
	
	@Test
	public void testConvertOrderPartListToXMLA(){
		List<PartLineItem> partList = PartnerDomainMockDataGenerator.getPartLineItemListWithoutRandom();
		PartLineItem partA = partList.get(0);
		partA.setShipDate(new Date());
		partA.getCarrier().setValue(CarrierEnum.FEDEX.getValue());
		
		GenericAddress genericAddressA = partA.getShipToAdress();
		genericAddressA.setProvince(null);
		genericAddressA.setState("genericAddressAState");
		genericAddressA.setAddressLine2("genericAddressAAddressLine2");
		genericAddressA.setAddressLine3("genericAddressAAddressLine3");
		
		PartLineItem partB = partList.get(1);
		partB.setShipDate(null);
		GenericAddress genericAddressB = partB.getShipToAdress();
		genericAddressB.setProvince("genericAddressBProvince");
		
		String result = xmlOutputGenerator.convertOrderPartListToXML(partList);
		
		assetStringExist(result,"row");
		assetStringExist(result,"genericAddressAState");
		assetStringExist(result,"genericAddressBProvince");
		assetStringExist(result,"genericAddressAAddressLine2");
		assetStringExist(result,"genericAddressAAddressLine3");
		assetStringExist(result,"onTrackingNumberClick");
	}
	
	
	@Test
	public void testConvertUpdateReturnPartListToXMLEmpty() {
		String result = xmlOutputGenerator.convertUpdateReturnPartListToXML(null,null);
		Assert.assertTrue(emptyXMLA.equals(result));
	}
	
	
	@Test
	public void testConvertUpdateReturnPartListToXMLWithSubRow() {
		Map<String, String> carrierDropDown = new HashMap<String, String>();
		carrierDropDown.put("carrierKey", "carrierValue");
		
		List<PartLineItem> partList = PartnerDomainMockDataGenerator.getPartLineItemListWithoutRandom();
		PartLineItem partA = partList.get(0);
		partA.setShipDate(new Date());
		partA.getCarrier().setValue(CarrierEnum.FEDEX.getValue());
		partA.getLineStatus().setValue(LineStatusEnum.RETURNED.getValue());
		
		LineStatusEnum.RETURNED.getValue();
		
		GenericAddress genericAddressA = partA.getShipToAdress();
		genericAddressA.setProvince(null);
		genericAddressA.setState("genericAddressAState");
		genericAddressA.setAddressLine2("genericAddressAAddressLine2");
		genericAddressA.setAddressLine3("genericAddressAAddressLine3");
		
		PartLineItem partB = partList.get(1);
		partB.setShipDate(null);
		partB.getLineStatus().setValue(LineStatusEnum.RETURNED.getValue());
		GenericAddress genericAddressB = partB.getShipToAdress();
		genericAddressB.setState(null);
		genericAddressB.setProvince("genericAddressBProvince");
		
		PartLineItem partC = partList.get(2);
		partC.getLineStatus().setValue(LineStatusEnum.IN_PROCESS.getValue());
		String result = xmlOutputGenerator.convertUpdateReturnPartListToXML(partList,true,carrierDropDown);
		assetStringExist(result,"genericAddressAState");
		assetStringExist(result,"genericAddressBProvince");
		assetStringExist(result,"genericAddressAAddressLine2");
		assetStringExist(result,"genericAddressAAddressLine3");
	}
	
	@Test
	public void testConvertUpdateReturnPartListToXMLWithoutSubRow() {
		Map<String, String> carrierDropDown = new HashMap<String, String>();
		List<PartLineItem> partList = PartnerDomainMockDataGenerator.getPartLineItemListWithoutRandom();
		carrierDropDown.put("carrierKey", "carrierValue");
		String result = xmlOutputGenerator.convertUpdateReturnPartListToXML(partList,false,carrierDropDown);
		assetStringNotExist(result,localizeMessage("claim.label.address"));
	}
	
	@Test
	public void testConvertReturnPartListToXMLEmpty() {
		String result = xmlOutputGenerator.convertReturnPartListToXML(null);
		Assert.assertTrue(emptyXMLA.equals(result));
	}
	
	@Test
	public void testConvertReturnPartListToXMLWithSubRow() {
		List<PartLineItem> partList = PartnerDomainMockDataGenerator.getPartLineItemListWithoutRandom();
		PartLineItem partA = partList.get(0);
		partA.setPartReceivedDate(new Date());
		partA.getCarrier().setValue(CarrierEnum.FEDEX.getValue());
		partA.getLineStatus().setValue(LineStatusEnum.RETURNED.getValue());
		
		LineStatusEnum.RETURNED.getValue();
		
		GenericAddress genericAddressA = partA.getShipToAdress();
		genericAddressA.setProvince(null);
		genericAddressA.setState("genericAddressAState");
		genericAddressA.setAddressLine2("genericAddressAAddressLine2");
		genericAddressA.setAddressLine3("genericAddressAAddressLine3");
		
		PartLineItem partB = partList.get(1);
		partB.getCarrier().setValue("other");
		partB.setPartReceivedDate(null);
		partB.getLineStatus().setValue(LineStatusEnum.RETURNED.getValue());
		GenericAddress genericAddressB = partB.getShipToAdress();
		genericAddressB.setState(null);
		genericAddressB.setProvince("genericAddressBProvince");
		
		PartLineItem partC = partList.get(2);
		partC.getLineStatus().setValue(LineStatusEnum.IN_PROCESS.getValue());
		String result = xmlOutputGenerator.convertReturnPartListToXML(partList,true);
		assetStringExist(result,"genericAddressAState");
		assetStringExist(result,"genericAddressBProvince");
		assetStringExist(result,"genericAddressAAddressLine2");
		assetStringExist(result,"genericAddressAAddressLine3");
	}
	
	@Test
	public void testConvertReturnPartListToXMLWithoutSubRow() {
		List<PartLineItem> partList = PartnerDomainMockDataGenerator.getPartLineItemListWithoutRandom();
		String result = xmlOutputGenerator.convertReturnPartListToXML(partList,false);
		assetStringNotExist(result,localizeMessage("claim.label.address"));
	}
	
	
	
	@Test
	public void testConvertThankYouReturnPartListToXMLEmpty() {
		String result = xmlOutputGenerator.convertThankYouReturnPartListToXML(null);
		Assert.assertTrue(emptyXMLA.equals(result));
	}
	
	@Test
	public void testConvertThankYouReturnPartListToXML() {
		List<PartLineItem> partList = PartnerDomainMockDataGenerator.getPartDebriefListForTesting();
		String result = xmlOutputGenerator.convertThankYouReturnPartListToXML(partList);
		assetStringExist(result,"row");
	}
	
	@Test
	public void testConvertThankYouReturnPartListNOToXMLEmpty() {
		String result = xmlOutputGenerator.convertThankYouReturnPartListNOToXML(null, null);
		Assert.assertTrue(emptyXMLA.equals(result));
	}
	
	@Test
	public void testConvertThankYouReturnPartListNOToXML() {
		List<PartLineItem> partList = PartnerDomainMockDataGenerator.getPartDebriefListForTesting();
		PartLineItem partA = partList.get(0);
		partA.setPartOrderedDate(new Date());
		String result = xmlOutputGenerator.convertThankYouReturnPartListNOToXML(partList, "Y");
		assetStringExist(result,"row");
	}	
	
	@Test
	public void testConvertTechnicianInformationListToXMLEmpty() {
		String result = xmlOutputGenerator.convertTechnicianInformationListToXML(null);
		Assert.assertTrue(emptyXMLC.equals(result));
	}
	
	@Test
	public void testConvertTechnicianInformationListToXML() {
		List<TechnicianInstruction> list = PartnerDomainMockDataGenerator.getServiceInstructionListForTesting();
		String result = xmlOutputGenerator.convertTechnicianInformationListToXML(list);
		assetStringExist(result,"row");
	}	
		
	
	@Test
	public void testConvertAdditionalPaymentListToXML() throws Exception{
		List<AdditionalPaymentRequest> list = PartnerDomainMockDataGenerator.getAddtionalPaymentRequestListForTesting();
		String result = xmlOutputGenerator.convertAdditionalPaymentListToXML(list);
		assetStringExist(result,"row");
	}
	
	@Test
	public void testConvertAdditionalPaymentListToXMLEmpty() throws Exception{
		String result = xmlOutputGenerator.convertAdditionalPaymentListToXML(null);
		Assert.assertTrue(emptyXMLA.equals(result));
	}
	
	@Test
	public void testConvertAdditionalPaymentListToXMLForEditEmpty() {
		String result = xmlOutputGenerator.convertAdditionalPaymentListToXMLForEdit(null,null);
		Assert.assertTrue(emptyXMLA.equals(result));
	}
	
	@Test
	public void testConvertAdditionalPaymentListToXMLForEdit() {
		List<AdditionalPaymentRequest> list = PartnerDomainMockDataGenerator.getAddtionalPaymentRequestListForTesting();
		String result = xmlOutputGenerator.convertAdditionalPaymentListToXMLForEdit(list,null);
		assetStringExist(result,"row");
	}
	
	@Test
	public void testConvertActivityNoteListToXMLEmpty() {
		String result = xmlOutputGenerator.convertActivityNoteListToXML(null);
		Assert.assertTrue(emptyXMLA.equals(result));
	}
	
	@Test
	public void testConvertActivityNoteListToXML() {
		List<ActivityNote> list = PartnerDomainMockDataGenerator.getActivityNoteListForTesting();
		String result = xmlOutputGenerator.convertActivityNoteListToXML(list);
		assetStringExist(result,"cell");
	}
	
	@Test
	public void testConvertActivityNoteListToXMLForEditEmpty() {
		String result = xmlOutputGenerator.convertActivityNoteListToXMLForEdit(null,"1-P3YKE1");
		Assert.assertTrue(emptyXMLA.equals(result));
	}

	@Test
	public void testConvertActivityNoteListToXMLForEdit(){
		List<ActivityNote> list = PartnerDomainMockDataGenerator.getActivityNoteListForTesting();
		ActivityNote activityNote = list.get(0);
		activityNote.getNoteAuthor().setContactId("1-P3YKE1");
		String result = xmlOutputGenerator.convertActivityNoteListToXMLForEdit(list,"1-P3YKE1");
		assetStringExist(result,"cell");
		assetStringExist(result,"editRow");
	}
	
	@Test(expected=RuntimeException.class)
	public void testConvertActivityNoteListToXMLForEditException() throws UnsupportedEncodingException {
		List<ActivityNote> list = PartnerDomainMockDataGenerator.getActivityNoteListForTesting();
		ActivityNote activityNote = list.get(0);
		activityNote.getNoteAuthor().setContactId(null);
		xmlOutputGenerator.convertActivityNoteListToXMLForEdit(list,"1-P3YKE1");
	}
	
	
	@Test
	public void testGetGenericAddressList()  {
		List<GenericAddress> list = PartnerDomainMockDataGenerator.getGenericAddressList();
		GenericAddress genericAddress = new GenericAddress();
		list.add(genericAddress);
		int totalCount = 20;
		int posStart = 0;
		String contextPath = "contextPath";
		String result = xmlOutputGenerator.convertAddressListToXML(list, totalCount, posStart, contextPath);
		assetStringExist(result,"cell");
	}
	
	@Test
	public void testConvertMyAddressListToXML()  {
		List<GenericAddress> list = PartnerDomainMockDataGenerator.getGenericAddressList();
		GenericAddress genericAddress = new GenericAddress();
		list.add(genericAddress);
		String contextPath = "contextPath";
		String result = xmlOutputGenerator.convertAddressListToXML(list,0,0, contextPath);
		assetStringExist(result,"cell");
	}
	
	
	@Test
	public void testConvertOpenClaimListToXML()  {
		List<Activity> list = PartnerDomainMockDataGenerator.getActivities();
		float timezoneOffset = 10;
		Activity activityA = list.get(0);
		activityA.setActivityDate(null);
		Activity activityB = list.get(1);
		activityB.setActivityDate(new Date());
		String result = xmlOutputGenerator.convertOpenClaimListToXML(list, timezoneOffset);
		assetStringExist(result,"cell");
	}
	
	@Test
	public void testConvertRecommendedPartListToXML()  {
		List<PartLineItem> list = PartnerDomainMockDataGenerator.getRecommendedPartLineItemListForTesting();
		PartLineItem part = new PartLineItem();
		list.add(part);
		String result = xmlOutputGenerator.convertRecommendedPartListToXML(list,true);
		assetStringExist(result,"cell");
	}
	
	@Test
	public void testConvertNoteListToXML() {
		String authorContactId = "authorContactId";
		List<ActivityNote> list = PartnerDomainMockDataGenerator.getActivityNoteListForTesting();
		ActivityNote note = list.get(0);
		note.getNoteAuthor().setContactId(authorContactId);
		note = new ActivityNote();
		list.add(note);
		String result = xmlOutputGenerator.convertNoteListToXML(list,authorContactId);
		assetStringExist(result,"cell");
	}
	
	@Test
	public void testConvertServiceRequestActivityListToXML() {
		List<ServiceRequestActivity> list = PartnerDomainMockDataGenerator.getServiceRequestActivityListForTesting();
		ServiceRequestActivity serviceRequestActivity = new ServiceRequestActivity();
		list.add(serviceRequestActivity);
		String result = xmlOutputGenerator.convertServiceRequestActivityListToXML(list);
		assetStringExist(result,"cell");
	}
	
	@Test
	public void testConvertRecommendedPartList() {
		List<PartLineItem> list = PartnerDomainMockDataGenerator.getRecommendedPartLineItemListForTesting();
		PartLineItem partA = list.get(0);
		partA.setReturnRequiredFlag(true);
		PartLineItem partB = list.get(1);
		partB.setReturnRequiredFlag(false);
		String result = xmlOutputGenerator.convertRecommendedPartList(list);
		assetStringExist(result,"cell");
		assetStringExist(result,localizeMessage(yesMessage));
		assetStringExist(result,localizeMessage(noMessage));
	}
	
	@Test
	public void testConvertPartnerAgreementToXML() {
		List<PartnerAgreement> partnerAgreementList = PartnerDomainMockDataGenerator.getPartnerAgreements();
		String result = xmlOutputGenerator.convertPartnerAgreementToXML(partnerAgreementList);
		assetStringExist(result,"tree");
	}
	
	
	@Test
	public void testConvertBulkUploadStatusToXML() {
		List<BulkUploadStatus> list = PartnerDomainMockDataGenerator.getBulkUploadStatusListForTesting();
		BulkUploadStatus bulkUploadStatus = list.get(0);
		String result = xmlOutputGenerator.convertBulkUploadStatusToXML(list);
		assetStringExist(result,bulkUploadStatus.getCompletedOn());
	}
	
	
	private void assetStringExist(String result, String value) {
		Assert.assertTrue(result.indexOf(value)>0);
	}	
	
	private void assetStringNotExist(String result, String value) {
		Assert.assertTrue(result.indexOf(value)==-1);
	}	
	
	private String localizeMessage(String propertyAttribute) {
		return (PropertiesMessageUtil.getPropertyMessage(LexmarkPPConstants.MESSAGE_BUNDLE_NAME, propertyAttribute, Locale.ENGLISH));
	}	
	    
}
