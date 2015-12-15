<%@ include file="/WEB-INF/jsp/include.jsp"%>
<%@ include file="/WEB-INF/jsp/includeGrid.jsp"%>

<style>
	#reportListContainer{
		width:100% !important;
	}
	div.objbox {
	    overflow-x: auto !important;
	}
	div.buttonsPanel {
		padding: 25px 0;
		overflow: hidden;		
	}
	.right-column {
		padding: 5px 0 0 0;
	}
	
	/*div.right-column{overflow:hidden;margin-left:0;padding-left: 5px;}
	#reports-banner,div.portlet-wrap{width:auto;}*/
</style>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/mps.css"/>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/in-styles.css?version=<html:fileCacheVersion/>" />
<div id="emailReport" style="display:none"></div>

<div class="main-wrap">
<div class="journal-content-article">
      <h1>Reports</h1>
</div>


	<div class="content">
	    <html:statusBanner id="scheduleStatus"/>
	    <style type="text/css"><%@ include file="/WEB-INF/css/ci6.css" %></style>
<!--[if IE 7]>
	<style type="text/css"><%@ include file="/WEB-INF/css/mps_ie7.css" %></style>
<![endif]-->

		<%@ include file="/WEB-INF/jsp/customerReports/leftPanel.jsp"%>
		<div class="right-column"><!-- col-xs-12 col-sm-9 col-md-9"-->				
			<div class="portlet-wrap">
				<div class="rounded shadow">
					<!-- <h3>${definitionDisplayForm.definitionName}</h3> -->	<!-- commented for employee report -->
					<!-- added for employee report -->
					<c:if test="${not isEmployee}">
					<table class="displayGrid">
              <thead>
                <tr><th>${definitionDisplayForm.definitionName}</th>
                </tr></thead>
              
            </table>
						
					</c:if>
					<c:if test="${isEmployee}">
					<table class="displayGrid">
              <thead>
                <tr><th>${definitionDisplayForm.definitionName} - ${accountName}</th>
                </tr></thead>
              
            </table>
						
					</c:if>
					<!-- end of addition for employee report -->
				</div>		
				<div class="portlet-wrap-inner">
					<table >
				<!-- Changes for 14.9 CR 14720 -->
					  <tr><td width="800px" class="td-padding1">
					<span> ${definitionDisplayForm.definitionDescription}</span>
					  </td></tr>
					</table>
					<!-- c:if test="${not isEmployee}" -->      <!-- commented -->
						<div class="buttonContainer text-align-left">
							<!--<a href="javascript:reportRunNow();" style="color:#FFF;text-decoration: none;" class="button"><spring:message code="customerReports.scheduledReports.label.runReport"/></a>
								&nbsp;&nbsp;&nbsp;-->
							<a href="javascript:openScheduleWindow();" class="button anchor-button1"><spring:message code="customerReports.scheduledReports.label.runReport"/></a>									
						
							<!--  <button style="color:#FFF;text-decoration: none;" class="button" onClick="upLoadExcel();"><spring:message code="pageCounts.label.uploadExcel"/></button> --> <!-- Commented by Arunava for LEX:AIR00072078 -->
						
						</div>
					<!--/c:if -->
					<div id="reportListContainer"></div>
					<div id="loadingNotification" class='gridLoading'>
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
		        <portlet:actionURL var="runNowReport">
		        	<portlet:param name="action" value="runNowReport"></portlet:param>
		    	</portlet:actionURL>
				<form:form id="runNowForm" commandName="runReportNowForm" method="post" action="${runNowReport}">
				    <form:hidden path="submitToken"/>
				    <form:hidden path="roleCategoryId"/>
                    <form:hidden path="docDefinitionId"/>
				</form:form>
			</div>
		</div>	
	</div>	
	<div id="dialog" style="display: none;"></div> 
	<div id="content_param_alert" style="display: none;">
		<div class="copy">
            <p><img src="/LexmarkServicesPortal/images/jQueryAlert/important.gif" width="32" height="32" alt="Alert " class="img-alert"><span id="runNowFailedMsg"></span></p>
            <div class="buttonContainer">
            <button name="Cancel" class="button_cancel" type="button" onclick="javascript:closeDialog();"><spring:message code="button.cancel"/></button>
           	<button name="Ok" class="button" type="button" onClick="javascript:closeDialog();openScheduleWindow();"><spring:message code="button.ok"/></button>
           	</div>
        </div>
	</div>
</div>
				<script type="text/javascript">
				var dialog;
					var runNowFailed = '${runNowFailed}';
					if(runNowFailed){
						//alert('runNowFailed');
						//showError(runNowFailed, null, true);
						jQuery('#runNowFailedMsg').html(runNowFailed);
								showOverlay();
								jQuery('#content_param_alert').show();
								dialog=jQuery('#content_param_alert').dialog({
								autoOpen: false,
								modal: true,
								draggable: false,
								resizable: false,
								height: 150,
								width: 500,
								close: function(event,ui){
									hideOverlay();
									dialog=null;
									jQuery('#content_param_alert').remove();
									}
								});
							dialog.dialog('open');
						
					}
					var runNowSuccess = '${runNowSuccess}';
					if(runNowSuccess){
						//alert('runNowSuccess');
						showMessage(runNowSuccess, null, true);
					}
					var headerTxt = "<spring:message code='customerReports.scheduledReports.listHeader.reportFields'/>";
					reportListGrid = new dhtmlXGridObject('reportListContainer');
					reportListGrid.setImagePath("<html:imagesPath/>gridImgs/");
					reportListGrid.setHeader(headerTxt);
					reportListGrid.setInitWidths("*,100,100,*");
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
				            reportListGrid.setSortImgState(true, 1, 'asc');
				            isFistLoadFlag = false;
				        }
				        document.getElementById('loadingNotification').style.display = 'none';
				        
				     /* //Function for Responsive table
				  	  var hdTxt = headerTxt.split(',');
				  	  
				  		$("table.obj tr td:first-child").attr("data-title",hdTxt[0])
				  		.next().attr("data-title",hdTxt[1])
				  		.next().attr("data-title",hdTxt[2])
				  		.next().attr("data-title",hdTxt[3]);
				  		
				  		//Remove data-title attribute for inner td's of table
				  		$("table.obj td td").removeAttr("data-title");*/
				    });
				    reportListGrid.init();
				    reportListGrid.setPagingSkin("bricks");
				    reportListGrid.loadXML("${definitionDisplayForm.definitionURL}&definitionId=${definitionDisplayForm.definitionId}&timezone=" + timezone);
				
				    reportListGrid.attachEvent("onMouseOver", function(id,index){
				        return false;
				    }); 


					function openScheduleWindow() {
						clearMessage();
						url ="<portlet:renderURL windowState='<%= LiferayWindowState.POP_UP.toString() %>'>" + 
							"<portlet:param name='action' value='showScheduleReportPage' />" +
							"</portlet:renderURL>&definitionId=${definitionDisplayForm.definitionId}";
					    var iWidth=900;
					    var iHeight=600;
					    var iTop = (window.screen.availHeight-30-iHeight)/2;
					    var iLeft = (window.screen.availWidth-10-iWidth)/2;
					    callOmnitureReportRunAction('${definitionDisplayForm.definitionName}');
					    window.open(url,
					    		'MeterReadList',
							    'height='+iHeight+
							    ',innerHeight='+
							    iHeight+',width='+
							    iWidth+',innerWidth='+
							    iWidth+',top='+iTop+
							    ',left='+iLeft+
							    ',status=no,toolbar=no,menubar=no,location=no,resizable=yes,scrollbars=yes,titlebar=no');
					};	


					function reportRunNow(){
						var runNowForm = document.getElementById('runNowForm');
						callOmnitureReportRunAction('${definitionDisplayForm.definitionName}');
						runNowForm.submit();
					}


				
					 
					 function openSendEmailPage(reportDefinitionName ,reportId){
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
					
					//PARTHA ADDED FOR PAGE REFRESH START
					function refreshPage(doc_id_refresh, role_id_refresh){
							clickReportDefinition(role_id_refresh, doc_id_refresh);
					}
					var needRefresh = '${refreshStatus}';	
					if(needRefresh && needRefresh == 'success'){						
						showMessage("<spring:message code='customerReports.scheduleReport.success'/>","scheduleStatus");
					}else if(needRefresh && needRefresh =='failed') {showError("<spring:message code='customerReports.scheduleReport.failure'/>","scheduleStatus");}
					//PARTHA ADDED FOR PAGE REFRESH END
					
					function closeDialog()
					{
						dialog.dialog('close');			
						dialog=null;
						jQuery('#content_param_alert').remove();
					}
				</script>
<script type="text/javascript">
//---- Ominture script 
     portletName = "Schedule reports list";
     addPortlet(portletName);
     pageTitle="Schedule reports list";
</script>