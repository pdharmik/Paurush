<%@ include file="/WEB-INF/jsp/include.jsp"%>
<%@ include file="/WEB-INF/jsp/includeGrid.jsp"%>
<script src='<html:rootPath/>js/trackingNumber.js'></script>

<style type="text/css"><%@ include file="/WEB-INF/css/ci6.css" %></style>
<style type="text/css"><%@ include file="/WEB-INF/css/mps.css" %></style>
<!--[if IE 7]>
	<style type="text/css"><%@ include file="/WEB-INF/css/mps_ie7.css" %></style>
<![endif]-->
<style type="text/css">
.pagination span#gridCDtlVRPRequestPagingArea>div { width:100%!important }
.pagination span#gridCDtlVNRequestPagingArea>div { width:100%!important }
.pagination span#gridCDtlVOPRequestPagingArea>div { width:100%!important }

	/* Landscaping Printer overrides */
	@media print {
		*, body {
			background-color:white !important;
			background:none !important;
			}
		table {
			page-break-after:left;
			}
		html, body, .gridbox_light {
			width:auto;
			overflow:visable !important;
			}	
		#wrapper {
			width:auto;
			overflow:visible;
			}	
		#top-level-navigation, .grid-controls, #topnavigation, .left-nav, #banner, #pagingArea, .portlet-footer, .portlet-chat, .utilities, img {
 			visibility:hidden !important;
			display:none !important;
			}
		.objbox	{
			overflow:hidden !important;
			}
		.journal-content-article h2 {
			color: black !important;
			}
		.right-column {
			width:auto !important;
			margin-left:0px !important;
			}	
		.portlet-wrap-inner, .gridbox .gridbox_light {
			width:auto !important;
			height:auto;
			overflow:visable !important;
			page-break-after:left;
			}		
}
</style>
<portlet:renderURL  var="requestsListViewUrl">
</portlet:renderURL>

<portlet:renderURL  var="showCloseOutClaimPage">
    <portlet:param name="action" value="showCloseOutClaimPage" />
    <portlet:param name="activityId" value="${claimDetailForm.claimDetail.activityId}" />
    <portlet:param name="serviceRequestId" value="${claimDetailForm.claimDetail.serviceRequest.id}" />
    <portlet:param name="serviceType" value="${claimDetailForm.claimDetail.serviceType}" />
    <portlet:param name="fromPage" value="claimDetailView" />
    <portlet:param name="timezoneOffset" value="${timezoneOffset}" />
</portlet:renderURL>

<portlet:renderURL var="claimUpdateRequestPageURL">
	<portlet:param name="action" value="showClaimUpdatePage"/>
	<portlet:param name="activityId" value="${claimDetailForm.claimDetail.activityId}" />
	<portlet:param name="serviceRequestId" value="${claimDetailForm.claimDetail.serviceRequest.id}" />
	<portlet:param name="serviceType" value="${claimDetailForm.claimDetail.serviceType}" />
	<portlet:param name="fromPage" value="claimDetailView" />
	<portlet:param name="timezoneOffset" value="${timezoneOffset}" />
</portlet:renderURL>

<portlet:resourceURL var="downClaimDetailPdfURL" id="downClaimDetailPdfURL">
    <portlet:param name="activityId" value="${claimDetailForm.claimDetail.activityId}" />
    <portlet:param name="serviceRequestId" value="${claimDetailForm.claimDetail.serviceRequest.id}"/>
    <portlet:param name="timezoneOffset" value="${timezoneOffset}" />
</portlet:resourceURL>

<c:set var="serviceRequestId" value="${claimDetailForm.claimDetail.serviceRequest.id}"/>
<c:set var="emailAddress" value="${claimDetailForm.claimDetail.emailAddress}"/>

<script type="text/javascript">
    var exchangeflag="${claimDetailForm.claimDetail.exchangeFlag}";
    var serviceType="${claimDetailForm.claimDetail.serviceType}";
	var showButtonsFlag = "${showCloseButtonFlag}" == "true";
	var requestLexmarkReviewFlag = "${claimDetailForm.claimDetail.requestLexmarkReviewFlag}" == "true";
	var showRequestedPartsFlag = "${showRequestedPartsFlag}" == "true";
	var showOrderPartsFlag = "${showOrderPartsFlag}" == "true";
	var showReturnPartsFlag = "${showReturnPartsFlag}" == "true";
	var showAdditionalPaymentRequestListFlag = "${showAdditionalPaymentRequestListFlag}" == "true";
	function updateClaimBtnClick(activityId){
		callOmnitureAction('Claim Detail View', 'Update Claim');
		window.location.href = "${claimUpdateRequestPageURL}&serviceType="+serviceType;
	}
	//updated for 2098
	function closeOutClaimBtnClick(){
		callOmnitureAction('Claim Detail View', 'Close Out Claim');
		window.location.href= "${showCloseOutClaimPage}&serviceType="+serviceType;
	}
	
    function print(){
		callOmnitureAction('Claim Detail View', 'Claim Detail Print');
		var url ="<portlet:renderURL windowState='<%= LiferayWindowState.POP_UP.toString() %>'>"+
			  	"<portlet:param name='action' value='printClaimDetail' />"+
			  "</portlet:renderURL>";
		    var iWidth=900;
		    var iHeight=600;
		    var iTop = (window.screen.availHeight-30-iHeight)/2;        
		    var iLeft = (window.screen.availWidth-10-iWidth)/2;           
		    window.open(url,
		    		'RequestList',
				    'height='+iHeight+
				    ',innerHeight='+
				    iHeight+',width='+
				    iWidth+',innerWidth='+
				    iWidth+',top='+iTop+
				    ',left='+iLeft+
				    ',status=no,toolbar=no,menubar=no,location=no,resizable=yes,scrollbars=yes,titlebar=no');
	}

	function downloadPdf(){
		callOmnitureAction('Claim Detail View', 'Claim Detail PDF');
		window.location.href = "${downClaimDetailPdfURL}";
	}
	
</script>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/in-styles.css?version=<html:fileCacheVersion/>" />
<table width="100%">
	<tr>
		<td>
			<div class="main-wrap main-wrap-ext2" >
				<div class="content">
					<!-- Export(print,csv,pdf) Start -->
					<div class="grid-controls">	
						<div class="utilities">	
							<ul>	
								<li class="first">
									<img height="23px" width="23px" title="<spring:message code='image.title.print'/>" onclick="print()"; src="<html:imagesPath/>transparent.png" class="ui-icon print-icon cursor-pointer">
								</li>
								<li>
									<img src="<html:imagesPath/>transparent.png" class="ui-icon pdf-icon cursor-pointer" height="23px" width="23px" onClick="downloadPdf()" title="<spring:message code='image.title.exportPDF'/>" />
								</li>
							</ul>
						</div>
						<div class="expand-min">
							<ul>
								<li class="first">
									<a id="headerImageButton" href="${requestsListViewUrl}&back=true" class="button">&lt;&lt;<spring:message code="link.return.to.requests"/></a>
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
								
									<c:choose>
										<c:when test="${showUpdateButtonFlag || showCloseButtonFlag}">
											<div class="first-column position-relative">
						           			<c:if test="${showUpdateButtonFlag}">
												<a href="javascript:updateClaimBtnClick();" class="button button-style1" ><spring:message code="claim.button.updateClaim"/></a>
												<br>
												<br>
											</c:if>
											
											<c:if test="${showCloseButtonFlag}">
												<c:if test="${showUpdateButtonFlag}">
													<a href="javascript:closeOutClaimBtnClick();" class="button button-style2" ><spring:message code="claim.button.closeOutClaim"/></a>
												</c:if>
												<c:if test="${showUpdateButtonFlag == false}">
													<a href="javascript:closeOutClaimBtnClick();" class="button button-style1" ><spring:message code="claim.button.closeOutClaim"/></a>
													<br>
													<br>
												</c:if>
											</c:if>
											</div>
											<div id="divClaimInformation" class="second-column">
												<table  border="0">
													<tr>
														<td width="150px" align="right"><b><spring:message code="claim.label.claimNumber"/></b></td>
														<td width="10px"/>
														<td>${claimDetailForm.claimDetail.serviceRequest.serviceRequestNumber}</td>
													</tr>
													<tr>
														<td align="right"><b><spring:message code="claim.label.claimStatus"/></td>
														<td/>
														<td>${claimDetailForm.claimDetail.activityStatus.name}</b></td>
													</tr>
													<tr>
														<td align="right"><b><spring:message code="claim.label.openedDate"/></td>
														<td/>
														<td><util:dateFormat value="${claimDetailForm.claimDetail.serviceRequest.serviceRequestDate}" timezoneOffset="${timezoneOffset}"/><b></td>
													</tr>
												</table>
											</div>
										</c:when>
										<c:otherwise>
											<div id="divClaimInformation" class="first-column">
												<table  border="0">
													<tr>
														<td width="150px" align="right"><b><spring:message code="claim.label.claimNumber"/></b></td>
														<td width="10px"/>
														<td>${claimDetailForm.claimDetail.serviceRequest.serviceRequestNumber}</td>
													</tr>
													<tr>
														<td align="right"><b><spring:message code="claim.label.claimStatus"/></td>
														<td/>
														<td>${claimDetailForm.claimDetail.activityStatus.name}</b></td>
													</tr>
													<tr>
														<td align="right"><b><spring:message code="claim.label.openedDate"/></td>
														<td/>
														<td><util:dateFormat value="${claimDetailForm.claimDetail.serviceRequest.serviceRequestDate}" timezoneOffset="${timezoneOffset}"/><b></td>
													</tr>
												</table>
											</div>
										</c:otherwise>
									</c:choose>
									
								
					           		
								</div>
							</div>
						</div>
						<div class="portlet-footer">
							<div class="portlet-footer-inner"></div>
						</div>
					</div>
			        <!-- Claim Information End -->    	 
			        
			        <!-- Device Information Start -->   	 
			        <div id="divDeviceInformation" class = "portlet-wrap">
			        	<div class="portlet-header dark-bar">
			           		<h3><spring:message code="claim.label.deviceInformation"/></h3>
			       		</div>
			       		<div class="portlet-wrap-inner">
			       			<div class="width-100per">
				    			<div class="columns two">
					           		<div class="first-column">
										<table width="80%" border="0">
											<tr>
												<td  class="labelTD" align="center"><h3>${claimDetailForm.claimDetail.serviceRequest.asset.productLine}</h3>
											    </td>
											</tr>
											<tr>
											  	<td align="center"><img width="75" src="${claimDetailForm.claimDetail.serviceRequest.asset.productImageURL}"></td>
											</tr>
										</table>
									</div>
									<div class="second-column">
										<br/>
										<br/>
										<table height="100%"  border="0">
											<tr>
											 	<td width="150px" align="right"><b><spring:message code="claim.label.serialNumber"/></b></td>
												<td width="10px" />
											  	<td class="labelTD">${claimDetailForm.claimDetail.serviceRequest.asset.serialNumber}</td>
											</tr>	 
											<tr>
												<td class="labelTD" align="right"><b><spring:message code="claim.label.machineTypeModel"/></b></td>
												<td/>
												<td class="labelTD">${claimDetailForm.claimDetail.serviceRequest.asset.modelNumber}</td>
											</tr>
											<tr>
												<td class="labelTD" align="right"><b><spring:message code="claim.label.productPN/TLI"/></b></td>
												<td/>
												<td class="labelTD">${claimDetailForm.claimDetail.serviceRequest.asset.productTLI}</td>
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
			       		<c:set var="assetId" value="${claimDetailForm.claimDetail.serviceRequest.asset.assetId}"/> 
						<c:set var="serviceRequestId" value="${claimDetailForm.claimDetail.serviceRequest.id}"/> 
						<%@ include file="/WEB-INF/jsp/request/serviceRequestHistoryList.jsp"%>
			        <!-- Service History End --> 
			       
			        <!-- Customer Information Start -->  	 
					<div id="divCustomerInformation" class = "portlet-wrap">
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
											 	 <td>${claimDetailForm.claimDetail.customerAccount.accountName}</td>
										 	</tr>
										  	<tr>
											 	<td align="right" valign="top" rowspan="2"><b><spring:message code="claim.label.address"/></b></td>
											  	<td rowspan="2">
											  		<c:choose>
														<c:when test="${empty claimDetailForm.claimDetail.newCustomerAddressCombined}">
															<util:addressOutput value="${claimDetailForm.claimDetail.serviceRequest.asset.installAddress}"/>
														</c:when>
														<c:otherwise>
															<util:replaceRN value="${claimDetailForm.claimDetail.newCustomerAddressCombined}"/>
														</c:otherwise>
													</c:choose>
											  	</td>
										 	</tr>
										</table>
									</div>
									<div class="second-column" >
										<table  border="0">
											<tr>
											  	<td/>
											  	<td rowspan="4" width="10px"/>
											  	<td><b><u><spring:message code="claim.label.primaryContact"/></u></b></td>
										 	</tr>
										  	<tr>
												<td align="right"><b><spring:message code="claim.label.name"/></b></td>
												<td>${claimDetailForm.claimDetail.serviceRequest.primaryContact.firstName} ${claimDetailForm.claimDetail.serviceRequest.primaryContact.lastName}</td>
										 	</tr>
										  	<tr>
											  	<td align="right"><b><spring:message code="claim.label.phone"/></b></td>
											 	<td>${claimDetailForm.claimDetail.serviceRequest.primaryContact.workPhone}</td>
										 	</tr>
										 	<tr>
											 	<td align="right" valign="top"><b><spring:message code="claim.label.email"/></b></td>
											 	<td valign="top">${claimDetailForm.claimDetail.serviceRequest.primaryContact.emailAddress}</td>
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
			        
			        <!-- Problem Information Start -->  	 	 
					<div id ="divProblemInformation" class = "portlet-wrap">
						<div class="portlet-header dark-bar">
							<h3><spring:message code="claim.label.problemInformation"/></h3>
						</div>
			       	 	<div class="portlet-wrap-inner">
				       	 	<div class="width-100per">
				       	 		<div class="columns two">
										<table width="80%">
											<tr><td><b><spring:message code="claim.label.problemCode"/></b></td></tr>
											<tr><td height="5px"></td></tr>
											<tr><td>${claimDetailForm.claimDetail.actualFailureCode.name}</td></tr>
											<tr><td height="10px"></td></tr>
											<tr><td><b><spring:message code="claim.label.problemDetails"/></b></td></tr>
											<tr><td height="5px"></td></tr>
											<tr><td>${claimDetailForm.claimDetail.serviceRequest.problemDescription}</td></tr>
											<tr><td height="10px"></td></tr>
											<tr><td><b><spring:message code="claim.label.serviceProviderReferenceNumber"/></b></td></tr>
											<tr><td height="5px"></td></tr>
											<tr><td>${claimDetailForm.claimDetail.serviceProviderReferenceNumber}</td></tr>
											<tr><td height="10px"></td></tr>
											<c:if test="${claimDetailForm.claimDetail.requestLexmarkReviewFlag}">
											<tr><td><b><spring:message code="claim.label.reviewComments"/></b></td></tr>
											<tr><td height="10px"></td></tr>
											<tr><td>${claimDetailForm.claimDetail.reviewComments}</td></tr>
											</c:if>
										</table>
								</div>
							</div>
						</div>
						<div class="portlet-footer">
							<div class="portlet-footer-inner"></div>
						</div>
					</div>
			        <!-- Problem Information End -->    	 
			        
			        <!-- Technician Information Start -->       	 
					<div id ="divTechnicianInformation" class = "portlet-wrap">
						<div class="portlet-header dark-bar">
							<h3><spring:message code="claim.label.technicianInformation"/></h3>
						</div>
						<div class="portlet-wrap-inner">
							<div class="width-100per">
								<div class="columns two">
									<lable><b><spring:message code="claim.label.technician"/></b></lable>
									
										 ${claimDetailForm.claimDetail.technician.lastName}
										 <c:if test="${!empty claimDetailForm.claimDetail.technician.firstName}">
										 , 
										 </c:if>
										 ${claimDetailForm.claimDetail.technician.firstName}
									
								</div>
							</div>
						</div>
						<div class="portlet-footer">
							<div class="portlet-footer-inner"></div>
						</div>
					</div> 
					<!-- Technician Information End -->  	 
					
					
					<!-- Close Out Start -->   
					<c:if test="${!empty activity.debrief.debriefStatus}">
					<div id="closeOutActivity" class="portlet-wrap">
						<div class="portlet-header">
							<h3><spring:message code="claim.label.closeOutActivity" /></h3>
						</div>
						<div class="portlet-wrap-inner">
								<div class="width-100per">
										<table width="95%" border="1" borderColor="white" height="80%" class="table-style4" >
											<tr height="30">
										        <td align="right" width="20%"><B><spring:message code="claim.label.actualStartDate" /><B/></td>
										        <td align="left"  width="30%" class="padding-left-10px"><util:dateFormat value="${activity.debrief.serviceStartDate}" showTime="true" timezoneOffset="${timezoneOffset}"/></td>
										        <td align="left"  width="30%" class="padding-left-10px"><util:dateFormat value="${activity.debrief.serviceStartDate}" showTime="true" timezoneOffset="${timezoneOffset}"/></td>
										        <%-- <td align="right" width="20%"><B><spring:message code="claim.label.resolutionCode" /><B/></td> Commented for AIR 63551--%>
										        <td align="left" width="30%" class="padding-left-10px">${activity.resolutionCode.value}</td>
											</tr>
											<tr height="30">
										        <td align="right"><B><spring:message code="claim.label.actualEndDate" /></B></td>
										        <td align="left" class="padding-left-10px"><util:dateFormat value="${activity.debrief.serviceEndDate}" showTime="true" timezoneOffset="${timezoneOffset}"/></td>
										        <td align="right" valign="top" class="padding-5px"><B><spring:message code="claim.label.repairDescription" /></B></td>
										        <td align="left" valign="top" rowspan="4" class="padding-5px">${activity.debrief.repairDescription}</td>
											</tr>
											<tr height="30">
										        <td align="right"><B><spring:message code="claim.label.travelDistance" /></B></td>
										        <td align="left" class="padding-left-10px">${activity.debrief.travelDistance}&nbsp;&nbsp;${activity.debrief.travelUnitOfMeasure.name}</td>
												<td></td>
											</tr>
											<tr height="30">
										        <td align="right"><B><spring:message code="claim.label.travelDuration" /></B></td>
										        <td align="left" class="padding-left-10px">${activity.debrief.travelDuration}</td>
												
											</tr>
											<tr style="height:auto;"><td></td><td></td><td></td></tr>
										    <tr height="30">
										        <td align="right"><B><spring:message code="claim.label.problemCode" /></B></td>
										        <td align="left" class="padding-left-10px">${activity.debrief.actualFailureCode.name}</td>
												<td align="right"><B><spring:message code="claim.label.additionalServiceRequired" /></B></td>
										        <td align="left" class="padding-left-10px">${activity.debrief.debriefActionStatus}</td>
											</tr>
											<tr height="30">
										        <td align="right" valign="top" rowspan="3" class="padding-5px"><B><spring:message code="claim.label.problemDetails" /></B></td>
										        <td align="left"  valign="top" rowspan="3"  class="padding-5px">${activity.serviceRequest.problemDescription}</td>
										        <td align="right"><B><spring:message code="claim.label.nonLexmarkSuppliesUsed" /></B></td>
										        <td align="left" class="padding-left-10px">
										        <c:choose>
										        	<c:when test="${!activity.debrief.genuineLexmarkSuppliesUsedFlag}"><spring:message code="selection.label.yes" /></c:when>
										       		<c:otherwise><spring:message code="selection.label.No" /></c:otherwise>
										       	</c:choose>
										       	</td>
											</tr>											
											<tr height="30">
											<c:if test="${!activity.debrief.genuineLexmarkSuppliesUsedFlag}">
										        <td align="right"><B><spring:message code="claim.label.supplyManufacturer" /></B></td>
										        <td align="left" class="padding-left-10px">${activity.debrief.supplyManufacturer}</td>
												<td align="right"><br></br></td>
										        <td align="left"><br></br></td>
											</c:if>
											</tr>
											<tr>
												<td><br></br></td>
										        <td><br></br></td>
										        <td><br></br></td>
										        <td><br></br></td>
											</tr>
											<c:if test="${activity.debrief.installedAsset == null }">
											<tr height="30">
										        <td colspan="4" width="200" align="left" class="padding-left-10px"><h3><spring:message code="claim.label.repairedDeviceInformation" /></h3></td>
											</tr>
											<tr height="30">
										        <td align="right"><B><spring:message code="claim.label.printerWorkingCondition" /></B></td>
										        <td align="left" class="padding-left-10px">${activity.debrief.deviceCondition.name}</td>
										        <c:choose>
											        <c:when test="${activity.debrief.installedAsset == null }">
													<td align="right"><B><spring:message code="claim.label.networkConnected" /></B></td>
											        <td align="left" class="padding-left-10px">
													   <c:choose>
														<c:when test="${activity.serviceRequest.asset.networkConnectedFlag}"><spring:message code="selection.label.yes" /></c:when>
														<c:otherwise><spring:message code="selection.label.No" /></c:otherwise>
													   </c:choose>
													</td>
													</c:when>
													<c:otherwise>
														<td></td><td></td>
													</c:otherwise>
												</c:choose>
											</tr>
											<tr height="30">
										        <td align="right"><B><spring:message code="claim.label.pageCountAll" /></B></td>
										        <td align="left" class="padding-left-10px">${activity.serviceRequest.asset.newPageCount}</td>
										        <c:choose>
											        <c:when test="${activity.debrief.installedAsset == null }">
											        <td align="right"><B><spring:message code="claim.label.ipAddress" /></B></td>
											        <td align="left" class="padding-left-10px">${activity.serviceRequest.asset.ipAddress}</td>
											        </c:when>
											        <c:otherwise>
														<td></td><td></td>
													</c:otherwise>
												</c:choose>
											</tr>
											<tr height="30">
										        <td align="right"><B><spring:message code="claim.label.lexmarkTag" /></B></td>
										        <td align="left" class="padding-left-10px">${activity.serviceRequest.asset.deviceTag}</td>
										        <c:choose>
											        <c:when test="${activity.debrief.installedAsset == null }">
											        <td align="right"><B><spring:message code="claim.label.macAddress" /></B></td>
											        <td align="left" class="padding-left-10px">${activity.serviceRequest.asset.macAddress}</td>
											        </c:when>
											        <c:otherwise>
											        	<td></td><td></td>
											        </c:otherwise>
											    </c:choose>
											</tr>
											
											<tr height="30">
												<td><br></br></td>
										        <td><br></br></td>
												<td><br></br></td>
										        <td><br></br></td>
											</tr>
											</c:if>
											<c:if test="${activity.debrief.installedAsset != null }">
											<tr height="30">
										        <td colspan="4" width="200" align="left" class="padding-left-10px"><h3><spring:message code="claim.label.removedDeviceInformation" /></h3></td>
											</tr>
											<tr height="30">
										        <td align="right"><B><spring:message code="claim.label.printerWorkingCondition" /></B></td>
										        <td align="left" class="padding-left-10px">${activity.debrief.deviceCondition.name}</td>
										   
											</tr>
											<tr height="30">
										        <td align="right"><B><spring:message code="claim.label.pageCountAll" /></B></td>
										        <td align="left" class="padding-left-10px">${activity.serviceRequest.asset.newPageCount}</td>
										        
											</tr>
											<tr height="30">
										        <td align="right"><B><spring:message code="claim.label.lexmarkTag" /></B></td>
										        <td align="left" class="padding-left-10px">${activity.serviceRequest.asset.deviceTag}</td>
										        
											</tr>
											
											<tr height="30">
												<td><br></br></td>
										        <td><br></br></td>
												<td><br></br></td>
										        <td><br></br></td>
											</tr>
											<tr height="30">
										        <td colspan="4" width="200" align="left" class="padding-left-10px"><h3><spring:message code="claim.label.intalledDeviceInformation" /></h3></td>
											</tr>
											<tr height="30">
										       <td align="right"><B><spring:message code="claim.label.machineTypeModel" /></B></td>
										       <td align="left" class="padding-left-10px">${activity.debrief.installedAsset.modelNumber}</td>
										       <td align="right"><B><spring:message code="claim.label.networkConnected" /></B></td>
										       <td align="left" class="padding-left-10px">
										       <c:choose>
										       	<c:when test="${activity.debrief.installedAsset.networkConnectedFlag}"><spring:message code="selection.label.yes" /></c:when>
										       	<c:otherwise><spring:message code="selection.label.No" /></c:otherwise>
										       </c:choose>
										       </td>
											</tr>
											<tr height="30">
										        <td align="right"><B><spring:message code="claim.label.serialNumber" /></B></td>
										        <td align="left" class="padding-left-10px">${activity.debrief.installedAsset.serialNumber}</td>
										        <td align="right"><B><spring:message code="claim.label.ipAddress" /></B></td>
										        <td align="left" class="padding-left-10px">${activity.debrief.installedAsset.ipAddress}</td>
											</tr>
											<tr height="30">
										        <td align="right"><B><spring:message code="claim.label.pageCountAll" /></B></td>
										        <td align="left" class="padding-left-10px">${activity.debrief.installedAsset.newPageCount}</td>
										        <td align="right"><B><spring:message code="claim.label.macAddress" /></B></td>
										        <td align="left" class="padding-left-10px">${activity.debrief.installedAsset.macAddress}</td>
											</tr>
											<tr height="30">
										        <td align="right"><B><spring:message code="claim.label.lexmarkTag" /></B></td>
										        <td align="left" class="padding-left-10px">${activity.debrief.installedAsset.deviceTag}</td>
										        <td></td>
										        <td></td>
											</tr>
											</c:if>
										</table>
								</div>
						</div>	
						<div class="portlet-footer">
							<div class="portlet-footer-inner"></div>
						</div>	
					</div>
					</c:if>
					<!-- Close Out End -->
					<!-- Debrief Information Start--> 
					<c:if test="${!empty claimDetailForm.claimDetail.debrief.debriefStatus}">
						<div id ="divDebriefInformation" class = "portlet-wrap">
				 			<div class="portlet-header dark-bar">
								<h3><spring:message code="claim.label.debriefInformation"/></h3>
							</div>
							<div class="portlet-wrap-inner">
								<div class="width-100per">
									<div class="columns two">
										<dl>
											<dd><b><spring:message code="claim.label.repairDescription"/></b></dd>
											<dd>${claimDetailForm.claimDetail.debrief.repairDescription}</dd><br/>
											<dd><b><spring:message code="claim.label.resolutionCode"/></b></dd>
											<dd>${claimDetailForm.claimDetail.debrief.resolutionCode.name}</dd><br/>
											<!-- Added for CI BRD13-10-07 STARTS -->
											<dd><b><spring:message code="claim.label.dateServiceRequested"/></b></dd>
											<dd>${claimDetailForm.formattedServiceRequestedDate}</dd><br/>
											<!-- Added for CI BRD13-10-07 ENDS -->
											<dd><b><spring:message code="claim.label.dateServiced(Start)"/></b></dd>
											<dd>${claimDetailForm.formattedServiceStartDate}</dd><br/>
											<dd><b><spring:message code="claim.label.dateServiced(End)"/></b></dd>
											<dd>${claimDetailForm.formattedServiceEndDate}</dd><br/>
											<dd><b><spring:message code="claim.label.printerWorkingCondition"/></b></dd>
											<dd>${claimDetailForm.claimDetail.debrief.deviceCondition.name}</dd></br>
											<dd><b><spring:message code="claim.label.pageCountAll" /></b></dd>
											<dd>${claimDetailForm.pageCountAll}</dd>
										</dl>
									</div>
								</div>
							</div>
							<div class="portlet-footer">	
								<div class="portlet-footer-inner"></div>
							</div>
						</div>
					</c:if>
			        <!-- Debrief Information End-->  
			        
			        <!-- Parts and Tools Start-->  
			        <c:if test="${showRequestedPartsFlag || showOrderPartsFlag || showReturnPartsFlag}"> 
				        <div class = "portlet-wrap">
							<div class="portlet-header dark-bar">
							   <c:if test="${claimDetailForm.claimDetail.exchangeFlag=='false'}">
							   <h3><spring:message code="claim.label.partsAndTools"/></h3>
							   </c:if>
							    <c:if test="${claimDetailForm.claimDetail.exchangeFlag=='true'}">
							   <h3><spring:message code="claim.label.deviceToBeExchanged"/></h3>
							   </c:if>
							</div>
				       	 	<div class="portlet-wrap-inner">
				       	 		<div class="scroll">
					       	 		<c:if test="${showRequestedPartsFlag}">
					       	 			<div class="status-wrap">
						         	 		<div class="status-block">
						         	 			<h4><spring:message code="claim.label.requestedParts"/></h4>
												<div id="gridCDtlVRPRequest"  class="gridbox_light width-100per" ></div>
												<div class="pagination"><span id="gridCDtlVRPRequestPagingArea"></span>&nbsp;<span id="gridCDtlVRPRequestRecinfoArea"></span></div><!-- mygrid_container -->
											</div>
										</div>
										<script type="text/javascript">
											//jQuery(document).ready(function() {
												gridCDtlVRPRequestGrid = new dhtmlXGridObject('gridCDtlVRPRequest');
												gridCDtlVRPRequestGrid.setImagePath("<html:imagesPath/>gridImgs/");
												setHeaderForExchange(); //added by ragesree -2098
												//gridCDtlVRPRequestGrid.setHeader(autoAppendPlaceHolder('<spring:message code="claim.headerList.partsAndTools.detail"/>',5));
												//CI-6 Start
												//gridCDtlVRPRequestGrid.setColAlign("left,left,left,left,left");
												//gridCDtlVRPRequestGrid.setColTypes("ro,ro,ro,ro,ro");
												//gridCDtlVRPRequestGrid.setColSorting("str,str,str,str,str");
												//gridCDtlVRPRequestGrid.setInitWidths("140,140,140,140,540");
												//Changes for CI7 14-07-06 START
												gridCDtlVRPRequestGrid.setColAlign("left,left,left,left,left,left,left");
												gridCDtlVRPRequestGrid.setColTypes("ro,ro,ro,ro,ro,ro,ro");
												gridCDtlVRPRequestGrid.setColSorting("str,str,str,str,str,str,str");
												gridCDtlVRPRequestGrid.setLockColVisibility("false,false,false,false,false,false,false");
												gridCDtlVRPRequestGrid.setInitWidths("140,140,140,140,140,140,140");
												//Changes for CI7 14-07-06 ENDS
												//CI-6 End
												gridCDtlVRPRequestGrid.enableAutoWidth(true);
												gridCDtlVRPRequestGrid.enableAutoHeight(true);
												gridCDtlVRPRequestGrid.enableMultiline(true);
												gridCDtlVRPRequestGrid.enablePaging(true, 6, 10, "gridCDtlVRPRequestPagingArea", true, "gridCDtlVRPRequestRecinfoArea");
												gridCDtlVRPRequestGrid.init();
												gridCDtlVRPRequestGrid.setSkin("light");
												// move enabled for CI 14.10 CRM-DAmster201408041344
												gridCDtlVRPRequestGrid.enableColumnMove(true);
												gridCDtlVRPRequestGrid.setPagingSkin("bricks");		
												gridCDtlVRPRequestGrid.loadXMLString("${claimDetailForm.pendingPartListXML}");
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
											//});
											
											//added by ragesree -2098
											function setHeaderForExchange(){
												if(exchangeflag==true || exchangeflag=='true'){
													gridCDtlVRPRequestGrid.setHeader(autoAppendPlaceHolder('<spring:message code="claim.headerList.deviceExchange.detail.expedite"/>',7));
												}else{
													gridCDtlVRPRequestGrid.setHeader(autoAppendPlaceHolder('<spring:message code="claim.headerList.partsAndTools.detail.expedite"/>',7));
												}
											}
										</script>
									</c:if>
									<c:if test="${showOrderPartsFlag && claimDetailForm.claimDetail.exchangeFlag=='false'}">
										<div class="status-wrap">
						         	 		<div class="status-block">
						         	 			<h4><spring:message code="claim.label.ordered.parts"/></h4>
												<div id="gridCDtlVOPRequest" class="gridbox_light width-100per" ></div>
												<div class="pagination"><span id="gridCDtlVOPRequestPagingArea"></span>&nbsp;<span id="gridCDtlVOPRequestRecinfoArea"></span></div><!-- mygrid_container -->
											</div>
										</div>
										<script type="text/javascript">
											//jQuery(document).ready(function() {
												//Changes for CI7 14-07-06
												gridCDtlVOPRequestGrid = new dhtmlXGridObject('gridCDtlVOPRequest');
												gridCDtlVOPRequestGrid.setImagePath("<html:imagesPath/>gridImgs/");
												gridCDtlVOPRequestGrid.setHeader(autoAppendPlaceHolder('<spring:message code="claim.headerList.orderedParts.detail"/>',12));
												gridCDtlVOPRequestGrid.setColAlign("left,left,left,left,left,left,left,left,left,left,left,left,left");
												gridCDtlVOPRequestGrid.setColTypes("sub_row,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro");
												gridCDtlVOPRequestGrid.setColSorting("na,str,str,str,str,str,str,str,str,str,str,str,str");
												gridCDtlVOPRequestGrid.setLockColVisibility("false,false,false,false,false,false,false,false,false,false,false,false,false");
												gridCDtlVOPRequestGrid.setInitWidths("20,80,50,80,80,80,80,80,80,80,80,120,50");					
												//gridCDtlVOPRequestGrid.enableAutoWidth(true);
												gridCDtlVOPRequestGrid.enableAutoHeight(true);
												gridCDtlVOPRequestGrid.enableMultiline(true);
												gridCDtlVOPRequestGrid.enablePaging(true, 5, 10, "gridCDtlVOPRequestPagingArea", true, "gridCDtlVOPRequestRecinfoArea");
												gridCDtlVOPRequestGrid.init();
												gridCDtlVOPRequestGrid.setSkin("light");	
												gridCDtlVOPRequestGrid.setPagingSkin("bricks");
												// move enabled for CI 14.10 CRM-DAmster201408041344
												gridCDtlVOPRequestGrid.enableColumnMove(true);
												gridCDtlVOPRequestGrid.setColumnHidden(12,true);
												gridCDtlVOPRequestGrid.loadXMLString("${claimDetailForm.orderPartListXML}");

												// changes for db saving and retrieving	start
												//gridCDtlVOPRequestGrid.enableAutoSavingToDB("${saveCustomizedGridSettingURL}");
												gridCDtlVOPRequestGrid.attachEvent("onAfterCMove",function(a,b){
													gridCDtlVOPRequestGrid.enableAutoSavingToDB("${saveCustomizedGridSettingURL}");
													//});	
												});	
												<c:forEach var="gridSettingVar" items="${gridSettingList}" varStatus="status">
												<c:if test="${gridSettingVar.gridId == 'gridCDtlVOPRequest'}">					
												var colsOrder = "${gridSettingVar.colsOrder}";
												gridCDtlVOPRequestGrid.loadOrder(colsOrder);
												
												</c:if>
												</c:forEach>
												// changes for db saving and retrieving	end
												
											//});
										</script>
									</c:if>
									<c:if test="${showReturnPartsFlag}">
										<div class="status-wrap">
						         	 		<div class="status-block">
						         	 		     <c:if test="${claimDetailForm.claimDetail.exchangeFlag=='false'}">
						         	 		     	<h4><spring:message code="claim.label.parts.to.be.returned"/></h4>
						         	 		     </c:if>
						         	 			 <c:if test="${claimDetailForm.claimDetail.exchangeFlag=='true'}">
						         	 		     	<h4><spring:message code="claim.label.deviceToBeReturned"/></h4>
						         	 		     </c:if>
												<div id="gridCDtlVBRPRequest" class="gridbox_light width-100per" ></div>
												<div><span id="gridCDtlVBRPRequestPagingArea"></span>&nbsp;<span id="gridCDtlVBRPRequestRecinfoArea"></span></div><!-- mygrid_container -->
											</div>
										</div>
										<script type="text/javascript">
										//	jQuery(document).ready(function() {
												
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
												// move enabled for CI 14.10 CRM-DAmster201408041344
												gridCDtlVBRPRequestGrid.enableColumnMove(true);
												gridCDtlVBRPRequestGrid.setPagingSkin("bricks");	
												gridCDtlVBRPRequestGrid.loadXMLString("${claimDetailForm.returnPartListXML}");	

												// changes for db saving and retrieving	start
												//gridCDtlVBRPRequestGrid.enableAutoSavingToDB("${saveCustomizedGridSettingURL}");
												gridCDtlVBRPRequestGrid.attachEvent("onAfterCMove",function(a,b){
													gridCDtlVBRPRequestGrid.enableAutoSavingToDB("${saveCustomizedGridSettingURL}");
													//});	
												});	
												<c:forEach var="gridSettingVar" items="${gridSettingList}" varStatus="status">
												<c:if test="${gridSettingVar.gridId == 'gridCDtlVBRPRequest'}">					
												var colsOrder = "${gridSettingVar.colsOrder}";
												gridCDtlVBRPRequestGrid.loadOrder(colsOrder);
												
												</c:if>
												</c:forEach>
												// changes for db saving and retrieving	end
												
											//});
											
											//added by ragesree -2098
											function setHeaderForPartsToBeReturned(){
												if(exchangeflag==true || exchangeflag=='true'){
													gridCDtlVBRPRequestGrid.setHeader(autoAppendPlaceHolder(' ,<spring:message code="claim.headerList.deviceToBeReturned"/>',9));
												}else{
													gridCDtlVBRPRequestGrid.setHeader(autoAppendPlaceHolder(' ,<spring:message code="claim.headerList.partsToBeReturned"/>',9));
												}
											}
										</script>
									</c:if>
								</div>
							</div>
							<div class="portlet-footer">
								<div class="portlet-footer-inner"></div>
							</div>
						</div>
					</c:if>
			        <!-- Parts and Tools End-->  
			        
			        <!-- Additional Payment Requests Start-->  
			        <c:if test="${showAdditionalPaymentRequestListFlag}">   	 
						<div class = "portlet-wrap">
							<div class="portlet-header dark-bar">
								<h3><spring:message code="claim.label.additionalPaymentRequests"/></h3>
							</div>
							<div class="portlet-wrap-inner">
								<div class="scroll">
									<div class="columns two">
										<div id="gridCDtlAPRequest" class="width-100per"></div>
										<div><span id="gridCDtlAPRequestPagingArea"></span>&nbsp;<span id="gridCDtlAPRequestRecinfoArea"></span></div><!-- mygrid_container -->
									</div>
								</div>
							</div>
				       	 	<div class="portlet-footer">
				       	 		<div class="portlet-footer-inner"></div>
				       	 	</div>
						</div>
						
						<script type="text/javascript">
							//jQuery(document).ready(function() {
								gridCDtlAPRequestGrid = new dhtmlXGridObject('gridCDtlAPRequest');
								gridCDtlAPRequestGrid.setImagePath("<html:imagesPath/>gridImgs/");
								gridCDtlAPRequestGrid.setHeader(autoAppendPlaceHolder('<spring:message code="claim.headerList.additionalPaymentRequests"/>',6));
								gridCDtlAPRequestGrid.setColAlign("left,left,left,left,left,left");
								gridCDtlAPRequestGrid.setColTypes("ro,ro,ro,ro,ro,ro");
								gridCDtlAPRequestGrid.setColSorting("str,str,str,str,str,str");
								gridCDtlAPRequestGrid.setInitWidths("140,140,140,140,140,*");
								//gridCDtlAPRequestGrid.enableAutoWidth(true);
								gridCDtlAPRequestGrid.enableAutoHeight(true);
								gridCDtlAPRequestGrid.enableMultiline(true);
								gridCDtlAPRequestGrid.enablePaging(true, 5, 10, "gridCDtlAPRequestPagingArea", true, "gridCDtlAPRequestRecinfoArea");
								gridCDtlAPRequestGrid.init();
								gridCDtlAPRequestGrid.setSkin("light");
								gridCDtlAPRequestGrid.setPagingSkin("bricks");
								gridCDtlAPRequestGrid.loadXMLString("${claimDetailForm.additionalPaymentRequestListXML}");	
							//});	
						</script>
					</c:if>
			        <!-- Additional Payment Requests End-->            	
			        
					<!-- Notes Start-->     	
					<div class = "portlet-wrap">
						<div class="portlet-header dark-bar">
							<h3><spring:message code="claim.label.notes"/></h3>
						</div>
						<div class="portlet-wrap-inner">
							<div class="width-100per">
								<div class="columns two">
									<div class="width-100per" id="gridCDtlVNRequest"></div>
									<div class="pagination"><span id="gridCDtlVNRequestPagingArea"></span>&nbsp;<span id="gridCDtlVNRequestRecinfoArea"></span></div><!-- mygrid_container -->
								</div>
							</div>
						</div>
						<div class="portlet-footer">
							<div class="portlet-footer-inner"></div>
						</div>
					</div>
					
					<script type="text/javascript">
						//jQuery(document).ready(function() {
							gridCDtlVNRequestGrid = new dhtmlXGridObject('gridCDtlVNRequest');
							gridCDtlVNRequestGrid.setImagePath("<html:imagesPath/>gridImgs/");
							gridCDtlVNRequestGrid.setHeader(autoAppendPlaceHolder('<spring:message code="claim.headerList.notes.detail"/>',4));
							gridCDtlVNRequestGrid.setColAlign("left,left,left,left");
							gridCDtlVNRequestGrid.setColTypes("sub_row,ro,ro,ro");
							gridCDtlVNRequestGrid.setColSorting("na,str,str,str");
							gridCDtlVNRequestGrid.setInitWidths("20,150,150,*");
							gridCDtlVNRequestGrid.enableAutoWidth(true);
							gridCDtlVNRequestGrid.enableAutoHeight(true);
							gridCDtlVNRequestGrid.enableMultiline(true);
							gridCDtlVNRequestGrid.init();
							gridCDtlVNRequestGrid.setSkin("light");
							gridCDtlVNRequestGrid.enablePaging(true, 5, 10, "gridCDtlVNRequestPagingArea", true, "gridCDtlVNRequestRecinfoArea");
							gridCDtlVNRequestGrid.setPagingSkin("bricks");
							gridCDtlVNRequestGrid.loadXMLString("${claimDetailForm.activityNoteListXML}");	
						//});
					</script>
					<!-- Notes End-->   
					
					<!-- Email Notification started-->
					<div id = includeEmailNotification> 
						<jsp:include page="/WEB-INF/jsp/claims/emailNotificationsDisplay.jsp" flush="true"/>
					</div>
					<!-- Email Notification End-->
					<!-- Attachment started -->
					<div class="portlet-wrap" id="attachmentBorder">
				        	<div class="portlet-header">
								<h3><spring:message code="claim.label.attachments" /></h3>
							</div>
							<br><br>
					<div id='uploadedFileDiv' class="portlet-wrap-inner">
						<table id="fileListTable">
						</table>
					</div>
					<div class="portlet-footer">	<div class="portlet-footer-inner"></div></div>
					<script type="text/javascript">
					<c:if test="${claimDetailForm.attachmentList != null || not empty claimDetailForm.attachmentList}">
					jQuery('#fileListTable',window.parent.document).empty();
					var responseText = '<thead><tr class="tableHeader"><b><th class="width60"><spring:message code="attachment.message.FileName"/></th><th class="width35"><spring:message code="attachment.message.FileSize"/></th></b></tr></thead>';
					   responseText = responseText + '<tbody>';
				 		<c:forEach items="${claimDetailForm.attachmentList}" var="entry">
				 			responseText = responseText + '<tr class="tableContentColor">';
				 			responseText = responseText + '<td class="width60" >'+ '<a href="#" onClick=\'outPutFile("${entry.attachmentName}","${entry.activityId}","${entry.actualFileName}","${entry.identifier}","${entry.type}") ;\'><c:out value="${entry.attachmentName}" /></a>' + '</td>';
				 			responseText = responseText + '<td align="center" class="width35">'+ '<c:out value="${entry.sizeForDisplay}" />' + '</td>';
				 			responseText = responseText + '</tr>';
				 			
						</c:forEach>
						responseText = responseText + '</tbody>';
						jQuery('#fileListTable',window.parent.document).append(responseText);
				</c:if>
				
				<c:if test="${claimDetailForm.attachmentList == null || empty claimDetailForm.attachmentList}">
					jQuery('#fileListTable',window.parent.document).empty();
					jQuery('#attachmentBorder').hide();
				</c:if>
					</script>
					</div>
					<!-- Attachment End -->
				</div><!-- content -->
			</div>
		</td>
	</tr>
</table>
<script type="text/javascript">
function outPutFile(userFileName,activityId,actualFileName,identifier,type){
        window.open("${outPutFileURL}&userFileName=" + userFileName + "&activityId=" + activityId+ "&actualFileName=" + actualFileName+ "&identifier=" + identifier+ "&type=" + type, "");
};
</script>

<script type="text/javascript">
//---- Ominture script 
     var portletName = "Claim Detail View";
     addPortlet(portletName);
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
</script>