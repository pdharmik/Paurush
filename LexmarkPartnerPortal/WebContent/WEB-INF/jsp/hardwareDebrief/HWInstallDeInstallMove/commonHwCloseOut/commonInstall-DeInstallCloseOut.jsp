<%@ taglib prefix="html" tagdir="/WEB-INF/tags/html" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="util" uri="http://lexmark.com/tld/util" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div class="portletBlock infoBox rounded shadow">
 <div class="columnsOne">
            <div class="columnInner">
            
           <div class="infoBox columnInner rounded shadow">
					<h4 class="gray"><spring:message code="requestInfo.hardwareDebreief.common.closeOut.installDeinstallActivity"/></h4>
					<div class="columnsTwo">
								<table width="100%" border="0" cellspacing="0" cellpadding="0" class="dataGrid">
								<tr>
										<td class="label"><spring:message code="requestInfo.hardwareDebreief.common.closeOut.closeOutInstallActivity.srNo"/></td>
										<td>
											<form:input id="assetSerialNumber" type="text" path="userEnteredActivity.serviceRequest.asset.deinstSerialNumber"/> 
										</td>
									</tr>
									
									<tr>
										<td class="label"><spring:message code="requestInfo.serviceOrder.listHeader.pendingShipment.partNumber"/></td>
										<td>
											<form:input id="assetPartNumber" type="text" path="userEnteredActivity.serviceRequest.asset.deinstPartNumber"/> 
										</td>
									</tr>
									
									<tr>
										<td class="label"><spring:message code="requestInfo.hardwareDebreief.common.closeOut.productModel"/></td>
										<td>
											<form:input id="assetProductModel" type="text" path="userEnteredActivity.serviceRequest.asset.deinstModel"/> 
										</td>
									</tr>
									
									<tr>
										<td class="label"><spring:message code="hardware.massUploadTemplate.Brand.deinstall"/></td>
										<td>
											<form:input id="assetBrand" type="text" path="userEnteredActivity.serviceRequest.asset.deinstBrand"/> 
										</td>
									</tr>
									
									
									<tr>
										<td class="label"><spring:message code="hardware.massUploadTemplate.RemovalDate"/></td>
										
										<td>
										<input disabled="disabled" id="assetRemovalDate" name="userEnteredActivity.serviceRequest.asset.deinstRemovalDate" type="text" value="<util:dateFormat value="${hardwareDebriefForm.userEnteredActivity.serviceRequest.asset.deinstRemovalDate}" showTime="true" timezoneOffset="${tzOffset}"></util:dateFormat>">
				 							<img class="ui-icon calendar-icon" src="<html:imagesPath/>transparent.png" alt="Remove" title="<spring:message code="hardware.massUploadTemplate.RemovalDate"/>" id="configDetailsAssetRemovalDate_cal" onclick="showcal('assetRemovalDate',null,null,true);jQuery('#assetRemovalDate').removeClass('errorColor');" />
										</td>
									</tr>
									
									
									
									
									
								</table>
							</div>
				
				<div class="columnsTwo">
					<table width="100%" border="0" cellspacing="0" cellpadding="0" class="dataGrid">
					<tr>
										<td class="label"><spring:message code="requestInfo.hardwareDebreief.common.closeOut.closeOutInstallActivity.ip/ipv"/></td>
										<td>
											<form:input path="userEnteredActivity.serviceRequest.asset.deinstIpAddress"></form:input>
										</td>
									</tr>
									
						<tr>
							<td class="label"><spring:message code="requestInfo.hardwareDebreief.common.closeOut.closeOutInstallActivity.hostName"/></td>
							<td>
								<form:input path="userEnteredActivity.serviceRequest.asset.deinstHostName"></form:input>
							</td>
						</tr>
						
						
						
						<tr>
							<td class="label"><spring:message code="requestInfo.hardwareDebreief.common.closeOut.closeOutInstallActivity.printerCondition"/></td>
								<td>
								<%-- THis value needed when exception occurs to store the value again other wise 
								there will be an issue in populateclouseinformation funciton --%>
								<form:hidden path="activity.debrief.deviceCondition.value"/>
								<form:select path="userEnteredActivity.serviceRequest.asset.deviceCondition">
									<form:option value=""></form:option>
                  					<c:forEach items="${hardwareDebriefForm.deviceWorkingConditionMap}" var="entry">
					                  		<c:choose>
					                  			<c:when test="${hardwareDebriefForm.userEnteredActivity.serviceRequest.asset.deviceCondition == entry.key}">
					                  				<option value="${entry.key}" selected="selected">${entry.value}</option>
					                  			</c:when>
					                  			<c:otherwise>
					                  				<option value="${entry.key}" >${entry.value}</option>
					                  			</c:otherwise>
					                  		</c:choose>
                  		
	                    
									</c:forEach>
								</form:select>
								
								</td>
						</tr>
						<tr>
										<td class="label"><spring:message code="requestInfo.hardwareDebreief.common.closeOut.closeOutInstallActivity.customerDeviceTag"/></td>
										<td>
											<form:input path="userEnteredActivity.serviceRequest.asset.deinstAssetTag"></form:input>
										</td>
									</tr>
									
									<tr>
					
							<td class="label"><spring:message code="requestInfo.hardwareDebreief.common.closeOut.closeOutInstallActivity.comments"/></td>	
							<td><div class="w300"><div style="width:230px;float:left;"><form:textarea path="userEnteredActivity.serviceRequest.asset.deinstComments" id="desc" rows="5" style="width:200px;"></form:textarea></div>
							<div style="width:70px;float:left;"><img src="<html:imagesPath/>transparent.png" id="statusValues" class="helpIconList ui-icon info-icon" title="<spring:message code="requestInfo.hardwareDebrief.comments.requiredFor"/>"></div><div style="clear:both;"></div></div>
							
							</td>
											
						</tr> 
						
					</table>
				</div>
				</div>
            
            
            
            
            
            
            </div></div>



</div>
<script>





</script>