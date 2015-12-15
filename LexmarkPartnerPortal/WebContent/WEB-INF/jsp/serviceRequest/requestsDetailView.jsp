<%@ include file="/WEB-INF/jsp/include.jsp"%>
<%@ include file="/WEB-INF/jsp/includeGrid.jsp"%>
<script src='<html:rootPath/>js/trackingNumber.js'></script>
<%@ page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>
<style type="text/css"><%@ include file="/WEB-INF/css/ci6.css" %></style>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/mps.css?version=<html:fileCacheVersion/>" />
<!--[if IE 7]>
	<style type="text/css"><%@ include file="/WEB-INF/css/mps_ie7.css" %></style>
<![endif]-->
<style type="text/css">
.displayGrid{line-height:20px;}
/* Changes for CI7 BRD 14-07-06 */
.pagination span#gridRDVRecommendedPartsPagingArea>div { width:100%!important }
.pagination span#gridRDVnotesPagingArea>div { width:100%!important }
.pagination span#gridRDVbeReturnedPartsPagingArea>div { width:100%!important }
.pagination span#gridRDVOrderedPartsTablePagingArea>div { width:100%!important }
</style>
<style>
<!--
added for blue bars
-->
div.portlet-header {
	background: url(/LexmarkPartnerPortal/images/portlet-topleft.png) no-repeat top left;
	height: 30px;
	margin: 0;
	padding: 0;}


div.portlet-der h3 {
	background: url(/LexmarkPartnerPortal/images/portlet-topright.png) no-repeat top right;
	height: 30px;
	width: 100%;
	margin: 0;
	padding: 0;
	font-size: 16px;
	line-height: 37px;
	text-indent: 15px;
	color: #fff;
	font-weight: normal;
	}	
	
* html div.portlet-header h3 {	
	line-height: 30px;
}
</style>
<c:set var="activity" value="${serviceRequestDetailForm.activity}"/>

<portlet:renderURL  var="updateRequestUrl">
	<portlet:param name="action" value="showUpdateRequestPage" />
	<portlet:param name="serviceRequestId" value="${activity.serviceRequest.id}" />
	<portlet:param name="activityId" value="${activity.activityId}" />
	<portlet:param name="requestFromPage" value="requestsDetailView" />
</portlet:renderURL>

<portlet:renderURL  var="debriefRequestUrl">
	<portlet:param name="action" value="showRequestsDebriefPage" />
	<portlet:param name="serviceRequestId" value="${activity.serviceRequest.id}" />
	<portlet:param name="activityId" value="${activity.activityId}" />
	<portlet:param name="fromPage" value="requestDetailView" />
</portlet:renderURL>

<portlet:renderURL var="printServiceRequestDetailUrl"
	windowState='<%= LiferayWindowState.POP_UP.toString() %>'>
	<portlet:param name='action' value='printServiceRequestDetail' />
</portlet:renderURL>

<portlet:resourceURL var="downServiceRequestDetailPdfURL" id="downServiceRequestDetailPdfURL">
	<portlet:param name="serviceRequestId" value="${activity.serviceRequest.id}" />
	<portlet:param name="activityId" value="${activity.activityId}" />
	<portlet:param name="timezoneOffset" value="${timezoneOffset}" />
</portlet:resourceURL>

<c:set var="serviceRequestId" value="${activity.serviceRequest.id}"/>
<c:set var="emailAddress" value="${activity.emailAddress}"/>
<style type="text/css">
.displayGrid{line-height:20px}

</style>
<script type="text/javascript">
	function print(){
		callOmnitureAction('Requests Detail View', 'Request Detail Print');
			var url="${printServiceRequestDetailUrl}";
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
		callOmnitureAction('Requests Detail View', 'Request Detail PDF');
		window.location.href = "${downServiceRequestDetailPdfURL}";
	}

	var forPrintGridObjects=[];
</script>
<!-- <div class="journal-content-article">
      <h2>Payments</h2>
</div> -->
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/in-styles.css?version=<html:fileCacheVersion/>" />

<table width="100%">
	<tr>
		<td>
			<div class="main-wrap width100per-height100per" >
				<div class="content">
					<div class="grid-controls">
						<div class="utilities">
							<ul>
								<li class="first"><img src="<html:imagesPath/>transparent.png" class="ui-icon print-icon cursor-pointer" onclick="print()" height="23px" width="23px" title="<spring:message code='image.title.print'/>"></li>
								<li>
									<img src="<html:imagesPath/>transparent.png" class="ui-icon pdf-icon cursor-pointer" height="23px" width="23px" onClick="downloadPdf()" title="<spring:message code='image.title.exportPDF'/>"; />
								</li>
							</ul>
						</div>
						<div class="expand-min">
							<ul>
								<li class="first">						
									<c:choose>
										<c:when test="${empty from}">
							    			<a class="button anchor-style3" href="${returnURL}&back=true">&lt;&lt;<spring:message code="link.return.to.requests"/></a>
							    		</c:when>
							    		<c:when test='${from == "paymentDetail"}'>
							    			<a class="button anchor-style3" href="${returnURL}&back=true">&lt;&lt;<spring:message code="link.return.to.paymentDetails"/></a>
							    		</c:when>
							    		<c:when test='${from == "paymentRequestList"}'>
							    			<a class="button anchor-style3" href="${returnURL}&back=true">&lt;&lt;<spring:message code="link.return.to.paymentRequestList"/></a>
							    		</c:when>					    		
						    		</c:choose>
			           	 		</li>
			           	 	</ul>						    		
						</div>
						<div class="clear-right"></div><!-- clear -->	
					</div>
				
					<div id="requestInformation" class="portlet-wrap">
						<div class="portlet-header">
						<table class="displayGrid">
              <thead>
                <tr><th><spring:message code="claim.label.RequestInformation" /></th>
                </tr></thead>
              
            </table>
						
						</div>
						<div class="portlet-wrap-inner">
							<div class="width-100per">
								<div class="columns two">
									<c:if test="${empty from && (showCloseButtonFlag || showUpdateButtonFlag)&& (isTechnician || isServiceMgr)}"><%-- Modified as part of MPS--%>
										<div class="first-column">
											<dl>
												</br>
												
												
												<c:if test="${showUpdateButtonFlag && activity.activityStatus.name!='Cancelled Service'}">
												<dt><a href="javascript:updateRequest();" class="button"><span><spring:message code="claim.button.updateRequest" /></span></a></dt>
												</c:if>
												
												<c:if test="${showCloseButtonFlag && activity.activityStatus.name!='Cancelled Service'}">
												<dt><a href="javascript:debriefRequest();" class="button"><span><spring:message code="claim.button.closeOutRequest" /></span></a></dt>
												</c:if>
												</br>
											</dl>
										</div>
									</c:if>
									<div class="second-column">
										<table>
											<tr>
												<td></td>
												<td></td>
											</tr>
											<tr height="25px">
												<td   align="right"><B><spring:message code="claim.label.requestNumber" /></B></td>
												<td width="20px"/></td>
												<td>${activity.serviceRequest.serviceRequestNumber}</td>
											</tr>
											<tr height="25px">
												<td  align="right"><B><spring:message code="claim.label.serviceProviderReferenceNumber" /></B></td>
												<td width="20px"/></td>
												<td>${activity.serviceRequest.referenceNumber}</td>
											</tr>
											<tr height="25px">
												<td   align="right"><B><spring:message code="claim.label.openedDateTime" /></B></td>
												<td width="20px"/></td>
												<td><util:dateFormat value="${activity.serviceRequest.serviceRequestDate}" showTime="true" timezoneOffset="${timezoneOffset}"/></td>
											</tr>
											<tr height="25px">
												<td  align="right"><B><spring:message code="claim.label.requestStatus" /></B></td>
												<td width="20px"/></td>
												<td>${activity.activityStatus.name}</td>
											</tr>
											<tr height="25px">
												<td  align="right"><B><spring:message code="claim.label.statusDetail" /></B></td>
												<td width="20px"/></td>
												<td>${activity.activitySubStatus.name}</td>
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
				
					<div id="deviceInformation" class="portlet-wrap">
						<div class="portlet-header">
						<table class="displayGrid">
              <thead>
                <tr><th><spring:message code="claim.label.deviceInformation" /></th>
                </tr></thead>
              
            </table>
						
						</div>
						<div class="portlet-wrap-inner">
							<div class="width-100per">
								<div class="columns three">
									<div class="first-column">
										<dl>
					                        <dd>
												<!--Change Reverted for CI Defect # 10118 -->
					                           <H3>${activity.serviceRequest.asset.productLine}</H3>
					                        </dd>
					                        <dd>
					                           <div class="icon" id="manImage">
					                              <img width="75" src="${activity.serviceRequest.asset.productImageURL}"  />
					                        	</div>
					                        </dd>
					                     </dl>
									</div>
									<div class="second-column">
										<br/>
										<br/>
										<table border="0">
											<tr>
											 	<td width="150px" align="right"><b><spring:message code="claim.label.serialNumber"/></b></td>
												<td width="20px" />
											  	<td class="labelTD">${activity.serviceRequest.asset.serialNumber}</td>
											</tr>	 
											<tr>
												<td class="labelTD" align="right"><b><spring:message code="claim.label.machineTypeModel"/></b></td>
												<td/>
												<td class="labelTD">${activity.serviceRequest.asset.modelNumber}</td>
											</tr>
											<tr>
												<td class="labelTD" align="right"><b><spring:message code="claim.label.productPN/TLI"/></b></td>
												<td/>
												<td class="labelTD">${activity.serviceRequest.asset.productTLI}</td>
											</tr>
										</table>
									</div>
									<div class="third-column">
									</div>
								</div>
							</div>
						</div>
						<div class="portlet-footer">
							<div class="portlet-footer-inner"></div>
						</div>
					</div>
				
				
					<!-- Service History Start -->
				 	<c:set var="assetId" value="${activity.serviceRequest.asset.assetId}"></c:set> 
				 	<c:set var="serviceRequestId" value="${activity.serviceRequest.id}"></c:set>
					<%@ include file="/WEB-INF/jsp/request/serviceRequestHistoryList.jsp"%>
			        <!-- Service History End -->
				
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
										<table width="100%" class="table-style8">			
											<tr>
												<td></td>
												<td width="20px"/></td>
												<td><u><B><spring:message code="claim.label.customerAccount" /></B></u></td>
											</tr>
											<tr>
												<td  align="right"><B><spring:message code="claim.label.name" /></B></td>
												<td width="20px"/></td>
												<td>${activity.customerAccount.accountName}</td>
											</tr>
											<tr>
												<td  align="right" valign="top"><B><spring:message code="claim.label.address" /></B></td>
												<td width="20px"/></td>
												<td valign="bottom">
													<util:addressOutput value="${activity.customerAccount.address}"/>
												</td>
											</tr>
											<tr>
												<td  align="right" valign="top"><B><spring:message code="claim.label.helpDeskReference" /></B></td>
												<td width="20px"/></td>
												<td valign="bottom">${activity.serviceRequest.customerReferenceNumber}</td>
											</tr>
										</table>
									</div>
									<div class="second-column">
										 <table width="90%" class="table-style9">			
											<tr>
												<td></td>
												<td width="20px"/></td>
												<td><u><B><spring:message code="claim.label.primaryContact" /></B></u></td>
											</tr>
											<tr>
												<td  align="right"><B><spring:message code="claim.label.name" /></B></td>
												<td width="20px"/></td>
												<td>${activity.serviceRequest.primaryContact.firstName} ${activity.serviceRequest.primaryContact.lastName}</td>
											</tr>
											<tr>
												<td  align="right"><B><spring:message code="claim.label.phone" /></B></td>
												<td width="20px"/></td>
												<td>${activity.serviceRequest.primaryContact.workPhone}</td>
											</tr>
											<tr>
												<td  align="right"><B><spring:message code="requestInfo.contactInfo.label.alternatePhone" /></B></td>
												<td width="20px"/></td>
												<td class="word-wrap-break-word">${activity.serviceRequest.primaryContact.alternatePhone}</td>
											</tr>
											<tr>
												<td  align="right"><B><spring:message code="claim.label.email" /></B></td>
												<td width="20px"/></td>
												<td class="word-wrap-break-word">${activity.serviceRequest.primaryContact.emailAddress}</td>
											</tr>
										</table>
									</div>
									<div class="third-column">
										<table width="90%" class="table-style9">			
											<tr>
												<td></td>
												<td width="20px"/></td>
												<td><u><B><spring:message code="claim.label.secondaryContact" /></B></u></td>
											</tr>
											<tr>
												<td align="right"><B><spring:message code="claim.label.name" /></B></td>
												<td width="20px"/></td>
												<td>${activity.serviceRequest.secondaryContact.firstName} ${activity.serviceRequest.secondaryContact.lastName}</td>
											</tr>
											<tr>
												<td  align="right" class="top-0px"><B><spring:message code="claim.label.phone" /></B></td>
												<td width="20px"/></td>
												<td>${activity.serviceRequest.secondaryContact.workPhone}</td>
											</tr>
											<tr>
												<td  align="right" class="top-0px"><B><spring:message code="requestInfo.contactInfo.label.alternatePhone" /></B></td>
												<td width="20px"/></td>
												<td class="word-wrap-break-word">${activity.serviceRequest.secondaryContact.alternatePhone}</td>
											</tr>
											<tr>
												<td  align="right" class="top-0px"><B><spring:message code="claim.label.email" /></B></td>
												<td width="20px"/></td>
												<td class="word-wrap-break-word">${activity.serviceRequest.secondaryContact.emailAddress}</td>
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
			
			
					<div id="problemInformation" class="portlet-wrap">
						<div class="portlet-header">
						<table class="displayGrid">
              <thead>
                <tr><th><spring:message code="claim.label.problemInformation" /></th>
                </tr></thead>
              
            </table>
						
						</div><!-- portlet-header -->
						<div class="portlet-wrap-inner">
							<div class="width-100per">
								<div class="columns two">
									<div class="first-column">
										<dl>
											<dd><label><spring:message code="claim.label.problemCode" /></label></dd>
											<dd class="padding-left-5px">${activity.actualFailureCode.name}</dd>
											<br>
											<dd><label><spring:message code="claim.label.problemDetails" /></label></dd>
											<dd class="padding-left-5px">${activity.serviceRequest.problemDescription}</dd>
											<br>
											<c:if test="${!empty activity.reviewComments}">
											<dd><label><spring:message code="claim.label.reviewComments" /></label></dd>
											<dd class="padding-left-5px">${activity.reviewComments}</dd>
											<br>
											</c:if>
										</dl>
									</div><!-- first-column -->
								</div>
							</div>
						</div><!-- portlet-wrap-inner -->
						<div class="portlet-footer">
							<div class="portlet-footer-inner"></div><!-- portlet-footer-inner -->
						</div><!-- portlet-footer -->
					</div>
				
					<div id="serviceInformation" class="portlet-wrap">
						<div class="portlet-header">
						<table class="displayGrid">
              <thead>
                <tr><th><spring:message code="claim.label.serviceInformation" /></th>
                </tr></thead>
              
            </table>
							
						</div>
						<div class="portlet-wrap-inner">
							<div class="width-100per">
								<table width="90%" class="table-style4">
									<tr height="30">
										<c:choose>
											<c:when test="${activity.responseMetric != null}">
												<td width="25%" valign="top" align="right"><B><spring:message code="claim.label.respondWithin" /></B></td>
												<td width="25%" valign="top" class="padding-left-10px">${activity.responseMetric}</td>
											</c:when>
											<c:when test="${activity.resolutionMetric != null}">
												<td width="25%" valign="top" align="right"><B><spring:message code="claim.label.mustResolveWithin" /></B></td>
												<td width="25%" valign="top" class="padding-left-10px">${activity.resolutionMetric}</td>
											</c:when>
										</c:choose>
										<td width="25%" valign="top" align="right"><B><spring:message code="claim.label.serviceActivityWithin30Days"/></B></td>
										<td width="25%" valign="top" class="padding-left-10px">${activity.serviceActivityWithin30Days}</td>
									</tr>
									<tr height="30">
										<c:choose>
											<c:when test="${activity.responseMetric != null}">
												<td width="25%" valign="top" align="right"><B><spring:message code="claim.label.customerRequestedResponse" /></B></td>
												<td width="25%" valign="top" class="padding-left-10px"><util:dateFormat value="${activity.customerRequestedResponseDate}" showTime="true" timezoneOffset="${timezoneOffset}"/></td>
											</c:when>
											<c:when test="${activity.resolutionMetric != null}">	
												<td width="25%" valign="top" align="right"><B><spring:message code="claim.label.mustResolveBy" /></B></td>
												<td width="25%" valign="top" class="padding-left-10px"><util:dateFormat value="${activity.resolutionDate}" showTime="true" timezoneOffset="${timezoneOffset}"/></td>
											</c:when>
										</c:choose>	
										<td width="25%" valign="top" align="right"><B><spring:message code="claim.label.serviceProviderAttemptNumber" /></B></td>
										<td width="25%" valign="top" class="padding-left-10px">${activity.serviceProviderAttemptNumber}</td>
									</tr>
									<c:if test="${activity.responseMetric != null && activity.resolutionMetric != null}">
									<tr height="30">
										<td width="25%" valign="top" align="right"><B><spring:message code="claim.label.mustResolveWithin" /></B></td>
										<td width="25%" valign="top" class="padding-left-10px">${activity.resolutionMetric}</td>
										<!--CI BRD13-10-21 STARTS-->
										<td width="25%" valign="top" align="right"><B><spring:message code="claim.label.lastUpdatedComment" /></B></td>
										<td width="25%" valign="top" class="padding-left-10px">${activity.servicerComments}</td>
										<!-- CI BRD13-10-21 Ends -->
									</tr>
									<tr height="30">
										<td width="25%" valign="top" align="right"><B><spring:message code="claim.label.mustResolveBy" /></B></td>
										<td width="25%" valign="top" class="padding-left-10px"><util:dateFormat value="${activity.resolutionDate}" showTime="true" timezoneOffset="${timezoneOffset}"/></td>
									</tr>
									</c:if>
									<tr height="30">
										<td width="25%" valign="top" align="right"><B><spring:message code="claim.label.problemDescription" /></B></td>
										<td width="75%" valign="top" colspan=3 class="padding-left-10px">${activity.serviceSummary}</td>
									</tr>
									<tr height="30">
										<td width="25%" valign="top" align="right"><B><spring:message code="claim.label.accountSpecialHandling" /></B></td>
										<td width="75%" valign="top" colspan=3 class="padding-left-10px">${activity.accountSpecialHandling}</td>
									</tr>
									<tr height="30">
										<td width="25%" valign="top" align="right"><B><spring:message code="claim.label.productAndAssetWarningMessage" /></B></td>
										<td width="75%" valign="top" colspan=3 class="padding-left-10px">${activity.assetWarningMessage}</td>
									</tr>
								</table>
							</div>
						</div>
						<div class="portlet-footer">
							<div class="portlet-footer-inner"></div>
						</div>
					</div>
					
					
					<!-- Close Out Activity start -->
					<c:if test="${!empty activity.debrief.debriefStatus}">
					<div id="closeOutActivity" class="portlet-wrap">
						<div class="portlet-header">
						<table class="displayGrid">
              <thead>
                <tr><th><spring:message code="claim.label.closeOutActivity" /></th>
                </tr></thead>
              
            </table>
							
						</div>
						<div class="portlet-wrap-inner">
								<div class="width-100per">
										<table width="95%"  height="80%" class="table-style4">
											<tr height="30">
										        <td align="right" width="20%"><B><spring:message code="claim.label.actualStartDate" /><B/></td>
										        <td align="left"  width="30%" class="padding-left-10px"><util:dateFormat value="${activity.debrief.serviceStartDate}" showTime="true" timezoneOffset="${timezoneOffset}"/></td>
										        <td align="right" width="20%"><B><spring:message code="claim.label.resolutionCode" /><B/></td>
										        <%-- <td align="left" width="30%" class="padding-left-10px">${activity.debrief.resolutionCode.name}</td>  Commented for AIR 63551--%>
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
											<tr class="height-auto"><td></td><td></td><td></td></tr>
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
										        <td colspan="4"  align="left" class="padding-left-10px"><h3><spring:message code="claim.label.repairedDeviceInformation" /></h3></td>
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
					<!-- Close Out Activity end -->
					
					
					<div id="technicianInformation" class="portlet-wrap">
						<div class="portlet-header">
						<table class="displayGrid">
              <thead>
                <tr><th><spring:message code="claim.label.technicianInformation"/></th>
                </tr></thead>
              
            </table>
							
						</div>
						<div class="portlet-wrap-inner">
							<div class="width-100per">
								<table width="40%" class="table-style10">
									<tr height="30">
										<td width="20%"><B><spring:message code="claim.label.technician"/></B></td>
										<td width="80%">
											${activity.technician.lastName} 
											<c:if test="${!empty activity.technician.firstName}">,</c:if>
											${activity.technician.firstName}
										</td>
									</tr>
								</table>
							</div>
						</div>
						<div class="portlet-footer">
							<div class="portlet-footer-inner"></div>
						</div>
					</div>
					
										
					<!-- Technician Instructions Start -->
					<c:if test="${serviceRequestDetailForm.technicianInformationListXML != null || not empty activity.activityServiceInstructions}">
					<div id="technicianInstructions" class = "portlet-wrap">
						<div class="portlet-header dark-bar">
						<table class="displayGrid">
              <thead>
                <tr><th><spring:message code="claim.label.technicianInstructions"/></th>
                </tr></thead>
              
            </table>
							
						</div>
							
						<div class="portlet-wrap-inner">
							<div class="width-100per">
								<div class="columns two">
									<c:if test="${not empty activity.activityServiceInstructions}">
										<div><label><H4>${activity.activityServiceInstructions}</H4></label></div>
									</c:if>
									<c:if test="${serviceRequestDetailForm.technicianInformationListXML != null}">
										<div id="gridRDVTechnicianInstructions" class="width-100per"></div>
										<div><span id="gridRDVTechnicianInstructionsPagingArea"></span>&nbsp;<span id="gridRDVTechnicianInstructionsInfoArea"></span></div><!-- mygrid_container -->
									</c:if>
								</div>
							</div>	
						</div>
						<c:if test="${serviceRequestDetailForm.technicianInformationListXML != null}">
						<script type="text/javascript">
							technicianInstructionsGrid = new dhtmlXGridObject('gridRDVTechnicianInstructions');
							technicianInstructionsGrid.setImagePath("<html:imagesPath/>gridImgs/");
							technicianInstructionsGrid.setHeader(autoAppendPlaceHolder('<spring:message code="claim.headerList.technicianInstructions"/>',3));
							technicianInstructionsGrid.setColAlign("left,left,left");
							technicianInstructionsGrid.setColTypes("ro,ro,ro");
							technicianInstructionsGrid.setColSorting("str,str,str");
							technicianInstructionsGrid.enableAutoWidth(true);
							technicianInstructionsGrid.enableAutoHeight(true);
							technicianInstructionsGrid.enableMultiline(true);
							technicianInstructionsGrid.enablePaging(true, 5, 10, "gridRDVTechnicianInstructionsPagingArea", true, "gridRDVTechnicianInstructionsInfoArea");
							technicianInstructionsGrid.init();
							technicianInstructionsGrid.setSkin("light");
							technicianInstructionsGrid.setPagingSkin("bricks");
							technicianInstructionsGrid.loadXMLString("${serviceRequestDetailForm.technicianInformationListXML}");
							forPrintGridObjects.push(technicianInstructionsGrid);
						</script>
						</c:if>
						<div class="portlet-footer">	
							<div class="portlet-footer-inner"></div>
						</div>
			        </div>
					</c:if>
					<!-- Technician Instructions End -->
					
					<!-- Parts and Tools Start -->
					<c:if test="${serviceRequestDetailForm.returnPartsXML != null || serviceRequestDetailForm.orderPartsXML != null}">		
					<div id="partsAndTools" class="portlet-wrap">
			         	<div class="portlet-header dark-bar">
			         	<table class="displayGrid">
              <thead>
                <tr><th><spring:message code="claim.label.partsAndTools"/></th>
                </tr></thead>
              
            </table>
			         	
			         	</div>
						<div class="portlet-wrap-inner">
						<!-- Order Parts Start -->
						<c:if test="${serviceRequestDetailForm.orderPartsXML != null}">			
						<div class="status-wrap">
				         	<div class="status-block">
				         	 	<h4><spring:message code="claim.label.ordered.parts"/></h4>
									<div id="gridRDVOrderedPartsTable" class="gridbox_light width-100per" ></div>
									<div class="pagination"><span id="gridRDVOrderedPartsTablePagingArea"></span>&nbsp;<span id="gridRDVOrderedPartsTableInfoArea"></span></div><!-- mygrid_container -->
							</div>
						</div>
						<script type="text/javascript">
						//jQuery(document).ready(function(){
							/* var gridRDVOrderedPartsTable = new dhtmlXGridObject("gridRDVOrderedPartsTable");
							gridRDVOrderedPartsTable.setImagePath("<html:imagesPath/>gridImgs/");
							gridRDVOrderedPartsTable.setHeader(autoAppendPlaceHolder('<spring:message code="claim.headerList.orderedParts.detail"/>',10));
							gridCDtlVOPRequestGrid.setColAlign("left,left,left,left,left,left,left,left,left,left,left");
							gridCDtlVOPRequestGrid.setColTypes("sub_row,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro");
							gridCDtlVOPRequestGrid.setColSorting("na,str,str,str,str,str,str,str,str,str,str");
							gridCDtlVOPRequestGrid.setInitWidths("20,110,50,120,120,120,120,120,120,210,120");
							gridRDVOrderedPartsTable.enableAutoWidth(true);
							gridRDVOrderedPartsTable.enableAutoHeight(true);
							gridRDVOrderedPartsTable.enableMultiline(true);
							gridRDVOrderedPartsTable.enablePaging(true, 5, 10, "gridRDVOrderedPartsTablePagingArea", true, "gridRDVOrderedPartsTableInfoArea");
							gridRDVOrderedPartsTable.init();
							gridRDVOrderedPartsTable.setSkin("light");	
							gridRDVOrderedPartsTable.setPagingSkin("bricks");	
							gridRDVOrderedPartsTable.loadXMLString("${serviceRequestDetailForm.orderPartsXML}");
							forPrintGridObjects.push(gridRDVOrderedPartsTable); */
							//Changes for CI7 BRD 14-07-06 START
							gridCDtlVOPRequestGrid = new dhtmlXGridObject('gridRDVOrderedPartsTable');
							gridCDtlVOPRequestGrid.setImagePath("<html:imagesPath/>gridImgs/");
							gridCDtlVOPRequestGrid.setHeader(autoAppendPlaceHolder('<spring:message code="claim.headerList.orderedParts.detail"/>',12));
							gridCDtlVOPRequestGrid.setColAlign("left,left,left,left,left,left,left,left,left,left,left,left,left");
							gridCDtlVOPRequestGrid.setColTypes("sub_row,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro");
							gridCDtlVOPRequestGrid.setColSorting("na,str,str,str,str,str,str,str,str,str,str,str,str");
							gridCDtlVOPRequestGrid.setLockColVisibility("false,false,false,false,false,false,false,false,false,false,false,false,false");
							gridCDtlVOPRequestGrid.setInitWidths("20,80,50,80,80,80,80,80,80,80,80,100,*");
							//gridCDtlVOPRequestGrid.enableAutoWidth(true);
							gridCDtlVOPRequestGrid.enableAutoHeight(true);
							gridCDtlVOPRequestGrid.enableMultiline(true);
							gridCDtlVOPRequestGrid.enablePaging(true, 5, 10, "gridRDVOrderedPartsTablePagingArea", true, "gridRDVOrderedPartsTableInfoArea");
							gridCDtlVOPRequestGrid.init();
							gridCDtlVOPRequestGrid.setSkin("light");
							// move enabled for CI 14.10 CRM-DAmster201408041344
							gridCDtlVOPRequestGrid.enableColumnMove(true);

							// changes for db saving and retrieving	start
							//gridCDtlVOPRequestGrid.enableAutoSavingToDB("${saveCustomizedGridSettingURL}");
							<c:forEach var="gridSettingVar" items="${gridSettingList}" varStatus="status">
							<c:if test="${gridSettingVar.gridId == 'gridRDVOrderedPartsTable'}">					
							var colsOrder = "${gridSettingVar.colsOrder}";
							gridCDtlVOPRequestGrid.loadOrder(colsOrder);
							
							</c:if>
							</c:forEach>
							gridCDtlVOPRequestGrid.attachEvent("onAfterCMove",function(a,b){
								gridCDtlVOPRequestGrid.enableAutoSavingToDB("${saveCustomizedGridSettingURL}");
								//});	
							});	
							
							// changes for db saving and retrieving	end
							gridCDtlVOPRequestGrid.setPagingSkin("bricks");
							gridCDtlVOPRequestGrid.setColumnHidden(12,true);
							gridCDtlVOPRequestGrid.loadXMLString("${serviceRequestDetailForm.orderPartsXML}");
							//Changes for CI7 BRD 14-07-06 END
						//});
						</script>
						</c:if>
						<!-- Order Parts End -->
						
						<!-- Parts to be returned Start -->
						<c:if test="${serviceRequestDetailForm.returnPartsXML != null}">
						<div class="status-wrap">
							<div class="status-block">
								<h4><spring:message code="claim.label.parts.to.be.returned"/></h4>
								<div id="gridRDVbeReturnedParts" class="gridbox_light width-100per" ></div>
								<div class="pagination"><span id="gridRDVbeReturnedPartsPagingArea"></span>&nbsp;<span id="gridRDVbeReturnedPartsRecinfoArea"></span></div><!-- mygrid_container -->
							</div>
						</div>
						<script type="text/javascript">
							//jQuery(document).ready(function() {
								var gridRDVbeReturnedPartsGrid = new dhtmlXGridObject('gridRDVbeReturnedParts');
								gridRDVbeReturnedPartsGrid.setImagePath("<html:imagesPath/>gridImgs/");
								gridRDVbeReturnedPartsGrid.setHeader(autoAppendPlaceHolder(' ,<spring:message code="claim.headerList.partsToBeReturned"/>',9));
								gridRDVbeReturnedPartsGrid.setColAlign("left,left,left,left,left,left,left,left,left");
								gridRDVbeReturnedPartsGrid.setColTypes("sub_row,ro,ro,ro,ro,ro,ro,ro,ro");
								gridRDVbeReturnedPartsGrid.setColSorting("na,str,str,str,str,str,str,str,str");
								gridRDVbeReturnedPartsGrid.setLockColVisibility("false,false,false,false,false,false,false,false,false");
								gridRDVbeReturnedPartsGrid.setInitWidths("20,50,140,140,140,140,140,140,140");
								gridRDVbeReturnedPartsGrid.enableAutoWidth(true);
								gridRDVbeReturnedPartsGrid.enableAutoHeight(true);
								gridRDVbeReturnedPartsGrid.enableMultiline(true);
								gridRDVbeReturnedPartsGrid.enablePaging(true, 5, 10, "gridRDVbeReturnedPartsPagingArea", true, "gridRDVbeReturnedPartsRecinfoArea");
								gridRDVbeReturnedPartsGrid.init();
								gridRDVbeReturnedPartsGrid.setSkin("light");
								// move enabled for CI 14.10 CRM-DAmster201408041344
								gridRDVbeReturnedPartsGrid.enableColumnMove(true);
								gridRDVbeReturnedPartsGrid.setPagingSkin("bricks");
								gridRDVbeReturnedPartsGrid.loadXMLString("${serviceRequestDetailForm.returnPartsXML}");
								forPrintGridObjects.push(gridRDVbeReturnedPartsGrid);	
								// changes for db saving and retrieving	start
								
								//gridRDVbeReturnedPartsGrid.enableAutoSavingToDB("${saveCustomizedGridSettingURL}");
								<c:forEach var="gridSettingVar" items="${gridSettingList}" varStatus="status">
								<c:if test="${gridSettingVar.gridId == 'gridRDVbeReturnedParts'}">
								var colsOrder = "${gridSettingVar.colsOrder}";
								gridRDVbeReturnedPartsGrid.loadOrder(colsOrder);									
								</c:if>
								</c:forEach>
								gridRDVbeReturnedPartsGrid.attachEvent("onAfterCMove",function(a,b){
									gridRDVbeReturnedPartsGrid.enableAutoSavingToDB("${saveCustomizedGridSettingURL}");
									//});	
								});
								
								// changes for db saving and retrieving	end							
								
								
							//});
						</script>
						</c:if>
						<!-- Parts to be returned End -->
						</div>
			          	<div class="portlet-footer">	
			       	 		<div class="portlet-footer-inner"></div>
			       	 	</div>       	
			        </div>
					</c:if>
					<!-- Parts and Tools End -->
			
					
					<div id="recommendedParts" class = "portlet-wrap">
						<div class="portlet-header dark-bar">
						<table class="displayGrid">
              <thead>
                <tr><th><spring:message code="claim.label.recommendedParts"/></th>
                </tr></thead>
              
            </table>
							
						</div>
						
						<div class="portlet-wrap-inner">
							<div class="scroll">
								<div class="columns two">
									<div id="gridRDVRecommendedParts" class="gridbox_light width-100per" ></div>
									<div class="pagination"><span id="gridRDVRecommendedPartsPagingArea"></span>&nbsp;<span id="gridRDVRecommendedPartsInfoArea"></span></div><!-- mygrid_container -->
								</div>
							</div>
						</div>
			
						<script type="text/javascript">
							//jQuery(document).ready(function() {
								recommendedPartsGrid = new dhtmlXGridObject('gridRDVRecommendedParts');
								recommendedPartsGrid.setImagePath("<html:imagesPath/>gridImgs/");
								//Changes for CI7 BRD 14-07-06
								recommendedPartsGrid.setHeader(autoAppendPlaceHolder('<spring:message code="claim.headerList.recommendedParts"/>',5));
								recommendedPartsGrid.setColAlign("left,left,left,left,left");
								recommendedPartsGrid.setColTypes("ro,ro,ro,ro,ro");
								recommendedPartsGrid.setColSorting("str,str,str,str,str");
								recommendedPartsGrid.setLockColVisibility("false,false,false,false,false");
								recommendedPartsGrid.setInitWidths("150,150,150,150,150");
								recommendedPartsGrid.enableAutoWidth(true);
								recommendedPartsGrid.enableAutoHeight(true);
								recommendedPartsGrid.enableMultiline(true);
								recommendedPartsGrid.init();
								recommendedPartsGrid.setSkin("light");
								// move enabled for CI 14.10 CRM-DAmster201408041344
								recommendedPartsGrid.enableColumnMove(true);
								recommendedPartsGrid.enablePaging(true, 5, 10, "gridRDVRecommendedPartsPagingArea", true, "gridRDVRecommendedPartsInfoArea");
								recommendedPartsGrid.setPagingSkin("bricks");
								recommendedPartsGrid.loadXMLString("${serviceRequestDetailForm.recommendedPartsXML}");	
								forPrintGridObjects.push(recommendedPartsGrid);
								// changes for db saving and retrieving	start
								//recommendedPartsGrid.enableAutoSavingToDB("${saveCustomizedGridSettingURL}");
								<c:forEach var="gridSettingVar" items="${gridSettingList}" varStatus="status">
								<c:if test="${gridSettingVar.gridId == 'gridRDVRecommendedParts'}">
								var colsOrder = "${gridSettingVar.colsOrder}";
								recommendedPartsGrid.loadOrder(colsOrder);								
								</c:if>
								</c:forEach>
								recommendedPartsGrid.attachEvent("onAfterCMove",function(a,b){
										recommendedPartsGrid.enableAutoSavingToDB("${saveCustomizedGridSettingURL}");
									//});	
								});		
								
								// changes for db saving and retrieving	end	
							//});
						</script>  	
						
						<div class="portlet-footer">	
							<div class="portlet-footer-inner"></div>
						</div>
			        </div>
				
					<!-- Additional Payment Requests Start--> 
					<c:if test="${serviceRequestDetailForm.allowAdditionalPaymentRequestFlag && serviceRequestDetailForm.additionalPaymentRequestsXML != null}">
					<div id="additionalPaymentRequests" class = "portlet-wrap">
						<div class="portlet-header dark-bar">
						<table class="displayGrid">
              <thead>
                <tr><th><spring:message code="claim.label.additionalPaymentRequests"/></th>
                </tr></thead>
              
            </table>
						
						</div>
						
						<div class="portlet-wrap-inner">
							<div class="width-100per">
								<div class="columns two">
									<div id="gridRDVpaymentTable" class="gridbox_light width-100per" ></div>
									<div><span id="gridRDVpaymentTablePagingArea"></span>&nbsp;<span id="gridRDVpaymentTableInfoArea"></span></div><!-- mygrid_container -->
								</div>
							</div>
						</div>
									
						<script type="text/javascript">
							//jQuery(document).ready(function() {
								var paymentTableDetailsGrid = new dhtmlXGridObject('gridRDVpaymentTable');
								paymentTableDetailsGrid.setImagePath("<html:imagesPath/>gridImgs/");
								paymentTableDetailsGrid.setHeader(autoAppendPlaceHolder('<spring:message code="claim.headerList.additionalPaymentRequests"/>',6));
								paymentTableDetailsGrid.setColAlign("left,left,left,left,left,left");
								paymentTableDetailsGrid.setColTypes("ro,ro,ro,ro,ro,ro");
								paymentTableDetailsGrid.setColSorting("str,str,str,str,str,str");
								paymentTableDetailsGrid.setInitWidths("100,150,150,150,150,400");
								paymentTableDetailsGrid.enableAutoWidth(true);
								paymentTableDetailsGrid.enableAutoHeight(true);
								paymentTableDetailsGrid.enableMultiline(true);
								paymentTableDetailsGrid.init();
								paymentTableDetailsGrid.setSkin("light");
								paymentTableDetailsGrid.enablePaging(true, 5, 10, "gridRDVpaymentTablePagingArea", true, "gridRDVpaymentTableInfoArea");
								paymentTableDetailsGrid.setPagingSkin("bricks");
								paymentTableDetailsGrid.loadXMLString("${serviceRequestDetailForm.additionalPaymentRequestsXML}");
								forPrintGridObjects.push(paymentTableDetailsGrid);	
							//});
						</script>
						
						<div class="portlet-footer">	
							<div class="portlet-footer-inner"></div>
						</div>
			        </div>
			        </c:if>
			        <!-- Additional Payment Requests end-->  	 
			         	 
					<!-- Notes Start-->  
					<!--/*changes for LEX:AIR00070474 Start*/-->   	
					<div id="serviceRequestNotes" class = "portlet-wrap">
						<div class="portlet-header dark-bar">
						<table class="displayGrid">
              <thead>
                <tr><th><spring:message code="claim.label.notes"/></th>
                </tr></thead>
              
            </table>
							
						</div>
						<div class="portlet-wrap-inner">
							<div class="scroll">
								<div class="columnsTwo columns">
									<div id="gridRDVnotes" class="gridbox_light width-100per" ></div>
									<div class="pagination"><span id="gridRDVnotesPagingArea"></span>&nbsp;<span id="gridRDVnotesInfoArea"></span></div><!-- mygrid_container -->
								</div>
							</div>
						</div>
						<div class="portlet-footer">
							<div class="portlet-footer-inner"></div>
						</div>
					</div>
					<!--/*changes for LEX:AIR00070474 End*/-->   	
					<script type="text/javascript">
						//jQuery(document).ready(function() {
							gridRDVnotesGrid = new dhtmlXGridObject('gridRDVnotes');
							gridRDVnotesGrid.setImagePath("<html:imagesPath/>gridImgs/");
							gridRDVnotesGrid.setHeader(autoAppendPlaceHolder('<spring:message code="claim.headerList.notes.detail"/>',4));
							gridRDVnotesGrid.setColAlign("left,left,left,left");
							gridRDVnotesGrid.setColTypes("sub_row,ro,ro,ro");
							gridRDVnotesGrid.setColSorting("na,str,str,str");
							gridRDVnotesGrid.setInitWidths("20,150,150,200");
							gridRDVnotesGrid.enableAutoWidth(true);
							gridRDVnotesGrid.enableAutoHeight(true);
							gridRDVnotesGrid.enableMultiline(true);
							gridRDVnotesGrid.init();
							gridRDVnotesGrid.setSkin("light");
							gridRDVnotesGrid.enablePaging(true, 5, 10, "gridRDVnotesPagingArea", true, "gridRDVnotesInfoArea");
							gridRDVnotesGrid.setPagingSkin("bricks");
							gridRDVnotesGrid.loadXMLString("${serviceRequestDetailForm.notesXML}");
							//forPrintGridObjects.push(gridRDVnotesGrid);
						//});
					</script>
					<!-- Notes End-->   
					
					<!-- Email Notification started-->
					<div id = includeEmailNotification> 
						<jsp:include page="/WEB-INF/jsp/claims/emailNotificationsDisplay.jsp" flush="true"/>
					</div>
					<!-- Email Notification End-->
					<!-- Attachment started -->
					<div id='uploadedFileDiv' class="portlet-wrap-inner">
						<table id="fileListTable">
						</table>
					</div>
					
					<script type="text/javascript">
					<c:if test="${serviceRequestDetailForm.attachmentList != null || not empty serviceRequestDetailForm.attachmentList}">
					jQuery('#fileListTable',window.parent.document).empty();
				 		responseText = '<thead><tr class="tableHeader"><b><th class="width60"><spring:message code="attachment.message.FileName"/></th><th class="width35"><spring:message code="attachment.message.FileSize"/></th></b></tr></thead>';
					   responseText = responseText + '<tbody>';
				 		<c:forEach items="${serviceRequestDetailForm.attachmentList}" var="entry">
				 			responseText = responseText +  '<tr class="tableContentColor">';
				 			responseText = responseText + '<td class="width60">'+ '<a href="#" onClick=\'outPutFile("${entry.actualFileName}","${entry.activityId}","${entry.attachmentName}");\'><c:out value="${entry.attachmentName}" /></a>' + '</td>';
				 			responseText = responseText + '<td align="center" class="width35">'+ '<c:out value="${entry.sizeForDisplay}" />' + '</td>';
				 			responseText = responseText + '</tr>';
				 			
						</c:forEach>
						responseText = responseText + '</tbody>';
						jQuery('#fileListTable',window.parent.document).append(responseText);
				</c:if>
				
				<c:if test="${serviceRequestDetailForm.attachmentList == null || empty serviceRequestDetailForm.attachmentList}">
					jQuery('#fileListTable',window.parent.document).empty();
					jQuery('#uploadedFileDiv').hide();
				</c:if>
					</script>
					<!-- Attachment End -->
					<script type="text/javascript">
						if(emailNotificationsGrid)
							forPrintGridObjects.push(emailNotificationsGrid);
					</script>					
				</div>
			</div>
		</td>
	</tr>
</table>
<script type="text/javascript">
function outPutFile(userFileName,activityId,attachmentName){
        window.open("${outPutFileURLRequest}&userFileName=" + userFileName + "&activityId=" + activityId+ "&attachmentName=" + attachmentName, "");
};
</script>

<script type="text/javascript">

	function updateRequest(){
		callOmnitureAction('Requests Detail View', 'Update Request');
		window.location.href="${updateRequestUrl}"+ "&timezoneOffset="+timezoneOffset;;
	}
	function debriefRequest(){
		callOmnitureAction('Requests Detail View', 'Close Out Request');
		window.location.href="${debriefRequestUrl}"+ "&timezoneOffset="+timezoneOffset;;
	}
</script>
<script type="text/javascript">
//---- Ominture script 
     portletName = "Service Request Detail Page";
     addPortlet(portletName);
</script>