<%@ taglib uri="/WEB-INF/tld/liferay-portlet.tld" prefix="portlet"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="html" tagdir="/WEB-INF/tags/html" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/mps.css"/>
<!--[if IE 7]>
	<style type="text/css"><%@ include file="/WEB-INF/css/mps_ie7.css" %></style>
<![endif]-->
<script type="text/javascript" src="<html:rootPath/>js/expand.js"></script>
	<portlet:actionURL var="addAttachmentsCreate">
	<portlet:param name="action" value="addAttachmentsCreate"/>
</portlet:actionURL>
<portlet:actionURL var="removeAttachmentURL">
	<portlet:param name="action" value="removeAttachment"/>
</portlet:actionURL> 
<portlet:resourceURL var="displayAttachmentURL" id="displayAttachment">	
</portlet:resourceURL>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/in-styles.css?version=<html:fileCacheVersion/>" />
<div class="portletBlock infoBox rounded shadow">
									<div class="columnsOne">
										<div class="columnInner">
											<h4 class="expand"><spring:message code="requestInfo.heading.addNotesAndAttachments"/></h4>
												<div class="collapse" id="showAttachment">
			<div class="infoBox">	

					<form:form id="attachmentForm" name="attachmentForm" commandName="attachmentForm" method="post" enctype="multipart/form-data">
					<div class="columnInner">
					<div class="columnsTwo">
					<ul class="form">
						<li>
							<label for="attachDesc"><spring:message code="requestInfo.label.attachmentNotes"/>:</label> 
							<span class="multiLine"><form:textarea id="attachmentDescription" rows="3" path="attachmentDescription" maxlength="2000"/></span>
						</li>
					</ul>
					</div>
					
			</div>
			<p class="inlineTitle"><spring:message code="requestInfo.label.attachFiles"/></p>
			<br>
			<div id="fileErrors" class="error"  style="display: none;"></div>
						<input type="text" id="txtFilePath" readonly="readonly"/>
						<form:input type="file" id="files" size="1" class="requestsUploader" path="fileData" onchange="setPathToTextBox();" />
						<button type="button" id="btnBrowse" class="button_cancel relativeP"><span><spring:message code="button.browse"/></span></button>&nbsp;
						<button class="button_cancel relativeP cursor-pointer" type="button" id="btnUpload" onclick="uploadFile();"><spring:message code="button.attach"/></button>
						
						<span style="display: none;">
         <form:input path="listOfFileTypes" id="listOfFileTypes"/>
		<form:input id="attMaxCount" path="attMaxCount"/>
		<input type="hidden" name="pageType" id="pageType"/>
		</span>
					</form:form>
					<p class="note" ><spring:message code="requestInfo.label.attachmentInstruction1"/>&nbsp;
					<spring:message code="requestInfo.label.attachmentInstruction2"/></p>
					<div class="wHalf displayGrid columnInnerTable rounded shadow">
					<table id="fileListTable"></table>
					</div>
					<iframe id="upload_target" name="upload_target" class="iframe-upload-target"></iframe>
					</div></div></div></div></div>
					<!-- infoBox -->
			   
			  
<script type="text/javascript">
jQuery(function() {
    jQuery("h4.expand").toggler();
});

jQuery(document).ready(function(){
	var error;
	var fileCount = '<c:out value="${fileUploadForm.fileCount}" />';
	//alert("fileCount"+fileCount);
	window.parent.document.getElementById("fileCount").value = fileCount;
	<c:if test="${attachmentForm.attachmentList != null}">
	
	if('${fileSizeError}'=='error'){
		 error="<spring:message code='requestInfo.label.validation.fileSize'/>";
		jQuery('#fileErrors',window.parent.document).show();
		jQuery('#fileErrors',window.parent.document).append(error);
		
	}else{
		jQuery('#fileErrors',window.parent.document).hide();
		jQuery('#fileErrors',window.parent.document).empty();
	}
	
	<c:if test="${empty attachmentForm.attachmentList}">
	parent.hideOverlay();
	jQuery('#fileListTable',window.parent.document).empty();
	</c:if>
	<c:if test="${not empty attachmentForm.attachmentList}">
	parent.hideOverlay();
	jQuery('#fileListTable',window.parent.document).empty();
	responseText = '<thead><tr><th class="w15" id="fileData"></th><th><spring:message code="requestInfo.heading.attachmentFileName"/></th><th class="w120 right"><spring:message code="requestInfo.heading.attachmentFileSizeKiloBytes"/></th></tr></thead>';
	    responseText = responseText + '<tbody>';
	    var fileCountInJsp = 0;
 		<c:forEach items="${attachmentForm.attachmentList}" var="entry">
 		jQuery('#showAttachment').show();
	 		if(fileCountInJsp%2==0){
				responseText = responseText + '<tr>';
			}
			else{
				responseText = responseText + '<tr class="altRow">';
			}
			responseText = responseText + '<td class="w15"><img class="ui_icon_sprite trash-icon" id="img_help1" width="15" height="15"  src="<html:imagesPath/>transparent.png" style="cursor:pointer;" title="Delete" alt="Remove" onclick=\'deleteFile("${entry.attachmentName}");\' />'+'</td>';
			responseText = responseText + '<td>'+ ' <a href="javascript:void(0);" onclick="openAttachment(\'${entry.attachmentName}\');"><c:out value="${entry.displayAttachmentName}" /></a>' + '</td>';
 			responseText = responseText + '<td class="right">'+ '<c:out value="${entry.sizeForDisplay}" />' + '</td>';
 			responseText = responseText + '</tr>';
 			fileCountInJsp = fileCountInJsp + 1;
 			if(pageType=="hardwareDetails"){
 				<c:if test="${entry.displayAttachmentName == 'HardwareInstallTemplate.xls'}">
 				attachTemplateCheck=true;	 			
			</c:if>
 			}
		</c:forEach>
		if(pageType=="hardwareDetails"){
			if(attachTemplateCheck){
				jQuery('#templateFileCheck').val("true");
		}
		}
		responseText = responseText + '</tbody>';
		jQuery('#fileListTable',window.parent.document).append(responseText);
 		//alert("end :::"+ document.getElementById("test").innerHTML);
		jQuery('#txtFilePath',window.parent.document).val('');//Used to remove the filename when the upload is done
</c:if>
</c:if>
<c:if test="${attachmentForm.attachmentList == null}">
parent.hideOverlay();
	var fileCountInJsp = 0;
	jQuery('#fileListTable',window.parent.document).empty();
</c:if>
});
function uploadFile() {
	
	document.getElementById("jsValidationErrors").style.display = 'none';
	document.getElementById("validationErrors").style.display = 'none';
	document.getElementById("fileErrors").style.display = 'none';
	var fileCount =document.getElementById("fileCount").value;
	var validFlag = 1;
	var errorList = "";
	document.getElementById("fileErrors").innerHTML = '';
	document.getElementById("jsValidationErrors").innerHTML = '';
	
	document.getElementById("validationErrors").innerHTML = '';
	
	var listOfFileTypes = document.getElementById("listOfFileTypes").value;
	var maxAttCount = document.getElementById("attMaxCount").value;
	//alert("maxAttCount"+maxAttCount);
	var fileNameLocal = document.getElementById("txtFilePath").value;
	var fileExt = fileNameLocal.substring(fileNameLocal.lastIndexOf(".")+1, fileNameLocal.length).toLowerCase();
	<%-- This div has been renamed to errorMsgPopup_CatalogDtl because contact popup validations div id was conflicting --%>
	document.getElementById('errorMsgPopup_CatalogDtl').innerHTML = "";
	if(fileNameLocal == ""){
		errorList ="<li><strong><spring:message code='requestInfo.label.validation.attachmentEmpty'/></strong></li>";
		jQuery('#fileErrors',window.parent.document).empty();
		document.getElementById("fileErrors").innerHTML = errorList;
		document.getElementById("fileErrors").style.display = 'block';
		//window.parent.scrollTo(0,0);
		validFlag = 0;
	}
	else if(fileCount >= maxAttCount){
		errorList = errorList + "<li><strong><spring:message code='validation.fileUpload.fileCountExceeds.errorMsg'/></strong></li>";
		jQuery('#fileErrors',window.parent.document).empty();
		document.getElementById("fileErrors").innerHTML = errorList;
		document.getElementById("fileErrors").style.display = 'block';
		validFlag = 0;
	}
	else if(listOfFileTypes.indexOf(fileExt) == -1){		
		errorList = errorList + "<li><strong><spring:message code='validation.fileUpload.fileTypeInvalid.errorMsg'/></strong></li>";
		jQuery('#fileErrors',window.parent.document).empty();
		document.getElementById("fileErrors").innerHTML = errorList;
		document.getElementById("fileErrors").style.display = 'block';
		validFlag = 0;
	}
	
	
	if(validFlag==1) {
		showOverlay();
		document.getElementById("attachmentForm").target = 'upload_target';
		var link = "${addAttachmentsCreate}";
		jQuery('#pageType').val(pageType);
		if(pageType=="hardwareDetails"){
			if(fileNameLocal==jQuery('#templateFileName').val()){
				jQuery('#templateFileCheck',window.parent.document).val("true")
		}
			}
		document.getElementById("attachmentForm").action = link;
		jQuery("#attachmentForm").submit();
	}
	
};

function deleteFile(fileName) {
	showOverlay();
	// call action in controller to delete file
	var link = '${removeAttachmentURL}';
	document.getElementById('attachmentForm').target = 'upload_target';
	jQuery('#pageType').val(pageType);
	if(pageType=="hardwareDetails"){
		var modifiedFileName = fileName.substring(0, fileName.lastIndexOf('_'));
		if(jQuery('#templateFileName').val().substring(0, jQuery('#templateFileName').val().lastIndexOf('.'))==modifiedFileName){
			jQuery('#templateFileCheck',window.parent.document).val("false");
			}
		}
	document.getElementById('attachmentForm').action = link + "&fileName=" + fileName;
	jQuery('#attachmentForm').submit();
	
};
function setPathToTextBox()
{
	var path = document.getElementById('files').value;
	var pathChange=path.substring(path.lastIndexOf('\\')+1);	
	jQuery('#txtFilePath').val(pathChange);
	//fileNameVar = filename;
}
function openAttachment(fileName){
	window.location="${displayAttachmentURL}&fileName="+fileName;
	
}
</script>
			 