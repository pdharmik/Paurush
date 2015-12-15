<?xml version="1.0" encoding="UTF-8"?>
<%@ taglib uri="/WEB-INF/tld/liferay-portlet.tld" prefix="portlet"%>
<%@ taglib prefix="util" uri="http://lexmark.com/tld/util" %> 
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<rows total_count="${totalcount}" pos="${startPos}">
	<c:forEach var="activity" items="${activityList}" varStatus="status">
	<c:set var="requestType" value="${activity.serviceRequest.serviceRequestType.value}"/>
	<c:set var="createChildSR" value="${activity.createChildSR}"/>
	<!-- added by ragesree -2098 start-->
	<c:set var="serviceType" value="${activity.activityType.name}"/>
	<!-- added by ragesree -2098 end-->
	
	<portlet:renderURL var="claimRequestDetailURL">
		<portlet:param name='action' value='retrieveClaimDetail' />
		<portlet:param name="serviceRequestId" value="${activity.serviceRequest.id}" />
		<portlet:param name="activityId" value="${activity.activityId}" />
		<portlet:param name="timezoneOffset" value="${timezoneOffset}" />
	</portlet:renderURL>
	<portlet:renderURL var="serviceRequestDetailURL">
		<portlet:param name='action' value='showRequestDetailPage' />
		<portlet:param name="serviceRequestId" value="${activity.serviceRequest.id}" />
		<portlet:param name="activityId" value="${activity.activityId}" />
		<portlet:param name="timezoneOffset" value="${timezoneOffset}" />
	</portlet:renderURL>
	<portlet:renderURL  var="updateRequestUrl">
		<portlet:param name="action" value="showUpdateRequestPage" />
		<portlet:param name="serviceRequestId" value="${activity.serviceRequest.id}" />
		<portlet:param name="activityId" value="${activity.activityId}" />
		<portlet:param name="requestFromPage" value="requestsListView" />
		<portlet:param name="timezoneOffset" value="${timezoneOffset}" />
	</portlet:renderURL>
	<portlet:renderURL  var="updateClaimUrl">
		<portlet:param name="action" value="showClaimUpdatePage" />
		<portlet:param name="serviceRequestId" value="${activity.serviceRequest.id}" />
		<portlet:param name="activityId" value="${activity.activityId}" />
		<portlet:param name="serviceType" value="${activity.activityType.name}" />
		<portlet:param name="fromPage" value="requestList" />
		<portlet:param name="timezoneOffset" value="${timezoneOffset}" />
	</portlet:renderURL>
	<portlet:renderURL  var="showCloseOutClaimPage">
	    <portlet:param name="action" value="showCloseOutClaimPage" />
	    <portlet:param name="activityId" value="${activity.activityId}" />
	    <portlet:param name="serviceRequestId" value="${activity.serviceRequest.id}" />
	    <portlet:param name="serviceType" value="${activity.activityType.name}" />
	    <portlet:param name="fromPage" value="requestListView" />
	    <portlet:param name="timezoneOffset" value="${timezoneOffset}" />
	</portlet:renderURL>
	<portlet:renderURL  var="showRequestsDebriefPage">
	    <portlet:param name="action" value="showRequestsDebriefPage" />
	    <portlet:param name="activityId" value="${activity.activityId}" />
	    <portlet:param name="serviceRequestId" value="${activity.serviceRequest.id}" />
	    <portlet:param name="fromPage" value="requestListView" />
	    <portlet:param name="timezoneOffset" value="${timezoneOffset}" />
	</portlet:renderURL>
		<row id="${startPos+status.index}">
		    <cell><![CDATA[
					<table width="800px"><tbody>
							<tr valign="top">
								<td width="30%">
									<table>
										<tbody>
											<tr>
												<td align="right" NOWRAP><h5><spring:message code="claim.label.serviceType" />&nbsp;&nbsp;</h5></td>
												<td>${activity.activityType.name}</td>
											</tr>
											<tr>
												<td align="right" NOWRAP><h5><spring:message code="claim.label.statusDetail" />&nbsp;&nbsp;</h5></td>
												<td>${activity.activitySubStatus.name}</td>
											</tr>
											<tr>
												<td align="right" NOWRAP><h5><spring:message code="claim.label.serialNumber" />&nbsp;&nbsp;</h5></td>
												<td>${activity.serviceRequest.asset.serialNumber}</td>
											</tr>
											<tr>
												<td align="right" NOWRAP><h5><spring:message code="claim.label.machineTypeModel" />&nbsp;&nbsp;</h5></td>
												<td>${activity.serviceRequest.asset.modelNumber}</td>
											</tr>
											<tr>
												<td align="right" NOWRAP><h5><spring:message code="claim.label.productPN/TLI" />&nbsp;&nbsp;</h5></td>
												<td>${activity.serviceRequest.asset.productTLI}</td>
											</tr>
										</tbody>
									</table>
								</td>
								<td width="50%">
									<table>
										<tbody>
											<tr>
												<td width="50%" align="right" NOWRAP><h5><spring:message code="claim.label.customerAccount" />&nbsp;&nbsp;</h5></td>
												<td>${activity.customerAccount.accountName}</td>
											</tr>
											<tr valign="top">
												<td align="right" NOWRAP><h5><spring:message code="claim.label.customerAddress" />&nbsp;&nbsp;</h5></td>
												<td>
													<util:addressOutput value="${activity.serviceAddress}"/>
												</td>
											</tr>
										</tbody>
									</table>
								</td>
								<td width="20%">
									<table>
										<tbody>
											<c:if test="${requestType == 'Service Request' && isDirectPartner && isTechnician && (activity.technician == null || activity.technician.contactId == '') && activity.activityStatus.value != 'Cancelled Service' && activity.activityStatus.value != 'Completed'}">
											<tr id="trAssignToMe_${activity.activityId}">
												<td width="15%"></td>
												<td><A href="#" onclick="assignToMe('${activity.activityId}')">&gt;&gt; <spring:message code="claim.label.assignToMe" /></A></td>
											</tr>
											</c:if>
											<tr>
												<td></td>
												<td>
													<c:if test="${requestType == 'Claim Request' && (isServiceMgr|| isPartnerAdministrator)}"><%-- Modified as part of MPS--%>
													<util:requestActionLink activityType="${activity.serviceRequest.serviceRequestType.value}" debriefStatus="${activity.debrief.debriefStatus}" value="${activity.activitySubStatus.value}" type="update">
														<A href="javascript:showUpdateClaimPage('${activity.serviceRequest.id}','${activity.activityId}','${activity.activityType.name}');">&gt;&gt; <spring:message code="claim.label.updateClaim" /></A>
													</util:requestActionLink>
													</c:if>
													<c:if test="${requestType == 'Service Request' && (isTechnician || isServiceMgr || isPartnerAdministrator) && activity.activityStatus.value != 'Cancelled Service'}"><%-- Modified as part of MPS--%>
													<util:requestActionLink activityType="${activity.serviceRequest.serviceRequestType.value}" debriefStatus="${activity.debrief.debriefStatus}" value="${activity.activitySubStatus.value}" type="update">
														<A href="javascript:showUpdateRequestPage('${activity.serviceRequest.id}','${activity.activityId}');">&gt;&gt; <spring:message code="claim.label.updateRequest" /></A>
													</util:requestActionLink>	
													</c:if>													
												</td>
											</tr>
											<tr>
												<td></td>
												<td>
													<c:if test="${requestType == 'Claim Request' && (isServiceMgr|| isPartnerAdministrator)}"><%-- Modified as part of MPS--%>
													<util:requestActionLink activityType="${activity.serviceRequest.serviceRequestType.value}" debriefStatus="${activity.debrief.debriefStatus}" value="${activity.activitySubStatus.value}" type="debrief">
														<A href="javascript:showCloseOutClaimPage('${activity.serviceRequest.id}','${activity.activityId}','${activity.activityType.name}');">&gt;&gt; <spring:message code="claim.label.closeOutClaim" /></A>
													</util:requestActionLink>
													</c:if>
													<c:if test="${requestType == 'Service Request' && (isTechnician || isServiceMgr|| isPartnerAdministrator)&& activity.activityStatus.value != 'Cancelled Service'}"><%-- Modified as part of MPS--%>
													<util:requestActionLink activityType="${activity.serviceRequest.serviceRequestType.value}" debriefStatus="${activity.debrief.debriefStatus}" value="${activity.activitySubStatus.value}" type="debrief">
														<A href="javascript:showRequestsDebriefPage('${activity.serviceRequest.id}','${activity.activityId}');">&gt;&gt; <spring:message code="claim.label.closeOutRequest" /></A>
													</util:requestActionLink>
													</c:if>
												</td>
											</tr>
											<tr>
												<td></td>
												<td>
													<c:if test="${requestType == 'Service Request' && (isTechnician || isServiceMgr || isPartnerAdministrator) && (createChildSR) && (!activity.isChildSR) && (activity.activityStatus.name != 'Completed' || activity.activitySubStatus.name != 'Service Action Complete') && (activity.parentSRNum=='')}"><%-- Modified as part of MPS--%>													
														<A href="javascript:createChildSR('${activity.serviceRequest.serviceRequestNumber}','${activity.activityId}','${activity.partnerAccount.accountId}','${activity.partnerAccount.organizationID}');">&gt;&gt; <spring:message code="serviceRequest.createChildSR" /></A>												
													</c:if>
												</td>
											</tr>
										</tbody>
									</table>
								</td>
							</tr>
						</tbody></table>
		    ]]></cell>
			<cell><![CDATA[<util:dateFormat value="${activity.activityDate}" showTime="true" timezoneOffset="${timezoneOffset}"/>]]></cell>
			<cell><![CDATA[${activity.serviceRequest.serviceRequestType.name}]]></cell>
			<!-- Added Activity Id in All Request -->
			<cell><![CDATA[
			<c:choose>
				<c:when test="${requestType == 'Change Request'}">
				<a style="cursor:pointer;" onClick="redirectToDebriefViewAndCloseOut('${activity.serviceRequest.serviceRequestNumber}','${activity.activityType.value}','view','${activity.activityId}');">${activity.activityId}</a>
				</c:when>
				<c:when test="${requestType == 'Service Request'}">
				${activity.activityId}
				</c:when>
			</c:choose>
			]]></cell>
			<c:if test="${requestType == 'Claim Request'}">
			<!-- Below edited for CI BRD13-10-01 -->
			<cell><![CDATA[<A href="javascript:showClaimRequestDetail('${activity.serviceRequest.id}','${activity.activityId}','${activity.activityType.name}','${activity.activityStatus.name}','${requestTyepeis}','${activity.serviceRequest.asset.assetId}');">${activity.serviceRequest.serviceRequestNumber}</A>]]></cell>
			</c:if>
			<c:if test="${requestType == 'Service Request'}">
			<cell><![CDATA[<A href="javascript:showServiceRequestDetail('${activity.serviceRequest.id}','${activity.activityId}');">${activity.serviceRequest.serviceRequestNumber}</A>]]></cell>
			</c:if>
			<!-- Added for CI -7 Defect #12370 -->
			<c:if test="${requestType == 'Change Request'}">
			<cell><![CDATA[${activity.serviceRequest.serviceRequestNumber}]]></cell>
			</c:if>
			
			<cell><![CDATA[${activity.serviceRequest.asset.serialNumber}]]></cell>
			<!-- Change Reverted for CI Defect # 10118 -->
			<cell><![CDATA[${activity.serviceRequest.asset.productLine}]]></cell>
			<cell><![CDATA[${activity.activityStatus.name}]]></cell>
			<cell><![CDATA[${activity.customerAccount.accountName}]]></cell>
			<cell><![CDATA[${activity.responseMetric}]]></cell>
			<cell><![CDATA[${activity.parentSRNum}]]></cell>
			<cell><![CDATA[${activity.serviceProviderReferenceNumber}]]></cell>
			<cell><![CDATA[${activity.serviceRequest.asset.modelNumber}]]></cell>
			<cell><![CDATA[${activity.serviceRequest.asset.productTLI}]]></cell>
			<cell><![CDATA[${activity.activitySubStatus.name}]]></cell>
			<cell><![CDATA[${activity.activityType.name}]]></cell>
			<cell><![CDATA[${activity.partnerAccount.accountName}]]></cell>
			<cell><![CDATA[${activity.technician.firstName}]]></cell>
			<cell><![CDATA[${activity.technician.lastName}]]></cell>
			<c:choose>
			<c:when test="${activity.customerRequestedResponseDate != null}">
				<cell><![CDATA[<util:dateFormat value="${activity.customerRequestedResponseDate}" showTime="true" timezoneOffset="${timezoneOffset}"/>]]></cell>
			</c:when>
			<c:otherwise>
				<cell></cell>
			</c:otherwise>
			</c:choose>			
			<cell><![CDATA[${activity.serviceAddress.addressLine1}]]></cell>
 			<cell><![CDATA[${activity.serviceAddress.officeNumber}]]></cell><%--added House No for CI BRD 13-10-08 --%>
			<cell><![CDATA[${activity.serviceAddress.city}]]></cell>
			<cell><![CDATA[${activity.serviceAddress.state}]]></cell>
			<cell><![CDATA[${activity.serviceAddress.province}]]></cell>
			<cell><![CDATA[${activity.serviceAddress.county}]]></cell><%--added County for CI BRD 13-10-08 --%>
			<cell><![CDATA[${activity.serviceAddress.district}]]></cell><%--added District for CI BRD 13-10-08 --%>
			<cell><![CDATA[${activity.serviceAddress.postalCode}]]></cell>
			<cell><![CDATA[${activity.serviceAddress.country}]]></cell>
			<cell><![CDATA[${activity.serviceRequest.primaryContact.firstName}]]></cell>
			<cell><![CDATA[${activity.serviceRequest.primaryContact.lastName}]]></cell>
			<!-- Changes for CI7 14-07-05 START-->
			<cell><![CDATA[${activity.agencyContactFirstName}]]></cell>
			<cell><![CDATA[${activity.agencyContactLastName}]]></cell>
			<cell><![CDATA[${activity.agencyContacteMail}]]></cell> 
			<!-- Changes for CI7 14-07-05 END-->
			<!-- changes for Child SR 14-07-01 -->
			<cell><![CDATA[${activity.localizedCreateChildSR}]]></cell>
			<!-- changes for Child SR 14-07-01 END-->
			<!-- changes for CI 15.3  start-->
			<!--cell><![CDATA[${activity.serviceProviderETA}]]></cell-->
			<cell><![CDATA[<util:dateFormat value="${activity.serviceProviderETA}" showTime="true" timezoneOffset="${timezoneOffset}"/>]]></cell>
			<!--cell><![CDATA[${activity.committedDate}]]></cell-->
			<cell><![CDATA[<util:dateFormat value="${activity.committedDate}" showTime="true" timezoneOffset="${timezoneOffset}"/>]]></cell>
			<!-- Changes for CI 15.3 end -->
			<cell><![CDATA[${pendingDebrief}]]></cell> 
		</row>
	</c:forEach>
</rows>