<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="util" uri="http://lexmark.com/tld/util" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="html" tagdir="/WEB-INF/tags/html" %>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/in-styles.css?version=<html:fileCacheVersion/>" />
 <div class="columnInner">
	          	<h4><spring:message code="requestInfo.hardwareDebreief.common.closeOut.requestInformation.reqInfo"/></h4>
	        
<div class="div-style12" >
	        &nbsp;
          </div> 
<div class="div-style13">
            
              <table width="100%" border="0" cellspacing="0" cellpadding="0" class="dataGrid">
                <tr>
                  <td width="220" class="label"><spring:message code="requestInfo.hardwareDebreief.common.closeOut.requestInformation.reqNo"/></td>
                  <td>${hardwareDebriefForm.activity.serviceRequest.serviceRequestNumber}</td>
                  <form:hidden path="activity.serviceRequest.serviceRequestNumber"/>
                </tr>
                <tr>
                  <td width="220" class="label"><spring:message code="requestInfo.hardwareDebreief.common.closeOut.requestInformation.reqType"/></td>
                  <td>${hardwareDebriefForm.activity.activityType.name}</td>
                  <form:hidden path="activity.activityType.value"/>
                </tr>
                <tr>
                  <td width="150" class="label"><spring:message code="requestInfo.hardwareDebreief.common.closeOut.requestInformation.installActivityNo"/></td>
                  <td>${hardwareDebriefForm.activity.activityId}</td>
                  <form:hidden path="activity.activityId"/>
                  <form:hidden path="userEnteredActivity.activityId"/>
                </tr>
                <tr>
                  <td class="label"><spring:message code="requestInfo.hardwareDebreief.common.closeOut.requestInformation.serviceProviderRefNo"/></td>
                  <td><form:input path="userEnteredActivity.serviceProviderReferenceNumber"/></td>
                  
                </tr>
                <tr>
                  <td class="label"><spring:message code="requestInfo.hardwareDebreief.common.closeOut.requestInformation.openDate/Time"/></td>
                  <td><util:dateFormat value="${hardwareDebriefForm.activity.serviceRequest.serviceRequestDate}" showTime="true"  timezoneOffset="${tzOffset}"></util:dateFormat>
                  <input  type="hidden" name="activity.serviceRequest.serviceRequestDate" value="<util:dateFormat value="${hardwareDebriefForm.activity.serviceRequest.serviceRequestDate}" showTime="true"  timezoneOffset="${tzOffset}"/>"/>
                  </td>
                </tr>
                <tr>
                  <td class="label"><spring:message code="requestInfo.hardwareDebreief.common.closeOut.requestInformation.requestStatus"/></td>
                  <td>${hardwareDebriefForm.activity.activityStatus.name}</td>
                  <form:hidden path="activity.activityStatus.value"/>
                </tr>
                <tr>
                  <td class="label"><spring:message code="requestInfo.hardwareDebreief.common.closeOut.requestInformation.statusDetail"/></td>
                  <td>${hardwareDebriefForm.activity.activitySubStatus.name}</td>
                  <form:hidden path="activity.activitySubStatus.value"/>
                </tr>
                <tr>
                  <td class="label"><spring:message code="requestInfo.hardwareDebreief.common.closeOut.requestInformation.projectName"/></td>
                  <td>${hardwareDebriefForm.activity.serviceRequest.projectName}</td>
                  <form:hidden path="activity.serviceRequest.projectName"/>
                </tr>
                <tr>
                  <td class="label"><spring:message code="requestInfo.hardwareDebreief.common.closeOut.requestInformation.projectPhase"/></td>
                  <td>${hardwareDebriefForm.activity.serviceRequest.projectPhase}</td>
                  <form:hidden path="activity.serviceRequest.projectPhase"/>
                </tr>
                <tr>
                  <td class="label"><spring:message code="requestInfo.hardwareDebreief.common.closeOut.closeOutInstallActivity.dispatchedDate"/></td>
                   <td><util:dateFormat value="${hardwareDebriefForm.activity.dispatchDate}" showTime="true"  timezoneOffset="${tzOffset}"></util:dateFormat></td>
                  <form:hidden path="activity.dispatchDate"/>
                </tr>
              </table>
            </div>
          
          <div class="div-style14">
 			<table width="100%" border="0" cellspacing="0" cellpadding="0" class="dataGrid">
 			<c:if test="${hardwareDebriefForm.activity.debriefStatus ne null and hardwareDebriefForm.activity.debriefStatus ne ''}">
                <tr>
                  <td  class="label width-35per-important"><spring:message code="requestInfo.label.hardwareDebrief.debriefStatus"/>:</td>
                  <td class="word-break-break-word">${hardwareDebriefForm.activity.debriefStatus}</td>
                </tr>
                 <tr>
                  <td class="label width-35per-important"><spring:message code="requestInfo.label.hardwareDebrief.debriefError"/>:</td>
                  <td class="word-break-break-word">${fn:replace(fn:replace(hardwareDebriefForm.activity.rfvErrors,"\"",""),";"," ")}</td>
                </tr>
              </c:if>
  			</table>
		</div>
		  </div>