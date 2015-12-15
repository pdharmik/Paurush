<%-- Added for CI BRD13-10-02--STARTS --%>
<%@ taglib prefix="html" tagdir="/WEB-INF/tags/html" %>

<%@page import=" static com.lexmark.constants.LexmarkConstants.gridConfigurationValues" %>
<%@page import=" static com.lexmark.constants.LexmarkConstants.JSON_COMBO_FILTER"%>
<%@page import=" static com.lexmark.constants.LexmarkConstants.JSON_RESOURCE_URL"%>
<%@page import=" static com.lexmark.constants.LexmarkConstants.gridSavingParams" %>
<%@ taglib prefix="util" uri="/WEB-INF/tld/lexmark-util.tld" %>
<%@page import="com.liferay.portal.util.PortalUtil"%>
<style type="text/css"><%@ include file="../../css/jquery/jquery-ui.css" %></style>
<%-- Added for CI BRD13-10-02--ENDS --%>
<%-- <%@ include file="/WEB-INF/jsp/include.jsp"%> --%>
<%-- common subtab jsp to select history or create new request 
<%String currURL=PortalUtil.getCurrentCompleteURL(request);
if(currURL.indexOf("dFFlag=true")==-1){
	%>
	<%@ include file="../common/subTab.jsp"%>
<% }%>

--%>
<script type="text/javascript" src='<html:rootPath/>js/jQuery/jquery.min.js'></script>
<% request.setAttribute("subTabSelected","createNewRequest"); %>
<%@ include file="../common/subTab.jsp"%>
<style type="text/css">
.status-bar {
    top: 0 !important;
}
div.activity_Table{
    overflow: auto;
    width: 100%;
}
.activity_Table table.displayGrid{
	max-width:120% !important;
	width: 120% !important;
}
.displayGrid tr th.short{
	width:auto!important;
}
.displayGrid tr th.long{
	width:20%!important;
}
</style>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/mps.css" />
<!--[if IE 7]>
	<style type="text/css"><%@ include file="/WEB-INF/css/mps_ie7.css" %></style>
<![endif]-->
<jsp:include page="/WEB-INF/jsp/includeGrid.jsp"/>
<script type="text/javascript" src="<html:rootPath/>js/jQuery/jquery-ui.min.js"></script>


<%@ page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>

<script type="text/javascript" src='<html:rootPath/>js/trackingNumber.js'></script>
<script type="text/javascript" src='<html:rootPath/>js/jquery.progressbar.js'></script>
<script type="text/javascript" src="<html:rootPath/>js/expand.js"></script>

<style>
div.portlet-header h3{margin:0}
div.portlet-footer, div.portlet-footer-inner{background-image:none}
div.portlet-header{margin:0 !important}

.displayGrid tr th{

    background: #ededed;
    filter: none;
	color: #000!important;
}

</style>
<script type="text/javascript">
	barImages = { 0: '<html:imagesPath/>progressBar/progressbg_red.gif',
		45:	'<html:imagesPath/>progressBar/progressbg_half.gif',
   		55: '<html:imagesPath/>progressBar/progressbg_orange.gif',
		70: '<html:imagesPath/>progressBar/progressbg_green.gif'};
	barImagesCancel = {0: '<html:imagesPath/>progressBar/progressbg_black.gif'};
</script>

<script type="text/javascript"> 
//CI-6 : Start : Attachment
jQuery(document).ready(function() {
	jQuery("h4.expand").toggler(); //Done for CI-7 Request History on Asset
	<c:if test="${serviceRequestForm.attachmentList != null}">
	jQuery('#fileListTable',window.parent.document).empty();
 		responseText = '<thead><tr class="tableHeader"><th class="width60"><spring:message code="attachment.message.FileName"/></th><th class="width35"><spring:message code="serviceRequest.attachment.table.header.size"/></th></thead>';
	   responseText = responseText + '<tbody>';
 		<c:forEach items="${serviceRequestForm.attachmentList}" var="entry">
 			responseText = responseText + '<tr class="tableContentColor">';
 			responseText = responseText + '<td class="width60">'+ '<a href="#" onClick=\'outPutFile("${entry.attachmentName}","${entry.activityId}","${entry.displayAttachmentName}") ;\'><c:out value="${entry.displayAttachmentName}" /></a>' + '</td>';
 			responseText = responseText + '<td class="width35">'+ '<c:out value="${entry.sizeForDisplay}" />' + '</td>';
 			responseText = responseText + '</tr>'; 
 			
		</c:forEach>
		responseText = responseText + '</tbody>';
		jQuery('#fileListTable',window.parent.document).append(responseText);
</c:if>});
//CI-6 : Start : End
</script>
<portlet:resourceURL var="displayAttachmentURLBreakFix" id="displayAttachmentBreakFix">	
</portlet:resourceURL>
<div class="main-wrap">
<br/>
  <div id="serviceUtilityBox" style="float:left;width:98.3%;border-bottom: 1px dotted #AAAAAA;margin:5px 25px 5px 5px">
     <div style="float:left;width:40%">
    		<img src="<html:imagesPath/>transparent.png" class="ui_icon_sprite email-icon" onClick="loadGridDataForEmail()"; style="cursor:pointer";/> 
       		<img src="/LexmarkOPSPortal/images/transparent.png" class="ui_icon_sprite print-icon" onClick="loadGridDataForPrint()"; style="cursor:pointer" />
    </div>
	<div style="float:right;width:40%;padding:0 0 8px 8px;text-align:right">
   		<button class="button" type="button" onclick="backToHistory();"><spring:message code="Details.changeRequestDetails.button.back"/></button>&nbsp;
 
		<c:if test="${serviceRequestForm.createServiceRequestFlag}" > 
    				<!-- 
    				<button class="button" onClick="doSubmitSR();">
    					<spring:message code="serviceRequest.description.submitServiceRquest"/>
    				</button>
    				 -->
    				<a class="button" style="color:#fff" href='<portlet:renderURL windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>">
			      		<portlet:param name="action" value="createNewRequestPopUp"></portlet:param>
						</portlet:renderURL>' class='createNewRequestPopUp' title='<spring:message code="button.createNewRequest"/>' onclick="return popupDialog();">
						<span><spring:message code="button.createNewRequest"/></span>
					</a>
    			</c:if>
 	</div>
</div>
<div style="float:left;width: 98.3%">

<div id="div_emailDetailBody">
		<div id="div_dillDownHead" class="sr-header">
			<h2><spring:message code="message.servicerequest.detail" />-&nbsp;${serviceRequestForm.serviceRequest.serviceRequestNumber}</h2>
			<div class="utilities"></div><!-- utilities -->
			<div class="header-right"></div><!-- header-right -->
			<div class="clear"></div><!-- clear -->
		</div><!-- sr-header -->
		
		<div id="div_problemInfoPrint">
		<div id="tab_problemInfo" class="portlet-wrap rounded">
			<div class="portlet-header">
				<h3><spring:message code="message.servicerequest.problemInformation" /></h3>
			</div><!-- portlet-header -->
			
			<div class="portletBlock infoBox rounded shadow split">
				<div class="columns two">
					<div id="problemDescription" class="first-column">
						<ul class="form">
							<li><label><spring:message code="serviceRequest.label.problemDescription" /></label>
							<span>${fn:replace(serviceRequestForm.serviceRequest.problemDescription, newLineChar, "<br/>")}</span></li>
							<!-- added for CI5 -->
							
							<li><label><spring:message code="serviceRequest.label.resolutionCode" /></label>
							<span>${fn:replace(serviceRequestForm.serviceRequest.resolutionCode, newLineChar, "<br/>")}</span></li>
							<!-- end of addition for CI5 -->
							
							<li><label><spring:message code="requestInfo.label.agreementName"/> </label>
                  <span id ="agreementName">${serviceRequestForm.serviceRequest.agreementName }</span></li>
                  
                  
                  
                   
                  <li><label><spring:message code="requestInfo.label.agreementNumber"/> </label>
                  <span id ="agreementNumber">${serviceRequestForm.serviceRequest.agreementNumber }</span></li>
                
                <!-- Added for OPS Portal -->
                
                
                 <li> <label><spring:message code="requestInfo.label.area"/></label>
                  <span id="area">${serviceRequestForm.serviceRequest.area.value }</span></li>
                
                  <li><label><spring:message code="requestInfo.label.subArea"/></label>
                  <span id="subarea">${serviceRequestForm.serviceRequest.subArea.value }</span></li>
			
                
                  <li><label><spring:message code="Details.changeRequestDetails.label.status"/></label>
                  <span id="serviceRequestStatus">${serviceRequestForm.serviceRequest.serviceRequestStatus }</span></li>
                  <!-- Added for OPS Portal -->
                 </ul>
					</div><!-- first-column -->
					<div id="typeOfServiceNeeded" class="second-column">
						<ul class="form">
	                <li><label> <spring:message code="Details.supplyRequestDetails.label.orderSource"/></label>
	                <span id = "orderSource">${serviceRequestForm.serviceRequest.orderSource } </span></li>
	                
                 <li> <label><spring:message code="requestInfo.label.requestStatus"/></label>
                  <span id = "requestStatus">${serviceRequestForm.serviceRequest.requestStatus }</span></li>
                  
               
               
                 <li> <label><spring:message code="requestInfo.label.subStatus"/> </label>
                  <span id = "subStatus">${serviceRequestForm.serviceRequest.subStatus }</span></li>
                  
               
               
                 <li> <label><spring:message code="requestInfo.label.severity"/>:  </label>
                  <span id = "severity">${serviceRequestForm.serviceRequest.severity }</span></li>
                  
							<!-- Added For Printer Error Code for CI 13.10.17 -->
							<%--<label><spring:message code="serviceRequest.label.printerErrorCode" /></label>
							<span>1111</span> --%>
						
						
							<li><label><spring:message code="serviceRequest.label.typeOfServiceNeeded" /></label>
							<span>${serviceRequestForm.serviceRequest.otherRequestedService}</span></li>
							<li><c:forEach items="${serviceRequestForm.serviceRequest.selectedServiceDetails}" var="selectedServiceDetial" varStatus="status">				
								<c:choose>
									<c:when test="${selectedServiceDetial.serviceDetailDescription == 'Option Exchange'}">
										<c:if test="${serviceRequestForm.serviceRequest.optionExchangeOtherDescription != null && fn:trim(serviceRequestForm.serviceRequest.optionExchangeOtherDescription) != ''}">
											<span><spring:message	code='serviceRequest.label.ExchangeOfOption' />:</span><br>
											<span>&nbsp;&nbsp;&nbsp;&nbsp;${serviceRequestForm.serviceRequest.optionExchangeOtherDescription}</span><br>
										</c:if>	
									</c:when>
									<c:otherwise> 
										<span>${selectedServiceDetial.serviceDetailDescription}</span><br>
									</c:otherwise>
								</c:choose>
							</c:forEach></li>
<!--							<c:if test="${serviceRequestForm.serviceRequest.otherRequestedService != null && fn:trim(serviceRequestForm.serviceRequest.otherRequestedService) != ''}">-->
<!--								<span><spring:message	code='serviceRequest.label.Other' /></span><br>-->
<!--								<span>&nbsp;&nbsp;&nbsp;&nbsp;${serviceRequestForm.serviceRequest.otherRequestedService}</span><br>-->
<!--							</c:if>-->

							
							<!-- partha added start -->
							
								<li><label><spring:message	code='serviceRequest.label.helpdeskReference' /></label>
								<span>&nbsp;${serviceRequestForm.serviceRequest.helpdeskReferenceNumber}</span></li>
							
							<!-- partha added end -->
							
							
							</ul>
					</div><!-- first-column -->
					<div id="typeOfServiceNeeded" class="third-column">
						<ul class="form">
                  
                
                 <li> <label><spring:message code="Details.changeRequestDetails.label.requesterName"/></label>
                  <span id="requestorName">${fn:trim(serviceRequestForm.serviceRequest.requestor.firstName)}&nbsp;
                  ${fn:trim(serviceRequestForm.serviceRequest.requestor.lastName)}</span></li>
                  
                  
               
               		<li><label><spring:message code="Details.changeRequestDetails.label.accountName"/>	</label>
               		<span id = "accountName">${serviceRequestForm.serviceRequest.accountName }</span></li>
                
               <!-- Added for OPS Portal -->
                  	  
                  		<li><label><spring:message code="requestInfo.label.customerRequestDateTime"/></label>
                  		 <span id="customerRequestDateTime">${serviceRequestForm.serviceRequest.customerRequestDateTime }</span></li>
                  	 
                  	 
                  		<li><label><spring:message code="requestInfo.label.committedDateTime"/> </label>
                  		 <span id="committedDateTime">${serviceRequestForm.serviceRequest.committedDateTime }</span></li>
                  		 
                  		 
                  		<li><label><spring:message code="requestInfo.label.projectName"/> </label>
                  		 <span id="projectName">${serviceRequestForm.serviceRequest.projectName }</span></li>
                  		
                  		<li><label><spring:message code="requestInfo.label.projectPhase"/> </label>
                  		 <span id="projectPhase">${serviceRequestForm.serviceRequest.projectPhase }</span></li>
                  		 
                  		 
                  		<li> <label>&nbsp;&nbsp;<spring:message code="serviceRequest.label.eta" /></label>							
							<c:choose>
								<c:when test="${serviceRequestForm.serviceRequest.serviceRequestETA != null && fn:trim(serviceRequestForm.serviceRequest.serviceRequestETA) != '' }">
									<span>${serviceRequestForm.serviceRequest.serviceRequestETA}</span>
								</c:when>
								<c:otherwise> 
									<span>${serviceRequestForm.serviceRequest.serviceRequestSLA}</span>
								</c:otherwise>
							</c:choose>
							</li>
						</ul>
					</div><!-- second-column -->
					
					
					
				</div>
			
			<div class="div-left">	
			<table>
				<tr>
					<td style="display:block;">
						<img id="img_tab_associatedTicketsGrid" class="ui_icon_sprite expand-icon" src="<html:imagesPath/>transparent.png" onClick="expandCollapesGrid('tab_associatedTicketsGrid');" style="cursor:pointer;" />
					</td>
					<td>
						<a href="#" onClick="expandCollapesGrid('tab_associatedTicketsGrid');"><spring:message code="serviceRequest.label.associatedServiceTickets"/></a>
					</td>
				</tr>
			</table>
			</div>
			<div id="div_problemInfoPrintTable">
				<div id="tab_associatedTicketsGrid"  style="display:none;">
        			<div id="div_SRAssociatedContainer" style="width:100%;"></div>
        			<div id="div_associatedLoadingNotification" class='gridLoading'>
        				<!--spring:message code='loadingNotification' />&nbsp;&nbsp;--><img src="<html:imagesPath/>gridloading.gif"/>
    				</div>
        			<div id="div_associatedPagingArea"></div>
    			</div>
			</div>
			</div><!-- portlet-wrap-inner -->	
			<div class="portlet-footer">
				<div class="portlet-footer-inner">
				</div><!-- portlet-footer-inner -->
			</div><!-- portlet-footer -->
			</div>
	</div>
	
	<div id="div_deviceInfoPrint">
    <div id="tab_deviceInfo" class="portlet-wrap rounded">
    	<div class="portlet-header">
				<h3><spring:message code="serviceRequest.label.deviceInformation" /></h3>
		</div>
		<div class="portlet-wrap-inner">
			<div class="columns two">
					<div class="first-column">
						<div id="divDeviceImgs" class="device" style="width:300px">
								<img id="divImgs" height="${newImageHeight}" width="${newImageWidth}" src="${serviceRequestForm.serviceRequest.asset.productImageURL}"/>
						</div><!-- device -->
					</div><!-- first-column -->
					<div id="deviceSecondColumn" class="second-column">
						<dl>
 							<!--Changed for CI Defect # 10118 -->
							<dd><label><spring:message code="serviceRequest.label.productModel" />:</label>${serviceRequestForm.serviceRequest.descriptionLocalLang}</dd>
							<dd><label><spring:message code="serviceRequest.label.serialNumber" />:</label>${serviceRequestForm.serviceRequest.asset.serialNumber}</dd>
   						<c:if test="${!serviceRequestForm.serviceRequest.asset.notMyPrinter}">
   							<dd><label><spring:message code="serviceRequest.label.assetTag" />:</label>
   								<c:choose>
   									<c:when test="${serviceRequestForm.serviceRequest.asset.assetTag!=null}">${serviceRequestForm.serviceRequest.asset.assetTag}</c:when>
   									<c:otherwise><spring:message code="serviceRequest.label.n/a"/></c:otherwise>
   								</c:choose>
   							</dd>
							<dd><label><spring:message code="serviceRequest.label.ipAddress" />:</label>
							<c:choose>
									<%--Start: Added for Defect 3924-Jan Release--%>
   									<c:when test="${serviceRequestForm.serviceRequest.asset.ipAddress!=null}">
   									<a href="javascript:gotoControlPanel('${serviceRequestForm.serviceRequest.asset.controlPanelURL}')">${serviceRequestForm.serviceRequest.asset.ipAddress}
   									</a>
   									</c:when>
   									<%--End: Added for Defect 3924-Jan Release--%>
   									<c:otherwise><spring:message code="serviceRequest.label.n/a"/></c:otherwise>
   							</c:choose>
   							</dd>
   						</c:if>
						</dl>
					</div><!-- second-column -->
					<div id="devicethirdColumn" class="third-column">
						<dl>
							<dt><spring:message code="serviceRequest.label.serviceAddress" />:<br /><br />
							</dt>
							<util:addressOutput value="${serviceRequestForm.serviceRequest.serviceAddress}"></util:addressOutput>
							<dd><label><spring:message code="requestInfo.addressInfo.label.lbsIdentifier" /></label>${serviceRequestForm.serviceRequest.serviceAddress.LBSIdentifierFlag}</dd>
<%-- 						<c:if test="${serviceRequestForm.serviceRequest.serviceAddress.addressLine1==''||serviceRequestForm.serviceRequest.serviceAddress.addressLine1==null}"> --%>
<%-- 							${serviceRequestForm.serviceRequest.serviceAddress.addressName} --%>
<%-- 						</c:if></dt> --%>
<%-- 							${serviceRequestForm.serviceRequest.serviceAddress.addressLine1} --%>
<%-- 							<dd>${serviceRequestForm.serviceRequest.serviceAddress.addressLine2}</dd> --%>
<%-- 							<dd>${serviceRequestForm.serviceRequest.serviceAddress.addressLine3}</dd> --%>
<%-- 							<dd>${serviceRequestForm.serviceRequest.serviceAddress.city} --%>
<%-- 					  			&nbsp;${serviceRequestForm.serviceRequest.serviceAddress.state} --%>
<%-- 					  			&nbsp;${serviceRequestForm.serviceRequest.serviceAddress.county} --%>
<%-- 					  			&nbsp;${serviceRequestForm.serviceRequest.serviceAddress.postalCode}</dd> --%>
<%-- 							<dd>${serviceRequestForm.serviceRequest.serviceAddress.country}</dd> --%>
						</dl>
					</div><!-- third-column --> 
				</div><!-- columns -->
				
				 <!-- OPS ACTIVITY BLOCK -->
		  <c:if test="${not empty serviceRequestForm.serviceRequest.servicewebUpdateActivities}">		 
        <div class="portletBlock infoBox rounded shadow">
          <div class="columnsOne">
            <div class="columnInner">
             <h4 class="expand" id="activity"><spring:message code="Details.supplyRequestDetails.heading.activity"/></h4>
              <div class="collapse">
              <div class="icon"><img class="ui_icon_sprite widgets technician-large-icon" src="<html:imagesPath/>transparent.png" width="100" height="100" /></div>
              <div class="columnInner separatorV">
              
              <table class="shipment wFull">
                  <tr>
                    <td class="w70p"><div class="status">
<!--                         <div id="div_technicianProgressBar">PROGRESS BAR WILL COME HERE</div> -->
                        <div id="div_technicianProgressBar1" class="status-bar"></div><br/><!-- status-bar -->
                        <table border="0" cellspacing="0" cellpadding="0">
	                          <tr>
	                            <td><spring:message code="serviceRequest.label.inProcess"/></td>
	                            <td><spring:message code="serviceRequest.label.assigned"/></td>
	                            <td><spring:message code="reportStatus.PUBLISHOK"/></td>
	                          </tr>
	                        </table>
                        <!--<c:if test="${requestForm.serviceRequestTechnicianProgress gt 0}">-->
	                        
                       <!--  </c:if>
                        <c:if test="${requestForm.serviceRequestTechnicianProgress==0}">
	                        <table border="0" cellspacing="0" cellpadding="0">
	                          <tr>
	                            <td colspan="3"><spring:message code="serviceRequest.label.cancelled" /></td>
	                            
	                          </tr>
	                        </table>
                        </c:if> -->
                      </div></td>
                    <td>&nbsp;</td>
                  </tr>
                </table>
            <div class="activity_Table">
              <table border="0" width="100%" cellspacing="0" cellpadding="0" class="displayGrid" >
	                          <thead><tr><th class="short"><spring:message code="requestInfo.label.activityType"/></th><th class="short"><spring:message code="requestInfo.label.activityNumber"/></th><th class="short"><spring:message code="requestInfo.label.severity"/></th><th class="short"><spring:message code="requestInfo.label.activityDate"/></th><th class="short"><spring:message code="requestInfo.label.activityStatus"/></th><th class="long"><spring:message code="requestInfo.label.activityDescription"/></th><th class="short"><spring:message code="requestInfo.label.activitySerialNo"/></th><th class="short"><spring:message code="requestInfo.label.activitySubstatus"/></th><th class="long"><spring:message code="requestInfo.label.activitydeviceType"/></th></tr></thead>
	                          <c:forEach items="${serviceRequestForm.serviceRequest.servicewebUpdateActivities}" var="entry" >
	                          <tr>
	                            <td>${entry.activityType}</td>
	                            <td>${entry.activityId}</td>
	                            <td>${entry.severity}</td>
	                            <td>${entry.activityDate}</td>
	                            <td>${entry.activityStatus}</td>
	                            <td>${entry.activityDescription}</td>
	                            <td>${entry.activitySerialNumber}</td>
	                            <td>${entry.activitySubStatus}</td>
	                            <td>${entry.deviceType}</td>
	                          </tr>
	                          </c:forEach>
	                          
	                        </table>
	                 </div>      
              </div>
              </div>
              </div>
       </div>
       </div>
         </c:if>
        <!-- OPS ACTIVITY BLOCK -->
        
        
        <!-- OPS ORDER BLOCK -->
         <c:if test="${not empty serviceRequestForm.serviceRequest.orders}">
        <div class="portletBlock infoBox rounded shadow">
          <div class="columnsOne">
            <div class="columnInner">
             <h4 class="" id="activity"><spring:message code="requestInfo.label.orderHeader"/></h4>
              <div class="">
              <div class="icon"><img class="ui_icon_sprite widgets technician-large-icon" src="<html:imagesPath/>transparent.png" width="100" height="100" /></div>
              <div class="columnInner separatorV">
              
              
              
              <table border="0" width="100%" cellspacing="0" cellpadding="0" class="displayGrid">
	                         <thead> <tr><th><spring:message code="requestInfo.label.orderNumber"/></th><th>Order Type</th><th><spring:message code="requestInfo.label.severity"/></th><th><spring:message code="requestInfo.label.activitySubstatus"/></th><th><spring:message code="requestInfo.label.activityStatus"/></th><th><spring:message code="requestInfo.label.serviceProviderName"/></th></tr></thead>
	                         
	                          <c:forEach items="${serviceRequestForm.serviceRequest.orders}" var="entry" >
	                         
	                          <tr>
	                          <td>${entry.orderNumber}</td>
	                          <td>${entry.orderType}</td>
	                          <td>${entry.severity}</td>
	                          <td>${entry.subStatus}</td>
	                          
	                          <c:forEach items="${entry.orderLineItems}" var="entry2" varStatus="loopStatus" >
	                            
	                            
	                             
                                   <c:if test="${loopStatus.index == 0}">
                                   <td>${entry2.status}</td>
                                   <td>${entry2.serviceProviderName}</td>
                                  </c:if>
                                  
                                 </c:forEach>
	                          </tr>
	                          </c:forEach>
	                           
	                           
	                        </table>
              </div>
              </div>
              </div>
       </div>
       </div>
      </c:if>  
        <!-- OPS ORDER BLOCK -->
				
				
<%--Request History for Asset (CI) STARTS--%>
		 <div class="portletBlock infoBox rounded shadow">
          <div class="columnsOne">
            <div class="columnInner">
               <h4 class="expand" onclick="show_load();"> <!--show load called for asynshronous-CI7 -->
              <spring:message code="Details.manageAsset.heading.requestHistoryOnThisAsset"/></h4>
              <div  class="collapse">
<!--                <div class="grid-controls">  -->
<!--                       Expand-min Start -->
                       <div class="expand-min">
                      
                       <a href="#grid" id='headerMenuButton' style="display: none;"><spring:message code="requestInfo.link.customizeColumns"/></a> </div> <!--DONT REMOVE THIS CI BRD13-10-02--%>
                       
<!--                       NO CONTENT HERE PLS  -->
<!--                     </div> -->
<!--                     Changes for Defect 7490 MPS 2.1 -->
                <div id="gridContainerDiv_all_request_types"  class="gridbox gridbox_light" style="display: none;"></div>
                
				    		<div id="loadingNotification_request_type" class='gridLoading'>
	        					<!--spring:message code='loadingNotification' />&nbsp;&nbsp;--><img src="<html:imagesPath/>gridloading.gif"/>
	    					</div>
				    		<div class="pagination" id="paginationId">
				    		<span id="pagingArea_request_type"></span><span id="infoArea_req_status"></span> 
				    		</div>
<!-- 				   ENDS Changes for Defect 7490 MPS 2.1 -->
					
              </div>
            </div>
          </div>
        </div>
        </div>
        <%--Request History for Asset (CI) ENDS--%>
			
				</div>
				<div class="portlet-footer">
				<div class="portlet-footer-inner"></div><!-- portlet-footer-inner -->
			</div>
			</div>
	</div>

	
	<div id="div_statusPrint">
	<div id="tab_status" class="portlet-wrap rounded">
		<div class="portlet-header">
				<h3><spring:message code="serviceRequest.label.status" /></h3>
		</div>
		<div class="portlet-wrap-inner">
		<div>
			<table>
				<tr>
					<td style="display:block;">
						<img id="img_tab_statusGrid" class="ui_icon_sprite expand-icon" src="<html:imagesPath/>transparent.png" onClick="expandCollapesGrid('tab_statusGrid');" style="cursor:pointer;"/>
					</td>
					<td id="serviceRequestStatus" class="labelBold">
						<spring:message code="serviceRequest.label.serviceRequestStatus"/>:&nbsp;${serviceRequestForm.serviceRequest.serviceRequestStatus}
					</td>
				</tr>
			</table>
			</div>
			<div id="div_statusPrintTable">
				<div id="tab_statusGrid" style="display:block;">
					<div id="div_status_container" style="background-color: white;width:100%;"></div>
    				<div id="div_statusPagingArea"></div>
				</div>
			</div>
			<div class="status-wrap">
			<c:if test="${serviceRequestForm.serviceRequest.serviceActivityStatus!= null}">
			
			<div class="status-block">
						<h4><spring:message code="serviceRequest.label.technician" /></h4>
						<div class="status-info" style="display:block;">
							<div class="icon" id="manImage" >
								<img class="ui_icon_sprite widgets technician-large-icon" src="${serviceRequestForm.serviceHost}<html:imagesPath/>transparent.png" alt="technician" />
							</div><!-- icon -->
							<div class="floatL" id="manImage_Status">
							<div id="div_technicianProgressBar" class="status-bar"></div><br/><!-- status-bar -->
							<c:choose>
								<c:when test="${serviceRequestForm.serviceRequestTechnicianProgress==0}">
									<table width="480" style="clear:both;float: left;position: relative;left: 220px;">
									<tr >
										<td width="33%"></td>
										<td width="33%"><spring:message code="serviceRequest.label.cancelled" /></td>
										<td width="33%"></td>
									</tr>
								</table>
								</c:when>
								<c:otherwise>
									<table width="480" style="clear:both;float: left;position: relative;left: 220px;">
									<tr>
										<td width="33%"><spring:message code="serviceRequest.label.inProcess" /></td>
										<td width="33%"><spring:message code="serviceRequest.label.assigned" /></td>
										<td width="33%"><spring:message code="serviceRequest.label.complete" /></td>
									</tr>
								</table>
								</c:otherwise>
							</c:choose></div>
						</div><!-- status-info -->
						<div style="display:block;">
						<!--Changes Starts for LEX:AIR00072803 sabya-->
						<!--<table>
							<tr>
								<td>
								<img id="img_tab_technicianGrid" src="<html:imagesPath/>expand.jpg" onClick="expandCollapesGrid('tab_technicianGrid');" style="cursor:pointer;"/>
								</td>
								<td>
								<spring:message code="serviceRequest.label.showDetail"/>
								</td>
							</tr>
						</table>-->
						<!--Changes Continue for LEX:AIR00072803-->
						</div>
						<c:if test="${serviceRequestForm.serviceRequestTechnicianXML!= null}">
						
						<div style="display:block;">
						<!--Changes Continue for LEX:AIR00072803-->
						<table>
							<tr>
								<td>
								<img id="img_tab_technicianGrid" class="ui_icon_sprite expand-icon" src="<html:imagesPath/>transparent.png" onClick="expandCollapesGrid('tab_technicianGrid');" style="cursor:pointer;"/>
								</td>
								<td>
								<spring:message code="serviceRequest.label.showDetail"/>
								</td>
							</tr>
						</table>
						<!--Changes End for LEX:AIR00072803-->
						</div>
						<div id="div_technicianPrintTable">
							<div id="tab_technicianGrid" style="display:block;">
								<div id="div_technicianContainer" style="background-color: white;width:100%;"></div>
    							<div id="div_technicianPagingArea"></div>
    						</div>
    						
    				</div><!-- mygrid_container -->
    				</c:if>
			 </div><!-- status-block -->
			</c:if>
			
			<!-- In process orders START -->
			<c:if test="${serviceRequestForm.inProcessForm.shipmentXML!= null}">
			<div class="status-block">
						<h4><spring:message code="serviceRequest.label.inProcessOrders" /></h4>
						<div class="status-info"  style="display:block;">
							<div class="icon" id="inProcessImage" style="">
								<img class="ui_icon_sprite widgets inProcess-icon" src="${serviceRequestForm.serviceHost}<html:imagesPath/>transparent.png" alt="inProcess"/>
							</div><!-- icon -->
							<div id="div_inProgressBar" class="status-bar"></div><br/><!-- status-bar -->
							<c:choose>
								<c:when test="${serviceRequestForm.inProcessForm.shipmentProgress==0}">
									<table width="480" style="float: left;position: relative;left: 220px;">
										<tr height="30">
											<td width="33%"></td>
											<td width="33%"><spring:message code="serviceRequest.label.cancelled"/></td>
											<td width="33%"></td>
										</tr>
									</table>
								</c:when>
								<c:otherwise>
									<table width="480" style="float: left;position: relative;left: 220px;">
										<tr height="30">
											<td width="33%"><spring:message code="serviceRequest.label.inProcess" /></td>
											<td width="33%"><spring:message code="serviceRequest.label.shipped" /></td>
											<td width="33%"><spring:message code="serviceRequest.label.delivered" /></td>
										</tr>
									</table>		
								</c:otherwise>
							</c:choose>
						</div><!-- status-info -->
						<div style="display:block;">
						<table>
							<tr>
								<td>
								<img id="img_tab_inprocessGrid" class="ui_icon_sprite expand-icon" src="<html:imagesPath/>transparent.png" onClick="expandCollapesGrid('tab_inprocessGrid');" style="cursor:pointer;" />
								</td>
								<td>
								<spring:message code="serviceRequest.label.showDetail"/>
								</td>
							</tr>
						</table>
						</div>
						<div id="div_inprocessPrintTable">
							<div id="tab_inprocessGrid"  style="display:block;">
								<div id="div_inprocessGridContainer" class="gridbox_light" style="width:100%;"></div>
    							<div id="div_inprocessPagingArea"></div>
    						</div>
    					</div><!-- mygrid_container -->
			 </div><!-- status-block -->	
			</c:if>
			<!-- In process orders END -->
			
			<!-- Shipped orders START -->
			<c:forEach var="shipmentForm" items="${serviceRequestForm.serviceRequestShipments}" varStatus="status">
			<div class="status-block">
						<!-- <h4><spring:message code="serviceRequest.label.shipment" />:&nbsp;<a href="#" onClick="onTrackingNumberClick('${shipmentForm.carrier}', '${shipmentForm.trackingNumber}')">${shipmentForm.trackingNumber}</a></h4> -->
						<h4><spring:message code="serviceRequest.label.shipment" /></h4>
						<c:choose>
							<c:when test="${shipmentForm.hasProperUrl}">
								<spring:message code="serviceRequest.label.carrierName"/>:&nbsp;${shipmentForm.carrier}<br>
								<spring:message code="serviceRequest.label.trackingNumber"/>:&nbsp;<a href="${shipmentForm.carrierLink}" target="_blank">${shipmentForm.trackingNumber}</a>
							</c:when>
							<c:otherwise>
								<spring:message code="serviceRequest.label.carrierName"/>:&nbsp;<a href="${shipmentForm.carrierLink}" target="_blank">${shipmentForm.carrier}</a><br>
								<spring:message code="serviceRequest.label.trackingNumber"/>:&nbsp;${shipmentForm.trackingNumber}
							</c:otherwise>
						</c:choose>
						<div class="status-info" style="display:block;">
							<div class="icon" id="truckLeftImage${status.index}" >
								<img class="ui_icon_sprite widgets widgets-truck-rt-icon" src="${serviceRequestForm.serviceHost}<html:imagesPath/>transparent.png" alt="shipment" />
							</div><!-- icon -->
							<div id="div_shipmentProgressBar${status.count}" class="status-bar"></div><br/><!-- status-bar -->
							<c:choose>
								<c:when test="${shipmentForm.shipmentProgress==0}">
									<table width="480" style="float: left;position: relative;left: 220px;">
										<tr height="30">
											<td width="33%"></td>
											<td width="33%"><spring:message code="serviceRequest.label.cancelled"/></td>
											<td width="33%"></td>
										</tr>
									</table>
								</c:when>
								<c:otherwise>
									<table width="480" style="float: left;position: relative;left: 220px;">
										<tr height="30">
											<td width="33%"><spring:message code="serviceRequest.label.inProcess" /></td>
											<td width="33%"><spring:message code="serviceRequest.label.shipped" /></td>
											<td width="33%"><spring:message code="serviceRequest.label.delivered" /></td>
										</tr>
									</table>		
								</c:otherwise>
							</c:choose>
							
							
						</div><!-- status-info -->
						<div style="display:block;">
						<table>
							<tr>
								<td>
								<img id="img_tab_shipmentGrid${status.count}"  class="ui_icon_sprite expand-icon" src="<html:imagesPath/>transparent.png" onClick="expandCollapesGrid('tab_shipmentGrid${status.count}');" style="cursor:pointer;" />
								</td>
								<td>
								<spring:message code="serviceRequest.label.showDetail"/>
								</td>
							</tr>
						</table>
						</div>
						<div id="div_shipmentPrintTable${status.count}">
							<div id="tab_shipmentGrid${status.count}"  style="display:block;">
							<div id="div_shipmentGrid_container${status.count}" style="width:100%;"></div>
    						<div id="div_shipmentPagingArea${status.count}"></div>
    						</div>
    				</div><!-- mygrid_container -->
			 </div><!-- status-block -->	
			<script type="text/javascript">
			if(${shipmentForm.shipmentProgress==0}){
				jQuery('#div_shipmentProgressBar${status.count}').progressBar(100, { width:480,speed:25,increment:1,height:12,showText: false, boxImage: '<html:imagesPath/>progressBar/progressbar.gif', barImage:barImagesCancel} );
			}else{
				jQuery('#div_shipmentProgressBar${status.count}').progressBar(${shipmentForm.shipmentProgress}, { width:480,speed:25,increment:1,height:12,showText: false, boxImage: '<html:imagesPath/>progressBar/progressbar.gif', barImage:barImages} );
			}
			SRShipmentGrid${status.count} = new dhtmlXGridObject('div_shipmentGrid_container${status.count}');
	 		SRShipmentGrid${status.count}.setImagePath("<html:imagesPath/>gridImgs/");
	 		SRShipmentGrid${status.count}.setHeader(autoAppendPlaceHolder("<spring:message code='serviceRequest.listHeader.shipment'/>", 6));
	 		SRShipmentGrid${status.count}.setColAlign("left,left,left,left,left,left");
	 		SRShipmentGrid${status.count}.setColTypes("ro,ro,ro,ro,ro,ro");
	 		SRShipmentGrid${status.count}.setColSorting("str,str,str,str,str,str");
	 		SRShipmentGrid${status.count}.setInitWidths("150,390,*,*,*,*");
	 		SRShipmentGrid${status.count}.enableAutoWidth(true);
	 		SRShipmentGrid${status.count}.enableMultiline(true);
	 		SRShipmentGrid${status.count}.enableAutoHeight(true);
	 		SRShipmentGrid${status.count}.init(); 
	 		SRShipmentGrid${status.count}.prftInit();
	 		SRShipmentGrid${status.count}.enablePaging(true, 5, 10, "div_shipmentPagingArea${status.count}", true);
	 		SRShipmentGrid${status.count}.setPagingSkin("bricks");
	 		SRShipmentGrid${status.count}.setSkin("light");
	 		SRShipmentGrid${status.count}.loadXMLString('${shipmentForm.shipmentXML}');
	 		SRShipmentGrid${status.count}.sortRows(0,"str","asc");  
	 		SRShipmentGrid${status.count}.setSortImgState(true, 0, 'asc');	
			</script>
			</c:forEach>
			<script type="text/javascript">
				var trackingNumbers = new Array();
	 			<c:forEach items="${serviceRequestForm.serviceRequestShipments}" var="trackingNumberForm" varStatus = "status" >
	 				trackingNumbers[${status.index}] = "${trackingNumberForm.trackingNumber}";
	 			</c:forEach>
			</script>
			<!-- Shipped orders END -->
			
			<!-- In progress return without tracking number START -->
			<c:if test="${not empty serviceRequestForm.inProgressReturnNoTrackNumForm}">
			<div class="status-block">
				<h4><spring:message code="serviceRequest.label.inProcessReturns" /></h4>
				<div class="status-info" style="display:block;">
					<div class="icon" id="returnInProcessImage" >
						<img class="ui_icon_sprite widgets inProcess-icon" src="${serviceRequestForm.serviceHost}<html:imagesPath/>transparent.png" alt="shipment" />
					</div><!-- icon -->
					<div id="div_inProgressReturnNoTrackNumProgressBar" class="status-bar"></div><br/><!-- status-bar -->
					<c:choose>
						<c:when test="${serviceRequestForm.inProgressReturnNoTrackNumForm.shipmentProgress==1}">
							<table width="480" style="float: left;position: relative;left: 220px;">
								<tr height="30">
									<td width="33%"></td>
									<td width="33%"><spring:message code="serviceRequest.label.cancelled"/></td>
									<td width="33%"></td>
								</tr>
							</table>
						</c:when>
						<c:otherwise>
							<table width="480" style="float: left;position: relative;left: 220px;">
								<tr height="30">
									<td width="33%"><spring:message code="serviceRequest.label.inProcess" /></td>
									<td width="33%"></td>
									<td width="33%"><spring:message code="serviceRequest.label.delivered" /></td>
								</tr>
							</table>
						</c:otherwise>
					</c:choose>
					
				</div><!-- status-info -->
				<div style="display:block;">
				<table>
					<tr>
						<td>
						<img id="img_tab_inProgressReturnNoTrackNumGrid" class="ui_icon_sprite expand-icon" src="<html:imagesPath/>transparent.png" onClick="expandCollapesGrid('tab_inProgressReturnNoTrackNumGrid');" style="cursor:pointer;" />
						</td>
						<td>
						<spring:message code="serviceRequest.label.showDetail"/>
						</td>
					</tr>
				</table>
				</div>
				<div id="div_inProgressReturnNoTrackNumPrintTable">
					<div id="tab_inProgressReturnNoTrackNumGrid"  style="display:block;">
						<div id="div_inProgressReturnNoTrackNumGrid_container" class="gridbox_light" style="width:100%;"></div>
  						<div id="div_inProgressReturnNoTrackNumInfoArea"></div>
  					</div>
 				</div><!-- mygrid_container -->
			</div><!-- status-block -->	
			<script type="text/javascript">
	 		/** 
	 	 	  *  Return Shipment Grid config begin
	 	 	  */
	 	 	if(${serviceRequestForm.inProgressReturnNoTrackNumForm.shipmentProgress==1}){
	 	 		jQuery('#div_inProgressReturnNoTrackNumProgressBar').progressBar(100, { width:480,speed:25,increment:1,height:12,showText: false, boxImage: '<html:imagesPath/>progressBar/progressbar.gif', barImage:barImagesCancel} );
	 	 	}else{
	 	 		jQuery('#div_inProgressReturnNoTrackNumProgressBar').progressBar(${serviceRequestForm.inProgressReturnNoTrackNumForm.shipmentProgress}, { width:480,speed:25,increment:1,height:12,showText: false, boxImage: '<html:imagesPath/>progressBar/progressbar.gif', barImage:barImages} );
	 	 	}
	 	 	
	 	 	inProgressReturnNoTrackNumGrid = new dhtmlXGridObject('div_inProgressReturnNoTrackNumGrid_container');
	 	 	inProgressReturnNoTrackNumGrid.setImagePath("<html:imagesPath/>gridImgs/");
	 	 	inProgressReturnNoTrackNumGrid.setHeader(autoAppendPlaceHolder("<spring:message code='serviceRequest.listHeader.shipment'/>", 6));
	 	 	inProgressReturnNoTrackNumGrid.setColAlign("left,left,left,left,left,left");
	 	 	inProgressReturnNoTrackNumGrid.setColTypes("ro,ro,ro,ro,ro,ro");
	 	 	inProgressReturnNoTrackNumGrid.setColSorting("str,str,str,str,str,str");
	 	 	inProgressReturnNoTrackNumGrid.setInitWidths("150,390,*,*,*,*");
	 	 	inProgressReturnNoTrackNumGrid.enableAutoWidth(true);
	 	 	inProgressReturnNoTrackNumGrid.enableMultiline(true);
	 	 	inProgressReturnNoTrackNumGrid.enableAutoHeight(true);
	 	 	inProgressReturnNoTrackNumGrid.init(); 
	 	 	inProgressReturnNoTrackNumGrid.enablePaging(true, 5, 10, "div_inProgressReturnNoTrackNumInfoArea", true);
	 	 	inProgressReturnNoTrackNumGrid.setPagingSkin("bricks");
	 	 	inProgressReturnNoTrackNumGrid.setSkin("light");
	 	 	inProgressReturnNoTrackNumGrid.loadXMLString('${serviceRequestForm.inProgressReturnNoTrackNumForm.shipmentXML}'); 
	 	 	inProgressReturnNoTrackNumGrid.sortRows(0,"str","asc");  
	 	 	inProgressReturnNoTrackNumGrid.setSortImgState(true, 0, 'asc');	
			</script>
			</c:if>
			<!-- In progress return without tracking number END -->
			
			<!-- Returns with tracking number START -->
			<c:forEach var="returnShipmentForm" items="${serviceRequestForm.serviceRequestReturnShipments}" varStatus="status">
			<div class="status-block">
						<!-- <h4><spring:message code="serviceRequest.label.return" />:&nbsp;<a href="#" onClick="onTrackingNumberClick('${returnShipmentForm.carrier}', '${returnShipmentForm.trackingNumber}')">${returnShipmentForm.trackingNumber}</a></h4> -->
						<h4><spring:message code="serviceRequest.label.return" /></h4>
						<c:choose>
							<c:when test="${shipmentForm.hasProperUrl}">
								<spring:message code="serviceRequest.label.carrierName"/>:&nbsp;${returnShipmentForm.carrier}<br>
								<spring:message code="serviceRequest.label.trackingNumber"/>:&nbsp;<a href="${shipmentForm.carrierLink}" target="_blank">${returnShipmentForm.trackingNumber}</a>
							</c:when>
							<c:otherwise>
								<spring:message code="serviceRequest.label.carrierName"/>:&nbsp;<a href="${shipmentForm.carrierLink}" target="_blank">${returnShipmentForm.carrier}</a><br>
								<spring:message code="serviceRequest.label.trackingNumber"/>:&nbsp;${returnShipmentForm.trackingNumber}
							</c:otherwise>
						</c:choose>
						<div class="status-info" style="display:block;">
							<div class="icon" id="truckRightImage${status.index}" >
								<img class="ui_icon_sprite widgets widgets-truck-lf-icon" src="${serviceRequestForm.serviceHost}<html:imagesPath/>transparent.png" alt="shipment" />
							</div><!-- icon -->
							<div id="div_returnProgressBar${status.count}" class="status-bar"></div><br/><!-- status-bar -->
							<c:choose>
							<c:when test="${returnShipmentForm.shipmentProgress==1}">
								<table width="480" style="float: left;position: relative;left: 220px;">
									<tr height="30">
										<td width="33%"></td>
										<td width="33%"><spring:message code="serviceRequest.label.cancelled"/></td>
										<td width="33%"></td>
									</tr>
								</table>
							</c:when>
							<c:otherwise>
								<table width="480" style="float: left;position: relative;left: 220px;">
									<tr height="30">
										<td width="33%"><spring:message code="serviceRequest.label.inProcess" /></td>
										<td width="33%"></td>
										<td width="33%"><spring:message code="serviceRequest.label.delivered" /></td>
									</tr>
								</table>
							</c:otherwise>
							</c:choose>
							
						</div><!-- status-info -->
						<div style="display:block;">
						<table>
							<tr>
								<td>
								<img id="img_tab_returnShipmentGrid${status.count}" class="ui_icon_sprite expand-icon" src="<html:imagesPath/>transparent.png" onClick="expandCollapesGrid('tab_returnShipmentGrid${status.count}');" style="cursor:pointer;" />
								</td>
								<td>
								<spring:message code="serviceRequest.label.showDetail"/>
								</td>
							</tr>
						</table>
						</div>
						<div id="div_returnShipmentPrintTable${status.count}">
							<div id="tab_returnShipmentGrid${status.count}"  style="display:block;">
							<div id="div_returnShipmentGrid_container${status.count}" style="width:100%;"></div>
    						<div id="div_returnShipmentInfoArea${status.count}"></div>
    						</div>
    				</div><!-- mygrid_container -->
			 </div><!-- status-block -->	
			<script type="text/javascript">
	 		/** 
	 	 	  *  Return Shipment Grid config begin
	 	 	  */
	 	 	
			if(${returnShipmentForm.shipmentProgress==1}){
				jQuery('#div_returnProgressBar${status.count}').progressBar(100, { width:480,speed:25,increment:1,height:12,showText: false, boxImage: '<html:imagesPath/>progressBar/progressbar.gif', barImage:barImagesCancel} );
			}else{
				jQuery('#div_returnProgressBar${status.count}').progressBar(${returnShipmentForm.shipmentProgress}, { width:480,speed:25,increment:1,height:12,showText: false, boxImage: '<html:imagesPath/>progressBar/progressbar.gif', barImage:barImages} );
			}
		 	SRReturnShipmentGrid${status.count} = new dhtmlXGridObject('div_returnShipmentGrid_container${status.count}');
	 	 	SRReturnShipmentGrid${status.count}.setImagePath("<html:imagesPath/>gridImgs/");
	 	 	SRReturnShipmentGrid${status.count}.setHeader(autoAppendPlaceHolder("<spring:message code='serviceRequest.listHeader.shipment'/>",6));
	 	 	SRReturnShipmentGrid${status.count}.setColAlign("left,left,left,left,left,left");
	 	 	SRReturnShipmentGrid${status.count}.setColTypes("ro,ro,ro,ro,ro,ro");
	 	 	SRReturnShipmentGrid${status.count}.setColSorting("str,str,str,str,str,str");
	 	 	SRReturnShipmentGrid${status.count}.setInitWidths("150,390,*,*,*,*");
	 	 	SRReturnShipmentGrid${status.count}.enableAutoWidth(true);
	 	 	SRReturnShipmentGrid${status.count}.enableMultiline(true);
	 	 	SRReturnShipmentGrid${status.count}.enableAutoHeight(true);
	 	 	SRReturnShipmentGrid${status.count}.init(); 
	 	 	SRReturnShipmentGrid${status.count}.enablePaging(true, 5, 10, "div_returnShipmentInfoArea${status.count}", true);
	 	 	SRReturnShipmentGrid${status.count}.setPagingSkin("bricks");
	 	 	SRReturnShipmentGrid${status.count}.setSkin("light");
	 	 	SRReturnShipmentGrid${status.count}.loadXMLString('${returnShipmentForm.shipmentXML}'); 
	 	 	SRReturnShipmentGrid${status.count}.sortRows(0,"str","asc");  
	 	 	SRReturnShipmentGrid${status.count}.setSortImgState(true, 0, 'asc');	
			</script>
			</c:forEach>
			<script type="text/javascript">
				var returnTrackingNumbers = new Array();
	 			<c:forEach items="${serviceRequestForm.serviceRequestReturnShipments}" var="returnShipmentNumForm" varStatus = "status" >
	 				returnTrackingNumbers[${status.index}] = "${returnShipmentNumForm.trackingNumber}";
	 			</c:forEach>
			</script>
			<!-- Returns with tracking number END -->
			
			<!-- Delivered return without tracking number START -->
			<c:if test="${not empty serviceRequestForm.deliveredReturnNoTrackNumForm}">
			<div class="status-block">
				<h4><spring:message code="serviceRequest.label.returnsTrackNumNotAvailable" /></h4>
				<div class="status-info" style="display:block;">
					<div class="icon" id="noTrackingNumtruckLeftImage" >
						<img class="ui_icon_sprite widgets widgets-truck-lf-icon" src="${serviceRequestForm.serviceHost}<html:imagesPath/>transparent.png" alt="shipment" />
					</div><!-- icon -->
					<div id="div_deliveredReturnNoTrackNumProgressBar" class="status-bar"></div><br/><!-- status-bar -->
					<c:choose>
					<c:when test="${serviceRequestForm.deliveredReturnNoTrackNumForm.shipmentProgress==1}">
						<table width="480" style="float: left;position: relative;left: 220px;">
						<tr height="30">
							<td width="33%"></td>
							<td width="33%"><spring:message code="serviceRequest.label.cancelled"/></td>
							<td width="33%"></td>
						</tr>
					</table>
					</c:when>
					<c:otherwise>
						<table width="480" style="float: left;position: relative;left: 220px;">
						<tr height="30">
							<td width="33%"><spring:message code="serviceRequest.label.inProcess" /></td>
							<td width="33%"></td>
							<td width="33%"><spring:message code="serviceRequest.label.delivered" /></td>
						</tr>
					</table>
					</c:otherwise>
					</c:choose>
					
				</div><!-- status-info -->
				<div style="display:block;">
				<table>
					<tr>
						<td>
						<img id="img_tab_deliveredReturnNoTrackNumGrid" class="ui_icon_sprite expand-icon" src="<html:imagesPath/>transparent.png" onClick="expandCollapesGrid('tab_deliveredReturnNoTrackNumGrid');" style="cursor:pointer;" />
						</td>
						<td>
						<spring:message code="serviceRequest.label.showDetail"/>
						</td>
					</tr>
				</table>
				</div>
				<div id="div_deliveredReturnNoTrackNumPrintTable">
					<div id="tab_deliveredReturnNoTrackNumGrid"  style="display:block;">
						<div id="div_deliveredReturnNoTrackNumGrid_container" class="gridbox_light" style="width:100%;"></div>
  						<div id="div_deliveredReturnNoTrackNumInfoArea"></div>
  					</div>
 				</div><!-- mygrid_container -->
			</div><!-- status-block -->	
			<script type="text/javascript">
	 		/** 
	 	 	  *  Return Shipment Grid config begin
	 	 	  */
	 	 	
			if(${serviceRequestForm.deliveredReturnNoTrackNumForm.shipmentProgress==1}){
				jQuery('#div_deliveredReturnNoTrackNumProgressBar').progressBar(100, { width:480,speed:25,increment:1,height:12,showText: false, boxImage: '<html:imagesPath/>progressBar/progressbar.gif', barImage:barImagesCancel} );
			}else{
				jQuery('#div_deliveredReturnNoTrackNumProgressBar').progressBar(${serviceRequestForm.deliveredReturnNoTrackNumForm.shipmentProgress}, { width:480,speed:25,increment:1,height:12,showText: false, boxImage: '<html:imagesPath/>progressBar/progressbar.gif', barImage:barImages} );
			}
		 	deliveredReturnNoTrackNumGrid = new dhtmlXGridObject('div_deliveredReturnNoTrackNumGrid_container');
	 	 	deliveredReturnNoTrackNumGrid.setImagePath("<html:imagesPath/>gridImgs/");
	 	 	deliveredReturnNoTrackNumGrid.setHeader(autoAppendPlaceHolder("<spring:message code='serviceRequest.listHeader.shipment'/>",6));
	 	 	deliveredReturnNoTrackNumGrid.setColAlign("left,left,left,left,left,left");
	 	 	deliveredReturnNoTrackNumGrid.setColTypes("ro,ro,ro,ro,ro,ro");
	 	 	deliveredReturnNoTrackNumGrid.setColSorting("str,str,str,str,str,str");
	 	 	deliveredReturnNoTrackNumGrid.setInitWidths("150,390,*,*,*,*");
	 	 	deliveredReturnNoTrackNumGrid.enableAutoWidth(true);
	 	 	deliveredReturnNoTrackNumGrid.enableMultiline(true);
	 	 	deliveredReturnNoTrackNumGrid.enableAutoHeight(true);
	 	 	deliveredReturnNoTrackNumGrid.init(); 
	 	 	deliveredReturnNoTrackNumGrid.enablePaging(true, 5, 10, "div_deliveredReturnNoTrackNumInfoArea", true);
	 	 	deliveredReturnNoTrackNumGrid.setPagingSkin("bricks");
	 	 	deliveredReturnNoTrackNumGrid.setSkin("light");
	 	 	deliveredReturnNoTrackNumGrid.loadXMLString('${serviceRequestForm.deliveredReturnNoTrackNumForm.shipmentXML}'); 
	 	 	deliveredReturnNoTrackNumGrid.sortRows(0,"str","asc");  
	 	 	deliveredReturnNoTrackNumGrid.setSortImgState(true, 0, 'asc');	
			</script>
			</c:if>
			<!-- Delivered return without tracking number END -->
	</div>
	</div>
	<div class="portlet-footer">
		<div class="portlet-footer-inner"></div><!-- portlet-footer-inner -->
    </div><!-- portlet-footer -->
	</div>
	</div>
	<!-- end of status part -->
   <div id="div_contactInfoPrint">
   <div id="tab_contactInfo" class="portlet-wrap rounded">
			<div class="portlet-header">
				<h3><spring:message code="serviceRequest.label.contactInfo" /></h3>
			</div><!-- portlet-header -->
			<div class="portlet-wrap-inner">
				<div class="columns two">
					<div id="contactInfoFirstColum" class="first-column">
						<dl>
							<dt><spring:message code="serviceRequest.label.requestorInfo" /></dt>
							<dd><label><spring:message code="serviceRequest.label.name" />:</label> ${serviceRequestForm.serviceRequest.requestor.firstName}&nbsp;${serviceRequestForm.serviceRequest.requestor.lastName}</dd>
							<dd><label><spring:message code="serviceRequest.label.workPhone" />:</label> ${serviceRequestForm.serviceRequest.requestor.workPhone}</dd>
							<!-- Fix for Floor Support Defect-12762 -->
							<dd style="word-wrap: break-word;"><label><spring:message code="serviceRequest.label.email" />:</label> ${serviceRequestForm.serviceRequest.requestor.emailAddress}</dd>
						</dl>
					</div><!-- first-column -->
					<div id="contactInfoSecondColum" class="second-column">
						<dl>
							<dt><spring:message code="serviceRequest.label.primaryContactInformation" /></dt>
							<dd><label><spring:message code="serviceRequest.label.name" />:</label> ${serviceRequestForm.serviceRequest.primaryContact.firstName}&nbsp;${serviceRequestForm.serviceRequest.primaryContact.lastName}</dd>
							<dd><label><spring:message code="serviceRequest.label.telephone" />:</label> ${serviceRequestForm.serviceRequest.primaryContact.workPhone}</dd>
							<!-- Fix for Floor Support Defect-12762 -->
							<dd style="word-wrap: break-word;"><label><spring:message code="serviceRequest.label.email" />:</label>${serviceRequestForm.serviceRequest.primaryContact.emailAddress}</dd>

						</dl>
					</div><!-- second-column -->
					<c:if test="${serviceRequestForm.serviceRequest.secondaryContact!= null}">
					<c:if test="${serviceRequestForm.serviceRequest.secondaryContact.firstName != ''||
								  serviceRequestForm.serviceRequest.secondaryContact.lastName != ''||
								  serviceRequestForm.serviceRequest.secondaryContact.workPhone!=''||
								  serviceRequestForm.serviceRequest.secondaryContact.emailAddress!=''}">
					<div id="contactInfoThirdColum" class="third-column">
						<dl>
							<dt><spring:message code="serviceRequest.label.secondaryContactinformation" /></dt>
							<dd><label><spring:message code="serviceRequest.label.name" />:</label>${serviceRequestForm.serviceRequest.secondaryContact.firstName}&nbsp;${serviceRequestForm.serviceRequest.secondaryContact.lastName}</dd>
							<dd><label><spring:message code="serviceRequest.label.telephone" />:</label>${serviceRequestForm.serviceRequest.secondaryContact.workPhone}</dd>
							<!-- Fix for Floor Support Defect-12762 -->
					        <dd style="word-wrap: break-word;"><label><spring:message code="serviceRequest.label.email" />:</label>${serviceRequestForm.serviceRequest.secondaryContact.emailAddress}</dd>
						</dl>
					</div><!-- third-column --> 
					</c:if>
					</c:if>
				</div><!-- columns -->
			</div><!-- portlet-wrap-inner -->	
			<div class="portlet-footer">
				<div class="portlet-footer-inner"></div><!-- portlet-footer-inner -->
			</div><!-- portlet-footer -->
		</div><!-- portlet-wrap -->
	</div>
	
	<div id="div_notificationsPrint">
	<c:if test="${serviceRequestForm.serviceRequestNotificationsXML!= null}">
    <div id="tab_notifications"  class="portlet-wrap">
    <div class="portlet-wrap rounded">
			<div class="portlet-header">
			<h3><spring:message code="serviceRequest.label.notifications" /></h3>
			</div><!-- portlet-header -->
			<div class="portlet-wrap-inner">
				<div id="div_notificationsPrintTable">
					<div id="notificationsGrid" style="display:block;">
						<div id="div_notifications_container" style="width:100%;"></div>
    					<div id="div_notificationsPagingArea"></div>
    				</div>
    			</div>
			</div><!-- portlet-wrap-inner -->	
			<div class="portlet-footer">
				<div class="portlet-footer-inner">
				</div><!-- portlet-footer-inner -->
			</div><!-- portlet-footer -->
		</div><!-- portlet-wrap -->
	</div>
	</c:if>
	</div>
	<!-- **********************Download Attachments START******************************* 
	<p class="inlineTitle"><spring:message code="serviceRequest.heading.downloadAttachments"/></p>
				 <div class="columnInner">
						  <div id="fileListDiv" >
							
							<table class="displayGrid rounded shadow wHalf" id="fileListTable">
							</table>
							</div>
							<div id="test"></div>
							 <div class="lineClear"></div>							
						  </div>
     <!-- **********************Download Attachments END******************************* -->
     <!-- **********************Download Attachments START******************************* -->
     
    <div class="portlet-wrap" style="width:50%">
    <div class="portlet-wrap rounded">
			<div class="portlet-header">
			<h3><spring:message code="serviceRequest.heading.downloadAttachments" /></h3>
			</div><!-- portlet-header -->
			<div class="portlet-wrap-inner">
				<div id="fileListDiv" >
							
							<table class="displayGrid rounded shadow" id="fileListTable">
							</table>
							</div>
							<div id="test"></div>
							 <div class="lineClear"></div>							
						  </div>
			</div><!-- portlet-wrap-inner -->	
		</div><!-- portlet-wrap -->

     <!-- **********************Download Attachments END******************************* -->
	</div>
	</div>

<table id="serviceButtonContainer" width="100%">
	<tr><td align="right" width="80%">
 	</td>
 	<td width="20%" style="text-align:right;padding-right:25px">
 		<button class="button" type="button" onclick="backToHistory();"><spring:message code="Details.changeRequestDetails.button.back"/></button>
 	</td></tr>
</table>
</div>
<div id="dialog_createNew" style="display:none;">
<div id="totalContent">

<div id="createNewRequestPageData"></div>
<jsp:include page="/WEB-INF/jsp/common/accountSelection.jsp"/> 
</div>
</div>
<div id="serviceRequestDetailDiv"></div>
<jsp:include page="/WEB-INF/jsp/common/dynamicGridInitialize.jsp"></jsp:include> <%--Added for CI BRD13-10-02--%>
<div id="emaildiv" style="display: none;"></div>
<script type="text/javascript">
//Added for CI BRD13-10-02--START
var assetRowId1="${serviceRequestForm.serviceRequest.asset.assetId}";
var accountId='${serviceRequestForm.serviceRequest.asset.account.accountId}';
var linkClicked=1;
var timezoneOffsetServiceRequest="";
//Added for CI BRD13-10-02--END
	// following variables are declared in dynamicGridInitialize
	// Changes for Defect 7490 MPS 2.1
	pagingArea="pagingArea_request_type";infoArea="infoArea_req_status";headerMenuButton="headerMenuButton";loadingNotification="loadingNotification_request_type";backFilterValues="";
	gridCreationId="gridContainerDiv_all_request_types";
	// ENDS Changes for Defect 7490 MPS 2.1

	/*********************************************************************************
	***************************** SR associated grid configuration *******************
	**********************************************************************************/
	var hostURL = "${serviceRequestForm.serviceHost}<html:imagesPath/>progressBarforEmail/";
	var deviceImageURL = "${serviceRequestForm.serviceRequest.asset.productImageURL}";
	var srNum = "${serviceRequestForm.serviceRequest.serviceRequestNumber}";
	var tempHTML;
	var doPrint = false;
	var doEmail = false;
	isAssociatedGridAlreadyLoaded = false;
    isHistoryGridAlreadyLoaded = false;
 	SRAssociatedGrid = new dhtmlXGridObject('div_SRAssociatedContainer');
 	SRAssociatedGrid.setImagePath("<html:imagesPath/>gridImgs/");
 	SRAssociatedGrid.setHeader(autoAppendPlaceHolder("<spring:message code='serviceRequest.listHeader.associatedPastServiceRequests'/>",9));
 	SRAssociatedGrid.setColAlign("left,left,left,left,left,left,left,left,left");
 	SRAssociatedGrid.setColTypes("link,ro,ro,ro,ro,ro,ro,ro,ro");
 	SRAssociatedGrid.setColSorting("str,str,str,str,str,str,str,str,str");
 	SRAssociatedGrid.setInitWidths("120,140,140,140,*,0,0,0,0");
 	SRAssociatedGrid.enableAutoWidth(true);
 	SRAssociatedGrid.enableMultiline(true);
 	SRAssociatedGrid.enableAutoHeight(true);
 	SRAssociatedGrid.setSkin("light");
 	SRAssociatedGrid.setSizes();
 	SRAssociatedGrid.init(); 
 	SRAssociatedGrid.prftInit();
 	SRAssociatedGrid.enablePaging(true, 5, 10, "div_associatedPagingArea", true);
 	SRAssociatedGrid.setPagingSkin("bricks");
 	SRAssociatedGrid.setColumnHidden(5,true);
 	SRAssociatedGrid.setColumnHidden(6,true);
 	SRAssociatedGrid.setColumnHidden(7,true);
 	SRAssociatedGrid.setColumnHidden(8,true);
 	
 	SRAssociatedGrid.setSortImgState(true, 0, 'asc');
 	SRAssociatedGrid.attachEvent("onXLS", function() {
 		document.getElementById("div_associatedLoadingNotification").style.display = 'block';
	});
 	SRAssociatedGrid.attachEvent("onXLE", function() {
 		document.getElementById("div_associatedLoadingNotification").style.display = 'none';
 		isAssociatedGridAlreadyLoaded = true;
 		if(doPrint && isHistoryGridAlreadyLoaded){
 			print();
 		}
 		if(doEmail && isHistoryGridAlreadyLoaded){
 			showEmailPopup();
 			hideSelect();
 		}
 		setTimeout(function(){
    		rebrandPagination();
    	
    	},100);
	});
 	
 	/*********************************************************************************
	******************************History Grid configuration********************
	**********************************************************************************/
	//Script for Request History Grid CI (BRD13-10-02) STARTS
	var counter=0;
	//Added for CI BRD13-10-02--START
	function show_load(){
			counter++;
			if(!(counter%2)==0)
				{
			initialiseGrid();
			//Changes for CI7 BRD 14-06-07
			requestListGrid.detachHeader(1);
			jQuery("#gridContainerDiv_all_request_types").css('height','25px');
			jQuery("#gridContainerDiv_all_request_types").show();
				}
		}
	//Added for CI BRD13-10-02--END
	
	pageSize=5,pagesIngrp=10;
	JSON_Param["<%=gridConfigurationValues[0]%>"]="<spring:message code='suppliesmanagement.details.srhistorygrid'/>";
	JSON_Param["<%=gridConfigurationValues[1]%>"]=",,,,";
	JSON_Param["<%=gridConfigurationValues[2]%>"]="left,left,left,left,left";
	JSON_Param["<%=gridConfigurationValues[3]%>"]="130,160,120,120,*";//Changes for CI7 BRD 14-06-07
	JSON_Param["<%=gridConfigurationValues[4]%>"]="ro,ro,ro,ro,ro";
	JSON_Param["<%=gridConfigurationValues[5]%>"]="str,str,str,str,str";
	JSON_Param["<%=gridConfigurationValues[6]%>"]="0,1,2,3,4"; //Changed for CI Defect #8217
	JSON_Param["<%=gridConfigurationValues[7]%>"]="0,des";
	JSON_Param["<%=gridConfigurationValues[8]%>"]=""; //Changed for CI Defect #8217
	JSON_Param["<%=JSON_COMBO_FILTER%>1"]="";
	JSON_Param["<%=JSON_RESOURCE_URL%>"]="<portlet:resourceURL id='retrieveHistoryList'/>";
	JSON_Param["<%=gridSavingParams[0]%>"]="";
	JSON_Param["<%=gridSavingParams[1]%>"]="";
	JSON_Param["<%=gridSavingParams[2]%>"]="";
	JSON_Param["<%=gridSavingParams[3]%>"]="";
	
	initURLParams = function(){
		xmlURLQueryParams={
				urlParameters:["direction","orderBy","timezoneOffset","accountRowId","srHistoryType","assetRowId"],
				setParameters : function(){
										this["direction"]=requestListGrid.a_direction;
										this["orderBy"]=requestListGrid.getSortColByOffset();
										this["timezoneOffset"]=timezoneOffset;
										this["accountRowId"]=accountId;
										this["srHistoryType"]="ALL_REQUESTS";
										this["assetRowId"]=assetRowId1;
										
										},
				setLoadXMLUrl : function(){
												xmlURL=new String(JSON_Param["<%=JSON_RESOURCE_URL%>"]);
												this.setParameters();
												for(i=0;i<this.urlParameters.length;i++){
													if(this[this.urlParameters[i]]!=null)
														xmlURL+="&"+this.urlParameters[i]+"="+this[this.urlParameters[i]];
														
												}	
										}
				};
		};
//  	function customColumnSort(ind) {
//  	    callOmnitureAction('Service Request', 'Service Request DrillDown Sort History Grid');
// 		var a_state = SRHistoryGrid.getSortingState();
// 		if(a_state[0] == (ind)){
// 			SRHistoryGrid.a_direction = ((a_state[1] == "asc") ? "des":"asc" );
// 		}else {
// 			SRHistoryGrid.a_direction = "asc" ;
// 			SRHistoryGrid.columnChanged = true;
// 		}
// 		SRHistoryGrid.sortIndex = ind;
// 		customizedGridURL = updateGridURL(customizedGridURL, SRHistoryGrid.getSortColByOffset(), SRHistoryGrid.a_direction, null);
//         reloadGrid();
// 		return true;
// 	}
	var searchCriteria="";
// 	function populateURLCriterias(){
//     	var url = "<portlet:resourceURL id='retrieveHistoryList'/>";
//         url = url + "&orderBy=" + SRHistoryGrid.getSortColByOffset();
//         if(SRHistoryGrid.a_direction != null && SRHistoryGrid.a_direction != "") {
//             url = url + "&direction=" + SRHistoryGrid.a_direction;
//         }
//         return url;
//     }

//     function resetCallback() {
//         clearMessage();
//         /* colsOrder = "0,1,2,3,4,5,6,7,8,9,10,11,12,13,14";
//         colsWidth = "px:125,80,110,140,140,110,110,80,80,60,80,150,150,150,125"; */
//         colsOrder = "0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15";
//         colsWidth = "px:130,100,120,120,100,100,100,150,100,80,80,120,120,120,120,100";	// changed for CI5
//         colsSorting = "1,asc";
//         colsHidden = "";
//         SRHistoryGrid.sortIndex = 1;
//         gridToBeReset = true;
//         dBPersistentFlag = false;
// 		SRHistoryGrid.setColumnsVisibility("false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false");	// changed by for CI5
//         SRHistoryGrid.loadOrder(colsOrder);
//         SRHistoryGrid.loadPagingSorting(colsSorting,1);
//         SRHistoryGrid.loadSize(colsWidth);
//         customizedGridURL = updateGridURL(customizedGridURL, 1, 'asc', null);
//         SRHistoryGrid.clearAndLoad(customizedGridURL);
//     };
    
 	/** 
 	 *  status_container config begin
 	 */
 	SRStatusGrid = new dhtmlXGridObject('div_status_container');
 	SRStatusGrid.setImagePath("<html:imagesPath/>gridImgs/");
 	SRStatusGrid.setHeader("<spring:message code='serviceRequest.listHeader.srstatus'/>");
 	SRStatusGrid.setColAlign("left,left,left");
 	SRStatusGrid.setColTypes("ro,ro,ro");
 	//SRStatusGrid.setColSorting("str,str,str");
 	SRStatusGrid.setInitWidths("150,150,*");
 	SRStatusGrid.enableAutoWidth(true);
 	SRStatusGrid.enableMultiline(true);
 	SRStatusGrid.enableAutoHeight(true);
 	SRStatusGrid.init(); 
 	SRStatusGrid.prftInit();
 	SRStatusGrid.enablePaging(true, 5, 10, "div_statusPagingArea", true);
 	SRStatusGrid.setPagingSkin("bricks");
 	SRStatusGrid.setSkin("light");
 	SRStatusGrid.loadXMLString('${serviceRequestForm.serviceRequestStausXML}'); 
 	//SRStatusGrid.setSortImgState(true, 0, 'asc');
 	
 	/** 
 	  *  Technician Grid Config begin
 	  */
 	if(${serviceRequestForm.serviceRequestTechnicianXML!= null}){
 	SRTechnicianGrid = new dhtmlXGridObject('div_technicianContainer');
 	SRTechnicianGrid.setImagePath("<html:imagesPath/>gridImgs/");
 	SRTechnicianGrid.setHeader("<spring:message code='serviceRequest.listHeader.srstatus'/>");
 	SRTechnicianGrid.setColAlign("left,left,left");
 	SRTechnicianGrid.setColTypes("ro,ro,ro");
 	//SRTechnicianGrid.setColSorting("str,str,str");
 	SRTechnicianGrid.setInitWidths("150,150,*");
 	SRTechnicianGrid.enableAutoWidth(true);
 	SRTechnicianGrid.enableMultiline(true);
 	SRTechnicianGrid.enableAutoHeight(true);
 	SRTechnicianGrid.init(); 
 	SRTechnicianGrid.prftInit();
 	SRTechnicianGrid.enablePaging(true, 5, 10, "div_technicianPagingArea", true);
 	SRTechnicianGrid.setPagingSkin("bricks");
 	SRTechnicianGrid.setSkin("light");
 	SRTechnicianGrid.loadXMLString('${serviceRequestForm.serviceRequestTechnicianXML}'); 
 	//SRTechnicianGrid.setSortImgState(true, 0, 'asc');
 	}
 	/** 
 	  *  InProcess Grid config begin
 	  */
 	if(${serviceRequestForm.inProcessForm.shipmentXML!= null}){
 	SRInProgressGrid = new dhtmlXGridObject('div_inprocessGridContainer');
 	SRInProgressGrid.setImagePath("<html:imagesPath/>gridImgs/");
 	SRInProgressGrid.setHeader(autoAppendPlaceHolder("<spring:message code='serviceRequest.listHeader.shipment'/>", 6));
 	SRInProgressGrid.setColAlign("left,left,left,left,left,left");
 	SRInProgressGrid.setColTypes("ro,ro,ro,ro,ro,ro");
 	SRInProgressGrid.setColSorting("str,str,str,str,str,str");
 	SRInProgressGrid.setInitWidths("150,390,*,*,*,*");
 	SRInProgressGrid.enableAutoWidth(true);
 	SRInProgressGrid.enableMultiline(true);
 	SRInProgressGrid.enableAutoHeight(true);
 	SRInProgressGrid.init(); 
 	SRInProgressGrid.prftInit();
 	SRInProgressGrid.enablePaging(true, 5, 10, "div_inprocessPagingArea", true);
 	SRInProgressGrid.setPagingSkin("bricks");
 	SRInProgressGrid.setSkin("light");
 	SRInProgressGrid.loadXMLString('${serviceRequestForm.inProcessForm.shipmentXML}'); 
 	SRInProgressGrid.setSortImgState(true, 0, 'asc');	
 	 }	
 	/** 
 	 	*  Notifications Grid config begin
 	 	*/
 	 if(${serviceRequestForm.serviceRequestNotificationsXML!= null}){
 	 SRNotificationsGrid = new dhtmlXGridObject('div_notifications_container');
 	 SRNotificationsGrid.setImagePath("<html:imagesPath/>gridImgs/");
 	 SRNotificationsGrid.setHeader("<spring:message code='serviceRequest.listHeader.notifications'/>");
 	/* Added extra column for defect 4515*/
 	 SRNotificationsGrid.setColAlign("left,left,left,left,left");
 	 SRNotificationsGrid.setColTypes("ro,ro,link,ro,ro");
 	 SRNotificationsGrid.setColSorting("str,str,str,str,str");
 	 SRNotificationsGrid.setInitWidths("180,200,*,0,0");
 	/* End of defect 4515 */
 	 SRNotificationsGrid.enableAutoWidth(true);
 	 SRNotificationsGrid.enableAutoHeight(true);
 	 SRNotificationsGrid.enableMultiline(true);
 	 SRNotificationsGrid.init(); 
 	 SRNotificationsGrid.setSkin("light");
 	 SRNotificationsGrid.prftInit();
 	 SRNotificationsGrid.enablePaging(true, 5, 10, "div_notificationsPagingArea", true);
 	 SRNotificationsGrid.setPagingSkin("bricks");
 	 
 	 SRNotificationsGrid.loadXMLString('${serviceRequestForm.serviceRequestNotificationsXML}'); 
 	 SRNotificationsGrid.setSortImgState(true, 0, 'asc');
 	 SRNotificationsGrid.setColumnHidden(3,true);
 	 SRNotificationsGrid.setColumnHidden(4,true);//For defect 6328
 	 }
	/**
		****************progressBars configration start*******************
	*/
	if(${serviceRequestForm.serviceRequest.serviceActivityStatus != null}){
		if(${serviceRequestForm.serviceRequestTechnicianProgress==0}){
			jQuery("#div_technicianProgressBar").progressBar(100 , { width:480,speed:25,increment:1,height:12,showText: false, boxImage: '<html:imagesPath/>progressBar/progressbar.gif', barImage : barImagesCancel} );
		}else{
	  		jQuery("#div_technicianProgressBar").progressBar(${serviceRequestForm.serviceRequestTechnicianProgress}, { width:480,speed:25,increment:1,height:12,showText: false, boxImage: '<html:imagesPath/>progressBar/progressbar.gif', barImage : barImages} );
		}
	}
	if(${serviceRequestForm.inProcessForm.shipmentXML!= null}){
		if(${serviceRequestForm.inProcessForm.shipmentProgress==0}){
			jQuery("#div_inProgressBar").progressBar(100, { width:480,speed:25,increment:1,height:12,showText: false, boxImage: '<html:imagesPath/>progressBar/progressbar.gif', barImage : barImagesCancel} );
		}else{
	  		jQuery("#div_inProgressBar").progressBar(33, { width:480,speed:25,increment:1,height:12,showText: false, boxImage: '<html:imagesPath/>progressBar/progressbar.gif', barImage : barImages} );
		}
	}
	/**
		****************progressBars configration  end*******************
	*/	 
	<%--	Commented for AUI Popup
	function email(){
		 doEmail = false;
		 new Liferay.Popup({
		 	    title: "<spring:message code='serviceRequest.label.detail'/>&nbsp;<spring:message code='serviceRequest.label.email'/>",
		     	position: [450,50],
		     	modal: true,
		     	width: 550, 
		     	height: 'auto',
		     	xy: [100, 100],
		     	onClose: function() {showSelect();},
		     	url:"<portlet:renderURL 
		         	windowState='<%= LiferayWindowState.POP_UP.toString() %>'>" + 
		         	"<portlet:param name='action' value='showSRDrillDownEmailSendingPage' />" + 
		         	"</portlet:renderURL>&requestNumber=${serviceRequestForm.serviceRequest.serviceRequestNumber}"
		     	});
		};
		--%>
	function loadGridDataForEmail(){
 	    callOmnitureAction('Service Request', 'Service Request DrillDown Send Email');
		expandAssociatedAndHistory();
		if(isAssociatedGridAlreadyLoaded == false){
			SRAssociatedGrid.loadXML("${serviceRequestForm.associatedServiceTicketXML}");
		}
		//code commented for CI-7 Defect #8217
// 		if(isHistoryGridAlreadyLoaded == false){
// 			customizedGridURL = populateURLCriterias();
// 			SRHistoryGrid.loadXML(customizedGridURL);
//     	}
//     	if(isAssociatedGridAlreadyLoaded == true && isHistoryGridAlreadyLoaded==true){
//     		email();
//     		hideSelect();
//         }

		initialiseGrid();
		requestListGrid.loadXML("<portlet:resourceURL id='retrieveHistoryList'/>");
		showEmailPopup();
    	doEmail = true;
	};
	function loadGridDataForPrint(){
 	    callOmnitureAction('Service Request', 'Service Request DrillDown Print');
		expandAssociatedAndHistory();
		if(isAssociatedGridAlreadyLoaded == false){
			SRAssociatedGrid.loadXML("${serviceRequestForm.associatedServiceTicketXML}");
		}
		//code commented for CI-7 Defect #8217
// 		if(isHistoryGridAlreadyLoaded == false){
// 			customizedGridURL = populateURLCriterias();
// 			SRHistoryGrid.loadXML(customizedGridURL);
//     	}
//     	if(isAssociatedGridAlreadyLoaded == true && isHistoryGridAlreadyLoaded==true){
//     		print();
//         }
		print();
		doPrint = true;
	};
	function print(){
		doPrint = false;
        var url ="<portlet:renderURL windowState='<%= LiferayWindowState.POP_UP.toString() %>'><portlet:param name='action' value='showServiceRequestDrillDownPrintPage' /></portlet:renderURL>";         
        var iWidth=900;
        var iHeight= 'auto';
        var iTop = (window.screen.availHeight-30-iHeight)/2;        
        var iLeft = (window.screen.availWidth-10-iWidth)/2;           
        window.open(url,
                'ServiceRequestDrilldown',
                'height='+iHeight+
                ',innerHeight='+
                iHeight+',width='+
                iWidth+',innerWidth='+
                iWidth+',top='+iTop+
                ',left='+iLeft+
                ',status=no,toolbar=no,menubar=no,location=no,resizable=yes,scrollbars=yes,titlebar=no');
    };
    function getEmailSubject(){
        var serviceRequestNumber = '${serviceRequestForm.serviceRequest.serviceRequestNumber}';
        var emailSubject = "<spring:message code='serviceRequest.label.detail'/>"+"("+ serviceRequestNumber +")";
        return emailSubject;
    };
        
    function getEmailNotification(){
        return "<spring:message code='serviceRequest.label.detail'/>";
    };
    
    function getJSPPageName(){
        return "serviceRequestDetail";
    };
    function expandCollapesGrid(gridId){
        var isDisplay = document.getElementById(gridId).style.display;
        var showImg = document.getElementById("img_"+gridId);
    	if(isDisplay=='none'){
    		callOmnitureAction('Service Request', 'Service Request Drilldown Collapse Grid');
    		document.getElementById(gridId).style.display = '';
    		$('#img_'+gridId).removeClass('expand-icon');
    		$('#img_'+gridId).addClass('minimize-icon');
    		if(gridId=="tab_associatedTicketsGrid"&&!isAssociatedGridAlreadyLoaded){
        		/* var url = "${serviceRequestForm.associatedServiceTicketXML}";
        		url = url+"&lightBox1=lightBox";
    			SRAssociatedGrid.loadXML(url); */
    			SRAssociatedGrid.loadXML("${serviceRequestForm.associatedServiceTicketXML}");
    		}
        }else{
    		callOmnitureAction('Service Request', 'Service Request Drilldown Expand Grid');
        	document.getElementById(gridId).style.display = 'none';
    		$('#img_'+gridId).addClass('expand-icon');
    		$('#img_'+gridId).removeClass('minimize-icon');
           }
    }
    function onSRNmbrClick(serviceRequestNumber,type){
    	closeCustomizedWindow();
		callOmnitureAction('Service Request', 'Service Request View Detail');
        var iLeft = (window.screen.availWidth-820)/2; 
        
        var url="<portlet:renderURL windowState='<%= LiferayWindowState.EXCLUSIVE.toString() %>'><portlet:param name='action' value='serviceRequestDrillDownLightBox' /></portlet:renderURL>&serviceRequestNumber="+ serviceRequestNumber+"&lightBox=lightBox"+"&g="+(typeof dhtmlXGridObject=="function"?false:true);
       
        showOverlay();
        $('#serviceRequestDetailDiv').load(url,function(){
        	dialog=$('#serviceRequestDetailDiv').dialog({
        		autoOpen: false,
        		title: "<spring:message code='serviceRequest.title.serviceRequest'/>",
				modal: true,
				height: 900,
				width: 850,
				draggable: false,
				resizable: false,
				position: ['center','top'],
				open: function(){
						
		  			},
				close: function(event,ui){
   				  	  hideOverlay();
   				  	  dialog.dialog('destroy');
   				  	showSelect();
					}
        	});
        	dialog.dialog('open');
        });
        
       
    };
    function goBack(){
	  		  callOmnitureAction('Service Request', 'Service Request DrillDown Go back');
        	  window.location.href="<portlet:renderURL></portlet:renderURL>";
          };
    function doSubmitSR(){
		  callOmnitureAction('Service Request', 'Service Request DrillDown Submit Service Request');
       		window.location.href="<portlet:actionURL><portlet:param name='action' value='checkAccountSRPrivilege'/></portlet:actionURL>";
     };
     function popupDialog(){
 		/* if(window.location.href.indexOf('/partner-portal') != -1)
 		{
 			window.location.href="customer-requests?create=1";
 			return false;
 		} */
 		isCatalogPage = "false";
 		//var url=jQuery('.createNewRequestPopUp').attr('href');
 		var url='<portlet:renderURL windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>">'+
      		'<portlet:param name="action" value="createNewRequestPopUp"></portlet:param>'+
			'</portlet:renderURL>';
 		url+="&uniqueTime="+new Date().getTime();
 		showOverlay();
 		/*alert('before ajax call');*/
 		jQuery('#createNewRequestPageData').load(url,function(){
 			/*alert('after ajax call');*/
 			dialog=jQuery('#totalContent').dialog({
 				autoOpen: false,
 				title: jQuery('.createNewRequestPopUp').attr('title'),
 				modal: true,
 				height: 460,
 				width: 700,
 				open: function(){
 					jQuery('#totalContent').show();
 				},
 				position: ['center','top'],
 				close: function(event,ui){
 						/*alert('in close dialog of createnew');*/
 						
 					 hideOverlay();
 					 dialog.dialog('destroy');
 					 
 					 dialog=null;
 					 jQuery('#createNewRequestPageData').empty();
 					 jQuery('#accountInitialize').hide();
 					 if(ajaxAccountSelection=="success"){
 							showOverlay();
 							window.location.href=linkObj_accSelect.href;
 						}
 					 
 					}
 				});
 			//jQuery(document).scrollTop(0);
 			dialog.dialog('open');
 			});
 		
 		return false;
 	}
     var shipmentLength = (${fn:length(serviceRequestForm.serviceRequestShipments)}+1);
	 var returnShipmentLength = (${fn:length(serviceRequestForm.serviceRequestReturnShipments)}+1);
    	 	document.getElementById("tab_associatedTicketsGrid").style.display = 'none';
    	 	document.getElementById("tab_statusGrid").style.display = 'none';
    	 if(${serviceRequestForm.serviceRequestTechnicianXML!= null}){
    	 SRTechnicianGrid.sortRows(0,"str","asc");
    	 document.getElementById("tab_technicianGrid").style.display = 'none';
    	 }
    	 if(${serviceRequestForm.inProcessForm.shipmentXML!= null}){
    	 SRInProgressGrid.sortRows(0,"str","asc");
    	 document.getElementById("tab_inprocessGrid").style.display = 'none';
    	 }
    	 if(${serviceRequestForm.inProgressReturnNoTrackNumForm != null}){
    		 inProgressReturnNoTrackNumGrid.sortRows(0,"str","asc");
        	 document.getElementById("tab_inProgressReturnNoTrackNumGrid").style.display = 'none';
    	 }
    	 if(${serviceRequestForm.deliveredReturnNoTrackNumForm != null}){
    		 deliveredReturnNoTrackNumGrid.sortRows(0,"str","asc");
        	 document.getElementById("tab_deliveredReturnNoTrackNumGrid").style.display = 'none';
    	 }
    	 if(${serviceRequestForm.serviceRequestNotificationsXML!= null}){
    	 SRNotificationsGrid.sortRows(0,"str","asc");
    	 }
    	 if(${serviceRequestForm.serviceRequestShipments!=null}){
    	 for(var i=1;i<shipmentLength;i++){
    		 eval("SRShipmentGrid"+i.toString()).sortRows(0,"str","des");  
    		 document.getElementById("tab_shipmentGrid"+i).style.display = 'none';
         }
    	 }
    	 if(${serviceRequestForm.serviceRequestReturnShipments!=null}){
    	 for(i=1;i<returnShipmentLength;i++){
    		 eval("SRReturnShipmentGrid"+i.toString()).sortRows(0,"str","des");  
    		 document.getElementById("tab_returnShipmentGrid"+i).style.display = 'none';
         }
    	 }
    	 function expandAssociatedAndHistory(){
    	  		document.getElementById("tab_associatedTicketsGrid").style.display = 'block';
    	  		document.getElementById("img_tab_associatedTicketsGrid").setAttribute("src", "<html:imagesPath/>collapsed.jpg");
    	  		//Code commented for CI-7 Defect #8217
    	  		//document.getElementById("tab_historyGrid").style.display = 'block';
    	  		//document.getElementById("img_tab_historyGrid").setAttribute("src", "<html:imagesPath/>collapsed.jpg"); 
    	  	  	};
    	  	 function popupNotificationDecDetail(selectedRowId){
    	    		var date = SRNotificationsGrid.cellByIndex(selectedRowId,0).cell.innerHTML;
    	    	 	var recipient = SRNotificationsGrid.cellByIndex(selectedRowId,1).cell.innerHTML;
    	    		var description = SRNotificationsGrid.cells(selectedRowId,3).cell.innerHTML;
    	    		var comment = SRNotificationsGrid.cells(selectedRowId,4).cell.innerHTML; //Added for defect 4515
    	    		
    	    		new Liferay.Popup({
    	  	 	    title: "<spring:message code='serviceRequest.label.notificationsDetail'/>",
    	  	     	position: [450,50],
    	  	     	modal: true,
    	  	     	width: 550, 
    	  	     	height: 'auto',
    	  	     	xy: [100, 100],
    	  	     	onClose: function() {showSelect();},
    	  	     	url:"<portlet:renderURL 
    	  	         	windowState='<%= LiferayWindowState.POP_UP.toString() %>'>" + 
    	  	         	"<portlet:param name='action' value='showNotificationDescriptionDetail' />" + 
    	  	         	"</portlet:renderURL>&date="+date+"&recipient="+recipient+"&description="+description+"&selectedRowId="+selectedRowId
    	  	     	});
    	    	  	}
    	  	function hideSelect(){   
    			var selects = document.getElementsByTagName('select');
    			for (var i=0; i < selects.length; i++){
    				var select = selects[i];
    				select.style.visibility = "hidden";
    			}      
    		}  

    		function showSelect(){   
    			var selects = document.getElementsByTagName('select');
    			for (var i=0; i < selects.length; i++){
    				var select = selects[i];
    				select.style.visibility = "visible";
    			} 
    		} 
    		 function closeCustomizedWindow(){
    				if(document.getElementById('grid_menu')!=null)
    				{
    			   	  document.getElementById('grid_menu').style.display = 'none';
    				}
    		}
    		 
    		 function backToHistory(){
    		  	var backUrl =  "<portlet:renderURL><portlet:param name='action' value='backToHistory' />	</portlet:renderURL>" ;
    		  	var sourceRequest="${requestedFrom}";
    		  	if(sourceRequest!="" && sourceRequest=="ALL_REQUESTS")
    		  		backUrl = backUrl+'&requestTypeStr=' + sourceRequest;
    		  	else if(sourceRequest=="DM"){
    		  		var currentURL = window.location.href;
    		  		if(currentURL.indexOf('/grid-view') != -1){
    		  			backUrl="grid-view?assetRowId=${assetRowId}&isDeviceBookmarked=${isDeviceBookmarked}&backHistory=true&lclick=${linkClicked}";	
    		  		}
    		  		else{
    		  		backUrl="device-finder?assetRowId=${assetRowId}&isDeviceBookmarked=${isDeviceBookmarked}&backHistory=true&lclick=${linkClicked}";
    		  		}
    		  	}else
    		  		backUrl = backUrl+'&requestTypeStr=' + 'SERVICE_REQUESTS';
    		   	window.location=backUrl;
    		}
</script>
<script type="text/javascript">
function outPutFile(attachmentName,activityId,displayAttachmentName){
	        window.open("${displayAttachmentURLBreakFix}&attachmentName=" + attachmentName + "&activityId=" + activityId+ "&displayAttachmentName=" + displayAttachmentName, "");
       
       
};
/*Method gotoControlPanel added for Defect 3924- Jan Release*/
       function gotoControlPanel(url) {
    		controlPanelPopUpWindow.show();
    		controlPanelPopUpWindow.io.set('uri',"<portlet:renderURL windowState='<%= LiferayWindowState.POP_UP.toString() %>'><portlet:param name='action' value='gotoControlPanel' /></portlet:renderURL>&controlPanelURL=" + url);
    		controlPanelPopUpWindow.io.start();
           <%--
			    new Liferay.Popup({
			        title: "",
			        position: [400,150], 
			        modal: true,
			        width: 400, 
			        height: 150,
			        xy: [100, 100],
			        onClose: function() {showSelect();},
			        url:"<portlet:renderURL 
			             windowState='<%= LiferayWindowState.POP_UP.toString() %>'>" + 
			            "<portlet:param name='action' value='gotoControlPanel' />" + 
			            "</portlet:renderURL>&controlPanelURL=" + url
        			}); --%>
		};
</script>
<script type="text/javascript">
//---- Ominture script 
     portletName = "Service Request";
     addPortlet(portletName);
     pageTitle="Service request Drilldown";
</script>
<!-- Added for AUI popup -->
<%--<script>
var emailPopUpWindow;
AUI().use('aui-base',
'aui-io-plugin-deprecated',
'liferay-util-window',
function(A) {

	emailPopUpWindow=Liferay.Util.Window.getWindow(
	{
	dialog: {
	centered: true,
	constrain2view: true,
	//cssClass: 'yourCSSclassName',
	modal: true,
	resizable: false,
	width: 650,
	height: 350,
	visible: false
	}
	}
	).plug(
	A.Plugin.IO,
	{
	autoLoad: false
	})



});
function showEmailPopup(){
	emailPopUpWindow.show();
	emailPopUpWindow.titleNode.html("<spring:message code='requestInfo.title.emailConfirm'/>");
	
	emailPopUpWindow.io.start();
	
	}

</script> --%>

<script>


function showEmailPopup(){
var url="<portlet:renderURL windowState='<%= LiferayWindowState.EXCLUSIVE.toString() %>'><portlet:param name='action' value='showSRDrillDownEmailSendingPage' /></portlet:renderURL>&requestNumber=${serviceRequestForm.serviceRequest.serviceRequestNumber}";
showOverlay();	
	$('#emaildiv').load(url,function(){
		
		dialog=jQuery('#divToAddress').dialog({
		autoOpen: false,
		//title: titleStr,
		modal: true,
		draggable: false,
		resizable: false,
		position: 'center',
		height: 'auto',
		width: 650,
		open: function(){	
			jQuery('#divToAddress').show();
		},
		close: function(event,ui){
			hideOverlay();
			dialog.dialog('destroy');					 
			dialog=null;
			jQuery('#divToAddress').remove();
			},
		error: function (jqXHR, textStatus, errorThrown) {
			hideOverlay();
			dialog.dialog('destroy');					 
			dialog=null;
			jQuery('#divToAddress').remove();
			}
		});
	dialog.dialog('open');
	});	
}
</script>
<script>
var controlPanelPopUpWindow;
AUI().use('aui-base',
'aui-io-plugin-deprecated',
'liferay-util-window',
function(A) {

	controlPanelPopUpWindow=Liferay.Util.Window.getWindow(
	{
	dialog: {
	centered: true,
	constrain2view: true,
	//cssClass: 'yourCSSclassName',
	modal: true,
	resizable: false,
	width: 400,
	height: 195,
	visible: false
	}
	}
	).plug(
	A.Plugin.IO,
	{
	autoLoad: false
	})



});

</script>