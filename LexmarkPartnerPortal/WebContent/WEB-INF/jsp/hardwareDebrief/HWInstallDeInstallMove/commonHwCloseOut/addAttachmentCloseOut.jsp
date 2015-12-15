<%@ taglib prefix="html" tagdir="/WEB-INF/tags/html" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="/WEB-INF/tld/liferay-portlet.tld" prefix="portlet"%>
<%@ page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript" src="<html:rootPath/>js/expand.js"></script>
<portlet:actionURL var='attachDocumentAction' windowState="<%=LiferayWindowState.EXCLUSIVE.toString() %>">
	<portlet:param name='action' value='attachDocument' />
</portlet:actionURL>
<portlet:actionURL var ="removeAttachmentURL" windowState="<%=LiferayWindowState.EXCLUSIVE.toString() %>">
	<portlet:param name='action' value='removeAttachment' />
</portlet:actionURL>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/in-styles.css?version=<html:fileCacheVersion/>" />
<div class="portletBlock infoBox rounded shadow">
          <div class="columnsOne">
            <div class="columnInner">
              <h4 class="expand"><spring:message code="requestInfo.hardwareDebreief.common.closeOut.addAttachment.header"/></h4>
              <div class="collapse"> 
                <p class="inlineTitle inlineTitle2"><spring:message code="requestInfo.hardwareDebreief.common.closeOut.addAttachment.attachFiles"/></p>
                
                
                
      	 <form:form id="fileUploadFormId" commandName="fileUploadForm" action="${attachDocumentAction}" method="post" enctype="multipart/form-data" target="upload_target">
      	 <div class="floatL wFull">
      	  <form:input id="pageTypeUpload" type="hidden" path="pageType" />
      	 <form:input path="sessionFileKey" type="hidden" />
      	 <form:input path="fileIndex" type="hidden" id="fileIndex"/>
        <table class="relativePos floatL">
        			<tr></tr>
                   	<tr class="floatL">
        			<td width="225" class="relativePos"><input type="text" size="70" name="txtFilePath" id="textInput" readonly="readonly" class="relativePos">&nbsp;</td>
                    <td width="100" class="relativePos"><form:input type="file" id="fileUploadInput" path="fileData" class="requestsUploader requestsUploader2" size="1" />
                    <button class="button relativePos" type="button" id="browseButton" ><spring:message code="button.browse"/></button></td>
                    <td width="100" class="relativePos">
                    <button class="button relativePos" type="button" id="uploadButn"><spring:message code="requestInfo.button.upload"/></button> </td>
                    </tr>
            </table>
             <p class="note"><spring:message code="requestInfo.hardwareDebreief.common.closeOut.addAttachment.attachmentMessage"/></p>
        </div>
        </form:form>
        <div id="uploadInformationDiv" class="wHalf floatL margin-top-20px" >
      
        <c:if test="${ not empty fileMapInSesion }">
        <table class="displayGrid rounded shadow ">
                    <thead>
                      <tr>
                      	<th></th>
                        <th><spring:message code="attachment.message.FileName"/></th>
                        <th class="w80 right"><spring:message code="requestInfo.heading.attachmentFileSizeBytes"/></th>
                        
                      </tr>
                        </thead>
                        <tbody>
        <c:forEach items="${fileMapInSesion}" var="fileUploadDetails" varStatus = "status" >
						

                      <tr>
                        <td class="w15"><img id="img_help1"  src="<html:imagesPath/>delete.gif" alt="Remove" height="15" width="15" onclick="parent.removeFile('${status.index}');" /></td>
                        <td><a class="cursor-pointer" onclick="parent.downloadUploadedFile()">${fileUploadDetails.value.displayFileName}</a></td>
                        <td class="w80 right">${fileUploadDetails.value.fileSize}</td>
                        
                                         
                      </tr>
        </c:forEach>
             </tbody>
		</table>
        </c:if>
        </div>
        <div class="clear-both"></div>
        <iframe id="upload_target" name="upload_target" style="display: none;" frameBorder="0" scrolling="no"></iframe>
            <%--
            
                <div class="infoBox">
                  <input type="text" size="70" name="txtFilePath" id="txtFilePath" readonly="readonly">
                  <button id="btnUpload" type="button" class="button">Browse...</button>
                  <button class="button">Attach</button>
                  <p class="note">Note: .csv, .xls, .xlsx, .vsd, .doc, .docx, .ppt, .pptx, .pdf, .zip file formats are supported. Max. of 2 files of max. 1mb each.</p>
                  <table class="displayGrid rounded shadow wHalf">
                    <thead>
                      <tr>                        
                        <th>File Name</th>
                        <th class="w80 right">Size (bytes)</th>
						<th class="w15">Delete</th>
                      </tr>
                    </thead>
                    <tbody>
                      <tr>
                        <td><a href="#">DocumentName1</a></td>
                        <td class="w80 right">678</td>
						<td class="w15"><a href="#"><img src="<html:imagesPath/>delete.png" width="16" height="16" alt="Remove" title="Remove"></a></td>
                      </tr>
                    </tbody>
                  </table>
                </div>
                --%>
              </div>
            </div>
          </div>
        </div>
        <script>
        jQuery(function() {
            jQuery("h4.expand").toggler();
        });
        var isFileUploaded;
        jQuery('#uploadButn').click(function(){
			
			
			if(jQuery('#textInput').val()!=""){					
				var actionURL="${attachDocumentAction}&timezoneOffset="+timezoneOffset;
				jQuery('#fileUploadFormId').attr('action',actionURL);
				
				jQuery('#fileUploadFormId').submit();
					
				
			}else{
				jAlert("<spring:message code='requestInfo.message.massUpload.errorMsg.pleaseUploadFile'/>");
				}
			
			
		});

        jQuery('#fileUploadInput').change(function(){
			jQuery('#textInput').val(jQuery('#fileUploadInput').val());	
		});
        function showUploadedDetails(){
    		hideOverlay();
    		//alert('in changeIframeStyle');
    		
    			//jQuery('#upload_target').attr('style','width:500px;height:70px;border:0px;overflow:hidden!important;background-color:white;');
				jQuery('#uploadInformationDiv').html(jQuery('#upload_target').contents().find('div:first').html()==null?"":jQuery('#upload_target').contents().find('div:first').html());
				jQuery('#textInput').val('');
				//alert(isFileUploaded);
    		}
        function removeFile(fileIndex){

    		var actionURL="${removeAttachmentURL}&t="+new Date().getTime();
    		jQuery('#fileIndex').val(fileIndex);
    		jQuery('#fileUploadFormId').attr('action',actionURL);
    		showOverlay();
    		jQuery('#fileUploadFormId').submit();	
    		
    		
    	}
        function downloadUploadedFile(fileIndex){
    		//alert(jQuery("[name='sessionFileKey']").val());
    		var isHw=true;
    		window.location="<portlet:resourceURL id="downloadUploadedAttachment"/>&t="+new Date().getTime()+"&sessionKey="+jQuery("[name='sessionFileKey']").val()+"&index="+fileIndex+"&isHw="+isHw;
    		}
        </script>