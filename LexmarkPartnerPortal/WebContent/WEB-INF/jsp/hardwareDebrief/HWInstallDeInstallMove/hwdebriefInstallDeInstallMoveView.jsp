<%@ include file="/WEB-INF/jsp/include.jsp"%>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/jQueryAlert/jquery.alerts.css?version=<html:fileCacheVersion/>" />
<style>
.dataGrid td.label{
	width: 50%;
	text-align: right;
}
.displayGrid tr th {
	
	background: #e6e6f0!important;
	color:black!important;
	
}
</style>
<style>
.ie7 .button22, .ie7 .button_cancel22 {
behavior: url(/LexmarkServicesPortal/WEB-INF/css/PIE.htc) !important;
}
</style>
<script>
var  global_button_message = { close: "<spring:message code='button.close'/>",
		ok: "<spring:message code='button.ok'/>",
		cancel: "<spring:message code='button.cancel'/>",
		selectColumns:"<spring:message code='button.selectColumns'/>"
};
</script>
<script type="text/javascript" src="<html:rootPath/>js/jQueryAlert/jquery.alerts.js?version=<html:fileCacheVersion/>"></script>
<%@page import=" static com.lexmark.constants.LexmarkPPConstants.SRTYPE"%>
<%@page import=" static com.lexmark.constants.LexmarkPPConstants.SRNUMBER"%>
<%@page import=" static com.lexmark.constants.LexmarkPPConstants.viewOrCloseout"%>
<%@page import=" static com.lexmark.constants.LexmarkConstants.TIMEZONE_OFFSET"%>
<%@page import=" static com.lexmark.constants.LexmarkPPConstants.BACKURL"%>
<%-- The type of the SR coming from the request from Amind--%>
<c:set var="requestType" value="${fn:toUpperCase(srType)}" scope="request"/>

<%-- Constants to differentiate Which type of request is
hardwareActivityTypes is kept at application context level.
So that any changes to type of request there need not be code level changes 
--%>
	
	<c:set var="isHwInstall" value="false"  scope="request"/>
	<c:set var="isHwDeInstall" value="false" scope="request"/>
	<c:set var="isHwMove" value="false" scope="request"/>
	<c:set var="isHwInstallDecommission" value="false" scope="request"/>	

<c:set var="tzOffset" value="${hardwareDebriefForm.timezoneOffset}" scope="request"/>



		<c:choose>
			<c:when test='${fn:endsWith(requestType, "INSTALL") or fn:endsWith(requestType, "CHANGE") or fn:endsWith(requestType, "INSTALL/DECOMMISSION")}'>
				<c:set var="isHwInstall" value="true"  scope="request"/>
				<c:if test='${fn:endsWith(requestType, "INSTALL/DECOMMISSION")}'>
				<c:set var="isHwInstallDecommission" value="true" scope="request"/>
				</c:if>
			</c:when> 
			<c:when test='${fn:endsWith(requestType, "DECOMMISSION")}'>
			<c:choose>
			<c:when test='${fn:endsWith(requestType, "INSTALL/DECOMMISSION")}'>
			<c:set var="isHwInstallDecommission" value="true" scope="request"/>
			</c:when>
			<c:otherwise>
			<c:set var="isHwDeInstall" value="true" scope="request"/>
			</c:otherwise>
			</c:choose>
			</c:when>
			<c:when test='${fn:endsWith(requestType, "MOVE")}'>
				<c:set var="isHwMove" value="true" scope="request"/>
			</c:when>
		</c:choose>


<%--ENDS Constants to differentiate --%>


<portlet:resourceURL var="acceptRejectRequest" id='acceptRejectRequest'></portlet:resourceURL>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/mps.css?version=<html:fileCacheVersion/>" />
<!--[if IE 7]>
	<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/mps_ie7.css?version=<html:fileCacheVersion/>" />
<![endif]-->

  <div id="content-wrapper">
  <div id="header">
    <div class="journal-content-article">
      <h1><spring:message code="download.request" /> 
      <c:choose>
      <c:when test='${isHwInstall && isHwInstallDecommission}'>
      <spring:message code="requestInfo.hardwareDebreief.heading.InstallDecommission"/>
      
      </c:when>
      	<c:when test='${isHwInstall}'>
      	
      		<spring:message code="requestInfo.hardwareDebreief.heading.install"/>
      	</c:when>
      	<c:when test='${isHwDeInstall}'>
      		<spring:message code="requestInfo.hardwareDebreief.heading.deInstall"/>
      	</c:when>
      	<c:when test='${isHwMove}'>
      		<spring:message code="requestInfo.hardwareDebreief.heading.move"/>
      	</c:when>
      </c:choose> 
      <spring:message code="requestInfo.hardwareDebreief.view.headingSuffix"/></h1>
    </div>
    </div>
   
    <div class="main-wrap">
 	
    
    <c:if test="${exceptionOccured eq true or exceptionOccured_Detail eq true}">
    	<div class="error" id="failureDiv" >
    		<ul>
    		<li><spring:message code="requestInfo.hardwareDebreief.view.errorMsg"/></li>
    	</ul>
    </div>
    
    </c:if>
    
    
    	
    <form:form commandName="hardwareDebriefForm" action="hardwareinstallation" id="hwInstallForm" method="post">
      <div class="content">
      <div class="grid-controls"> 
             <div class="utilities">
		  <ul>
              <li class="first"><a href="javascript:email();"><img class="ui-icon email-icon" src="<html:imagesPath/>transparent.png" alt="Email this page" height="23" width="23"></a></li>
              <li><a href="javascript:print();"><img src="<html:imagesPath/>transparent.png" class="ui-icon print-icon" alt="Print this page" height="27" width="27"></a></li>
          </ul>
        </div>
            <div class="expand-min">
              <ul>
                <li class="first"><img class="ui-icon bck-arrow-icon" src="<html:imagesPath/>transparent.png" alt="Back to Service Orders" width="24" height="24" title="Back to Service Orders" /><a href="#" id="returnRequests" name="view" ><spring:message code="button.return.to.requests"/></a> </li>
              </ul>
            </div>
          </div>
        <!-- MAIN CONTENT GOES HERE -->
        
        <div id="hwDebriefView">
        
        <!-- Request Information BLOCK - Start -->
       <jsp:include page="/WEB-INF/jsp/hardwareDebrief/HWInstallDeInstallMove/commonHwView/commonRequestInformationView.jsp"/>
       <!-- Request Information BLOCK - End -->
		
		
		
        <!-- Customer Requested Items BLOCK - Start -->
        <jsp:include page="/WEB-INF/jsp/hardwareDebrief/HWInstallDeInstallMove/commonHwView/commonDeviceDetailsAndCustomerRequestedItems.jsp"/>
        <!-- Customer Requested Items BLOCK - End --> 
        
        <!-- Service Information BLOCK - Start -->
        <jsp:include page="/WEB-INF/jsp/hardwareDebrief/HWInstallDeInstallMove/commonHwView/commonServiceInformation.jsp"/>
        <!-- Service Information BLOCK - End --> 
        
        <!-- Parts And Tools Used BLOCK - Start -->
        
	    <c:if test='${isHwInstall}'>
	    
        <div class="portletBlock infoBox rounded shadow">
          <div class="columnsOne">
            <div class="columnInner">
              <h4><spring:message code="requestInfo.hardwareDebreief.debriefInstallCloseOut.partsAndTools"/></h4>
				<p class="inlineTitle inlineTitle2"><spring:message code="requestInfo.hardwareDebreief.debriefInstallCloseOut.recommendedParts"/></p>
				<div class="w70p">
				<table class="displayGrid2 rounded shadow wFull">
						<thead>
							<tr>
								<th class="w100"><spring:message code="requestInfo.hardwareDebreief.debriefInstallCloseOut.partNo"/></th>
								<th><spring:message code="requestInfo.hardwareDebreief.debriefInstallCloseOut.description"/></th>
								<th class="w100"><spring:message code="requestInfo.hardwareDebreief.debriefInstallCloseOut.quantity"/></th>
								<th class="w150"><spring:message code="requestInfo.hardwareDebreief.debriefInstallCloseOut.status"/></th>
								<%-- removed as per defect 10408 
								<th class="w150">Serial No</th>--%>
							</tr>
						</thead></table></div>
						<div class="scrollTable w70p">
						<table class="displayGrid2 rounded shadow wFull">
						<tbody>
						<c:choose>
							<c:when test="${not empty hardwareDebriefForm.activity.serviceRequest.parts }">
								<c:forEach var="part" items="${hardwareDebriefForm.activity.serviceRequest.parts}">
								<tr>
									<td class="w100">${part.partNumber}</td>
									<td>${part.description}</td>
									<td class="w100">${part.orderQuantity}</td>
									<td class="w150">
									<c:if test="${part.typePrinter}">
									<c:forEach items="${hardwareDebriefForm.recommendedPartlistStatusForPrinterType}" var="recomendedPartlistTypeMap">
											<c:choose>
												<c:when test="${part.status eq recomendedPartlistTypeMap.key}">
													${recomendedPartlistTypeMap.value}													
												</c:when>
												<c:otherwise>												
												</c:otherwise>												
											</c:choose>														
									</c:forEach>
									</c:if>
									<c:if test="${not part.typePrinter}">
									<c:forEach items="${hardwareDebriefForm.recommendedPartlistStatusForNonPrinterType}" var="recomendedPartlistTypeMap">
											<c:choose>
												<c:when test="${part.status eq recomendedPartlistTypeMap.key}">
													${recomendedPartlistTypeMap.value}													
												</c:when>
												<c:otherwise>												
												</c:otherwise>												
											</c:choose>														
									</c:forEach>
									</c:if>
									</td>
									<%--removed as per defect 10408 
									<td class="w150">${part.assetSerialNumber}</td> --%>
								</tr>
								</c:forEach>	
							</c:when>
							<c:otherwise>
							<tr><td colspan="4"><spring:message code="requestInfo.hardwareDebrief.commonContactInfo.noRec"/></td></tr>
							</c:otherwise>
						</c:choose>
						
						
							
						</tbody>
					</table>
					</div>
					
				  <p class="inlineTitle inlineTitle2"><spring:message code="requestInfo.hardwareDebreief.debriefInstallCloseOut.additionalPartsUsed"/></p>
				  <table class=" displayGrid2 rounded shadow wHalf">
						<thead>
							<tr>
								<th class="w100"><spring:message code="requestInfo.hardwareDebreief.debriefInstallCloseOut.partNo"/></th>
								<th><spring:message code="requestInfo.hardwareDebreief.debriefInstallCloseOut.description"/></th>
								<th class="w100"><spring:message code="requestInfo.hardwareDebreief.debriefInstallCloseOut.quantity"/></th>
								<th class="w150"><spring:message code="requestInfo.hardwareDebreief.debriefInstallCloseOut.status"/></th>
								<%--removed as per defect 10408 
								<th class="w150">Serial No</th>	--%>							
							</tr>
						</thead>
						<tbody>
						<c:choose>
							<c:when test="${not empty hardwareDebriefForm.activity.additionalPartList}">
							<c:forEach var="part" items="${hardwareDebriefForm.activity.additionalPartList}">
							<tr>
								<td class="w100">${part.partNumber }</td>
								<td>${part.description}</td>
								<td class="w100">${part.orderQuantity}</td>
								<td class="w150">
								<c:if test="${part.typePrinter}">
									<c:forEach items="${hardwareDebriefForm.recommendedPartlistStatusForPrinterType}" var="recomendedPartlistTypeMap">
											<c:choose>
												<c:when test="${part.status eq recomendedPartlistTypeMap.key}">
													${recomendedPartlistTypeMap.value}													
												</c:when>
												<c:otherwise>												
												</c:otherwise>												
											</c:choose>														
									</c:forEach>
									</c:if>
									<c:if test="${not part.typePrinter}">
									<c:forEach items="${hardwareDebriefForm.recommendedPartlistStatusForNonPrinterType}" var="recomendedPartlistTypeMap">
											<c:choose>
												<c:when test="${part.status eq recomendedPartlistTypeMap.key}">
													${recomendedPartlistTypeMap.value}													
												</c:when>
												<c:otherwise>												
												</c:otherwise>												
											</c:choose>														
									</c:forEach>
									</c:if>
								</td>
								<%--removed as per defect 10408
								<td class="w150">${part.assetSerialNumber}</td>--%>
							</tr>
						</c:forEach>
						</c:when>
						<c:otherwise>
							<tr><td colspan="4"><spring:message code="requestInfo.hardwareDebrief.commonContactInfo.noRec"/></td></tr>
						</c:otherwise>
						</c:choose>
							
							
						</tbody>
					</table>
            </div>
          </div>
        </div>
        </c:if>
	
        <!-- Parts And Tools Used BLOCK - End --> 
        
		<!-- Close Out Install Activity BLOCK - Start -->       
         <jsp:include page="/WEB-INF/jsp/hardwareDebrief/HWInstallDeInstallMove/commonHwView/commonCloseOutInstallActivityView.jsp"/>        
        <!-- Close Out Install Activity BLOCK - End -->
        <c:if test='${isHwInstall ||isHwMove }'>
			<!-- Service Information for this Device BLOCK - Start -->        
	        <jsp:include page="/WEB-INF/jsp/hardwareDebrief/HWInstallDeInstallMove/commonHwView/commonServiceRequestForDeviceView.jsp"/>        
	        <!-- Service Information for this Device BLOCK - End --> 
        </c:if>
        
        <c:if test='${isHwInstall ||isHwMove }'>
		<!-- Supplies Information for this Device BLOCK - Start -->        
        <jsp:include page="/WEB-INF/jsp/hardwareDebrief/HWInstallDeInstallMove/commonHwView/commonContactInfoForView.jsp"/>        
        <!-- Supplies Information for this Device BLOCK - End -->
        </c:if>
        <%--<c:if test='${srType == hwInstall || srType == hwDeInstall }'>--%>
		<!-- Page Count BLOCK - Start -->
          
         <jsp:include page="/WEB-INF/jsp/hardwareDebrief/HWInstallDeInstallMove/commonHwView/commonPageCountView.jsp"/>         
        <!-- Page Count BLOCK - End --> 
        <c:if test="${isHwInstallDecommission}">
         <jsp:include page="/WEB-INF/jsp/hardwareDebrief/HWInstallDeInstallMove/commonHwView/commonInstall-DeInstallView.jsp"/>
         <jsp:include page="/WEB-INF/jsp/hardwareDebrief/HWInstallDeInstallMove/commonHwView/InstallDeInstallPageCountView.jsp"/>
       </c:if>
        <!-- NOTES BLOCK - Start -->        
        <jsp:include page="/WEB-INF/jsp/hardwareDebrief/HWInstallDeInstallMove/commonHwView/commonNotesView.jsp"/>        
        <!-- NOTES BLOCK - End --> 
        
		<!-- ATTACHMENTS BLOCK - Start -->        
        <jsp:include page="/WEB-INF/jsp/hardwareDebrief/HWInstallDeInstallMove/commonHwView/commonAttachmentsView.jsp"/>        
        <!-- ATTACHMENTS BLOCK - End --> 
        
        </div>
    </div>
   		 <input type="hidden" name="<%=SRTYPE%>" id="<%=SRTYPE%>" value="${requestType}"/>
		<input type="hidden" name="<%=SRNUMBER%>" id="<%=SRNUMBER%>" value="${hardwareDebriefForm.activity.serviceRequest.serviceRequestNumber}"/>
		<input type="hidden" name="<%=viewOrCloseout%>" id="<%=viewOrCloseout%>" value="closeOut"/>
		<input type="hidden" name="activityId" id="activityId" value="${hardwareDebriefForm.activity.activityId}"/>
		<input type="hidden" name="status" id="status" value="Accepted"/>
		<input type="hidden" name="acceptUpdate" id="acceptUpdate" value="true"/>
		<input type="hidden" name="<%=TIMEZONE_OFFSET%>" id="<%=TIMEZONE_OFFSET%>" value="${tzOffset}"/>
		<input type="hidden" name="<%=BACKURL%>" value="${hardwareDebriefForm.backURL}"/>
		
    </form:form>
    </div>
  </div>
        
		<div class="buttonContainer">
          <button class="button_cancel" id="returnRequests_but" name="view" ><spring:message code="button.return.to.requests"/></button>
        </div>
  <%-- Added for LBS 1.5 View Grid--%>
   <jsp:include page="/WEB-INF/jsp/common/mapPopup.jsp" />
  <%-- ENDS for LBS 1.5 View Grid--%>
 <script>
 var backURL={
			backURLocation:"${hardwareDebriefForm.backURL}"	 
	 };
 </script>  
 <script type="text/javascript" src="<html:rootPath/>js/hardwareDebreif.js?version=<html:fileCacheVersion/>"></script>
 
 <script>
 <%-- Below value is for BACK URL--%>
 
 <%-- Below confirmation messages are used in hardwaredebrief.js to show the jConfirm --%>
 var confirmationMessages={
			ConfirmMsg1:"<spring:message code="requestInfo.hardwareDebreief.common.closeOut.confirmMessage" javaScriptEscape="true"/>",
			ConfirmTitle:"<spring:message code="requestInfo.hardwareDebreief.common.closeOut.confirmMessageTitle" javaScriptEscape="true"/>",
			alertSuccess:"<spring:message code="requestInfo.hardwareDebreief.common.closeOut.alertMessageSuccess" javaScriptEscape="true"/>",
			alertFailure:"<spring:message code="requestInfo.hardwareDebreief.common.closeOut.alertMessageFailure" javaScriptEscape="true"/>",
			ConfirmMsg2:"<spring:message code="requestInfo.hardwareDebreief.common.closeOut.confirmMessageToClose" javaScriptEscape="true"/>",
			commentTitle:"<spring:message code="claim.label.comments" javaScriptEscape="true"/>"
	}
 <%-- Below URLs are used in hardwaredebrief.js to show the popups --%>
	var popupURLS={
				acceptURL:"${acceptRejectRequest}"
				
		};
	function print() {

		url ="<portlet:renderURL windowState='<%= LiferayWindowState.POP_UP.toString() %>'>"+
			"<portlet:param name='action' value='printDebrif' />"+
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
		
		url ="<portlet:renderURL windowState='<%= LiferayWindowState.POP_UP.toString() %>'>"+
			"<portlet:param name='action' value='emailDebrif' />"+
			"</portlet:renderURL>";

	    var iWidth=1100;
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
	function acceptAndUpdate(){
		
		jQuery('#hwInstallForm').submit();

	}
      function redirectToDebriefViewAndCloseOut(srNumber,srType,viewOrCloseOut,activityId){
		
		jQuery('#<%=SRNUMBER%>').val(srNumber);
		jQuery('#<%=SRTYPE%>').val(srType);
		<%--To be uncommented lateer
	//	jQuery('#<%=SRTYPE%>').val('HW Install Move');--%>
		jQuery('#<%=viewOrCloseout%>').val(viewOrCloseOut);
		jQuery('#activityId').val(activityId);
		jQuery('#status').val('');
		jQuery('#acceptUpdate').val('');
		jQuery('#<%=TIMEZONE_OFFSET%>').val(${tzOffset});
		jQuery('#hwInstallForm').submit();
		//window.location.href="hardwareinstallation?sr="+srNumber;
	}
  	function reloadGrid(){}
 </script>  
   
