<?xml version="1.0" encoding="UTF-8"?>
<%@ taglib uri="/WEB-INF/tld/liferay-portlet.tld" prefix="portlet"%>
<%@ taglib prefix="util" uri="http://lexmark.com/tld/util" %> 
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<rows total_count="${totalcount}" pos="${startPos}">
	<c:forEach var="payment" items="${paymentList}" varStatus="status">
	<portlet:renderURL var="paymentDetailURL">
		<portlet:param name='action' value='showPaymentDetail' />
		<portlet:param name="paymentId" value="${payment.paymentId}" />
	</portlet:renderURL>
		<row id="${startPos+status.index}">
			<cell><![CDATA[<util:dateFormat value="${payment.dateCreated}" timezoneOffset="${timezoneOffset}" />]]></cell>
			<cell><![CDATA[<A href="javascript:showPaymentDetail('${payment.paymentId}');">${payment.paymentNumber}]]></cell>
			<cell><![CDATA[${payment.partnerAgreement}]]></cell>
			<cell><![CDATA[${payment.checkNumber}]]></cell>
			<cell><![CDATA[${payment.paymentTotal}]]></cell>
			<cell><![CDATA[${payment.partnerAccount.accountName}]]></cell>
			<cell><![CDATA[${payment.payableToName}]]></cell>
			<cell><![CDATA[${payment.partnerAccount.address.addressLine1}]]></cell>
			<cell><![CDATA[${payment.partnerAccount.address.addressLine2}]]></cell>
			<cell><![CDATA[${payment.partnerAccount.address.addressLine3}]]></cell>
			<cell><![CDATA[${payment.partnerAccount.address.city}]]></cell>
			<cell><![CDATA[${payment.partnerAccount.address.state}]]></cell>
			<cell><![CDATA[${payment.partnerAccount.address.province}]]></cell>
			<cell><![CDATA[${payment.partnerAccount.address.postalCode}]]></cell>
		</row>
	</c:forEach>
</rows>