package com.lexmark.servlet;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.apache.commons.lang.StringEscapeUtils;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

/**
 * Created by IntelliJ IDEA.
 * User: jkalathi
 * Date: Aug 11, 2008
 * Time: 2:52:04 PM
 * To change this template use File | Settings | File Templates.
 */
/**
 * @author michakr
 * @version Add logic to hold department and currency information. Add logger
 *          and log all input request and errors
 */
public class PunchoutSetup extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private XPath xpath;
	

	/**
	 * @param servletConfig 
	 * @throws ServletException 
	 */
	public void init(ServletConfig servletConfig) throws ServletException {
		super.init(servletConfig);
		xpath = XPathFactory.newInstance().newXPath();
	}

	/**
	 * @param request 
	 * @param response 
	 * @throws ServletException 
	 * @throws IOException 
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		execute(request, response);
	}

	/**
	 * @param request 
	 * @param response 
	 * @throws ServletException 
	 * @throws IOException 
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		execute(request, response);
	}

	/**
	 * @param request 
	 * @param response 
	 * @throws ServletException 
	 * @throws IOException 
	 */
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String dept = request.getParameter("dept");
		String currency = request.getParameter("currency");
		StringBuilder inputData = new StringBuilder("****Input Request:");
		int inputByte = 0;
		DocumentBuilderFactory domFactory = DocumentBuilderFactory
				.newInstance();
		try {
			domFactory.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
		} catch (ParserConfigurationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		domFactory.setNamespaceAware(true);
		domFactory.setValidating(false);
		DocumentBuilder builder = null;
		Document doc = null;
		try {
			builder = domFactory.newDocumentBuilder();
			InputStream sis = request.getInputStream();
			
			
			InputSource is = new InputSource(sis);
			// start - log all input data from request
			
			if (sis.markSupported()) {
				
				sis.mark(sis.available());
				
				BufferedInputStream bis = new BufferedInputStream(sis);
				
				while ((inputByte = bis.read()) != -1) {
					
					inputData.append((char) inputByte);
					
				}
				
				sis.reset();
			}
		
			// end - log all input data from request
			
			doc = builder.parse(is);
			
		} catch (Exception e) {
			e.printStackTrace();
			
			throw new RuntimeException(e);
		}
		String buyerCookie = null;
		String identity = null;
		String operation = null;
		String formPostURL = null;
		String supplierCookie = null;
		String networkUserId = null;
		String sharedSecret = null;
		try {
			buyerCookie = xpath.evaluate(
					"//PunchOutSetupRequest/BuyerCookie/text()", doc);
			identity = xpath.evaluate("//From/Credential/Identity/text()", doc);
			operation = xpath
					.evaluate("//PunchOutSetupRequest/@operation", doc);
			formPostURL = xpath.evaluate("//BrowserFormPost/URL/text()", doc);
			networkUserId = xpath.evaluate(
					"//Sender/Credential/Identity/text()", doc);
			sharedSecret = xpath.evaluate(
					"//Sender/Credential/SharedSecret/text()", doc);
			if (operation.equalsIgnoreCase("create")) {
				supplierCookie = buyerCookie;
			} else {
				supplierCookie = xpath.evaluate(
						"//ItemID/SupplierPartAuxiliaryID/text()", doc);
			}
         
			if (identity.equalsIgnoreCase("lxkstatenj")) {
				Calendar c = Calendar.getInstance();
				Double d = Math.random();
				Long l = c.getTimeInMillis();
				supplierCookie = l.toString().concat(d.toString());
			}
			
		} catch (XPathExpressionException ex) {
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}

		String payloadId = buyerCookie + "@lexmark.com";
		String url = "https://portal.lexmark.com/LexmarkServicesPunchout/punchoutcatalog?";
		String params = getParams(buyerCookie, supplierCookie, identity,
				operation, formPostURL, networkUserId, sharedSecret, dept,
				currency);
		String startPageURL = StringEscapeUtils.escapeXml(url + params);

		String iso_8601_date_format = "yyyy-MM-dd'T'HH:mm:ssZ";
		final SimpleDateFormat iso8601 = new SimpleDateFormat(
				iso_8601_date_format);
		String ts = iso8601.format(new Date(System.currentTimeMillis()));

		ServletOutputStream out = response.getOutputStream();
		String rsp = "<?xml version=\"1.0\"?>\n"
				+ "<!DOCTYPE cXML SYSTEM \"http://xml.cxml.org/schemas/cXML/1.2.014/cXML.dtd\">\n"
				+ "<cXML payloadID=\"" + payloadId + "\" timestamp=\"" + ts
				+ "\">\n" + "<Response>\n"
				+ "<Status code=\"200\" text=\"success\"/>\n"
				+ "<PunchOutSetupResponse>\n" + "<StartPage>\n" + "<URL>"
				+ startPageURL + "</URL>\n" + "</StartPage>\n"
				+ "</PunchOutSetupResponse>\n" + "</Response>\n" + "</cXML>";
				
		 
		out.println(rsp);
	}

	/**
	 * @param buyerCookie 
	 * @param supplierCookie 
	 * @param identity 
	 * @param operation 
	 * @param formPostURL 
	 * @param networkUserId 
	 * @param sharedSecret 
	 * @param dept 
	 * @param currency 
	 * @return String 
	 */
	public String getParams(String buyerCookie, String supplierCookie,
			String identity, String operation, String formPostURL,
			String networkUserId, String sharedSecret, String dept,
			String currency) {
		StringBuffer sb = new StringBuffer();
		if (buyerCookie != null){
			sb.append("buyerCookie=").append(buyerCookie).append("&");
		}
		if (supplierCookie != null){
			sb.append("supplierCookie=").append(supplierCookie).append("&");
		}
		if (identity != null){
			sb.append("identity=").append(identity).append("&");
		}
		if (operation != null){
			sb.append("operation=").append(operation).append("&");
		}
		if (formPostURL != null){
			sb.append("formPostURL=").append(formPostURL).append("&");
		}
		if (networkUserId != null){
			sb.append("networkUserId=").append(networkUserId).append("&");
		}
		if (sharedSecret != null){
			sb.append("sharedSecret=").append(sharedSecret).append("&");
		}
		if (dept != null){
			sb.append("dept=").append(dept).append("&");
		}
		if (currency != null){
			sb.append("currency=").append(currency).append("&");
		}
		return ((sb == null) ? "" : sb.substring(0, sb.length() - 1));
	}
}
