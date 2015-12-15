<%-- common subtab jsp to select history or create new request --%>
<% request.setAttribute("subTabSelected","createNewRequest"); %>
<%@ include file="../../common/subTab.jsp"%> 
<script type="text/javascript" src="<html:rootPath/>js/addressPopup.js"></script>
<jsp:include page="../../common/validationMPS.jsp" />
<jsp:include page="/WEB-INF/jsp/includeGrid.jsp"/>
<script type="text/javascript"><%@ include file="../../../../js/portletRedirection.js"%></script>
<%@ page import = "com.lexmark.services.util.LexmarkSPOmnitureConstants" %>
<%@page import ="static com.lexmark.services.util.ChangeMgmtConstant.ADDRESSCLEANSEFIELDS_OUTPUT"%>
<style type="text/css"><%@ include file="/WEB-INF/css/jquery/jquery-ui.css" %></style>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/mps.css"/>
<!--[if IE 7]>
	<style type="text/css"><%@ include file="/WEB-INF/css/mps_ie7.css" %></style>
	<style type="text/css">
	/*Changes for MPS 2.1*/
	 .buttonContainer{position:relative!important;}
	/*Ends Changes MPS 2.1*/
	 </style>
<![endif]-->

<portlet:renderURL var="manageContactReviewUrl">
<portlet:param name="action" value="manageContactReview"></portlet:param>
</portlet:renderURL>

<portlet:renderURL var="addressListPopUpUrl" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>">
<portlet:param name="action" value="showAddressListPopUp"></portlet:param>
</portlet:renderURL>

<script type="text/javascript">
var portlet_name="<%=LexmarkSPOmnitureConstants.CHANGECONTACT %>";
var action_primary="<%=LexmarkSPOmnitureConstants.CHANGECONTACTSELECTADIFFERENTCONTACT%>";
var action_secondary="<%=LexmarkSPOmnitureConstants.CHANGECONTACTADDADDITIONALCONTACT%>";
var dialog;
var addressValues={};
var pageType = "changeContact"; //Added for page type for CI 14-02-03
/*
 * If user press enter, form data will be validated
 */
function onKeySubmit(event) {
	var keycode;
	if (window.event) keycode = window.event.keyCode;
	else if (event) keycode = event.which;
	if (keycode==13) {
		validateContact();
	}
}

/*
 * Validate the form data
 */
function validateContact() {
	var validationFlag = true;
	document.getElementById('errorMsg').innerHTML = "";
	document.getElementById('errorMsg').style.display = "none";
	//alert("Front end validation");
	
	if(jQuery('#workphone').val()=='' && jQuery('#alternatephone').val()=='' && jQuery('#emailAddr').val()=='' &&
		jQuery('#building').val()=='${oldContactChange["building"]}' && jQuery('#floor').val()=='${oldContactChange["floor"]}' &&
		jQuery('#office').val()=='${oldContactChange["office"]}' && jQuery('#addressLine1').val()=='${oldContactChange["addressLine1"]}') 
	{	
		jQuery('#errorMsg').append('<li><strong><spring:message code="validation.contact.change.nodata"/></strong></li>');
		validationFlag = false;
	}
	/* Added by jyoti effective date validation */
	if( jQuery('#effDtOfChange').val()!="" && (!validateFormat(jQuery('#effDtOfChange').val(),"effDtOfChange","errorMsg"))){
		
		validationFlag=false;
		jQuery('#effDtOfChange').addClass('errorColor');
	}
	/*Pattern validation added */
	<%--
	Commented for MPS2.1
	if( jQuery('#workphone').val()!="" && (!validateFormat(jQuery('#workphone').val(),"workphone","errorMsg"))){
		
		validationFlag=false;
		jQuery('#workphone').addClass('errorColor');
	}
	if( jQuery('#alternatephone').val()!="" && (!validateFormat(jQuery('#alternatephone').val(),"alternatephone","errorMsg"))){
		
		validationFlag=false;
		jQuery('#alternatephone').addClass('errorColor');
	}

	if( jQuery('#emailAddr').val()!="" && (!validateFormat(jQuery('#emailAddr').val(),"emailAddr","errorMsg"))){
		
		validationFlag=false;
		jQuery('#emailAddr').addClass('errorColor');
	}--%>
	/* pattern validation ends*/	
	//alert("validationFlag "+validationFlag);
	
	/* Check field length */
	if(!descValidate(jQuery('#addtnlDescription').val(),"errorMsg")) {
		validationFlag = false;
		jQuery('#addtnlDescription').addClass('errorColor');
	}
	
	//Submit the page if there is no error
	if (validationFlag)
	{
		jQuery('#attachmentDescriptionID').val(jQuery('#attachmentDescription').val());
		
		showOverlay();
		document.forms["manageContactForm"].action="${manageContactReviewUrl}&requestType=changeContactRequest&timeZoneOffset=" + timezoneOffset;
		document.forms["manageContactForm"].submit();
	}
	//Show error as per above validations
	else {
		document.getElementById('errorMsg').style.display = "block";
		jQuery(document).scrollTop(0);
	}
}

jQuery(document).ready(function(){
	var currentURL = window.location.href;
//	Change Account Link Hide/Show for CI-7 Defect #12274
	if(currentURL.indexOf('/partner-portal') == -1)
	  {	
		  jQuery('#changeaccount').show();
	  }
	else
		{
		jQuery('#changeaccount').hide();
		}
	jQuery('#attachmentDescription').val('${manageContactForm.attachmentDescription}');
	jQuery("#prevSrNo").val('<%= request.getParameter("prevSrNo")%>');	

	
	jQuery(':input').bind('mousedown focus',function(){
		jQuery(this).removeClass('errorColor');
		});

	jQuery('#errorMsg').empty();
	//Show errros if exception has occured
	var isException = '${exception}';
	if (isException) {
		jQuery('#errorMsg').append('<li><strong><spring:message code="exception.continue"/></strong></li>');
		jQuery('#errorMsg').show();
	}
	
	/*
	* Add the selected address from contact list page, to hidden fields.
	* These fields will bind with the form.
	*/
	if (jQuery('#addressLine1').val() == "") {
		//alert("forward flow");
		jQuery('#addressLine1').val("${oldContactChange['addressLine1']}");
		jQuery('#addressLine2').val("${oldContactChange['addressLine2']}");
		//jQuery('#addressLine3').val("${oldContactChange['addressLine3']}");
		jQuery('#city').val("${oldContactChange['city']}");
		jQuery('#country').val("${oldContactChange['country']}");
		jQuery('#state').val("${oldContactChange['state']}");
		jQuery('#province').val("${oldContactChange['province']}");
		jQuery('#zipPostal').val("${oldContactChange['zipPostal']}");
		/*jQuery('#building').val("${oldContactChange['building']}");
		jQuery('#floor').val("${oldContactChange['floor']}");
		jQuery('#office').val("${oldContactChange['office']}");*/
	}
	if(jQuery('#building').val() == "") {
		jQuery('#building').val("${oldContactChange['building']}");
	}
	if(jQuery('#floor').val() == "") {
		jQuery('#floor').val("${oldContactChange['floor']}");
	}
	if(jQuery('#office').val() == "") {
		jQuery('#office').val("${oldContactChange['office']}");
	}

	addressValues["addressLine1"]="${oldContactChange['addressLine1']}";
	addressValues["officeNumber"]="${oldContactChange['officeNumber']}";
	addressValues["addressLine2"]="${oldContactChange['addressLine2']}";
	addressValues["city"]="${oldContactChange['city']}";
	addressValues["county"]="${oldContactChange['county']}";
	addressValues["state"]="${oldContactChange['state']}";
	addressValues["province"]="${oldContactChange['province']}";
	addressValues["district"]="${oldContactChange['district']}";
	addressValues["postalCode"]="${oldContactChange['zipPostal']}";
	addressValues["country"]="${oldContactChange['country']}";

	
	setValuestoLabel();
	
});

/*
 * Show the selected address to page
 */
function setValuestoLabel() {
	jQuery('#changeContactAddr').html(generateAddressDisplayHTML(addressValues));
	<%--
	jQuery('#addressLine1Label').html(jQuery('#addressLine1').val());
	jQuery('#addressLine2Label').html(jQuery('#addressLine2').val());
	//jQuery('#addressLine3Label').html(jQuery('#addressLine3').val());
	jQuery('#cityLabel').html(jQuery('#city').val());
	jQuery('#countryLabel').html(jQuery('#country').val());
	jQuery('#stateLabel').html(jQuery('#state').val());
	jQuery('#provinceLabel').html(jQuery('#province').val());
	jQuery('#zipPostalLabel').html(jQuery('#zipPostal').val());

	//changes for MPS 2.1
	//This is for display purpose pickup address
	jQuery("#pickupStoreFrontNameLbl").html(jQuery('#strFrntNm').val());
	jQuery("#pickupAddressLine1Lbl").html(addressLine1);
	jQuery("#pickupAddressofficeNumberLbl").html(officeNumber)		
	if(addressLine2 !='' && addressLine2 !=' ' && addressLine2 != null){
		jQuery("#pickupAddressLine2Lbl").html(addressLine2+",");
		jQuery("#pickupAddressLine2Lbl").show();
		}

	if(addressCity !='' && addressCity !=' ' && addressCity != null){
		jQuery("#pickupAddressCityLbl").html(addressCity);
		}
	
	if(county !='' && county !=' ' && county != null){
		jQuery("#pickupAddresscountyLbl").html(', '+county);
		jQuery("#pickupAddresscountyLbl").show();
		}
	if(stateCode !='' && stateCode !=' ' && stateCode != null){
		jQuery("#pickupAddressStateLbl").html(", "+stateCode);
		jQuery("#pickupAddressStateLbl").show();
		}
	if(addressProvince !='' && addressProvince !=' ' && addressProvince != null){
		jQuery("#pickupAddressProvinceLbl").html(', '+addressProvince);
		jQuery("#pickupAddressProvinceLbl").show();
		}
	// region changed to district fot MPS 2.1
	if(district !=' ' && district !=' ' && district != null){
		jQuery("#pickupAddressRegionLB").html(', '+district);
		jQuery("#pickupAddressRegionLB").show();
		}
	jQuery("#pickupAddressPostCodeLbl").html(addressPostCode);		
	jQuery("#pickupAddressCountryLbl").html(addressCountry);
	if(addressProvince =='' || addressProvince ==' ' || addressProvince == null){
		jQuery("#pickupAddressProvinceLbl").hide();
		}
	if(county =='' || county ==' ' || county == null){
		jQuery("#pickupAddresscountyLbl").hide();
		}
	// region changed to district for MPS 2.1
	if(district ==' '||district =='' || district == null){
		jQuery("#pickupAddressRegionLB").hide();
		}
	if(addressLine2 =='' || addressLine2 ==' ' || addressLine2 == null){
		jQuery("#pickupAddressLine2Lbl").hide();
		}
	if(stateCode =='' || stateCode ==' ' || stateCode == null){
		jQuery("#pickupAddressStateLbl").hide();
		}
	//changes for MPS 2.1 end
	--%>
}

/*
 * Open the select address popup
 */
function popupAddress(hyperlinkId){
	//alert('in popup address');
	linkId=hyperlinkId;//This link id is for displaying inner htmls in address
	//alert(linkId);
	showOverlay();
	jQuery('#dialogAddress').load(jQuery('#'+hyperlinkId).attr('href'),function(){
	//alert(jQuery('#'+hyperlinkId).attr('href'));	
		dialog=jQuery('#content_addr').dialog({
			autoOpen: false,
			title: jQuery('#'+hyperlinkId).attr('title'),
			modal: true,
			draggable: false,
			resizable: false,
			height: 500,
			width: 950,
			close: function(event,ui){
				hideOverlay();
				dialog=null;
				jQuery('#content_addr').remove();
				}
			});
		jQuery(document).scrollTop(0);
		dialog.dialog('open');
		initializeAddressGrid();
		
		});
	return false;
}

/*
 * Select the address from popup and set to from data
 */
//MPS 2.1 Changes
function addServiceAddressElement(addressId,addressName,addressLine1,addressLine2,
		city,addressState,addressProvince,addressCountry,addressPostCode,storeFrontName,building,floor,office,favourite,officeNumber,district,county,countryisocode,region)
{
	//setValuestoForm(addressLine1,addressLine2,addressLine3,city,addressCountry,addressState,addressPostCode,building,floor,office);
	
	jQuery('#addressLine1').val(addressLine1);
	jQuery('#addressLine2').val(addressLine2);
	//jQuery('#addressLine3').val(addressLine3);
	jQuery('#city').val(city);
	jQuery('#country').val(addressCountry);
	jQuery('#state').val(addressState);
	jQuery('#province').val(addressProvince);
	jQuery('#zipPostal').val(addressPostCode);
	jQuery('#strFrntNm').val(storeFrontName);
	
	jQuery('#officeNumber').val(officeNumber);
	jQuery('#district').val(district);
	jQuery('#county').val(county);
	jQuery('#countryisocode').val(countryisocode);
	jQuery('#region').val(region);

	addressValues["addressName"]=addressName;
	addressValues["addressLine1"]=addressLine1;
	addressValues["officeNumber"]=officeNumber;
	addressValues["addressLine2"]=addressLine2;
	addressValues["city"]=city;
	addressValues["county"]=county;
	addressValues["state"]=addressState;
	addressValues["province"]=addressProvince;
	addressValues["district"]=district;
	addressValues["postalCode"]=addressPostCode;
	addressValues["country"]=addressCountry;
	
	
	
	setValuestoLabel();
	closeDialog();
}

/*
 * Close the address popup
 */
function closeDialog()
{
	dialog.dialog('close');
	dialog=null;
	jQuery('#dialogAddress').html("");		
}


/* Added for CI7 BRD14-02-12 */
ajaxSuccessFunction=function updateRequest(){
	window.location.href="<portlet:actionURL><portlet:param name='action' value='showContactPage'/></portlet:actionURL>&friendlyURL="+populateURL("managecontacts",[],[]);					
}
/* END */
</script>
	<div id="content-wrapper-inner">
    <div class="journal-content-article">
      <h1><spring:message code="requestInfo.cm.heading.changeRequest"/></h1> 
      <h2 class="step"><spring:message code="requestInfo.cm.manageContact.heading.contacts"/></h2>
    </div>
    
    <!-- Added for CI7 BRD14-02-12 -->
    <div><h3><strong>
      <%@ include file="/WEB-INF/jsp/common/commonAccountAgreementNameInfo.jsp"%>
      <c:if test='${sessionScope.accountCurrentDetails["rowCount"]>"1"}'>
        		<a href="#" onclick="javascript:showChangeAccountPopup();" class="edit" id="changeaccount"><spring:message code="requestInfo.link.changeAccount"/></a>
        	</c:if>
        	</strong></h3></div>
        	<!-- END -->
        	
    <div class="main-wrap">
      <div class="content">
          <!-- MAIN CONTENT BEGIN -->
          <h3 class="pageTitle"><spring:message code="requestInfo.cm.manageContact.heading.changeContactDetails"/></h3>
          
          <!-- This block is to show server side errors  -->
          <div id="serverErrorMsg">
          <spring:hasBindErrors name="manageContactForm">
          	<div class="error">
				<c:forEach var="error" items="${errors.allErrors}">
			   		<li><strong><spring:message code="${error.code}"/></strong></li>
            	</c:forEach>
     		</div>	
	     </spring:hasBindErrors>
	     <!-- Added for CI 14-02-03 START-->
	      <div id="jsValidationErrors" class="error" style="display: none;"></div>	
	        <div id="validationErrors" class="error" style="display: none;"></div>
	        <%-- This div has been renamed to errorMsgPopup_CatalogDtl because contact popup validations div id was conflicting --%>
     		<div class="error" id="errorMsgPopup_CatalogDtl" style="display:none"></div>
		  <!-- Added for CI 14-02-03 ENDS-->
	     </div>
                    
          <form:form commandName="manageContactForm" method="post" onkeypress="javascript:onKeySubmit(event);">  
          
           <%-- Below section is for the prev sr no and update flag binding--%>
		  <form:hidden path="prevSRNumber" id="prevSrNo"/>
		  
		  
		  
          <!-- This block is to show client side errors  -->
          <div class="error" id="errorMsg" style="display:none"></div>                 

		  <!-- Include the common contact info jsp -->
		  <jsp:include page="/WEB-INF/jsp/common/commonContactInfo.jsp"/>                    
          
          <hr class="separator" />
          <p class="info"><spring:message code="requestInfo.cm.manageContact.heading.requestToChangePhoneAndEmail"/></p>         
		  
		  <!-- Show the selected contacts -->
		  <div class="portletBlock infoBox rounded shadow">
		    <div class="columnsOne">
		    	<div class="columnInner">
				<h4><a href="managecontacts"><spring:message code="requestInfo.link.selectAdifferentContact"/></a><spring:message code="requestInfo.cm.manageContact.heading.selectedContact"/></h4>
				<ul class="form">
	                <li>
	                  <label><spring:message code="requestInfo.contactInfo.label.firstName"/></label>
	                  <span>${oldContactChange['firstName']}</span></li>
	                <li>
	                  <label><spring:message code="requestInfo.contactInfo.label.lastName"/></label>
	                  <span>${oldContactChange['lastName']}</span></li>
              	</ul>
              	</div>
			</div>			
		  </div>
		  <div class="portletBlock infoBox rounded shadow">
		  	<!-- Show the current contact details -->
			<div class="columnsTwo">
				<div class="columnInner">				
					<h4><spring:message code="requestInfo.cm.manageContact.heading.currentContactDetails"/></h4>
                    <ul class="form wordBreak">
					  <li>
                        <label><spring:message code="requestInfo.contactInfo.label.workPhone"/> </label>
                        <span>${oldContactChange['workPhone']}</span>
                      </li>
					  <li>
                        <label><spring:message code="requestInfo.contactInfo.label.alternatePhone"/>  </label>
                        <span>${oldContactChange['alternatePhone']}</span>
                      </li>
					  <li>
                        <label><spring:message code="requestInfo.contactInfo.label.emailAddress"/> </label>
                         <span>${oldContactChange['emailId']}</span>
                      </li>
					</ul>
				</div>
			</div>
	
			<!-- User needs to add the new contact details here -->
			<%-- COMMENTED FOR MPS 2.1
			<div class="columnsTwo splitter">
				<div class="columnInner">
					<h4><spring:message code="requestInfo.cm.manageContact.heading.newContactDetails"/></h4>
                    <ul class="form">
					  <li>
                        <label for="workphone"><spring:message code="requestInfo.contactInfo.label.workPhone"/> </label>
                        <span><form:input id="workphone" path="accountContact.workPhone" class="w100" maxlength="40" /></span>
                      </li>
					  <li>
                        <label for="alternatephone"><spring:message code="requestInfo.contactInfo.label.alternatePhone"/>   </label>
                        <span><form:input id="alternatephone" path="accountContact.alternatePhone" class="w100" maxlength="40" /></span>
                      </li>
					  <li>
                        <label for="emailAddr"><spring:message code="requestInfo.contactInfo.label.emailAddress"/> </label>
                        <span><form:input id="emailAddr" path="accountContact.emailAddress" maxlength="100" /></span>
                      </li>
					</ul>
				</div>
			</div>
			
			--%>
		  </div>
		  
		  <!-- Show the contact address -->
		  <div class="portletBlock infoBox rounded shadow">
		  	<div class="columnsOne">
				<div class="columnInner">
					<h4><a href="${addressListPopUpUrl}" title='<spring:message code="requestInfo.link.selectADifferentAddress"/>' id="diffAddressLink" onclick="return popupAddress(id);">
					<spring:message code="requestInfo.link.selectADifferentAddress"/></a>
					<spring:message code="requestInfo.heading.contactAddress"/></h4>
                			
	                   <ul class="roDisplay">
						  <li id="changeContactAddr">
						 
	                        <div id="addressLine1Label"></div>
	                        <div id="addressLine2Label"></div>
	                        <span id="cityLabel"></span>, <span id="stateLabel"></span> <span id="provinceLabel"></span> 
	                        <span id="zipPostalLabel"></span>
	                        <div id="countryLabel"></div>
	                     
	                      </li>	
	                      <li>
	                        <label for="building"><spring:message code="requestInfo.addressInfo.label.building"/> </label>
	                        <span><form:input id="building" path="accountContact.address.physicalLocation1" class="w100" maxlength="100" /></span>
	                      </li>
						  <li>
	                        <label for="floor"><spring:message code="requestInfo.addressInfo.label.floor"/> </label>
	                        <span><form:input id="floor" path="accountContact.address.physicalLocation2" class="w100" maxlength="100" /></span>
	                      </li>
						  <li>
	                        <label for="office"><spring:message code="requestInfo.addressInfo.label.office"/> </label>
	                        <span><form:input id="office" path="accountContact.address.physicalLocation3" class="w100" maxlength="100" /></span>
	                      </li>					  
	                  </ul>
				</div>
			</div>			
		  </div>

		  <!-- Hidden fields to bind with form -->
		  <div style="display:none">
			<form:input id="addressLine1" path="accountContact.address.addressLine1"/>
			<form:input id="addressLine2" path="accountContact.address.addressLine2"/>
			<form:input id="city" path="accountContact.address.city"/>
			<form:input id="country" path="accountContact.address.country"/>
			<form:input id="state" path="accountContact.address.state"/>
			<form:input id="province" path="accountContact.address.province"/>
			<form:input id="zipPostal" path="accountContact.address.postalCode"/>
			<form:input id="strFrntNm" path="accountContact.address.storeFrontName"/>
			
			
			
						<form:input id="county"  path="accountContact.address.county" />
						<form:input id="officeNumber" path="accountContact.address.officeNumber" />
						<form:input id="stateFullName" path="accountContact.address.stateFullName" />
						<form:input id="district" path="accountContact.address.district" />
						<form:input id="region" path="accountContact.address.region" />
						<form:input id="stateCode" path="accountContact.address.stateCode" />
						<form:input id="latitude" path="accountContact.address.latitude" />
						<form:input id="longitude" path="accountContact.address.longitude" />
						<form:input id="savedErrorMessage" path="accountContact.address.savedErrorMessage" />
						<form:input id="countryISOCode" path="accountContact.address.countryISOCode" />
						<form:input id="isAddressCleansed" path="accountContact.address.isAddressCleansed" />
						
				
	
			
			
			<form:input id="pageName" path="pageName" value="changeContact"/>
		  </div>
		  <input type="hidden" name="attachmentDescriptionID" id="attachmentDescriptionID" />  <!--//Added for CI 14-02-03 -->
		  </form:form>
		 <!-- Added for CI 14-02-03  STARTS -->
		<jsp:include page="/WEB-INF/jsp/common/commonAttachment.jsp" />
		<input type="hidden" id="fileCount" />
		<!--Added for CI 14-02-03  ENDS-->

 		  <!-- This block is for all the buttons at end of page -->
		  <div class="buttonContainer">
            <button class="button_cancel" type="button" onclick="javascript:redirectToHistory('others');"><spring:message code="button.cancel"/></button>
            <button class="button" type="button" onclick="javascript:validateContact();" ><spring:message code="button.continue"/></button>
          </div>
          
          
		  
        <!-- MAIN CONTENT END --> 
      </div>
    </div>
  </div>

  <div id="dialog"></div>
  <div id="dialogAddress" style="display: none;"></div>
<script type="text/javascript">
function addRestFieldsOfCleanseAddress(){
	jQuery('#isAddressCleansed').val("true");
	<%for(String cleanseOutputFields:ADDRESSCLEANSEFIELDS_OUTPUT){%>
		jQuery("#<%=cleanseOutputFields%>").val(cleaseAddressFields["<%=cleanseOutputFields%>"]);
	<%}%>
	jQuery('#changeContactAddr').html(generateAddressDisplayHTML(cleaseAddressFields));	
	}	

//Ends
</script>