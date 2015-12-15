package com.lexmark.portlet;

import javax.portlet.ResourceResponse;

import org.easymock.EasyMockSupport;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.portlet.MockRenderRequest;
import org.springframework.mock.web.portlet.MockRenderResponse;
import org.springframework.mock.web.portlet.MockResourceRequest;
import org.springframework.mock.web.portlet.MockResourceResponse;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;

public class BaseControllerTest extends EasyMockSupport{
	private BaseController baseController;
	MockRenderRequest request;
	MockRenderResponse response;
	Model model;
	
	@Before
	public void setUp() throws Exception {
		request = new MockRenderRequest();
		response = new MockRenderResponse();
		model = new ExtendedModelMap();
		baseController = new BaseController();
	}
	
	@Test
	public void testShowPopupErrorPage() throws Exception{
		Assert.assertNotNull("return is expected", baseController.showPopupErrorPage(request,response,model));
	}
	
	@Test
	public void testShowEmailNotificationSendingPage() throws Exception{
		Assert.assertNotNull("return is expected", baseController.showEmailNotificationSendingPage(request,response,model));
	}
	
	@Test
	public void testSendingEmailIf()throws Exception{
		MockResourceRequest rRequest = new MockResourceRequest();
		MockResourceResponse rResponse = new MockResourceResponse();
		baseController.sendingEmail("testContent","testSubject","testAddress@163.com",true,rRequest,rResponse);
		Assert.assertEquals("500",rResponse.getProperty(ResourceResponse.HTTP_STATUS_CODE));
	}
	
	@Test
	public void testSendingEmailElse()throws Exception{
		MockResourceRequest rRequest = new MockResourceRequest();
		MockResourceResponse rResponse = new MockResourceResponse();
		baseController.sendingEmail("testContent","testSubject","",true,rRequest,rResponse);
		Assert.assertEquals("200",rResponse.getProperty(ResourceResponse.HTTP_STATUS_CODE));
	}
	
	@Test
	public void testSendingEmail2If()throws Exception{
		Assert.assertFalse(baseController.sendingEmail("testAddress@163.com","testSubject","testContent",true));
	}
	
	@Test
	public void testSendingEmail2Else()throws Exception{
		Assert.assertTrue(baseController.sendingEmail("","testSubject","testContent",true));
	}
}
