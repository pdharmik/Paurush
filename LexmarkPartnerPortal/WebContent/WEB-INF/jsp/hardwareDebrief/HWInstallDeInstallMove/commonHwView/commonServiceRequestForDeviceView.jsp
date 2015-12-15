<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="util" uri="http://lexmark.com/tld/util" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div class="portletBlock infoBox rounded shadow">
          <div class="columnsOne">
            <div class="columnInner">
              <h4><spring:message code="requestInfo.hardwareDebreief.common.closeOut.serviceInfoForDevice.header"/></h4>
              <div class="portletBlock">
          <div class="columnsTwo">
            <div class="infoBox columnInner rounded shadow">
             <h4 class="gray">
             <c:choose>
              <c:when test='${isHwMove}'>
              	<spring:message code="requestInfo.hardwareDebreief.common.closeOut.serviceInfoForDevice.moveToAddr"/>
              </c:when>
              <c:otherwise>
              	<spring:message code="requestInfo.hardwareDebreief.common.closeOut.serviceInfoForDevice.deviceInstalledAddress"/>
              </c:otherwise>
             </c:choose>
             </h4>
              <ul class="roDisplay">
               <util:addressOutput value="${hardwareDebriefForm.activity.serviceRequest.asset.installAddress}" displayInDivs="true"></util:addressOutput>
              </ul>
              <ul class="form division">
                <li>
                  <label for="building"><spring:message code="claimCreate.addressInfo.label.building"/></label>
                  <span>
                  ${hardwareDebriefForm.activity.serviceRequest.asset.installAddress.physicalLocation1}
                  </span></li>
                <li>
                  <label for="floor"><spring:message code="claimCreate.addressInfo.label.floor"/></label>
                  <span>
                  ${hardwareDebriefForm.activity.serviceRequest.asset.installAddress.physicalLocation2}
                  </span></li>
                <li>
                  <label for="office"><spring:message code="claimCreate.addressInfo.label.office"/></label>
                  <span>
                 ${hardwareDebriefForm.activity.serviceRequest.asset.installAddress.physicalLocation3}
                  </span></li>
                  <c:if test="${hardwareDebriefForm.userEnteredActivity.serviceRequest.asset.installAddress.coordinatesXPreDebriefRFV != null 
                  && hardwareDebriefForm.userEnteredActivity.serviceRequest.asset.installAddress.coordinatesXPreDebriefRFV != ''}">
                  <li><label for="coordinateX">Coordinate X</label> 
						 	<span>${hardwareDebriefForm.userEnteredActivity.serviceRequest.asset.installAddress.coordinatesXPreDebriefRFV}</span>
						 	</li>
                  </c:if>
                  <c:if test="${hardwareDebriefForm.userEnteredActivity.serviceRequest.asset.installAddress.coordinatesYPreDebriefRFV != null 
                  && hardwareDebriefForm.userEnteredActivity.serviceRequest.asset.installAddress.coordinatesYPreDebriefRFV != ''}">
                  <li><label for="coordinateY">Coordinate Y</label>
							<span>${hardwareDebriefForm.userEnteredActivity.serviceRequest.asset.installAddress.coordinatesYPreDebriefRFV}</span>
							</li>
                  </c:if>
				  
              </ul>
            </div>
          </div>
           <c:if test='${isHwMove}'>
          <div class="columnsTwo">
            <div class="infoBox columnInner rounded shadow">
<%--               <h4 class="gray"><spring:message code="requestInfo.hardwareDebreief.common.closeOut.serviceInfoForDevice.moveToAddr"/></h4> --%>
			   <h4 class="gray"><spring:message code="requestInfo.hardwareDebreief.common.closeOut.serviceInfoForDevice.deviceInstalledAddress"/></h4>
              <ul class="roDisplay">
               <li> <c:choose>
                  <c:when test="${not empty hardwareDebriefForm.userEnteredActivity.serviceRequest.asset.moveFromAddress.addressLine1}">
                  <%--	<util:convertToGenericAddress attrName="moveToAddressDisplayServiceInfo" addressString="${hardwareDebriefForm.userEnteredActivity.serviceRequest.asset.moveToAddressGrouped}"></util:convertToGenericAddress>
                    <util:addressOutput value="${moveToAddressDisplayServiceInfo}" displayInDivs="true"></util:addressOutput>
                   --%>
                   <div>${hardwareDebriefForm.userEnteredActivity.serviceRequest.asset.storeFrontName}</div>
                    <util:addressOutput value="${hardwareDebriefForm.userEnteredActivity.serviceRequest.asset.moveFromAddress}" displayInDivs="true"></util:addressOutput>
                   </c:when>
                   <c:otherwise>
                   <spring:message code="requestInfo.hardwareDebreief.common.closeOut.serviceInfoForDevice.addrNotAvl"/>
                   </c:otherwise>
                   </c:choose> 
               </li>
              </ul>
              <ul class="form division">
                <li>
                  <label for="building"><spring:message code="claimCreate.addressInfo.label.building"/></label>
                  <span>
                  ${hardwareDebriefForm.userEnteredActivity.serviceRequest.asset.moveFromAddress.physicalLocation1}
                  </span></li>
                <li>
                  <label for="floor"><spring:message code="claimCreate.addressInfo.label.floor"/></label>
                  <span>
                  ${hardwareDebriefForm.userEnteredActivity.serviceRequest.asset.moveFromAddress.physicalLocation2}
                  </span></li>
                <li>
                  <label for="office"><spring:message code="claimCreate.addressInfo.label.office"/></label>
                  <span>
                 ${hardwareDebriefForm.userEnteredActivity.serviceRequest.asset.moveFromAddress.physicalLocation3}
                  </span></li>
              </ul> 
            </div>
           </div>
        </c:if>
          
  <%--
          <div class="columnsTwo">
            <div class="infoBox columnInner rounded shadow">
              <h4>Primary Device Contact</h4>
              <table width="100%" border="0" cellspacing="0" cellpadding="0" class="dataGrid">
                <tr>
                  <td width="100" class="label">Name:</td>
                  <td>${fn:trim(hardwareDebriefForm.activity.serviceRequest.primaryContact.firstName)}&nbsp;${fn:trim(hardwareDebriefForm.activity.serviceRequest.primaryContact.lastName)}</td>
                </tr>
                <tr>
                  <td width="150" class="label">Phone:</td>
                  <td>${fn:trim(hardwareDebriefForm.activity.serviceRequest.primaryContact.workPhone)}</td>
                </tr>
                <tr>
                  <td class="label">Email Address:</td>
                  <td>${fn:trim(hardwareDebriefForm.activity.serviceRequest.primaryContact.emailAddress)}</td>
                </tr>               
              </table>
            </div>
			<div class="infoBox columnInner rounded shadow">
              <h4>Secondary Device Contact</h4>
              <table width="100%" border="0" cellspacing="0" cellpadding="0" class="dataGrid">
                <tr>
                  <td width="100" class="label">Name:</td>
                  <td>${fn:trim(serviceRequest.secondaryContact.firstName)}&nbsp;${fn:trim(serviceRequest.secondaryContact.lastName)}</td>
                </tr>
                <tr>
                  <td width="150" class="label">Phone:</td>
                  <td>${fn:trim(serviceRequest.secondaryContact.workPhone)}</td>
                </tr>
                <tr>
                  <td class="label">Email Address:</td>
                  <td>${fn:trim(serviceRequest.secondaryContact.emailAddress)}</td>
                </tr>               
              </table>
            </div>
            </div> --%>
        
      
        </div>
            </div>
          </div>
</div>
