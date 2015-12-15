<%@ taglib prefix="util" uri="/WEB-INF/tld/lexmark-util.tld" %>
<% request.setAttribute("subTabSelected","createNewRequest"); %>
<%@ include file="/WEB-INF/jsp/common/subTab.jsp"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<jsp:include page="/WEB-INF/jsp/includeGrid.jsp"/>
<%@ page import="com.lexmark.services.form.FileUploadForm"%>
<%@ page import = "com.lexmark.services.util.LexmarkSPOmnitureConstants" %>
<script type="text/javascript"><%@ include file="../../../../js/portletRedirection.js"%></script>

<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/mps.css"/>
<!--[if IE 7]>
	<style type="text/css"><%@ include file="/WEB-INF/css/mps_ie7.css" %></style>
<![endif]-->

<style type="text/css">
	.ie7 .button, .ie7 .button_cancel { 
	 	margin-left: 5px;
	 	padding-left: .8em !important;
	 	behavior: url(/LexmarkServicesPortal/WEB-INF/css/PIE.htc);
		position: static;		
	 }
</style>

<portlet:actionURL var="createRequestAction">
	<portlet:param name="action" value="createRequestAction" />
</portlet:actionURL>
<portlet:actionURL var='backToEditAction'>
	<portlet:param name='action' value='backToEdit' />
</portlet:actionURL>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/in-styles.css?version=<html:fileCacheVersion/>" />

<body onload="backDisable();" onpageshow="if (event.persisted) backDisable();" onunload="">
<div id="content-wrapper">
    <div class="journal-content-article">
      <h1><spring:message code="requestInfo.cm.heading.changeRequest"/></h1>
      <h2 class="step"><spring:message code = "requestInfo.cm.label.acceptanceRequest"/></h2> 
      </div>
      
      <!-- Added for CI7 BRD14-02-12 -->
      <div><h3><strong>
      <%@ include file="/WEB-INF/jsp/common/commonAccountAgreementNameInfo.jsp"%>
    	<c:if test='${sessionScope.accountCurrentDetails["rowCount"]>"1"}'>
        		<a href="#" onclick="javascript:showChangeAccountPopup();" class="edit" id="changeaccount"><spring:message code="requestInfo.link.changeAccount"/></a>
        	</c:if>
        	</strong>
        	</h3>
        	</div>
        	<!-- END -->
        	
      <div class="main-wrap">
	<div class="content">
	<!-- MAIN CONTENT BEGIN -->
          <h3 class="pageTitle"><spring:message code = "requestInfo.heading.acceptanceRequest"/> - <spring:message code="requestInfo.heading.review"/></h3>
          	
				<p class="info banner"><spring:message code="requestInfo.message.reviewMessage"/>
				
				<!-- Exceptions displayed in the below div -->
	          	 <c:if test="${exceptionList != null}">
	       			<div class="info banner err" id="dispalyExceptions">
		       			<c:forEach items="${exceptionList}" var="entry">
				       		<li><strong><spring:message code="requestInfo.error.exception"/> - ${entry}</strong></li>		       		
				     	</c:forEach>
	      			</div>
	          	</c:if>  
	          	 
	          	
	          	<form:form id="assetAcceptFormId" commandName="assetAcceptForm" method="post" action="${createRequestAction}" enctype="multipart/form-data" >
		  
	          <div class="portletBlock">
	            <div class="columnsTwo">
	              <div class="columnInner rounded infoBox shadow">
	               
	                	<h4><spring:message code="requestInfo.heading.primaryContactForThisRequest"/></h4>
	                	
	                	<ul class="form wordBreak">
	                  <li>
	                    <label><spring:message code="requestInfo.label.name"/></label>
	                    <span id="firstNameLabel"> ${assetAcceptForm.serviceRequest.primaryContact.firstName } ${assetAcceptForm.serviceRequest.primaryContact.lastName } </span>
	                  </li>
	                  <li>
	                    <label><spring:message code="requestInfo.label.phoneNumber"/></label>
	                    <span id="workPhoneLabel">${assetAcceptForm.serviceRequest.primaryContact.workPhone } </span>
	                  </li>
	                  <li>
	                    <label><spring:message code="requestInfo.label.emailAddress"/></label>
	                    <span id="emailAddressLabel">${assetAcceptForm.serviceRequest.primaryContact.emailAddress }</span>  
	                  </li>
	                </ul>
	                <span style="display: none">
	                	<form:input path="serviceRequest.requestor.contactId" /> 
						<form:input path="serviceRequest.requestor.firstName" /> 
						<form:input path="serviceRequest.requestor.lastName" /> 
						<form:input path="serviceRequest.requestor.workPhone" /> 
						<form:input path="serviceRequest.requestor.emailAddress" />
						
						<form:input path="serviceRequest.primaryContact.contactId" /> 
						<form:input path="serviceRequest.primaryContact.firstName" /> 
						<form:input path="serviceRequest.primaryContact.lastName" /> 
						<form:input path="serviceRequest.primaryContact.workPhone" /> 
						<form:input path="serviceRequest.primaryContact.emailAddress" />
						<!-- This hidden field is for passing alternate phone -->
						<form:input path="serviceRequest.primaryContact.alternatePhone" />
					</span>
					</div>
				   <c:if test="${assetAcceptForm.serviceRequest.secondaryContact.firstName != ''}">
				  <div class="columnInner rounded infoBox shadow" id="addiContact">
	                <h4><spring:message code="requestInfo.heading.secondaryContactForThisRequest"/></h4>
	                				
	                <ul class="form wordBreak">
	                  <li>
	                    <label><spring:message code="requestInfo.label.name"/></label>
	                    <span id="firstNameLabel2">${assetAcceptForm.serviceRequest.secondaryContact.firstName }  ${assetAcceptForm.serviceRequest.secondaryContact.lastName }</span>
	                  </li>
	                  <li>
	                    <label><spring:message code="requestInfo.label.phoneNumber"/></label>
	                    <span id="workPhoneLabel2">${assetAcceptForm.serviceRequest.secondaryContact.workPhone }</span>  
	                  </li>
	                  <li>
	                    <label><spring:message code="requestInfo.label.emailAddress"/></label>
	                    <span id="emailAddressLabel2">${assetAcceptForm.serviceRequest.secondaryContact.emailAddress }</span>
	                  </li>
	                </ul>
	                <span style="display: none">
						<form:input path="serviceRequest.secondaryContact.contactId" /> 
						<form:input path="serviceRequest.secondaryContact.firstName" /> 
						<form:input path="serviceRequest.secondaryContact.lastName" /> 
						<form:input path="serviceRequest.secondaryContact.workPhone" /> 
						<form:input path="serviceRequest.secondaryContact.emailAddress" />
						<!-- This hidden field is for passing alternate phone -->
						<form:input path="serviceRequest.secondaryContact.alternatePhone" />
					</span>
	              </div>
				 </c:if> 
	            </div>
	            <div class="columnsTwo">
	              <div class="infoBox columnInner rounded shadow">
	                <h4><spring:message code="requestInfo.heading.additionalInformationForThisRequest"/></h4>
	                
	                <ul class="form wordBreak">
	                  <li>
	                    <label><spring:message code="requestInfo.label.customerRefereceId"/></label>
	                    <span id="custReferenceId">${assetAcceptForm.serviceRequest.customerReferenceId }</span>
					  </li>
	                  <li>
	                    <label><spring:message code="requestInfo.label.costCentre"/></label>
	                    <span id="costCenter">${assetAcceptForm.serviceRequest.costCenter }</span>
					  </li>
	                  <li>
	                    <label><spring:message code="requestInfo.label.description"/></label>
	                    <span id="specialInstruction">${assetAcceptForm.serviceRequest.addtnlDescription }</span>
					  </li>
					  </ul>
	                <span style="display: none">
	                	<form:input path="serviceRequest.customerReferenceId" /> 
						<form:input path="serviceRequest.costCenter" /> 
						<form:input path="serviceRequest.addtnlDescription" /> 
						<form:input path="cmAreaValue" /> 
						<form:input path="cmArea" /> 
						<form:input path="prevSRNumber" />
						<form:input path="listOfFileTypes" id="listOfFileTypes"/>
						<form:input path="attMaxCount" id="attMaxCount"/>
						<form:input path="requestedFrom" id="requestedFrom"/>
						<form:hidden path="submitToken" />
					</span>
					
				  </div>
	            </div>
	          </div>
	          <hr class="separator" />
			 
			  <div class="portletBlock infoBox">			
					
					<div class="columnsTwo">
					<div class="columnInner">
					<ul class="form wordBreak">
	                  
					  <li>
	                    <label for="rqstSubType"><spring:message code="requestInfo.label.subArea"/></label>
                  <span>${assetAcceptForm.cmAreaValue }</span> </li>
                
					  <li>
                  <label for="notes"><spring:message code="requestInfo.label.notes"/></label>
                  <span id="notes">${assetAcceptForm.notes}</span>
                </li>
	                </ul>
	                </div>
				</div>
			  </div>
			  
	          </form:form>
	          
      
         <c:if test="${fileMap != null}">
         <p class="inlineTitle"><spring:message code="requestInfo.heading.attachments"/></p>				
			 	<div class="columnInner">			  		
					  <div id="fileListDiv" class="wHalf displayGrid columnInnerTable rounded shadow">						
						<table id="fileListTable">
						</table>
						<div class="lineClear"></div>							
					  </div>					  
				  </div>
				  		
			  </c:if>
			  <div class="buttonContainer">
	            <button type="button" id="btn_back" class="button_cancel" onclick="backToEdit();"><spring:message code="button.back"/></button>
				<button type="button" id="btn_cancel" class="button_cancel" onclick="javascript:redirectToHistory(); callOmnitureAction('<%=LexmarkSPOmnitureConstants.ASSETACCEPTANCEREVIEW%>','<%=LexmarkSPOmnitureConstants.REVIEWASSETACCEPTANCECANCEL%>');"><spring:message code="button.cancel"/></button>
	            <button type="button" id="btn_submit" class="button" onclick="submitRequest();"><spring:message code="button.submit"/></button>
	          </div>
	          
	          
        </div>
        <!-- MAIN CONTENT END -->	
		</div>
	
</div>
</body>	

<script type="text/javascript">
window.history.forward();
function backDisable() { 
	
	window.history.forward(); 
}
function submitRequest(){
	
	callOmnitureAction('<%=LexmarkSPOmnitureConstants.ASSETACCEPTANCEREVIEW%>','<%=LexmarkSPOmnitureConstants.REVIEWASSETACCEPTANCESUBMIT%>');
	
	showOverlay();
	var link="<%=createRequestAction %>";
	
	document.getElementById('assetAcceptFormId').action = link;
	
	document.getElementById('assetAcceptFormId').submit();
}
function backToEdit(){
	callOmnitureAction('<%=LexmarkSPOmnitureConstants.ASSETACCEPTANCEREVIEW%>','<%=LexmarkSPOmnitureConstants.REVIEWASSETACCEPTANCEBACK%>');
	showOverlay();
	var link="<%=backToEditAction %>";
		
	document.getElementById('assetAcceptFormId').action = link;
	
	document.getElementById('assetAcceptFormId').submit();
}
function openTemplate(type,fileName){
	callOmnitureAction('<%=LexmarkSPOmnitureConstants.ASSETACCEPTANCEREVIEW%>','<%=LexmarkSPOmnitureConstants.ASSETACCEPTANCEFILEDOWNLOAD%>');
	if(type=='CHLTemplate'){
		window.location="${attachmentURL}&fileType=CHLTemplate";
	}else if(type=='Attachment'){
		window.location="${attachmentURL}&fileType=Attachment&fileName="+fileName;
	}
	
}
 jQuery(document).ready(function() {
	 var currentURL = window.location.href;
	var c=1; 
	
	<c:if test="${fileMap != null}">
	jQuery('#fileListTable').empty();
	var responseText = '<thead><tr>	<th><spring:message code="requestInfo.heading.attachmentFileName"/></th><th class="w120 right"><spring:message code="requestInfo.heading.attachmentFileSizeKiloBytes"/></th></tr></thead>';
		   
	 		responseText = responseText + '<tbody>';
	 		<c:forEach items="${fileMap}" var="entry">
	 		if(c%2==0){
 				responseText = responseText + '<tr class="altRow">';
 			}
 			else{
 				responseText = responseText + '<tr>';
 			}
 			c++;
	 			responseText = responseText + '<td>'+ ' <a href="javascript:void(0);" onclick="openTemplate(\'Attachment\',\'${entry.key}\');"><c:out value="${entry.value.displayFileName}" /></a>' + '</td>';
	 			responseText = responseText + '<td class="right">'+ '<c:out value="${entry.value.fileSize}" />' + '</td>';
	 			
	 			responseText = responseText + '</tr>';
	 			
			</c:forEach>
			responseText = responseText + '</tbody>';
			jQuery('#fileListTable').append(responseText);
	 		

	</c:if>

	<c:if test="${fileMap == null}">
	jQuery('#fileListTable').empty();
	</c:if>
	 
	<c:if test="${exceptionList != null}">
		window.scrollTo(0,0);
	</c:if> 

	textScroll(document.getElementById('specialInstruction'));
	textScroll(document.getElementById('costCenter'));
	textScroll(document.getElementById('notes'));

	jQuery("#processingHint",window.parent.document).css({
		display:"none"
	});
	jQuery("#overlay",window.parent.document).css({
		display:"none" 
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


 if (window.PIE) {
		
     document.getElementById("btn_back").fireEvent("onmove");
     document.getElementById("btn_cancel").fireEvent("onmove");
     document.getElementById("btn_submit").fireEvent("onmove");
     
 }
 
 
 /* Added for CI7 BRD14-02-12 */
ajaxSuccessFunction=function updateRequest(){
		window.location.href="<portlet:actionURL><portlet:param name='action' value='showAcceptancePage'/></portlet:actionURL>&friendlyURL="+populateURL("assetacceptance",[],[]);					
}
/* END */
</script>