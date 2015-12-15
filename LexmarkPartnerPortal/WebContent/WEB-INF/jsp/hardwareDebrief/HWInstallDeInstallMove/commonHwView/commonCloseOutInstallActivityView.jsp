<%@ taglib prefix="html" tagdir="/WEB-INF/tags/html" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="util" uri="http://lexmark.com/tld/util" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<div class="portletBlock infoBox rounded shadow">
          <div class="columnsOne">
            <div class="columnInner">
           
              <h4>
              	 <spring:message code="requestInfo.hardwareDebreief.common.closeOut.closeOutInstallActivity.headerPrefix"/>
              	<c:if test='${isHwInstall }'>
					 <spring:message code="requestInfo.hardwareDebreief.common.closeOut.closeOutInstallActivity.install"/> 
				</c:if>
				<%--ENDS This is for Install debrief view  --%>
				
				<%--This is for De Install/ De Commission debrief view  --%>
				<c:if test='${isHwDeInstall }'>
					<spring:message code="requestInfo.hardwareDebreief.heading.deInstall"/>
				</c:if>
				<%--ENDS This is for De Install/ De Commission debrief view  --%>
				
				<%--This is for Move debrief view  --%>
				<c:if test='${isHwMove}'>
					 <spring:message code="requestInfo.hardwareDebreief.heading.move"/> 						
				</c:if>
               <spring:message code="requestInfo.hardwareDebreief.common.closeOut.closeOutInstallActivity.headerSufix"/>
              
              
              </h4>
				<div class="columnsTwo">
					<table width="100%" border="0" cellspacing="0" cellpadding="0" class="dataGrid">
				<%-- <tr>
                  <td width="200" class="label"><spring:message code="requestInfo.hardwareDebreief.common.closeOut.closeOutInstallActivity.expectedStartDate"/></td>
                  <td><util:dateFormat value="${hardwareDebriefForm.activity.debrief.expectedStartDate}" showTime="true" timezoneOffset="${tzOffset}"></util:dateFormat>
                  </td>
                </tr> --%>
                <tr>
                  <td width="200" class="label"><spring:message code="requestInfo.hardwareDebreief.common.closeOut.closeOutInstallActivity.projectedDate"/></td>
                  <td><util:dateFormat value="${hardwareDebriefForm.activity.estimatedArrivalTime}" showTime="true" timezoneOffset="${tzOffset}"></util:dateFormat>
                  </td>
                </tr>
                
                <tr>
                  <td class="label"><span class="req">*</span><spring:message code="requestInfo.hardwareDebreief.common.closeOut.closeOutInstallActivity.actualStartDate"/></td>
                  <td><util:dateFormat value="${hardwareDebriefForm.activity.debrief.serviceStartDate}" showTime="true" timezoneOffset="${tzOffset}"></util:dateFormat>
                  </td>
                </tr>
                <tr>
                  <td class="label"><span class="req">*</span><spring:message code="requestInfo.hardwareDebreief.common.closeOut.closeOutInstallActivity.actualEndDate"/></td>
                  <td><util:dateFormat value="${hardwareDebriefForm.activity.debrief.serviceEndDate}" showTime="true" timezoneOffset="${tzOffset}"></util:dateFormat>
                  </td>
                </tr>
                  <tr>
                  <td class="label"><span class="req">*</span><spring:message code="requestInfo.hardwareDebreief.common.closeOut.closeOutInstallActivity.technicianName"/></td>
                  <td>
<%--                   	${fn:trim(hardwareDebriefForm.activity.technician.firstName)}&nbsp;${fn:trim(hardwareDebriefForm.activity.technician.lastName)}                   --%>
						${fn:trim(hardwareDebriefForm.activity.technician.technicianName)}
                  </td>
                </tr>
                
              </table>
				</div>
				<div class="columnsTwo">
					<table width="100%" border="0" cellspacing="0" cellpadding="0" class="dataGrid">
						<tr>
							<td class="label"><spring:message code="requestInfo.hardwareDebreief.common.closeOut.closeOutInstallActivity.status"/></td>
							<td>${hardwareDebriefForm.activity.activitySubStatus.name}</td>
						</tr>   
						<tr>
							<td class="label"><spring:message code="requestInfo.hardwareDebreief.common.closeOut.closeOutInstallActivity.comments"/></td>	
							<td><span class="textScroll">
			  						${hardwareDebriefForm.activity.debrief.problemDescription}
			  				</span>
			  				</td>					
						</tr>
						      
					</table>
					
				</div>
				
				<p class="inlineTitle inlineTitle2"><spring:message code="requestInfo.hardwareDebreief.common.closeOut.closeOutInstallActivity.installedDeviceInfo.header"/></p>
				
				<div class="columnsOne">
				<div class="infoBox columnInner rounded shadow">
					<h4 class="gray"><spring:message code="requestInfo.hardwareDebreief.common.closeOut.closeOutInstallActivity.configDetails"/></h4>
					<div class="columnsTwo">
								<table width="100%" border="0" cellspacing="0" cellpadding="0" class="dataGrid">
									<c:if test='${isHwInstall }'>
									<tr>
										<td class="label"><span class="req">*</span><spring:message code="requestInfo.hardwareDebreief.common.closeOut.closeOutInstallActivity.installDate"/></td>
										<td>
										<util:dateFormat value="${hardwareDebriefForm.activity.serviceRequest.asset.installDate}" showTime="true" timezoneOffset="${tzOffset}"></util:dateFormat>
										</td>
									</tr>
									</c:if>
									<tr>
										<td class="label"><span class="req">*</span><spring:message code="requestInfo.hardwareDebreief.common.closeOut.closeOutInstallActivity.srNo"/></td>
										<td>
											${hardwareDebriefForm.activity.serviceRequest.asset.serialNumber}
										</td>
									</tr>
									<tr>
										<td class="label"><spring:message code="requestInfo.hardwareDebreief.common.closeOut.closeOutInstallActivity.networkConnected"/></td>
										<td><c:choose><c:when test='${hardwareDebriefForm.activity.serviceRequest.asset.networkConnectedFlag}==true'><spring:message code="selection.label.yes"/></c:when><c:otherwise><spring:message code="selection.label.No"/></c:otherwise></c:choose></td>
									</tr>
									<tr>
										<td class="label"><spring:message code="requestInfo.hardwareDebreief.common.closeOut.closeOutInstallActivity.ip/ipv"/></td>
										<td>
											${hardwareDebriefForm.activity.serviceRequest.asset.ipAddress}
										</td>
									</tr>
									<tr>
										<td class="label"><spring:message code="requestInfo.hardwareDebreief.common.closeOut.closeOutInstallActivity.ipv"/></td>
										<td>
											${hardwareDebriefForm.activity.serviceRequest.asset.ipV6}
										</td>
									</tr>
									<tr>
										<td class="label"><spring:message code="requestInfo.hardwareDebreief.common.closeOut.closeOutInstallActivity.subnetMask"/></td>
										<td>
											${hardwareDebriefForm.activity.serviceRequest.asset.subnet}
										</td>
									</tr>
									<tr>
										<td class="label"><spring:message code="requestInfo.hardwareDebreief.common.closeOut.closeOutInstallActivity.ipGateway"/></td>
										<td>
											${hardwareDebriefForm.activity.serviceRequest.asset.gateway}
										</td>
									</tr>
									<tr>
										<td class="label"><spring:message code="requestInfo.hardwareDebreief.common.closeOut.closeOutInstallActivity.macAddress"/></td>
										<td>
											${hardwareDebriefForm.activity.serviceRequest.asset.macAddress}
										</td>
									</tr>
									
								</table>
							</div>
							
							<div class="columnsTwo">
					<table width="100%" border="0" cellspacing="0" cellpadding="0" class="dataGrid">
						<tr>
							<td class="label"><spring:message code="requestInfo.hardwareDebreief.common.closeOut.closeOutInstallActivity.hostName"/></td>
							<td>
								${hardwareDebriefForm.activity.serviceRequest.asset.hostName}
							</td>
						</tr>
						<tr>
							<td class="label"><spring:message code="requestInfo.hardwareDebreief.common.closeOut.closeOutInstallActivity.portNo"/></td>
							<td>
								${hardwareDebriefForm.activity.serviceRequest.asset.portNumber}
							</td>
						</tr>
						<tr>
							<td class="label"><spring:message code="requestInfo.hardwareDebreief.common.closeOut.closeOutInstallActivity.faxConnected"/></td>
							<td>
								<c:forEach items="${hardwareDebriefForm.faxConnectedTypeMap}" var="faxconectedTypeMAp">
																<c:choose>
																	<c:when test="${hardwareDebriefForm.userEnteredActivity.serviceRequest.asset.faxConnectedValue eq faxconectedTypeMAp.key}">
																	${faxconectedTypeMAp.value}
																	</c:when>
																	<c:otherwise>																	
																	</c:otherwise>
																</c:choose>														
								</c:forEach>
							
							</td>
						</tr>
						<tr>
							<td class="label"><spring:message code="requestInfo.hardwareDebreief.common.closeOut.closeOutInstallActivity.faxPortNo"/></td>
							<td>
								${hardwareDebriefForm.activity.serviceRequest.asset.faxPortNumber}
							</td>
						</tr>
						<tr>
							<td class="label"><spring:message code="requestInfo.hardwareDebreief.common.closeOut.closeOutInstallActivity.printerCondition"/></td>
								<td>${hardwareDebriefForm.activity.debrief.deviceCondition.name}</td>
						</tr>
						<tr>
							<td class="label"><spring:message code="requestInfo.hardwareDebreief.common.closeOut.closeOutInstallActivity.wiringNwPt"/></td>
							<td>${hardwareDebriefForm.activity.serviceRequest.asset.wiringClosestNetworkPoint}</td>
						</tr>
						<%--Changes for 11599 MPS 2.1 --%> 
						<%--Asset field 1 is commented as per Joan 
						<tr>
							<td class="label"><spring:message code="requestInfo.hardwareDebrief.assetField" arguments="1"/>:</td>
							<td>${hardwareDebriefForm.activity.serviceRequest.asset.assetField1}</td>
						</tr>--%>
						<tr>
							<td class="label"><spring:message code="requestInfo.hardwareDebrief.assetField" arguments="2"/>:</td>
							<td>${hardwareDebriefForm.activity.serviceRequest.asset.assetField2}</td>
						</tr>
						<tr>
							<td class="label"><spring:message code="requestInfo.hardwareDebrief.assetField" arguments="3"/>:</td>
							<td>${hardwareDebriefForm.activity.serviceRequest.asset.assetField3}</td>
						</tr>
						<%--Ends Changes for 11599 MPS 2.1  --%>
					</table>
				</div>
					
				</div>
				</div>
				
				<div class="columnsTwo">
				<div class="infoBox columnInner rounded shadow">
					<h4 class="gray"><spring:message code="requestInfo.hardwareDebreief.common.closeOut.closeOutInstallActivity.accDetails"/></h4>
					<table width="100%" border="0" cellspacing="0" cellpadding="0" class="dataGrid">
									<tr>
										<td class="label"><spring:message code="requestInfo.hardwareDebreief.common.closeOut.closeOutInstallActivity.deviceDescription"/></td>
										<td>
											${hardwareDebriefForm.activity.serviceRequest.asset.description}
										</td>
									</tr>
									<tr>
										<td class="label"><spring:message code="requestInfo.hardwareDebreief.common.closeOut.closeOutInstallActivity.customerDeviceTag"/></td>
										<td>
											${hardwareDebriefForm.activity.serviceRequest.asset.deviceTag}
										</td>
									</tr>
									<tr>
										<td class="label"><spring:message code="requestInfo.hardwareDebreief.common.closeOut.closeOutInstallActivity.accountName"/></td>
										<td>${hardwareDebriefForm.activity.customerAccount.accountName}
										</td>
									</tr>
									<tr>
										<td class="label"><spring:message code="requestInfo.hardwareDebreief.common.closeOut.closeOutInstallActivity.departmentName"/></td>
										<td>
										${hardwareDebriefForm.activity.serviceRequest.asset.department}
										</td>
									</tr>
									<%-- Commented as per requested by business 
									<tr>
										<td class="label"><spring:message code="requestInfo.hardwareDebreief.common.closeOut.closeOutInstallActivity.assetHierarchy"/></td>
										<td>
											${hardwareDebriefForm.activity.serviceRequest.asset.assetHierarchyLevel}
										</td>
									</tr>--%>
									<tr>
										<td class="label"><spring:message code="requestInfo.hardwareDebreief.common.closeOut.closeOutInstallActivity.costCenter"/></td>
										<td>
											${hardwareDebriefForm.activity.serviceRequest.asset.assetCostCenter}
										</td>
									</tr>
									
								</table>
				</div>
				</div>
				
				<div class="columnsTwo">
				<div class="infoBox columnInner rounded shadow">
					<h4 class="gray"><spring:message code="requestInfo.hardwareDebreief.common.closeOut.closeOutInstallActivity.otherInfo"/></h4>
					<table width="100%" border="0" cellspacing="0" cellpadding="0" class="dataGrid">
									<tr>
										<td class="label"><spring:message code="requestInfo.hardwareDebreief.common.closeOut.closeOutInstallActivity.os"/></td>
										<td>
											${hardwareDebriefForm.activity.serviceRequest.asset.operatingSystem }
										</td>
									</tr>
									<tr>
										<td class="label"><spring:message code="requestInfo.hardwareDebreief.common.closeOut.closeOutInstallActivity.osVersion"/></td>
										<td>
											${hardwareDebriefForm.activity.serviceRequest.asset.operatingSystemVersion}
										</td>
									</tr>
									<tr>
										<td class="label"><spring:message code="requestInfo.hardwareDebreief.common.closeOut.closeOutInstallActivity.firmware"/></td>
										<td>
											${hardwareDebriefForm.activity.serviceRequest.asset.firmware }
										</td>
									</tr>
									<tr>
										<td class="label"><spring:message code="requestInfo.hardwareDebreief.common.closeOut.closeOutInstallActivity.nwTopology"/></td>
										<td>
											${hardwareDebriefForm.activity.serviceRequest.asset.networkTopology }
										</td>
									</tr>
									<%-- 
									Commented as per mail from saurabh on 18-11-2013
									<tr>
										<td class="label">Top Bill :<span class="req">*</span></td>
										<td>
											${hardwareDebriefForm.activity.serviceRequest.asset.topBill }
										</td>
									</tr>
									--%>
									<tr>
										<td class="label"><spring:message code="requestInfo.hardwareDebreief.common.closeOut.closeOutInstallActivity.splUsage"/></td>
										
										<td>
								<c:forEach items="${hardwareDebriefForm.specialUsageMap}" var="specialUsageMap">
																<c:choose>
																	<c:when test="${hardwareDebriefForm.userEnteredActivity.serviceRequest.asset.specialUsage eq specialUsageMap.key}">
																	${specialUsageMap.value}
																	</c:when>
																	<c:otherwise>																	
																	</c:otherwise>
																</c:choose>														
								</c:forEach>
							
							</td>
									</tr>
										<%-- 
									Commented as per mail from saurabh on 18-11-2013
									<tr>
										<td class="label">Asset Life Cycle :</td>
										<td>
											${hardwareDebriefForm.activity.serviceRequest.asset.assetLifeCycle }
										</td>
									</tr>
									--%>
									
								</table>
				</div>
				</div>
				
				
				
            </div>
          </div>
        </div>