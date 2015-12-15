<!-- <script src="/CreditCardAuthorize/js/jQuery/jquery.min.js"></script> -->
<!-- <script src="/CreditCardAuthorize/js/jQuery/jquery-ui.min.js"></script> -->
<script src="/CreditCardAuthorize/js/jQuery/jquery.ui.widget.js"></script>
<!-- <script src="/CreditCardAuthorize/js/jQuery/jquery.qtip-1.0.min.js"></script> -->
<script src="/CreditCardAuthorize/js/jQuery/jquery.qtip-1.0.0-rc3.min.js"></script>
<script src="/CreditCardAuthorize/js/mps.js"></script>

<style type="text/css"><%@ include file="../css/mps.css" %></style>

<div id="totalContentCredit">
<div id="processingHintPopup" tabindex="-1" >
   
</div>


<form name="authorisePayment">
<input type="hidden" id="creditId" value="<%=session.getAttribute("creditId")%>"/>

<div class="columnInner odd_row borderBlack paddingAll marginAll">
			 <div class="error" style="display:none;">
			 	<div id="errorDiv1"></div>
			 </div>
             <div class="paddingAll cBox gray">
              <ul class="form infoBox">
                <li><label>Card Type:</label>
                  <span class="radio odd_row">
                  <input type="radio" id="cardType" name="cardType" value="visa" />
                  <label for="visa"><img src="/CreditCardAuthorize/images/visa.gif" width="26" height="26" alt="VISA"></img></label>                  
                  </span>
                  <span class="radio odd_row">
                  <input type="radio" id="cardType" name="cardType" value="mastercard" />
                  <label for="mastercard"><img src="/CreditCardAuthorize/images/master.jpg" width="26" height="26" alt="master"></img></label>                  
                  </span>
                  <span class="radio odd_row">
                  <input type="radio" id="cardType" name="cardType" value="amex" />
                  <label for="amex"><img src="/CreditCardAuthorize/images/ax.jpg" width="26" height="26" alt="RBS"></img></label>                  
                  </span>
                 </li>
                 <li>
                  <label for="creditCard">Card Number: <span class="req">*</span></label>
                  <span><input type="text" name="creditCard" id="creditCard" /></span>
                 </li>
                 <li>
                  <label for="cardHolderName">Card Holder's Name: <span class="req">*</span></label>
                  <span><input type="text" name="cardHolderName" id="cardHolderName" /></span>
                 </li>
                 <li>
                  <label>Expiration Date: <span class="req">*</span></label>
                  <span>
                  <select id ="month" name="month" class="w100">
					<option value="">Month</option>
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
                <select id="year" name="year" class="w100">
					<option value="">Year</option>
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
                  <label for="securityCode">Security Code: <span class="req">*</span></label>
                  <span><input type="text" name="securityCode" id="securityCode" class="w100"/>
                  <img src="/CreditCardAuthorize/images/help.png" class="helpIconPopup" title="Type security code" width="18" height="18"/></span>
                 </li>
              </ul>
              </div>
            <div class="buttonContainer">
  				<button class="button" type="button" onClick="validateCreditCard();">Submit</button>
			</div>
</div>

<div id="processing" tabindex="-1" style="display:none">
   
</div>
</form>
</div>

<script>
	var encryptedVal;
	
	function showToolTipInPopup() {
		showToolTip('helpIconPopup');
	};
	
	function maskCreditCardNumber(creditCardNumber){
        var cardLength = creditCardNumber.length;
        var strToMask = creditCardNumber.substring(0, cardLength - 4);
        var maskedCardNumber = creditCardNumber.replace(strToMask, "************");
        return maskedCardNumber;
    };
	
	function validateCreditCard(){		
		
// 		alert('in validateCreditCard');
		
		var validation_error_flag=false;
        var cardNumber = jQuery('#creditCard').val();        
        var cardHolderName = jQuery('#cardHolderName').val();
        var month = jQuery('#month').val();
        var year = jQuery('#year').val();
        var securityCode = jQuery('#securityCode').val();
        jQuery("#errorDiv1").html("");
		
//         alert('cardNumber='+cardNumber);
        if(cardNumber==null||cardNumber==""){       	
        	validation_error_flag = true;
        	jQuery("#errorDiv1").append("<li><strong>Please enter Credit Card Number</strong></li>");
        }
        
        if(jQuery('input[type=radio]:checked').size()>0){
        	var cardType = jQuery('[name=cardType]:checked').val();
    		var cardNumberValid = false;
            if (cardType == "visa") {
                if ((cardNumber.length == 13 ||  cardNumber.length == 16) && /^4[0-9]*/.test(cardNumber))
                	cardNumberValid = true;
            } else if(cardType == "mastercard") {
                if ((cardNumber.length == 16) && /^(5[1-5])[0-9]*/.test(cardNumber))
                	cardNumberValid = true;
            } else if(cardType == "amex") {
                if ((cardNumber.length == 15) && /^3(4|7)[0-9]*/.test(cardNumber))
                	cardNumberValid = true;
            }
            if(cardNumberValid==false){
            	validation_error_flag = true;
            	jQuery("#errorDiv1").append("<li><strong>Please enter Valid Credit card Number</strong></li>");
            }
        }else{
        	validation_error_flag = true;
        	jQuery("#errorDiv1").append("<li><strong>Please select Credit card Type</strong></li>");        	
        }
        if(cardHolderName==null||cardHolderName==""){
        	validation_error_flag = true;
        	jQuery("#errorDiv1").append("<li><strong>Please enter Card Holder Name</strong></li>");
        }
        if(month==null||month==""||year==null||year==""){
        	validation_error_flag = true;
        	jQuery("#errorDiv1").append("<li><strong>Please select Card Expiry Date</strong></li>");
        }
        if(securityCode==null||securityCode==""){
        	validation_error_flag = true;
        	jQuery("#errorDiv1").append("<li><strong>Please enter Card Security Code</strong></li>");
        }
        
//         alert('validation_error_flag='+validation_error_flag);
        if(validation_error_flag==true){
        	jQuery(".error").show();
        	return false;
        }else{
        	authorize();
        	return true;
        }
        
    }
	function authorize(){
// 		alert('authorize');
		var creditCardNo=jQuery('#creditCard').val();
		jQuery('#'+creditId).html("");
		jQuery('#'+creditId).val("");
		jQuery('#processing').show();
		jQuery.ajax({
			url:'/CreditCardAuthorize/CreditCardPayment',
			data:{creditCard:creditCardNo},
			type:'POST',
			dataType: 'JSON',		
			success: function(results){
				jQuery('#processing').hide();
				var obj2;
				try{
					 //var stringified = JSON.stringify(results);
					 obj2=jQuery.parseJSON(results);
				}catch(e){
					
					}
				if(obj2 !=null){
					if(obj2.creditsuccess=="Success"){
					
						if(obj2.encryptedNumber != null)
						{	
							encryptedVal=obj2.encryptedNumber;				
						}
						else {
							encryptedVal = "";
						}	
						
					
					var creditId = jQuery('#creditId').val();
					jQuery('#'+creditId).val(encryptedVal);
					var maskedVal = maskCreditCardNumber(encryptedVal);
					jQuery('#'+creditId).html(maskedVal);
					jQuery('#'+creditId).show();
					/* jQuery('#creditCard').val("");        
			        jQuery('#cardHolderName').val("");
			        jQuery('#month').val("");
			        jQuery('#year').val("");
			        jQuery('#securityCode').val("");
			        jQuery('#cardType').prop('checked', false); */
					hideOverlay();
					dialog.dialog('destroy');				 
					dialog=null;
					jQuery('#totalContentCredit').remove();
					}			
					else {	
						//alert('in credit card error');
						jQuery("#errorDiv1").html("Invalid Credit card Number");	
						jQuery(".error").show();						
					}
				}
				else
				{
					//jQuery("#errorDiv").show();
					jQuery("#errorDiv1").html("Invalid Credit card Number");
					jQuery(".error").show();
				}
			}
		});
		
	}
</script>