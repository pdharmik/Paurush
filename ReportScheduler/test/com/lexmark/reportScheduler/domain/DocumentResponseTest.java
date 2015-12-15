package com.lexmark.reportScheduler.domain;

import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import junit.framework.Assert;

import org.apache.commons.codec.binary.Base64;
import org.junit.Test;

import com.lexmark.properties.schema.sw.document.DocumentResponse;


public class DocumentResponseTest {
	@Test
	public void testUnmarsher() throws Exception {
		String xmlContent = "<response><status_code>1</status_code><r_object_id>0901ed8680029982</r_object_id><r_folder_path>/Services Web/servicesPortal/reportstest-bruce.pdf</r_folder_path></response>";
		ByteArrayInputStream xmlContentBytes = new ByteArrayInputStream (xmlContent.getBytes());
		JAXBContext context = JAXBContext.newInstance(DocumentResponse.class);
		Unmarshaller unmarshaller = context.createUnmarshaller();
		//note: setting schema to null will turn validator off
		unmarshaller.setSchema(null);
		DocumentResponse response = (DocumentResponse)unmarshaller.unmarshal(xmlContentBytes);
		Assert.assertNotNull(response);
		Assert.assertEquals("0901ed8680029982", response.getRObjectId());
		Assert.assertEquals(1, response.getStatusCode());
	}
	
	@Test
	public void testLocale() throws Exception {
		Locale locale = Locale.SIMPLIFIED_CHINESE;
		Locale tradition = Locale.TRADITIONAL_CHINESE;
		Locale  en = Locale.ENGLISH;
		
		Locale l = new Locale("a");
		
	}
}
