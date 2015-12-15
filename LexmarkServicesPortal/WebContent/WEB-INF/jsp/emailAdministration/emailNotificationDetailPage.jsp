<%@ include file="/WEB-INF/jsp/include.jsp"%>
<%@ include file="/WEB-INF/jsp/includeGrid.jsp"%>
<%@ taglib uri="/WEB-INF/tld/liferay-ui.tld" prefix="liferay-ui" %>
<style type="text/css"><%@ include file="/WEB-INF/css/ci6.css" %></style>

<!--[if IE 7]>
	<style type="text/css"><%@ include file="/WEB-INF/css/mps_ie7.css" %></style>
<![endif]-->
<script type="text/javascript">
	<%@ include file="../../../js/validation.js"%>
	<%@ include file="../../../js/jquery.validate.js"%>
</script>
<portlet:actionURL var="actionUrl">
	<portlet:param name="action" value="saveEmailNotificationDetail"/>		
</portlet:actionURL>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/in-styles.css?version=<html:fileCacheVersion/>" />
<body>

<style type="text/css">
   .labelBold {
    min-width: 275px;
}
 label.error { width: 300px; display: block; float: left; color: red; padding-left: 5px; }
</style>
<div class="main-wrap">
	<div class="content">
		<label for="emailName" id="labErrorMsg" class="error" style="display: none;"><spring:message code="emailNotification.validateError.emailNameValueNull"/></label><br>
		<form:form id="enForm" commandName="emailNotificationDetailForm" method="post" action="${actionUrl}" onSubmit="saveEntry();">
			<div class='orangeSectionTitles' ><spring:message code="emailNotification.titile.emailAdministrationDetails" /></div>
			<div class='description' ><spring:message code="emailNotification.label.emailAdministrationDetailsDescription" /></div>
			<div class="labelBold width-auto floatL">
				*<spring:message code="emailNotification.label.emailName"/>:<br/>
				<div class="inputLgWrapper"><form:input path="emailName" class="inputLg" maxlength="200" htmlEscape=""/></div>
			</div>
			<div class="labelBold width-auto floatL">
				<spring:message code="emailNotification.label.emailDescription"/>:<br/>
				<form:textarea  path="emailDescription" rows="5" cols="80" maxlength="500"/>
			</div>
			<div class="overflow-auto-important width-100per"><div id="emailNotificationDetailContainer"></div></div>
			<div id="tabEmailNotificationLocale">
				<br><div class="labelBold"><spring:message code="emailNotification.label.localizedSetting"/>: <span id="localeLanguage"></span></div>
				<br><div class="labelBold"><spring:message code="emailNotification.label.subject"/>:</div><br/>
				<div id="divSubjectArea">
					<div class="inputLgWrapper">
					<form:input class="inputLg" path="emailNotificationLocale.emailSubject" maxlength="200" />
					</div>
				</div>
				<br><div class="labelBold"><spring:message code="emailNotification.label.header"/>:</div>
				<div id="divHeaderArea" >
					<liferay-ui:input-editor editorImpl="<%= EDITOR_WYSIWYG_IMPL_KEY %>" name="headEditor" toolbarSet="liferay" height="200" width="100%"></liferay-ui:input-editor>
					<form:input style="display:none;"  path="emailNotificationLocale.emailHeader"  size="50" maxlength="2000" />
				</div>
				<br><div class="labelBold"><spring:message code="emailNotification.label.body"/>:</div>
				<div id="divBodyArea"  >
					<liferay-ui:input-editor editorImpl="<%= EDITOR_WYSIWYG_IMPL_KEY %>" name="bodyEditor" toolbarSet="liferay" height="400" width="100%"></liferay-ui:input-editor>
					<form:input style="display:none;"  path="emailNotificationLocale.emailBody"    size="50" maxlength="4000" />
				</div>
				<br><div class="labelBold"><spring:message code="emailNotification.label.footer"/>:</div>
				<div id="divFooterArea"  >
					<liferay-ui:input-editor editorImpl="<%= EDITOR_WYSIWYG_IMPL_KEY %>" name="footerEditor" toolbarSet="liferay" height="200" width="100%"></liferay-ui:input-editor>
					<form:input style="display:none;"  path="emailNotificationLocale.emailFooter"    size="30" maxlength="2000" />
				</div><br/>
				<div class="buttonContainer">
					<button class="button_cancel" type="button" onclick="goBack();" /><spring:message code="button.cancel"/></button>&nbsp;
					<button class="button" type="submit"><spring:message code="button.submit"/></button>
				</div>
			</div>
			<table>
				
				<tr>
					<td style="display:none"><form:input class="width-70px"  path="emailNotificationLocale.emailNotificationLocaleId"  size="30" maxlength="80" /></td>
					<td style="display:none"><form:input class="width-70px"  path="emailNotificationLocale.locale.supportedLocaleId" size="30" maxlength="80" /></td>
					<td style="display:none"><form:input class="width-70px"  path="emailNotificationLocale.locale.supportedLocaleName" size="30" maxlength="80" /></td>
					<td style="display:none"><form:input class="width-70px"  path="emailNotificationLocale.emailNotification.emailNotificationId"    size="30" maxlength="80" /></td>
					<td style="display:none"><form:input class="width-70px"  path="editFlag"    size="30" maxlength="80" /></td>
					<td style="display:none"><form:input class="width-70px"  path="emailNotificationId"    size="30" maxlength="80" /></td>
				</tr>
			</table>
		</form:form>
	</div>
</div>	
<script type="text/javascript">
	var addFlag = true;
	function <portlet2:namespace />initEditor() {
		return "";
		};
	function saveEntry() {
		if(document.getElementById("editFlag").value== "true"){
			document.getElementById("emailNotificationLocale.emailHeader").value = parent.headEditor.getHTML();
			document.getElementById("emailNotificationLocale.emailBody").value = parent.bodyEditor.getHTML();
			document.getElementById("emailNotificationLocale.emailFooter").value = parent.footerEditor.getHTML();
		}
	};
	if(null=="${emailNotificationDetailForm.emailName}" || ""=="${emailNotificationDetailForm.emailName}"){
	}else{
		document.getElementById("emailName").value = "${emailNotificationDetailForm.emailName}";
		document.getElementById("emailDescription").value = "${emailNotificationDetailForm.emailDescription}";
		document.getElementById("emailNotificationId").value ="${emailNotificationDetailForm.emailNotificationId}";
	}
	emailDetailGrid = new dhtmlXGridObject('emailNotificationDetailContainer');
	emailDetailGrid.setImagePath("<html:imagesPath/>gridImgs/");
	emailDetailGrid.setHeader("<spring:message code='emailNotification.listHeader.emailNotificationDetail'/>");
	emailDetailGrid.setInitWidths("300,150,150");
	emailDetailGrid.setColAlign("left,left,left");
	emailDetailGrid.setColTypes("ro,ro,ro");
	emailDetailGrid.setColSorting("str,na,na");
	emailDetailGrid.setSkin("light");
	emailDetailGrid.enableAutoWidth(true);
	emailDetailGrid.enableAutoHeight(true);
	emailDetailGrid.init();
	emailDetailGrid.loadXML("${showEmailNotificationDetailURL}");

    var localeArray =new Array();
    
    <c:forEach items="${emailNotificationDetailForm.emailNotificationLocaleList}" var="emailNotificationLocale" varStatus = "status" >
    localeArray[${status.index}] =  { 
    		localeId : "${emailNotificationLocale.locale.supportedLocaleId}",
    		localeName : "${emailNotificationLocale.locale.supportedLocaleName}",
    		emailSubject : "${emailNotificationLocale.emailSubject}",
    		emailHeader : "${emailNotificationLocale.emailHeader}",
    		emailBody : "${emailNotificationLocale.emailBody}",
    		emailFooter : "${emailNotificationLocale.emailFooter}",
    		emailNotificationLocaleId : "${emailNotificationLocale.emailNotificationLocaleId}"
		}
	</c:forEach>
	
function editEmailNotificationLocale(emailNotificationLocaleId){
	callOmnitureAction('Email Admin', 'Email Admin Detail Edit Notification Locale');
	clearMessage();
	addFlag = false;
	document.getElementById("tabEmailNotificationLocale").style.display = "";
	document.getElementById("emailNotificationLocale.emailSubject").focus();
	document.getElementById("editFlag").value= true;
	for(var i = 0;i<localeArray.length;i++){
		if(localeArray[i].emailNotificationLocaleId == emailNotificationLocaleId){
			parent.headEditor.setHTML(localeArray[i].emailHeader);
			parent.bodyEditor.setHTML(localeArray[i].emailBody);
			parent.footerEditor.setHTML(localeArray[i].emailFooter);
			document.getElementById("emailNotificationLocale.emailSubject").value=localeArray[i].emailSubject;
			
			document.getElementById("emailNotificationLocale.emailNotificationLocaleId").value=emailNotificationLocaleId;
			document.getElementById("emailNotificationLocale.locale.supportedLocaleId").value=localeArray[i].localeId;
			document.getElementById("emailNotificationLocale.locale.supportedLocaleName").value=localeArray[i].localeName;
			if(null!=<%= request.getParameter("emailNotificationId")%>){
				document.getElementById("emailNotificationLocale.emailNotification.emailNotificationId").value=<%= request.getParameter("emailNotificationId")%>;
				}
			document.getElementById("localeLanguage").innerHTML = localeArray[i].localeName;
			break;
			}
		}
};

function deleteEmailNotificationLocale(emailNotificationLocaleId){
	callOmnitureAction('Email Admin', 'Email Admin Detail Delete Notification Locale');
  	var url = '<portlet:resourceURL id="deleteEmailNotificationLocale"/>';
  	url +="&emailNotificationLocaleId="+emailNotificationLocaleId;
	doAjax(url, callbackGetResult);
};
	
function addEmailNotificationLocale(localeId){
	callOmnitureAction('Email Admin', 'Email Admin Detail Add Notification Locale');
	clearMessage();
	document.getElementById("tabEmailNotificationLocale").style.display = "";
	document.getElementById("editFlag").value= true;
	document.getElementById("emailNotificationLocale.emailSubject").focus();
	if(!addFlag){
		parent.headEditor.setHTML("");
		parent.bodyEditor.setHTML("");
		parent.footerEditor.setHTML("");
		document.getElementById("emailNotificationLocale.emailSubject").value ="";
		addFlag = true;
	}
	for(var i = 0;i<localeArray.length;i++){
		if(localeArray[i].localeId == localeId){
			document.getElementById("emailNotificationLocale.emailNotificationLocaleId").value=localeArray[i].emailNotificationLocaleId;
			document.getElementById("emailNotificationLocale.locale.supportedLocaleId").value=localeArray[i].localeId;
			document.getElementById("emailNotificationLocale.locale.supportedLocaleName").value=localeArray[i].localeName;
			if(null!=<%= request.getParameter("emailNotificationId")%>){
				document.getElementById("emailNotificationLocale.emailNotification.emailNotificationId").value=<%= request.getParameter("emailNotificationId")%>;
				}
			document.getElementById("localeLanguage").innerHTML = localeArray[i].localeName;
			break;
			}
	}
};
function callbackGetResult(result) {
	if(result){
		reloadGrid();
		document.getElementById("tabEmailNotificationLocale").style.display = "none";
	}
};

function reloadGrid() {
	var url = "${showEmailNotificationDetailURL}";
	emailDetailGrid.clearAndLoad(url);
};
function goBack(){
	 var backURL = "<portlet:renderURL></portlet:renderURL>";
	 location.href = backURL;
};
	//jquery form validation approach
	jQuery(document).ready(function() {		 
		  //reloadOnceOnly();
    	jQuery("#enForm").validate({
     	 rules: {    	  	
      		"emailName": "required"
  	   		}
       });
  	});

</script>
</body>

<%!
public static final String EDITOR_WYSIWYG_IMPL_KEY = "editor.wysiwyg.portal-web.docroot.html.portlet.blogs.edit_entry.jsp";
%>
<script type="text/javascript">
//---- Ominture script 
     portletName = "Email Admin Detail";
     addPortlet(portletName);
     pageTitle="Email notification detail";
</script>