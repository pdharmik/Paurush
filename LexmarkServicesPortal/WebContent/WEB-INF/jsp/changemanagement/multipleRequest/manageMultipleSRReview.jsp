<%@ taglib prefix="util" uri="/WEB-INF/tld/lexmark-util.tld" %>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<jsp:include page="/WEB-INF/jsp/includeGrid.jsp"/>
<% request.setAttribute("subTabSelected","createNewRequest"); %>
<%@ include file="/WEB-INF/jsp/common/subTab.jsp"%>
<script type="text/javascript"><%@ include file="../../../../js/portletRedirection.js"%></script>
	<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/mps.css"/>
<!--[if IE 7]>
	<style type="text/css"><%@ include file="/WEB-INF/css/mps_ie7.css" %></style> 
<![endif]-->

<portlet:actionURL var="submitTemplateSRURL">
 	<portlet:param name="action" value="submitTemplateSRForm" />
 </portlet:actionURL>
 
  
  <div id="content-wrapper">
    <div class="journal-content-article">
      <h1><spring:message code="requestInfo.cm.heading.changeRequest"/></h1>  <h2 class="step">${requestSubType} </h2>
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
          <h3 class="pageTitle"> ${requestType}  <spring:message code="requestInfo.heading.multiple"/> ${requestSubType} &ndash; <spring:message code="requestInfo.heading.review"/></h3>
          <p class="info banner"><spring:message code="requestInfo.message.reviewMessage"/></p>
		<form:form id="templateRequestReviewForm" name="templateRequestReviewForm"  modelAttribute="templateRequestForm" method="post" action="${submitTemplateSRURL}">

          <div class="portletBlock">
            <div class="columnsTwo">
              <div class="infoBox columnInner rounded shadow">
                <h4>
                	<spring:message code="requestInfo.heading.primaryContactForThisRequest"/>
                </h4>
                <ul class="form wordBreak">
                  <li>
                    <label><spring:message code="requestInfo.label.name"/></label>
                    <span id="firstLastName1"> ${templateRequestForm.serviceRequest.primaryContact.firstName } &nbsp; ${templateRequestForm.serviceRequest.primaryContact.lastName }</span>
                  </li>
                  <li>
                    <label><spring:message code="requestInfo.label.phoneNumber"/></label>
                     <span id="phoneNo1">${templateRequestForm.serviceRequest.primaryContact.workPhone } </span>
                  </li>
                  <li>
                    <label><spring:message code="requestInfo.label.emailAddress"/></label>
                      <span id="emailAddress1">${templateRequestForm.serviceRequest.primaryContact.emailAddress }</span>
                  </li>
                </ul>
              </div>
			<!--   <div class="columnInner rounded infoBox"> -->
			   <div class="infoBox columnInner rounded shadow" id="addiContact">
                <h4>
                	<spring:message code="requestInfo.heading.secondaryContactForThisRequest"/>
                </h4>
                <ul class="form wordBreak">
                  <li>
                    <label><spring:message code="requestInfo.label.name"/></label>
                     <span id="firstLastName2">${templateRequestForm.serviceRequest.secondaryContact.firstName } &nbsp; ${templateRequestForm.serviceRequest.secondaryContact.lastName }
                     </span> </li>
                  <li>
                    <label><spring:message code="requestInfo.label.phoneNumber"/></label>
                    <span id="phoneNo2"> ${templateRequestForm.serviceRequest.secondaryContact.workPhone }</span>
                  </li>
                  <li>
                    <label><spring:message code="requestInfo.label.emailAddress"/></label>
                   <span id="emailAddress2"> ${templateRequestForm.serviceRequest.secondaryContact.emailAddress } </span>
                   </li>
                </ul>
              </div>
            </div>
            <div class="columnsTwo">
              <div class="infoBox columnInner rounded shadow">
                <h4>
                	<spring:message code="requestInfo.heading.additionalInformationForThisRequest"/>
               	</h4>
                <ul class="form wordBreak">
                  <li>
                    <label for="refId"><spring:message code="requestInfo.label.customerRefereceId"/></label>
                    <span id="customerRefId"> ${templateRequestForm.serviceRequest.customerReferenceId}</span>
					
				  </li>
                  <li>
                    <label for="costCntr"><spring:message code="requestInfo.label.costCentre"/></label>
                    <span id="costCenter">${templateRequestForm.serviceRequest.costCenter} </span>
					
				  </li>
                  <li>
                    <label for="desc"><spring:message code="requestInfo.label.description"/></label>
                    <span id="description">${templateRequestForm.serviceRequest.addtnlDescription} </span>
					
				  </li>
				  <li><label for="date1"><spring:message code="requestInfo.label.dateOfChange"/></label>
					<span id="reqEffectiveDate"><util:dateFormat value="${templateRequestForm.serviceRequest.requestedEffectiveDate}">
					</util:dateFormat>
					</span>
					</li>
                </ul>
			  </div>
            </div>
          </div>
          
          
	                	<form:hidden path="serviceRequest.requestor.contactId" /> 
						<form:hidden path="serviceRequest.requestor.firstName" /> 
						<form:hidden path="serviceRequest.requestor.lastName" /> 
						<form:hidden path="serviceRequest.requestor.workPhone" /> 
						<form:hidden path="serviceRequest.requestor.emailAddress" />
						
						<form:hidden path="serviceRequest.primaryContact.contactId" /> 
						<form:hidden path="serviceRequest.primaryContact.firstName" /> 
						<form:hidden path="serviceRequest.primaryContact.lastName" /> 
						<form:hidden path="serviceRequest.primaryContact.workPhone" /> 
						<form:hidden path="serviceRequest.primaryContact.emailAddress" />
						<form:hidden path="serviceRequest.primaryContact.alternatePhone" />
						
						<form:hidden path="serviceRequest.secondaryContact.contactId" /> 
						<form:hidden path="serviceRequest.secondaryContact.firstName" /> 
						<form:hidden path="serviceRequest.secondaryContact.lastName" /> 
						<form:hidden path="serviceRequest.secondaryContact.workPhone" /> 
						<form:hidden path="serviceRequest.secondaryContact.emailAddress" />
						<form:hidden path="serviceRequest.secondaryContact.alternatePhone" />
						
						<form:hidden path="serviceRequest.customerReferenceId" /> 
						<form:hidden path="serviceRequest.costCenter" /> 
						<form:hidden path="serviceRequest.addtnlDescription" /> 
						<form:hidden path="serviceRequest.requestedEffectiveDate" />
						
						<form:hidden path="prevSRNumber" />
						<form:hidden path="requestType"/>
						<form:hidden path="requestSubType"/>
						<form:hidden path="fileName"/>
						<form:hidden path="fileSize"/>
						<form:hidden path="submitToken" />
						
					
		  </form:form>
		  
		  
		<hr class="separator" />
        <p class="info"><spring:message code="requestInfo.heading.requestDetails"/></p>
        <div class="columnInner">
        <div class="wHalf displayGrid columnInnerTable rounded shadow">
          <table>
            <thead>
              <tr>
                <th><spring:message code="requestInfo.heading.attachmentFileName"/></th>
                <th class="w120 right"><spring:message code="requestInfo.heading.attachmentFileSizeKiloBytes"/></th>
              </tr>
            </thead>
            <tbody>
              <tr>
                <td><a href="javascript:downloadTemplate();"><span id="filename">${templateRequestForm.fileName} </span></a></td>
                <td class="right"><label id="filesize">${templateRequestForm.fileSize} </label></td>
              </tr>
            </tbody>
          </table>
        </div>
		 </div>
		
          <div class="buttonContainer">
		  <button class="button_cancel" onclick="backToMultipleSRHome();"><spring:message code="button.back"/></button>
            <button class="button_cancel ie7_fix_margin" onclick="javascript:redirectToHistory();"><spring:message code="button.cancel"/></button>
            <button class="button" onclick="jQuery('#templateRequestReviewForm').submit();"><spring:message code="button.submit"/></button>
          </div>
  
        <!-- MAIN CONTENT END --> 
      </div>
    </div>
  </div>


<script type="text/javascript">

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

	//Hide the additional contact, if not selected in previous page
	if ("${templateRequestForm.serviceRequest.secondaryContact.firstName }"=="" || "${templateRequestForm.serviceRequest.secondaryContact.firstName }"==null) 
	{
		document.getElementById('addiContact').style.display = "none";
	}
	
	textScroll(document.getElementById('description'));
	textScroll(document.getElementById('costCenter'));
	  
});

function downloadTemplate(){
	//var url = "${downloadTemplateUrl}" ;
	var url = '<portlet:resourceURL id="downloadTemplate"/>';
	window.location=url+"&requestType=" + "${requestType}"+"&requestSubType="+"${requestSubType}"+"&attachedFileName="+"${uploadedFileName}";
}

function backToMultipleSRHome(){
//alert("Go Back");
	var multipleSRHomeUrl  = '<portlet:actionURL><portlet:param name="action" value="backToManageTemplateSRPage" />	</portlet:actionURL>';
	document.templateRequestReviewForm.action = multipleSRHomeUrl;
	document.templateRequestReviewForm.submit();
}


/* Added for CI7 BRD14-02-12 */
ajaxSuccessFunction=function updateRequest(){
	<c:if test="${requestType == 'Add' && requestSubType == 'Addresses'}">
	window.location.href="<portlet:actionURL><portlet:param name='action' value='showAddressPage'/></portlet:actionURL>&friendlyURL="+populateURL("manageaddresses",[],[]);
	</c:if>
	<c:if test="${requestType == 'Change' && requestSubType == 'Addresses'}">
	window.location.href="<portlet:actionURL><portlet:param name='action' value='showAddressPage'/></portlet:actionURL>&friendlyURL="+populateURL("manageaddresses",[],[]);
	</c:if>
	<c:if test="${requestType == 'Remove' && requestSubType == 'Addresses'}">
	window.location.href="<portlet:actionURL><portlet:param name='action' value='showAddressPage'/></portlet:actionURL>&friendlyURL="+populateURL("manageaddresses",[],[]);
	</c:if>
	<c:if test="${requestType == 'Add' && requestSubType == 'Contacts'}">
	window.location.href="<portlet:actionURL><portlet:param name='action' value='showContactPage'/></portlet:actionURL>&friendlyURL="+populateURL("managecontacts",[],[]);
	</c:if>
	<c:if test="${requestType == 'Change' && requestSubType == 'Contacts'}">
	window.location.href="<portlet:actionURL><portlet:param name='action' value='showContactPage'/></portlet:actionURL>&friendlyURL="+populateURL("managecontacts",[],[]);
	</c:if>
	<c:if test="${requestType == 'Remove' && requestSubType == 'Contacts'}">
	window.location.href="<portlet:actionURL><portlet:param name='action' value='showContactPage'/></portlet:actionURL>&friendlyURL="+populateURL("managecontacts",[],[]);
	</c:if>
	<c:if test="${requestType == 'Add' && requestSubType == 'Assets'}">
	window.location.href="<portlet:actionURL><portlet:param name='action' value='showAssetPage'/></portlet:actionURL>&friendlyURL="+populateURL("manageassets",[],[]);
	</c:if>
	<c:if test="${requestType == 'Change' && requestSubType == 'Assets'}">
	window.location.href="<portlet:actionURL><portlet:param name='action' value='showAssetPage'/></portlet:actionURL>&friendlyURL="+populateURL("manageassets",[],[]);
	</c:if>
	<c:if test="${requestType == 'Decommission' && requestSubType == 'Assets'}">
	window.location.href="<portlet:actionURL><portlet:param name='action' value='showAssetPage'/></portlet:actionURL>&friendlyURL="+populateURL("manageassets",[],[]);
	</c:if>
	<c:if test="${requestType == 'Swap' && requestSubType == 'Assets'}">
	window.location.href="<portlet:actionURL><portlet:param name='action' value='showAssetPage'/></portlet:actionURL>&friendlyURL="+populateURL("manageassets",[],[]);
	</c:if>
};
/* END */

</script>


