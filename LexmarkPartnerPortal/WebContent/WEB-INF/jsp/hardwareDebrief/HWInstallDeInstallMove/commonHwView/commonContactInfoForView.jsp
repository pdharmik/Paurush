<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="util" uri="http://lexmark.com/tld/util" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="html" tagdir="/WEB-INF/tags/html" %>
<c:if test="${fn:length(hardwareDebriefForm.activity.serviceRequest.contactInfoForDevice)>0}">

<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/in-styles.css?version=<html:fileCacheVersion/>" />
<div class="infoBox columnInner rounded shadow">

			<h4><spring:message code="requestInfo.hardwareDebrief.commonContactInfo.heading"/></h4>
             			
			
        <div>
					<table class="displayGrid2 rounded shadow wFull">
						<thead>
							<tr>
								<th width="20%"><spring:message code="requestInfo.hardwareDebrief.commonContactInfo.contactType"/></th>
								<th width="39%"><spring:message code="requestInfo.hardwareDebreief.servInfo.primaryContact"/></th>								
								<th width="39%"><spring:message code="requestInfo.hardwareDebrief.commonContactInfo.address"/></th>
								
							</tr>
						</thead>
					</table>
					<div class="scrollTable">
					<table class="displayGrid2 rounded shadow wFull">
						<tbody>
						<c:forEach var="contactInformation" items="${hardwareDebriefForm.activity.serviceRequest.contactInfoForDevice}" varStatus="status">
							<c:choose>
									<c:when test="${status.index%2==0}">
													<tr>
												</c:when>
												<c:otherwise>
													<tr class="altRow">
												</c:otherwise>
							</c:choose>
								<td width="20%" class="strong middle">${contactInformation.deviceContactType}</td>
								<td width="39%" class="middle">
									<ul class="form">
						                <li>
						                  <label><spring:message code="requestInfo.hardwareDebrief.commonContactInfo.name"/></label>
						                  <span>${contactInformation.firstName}</span><span>${contactInformation.lastName}</span></li>
						                <li>
						                  <label><spring:message code="requestInfo.hardwareDebrief.commonContactInfo.ph"/></label>
						                  <span>${contactInformation.workPhone}</span></li>
						                <li>
						                  <label><spring:message code="requestInfo.hardwareDebreief.common.closeOut.serviceInfoForDevice.email"/></label>
						                  <span class="word-break-break-all">${contactInformation.emailAddress}</span></li>
						            </ul>
			  
								</td>
								<td width="39%" class="middle">
								
										<ul class="roDisplay">
								                <li><util:addressOutput value="${contactInformation.address}" displayInDivs="true"></util:addressOutput>
								                 </li>
             						    </ul>
							              <ul class="form division">
							                <li>
							                  <label for="building"><spring:message code="claimCreate.addressInfo.label.building" /></label>
							                  <span>
							                  ${contactInformation.address.physicalLocation1}
							                  </span></li>
							                <li>
							                  <label for="floor"><spring:message code="claimCreate.addressInfo.label.floor" /></label>
							                  <span>
							                  ${contactInformation.address.physicalLocation2}
							                  </span></li>
							                <li>
							                  <label for="office"><spring:message code="claimCreate.addressInfo.label.office" /></label>
							                  <span>
							                  ${contactInformation.address.physicalLocation3}
							                  </span></li>
							              </ul>
								</td>
								
							</tr>
							</c:forEach>
						</tbody>
					</table>
					</div>
            </div>


<%-- 
          <div class="columnsOne">
            <div class="columnInner">
              <h4>Supplies Information for this Device</h4>
				<div class="columnsTwo">
					<div class="infoBox columnInner rounded shadow">
					<h4>Primary Supplies Contact</h4>
						<table width="100%" border="0" cellspacing="0" cellpadding="0" class="dataGrid">
							<tr>
								<td width="100" class="label">Name:</td>
								<td>${fn:trim(serviceRequest.primarySuppliesContact.firstName)}&nbsp;${fn:trim(serviceRequest.primarySuppliesContact.lastName)}</td>
							</tr>
							<tr>
								<td width="150" class="label">Phone:</td>
								<td>${fn:trim(serviceRequest.primarySuppliesContact.workPhone)}</td>
							</tr>
							<tr>
								<td class="label">Email Address:</td>
								<td>${fn:trim(serviceRequest.primarySuppliesContact.emailAddress)}</td>
							</tr>               
						</table>
					</div>
					<div class="infoBox columnInner rounded shadow">
					  <h4>Primary Contact Address</h4>
					  <ul class="roDisplay">
						<li>
						 <util:addressOutput value="${serviceRequest.primarySuppliesContact.address}"></util:addressOutput>
						 </li>
					  </ul>
					  <ul class="form division">
						<li>
						  <label for="building">Building:</label>
						  <span>
						 ${serviceRequest.primarySuppliesContact.address.physicalLocation1}
						  </span>
						<li>
						  <label for="floor">Floor:</label>
						  <span>
						   ${serviceRequest.primarySuppliesContact.address.physicalLocation2}
						  </span>
						<li>
						  <label for="office">Office:</label>
						  <span>
						    ${serviceRequest.primarySuppliesContact.address.physicalLocation3}
						  </span></li>
					  </ul>
					</div>
				</div>
				<div class="columnsTwo">
					<div class="infoBox columnInner rounded shadow">
					<h4>Secondary Supplies Contact</h4>
						<table width="100%" border="0" cellspacing="0" cellpadding="0" class="dataGrid">
							<tr>
								<td width="100" class="label">Name:</td>
								<td>${fn:trim(serviceRequest.secondarySuppliesContact.firstName)}&nbsp;${fn:trim(serviceRequest.secondarySuppliesContact.lastName)}</td>
							</tr>
							<tr>
								<td width="150" class="label">Phone:</td>
								<td>${fn:trim(serviceRequest.secondarySuppliesContact.workPhone)}</td>
							</tr>
							<tr>
								<td class="label">Email Address:</td>
								<td>${fn:trim(serviceRequest.secondarySuppliesContact.emailAddress)}</td>
							</tr>               
						</table>
					</div>
					<div class="infoBox columnInner rounded shadow">
					  <h4>Secondary Contact Address</h4>
					  <ul class="roDisplay">
						<li>
						 <util:addressOutput value="${serviceRequest.secondarySuppliesContact.address}"></util:addressOutput>
						</li>
					  </ul>
					  <ul class="form division">
						<li>
						  <label for="building">Building:</label>
						  <span>
						  ${serviceRequest.secondarySuppliesContact.address.physicalLocation1}
						  </span>
						<li>
						  <label for="floor">Floor:</label>
						  <span>
						   ${serviceRequest.secondarySuppliesContact.address.physicalLocation1}
						  </span>
						<li>
						  <label for="office">Office:</label>
						  <span>
				          ${serviceRequest.secondarySuppliesContact.address.physicalLocation1}
						  </span></li>
					  </ul>
					</div>
				</div>

            </div>
          </div>
          --%>
          
          
        </div>
  </c:if>      
        
        
        
        
     