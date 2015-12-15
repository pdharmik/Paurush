package com.lexmark.api.security.filter;

import java.io.IOException;
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
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

/**
 * This class is for checking timestamp and token in request URL for it's
 * validity Authentication class
 */
public class SecurityServiceValidateFilter implements Filter {
	//private static final DateTimeFormatter ISO_FORMAT = ISODateTimeFormat.dateTimeParser();
	public static final DateTimeFormatter ISO_FORMAT = ISODateTimeFormat
			.dateTime().withZone(DateTimeZone.UTC);
	private static final Logger LOG = Logger.getLogger(SecurityServiceValidateFilter.class.getName());
	private static SecretKey key;

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#destroy()
	 */
	@Override
	public void destroy() {
		key = null;
	}

	/**
	 * HMACsha1 algorithm is used for decoding tokenStr
	 * 
	 * @param request
	 *            ServletRequest
	 * @param response
	 *            ServletResponse
	 * @param chain
	 *            FilterChain
	 * @throws IOException 
	 * 
	 * @throws ServletException 
	 */

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;

		// ts is the ISO 8601 formated time stamp. Request should fail after
		// this time has passed.
		String ts = req.getParameter("ts");
		if (ts == null) {
			resp.sendError(403);
			return;
		}
		DateTime dt = ISO_FORMAT.parseDateTime(ts);
		LOG.info("####### Date ------" + dt);
		String st = ISO_FORMAT.print(new DateTime((System.currentTimeMillis())));
		LOG.info("####### Date in Server ------" + st);
		if (dt.isBeforeNow()) {
			LOG.info("Outdated URL: " + req.getRequestURL().append('?').append(req.getQueryString()));
			resp.sendError(403);
			return;
		}

		// token is the URL safe Base64 encoding of the HMACsha1 token
		String tokenStr = request.getParameter("token");
		if (tokenStr == null) {
			LOG.info("Missing token: " + req.getRequestURL().append('?').append(req.getQueryString()));
			resp.sendError(403);
			return;
		}

		// Creating a canonical form of the URL will cause URLs to work more
		// like humans think (non-positional)
		String[] keyValues = req.getQueryString().split("&");
		Arrays.sort(keyValues);
		StringBuffer url = req.getRequestURL().append('?');
		boolean first = true;
		for (String keyval : keyValues) {
			if (!keyval.startsWith("token=")) {
				if (!first)
					url.append('&');
				else
					first = false;
				url.append(keyval);
			}
		}

		try {
			Mac mac = Mac.getInstance("HMACsha1");
			mac.init(key);
			byte[] hmac = mac.doFinal(url.toString().getBytes("UTF-8"));
			byte[] token = Base64.decodeBase64(tokenStr);
			LOG.info("hmac--------" + hmac);
			LOG.info("token-------" + token);
			if (!Arrays.equals(hmac, token)) { // If both HMACs don't match,
												// then the token is bad
				LOG.info("Forged or bad token: " + url + " token should have been: " + new String(Base64.encodeBase64URLSafe(hmac), "UTF-8") + " not " + tokenStr);
				resp.sendError(403);
				return;
			}
		} catch (Exception e) { // Catch-all for algorithm exceptions that
								// should never occur, and most certainly will
								// require developer intervention.
			LOG.log(Level.SEVERE, "Unable to verify token.", e);
			resp.sendError(500);
			return;
		}
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		byte[] keyBytes = Base64.decodeBase64(config.getInitParameter("key"));
		key = new SecretKeySpec(keyBytes, "HMACsha1");
		LOG.info("########### Initiating Security Filter for the Services ################");
		LOG.info("Key------" + key);
	}
}
