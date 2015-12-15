<%@ taglib prefix="html" tagdir="/WEB-INF/tags/html" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.Date"%>


<%@ taglib uri="/WEB-INF/tld/lexmark-util.tld" prefix="lexmarkUtil"%>

<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/mps.css?version=<html:fileCacheVersion/>" />
<!--[if IE 7]>
	<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/mps_ie7.css?version=<html:fileCacheVersion/>" />
<![endif]-->
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/in-styles.css?version=<html:fileCacheVersion/>" />


<div id="uploadTotalContent">
<c:set var="errorOccured" value="${empty errors.allErrors}"/>
<spring:hasBindErrors name="fileUploadForm">
<br/>
          	<div class="error">
				<c:forEach var="error" items="${errors.allErrors}">
			   		<li><strong><spring:message code="${error.code}" arguments="${error.arguments}" /></strong></li>
            	</c:forEach>
     		</div>	
</spring:hasBindErrors>      



<%-- 
<c:if test="${errorOccured}">	--%> 
<c:if test="${ not empty fileUploadDetailsMap}">     
 <table class="displayGrid2 rounded shadow ">
                    <thead>
                      <tr>
                      	<th></th>
                        <th><spring:message code="attachment.message.FileName"/></th>
                        <th class="w80 right"><spring:message code="requestInfo.heading.attachmentFileSizeBytes"/></th>
                        
                      </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${fileUploadDetailsMap}" var="fileUploadDetails" varStatus = "status" >
						

                      <tr>
                        <td class="w15"><img id="img_help1"  src="<html:imagesPath/>delete.gif" alt="Remove" height="15" width="15" onclick="parent.removeFile('${status.index}');" /></td>
                        <td class="word-break-break-all"><a class="cursor-pointer" onclick="parent.downloadUploadedFile('${status.index}')">${fileUploadDetails.value.displayFileName}</a></td>
                        <td class="w80 right">${fileUploadDetails.value.fileSize}</td>
                        
                                         
                      </tr>
                     </c:forEach>
                    </tbody>
</table>
</c:if>
<%-- 
</c:if>--%>      
</div>
<script>
//jQuery(document).ready(function(){

	//jQuery('.portal-popup').removeClass('portal-popup');
	
	parent.isFileUploaded="${ not empty fileUploadDetailsMap}";
	parent.showUploadedDetails();
	
	 
//});
</script>