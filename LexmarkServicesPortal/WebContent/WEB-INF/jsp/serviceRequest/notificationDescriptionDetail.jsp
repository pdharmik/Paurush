<%@ include file="/WEB-INF/jsp/include.jsp"%>
<html>
<head>
<title>Notifications Detail</title>

<style>
.leftPadding {padding-left:20px;}
</style>

</head>
<body>
<table width="100%" height="100%" border="1px" rules="none">
	<tr>
		<td colSpan="2">
		<br>
		</td>
	</tr>
	<tr>
		<td align="left" valign="top" width="50%" class="leftPadding">
			<spring:message code='serviceRequest.label.date'/>:&nbsp;
		</td>
		<td align="left" valign="top" width="50%">
			<%=request.getParameter("date") %>
		</td>
	</tr>
	<tr>
		<td align="left"  valign="top" class="leftPadding">
			<spring:message code='serviceRequest.label.to'/>:&nbsp;
		</td>
		<td align="left" valign="top">
			<%=request.getParameter("recipient") %>
		</td>
	</tr>
	<tr>
		<td align="left" valign="top" class="leftPadding">
			<spring:message code='serviceRequest.label.description'/>:&nbsp;
		</td>
		<td align="left" valign="top">
			<%=request.getParameter("description") %>
		</td>
	</tr>
	<!-- Added extra row for defect 4515  -->
	<tr>
		<td align="left" valign="top" class="leftPadding">
			<spring:message code='requestInfo.label.comments'/>:&nbsp;
		</td>
		<td id="comment" align="left" valign="top">
			<%--Changes for CI Defect # 12367--%>
			<%=request.getParameter("comment") %>
		</td>
	</tr>
	<!-- End of defect 4515 -->
	<tr>
		<td colSpan="2">
		<br>
		</td>
	</tr>
</table>
<div width="100%" align="right"><button type="button" class="button" onclick="closeWindow(this);"><spring:message code='button.close'/></button></div>
<script type="text/javascript">
function closeWindow(element){
	Liferay.Popup.close(element);
}
jQuery(document).ready(function(){
	jQuery('#comment').html(SRNotificationsGrid.cells(<%=request.getParameter("selectedRowId")%>,4).cell.innerHTML);
});
</script>
</body>
</html>
