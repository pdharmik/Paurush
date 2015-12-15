<%@ taglib prefix="util" uri="http://lexmark.com/tld/util" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

 
<rows total_count="${totalcount}" pos="${startPos}">
	<c:forEach var="uploadDetail" items="${uploadDetailList}" varStatus="status">
		<row id="${startPos+status.index}">
			<cell><![CDATA[${uploadDetail.requestNumber}]]></cell>
			<cell><![CDATA[${uploadDetail.size}]]></cell>
			<cell><![CDATA[${uploadHistoryType[uploadDetail.subArea]}]]></cell>
			<cell><![CDATA[<c:choose>
					<c:when test='${uploadDetail.status eq "Error"}'>
						<a onClick="downloadErrorFile('${uploadDetail.srRowId}','${uploadDetail.fileName}.${uploadDetail.extension}');" style="cursor:pointer;">${uploadDetail.status}</a>
					</c:when>
					<c:otherwise>
						${uploadDetail.status}
					</c:otherwise>
				</c:choose>]]></cell>
			<cell><![CDATA[<util:dateFormat value="${uploadDetail.submittedOn}" timezoneOffset="${timeZoneOffset}" showTime="true" showSecond="true"></util:dateFormat>]]></cell>
			<cell><![CDATA[<util:dateFormat value="${uploadDetail.completedOn}" timezoneOffset="${timeZoneOffset}" showTime="true" showSecond="true"></util:dateFormat>]]></cell>
			<cell><![CDATA[${uploadDetail.fileName}]]></cell>
		</row>
	</c:forEach>
</rows>