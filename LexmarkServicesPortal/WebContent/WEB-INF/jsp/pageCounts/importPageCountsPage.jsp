<%@ include file="/WEB-INF/jsp/include.jsp"%>
<style type="text/css"><%@ include file="../../css/grid/dhtmlxgrid.css" %></style> 
<style type="text/css"><%@ include file="../../css/grid/skins/dhtmlxgrid_dhx_lex.css" %></style> 
<jsp:include page="/WEB-INF/jsp/includeGrid.jsp"/>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/mps.css"/>
<!--[if IE 7]>
       <style type="text/css">
input[type="text"], input[type="password"], input[type="button"], input[type="submit"] {
behavior: url(/LexmarkServicesPortal/WEB-INF/css/PIE.htc) !important;
}
</style>
<![endif]-->

<!--[if IE 8]>
       <style type="text/css">
input[type="text"], input[type="password"], input[type="button"], input[type="submit"] {
behavior: url(/LexmarkServicesPortal/WEB-INF/css/PIE.htc) !important;
}
</style>
<![endif]-->
	
<style type="text/css"><%@ include file="/WEB-INF/css/ci6.css" %></style>
<style type="text/css"><%@ include file="/WEB-INF/css/mps_ie7.css" %></style>  
<style type="text/css">
table.hdr,table.obj {
	width:100% !important;
}
</style>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/in-styles.css?version=<html:fileCacheVersion/>" />
<script type="text/javascript"><%@ include file="../../../js/grid/dhtmlxgrid.js"%></script>
<script type="text/javascript"><%@ include file="../../../js/grid/dhtmlxgridcell.js"%></script>
<script type="text/javascript"><%@ include file="../../../js/grid/dhtmlxcommon.js"%></script>

<!-- Added for defect no. 8321 -->
			<div id="content-wrapper-inner">
    <div class="journal-content-article" >
      <h1><spring:message code="meterRead.title.pageCounts"/></h1>
       <h2 class="step"><spring:message code="meterRead.label.fileImportScreen"/></h2>
     </div>
     <div>
				<h3 class="margin-left-15px"><strong><spring:message code="meterRead.label.note"/></strong></h3>
				</div>
				
     <!-- END -->
<div class="portlet-section-header"></div>
<div class="portlet-section-body">
	<portlet:actionURL var="actionUrl">
		<portlet:param name="action" value="doCSVUpload"/>
	</portlet:actionURL>
	<portlet:resourceURL var="templateUrl" id="downLoadHelpFileURL" />
	<form:form id="documentForm"  enctype="multipart/form-data" commandName="pageCountsImportForm" action="${actionUrl}" method="post" onsubmit="return validation();">
	<form:hidden path="submitToken"/>
	
			<html:statusBanner id="pageCountUpload"/>
			
			
				<div class="main-wrap">
					<div class="content">
						<table class="importTable" width="100%">
							<tr>
								<td colspan="2">	
									<nobr>	
									<label for="fileInput" class="labelBold"><spring:message code="meterRead.label.fileToImport" />&nbsp;</label>
									<br>	
									<span class="float-left">
										<input type="text" readonly="true" size="70" name="txtFilePath" id="txtFilePath">
									</span>
									<span class="float-left">	
										<button onmousemove="move(this);" id="btnUpload" type="button" class="button left-10px"><spring:message code="button.browse" /></button>
									</span>	
									</nobr>	
								</td>
							</tr>
							<tr>
								<td>
									<input type="file" id="fileContent" onchange="document.getElementById('txtFilePath').value=getFullPath(this);checkType();" name="fileContent" class="file-content" hidefocus/>
								</td>
							</tr>
							<tr>
								<td><br></td>
							</tr>
							<tr class="margin-top-10px">
								<td nowrap><spring:message code="meterRead.description.importPageCount"/></td>
							</tr>
						</table>
					</div> <!-- content-->
					<div class="portlet-wrap">
						<div class="portlet-header">
							<h3><spring:message code="pageCntUploadLegend.label.uploadFileFormatLegend"/></h3>
						</div><!-- portlet-header-->
						<div class="portlet-wrap-inner">
							<div id="divPageCountUploadTemplate" class="div-Page-Count-Upload-Template"></div>
						</div><!--portlet-wrap-inner-->
						<div class="portlet-footer">
							<div class="portlet-footer-inner">
						</div><!-- portlet-footer-inner -->
					</div><!--portlet-wrap-->	
					<div>
						<table width="97%">
							<tr>
								<td>
									&nbsp;
									<spring:message code="meterRead.description.predefinedHelp1"/>
									<a href="#" onClick="downLoadHelpFile();"><spring:message code="meterRead.description.predefinedHelp2"/></a>
									<spring:message code="meterRead.description.predefinedHelp3"/>
								</td>
							</tr>
							<tr>
								<td align="right" width="100%">
									<button class="button_cancel" type="button" onclick="goBack();" /><spring:message code="button.cancel"/></button>&nbsp;
									<button class="button" name="upload" type="submit"  ><spring:message code="button.submit"/></button>
								</td>
							</tr>
						</table>
					</div>	
				</div><!-- main-wrap-->
				</div>
				
				
		<textarea id="gridContent_xml" style="display:none;"><rows>
		   <row id="1">
			  <cell><spring:message code="pageCntUploadLegend.column.productLine"/></cell>
			  <cell><spring:message code="pageCntUploadLegend.example.productLine"/></cell>
			  <cell><spring:message code="button.yes"/></cell>
			  <cell><spring:message code="pageCntUploadLegend.description.productLine"/></cell>
		   </row>
		   <row id="2">
			  <cell><spring:message code="pageCntUploadLegend.column.serialNumber"/></cell>
			  <cell><spring:message code="pageCntUploadLegend.example.serialNumber"/></cell>
			  <cell><spring:message code="button.yes"/></cell>
			  <cell><spring:message code="pageCntUploadLegend.description.serialNumber"/></cell>
		   </row>
		   <row id="3">
			  <cell><spring:message code="pageCntUploadLegend.column.newPageCount"/></cell>
			  <cell><spring:message code="pageCntUploadLegend.example.newPageCount"/></cell>
			  <cell><spring:message code="button.yes"/></cell>
			  <cell><spring:message code="pageCntUploadLegend.description.newPageCount"/></cell>
		   </row>
		   <row id="4">
			  <cell><spring:message code="pageCntUploadLegend.column.newColorCount"/></cell>
			  <cell><spring:message code="pageCntUploadLegend.example.newColorCount"/></cell>
			  <cell><spring:message code="button.no"/></cell>
			  <cell><spring:message code="pageCntUploadLegend.description.newColorCount"/></cell>
		   </row>
		   <row id="5">
			  <cell><spring:message code="pageCntUploadLegend.column.a4LTPC"/></cell>
			  <cell><spring:message code="pageCntUploadLegend.example.a4LTPC"/></cell>
			  <cell><spring:message code="button.no"/></cell>
			  <cell><spring:message code="pageCntUploadLegend.description.a4LTPC"/></cell>
		   </row>
		   <row id="6">
			  <cell><spring:message code="pageCntUploadLegend.column.a4Color"/></cell>
			  <cell><spring:message code="pageCntUploadLegend.example.a4Color"/></cell>
			  <cell><spring:message code="button.no"/></cell>
			  <cell><spring:message code="pageCntUploadLegend.description.a4Color"/></cell>
		   </row>
		   <row id="7">
			  <cell><spring:message code="pageCntUploadLegend.column.a5LTPC"/></cell>
			  <cell><spring:message code="pageCntUploadLegend.example.a5LTPC"/></cell>
			  <cell><spring:message code="button.no"/></cell>
			  <cell><spring:message code="pageCntUploadLegend.description.a5LTPC"/></cell>
		   </row>
		   <row id="8">
			  <cell><spring:message code="pageCntUploadLegend.column.a5Color"/></cell>
			  <cell><spring:message code="pageCntUploadLegend.example.a5Color"/></cell>
			  <cell><spring:message code="button.no"/></cell>
			  <cell><spring:message code="pageCntUploadLegend.description.a5Color"/></cell>
		   </row>
		   <row id="9">
			  <cell><spring:message code="pageCntUploadLegend.column.scans"/></cell>
			  <cell><spring:message code="pageCntUploadLegend.example.scans"/></cell>
			  <cell><spring:message code="button.no"/></cell>
			  <cell><spring:message code="pageCntUploadLegend.description.scans"/></cell>
		   </row>
		     <row id="10">
			  <cell><spring:message code="pageCntUploadLegend.column.readDate"/></cell>
			  <cell>${pageCountsImportForm.currentDateStr}</cell>
			  <cell><spring:message code="button.yes"/></cell>
			  <cell><spring:message code="pageCntUploadLegend.description.readDate"/></cell>
		   </row>
		</rows>
      </textarea>
  
	</form:form>
</div>
</div>
<script type="text/javascript">
	
	templateGrid = new dhtmlXGridObject('divPageCountUploadTemplate');
	templateGrid.setImagePath("<html:imagesPath/>gridImgs/");
	templateGrid.setHeader("<spring:message code="meterRead.listHeader.importPageCount"/>");
	templateGrid.setInitWidths("300,200,100,400");
	templateGrid.setColTypes("ro,ro,ro,ro");
	templateGrid.setColAlign("left,left,left,left");
	templateGrid.setSkin("light");
	templateGrid.enableAutoHeight(true);
	templateGrid.init();
	templateGrid.parse(document.getElementById('gridContent_xml').value);
	
	function validation(){
		try{
			var v = document.getElementById('fileContent').value;
			if(!v){
				showError("<spring:message code='exception.pageCounts.filePathEmpty'/>","pageCountUpload",false);
				return false;
			}else if((document.getElementById("fileContent").value.indexOf(".csv") == -1)){
				return false;
			}
			//document.getElementById('upload').disabled = true;
			//document.getElementById('documentForm').submit();
		}catch(e){
			showError(e.message,"pageCountUpload",false);
		}
		return true;
	};
	function goBack(){
		 var backURL = "<portlet:renderURL></portlet:renderURL>";
		 location.href = backURL;
	};
	function checkType(){
		if(!document.getElementById("fileContent").value){
			showError("<spring:message code='exception.pageCounts.filePathEmpty'/>","pageCountUpload",false);
		}else if(document.getElementById("fileContent").value.indexOf(".csv") == -1){
			showError("<spring:message code='exception.pageCounts.wrongFileType'/>","pageCountUpload",false);
		}else
			clearMessage();
	};
	function downLoadHelpFile(){
		callOmnitureAction('Page Counts', 'Notification Admin Detail show begin date calendar');
		window.location.href="${templateUrl}";
	};
	function getFullPath(obj) 
    { 
        if(obj) 
        { 
            //ie 
            if (window.navigator.userAgent.indexOf("MSIE")>=1) 
            { 
                obj.select(); 
               var path = document.selection.createRange().text;
               /* Added for defect 11736 */
               var filename = path.replace(/^.*(\\|\/|\:)/, ''); 
			   return filename ;
			   /* End */
            } 
            else
            {  
                if(obj.files) 
                { // Changes done for Defect 3309
					var path = obj.value;   
			        var filename = path.replace(/^.*(\\|\/|\:)/, ''); 
			           return filename ; 
                //	return obj.files.item(0).fileName; 
                } 
                return obj.value;
            } 
        } 
    };
    function move(thisObject){
        var a= document.getElementById('fileContent');
        var offset = jQuery('#btnUpload').offset();
        a.style.left = offset.left + 'px';
        a.style.top = offset.top + 'px';
	};

    addPortlet('Page Count Import Page');
</script>