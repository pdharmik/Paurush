<% request.setAttribute("subTabSelected","createNewRequest"); %>
<%@ include file="/WEB-INF/jsp/common/subTab.jsp"%>

<jsp:include page="../../common/validationMPS.jsp" />
<jsp:include page="/WEB-INF/jsp/includeGrid.jsp"/>
<%@ page import = "com.lexmark.services.util.LexmarkSPOmnitureConstants" %>
<script type="text/javascript"><%@ include file="../../../../js/portletRedirection.js"%></script>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/mps.css"/>
<!--[if IE 7]>
	<style type="text/css"><%@ include file="/WEB-INF/css/mps_ie7.css" %></style>
<![endif]-->

<portlet:actionURL var='attachDocumentAction'>
	<portlet:param name='action' value='attachDocument' />
</portlet:actionURL>
<portlet:actionURL var='removeDocumentAction'>
	<portlet:param name='action' value='removeDocument' />
</portlet:actionURL>

<portlet:actionURL var='confirmRequestAction'>
	<portlet:param name='action' value='confirmRequest' />
</portlet:actionURL>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/in-styles.css?version=<html:fileCacheVersion/>" />
<script type="text/javascript">
//var fileNameVar = "";
 var portlet_name="<%=LexmarkSPOmnitureConstants. CHL%>";
     var action_primary="<%=LexmarkSPOmnitureConstants. CHLSELECTADIFFERENTCONTACT%>";
     var action_secondary="<%=LexmarkSPOmnitureConstants.CHLADDADDITIONALCONTACT%>";
     
var contactListType="";
var combo = "";

function uploadFile() {
	
	var fileCount = document.getElementById("fileCount").value;
	var chlTempMaxCount = document.getElementById("chlTempMaxCount").value;
	var fileNameLocal = document.getElementById("txtFilePath").value;
	var errorList = "";
	var validFlag = 1;
	
	
	var fileNameLocal = document.getElementById("txtFilePath").value;
	if(fileNameLocal == ""){
		document.getElementById("validationErrors").style.display = 'none';
		document.getElementById("exceptionsDiv").style.display = 'none';
		errorList ="<li><strong><spring:message code='requestInfo.label.validation.attachmentEmpty'/></strong></li>";
		jQuery('#jsValidationErrors',window.parent.document).empty();
		document.getElementById("jsValidationErrors").innerHTML = errorList;
		document.getElementById("jsValidationErrors").style.display = 'block';
		window.parent.scrollTo(0,0);
		
		validFlag = 0;
	}
	else if(fileCount >= chlTempMaxCount){
		document.getElementById("validationErrors").style.display = 'none';
		document.getElementById("exceptionsDiv").style.display = 'none';
		errorList = errorList + "<li><strong><spring:message code='validation.fileUpload.template.fileCountExceeds.errorMsg'/></strong></li>";
		jQuery('#jsValidationErrors',window.parent.document).empty();
		document.getElementById("jsValidationErrors").innerHTML = errorList;
		document.getElementById("jsValidationErrors").style.display = 'block';
		window.parent.scrollTo(0,0);
		validFlag = 0;
	}
	else if(fileNameLocal.indexOf(".xls") == -1){
		document.getElementById("validationErrors").style.display = 'none';
		document.getElementById("exceptionsDiv").style.display = 'none';
		errorList = errorList + "<li><strong><spring:message code='validation.fileUpload.template.fileTypeInvalid.errorMsg'/></strong></li>";
		jQuery('#jsValidationErrors',window.parent.document).empty();
		document.getElementById("jsValidationErrors").innerHTML = errorList;
		document.getElementById("jsValidationErrors").style.display = 'block';
		window.parent.scrollTo(0,0);
		validFlag = 0;
	}
	if(validFlag==1){
		
		var link="<%=attachDocumentAction %>";
		document.getElementById('fileUploadFormId').target = 'upload_target';
		document.getElementById('fileUploadFormId').action = link;
		document.getElementById('pageType').value = "Template";
		jQuery('#fileUploadFormId').submit();

	}	
}

function removeFile(fileName) {
	//alert("fileName::"+fileName);
	var link="<%=removeDocumentAction %>";
	//alert(link);
	document.getElementById("fileCount").value = document.getElementById("fileCount").value - 1;
	document.getElementById('fileUploadFormId').target = 'upload_target';
	document.getElementById('fileUploadFormId').action = link + "&fileName=" + fileName;
	//alert("hello "+document.getElementById('fileUploadFormId').action);
	jQuery('#fileUploadFormId').submit();
}

function submitRequest(){
	
	//alert(document.getElementById("requestedEffectiveDate").value);
	var cmArea = document.getElementById('cmArea')[document.getElementById('cmArea').selectedIndex].value;
//	var cmSubArea="";
	document.getElementById("cmAreaValue").value=document.getElementById('cmArea')[document.getElementById('cmArea').selectedIndex].innerHTML;
	//alert(combo);
// 	if(combo!=""){
// 		cmSubArea = combo.getSelectedText();
//		//alert("cmSubArea:::"+cmSubArea+"::");
// 		document.getElementById("cmSubAreaValue").value=cmSubArea;
// 	}
	//alert(":::"+document.getElementById('cmSubAreaValue').value+":::");
	window.parent.document.getElementById("validationErrors").style.display = 'none';
	window.parent.document.getElementById("exceptionsDiv").style.display = 'none';
	var errorList = "";
	if(document.getElementById('cmArea').value==0 ){
		//alert("Please select an AREA.");
		errorList ="<li><strong><spring:message code='requestInfo.label.validation.subAreaMandatory'/></strong></li>";
		jQuery('#cmArea').addClass('errorColor');
	}
	if(document.getElementById('notesOrComments').value == ''){
		//alert("Please fill in the notes field.");
		errorList = errorList + "<li><strong><spring:message code='requestInfo.label.validation.notesMandatory'/></strong></li>";
		jQuery('#notesOrComments').addClass('errorColor');
	}
	if(document.getElementById('addtnlDescription').value.length > 2000){
		//alert("desc size exceeded" + document.getElementById('addtnlDescription').value.length);
		errorList = errorList + "<li><strong><spring:message code='validation.description.length.errorMsg'/></strong></li>";
	}
	// added by jyoti for effective date of change validation
	if(jQuery('#effDtOfChange').val()!=""&&!dateCheck(jQuery('#effDtOfChange').val()))
		{
			//alert(jQuery('#effDtOfChange').val());
			errorList=errorList + "<li><strong><spring:message code='validation.date.format.errorMsg'/></strong></li>";
			jQuery('#effDtOfChange').addClass('errorColor');		
		}
	if(document.getElementById('notesOrComments').value.length > 2000){
		//alert("notes size exceeded" + document.getElementById('notesOrComments').value.length);
		errorList = errorList + "<li><strong><spring:message code='validation.notes.length.errorMsg'/></strong></li>";
		jQuery('#notesOrComments').addClass('errorColor');
	}
	
	if(errorList != ""){
		jQuery('#jsValidationErrors',window.parent.document).empty();
		document.getElementById("jsValidationErrors").innerHTML = errorList;
		window.parent.document.getElementById("jsValidationErrors").style.display = 'block';
		window.parent.scrollTo(0,0);
		
	}else{
		var link="<%=confirmRequestAction %>";
		showOverlay();
		//alert(link);	
		document.getElementById('chlOthersFormId').action = link +"&forward=CHL" + "&timeZoneOffset=" + timezoneOffset;
		//alert("hello "+document.getElementById('chlOthersFormId').action);
		document.getElementById('chlOthersFormId').submit();
	}
	
}

function setPathToTextBox()
{
	filename=jQuery('#file').val();
	if(filename.match(/fakepath/)) {
		filename = filename.replace(/C:\\fakepath\\/i, '');
	}
	jQuery('#txtFilePath').val(filename);
	//fileNameVar = filename;
}

function clearAttachmentForm(){
    document.getElementById("fileUploadFormId").reset();
}

function openTemplate(type,fileName){
	//alert("type fileName "+ type +" "+fileName);
	if(type=='CHLTemplate'){
		window.location="${chlTemplateURL}&fileType=CHLTemplate";
	}else if(type=='Attachment'){
		window.location="${chlTemplateURL}&fileType=Attachment&fileName="+fileName;
	}
	
}
jQuery(document).ready(function() {
	var currentURL = window.location.href;
//	Change Account Link Hide/Show for CI-7 Defect #12274
	if(currentURL.indexOf('/partner-portal') == -1)
	  {	
		  jQuery('#changeaccount').show();
	  }
	else
		{
		jQuery('#changeaccount').hide();
		}
	jQuery('#cmArea,#notesOrComments').bind('mousedown focus',function(){
		jQuery(this).removeClass('errorColor');
	});
	
		c=1;
	var responseText = "";
	var fileCount = '<c:out value="${fileUploadForm.fileCount}" />';
	window.parent.document.getElementById("fileCount").value = fileCount;
	<c:if test="${fileUploadForm.fileMap != null}">
		jQuery('#fileListTable',window.parent.document).empty();
	 		responseText = '<thead><tr><th class="w15" id="fileData"></th>	<th><spring:message code="requestInfo.heading.attachmentFileName"/></th><th class="w120 right"><spring:message code="requestInfo.heading.attachmentFileSizeKiloBytes"/></th>	</tr></thead>';
		    responseText = responseText + '<tbody>';
	 		<c:forEach items="${fileUploadForm.fileMap}" var="entry">
	 		if(c%2==0){
 				responseText = responseText + '<tr class="altRow">';
 			}
 			else{
 				responseText = responseText + '<tr>';
 			}
 			c++;
	 			responseText = responseText + '<td class="w15"><img id="img_help1"  class="ui_icon_sprite ui-icon-closethick" src="<html:imagesPath/>transparent.png" alt="Remove" height="15" width="15" onclick=\'removeFile("${entry.key}");\' /></td>';
	 			responseText = responseText + '<td>'+ ' <a href="javascript:void(0);" onclick="openTemplate(\'Attachment\',\'${entry.key}\');"><c:out value="${entry.value.displayFileName}" /></a>' + '</td>';
	 			responseText = responseText + '<td class="right">'+ '<c:out value="${entry.value.fileSize}" />' + '</td>';
	 			
	 			responseText = responseText + '</tr>';
	 			
			</c:forEach>
			responseText = responseText + '</tbody>';
			jQuery('#fileListTable',window.parent.document).append(responseText);
	 		//alert("end :::"+ document.getElementById("test").innerHTML);
			
	</c:if>

	<c:if test="${fileUploadForm.fileMap == null}">
		jQuery('#fileListTable',window.parent.document).empty();
	</c:if>
	
	//alert(document.getElementById("errors"));
	jQuery('#validationErrors',window.parent.document).empty();
	//jQuery('#validationErrors').hide();
	window.parent.document.getElementById("validationErrors").style.display = 'none';
	if(document.getElementById("errors")!= null){
		window.parent.document.getElementById("jsValidationErrors").style.display = 'none';
		//jQuery('#jsValidationErrors',window.parent.document).empty();
		var divText = document.getElementById("errors").innerHTML;
		jQuery('#validationErrors',window.parent.document).append(divText);
		window.parent.document.getElementById("validationErrors").style.display = 'block';
		window.parent.scrollTo(0,0);
	}
	//alert('hiii ' + document.getElementById("dispalyExceptions"));
	jQuery('#exceptionsDiv',window.parent.document).empty();
	window.parent.document.getElementById("exceptionsDiv").style.display = 'none';
	if(document.getElementById("dispalyExceptions")!= null){
		window.parent.document.getElementById("jsValidationErrors").style.display = 'none';
		var divText = document.getElementById("dispalyExceptions").innerHTML;
		jQuery('#exceptionsDiv',window.parent.document).append(divText);
		window.parent.document.getElementById("exceptionsDiv").style.display = 'block';
		window.parent.scrollTo(0,0);
		//jQuery('#validationErrors').show();
	}
	
	jQuery("#processingHint",window.parent.document).css({
		display:"none"
	});
	jQuery("#overlay",window.parent.document).css({
		display:"none" 
	});
	

});


/* Added for CI7 BRD14-02-12 */
ajaxSuccessFunction=function updateRequest(){
		window.location.href="<portlet:actionURL><portlet:param name='action' value='showCHLOthersPage'/></portlet:actionURL>&friendlyURL="+populateURL("managechlothers?forward=CHL",[],[]);					
}
/* END */
</script>
 
<div id="content-wrapper">
    <div class="journal-content-article">
      <h1><spring:message code="requestInfo.cm.heading.changeRequest"/></h1>
      <h2 class="step"><spring:message code="requestInfo.cm.chl.label.customerHierarchy"/></h2> 
      </div>
      
      <!-- Added for CI7 BRD14-02-12 -->
      <div><h3><strong>
      <%@ include file="/WEB-INF/jsp/common/commonAccountAgreementNameInfo.jsp"%>
    	<c:if test='${sessionScope.accountCurrentDetails["rowCount"]>"1"}'>
        		<a href="#" onclick="javascript:showChangeAccountPopup();" class="edit" id="changeaccount"><spring:message code="requestInfo.link.changeAccount"/></a>
        	</c:if>
        	</strong>
        	</h3>
        	</div>
        	<!-- END -->
        	
      <div id="jsValidationErrors" class="error" style="display: none;"></div>
          <!-- Validation Errors displayed in the below div -->
           <spring:hasBindErrors name="fileUploadForm">
				<div class="error" id="errors">
					<c:forEach var="error" items="${errors.allErrors}">
						<li><strong><spring:message code="${error.code}"/></strong></li>
					</c:forEach>
				</div>	
			</spring:hasBindErrors>
          	<div id="validationErrors" class="error" style="display: none;"></div>
          	
          	<!-- Exceptions displayed in the below div -->
          	 <c:if test="${exceptionList != null}">
       			<div class="error" id="dispalyExceptions">
	       			<c:forEach items="${exceptionList}" var="entry">
			       		<li><strong>Exception exists - ${entry}</strong></li>		       		
			     	</c:forEach>
      			</div>
          	</c:if>
			<div id="exceptionsDiv" class="error" style="display: none;"></div>	

<div class="main-wrap">
	<div class="content">
			
		<!-- MAIN CONTENT BEGIN -->
          <h3 class="pageTitle"><spring:message code="requestInfo.heading.customerHierarchy"/><span><spring:message code="requestInfo.label.fieldsMarked"/> <span class="req">*</span><spring:message code="requestInfo.label.areRequired"/></span></h3>
 
		  <form:form id="chlOthersFormId" commandName="chlOthersForm" method="post" action="${createRequestAction}" enctype="multipart/form-data" >
		  <%--This jsp would display the contact of the requestor retrieved from Portal & Session,  
			Additional Information of the Change Mgmt Request End--%>
			<%@ include file="/WEB-INF/jsp/common/commonContactInfo.jsp"%>
	        
			 <span style="display: none">
				<form:input path="prevSRNumber" /> 
			 </span> 
			<hr class="separator" />		
					<p class="info"><spring:message code="requestInfo.cm.chl.label.pleaseChooseTypeOfRequest"/></p>
					<div class="portletBlock infoBox">
					<div class="columnsTwo">
					<div class="columnInner">
					<span style="display: none">
						<form:input path="cmAreaValue" /> 
<%-- 						<form:input path="cmSubAreaValue" /> --%>
					</span>
					<ul class="form">
	                  <li>
	                    <label for="rqstType"><spring:message code="requestInfo.label.subArea"/><span class="req">*</span></label>
	                   <span> <form:select path="cmArea" id="cmArea" multiple="false">
	                   		 <form:option value="0" >-<spring:message code="requestInfo.option.select"/>-</form:option>
	                    	 <c:forEach items="${chlSubAreaMap}" var="entry">
					       		 <form:option value="${entry.key}">${entry.value}</form:option>
					     	 </c:forEach>
	                    </form:select> </span>
	                    
	                    				
						<img id="img_help3" class="helpIcon ui_icon_sprite info-icon"  src="<html:imagesPath/>transparent.png"  title='<spring:message code="requestInfo.tooltip.manageCHL.subArea"/>'  /> <!--  Help Icon Title changed for 14.8 and 14.9 Translations -->
					  </li>
					
	                  <li>
	                    <label for="rqstDesc"><spring:message code="requestInfo.label.notes"/><span class="req">*</span></label>
	                  <span> <form:textarea path="notesOrComments" id="notesOrComments" rows="3" maxlength="2000"/></span>
					  </li>
	                </ul>
				</div>
				</div>
				</div>
			  
			  <span style="display: none">
					<form:input path="listOfFileTypes" id="listOfFileTypes"/>
					<form:input path="chlTempMaxCount" id="chlTempMaxCount"/>  
			  </span>
	          </form:form>
			 
				<p class="inlineTitle"><spring:message code="requestInfo.heading.attachments"/></p>
				<p><spring:message code="requestInfo.label.download"/> <a href="javascript:void(0);" onclick="openTemplate('CHLTemplate',null);"> <spring:message code="requestInfo.label.reqFromHere"/></a> <spring:message code="requestInfo.label.fillDetails"/></p>
			
			  <div class="columnInner">
			  
			  <form:form id="fileUploadFormId" commandName="fileUploadForm" method="post" action="${attachDocumentAction}" enctype="multipart/form-data" >
			
			    <span style="display: none">
					<form:input path="pageType" id="pageType"/> 
				</span>
			  	
			  		
			 	<nobr>
					<input type="text" id="txtFilePath" readonly="true" class="txtFilePath" />&nbsp;
					<form:input type="file" size="1" id="file" class="requestsUploader" path="fileData" onchange="setPathToTextBox();" onclick="clearAttachmentForm();"/>
					<button class="button_cancel cursor-pointer" type="button" id="btnBrowse" ><spring:message code="button.browse"/></button>&nbsp;&nbsp;					
					<button class="button_cancel cursor-pointer" type="button" id="btnUpload" onclick="uploadFile();"><spring:message code="button.attach"/></button>
				</nobr>
						 	
					<p class="note"><spring:message code="requestInfo.label.attachmentInstruction3"/></p>
									
					<iframe id="upload_target" name="upload_target" class="iframe-upload-target">
					
					</iframe>
					
						
						
						
						  <div id="fileListDiv" >
							
							<table id="fileListTable" class="wHalf displayGrid columnInnerTable rounded shadow">
							</table>
							<div id="test"></div>
							 <div class="lineClear"></div>							
						  </div>
							  
				
				
					
			 
			  </form:form>
 			  </div>
			  <div class="buttonContainer">
	            <button type="button" class="button_cancel" onclick="javascript:redirectToHistory('others');"><spring:message code="button.cancel"/></button>
	            <button type="button" class="button" onclick="submitRequest();" ><spring:message code="button.continue"/></button>
	          </div>
		  <input type="hidden" id="fileCount" />
		  
        </div>
        <!-- MAIN CONTENT END -->	
		</div>

</div>
