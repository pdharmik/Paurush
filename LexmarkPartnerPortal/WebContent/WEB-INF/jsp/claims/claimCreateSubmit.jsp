<%@ include file="/WEB-INF/jsp/include.jsp"%>
<%@ include file="/WEB-INF/jsp/includeGrid.jsp"%>
<%@ include file="/WEB-INF/jsp/dateUtil.jsp"%>
<!-- include changed to jsp:include for CI BRD 13-10-08 -->
<jsp:include page="/WEB-INF/jsp/includeDatePicker.jsp"></jsp:include>
<jsp:include page="/WEB-INF/jsp/noBackToThisPage.jsp"></jsp:include>
<%--CI-7 Changes Added for address cleansing --%>
<%@page import ="static com.lexmark.util.ChangeMgmtConstant.ADDRESSCLEANSEFIELDS_OUTPUT"%>
<style type="text/css"><%@ include file="/WEB-INF/css/ci6.css" %></style>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/mps.css?version=<html:fileCacheVersion/>" />
<style type="text/css"><%@ include file="/css/jquery-ui.css"%></style>
<!--[if IE 7]>
	<style type="text/css"><%@ include file="/WEB-INF/css/mps_ie7.css" %></style>
<![endif]-->

<script language="JavaScript" type="text/javascript" src="<html:rootPath/>js/jquery.validate.js"></script>

<!-- Added for CI BRD13-08-10 -->
<script type="text/javascript">
<%@ include file="/js/commonAsset.js"%>
</script> 


<portlet:renderURL  var="requestsListViewUrl">
</portlet:renderURL>

<portlet:renderURL  var="showAdressPopUrl" windowState='<%= LiferayWindowState.POP_UP.toString() %>'>
    <portlet:param name="action" value="showSelectAddressPage" />
    <portlet:param name="partnerAddressListURL" value="${claimRequestConfirmationForm.partnerAddressListURL}" />
</portlet:renderURL>
<portlet:renderURL  var="updateNoteUrl" windowState='<%= LiferayWindowState.POP_UP.toString() %>'>
    <portlet:param name="action" value="showUpdateNotePopup" />
</portlet:renderURL>

<portlet:renderURL  var="requestListUrl">
    <portlet:param name="action" value="" />
</portlet:renderURL>

<portlet:actionURL var="createClaimRequest">
	<portlet:param name="action" value="createClaimRequest"/>
</portlet:actionURL>

<portlet:actionURL var="addAttachmentsCreate">
	<portlet:param name="action" value="addAttachmentsCreateCreateClaim"/>
</portlet:actionURL>

<portlet:actionURL var="removeAttachmentURL">
	<portlet:param name="action" value="removeAttachmentActionClaimCreate"/>
</portlet:actionURL>

<portlet:renderURL  var="showAddressPopup" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>">
    <portlet:param name="action" value="showSelectAddressPage" />
</portlet:renderURL>

<c:set var="assetId" value="${claimRequestConfirmationForm.asset.assetId}"/>
<c:set var="serviceRequestId" value="${claimRequestConfirmationForm.activity.serviceRequest.id}"/>

<%--Added for CI--%>
<%-- Below div opens up as popup with address--%>
<div id="dialogAddress"></div>


<script type="text/javascript">
var dialog;
var exchangeflag="${claimRequestConfirmationForm.activity.exchangeFlag}";
var additionalpaymentflag="${claimRequestConfirmationForm.showAdditionalPaymentRequestListFlag}";//AIR65413
function trim(strValue){
	var trimValue = strValue.replace(/\s/g,"");
	return trimValue;	
}


window.onload = function(){
	callOmnitureCreateClaimAction();
};
</script>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/in-styles.css?version=<html:fileCacheVersion/>" />
	<div class="main-wrap" >

			<form:form id="createClaimRequestForm" name="createClaimRequestForm" commandName="claimRequestConfirmationForm" method="post" action="${createClaimRequest}" onkeydown="if(event.keyCode==13){return event.srcElement.type == 'textarea';}">
			<!-- Below line added for CI BRD13-10-01 -->
			<form:hidden path="pageSubmitType" id="pageSubmitType"/>
			<form:hidden path="timezoneOffset"/>

	    <div class="content">	
			<div class="grid-controls">
				<div class="utilities">
				</div><!-- utilities -->
				<div class="expand-min">
					<A id="headerImageButton" class="button header-image-button" href="${requestsListViewUrl}" >&lt;&lt; <spring:message code="link.return.to.requests"/></A></div>
				<div class="clear-both"></div>
				<br>
			</div>
			
			<!-- Below hidden input parameters added for Address Cleansing -->
		           					<!-- Hidden fields to bind the address data with form -->
						         <span style="display: none">
									<form:input id="pickupAddressId" path="activity.shipToAddress.addressId" />
									<form:input id="pickupStoreFrontName" path="activity.shipToAddress.storeFrontName" />
							     	<form:input id="pickupAddressLine1" path="activity.shipToAddress.addressLine1" /> 
							        <form:input id="pickupAddressLine2" path="activity.shipToAddress.addressLine2" />
							        <form:input id="pickupAddressCity" path="activity.shipToAddress.city" />
							        <form:input id="pickupAddressState" path="activity.shipToAddress.state" />
							        <form:input id="pickupAddressProvince" path="activity.shipToAddress.province" />
							        <form:input id="pickupAddressPostCode" path="activity.shipToAddress.postalCode" />
							        <form:input id="pickupAddressCountry" path="activity.shipToAddress.country" />

									<!-- Below Fields for Cleansing address Don't change the
									input id's as it is related to ChangeMgmtConstant.ADDRESSCLEANSEFIELDS_OUTPUT 
									 -->
									
									<form:input id="pickupAddresscounty" path="activity.shipToAddress.county" />
							        <form:input id="pickupAddressofficeNumber" path="activity.shipToAddress.officeNumber" />
							        <form:input id="pickupAddressstateCode" path="activity.shipToAddress.stateCode" />
							        <form:input id="pickupAddressstateFullName" path="activity.shipToAddress.stateFullName" />
							        <form:input id="pickupAddressdistrict" path="activity.shipToAddress.district" />
							        <form:input id="pickupAddressregion" path="activity.shipToAddress.region" />
							        <form:input id="pickupAddresslatitude" path="activity.shipToAddress.latitude" />
							        <form:input id="pickupAddresslongitude" path="activity.shipToAddress.longitude" />
							        <form:input id="pickupAddresscountryISOCode" path="activity.shipToAddress.countryISOCode" />
							        <form:input id="pickupAddressisAddressCleansed" path="activity.shipToAddress.isAddressCleansed" />
							        <!-- added to pass problem codes -->
							        <input type="hidden" id="problemCodes" name="problemCodes" value="" /> 
							        <input type="hidden" id="claimType" name="claimType" value="" /> 
							        
									
									</span>
	
			<h1><spring:message code="claim.label.warrantyClaim"/></h1>
			
			<!-- TODO: include assert account info page -->
			<div id = "include"> 
				<jsp:include page="/WEB-INF/jsp/claims/deviceInformation.jsp" flush="true"/>
			</div>
			<script type="text/javascript">
			function validatePartnerAgreement(){
				var result = true;
				var selectagrmt = document.getElementById('selectPartnerAgreement');
				var prtagrmt = selectagrmt.options[selectagrmt.selectedIndex].text;
				if(!trim(prtagrmt)){
					result = false;
					callOmnitureFieldErrorAction('Partner Agreement');
					showError("<spring:message code='claim.errorMsg.partnerAgreementNotNull'/>",null, true);
					jQuery('#selectPartnerAgreement').addClass('errorColor');
				}
				return result;
			}
			function validateCustomerAccount(){
				var result = true;
				var customerAccount;
				var customerAddress;
				var newCustomerAccountFlag = document.getElementById('newCustomerAccountFlagHidden').value;
				if(document.getElementById('customerAccount1').innerHTML=="" || document.getElementById('customerAddress1').innerHTML==""){
				if(document.getElementById('customerAccount2')==null ||document.getElementById('customerAddress2')==null)
					{					
					 customerAccount = document.getElementById('divCustomerInfo2InnerHTMLAccount').innerHTML;
					 customerAddress = document.getElementById('divCustomerInfo2InnerHTMLAddress').innerHTML;
					}
				else
					{					
					if(document.getElementById('customerAccount2').value!="" && document.getElementById('customerAddress2').value!="")
						{
					 	customerAccount = document.getElementById('customerAccount2').value;
					 	customerAddress = document.getElementById('customerAddress2').value;
						}
					else
						{
						customerAccount="";
						customerAddress="";
						}
					}
				}
				else
					{
					customerAccount=document.getElementById('customerAccount1').innerHTML;
					customerAddress=document.getElementById('customerAddress1').innerHTML;
					}
				if(newCustomerAccountFlag == true||customerAccount==""||customerAddress==""){
					if(!trim(customerAccount)){
						result = false;
						callOmnitureFieldErrorAction('Customer Account');
						showError("<spring:message code='claim.errorMsg.customerAccountNotNull'/>",null, true);
						jQuery('#customerAccount2').addClass('errorColor');
					}
					if(!trim(customerAddress)){
						result = false;
						callOmnitureFieldErrorAction('Customer Address');
						showError("<spring:message code='claim.errorMsg.customerAddressNotNull'/>",null, true);
						jQuery('#customerAddress2').addClass('errorColor');
					}
				}
				
				return result;
			}
			</script>	
				
	<div id="div_gray_layout">		
		   <%@ include file="/WEB-INF/jsp/request/serviceRequestHistoryList.jsp"%>
			
			<div class="portlet-wrap" id="customer_contact_information">
				<div class="portlet-header"><h3><spring:message code="claim.label.customerContactinformation"/></h3></div><!-- portlet-header -->
				<br>
				<div class="portlet-wrap-inner">
					<div class="width-100per" >
						<div class=" two">
							<div class="first-column">
								<table>
									<tr><td align="right"><B><label><span class="required"> * </span><spring:message code="claim.label.firstName"/>&nbsp;</label></B></td><td  align="left"><div class="inputMedWrapper"><input class="inputMed" type="text" id="activity.serviceRequest.primaryContact.firstName" name="activity.serviceRequest.primaryContact.firstName" onafterpaste="removeSpecialCharacters(this);" onkeyup="removeSpecialCharacters(this);" onchange="validateLength(0, 50, this)"/></div></td></tr>
									<tr><td align="right"><B><label><span class="required"> * </span><spring:message code="claim.label.lastName"/>&nbsp;</label></B></td><td align="left"><div class="inputMedWrapper"><input class="inputMed" type="text" id="activity.serviceRequest.primaryContact.lastName" name="activity.serviceRequest.primaryContact.lastName" onafterpaste="removeSpecialCharacters(this);" onkeyup="removeSpecialCharacters(this);" onchange="validateLength(0,50,this)"/></div></td></tr>
									<tr><td align="right"><B><label><span class="required"> * </span><spring:message code="claim.label.phone"/>&nbsp;</label></B></td><td align="left"><div class="inputMedWrapper"><input class="inputMed" type="text" maxlength=40 id="activity.serviceRequest.primaryContact.workPhone" name="activity.serviceRequest.primaryContact.workPhone" onafterpaste="javascript:if(event.keyCode != 37 && event.keyCode != 39 && event.keyCode != 8){clearNoNumAndPoint(this);}" onkeyup="javascript:if(event.keyCode != 37 && event.keyCode != 39 && event.keyCode != 8){clearNoNumAndPoint(this);}" onchange="validateLength(0,40,this)"/></div></td></tr>
									<tr><td align="right"><B><label><spring:message code="claim.label.emailID"/>&nbsp;</label></B></td><td align="left"><div class="inputMedWrapper"><input class="inputMed" type="text" id="activity.serviceRequest.primaryContact.emailAddress" name="activity.serviceRequest.primaryContact.emailAddress" onBlur="validateEmailNotEmpty(this);" onchange="validateLength(0,50,this)"/></div></td></tr>					
								</table>
							</div>
						</div>
					</div>
				</div><!-- portlet-wrap-inner -->
				<div class="portlet-footer">
					<div class="portlet-footer-inner"></div><!-- portlet-footer-inner -->
				</div><!-- portlet-footer -->
			</div>
			<script type="text/javascript">
				function validateEmailNotEmpty(ele){
					if(ele.value != ""){
						validateEmail(ele);
					}  
				}
			
				function validateCustomerContactInfo(){

					var result = true;
					var firstName = document.getElementById('activity.serviceRequest.primaryContact.firstName').value;
					var lastName = document.getElementById('activity.serviceRequest.primaryContact.lastName').value;
					var phoneNum = document.getElementById('activity.serviceRequest.primaryContact.workPhone').value;

					if(!trim(firstName)){
						result = false;
						callOmnitureFieldErrorAction('Contact First Name');
						showError("<spring:message code='claim.errorMsg.firstNameNotNull'/>",null, true);
						jQuery('#activity\\.serviceRequest\\.primaryContact\\.firstName').addClass('errorColor');
					}
					if(!trim(lastName)){
						result = false;
						callOmnitureFieldErrorAction('Contact Last Name');
						showError("<spring:message code='claim.errorMsg.lastNameNotNull'/>",null, true);
						jQuery('#activity\\.serviceRequest\\.primaryContact\\.lastName').addClass('errorColor');
					}
					if(!trim(phoneNum)){
						result = false;
						callOmnitureFieldErrorAction('Contact Phone Number');
						showError("<spring:message code='claim.errorMsg.phoneNotNull'/>",null, true);
						jQuery('#activity\\.serviceRequest\\.primaryContact\\.workPhone').addClass('errorColor');
					}
					return result;
				}
				function removeSpecialCharacters(ele){
					if (event.keyCode == 37 || event.keyCode == 39)
						return;
					reg = /[!#%&~@\$\^\*\.\?\"\[\]\(\)\-=_\+\{\}\\;':",<>/\|`]/g;
					ele.value = ele.value.replace(reg,"");
				} 
			</script>
			
			
	
			<div class="portlet-wrap" id="claim_details">
				<div class="portlet-header"><h3><spring:message code="claim.label.claimDetails"/></h3></div><!-- portlet-header -->
					<div class="portlet-wrap-inner">
						<div class="width-100per">
							<div class="columns two">
								<div class="first-column">
									<ul>
									<li>
										<label><span class="required"> * </span><spring:message code="claim.label.problemCode1"/></label><br/>
										
										<!-- onchange added for problem Code 1 CI 15.4 -->
								        	<select name="activity.actualFailureCode.value" id="problemCode" onchange="getprobCode(this.value,1)">
								        		<option value="" > </option>
												<c:forEach items="${claimRequestConfirmationForm.problemCodeList}" var="problemCodeEntry" varStatus="loop">
													<option value="${problemCodeEntry.key}"> ${problemCodeEntry.value} </option>
												</c:forEach>
											</select>
									</li>
									<li>
										
										
											<div id="problemCode2Div" style="display: none;"><label><b><span class="required">* </span></b><spring:message code="claim.label.problemCode2"/></label></div>
												<span id="showProbErrorCode2"></span>
										
									</li>
									<li>
										
											<div id="problemCode3Div" style="display: none;"><label><b><span class="required">* </span></b><spring:message code="claim.label.problemCode3"/></label></div>
											<span id="showProbErrorCode3"></span>											
									</li>
									<li class="clear-both">											
										<label><spring:message code="claim.label.problemDetails"/></label><br/>
										<textarea name="activity.serviceRequest.problemDescription" cols="80" rows="6" onchange="validateLength(0,2000,this)" class="problem-description" ></textarea>
									</li>
									<li class="clear-both">
										
										<label><spring:message code="claim.label.serviceProviderReferenceNumber"/></label>
										<div class="inputMedWrapper"><input class="inputMed" type="text" name="activity.serviceProviderReferenceNumber" onchange="validateLength(0,30,this)"></div>
									</li>
									<li class="clear-both" id="reviewFlag">
										<input type="checkbox" id="activity.requestLexmarkReviewFlag" onclick="showHideReviewComments(this)" name="activity.requestLexmarkReviewFlag" value="true"/><label class="margin-left-10px"><spring:message code="claim.label.requestLexmarkReview"/></label></dd>
									</li>
										<br/><br/>
										<li>
										<div id="reviewCommentsDiv" style="display:none">
											
												<label><spring:message code="claim.label.reviewComments"/></label>
												<textarea id="reviewCommentsInput" name="activity.reviewComments" cols="80" rows="6" onchange="validateLength(0,150,this)"></textarea>
											
										</div>
										</li>
									</ul>
								</div>
							</div>
						</div>
					</div>
					<div class="portlet-footer">
						<div class="portlet-footer-inner"></div><!-- portlet-footer-inner -->
				</div><!-- portlet-footer -->
			</div>
			<script type="text/javascript">
				function showHideReviewComments(ele){
					if(ele.checked){
						document.getElementById('reviewCommentsDiv').style.display = 'block';
					}
					else{
						document.getElementById('reviewCommentsDiv').style.display = 'none';
						document.getElementById('reviewCommentsInput').value = '';
					}
				}
				
				function cleanClaimDetails(){
					document.getElementById("activity.requestLexmarkReviewFlag").checked = '';
					document.getElementById('reviewCommentsDiv').style.display = 'none';
				} 

				function validateClaimDetails(){
					var result = true;
					var pbEl = document.getElementById('problemCode');	
					if(pbEl.value == ''){
						result = false;
						callOmnitureFieldErrorAction('Problem Code');
						showError("<spring:message code='claim.errorMsg.problemCodeNotNull'/>", null, true);
						jQuery('#problemCode').addClass('errorColor');
						
					}	
					return result;
				}
			</script>
			
			
	
			<div class="portlet-wrap" id="technician_information">
				<div class="portlet-header">
					<h3><spring:message code="claim.label.technicianInformation"/></h3>
				</div><!-- portlet-header -->
				<div class="portlet-wrap-inner">
					<div class="width-100per">
						<div class="columns two">
							<div class="first-column">
								<table>
									<tr>
										<td align="right" class="padding-1">
											<label><b><span class="required">* </span><spring:message code="claim.label.technician"/></b></label> 
										</td>
										<td width="10px" />
										<td align="left">
											<select id="technicianChoose" onchange="showTechnicianInputTexts()" name="technicianFullName">
											</select>
										</td>
										<td>
											<span id="technicianLoading" style="display: none;">
												&nbsp;&nbsp;&nbsp;<img  src = "<html:imagesPath/>loading-icon.gif"; />&nbsp;<spring:message code="label.searchingNotification" />
											</span>											
										</td>
									</tr>
									<tr id="technicianFirstNameDD" style="display:none">
										<td align="right">
											<label><b><span class="required">* </span><spring:message code="claim.label.firstName"/></b></label>
										</td>
										<td width="10px" />
										<td align="left">
											<input type="text" id="technicianFirstNameInput" name="activity.technician.firstName" onchange="validateLength(0,50,this)"/>
										</td>
										<td/>
									</tr>
									<tr id="technicianLastNameDD" style="display:none">
										<td align="right">
											<label><b><span class="required">* </span><spring:message code="claim.label.lastName"/></b></label>
										</td>
										<td width="10px" />
										<td align="left">
											<input type="text" id="technicianLastNameInput" name="activity.technician.lastName" onchange="validateLength(0,50,this)"/>
										</td>
										<td/>
									</tr>
								</table>
							</div><!-- first-column -->
						</div>
					</div>
				</div><!-- portlet-wrap-inner -->
				<div class="portlet-footer">
					<div class="portlet-footer-inner"></div><!-- portlet-footer-inner -->
				</div><!-- portlet-footer -->
			</div>
			<script type="text/javascript">
				var accountId = document.getElementById("partnerAccountIdHidden").value;
				var partnerAccountValue = '${claimRequestConfirmationForm.asset.partnerAccount}';
		      	var customerAccountValue = '${claimRequestConfirmationForm.asset.account}';
				if(partnerAccountValue != '' && customerAccountValue != ''){
					if(accountId){
						ajaxTechnicianInformation(accountId);
					}else{
						ajaxTechnicianInformation("");
					}
				}
			
			
				function showTechnicianInputTexts(){
					var value = document.getElementById("technicianChoose").value;
					if(value == "other"){
						document.getElementById("technicianLastNameDD").style.display = "";
						document.getElementById("technicianFirstNameDD").style.display = "";
					}
					else{
						document.getElementById("technicianLastNameDD").style.display = "none";
						document.getElementById("technicianFirstNameDD").style.display = "none";
						document.getElementById("technicianLastNameInput").value = "";
						document.getElementById("technicianFirstNameInput").value = "";
					}
				}

				function cleanTechnicians(){
					document.getElementById("technicianChoose").value = '';
					document.getElementById("technicianLastNameDD").style.display = "none";
					document.getElementById("technicianFirstNameDD").style.display = "none";
					document.getElementById("technicianLastNameInput").value = "";
					document.getElementById("technicianFirstNameInput").value = "";
				}

				function validateTechnicianInfo(){
					var result = true;
					var technician = document.getElementById('technicianChoose').value;
					if(!technician){
						result = false;
						callOmnitureFieldErrorAction('Technician');
						showError("<spring:message code='claim.errorMsg.technicianNotNull'/>",null, true);
						jQuery('#technicianChoose').addClass('errorColor');
					}
					if(document.getElementById("technicianFirstNameDD").style.display != "none"){
						if(!trim(document.getElementById("technicianFirstNameInput").value)){
							result = false;
							callOmnitureFieldErrorAction('Technician First Name');
							showError("<spring:message code='claim.errorMsg.technicianFirstNameNotNull'/>",null, true);
							jQuery('#technicianFirstNameInput').addClass('errorColor');
						}
					}
					if(document.getElementById("technicianLastNameDD").style.display != "none"){
						if(!trim(document.getElementById("technicianLastNameInput").value)){
							result = false;
							callOmnitureFieldErrorAction('Technician Last Name');
							showError("<spring:message code='claim.errorMsg.technicianLastNameNotNull'/>",null, true);
							jQuery('#technicianLastNameInput').addClass('errorColor');
						}
					}
					return result;
				}
			</script>
	
	
	
	
	
	
			<div class="columns two">
				<div class="first-column">
					<dl>
						<dd>
							<c:if test="${claimRequestConfirmationForm.activity.exchangeFlag=='false'}">
							<label><spring:message code="claim.label.serviceRepairComplete"/></label>
							</c:if>
							<c:if test="${claimRequestConfirmationForm.activity.exchangeFlag=='true'}">
							<label><spring:message code="claim.label.serviceExchangeComplete"/></label>
							</c:if>
				        	<select name="activity.repairCompleteFlag" id="repairCompleteFlag" onchange="repairCompleteChanged(this)">
									<option value="false"><spring:message code="button.no"/></option>
									<option value="true"><spring:message code="button.yes"/></option>
							</select>							
						</dd>
					</dl>
				</div>			
			</div>		
<!--Changes for Defect # 14918-->			
<style>
#imgDateServicedAlingment dl dd{float:left;clear:both}
#imgDateServicedAlingment dl dd input{float:left;}
#imgDateServicedAlingment dl dd img{cursor:pointer;margin: 2px 0 0 5px;}
.ie7 #imgDateServicedAlingment dl dd img{margin:0 !important;margin-left:10px !important;}
</style>
			<div class="portlet-wrap" id="close_out_claim" style="display:none;">
				<table width="100%"><tr><td>
				<div id="close_out_claim_content">
					<div class="portlet-header" id="choseOutClaimHeader"><h3><spring:message code="claim.button.closeOutClaim"/></h3></div><!-- portlet-header -->
					<div class="portlet-wrap-inner">
						<div class="width-100per">
							<div class="columns two">
 								<div id="imgDateServicedAlingment" class="first-column width-40per-important" > <!--Changes for Defect # 8727 CI13.10 -->
									<dl>
										<dd><label><span class="required">* </span><spring:message code="claim.label.repairDescription"/></label></dd>
										<dd>
											<textarea id="activity.debrief.repairDescription" name="activity.debrief.repairDescription" cols="80" rows="6" onchange="validateLength(0,2000,this)"></textarea>
										</dd>
										<br>
										<dd><label><span class="required">* </span><spring:message code="claim.label.resolutionCode"/></label></dd>
										<dd>
								        	<select name="activity.debrief.resolutionCode.value" id="resolutionCode">
								        		<option value="" > </option>
												<c:forEach items="${claimRequestConfirmationForm.resolutionCodeList}" var="resolutionCode" varStatus="loop">
													<option value="${resolutionCode.key}" > ${resolutionCode.value} </option>
												</c:forEach>
											</select>									
										</dd>
										<br/>
										<!-- Added for CI BRD13-10-07 STARTS-->
										<dd><label><span class="required">* </span><spring:message code="claim.label.dateServiceRequested"/></label></dd>
										<dd>
											<input type="text" name="serviceRequestedDate" id="dateServicedRequested" size="16" onMouseUp="show_cal('dateServicedRequested', null, convertDateToString(new Date()), true);" readOnly="readonly" class="width-auto"/>
											<img id="imgdateServicedRequested"  style="" src="<html:imagesPath/>transparent.png" class="ui-icon calendar-icon" onClick="show_cal('dateServicedRequested', null, convertDateToString(new Date()), true)"/>
										</dd>
										<br/>
										<!-- Added for CI BRD13-10-07 ENDS-->
										<dd><label><span class="required">* </span><spring:message code="claim.label.dateServiced(Start)"/></label></dd>
										<dd>
											<input type="text" name="serviceStartDate" id="dateServicedStart" size="16" onMouseUp="show_cal('dateServicedStart', null, convertDateToString(new Date()), true);" readOnly="readonly" class="width-auto"/>
											<img id="imgDateServicedStart"  style="" src="<html:imagesPath/>transparent.png" class="ui-icon calendar-icon" onClick="show_cal('dateServicedStart', null, convertDateToString(new Date()), true);" />
										</dd>
										<br/>
										<dd><label><span class="required">* </span><spring:message code="claim.label.dateServiced(End)"/></label></dd>
										<dd>
											<input type="text" name="serviceEndDate" id="dateServicedEnd" size="16" onMouseUp="show_cal('dateServicedEnd', null, convertDateToString(new Date()), true);" readOnly="readonly" class="width-auto"/>
											<img id="imgDateServicedEnd"  src="<html:imagesPath/>transparent.png" class="ui-icon calendar-icon cursor-pointer" onClick="show_cal('dateServicedEnd', null, convertDateToString(new Date()), true);"/>								
										</dd>
										<br/>
										<dd><label><span class="required">* </span><spring:message code="claim.label.printerWorkingCondition"/></label></dd>
										<dd>
											<select id="printerWorkingCondition" name="activity.debrief.deviceCondition.value">
												<option value=""> </option>
												<c:forEach items="${claimRequestConfirmationForm.workingConditionList}" var="workingCondition" varStatus="loop">
													<option value="${workingCondition.key}" > ${workingCondition.value} </option>
												</c:forEach>
											</select>
										</dd>
										<br/>
										<dd><b><spring:message code="claim.label.pageCountAll" /></b></dd>
										<dd><input type="text" name="activity.debrief.pageCountAll" id="pageCountAllInput"  size="16" onblur="validateLength(0,9,this);" onafterpaste="clearNoNumAndPoint(this)" onkeyup="clearNoNumAndPoint(this)"></dd>
										<br/>  
									</dl>
								</div><!-- first-column -->
							</div>
						</div>
					</div><!-- portlet-wrap-inner -->
					<div class="portlet-footer">
						<div class="portlet-footer-inner"></div><!-- portlet-footer-inner -->
					</div><!-- portlet-footer -->
				</div>
				</td></tr></table>
			</div>
			<script type="text/javascript">
			
			 	function repairCompleteChanged(ele){
			 		showHiddenCloseOutClaim();
			 		if(additionalpaymentflag==true || additionalpaymentflag=='true'){
			 			showHiddenAdditionalPayment();
			 		}				 		
			 		if(exchangeflag==false || exchangeflag=='false'){
			 			changeDefault(ele.value);			
			 		}
			 		 		
			 	}
			 	
				function showHiddenCloseOutClaim(){
					var value = document.getElementById("repairCompleteFlag").value;
					if(value == 'true'){	
						document.getElementById("close_out_claim").style.display = "block";
					}
					else
					if(value == 'false'){
						document.getElementById("close_out_claim").style.display = "none";
						clearContentInCloseOutClaim();				
					}				
				}

				function clearContentInCloseOutClaim(){
					document.getElementById("activity.debrief.repairDescription").value = '';
					document.getElementById("resolutionCode").value = '';
					document.getElementById("dateServicedRequested").value = ''; // Added for CI BRD13-10-07
					document.getElementById("dateServicedStart").value = '';
					document.getElementById("dateServicedEnd").value = '';
					document.getElementById("printerWorkingCondition").value = '';	
				}

				function showHiddenAdditionalPayment(){
					var value = document.getElementById("repairCompleteFlag").value;
					if(value == 'true'){
						document.getElementById("Additional_payment_requests").style.display = "block";
						additionalPaymentRequestsGrid.loadXMLString("<rows></rows>");
					}
					else
					if(value == 'false'){
						document.getElementById("Additional_payment_requests").style.display = "none";
						deleteAllRowsInPaymentGrid();					
					}
				}


				function cleanRepairComplete(){
					document.getElementById("repairCompleteFlag").value = 'false';
					document.getElementById("close_out_claim").style.display = "none";
					document.getElementById("Additional_payment_requests").style.display = "none";
					deleteAllRowsInPaymentGrid();
				}

				function validateCloseOut(){
					var result = true;
					var value = document.getElementById("repairCompleteFlag").value;
					if(value == 'true'){
						var repairDesc = document.getElementById("activity.debrief.repairDescription").value;
						if(!trim(repairDesc)){
							result = false;
							callOmnitureFieldErrorAction('Repair Description');
							showError("<spring:message code='claim.errorMsg.repairDescNotNull'/>",null, true);
							jQuery('#activity\\.debrief\\.repairDescription').addClass('errorColor');
						}
						var resolutionCode = document.getElementById("resolutionCode").value;
						if(!trim(resolutionCode)){
							result = false;
							callOmnitureFieldErrorAction('Resolution Code');
							showError("<spring:message code='claim.errorMsg.resolutionCodeNotNull'/>",null, true);
							jQuery('#resolutionCode').addClass('errorColor');
						}
						var serviceStart = document.getElementById('dateServicedStart').value;
						var serviceEnd = document.getElementById('dateServicedEnd').value;	
						
						// Added for CI BRD13-10-07--STARTS
						var dateSerReq = document.getElementById('dateServicedRequested').value;

						if(!trim(dateSerReq)){
							result = false;							
							showError("<spring:message code='claim.errorMsg.dateServiceRequestedNotNull'/>",null, true);
							jQuery('#dateServicedRequested').addClass('errorColor');
						}
						// Added for CI BRD13-10-07--ENDS
						
						if(!trim(serviceStart)){
							result = false;
							callOmnitureFieldErrorAction('Service Start Date');
							showError("<spring:message code='claim.errorMsg.startDateNotNull'/>",null, true);
							jQuery('#dateServicedStart').addClass('errorColor');
						}
						
						if(!trim(serviceEnd)){
							result = false;
							callOmnitureFieldErrorAction('Service End Date');
							showError("<spring:message code='claim.errorMsg.EndDateNotNull'/>",null, true);
							jQuery('#dateServicedEnd').addClass('errorColor');
						}

						if(trim(serviceStart) && trim(serviceEnd)){
							var serviceStartDate = convertStringToStandardDateTime(serviceStart);
							var serviceEndDate = convertStringToStandardDateTime(serviceEnd);
							if(serviceStartDate > serviceEndDate || serviceStartDate == serviceEndDate){
								result = false;
								callOmnitureFieldErrorAction('Service Dates');
								showError("<spring:message code='claim.errorMsg.startDateLTEndDate'/>",null, true);
								jQuery('#dateServicedStart').addClass('errorColor');
							}
						}

						var printerCondition = document.getElementById('printerWorkingCondition').value;
						if(!trim(printerCondition)){
							result = false;
							callOmnitureFieldErrorAction('Printer Working Condition');
							showError("<spring:message code='claim.errorMsg.printerWorkingConditionNotNull'/>",null, true);
							jQuery('#printerWorkingCondition').addClass('errorColor');
							
						}
						result &= validateStartTime('dateServicedStart'); //Added for defect #8727
						result &= validateEndTime('dateServicedEnd');
						result &= validateDateServiceRequested(); //Added for CI Defect #9332
					}
					return result;
				}
			</script>
			
		<div class="portlet-wrap" id="partAndToolDiv">
			<div class="portlet-header">
			    <c:if test="${claimRequestConfirmationForm.activity.exchangeFlag=='true'}">
				<h3><spring:message code="claim.label.deviceToBeExchanged"/></h3>
				</c:if>
				<c:if test="${claimRequestConfirmationForm.activity.exchangeFlag=='false'}">
				<h3><spring:message code="claim.label.partsAndTools"/></h3>
				</c:if>
			</div>
			<div class="portlet-wrap-inner overflow-xAuto-yHidden" >
			<c:if test="${claimRequestConfirmationForm.activity.exchangeFlag=='true'}">
				<portlet:renderURL var="customerAccountInfoUrl"
					windowState='<%= LiferayWindowState.POP_UP.toString() %>'>
						<portlet:param name="action" value="customerAccountInfo"/>
				</portlet:renderURL>
				  	<div class="width-100per">
					    <div class="columns three">
					      <div class="first-column width-25per" >
						    <dl>
							  <dd>
					            <div class="device">
								  <label>${claimRequestConfirmationForm.asset.productLine}</label><br />
								  <img width="75" src="${claimRequestConfirmationForm.asset.productImageURL}"/><br />
							    </div><!-- device -->
							  </dd>
					        </dl>		  
					      </div><!-- first-column -->
					      <div class="second-column width-35per" >
					      	<table>
							  	<tr>
								  	<td align="right" ><span ><B><spring:message code="claim.label.deviceData" /></B></span></td>
								  	<td width="20px">&nbsp;</td>
						  				<td>&nbsp;</td>
					  				</tr>
							  	<tr>
				  	<td align="right" ><span ><B><spring:message code="claim.label.serialNumber" /></B></span></td>
				  	<td width="20px">&nbsp;</td>
		  				<td><span>${claimRequestConfirmationForm.asset.serialNumber}</span></td>
	  				</tr>
	  				<tr>
				  	<td align="right" ><span><B><spring:message code="claim.label.machineTypeModel" /></B></span></td>
				  	<td width="20px">&nbsp;</td>
		  				<td><span>${claimRequestConfirmationForm.asset.modelNumber}</span></td>
	  				</tr>
	  				<tr>
				  	<td align="right" ><span ><B><spring:message code="claim.label.productPN/TLI" /></B></span></td>
				  	<td width="20px">&nbsp;</td>
		  				<td><span>${claimRequestConfirmationForm.asset.productTLI}</span></td>
	  			</tr>
	  			<tr>
				  	<td align="right" >&nbsp;</td>
				  	<td width="20px">&nbsp;</td>
		  				<td>&nbsp;</td>
	  			</tr>
	  			
	  			
			</table>
	      </div><!-- second-column -->
	      <div class="third-column width-40per" >
	      	<input type="hidden" id = "newCustomerAccountFlagHidden" name="activity.newCustomerAccountFlag" value="false"></input>
	      				<!-- 2098 Changes 111 -->
	      	
	     	      				
	      	<input type="hidden" id = "partnerAccountCreateNewAddressFlagHidden" name="activity.partnerAccount.createShipToAddressFlag" value="${claimRequestConfirmationForm.asset.partnerAccount.createShipToAddressFlag}"></input>
	      	
	      	<input type="hidden" id = "fromDiv"/>
	      	<input type="hidden" id = "PartnerAccountOptionValue" value=""/>
	      	<input type="hidden" id = "OnChangeOfPartnerAccount"/>
	      	
	      	<input type="hidden" id = "CustomerPartnerStatus" /> 
	      	<input type="hidden" id = "partnerAgreementSelectFlag" value="false"/> 
	      	
	      	<input type="hidden" id = "defaultCurrencyHidden" value="${claimRequestConfirmationForm.asset.partnerAccount.defaultCurrency}"/> 
	      	<div id="divCustomerInfo1" style="display:none">
		        <table width="100%">
				  	<tr>
					  	<td align="right" ><span ><B><spring:message code="claim.label.customerInformation" />:</B></span></td>
					  	<td width="20px">&nbsp;</td>
	 	  				<td align="left">&nbsp;</td>
		  				</tr>
				  	<tr>
					  	<td align="right" ><span ><B><spring:message code="claim.label.customerAccount" /></B></span></td>
					  	<td width="20px">&nbsp;</td>
	 	  				<td align="left">
	 	  					<span id = "customerAccount1">${claimRequestConfirmationForm.asset.account.accountName}</span>
	 	  				</td>
		  			</tr>
		  			<tr>
					  	<td align="right" valign="top" rowspan="3"><span ><B><spring:message code="claim.label.customerAddress" /></B></span></td>
					  	<td width="20px" rowspan="3">&nbsp;</td>
	 	  				<td align="left" valign="top" rowspan="3">
	 	  					<span id = "customerAddress1">
								<util:addressOutput value="${claimRequestConfirmationForm.asset.installAddress}"/>
	 	  					</span>
	 	  				</td>
		  			</tr>
		  			<!-- Commented for CI CR's 15.1 -->
		  			<%-- <tr></tr>
		  			<tr></tr>
		  			<tr>
					  	<td align="right" >&nbsp;</td>
					  	<td width="20px">&nbsp;</td>
	 	  				<td>&nbsp;</td>
		  			</tr>
		  			<tr>
					  	<td colspan="3" align="center" ><a href="javascript:void(0);"  onclick="showCustomerAccountInfoPop('divCustomerInfo1');"><spring:message code="claim.label.chooseDifferentCustomerAccount"/></a></td>
		  			</tr>
		  			<tr>
					  	<td colspan="3" align="center" ><a href="javascript:void(0);"  onclick="createNewCustomerAccountHiddenShow();return false;"><spring:message code="claim.label.createNewCustomerAccount" /></a></td>
		  			</tr> --%>
		  			<!-- End -->
	 			</table>
	      	</div>
	      	<div id="divCustomerInfo2" style="display:none">
		        <table width="100%">
				  	<tr>
					  	<td align="right" ><span ><B><spring:message code="claim.label.customerInformation" />:</B></span></td>
					  	<td width="20px">&nbsp;</td>
	 	  				<td align="left">&nbsp;</td>
		  			</tr>
		  			<tr id="selectPartnerAgreementTR">
					  	<td align="right"><label class="color-red">*&nbsp;</label><span ><spring:message code="claim.label.partnerAgreement" /></span></td>
					  	<td width="20px">&nbsp;</td>
	 	  				<td align="left">
	 	  					<select id="selectPartnerAgreement" name="selectPartnerAgreement" onclick="partnerAgreementOnClick();" onchange="partnerAgreementOnChange();" class="width-150px" onmouseover="this.title=this.options[this.selectedIndex].title">
								<option value=""></option>
			 	  				<c:forEach items="${claimRequestConfirmationForm.partnerIndirectAccountList}" var="accounts">
			 	  					<c:choose >  
			 	  						<c:when test="${accounts.accountId == claimRequestConfirmationForm.asset.partnerAccount.accountId}">
											<option value="${accounts.accountId}" title="${accounts.accountName}" selected = "selected">${accounts.accountName}</option>
												<script type="text/javascript">
													document.getElementById("partnerAgreementSelectFlag").value = 'true';
												</script>
									 	</c:when>  
										<c:otherwise>  
											<option value="${accounts.accountId}" title="${accounts.accountName}">${accounts.accountName}</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</select>
						</td>
		  			</tr>
		  			
				  	<tr>
					  	<td align="right"><label class="color-red">*&nbsp;</label><spring:message code="claim.label.customerAccount" /></td>
					  	<td width="20px">&nbsp;</td>
	 	  				<td align="left">
	 	  					<div id ="divCustomerInfo2InnerHTMLAccount">
	 	  						<div class="inputMedWrapper"><input class="inputMed" type="text" name="newAccountName" id="customerAccount2" /></div>
	 	  					</div>
	 	  				</td>
		  			</tr>
		  			<tr>
					  	<td align="right" valign="top" rowspan="3"><label class="color-red">*&nbsp;</label><span ><spring:message code="claim.label.customerAddress" /></span></td>
					  	<td width="20px" rowspan="3">&nbsp;</td>
	 	  				<td align="left" valign="top" rowspan="3">
	 	  					<div id ="divCustomerInfo2InnerHTMLAddress">
	 	  						<textarea  id="customerAddress2" name="activity.newCustomerAddressCombined" rows="4" cols="30" class="customer-address2"></textarea>
	 	  					</div>
	 	  				</td>
		  			</tr>
		  		<!-- Commented for CI CR's 15.1 -->
		  			<%-- <tr></tr>
		  			<tr></tr>
		  			<tr>
					  	<td align="right" >&nbsp;</td>
					  	<td width="20px">&nbsp;</td>
	 	  				<td>&nbsp;</td>
		  			</tr>
		  			<tr>
					  	<td colspan="3" align="center" ><span id="chooseDifferentCustomerAccountSpan"><a href="javascript:void(0);" onclick="showCustomerAccountInfoPop('divCustomerInfo2');"><spring:message code="claim.label.chooseDifferentCustomerAccount"/></a></span></td>
		  			</tr>
		  			<tr>
					  	<td colspan="3" align="center" ><span id="createNewCustomerAccountSpan"><a href="javascript:void(0);"  onclick="createNewCustomerAccountHiddenShow();return false;"><spring:message code="claim.label.createNewCustomerAccount" /></a></span></td>
		  			</tr> --%>
		  			<!-- END -->
		  		</table>
		  	
	      	</div>
	      	<script type="text/javascript">
	      	var disableAccount2 = '<input class="text" type="text" name="newAccountName" id="customerAccount2" disable="true" />';
			var disableAddress2 = '<textarea  id="customerAddress2" name="activity.newCustomerAddressCombined" rows="4" cols="30" disable="true"></textarea>';
			var inableAccount2 = '<input class="text" type="text" name="newAccountName" id="customerAccount2"/>';
			var inableAddress2 = '<textarea  id="customerAddress2" name="activity.newCustomerAddressCombined" rows="4" cols="30"></textarea>';
 	 
			/* Commented for CI CR's 15.1 */
			/* function createNewCustomerAccountHiddenShow(){
				callOmnitureAction('Create Claim Device Selection - Multiple Claims View', 'Create New Customer Account');
				flag = document.getElementById("CustomerPartnerStatus").value;
				
				document.getElementById("newCustomerAccountFlagHidden").value = "true";
				jQuery('#customerAccountIdHidden').val('');//added
				
				jQuery("#divCustomerInfo1").hide();
				jQuery("#divCustomerInfo2").show();
				
				if(document.getElementById("partnerAgreementSelectFlag").value == 'true'){
					jQuery("#divCustomerInfo2InnerHTMLAccount").html(inableAccount2);
					jQuery("#divCustomerInfo2InnerHTMLAddress").html(inableAddress2);
				}else{
					callDisableAllInputAndButtons();

					jQuery("#divCustomerInfo2InnerHTMLAccount").html(disableAccount2);
					jQuery("#divCustomerInfo2InnerHTMLAddress").html(disableAddress2);
				}

			} */
/* END */
			function selectAnotherPrinter(){
				callOmnitureAction('Create Claim Device Selection - Multiple Claims View', 'Select Another Printer');
				jConfirm("<spring:message code='claimCreate.alert.selectAnotherPrinter'/>", "", function(answer) {
						if(!answer){
							return;
						}else{
							window.location.href="<portlet:renderURL><portlet:param name='action' value='showGlobalPartnerAssetSectionView' /></portlet:renderURL>";
						}
					});
			}
			/* Commented for CI CR's 15.1 */
			/* function showCustomerAccountInfoPop(fromDiv){
				callOmnitureAction('Create Claim Device Selection - Multiple Claims View', 'Choose A Different Customer Account');
				document.getElementById("fromDiv").value = fromDiv;
				var customerAccountInfoUrl = "${customerAccountInfoUrl}"
				var widthStr = (document.documentElement.clientWidth-1145)/2;
				var heightStr = (document.documentElement.clientHeight-300)/2;
				showOverlayIE6();
				new Liferay.Popup({
					title: "<spring:message code='claim.label.customerAccountInformation'/>",
					position: [widthStr,heightStr], 
					modal: true,
					width: 1145,
					resizable:false, 
					height: 'auto',
					xy: [100, 100],
					url:customerAccountInfoUrl
					});
				bindingClosePopupIE6();
			} */
			/* END */
			function partnerAgreementOnClick(){
				document.getElementById("PartnerAccountOptionValue").value = document.getElementById("selectPartnerAgreement").value;
			}
	
			function partnerAgreementChange(accountId){
				var el = document.getElementById("selectPartnerAgreement");
				var val = el[el.selectedIndex].text;
				
				document.getElementById("partnerAccountNameHidden").value =  val;
				document.getElementById("partnerAccountIdHidden").value =  document.getElementById("selectPartnerAgreement").value;
				document.getElementById("OnChangeOfPartnerAccount").value =  document.getElementById("selectPartnerAgreement").value;
				
				ajaxRetrieveOrgId(accountId);
				ajaxShiptoAddressbyAccountId(accountId);
				ajaxTechnicianInformation(accountId);
				if(exchangeflag==false || exchangeflag=='false'){
					setDefaultCurrencyAndAddPart(accountId);
				}
			}
			function setDefaultCurrencyAndAddPart(accountId){
				if(accountId != ""){
					<c:forEach items="${claimRequestConfirmationForm.partnerIndirectAccountList}" var="account">
						if("${account.accountId}" == accountId){
							document.getElementById("defaultCurrencyHidden").value = "${account.defaultCurrency}";
							if("${account.orderPartsFlag}" == "true"){
								if(null != document.getElementById("claimTypeList")){
									claimTypeValue = document.getElementById("claimTypeList").value;
									if(claimTypeValue == "Maintenance Kit Install"){
										document.getElementById("partAndToolDiv").style.display="none";
									}
									else{
										document.getElementById("partAndToolDiv").style.display="block";
									}
									
								}
								else{
									document.getElementById("partAndToolDiv").style.display="block";
								}								
								if(exchangeflag==false || exchangeflag=='false'){     
									document.getElementById("partNumberWrapper").innerHTML='<input class="inputMed text" type="text" id="partNumber"/>';
								}
								partsAndToolsDebriefGrid.loadXMLString("<rows></rows>");
							}else{
								document.getElementById("partAndToolDiv").style.display="none";
								clearAddress();
							}
							return;
						}
					</c:forEach>
				}
				
			}
	
			function ajaxRetrieveOrgId(accountId){
				var url = '<portlet:resourceURL id="retrieveOrganizationId"/>';
			    url += "&accountId="+accountId;
	
			    doAjax(url, callbackGetOrganizationIdResult, null, "");
			}
	
			function callbackGetOrganizationIdResult(result){
				document.getElementById("partnerAccountOrganizationIdHidden").value = result.data;
				return true;
			}
			function callDisableAllInputAndButtons(){
				disableAllInputAndButtons();
			}
			
			function callCleanAllInputs(){
				cleanAllInputs();
			}
			
			function callEnableAllInputAndButtons(){
				enableAllInputAndButtons();
			}
	
			
			function ajaxTechnicianInformation(accountId){
				var url = '<portlet:resourceURL id="setTechnicianInformation"/>';
				url +="&accountId=" + accountId;
				document.getElementById("technicianLoading").style.display = "block";
				doAjax(url, callbackGetTechInfoAccountList, null, null);
			}
	
			function callbackGetTechInfoAccountList(result){
				var mapList = result.data;
				var valueKey = mapList.split("}");
				var selectObject = document.getElementById('technicianChoose');
				selectObject.options.length=0;
				for (i=0;i<valueKey.length;i++){
					var textValue = valueKey[i].split("/");
					selectObject.options.add(new Option(textValue[0],valueKey[i]));
				}
				selectObject.remove(selectObject.length-1);
				selectObject.options.add(new Option('<spring:message code="claim.label.dropdownlist.optionvalue.technician.other"/>','other'));
				
				document.getElementById("technicianLoading").style.display = "none";
				return true;
			}
	
			function ajaxShiptoAddressbyAccountId(accountId){
				var url = '<portlet:resourceURL id="setShiptoAddress"/>';
				url +="&accountId=" + accountId;
				doAjax(url, callbackGetShiptoAddress, null, null);
			}
	
			function callbackGetShiptoAddress(result){
				var mapList = result.data;
				if(mapList == ""){
				}else{
					var valueKey = mapList.split("/");
						document.getElementById("partnerAccountCreateNewAddressFlagHidden").value = valueKey[0];					
					if(valueKey[1]!="" && valueKey[1]!="null"){
						addPartnerAddressElement(valueKey[1],valueKey[2],valueKey[3],valueKey[4],valueKey[5],valueKey[6],valueKey[7],valueKey[8],
								valueKey[9],valueKey[10],valueKey[11],valueKey[12],valueKey[13]);
					}
				}
				return true;
			} 

			</script>
			
			<script type="text/javascript">
     		// step 1 inorder to get asset account and partner account status
			jQuery(document).ready(function(){
		     	var partnerAccount = '${claimRequestConfirmationForm.asset.partnerAccount}';
		      	var customerAccount = '${claimRequestConfirmationForm.asset.account}';
				if(customerAccount != ''){ 
					if(partnerAccount !=''){ 
						
						jQuery("#CustomerPartnerStatus").val("CustomerPartner");
					}else{
						
						jQuery("#CustomerPartnerStatus").val("Customer");
					}
				}else{
					if(partnerAccount !=''){ 						
						jQuery("#CustomerPartnerStatus").val("Partner");
					}else{					
						jQuery("#CustomerPartnerStatus").val("Nothing");
					}
				}
				
		    	// step 2 display condition 
				var flag = document.getElementById("CustomerPartnerStatus").value;
				if(flag == 'CustomerPartner'){
					jQuery("#divCustomerInfo1").show();
					jQuery("#divCustomerInfo2").hide();
				}
				if(flag == 'Nothing' || flag == 'Customer' || flag == 'Partner'){
					jQuery("#divCustomerInfo1").hide();
					jQuery("#divCustomerInfo2").show();

					jQuery("#newCustomerAccountFlagHidden").val("true");

					if(flag == 'Nothing'||flag == 'Customer'){
						callDisableAllInputAndButtons();
						jQuery("#divCustomerInfo2InnerHTMLAccount").html(disableAccount2);
						jQuery("#divCustomerInfo2InnerHTMLAddress").html(disableAddress2);
					}else{
						//the partner agreement have been selected	
						if(document.getElementById("partnerAgreementSelectFlag").value == 'true'){
							jQuery("#divCustomerInfo2InnerHTMLAccount").html(inableAccount2);
							jQuery("#divCustomerInfo2InnerHTMLAddress").html(inableAddress2);
						}else{
							jQuery("#divCustomerInfo2InnerHTMLAccount").html(disableAccount2);
							jQuery("#divCustomerInfo2InnerHTMLAddress").html(disableAddress2);
							
							callDisableAllInputAndButtons();
						}
					}
				}
				var numericCodeFlag = true;
				var showProbValidateMsg = false;
				var claimTypeFlag = false;
			});
     					
			</script>
			
	      </div><!-- third-column -->
	    </div><!-- columns -->
    </div>
  <div class="portlet-footer">
	<div class="portlet-footer-inner"></div><!-- portlet-footer-inner -->
  </div><!-- portlet-footer -->
				  
		    </c:if>
		    <c:if test="${claimRequestConfirmationForm.activity.exchangeFlag=='false'}">
						<!-- 2098 Changes 222 -->
			
				<div class="columns scroll">
					<table>
						<tr>
							<td>
								<b><label><spring:message code="claim.label.partNumberAdd"/>:</label></b>
							</td>
						</tr>
						<tr>
							<td>
								<table>
									<tr>
										<td class="padding-right-15px">
											<div id="partNumberWrapper"><input class="inputMed text" type="text" id="partNumber"/></div>
										</td>
										<td>
											<a href="javascript:searchPart(document.getElementById('partNumber').value);" class="button" id="partSearchButton"><spring:message code="claim.button.addPart"/></a>&nbsp;&nbsp;
										</td>
										<td>
											<span id="showPartsListSpan"><a id="showPartsListLink" href="javascript:void(0);" onclick="showPartList();"><spring:message code="claim.label.partsList"/></a></span>
										</td>
									</tr>
								</table>					
							</td>
							<td>
								<label id="partSearchLoading" style="display: none;">
									&nbsp;&nbsp;&nbsp;<img  src = "<html:imagesPath/>loading-icon.gif"; />&nbsp;<spring:message code="label.searchingNotification" />
								</label>
							</td>
						</tr>
					</table>
				</div>
				
<style type="text/css">
	#partNumber .inputMed .text {
		width:141px;
	}

</style>
				<div id="qtyValidate" class="qty-validate" ></div>
				<div class="">
					<div id="gridCCVPartDebrief" class="width-900px"></div>
					<div id="pagingAreaDebrief" class="width-100per-important"></div>
				
				</div>
				<label for="activity.shipToAddress.addressId" class="error"
				style="display: none;"><spring:message code="claim.errorMsg.partnerAddress"/></label>
			<div id="partnerAddressDiv" class="scroll">
				<table>
					<tr>
						<td valign="top" align="left" width="85px">
							&nbsp;&nbsp;&nbsp;&nbsp;<b><spring:message code="claim.label.shipTo"/></b>
						</td>
						<td width="590px">
						<!-- Changes Done in Structure for CI Defect #9332 -->
							<table width="590px" id="addressInfo" name="addressInfo" >
								<tr><td><label id="addressNameLabel" style="display:none;"></label></td></tr>
								<%--<tr><td><label id="pickupStoreFrontNameLbl" style="display:none;"></label></td></tr>  --%>
								<tr><td><label id="addressLine1Label" style="display:none;"></label></td></tr>
								<tr><td><label id="pickupAddressofficeNumberLbl" style="display:none;"></label></td></tr>
								<tr><td><label id="addressLine2Label" style="display:none;"></label></td></tr>
								<tr>
									<td>
										<label id="cityLabel" style="display:none;"></label>
										<label id="pickupAddresscountyLbl" style="display:none;"></label>
										<label id="stateLabel" style="display:none;"></label> 
										<label id="provinceLabel" style="display:none;"></label>
										<label id="pickupAddressRegionLB" style="display:none;"></label>
										
									</td>
								</tr>
								<tr><td><label id="postalCodeLabel" style="display:none;"></label><br></td></tr>
								<tr><td><label id="countryLabel" style="display:none;"></label><br></td></tr>	
										
									
								
								<tr align="left">
									<td style="display: none">
									<!-- form input id added for CI BRD 08-10-08 -->
 									<!--Commented for CI Defect #9335 -->
  										<%--<form:input path="activity.shipToAddress.addressId" /> --%>
										<form:input path="activity.shipToAddress.addressName" />
										<form:input path="activity.shipToAddress.storeFrontName" /> 
										<form:input path="activity.shipToAddress.addressLine1" /> 
										<form:input path="activity.shipToAddress.officeNumber" /> 
										<form:input path="activity.shipToAddress.addressLine2" /> 
										<form:input path="activity.shipToAddress.city" /> 
										<form:input path="activity.shipToAddress.county" />
										<form:input path="activity.shipToAddress.state" /> 
										<form:input path="activity.shipToAddress.province" /> 
										<form:input path="activity.shipToAddress.region" />
										<form:input path="activity.shipToAddress.country" /> 
										<form:input path="activity.shipToAddress.postalCode" />
										<form:input path="activity.shipToAddress.newAddressFlag" />
										
									
									</td>
								</tr>
							</table>
						</td>
					</tr>
					<tr>
						<td colspan="2"><a href="javascript:void(0);" id="installedAddressLink" onClick="return popupAddress(id);">
							<spring:message code="claim.label.chooseADifferentAddress"/></a>
						</td>
					</tr>
					<tr>
						<td colspan="2"><a href="javascript:void(0);" onClick="clearAddress();return false;">
							<spring:message code="claim.label.clearAddress"/></a>
						</td>
					</tr>
				</table>
				</div>
			<!-- CI-6 Added Start -->
			<div id="expediteDiv" class="scroll">
				<table>
					<tr>
						<td valign="top" align="left" width="85px">
							&nbsp;&nbsp;&nbsp;&nbsp;<b><spring:message code="claim.label.expedite"/></b>
						</td>
						<td>
							<input type="checkbox" id="activity.expedite" onclick="saveCheckbox(this)" name="activity.expedite" checked="checked" value="Y"/>
						</td>
					</tr>
				</table>
			</div>
			<!-- CI-6 Added End -->
			
			<script type="text/javascript">
			//var isPartnerAddressGridLoaded = false;
			var isPageLoaded = false;
			var isChooseAdifferentAddress = true;
			var preOption = 1;
			var modelNumber="${claimRequestConfirmationForm.asset.modelNumber}";
			
			var favStatus = {};
			var partStatus = new Array();
			var partStatusValue = new Array();
			var errorCode1 = new Array();
			var errorCode1Value = new Array();
			var carriers = new Array();
			var carriersValue = new Array();
			var qtyList = new Array();
			
			var partDate = localizeFormatDate(new Date());
		
			<c:forEach items="${claimRequestConfirmationForm.partStatusList}" var="partStatus" varStatus = "status" >
				partStatus[${status.index}] = "${partStatus.value}";
				partStatusValue[${status.index}] = "${partStatus.key}";
			</c:forEach>
			<c:forEach items="${claimRequestConfirmationForm.errorCode1List}" var="errorCode1" varStatus = "status" >
				errorCode1[${status.index}] = "${errorCode1.value}";
				errorCode1Value[${status.index}] = "${errorCode1.key}";
			</c:forEach>
			<c:forEach items="${claimRequestConfirmationForm.carriers}" var="carrier" varStatus = "status" >
				carriers[${status.index}] = "${carrier.value}";
				carriersValue[${status.index}] = "${carrier.key}";
			</c:forEach>
		
			for(b = 1;b<=5;b++){
				qtyList[b] = b;
			}
			
			partsAndToolsDebriefGrid = new dhtmlXGridObject('gridCCVPartDebrief');
			partsAndToolsDebriefGrid.setImagePath("<html:imagesPath/>gridImgs/");
			partsAndToolsDebriefGrid.setHeader(autoAppendPlaceHolder(autoApplyRequiredStyle('<spring:message code="claim.headerList.partsAndTools.debrief"/>'),11));
			partsAndToolsDebriefGrid.setColAlign("left,left,left,left,left,left,left,left,left,left,left");
			partsAndToolsDebriefGrid.setInitWidths("200,200,300,300,0,0,0,0,0,0,65");
			partsAndToolsDebriefGrid.setColTypes("ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro");
			partsAndToolsDebriefGrid.setColSorting("na,na,str,str,na,na,na,str,na,na,na");
			partsAndToolsDebriefGrid.enableAutoWidth(true);
			partsAndToolsDebriefGrid.enableAutoHeight(true);
			partsAndToolsDebriefGrid.init();
			partsAndToolsDebriefGrid.prftInit();
			hideOrShowPartAndToolColumns(true);
			partsAndToolsDebriefGrid.setColumnHidden(5,true);
			partsAndToolsDebriefGrid.setColumnHidden(6,true);
			partsAndToolsDebriefGrid.enablePaging(true, 9999, 6, "pagingAreaDebrief", true);			
			partsAndToolsDebriefGrid.setSkin("light");
			partsAndToolsDebriefGrid.setPagingSkin("bricks");
			partsAndToolsDebriefGrid.attachEvent("onMouseOver", function(id,ind){
				return false;
			});
			partsAndToolsDebriefGrid.attachEvent("onRowAdded", function(rId){
				document.getElementById("partNumber").value = "";
			}); 
			partsAndToolsDebriefGrid.attachEvent("onAfterRowDeleted", function(id,pid){
				document.getElementById("partNumber").value = "";
     		});
					 
			function changeDefault(value){
				partsAndToolsDebriefGrid.setColumnHidden(5,true);
				partsAndToolsDebriefGrid.setColumnHidden(6,true);
				if(value=="false"){
					hideOrShowPartAndToolColumns(true);
					partsAndToolsDebriefGrid.loadSize("px:200,200,300,300,0,0,0,200,0,0,65"); 
					partsAndToolsDebriefGrid.loadXMLString("<rows></rows>");
				}
				if(value=="true"){
					hideOrShowPartAndToolColumns(false);
					partsAndToolsDebriefGrid.setColumnHidden(7,true);//added by ragesree 
					partsAndToolsDebriefGrid.loadSize("px:100,70,100,100,100,160,160,110,150,170,60");
					partsAndToolsDebriefGrid.loadXMLString("<rows></rows>");
				}
			}

			function hideOrShowPartAndToolColumns(hideFlag){
				partsAndToolsDebriefGrid.setColumnHidden(4,hideFlag);
				
				partsAndToolsDebriefGrid.setColumnHidden(7,hideFlag); //added by ragesree
				partsAndToolsDebriefGrid.setColumnHidden(8,hideFlag);
				partsAndToolsDebriefGrid.setColumnHidden(9,hideFlag);
			}
			
			function searchPart(partNumber){
				var partNum = partNumber.replace(/(^\s*)|(\s*$)/g, "");
				if(!document.getElementById("partListDiv")){
					document.getElementById("partNumber").value = partNum;
				}
				if(partNum=="" || partNum==null){
					jAlert('<spring:message code="claim.message.pleaseEnterAPartNumber"/>', "");
					return;
				}
				document.getElementById("partSearchButton").disabled = true;
				document.getElementById("partSearchLoading").style.display = "block";
				var url = '<portlet:resourceURL id="retrievePart"/>';
				url+="&partNumber="+partNumber+"&modelNumber="+modelNumber+"&organizationId="+document.getElementById("partnerAccountOrganizationIdHidden").value+"&isMaintenance=true";
				doAjax(url,callbackGetResult,callbackGetFailureResult,null);
			}
			function callbackGetResult(result) {
				document.getElementById("partSearchButton").disabled = false;
				document.getElementById("partSearchLoading").style.display = "none";
				var data = result.data?result.data:"";
				if(document.getElementById("partListDiv")){
					jQuery(window).scrollTop(0);
					partListPopUpWindow.destroy();
					partListFunction();
				}
				if(!result.data){
					jAlert('<spring:message code="claim.message.noPartFoundModified"/>', "");
					return true;
				}
				var strs = data.split("///");
				addRowByPart(strs);
				return true;
			}
			function callbackGetFailureResult(result){
				document.getElementById("partSearchButton").disabled = false;
				document.getElementById("partSearchLoading").style.display = "none";
				if(document.getElementById("partListDiv")){
					jQuery(window).scrollTop(0);
					partListPopUpWindow.destroy();
					partListFunction();
				}
				if(!result.data){
					jAlert('<spring:message code="claim.message.noPartFoundModified"/>', "");
					return true;
				}
				return false;
			}
			
			function deletePartRow(ind){
				jConfirm("<spring:message code='claim.message.removePart'/>", "", function(confirmResult) {
					if(!confirmResult){
						return;
					}else{
						partsAndToolsDebriefGrid.deleteRow(ind);
						showMessageForQty("",true);
					}
				});
			}
			function saveOption(ele){
				preOption = ele.value;
			}
			//CI-6 Changes Partha Start
			function saveCheckbox(ele){
				if(ele.value=='N'){
					ele.value = 'Y';
				}
				else if(ele.value=='Y'){
					ele.value = 'N';
				}
			}
			function selectQty(ele){
				if(parseInt(ele.value)>1){
					jAlert("<spring:message code='claim.message.qtyIsGreaterThan1'/>", "");
					showMessageForQty("<spring:message code='claim.message.qtyIsGreaterThan5'/>",false);
				}else{
					showMessageForQty("<spring:message code='claim.message.qtyIsGreaterThan5'/>",false);
				}
			}
			function showMessageForQty(msg,isDelete){
				var isMoreThan5 = checkTotalQTY();
				if(!isDelete){
					if(isMoreThan5)
						document.getElementById("qtyValidate").innerHTML = msg;
					else 
						document.getElementById("qtyValidate").innerHTML = "";
				}else{
					if(!isMoreThan5)
						document.getElementById("qtyValidate").innerHTML = "";
				}
			}
			function checkTotalQTY(){
				var grid = partsAndToolsDebriefGrid;
				var idPre = "qtyDebrif_";
				var rowNumber = grid.getRowsNum();
				var totalQty=0;
				if(rowNumber!=0){
					for(i = 0;i< rowNumber ;i++){
						var ind = grid.getRowId(i);
						var qtyValue = document.getElementById(idPre+ind).value;
						totalQty=parseInt(totalQty)+parseInt(qtyValue);	
					}
					if(parseInt(totalQty)>5)
						return true;
				}
				return false;
			}
			
			function countQTYSum(){
				var grid = partsAndToolsDebriefGrid;
				var idPre = "qtyDebrif_";
				var rowNumber = grid.getRowsNum();
				var totalQty=0;
				if(rowNumber!=0){
					for(i = 0;i< rowNumber ;i++){
						var ind = grid.getRowId(i);
						var qtyValue = document.getElementById(idPre+ind).value;
						totalQty=parseInt(totalQty)+parseInt(qtyValue);	
					}
				}
				return totalQty;
			}
			
			
			partListFunction();
			var partListPopUpWindow;
			function partListFunction(){
			AUI().use('aui-base',
			'aui-io-plugin-deprecated',
			'liferay-util-window',
			function(A) {

				partListPopUpWindow=Liferay.Util.Window.getWindow(
				{
				dialog: {
				centered: true,
				constrain2view: true,
				//cssClass: 'yourCSSclassName',
				modal: true,
				position: [-80,20],
				resizable: false,
				width: 950,
				height: 550,
				xy: [-80, 20],
				destroyOnClose: true,
				visible: false
				}
				}
				).plug(
				A.Plugin.IO,
				{
				autoLoad: false
				})



			});
			}
			var url = "<portlet:renderURL windowState='<%= LiferayWindowState.POP_UP.toString() %>'><portlet:param name='isMaintenance' value='true' />"+
			"<portlet:param name='action' value='showPartAndToolPage' /></portlet:renderURL>";
			function partListPopup(){
				jQuery(window).scrollTop(0);
				partListPopUpWindow.show();
				jQuery(".aui button.close, .aui button.btn.close").hide();
				partListPopUpWindow.titleNode.html("<spring:message code='claim.label.partsList'/>");
				partListPopUpWindow.io.set('uri',url);
				partListPopUpWindow.io.start();
				
				}
			function showPartList(){
				showOverlayIE6();
				partListPopup();
				bindingClosePopupIE6();
			}
			function addRowByPart(part){
				var rowNumber = partsAndToolsDebriefGrid.getRowsNum();
				if(rowNumber!=0){
					for(i = 0;i< rowNumber ;i++){
						var parNum = partsAndToolsDebriefGrid.cellByIndex(i,2).cell.innerHTML;
						if(parNum==part[0]){
							if(part[3]!="null" && part[3]!=null && part[3]!= ""){
								var replaceMessage = '<spring:message code="claim.message.partNumber"/>&nbsp;' +part[3]+'&nbsp;<spring:message code="claim.message.rePlacePartNumber"/>&nbsp;' +part[0]+'. <spring:message code="claim.message.partIsExist"/>';
								jAlert(replaceMessage, "");
								return;
							}
							jAlert('<spring:message code="claim.message.partIsExist"/>', "");
							return;
						}
					}
				}
				if(part[3]!="null" && part[3]!=null && part[3]!= ""){
					var replaceMessage = '<spring:message code="claim.message.partNumber"/>&nbsp;' +part[3]+'&nbsp;<spring:message code="claim.message.rePlacePartNumber"/>&nbsp;' +part[0];
					jAlert(replaceMessage, "");
				}
				var id = part[4];
				var str =[partDate,'<table><tr><td><select id="qtyDebrif_'+id+'" onchange=\"selectQty(this);\"  onclick=\"saveOption(this);\" >'+generateOption(qtyList)+'</select></td></tr></table>'
					,part[0],part[1]
					,'<table><tr><td><select id="partStatus_'+id+'" class="width-80px"><option value=""></option>'+generateOption(partStatus)+'</select></td></tr></table>'
					,'<table><tr><td><select onChange="retrieveErrorCode2(\''+id+'\');" class="width-140px" style="display:none;" id="errorCode1_'+id+'"><option value=""></option>'+generateOption(errorCode1)+'</select></td></tr></table>'
					,'<table><tr><td><label id="errorCode2Loading_'+id+'" style="display: none;"><img  src = "<html:imagesPath/>loading-icon.gif"; />&nbsp;<spring:message code="label.loadingNotification" /></label><select class="width-140px" style="display:none;" id="errorCode2_'+id+'"></select></td></tr></table>'
					,(part[2]=="true"?'<spring:message code="claim.lebel.returnRequired.yes"/>':'<spring:message code="claim.lebel.returnRequired.no"/>')
					,((part[2]=="false")?'':'<table><tr><td><select class="width-130px" id="reCarrier_'+id+'"><option value=""></option>'+generateOption(carriers)+'</select></td></tr></table>')
					,((part[2]=="false")?'':'<table><tr><td><input id="reTracingNum_'+id+'"  onblur="validateLength(1 &#44; 30 &#44; this);" class="width-130px" type="text" value=""/></td></tr></table>')
					,'<a href="javascript:deletePartRow(\''+id+'\');"><img class="ui-icon trash-icon" src="<html:imagesPath/>transparent.png"></a>'];
				partsAndToolsDebriefGrid.addRow(id,str,0);
				showMessageForQty("<spring:message code='claim.message.addRowQtyIsGreaterThan5'/>",false);
				
			}

			function addRowByPartList(part){
				document.getElementById("partListGridDiv").style.display = "none";
				document.getElementById("partListLoadingDiv").style.display = "";
				searchPart(part[0]);
			}
			
			function selectStatus(ele,ind){
				if(ele.value=="" || partStatusValue[ele.value].toLowerCase() != "used"){
					document.getElementById("errorCode1_"+ind).style.display = "none";
					document.getElementById("errorCode2_"+ind).style.display = "none";
				}else{
					document.getElementById("errorCode1_"+ind).style.display = "block";
					if(document.getElementById("errorCode1_"+ind) && document.getElementById("errorCode1_"+ind).value != ""){
						document.getElementById("errorCode2_"+ind).style.display = "block";
					}
				}
			}
			
			function generateOption(arr){
				var optionStr="";
				for(var i = 0;i<arr.length;i++){
					if(arr[i]==null)
						continue;
					optionStr +='<option value="'+i+'">'+arr[i]+'</option>';
				}
				return optionStr;
			}
			
			function clearAddress(){  
				document.getElementById("addressInfo").style.display="none";				
			}
			
			/* Commented for CI CR's 15.1 */
			/* function chooseADifferentAddressPopup(){
				var createNewFlag = document.getElementById("partnerAccountCreateNewAddressFlagHidden").value;
				callOmnitureAction('Create Claim Device Selection', 'Choose A Different Address');
				showOverlayIE6();
				new Liferay.Popup({
					title: "<spring:message code='claim.label.selectAddress'/>",
					position: [350,50], 
					modal: true,
					draggable: false,
					resizable: false,
					width: 830, 
					height: 'auto',
					xy: [100, 100],
					url:"${showAdressPopUrl}&createNewFlag="+createNewFlag+"&accountId="+document.getElementById("partnerAccountIdHidden").value
					});
				bindingClosePopupIE6();
			} */
		/* END */
			
			//the next three functions are used to add/remove classes on elements
			function hasClass(ele,cls) {
				return ele.className.match(new RegExp('(\\s|^)'+cls+'(\\s|$)'));
			}
			function addClass(ele,cls) {
				if (!this.hasClass(ele,cls)) ele.className += " "+cls;
			}
			function removeClass(ele,cls) {
				if (hasClass(ele,cls)) {
					var reg = new RegExp('(\\s|^)'+cls+'(\\s|$)');
					ele.className=ele.className.replace(reg,' ');
				}
			}
			
			//Change Done for CI BRD13-10-08
			function addPartnerAddressElement(ai,storeName,currentFavStatus, 
					  an, al1, offcNo, al2,city,county, state, province,region,country, zip,
					  physicalLoc1,physicalLoc2,physicalLoc3,district,countryISO,stateCode){
				//callOmnitureAction('Create Claim Device Selection', 'Add A Partner Address Element');
				if(favStatus[ai]){  		
					currentFavStatus = favStatus[ai].isFavorite? "true":"false";
				}
				if(ai==null || ai=="")
					jQuery('#pickupAddressisAddressCleansed').val("false");
				else
					jQuery('#pickupAddressisAddressCleansed').val("true");
				//Commented for CI Defects #9332 and #9335


				//we will not mark that address as favorite if it is already a favorite one.
				if(isPageLoaded == true && currentFavStatus == "false")
					//setPartnerAddressFavourite(ai,true); //commented
				if(isPageLoaded == true){
					//manually triger the jquery validation framework.
					//jQuery("#createClaimRequestForm").validate().element("#activity\\.shipToAddress\\.addressId");
				}
				isChooseAdifferentAddress = true;
				document.getElementById("partnerAddressDiv").style.display="block";
				document.getElementById("addressInfo").style.display="block";
	 			addPickupAddress(ai,storeName,currentFavStatus, 
						  an, al1, offcNo, al2,city,county, state, province,region,country, zip,
						  physicalLoc1,physicalLoc2,physicalLoc3,district,countryISO,stateCode);
	 			closeDialog();
			}
			
			function setPartnerAddressFavourite(favoriteAddressId, doSelectUnfavoriteFlag) {
				var url = '<portlet:resourceURL id="setPartnerAddressFavouriteFlag"/>';
			    url += "&favoriteAddressId="+favoriteAddressId;
			    url += "&accountId="+document.getElementById("partnerAccountIdHidden").value;
			
			    // if user selects an unfavorite contact
			    if (doSelectUnfavoriteFlag) {
			        url += "&flagStatus=" + "false";
			        doAjax(url, null, null, "");
			        return;
			    }
			    // if the favorite flag is being updated
			    if (favStatus[favoriteAddressId] && favStatus[favoriteAddressId].isLoading) {
			        return;
			    }
			    
			    var starImg = window.document.getElementById("starImg_address_" + favoriteAddressId);
			    var isFavorite = !(starImg.src.indexOf("unfavorite.png") > -1);
			    favStatus[favoriteAddressId] = {isFavorite:isFavorite,isLoading:true};
			    starImg.src = "<html:imagesPath/>loading-icon.gif";
			    url += "&flagStatus=" + isFavorite;
			    doAjax(url, callbackGetPartnerAddressResult, callbackGetPartnerAddressFailure, "");
			}
			
			//call back, when successfully update favorite status of service address
			function callbackGetPartnerAddressResult(result) {
				var addressId = result.data;
				if (favStatus[addressId].isFavorite) {
					favStatus[addressId].isFavorite = false;
				} else {
					favStatus[addressId].isFavorite = true;
				}
				favStatus[addressId].isLoading = false;
			}
			
			// call back, when fail to update favorite status of service address
			function callbackGetPartnerAddressFailure(result) {
			    var addressId = result.data;
			    favStatus[addressId].isLoading = false;
			}
			
			jQuery(document).ready(function() {
				if("${duplicateDevice}"=="true"){
					jQuery("#reviewFlag").hide();
				}
				document.getElementById("partnerAddressDiv").style.display="block";
					partsAndToolsDebriefGrid.loadXMLString("<rows></rows>");
					isPageLoaded = true;
					if("${claimRequestConfirmationForm.asset.partnerAccount}"!=null && "${claimRequestConfirmationForm.asset.partnerAccount}"!="null" && "${claimRequestConfirmationForm.asset.partnerAccount}"!=""){
						addPartnerAddressElement("${claimRequestConfirmationForm.asset.partnerAccount.address.addressId}",
												"${claimRequestConfirmationForm.asset.partnerAccount.address.storeFrontName}",
												"${claimRequestConfirmationForm.asset.partnerAccount.address.userFavorite}",
												"${claimRequestConfirmationForm.asset.partnerAccount.address.addressName}", 
												 "${claimRequestConfirmationForm.asset.partnerAccount.address.addressLine1}", 
												 "${claimRequestConfirmationForm.asset.partnerAccount.address.officeNumber}",
												 "${claimRequestConfirmationForm.asset.partnerAccount.address.addressLine2}", 
												 "${claimRequestConfirmationForm.asset.partnerAccount.address.city}", 
												 "${claimRequestConfirmationForm.asset.partnerAccount.address.county}",
												 "${claimRequestConfirmationForm.asset.partnerAccount.address.state}", 
												 "${claimRequestConfirmationForm.asset.partnerAccount.address.province}", 
												 "${claimRequestConfirmationForm.asset.partnerAccount.address.region}",  
												 "${claimRequestConfirmationForm.asset.partnerAccount.address.country}", 
												 "${claimRequestConfirmationForm.asset.partnerAccount.address.postalCode}",
												 "${claimRequestConfirmationForm.asset.partnerAccount.address.physicalLocation1}",
												 "${claimRequestConfirmationForm.asset.partnerAccount.address.physicalLocation2}",
												 "${claimRequestConfirmationForm.asset.partnerAccount.address.physicalLocation3}",
												 "${claimRequestConfirmationForm.asset.partnerAccount.address.district}",
												 "${claimRequestConfirmationForm.asset.partnerAccount.address.countryISOCode}",
												 "${claimRequestConfirmationForm.asset.partnerAccount.address.stateCode}");
					}
					if("${claimRequestConfirmationForm.asset.partnerAccount.orderPartsFlag}" == "true"){
						if(null != document.getElementById("claimTypeList")){
							claimTypeValue = document.getElementById("claimTypeList").value;
							if(claimTypeValue == "Maintenance Kit Install"){
								document.getElementById("partAndToolDiv").style.display="none";
							}
							else{
								document.getElementById("partAndToolDiv").style.display="block";
							}
							
						}
						else{
							document.getElementById("partAndToolDiv").style.display="block";
						}
						
					}else{
						document.getElementById("partAndToolDiv").style.display="none";
						clearAddress();
					}
			});

			function clearPartAndAddress(){
				partsAndToolsDebriefGrid.clearAll();
				isChooseAdifferentAddress = true;
				//isPartnerAddressGridLoaded = false;
				changeDefault("false");
			}

			function disablePartAndAddress(){
				document.getElementById("showPartsListSpan").innerHTML = '<label class="color-grey"><spring:message code="claim.label.partsList"/></label>';
				partsAndToolsDebriefGrid.clearAll();
				changeDefault("false");
				isChooseAdifferentAddress = true;
				//isPartnerAddressGridLoaded = false;
				document.getElementById("partnerAddressDiv").style.display="none";
			}

			function enablePartAndAddress(){
				document.getElementById("showPartsListSpan").innerHTML = '<a id="showPartsListLink" href="javascript:void(0);" onclick="showPartList();"><spring:message code="claim.label.partsList"/></a>';
			}

			function validateAddress(){
				var result = true;
				if(document.getElementById("activity.shipToAddress.newAddressFlag").value == ''){
					document.getElementById("activity.shipToAddress.newAddressFlag").value="false";
				}
				return result;
			}
			
			function validatePartAndTool(){
				var result = true;
				if(document.getElementById("repairCompleteFlag").value == "false"){
					resule = true;
				}else{
					var rows = partsAndToolsDebriefGrid.getRowsNum();
					for(var i = 0 ;i < rows ;i++){
						var ind = partsAndToolsDebriefGrid.getRowId(i);
						if(document.getElementById("partStatus_"+ind).value ==""){
							var errorMsg = "<spring:message code='claim.label.row'/> "+(i+1)+" <spring:message code='claim.errorMsg.partStatusRequired'/>";
							callOmnitureFieldErrorAction('Part Status');
							showError(errorMsg, null, true);
							result = false;
						}//else if(partStatusValue[document.getElementById("partStatus_"+ind).value].toLowerCase() == "used" && (document.getElementById("errorCode1_"+ind).value == "" || document.getElementById("errorCode2_"+ind).value == "")){
							//var errorMsg = "<spring:message code='claim.label.row'/> "+(i+1)+" <spring:message code='claim.errorMsg.errorCodeRequired'/>";
							//callOmnitureFieldErrorAction('Part Error Code');
							//showError(errorMsg, null, true);
							//result = false;
						//}
					}
					
				}
				return result;
			}
			
			function generatePartAndTool(){
				document.getElementById("contentParts").innerHTML = "";
				var date = new Date();
				var rows = partsAndToolsDebriefGrid.getRowsNum();
				for(var i = 0 ;i < rows ;i++){
					var ind = partsAndToolsDebriefGrid.getRowId(i);
					var qtyValue = document.getElementById("qtyDebrif_"+ind).value;
					var partNumber = partsAndToolsDebriefGrid.cellByIndex(i,2).cell.innerHTML;
					var partName = partsAndToolsDebriefGrid.cellByIndex(i,3).cell.innerHTML;
					var reRequire = partsAndToolsDebriefGrid.cellByIndex(i,7).cell.innerHTML;

					var input = document.createElement("input");
					input.id = "activity.orderPartList["+i+"].partId"; 
					input.name = "activity.orderPartList["+i+"].partId"; 
					input.value = ind;
					document.getElementById("contentParts").appendChild(input);
					
					var input = document.createElement("input");
					input.id = "activity.orderPartList["+i+"].partOrderedDate"; 
					input.name = "activity.orderPartList["+i+"].partOrderedDate"; 
					input.value = date;
					document.getElementById("contentParts").appendChild(input);
					
					var input = document.createElement("input");
					input.id = "activity.orderPartList["+i+"].quantity"; 
					input.name = "activity.orderPartList["+i+"].quantity"; 
					input.value = parseInt(qtyValue);
					document.getElementById("contentParts").appendChild(input);
					
					var input = document.createElement("input");
					input.id = "activity.orderPartList["+i+"].partNumber"; 
					input.name = "activity.orderPartList["+i+"].partNumber"; 
					input.value = partNumber;
					document.getElementById("contentParts").appendChild(input);
					
					var input = document.createElement("input");
					input.id = "activity.orderPartList["+i+"].partName"; 
					input.name = "activity.orderPartList["+i+"].partName"; 
					input.value = partName;
					document.getElementById("contentParts").appendChild(input);
					
					var input = document.createElement("input");
					input.id = "activity.orderPartList["+i+"].returnRequiredFlag"; 
					input.name = "activity.orderPartList["+i+"].returnRequiredFlag"; 
					input.value = '<spring:message code="claim.lebel.returnRequired.yes"/>'==reRequire?true:false;
					document.getElementById("contentParts").appendChild(input);
					
					if(document.getElementById("repairCompleteFlag").value == "true"){
						var statusValueKey = document.getElementById("partStatus_"+ind).value;
						var statusValue = partStatusValue[statusValueKey];

						var input = document.createElement("input");
						input.id = "activity.orderPartList["+i+"].partDisposition.value"; 
						input.name = "activity.orderPartList["+i+"].partDisposition.value"; 
						input.value = statusValue;
						document.getElementById("contentParts").appendChild(input);
						
						if(statusValue.toLowerCase() == "used"){
							var errorCode1Key = document.getElementById("errorCode1_"+ind).value;
							var errorCode2 = document.getElementById("errorCode2_"+ind).value;
							var errorCode1 = errorCode1Value[errorCode1Key];
							
							var input = document.createElement("input");
							input.id = "activity.orderPartList["+i+"].errorCode1.value"; 
							input.name = "activity.orderPartList["+i+"].errorCode1.value"; 
							input.value = errorCode1;
							document.getElementById("contentParts").appendChild(input);
						
							var input = document.createElement("input");
							input.id = "activity.orderPartList["+i+"].errorCode2.value"; 
							input.name = "activity.orderPartList["+i+"].errorCode2.value"; 
							input.value = errorCode2;
							document.getElementById("contentParts").appendChild(input);
						}
						if('<spring:message code="claim.lebel.returnRequired.yes"/>'==reRequire){
							var carrierKey = document.getElementById("reCarrier_"+ind).value;
							var reCarrier = carriersValue[carrierKey];
							var reTracingNum = document.getElementById("reTracingNum_"+ind).value;
						
							var input = document.createElement("input");
							input.id = "activity.orderPartList["+i+"].carrier.value"; 
							input.name = "activity.orderPartList["+i+"].carrier.value"; 
							input.value = reCarrier;
							document.getElementById("contentParts").appendChild(input);
						
							var input = document.createElement("input");
							input.id = "activity.orderPartList["+i+"].trackingNumber"; 
							input.name = "activity.orderPartList["+i+"].trackingNumber"; 
							input.value = reTracingNum;
							document.getElementById("contentParts").appendChild(input);
						}
					}
				}
			}
		</script>
		</c:if><!-- end of parts and tools -->
			</div><!-- portlet-wrap-inner -->
			<div class="portlet-footer">
				<div class="portlet-footer-inner"></div><!-- portlet-footer-inner -->
			</div><!-- portlet-footer -->
			<div id="contentParts" style="display:none;"></div>
		</div>
		
		<!-- Part and Address End -->
			<table width="100%"><tr><td>
			<div class="portlet-wrap" id="Additional_payment_requests" style="display:none">
				<div class="portlet-header">
					<h3><spring:message code="claim.label.additionalPaymentRequests"/></h3>
				</div><!-- portlet-header -->
				<br>
				<div class="portlet-wrap-inner" class="overflow-xAuto-yHidden" >
					<div class="columns scroll">
						<div id="gridAdditionalPaymentRequests" class="width-100per"></div>
						<div id="pagingAreaForAdditionalPayment"></div>
					</div>
					<div class="columns scroll">
						<dl>
							<dd>
								<a href="javascript:addNewPaymentRequest();" class="button" ><spring:message code="claim.button.addNew"/></a>
							</dd>
						</dl>
					</div>
				</div><!-- portlet-wrap-inner -->
				<div style="display:none" id="paymentContent"></div>
				<div class="portlet-footer">
					<div class="portlet-footer-inner"></div><!-- portlet-footer-inner -->
				</div><!-- portlet-footer -->
			</div>
			</td></tr></table>
			<script type="text/javascript">
				var additionalPaymentRequestsGrid = new dhtmlXGridObject('gridAdditionalPaymentRequests');
				additionalPaymentRequestsGrid.setImagePath("<html:imagesPath/>gridImgs/");
				additionalPaymentRequestsGrid.setHeader(autoAppendPlaceHolder('<spring:message code="claim.headerList.additionalPaymentRequests"/>,',7));
				additionalPaymentRequestsGrid.setColAlign("left,left,left,left,left,left,left");
				additionalPaymentRequestsGrid.setInitWidths("200,100,150,100,100,400,100");
				additionalPaymentRequestsGrid.setColTypes("ro,ro,ro,ro,ro,ro,ro");
				additionalPaymentRequestsGrid.setColSorting("str,str,str,str,str,str,str");
				additionalPaymentRequestsGrid.enableAutoWidth(true);
				additionalPaymentRequestsGrid.enableAutoHeight(true);
				additionalPaymentRequestsGrid.enableMultiline(true);
				additionalPaymentRequestsGrid.enablePaging(true, 9999, 6, "pagingAreaForAdditionalPayment", true);
				additionalPaymentRequestsGrid.init(); 
				additionalPaymentRequestsGrid.setSkin("light");	
				additionalPaymentRequestsGrid.setPagingSkin("bricks");
	
				function addNewPaymentRequest(){
					callOmnitureAction('Create Claim Device Selection', 'Add New Payment Request');
					var id = additionalPaymentRequestsGrid.getUID();
					var defaultCurrency = document.getElementById("defaultCurrencyHidden").value;
					var str = '<table><tr><td><select id="'+id+'_paymentType_grid" >'+ getPaymentTypeOptions() +'</select></td></tr></table>,' +
							  '<table><tr><td><input id="'+id+'_qty_grid" size="2" type="text" maxlength = "5" onblur="calculateAmount(this &#44;' + id + ')"  onafterpaste="clearNoNumAndPoint(this)" onkeyup="clearNoNumAndPoint(this)"  value=""/></td></tr></table>,' +
							  '<table><tr><td><input id="'+id+'_unitPrice_grid" size="10" maxlength = "10" type="text" onblur="calculateAmount(this &#44;' + id + ')" onafterpaste="clearNoNum(this)" onkeyup="clearNoNum(this)"   value=""/></td></tr></table>,' +
							  ''+ ',' +
							  defaultCurrency + ',' +
							  '<table><tr><td><input id="'+id+'_description_grid" size="50" type="text" onchange="validateLength(0&#44; 250&#44; this)"/></td></tr></table>,' +
							  '<a href="javascript:deleteRowInPaymentGrid(\''+id+'\');"><img class="ui-icon trash-icon" src="<html:imagesPath/>transparent.png"></a>';
					additionalPaymentRequestsGrid.addRow(id,str,0);				
				}
	
				function getPaymentTypeOptions(){
					var options = '<option value=""></option><c:forEach items="${claimRequestConfirmationForm.paymentTypes}" var="type" varStatus="loop"><option value="${type.key}">${type.value}</option></c:forEach>';
					return options;
				}
	
				function calculateAmount(ele, rowId){
					if(ele.value){
						var floatValue = parseFloat(ele.value);
						ele.value = floatValue;													
					}	
					var qty = document.getElementById(rowId+"_qty_grid").value;
					var unitPrice = document.getElementById(rowId+"_unitPrice_grid").value;
					if(qty * unitPrice){
						var amount = qty * unitPrice;
						amount = new Number(amount);
						additionalPaymentRequestsGrid.cells(rowId,3).setValue(amount.toFixed(2));
					}
				}
	
				function deleteRowInPaymentGrid(id){
					callOmnitureAction('Create Claim Device Selection', 'Delete Row In PaymentGrid');
					jConfirm("<spring:message code='claim.message.removeAdditionalPayment'/>", "", function(confirmResult) {
						if(!confirmResult){
							return;
						}else{
							additionalPaymentRequestsGrid.deleteRow(id);
						}
					});
				}

				function deleteAllRowsInPaymentGrid(){
					additionalPaymentRequestsGrid.clearAll();
				}

				function generateAdditionalPaymentFormData(){
					
					document.getElementById("paymentContent").innerHTML = "";
					var rows = additionalPaymentRequestsGrid.getRowsNum();
					for(var i = 0 ;i < rows ;i++){
						var rowId = additionalPaymentRequestsGrid.getRowId(i);
						//payment type
						var inputType = document.createElement("input");
						inputType.id = "activity.additionalPaymentRequestList["+i+"].paymentType.value"; 
						inputType.name = "activity.additionalPaymentRequestList["+i+"].paymentType.value";
						inputType.value = document.getElementById(rowId+"_paymentType_grid").value;
						document.getElementById("paymentContent").appendChild(inputType);
						//qty
						var inputQty = document.createElement("input");
						inputQty.id = "activity.additionalPaymentRequestList["+i+"].quantity"; 
						inputQty.name = "activity.additionalPaymentRequestList["+i+"].quantity";
						inputQty.value = document.getElementById(rowId+"_qty_grid").value;
						document.getElementById("paymentContent").appendChild(inputQty);
						//unit price
						var inputUnitPrice = document.createElement("input");
						inputUnitPrice.id = "activity.additionalPaymentRequestList["+i+"].unitPrice"; 
						inputUnitPrice.name = "activity.additionalPaymentRequestList["+i+"].unitPrice";
						inputUnitPrice.value = document.getElementById(rowId+"_unitPrice_grid").value;
						document.getElementById("paymentContent").appendChild(inputUnitPrice);
						//total price
						var inputTotal = document.createElement("input");
						inputTotal.id = "activity.additionalPaymentRequestList["+i+"].totalAmount"; 
						inputTotal.name = "activity.additionalPaymentRequestList["+i+"].totalAmount";
						inputTotal.value = additionalPaymentRequestsGrid.cellByIndex(i,3).cell.innerHTML;
						document.getElementById("paymentContent").appendChild(inputTotal);
						//currency
						var inputCurrency = document.createElement("input");
						inputCurrency.id = "activity.additionalPaymentRequestList["+i+"].paymentCurrency"; 
						inputCurrency.name = "activity.additionalPaymentRequestList["+i+"].paymentCurrency";
						inputCurrency.value = additionalPaymentRequestsGrid.cellByIndex(i,4).cell.innerHTML;
						document.getElementById("paymentContent").appendChild(inputCurrency);
						//description
						var inputDescription = document.createElement("input");
						inputDescription.id = "activity.additionalPaymentRequestList["+i+"].description"; 
						inputDescription.name = "activity.additionalPaymentRequestList["+i+"].description";
						inputDescription.value = document.getElementById(rowId+"_description_grid").value;
						document.getElementById("paymentContent").appendChild(inputDescription);
					}		
						
				}


				function clearNoNum(ele){
					//remove all non-digit characters, except "."
					ele.value = ele.value.replace(/[^\d.]/g,"");
					//make sure "." is not the first character
					ele.value = ele.value.replace(/^\./g,"0.");
					ele.value = ele.value.replace(/\.{2,}/g,".");
					ele.value = ele.value.replace(".","$#$").replace(/\./g,"").replace("$#$",".");
				}
				
				function clearNoNumAndPoint(ele){
					//remove all non-digit characters, include "."
					ele.value = ele.value.replace(/[^\d]/g,"");				
				}


				function validatePaymentRequests(){
					var rows = additionalPaymentRequestsGrid.getRowsNum();
					var result = true;
					for(var i = 0 ;i < rows ;i++){
						var errMsg = '<spring:message code="claim.errorMsg.additionalPaymentLine"/> ' + (i+1) + ': ';
						var currentResult = true;
						var rowId = additionalPaymentRequestsGrid.getRowId(i);
						var inputType = document.getElementById(rowId+"_paymentType_grid").value;
						var inputQty = document.getElementById(rowId+"_qty_grid").value;
						var inputUnitPrice = document.getElementById(rowId+"_unitPrice_grid").value;
						var inputDescription = document.getElementById(rowId+"_description_grid").value;
						
						if(trim(inputType) == '' || trim(inputType) == '&nbsp;'){
							currentResult = false;
							errMsg += '<spring:message code="claim.errorMsg.additionalPayment.paymentTypeNotNull"/>' + '; ';
							callOmnitureFieldErrorAction('Additional Payment Type');
						}
						if(trim(inputQty) == '' || trim(inputQty) == '&nbsp;'){
							currentResult = false;
							errMsg += '<spring:message code="claim.errorMsg.additionalPayment.qtyNotNull"/>' + '; ';
							callOmnitureFieldErrorAction('Additional Payment Quantity');
						}
						if(trim(inputUnitPrice) == '' || trim(inputUnitPrice) == '&nbsp;'){
							currentResult = false;
							errMsg += '<spring:message code="claim.errorMsg.additionalPayment.unitPrice"/>' + '; ';
							callOmnitureFieldErrorAction('Additional Payment Unit Price');
						}
						if(trim(inputDescription) == '' || trim(inputDescription) == '&nbsp;'){
							currentResult = false;
							errMsg += '<spring:message code="claim.errorMsg.additionalPayment.description"/>' + '; ';
							callOmnitureFieldErrorAction('Additional Payment Description');
						}
						if(!currentResult){
							showError(errMsg, null, true);
						}
						errMsg = '';
						result &= currentResult;
					}
					return result;
				}
			</script>
	
	

	
			<div class="portlet-wrap" id="notes">
			<div class="portlet-header">
				<h3><spring:message code="claim.label.notes"/></h3>
			</div><!-- portlet-header -->
			<div class="div-style1"><div class="div-style2"><b><spring:message code="claim.notes.helpText2"/></b></div><div class="div-style3"><b><spring:message code="claim.notes.helpText"/></b></div></div>
			<div class="portlet-wrap-inner div-style4" >
 			<!--Below Style changes under CI 7 -->
				<div class="scroll overflow-hidden" >
					<div id="gridCCSNotes" class="width-900px"></div>
					<div id="pagingNote"></div>
					<table><tr><td>
						<a href="javascript:addNotePopUp();" class="button"><spring:message code="claim.button.addNotes"/></a>
						<div class="overlayAddnotePopup"></div>
					</td></tr></table>
					<br>
				</div>
			</div><!-- portlet-wrap-inner -->
			<div class="portlet-footer">
				<div class="portlet-footer-inner"></div><!-- portlet-footer-inner -->
			</div><!-- portlet-footer -->
			</div>
			<div id="noteContent" style="display:none;"></div>
			<script type="text/javascript">
			var showNotes;
			showNotesFunction();
			
			function showNotesFunction(){
			AUI().use('aui-base',
			'aui-io-plugin-deprecated',
			'liferay-util-window',
			function(A) {

				showNotes=Liferay.Util.Window.getWindow(
				{
				dialog: {
				centered: true,
				constrain2view: true,
				//cssClass: 'yourCSSclassName',
				modal: true,
				position: [-80,20],
				resizable: false,
				width: 700,
				height: 400,
				xy: [-80, 20],
				destroyOnClose: true,
				visible: false
				}
				}
				).plug(
				A.Plugin.IO,
				{
				autoLoad: false
				})



			});
			}
			 var notesGrid = new dhtmlXGridObject('gridCCSNotes');
			 notesGrid.setImagePath("<html:imagesPath/>gridImgs/");
			 notesGrid.setHeader(autoAppendPlaceHolder(" <spring:message code='claim.headerList.notes.details'/>",5));
			 notesGrid.setColTypes("sub_row,ro,ro,ro,ro");
			 notesGrid.setColAlign("left,left,left,left,left");
			 notesGrid.setInitWidths("25,120,120,500,500");
			 notesGrid.enableAutoWidth(true);
			 notesGrid.enableAutoHeight(true);
			 notesGrid.enableMultiline(true);
			 notesGrid.init(); 
			 notesGrid.enablePaging(true, 9999, 6, "pagingNote", true);	
			 notesGrid.setSkin("light");
			 notesGrid.setPagingSkin("bricks");
			 
			 
			 
				
				function showNotesPopup(){
					jQuery(window).scrollTop(0);
					showNotes.show();
					jQuery(".aui button.close, .aui button.btn.close").hide();
					showNotes.titleNode.html("<spring:message code='claim.label.updateNote'/>");
					showNotes.io.set('uri',"${updateNoteUrl}&handleGridFlag=add");
					showNotes.io.start();
					
					}
				function addNotePopUp(){
					callOmnitureAction('Request Close Out View', 'Add Note PopUp');
					showOverlayIE6();
					showNotesPopup();
					bindingClosePopupIE6();
				}
			function addRowInNotesGrid(note){
					var subDetail = escapeStringForNoteSubRow(note[0]);
					var id = notesGrid.getUID();
					var str =['<strong><spring:message code="claim.label.note"/></strong><br><div style="word-wrap: break-word;width:80%">'+subDetail+'</div>',note[1],note[2],note[3],'<button class="button" type="button" onclick="editRow('+id+')"><spring:message code="button.edit"/></button> <input id="'+id+'_noteDetail" type="hidden" value="'+note[0]+'">'];
					notesGrid.addRow(id,str,0);
			}
			function updateRowInNotesGrid(note,rowId){
				var subDetail = escapeStringForNoteSubRow(note[0]);
				var newRowId = notesGrid.getUID();
				var str =['<strong><spring:message code="claim.label.note"/></strong><br><div style="word-wrap: break-word;width:80%">'+subDetail+'</div>',note[1],note[2],note[3],'<button class="button" type="button" onclick="editRow('+rowId+')"><spring:message code="button.edit"/></button> <input id="'+rowId+'_noteDetail" type="hidden" value="'+note[0]+'">'];
				notesGrid.addRow(newRowId,str,0);
				notesGrid.copyRowContent(newRowId,rowId);
				notesGrid.deleteRow(newRowId);
				notesGrid.cellById(rowId,0).setContent('<strong><spring:message code="claim.label.note"/></strong><br><div style="word-wrap: break-word;width:80%">'+subDetail+'</div>');
				notesGrid.cellById(rowId,0).close();
			}
			function updateNotesPopup(noteDate,noteAuthor,noteDetailId,id){
				jQuery(window).scrollTop(0);
				showNotes.show();
				jQuery(".aui button.close, .aui button.btn.close").hide();
				showNotes.titleNode.html("<spring:message code='claim.label.updateNote'/>");
				showNotes.io.set('uri',"${updateNoteUrl}&noteDate="+noteDate+"&noteAuthor="+noteAuthor+"&noteDetailId="+noteDetailId+"&rowId="+id+"&handleGridFlag=update");
				showNotes.io.start();
				
				}
			function editRow(id){
				callOmnitureAction('Request Close Out View', 'Edit Note');
				var noteDate = notesGrid.cellById(id,1).cell.innerHTML;
				var noteAuthor = notesGrid.cellById(id,2).cell.innerHTML;
				var noteDetailId = id+"_noteDetail";
				showOverlayIE6();
				updateNotesPopup(noteDate,noteAuthor,noteDetailId,id);
				bindingClosePopupIE6();
			}
			function generateNotesFormData(){
				
				document.getElementById("noteContent").innerHTML = "";
				var rows = notesGrid.getRowsNum();
				for(var i = 0 ;i < rows ;i++){
					var rowId = notesGrid.getRowId(i);
					//noteDate
					var inputDate = document.createElement("input");
					inputDate.id = "activity.activityNoteList["+i+"].noteDate"; 
					inputDate.name = "activity.activityNoteList["+i+"].noteDate";
					inputDate.value = formatDateToDefault(notesGrid.cellByIndex(i,1).cell.innerHTML);
					document.getElementById("noteContent").appendChild(inputDate);
					//noteAuthor
					var inputFirstName = document.createElement("input");
					inputFirstName.id = "activity.activityNoteList["+i+"].noteAuthor.firstName"; 
					inputFirstName.name = "activity.activityNoteList["+i+"].noteAuthor.firstName";
					inputFirstName.value = "${noteAuthorFirstName}";
					document.getElementById("noteContent").appendChild(inputFirstName);
					
					var inputLastName = document.createElement("input");
					inputLastName.id = "activity.activityNoteList["+i+"].noteAuthor.lastName"; 
					inputLastName.name = "activity.activityNoteList["+i+"].noteAuthor.lastName";
					inputLastName.value = "${noteAuthorLastName}";
					document.getElementById("noteContent").appendChild(inputLastName);

					var inputContactId = document.createElement("input");
					inputContactId.id = "activity.activityNoteList["+i+"].noteAuthor.contactId"; 
					inputContactId.name = "activity.activityNoteList["+i+"].noteAuthor.contactId";
					inputContactId.value = "${currentContactId}";
					document.getElementById("noteContent").appendChild(inputContactId);
					//noteDetail
					var inputDetail = document.createElement("input");
					inputDetail.id = "activity.activityNoteList["+i+"].noteDetails"; 
					inputDetail.name = "activity.activityNoteList["+i+"].noteDetails";
					//inputDetail.value = escapeStringForNote(document.getElementById(rowId+"_noteDetail").value);
					inputDetail.value = document.getElementById(rowId+"_noteDetail").value;
					document.getElementById("noteContent").appendChild(inputDetail);
					
				}
			} 

			function cleanNotes(){
				notesGrid.clearAll();
			}
		</script>
	
	</div><!-- end of div_gray_layout  -->
	
			
			<script type="text/javascript">
				document.getElementById('timezoneOffset').value = timezoneOffset;
				var count = 1;
				function validateBeforeSubmit(){
					showProbValidateMsg = false;
					claimTypeFlag = false;
					var result = validateCustomerContactInfo();
					result &= validateClaimDetails();
					result &= validateTechnicianInfo();
					result &= validateCloseOut();
					result &= validatePaymentRequests();
					var customerPartnerStatus = document.getElementById("CustomerPartnerStatus").value;
					
					if(customerPartnerStatus != 'CustomerPartner'){
						result &= validatePartnerAgreement(); //Added by sankha for Defect # 2258 (added in the includeDatePicker.jsp)
					}					
					result &= validateCustomerAccount();  // added in the includeDatePicker.jsp
					result &= validateStartEndLocalizedDate();//Added for CI BRD13-10-07
					if(exchangeflag==false || exchangeflag=='false'){    // added by ragesree -2098
						result &= validateAddress();
						result &= validatePartAndTool();
					}
					if(null != document.getElementById("numericCode2") && (document.getElementById("numericCode2").value=="" || numericCodeFlag==false) ){
						result &= false;
						showProbValidateMsg = true;
					}
					if(null != document.getElementById("probCode2") && document.getElementById("probCode2").value == ""){
						result &= false;
						showProbValidateMsg = true;
					}
					if(null != document.getElementById("probCode3") && document.getElementById("probCode3").value == ""){
						result &= false;
						showProbValidateMsg = true;
					}
					if(customerPartnerStatus != 'CustomerPartner'){
						if(null != document.getElementById("claimTypeList") && document.getElementById("claimTypeList").value == ""){
							claimTypeFlag = true;
							result &= false;
						}
					}
					else{
						if(null != document.getElementById("claimTypeListLASP") && document.getElementById("claimTypeListLASP").value == ""){
							claimTypeFlag = true;
							result &= false;
						}
					}
					return result;
				}
				
				//added to validate the timestamp while submitting the claim
								
		     function validateStartTime(textFieldId)
				{
		    	    var result = true;
					var text_field = document.getElementById(textFieldId).value;
					var dateTime = text_field.split(" ");
					var selectedTime = dateTime[1];
					selectedTime = selectedTime.split(":");
					var selectedHours = selectedTime[0];
					var selectedMins = selectedTime[1];
					var currentDate = new Date();					
					var currentdateTime = localizeFormatDate(new Date());
					var currentdateTimeDefault = formatDateToDefault(currentdateTime); //converting into mm/dd/yyyy format
					var selectedStartTimedfault = formatDateToDefault(dateTime[0]);
					var currentHours=currentDate.getHours();
					var currentMins=currentDate.getMinutes();
					var timeCheck = validatePastDate(currentdateTimeDefault,selectedStartTimedfault);
					if(timeCheck){
					if(selectedHours>currentHours || selectedMins>currentMins)
						{
						    result = false;													
							showError("<spring:message code='claim.popUp.errorMsg.debriefStartTimeValidation'/>", null, true);
							jQuery('#dateServicedStart').addClass('errorColor');
							return false;
						}	
					}
					return result;
				}
				
			function validatePastDate(currentdateTimeDefault, selectedStartTimedfault)
			{
				if(convertStringToDate(currentdateTimeDefault)>convertStringToDate(selectedStartTimedfault)) //Considering only the date , not time
					{
					 return false;
					}
				else
					{
					return true;
					}
			}
				
				
				
		     function validateEndTime(textFieldId)
				{
		    	    var result = true;
					var text_field = document.getElementById(textFieldId).value;
					var dateTime = text_field.split(" ");
					var selectedTime = dateTime[1];
					selectedTime = selectedTime.split(":");
					var selectedHours = selectedTime[0];
					var selectedMins = selectedTime[1];
					var currentDate = new Date();										
					var currentdateTime = localizeFormatDate(new Date());
					var currentdateTimeDefault = formatDateToDefault(currentdateTime); //converting into mm/dd/yyyy format
					var selectedEndTimedfault = formatDateToDefault(dateTime[0]);
					var timeCheck = validatePastDate(currentdateTimeDefault,selectedEndTimedfault);
					var currentHours=currentDate.getHours();
					var currentMins=currentDate.getMinutes();
					if(timeCheck){
					if(selectedHours>currentHours || selectedMins>currentMins)
						{
						    result = false;														
							showError("<spring:message code='claim.popUp.errorMsg.debriefEndTimeValidation'/>", null, true);
							jQuery('#dateServicedEnd').addClass('errorColor');
							return false;
						}	
					}
					return result;
				}
		     
		     //added 
		     //CI Defect #9332
		     
		     function validateDateServiceRequested()
				{
					var result = true;
					var dateServicedRequested =document.getElementById("dateServicedRequested").value;
					var dateServicedStart =document.getElementById("dateServicedStart").value;
					var dateServicedEnd =document.getElementById("dateServicedEnd").value;
					result = validateServiceRequestedDateTime(dateServicedRequested,dateServicedStart,dateServicedEnd);
					return result;
				}
		     
				//ends here				
				
				/* Commented for CI CR's 15.1 */
				/* function disableChooseDifferentCustomerAccountLink(){
					document.getElementById("chooseDifferentCustomerAccountSpan").innerHTML = '<label><spring:message code="claim.label.chooseDifferentCustomerAccount"/></label>';
					document.getElementById("createNewCustomerAccountSpan").innerHTML = '<label><spring:message code="claim.label.createNewCustomerAccount"/></label>';
				}

				function enableChooseDifferentCustomerAccountLink(){
					document.getElementById("chooseDifferentCustomerAccountSpan").innerHTML = '<a href="javascript:void(0);" onclick="showCustomerAccountInfoPop(\'divCustomerInfo2\');"><spring:message code="claim.label.chooseDifferentCustomerAccount"/></a>';
					document.getElementById("createNewCustomerAccountSpan").innerHTML = '<a href="javascript:void(0);"  onclick="createNewCustomerAccountHiddenShow()"><spring:message code="claim.label.createNewCustomerAccount" /></a>';
				} */
				/* END */
				function checkQTY(){
					
					if(exchangeflag=='false' || exchangeflag==false){
						if(checkTotalQTY()){
						
							jiConfirm('<spring:message code="claim.label.submitMoreThan5"/>','','<spring:message code="button.submitClaim" />','<spring:message code="button.goBack" />','left',function(iconfirmResult){
								if(iconfirmResult){
						
									confirmSubmitCreateClaim();
								}
							});
						}else{
						
							confirmSubmitCreateClaim();
						}
						
					}else{
						
						confirmSubmitCreateClaim();
					}
				}
				
				
				function submitFormCreateClaim(){
					document.getElementById("pageSubmitType").value = 'submitClaimRequest'; //Added for CI BRD13-10-01
					
					callOmnitureAction('Create Claim Device Selection', 'Submit');
					removeErrorClass();
					generateAdditionalPaymentFormData();
					validateCustomerContactInfo();
					generateNotesFormData();
					
					clearMessage();
					
					if(validateBeforeSubmit() != 0){
						//changeDateFormat();commented for LEX:AIR00076932
							checkQTY();
					}else{
						if(showProbValidateMsg){
							showError("<spring:message code='claim.errorMsg.problemCodeNotNull'/>", null, true);
							window.scroll(0,0);
						}
						if(claimTypeFlag){
							showError("Please Enter Claim Type", null, true);
							window.scroll(0,0);
						}
						window.scroll(0,0);
					}	
				}


				function changeDateFormat(){
					var serviceStart = document.getElementById('dateServicedStart').value;
					var serviceEnd = document.getElementById('dateServicedEnd').value;
					//Added for CI BRD13-10-07 --STARTS
					var servicedRequested = document.getElementById('dateServicedRequested').value;
					if(trim(servicedRequested)){
						document.getElementById('dateServicedRequested').value = formatDateToDefault(servicedRequested);
					}
					//Added for CI BRD13-10-07 --ENDS
					if(trim(serviceStart)){
						document.getElementById('dateServicedStart').value = formatDateToDefault(serviceStart);
					}
					if(trim(serviceEnd)){
						document.getElementById('dateServicedEnd').value = formatDateToDefault(serviceEnd);
					}
				}

				function disableAllInputAndButtons(){
					document.getElementById('div_gray_layout').style.color="DarkGray";
					
					var inputs = document.getElementsByTagName('input');
					for(var i=0; i<inputs.length; i++){
						if(inputs[i].type != 'hidden'){
							inputs[i].disabled='true';
							if(inputs[i].type == 'text'){
								inputs[i].value = '';
							}
						}
					}

					var selects = document.getElementsByTagName('select');
					for(i=0; i<selects.length; i++){
						if(selects[i].id != 'selectPartnerAgreement' && selects[i].id != 'claimTypeList' &&
						   selects[i].name != '_82_languageId'){    //language select on the top of the page
							selects[i].disabled='true';
							selects[i].value='';
						}
					}

					var buttons = document.getElementsByTagName('button');
					for(i=0; i<buttons.length; i++){
						if(buttons[i].id=='cancel_button'){
						}else{
							buttons[i].disabled='true';
						}
					}

					var textareas = document.getElementsByTagName('textarea');
					for(i=0; i<textareas.length; i++){
						textareas[i].disabled='true';
						textareas[i].value='';
					}
					cleanTechnicians();
					cleanClaimDetails();
					cleanRepairComplete();
					cleanNotes();				
					if(exchangeflag==false || exchangeflag=='false'){			
						disablePartAndAddress();
					}
					/* Commented for CI CR's 15.1 */
					//disableChooseDifferentCustomerAccountLink();
					/* END */
				}

				function enableAllInputAndButtons(){
					document.getElementById('div_gray_layout').style.color="";
					
					var inputs = document.getElementsByTagName('input');
					for(var i=0; i<inputs.length; i++){
						inputs[i].disabled='';
					}

					var selects = document.getElementsByTagName('select');
					for(i=0; i<selects.length; i++){
						selects[i].disabled='';
					}

					var buttons = document.getElementsByTagName('button');
					for(i=0; i<buttons.length; i++){
						buttons[i].disabled='';
					}

					var textareas = document.getElementsByTagName('textarea');
					for(i=0; i<textareas.length; i++){
						textareas[i].disabled='';
					}

					//enablePartAndAddress();
					if(exchangeflag==false || exchangeflag=='false'){           //added by ragesree -2098
						enablePartAndAddress();
					}
					/* Commented for CI CR's 15.1 */
					//enableChooseDifferentCustomerAccountLink();
					/* END */
				}


				function cleanAllInputs(){
					var inputs = document.getElementsByTagName('input');
					for(var i=0; i<inputs.length; i++){
						if(inputs[i].type == 'text'){
							inputs[i].value = '';
						}
					}
					
					var selects = document.getElementsByTagName('select');
					for(i=0; i<selects.length; i++){
						if(selects[i].id != 'selectPartnerAgreement' && selects[i].id !='claimTypeList'){
							selects[i].value='';
						}
					}

					var textareas = document.getElementsByTagName('textarea');
					for(i=0; i<textareas.length; i++){
						textareas[i].value='';
					}
					cleanTechnicians();
					cleanClaimDetails();
					cleanRepairComplete();
					cleanNotes();
					//clearPartAndAddress();
					//added by ragesree -2098
					if(exchangeflag==false || exchangeflag=='false'){
						clearPartAndAddress();
					}
					//end
				}

				function confirmSubmitCreateClaim(){
					
					jConfirm("<spring:message code='claim.message.confirmSubmitCreateClaim'/>", "", function(confirmResult) {
						
						if(confirmResult){
						
							if(exchangeflag=='false' || exchangeflag==false){
								if(null != document.getElementById("claimTypeList")){
									claimTypeValue = document.getElementById("claimTypeList").value;
									if(claimTypeValue != "Maintenance Kit Install"){
										generatePartAndTool();
									}
								}
								else{
									generatePartAndTool();
								}
								
								
							}
						
							var selectProbCode = "";
							var selectprobcode1 = document.getElementById("problemCode").value;	
							selectProbCode = selectprobcode1;
							if(selectprobcode1 == 'Error Code Displayed'){
								var numericCode2 = document.getElementById("numericCode2").value;
								selectProbCode = selectprobcode1+";"+numericCode2;
							}
							else{
							if(null != document.getElementById("probCode2") && document.getElementById("probCode2").value != ""){
								var selectprobcode2 = document.getElementById("probCode2").value;
								selectProbCode = selectprobcode2;
							}
							
							if(null != document.getElementById("probCode3") && document.getElementById("probCode3").value != ""){
								var selectprobcode3 = document.getElementById("probCode3").value;
								selectProbCode = selectprobcode3;
							}
						}
							jQuery('#problemCodes').val(selectProbCode);
							if("${duplicateDevice}"=="true"){
								document.getElementById("activity.requestLexmarkReviewFlag").checked = true;
							}
							var customerPartnerStatus = document.getElementById("CustomerPartnerStatus").value;
							if(customerPartnerStatus != 'CustomerPartner'){
								if(null != document.getElementById("claimTypeList")){
									var claimType = document.getElementById("claimTypeList").value;
									jQuery('#claimType').val(claimType)
								}
							}
							else{
								if(null != document.getElementById("claimTypeListLASP")){
									var claimType = document.getElementById("claimTypeListLASP").value;
									jQuery('#claimType').val(claimType)
								}
							}																				
						
							jQuery("#createClaimRequestForm").submit();
						}else{
						
							return;
						}
					});
				}
				//Added for CI 13.10 BRD13-10-27 Starts
				function validateStartEndLocalizedDate()
				{
					var fromDate=jQuery("#dateServicedStart").val();
					var result = true;
				    var toDate=jQuery("#dateServicedEnd").val();
				    var serviceStart = document.getElementById('dateServicedStart').value;
					var serviceEnd = document.getElementById('dateServicedEnd').value;	
				    var deFlag = false;
		    		var startDate;
		    		var endDate;
		    		var today;
		    		var tgtSeperator = dateFormat.substring(2,3);
		    		var today = convertDateFormat("mm/dd/yyyy","/", todayStr, dateFormat, tgtSeperator);
			    	if(fromDate.substring(2,3)=="."){
			    		startDate = convertStringToDate(convertDateFormat("dd.mm.yyyy",".",fromDate, "mm/dd/yyyy","/"));
			    		endDate = convertStringToDate(convertDateFormat("dd.mm.yyyy",".",toDate, "mm/dd/yyyy","/"));
			    		today = convertStringToDate(convertDateFormat("dd.mm.yyyy",".",today, "mm/dd/yyyy","/"));
			    		deFlag =true;
			    	}
			    	
			    	if(!deFlag){
			    	fromDat_converted = convertStringToDate((convertDateFormat(dateFormat, tgtSeperator, fromDate, "mm/dd/yyyy", "/")));
			    	endDat_converted = convertStringToDate((convertDateFormat(dateFormat, tgtSeperator, toDate, "mm/dd/yyyy", "/")));
			    	}
			    	else{
				    	fromDat_converted = startDate;
			    		endDat_converted = endDate;
			    	}
			    	if(endDat_converted > today){
			    		result=false;
			    		showError("<spring:message code='claim.errorMsg.dateServicedEndViolation'/>",null, true);
						jQuery('#dateServicedEnd').addClass('errorColor');
					}
			    	
			    	return result;
				}
				//Added for CI13.10 BRD13-10-27 Ends
				function cancelCreateClaim(){
					callOmnitureAction('Create Claim Device Selection', 'Cancel');
					jConfirm("<spring:message code='claim.message.cancelCreateClaim'/>", "", function(confirmResult) {
						if(!confirmResult){
							return;
						}else{
							window.location.href = "${requestListUrl}";
						}
					});
				}

				jQuery(document).ready(function(){
					notesGrid.loadXMLString("<rows></rows>");
					var Flag = document.getElementById("CustomerPartnerStatus").value;
					if(Flag == 'Nothing' || Flag == 'Customer'){
						if(${condition}=="1"){							
							enableAllInputAndButtons()
							document.getElementById("selectPartnerAgreement").disabled=true;
							
						}
						else{
							disableAllInputAndButtons();
						}
						
					}
				});
					
				
			</script>
		</div>	
	
</form:form>
 <!-- Added for BRD13-10-01 STARTS-->
			     <div id="modaldialogbox" class="modal-dialogbox1" >
			     
    								<div class="columnInner" id="loadingDialog"><div class="columnsTwo w50"><img src="/LexmarkPartnerPortal/images/green-check.png" class="green-check-img" ></img></div>
    								<div class="columnsTwo w70p">
      									<spring:message code="saveAsDraft.popup"/> ${srNumber} <spring:message code="saveAsDraft.popup.lastPart"/>
      								</div>
      								<div class="buttonContainer popupBtnCont border-top-0" > <!-- Style added for CI BRD 13-10-01-->
      								<a href="javascript:onOkClick();" class="button_cancel" id="draftPopupOk"><spring:message code="button.ok"/></a>
      								</div>
      								</div>
      								
				</div>
<!-- Added for BRD13-10-01 ENDS-->
<!-- File Attachment start -->
	
		
				      
				  <table width="98%" class="margin-left-10px">
				    <tr>
				      <td>
				      <form:form id="attachmentForm" name="attachmentForm" commandName="attachmentFormDisplay" method="post" enctype="multipart/form-data">
				        <div class="portlet-wrap" id="attachmentBorder">
				        	<div class="portlet-header">
								<h3><spring:message code="claim.label.attachments" /></h3>
							</div>
							
							<div id="browseAttachmentFileId" class="portlet-wrap-inner">
								<table class="importTable" width="100%" border="0">
									<tr class="import-table-tr1">
										<td>
											<b><LABEL class="labelBold" for="fileInput"><spring:message code="fileAttachment.label.fileToUpload" /></LABEL>:&nbsp;</b>
										</td>
										<td>
											<nobr>
												<input type="text" id="txtFilePath" size="50" readonly="true" />&nbsp;
												<a href="#" class="button"><span><spring:message code="button.browse"/></span></a>&nbsp;
												<form:input type="file" size="1" id="fileInput" class="requestsUploader" path="fileData" onchange="document.getElementById('txtFilePath').value = getFileName();" style="z-index: 2; position: relative; filter: alpha(opacity = 0); opacity: 0; width: 80px; left: 0px !important; top: 0px !important; margin-left: -75px !important;" /> &nbsp;
												<a href="javascript:uploadFile();" class="button" ><span><spring:message code="button.uploadFile"/></span></a>
											</nobr>		   
										</td>
									</tr>
									
							        
									<tr class="import-table-tr1"><td></td><td><nobr><b><spring:message code="attachment.messgae.FileRestriction"/></b></nobr></td></tr>
									<tr class="import-table-tr1"><td></td><td><nobr><b><table id="errorMessageTable"></table></b></nobr></td></tr>
									<script type="text/javascript">
									
									//Added for CI BRD13-10-01 --STARTS
									function onOkClick() {																														
										window.location.href = "/group/partner-portal/services-requests?requestTyepeis=Draft&srNumber="+"${srNumber}";
									};
									//Added for CI BRD13-10-01 --ENDS
									
							        var errorMessageText;
				                    var attachmentErrorMsg="${attachmentFormDisplay.errorMessage}";
				                    jQuery('#errorMessageTable',window.parent.document).empty();
				                    errorMessageText ='<thead><tr><th><font color="RED">';
				                    if(attachmentErrorMsg=='attachment.maxFileSize.message'){
				                    	errorMessageText=errorMessageText+' <spring:message code="attachment.maxFileSize.message"/>';
				                    }
				                    if(attachmentErrorMsg=='attachment.duplicateFile.message'){
				                    	errorMessageText=errorMessageText+'<spring:message code="attachment.duplicateFile.message"/>';
				                    }
				                    if(attachmentErrorMsg=='attachment.maxNumberOfFiles.message'){
				                    	errorMessageText=errorMessageText+'<spring:message code="attachment.maxNumberOfFiles.message"/>';
				                    }
				                    if(attachmentErrorMsg=='attachment.FileType.message'){
				                    	errorMessageText=errorMessageText+' <spring:message code="attachment.FileType.message"/>';
				                    }
				                    errorMessageText=errorMessageText+'</font></th></tr></thead>';
									
									jQuery('#errorMessageTable',window.parent.document).append(errorMessageText);
							        </script>
							        
							        
							        </table>
							        </div>
														
							<div id='uploadedFileDIV' class="portlet-wrap-inner">
							<table id="fileListTable"></table>
							<!-- 
								<div id='uploadedFileGrid' style="width:100%;"></div>
								<div id="loadingAttachmentNote" class="gridLoading" style=" display:none;"><spring:message code="label.loadingNotification"/>&nbsp;
									<img src="<html:imagesPath/>gridloading.gif" /><br>
								</div>
								-->
								
								
							</div>
							<div class="portlet-footer">
								<div class="portlet-footer-inner"></div>
							</div>
				        </div>
				     	<iframe id="upload_target" name="upload_target" class="iframe-upload-target"></iframe>
				        
				        <script type="text/javascript">
				        
				     <c:if test="${attachmentFormDisplay.attachmentList != null}">
				     jQuery('#fileListTable',window.parent.document).empty();
				     var responseText = '<thead><tr class="tableHeader"><b><th class="width60"><spring:message code="attachment.message.FileName"/></th><th class="width35"><spring:message code="attachment.message.FileSize"/></th></b></tr></thead>';
						   responseText = responseText + '<tbody>';
							<c:forEach items="${attachmentFormDisplay.attachmentList}" var="entry">
					 			responseText = responseText + '<tr class="tableContentColor">';
					 			responseText = responseText + '<td class="width60">'+ '<a href="#" onClick=\'openFile("${entry.attachmentName}");\'><c:out value="${entry.displayAttachmentName}" /></a>';
					 			responseText = responseText + '<img id="img_help1" class="ui-icon trash-icon" src="<html:imagesPath/>transparent.png" style="cursor:pointer;" title="Delete" alt="Remove" onclick=\'deleteFile("${entry.attachmentName}");\' /></td>';
					 			responseText = responseText + '<td align="center" class="width35">'+ '<c:out value="${entry.sizeForDisplay}" />' + '</td>';
					 			responseText = responseText + '</tr>';					 			
					 		</c:forEach>
							responseText = responseText + '</tbody>';
							jQuery('#fileListTable',window.parent.document).append(responseText);
							jQuery('#txtFilePath',window.parent.document).val('');//Used to remove the filename when the upload is done
					</c:if>
					
					<c:if test="${attachmentFormDisplay.attachmentList == null}">
						jQuery('#fileListTable',window.parent.document).empty();
					</c:if>


					jQuery(document).ready(function() {
						jQuery("#fileInput").css({"cursor":"pointer"});
					jQuery("#processingHint",window.parent.document).css({
					               display:"none"
					       });
					       jQuery("#overlay",window.parent.document).css({
					               display:"none"
					       });
					});

						function uploadFile() {
							var filePath = document.getElementById('txtFilePath').value;
							
							if(filePath != '') {
							
								document.getElementById('attachmentForm').target = 'upload_target';
							
								var link = "${addAttachmentsCreate}";
							
								document.getElementById('attachmentForm').action = link;
							
								//jQuery('#attachmentForm').submit();
								eval(document.attachmentForm).submit();
							}
						  
						};

						function deleteFile(fileName) {
							// call action in controller to delete file
							var link = '${removeAttachmentURL}';
							document.getElementById('attachmentForm').target = 'upload_target';
							document.getElementById('attachmentForm').action = link + "&fileName=" + fileName;
							// jQuery('#attachmentForm').submit();
							eval(document.attachmentForm).submit();
							};

						function openFile(fileNm) {
							// call action in controller to open file
							
						};

						function getFileName() {
							var path = document.getElementById('fileInput').value;
								if(path.match(/fakepath/)) {
									path = path.replace(/C:\\fakepath\\/i, '');
							    }
							return path;
						};

						function removeErrorClass(){
							jQuery('#selectPartnerAgreement').removeClass('errorColor');
							jQuery('#customerAccount2').removeClass('errorColor');
							jQuery('#customerAddress2').removeClass('errorColor');
							jQuery('#activity\\.serviceRequest\\.primaryContact\\.firstName').removeClass('errorColor');
							jQuery('#activity\\.serviceRequest\\.primaryContact\\.lastName').removeClass('errorColor');
							jQuery('#activity\\.serviceRequest\\.primaryContact\\.workPhone').removeClass('errorColor');
							jQuery('#problemCode').removeClass('errorColor');
							jQuery('#activity\\.debrief\\.repairDescription').removeClass('errorColor');
							jQuery('#resolutionCode').removeClass('errorColor');
							jQuery('#dateServicedRequested').removeClass('errorColor'); //Added for CI BRD13-10-07
							jQuery('#dateServicedStart').removeClass('errorColor');
							jQuery('#dateServicedEnd').removeClass('errorColor');
							jQuery('#printerWorkingCondition').removeClass('errorColor');
							jQuery('#technicianChoose').removeClass('errorColor');
							jQuery('#technicianFirstNameInput').removeClass('errorColor');
							jQuery('#dateServicedStart').removeClass('errorColor'); //Added for CI BRD13-10-07 --Release 13.10
							jQuery('#dateServicedEnd').removeClass('errorColor');//Added for CI BRD13-10-07 --Release 13.10
						}
						
						/*********Added***********/
						/*******************Method for opening the popup for all kinds of address***************/
						function popupAddress(hyperlinkId){
									var createNewFlag = document.getElementById("partnerAccountCreateNewAddressFlagHidden").value;
									if(createNewFlag == null || createNewFlag == "")
										{
										createNewFlag = "false";
										}
									
									linkId=hyperlinkId;//This link id is for displaying inner htmls in address
									showOverlay();
									var url="${showAddressPopup}"+"&createNewFlag="+createNewFlag;
									
									jQuery('#dialogAddress').load(url,function(){
										
										dialog=jQuery('#content_addr').dialog({
											autoOpen: false,
											title: jQuery('#'+hyperlinkId).attr('title'),
											modal: true,
											draggable: false,
											resizable: false,
											height: 500,
											width: 950,
											close: function(event,ui){
												hideOverlay();
												dialog=null;
												jQuery('#content_addr').remove();
												}
											});
										jQuery(document).scrollTop(0);
										dialog.dialog('open');
										initializeAddressGrid();
										if (window.PIE) {
								            document.getElementById("createNewAddressButton").fireEvent("onmove");
								            document.getElementById("cancelAddressButton").fireEvent("onmove");
								        }
										});
									return false;
								}
							function addPickupAddress(ai,storeName,currentFavStatus, 
									  an, al1, offcNo, al2,city,county, state, province,region,country, zip,
									  physicalLoc1,physicalLoc2,physicalLoc3,district,countryISO,stateCode)
						
							{	
								if(province == null){
									province = '';
								}
								//alert('addressId is '+addressId);
								//alert('addressProvince '+addressProvince);
								if(ai==null){
									ai = '-1';
								}
									
								//alert('addressId is '+addressId);
								jQuery("#pickupAddressId").val(ai);
								jQuery("#pickupStoreFrontName").val(storeName);
								jQuery("#pickupAddressLine1").val(al1);
								jQuery("#pickupAddresscounty").val(county);
								jQuery("#pickupAddressofficeNumber").val(offcNo);
								jQuery("#pickupAddressLine2").val(al2);	
								jQuery("#pickupAddressCity").val(city);
								jQuery("#pickupAddressState").val(state);
								jQuery("#pickupAddressProvince").val(province);
								jQuery("#pickupAddressPostCode").val(zip);
								jQuery("#pickupAddressCountry").val(country);
								
								jQuery("#pickupAddressdistrict").val(district);
								jQuery("#pickupAddressregion").val(region);
								jQuery("#pickupAddresscountryISOCode").val(countryISO);
								
								if(storeName!="null"){
									jQuery("#pickupStoreFrontNameLbl").html(storeName);
									jQuery("#pickupStoreFrontNameLbl").show();
									}
								if(storeName!=null ||storeName!=' '){
									jQuery("#pickupStoreFrontNameLbl").html(storeName);
									jQuery("#pickupStoreFrontNameLbl").show();
									}
								if(al1!=' ' || al1!=''){
									jQuery("#addressLine1Label").html(al1);
									jQuery("#addressLine1Label").show();
								}
								if(al1!=null){
									jQuery("#addressLine1Label").html(al1);
									jQuery("#addressLine1Label").show();
								}
								if(offcNo!=' '||offcNo!=''){
									jQuery("#pickupAddressofficeNumberLbl").html(offcNo);
									jQuery("#pickupAddressofficeNumberLbl").show();
								}
								if(offcNo!=null){
									jQuery("#pickupAddressofficeNumberLbl").html(offcNo);
									jQuery("#pickupAddressofficeNumberLbl").show();
								}
								if(al2!=' '||al2!=''||al2!=null){
									jQuery("#addressLine2Label").html(al2);
									jQuery("#addressLine2Label").show();
								}
								//Changes done for CI Defect #9183--Starts 
								if(city !='' || city !=' ' || city != null){
								jQuery("#cityLabel").html(city);
								jQuery("#cityLabel").show();
								}
								if(county !=' '||county !=''||county !=null){
								jQuery("#pickupAddresscountyLbl").html(', '+county);
								jQuery("#pickupAddresscountyLbl").show();
								}
								if(state!=' '||state!=''||state!=null)
									{
									jQuery("#stateLabel").html(', '+state);
									jQuery("#stateLabel").show();
									}
								if(province !="null"){
									jQuery("#provinceLabel").html(', '+province);
									jQuery("#provinceLabel").show();
								}
								if(province !=' '||province!=''||province!=null){
									jQuery("#provinceLabel").html(', '+province);
									jQuery("#provinceLabel").show();
								}
								if(district !=' ' || district !='' || district !=null){
									jQuery("#pickupAddressRegionLB").html(', '+district);
									jQuery("#pickupAddressRegionLB").show();
									}
								if(zip !=' ' || zip !=''||zip!=null){
									jQuery("#postalCodeLabel").html(zip);
									jQuery("#postalCodeLabel").show();
									}	
								if(country !=' ' || country !=''||country !=null){
									jQuery("#countryLabel").html(country);
									jQuery("#countryLabel").show();
									}	
								//This is to hide if values are blank or null
								if(storeName==null ||storeName==' '){
									jQuery("#pickupStoreFrontNameLbl").hide();
									}
								if(storeName=="null"){
									jQuery("#pickupStoreFrontNameLbl").hide();
									}
								if(al1==null){
									jQuery("#addressLine1Label").hide();
								}
								if(al1==' ' || al1==''){
									jQuery("#addressLine1Label").hide();
								}
								if(offcNo==' '||offcNo==''){
									jQuery("#pickupAddressofficeNumberLbl").hide();
								}
								if(offcNo==null){
									jQuery("#pickupAddressofficeNumberLbl").hide();
								}
								if(al2==' '||al2==''||al2=="null"){
									jQuery("#addressLine2Label").hide();
								}
								if(city =='' || city ==' ' || city == null){
									jQuery("#cityLabel").hide();
									}
								if(county ==' '||county ==''||county ==null){
									jQuery("#pickupAddresscountyLbl").hide();
									}
								if(state==' '||state==''||state==null)
								{
								jQuery("#stateLabel").hide();
								}
								if(province =="null"){
									jQuery("#provinceLabel").hide();
								}
								if(district ==' ' || district =='' || district ==null){
									jQuery("#pickupAddressRegionLB").hide();
									}
								if(district =="undefined"){
									jQuery("#pickupAddressRegionLB").hide();
									}
								if(zip ==' ' || zip ==''||zip==null){
									jQuery("#postalCodeLabel").hide();
									}	
								if(country ==' ' || country ==''||country ==null){
									jQuery("#countryLabel").hide();
									}	
								//Changes done for CI Defect #9183--ENDS
								//Done for CI Address comma issue-ENDS


							}
							//Changes done for CI BRD 13-10-08 STARTS
							function addRestFieldsOfCleanseAddress(){
								jQuery('#pickupAddressisAddressCleansed').val("true");
 								
								<%for(String cleanseOutputFields:ADDRESSCLEANSEFIELDS_OUTPUT){%>
									jQuery("#pickupAddress<%=cleanseOutputFields%>").val(cleaseAddressFields["<%=cleanseOutputFields%>"]);
								<%}%>
								jQuery('#pickupAddressofficeNumberLbl').html(jQuery('#pickupAddressofficeNumber').val());
								//Start Added for MPS 2.1
								jQuery("#pickupAddresscountyLbl").html(jQuery('#pickupAddresscounty').val());
								jQuery("#pickupAddresscountyLbl").show();
								if(jQuery('#pickupAddresscounty').val() =='' || jQuery('#pickupAddresscounty').val() == null){
									jQuery("#pickupAddresscountyLbl").hide();
									}
								jQuery("#pickupAddressdistrictLbl").html(jQuery('#pickupAddressdistrict').val());
								//END
								}
							//Changes done for CI BRD 13-10-08 ENDS
						
						function closeDialog()
						{
								if(dialog !=null){
									dialog.dialog('close');
									dialog=null;
									jQuery('#dialog').html("");	
								}
								
						}
						/********************End*******************/
						
						// Added for CI BRD13-10-01 --STARTS
						var draftConfirmation = "${draftConfirmation}";
						if(draftConfirmation == "draftConfirmation"){
							popupDialogDraft();
							
						}else{
							
							document.getElementById("modaldialogbox").style.display = 'none';
						}

						function popupDialogDraft(){
							dialog = jQuery("#modaldialogbox").dialog({
								autoOpen: false,
								open: function() {jQuery(".ui-dialog-titlebar-close").hide();jQuery('#modaldialogbox').show();},
								modal: true,
								closeOnEscape: false,
								title: '<spring:message code="requestInfo.message.draftSaved"/>',
								resizable: false,
								position: "center",
								width: 450,
								height: 400
								
								});

							
							dialog.dialog("open");
							
							if (window.PIE) {
						        document.getElementById("draftPopupOk").fireEvent("onmove");
						    }
						    
							return false;
						}
						</script>
						</form:form>
					  </td>
				  </tr>
			  </table>
			  <table width="98%" class="margin-left-10px">
			  	<tr>
			  		<td>
			  			<form:form id="dummyForm" name="dummyForm" method="post">
			  				<table width="98%">
								<tr>
									<td>
										<div class="padding-left-20px">
				<a href="javascript:submitFormCreateClaim();" class="button"><span><spring:message code="button.submit"/></span></a>&nbsp;&nbsp;<a href="javascript:cancelCreateClaim()" class="button_cancel" id="cancel_button"><span><spring:message code="button.cancel"/></span></a>
			 	 </div>
									</td>
								</tr>
							</table>
			  			</form:form>
			  		</td>	
			  	</tr>
			  </table>	 
			      
				        <!-- File Attachment end -->
	
</div>

<script>

var showSRPopup;
showSRPopupFunction();

function showSRPopupFunction(){
AUI().use('aui-base',
'aui-io-plugin-deprecated',
'liferay-util-window',
function(A) {

	showSRPopup=Liferay.Util.Window.getWindow(
	{
	dialog: {
	centered: true,
	constrain2view: true,
	modal: true,
	position: [-80,20],
	resizable: false,
	width: 900,
	height: 700,
	xy: [-80, 20],
	destroyOnClose: true,
	visible: false
	}
	}
	).plug(
	A.Plugin.IO,
	{
	autoLoad: false
	})



});
}
</script>


<script>

// script added for Problem Description CI 15.4 start
	function getprobCode(problemCode,problemCodeLevel){
		if(problemCodeLevel == '1'){
			jQuery('#problemCode3Div').hide();
			jQuery('#showProbErrorCode3').hide();
			if(null != document.getElementById("probCode3")){
				jQuery('#probCode3').remove();					
			}
			if(problemCode == ""){				
				if(null != document.getElementById("probCode2")){
					jQuery('#probCode2').remove();
				}
				jQuery('#problemCode2Div').hide();
				jQuery('#showProbErrorCode2').hide();
			}
			else{
					if(problemCode.indexOf("Error Code Displayed") > -1){	
						if(null != document.getElementById("probCode2")){
							jQuery('#probCode2').remove();
						}						
						jQuery('#problemCode2Div').show();
						jQuery('#showProbErrorCode2').show();
						jQuery('#showProbErrorCode2').html("");
						jQuery('#showProbErrorCode2').html("<input type=\"textarea\" id=\"numericCode2\" onkeyup=\"validateNumericCode();\" \><span id=\"errorMsgNumCode\" class=\"errorColor\" style=\"display: none; color: red\"><B></B></span");
					}
				else{
					if(null != document.getElementById("numericCode2")){
						jQuery('#numericCode2').remove();
					}
					doAjaxCallForProbCode(problemCode,problemCodeLevel);			
					}
			}
		}		
		
		if(problemCodeLevel == '2'){
			if(problemCode == ""){
				if(null != document.getElementById("probCode3")){
					jQuery('#probCode3').remove();
				}
				jQuery('#problemCode3Div').hide();
				jQuery('#showProbErrorCode3').hide();
				
			}
			else{
				doAjaxCallForProbCode(problemCode,problemCodeLevel);
			}
		}
		
	}
	
	 function doAjaxCallForProbCode(probCode,problemCodeLevel){
		showOverlay();
		
		jQuery.ajax({
			url:"<portlet:resourceURL id='retriveProblemCode'></portlet:resourceURL>",
			data:{
					problemCode : probCode,
					problemCodeLevel : problemCodeLevel
				 },
			type:'POST',
			dataType: 'JSON',		
			success: function(results){
				hideOverlay();
				var resultJSON=results;
				if(resultJSON !=null){ 
					if(resultJSON.problCode !=null){
						if(problemCodeLevel == '1'){
							if(resultJSON.problCode != ""){
								jQuery('#problemCode2Div').show();
								jQuery('#showProbErrorCode2').show();
								jQuery('#showProbErrorCode2').html("");
								jQuery('#showProbErrorCode2').html(resultJSON.problCode);
								
							}
							else{	
								if(null != document.getElementById("probCode2")){
									jQuery('#probCode2').remove();					
								}
								jQuery('#problemCode2Div').hide();
								jQuery('#showProbErrorCode2').hide();
							}	
						}
						if(problemCodeLevel == '2'){
							if(resultJSON.problCode != ""){
								jQuery('#problemCode3Div').show();
								jQuery('#showProbErrorCode3').show();
								jQuery('#showProbErrorCode3').html("");
								jQuery('#showProbErrorCode3').html(resultJSON.problCode);
							}
							else{
								if(null != document.getElementById("probCode3")){
									jQuery('#probCode3').remove();					
								}
								jQuery('#problemCode3Div').hide();
								jQuery('#showProbErrorCode3').hide();
							}		
						}										
						
					}			
				}
			}
			});
	}
	 function validateNumericCode(){
		 var errorNumericCode2 = document.getElementById("numericCode2").value;
		
		 var isNotNumber = isNaN(errorNumericCode2);
		 if(isNotNumber==false){
			 if(errorNumericCode2.indexOf('.') > -1 && ((errorNumericCode2.indexOf('.') == 2 && errorNumericCode2.length == 5) || (errorNumericCode2.indexOf('.') == 3 && errorNumericCode2.length == 6 ))){
				 doAjaxForNumericCode(errorNumericCode2);
				 
			 }
			 else{
				 numericCodeFlag=false;
				 jQuery('#errorMsgNumCode').html("");
				 jQuery('#errorMsgNumCode').html("<B><spring:message code='debrief.Numeric.ProblemCode.ValidationMsg'/></B>");
				 document.getElementById("errorMsgNumCode").style.display = 'block';
			 }
			
		 }
		 else{
			 numericCodeFlag=false;
			 jQuery('#errorMsgNumCode').html("");
				// jQuery('#errorMsgNumCode').html("<spring:message code='claim.message.confirmSubmitCreateClaim'/>");
				 jQuery('#errorMsgNumCode').html("<B><spring:message code='debrief.Numeric.ProblemCode.ValidationMsg'/></B>");
				 document.getElementById("errorMsgNumCode").style.display = 'block';
		 }
		 if(errorNumericCode2 == ""){
			 jQuery('#errorMsgNumCode').hide();
		 }
	 }
	 
	 function doAjaxForNumericCode(errorNumericCode2){
		 showOverlay();
		 jQuery.ajax({
				url:"<portlet:resourceURL id='retriveNumericCode2'></portlet:resourceURL>",
				data:{
					problemCode : "Error Code Displayed",
					errorNumericCode2 : errorNumericCode2
					 },
				type:'POST',
				dataType: 'JSON',		
				success: function(results){
					hideOverlay();
					var resultJSON=results;
					if(resultJSON !=null){ 
						if(resultJSON.message == "Numeric Code Not Found"){
							numericCodeFlag = false;
							 jQuery('#errorMsgNumCode').html("<B><spring:message code='debrief.Numeric.ProblemCode.NotFound'/></B>");
							document.getElementById("errorMsgNumCode").style.display = 'block';
							//jQuery('#errorMsgNumCode').show();		
							
							
						}
						else{
							numericCodeFlag=true;
							document.getElementById("errorMsgNumCode").style.display = 'none';
						}
					}
				}
				});
	 }
	 
	
	 
	
	// script added for Problem Description CI 15.4 end
</script>