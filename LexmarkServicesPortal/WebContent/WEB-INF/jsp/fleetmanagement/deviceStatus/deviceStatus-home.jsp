<%@ taglib uri="/WEB-INF/tld/liferay-portlet.tld" prefix="portlet"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@page import=" static com.lexmark.constants.LexmarkConstants.ROLE_SERVICE_SUPPORT" %>
<%@page import=" static com.lexmark.constants.LexmarkConstants.ROLE_SERVICE_SUPPORT_DUP" %>
<%@page import=" static com.lexmark.constants.LexmarkConstants.ROLE_ACCOUNT_MANAGEMENT" %>


<c:set var="showDeviceStatus" value="none"/>
<c:set var="showDeviceStatusUtil" value="none"/>
		<c:if test="${fleetMgmtForm.showDeviceStatus}">
			<c:set var="showDeviceStatus" value="block"/>
		</c:if>
		<c:if test="${fleetMgmtForm.showDeviceUtilization}">
			<c:set var="showDeviceStatusUtil" value="block"/>	
		</c:if>
		<div id="main-device-Status" style="position:relative;height: 33px; display: ${showDeviceStatus};">
				<div class="mapDeviceStatus wAuto" id="device-status-main">
					<span class="mapDeviceStatusSpan" onclick="openDeviceStatus()">
						<span class="arrow_icon arrow_right"></span>
						<input id="mapDeviceStatusRadioFlag" type="radio" name="mapDeviceStatusRadioFlag" disabled=""/>
						<span class="mapDeviceStatusHeader"><spring:message code="fleetmanagement.headers.devicestatus"/></span>
					</span>
					<span class="clearBoth"></span>
				</div>
				<div id="device-status-ajax-content"></div>
			</div>
			<div id="main-device-utilization" style="position:relative;height: 33px; display: ${showDeviceStatusUtil};">
				<div class="mapDeviceStatus wAuto"  id="device-utilization-terms-main">
					<span class="mapDeviceStatusSpan" onclick="openDeviceUT()">
						<span class="arrow_icon arrow_right"></span>
						<input id="mapDeviceUTRadioFlag" type="radio" name="mapDeviceUTRadioFlag" disabled=""/>
						<span class="mapDeviceStatusHeader"><spring:message code="fleetmanagement.headers.devicesUtilTerms"/></span>
					</span>
					<span class="clearBoth"></span>
				</div>
				<div id="device-utilizationAndTerms-ajax-content">
					<div class="mapUTStatusBody clearBoth">
					<div id="UtilizationStatusDiv">
						<div  class="deviceStatusSubHeader" onclick="toggleClass('#UtilizationStatusDiv','openSub');toggleSlide('#utilizationStatusSubMenu','up')">
							<input id="utilizationStatusRadioFlag" type="radio" name="utilizationStatusRadioFlag" disabled=""/>
							<span class="mapDeviceStatusHeader"><spring:message code="fleetmanagement.headers.utilization"/></span>
							<span class="arrow_icon up-down floatR"></span>
						</div>
						<div id="utilizationStatusSubMenu" class="deviceStatusSubMenu">
							<a class="floatR" href="javascript:deviceStatus.__utilization.clear(true);"><spring:message code="fleetmanagement.headers.clear"/></a>
							<div class="subMenuPanel">
							</div>
							<div id="utilization_pages" class="onlyDropdownMultiSelect"></div>							
						</div>
					</div> 

					<div id="expiredStatusDiv">
						<div  class="deviceStatusSubHeader" onclick="toggleClass('#expiredStatusDiv','openSub');toggleSlide('#expiredStatusSubMenu','up')">
							<input id="expiredStatusRadioFlag" type="radio" name="expiredStatusRadioFlag" disabled=""/>
							<span class="mapDeviceStatusHeader"><spring:message code="fleetmanagement.headers.expired"/></span>
							<span class="arrow_icon up-down floatR"></span>
						</div>
						<div id="expiredStatusSubMenu" class="deviceStatusSubMenu">
							<a class="floatR" href="javascript:deviceStatus.__expirationStatus.clear(true);"><spring:message code="fleetmanagement.headers.clear"/></a>
							<div class="subMenuPanel">
								<div id="expiration_status" class="onlyDropdownMultiSelect"></div>
							</div>
							
						</div>
						
					</div>
							
						
					</div>
				</div>
			</div>
			<portlet:resourceURL var="loadDeviceStatus" id="showDeviceStatus"></portlet:resourceURL>
			<script>
			function openDeviceStatus(){
				if($('#device-status-ajax-content').html()==''){
					showOverlay();
					$.get("${loadDeviceStatus}",function(response){
						hideOverlay();
						$('#device-status-ajax-content').append(response);
						reskinInputCheckbox();
						toggleDeviceStatus();
					});	
				}else{
					toggleDeviceStatus();
					toggleClass('#device-utilization-terms-main','open',false);
					$('.mapUTStatusBody').hide();	
				}
			}
			function openDeviceUT(){
				if($('#device-status-ajax-content').html()==''){
					showOverlay();
					$.get("${loadDeviceStatus}",function(response){
						hideOverlay();
						$('#device-status-ajax-content').append(response);
						reskinInputCheckbox();
						toggleDeviceUtilizationTerms();
					});	
				}else{
					toggleDeviceUtilizationTerms();	
					toggleClass('#device-status-main','open',false);
					$('.mapDeviceStatusBody').hide();
				}
			}
			function toggleDeviceStatus(){
				toggleSlide('.mapDeviceStatusBody','left');
				toggleClass('#device-status-main','open');
			}
			function toggleDeviceUtilizationTerms(){
				toggleSlide('.mapUTStatusBody','left');
				toggleClass('#device-utilization-terms-main','open');
			}
			var roleList=[];
			<c:forEach items="${fleetMgmtForm.roleList}" var="loopStatus">
			roleList.push("${loopStatus}");
			</c:forEach>
			function showDeviceStatusOnAccess(deviceStat,deviceUtilz){
				if(deviceStat && (roleList.indexOf("<%=ROLE_SERVICE_SUPPORT%>")!=-1 ||roleList.indexOf("<%=ROLE_SERVICE_SUPPORT_DUP%>")!=-1 )){
					$('#main-device-Status').show();
				}else{
					$('#main-device-Status').hide();
				} if (deviceUtilz && (roleList.indexOf("<%=ROLE_ACCOUNT_MANAGEMENT%>")!=-1 )){
					$('#main-device-utilization').show();
				}else{
					$('#main-device-utilization').hide();
				}
			}
			</script>