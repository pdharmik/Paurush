<%@ include file="/WEB-INF/jsp/include.jsp"%>
<%@ include file="/WEB-INF/jsp/includeGrid.jsp"%>
<%@ include file="/WEB-INF/jsp/includeDatePicker.jsp"%>
<script type="text/javascript" src='<html:rootPath/>js/validation.js'></script>
<style type="text/css"><%@ include file="/WEB-INF/css/ci6.css" %></style>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/mps.css"/>
<!--[if IE 7]>
	<style type="text/css"><%@ include file="/WEB-INF/css/mps_ie7.css" %></style>
<![endif]-->
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/in-styles.css?version=<html:fileCacheVersion/>" />
<div class="main-wrap">
	<div class="content">
		<div style="">
			<div id="divNotificationTitle">
				<span class="orangeSectionTitles"><spring:message code="notificationAdmin.label.notificationAdminDetail"/></span><br/>
				<spring:message code="notificationAdmin.label.detailScreenDescription"/>
			</div>
			<div id="divNotificationDetail">
				<portlet:actionURL var="saveNotificationURL">
					<portlet:param name="action" value="saveNotificationDetail"></portlet:param>
				</portlet:actionURL>
				<form:form name="formNotificationDetail" id="formNotificationDetail" commandName="notificationDetailForm" method="post" action="${saveNotificationURL}" >
					<form:hidden path="submitToken"/>
					<table width="100%">
						<tr>
							<td width="400px" class="boldField" height="30px">
								*&nbsp;<spring:message code="notificationAdmin.label.notificationName"/>:
							</td>
							<td class="boldField">
								<spring:message code="notificationAdmin.label.urlLink"/>:
							</td>
						</tr>
						<tr>
							<td>
								<div class="inputLgWrapper">
								<form:input class="inputLg" path="notificationDetail.notification.adminName" maxlength="100" />
								</div>
							</td>
							<td>
								<div class="inputLgWrapper">
								<form:input class="inputLg" path="notificationDetail.notification.displayURL" maxlength="250"/>
								</div>
								<form:hidden path="notificationDetail.notification.notificationId"/>
								<form:hidden path="notificationDetail.notification.displayOrder"/>
							</td>
						</tr>
					</table>
					<br/>
					<table width="100%">
						<tr>
							<td width="400px" class="boldField" height="30px">
								*&nbsp;<spring:message code="notificationAdmin.label.dateToBeginDisplay"/>:
							</td>
							<td class="boldField">
								*&nbsp;<spring:message code="notificationAdmin.label.dateToEndDisplay"/>:
							</td>
						</tr>
						<tr>
							<td width="400px">
								<a id="prevLocalizedDisplayDate" onClick="clickDateAction('localizedDisplayDate', 'PREV')" class="validDateAction" title="Previous Day"><<</a>
								<input type="hidden" id="displayDate" name="displayDate" readonly="readonly" value='<fmt:formatDate value="${notificationDetailForm.notificationDetail.notification.displayDate}" pattern="MM/dd/yyyy"/>'/>
								<input type="text" name="localizedDisplayDate" id="localizedDisplayDate" size="7" class="inputSm" onMouseUp="callOmnitureAction('Notifications Admin', 'Notification Admin Detail show begin date calendar');show_cal('localizedDisplayDate', todayStr, null);" onFocus="reloadDateAction('localizedDisplayDate', todayStr, 'PREV')" onBlur="this.className='';" readonly=""/>
								<img id="imgLocalizedDisplayDate" class="ui_icon_sprite calendar-icon" src="<html:imagesPath/>transparent.png" onClick="callOmnitureAction('Notifications Admin', 'Notification Admin Detail show end date calendar');show_cal('localizedDisplayDate', todayStr, null)"/>
								<a id="nextLocalizedDisplayDate" onClick="clickDateAction('localizedDisplayDate', 'NEXT')" class="validDateAction" title="Next Day">>></a>
							</td>
							<td>
								<a id="prevLocalizedRemoveDate" onClick="clickDateAction('localizedRemoveDate', 'PREV')" class="validDateAction" title="Previous Day"><<</a>
								<input type="hidden" id="removeDate" name="removeDate" readonly="readonly" value='<fmt:formatDate value="${notificationDetailForm.notificationDetail.notification.removeDate}" pattern="MM/dd/yyyy"/>'/>
								<input type="text" name="localizedRemoveDate" id="localizedRemoveDate" size="7" class="inputSm" onMouseUp="callOmnitureAction('Notifications Admin', 'Notification Admin Detail show begin date calendar');show_cal('localizedRemoveDate', todayStr, null);" onFocus="reloadDateAction('localizedRemoveDate', todayStr, 'PREV')" onBlur="this.className='';" readonly=""/>
								<img id="imgLocalizedRemoveDate"class="ui_icon_sprite calendar-icon" src="<html:imagesPath/>transparent.png" onClick="callOmnitureAction('Notifications Admin', 'Notification Admin Detail show end date calendar');show_cal('localizedRemoveDate', todayStr, null)"/>
								<a id="nextLocalizedRemoveDate" onClick="clickDateAction('localizedRemoveDate', 'NEXT')" class="validDateAction" title="Next Day">>></a>
							</td>
						</tr>
					</table>
					<br/>
					<table width="100%">
						<tr>
							<td width="200px" class="boldField" height="30px">
								<spring:message code="notificationAdmin.label.supportedLanguage" />
							</td>
							<td class="boldField">
								<spring:message code="notificationAdmin.label.displayLocalizedNotificationText" />
							</td>
						</tr>
					<c:forEach items="${notificationDetailForm.notificationDetail.notificationLocaleList}" var="notificationLocale" varStatus="counter" begin="0">
						<tr height="30px">
							<td>
								${notificationLocale.supportedLocale.supportedLocaleName}
								<c:if test="${notificationLocale.supportedLocale.supportedLocaleName == 'English'}">
									<label id="lblEnglishIndex" style="display: none; visibility: hidden">${counter.index}</label>
								</c:if>
								<form:hidden path="notificationDetail.notificationLocaleList[${counter.index}].notificationLocaleId"/>
								<form:hidden path="notificationDetail.notificationLocaleList[${counter.index}].supportedLocale.supportedLocaleId"/>
							</td>
							<td>
								<div class="inputXLgWrapper">
								<form:input class="inputXLg" path="notificationDetail.notificationLocaleList[${counter.index}].displayDescription" maxlength="500"/>
								</div>
							</td>
						</tr>
					</c:forEach>
					</table>
					<br/>
					<div class="text-align-right buttonContainer">
						<a class="button_cancel color-606060" href="javascript:onCancelClick();">CANCEL</a>&nbsp;
						<a class="button color-white" href="javascript:doSubmit();">SUBMIT</a> 
					</div>
				</form:form>
			</div>
		</div>
    </div>
</div>
<script type="text/javascript">
    if (document.getElementById("displayDate").value != "") {
        document.getElementById("localizedDisplayDate").value = localizeDate(document.getElementById("displayDate").value);
    } else {
    	document.getElementById("localizedDisplayDate").value = localizeDate(todayStr);
    }
    if (document.getElementById("removeDate").value != "") {
        document.getElementById("localizedRemoveDate").value = localizeDate(document.getElementById("removeDate").value);
    } else {
        document.getElementById("localizedRemoveDate").value = localizeDate(todayStr);
    }
    reloadDateAction('localizedDisplayDate', todayStr, 'PREV');
    reloadDateAction('localizedRemoveDate', todayStr, 'PREV');

	function doValidate() {
		clearMessage();
		document.getElementById("displayDate").value = formatDateToDefault(document.getElementById("localizedDisplayDate").value);
        document.getElementById("removeDate").value = formatDateToDefault(document.getElementById("localizedRemoveDate").value);
		var isValid = true;
		if (document.getElementById("notificationDetail.notification.adminName").value.trim() == "") {
			showError("<spring:message code='notificationAdmin.label.nameRequired'/>",null, true);
			isValid = false;
		}
		var url = document.getElementById("notificationDetail.notification.displayURL").value.trim().toLowerCase();
		if(url!=""){
			if(url.indexOf("http:\/\/")!=0 && url.indexOf("https:\/\/")!=0){
				document.getElementById("notificationDetail.notification.displayURL").value = "http://"+document.getElementById("notificationDetail.notification.displayURL").value;
				url = "http://"+url;
			}
			if(!isURL(url)){
				showError("<spring:message code='notificationAdmin.label.invalidURL'/>",null, true);
				isValid = false;
			}		
		}
		
		var startDateStr = document.getElementById("displayDate").value.trim();
		var endDateStr = document.getElementById("removeDate").value.trim();
		if (startDateStr == "") {
			showError("<spring:message code='notificationAdmin.label.dateToBeginDisplayRequired'/>",null, true);
			isValid = false;
		} 
		if (endDateStr == "") {
			showError("<spring:message code='notificationAdmin.label.dateToEndDisplayRequired'/>",null, true);
			isValid = false;
		}
		if (document.getElementById("lblEnglishIndex") != null &&
				document.getElementById('notificationDetail.notificationLocaleList' +
					    document.getElementById("lblEnglishIndex").innerHTML + '.displayDescription').value == "") {
			showError("<spring:message code='notificationAdmin.label.englishDescriptionRequired'/>",null, true);
			isValid = false;
		}
		// return false when any date is empty
		if (startDateStr == "" || endDateStr == "") {
			return false;
		}
		startDate = convertStringToDate(startDateStr);
		endDate = convertStringToDate(endDateStr);
		if (startDate > endDate) {
			showError("<spring:message code='notificationAdmin.label.beginDateBeforeEndDate'/>",null, true);
			isValid = false;
		}
		return isValid;
	};

	function onCancelClick(){
    	callOmnitureAction('Notifications Admin', 'Notification Admin Detail Return to List');
		window.location.href="<portlet:renderURL></portlet:renderURL>";
	};
	function doSubmit(){
		doValidate();
		document.formNotificationDetail.submit();
	}
</script>
<script type="text/javascript">
//---- Ominture script 
     portletName = "Notification Admin Detail";
     addPortlet(portletName);
     pageTitle="Notification detail";
</script>