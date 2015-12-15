<%-- ADD NEW ADDRESS PAGE --%>


<%@ include file="/WEB-INF/jsp/include.jsp"%>
<%--@ include file="/WEB-INF/jsp/includeDatePicker.jsp"--%>
<script type="text/javascript" src="<html:rootPath/>js/addressPopup.js"></script> 
<jsp:include page="../../common/validationMPS.jsp"></jsp:include>
<%@ page import = "com.lexmark.services.util.LexmarkSPOmnitureConstants" %>
<%@page import ="static com.lexmark.services.util.ChangeMgmtConstant.ADDRESSCLEANSEFIELDS_INPUT"%>
<jsp:include page="/WEB-INF/jsp/includeGrid.jsp"/>
<script type="text/javascript"><%@ include file="../../../../js/portletRedirection.js"%></script>

<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/mps.css"/>
<!--[if IE 7]>
	<style type="text/css"><%@ include file="/WEB-INF/css/mps_ie7.css" %></style>
<![endif]-->
<style>.ie7 #wrapper{min-width:800px!important;}
#inputValuesArea{width:68%!important;}</style>

<portlet:resourceURL var="newAddressValidateURL" id="newAddressValidate"></portlet:resourceURL>
<portlet:renderURL var="addNewAddressConfirmationUrl">
	<portlet:param name="action" value="addressConfirm"></portlet:param>
</portlet:renderURL>
<portlet:renderURL var="countryListPopulateURL" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>" >
	<portlet:param name="action" value="countryDropDownPopulate"></portlet:param>
</portlet:renderURL>
<portlet:renderURL var="stateListPopulateURL" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>" >
	<portlet:param name="action" value="stateDropDownPopulate"></portlet:param>
</portlet:renderURL>
<style>
div.portletBlock{clear:both;}
</style>
 	
 	<% request.setAttribute("subTabSelected","createNewRequest"); %>
	<%@ include file="/WEB-INF/jsp/common/subTab.jsp" %>
   <form:form id="newAddressForm" action="" commandName="addressForm" method="post">
   
<%-- Below section is for the prev sr no and update flag binding--%>
<form:hidden path="prevSrNo" id="prevSrNo"/>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/in-styles.css?version=<html:fileCacheVersion/>" />
   
  <div id="content-wrapper">
    <div class="journal-content-article">
      <h1> <spring:message code="requestInfo.cm.heading.changeRequest"></spring:message></h1> 
      <h2 class="step"><spring:message code="requestInfo.cm.manageAddress.heading.addresses"/></h2>
    </div>
    
    <!-- Added for CI7 BRD14-02-12 -->
    <div><h3><strong>
      <%@ include file="/WEB-INF/jsp/common/commonAccountAgreementNameInfo.jsp"%>
      <c:if test='${sessionScope.accountCurrentDetails["rowCount"]>"1"}'>
        		<a href="#" onclick="javascript:showChangeAccountPopup();" class="edit" id="changeaccount"><spring:message code="requestInfo.link.changeAccount"/></a>
        	</c:if>
        	</strong></h3></div>
        	<!-- END -->
        	
    <div class="error" id="errorMsg" style="display:none"></div>
   
    <div class="main-wrap">
      <div class="content">

	     
          <!-- MAIN CONTENT BEGIN -->
          <h3 class="pageTitle">
          	<spring:message code="requestInfo.cm.manageAddress.heading.addAddresses"/> 
          	<%-- <span><spring:message code="requestInfo.label.fieldsMarked"/>
          	<span class="req">*</span>
          	<spring:message code="requestInfo.label.areRequired"/>--%>
          	</span>
          </h3>
           <!-- This block is to show server side errors  -->
           <!-- Added for CI 14-02-03-->
           <spring:hasBindErrors name="validation.fileUpload.attachmentDuplicate.errorMsg">
           </spring:hasBindErrors>
           <spring:hasBindErrors name="addressForm">
          	<div class="error">
				<c:forEach var="error" items="${errors.allErrors}">
			   		<li><strong><spring:message code="${error.code}"/></strong></li>
            	</c:forEach>
     		</div>	
	      </spring:hasBindErrors>
			<!-- Added for CI 14-02-03   START    -->
	        <div id="jsValidationErrors" class="error" style="display: none;"></div>	
	        <div id="validationErrors" class="error" style="display: none;"></div>
	        <%-- This div has been renamed to errorMsgPopup_CatalogDtl because contact popup validations div id was conflicting --%>
     		<div class="error" id="errorMsgPopup_CatalogDtl" style="display:none"></div>
			<!-- Added for CI 14-02-03    ENDS 		 -->
           <jsp:include page="/WEB-INF/jsp/common/commonContactInfo.jsp"/>        
           <hr class="separator" />
		  <p class="info">
			<spring:message code="requestInfo.cm.manageAddress.heading.addAddressesInfo"/>
			 <span class="span-style1" ><spring:message code="requestInfo.label.fieldsMarked"/> <span class="req">*</span><spring:message code="requestInfo.label.areRequired"/></span>
		  <span class="span-style1"><spring:message code="requestInfo.label.fieldsMarked"/> <span class="req">**</span><spring:message code="requestInfo.label.provideAtleastOne"/></span>
			</p>
	

		   <div class="portletBlock infoBox rounded shadow">
		   <div class="columnsOne">
            <div class="columnInner">
			<h4><spring:message code="requestInfo.cm.manageAddress.heading.newAddresses"/></h4>
				
                    <ul class="form" id="inputValuesArea">
					  
					  <li>
                        <label for="addName"><spring:message code="requestInfo.addressInfo.label.addressName"/><%-- <span class="req">*</span>--%></label>
	                    <span><input type="text" id="addName"   maxlength="100"/></span>
						<img class="helpIcon ui_icon_sprite info-icon"  src="<html:imagesPath/>transparent.png" title="<spring:message code="requestInfo.tooltip.addressName"/>" >
                      </li>
					  <li>
                        <label for="storeName"><spring:message code="requestInfo.addressInfo.label.storeFrontName"/> </label>
                        <span><input type="text" id="storeName"  maxlength="50"/></span>
						<img class="helpIcon ui_icon_sprite info-icon"  src="<html:imagesPath/>transparent.png" title="<spring:message code="requestInfo.tooltip.storeFrontName"/>" >
                      </li>
                      
                      <li>
                        <label for="addrLine1"><spring:message code="requestInfo.addressInfo.label.addressLine1"/><span class="req">*</span></label>
                        <span><input type="text" id="addrLine1"  maxlength="70"/></span>
                           
                     </li>
					  <li>
                        <label for="addrLine2"><spring:message code="requestInfo.addressInfo.label.addressLine2"/></label>
                        <span><input type="text"  id="addrLine2"  maxlength="35"/></span>
                     
                      </li>
					 
					  <li>
                        <label for="city"><spring:message code="requestInfo.addressInfo.label.city" /><span class="req">*</span> </label>
                        <span><input  id="city" type="text" maxlength="50"/></span>
                      </li>
					  <li>
                        <label for="country"><spring:message code="requestInfo.addressInfo.label.country"/>
                        <span class="req">*</span> </label>
                        <span><select  id="country" onchange="loadStateList();"></select></span>
	                  </li>
					  <li>
					  <%--Either of Region or Zip Code Reqd..** added under CI Defect #9814 --%>
                        <label for="state"><spring:message code="requestInfo.addressInfo.label.region"/><span class="req">**</span> </label>
                        <span><select  id="state" disabled="disabled" ></select></span>
                        <img src="<html:imagesPath/>loading-icon.gif" id="loadingImage" style="display: none;"/>
                         <div id="stateorzipmesg" class="note" ><span class="margin-top--8px">(<spring:message code="requestInfo.addressInfo.label.regionDetails"/>)</span></div>
                        
                      </li>
                      <%-- Changes for MPS 2.1 House Number Added--%>
					  <li id="liofficeId" style="display: none;">
                      	<label for="officeNo"><spring:message code="requestInfo.addressInfo.label.officeNumber"/>  </label>
                       	 <span><input type="text" type="text" id="officeNo" maxlength="50" onfocus="jQuery(this).removeClass('errorColor');" onmousedown="jQuery(this).removeClass('errorColor');"/></span>
                      </li>
					<%-- Ends Changes for MPS 2.1 House Number Added--%>
					<%--jQuery('#cleansedAddress').hide(function(){jQuery('#button_popup').show();});
					<input type="text" name="genericAddress.officeNumber"/> 
					--%>
					  
					  <li>
                        <label for="zipCode"><spring:message code="requestInfo.addressInfo.label.postalCode"/><span class="req">**</span> </label>
                        <span><input type="text"  id="zipCode" class="w100" maxlength="30"/></span>
                        </li>
					</ul>
			
				</div>
			</div>
		  </div> 
		  
<%--ADDED FOR ADDRESS CLEANSING  --%>
		  <div class="buttonContainer" id="ignoreAndSaveBTN">
						<div id="ignoreSaveAddress" style="display: none;" align="left">
							<input type="checkbox" id="ignoreSaveAddrChk" onclick="ignoreAndSave();"/>
							<label><spring:message code="addressCleansing.error.ignore.warning">
							</spring:message></label>
						</div>
							
							
						</div>
				
					<div id="cleansedAddress" style="display:none;">
					<hr class="separator" />
						<p class="info banner"><spring:message code="requestInfo.popup.cleansedAddresses"/></p>
						<div class="portletBlock infoBox">
						<div class="columnsOne">
						<div class="columnInner">
						<ul class="roDisplay">
						
						
									<li id="cleanseAddressOutput"></li>
									

								</ul>
								<p class="check">
								<input type="checkbox" id="checkCleanse"	onclick="javascript:copyCleansedValuesToInput();" /> 
								<label for="modAddress">
								<%-- <spring:message code="requestInfo.popup.cleansedAddresses"/> --%>
								<spring:message code="requestInfo.popup.usecleansedAddresses"/>
								</label>
								</p>
								
							</div>
						</div>
					</div>
						
					</div>
					<div style="display: none;">
						 						  
												  <input type="text" name="genericAddress.addressName" value="${addressForm.genericAddress.addressName }"/>
												  <input type="text" name="genericAddress.addressLine1" value="${addressForm.genericAddress.addressLine1 }"/>
												  <input type="text" name="genericAddress.addressLine2" value="${addressForm.genericAddress.addressLine2 }"/>
												  <input type="text" name="genericAddress.city" value="${addressForm.genericAddress.city }"/>
												  <input type="text" id="stateFormVal" name="genericAddress.state" value="${addressForm.genericAddress.state }"/>
												  <input type="text" name="genericAddress.province" value="${addressForm.genericAddress.province }"/>
												  <input type="text" name="genericAddress.postalCode" value="${addressForm.genericAddress.postalCode }"/>
												  <input type="text" id="countryFormVal" name="genericAddress.country" value="${addressForm.genericAddress.country }"/>
												  <input type="text" name="genericAddress.storeFrontName" value="${addressForm.genericAddress.storeFrontName }"/>
												  <input type="text" name="genericAddress.county" value="${addressForm.genericAddress.county }"/>
												  <input type="text" name="genericAddress.officeNumber" value="${addressForm.genericAddress.officeNumber }"/>
												  <input type="text" name="genericAddress.stateFullName" value="${addressForm.genericAddress.stateFullName }"/>
												  <input type="text" name="genericAddress.district" value="${addressForm.genericAddress.district }"/>
												  <input type="text" name="genericAddress.region" value="${addressForm.genericAddress.region }"/>
												  <input type="text" name="genericAddress.stateCode" value="${addressForm.genericAddress.stateCode }"/>
												  <input type="text" name="genericAddress.latitude" value="${addressForm.genericAddress.latitude }"/>
												  <input type="text" name="genericAddress.longitude" value="${addressForm.genericAddress.longitude }"/>
												  <input type="text" name="genericAddress.savedErrorMessage" value="${addressForm.genericAddress.savedErrorMessage }"/>
												  <input type="text" id="isoCode" name="genericAddress.countryISOCode" value="${addressForm.genericAddress.countryISOCode }"/>
												  <input type="text" name="genericAddress.isAddressCleansed" value="${genericAddress.isAddressCleansed}"/>
					</div>
					   <form:hidden path="requestName" id="typeOfReq"/>
    
   
<input type="hidden" name="attachmentDescriptionID" id="attachmentDescriptionID" />  <!--//Added for CI 14-02-03 -->
</form:form>
		<%--ENds ADDED FOR ADDRESS CLEANSING  --%>
		<!-- Added for CI 14-02-03  STARTS -->
	<jsp:include page="/WEB-INF/jsp/common/commonAttachment.jsp" />
	<input type="hidden" id="fileCount" />
	<!--Added for CI 14-02-03  ENDS-->
		
		  <div class="buttonContainer">
		  	<button class="button_cancel" type="button" onclick="redirectToList();"><spring:message code="button.back"/></button>
            <button class="button_cancel" type="button" onclick="redirectToHistory('others');"><spring:message code="button.cancel"/></button>
            <button class="button" type="button" onclick="cleanseAndSubmit();"><spring:message code="button.continue"/></button><%--addNewAddressConfirmation --%>
          </div>
		

        <!-- MAIN CONTENT END --> 
        
      </div>
    </div>
      
  </div>
 





  
<script type="text/javascript">

var portlet_name="<%=LexmarkSPOmnitureConstants.ADDANEWADDRESS%>";
var action_primary="<%=LexmarkSPOmnitureConstants. ADDADDRESSSELECTADIFFERENTCONTACT%>";
var action_secondary="<%=LexmarkSPOmnitureConstants.ADDADDRESSADDADDITIONALCONTACT%>";
var pageType = "addAddress"; //Added for page type for CI 14-02-03
var errorList = "";
var callCleansing=true; 
var cleaseAddressFields={
		inputIds:["addName","storeName","addrLine1","addrLine2","officeNo","city","country","state","zipCode"],
		keys:["addressName","storeFrontName","addressLine1","addressLine2","officeNumber","city","country","state","postalCode","province","county","district","countryISOCode","region","stateCode","stateFullName","latitude","longitude","savedErrorMessage","isAddressCleansed"]
};

jQuery(document).ready(function(){
	var currentURL = window.location.href;
	jQuery('#attachmentDescription').val('${addressForm.attachmentDescription}');
	<%-- jQuery("#prevSrNo").val('<%= request.getParameter("prevSrNo")%>'); --%>
	jQuery('#inputValuesArea input,select').click(function(){
			callCleansing=true;
			jQuery('#cleansedAddress').hide('slow');
			jQuery('#checkCleanse').attr('checked',false);
			/*copyCleansedValuesToInput();*/
			 
		});
	for(i=0;i<cleaseAddressFields.inputIds.length;i++){
		var inputHTMLId="genericAddress."+cleaseAddressFields.keys[i];
		jQuery('#'+cleaseAddressFields.inputIds[i]).val(jQuery("[name='"+inputHTMLId+"']").val());
	}
	
	//initialize the country dropdownlist
	loadCountryList();	
	jQuery(':input').bind('mousedown focus',function(){
		jQuery(this).removeClass('errorColor');
		if(jQuery(this).attr('id')=='zipCode'){
			jQuery('#state').removeClass('errorColor');
		}
		});
//		Change Account Link Hide/Show for CI-7 Defect #12274
	if(currentURL.indexOf('/partner-portal') == -1)
	  {	
		  jQuery('#changeaccount').show();
	  }
	else
		{
		jQuery('#changeaccount').hide();
		}
	
	
	
});
function redirectToList(){
	jConfirm("<spring:message code="common.back.alert"/>", "", function(confirmResult) {
		if(confirmResult){
			showOverlay();
			window.location.href="<portlet:renderURL/>";
		}else{
			return false;
			}
	});
}

function cleanseAndSubmit(){
	
	jQuery("#prevSrNo").val('${addressForm.prevSrNo}');	
	//alert("prev sr no "+jQuery('#prevSrNo').val());
	
	jQuery("#typeOfReq").val("add");
	/*jQuery('#countryFormVal').val(jQuery('#country option:selected').text());
	jQuery('#stateFormVal').val(jQuery('#state option:selected').text());*/
	//Clear the contents of error div
	jQuery('#errorMsg').html('');
	
	//alert('in validation');
	

	var validationFlag=validateAddress();
	
	if(validationFlag==false)
	{
		jQuery('#errorMsg').show();
		jQuery(document).scrollTop(0);
	}else{
		if(callCleansing){
			<%--Chagnes for address Cleansing--%>
			goForAddressCleansing('errorMsg');
			<%--ENDS Chagnes for address Cleansing--%>
		}else{
			copyCleansedValuesToInput();
			//Added for CI 14-02-03
	//alert(jQuery('#attachmentDescription').val());
	jQuery('#attachmentDescriptionID').val(jQuery('#attachmentDescription').val());
	//Added for CI 14-02-03
	//alert('in validation');
			var addAddressConfirmUrl="${addNewAddressConfirmationUrl}&addAddress=true&timeZoneOffset=" + timezoneOffset;
			jQuery("#newAddressForm").attr("action", addAddressConfirmUrl);		
			jQuery('#newAddressForm').submit();
		}
		
	}
		
}




//Ajax call to load the country list
function loadCountryList(){

	
	jQuery.ajax({
		url:'${countryListPopulateURL}',
		type:'POST',
		dataType: 'html',
		success: function(countryListResult){
					
					jQuery("#country").append(countryListResult);	
					
					
					//Below code is for Back button to populate country n state list
					if(jQuery('#isoCode').val()!="")
					{
						jQuery('#country').val(jQuery('#isoCode').val());
						loadStateList();
					}			
			},
	  failure: function(results){}
	});
}
//Ajax call to load the State List
function loadStateList(){
	if(jQuery('#country').val()=="BR"){
		jQuery('#liofficeId').show();
	}
	else{
		jQuery('#liofficeId').hide();
		jQuery('#liofficeId input').val('');
	}
	jQuery('#state').empty();
	jQuery.ajax({
		url:'${stateListPopulateURL}',
		type:'POST',
		data: {
				countryCode:jQuery('#country').val()
		      }, 
		beforeSend: function(){
				jQuery('#loadingImage').show();
			},
		success: function(stateListResult){
					var countryCode=jQuery('#country').val();
					if(stateListResult.substring(0,2)==countryCode){
						jQuery('#state').empty();
						jQuery("#state").append(stateListResult.substring(2));
					}
					jQuery('#loadingImage').hide();	
					//Show the State dropdown if there exists any state.
					if(stateListResult!=(countryCode+"<option></option>")){
						jQuery('#state').removeAttr('disabled');
					}else{
						jQuery('#state').attr('disabled','disabled');
					}
					
					//Below code is for Back button to populate country n state list
					if(jQuery('#stateFormVal').val()!="")
						jQuery('#state').val(jQuery('#stateFormVal').val());		
			}
	});
	
}

function goForAddressCleansing(errorDivId){


	
	jQuery('#'+errorDivId).hide();
	jQuery.ajax({
			url:'${newAddressValidateURL}',
			beforeSend: function()
			{
				showOverlay();
			},
			type:'POST',
			dataType: 'JSON',
			data: {
					<%=ADDRESSCLEANSEFIELDS_INPUT[0]%>: jQuery('#storeName').val(),
					<%=ADDRESSCLEANSEFIELDS_INPUT[1]%>: jQuery('#addrLine1').val(),
					<%=ADDRESSCLEANSEFIELDS_INPUT[2]%>: jQuery('#addrLine2').val(),
					<%=ADDRESSCLEANSEFIELDS_INPUT[3]%>: jQuery('#city').val(),
					<%=ADDRESSCLEANSEFIELDS_INPUT[4]%>:	jQuery('#country option:selected').val(),
					<%=ADDRESSCLEANSEFIELDS_INPUT[5]%>:	jQuery('#state option:selected').val(), 	
					<%=ADDRESSCLEANSEFIELDS_INPUT[6]%>: jQuery('#zipCode').val(),
					<%=ADDRESSCLEANSEFIELDS_INPUT[7]%>: jQuery('#officeNo').val()
					
					
				},
			success: function(results){
					//alert('success');
					hideOverlay();
					var obj2;
					try{
						 obj2=results;
					}catch(e){
						//alert('exception occured');
						}
					
					if(obj2 !=null){						
						var error=obj2.error;
						//alert('obj2 is not null');
						//alert(error);
						if(error=="none"){

							
							jQuery.each(obj2, function(name, value) {
									cleaseAddressFields[name]=value;		
								});

							jQuery('#cleanseAddressOutput').html(generateAddressDisplayHTML(obj2));
							
							
							//display the cleansed address
							jQuery('#cleansedAddress').show();
							//if error div is already show hide it
							jQuery('#'+errorDivId).hide();
							document.getElementById('ignoreAndSaveBTN').style.display = "none";
							
							jQuery("#ignoreSaveAddress").hide();
							callCleansing=false;
							}
						else if(error=="cleanseError")
							{
							callCleansing=true;
							jQuery('#'+errorDivId).html(obj2.cleansedError);
							jQuery('#'+errorDivId).show();
							jQuery("#ignoreSaveAddress").show();
							}
						else{
							callCleansing=true;
								jQuery('#'+errorDivId).html('');
								jQuery.each(obj2, function(name, value) {
									if(value!="yes")
									jQuery('#'+errorDivId).append('<li><strong>'+value+'</strong></li>');
									
								});
								jQuery('#'+errorDivId).show();
							
																
							}
					}	else{
						//show the error div with error details
						
						//alert('in else');
						//alert(results);
						jQuery('#'+errorDivId).html('');
						jQuery('#'+errorDivId).append(results);
						jQuery('#'+errorDivId).show();
						callCleansing=true;
						
						}
				},
			failure: function(results){
						callCleansing=true;
						jQuery('#'+errorDivId).html('<li><strong>Unable to cleanse address</strong></li>');
						jQuery('#'+errorDivId).show();
					}
		});
	
}

function validateAddress(){

	//Validation code goes here
	var validationFlag=true;
	/*
	These validations are for mandatory fields
	*/
	
	//if(jQuery('#addrLine1').val().trim()=="")
	if(jQuery.trim(jQuery('#addrLine1').val())=="")
	{
		validationFlag=false;
		jQuery('#errorMsg').append('<li><strong><spring:message code="validation.address.addrline1.empty"/></strong></li>');
		jQuery('#addrLine1').addClass('errorColor');
	}
	if(jQuery.trim(jQuery('#city').val())=="")
	{
		validationFlag=false;
		jQuery('#errorMsg').append('<li><strong><spring:message code="validation.address.city.empty"/></strong></li>');
		jQuery('#city').addClass('errorColor');
	}
	
	if(jQuery('#country option:selected').text()=="")
	{
		validationFlag=false;
		jQuery('#errorMsg').append('<li><strong><spring:message code="validation.address.country.empty"/></strong></li>');
		jQuery('#country').addClass('errorColor');
	}
	
	if((jQuery.trim(jQuery('#zipCode').val())=="")&&(jQuery('#state option:selected').text()=="")){
		
		validationFlag=false;
		jQuery('#errorMsg').append('<li><strong><spring:message code="validation.address.ziporstate.empty"/></strong></li>');
		jQuery('#state').addClass('errorColor');
		jQuery('#zipCode').addClass('errorColor');
		}	
	return validationFlag;
}

function copyCleansedValuesToInput(){


	//Clear all inputs
	for(i=0;i<cleaseAddressFields.keys.length;i++){
		var inputHTMLId="genericAddress."+cleaseAddressFields.keys[i];
		if(cleaseAddressFields.keys[i]=="isAddressCleansed"){
			jQuery("[name='"+inputHTMLId+"']").val(false);
		}else{
			jQuery("[name='"+inputHTMLId+"']").val('');	
			}
		
		
	}
	if(jQuery('#checkCleanse').attr('checked')){
		for(i=0;i<cleaseAddressFields.keys.length;i++){
				var inputHTMLId="genericAddress."+cleaseAddressFields.keys[i];
				if("addressName"==cleaseAddressFields.keys[i]){
					jQuery("[name='"+inputHTMLId+"']").val(jQuery('#addName').val());
				}else if(cleaseAddressFields.keys[i]=="isAddressCleansed"){
					jQuery("[name='"+inputHTMLId+"']").val(true);
				}else{
 					jQuery("[name='"+inputHTMLId+"']").val(cleaseAddressFields[cleaseAddressFields.keys[i]]);
				}
	    	}
	}else{
		//alert(cleaseAddressFields.inputIds.length);
		for(i=0;i<cleaseAddressFields.inputIds.length;i++){
			
			var inputHTMLId="genericAddress."+cleaseAddressFields.keys[i];
			
			jQuery("[name='"+inputHTMLId+"']").val(jQuery('#'+cleaseAddressFields.inputIds[i]).val());
			//alert('inputHTMLId='+inputHTMLId+'val='+jQuery('#'+cleaseAddressFields.inputIds[i]).val());	
			}
		
		

	}
	

	
}

function ignoreAndSave(){
	callCleansing=false;
}


/* Added for CI7 BRD14-02-12 */
ajaxSuccessFunction=function updateRequest(){
	window.location.href="<portlet:actionURL><portlet:param name='action' value='showAddressPage'/></portlet:actionURL>&friendlyURL="+populateURL("manageaddresses",[],[]);					
}
/* END */
</script>
