package com.lexmark.portlet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.portlet.MockPortletSession;
import org.springframework.mock.web.portlet.MockRenderRequest;
import org.springframework.mock.web.portlet.MockRenderResponse;
import org.springframework.mock.web.portlet.MockResourceRequest;
import org.springframework.mock.web.portlet.MockResourceResponse;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;

import com.lexmark.domain.Account;
import com.lexmark.service.api.PartnerRequestsService;
import com.lexmark.service.impl.mock.PartnerRequestsServiceImpl;
import com.lexmark.util.TestUtil;

public class CustomerAccountInfoControllerTest {
	
	private CustomerAccountInfoController customerAccountInfoController = null;
	
    private Model mockModel = new ExtendedModelMap();
	private MockRenderRequest renderRequest = new MockRenderRequest();
	private MockRenderResponse renderResponse = new MockRenderResponse();
	private MockResourceRequest resourceRequest = new MockResourceRequest();
	private MockResourceResponse resourceResponse = new MockResourceResponse();
	private MockPortletSession session = new MockPortletSession();

	@Before
	public void setUp() throws Exception {
		TestUtil.initSession(session);
		customerAccountInfoController = new CustomerAccountInfoController();
		PartnerRequestsService mockPartnerRequestsService = new PartnerRequestsServiceImpl();
		customerAccountInfoController.setPartnerRequestsService(mockPartnerRequestsService);
		resourceRequest.setSession(session);
	}

	@After
	public void tearDown() throws Exception {
	}
	

	@Test
	public void testShowCustomerAccountInfo() throws IOException {
		String  ResultString =	customerAccountInfoController.showCustomerAccountInfo(renderRequest, renderResponse,mockModel);
		assertEquals(ResultString,"claims/customerAccountInfoPopup");
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testGetCustomerAccountInfoList() throws Exception {
		
		String  ResultString =	customerAccountInfoController.getCustomerAccountInfoList(resourceRequest, resourceResponse,mockModel);
		assertNotNull(ResultString);

		List<Account> customerAccountList =(List<Account>) mockModel.asMap().get("customerAccountList");
		assertNotNull(customerAccountList);
		
	}
	
}
