<%@ include file="/WEB-INF/jsp/include.jsp"%>
<%@ include file="/WEB-INF/jsp/includeGrid.jsp"%>
<%@ include file="/WEB-INF/jsp/includeDatePicker.jsp"%>
<style type="text/css"><%@ include file="/WEB-INF/css/ci6.css" %></style>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/mps.css"/>
<!--[if IE 7]>
	<style type="text/css"><%@ include file="/WEB-INF/css/mps_ie7.css" %></style>
<![endif]-->
<style>
.ie7 .portlet-topper{width:99% !important;}
.ie7 .test{width:97% !important;}
.ie9 #div_reports_container{width:100% !important;}
div.gridbox_light .xhdr { 
	background-image:none !important;
	background:#e6e6f0 !important;
}
</style>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/in-styles.css?version=<html:fileCacheVersion/>" />
<div class="main-wrap">
	<div class="content">
	<div id="detailDiv" style="display:none"></div>
	    <div id="rptAdminScdlMntnTitle"  class="rptAdminScdlMntnTitle">
	        <span class="orangeSectionTitles">
	            <spring:message code="reportAdmin.label.rptAdminSchedulerMaintenance"/>
	        </span><br/>
	        <spring:message code="reportAdmin.label.rptAdminSchedulerMaintenanceDescription" /><br/><br/>
	    </div>
	    <div class="height-5px"></div>
	    <div>
	        <LABEL class="labelBold"><spring:message code="reportAdmin.label.displayReportName"/>:</LABEL>
	        <br/>
	        <select id='definitionName' onchange="doSearch();" >
	            <option value=''><spring:message code="reportAdmin.label.all" /></option>
	            <c:forEach items="${nameList}" var="name" varStatus="status">
	                <option value="${name}" <c:if test="${name == definitionName}"><spring:message code="reportAdmin.label.selected" /></c:if>>${name}</option>
	            </c:forEach>
	        </select>
	        <br/><br/>
	        <LABEL class="labelBold"><spring:message code="reportAdmin.label.displayReportsBetween"/>:</LABEL><br/>
	        <div>
	            <a id="prevLocalizedBeginDate" onClick="moveDate('localizedBeginDate', -1);" class="validDateAction" title="Previous Day"><<</a>
	            <input type="text" name="localizedBeginDate" id="localizedBeginDate" size="9" onMouseUp="show_cal('localizedBeginDate', null, null);" readOnly="readonly"/>
	            <img id="imgLocalizedBeginDate" class="ui_icon_sprite calendar-icon" src="<html:imagesPath/>transparent.png" onClick="show_cal('localizedBeginDate', null, null)"/>
	            <a id="nextLocalizedBeginDate" onClick="moveDate('localizedBeginDate', 1);" class="validDateAction" title="Next Day">>></a>
	            &nbsp;<spring:message code="reportAdmin.label.and"/>&nbsp;
	            <a id="prevLocalizedEndDate" onClick="moveDate('localizedEndDate', -1);" class="validDateAction" title="Previous Day"><<</a>
	            <input type="text" name="localizedEndDate" id="localizedEndDate" size="9" onMouseUp="show_cal('localizedEndDate', null, null);" readOnly="readonly"/>
	            
	            <img id="imgLocalizedEndDate" class="ui_icon_sprite calendar-icon" src="<html:imagesPath/>transparent.png" onClick="show_cal('localizedEndDate', null, null)"/>
	            
	            <a id="nextLocalizedEndDate" onClick="moveDate('localizedEndDate', 1);" class="validDateAction" title="Next Day">>></a>&nbsp;&nbsp;
		        <a href="javascript:doSearch();" class="button color-white"><spring:message code="button.search"/></a>
	        </div>
	    </div>
	    <div class="height-5px"></div>
	    <div id="test">
	       <div class="overflow-auto-important width-100per"><div id="div_reports_container" class="width-800px"></div></div>
	        <div id="loadingNotification" class='gridLoading'>
		    	<!--spring:message code='loadingNotification' />&nbsp;&nbsp;--><img src="<html:imagesPath/>gridloading.gif"/>
		   	</div>
	        <div>
	            <span id="pagingArea"></span>&nbsp;<span id="infoArea"></span>
	        </div>
	    </div>
	</div>
	
</div>

<script type="text/javascript">

var today = new Date();
var timezone = 0 - today.getTimezoneOffset()/60;
document.getElementById("localizedBeginDate").value = localizeDate(todayStr);
document.getElementById("localizedEndDate").value = localizeDate(todayStr);

ReportsGrid = new dhtmlXGridObject('div_reports_container');
ReportsGrid.setImagePath("<html:imagesPath/>gridImgs/");
ReportsGrid.setHeader("<spring:message code='reportAdmin.listHeader.reportListFields'/>");
ReportsGrid.setColAlign("left,left,left,left,left,left,left");
ReportsGrid.attachHeader("#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,,");
ReportsGrid.setColTypes("ro,ro,ro,ro,ro,ro,ro");
ReportsGrid.setInitWidths("*,*,*,110,*,*,110");
ReportsGrid.setColSorting("str,str,str,str,str,na,na");
ReportsGrid.enableAutoWidth(true);
ReportsGrid.enableMultiline(true);
ReportsGrid.enableAutoHeight(true);
ReportsGrid.setSkin("light");
ReportsGrid.init(); 
ReportsGrid.prftInit();
ReportsGrid.enablePaging(true, 100, 10, "pagingArea", true);
ReportsGrid.setPagingSkin("bricks");
ReportsGrid.loadXML("<portlet:resourceURL></portlet:resourceURL>&timezone=" + timezone);
ReportsGrid.attachEvent("onXLS", function() {
	document.getElementById("loadingNotification").style.display = 'block';
});
ReportsGrid.attachEvent("onXLE", function() {
	document.getElementById("loadingNotification").style.display = 'none';
	setTimeout(function(){
		rebrandPagination();
	
	},100);
	
});
function doSearch(){
	clearMessage();
	var localizedBeginDate = document.getElementById("localizedBeginDate").value;
	var localizedEndDate = document.getElementById("localizedEndDate").value;

	if (localizedBeginDate != "" && localizedEndDate != "" && convertStringToDate(localizedBeginDate) > convertStringToDate(localizedEndDate)) {
		showError("<spring:message code='reportAdmin.message.beginDateCantAfterEndDate'/>");
		return;
	}
	var beginDate = formatDateToDefault(localizedBeginDate);
	var endDate = formatDateToDefault(localizedEndDate);

	callOmnitureAction('Scheduler Maintenance', 'Schedule maintenance do search');
	var definitionName = document.getElementById("definitionName");
	reloadGrid(beginDate,endDate,definitionName.options[definitionName.selectedIndex].value);
}
function reloadGrid(beginDate,endDate,definitionName) {
	clearMessage();
	var url = "<portlet:resourceURL></portlet:resourceURL>";
	url = url + "&beginDate=" + beginDate + "&endDate=" + endDate + "&timezone=" + timezone + "&definitionName=" + definitionName ;
    ReportsGrid.clearAndLoad(url);
}
var showRunTime;
var showReportName;
var dialog;
function goDetail(runTime,reportName,runLogID){
	callOmnitureAction('Scheduler Maintenance', 'Schedule maintenance goto detail');
	showRunTime = runTime;
	showReportName = reportName;
	var url1 = "<portlet:renderURL windowState='<%= LiferayWindowState.EXCLUSIVE.toString() %>'><portlet:param name='action' value='runLogParametersLightBox' /></portlet:renderURL>&runLogID="
	+ runLogID;
	showOverlay();
	//alert("in openpop up1");
	jQuery('#detailDiv').load(url1,function(){
		
		dialog=jQuery('#detailDiv').dialog({
			autoOpen: false,
			title: 'Parameters Detail',
			modal: true,
			draggable: false,
			resizable: false,
			position: 'center',
			height: 'auto',
			width: 940,
			close: function(event,ui){
				hideOverlay();
				dialog.dialog('destroy');					 
				dialog=null;
				jQuery('#detailDiv').hide();
				}

			});
		
		dialog.dialog('open');
		jQuery('#detailDiv').show();
		});	
}
function hideSelect(){   
	var selects = document.getElementsByTagName('select');
	for (var i=0; i < selects.length; i++){
		var select = selects[i];
		select.style.visibility = "hidden";
	}      
}  

function showSelect(){   
	var selects = document.getElementsByTagName('select');
	for (var i=0; i < selects.length; i++){
		var select = selects[i];
		select.style.visibility = "visible";
	} 
}  
function reRun(batchId){
	<portlet:resourceURL var="rerunURL" id="rerunScheduleMaintenance"/>;
	var url = "${rerunURL}"+"&batchId="+batchId;
	callOmnitureAction('Scheduler Maintenance', 'Schedule maintenance reRun report');
	doAjax(url, callbackGetResult());
}
function callbackGetResult(){}

jQuery('#rptAdminScdlMntnTitle').ready(function(){
	doSearch(); //calling dosearch for first time when page appears.
});

</script>
<script type="text/javascript">
//---- Ominture script 
     portletName = "Schedule maintenance";
     addPortlet(portletName);
     pageTitle="Schedule maintenance";
</script>
