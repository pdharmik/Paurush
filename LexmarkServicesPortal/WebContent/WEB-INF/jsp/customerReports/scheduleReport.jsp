<%@ page import="com.lexmark.enums.ReportParameterTypeEnum, com.lexmark.services.form.*" %> 
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<%@ include file="/WEB-INF/jsp/includeDatePicker.jsp"%>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/mps.css"/>
<script type="text/javascript" src="<html:rootPath/>js/jQuery/jquery.qtip-1.0.0-rc3.min.js"></script>
<style type="text/css"><%@ include file="/WEB-INF/css/ci6.css" %></style>
<!--[if IE 7]>
	<style type="text/css"><%@ include file="/WEB-INF/css/mps_ie7.css" %></style>
<![endif]-->
<c:set var="booleanType" value="<%=ReportParameterTypeEnum.BOOLEAN.getType()%>"/>
<c:set var="dateType" value="<%=ReportParameterTypeEnum.DATE.getType()%>"/>
<c:set var="listType" value="<%=ReportParameterTypeEnum.LIST.getType()%>"/>
<c:set var="numericType" value="<%=ReportParameterTypeEnum.NUMERIC.getType()%>"/>
<c:set var="stringType" value="<%=ReportParameterTypeEnum.STRING.getType()%>"/>

<%int runNowParametersLength = ((ScheduleReportForm)request.getAttribute("reportScheduleForm")).getRunNowParameters().size();%>
<%int scheduleParametersLength = ((ScheduleReportForm)request.getAttribute("reportScheduleForm")).getRunNowParameters().size();%>

<!-- added by-->
<%
int parametersLength = 0;
if(runNowParametersLength > 0) {
	parametersLength = runNowParametersLength;
}
if(scheduleParametersLength > 0) {
	parametersLength = scheduleParametersLength;
}
%>
<!-- end of addition -->

<style>
div.buttomLine {
	border-bottom: #999 1px dotted;	
}
.displayGrid{line-height:20px;}

.schedule-now{
	margin:10px 0 0 ;
	font-family: 'Open Sans', sans-serif;
	padding:2px 5px;
	color:#1d1d25;
	text-transform:uppercase;
	float:left;
	width:97%;
	background:none repeat scroll 0 0 #e6e6f0;
}
.schedule-later-cntnr{
	float:left;
	width:65%;
	}
.schedule-later{
	font-family: 'Open Sans', sans-serif;
	color:#1d1d25;
	margin: 10px 5px 0 0;
    padding: 2px 5px;
    text-transform: uppercase;
    width: 98.5%;
	float:left;
	background:#e6e6f0;
}
.schedule-cntnt{
	float:left;
	width:100%;
	border:1px solid #e9e9e9;
}
.schedule-now-cntnt{
	float:left;
	border:1px solid #e9e9e9;
	width:96.3%;
	padding:5px;
}
.schedule-now-cntnr{
	float:left;
	width:30%;
	margin:0 10px 0 0;
}


.day-wek-cntnr{
	float:left;
	padding:5px;
	width:98.3%;
	background:#e7e7e7;
}
.day-week-title{
	float:left;
	font-family: 'Open Sans', sans-serif;
	font-size:14px;
	color:#666;
	background:url(/LexmarkServicesPortal/images/gradient-title-bg.jpg) repeat-y; 
	line-height:30px;
	text-transform:uppercase;
	margin:5px 0;
	width:100%;
}
input[type="text"], input[type="password"], input[type="date"], textarea, textarea.dhx_combo_edit {
	width:140px;
}
</style>

<script type="text/javascript">
	var needClose = '${scheduleSuccess}';

		if(needClose == 'success' || needClose =='failed'){
		
		var docId = '${docDefinitionIdInSession}';
		var roleId = '${roleCategoryIdInSession}';
		window.opener.refreshPage(docId, roleId);
		window.close();
	}
</script>

<portlet:actionURL var="saveReportSchedule">
    <portlet:param name="action" value="saveReportSchedule"></portlet:param>
</portlet:actionURL>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/in-styles.css?version=<html:fileCacheVersion/>" />
<div class="main-wrap">
	<div class="content">
	<div id="scheduleRptTitle" class="journal-content-article">
          <h1><spring:message code='customerReports.scheduleOneReport.label.title'/></h1>
    </div>
	<form:form id="scheduleReportForm" commandName="reportScheduleForm" method="post" action="${saveReportSchedule}">
		<% //if(parametersLength > 0){ %>
		<div class="portlet-wrap">
		<div class="portlet-header">
		
<table class="displayGrid">
              <thead>
                <tr><th><spring:message code="customerReports.scheduleOneReport.label.parametersDesc"/></th>
                </tr></thead>
              
            </table>
			
		</div>
		<!-- added -->
		<form:hidden path="reportScheduleId"/>
		<form:hidden path="reportDefinitionId"/>
		<!-- end of addition -->
		<div class="portlet-wrap-inner" id="runNowParamsDisplay">
			<table>
				<tr>
					<td align="left">
						<!-- commented -->
						<!-- form:hidden path="reportScheduleId"/ -->
						<!-- form:hidden path="reportDefinitionId"/ -->
						<table>
						<c:forEach items="${reportScheduleForm.runNowParameters}" var="rnparameter" varStatus="counter" begin="0">
							<form:hidden path="runNowParameters[${counter.index}].reportParameterId"/>
							<form:hidden path="runNowParameters[${counter.index}].name"/>
							<form:hidden path="runNowParameters[${counter.index}].dataType"/>
                                     <form:hidden path="runNowParameters[${counter.index}].isRequired"/>
                            <tr><td colspan="4">&nbsp;</td></tr>
							<tr>
							<c:choose>
								<c:when test="${rnparameter.isRequired}">
								<td id='requiredTd${counter.index}' align="right">*</td>
								<td id='maxLengthTd${counter.index}' style="display:none">${rnparameter.maxSize}</td>
								</c:when>
								<c:otherwise>
								<td id='requiredTd${counter.index}'></td>
								<td id='maxLengthTd${counter.index}' style="display:none">${rnparameter.maxSize}</td>
								</c:otherwise>
							</c:choose>
							<c:choose>
								<c:when test="${booleanType == rnparameter.dataType}">
								<!-- Changes to fetch displayNames based on locale -CI-7 #11656 -->
								<c:if test="${portalLocale == 'en_GB'}">
								<td align="left" id="displayName${counter.index}" width="130">${rnparameter.displayName}:</td>
								</c:if>
								<c:if test="${portalLocale == 'en_US'}">
								<td align="left" id="displayName${counter.index}" width="130">${rnparameter.displayName}:</td>
								</c:if>
								<c:if test="${portalLocale == 'fr_FR'}">
								<td align="left" id="displayName${counter.index}" width="130">${rnparameter.displayNameFrench}:</td>
								</c:if>
								<c:if test="${portalLocale == 'it_IT'}">
								<td align="left" id="displayName${counter.index}" width="130">${rnparameter.displayNameItaly}:</td>
								</c:if>
								<c:if test="${portalLocale == 'ja_JP'}">
								<td align="left" id="displayName${counter.index}" width="130">${rnparameter.displayNameJapan}:</td>
								</c:if>
								<c:if test="${portalLocale == 'ko_KR'}">
								<td align="left" id="displayName${counter.index}" width="130">${rnparameter.displayNameKorea}:</td>
								</c:if>
								<c:if test="${portalLocale == 'pt_BR'}">
								<td align="left" id="displayName${counter.index}" width="130">${rnparameter.displayNamePortugalBrazil}:</td>
								</c:if>
								<c:if test="${portalLocale == 'pt_PT'}">
								<td align="left" id="displayName${counter.index}" width="130">${rnparameter.displayNamePortugal}:</td>
								</c:if>
								<c:if test="${portalLocale == 'ru_RU'}">
								<td align="left" id="displayName${counter.index}" width="130">${rnparameter.displayNameRussia}:</td>
								</c:if>
								<c:if test="${portalLocale == 'es_ES'}">
								<td align="left" id="displayName${counter.index}" width="130">${rnparameter.displayNameSpanish}:</td>
								</c:if>
								<c:if test="${portalLocale == 'tr_TR'}">
								<td align="left" id="displayName${counter.index}" width="130">${rnparameter.displayNameTurkey}:</td>
								</c:if>
								<c:if test="${portalLocale == 'de_DE'}">
								<td align="left" id="displayName${counter.index}" width="130">${rnparameter.displayNameGerman}:</td>
								</c:if>
								<c:if test="${portalLocale == 'zh_CN'}">
								<td align="left" id="displayName${counter.index}" width="130">${rnparameter.displayNameChina}:</td>
								</c:if>
								<c:if test="${portalLocale == 'zh_TW'}">
								<td align="left" id="displayName${counter.index}" width="130">${rnparameter.displayNameChinaTw}:</td>
								</c:if>
								<td align="left"><spring:message code="customerReports.scheduleOneReport.label.booleanTrue"/>&nbsp;<form:radiobutton id="booleanTrue${counter.index}" path="runNowParameters[${counter.index}].value" value="true"/>
									&nbsp;&nbsp;
									<spring:message code="customerReports.scheduleOneReport.label.booleanFalse"/><form:radiobutton id="booleanFalse${counter.index}" path="runNowParameters[${counter.index}].value" value="false"/>
								</td>
								</c:when>
								<c:when test="${dateType == rnparameter.dataType}">
								<!-- Changes to fetch displayNames based on locale -CI-7 #11656 -->
								<c:if test="${portalLocale == 'en_GB'}">
								<td align="left" id="displayName${counter.index}" width="130">${rnparameter.displayName}:</td>
								</c:if>
								<c:if test="${portalLocale == 'en_US'}">
								<td align="left" id="displayName${counter.index}" width="130">${rnparameter.displayName}:</td>
								</c:if>
								<c:if test="${portalLocale == 'fr_FR'}">
								<td align="left" id="displayName${counter.index}" width="130">${rnparameter.displayNameFrench}:</td>
								</c:if>
								<c:if test="${portalLocale == 'it_IT'}">
								<td align="left" id="displayName${counter.index}" width="130">${rnparameter.displayNameItaly}:</td>
								</c:if>
								<c:if test="${portalLocale == 'ja_JP'}">
								<td align="left" id="displayName${counter.index}" width="130">${rnparameter.displayNameJapan}:</td>
								</c:if>
								<c:if test="${portalLocale == 'ko_KR'}">
								<td align="left" id="displayName${counter.index}" width="130">${rnparameter.displayNameKorea}:</td>
								</c:if>
								<c:if test="${portalLocale == 'pt_BR'}">
								<td align="left" id="displayName${counter.index}" width="130">${rnparameter.displayNamePortugalBrazil}:</td>
								</c:if>
								<c:if test="${portalLocale == 'pt_PT'}">
								<td align="left" id="displayName${counter.index}" width="130">${rnparameter.displayNamePortugal}:</td>
								</c:if>
								<c:if test="${portalLocale == 'ru_RU'}">
								<td align="left" id="displayName${counter.index}" width="130">${rnparameter.displayNameRussia}:</td>
								</c:if>
								<c:if test="${portalLocale == 'es_ES'}">
								<td align="left" id="displayName${counter.index}" width="130">${rnparameter.displayNameSpanish}:</td>
								</c:if>
								<c:if test="${portalLocale == 'tr_TR'}">
								<td align="left" id="displayName${counter.index}" width="130">${rnparameter.displayNameTurkey}:</td>
								</c:if>
								<c:if test="${portalLocale == 'de_DE'}">
								<td align="left" id="displayName${counter.index}" width="130">${rnparameter.displayNameGerman}:</td>
								</c:if>
								<c:if test="${portalLocale == 'zh_CN'}">
								<td align="left" id="displayName${counter.index}" width="130">${rnparameter.displayNameChina}:</td>
								</c:if>
								<c:if test="${portalLocale == 'zh_TW'}">
								<td align="left" id="displayName${counter.index}" width="130">${rnparameter.displayNameChinaTw}:</td>
								</c:if>
								<td align="left">
								    <a id="prevLocalizedDateValue${counter.index}" onClick="moveDate('localizedDateValue${counter.index}', -1);" class="validDateAction" title="Previous Day"><<</a>&nbsp;
								    <form:hidden id="dateValue${counter.index}" path="runNowParameters[${counter.index}].value" class="reportDates"/>
								    <input type="text" id="localizedDateValue${counter.index}" size="7" onMouseUp="show_cal('localizedDateValue${counter.index}', null, null);" readOnly="readOnly"/>
								    <img id="imgLocalizedDateValue${counter.index}" align="top"  class="ui_icon_sprite calendar-icon" src="<html:imagesPath/>transparent.png" onClick="show_cal('localizedDateValue${counter.index}', null, null)"/>&nbsp;
								    <a id="nextLocalizedDateValue${counter.index}" onClick="moveDate('localizedDateValue${counter.index}', 1);" class="validDateAction" title="Next Day">>></a>&nbsp;
								</td>
								</c:when>
								<c:when test="${listType == rnparameter.dataType}">
								<!-- Changes to fetch displayNames based on locale -CI-7 #11656 -->
								<c:if test="${portalLocale == 'en_GB'}">
								<td align="left" id="displayName${counter.index}" width="130">${rnparameter.displayName}:</td>
								</c:if>
								<c:if test="${portalLocale == 'en_US'}">
								<td align="left" id="displayName${counter.index}" width="130">${rnparameter.displayName}:</td>
								</c:if>
								<c:if test="${portalLocale == 'fr_FR'}">
								<td align="left" id="displayName${counter.index}" width="130">${rnparameter.displayNameFrench}:</td>
								</c:if>
								<c:if test="${portalLocale == 'it_IT'}">
								<td align="left" id="displayName${counter.index}" width="130">${rnparameter.displayNameItaly}:</td>
								</c:if>
								<c:if test="${portalLocale == 'ja_JP'}">
								<td align="left" id="displayName${counter.index}" width="130">${rnparameter.displayNameJapan}:</td>
								</c:if>
								<c:if test="${portalLocale == 'ko_KR'}">
								<td align="left" id="displayName${counter.index}" width="130">${rnparameter.displayNameKorea}:</td>
								</c:if>
								<c:if test="${portalLocale == 'pt_BR'}">
								<td align="left" id="displayName${counter.index}" width="130">${rnparameter.displayNamePortugalBrazil}:</td>
								</c:if>
								<c:if test="${portalLocale == 'pt_PT'}">
								<td align="left" id="displayName${counter.index}" width="130">${rnparameter.displayNamePortugal}:</td>
								</c:if>
								<c:if test="${portalLocale == 'ru_RU'}">
								<td align="left" id="displayName${counter.index}" width="130">${rnparameter.displayNameRussia}:</td>
								</c:if>
								<c:if test="${portalLocale == 'es_ES'}">
								<td align="left" id="displayName${counter.index}" width="130">${rnparameter.displayNameSpanish}:</td>
								</c:if>
								<c:if test="${portalLocale == 'tr_TR'}">
								<td align="left" id="displayName${counter.index}" width="130">${rnparameter.displayNameTurkey}:</td>
								</c:if>
								<c:if test="${portalLocale == 'de_DE'}">
								<td align="left" id="displayName${counter.index}" width="130">${rnparameter.displayNameGerman}:</td>
								</c:if>
								<c:if test="${portalLocale == 'zh_CN'}">
								<td align="left" id="displayName${counter.index}" width="130">${rnparameter.displayNameChina}:</td>
								</c:if>
								<c:if test="${portalLocale == 'zh_TW'}">
								<td align="left" id="displayName${counter.index}" width="130">${rnparameter.displayNameChinaTw}:</td>
								</c:if>
								<td align="left">
									&nbsp;
									<form:select id="listValue${counter.index}" path="runNowParameters[${counter.index}].value">
										<form:option value="" label=""/>
										<form:options items="${rnparameter.listValuesList}" itemValue="value" itemLabel="label"/>
									</form:select>
								</td>
								</c:when>
								<c:when test="${numericType == rnparameter.dataType}">
								<!-- Changes to fetch displayNames based on locale -CI-7 #11656 -->
								<c:if test="${portalLocale == 'en_GB'}">
								<td align="left" id="displayName${counter.index}" width="130">${rnparameter.displayName}:</td>
								</c:if>
								<c:if test="${portalLocale == 'en_US'}">
								<td align="left" id="displayName${counter.index}" width="130">${rnparameter.displayName}:</td>
								</c:if>
								<c:if test="${portalLocale == 'fr_FR'}">
								<td align="left" id="displayName${counter.index}" width="130">${rnparameter.displayNameFrench}:</td>
								</c:if>
								<c:if test="${portalLocale == 'it_IT'}">
								<td align="left" id="displayName${counter.index}" width="130">${rnparameter.displayNameItaly}:</td>
								</c:if>
								<c:if test="${portalLocale == 'ja_JP'}">
								<td align="left" id="displayName${counter.index}" width="130">${rnparameter.displayNameJapan}:</td>
								</c:if>
								<c:if test="${portalLocale == 'ko_KR'}">
								<td align="left" id="displayName${counter.index}" width="130">${rnparameter.displayNameKorea}:</td>
								</c:if>
								<c:if test="${portalLocale == 'pt_BR'}">
								<td align="left" id="displayName${counter.index}" width="130">${rnparameter.displayNamePortugalBrazil}:</td>
								</c:if>
								<c:if test="${portalLocale == 'pt_PT'}">
								<td align="left" id="displayName${counter.index}" width="130">${rnparameter.displayNamePortugal}:</td>
								</c:if>
								<c:if test="${portalLocale == 'ru_RU'}">
								<td align="left" id="displayName${counter.index}" width="130">${rnparameter.displayNameRussia}:</td>
								</c:if>
								<c:if test="${portalLocale == 'es_ES'}">
								<td align="left" id="displayName${counter.index}" width="130">${rnparameter.displayNameSpanish}:</td>
								</c:if>
								<c:if test="${portalLocale == 'tr_TR'}">
								<td align="left" id="displayName${counter.index}" width="130">${rnparameter.displayNameTurkey}:</td>
								</c:if>
								<c:if test="${portalLocale == 'de_DE'}">
								<td align="left" id="displayName${counter.index}" width="130">${rnparameter.displayNameGerman}:</td>
								</c:if>
								<c:if test="${portalLocale == 'zh_CN'}">
								<td align="left" id="displayName${counter.index}" width="130">${rnparameter.displayNameChina}:</td>
								</c:if>
								<c:if test="${portalLocale == 'zh_TW'}">
								<td align="left" id="displayName${counter.index}" width="130">${rnparameter.displayNameChinaTw}:</td>
								</c:if>
								<td align="left"><form:input id="numericValue${counter.index}" path="runNowParameters[${counter.index}].value" maxlength="100"/></td>
								</c:when>
								<c:when test="${stringType == rnparameter.dataType}">
								<!-- Changes to fetch displayNames based on locale -CI-7 #11656 -->
								<c:if test="${portalLocale == 'en_GB'}">
								<td align="left" id="displayName${counter.index}" width="130">${rnparameter.displayName}:</td>
								</c:if>
								<c:if test="${portalLocale == 'en_US'}">
								<td align="left" id="displayName${counter.index}" width="130">${rnparameter.displayName}:</td>
								</c:if>
								<c:if test="${portalLocale == 'fr_FR'}">
								<td align="left" id="displayName${counter.index}" width="130">${rnparameter.displayNameFrench}:</td>
								</c:if>
								<c:if test="${portalLocale == 'it_IT'}">
								<td align="left" id="displayName${counter.index}" width="130">${rnparameter.displayNameItaly}:</td>
								</c:if>
								<c:if test="${portalLocale == 'ja_JP'}">
								<td align="left" id="displayName${counter.index}" width="130">${rnparameter.displayNameJapan}:</td>
								</c:if>
								<c:if test="${portalLocale == 'ko_KR'}">
								<td align="left" id="displayName${counter.index}" width="130">${rnparameter.displayNameKorea}:</td>
								</c:if>
								<c:if test="${portalLocale == 'pt_BR'}">
								<td align="left" id="displayName${counter.index}" width="130">${rnparameter.displayNamePortugalBrazil}:</td>
								</c:if>
								<c:if test="${portalLocale == 'pt_PT'}">
								<td align="left" id="displayName${counter.index}" width="130">${rnparameter.displayNamePortugal}:</td>
								</c:if>
								<c:if test="${portalLocale == 'ru_RU'}">
								<td align="left" id="displayName${counter.index}" width="130">${rnparameter.displayNameRussia}:</td>
								</c:if>
								<c:if test="${portalLocale == 'es_ES'}">
								<td align="left" id="displayName${counter.index}" width="130">${rnparameter.displayNameSpanish}:</td>
								</c:if>
								<c:if test="${portalLocale == 'tr_TR'}">
								<td align="left" id="displayName${counter.index}" width="130">${rnparameter.displayNameTurkey}:</td>
								</c:if>
								<c:if test="${portalLocale == 'de_DE'}">
								<td align="left" id="displayName${counter.index}" width="130">${rnparameter.displayNameGerman}:</td>
								</c:if>
								<c:if test="${portalLocale == 'zh_CN'}">
								<td align="left" id="displayName${counter.index}" width="130">${rnparameter.displayNameChina}:</td>
								</c:if>
								<c:if test="${portalLocale == 'zh_TW'}">
								<td align="left" id="displayName${counter.index}" width="130">${rnparameter.displayNameChinaTw}:</td>
								</c:if>
								<td align="left"><form:input id="stringValue${counter.index}" path="runNowParameters[${counter.index}].value" maxlength="100"/></td>
								</c:when>
								<c:otherwise>
								<td></td>
								</c:otherwise>
							</c:choose>
							</tr>
						</c:forEach>
						</table>
					</td>
				</tr>
			</table>
		</div>
		<% //};%>
		
		<!-- added -->
		<div class="portlet-wrap-inner" id="scheduleParamsDisplay">
			<table>
				<tr>
					<td align="left">
						<table>
						<c:forEach items="${reportScheduleForm.scheduleParameters}" var="sparameter" varStatus="counter" begin="0">
							<form:hidden path="scheduleParameters[${counter.index}].reportParameterId"/>
							<form:hidden path="scheduleParameters[${counter.index}].name"/>
							<form:hidden path="scheduleParameters[${counter.index}].dataType"/>
                                     <form:hidden path="scheduleParameters[${counter.index}].isRequired"/>
                            <tr><td colspan="4">&nbsp;</td></tr>
							<tr>
							<c:choose>
								<c:when test="${sparameter.isRequired}">
								<td id='srequiredTd${counter.index}' align="right">*</td>
								<td id='smaxLengthTd${counter.index}' style="display:none">${sparameter.maxSize}</td>
								</c:when>
								<c:otherwise>
								<td id='srequiredTd${counter.index}'></td>
								<td id='smaxLengthTd${counter.index}' style="display:none">${sparameter.maxSize}</td>
								</c:otherwise>
							</c:choose>
							<c:choose>
								<c:when test="${booleanType == sparameter.dataType}">
								<!-- Changes to fetch displayNames based on locale -CI-7 #11656 -->
								<c:if test="${portalLocale == 'en_GB'}">
								<td align="left" id="sdisplayName${counter.index}" width="130">${sparameter.displayName}:</td>
								</c:if>
								<c:if test="${portalLocale == 'en_US'}">
								<td align="left" id="sdisplayName${counter.index}" width="130">${sparameter.displayName}:</td>
								</c:if>
								<c:if test="${portalLocale == 'fr_FR'}">
								<td align="left" id="sdisplayName${counter.index}" width="130">${sparameter.displayNameFrench}:</td>
								</c:if>
								<c:if test="${portalLocale == 'it_IT'}">
								<td align="left" id="sdisplayName${counter.index}" width="130">${sparameter.displayNameItaly}:</td>
								</c:if>
								<c:if test="${portalLocale == 'ja_JP'}">
								<td align="left" id="sdisplayName${counter.index}" width="130">${sparameter.displayNameJapan}:</td>
								</c:if>
								<c:if test="${portalLocale == 'ko_KR'}">
								<td align="left" id="sdisplayName${counter.index}" width="130">${sparameter.displayNameKorea}:</td>
								</c:if>
								<c:if test="${portalLocale == 'pt_BR'}">
								<td align="left" id="sdisplayName${counter.index}" width="130">${sparameter.displayNamePortugalBrazil}:</td>
								</c:if>
								<c:if test="${portalLocale == 'pt_PT'}">
								<td align="left" id="sdisplayName${counter.index}" width="130">${sparameter.displayNamePortugal}:</td>
								</c:if>
								<c:if test="${portalLocale == 'ru_RU'}">
								<td align="left" id="sdisplayName${counter.index}" width="130">${sparameter.displayNameRussia}:</td>
								</c:if>
								<c:if test="${portalLocale == 'es_ES'}">
								<td align="left" id="sdisplayName${counter.index}" width="130">${sparameter.displayNameSpanish}:</td>
								</c:if>
								<c:if test="${portalLocale == 'tr_TR'}">
								<td align="left" id="sdisplayName${counter.index}" width="130">${sparameter.displayNameTurkey}:</td>
								</c:if>
								<c:if test="${portalLocale == 'de_DE'}">
								<td align="left" id="sdisplayName${counter.index}" width="130">${sparameter.displayNameGerman}:</td>
								</c:if>
								<c:if test="${portalLocale == 'zh_CN'}">
								<td align="left" id="sdisplayName${counter.index}" width="130">${sparameter.displayNameChina}:</td>
								</c:if>
								<c:if test="${portalLocale == 'zh_TW'}">
								<td align="left" id="sdisplayName${counter.index}" width="130">${sparameter.displayNameChinaTw}:</td>
								</c:if>
								<td align="left"><spring:message code="customerReports.scheduleOneReport.label.booleanTrue"/>&nbsp;<form:radiobutton id="sbooleanTrue${counter.index}" path="scheduleParameters[${counter.index}].value" value="true"/>
									&nbsp;&nbsp;
									<spring:message code="customerReports.scheduleOneReport.label.booleanFalse"/><form:radiobutton id="sbooleanFalse${counter.index}" path="scheduleParameters[${counter.index}].value" value="false"/>
								</td>
								</c:when>
								<c:when test="${dateType == sparameter.dataType}">
								<!-- Changes to fetch displayNames based on locale -CI-7 #11656 -->
								<c:if test="${portalLocale == 'en_GB'}">
								<td align="left" id="sdisplayName${counter.index}" width="130">${sparameter.displayName}:</td>
								</c:if>
								<c:if test="${portalLocale == 'en_US'}">
								<td align="left" id="sdisplayName${counter.index}" width="130">${sparameter.displayName}:</td>
								</c:if>
								<c:if test="${portalLocale == 'fr_FR'}">
								<td align="left" id="sdisplayName${counter.index}" width="130">${sparameter.displayNameFrench}:</td>
								</c:if>
								<c:if test="${portalLocale == 'it_IT'}">
								<td align="left" id="sdisplayName${counter.index}" width="130">${sparameter.displayNameItaly}:</td>
								</c:if>
								<c:if test="${portalLocale == 'ja_JP'}">
								<td align="left" id="sdisplayName${counter.index}" width="130">${sparameter.displayNameJapan}:</td>
								</c:if>
								<c:if test="${portalLocale == 'ko_KR'}">
								<td align="left" id="sdisplayName${counter.index}" width="130">${sparameter.displayNameKorea}:</td>
								</c:if>
								<c:if test="${portalLocale == 'pt_BR'}">
								<td align="left" id="sdisplayName${counter.index}" width="130">${sparameter.displayNamePortugalBrazil}:</td>
								</c:if>
								<c:if test="${portalLocale == 'pt_PT'}">
								<td align="left" id="sdisplayName${counter.index}" width="130">${sparameter.displayNamePortugal}:</td>
								</c:if>
								<c:if test="${portalLocale == 'ru_RU'}">
								<td align="left" id="sdisplayName${counter.index}" width="130">${sparameter.displayNameRussia}:</td>
								</c:if>
								<c:if test="${portalLocale == 'es_ES'}">
								<td align="left" id="sdisplayName${counter.index}" width="130">${sparameter.displayNameSpanish}:</td>
								</c:if>
								<c:if test="${portalLocale == 'tr_TR'}">
								<td align="left" id="sdisplayName${counter.index}" width="130">${sparameter.displayNameTurkey}:</td>
								</c:if>
								<c:if test="${portalLocale == 'de_DE'}">
								<td align="left" id="sdisplayName${counter.index}" width="130">${sparameter.displayNameGerman}:</td>
								</c:if>
								<c:if test="${portalLocale == 'zh_CN'}">
								<td align="left" id="sdisplayName${counter.index}" width="130">${sparameter.displayNameChina}:</td>
								</c:if>
								<c:if test="${portalLocale == 'zh_TW'}">
								<td align="left" id="sdisplayName${counter.index}" width="130">${sparameter.displayNameChinaTw}:</td>
								</c:if>
								<td align="left">
								    <a id="prevLocalizedDateValue${counter.index}" onClick="moveDate('slocalizedDateValue${counter.index}', -1);" class="validDateAction" title="Previous Day"><<</a>&nbsp;
								    <form:hidden id="sdateValue${counter.index}" path="scheduleParameters[${counter.index}].value" class="sreportDates"/>
								    <input type="text" id="slocalizedDateValue${counter.index}" size="7" onMouseUp="show_cal('slocalizedDateValue${counter.index}', null, null);" readOnly="readOnly"/>
								    <img id="imgLocalizedDateValue${counter.index}" align="top"  class="ui_icon_sprite calendar-icon" src="<html:imagesPath/>transparent.png" onClick="show_cal('slocalizedDateValue${counter.index}', null, null)"/>&nbsp;
								    <a id="nextLocalizedDateValue${counter.index}" onClick="moveDate('slocalizedDateValue${counter.index}', 1);" class="validDateAction" title="Next Day">>></a>&nbsp;
								</td>
								</c:when>
								<c:when test="${listType == sparameter.dataType}">
								<!-- Changes to fetch displayNames based on locale -CI-7 #11656 -->
								<c:if test="${portalLocale == 'en_GB'}">
								<td align="left" id="sdisplayName${counter.index}" width="130">${sparameter.displayName}:</td>
								</c:if>
								<c:if test="${portalLocale == 'en_US'}">
								<td align="left" id="sdisplayName${counter.index}" width="130">${sparameter.displayName}:</td>
								</c:if>
								<c:if test="${portalLocale == 'fr_FR'}">
								<td align="left" id="sdisplayName${counter.index}" width="130">${sparameter.displayNameFrench}:</td>
								</c:if>
								<c:if test="${portalLocale == 'it_IT'}">
								<td align="left" id="sdisplayName${counter.index}" width="130">${sparameter.displayNameItaly}:</td>
								</c:if>
								<c:if test="${portalLocale == 'ja_JP'}">
								<td align="left" id="sdisplayName${counter.index}" width="130">${sparameter.displayNameJapan}:</td>
								</c:if>
								<c:if test="${portalLocale == 'ko_KR'}">
								<td align="left" id="sdisplayName${counter.index}" width="130">${sparameter.displayNameKorea}:</td>
								</c:if>
								<c:if test="${portalLocale == 'pt_BR'}">
								<td align="left" id="sdisplayName${counter.index}" width="130">${sparameter.displayNamePortugalBrazil}:</td>
								</c:if>
								<c:if test="${portalLocale == 'pt_PT'}">
								<td align="left" id="sdisplayName${counter.index}" width="130">${sparameter.displayNamePortugal}:</td>
								</c:if>
								<c:if test="${portalLocale == 'ru_RU'}">
								<td align="left" id="sdisplayName${counter.index}" width="130">${sparameter.displayNameRussia}:</td>
								</c:if>
								<c:if test="${portalLocale == 'es_ES'}">
								<td align="left" id="sdisplayName${counter.index}" width="130">${sparameter.displayNameSpanish}:</td>
								</c:if>
								<c:if test="${portalLocale == 'tr_TR'}">
								<td align="left" id="sdisplayName${counter.index}" width="130">${sparameter.displayNameTurkey}:</td>
								</c:if>
								<c:if test="${portalLocale == 'de_DE'}">
								<td align="left" id="sdisplayName${counter.index}" width="130">${sparameter.displayNameGerman}:</td>
								</c:if>
								<c:if test="${portalLocale == 'zh_CN'}">
								<td align="left" id="sdisplayName${counter.index}" width="130">${sparameter.displayNameChina}:</td>
								</c:if>
								<c:if test="${portalLocale == 'zh_TW'}">
								<td align="left" id="sdisplayName${counter.index}" width="130">${sparameter.displayNameChinaTw}:</td>
								</c:if>
								<td align="left">
									&nbsp;
									<form:select id="slistValue${counter.index}" path="scheduleParameters[${counter.index}].value">
										<form:option value="" label=""/>
										<form:options items="${sparameter.listValuesList}" itemValue="value" itemLabel="label"/>
									</form:select>
								</td>
								</c:when>
								<c:when test="${numericType == sparameter.dataType}">
								<!-- Changes to fetch displayNames based on locale -CI-7 #11656 -->
								<c:if test="${portalLocale == 'en_GB'}">
								<td align="left" id="sdisplayName${counter.index}" width="130">${sparameter.displayName}:</td>
								</c:if>
								<c:if test="${portalLocale == 'en_US'}">
								<td align="left" id="sdisplayName${counter.index}" width="130">${sparameter.displayName}:</td>
								</c:if>
								<c:if test="${portalLocale == 'fr_FR'}">
								<td align="left" id="sdisplayName${counter.index}" width="130">${sparameter.displayNameFrench}:</td>
								</c:if>
								<c:if test="${portalLocale == 'it_IT'}">
								<td align="left" id="sdisplayName${counter.index}" width="130">${sparameter.displayNameItaly}:</td>
								</c:if>
								<c:if test="${portalLocale == 'ja_JP'}">
								<td align="left" id="sdisplayName${counter.index}" width="130">${sparameter.displayNameJapan}:</td>
								</c:if>
								<c:if test="${portalLocale == 'ko_KR'}">
								<td align="left" id="sdisplayName${counter.index}" width="130">${sparameter.displayNameKorea}:</td>
								</c:if>
								<c:if test="${portalLocale == 'pt_BR'}">
								<td align="left" id="sdisplayName${counter.index}" width="130">${sparameter.displayNamePortugalBrazil}:</td>
								</c:if>
								<c:if test="${portalLocale == 'pt_PT'}">
								<td align="left" id="sdisplayName${counter.index}" width="130">${sparameter.displayNamePortugal}:</td>
								</c:if>
								<c:if test="${portalLocale == 'ru_RU'}">
								<td align="left" id="sdisplayName${counter.index}" width="130">${sparameter.displayNameRussia}:</td>
								</c:if>
								<c:if test="${portalLocale == 'es_ES'}">
								<td align="left" id="sdisplayName${counter.index}" width="130">${sparameter.displayNameSpanish}:</td>
								</c:if>
								<c:if test="${portalLocale == 'tr_TR'}">
								<td align="left" id="sdisplayName${counter.index}" width="130">${sparameter.displayNameTurkey}:</td>
								</c:if>
								<c:if test="${portalLocale == 'de_DE'}">
								<td align="left" id="sdisplayName${counter.index}" width="130">${sparameter.displayNameGerman}:</td>
								</c:if>
								<c:if test="${portalLocale == 'zh_CN'}">
								<td align="left" id="sdisplayName${counter.index}" width="130">${sparameter.displayNameChina}:</td>
								</c:if>
								<c:if test="${portalLocale == 'zh_TW'}">
								<td align="left" id="sdisplayName${counter.index}" width="130">${sparameter.displayNameChinaTw}:</td>
								</c:if>
								<td align="left"><form:input id="snumericValue${counter.index}" path="scheduleParameters[${counter.index}].value" maxlength="100"/></td>
								</c:when>
								<c:when test="${stringType == sparameter.dataType}">
								<c:if test="${portalLocale == 'en_GB'}">
								<td align="left" id="sdisplayName${counter.index}" width="130">${sparameter.displayName}:</td>
								</c:if>
								<c:if test="${portalLocale == 'en_US'}">
								<td align="left" id="sdisplayName${counter.index}" width="130">${sparameter.displayName}:</td>
								</c:if>
								<c:if test="${portalLocale == 'fr_FR'}">
								<td align="left" id="sdisplayName${counter.index}" width="130">${sparameter.displayNameFrench}:</td>
								</c:if>
								<c:if test="${portalLocale == 'it_IT'}">
								<td align="left" id="sdisplayName${counter.index}" width="130">${sparameter.displayNameItaly}:</td>
								</c:if>
								<c:if test="${portalLocale == 'ja_JP'}">
								<td align="left" id="sdisplayName${counter.index}" width="130">${sparameter.displayNameJapan}:</td>
								</c:if>
								<c:if test="${portalLocale == 'ko_KR'}">
								<td align="left" id="sdisplayName${counter.index}" width="130">${sparameter.displayNameKorea}:</td>
								</c:if>
								<c:if test="${portalLocale == 'pt_BR'}">
								<td align="left" id="sdisplayName${counter.index}" width="130">${sparameter.displayNamePortugalBrazil}:</td>
								</c:if>
								<c:if test="${portalLocale == 'pt_PT'}">
								<td align="left" id="sdisplayName${counter.index}" width="130">${sparameter.displayNamePortugal}:</td>
								</c:if>
								<c:if test="${portalLocale == 'ru_RU'}">
								<td align="left" id="sdisplayName${counter.index}" width="130">${sparameter.displayNameRussia}:</td>
								</c:if>
								<c:if test="${portalLocale == 'es_ES'}">
								<td align="left" id="sdisplayName${counter.index}" width="130">${sparameter.displayNameSpanish}:</td>
								</c:if>
								<c:if test="${portalLocale == 'tr_TR'}">
								<td align="left" id="sdisplayName${counter.index}" width="130">${sparameter.displayNameTurkey}:</td>
								</c:if>
								<c:if test="${portalLocale == 'de_DE'}">
								<td align="left" id="sdisplayName${counter.index}" width="130">${sparameter.displayNameGerman}:</td>
								</c:if>
								<c:if test="${portalLocale == 'zh_CN'}">
								<td align="left" id="sdisplayName${counter.index}" width="130">${sparameter.displayNameChina}:</td>
								</c:if>
								<c:if test="${portalLocale == 'zh_TW'}">
								<td align="left" id="sdisplayName${counter.index}" width="130">${sparameter.displayNameChinaTw}:</td>
								</c:if>
								<td align="left"><form:input id="sstringValue${counter.index}" path="scheduleParameters[${counter.index}].value" maxlength="100"/></td>
								</c:when>
								<c:otherwise>
								<td></td>
								</c:otherwise>
							</c:choose>
							</tr>
						</c:forEach>
						</table>
					</td>
				</tr>
			</table>
		</div>
		<!-- end of addition -->
		
		<div class="portlet-footer">
			<div class="portlet-footer-inner"></div>
		</div>
		</div>
			
		<div id='parameterPanel' class="portlet-wrap">
		<div class="portlet-header">
		<table class="displayGrid">
              <thead>
                <tr><th><spring:message code="customerReports.scheduleOneReport.label.scheduleDesc"/></th>
                </tr></thead>
              
            </table>
			
		</div>
		<div class="portlet-wrap-inner">
		
				<div class="schedule-now-cntnr">
	            <div class="schedule-now">
	              <table border="0" cellspacing="0" cellpadding="0">
	                <tr>
	                  <td align="left" valign="middle" >
	                  	<form:checkbox path="runType" id="runNowCheckBox" value="runNow" onclick="clickRunType(this)"/>
	                  </td>
	                  <td align="left" width="130px" class="td-padding2"><spring:message code="customerReports.scheduleOneReport.label.runNow"/>
	                   <img class="helpIcon ui_icon_sprite info-icon"  src="<html:imagesPath/>transparent.png" title="<spring:message code="customerReports.scheduleOneReport.label.runNowNote.tooltip"/>" >
	                  </td>
	                </tr>
	              </table>
	            </div>
	              <%-- 
	            <div class="schedule-now-cntnt"><spring:message code="customerReports.scheduleOneReport.label.runNowNote"/></div>
	            --%>
	            </div>
	            
	            <div class="schedule-later-cntnr">
	              <div class="schedule-later">
	                <table width="40%" border="0" cellpadding="0" cellspacing="0">
	                  <tr>
	                    <td align="left" valign="middle" class="td-padding2">
	                    	<form:checkbox path="runType" id="scheduleCheckBox" value="schedule" onclick="clickRunType(this)"/>
	                    </td>
	                    <td align="left" valign="middle" class="td-padding2">
	                    <spring:message code="customerReports.scheduleOneReport.label.schedule"/>
	                    <img class="helpIcon ui_icon_sprite info-icon"  src="<html:imagesPath/>transparent.png" title="<spring:message code="customerReports.scheduleOneReport.label.scheduleNote.tooltip"/>" >
	                    </td>
	                  </tr>
	                </table>
	              </div>
	               <%-- 
	              <div class="schedule-now-cntnt" style="width: 98.5%;">
	              <spring:message code="customerReports.scheduleOneReport.label.scheduleNote"/>
	              </div>
	            --%>
				<div class="schedule-cntnt">
				<div id="schedulePanel" style="display:none">
					<div class="day-wek-cntnr">
					<table>
						<tr>
							<td align="left">
								<form:radiobutton id="dailyButton" path="scheduleFrequency" value="D" onclick="clickFrequencyButton(this)"/>
								&nbsp;
								<spring:message code="customerReports.scheduleOneReport.label.daily"/>
								&nbsp;&nbsp;&nbsp;
							</td>
							<td align="left">
								<form:radiobutton id="weeklyButton" path="scheduleFrequency" value="W" onclick="clickFrequencyButton(this)"/>
								&nbsp;
								<spring:message code="customerReports.scheduleOneReport.label.weekly"/>
								&nbsp;&nbsp;&nbsp;
							</td>
							<td align="left">
								<form:radiobutton id="monthlyButton" path="scheduleFrequency" value="M" onclick="clickFrequencyButton(this)"/>
								&nbsp;
								<spring:message code="customerReports.scheduleOneReport.label.monthly"/>
								&nbsp;&nbsp;&nbsp;
							</td>
						</tr>
					</table>
					</div>
					
					<br/>
					
					<div id="dailyPanel" style="display:none">
					<div class="day-week-title">
						<img src="<html:imagesPath/>reports-clndr-icon.png" width="23" height="23" alt="Reports Calender" class="icon-calendar1" >
						<spring:message code="customerReports.scheduleOneReport.label.dailySettings"/>
					</div>
					<div class="div-style36">
						<table width="90%">
							<tr>
								<td align="left"><spring:message code="customerReports.scheduleOneReport.label.everyNDays"/></td>
							</tr>
							<tr>
								<td align="left"><form:input id="runIntervalDaily" size="5" path="runIntervalDaily" maxlength="5"/><br/></td>
							</tr>
							<tr>
								<td>&nbsp;</td>
							</tr>
							<tr>
								<td>
									<table width="100%">
										<tr>
											<td  width="220" align="left">
												<spring:message code="customerReports.scheduleOneReport.label.dayStart"/>
											</td>
											<td  width="220" align="left">
												<input id="daily_expirationDate_checkbox" type="checkbox"/>
												&nbsp;
												<spring:message code="customerReports.scheduleOneReport.label.dayEnd"/>
											</td>
										</tr>
										<tr>
											<td width="220" align="left">
                                                <a id="prevDailyEffectiveDate" onClick="moveDate('localizedDailyEffectiveDate', -1);" class="validDateAction" title="Previous Day">&lt;&lt;</a>&nbsp;
                                                <form:hidden id="dailyEffectiveDate" path="effectiveDateDaily"/>
                                                <input type="text" id="localizedDailyEffectiveDate" size="7" onMouseUp="show_cal('localizedDailyEffectiveDate', todayStr, null);" readOnly="readOnly"/>
                                                <img id="imgLocalizedDailyEffectiveDate" align="top" class="ui_icon_sprite calendar-icon" src="<html:imagesPath/>transparent.png" onClick="show_cal('localizedDailyEffectiveDate', todayStr, null)"/>&nbsp;
                                                <a id="nextLocalizedDailyEffectiveDate" onClick="moveDate('localizedDailyEffectiveDate', 1);" class="validDateAction" title="Next Day">&gt;&gt;</a>&nbsp;
											</td>
											<td width="220" align="left">
                                                         <a id="prevDailyExpirationDate" onClick="moveDate('localizedDailyExpirationDate', -1);" class="validDateAction" title="Previous Day">&lt;&lt;</a>&nbsp;
                                                         <form:hidden id="dailyExpirationDate" path="expirationDateDaily"/>
                                                         <input type="text" id="localizedDailyExpirationDate" size="7" onMouseUp="show_cal('localizedDailyExpirationDate', todayStr, null);" readOnly="readOnly"/>
                                                         <img id="imgLocalizedDailyExpirationDate" align="top" class="ui_icon_sprite calendar-icon" src="<html:imagesPath/>transparent.png" onClick="show_cal('localizedDailyExpirationDate', todayStr, null)"/>&nbsp;
                                                         <a id="nextLocalizedDailyExpirationDate" onClick="moveDate('localizedDailyExpirationDate', 1);" class="validDateAction" title="Next Day">&gt;&gt;</a>&nbsp;
											</td>
										</tr>
									</table>
								</td>
							</tr>
						</table>
					</div>
					</div>
					
					
					<div id="weeklyPanel" style="display:none">
					<div class="day-week-title">
						<img src="<html:imagesPath/>reports-clndr-icon.png" width="23" height="23" alt="Reports Calender" class="icon-calendar1" >
						<spring:message code="customerReports.scheduleOneReport.label.weeklySettings"/>
					</div>
					<div class="div-style36">
						<table width="90%">
							<tr style="display:none">
 								<td align="left"><spring:message code="customerReports.scheduleOneReport.label.everyNWeeks"/></td>
							</tr>
							<tr style="display:none">
								<td align="left"><form:input id="runIntervalWeekly" size="5" path="runIntervalWeekly" maxlength="5"/><br/><br/></td>
							</tr>
							<tr>
								<td align="left"><spring:message code="customerReports.scheduleOneReport.label.specificDayInWeek"/></td>
							</tr>
							<tr>
								<td align="left">
									<form:select path="specificDayInWeek">
										<option value=""></option>
										<c:forEach items="${reportScheduleForm.weekDays}" var="weekDay">
											<form:option value="${weekDay.dayNumber}" label="${weekDay.dayName}"/>
										</c:forEach>
									</form:select>
									<br/>
									<br/>
								</td>
							</tr>
							<tr>
								<td align="left">
									<table width="100%">
										<tr>
											<td width="220" align="left">
												<spring:message code="customerReports.scheduleOneReport.label.dayStart"/>
											</td>
											<td width="220" align="left">
												<input id="weekly_expirationDate_checkbox" type="checkbox"/>
												&nbsp;
												<spring:message code="customerReports.scheduleOneReport.label.dayEnd"/>
											</td>
										</tr>
										<tr>
                                                     <td width="220" align="left">
                                                         <a id="prevWeeklyEffectiveDate" onClick="moveDate('localizedWeeklyEffectiveDate', -1);" class="validDateAction" title="Previous Day"><<</a>&nbsp;
                                                         <form:hidden id="weeklyEffectiveDate" path="effectiveDateWeekly"/>
                                                         <input type="text" id="localizedWeeklyEffectiveDate" size="7" onMouseUp="show_cal('localizedWeeklyEffectiveDate', todayStr, null);" readOnly="readOnly"/>
                                                         <img id="imgLocalizedWeeklyEffectiveDate" align="top" class="ui_icon_sprite calendar-icon" src="<html:imagesPath/>transparent.png" onClick="show_cal('localizedWeeklyEffectiveDate', todayStr, null)"/>&nbsp;
                                                         <a id="nextLocalizedWeeklyEffectiveDate" onClick="moveDate('localizedWeeklyEffectiveDate', 1);" class="validDateAction" title="Next Day">>></a>&nbsp;
                                                     </td>
                                                     <td width="220" align="left">
                                                         <a id="prevWeeklyExpirationDate" onClick="moveDate('localizedWeeklyExpirationDate', -1);" class="validDateAction" title="Previous Day"><<</a>&nbsp;
                                                         <form:hidden id="weeklyExpirationDate" path="expirationDateWeekly"/>
                                                         <input type="text" id="localizedWeeklyExpirationDate" size="7" onMouseUp="show_cal('localizedWeeklyExpirationDate', todayStr, null);" readOnly="readOnly"/>
                                                         <img id="imgLocalizedWeeklyExpirationDate" align="top" class="ui_icon_sprite calendar-icon" src="<html:imagesPath/>transparent.png" onClick="show_cal('localizedWeeklyExpirationDate', todayStr, null)"/>&nbsp;
                                                         <a id="nextLocalizedWeeklyExpirationDate" onClick="moveDate('localizedWeeklyExpirationDate', 1);" class="validDateAction" title="Next Day">>></a>&nbsp;
                                                     </td>
										</tr>
									</table>
								</td>
							</tr>
						</table>
					</div>
					</div>
					
					
					<div id="monthlyPanel" style="display:none">
					<div class="day-week-title">
						<img src="<html:imagesPath/>reports-clndr-icon.png" width="23" height="23" alt="Reports Calender" class="icon-calendar1" >
						<spring:message code="customerReports.scheduleOneReport.label.monthlySettings"/>
					</div>
					<div class="div-style36">
						<table width="90%">
							<tr style="display:none">
								<td align="left"><spring:message code="customerReports.scheduleOneReport.label.everyNMonths"/></td>
							</tr>
							<tr style="display:none">
								<td align="left"><form:input id="runIntervalMonthly" size="5" path="runIntervalMonthly" maxlength="5"/><br/><br/></td>
							</tr>
							
							<tr>
								<td align="left"><spring:message code="customerReports.scheduleOneReport.label.specificDayInMonth"/></td>
							</tr>
							<tr>
								<td align="left">
									<form:select path="specificDayInMonth">
										<form:option id="specificDayInMonth" value=""></form:option>
										<c:forEach varStatus="counter" begin="1" end="31" step="1">
											<form:option id="specificDayInMonth" value="${counter.count}">${counter.count}</form:option>
										</c:forEach>
									</form:select>
									<br/>
									<br/>
								</td>
							</tr>
							<tr>
								<td align="left">
									<table width="100%">
										<tr>
											<td width="220" align="left">
												<spring:message code="customerReports.scheduleOneReport.label.dayStart"/>
											</td>
											<td width="220" align="left">
												<input id="monthly_expirationDate_checkbox" type="checkbox"/>
												&nbsp;
												<spring:message code="customerReports.scheduleOneReport.label.dayEnd"/>
											</td>
										</tr>
										<tr>
                                                     <td width="220" align="left">
                                                         <a id="prevMonthlyEffectiveDate" onClick="moveDate('localizedMonthlyEffectiveDate', -1);" class="validDateAction" title="Previous Day"><<</a>&nbsp;
                                                         <form:hidden id="monthlyEffectiveDate" path="effectiveDateMonthly"/>
                                                         <input type="text" id="localizedMonthlyEffectiveDate" size="7" onMouseUp="show_cal('localizedMonthlyEffectiveDate', todayStr, null);" readOnly="readOnly"/>
                                                         <img id="imgLocalizedMonthlyEffectiveDate" align="top" class="ui_icon_sprite calendar-icon" src="<html:imagesPath/>transparent.png" onClick="show_cal('localizedMonthlyEffectiveDate', todayStr, null)"/>&nbsp;
                                                         <a id="nextLocalizedMonthlyEffectiveDate" onClick="moveDate('localizedMonthlyEffectiveDate', 1);" class="validDateAction" title="Next Day">>></a>&nbsp;
                                                     </td>
                                                     <td width="220" align="left">
                                                         <a id="prevMonthlyExpirationDate" onClick="moveDate('localizedMonthlyExpirationDate', -1);" class="validDateAction" title="Previous Day"><<</a>&nbsp;
                                                         <form:hidden id="monthlyExpirationDate" path="expirationDateMonthly"/>
                                                         <input type="text" id="localizedMonthlyExpirationDate" size="7" onMouseUp="show_cal('localizedMonthlyExpirationDate', todayStr, null);" readOnly="readOnly"/>
                                                         <img id="imgLocalizedMonthlyExpirationDate" align="top" class="ui_icon_sprite calendar-icon" src="<html:imagesPath/>transparent.png" onClick="show_cal('localizedMonthlyExpirationDate', todayStr, null)"/>&nbsp;
                                                         <a id="nextLocalizedMonthlyExpirationDate" onClick="moveDate('localizedMonthlyExpirationDate', 1);" class="validDateAction" title="Next Day">>></a>&nbsp;
                                                     </td>
										</tr>
									</table>
								</td>
							</tr>
						</table>
					</div>
					</div>
					<br/>
					
					<div class="div-style37">
					<div id="divTimezone">
					    <spring:message code="reportAdmin.label.selectTimezone"/>:<br/>
                              <form:select path="timezone">
                                  <form:option value="-12">(GMT -12:00) Eniwetok, Kwajalein</form:option>
                                  <form:option value="-11">(GMT -11:00) Midway Island, Samoa</form:option>
                                  <form:option value="-10">(GMT -10:00) Hawaii</form:option>
                                  <form:option value="-9">(GMT -9:00) Alaska</form:option>
                                  <form:option value="-8">(GMT -8:00) Pacific Time (US &amp; Canada)</form:option>
                                  <form:option value="-7">(GMT -7:00) Mountain Time (US &amp; Canada)</form:option>
                                  <form:option value="-6">(GMT -6:00) Central Time (US &amp; Canada), Mexico City</form:option>
                                  <form:option value="-5">(GMT -5:00) Eastern Time (US &amp; Canada), Bogota, Lima</form:option>
                                  <form:option value="-4">(GMT -4:00) Atlantic Time (Canada), Caracas, La Paz</form:option>
                                  <form:option value="-3">(GMT -3:00) Brazil, Buenos Aires, Georgetown</form:option>
                                  <form:option value="-2">(GMT -2:00) Mid-Atlantic</form:option>
                                  <form:option value="-1">(GMT -1:00 hour) Azores, Cape Verde Islands</form:option>
                                  <form:option value="0">(GMT) Western Europe Time, London, Lisbon, Casablanca</form:option>
                                  <form:option value="1">(GMT +1:00 hour) Brussels, Copenhagen, Madrid, Paris</form:option>
                                  <form:option value="2">(GMT +2:00) Kaliningrad, South Africa</form:option>
                                  <form:option value="3">(GMT +3:00) Baghdad, Riyadh, Moscow, St. Petersburg</form:option>
                                  <form:option value="4">(GMT +4:00) Abu Dhabi, Muscat, Baku, Tbilisi</form:option>
                                  <form:option value="5">(GMT +5:00) Ekaterinburg, Islamabad, Karachi, Tashkent</form:option>
                                  <form:option value="6">(GMT +6:00) Almaty, Dhaka, Colombo</form:option>
                                  <form:option value="7">(GMT +7:00) Bangkok, Hanoi, Jakarta</form:option>
                                  <form:option value="8">(GMT +8:00) Beijing, Perth, Singapore, Hong Kong</form:option>
                                  <form:option value="9">(GMT +9:00) Tokyo, Seoul, Osaka, Sapporo, Yakutsk</form:option>
                                  <form:option value="10">(GMT +10:00) Eastern Australia, Guam, Vladivostok</form:option>
                                  <form:option value="11">(GMT +11:00) Magadan, Solomon Islands, New Caledonia</form:option>
                                  <form:option value="12">(GMT +12:00) Auckland, Wellington, Fiji, Kamchatka</form:option>
                              </form:select>
					</div>
					</div>
				</div>
				</div>
			</div>
			</div>
			<div class="portlet-footer">
				<div class="portlet-footer-inner"></div>
			</div>
		</div>

		<div class="clear-both position-relative">
			<table width="100%">
				<tr>
					<!-- IE8 specific fixes (PIE) -->
					<!--[if IE 8]>
					<link rel="stylesheet" type="text/css" href="/lexmark-services-portal-theme/css/ie8.css" />
					<![endif]-->
					
					<!-- IE7 specific fixes (PIE) -->
					<!--[if IE 7]>
					<link rel="stylesheet" type="text/css" href="/lexmark-services-portal-theme/css/ie7.css" />
					<![endif]-->

					<!-- IE6 specific fixes (DD_Roundies) -->
					<!--[if IE 6]> 
					<link rel="stylesheet" type="text/css" href="/lexmark-services-portal-theme/css/ie6.css" />
					<script type="text/javascript" src="/lexmark-services-portal-theme/javascript/DD_roundies_0.0.2a.js"></script>
					<script type="text/javascript">
						DD_roundies.addRule('.button', '12px');
						DD_roundies.addRule('.button_cancel', '12px');
					</script>
					<![endif]-->
					<td align="right">
						<button class="button_cancel" type="button" onclick="javascript:window.close();" style=""><spring:message code="button.cancel"/></button>
						&nbsp;&nbsp;
						<button class="button" type="button" onclick="doValidate()"><spring:message code="button.submit"/></button>
					</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
				</tr>
			</table>
		</form:form>
	</div>
</div>

<script type="text/javascript">
showToolTip('helpIcon');
	var savedTimezone = '${reportScheduleForm.timezone}';
	if(!savedTimezone){
		var today = new Date();
		var defaultOffset = today.getTimezoneOffset()/60;
		document.getElementById("timezone").value = 0 - defaultOffset;
	}

	var totalSize = <%=parametersLength%>;
	for(var i=0;i<totalSize;i++){
		if(document.getElementById('dateValue' + i)){
		    if (document.getElementById('dateValue' + i).value != '') {
		    	document.getElementById("localizedDateValue" + i).value = localizeDate(document.getElementById("dateValue" + i).value);
		    }
		}
	}
	
//	added 
	var stotalSize = <%=parametersLength%>;
	for(var i=0;i<stotalSize;i++){
		if(document.getElementById('sdateValue' + i)){
		    if (document.getElementById('sdateValue' + i).value != '') {
		    	document.getElementById("slocalizedDateValue" + i).value = localizeDate(document.getElementById("sdateValue" + i).value);
		    }
		}
	}
//	end of addition
    document.getElementById('localizedDailyEffectiveDate').value = localizeDate(todayStr);
  //  document.getElementById('localizedDailyExpirationDate').value = localizeDate(document.getElementById('dailyExpirationDate').value);
    document.getElementById('localizedWeeklyEffectiveDate').value = localizeDate(todayStr);
  //  document.getElementById('localizedWeeklyExpirationDate').value = localizeDate(document.getElementById('weeklyExpirationDate').value);
    document.getElementById('localizedMonthlyEffectiveDate').value = localizeDate(todayStr);
 //   document.getElementById('localizedMonthlyExpirationDate').value = localizeDate(document.getElementById('monthlyExpirationDate').value);
       
	
	var scheduleCheckBox = document.getElementById("scheduleCheckBox");
	if(scheduleCheckBox.checked == true){
		document.getElementById('schedulePanel').style.display = "";
		if(document.getElementById('dailyButton').checked){
			document.getElementById('dailyPanel').style.display = "";
		}
		else
		if(document.getElementById('weeklyButton').checked){
			document.getElementById('weeklyPanel').style.display = "";
		}
		else
		if(document.getElementById('monthlyButton').checked){
			document.getElementById('monthlyPanel').style.display = "";
		}
	}
	
	function doValidate(){
	
		clearMessage();
		
		populateEffectiveDates();
		
//		added
		var runNowCheckBox = document.getElementById('runNowCheckBox');
		var scheduleCheckBox = document.getElementById('scheduleCheckBox');
		
		var requiredValid=false;
		var maxLengthValid=false;
		var numericValid=false;
		var validDates = false;
		
		validDates = validateDates();
		
		differentDate = dateDiff(scheduleReportForm);
		runNowFlag = runNowCheckBox.checked;
		scheduleFlag = scheduleCheckBox.checked;
		if(runNowFlag) {
			
			requiredValid = validateRequired();
			maxLengthValid = validateMaxLength();
			numericValid = validateNumeric();
		}
		else if(scheduleFlag) {
			requiredValid = svalidateRequired();
			maxLengthValid = svalidateMaxLength();
			numericValid = svalidateNumeric();
		}
//		end of addition
	
		/*
		var requiredValid = validateRequired();
		var maxLengthValid = validateMaxLength();
		var numericValid = validateNumeric();
		*/		// commented
		
		var scheduleInfo = validateScheduleInfo();
		
		 if(requiredValid && maxLengthValid && numericValid && scheduleInfo && differentDate && validDates)
			 {
			
			 jQuery('#scheduleReportForm').submit();
			 
			 
			 }
		 else
			 return false;
	}

	function validateScheduleInfo(){
		var isValid = true;
		
		var runNowChecked = runNowFlag;
		
		var scheduleChecked = scheduleFlag;
		
		if(!runNowChecked && !scheduleChecked){
			isValid = false;
			showError("<spring:message code='customerReports.scheduleReport.runNowOrSchedule'/>" ,null, true);
			
		}
		if(scheduleChecked){
			
			if(!document.getElementById('dailyButton').checked &&
					   !document.getElementById('weeklyButton').checked	&&
					   !document.getElementById('monthlyButton').checked){
						isValid = false;
						showError("<spring:message code='customerReports.scheduleReport.dOrWorM'/>" ,null, true);
			}
			else
			if(document.getElementById('dailyButton').checked){
				if(document.getElementById('runIntervalDaily').value == ''){
					isValid = false;
					showError("<spring:message code='customerReports.scheduleReport.intervalRequired'/>" ,null, true);
				}
				else{
					var pattern = /^ *[0-9]+ *$/;
					if(!pattern.exec(document.getElementById('runIntervalDaily').value)){
						isValid = false;
						showError("<spring:message code='customerReports.scheduleReport.intervalInteger'/>" ,null, true);
					} 				
				}
				if(document.getElementById('localizedDailyEffectiveDate').value == ''){
					isValid = false;
					showError("<spring:message code='customerReports.scheduleReport.effectiveDateRequired'/>" ,null, true);					
				} else {
					document.getElementById('dailyEffectiveDate').value = formatDateToDefault(document.getElementById('localizedDailyEffectiveDate').value);
				}
				if(document.getElementById('daily_expirationDate_checkbox').checked){
					if(document.getElementById('localizedDailyExpirationDate').value == ''){
						isValid = false;
						showError("<spring:message code='customerReports.scheduleReport.expirationDateRequired'/>" ,null, true);					
					} else {
	                    document.getElementById('dailyExpirationDate').value = formatDateToDefault(document.getElementById('localizedDailyExpirationDate').value);
	                }
				}
			}
			else
			if(document.getElementById('weeklyButton').checked){
                /*if(document.getElementById('runIntervalWeekly').value == ''){
                    isValid = false;
                    showError("<spring:message code='customerReports.scheduleReport.intervalRequired'/>" ,null, true);
                }
                else{
                    var pattern = /^ *[0-9]+ *$/;
                    if(!pattern.exec(document.getElementById('runIntervalWeekly').value)){
                        isValid = false;
                        showError("<spring:message code='customerReports.scheduleReport.intervalInteger'/>" ,null, true);
                    }               
                }*/
                if(document.getElementById('localizedWeeklyEffectiveDate').value == ''){
                    isValid = false;
                    showError("<spring:message code='customerReports.scheduleReport.effectiveDateRequired'/>" ,null, true);                 
                } else {
                    document.getElementById('weeklyEffectiveDate').value = formatDateToDefault(document.getElementById('localizedWeeklyEffectiveDate').value);
                }
                if(document.getElementById('weekly_expirationDate_checkbox').checked){
                    if(document.getElementById('localizedWeeklyExpirationDate').value == ''){
                        isValid = false;
                        showError("<spring:message code='customerReports.scheduleReport.expirationDateRequired'/>" ,null, true);                    
                    } else {
                        document.getElementById('weeklyExpirationDate').value = formatDateToDefault(document.getElementById('localizedWeeklyExpirationDate').value);
                    }
                }
            }
			else
			if(document.getElementById('monthlyButton').checked){
                if(document.getElementById('specificDayInMonth').value == ''){
                    isValid = false;
                    showError("<spring:message code='customerReports.scheduleReport.intervalRequired'/>" ,null, true);
                }
                
                if(document.getElementById('localizedMonthlyEffectiveDate').value == ''){
                    isValid = false;
                    showError("<spring:message code='customerReports.scheduleReport.effectiveDateRequired'/>" ,null, true);                 
                } else {
                    document.getElementById('monthlyEffectiveDate').value = formatDateToDefault(document.getElementById('localizedMonthlyEffectiveDate').value);
                }
                if(document.getElementById('monthly_expirationDate_checkbox').checked){
                    if(document.getElementById('localizedMonthlyExpirationDate').value == ''){
                        isValid = false;
                        showError("<spring:message code='customerReports.scheduleReport.expirationDateRequired'/>" ,null, true);                    
                    } else {
                        document.getElementById('monthlyExpirationDate').value = formatDateToDefault(document.getElementById('localizedMonthlyExpirationDate').value);
                    }
                }
            }
		}
		
		return isValid;
	}

	function validateRequired(){
		var totalSize = <%=parametersLength%>;
		var valid = true;
		for(var i=0;i<totalSize;i++){
			var required = document.getElementById('requiredTd' + i).innerHTML == "*";
            var booleanTrueInput = document.getElementById('booleanTrue' + i);
            var booleanFalseInput = document.getElementById('booleanFalse' + i);
            var stringInput = document.getElementById('stringValue' + i);
            var numericInput = document.getElementById('numericValue' + i);
            var dateInput = document.getElementById('localizedDateValue' + i);
            var listInput = document.getElementById('listValue' + i);
			if(required){
				if(booleanTrueInput){
					if((booleanTrueInput.checked || booleanFalseInput.checked) == false){
						valid = false;
						var displayName = document.getElementById('displayName' + i).innerHTML;
						displayName = displayName.substring(0, displayName.lastIndexOf(':'));
						showError("<spring:message code='customerReports.scheduleReport.parameterName'/>" + displayName + "<spring:message code='customerReports.scheduleReport.parameterRequired'/>" ,null, true);
					}
				}
				else
				if(stringInput){ 
					if(stringInput.value == ''){
						valid = false;
						var displayName = document.getElementById('displayName' + i).innerHTML;
						displayName = displayName.substring(0, displayName.lastIndexOf(':'));
						showError("<spring:message code='customerReports.scheduleReport.parameterName'/>" + displayName + "<spring:message code='customerReports.scheduleReport.parameterRequired'/>" ,null, true);
					}
				}
				else
				if(numericInput){
					if(numericInput.value == ''){
						valid = false;
						var displayName = document.getElementById('displayName' + i).innerHTML;
						displayName = displayName.substring(0, displayName.lastIndexOf(':'));
						showError("<spring:message code='customerReports.scheduleReport.parameterName'/>" + displayName + "<spring:message code='customerReports.scheduleReport.parameterRequired'/>" ,null, true);
					}
				}
				else
				if(dateInput){ 
					if(dateInput.value == ''){
						valid = false;
						var displayName = document.getElementById('displayName' + i).innerHTML;
						displayName = displayName.substring(0, displayName.lastIndexOf(':'));
						showError("<spring:message code='customerReports.scheduleReport.parameterName'/>" + displayName + "<spring:message code='customerReports.scheduleReport.parameterRequired'/>" ,null, true);
					}
				}
				else
				if(listInput){
					if(listInput.value == ''){
						valid = false;
						var displayName = document.getElementById('displayName' + i).innerHTML;
						displayName = displayName.substring(0, displayName.lastIndexOf(':'));
						showError("<spring:message code='customerReports.scheduleReport.parameterName'/>" + displayName + "<spring:message code='customerReports.scheduleReport.parameterRequired'/>" ,null, true);
					}
				}
			}

			// fill in data value to parameter
			if (dateInput) {
				document.getElementById('dateValue' + i).value = formatDateToDefault(document.getElementById('localizedDateValue' + i).value);
			}
		}
		return valid;
	}
	
//	added
	function svalidateRequired(){
		var totalSize = <%=parametersLength%>;
		var valid = true;
		for(var i=0;i<totalSize;i++){
			var required = document.getElementById('srequiredTd' + i).innerHTML == "*";
            var booleanTrueInput = document.getElementById('sbooleanTrue' + i);
            var booleanFalseInput = document.getElementById('sbooleanFalse' + i);
            var stringInput = document.getElementById('sstringValue' + i);
            var numericInput = document.getElementById('snumericValue' + i);
            var dateInput = document.getElementById('slocalizedDateValue' + i);
            var listInput = document.getElementById('slistValue' + i);
			if(required){
				if(booleanTrueInput){
					if((booleanTrueInput.checked || booleanFalseInput.checked) == false){
						valid = false;
						var displayName = document.getElementById('sdisplayName' + i).innerHTML;
						displayName = displayName.substring(0, displayName.lastIndexOf(':'));
						showError("<spring:message code='customerReports.scheduleReport.parameterName'/>" + displayName + "<spring:message code='customerReports.scheduleReport.parameterRequired'/>" ,null, true);
					}
				}
				else
				if(stringInput){ 
					if(stringInput.value == ''){
						valid = false;
						var displayName = document.getElementById('sdisplayName' + i).innerHTML;
						displayName = displayName.substring(0, displayName.lastIndexOf(':'));
						showError("<spring:message code='customerReports.scheduleReport.parameterName'/>" + displayName + "<spring:message code='customerReports.scheduleReport.parameterRequired'/>" ,null, true);
					}
				}
				else
				if(numericInput){
					if(numericInput.value == ''){
						valid = false;
						var displayName = document.getElementById('sdisplayName' + i).innerHTML;
						displayName = displayName.substring(0, displayName.lastIndexOf(':'));
						showError("<spring:message code='customerReports.scheduleReport.parameterName'/>" + displayName + "<spring:message code='customerReports.scheduleReport.parameterRequired'/>" ,null, true);
					}
				}
				else
				if(dateInput){ 
					if(dateInput.value == ''){
						valid = false;
						var displayName = document.getElementById('sdisplayName' + i).innerHTML;
						displayName = displayName.substring(0, displayName.lastIndexOf(':'));
						showError("<spring:message code='customerReports.scheduleReport.parameterName'/>" + displayName + "<spring:message code='customerReports.scheduleReport.parameterRequired'/>" ,null, true);
					}
				}
				else
				if(listInput){
					if(listInput.value == ''){
						valid = false;
						var displayName = document.getElementById('sdisplayName' + i).innerHTML;
						displayName = displayName.substring(0, displayName.lastIndexOf(':'));
						showError("<spring:message code='customerReports.scheduleReport.parameterName'/>" + displayName + "<spring:message code='customerReports.scheduleReport.parameterRequired'/>" ,null, true);
					}
				}
			}

			// fill in data value to parameter
			if (dateInput) {
				document.getElementById('sdateValue' + i).value = formatDateToDefault(document.getElementById('slocalizedDateValue' + i).value);
			}
		}
		return valid;
	}
	
//	end of addition

	function validateNumeric(){
		var valid = true;
		var totalSize = <%=parametersLength%>;
		for(var i=0;i<totalSize;i++){
			var numericValue = document.getElementById('numericValue' + i);
			if(numericValue){
				var value = numericValue.value;
				var pattern = /^[-+]?[0-9]*\.?[0-9]+(?:[eE][-+]?[0-9]+)?$/;
				if(value){
					if(!pattern.exec(value)){
						valid = false;
						var displayName = document.getElementById('displayName' + i).innerHTML;
						displayName = displayName.substring(0, displayName.lastIndexOf(':'));
						showError("<spring:message code='customerReports.scheduleReport.parameterName'/>" + displayName + "<spring:message code='customerReports.scheduleReport.parameterNumeric'/>" ,null, true);
					} 
				}
			}
		}
		return valid;
	}
	
//	added
	function svalidateNumeric(){
		var valid = true;
		var totalSize = <%=parametersLength%>;
		for(var i=0;i<totalSize;i++){
			var numericValue = document.getElementById('snumericValue' + i);
			if(numericValue){
				var value = numericValue.value;
				var pattern = /^[-+]?[0-9]*\.?[0-9]+(?:[eE][-+]?[0-9]+)?$/;
				if(value){
					if(!pattern.exec(value)){
						valid = false;
						var displayName = document.getElementById('sdisplayName' + i).innerHTML;
						displayName = displayName.substring(0, displayName.lastIndexOf(':'));
						showError("<spring:message code='customerReports.scheduleReport.parameterName'/>" + displayName + "<spring:message code='customerReports.scheduleReport.parameterNumeric'/>" ,null, true);
					} 
				}
			}
		}
		return valid;
	}
	
//	end of addition

	function validateMaxLength(){
		var valid = true;
		var totalSize = <%=parametersLength%>;
		for(var i=0;i<totalSize;i++){
			var stringInput = document.getElementById('stringValue' + i);
			var numericInput = document.getElementById('numericValue' + i);
			var maxLength = document.getElementById('maxLengthTd' + i).innerHTML;

			if(maxLength && maxLength != 0){
				if(stringInput){
					if(stringInput.value && stringInput.value.length > maxLength){
						valid = false;
						var displayName = document.getElementById('displayName' + i).innerHTML;
						displayName = displayName.substring(0, displayName.lastIndexOf(':'));
						showError("<spring:message code='customerReports.scheduleReport.parameterLength'/>" + displayName + "<spring:message code='customerReports.scheduleReport.parameterLessThan'/>" + " " + maxLength,null, true);
					}
				}
				else
				if(numericInput){
					if(numericInput.value && numericInput.value.length > maxLength){
						valid = false;
						var displayName = document.getElementById('displayName' + i).innerHTML;
						displayName = displayName.substring(0, displayName.lastIndexOf(':'));
						showError("<spring:message code='customerReports.scheduleReport.parameterLength'/>" + displayName + "<spring:message code='customerReports.scheduleReport.parameterLessThan'/>" + " " + maxLength,null, true);
					}					
				}
			}		
		}
		return valid;
	}
	
//	added 
	function svalidateMaxLength(){
		var valid = true;
		var totalSize = <%=parametersLength%>;
		for(var i=0;i<totalSize;i++){
			var stringInput = document.getElementById('sstringValue' + i);
			var numericInput = document.getElementById('snumericValue' + i);
			var maxLength = document.getElementById('smaxLengthTd' + i).innerHTML;

			if(maxLength && maxLength != 0){
				if(stringInput){
					if(stringInput.value && stringInput.value.length > maxLength){
						valid = false;
						var displayName = document.getElementById('sdisplayName' + i).innerHTML;
						displayName = displayName.substring(0, displayName.lastIndexOf(':'));
						showError("<spring:message code='customerReports.scheduleReport.parameterLength'/>" + displayName + "<spring:message code='customerReports.scheduleReport.parameterLessThan'/>" + " " + maxLength,null, true);
					}
				}
				else
				if(numericInput){
					if(numericInput.value && numericInput.value.length > maxLength){
						valid = false;
						var displayName = document.getElementById('sdisplayName' + i).innerHTML;
						displayName = displayName.substring(0, displayName.lastIndexOf(':'));
						showError("<spring:message code='customerReports.scheduleReport.parameterLength'/>" + displayName + "<spring:message code='customerReports.scheduleReport.parameterLessThan'/>" + " " + maxLength,null, true);
					}					
				}
			}		
		}
		return valid;
	}
	
//	end of addition
    var runNowFlag=true;
    var scheduleFlag=false;
	function clickRunType(runTypeCheckBox){
//	added 
	var runNowPanelDisplay = document.getElementById('runNowParamsDisplay');
	var schedulePanelDisplay = document.getElementById('scheduleParamsDisplay');
//	end of addition 
	
	if(runTypeCheckBox && runTypeCheckBox.id == 'runNowCheckBox'){
		var scheduleCheckBox = document.getElementById('scheduleCheckBox');
		scheduleCheckBox.checked = false;
		
		var schedulePanel = document.getElementById('schedulePanel');
		schedulePanel.style.display = "none";
		
//		added
		runNowPanelDisplay.style.display = "";
		schedulePanelDisplay.style.display = "none";
//	    end of addition
		
		clearSchedulePanel();
		if(runTypeCheckBox.checked){
			runNowFlag=true;
			scheduleFlag=false;
			jQuery('#scheduleCheckBox').next('span').removeClass('check_On');
			jQuery('#scheduleCheckBox').next('span').addClass('check_Off');
		}
		else{
			runNowFlag=false;
			scheduleFlag=false;
			
			
			
		}
	}
	else
	if(runTypeCheckBox && runTypeCheckBox.id == 'scheduleCheckBox')
	{
		
		var runNowCheckBox = document.getElementById('runNowCheckBox');
		runNowCheckBox.checked = false;	
		
		var schedulePanel = document.getElementById('schedulePanel');
		schedulePanel.style.display = "";	
		if(!runTypeCheckBox.checked){
			var schedulePanel = document.getElementById('schedulePanel');
			schedulePanel.style.display = "none";	
			
		}
//		added
		runNowPanelDisplay.style.display = "none";
		schedulePanelDisplay.style.display = "";
//s			end of addition
		
		populateSchedulePanelWithData();	
		if(runTypeCheckBox.checked){
			runNowFlag=false;
			scheduleFlag=true;
			jQuery('#runNowCheckBox').next('span').removeClass('check_On');
			jQuery('#runNowCheckBox').next('span').addClass('check_Off');
		}
		else{
			runNowFlag=false;
			scheduleFlag=false;
			
		}
	}
}

	function clearSchedulePanel(){
		document.getElementById('dailyButton').checked = false;
		document.getElementById('weeklyButton').checked = false;
		document.getElementById('monthlyButton').checked = false;
		radioReset();
		document.getElementById('dailyPanel').style.display = 'none';
		document.getElementById('weeklyPanel').style.display = 'none';
		document.getElementById('monthlyPanel').style.display = 'none';

		document.getElementById('runIntervalDaily').value = '';
		document.getElementById('daily_expirationDate_checkbox').checked = false;
		jQuery('#daily_expirationDate_checkbox').trigger('click');
		document.getElementById('dailyEffectiveDate').value = '';
		document.getElementById('dailyExpirationDate').value = '';
        document.getElementById('localizedDailyEffectiveDate').value = '';
        document.getElementById('localizedDailyExpirationDate').value = '';

		document.getElementById('runIntervalWeekly').value = '';
		document.getElementById('specificDayInWeek').value = '';
		document.getElementById('weekly_expirationDate_checkbox').checked = false;
		jQuery('#weekly_expirationDate_checkbox').trigger('click');
		document.getElementById('weeklyEffectiveDate').value = '';
		document.getElementById('weeklyExpirationDate').value = '';
        document.getElementById('localizedWeeklyEffectiveDate').value = '';
        document.getElementById('localizedWeeklyExpirationDate').value = '';

		document.getElementById('runIntervalMonthly').value = '';
		document.getElementById('specificDayInMonth').value = '';
		document.getElementById('monthly_expirationDate_checkbox').checked = false;
		jQuery('#monthly_expirationDate_checkbox').trigger('click');
		document.getElementById('monthlyEffectiveDate').value = '';
		document.getElementById('monthlyExpirationDate').value = '';
        document.getElementById('localizedMonthlyEffectiveDate').value = '';
        document.getElementById('localizedMonthlyExpirationDate').value = '';
	}

	function clearDailyPanel(){
		document.getElementById('runIntervalDaily').value = '';
		document.getElementById('daily_expirationDate_checkbox').checked = false;
		jQuery('#daily_expirationDate_checkbox').trigger('click');
		document.getElementById('dailyEffectiveDate').value = '';
		document.getElementById('dailyExpirationDate').value = '';
        document.getElementById('localizedDailyEffectiveDate').value = '';
        document.getElementById('localizedDailyExpirationDate').value = '';
	}

	function clearWeeklyPanel(){
		document.getElementById('runIntervalWeekly').value = '';
		document.getElementById('specificDayInWeek').value = '';
		document.getElementById('weekly_expirationDate_checkbox').checked = false;
		jQuery('#weekly_expirationDate_checkbox').trigger('click');
		document.getElementById('weeklyEffectiveDate').value = '';
		document.getElementById('weeklyExpirationDate').value = '';
        document.getElementById('localizedWeeklyEffectiveDate').value = '';
        document.getElementById('localizedWeeklyExpirationDate').value = '';
	}

	function clearMonthlyPanel(){
		document.getElementById('runIntervalMonthly').value = '';
		document.getElementById('specificDayInMonth').value = '';
		document.getElementById('monthly_expirationDate_checkbox').checked = false;
		jQuery('#monthly_expirationDate_checkbox').trigger('click');
		document.getElementById('monthlyEffectiveDate').value = '';
		document.getElementById('monthlyExpirationDate').value = '';
        document.getElementById('localizedMonthlyEffectiveDate').value = '';
        document.getElementById('localizedMonthlyExpirationDate').value = '';
	}

	function clickFrequencyButton(frequencyButton){
		
		if(frequencyButton.id == 'dailyButton'){
			document.getElementById('dailyPanel').style.display = '';
			document.getElementById('weeklyPanel').style.display = 'none';
			document.getElementById('monthlyPanel').style.display = 'none';	
			//clearWeeklyPanel();	
			//clearMonthlyPanel();	
		}
		else
		if(frequencyButton.id == 'weeklyButton'){
			document.getElementById('weeklyPanel').style.display = '';
			document.getElementById('dailyPanel').style.display = 'none';
			document.getElementById('monthlyPanel').style.display = 'none';	
			//clearDailyPanel();
			//clearMonthlyPanel();
		}
		else
		if(frequencyButton.id == 'monthlyButton'){
			document.getElementById('monthlyPanel').style.display = '';	
			document.getElementById('dailyPanel').style.display = 'none';
			document.getElementById('weeklyPanel').style.display = 'none';
			//clearDailyPanel();
			//clearWeeklyPanel();	
		}
	}
	
//	added
	function populateSchedulePanelWithData() {
		if(dailyCheckBox) {
			document.getElementById('dailyButton').checked = true;
			document.getElementById('dailyPanel').style.display = '';
			document.getElementById('runIntervalDaily').value = dailyRunInterval;
			document.getElementById('dailyEffectiveDate').value = dailyEffDate;
			document.getElementById('localizedDailyEffectiveDate').value = dailyLocEffDate;
			document.getElementById('dailyExpirationDate').value = dailyExpDate;
			document.getElementById('localizedDailyExpirationDate').value = dailyLocExpDate;
		} else
		if(weeklyCheckBox) {
			document.getElementById('weeklyButton').checked = true;
			document.getElementById('weeklyPanel').style.display = '';
			document.getElementById('runIntervalWeekly').value = weeklyRunInterval;
			document.getElementById('specificDayInWeek').value = specificWeekDay;
			document.getElementById('weeklyEffectiveDate').value = weeklyEffDate;
			document.getElementById('localizedWeeklyEffectiveDate').value = weeklyLocEffDate;
			document.getElementById('weeklyExpirationDate').value = weeklyExpDate;
			document.getElementById('localizedWeeklyExpirationDate').value = weeklyLocExpDate;
		} else
		if(monthlyCheckBox) {
			document.getElementById('monthlyButton').checked = true;
			document.getElementById('monthlyPanel').style.display = '';	
			document.getElementById('runIntervalMonthly').value = monthlyRunInterval;
			document.getElementById('specificDayInMonth').value = specificMonthDay;
			document.getElementById('monthlyEffectiveDate').value = monthlyEffDate;
			document.getElementById('localizedMonthlyEffectiveDate').value = monthlyLocEffDate;
			document.getElementById('monthlyExpirationDate').value = monthlyExpDate;
			document.getElementById('localizedMonthlyExpirationDate').value = monthlyLocExpDate;
		}
	}
	
	var runNowCheckBox = document.getElementById('runNowCheckBox').checked;
	var scheduledCheckBox = document.getElementById('scheduleCheckBox').checked;
	
	var runNowPanelDisplay = document.getElementById('runNowParamsDisplay');
	var schedulePanelDisplay = document.getElementById('scheduleParamsDisplay');
	
	var dailyCheckBox = document.getElementById('dailyButton').checked;
	var weeklyCheckBox = document.getElementById('weeklyButton').checked;
	var monthlyCheckBox = document.getElementById('monthlyButton').checked;
	
	var dailyExpDateChk = document.getElementById('daily_expirationDate_checkbox').checked;
	var weeklyExpDateChk = document.getElementById('weekly_expirationDate_checkbox').checked;
	var monthlyExpDateChk = document.getElementById('monthly_expirationDate_checkbox').checked;
	
	var dailyRunInterval = document.getElementById('runIntervalDaily').value;
	var dailyEffDate = document.getElementById('dailyEffectiveDate').value;
	var dailyExpDate = document.getElementById('dailyExpirationDate').value;
	var dailyLocEffDate = document.getElementById('localizedDailyEffectiveDate').value;
	var dailyLocExpDate = document.getElementById('localizedDailyExpirationDate').value;

	var weeklyRunInterval = document.getElementById('runIntervalWeekly').value;
	var specificWeekDay = document.getElementById('specificDayInWeek').value;
	var weeklyEffDate = document.getElementById('weeklyEffectiveDate').value;
	var weeklyExpDate = document.getElementById('weeklyExpirationDate').value;
	var weeklyLocEffDate = document.getElementById('localizedWeeklyEffectiveDate').value;
	var weeklyLocExpDate = document.getElementById('localizedWeeklyExpirationDate').value;

	var monthlyRunInterval = document.getElementById('runIntervalMonthly').value;
	var specificMonthDay = document.getElementById('specificDayInMonth').value;
	var monthlyEffDate = document.getElementById('monthlyEffectiveDate').value;
	var monthlyExpDate = document.getElementById('monthlyExpirationDate').value;
	var monthlyLocEffDate = document.getElementById('localizedMonthlyEffectiveDate').value;
	var monthlyLocExpDate = document.getElementById('localizedMonthlyExpirationDate').value;
	
	function showParamsBlock() {

		if(runNowCheckBox) {
			runNowPanelDisplay.style.display = "";
			schedulePanelDisplay.style.display = "none";
		}
		if(scheduledCheckBox) {
			runNowPanelDisplay.style.display = "none";
			schedulePanelDisplay.style.display = "";
		}
	}
//	end of addition

function showToolTip(clName){
	jQuery('.'+clName).qtip({
		style: {
			name: 'green',
			tip: true,
			width: 180,
			//background: '#A2D959',
			color: '#000000',
      		textAlign: 'left',
			border: {
				//color: '#6699CC',
				width: 1,
				radius: 5
			}
		},
		position: {
			corner: {
				target: 'rightMiddle',
				tooltip: 'leftBottom'
			},
			adjust: {
				screen: true,
				mouse: true
			}
		}
	});
}
	
function populateEffectiveDates(){
	document.getElementById('dailyEffectiveDate').value = document.getElementById('localizedDailyEffectiveDate').value;
	document.getElementById('dailyExpirationDate').value = document.getElementById('localizedDailyExpirationDate').value;
	
	document.getElementById('weeklyEffectiveDate').value = document.getElementById('localizedWeeklyEffectiveDate').value;
	document.getElementById('weeklyExpirationDate').value = document.getElementById('localizedWeeklyExpirationDate').value;
	
	document.getElementById('monthlyEffectiveDate').value = document.getElementById('localizedMonthlyEffectiveDate').value;
	document.getElementById('monthlyExpirationDate').value = document.getElementById('localizedMonthlyExpirationDate').value;
}

//Functiion for calculating diff in days.
function dateDiff(scheduleReportForm) {
		date1 = new Date();
		date2 = new Date();
		diff  = new Date();
	
		date1temp = new Date(scheduleReportForm.localizedDailyEffectiveDate.value);
		date1.setTime(date1temp.getTime());
	
		date2temp = new Date(scheduleReportForm.localizedDailyExpirationDate.value);
		date2.setTime(date2temp.getTime());
		diff.setTime(Math.abs(date1.getTime() - date2.getTime()));
	
		timediff = diff.getTime();
		days = Math.floor(timediff / (1000 * 60 * 60 * 24)); 
		//scheduleReportForm.ntimes.value = days;
		ndays = scheduleReportForm.runIntervalDaily.value;
		
	   if(ndays > days || ndays > 3001){
			showError('<spring:message code="customerReports.scheduleReport.appropriateLength"/>');
			return false;
			//alert("returning false");
		}
		else{
			return true;
		}
}


    
	function validateDates() {
		var startDateStr = "";
		var endDateStr = "";
		if (jQuery("#runNowParamsDisplay").css('display') != 'none') {
			var runNowparams = <%=parametersLength%>;
			jQuery(".reportDates").each(function(){
				var name = jQuery(this).attr("name");
				var dateValue = jQuery(this).val();
				
				var paramName = name.split('.')[0]+".name"; 
				var datetype = jQuery("input[name~='"+paramName+"']").val();
				if(datetype.toLowerCase().search("start") != -1  ){
					startDateStr = dateValue;
				}else if(datetype.toLowerCase().search("end") != -1  ){
					endDateStr = dateValue;
				}
			});
			
		}else if(jQuery("#scheduleParamsDisplay").css('display') != 'none'){
			var schParams = <%=parametersLength%>;
			jQuery(".sreportDates").each(function(){
				var name = jQuery(this).attr("name");
				var dateValue = jQuery(this).val();
				var paramName = name.split('.')[0]+".name";
				var datetype = jQuery("input[name~='"+paramName+"']").val();
				if(datetype.toLowerCase().search("start") != -1  ){
					
					startDateStr = dateValue;
				}else if(datetype.toLowerCase().search("end") != -1  ){
					
					endDateStr = dateValue;
				}
			});
		}
		var startDate = new Date(startDateStr);
		var endDate = new Date(endDateStr);

		if(startDate > endDate){
			showError("<spring:message code='customerReports.scheduleReport.dateValidation'/>" ,null, true);
			return false;
		}else{
			return true;
		}
	}
</script>
<script type="text/javascript">
//---- Ominture script 
     window.opener.addPortlet("Schedule report");
	 window.onload = showParamsBlock();  		// added
</script>