<%@ include file="/WEB-INF/jsp/include.jsp"%>
<jsp:include page="/WEB-INF/jsp/includeGrid.jsp"/>
<%@ page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>

<style type="text/css"><%@ include file="/WEB-INF/css/ci6.css" %></style>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/mps.css" />
<!--[if IE 7]>
	<style type="text/css"><%@ include file="/WEB-INF/css/mps_ie7.css" %></style>
<![endif]-->
<style>
.gridbox_light{width:100% !important}
</style>
<div id="dwnlCSVPageData" style="display: none;">
<jsp:include page="pageCountExportPage.jsp"></jsp:include>
</div>
<table width="1000px">
	<tr>
		<td>
		
		<!-- Added for defect no. 8321 -->
		<div id="content-wrapper-inner">
    <div class="journal-content-article" >
      <h1><spring:message code="meterRead.title.pageCounts"/></h1>
     </div>
     <div>
				<h3 style="margin-left:15px"><strong><spring:message code="meterRead.label.note"/></strong></h3>
				</div>
				
     <!-- END -->
			<div class="main-wrap">
				<div class="content">
					<table width="100%" style="background-color:#ededed;">
						<tr>
							<td><spring:message code="pageCounts.label.pageCountsDescription"/><br></td>
						</tr>
						<tr>
							<td height="5">&nbsp;</td>
						</tr>
						<tr>
							<td width="40%" style="padding: 0 30px;" class="labelBold"><spring:message code="pageCounts.label.importUpdatesFromAnExcelSpreadsheet"/><br></td>
							<td width="10%" style="padding: 0 30px;" class="labelBold">&nbsp;</td>
							<td width="40%" style="padding: 0 30px;" class="labelBold"><spring:message code="pageCounts.label.updateThroughOurWebInterface"/><br></td>
						</tr>
						<tr>
							<td style="padding: 0 30px;"><spring:message code="pageCounts.label.descriptionTextForImportUpdates"/><br></td>
							<td class="labelBold" align="center"><spring:message code="pageCounts.label.or"/></td>
							<td style="padding: 0 30px;"><spring:message code="pageCounts.label.descriptionTextForUtilizingTableBelow"/><br></td>
						</tr>
						<c:if test="${manualMeterReadFlag}">
						<tr>
							<td>
								<br>
							</td>
						</tr>
						<tr>
							<td style="padding: 0 30px;">
							<div class="buttonContainer floatL">
								<input type="hidden" name="exportPageCountUrl" id="exportPageCountUrl" value="${exportPageCountUrl}">
								<button class="button" onClick="popupDownLoadToExcel();hideSelect();"><spring:message code="pageCounts.label.downloadToExcel"/></button>&nbsp;&nbsp;
								<button class="button" onClick="upLoadExcel();"><spring:message code="pageCounts.label.uploadExcel"/></button>
							</div>
							
							</td>
							<td>&nbsp;</td>
							<td style="padding: 0 30px;">
							<div class="buttonContainer floatL">
								<button class="button" onClick="makeUpdatesOnline();"><spring:message code="pageCounts.label.makeUpdatesOnline"/></button>
							</div>
							</td>
						</tr>
						</c:if>
						<tr>
							<td height="5">&nbsp;</td>
						</tr>
					</table>
					<div class="portlet-wrap" style="margin:12px 0; border:1px solid #ededed;">
					
						
						<table class="displayGrid">
              <thead>
                <tr><th><spring:message code="pageCounts.label.importHistory"/></th>
                </tr></thead>
              
            </table>
						<!-- portlet-header-->
						<div class="portlet-wrap-inner">
							<table>
								<tr>
									<td colspan="3"><br>
										<div id="divPageCountsContainer" width="1000px"></div>
										<div id="loadingNotification" class='gridLoading'>
				    					<!--spring:message code='loadingNotification' />&nbsp;&nbsp;--><img src="<html:imagesPath/>gridloading.gif"/>
				   						</div>
									</td>
								</tr>
								<tr>
									<td colspan="3" width="100%">
										<span id="pagingArea"></span>&nbsp;<span id="infoArea"></span>
									</td>
								</tr>
							</table>
						</div><!--portlet-wrap-inner-->
						<div class="portlet-footer">
							<div class="portlet-footer-inner">
						</div><!-- portlet-footer-inner -->
					</div><!--portlet-wrap-->	
				</div><!-- content-->
			</div><!-- main-wrap-->	
			</div>
			
			<div id="fileWindow" style="display:none"><h4><spring:message code="pagecounts.fileerror"/><spring:message code="exception.contactAdmin"/></h4></div>
			<iframe id="fileFrame" style="display:none ;border: 0px"></iframe>
			</div>
			
		</td>
	</tr>
</table>
	
<script type="text/javascript">
	pageCountsGrid = new dhtmlXGridObject('divPageCountsContainer');
	pageCountsGrid.setImagePath("<html:imagesPath/>/gridImgs/");
	pageCountsGrid.setHeader(autoAppendPlaceHolder("<spring:message code="meterRead.listHeader.importHistory"/>",6));
	pageCountsGrid.attachHeader("#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter");
	pageCountsGrid.setInitWidths("200,80,140,140,100,250");
	pageCountsGrid.setColAlign("left,left,left,left,left,left");
	pageCountsGrid.setColTypes("ro,ro,ro,ro,ro,ro");
	pageCountsGrid.setColSorting("str,int,date,date,str,str");
	pageCountsGrid.setSkin("light");
	//pageCountsGrid.enableAutoWidth(true);
	pageCountsGrid.enableAutoHeight(true);
	pageCountsGrid.enablePaging(true, 20, 10, "pagingArea", true, "infoArea");
	pageCountsGrid.setPagingSkin("bricks");
	/* Added for defect no. 11351 */
	pageCountsGrid.setCustomSorting(sort_pageCount,4);
	/* End */
	pageCountsGrid.attachEvent("onXLS", function() {
        document.getElementById('loadingNotification').style.display = 'block';
    });
    pageCountsGrid.attachEvent("onXLE", function() {
        document.getElementById('loadingNotification').style.display = 'none';
        setTimeout(function(){
    		rebrandPagination();
    	
    	},100);
    });
   
	pageCountsGrid.init();
	pageCountsGrid.loadXML("${pageCountsLandingPageURL}");
	<%--
	function popupDownLoadToExcel(){
		//callOmnitureAction('Page Counts', 'Page Count Landing Page Download to Excel');
		new Liferay.Popup({
		title: "<spring:message code='pageCounts.title.exportPageCounts'/>",
		position: [300,150], 
		modal: true,
		width: '400', 
		height: 'auto',
		xy: [100, 100],
		onClose: function() {showSelect();},
		url:"<portlet:renderURL
			windowState='<%= LiferayWindowState.POP_UP.toString() %>'>" + 
			"<portlet:param name='action' value='pageCountExport' />" + 
			"</portlet:renderURL>"
		});
	};
--%>
<%--
var dwnlCSVPopUpWindow=null;



YUI().use('aui-base',
'aui-io-plugin-deprecated',
'liferay-util-window',
function(A) {
	
	dwnlCSVPopUpWindow = Liferay.Util.Window.getWindow(
	{
	dialog: {
	centered: true,
	constrain2view: true,
	modal: true,
	position: [-80,20],
	resizable: false,
	width: 400,
	height: "auto",
	xy: [-80, 20],
	destroyOnClose: true,
	visible: false
	}
	}
	).plug(
	A.Plugin.IO,
	{
	autoLoad: false
	});
	alert(dwnlCSVPopUpWindow);
});

function popupDownLoadToExcel(){
	alert("popupDownLoadToExcel 0");
	jQuery(window).scrollTop(0);
	alert("popupDownLoadToExcel 1");
	dwnlCSVPopUpWindow.show();
	alert("popupDownLoadToExcel 2");
	jQuery(".aui button.close, .aui button.btn.close").hide();
	dwnlCSVPopUpWindow.titleNode.html("<spring:message code='pageCounts.title.exportPageCounts'/>");
	dwnlCSVPopUpWindow.io.set('uri',"<portlet:renderURL windowState='<%= LiferayWindowState.POP_UP.toString() %>'><portlet:param name='action' value='pageCountExport' /></portlet:renderURL>");
	dwnlCSVPopUpWindow.io.start();
	}
	--%>
	
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
	function upLoadExcel(){
		callOmnitureAction('Page Counts', 'Page Count Landing Page Upload');
		clearMessage();
		window.location.href="<portlet:renderURL><portlet:param name='action' value='importPageCounts'/></portlet:renderURL>";
	};
	function makeUpdatesOnline(){
		callOmnitureAction('Page Counts', 'Page Count Landing Page Update online');
		window.location.href="<portlet:renderURL><portlet:param name='action' value='pageCountsList'/></portlet:renderURL>";
	};
	
	
	var dialogFrame;
	function initializeDialog(){
		dialogFrame=jQuery('#fileWindow').dialog({
			autoOpen: false,
			title: '<spring:message code='meterRead.label.meterReadError'/>',
			modal: true,
			resizable: false,
			position: 'center',
			height: 'auto',
			width: 400,
			open: function(){
				jQuery('#fileWindow').show();
			},
			close: function(event,ui){
				hideOverlay();
				dialogFrame.dialog('destroy');					 
				dialogFrame=null;
			}
		});
	}
	
	var frame;
	function outPutFile(fileName){
		$('#fileFrame').contents().find("html").html('');
		initializeDialog();
		jQuery('#fileFrame').attr('src',"${outPutFileURL}&fileName="+fileName);
		checkForFailure();
			
		}
	var errorFlag;
	function checkForFailure(){
		frame=$('#fileFrame');
		frame.load(function(){
		showOverlay();	
		var htmlContent=frame.contents().find("html").html();
		
		if(htmlContent.indexOf("File not found")!=-1){
			dialogFrame.dialog('open');
			}
		});
	}
	/* Added for defect no. 11351 */
	function sort_pageCount(valA,valB,order){
		
		valA=valA.indexOf('>')==-1?valA:jQuery(valA).text();
		valB=valB.indexOf('>')==-1?valB:jQuery(valB).text();
		
		return checkAndreturn(valA,valB,order);
	}	
	function checkAndreturn(valA,valB,order){
		
		if(order=="asc")
			return valA.trim()>valB.trim()?1:-1;
		else
			return valA.trim()<valB.trim()?1:-1;
	}
	
	
	var dialog;
	function popupDownLoadToExcel(){
		
				
				showOverlay();
		
			dialog=jQuery('#totalContent').dialog({
				autoOpen: false,
				title: '<spring:message code='pageCounts.title.exportPageCounts'/>',
				modal: true,
				draggable: false,
				resizable: false,
				position: 'center',
				height: 'auto',
				width: 400,
				open: function(){
					
					jQuery('#totalContent').show();
				},
				close: function(event,ui){
					hideOverlay();
					dialog.dialog('destroy');					 
					dialog=null;
					
					}
				
				});
			//jQuery(document).scrollTop(0);
			dialog.dialog('open');
		
				
	
	}
</script>
