<%-- common subtab jsp to select history or create new request --%>
<% request.setAttribute("subTabSelected","createNewRequest"); %>
<%@ include file="../../common/subTab.jsp"%>

<jsp:include page="../../common/validationMPS.jsp" />
<%@ page import = "com.lexmark.services.util.LexmarkSPOmnitureConstants" %>
<jsp:include page="/WEB-INF/jsp/includeGrid.jsp"/>
<script type="text/javascript"><%@ include file="../../../../js/portletRedirection.js"%></script>
<style type="text/css"><%@ include file="/WEB-INF/css/jquery/jquery-ui.css" %></style>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/mps.css"/>
<!--[if IE 7]>
	<style type="text/css"><%@ include file="/WEB-INF/css/mps_ie7.css" %></style>
<![endif]-->

<portlet:renderURL var="manageContactReviewUrl">
<portlet:param name="action" value="manageContactReview"></portlet:param>
</portlet:renderURL>

<script type="text/javascript">
var portlet_name="<%=LexmarkSPOmnitureConstants.DECOMMISSIONCONTACT  %>";
var action_primary="<%=LexmarkSPOmnitureConstants. DECOMMISSIONCONTACTSELECTADIFFERENTCONTACT%>";
var action_secondary="<%=LexmarkSPOmnitureConstants.DECOMMISSIONADDRESSREMOVEADDITIONALCONTACT%>";
var pageType = "removeContact"; //Added for page type for CI 14-02-03

jQuery(document).ready( function() {
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

	

	jQuery('#errorMsg').empty();
	//Show errros if exception has occured
	var isException = '${exception}';
	if (isException) {
		jQuery('#errorMsg').append('<li><strong><spring:message code="exception.continue"/></strong></li>');
		jQuery('#errorMsg').show();
	}

});

/*
 * If user press enter, form data will be validated
 */
function onKeySubmit(event) {
	var keycode;
	if (window.event) keycode = window.event.keyCode;
	else if (event) keycode = event.which;
	
	if (keycode==13) {
		//alert("before validate");
		validateContact();
	}
}

/*
 * Validate the form data
 */
function validateContact() {
	//alert("in validate");
	var validationFlag = true;
	document.getElementById('errorMsg').innerHTML = "";

	/* Check field length */
	if(!descValidate(jQuery('#addtnlDescription').val(),"errorMsg")) {
		//alert('desc long');
		validationFlag = false;
		jQuery('#addtnlDescription').addClass('errorColor');
	}
	/* Added by jyoti effective date validation */
	if( jQuery('#effDtOfChange').val()!="" && (!validateFormat(jQuery('#effDtOfChange').val(),"effDtOfChange","errorMsg"))){
		
		validationFlag=false;
		jQuery('#effDtOfChange').addClass('errorColor');
	}
	//alert("Front end validation");
	
	/*
	//Validate Customer Reference Id
	if(!custRefIdValidate(document.getElementById("custReferenceId").value,"custReferenceId","removeContact")){
		validationFlag = false;
	}
	//Validate Cost Center
	if(!costCenterValidate(document.getElementById("costCenter").value,"costCenter","removeContact")){
		validationFlag = false;
	}*/

	//Validate Date
	/*
	if(!dateValidate(document.getElementById("effDtOfChange").value,"effDtOfChange","removeContact")){
		//alert("nameValidate firstname");
		validationFlag = false;
	}*/

	//Show error as per above validations
	if (validationFlag)
	{		
		showOverlay();
		jQuery('#attachmentDescriptionID').val(jQuery('#attachmentDescription').val());
		document.forms["manageContactForm"].action="${manageContactReviewUrl}&requestType=removeContactRequest&timeZoneOffset=" + timezoneOffset;
		document.forms["manageContactForm"].submit();
	}
	else {
		document.getElementById('errorMsg').style.display = "block";
		jQuery(document).scrollTop(0);
	}
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
          <h3 class="pageTitle"><spring:message code="requestInfo.cm.manageContact.heading.removeAcontact"/></h3>
          
          <!-- This block is to show server side errors  -->
          <spring:hasBindErrors name="manageContactForm">
          	<div class="error">
				<c:forEach var="error" items="${errors.allErrors}">
			   		<li><strong><spring:message code="${error.code}"/></strong></li>
            	</c:forEach>
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
         <%-- Commented for defect no. #1846
          <p class="info"><spring:message code="requestInfo.cm.manageContact.heading.pleaseEnterTheFollowingInformationToAddaNewContact"/></p>--%>         
          
          <!-- This block is to show the selected contact -->
		  <div class="portletBlock infoBox rounded shadow">
			  <div class="columnsOne">
			  <div class="columnInner">
	            	<h4><a href="managecontacts"><spring:message code="requestInfo.link.selectAdifferentContact"/></a>
	            		<spring:message code="requestInfo.cm.manageContact.heading.selectedContact"/>
	            	</h4>
	                    <ul class="form">
						  <li>
	                        <label><spring:message code="requestInfo.contactInfo.label.firstName"/> </label>
	                        <span>${oldContactRemove['firstName']}</span>
	                      </li>
						  <li>
	                        <label><spring:message code="requestInfo.contactInfo.label.middleName"/> </label>
	                        <span>${oldContactRemove['middleName']}</span>
	                      </li>
						  <li>
	                        <label><spring:message code="requestInfo.contactInfo.label.lastName"/></label>
	                        <span>${oldContactRemove['lastName']}</span>
	                      </li>
	                      
						  <li>
	                        <label><spring:message code="requestInfo.contactInfo.label.workPhone"/></label>
	                        <span>${oldContactRemove['workPhone']}</span>
	                      </li>
						  <li>
	                        <label><spring:message code="requestInfo.contactInfo.label.alternatePhone"/> </label>
	                        <span>${oldContactRemove['alternatePhone']}</span>
	                      </li>
						  <li>
	                        <label><spring:message code="requestInfo.contactInfo.label.emailAddress"/> </label>
	                        <span>${oldContactRemove['emailId']}</span>
	                      </li>
						</ul>				
			  </div>
			  </div>
		  </div>
		  		  
 		  <div style="display:none">
 		  <input type="hidden" name="attachmentDescriptionID" id="attachmentDescriptionID" />  <!--//Added for CI 14-02-03 -->
			    <form:input id="pageName" path="pageName" value="removeContact"/>
		  </div>
		  </form:form>
		  <!-- Added for CI 14-02-03  STARTS -->
		  <jsp:include page="/WEB-INF/jsp/common/commonAttachment.jsp" />
		  <input type="hidden" id="fileCount" />
		  <!--Added for CI 14-02-03  ENDS-->
  
  		  <!-- This block is for all the buttons at end of page -->
		  <div class="buttonContainer">
            <button class="button_cancel" type="button" onclick="javascript:redirectToHistory('others');"><spring:message code="button.cancel"/></button>
            <button class="button" type="button" onclick="javascript:validateContact();"><spring:message code="button.continue"/></button>
          </div>
          
          
		  
        <!-- MAIN CONTENT END --> 
      </div>
    </div>
</div>

  <div id="dialog"></div>
