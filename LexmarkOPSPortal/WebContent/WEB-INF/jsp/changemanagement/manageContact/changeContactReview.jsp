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
	
	//Add the contact details with form data
	document.getElementById("firstName").value="${oldContactChange['firstName']}";
	document.getElementById("middleName").value="${oldContactChange['middleName']}";
	document.getElementById("lastName").value="${oldContactChange['lastName']}";
	document.getElementById("workPhone").value='${manageContactForm.accountContact.workPhone}';
	document.getElementById("alternatePhone").value='${manageContactForm.accountContact.alternatePhone}';
	document.getElementById("emailAddress").value='${manageContactForm.accountContact.emailAddress}';

	if('${manageContactForm.accountContact.address.addressLine1}'=="" || '${manageContactForm.accountContact.address.addressLine1}'==null) {
		jQuery('#changeContactAddr').hide();
	}
	else {
		jQuery('#changeContactAddr').show();
	}
	
});

/*
 * Navigate user to change contact page
 */
function backToManage(){
	showOverlay();
	var link="<%=backToManageAction %>";
	//alert(link);
	document.forms["manageContactForm"].action = link +"&requestType=changeContactRequest";
	document.forms["manageContactForm"].submit();
}
//alert('back disable');
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
<form:form action="${contactSummaryUrl}&requestType=changeContactRequest" method="post" modelAttribute="manageContactForm">

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
          <h3 class="pageTitle"><spring:message code="requestInfo.cm.manageContact.heading.changeContactDetails"/> &ndash; <spring:message code="requestInfo.heading.review"/></h3>
		  <p class="info banner"><spring:message code="requestInfo.message.reviewMessage"/></p>
          
          <%@ include file="/WEB-INF/jsp/changemanagement/manageContact/commonContactInfoReview.jsp"%> 
          
          <hr class="separator" />        
		  
		  <!-- Show the selected contacts -->
		  <div class="portletBlock infoBox rounded shadow">
			<div class="columnsOne">
				<div class="columnInner">
					<h4><spring:message code="requestInfo.cm.manageContact.heading.selectedContact"/></h4>
                    <ul class="form">
                      <li>
                        <label><spring:message code="requestInfo.contactInfo.label.firstName"/> </label>
                        <span>${oldContactChange['firstName']}</span>
                      </li>
					  <li>
                        <label><spring:message code="requestInfo.contactInfo.label.lastName"/> </label>
                        <span>${oldContactChange['lastName']}</span>
                      </li>
					</ul>
				</div>
			</div>			
		  </div>
		  <div class="portletBlock infoBox rounded shadow">
		  	<!-- Show the current contact details -->
			<div class="columnsOne">
				<div class="columnInner">				
					<h4><spring:message code="requestInfo.cm.manageContact.heading.currentContactDetails"/></h4>
                    <ul class="form wordBreak">
					  <li>
                        <label><spring:message code="requestInfo.contactInfo.label.workPhone"/> </label>
                        <span>${oldContactChange['workPhone']}</span>
                      </li>
					  <li>
                        <label><spring:message code="requestInfo.contactInfo.label.alternatePhone"/> </label>
                        <span>${oldContactChange['alternatePhone']}</span>
                      </li>
					  <li>
                        <label><spring:message code="requestInfo.contactInfo.label.emailAddress"/> </label>
                         <span>${oldContactChange['emailId']}</span>
                      </li>
					</ul>
				</div>
			</div>
			<%-- Commented mps 2.1	
			<!-- Show the new contact details -->
			<div class="columnsTwo splitter">
				<div class="columnInner">					
					<h4><spring:message code="requestInfo.cm.manageContact.heading.newContactDetails"/></h4>		
                    <ul class="form wordBreak">
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
			--%>
		  </div>
		  
		  <!-- Show the contact address -->
		  <div class="portletBlock infoBox rounded shadow">
			<div class="columnsOne">
				<div class="columnInner">
					<h4><spring:message code="requestInfo.heading.contactAddress"/></h4>
					<ul class="roDisplay">
						  <li id="changeContactAddr">
						  <util:addressOutput value="${manageContactForm.accountContact.address}"></util:addressOutput>
						  <%-- Commented in MPS 2.1
	                        <div>${manageContactForm.accountContact.address.addressLine1}</div>
	                        <div>${manageContactForm.accountContact.address.addressLine2}</div>
                        
	                        <span>${manageContactForm.accountContact.address.city}</span>, 
	                        <span>${manageContactForm.accountContact.address.state}</span> 
	                        <span>${manageContactForm.accountContact.address.province}</span> 
	                        <span>${manageContactForm.accountContact.address.postalCode}</span>
	                        
	                        <div>${manageContactForm.accountContact.address.country}</div> --%>
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
		  <span style="display: none">
	                	<form:input id="firstName" path="accountContact.firstName" /> 
						<form:input id="middleName" path="accountContact.middleName" /> 
						<form:input id="lastName" path="accountContact.lastName" /> 
						<form:input id="workPhone" path="accountContact.workPhone" /> 
						<form:input id="alternatePhone" path="accountContact.alternatePhone" />
						<form:input id="emailAddress" path="accountContact.emailAddress" />
						<form:input id="pageName" path="pageName" value="changeContact"/>
						<form:hidden path="attachmentDescription" id="attachmentDescription"/> <%-- //Added for CI 14-02-03 --%>
		  </span>
		  <!-- Add Attachments BLOCK - Start -->
        <p class="inlineTitle"><spring:message code="requestInfo.heading.notesAndAttachments"/></p>
				<div class="portletBlock">
          			<div class="columnsOne">
            			<div class="infoBox columnInner rounded shadow">
            				<h4><spring:message code="requestInfo.heading.notes"/></h4>
            				 <p class="multiLine noteWrap notesOverflow">${manageContactForm.attachmentDescription}</p>
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
			<button class="button_cancel" type="button" onclick="javascript:backToManage();"><spring:message code="button.back"/></button>
            <button class="button_cancel ie7_fix_margin" type="button" onclick="javascript:redirectToHistory('others');"><spring:message code="button.cancel"/></button>
            <button class="button" type="submit"><spring:message code="button.submit"/></button>
          </div>
        <!-- MAIN CONTENT END --> 
      </div>
    </div>
  </div>
</form:form>
</body>