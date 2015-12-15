<%@ include file="/WEB-INF/jsp/include.jsp"%>
<c:if test="${grid eq true}">
<%@ include file="/WEB-INF/jsp/includeGrid.jsp"%>
</c:if>
<script type="text/javascript" src='<html:rootPath/>js/trackingNumber.js'></script>
<script type="text/javascript" src='<html:rootPath/>js/jquery.progressbar.js'></script>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/mps.css" />
<!--[if IE 7]>
	<style type="text/css"><%@ include file="/WEB-INF/css/mps_ie7.css" %></style>
<![endif]-->
<script type="text/javascript">
	barImages = { 0:'<html:imagesPath/>progressBar/progressbg_red.gif',	
		45:	'<html:imagesPath/>progressBar/progressbg_half.gif',
		55: '<html:imagesPath/>progressBar/progressbg_orange.gif',
		70: '<html:imagesPath/>progressBar/progressbg_green.gif'};
	barImagesCancel = {0: '<html:imagesPath/>progressBar/progressbg_black.gif'};
</script>
<style type="text/css">
body{
	align: left;
	text-align:left;
}
div.status-block div.status-bar {
	float: left;
	position: relative;
	top: 0px!important;
	left: 130px;
}
div.gridbox_light table.hdr {
    width: auto !important;
}

.ie7 #tab_lb_statusGrid .xhdr {
	position: static!important;
}
.ie7 #tab_lb_statusGrid .objbox {
	position: static;
}
.ie7 .buttonContainer2 .button {
 behavior: url(/LexmarkOPSPortal/WEB-INF/css/PIE.htc);
 position: static;
}
</style>

<style type="text/css">
.displayGrid tr th{
width:20%!important;
}</style>
<div id="contentDetailsService" style="max-height: 500px; overflow-y: auto">
<table width="800">
<tr>
<td width="100%">
		<div id="div_lb_dillDownHead" class="sr-header">
			<h2><spring:message code="message.servicerequest.detail" />-&nbsp;${serviceRequestForm.serviceRequest.serviceRequestNumber}</h2>
			<div class="utilities"></div><!-- utilities -->
			<div class="header-right"></div><!-- header-right -->
			<div class="clear"></div><!-- clear -->
		</div><!-- sr-header -->
		<div id="tab_lb_problemInfo" class="portlet-wrap">
		<div class="portlet-header">
				
		<table class="displayGrid" style="width:100%;margin-top:1px;">
              <thead>
                <tr><th><spring:message code="message.servicerequest.problemInformation" /></th>
                </tr></thead>
              
            </table>
			
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
                  
               
               
                 <li> <label><spring:message code="requestInfo.label.severity"/> : </label>
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
		
		
		<div id="tab_lb_deviceInfo" class="portlet-wrap">
    	<div class="portlet-header">
    	<table class="displayGrid" style="width:100%;margin-top:1px;">
              <thead>
                <tr><th><spring:message code="serviceRequest.label.deviceInformation" /></th>
                </tr></thead>
              
            </table>
    	</div>
		<div class="portlet-wrap-inner">
			<div class="columns two">
					<div class="first-column">
						<div class="device">
								<img id="divImgs" height="${newImageHeight}" width="${newImageWidth}" src="${serviceRequestForm.serviceRequest.asset.productImageURL}"/>
						</div><!-- device -->
					</div><!-- first-column -->
					<div class="second-column">
						<dl>
<!-- 						Customer Reporting Name fetched for CI BRD 14-02-04 -->
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
   									<a href="javascript:gotoControlPanel('${serviceRequestForm.serviceRequest.asset.controlPanelURL}')">
   									${serviceRequestForm.serviceRequest.asset.ipAddress}
   									</a>
   									</c:when>
   							<%--End: Added for Defect 3924-Jan Release--%>
   									<c:otherwise><spring:message code="serviceRequest.label.n/a"/></c:otherwise>
   							</c:choose>
   							</dd>
   						</c:if>
						</dl>
					</div><!-- second-column -->
					<div class="third-column">
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
				</div>
				<div class="portlet-footer"><div class="portlet-footer-inner"></div><!-- portlet-footer-inner --></div>
			</div>
		
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
                        <div id="div_technicianProgressBar1" class="status-bar"></div><!-- status-bar -->
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
            <div class="overflowAuto">
              <table border="0" width="100%" cellspacing="0" cellpadding="0" class="displayGrid">
	                          <thead><tr><th><spring:message code="requestInfo.label.activityType"/></th><th><spring:message code="requestInfo.label.activityNumber"/></th><th><spring:message code="requestInfo.label.severity"/></th><th><spring:message code="requestInfo.label.activityDate"/></th><th><spring:message code="requestInfo.label.activityStatus"/></th><th><spring:message code="requestInfo.label.activityDescription"/></th><th><spring:message code="requestInfo.label.activitySerialNo"/></th><th><spring:message code="requestInfo.label.activitySubstatus"/></th><th><spring:message code="requestInfo.label.activitydeviceType"/></th></tr></thead>
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
			<div id="tab_lb_status" class="portlet-wrap">
			<div class="portlet-header">
			<table class="displayGrid" style="width:100%;margin-top:1px;">
              <thead>
                <tr><th><spring:message code="serviceRequest.label.status" /></th>
                </tr></thead>
              
            </table>
				
			</div>
			<div class="portlet-wrap-inner">
			<div><img id="img_tab_lb_statusGrid" class="ui_icon_sprite expand-icon" src="<html:imagesPath/>transparent.png" onClick="expandCollapesGrid('tab_lb_statusGrid');" style="cursor:pointer";  /><spring:message code="serviceRequest.label.serviceRequestStatus"/>:&nbsp;${serviceRequestForm.serviceRequest.serviceRequestStatus}</div>
			<div id="tab_lb_statusGrid">
					<div id="div_lb_status_container" style="background-color: white;"></div>
    				<div id="div_lb_statusPagingArea"></div>
			</div>
			
			<div class="status-wrap">
			<c:if test="${serviceRequestForm.serviceRequestTechnicianXML!= null}">
			<div class="status-block">
						<h4><spring:message code="serviceRequest.label.technician" /></h4>
						<div class="status-info">
							<div class="icon">
								<img class="ui_icon_sprite widgets widgets technician-large-icon" src="<html:imagesPath/>transparent.png" alt="technician" />
							</div><!-- icon -->
							<div  id="div_lb_technicianProgressBar" class="status-bar"></div><!-- status-bar -->
							<c:choose>
								<c:when test="${serviceRequestForm.serviceRequestTechnicianProgress==0}">
									<table width="450" style="margin-left:220px;">
										<tr>
											<td width="33%" align="left"></td>
											<td width="33%" align="center"><spring:message code="serviceRequest.label.cancelled" /></td>
											<td width="33%" align="right"></td>
										</tr>
									</table>
								</c:when>
								<c:otherwise>
									<table width="450" style="margin-left:220px;">
										<tr>
											<td width="33%" align="left"><spring:message code="serviceRequest.label.inProcess" /></td>
											<td width="33%" align="center"><spring:message code="serviceRequest.label.assigned" /></td>
											<td width="33%" align="right"><spring:message code="serviceRequest.label.complete" /></td>
										</tr>
									</table>
								</c:otherwise>
							</c:choose>
							
						</div><!-- status-info -->
						<div><img id="img_tab_lb_technicianGrid" class="ui_icon_sprite expand-icon" src="<html:imagesPath/>transparent.png" onClick="expandCollapesGrid('tab_lb_technicianGrid');" style="cursor:pointer"; /><spring:message code="serviceRequest.label.showDetail"/></div>
						<div id="tab_lb_technicianGrid">
						<div id="div_lb_technicianContainer" style="background-color: white;"></div>
    					<div id="div_lb_technicianPagingArea"></div>
    					</div><!-- mygrid_container -->
			 </div><!-- status-block -->
			</c:if>

			<!-- In process orders START -->
			<c:if test="${serviceRequestForm.inProcessForm.shipmentXML!= null}">
			<div class="status-block">
						<h4><spring:message code="serviceRequest.label.inProcessOrders" /></h4>
						<div class="status-info">
							<div class="icon" >
								<img class="ui_icon_sprite widgets inProcess-icon" src="<html:imagesPath/>transparent.png" alt="inProcess" />
							</div><!-- icon -->
							<div id="div_lb_inProgressBar" class="status-bar"></div><!-- status-bar -->
							<c:choose>
								<c:when test="${serviceRequestForm.inProcessForm.shipmentProgress==0}">
									<table width="450" style="margin-left:220px;">
										<tr height="30">
											<td width="33%" align="left"></td>
											<td width="33%" align="center"><spring:message code="serviceRequest.label.cancelled"/></td>
											<td width="33%" align="right"></td>
										</tr>
									</table>
								</c:when>
								<c:otherwise>
									<table width="450" style="margin-left:220px;">
										<tr height="30">
											<td width="33%" align="left"><spring:message code="serviceRequest.label.inProcess" /></td>
											<td width="33%" align="center"><spring:message code="serviceRequest.label.shipped" /></td>
											<td width="33%" align="right"><spring:message code="serviceRequest.label.delivered" /></td>
										</tr>
									</table>
								</c:otherwise>
							</c:choose>
							
						</div><!-- status-info -->
						<div><img id="img_tab_lb_inprocessGrid" class="ui_icon_sprite expand-icon" src="<html:imagesPath/>transparent.png" onClick="expandCollapesGrid('tab_lb_inprocessGrid');" style="cursor:pointer"; /><spring:message code="serviceRequest.label.showDetail"/></div>
						<div id="tab_lb_inprocessGrid" >
						<div id="div_lb_inprocessGridContainer"></div>
    					<div id="div_lb_inprocessPagingArea"></div>
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
						<div class="status-info">
							<div class="icon">
								<img class="ui_icon_sprite widgets widgets-truck-rt-icon" src="<html:imagesPath/>transparent.png" alt="shipment"/>
							</div><!-- icon -->
							<div id="div_lb_shipmentProgressBar${status.count}" class="status-bar"></div><!-- status-bar -->
							<c:choose>
								<c:when test="${shipmentForm.shipmentProgress==0}">
									<table width="450" style="margin-left:220px;">
										<tr height="30">
											<td width="33%" align="left"></td>
											<td width="33%" align="center"><spring:message code="serviceRequest.label.cancelled"/></td>
											<td width="33%" align="right"></td>
										</tr>
									</table>
								</c:when>
								<c:otherwise>
									<table width="450" style="margin-left:220px;">
										<tr height="30">
											<td width="33%" align="left"><spring:message code="serviceRequest.label.inProcess" /></td>
											<td width="33%" align="center"><spring:message code="serviceRequest.label.shipped" /></td>
											<td width="33%" align="right"><spring:message code="serviceRequest.label.delivered" /></td>
										</tr>
									</table>
								</c:otherwise>
							</c:choose>
							
						</div><!-- status-info -->
						<div><img id="img_tab_lb_shipmentGrid${status.count}"  class="ui_icon_sprite expand-icon" src="<html:imagesPath/>transparent.png" onClick="expandCollapesGrid('tab_lb_shipmentGrid${status.count}');" style="cursor:pointer"; /><spring:message code="serviceRequest.label.showDetail"/></div>
						<div id="tab_lb_shipmentGrid${status.count}" >
						<div id="div_lb_shipmentGrid_container${status.count}"></div>
    						<div id="div_lb_shipmentPagingArea${status.count}"></div>
    					</div><!-- mygrid_container -->
			 </div><!-- status-block -->	
			<script type="text/javascript">
			if(${shipmentForm.shipmentProgress==0}){
				jQuery('#div_lb_shipmentProgressBar${status.count}').progressBar(100, { width:480,speed:25,increment:1,height:12,showText: false, boxImage: '<html:imagesPath/>progressBar/progressbar.gif', barImage:barImagesCancel} );
			}else{
				jQuery('#div_lb_shipmentProgressBar${status.count}').progressBar(${shipmentForm.shipmentProgress}, { width:480,speed:25,increment:1,height:12,showText: false, boxImage: '<html:imagesPath/>progressBar/progressbar.gif', barImage:barImages} );
			}
			SRShipmentGrid${status.count} = new dhtmlXGridObject('div_lb_shipmentGrid_container${status.count}');
	 		SRShipmentGrid${status.count}.setImagePath("<html:imagesPath/>gridImgs/");
	 		SRShipmentGrid${status.count}.setHeader("<spring:message code='serviceRequest.listHeader.shipment'/>");
	 		SRShipmentGrid${status.count}.setColAlign("left,left,left");
	 		SRShipmentGrid${status.count}.setColTypes("ro,ro,ro");
	 		SRShipmentGrid${status.count}.setColSorting("str,str,str");
	 		SRShipmentGrid${status.count}.setInitWidths("150,390,*");
	 		SRShipmentGrid${status.count}.enableAutoWidth(true);
	 		SRShipmentGrid${status.count}.enableMultiline(true);
	 		SRShipmentGrid${status.count}.enableAutoHeight(true);
	 		SRShipmentGrid${status.count}.init(); 
	 		SRShipmentGrid${status.count}.prftInit();
	 		SRShipmentGrid${status.count}.enablePaging(true, 5, 10, "div_lb_shipmentPagingArea${status.count}", true);
	 		SRShipmentGrid${status.count}.setPagingSkin("bricks");
	 		SRShipmentGrid${status.count}.setSkin("light");
	 		SRShipmentGrid${status.count}.loadXMLString('${shipmentForm.shipmentXML}');
	 		SRShipmentGrid${status.count}.sortRows(0,"str","asc");   
	 		SRShipmentGrid${status.count}.setSortImgState(true, 0, 'asc');	
			</script>
			</c:forEach>
			<!-- Shipped orders END -->
			
			<!-- In progress return without tracking number START -->
			<c:if test="${not empty serviceRequestForm.inProgressReturnNoTrackNumForm}">
			<div class="status-block">
				<h4><spring:message code="serviceRequest.label.inProcessReturns" /></h4>
				<div class="status-info" style="display:block;">
					<div class="icon" id="truckRightImage">
						<img class="ui_icon_sprite widgets inProcess-icon" src="${serviceRequestForm.serviceHost}<html:imagesPath/>transparent.png" alt="shipment"/>
					</div><!-- icon -->
					<div id="div_lb_inProgressReturnNoTrackNumProgressBar" class="status-bar"></div><!-- status-bar -->
					<c:choose>
						<c:when test="${serviceRequestForm.inProgressReturnNoTrackNumForm.shipmentProgress==1}">
							<table width="450" style="margin-left:220px;">
								<tr height="30">
									<td width="33%" align="left"></td>
									<td width="33%" align="center"><spring:message code="serviceRequest.label.cancelled"/></td>
									<td width="33%" align="right"></td>
								</tr>
							</table>
						</c:when>
						<c:otherwise>
							<table width="450" style="margin-left:220px;">
								<tr height="30">
									<td width="33%" align="left"><spring:message code="serviceRequest.label.inProcess" /></td>
									<td width="33%" align="center"></td>
									<td width="33%" align="right"><spring:message code="serviceRequest.label.delivered" /></td>
								</tr>
							</table>
						</c:otherwise>
					</c:choose>
					
				</div><!-- status-info -->
				<div style="display:block;">
				<table>
					<tr>
						<td>
						<img id="img_tab_lb_inProgressReturnNoTrackNumGrid" class="ui_icon_sprite expand-icon" src="<html:imagesPath/>transparent.png" onClick="expandCollapesGrid('tab_lb_inProgressReturnNoTrackNumGrid');" style="cursor:pointer;" />
						</td>
						<td>
						<spring:message code="serviceRequest.label.showDetail"/>
						</td>
					</tr>
				</table>
				</div>
				<div id="div_lb_inProgressReturnNoTrackNumPrintTable">
					<div id="tab_lb_inProgressReturnNoTrackNumGrid"  style="display:block;">
						<div id="div_lb_inProgressReturnNoTrackNumGrid_container"></div>
  						<div id="div_lb_inProgressReturnNoTrackNumInfoArea"></div>
  					</div>
 				</div><!-- mygrid_container -->
			</div><!-- status-block -->	
			<script type="text/javascript">
	 		/** 
	 	 	  *  Return Shipment Grid config begin
	 	 	  */
	 	 	if(${serviceRequestForm.inProgressReturnNoTrackNumForm.shipmentProgress==1}){
	 	 		jQuery('#div_lb_inProgressReturnNoTrackNumProgressBar').progressBar(100, { width:480,speed:25,increment:1,height:12,showText: false, boxImage: '<html:imagesPath/>progressBar/progressbar.gif', barImage:barImagesCancel} );
	 	 	}else{
	 	 		jQuery('#div_lb_inProgressReturnNoTrackNumProgressBar').progressBar(${serviceRequestForm.inProgressReturnNoTrackNumForm.shipmentProgress}, { width:480,speed:25,increment:1,height:12,showText: false, boxImage: '<html:imagesPath/>progressBar/progressbar.gif', barImage:barImages} );
	 	 	}
	 	 	inProgressReturnNoTrackNumGrid = new dhtmlXGridObject('div_lb_inProgressReturnNoTrackNumGrid_container');
	 	 	inProgressReturnNoTrackNumGrid.setImagePath("<html:imagesPath/>gridImgs/");
	 	 	inProgressReturnNoTrackNumGrid.setHeader("<spring:message code='serviceRequest.listHeader.shipment'/>");
	 	 	inProgressReturnNoTrackNumGrid.setColAlign("left,left,left");
	 	 	inProgressReturnNoTrackNumGrid.setColTypes("ro,ro,ro");
	 	 	inProgressReturnNoTrackNumGrid.setColSorting("str,str,str");
	 	 	inProgressReturnNoTrackNumGrid.setInitWidths("150,390,*");
	 	 	inProgressReturnNoTrackNumGrid.enableAutoWidth(true);
	 	 	inProgressReturnNoTrackNumGrid.enableMultiline(true);
	 	 	inProgressReturnNoTrackNumGrid.enableAutoHeight(true);
	 	 	inProgressReturnNoTrackNumGrid.init(); 
	 	 	inProgressReturnNoTrackNumGrid.enablePaging(true, 5, 10, "div_lb_inProgressReturnNoTrackNumInfoArea", true);
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
						<div class="status-info">
							<div class="icon">
								<img class="ui_icon_sprite widgets widgets-truck-lf-icon" src="<html:imagesPath/>transparent.png" alt="shipment" />
							</div><!-- icon -->
							<div id="div_lb_returnProgressBar${status.count}" class="status-bar"></div><!-- status-bar -->
							<c:choose>
								<c:when test="${returnShipmentForm.shipmentProgress==1}">
									<table width="450" style="margin-left:220px;">
										<tr height="30">
											<td width="33%" align="left"></td>
											<td width="33%" align="center"><spring:message code="serviceRequest.label.cancelled"/></td>
											<td width="33%" align="right"></td>
										</tr>
									</table>
								</c:when>
								<c:otherwise>
									<table width="450" style="margin-left:220px;">
										<tr height="30">
											<td width="33%" align="left"><spring:message code="serviceRequest.label.inProcess" /></td>
											<td width="33%" align="center"></td>
											<td width="33%" align="right"><spring:message code="serviceRequest.label.delivered" /></td>
										</tr>
									</table>
								</c:otherwise>
							</c:choose>
							
						</div><!-- status-info -->
						<div><img id="img_tab_lb_returnShipmentGrid${status.count}" class="ui_icon_sprite expand-icon" src="<html:imagesPath/>transparent.png" onClick="expandCollapesGrid('tab_lb_returnShipmentGrid${status.count}');" style="cursor:pointer"; /><spring:message code="serviceRequest.label.showDetail"/></div>
							<div id="tab_lb_returnShipmentGrid${status.count}">
							<div id="div_lb_returnShipmentGrid_container${status.count}"></div>
    						<div id="div_lb_returnShipmentInfoArea${status.count}"></div>
    				 	</div><!-- mygrid_container -->
			 </div><!-- status-block -->	
			<script type="text/javascript">
	 		/** 
	 	 	  *  Return Shipment Grid config begin
	 	 	  */
	 	 	if(${returnShipmentForm.shipmentProgress==1}){
	 	 		jQuery('#div_lb_returnProgressBar${status.count}').progressBar(100, { width:480,speed:25,increment:1,height:12,showText: false, boxImage: '<html:imagesPath/>progressBar/progressbar.gif', barImage:barImagesCancel} );
	 	 	}else{
	 	 		jQuery('#div_lb_returnProgressBar${status.count}').progressBar(${returnShipmentForm.shipmentProgress}, { width:480,speed:25,increment:1,height:12,showText: false, boxImage: '<html:imagesPath/>progressBar/progressbar.gif', barImage:barImages} );
	 	 	}
	 	 	
	 	 	SRReturnShipmentGrid${status.count} = new dhtmlXGridObject('div_lb_returnShipmentGrid_container${status.count}');
	 	 	SRReturnShipmentGrid${status.count}.setImagePath("<html:imagesPath/>gridImgs/");
	 	 	SRReturnShipmentGrid${status.count}.setHeader("<spring:message code='serviceRequest.listHeader.shipment'/>");
	 	 	SRReturnShipmentGrid${status.count}.setColAlign("left,left,left");
	 	 	SRReturnShipmentGrid${status.count}.setColTypes("ro,ro,ro");
	 	 	SRReturnShipmentGrid${status.count}.setColSorting("str,str,str");
	 	 	SRReturnShipmentGrid${status.count}.setInitWidths("150,390,*");
	 	 	SRReturnShipmentGrid${status.count}.enableAutoWidth(true);
	 	 	SRReturnShipmentGrid${status.count}.enableMultiline(true);
	 	 	SRReturnShipmentGrid${status.count}.enableAutoHeight(true);
	 	 	SRReturnShipmentGrid${status.count}.init(); 
	 	 	SRReturnShipmentGrid${status.count}.prftInit();
	 	 	SRReturnShipmentGrid${status.count}.enablePaging(true, 5, 10, "div_lb_returnShipmentInfoArea${status.count}", true);
	 	 	SRReturnShipmentGrid${status.count}.setPagingSkin("bricks");
	 	 	SRReturnShipmentGrid${status.count}.setSkin("light");
	 	 	SRReturnShipmentGrid${status.count}.loadXMLString('${returnShipmentForm.shipmentXML}'); 
	 	 	SRReturnShipmentGrid${status.count}.sortRows(0,"str","asc");  
	 	 	SRReturnShipmentGrid${status.count}.setSortImgState(true, 0, 'asc');	
			</script>
			</c:forEach>
			<!-- Returns with tracking number END -->
			
			<!-- Delivered return without tracking number START -->
			<c:if test="${not empty serviceRequestForm.deliveredReturnNoTrackNumForm}">
			<div class="status-block">
				<h4><spring:message code="serviceRequest.label.returnsTrackNumNotAvailable" /></h4>
				<div class="status-info" style="display:block;">
					<div class="icon" id="truckRightImage">
						<img class="ui_icon_sprite widgets widgets-truck-lf-icon" src="${serviceRequestForm.serviceHost}<html:imagesPath/>transparent.png" alt="shipment" />
					</div><!-- icon -->
					<div id="div_lb_deliveredReturnNoTrackNumProgressBar" class="status-bar"></div><!-- status-bar -->
					<c:choose>
						<c:when test="${serviceRequestForm.deliveredReturnNoTrackNumForm.shipmentProgress==1}">
							<table width="450" style="margin-left:220px;">
								<tr height="30">
									<td width="33%" align="left"></td>
									<td width="33%" align="center"><spring:message code="serviceRequest.label.cancelled"/></td>
									<td width="33%" align="right"></td>
								</tr>
							</table>
						</c:when>
						<c:otherwise>
							<table width="450" style="margin-left:220px;">
								<tr height="30">
									<td width="33%" align="left"><spring:message code="serviceRequest.label.inProcess" /></td>
									<td width="33%" align="center"></td>
									<td width="33%" align="right"><spring:message code="serviceRequest.label.delivered" /></td>
								</tr>
							</table>
						</c:otherwise>
					</c:choose>
					
				</div><!-- status-info -->
				<div style="display:block;">
				<table>
					<tr>
						<td>
						<img id="img_tab_lb_deliveredReturnNoTrackNumGrid" class="ui_icon_sprite expand-icon" src="<html:imagesPath/>transparent.png" onClick="expandCollapesGrid('tab_lb_deliveredReturnNoTrackNumGrid');" style="cursor:pointer;" />
						</td>
						<td>
						<spring:message code="serviceRequest.label.showDetail"/>
						</td>
					</tr>
				</table>
				</div>
				<div id="div_lb_deliveredReturnNoTrackNumPrintTable">
					<div id="tab_lb_deliveredReturnNoTrackNumGrid"  style="display:block;">
						<div id="div_lb_deliveredReturnNoTrackNumGrid_container"></div>
  						<div id="div_lb_deliveredReturnNoTrackNumInfoArea"></div>
  					</div>
 				</div><!-- mygrid_container -->
			</div><!-- status-block -->	
			<script type="text/javascript">
	 		/** 
	 	 	  *  Return Shipment Grid config begin
	 	 	  */
	 	 	if(${serviceRequestForm.deliveredReturnNoTrackNumForm.shipmentProgress==1}){
	 	 		jQuery('#div_lb_deliveredReturnNoTrackNumProgressBar').progressBar(100, { width:480,speed:25,increment:1,height:12,showText: false, boxImage: '<html:imagesPath/>progressBar/progressbar.gif', barImage:barImagesCancel} );
	 	 	}else{
	 	 		jQuery('#div_lb_deliveredReturnNoTrackNumProgressBar').progressBar(${serviceRequestForm.deliveredReturnNoTrackNumForm.shipmentProgress}, { width:480,speed:25,increment:1,height:12,showText: false, boxImage: '<html:imagesPath/>progressBar/progressbar.gif', barImage:barImages} );
	 	 	}
	 	 	deliveredReturnNoTrackNumGrid = new dhtmlXGridObject('div_lb_deliveredReturnNoTrackNumGrid_container');
	 	 	deliveredReturnNoTrackNumGrid.setImagePath("<html:imagesPath/>gridImgs/");
	 	 	deliveredReturnNoTrackNumGrid.setHeader("<spring:message code='serviceRequest.listHeader.shipment'/>");
	 	 	deliveredReturnNoTrackNumGrid.setColAlign("left,left,left");
	 	 	deliveredReturnNoTrackNumGrid.setColTypes("ro,ro,ro");
	 	 	deliveredReturnNoTrackNumGrid.setColSorting("str,str,str");
	 	 	deliveredReturnNoTrackNumGrid.setInitWidths("150,390,*");
	 	 	deliveredReturnNoTrackNumGrid.enableAutoWidth(true);
	 	 	deliveredReturnNoTrackNumGrid.enableMultiline(true);
	 	 	deliveredReturnNoTrackNumGrid.enableAutoHeight(true);
	 	 	deliveredReturnNoTrackNumGrid.init(); 
	 	 	deliveredReturnNoTrackNumGrid.enablePaging(true, 5, 10, "div_lb_deliveredReturnNoTrackNumInfoArea", true);
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

	<div id="tab_lb_contactInfo" class="portlet-wrap">
			<div class="portlet-header">
			<table class="displayGrid" style="width:100%;margin-top:1px;">
              <thead>
                <tr><th><spring:message code="serviceRequest.label.contactInfo" /></th>
                </tr></thead>
              
            </table>
				
			</div><!-- portlet-header -->
			<div class="portlet-wrap-inner">
				<div class="columns two">
					<div class="first-column">
						<dl>
							<dt><spring:message code="serviceRequest.label.requestorInfo" /></dt>
							<dd><label><spring:message code="serviceRequest.label.name" />:</label> ${serviceRequestForm.serviceRequest.requestor.firstName}&nbsp;${serviceRequestForm.serviceRequest.requestor.lastName}</dd>
							<dd><label><spring:message code="serviceRequest.label.telephone" />:</label> ${serviceRequestForm.serviceRequest.primaryContact.workPhone}</dd>
							<dd><label><spring:message code="serviceRequest.label.email" />:</label> ${serviceRequestForm.serviceRequest.requestor.emailAddress}</dd>
						</dl>
					</div><!-- first-column -->
					<div class="second-column">
						<dl>
							<dt><spring:message code="serviceRequest.label.primaryContactInformation" /></dl>
							<dd><label><spring:message code="serviceRequest.label.name" />:</label> ${serviceRequestForm.serviceRequest.primaryContact.firstName}&nbsp;${serviceRequestForm.serviceRequest.primaryContact.lastName}</dd>
							<dd><label><spring:message code="serviceRequest.label.telephone" />:</label> ${serviceRequestForm.serviceRequest.primaryContact.workPhone}</dd>
							<dd><label><spring:message code="serviceRequest.label.email" />:</label>${serviceRequestForm.serviceRequest.primaryContact.emailAddress}</dd>
						</dl>
					</div><!-- second-column -->
					<c:if test="${serviceRequestForm.serviceRequest.secondaryContact!= null}">
					<c:if test="${serviceRequestForm.serviceRequest.secondaryContact.firstName != ''||
								  serviceRequestForm.serviceRequest.secondaryContact.lastName != ''||
								  serviceRequestForm.serviceRequest.secondaryContact.workPhone!=''||
								  serviceRequestForm.serviceRequest.secondaryContact.emailAddress!=''}">
					<div class="third-column">
						<dl>
							<dt><spring:message code="serviceRequest.label.secondaryContactinformation" /></dt>
							<dd><label><spring:message code="serviceRequest.label.name" />:</label>${serviceRequestForm.serviceRequest.secondaryContact.firstName}&nbsp;${serviceRequestForm.serviceRequest.secondaryContact.lastName}</dd>
							<dd><label><spring:message code="serviceRequest.label.telephone" />:</label>${serviceRequestForm.serviceRequest.secondaryContact.workPhone}</dd>
					        <dd><label><spring:message code="serviceRequest.label.email" />:</label>${serviceRequestForm.serviceRequest.secondaryContact.emailAddress}</dd>
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
		
		<c:if test="${serviceRequestForm.serviceRequestNotificationsXML!= null}">
    	<div id="tab_lb_notifications"  class="portlet-wrap">
    	<div class="portlet-wrap">
			<div class="portlet-header">
			<table class="displayGrid" style="width:100%;margin-top:1px;">
              <thead>
                <tr><th><spring:message code="serviceRequest.label.notifications" /></th>
                </tr></thead>
              
            </table>
		
			</div><!-- portlet-header -->
			<div class="portlet-wrap-inner">
				<div id="notificationsGrid" >
						<div id="div_lb_notifications_container"></div>
    					<div id="div_lb_notificationsPagingArea"></div>
    			</div>
			</div><!-- portlet-wrap-inner -->	
			<div class="portlet-footer">
				<div class="portlet-footer-inner">
				</div><!-- portlet-footer-inner -->
			</div><!-- portlet-footer -->
		</div><!-- portlet-wrap -->
	</div>
	</c:if>

</td>
</tr>
<tr><td align="right"> </td></tr>
</table>
<%--Change done for CI BRD13-10-02--%>
<div class="buttonContainer buttonContainer2 "><button class="button" onClick="callOmnitureAction('Service Request', 'Service request Drilldown lightbox Close');dialog.dialog('close');"><spring:message code="button.close" /></button></div>
</div>

<script type="text/javascript">
 	/** 
 	 *  status_container config begin
 	 */
 	
 		SRStatusGrid = new dhtmlXGridObject('div_lb_status_container');
 		SRStatusGrid.setImagePath("<html:imagesPath/>gridImgs/");
 		SRStatusGrid.setHeader("<spring:message code='serviceRequest.listHeader.srstatus'/>");
 		SRStatusGrid.setColAlign("left,left,left");
 		SRStatusGrid.setColTypes("ro,ro,ro");
 		SRStatusGrid.setColSorting("str,str,str");
 		SRStatusGrid.setInitWidths("150,150,*");
 		SRStatusGrid.enableAutoWidth(true);
 		SRStatusGrid.enableMultiline(true);
 		SRStatusGrid.enableAutoHeight(true);
 		SRStatusGrid.init(); 
 		SRStatusGrid.prftInit();
 		SRStatusGrid.enablePaging(true, 5, 10, "div_lb_statusPagingArea", true);
 		SRStatusGrid.setPagingSkin("bricks");
 		SRStatusGrid.setSkin("light");
 		if(${serviceRequestForm.serviceRequestStausXML!= null}){
 			SRStatusGrid.loadXMLString('${serviceRequestForm.serviceRequestStausXML}'); 
 		}
 		SRStatusGrid.setSortImgState(true, 0, 'asc');
 	
 	/** 
 	  *  Technician Grid Config begin
 	  */
 	 if(${serviceRequestForm.serviceRequestTechnicianXML!= null}){
 	 	SRTechnicianGrid = new dhtmlXGridObject('div_lb_technicianContainer');
 	 	SRTechnicianGrid.setImagePath("<html:imagesPath/>gridImgs/");
 	 	SRTechnicianGrid.setHeader("<spring:message code='serviceRequest.listHeader.srstatus'/>");
 	 	SRTechnicianGrid.setColAlign("left,left,left");
 	 	SRTechnicianGrid.setColTypes("ro,ro,ro");
 	 	SRTechnicianGrid.setColSorting("str,str,str");
 	 	SRTechnicianGrid.setInitWidths("150,150,*");
 	 	SRTechnicianGrid.enableAutoWidth(true);
 	 	SRTechnicianGrid.enableMultiline(true);
 	 	SRTechnicianGrid.enableAutoHeight(true);
 	 	SRTechnicianGrid.init(); 
 	 	SRTechnicianGrid.prftInit();
 	 	SRTechnicianGrid.enablePaging(true, 5, 10, "div_lb_technicianPagingArea", true);
 	 	SRTechnicianGrid.setPagingSkin("bricks");
 	 	SRTechnicianGrid.setSkin("light");
 	 	SRTechnicianGrid.loadXMLString('${serviceRequestForm.serviceRequestTechnicianXML}'); 
 	 	SRTechnicianGrid.setSortImgState(true, 0, 'asc');
 	 }
 	/** 
 	  *  InProcess Grid config begin
 	  */
 		if(${serviceRequestForm.inProcessForm.shipmentXML!= null}){
 		SRInProgressGrid = new dhtmlXGridObject('div_lb_inprocessGridContainer');
 		SRInProgressGrid.setImagePath("<html:imagesPath/>gridImgs/");
 		SRInProgressGrid.setHeader("<spring:message code='serviceRequest.listHeader.shipment'/>");
 		SRInProgressGrid.setColAlign("left,left,left");
 		SRInProgressGrid.setColTypes("ro,ro,ro");
 		SRInProgressGrid.setColSorting("str,str,str");
 		SRInProgressGrid.setInitWidths("150,390,*");
 		SRInProgressGrid.enableAutoWidth(true);
 		SRInProgressGrid.enableMultiline(true);
 		SRInProgressGrid.enableAutoHeight(true);
 		SRInProgressGrid.init(); 
 		SRInProgressGrid.prftInit();
 		SRInProgressGrid.enablePaging(true, 5, 10, "div_lb_inprocessPagingArea", true);
 		SRInProgressGrid.setPagingSkin("bricks");
 		SRInProgressGrid.setSkin("light");
 		SRInProgressGrid.loadXMLString('${serviceRequestForm.inProcessForm.shipmentXML}'); 
 		SRInProgressGrid.setSortImgState(true, 0, 'asc');	
 		}
 	/** 
 	 	*  Notifications Grid config begin
 	 	*/
 	 	if(${serviceRequestForm.serviceRequestNotificationsXML!= null}){
 	 	SRNotificationsGrid = new dhtmlXGridObject('div_lb_notifications_container');
 	 	SRNotificationsGrid.setImagePath("<html:imagesPath/>gridImgs/");
 	 	SRNotificationsGrid.setHeader("<spring:message code='serviceRequest.listHeader.notifications'/>");
 	 	/* Added extra column for defect 4515*/
 	 	SRNotificationsGrid.setColAlign("left,left,left,left,left");
 	 	SRNotificationsGrid.setColTypes("ro,ro,link,ro,ro");
 	 	SRNotificationsGrid.setColSorting("str,str,str,str,str");
 	 	SRNotificationsGrid.setInitWidths("180,200,*,0,0");
 	 	/* End of defect 4515 */
 	 	SRNotificationsGrid.enableAutoWidth(true);
 	 	SRNotificationsGrid.enableMultiline(true);
 	 	SRNotificationsGrid.enableAutoHeight(true);
 	 	SRNotificationsGrid.init(); 
 	 	SRNotificationsGrid.prftInit();
 	 	SRNotificationsGrid.enablePaging(true, 5, 10, "div_lb_notificationsPagingArea", true);
 	 	SRNotificationsGrid.setPagingSkin("bricks");
 	 	SRNotificationsGrid.setSkin("light");
 	 	SRNotificationsGrid.loadXMLString('${serviceRequestForm.serviceRequestNotificationsXML}'); 
 	 	SRNotificationsGrid.setSortImgState(true, 0, 'asc');
 	 	SRNotificationsGrid.setColumnHidden(3,true);
 	 	SRNotificationsGrid.setColumnHidden(4,true);//For defect 6328
 	 	}
	/**
		****************progressBars configration start*******************
	*/
	if(${serviceRequestForm.serviceRequestTechnicianXML!= null}){
		if(${serviceRequestForm.serviceRequestTechnicianProgress==0}){
			jQuery("#div_lb_technicianProgressBar").progressBar(100, { width:480,speed:25,increment:1,height:12,showText: false, boxImage: '<html:imagesPath/>progressBar/progressbar.gif', barImage:barImagesCancel} );
		}else{
			jQuery("#div_lb_technicianProgressBar").progressBar(${serviceRequestForm.serviceRequestTechnicianProgress}, { width:480,speed:25,increment:1,height:12,showText: false, boxImage: '<html:imagesPath/>progressBar/progressbar.gif', barImage:barImages} );
		}
	}
	if(${serviceRequestForm.inProcessForm.shipmentXML!= null}){
		if(${serviceRequestForm.inProcessForm.shipmentProgress==0}){
			jQuery("#div_lb_inProgressBar").progressBar(100, { width:480,speed:25,increment:1,height:12,showText: false, boxImage: '<html:imagesPath/>progressBar/progressbar.gif', barImage:barImagesCancel} );
		}else{
			jQuery("#div_lb_inProgressBar").progressBar(33, { width:480,speed:25,increment:1,height:12,showText: false, boxImage: '<html:imagesPath/>progressBar/progressbar.gif', barImage:barImages} );
		}
	}
	
	/**
		****************progressBars configration  end*******************
	*/	 
	var sraFirstLode = true;
    function expandCollapesGrid(gridId){
        var isDisplay = document.getElementById(gridId).style.display;
        var showImg = document.getElementById("img_"+gridId);
    	if(isDisplay=='none'){
    		callOmnitureAction('Service Request', 'Service request Drilldown lightbox Expand Grid');
    		document.getElementById(gridId).style.display = '';
    		$('#img_'+gridId).removeClass('expand-icon');
    		$('#img_'+gridId).addClass('minimize-icon'); 
    		if(gridId=="tab_lb_associatedTicketsGrid"&&sraFirstLode){
    			sraFirstLode = false;
     			SRAssociatedGrid.loadXML("${serviceRequestForm.associatedServiceTicketXML}");
     		}
        }else{
    		callOmnitureAction('Service Request', 'Service request Drilldown lightbox Collapse Grid');
        	document.getElementById(gridId).style.display = 'none';
    		$('#img_'+gridId).addClass('expand-icon');
    		$('#img_'+gridId).removeClass('minimize-icon');
        }
    	
    }

     jQuery(document).ready(function() {
    	 	SRStatusGrid.sortRows(0,"str","asc");
    	 	document.getElementById("tab_lb_statusGrid").style.display = 'none';
    	 if(${serviceRequestForm.serviceRequestTechnicianXML!= null}){
    	 	SRTechnicianGrid.sortRows(0,"str","asc");
    	 	document.getElementById("tab_lb_technicianGrid").style.display = 'none';
    	 }
    	 if(${serviceRequestForm.inProcessForm.shipmentXML!= null}){
    	 	SRInProgressGrid.sortRows(0,"str","asc");
    	 	document.getElementById("tab_lb_inprocessGrid").style.display = 'none';
    	 }
    	 if(${serviceRequestForm.inProgressReturnNoTrackNumForm != null}){
    		 inProgressReturnNoTrackNumGrid.sortRows(0,"str","asc");
        	 document.getElementById("tab_lb_inProgressReturnNoTrackNumGrid").style.display = 'none';
    	 }
    	 if(${serviceRequestForm.deliveredReturnNoTrackNumForm != null}){
    		 deliveredReturnNoTrackNumGrid.sortRows(0,"str","asc");
        	 document.getElementById("tab_lb_deliveredReturnNoTrackNumGrid").style.display = 'none';
    	 }
    	 if(${serviceRequestForm.serviceRequestShipments!=null}){
    	 for(var i=1;i<(${fn:length(serviceRequestForm.serviceRequestShipments)}+1);i++){
    		 eval("SRShipmentGrid"+i.toString()).sortRows(0,"str","des");  
    		 document.getElementById("tab_lb_shipmentGrid"+i).style.display = 'none';
         }
    	 }
    	 if(${serviceRequestForm.serviceRequestReturnShipments!=null}){
    	 for(i=1;i<(${fn:length(serviceRequestForm.serviceRequestReturnShipments)}+1);i++){
    		 eval("SRReturnShipmentGrid"+i.toString()).sortRows(0,"str","des");  
     		 document.getElementById("tab_lb_returnShipmentGrid"+i).style.display = 'none';
         }
    	 }
     	});
     
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
//     portletName = "Service request Drilldown lightbox";
  //   addPortlet(portletName);
    // pageTitle="Service request Drilldown lightbox";
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
/* function closePopup(){
	showSelect();
	jQuery(window).scrollTop(0);
	popUpWindow.destroy();
	popupFunction();
	
} */

</script>