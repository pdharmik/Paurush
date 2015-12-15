<%@ taglib prefix="util" uri="/WEB-INF/tld/lexmark-util.tld" %>
<% request.setAttribute("subTabSelected","createNewRequest"); %>
<%@ include file="/WEB-INF/jsp/common/subTab.jsp"%>
<%@page import="java.util.Map"%>
<jsp:include page="/WEB-INF/jsp/common/validationMPS.jsp" />
<jsp:include page="/WEB-INF/jsp/includeGrid.jsp"/>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/mps.css" />
<!--[if IE 7]>
	<style type="text/css"><%@ include file="/WEB-INF/css/mps_ie7.css" %></style>
<![endif]-->
<script type="text/javascript"><%@ include file="/js/commonAddress.js"%></script>
<script type="text/javascript"><%@ include file="../../../../js/portletRedirection.js"%></script>

<portlet:actionURL var="createManageReturnsRequest">
 	<portlet:param name="action" value="createManageReturnsRequest" />
 </portlet:actionURL>
 

  <div id="content-wrapper">
    <div class="journal-content-article">
      <h1><spring:message code="requestInfo.heading.suppliesRequests"/></h1>
      <h2 class="step"><spring:message code="ordermanagement.returns.heading.returnSupplies"/></h2> </div>
      
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
        <h3 class="pageTitle"><spring:message code="ordermanagement.returns.heading.returnSupplies"/> &ndash; <spring:message code="requestInfo.heading.review"/></h3>
        <p class="info banner"><spring:message code="requestInfo.message.reviewMessage"/></p>
        <!-- Exceptions displayed in the below div -->
          	 <c:if test="${exceptionList != null}">
       			<div class="error" id="dispalyExceptions">
	       			<c:forEach items="${exceptionList}" var="entry">
			       		<li><strong>Exception - ${entry}</strong></li>		       		
			     	</c:forEach>
      			</div>
          	</c:if>
        <form:form id="manageReturnsForm" name="manageReturnsForm"  modelAttribute="manageReturnsForm" method="post" action="${createManageReturnsRequest}" enctype="multipart/form-data" >
        <div class="portletBlock">
          <div class="columnsTwo">
             <div class="infoBox columnInner rounded shadow">
	               
	                	<h4><spring:message code="requestInfo.heading.primaryContactForThisRequest"/></h4>				
	                
	                <ul class="form wordBreak">
	                  <li>
	                    <label><spring:message code="requestInfo.label.name"/></label>
	                    <span id="firstNameLabel"> ${manageReturnsForm.serviceRequest.primaryContact.firstName }   ${manageReturnsForm.serviceRequest.primaryContact.lastName } </span>
	                  </li>
	                  <li>
	                    <label><spring:message code="requestInfo.label.phoneNumber"/></label>
	                    <span id="workPhoneLabel">${manageReturnsForm.serviceRequest.primaryContact.workPhone } </span>
	                  </li>
	                  <li>
	                    <label><spring:message code="requestInfo.label.emailAddress"/></label>
	                    <span id="emailAddressLabel">${manageReturnsForm.serviceRequest.primaryContact.emailAddress }</span>  
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
						<form:hidden path="submitToken" />
					</span>
	                
	              </div>
				   <c:if test="${manageReturnsForm.serviceRequest.secondaryContact.firstName != ''}">
				  <div class="infoBox columnInner rounded shadow" id="addiContact">
	                <h4><spring:message code="requestInfo.heading.secondaryContactForThisRequest"/></h4>
	               			
	                <ul class="form wordBreak">
	                  <li>
	                    <label><spring:message code="requestInfo.label.name"/></label>
	                    <span id="firstNameLabel2">${manageReturnsForm.serviceRequest.secondaryContact.firstName }  ${manageReturnsForm.serviceRequest.secondaryContact.lastName }</span>
	                  </li>
	                  <li>
	                    <label><spring:message code="requestInfo.label.phoneNumber"/></label>
	                    <span id="workPhoneLabel2">${manageReturnsForm.serviceRequest.secondaryContact.workPhone }</span>  
	                  </li>
	                  <li>
	                    <label><spring:message code="requestInfo.label.emailAddress"/></label>
	                    <span id="emailAddressLabel2">${manageReturnsForm.serviceRequest.secondaryContact.emailAddress }</span>
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
	                    <span id="custReferenceId">${manageReturnsForm.serviceRequest.customerReferenceId }</span>
					  </li>
	                  <li>
	                    <label><spring:message code="requestInfo.label.costCentre"/></label>
	                    <span id="costCenter">${manageReturnsForm.serviceRequest.costCenter }</span>
					  </li>
	                  <li>
	                    <label><spring:message code="requestInfo.label.description"/></label>
	                    <span id="specialInstruction">${manageReturnsForm.serviceRequest.addtnlDescription }</span>
					  </li>
				</ul>	 
					  
            </div>
          </div>
        </div>
       
        
        <hr class="separator" />
        <div class="portletBlock infoBox">
          <div class="columnsTwo">
          <!-- Changed for CI Defect # 11926 -->
            <div class="infoBox columnInner rounded shadow">
            <h4><spring:message code="requestInfo.heading.details"/></h4>
              <ul class="form wordBreak">
                <li>
                  <label for="rqstSubType"><spring:message code="requestInfo.label.subArea"/></label>
                  <span>${manageReturnsForm.returnsSubAreaValue }</span> </li>
                <li>
                  <label for="notes"><spring:message code="requestInfo.label.notes"/></label>
                  <span id="notes">${manageReturnsForm.notes}</span>
                </li>
               
              </ul>
            </div>
          </div>
          <div class="columnsTwo">
            <div class="infoBox columnInner rounded shadow">
              <h4> <spring:message code="requestInfo.heading.returnAddress"/></h4>
              <ul class="roDisplay">
               <%--Changes for CI Changes BRD13-10-08 STARTS--%>
              <li><div>${manageReturnsForm.returnAddress.storeFrontName}</div>
              <util:addressOutput value="${manageReturnsForm.returnAddress}"></util:addressOutput>
              </li>
<%--                 <li><div>${manageReturnsForm.returnAddress.addressLine1}</div> --%>
<%--                   <div>${manageReturnsForm.returnAddress.addressLine2}</div>           --%>
<%--                   ${manageReturnsForm.returnAddress.addressLine3}    --%>
<%--                   <span>${manageReturnsForm.returnAddress.city}  --%>
<%--                   <c:if test="${not empty manageReturnsForm.returnAddress.state }">,</c:if>  --%>
<%--                   ${manageReturnsForm.returnAddress.state}  --%>
<%--                   <c:if test="${not empty manageReturnsForm.returnAddress.province}">/</c:if> --%>
<%--                   ${manageReturnsForm.returnAddress.province}&nbsp; ${manageReturnsForm.returnAddress.postalCode}</span> --%>
<%--                   <div>${manageReturnsForm.returnAddress.country}</div> --%>
<!--                 </li> -->
                <li><label><spring:message code="requestInfo.addressInfo.label.building"/> </label>
                <span id="building">${manageReturnsForm.returnAddress.physicalLocation1}</span></li>
                <li><label><spring:message code="requestInfo.addressInfo.label.floor"/> </label>
                <span id="floor">${manageReturnsForm.returnAddress.physicalLocation2}</span></li>
                <li><label><spring:message code="requestInfo.addressInfo.label.office"/> </label> 
                <span id="office">${manageReturnsForm.returnAddress.physicalLocation3}</span></li>
                 <%--Changes for CI Changes BRD13-10-08 ENDS--%>
              </ul>
            </div>
          </div>
        </div>
        
        </form:form>
         </div>
        <p class="inlineTitle"><spring:message code="requestInfo.heading.attachments"/></p>
      
         <c:if test="${fileMap != null}">				
			 	<div class="columnInner">			  		
					  <div id="fileListDiv" class="wHalf displayGrid columnInnerTable rounded shadow">						
						<table id="fileListTable">
						</table>
						<div class="lineClear"></div>							
					  </div>					  
				  </div>
				  		
			  </c:if>
       
        
        <div class="buttonContainer">
          <button class="button_cancel" id="backToReturnHomeBtn" onclick="backToReturnHome();"><spring:message code="button.back"/></button>
          <button class="button_cancel"  id="backToHistoryBtn" onclick="javascript:redirectToHistory(); callOmnitureAction('<%=LexmarkSPOmnitureConstants.RETURNSUPPLIESREVIEW%>','<%=LexmarkSPOmnitureConstants.RETURNSUPPLIESREVIEWCANCEL%>');"><spring:message code="button.cancel"/></button>
          <button class="button" id="submitReturnOrderBtn" onclick="jQuery('#manageReturnsForm').submit(); callOmnitureAction('<%=LexmarkSPOmnitureConstants.RETURNSUPPLIESREVIEW%>','<%=LexmarkSPOmnitureConstants.RETURNSUPPLIESREVIEWSUBMIT%>');"><spring:message code="button.submit"/></button>
        </div>
      </div>
      <!-- MAIN CONTENT END --> 
    </div>
  
  
<script type="text/javascript">


function backToReturnHome(){
	callOmnitureAction('<%=LexmarkSPOmnitureConstants.RETURNSUPPLIESREVIEW%>','<%=LexmarkSPOmnitureConstants.RETURNSUPPLIESREVIEWBACK%>');
//alert("Go Back");
	var multipleSRHomeUrl  = '<portlet:actionURL><portlet:param name="action" value="backToManageReturns" /></portlet:actionURL>';
	document.manageReturnsForm.action = multipleSRHomeUrl;
	//document.manageReturnsForm.submit();
	jQuery('#manageReturnsForm').submit();
}

function createReturnOrder(){
		jQuery('#manageReturnsForm').submit();
		//document.manageReturnsForm.submit();
	}


jQuery(document).ready(function() {
	var currentURL = window.location.href;
	if(currentURL.indexOf('/partner-portal') != -1)
	{	
		jQuery('#topnavigation li a').each(function(){
			  if(jQuery(this).attr('href')=="/group/partner-portal/customer-requests")
			  jQuery(this).parent().addClass('selected');
			  });
	}
// 		Change Account Link Hide/Show for CI-7 Defect #12274
		if(currentURL.indexOf('/partner-portal') == -1)
		  {	
			  jQuery('#changeaccount').show();
		  }
		else
			{
			jQuery('#changeaccount').hide();
			}
	var c=1;
	<c:if test="${fileMap != null}">
	jQuery('#fileListTable').empty();
	var responseText = '<thead><tr>	<th><spring:message code="requestInfo.heading.attachmentFileName"/></th><th class="w120 right"><spring:message code="requestInfo.heading.attachmentFileSizeKiloBytes"/></th></tr></thead>';
		   // alert("responseText" + responseText);
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
	 			//responseText = responseText + '<td class="width5"><img id="img_help1" width="18" height="18"  src="<html:imagesPath/>tabbarImgs/default/close.png" style="cursor:help;" title="Delete" alt="Remove" onclick=\'removeFile("${entry.key}");\' /></td>';
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
	textScroll(document.getElementById('notes'));

	jQuery("#processingHint",window.parent.document).css({
		display:"none"
	});
	jQuery("#overlay",window.parent.document).css({
		display:"none" 
	});
	
	
});


function openTemplate(type,fileName){
	callOmnitureAction('<%=LexmarkSPOmnitureConstants.RETURNSUPPLIES%>','<%=LexmarkSPOmnitureConstants.RETURNSUPPLIESREVIEWFILENAME%>');
	if(type=='CHLTemplate'){
		window.location="${attachmentURL}&fileType=CHLTemplate";
	}else if(type=='Attachment'){
		window.location="${attachmentURL}&fileType=Attachment&fileName="+fileName;
	}
	
}


/* Added for CI7 BRD14-02-12 */
ajaxSuccessFunction=function updateRequest(){
	window.location.href="<portlet:actionURL><portlet:param name='action' value='showReturnsPage'/></portlet:actionURL>&friendlyURL="+populateURL("returnsupplies",[],[]);					
}
/* END */
</script>


  