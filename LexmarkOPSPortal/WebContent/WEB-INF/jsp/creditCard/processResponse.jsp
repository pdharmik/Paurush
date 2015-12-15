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
<%@ page import="java.net.URL" %>
<%@ page import="javax.servlet.http.HttpServletRequest" %>
<%@ page import="com.liferay.portal.util.PortalUtil" %>


<%
// String custom_receipt_page = "http://localhost:8080/group/global-services/creditCard";
// String actionUrl = "https://testsecureacceptance.cybersource.com/silent/token/create";
Properties prop = new Properties();
URL reconstructedURL = new URL(request.getScheme(),
                               request.getServerName(),
                               request.getServerPort(),"");
prop.load(getServletContext().getResourceAsStream("/WEB-INF/context/creditCard.properties"));
String custom_receipt_page = reconstructedURL+prop.getProperty("PORTAL_URL");
String actionUrl = prop.getProperty("CYBSERSOURCE_URL");

String SECRET_KEY = "";
String access_key = "";
String profile_id = "";

if(session.getAttribute("shipToCountry")!=null && session.getAttribute("shipToCountry").toString().equalsIgnoreCase("US")){
	SECRET_KEY = prop.getProperty("US_SAP_SECRET_KEY");
	access_key = prop.getProperty("US_SAP_ACCESS_KEY");
	profile_id = prop.getProperty("US_SAP_PROFILEID");
}
if(session.getAttribute("shipToCountry")!=null && session.getAttribute("shipToCountry").toString().equalsIgnoreCase("CA")){
	SECRET_KEY = prop.getProperty("CA_SAP_SECRET_KEY");
	access_key = prop.getProperty("CA_SAP_ACCESS_KEY");
	profile_id = prop.getProperty("CA_SAP_PROFILEID");
}
HashMap params = new HashMap();
//params.put("access_key","c3f24b369f3b37c6bd7a80f2170fb6c3");
params.put("access_key",access_key);
//params.put("profile_id","Api0001");
params.put("profile_id",profile_id);
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

<script type="text/javascript">
	<%
		HttpServletRequest httpReq = PortalUtil.getOriginalServletRequest(request);
	%>
	<%= httpReq.getParameter("reason_code") %>
	<%    
		if(httpReq.getParameter("reason_code")!=null && httpReq.getParameter("reason_code").equalsIgnoreCase("100")){
	%>
		parent.document.getElementById('creditCardToken').value = '<%= httpReq.getParameter("payment_token") %>';
		parent.document.getElementById('creditCardNo').innerHTML = '<%= httpReq.getParameter("req_card_number") %>';
		parent.document.getElementById('creditCardNo').value = '<%= httpReq.getParameter("req_card_number") %>';
		parent.document.getElementById('creditCardExpirationDate').value = '<%= httpReq.getParameter("req_card_expiry_date") %>';
		parent.document.getElementById('creditCardType').value = '<%= httpReq.getParameter("req_card_type") %>';
		parent.document.getElementById('transactionId').value = '<%= httpReq.getParameter("transaction_id") %>';
		parent.document.getElementById('cardHoldername').value = '<%= httpReq.getParameter("req_bill_to_forename") %>'+" "+'<%= httpReq.getParameter("req_bill_to_surname") %>';
		parent.displayBillTo();
		parent.hideOverlayPopup();
		parent.closePopup();
	<%}else{%>		
		parent.document.getElementById('fromIframe').value='true';
		parent.document.getElementById('access_key').value = '<%= params.get("access_key") %>';
		parent.document.getElementById('profile_id').value = '<%= params.get("profile_id") %>';
		parent.document.getElementById('transaction_uuid').value = '<%= params.get("transaction_uuid") %>';
		parent.document.getElementById('signed_field_names').value = '<%= params.get("signed_field_names") %>';
		parent.document.getElementById('unsigned_field_names').value = '<%= params.get("unsigned_field_names") %>';
		parent.document.getElementById('signed_date_time').value = '<%= params.get("signed_date_time") %>';
		parent.document.getElementById('locale').value = '<%= params.get("locale") %>';
		parent.document.getElementById('transaction_type').value = '<%= params.get("transaction_type") %>';
		
		parent.document.getElementById('reference_number').value = '<%= params.get("reference_number") %>';
		parent.document.getElementById('amount').value = '<%= params.get("amount") %>';
		parent.document.getElementById('currency').value = '<%= params.get("currency") %>';
		parent.document.getElementById('payment_method').value = '<%= params.get("payment_method") %>';
		parent.document.getElementById('override_custom_receipt_page').value = '<%= params.get("override_custom_receipt_page") %>';
		parent.document.getElementById('signature').value = "<%= sign(buildDataToSign(params), SECRET_KEY).replaceAll("(\\r|\\n)", "") %>";
		parent.hideOverlayPopup();
		parent.document.getElementById('errorDiv2').innerHTML='<li><strong>There was an error processing the Credit Card. Please try again</strong></li>';
		parent.document.getElementById('responseError').style.display="block";
	<%}%>
</script>

<%!
	private String getUTCDateTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
		return sdf.format(new Date());
   	}

	private static final String HMAC_SHA256 = "HmacSHA256";
// 	private static final String SECRET_KEY = "2a639a00e987486292b0eca40cbb814f97e4eb3f4f844d8b8b13b6b34c1a5743f0fd9fda9a6844de86127157608918f6c73aac8bfe864fe18dc8215d4e097c7179bf9f3419ae41e38fb30bbb32ca2de729e2cdfb8dc54a94afe410737f0f3529272d075eb93e4d68a49d48e38914bf2eeda7692c259a4a2f93e40443ef611b84";
// 	private static final String SECRET_KEY = "a9b37c0392c84baaa3c34e11227bba380d360db6fc7e4dbf932128d1b8f6605866496d22a0804529947d52d6fa63a93e512f51ea808542bf9086ab9fdccbac8ca4cedeb7dc1c4ed9b0b7fc17c32f59f6606e7f8c3c1e4e06b87454f0fd13ccaaf9456e5c736a4c349edf99b47fac57cc5bb971b1347e49368f125cec10bec7f2";


	/* private String sign(HashMap params) throws InvalidKeyException, NoSuchAlgorithmException {		
		String finalSign = sign(buildDataToSign(params), SECRET_KEY);
		finalSign = finalSign.replaceAll("(\\r|\\n)", "");
		return finalSign;
	} */

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
