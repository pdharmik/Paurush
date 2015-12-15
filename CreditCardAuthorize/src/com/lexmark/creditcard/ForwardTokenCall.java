package com.lexmark.creditcard;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.TimeZone;
import java.util.UUID;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import sun.misc.BASE64Encoder;

/**
 * Servlet implementation class ForwardTokenCall
 */
public class ForwardTokenCall extends HttpServlet {
		private static final long serialVersionUID = 1L;
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
			doPost(request,response);		
		}
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        DefaultHttpClient httpclient = new DefaultHttpClient();
	        

	        HttpPost httpPost = new HttpPost("https://testsecureacceptance.cybersource.com/api/payment");
	        List <NameValuePair> nvps = new ArrayList <NameValuePair>();
	        
	        
	        
	        nvps.add(new BasicNameValuePair("access_key", request.getParameter("access_key")));
	        nvps.add(new BasicNameValuePair("profile_id", request.getParameter("profile_id")));
			nvps.add(new BasicNameValuePair("signature", request.getParameter("signature")));
	        nvps.add(new BasicNameValuePair("override_custom_receipt_page", request.getParameter("override_custom_receipt_page")));
	        nvps.add(new BasicNameValuePair("transaction_uuid",request.getParameter("transaction_uuid")));
	        nvps.add(new BasicNameValuePair("signed_field_names",request.getParameter("signed_field_names")));
	        nvps.add(new BasicNameValuePair("unsigned_field_names",request.getParameter("unsigned_field_names")));
	        nvps.add(new BasicNameValuePair("signed_date_time",request.getParameter("signed_date_time")));
	        nvps.add(new BasicNameValuePair("locale",request.getParameter("locale")));
	        nvps.add(new BasicNameValuePair("transaction_type",request.getParameter("transaction_type")));
	        nvps.add(new BasicNameValuePair("reference_number",request.getParameter("reference_number")));
	        nvps.add(new BasicNameValuePair("amount",request.getParameter("amount")));
	        nvps.add(new BasicNameValuePair("currency",request.getParameter("currency")));
	        nvps.add(new BasicNameValuePair("payment_method",request.getParameter("payment_method")));
	        
	        
	        nvps.add(new BasicNameValuePair("card_type",request.getParameter("card_type")));
	        nvps.add(new BasicNameValuePair("card_number",request.getParameter("card_number")));
	        nvps.add(new BasicNameValuePair("card_expiry_date",request.getParameter("card_expiry_date")));
	        nvps.add(new BasicNameValuePair("card_cvn",request.getParameter("card_cvn")));
	        nvps.add(new BasicNameValuePair("bill_to_forename",request.getParameter("bill_to_forename")));
	        nvps.add(new BasicNameValuePair("bill_to_surname",request.getParameter("bill_to_surname")));
	        nvps.add(new BasicNameValuePair("bill_to_email",request.getParameter("bill_to_email")));
	        nvps.add(new BasicNameValuePair("bill_to_phone",request.getParameter("bill_to_phone")));
	        nvps.add(new BasicNameValuePair("bill_to_address_line1",request.getParameter("bill_to_address_line1")));
	        nvps.add(new BasicNameValuePair("bill_to_address_city",request.getParameter("bill_to_address_city")));
	        
	        nvps.add(new BasicNameValuePair("bill_to_address_state",request.getParameter("bill_to_address_state")));
	        nvps.add(new BasicNameValuePair("bill_to_address_country",request.getParameter("bill_to_address_country")));
	        nvps.add(new BasicNameValuePair("bill_to_address_postal_code",request.getParameter("bill_to_address_postal_code")));
	        httpPost.setEntity(new UrlEncodedFormEntity(nvps,"UTF-8"));
	        StringBuffer req = new StringBuffer();
	        BufferedReader in1 = new BufferedReader(new InputStreamReader(httpPost.getEntity().getContent()));
	        String inputLine1;
	        while ((inputLine1 = in1.readLine()) != null) {
	        	req.append(inputLine1);
	        }	     
	        
	        HttpResponse response2 = httpclient.execute(httpPost);
		}

	        


}
