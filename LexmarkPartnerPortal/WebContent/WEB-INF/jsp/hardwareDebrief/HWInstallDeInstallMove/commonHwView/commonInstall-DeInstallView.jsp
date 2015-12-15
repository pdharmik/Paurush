<%@ taglib prefix="html" tagdir="/WEB-INF/tags/html" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="util" uri="http://lexmark.com/tld/util" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<div class="portletBlock infoBox rounded shadow">
        
				<div class="columnsOne">
				<div class="infoBox columnInner rounded shadow">
					<h4 class="gray"><spring:message code="requestInfo.hardwareDebreief.common.closeOut.installDeinstallActivity"/></h4>
					<div class="columnsTwo">
								<table width="100%" border="0" cellspacing="0" cellpadding="0" class="dataGrid">
								<tr>
										<td class="label"><spring:message code="requestInfo.hardwareDebreief.common.closeOut.closeOutInstallActivity.srNo"/></td>
										<td>
											${hardwareDebriefForm.activity.serviceRequest.asset.deinstSerialNumber}
										</td>
									</tr>
									<tr>
										<td class="label"><spring:message code="requestInfo.hardwareDebreief.common.closeOut.productModel"/></td>
										<td>
											${hardwareDebriefForm.activity.serviceRequest.asset.deinstModel}
										</td>
									</tr>
									<tr>
										<td class="label"><spring:message code="requestInfo.label.serviceOrder.partNumber"/></td>
										<td>
											${hardwareDebriefForm.activity.serviceRequest.asset.deinstPartNumber}
										</td>
									</tr>
									<tr>
										<td class="label"><spring:message code="hardware.massUploadTemplate.Brand.deinstall"/></td>
										<td>
											${hardwareDebriefForm.activity.serviceRequest.asset.deinstBrand}
										</td>
									</tr>
									<tr>
										<td class="label"><spring:message code="hardware.massUploadTemplate.RemovalDate"/></td>
										<td>
										<util:dateFormat value="${hardwareDebriefForm.activity.serviceRequest.asset.deinstRemovalDate}" showTime="true" timezoneOffset="${tzOffset}"></util:dateFormat>
										</td>
									</tr>
									</table></div>
									<div class="columnsTwo">
					<table width="100%" border="0" cellspacing="0" cellpadding="0" class="dataGrid">
					<tr>
										<td class="label"><spring:message code="requestInfo.hardwareDebreief.common.closeOut.closeOutInstallActivity.ip/ipv"/></td>
										<td>
											${hardwareDebriefForm.activity.serviceRequest.asset.deinstIpAddress}
										</td>
									</tr>
					<tr>
							<td class="label"><spring:message code="requestInfo.hardwareDebreief.common.closeOut.closeOutInstallActivity.hostName"/></td>
							<td>
								${hardwareDebriefForm.activity.serviceRequest.asset.deinstHostName}
							</td>
						</tr>
						<tr>
										<td class="label"><spring:message code="requestInfo.hardwareDebreief.common.closeOut.closeOutInstallActivity.customerDeviceTag"/></td>
										<td>
											${hardwareDebriefForm.activity.serviceRequest.asset.deinstAssetTag}
										</td>
									</tr>
									<tr>
							<td class="label"><spring:message code="requestInfo.hardwareDebreief.common.closeOut.closeOutInstallActivity.printerCondition"/></td>
								<td>${hardwareDebriefForm.activity.serviceRequest.asset.deviceCondition}</td>
						</tr>
						<tr>
							<td class="label"><spring:message code="requestInfo.hardwareDebreief.common.closeOut.closeOutInstallActivity.comments"/></td>	
							<td><span class="textScroll">
			  						${hardwareDebriefForm.activity.serviceRequest.asset.deinstComments}
			  				</span>
			  				</td>					
						</tr>
					</table>
					</div>
									</div></div></div>