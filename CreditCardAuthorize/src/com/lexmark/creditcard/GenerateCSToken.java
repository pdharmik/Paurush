package com.lexmark.creditcard;

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
 * Servlet implementation class GenerateCSToken
 */
public class GenerateCSToken extends HttpServlet{
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		doPost(request,response);		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DefaultHttpClient httpclient = new DefaultHttpClient();
        

        HttpPost httpPost = new HttpPost("https://testsecureacceptance.cybersource.com/api/payment");
//        HttpPost httpPost = new HttpPost("http://localhost:8080/CreditCardAuthorize/ProcessCSResponse");
        List <NameValuePair> nvps = new ArrayList <NameValuePair>();
        
        HashMap params = new HashMap();
        params.put("access_key","c3f24b369f3b37c6bd7a80f2170fb6c3");
        params.put("profile_id","Api0001");
        params.put("transaction_uuid",UUID.randomUUID());
        params.put("signed_field_names","access_key,profile_id,transaction_uuid,signed_field_names,unsigned_field_names,signed_date_time,locale,transaction_type,reference_number,amount,currency,payment_method,override_custom_receipt_page");
        params.put("unsigned_field_names","card_type,card_number,card_expiry_date,card_cvn,bill_to_forename,bill_to_surname,bill_to_email,bill_to_phone,bill_to_address_line1,bill_to_address_city,bill_to_address_state,bill_to_address_country,bill_to_address_postal_code");
        params.put("signed_date_time",getUTCDateTime());
        params.put("locale","en");
        params.put("transaction_type","create_payment_token");
        params.put("reference_number","20130116030000");
        params.put("amount","0");
        params.put("currency","USD");
        params.put("payment_method","card");
        params.put("override_custom_receipt_page","http://localhost:8080/CreditCardAuthorize/ProcessCSResponse");
        
        nvps.add(new BasicNameValuePair("access_key", "c3f24b369f3b37c6bd7a80f2170fb6c3"));
        nvps.add(new BasicNameValuePair("profile_id", "Api0001"));
        try {
			nvps.add(new BasicNameValuePair("signature", sign(params)));
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        nvps.add(new BasicNameValuePair("override_custom_receipt_page", "http://localhost:8080/CreditCardAuthorize/ProcessCSResponse"));
        nvps.add(new BasicNameValuePair("transaction_uuid",UUID.randomUUID().toString()));
        nvps.add(new BasicNameValuePair("signed_field_names","access_key,profile_id,transaction_uuid,signed_field_names,unsigned_field_names,signed_date_time,locale,transaction_type,reference_number,amount,currency,payment_method,override_custom_receipt_page"));
        nvps.add(new BasicNameValuePair("unsigned_field_names","card_type,card_number,card_expiry_date,card_cvn,bill_to_forename,bill_to_surname,bill_to_email,bill_to_phone,bill_to_address_line1,bill_to_address_city,bill_to_address_state,bill_to_address_country,bill_to_address_postal_code"));
        nvps.add(new BasicNameValuePair("signed_date_time",getUTCDateTime().toString()));
        nvps.add(new BasicNameValuePair("locale","en"));
        nvps.add(new BasicNameValuePair("transaction_type","create_payment_token"));
        nvps.add(new BasicNameValuePair("reference_number","20130116030000"));
        nvps.add(new BasicNameValuePair("amount","0"));
        nvps.add(new BasicNameValuePair("currency","USD"));
        nvps.add(new BasicNameValuePair("payment_method","card"));
        
        
        nvps.add(new BasicNameValuePair("card_type","001"));
        nvps.add(new BasicNameValuePair("card_number","4111111111111111"));
        nvps.add(new BasicNameValuePair("card_expiry_date","02-2015"));
        nvps.add(new BasicNameValuePair("card_cvn","123"));
        nvps.add(new BasicNameValuePair("bill_to_forename","Joyeeta"));
        nvps.add(new BasicNameValuePair("bill_to_surname","Chowdhury"));
        nvps.add(new BasicNameValuePair("bill_to_email","jchowdhu@lexmark.com"));
        nvps.add(new BasicNameValuePair("bill_to_phone","8592322000"));
        nvps.add(new BasicNameValuePair("bill_to_address_line1","3351 Cove Lake DR"));
        nvps.add(new BasicNameValuePair("bill_to_address_city","Lexington"));
        
        nvps.add(new BasicNameValuePair("bill_to_address_state","KY"));
        nvps.add(new BasicNameValuePair("bill_to_address_country","USA"));
        nvps.add(new BasicNameValuePair("bill_to_address_postal_code","40515"));
        httpPost.setEntity(new UrlEncodedFormEntity(nvps,"UTF-8"));
        StringBuffer req = new StringBuffer();
        BufferedReader in1 = new BufferedReader(new InputStreamReader(httpPost.getEntity().getContent()));
        String inputLine1;
        while ((inputLine1 = in1.readLine()) != null) {
        	req.append(inputLine1);
        }        
        
        HttpResponse response2 = httpclient.execute(httpPost);

        try {
            
            HttpEntity entity2 = response2.getEntity();
            StringBuffer jb = new StringBuffer();
            BufferedReader in2 = new BufferedReader(new InputStreamReader(
            		response2.getEntity().getContent()));
            String inputLine;
            while ((inputLine = in2.readLine()) != null) {
                jb.append(inputLine);
            }
            
            // do something useful with the response body
            // and ensure it is fully consumed
            EntityUtils.consume(entity2);
        } finally {
            httpPost.releaseConnection();
        }
    }
	
	private static String getUTCDateTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
		return sdf.format(new Date());
   	}

	private static final String HMAC_SHA256 = "HmacSHA256";
	private static final String SECRET_KEY = "2a639a00e987486292b0eca40cbb814f97e4eb3f4f844d8b8b13b6b34c1a5743f0fd9fda9a6844de86127157608918f6c73aac8bfe864fe18dc8215d4e097c7179bf9f3419ae41e38fb30bbb32ca2de729e2cdfb8dc54a94afe410737f0f3529272d075eb93e4d68a49d48e38914bf2eeda7692c259a4a2f93e40443ef611b84";

	private static String sign(HashMap params) throws InvalidKeyException, NoSuchAlgorithmException {
		return sign(buildDataToSign(params), SECRET_KEY);
	}

	private static String sign(String data, String secretKey) throws InvalidKeyException, NoSuchAlgorithmException {
		SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(), HMAC_SHA256);
	    Mac mac = Mac.getInstance(HMAC_SHA256);
		mac.init(secretKeySpec);
		byte[] rawHmac = mac.doFinal(data.getBytes());
		    BASE64Encoder encoder = new BASE64Encoder();
		return encoder.encodeBuffer(rawHmac).replace("\n", "");
	}
	
	private static String buildDataToSign(HashMap params) {
		String[] signedFieldNames = String.valueOf(params.get("signed_field_names")).split(",");
		ArrayList<String>dataToSign = new ArrayList<String>();
		for (String signedFieldName : signedFieldNames) {
		dataToSign.add(signedFieldName + "=" + String.valueOf(params.get(signedFieldName)));
		    }
		return commaSeparate(dataToSign);
	}
	
	private static String commaSeparate(ArrayList<String>dataToSign) {
		StringBuilder csv = new StringBuilder();
		for (Iterator<String> it = dataToSign.iterator(); it.hasNext(); ) {
			csv.append(it.next());
			if (it.hasNext()) {
				csv.append(",");
			}
	    }
	return csv.toString();
	}
}
