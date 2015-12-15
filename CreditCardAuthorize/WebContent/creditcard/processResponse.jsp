<%@page import="java.text.SimpleDateFormat"%>
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
<%@ page import="java.util.Properties" %>
<%@ page import="java.util.ResourceBundle" %>

<%
Properties prop = new Properties();
prop.load(getServletContext().getResourceAsStream("/WEB-INF/creditCard.properties"));
String custom_receipt_page = prop.getProperty("overrideCustomReceiptPage");
String actionUrl = prop.getProperty("cyberSourceUrl");
HashMap params = new HashMap();
params.put("access_key","c3f24b369f3b37c6bd7a80f2170fb6c3");
params.put("profile_id","Api0001");
params.put("transaction_uuid",UUID.randomUUID());
params.put("signed_field_names","access_key,profile_id,transaction_uuid,signed_field_names,unsigned_field_names,signed_date_time,locale,transaction_type,reference_number,amount,currency,payment_method,override_custom_receipt_page");
params.put("unsigned_field_names","card_type,card_number,card_expiry_date,card_cvn,bill_to_forename,bill_to_surname,bill_to_email,bill_to_address_line1,bill_to_address_city,bill_to_address_state,bill_to_address_country,bill_to_address_postal_code");
params.put("signed_date_time",getUTCDateTime());
params.put("locale","en");
params.put("transaction_type","create_payment_token");
params.put("reference_number","20130116030000");
params.put("amount","0");
params.put("currency","USD");
params.put("payment_method","card");
params.put("override_custom_receipt_page",custom_receipt_page);
%>


<script>
	<%
		ResourceBundle messages = ResourceBundle.getBundle("messages");
		String errCCProcessingStr = messages.getString("error.message.creditCardProcessing");
		
		if(request.getParameter("reason_code")!=null && request.getParameter("reason_code").equalsIgnoreCase("100")){
	%>
		parent.document.getElementById('creditCardToken').value = '<%= request.getParameter("payment_token") %>';
		parent.document.getElementById('creditCardNo').innerHTML = '<%= request.getParameter("req_card_number") %>';
		parent.document.getElementById('creditCardNo').value = '<%= request.getParameter("req_card_number") %>';
		parent.document.getElementById('creditCardExpirationDate').value = '<%= request.getParameter("req_card_expiry_date") %>';
		parent.document.getElementById('creditCardType').value = '<%= request.getParameter("req_card_type") %>';
		parent.document.getElementById('transactionId').value = '<%= request.getParameter("transaction_id") %>';
		parent.document.getElementById('cardHoldername').value = '<%= request.getParameter("req_bill_to_forename") %>'+" "+'<%= request.getParameter("req_bill_to_surname") %>';
		parent.hideOverlayPopup();
		parent.closePopup();
	<%}else{%>		
		parent.document.getElementById('fromIframe').value='true';
		parent.document.getElementById('signed_date_time').value = '<%= params.get("signed_date_time") %>';
		parent.document.getElementById('transaction_uuid').value = '<%= params.get("transaction_uuid") %>';
		parent.document.getElementById('signature').value = "<%= sign(params) %>";
		parent.hideOverlayPopup();
		parent.document.getElementById('errorDiv2').innerHTML='<li><strong><%=errCCProcessingStr %></strong></li>';
		parent.document.getElementById('responseError').style.display='block';
		parent.document.getElementById('requestError').style.display='none';
	<%}%>
</script>

<%!
	private String getUTCDateTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
		return sdf.format(new Date());
   	}

	private static final String HMAC_SHA256 = "HmacSHA256";
	private static final String SECRET_KEY = "2a639a00e987486292b0eca40cbb814f97e4eb3f4f844d8b8b13b6b34c1a5743f0fd9fda9a6844de86127157608918f6c73aac8bfe864fe18dc8215d4e097c7179bf9f3419ae41e38fb30bbb32ca2de729e2cdfb8dc54a94afe410737f0f3529272d075eb93e4d68a49d48e38914bf2eeda7692c259a4a2f93e40443ef611b84";

	private String sign(HashMap params) throws InvalidKeyException, NoSuchAlgorithmException {		
		String finalSign = sign(buildDataToSign(params), SECRET_KEY);
		finalSign = finalSign.replaceAll("(\\r|\\n)", "");
		return finalSign;
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
