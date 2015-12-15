<%@ include file="/WEB-INF/jsp/include.jsp"%>
<%@ include file="/WEB-INF/jsp/includeGrid.jsp"%>
<%@ page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/mps.css?version=<html:fileCacheVersion/>" />
<style type="text/css"><%@ include file="/WEB-INF/css/ci6.css" %></style>
<!--[if IE 7]>
	<style type="text/css"><%@ include file="/WEB-INF/css/mps_ie7.css" %></style>
<![endif]-->
<style type="text/css">
.displayGrid{line-height:20px;}
</style>
<table width="100%">
	<tr>
		<td>
			<div class="main-wrap">
			<div class="journal-content-article">
      <h1><spring:message code="bulkUpload.heading.requestsUploader"/></h1>
      </div>
			
				<div class="content">
					<table width="100%">
						<tr>
							<td><spring:message code="bulkUpload.label.bulkUploadDescription"/><br></td>
						</tr>
						<tr>
							<td height="5">&nbsp;</td>
						</tr>
						<tr>
							<td width="40%" class="labelBold"><spring:message code="bulkUpload.label.importUpdatesFromAnExcelSpreadsheet"/><br></td>
							<td width="10%" class="labelBold">&nbsp;</td>
							<td width="40%" class="labelBold">&nbsp;<br></td>
						</tr>
						<tr>
							<td><spring:message code="bulkUpload.label.descriptionTextForImportUpdates"/><br></td>
							<td class="labelBold" align="center">&nbsp;</td>
							<td>&nbsp;<br></td>
						</tr>
						
						<tr>
							<td>
								<br>
							</td>
						</tr>
						<tr>
							<td>
								<button class="button" onClick="upLoadExcel();"><spring:message code="bulkUpload.label.uploadExcel"/></button>
								
							</td>
							<td>&nbsp;</td>
							<td>
								&nbsp;
							</td>
						</tr>
						
						<tr>
							<td height="5">&nbsp;</td>
						</tr>
					</table>
					<div class="portlet-wrap">
						<div class="portlet-header">
						<table class="displayGrid">
						  <thead>
						     <tr>
						     	<th>
						     		<spring:message code="bulkUpload.label.importHistory"/>
						     	</th>
						      </tr>
						  </thead>
						</table>
						
						</div><!-- portlet-header-->
						<div class="portlet-wrap-inner">
							 <table>
								<tr>
									<td colspan="3"><br>
										<div id="divBulkUploadContainer" width="1000px"></div>
										<div id="loadingNotification" class='gridLoading'>
				    					<br><!--spring:message code="label.loadingNotification"/-->&nbsp;&nbsp;<img src="<html:imagesPath/>gridloading.gif"/><br>
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
		</td>
	</tr>
</table>
<script type="text/javascript">
	bulkUploadGrid = new dhtmlXGridObject('divBulkUploadContainer');
	bulkUploadGrid.setImagePath("<html:imagesPath/>gridImgs/");
	bulkUploadGrid.setHeader(autoAppendPlaceHolder("<spring:message code="bulkUpload.listHeader.importHistory"/>",7));
	bulkUploadGrid.attachHeader("#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter");
	bulkUploadGrid.setInitWidths("200,80,80,140,140,100,250");
	bulkUploadGrid.setColAlign("left,left,left,left,left,left,left");
	bulkUploadGrid.setColTypes("ro,ro,ro,ro,ro,ro,ro");
	bulkUploadGrid.setColSorting("str,int,str,str,str,str,str");
	bulkUploadGrid.setSkin("light");
	//pageCountsGrid.enableAutoWidth(true);
	bulkUploadGrid.enableAutoHeight(true);
	bulkUploadGrid.enablePaging(true, 20, 10, "pagingArea", true, "infoArea");
	bulkUploadGrid.setPagingSkin("bricks");
	bulkUploadGrid.attachEvent("onXLS", function() {
        document.getElementById('loadingNotification').style.display = 'block';
    });
	bulkUploadGrid.attachEvent("onXLE", function() {
        document.getElementById('loadingNotification').style.display = 'none';
        setTimeout(function(){
    		rebrandPagination();
    	
    	},100);
    });
   
	bulkUploadGrid.init();
	bulkUploadGrid.loadXML("${bulkUploadLandingPageURL}");

	function upLoadExcel(){
		callOmnitureAction('Bulk Upload', 'Bulk Upload Landing Page Upload');
		clearMessage();
		window.location.href="<portlet:renderURL><portlet:param name='action' value='importBulkUpload'/></portlet:renderURL>";
	};
	function outPutFile(fileName){
		 window.open("${outPutFileURL}&fileName=" + fileName + "&timezoneOffset=" + timezoneOffset, "");
	};
</script>
