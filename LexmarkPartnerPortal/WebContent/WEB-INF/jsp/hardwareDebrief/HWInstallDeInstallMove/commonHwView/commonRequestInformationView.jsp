<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="util" uri="http://lexmark.com/tld/util" %> 
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="html" tagdir="/WEB-INF/tags/html" %>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/in-styles.css?version=<html:fileCacheVersion/>" />
        <!-- Request Information BLOCK - Start -->
        <%--Changes for Jun release MPS 2.1 10913 --%>
	<div style="display:none;">
		<div id="commentsDiv">
		<div id="commentErrorDiv" class="error" style="display:none;">
			<p><spring:message code="requestInfo.hardwareDebreief.common.reject"/></p>
		</div>
		<ul>
			<li class="first list-style-none" >
			<div claas="list-div1"><spring:message code="claim.label.comments"/>: </div>
			 <div class="list-div2"> <textarea id="reject_textArea" class="width-175px"></textarea></div>
			 <div class="clear-both"></div>
			 </li>
			<li class="buttonContainer list-style-none" >
			<button class="button button22" id="ok_butn" onclick="commentConfirm()"><spring:message code="button.ok"/></button><button class="button button_cancel22" onClick="closeDialog()"><spring:message code="button.cancel"/></button>
			</li>
		
		</ul>
		
		</div>
	</div>
	<%--Ends Changes for Jun release MPS 2.1 10913 --%>
        <div class="portletBlock infoBox rounded shadow">
         <div class="columnInner">
        <h4><spring:message code="requestInfo.hardwareDebreief.common.closeOut.requestInformation.reqInfo"/></h4>
          </div><div class="div-style15">
            <div class="columnInner">
              
               <div id="acceptRejectButton">
               
               <c:if test="${fn:toUpperCase(sessionScope.ldapUserData_PHASE2.USERSEGMENT)!='EMPLOYEE' || showFseSelector=='true'}">
               <c:if test="${hardwareDebriefForm.offlineDebrief == false}">
               <c:if test="${not exceptionOccured_Detail  eq true}">
              	<c:if test="${(hardwareDebriefForm.activity.activitySubStatus == 'Pending SP Acknowledgement' || hardwareDebriefForm.activity.activitySubStatus =='') && hardwareDebriefForm.activity.activityStatus != 'Completed' && hardwareDebriefForm.activity.activityStatus != 'Cancelled Service'}">
              		<div class="dataGridCust"><button type="button" id="accept_${hardwareDebriefForm.activity.activityId}" class="button button21" onclick="acceptAndUpdate()"><spring:message code="requestInfo.hardwareDebreief.common.closeOut.requestInformation.updateReq.button"/></button></div>	              		
              		<div class="dataGridCust"><button type="button" id="reject_${hardwareDebriefForm.activity.activityId}"  class="button button21" onclick="acceptRejectRequest('Not Accepted','${hardwareDebriefForm.activity.serviceRequest.serviceRequestNumber}','${hardwareDebriefForm.activity.activityId}',id)"><spring:message code="requestInfo.hardwareDebreief.common.closeOut.requestInformation.rejectReq.button"/></button></div>
              		<div class="dataGridCust" style="display:none;" id="update_${hardwareDebriefForm.activity.activityId}"><button type="button" class="button button21" onclick="redirectToDebriefViewAndCloseOut('${hardwareDebriefForm.activity.serviceRequest.serviceRequestNumber}','${hardwareDebriefForm.activity.activityType.value}','closeOut','${hardwareDebriefForm.activity.activityId}')"><spring:message code="claim.button.updateRequest"/></button></div>
              		
				</c:if>
				<c:if test="${hardwareDebriefForm.activity.activitySubStatus != 'Pending SP Acknowledgement' && hardwareDebriefForm.activity.activitySubStatus != 'Not Accepted' && hardwareDebriefForm.activity.activityStatus != 'Completed' && hardwareDebriefForm.activity.activitySubStatus !='' && hardwareDebriefForm.activity.activityStatus != 'Cancelled Service'}">
				<%-- the onclick method is defined in the main page --%>
					<div class="dataGridCust"><button type="button" class="button button21" onclick="redirectToDebriefViewAndCloseOut('${hardwareDebriefForm.activity.serviceRequest.serviceRequestNumber}','${hardwareDebriefForm.activity.activityType.value}','closeOut','${hardwareDebriefForm.activity.activityId}')"><spring:message code="claim.button.updateRequest"/></button></div>							
				</c:if>
				</c:if>		
				</c:if>
				</c:if>
				</div>				
            </div>
          </div>
          <div class="div-style13" >
            <div class="columnInner">
              
              <table width="100%" border="0" cellspacing="0" cellpadding="0" class="dataGrid">
                <tr>
                  <td width="250" class="label"><spring:message code="requestInfo.hardwareDebreief.common.closeOut.requestInformation.reqNo"/></td>
                  <td>${hardwareDebriefForm.activity.serviceRequest.serviceRequestNumber}</td>
                </tr>
                <tr>
                  <td width="250" class="label"><spring:message code="requestInfo.hardwareDebreief.common.closeOut.requestInformation.reqType"/></td>
                  <td>${hardwareDebriefForm.activity.activityType.name}</td>
                </tr>
                <tr>
                  <td width="150" class="label"><spring:message code="requestInfo.hardwareDebreief.common.closeOut.requestInformation.installActivityNo"/></td>
                  <td>${hardwareDebriefForm.activity.activityId}</td>
                </tr>
                <tr>
                  <td class="label"><spring:message code="requestInfo.hardwareDebreief.common.closeOut.requestInformation.serviceProviderRefNo"/></td>
                  <td>${hardwareDebriefForm.activity.serviceProviderReferenceNumber}</td>
                </tr>
                <tr>
                  <td class="label"><spring:message code="requestInfo.hardwareDebreief.common.closeOut.requestInformation.openDate/Time"/></td>
                  <td><util:dateFormat value="${hardwareDebriefForm.activity.serviceRequest.serviceRequestDate}" showTime="true" timezoneOffset="${tzOffset}"></util:dateFormat>
                  </td>
                </tr>
                <tr>
                  <td class="label"><spring:message code="requestInfo.hardwareDebreief.common.closeOut.requestInformation.requestStatus"/></td>
                  <td>${hardwareDebriefForm.activity.activityStatus.value}</td>
                </tr>
                <tr>
                  <td class="label"><spring:message code="requestInfo.hardwareDebreief.common.closeOut.requestInformation.statusDetail"/></td>
                  <td>${hardwareDebriefForm.activity.activitySubStatus.value}</td>
                </tr>
                <tr>
                  <td class="label"><spring:message code="requestInfo.hardwareDebreief.common.closeOut.requestInformation.projectName"/></td>
                  <td>${hardwareDebriefForm.activity.serviceRequest.projectName}</td>
                </tr>
                <tr>
                  <td class="label"><spring:message code="requestInfo.hardwareDebreief.common.closeOut.requestInformation.projectPhase"/></td>
                  <td>${hardwareDebriefForm.activity.serviceRequest.projectPhase}</td>
                </tr>
                 <tr>
                  <td class="label"><spring:message code="requestInfo.hardwareDebreief.common.closeOut.closeOutInstallActivity.dispatchedDate"/></td>
                  <td><util:dateFormat value="${hardwareDebriefForm.activity.dispatchDate}" showTime="true" timezoneOffset="${tzOffset}"></util:dateFormat>
                  </td>
                </tr>
              </table>
            </div>
          </div>
			<div class="div-style14" >
 			<table width="100%" border="0" cellspacing="0" cellpadding="0" class="dataGrid">
 			<c:if test="${hardwareDebriefForm.activity.debriefStatus ne null and hardwareDebriefForm.activity.debriefStatus ne ''}">
                <tr>
                  <td class="label width-35per-important"><spring:message code="requestInfo.label.hardwareDebrief.debriefStatus"/>:</td>
                  <td class="word-break-break-word">${hardwareDebriefForm.activity.debriefStatus}</td>
                </tr>
                 <tr>
                  <td class="label width-35per-important"><spring:message code="requestInfo.label.hardwareDebrief.debriefError"/>:</td>
                  <td class="word-break-break-word">${fn:replace(fn:replace(hardwareDebriefForm.activity.rfvErrors,"\"",""),";"," ")}</td>
                </tr>
              </c:if>
  			</table>
		</div>
<div class="clear-both"></div>
          
        </div>
        <!-- Request Information BLOCK - End -->