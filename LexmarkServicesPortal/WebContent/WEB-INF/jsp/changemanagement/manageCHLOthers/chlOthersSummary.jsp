<%@ taglib prefix="util" uri="/WEB-INF/tld/lexmark-util.tld" %>
<% request.setAttribute("subTabSelected","createNewRequest"); %>
<%@ include file="/WEB-INF/jsp/common/subTab.jsp"%>
<%@ page import="com.lexmark.services.form.FileUploadForm"%>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/mps.css"/>

<!--[if IE 7]>
	<style type="text/css"><%@ include file="/WEB-INF/css/mps_ie7.css" %></style>
<![endif]-->

<script type="text/javascript">
jQuery(document).ready(function() {
	
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
	 			responseText = responseText + '<td>'+ '<c:out value="${entry.value.displayFileName}" />' + '</td>';
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
	
	textScroll(document.getElementById('specialInstruction'));
	textScroll(document.getElementById('costCenter'));

	var flowType = '${typeOfFlow}';
	if (flowType == 'CHL') {
		textScroll(document.getElementById('rqstDesc1'));
	} else if(flowType == 'Others') {
		textScroll(document.getElementById('rqstDesc'));
	}
	
});


</script>

<div id="content-wrapper">
    <div class="journal-content-article" id="chlOthersHeader">
      <h1><span id="changeRequest"><spring:message code="requestInfo.cm.heading.changeRequest"/></span></h1>
      <c:if test="${typeOfFlow =='CHL'}"><h2 class="step" id="typeofpage"><spring:message code="requestInfo.cm.chl.label.customerHierarchy"/></h2> </c:if>
      <c:if test="${typeOfFlow =='Others'}"><h2 class="step" id="typeofpage"><spring:message code="requestInfo.cm.others.heading.others"/></h2> </c:if>
    </div>
    
    <!-- Added for CI7 BRD14-02-12 -->
    <div id="accNameAgreeName"><h3><strong>
    <%@ include file="/WEB-INF/jsp/common/commonAccountAgreementNameInfo.jsp"%>
      </strong></h3></div>
      <!-- END -->
      
<div class="main-wrap">
	<div class="content">
				
		<!-- <div class="right-column"> -->
		<!-- MAIN CONTENT BEGIN -->
          <h3 class="pageTitle"><spring:message code="requestInfo.heading.requestConfirmation"/></h3>
          <div class="utilities printPage floatR">
            <ul>
              <li class="first"><a href="javascript:email();"><img src="<html:imagesPath/>transparent.png" class="ui_icon_sprite email-icon" alt="<spring:message code="requestInfo.link.emailThisPage"/>" title="<spring:message code="requestInfo.link.emailThisPage"/>" ></a></li>
              <li><a href="javascript:print();"><img src="/LexmarkServicesPortal/images/transparent.png" class="ui_icon_sprite print-icon" alt="<spring:message code="requestInfo.link.printThisPage"/>" title="<spring:message code="requestInfo.link.printThisPage"/>" height="27" width="27"></a></li>
            </ul>
          </div>
          <div id="emailPrint">
          	<div class="columnInner" id="headerMesg1">          
          <c:if test="${error ne null && error != ''}">
		  <p class="info banner err"><span>${error}</span></p>
		  </c:if>
		  <c:if test="${chlOthersForm.serviceRequest.serviceRequestNumber != null && chlOthersForm.serviceRequest.serviceRequestNumber != ''}">
		  <p class="info banner ok"><spring:message code="requestInfo.message.confirmMessage1"/>
		  <span id="reqNumber">${chlOthersForm.serviceRequest.serviceRequestNumber}</span>	
		  <spring:message code="requestInfo.message.confirmMessage2"/></p>
		  </c:if>
		  <%-- <p class="info banner ok"><spring:message code="requestInfo.message.confirmMessage1"/> 
			<span id="reqNumber">${chlOthersForm.serviceRequest.serviceRequestNumber}</span> 
			<spring:message code="requestInfo.message.confirmMessage2"/>
			</p> --%>
<%-- 				<p class="info banner ok"><spring:message code="requestInfo.message.allowTimeForFulfillment"/> </p> --%>
			   </div>   
			         	  
		  <form:form id="chlOthersFormId" commandName="chlOthersForm" method="post" action="${createRequestAction}" enctype="multipart/form-data" >
		  
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
					</span>
	                
	              </div>
				  <c:if test="${chlOthersForm.serviceRequest.secondaryContact != null}">
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
						 <%-- <span id="">${chlOthersForm.serviceRequest.requestedEffectiveDate }</span> --%>
						<span id="reqDate"><%-- <fmt:formatDate value="${chlOthersForm.serviceRequest.requestedEffectiveDate}" pattern="MM/dd/yyyy"/> --%>
						<util:dateFormat value="${chlOthersForm.serviceRequest.requestedEffectiveDate}">
						</util:dateFormat>
						 </span>
					  </li>
	                </ul>
				  </div>
	            </div>
	          </div>
			  
			 <hr class="separator" />
			<div class="portletBlock infoBox">			
					
					<div class="columnsTwo">
					  <div class="columnInner">
					<ul class="form wordBreak">
					<c:if test="${typeOfFlow =='CHL'}">
					  <li>
	                    <label><spring:message code="requestInfo.label.subArea"/></label>
	                    <span id="cmSubArea1">${chlOthersForm.cmAreaValue }</span>
	                   
					  </li>
					  <li>
	                    <label for="rqstDesc1"><spring:message code="requestInfo.label.notes"/></label>
	                    <span id="rqstDesc1">${chlOthersForm.notesOrComments }</span>
					  </li>
					</c:if>
					<c:if test="${typeOfFlow =='Others'}">
	                  <li>
	                    <label><spring:message code="requestInfo.label.area"/></label>
	                    <span id="cmArea">${chlOthersForm.cmAreaValue }</span>
	                   
					  </li>
					  <c:if test="${chlOthersForm.cmSubAreaValue != ''}">
					  <li>
	                    <label for="rqstSubType"><spring:message code="requestInfo.label.subArea"/></label>
	                    <span id="cmSubArea">${chlOthersForm.cmSubAreaValue }</span>
	                    						
					  </li>
					  </c:if>
	                  <li>
	                    <label for="rqstDesc"><spring:message code="requestInfo.label.notes"/></label>
	                    <span id="rqstDesc">${chlOthersForm.notesOrComments }</span>
					  </li>
					</c:if>
	                </ul>
	                </div>
				</div>
			  </div>
	          </form:form>
	          <c:if test="${fileMap != null}">
			
				<p class="info"><spring:message code="requestInfo.heading.attachments"/></p>
				
			 <div class="columnInner">
			  	
					  <div id="fileListDiv" class="wHalf displayGrid columnInnerTable rounded shadow">
						
						<table id="fileListTable">
						</table>

						<div class="lineClear"></div>							
					  </div>
						  
				  </div>
				
			
			  
			 
			  </c:if>
			  
	          </div>
	          
        </div>
        
        <!-- MAIN CONTENT END -->	
		</div>
		 
	</div>

		 <div class="filterLinks floatR">
	          <ul>
	            <li class="first"><a href="javascript:email();"><img class="ui_icon_sprite email-icon" src="<html:imagesPath/>transparent.png" height="23" width="27"/><spring:message code="requestInfo.link.emailThisPage"/></a></li>
	            <li><a href="javascript:print();"><img class="ui_icon_sprite print-icon" src="<html:imagesPath/>transparent.png" height="27" width="27"/><spring:message code="requestInfo.link.printThisPage"/></a></li>
	          </ul>
          </div>
<script type="text/javascript">
function print() {
	
		
		callOmnitureAction('Others', 'Others Summary');
		url ="<portlet:renderURL windowState='<%= LiferayWindowState.POP_UP.toString() %>'>" +
			"<portlet:param name='action' value='othersSummary' />" +
			"</portlet:renderURL>";
	
	    var iWidth=1000;
	    var iHeight=600;
	    var iTop = (window.screen.availHeight-30-iHeight)/2;
	    var iLeft = (window.screen.availWidth-10-iWidth)/2;
	    window.open(url,
	    		'historyList',
			    'height='+iHeight+
			    ',innerHeight='+
			    iHeight+',width='+
			    iWidth+',innerWidth='+
			    iWidth+',top='+iTop+
			    ',left='+iLeft+
			    ',status=no,toolbar=yes,menubar=no,location=no,resizable=yes,scrollbars=yes,titlebar=no');
	};
	
function email() {
	
		
		callOmnitureAction('Others', 'Others Summary');
		url ="<portlet:renderURL windowState='<%= LiferayWindowState.POP_UP.toString() %>'>" +
			"<portlet:param name='action' value='othersEmail' />" +
			"</portlet:renderURL>" + "&typeOfFlow=" + "${typeOfFlow}";
		
	
	    var iWidth=1000;
	    var iHeight=600;
	    var iTop = (window.screen.availHeight-30-iHeight)/2;
	    var iLeft = (window.screen.availWidth-10-iWidth)/2;
	    window.open(url,
	    		'historyList',
			    'height='+iHeight+
			    ',innerHeight='+
			    iHeight+',width='+
			    iWidth+',innerWidth='+
			    iWidth+',top='+iTop+
			    ',left='+iLeft+
			    ',status=no,toolbar=yes,menubar=no,location=no,resizable=yes,scrollbars=yes,titlebar=no');
	};

</script>