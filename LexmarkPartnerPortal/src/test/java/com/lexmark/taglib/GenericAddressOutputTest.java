package com.lexmark.taglib;

import javax.servlet.jsp.tagext.TagSupport;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockPageContext;
import org.springframework.mock.web.MockServletContext;

import com.lexmark.domain.GenericAddress;

public class GenericAddressOutputTest {
	private static Logger LOGGER = LogManager.getLogger(GenericAddressOutputTest.class);
	private GenericAddressOutput genericAddressOutput;
	private MockPageContext mockPageContext;
	private MockServletContext mockServletContext;
	
	@Before
	public void setUp() throws Exception {
		mockServletContext = new MockServletContext();
		mockPageContext = new MockPageContext(mockServletContext);
		genericAddressOutput = new GenericAddressOutput();
		genericAddressOutput.setPageContext(mockPageContext);
	}
	
	@Test
	public void testValueNull() throws Exception{
		genericAddressOutput.setValue(null);
		int result = genericAddressOutput.doStartTag();
		Assert.assertTrue(result == TagSupport.SKIP_BODY);
	}
	
	@Test
	public void testShowState() throws Exception{
		GenericAddress genericAddress = new GenericAddress();
		genericAddress.setAddressId("addressId");
		genericAddress.setAddressLine1("addressLine1");
		genericAddress.setAddressLine2("addressLine2");
		genericAddress.setAddressLine3("addressLine3");
		genericAddress.setAddressLine4("addressLine4");
		genericAddress.setAddressName("addressName");
		genericAddress.setCity("city");
		genericAddress.setCountry("country");
		genericAddress.setPostalCode("postalCode");
		genericAddress.setProvince("province");
		genericAddress.setState("state");
		genericAddress.setStateProvince("stateProvince");
		
		genericAddressOutput.setValue(genericAddress);
		genericAddressOutput.doStartTag();
		String output = ((MockHttpServletResponse) mockPageContext
				.getResponse()).getContentAsString();
		String expectResult = "addressLine1<br>addressLine2<br>addressLine3<br>city,&nbsp;state,&nbsp;country,&nbsp;postalCode";
		Assert.assertTrue(expectResult.equals(output));
	}
	
	@Test
	public void testShowProvince() throws Exception{
		GenericAddress genericAddress = new GenericAddress();
		genericAddress.setAddressId("addressId");
		genericAddress.setAddressLine1("addressLine1");
		genericAddress.setPostalCode("postalCode");
		genericAddress.setProvince("province");
		genericAddress.setState(null);
		genericAddress.setStateProvince("stateProvince");
		
		genericAddressOutput.setValue(genericAddress);
		genericAddressOutput.doStartTag();
		String output = ((MockHttpServletResponse) mockPageContext
				.getResponse()).getContentAsString();
		LOGGER.info(output);
		String expectResult = "addressLine1<br>province,&nbsp;postalCode";
		Assert.assertTrue(expectResult.equals(output));
	}
}
