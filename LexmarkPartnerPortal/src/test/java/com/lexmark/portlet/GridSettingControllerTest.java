package com.lexmark.portlet;

import static org.easymock.EasyMock.anyObject;
import static org.easymock.EasyMock.expect;

import java.util.HashMap;
import java.util.Map;

import javax.portlet.PortletRequest;
import javax.portlet.RenderRequest;

import org.easymock.EasyMockSupport;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.portlet.MockRenderRequest;
import org.springframework.mock.web.portlet.MockResourceRequest;
import org.springframework.mock.web.portlet.MockResourceResponse;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;

import com.lexmark.contract.UserGridSettingContract;
import com.lexmark.result.UserGridSettingResult;
import com.lexmark.service.api.UserGridSettingService;
import com.lexmark.util.TestUtil;

public class GridSettingControllerTest extends EasyMockSupport {
	private UserGridSettingService userGridSettingService;
	private GridSettingController gridSettingController;
	
	private MockResourceRequest request;
	private MockResourceResponse response;
	private MockRenderRequest renderRequest;
	private Model mockModel;
	
	@Before
	public void setUp() throws Exception {
		userGridSettingService = createMock(UserGridSettingService.class);
		gridSettingController = new GridSettingController();
		
		TestUtil.setProperty(gridSettingController,"userGridSettingService",userGridSettingService);
		resetAll();
		request = new MockResourceRequest();
		response = new MockResourceResponse();
		renderRequest = new MockRenderRequest();
		mockModel = new ExtendedModelMap();
	}
	@Test
	public void testSaveGridSetting() throws Exception{
		generateMockResourceRequest(request);
		expect(userGridSettingService.saveUserGridSettings((UserGridSettingContract)anyObject())).andReturn(true);
		replayAll();
		gridSettingController.saveGridSetting(request, response);
		verifyAll();
	}
	
	@Test
	public void testSaveGridSettingException() throws Exception{
		generateMockResourceRequest(request);
		Exception exception = new Exception();
		expect(userGridSettingService.saveUserGridSettings((UserGridSettingContract)anyObject())).andThrow(exception);
		replayAll();
		gridSettingController.saveGridSetting(request, response);
		verifyAll();
	}
	
	
	@Test
	public void testResetGridSetting()throws Exception{
		generateMockResourceRequest(request);
		expect(userGridSettingService.deleteUserGridSettings((UserGridSettingContract)anyObject())).andReturn(true);
		replayAll();
		gridSettingController.resetGridSetting(request, response);
		verifyAll();
	}
	
	@Test
	public void testResetGridSettingException()throws Exception{
		generateMockResourceRequest(request);
		Exception exception = new Exception();
		expect(userGridSettingService.deleteUserGridSettings((UserGridSettingContract)anyObject())).andThrow(exception);
		replayAll();
		gridSettingController.resetGridSetting(request, response);
		verifyAll();
	}
	
	
	@Test
	public void testRetrieveQueryParamsNull()throws Exception{
		generateMockResourceRequest(request);
		gridSettingController.retrieveQueryParams(request, response);
		Assert.assertTrue("{status:\"success\"}".equals(response.getContentAsString()));
	}
	
	@Test
	public void testRetrieveQueryParamsNotNull()throws Exception{
		generateMockResourceRequest(request);
		request.getPortletSession().setAttribute("grid_query_params_English_gridId", "sessionKey");
		
		gridSettingController.retrieveQueryParams(request, response);
		Assert.assertTrue("{status:\"success\",data:sessionKey}".equals(response.getContentAsString()));
	}
	
	@Test
	public void testSaveGridQueryParamsNull()throws Exception{
		generateMockResourceRequest(request);
		request.setParameter("queryParams", "");

		gridSettingController.saveGridQueryParams(request, response);
		Assert.assertTrue("{error:\"queryParams required\"}".equals(response.getContentAsString()));
	}
	
	@Test
	public void testSaveGridQueryParamsGridIdNull()throws Exception{
		generateMockResourceRequest(request);
		request.setParameter("queryParams", "queryParams");
		request.setParameter("gridId", "");
		gridSettingController.saveGridQueryParams(request, response);
		Assert.assertTrue("{error:\"gridId required\"}".equals(response.getContentAsString()));
	}
	@Test
	public void testSaveGridQueryParams()throws Exception{
		generateMockResourceRequest(request);
		request.setParameter("queryParams", "queryParams");
		
		TestUtil.initSession(request.getPortletSession());
		
		gridSettingController.saveGridQueryParams(request, response);
		Assert.assertTrue("success".equals(response.getContentAsString()));
	}
	
	@Test
	public void testRetrieveGridSettingException()throws Exception{
		 MockRenderRequest renderRequest = new MockRenderRequest();
		Model mockModel = new ExtendedModelMap();
		
		generateMockRenderRequest(renderRequest);
		
		Exception exception = new Exception();
		expect(userGridSettingService.retrieveUserGridSettings((UserGridSettingContract)anyObject())).andThrow(exception);
		replayAll();
		gridSettingController.retrieveGridSetting("GridId",renderRequest, mockModel);
		verifyAll();
	}
	
	@Test
	public void testRetrieveGridSettingResult()throws Exception{
		generateMockRenderRequest(renderRequest);
		
		UserGridSettingResult result = new UserGridSettingResult();
		result.setGridId("gridId");
		result.setColsFilter("colsFilter");
		result.setColsHidden("colsHidden");
		result.setColsOrder("colsOrder");
		result.setColsWidth("colsWidth");
		result.setUserNumber("userNumber");
		
		expect(userGridSettingService.retrieveUserGridSettings((UserGridSettingContract)anyObject())).andReturn(result);
		replayAll();
		gridSettingController.retrieveGridSetting("GridId",renderRequest, mockModel);
		verifyAll();
		Assert.assertTrue("userNumber".equals(((UserGridSettingResult)mockModel.asMap().get("gridSettings")).getUserNumber()));
		
	}
	
	
	
	private  void generateMockResourceRequest(MockResourceRequest request){
		request.setParameter("gridId", "gridId");
		request.setParameter("colsHidden", "colsHidden");
		request.setParameter("colsOrder", "colsOrder");
		request.setParameter("colsSorting", "colsSorting");
		request.setParameter("colsWidth", "colsWidth");
		Map uinfo = new HashMap();
		uinfo.put("liferay.user.id", "1");
		request.setAttribute(PortletRequest.USER_INFO, uinfo);
		
	}
	
	private  void generateMockRenderRequest(MockRenderRequest request){
		request.setParameter("gridId", "gridId");
		request.setParameter("colsHidden", "colsHidden");
		request.setParameter("colsOrder", "colsOrder");
		request.setParameter("colsSorting", "colsSorting");
		request.setParameter("colsWidth", "colsWidth");
		Map uinfo = new HashMap();
		uinfo.put("liferay.user.id", "1");
		request.setAttribute(PortletRequest.USER_INFO, uinfo);
	}
	
}
