<%@ include file="/WEB-INF/jsp/include.jsp"%>
<%@ include file="/WEB-INF/jsp/includeGrid.jsp"%>
<!-- css changes made for LEX:AIR00059786 -->
<style>
	div.right-column{overflow:hidden;margin-left:0;padding-left: 5px;}
	/*#reports-banner,div.portlet-wrap{width:auto;}*/
	#reports-banner,div.portlet-wrap,div.right-column{}
</style>
<style type="text/css"><%@ include file="/WEB-INF/css/ci6.css" %></style>
<!--[if IE 7]>
	<style type="text/css"><%@ include file="/WEB-INF/css/mps_ie7.css" %></style>
<![endif]-->
<div class="main-wrap">
<div class="journal-content-article">
      <h1><spring:message code="customerReports.defaultView.heading.reports"/></h1>
</div>

<div id="emailReport" style="display:none"></div>
<div class="main-wrap">
	<div class="content">
		<%@ include file="/WEB-INF/jsp/customerReports/leftPanel.jsp"%>
				<div class="right-column">
		<div class="div-style35">
				<img src="<html:imagesPath/>reports_banner.jpg" id="reports-banner" title="report_banner"/>
		<!-- <div class="right-column col-xs-12 col-sm-9 col-md-9">
		<div style="width: 100%;max-width:715px; text-align: center">
				<img src="<html:imagesPath/>reports_banner.jpg" id="reports-banner" title="report_banner" style="width: 100%"/> -->
		</div>
			<div class="portlet-wrap">
				
			<!-- added for employee report -->
				<c:if test="${not isEmployee}">		
					<table class="displayGrid">
              		<thead>
                		<tr><th>${definitionName}</th></tr>
                	</thead>
              		</table>
           		 </c:if>
            	<c:if test="${isEmployee}">
           			 <table class="displayGrid">
              		<thead>
                		<tr><th>${definitionName} - ${accountName}</th></tr>
                	</thead>
              		</table>
           		</c:if>
				<!-- end of addition for employee report -->
            <div class="portlet-wrap-inner"">
            <div id="fileWindow" style="display:none"><h4><spring:message code="reports.fileerror"/><spring:message code="exception.contactAdmin"/></h4></div>
			<iframe id="fileFrame" style="display:none ;border: 0px"></iframe>
			</div>
					<table width="100%">
						<tr><td>
							<span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${definitionDesc}</span>
						</td></tr>
					</table>
					<div id="reportListContainer" class="width-100per"></div>
					<div id="loadingNotification" class='gridLoading width-100per'>
					<!--spring:message code='loadingNotification' />&nbsp;&nbsp;--><img src="<html:imagesPath/>gridloading.gif"/>
					</div>
					<div>
						<span id="pagingArea"></span>&nbsp;<span id="infoArea"></span>
					</div>
				</div>	
				<div class="portlet-footer">
					<div class="portlet-footer-inner"></div>
				</div>
			</div>
		</div>
	</div>
</div>	
<script type="text/javascript">


$(document).ready(function(){
	var today = new Date();
	var headerTxt = "<spring:message code='customerReports.scheduledReports.listHeader.reportFields'/>";
	var timezone = 0 - today.getTimezoneOffset()/60;
	reportListGrid = new dhtmlXGridObject('reportListContainer');
	reportListGrid.setImagePath("<html:imagesPath/>gridImgs/");
	reportListGrid.setHeader(headerTxt);
	reportListGrid.setInitWidths("200,100,110,300");
	reportListGrid.setColAlign("left,left,left,left");
	reportListGrid.setColTypes("ro,ro,ro,ro");
	reportListGrid.setColSorting("str,date,str,na");
	reportListGrid.enableAutoWidth(true);
	reportListGrid.enableAutoHeight(true);
	reportListGrid.enableMultiline(true);
	reportListGrid.enableLightMouseNavigation(true);
	reportListGrid.setSkin("light");  
	reportListGrid.attachEvent("onXLS", function() {
	    document.getElementById('loadingNotification').style.display = 'block';
	});
	var isFistLoadFlag = true;
	reportListGrid.attachEvent("onXLE", function() {
	    if(isFistLoadFlag){
	        reportListGrid.setSortImgState(true, 1, 'desc');
	        isFistLoadFlag = false;
	    }
	    document.getElementById('loadingNotification').style.display = 'none';
	    
	  //Function for Responsive table
	  /*var hdTxt = headerTxt.split(',');
	  
		$("table.obj tr td:first-child").attr("data-title",hdTxt[0])
		.next().attr("data-title",hdTxt[1])
		.next().attr("data-title",hdTxt[2])
		.next().attr("data-title",hdTxt[3]);
		
		//Remove data-title attribute for inner td's of table
		$("table.obj td td").removeAttr("data-title");*/
		
	});
	reportListGrid.init();
	reportListGrid.setPagingSkin("bricks");
	reportListGrid.loadXML("<portlet:resourceURL></portlet:resourceURL>&timezone=" + timezone);
	
	reportListGrid.attachEvent("onMouseOver", function(id,index){
	    return false;
	});   
	
	
	
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
	
	});
</script>
<script>function openSendEmailPage(reportDefinitionName ,reportId){
	callOmnitureAction('Customer Reports', 'Report User Send Email' + reportDefinitionName);
   var  url ="<portlet:renderURL windowState='<%= LiferayWindowState.EXCLUSIVE.toString() %>'>" + 
    "<portlet:param name='action' value='showEmailPage' />" +
    "</portlet:renderURL>&reportDefinitionName=" + encodeURIComponent(reportDefinitionName)
	    +"&reportId="+reportId;

showOverlay();	
jQuery('#emailReport').load(url,function(){
	dialog=jQuery('#emailReport').dialog({
	autoOpen: false,
	title: "<spring:message code='customerReport.title.reportEmailNotification'/>",
	modal: true,
	draggable: false,
	resizable: false,
	position: 'center',
	height: 'auto',
	width: 600,
	open: function(){	
		jQuery('#emailReport').show();
		hideOverlay();
	},
	close: function(event,ui){
		hideOverlay();
		dialog.dialog('destroy');					 
		dialog=null;
		showSelect();
	}
	});
dialog.dialog('open');
});
                       
}</script>
<script type="text/javascript">
//---- Ominture script 
     portletName = "Report User Default View";
     addPortlet(portletName);
     pageTitle="Default view";
</script>
