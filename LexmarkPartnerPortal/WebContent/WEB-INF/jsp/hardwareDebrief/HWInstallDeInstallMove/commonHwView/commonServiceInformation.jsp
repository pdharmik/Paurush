<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="util" uri="http://lexmark.com/tld/util" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="html" tagdir="/WEB-INF/tags/html" %>
           <c:choose>
             <c:when test="${hardwareDebriefForm.activity.serviceRequest.secondaryContact.firstName == ''
               && hardwareDebriefForm.activity.serviceRequest.secondaryContact.lastName == ''
               && hardwareDebriefForm.activity.serviceRequest.secondaryContact.workPhone == ''
               && hardwareDebriefForm.activity.serviceRequest.secondaryContact.alternatePhone == ''
               && hardwareDebriefForm.activity.serviceRequest.secondaryContact.emailAddress == ''}">
                    
                <c:set var="HideSecondary" value="yes"/>            
				<c:set var="className" value="columnsThird"/>
				<c:set var="minHeight" value="150px"/>
				<c:if test='${isHwMove}'>
				<c:set var="className" value="wQuarter"/>
				<c:set var="minHeight" value="190px"/>
				</c:if>
		    </c:when>
			 <c:otherwise>
				<c:set var="HideSecondary" value="no"/>
				<c:set var="className" value="wQuarter"/>
				<c:set var="minHeight" value="150px"/>
				<c:if test='${isHwMove}'>
				<c:set var="className" value="wFiveColumns"/>
				<c:set var="minHeight" value="150px"/>
				</c:if>
			 </c:otherwise>
</c:choose>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/in-styles.css?version=<html:fileCacheVersion/>" />
        <!-- Service Information BLOCK - Start -->
        <div class="portletBlock infoBox rounded shadow">
          <div class="columnsOne">
            <div class="columnInner">
              <h4><spring:message code="requestInfo.hardwareDebreief.servInfo.header"/></h4>
			  <div class=" columnInner rounded shadow">
			  <div class="${className} floatL">
			    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="dataGrid">
				<tr >
				<%--This is for Install debrief view  --%>
				<c:if test='${isHwInstall}'>
					<%--<c:if test="${not empty hardwareDebriefForm.activity.serviceRequest.asset.customerAccount.address.addressLine1}">--%>
					<td class="strong center gray"><u><spring:message code="requestInfo.hardwareDebreief.servInfo.custAccAddr"/></u></td>
					<%--</c:if>--%>
					<c:set var="address" value="${hardwareDebriefForm.activity.serviceRequest.asset.customerAccount.address}"/>
					<c:set var="preName" value="activity.serviceRequest.asset.customerAccount.address"/>
				</c:if>
				<%--ENDS This is for Install debrief view  --%>
				
				<%--This is for De Install/ De Commission debrief view  --%>
				<c:if test='${isHwDeInstall }'>
				<%--<c:if test="${not empty hardwareDebriefForm.activity.serviceRequest.asset.pickupAddress.addressLine1}">--%>
					<td class="strong center gray"><u><spring:message code="requestInfo.hardwareDebreief.servInfo.pickUpAddr"/></u></td>
				<%--</c:if>--%>
					<c:set var="address" value="${hardwareDebriefForm.activity.serviceRequest.asset.pickupAddress}"/>
					<c:set var="preName" value="activity.serviceRequest.asset.pickupAddress"/>
				</c:if>
				<%--ENDS This is for De Install/ De Commission debrief view  --%>
				
				<%--This is for Move debrief view  --%>
				<c:if test='${isHwMove}'>
					
						<td class="strong center gray"><u><spring:message code="requestInfo.hardwareDebreief.common.closeOut.serviceInfoForDevice.moveToAddr"/></u></td>	
					
					<c:set var="address" value="${hardwareDebriefForm.activity.serviceRequest.asset.installAddress}"/>
					<c:set var="preName" value="activity.serviceRequest.asset.installAddress"/>
				</c:if>
				<%--ENDS This is for Move debrief view  --%>
                
				</tr>
				</table>
				
				
				<%--Section for Customer Address --%>
				<table width="100%" border="0" cellspacing="0" cellpadding="0" class="dataGrid">
				<tr><td>
					<util:addressOutput value="${address}" displayInDivs="true"></util:addressOutput>
					</td>
				</tr>
              	</table>
              	
              	<c:if test="${not empty address.physicalLocation1}">
              		<div><spring:message code="claimCreate.addressInfo.label.building"/> ${address.physicalLocation1}</div>
              	
              	</c:if>
              	<c:if test="${not empty address.physicalLocation2}">
				<div><spring:message code="claimCreate.addressInfo.label.floor"/> ${address.physicalLocation2}</div>
				
				</c:if>
				<c:if test="${not empty address.physicalLocation3}">
				<div><spring:message code="claimCreate.addressInfo.label.office"/> ${address.physicalLocation3}</div>
				
				</c:if>
				<div style="display:none">
				<%-- This tag used for hidden input fields that are set when address selected from popup. --%>
					<util:generateAddressInputFields value="${address}" index="" preName="${preName}" postName="."></util:generateAddressInputFields>
					<input type="hidden" name="${preName}.physicalLocation1" value="${address.physicalLocation1}"/>
					<input type="hidden" name="${preName}.physicalLocation2" value="${address.physicalLocation2}"/>
					<input type="hidden" name="${preName}.physicalLocation3" value="${address.physicalLocation3}"/>
					
				</div>
				
               </div>
               	<%--This is for Move debrief view  --%>
               	
               
				<c:if test='${isHwMove}'>
						
						<div class="${className} separatorV floatL" style="min-height: ${minHeight};">
			   				 <table width="100%" border="0" cellspacing="0" cellpadding="0" class="dataGrid">
			   				 
								<tr><td class="strong center gray"><u><spring:message code="requestInfo.hardwareDebreief.servInfo.devInstAddr"/></u></td></tr>
							
							</table>
							
							<%--Section for NEW  Address --%>
							<table width="100%" border="0" cellspacing="0" cellpadding="0" class="dataGrid">
							<tr><td>
							<%-- <util:convertToGenericAddress attrName="moveToAddressDisplay" addressString="${hardwareDebriefForm.activity.serviceRequest.asset.moveToAddressGrouped}"></util:convertToGenericAddress>
								<util:addressOutput  value="${moveToAddressDisplay}" displayInDivs="true" ></util:addressOutput> --%>
							<div>${hardwareDebriefForm.userEnteredActivity.serviceRequest.asset.storeFrontName}</div>
                    		<util:addressOutput value="${hardwareDebriefForm.userEnteredActivity.serviceRequest.asset.moveFromAddress}" displayInDivs="true"></util:addressOutput>	
								
								
							</td></tr>
			                </table>
			                
			                <c:if test="${not empty hardwareDebriefForm.userEnteredActivity.serviceRequest.asset.moveFromAddress.physicalLocation1}">
			                <div><spring:message code="claimCreate.addressInfo.label.building"/> ${hardwareDebriefForm.userEnteredActivity.serviceRequest.asset.moveFromAddress.physicalLocation1}</div>
			                
			                </c:if>
			                <c:if test="${not empty hardwareDebriefForm.userEnteredActivity.serviceRequest.asset.moveFromAddress.physicalLocation2}">
							<div><spring:message code="claimCreate.addressInfo.label.floor"/> ${hardwareDebriefForm.userEnteredActivity.serviceRequest.asset.moveFromAddress.physicalLocation2}</div>
							
							</c:if>
							<c:if test="${not empty hardwareDebriefForm.userEnteredActivity.serviceRequest.asset.moveFromAddress.physicalLocation3}">
							<div><spring:message code="claimCreate.addressInfo.label.office"/> ${hardwareDebriefForm.userEnteredActivity.serviceRequest.asset.moveFromAddress.physicalLocation3}</div>
							
							</c:if>
						</div>
						<div style="display:none">
					<util:generateAddressInputFields value="${moveToAddressDisplay}" index="" preName="activity.serviceRequest.asset.moveToAddress" postName="."></util:generateAddressInputFields>
					<input type="hidden" name="activity.serviceRequest.asset.moveToAddress.physicalLocation1" value="${moveToAddressDisplay.physicalLocation1}"/>
					<input type="hidden" name="activity.serviceRequest.asset.moveToAddress.physicalLocation2" value="${moveToAddressDisplay.physicalLocation2}"/>
					<input type="hidden" name="activity.serviceRequest.asset.moveToAddress.physicalLocation3" value="${moveToAddressDisplay.physicalLocation3}"/>
					
				</div>
				</c:if>
				<%--ENDS This is for Move debrief view  --%>
              
              
			 
			  <div class="${className} separatorV floatL " style="min-height: ${minHeight};">
			    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="dataGrid">
				<tr>
				<td class="strong center gray"><u><spring:message code="requestInfo.hardwareDebreief.servInfo.primaryContact"/></u></td>
				</tr>
				</table>
				<table width="100%" border="0" cellspacing="0" cellpadding="0" class="dataGrid">
                <tr>
                  <td width="160" class="label "><spring:message code="requestInfo.hardwareDebreief.servInfo.name"/></td>
                  <td class="textWrap">${fn:trim(hardwareDebriefForm.activity.serviceRequest.primaryContact.firstName)}&nbsp;${fn:trim(hardwareDebriefForm.activity.serviceRequest.primaryContact.lastName)}</td>
                  <form:hidden path="activity.serviceRequest.primaryContact.firstName"/>
                  <form:hidden path="activity.serviceRequest.primaryContact.lastName"/>
                </tr>
                <tr>
                  <td class="label"><spring:message code="requestInfo.hardwareDebreief.servInfo.primaryPh"/></td>
                  <td class="textWrap">${fn:trim(hardwareDebriefForm.activity.serviceRequest.primaryContact.workPhone)}</td>
                  <form:hidden path="activity.serviceRequest.primaryContact.workPhone"/>
                </tr>
                 <tr>
                  <td class="label"><spring:message code="requestInfo.hardwareDebreief.servInfo.alternatePh"/></td>
                  <td class="textWrap">${fn:replace(fn:trim(hardwareDebriefForm.activity.serviceRequest.primaryContact.alternatePhone),'undefined','')}</td>
                  <form:hidden path="activity.serviceRequest.primaryContact.alternatePhone"/>
                </tr>
                <tr>
                  <td class="label"><spring:message code="requestInfo.hardwareDebreief.common.closeOut.serviceInfoForDevice.email"/></td>
                  <td class="textWrap">${fn:trim(hardwareDebriefForm.activity.serviceRequest.primaryContact.emailAddress)}</td>
                  <form:hidden path="activity.serviceRequest.primaryContact.emailAddress"/>
                </tr>                
              </table>
			  </div>
			 
			  
			  <c:if test="${HideSecondary == 'no'}">
			  
			  <div class="${className} separatorV floatL" style="min-height: ${minHeight};">
			    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="dataGrid">
				<tr>
				
				<td class="strong center gray" ><u><spring:message code="requestInfo.hardwareDebreief.servInfo.secnContact"/></u></td>
                  
				</tr>
				</table>
				<table width="100%" border="0" cellspacing="0" cellpadding="0" class="dataGrid">
                <tr>
                  <td width="160" class="label"><spring:message code="requestInfo.hardwareDebreief.servInfo.name"/></td>
                   <td class="textWrap">${fn:trim(hardwareDebriefForm.activity.serviceRequest.secondaryContact.firstName)}&nbsp;${fn:trim(hardwareDebriefForm.activity.serviceRequest.secondaryContact.lastName)}</td>
                   <form:hidden path="activity.serviceRequest.secondaryContact.firstName"/>
                   <form:hidden path="activity.serviceRequest.secondaryContact.lastName"/>
                </tr>
                <tr>
                  <td class="label"><spring:message code="requestInfo.hardwareDebreief.servInfo.primaryPh"/></td>
                  <td class="textWrap">${fn:trim(hardwareDebriefForm.activity.serviceRequest.secondaryContact.workPhone)}</td>
                  <form:hidden path="activity.serviceRequest.secondaryContact.workPhone"/>
                   
                </tr>
                <tr>
                  <td class="label"><spring:message code="requestInfo.hardwareDebreief.servInfo.alternatePh"/></td>
                  <td class="textWrap">${fn:trim(hardwareDebriefForm.activity.serviceRequest.secondaryContact.alternatePhone)}</td>
                  <form:hidden path="activity.serviceRequest.secondaryContact.alternatePhone"/>
                </tr>
                <tr>
                  <td class="label"><spring:message code="requestInfo.hardwareDebreief.common.closeOut.serviceInfoForDevice.email"/></td>
                  <td class="textWrap">${fn:trim(hardwareDebriefForm.activity.serviceRequest.secondaryContact.emailAddress)}</td>
                  <form:hidden path="activity.serviceRequest.secondaryContact.emailAddress"/>
                </tr>                
              </table>
			  </div>
			  </c:if>
			  <div class="${className} separatorV floatL" style="min-height: ${minHeight} ;">
			    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="dataGrid">
				<tr>
				
				<td class="strong center gray" ><u><spring:message code="serviceRequest.label.requestorInfo"/></u></td>
                  
				</tr>
				</table>
				<table width="100%" border="0" cellspacing="0" cellpadding="0" class="dataGrid">
                <tr>
                  <td width="160" class="label"><spring:message code="requestInfo.hardwareDebreief.servInfo.name"/></td>
                   <td class="textWrap">${fn:trim(hardwareDebriefForm.activity.requestorFirstName)}&nbsp;${fn:trim(hardwareDebriefForm.activity.requestorLastName)}</td>
                   <form:hidden path="activity.requestorFirstName"/>
                   <form:hidden path="activity.requestorLastName"/>
                </tr>
                <tr>
                  <td class="label"><spring:message code="requestInfo.hardwareDebreief.servInfo.primaryPh"/></td>
                  <td class="textWrap">${fn:trim(hardwareDebriefForm.activity.requestorWorkPhone)}</td>
                  <form:hidden path="activity.requestorWorkPhone"/>
                   
                </tr>
                <tr>
                  <td class="label"><spring:message code="requestInfo.hardwareDebreief.common.closeOut.serviceInfoForDevice.email"/></td>
                  <td class="textWrap">${fn:trim(hardwareDebriefForm.activity.requestorEmail)}</td>
                  <form:hidden path="activity.requestorEmail"/>
                </tr>                
              </table>
			  </div>
			  <div class="clear-both"></div>
			  </div>
              <div class="lineClear">
              </div>
			  <table width="100%" border="0" cellspacing="0" cellpadding="0" class="dataGrid">
			  	<tr>
			  		<td width="20%" class="label leftBlock"><spring:message code="requestInfo.hardwareDebreief.servInfo.helpdeskRefNo"/></td>
			  		<%-- Changes on field mapping on 24th jan --%>
			  		 <td width="80%">${hardwareDebriefForm.activity.serviceRequest.customerReferenceNumber}</td>
			  		 <form:hidden path="activity.serviceRequest.customerReferenceNumber"/>
			  		 <%-- Ends Changes on field mapping on 24th jan --%>
			  	</tr>
			  	<%-- Commented as per email by Joan added Expected Start Date,Expected Completion Date
                <tr>
                  <td width="200" class="label leftBlock"><spring:message code="requestInfo.hardwareDebreief.servInfo.respond"/></td>
                  <td>${hardwareDebriefForm.activity.serviceRequest.respondWithin}</td>
                  <form:hidden path="activity.serviceRequest.respondWithin"/>
                </tr>--%>
                <%-- 
                <tr>
                  <td  class="label">Customer Requested Response :</td>
                  <td><util:dateFormat value="${hardwareDebriefForm.activity.serviceRequest.customerRequestedResponseDate}" showTime="true" timezoneOffset="${tzOffset}"></util:dateFormat></td>
                </tr>
                --%>
                	<%--  <tr>
                  <td class="label leftBlock"><spring:message code="requestInfo.hardwareDebreief.servInfo.resolveWithin"/></td>
                  <td>${hardwareDebriefForm.activity.serviceRequest.resolveWithin}</td>
                  <form:hidden path="activity.serviceRequest.resolveWithin"/>
                </tr>
                <tr>
                  <td class="label leftBlock"><spring:message code="requestInfo.hardwareDebreief.servInfo.resolveBy"/></td>
                 
                  <td><util:dateFormat value="${hardwareDebriefForm.activity.resolutionDate}" showTime="true" timezoneOffset="${tzOffset}"></util:dateFormat>
                  <input type="hidden" id="serviceRequestEndDate" name="activity.resolutionDate" value="<util:dateFormat value="${hardwareDebriefForm.activity.resolutionDate}" showTime="true" timezoneOffset="${tzOffset}"/>"/>
                  </td>
                </tr>--%>
                
                <tr>
                  <td width="200" class="label leftBlock"><spring:message code="requestInfo.hardwareDebreief.common.closeOut.closeOutInstallActivity.expectedStartDate"/></td>
                  <td>
                  <util:dateFormat value="${hardwareDebriefForm.activity.customerRequestedResponseDate}" showTime="true"  timezoneOffset="${tzOffset}"></util:dateFormat>
                  <input  type="hidden" name="activity.customerRequestedResponseDate" value="<util:dateFormat value="${hardwareDebriefForm.activity.customerRequestedResponseDate}" showTime="true"  timezoneOffset="${tzOffset}"/>"/>
                  </td>
                </tr>
                <tr>
                  <td width="200" class="label leftBlock"><spring:message code="requestInfo.hardwareDebreief.common.closeOut.closeOutInstallActivity.expectedEndDate"/></td>
                  <td><util:dateFormat value="${hardwareDebriefForm.activity.serviceRequest.resolveWithin}" showTime="true"  timezoneOffset="${tzOffset}"></util:dateFormat>
                  <input  type="hidden" name="activity.serviceRequest.resolveWithin" value="<util:dateFormat value="${hardwareDebriefForm.activity.serviceRequest.resolveWithin}" showTime="true"  timezoneOffset="${tzOffset}"/>"/>
                              
                  </td>
                  
                </tr>
                
                <tr>
                  <td class="label leftBlock"><spring:message code="requestInfo.hardwareDebreief.servInfo.accHandling"/></td>
                  <c:choose>
                  	<c:when test="${fn:length(hardwareDebriefForm.activity.accountSpecialHandling) gt 100 }">
                  	<td class="textScroll">${hardwareDebriefForm.activity.accountSpecialHandling}</td>
                  	</c:when>
                  	<c:otherwise>
                  	<td>${hardwareDebriefForm.activity.accountSpecialHandling}</td>
                  	</c:otherwise>
                  </c:choose>
                  
                  
                  <form:hidden path="activity.serviceRequest.specialInstructions"/>
                </tr>
                
              </table>
			  
          </div>
        </div>
		</div>
        <!-- Service Information BLOCK - End --> 