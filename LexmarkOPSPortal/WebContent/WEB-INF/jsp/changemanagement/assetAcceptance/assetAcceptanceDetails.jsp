<% request.setAttribute("subTabSelected","createNewRequest"); %>
<%@ include file="/WEB-INF/jsp/common/subTab.jsp"%>
<%@page import="java.util.Map"%>

<jsp:include page="/WEB-INF/jsp/common/validationMPS.jsp" />
<jsp:include page="/WEB-INF/jsp/includeGrid.jsp"/>
<%@ page import = "com.lexmark.services.util.LexmarkSPOmnitureConstants" %>

<script type="text/javascript" src="<html:rootPath/>js/grid/dhtmlxcommon.js"></script>
<script type="text/javascript" src="<html:rootPath/>js/combo/dhtmlxcombo.js"></script>
<script type="text/javascript" src="<html:rootPath/>js/combo/dhtmlxcombo_whp.js"></script>
<script type="text/javascript"><%@ include file="../../../../js/portletRedirection.js"%></script>
<style type="text/css"><%@ include file="/WEB-INF/css/combo/dhtmlxcombo.css" %></style>
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

<portlet:actionURL var='assetAcceptanceReviewActionUrl'>
<portlet:param name='action' value='showAssetAcceptanceReview' />
</portlet:actionURL>




  <div id="content-wrapper">
    <div class="journal-content-article">
      <h1><spring:message code="requestInfo.cm.heading.changeRequest"/></h1>
      <h2 class="step"><spring:message code = "requestInfo.cm.label.acceptanceRequest"/></h2> </div>
      
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
          <h3 class="pageTitle"><spring:message code = "requestInfo.heading.acceptanceRequest"/><span><spring:message code="requestInfo.label.fieldsMarked"/><span class="req">*</span><spring:message code="requestInfo.label.areRequired"/></span></h3>
          
          <html:statusBanner id="multipleSRUploadError"/>
          <div class="error" id="errorMsg" style="display:none;font-weight:bold;"></div>  
          
          <form:form id="assetAcceptFormId" commandName="assetAcceptForm" modelAttribute="assetAcceptForm" method="post" action="${assetAcceptanceReviewActionUrl}" enctype="multipart/form-data" >
          <%@ include file="/WEB-INF/jsp/common/commonContactInfo.jsp"%>
          
	
          
          
          <hr class="separator" />
			 <span style="display: none">
				<form:input path="prevSRNumber" /> 
			 </span> 
			  		
					
					<div class="portletBlock infoBox">
					<div class="columnsTwo">
					<div class="columnInner">
					<span style="display: none">
					<form:input path="cmAreaValue" /> 
					</span>
					
					<ul class="form">
	                  <li>
	                    <label for="rqstSubType"><spring:message code="requestInfo.label.subArea"/><span class="req">*</span></label>
	                   <span><form:select path="cmArea" id="cmArea" multiple="false">
	                   		 <%-- <form:option value="0" >-<spring:message code="requestInfo.option.select"/>-</form:option> --%>
	                   		 
	                    	 <c:forEach items="${aaSubAreaMap}" var="entry">
					       		 <form:option value="${entry.key}">${entry.value}</form:option>
					     	 </c:forEach> 
	                    </form:select> </span>
	                    
	                    				
						<img id="img_help3" class="helpIcon ui_icon_sprite info-icon"  src="<html:imagesPath/>transparent.png"  title='<spring:message code="requestInfo.tooltip.subArea"/>'  />
					  </li>
					
	                  <li>
	                    <label for="rqstDesc"><spring:message code="requestInfo.label.notes"/><span class="req">*</span></label>
	                  <span>
	                  	<form:textarea path="notes" id="notes" rows="4"/>
	                  </span>
	                  <em class="note w200"><spring:message code = "requestInfo.label.note/comment"/></em>
					  </li>
	                </ul>
				</div>
		</div>
		</div>
		  <span style="display: none">
					<form:input path="listOfFileTypes" id="listOfFileTypes"/>
					<form:input path="attMaxCount" id="attMaxCount"/> 
					<form:input path="requestedFrom" id="requestedFrom"/>
			  </span>        
 </form:form> 
			  
				<p class="inlineTitle"><spring:message code="requestInfo.heading.attachments"/></p>
        <div class="columnInner">
        <form:form id="fileUploadFormId" commandName="fileUploadForm" method="post" action="${attachDocumentAction}" enctype="multipart/form-data" >
        	 
				<span style="display: none">
					<form:input path="pageType" id="pageType"/> 
				</span>
			
          	<input type="text" size="70" name="txtFilePath" id="txtFilePath" readonly="readonly">
          	<form:input type="file" size="1" id="file" class="requestsUploader" path="fileData" onchange="setPathToTextBox();" onclick="clearAttachmentForm();"  />
			 <button class="button_cancel" type="button" id="btnBrowse" style="cursor:pointer;" onclick="callOmnitureAction('<%=LexmarkSPOmnitureConstants.ASSETACCEPTANCE%>','<%=LexmarkSPOmnitureConstants.ASSETACCEPTANCEBROWSE%>');">
			 <spring:message code="button.browse"/></button>&nbsp;&nbsp;					
			<button class="button_cancel" type="button" id="btnUpload" style="cursor:pointer;" onclick="uploadFile();"><spring:message code="button.attach"/></button>
						 	
					<p class="note"><spring:message code = "requestInfo.label.attachmentInstruction4"/>
					<spring:message code = "requestInfo.label.attachmentInstruction5"/></p>
									
					<iframe id="upload_target" name="upload_target" style="width:0px;height:0px;border:0px solid #fff;"></iframe>
			<div id="fileListDiv" class="wHalf displayGrid columnInnerTable rounded shadow">
				<table id="fileListTable"></table>
			</div>
		 	<div id="test"></div>
			<div class="lineClear"></div>
        </form:form>
        
 			  
			  <div class="buttonContainer">
	            <button type="button" id="btn_cancel" class="button_cancel" onclick="javascript:redirectToHistory(); callOmnitureAction('<%=LexmarkSPOmnitureConstants.ASSETACCEPTANCE%>','<%=LexmarkSPOmnitureConstants.ASSETACCEPTANCECANCEL%>');"><spring:message code="button.cancel"/></button>
	            <button type="button" id="btn_continue" class="button" onclick="submitRequest();" ><spring:message code="button.continue"/></button>
	          </div>
	          </div>
		  <input type ="hidden" id="fileCount"/>
        </div>
        <!-- MAIN CONTENT END -->
        	
		</div>
</div>
     
     <script type="text/javascript">
     
     if (window.PIE) {
 		
      document.getElementById("btn_cancel").fireEvent("onmove");
      document.getElementById("btn_continue").fireEvent("onmove");
      document.getElementById("btnBrowse").fireEvent("onmove");
      document.getElementById("btnUpload").fireEvent("onmove");
  }
     
     var portlet_name="<%=LexmarkSPOmnitureConstants.ASSETACCEPTANCE%>";
     var action_primary="<%=LexmarkSPOmnitureConstants.ASSETACCEPTANCESELECTADIFFERENTCONTACT%>";
     var action_secondary="<%=LexmarkSPOmnitureConstants.ASSETACCEPTANCEADDADDITIONALCONTACT%>";
     
     var errorList = "";
      


	function uploadFile() {
		
		callOmnitureAction('<%=LexmarkSPOmnitureConstants.ASSETACCEPTANCE%>','<%=LexmarkSPOmnitureConstants.ASSETACCEPTANCEATTACH%>');
		
		var listOfFileTypes = document.getElementById("listOfFileTypes").value; 
		
		var fileCount = document.getElementById("fileCount").value;
		var maxAttCount = document.getElementById("attMaxCount").value;
		var fileNameLocal = document.getElementById("txtFilePath").value;
		var errorList = "";
		var validFlag = 1;
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
		 if(fileCount >= maxAttCount){
			 
			document.getElementById("validationErrors").style.display = 'none';
			document.getElementById("exceptionsDiv").style.display = 'none';
			errorList = errorList + "<li><strong><spring:message code='validation.fileUpload.template.fileCountExceeds.errorMsg'/></strong></li>";
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
			document.getElementById('pageType').value = "Acceptance";
			jQuery('#fileUploadFormId').submit();
			

		}
		
	}

	function removeFile(fileName) {
		
		callOmnitureAction('<%=LexmarkSPOmnitureConstants.ASSETACCEPTANCE%>','<%=LexmarkSPOmnitureConstants.ASSETACCEPTANCEDELETEFILE%>');
		
		var link="<%=removeDocumentAction %>";
		
		document.getElementById("fileCount").value = document.getElementById("fileCount").value - 1;
		document.getElementById('fileUploadFormId').target = 'upload_target';
		document.getElementById('fileUploadFormId').action = link + "&fileName=" + fileName;
		jQuery('#fileUploadFormId').submit();
		
	}
	
     function submitRequest(){
    	
    	 callOmnitureAction('<%=LexmarkSPOmnitureConstants.ASSETACCEPTANCE%>','<%=LexmarkSPOmnitureConstants.ASSETACCEPTANCECONTINUE%>');
    		
    	 
    	 var cmArea = document.getElementById('cmArea')[document.getElementById('cmArea').selectedIndex].innerHTML;
    		
    		document.getElementById("cmAreaValue").value=cmArea;
    		
    		
    		window.parent.document.getElementById("validationErrors").style.display = 'none';
    		window.parent.document.getElementById("exceptionsDiv").style.display = 'none';
    		var errorList = "";
    		if(document.getElementById('cmArea').value==0 ){
    			errorList = "<li><strong><spring:message code='requestInfo.label.validation.subAreaMandatory'/></strong></li>";
    			jQuery('#cmArea').addClass('errorColor');
    		}
    		if(document.getElementById('notes').value == ''){
    			
    			errorList = errorList + "<li><strong><spring:message code='requestInfo.label.validation.notesMandatory'/></strong></li>";
    			jQuery('#notes').addClass('errorColor');
    		}
    		if(document.getElementById('addtnlDescription').value.length > 2000){
    			
    			errorList = errorList + "<li><strong><spring:message code='validation.description.length.errorMsg'/></strong></li>";
    		}
    		var note = document.getElementById('notes').value;
    		
    		if(note.length >2000){

    			errorList = errorList + "<li><strong><spring:message code='validation.notes.notesMaxLength'/></strong></li>";
    			
    			jQuery('#notes').addClass('errorColor');
    		}
    		
    		
    		if(errorList != ""){
    			jQuery('#jsValidationErrors',window.parent.document).empty();
    			document.getElementById("jsValidationErrors").innerHTML = errorList;
    			window.parent.document.getElementById("jsValidationErrors").style.display = 'block';
    			window.parent.scrollTo(0,0);
    			
    		}else{
    			var link="<%=assetAcceptanceReviewActionUrl %>";
    			showOverlay();
    			
    			document.getElementById('assetAcceptFormId').action = link +"&timeZoneOffset=" + timezoneOffset;
    			
    			document.getElementById('assetAcceptFormId').submit();
    		}
    		
    	}
      function setPathToTextBox()
     {
    	  
     	filename=jQuery('#file').val();
     	if(filename.match(/fakepath/)) {
			filename = filename.replace(/C:\\fakepath\\/i, '');
		}
     	jQuery('#txtFilePath').val(filename);
     	
     } 
     

     function clearAttachmentForm(){
         document.getElementById('fileUploadFormId').reset();
     }
     

     function openTemplate(type,fileName){
    	callOmnitureAction('<%=LexmarkSPOmnitureConstants.ASSETACCEPTANCE%>','<%=LexmarkSPOmnitureConstants.ASSETACCEPTANCEFILEDOWNLOAD%>');
    	if(type=='CHLTemplate'){
    		window.location="${chlTemplateURL}&fileType=CHLTemplate";
    	}else if(type=='Attachment'){
    			window.location="${chlTemplateURL}&fileType=Attachment&fileName="+fileName;
    		}
    		
    	}
     jQuery(document).ready(function() {
    	 var currentURL = window.location.href;
    		jQuery('#cmArea,#notes').bind('mousedown focus',function(){
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
    		 			responseText = responseText + '<td class="w15"><img id="img_help1"  src="<html:imagesPath/>tabbarImgs/default/close.png" alt="Remove" height="15" width="15" onclick=\'removeFile("${entry.key}");\' /></td>';
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
    		
    		
    		jQuery('#validationErrors',window.parent.document).empty();
    		
    		window.parent.document.getElementById("validationErrors").style.display = 'none';
    		if(document.getElementById("errors")!= null){
    			window.parent.document.getElementById("jsValidationErrors").style.display = 'none';
    			
    			var divText = document.getElementById("errors").innerHTML;
    			jQuery('#validationErrors',window.parent.document).append(divText);
    			window.parent.document.getElementById("validationErrors").style.display = 'block';
    			window.parent.scrollTo(0,0);
    		}
    		
    		jQuery('#exceptionsDiv',window.parent.document).empty();
    		window.parent.document.getElementById("exceptionsDiv").style.display = 'none';
    		if(document.getElementById("dispalyExceptions")!= null){
    			window.parent.document.getElementById("jsValidationErrors").style.display = 'none';
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
//     		Change Account Link Hide/Show for CI-7 Defect #12274
    		if(currentURL.indexOf('/partner-portal') == -1)
  		  {	
  			  jQuery('#changeaccount').show();
  		  }
  		else
  			{
  			jQuery('#changeaccount').hide();
  			}
    		
    		

    	});
     
     document.getElementById('effctiveDateshow').style.display = "none";
     jQuery(':input').bind('mousedown focus',function(){
 		jQuery(this).removeClass('errorColor');
 		});
     
     
     /* Added for CI7 BRD14-02-12 */
 ajaxSuccessFunction=function updateRequest(){
 		window.location.href="<portlet:actionURL><portlet:param name='action' value='showAcceptancePage'/></portlet:actionURL>&friendlyURL="+populateURL("assetacceptance",[],[]);					
 }
     /* END */
     </script>  
		  