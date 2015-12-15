<?xml version="1.0" encoding="UTF-8"?>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<rows>
	<c:forEach var="serviceRequest" items="${gridData}" varStatus="status">
		<row id="${status.index}">
		    <cell><![CDATA[<A href="javascript:goToRequestDetails('${serviceRequest.serviceRequestNumber}', '${serviceRequest.requestType}');">${serviceRequest.serviceRequestNumber}</A>]]></cell>
		   <cell><![CDATA[ ${serviceRequest.serviceRequestDate}  ]]></cell>
		    <cell><![CDATA[  ${serviceRequest.requestType}  ]]></cell>
		    <cell><![CDATA[  ${serviceRequest.area.value} ]]></cell>
		   <cell><![CDATA[  ${serviceRequest.serviceRequestStatus}]]></cell>
		   <cell><![CDATA[  ${serviceRequest.webOrderNumber}]]></cell>
		   <cell><![CDATA[  ${serviceRequest.primaryContact.firstName}]]></cell>
		   <cell><![CDATA[  ${serviceRequest.primaryContact.lastName}]]></cell>
		   <cell><![CDATA[  ${serviceRequest.primaryContact.emailAddress}]]></cell>
		    
		</row>
</c:forEach>
</rows>