<%@page import="javax.portlet.RenderResponse"%>
<%-- common subtab jsp to select history or create new request --%>
<% request.setAttribute("subTabSelected","createNewRequest"); %>
<jsp:include page="../../common/subTab.jsp"></jsp:include> 

<%@ include file="/WEB-INF/jsp/include.jsp"%>
<jsp:include page="/WEB-INF/jsp/includeGrid.jsp"/>
<script type="text/javascript"><%@ include file="../../../../js/portletRedirection.js"%></script>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/mps.css"/>
<!--[if IE 7]>
	<style type="text/css"><%@ include file="/WEB-INF/css/mps_ie7.css" %></style>
<![endif]-->

<portlet:actionURL var="contactSummaryUrl">
	<portlet:param name="action" value="contactSummary"></portlet:param>
</portlet:actionURL>
<portlet:renderURL var="backToManageAction">
	<portlet:param name="action" value="backToManage"></portlet:param>
</portlet:renderURL>

<script type="text/javascript">

/*
 * From summary page back is disabled
 */
window.history.forward();
function backDisable() { 
	//alert('in noback');
	window.history.forward(); 
}

/*
 * Show error message if any exception has occured
 */
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
	jQuery('#errorMsg').empty();
	//Show errros if exception has occured
	var isException = '${exception}';
	if (isException) {
		jQuery('#errorMsg').append('<strong><spring:message code="exception.sr.save"/></strong>');
		jQuery('#errorMsg').show();
	}
});

/*
 * Navigate user to add contact page
 */
function backToManage(){
	showOverlay();
	var link="<%=backToManageAction %>";
	document.forms["manageContactForm"].action = link +"&requestType=addContactRequest";
	document.forms["manageContactForm"].submit();
}

//alert('in backdisable');
backDisable();


/* Added for CI7 BRD14-02-12 */
ajaxSuccessFunction=function updateRequest(){
	window.location.href="<portlet:actionURL><portlet:param name='action' value='showContactPage'/></portlet:actionURL>&friendlyURL="+populateURL("managecontacts",[],[]);					
}
/* END */
</script>


<body onload="" onpageshow="if (event.persisted) backDisable();" onunload="">
<form:form action="${contactSummaryUrl}&requestType=addContactRequest" method="post" modelAttribute="manageContactForm">
<form:hidden path="submitToken" />
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
      	  <p class="info banner err" id="errorMsg" style="display:none"></p>
          <!-- MAIN CONTENT BEGIN -->
          <h3 class="pageTitle"><spring:message code="requestInfo.cm.manageContact.heading.addNewContact"/> &ndash; <spring:message code="requestInfo.heading.review"/></h3>
		  <p class="info banner"><spring:message code="requestInfo.message.reviewMessage"/></p>
          
          <!-- Include the common contact info jsp -->
          <%@ include file="/WEB-INF/jsp/changemanagement/manageContact/commonContactInfoReview.jsp"%> 
           
          <hr class="separator" />         
		  <div class="portletBlock infoBox rounded shadow">           				
            <!-- Show the new contact details -->
			<div class="columnsTwo">
				<div class="columnInner">
					<h4><spring:message code="requestInfo.heading.newContact"/></h4>
                    <ul class="form wordBreak">
					  <li>
                        <label><spring:message code="requestInfo.contactInfo.label.firstName"/></label>
                        <span>${manageContactForm.accountContact.firstName}</span>
                      </li>
					  <li>
                        <label><spring:message code="requestInfo.contactInfo.label.middleName"/> </label>
                        <span>${manageContactForm.accountContact.middleName}</span>
                      </li>
					  <li>
                        <label><spring:message code="requestInfo.contactInfo.label.lastName"/> </label>
                        <span>${manageContactForm.accountContact.lastName}</span>
                      </li>
                      
					  <li>
                        <label><spring:message code="requestInfo.contactInfo.label.workPhone"/> </label>
                        <span>${manageContactForm.accountContact.workPhone}</span>
                      </li>
					  <li>
                        <label><spring:message code="requestInfo.contactInfo.label.alternatePhone"/> </label>
                        <span>${manageContactForm.accountContact.alternatePhone}</span>
                      </li>
					  <li>
                        <label><spring:message code="requestInfo.contactInfo.label.emailAddress"/> </label>
                        <span>${manageContactForm.accountContact.emailAddress}</span>
                      </li>
					</ul>
				</div>
			</div>
			<!-- Show the new contact address details -->
			<div class="columnsTwo splitter">		
				<div class="columnInner">
					<h4><spring:message code="requestInfo.cm.manageContact.heading.newContactAddress"/></h4>
                    <ul class="form wordBreak">
					  <li>
                        <label><spring:message code="requestInfo.addressInfo.label.addressLine1"/> </label>
                        <span>${manageContactForm.accountContact.address.addressLine1}</span>
                      </li>
					  <li>
                        <label><spring:message code="requestInfo.addressInfo.label.addressLine2"/> </label>
                        <span>${manageContactForm.accountContact.address.addressLine2}</span>
                      </li>
					  <li>
                        <label><spring:message code="requestInfo.addressInfo.label.city"/> </label>
                        <span>${manageContactForm.accountContact.address.city}</span>
                      </li>
					  <li>
                        <label><spring:message code="requestInfo.addressInfo.label.country"/> </label>
                        <span>${manageContactForm.accountContact.address.country}</span>
                      </li>
					  <li>
                        <label><spring:message code="requestInfo.addressInfo.label.stateProvince"/> </label>
                        <span>${manageContactForm.accountContact.address.state}</span>
                      </li>
					  
					  <li>
                        <label><spring:message code="requestInfo.addressInfo.label.postalCode"/> </label>
                        <span>${manageContactForm.accountContact.address.postalCode}</span>
                      </li>
					  <li>
                        <label><spring:message code="requestInfo.addressInfo.label.building"/> </label>
                        <span>${manageContactForm.accountContact.address.physicalLocation1}</span>
                      </li>
					  <li>
                        <label><spring:message code="requestInfo.addressInfo.label.floor"/> </label>
                        <span>${manageContactForm.accountContact.address.physicalLocation2}</span>
                      </li>
					  <li>
                        <label><spring:message code="requestInfo.addressInfo.label.office"/> </label>
                        <span>${manageContactForm.accountContact.address.physicalLocation3}</span>
                      </li>
					</ul>
				</div>				
			</div>
		  </div>
		  
		  <!-- Hidden fields to bind with form -->
		  <div style="display:none">
		  <form:input id="pageName" path="pageName" value="addContact"/>
		  </div>
		   
		  <!-- This block is for all the buttons at end of page -->
		  <div class="buttonContainer">
			<button class="button_cancel" type="button" onclick="backToManage();"><spring:message code="button.back"/></button>
            <button class="button_cancel ie7_fix_margin" type="button" onclick="javascript:redirectToHistory('others');"><spring:message code="button.cancel"/></button>
            <button class="button" type="submit"><spring:message code="button.submit"/></button>
          </div>
        <!-- MAIN CONTENT END --> 
      </div>
    </div>
</div>
</form:form>
</body>