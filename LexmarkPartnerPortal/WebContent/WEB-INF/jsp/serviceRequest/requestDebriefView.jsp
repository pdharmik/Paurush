<%@ include file="/WEB-INF/jsp/include.jsp"%>
<%@ include file="/WEB-INF/jsp/includeGrid.jsp"%>
<%@ include file="/WEB-INF/jsp/dateUtil.jsp"%>
<%@ include file="/WEB-INF/jsp/includeDatePicker.jsp"%>
<%@ include file="/WEB-INF/jsp/noBackToThisPage.jsp" %>
<style type="text/css"><%@ include file="/WEB-INF/css/ci6.css" %></style>
<!--[if IE 7]>
	<style type="text/css"><%@ include file="/WEB-INF/css/mps_ie7.css" %></style>
<![endif]-->
<!-- Added for CI Defect # 11682 -->
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/mps.css?version=<html:fileCacheVersion/>" />

<style type="text/css">
.test_cal_icon select {
    width: 212px !important;
}
.columns{
	overflow:auto !important;
}
.displayGrid{line-height:20px;}
.test_cal_icon {width:100%}
.ie7 .test_cal_icon, .ie8 .test_cal_icon{width:85% !important}
.ie9 .test_cal_icon{width:76% !important}
.test_cal_icon input{float:left}
.test_cal_icon img{float:left !important}
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
<script language="JavaScript" type="text/javascript" src="<html:rootPath/>js/jquery.validate.js"></script>
<portlet:renderURL  var="updateNoteUrl" windowState='<%= LiferayWindowState.POP_UP.toString() %>'>
	<portlet:param name="action" value="showUpdateNotePopup" />
</portlet:renderURL>

<portlet:actionURL var="submitRequestsDebrief">
	<portlet:param name="action" value="submitRequestsDebrief"/>
</portlet:actionURL>

<portlet:actionURL  var="partnerAddressListURL">
	<portlet:param name="action" value="retrievePartnerAddressListURL" />
</portlet:actionURL>

<portlet:renderURL  var="requestsListViewUrl">
</portlet:renderURL>
<portlet:actionURL var="addAttachmentsDebriefRequest">
	<portlet:param name="action" value="addAttachmentsDebriefRequest"/>
</portlet:actionURL>

<portlet:actionURL var="removeAttachmentURL">
	<portlet:param name="action" value="removeAttachmentActionRequestDebrief"/>
</portlet:actionURL>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/in-styles.css?version=<html:fileCacheVersion/>" />
<table width="100%">
	<tr>
		<td>
<form:form id="requestsDebriefForm" commandName="requestsDebriefForm" name="requestsDebriefForm" method="post" action="${submitRequestsDebrief}">
	<input type="hidden" name="activity.activityId" value="${requestsDebriefForm.activity.activityId}"/>
	<input type="hidden" name="activity.serviceRequest.asset.assetId" value="${requestsDebriefForm.activity.serviceRequest.asset.assetId}"/>
	<input type="hidden" name="activity.serviceRequest.id" value="${requestsDebriefForm.activity.serviceRequest.id}"/>
	<input type="hidden" name="activity.partnerAccount.accountId" value="${requestsDebriefForm.activity.partnerAccount.accountId}"/>
	<input type="hidden" name="activity.partnerAccount.accountName" value="${requestsDebriefForm.activity.partnerAccount.accountName}"/>
	<input type="hidden" name="activity.partnerAccount.organizationID" value="${requestsDebriefForm.activity.partnerAccount.organizationID}"/>
	<input type="hidden" name="activity.partnerAccount.address.addressId" value="${requestsDebriefForm.activity.partnerAccount.address.addressId}"/>
	<input type="hidden" name="activity.partnerAccount.address.addressName" value="${requestsDebriefForm.activity.partnerAccount.address.addressName}"/>
	<input type="hidden" name="activity.partnerAccount.address.addressLine1" value="${requestsDebriefForm.activity.partnerAccount.address.addressLine1}"/>
	<input type="hidden" name="activity.partnerAccount.address.addressLine2" value="${requestsDebriefForm.activity.partnerAccount.address.addressLine2}"/>
	<input type="hidden" name="activity.partnerAccount.address.addressLine3" value="${requestsDebriefForm.activity.partnerAccount.address.addressLine3}"/>
	<input type="hidden" name="activity.partnerAccount.address.addressLine4" value="${requestsDebriefForm.activity.partnerAccount.address.addressLine4}"/>
	<input type="hidden" name="activity.partnerAccount.address.city" value="${requestsDebriefForm.activity.partnerAccount.address.city}"/>
	<input type="hidden" name="activity.partnerAccount.address.state" value="${requestsDebriefForm.activity.partnerAccount.address.state}"/>
	<input type="hidden" name="activity.partnerAccount.address.province" value="${requestsDebriefForm.activity.partnerAccount.address.province}"/>
	<input type="hidden" name="activity.partnerAccount.address.postalCode" value="${requestsDebriefForm.activity.partnerAccount.address.postalCode}"/>
	<input type="hidden" name="activity.partnerAccount.address.country" value="${requestsDebriefForm.activity.partnerAccount.address.country}"/>
	<input type="hidden" name="activity.partnerAccount.address.newAddressFlag" value="${requestsDebriefForm.activity.partnerAccount.address.newAddressFlag}"/>
	<input type="hidden" name="activity.partnerAccount.address.stateProvince" value="${requestsDebriefForm.activity.partnerAccount.address.stateProvince}"/>
	<input type="hidden" id="problemCodes" name="activity.actualFailureCode.value" value="${requestsDebriefForm.activity.actualFailureCode.value}"/>
	<input type="hidden" name="activity.serviceRequest.primaryContact.contactId" value="${requestsDebriefForm.activity.serviceRequest.primaryContact.contactId}"/>
	<input type="hidden" name="activity.serviceRequest.primaryContact.department" value="${requestsDebriefForm.activity.serviceRequest.primaryContact.department}"/>
	<input type="hidden" name="activity.serviceRequest.primaryContact.workPhone" value="${requestsDebriefForm.activity.serviceRequest.primaryContact.workPhone}"/>
	<input type="hidden" name="activity.serviceRequest.primaryContact.emailAddress" value="${requestsDebriefForm.activity.serviceRequest.primaryContact.emailAddress}"/>
	<input type="hidden" name="activity.serviceRequest.primaryContact.firstName" value="${requestsDebriefForm.activity.serviceRequest.primaryContact.firstName}"/>
	<input type="hidden" name="activity.serviceRequest.primaryContact.lastName" value="${requestsDebriefForm.activity.serviceRequest.primaryContact.lastName}"/>
	<input type="hidden" name="activity.serviceRequest.primaryContact.country" value="${requestsDebriefForm.activity.serviceRequest.primaryContact.country}"/>
	<input type="hidden" name="activity.serviceRequest.primaryContact.shortId" value="${requestsDebriefForm.activity.serviceRequest.primaryContact.shortId}"/>
	
	<input type="hidden" name="activity.activityStatus.value" value="${requestsDebriefForm.activity.activityStatus.value}"/>
	<input type="hidden" name="activity.activitySubStatus.value" value="${requestsDebriefForm.activity.activitySubStatus.value}"/>
	
	<input type="hidden" name="activity.serviceRequest.serviceRequestNumber" value="${requestsDebriefForm.activity.serviceRequest.serviceRequestNumber}"/>
	
	<form:hidden path="timezoneOffset"/>
	<input type="hidden" name="fromPage" value="${requestsDebriefForm.fromPage}"/>
	<form:hidden path="submitToken" />
	<div class="main-wrap height-100per" >
		<div class="content">
			<!-- Export(print,csv,pdf) Start -->
			<div class="grid-controls">	
				<div class="utilities">	
				</div>
				<div class="expand-min">
					<ul>
						<li class="first">
							<a id="headerImageButton" class="button" href="${requestsListViewUrl}&back=true">&lt;&lt;<spring:message code="link.return.to.requests"/></a><!-- Changed By sabya for AIR00072632 -->
	           	 		</li>
	           	 	</ul>
	           	 </div>
	           	 <div class="clear-right"></div><!-- clear -->	
			</div>
	        <!-- Export(print,csv,pdf) End -->
			
			<!-- Request Information Start -->  
			<div class="portlet-wrap">
	            <div class="portlet-header">
	            <table class="displayGrid">
              <thead>
                <tr><th><spring:message code="claim.label.requestInformation" /></th>
                </tr></thead>
              
            </table>
	            
	            </div>
	            <div class="portlet-wrap-inner">
	            	<div class="width-100per">
		                <div class="columns two">
		                    <div class="first-column">
		                        <dl>
		                            <dd>
		                                <table>
		                                    <tr>
		                                        <td align="right"><label><spring:message code="claim.label.reuqestNumber" /></label></td>
		                                        <td>
		                                           ${requestsDebriefForm.activity.serviceRequest.serviceRequestNumber}
		                                        </td>
		                                    </tr>
		                                    <tr>
		                                        <td align="right"><label><spring:message code="claim.label.serviceProviderReferenceNumber" /></label></td>
		                                        <td>
		                                           ${requestsDebriefForm.activity.serviceRequest.referenceNumber}
		                                        </td>
		                                    </tr>
		                                    <tr>
		                                        <td align="right"><label><spring:message code="claim.label.openedDateTime" /></label></td>
		                                        <td>
		                                        	<util:dateFormat value="${requestsDebriefForm.activity.serviceRequest.serviceRequestDate}" showTime="true" timezoneOffset="${requestsDebriefForm.timezoneOffset}"/>
		                                        </td>
		                                    </tr>
		                                    <tr>
		                                        <td align="right"><label><spring:message code="claim.label.requestStatus" /></label></td>
		                                        <td>
		                                           ${requestsDebriefForm.activity.activityStatus.name}
		                                        </td>
		                                    </tr>
		                                    <tr>
		                                        <td align="right"><label><spring:message code="claim.label.statusDetail" /></label></td>
		                                        <td>
		                                           ${requestsDebriefForm.activity.activitySubStatus.name}
		                                        </td>
		                                    </tr>
		                                </table>
		                            </dd>
		                            
		                        </dl>
		                    </div>
		                    
		                </div>
	                </div>
	            </div>
	            <div class="portlet-footer">
	                <div class="portlet-footer-inner">
	                </div><!-- portlet-footer-inner -->
	            </div>
	        </div>
	        <!-- Request Information End --> 
	        
	        <!-- Device Information Start -->
	        <div class="portlet-wrap">
	            <div class="portlet-header">
	            <table class="displayGrid">
              <thead>
                <tr><th><spring:message code="claim.label.deviceInformation" /></th>
                </tr></thead>
              
            </table>
	                
	            </div>
	            <div class="portlet-wrap-inner">
	            	<div class="width-100per">
		                <div class="columns two">
		                    <div class="first-column">
		                        <dl>
		                            <dd>
		                                <H3>${requestsDebriefForm.activity.serviceRequest.asset.productLine}</H3>
		                            </dd>
		                            <dd>
		                                <div class="icon" id="manImage">
		                                   <img width="75" src="${requestsDebriefForm.activity.serviceRequest.asset.productImageURL}"/>
		                                </div>
		                            </dd>
		                        </dl>
		                    </div>
		                    <div class="second-column">
		                        <dl>
		                            <dd>
		                                &nbsp;
		                            </dd>
		                            <dd>
		                                &nbsp;
		                            </dd>
		                            <dd>
		                                <table>
		                                    <tr>
		                                        <td align="right"><label><spring:message code="claim.label.serialNumber" /></label></td>
		                                        <td>
		                                           ${requestsDebriefForm.activity.serviceRequest.asset.serialNumber}
		                                        </td>
		                                    </tr>
		                                    <tr>
		                                        <td align="right"><label><spring:message code="claim.label.machineTypeModel" /></label></td>
		                                        <td>
		                                           ${requestsDebriefForm.activity.serviceRequest.asset.modelNumber}
		                                        </td>
		                                    </tr>
		                                    <tr>
		                                        <td align="right"><label><spring:message code="claim.label.productPN/TLI" /></label></td>
		                                        <td>
		                                           ${requestsDebriefForm.activity.serviceRequest.asset.productTLI}
		                                        </td>
		                                    </tr>
		                                </table>
		                            </dd>
		                            
		                        </dl>
		                    </div>
		                </div>
		             </div>
	            </div>
	            <div class="portlet-footer">
	                <div class="portlet-footer-inner">
	                </div><!-- portlet-footer-inner -->
	            </div>
	        </div>
	        <!-- Device Information End -->
	        
	        <!-- Service History Start -->
	       		<c:set var="assetId" value="${requestsDebriefForm.activity.serviceRequest.asset.assetId}"></c:set>
	       		<c:set var="serviceRequestId" value="${requestsDebriefForm.activity.serviceRequest.id}"></c:set>
				<%@ include file="/WEB-INF/jsp/request/serviceRequestHistoryList.jsp"%>
	        <!-- Service History End-->
	        
	        <!-- Customer Information Start-->
			<div id="customerInformation" class="portlet-wrap">
				<div class="portlet-header">
				<table class="displayGrid">
              <thead>
                <tr><th><spring:message code="claim.label.customerInformation" /></th>
                </tr></thead>
              
            </table>
					
				</div>
				<div class="portlet-wrap-inner">
					<div class="width-100per">
						<div class="columns three">
							<div class="first-column">
								<table width="100%" class="table-style1">			
									<tr>
										<td></td>
										<td width="20px"/></td>
										<td><u><B><spring:message code="claim.label.customerAccount" /></B></u></td>
									</tr>
									<tr>
										<td  align="right"><B><spring:message code="claim.label.name" /></B></td>
										<td width="20px"/></td>
										<td>${requestsDebriefForm.activity.customerAccount.accountName}</td>
									</tr>
									<tr>
										<td  align="right" valign="top"><B><spring:message code="claim.label.address" /></B></td>
										<td width="20px"/></td>
										<td valign="bottom">
											<util:addressOutput value="${requestsDebriefForm.activity.customerAccount.address}"/>
										</td>
									</tr>
									<tr>
										<td  align="right" valign="top"><B><spring:message code="claim.label.helpDeskReference" /></B></td>
										<td width="20px"/></td>
										<td valign="bottom">${requestsDebriefForm.activity.serviceRequest.customerReferenceNumber}</td>
									</tr>
								</table>
							</div>
							<div class="second-column">
								 <table width="90%" class="table-style2">			
									<tr>
										<td></td>
										<td width="20px"/></td>
										<td><u><B><spring:message code="claim.label.primaryContact" /></B></u></td>
									</tr>
									<tr>
										<td  align="right"><B><spring:message code="claim.label.name" /></B></td>
										<td width="20px"/></td>
										<td>${requestsDebriefForm.activity.serviceRequest.primaryContact.firstName} ${requestsDebriefForm.activity.serviceRequest.primaryContact.lastName}</td>
									</tr>
									<tr>
										<td  align="right"><B><spring:message code="claim.label.phone" /></B></td>
										<td width="20px"/></td>
										<td>${requestsDebriefForm.activity.serviceRequest.primaryContact.workPhone}</td>
									</tr>
									<tr>
										<td  align="right"><B><spring:message code="requestInfo.contactInfo.label.alternatePhone" /></B></td>
										<td width="20px"/></td>
										<td class="word-wrap-break-word">${requestsDebriefForm.activity.serviceRequest.primaryContact.alternatePhone}</td>
									</tr>
									<tr>
										<td  align="right"><B><spring:message code="claim.label.email" /></B></td>
										<td width="20px"/></td>
										<td class="word-wrap-break-word">${requestsDebriefForm.activity.serviceRequest.primaryContact.emailAddress}</td>
									</tr>
								</table>
							</div>
							<div class="third-column">
								<table width="90%" class="table-style2">			
									<tr>
										<td></td>
										<td width="20px"/></td>
										<td><u><B><spring:message code="claim.label.secondaryContact" /></B></u></td>
									</tr>
									<tr>
										<td align="right"><B><spring:message code="claim.label.name" /></B></td>
										<td width="20px"/></td>
										<td>${requestsDebriefForm.activity.serviceRequest.secondaryContact.firstName} ${requestsDebriefForm.activity.serviceRequest.secondaryContact.lastName}</td>
									</tr>
									<tr>
										<td  align="right" class="top-0px"><B><spring:message code="claim.label.phone" /></B></td>
										<td width="20px"/></td>
										<td>${requestsDebriefForm.activity.serviceRequest.secondaryContact.workPhone}</td>
									</tr>
									<tr>
										<td  align="right"><B><spring:message code="requestInfo.contactInfo.label.alternatePhone" /></B></td>
										<td width="20px"/></td>
										<td class="word-wrap-break-word">${requestsDebriefForm.activity.serviceRequest.secondaryContact.alternatePhone}</td>
									</tr>
									<tr>
										<td  align="right" class="top-0px"><B><spring:message code="claim.label.email" /></B></td>
										<td width="20px"/></td>
										<td class="word-wrap-break-word">${requestsDebriefForm.activity.serviceRequest.secondaryContact.emailAddress}</td>
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
	        <!-- Customer Information End-->
						
	        <!-- Technician Information Start -->	
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
		    	var newContactOriginalFlag = "${requestsDebriefForm.activity.technician.newContactFlag}" == "true";
		    	var updateContactOriginalFlag = "${requestsDebriefForm.activity.technician.updateContactFlag}" == "true";
		    	var newContactFlag = newContactOriginalFlag;
		    	var updateContactFlag = updateContactOriginalFlag;
		    	var technicianOriginalFirstName = "${requestsDebriefForm.activity.technician.firstName}";
		    	var technicianOriginalLastName = "${requestsDebriefForm.activity.technician.lastName}";
		    	var technicianFirstName = "${requestsDebriefForm.activity.technician.firstName}";
		    	var technicianLastName = "${requestsDebriefForm.activity.technician.lastName}";

		    	function generateTechnicianinformation(){
		    		document.getElementById("newContactFlagHidden").value = newContactFlag;
		    		document.getElementById("updateContactFlagHidden").value = updateContactFlag;
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
	         
	        <input type="hidden" id="newContactFlagHidden" name="activity.technician.newContactFlag" value="${requestsDebriefForm.activity.technician.newContactFlag}" />
	        <input type="hidden" id="updateContactFlagHidden" name="activity.technician.updateContactFlag" value= "${requestsDebriefForm.activity.technician.updateContactFlag}" />
			<div class="portlet-wrap" id="technician_information">
				<div class="portlet-header">
				<table class="displayGrid">
              <thead>
                <tr><th><spring:message code="claim.label.technicianInformation"/></th>
                </tr></thead>
              
            </table>
					
				</div><!-- portlet-header -->
				<div class="portlet-wrap-inner">
					<div class="width-100per">
						<div class="columns two">
							<div class="first-column">
								<table  border="0" class="margin-bottom-20px">
										<tr>
										  	<td align="right" class="table-td-style7" NOWRAP><b><span class="required">* </span></b><label><spring:message code="claim.label.technician"/></label></td>
										  	<td width="10px"/>
										  	<td>
												<select id="technicianChoose" name="activity.technician.contactId" onchange="showTechnicianInputTexts()">
												<option value="noContact" > </option>	
												<c:forEach items="${requestsDebriefForm.technicianList}" var="technicianEntry" varStatus="loop">
													<option  
													<c:if test="${requestsDebriefForm.activity.technician.contactId == technicianEntry.contactId && !requestsDebriefForm.activity.technician.newContactFlag}">
														selected
													</c:if>
													 value="${technicianEntry.contactId}" >${technicianEntry.lastName}, ${technicianEntry.firstName}</option>
												</c:forEach>
												<option 
													<c:if test="${requestsDebriefForm.activity.technician.newContactFlag}">
															selected
													</c:if>
												 value="" ><spring:message code="claim.label.dropdownlist.optionvalue.technician.other"/></option>
											</select>
											</td>
									 	</tr>
									  	<tr id="technicianFirstNameDD" style="display:none">
											<td align="right"><b><span class="required">* </span></b><label><spring:message code="claim.label.firstName"/></label></td>
											<td/>
											<td>
												<input class="width-130px" type="text" id="technicianFirstNameInput" name="activity.technician.firstName" onblur="validateLength(1,50,this);"
													<c:if test="${requestsDebriefForm.activity.technician.newContactFlag}">
														value="${requestsDebriefForm.activity.technician.firstName}"
													</c:if>
												 />
											</td>
									 	</tr>
									  	<tr id="technicianLastNameDD" style="display:none">
										  	<td align="right"><b><span class="required">* </span></b><spring:message code="claim.label.lastName"/></td>
										 	<td/>
										 	<td>
										    	<input class="width-130px" type="text" id="technicianLastNameInput" name="activity.technician.lastName" onblur="validateLength(1,50,this);"
										    		<c:if test="${requestsDebriefForm.activity.technician.newContactFlag}">
														value="${requestsDebriefForm.activity.technician.lastName}"
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
						
			<!-- Close Out Activity start -->
			<script type="text/javascript">
				var activityId = "${requestsDebriefForm.activity.activityId}";
				var serviceRequestId =  "${requestsDebriefForm.activity.serviceRequest.id}";
				var isAssetBlankValid = true;
				var isAssetOtherAsset = false;
				var isValidMachineTypeModel = false;
				var isValidSerialNumber = false;
				var removeDeviceSectionFlag = "${requestsDebriefForm.removeDeviceSectionFlag}" == "true";
				
				var partAssetList = [];
				<c:forEach items="${requestsDebriefForm.assetList}" var="assetTemp" varStatus = "status" >
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
						jQuery('#actualStartDate').addClass('errorColor');
						result =  false;
						isValidStartDate = false;
					}

					var endDate = document.getElementById("actualEndDate").value;
					if(isNullFun(endDate)){
						fieldname = '<spring:message code="claim.errorMsg.name.actualEndDate"/>';
						showError('<spring:message code="claim.errorMsg.notNull.field" arguments="'+fieldname+'"/>', null, true);
						jQuery('#actualEndDate').addClass('errorColor');
						result =  false;
						isValidEndDate = false;
					}

					if(isValidStartDate && isValidEndDate && convertStringToStandardDateTime(endDate) < convertStringToStandardDateTime(startDate)){
						showError("<spring:message code='claim.errorMsg.actualStartDateLTEndDate'/>", null, true);
						jQuery('#actualStartDate').addClass('errorColor');
						jQuery('#actualEndDate').addClass('errorColor');
						result =  false;
					}


					
					var addtionServiceRequiredValue = document.getElementById("addtionServiceRequiredSelect").value;
					if(isNullFun(addtionServiceRequiredValue)){
						fieldname = '<spring:message code="claim.errorMsg.name.additionalServiceRequired"/>';
						showError('<spring:message code="claim.errorMsg.notNull.field" arguments="'+fieldname+'"/>', null, true);
						jQuery('#addtionServiceRequiredSelect').addClass('errorColor');
						result =  false;
					}

					
					var nonLexmarkSuppliesUsedValue = document.getElementById("nonLexmarkSuppliesUsedSelect").value;
					if(isNullFun(nonLexmarkSuppliesUsedValue)){
						fieldname = '<spring:message code="claim.errorMsg.name.nonLexmarkSuppliesUsed"/>';
						showError('<spring:message code="claim.errorMsg.notNull.field" arguments="'+fieldname+'"/>', null, true);
						jQuery('#nonLexmarkSuppliesUsedSelect').addClass('errorColor');
						result =  false;
					}

					var problemCodeValue = document.getElementById("problemCodeSelect").value;
					if(isNullFun(problemCodeValue)){
						showError('<spring:message code="claim.errorMsg.problemCodeNotNull"/>', null, true);
						jQuery('#problemCodeSelect').addClass('errorColor');
						result =  false;
					}

					var travelDistanceValue = document.getElementById("travelDistanceInput").value;
					if(!isNullFun(travelDistanceValue)){
						var travelDistanceSelectValue = document.getElementById("travelDistanceSelect").value;
						if(isNullFun(travelDistanceSelectValue)){
							fieldname = '<spring:message code="claim.errorMsg.name.travelUnitOfMeasure"/>';
							showError('<spring:message code="claim.errorMsg.notNull.field" arguments="'+fieldname+'"/>', null, true);
							jQuery('#travelDistanceSelect').addClass('errorColor');
							result =  false;
						}
					}

					
					var resolutionCodeValue = document.getElementById("resolutionCodeSelect").value;
					if(isNullFun(resolutionCodeValue)){
						showError("<spring:message code='claim.errorMsg.resolutionCodeNotNull'/>", null, true);
						jQuery('#resolutionCodeSelect').addClass('errorColor');
						result =  false;
					}

					var repairDescriptionValue = document.getElementById("repairDescriptionInput").value || "";
					repairDescriptionValue = repairDescriptionValue.trim();
					if(isNullFun(repairDescriptionValue)){
						showError("<spring:message code='claim.errorMsg.repairDescNotNull'/>", null, true);
						jQuery('#repairDescriptionInput').addClass('errorColor');
						result =  false;
					}

					var deviceConditionValue = document.getElementById("deviceConditionSelect").value;
					if(isNullFun(deviceConditionValue)){
						showError("<spring:message code='claim.errorMsg.printerWorkingConditionNotNull'/>", null, true);
						jQuery('#deviceConditionSelect').addClass('errorColor');
						result =  false;
					}
					
					if(removeDeviceSectionFlag){
						if(isAssetBlankValid){
							showError("<spring:message code='claim.errorMsg.Asset'/>", null, true);
							jQuery('#assetListSelect').addClass('errorColor');
							result =  false;
						}else if(isAssetOtherAsset){
							document.getElementById("modelNumberHidden").value = document.getElementById("machineTypeInput").value;
							document.getElementById("serialNumberHidden").value = document.getElementById("serialNumberInput").value;
							
							if(!isValidMachineTypeModel){
								showError("<spring:message code='deviceSelection.errorMsg.machineTypeErrorMsg'/>", null, true);
								jQuery('#machineTypeInput').addClass('errorColor');
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
							jQuery('#ipAddressInput').addClass('errorColor');
							result =  false;
						}
							
						var macAddressValue = document.getElementById("macAddressInput").value;
						macAddressValue=macAddressValue.trim();
						if(!isNullFun(macAddressValue) && !isMACAddress(macAddressValue)){
							fieldname = '<spring:message code="claim.errorMsg.name.macAdress"/>';
							showError('<spring:message code="claim.errorMsg.invalid.field" arguments="'+fieldname+'"/>', null, true);
							jQuery('#macAddressInput').addClass('errorColor');
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
			</script>
			<input type="hidden" id="modelNumberHidden" name="activity.debrief.installedAsset.modelNumber"/>
			<input type="hidden" id="serialNumberHidden" name="activity.debrief.installedAsset.serialNumber"/>
			<div class="portlet-wrap">
				<div class="portlet-header">
				<table class="displayGrid">
              <thead>
                <tr><th><spring:message code="claim.label.closeOutActivity" /></th>
                </tr></thead>
              
            </table>
					
				</div>
				<div class="portlet-wrap-inner">
					<div id="closeOutActivity1">
						<div class="width-100per">
							<div class="columns two">
								<table width="100%">
									<tr>
										<td align="left" valign="top" width="50%">
											<table class="test_cal_icon">
												<tr>
													<td align="right" class="width-150px"><b><span class="required">* </span></b><spring:message code="claim.label.actualStartDate" />&nbsp;</td>
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
															<c:forEach items="${requestsDebriefForm.travelUnitOfMeasureMap}" var="travelUnitOfMeasure" varStatus="loop">
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
											        <td align="right"><b><span class="required">* </span></b><spring:message code="claim.label.problemCode1" />&nbsp;</td>
											        <td align="left">
											        	<select id="problemCodeSelect" name="activity.debrief.actualFailureCode.value" onchange="getprobCode(this.value,1)">
															<option value=""></option>
															<c:forEach items="${requestsDebriefForm.problemCodeMap}" var="problemCodeEntry" varStatus="loop">
																<option  
																<c:if test="${requestsDebriefForm.activity.actualFailureCode.value == problemCodeEntry.key}">
																	selected
																</c:if>
																 value="${problemCodeEntry.key}" > ${problemCodeEntry.value} </option>
															</c:forEach>
														</select>
													</td>
												</tr>
												<tr><td height="5px" colspan="2"></td></tr>
												<tr>
													<td align="right" id="problemCode2Div" style="display: none;"><b><span class="required">* </span></b><spring:message code="claim.label.problemCode2" /> &nbsp;</td>
													<td align="left">
											        	<span id="showProbErrorCode2"></span>
													</td>
												</tr>
												<tr><td height="5px" colspan="2"></td></tr>
												<tr>
													<td align="right" id="problemCode3Div" style="display: none;"><b><span class="required">* </span></b><spring:message code="claim.label.problemCode3" /> &nbsp;</td>
													<td align="left">
											        	<span id="showProbErrorCode3"></span>
													</td>
												</tr>
												<tr><td height="5px" colspan="2"></td></tr>
												<tr>
											        <td align="right" valign="top"><spring:message code="claim.label.problemDetails" />&nbsp;</td>
											        <td align="left"  valign="top">
														<textarea id="problemDescriptionInput" name="activity.serviceRequest.problemDescription" cols="80" rows="6" onblur="validateLength(0,2000,this);" class="problem-description-input">${requestsDebriefForm.activity.serviceRequest.problemDescription}</textarea>
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
															<c:forEach items="${requestsDebriefForm.resolutionCodeMap}" var="resolutionCodeEntry" varStatus="loop">
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
								
								<c:if test="${requestsDebriefForm.removeDeviceSectionFlag == 'false'}">
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
															<c:forEach items="${requestsDebriefForm.workingConditionaMap}" var="workingConditionaEntry" varStatus="loop">
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
													   			 <c:if test="${!requestsDebriefForm.networkConnectedFlag}">
													   		 		selected
													   		 	 </c:if>
													   		 ><spring:message code="selection.label.No" /></option>
													  		 <option value="true"
													  		 	 <c:if test="${requestsDebriefForm.networkConnectedFlag}">
													   		 		selected
													   		 	 </c:if>
													  		 ><spring:message code="selection.label.yes" /></option>
														</select>
													</td>
												</tr>
												<tr><td height="5px" colspan="2"></td></tr>
												
												 <c:if test="${!requestsDebriefForm.networkConnectedFlag}">
													<tr>
												        <td align="right"><div id="ipAddressLableDiv" style="display:none;"><spring:message code="claim.label.ipAddress" />&nbsp;</div></td>
												        <td align="left">
												        	<input type="text" name="activity.serviceRequest.asset.ipAddress" id="ipAddressInput" style="display:none;" class="width-190px"  value="${requestsDebriefForm.activity.serviceRequest.asset.ipAddress}" onblur="validateLength(0,100,this);">
												        </td>
													</tr>
													<tr><td height="5px" colspan="2"></td></tr>
													<tr>
												        <td align="right"><div id="macAddressLableDiv" style="display:none;"><spring:message code="claim.label.macAddress" />&nbsp;</div></td>
												        <td align="left">
												        	<input type="text" name="activity.serviceRequest.asset.macAddress" id="macAddressInput" style="display:none;" class="width-190px"  value="${requestsDebriefForm.activity.serviceRequest.asset.macAddress}" onblur="validateLength(0,100,this);">
												        </td>
													</tr>
												</c:if>
												
												 <c:if test="${requestsDebriefForm.networkConnectedFlag}">
													<tr>
												        <td align="right"><div id="ipAddressLableDiv"><spring:message code="claim.label.ipAddress" />&nbsp;</div></td>
												        <td align="left">
												        	<input type="text" name="activity.serviceRequest.asset.ipAddress" id="ipAddressInput" class="width-190px"  value="${requestsDebriefForm.activity.serviceRequest.asset.ipAddress}" onblur="validateLength(0,100,this);">
												        </td>
													</tr>
													<tr><td height="5px" colspan="2"></td></tr>
													<tr>
												        <td align="right"><div id="macAddressLableDiv"><spring:message code="claim.label.macAddress" />&nbsp;</td>
												        <td align="left">
												        	<input type="text" name="activity.serviceRequest.asset.macAddress" id="macAddressInput" class="width-190px"  value="${requestsDebriefForm.activity.serviceRequest.asset.macAddress}" onblur="validateLength(0,50,this);">
												        </td>
													</tr>
												</c:if>
												
											</table>
											</div>
										</td>
									</tr>
								</table>
								</c:if>
								
								<c:if test="${requestsDebriefForm.removeDeviceSectionFlag == 'true'}">
								<table width="50%">
									<tr>
								        <td colspan="2" align="left"><h3>&nbsp;&nbsp;<spring:message code="claim.label.removedDeviceInformation" /></h3></td>
									</tr>
									<tr>
								        <td align="right"><b><span class="required">* </span></b><spring:message code="claim.label.printerWorkingCondition" />&nbsp;</td>
								        <td align="left">
											<select id="deviceConditionSelect" name="activity.debrief.deviceCondition.value">
												<option value=""></option>
												<c:forEach items="${requestsDebriefForm.workingConditionaMap}" var="workingConditionaEntry" varStatus="loop">
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
															<c:forEach items="${requestsDebriefForm.assetList}" var="assetTemp" varStatus = "status" >
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
											   		<td align="left"><input type="text" name="activity.debrief.installedAsset.assetTag" id="installedLexmarkTag" class="width-190px;" onblur="validateLength(0,50,this);"></td>
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
				<div class="portlet-footer">
					<div class="portlet-footer-inner"></div>
				</div>	
			</div>		
			<!-- Close Out Activity End -->
					
			<!-- Parts and Tools Start -->
			<script type="text/javascript">
			var isPartnerAddressGridLoaded = false;
			var isPageLoaded = false;
			var isChooseAdifferentAddress = true;
			var favStatus = {};
			var modelNumber = "${requestsDebriefForm.activity.serviceRequest.asset.modelNumber}";
			var orgId = "${requestsDebriefForm.activity.partnerAccount.organizationID}";
			var partStatus = new Array();
			var partStatusValue = new Array();
			var errorCode1 = new Array();
			var errorCode1Value = new Array();
			var carrier = new Array();
			var carrierValue = new Array();
			var source = new Array();
			var sourceValue = new Array();
			var partDate = localizeFormatDate(new Date());

			<c:forEach items="${requestsDebriefForm.partStatusList}" var="partStatus" varStatus = "status" >
				partStatus[${status.index}] = "${partStatus.value}";
				partStatusValue[${status.index}] = "${partStatus.key}";
			</c:forEach>

			<c:forEach items="${requestsDebriefForm.errorCode1List}" var="errorCode1" varStatus = "status" >
				errorCode1[${status.index}] = "${errorCode1.value}";
				errorCode1Value[${status.index}] = "${errorCode1.key}";
			</c:forEach>

			<c:forEach items="${requestsDebriefForm.carrierList}" var="carrier" varStatus = "status" >
				carrier[${status.index}] = "${carrier.value}";
				carrierValue[${status.index}] = "${carrier.key}";
			</c:forEach>
			
			<c:forEach items="${requestsDebriefForm.sourceList}" var="source" varStatus = "status" >
				source[${status.index}] = "${source.value}";
				sourceValue[${status.index}] = "${source.key}";
			</c:forEach>
			
			</script>
			<div class="portlet-wrap width-100per" id="partAndTool">
				<div class="portlet-header">
				<table class="displayGrid">
              <thead>
                <tr><th><spring:message code="claim.label.partsAndTools"/></th>
                </tr></thead>
              
            </table>
					
				</div>
				<div class="portlet-wrap-inner">
					<div class="columns">
						<div id="gridCCVPartDebrief1" class="width-100per"></div>
						<div id="pagingPartDebrief"></div>
					</div>
					<c:if test="${requestsDebriefForm.activity.partnerAccount.orderPartsDebriefFlag}">
						<div class="columns" style="position:relative; width: 95%; padding: 15px 0px; overflow-x: auto; overflow-y: hidden; min-width: 1024px; max-width: 1200px; width: expression(this.width > 1200 ? 1200: true;">
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
													<a href="javascript:searchPart(document.getElementById('partNumber').value);" class="button" id="partSearchButton"><span><spring:message code="claim.button.addPart"/></span></a>&nbsp;&nbsp;
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
								<div id="qtyValidate" class="qty-validate2"></div>
								<div id="gridAdditionalParts1" class="width-100per"></div>
								<div id="pagingAdditionalParts"></div>
							</div>
						</div>
					
					<div id="partnerAddressDiv" style="display:none">
					<!--code hided by Ragesree-->
						<table>
							<tr>
								<td valign="top" align="left" width="80px">
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b><spring:message code="claim.label.shipTo"/></b>
								</td>
								<td width="590px">
									<table width="590px" id="addressInfo" name="addressInfo" >
										<tr><td><label id="addressNameLabel" style="display:none;"></label></td></tr>
										<tr><td><label id="addressLine1Label"></label></td></tr>
										<tr><td><label id="addressLine2Label"></label></td></tr>
										<tr><td><label id="addressLine3Label"></label></td></tr>
										<tr>
											<td>
												<label id="cityLabel"></label>, <label id="stateLabel"></label> 
												<label id="provinceLabel"></label> <label id="stateProvinceLabel"></label>
												<label id="commaLevel">,</label> <label id="countryLabel"></label>, <label id="postalCodeLabel"></label><br></br>
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
							<tr id="selectOtherAddress">
								<td colspan="2">
									</br>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<a href="javascript:void(0);" onClick="chooseADifferentAddress();return false;"><spring:message code="claim.label.selectAddress" /></a>
								</td></tr>
						</table>
					</div>
					<!--  address list start -->
					<div id="partnerAddressList" class="scroll" style="display:none"> <!-- hidden by ragesree 2092 -->
							<table width="100%">
							<tr>
								<td class="contactsTD">
									<a href="javascript:switchToAllAddresses();" class="button" id="accountAddressesBtn">
										<span><spring:message code="claim.button.allAccountAddresses"/></span>
									</a>&nbsp;
									<a href="javascript:switchToMyAddresses();" id="myFavoriteAddressesBtn" class="selected_fav_button_anchor" style="background-Color:#6495ED;">
										<span><spring:message code="claim.button.myAddresses"/></span>
									</a>
								</td>
							</tr>
							<tr>
								<td>
								<div id="gridCCVAllPartnerAddress" class="gridCCVAllPartnerAddress2"
									style="display:none;"></div>
								<div id="loadingNotificationAll" class="gridLoading" style=" display:none;"><!--spring:message code="label.loadingNotification"/-->&nbsp;<img
									src="<html:imagesPath/>gridloading.gif" /><br>
								</div>
								<div id="pagingAreaAll"></div>
								<div id="gridCCVMyPartnerAddress" class="gridCCVMyPartnerAddress"></div>
								<div id="loadingNotificationMy" class="gridLoading">
									<!--spring:message code="label.loadingNotification"/-->&nbsp;
									<img src="<html:imagesPath/>gridloading.gif" /><br>
								</div>
								<div id="pagingAreaMy"></div>
								</td>
							</tr>
							<c:if test="${requestsDebriefForm.activity.partnerAccount.createShipToAddressFlag}">
								<tr>
									<td align="left">
										<a href="javascript:popupCreateNewAddressPage();" class="button" id="createSecondaryBtn"><span><spring:message code="claim.button.createNew"/></span></a>
									</td>
								</tr>
							</c:if>
							<c:if test="${requestsDebriefForm.activity.partnerAccount.createShipToAddressFlag!=true}">
								<tr>
									<td align="left">
										<label><spring:message code="claim.label.canNotCreateShipToAddress"/></label>
									</td>
								</tr>
							</c:if>
						</table>
					</div><!--  address list end -->
					
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
					url="<portlet:renderURL 
			         	windowState='<%= LiferayWindowState.POP_UP.toString() %>'><portlet:param name='isMaintenance' value='false' />"+
		         	"<portlet:param name='action' value='showPartAndToolPage' /></portlet:renderURL>";
					function partListPopup(){
						jQuery(window).scrollTop(0);
						partListPopUpWindow.show();
						jQuery(".aui button.close, .aui button.btn.close").hide();
						partListPopUpWindow.titleNode.html("<spring:message code='claim.label.partsList'/>");
						partListPopUpWindow.io.set('uri',url);
						partListPopUpWindow.io.start();
						
						}
						/*********************************************************************************
						******************************Addintional parts grid script*************************
						**********************************************************************************/
						additionalPartsGrid = new dhtmlXGridObject('gridAdditionalParts1');
						additionalPartsGrid.setImagePath("<html:imagesPath/>gridImgs/");
						additionalPartsGrid.setHeader(autoAppendPlaceHolder(autoApplyRequiredStyle('<spring:message code="request.headerList.partsAndTools.debrief"/>'),11));
						additionalPartsGrid.setColAlign("left,left,left,left,left,left,left,left,left,left,left,left");
						additionalPartsGrid.setInitWidths("100,40,100,110,*,*,*,*,100,*,*,30");
						additionalPartsGrid.setColTypes("ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro");
						additionalPartsGrid.setColSorting("na,str,str,na,na,na,na,str,na,na,na,na");
						// additionalPartsGrid.enableAutoWidth(true);
						additionalPartsGrid.enableAutoHeight(true);
						additionalPartsGrid.init(); 
						additionalPartsGrid.prftInit();
						additionalPartsGrid.setColumnHidden(5,true);
						additionalPartsGrid.setColumnHidden(6,true);
						additionalPartsGrid.enablePaging(true, 9999, 6, "pagingAdditionalParts", true);
						additionalPartsGrid.setSkin("light");
						//additionalPartsGrid.enableColumnMove(true);
						additionalPartsGrid.setPagingSkin("bricks");
						additionalPartsGrid.attachEvent("onMouseOver", function(id,ind){		
							return false;
						});
						additionalPartsGrid.attachEvent("onRowAdded", function(rId){
							document.getElementById("partNumber").value = "";
						}); 
						additionalPartsGrid.attachEvent("onAfterRowDeleted", function(id,pid){
							document.getElementById("partNumber").value = "";
			     		});

						// changes for db saving and retrieving	start						
						
						<c:forEach var="gridSettingVar" items="${gridSettingList}" varStatus="status">
						<c:if test="${gridSettingVar.gridId == 'gridAdditionalParts1'}">
						var colsOrder = "${gridSettingVar.colsOrder}";
						additionalPartsGrid.loadOrder(colsOrder);									
						</c:if>
						</c:forEach>
						additionalPartsGrid.attachEvent("onAfterCMove",function(a,b){
							additionalPartsGrid.enableAutoSavingToDB("${saveCustomizedGridSettingURL}");								
						});
						
						// changes for db saving and retrieving	end

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
							url+="&partNumber="+partNumber+"&modelNumber="+modelNumber+"&organizationId="+orgId+"&isMaintenance=false";
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
								,'<table><tr><td><label id="errorCode2Loading_'+id+'" style="display: none;"><img  src = "<html:imagesPath/>loading-icon.gif"; />&nbsp;<spring:message code="label.loadingNotification" /></label><select style="width:130px;display:none;" id="errorCode2_'+id+'"></select></td></tr></table>'
								,'<table><tr><td><select style="width:100px;display:none;" id="source_'+id+'"><option value=""></option>'+generateOption(source)+'</select></td></tr></table>'
								,(part[2]=="true"?'<spring:message code="claim.lebel.returnRequired.yes"/>':'<spring:message code="claim.lebel.returnRequired.unknown"/>')
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
									if(partNumber == partsAndToolsDebriefGrid.cellByIndex(i,1).cell.innerHTML)
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
						allPartnerAddressGrid = new dhtmlXGridObject('gridCCVAllPartnerAddress'); 	 
						allPartnerAddressGrid.setImagePath("<html:imagesPath/>gridImgs/");
						allPartnerAddressGrid.setHeader(autoAppendPlaceHolder('<spring:message code="claim.headerList.address"/>',10));
						allPartnerAddressGrid.attachHeader("#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter");
						allPartnerAddressGrid.setInitWidths("100,150,200,150,150,100,100,100,100,150");
						allPartnerAddressGrid.setColAlign("left,left,left,left,left,left,left,left,left,left");
						allPartnerAddressGrid.setColTypes("ro,rotxt,rotxt,rotxt,rotxt,rotxt,ro,ro,ro,ro");
						allPartnerAddressGrid.setColSorting("na,str,str,str,str,str,str,str,str,na");
						allPartnerAddressGrid.init();
						allPartnerAddressGrid.prftInit();
						allPartnerAddressGrid.enablePaging(true, 5, 6, "pagingAreaAll", true, "infoArea");
						allPartnerAddressGrid.setPagingSkin("bricks");
						allPartnerAddressGrid.enableAutoWidth(true);
						allPartnerAddressGrid.enableAutoHeight(true);
						allPartnerAddressGrid.enableMultiline(true);
						allPartnerAddressGrid.a_direction = "ASCENDING";
						allPartnerAddressGrid.setColumnHidden(0,true); // indicate column index and set the state to true to hire the column
						allPartnerAddressGrid.setSkin("light");
						allPartnerAddressGrid.attachEvent("onXLS", function() {
							document.getElementById('loadingNotificationAll').style.display = '';
						});
						allPartnerAddressGrid.attachEvent("onMouseOver", function(id,ind){		
							return false;
						});
						allPartnerAddressGrid.attachEvent("onXLE", function() {
							isPartnerAddressGridLoaded=true;
							allPartnerAddressGrid.setSortImgState(true, 2, "asc");
							document.getElementById('loadingNotificationAll').style.display = 'none';
							setTimeout(function(){
					    		rebrandPagination();
					    	
					    	},100);
						});
				
						//myPartnerAddressGrid for My Addresses
						myPartnerAddressGrid = new dhtmlXGridObject('gridCCVMyPartnerAddress');
						myPartnerAddressGrid.setImagePath("<html:imagesPath/>gridImgs/");
						myPartnerAddressGrid.setHeader(autoAppendPlaceHolder('<spring:message code="claim.headerList.address"/>',10));
						myPartnerAddressGrid.attachHeader("&nbsp;,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,&nbsp;");
						myPartnerAddressGrid.setInitWidths("100,150,200,150,150,100,100,100,100,150");
						myPartnerAddressGrid.setColAlign("left,left,left,left,left,left,left,left,left,left");
						myPartnerAddressGrid.setColTypes("ro,rotxt,rotxt,rotxt,rotxt,rotxt,ro,ro,ro,ro");
						myPartnerAddressGrid.setColSorting("na,str,str,str,str,str,str,str,str,na");
						myPartnerAddressGrid.init();
						myPartnerAddressGrid.prftInit();
						myPartnerAddressGrid.enablePaging(true, 5, 6, "pagingAreaMy", true, "infoArea");
						myPartnerAddressGrid.setPagingSkin("bricks");
						myPartnerAddressGrid.enableAutoWidth(true);
						myPartnerAddressGrid.enableAutoHeight(true);
						myPartnerAddressGrid.enableMultiline(true);
						myPartnerAddressGrid.a_direction = "ASCENDING";  
						myPartnerAddressGrid.setColumnHidden(0,true); // indicate column index and set the state to true to hire the column
						myPartnerAddressGrid.setSkin("light");
						myPartnerAddressGrid.attachEvent("onXLS", function() {
							if(!isChooseAdifferentAddress){
								document.getElementById('loadingNotificationMy').style.display = 'block';
							}
						});
						myPartnerAddressGrid.attachEvent("onMouseOver", function(id,ind){		
							return false;
						});
						myPartnerAddressGrid.attachEvent("onXLE", function() {
							myPartnerAddressGrid.setSortImgState(true, 2, "asc");
							if(isChooseAdifferentAddress){
								isChooseAdifferentAddress = false;
								document.getElementById('partnerAddressList').style.display = 'block';
								document.getElementById("partnerAddressDiv").style.display="none";	
								if(myPartnerAddressGrid.getRowsNum()==0){
									switchToAllAddresses();
									}
								else{
									switchToMyAddresses();
								}
						
							}
							document.getElementById('loadingNotificationMy').style.display = 'none';
							setTimeout(function(){
					    		rebrandPagination();
					    	
					    	},100);
					
						});
						function setPartnerAddressFavourite(favoriteAddressId, doSelectUnfavoriteFlag) {
							var url = '<portlet:resourceURL id="setPartnerAddressFavouriteFlag"/>';
							url += "&favoriteAddressId="+favoriteAddressId;
							url += "&accountId=${requestsDebriefForm.activity.partnerAccount.accountId}";
							
							// if user selects an unfavorite contact
							if (doSelectUnfavoriteFlag) {
								url += "&flagStatus=" + "false";
								doAjax(url, null, null, "");
								return;
							}
							// if the favorite flag is being updated
							if (favStatus[favoriteAddressId]&&favStatus[favoriteAddressId].isLoading) {
								return;
							}
					
							var starImg = window.document.getElementById("starImg_address_" + favoriteAddressId);
							var isFavorite = !(starImg.src.indexOf("unfavorite.png") > -1);
							favStatus[favoriteAddressId] = {isFavorite:isFavorite,isLoading:true};
							starImg.src = "<html:imagesPath/>loading-icon.gif";
							url += "&flagStatus=" + isFavorite;
							
							doAjax(url, callbackGetPartnerAddressResult, callbackGetPartnerAddressFailure, "");
				
						}
				
						function callbackGetPartnerAddressResult(result) {
							var addressId = result.data;
							if (document.getElementById("gridCCVMyPartnerAddress").style.display!="none") {		
								var starImg = document.getElementById('starImg_address_' + addressId);
								if (favStatus[addressId].isFavorite) {
									starImg.src = "<html:imagesPath/>unfavorite.png";
								} else {
									starImg.src = "<html:imagesPath/>favorite.png";
								}
							}
							if (favStatus[addressId].isFavorite) {
								favStatus[addressId].isFavorite = false;
							} else {
								favStatus[addressId].isFavorite = true;
							}
							favStatus[addressId].isLoading = false;
						}
				
						function callbackGetPartnerAddressFailure(result) {
							var addressId = result.data;
							if (document.getElementById("gridCCVMyPartnerAddress").style.display!="none") {      
								var starImg = document.getElementById('starImg_address_' + addressId);
								if (favStatus[addressId].isFavorite) {
									starImg.src = "<html:imagesPath/>favorite.png";
								} else {
									starImg.src = "<html:imagesPath/>unfavorite.png";
								}
							}
							favStatus[addressId].isLoading = false;
						}
				
						function switchToAllAddresses(){			
					  		document.getElementById("gridCCVAllPartnerAddress").style.display="block";
					 		document.getElementById("gridCCVMyPartnerAddress").style.display="none";
					 		document.getElementById("loadingNotificationMy").style.display="none";
					 		document.getElementById("pagingAreaAll").style.display="block";
					 		document.getElementById("pagingAreaMy").style.display="none";
					  		changeMyColor('accountAddressesBtn');
					  		if(isPartnerAddressGridLoaded==false){
						  		allPartnerAddressGrid.clearAndLoad("${requestsDebriefForm.partnerAddressListURL}&accountId=${requestsDebriefForm.activity.partnerAccount.accountId}&favoriteFlag=false");		
					  		}else{
					  			allPartnerAddressGrid.loadXMLString("<rows></rows>");
						  	}	  
						}
				
						function chooseADifferentAddress(){
							callOmnitureAction('Request Close Out View', 'Choose A Different Address');
							document.getElementById("activity.shipToAddress.addressId").value="";
							if(!isChooseAdifferentAddress){
								document.getElementById('partnerAddressList').style.display = 'block';
								document.getElementById("partnerAddressDiv").style.display = 'none';
								switchToMyAddresses();
								return;
							}
							myPartnerAddressGrid.clearAndLoad("${requestsDebriefForm.partnerAddressListURL}&accountId=${requestsDebriefForm.activity.partnerAccount.accountId}&favoriteFlag=true");
						}
				
						function switchToMyAddresses(){	
							document.getElementById("gridCCVMyPartnerAddress").style.display="block";
							document.getElementById("gridCCVAllPartnerAddress").style.display="none";
							document.getElementById("loadingNotificationAll").style.display="none";
							document.getElementById("pagingAreaMy").style.display="block";
							document.getElementById("pagingAreaAll").style.display="none";
							changeMyColor('myFavoriteAddressesBtn');
							myPartnerAddressGrid.clearAndLoad("${requestsDebriefForm.partnerAddressListURL}&accountId=${requestsDebriefForm.activity.partnerAccount.accountId}&favoriteFlag=true");		
						}
				
						//change color for selected button
						function changeMyColor(btnName){
							  btnEle = document.getElementById(btnName);
							  if(btnEle.id=="myFavoriteAddressesBtn"){
								  addClass(btnEle, "selected_fav_button_anchor");
								  removeClass(btnEle, "not_selected_fav_button_anchor");
								  //addClass(document.getElementById("accountAddressesBtn"), "not_selected_button_anchor");
								  //removeClass(document.getElementById("accountAddressesBtn"), "selected_button_anchor");
							  }
							  if(btnEle.id=="accountAddressesBtn"){
								  //addClass(btnEle, "selected_button_anchor");
								  //removeClass(btnEle, "not_selected_button_anchor");
								  addClass(document.getElementById("myFavoriteAddressesBtn"), "not_selected_fav_button_anchor");
								  removeClass(document.getElementById("myFavoriteAddressesBtn"), "selected_fav_button_anchor");
							  }	  
						}

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
	
						//create a new address
						addressFunction();
					var addressPopUpWindow;
					function addressFunction(){
						AUI().use('aui-base',
						'aui-io-plugin-deprecated',
						'liferay-util-window',
						function(A) {

							addressPopUpWindow=Liferay.Util.Window.getWindow(
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
					urladdress="<portlet:renderURL windowState='<%= LiferayWindowState.POP_UP.toString() %>'>"+
					"<portlet:param name='action' value='showCreateNewAddressPage' /></portlet:renderURL>";
					function addressPopup(){
						jQuery(window).scrollTop(0);
						addressPopUpWindow.show();
						jQuery(".aui button.close, .aui button.btn.close").hide();
						addressPopUpWindow.titleNode.html("<spring:message code='claim.label.createNewAddress'/>");
						addressPopUpWindow.io.set('uri',urladdress);
						addressPopUpWindow.io.start();
						
						}
						function popupCreateNewAddressPage(){
							showOverlayIE6();
							addressPopUpWindow.io.set('uri',urladdress);
							bindingClosePopupIE6();
						};
			
						function addPartnerAddressElement(ai, an, al1, al2, al3, city, state, province, stateProvince, country, zip, currentFavStatus){
							if(favStatus[ai]){  
								currentFavStatus = favStatus[ai].isFavorite? "true":"false";
							}  		
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
							//just for display purpose	
							document.getElementById("addressNameLabel").innerHTML=an;
							document.getElementById("addressLine1Label").innerHTML=al1;
							document.getElementById("addressLine2Label").innerHTML=al2;
							document.getElementById("addressLine3Label").innerHTML=al3;
							document.getElementById("cityLabel").innerHTML=city;
							document.getElementById("stateLabel").innerHTML=state;
							document.getElementById("provinceLabel").innerHTML=province;
							document.getElementById("stateProvinceLabel").innerHTML=stateProvince;
							document.getElementById("countryLabel").innerHTML=country;
							document.getElementById("postalCodeLabel").innerHTML=zip;
							//we will not mark that address as favorite if it is already a favorite one.
							if(isPageLoaded == true && currentFavStatus == "false")
								setPartnerAddressFavourite(ai,true);
							var elmProvince =document.getElementById("provinceLabel");
							var elmState =document.getElementById("stateLabel");
							var elmStateProvince =document.getElementById("stateProvinceLabel");
							var elmCommaLevel=document.getElementById("commaLevel");
					 		if(stateProvince==null||stateProvince==""){
						  		if(province==null||province==""){
							 		elmProvince.style.display="none";
							  		elmState.style.display="";
							 		 elmStateProvince.style.display="none";
							  		elmCommaLevel.style.display="";
						  		}
						  		else{			 
							  		elmState.style.display="none";
							  		elmProvince.style.display="";
							 		elmStateProvince.style.display="none";
							  		elmCommaLevel.style.display="";
						  		}
					  		}else{
						 		if(stateProvince=="nostate"){
									elmProvince.style.display="none";
									elmState.style.display="none";
									elmStateProvince.style.display="none";
									elmCommaLevel.style.display="none";
							  	}else{
									elmProvince.style.display="none";
									elmState.style.display="none";
									elmStateProvince.style.display="";
									elmCommaLevel.style.display="";
							  	}
						  	}
							if(isPageLoaded == true){
								//manually triger the jquery validation framework.
								//jQuery("#createClaimRequestForm").validate().element("#activity\\.shipToAddress\\.addressId");
							}
							document.getElementById("partnerAddressList").style.display="none";
							//document.getElementById("partnerAddressDiv").style.display="block";  //commented by ragesree 2092
							document.getElementById("addressInfo").style.display="block";
						}
						
						jQuery(document).ready(function() {
							if("${requestsDebriefForm.activity.addressStatus}"=="${requestsDebriefForm.addressStatusList[0]}" || "${requestsDebriefForm.activity.partnerAccount.orderPartsDebriefFlag}"=="false"){
								document.getElementById('partnerAddressList').style.display = 'none';
								document.getElementById("partnerAddressDiv").style.display="none";
							}else if("${requestsDebriefForm.activity.addressStatus}"=="${requestsDebriefForm.addressStatusList[1]}"){
								document.getElementById("activity.shipToAddress.addressId").value="";
								document.getElementById("addressNameLabel").innerHTML="";
								document.getElementById("addressInfo").style.display="none";
							}else if("${requestsDebriefForm.activity.addressStatus}"=="${requestsDebriefForm.addressStatusList[3]}"){
								document.getElementById("activity.shipToAddress.addressId").value="";
								document.getElementById("addressNameLabel").innerHTML="";
								document.getElementById("addressInfo").style.display="none";
							}else{
								addPartnerAddressElement("${requestsDebriefForm.activity.partnerAccount.address.addressId}", "${requestsDebriefForm.activity.partnerAccount.address.addressName}", 
													 "${requestsDebriefForm.activity.partnerAccount.address.addressLine1}", "${requestsDebriefForm.activity.partnerAccount.address.addressLine2}", "${requestsDebriefForm.activity.partnerAccount.address.addressLine3}", 
													 "${requestsDebriefForm.activity.partnerAccount.address.city}", 
													 "${requestsDebriefForm.activity.partnerAccount.address.state}", 
													 "${requestsDebriefForm.activity.partnerAccount.address.province}", 
													 "${requestsDebriefForm.activity.partnerAccount.address.stateProvince}", 
													 "${requestsDebriefForm.activity.partnerAccount.address.country}", 
													 "${requestsDebriefForm.activity.partnerAccount.address.postalCode}", 
													 "");
							
							}
							additionalPartsGrid.loadXMLString("<?xml version=\"1.0\" ?><rows></rows>");
						});
				
					</script>
					</c:if>
				</div>	
				<div id="partToolContent" style="display:none;"></div>
				<div class="portlet-footer">
					<div class="portlet-footer-inner"></div>
				</div>		
			</div>
			
			<!-- Parts and Tools end -->
			
			<c:if test="${requestsDebriefForm.showAdditionalPaymentRequestListFlag}">			
			<!-- Additional Payment Requests Start -->
			<div class="portlet-wrap width-100per" id="Additional_payment_requests">
				<div class="portlet-header">
				<table class="displayGrid">
              <thead>
                <tr><th><spring:message code="claim.label.additionalPaymentRequests"/></th>
                </tr></thead>
              
            </table>
				</div><!-- portlet-header -->
				<div class="portlet-wrap-inner">
					<div class="columns">
						<div id="gridAdditionalPaymentRequests" class="width-100per"></div>
						<div id="pagingAdditionalPayment"></div>
					</div>
					<div class="columns">
						<dl>
							<dd>
								<a href="javascript:addNewPaymentRequest();" class="button"><span><spring:message code="claim.button.addNew"/></span></a>
							</dd>
						</dl>
					</div>
				</div><!-- portlet-wrap-inner -->
				<div style="display:none" id="paymentContent"></div>
				<div class="portlet-footer">
					<div class="portlet-footer-inner"></div><!-- portlet-footer-inner -->
				</div><!-- portlet-footer -->
			</div>
			<!-- Additional Payment Requests End -->  	
			</c:if>
						
			<!-- Notes Start -->   
			<div class="portlet-wrap width-100per" id="notes" >
			<div class="portlet-header">
			<table class="displayGrid">
              <thead>
                <tr><th><spring:message code="claim.label.notes"/></th>
                </tr></thead>
              
            </table>
				
			</div><!-- portlet-header -->
			<div class="portlet-wrap-inner">
				<div class="columns">
					<div id="gridRDBrfVNRequest" class="width-100per"></div>
					<div id="loadingNotificationNote" class="gridLoading" style=" display:none;"><!--spring:message code="label.loadingNotification"/-->&nbsp;
						<img src="<html:imagesPath/>gridloading.gif" /><br>
					</div>
					<div id="pagingNote"></div>
					<br><br>
				</div>
				<div class="columns">
					<dl>
						<dd>
							<a href="javascript:addNotePopUp();" class="button"><span><spring:message code="button.addNote"/></span></a>
							<div class="overlayAddnotePopup"></div>
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
				gridRDBrfVNoteGrid = new dhtmlXGridObject('gridRDBrfVNRequest');
				gridRDBrfVNoteGrid.setImagePath("<html:imagesPath/>gridImgs/");
				gridRDBrfVNoteGrid.setHeader(autoAppendPlaceHolder('<spring:message code="claim.headerList.notes.details"/>',5));
				gridRDBrfVNoteGrid.setColAlign("left,left,left,left,left");
				gridRDBrfVNoteGrid.setColTypes("sub_row,ro,ro,ro,ro");
				gridRDBrfVNoteGrid.setColSorting("na,str,str,str,str");
				gridRDBrfVNoteGrid.setInitWidths("20,150,150,350,*");
				gridRDBrfVNoteGrid.enableAutoWidth(true);
				gridRDBrfVNoteGrid.enableAutoHeight(true);
				gridRDBrfVNoteGrid.enableMultiline(true);
				gridRDBrfVNoteGrid.enablePaging(true, 5, 6, "pagingNote", true);	
				gridRDBrfVNoteGrid.init();
				gridRDBrfVNoteGrid.setSkin("light");
				gridRDBrfVNoteGrid.setPagingSkin("bricks");
				gridRDBrfVNoteGrid.attachEvent("onXLS", function() {
					document.getElementById('loadingNotificationNote').style.display = 'block';
				});
				gridRDBrfVNoteGrid.attachEvent("onXLE", function() {
					document.getElementById('loadingNotificationNote').style.display = 'none';
					setTimeout(function(){
			    		rebrandPagination();
			    	
			    	},100);
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
					callOmnitureAction('Request Close Out View', 'Add Note PopUp');
					showOverlayIE6();
					showNotesPopup();
					bindingClosePopupIE6();
				}
				function addRowInNotesGrid(note){
					var subDetail = escapeStringForNoteSubRow(note[0]);
					var id = gridRDBrfVNoteGrid.getUID();
					var str =['<strong><spring:message code="claim.label.note"/></strong><br><div style="word-wrap: break-word;width:80%">'+subDetail+'</div>',note[1],note[2],note[3],
								'<button class="button" type="button" onclick="editRow('+id+')"><spring:message code="button.edit"/></button> <input id="'+id+'_noteDetail" type="hidden" value="'+note[0]+'">'
								+'<input id="'+id+'_contactId" type="hidden" value="${requestsDebriefForm.contactId}">'];
					gridRDBrfVNoteGrid.addRow(id,str,0);
					gridRDBrfVNoteGrid.changePage(1);
				}
				function updateRowInNotesGrid(note,rowId){
					var subDetail = escapeStringForNoteSubRow(note[0]);
					var newRowId = gridRDBrfVNoteGrid.getUID();
					var str =['<strong><spring:message code="claim.label.note"/></strong><br><div style="word-wrap: break-word;width:80%">'+subDetail+'</div>',note[1],note[2],note[3],
								'<button class="button" type="button" onclick="editRow('+rowId+')"><spring:message code="button.edit"/></button> <input id="'+rowId+'_noteDetail" type="hidden" value="'+note[0]+'">'
								+'<input id="'+rowId+'_contactId" type="hidden" value="'+document.getElementById(rowId+"_contactId").value+'">'];
					if(document.getElementById(rowId+"_noteId")){
						str = ['<strong><spring:message code="claim.label.note"/></strong><br><div style="word-wrap: break-word;width:80%">'+subDetail+'</div>',note[1],note[2],note[3],
								'<button class="button" type="button" onclick="editRow('+rowId+')"><spring:message code="button.edit"/></button> <input id="'+rowId+'_noteDetail" type="hidden" value="'+note[0]+'">'
								+'<input id="'+rowId+'_contactId" type="hidden" value="'+document.getElementById(rowId+"_contactId").value+'">'
								+'<input id="'+rowId+'_noteId" type="hidden" value="'+document.getElementById(rowId+"_noteId").value+'">'
								+'<input id="'+rowId+'_activityUpdateFlag" type="hidden" value="true">'];
					}
					gridRDBrfVNoteGrid.addRow(newRowId,str,0);
					gridRDBrfVNoteGrid.copyRowContent(newRowId,rowId);
					gridRDBrfVNoteGrid.deleteRow(newRowId);
					gridRDBrfVNoteGrid.cellById(rowId,0).setContent('<strong><spring:message code="claim.label.note"/></strong><br><div style="word-wrap: break-word;width:80%">'+subDetail+'</div>');
					gridRDBrfVNoteGrid.cellById(rowId,0).close();
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
					var noteDate = gridRDBrfVNoteGrid.cellById(id,1).cell.innerHTML;
					var noteAuthor = gridRDBrfVNoteGrid.cellById(id,2).cell.innerHTML;
					var noteDetailId = id+"_noteDetail";
					showOverlayIE6();
					updateNotesPopup(noteDate,noteAuthor,noteDetailId,id);
					bindingClosePopupIE6();
				}
				
				function generateNotesFormData(){
					document.getElementById("noteContent").innerHTML = "";
					var rows = gridRDBrfVNoteGrid.getRowsNum();
					var noteCurrentPage = gridRDBrfVNoteGrid.currentPage;
					for(var i = 0 ;i < rows ;i++){
						if(i == 0){
							gridRDBrfVNoteGrid.changePage(1);
						}else if(i%global_paging_size ==0){
							gridRDBrfVNoteGrid.changePageRelative(1);
						}
						var rowId = gridRDBrfVNoteGrid.getRowId(i);
						//noteDate
						var inputDate = document.createElement("input");
						inputDate.id = "activity.activityNoteList["+i+"].noteDate"; 
						inputDate.name = "activity.activityNoteList["+i+"].noteDate";
						inputDate.value = formatDateToDefault(gridRDBrfVNoteGrid.cellByIndex(i,1).cell.innerHTML);
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
					gridRDBrfVNoteGrid.changePage(noteCurrentPage);
				} 
			</script>
		    <!-- Notes End -->   
		</div>
	</div>
		     	
</form:form>
<!-- File Attachment start -->
				      
				  <table width="98%" class="margin-left-10px">
				    <tr>
				      <td>
				      <form:form id="attachmentForm" name="attachmentForm" commandName="attachmentFormDisplay" method="post" enctype="multipart/form-data">
				        <div class="portlet-wrap" id="attachmentBorder">
				        	<div class="portlet-header">
				        	<table class="displayGrid">
              <thead>
                <tr><th><spring:message code="claim.label.attachments" /></th>
                </tr></thead>
              
            </table>
								
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
												<a href="javascript:uploadFile();" class="button"><span><spring:message code="button.uploadFile"/></span></a>
											</nobr>		   
										</td>
									</tr>
									<tr class="import-table-tr1"><td></td><td><nobr><b><spring:message code="attachment.messgae.FileRestriction"/></b></nobr></td></tr>
								
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
							<!-- 
								<div id='uploadedFileGrid' style="width:100%;"></div>
								<div id="loadingAttachmentNote" class="gridLoading" style=" display:none;"><spring:message code="label.loadingNotification"/>&nbsp;
									<img src="<html:imagesPath/>gridloading.gif" /><br>
								</div>
								-->
									<table id="fileListTable"></table>							
							</div>
							<div class="portlet-footer">
								<div class="portlet-footer-inner"></div>
							</div>
				        </div>
				     	<iframe id="upload_target" name="upload_target" class="iframe-upload-target"></iframe>
				        
				        <script type="text/javascript">
				       var responseText1;
				     <c:if test="${attachmentFormDisplay.attachmentList != null}">
				     
						jQuery('#fileListTable',window.parent.document).empty();
						
						var responseText = '<thead><tr class="tableHeader"><b><th class="width60"><spring:message code="attachment.message.FileName"/></th><th class="width35"><spring:message code="attachment.message.FileSize"/></th></b></tr></thead>';
						   responseText = responseText + '<tbody>';
							<c:forEach items="${attachmentFormDisplay.attachmentList}" var="entry">
					 			responseText = responseText + '<tr class="tableContentColor">';
					 			responseText = responseText + '<td class="width60">'+ '<a href="#" onClick=\'openFile("${entry.attachmentName}");\'><c:out value="${entry.attachmentName}" /></a>';
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
								var link = "${addAttachmentsDebriefRequest}";
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
						</script>
						</form:form>
					  </td>
				  </tr>
			  </table>
			  <table width="98%" class="margin-left-10px">
			  	<tr>
			  		<td>
			  			<form:form id="dummyForm" name="dummyForm" method="post">
			  			<script type="text/javascript">
			  			</script>
			  				<table class="width-75per">
								<tr>
									<td>
			<div align="left">
				<a href="javascript:submitFormRequestCloseOut();" class="button"><span><spring:message code="button.submit"/></span></a>&nbsp;&nbsp;<a href="javascript:cancelConfirm();" class="button_cancel"><span><spring:message code="button.cancel"/></span></a>
			</div>
									</td>
								</tr>
							</table>
			  			</form:form>
			  		</td>	
			  	</tr>
			  </table>	        
				        <!-- File Attachment end -->
		</td>
	</tr>
</table>     	
	
			<script type="text/javascript">
			
				function submitFormRequestCloseOut(){
					callOmnitureAction('Request Close Out View', 'Submit');
					clearMessage();
					removeErrorClass();
					if(validateBeforeSubmit()){
						callOmnitureAction('Request Close Out View', 'Submit');
						if("${requestsDebriefForm.showAdditionalPaymentRequestListFlag}" == "true"){
							generateAdditionalPaymentFormData();
						}
						generateTechnicianinformation();
						generateNotesFormData();
						generatePartAndTool();
						confirmSubmitDebriefRequest();
					}
					
				}
			
				function validateBeforeSubmit(){
					var result = validateTechnicianinformation();
					result &= validateCloseOutActivityInfo();
					if("${requestsDebriefForm.showAdditionalPaymentRequestListFlag}" == "true"){
						result &= validatePaymentRequests();
					}
					result &= validatePartAndTool();
					//result &= validateShipToAddress();
					
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
						scroll(0,0);
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
					var fromPage = "${requestsDebriefForm.fromPage}";
					callOmnitureAction('Request Close Out View', 'Cancel');
					jiConfirm("<br><spring:message code='claim.message.cancelConfirm'/>", "","<spring:message code='button.ok'/>","<spring:message code='button.goBack'/>","center", function(confirmResult) {
						if(!confirmResult){
							return;
						}else{
							if("requestListView" == fromPage){
								window.location.href="${requestsListViewUrl}";
							}else{
								window.history.go(-1);
							}
						}
					});
				}

				function confirmSubmitDebriefRequest(){
					jConfirm("<spring:message code='request.message.confirmSubmitDebriefRequest'/>", "", function(confirmResult) {
						if(confirmResult){
							var selectProbCode = "";
							var selectprobcode1 = document.getElementById("problemCodeSelect").value;	
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
							jQuery("#requestsDebriefForm").submit();
							setAllToReadOnly();
						}else{
							return;
						}
					});
				}
				
			</script>	


<script type="text/javascript">
	function removeErrorClass(){
		jQuery('#technicianChoose').removeClass('errorColor');
		jQuery('#technicianFirstNameInput').removeClass('errorColor');
		jQuery('#technicianLastNameInput').removeClass('errorColor');
		jQuery('#actualStartDate').removeClass('errorColor');
		jQuery('#actualEndDate').removeClass('errorColor');
		jQuery('#addtionServiceRequiredSelect').removeClass('errorColor');
		jQuery('#nonLexmarkSuppliesUsedSelect').removeClass('errorColor');
		jQuery('#problemCodeSelect').removeClass('errorColor');
		jQuery('#resolutionCodeSelect').removeClass('errorColor');
		jQuery('#travelDistanceSelect').removeClass('errorColor');
		jQuery('#repairDescriptionInput').removeClass('errorColor');
		jQuery('#deviceConditionSelect').removeClass('errorColor');
		jQuery('#assetListSelect').removeClass('errorColor');
		jQuery('#machineTypeInput').removeClass('errorColor');
		jQuery('#ipAddressInput').removeClass('errorColor');
		jQuery('#macAddressInput').removeClass('errorColor');
	}
	
	jQuery(document).ready(function() {	
		if("${requestsDebriefForm.activity.technician.newContactFlag}" == "true"){
			document.getElementById("technicianLastNameDD").style.display = "";
			document.getElementById("technicianFirstNameDD").style.display = "";
		}
		
		var wrapperWidth=window.innerWidth-144;
		   $(".columns").css({"max-width":wrapperWidth+"px"});
	});	

     portletName = "Request Debrief View";
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
					jQuery('#showProbErrorCode2').html("<input type=\"textarea\" id=\"numericCode2\" onkeyup=\"validateNumericCode();\" \><span id=\"errorMsgNumCode\" class=\"errorColor\" style=\"display: none; color: red;clear:both\"><B></B></span");
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

<script type="text/javascript">
// CHG0002094  starts here
<c:if test="${requestsDebriefForm.showAdditionalPaymentRequestListFlag}">
				var additionalPaymentRequestsGrid = new dhtmlXGridObject('gridAdditionalPaymentRequests');
				additionalPaymentRequestsGrid.setImagePath("<html:imagesPath/>/gridImgs/");
				additionalPaymentRequestsGrid.setHeader(autoAppendPlaceHolder('<spring:message code="claim.headerList.additionalPaymentRequests"/>,',7));
				additionalPaymentRequestsGrid.setColAlign("left,left,left,left,left,left,left");
				additionalPaymentRequestsGrid.setInitWidths("200,100,150,100,100,400,100");
				additionalPaymentRequestsGrid.setColTypes("ro,ro,ro,ro,ro,ro,ro");
				additionalPaymentRequestsGrid.setColSorting("str,na,na,int,na,str,na");
				additionalPaymentRequestsGrid.enableAutoWidth(true);
				additionalPaymentRequestsGrid.enableAutoHeight(true);
				additionalPaymentRequestsGrid.enableMultiline(true);
				additionalPaymentRequestsGrid.enablePaging(true, 5, 6, "pagingAdditionalPayment", true);
				additionalPaymentRequestsGrid.init(); 
				additionalPaymentRequestsGrid.setSkin("light");	
				additionalPaymentRequestsGrid.setPagingSkin("bricks");
				
				jQuery(document).ready(function() {
					additionalPaymentRequestsGrid.loadXMLString("${requestsDebriefForm.additionalPaymentRequestListXML}");
					var numericCodeFlag = true;
					var showProbValidateMsg = false;
				});
				

				function addNewPaymentRequest(){
					callOmnitureAction('Request Close Out View', 'Add New Payment Request');
					var id = additionalPaymentRequestsGrid.getUID();
					var defaultCurrency = "${requestsDebriefForm.activity.partnerAccount.defaultCurrency}";
					var str = '<table><tr><td><select id="'+id+'_paymentType_grid" >'+ getPaymentTypeOptions() +'</select></td></tr></table>,' +
							  '<table><tr><td><input id="'+id+'_qty_grid" size="2" type="text" maxlength="5" onblur="calculateAmount(this &#44;' + id + ')"  onafterpaste="clearNoNumAndPoint(this)" onkeyup="clearNoNumAndPoint(this)"  value=""/></td></tr></table>,' +
							  '<table><tr><td><input id="'+id+'_unitPrice_grid" size="10" maxlength="10" type="text" onblur="calculateAmount(this &#44;' + id + ')" onafterpaste="clearNoNum(this)" onkeyup="clearNoNum(this)"   value=""/></td></tr></table>,' +
							  ''+ ',' +
							  defaultCurrency + ',' +
							  '<table><tr><td><input onblur="validateLength(1 &#44; 250 &#44; this);" id="'+id+'_description_grid" size="50" type="text" /></td></tr></table>,' +
							  '<a href="javascript:deleteRowInPaymentGrid(\''+id+'\');"><img class="ui-icon trash-icon" src="<html:imagesPath/>transparent.png"></a><input id="'+id+'_paymentRequestUpdateFlag" type="hidden" value="false" /><input id="'+id+'_isNew" type="hidden" value="false" />';
					additionalPaymentRequestsGrid.addRow(id,str,0);
					additionalPaymentRequestsGrid.changePage(1);				
				}
	
				function getPaymentTypeOptions(){
					var options = '<option value=""></option><c:forEach items="${requestsDebriefForm.paymentTypes}" var="type" varStatus="loop"><option value="${type.key}">${type.value}</option></c:forEach>';
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
							
							if(inputType.trim() == '' || inputType.trim() == '&nbsp;'){
								currentResult = false;
								errMsg += '<spring:message code="claim.errorMsg.additionalPayment.paymentTypeNotNull"/>' + '; ';
							}
							if(inputQty.trim() == '' || inputQty.trim() == '&nbsp;'){
								currentResult = false;
								errMsg += '<spring:message code="claim.errorMsg.additionalPayment.qtyNotNull"/>' + '; ';
							}
							if(inputUnitPrice.trim() == '' || inputUnitPrice.trim() == '&nbsp;'){
								currentResult = false;
								errMsg += '<spring:message code="claim.errorMsg.additionalPayment.unitPrice"/>' + '; ';
							}

							if(inputDescription.trim() == '' || inputDescription.trim() == '&nbsp;'){
								currentResult = false;
								errMsg += '<spring:message code="claim.errorMsg.additionalPayment.description"/>' + '; ';
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
				</c:if>
				// CHG0002094  ends here, the place for this script is changed as the grid was not getting loaded
			</script>
			<script type="text/javascript">
				
				partsAndToolsDebriefGrid = new dhtmlXGridObject('gridCCVPartDebrief1');
				partsAndToolsDebriefGrid.setImagePath("<html:imagesPath/>gridImgs/");
				partsAndToolsDebriefGrid.setHeader(autoAppendPlaceHolder(autoApplyRequiredStyle('<spring:message code="request.headerList.partsAndTools.debrief.requestDebrief"/>'),10));
				partsAndToolsDebriefGrid.setColAlign("left,left,left,left,left,left,left,left,left,left,left");
				partsAndToolsDebriefGrid.setInitWidths("50,70,100,110,110,140,140,100,130,150,*");
				partsAndToolsDebriefGrid.setColTypes("ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro");
				partsAndToolsDebriefGrid.setColSorting("na,str,str,na,na,na,na,str,na,na,na");
				partsAndToolsDebriefGrid.enableAutoWidth(true);
				partsAndToolsDebriefGrid.enableAutoHeight(true);
				partsAndToolsDebriefGrid.enableMultiline(true);
				partsAndToolsDebriefGrid.init(); 
				partsAndToolsDebriefGrid.prftInit();
				partsAndToolsDebriefGrid.enablePaging(true, 9999, 6, "pagingPartDebrief", true);
				partsAndToolsDebriefGrid.setSkin("light");
				partsAndToolsDebriefGrid.enableColumnMove(true);
				partsAndToolsDebriefGrid.setColumnHidden(4,true);
				partsAndToolsDebriefGrid.setColumnHidden(5,true);
				partsAndToolsDebriefGrid.setPagingSkin("bricks");
				partsAndToolsDebriefGrid.attachEvent("onMouseOver", function(id,ind){		
					return false;
				});
				// changes for db saving and retrieving	start						
				
				<c:forEach var="gridSettingVar" items="${gridSettingList}" varStatus="status">
				<c:if test="${gridSettingVar.gridId == 'gridCCVPartDebrief1'}">
				var colsOrder = "${gridSettingVar.colsOrder}";
				partsAndToolsDebriefGrid.loadOrder(colsOrder);									
				</c:if>
				</c:forEach>
				partsAndToolsDebriefGrid.attachEvent("onAfterCMove",function(a,b){
					partsAndToolsDebriefGrid.enableAutoSavingToDB("${saveCustomizedGridSettingURL}");								
				});
				
				// changes for db saving and retrieving	end
				
				function showMessageForQty(){
					if(checkTotalQTY())
						document.getElementById("qtyValidate").innerHTML = "<spring:message code='claim.message.qtyIsGreaterThan5'/>";
					else 
						document.getElementById("qtyValidate").innerHTML = "";
				}
				
				function checkTotalQTY(){
					var debriefRowNumber = partsAndToolsDebriefGrid.getRowsNum();
					var additionalRowNumber = "${requestsDebriefForm.activity.partnerAccount.orderPartsDebriefFlag}"=="true"?additionalPartsGrid.getRowsNum():0;
					if(debriefRowNumber+additionalRowNumber>5){
						return true;
					}
					return false;
				}

				function loadPartDebrief(partDebrief){
					var id = partsAndToolsDebriefGrid.getUID();
						var str =[partDebrief[5],partDebrief[0],partDebrief[1]
						,'<table><tr><td><select onblur="setPartLineItemUpdateFlag(this &#44; \''+id+'\');" onfocus="setTempData(this);" id="partStatus_'+id+'" onchange=\"selectStatus(this &#44;\''+id+'\');\"><option value=""></option>'+generateOptionAndSetValue(partStatus,partStatusValue,partDebrief[6])+'</select></td></tr></table>'
						,'<table><tr><td><select onChange="retrieveErrorCode2(\''+id+'\');" onblur="setPartLineItemUpdateFlag(this &#44; \''+id+'\');" onfocus="setTempData(this);" style="width:100px;display:block;" id="errorCode1_'+id+'"><option value=""></option>'+generateOptionAndSetValue(errorCode1,errorCode1Value,partDebrief[7])+'</select></td></tr></table>'
						,'<table><tr><td><label id="errorCode2Loading_'+id+'" style="display: none;"><img  src = "<html:imagesPath/>loading-icon.gif"; />&nbsp;<spring:message code="label.loadingNotification" /></label><select onblur="setPartLineItemUpdateFlag(this &#44; \''+id+'\');" onfocus="setTempData(this);" style="width:130px;display:block;" id="errorCode2_'+id+'"></select></td></tr></table>'
						,'<table><tr><td><select style="width:100px;" id="source_'+id+'"><option value=""></option>'+generateOptionAndSetValue(source,sourceValue,partDebrief[14])+'</select></td></tr></table>'
						,(partDebrief[2]=="true"?'<spring:message code="claim.lebel.returnRequired.yes"/>':'<spring:message code="claim.lebel.returnRequired.no"/>')
						,((partDebrief[2]=="false")?'':'<table><tr><td><select onblur="setPartLineItemUpdateFlag(this &#44; \''+id+'\');" onfocus="setTempData(this);" id="reCarrier_'+id+'"><option value=""></option>'+generateOptionAndSetValue(carrier,carrierValue,partDebrief[9])+'</select></td></tr></table>')
						,((partDebrief[2]=="false")?'':'<table><tr><td><input onblur="validateLength(1 &#44; 30 &#44; this);setPartLineItemUpdateFlag(this &#44; \''+id+'\');" onfocus="setTempData(this);" id="reTracingNum_'+id+'" style="width:110px;" type="text" value="'+partDebrief[10]+'"/></td></tr></table>')
						,'<table><tr><td><input id="partDebriefId_'+id+'" type="hidden" value="'+partDebrief[11]+'"/> <input id="partId_'+id+'" type="hidden" value="'+partDebrief[12]+'"/> <input id="partLineItemUpdateFlag_'+id+'" type="hidden" value="'+partDebrief[13]+'"/></td></tr></table>'];
					partsAndToolsDebriefGrid.addRow(id,str,0);
					retrieveErrorCode2ForExsit(id,partDebrief[7],partDebrief[8]);
					selectStatus(document.getElementById("partStatus_"+id),id);
				}
				
				var tempData;
				function setTempData(ele){
					tempData = ele.value;
				}
				
				function selectStatus(ele,ind){
					if(ele.value=="" || partStatusValue[ele.value].toLowerCase() != "used - proactive replacement" && partStatusValue[ele.value].toLowerCase() != "used - required for repair"){
						document.getElementById("errorCode1_"+ind).style.display = "none";
						document.getElementById("errorCode2_"+ind).style.display = "none";
						document.getElementById('source_'+ind).style.display = "none";
					}else{
						document.getElementById("errorCode1_"+ind).style.display = "block";
						if(document.getElementById("errorCode1_"+ind) && document.getElementById("errorCode1_"+ind).value != ""){
							document.getElementById("errorCode2_"+ind).style.display = "block";
						}
						document.getElementById('source_'+ind).style.display = "block";
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
	
				function setPartLineItemUpdateFlag(ele, rowId){
					var currentData = ele.value;
					if(tempData != currentData){
						if(document.getElementById("partLineItemUpdateFlag_"+rowId)){
							document.getElementById("partLineItemUpdateFlag_"+rowId).value = "true";
						}
					}
				}
				
				jQuery(document).ready(function() {
					<c:forEach items="${requestsDebriefForm.activity.debrief.partDebriefList}" var="partDebrief" varStatus = "status" >
						var partDebrief = new Array();
						partDebrief[0] = "${partDebrief.partNumber}";
						partDebrief[1] = "${partDebrief.partName}";
						partDebrief[2] = "${partDebrief.returnRequiredFlag}";
						partDebrief[3] = "${partDebrief.replacementPartNumber}";
						partDebrief[4] = ("${partDebrief.requestedDate}"=="null"||"${partDebrief.requestedDate}"=="")?"":localizeFormatDate(new Date("${partDebrief.requestedDate}"));
						partDebrief[5] = "${partDebrief.quantity}";
						partDebrief[6] = "${partDebrief.partDisposition.value}";
						partDebrief[7] = "${partDebrief.errorCode1.value}";
						partDebrief[8] = "${partDebrief.errorCode2.value}";
						partDebrief[9] = "${partDebrief.carrier.value}";
						partDebrief[10] = "${partDebrief.trackingNumber}";
						partDebrief[11] = "${partDebrief.partLineItemId}";
						partDebrief[12] = "${partDebrief.partId}";
						partDebrief[13] = "${partDebrief.partLineItemUpdateFlag}";
						partDebrief[14] = "$(partDebrief.partSource.value)";
						loadPartDebrief(partDebrief);
					</c:forEach>
					isPageLoaded = true;
					gridRDBrfVNoteGrid.loadXMLString("${requestsDebriefForm.activityNoteListXML}");
					partsAndToolsDebriefGrid.loadXMLString("<?xml version=\"1.0\" ?><rows></rows>");
				});
				
				function generatePartAndTool(){
					document.getElementById("partToolContent").innerHTML="";
					var rows = partsAndToolsDebriefGrid.getRowsNum();
					
					for(var i = 0 ;i < rows ;i++){
						var ind = partsAndToolsDebriefGrid.getRowId(i);
						if(document.getElementById("qty_"+ind)){
							var qtyValue = document.getElementById("qty_"+ind).value;
						}else{
							var qtyValue = partsAndToolsDebriefGrid.cellByIndex(i,0).cell.innerHTML;
						}
						var reRequire = partsAndToolsDebriefGrid.cellByIndex(i,7).cell.innerHTML;
						var statusValueKey = document.getElementById("partStatus_"+ind).value;
						var statusValue = partStatusValue[statusValueKey];
						if(statusValue.toLowerCase() == "used - proactive replacement" || statusValue.toLowerCase() == "used - required for repair" ){
							var errorCode1Key = document.getElementById("errorCode1_"+ind).value;
							var errorCode2 = document.getElementById("errorCode2_"+ind).value;
							var errorCode1 = errorCode1Value[errorCode1Key];
							var sourceKey = document.getElementById("source_"+ind).value;
							var source = "";
							if (sourceKey != "") source = sourceValue[sourceKey];
							
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

							var input = document.createElement("input");
							input.id = "activity.debrief.partDebriefList["+i+"].partSource.value"; 
							input.name = "activity.debrief.partDebriefList["+i+"].partSource.value"; 
							input.value = source;
							document.getElementById("partToolContent").appendChild(input);
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
							var reTracingNum = document.getElementById("reTracingNum_"+ind).value;
							
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
						input.value = partsAndToolsDebriefGrid.cellByIndex(i,1).cell.innerHTML;
						document.getElementById("partToolContent").appendChild(input);

						var input = document.createElement("input");
						input.id = "activity.debrief.partDebriefList["+i+"].partName"; 
						input.name = "activity.debrief.partDebriefList["+i+"].partName"; 
						input.value = partsAndToolsDebriefGrid.cellByIndex(i,2).cell.innerHTML;
						document.getElementById("partToolContent").appendChild(input);
					}
					if("${requestsDebriefForm.activity.partnerAccount.orderPartsDebriefFlag}"=="true"){
						var additionalPartsRows = additionalPartsGrid.getRowsNum();
						for(var i = 0 ;i < additionalPartsRows ;i++){
							var listIndex = rows+i;
							var ind = additionalPartsGrid.getRowId(i);
							var qtyValue = 1;
							var reRequire = additionalPartsGrid.cellByIndex(i,8).cell.innerHTML;
							var statusValueKey = document.getElementById("partStatus_"+ind).value;
							var statusValue = partStatusValue[statusValueKey];
							if(statusValue.toLowerCase() == "used - proactive replacement" || statusValue.toLowerCase() == "used - required for repair"){
								var errorCode1Key = document.getElementById("errorCode1_"+ind).value;
								var errorCode2 = document.getElementById("errorCode2_"+ind).value;
								var errorCode1 = errorCode1Value[errorCode1Key];
								var sourceKey = document.getElementById("source_"+ind).value;
								var source = "";
								if (sourceKey != "") source = sourceValue[sourceKey];
								
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

								var input = document.createElement("input");
								input.id = "activity.debrief.partDebriefList["+listIndex+"].partSource.value"; 
								input.name = "activity.debrief.partDebriefList["+listIndex+"].partSource.value"; 
								input.value = source;
								document.getElementById("partToolContent").appendChild(input);
							}
							
							if('<spring:message code="claim.lebel.returnRequired.yes"/>'==reRequire){
								var reCarrierIndex = document.getElementById("reCarrier_"+ind).value;
								var reCarrier = carrierValue[reCarrierIndex];
								var reTracingNum = document.getElementById("reTracingNum_"+ind).value;
								
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
				
				function validatePartAndTool(){
					var result = true;
					var debriefRowNumber = partsAndToolsDebriefGrid.getRowsNum();
					var additionalRowNumber = "${requestsDebriefForm.activity.partnerAccount.orderPartsDebriefFlag}"=="true"?additionalPartsGrid.getRowsNum():0;
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
				
				function validateShipToAddress(){
					var result = true;
					if("${requestsDebriefForm.activity.partnerAccount.orderPartsDebriefFlag}"=="true" && "${requestsDebriefForm.activity.addressStatus}"!="${requestsDebriefForm.addressStatusList[0]}" && additionalPartsGrid.getRowsNum()!=0 && document.getElementById("activity.shipToAddress.addressId").value==""){
						result = false;
						callOmnitureFieldErrorAction('Ship To Address');
						showError("<spring:message code='claim.label.noAddressSelected'/>", null, true);
					}
					return result;
				}
			</script>