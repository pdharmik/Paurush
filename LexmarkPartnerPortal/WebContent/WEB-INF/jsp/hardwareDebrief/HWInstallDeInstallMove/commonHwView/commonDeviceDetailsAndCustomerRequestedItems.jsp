<%@ taglib prefix="html" tagdir="/WEB-INF/tags/html" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%-- THIS JSP IS BEING USED IN VIEW AND MOVE  CLOSEOUT--%>

<div class="portletBlock infoBox rounded shadow">
          <div class="columnsOne">
            <div class="columnInner">
              <h4>
              <c:choose>
              <%-- hwInstall variable is set from closeout page
              and isHwInstall is set from view page --%>
	              	<c:when test='${hwInstall or isHwInstall}'>
	              	<spring:message code="requestInfo.hardwareDebreief.debriefInstall.customerReq"/>
	              	</c:when>
	              	<c:otherwise>
	              	<spring:message code="requestInfo.hardwareDebreief.deviceDetails.header"/>
	              	</c:otherwise>
               </c:choose>
              </h4>
              
              <table class="noBorder wFull">
			  <tr>
			  <td class="wQuarter">
				<table>
				<tr>
				<td>
				<c:choose>
							<c:when test="${not empty hardwareDebriefForm.activity.serviceRequest.asset.productImageURL}">
								<img height="100" width="100" src="${hardwareDebriefForm.activity.serviceRequest.asset.productImageURL}"/>
							</c:when>
							<c:otherwise>
								<img src="<html:imagesPath/>dummy_printer.jpg"/>
							</c:otherwise>
						</c:choose>
				
				</td>
				</tr>
				<tr>
				<td class="strong listStyle">
				<%-- changes for defect 14223 MPS 2.1 --%>
				${hardwareDebriefForm.activity.serviceRequest.asset.deviceName}				
				<input type="hidden" name="activity.serviceRequest.asset.machineTypeModel" value="${hardwareDebriefForm.activity.serviceRequest.asset.deviceName}">
				</td>
				
				</tr>
				</table>
			  </td>
			  
			  <c:choose>
			  <%-- hwInstall variable is set from closeout page
              and isHwInstall is set from view page --%>
			  <c:when test='${hwInstall or isHwInstall}'>
				  <td class="wQuarter">
					<p>${hardwareDebriefForm.activity.serviceRequest.asset.description }</p>
					<form:hidden path="activity.serviceRequest.asset.description"/>
<%-- 					<p class="strong"><spring:message code="requestInfo.hardwareDebreief.deviceDetails.inst&War"/></p> --%>
				  </td>
				   <td>
				<div>
					<table class="displayGrid2 rounded shadow wFull">
						<thead>
							<tr>
								<th class="w100"><spring:message code="requestInfo.hardwareDebreief.debriefInstallCloseOut.partNo"/></th>
								<th><spring:message code="requestInfo.hardwareDebreief.debriefInstallCloseOut.description"/></th>
								<th class="w100"><spring:message code="requestInfo.hardwareDebreief.debriefInstallCloseOut.quantity"/></th>
							</tr>
						</thead>
						<tbody>
						<c:choose>
							<c:when test="${not empty hardwareDebriefForm.activity.serviceRequest.asset.partList }">
						<c:forEach var="bundlePart" items="${hardwareDebriefForm.activity.serviceRequest.asset.partList}" varStatus="statusPart">
										<c:choose>
												<c:when test="${statusPart.count%2==0}">
													<tr class="altRow">
												</c:when>
												<c:otherwise>
													<tr>
												</c:otherwise>
											</c:choose>
											<td class="w100">${bundlePart.partNumber}</td>
											<td>${bundlePart.description}</td>
											<td class="w100">${bundlePart.orderQuantity}</td>
										</tr>
									
									<input type="hidden" name="activity.serviceRequest.asset.partList[${statusPart.index}].partNumber" value="${bundlePart.partNumber}"/>
									<input type="hidden" name="activity.serviceRequest.asset.partList[${statusPart.index}].description" value="${bundlePart.description}"/>
									<input type="hidden" name="activity.serviceRequest.asset.partList[${statusPart.index}].orderQuantity" value="${bundlePart.orderQuantity}"/>
									<input type="hidden" name="activity.serviceRequest.asset.partList[${statusPart.index}].orderNumber" value="${bundlePart.orderNumber}"/>
						</c:forEach>
						</c:when>
						<c:otherwise>
							<tr><td colspan="3"><spring:message code="requestInfo.hardwareDebrief.commonContactInfo.noRec"/></td></tr>
						</c:otherwise>
						</c:choose>
						</tbody>
					</table>
				</div>
			  </td>
              </c:when>
            <c:otherwise>
	              <td class="wHalf">
						<table width="100%" border="0" cellspacing="0" cellpadding="0" class="dataGrid">
			                <tr>
			                  <td width="220" class="label"><spring:message code="requestInfo.hardwareDebreief.common.closeOut.closeOutInstallActivity.srNo"/></td>
			                  <td>${hardwareDebriefForm.activity.serviceRequest.asset.serialNumber }</td>
			                  <form:hidden path="activity.serviceRequest.asset.serialNumber"/>
			                </tr>
			                <tr>
			                  <td width="150" class="label"><spring:message code="claim.label.machineTypeModel"/></td>
			                  <td>
			                  
								${hardwareDebriefForm.activity.serviceRequest.asset.productModelNumber}
								<input type="hidden" name="activity.serviceRequest.asset.productModelNumber" value="${hardwareDebriefForm.activity.serviceRequest.asset.productModelNumber}">
			                  </td>
			                  
			                </tr>
			                <tr>
			                  <td class="label"><spring:message code="requestInfo.hardwareDebreief.common.closeOut.closeOutInstallActivity.prodPN/TLI"/></td>
			                  <td>${hardwareDebriefForm.activity.serviceRequest.asset.productTLI }</td>
			                  <form:hidden path="activity.serviceRequest.asset.productTLI"/>
			                </tr>
		                
		              </table>
				  </td>
				  <td>
				  </td>
			  </c:otherwise>
             </c:choose> 
             
            </tr>
			</table>
            </div>
          </div>
        </div>