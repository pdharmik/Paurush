<%@ include file="/WEB-INF/jsp/include.jsp"%>
<div class="portlet-section-header"></div>
<div class="portlet-section-body">
<br/>
<portlet:resourceURL id="showReportEmaiLinkelPage" var="showReportEmaiLinkelPage"></portlet:resourceURL>	
	<div class="main-wrap">
		<div class="content">
						
		
			<table width="100%">
				<tr>
				<c:if test="${not hasPermission}">
					<td width="60px" height="30px">
						<p><img src="<html:imagesPath/>error.png"  width="32px" id = "errImg" title="err"/></p>
					</td>
					<td>
						<spring:message code="downloadConsole.message.noPermission"/>
					</td>
				</c:if>
				<c:if test="${hasPermission}">
				<script>
	
	var showreport = "${showReportEmaiLinkelPage}";
	url=showreport+"&id="+"${reportId}";
		window.location.href=url;
		</script>
	</c:if>
				
				<td>&nbsp;</td>
				</tr>
			</table>	
		</div>
	</div>	
<br/>
</div>
<script type='text/javascript'>
	portletName = "DownloadConsole";
	addPortlet(portletName);
	
</script>
