<%@ include file="/WEB-INF/jsp/include.jsp"%>
<style type="text/css"><%@ include file="/WEB-INF/css/ci6.css" %></style>
<!--[if IE 7]>
	<style type="text/css"><%@ include file="/WEB-INF/css/mps_ie7.css" %></style>
<![endif]-->
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/in-styles.css?version=<html:fileCacheVersion/>" />
<div class="portlet-section-header"></div>
<div class="portlet-section-body">
	<portlet:actionURL var="actionUrl">
		<portlet:param name="action" value="doCSVUpload"/>
	</portlet:actionURL>
	<portlet:resourceURL var="templateUrl" id="downLoadHelpFileURL" />
	<form:form id="documentForm"  enctype="multipart/form-data" commandName="bulkUploadForm" action="${actionUrl}" method="post" onsubmit="return validation();">
	<form:hidden path="submitToken"/>
	<form:hidden path="timezoneOffset"/>

	
	<table class="importTable8" width="1000px">
		<tr>
			<td>
			<html:statusBanner id="bulkUpload"/>
			
				<div class="main-wrap">
				<div class="journal-content-article">
      <h1>Requests Uploader</h1>
      </div>
				
					<div class="content">
						<table class="importTable" width="100%">
							<tr>
								<td colspan = '2'>
									<nobr>
										<LABEL class="labelBold" for="fileInput"><spring:message code="bulkUploadLegend.label.fileToImport"/>:&nbsp;</LABEL><br>
										<input type="text" id="txtFilePath" name="txtFilePath" size ="70" readonly="true" class="margin-right-5px"/>
										<button class="button" type="button" id="btnUpload" onmousemove="move(this);"><spring:message code="button.browse"/></button>
										<input type="file" id="fileContent" class="requestsUploader fileContent" onchange="document.getElementById('txtFilePath').value=getFullPath(this);checkType();" name="fileContent" hidefocus/>
									</nobr>
								</td>
							</tr>

							<tr>
								<td><br></td>
							</tr>
							<tr class="margin-top-10px">
								<td nowrap><spring:message code="bulkUpload.description.importPageCount"/></td>
							</tr>
						</table>
					</div> <!-- content-->
					<div>
						<table width="100%">
							<!-- 
							<tr>
								<td>
									&nbsp;
									<spring:message code="bulkUpload.description.predefinedHelp1"/>
									<a href="#" onClick="downLoadHelpFile();"><spring:message code="bulkUpload.description.predefinedHelp2"/></a>
									<spring:message code="bulkUpload.description.predefinedHelp3"/>
								</td>
							</tr>
							-->
							<tr>
								<td align="right" width="100%">
									<button class="button_cancel" type="button" onclick="goBack();" /><spring:message code="button.cancel"/></button>&nbsp;
									<button class="button" name="upload" type="submit" /><spring:message code="button.submit"/></button>
								</td>
							</tr>
						</table>
					</div>	
				</div><!-- main-wrap-->
			</td>
		</tr>	
		</table>
	</form:form>
</div>
<script type="text/javascript">
document.getElementById('timezoneOffset').value = timezoneOffset;
function validation(){
		callOmnitureAction('Bulk Upload', 'Bulk Upload Upload Page');
		try{
			var v = document.getElementById('fileContent').value;
			if(!v){
				showError("<spring:message code='exception.bulkUpload.filePathEmpty'/>","bulkUpload",false);
				return false;
			}else if((document.getElementById("fileContent").value.indexOf(".csv") == -1)){
				return false;
			}
			//document.getElementById('upload').disabled = true;
			//document.getElementById('documentForm').submit();
		}catch(e){
			showError(e.message,"bulkUpload",false);
		}
		return true;
	};
	function goBack(){
		 var backURL = "<portlet:renderURL></portlet:renderURL>";
		 location.href = backURL;
	};
	function checkType(){
		if(!document.getElementById("fileContent").value){
			showError("<spring:message code='exception.bulkUpload.filePathEmpty'/>","bulkUpload",false);
		}else if(document.getElementById("fileContent").value.indexOf(".csv") == -1){
			showError("<spring:message code='exception.bulkUpload.wrongFileType'/>","bulkUpload",false);
		}else
			clearMessage();
	};
	function downLoadHelpFile(){
		callOmnitureAction('Bulk upload', 'Notification Admin Detail show begin date calendar');
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
			   return path ; 
           } 
           else
           {  
               if(obj.files) 
               { 
					return obj.files.item(0).fileName; 
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
	</script>