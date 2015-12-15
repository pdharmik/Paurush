<% request.setAttribute("subTabSelected","createNewRequest"); %>
<%@ include file="/WEB-INF/jsp/common/subTab.jsp"%>
<jsp:include page="../../common/validationMPS.jsp" />
<script type="text/javascript"><%@ include file="../../../../js/portletRedirection.js"%></script>
<jsp:include page="/WEB-INF/jsp/includeGrid.jsp"/>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/mps.css"/>
<!--[if IE 7]>
	<style type="text/css"><%@ include file="/WEB-INF/css/mps_ie7.css" %></style> 
<![endif]-->


<portlet:actionURL var="renderReviewFormURL">
 	<portlet:param name="action" value="reviewForm" />
 </portlet:actionURL>
 
<portlet:actionURL var="uploadURL">
 	<portlet:param name="action" value="uploadAttachment" />
 </portlet:actionURL>
 
 <portlet:actionURL var="deleteURL">
 	<portlet:param name="action" value="deleteAttachment"/>
 </portlet:actionURL>
 


<input type="hidden" name="size_of_file" value="${size}"></input>
  
  
  
  <div id="content-wrapper">
    <div class="journal-content-article">
      <h1><spring:message code="requestInfo.cm.heading.changeRequest"/> </h1>  <h2 class="step">${requestSubType} </h2>
    </div>
    
    <!-- Added for CI7 BRD14-02-12 -->
    <div><h3><strong>
      <%@ include file="/WEB-INF/jsp/common/commonAccountAgreementNameInfo.jsp"%>
      <c:if test='${sessionScope.accountCurrentDetails["rowCount"]>"1"}'>
        		<a href="#" onclick="javascript:showChangeAccountPopup();" class="edit" id="changeaccount"><spring:message code="requestInfo.link.changeAccount"/></a>
        	</c:if>
        	</strong></h3></div>
        	<!-- END -->
        	
    <div class="main-wrap">
      <div class="content">
    
          <!-- MAIN CONTENT BEGIN -->
          <h3 class="pageTitle"> ${requestType}  <spring:message code="requestInfo.heading.multiple"/> ${requestSubType}  </h3>
         
          <html:statusBanner id="multipleSRUploadError"/>
          <div class="error" id="errorMsg" style="display:none;font-weight:bold;"></div>       
 	
          
<form:form id="manageMultipleSRForm" name="templateRequestForm" commandName="templateRequestForm"  method="post" action="${renderReviewFormURL}">
<%--This jsp would display the contact of the requestor retrieved from Portal & Session,  
Additional Information of the Change Mgmt Request End--%>
<%@ include file="/WEB-INF/jsp/common/commonContactInfo.jsp"%>
<form:hidden  id="filename" path="fileName" />
<form:hidden  id="filesize" path="fileSize" />

<form:hidden  id="requestType" path="requestType" />
<form:hidden  id="requestSubType" path="requestSubType" />


	<!-- <input id="requestType" name="requestType" type="hidden" value="${requestType}" /> 
	<input id="requestSubType" name="requestSubType" type="hidden" value="${requestSubType}" />  -->
	
	<input id="uploadedFileName" name="uploadedFileName" type="hidden" value="" />
<form:hidden id="prevSRNumber" path="prevSRNumber"></form:hidden>
</form:form>


		  
		  <p class="inlineTitle"><spring:message code="requestInfo.heading.requestDetails"/></p>		  
		  <p><spring:message code="requestInfo.label.download"/><a href="javascript:downloadTemplate();"> <spring:message code="requestInfo.label.reqFromHere"/></a></p>
		  <p><spring:message code="requestInfo.label.fillDetails"/></p>

		  <div class="columnInner">
		  <form:form id="fileUploadFormId" commandName="fileUploadForm" method="post" action="${uploadURL}" enctype="multipart/form-data" >
		
			<nobr>
					<input type="text" id="txtFilePath" readonly="true" style="width:320px; height:20px; margin-left:0px" />&nbsp;
					<form:input type="file" size="1" id="file" class="requestsUploader" path="fileData" onchange="setPathToTextBox();" />
					<button class="button_cancel" type="button" id="btnBrowse" style="cursor:pointer;"><spring:message code="button.browse"/></button>&nbsp;&nbsp;					
					<button class="button_cancel" type="button" id="btnUpload" style="cursor:pointer;" ><spring:message code="button.attach"/></button>
				</nobr>
			
			<p class="note"><spring:message code="requestInfo.label.attachmentInstruction3"/></p>
					
			
			<iframe id="upload_target" name="upload_target" src="" style="display:none;"></iframe>
					
					
		
			  <div id="uploadfiledetails" style="display: none" class="wHalf displayGrid columnInnerTable rounded shadow">
				
				 <table>
		            <thead>
		              <tr>
		                <th class="w15"></th>
		                <th><spring:message code="requestInfo.heading.attachmentFileName"/></th>
		                <th class="w80 right"><spring:message code="requestInfo.heading.attachmentFileSizeKiloBytes"/></th>
		              </tr>
		            </thead>
		            <tbody>
		              <tr class="odd">
		                <td class="w15"><img title="Delete" height="15" width="15" id="deleteAttachmentBtn" src="<html:imagesPath/>tabbarImgs/default/close.png"></td>
		                <td><label id="uploadedFilename"></label></td>
		                <td class="w120 right"><label id="uploadedFilesize"></label></td>
		              </tr>
		            </tbody>
		          </table>			
				</div>
											
		  </form:form>
		  </div>
          <div class="buttonContainer">
		  <button class="button_cancel" id="backToHomeBtn" onclick="javascript:redirectToHome('${requestSubTypeNew}');"> <spring:message code="button.back"/></button>
            <button class="button_cancel" onclick="javascript:redirectToHistory();"><spring:message code="button.cancel"/></button>
            <button class="button" onclick="goReviewForm();"><spring:message code="button.continue"/></button>
          </div>
  
        <!-- MAIN CONTENT END --> 
      </div>
    </div>
  </div>
  

<script type="text/javascript">
var filesize=0;
var filename = "";


document.getElementById('errorMsg').innerHTML = "";
document.getElementById('errorMsg').style.display = "none";

jQuery(document).ready(function(){
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


	jQuery('#btnUpload').bind('click', function() {
		//alert('Attaching file..'+filename);
		if(filename == ""){
			//alert("Please browse and select a file to be attached.");
			document.getElementById('errorMsg').innerHTML = "<spring:message code="requestInfo.label.validation.attachmentEmpty"/>";
			document.getElementById('errorMsg').style.display = "block";
			return false;
		}else if(jQuery('#filename').val()!=""){
			document.getElementById('errorMsg').innerHTML = '<spring:message code="fileUpload.count.exceeds"/>';
			document.getElementById('errorMsg').style.display = "block";
			return false;
		}else{
			document.getElementById('errorMsg').innerHTML = "";
			document.getElementById('errorMsg').style.display = "none";
			
			document.getElementById('fileUploadFormId').target = 'upload_target';	
			document.getElementById('fileUploadFormId').action="${uploadURL}"	;	
			jQuery('#fileUploadFormId').submit();
		}

	});


	
	jQuery('#deleteAttachmentBtn').bind('click', function() {
		//alert('Deleting file..');
		
		var link="${deleteURL}";
		//alert("uploadedFileName-->"+ jQuery('#uploadedFileName').val());
		document.getElementById('fileUploadFormId').target = 'upload_target';
		document.getElementById('fileUploadFormId').action = link + "&fileName=" + jQuery('#uploadedFileName').val();
		//alert("hello "+document.getElementById('fileUploadFormId').action);
		jQuery('#fileUploadFormId').submit();

		jQuery('#uploadfiledetails',window.parent.document).hide();
		jQuery('#file').val("");
		jQuery('#txtFilePath').val("");
		jQuery('#filename').val("");
		jQuery('#filesize').val("");
		
	});



	<c:if test="${fileUploadForm.fileMap != null}">
		<c:forEach items="${fileUploadForm.fileMap}" var="entry">
			var name = '<c:out value="${entry.key}" />';
			var size = '<c:out value="${entry.value.fileSize}" />';
			var fileToBeDeleted = '<c:out value="${entry.value.fileName}" />';
			//alert("fileToBeDeleted-->"+fileToBeDeleted);
			jQuery('#uploadedFileName',window.parent.document).val(fileToBeDeleted);
			jQuery('#txtFilePath',window.parent.document).val("");
			if(size!="" && size!="0" && size < 1024){
				window.parent.document.getElementById('errorMsg').style.display = "none";
				jQuery('#filename',window.parent.document).val(name);
				jQuery('#filesize',window.parent.document).val(size);	
				filesize=size;
				filename = fileToBeDeleted;
				jQuery('#uploadedFilename',window.parent.document).text(name);
				jQuery('#uploadedFilesize',window.parent.document).text(size);
				jQuery('#uploadfiledetails',window.parent.document).show();
			}else if(size > 1024){
				window.parent.document.getElementById('errorMsg').innerHTML = '<spring:message code="validation.fileUpload.maxSizeExceeds.errorMsg"/>';
				window.parent.document.getElementById('errorMsg').style.display = "block";

				jQuery('#uploadfiledetails',window.parent.document).hide();
				jQuery('#file',window.parent.document).val("");
				//jQuery('#txtFilePath',window.parent.document).val("");
				jQuery('#filename',window.parent.document).val("");
				jQuery('#filesize',window.parent.document).val("");
				filesize = 0;
				filename="";
				
			}else {
				//alert("size--->"+size);
				window.parent.document.getElementById('errorMsg').innerHTML = '<spring:message code="validation.fileUpload.fileTypeInvalid.errorMsg"/>';
				window.parent.parent.document.getElementById('errorMsg').style.display = "block";

				jQuery('#uploadfiledetails',window.parent.document).hide();
				jQuery('#file',window.parent.document).val("");
				
				jQuery('#filename',window.parent.document).val("");
				jQuery('#filesize',window.parent.document).val("");
				filesize = 0;
				filename="";
			}
			
		</c:forEach>
		
	</c:if>

	if(jQuery('#filesize').val()>1*1024){
		//alert('filesize exceeds..');
		document.getElementById('errorMsg').innerHTML = '<spring:message code="validation.fileUpload.maxSizeExceeds.errorMsg"/>';
		document.getElementById('errorMsg').style.display = "block";
		//alert("2");
		jQuery('#uploadfiledetails',window.parent.document).hide();
		jQuery('#file').val("");
		jQuery('#txtFilePath').val("");
		jQuery('#filename').val("");
		jQuery('#filesize').val("");
		filesize = 0;
		filename="";
	}

	if( jQuery('#filename').val()!= "" && jQuery('#filesize').val()==0){
		
		document.getElementById('errorMsg').innerHTML = '<spring:message code="validation.fileUpload.fileSizeEmpty.errorMsg"/>';
		document.getElementById('errorMsg').style.display = "block";
	}
	//jQuery('#fileListTable',window.parent.document).append(responseText);	

	jQuery("#processingHint",window.parent.document).css({
		display:"none"
	});
	jQuery("#overlay",window.parent.document).css({
		display:"none" 
	});
	
	
	
});//End of jquery ready function


function goReviewForm(){
	
	var name = jQuery('#filename').val();
	var size = jQuery('#filesize').val();
	// added by jyoti for effective date of change validation
	if(jQuery('#effDtOfChange').val()!=""&&!dateCheck(jQuery('#effDtOfChange').val()))
		{
			//alert(jQuery('#effDtOfChange').val());
			document.getElementById('errorMsg').innerHTML = "<spring:message code='validation.date.format.errorMsg'/></strong></li>";
			document.getElementById('errorMsg').style.display = "block";
			//errorList=errorList + "<li><strong><spring:message code='validation.date.format.errorMsg'/></strong></li>";
			jQuery('#effDtOfChange').addClass('errorColor');	
			return false;
		}
	if(name == ""){		
		//showError("Please attach a template first.","multipleSRUploadError",false);
		document.getElementById('errorMsg').innerHTML = "<spring:message code='validation.fileUpload.attachmentEmpty.errorMsg'/>";
		document.getElementById('errorMsg').style.display = "block";
		return false;
	}if(size=="" || size == 0){
		//showError("The file you are trying to upload is empty or not valid. Please attach a valid file .","multipleSRUploadError",false);
		document.getElementById('errorMsg').innerHTML = "<spring:message code='validation.fileUpload.fileTypeInvalid.errorMsg'/>";
		document.getElementById('errorMsg').style.display = "block";
		return false;
	}if(size>1*1024){
		//alert("000");
		document.getElementById('errorMsg').innerHTML = "<spring:message code='validation.fileUpload.maxSizeExceeds.errorMsg'/>";
		document.getElementById('errorMsg').style.display = "block";
		return false;
	}if(!descValidate(jQuery('#addtnlDescription').val(),"errorMsg")) {		
		jQuery('#addtnlDescription').addClass('errorColor');
		document.getElementById('errorMsg').style.display = "block";
		jQuery(document).scrollTop(0);
		return false;
	}

	//alert("B4 submit-->"+"${renderReviewFormURL}");
	//document.getElementById('ManageMultipleSRForm').action = "${renderReviewFormURL}";
	jQuery('#manageMultipleSRForm').submit();
}

function setPathToTextBox()	{
		filename=jQuery('#file').val();
		if(filename.match(/fakepath/)) {
			filename = filename.replace(/C:\\fakepath\\/i, '');
		}
		//alert("filename===>"+filename);
				
		if(filename.length > 0 && filename.indexOf(".xls") == -1){
			//alert("Upload a .xls file only.");
			//showError("Please upload only .xls file","multipleSRUploadError",false);
			document.getElementById('errorMsg').innerHTML = "<spring:message code='validation.fileUpload.template.fileTypeInvalid.errorMsg'/>";
			document.getElementById('errorMsg').style.display = "block";
			jQuery('#txtFilePath').val("");
			filename = "";
			return false;
		}else{
			jQuery('#txtFilePath').val(filename);

			document.getElementById('errorMsg').innerHTML = "";
			document.getElementById('errorMsg').style.display = "none";
		}
		
	}


function redirectToHome(homePage){
		//alert("homePage-->"+homePage);
		
		
			jConfirm('<spring:message code="common.back.alert"/>', "", function(confirmResult) {
				if(confirmResult){
					showOverlay();
					//window.location.href="service-requests";
					if(homePage == "Assets"){
						window.location.href="manageassets";
					}else if(homePage == "Contacts"){
						window.location.href="managecontacts";
					}else if(homePage == "Addresses"){

						window.location.href="manageaddresses";
					}
				}else{
					return false;
					}
			});
		

	}


function hideErrorMessage(id) {
	document.getElementById(id).style.display = "none";
	document.getElementById(id).style.visibility = "hidden";
};

	function downloadTemplate(){
		//var url = "${downloadTemplateUrl}" ;

		window.location="${downloadTemplateUrl}&requestType=" + "${requestTypeNew}"+"&requestSubType="+"${requestSubTypeNew}";
	}
	
	
	/* Added for CI7 BRD14-02-12 */
	ajaxSuccessFunction=function updateRequest(){
		<c:if test="${requestTypeNew == 'Add' && requestSubTypeNew == 'Addresses'}">
		window.location.href="<portlet:actionURL><portlet:param name='action' value='showAddressPage'/></portlet:actionURL>&friendlyURL="+populateURL("manageaddresses",[],[]);
		</c:if>
		<c:if test="${requestTypeNew == 'Change' && requestSubTypeNew == 'Addresses'}">
		window.location.href="<portlet:actionURL><portlet:param name='action' value='showAddressPage'/></portlet:actionURL>&friendlyURL="+populateURL("manageaddresses",[],[]);
		</c:if>
		<c:if test="${requestTypeNew == 'Remove' && requestSubTypeNew == 'Addresses'}">
		window.location.href="<portlet:actionURL><portlet:param name='action' value='showAddressPage'/></portlet:actionURL>&friendlyURL="+populateURL("manageaddresses",[],[]);
		</c:if>
		<c:if test="${requestTypeNew == 'Add' && requestSubTypeNew == 'Contacts'}">
		window.location.href="<portlet:actionURL><portlet:param name='action' value='showContactPage'/></portlet:actionURL>&friendlyURL="+populateURL("managecontacts",[],[]);
		</c:if>
		<c:if test="${requestTypeNew == 'Change' && requestSubTypeNew == 'Contacts'}">
		window.location.href="<portlet:actionURL><portlet:param name='action' value='showContactPage'/></portlet:actionURL>&friendlyURL="+populateURL("managecontacts",[],[]);
		</c:if>
		<c:if test="${requestTypeNew == 'Remove' && requestSubTypeNew == 'Contacts'}">
		window.location.href="<portlet:actionURL><portlet:param name='action' value='showContactPage'/></portlet:actionURL>&friendlyURL="+populateURL("managecontacts",[],[]);
		</c:if>
		<c:if test="${requestTypeNew == 'Add' && requestSubTypeNew == 'Assets'}">
		window.location.href="<portlet:actionURL><portlet:param name='action' value='showAssetPage'/></portlet:actionURL>&friendlyURL="+populateURL("manageassets",[],[]);
		</c:if>
		<c:if test="${requestTypeNew == 'Change' && requestSubTypeNew == 'Assets'}">
		window.location.href="<portlet:actionURL><portlet:param name='action' value='showAssetPage'/></portlet:actionURL>&friendlyURL="+populateURL("manageassets",[],[]);
		</c:if>
		<c:if test="${requestTypeNew == 'Decommission' && requestSubTypeNew == 'Assets'}">
		window.location.href="<portlet:actionURL><portlet:param name='action' value='showAssetPage'/></portlet:actionURL>&friendlyURL="+populateURL("manageassets",[],[]);
		</c:if>
		<c:if test="${requestTypeNew == 'Swap' && requestSubTypeNew == 'Assets'}">
		window.location.href="<portlet:actionURL><portlet:param name='action' value='showAssetPage'/></portlet:actionURL>&friendlyURL="+populateURL("manageassets",[],[]);
		</c:if>
	};
	/* END */
	
</script>


