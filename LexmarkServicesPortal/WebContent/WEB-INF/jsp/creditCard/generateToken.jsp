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
<%@ page import="java.net.URL" %>
<%@ page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>
<jsp:include page="/WEB-INF/jsp/include.jsp"/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="com.lexmark.services.util.LexmarkSPOmnitureConstants" %>
<%@ taglib uri="/WEB-INF/tld/liferay-portlet.tld" prefix="portlet"%>
<%@ taglib prefix="portlet2" uri="http://java.sun.com/portlet" %> 
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="html" tagdir="/WEB-INF/tags/html" %>
<% pageContext.setAttribute("newLineChar", "\n"); %>
<script type="text/javascript"><%@ include file="../../../js/jQueryAlert/jquery.alerts.js"%></script>
<script type="text/javascript"><%@ include file="../../../js/validation.js"%></script>
<script type="text/javascript" src="<html:rootPath/>js/jQuery/jquery.qtip-1.0.0-rc3.min.js"></script>
<script type="text/javascript" src="<html:rootPath/>js/mps.js"></script>
<script type="text/javascript" src="<html:rootPath/>js/addressPopup.js"></script>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/mps.css"/>
<!--[if IE 7]>
	<style type="text/css"><%@ include file="/WEB-INF/css/mps_ie7.css" %></style>
<![endif]-->
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/in-styles.css?version=<html:fileCacheVersion/>" />
<div id="totalContentCredit">
<%
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
if((request.getParameter("shipToCountryName")!=null && request.getParameter("shipToCountryName").equalsIgnoreCase("USA")) || (request.getParameter("shipToCountryCode")!=null && request.getParameter("shipToCountryCode").equalsIgnoreCase("US"))){
	session.setAttribute("shipToCountry","US");
	SECRET_KEY = prop.getProperty("US_SAP_SECRET_KEY");
	access_key = prop.getProperty("US_SAP_ACCESS_KEY");
	profile_id = prop.getProperty("US_SAP_PROFILEID");
}
if((request.getParameter("shipToCountryName")!=null && request.getParameter("shipToCountryName").equalsIgnoreCase("Canada")) || (request.getParameter("shipToCountryCode")!=null && request.getParameter("shipToCountryCode").equalsIgnoreCase("CA"))){
	session.setAttribute("shipToCountry", "CA");
	SECRET_KEY = prop.getProperty("CA_SAP_SECRET_KEY");
	access_key = prop.getProperty("CA_SAP_ACCESS_KEY");
	profile_id = prop.getProperty("CA_SAP_PROFILEID");
}

// String custom_receipt_page = "http://localhost:8080/group/global-services/creditCard";
// String actionUrl = "https://testsecureacceptance.cybersource.com/silent/token/create";
HashMap params = new HashMap();
// params.put("access_key","4a6ce1f2212e3f91b5fa15ce12512bea");
// params.put("profile_id","lexmark");

params.put("access_key",access_key);
params.put("profile_id",profile_id);
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
<iframe id="paymentSubmit" name="paymentSubmit" class="iframe-upload-target"></iframe>


<script type="text/javascript">
var addressValues={};

addressValues["addressLine1"]="<%=request.getParameter("address_line1") %>";
addressValues["officeNumber"]="<%=request.getParameter("address_office") %>";
addressValues["addressLine2"]="<%=request.getParameter("address_line2") %>";
addressValues["city"]="<%=request.getParameter("address_city") %>";
addressValues["county"]="<%=request.getParameter("county") %>";
addressValues["state"]="<%=request.getParameter("address_state") %>";
addressValues["province"]="<%=request.getParameter("province") %>";
addressValues["district"]="<%=request.getParameter("district") %>";
addressValues["postalCode"]="<%=request.getParameter("address_postal_code") %>";
addressValues["country"]="<%=request.getParameter("dispCountry") %>";
jQuery(document).ready(function(){
	
	var html="<div><%=request.getParameter("address_name") %></div>"+generateAddressDisplayHTML(addressValues);
	jQuery('#adddressLiHTML').html(html);
	
});
	function closePopup(){
		/* hideOverlay();	
		dialog.dialog('destroy');
		dialog=null;
		jQuery('#totalContentCredit').remove(); */
		jQuery('.ui-dialog-titlebar a').click();
	}
	
	function displayBillTo(){
		jQuery('#billToParent').show();
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
        	jQuery("#errorDiv1").append("<li><strong>Please enter Credit Card Number</strong></li>");
        	jQuery("#card_number").addClass("errorColor");
        	
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
        	jQuery("#errorDiv1").append("<li><strong>Please select Credit card Type</strong></li>");
        	jQuery("#selectCCNumber").addClass("errorColor");        	
        }
        if(cardHolderFirstName==null||cardHolderFirstName==""||cardHolderLastName==null||cardHolderLastName==""){
        	validation_error_flag = true;
        	jQuery("#errorDiv1").append("<li><strong>Please enter Card Holder Name</strong></li>");
        	jQuery("#bill_to_forename").addClass("errorColor");
        	jQuery("#bill_to_surname").addClass("errorColor");
        }
        if(month==null||month==""||year==null||year==""){
        	validation_error_flag = true;
        	jQuery("#errorDiv1").append("<li><strong>Please select Card Expiry Date</strong></li>");
        	jQuery("#month").addClass("errorColor");
        	jQuery("#year").addClass("errorColor");
        }else{
       	    var selectedDate = new Date (year,month);
       	    var nextmonth = selectedDate.setMonth(selectedDate.getMonth() + 1);
       	    var today = new Date();
       	    if (today > selectedDate) {
       	    	validation_error_flag = true;
       	    	jQuery("#errorDiv1").append("<li><strong>Please enter valid Card Expiry Date</strong></li>");
       	    	jQuery("#month").addClass("errorColor");
            	jQuery("#year").addClass("errorColor");
       	    }
        }
        if(securityCode==null||securityCode==""){
        	validation_error_flag = true;
        	jQuery("#errorDiv1").append("<li><strong>Please enter Card Security Code</strong></li>");
        	jQuery("#card_cvn").addClass("errorColor");
        }
        
//         alert('validation_error_flag='+validation_error_flag);
        if(validation_error_flag==true){
        	jQuery("#requestError").show();
        	return false;
        }else{
        	var expiryDate = month+"-"+year;
        	jQuery("#card_expiry_date").val(expiryDate);
        	var isFromIframe =jQuery("#fromIframe").val();
        	var addressLine1 = jQuery("#pickupAddressLine1").val();
        	var state;
        	var country;
        	if(jQuery('#pickupAddressregion').val()!=null && jQuery('#pickupAddressregion').val()!=''){
        		state = jQuery('#pickupAddressregion').val();
        	}else if(jQuery('#pickupAddressState').val()!=null && jQuery('#pickupAddressState').val()!=''){
        		state = jQuery('#pickupAddressState').val();
        	}
        	else if(jQuery('#pickupAddressProvince').val()!=null && jQuery('#pickupAddressProvince').val()!=''){
        		state = jQuery('#pickupAddressProvince').val();
        	}
        	
        	if(jQuery('#pickupAddresscountryISOCode').val()!=null && jQuery('#pickupAddresscountryISOCode').val()!=''){
        		country = jQuery('#pickupAddresscountryISOCode').val();
        	}

        	

        
        	jQuery("#bill_to_address_line1").val(addressLine1);
        	jQuery("#bill_to_address_city").val(jQuery("#pickupAddressCity").val());
        	jQuery("#bill_to_address_state").val(state);
        	jQuery("#bill_to_address_country").val(country);
        	jQuery("#bill_to_address_postal_code").val(jQuery("#pickupAddressPostCode").val());
		
        	
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
		
       <div class="portletBlock infoBox rounded shadow">  
       <div class="columnInner">
	   <%if(request.getParameter("address_name")!=null && !"".equalsIgnoreCase(request.getParameter("address_line1"))){%>

       <h4><a href="javascript:void(0)" class="lightboxLarge" title="<spring:message code="requestInfo.link.selectADifferentAddress"/>" id="diffAddressLink" onclick="popupAddress(id);"><spring:message code="requestInfo.link.selectADifferentAddress"/></a><spring:message code="requestInfo.heading.billToAddress"/></h4>

	<%}else{%>

       <h4><a href="javascript:void(0)" class="lightboxLarge" title="<spring:message code="requestInfo.link.selectAnAddress"/>" id="diffAddressLink" onclick="popupAddress(id);"><spring:message code="requestInfo.link.selectAnAddress"/></a><spring:message code="requestInfo.heading.billToAddress"/></h4>
<%}%>
       
         <div class="paddingAll cBox gray">
           <ul class="roDisplay">           	
			<li id="adddressLiHTML"></li>
		</ul>
		</div>
		</div>
		</div>
		<div class="portletBlock infoBox rounded shadow">  
       <div class="columnInner">
		<form id="payment_form" name="payment_form" action="<%=actionUrl%>" method="post">
			<%
			Iterator paramsIterator = params.entrySet().iterator();
			while (paramsIterator.hasNext()) {
			Map.Entry param = (Map.Entry) paramsIterator.next();
			out.print("<input type=\"hidden\" id=\"" + param.getKey() + "\" name=\"" + param.getKey() + "\" value=\"" + param.getValue() + "\"/>\n");
			}
			out.print("<input type=\"hidden\" id=\"signature\" name=\"signature\" value=\"" + sign(buildDataToSign(params), SECRET_KEY) + "\"/>\n");
			%>
			<div id="fromIframe" value="false"></div>
			
			 <div id="requestError" class="error" style="display:none;">
			 	<div id="errorDiv1"></div>
			 </div>
			 <div id="responseError" class="error" style="display:none;">
			 	<div id="errorDiv2"></div>
			 </div>
			 <div class="paddingAll cBox gray">
              <ul class="form infoBox">
                <li ><label><spring:message code="requestInfo.label.cardType"/></label>
                  <span id="selectCCNumber"><span class="radio odd_row">
                  <input type="radio" id="card_type" name="card_type" value="001" />
                  <label for="visa"><img src="<html:imagesPath/>visa.gif" width="28" height="24" alt="VISA"></img></label>                  
                  </span>
                  <span class="radio odd_row">
                  <input type="radio" id="card_type" name="card_type" value="002" />
                  <label for="mastercard"><img src="<html:imagesPath/>master.jpg" width="28" height="24" alt="master"></img></label>                  
                  </span>
                  <span class="radio odd_row">
                  <input type="radio" id="card_type" name="card_type" value="003" />
                  <label for="amex"><img src="<html:imagesPath/>ax.jpg" width="26" height="26" alt="RBS"></img></label>                  
                  </span>
                  <span class="radio odd_row">
                  <input type="radio" id="card_type" name="card_type" value="004" />
                  <label for="discover"><img src="<html:imagesPath/>discover.jpg" width="28" height="24" alt="discover"></img></label>                  
                  </span></span>
                 </li>
                 <li>
                  <label for="creditCard"><spring:message code="requestInfo.label.cardNo"/><span class="req">*</span></label>
                  <span><input type="text" name="card_number" id="card_number" /></span>
                 </li>
                 <li>
                  <label for="bill_to_forename"><spring:message code="requestInfo.contactInfo.label.firstName"/><span class="req">*</span></label>
                  <span><input type="text" name="bill_to_forename" id="bill_to_forename" /></span>
                 </li>
                 <li>
                  <label for="bill_to_surname"><spring:message code="requestInfo.contactInfo.label.lastName"/><span class="req">*</span></label>
                  <span><input type="text" name="bill_to_surname" id="bill_to_surname" /></span>
                 </li>
                 <li>
                  <label><spring:message code="requestInfo.label.expiryDate"/><span class="req">*</span></label>
<!--                   <span><input type="text" name="card_expiry_date" id="card_expiry_date" /></span> -->
                  <span>
                  <select id ="month" class="w100">
					<option value=""><spring:message code="requestInfo.label.month"/></option>
					<option value="01"><spring:message code="requestInfo.label.month01"/></option>
					<option value="02"><spring:message code="requestInfo.label.month02"/></option>
					<option value="03"><spring:message code="requestInfo.label.month03"/></option>
					<option value="04"><spring:message code="requestInfo.label.month04"/></option>
					<option value="05"><spring:message code="requestInfo.label.month05"/></option>
					<option value="06"><spring:message code="requestInfo.label.month06"/></option>
					<option value="07"><spring:message code="requestInfo.label.month07"/></option>
					<option value="08"><spring:message code="requestInfo.label.month08"/></option>
					<option value="09"><spring:message code="requestInfo.label.month09"/></option>
					<option value="10"><spring:message code="requestInfo.label.month10"/></option>
					<option value="11"><spring:message code="requestInfo.label.month11"/></option>
					<option value="12"><spring:message code="requestInfo.label.month12"/></option>				
				</select>
				</span>
                <span>
                <select id="year" class="w100">
					<option value=""><spring:message code="requestInfo.label.year"/></option>
					<option value="2013"><spring:message code="requestInfo.label.year13"/></option>
					<option value="2014"><spring:message code="requestInfo.label.year14"/></option>
					<option value="2015"><spring:message code="requestInfo.label.year15"/></option>
					<option value="2016"><spring:message code="requestInfo.label.year16"/></option>
					<option value="2017"><spring:message code="requestInfo.label.year17"/></option>
					<option value="2018"><spring:message code="requestInfo.label.year18"/></option>
					<option value="2019"><spring:message code="requestInfo.label.year19"/></option>
					<option value="2020"><spring:message code="requestInfo.label.year20"/></option>
					<option value="2021"><spring:message code="requestInfo.label.year21"/></option>
					<option value="2022"><spring:message code="requestInfo.label.year22"/></option> 
					<option value="2023"><spring:message code="requestInfo.label.year23"/></option>
					<option value="2024"><spring:message code="requestInfo.label.year24"/></option>
					<option value="2025"><spring:message code="requestInfo.label.year25"/></option>
					<option value="2026"><spring:message code="requestInfo.label.year26"/></option>
					<option value="2027"><spring:message code="requestInfo.label.year27"/></option>
					<option value="2028"><spring:message code="requestInfo.label.year28"/></option>
					<option value="2029"><spring:message code="requestInfo.label.year29"/></option>
					<option value="2030"><spring:message code="requestInfo.label.year30"/></option>			
				 </select>
                 </span>
                 </li>
                 <li>
                  <label for="securityCode"><spring:message code="requestInfo.label.securityCode"/><span class="req">*</span></label>
                  <span><input type="text" name="card_cvn" id="card_cvn" class="w100"/>
                  <img class="helpIcon ui_icon_sprite info-icon"  src="<html:imagesPath/>transparent.png" title="Type security code" /></span>
                 </li>
              </ul>
              </div>
				<input type="hidden" name="card_expiry_date" id="card_expiry_date" />
				<input type="hidden" name="bill_to_email" value="XXXX@lexmark.com"/>
				<input type="hidden" id="bill_to_address_line1" name="bill_to_address_line1" value="<%=request.getParameter("address_line1") %>"/>
				<input type="hidden" id="bill_to_address_city" name="bill_to_address_city" value="<%=request.getParameter("address_city") %>"/>
				<input type="hidden" id="bill_to_address_state" name="bill_to_address_state" value="<%=request.getParameter("address_state") %>"/>
				<input type="hidden" id="bill_to_address_country" name="bill_to_address_country" value="<%=request.getParameter("address_country") %>"/>
				<input type="hidden" id="bill_to_address_postal_code" name="bill_to_address_postal_code" value="<%=request.getParameter("address_postal_code") %>"/>
				
		</form>
		<div id="overlay" style="display:none">
		</div>
		<div id="processingHint" tabindex="-1" >
		   &nbsp;&nbsp;<img class="margin-top-10px" src="<html:imagesPath/>loading-icon.gif">&nbsp;&nbsp;<spring:message code="processing"/><br>
		</div>
		<div id="overlayPopup" style="display:none">
		</div>
		<div id="processingHintPopup" tabindex="-1" style="display:none">
		   &nbsp;&nbsp;<img class="margin-top-10px" src="<html:imagesPath/>loading-icon.gif">&nbsp;&nbsp; <spring:message code="processing"/><br>
		</div>
</div>
</div>
		<div class="buttonContainer">
			<button class="button_cancel" onclick="javascript:closeDialog();" type="button"><spring:message code="button.cancel"/></button>
 			<button class="button" type="button" onClick="javascript:submitPaymentForm();"><spring:message code="button.submit"/></button>
		</div>
</div>
<%!
	private String getUTCDateTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
		return sdf.format(new Date());
   	}

	
	private static final String HMAC_SHA256 = "HmacSHA256";
// 	private static final String SECRET_KEY = "2a639a00e987486292b0eca40cbb814f97e4eb3f4f844d8b8b13b6b34c1a5743f0fd9fda9a6844de86127157608918f6c73aac8bfe864fe18dc8215d4e097c7179bf9f3419ae41e38fb30bbb32ca2de729e2cdfb8dc54a94afe410737f0f3529272d075eb93e4d68a49d48e38914bf2eeda7692c259a4a2f93e40443ef611b84";
	
	
	/* private String sign(HashMap params) throws InvalidKeyException, NoSuchAlgorithmException {
		return sign(buildDataToSign(params), SECRET_KEY);
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
