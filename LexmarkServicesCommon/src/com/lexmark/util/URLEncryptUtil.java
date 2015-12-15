package com.lexmark.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.InvalidParameterException;
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
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;




public class URLEncryptUtil {
	private static Logger logger = LogManager.getLogger(URLEncryptUtil.class);

	
	private static final String ENCRYPT_ALGURITHM = "HMACsha1";
	private final static String KeyString = 
		PropertiesMessageUtil.getPropertyMessage("com.lexmark.services.resources.reportURL", "public_key", Locale.US); 
	private final static int EXPIRATION_MINUTE = 30;
	private final static int EXPIRATION_DAYS = 14;
	
	public static String encrypt(String url) {
		return encrypt(url, false);
	}
	
	public static String encrypt(String url, boolean twoWeeksExpiration) {
		if(url == null) {
			throw new InvalidParameterException("url encrypt is null");
		}
		String encryptedURL = "";
		SecretKey key = new SecretKeySpec(Base64.decodeBase64(KeyString.getBytes()), 
		ENCRYPT_ALGURITHM); 
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ"); 

		Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
		if(twoWeeksExpiration) {
			calendar.add(Calendar.DATE, EXPIRATION_DAYS);
		} else {
			calendar.add(Calendar.MINUTE, EXPIRATION_MINUTE);
		}

		String urlBase = ""; 
		Date dt = calendar.getTime();
		df.setTimeZone(TimeZone.getTimeZone("GMT"));
		String ts = df.format(dt); 
		StringBuffer urlBuffer = new StringBuffer(); 


	
		// Perform a partial encoding of spaces in filenames 
	    // cannot use full encoding function because slashes and colons in http and directory need to be retained as-is
		urlBase = url.replaceAll(" ", "%20");
		
		try { 
			Mac mac = Mac.getInstance(ENCRYPT_ALGURITHM); 
			mac.init(key); 
			urlBuffer.append(urlBase);


			if(urlBase.indexOf("?") > -1) {
				urlBuffer.append("&");
			} else {
				urlBuffer.append("?");
			}
			

			urlBuffer.append("ts=").append(URLEncoder.encode(ts,"UTF-8")); 
			String token =Base64.encodeBase64URLSafeString(mac.doFinal(urlBuffer.toString().getBytes("UTF-8")));
			urlBuffer.append("&token=").append(token);
			encryptedURL = urlBuffer.toString();
			
			logger.debug("RAB STEP22 NEWX encryptedURL="+encryptedURL);

			
			
		} catch (UnsupportedEncodingException e) { 
			throw new RuntimeException(e); 
		} catch (NoSuchAlgorithmException e) { 
			throw new RuntimeException(e); 
		} catch (InvalidKeyException e) { 
			throw new RuntimeException(e); 
		} 
		return encryptedURL;
	}
}
