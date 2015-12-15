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
<!-- Added for CI 14-02-03 -->
<portlet:resourceURL var="displayAttachmentURL" id="displayAttachment">
	
</portlet:resourceURL>

<script type="text/javascript">

/*
 * From summary page back is disabled
 */
window.history.forward();
function backDisable() { 
	//alert('in noback');
	window.history.forward(); 
}

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

	jQuery('#errorMsg').empty();
	//Show errros if exception has occured
	var isException = '${exception}';
	if (isException) {
		jQuery('#errorMsg').append('<strong><spring:message code="exception.sr.save"/></strong>');
		jQuery('#errorMsg').show();
	}
	
	/*
	 * Add the contact details to form data
	 */
	document.getElementById("firstName").value="${oldContactRemove['firstName']}";
	document.getElementById("middleName").value="${oldContactRemove['middleName']}";
	document.getElementById("lastName").value="${oldContactRemove['lastName']}";
	document.getElementById("workPhone").value="${oldContactRemove['workPhone']}";
	document.getElementById("alternatePhone").value="${oldContactRemove['alternatePhone']}";
	document.getElementById("emailAddress").value="${oldContactRemove['emailId']}";
});

/*
 * Navigate user to remove contact page
 */
function backToManage(){
	showOverlay();
	var link="<%=backToManageAction %>";
	//alert(link);	
	document.forms["manageContactForm"].action = link +"&requestType=removeContactRequest";
	document.forms["manageContactForm"].submit();
}

backDisable();
//Added for CI 14-02-03
function openAttachment(fileName){
	//alert(fileName);
	window.location="${displayAttachmentURL}&fileName="+fileName;
	
}

/* Added for CI7 BRD14-02-12 */
ajaxSuccessFunction=function updateRequest(){
	window.location.href="<portlet:actionURL><portlet:param name='action' value='showContactPage'/></portlet:actionURL>&friendlyURL="+populateURL("managecontacts",[],[]);					
}
/* END */
</script>

<body onload="" onpageshow="if (event.persisted) backDisable();" onunload="">
<form:form action="${contactSummaryUrl}&requestType=removeContactRequest" method="post" modelAttribute="manageContactForm">

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
          <h3 class="pageTitle"><spring:message code="requestInfo.cm.manageContact.heading.removeAcontact"/> &ndash; <spring:message code="requestInfo.heading.review"/></h3>
		  <p class="info banner"><spring:message code="requestInfo.message.reviewMessage"/></p>
          
          <!-- Include the common contact info jsp -->
          <%@ include file="/WEB-INF/jsp/changemanagement/manageContact/commonContactInfoReview.jsp"%> 
           
          <hr class="separator" />    
          <!-- This block is to show the selected contact -->     
		  <div class="portletBlock infoBox rounded shadow">
		  	<div class="columnsOne">
		  		<div class="columnInner">			
            		<h4><spring:message code="requestInfo.cm.manageContact.heading.selectedContact"/></h4>
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
                        <label><spring:message code="requestInfo.contactInfo.label.lastName"/> </label>
                        <span>${oldContactRemove['lastName']}</span>
                      </li>
                      
					  <li>
                        <label><spring:message code="requestInfo.contactInfo.label.workPhone"/> </label>
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
		  
		  <!-- Hidden fields to bind with form -->
		  <span style="display: none">
	                	<form:input id="firstName" path="accountContact.firstName" /> 
						<form:input id="middleName" path="accountContact.middleName" /> 
						<form:input id="lastName" path="accountContact.lastName" /> 
						<form:input id="workPhone" path="accountContact.workPhone" /> 
						<form:input id="alternatePhone" path="accountContact.alternatePhone" />
						<form:input id="emailAddress" path="accountContact.emailAddress" />
						<form:input id="pageName" path="pageName" value="removeContact"/>
						<form:hidden path="attachmentDescription" id="attachmentDescription"/> <%-- //Added for CI 14-02-03 --%>
		  </span>
		  <!-- Add Attachments BLOCK - Start -->
        <p class="inlineTitle"><spring:message code="requestInfo.heading.notesAndAttachments"/></p>
				<div class="portletBlock">
          			<div class="columnsOne">
            			<div class="infoBox columnInner rounded shadow">
            				<h4><spring:message code="requestInfo.heading.notes"/></h4>
            				 <p class="multiLine">${manageContactForm.attachmentDescription}</p>
            			</div>
            		</div>
            	</div>
            	<c:if test="${not empty attachmentForm.attachmentList}">
        <div class="portletBlock infoBox">
					<div class="wHalf displayGrid columnInnerTable rounded shadow">
						<table>
						<thead><tr><th><spring:message code="requestInfo.heading.attachmentFileName"/></th><th class="w120 right"><spring:message code="requestInfo.heading.attachmentFileSizeKiloBytes"/></th></tr></thead>
							 <tbody>
								<c:forEach items="${attachmentForm.attachmentList}" var="attachmentListDetail" varStatus="counter" begin="0">
								<c:choose>
									<c:when test="${counter.count % 2 == 0}">
									   <tr class="altRow">
									</c:when>
									<c:otherwise>
									   <tr>
									</c:otherwise>
								</c:choose>
										<td class="w70p"><a href="javascript:void(0);" onclick="openAttachment('${attachmentListDetail.attachmentName}');">${attachmentListDetail.displayAttachmentName}</a></td>
										<td class="right"><fmt:formatNumber type="number" maxFractionDigits="2" minFractionDigits="2" value="${attachmentListDetail.size div 1024}"/></td>
									</tr>
								</c:forEach>
							 </tbody>
						</table>
					</div>
				</div>
				</c:if>
        <!-- Add Attachments BLOCK - End -->
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