<%@ include file="/WEB-INF/jsp/include.jsp"%>
<%@ include file="/WEB-INF/jsp/dateUtil.jsp"%>
<%@ include file="/WEB-INF/jsp/includeGrid.jsp"%>
<%@ include file="/WEB-INF/jsp/includeDatePicker.jsp"%>
<%@ include file="/WEB-INF/jsp/noBackToThisPage.jsp" %>

<style type="text/css"><%@ include file="/WEB-INF/css/ci6.css" %></style>
<!-- Added for CI Defect # 11682 -->
 <link rel="stylesheet" type="text/css" href="<html:rootPath/>css/mps.css?version=<html:fileCacheVersion/>" />
<!--[if IE 7]>
	<style type="text/css"><%@ include file="/WEB-INF/css/mps_ie7.css" %></style>
<![endif]-->
<style type="text/css">
.modal{
background-color:#fff !important;
}
input[type="text"], input[type="password"], input[type="date"], textarea, textarea.dhx_combo_edit {
	padding:4px!important;
	border-top:1px inset #bfbfbf;
	border-left:1px inset #bfbfbf;
	border-bottom:1px solid #dedede;
	border-right:1px solid #dedede;
	outline: 0;
	border-radius: 0;
	-moz-border-radius: 0;
	-webkit-border-radius: 0;
	margin-left: 0px!important;
	width: auto;
}
div.columns{
  overflow: auto !important;
}
input:focus,input.ieFocusHack,input.focus,.form input:focus,.form input.ieFocusHack,.form textarea:focus,.form textarea.ieFocusHack,.form select:focus,.form select.ieFocusHack,.displayGrid input:focus,.displayGrid input.ieFocusHack
{background-color:lightyellow !important;border:2px #FC3 solid !important;padding:3px 4px !important;}
</style>
<!-- Added for CI Defect # 11682 -->
<style>
.ie7 .button, .ie7 .button_cancel {
behavior: url(/LexmarkPartnerPortal/WebContent/css/PIE.htc) !important;
}
.ie8 .button, .ie8 .button_cancel {
behavior: url(/LexmarkPartnerPortal/WebContent/css/PIE.htc) !important;
}
</style>
<portlet:actionURL var="submitClaimDebriefs">
	<portlet:param name="action" value="submitClaimDebriefs"/>
</portlet:actionURL>

<portlet:actionURL  var="cancelDebrief">
    <portlet:param name="action" value="cancelClaimDebrief" />
</portlet:actionURL>

<portlet:renderURL  var="requestsListViewUrl">
</portlet:renderURL>

<portlet:resourceURL var="downServiceHistoryURL" id="getServiceHistoryList">
    <portlet:param name="assetId" value="${claimDebriefForm.activity.serviceRequest.asset.assetId}" />
    <portlet:param name="serviceRequestId" value="${claimDebriefForm.activity.serviceRequest.id}" />
</portlet:resourceURL>

<portlet:renderURL  var="updateNoteUrl" windowState='<%= LiferayWindowState.POP_UP.toString() %>'>
	<portlet:param name="action" value="showUpdateNotePopup" />
</portlet:renderURL>

<portlet:actionURL var="addAttachmentsCreate">
	<portlet:param name="action" value="addAttachmentsCreateDebrief"/>
</portlet:actionURL>

<portlet:actionURL var="removeAttachmentURL">
	<portlet:param name="action" value="removeAttachmentActionClaimCloseout"/>
</portlet:actionURL>

<script type="text/javascript">

	var exchangeflag="${claimDebriefForm.activity.exchangeFlag}"
	var newContactOriginalFlag = "${claimDebriefForm.activity.technician.newContactFlag}" == "true";
	var updateContactOriginalFlag = "${claimDebriefForm.activity.technician.updateContactFlag}" == "true";
	var newContactFlag = newContactOriginalFlag;
	var updateContactFlag = updateContactOriginalFlag;
	var requestLexmarkReviewFlag = "${claimDebriefForm.activity.requestLexmarkReviewFlag}" == "true";
	var technicianOriginalFirstName = "${claimDebriefForm.activity.technician.firstName}";
	var technicianOriginalLastName = "${claimDebriefForm.activity.technician.lastName}";
	var technicianFirstName = "${claimDebriefForm.activity.technician.firstName}";
	var technicianLastName = "${claimDebriefForm.activity.technician.lastName}";
	
	function goBackPage(){
			window.history.go(-1);
	}
	function alertFun(){
		return false;
	}
	

	function generateTechnicianinformation(){
		document.getElementById("newContactFlagHidden").value = newContactFlag;
		document.getElementById("updateContactFlagHidden").value = updateContactFlag;
	}

	function validateProbleminformation(){
		var result = true;

		var problemCodeValue = document.getElementById("problemCodeSelect").value;
		if(problemCodeValue == "empty" || problemCodeValue == ""){
			showError("<spring:message code='claim.errorMsg.problemCodeNotNull'/>", null, true);
			jQuery('#problemCodeSelect').addClass('errorColor');
			result =  false;
		}
		
		var validateValue = document.getElementById("problemDescriptionInput").value || "";
		validateValue = validateValue.trim();
		if(validateValue.length > 2000){
			showError("<spring:message code='claim.errorMsg.problem.detail.length'/>", null, true);
			jQuery('#problemDescriptionInput').addClass('errorColor');
			result =  false;
		}
		if(requestLexmarkReviewFlag){
			validateValue = document.getElementById("reviewCommentsInput").value  || "";
			validateValue = validateValue.trim();
			if(validateValue.length > 150){
				showError("<spring:message code='claim.errorMsg.review.Comment.length'/>", null, true);
				jQuery('#reviewCommentsInput').addClass('errorColor');
				result =  false;
			}
		}
		return result;
	}

	function validateDebriefinformation(){
		var result = true;
		var isValidStartDate = true;
		var isValidEndDate = true;
		validateValue = document.getElementById("repairDescriptionInput").value  || "";
		validateValue = validateValue.trim();
		if(validateValue == ""){
			showError("<spring:message code='claim.errorMsg.repairDescNotNull'/>", null, true);
			jQuery('#repairDescriptionInput').addClass('errorColor');
			result =  false;
		}
		
		var dateServicedRequested = document.getElementById("dateServicedRequested").value  || "";
		if(dateServicedRequested == null || dateServicedRequested == ''){
			showError("<spring:message code='claim.errorMsg.dateServiceRequestedNotNull'/>", null, true);
			jQuery('#dateServicedRequested').addClass('errorColor');
			result =  false;
			isValidStartDate = false;
		}
		
		var serviceStartDateStr = document.getElementById("serviceStartDate").value  || "";
		if(serviceStartDateStr == null || serviceStartDateStr == ''){
			showError("<spring:message code='claim.errorMsg.startDateNotNull'/>", null, true);
			jQuery('#serviceStartDate').addClass('errorColor');
			result =  false;
			isValidStartDate = false;
		}

		
		
		var serviceEndDateStr = document.getElementById("serviceEndDate").value  || "";
		if(serviceEndDateStr == null || serviceEndDateStr == ''){
			showError("<spring:message code='claim.errorMsg.EndDateNotNull'/>", null, true);
			jQuery('#serviceEndDate').addClass('errorColor');
			result =  false;
			isValidEndDate= false;
		}
	
		if(isValidEndDate && isValidStartDate && convertStringToStandardDateTime(serviceEndDateStr) < convertStringToStandardDateTime(serviceStartDateStr)
				|| (convertStringToStandardDateTime(serviceEndDateStr) == convertStringToStandardDateTime(serviceStartDateStr))){
			showError("<spring:message code='claim.errorMsg.startDateLTEndDate'/>", null, true);
			jQuery('#serviceStartDate').addClass('errorColor');
			jQuery('#serviceEndDate').addClass('errorColor');
			result =  false;
		}

		var resolutionCodeValue = document.getElementById("resolutionCodeSelect").value;
		if(resolutionCodeValue == "empty" || resolutionCodeValue ==""){
			showError("<spring:message code='claim.errorMsg.resolutionCodeNotNull'/>", null, true);
			jQuery('#resolutionCodeSelect').addClass('errorColor');
			result =  false;
		}

		var deviceConditionValue = document.getElementById("deviceConditionSelect").value;
		if(deviceConditionValue == "empty" || deviceConditionValue ==""){
			showError("<spring:message code='claim.errorMsg.printerWorkingConditionNotNull'/>", null, true);
			jQuery('#deviceConditionSelect').addClass('errorColor');
			result =  false;
		}
		//added
		var isValidateStart = validateStartTimeCloseOut('serviceStartDate');
		var isValidateEnd = validateEndTimeCloseOut('serviceEndDate');
		result &= isValidateStart; 
		result &= isValidateEnd;
		if(isValidateStart && isValidateEnd){
		result &= validateDateServiceRequestedCloseOut(); 
		}
		function validateStartTimeCloseOut(textFieldId)
		{
    	    var result = true;
			var text_field = document.getElementById(textFieldId).value;
			if(text_field != ''){
				var dateTime = text_field.split(" ");
				var selectedTime = dateTime[1];
				selectedTime = selectedTime.split(":");
				var selectedHours = selectedTime[0];
				var selectedMins = selectedTime[1];
				
				var slectedHoursMin = selectedHours.toString()+selectedMins.toString();
				
				var currentDate = new Date();					
				var currentdateTime = localizeFormatDate(new Date());
				var currentdateTimeDefault = formatDateToDefault(currentdateTime); //converting into mm/dd/yyyy format
				var selectedStartTimedfault = formatDateToDefault(dateTime[0]);
				var currentHours=currentDate.getHours();
				var currentMins=currentDate.getMinutes();
				
				if(currentMins<10)
					{
					currentMins = "0"+currentMins;
					}
				var currentHoursMin = currentHours.toString()+currentMins.toString();
				var timeCheck = validatePastDateCloseOut(currentdateTimeDefault,selectedStartTimedfault);
				if(timeCheck){
				if(slectedHoursMin>currentHoursMin)
					{
					    result = false;													
						showError("<spring:message code='claim.popUp.errorMsg.debriefStartTimeValidation'/>", null, true);
						jQuery('#serviceStartDate').addClass('errorColor');
						return false;
					}	
				}
				
			
			}else{
				return false;
			}
				return result;
		}
	 function validateEndTimeCloseOut(textFieldId)
		{
    	    var result = true;
			var text_field = document.getElementById(textFieldId).value;
			if(text_field != ''){
				var dateTime = text_field.split(" ");
				var selectedTime = dateTime[1];
				selectedTime = selectedTime.split(":");
				var selectedHours = selectedTime[0];
				var selectedMins = selectedTime[1];
				var slectedHoursMin = selectedHours.toString()+selectedMins.toString();
				var currentDate = new Date();										
				var currentdateTime = localizeFormatDate(new Date());
				var currentdateTimeDefault = formatDateToDefault(currentdateTime); //converting into mm/dd/yyyy format
				var selectedEndTimedfault = formatDateToDefault(dateTime[0]);
				var timeCheck = validatePastDateCloseOut(currentdateTimeDefault,selectedEndTimedfault);
				var currentHours=currentDate.getHours();
				var currentMins=currentDate.getMinutes();
				if(currentMins<10)
					{
					currentMins = "0"+currentMins;
					}
				var currentHoursMin = currentHours.toString()+currentMins.toString();
				if(timeCheck){
				if(slectedHoursMin>currentHoursMin)
					{
					    result = false;														
						showError("<spring:message code='claim.popUp.errorMsg.debriefEndTimeValidation'/>", null, true);
						jQuery('#serviceEndDate').addClass('errorColor');
						return false;
					}	
				}
			}else{
				return false;
			}
			
			
			return result;
		}
	 
	 function validateDateServiceRequestedCloseOut()
		{
		 	//alert("Hello");
			var result = true;
			var dateServicedRequested =document.getElementById("dateServicedRequested").value;
			var dateServicedStart =document.getElementById("serviceStartDate").value;
			var dateServicedEnd =document.getElementById("serviceEndDate").value;
			result = validateServiceRequestedDateTimeCloseOut(dateServicedRequested,dateServicedStart,dateServicedEnd);
			return result;
		}
	 
	 function validatePastDateCloseOut(currentdateTimeDefault, selectedStartTimedfault)
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
	 
	 function validateServiceRequestedDateTimeCloseOut(dateServicedRequested,dateServicedStart,dateServicedEnd)
		{
		               
						// var dateServicedRequested =document.getElementById("dateServicedRequested").value;	
						var result = true;
						var inputDateTime = formatDateToDefault(dateServicedRequested);
						var inputDate =inputDateTime.split(' ');
						var inputDateWithNoTime = inputDate[0];
						var inputDateTimeWithNoDate = inputDate[1].split(":");
						var inputDateHours = inputDateTimeWithNoDate[0];
						var inputDateMin = inputDateTimeWithNoDate[1];
						if(currentDateMin<10)
							{
							currentDateMin = "0"+currentDateMin;
							}
						var inputDateHoursMin = inputDateHours.toString()+inputDateMin.toString();
						//alert("inputDateHoursMin"+inputDateHoursMin);

						var today =new Date();
						var todayDateTime = localizeFormatDate(today)
						//alert("---"+todayDateTime);
						var currentdateTime = formatDateToDefault(todayDateTime);
						var currentDate = currentdateTime.split(' ');
						var currentDateWithNoTime = currentDate[0];
						var currentDateHours = today.getHours();
						//alert(currentDateHours);
						var currentDateMin = today.getMinutes();
						//alert(currentDateMin);
						var currentHoursMin = currentDateHours.toString()+currentDateMin.toString();
						//alert("currentHoursMin"+currentHoursMin);
						
						//var dateServicedStart =document.getElementById("dateServicedStart").value;
						var startDateTime = formatDateToDefault(dateServicedStart);
						var startDate =startDateTime.split(' ');
						var startDateWithNoTime = startDate[0];
						var startDateWithTime = startDate[1].split(":");
						var startDateHours = startDateWithTime[0];
						var startDateMin = startDateWithTime[1];
						var startDateHourMin = startDateHours+startDateMin;
						
						//var dateServicedEnd =document.getElementById("dateServicedEnd").value;
						var endDateTime =  formatDateToDefault(dateServicedEnd);
						var endDate =endDateTime.split(' ');
						var endDateWithNoTime = endDate[0];
						var endDateWithTime = endDate[1].split(":");
						var endDateHours = endDateWithTime[0];
						var endDateMin = endDateWithTime[1];
						var endDateHourMin = endDateHours+endDateMin;						
						if ((inputDateWithNoTime==currentDateWithNoTime) && (inputDateHoursMin>currentHoursMin))
						{
							result= false;
						    showError("<spring:message code='claim.errorMsg.dateServiceRequested.greaterThanToday'/>", null, true);
							jQuery('#dateServicedRequested').addClass('errorColor');
						}
						if (convertStringToDate(inputDateWithNoTime)>convertStringToDate(startDateWithNoTime))
						{
							result= false;
						    showError("<spring:message code='claim.errorMsg.dateServiceRequested.greaterThanStartDate'/>", null, true);
							jQuery('#serviceStartDate').addClass('errorColor');
						}
						if (convertStringToDate(inputDateWithNoTime)>convertStringToDate(endDateWithNoTime))
						{
							result= false;
						    showError("<spring:message code='claim.errorMsg.dateServiceRequested.greaterThanEndDate'/>", null, true);
							jQuery('#serviceEndDate').addClass('errorColor');
						}
						if(inputDateWithNoTime==startDateWithNoTime)
						{
							 if(inputDateHoursMin>startDateHourMin || inputDateHoursMin==startDateHourMin)
								 {
								 result= false;
								    showError("<spring:message code='claim.errorMsg.dateServiceRequested.greaterThanStartDate'/>", null, true);
									jQuery('#serviceStartDate').addClass('errorColor');
								 }
							
						}
						if(inputDateWithNoTime==endDateWithNoTime)
						{
							if(inputDateHoursMin>endDateHourMin || inputDateHoursMin==endDateHourMin)
							{
								result= false;
							    showError("<spring:message code='claim.errorMsg.dateServiceRequested.greaterThanEndDate'/>", null, true);
								jQuery('#serviceEndDate').addClass('errorColor');	
							}
							
						}
						
						return result;
		}
	
	
	//ends here
		 
		
		return result;
	}

	function validateTechnicianinformation(){
		var result = true;
		var technicianContactID = document.getElementById("technicianChoose").value;

		if(technicianContactID == "noContact"){
			showError("<spring:message code='claim.errorMsg.technicianNotNull'/>", null, true);
			jQuery('#technicianChoose').addClass('errorColor');
			return false;
		}
		
		if(technicianContactID != ""){
			newContactFlag = false;
			updateContactOriginalFlag = false;
			return true;
		}
		technicianFirstName = document.getElementById("technicianFirstNameInput").value;
		technicianFirstName = technicianFirstName.trim()
		if(technicianFirstName==""){
			showError("<spring:message code='claim.errorMsg.technicianFirstNameNotNull'/>", null, true);
			jQuery('#technicianFirstNameInput').addClass('errorColor');
			result =  false;
		}
		
		technicianLastName = document.getElementById("technicianLastNameInput").value;
		technicianLastName = technicianLastName.trim();
		if(technicianLastName==""){
			showError("<spring:message code='claim.errorMsg.technicianLastNameNotNull'/>", null, true);
			jQuery('#technicianLastNameInput').addClass('errorColor');
			result =  false;
		}


		if(!result){
			return result;
		}
		if(newContactOriginalFlag){
			if(technicianFirstName != technicianOriginalFirstName|| technicianLastName != technicianOriginalLastName){
				updateContactFlag = true;
			}else{
				updateContactFlag = false;
			}
			newContactFlag = false;
		}else{
			newContactFlag = true;
			updateContactFlag = false;
		}
		return result;
	}
</script>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/in-styles.css?version=<html:fileCacheVersion/>" />
<table width="100%">
	<tr>
		<td>
			<form:form id="claimDebriefForm" name ="claimDebriefForm" commandName="claimDebriefForm" method="post" action="${submitClaimDebriefs}" >
			<form:hidden path="submitToken" />
			<form:hidden path="timezoneOffset"/>
			<input type="hidden" name="activity.serviceType" value="${claimDebriefForm.activity.serviceType}"/>
			<input type="hidden" name="activity.activityId" value="${claimDebriefForm.activity.activityId}"/>
			<input type="hidden" name="activity.serviceRequest.id" value="${claimDebriefForm.activity.serviceRequest.id}"/>
			<input type="hidden" name="activity.serviceRequest.serviceRequestNumber" value="${claimDebriefForm.activity.serviceRequest.serviceRequestNumber}"/>
			<input type="hidden" name="fromPage" value="${claimDebriefForm.fromPage}"/>
			<input type="hidden" name="activity.partnerAccount.accountId" value="${claimDebriefForm.activity.partnerAccount.accountId}"/>
			<input type="hidden" name="activity.partnerAccount.accountName" value="${claimDebriefForm.activity.partnerAccount.accountName}"/>
			<input type="hidden" name="activity.partnerAccount.organizationID" value="${claimDebriefForm.activity.partnerAccount.organizationID}"/>
			<input type="hidden" name="activity.partnerAccount.address.addressId" value="${claimDebriefForm.activity.partnerAccount.address.addressId}"/>
			<input type="hidden" name="activity.partnerAccount.address.addressName" value="${claimDebriefForm.activity.partnerAccount.address.addressName}"/>
			<input type="hidden" name="activity.partnerAccount.address.addressLine1" value="${claimDebriefForm.activity.partnerAccount.address.addressLine1}"/>
			<input type="hidden" name="activity.partnerAccount.address.addressLine2" value="${claimDebriefForm.activity.partnerAccount.address.addressLine2}"/>
			<input type="hidden" name="activity.partnerAccount.address.addressLine3" value="${claimDebriefForm.activity.partnerAccount.address.addressLine3}"/>
			<input type="hidden" name="activity.partnerAccount.address.addressLine4" value="${claimDebriefForm.activity.partnerAccount.address.addressLine4}"/>
			<input type="hidden" name="activity.partnerAccount.address.city" value="${claimDebriefForm.activity.partnerAccount.address.city}"/>
			<input type="hidden" name="activity.partnerAccount.address.state" value="${claimDebriefForm.activity.partnerAccount.address.state}"/>
			<input type="hidden" name="activity.partnerAccount.address.province" value="${claimDebriefForm.activity.partnerAccount.address.province}"/>
			<input type="hidden" name="activity.partnerAccount.address.postalCode" value="${claimDebriefForm.activity.partnerAccount.address.postalCode}"/>
			<input type="hidden" name="activity.partnerAccount.address.country" value="${claimDebriefForm.activity.partnerAccount.address.country}"/>
			<input type="hidden" name="activity.partnerAccount.address.newAddressFlag" value="${claimDebriefForm.activity.partnerAccount.address.newAddressFlag}"/>
			<input type="hidden" name="activity.partnerAccount.address.stateProvince" value="${claimDebriefForm.activity.partnerAccount.address.stateProvince}"/>
			<input type="hidden" id="problemCodes" name="activity.actualFailureCode.value" value="${claimDebriefForm.activity.actualFailureCode.value}"/>
			<input type="hidden" id="debriefStatus" name="debriefStatus" value =""/>
			
			<div class="main-wrap height-100per">
				<div class="content">
					<!-- Export(print,csv,pdf) Start -->
					<div class="grid-controls">	
						<div class="utilities">	
						</div>
						<div class="expand-min">
							<ul>
								<li class="first">
									<a id="headerImageButton" href="${requestsListViewUrl}&back=true" class="button">&lt; &lt;  <spring:message code="link.return.to.requests"/></a><!-- Changed By sabya for AIR00072632 -->
			           	 		</li>
			           	 	</ul>
			           	 </div>
			           	 <div class="clear-right"></div><!-- clear -->	
					</div>
			        <!-- Export(print,csv,pdf) End -->
			         
			        <!-- Claim Information Start -->   	 
			        <div class = "portlet-wrap" id = "claimInformation">
			        	<div class="portlet-header dark-bar">
			        		 <h3><spring:message code="claim.label.claimInformation"/></h3>
			        	</div>
			        	<div class="portlet-wrap-inner">
			        		<div class="width-100per">
								<div class="columns two" >
									<div class="first-column" >
										<table border="0">
											<tr>
												<td class="labelTD" align="Right"><b><spring:message code="claim.label.claimNumber"/></b></td>
												<td width="10px"/>
											  	<td>${claimDebriefForm.activity.serviceRequest.serviceRequestNumber}</td>
											</tr>
											<tr>
												<td class="labelTD" align="right" valign="top"><b><spring:message code="claim.label.claimStatus"/></b></td>
											  	<td/>
											  	<td>${claimDebriefForm.activity.activityStatus.name}</td>
											</tr>
											<tr>
											  	<td class="labelTD" align="right" valign="top"><b><spring:message code="claim.label.openedDate"/></b></td>
											  	<td/>
											  	<td><util:dateFormat value="${claimDebriefForm.activity.serviceRequest.serviceRequestDate}" timezoneOffset="${timezoneOffset}" /></td>
											</tr>
										</table>
									</div>
								</div>
							</div>
					 	</div>
			       	 	<div class="portlet-footer">	
			       	 		<div class="portlet-footer-inner"></div>
			       	 	</div>
			        </div>
			        <!-- Claim Information End -->   	 
			        
			        <!-- Device Information Start -->     	 
			        <div class = "portlet-wrap" id = "deviceInformation">
			        	<div class="portlet-header dark-bar">
			           		<h3><spring:message code="claim.label.deviceInformation"/></h3>
			       		</div>
			       		<div class="portlet-wrap-inner">
			       			<div class="width-100per">
				    			<div class="columns two">
					           		<div class="first-column">
										<table width="80%" border="0">
											<tr>
												<td  class="labelTD" align="center"><h3>${claimDebriefForm.activity.serviceRequest.asset.productLine}</h3>
											    </td>
											</tr>
											<tr>
											  	<td align="center"><img width="75" src="${claimDebriefForm.activity.serviceRequest.asset.productImageURL}"></td>
											</tr>
										</table>
									</div>
									<div class="second-column">
										<br/>
										<br/>
										<table  border="0">
											<tr>
											 	<td width="150px" align="right"><b><spring:message code="claim.label.serialNumber"/></b></td>
												<td width="10px" />
											  	<td class="labelTD">${claimDebriefForm.activity.serviceRequest.asset.serialNumber}</td>
											</tr>	 
											<tr>
												<td class="labelTD" align="right"><b><spring:message code="claim.label.machineTypeModel"/></b></td>
												<td/>
												<td class="labelTD">${claimDebriefForm.activity.serviceRequest.asset.modelNumber}</td>
											</tr>
											<tr>
												<td class="labelTD" align="right"><b><spring:message code="claim.label.productPN/TLI"/></b></td>
												<td/>
												<td class="labelTD">${claimDebriefForm.activity.serviceRequest.asset.productTLI}</td>
											</tr>
										</table>
									</div>
								</div>
							</div>
						</div>
						<div class="portlet-footer">
							<div class="portlet-footer-inner"></div>
			           	</div>
					</div>
			        <!-- Device Information End -->     
			        
			            	 
			        <!-- Service History Start -->     
			        	<c:set var="assetId" value="${claimDebriefForm.activity.serviceRequest.asset.assetId}"/>   	 
						<c:set var="serviceRequestId" value="${claimDebriefForm.activity.serviceRequest.id}"/> 
						<%@ include file="/WEB-INF/jsp/request/serviceRequestHistoryList.jsp"%>
			        <!-- Service History End -->   
			        
			        <!-- Customer Information Start --> 
					<div class = "portlet-wrap">
						<div class="portlet-header dark-bar">
							<h3><spring:message code="claim.label.customerInformation"/></h3>
						</div>
						<div class="portlet-wrap-inner">
							<div class="width-100per">
								<div class="columns two">
									<div class="first-column" >
										<table  border="0">
											<tr>
												<td/>
											  	<td rowspan="3" width="10px"/>
											  	<td><b><u><spring:message code="claim.label.customer"/></u></b></td>
										 	</tr>
										  	<tr>
											 	 <td align="right"><b><spring:message code="claim.label.name"/></b></td>
											 	 <td>${claimDebriefForm.activity.customerAccount.accountName}</td>
										 	</tr>
										  	<tr>
											 	<td align="right" valign="top" rowspan="2"><b><spring:message code="claim.label.address"/></b></td>
											  	<td rowspan="2">
											  		<c:choose>
														<c:when test="${empty claimDebriefForm.activity.newCustomerAddressCombined}">
															<util:addressOutput value="${claimDebriefForm.activity.serviceRequest.asset.installAddress}"/>
														</c:when>
														<c:otherwise>
															<util:replaceRN value="${claimDebriefForm.activity.newCustomerAddressCombined}"/>
														</c:otherwise>
													</c:choose>
											  	</td>
										 	</tr>
										</table>
									</div>
									<div class="second-column" >
										<table  width="90%" class="table-style2">
											<tr>
											  	<td/>
											  	<td width="10px"/>
											  	<td><b><u><spring:message code="claim.label.primaryContact"/></u></b></td>
										 	</tr>
										  	<tr>
												<td align="right"><b><spring:message code="claim.label.name"/></b></td>
												<td width="20px"/></td>
												<td><label id="primaryContactFirstNameLabel">${claimDebriefForm.activity.serviceRequest.primaryContact.firstName}</label>&nbsp; <label id="primaryContactLastNameLabel">${claimDebriefForm.activity.serviceRequest.primaryContact.lastName}</label></td>
										 	</tr>
										  	<tr>
											  	<td align="right"><b><spring:message code="claim.label.phone"/></b></td>
											  	<td width="20px"/></td>
											 	<td class="word-wrap-break-word"><label id="primaryContactPhoneLabel">${claimDebriefForm.activity.serviceRequest.primaryContact.workPhone}</label></td>
										 	</tr>
										 	<tr>
											 	<td align="right" valign="top"><b><spring:message code="claim.label.email"/></b></td>
											 	<td width="20px"/></td>
											 	<td valign="top" class="word-wrap-break-word"><label id="primaryContactEmailLabel">${claimDebriefForm.activity.serviceRequest.primaryContact.emailAddress}</label></td>
											</tr>
										</table>
									</div>					
								</div>
							</div>
						</div>
						<div class="portlet-footer">
							<div class="portlet-footer-inner"></div>
						</div>
					</div>
			        <!-- Customer Information End --> 
			        
			        <!-- Problem information Start -->  	
					<div class="portlet-wrap" id="problem_information">
						<div class="portlet-header"><h3><spring:message code="claim.label.problemInformation"/></h3></div><!-- portlet-header -->
							<div class="portlet-wrap-inner">
								<div class="width-100per">
									<div class="columns two">
										<div class="first-column">
											<ul>
											<li>
												<label><b><span class="required">* </span></b><spring:message code="claim.label.problemCode1"/></label><br/>
												
													<!-- onchange added for problem Code 1 CI 15.4 -->
										        	<select name="activity.debrief.actualFailureCode.value" id="problemCodeSelect" onchange="getprobCode(this.value,1)">
										        		<option value="empty"></option>  
														<c:forEach items="${claimDebriefForm.problemCodeMap}" var="problemCodeEntry" varStatus="loop">
															<option  value="${problemCodeEntry.key}" > ${problemCodeEntry.value} </option>
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
												
													<textarea id="problemDescriptionInput" name="activity.serviceRequest.problemDescription" class="width-200px" cols="80" rows="6" onblur="validateLength(0,2000,this);">${claimDebriefForm.activity.serviceRequest.problemDescription}</textarea>
												
												</li>
												<li class="clear-both">
												<label><spring:message code="claim.label.serviceProviderReferenceNumber"/></label><br/>
												<input id="serviceProviderReferenceNumber" type="text" name="activity.serviceProviderReferenceNumber" value="${claimDebriefForm.activity.serviceProviderReferenceNumber}" onchange="validateLength(0,30,this)"/>
												</li>
												<li>
												<input type="hidden" name="claimDebriefForm.activity.requestLexmarkReviewFlag" value="${claimDebriefForm.activity.requestLexmarkReviewFlag}"/>
												<c:if test="${claimDebriefForm.activity.requestLexmarkReviewFlag}">
													<label><spring:message code="claim.label.reviewComments"/></label>
													<textarea id="reviewCommentsInput" name="activity.reviewComments" cols="80" rows="6" onblur="validateLength(1,150,this);">${claimDebriefForm.activity.reviewComments}</textarea>
												</c:if>
												</li>
											</ul>
										</div><!-- first-column -->
									</div>
								</div>
							</div><!-- portlet-wrap-inner -->
							<div class="portlet-footer">
								<div class="portlet-footer-inner"></div><!-- portlet-footer-inner -->
						</div><!-- portlet-footer -->
					</div>
			        <!-- Problem information End --> 	 
			        
			        <!-- Technician Information Start -->	 
			        <input type="hidden" id="newContactFlagHidden" name="activity.technician.newContactFlag" value="${claimDebriefForm.activity.technician.newContactFlag}" />
			        <input type="hidden" id="updateContactFlagHidden" name="activity.technician.updateContactFlag" value= "${claimDebriefForm.activity.technician.updateContactFlag}" />
					<div class="portlet-wrap" id="technician_information">
						<div class="portlet-header">
							<h3><spring:message code="claim.label.technicianInformation"/></h3>
						</div><!-- portlet-header -->
						<div class="portlet-wrap-inner">
							<div class="width-100per">
								<div class="columns two">
									<div class="first-column">
										<table  border="0">
												<tr>
												  	<td align="right" class="table-td-style1"><b><span class="required">* </span></b><label><spring:message code="claim.label.technician"/></label></td>
												  	<td width="10px"/>
												  	<td>
														<select id="technicianChoose" name="activity.technician.contactId" onchange="showTechnicianInputTexts()">
														<option value="noContact" > </option>	
														<c:forEach items="${claimDebriefForm.technicianList}" var="technicianEntry" varStatus="loop">
															<option  
															<c:if test="${claimDebriefForm.activity.technician.contactId == technicianEntry.contactId && !claimDebriefForm.activity.technician.newContactFlag}">
																selected
															</c:if>
															 value="${technicianEntry.contactId}" >${technicianEntry.lastName}, ${technicianEntry.firstName}</option>
														</c:forEach>
														<option 
															<c:if test="${claimDebriefForm.activity.technician.newContactFlag}">
																	selected
															</c:if>
														 value="" ><spring:message code="claim.label.dropdownlist.optionvalue.technician.other"/></option>
													</select>
													</td>
											 	</tr>
											  	<tr id="technicianFirstNameDD" style="display:none">
													<td align="right"><label><span class="required">* </span><spring:message code="claim.label.firstName"/></label></td>
													<td/>
													<td>
														<input class="width-130px" type="text" id="technicianFirstNameInput" name="activity.technician.firstName" onblur="validateLength(1,50,this);"
															<c:if test="${claimDebriefForm.activity.technician.newContactFlag}">
																value="${claimDebriefForm.activity.technician.firstName}"
															</c:if>
														 />
													</td>
											 	</tr>
											  	<tr id="technicianLastNameDD" style="display:none">
												  	<td align="right"><span class="required">* </span><spring:message code="claim.label.lastName"/></td>
												 	<td/>
												 	<td>
												    	<input class="width-130px" type="text" id="technicianLastNameInput" name="activity.technician.lastName" onblur="validateLength(1,50,this);"
												    		<c:if test="${claimDebriefForm.activity.technician.newContactFlag}">
																value="${claimDebriefForm.activity.technician.lastName}"
															</c:if>
												    	 />										 	
												 	</td>
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
						jQuery(document).ready(function() {	
							if("${claimDebriefForm.activity.technician.newContactFlag}" == "true"){
								document.getElementById("technicianLastNameDD").style.display = "";
								document.getElementById("technicianFirstNameDD").style.display = "";
							}
							// added for Problem Code CI 15.4 start
							var selectProblemCode1Val = document.getElementById("problemCodeSelect").value; 
							if(selectProblemCode1Val != "" && selectProblemCode1Val != 'empty'){
								getprobCode(selectProblemCode1Val,'1');
							}
							
							var numericCodeFlag = true;
							var showProbValidateMsg = false;
							
							// added for Problem Code CI 15.4 end
						});		
					
						function showTechnicianInputTexts(){
							var value = document.getElementById("technicianChoose").value;
							if(value == ""){
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
					</script>
					<!-- Technician Information End -->	 
						 
					<!-- Close Out Claim Start -->	 
					<script>
					//script for activity for 2098
					var activityId = "${claimDebriefForm.activity.activityId}";
					var serviceRequestId =  "${claimDebriefForm.activity.serviceRequest.id}";
					var isAssetBlankValid = true;
					var isAssetOtherAsset = false;
					var isValidMachineTypeModel = false;
					var isValidSerialNumber = false;
					var removeDeviceSectionFlag = "${claimDebriefForm.removeDeviceSectionFlag}" == "true";
					
					var partAssetList = [];
					<c:forEach items="${claimDebriefForm.assetList}" var="assetTemp" varStatus = "status" >
					partAssetList[${status.index}] = ["${assetTemp.modelNumber}","${assetTemp.serialNumber}"];
					</c:forEach>

					
					function networkConnectedSelectChange(){
						var value = document.getElementById("networkConnectedSelect").value;
						if(value == "true"){
							document.getElementById("macAddressInput").value = "";
							document.getElementById("ipAddressInput").value = "";
							document.getElementById("ipAddressLableDiv").style.display = "";
							document.getElementById("macAddressLableDiv").style.display = "";
							jQuery("#macAddressInput").show();
							jQuery("#ipAddressInput").show();
						}
						else{
							document.getElementById("ipAddressLableDiv").style.display = "none";
							document.getElementById("macAddressLableDiv").style.display = "none";
							jQuery("#macAddressInput").hide();
							jQuery("#ipAddressInput").hide();
						}
					}

					function nonLexmarkSuppliesUsedSelectChange(){
						var value = document.getElementById("nonLexmarkSuppliesUsedSelect").value;
						if(value == "true"){
							showSupplyManufacturer(true);
						}
						else{
							showSupplyManufacturer(false);
						}
					}


					function assetListSelectChange(){

						isAssetBlankValid = false;
						isAssetOtherAsset = false;
						isValidSerialNumber = false;
						isValidMachineTypeModel= false;

						var assetSelectValue = document.getElementById("assetListSelect").value;
						if(assetSelectValue == ""){
							isAssetBlankValid=true;
							showMTMAndSerialNumberDiv(false);
						}else if(assetSelectValue == "other"){
							isAssetOtherAsset=true;
							showMTMAndSerialNumberDiv(true);
						}else{
							showMTMAndSerialNumberDiv(false);
							document.getElementById("modelNumberHidden").value = partAssetList[assetSelectValue][0];
							document.getElementById("serialNumberHidden").value = partAssetList[assetSelectValue][1];
						}
					}

					function showMTMAndSerialNumberDiv(flag){
						document.getElementById("machineTypeLoadingIndicator").style.display = "none";
						document.getElementById("trMachineType").style.display = "none";
						document.getElementById("serialNumberLoadingIndicator").style.display = "none";
						document.getElementById("trSerialNumber").style.display = "none";
						document.getElementById("machineTypeErrorMsg").innerHTML = "";
						document.getElementById("serialNumberErrorMsg").innerHTML
						document.getElementById("serialNumberInput").value=""; 
						document.getElementById("machineTypeInput").value="";
						if(flag){
							document.getElementById("machineTypeLableDiv").style.display = "";
							document.getElementById("machineTypeInputDiv").style.display = "";
							document.getElementById("serialNumberLableDiv").style.display = "";
							document.getElementById("serialNumberInputDiv").style.display = "";
						}else{

							document.getElementById("machineTypeLableDiv").style.display = "none";
							document.getElementById("machineTypeInputDiv").style.display = "none";
							document.getElementById("serialNumberLableDiv").style.display = "none";
							document.getElementById("serialNumberInputDiv").style.display = "none";
						}
					}

					function showSupplyManufacturer(flag){
						if(flag){
							document.getElementById("supplyManufacturerLableDiv").style.display = "";
							document.getElementById("supplyManufacturerInputDiv").style.display = "";
							document.getElementById("supplyManufacturer").value = "";
						}else{
							document.getElementById("supplyManufacturerLableDiv").style.display = "none";
							document.getElementById("supplyManufacturerInputDiv").style.display = "none";
							document.getElementById("supplyManufacturer").value = "";
						}
					}
					
					var MachineTypeModelLock = false;
					function validateManualAsset(){
						if(MachineTypeModelLock  || !isAssetOtherAsset){
							return;
						}
						isValidMachineTypeModel = false;
						isValidSerialNumber = false;
						document.getElementById("serialNumberInput").value=""; 
						document.getElementById("serialNumberLoadingIndicator").style.display = "none";
						document.getElementById("serialNumberErrorMsg").innerHTML = "";
						document.getElementById("trSerialNumber").style.display = "none";
						validateLength(0,100,document.getElementById("machineTypeInput"));
						var machineType = document.getElementById("machineTypeInput").value;
						machineType = machineType.trim();
						if (machineType == '') {
							document.getElementById("machineTypeErrorMsg").innerHTML = "";
							return;
						}
						
						var url = '<portlet:resourceURL id="validateManualAsset"/>';
						url +="&machineType=" + machineType;
						document.getElementById("machineTypeErrorMsg").innerHTML = "";
						document.getElementById("trMachineType").style.display = "";
						document.getElementById("machineTypeLoadingIndicator").style.display = "";
						MachineTypeModelLock = true;
						doAjax(url, callbackGetResult4MT,failBack4MT, null);

						//ServiceRequestProxy.setServiceAddressFavouriteFlag("${serviceRequestConfirmationForm.loginAccountContact.contactId}", favoriteAddressId, flagStatus, callbackGetResult);
					}
					function callbackGetResult4MT(result) {
						if(isAssetOtherAsset){
							if(result.data){
								isValidMachineTypeModel = true;
								document.getElementById("machineTypeLoadingIndicator").style.display = "none";
								document.getElementById("machineTypeErrorMsg").innerHTML = "";
								document.getElementById("trMachineType").style.display = "none";
							}else{
								document.getElementById("trMachineType").style.display = "";
								document.getElementById("machineTypeLoadingIndicator").style.display = "none";
								document.getElementById("machineTypeErrorMsg").innerHTML = "<spring:message code='deviceSelection.errorMsg.machineTypeErrorMsg'/>";
							}
						}
						MachineTypeModelLock = false;
						return true;
					}
					function failBack4MT(result) {
						MachineTypeModelLock = false;
						return false;
					}

					
					var serialNumberLock = false;
					function validateInstalledPrinterSerialNumber(){
						if(serialNumberLock || !isAssetOtherAsset){
							return;
						}
						
						isValidSerialNumber = false;
						var machineType = document.getElementById("machineTypeInput").value; 
						machineType = machineType.trim();
						if (!isValidMachineTypeModel) {
							document.getElementById("serialNumberLoadingIndicator").style.display = "none";
							document.getElementById("trSerialNumber").style.display = "";
							document.getElementById("serialNumberErrorMsg").innerHTML = "<spring:message code='deviceSelection.description.validatingSerialNumber.machineTypeNull'/>";
							return;
						}
						validateLength(0,100,document.getElementById("serialNumberInput"));
						var serialNumberValue = document.getElementById("serialNumberInput").value; 
						serialNumberValue = serialNumberValue.trim();
						if (serialNumberValue == '') {
							return;
						}
						var url = '<portlet:resourceURL id="validateInstalledPrinterSerialNumber"/>';
						url = url + "&activityId=" + activityId;
						url = url + "&serviceRequestId=" + serviceRequestId;
						url = url + "&modelNumber=" + machineType;
						url = url + "&serialNumber=" + serialNumberValue;
						document.getElementById("serialNumberErrorMsg").innerHTML = "";
						document.getElementById("trSerialNumber").style.display = "";
						document.getElementById("serialNumberLoadingIndicator").style.display = "";
						
						serialNumberLock = true;
						doAjax(url, callbackGetResultSerialNumber,failBackSerialNumber, null);
					}

					
					function callbackGetResultSerialNumber(result) {
						if(isAssetOtherAsset){
							if(result.data){
								if(isValidMachineTypeModel){
									isValidSerialNumber = true;
									document.getElementById("serialNumberLoadingIndicator").style.display = "none";
									document.getElementById("serialNumberErrorMsg").innerHTML = "";
									document.getElementById("trSerialNumber").style.display = "none";
								}else{
									document.getElementById("machineTypeInput").value = "";
									document.getElementById("machineTypeLoadingIndicator").style.display = "none";
									document.getElementById("machineTypeErrorMsg").innerHTML = "";
									document.getElementById("trMachineType").style.display = "none";
								}
							}else{
								document.getElementById("trSerialNumber").style.display = "";
								document.getElementById("serialNumberLoadingIndicator").style.display = "none";
								document.getElementById("serialNumberErrorMsg").innerHTML = "<spring:message code='deviceSelection.errorMsg.serialNumberErrorMsg'/>";
							}
						}
						serialNumberLock = false;
						return true;
					}
					
					function failBackSerialNumber(result) {
						serialNumberLock =false;
						return false;
					}

					function validateCloseOutActivityInfo(){
						var isValidStartDate = true;
						var isValidEndDate = true;
						var result = true;
						var fieldname;	
						//warning.maximumLengthExceed.field
						var startDate = document.getElementById("actualStartDate").value;
						if(isNullFun(startDate)){
							fieldname = '<spring:message code="claim.errorMsg.name.actualStartDate"/>';
							showError('<spring:message code="claim.errorMsg.notNull.field" arguments="'+fieldname+'"/>', null, true);
							result =  false;
							isValidStartDate = false;
						}

						var endDate = document.getElementById("actualEndDate").value;
						if(isNullFun(endDate)){
							fieldname = '<spring:message code="claim.errorMsg.name.actualEndDate"/>';
							showError('<spring:message code="claim.errorMsg.notNull.field" arguments="'+fieldname+'"/>', null, true);
							result =  false;
							isValidEndDate = false;
						}

						if(isValidStartDate && isValidEndDate && convertStringToStandardDateTime(endDate) < convertStringToStandardDateTime(startDate)){
							showError("<spring:message code='claim.errorMsg.actualStartDateLTEndDate'/>", null, true);
							result =  false;
						}


						
						var addtionServiceRequiredValue = document.getElementById("addtionServiceRequiredSelect").value;
						if(isNullFun(addtionServiceRequiredValue)){
							fieldname = '<spring:message code="claim.errorMsg.name.additionalServiceRequired"/>';
							showError('<spring:message code="claim.errorMsg.notNull.field" arguments="'+fieldname+'"/>', null, true);
							result =  false;
						}

						
						var nonLexmarkSuppliesUsedValue = document.getElementById("nonLexmarkSuppliesUsedSelect").value;
						if(isNullFun(nonLexmarkSuppliesUsedValue)){
							fieldname = '<spring:message code="claim.errorMsg.name.nonLexmarkSuppliesUsed"/>';
							showError('<spring:message code="claim.errorMsg.notNull.field" arguments="'+fieldname+'"/>', null, true);
							result =  false;
						}

						var problemCodeValue = document.getElementById("problemCodeSelect").value;
						if(isNullFun(problemCodeValue)){
							showError('<spring:message code="claim.errorMsg.problemCodeNotNull"/>', null, true);
							result =  false;
						}

						var travelDistanceValue = document.getElementById("travelDistanceInput").value;
						if(!isNullFun(travelDistanceValue)){
							var travelDistanceSelectValue = document.getElementById("travelDistanceSelect").value;
							if(isNullFun(travelDistanceSelectValue)){
								fieldname = '<spring:message code="claim.errorMsg.name.travelUnitOfMeasure"/>';
								showError('<spring:message code="claim.errorMsg.notNull.field" arguments="'+fieldname+'"/>', null, true);
								result =  false;
							}
						}

						
						var resolutionCodeValue = document.getElementById("resolutionCodeSelect").value;
						if(isNullFun(resolutionCodeValue)){
							showError("<spring:message code='claim.errorMsg.resolutionCodeNotNull'/>", null, true);
							result =  false;
						}

						var repairDescriptionValue = document.getElementById("repairDescriptionInput").value || "";
						repairDescriptionValue = repairDescriptionValue.trim();
						if(isNullFun(repairDescriptionValue)){
							showError("<spring:message code='claim.errorMsg.repairDescNotNull'/>", null, true);
							result =  false;
						}

						var deviceConditionValue = document.getElementById("deviceConditionSelect").value;
						if(isNullFun(deviceConditionValue)){
							showError("<spring:message code='claim.errorMsg.printerWorkingConditionNotNull'/>", null, true);
							result =  false;
						}
						
						if(removeDeviceSectionFlag){
							if(isAssetBlankValid){
								showError("<spring:message code='claim.errorMsg.Asset'/>", null, true);
								result =  false;
							}else if(isAssetOtherAsset){
								document.getElementById("modelNumberHidden").value = document.getElementById("machineTypeInput").value;
								document.getElementById("serialNumberHidden").value = document.getElementById("serialNumberInput").value;
								
								if(!isValidMachineTypeModel){
									showError("<spring:message code='deviceSelection.errorMsg.machineTypeErrorMsg'/>", null, true);
									result =  false;
								}
		
								if(!isValidSerialNumber){
									showError("<spring:message code='deviceSelection.errorMsg.serialNumberErrorMsg'/>", null, true);
									result =  false;
								}
							}
						}

						var networkConnectedValue = document.getElementById("networkConnectedSelect").value;
						if(networkConnectedValue == "true"){
							var ipAddressValue = document.getElementById("ipAddressInput").value;
							ipAddressValue = ipAddressValue.trim();
							if(!isNullFun(ipAddressValue) && !isIPAddress(ipAddressValue)){
								fieldname = '<spring:message code="claim.errorMsg.name.ipAdress"/>';
								showError('<spring:message code="claim.errorMsg.invalid.field" arguments="'+fieldname+'"/>', null, true);
								result =  false;
							}
								
							var macAddressValue = document.getElementById("macAddressInput").value;
							macAddressValue=macAddressValue.trim();
							if(!isNullFun(macAddressValue) && !isMACAddress(macAddressValue)){
								fieldname = '<spring:message code="claim.errorMsg.name.macAdress"/>';
								showError('<spring:message code="claim.errorMsg.invalid.field" arguments="'+fieldname+'"/>', null, true);
								result =  false;
							}
						}

						return result;
					}
					function isNullFun(obj){
						if(obj == null || obj==""){
							return true;
						}else{
							return false;
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
						validateLength(0,9,ele);
						ele.value = ele.value.replace(/[^\d]/g,"");				
					}
					//end 2098
					</script>
					<input type="hidden" id="modelNumberHidden" name="activity.debrief.installedAsset.modelNumber"/>
					<input type="hidden" id="serialNumberHidden" name="activity.debrief.installedAsset.serialNumber"/>
					<div class = "portlet-wrap" id="close_out">
			        	<div class="portlet-header dark-bar">
			         		<h3><spring:message code="claim.label.closeOutClaim"/></h3>
			        	</div>
			         	 	
			        	<div class="portlet-wrap-inner">
				        	<!-- added by ragesree -2098 -->
				        	    <c:if test="${claimDebriefForm.activity.exchangeFlag=='true'}">
				        	    	<div id="exchangecloseout">
				        	    		
				        	    		<div class="portlet-wrap-inner">
					<div id="closeOutActivity1">
						<div class="width-100per">
				         		<div class="columns two">
								<table width="100%">
									<tr>
										<td align="left" valign="top" width="50%">
											<table width="100%">
												<tr>
													<td align="right"><b><span class="required">* </span></b><spring:message code="claim.label.actualStartDate" />&nbsp;</td>
											        <td align="left">
												        <input type="text" readonly="readonly" 
													        onmouseup="show_cal('actualStartDate', null, null,true);" 
													        size="16" id="actualStartDate" name="serviceStartDate" class="">
																<img onclick="show_cal('actualStartDate', null, null,true)"  src="<html:imagesPath/>transparent.png" class="ui-icon calendar-icon image-style1" id="imgActualStartDate"/>
											        </td>
												</tr>
												<tr><td height="5px" colspan="2"></td></tr>
												<tr>
											        <td align="right"><b><span class="required">* </span></b><spring:message code="claim.label.actualEndDate" />&nbsp;</td>
											        <td align="left">
												        <input type="text" readonly="readonly" 
													        onmouseup="show_cal('actualEndDate', null, null,true);" 
													        size="16" id="actualEndDate" name="serviceEndDate" class="">
																<img onclick="show_cal('actualEndDate', null, null,true)"  src="<html:imagesPath/>transparent.png" class="ui-icon calendar-icon image-style1" id="imgActualEndDate"/>
											        </td>
												</tr>
												<tr><td height="5px" colspan="2"></td></tr>
												<tr>
											        <td align="right"><spring:message code="claim.label.travelDistance" />&nbsp;</td>
											        <td align="left"><input class="width-110px" type="text" name="activity.debrief.travelDistance" id="travelDistanceInput"  onafterpaste="clearNoNum(this)" onkeyup="clearNoNum(this)" >
														<select id="travelDistanceSelect" name="activity.debrief.travelUnitOfMeasure.value">
															<option value="" > </option>
															<c:forEach items="${claimDebriefForm.travelUnitOfMeasureMap}" var="travelUnitOfMeasure" varStatus="loop">
																<option value="${travelUnitOfMeasure.key}" > ${travelUnitOfMeasure.value}</option>
															</c:forEach>
														</select>
													</td>
												</tr>
												<tr><td height="5px" colspan="2"></td></tr>
												<tr>
											        <td align="right"><spring:message code="claim.label.travelDuration" />&nbsp;</td>
											        <td align="left"><input class="width-110px" type="text" name="activity.debrief.travelDuration" id="travelDurationInput" onafterpaste="clearNoNum(this)" onkeyup="clearNoNum(this)"></td>
												</tr>
												<tr><td height="5px" colspan="2"></td></tr>
											    <tr>
											        <td align="right"><b><span class="required">* </span></b><spring:message code="claim.label.problemCode" />&nbsp;</td>
											        <td align="left">
											        	<select id="problemCodeSelect" name="activity.debrief.actualFailureCode.value">
															<option value=""></option>
															<c:forEach items="${claimDebriefForm.problemCodeMap}" var="problemCodeEntry" varStatus="loop">
																<option  
																<c:if test="${claimDebriefForm.activity.actualFailureCode.value == problemCodeEntry.key}">
																	selected
																</c:if>
																 value="${problemCodeEntry.key}" > ${problemCodeEntry.value} </option>
															</c:forEach>
														</select>
													</td>
												</tr>
												<tr><td height="5px" colspan="2"></td></tr>
												<tr>
											        <td align="right" valign="top"><spring:message code="claim.label.problemDetails" />&nbsp;</td>
											        <td align="left"  valign="top">
														<textarea id="problemDescriptionInput" name="activity.serviceRequest.problemDescription" cols="80" rows="6" onblur="validateLength(0,2000,this);" class="problem-description-input">${claimDebriefForm.activity.serviceRequest.problemDescription}</textarea>
													</td>
												</tr>																								
											</table>
										</td>
										<td align="left" valign="top" width="50%">
											<table width="100%">
												<tr>
													<td align="right"><b><span class="required">* </span></b><spring:message code="claim.label.resolutionCode" />&nbsp;</td>
											        <td align="left">
														<select id="resolutionCodeSelect" name="activity.debrief.resolutionCode.value">
															<option value="" > </option>
															<c:forEach items="${claimDebriefForm.resolutionCodeMap}" var="resolutionCodeEntry" varStatus="loop">
																<option value="${resolutionCodeEntry.key}" > ${resolutionCodeEntry.value}</option>
															</c:forEach>
														</select>
													</td>
												</tr>
												<tr><td height="5px" colspan="2"></td></tr>
												<tr>
											        <td align="right" valign="top"><b><span class="required">* </span></b><spring:message code="claim.label.repairDescription" />&nbsp;</td>
											        <td align="left" valign="top"><textarea id="repairDescriptionInput" name="activity.debrief.repairDescription" onblur="validateLength(0,2000,this);" class="repair-description-input" rows="5" cols="25"></textarea></td>
												</tr>
												<tr><td height="5px" colspan="2"></td></tr>	
											    <tr>
													<td align="right"><b><span class="required">* </span></b><spring:message code="claim.label.additionalServiceRequired" />&nbsp;</td>
											        <td align="left">
											        	<select id="addtionServiceRequiredSelect" name="activity.repairCompleteFlag">
														   <option value=""></option>
														   <option value="false"><spring:message code="selection.label.No" /></option>
														   <option value="true"><spring:message code="selection.label.yes" /></option>
														</select>
													</td>
												</tr>
												<tr><td height="5px" colspan="2"></td></tr>
												<tr>
											        <td align="right"><b><span class="required">* </span></b><spring:message code="claim.label.nonLexmarkSuppliesUsed" />&nbsp;</td>
											        <td align="left">
											       		<select id="nonLexmarkSuppliesUsedSelect" name="activity.debrief.genuineLexmarkSuppliesUsedFlag" onchange="nonLexmarkSuppliesUsedSelectChange()">
											        	   <option value=""></option>
														   <option value="false"><spring:message code="selection.label.No" /></option>
														   <option value="true"><spring:message code="selection.label.yes" /></option>
														</select>
													</td>
												</tr>	
												<tr><td height="5px" colspan="2"></td></tr>
												<tr>
											        <td align="right"><div id="supplyManufacturerLableDiv" style="display:none;"><spring:message code="claim.label.supplyManufacturer" />&nbsp;</div></td>
											        <td align="left"><div id="supplyManufacturerInputDiv" style="display:none;"><input type="text" name="activity.debrief.supplyManufacturer" id="supplyManufacturer"  onblur="validateLength(0,50,this);" class="width-190px"></div></td>
												</tr>												
											</table>
										</td>
									</tr>	
								</table>	
								<br/>
								<br/>
								
								<c:if test="${claimDebriefForm.removeDeviceSectionFlag == 'false'}">
								<table width="100%">
									<tr>
								        <td colspan="2" align="left"><h3>&nbsp;&nbsp;<spring:message code="claim.label.repairedDeviceInformation" /></h3></td>
									</tr>
									
									<tr>
										<td align="left" valign="top" width="50%">
											<table width="100%">
												<tr>
													<td align="right"><b><span class="required">* </span></b><spring:message code="claim.label.printerWorkingCondition" />&nbsp;</td>
											        <td align="left">
														<select id="deviceConditionSelect" name="activity.debrief.deviceCondition.value" >
															<option value=""></option>
															<c:forEach items="${claimDebriefForm.workingConditionMap}" var="workingConditionaEntry" varStatus="loop">
																<option value="${workingConditionaEntry.key}" > ${workingConditionaEntry.value}</option>
															</c:forEach>
														</select>
													</td>
												</tr>
												<tr><td height="5px" colspan="2"></td></tr>
												<tr>
													<td align="right"><spring:message code="claim.label.pageCountAll" />&nbsp;</td>
								    			    <td align="left"><input type="text" name="activity.serviceRequest.asset.newPageCount" id="pageCountAllInput"  class="width-190px" onafterpaste="clearNoNumAndPoint(this)" onkeyup="clearNoNumAndPoint(this)"></td>
												</tr>
												<tr><td height="5px" colspan="2"></td></tr>
												<tr>
													<td align="right"><spring:message code="claim.label.lexmarkTag" />&nbsp;</td>
								     		   		<td align="left"><input type="text" name="activity.serviceRequest.asset.assetTag" id="lexmarkTagInput" class="width-190px"  onblur="validateLength(0,50,this);"></td>
												</tr>
											</table>
										</td>
										<td align="left" valign="top" width="50%">
											<div class="width-100per">
											<table width="100%">
												<tr>
													<td width="50%" align="right"><spring:message code="claim.label.networkConnected" />&nbsp;</td>
											        <td width="50%" align="left">
											        	<select id="networkConnectedSelect" name="activity.serviceRequest.asset.networkConnectedFlag" onchange="networkConnectedSelectChange()" >
													  		 <option value="false"
													   			 <c:if test="${!claimDebriefForm.networkConnectedFlag}">
													   		 		selected
													   		 	 </c:if>
													   		 ><spring:message code="selection.label.No" /></option>
													  		 <option value="true"
													  		 	 <c:if test="${claimDebriefForm.networkConnectedFlag}">
													   		 		selected
													   		 	 </c:if>
													  		 ><spring:message code="selection.label.yes" /></option>
														</select>
													</td>
												</tr>
												<tr><td height="5px" colspan="2"></td></tr>
												
												 <c:if test="${!claimDebriefForm.networkConnectedFlag}">
													<tr>
												        <td align="right"><div id="ipAddressLableDiv" style="display:none;"><spring:message code="claim.label.ipAddress" />&nbsp;</div></td>
												        <td align="left">
												        	<input type="text" name="activity.serviceRequest.asset.ipAddress" id="ipAddressInput" style="display:none;" class="width-190px;"  value="${claimDebriefForm.activity.serviceRequest.asset.ipAddress}" onblur="validateLength(0,100,this);">
												        </td>
													</tr>
													<tr><td height="5px" colspan="2"></td></tr>
													<tr>
												        <td align="right"><div id="macAddressLableDiv" style="display:none;"><spring:message code="claim.label.macAddress" />&nbsp;</div></td>
												        <td align="left">
												        	<input type="text" name="activity.serviceRequest.asset.macAddress" id="macAddressInput" style="display:none;" class="width-190px;"  value="${claimDebriefForm.activity.serviceRequest.asset.macAddress}" onblur="validateLength(0,100,this);">
												        </td>
													</tr>
												</c:if>
												
												 <c:if test="${claimDebriefForm.networkConnectedFlag}">
													<tr>
												        <td align="right"><div id="ipAddressLableDiv"><spring:message code="claim.label.ipAddress" />&nbsp;</div></td>
												        <td align="left">
												        	<input type="text" name="activity.serviceRequest.asset.ipAddress" id="ipAddressInput" class="width-190px"  value="${claimDebriefForm.activity.serviceRequest.asset.ipAddress}" onblur="validateLength(0,100,this);">
												        </td>
													</tr>
													<tr><td height="5px" colspan="2"></td></tr>
													<tr>
												        <td align="right"><div id="macAddressLableDiv"><spring:message code="claim.label.macAddress" />&nbsp;</td>
												        <td align="left">
												        	<input type="text" name="activity.serviceRequest.asset.macAddress" id="macAddressInput" class="width-190px"  value="${claimDebriefForm.activity.serviceRequest.asset.macAddress}" onblur="validateLength(0,50,this);">
												        </td>
													</tr>
												</c:if>
												
											</table>
											</div>
										</td>
									</tr>
								</table>
								</c:if>
								
								<c:if test="${claimDebriefForm.removeDeviceSectionFlag == 'true'}">
								<table width="50%">
									<tr>
								        <td colspan="2" align="left"><h3>&nbsp;&nbsp;<spring:message code="claim.label.removedDeviceInformation" /></h3></td>
									</tr>
									<tr>
								        <td align="right"><b><span class="required">* </span></b><spring:message code="claim.label.printerWorkingCondition" />&nbsp;</td>
								        <td align="left">
											<select id="deviceConditionSelect" name="activity.debrief.deviceCondition.value">
												<option value=""></option>
												<c:forEach items="${claimDebriefForm.workingConditionMap}" var="workingConditionaEntry" varStatus="loop">
													<option value="${workingConditionaEntry.key}" > ${workingConditionaEntry.value}</option>
												</c:forEach>
											</select>
										</td>
									</tr>
									<tr><td height="5px" colspan="4"></td></tr>
									<tr>
								        <td align="right"><spring:message code="claim.label.pageCountAll" />&nbsp;</td>
								        <td align="left"><input type="text" name="activity.serviceRequest.asset.newPageCount" id="pageCountAllInput" class="width-190px" onafterpaste="clearNoNumAndPoint(this)" onkeyup="clearNoNumAndPoint(this)"></td>
									</tr>
									<tr><td height="5px" colspan="4"></td></tr>
									<tr>
								        <td align="right"><spring:message code="claim.label.lexmarkTag" />&nbsp;</td>
								        <td align="left"><input type="text" name="activity.serviceRequest.asset.assetTag" id="lexmarkTagInput" class="width-190px" onblur="validateLength(0,50,this);"></td>
									</tr>							
									<tr>
										<td align="right"><br></br></td>
								        <td align="left"><br></br></td>
									</tr>
								</table>
									
								<br/>
								<br/>
									
								<table width="100%">
									<tr>
								        <td colspan="2" align="left"><h3>&nbsp;&nbsp;<spring:message code="claim.label.intalledDeviceInformation" /></h3></td>
									</tr>
									<tr>
										<td align="left" valign="top" width="50%">
											<table width="100%">
												<tr>
													<td width="50%" align="right" valign="top"><b><span class="required">* </span></b><spring:message code="claim.label.Asset" />&nbsp;</td>
											       	<td width="50%" align="left" valign="top">
											        	<select id="assetListSelect" onchange="assetListSelectChange()" >
													  		<option selected value=""></option>
															<c:forEach items="${claimDebriefForm.assetList}" var="assetTemp" varStatus = "status" >
															<option value="${status.index}" >${assetTemp.serialNumber}</option>
															</c:forEach>
													  		<option value="other"><spring:message code="claim.label.dropdownlist.optionvalue.technician.other" /></option>
														</select>
													</td>
												</tr>
												<tr><td height="5px" colspan="2"></td></tr>
												<tr>
													<td align="right"><div id="machineTypeLableDiv" style="display:none;"><b><span class="required">* </span></b><spring:message code="claim.label.machineTypeModel" />&nbsp;</div></td>
								       				<td align="left"><div id="machineTypeInputDiv" style="display:none;"><input type="text" onblur="validateLength(0,100,this);" id="machineTypeInput" onChange="validateManualAsset();" onkeypress= "javascript:if(event.keyCode==13){validateManualAsset();}" class="width-190px"/></div></td>	
												</tr>
												<tr><td height="5px" colspan="2"></td></tr>
												<tr id="trMachineType" style="display: none;" >
													<td align="center" colspan="2">
														<div align="left" class="width-360px">
															<label id="machineTypeLoadingIndicator"><spring:message code="deviceSelection.description.validatingMachineType"/><img  src = "<html:imagesPath/>loading-icon.gif"; /></label>
															<label for="machineType" id="machineTypeErrorMsg" class="color-red" />
														</div>
													</td>
												</tr>
												<tr>
													<td align="right"><div id="serialNumberLableDiv" style="display:none;"><span class="required">* </span></b><spring:message code="claim.label.serialNumber" />&nbsp;</div></td>
													<td align="left"><div id="serialNumberInputDiv" style="display:none;"><input type="text"  id="serialNumberInput" onChange="validateInstalledPrinterSerialNumber();" onkeypress= "javascript:if(event.keyCode==13){validateInstalledPrinterSerialNumber();}" class="width-190px"></div></td>
												</tr>
												<tr><td height="5px" colspan="2"></td></tr>
												<tr id="trSerialNumber" style="display: none;">
													<td align="center" colspan="2">
														<div align="left" class="width-360px">
															<label id="serialNumberLoadingIndicator"><spring:message code="deviceSelection.description.validatingSerialNumber" /><img  src = "<html:imagesPath/>loading-icon.gif"; /></label>
															<label for="serialNumber" id="serialNumberErrorMsg" class="color-red" />
														</div>
													</td>
												</tr>
												<tr>
													<td align="right"><spring:message code="claim.label.pageCountAll" />&nbsp;</td>
								   					<td align="left"><input type="text" name="activity.debrief.installedAsset.newPageCount" id="installedPageCountAll" class="width-190px" onafterpaste="clearNoNumAndPoint(this)" onkeyup="clearNoNumAndPoint(this)"></td>
												</tr>
												<tr><td height="5px" colspan="2"></td></tr>
												<tr>
													<td align="right"><spring:message code="claim.label.lexmarkTag" />&nbsp;</td>
											   		<td align="left"><input type="text" name="activity.debrief.installedAsset.assetTag" id="installedLexmarkTag" class="width-190px" onblur="validateLength(0,50,this);"></td>
												</tr>
											</table>
										</td>
										
										<td align="left" valign="top" width="50%">
											<table width="100%">
												<tr>
													<td width="50%" align="right" valign="top"><spring:message code="claim.label.networkConnected" />&nbsp;</td>
											       	<td width="50%" align="left" valign="top">
											        	<select id="networkConnectedSelect" name="activity.debrief.installedAsset.networkConnectedFlag" onchange="networkConnectedSelectChange()">
													  		 <option value="false" selected><spring:message code="selection.label.No" /></option>
													  		 <option value="true"><spring:message code="selection.label.yes" /></option>
														</select>
													</td>
												</tr>
												<tr><td height="5px" colspan="2"></td></tr>
												<tr>
													<td align="right"><div id="ipAddressLableDiv" style="display:none;"><spring:message code="claim.label.ipAddress" />&nbsp;</div></td>
								      				<td align="left"><input type="text" name="activity.debrief.installedAsset.ipAddress" id="ipAddressInput" style="display:none;" class="width-190px" onblur="validateLength(0,100,this);"></td>
												</tr>
												<tr><td height="5px" colspan="2"></td></tr>
												<tr>
											    	<td align="right"><div id="macAddressLableDiv" style="display:none;"><spring:message code="claim.label.macAddress" />&nbsp;</div></td>
											        <td align="left"><input type="text" name="activity.debrief.installedAsset.macAddress" id="macAddressInput" style="display:none;" class="width-190px" onblur="validateLength(0,50,this);"></td>
												</tr>
											</table>
										</td>
									</tr>
								</table>
								</c:if>							
							</div>
						</div>
					</div>	
				</div>
				        	    	</div>
				        	    </c:if>
				        	    <c:if test="${claimDebriefForm.activity.exchangeFlag=='false'}">
				        	    	<style>
				        	    	.claim_request_calc_icon{width:100% !important;}
				        	    	.claim_request_calc_icon dd{float:left;clear:both;}
				        	    	.claim_request_calc_icon dd div input{float:left;}
				        	    	</style>
				        	    	<div class="columns two">
									<dl class="claim_request_calc_icon">
										<dd><b><span class="required">* </span><spring:message code="claim.label.repairDescription"/></b></dd>
										<dd><textarea id="repairDescriptionInput" name="activity.debrief.repairDescription" class="width-400px" rows="5" cols="25" onblur="validateLength(0,2000,this);">${claimDebriefForm.activity.debrief.repairDescription}</textarea></dd><br/>
										<dd><b><span class="required">* </span><spring:message code="claim.label.resolutionCode"/></b></dd>
										<dd>
											<select id="resolutionCodeSelect" name="activity.debrief.resolutionCode.value">
												<option value=""></option> 
												<option value="${claimDebriefForm.activity.debrief.resolutionCode}" selected="selected">${claimDebriefForm.activity.debrief.resolutionCode}</option>
												<c:forEach items="${claimDebriefForm.resolutionCodeMap}" var="resolutionCodeEntry" varStatus="loop">
													<option value="${resolutionCodeEntry.key}" > ${resolutionCodeEntry.value}</option>
												</c:forEach>
											</select>
										</dd><br/>
										<dd><b><span class="required">* </span><spring:message code="claim.label.dateServiceRequested"/></b></dd>										
										<dd>
											<div>
												<input type="text" readonly="readonly" onmouseup="show_cal('dateServicedRequested', null, convertDateToString(new Date()),true);" size="16" id="dateServicedRequested" value="${claimDebriefForm.formattedServiceRequestedDate}" name="dateServicedRequested" class="dateTimeInput">
												<img onclick="show_cal('dateServicedRequested', null, convertDateToString(new Date()),true)"   src="<html:imagesPath/>transparent.png" class="ui-icon calendar-icon img-localized-begin-date" id="imgLocalizedBeginDate">	            
											</div>
										</dd>
										<dd><b><span class="required">* </span><spring:message code="claim.label.dateServiced(Start)"/></b></dd>										
										<dd>
											<div>
												<input type="text" readonly="readonly" onmouseup="show_cal('serviceStartDate', null, convertDateToString(new Date()),true);" size="16" id="serviceStartDate" value="${claimDebriefForm.formattedServiceStartDate}" name="serviceStartDate" class="dateTimeInput">
												<img onclick="show_cal('serviceStartDate', null, convertDateToString(new Date()),true)"   src="<html:imagesPath/>transparent.png" class="ui-icon calendar-icon img-localized-begin-date" id="imgLocalizedBeginDate">	            
											</div>
										</dd>
										<dd><b><span class="required">* </span><spring:message code="claim.label.dateServiced(End)"/></b></dd>
										<dd>
											<div>
												<input type="text" readonly="readonly" onmouseup="show_cal('serviceEndDate', null, convertDateToString(new Date()),true);" size="16" id="serviceEndDate" value="${claimDebriefForm.formattedServiceEndDate}" name="serviceEndDate" class="dateTimeInput">
												<img onclick="show_cal('serviceEndDate', null, convertDateToString(new Date()),true)"  src="<html:imagesPath/>transparent.png" class="ui-icon calendar-icon img-localized-begin-date" id="imgLocalizedEndDate">	            
											</div>
										</dd>
										<dd><b><span class="required">* </span><spring:message code="claim.label.printerWorkingCondition"/></b></dd>
										<dd>
											<select id="deviceConditionSelect" name="activity.debrief.deviceCondition.value">
												<option value=""></option>
												<option value="${claimDebriefForm.activity.debrief.deviceCondition.name}" selected="selected">${claimDebriefForm.activity.debrief.deviceCondition.name}</option> 
												<c:forEach items="${claimDebriefForm.workingConditionMap}" var="workingConditionEntry" varStatus="loop">
													<option value="${workingConditionEntry.key}" > ${workingConditionEntry.value}</option>
												</c:forEach>
											</select>
										</dd>
										<!-- -------------added by ragesree 2097------------ -->
										<dd><b><spring:message code="claim.label.pageCountAll" /></b></dd>
										<dd><input type="text" name="activity.debrief.pageCountAll" id="pageCountAllInput"  value="${claimDebriefForm.activity.pageCountAll}" size="16" onblur="validateLength(0,9,this);" onafterpaste="clearNoStringPoint(this)" onkeyup="clearNoStringPoint(this)"></dd> 
										
									</dl>
								</div>
				        	    </c:if>
				         		
							</div>
						<script>
						function clearNoStringPoint(ele){
							ele.value = ele.value.replace(/[^\d]/g,"");		
						}
						
						</script>
						<div class="portlet-footer">	
							<div class="portlet-footer-inner"></div>
						</div>
					</div>
			        <!-- Close Out Claim End -->	 
			       
			        <!-- Parts and Tools Start -->		 
			        <c:if test="${claimDebriefForm.activity.exchangeFlag=='false' && claimDebriefForm.activity.serviceType!='Maintenance Kit Install'}">
			        <div class="portlet-wrap width-100per" id="partAndTool">
						<div class="portlet-header">
							<h3><spring:message code="claim.label.partsAndTools"/></h3>
						</div><!-- portlet-header -->
						<div class="portlet-wrap-inner" >
							<div class="columns">
								<div id="gridCCVPartDebrief" class="width-100per"></div>
								<div id="pagingPartDebrief"></div>
							</div>
							<c:if test="${claimDebriefForm.activity.partnerAccount.orderPartsDebriefFlag}">
							<div class="columns div-style5" style="width: expression(this.width > 1200 ? 1200: true;">
								<table>
									<tr>
										<td>
											<b><label><spring:message code="claim.label.addAdditionalPartsUsed"/>:</label></b>
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
							<div id="additionalPartsGridDiv">
								<div class="columns">
									<div id="qtyValidate" class="qty-validate2" ></div>
									<div id="gridAdditionalParts" class="width-100per"></div>
									<div id="pagingAdditionalParts"></div>
								</div>
							</div>
							<!-- partner Address div Start -->	
							<div id="partnerAddressDiv" style="display:none">
								<table>
									<tr>
										<td valign="top" align="left" width="80px">
											<b><spring:message code="claim.label.shipTo"/></b>
										</td>
										<td width="590px">
											<table width="590px" id="addressInfo" name="addressInfo" >
												<tr>
													<td valign="bottom">
														<util:addressOutput value="${claimDebriefForm.activity.partnerAccount.address}"/>
													</td>
												</tr>
												<tr align="left">
													<td style="display: none"><form:input
														path="activity.shipToAddress.addressId" /> <form:input
														path="activity.shipToAddress.addressName" /> <form:input
														path="activity.shipToAddress.addressLine1" /> <form:input
														path="activity.shipToAddress.addressLine2" /> <form:input
														path="activity.shipToAddress.addressLine3" /> <form:input
														path="activity.shipToAddress.city" /> <form:input
														path="activity.shipToAddress.state" /> <form:input
														path="activity.shipToAddress.province" /> <form:input
														path="activity.shipToAddress.stateProvince" /> <form:input
														path="activity.shipToAddress.country" /> <form:input
														path="activity.shipToAddress.postalCode" />
													</td>
												</tr>
											</table>
										</td>
									</tr>
								</table>
							</div>
							<!-- partner Address div end -->	
							<!-- CI-6 Changes added for Expedite Start -->
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
							<!-- CI-6 Changes added for Expedite End -->
								<script type="text/javascript">
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
								var url="<portlet:renderURL windowState='<%= LiferayWindowState.POP_UP.toString() %>'>"+
					  			"<portlet:param name='isMaintenance' value='true' />"+
					         	"<portlet:param name='action' value='showPartAndToolPage' />"+
					         	"</portlet:renderURL>";
								function partListPopup(){
									jQuery(window).scrollTop(0);
									partListPopUpWindow.show();
									jQuery(".aui button.close, .aui button.btn.close").hide();
									partListPopUpWindow.titleNode.html("<spring:message code='claim.label.partsList'/>");
									partListPopUpWindow.io.set('uri',url);
									partListPopUpWindow.io.start();
									
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
								//CI-6 Changes Partha End
								
								/*********************************************************************************
								******************************Addintional parts grid script*************************
								**********************************************************************************/
								additionalPartsGrid = new dhtmlXGridObject('gridAdditionalParts');
								additionalPartsGrid.setImagePath("<html:imagesPath/>gridImgs/");
								additionalPartsGrid.setHeader(autoAppendPlaceHolder(autoApplyRequiredStyle('<spring:message code="claim.headerList.partsAndTools.debrief"/>'),11));
								additionalPartsGrid.setColAlign("left,left,left,left,left,left,left,left,left,left,left");
								additionalPartsGrid.setInitWidths("100,40,100,*,100,*,100,*,110,*,*");
								additionalPartsGrid.setColTypes("ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro");
								additionalPartsGrid.setColSorting("na,str,str,na,na,na,str,na,na,na,na");
								
								additionalPartsGrid.enableAutoWidth(true);
								additionalPartsGrid.enableAutoHeight(true);
								additionalPartsGrid.init(); 
								additionalPartsGrid.prftInit();
								/* Added by sankha for LEX:AIR00070359*/
								additionalPartsGrid.setColumnHidden(5,true);
								additionalPartsGrid.setColumnHidden(6,true);
								additionalPartsGrid.setColumnHidden(7,true);
								/* End */
								additionalPartsGrid.enablePaging(true, 9999, 6, "pagingAdditionalParts", true);
								additionalPartsGrid.setSkin("light");
								additionalPartsGrid.setPagingSkin("bricks");
								additionalPartsGrid.enableAutoSavingToDB("${saveCustomizedGridSettingURL}");
								additionalPartsGrid.attachEvent("onMouseOver", function(id,ind){		
									return false;
								});
								additionalPartsGrid.attachEvent("onRowAdded", function(rId){
									document.getElementById("partNumber").value = "";
								}); 
								additionalPartsGrid.attachEvent("onAfterRowDeleted", function(id,pid){
									document.getElementById("partNumber").value = "";
					     		});
	
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
									url+="&partNumber="+partNumber+"&modelNumber="+modelNumber+"&organizationId="+orgId+"&isMaintenance=true";
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
											additionalPartsGrid.deleteRow(ind);
											showMessageForQty();
										}
									});
								}
	
								function showPartList(){
									showOverlayIE6();
									partListPopup();
									bindingClosePopupIE6();
								}
								function addRowByPart(part){
									var rowNumber = additionalPartsGrid.getRowsNum();
									if(checkSpecialPartCount(part[0])){
										if(part[3]!="null" && part[3]!=null && part[3]!= ""){
											var replaceMessage = '<spring:message code="claim.message.partNumber"/>&nbsp;' +part[3]+'&nbsp;<spring:message code="claim.message.rePlacePartNumber"/>&nbsp;' +part[0]+'. <spring:message code="claim.message.partCountMoreThan5"/>';
											jAlert(replaceMessage, "");
										}else{
											jAlert('<spring:message code="claim.message.partCountMoreThan5"/>', "");
										}
										return;
									}
									if(part[3]!="null" && part[3]!=null && part[3]!= ""){
										var replaceMessage = '<spring:message code="claim.message.partNumber"/>&nbsp;' +part[3]+'&nbsp;<spring:message code="claim.message.rePlacePartNumber"/>&nbsp;' +part[0];
										jAlert(replaceMessage, "");
									}
									var id = additionalPartsGrid.getUID();
									var str =[partDate,'1',part[0],part[1]
										,'<table><tr><td><select id="partStatus_'+id+'" style="width:80px;" onchange=\"selectStatus(this &#44;\''+id+'\');\"><option value=""></option>'+generateOption(partStatus)+'</select></td></tr></table>'
										,'<table><tr><td><select onChange="retrieveErrorCode2(\''+id+'\');" style="width:100px;display:none;" id="errorCode1_'+id+'"><option value=""></option>'+generateOption(errorCode1)+'</select></td></tr></table>'
										,'<table><tr><td><label id="errorCode2Loading_'+id+'" style="display: none;"><img  src = "<html:imagesPath/>loading-icon.gif"; />&nbsp;<spring:message code="label.loadingNotification" /></label><select style="width:100px;display:none;" id="errorCode2_'+id+'"></select></td></tr></table>'
										,(part[2]=="true"?'<spring:message code="claim.lebel.returnRequired.yes"/>':'<spring:message code="claim.lebel.returnRequired.no"/>')
										,((part[2]=="false")?'':'<table><tr><td><select style="width:100px;" id="reCarrier_'+id+'"><option value=""></option>'+generateOption(carrier)+'</select></td></tr></table>')
										,((part[2]=="false")?'':'<table><tr><td><input id="reTracingNum_'+id+'"  onblur="validateLength(1 &#44; 30 &#44; this);" style="width:110px;" type="text" value=""/></td></tr></table>')
										,'<a href="javascript:deletePartRow(\''+id+'\');"><img class="ui-icon trash-icon" src="<html:imagesPath/>transparent.png"></a><input id="partId_'+id+'" type="hidden" value="'+part[4]+'"/>'];
									additionalPartsGrid.addRow(id,str,0);
									showMessageForQty();
									
								}
	
								function addRowByPartList(part){
									document.getElementById("partListGridDiv").style.display = "none";
									document.getElementById("partListLoadingDiv").style.display = "";
									searchPart(part[0]);
								}
							
								function checkSpecialPartCount(partNumber){
									var debriefRowNumber = partsAndToolsDebriefGrid.getRowsNum();
									var additionalRowNumber = additionalPartsGrid.getRowsNum();
									var specialPartCount = 0;
									if(debriefRowNumber!=0){
										for(i = 0;i< debriefRowNumber ;i++){
											if(partNumber == partsAndToolsDebriefGrid.cellByIndex(i,2).cell.innerHTML)
												specialPartCount++;
										}
									}
									if(additionalRowNumber!=0){
										for(i = 0;i< additionalRowNumber ;i++){
											if(partNumber == additionalPartsGrid.cellByIndex(i,2).cell.innerHTML)
												specialPartCount++;
										}
									}
									if(specialPartCount>4)
										return true;
									
									return false;
								}
								
								/*********************************************************************************
								******************************Partner address grid script*************************
								**********************************************************************************/
								function addPartnerAddressElement(ai, an, al1, al2, al3, city, state, province, stateProvince, country, zip, currentFavStatus){
									document.getElementById("activity.shipToAddress.addressId").value=ai;
									document.getElementById("activity.shipToAddress.addressName").value=an;
									document.getElementById("activity.shipToAddress.addressLine1").value=al1;
									document.getElementById("activity.shipToAddress.addressLine2").value=al2;
									document.getElementById("activity.shipToAddress.addressLine3").value=al3;
									document.getElementById("activity.shipToAddress.city").value=city;
									document.getElementById("activity.shipToAddress.state").value=state;
									document.getElementById("activity.shipToAddress.province").value=province;
									document.getElementById("activity.shipToAddress.stateProvince").value=(stateProvince=="nostate"?null:stateProvince);
									document.getElementById("activity.shipToAddress.country").value=country;
									document.getElementById("activity.shipToAddress.postalCode").value=zip;
								}
								
								jQuery(document).ready(function() {
									// changes for db saving and retrieving	start
									//gridCDtlVRPRequestGrid.enableAutoSavingToDB("${saveCustomizedGridSettingURL}");
									additionalPartsGrid.attachEvent("onAfterCMove",function(a,b){																	
										additionalPartsGrid.enableAutoSavingToDB("${saveCustomizedGridSettingURL}");										
									});	
									<c:forEach var="gridSettingVar" items="${gridSettingList}" varStatus="status">
									<c:if test="${gridSettingVar.gridId == 'gridAdditionalParts'}">														
									var colsOrder = "${gridSettingVar.colsOrder}";									
									additionalPartsGrid.loadOrder(colsOrder);
									
									</c:if>
									</c:forEach>
									// changes for db saving and retrieving	end
									additionalPartsGrid.loadXMLString("<?xml version=\"1.0\" ?><rows></rows>");
									addPartnerAddressElement("${claimDebriefForm.activity.partnerAccount.address.addressId}", "${claimDebriefForm.activity.partnerAccount.address.addressName}", 
											 "${claimDebriefForm.activity.partnerAccount.address.addressLine1}", "${claimDebriefForm.activity.partnerAccount.address.addressLine2}", "${claimDebriefForm.activity.partnerAccount.address.addressLine3}", 
											 "${claimDebriefForm.activity.partnerAccount.address.city}", 
											 "${claimDebriefForm.activity.partnerAccount.address.state}", 
											 "${claimDebriefForm.activity.partnerAccount.address.province}", 
											 "${claimDebriefForm.activity.partnerAccount.address.stateProvince}", 
											 "${claimDebriefForm.activity.partnerAccount.address.country}", 
											 "${claimDebriefForm.activity.partnerAccount.address.postalCode}", 
											 "");
								});
							</script>
						</c:if>
						</div><!-- portlet-wrap-inner -->
						<div id="partToolContent" style="display:none;"></div>
						<div class="portlet-footer">
							<div class="portlet-footer-inner"></div><!-- portlet-footer-inner -->
						</div><!-- portlet-footer -->
					</div>
					</c:if>
					<script type="text/javascript">
					var preOption = 1;
					var modelNumber = "${claimDebriefForm.activity.serviceRequest.asset.modelNumber}";
					var orgId = "${claimDebriefForm.activity.partnerAccount.organizationID}";
					var partStatus = new Array();
					var partStatusValue = new Array();
					var errorCode1 = new Array();
					var errorCode1Value = new Array();
					var carrier = new Array();
					var carrierValue = new Array();
					var qtyList = new Array();
					var partDate = localizeFormatDate(new Date());
		
					<c:forEach items="${claimDebriefForm.partStatusList}" var="partStatus" varStatus = "status" >
						partStatus[${status.index}] = "${partStatus.value}";
						partStatusValue[${status.index}] = "${partStatus.key}";
					</c:forEach>
		
					<c:forEach items="${claimDebriefForm.errorCode1List}" var="errorCode1" varStatus = "status" >
						errorCode1[${status.index}] = "${errorCode1.value}";
						errorCode1Value[${status.index}] = "${errorCode1.key}";
					</c:forEach>
		
					<c:forEach items="${claimDebriefForm.carrierList}" var="carrier" varStatus = "status" >
						carrier[${status.index}] = "${carrier.value}";
						carrierValue[${status.index}] = "${carrier.key}";
					</c:forEach>
					
					for(b = 1;b<=5;b++){
						qtyList[b] = b;
					}
					
					partsAndToolsDebriefGrid = new dhtmlXGridObject('gridCCVPartDebrief');
					partsAndToolsDebriefGrid.setImagePath("<html:imagesPath/>gridImgs/");
					//CI-6 Changes Start
					partsAndToolsDebriefGrid.setHeader(autoAppendPlaceHolder(autoApplyRequiredStyle('<spring:message code="claim.headerList.partsAndTools.debrief.expedite"/>'),12));
					partsAndToolsDebriefGrid.setColAlign("left,left,left,left,left,left,left,left,left,left,left,left");
					partsAndToolsDebriefGrid.setInitWidths("90,50,50,70,*,110,*,*,100,*,*,*");
					partsAndToolsDebriefGrid.setColTypes("ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro");
					partsAndToolsDebriefGrid.setColSorting("na,na,str,str,str,na,na,na,str,na,na,na");
					
					//partsAndToolsDebriefGrid.setHeader(autoAppendPlaceHolder(autoApplyRequiredStyle('<spring:message code="claim.headerList.partsAndTools.debrief"/>'),11));
					//partsAndToolsDebriefGrid.setColAlign("left,left,left,left,left,left,left,left,left,left,left");
					//partsAndToolsDebriefGrid.setInitWidths("90,50,70,100,110,110,140,100,130,150,*");
					//partsAndToolsDebriefGrid.setColTypes("ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro");
					//partsAndToolsDebriefGrid.setColSorting("na,na,str,str,na,na,na,str,na,na,na");
					//CI-6 Changes End
					partsAndToolsDebriefGrid.enableAutoWidth(true);
					partsAndToolsDebriefGrid.enableAutoHeight(true);
					partsAndToolsDebriefGrid.enableMultiline(true);
					partsAndToolsDebriefGrid.enablePaging(true, 9999, 6, "pagingPartDebrief", true);
					partsAndToolsDebriefGrid.init(); 
					partsAndToolsDebriefGrid.prftInit();
					/* Added by sankha for LEX:AIR00070359*/
					partsAndToolsDebriefGrid.setColumnHidden(6,true);
					partsAndToolsDebriefGrid.setColumnHidden(7,true);
					partsAndToolsDebriefGrid.setColumnHidden(8,true);
					/* End */
					partsAndToolsDebriefGrid.setSkin("light");
					// move enabled for MPS CI 14.10 CRM-DAmster201408041344
					partsAndToolsDebriefGrid.enableColumnMove(true);
					partsAndToolsDebriefGrid.setPagingSkin("bricks");
					partsAndToolsDebriefGrid.enableAutoSavingToDB("${saveCustomizedGridSettingURL}");					
					partsAndToolsDebriefGrid.attachEvent("onMouseOver", function(id,ind){		
						return false;
					});

					function showMessageForQty(){
						if(checkTotalQTY())
							document.getElementById("qtyValidate").innerHTML = "<spring:message code='claim.message.qtyIsGreaterThan5'/>";
						else 
							document.getElementById("qtyValidate").innerHTML = "";
					}
					
					function checkTotalQTY(){
						var debriefRowNumber = partsAndToolsDebriefGrid.getRowsNum();
						var additionalRowNumber = "${claimDebriefForm.activity.partnerAccount.orderPartsDebriefFlag}"=="true"?additionalPartsGrid.getRowsNum():0;
						if(debriefRowNumber+additionalRowNumber>5){
							return true;
						}
						return false;
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
						var optionStr = "";
						for(var i = 0;i<arr.length;i++){
							if(arr[i]==null)
								continue;
							optionStr +='<option value="'+i+'">'+arr[i]+'</option>';
						}
						return optionStr;
					}
					
					function generateOptionAndSetValue(displayArr,valueArr,value){
						var optionStr = "";
						for(var i = 0;i<displayArr.length;i++){
							if(displayArr[i]==null)
								continue;
							if(value == valueArr[i]){
								optionStr +='<option value="'+i+'" selected>'+displayArr[i]+'</option>';
							}else{
								optionStr +='<option value="'+i+'">'+displayArr[i]+'</option>';
							}
						}
						return optionStr;
					}
					
					function validatePartAndTool(){
						var result = true;
						var debriefRowNumber = partsAndToolsDebriefGrid.getRowsNum();
						var additionalRowNumber = "${claimDebriefForm.activity.partnerAccount.orderPartsDebriefFlag}"=="true"?additionalPartsGrid.getRowsNum():0;
						for(var i = 0 ;i < debriefRowNumber ;i++){
							var ind = partsAndToolsDebriefGrid.getRowId(i);
							
							var partsStatus = '#partStatus_'+ind;
							var errorCode1 = '#errorCode1_'+ind;
							var errorCode2 = '#errorCode2_'+ind;
							
							jQuery(partsStatus).removeClass('errorColor');
							jQuery(errorCode1).removeClass('errorColor');
							jQuery(errorCode2).removeClass('errorColor');
							
							if(document.getElementById("partStatus_"+ind).value ==""){
								var errorMsg = "<spring:message code='claim.label.partsAndTools'/>: <spring:message code='claim.label.row'/> "+(i+1)+" <spring:message code='claim.errorMsg.partStatusRequired'/>";
								showError(errorMsg, null, true);
								jQuery(partsStatus).addClass('errorColor');
								result = false;
							}//else if(partStatusValue[document.getElementById("partStatus_"+ind).value].toLowerCase() == "used" && (document.getElementById("errorCode1_"+ind).value == "" || document.getElementById("errorCode2_"+ind).value == "")){
								//var errorMsg = "<spring:message code='claim.label.partsAndTools'/>: <spring:message code='claim.label.row'/> "+(i+1)+" <spring:message code='claim.errorMsg.errorCodeRequired'/>";
								//showError(errorMsg, null, true);
								//if(document.getElementById("errorCode1_"+ind).value == ""){
								//	jQuery(errorCode1).addClass('errorColor');
								//}
								//if(document.getElementById("errorCode2_"+ind).value == ""){
								//	jQuery(errorCode2).addClass('errorColor');
								//}
								//result = false;
							//}
						}
						for(var i = 0 ;i < additionalRowNumber ;i++){
							var ind = additionalPartsGrid.getRowId(i);
							
							var _partsStatus = '#partStatus_'+ind;
							var _errorCode1 = '#errorCode1_'+ind;
							var _errorCode2 = '#errorCode2_'+ind;
							
							jQuery(_partsStatus).removeClass('errorColor');
							jQuery(_errorCode1).removeClass('errorColor');
							jQuery(_errorCode2).removeClass('errorColor');
							
							if(document.getElementById("partStatus_"+ind).value ==""){
								var errorMsg = "<spring:message code='claim.label.additionalPartsAndTools'/>: <spring:message code='claim.label.row'/> "+(i+1)+" <spring:message code='claim.errorMsg.partStatusRequired'/>";
								showError(errorMsg, null, true);
								jQuery(_partsStatus).addClass('errorColor');
								result = false;
							}//else if(partStatusValue[document.getElementById("partStatus_"+ind).value].toLowerCase() == "used" && (document.getElementById("errorCode1_"+ind).value == "" || document.getElementById("errorCode2_"+ind).value == "")){
								//var errorMsg = "<spring:message code='claim.label.additionalPartsAndTools'/>: <spring:message code='claim.label.row'/> "+(i+1)+" <spring:message code='claim.errorMsg.errorCodeRequired'/>";
								//showError(errorMsg, null, true);
								//if(document.getElementById("errorCode1_"+ind).value == ""){
								//	jQuery(_errorCode1).addClass('errorColor');
								//}
								//if(document.getElementById("errorCode2_"+ind).value == ""){
								//	jQuery(_errorCode2).addClass('errorColor');
								//}
								//result = false;
							//}
						}
						return result;
					}
					
					function generatePartAndTool(){
						document.getElementById("partToolContent").innerHTML="";
						var rows = partsAndToolsDebriefGrid.getRowsNum();
						
						for(var i = 0 ;i < rows ;i++){
							var ind = partsAndToolsDebriefGrid.getRowId(i);
							if(document.getElementById("qty_"+ind)){
								var qtyValue = document.getElementById("qty_"+ind).value;
							}else{
								var qtyValue = partsAndToolsDebriefGrid.cellByIndex(i,1).cell.innerHTML;
							}
							var reRequire = partsAndToolsDebriefGrid.cellByIndex(i,8).cell.innerHTML;
							//alert("reRequire "+reRequire);
							var statusValueKey = document.getElementById("partStatus_"+ind).value;
							var statusValue = partStatusValue[statusValueKey];
							if (typeof(statusValue) != "undefined"){
							if(statusValue.toLowerCase() == "used" && document.getElementById("errorCode1_"+ind).style.display!="none"){
								var errorCode1Key = document.getElementById("errorCode1_"+ind).value;
								var errorCode2 = document.getElementById("errorCode2_"+ind).value;
								var errorCode1 = errorCode1Value[errorCode1Key];
								
								var input = document.createElement("input");
								input.id = "activity.debrief.partDebriefList["+i+"].errorCode1.value"; 
								input.name = "activity.debrief.partDebriefList["+i+"].errorCode1.value"; 
								input.value = errorCode1;
								document.getElementById("partToolContent").appendChild(input);
							
								var input = document.createElement("input");
								input.id = "activity.debrief.partDebriefList["+i+"].errorCode2.value"; 
								input.name = "activity.debrief.partDebriefList["+i+"].errorCode2.value"; 
								input.value = errorCode2;
								document.getElementById("partToolContent").appendChild(input);
							}
							}
							else
							{
								statusValue = '';
							}
							if(document.getElementById("partDebriefId_"+ind)){
								var input = document.createElement("input");
								input.id = "activity.debrief.partDebriefList["+i+"].partLineItemId"; 
								input.name = "activity.debrief.partDebriefList["+i+"].partLineItemId"; 
								input.value = document.getElementById("partDebriefId_"+ind).value;
								document.getElementById("partToolContent").appendChild(input);
		
								var input = document.createElement("input");
								input.id = "activity.debrief.partDebriefList["+i+"].partLineItemUpdateFlag"; 
								input.name = "activity.debrief.partDebriefList["+i+"].partLineItemUpdateFlag"; 
								input.value = document.getElementById("partLineItemUpdateFlag_"+ind).value;
								document.getElementById("partToolContent").appendChild(input);
							}
							
							if('<spring:message code="claim.lebel.returnRequired.yes"/>'==reRequire){
								var reCarrierIndex = document.getElementById("reCarrier_"+ind).value;
								var reCarrier = carrierValue[reCarrierIndex];
								if (typeof(reCarrier) == "undefined"){
									reCarrier='';
								}
								var reTracingNum = document.getElementById("reTracingNum_"+ind).value;
								if (typeof(reTracingNum) == "undefined"){
									reTracingNum='';
								}
								var input = document.createElement("input");
								input.id = "activity.debrief.partDebriefList["+i+"].carrier.value"; 
								input.name = "activity.debrief.partDebriefList["+i+"].carrier.value"; 
								input.value = reCarrier;
								document.getElementById("partToolContent").appendChild(input);
							
								var input = document.createElement("input");
								input.id = "activity.debrief.partDebriefList["+i+"].trackingNumber"; 
								input.name = "activity.debrief.partDebriefList["+i+"].trackingNumber"; 
								input.value = reTracingNum;
								document.getElementById("partToolContent").appendChild(input);
							}			
							
							var input = document.createElement("input");
							input.id = "activity.debrief.partDebriefList["+i+"].quantity"; 
							input.name = "activity.debrief.partDebriefList["+i+"].quantity"; 
							input.value = parseInt(qtyValue);
							document.getElementById("partToolContent").appendChild(input);
							
							var input = document.createElement("input");
							input.id = "activity.debrief.partDebriefList["+i+"].partDisposition.value"; 
							input.name = "activity.debrief.partDebriefList["+i+"].partDisposition.value"; 
							input.value = statusValue;
							document.getElementById("partToolContent").appendChild(input);
		
							var input = document.createElement("input");
							input.id = "activity.debrief.partDebriefList["+i+"].partId"; 
							input.name = "activity.debrief.partDebriefList["+i+"].partId";
							input.value = document.getElementById("partId_"+ind).value;
							document.getElementById("partToolContent").appendChild(input);

							var input = document.createElement("input");
							input.id = "activity.debrief.partDebriefList["+i+"].partNumber"; 
							input.name = "activity.debrief.partDebriefList["+i+"].partNumber"; 
							input.value = partsAndToolsDebriefGrid.cellByIndex(i,2).cell.innerHTML;
							document.getElementById("partToolContent").appendChild(input);

							var input = document.createElement("input");
							input.id = "activity.debrief.partDebriefList["+i+"].partName"; 
							input.name = "activity.debrief.partDebriefList["+i+"].partName"; 
							input.value = partsAndToolsDebriefGrid.cellByIndex(i,3).cell.innerHTML;
							document.getElementById("partToolContent").appendChild(input);
						}
						if("${claimDebriefForm.activity.partnerAccount.orderPartsDebriefFlag}"=="true"){
							var additionalPartsRows = additionalPartsGrid.getRowsNum();
							for(var i = 0 ;i < additionalPartsRows ;i++){
								var listIndex = rows+i;
								var ind = additionalPartsGrid.getRowId(i);
								var qtyValue = 1;
								var reRequire = additionalPartsGrid.cellByIndex(i,7).cell.innerHTML;
								var statusValueKey = document.getElementById("partStatus_"+ind).value;
								var statusValue = partStatus[statusValueKey];
								if (typeof(statusValue) != "undefined"){
								if(statusValue.toLowerCase() == "used" && document.getElementById("errorCode1_"+ind).style.display!="none"){
									var errorCode1Key = document.getElementById("errorCode1_"+ind).value;
									var errorCode2 = document.getElementById("errorCode2_"+ind).value;
									var errorCode1 = errorCode1Value[errorCode1Key];
									
									var input = document.createElement("input");
									input.id = "activity.debrief.partDebriefList["+listIndex+"].errorCode1.value"; 
									input.name = "activity.debrief.partDebriefList["+listIndex+"].errorCode1.value"; 
									input.value = errorCode1;
									document.getElementById("partToolContent").appendChild(input);
								
									var input = document.createElement("input");
									input.id = "activity.debrief.partDebriefList["+listIndex+"].errorCode2.value"; 
									input.name = "activity.debrief.partDebriefList["+listIndex+"].errorCode2.value"; 
									input.value = errorCode2;
									document.getElementById("partToolContent").appendChild(input);
								}
								}
								else
								{
								 statusValue = '';
								}
								if('<spring:message code="claim.lebel.returnRequired.yes"/>'==reRequire){
									var reCarrierIndex = document.getElementById("reCarrier_"+ind).value;
									var reCarrier = carrierValue[reCarrierIndex];
									if (typeof(reCarrier) == "undefined"){
										reCarrier='';
									}
									var reTracingNum = document.getElementById("reTracingNum_"+ind).value;
									if (typeof(reCarrier) == "undefined"){
										reTracingNum='';
									}
									var input = document.createElement("input");
									input.id = "activity.debrief.partDebriefList["+listIndex+"].carrier.value"; 
									input.name = "activity.debrief.partDebriefList["+listIndex+"].carrier.value"; 
									input.value = reCarrier;
									document.getElementById("partToolContent").appendChild(input);
								
									var input = document.createElement("input");
									input.id = "activity.debrief.partDebriefList["+listIndex+"].trackingNumber"; 
									input.name = "activity.debrief.partDebriefList["+listIndex+"].trackingNumber"; 
									input.value = reTracingNum;
									document.getElementById("partToolContent").appendChild(input);
								}			
								
								var input = document.createElement("input");
								input.id = "activity.debrief.partDebriefList["+listIndex+"].quantity"; 
								input.name = "activity.debrief.partDebriefList["+listIndex+"].quantity"; 
								input.value = parseInt(qtyValue);
								document.getElementById("partToolContent").appendChild(input);
								
								var input = document.createElement("input");
								input.id = "activity.debrief.partDebriefList["+listIndex+"].partDisposition.value"; 
								input.name = "activity.debrief.partDebriefList["+listIndex+"].partDisposition.value"; 
								input.value = statusValue;
								document.getElementById("partToolContent").appendChild(input);

								var input = document.createElement("input");
								input.id = "activity.debrief.partDebriefList["+listIndex+"].partId"; 
								input.name = "activity.debrief.partDebriefList["+listIndex+"].partId"; 
								input.value = document.getElementById("partId_"+ind).value;
								document.getElementById("partToolContent").appendChild(input);

								var input = document.createElement("input");
								input.id = "activity.debrief.partDebriefList["+listIndex+"].partNumber"; 
								input.name = "activity.debrief.partDebriefList["+listIndex+"].partNumber"; 
								input.value = additionalPartsGrid.cellByIndex(i,2).cell.innerHTML;
								document.getElementById("partToolContent").appendChild(input);

								var input = document.createElement("input");
								input.id = "activity.debrief.partDebriefList["+listIndex+"].partName"; 
								input.name = "activity.debrief.partDebriefList["+listIndex+"].partName"; 
								input.value = additionalPartsGrid.cellByIndex(i,3).cell.innerHTML;
								document.getElementById("partToolContent").appendChild(input);
							}
						}
					}
					
					jQuery(document).ready(function() {
						
						<c:forEach items="${claimDebriefForm.activity.debrief.partDebriefList}" var="partDebrief" varStatus = "status" >
							var partDebrief = new Array();
							partDebrief[0] = "${partDebrief.partNumber}";
							partDebrief[1] = "${partDebrief.partName}";
							partDebrief[2] = "${partDebrief.returnRequiredFlag}";
							partDebrief[3] = "${partDebrief.replacementPartNumber}";
							partDebrief[4] ="<util:dateFormat value="${partDebrief.partOrderedDate}" timezoneOffset="${timezoneOffset}"/>";
							partDebrief[5] = "${partDebrief.quantity}";
							partDebrief[6] = "${partDebrief.partDisposition.value}";
							partDebrief[7] = "${partDebrief.errorCode1.value}";
							partDebrief[8] = "${partDebrief.errorCode2.value}";
							partDebrief[9] = "${partDebrief.carrier.value}";
							partDebrief[10] = "${partDebrief.trackingNumber}";
							partDebrief[11] = "${partDebrief.partLineItemId}";
							partDebrief[12] = "${partDebrief.partId}";
							partDebrief[13] = "${partDebrief.partLineItemUpdateFlag}";
							partDebrief[14] = "${partDebrief.expedite}"; //CI-6 Added
							loadPartDebrief(partDebrief);
						</c:forEach>
						
						if("${claimDebriefForm.showAdditionalPaymentRequestListFlag}" == "true"){
							additionalPaymentRequestsGrid.loadXMLString("${claimDebriefForm.additionalPaymentRequestListXML}");
							
						}
						

						// changes for db saving and retrieving	start						
						partsAndToolsDebriefGrid.attachEvent("onAfterCMove",function(a,b){																	
							partsAndToolsDebriefGrid.enableAutoSavingToDB("${saveCustomizedGridSettingURL}");										
						});	
						<c:forEach var="gridSettingVar" items="${gridSettingList}" varStatus="status">
						<c:if test="${gridSettingVar.gridId == 'gridCCVPartDebrief'}">					
						var colsOrder = "${gridSettingVar.colsOrder}";						
						partsAndToolsDebriefGrid.loadOrder(colsOrder);
						
						</c:if>
						</c:forEach>
						// changes for db saving and retrieving	end
						partsAndToolsDebriefGrid.loadXMLString("<?xml version=\"1.0\" ?><rows></rows>");
					});
					function loadPartDebrief(partDebrief){
						
						var id = partsAndToolsDebriefGrid.getUID(); // CI-6 Changes : partDebrief[14] added
							var str =[partDebrief[4],partDebrief[5],partDebrief[14],partDebrief[0],partDebrief[1]
							,'<table><tr><td><select onblur="setPartLineItemUpdateFlag(this &#44; \''+id+'\');" onfocus="setTempData(this);" id="partStatus_'+id+'" onchange=\"selectStatus(this &#44;\''+id+'\');\"><option value=""></option>'+generateOptionAndSetValue(partStatus,partStatusValue,partDebrief[6])+'</select></td></tr></table>'
							,'<table><tr><td><select onChange="retrieveErrorCode2(\''+id+'\');" onblur="setPartLineItemUpdateFlag(this &#44; \''+id+'\');" onfocus="setTempData(this);" style="width:100px;display:block;" id="errorCode1_'+id+'"><option value=""></option>'+generateOptionAndSetValue(errorCode1,errorCode1Value,partDebrief[7])+'</select></td></tr></table>'
							,'<table><tr><td><label id="errorCode2Loading_'+id+'" style="display: none;"><img  src = "<html:imagesPath/>loading-icon.gif"; />&nbsp;<spring:message code="label.loadingNotification" /></label><select onblur="setPartLineItemUpdateFlag(this &#44; \''+id+'\');" onfocus="setTempData(this);" style="width:130px;display:block;" id="errorCode2_'+id+'"></select></td></tr></table>'
							,(partDebrief[2]=="true"?'<spring:message code="claim.lebel.returnRequired.yes"/>':'<spring:message code="claim.lebel.returnRequired.no"/>')
							,((partDebrief[2]=="false")?'':'<table><tr><td><select style="width:120px;" onblur="setPartLineItemUpdateFlag(this &#44; \''+id+'\');" onfocus="setTempData(this);" id="reCarrier_'+id+'"><option value=""></option>'+generateOptionAndSetValue(carrier,carrierValue,partDebrief[9])+'</select></td></tr></table>')
							,((partDebrief[2]=="false")?'':'<table><tr><td><input onblur="validateLength(1 &#44; 30 &#44; this);setPartLineItemUpdateFlag(this &#44; \''+id+'\');" onfocus="setTempData(this);" id="reTracingNum_'+id+'" style="width:110px;" type="text" value="'+partDebrief[10]+'"/></td></tr></table>')
							,'<input id="partDebriefId_'+id+'" type="hidden" value="'+partDebrief[11]+'"/> <input id="partId_'+id+'" type="hidden" value="'+partDebrief[12]+'"/> <input id="partLineItemUpdateFlag_'+id+'" type="hidden" value="'+partDebrief[13]+'"/>'];
					
						partsAndToolsDebriefGrid.addRow(id,str,0);
						retrieveErrorCode2ForExsit(id,partDebrief[7],partDebrief[8]);
						selectStatus(document.getElementById("partStatus_"+id),id);						
					}
		
					function setPartLineItemUpdateFlag(ele, rowId){
						var currentData = ele.value;
						if(tempData != currentData){
							if(document.getElementById("partLineItemUpdateFlag_"+rowId)){
								document.getElementById("partLineItemUpdateFlag_"+rowId).value = "true";
							}
						}
					}
					var tempData;
					function setTempData(ele){
						tempData = ele.value;
					}
					
					
					</script>
			        <!-- Parts and Tools End -->  
			        
			        <c:if test="${claimDebriefForm.showAdditionalPaymentRequestListFlag}">
			        <!-- Additional Payment Requests Start -->
			        <div class="portlet-wrap width-100per" id="Additional_payment_requests">
						<div class="portlet-header">
							<h3><spring:message code="claim.label.additionalPaymentRequests"/></h3>
						</div><!-- portlet-header -->
						<div class="portlet-wrap-inner">
							<div class="columns">
								<div id="gridAdditionalPaymentRequests" class="width-100per"></div>
								<div id="pagingAdditionalPayment"></div>
							</div>
							<div class="columns">
								<dl>
									<dd>
										<a href="javascript:addNewPaymentRequest();" class="button"><spring:message code="claim.button.addNew"/></a>
									</dd>
								</dl>
							</div>
						</div><!-- portlet-wrap-inner -->
						<div style="display:none" id="paymentContent"></div>
						<div class="portlet-footer">
							<div class="portlet-footer-inner"></div><!-- portlet-footer-inner -->
						</div><!-- portlet-footer -->
					</div>
					<script type="text/javascript">
					var additionalPaymentRequestsGrid = new dhtmlXGridObject('gridAdditionalPaymentRequests');
					additionalPaymentRequestsGrid.setImagePath("<html:imagesPath/>gridImgs/");
					additionalPaymentRequestsGrid.setHeader(autoAppendPlaceHolder('<spring:message code="claim.headerList.additionalPaymentRequests"/>,',7));
					additionalPaymentRequestsGrid.setColAlign("left,left,left,left,left,left,left");
					additionalPaymentRequestsGrid.setInitWidths("*,100,*,100,*,*,100");
					additionalPaymentRequestsGrid.setColTypes("ro,ro,ro,ro,ro,ro,ro");
					additionalPaymentRequestsGrid.setColSorting("str,na,na,int,na,str,na");
					additionalPaymentRequestsGrid.enableAutoWidth(true);
					additionalPaymentRequestsGrid.enableAutoHeight(true);
					additionalPaymentRequestsGrid.enableMultiline(true);
					additionalPaymentRequestsGrid.enablePaging(true, 5, 6, "pagingAdditionalPayment", true);
					additionalPaymentRequestsGrid.init(); 
					additionalPaymentRequestsGrid.setSkin("light");	
					additionalPaymentRequestsGrid.setPagingSkin("bricks");
					
					
					function addNewPaymentRequest(){
						callOmnitureAction('Claim Close Out View', 'Add New Payment Request');
						var id = additionalPaymentRequestsGrid.getUID();
						var defaultCurrency = "${claimDebriefForm.activity.partnerAccount.defaultCurrency}";
						var str = '<table><tr><td><select id="'+id+'_paymentType_grid" >'+ getPaymentTypeOptions() +'</select></td></tr></table>,' +
								  '<table><tr><td><input id="'+id+'_qty_grid" size="2" type="text" maxlength = "5" onblur="calculateAmount(this &#44;' + id + ')"  onafterpaste="clearNoNumAndPoint(this)" onkeyup="clearNoNumAndPoint(this)"  value=""/></td></tr></table>,' +
								  '<table><tr><td><input id="'+id+'_unitPrice_grid" size="10" maxlength = "10" type="text" onblur="calculateAmount(this &#44;' + id + ')" onafterpaste="clearNoNum(this)" onkeyup="clearNoNum(this)"   value=""/></td></tr></table>,' +
								  ''+ ',' +
								  defaultCurrency + ',' +
								  '<table><tr><td><input onblur="validateLength(1 &#44; 250 &#44; this);" id="'+id+'_description_grid" size="50" type="text" /></td></tr></table>,' +
								  '<a href="javascript:deleteRowInPaymentGrid(\''+id+'\');"><img class="ui-icon trash-icon" src="<html:imagesPath/>transparent.png"></a><input id="'+id+'_paymentRequestUpdateFlag" type="hidden" value="false" /><input id="'+id+'_isNew" type="hidden" value="false" />';
						additionalPaymentRequestsGrid.addRow(id,str,0);
						additionalPaymentRequestsGrid.changePage(1);				
					}
		
					function getPaymentTypeOptions(){
						var options = '<option value=""></option><c:forEach items="${claimDebriefForm.paymentTypes}" var="type" varStatus="loop"><option value="${type.key}">${type.value}</option></c:forEach>';
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
						callOmnitureAction('Claim Close Out View', 'Delete Row In Payment Request');
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
						var offset = 0;
						var currentPage = additionalPaymentRequestsGrid.currentPage;
						for(var i = 0 ;i < rows ;i++){
							if(i == 0){
								additionalPaymentRequestsGrid.changePage(1);
							}else if(i%global_paging_size ==0){
								additionalPaymentRequestsGrid.changePageRelative(1);
							}
							var rowId = additionalPaymentRequestsGrid.getRowId(i);
							var isNewPayment = document.getElementById(rowId + "_isNew").value;
							if(isNewPayment == "false"){
								//payment type
								var inputType = document.createElement("input");
								inputType.id = "newAdditionalPaymentRequestList["+offset+"].paymentType.value"; 
								inputType.name = "newAdditionalPaymentRequestList["+offset+"].paymentType.value";
								inputType.value = document.getElementById(rowId+"_paymentType_grid").value;
								document.getElementById("paymentContent").appendChild(inputType);
								//qty
								var inputQty = document.createElement("input");
								inputQty.id = "newAdditionalPaymentRequestList["+offset+"].quantity"; 
								inputQty.name = "newAdditionalPaymentRequestList["+offset+"].quantity";
								inputQty.value = document.getElementById(rowId+"_qty_grid").value;
								document.getElementById("paymentContent").appendChild(inputQty);
								//unit price
								var inputUnitPrice = document.createElement("input");
								inputUnitPrice.id = "newAdditionalPaymentRequestList["+offset+"].unitPrice"; 
								inputUnitPrice.name = "newAdditionalPaymentRequestList["+offset+"].unitPrice";
								inputUnitPrice.value = document.getElementById(rowId+"_unitPrice_grid").value;
								document.getElementById("paymentContent").appendChild(inputUnitPrice);
								//total price
								var inputTotal = document.createElement("input");
								inputTotal.id = "newAdditionalPaymentRequestList["+offset+"].totalAmount"; 
								inputTotal.name = "newAdditionalPaymentRequestList["+offset+"].totalAmount";
								inputTotal.value = additionalPaymentRequestsGrid.cellByIndex(i,3).cell.innerHTML;
								document.getElementById("paymentContent").appendChild(inputTotal);
								//currency
								var inputCurrency = document.createElement("input");
								inputCurrency.id = "newAdditionalPaymentRequestList["+offset+"].paymentCurrency"; 
								inputCurrency.name = "newAdditionalPaymentRequestList["+offset+"].paymentCurrency";
								inputCurrency.value = additionalPaymentRequestsGrid.cellByIndex(i,4).cell.innerHTML;
								document.getElementById("paymentContent").appendChild(inputCurrency);
								//description
								var inputDescription = document.createElement("input");
								inputDescription.id = "newAdditionalPaymentRequestList["+offset+"].description"; 
								inputDescription.name = "newAdditionalPaymentRequestList["+offset+"].description";
								inputDescription.value = document.getElementById(rowId+"_description_grid").value;
								document.getElementById("paymentContent").appendChild(inputDescription);
								//paymentRequestUpdateFlag
								var inputPaymentRequestUpdateFlag = document.createElement("input");
								inputPaymentRequestUpdateFlag.id = "newAdditionalPaymentRequestList["+offset+"].paymentRequestUpdateFlag"; 
								inputPaymentRequestUpdateFlag.name = "newAdditionalPaymentRequestList["+offset+"].paymentRequestUpdateFlag";
								inputPaymentRequestUpdateFlag.value = "false";
								document.getElementById("paymentContent").appendChild(inputPaymentRequestUpdateFlag);		
								offset++;
							}	
						}
						additionalPaymentRequestsGrid.changePage(currentPage);				
					}
					function validatePaymentRequests(){
						var rows = additionalPaymentRequestsGrid.getRowsNum();
						var result = true;
						var currentPage = additionalPaymentRequestsGrid.currentPage;
						for(var i = 0 ;i < rows ;i++){
							if(i == 0){
								additionalPaymentRequestsGrid.changePage(1);
							}else if(i%global_paging_size ==0){
								additionalPaymentRequestsGrid.changePageRelative(1);
							}
							var rowId = additionalPaymentRequestsGrid.getRowId(i);
							var isNewPayment = document.getElementById(rowId + "_isNew").value;
							if(isNewPayment == "false"){
								var errMsg = '<spring:message code="claim.errorMsg.additionalPaymentLine"/> ' + (i+1) + ': ';
								var currentResult = true;
								var inputType = document.getElementById(rowId+"_paymentType_grid").value;
								var inputQty = document.getElementById(rowId+"_qty_grid").value;
								var inputUnitPrice = document.getElementById(rowId+"_unitPrice_grid").value;
								var inputDescription = document.getElementById(rowId+"_description_grid").value;
								
								var id_inputType = rowId+"_paymentType_grid";
								var id_inputQty = rowId+"_qty_grid";
								var id_inputUnitPrice = rowId+"_unitPrice_grid";
								var id_inputDescription = rowId+"_description_grid";
								
								jQuery(id_inputType).removeClass('errorColor');
								jQuery(id_inputQty).removeClass('errorColor');
								jQuery(id_inputUnitPrice).removeClass('errorColor');
								jQuery(id_inputDescription).removeClass('errorColor');
								
								if(inputType.trim() == '' || inputType.trim() == '&nbsp;'){
									currentResult = false;
									errMsg += '<spring:message code="claim.errorMsg.additionalPayment.paymentTypeNotNull"/>' + '; ';
									jQuery(id_inputType).addClass('errorColor');
								}
								if(inputQty.trim() == '' || inputQty.trim() == '&nbsp;'){
									currentResult = false;
									errMsg += '<spring:message code="claim.errorMsg.additionalPayment.qtyNotNull"/>' + '; ';
									jQuery(id_inputQty).addClass('errorColor');
								}
								if(inputUnitPrice.trim() == '' || inputUnitPrice.trim() == '&nbsp;'){
									currentResult = false;
									errMsg += '<spring:message code="claim.errorMsg.additionalPayment.unitPrice"/>' + '; ';
									jQuery(id_inputUnitPrice).addClass('errorColor');
								}

								if(inputDescription.trim() == '' || inputDescription.trim() == '&nbsp;'){
									currentResult = false;
									errMsg += '<spring:message code="claim.errorMsg.additionalPayment.description"/>' + '; ';
									jQuery(id_inputDescription).addClass('errorColor');
								}
								if(!currentResult){
									showError(errMsg, null, true);
								}
								errMsg = '';
								result &= currentResult;																
							}
						}
						additionalPaymentRequestsGrid.changePage(currentPage);
						return result;
					}
		
						function clearNoNum(ele){
							//remove all non-digit characters, except "."
							ele.value = ele.value.replace(/[^\d.]/g,"");
							//make sure "." is not the first character
							ele.value = ele.value.replace(/^\./g,"0.");
							ele.value = ele.value.replace(/\.{2,}/g,".");
							ele.value = ele.value.replace(".","$#$").replace(/\./g,"").replace("$#$",".");
						}
						//added by ragesree
						//function clearNoNumAndPoint(ele){
							//alert("debrief"+ele);
							//remove all non-digit characters, include "."
							//ele.value = ele.value.replace(/[^\d]/g,"");				
						//}
						
					</script>
			        <!-- Additional Payment Requests End -->  	
			        </c:if>
			        
			        
			        
			        <!-- Notes Start -->   
					<div class="portlet-wrap width-100per" id="notes" >
					<div class="portlet-header">
						<h3><spring:message code="claim.label.notes"/></h3>
					</div><!-- portlet-header -->
					<div class="div-style6" ><div class="div-style7" ><b><spring:message code="claim.notes.helpText2"/></b></div><div class="div-style8"><b><spring:message code="claim.notes.helpText"/></b></div></div>
					<div class="portlet-wrap-inner div-style9" >
						<div class="columns">
							<div id="gridCDBrfVNRequest" class="width-100per"></div>
							<div id="loadingNotificationNote" class="gridLoading" style=" display:none;"><!--spring:message code="label.loadingNotification"/-->&nbsp;
								<img src="<html:imagesPath/>gridloading.gif" /><br>
							</div>
							<div id="pagingNote"></div>
						</div>
						<div class="columns">
							<dl>
								<dd>
									<a href="javascript:addNotePopUp();" class="button"><spring:message code="claim.button.addNotes"/></a>
								<div class="overlayAddnotePopup" ></div>
								</dd>
							</dl>
						</div>				
					</div><!-- portlet-wrap-inner -->
					<div id="noteContent" style="display:none;"></div>
					<div class="portlet-footer">
						<div class="portlet-footer-inner"></div><!-- portlet-footer-inner -->
					</div><!-- portlet-footer -->
					</div>
					
			       	 
					<script type="text/javascript">
					showNotesFunction();
					var showNotes;
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
						width: 650,
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
					gridCDBrfVNRequestJs = new dhtmlXGridObject('gridCDBrfVNRequest');
					gridCDBrfVNRequestJs.setImagePath("<html:imagesPath/>gridImgs/");
					gridCDBrfVNRequestJs.setHeader(autoAppendPlaceHolder('<spring:message code="claim.headerList.notes.details"/>',5));
					gridCDBrfVNRequestJs.setColAlign("left,left,left,left,left");
					gridCDBrfVNRequestJs.setColTypes("sub_row,ro,ro,ro,ro");
					gridCDBrfVNRequestJs.setColSorting("na,str,str,str,str");
					gridCDBrfVNRequestJs.setInitWidths("20,100,150,400,100");
					gridCDBrfVNRequestJs.enableAutoWidth(true);
					gridCDBrfVNRequestJs.enableAutoHeight(true);
					gridCDBrfVNRequestJs.enableMultiline(true);
					gridCDBrfVNRequestJs.enablePaging(true, 5, 6, "pagingNote", true);	
					gridCDBrfVNRequestJs.init();
					gridCDBrfVNRequestJs.setSkin("light");
					gridCDBrfVNRequestJs.setPagingSkin("bricks");
					gridCDBrfVNRequestJs.attachEvent("onXLS", function() {
						document.getElementById('loadingNotificationNote').style.display = 'block';
					});
					gridCDBrfVNRequestJs.attachEvent("onXLE", function() {
						setTimeout(function(){
				    		rebrandPagination();
				    	
				    	},100);
						document.getElementById('loadingNotificationNote').style.display = 'none';
					});
					
					jQuery(document).ready(function() {
					gridCDBrfVNRequestJs.loadXMLString("${claimDebriefForm.activityNoteListXML}");
					});
					
					function showNotesPopup(){
						jQuery(window).scrollTop(0);
						showNotes.show();
						jQuery(".aui button.close, .aui button.btn.close").hide();
						showNotes.titleNode.html("<spring:message code='claim.label.updateNote'/>");
						showNotes.io.set('uri',"${updateNoteUrl}&handleGridFlag=add");
						showNotes.io.start();
						
						}
					function addNotePopUp(){
						callOmnitureAction('Claim Close Out View', 'Add Note PopUp');
						showOverlayIE6();
						showNotesPopup();
						bindingClosePopupIE6();
					}
					function addRowInNotesGrid(note){
						var subDetail = escapeStringForNoteSubRow(note[0]);
						var id = gridCDBrfVNRequestJs.getUID();
						var str =['<strong><spring:message code="claim.label.note"/></strong><br><div style="word-wrap: break-word;width:80%">'+subDetail+'</div>',note[1],note[2],note[3],'<button class="button" type="button" onclick="editRow('+id+')"><spring:message code="button.edit"/></button> <input id="'+id+'_noteDetail" type="hidden" value="'+note[0]+'"><input id="'+id+'_contactId" type="hidden" value="${claimDebriefForm.contactId}">'];
						gridCDBrfVNRequestJs.addRow(id,str,0);
						gridCDBrfVNRequestJs.changePage(1);
					}
					function updateRowInNotesGrid(note,rowId){
						var subDetail = escapeStringForNoteSubRow(note[0]);
						var newRowId = gridCDBrfVNRequestJs.getUID();
						var str =['<strong><spring:message code="claim.label.note"/></strong><br><div style="word-wrap: break-word;width:80%">'+subDetail+'</div>',note[1],note[2],note[3],'<button class="button" type="button" onclick="editRow('+rowId+')"><spring:message code="button.edit"/></button> <input id="'+rowId+'_noteDetail" type="hidden" value="'+note[0]+'"><input id="'+rowId+'_contactId" type="hidden" value="'+document.getElementById(rowId+"_contactId").value+'">'];
						if(document.getElementById(rowId+"_noteId")){
							str = ['<strong><spring:message code="claim.label.note"/></strong><br><div style="word-wrap: break-word;width:80%">'+subDetail+'</div>',note[1],note[2],note[3],
									'<button class="button" type="button" onclick="editRow('+rowId+')"><spring:message code="button.edit"/></button> <input id="'+rowId+'_noteDetail" type="hidden" value="'+note[0]+'"><input id="'+rowId+'_contactId" type="hidden" value="'+document.getElementById(rowId+"_contactId").value+'"><input id="'+rowId+'_noteId" type="hidden" value="'+document.getElementById(rowId+"_noteId").value+'"><input id="'+rowId+'_activityUpdateFlag" type="hidden" value="true">'];
						}
						gridCDBrfVNRequestJs.addRow(newRowId,str,0);
						gridCDBrfVNRequestJs.copyRowContent(newRowId,rowId);
						gridCDBrfVNRequestJs.deleteRow(newRowId);
						gridCDBrfVNRequestJs.cellById(rowId,0).setContent('<strong><spring:message code="claim.label.note"/></strong><br><div style="word-wrap: break-word;width:80%">'+subDetail+'</div>');
						gridCDBrfVNRequestJs.cellById(rowId,0).close();
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
						callOmnitureAction('Claim Close Out View', 'Edit Note');
						var noteDate = gridCDBrfVNRequestJs.cellById(id,1).cell.innerHTML;
						var noteAuthor = gridCDBrfVNRequestJs.cellById(id,2).cell.innerHTML;
						var noteDetailId = id+"_noteDetail";
						showOverlayIE6();
						updateNotesPopup(noteDate,noteAuthor,noteDetailId,id);
						bindingClosePopupIE6();
					}
					
					function generateNotesFormData(){
						document.getElementById("noteContent").innerHTML = "";
						var rows = gridCDBrfVNRequestJs.getRowsNum();
						var noteCurrentPage = gridCDBrfVNRequestJs.currentPage;
						for(var i = 0 ;i < rows ;i++){
							if(i == 0){
								gridCDBrfVNRequestJs.changePage(1);
							}else if(i%global_paging_size ==0){
								gridCDBrfVNRequestJs.changePageRelative(1);
							}
							var rowId = gridCDBrfVNRequestJs.getRowId(i);
							//noteDate
							var inputDate = document.createElement("input");
							inputDate.id = "activity.activityNoteList["+i+"].noteDate"; 
							inputDate.name = "activity.activityNoteList["+i+"].noteDate";
							inputDate.value = formatDateToDefault(gridCDBrfVNRequestJs.cellByIndex(i,1).cell.innerHTML);
							document.getElementById("noteContent").appendChild(inputDate);
							//noteAuthor
							var inputContactId = document.createElement("input");
							inputContactId.id = "activity.activityNoteList["+i+"].noteAuthor.contactId"; 
							inputContactId.name = "activity.activityNoteList["+i+"].noteAuthor.contactId";
							inputContactId.value = document.getElementById(rowId+"_contactId").value;
							document.getElementById("noteContent").appendChild(inputContactId);
							//noteDetail
							var inputDetail = document.createElement("input");
							inputDetail.id = "activity.activityNoteList["+i+"].noteDetails"; 
							inputDetail.name = "activity.activityNoteList["+i+"].noteDetails";
							//inputDetail.value = escapeStringForNote(document.getElementById(rowId+"_noteDetail").value);
							inputDetail.value = document.getElementById(rowId+"_noteDetail").value;
							document.getElementById("noteContent").appendChild(inputDetail);
							//noteId
							var inputNoteId = document.createElement("input");
							inputNoteId.id = "activity.activityNoteList["+i+"].noteId"; 
							inputNoteId.name = "activity.activityNoteList["+i+"].noteId";
							if(document.getElementById(rowId+"_noteId")){
								inputNoteId.value = document.getElementById(rowId+"_noteId").value;
							}
							document.getElementById("noteContent").appendChild(inputNoteId);
							
							if(document.getElementById(rowId+"_noteId")){
								//activityUpdateFlag
								var inputActivityUpdateFlag = document.createElement("input");
								inputActivityUpdateFlag.id = "activity.activityNoteList["+i+"].activityUpdateFlag"; 
								inputActivityUpdateFlag.name = "activity.activityNoteList["+i+"].activityUpdateFlag";
								inputActivityUpdateFlag.value = document.getElementById(rowId+"_activityUpdateFlag").value;
								document.getElementById("noteContent").appendChild(inputActivityUpdateFlag);
							}
						}
						gridCDBrfVNRequestJs.changePage(noteCurrentPage);
					} 
					
					</script>
			         <!-- Notes End -->    	
			         	 
						
				</div><!-- content -->
			</div>
		</form:form>
		</td>
	</tr>
	<!-- File Attachment start -->
				     <style>
				     #browseAttachmentFileId{padding-top:10px}
				     </style> 
				  <table width="98%" class="table-style3" >
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
										<td colspan="2">
											<nobr>
											<b><LABEL class="labelBold" for="fileInput"><spring:message code="fileAttachment.label.fileToUpload" /></LABEL>:&nbsp;&nbsp;</b>
										<!-- </td>
										<td> -->
												<input type="text" id="txtFilePath" size="50" readonly="true" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												 <a href="#" class="button"><spring:message code="button.browse"/></a>&nbsp;
												<form:input type="file" size="1" id="fileInput" class="requestsUploader" path="fileData" onchange="document.getElementById('txtFilePath').value = getFileName();" style="z-index: 2;  filter: alpha(opacity = 0); opacity: 0; width: 80px; left: 0px !important; top: 0px !important; margin-left: -100px !important;position:relative;" /> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											    <a href="javascript:uploadFile();" class="button"><spring:message code="button.uploadFile"/></a>
											</nobr>		   
										</td>
									</tr>
									<tr class="import-table-tr1"><td></td><td><nobr><b><spring:message code="attachment.messgae.FileRestriction"/></b></nobr></td></tr>
									<!-- File Validation -->
									<tr class="import-table-tr1"><td></td><td><nobr><b><table id="errorMessageTable"></table></b></nobr></td></tr>
									<script type="text/javascript">
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
				                    	errorMessageText=errorMessageText+' <spring:message code="attachment.maxNumberOfFiles.message"/>';
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
							</div>
							<div class="portlet-footer">
							
								<div class="portlet-footer-inner"></div>
							</div>
				        </div>
				     	<iframe id="upload_target" name="upload_target" class="iframe-upload-target"></iframe>
				        
				        <script type="text/javascript">
				        var responseText;
				        jQuery(document).ready(function() {
				        	jQuery("#fileInput").css({"cursor":"pointer"});
							jQuery("#processingHint",window.parent.document).css({
							               display:"none"
							       });
							       jQuery("#overlay",window.parent.document).css({
							               display:"none"
							       });
							   var wrapperWidth=window.innerWidth-144;
							   $(".columns").css({"max-width":wrapperWidth+"px"});
							});

				     <c:if test="${attachmentFormDisplay.attachmentList != null}">
						jQuery('#fileListTable',window.parent.document).empty();
						var responseText = '<thead><tr class="tableHeader"><b><th class="width60"><spring:message code="attachment.message.FileName"/></th><th class="width35"><spring:message code="attachment.message.FileSize"/></th></b></tr></thead>';
						   responseText = responseText + '<tbody>';
							<c:forEach items="${attachmentFormDisplay.attachmentList}" var="entry">
					 			responseText = responseText + '<tr class="tableContentColor">';
					 			responseText = responseText + '<td class="width60">'+ '<a href="#" onClick=\'openFile("${entry.attachmentName}","${entry.activityId}","${entry.actualFileName}","${entry.identifier}","${entry.type}") ;\'><c:out value="${entry.attachmentName}" /></a>';
					 			responseText = responseText + '<img id="img_help1" class="ui-icon trash-icon"  src="<html:imagesPath/>transparent.png" style="cursor:pointer;" title="Delete" alt="Remove" onclick=\'deleteFile("${entry.attachmentName}");\' /></td>';
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

						function uploadFile() {
							var filePath = document.getElementById('txtFilePath').value;
							
							if(filePath != '') {
							
								document.getElementById('attachmentForm').target = 'upload_target';
							
								var link = "${addAttachmentsCreate}";
							
								document.getElementById('attachmentForm').action = link;
							
								// jQuery('#attachmentForm').submit();
								eval(document.attachmentForm).submit();
								
							}
						};

						function deleteFile(fileName) {
							// call action in controller to delete file
							var link = '${removeAttachmentURL}';
							document.getElementById('attachmentForm').target = 'upload_target';
							document.getElementById('attachmentForm').action = link + "&fileName=" + fileName;
							eval(document.attachmentForm).submit();
							// jQuery('#attachmentForm').submit();
							};

						function openFile(userFileName,activityId,actualFileName,identifier,type){
					        window.open("${outPutFileURL}&userFileName=" + userFileName + "&activityId=" + activityId+ "&actualFileName=" + actualFileName+ "&identifier=" + identifier+ "&type=" + type, "");
						};

						function getFileName() {
							var path = document.getElementById('fileInput').value;
								if(path.match(/fakepath/)) {
									path = path.replace(/C:\\fakepath\\/i, '');
							    }
							return path;
						};
						</script>
						</form:form>
					  </td>
				  </tr>
			  </table>
			  <table width="90%">
			  	<tr>
			  		<td>
			  			<form:form id="dummyForm" name="dummyForm" method="post">
			  				<table width="98%" class="table-style3">
								<tr>
									<td>
					<div align="left">
						<a href="javascript:submitFormClaimCloseOutDraft();" class="button"><spring:message code="button.save_as_draft"/></a>&nbsp;&nbsp;<a href="javascript:submitFormClaimCloseOut();" class="button"><spring:message code="button.submit"/></a>&nbsp;&nbsp;<a href="javascript:cancelConfirm();" class="button_cancel"><spring:message code="button.cancel"/></a>
					</div>	
									</td>
								</tr>
							</table>
		</form:form>
		</td>
	</tr>
</table>
				        <!-- File Attachment end -->
	
</table>
<script type="text/javascript">
	document.getElementById('timezoneOffset').value = timezoneOffset;
	function submitFormClaimCloseOut(){		
		
		var selectProbCode = "";		
		
		var selectprobcode1 = document.getElementById("problemCodeSelect").value;
		selectProbCode = selectprobcode1;
		if(selectprobcode1 == 'Error Code Displayed'){
			var numericCode2 = document.getElementById("numericCode2").value;
			selectProbCode = selectprobcode1+";"+numericCode2;
		}
		else{
			if(null != document.getElementById("probCode2") && document.getElementById("probCode2").value != ""){
				var selectprobcode2 = document.getElementById("probCode2").value;
				//selectProbCode = selectprobcode1+";"+selectprobcode2;
				selectProbCode = selectprobcode2;
			}
			if(null != document.getElementById("probCode3") && document.getElementById("probCode3").value != ""){
				var selectprobcode3 = document.getElementById("probCode3").value;
				selectProbCode = selectprobcode3;
			}
		}
		
		jQuery('#problemCodes').val(selectProbCode);
		
		removeErrorClass();
		clearMessage();
		if(validateBeforeSubmit()){
			callOmnitureAction('Debrief Claim', 'Submit');
			if("${claimDebriefForm.showAdditionalPaymentRequestListFlag}" == "true"){
				generateAdditionalPaymentFormData();
			}
			generateTechnicianinformation();
			generateNotesFormData();
			if((exchangeflag=='false') || (exchangeflag==false)){
				var serviceType = "${claimDebriefForm.activity.serviceType}";
				if(serviceType != 'Maintenance Kit Install'){
					generatePartAndTool();
				}			
			}
			confirmSubmitDebriefClaim();
		}
		
	}
	
	//added BRD #13-10-01
	
	function submitFormClaimCloseOutDraft(){
		
		
		var selectProbCode = "";		
		
		var selectprobcode1 = document.getElementById("problemCodeSelect").value;
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
		document.getElementById("debriefStatus").value="Draft";	
		jConfirm("<spring:message code='claim.message.confirmSubmitDraftClaim'/>", "", function(confirmResult) {
			if(confirmResult){
				var serviceType = "${claimDebriefForm.activity.serviceType}";
				if(serviceType != 'Maintenance Kit Install'){
			    generatePartAndTool();
				}
				generateNotesFormData();
				jQuery("#claimDebriefForm").submit();
				setAllToReadOnly();
			}else{
				return;
			}
		});
		//document.getElementById("debriefStatus").value="Draft";		
		//jQuery("#claimDebriefForm").submit();		
	}
	 
	 //ends here

	function validateBeforeSubmit(){
		var result = validateProbleminformation();
		if((exchangeflag=='true') || (exchangeflag==true)){
			result &=validateCloseOutActivityInfo();
		}else{
		result &= validateDebriefinformation();
		}
		result &= validateTechnicianinformation();
		if("${claimDebriefForm.showAdditionalPaymentRequestListFlag}" == "true"){
			result &= validatePaymentRequests();
		}
		if((exchangeflag=='false') || (exchangeflag==false)){
				var serviceType = "${claimDebriefForm.activity.serviceType}";
				if(serviceType != 'Maintenance Kit Install'){
					result &= validatePartAndTool();
				}				
		}
		
		showProbValidateMsg = false;
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
		
		if(!result)
			if(showProbValidateMsg){
				showError("<spring:message code='claim.errorMsg.problemCodeNotNull'/>", null, true);
			}
			scrollTo(0,0);
		return result;
	}
	function setAllToReadOnly(){
		var input = document.getElementsByTagName("input");
		var select = document.getElementsByTagName("select");
		var textarea = document.getElementsByTagName("textarea");
		for(var i=0;i<input.length;i++){
			input[i].readOnly = true;
			}
		for(var i=0;i<select.length;i++){
			select[i].disabled = "disabled";
			}
		for(var i=0;i<textarea.length;i++){
			textarea[i].readOnly = true;
			}
	}

	function cancelConfirm(){
		callOmnitureAction('Claim Close Out View', 'Cancel');
		jiConfirm("<br><spring:message code='claim.message.cancelConfirm'/>", "","<spring:message code='button.ok'/>","<spring:message code='button.goBack'/>","center", function(confirmResult) {
			if(!confirmResult){
				return;
			}else{
				window.location.href = "${cancelDebrief}&activityId=${claimDebriefForm.activity.activityId}&serviceRequestId=${claimDebriefForm.activity.serviceRequest.id}&fromPage=${claimDebriefForm.fromPage}&timezoneOffset="+timezoneOffset;
			}
		});
	}

	function confirmSubmitDebriefClaim(){
		callOmnitureAction('Claim Close Out View', 'Submit');
		jConfirm("<spring:message code='claim.message.confirmSubmitCreateClaim'/>", "", function(confirmResult) {
			if(confirmResult){
				jQuery("#claimDebriefForm").submit();
				setAllToReadOnly();
			}else{
				return;
			}
		});
	}
	
	function removeErrorClass(){
		jQuery('#problemCodeSelect').removeClass('errorColor');
		jQuery('#problemDescriptionInput').removeClass('errorColor');
		jQuery('#reviewCommentsInput').removeClass('errorColor');
		jQuery('#repairDescriptionInput').removeClass('errorColor');
		jQuery('#dateServicedRequested').removeClass('errorColor');
		jQuery('#serviceStartDate').removeClass('errorColor');
		jQuery('#serviceEndDate').removeClass('errorColor');
		jQuery('#resolutionCodeSelect').removeClass('errorColor');
		jQuery('#deviceConditionSelect').removeClass('errorColor');
		jQuery('#technicianChoose').removeClass('errorColor');
		jQuery('#technicianFirstNameInput').removeClass('errorColor');
		jQuery('#technicianLastNameInput').removeClass('errorColor');
	}
</script>

<script type="text/javascript">
     portletName = "Claim Debrief View";
     addPortlet(portletName);
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
					jQuery('#problemCode2Div').show();
					jQuery('#showProbErrorCode2').show();
					jQuery('#showProbErrorCode2').html("");
					jQuery('#showProbErrorCode2').html("<input type=\"textarea\" id=\"numericCode2\" onkeyup=\"validateNumericCode();\" \><span id=\"errorMsgNumCode\" class=\"errorColor\" style=\"display: none; color: red\"><B>Problem Code Not Found</B></span");
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
			url:"<portlet:resourceURL id='retriveProblemCodeClaimCloseOut'></portlet:resourceURL>",
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
								if(array[1]!=null){
									jQuery('#probCode2').val(array[0]+';'+array[1]);
								}
								if(array[2]!=null){
									getprobCode(array[1],'2');
								}
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
								if(array[2]!=null){
									jQuery('#probCode3').val(array[0]+';'+array[1]+';'+array[2]);
									//jQuery('#probCode3').val(array[2]);
								}
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
				url:"<portlet:resourceURL id='retriveNumericCode2ClaimCloseOut'></portlet:resourceURL>",
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
							jQuery('#errorMsgNumCode').html("");
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
<script>
var actualFailureCode="${claimDebriefForm.activity.actualFailureCode.value}";

if(actualFailureCode.indexOf(";") > -1){
	var array=actualFailureCode.split(";");
	$('#problemCodeSelect').val(array[0]);
}
else{
	$('#problemCodeSelect').val(actualFailureCode);
}




</script>
