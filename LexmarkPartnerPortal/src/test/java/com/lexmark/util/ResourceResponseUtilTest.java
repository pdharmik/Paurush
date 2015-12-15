package com.lexmark.util;

import java.io.PrintWriter;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.mock.web.portlet.MockResourceResponse;

public class ResourceResponseUtilTest {
	@Test
	public void testGetUTF8PrintWrtierWithBOM() throws Exception{
		MockResourceResponse response = new MockResourceResponse();
		PrintWriter out = ResourceResponseUtil.getUTF8PrintWrtierWithBOM(response);
		Assert.assertNotNull(out);
	}
}
