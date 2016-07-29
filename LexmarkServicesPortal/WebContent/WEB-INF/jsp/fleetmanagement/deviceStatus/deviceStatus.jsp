<%@ taglib prefix="html" tagdir="/WEB-INF/tags/html" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
			<div class="mapDeviceStatusBody clearBoth">
				
					<div id="alertsSuppliesStatusDiv">
						<div  class="deviceStatusSubHeader"  onclick="toggleClass('#alertsSuppliesStatusDiv','openSub');toggleSlide('#alertsSuppliesStatusSubMenu','up')">
							<input id="alertsSuppliesStatusRadioFlag" type="radio" name="alertsSuppliesStatusRadioFlag" disabled=""/>
							<span class="mapDeviceStatusHeader"><spring:message code="fleetmanagement.headers.suppliesAlerts"/></span>
							<span class="arrow_icon up-down floatR"></span>
						</div>
						<div id="alertsSuppliesStatusSubMenu" class="deviceStatusSubMenu">
							<a class="floatR" href="javascript:deviceStatus.__alerts.clearSupplies(true);"><spring:message code="fleetmanagement.headers.clear"/></a>
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
							<span class="mapDeviceStatusHeader"><spring:message code="fleetmanagement.headers.deviceAlerts"/></span>
							<span class="arrow_icon up-down floatR"></span>
						</div>
						<div id="alertsDeviceStatusSubMenu" class="deviceStatusSubMenu">
							<a class="floatR" href="javascript:deviceStatus.__alerts.clearDevice(true);"><spring:message code="fleetmanagement.headers.clear"/></a>
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
							<span class="mapDeviceStatusHeader"><spring:message code="fleetmanagement.headers.reportingstatus"/></span>
							<span class="arrow_icon up-down floatR"></span>
						</div>
						<div id="reportingStatusSubMenu" class="deviceStatusSubMenu">
							<a class="floatR" href="javascript:deviceStatus.__reportingStatus.clear(true);"><spring:message code="fleetmanagement.headers.clear"/></a>
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
			
			

			<div id="deviceStatusPopUp" style="display:none;">
			      <div class="popUpBody clearBoth">
					   <div id="alertsPopUpStatusDiv" class="deviceStatusSubHeader">
						       <div  onclick="toggleClass('#alertsPopUpStatusDiv','openSub');toggleSlide('#alertsStatusSubMenu1','up')">
							          <span class="mapDeviceStatusHeader"><spring:message code="fleetmanagement.headers.alert"/></span>
							          <span class="arrow_icon up-down floatR"></span>
						       </div>
						       <div id="alertsStatusSubMenu1" style="display:none">
					                <div class="subMenuPanel"></div>
						        </div>
					    </div>	
					<div id="UtilisationPopUpStatusDiv" class="deviceStatusSubHeader">
						<div  onclick="toggleClass('#UtilisationPopUpStatusDiv','openSub');toggleSlide('#alertsStatusSubMenu2','up')">
							<span class="mapDeviceStatusHeader"><spring:message code="fleetmanagement.headers.utilization"/></span>
							<span class="arrow_icon up-down floatR"></span>
						</div>
						<div id="alertsStatusSubMenu2"  style="display:none">
						    <div class="subMenuPanel">
						    </div>
			            </div>
			        </div>	
					<div id="ReportsStatusspopUpStatusDiv" class="deviceStatusSubHeader">
						<div  onclick="toggleClass('#ReportsStatusspopUpStatusDiv','openSub');toggleSlide('#alertsStatusSubMenu3','up')">
							<span class="mapDeviceStatusHeader"><spring:message code="fleetmanagement.headers.reportingstatus"/></span>
							<span class="arrow_icon up-down floatR"></span>
							<br>
						</div>
						<div id="alertsStatusSubMenu3"  style="display:none">
						    <div class="subMenuPanel">
						     <br>
							</div>
						</div>
					</div>	
					
				
				</div>
				
			</div>

<script id="device-utilization" type="text/x-handlebars-template">
{{#if Utilization}}
<div class="headerTable">
<table><thead><tr><th><spring:message code="fleetmanagement.tableheaders.serialnumber"/></th><th><spring:message code="fleetmanagement.tableheaders.expected"/></th><th><spring:message code="fleetmanagement.tableheaders.actual"/></th></tr></thead></table></div>
<div class="bodyTable"><table>
			{{#Utilization}}
			<tr><td><a onclick="highLightInMap('{{id}}')">{{serialNumber}}</a></td>
				<td>{{ExpectedLTPC}}</td><td>{{MonthlyAvgUsage}}</td></tr>
			{{/Utilization}}	
</table></div>
{{/if}}
</script>
<script id="device-report-Status" type="text/x-handlebars-template">
{{#if Reporting}}
<table><thead><tr><th><spring:message code="fleetmanagement.tableheaders.serialnumber"/></th><th><spring:message code="deviceStatus.header.lastMeterRead"/></th></tr></thead>
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
<table><thead><tr><th><spring:message code="fleetmanagement.tableheaders.serialnumber"/></th><th><spring:message code="fleetmanagement.tableheaders.alertdescription"/></th></tr></thead>
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
<table><thead><tr><th><spring:message code="fleetmanagement.tableheaders.serialnumber"/></th><th><spring:message code="fleetmanagement.tableheaders.expirationdate"/></th></tr></thead>
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
alertSuppliesArray.push({"value":"Select All", "displayValue":"<spring:message code='lbs.label.selectAll'/>"});
<c:forEach items="${alertSupplies}" var="loopStatus">
alertSuppliesArray.push({"value":"${loopStatus.key}","displayValue":"${loopStatus.value}"});
</c:forEach>

var alertDeviceArray=[];

alertDeviceArray.push({"value":"", "displayValue":"Select Device Alert"});
alertDeviceArray.push({"value":"Select All", "displayValue":"<spring:message code='lbs.label.selectAll'/>"});
<c:forEach items="${alertDevice}" var="loopStatus">
alertDeviceArray.push({"value":"${loopStatus.key}","displayValue":"${loopStatus.value}"});
</c:forEach>


var utilizationArray=[];
utilizationArray.push({"value":"", "displayValue":"Select Utilization Type"});
utilizationArray.push({"value":"Select All", "displayValue":"<spring:message code='lbs.label.selectAll'/>"});
<c:forEach items="${utilization}" var="loopStatus">
utilizationArray.push({"value":"${loopStatus.key}","displayValue":"${loopStatus.value}"});
</c:forEach>
utilizationArray.push({"value":"NoUtilization", "displayValue":"NoUtilization"});



var reportingStatArray=[];
reportingStatArray.push({"value":"", "displayValue":"Select Reporting Status"});
reportingStatArray.push({"value":"Select All", "displayValue":"<spring:message code='lbs.label.selectAll'/>"});
<c:forEach items="${reportingStatus}" var="loopStatus">
reportingStatArray.push({"value":"${loopStatus.key}","displayValue":"${loopStatus.value}"});
</c:forEach>



function highLightInMap(id){
	lbs.highlightMap(id);
	respProcessorObj.showAssetInLeftNav(id);
}

var mock={

	    "action": "deviceStatusListNotification",
	    "item": "asset",
	    "info": {
	        "supplies": [
	            {
	                "id": 123,"serialNumber": 123,"detail": "Black Toner",
	                "codes": [
	                    "item1",
	                    "item2"
	                ]
	            },
	            {
	                "id": 124,"serialNumber": "sl-124","detail": "Staples"
	            },
	            {
	                "id": 125,"serialNumber": "sl125","detail": "Staples"
	            },
	            {
	                "id": 126,"serialNumber": 126,"detail": "Staples"
	            },
	            {
	                "id": 127,"serialNumber": 127,"detail": "Staples"
	            },
	            {
	                "id": 128,"serialNumber": 128,"detail": "Staples"
	            },
	            {
	                "id": 129,"serialNumber": 129,"detail": "Staples"
	            },
	            {
	                "id": 130,"serialNumber": 130,"detail": "Staples"
	            },
	            {
	                "id": 131,"serialNumber": 131,"detail": "Staples"
	            }
	        ],
	        "device": [/*
	            {
	                "id": 225,"serialNumber": 126,"detail": "1xx"
	            },
	            {
	                "id": 228,"serialNumber": 1255,"detail": "2xx"
	            },
	            {
	                "id": 229,"serialNumber": 1256,"detail": "2xx"
	            },
	            {
	                "id": 230,"serialNumber": 1257,"detail": "2xx"
	            },
	            {
	                "id": 231,"serialNumber": 1258,"detail": "2xx"
	            },
	            {
	                "id": 232,"serialNumber": 1259,"detail": "2xx"
	            }*/
	        ],
	        "utilization": [
	            {
	                "id": "312","serialNumber": "SN1","detail": "Over Utilized","ActualLTPC": "1400","ExpectedLTPC": "2500"
	            },
	            {
	                "id": "313","serialNumber": "SN2","detail": "Under Utilized","ActualLTPC": "1400","ExpectedLTPC": "2500"
	            },
	            {
	                "id": "314","serialNumber": "SN2","detail": "Under Utilized","ActualLTPC": "1400","ExpectedLTPC": "2500"
	            },
	            {
	                "id": "315","serialNumber": "SN2","detail": "Under Utilized","ActualLTPC": "1400","ExpectedLTPC": "2500"
	            },
	            {
	                "id": "316","serialNumber": "SN2","detail": "Under Utilized","ActualLTPC": "1400","ExpectedLTPC": "2500"
	            }
	        ],
	        "reportingStatus": [
	            {
	                "id": "412","serialNumber": "SN1","detail": "Missing Meter Reads","LastReportedDateTime": "03/11/2011 10:04:12"
	            },
	            {
	                "id": "413","serialNumber": "SN1","detail": "Not Reporting","LastReportedDateTime": "03/11/2011 10:04:12"
	            },
	            {
	                "id": "414","serialNumber": "SN1","detail": "Not Reporting","LastReportedDateTime": "03/11/2011 10:04:12"
	            },
	            {
	                "id": "415","serialNumber": "SN1","detail": "Not Reporting","LastReportedDateTime": "03/11/2011 10:04:12"
	            },
	            {
	                "id": "416","serialNumber": "SN1","detail": "Not Reporting","LastReportedDateTime": "03/11/2011 10:04:12"
	            }
	        ],
	        "expiration": [
	            {
	                "id": "513","serialNumber": "SN1","detail": "Service","ExpirationDate": "04/18/2015 11:04:12"
	            },
	            {
	                "id": "517","serialNumber": "SN1","detail": "BreakFix","ExpirationDate": "04/18/2015 11:04:12"
	            },
	            {
	                "id": "518","serialNumber": "SN1","detail": "Supplies","ExpirationDate": "04/18/2015 11:04:12"
	            },
	            {
	                "id": "519","serialNumber": "SN1","detail": "Supplies","ExpirationDate": "04/18/2015 11:04:12"
	            },
	            {
	                "id": "520","serialNumber": "SN1","detail": "Supplies","ExpirationDate": "04/18/2015 11:04:12"
	            }
	        ]
	    }
	};
</script>
<script type="text/javascript" src="<html:rootPath/>js/deviceStatus.js?version=0.05"></script>
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