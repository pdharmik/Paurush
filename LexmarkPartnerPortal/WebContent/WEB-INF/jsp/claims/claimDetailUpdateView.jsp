<%@ include file="/WEB-INF/jsp/include.jsp"%>
<%@ include file="/WEB-INF/jsp/includeGrid.jsp"%>
<%@ include file="/WEB-INF/jsp/dateUtil.jsp"%>
<%@ include file="/WEB-INF/jsp/includeDatePicker.jsp"%>
<%@ include file="/WEB-INF/jsp/noBackToThisPage.jsp" %>

<style type="text/css"><%@ include file="/WEB-INF/css/ci6.css" %></style>
<!-- Added for CI Defect # 11682 -->
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/mps.css?version=<html:fileCacheVersion/>" />
<!--[if IE 7]>
	<style type="text/css"><%@ include file="/WEB-INF/css/mps_ie7.css" %></style>
<![endif]-->
<script src='<html:rootPath/>js/trackingNumber.js'></script>
<script type="text/css">
.status-block{
overflow:auto!important;
}
	.columns {
   		min-width: 1024px;
    	overflow-x: auto;
		overflow-y: auto !important;
    	padding: 15px 0;
    	width: 95%;
		}
</script>
<style type="text/css">
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
	width: 100px;
}
input:focus,input.ieFocusHack,input.focus,.form input:focus,.form input.ieFocusHack,.form textarea:focus,.form textarea.ieFocusHack,.form select:focus,.form select.ieFocusHack,.displayGrid input:focus,.displayGrid input.ieFocusHack
{
	background-color:lightyellow !important;border:2px #FC3 solid !important;padding:3px 4px !important;
}

</style>
<!-- Added for CI Defect # 11682 -->
<style>
.ie7 .button, .ie7 .button_cancel {
behavior: url(/LexmarkPartnerPortal/WebContent/css/PIE.htc) !important;
}
.ie8 .button, .ie8 .button_cancel {
behavior: url(/LexmarkPartnerPortal/WebContent/css/PIE.htc) !important;
}
.ie7 #gridCCVPartDefaultPaging>div { width:100%!important }
</style>
<portlet:renderURL  var="requestsListViewUrl">
</portlet:renderURL>

<portlet:actionURL  var="cancelUpdate">
    <portlet:param name="action" value="cancelClaimUpdate" />
</portlet:actionURL>

<portlet:actionURL var="submitClaimUpdate">
	<portlet:param name="action" value="submitClaimUpdate"/>
</portlet:actionURL>

<portlet:renderURL  var="updateNoteUrl" windowState='<%= LiferayWindowState.POP_UP.toString() %>'>
	<portlet:param name="action" value="showUpdateNotePopup" />
</portlet:renderURL>

<portlet:renderURL  var="requestListUrl">
    <portlet:param name="action" value="" />
</portlet:renderURL>

<portlet:actionURL var="addAttachmentsCreate">
	<portlet:param name="action" value="addAttachmentsCreate"/>
</portlet:actionURL>

<portlet:actionURL var="removeAttachmentURL">
	<portlet:param name="action" value="removeAttachmentActionClaimUpdate"/>
</portlet:actionURL>

<script type="text/javascript">
//trimm spaces 
var exchangeflag="${claimDetailFormForUpdate.claimDetail.exchangeFlag}";
function trim(strValue){
	var trimValue = strValue.replace(/\s/g,"");
	return trimValue;	
}


</script>


<script type="text/javascript">
	var isCDtlServiceHistoryLoaded = false;
	function expandCollapesGrid(gridId){
        var isDisplay = document.getElementById(gridId).style.display;
        var showImg = document.getElementById("img_"+gridId);
    	if(isDisplay=='none'){
    		callOmnitureAction('Clam Update', 'Service Request Drilldown Collapse Grid');
    		document.getElementById(gridId).style.display = '';
    		$('#img_'+gridId).removeClass('expand-icon');
    		$('#img_'+gridId).addClass('minimize-icon'); 
    		if(!isCDtlServiceHistoryLoaded){
    			gridCDtlVSHRequestGrid.loadXML("${downServiceHistoryURL}&timezoneOffset=" + timezoneOffset);
    			isCDtlServiceHistoryLoaded = true;
        	}
        }else{
    		callOmnitureAction('Clam Update', 'Service Request Drilldown Expand Grid');
        	document.getElementById(gridId).style.display = 'none';
    		$('#img_'+gridId).addClass('expand-icon');
    		$('#img_'+gridId).removeClass('minimize-icon');
		}
	}
</script>

<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/in-styles.css?version=<html:fileCacheVersion/>" />
<table width="100%">
	<tr>
		<td>
			
				<div class="main-wrap" >
					<div class="content">
					<form:form id="claimUpdateForm" commandName="claimDetailFormForUpdate" method="post" action="${submitClaimUpdate}" onkeydown="if(event.keyCode==13){return event.srcElement.type == 'textarea';}">
						<form:hidden path="timezoneOffset"/>
						
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
										<div class="first-column">
											<table  border="0">
												<tr>
													<td width="150px" align="right"><b><spring:message code="claim.label.claimNumber"/></b></td>
													<td width="10px"></td>
													<td>${claimDetailFormForUpdate.claimDetail.serviceRequest.serviceRequestNumber}</td>
												</tr>
												<tr>
													<td align="right"><b><spring:message code="claim.label.claimStatus"/></b></td>
													<td></td>
													<td>${claimDetailFormForUpdate.claimDetail.activityStatus.name}</td>
												</tr>
												<tr>
													<td align="right"><b><spring:message code="claim.label.openedDate"/></b></td>
													<td></td>
													<td><util:dateFormat value="${claimDetailFormForUpdate.claimDetail.serviceRequest.serviceRequestDate}" timezoneOffset="${timezoneOffset}" /></td>
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
													<td  class="labelTD" align="center"><h3>${claimDetailFormForUpdate.claimDetail.serviceRequest.asset.productLine}</h3>
												    </td>
												</tr>
												<tr>
												  	<td align="center"><img width="75" src="${claimDetailFormForUpdate.claimDetail.serviceRequest.asset.productImageURL}"></td>
												</tr>
											</table>
										</div>
										<div class="second-column">
											<br/>
											<br/>
											<table border="0">
												<tr>
												 	<td width="150px" align="right"></td>
													<td width="10px" ></td>
												  	<td class="labelTD"></td>
												</tr>
												<tr>
												 	<td width="150px" align="right"><b><spring:message code="claim.label.serialNumber"/></b></td>
													<td width="10px" ></td>
												  	<td class="labelTD">${claimDetailFormForUpdate.claimDetail.serviceRequest.asset.serialNumber}</td>
												</tr>	 
												<tr>
													<td class="labelTD" align="right"><b><spring:message code="claim.label.machineTypeModel"/></b></td>
													<td></td>
													<td class="labelTD">${claimDetailFormForUpdate.claimDetail.serviceRequest.asset.modelNumber}</td>
												</tr>
												<tr>
													<td class="labelTD" align="right"><b><spring:message code="claim.label.productPN/TLI"/></b></td>
													<td></td>
													<td class="labelTD">${claimDetailFormForUpdate.claimDetail.serviceRequest.asset.productTLI}</td>
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
				         	   	 
						<div>
							<label class="orangeSectionTitles cursor-pointer" onclick="return false;">	
				           		<img id="img_divCDtlServiceHistory" onClick="expandCollapesGrid('divCDtlServiceHistory');" class="ui-icon expand-icon" src="<html:imagesPath/>transparent.png" ><spring:message code="claim.label.viewServiceHistory"/></label>	
				       	</div>
				        
				        <!-- Service History Start -->   	 
						<div id="divCDtlServiceHistory" class="portlet-wrap" style="display:none;">
				 			<div class="portlet-header dark-bar">
				 			<table class="displayGrid">
              <thead>
                <tr><th><spring:message code="claim.label.serviceHistory"/></th>
                </tr></thead>
              
            </table>
				        		
				        	</div>
				        	<div class="portlet-wrap-inner">
					        	<div class="width-100per">
					       			<div class="columns two">
										<div id="gridCDtlVSHRequest"  class="width-100per"></div>
										<div id="CDtlVSHRequestloadingGrid" class='gridLoading'>
											<br>
											<!--spring:message code="label.loadingNotification"/-->&nbsp;&nbsp;<img src="<html:imagesPath/>gridloading.gif" /><br>
										</div>
										<div><span id="gridCDtlVSHRequestPagingArea"></span>&nbsp;<span id="gridCDtlVSHRequestRecinfoArea"></span></div><!-- mygrid_container -->
									</div>
								</div>
							</div>
				       	 	<div class="portlet-footer">
				       	 		<div class="portlet-footer-inner"></div>
				       	 	</div>
						</div>
						
						<script type="text/javascript">
							gridCDtlVSHRequestGrid = new dhtmlXGridObject('gridCDtlVSHRequest');
							gridCDtlVSHRequestGrid.setImagePath("<html:imagesPath/>gridImgs/");
							gridCDtlVSHRequestGrid.setHeader(autoAppendPlaceHolder('<spring:message code="claim.headerList.serviceHistory"/>',7));
							gridCDtlVSHRequestGrid.setColAlign("left,left,left,left,left,left,left");
							gridCDtlVSHRequestGrid.setColTypes("ro,ro,ro,ro,ro,ro,ro");
							gridCDtlVSHRequestGrid.setColSorting("str,str,str,str,str,str,str");
							gridCDtlVSHRequestGrid.enableAutoWidth(true);
							gridCDtlVSHRequestGrid.enableAutoHeight(true);
							gridCDtlVSHRequestGrid.enableMultiline(true);
							gridCDtlVSHRequestGrid.enablePaging(true, 5, 10, "gridCDtlVSHRequestPagingArea", true, "gridCDtlVSHRequestRecinfoArea");
							gridCDtlVSHRequestGrid.init();
							gridCDtlVSHRequestGrid.setSkin("light");
							gridCDtlVSHRequestGrid.setPagingSkin("bricks");	
							gridCDtlVSHRequestGrid.attachEvent("onXLS", function() {
								document.getElementById('CDtlVSHRequestloadingGrid').style.display = 'block';
							});
							gridCDtlVSHRequestGrid.attachEvent("onXLE", function() {
								setTimeout(function(){
						    		rebrandPagination();
						    	
						    	},100);
							    document.getElementById('CDtlVSHRequestloadingGrid').style.display = 'none';
							});
						</script>
						<br/>
				        <!-- Service History End --> 
				       
				        <!-- Customer Information Start --> 
							
						<div class="portlet-wrap">
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
												 	 <td>${claimDetailFormForUpdate.claimDetail.customerAccount.accountName}</td>
											 	</tr>
											  	<tr>
												 	<td align="right" valign="top" rowspan="2"><b><spring:message code="claim.label.address"/></b></td>
												  	<td rowspan="2">
												  		<c:choose>
															<c:when test="${empty claimDetailFormForUpdate.claimDetail.newCustomerAddressCombined}">
																<util:addressOutput value="${claimDetailFormForUpdate.claimDetail.serviceRequest.asset.installAddress}"/>
															</c:when>
															<c:otherwise>
																<util:replaceRN value="${claimDetailFormForUpdate.claimDetail.newCustomerAddressCombined}"/>
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
													<td><label id="primaryContactFirstNameLabel">${claimDetailFormForUpdate.claimDetail.serviceRequest.primaryContact.firstName}</label>&nbsp; <label id="primaryContactLastNameLabel">${claimDetailFormForUpdate.claimDetail.serviceRequest.primaryContact.lastName}</label></td>
											 	</tr>
											  	<tr>
												  	<td align="right"><b><spring:message code="claim.label.phone"/></b></td>
												  	<td width="20px"/></td>
												 	<td class="word-wrap-break-word"><label id="primaryContactPhoneLabel">${claimDetailFormForUpdate.claimDetail.serviceRequest.primaryContact.workPhone}</label></td>
											 	</tr>
											 	<tr>
												 	<td align="right" valign="top"><b><spring:message code="claim.label.email"/></b></td>
												 	<td width="20px"/></td>
												 	<td valign="top" class="word-wrap-break-word"><label id="primaryContactEmailLabel">${claimDetailFormForUpdate.claimDetail.serviceRequest.primaryContact.emailAddress}</label></td>
												</tr>
												<tr>
												 	<td></td>
												 	<td></td>
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
				        <!-- Problem Imformation Start -->  	 	 
						<div class = "portlet-wrap">
							<div class="portlet-header dark-bar">
								<h3><spring:message code="claim.label.problemInformation"/></h3>
							</div>
				       	 	<div class="portlet-wrap-inner">
					       	 	<div class="width-100per">
					       	 		<div class="columns two">
										<dl>
											<dd><b><spring:message code="claim.label.problemCode"/></b></dd>
											<dd>${claimDetailFormForUpdate.claimDetail.actualFailureCode.name}</dd><br/>
											<dd><b><spring:message code="claim.label.problemDetails"/></b></dd>
											<dd>${claimDetailFormForUpdate.claimDetail.serviceRequest.problemDescription}</dd><br/>
											<dd><b><spring:message code="claim.label.serviceProviderReferenceNumber"/></b></dd>
											<dd><input type="text" name="claimDetail.serviceProviderReferenceNumber" value="${claimDetailFormForUpdate.claimDetail.serviceProviderReferenceNumber}" id="serviceProviderReferenceNumber"  onchange="validateLength(0,30,this)"></input></dd><br/>
											  <dd><input type="checkbox" name="claimDetail.requestLexmarkReviewFlag" onclick="showHideReviewComments(this)" name="activity.requestLexmarkReviewFlag" value="true"/><label class="margin-left-10px"><spring:message code="claim.label.requestLexmarkReview"/></label></dd>
                                             <br/><br/>
                                            <div id="reviewCommentsDiv" style="display:none">
                                            <dl>
                                             <dd><label><spring:message code="claim.label.reviewComments"/></label></dd>
                                             <dd><textarea id="reviewCommentsInput" name="claimDetail.reviewComments" class="review-comments-input" onchange="validateLength(0,150,this)">${claimDetailFormForUpdate.claimDetail.reviewComments}</textarea></dd>
                                             </dl>
                                            </div>
										</dl>
									</div>
								</div>
							</div>
							<div class="portlet-footer">
								<div class="portlet-footer-inner"></div>
							</div>
						</div>
				        <!-- Problem Imformation End -->    	 
				        
				        <!-- Technician Information Start -->       	 
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
														<input type="text" id="technicianFirstNameInput" name="claimDetail.technician.firstName" onchange="validateLength(0,50,this)"/>
													</td>
													<td/>
												</tr>
												<tr id="technicianLastNameDD" style="display:none">
													<td align="right">
														<label><b><span class="required">* </span><spring:message code="claim.label.lastName"/></b></label>
													</td>
													<td width="10px" />
													<td align="left">
														<input type="text" id="technicianLastNameInput" name="claimDetail.technician.lastName" onchange="validateLength(0,50,this)"/>
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
								var accountId = '${claimDetailFormForUpdate.claimDetail.partnerAccount.accountId}';
								if(accountId){
									//to ajax technician information use partnerAccount Id
									ajaxTechnicianInformation(accountId);
								}else{
									ajaxTechnicianInformation("");
								}
							 
								 function showHideReviewComments(ele){
									if(ele.checked){
										document.getElementById('reviewCommentsDiv').style.display = 'block';
										document.getElementById('reviewCommentsInput').value = '';
									}
									else{
										document.getElementById('reviewCommentsDiv').style.display = 'none';
										document.getElementById('reviewCommentsInput').value = '';
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
								
								function ajaxTechnicianInformation(accountId){
									//var result = // result from Ajax;
									var url = '<portlet:resourceURL id="setTechnicianInformation"/>';
									url +="&accountId=" + accountId;
									document.getElementById("technicianLoading").style.display = "block";
									doAjax(url, callbackGetTechInfoAccountList, null, null);
								}
								
								function callbackGetTechInfoAccountList(result){
									var mapList = result.data;
									//Alex0, zhou/1-P00910}Alex1, zhou/1-P3YKE1}Alex2, zhou/1-P00912}Alex3, zhou/1-P00913}
									//put the result value into parent page technician choose.
									var valueKey = mapList.split("}");
									var selectObject = document.getElementById('technicianChoose');
									selectObject.options.length=0;
									//clearn all then added
									for (var i=0;i<valueKey.length;i++){
										var textValue = valueKey[i].split("/");
										var optionObject = new Option(textValue[0],valueKey[i]);
										var name = textValue[0].split(",");
										var firstName = '${claimDetailFormForUpdate.claimDetail.technician.firstName}';
										var lastName = '${claimDetailFormForUpdate.claimDetail.technician.lastName}';
										if(textValue[0] == '${claimDetailFormForUpdate.claimDetail.technician.lastName}, ${claimDetailFormForUpdate.claimDetail.technician.firstName}'){
											optionObject.selected = true;
										}
										selectObject.options.add(optionObject);
									}
									selectObject.remove(selectObject.length-1);
									
									selectObject.options.add(new Option('<spring:message code="claim.label.dropdownlist.optionvalue.technician.other"/>','other'));
									document.getElementById("technicianLoading").style.display = "none";
									return true;
								}
				
								function validateTechnicianInfo(){
									var result = true;
									var technician = document.getElementById('technicianChoose').value;
									if(!technician){
										result = false;
										showError("<spring:message code='claim.errorMsg.technicianNotNull'/>",null, true);
									}
									if(document.getElementById("technicianFirstNameDD").style.display != "none"){
										if(!trim(document.getElementById("technicianFirstNameInput").value)){
											result = false;
											showError("<spring:message code='claim.errorMsg.technicianFirstNameNotNull'/>",null, true);
										}
									}
									if(document.getElementById("technicianLastNameDD").style.display != "none"){
										if(!trim(document.getElementById("technicianLastNameInput").value)){
											result = false;
											showError("<spring:message code='claim.errorMsg.technicianLastNameNotNull'/>",null, true);
										}
									}
									return result;
								}
				
							</script>
						<!-- Technician Information End -->  	 
						
						
				        
				        <!-- Parts and Tools Start-->
					    <c:if test="${showRequestedPartsFlag || showOrderPartsFlag || showReturnPartsFlag}"> 
					        <table width="100%">
								<tr>
									<td>
										<div class = "portlet-wrap">
											<!-- added by ragesree -2098 -->
											<div class="portlet-header">
											    <c:if test="${claimDetailFormForUpdate.claimDetail.exchangeFlag=='false'}">
											    	<h3><spring:message code="claim.label.partsAndTools"/></h3>
											    </c:if>
											    <c:if test="${claimDetailFormForUpdate.claimDetail.exchangeFlag=='true'}">
											    	<h3><spring:message code="claim.label.deviceToBeExchanged"/></h3>
											    </c:if>
												
											</div>
								       	 	<div class="portlet-wrap-inner">
								       	 		<div >
								       	 		
								       	 		<!--  Requested Parts Start--> 
									       	 		<c:if test="${showRequestedPartsFlag}">
									       	 			<div class="status-wrap">
										         	 		<div class="status-block overflow-auto-important">
										         	 			<h4><spring:message code="claim.label.requestedParts"/></h4>
																<div id="gridCDtlVRPRequest" class="width-100per"></div>
																<div><span id="gridCDtlVRPRequestPagingArea"></span>&nbsp;<span id="gridCDtlVRPRequestRecinfoArea"></span></div><!-- mygrid_container -->
															</div>
														</div>
														<script type="text/javascript">
															jQuery(document).ready(function() {
																gridCDtlVRPRequestGrid = new dhtmlXGridObject('gridCDtlVRPRequest');
																gridCDtlVRPRequestGrid.setImagePath("<html:imagesPath/>gridImgs/");
																setHeaderForExchange(); //added by ragesree -2098
																//gridCDtlVRPRequestGrid.setHeader(autoAppendPlaceHolder('<spring:message code="claim.headerList.partsAndTools.detail"/>',5));
																//Changed for CI7 BRD 14-07-06
																gridCDtlVRPRequestGrid.setColAlign("left,left,left,left,left,left");
																gridCDtlVRPRequestGrid.setColTypes("ro,ro,ro,ro,ro,ro");
																gridCDtlVRPRequestGrid.setColSorting("str,str,str,str,str,str");
																gridCDtlVRPRequestGrid.setInitWidths("140,140,140,140,140,*");
																gridCDtlVRPRequestGrid.enableAutoWidth(true);
																gridCDtlVRPRequestGrid.enableAutoHeight(true);
																gridCDtlVRPRequestGrid.enableMultiline(true);
																gridCDtlVRPRequestGrid.enablePaging(true, 5, 10, "gridCDtlVRPRequestPagingArea", true, "gridCDtlVRPRequestRecinfoArea");
																gridCDtlVRPRequestGrid.init();
																gridCDtlVRPRequestGrid.setSkin("light");
																// move enabled for CI 14.10 CRM-DAmster201408041344
																gridCDtlVRPRequestGrid.enableColumnMove(true);
																gridCDtlVRPRequestGrid.setPagingSkin("bricks");		
																gridCDtlVRPRequestGrid.loadXMLString("${claimDetailFormForUpdate.pendingPartListXML}");

																// changes for db saving and retrieving	start
																//gridCDtlVRPRequestGrid.enableAutoSavingToDB("${saveCustomizedGridSettingURL}");
																gridCDtlVRPRequestGrid.attachEvent("onAfterCMove",function(a,b){																	
																	gridCDtlVRPRequestGrid.enableAutoSavingToDB("${saveCustomizedGridSettingURL}");
																	//});	
																});	
																<c:forEach var="gridSettingVar" items="${gridSettingList}" varStatus="status">
																<c:if test="${gridSettingVar.gridId == 'gridCDtlVRPRequest'}">					
																var colsOrder = "${gridSettingVar.colsOrder}";
																gridCDtlVRPRequestGrid.loadOrder(colsOrder);
																
																</c:if>
																</c:forEach>
																// changes for db saving and retrieving	end
																
															});
															
															//added by ragesree -2098
															function setHeaderForExchange(){
																if(exchangeflag==true){
																	gridCDtlVRPRequestGrid.setHeader(autoAppendPlaceHolder('<spring:message code="claim.headerList.deviceExchange.detail"/>',6));
																}else{
																	gridCDtlVRPRequestGrid.setHeader(autoAppendPlaceHolder('<spring:message code="claim.headerList.partsAndTools.detail"/>',6));
																}
															}
														</script>
													</c:if>
												<!--  Requested Parts End--> 	
												
												<!--  Ordered Parts Start--> 
													<c:if test="${showOrderPartsFlag}">
														<div id = include> 
															<jsp:include page="/WEB-INF/jsp/claims/orderedParts.jsp" flush="true"/>
														</div>
													</c:if>
													<script type="text/javascript">
													function carrierOnChange(i){
														var carrier = document.getElementById("carrier_"+i).value;
														if("" == carrier){// select value is null
															document.getElementById("partLineItemUpdateFlag_"+i).value = false;
															document.getElementById("carrierFlag_"+i).value = false;
														}else{
															document.getElementById("carrierFlag_"+i).value = true;
															if(document.getElementById("tackingNumberFlag_"+i).value == 'true'){
																document.getElementById("partLineItemUpdateFlag_"+i).value = true;
															}
														}
													}
								
													function trackingNumberOnChange(i){
														var trackingNumber = document.getElementById("trackingNumber_"+i).value;
														if("" == trackingNumber){// input value is null
															document.getElementById("partLineItemUpdateFlag_"+i).value = false;
															document.getElementById("tackingNumberFlag_"+i).value = false;
														}else{
															document.getElementById("tackingNumberFlag_"+i).value = true;
															if(document.getElementById("carrierFlag_"+i).value == 'true'){
																document.getElementById("partLineItemUpdateFlag_"+i).value = true;
															}
														}
													}
													</script>
												<!--  Ordered Parts End--> 
												
												<!--  Parts to Be Returned Start--> 	
													<c:if test="${showReturnPartsFlag}">
														<div class="status-wrap">
										         	 		<div class="status-block overflow-auto-important" >
										         	 			<!-- added by ragesree -2098 -->
										         	 		    <c:if test="${claimDetailFormForUpdate.claimDetail.exchangeFlag=='false'}">
										         	 		    	<h4><spring:message code="claim.label.parts.to.be.returned"/></h4>
										         	 		    </c:if>
										         	 			 <c:if test="${claimDetailFormForUpdate.claimDetail.exchangeFlag=='true'}">
										         	 		    	<h4><spring:message code="claim.label.deviceToBeReturned"/></h4>
										         	 		    </c:if>
																<div id="gridCDtlVBRPRequest"  class="gridCDtlVBRPRequest"></div>
																<div><span id="gridCDtlVBRPRequestPagingArea"></span>&nbsp;<span id="gridCDtlVBRPRequestRecinfoArea"></span></div><!-- mygrid_container -->
															</div>
														</div>
														<script type="text/javascript">
															jQuery(document).ready(function() {
																gridCDtlVBRPRequestGrid = new dhtmlXGridObject('gridCDtlVBRPRequest');
																gridCDtlVBRPRequestGrid.setImagePath("<html:imagesPath/>gridImgs/");
																setHeaderForPartsToBeReturned(); //added by ragesree -2098
																//gridCDtlVBRPRequestGrid.setHeader(autoAppendPlaceHolder(' ,<spring:message code="claim.headerList.partsToBeReturned"/>',9));
																gridCDtlVBRPRequestGrid.setColAlign("left,left,left,left,left,left,left,left,left");
																gridCDtlVBRPRequestGrid.setColTypes("sub_row,ro,ro,ro,ro,ro,ro,ro,ro");
																gridCDtlVBRPRequestGrid.setColSorting("na,str,str,str,str,str,str,str,str");
																gridCDtlVBRPRequestGrid.setLockColVisibility("false,false,false,false,false,false,false,false,false");
																gridCDtlVBRPRequestGrid.setInitWidths("20,50,140,140,140,140,140,140,190");
																//gridCDtlVBRPRequestGrid.enableAutoWidth(true);
																gridCDtlVBRPRequestGrid.enableAutoHeight(true);
																gridCDtlVBRPRequestGrid.enableMultiline(true);
																gridCDtlVBRPRequestGrid.enablePaging(true, 5, 10, "gridCDtlVBRPRequestPagingArea", true, "gridCDtlVBRPRequestRecinfoArea");
																gridCDtlVBRPRequestGrid.init();
																gridCDtlVBRPRequestGrid.setSkin("light");
																gridCDtlVBRPRequestGrid.setPagingSkin("bricks");
																// move enabled for CI 14.10 CRM-DAmster201408041344
																gridCDtlVBRPRequestGrid.enableColumnMove(true);	
																gridCDtlVBRPRequestGrid.loadXMLString("${claimDetailFormForUpdate.returnPartListXML}");	
																gridCDtlVBRPRequestGrid.attachEvent("onMouseOver",function(b,a){return false;});//added for AIR # LEX:AIR00070627

																// changes for db saving and retrieving	start
																//gridCDtlVBRPRequestGrid.enableAutoSavingToDB("${saveCustomizedGridSettingURL}");
																gridCDtlVBRPRequestGrid.attachEvent("onAfterCMove",function(a,b){																
																	gridCDtlVBRPRequestGrid.enableAutoSavingToDB("${saveCustomizedGridSettingURL}");
																		
																});	
																<c:forEach var="gridSettingVar" items="${gridSettingList}" varStatus="status">
																<c:if test="${gridSettingVar.gridId == 'gridCDtlVBRPRequest'}">					
																var colsOrder = "${gridSettingVar.colsOrder}";
																gridCDtlVBRPRequestGrid.loadOrder(colsOrder);
																
																</c:if>
																</c:forEach>
																// changes for db saving and retrieving	end
																
															});
															//added by ragesree -2098
															function setHeaderForPartsToBeReturned(){
																if(exchangeflag==true){
																	gridCDtlVBRPRequestGrid.setHeader(autoAppendPlaceHolder(' ,<spring:message code="claim.headerList.deviceToBeReturned"/>',9));
																}else{
																	gridCDtlVBRPRequestGrid.setHeader(autoAppendPlaceHolder(' ,<spring:message code="claim.headerList.partsToBeReturned"/>',9));
																}
															}
														</script>
													</c:if>
												<!--  Parts to Be Returned End--> 	
													
												</div>			
											</div>
											<div class="portlet-footer">
												<div class="portlet-footer-inner"></div>
											</div>
										</div>
									</td>
								</tr>
							</table>  
						</c:if>
				        <!-- exchange condition added by ragesree -2098 -->
				        <c:if test="${claimDetailFormForUpdate.claimDetail.partnerAccount.orderPartsFlag && claimDetailFormForUpdate.claimDetail.exchangeFlag=='false' && claimDetailFormForUpdate.claimDetail.serviceType!='Maintenance Kit Install'}">
				        <!-- Order Parts and Tools start  -->
				        <table width="100%">
							<tr>
								<td>
							        <div class = "portlet-wrap">
							        	<input type="hidden" id = "partnerAccountOrganizationIdHidden" value="${claimDetailFormForUpdate.claimDetail.partnerAccount.organizationID}"></input>
										<div class="portlet-header dark-bar">
											<h3><spring:message code="claim.label.orderPartsAndTools"/></h3>
										</div>
							       	 	<div class="portlet-wrap-inner">
							       	 		<div class="width-100per">
							       	 			<br>
							       	 			
													<table>
														<tr>
															<td>
																<b><label><spring:message code="claim.label.partNumberAdd"/>:</label></b>
															</td>
														</tr>
														<tr>
															<td>
																<input class="text" type="text" id="partNumber"/>&nbsp;&nbsp;
																<a href="javascript:searchPart(document.getElementById('partNumber').value);" class="button" id="partSearchButton"><spring:message code="claim.button.addPart"/></a>&nbsp;&nbsp;
																<span id="showPartsListSpan"><a id="showPartsListLink" href="javascript:void(0);" onclick="showPartList();"><spring:message code="claim.label.partsList"/></a></span>
															</td>
															<td>
																<label id="partSearchLoading" style="display: none;">
																&nbsp;&nbsp;&nbsp;<img  src = "<html:imagesPath/>loading-icon.gif"; />&nbsp;<spring:message code="label.searchingNotification" />
																</label>
															</td>
														</tr>
													</table>
												
												<br>
												<div id="qtyValidate" class="qty-validate"></div>
												<div id="contentPart" style="display:none"></div>
												<div class="scroll">
													<div id="gridCCVPartDefault" ></div>
													<div id="gridCCVPartDefaultPaging" ></div>
												</div>       	 		
							       	 		</div>
							       	 		<!-- Ship to Address Start -->
							       	 		<div id="partnerAddressDiv">
											<table>
												<tr>
													<td valign="top" align="left" width="85px">
														&nbsp;&nbsp;&nbsp;&nbsp;<b><spring:message code="claim.label.shipTo"/></b>
													</td>
													<td width="20px" rowspan="3">&nbsp;</td>
								 	  				<td rowspan="2">
														<util:addressOutput value="${claimDetailFormForUpdate.claimDetail.shipToAddress}"/>
												  	</td>
												</tr>
											</table>
											</div>
											
											<!-- CI-6 Added Start for Expedite-->
											<div id="expediteDiv" class="scroll">
												<table>
													<tr>
														<td valign="top" align="left" width="85px">
															&nbsp;&nbsp;&nbsp;&nbsp;<b><spring:message code="claim.label.expedite"/></b>
														</td>
														<td>
															<input type="checkbox" id="claimDetail.expedite" onclick="saveCheckbox(this)" name="claimDetail.expedite" checked="checked" value="Y"/>
														</td>
													</tr>
												</table>
											</div>
											<!-- CI-6 Added End -->
											
							       	 		<!-- Ship to Address End -->
							       	 	</div>
										<div class="portlet-footer">
											<div class="portlet-footer-inner"></div>
										</div>
							       	</div>
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
									url="<portlet:renderURL windowState='<%= LiferayWindowState.POP_UP.toString() %>'>"+
								   " <portlet:param name='isMaintenance' value='true' /> "+
								         	"<portlet:param name='action' value='showPartAndToolPage' /></portlet:renderURL>";
									function partListPopup(){
										jQuery(window).scrollTop(0);
										partListPopUpWindow.show();
										jQuery(".aui button.close, .aui button.btn.close").hide();
										partListPopUpWindow.titleNode.html("<spring:message code='claim.label.partsList'/>");
										partListPopUpWindow.io.set('uri',url);
										partListPopUpWindow.io.start();
										
										}
									var modelNumber="${claimDetailFormForUpdate.claimDetail.serviceRequest.asset.modelNumber}";
									var preOption = 1;
									var partDate = localizeFormatDate(new Date());
									var qtyList = new Array();
									
									for(b = 1;b<=5;b++){
										qtyList[b] = b;
									}
									
									var partsAndToolsGrid = new dhtmlXGridObject('gridCCVPartDefault');
									partsAndToolsGrid.setImagePath("<html:imagesPath/>gridImgs/");
									partsAndToolsGrid.setHeader(autoAppendPlaceHolder(autoApplyRequiredStyle('<spring:message code="claim.headerList.claimUpdate.partsAndTools"/>'),8));
									partsAndToolsGrid.setColAlign("left,left,left,left,left,left,left,left");
									partsAndToolsGrid.setInitWidths("200,150,200,200,0,20,0,0");
									partsAndToolsGrid.setColTypes("ro,ro,ro,ro,ro,ro,ro,ro");
									partsAndToolsGrid.setColSorting("na,na,str,str,str,na,na,na");
									partsAndToolsGrid.enableAutoWidth(true);
									partsAndToolsGrid.enableAutoHeight(true);
									partsAndToolsGrid.enableMultiline(true);
									partsAndToolsGrid.enablePaging(true, 9999, 10, "gridCCVPartDefaultPaging", true);
									partsAndToolsGrid.init(); 
									//added by ragesree//
									partsAndToolsGrid.prftInit();
									partsAndToolsGrid.setSizes();
									partsAndToolsGrid.setColumnHidden(4,true);
									//********************************//
									partsAndToolsGrid.setSkin("light");
									partsAndToolsGrid.setPagingSkin("bricks");
									partsAndToolsGrid.loadXMLString("<rows></rows>");


									// changes for db saving and retrieving	start
									//partsAndToolsGrid.enableAutoSavingToDB("${saveCustomizedGridSettingURL}");
									partsAndToolsGrid.attachEvent("onAfterCMove",function(a,b){																						
										partsAndToolsGrid.enableAutoSavingToDB("${saveCustomizedGridSettingURL}");				
									});	
									<c:forEach var="gridSettingVar" items="${gridSettingList}" varStatus="status">
									<c:if test="${gridSettingVar.gridId == 'gridCCVPartDefault'}">					
									var colsOrder = "${gridSettingVar.colsOrder}";
									partsAndToolsGrid.loadOrder(colsOrder);
									
									</c:if>
									</c:forEach>
									// changes for db saving and retrieving	end
									
									partsAndToolsGrid.attachEvent("onRowAdded", function(rId){
										document.getElementById("partNumber").value = "";
									});
									
									partsAndToolsGrid.attachEvent("onAfterRowDeleted", function(id,pid){
										document.getElementById("partNumber").value = "";
						     		});
									
									function searchPart(partNumber){
										callOmnitureAction('Claim Detail Update View', 'Search Part');
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
									function addRowByPartList(part){
										document.getElementById("partListGridDiv").style.display = "none";
										document.getElementById("partListLoadingDiv").style.display = "";
										searchPart(part[0]);
									}
			
									function addRowByPart(part){
										var rowNumber = partsAndToolsGrid.getRowsNum();
										if(rowNumber!=0){
											for(i = 0;i< rowNumber ;i++){
												var parNum = partsAndToolsGrid.cellByIndex(i,2).cell.innerHTML;
												if(parNum==part[0]){
													if(part[3]!= null && part[3]!= 'null' && part[3]!= ""){
														var replaceMessage = '<spring:message code="claim.message.partNumber"/>&nbsp;' +part[3]+'&nbsp;<spring:message code="claim.message.rePlacePartNumber"/>&nbsp;' +part[0]+'. <spring:message code="claim.message.partIsExist"/>';
														jAlert(replaceMessage, "");
														return;
													}
													jAlert('<spring:message code="claim.message.partIsExist"/>', "");
													return;
												}
											}
										}
										if(part[3]!= null && part[3] != 'null' && part[3]!= ""){
											var replaceMessage = '<spring:message code="claim.message.partNumber"/>&nbsp;' +part[3]+'&nbsp;<spring:message code="claim.message.rePlacePartNumber"/>&nbsp;' +part[0];
											jAlert(replaceMessage, "");
										}
										var id = partsAndToolsGrid.getUID();
										var str =[partDate,'<table><tr><td><select id="qty_'+id+'" onchange=\"selectQty(this);\" onclick=\"saveOption(this);\">'+generateOption(qtyList)+'</select></td></tr></table>',part[0],part[1],(part[2]=="true"?'<spring:message code="claim.lebel.returnRequired.yes"/>':'<spring:message code="claim.lebel.returnRequired.no"/>'),'<a href="javascript:deletePartRow(\''+id+'\');"><img class="ui-icon trash-icon" src="<html:imagesPath/>transparent.png"></a>', part[4],''];
										partsAndToolsGrid.addRow(id,str,0);
										showMessageForQty("<spring:message code='claim.message.addRowQtyIsGreaterThan5'/>",false);
									}
									
									
									function deletePartRow(ind){
										jConfirm("<spring:message code='claim.message.removePart'/>", "", function(confirmResult) {
											if(!confirmResult){
												return;
											}else{
												partsAndToolsGrid.deleteRow(ind);
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
									//CI-6 Changes Partha End
									
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
										var grid = partsAndToolsGrid;
										var rowNumber = grid.getRowsNum();
										var totalQty=0;
										if(rowNumber!=0){
											for(i = 0;i< rowNumber ;i++){
												var ind = grid.getRowId(i);
												var qtyValue = document.getElementById("qty_"+ind).value;
												totalQty=parseInt(totalQty)+parseInt(qtyValue);	
											}
											if(parseInt(totalQty)>5)
												return true;
										}
										return false;
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
									
									
									function generatePartAndTool(){
										var date = new Date();
										document.getElementById("contentPart").innerHTML = '';
										var rows = partsAndToolsGrid.getRowsNum();
										for(var i = 0 ;i < rows ;i++){
											var ind = partsAndToolsGrid.getRowId(i);
											var qtyValue = document.getElementById("qty_"+ind).value;
											var partNumber = partsAndToolsGrid.cellByIndex(i,2).cell.innerHTML;
											var partName = partsAndToolsGrid.cellByIndex(i,3).cell.innerHTML;
											var reRequire = partsAndToolsGrid.cellByIndex(i,4).cell.innerHTML;

											var input = document.createElement("input");
											input.id = "claimDetail.pendingPartList["+i+"].partId"; 
											input.name = "claimDetail.pendingPartList["+i+"].partId"; 
											input.value = partsAndToolsGrid.cellByIndex(i,6).cell.innerHTML=="&nbsp;"?"":partsAndToolsGrid.cellByIndex(i,6).cell.innerHTML;
											document.getElementById("contentPart").appendChild(input);

											var input = document.createElement("input");
											input.id = "claimDetail.pendingPartList["+i+"].partLineItemId"; 
											input.name = "claimDetail.pendingPartList["+i+"].partLineItemId"; 
											input.value = partsAndToolsGrid.cellByIndex(i,7).cell.innerHTML=="&nbsp;"?"":partsAndToolsGrid.cellByIndex(i,7).cell.innerHTML;
											document.getElementById("contentPart").appendChild(input);
											
											var input = document.createElement("input");
											input.id = "claimDetail.pendingPartList["+i+"].partOrderedDate"; 
											input.name = "claimDetail.pendingPartList["+i+"].partOrderedDate"; 
											input.value = date;
											document.getElementById("contentPart").appendChild(input);
											
											var input = document.createElement("input");
											input.id = "claimDetail.pendingPartList["+i+"].quantity"; 
											input.name = "claimDetail.pendingPartList["+i+"].quantity"; 
											input.value = parseInt(qtyValue);
											document.getElementById("contentPart").appendChild(input);
											
											var input = document.createElement("input");
											input.id = "claimDetail.pendingPartList["+i+"].partNumber"; 
											input.name = "claimDetail.pendingPartList["+i+"].partNumber"; 
											input.value = partNumber;
											document.getElementById("contentPart").appendChild(input);
											
											var input = document.createElement("input");
											input.id = "claimDetail.pendingPartList["+i+"].partName"; 
											input.name = "claimDetail.pendingPartList["+i+"].partName"; 
											input.value = partName;
											document.getElementById("contentPart").appendChild(input);
											
											if('<spring:message code="claim.lebel.returnRequired.yes"/>'==reRequire){
												var input = document.createElement("input");
												input.id = "claimDetail.pendingPartList["+i+"].returnRequiredFlag"; 
												input.name = "claimDetail.pendingPartList["+i+"].returnRequiredFlag"; 
												input.value = true;
												document.getElementById("contentPart").appendChild(input);
											}else{
												var input = document.createElement("input");
												input.id = "claimDetail.pendingPartList["+i+"].returnRequiredFlag"; 
												input.name = "claimDetail.pendingPartList["+i+"].returnRequiredFlag"; 
												input.value = false;
												document.getElementById("contentPart").appendChild(input);
											}
										}
									}
									
									function showPartList(){
										//showOverlayIE6();
										partListPopup();
										//bindingClosePopupIE6();
									}
								</script>  
								</td>
							</tr>
						</table>
				        <!-- Order Parts and Tools end -->	     
				        </c:if>
						<!-- Additional Payment Requests Start-->  
				        <table width="100%">
							<tr>
								<td>
							        <c:if test="${showAdditionalPaymentRequestListFlag}">   	 
								        <div class="portlet-wrap width-100per" id="Additional_payment_requests">
											<div class="portlet-header">
												<h3><spring:message code="claim.label.additionalPaymentRequests"/></h3>
											</div><!-- portlet-header -->
											<div class="portlet-wrap-inner scroll">
												<div class="columns scroll">
													<div id="gridAdditionalPaymentRequests" class="width-100per"></div>
													<div id="pagingAreaForAdditionalPayment" class="width-auto-important"></div>
												</div>
												<div class="columns" >
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
											additionalPaymentRequestsGrid.setInitWidths("200,100,150,100,100,400,100");
											additionalPaymentRequestsGrid.setColTypes("ro,ro,ro,ro,ro,ro,ro");
											additionalPaymentRequestsGrid.setColSorting("str,str,str,str,str,str,str");
											additionalPaymentRequestsGrid.enableAutoWidth(true);
											additionalPaymentRequestsGrid.enableAutoHeight(true);
											additionalPaymentRequestsGrid.enableMultiline(true);
											additionalPaymentRequestsGrid.enablePaging(true, 5, 6, "pagingAreaForAdditionalPayment", true);
											additionalPaymentRequestsGrid.init(); 
											additionalPaymentRequestsGrid.setSkin("light");	
											additionalPaymentRequestsGrid.setPagingSkin("bricks");

											function addNewPaymentRequest(){
												callOmnitureAction('Claim Detail Update View', 'Add New Payment Request');
												var id = additionalPaymentRequestsGrid.getUID();
												var defaultCurrency = "${claimDetailFormForUpdate.claimDetail.partnerAccount.defaultCurrency}";
												var str = '<table><tr><td><select id="'+id+'_paymentType_grid" >'+ getPaymentTypeOptions() +'</select></td></tr></table>,' +
														  '<table><tr><td><input id="'+id+'_qty_grid" size="2" maxlength = "5" type="text" onblur="calculateAmount(this &#44;' + id + ')"  onafterpaste="clearNoNumAndPoint(this)" onkeyup="clearNoNumAndPoint(this)"  value=""/></td></tr></table>,' +
														  '<table><tr><td><input id="'+id+'_unitPrice_grid" maxlength = "10" size="10" type="text" onblur="calculateAmount(this &#44;' + id + ')" onafterpaste="clearNoNum(this)" onkeyup="clearNoNum(this)"   value=""/></td></tr></table>,' +
														  ''+ ',' +
														  defaultCurrency + ',' +
														  '<table><tr><td><input id="'+id+'_description_grid" size="50" type="text" onchange="validateLength(0&#44; 250&#44; this)"/></td></tr></table>,' +
														  '<a href="javascript:deleteRowInPaymentGrid(\''+id+'\');"><img class="ui-icon trash-icon" src="<html:imagesPath/>transparent.png"></a><input id="'+id+'_paymentRequestUpdateFlag" type="hidden" value="false" /><input id="'+id+'_isNew" type="hidden" value="false" />';
												additionalPaymentRequestsGrid.addRow(id,str,0);
												additionalPaymentRequestsGrid.changePage(1);				
											}
								
											function getPaymentTypeOptions(){
												var options = '<option value=""></option><c:forEach items="${claimDetailFormForUpdate.paymentTypes}" var="type" varStatus="loop"><option value="${type.key}">${type.value}</option></c:forEach>';
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
												callOmnitureAction('Claim Detail Update View', 'Delete Row InPaymentGrid');
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
														var rowId = additionalPaymentRequestsGrid.getRowId(i);
														var inputType = document.getElementById(rowId+"_paymentType_grid").value;
														var inputQty = document.getElementById(rowId+"_qty_grid").value;
														var inputUnitPrice = document.getElementById(rowId+"_unitPrice_grid").value;
														var inputDescription = document.getElementById(rowId+"_description_grid").value;
														
														if(trim(inputType) == '' || trim(inputType) == '&nbsp;'){
															currentResult = false;
															errMsg += '<spring:message code="claim.errorMsg.additionalPayment.paymentTypeNotNull"/>' + '; ';
														}
														if(trim(inputQty) == '' || trim(inputQty) == '&nbsp;'){
															currentResult = false;
															errMsg += '<spring:message code="claim.errorMsg.additionalPayment.qtyNotNull"/>' + '; ';
														}
														if(trim(inputUnitPrice) == '' || trim(inputUnitPrice) == '&nbsp;'){
															currentResult = false;
															errMsg += '<spring:message code="claim.errorMsg.additionalPayment.unitPrice"/>' + '; ';
														}
														
														if(trim(inputDescription) == '' || trim(inputDescription) == '&nbsp;'){
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
										</script>
									</c:if>
								</td>
							</tr>
						</table>
				        <!-- Additional Payment Requests End-->          	
				        
						<!-- Notes Start-->  
						<table width="100%">
							<tr>
								<td>
									<div class="portlet-wrap width-100per" id="notes">
									<div class="portlet-header">
										<h3><spring:message code="claim.label.notes"/></h3>
									</div><!-- portlet-header -->
									<div class="div-style1"><div class="div-style2"><b><spring:message code="claim.notes.helpText2"/></b></div><div class="div-style3"><b><spring:message code="claim.notes.helpText"/></b></div></div>
									<div class="portlet-wrap-inner div-style4a" >
										<div class="width-100per">
												<div class="columns scroll">
												<div id="gridCUpdateNotes" class="width-100per"></div>
												<div id="loadingNotificationNote" class="gridLoading" style=" display:none;"><!--spring:message code="label.loadingNotification"/-->&nbsp;
													<img src="<html:imagesPath/>gridloading.gif" /><br>
												</div>
												<div id="pagingNote"></div>
											</div>
												<div class="columns scroll">
												<dl>
													<dd>
														<a href="javascript:addNotePopUp();" class="button"><spring:message code="claim.button.addNotes"/></a>
														<div class="overlayAddnotePopup" ></div>
													</dd>
												</dl>
											</div>		
										</div>		
									</div><!-- portlet-wrap-inner -->
									<div id="noteContent" style="display:none;"></div>
									<div class="portlet-footer">
										<div class="portlet-footer-inner"></div><!-- portlet-footer-inner -->
									</div><!-- portlet-footer -->
								</div>
								
								<script type="text/javascript">
									gridCUpdateNotesJs = new dhtmlXGridObject('gridCUpdateNotes');
									gridCUpdateNotesJs.setImagePath("<html:imagesPath/>gridImgs/");
									gridCUpdateNotesJs.setHeader(autoAppendPlaceHolder('<spring:message code="claim.headerList.notes.details"/>',5));
									gridCUpdateNotesJs.setColAlign("left,left,left,left,left");
									gridCUpdateNotesJs.setColTypes("sub_row,ro,ro,ro,ro");
									gridCUpdateNotesJs.setColSorting("na,str,str,str,str");
									gridCUpdateNotesJs.setInitWidths("25,120,120,500,100");
									gridCUpdateNotesJs.enableAutoWidth(true);
									gridCUpdateNotesJs.enableAutoHeight(true);
									gridCUpdateNotesJs.enableMultiline(true);
									gridCUpdateNotesJs.enablePaging(true, 5, 6, "pagingNote", true);	
									gridCUpdateNotesJs.init();
									gridCUpdateNotesJs.setSkin("light");
									gridCUpdateNotesJs.setPagingSkin("bricks");
									gridCUpdateNotesJs.attachEvent("onXLS", function() {
										document.getElementById('loadingNotificationNote').style.display = 'block';
									});
									gridCUpdateNotesJs.attachEvent("onXLE", function() {
										setTimeout(function(){
								    		rebrandPagination();
								    	
								    	},100);
										document.getElementById('loadingNotificationNote').style.display = 'none';
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
										callOmnitureAction('Claim Detail Update View', 'Add Note PopUp');
										//showOverlayIE6();
										showNotesPopup();
										//bindingClosePopupIE6();
									}
									function addRowInNotesGrid(note){
										var subDetail = escapeStringForNoteSubRow(note[0]);
										var id = gridCUpdateNotesJs.getUID();
										var str =['<strong><spring:message code="claim.label.note"/></strong><br><div style="word-wrap: break-word;width:80%">'+subDetail+'</div>',note[1],note[2],note[3],
													'<button class="button" type="button" onclick="editRow('+id+')"><spring:message code="button.edit"/></button> <input id="'+id+'_noteDetail" type="hidden" value="'+note[0]+'">'
													+'<input id="'+id+'_contactId" type="hidden" value="${claimDetailFormForUpdate.contactId}">'
													+'<input id="'+id+'_activityUpdateFlag" type="hidden" value="false">'];
										gridCUpdateNotesJs.addRow(id,str,0);
										gridCUpdateNotesJs.changePage(1);
									}
									function updateRowInNotesGrid(note,rowId){
										var subDetail = escapeStringForNoteSubRow(note[0]);
										var newRowId = gridCUpdateNotesJs.getUID();
										var str =['<strong><spring:message code="claim.label.note"/></strong><br><div style="word-wrap: break-word;width:80%">'+subDetail+'</div>',note[1],note[2],note[3],
													'<button class="button" type="button" onclick="editRow('+rowId+')"><spring:message code="button.edit"/></button> <input id="'+rowId+'_noteDetail" type="hidden" value="'+note[0]+'">'
													+'<input id="'+rowId+'_contactId" type="hidden" value="'+document.getElementById(rowId+"_contactId").value+'">'
													+'<input id="'+rowId+'_activityUpdateFlag" type="hidden" value="true">'];
										if(document.getElementById(rowId+"_noteId")){
											str = ['<strong><spring:message code="claim.label.note"/></strong><br><div style="word-wrap: break-word;width:80%">'+subDetail+'</div>',note[1],note[2],note[3],
													'<button class="button" type="button" onclick="editRow('+rowId+')"><spring:message code="button.edit"/></button> <input id="'+rowId+'_noteDetail" type="hidden" value="'+note[0]+'">'
													+'<input id="'+rowId+'_contactId" type="hidden" value="'+document.getElementById(rowId+"_contactId").value+'">'
													+'<input id="'+rowId+'_activityUpdateFlag" type="hidden" value="true">'
													+'<input id="'+rowId+'_noteId" type="hidden" value="'+document.getElementById(rowId+"_noteId").value+'">'];
										}
										gridCUpdateNotesJs.addRow(newRowId,str,0);
										gridCUpdateNotesJs.copyRowContent(newRowId,rowId);
										gridCUpdateNotesJs.deleteRow(newRowId);
										gridCUpdateNotesJs.cellById(rowId,0).setContent(['<strong><spring:message code="claim.label.note"/></strong><br><div style="word-wrap: break-word;width:80%">'+subDetail+'</div>']);
										gridCUpdateNotesJs.cellById(rowId,0).close();
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
										callOmnitureAction('Claim Detail Update View', 'Edit Note');
										var noteDate = gridCUpdateNotesJs.cellById(id,1).cell.innerHTML;
										var noteAuthor = gridCUpdateNotesJs.cellById(id,2).cell.innerHTML;
										var noteDetailId = id+"_noteDetail";
										//showOverlayIE6();
										updateNotesPopup(noteDate,noteAuthor,noteDetailId,id);
										//bindingClosePopupIE6();
									}
									
									function generateNotesFormData(){
										document.getElementById("noteContent").innerHTML = "";
										var rows = gridCUpdateNotesJs.getRowsNum();
										var noteCurrentPage = gridCUpdateNotesJs.currentPage;
										for(var i = 0 ;i < rows ;i++){
											if(i == 0){
												gridCUpdateNotesJs.changePage(1);
											}else if(i%global_paging_size ==0){
												gridCUpdateNotesJs.changePageRelative(1);
											}
											var rowId = gridCUpdateNotesJs.getRowId(i);
											//noteDate
											var inputDate = document.createElement("input");
											inputDate.id = "claimDetail.activityNoteList["+i+"].noteDate"; 
											inputDate.name = "claimDetail.activityNoteList["+i+"].noteDate";
											inputDate.value = formatDateToDefault(gridCUpdateNotesJs.cellByIndex(i,1).cell.innerHTML);
											document.getElementById("noteContent").appendChild(inputDate);
											//noteAuthor
											var inputContactId = document.createElement("input");
											inputContactId.id = "claimDetail.activityNoteList["+i+"].noteAuthor.contactId"; 
											inputContactId.name = "claimDetail.activityNoteList["+i+"].noteAuthor.contactId";
											inputContactId.value = document.getElementById(rowId+"_contactId").value;
											document.getElementById("noteContent").appendChild(inputContactId);
											//noteDetail
											var inputDetail = document.createElement("input");
											inputDetail.id = "claimDetail.activityNoteList["+i+"].noteDetails"; 
											inputDetail.name = "claimDetail.activityNoteList["+i+"].noteDetails";
											//var detail = escapeStringForNote(document.getElementById(rowId+"_noteDetail").value);
											var detail = document.getElementById(rowId+"_noteDetail").value;
											inputDetail.value = detail;
											document.getElementById("noteContent").appendChild(inputDetail);
											//noteId
											var inputNoteId = document.createElement("input");
											inputNoteId.id = "claimDetail.activityNoteList["+i+"].noteId"; 
											inputNoteId.name = "claimDetail.activityNoteList["+i+"].noteId";
											if(document.getElementById(rowId+"_noteId")){
												inputNoteId.value = document.getElementById(rowId+"_noteId").value;
											}
											document.getElementById("noteContent").appendChild(inputNoteId);
											//activityUpdateFlag
											var inputActivityUpdateFlag = document.createElement("input");
											inputActivityUpdateFlag.id = "claimDetail.activityNoteList["+i+"].activityUpdateFlag"; 
											inputActivityUpdateFlag.name = "claimDetail.activityNoteList["+i+"].activityUpdateFlag";
											inputActivityUpdateFlag.value = document.getElementById(rowId+"_activityUpdateFlag").value;
											document.getElementById("noteContent").appendChild(inputActivityUpdateFlag);
										}
										gridCUpdateNotesJs.changePage(noteCurrentPage);
									} 
									</script>
								</td>
							</tr>
						</table>   	  
						
						<!-- Notes End-->   
								
					</form:form>
								
			<!-- File Attachment start -->
			 <table width="100%">
			   <tr>
			     <td>
			     <form:form id="attachmentForm" name="attachmentForm" commandName="attachmentFormDisplay" method="post" enctype="multipart/form-data">
			       <div class="portlet-wrap" id="attachmentBorder">
			       	<div class="portlet-header">
						<h3><spring:message code="claim.label.attachments" /></h3>
					</div>
					
					
					<div id="browseAttachmentFileId" class="portlet-wrap-inner" >
						<table class="importTable margin-top-15px" width="100%" border="0" >
							<tr>
								<td>
									<b><LABEL class="labelBold" for="fileInput"><spring:message code="fileAttachment.label.fileToUpload" /></LABEL>:&nbsp;</b>
								
									<nobr>
										<input type="text" id="txtFilePath" size="50" readonly="true" />&nbsp; &nbsp;&nbsp;
										<a href="#" class="button"><spring:message code="button.browse"/></a>&nbsp;
										<form:input type="file" size="1" id="fileInput" class="requestsUploader" path="fileData" onchange="document.getElementById('txtFilePath').value = getFileName();" style="z-index: 2; position: relative; filter: alpha(opacity = 0); opacity: 0; width: 80px; left: 0px !important; top: 0px !important; margin-left: -75px !important;" />  
										<a href="javascript:uploadFile();" class="button"><spring:message code="button.uploadFile"/></a>
									</nobr>		   
								</td>
							</tr>
							<tr class="import-table-tr1"><td><nobr><b><spring:message code="attachment.messgae.FileRestriction"/></b></nobr></td></tr>
							
							<tr class="import-table-tr1"><td><nobr><b><table id="errorMessageTable"></table></b></nobr></td></tr>
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
					
				 <div class="columnInner">
				  <div id="fileListDiv" >
					<table id="fileListTable"></table>
					
					</div>
					<div id="test"></div>
					 <div class="lineClear"></div>							
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
						// jQuery('#attachmentForm').submit();
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
			
				function openFile(userFileName,activityId,actualFileName,identifier,type){
					//alert("Hello");
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

			 <table width="98%" class="margin-left-10px">
			  	<tr>
			  		<td>
			  			<form:form id="dummyForm" name="dummyForm" method="post">
			  				<table width="98%">
								<tr>
									<td>
										<div align="left">
											<a href="javascript:submitFormClaimUpdate();" class="button"><spring:message code="button.update"/></a>&nbsp;&nbsp;
											<a href="javascript:cancelUpdateClaim();" class="button_cancel"><spring:message code="button.cancel"/></a>
										</div>
									</td>
								</tr>
							</table>
			  			</form:form>
			  		</td>	
			  	</tr>
			  </table>	        
			<!-- File Attachment end -->
								
					</div><!-- content -->
				</div>
				
		</td>
	</tr>
</table>
	
			 

<script type="text/javascript">
	document.getElementById("timezoneOffset").value = timezoneOffset;
	function validateBeforeSubmit(){
		var result = validateTechnicianInfo();
		if(document.getElementById('gridAdditionalPaymentRequests')){
			result &= validatePaymentRequests();
		}
		return result;
	}
	
	function submitFormClaimUpdate(){
		callOmnitureAction('Claim Detail Update View', 'Submit');
		clearMessage();
		if(validateBeforeSubmit() != 0){
			if(document.getElementById('gridCCVPartDefault')){
				generatePartAndTool();
			}
			if(document.getElementById('gridAdditionalPaymentRequests')){
				generateAdditionalPaymentFormData();						
			}
			
			generateNotesFormData();
			confirmSubmitUpdateClaim();
		}	
		else{
			window.scroll(0,0);
		}
	}
	
	function confirmSubmitUpdateClaim(){
		jConfirm("<spring:message code='claim.message.confirmSubmitClaimUpdate'/>", "", function(confirmResult) {
			if(confirmResult){
				jQuery("#claimUpdateForm").submit();
			}else{
				return;
			}
		});
	}

	function cancelUpdateClaim(){
		callOmnitureAction('Claim Detail Update View', 'Cancel');
		jConfirm("<spring:message code='claim.message.cancelUpdateClaim'/>", "", function(confirmResult) {
			if(!confirmResult){
				return;
			}else{
				window.location.href = "${cancelUpdate}&timezoneOffset="+timezoneOffset;
			}
		});
	}

	jQuery(document).ready(function() {
		if(document.getElementById('gridAdditionalPaymentRequests')){
			additionalPaymentRequestsGrid.loadXMLString("${claimDetailFormForUpdate.additionalPaymentRequestListXML}");
		}
		gridCUpdateNotesJs.loadXMLString("${claimDetailFormForUpdate.activityNoteListXML}");
		var wrapperWidth=window.innerWidth-144;
		   $(".status-block,.columns").css({"max-width":wrapperWidth+"px"});
	
	});
</script>
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
</script>
<script type="text/javascript">
//---- Ominture script 
     portletName = "Claim Update View";
     addPortlet(portletName);
</script>

