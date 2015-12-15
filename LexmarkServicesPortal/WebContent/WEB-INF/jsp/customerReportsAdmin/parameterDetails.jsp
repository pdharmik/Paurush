<%@ include file="/WEB-INF/jsp/include.jsp"%>
<head></head>
<style type="text/css">
body{
    align: left;
    text-align:left;
}
</style>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/in-styles.css?version=<html:fileCacheVersion/>" />
<body>
	<table>
		<tr>
			<td  class="orangeSectionTitles" valign="top">
				<spring:message code="reportAdmin.label.rptAdminSchedulerParameters"/>
			</td>
		</tr>
		<tr>
			<td>
				<LABEL class="labelBold"><spring:message code="reportAdmin.label.reportName"/>:</LABEL>&nbsp;<label id="reportName"></label>
			</td>
		</tr>
		<tr>
			<td>
				<LABEL class="labelBold"><spring:message code="reportAdmin.label.reportRunDateTime"/>:</LABEL>&nbsp;<label id="runTime"></label>
			</td>
		</tr>
		
	</table>
	<br/>
	<table width="100%" class="table-style12">
		<tr  >
			<td class="labelBold" width="40%">&nbsp;&nbsp;<spring:message code="customerReportsAdmin.label.parameterName"/></td>
			<td class="labelBold" width="60%"> <spring:message code="customerReportsAdmin.label.value"/></td>
		<c:forEach items="${parameters}" var="parameter">
		<tr  >
			<td>&nbsp;&nbsp;${parameter.key}</td>	
			<td> ${parameter.value}</td>
		</tr>
		</c:forEach>
	</table>
	<table width="100%">
		<tr><td>&nbsp;</td></tr>
		<tr><td align="right"> <a href="###" class="button" onclick="dialog.dialog('close');"><span><spring:message code="button.close" /></span></a></td></tr>
	</table>
</body>
<script type="text/javascript">
	document.getElementById("reportName").innerHTML=window.parent.window.showReportName;
	document.getElementById("runTime").innerHTML=window.parent.window.showRunTime;
</script>
<script type="text/javascript">
//---- Ominture script 
     portletName = "Report Admin";
     addPortlet(portletName);
     pageTitle="Parameter detail";
</script>