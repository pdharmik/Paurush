<%@ include file="/WEB-INF/jsp/include.jsp"%>
<%@ include file="/WEB-INF/jsp/includeGrid.jsp"%>
<script type="text/javascript"><%@ include file="../../../js/portletRedirection.js"%></script>
<style type="text/css"><%@ include file="/WEB-INF/css/ci6.css" %></style>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/mps.css"/>
<!--[if IE 7]>
	<style type="text/css"><%@ include file="/WEB-INF/css/mps_ie7.css" %></style>
<![endif]-->
<style>
#reportListContainer{width:800px;}
.ie7 .portlet-topper{width:99% !important;}

div.gridbox_light .xhdr { 
	background-image:none !important;
	background:#e6e6f0 !important;
}
</style>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/in-styles.css?version=<html:fileCacheVersion/>" />
<div class="main-wrap">
	<div class="content">
	    <div id="cstmRptAdminTitle"  class="cstmRptAdminTitle">
	        <span class="orangeSectionTitles"><spring:message code="customerReportsAdmin.label.rptAdmin"/></span><br/>
	        <spring:message code="customerReportsAdmin.label.rptAdminDescription" /><br/><br/>
	    </div>
	    <div class="height-5px"></div>
	    <div class="div-style38">
	        <a href="javascript:addNewReport();" class="button anchor-button1"><spring:message code="customerReportsAdmin.label.addNewReport" /></a>&nbsp;
	        <a href="javascript:gotoCategoryDisplay();" class="button anchor-button1"><spring:message code="customerReportsAdmin.label.categoryDisplay" /></a>&nbsp;
	        <a href="javascript:gotoSchedulerMaintenance();" class="button anchor-button1"><spring:message code="customerReportsAdmin.label.scheduleMaintenance" /></a>
	    </div>
	    <div class="height-5px"></div>
	    <div>
	        <div class="overflow-auto-important width-100per"><div id="reportListContainer" ></div></div>
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
    reportListGrid = new dhtmlXGridObject('reportListContainer');
    reportListGrid.setImagePath("<html:imagesPath/>gridImgs/");
    reportListGrid.setHeader("<spring:message code='customerReportsAdmin.listHeader.reportFields'/>");
    reportListGrid.setInitWidths("300,100,200,100,100");
    reportListGrid.setColAlign("left,left,left,left,left");
    reportListGrid.setColTypes("ro,ro,ro,ro,ro");
    reportListGrid.setColSorting("domSort,str,str,str,na");
    reportListGrid.init();
    //reportListGrid.enableAutoWidth(true);
    reportListGrid.enableAutoHeight(true);
    //reportListGrid.enableMultiline(true);
    reportListGrid.setSizes();
    reportListGrid.prftInit();
	
    reportListGrid.enableLightMouseNavigation(true);
    reportListGrid.setSkin("light");
    reportListGrid.attachEvent("onXLS", function() {
        document.getElementById('loadingNotification').style.display = 'block';
    });
    var isFistLoadFlag = true;
    reportListGrid.attachEvent("onXLE", function() {
        if(isFistLoadFlag){
            reportListGrid.setSortImgState(true, 1, 'asc');
            isFistLoadFlag = false;
        }
        document.getElementById('loadingNotification').style.display = 'none';
        setTimeout(function(){
    		rebrandPagination();
    	
    	},100);
    });

    reportListGrid.enablePaging(true, 20, 10, "pagingArea", true, "infoArea");
    reportListGrid.setPagingSkin("bricks");

    reportListGrid.loadXML("<portlet:resourceURL></portlet:resourceURL>");

    reportListGrid.attachEvent("onMouseOver", function(id,index){
        return false;
    });

    function addNewReport() {
		callOmnitureAction('Report Administration', 'Report Administration -New Report');
 		<%--New Report Flag appended for CI BRD 13-10-27--%>
    	window.location.href = "<portlet:renderURL><portlet:param name='action' value='showReportDefinition' />"+
    		"<portlet:param name='newReport' value='true' />"+
    	"</portlet:renderURL>";
    };

    function gotoCategoryDisplay() {
		callOmnitureAction('Report Administration', 'Report Administration -Goto Category');
        window.location.href = "<portlet:renderURL><portlet:param name='action' value='showCategoryDisplay' /></portlet:renderURL>";
    };

    var changeToPageNumber=1;
    function deleteReportAdministration(reportDefinitionId, reportDefinitionName) {
    	if(confirm('Are you sure to delete "'+reportDefinitionName+'"?')){
            var url = '<portlet:resourceURL id="deleteReportDefinition"/>';
            url += "&reportDefinitionId=" + reportDefinitionId;
            changeToPageNumber = reportListGrid.currentPage;
    		callOmnitureAction('Report Administration', 'Report Administration -Delete report definition ' + reportDefinitionName);
              doAjax(url, refreshReportDefinitionList);
    	}
    };

    function refreshReportDefinitionList(result) {
    	reportListGrid.clearAndLoad("<portlet:resourceURL></portlet:resourceURL>",function(){
    		reportListGrid.changePage(changeToPageNumber);
        });
    };

    function editReportDefinition(reportDefinitionId) {
		callOmnitureAction('Report Administration', 'Report Administration -Edit report definition ' + reportDefinitionId);
        var reportDefinitionForm = document.createElement("form");
        reportDefinitionForm.action='<portlet:renderURL><portlet:param name="action" value="showReportDefinition"/></portlet:renderURL>';
        reportDefinitionForm.method="post";
        document.body.appendChild(reportDefinitionForm);

        var input = document.createElement("input"); 
        input.type="hidden";
        input.name="reportDefinitionId";
        input.value=reportDefinitionId;
        
        reportDefinitionForm.appendChild(input);
        setTimeout(function(){reportDefinitionForm.submit();},0);
    };

    function gotoSchedulerMaintenance(definitionName) {
		callOmnitureAction('Report Administration', 'Report Administration -Goto scheduler maintenance');
        var definitionNameParam = "";
        if (definitionName != null) {
        	definitionNameParam = "&definitionName=" + definitionName;
        }
        window.location.href = "<portlet:actionURL><portlet:param name='action' value='viewSchedulerMaintenance'/></portlet:actionURL>&schedulerMaintenanceURL=" + populateURL('scheduler-maintenance',[],[]) + definitionNameParam;
    };
</script>
<script type="text/javascript">
//---- Ominture script 
     portletName = "Report Administration Page";
     addPortlet(portletName);
     pageTitle="Default view";
</script>