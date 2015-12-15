package com.lexmark.services.tests.junitTests;



import static com.lexmark.services.util.ChangeMgmtConstant.ADDRESSCLEANSEFIELDS_INPUT;
import static org.junit.Assert.assertEquals;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.portlet.MockRenderRequest;
import org.springframework.mock.web.portlet.MockRenderResponse;
import org.springframework.mock.web.portlet.MockResourceRequest;
import org.springframework.mock.web.portlet.MockResourceResponse;
import org.springframework.ui.Model;

import com.lexmark.domain.GenericAddress;
import com.lexmark.exceptionimpl.checked.LGSCheckedException;
import com.lexmark.exceptionimpl.runtime.LGSRuntimeException;
import com.lexmark.services.portlet.common.AddressCleansingController;

public class AddressCleansingControllerTest {
	private static Logger logger = LogManager.getLogger(AddressCleansingController.class);

	private MockRenderRequest renderRequest = new MockRenderRequest();
	private MockRenderResponse renderResponse = new MockRenderResponse();
	private MockResourceRequest resourceRequest = new MockResourceRequest();
	private MockResourceResponse resourceResponse = new MockResourceResponse();
	private GenericAddress genericAddress = new GenericAddress();
	private Model model;
	private AddressCleansingController controller;
	
	
	/**
	 * @throws Exception 
	 */
	@Before
	public void setUp() throws Exception {
		controller = new AddressCleansingController();
	}
	

	/**
	 * @throws Exception 
	 */
	@After
	public void tearDown() throws Exception {
	}
	
	/**
	 * @throws LGSCheckedException 
	 * @throws LGSRuntimeException 
	 */
	@Test
	public void testcodeReturn()throws LGSCheckedException,
	LGSRuntimeException {
		
		genericAddress.setErrorMsgForCleansing("1040-error message");
		 String output= AddressCleansingController.codeReturn(genericAddress,resourceRequest);
		 String check="false";
		if(output.length()>0){
			check="true";
		}
		 assertEquals("OK", "true",check);
	}
	
	/**
	 * @throws Exception 
	 */
	@Test
	public void testnewAddressValidateFromPopup() throws Exception {
		String s1[] = {"city"}; 
		resourceRequest.setParameter(ADDRESSCLEANSEFIELDS_INPUT[0],s1);
		resourceRequest.setParameter(ADDRESSCLEANSEFIELDS_INPUT[1],s1);
		resourceRequest.setParameter(ADDRESSCLEANSEFIELDS_INPUT[2],s1);
		resourceRequest.setParameter(ADDRESSCLEANSEFIELDS_INPUT[3],s1);
		resourceRequest.setParameter(ADDRESSCLEANSEFIELDS_INPUT[4],s1);
		resourceRequest.setParameter(ADDRESSCLEANSEFIELDS_INPUT[5],s1);
		resourceRequest.setParameter(ADDRESSCLEANSEFIELDS_INPUT[6],s1);
		
		controller.newAddressValidateFromPopup(resourceRequest, resourceResponse, model);
		
	}
	
}

