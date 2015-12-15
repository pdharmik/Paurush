<?xml version="1.0" encoding="UTF-8"?>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="util" uri="http://lexmark.com/tld/util" %>
<%@ taglib prefix="html" tagdir="/WEB-INF/tags/html" %>

<rows total_count="${totalcount}" pos="${startPos}">
	<c:forEach var="contact" items="${contactList}" varStatus="rowCounter">
		<row id="${contact.contactId}">	
			<cell><![CDATA[<c:choose><c:when test="${contact.userFavorite eq true}">
				<img name="bookmark" id="starImg_primary_${contact.contactId}" class="helpIconList" title="<spring:message code="requestInfo.tooltip.contact.UnBookmark"/>" src="<html:imagesPath/>favorite.png"/>
			</c:when>
			<c:otherwise>
				<img name="bookmark" id="starImg_primary_${contact.contactId}"  class="helpIconList" title="<spring:message code="requestInfo.tooltip.contact.Bookmark"/>" src="<html:imagesPath/>unfavorite.png"/>
			</c:otherwise>
			</c:choose>
			]]></cell>
			
			
				
			<cell><![CDATA[${contact.lastName}]]></cell>
			<cell><![CDATA[${contact.firstName}]]></cell>
			<cell><![CDATA[<util:replaceNextLineAndSpace value="${contact.workPhone}"/>]]></cell>
			<cell><![CDATA[<util:replaceNextLineAndSpace value="${contact.alternatePhone}"/>]]></cell>
			<cell><![CDATA[<util:replaceNextLineAndSpace value="${contact.emailAddress}"/>]]></cell>
			<cell><![CDATA[<c:if test="${contact.manageContactFlag eq false}">
					<input name="edit" class="button" value="<spring:message code="button.edit"/>"  type="button" onclick="changeToEdit('${contact.contactId}',this)" style="cursor:pointer;"/>
			</c:if>]]></cell>	
			<cell><![CDATA[
				<input id="btn_select_${contact.contactId}" class="button"  type="button" onclick="selectContact('${contact.contactId}',this);" value="<spring:message code="button.select"/>"/>
			]]></cell>			
		</row>
	</c:forEach>
</rows>