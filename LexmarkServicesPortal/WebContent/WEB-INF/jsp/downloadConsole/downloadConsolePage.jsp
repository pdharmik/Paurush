<%@ include file="/WEB-INF/jsp/include.jsp"%>
<div class="portlet-section-header"></div>
<div class="portlet-section-body">
<br/>
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
	<c:if test="${hasPermission}">
		window.location.href = "${emailLink}";
	</c:if>
</script>
