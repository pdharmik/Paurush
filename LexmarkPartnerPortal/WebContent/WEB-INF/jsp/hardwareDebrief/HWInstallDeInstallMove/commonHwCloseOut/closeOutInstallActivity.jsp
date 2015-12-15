<%@ taglib prefix="html" tagdir="/WEB-INF/tags/html" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="util" uri="http://lexmark.com/tld/util" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div class="portletBlock infoBox rounded shadow">
          <div class="columnsOne">
            <div class="columnInner">
              <h4>
               <spring:message code="requestInfo.hardwareDebreief.common.closeOut.closeOutInstallActivity.headerPrefix"/>
              <c:choose>
      			<c:when test='${isHwInstall}'>
      				<spring:message code="requestInfo.hardwareDebreief.heading.install"/>
      			</c:when>
      			<c:when test='${isHwDeInstall}'>
      				<spring:message code="requestInfo.hardwareDebreief.heading.deInstall"/>
      			</c:when>
      			<c:when test='${isHwMove}'>
      				<spring:message code="requestInfo.hardwareDebreief.heading.move"/>
      			</c:when>
     		 </c:choose> 
             <spring:message code="requestInfo.hardwareDebreief.common.closeOut.closeOutInstallActivityView.headerSufix"/>
              
              </h4>
				<div class="columnsTwo">
					<table width="100%" border="0" cellspacing="0" cellpadding="0" class="dataGrid">
					
                <tr>
                  <td width="200" class="label"><spring:message code="requestInfo.hardwareDebreief.common.closeOut.closeOutInstallActivity.projectedDate"/></td>
                  <td>
                  
                  <%--Commented as per defect 11357 
                  <c:choose>
                  		<c:when test="${hardwareDebriefForm.userEnteredActivity.estimatedArrivalTime eq null or exceptionOccured}">--%>
                  		<input disabled="disabled" type="text" id="serviceRequestDate" name="userEnteredActivity.estimatedArrivalTime" value="<util:dateFormat value="${hardwareDebriefForm.userEnteredActivity.estimatedArrivalTime}" showTime="true" timezoneOffset="${tzOffset}"></util:dateFormat>"/>
                  		<img class="ui-icon calendar-icon" src="<html:imagesPath/>transparent.png" alt="Remove" title="<spring:message code="requestInfo.hardwareDebreief.common.closeOut.closeOutInstallActivity.projectedDate"/>" id="projectedDate_cal" />
                  		<%--Commented as per defect 11357 </c:when>
                  		<c:otherwise>
                  			<util:dateFormat value="${hardwareDebriefForm.userEnteredActivity.estimatedArrivalTime}" showTime="true" timezoneOffset="${tzOffset}"></util:dateFormat>
                  			<input type="hidden" id="serviceRequestDate" name="userEnteredActivity.estimatedArrivalTime" value="<util:dateFormat value="${hardwareDebriefForm.userEnteredActivity.estimatedArrivalTime}" showTime="true" timezoneOffset="${tzOffset}"></util:dateFormat>"/>
                  		</c:otherwise>	
                  </c:choose>--%>
                  				
				  </td>
                </tr>
                
                <tr>
                  <td class="label"><span class="req">*</span>&nbsp;<spring:message code="requestInfo.hardwareDebreief.common.closeOut.closeOutInstallActivity.actualStartDate"/></td>
                  <td>
				  <input disabled="disabled" type="text" id="actualStartDate" name="userEnteredActivity.debrief.serviceStartDate" value="<util:dateFormat value="${hardwareDebriefForm.userEnteredActivity.debrief.serviceStartDate}" showTime="true" timezoneOffset="${tzOffset}"></util:dateFormat>"/>
				  <img class="ui-icon calendar-icon" src="<html:imagesPath/>transparent.png" alt="Remove" title="<spring:message code="requestInfo.hardwareDebreief.common.closeOut.closeOutInstallActivity.actualStartDate"/>" id="actualStartDate_cal" />					
				  </td>
                </tr>
                <tr>
                  <td class="label"><span class="req">*</span>&nbsp;<spring:message code="requestInfo.hardwareDebreief.common.closeOut.closeOutInstallActivity.actualEndDate"/></td>
                  <td>
				  <input disabled="disabled" type="text" id="actualEndDate" name="userEnteredActivity.debrief.serviceEndDate" value="<util:dateFormat value="${hardwareDebriefForm.userEnteredActivity.debrief.serviceEndDate}" showTime="true" timezoneOffset="${tzOffset}"></util:dateFormat>"/>
				  <img class="ui-icon calendar-icon" src="<html:imagesPath/>transparent.png" alt="Remove" title="<spring:message code="requestInfo.hardwareDebreief.common.closeOut.closeOutInstallActivity.actualEndDate"/>" id="actualEndDate_cal" />
				  </td>
                </tr>
                
                <tr>
							<td class="label"><span class="req">*</span>&nbsp;<spring:message code="requestInfo.hardwareDebreief.common.closeOut.closeOutInstallActivity.technicianName"/></td>
		                  <td>
		                  <form:input type="text" path="userEnteredActivity.technician.technicianName" id="technicianNameArea" ></form:input>
		                  <%--
		                  Commented as per mail from saurabh on 18-11-2013
		                  
		                  <select id="technicianChoose" name="technicianFullName">
											</select>
											<span id="technicianLoading" style="display: none;">
												&nbsp;&nbsp;&nbsp;<img  src = "<html:imagesPath/>loading-icon.gif"; />&nbsp;<spring:message code="label.searchingNotification" />
											</span>	
							 --%>				
											
											
											</td>						
						</tr> 
                
              </table>
				</div>
				<div class="columnsTwo">
					<table width="100%" border="0" cellspacing="0" cellpadding="0" class="dataGrid">
						 <tr>
                  <td class="label"><span class="req">*</span>&nbsp;<spring:message code="requestInfo.hardwareDebreief.common.closeOut.closeOutInstallActivity.status"/></td>
                  <td>
                 
                  <form:select id="serviceRequestStatusDropDown" path="userEnteredActivity.activitySubStatus.value">
                  	<form:option value=""></form:option>
                  	<c:forEach items="${hardwareDebriefForm.requestStatusMap}" var="entry">
                  		<c:choose>
                  			<c:when test="${hardwareDebriefForm.userEnteredActivity.activitySubStatus.value == entry.key}">
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
						<%--Changes for June release 10913 --%>
							<td class="label"><spring:message code="requestInfo.hardwareDebreief.common.closeOut.closeOutInstallActivity.comments"/></td>	
							<td><div class="w300"><div style="width:230px;float:left;"><form:textarea path="userEnteredActivity.debrief.problemDescription" id="desc" rows="5" style="width:200px;"></form:textarea></div>
							<div style="width:70px;float:left;"><img src="<html:imagesPath/>transparent.png" id="statusValues" class="helpIconList ui-icon info-icon" title="<spring:message code="requestInfo.hardwareDebrief.comments.requiredFor"/>"></div><div style="clear:both;"></div></div>
							
							</td>
						<%--Ends Changes for June release 10913 --%>						
						</tr> 
						        
					</table>
			  
				</div>
				

			<p class="inlineTitle inlineTitle2"><spring:message code="requestInfo.hardwareDebreief.common.closeOut.closeOutInstallActivity.installedDeviceInfo.header"/></p>
				<p class="info banner"><spring:message code="requestInfo.hardwareDebreief.common.closeOut.closeOutInstallActivity.installedDeviceMsg"/></p>
				<div class="columnsOne">
				
				<div class="infoBox columnInner rounded shadow">
					<h4 class="gray"><spring:message code="requestInfo.hardwareDebreief.common.closeOut.closeOutInstallActivity.configDetails"/></h4>
					<div class="columnsTwo">
								<table width="100%" border="0" cellspacing="0" cellpadding="0" class="dataGrid">
									<c:if test='${isHwInstall}'>
									<tr>
										<td class="label"><span class="req">*</span>&nbsp;<spring:message code="requestInfo.hardwareDebreief.common.closeOut.closeOutInstallActivity.installDate"/></td>
										<td>
										<input disabled="disabled" id="assetInstallDate" name="userEnteredActivity.serviceRequest.asset.installDate" type="text" value="<util:dateFormat value="${hardwareDebriefForm.userEnteredActivity.serviceRequest.asset.installDate}" showTime="true" timezoneOffset="${tzOffset}"></util:dateFormat>">
				 							<img class="ui-icon calendar-icon" src="<html:imagesPath/>transparent.png" alt="Remove" title="<spring:message code="requestInfo.hardwareDebreief.common.closeOut.closeOutInstallActivity.installDate"/>" id="configDetailsInstallDate_cal" onclick="show_cal('assetInstallDate',null,null,true);jQuery('#assetInstallDate').removeClass('errorColor');" />
										</td>
									</tr>
									</c:if>
									<tr>
										<td class="label"><span class="req">*</span>&nbsp;<spring:message code="requestInfo.hardwareDebreief.common.closeOut.closeOutInstallActivity.srNo"/></td>
										<td>
											<form:input id="assetSerialNumber" type="text" path="userEnteredActivity.serviceRequest.asset.serialNumber"/> 
										</td>
									</tr>
									<tr>
										<td class="label"><spring:message code="requestInfo.hardwareDebreief.common.closeOut.closeOutInstallActivity.networkConnected"/></td>
										<td><form:select path="userEnteredActivity.serviceRequest.asset.networkConnectedFlag"><option value="true"><spring:message code="selection.label.yes"/></option><option value="false"><spring:message code="selection.label.No"/></option></form:select></td>
									</tr>
									<tr>
										<td class="label"><spring:message code="requestInfo.hardwareDebreief.common.closeOut.closeOutInstallActivity.ip/ipv"/></td>
										<td>
											<form:input path="userEnteredActivity.serviceRequest.asset.ipAddress"></form:input>
										</td>
									</tr>
									<tr>
										<td class="label"><spring:message code="requestInfo.hardwareDebreief.common.closeOut.closeOutInstallActivity.ipv"/></td>
										<td>
											<form:input path="userEnteredActivity.serviceRequest.asset.ipV6"></form:input>
										</td>
									</tr>
									
									<tr>
										<td class="label"><spring:message code="requestInfo.hardwareDebreief.common.closeOut.closeOutInstallActivity.subnetMask"/></td>
										<td>
											<form:input path="userEnteredActivity.serviceRequest.asset.subnet"></form:input>
										</td>
									</tr>
									<tr>
										<td class="label"><spring:message code="requestInfo.hardwareDebreief.common.closeOut.closeOutInstallActivity.ipGateway"/></td>
										<td>
											<form:input path="userEnteredActivity.serviceRequest.asset.gateway"></form:input>
										</td>
									</tr>
									<tr>
										<td class="label"><spring:message code="requestInfo.hardwareDebreief.common.closeOut.closeOutInstallActivity.macAddress"/></td>
										<td>
											<form:input path="userEnteredActivity.serviceRequest.asset.macAddress"></form:input>
										</td>
									</tr>
									
								</table>
							</div>
				
				<div class="columnsTwo">
					<table width="100%" border="0" cellspacing="0" cellpadding="0" class="dataGrid">
						<tr>
							<td class="label"><spring:message code="requestInfo.hardwareDebreief.common.closeOut.closeOutInstallActivity.hostName"/></td>
							<td>
								<form:input path="userEnteredActivity.serviceRequest.asset.hostName"></form:input>
							</td>
						</tr>
						<tr>
							<td class="label"><spring:message code="requestInfo.hardwareDebreief.common.closeOut.closeOutInstallActivity.portNo"/></td>
							<td>
								<input type="text" name="userEnteredActivity.serviceRequest.asset.portNumber" value="${fn:replace(hardwareDebriefForm.userEnteredActivity.serviceRequest.asset.portNumber,',', '')}"/>
								
							</td>
						</tr>
						<tr>
							<td class="label"><spring:message code="requestInfo.hardwareDebreief.common.closeOut.closeOutInstallActivity.faxConnected"/></td>
							<td><form:select path="userEnteredActivity.serviceRequest.asset.faxConnectedValue">
							<form:option value=""></form:option>
							<c:forEach items="${hardwareDebriefForm.faxConnectedTypeMap}" var="faxconectedTypeMAp">
																<c:choose>
																	<c:when test="${hardwareDebriefForm.userEnteredActivity.serviceRequest.asset.faxConnectedValue eq faxconectedTypeMAp.key }">
																	<form:option value="${faxconectedTypeMAp.key}"  selected="selected">${faxconectedTypeMAp.value}</form:option>
																	</c:when>
																	<c:otherwise>
																	<form:option value="${faxconectedTypeMAp.key}">${faxconectedTypeMAp.value}</form:option>
																	</c:otherwise>
																</c:choose>														
															</c:forEach>
							
							</form:select>
							</td>
						</tr>
						<tr>
							<td class="label"><spring:message code="requestInfo.hardwareDebreief.common.closeOut.closeOutInstallActivity.faxPortNo"/></td>
							<td>
								<input type="text" name="userEnteredActivity.serviceRequest.asset.faxPortNumber" value="${fn:replace(hardwareDebriefForm.userEnteredActivity.serviceRequest.asset.faxPortNumber,',', '')}"/>
							</td>
						</tr>
						<tr>
							<td class="label"><spring:message code="requestInfo.hardwareDebreief.common.closeOut.closeOutInstallActivity.printerCondition"/></td>
								<td>
								<%-- THis value needed when exception occurs to store the value again other wise 
								there will be an issue in populateclouseinformation funciton --%>
								<form:hidden path="activity.debrief.deviceCondition.value"/>
								<form:select path="userEnteredActivity.serviceRequest.asset.printerWorkingCondition">
									<form:option value=""></form:option>
                  					<c:forEach items="${hardwareDebriefForm.deviceWorkingConditionMap}" var="entry">
					                  		<c:choose>
					                  			<c:when test="${hardwareDebriefForm.userEnteredActivity.debrief.deviceCondition.value == entry.key}">
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
							<td class="label"><spring:message code="requestInfo.hardwareDebreief.common.closeOut.closeOutInstallActivity.wiringNwPt"/></td>
							<td><form:input path="userEnteredActivity.serviceRequest.asset.wiringClosestNetworkPoint"></form:input></td>
						</tr>
							<%--Changes for 11599 MPS 2.1  --%>
							<%--Asset field 1 is commented as per Joan 
						<tr>
							<td class="label"><spring:message code="requestInfo.hardwareDebrief.assetField" arguments="1"/>:</td>
							<td><form:input path="userEnteredActivity.serviceRequest.asset.assetField1"></form:input></td>
						</tr>--%>
						<tr>
							<td class="label"><spring:message code="requestInfo.hardwareDebrief.assetField" arguments="2"/>:</td>
							<td><form:input path="userEnteredActivity.serviceRequest.asset.assetField2"></form:input></td>
						</tr>
						<tr>
							<td class="label"><spring:message code="requestInfo.hardwareDebrief.assetField" arguments="3"/>:</td>
							<td><form:input path="userEnteredActivity.serviceRequest.asset.assetField3"></form:input></td>
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
											<form:input path="userEnteredActivity.serviceRequest.asset.description"></form:input>
										</td>
									</tr>
									<tr>
										<td class="label"><spring:message code="requestInfo.hardwareDebreief.common.closeOut.closeOutInstallActivity.customerDeviceTag"/></td>
										<td>
											<form:input path="userEnteredActivity.serviceRequest.asset.deviceTag"></form:input>
										</td>
									</tr>
									<tr>
										<td class="label"><spring:message code="requestInfo.hardwareDebreief.common.closeOut.closeOutInstallActivity.accountName"/></td>
										<td><form:input path="userEnteredActivity.customerAccount.accountName"></form:input>
										</td>
									</tr>
									<tr>
										<td class="label"><spring:message code="requestInfo.hardwareDebreief.common.closeOut.closeOutInstallActivity.departmentName"/></td>
										<td>
										<form:input path="userEnteredActivity.serviceRequest.asset.department"></form:input>
										</td>
									</tr>
									<%-- Commented as per request from Business CHL won't be there 
									<tr>
										<td class="label"><spring:message code="requestInfo.hardwareDebreief.common.closeOut.closeOutInstallActivity.assetHierarchy"/></td>
										<td>
											<span id="chlNodeValueHTML">${activity.serviceRequest.asset.assetHierarchyLevel}</span>
											<%-- Commented for DEFECT 10893
											<c:choose>
												<c:when test="${not empty activity.serviceRequest.asset.chlNodeValue}">
													<span><img id="removeCHLImage" src="<html:imagesPath/>transparent.png" alt="Remove" title="Remove" class="edit ui-icon ui-icon-closethick" style="cursor: pointer;" onclick="removeChl();"></span>
												</c:when>
												<c:otherwise>
												<span><img id="removeCHLImage" src="<html:imagesPath/>transparent.png" alt="Remove" title="Remove" class="edit ui-icon ui-icon-closethick" style="cursor: pointer; display:none;"onclick="removeChl();"></span>
												</c:otherwise>
											</c:choose>
											--%>
											<%-- 	<input type="hidden" name="userEnteredActivity.serviceRequest.asset.chlNodeId" id="chlNodeId" value="${userEnteredActivity.serviceRequest.asset.chlNodeId}"/>
												<input type="hidden" name="userEnteredActivity.serviceRequest.asset.chlNodeValue" value="${userEnteredActivity.serviceRequest.asset.assetHierarchyLevel}"/>
												<%-- Commented for DEFECT 10893
												<span><a id="selectChlLink" style="cursor: pointer;" onclick="popUpChlTree();" title="<spring:message code="requestInfo.hardwareDebreief.common.closeOut.closeOutInstallActivity.selectHierarchy"/>"><spring:message code="requestInfo.hardwareDebreief.common.closeOut.closeOutInstallActivity.selectHierarchy"/></a></span>
												
										</td>
									</tr>--%>
									<tr>
										<td class="label"><spring:message code="requestInfo.hardwareDebreief.common.closeOut.closeOutInstallActivity.costCenter"/></td>
										<td>
											<form:input path="userEnteredActivity.serviceRequest.asset.assetCostCenter"></form:input>
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
											<form:input path="userEnteredActivity.serviceRequest.asset.operatingSystem"></form:input>
										</td>
									</tr>
									<tr>
										<td class="label"><spring:message code="requestInfo.hardwareDebreief.common.closeOut.closeOutInstallActivity.osVersion"/></td>
										<td>
											<form:input path="userEnteredActivity.serviceRequest.asset.operatingSystemVersion"></form:input>
										</td>
									</tr>
									<tr>
										<td class="label"><spring:message code="requestInfo.hardwareDebreief.common.closeOut.closeOutInstallActivity.firmware"/></td>
										<td>
											<form:input path="userEnteredActivity.serviceRequest.asset.firmware"></form:input>
										</td>
									</tr>
									<tr>
										<td class="label"><spring:message code="requestInfo.hardwareDebreief.common.closeOut.closeOutInstallActivity.nwTopology"/></td>
										<td>
											<form:input path="userEnteredActivity.serviceRequest.asset.networkTopology"></form:input>
										</td>
									</tr>
									<%-- 
									Commented as per mail from saurabh on 18-11-2013
									<tr>
										<td class="label">Top Bill :<span class="req">*</span></td>
										<td>
											<form:input path="activity.serviceRequest.asset.topBill"></form:input>
										</td>
									</tr>
									--%>
									<tr>
										<td class="label"><spring:message code="requestInfo.hardwareDebreief.common.closeOut.closeOutInstallActivity.splUsage"/></td>
										<%-- 
								changed to a dropdown for CR#12022
									--%>
										<td><form:select path="userEnteredActivity.serviceRequest.asset.specialUsage">
							<form:option value=""></form:option>
							<c:forEach items="${hardwareDebriefForm.specialUsageMap}" var="specialUsageMap">
																<c:choose>
																	<c:when test="${hardwareDebriefForm.userEnteredActivity.serviceRequest.asset.specialUsage eq specialUsageMap.key}">
																	<form:option value="${specialUsageMap.key}"  selected="selected">${specialUsageMap.value}</form:option>
																	</c:when>
																	<c:otherwise>
																	<form:option value="${specialUsageMap.key}">${specialUsageMap.value}</form:option>
																	</c:otherwise>
																</c:choose>														
															</c:forEach>
							
							</form:select>
							</td>
									</tr>
									<%-- 
									Commented as per mail from saurabh on 18-11-2013
									<tr>
										<td class="label">Asset Life Cycle :</td>
										<td>
											<form:input path="userEnteredActivity.serviceRequest.asset.assetLifeCycle"></form:input>
										</td>
									</tr>
									--%>
									
								</table>
				</div>
				</div>
				
            </div>
          </div>
        </div>
        
        <%-- <div class="leftOverlay rounded shadow" style="left:600px;">
          <div class="columnInner">
            <h4><a href="javascript:void(0);" class="chlClose"><strong>Close</strong></a>CHL / Location</h4>
            The CHL / Location Tree structure goes here
            <div class="buttonContainerIn">
              <button class="button_cancel chlClose">Cancel</button>
              <button class="button chlClose">Select</button>
            </div>
          </div>
        </div>--%>
<script>

jQuery('#projectedDate_cal').click(function(){
	
	var actStartDate=null;
	var actEndDate=null;
	var prjctedDate=null;
	if(jQuery('#actualStartDate').val()!=''){
		actStartDate=formatDateToDefault(jQuery('#actualStartDate').val());
	}
	if(jQuery('#actualEndDate').val()!=''){
		actEndDate=formatDateToDefault(jQuery('#actualEndDate').val());
	}
	jQuery('#serviceRequestDate').removeClass('errorColor');
	//jQuery('#actualStartDate,#actualEndDate').val('');formatDateToDefault(todayStr)
	show_cal('serviceRequestDate', null, null,true);
	
	
});
jQuery('#actualStartDate_cal').click(function(){
	var endDate=null;
	var prjctDate=null;
	var finalEndDate=null
	if(jQuery('#actualEndDate').val()!=''){
		endDate=formatDateToDefault(jQuery('#actualEndDate').val());
	}
	if(jQuery('#serviceRequestDate').val()!=''){
		prjctDate=formatDateToDefault(jQuery('#serviceRequestDate').val());
	}
	
	jQuery('#actualStartDate').removeClass('errorColor');
	jQuery('#actualEndDate').val('');//endDate==null?prjctDate:endDate
	show_cal('actualStartDate', null, endDate,true);
});

jQuery('#actualEndDate_cal').click(function(){
	var stDate=null;
	var prjctDate=null;
	if(jQuery('#actualStartDate').val()!=''){
		stDate=formatDateToDefault(jQuery('#actualStartDate').val());
	}
	if(jQuery('#serviceRequestDate').val()!=''){
		prjctDate=formatDateToDefault(jQuery('#serviceRequestDate').val());
	}
	jQuery('#actualEndDate').removeClass('errorColor');//prjctDate
	show_cal('actualEndDate',stDate ,null,true);
});


var cId,cValue;
 function viewDeviceListByCHL(chlNodeId, chlValue) {
	
	//alert("Node Id "+chlNodeId + "Node Value " + chlValue);
	//set the data to variables for future use when user clicks on select
	
	cId=chlNodeId;
	cValue=chlValue;
	setValToPage();
	
};

function setValToPage(){
	//Please keep a span with id chlNodeValueLabel for display on screen
	
	jQuery("[name='userEnteredActivity.serviceRequest.asset.chlNodeId']").val(cId);
	jQuery("[name='userEnteredActivity.serviceRequest.asset.chlNodeValue']").val(cValue);
	jQuery('#chlNodeValueHTML').html(cValue);
	jQuery('#selectChlLink').hide();
	jQuery('#removeCHLImage').show();
	closeDialog();
}
function removeChl(){
	jQuery('#selectChlLink').show();
	jQuery('#removeCHLImage').hide();
	jQuery("[name='userEnteredActivity.serviceRequest.asset.chlNodeId']").val('');
	jQuery("[name='userEnteredActivity.serviceRequest.asset.chlNodeValue']").val('');
	jQuery('#chlNodeValueHTML').html('');
}
</script>