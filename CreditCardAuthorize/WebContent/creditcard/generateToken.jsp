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
<%@ page import="java.util.Properties" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- <script src="/CreditCardAuthorize/js/jQuery/jquery.min.js"></script> -->
<!-- <script src="/CreditCardAuthorize/js/jQuery/jquery-ui.min.js"></script> -->
<script src="/CreditCardAuthorize/js/jQuery/jquery.ui.widget.js"></script>
<!-- <script src="/CreditCardAuthorize/js/jQuery/jquery.qtip-1.0.min.js"></script> -->
<script src="/CreditCardAuthorize/js/jQuery/jquery.qtip-1.0.0-rc3.min.js"></script>
<script src="/CreditCardAuthorize/js/mps.js"></script>

<style type="text/css"><%@ include file="../css/mps.css" %></style>
<fmt:setBundle basename="resources.messages" />
<div id="totalContentCredit">
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
params.put("currency",request.getParameter("currency"));
params.put("payment_method","card");
params.put("override_custom_receipt_page",custom_receipt_page);
%>


<div id="token"></div>
<div id="response_card_number"></div>
<div id="processingHintPopup" tabindex="-1" ></div>
<iframe id="paymentSubmit" name="paymentSubmit" style="width:0px;height:0px;border:0px solid #fff;"></iframe>

<script>

	function closePopup(){
		hideOverlay();
		dialog.dialog('destroy');				 
		dialog=null;
		jQuery('#totalContentCredit').remove();
	}
	
	function clearFrameContent(){
		var cardIFrame = document.getElementById("paymentSubmit");
		cardIFrame.src = 'about:blank';
	}
	
	function showOverlay(){
		jQuery("#overlay").css({
			display:"block",
			top:0,
			left:0,
			width : jQuery.ui.dialog.overlay.width(),
			height : jQuery.ui.dialog.overlay.height()
		}).addClass("waiting");
		jQuery("#processingHint").css({
			display:"block",
			width: "300px",
			height:"50px",
			left: parseInt( jQuery.ui.dialog.overlay.width() )/2-100,
			top: document.documentElement.scrollTop + document.body.scrollTop + document.documentElement.clientHeight/2 - 50 +"px"
		}).addClass("waiting").html('&nbsp;&nbsp;<img style="margin-top: 10px;" src="<html:imagesPath/>loading-icon.gif">&nbsp;&nbsp;Processing<br>');
		jQuery(window.document.body).addClass("waiting");
	}
	
	function showOverlayPopup(){
		jQuery("#overlayPopup").css({
			display:"block",
			top:0,
			left:0,
			zIndex:1004,
			width : jQuery.ui.dialog.overlay.width(),
			height : jQuery.ui.dialog.overlay.height()
		}).addClass("waiting");
		jQuery("#processingHintPopup").css({
			display:"block",
			width: "300px",
			height: "50px",
			zIndex:1005,
			left: parseInt( jQuery.ui.dialog.overlay.width() )/2-100,
			top: document.documentElement.scrollTop + document.documentElement.clientHeight/2 - 100 +"px"
		}).addClass("waiting");
		jQuery(window.document.body).addClass("waiting");
	}
	
	function hideOverlay(){
		jQuery("#overlay").css({
			display:"none" 
		});
		jQuery("#processingHint").css({
			display:"none"
		}).html('');
	}
	
	function hideOverlayPopup(){
		jQuery("#overlayPopup").css({
			display:"none" 
		});
		jQuery("#processingHintPopup").css({
			display:"none"
		});
	}
	
	function submitPaymentForm(){
		
		var validation_error_flag=false;
        var cardNumber = jQuery('#card_number').val();        
        var cardHolderFirstName = jQuery('#bill_to_forename').val();
        var cardHolderLastName = jQuery('#bill_to_surname').val();
        var month = jQuery('#month').val();
        var year = jQuery('#year').val();
        var securityCode = jQuery('#card_cvn').val();
        jQuery("#errorDiv1").html("");
		
//         alert('cardNumber='+cardNumber);
        if(cardNumber==null||cardNumber==""){       	
        	validation_error_flag = true;
        	jQuery("#errorDiv1").append("<li><strong><fmt:message key='validation.message.mandatory.creditCardNumber' /></strong></li>");
        }
        
        if(jQuery('input[type=radio]:checked').size()>0){
//         	var cardType = jQuery('[name=card_type]:checked').val();
//     		var cardNumberValid = false;
//             if (cardType == "001") {
//                 if ((cardNumber.length == 13 ||  cardNumber.length == 16) && /^4[0-9]*/.test(cardNumber))
//                 	cardNumberValid = true;
//             } else if(cardType == "002") {
//                 if ((cardNumber.length == 16) && /^(5[1-5])[0-9]*/.test(cardNumber))
//                 	cardNumberValid = true;
//             } else if(cardType == "003") {
//                 if ((cardNumber.length == 15) && /^3(4|7)[0-9]*/.test(cardNumber))
//                 	cardNumberValid = true;
//             }
//             if(cardNumberValid==false){
//             	validation_error_flag = true;
//             	jQuery("#errorDiv1").append("<li><strong>Please enter Valid Credit card Number</strong></li>");
//             }
        }else{
        	validation_error_flag = true;
        	jQuery("#errorDiv1").append("<li><strong><fmt:message key='validation.message.mandatory.creditCardType' /></strong></li>");        	
        }
        if(cardHolderFirstName==null||cardHolderFirstName==""||cardHolderLastName==null||cardHolderLastName==""){
        	validation_error_flag = true;
        	jQuery("#errorDiv1").append("<li><strong><fmt:message key='validation.message.mandatory.creditCardHolderName' /></strong></li>");
        }
        if(month==null||month==""||year==null||year==""){
        	validation_error_flag = true;
        	jQuery("#errorDiv1").append("<li><strong><fmt:message key='validation.message.mandatory.creditCardExpirydate' /></strong></li>");
        }else{
       	    var selectedDate = new Date (year,month);
       	    var nextmonth = selectedDate.setMonth(selectedDate.getMonth() + 1);
       	    var today = new Date();
       	    if (today > selectedDate) {
       	    	validation_error_flag = true;
       	    	jQuery("#errorDiv1").append("<li><strong><fmt:message key='validation.message.valid.creditCardExpirydate' /></strong></li>");
       	    }
        }
        if(securityCode==null||securityCode==""){
        	validation_error_flag = true;
        	jQuery("#errorDiv1").append("<li><strong><fmt:message key='validation.message.mandatory.creditCardSecurityCode' /></strong></li>");
        }
        
//         alert('validation_error_flag='+validation_error_flag);
        if(validation_error_flag==true){
        	jQuery("#requestError").show();
        	return false;
        }else{
        	var expiryDate = month+"-"+year;
        	jQuery("#card_expiry_date").val(expiryDate);
        	var isFromIframe =jQuery("#fromIframe").val();
        	if(isFromIframe=='true'){
        		parent.document.getElementById('payment_form').target = 'paymentSubmit';
        		showOverlayPopup();
        		parent.document.getElementById('payment_form').submit();
        	}else{
        		document.getElementById('payment_form').target = 'paymentSubmit';
        		showOverlayPopup();
        		jQuery("#payment_form").submit();
            	
        	}
        	return true;
        }
	}
</script>
		<form id="payment_form" name="payment_form" action="<%=actionUrl%>" method="post">
			<%
			Iterator paramsIterator = params.entrySet().iterator();
			while (paramsIterator.hasNext()) {
			Map.Entry param = (Map.Entry) paramsIterator.next();
			out.print("<input type=\"hidden\" id=\"" + param.getKey() + "\" name=\"" + param.getKey() + "\" value=\"" + param.getValue() + "\"/>\n");
			}
			out.print("<input type=\"hidden\" id=\"signature\" name=\"signature\" value=\"" + sign(params) + "\"/>\n");
			%>
			<div id="fromIframe" value="false"></div>
			<div class="columnInner odd_row borderBlack paddingAll marginAll">
			 <div id="requestError" class="error" style="display:none;">
			 	<div id="errorDiv1"></div>
			 </div>
			 <div id="responseError" class="error" style="display:none;">
			 	<div id="errorDiv2"></div>
			 </div>
			 <div class="paddingAll cBox gray">
              <ul class="form infoBox">
                <li><label><fmt:message key="label.cardInformation.cardType" /></label>
                  <span class="radio odd_row">
                  <input type="radio" id="card_type" name="card_type" value="001" />
                  <label for="visa"><img src="/CreditCardAuthorize/images/visa.gif" width="26" height="26" alt="VISA"></img></label>                  
                  </span>
                  <span class="radio odd_row">
                  <input type="radio" id="card_type" name="card_type" value="002" />
                  <label for="mastercard"><img src="/CreditCardAuthorize/images/master.jpg" width="26" height="26" alt="master"></img></label>                  
                  </span>
                  <span class="radio odd_row">
                  <input type="radio" id="card_type" name="card_type" value="003" />
                  <label for="amex"><img src="/CreditCardAuthorize/images/ax.jpg" width="26" height="26" alt="RBS"></img></label>                  
                  </span>
                  <span class="radio odd_row">
                  <input type="radio" id="card_type" name="card_type" value="004" />
                  <label for="discover"><img src="/CreditCardAuthorize/images/discover.jpg" width="26" height="26" alt="discover"></img></label>                  
                  </span>
                 </li>
                 <li>
                  <label for="creditCard"><fmt:message key="label.cardInformation.cardNumber" /> <span class="req">*</span></label>
                  <span><input type="text" name="card_number" id="card_number" /></span>
                 </li>
                 <li>
                  <label for="bill_to_forename"><fmt:message key="label.cardInformation.cardHolderFirstName" /> <span class="req">*</span></label>
                  <span><input type="text" name="bill_to_forename" id="bill_to_forename" /></span>
                 </li>
                 <li>
                  <label for="bill_to_surname"><fmt:message key="label.cardInformation.cardHolderLastName" /> <span class="req">*</span></label>
                  <span><input type="text" name="bill_to_surname" id="bill_to_surname" /></span>
                 </li>
                 <li>
                  <label><fmt:message key="label.cardInformation.expiryDate" /> <span class="req">*</span></label>
<!--                   <span><input type="text" name="card_expiry_date" id="card_expiry_date" /></span> -->
                  <span>
                  <select id ="month" class="w100">
					<option value=""><fmt:message key="label.cardInformation.month" /></option>
					<option value="01">01</option>
					<option value="02">02</option>
					<option value="03">03</option>
					<option value="04">04</option>
					<option value="05">05</option>
					<option value="06">06</option>
					<option value="07">07</option>
					<option value="08">08</option>
					<option value="09">09</option>
					<option value="10">10</option>
					<option value="11">11</option>
					<option value="12">12</option>				
				</select>
				</span>
                <span>
                <select id="year" class="w100">
					<option value=""><fmt:message key="label.cardInformation.year" /></option>
					<option value="2013">2013</option>
					<option value="2014">2014</option>
					<option value="2015">2015</option>
					<option value="2016">2016</option>
					<option value="2017">2017</option>
					<option value="2018">2018</option>
					<option value="2019">2019</option>
					<option value="2020">2020</option>
					<option value="2021">2021</option>
					<option value="2022">2022</option> 
					<option value="2023">2023</option>
					<option value="2024">2024</option>
					<option value="2025">2025</option>
					<option value="2026">2026</option>
					<option value="2027">2027</option>
					<option value="2028">2028</option>
					<option value="2029">2029</option>
					<option value="2030">2030</option>			
				 </select>
                 </span>
                 </li>
                 <li>
                  <label for="securityCode"><fmt:message key="label.cardInformation.securityCode" /> <span class="req">*</span></label>
                  <span><input type="text" name="card_cvn" id="card_cvn" class="w100"/>
                  <img src="/CreditCardAuthorize/images/help.png" class="helpIconPopup" title="Type security code" width="18" height="18"/></span>
                 </li>
              </ul>
              </div>
				<input type="hidden" name="card_expiry_date" id="card_expiry_date" />
				<input type="hidden" name="bill_to_email" value="XXXX@lexmark.com"/>
				<input type="hidden" name="bill_to_address_line1" value="<%=request.getParameter("address_line1") %>"/>
				<input type="hidden" name="bill_to_address_city" value="<%=request.getParameter("address_city") %>"/>
				<input type="hidden" name="bill_to_address_state" value="<%=request.getParameter("address_state") %>"/>
				<input type="hidden" name="bill_to_address_country" value="<%=request.getParameter("address_country") %>"/>
				<input type="hidden" name="bill_to_address_postal_code" value="<%=request.getParameter("address_postal_code") %>"/>
				<div class="buttonContainer">
					<button class="button_cancel" onclick="javascript:closeDialog();" type="button"><fmt:message key="button.label.cancel" /></button>
	  				<button class="button" type="button" onClick="javascript:submitPaymentForm();"><fmt:message key="button.label.submit" /></button>
				</div>
		</form>
		<div id="overlay" style="display:none">
		</div>
		<div id="processingHint" tabindex="-1" >
		   &nbsp;&nbsp;<img style="margin-top: 10px;" src="<html:imagesPath/>loading-icon.gif">&nbsp;&nbsp;<fmt:message key="label.processing" /><br>
		</div>
		<div id="overlayPopup" style="display:none">
		</div>
		<div id="processingHintPopup" tabindex="-1" style="display:none">
		   &nbsp;&nbsp;<img style="margin-top: 10px;" src="<html:imagesPath/>loading-icon.gif">&nbsp;&nbsp; <fmt:message key="label.processing" /><br>
		</div>
</div>
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
