package com.lexmark.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.InvalidParameterException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;




public class URLEncryptUtilForCustomerInvoice {
	private static Logger logger = LogManager.getLogger(URLEncryptUtilForCustomerInvoice.class);

	
	private static final String ENCRYPT_ALGORITHM = "HmacSHA256".intern();
	private	static final String KEY_ALGORITHM = "SHA-256".intern();
	private final static String KeyString = 
		PropertiesMessageUtil.getPropertyMessage("com.lexmark.services.resources.customerInvoiceURL", "public_key", Locale.US);
	
	private final static String BASEURL = 
		PropertiesMessageUtil.getPropertyMessage("com.lexmark.services.resources.customerInvoiceURL", "customerInvoice.baseURL", Locale.US);
	
	private final static String HASH="#";
	private static final DateTimeFormatter ISO_FORMAT = ISODateTimeFormat.dateTime().withZone(DateTimeZone.UTC);
	
	
	public static String encrypt(String sapObjectId) {
		
		if(StringUtils.isBlank(sapObjectId)){
			return HASH;
		}
		byte[] key=null;
		try {
			 key = MessageDigest.getInstance(KEY_ALGORITHM).digest(KeyString.getBytes("UTF-8"));
		} catch (NoSuchAlgorithmException e1) {
			// TODO Auto-generated catch block
			logger.debug("error occured "+e1.getCause());
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			logger.debug("error occured "+e1.getCause());
		}
		SecretKey secretKey = new SecretKeySpec(key,ENCRYPT_ALGORITHM); 
		

		
		
		StringBuilder urlBuffer = new StringBuilder(BASEURL); 
		urlBuffer.append(sapObjectId);
			
		
		 String ts = ISO_FORMAT.print(System.currentTimeMillis());
		
		
	
		// Perform a partial encoding of spaces in filenames 
	    // cannot use full encoding function because slashes and colons in http and directory need to be retained as-is
		
		
		try { 
			Mac mac = Mac.getInstance(ENCRYPT_ALGORITHM); 
			
			mac.init(secretKey); 
			urlBuffer.append("?");
			urlBuffer.append("ts=").append(URLEncoder.encode(ts,"UTF-8")); 
			 byte[] signature = mac.doFinal(urlBuffer.toString().getBytes("UTF-8"));
			 urlBuffer.append("&token=").append(Base64.encodeBase64URLSafeString(signature));
			 
			logger.debug("the encryptedURL="+urlBuffer.toString());
			 
			 
			 
			 
			 
			
			
			
		} catch (UnsupportedEncodingException e) { 
			throw new RuntimeException(e); 
		} catch (NoSuchAlgorithmException e) { 
			throw new RuntimeException(e); 
		} catch (InvalidKeyException e) { 
			throw new RuntimeException(e); 
		} 
		return urlBuffer.toString();
	}
	
}
