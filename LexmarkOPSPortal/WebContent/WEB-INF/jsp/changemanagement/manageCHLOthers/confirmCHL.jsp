<%@ taglib prefix="util" uri="/WEB-INF/tld/lexmark-util.tld" %>
<% request.setAttribute("subTabSelected","createNewRequest"); %>
<%@ include file="/WEB-INF/jsp/common/subTab.jsp"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<jsp:include page="/WEB-INF/jsp/includeGrid.jsp"/>
<%@ page import="com.lexmark.services.form.FileUploadForm"%>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/mps.css"/>
<script type="text/javascript"><%@ include file="../../../../js/portletRedirection.js"%></script>
<!--[if IE 7]>
	<style type="text/css"><%@ include file="/WEB-INF/css/mps_ie7.css" %></style>
<![endif]-->

<portlet:actionURL var="createRequestAction">
	<portlet:param name="action" value="createCMRequest" />
</portlet:actionURL>
<portlet:actionURL var='backToEditAction'>
	<portlet:param name='action' value='backToEdit' />
</portlet:actionURL>

<script type="text/javascript">

/*
 * From summary page back is disabled
 */
window.history.forward();
function backDisable() { 
	//alert('in noback');
	window.history.forward(); 
}

function submitRequest(){
	
	showOverlay();
	var link="<%=createRequestAction %>";
	//alert(link);	
	document.getElementById('chlOthersFormId').action = link +"&forward=CHL";
	//alert("hello "+document.getElementById('chlOthersFormId').action);
	document.getElementById('chlOthersFormId').submit();
}

function backToEdit(){
	showOverlay();
	var link="<%=backToEditAction %>";
	//alert(link);	
	document.getElementById('chlOthersFormId').action = link +"&forward=CHL";
	//alert("hello "+document.getElementById('chlOthersFormId').action);
	document.getElementById('chlOthersFormId').submit();
}
function openTemplate(type,fileName){
	if(type=='CHLTemplate'){
		window.location="${attachmentURL}&fileType=CHLTemplate";
	}else if(type=='Attachment'){
		window.location="${attachmentURL}&fileType=Attachment&fileName="+fileName;
	}
	
}

jQuery(document).ready(function() {
//	Change Account Link Hide/Show for CI-7 Defect #12274
	var currentURL = window.location.href;
	if(currentURL.indexOf('/partner-portal') == -1)
	  {	
		  jQuery('#changeaccount').show();
	  }
	else
		{
		jQuery('#changeaccount').hide();
		}
	c=1;
	////var fileCount = '<c:out value="${fileUploadForm.fileCount}" />';
	//alert(fileCount);
	//window.parent.document.getElementById("fileCount").value = fileCount;
	<c:if test="${fileMap != null}">
	jQuery('#fileListTable').empty();
	responseText = '<thead><tr>	<th><spring:message code="requestInfo.heading.attachmentFileName"/></th><th class="w120 right"><spring:message code="requestInfo.heading.attachmentFileSizeKiloBytes"/></th></tr></thead>';
		    //alert("responseText" + responseText);
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
	 			//responseText = responseText + '<td class="width5"><img id="img_help1"  src="<html:imagesPath/>tabbarImgs/default/close.png" style="cursor:help;" title="Delete" alt="Remove" onclick=\'removeFile("${entry.key}");\' /></td>';
	 			responseText = responseText + '</tr>';
	 			//alert("file name " + '<c:out value="${entry.key}" />');
				//alert("file size " + '<c:out value="${entry.value.fileSize}" />');
			</c:forEach>
			responseText = responseText + '</tbody>';
			jQuery('#fileListTable').append(responseText);
	 		//alert("end :::"+ document.getElementById("test").innerHTML);

	</c:if>

	<c:if test="${fileMap == null}">
	jQuery('#fileListTable').empty();
	</c:if>
	
	<c:if test="${exceptionList != null}">
		window.scrollTo(0,0);
	</c:if>


	textScroll(document.getElementById('specialInstruction'));
	textScroll(document.getElementById('costCenter'));
	textScroll(document.getElementById('rqstDesc'));
	
});


/* Added for CI7 BRD14-02-12 */
ajaxSuccessFunction=function updateRequest(){
	window.location.href="<portlet:actionURL><portlet:param name='action' value='showCHLOthersPage'/></portlet:actionURL>&friendlyURL="+populateURL("managechlothers?forward=CHL",[],[]);					
}
/* END */

</script>

<body onload="backDisable();" onpageshow="if (event.persisted) backDisable();" onunload="">
<div id="content-wrapper">
    <div class="journal-content-article">
      <h1><spring:message code="requestInfo.cm.heading.changeRequest"/></h1>
      <h2 class="step"><spring:message code="requestInfo.cm.chl.label.customerHierarchy"/></h2> 
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
				
		<!-- <div class="right-column"> -->
		<!-- MAIN CONTENT BEGIN -->
          <h3 class="pageTitle"><spring:message code="requestInfo.cm.chl.label.customerHierarchy"/> - <spring:message code="requestInfo.heading.review"/></h3>
          	
				<p class="info banner"><spring:message code="requestInfo.message.reviewMessage"/>
		
			   
			   <!-- Exceptions displayed in the below div -->
	          	 <c:if test="${exceptionList != null}">
	       			<div class="info banner err" id="dispalyExceptions">
		       			<c:forEach items="${exceptionList}" var="entry">
				       		<li><strong><spring:message code="requestInfo.error.exception"/> - ${entry}</strong></li>		       		
				     	</c:forEach>
	      			</div>
	          	</c:if>
          	  
		  <form:form id="chlOthersFormId" commandName="chlOthersForm" method="post" action="${createRequestAction}" enctype="multipart/form-data" >
		  <form:hidden path="submitToken" />
		  
	          <div class="portletBlock">
	            <div class="columnsTwo">
	              <div class="infoBox columnInner rounded shadow">
	               
	                	<h4><spring:message code="requestInfo.heading.primaryContactForThisRequest"/></h4>				
	                
	                <ul class="form wordBreak">
	                  <li>
	                    <label><spring:message code="requestInfo.label.name"/></label>
	                    <span id="firstNameLabel"> ${chlOthersForm.serviceRequest.primaryContact.firstName }   ${chlOthersForm.serviceRequest.primaryContact.lastName } </span>
	                  </li>
	                  <li>
	                    <label><spring:message code="requestInfo.label.phoneNumber"/></label>
	                    <span id="workPhoneLabel">${chlOthersForm.serviceRequest.primaryContact.workPhone } </span>
	                  </li>
	                  <li>
	                    <label><spring:message code="requestInfo.label.emailAddress"/></label>
	                    <span id="emailAddressLabel">${chlOthersForm.serviceRequest.primaryContact.emailAddress }</span>  
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
				   <c:if test="${chlOthersForm.serviceRequest.secondaryContact.firstName != ''}">
				  <div class="infoBox columnInner rounded shadow" id="addiContact">
	                <h4><spring:message code="requestInfo.heading.secondaryContactForThisRequest"/></h4>
	               			
	                <ul class="form wordBreak">
	                  <li>
	                    <label><spring:message code="requestInfo.label.name"/></label>
	                    <span id="firstNameLabel2">${chlOthersForm.serviceRequest.secondaryContact.firstName }  ${chlOthersForm.serviceRequest.secondaryContact.lastName }</span>
	                  </li>
	                  <li>
	                    <label><spring:message code="requestInfo.label.phoneNumber"/></label>
	                    <span id="workPhoneLabel2">${chlOthersForm.serviceRequest.secondaryContact.workPhone }</span>  
	                  </li>
	                  <li>
	                    <label><spring:message code="requestInfo.label.emailAddress"/></label>
	                    <span id="emailAddressLabel2">${chlOthersForm.serviceRequest.secondaryContact.emailAddress }</span>
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
	                    <span id="custReferenceId">${chlOthersForm.serviceRequest.customerReferenceId }</span>
					  </li>
	                  <li>
	                    <label><spring:message code="requestInfo.label.costCentre"/></label>
	                    <span id="costCenter">${chlOthersForm.serviceRequest.costCenter }</span>
					  </li>
	                  <li>
	                    <label><spring:message code="requestInfo.label.description"/></label>
	                    <span id="specialInstruction">${chlOthersForm.serviceRequest.addtnlDescription }</span>
					  </li>
					  <div class="lineClear"/>
					  <li><label for="date1"><spring:message code="requestInfo.label.dateOfChange"/></label>
						 <%-- <span id=""> ${chlOthersForm.serviceRequest.requestedEffectiveDate} </span> --%>
						 <span id=""><%-- <fmt:formatDate value="${chlOthersForm.serviceRequest.requestedEffectiveDate}" pattern="MM/dd/yyyy"/> 
						 <util:dateFormat value="${chlOthersForm.serviceRequest.requestedEffectiveDate}" timezoneOffset="${timezoneOffset}">
						 </util:dateFormat>
						 --%>
						 <util:dateFormat value="${chlOthersForm.serviceRequest.requestedEffectiveDate}">
						 </util:dateFormat>
						 </span>
						
					  </li>
	                </ul>
	                <span style="display: none">
	                	<form:input path="serviceRequest.customerReferenceId" /> 
						<form:input path="serviceRequest.costCenter" /> 
						<form:input path="serviceRequest.addtnlDescription" /> 
						<form:input path="cmAreaValue" /> 
						<form:input path="cmArea" /> 
						<form:input path="serviceRequest.requestedEffectiveDate" />
						<form:input path="notesOrComments" />
						<form:input path="prevSRNumber" /> 
						<form:input path="listOfFileTypes" id="listOfFileTypes"/>
						<form:input path="chlTempMaxCount" id="chlTempMaxCount"/> 
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
	                    <label><spring:message code="requestInfo.label.subArea"/></label>
	                    
	                    <span id="cmArea">${chlOthersForm.cmAreaValue }</span>
	                   
					  </li>
					  <li>
	                    <label for="rqstDesc"><spring:message code="requestInfo.label.notes"/></label>
	                    <span id="rqstDesc">${chlOthersForm.notesOrComments }</span>
					  </li>
	                </ul>
	                </div>
				</div>
			  </div>
	          </form:form>
	          <c:if test="${fileMap != null}">
			  
				<p class="info"><spring:message code="requestInfo.heading.attachments"/></p>
				
			 	<div class="columnInner">
			  		
					  <div  id="fileListDiv" class="wHalf displayGrid columnInnerTable rounded shadow">
						
						<table id="fileListTable">
						</table>

						<div class="lineClear"></div>							
					  </div>
					  
				  </div>
				
					
			  
			  
			  </c:if>
			  
			  
			  <div class="buttonContainer">
	            <button type="button" class="button_cancel" onclick="backToEdit();"><spring:message code="button.back"/></button>
				<button type="button" class="button_cancel ie7_fix_margin" onclick="javascript:redirectToHistory('others');"><spring:message code="button.cancel"/></button>
	            <button type="button" class="button" onclick="submitRequest();"><spring:message code="button.submit"/></button>
	          </div>
	          
	          
        </div>
        <!-- MAIN CONTENT END -->	
		</div>
	
</div>
</body>