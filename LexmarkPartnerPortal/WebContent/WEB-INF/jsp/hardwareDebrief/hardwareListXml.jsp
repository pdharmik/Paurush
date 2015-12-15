<?xml version="1.0" encoding="UTF-8"?>

<%@ taglib prefix="util" uri="http://lexmark.com/tld/util" %> 
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="html" tagdir="/WEB-INF/tags/html" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%-- Constants to differentiate --%>

<c:set var="tzOffset" value="${timezoneOffset}" />
<%--ENDS Constants to differentiate --%>

<rows total_count="${totalcount}" pos="${startPos}">
	<c:forEach var="order" items="${orderList}" varStatus="status">
	
	<c:set var="srType" value="${fn:toUpperCase(order.activityType.value)}"/>
	<c:set var="isHwInstall" value="false"  />
	<c:set var="isHwDeInstall" value="false" />
	<c:set var="isHwMove" value="false" />	
		<c:choose>
			<c:when test='${fn:contains(srType, "DECOMMISSION")}'>
				<c:set var="isHwDeInstall" value="true" />
			</c:when>
			<c:when test='${fn:contains(srType, "MOVE")}'>
				<c:set var="isHwMove" value="true" />
			</c:when>
			<c:when test='${fn:contains(srType, "INSTALL") or fn:contains(srType, "CHANGE")}'>
				<c:set var="isHwInstall" value="true"  />
			</c:when>
			
			<%-- 
			<c:otherwise>
			<c:set var="serviceRequestType"  value="${hwMove}"/>
			</c:otherwise>
			--%>
		</c:choose>
			
		<row id="${startPos+status.index}">
		<%--This condition means the request is from Request tab --%>
		<c:if test='${requestType == "MADCRequests"}'>
			<cell><![CDATA[<c:if test="${order.serviceRequest.expediteOrder}"><img height="20" width="20" src="<html:imagesPath/>attention.png"/></c:if>]]></cell>
		</c:if>
	
		<cell><![CDATA[
			
				<table class="grid-inner-body" border="0" cellspacing="0" cellpadding="0">
					<tr>
					<td class="w20p"><ul style="list-style-type:none;padding:0px!important;"><li>
						<c:choose>
							<c:when test="${not empty order.serviceRequest.asset.productImageURL}">
								<img height="100" width="100" src="${order.serviceRequest.asset.productImageURL}"/>
							</c:when>
							<c:otherwise>
								<img height="80" width="80" src="<html:imagesPath/>dummy_printer.jpg"/>
							</c:otherwise>
						</c:choose>
						</li>
						<li>
						
							${order.serviceRequest.asset.deviceName}
						
						</li></ul>
						</td>
						
						<c:if test='${requestType != "MADCRequests"}'>
							<td width="15%"><p>${order.serviceRequest.asset.description}</p><p class="strong">Installation and Warranty</p></td>
						</c:if>
						<c:choose>
							<c:when test='${isHwDeInstall eq true || isHwMove eq true}'>
								<td width="35%">
								  <table class="dataGrid" width="100%" cellspacing="0" cellpadding="0" border="0">
									<tbody>
										<tr>
											<td class="label"><spring:message code="claim.label.serviceType" /></td><td>${order.activityType.name}</td>
										</tr>
										<tr>
											<td class="label" width="150"><spring:message code="claim.label.statusDetail" /></td>
											<td>${order.activitySubStatus.name}</td>
										</tr>
										<tr>
											<td class="label"><spring:message code="claim.label.serialNumber" /></td>
											<td>${order.serviceRequest.asset.serialNumber}</td>
										</tr>
										<tr>
											<td class="label"><spring:message code="claim.label.machineTypeModel" /></td>
											<td>
											
												${order.serviceRequest.asset.machineTypeModel}
											
											</td>
										</tr>
										<tr>
											<td class="label"><spring:message code="claim.label.productPN/TLI" /></td>
											<td>${order.serviceRequest.asset.productTLI}</td>
										</tr>
									</tbody>
								 </table>
								</td>
							</c:when>
							
							<c:otherwise>
								<td width="35%">
								<div class="scrollTable2">
									<table class="displayGrid w300" style="margin:0px;width: 100% !important;">
										<thead>
											<tr>
												<th><spring:message code="requestInfo.label.serviceOrder.partNumber"/></th>
												<th><spring:message code="requestInfo.label.serviceOrder.description"/></th>
												<th><spring:message code="requestInfo.label.serviceOrder.quantity"/></th>
											</tr>
										</thead>
										<tbody>	
									<c:choose>
									<c:when test="${not empty order.serviceRequest.asset.partList }">	
										<c:forEach var="bundlePart" items="${order.serviceRequest.asset.partList}" varStatus="statusPart">
											<c:choose>
												<c:when test="${statusPart.count%2==0}">
													<tr class="altRow">
												</c:when>
												<c:otherwise>
													<tr>
												</c:otherwise>
											</c:choose>
											<td>${bundlePart.partNumber}</td>
											<td>${bundlePart.description}</td>
											<td>${bundlePart.orderQuantity}</td>
										</tr>
									
									
									   </c:forEach>
									</c:when>
									<c:otherwise>
										<tr><td colspan="3"><spring:message code="requestInfo.hardwareDebrief.commonContactInfo.noRec"/></td></tr>
									</c:otherwise>
								  </c:choose>
									<tr>
									</tr>
									</tbody>	
								</table>
								</div>
							</td>
							</c:otherwise>
						</c:choose>
						
						<%--This condition means the request is from Request tab --%>
						<c:if test='${requestType == "MADCRequests"}'>
							<td class="" width="25%">
								<ul>
									<li class="strong"><spring:message code="requestInfo.requests.grid.heading.serviceAddress"/></li>
									<util:addressOutput value="${order.serviceAddress}" displayInDivs="true"></util:addressOutput>
									<%--<li>Target</li>
									<li>123 Sesame St.</li>
									<li>Apt. 400</li>
									<li>San Diego, CA 92111</li>
									<li>USA</li>--%>
								</ul>
							</td>
							<c:choose>
							<c:when test="${offlineRequests and ((order.offlineModeAttachment.comments=='' and order.offlineModeAttachment.fileName==null) or (order.offlineModeAttachment.fileName!='' && order.offlineModeAttachment.comments!=''))}">
							<td width="15%">
							<ul class="">
								<li>
									<button class="button buttonWide"  onclick="generateDoc('${order.offlineModeAttachment.fileName}','${order.offlineModeAttachment.fileSourcePath}','${order.offlineModeAttachment.fileSourceType}','${order.serviceRequest.id}',this,'${startPos+status.index}');"><spring:message code="requestInfo.offlineInstallRequest.generateDownload"/></button>
									
								</li>
							</ul>
							</td>
							</c:when>
							<c:when test="${showActionLink}">
							<td class="actionLinks" width="25%">
							<c:if test="${fn:toUpperCase(sessionScope.ldapUserData_PHASE2.USERSEGMENT)!='EMPLOYEE' || showFseSelector=='true'}">
								<ul class="">
								<c:if test="${(order.activitySubStatus == 'Pending SP Acknowledgement' || order.activitySubStatus =='') && order.activityStatus != 'Completed' && order.activityStatus != 'Cancelled Service'}">
									<li><a id="accept_${order.activityId}" style="cursor:pointer;" onclick="acceptRejectRequest('Accepted','${order.serviceRequest.serviceRequestNumber}','${order.activityId}',id)"> >> &nbsp;<spring:message code="requestInfo.link.serviceOrder.acceptRequest"/></a></li>
									<li><a id="reject_${order.activityId}" style="cursor:pointer;" onclick="acceptRejectRequest('Not Accepted','${order.serviceRequest.serviceRequestNumber}','${order.activityId}',id)"> >> &nbsp;<spring:message code="requestInfo.requests.grid.link.rejectRequest"/></a></li>
									<li><a id="update_${order.activityId}" style="cursor:pointer;display:none;" onClick="redirectToDebriefViewAndCloseOut('${order.serviceRequest.serviceRequestNumber}','${order.activityType.value}','closeOut','${order.activityId}')"> >> &nbsp;<spring:message code="requestInfo.requests.grid.link.updateRequest"/></a></li>
								</c:if>
								<c:if test="${order.activitySubStatus != 'Pending SP Acknowledgement' && order.activitySubStatus != 'Not Accepted' && order.activityStatus != 'Completed' && order.activitySubStatus !='' && order.activityStatus !='Cancelled Service'}">									
									<li><a style="cursor:pointer;" onClick="redirectToDebriefViewAndCloseOut('${order.serviceRequest.serviceRequestNumber}','${order.activityType.value}','closeOut','${order.activityId}')"> >> &nbsp;<spring:message code="requestInfo.requests.grid.link.updateRequest"/></a></li>
								</c:if>
								
								</ul>
							</c:if>
							</td>
							</c:when>
							</c:choose>
						</c:if>
					</tr>		
							
					</table>
		    ]]></cell>
		    <cell><![CDATA[ <c:choose>
							<c:when test="${offlineRequests}">
							<a style="cursor: pointer;">${order.serviceRequest.serviceRequestNumber}</a>
							</c:when>
							<c:otherwise>
							${order.serviceRequest.serviceRequestNumber}
							</c:otherwise>
							</c:choose>
							]]></cell>
			<cell><![CDATA[<util:dateFormat value="${order.serviceRequest.serviceRequestDate}" showTime="true" timezoneOffset="${timezoneOffset}"/>]]></cell>
			<cell><![CDATA[${order.serviceRequest.asset.serialNumber}]]></cell>
			<cell><![CDATA[${order.activityType.name}]]></cell>	
			<cell><![CDATA[${order.debriefStatus}]]></cell>		
			<cell><![CDATA[<a style="cursor:pointer;" onClick="redirectToDebriefViewAndCloseOut('${order.serviceRequest.serviceRequestNumber}','${order.activityType.value}','view','${order.activityId}');">${order.serviceRequest.asset.activityNumber}</a>]]></cell>
			
			<cell><![CDATA[${order.serviceRequest.asset.productModelNumber}]]></cell>
			<cell><![CDATA[${order.serviceRequest.asset.productLine}]]></cell>
			<cell><![CDATA[${order.serviceRequest.asset.productTLI}]]></cell>
			<c:choose>
			<c:when test="${offlineRequests}">			
			<cell><![CDATA[${order.serviceRequest.statusType.name}]]></cell>
			</c:when>
			<c:otherwise>
			<cell><![CDATA[${order.activityStatus.name}]]></cell>
			</c:otherwise>
			</c:choose>
			<cell><![CDATA[${order.activitySubStatus.name}]]></cell>
			<cell><![CDATA[${order.partnerAccount.accountName}]]></cell>
						
			<cell><![CDATA[${order.customerAccount.accountName}]]></cell>
			<cell><![CDATA[${order.responseMetric}]]></cell>
			<cell><![CDATA[${order.serviceRequest.projectName }]]></cell>
			<cell><![CDATA[<util:dateFormat value="${order.serviceRequest.actualStartDate}" showTime="false" timezoneOffset="${tzOffset}"/>]]></cell>
			
			<cell><![CDATA[<util:dateFormat value="${order.serviceRequest.asset.installDate}" showTime="false" timezoneOffset="${tzOffset}"/>]]></cell>
<%-- 			<cell><![CDATA[${order.technician.firstName}&nbsp;${order.technician.lastName}]]></cell> --%>
			<cell><![CDATA[${order.technician.technicianName}]]></cell>		
		
			<%--Ends request is from Request tab --%>
			<cell><![CDATA[${order.serviceRequest.asset.storeFrontName}]]></cell>
			<cell><![CDATA[${order.serviceAddress.addressLine1}]]></cell>
			<cell><![CDATA[${order.serviceAddress.officeNumber} ]]></cell>
			<cell><![CDATA[${order.serviceAddress.county}]]></cell>
			<cell><![CDATA[${order.serviceAddress.district}]]></cell>
			<cell><![CDATA[${order.serviceAddress.city}]]></cell>
			<cell><![CDATA[${order.serviceAddress.state}]]></cell>
			<cell><![CDATA[${order.serviceAddress.province}]]></cell>
			<cell><![CDATA[${order.serviceAddress.postalCode}]]></cell>
			<cell><![CDATA[${order.serviceAddress.country}]]></cell>
			<cell><![CDATA[${order.serviceRequest.primaryContact.firstName}]]></cell>
			<cell><![CDATA[${order.serviceRequest.primaryContact.lastName}]]></cell>
			<c:if test="${isRequests}">
			<cell><![CDATA[<util:dateFormat value="${order.expectedCompletionDate}" showTime="true" timezoneOffset="${timezoneOffset}"/>]]></cell>
			<cell><![CDATA[<util:dateFormat value="${order.dispatchDate}" showTime="true" timezoneOffset="${timezoneOffset}"/>]]></cell>
			<cell><![CDATA[<util:dateFormat value="${order.expectedStartInterventionDate}" showTime="true" timezoneOffset="${timezoneOffset}"/>]]></cell>
			</c:if>
			<c:if test="${isMassUpload}">
			
			<cell><![CDATA[<util:dateFormat value="${order.expectedStartInterventionDate}" showTime="true" timezoneOffset="${timezoneOffset}"/>]]></cell>
			<cell><![CDATA[<util:dateFormat value="${order.expectedCompletionDate}" showTime="true" timezoneOffset="${timezoneOffset}"/>]]></cell>
			</c:if>
			<c:if test="${offlineRequests}">
			<cell><![CDATA[
			<c:if test="${not empty order.offlineModeAttachment.fileSourcePath and not empty order.offlineModeAttachment.comments}">
			<a style="cursor: pointer;" onclick="acceptActivityWS('${order.serviceRequest.serviceRequestNumber}');" target="_blank" href="${order.offlineModeAttachment.fileSourcePath}"><spring:message code="requestInfo.offlineInstallRequest.download"/></a>
			</c:if>
			]]></cell>
			</c:if>
		
		    <%--
			<cell><![CDATA[<util:dateFormat value="${order.createdDate}" showTime="true" timezoneOffset="${timezoneOffset}"/>]]></cell>
			<cell><![CDATA[<A href="javascript:showOrderRequestDetail('${order.vendorId}','${order.orderNumber}','${order.serviceRequestNumber}');">${order.serviceRequestNumber}</A>]]></cell>
			<cell><![CDATA[<A href="javascript:showOrderRequestDetail('${order.vendorId}','${order.orderNumber}','${order.serviceRequestNumber}');">${order.orderNumber}</A>]]></cell>			
			<cell><![CDATA[${order.asset.serialNumber}]]></cell>
			<cell><![CDATA[${order.asset.productLine}]]></cell>
			<cell><![CDATA[${order.asset.machineTypeModel}]]></cell>
			<cell><![CDATA[${order.status}]]></cell>
			<c:set var="customerAccount" value="${order.customerAccount}"/>
			<cell><![CDATA[${fn:replace(customerAccount,'&', '&amp;')}]]></cell>
			<cell><![CDATA[${order.responseMetric}]]></cell>
			<cell><![CDATA[<util:dateFormat value="${order.customerRequestedResponseDate}" showTime="true" timezoneOffset="${timezoneOffset}"/>]]></cell>
			<cell><![CDATA[${order.serviceProviderReferenceNumber}]]></cell>			
			<cell><![CDATA[${order.customerAddress.addressLine1} ${order.customerAddress.addressLine2}]]></cell>
			<cell><![CDATA[${order.customerAddress.city}]]></cell>
			<cell><![CDATA[${order.customerAddress.state}]]></cell>
			<cell><![CDATA[${order.customerAddress.province}]]></cell>
			<cell><![CDATA[${order.customerAddress.postalCode}]]></cell>
			<cell><![CDATA[${order.customerContact.firstName}]]></cell>
			<cell><![CDATA[${order.customerContact.lastName}]]></cell>
			 --%>	
		</row>
	</c:forEach>
</rows>