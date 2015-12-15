<?xml version="1.0" encoding="UTF-8"?>
<%@ taglib uri="/WEB-INF/tld/liferay-portlet.tld" prefix="portlet"%>
<%@ taglib prefix="util" uri="http://lexmark.com/tld/util" %> 
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<rows total_count="${totalcount}" pos="${startPos}">
	<c:forEach var="order" items="${orderList}" varStatus="status">
<%-- 	<c:set var="requestType" value="${order.serviceRequest.serviceRequestType.value}"/> --%>
	
	
	
	<portlet:renderURL var="orderRequestDetailURL">
		<portlet:param name='action' value='retrieveOrderRequest' />
		<portlet:param name="vendorAccountId" value="${order.vendorId}" />
		<portlet:param name="orderNumber" value="${order.orderNumber}" />
		<portlet:param name="requestNumber" value="${order.serviceRequestNumber}" />		
		<portlet:param name="timezoneOffset" value="${timezoneOffset}" />
	</portlet:renderURL>
	<portlet:renderURL  var="updateOrderRequestUrl">
		<portlet:param name="action" value="showUpdateOrderRequestPage" />
		<portlet:param name="vendorAccountId" value="${order.vendorId}" />
		<portlet:param name="orderNumber" value="${order.orderNumber}" />
		<portlet:param name="requestNumber" value="${order.serviceRequestNumber}" />
		<portlet:param name="requestFromPage" value="orderRequestsListView" />
		<portlet:param name="timezoneOffset" value="${timezoneOffset}" />
	</portlet:renderURL>
		<row id="${startPos+status.index}">
		<cell><![CDATA[
					<table class="grid-inner-body" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td width="30%">
									<table class="dataGrid dataGridStatus" border="0" cellspacing="0" cellpadding="0">
											<tr>
												<td class="label" align="right"><spring:message code="requestInfo.label.serviceOrder.status" /></td>
												<td><span id="status_${order.id}">${order.status}</span></td>
											</tr>
											<tr>
												<td>&nbsp;</td>
												<td>&nbsp;</td>
											</tr>
									</table>
								</td>
								<td>
									<table class="dataGrid dataGridCust" border="0" cellspacing="0" cellpadding="0">
											<tr>
												<td class="label"><spring:message code="requestInfo.label.serviceOrder.customerAccount" /></td>
												<td>${order.customerAccount}</td>
											</tr>
											<tr valign="top">
												<td class="label"><spring:message code="requestInfo.label.serviceOrder.customerAddress" /></td>
												<td>
													<c:if test="${!empty order.customerAddress.addressLine1}">
														${order.customerAddress.addressLine1}<br>
													</c:if>
													<c:if test="${!empty order.customerAddress.addressLine2}">
														${order.customerAddress.addressLine2}<br>
													</c:if>						
													<c:if test="${!empty order.customerAddress.city}">
														${order.customerAddress.city}
														<c:if test="${!empty order.customerAddress.state}">
															,
														</c:if>
													</c:if>	
													<c:if test="${!empty order.customerAddress.state}">
														${order.customerAddress.state}
														<c:if test="${!empty order.customerAddress.province}">
															,
														</c:if>
													</c:if>
													<c:if test="${!empty order.customerAddress.province}">	
														${order.customerAddress.province}
														<c:if test="${!empty order.customerAddress.country}">
															,
														</c:if>
													</c:if>
													<c:if test="${!empty order.customerAddress.country}">	
														${order.customerAddress.country}
														<c:if test="${!empty order.customerAddress.postalCode}">
															,
														</c:if>
													</c:if>
													<c:if test="${!empty order.customerAddress.postalCode}">	
														${order.customerAddress.postalCode}&nbsp;
													</c:if>
													<%-- <util:addressOutput value="${order.customerAddress}"/> --%>
												</td>
											</tr>
									</table>
								</td>
								<c:if test="${isMassUpload != 'true' }">
								<td class="actionLinks">									
									<c:if test="${!empty order.pendingShipments}">		
										<c:if test="${order.status == 'Routed' && showProcessedUpdateFlag == 'true' && (!empty order.fulfillmentStatus && (order.fulfillmentStatus == 'Shipped' || order.fulfillmentStatus == 'Delivered'))}">
												<ul class="link" id="Accept_${order.id}"><li><A href="#" onclick="acceptOrderRequest('${order.vendorId}','${order.id}')"><spring:message code="requestInfo.link.serviceOrder.acceptRequest" /></A></li></ul>								
												<ul class="link" id="Update_${order.id}" style="display :none"><li><A href="javascript:showUpdateOrderRequestPage('${order.vendorId}','${order.orderNumber}','${order.serviceRequestNumber}');"><spring:message code="requestInfo.link.serviceOrder.updateOrderRequest" /></A></li></ul>
										</c:if>
										<c:if test="${(order.status == 'Order Accepted' && showProcessedUpdateFlag == 'true' && (!empty order.fulfillmentStatus && (order.fulfillmentStatus == 'Shipped' || order.fulfillmentStatus == 'Delivered')))
													|| (order.status == 'Back Ordered' && showProcessedUpdateFlag == 'true' && (!empty order.fulfillmentStatus && (order.fulfillmentStatus == 'Shipped' || order.fulfillmentStatus == 'Delivered'))) 
													|| (order.status == 'Shipped' && showProcessedUpdateFlag == 'true' && !empty order.fulfillmentStatus && order.fulfillmentStatus == 'Delivered')
													|| (order.status == 'Ship Pending')}">
																									
											<ul class="link"><li><A href="javascript:showUpdateOrderRequestPage('${order.vendorId}','${order.orderNumber}','${order.serviceRequestNumber}');"><spring:message code="requestInfo.link.serviceOrder.updateOrderRequest" /></A></li></ul>														
										</c:if>
									</c:if>
								</td>
								</c:if>								
							</tr>
							<c:if test="${!empty order.pendingShipments}">							
							<tr>
								<td colspan="3" valign="top">							
									<table class="displayGrid">
										<thead>								
											<tr>
												<th class="w100"><spring:message code="requestInfo.label.serviceOrder.partNumber" /></th>
												<th><spring:message code="requestInfo.label.serviceOrder.description" /></th>
												<th class="w150"><spring:message code="requestInfo.label.serviceOrder.partType" /></th>
												<th class="w100 right"><spring:message code="requestInfo.label.serviceOrder.orderedQty" /></th>
											</tr>
										</thead>
										<tbody>
										<c:forEach var="partLine" items="${order.pendingShipments}" varStatus="status">
										<c:choose>
										<c:when test="${status.count%2==0}">
											<tr class="altRow">
										</c:when>
										<c:otherwise>
											<tr>
										</c:otherwise>
										</c:choose>							
												<td class="w80">${partLine.partnumber}</td>
												<td>${partLine.productDescription}</td>
												<td>${partLine.partType}</td>
												<td class="w100 right">${partLine.quantity}</td>
											</tr>										
										</c:forEach>
										</tbody>
									</table>
								</td>
							</tr>
							</c:if>
							
						</tbody></table>
		    ]]></cell>
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
 			<cell><![CDATA[${order.customerAddress.officeNumber}]]></cell><%--House No. Added for CI BRD 13-10-08 --%>
			<cell><![CDATA[${order.customerAddress.city}]]></cell>
			<cell><![CDATA[${order.customerAddress.state}]]></cell>
			<cell><![CDATA[${order.customerAddress.province}]]></cell>
			<cell><![CDATA[${order.customerAddress.county}]]></cell><%--County Added for CI BRD 13-10-08 --%>
			<cell><![CDATA[${order.customerAddress.district}]]></cell><%--District Added for CI BRD 13-10-08 --%>
			<cell><![CDATA[${order.customerAddress.postalCode}]]></cell>
			<cell><![CDATA[${order.customerContact.firstName}]]></cell>
			<cell><![CDATA[${order.customerContact.lastName}]]></cell>	
		</row>
	</c:forEach>
</rows>