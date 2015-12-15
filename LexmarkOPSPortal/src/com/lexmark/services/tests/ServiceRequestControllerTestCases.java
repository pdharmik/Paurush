package com.lexmark.services.tests;
import static org.easymock.EasyMock.createMock;
import static org.junit.Assert.assertNull;

import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.mock.web.portlet.MockActionRequest;
import org.springframework.mock.web.portlet.MockActionResponse;
import org.springframework.mock.web.portlet.MockRenderRequest;
import org.springframework.mock.web.portlet.MockRenderResponse;
import org.springframework.mock.web.portlet.MockResourceRequest;
import org.springframework.mock.web.portlet.MockResourceResponse;
import org.springframework.ui.Model;

import com.lexmark.services.portlet.ServiceRequestController;
import com.lexmark.services.util.LexmarkUserUtil;

public class ServiceRequestControllerTestCases {

	private ServiceRequestController controller;
    private Model mockModel;
	private MockRenderRequest renderRequest = new MockRenderRequest();
	private MockRenderResponse renderResponse = new MockRenderResponse();
	private MockActionRequest actionRequest = new MockActionRequest();
	private MockActionResponse actionResponse = new MockActionResponse();
	private MockResourceRequest resourceRequest = new MockResourceRequest();
	private MockResourceResponse resourceResponse = new MockResourceResponse();
    private TestUtil testUtil = new TestUtil();
    private String agreementNumber;
    private String[] agreementNumbers;
    private	Map map;
    private LexmarkUserUtil mockLexmarkUserUtil;

	@Before
	public void setUp() throws Exception {
			
		renderRequest.addLocale(testUtil.getLocale());
		actionRequest.addLocale(testUtil.getLocale());
			    
	    controller = testUtil.getServiceRequestController();
	    
	    mockModel = createMock(Model.class);

	    map = createMock(Map.class);
	    
	    mockLexmarkUserUtil =  createMock(LexmarkUserUtil.class);
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	@Ignore
	public void testRetrieveServiceRequestListResourceXML() throws Exception
	{
		//mockModel.addAttribute(EasyMock.isA(String.class),EasyMock.isA(AssetListForm.class));
	   	//expectLastCall().andReturn(mockModel).times(1);
		//replay(mockModel);
		//TODO: find the way to test it 
        controller.retrieveServiceRequestList(resourceRequest, resourceResponse);
        assertNull(resourceResponse.getContentAsString());
		//logger.info(resourceResponse.getContentAsString());
	}
}
