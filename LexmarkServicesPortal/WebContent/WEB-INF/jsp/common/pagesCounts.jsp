<%@ page contentType="text/html" isELIgnored="false" %>
<%@ taglib uri="/WEB-INF/tld/liferay-portlet.tld" prefix="portlet"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="html" tagdir="/WEB-INF/tags/html" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<c:if test="${assetPageCountsList.name == 'LTPC' }">
<img class="helpIcon ui_icon_sprite info-icon"  src="<html:imagesPath/>transparent.png" title="<spring:message code="requestInfo.tooltip.LTPC"/>" >
</c:if>
<c:if test="${assetPageCountsList.name == 'A3 LTPC' }">
<img class="helpIcon ui_icon_sprite info-icon"  src="<html:imagesPath/>transparent.png" title="<spring:message code="requestInfo.tooltip.a3LTPC"/>" >
</c:if>
<c:if test="${assetPageCountsList.name == 'A4 LTPC' }">
<img class="helpIcon ui_icon_sprite info-icon"  src="<html:imagesPath/>transparent.png" title="<spring:message code="requestInfo.tooltip.a4LTPC"/>" >
</c:if>
<c:if test="${assetPageCountsList.name == 'A5 LTPC' }">
<img class="helpIcon ui_icon_sprite info-icon"  src="<html:imagesPath/>transparent.png" title="<spring:message code="requestInfo.tooltip.a5LTPC"/>" >
</c:if>
<c:if test="${assetPageCountsList.name == 'Letter LTPC' }">
<img class="helpIcon ui_icon_sprite info-icon"  src="<html:imagesPath/>transparent.png" title="<spring:message code="requestInfo.tooltip.letterLTPC"/>" >
</c:if>
<c:if test="${assetPageCountsList.name == 'Legal LTPC' }">
<img class="helpIcon ui_icon_sprite info-icon"  src="<html:imagesPath/>transparent.png" title="<spring:message code="requestInfo.tooltip.legalLTPC"/>" >
</c:if>
<c:if test="${assetPageCountsList.name == 'Statement LTPC' }">
<img class="helpIcon ui_icon_sprite info-icon"  src="<html:imagesPath/>transparent.png" title="<spring:message code="requestInfo.tooltip.statementLTPC"/>" >
</c:if>
<c:if test="${assetPageCountsList.name == 'Tabloid LTPC' }">
<img class="helpIcon ui_icon_sprite info-icon"  src="<html:imagesPath/>transparent.png" title="<spring:message code="requestInfo.tooltip.tabloidLTPC"/>" >
</c:if>
<c:if test="${assetPageCountsList.name == 'Tabloid Color' }">
<img class="helpIcon ui_icon_sprite info-icon"  src="<html:imagesPath/>transparent.png" title="<spring:message code="pageCntUploadLegend.column.tabloidColorHeader"/>" >
</c:if>
<c:if test="${assetPageCountsList.name == 'Statement Color' }">
<img class="helpIcon ui_icon_sprite info-icon"  src="<html:imagesPath/>transparent.png" title="<spring:message code="pageCntUploadLegend.column.statementColorHeader"/>" >
</c:if>
<c:if test="${assetPageCountsList.name == 'A5 Color' }">
<img class="helpIcon ui_icon_sprite info-icon"  src="<html:imagesPath/>transparent.png" title="<spring:message code="pageCntUploadLegend.column.a5ColorHeader"/>" >
</c:if>
<c:if test="${assetPageCountsList.name == 'A3 Color' }">
<img class="helpIcon ui_icon_sprite info-icon"  src="<html:imagesPath/>transparent.png" title="<spring:message code="pageCntUploadLegend.column.a3ColorHeader"/>" >
</c:if>
<c:if test="${assetPageCountsList.name == 'A4 Color' }">
<img class="helpIcon ui_icon_sprite info-icon"  src="<html:imagesPath/>transparent.png" title="<spring:message code="pageCntUploadLegend.column.a4ColorHeader"/>" >
</c:if>
<c:if test="${assetPageCountsList.name == 'Color' }">
<img class="helpIcon ui_icon_sprite info-icon"  src="<html:imagesPath/>transparent.png" title="<spring:message code="pageCntUpload.color.help"/>" >
</c:if>
<c:if test="${assetPageCountsList.name == 'Letter Color' }">
<img class="helpIcon ui_icon_sprite info-icon"  src="<html:imagesPath/>transparent.png" title="<spring:message code="pageCntUploadLegend.column.letterColorHeader"/>" >
</c:if>
<c:if test="${assetPageCountsList.name == 'Legal Color' }">
<img class="helpIcon ui_icon_sprite info-icon"  src="<html:imagesPath/>transparent.png" title="<spring:message code="pageCntUploadLegend.column.legalColorHeader"/>" >
</c:if>
<c:if test="${assetPageCountsList.name == 'Total Scans' }">
<img class="helpIcon ui_icon_sprite info-icon"  src="<html:imagesPath/>transparent.png" title="<spring:message code="pageCntUploadLegend.column.totalScansHeader"/>" >
</c:if>
<c:if test="${assetPageCountsList.name == 'PGS_SCAN_COPY' }">
<img class="helpIcon ui_icon_sprite info-icon"  src="<html:imagesPath/>transparent.png" title="<spring:message code="pageCntUploadLegend.column.pgsScanCopyHeader"/>" >
</c:if>
<c:if test="${assetPageCountsList.name == 'PGS_SCAN_FAX' }">
<img class="helpIcon ui_icon_sprite info-icon"  src="<html:imagesPath/>transparent.png" title="<spring:message code="pageCntUploadLegend.column.pgsScanFaxHeader"/>" >
</c:if>
<c:if test="${assetPageCountsList.name == 'PGS_SCAN_NETWORK' }">
<img class="helpIcon ui_icon_sprite info-icon"  src="<html:imagesPath/>transparent.png" title="<spring:message code="pageCntUploadLegend.column.pgsScanNetworkHeader"/>" >
</c:if>
<c:if test="${assetPageCountsList.name == 'PGS_SCAN_USB' }">
<img class="helpIcon ui_icon_sprite info-icon"  src="<html:imagesPath/>transparent.png" title="<spring:message code="pageCntUploadLegend.column.pgsScanUsbHeader"/>" >
</c:if>
<c:if test="${assetPageCountsList.name == 'Mono' }">
<img class="helpIcon ui_icon_sprite info-icon"  src="<html:imagesPath/>transparent.png" title="<spring:message code="pageCntUploadLegend.column.MonoHeader"/>" >
</c:if>
<c:if test="${assetPageCountsList.name == 'Letter Mono' }">
<img class="helpIcon ui_icon_sprite info-icon"  src="<html:imagesPath/>transparent.png" title="<spring:message code="requestInfo.tooltip.letterMono"/>" >
</c:if>
<c:if test="${assetPageCountsList.name == 'Legal Mono' }">
<img class="helpIcon ui_icon_sprite info-icon"  src="<html:imagesPath/>transparent.png" title="<spring:message code="requestInfo.tooltip.legalMono"/>" >
</c:if>
<c:if test="${assetPageCountsList.name == 'A3 Mono' }">
<img class="helpIcon ui_icon_sprite info-icon"  src="<html:imagesPath/>transparent.png" title="<spring:message code="requestInfo.tooltip.a3Mono"/>" >
</c:if>
<c:if test="${assetPageCountsList.name == 'A4 Mono' }">
<img class="helpIcon ui_icon_sprite info-icon"  src="<html:imagesPath/>transparent.png" title="<spring:message code="requestInfo.tooltip.a4Mono"/>" >
</c:if>
<c:if test="${assetPageCountsList.name == 'A5 Mono' }">
<img class="helpIcon ui_icon_sprite info-icon"  src="<html:imagesPath/>transparent.png" title="<spring:message code="requestInfo.tooltip.a5Mono"/>" >
</c:if>
<c:if test="${assetPageCountsList.name == 'Black' }">
<img class="helpIcon ui_icon_sprite info-icon"  src="<html:imagesPath/>transparent.png" title="<spring:message code="pageCntUploadLegend.column.blackHeader"/>" >
</c:if>
<c:if test="${assetPageCountsList.name == 'Cyan' }">
<img class="helpIcon ui_icon_sprite info-icon"  src="<html:imagesPath/>transparent.png" title="<spring:message code="pageCntUploadLegend.column.cyanHeader"/>" >
</c:if>
<c:if test="${assetPageCountsList.name == 'Software' }">
<img class="helpIcon ui_icon_sprite info-icon"  src="<html:imagesPath/>transparent.png" title="<spring:message code="pageCntUploadLegend.column.softwareHeader"/>" >
</c:if>
<c:if test="${assetPageCountsList.name == 'Scans' }">
<img class="helpIcon ui_icon_sprite info-icon"  src="<html:imagesPath/>transparent.png" title="<spring:message code="pageCntUploadLegend.column.scanHeader"/>" >
</c:if>
<c:if test="${assetPageCountsList.name == 'Statement Mono' }">
<img class="helpIcon ui_icon_sprite info-icon"  src="<html:imagesPath/>transparent.png" title="<spring:message code="requestInfo.tooltip.statementMono"/>" >
</c:if>
<c:if test="${assetPageCountsList.name == 'Tabloid Mono' }">
<img class="helpIcon ui_icon_sprite info-icon"  src="<html:imagesPath/>transparent.png" title="<spring:message code="requestInfo.tooltip.tabloidMono"/>" >
</c:if>
