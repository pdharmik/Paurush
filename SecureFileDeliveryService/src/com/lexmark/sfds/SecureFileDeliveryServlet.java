package com.lexmark.sfds;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

public class SecureFileDeliveryServlet implements Filter {
	private static final DateTimeFormatter ISO_FORMAT = ISODateTimeFormat.dateTimeParser();
	private static final Logger LOG = Logger.getLogger(SecureFileDeliveryServlet.class.getName());

	private SecretKey key=null;
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;

		//ts is the ISO 8601 formated time stamp. Request should fail after this time has passed.
		String ts = req.getParameter("ts");
		if(ts==null) {
			resp.sendError(403);
			return;
		}
		DateTime dt = ISO_FORMAT.parseDateTime(ts);
		if(dt.isBeforeNow()) {
			LOG.info("Outdated URL: "+req.getRequestURL().append('?').append(req.getQueryString()));
			resp.sendError(403);
			return;
		}

		//token is the URL safe Base64 encoding of the HMACsha1 token
		String tokenStr = request.getParameter("token");
		if(tokenStr==null) {
			LOG.info("Missing token: "+req.getRequestURL().append('?').append(req.getQueryString()));
			resp.sendError(403);
			return;
		} 
		
		//Creating a canonical form of the URL will cause URLs to work more like humans think (non-positional)
		String[] keyValues = req.getQueryString().split("&");
		Arrays.sort(keyValues);
		StringBuffer url = req.getRequestURL().append('?');
		boolean first=true;
		for(String keyval: keyValues){
			if(!keyval.startsWith("token=")){
				if(!first) url.append('&');
				else first = false;
				url.append(keyval);
			}
		}
		
		try {
			Mac mac = Mac.getInstance("HMACsha1");
			mac.init(key);
			byte[] hmac = mac.doFinal(url.toString().getBytes("UTF-8"));
			byte[] token = Base64.decodeBase64(tokenStr);
			
			if(!Arrays.equals(hmac,token)) { //If both HMACs don't match, then the token is bad
				LOG.info("Forged or bad token: "+url+" token should have been: "+new String(Base64.encodeBase64URLSafe(hmac),"UTF-8")+" not "+tokenStr);
				resp.sendError(403);
				return;
			}
		} catch (Exception e) { //Catch-all for algorithm exceptions that should never occur, and most certainly will require developer intervention.
			LOG.log(Level.SEVERE,"Unable to verify token.",e);
			resp.sendError(500);
			return;
		}
		chain.doFilter(request, response);
	}

	public void destroy() {
		key=null;
	}
	
	public void init(FilterConfig config) throws ServletException {
		byte[] keyBytes = Base64.decodeBase64(config.getInitParameter("key"));
		key = new SecretKeySpec(keyBytes, "HMACsha1");
	}
	
	public static void main(String[] args) {
		//Example URL Builder
		//Only requires commons-codec library.
		String keyStr = "UeV5DfuEzXsLsTzRP1a3ragNAQRt73GrOK2XZkjm6zIXyNZD66LZt4LvUuVh3O8RUnoZhDPADxu6lXW030i9NSv_dfciMO3JDA-Dm5ePlvVUKW7RMXuVrJG8wjfOiiQQA7VoTXYiYPdTfawRvzaiqrwLD06dKXA4Mww2KLJppGA";
		SecretKey key = new SecretKeySpec(Base64.decodeBase64(keyStr), "HMACsha1");
		String baseUrl="http://localhost:8080/files";
		String filePath="/hello.txt";
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
		Date dt = new Date(System.currentTimeMillis()+1000*60); //1 minute offset
		String ts = df.format(dt);
		StringBuffer url = new StringBuffer();
		try {
			Mac mac = Mac.getInstance("HMACsha1");
			mac.init(key);
			url.append(baseUrl)
			   .append(filePath)
			   .append("?ts=").append(URLEncoder.encode(ts,"UTF-8"));
			String token = Base64.encodeBase64URLSafeString(mac.doFinal(url.toString().getBytes("UTF-8")));
			url.append("&token=").append(token);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		} catch (InvalidKeyException e) {
			throw new RuntimeException(e);
		}
		
		//System.out.println(url);
		
	}
	
}
