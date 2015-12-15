<% request.setAttribute("subTabSelected","createNewRequest"); %>
<%@ include file="/WEB-INF/jsp/common/subTab.jsp"%>
<%@page import="java.util.Map"%>

<jsp:include page="../../common/validationMPS.jsp" />
<jsp:include page="/WEB-INF/jsp/includeGrid.jsp"/>
<%@ page import = "com.lexmark.services.util.LexmarkSPOmnitureConstants" %>
<script type="text/javascript"><%@ include file="../../../../js/portletRedirection.js"%></script>
<script type="text/javascript" src="<html:rootPath/>js/grid/dhtmlxcommon.js"></script>
<script type="text/javascript" src="<html:rootPath/>js/combo/dhtmlxcombo.js"></script>
<script type="text/javascript" src="<html:rootPath/>js/combo/dhtmlxcombo_whp.js"></script>

<style type="text/css"><%@ include file="/WEB-INF/css/combo/dhtmlxcombo.css" %></style>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/mps.css"/>
<!--[if IE 7]>
	<style type="text/css"><%@ include file="/WEB-INF/css/mps_ie7.css" %></style>
	<style type="text/css">
       .ie7 .buttonOK {
               behavior: url(/LexmarkServicesPortal/WEB-INF/css/PIE.htc) !important;                
        }
        .buttonOK{position:static!important;}
       
</style>
<![endif]-->
<!--[if IE 8]>
	
<style type="text/css">
       .ie8 .buttonOK {
               behavior: url(/LexmarkServicesPortal/WEB-INF/css/PIE.htc) !important;                
        }
         .buttonOK{position:static!important;}
       
</style>
<![endif]-->

<style type="text/css">
ul.form img.dhx_combo_img {
	margin-right: -1px !important;
} /* Added for combo select image */
</style>

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
//var fileNameVar = null;
var portlet_name="<%=LexmarkSPOmnitureConstants.INQUIRY %>";
var action_primary="<%=LexmarkSPOmnitureConstants.INQUIRYSELECTADIFFERENTCONTACT %>";
var action_secondary="<%=LexmarkSPOmnitureConstants.INQUIRYADDADDITIONALCONTACT %>";
var contactListType="";
var combo = "";



function uploadFile() {
	
	var fileCount = document.getElementById("fileCount").value;
	var validFlag = 1;
	var errorList = "";

	var listOfFileTypes = document.getElementById("listOfFileTypes").value;
    if("${chlOthersForm.cmSubArea}"=="LBS Map Updates"){
		
		
		listOfFileTypes=listOfFileTypes+",png,jpg,jpeg,bmp,tif,tiff,gif,dib,jpe,jfif"
        jQuery('#pageType').val("mapRequest");
		
	}
	var maxAttCount = document.getElementById("attMaxCount").value;
	var fileNameLocal = document.getElementById("txtFilePath").value;
	var fileExt = fileNameLocal.substring(fileNameLocal.lastIndexOf(".")+1, fileNameLocal.length).toLowerCase();
	
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
	else if(fileCount >= maxAttCount){
		document.getElementById("validationErrors").style.display = 'none';
		document.getElementById("exceptionsDiv").style.display = 'none';
		
		errorList = errorList + "<li><strong><spring:message code='validation.fileUpload.fileCountExceeds.errorMsg'/></strong></li>";
		jQuery('#jsValidationErrors',window.parent.document).empty();
		document.getElementById("jsValidationErrors").innerHTML = errorList;
		document.getElementById("jsValidationErrors").style.display = 'block';
		window.parent.scrollTo(0,0);
		validFlag = 0;
	}
	else if(listOfFileTypes.indexOf(fileExt) == -1){
		document.getElementById("validationErrors").style.display = 'none';
		document.getElementById("exceptionsDiv").style.display = 'none';
		errorList = errorList + "<li><strong><spring:message code='validation.fileUpload.fileTypeInvalid.errorMsg'/></strong></li>";
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
		jQuery('#fileUploadFormId').submit();

	}	
}

function removeFile(fileName) {
	var link="<%=removeDocumentAction %>";
	document.getElementById("fileCount").value = document.getElementById("fileCount").value - 1;
	document.getElementById('fileUploadFormId').target = 'upload_target';
	document.getElementById('fileUploadFormId').action = link + "&fileName=" + fileName;
	//alert("hello "+document.getElementById('fileUploadFormId').action);
	jQuery('#fileUploadFormId').submit();
}

function submitRequest(){
	
	//alert(document.getElementById("requestedEffectiveDate").value);
	var cmArea = document.getElementById('cmArea')[document.getElementById('cmArea').selectedIndex].innerHTML;
	var cmSubArea="";
	document.getElementById("cmAreaValue").value=cmArea;
	//alert(combo);
	if(combo!=""){
		cmSubArea = combo.getSelectedText();
		//alert("cmSubArea:::"+cmSubArea+"::");
		document.getElementById("cmSubAreaValue").value=cmSubArea;
	}
	else{
		document.getElementById("cmSubAreaValue").value="-Select-";
	}
	//alert(":::"+document.getElementById('cmSubAreaValue').value+":::");
	window.parent.document.getElementById("validationErrors").style.display = 'none';
	window.parent.document.getElementById("exceptionsDiv").style.display = 'none';
	var errorList = "";
	if(document.getElementById('cmArea').value==0 ){
		errorList = "<li><strong><spring:message code='requestInfo.label.validation.areaMandatory'/></strong></li>";
		//Added for defect no. #1683
		jQuery('#cmArea').addClass('errorColor');
		//ends for defect no. #1683
		
		
	}
	// added by jyoti for effective date of change validation
	if(jQuery('#effDtOfChange').val()!=""&&!dateCheck(jQuery('#effDtOfChange').val()))
		{
			//alert(jQuery('#effDtOfChange').val());
			errorList=errorList + "<li><strong><spring:message code='validation.date.format.errorMsg'/></strong></li>";
			jQuery('#effDtOfChange').addClass('errorColor');		
		}
	if(document.getElementById('cmSubAreaValue').value == '-Select-'){
		errorList = errorList + "<li><strong><spring:message code='requestInfo.label.validation.subAreaMandatory'/></strong></li>";
		//Added for defect no. #1683
		jQuery('#rqstType').addClass('errorColor');
		jQuery('#comboId').addClass('errorColor');
		//ends for defect no. #1683
		
		
	}
	if(document.getElementById('notesOrComments').value == ''){
		//alert("Please fill in the notes field.");
		errorList = errorList + "<li><strong><spring:message code='requestInfo.label.validation.notesMandatory'/></strong></li>";
		//Added for defect no. #1683
		jQuery('#notesOrComments').addClass('errorColor');
		//ends for defect no. #1683
	}
	if(document.getElementById('addtnlDescription').value.length > 2000){
		//alert("desc size exceeded" + document.getElementById('addtnlDescription').value.length);
		errorList = errorList + "<li><strong><spring:message code='validation.description.length.errorMsg'/></strong></li>";
	}
	if(document.getElementById('notesOrComments').value.length > 2000){
		//alert("notes size exceeded" + document.getElementById('notesOrComments').value.length);
		errorList = errorList + "<li><strong><spring:message code='validation.notes.length.errorMsg'/></strong></li>";
		//Added for defect no. #1683
		jQuery('#notesOrComments').addClass('errorColor');
		//ends for defect no. #1683
	}
	if(cmArea == "Upload"){
		if(document.getElementById("fileCount").value=="0"){
			errorList = errorList + "<li><strong><spring:message code='attachment.exception'/></strong></li>";
			}
		}
	if(errorList != ""){
		jQuery('#jsValidationErrors',window.parent.document).empty();
		document.getElementById("jsValidationErrors").innerHTML = errorList;
		window.parent.document.getElementById("jsValidationErrors").style.display = 'block';
		window.parent.scrollTo(0,0);
		
	}else{
		var link="<%=confirmRequestAction %>";
		//alert(link);	
		document.getElementById('chlOthersFormId').action = link +"&forward=Others"+"&timeZoneOffset=" + timezoneOffset;
		//alert("hello "+document.getElementById('chlOthersFormId').action);
		document.getElementById('chlOthersFormId').submit();
		showOverlay();
	}
	
}

function createSubTypeCombo(reqTypeObj,subAreaVal){
	window.dhx_globalImgPath = "<html:imagesPath/>comboImgs/"
	document.getElementById("rqstSubTypeDiv1").style.display = 'none';
	document.getElementById("rqstSubTypeDiv2").innerHTML = '';
	document.getElementById("rqstSubTypeDiv2").style.display = 'block';
	combo=new dhtmlXCombo("rqstSubTypeDiv2","cmSubArea",227);
	//combo.enableOptionAutoWidth(true);
	combo.setOptionWidth(210);
	combo.enableOptionAutoHeight(true);
	
	jQuery('.dhx_combo_input').attr('id','comboId');
	jQuery('#comboId').bind('mousedown focus',function(){
		jQuery(this).removeClass('errorColor');
	});
	//ends for defect no. #1683
	
	var url = "${subAreaURL}" + "&area="+ reqTypeObj.value ;
	if(subAreaVal != null){
		url = url + "&subArea="+ subAreaVal ;
	}
	
	combo.loadXML(url);
	combo.readonly(true);	
	combo.attachEvent("onSelectionChange", onChangeFunc);
	
}
function onChangeFunc()
{
	
	var cmArea = document.getElementById('cmArea')[document.getElementById('cmArea').selectedIndex].innerHTML;
	if(cmArea == "Upload"){
		if(combo!=""){
			cmSubArea = combo.getSelectedText();
			if(cmSubArea == "Upload Supplies Request"){
				document.getElementById("masstemplatespan").style.display = 'block';
				document.getElementById("hwMasstemplatespan").style.display = 'none';
			}			
			}
		}else if(cmArea == "Inquiry"){
		if(combo!=""){
			cmSubArea = combo.getSelectedText();
			 if(cmSubArea == "Upload HW Requests"){
				document.getElementById("hwMasstemplatespan").style.display = 'block';
				document.getElementById("masstemplatespan").style.display = 'none';
			}else{
				document.getElementById("masstemplatespan").style.display = 'none';
				document.getElementById("hwMasstemplatespan").style.display = 'none';
			}
		}
	}else{
		document.getElementById("masstemplatespan").style.display = 'none';
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
	if(type=='CHLTemplate'){
		window.location="${chlTemplateURL}&fileType=CHLTemplate";
	}else if(type=='Attachment'){
		window.location="${chlTemplateURL}&fileType=Attachment&fileName="+fileName;
	}else if(type=='massUploadTemplate'){
		window.location="${chlTemplateURL}&fileType=massUploadTemplate&fileName="+fileName;
	}else if(type=='hwMassUploadTemplate'){
		window.location="${chlTemplateURL}&fileType=hwMassUploadTemplate&fileName="+fileName;
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
	jQuery('#cmArea,#rqstType,#notesOrComments').bind('mousedown focus',function(){
		jQuery(this).removeClass('errorColor');
	});
	
	c=1;
	var responseText = "";
	var fileCount = '<c:out value="${fileUploadForm.fileCount}" />';
	window.parent.document.getElementById("fileCount").value = fileCount;
	<c:if test="${fileUploadForm.fileMap != null}">
		jQuery('#fileListTable',window.parent.document).empty();
		responseText = '<thead><tr><th class="w15" id="fileData"></th><th><spring:message code="requestInfo.heading.attachmentFileName"/></th><th class="w120 right"><spring:message code="requestInfo.heading.attachmentFileSizeKiloBytes"/></th></tr></thead>';
		    responseText = responseText + '<tbody>';
	 		<c:forEach items="${fileUploadForm.fileMap}" var="entry">
	 			if(c%2==0){
	 				responseText = responseText + '<tr class="altRow">';
	 			}
	 			else{
	 				responseText = responseText + '<tr>';
	 			}
	 			c++;
	 			responseText = responseText + '<td class="w15"><img id="img_help1" class="ui_icon_sprite ui-icon-closethick"  src="<html:imagesPath/>transparent.png" alt="Remove" height="15" width="15" onclick=\'removeFile("${entry.key}");\' /></td>';
	 			responseText = responseText + '<td>'+ ' <a href="javascript:void(0);" onclick="openTemplate(\'Attachment\',\'${entry.key}\');"><c:out value="${entry.value.displayFileName}" /></a>' + '</td>';
	 			responseText = responseText + '<td class="right">'+ '<c:out value="${entry.value.fileSize}" />' + '</td>';
	 			
	 			responseText = responseText + '</tr>';
			</c:forEach>
			responseText = responseText + '</tbody>';
			jQuery('#fileListTable',window.parent.document).append(responseText);
		
	</c:if>
	<c:if test="${fileUploadForm.fileMap == null}">
		jQuery('#fileListTable',window.parent.document).empty();
	</c:if>
	
	<c:if test="${chlOthersForm.cmArea != null}">
		//alert("Inside c If");
		createSubTypeCombo(document.getElementById("cmArea"),'<c:out value="${chlOthersForm.cmSubArea}" />');
		document.getElementById("img_help4").style.display = 'none';
		document.getElementById("img_help1").style.display = 'inline-block';
	</c:if>
	
	<c:if test="${chlOthersForm.flowType != null && chlOthersForm.flowType =='cancel'}">
		//alert("hii" + document.getElementById("cmArea").disabled);
		document.getElementById("cmAreaSpan").style.display = "none";
		document.getElementById("cmAreaSpanCancel").style.display = "inline-block";
		combo.disable(true);
		jQuery('.dhx_combo_img').attr('src','/LexmarkServicesPortal/images/comboImgs/combo_select_disable.gif');
		
		
	</c:if>

	<c:if test="${chlOthersForm.flowType != null && chlOthersForm.flowType =='update'}">
	document.getElementById("cmAreaSpan").style.display = "none";
	document.getElementById("cmAreaSpanUpdate").style.display = "inline-block";
	combo.disable(true);
	jQuery('.dhx_combo_img').attr('src','/LexmarkServicesPortal/images/comboImgs/combo_select_disable.gif');

	</c:if>
	
	jQuery('#txtFilePath').val('');
	
	<c:if test="${source != null}"> 
		
		jQuery('#txtFilePath',window.parent.document).val('');
		
		jQuery('#validationErrors',window.parent.document).empty();
		window.parent.document.getElementById("validationErrors").style.display = 'none';
		window.parent.document.getElementById("jsValidationErrors").style.display = 'none';
		if(document.getElementById("errors")!= null){
			//jQuery('#error_banner_jsValidationErrors',window.parent.document).empty();
			
			var divText = document.getElementById("errors").innerHTML;
			jQuery('#validationErrors',window.parent.document).append(divText);
			window.parent.document.getElementById("validationErrors").style.display = 'block';
			window.parent.scrollTo(0,0);
		}
		
		jQuery('#exceptionsDiv',window.parent.document).empty();
		window.parent.document.getElementById("exceptionsDiv").style.display = 'none';
		if(document.getElementById("dispalyExceptions")!= null){
			//jQuery('#error_banner_jsValidationErrors',window.parent.document).empty();
			
			var divText = document.getElementById("dispalyExceptions").innerHTML;
			jQuery('#exceptionsDiv',window.parent.document).append(divText);
			window.parent.document.getElementById("exceptionsDiv").style.display = 'block';
			window.parent.scrollTo(0,0);
		}
		
		jQuery("#processingHint",window.parent.document).css({
			display:"none"
		});
		jQuery("#overlay",window.parent.document).css({
			display:"none" 
		});
	
	
	</c:if>
	
	

});
// added
function imagePos(){
	
	document.getElementById("img_help4").style.display = 'none';
	document.getElementById("img_help1").style.display = 'inline-block';
}


/* Added for CI7 BRD14-02-12 */
ajaxSuccessFunction=function updateRequest(){
	window.location.href="<portlet:actionURL><portlet:param name='action' value='showCHLOthersPage'/></portlet:actionURL>&friendlyURL="+populateURL("managechlothers?forward=Others",[],[]);					
}
/* END */
</script>

	      
<div id="content-wrapper">
    <div class="journal-content-article">
      <h1><spring:message code="requestInfo.cm.heading.changeRequest"/></h1>
      <h2 class="step"><spring:message code="requestInfo.cm.others.heading.others"/></h2> </div>
      
      <!-- Added for CI7 BRD14-02-12 -->
      <c:if test="${changeAccountLink}">
      <div><h3><strong>
      <%@ include file="/WEB-INF/jsp/common/commonAccountAgreementNameInfo.jsp"%>
    	<c:if test='${sessionScope.accountCurrentDetails["rowCount"]>"1"}'>
        		<a href="#" onclick="javascript:showChangeAccountPopup();" class="edit" id="changeaccount"><spring:message code="requestInfo.link.changeAccount"/></a>
        	</c:if>
        	</strong>
        	</h3>
        	</div>
        	</c:if>
        	<!-- END -->
        	
       	   <!-- JS Validation Errors displayed in the below html:statusBanner -->
           <div id="jsValidationErrors" class="error" style="display: none;"></div>
            <!-- Validation Errors displayed in the below div -->
            <spring:hasBindErrors name="fileUploadForm">
				<div class="error" id="errors">
					<c:forEach var="error" items="${errors.allErrors}">
						<li><strong><spring:message code="${error.code}"/></strong></li>
					</c:forEach>
				</div>	
			</spring:hasBindErrors>
          	<div id="validationErrors" class="error" style="display: none;"></div><!--Div used for file upload validation errors  -->
          	
          	<!-- Exceptions displayed in the below div -->
          	 <c:if test="${exceptionList != null}">
       			<div class="error" id="dispalyExceptions">
	       			<c:forEach items="${exceptionList}" var="entry">
			       		<li><strong>${entry}</strong></li>		       		
			     	</c:forEach>
      			</div>
          	</c:if>
          	<div id="exceptionsDiv" class="error" style="display: none;"></div>	<!--Div used for file upload exception  -->
<div class="main-wrap">
	<div class="content">
			
		<!-- MAIN CONTENT BEGIN -->
          <h3 class="pageTitle"><spring:message code="requestInfo.cm.others.heading.others"/><span><spring:message code="requestInfo.label.fieldsMarked"/> <span class="req">*</span><spring:message code="requestInfo.label.areRequired"/></span></h3>
 		  <form:form id="chlOthersFormId" commandName="chlOthersForm" method="post" action="${createRequestAction}" enctype="multipart/form-data" >
		  <%--This jsp would display the contact of the requestor retrieved from Portal & Session,  
			Additional Information of the Change Mgmt Request End--%>
			<%@ include file="/WEB-INF/jsp/common/commonContactInfo.jsp"%>
	        
			  
			 <hr class="separator" />
			 <span style="display: none">
				<form:input path="prevSRNumber" /> 
			 </span> 
			  		
					<p class="info"><spring:message code="requestInfo.cm.others.label.pleaseChooseTypeOfRequest"/></p>
					<div class="portletBlock infoBox">
					<div class="columnsTwo">
					<div class="columnInner">
					<span style="display: none">
						<form:input path="cmAreaValue" /> 
						<form:input path="cmSubAreaValue" />
					</span>
					
					<ul class="form">
	                  <li>
                  <!-- 	changed -->
	                    <label for="rqstType"><spring:message code="requestInfo.label.area"/><span class="req">*</span></label>
	                    <span id="cmAreaSpan"><form:select path="cmArea" id="cmArea" multiple="false" onchange="imagePos();createSubTypeCombo(this, null);">
	                   		 <form:option value="0" >-<spring:message code="requestInfo.option.select"/>-</form:option>
	                    	 <c:forEach items="${areaMap}" var="entry">
	                    	 <!--<c:if test="${entry.value == 'Initiate PO'}">
	                    	 	<c:if test="${ not empty sessionScope.userAccessMapForArea}">
	                    	 		<form:option value="${entry.key}">${entry.value}</form:option>
	                    	 	</c:if>
	                    	 </c:if>-->
					       	 <form:option value="${entry.key}">${entry.value}</form:option>
					     	 </c:forEach>
	                    </form:select>
	                     </span>
	                     <span id="cmAreaSpanCancel" style="display: none;">
	                     	<!--  <select disabled="disabled"><option><spring:message code="Details.changeRequestDetails.button.cancelReq"/></option></select> --> 
	                     	<!-- The above line is commented Defect #7853 -->
	                     	<select disabled="disabled"><option><spring:message code="Details.changeRequestDetails.button.cancelReq"/></option></select>
	                     </span>
	                    <span id="cmAreaSpanUpdate" style="display: none;">
	                     	<select disabled="disabled"><option><spring:message code="Details.changeRequestDetails.button.updateReq"/></option></select>
	                     </span>
	                    				
						<img id="img_help3" class="helpIcon ui_icon_sprite info-icon"  src="<html:imagesPath/>transparent.png" title='<spring:message code="requestInfo.tooltip.area"/>'  />
					  </li>
					  <li>
	                    <label for="rqstSubType"><spring:message code="requestInfo.label.subArea"/><span class="req">*</span></label>
	                    
	                    <span id="rqstSubTypeDiv1">
	                    	<select  id="rqstType">
	                    		<option value="-<spring:message code="requestInfo.option.select"/>-">-<spring:message code="requestInfo.option.select"/>-</option>
	                    	</select>
	                    	
	                    </span>
	                    <span class="comboSelect rqstSubTypeDiv2" id="rqstSubTypeDiv2" >
	                    	
	                    </span>
	                    <img id="img_help4" class="helpIcon ui_icon_sprite info-icon"  src="<html:imagesPath/>transparent.png" title='<spring:message code="requestInfo.tooltip.subArea"/>' />
	                    <img id="img_help1" class="helpIcon ui_icon_sprite info-icon"  src="<html:imagesPath/>transparent.png" title='<spring:message code="requestInfo.tooltip.subArea"/>' style="display:none; " />
	                                
						
					  </li>
	                  <li>
	                    <label for="rqstDesc"><spring:message code="requestInfo.label.notes"/><span class="req">*</span></label>
	             		 <span> <form:textarea path="notesOrComments" id="notesOrComments" rows="3" maxlength="2000"/>  </span>
					  </li>
	                </ul>
				</div>
		</div>
		</div>
			  
			  <span style="display: none">
					<form:input path="listOfFileTypes" id="listOfFileTypes"/>
					<form:input path="attMaxCount" id="attMaxCount"/> 
					<form:input path="chlTempMaxCount" id="chlTempMaxCount"/>
					<form:input path="flowType" id="flowType"/> 
					<form:input path="requestedFrom" id="requestedFrom"/> 
			  </span>
	          </form:form>
			  
				<p class="inlineTitle"><spring:message code="requestInfo.heading.attachments"/></p>
				<span id="masstemplatespan" style="display: none;"><p><spring:message code="requestInfo.label.download"/> <a href="javascript:void(0);" onclick="openTemplate('massUploadTemplate',null);"> <spring:message code="requestInfo.label.massUploadTemplate"/></a> <spring:message code="requestInfo.label.fillDetails"/></p></span>
				<span id="hwMasstemplatespan" style="display: none;"><p><spring:message code="requestInfo.label.download"/> <a href="javascript:void(0);" onclick="openTemplate('hwMassUploadTemplate',null);"> <spring:message code="requestInfo.label.massUploadTemplate"/></a> <spring:message code="requestInfo.label.fillDetails"/></p></span>
				<div class="columnInner">
			  
			  
			  <form:form id="fileUploadFormId" commandName="fileUploadForm" method="post" action="${attachDocumentAction}" enctype="multipart/form-data" >
			  
			    		  	
			  		
			 	<nobr>
					<input type="text" id="txtFilePath" readonly="true" class="txtFilePath" />&nbsp;
					<form:input type="file" size="1" id="file" class="requestsUploader" path="fileData" onchange="setPathToTextBox();" onclick="clearAttachmentForm();"/>
					<button class="button_cancel cursor-pointer" type="button" id="btnBrowse" ><spring:message code="button.browse"/></button>&nbsp;&nbsp;					
					<button class="button_cancel cursor-pointer" type="button" id="btnUpload" onclick="uploadFile();"><spring:message code="button.attach"/></button>
				</nobr>
						 	
					<p class="note"><spring:message code="requestInfo.label.attachmentInstruction1"/>
					<spring:message code="requestInfo.label.attachmentInstruction2"/></p>
									
					<iframe id="upload_target" name="upload_target" class="iframe-upload-target">
					
					</iframe>
					
						
						
						
						  <div id="fileListDiv" class="wHalf displayGrid columnInnerTable rounded shadow">
							
							<table id="fileListTable">
							</table>
							</div>
							<div id="test"></div>
							 <div class="lineClear"></div>							
						 
							  
				
				<input type="hidden" name="pageType" id="pageType"/>
					
			  </div>
			  
			  </form:form>
 			  
			  <div class="buttonContainer">
	            <button type="button" class="button_cancel buttonOK" onclick="javascript:redirectToHistory('others');"><spring:message code="button.cancel"/></button>
	            <button type="button" class="button buttonOK" onclick="submitRequest();" ><spring:message code="button.continue"/></button>
	          </div>
		  <input type="hidden" id="fileCount" />
        </div>
        <!-- MAIN CONTENT END -->	
		</div>
</div>

