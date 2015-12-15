<?xml version="1.0" encoding="UTF-8"?>
<%@ taglib uri="/WEB-INF/tld/liferay-portlet.tld" prefix="portlet"%>
<%@ taglib prefix="util" uri="http://lexmark.com/tld/util" %> 
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>

<rows total_count="${totalcount}" pos="${startPos}">
	<c:forEach var="activity" items="${activityList}" varStatus="status">
		<portlet:renderURL var="paymentDetailURL">
			<portlet:param name='action' value='showPaymentDetail' />
			<portlet:param name="paymentId" value="${activity.payment.paymentId}" />
		</portlet:renderURL>
		<portlet:renderURL var="serviceRequestDetailURL">
			<portlet:param name='action' value='showRequestDetailPage' />
			<portlet:param name="serviceRequestId" value="${activity.serviceRequest.id}" />
			<portlet:param name="activityId" value="${activity.activityId}" />
			<portlet:param name="from" value="paymentRequestList"/>
		</portlet:renderURL>
		<row id="${startPos+status.index}">	
			<cell><![CDATA[<A href="javascript:showRequestDetailPopupPage('${activity.serviceRequest.serviceRequestNumber}');"><b><spring:message code='claim.label.detail'/></b></A>]]></cell>
			<cell><![CDATA[<util:dateFormat value="${activity.activityDate}" showTime="true" timezoneOffset="${timezoneOffset}" />]]></cell>
			<cell><![CDATA[<A href="javascript:showRequestDetailPage('${activity.serviceRequest.id}','${activity.activityId}');">${activity.serviceRequest.serviceRequestNumber}</A>]]></cell>
			<cell><![CDATA[${activity.serviceProviderReferenceNumber}]]></cell>
			<cell><![CDATA[${activity.paymentServiceType}]]></cell>
			<cell><![CDATA[${activity.serviceRequest.serviceRequestStatus}]]></cell>
			<cell><![CDATA[${activity.payment.paymentStatus.name}]]></cell>
			<cell><![CDATA[<A href="javascript:showPaymentDetail('${activity.payment.paymentId}');">${activity.payment.paymentNumber}</A>]]></cell>
			<cell><![CDATA[${activity.eligibleToPay}]]></cell>
			<cell><![CDATA[${activity.payEligiblityOverride}]]></cell>
			<cell><![CDATA[${activity.laborPayment}]]></cell>
			<cell><![CDATA[${activity.partsPayment}]]></cell>
			<cell><![CDATA[${activity.additionalPayments}]]></cell>
			<cell><![CDATA[${activity.partnerFee}]]></cell>
			<cell><![CDATA[${activity.totalPayment}]]></cell>
			<cell><![CDATA[${activity.partnerAccount.accountName}]]></cell>
			<cell><![CDATA[${activity.partnerAgreementName}]]></cell>
			<cell><![CDATA[${activity.customerAccount.accountName}]]></cell>
			<cell><![CDATA[${activity.serviceRequest.asset.serialNumber}]]></cell>
			<cell><![CDATA[${activity.serviceRequest.asset.productTLI}]]></cell>
			<cell><![CDATA[${activity.serviceRequest.asset.modelNumber}]]></cell>
			<cell><![CDATA[${activity.serviceRequest.asset.productLine}]]></cell>
		</row>
	</c:forEach>
</rows>