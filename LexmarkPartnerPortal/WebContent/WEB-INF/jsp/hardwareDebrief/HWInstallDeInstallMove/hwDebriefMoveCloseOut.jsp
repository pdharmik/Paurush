<%@ include file="/WEB-INF/jsp/include.jsp"%>
<%@ include file="/WEB-INF/jsp/includeGrid.jsp"%>
<%@ include file="/WEB-INF/jsp/dateUtil.jsp"%>
<%@ include file="/WEB-INF/jsp/includeDatePicker.jsp"%>
<%@page import=" static com.lexmark.constants.LexmarkConstants.TIMEZONE_OFFSET"%>
<jsp:include page="/WEB-INF/jsp/common/validationMPS.jsp"/>
<script type="text/javascript" src="<html:rootPath/>js/jQuery/jquery.qtip-1.0.0-rc3.min.js"></script>
<%-- Constants to differentiate --%>
<c:set var="hwMove" value="${isHwMove}" scope="request"/>
<%--
<c:set var="requestType" value="${srType}" scope="request"/>
 --%>
<c:set var="tzOffset" value="${hardwareDebriefForm.timezoneOffset}" scope="request"/>
<%--ENDS Constants to differentiate --%>
<%--Constant for address fields --%>
<%--
<c:set var="addressFields" value="addressName,storeFrontName,addressLine1,addressLine2,officeNumber,city,state,province,county,district,postalCode,country,addressId,countryISOCode,region" scope="request"/>
<c:forTokens var="fieldName" items="${addressFields}" delims=",">
            		<c:set var="beanFieldPath" value="activity.serviceRequest.asset.installAddress.${fieldName}"/> 
            			<input type="text" name="${beanFieldPath}" value="<jsp:getProperty name="hardwareDebriefFrom.activity.serviceRequest.asset.installAddress" property="${fieldName}"/>"/>
            		</c:forTokens>
 --%>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/mps.css?version=<html:fileCacheVersion/>" />
<!--[if IE 7]>
	<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/mps_ie7.css?version=<html:fileCacheVersion/>" />
<style>.labelie{margin-left:-45%!important;}</style>
<![endif]-->


<style>
.dataGrid td.label{
	width: 50%;
	text-align: right;
}
input[type="text"] {
	width: 180px;
}
.ie7 .button, .ie7 .button_cancel {
behavior: url(/LexmarkServicesPortal/WEB-INF/css/PIE.htc) !important;
}
.ie8 .button, .ie8 .button_cancel {
behavior: url(/LexmarkServicesPortal/WEB-INF/css/PIE.htc) !important;
}
#customerProfilePopup .expand-min1{height:400px!important;overflow:auto!important;}
.expand-min1 ul li{list-style-type: none;};

</style>

<%-- Below URL opens up the Address in popup --%>
<portlet:renderURL  var="showAddressPopup" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>">
    <portlet:param name="action" value="showAddressList" />
</portlet:renderURL>

<%-- Below URL opens up the Contact popup --%>
<portlet:renderURL  var="showContactPopup" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>">
    <portlet:param name="action" value="showContactListPopUp" />
</portlet:renderURL>

<%-- Below URL opens up the chl tree in popup --%>
<portlet:renderURL var="showCHLTreePopUp"
	windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>">
	<portlet:param name="action" value="showCHLTreePopUp"></portlet:param>
</portlet:renderURL>

<portlet:actionURL  var="submitDebrief">
    <portlet:param name="action" value="saveMoveDebrief" />
</portlet:actionURL>


<div id="wrapper">
  <div id="content-wrapper">
    <div class="journal-content-article">
          <h1><spring:message code="requestInfo.hardwareDebreief.moveCloseOut.heading"/> </h1>      
    </div>
   
    <div class="main-wrap">
 
    	<c:if test="${exceptionOccured}">
    		<div class="error"><spring:message code="requestInfo.hardwareDebreief.debriefInstall.errorMsg"/></div>
    	</c:if>
    	<c:if test="${exceptionOccured_Detail}">
    		<div class="error"><spring:message code="requestInfo.hardwareDebreief.view.errorMsg"/></div>
    	</c:if>
    	<c:if test="${not empty savedSuccess and savedSuccess eq 'true'}">
    		<p id="successDiv" class="info banner ok"><spring:message code="requestInfo.hardwareDebreief.moveCloseOut.msg"/></p>
    	</c:if>
    
    <%--This div is for front end validations --%>
    <div style="display: none;" id="errorDiv" class="error"></div>
  
    <form:form id="hardwareDebriefForm" method="post" action="${submitDebrief}" commandName="hardwareDebriefForm">
    <form:input type="hidden" id="<%=TIMEZONE_OFFSET%>" path="<%=TIMEZONE_OFFSET%>"/>
    <form:input type="hidden" path="saveCloseoutFlag"/>
    <form:input type="hidden" path="submitToken"/>
    <form:input type="hidden" path="backURL"/>
	<form:input type="hidden" path="srType" value="${hardwareDebriefForm.srType}"/>
      <c:forEach items="${hardwareDebriefForm.userEnteredActivity.cpFields}" var="cpField" varStatus="counter">
                <input type="hidden" name="userEnteredActivity.cpFields[${counter.index}].fieldName" value="${cpField.fieldName}"/>
		</c:forEach>
      <div class="content">
      <div class="grid-controls"> 
            <div class="expand-min">
              <ul>
                <li class="first"><img class="ui-icon bck-arrow-icon" src="<html:imagesPath/>transparent.png" alt="Back to Service Orders" width="24" height="24" title="Back to Service Orders" /><a href="#" id="returnRequests"><spring:message code="button.return.to.requests"/></a> </li>
              </ul>
            </div>
          </div>
        <!-- MAIN CONTENT GOES HERE -->
       
        <!-- Request Information BLOCK - Start -->
        <div class="portletBlock infoBox rounded shadow">
          <jsp:include page="/WEB-INF/jsp/hardwareDebrief/HWInstallDeInstallMove/commonHwCloseOut/requestInformationCloseOut.jsp"/>
           
        </div>
        <!-- Request Information BLOCK - End -->
        
        <!-- Customer Requested Items BLOCK - Start -->
        <jsp:include page="/WEB-INF/jsp/hardwareDebrief/HWInstallDeInstallMove/commonHwView/commonDeviceDetailsAndCustomerRequestedItems.jsp"/>
        <!-- Customer Requested Items BLOCK - End --> 
        
        <!-- Service Information BLOCK - Start -->
        <jsp:include page="/WEB-INF/jsp/hardwareDebrief/HWInstallDeInstallMove/commonHwView/commonServiceInformation.jsp"/>
        <!-- Service Information BLOCK - End --> 
        
		<!-- Close Out Install Activity BLOCK - Start -->
        <jsp:include page="/WEB-INF/jsp/hardwareDebrief/HWInstallDeInstallMove/commonHwCloseOut/closeOutInstallActivity.jsp"/>
        <!-- Close Out Install Activity BLOCK - End -->
        
        <%-- Added for September 2014 release Page Count BLOCK - Start --%>
		<jsp:include page="/WEB-INF/jsp/hardwareDebrief/HWInstallDeInstallMove/commonHwCloseOut/pageCountInstallCloseOut.jsp"/>        
        <%-- Page Count BLOCK - End --%>
        
        <%--Commented for September 2014 release 
		<!-- Page Count BLOCK - Start -->
        <jsp:include page="/WEB-INF/jsp/hardwareDebrief/HWInstallDeInstallMove/commonHwCloseOut/pageCountCloseOut.jsp"/>  
        <!-- Page Count BLOCK - End --> --%>
        
       <!-- Parts Section Hidden for June release-->
       <jsp:include page="/WEB-INF/jsp/hardwareDebrief/HWInstallDeInstallMove/commonHwCloseOut/commonPartsHiddenView.jsp"/>       
       <!-- Parts Section Hidden for June release END  -->
     
        
        
		<!-- Service Information for this Device BLOCK - Start -->
        <jsp:include page="/WEB-INF/jsp/hardwareDebrief/HWInstallDeInstallMove/commonHwCloseOut/serviceRequestForDeviceCloseOut.jsp"/> 
        </div>
        <!-- Service Information for this Device BLOCK - End -->
         <%-- 
		<!-- Supplies Information for this Device BLOCK - Start -->
        <jsp:include page="/WEB-INF/jsp/hardwareDebrief/HWInstallDeInstallMove/commonHwCloseOut/suppliesInfoForDeviceCloseOut.jsp"/>  
        <!-- Supplies Information for this Device BLOCK - End -->
        --%>
        <!-- Contact Information for this Device BLOCK - Start -->
        <jsp:include page="/WEB-INF/jsp/hardwareDebrief/HWInstallDeInstallMove/commonHwCloseOut/commonContactInformationCloseOut.jsp"/>
        <!-- Contact Information for this Device BLOCK - End -->

        <!-- NOTES BLOCK - Start -->
         <jsp:include page="/WEB-INF/jsp/hardwareDebrief/HWInstallDeInstallMove/commonHwCloseOut/notesCloseOut.jsp"/> 
        <!-- NOTES BLOCK - End -->
         
		<!-- ATTACHMENTS BLOCK - Start -->
        <jsp:include page="/WEB-INF/jsp/hardwareDebrief/HWInstallDeInstallMove/commonHwView/commonAttachmentsView.jsp"/>
        <!-- ATTACHMENTS BLOCK - End --> 
       </form:form> 
        <!-- Add Attachments BLOCK - Start -->
        <jsp:include page="/WEB-INF/jsp/hardwareDebrief/HWInstallDeInstallMove/commonHwCloseOut/addAttachmentCloseOut.jsp"/> 
        <!-- Add Attachments BLOCK - End --> 
        <c:if test="${not exceptionOccured_Detail}">
		<div class="buttonContainer">
		<a id="customerProfileInformation" onclick="popCustomerProfileInformation()" style="cursor:pointer;background: none !important; color:#336699!important;"><spring:message code="requestInfo.hardwareDebrief.customerProfile.heading.title"/></a>
		<c:if test="${hardwareDebriefForm.activity.serviceRequest.serviceRequestStatus != 'completed'}">
          <a class="button button21" style="position:static!important;"  onclick="saveMove()"><spring:message code="button.save_as_draft"/></a>
          
          <button class="button button21" style="position:static!important;" type="button" onclick="confirmInstallCloseOut()"><spring:message code="button.closeout"/></button>
          </c:if>
          <button style="position:static!important;" id="returnRequests_but" class="button_cancel button_cancel21"><spring:message code="button.cancel"/></button>
        </div>
        </c:if>
      </div>
    </div>
  </div>
  <spring:message code="requestInfo.hardwareDebreief.common.closeOut.pageCount.header" var="pageCountTitle"/>
  <%-- This div is used to hide elements from form submission --%>
  <div style="display: none;" id="tempContactDiv"></div>
  <div style="display: none;" id="tempNotesPopupDiv"></div>
  <%--Added for Sept 2014 release --%>
  <div style="display: none;" id="tempPageCountDiv"></div>
  
 
 	<div style="display: none;" id="addressListPopUp" title="Address List">
   	  <%--  <jsp:include page="/WEB-INF/jsp/claims/addressListPopup.jsp" /> --%>
	</div>
  
  <div style="display: none;" id="contactPopUp" title="Contact PopUp">
   	   <%--  <jsp:include page="/WEB-INF/jsp/common/selectContact.jsp" /> --%>
	</div>
  
<div style="display: none;" id="chlPopUp" title="CHL PopUp">
   	  <%--  <jsp:include page="/WEB-INF/jsp/common/displayCHLTree.jsp" /> --%>
	</div>
	
	<div style="display:none;"  >
			<div  id="customerProfilePopup" title="<spring:message code="requestInfo.hardwareDebrief.customerProfile.heading.title"/>" >
			<p class="inlineTitle inlineTitle2"><spring:message code="requestInfo.hardwareDebrief.customerProfile.heading"/></p>
				<div class="expand-min1"></div>
            </div>
	</div>
	<%-- Added for LBS 1.5 View Grid--%>
   <jsp:include page="/WEB-INF/jsp/common/mapPopup.jsp" />
  <%-- ENDS for LBS 1.5 View Grid--%>
<script>
<%-- Below value is for BACK URL--%>
var backURL={
		backURLocation:"${hardwareDebriefForm.backURL}"	 
};
	var popupURLS={
				addressPopupURL:"${showAddressPopup}",
				contactPopupURL:"${showContactPopup}",
				chlPopupURL:"${showCHLTreePopUp}"
			};
	var contact_AddressMessages={
			
			diffContactMsg:"<spring:message code="contact.selectDiffContact" javaScriptEscape="true"/>",
			diffAddressMsg:"<spring:message code="address.selectDiffAddress" javaScriptEscape="true"/>",
			contactMsg:"<spring:message code="contact.selectContact" javaScriptEscape="true"/>",
			addressMsg:"<spring:message code="address.selectAddress" javaScriptEscape="true" />"
		};
	var confirmationMessages={
			ConfirmMsg1:"<spring:message code="requestInfo.hardwareDebreief.common.closeOut.confirmMessage" javaScriptEscape="true"/>",
			ConfirmTitle:"<spring:message code="requestInfo.hardwareDebreief.common.closeOut.confirmMessageTitle" javaScriptEscape="true"/>",
			alertSuccess:"<spring:message code="requestInfo.hardwareDebreief.common.closeOut.alertMessageSuccess" javaScriptEscape="true"/>",
			alertFailure:"<spring:message code="requestInfo.hardwareDebreief.common.closeOut.alertMessageFailure" javaScriptEscape="true"/>",
			ConfirmMsg2:"<spring:message code="requestInfo.hardwareDebreief.common.closeOut.confirmMessageToClose" javaScriptEscape="true"/>"
	};
	<%--
	Commented as per mail from saurabh on 18-11-2013
	jQuery(document).ready(function(){
		jQuery.ajax({
			url:'<portlet:resourceURL id="setTechnicianInformation"/>',
			type:'GET',
			data:{accountId:"123"} 
		});
	});
	--%>
</script>
<script type="text/javascript" src="<html:rootPath/>js/validation.js?version=<html:fileCacheVersion/>"></script>
<script type="text/javascript" src="<html:rootPath/>js/hardwareDebreif.js?version=<html:fileCacheVersion/>"></script>
<script type="text/javascript" src="<html:rootPath/>js/commonAddress.js?version=<html:fileCacheVersion/>"></script>
<jsp:include page="/WEB-INF/jsp/hardwareDebrief/HWInstallDeInstallMove/commonHwCloseOut/commonHardwareValidation.jsp"></jsp:include>	    

<script type="text/javascript">
showToolTipList('helpIconList');
addRequiredFieldToInput();
var mandatoryFieldObj={
		1:["actualStartDate","<spring:message code="requestInfo.hardwareDebreief.common.closeOut.validation.actualStartDate" javaScriptEscape="true"/>"],
		2:["actualEndDate","<spring:message code="requestInfo.hardwareDebreief.common.closeOut.validation.actualEndDate" javaScriptEscape="true"/>"],
		//3:["assetInstallDate","<spring:message code="requestInfo.hardwareDebreief.common.closeOut.validation.assetInstallDate" javaScriptEscape="true"/>"],
		4:["serviceRequestDate",""],
		5:["serviceRequestStatusDropDown","<spring:message code="requestInfo.hardwareDebreief.common.closeOut.validation.statusEmpty" javaScriptEscape="true"/>"],
		6:["technicianNameArea","<spring:message code="requestInfo.hardwareDebreief.common.closeOut.validation.technicianName" javaScriptEscape="true"/>"],
		7:["assetSerialNumber","<spring:message code="requestInfo.hardwareDebreief.common.closeOut.validation.serialNumber" javaScriptEscape="true"/>"],
		8:["userEnteredActivity.serviceRequest.asset.installAddress.addressLine1","<spring:message code="requestInfo.hardwareDebreief.common.closeOut.validation.deviceInstallAddress" javaScriptEscape="true"/>"],
		10:["desc","<spring:message code="requestInfo.hardwareDebreief.common.reject" javaScriptEscape="true"/>"],
		<c:if test="${isLbsAddress==true && islod== true}">
		12:["userEnteredActivity.serviceRequest.asset.installAddress.buildingId","<spring:message code="requestInfo.hardwareDebrief.validation.building" javaScriptEscape="true"/>"],
		13:["userEnteredActivity.serviceRequest.asset.installAddress.floorId","<spring:message code="requestInfo.hardwareDebrief.validation.floor" javaScriptEscape="true"/>"],
		</c:if>
		11:["expectedEndDate",""],
		save:false,
		closeOut:false,
		requestFrom:"move",
		formId:"hardwareDebriefForm"		
};	
<%--Changed  for Sept 2014 release --%>
var formatValidation={
		1:["userEnteredActivity.serviceRequest.asset.ipAddress","<spring:message code="requestInfo.hardwareDebreief.common.closeOut.validation.IpAddress" javaScriptEscape="true"/>",isIPv4V6],
		2:["userEnteredActivity.serviceRequest.asset.macAddress","<spring:message code="requestInfo.hardwareDebreief.common.closeOut.validation.macAddress" javaScriptEscape="true"/>",isMACAddress],
		3:["userEnteredActivity.serviceRequest.asset.portNumber","<spring:message code="requestInfo.hardwareDebreief.common.closeOut.validation.portNumber" javaScriptEscape="true"/>",isDigit],
		4:["userEnteredActivity.serviceRequest.asset.faxPortNumber","<spring:message code="requestInfo.hardwareDebreief.common.closeOut.validation.faxPortNumber" javaScriptEscape="true"/>",isDigit],
		5:["userEnteredActivity.serviceRequest.asset.subnet","<spring:message code="requestInfo.hardwareDebreief.common.closeOut.validation.subnet" javaScriptEscape="true"/>",isIPAddress],
		6:["userEnteredActivity.serviceRequest.asset.gateway","<spring:message code="requestInfo.hardwareDebreief.common.closeOut.validation.gateway" javaScriptEscape="true"/>",isIPAddress],
		7:["userEnteredActivity.serviceRequest.asset.ipV6","<spring:message code="requestInfo.hardwareDebreief.common.closeOut.validation.IpAddress" javaScriptEscape="true"/>",isIPV6],
		12:["userEnteredActivity.serviceRequest.asset.pageCounts[].type","<spring:message code="requestInfo.errorMsg.pageCountType" javaScriptEscape="true" />",checkBlankValue,pageCountslength],
		9:["userEnteredActivity.serviceRequest.asset.pageCounts[].count","<spring:message code="claim.errorMsg.qtyMustBeNumThan0" javaScriptEscape="true" arguments="${pageCountTitle}" />",checkQuantity,pageCountslength],
		10:["userEnteredActivity.serviceRequest.contactInfoForDevice[].deviceContactType","<spring:message code="requestInfo.errorMsg.selectContactType" javaScriptEscape="true" />",checkBlankValue,contactListLength],
		11:["userEnteredActivity.serviceRequest.contactInfoForDevice[].firstName","<spring:message code="requestInfo.errorMsg.selectContact" javaScriptEscape="true" />",checkBlankValue,contactListLength],
		13:["","<spring:message code="requestInfo.hardwareDebreief.consumableContact.validation" javaScriptEscape="true" />",checkBlankValue,contactListLength],
		save:false,
		closeOut:false
};
var mandatoryFieldForSAVE={
		1:["desc","<spring:message code="requestInfo.hardwareDebreief.common.reject" javaScriptEscape="true"/>"],
		5:["serviceRequestStatusDropDown","<spring:message code="requestInfo.hardwareDebreief.common.closeOut.validation.statusEmpty" javaScriptEscape="true"/>"]		
};
		   
	function confirmInstallCloseOut()
		{
		formatValidation.save=false;
		formatValidation.closeOut=true;
		jQuery('#errorDiv').hide();
		if(checkFormats(formatValidation)){
			jQuery('#errorDiv').show();
			jQuery(document).scrollTop(0);
			return;
		}
		
		    jConfirm(confirmationMessages.ConfirmMsg2, confirmationMessages.ConfirmTitle, function(r)
				{
		    	if(r==true){
					jQuery('#errorDiv').hide();
					mandatoryFieldObj["save"]=false;
					mandatoryFieldObj["closeOut"]=true;
					jQuery('#successDiv').hide();
					if(!checkMandatoryFields(mandatoryFieldObj) && !doCustomerProfileValidation(customerProfileFields,inputNamesMapToSiebel,'move') ){
						submitForm(mandatoryFieldObj);
						//window.location.href="services-requests";
					}else{
							jQuery('#errorDiv').show();
							jQuery(document).scrollTop(0);
						}
					
					
				}
			
				
				
			});					
		}
	function saveMove(){
		jQuery('#errorDiv').hide();
		formatValidation.save=true;
		formatValidation.closeOut=false;
		
		mandatoryFieldForSAVE["save"]=true;
		mandatoryFieldForSAVE["closeOut"]=false;
		if(checkMandatoryFields(mandatoryFieldForSAVE)){
			jQuery('#errorDiv').show();
			jQuery(document).scrollTop(0);
			return;
		}
		
		if(checkFormats(formatValidation)){
			jQuery('#errorDiv').show();
			jQuery(document).scrollTop(0);
			return;
		}
		mandatoryFieldObj["save"]=true;
		mandatoryFieldObj["closeOut"]=false;
		
			submitForm(mandatoryFieldObj);
		}

	    
</script>