<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="util" uri="http://lexmark.com/tld/util" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="/WEB-INF/tld/liferay-portlet.tld" prefix="portlet"%>
<portlet:resourceURL var="encryptJSONVar" id="encryptJSON"></portlet:resourceURL>
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
             
               <c:set var="isLbsAddress" value="false" scope="request"/>
             <c:set var="islod" value="false" scope="request"/>
             <%--
             address.lbsAddressFlag=${hardwareDebriefForm.activity.serviceRequest.asset.installAddress.lbsAddressFlag}
             address.lbsAddressLod=${hardwareDebriefForm.activity.serviceRequest.asset.installAddress.lbsAddressLod}
			--%>
             <c:if test="${hardwareDebriefForm.activity.serviceRequest.asset.installAddress.lbsAddressFlag != null && hardwareDebriefForm.activity.serviceRequest.asset.installAddress.lbsAddressFlag != false}">
             <c:set var="isLbsAddress" value="true"/>
             </c:if>
             
              <c:if test="${hardwareDebriefForm.activity.serviceRequest.asset.installAddress.lbsAddressLod != null && hardwareDebriefForm.activity.serviceRequest.asset.installAddress.lbsAddressLod != ''}">
             <c:set var="islod" value="true"/>
             </c:if>
             
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
                  
                 
							
    	              		
							
							
							<c:if test="${(hardwareDebriefForm.activity.serviceRequest.asset.installAddress.lbsAddressLod == 'Mix-See Floor' && hardwareDebriefForm.activity.serviceRequest.asset.installAddress.floorLevelOfDetails == 'Grid Level') || hardwareDebriefForm.activity.serviceRequest.asset.installAddress.lbsAddressLod == 'Grid Level'}">
								<ul id="lod-floor-grid" style="${showGrid}" class="form division">
								 	<li><label for="coordinateX"><spring:message code="hardwareDebrief.closeOut.coordinateX"/></label>
								 	<span id="co-X"></span> 
								 	<span>${hardwareDebriefForm.userEnteredActivity.serviceRequest.asset.installAddress.coordinatesXPreDebriefRFV}</span>
								 	</li>
									<li><label for="coordinateY"><spring:message code="hardwareDebrief.closeOut.coordinateY"/></label>
									<span id="co-Y"></span>
									<span>${hardwareDebriefForm.userEnteredActivity.serviceRequest.asset.installAddress.coordinatesYPreDebriefRFV}</span>
									</li>
									<li> <a onclick="showMapPopup()" style="cursor: pointer; margin-left: -78.5%;"><spring:message code="hardwareDebrief.closeOut.viewGrid"/></a></li>
									<%--For LBS Adresses display names--%>
							 	</ul>	
							</c:if>
                  
                  
                  <%--<c:if test="${hardwareDebriefForm.userEnteredActivity.serviceRequest.asset.installAddress.coordinatesXPreDebriefRFV != null 
                  && hardwareDebriefForm.userEnteredActivity.serviceRequest.asset.installAddress.coordinatesXPreDebriefRFV != ''}">
                  <li><label for="coordinateX"><spring:message code='hardwareDebrief.closeOut.coordinateX'/></label> 
						 	<span>${hardwareDebriefForm.userEnteredActivity.serviceRequest.asset.installAddress.coordinatesXPreDebriefRFV}</span>
						 	</li>
                  </c:if>
                  <c:if test="${hardwareDebriefForm.userEnteredActivity.serviceRequest.asset.installAddress.coordinatesYPreDebriefRFV != null 
                  && hardwareDebriefForm.userEnteredActivity.serviceRequest.asset.installAddress.coordinatesYPreDebriefRFV != ''}">
                  <li><label for="coordinateY"><spring:message code='hardwareDebrief.closeOut.coordinateY'/></label>
							<span>${hardwareDebriefForm.userEnteredActivity.serviceRequest.asset.installAddress.coordinatesYPreDebriefRFV}</span>
							</li>
                  </c:if>--%>
				  
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
<script>
function successMap(obj){
	mapPopupObj.closePopup();
	
}


function showMapPopup(){
	mapPopupObj.showPopup({
		"id":'',
		"buildingId":"${hardwareDebriefForm.activity.serviceRequest.asset.installAddress.buildingId}",
		"floorId":"${hardwareDebriefForm.activity.serviceRequest.asset.installAddress.floorId}",
		"successFunc":successMap,
		"encryptionURL":"${encryptJSONVar}",
		"lbsPostURL":"${hardwareDebriefForm.mapForm.formPostUrl}",
		"locale":"<%=request.getLocale()%>",
		"mdmId":"${hardwareDebriefForm.userEnteredActivity.customerAccount.accountId}",
		"mdmLevel":"Siebel",
		"emailAddress":"${hardwareDebriefForm.mapForm.emailaddress}"
	});
	return false;
}
</script>