<?xml version="1.0" encoding="UTF-8"?>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="util" uri="http://lexmark.com/tld/util" %>
<%@ taglib uri="/WEB-INF/tld/liferay-portlet.tld" prefix="portlet"%>
<%@ page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>
<rows>
	<c:forEach var="activity" items="${activityList}" varStatus="rowCounter">
		<row>
			<cell><![CDATA[<util:dateFormat value="${activity.serviceRequest.serviceRequestDate}" showTime="true" timezoneOffset="${timezoneOffset}"/>]]></cell>
		<c:choose>
			<c:when test="${activity.serviceRequest.serviceRequestType.value == 'Claim Request'}">	
			<cell><![CDATA[<a href="javascript:void(0);" onClick="viewServiceHistoryPopup('<spring:message code="title.claimRequestDetail"/>','${activity.activityId}','${activity.serviceRequest.id}')"> ${activity.serviceRequest.serviceRequestNumber}</a>]]></cell>
			</c:when>
			<c:otherwise>
			<cell><![CDATA[<a href="javascript:void(0);" onClick="viewServiceHistoryPopup('<spring:message code="title.serviceRequestDetail"/>','${activity.activityId}','${activity.serviceRequest.id}')"> ${activity.serviceRequest.serviceRequestNumber}</a>]]></cell>
			</c:otherwise>
		</c:choose>			
			<cell><![CDATA[${activity.serviceRequest.serviceRequestType.name}]]></cell>
			<cell><![CDATA[${activity.activityType.name}]]></cell>
			<cell><![CDATA[${activity.activityStatus.name}]]></cell>
			<cell><![CDATA[${activity.serviceRequest.serviceRequestor}]]></cell>
			<cell><![CDATA[${activity.actualFailureCode.name}]]></cell>
		</row>
	</c:forEach>
</rows>