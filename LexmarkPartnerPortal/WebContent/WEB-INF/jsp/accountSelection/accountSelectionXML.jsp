<?xml version="1.0" encoding="UTF-8"?>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<rows total_count="${totalcount}" pos="${startPos}">
	<c:forEach var="account" items="${accountList}" varStatus="status">
			
	
		<row id="${startPos+status.index}${account.accountId}">
			<cell><![CDATA[${account.accountId}]]></cell>
			<cell><![CDATA[${account.address.city}]]></cell>
			<cell><![CDATA[${account.address.country}]]></cell>
			<cell><![CDATA[${account.accountName}]]></cell>
					
			<cell><![CDATA[<input id="button${startPos+status.index}${account.accountId}" name="btn_select" class="button" type="button"  value="<spring:message code="button.select"/>" onClick="setAccount('${account.accountId}','${startPos+status.index}${account.accountId}')"/>]]></cell>
			<%-- Added in mps 2.1 links hide show for massupload --%>
			<cell><![CDATA[${account.massUploadConsumablesFlag}]]></cell>
			<cell><![CDATA[${account.massUploadInstallDebriefFlag}]]></cell>
			<%-- Ends in mps 2.1 links hide show for massupload --%>	
		</row>
	</c:forEach>
</rows>