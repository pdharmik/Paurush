<%@page import=" static com.lexmark.constants.LexmarkPPConstants.SRTYPE"%>
<%@page import=" static com.lexmark.constants.LexmarkPPConstants.SRNUMBER"%>
<%@page import=" static com.lexmark.constants.LexmarkPPConstants.viewOrCloseout"%>
<%@page import=" static com.lexmark.constants.LexmarkPPConstants.BACKURL"%>
<%@page import=" static com.lexmark.constants.LexmarkConstants.TIMEZONE_OFFSET"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<form id="viewForm" action="hardwareinstallation" method="POST">
	 <input type="hidden" name="<%=SRTYPE%>" id="<%=SRTYPE%>" value="${srType}"/>
		<input type="hidden" name="<%=SRNUMBER%>" id="<%=SRNUMBER%>" value="${srNumber}"/>
		<input type="hidden" name="<%=viewOrCloseout%>" id="<%=viewOrCloseout%>" value="view"/>
		<input type="hidden" name="activityId" id="activityId" value="${activityId}"/>
		<input type="hidden" name="status" id="status" value=""/>
		<input type="hidden" name="acceptUpdate" id="acceptUpdate" value=""/>
		<input type="hidden" name="<%=TIMEZONE_OFFSET%>" id="<%=TIMEZONE_OFFSET%>" value="${timezoneOffset}"/>
		<input type="hidden" name="<%=BACKURL%>" value="/group/partner-portal/services-requests?back=hw"/>
</form>

<script>
showOverlay();
jQuery('#viewForm').submit();
</script>