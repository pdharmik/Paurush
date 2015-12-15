<%@ include file="/WEB-INF/jsp/include.jsp"%>
<%@ include file="/WEB-INF/jsp/includeGrid.jsp"%>
<style type="text/css"><%@ include file="/WEB-INF/css/ci6.css" %></style>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/mps.css"/>
<!--[if IE 7]>
	<style type="text/css"><%@ include file="/WEB-INF/css/mps_ie7.css" %></style>
<![endif]-->
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/in-styles.css?version=<html:fileCacheVersion/>" />
<style type="text/css">
#emailListContainer{width:800px;}
</style>
<div class="main-wrap">
	<div class="content">
		<table width="100%">
			<tr>
				<td class='orangeSectionTitles'><spring:message code="emailNotification.title.emailAdministration" /></td>
			</tr>
			<tr>
				<td class='description'><spring:message code="emailNotification.label.emailAdministrationDescription" /></td>
			</tr>
			<tr class="paddingTR"></tr>
			<tr>
				<td align="left">
				<div class="buttonContainer float-left text-align-left">
					<button id="btnCreate" class="button" onClick="createEmailNotification();"><spring:message code="emailNotification.label.addNewEmail"/></button>
				</div>
				</td>
			</tr>			
		</table>
		<div class="overflow-auto-important width-100per"><div id="emailListContainer"></div></div>
		<div id="loadingNotification" class='gridLoading'>
			<!--spring:message code='loadingNotification' />&nbsp;&nbsp;--><img src="<html:imagesPath/>gridloading.gif"/>
		</div>
		<div><span id="pagingArea"></span>&nbsp;<span id="infoArea"></span></div>
	</div>
</div>	
<script type="text/javascript">

	function domText(dom){
		var div = document.createElement("div");
		div.innerHTML = dom; 
		var text = div.innerText||div.textContent;
		div = null;//avoid mem leak
		return text;
	}
	function domSort(a,b,order){
		a = domText(a);
		b = domText(b);
		return (a>b?1:-1)*(order=="asc"?1:-1);
	}
	emailListGrid = new dhtmlXGridObject('emailListContainer');
	emailListGrid.setImagePath("<html:imagesPath/>gridImgs/");
	emailListGrid.setHeader("<spring:message code='emailNotification.listHeader.emailAdministration'/>");
	emailListGrid.setInitWidths("250,450,100");
	emailListGrid.setColAlign("left,left,center");
	emailListGrid.setColTypes("ro,ro,ro");
	emailListGrid.setColSorting("domSort,str,na");
	//emailListGrid.setSkin("light");
	//emailListGrid.enableAutoWidth(true);
	//emailListGrid.enableAutoHeight(true);
	//emailListGrid.enableMultiline(true);
	//emailListGrid.init();
	
	emailListGrid.enablePaging(true, 10, 10, "pagingArea", true, "infoArea");
	emailListGrid.setPagingSkin("bricks");
	emailListGrid.enableAutoHeight(true);
	emailListGrid.setSizes();
	emailListGrid.init();
	emailListGrid.prftInit();
	emailListGrid.setSkin("light");
	emailListGrid.sortIndex = null;
	emailListGrid.columnChanged = true;
	
	emailListGrid.loadXML("<portlet:resourceURL></portlet:resourceURL>");

	emailListGrid.attachEvent("onXLS", function() {
        document.getElementById('loadingNotification').style.display = 'block';
    });
	emailListGrid.attachEvent("onXLE", function() {
        document.getElementById('loadingNotification').style.display = 'none';
        setTimeout(function(){
    		rebrandPagination();
    	
    	},100);
	});
	
	emailListGrid.enablePaging(true, 20, 10, "pagingArea", true, "infoArea");
	emailListGrid.setPagingSkin("bricks");
	
	function viewEmailNotificationDetail(emailNotificationId){
		callOmnitureAction('Email Admin', 'Email Admin View Notification');
		window.location.href="<portlet:renderURL><portlet:param name='action' value='viewEmailNotificationDetail'/></portlet:renderURL>&emailNotificationId="+emailNotificationId;
	};
		
	function deleteEmailNotification(emailNotificationId,emailName) {
		callOmnitureAction('Email Admin', 'Email Admin Delete Notification');
		if(confirm("<spring:message code='emailNotification.message.emailDeleteConfirmation'/> "+emailName+"?")){
  	  	var url = '<portlet:resourceURL id="deleteEmailNotification"/>';
	  	url +="&emailNotificationId="+emailNotificationId;
		doAjax(url, callbackGetResult);
		}
	};
	
	function callbackGetResult(result) {
		emailListGrid.deleteSelectedRows();
	};
	
	function createEmailNotification(){
		callOmnitureAction('Email Admin', 'Email Admin Create New Notification');
		window.location.href="<portlet:renderURL><portlet:param name='action' value='createEmailNotification'/></portlet:renderURL>";
	}
</script>
<script type="text/javascript">
//---- Ominture script 
     portletName = "Email Admin List";
     addPortlet(portletName);
     pageTitle="Email notifiation list";
</script>
