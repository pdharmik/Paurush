<?xml version="1.0" encoding="UTF-8"?>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="util" uri="http://lexmark.com/tld/util" %>
<%@ taglib prefix="html" tagdir="/WEB-INF/tags/html" %>


<rows total_count="${totalcount}" pos="${startPos}">
	<c:forEach var="address" items="${addressList}" varStatus="rowCounter">
	
		<row id="${address.addressId}">
			
			<cell><![CDATA[
				<input id="btn_select_${address.addressId}" class="button"  type="button" onclick="selectAddress('${address.addressId}',this);" value="<spring:message code="button.select"/>"/>
			]]></cell>
			
			<cell><![CDATA[<c:choose><c:when test="${address.userFavorite eq true}">
				<img name="bookmark" id="starImg_address_${address.addressId}" class="helpIconList" title="<spring:message code="requestInfo.tooltip.address.UnBookmark"/>" src="<html:imagesPath/>favorite.png" onclick="setServiceAddressFavourite('${address.addressId}')"/>
			</c:when>
			<c:otherwise>
				<img name="bookmark" id="starImg_address_${address.addressId}"  class="helpIconList" title="<spring:message code="requestInfo.tooltip.address.Bookmark"/>" src="<html:imagesPath/>unfavorite.png" onclick="setServiceAddressFavourite('${address.addressId}')"/>
			</c:otherwise>
			</c:choose>
			]]></cell>
			
			
				
			<cell><![CDATA[${address.addressName}]]></cell>
			<cell><![CDATA[${address.storeFrontName}]]></cell>
			<cell><![CDATA[${address.addressLine1}]]></cell>
			<cell><![CDATA[${address.addressLine2}]]></cell>
			<cell><![CDATA[${address.officeNumber}]]></cell>
			<cell><![CDATA[${address.city}]]></cell>
			<cell><![CDATA[${address.state}]]></cell>
			<cell><![CDATA[${address.province}]]></cell>
			<cell><![CDATA[${address.county}]]></cell>
			<cell><![CDATA[${address.district}]]></cell>
			<cell><![CDATA[${address.region}]]></cell>
			<cell><![CDATA[${address.postalCode}]]></cell>
			<cell><![CDATA[${address.country}]]></cell>
			
			<%--Below columns should be hidden just for data passing --%>
			<cell><![CDATA[${address.addressId}]]></cell>	
			<cell><![CDATA[${address.countryISOCode}]]></cell>	
				
			<cell><![CDATA[${address.physicalLocation1}]]></cell>
			<cell><![CDATA[${address.physicalLocation2}]]></cell>	
			<cell><![CDATA[${address.physicalLocation3}]]></cell> 					
		</row>
	</c:forEach>
</rows>