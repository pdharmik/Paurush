package com.lexmark.portlet;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.PortletRequest;
import javax.portlet.PortletSession;

import org.easymock.EasyMockSupport;
import static org.easymock.EasyMock.*;
import org.hibernate.jdbc.Expectation;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.portlet.MockActionRequest;
import org.springframework.mock.web.portlet.MockActionResponse;
import org.springframework.mock.web.portlet.MockRenderRequest;
import org.springframework.mock.web.portlet.MockRenderResponse;
import org.springframework.mock.web.portlet.MockResourceRequest;
import org.springframework.mock.web.portlet.MockResourceResponse;
import org.springframework.test.annotation.ExpectedException;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import com.lexmark.constants.LexmarkConstants;
import com.lexmark.contract.GlobalPartnerAssetListContract;
import com.lexmark.contract.LocalizedSiebelLOVListContract;
import com.lexmark.contract.ManualAssetContract;
import com.lexmark.contract.OpenClaimListContract;
import com.lexmark.contract.ProductImageContract;
import com.lexmark.domain.Activity;
import com.lexmark.domain.Asset;
import com.lexmark.domain.ListOfValues;
import com.lexmark.domain.ServicesUser;
import com.lexmark.form.DeviceSelectionForm;
import com.lexmark.result.GlobalPartnerAssetListResult;
import com.lexmark.result.LocalizedSiebelLOVListResult;
import com.lexmark.result.ManualAssetResult;
import com.lexmark.result.OpenClaimListResult;
import com.lexmark.result.ProductImageResult;
import com.lexmark.service.api.PartnerRequestsService;
import com.lexmark.service.api.ProductImageService;
import com.lexmark.service.api.ServiceRequestLocale;
import com.lexmark.service.api.ServiceRequestService;
import com.lexmark.util.TestUtil;

public class ClaimRequestSelectClaimControllerTest extends EasyMockSupport{

	protected ClaimRequestSelectClaimController controller;
	private PartnerRequestsService partnerRequestsService;
	private ProductImageService productImageService;
	private ServiceRequestLocale serviceRequestLocaleService;
	private ServiceRequestService serviceRequestService;
	
	private static final float TIME_ZONE = -8;
	private static final String SELECT_DEVICE_EXCEPTION_MESSAGE = "Failed to open Select Device page: <br/> OpenClaimListResult is null";
	private static final String ASSET_ID = "assetId1";
	private static final String ASSET_SERIAL_NUMBER = "assetSerialNumber1";
	private static final String ASSET_PRODUCT_TLI = "productTLI1";
	private static final String ASSET_MODEL_NUMBER = "modelNumber1";
	private static final String EMPTY = "";
	
	private static final String PRODUCT_IMAGE_URL = "www.producImage.com";
	@Before
	public void setUp() throws Exception {
		partnerRequestsService = createMock(PartnerRequestsService.class);
		productImageService = createMock(ProductImageService.class);
		serviceRequestLocaleService = createMock(ServiceRequestLocale.class);
		serviceRequestService = createMock(ServiceRequestService.class);
		controller = new ClaimRequestSelectClaimController();
		
		TestUtil.setProperty(controller, "partnerRequestsService", partnerRequestsService);
		TestUtil.setProperty(controller, "productImageService", productImageService);
		TestUtil.setProperty(controller, "serviceRequestLocaleService", serviceRequestLocaleService);
		TestUtil.setProperty(controller, "serviceRequestService", serviceRequestService);
		resetAll();
	}

	/**
	 * test selectDevice start.
	 */
	@Test
	public void testSelectDevice() throws Exception {
		MockActionRequest request = new MockActionRequest();
		MockActionResponse response = new MockActionResponse();
		Model model = new ExtendedModelMap();
		DeviceSelectionForm deviceSelectionForm = new DeviceSelectionForm();
		Asset asset = new Asset();
		asset.setAssetId(ASSET_ID);
		deviceSelectionForm.setAsset(asset);
		fillRequest(request);
		expect(partnerRequestsService.retrieveOpenClaimList((OpenClaimListContract)anyObject())).andReturn(null);
		try {
			replayAll();
			controller.selectDevice(request, response, deviceSelectionForm, null, model);
			verifyAll();
		} catch (Exception e) {
			assertTrue(SELECT_DEVICE_EXCEPTION_MESSAGE.equals(e.getMessage()));
		}
		
	}
	
	@Test
	public void testSelectDeviceAssetIdEmpty() throws Exception {
		MockActionRequest request = new MockActionRequest();
		MockActionResponse response = new MockActionResponse();
		Model model = new ExtendedModelMap();
		DeviceSelectionForm deviceSelectionForm = new DeviceSelectionForm();
		deviceSelectionForm.setAsset(generateAsset(EMPTY));
		deviceSelectionForm.setNotMyPrinterFlag("true");
		deviceSelectionForm.setTimezoneOffset(TIME_ZONE);
		controller.selectDevice(request, response, deviceSelectionForm, null, model);
		assertTrue(ASSET_MODEL_NUMBER.equals(response.getRenderParameter("machineType")));
		assertTrue(ASSET_PRODUCT_TLI.equals(response.getRenderParameter("produceTLI")));
		assertTrue(ASSET_SERIAL_NUMBER.equals(response.getRenderParameter("serialNumber")));
		assertTrue(ASSET_MODEL_NUMBER.equals(response.getRenderParameter("machineType")));
		assertTrue(EMPTY.equals(response.getRenderParameter("assetId")));
		assertTrue("true".equals(response.getRenderParameter("notMyPrinterFlag")));
		assertTrue(String.valueOf(TIME_ZONE).equals(response.getRenderParameter(LexmarkConstants.TIMEZONE_OFFSET)));
	}
	
	@Test
	public void testSelectDeviceResultEmpty() throws Exception {
		MockActionRequest request = new MockActionRequest();
		MockActionResponse response = new MockActionResponse();
		Model model = new ExtendedModelMap();
		DeviceSelectionForm deviceSelectionForm = new DeviceSelectionForm();
		deviceSelectionForm.setAsset(generateAsset(ASSET_ID));
		deviceSelectionForm.setNotMyPrinterFlag("true");
		deviceSelectionForm.setTimezoneOffset(TIME_ZONE);
		fillRequest(request);
		OpenClaimListResult result = new OpenClaimListResult();
		result.setTotalCount(0);
		expect(partnerRequestsService.retrieveOpenClaimList((OpenClaimListContract)anyObject())).andReturn(result);
		replayAll();
		controller.selectDevice(request, response, deviceSelectionForm, null, model);
		verifyAll();
		assertTrue(ASSET_MODEL_NUMBER.equals(response.getRenderParameter("machineType")));
		assertTrue(ASSET_PRODUCT_TLI.equals(response.getRenderParameter("produceTLI")));
		assertTrue(ASSET_SERIAL_NUMBER.equals(response.getRenderParameter("serialNumber")));
		assertTrue(ASSET_MODEL_NUMBER.equals(response.getRenderParameter("machineType")));
		assertTrue(ASSET_ID.equals(response.getRenderParameter("assetId")));
		assertTrue("true".equals(response.getRenderParameter("notMyPrinterFlag")));
		assertTrue(String.valueOf(TIME_ZONE).equals(response.getRenderParameter(LexmarkConstants.TIMEZONE_OFFSET)));
	}
	
	@Test
	public void testSelectDeviceResult() throws Exception {
		MockActionRequest request = new MockActionRequest();
		MockActionResponse response = new MockActionResponse();
		Model model = new ExtendedModelMap();
		DeviceSelectionForm deviceSelectionForm = new DeviceSelectionForm();
		deviceSelectionForm.setAsset(generateAsset(ASSET_ID));
		deviceSelectionForm.setNotMyPrinterFlag("true");
		deviceSelectionForm.setTimezoneOffset(TIME_ZONE);
		fillRequest(request);
		OpenClaimListResult result = new OpenClaimListResult();
		List<Activity> claimList = new ArrayList<Activity>();
		result.setTotalCount(1);
		result.setClaimList(claimList);
		ProductImageResult productImageResult = new ProductImageResult();
		productImageResult.setProductImageUrl(PRODUCT_IMAGE_URL);
		LocalizedSiebelLOVListResult localizedLOVListresult = new LocalizedSiebelLOVListResult();
		List<ListOfValues> localizedSiebelLOVList = new ArrayList<ListOfValues>();
		localizedLOVListresult.setLocalizedSiebelLOVList(localizedSiebelLOVList);
		expect(partnerRequestsService.retrieveOpenClaimList((OpenClaimListContract)anyObject())).andReturn(result);
		expect(productImageService.retrieveProductImageUrl((ProductImageContract)anyObject(), (String)anyObject())).andReturn(productImageResult);
		expect(serviceRequestLocaleService.retrieveLocalizedSiebelLOVList((LocalizedSiebelLOVListContract)anyObject())).andReturn(localizedLOVListresult);
		replayAll();
		controller.selectDevice(request, response, deviceSelectionForm, null, model);
		verifyAll();
		assertNotNull(response.getRenderParameter("openClaimListXML"));
	}
	/**
	 * test selectDevice end.
	 */
	
	/**
	 * test showMultipleClaimView start.
	 * @throws Exception 
	 */
	@Test
	public void testShowMultipleClaimView() throws Exception {
		MockRenderRequest request = new MockRenderRequest();
		MockRenderResponse response = new MockRenderResponse();
		Model model = new ExtendedModelMap();
		request.setParameter("openClaimListXML", "claimListxml");
		controller.showMultipleClaimView(request, response, model);
		assertTrue("claimListxml".equals(model.asMap().get("openClaimListXML")));
	}
	/**
	 * test showMultipleClaimView end.
	 */
	
	/**
	 * test showGlobalPartnerAssetSectionView start.
	 * @throws Exception 
	 */
	@Test
	public void testShowGlobalPartnerAssetSectionView() {
		MockRenderRequest request = new MockRenderRequest();
		MockRenderResponse response = new MockRenderResponse();
		Model model = new ExtendedModelMap();
		controller.showGlobalPartnerAssetSectionView(request, response, model);
		assertNotNull(model.asMap().get("deviceSelectionForm"));
		assertNotNull(model.asMap().get("helpURL"));
	}
	/**
	 * test testShowGlobalPartnerAssetSectionView end.
	 */
	
	/**
	 * test getGlobalPartnerAssetListBySerialNumber start.
	 * @throws Exception 
	 */
	@Test
	public void testGetGlobalPartnerAssetListBySerialNumberAssetListNull() {
		MockResourceRequest request = new MockResourceRequest();
		MockResourceResponse response = new MockResourceResponse();
		Model model = new ExtendedModelMap();
		String serialNumber = "serialNumber";
		GlobalPartnerAssetListResult result = new GlobalPartnerAssetListResult();
		result.setAssetList(null);
		expect(partnerRequestsService.retrieveGlobalPartnerAssetList((GlobalPartnerAssetListContract)anyObject())).andReturn(result);
		replayAll();
		controller.getGlobalPartnerAssetListBySerialNumber(request, response, model, serialNumber);
		verifyAll();
		GlobalPartnerAssetListResult modelResult = (GlobalPartnerAssetListResult)model.asMap().get("globalPartnerAssetListResult");
		assertNull(modelResult.getAssetList());
	}
	
	@Test
	public void testGetGlobalPartnerAssetListBySerialNumber() throws Exception {
		MockResourceRequest request = new MockResourceRequest();
		MockResourceResponse response = new MockResourceResponse();
		Model model = new ExtendedModelMap();
		String serialNumber = "serialNumber";
		GlobalPartnerAssetListResult result = new GlobalPartnerAssetListResult();
		Asset asset = new Asset();
		result.getAssetList().add(asset);
		ProductImageResult productImageResult = new ProductImageResult();
		productImageResult.setProductImageUrl(PRODUCT_IMAGE_URL);
		expect(productImageService.retrieveProductImageUrl((ProductImageContract)anyObject(), (String)anyObject())).andReturn(productImageResult);
		expect(partnerRequestsService.retrieveGlobalPartnerAssetList((GlobalPartnerAssetListContract)anyObject())).andReturn(result);
		replayAll();
		controller.getGlobalPartnerAssetListBySerialNumber(request, response, model, serialNumber);
		verifyAll();
		GlobalPartnerAssetListResult modelResult = (GlobalPartnerAssetListResult)model.asMap().get("globalPartnerAssetListResult");
		assertNotNull(modelResult.getAssetList());
	}
	/**
	 * test getGlobalPartnerAssetListBySerialNumber end.
	 */
	
	/**
	 * test validateManualAsset start.
	 * @throws Exception 
	 * @throws Exception 
	 */
	@Test
	public void testValidateManualAsset() throws Exception {
		MockResourceRequest request = new MockResourceRequest();
		MockResourceResponse response = new MockResourceResponse();
		request.setParameter("machineType", "typeOne");
		request.setParameter("productTLI", "tliOne");
		ManualAssetResult mar = new ManualAssetResult();
		mar.setResult(true);
		expect(serviceRequestService.validateManualAsset((ManualAssetContract)anyObject())).andReturn(mar);
		replayAll();
		controller.validateManualAsset(request, response);
		verifyAll();
	}
	
	@Test
	public void testValidateManualAssetException() throws Exception {
		MockResourceRequest request = new MockResourceRequest();
		MockResourceResponse response = new MockResourceResponse();
		request.setParameter("machineType", "typeOne");
		request.setParameter("productTLI", "tliOne");
		Exception exception = new Exception();
		expect(serviceRequestService.validateManualAsset((ManualAssetContract)anyObject())).andThrow(exception);
		replayAll();
		controller.validateManualAsset(request, response);
		verifyAll();
	}
	/**
	 * test validateManualAsset end.
	 */
	private void fillRequest(PortletRequest request){
		request.getPortletSession().setAttribute(LexmarkConstants.SERVICES_USER_PHASE2, generateServiceUser(), PortletSession.APPLICATION_SCOPE);
	}
	private ServicesUser generateServiceUser(){
		ServicesUser servicesUser = new ServicesUser();
		servicesUser.setMdmId("mdmid");
		servicesUser.setMdmLevel("mdmlevel");
		return servicesUser;
	}
	private Asset generateAsset(String assetId){
		Asset asset = new Asset();
		asset.setAssetId(assetId);
		asset.setSerialNumber(ASSET_SERIAL_NUMBER);
		asset.setModelNumber(ASSET_MODEL_NUMBER);
		asset.setProductTLI(ASSET_PRODUCT_TLI);
		return asset;
	}
}
