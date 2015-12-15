<?xml version="1.0" encoding="UTF-8"?>
<%@ taglib uri="/WEB-INF/tld/liferay-portlet.tld" prefix="portlet"%>
<%@ taglib prefix="util" uri="http://lexmark.com/tld/util" %> 
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<rows total_count="${totalCount}" pos="${startPos}">
	<c:forEach var="activity" items="${paymentLineItemList}" varStatus="status">
		<row id="${startPos+status.index}">
			<cell><![CDATA[<util:dateFormat value="${activity.activityDate}" showTime="true" timezoneOffset="${timezoneOffset}"/>]]></cell>
			<cell><![CDATA[<A onclick="javascript:showRequestDetail('${activity.serviceRequest.id}','${activity.activityId}','${paymentId}')">${activity.serviceRequest.serviceRequestNumber}</A>]]></cell>
			<cell><![CDATA[${activity.serviceProviderReferenceNumber}]]></cell>
			<cell><![CDATA[${activity.paymentServiceType}]]></cell>
			<cell><![CDATA[${activity.laborPayment}]]></cell>
			<cell><![CDATA[${activity.partsPayment}]]></cell>
			<cell><![CDATA[${activity.additionalPayments}]]></cell>
			<cell><![CDATA[${activity.partnerFee}]]></cell>
			<cell><![CDATA[${activity.payment.supplyAgreementFee}]]></cell>
			<cell><![CDATA[${activity.totalPayment}]]></cell>
			<cell><![CDATA[${activity.customerAccount.accountName}]]></cell>
			<cell><![CDATA[${activity.serviceRequest.asset.serialNumber}]]></cell>
			<cell><![CDATA[${activity.serviceRequest.asset.productTLI}]]></cell>
			<cell><![CDATA[${activity.serviceRequest.asset.modelNumber}]]></cell>
			<cell><![CDATA[${activity.serviceRequest.asset.productLine}]]></cell>
		</row>
	</c:forEach>
</rows>