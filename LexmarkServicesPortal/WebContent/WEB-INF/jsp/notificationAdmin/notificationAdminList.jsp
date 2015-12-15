<%@ include file="/WEB-INF/jsp/include.jsp"%>
<%@ include file="/WEB-INF/jsp/includeGrid.jsp"%>
<style type="text/css"><%@ include file="/WEB-INF/css/ci6.css" %></style>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/mps.css"/>
<!--[if IE 7]>
	<style type="text/css"><%@ include file="/WEB-INF/css/mps_ie7.css" %></style>
<![endif]-->
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/in-styles.css?version=<html:fileCacheVersion/>" />
<div class="main-wrap">
	<div class="content">
		<div width="800px">
			<div id="divMessage"></div>
			<div id="notification_admin_default"  class="div-spl-style1">
				<span class="orangeSectionTitles"><spring:message code="notificationAdmin.label.notificationAdminDefault"/></span><br/>
				<spring:message code="notificationAdmin.label.defaultScreenDescription" /><br/><br/>
			</div>
			<div class="height-5px"></div>
			<div style="">
				<div>
					<div class="div-style59" >
						<button class="button" onClick="addNotification()"><spring:message code="notificationAdmin.label.addNotification"/></button>
					</div>
					<div class="overflow-auto-important width-100per"><div id="notification_list_container" class="width-800px"></div></div>
				</div>
				<div id="loadingNotification" class="gridLoading width-750px">
					<!--spring:message code='loadingNotification' />&nbsp;&nbsp;--><img src="<html:imagesPath/>gridloading.gif"/>
				</div>
				<div>
					<span id="pagingArea"></span>&nbsp;<span id="infoArea"></span>
				</div>
			</div>
		</div>
	</div>
</div>	
<portlet:resourceURL var="orderURL" id="updateNotificationDisplayOrder"/>
<script type="text/javascript">
	var isFistLoadFlag = true;
	var changeToPageNumber=1;
	var selectedRowId;
    var currentRowNum;
	notificationListGrid = new dhtmlXGridObject('notification_list_container');
	notificationListGrid.setImagePath("<html:imagesPath/>gridImgs/");
	notificationListGrid.setHeader("<spring:message code='notificationAdmin.listHeader.notificationFields'/>");
	notificationListGrid.setInitWidths("*,100,80,150,150");
	notificationListGrid.setColAlign("left,left,left,left,left");
	notificationListGrid.setColTypes("ro,ro,ro,ro,ro");
	notificationListGrid.setColSorting("na,na,na,na,na");
    notificationListGrid.enableAutoWidth(true);
    notificationListGrid.enableAutoHeight(true);
    notificationListGrid.enableMultiline(true);
    notificationListGrid.init();
    notificationListGrid.enableLightMouseNavigation(true);
    notificationListGrid.enablePaging(true, 20, 10, "pagingArea", true, "infoArea");
    notificationListGrid.setPagingSkin("bricks");
    notificationListGrid.setSkin("light");
	notificationListGrid.loadXML("<portlet:resourceURL></portlet:resourceURL>&orderBy=1&direction=ASCENDING");

	notificationListGrid.attachEvent("onXLS", function() {
        document.getElementById('loadingNotification').style.display = 'block';
    });
    notificationListGrid.attachEvent("onXLE", function() {
    	if(isFistLoadFlag){
    		notificationListGrid.setSortImgState(true, 1, 'asc');
    	    isFistLoadFlag = false;
        }
        document.getElementById('loadingNotification').style.display = 'none';
        setTimeout(function(){
    		rebrandPagination();
    	
    	},100);
    });
	notificationListGrid.attachEvent("onMouseOver", function(id,index){
		return false;
    });
    
	function deleteNotification(notificationId, notificationName) {
    	callOmnitureAction('Notifications Admin', 'Notification Admin List Delete Notification');
		if(confirm('Are you sure to delete "'+notificationName+'"?')){
  	  	var url = '<portlet:resourceURL id="deleteNotificationDisplay"/>';
	  	url +="&notificationId="+notificationId;
	  	changeToPageNumber = notificationListGrid.currentPage;
		doAjax(url, callbackGetResult);
		//NotificationAjaxProxy.deleteNotificationDisplay(notificationId,callbackGetResult);
		}
	};
	
	function callbackGetResult(result) {
		notificationListGrid.clearAndLoad("<portlet:resourceURL></portlet:resourceURL>",function(){
			notificationListGrid.changePage(changeToPageNumber);
		});
	};

	function addNotification() {
    	callOmnitureAction('Notifications Admin', 'Notification Admin List Add Notification');
        window.location.href = '<portlet:renderURL><portlet:param name="action" value="showNotificationDetail"/></portlet:renderURL>';
	};

	function viewNotificationDetail(notificationId) {
    	callOmnitureAction('Notifications Admin', 'Notification Admin List View Notification');
		window.location.href = '<portlet:renderURL><portlet:param name="action" value="showNotificationDetail"/></portlet:renderURL>&notificationId=' + notificationId;
	};

	function increaseDisplayOrder(rowId) {
		var displayOrder = notificationListGrid.cellById(rowId, 1).getValue();
		var url = '${orderURL}';
		url +="&displayOrder="+parseInt(displayOrder);
		url +="&increment=-1";
		changeToPageNumber = notificationListGrid.currentPage;
		doAjax(url, moveRowUp);
		//NotificationAjaxProxy.updateNotificationDisplayOrder(parseInt(displayOrder), -1, moveRowUp);
	};

	function decreaseDisplayOrder(rowId) {
		var displayOrder = notificationListGrid.cellById(rowId, 1).getValue();
		var url = '${orderURL}';
		url +="&displayOrder="+parseInt(displayOrder);
		url +="&increment=1";
		changeToPageNumber = notificationListGrid.currentPage;
		doAjax(url, moveRowDown);
		//NotificationAjaxProxy.updateNotificationDisplayOrder(parseInt(displayOrder), 1, moveRowDown);
	};

    function moveRowUp() {
    	callOmnitureAction('Notifications Admin', 'Notification Admin List Move Up');
    	notificationListGrid.clearAndLoad("<portlet:resourceURL></portlet:resourceURL>");
    	notificationListGrid.changePage(changeToPageNumber);
    };

    function moveRowDown() {
    	callOmnitureAction('Notifications Admin', 'Notification Admin List Move Down');
    	notificationListGrid.clearAndLoad("<portlet:resourceURL></portlet:resourceURL>");
    	notificationListGrid.changePage(changeToPageNumber);
		
    };
</script>
<script type="text/javascript">
//---- Ominture script 
     portletName = "Notification Admin List";
     addPortlet(portletName);
     pageTitle="Notification admin list";
</script>