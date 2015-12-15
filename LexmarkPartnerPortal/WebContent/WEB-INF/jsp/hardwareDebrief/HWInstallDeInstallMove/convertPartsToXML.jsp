<?xml version="1.0" encoding="UTF-8"?>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="util" uri="http://lexmark.com/tld/util" %>
<%@ taglib uri="/WEB-INF/tld/liferay-portlet.tld" prefix="portlet"%>
<%@ page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>
<rows>
	<c:forEach var="partList" items="${partList}" varStatus="rowCounter">
		<row id="${startPos+rowCounter.index}">	
			<cell><![CDATA[${partList.partNumber}]]></cell>
			<cell><![CDATA[${partList.partName}]]></cell>
			<cell><![CDATA[${partList.returnRequiredFlag}]]></cell>
			<cell><![CDATA[<input id="btn_select" name="btn_select" class="button"  type="button" value="<spring:message code='button.select'/>" onclick="selectRow('${startPos+rowCounter.index}',this)"/>]]></cell>
			<cell><![CDATA[${partList.typePrinter}]]></cell>			
		</row>
		
		
	</c:forEach>
</rows>