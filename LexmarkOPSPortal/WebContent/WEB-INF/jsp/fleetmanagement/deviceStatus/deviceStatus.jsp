<%@ taglib prefix="html" tagdir="/WEB-INF/tags/html" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

			<div class="mapDeviceStatusBody clearBoth">
				
					<div id="alertsSuppliesStatusDiv">
						<div  class="deviceStatusSubHeader"  onclick="toggleClass('#alertsSuppliesStatusDiv','openSub');toggleSlide('#alertsSuppliesStatusSubMenu','up')">
							<input id="alertsSuppliesStatusRadioFlag" type="radio" name="alertsSuppliesStatusRadioFlag" disabled=""/>
							<span class="mapDeviceStatusHeader">Supplies Alerts</span>
							<span class="arrow_icon up-down floatR"></span>
						</div>
						<div id="alertsSuppliesStatusSubMenu" class="deviceStatusSubMenu">
							<a class="floatR" href="javascript:deviceStatus.__alerts.clearSupplies(true);">Clear</a>
							<div class="subMenuPanel">
								
									<div id="alert_supplies" class="onlyDropdownMultiSelect"></div>
									<!-- <div id="alert_device" class="onlyDropdownMultiSelect"></div> -->

								<!-- <div id="alertsSuppliesStatusDescription" class="statusDescriptionBlock"></div> -->
							</div>
							
						</div>
					</div>
					
					<div id="alertsDeviceStatusDiv">
						<div  class="deviceStatusSubHeader"  onclick="toggleClass('#alertsDeviceStatusDiv','openSub');toggleSlide('#alertsDeviceStatusSubMenu','up')">
							<input id="alertsDeviceStatusRadioFlag" type="radio" name="alertsDeviceStatusRadioFlag" disabled=""/>
							<span class="mapDeviceStatusHeader">Device Alerts</span>
							<span class="arrow_icon up-down floatR"></span>
						</div>
						<div id="alertsDeviceStatusSubMenu" class="deviceStatusSubMenu">
							<a class="floatR" href="javascript:deviceStatus.__alerts.clearDevice(true);">Clear</a>
							<div class="subMenuPanel">
								
									<div id="alert_device" class="onlyDropdownMultiSelect"></div>

								<!-- <div id="alertsSuppliesStatusDescription" class="statusDescriptionBlock"></div> -->
							</div>
							
						</div>
					</div>
					
				<!-- 	<div id="UtilizationStatusDiv">
						<div  class="deviceStatusSubHeader" onclick="toggleClass('#UtilizationStatusDiv','openSub');toggleSlide('#utilizationStatusSubMenu','up')">
							<input id="utilizationStatusRadioFlag" type="radio" name="utilizationStatusRadioFlag" disabled=""/>
							<span class="mapDeviceStatusHeader">Utilization</span>
							<span class="arrow_icon up-down floatR"></span>
						</div>
						<div id="utilizationStatusSubMenu" class="deviceStatusSubMenu">
							<a class="floatR" href="javascript:deviceStatus.__utilization.clear(true);">Clear</a>
							<div class="subMenuPanel">
							</div>
							<div id="utilization_pages" class="onlyDropdownMultiSelect"></div>
							<div  id="utilizationStatusDescription"  class="statusDescriptionBlock threeCellTable">
								
							</div>
							
						</div>
					</div> -->
					
					<div id="ReportingStatusDiv">
						<div  class="deviceStatusSubHeader" onclick="toggleClass('#ReportingStatusDiv','openSub');toggleSlide('#reportingStatusSubMenu','up')">
							<input id="reportingStatusRadioFlag" type="radio" name="reportingStatusRadioFlag" disabled=""/>
							<span class="mapDeviceStatusHeader">Reporting Status</span>
							<span class="arrow_icon up-down floatR"></span>
						</div>
						<div id="reportingStatusSubMenu" class="deviceStatusSubMenu">
							<a class="floatR" href="javascript:deviceStatus.__reportingStatus.clear(true);">Clear</a>
							<div class="subMenuPanel">
								
								<div id="reporting_status"  class="onlyDropdownMultiSelect"></div>
							<!-- <div  id="reportingStatusDescription"  class="statusDescriptionBlock"></div>	 -->
							</div>
							
						</div>
					</div>
					
				<!-- 	<div id="expiredStatusDiv">
						<div  class="deviceStatusSubHeader" onclick="toggleClass('#expiredStatusDiv','openSub');toggleSlide('#expiredStatusSubMenu','up')">
							<input id="expiredStatusRadioFlag" type="radio" name="expiredStatusRadioFlag" disabled=""/>
							<span class="mapDeviceStatusHeader">Expiring</span>
							<span class="arrow_icon up-down floatR"></span>
						</div>
						<div id="expiredStatusSubMenu" class="deviceStatusSubMenu">
							<a class="floatR" href="javascript:deviceStatus.__expirationStatus.clear(true);">Clear</a>
							<div class="subMenuPanel">
								<div id="expiration_status" class="onlyDropdownMultiSelect"></div>
								<div  id="expiredStatusDescription"  class="statusDescriptionBlock"></div>
							</div>
							
						</div>
						
					</div> -->
							
						
					</div>
			
			

			

<script id="device-utilization" type="text/x-handlebars-template">
{{#if Utilization}}
<div class="headerTable">
<table><thead><tr><th>Serial Number</th><th>Target</th><th>Actual</th></tr></thead></table></div>
			<div class="bodyTable" id="utilizationDetailsDiv{{id}}"><table>
			{{#Utilization}}
			<tr id="try_{{serialNumber}}"><td><a onclick="highLightInMap('{{id}}')">{{serialNumber}}</a></td>
			<td>{{ExpectedLTPC}}</td><td>{{MonthlyAvgUsage}}</td></tr>
			{{/Utilization}}
			</table></div>	

{{/if}}
</script>
<script id="device-report-Status" type="text/x-handlebars-template">
{{#if Reporting}}
<table><thead><tr><th>Serial Number</th><th>Last Meter Read</th></tr></thead>
			{{#Reporting}}
			<tr><td><a onclick="highLightInMap('{{id}}')">{{serialNumber}}</a></td>
				<td>{{{localizeExpiredDate LastReportedDateTime}}}</td>
			</tr>
			{{/Reporting}}	
		</table>
{{/if}}
</script>

<script id="device-Alert-Details" type="text/x-handlebars-template">
{{#if alerts}}
<table><thead><tr><th>Serial Number</th><th>Alert Description</th></tr></thead>
		{{#alerts}}
			{{#if codes}}
				{{#codes}}
					<tr><td><a onclick="highLightInMap('{{../id}}')">{{../serialNumber}}</a></td>
					<td>{{{helpLocalizeAlerts this}}}</td></tr>
				{{/codes}}
		   {{/if}}	
		{{/alerts}}
</table>
{{/if}}
</script>

<script id="expiration-Alert-Details" type="text/x-handlebars-template">
{{#if Expired}}
<table><thead><tr><th>Serial Number</th><th>Expiration Date</th></tr></thead>
			{{#Expired}}
			<tr><td><a onclick="highLightInMap('{{id}}')">{{serialNumber}}</a></td>
				<td>{{{localizeExpiredDate ExpirationDate}}}</td></tr>
			{{/Expired}}	
</table>
{{/if}}
</script>

<script>
var alertSuppliesArray=[];

alertSuppliesArray.push({"value":"", "displayValue":"Select Supplies Alert"});
alertSuppliesArray.push({"value":"Select All", "displayValue":"Select All"});
<c:forEach items="${alertSupplies}" var="loopStatus">
alertSuppliesArray.push({"value":"${loopStatus.key}","displayValue":"${loopStatus.value}"});
</c:forEach>

var alertDeviceArray=[];

alertDeviceArray.push({"value":"", "displayValue":"Select Device Alert"});
alertDeviceArray.push({"value":"Select All", "displayValue":"Select All"});
<c:forEach items="${alertDevice}" var="loopStatus">
alertDeviceArray.push({"value":"${loopStatus.key}","displayValue":"${loopStatus.value}"});
</c:forEach>


var utilizationArray=[];
utilizationArray.push({"value":"", "displayValue":"Select Utilization Type"});
utilizationArray.push({"value":"Select All", "displayValue":"Select All"});
<c:forEach items="${utilization}" var="loopStatus">
utilizationArray.push({"value":"${loopStatus.key}","displayValue":"${loopStatus.value}"});
</c:forEach>
utilizationArray.push({"value":"NoUtilization", "displayValue":"NoUtilization"});
var reportingStatArray=[];
reportingStatArray.push({"value":"", "displayValue":"Select Reporting Status"});
reportingStatArray.push({"value":"Select All", "displayValue":"Select All"});
<c:forEach items="${reportingStatus}" var="loopStatus">
reportingStatArray.push({"value":"${loopStatus.key}","displayValue":"${loopStatus.value}"});
</c:forEach>

function highLightInMap(id){
	lbs.highlightMap(id);
	respProcessorObj.showAssetInLeftNav(id,'asset');
}


</script>
<script type="text/javascript" src="<html:rootPath/>js/deviceStatus.js?version=0.02"></script>
<script>
$(document).ready(function(){
	
	$('#alerts_sup_dev').change(function(){
		if($(this).val()==""){
			deviceStatus.__alerts.clear(true);
			return;
		}
		deviceStatus.__alerts.selectChange($(this).val());
	});
	<%--
	/*$('#utilization_pages').change(function(){
		if($(this).val()==""){
			deviceStatus.__utilization.clear();
			return;}
		deviceStatus.__utilization.selectChange($(this).val());
	});
	$('#reporting_status').change(function(){
		if($(this).val()==""){
			deviceStatus.__reportingStatus.clear();
			return;}
		deviceStatus.__reportingStatus.selectChange($(this).val());
	});
	$('#expiration_status').change(function(){
		if($(this).val()==""){
			deviceStatus.__expirationStatus.clear();
			return;}
		deviceStatus.__expirationStatus.selectChange($(this).val());
	});*/
	--%>
	initDeviceStatus();
	var popupFlag = [false, false];
	var popupNameArray = [".mapDeviceStatusBody",".mapUTStatusBody"];
	
	$('body').click(function() {
	    for (var i = 0; i < popupFlag.length; i++) {
	        if (!popupFlag[i]) {
				var ele = popupNameArray[i];
				if(ele == '.mapDeviceStatusBody' && $(ele).css('display')!="none"){
					toggleSlide('.mapDeviceStatusBody','left');
					toggleClass('#device-status-main','open');
				}
				else if(ele == '.mapUTStatusBody' && $(ele).css('display')!="none"){
					
					toggleSlide('.mapUTStatusBody','left');
					toggleClass('#device-utilization-terms-main','open');
				}
				else{
					$(popupNameArray[i]).hide();
				}
	        }
	    }
	});	
	
	$("#device-status-main,.mapDeviceStatusBody").mouseenter(function() {	
        popupFlag[0] = true;
    });
	$("#device-status-main,.mapDeviceStatusBody").mouseleave(function() {
        popupFlag[0] = false;
    });
    $("#device-utilization-terms-main,.mapUTStatusBody").mouseenter(function() {
        popupFlag[1] = true;
    });
	$("#device-utilization-terms-main,.mapUTStatusBody").mouseleave(function() {
        popupFlag[1] = false;
    });
	$("#uam-mps-view-map-iframe").mouseenter(function() {
	    popupFlag[0] = false;
	    popupFlag[1] = false;
	    setTimeout(function(){
	    	$('body').trigger('click');
	    },1000);
	});
});



	
 




</script>