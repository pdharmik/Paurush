<%@page import="java.text.SimpleDateFormat"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.TimeZone" %>
<%@ page import="java.util.UUID" %>
<%@ page import="java.util.Enumeration" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Map" %>
<%@ page import="sun.misc.BASE64Encoder" %>
<%@ page import="javax.crypto.Mac" %>
<%@ page import="javax.crypto.spec.SecretKeySpec" %>
<%@ page import="java.security.InvalidKeyException" %>
<%@ page import="java.security.NoSuchAlgorithmException" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Iterator" %>

<%
HashMap params = new HashMap();
params.put("access_key","c3f24b369f3b37c6bd7a80f2170fb6c3");
params.put("profile_id","Api0001");
params.put("transaction_uuid",UUID.randomUUID());
params.put("signed_field_names","access_key,profile_id,transaction_uuid,signed_field_names,unsigned_field_names,signed_date_time,locale,transaction_type,reference_number,amount,currency,payment_method");
params.put("unsigned_field_names","card_type,card_number,card_expiry_date,card_cvn,bill_to_forename,bill_to_surname,bill_to_email,bill_to_phone,bill_to_address_line1,bill_to_address_city,bill_to_address_state,bill_to_address_country,bill_to_address_postal_code");
params.put("signed_date_time",getUTCDateTime());
params.put("locale","en");
params.put("transaction_type","create_payment_token");
params.put("reference_number","20130116030000");
params.put("amount","1");
params.put("currency","USD");
params.put("payment_method","card");
%>
<html>
	<body>
		<form id="payment_form" action="https://testsecureacceptance.cybersource.com/api/payment" method="post">
			<%
			Iterator paramsIterator = params.entrySet().iterator();
			while (paramsIterator.hasNext()) {
			Map.Entry param = (Map.Entry) paramsIterator.next();
			out.print("<input type=\"hidden\" id=\"" + param.getKey() + "\" name=\"" + param.getKey() + "\" value=\"" + param.getValue() + "\"/>\n");
			}
			out.print("<input type=\"hidden\" id=\"signature\" name=\"signature\" value=\"" + sign(params) + "\"/>\n");
			%>
			<table>
				
				<tr><td>card_type:</td><td><input type="text" name="card_type"></td></tr>
				<tr><td>card_number:</td><td><input type="text" name="card_number"></td></tr>
				<tr><td>card_expiry_date:</td><td><input type="text" name="card_expiry_date"></td></tr>
				<tr><td>card_cvn:</td><td><input type="text" name="card_cvn" size="25"></td></tr>
			</table>
			
			<h2>Order Information</h2>
			
			<table>	
			
				<tr><td>First Name:</td><td><input type="text" name="bill_to_forename"></td></tr>
				<tr><td>Last Name:</td><td><input type="text" name="bill_to_surname"></td></tr>
				<tr><td>Email Address:</td><td><input type="text" name="bill_to_email"></td></tr>
				<tr><td>Phone No:</td><td><input type="text" name="bill_to_phone"></td></tr>
				<tr><td>Address:</td><td><input type="text" name="bill_to_address_line1"></td></tr>
				<tr><td>City:</td><td><input type="text" name="bill_to_address_city"></td></tr>
				<tr><td>State/Province:</td><td><input type="text" name="bill_to_address_state"></td></tr>
				<tr><td>Country:</td><td><input type="text" name="bill_to_address_country"></td></tr>
				<tr><td>Postal Code:</td><td><input type="text" name="bill_to_address_postal_code"></td></tr>
				
							
			</table>
			 
			<input type="submit" id="submit" name="submit" value="Submit"/>
		</form>
	</body>
</html>
<%!
	private String getUTCDateTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
		return sdf.format(new Date());
   	}

	private static final String HMAC_SHA256 = "HmacSHA256";
	private static final String SECRET_KEY = "2a639a00e987486292b0eca40cbb814f97e4eb3f4f844d8b8b13b6b34c1a5743f0fd9fda9a6844de86127157608918f6c73aac8bfe864fe18dc8215d4e097c7179bf9f3419ae41e38fb30bbb32ca2de729e2cdfb8dc54a94afe410737f0f3529272d075eb93e4d68a49d48e38914bf2eeda7692c259a4a2f93e40443ef611b84";

	private String sign(HashMap params) throws InvalidKeyException, NoSuchAlgorithmException {
		return sign(buildDataToSign(params), SECRET_KEY);
	}

	private String sign(String data, String secretKey) throws InvalidKeyException, NoSuchAlgorithmException {
		SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(), HMAC_SHA256);
	    Mac mac = Mac.getInstance(HMAC_SHA256);
		mac.init(secretKeySpec);
		byte[] rawHmac = mac.doFinal(data.getBytes());
		    BASE64Encoder encoder = new BASE64Encoder();
		return encoder.encodeBuffer(rawHmac).replace("\n", "");
	}
	
	private String buildDataToSign(HashMap params) {
		String[] signedFieldNames = String.valueOf(params.get("signed_field_names")).split(",");
		ArrayList<String>dataToSign = new ArrayList<String>();
		for (String signedFieldName : signedFieldNames) {
		dataToSign.add(signedFieldName + "=" + String.valueOf(params.get(signedFieldName)));
		    }
		return commaSeparate(dataToSign);
	}
	
	private String commaSeparate(ArrayList<String>dataToSign) {
		StringBuilder csv = new StringBuilder();
		for (Iterator<String> it = dataToSign.iterator(); it.hasNext(); ) {
			csv.append(it.next());
			if (it.hasNext()) {
				csv.append(",");
			}
	    }
	return csv.toString();
	}
%>
