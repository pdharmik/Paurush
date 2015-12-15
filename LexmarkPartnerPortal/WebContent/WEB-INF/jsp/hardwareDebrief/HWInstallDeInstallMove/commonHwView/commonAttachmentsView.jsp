 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="util" uri="http://lexmark.com/tld/util" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="/WEB-INF/tld/liferay-portlet.tld" prefix="portlet"%>
<%@ taglib prefix="html" tagdir="/WEB-INF/tags/html" %>
<portlet:resourceURL id="downloadHardwareAttachment" var="vardownloadHardwareAttachment"/>
<c:if test="${fn:length(hardwareDebriefForm.userEnteredActivity.attachmentList)>0}">

<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/in-styles.css?version=<html:fileCacheVersion/>" />
<div class="portletBlock infoBox rounded shadow">
          <div class="columnsOne">
            <div class="columnInner">
              <h4><spring:message code="requestInfo.hardwareDebreief.common.view.attachments.header"/></h4>
              <div class="wHalf">
					<table class="displayGrid2 rounded shadow wFull">
						<thead>
							<tr>
								
								<th><spring:message code="requestInfo.hardwareDebreief.common.view.attachments.attachmentName"/></th>
								<th class="w100"><spring:message code="requestInfo.hardwareDebreief.common.view.attachments.size"/></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="attachment" items="${hardwareDebriefForm.userEnteredActivity.attachmentList}" varStatus="statusAttachments">
										<c:choose>
												<c:when test="${statusAttachments.count%2==0}">
													<tr class="altRow">
												</c:when>
												<c:otherwise>
													<tr>
												</c:otherwise>
											</c:choose>
											
												<td><a onClick="downloadAttachment(this)" class="cursor-pointer">${attachment.attachmentName}</a></td>
												<input type="hidden" name="userEnteredActivity.attachmentList[${statusAttachments.index}].attachmentName" value="${attachment.attachmentName}"/>
												
											
											
												<td>${attachment.size}
												<input type="hidden" name="userEnteredActivity.attachmentList[${statusAttachments.index}].size" value="${attachment.size}"/>
												</td> 
											</tr>
									
						</c:forEach>	
						</tbody>
					</table>
				</div>
            </div>
          </div>
        </div>
        </c:if>
        <script>
        	function downloadAttachment(obj){
        		window.location.href="${vardownloadHardwareAttachment}&fName="+jQuery(obj).text()+"&activityId=${hardwareDebriefForm.activity.activityId}";
        		
        	}
        </script>