<%@ taglib prefix="html" tagdir="/WEB-INF/tags/html" %>
<%@ taglib uri="/WEB-INF/tld/liferay-portlet.tld" prefix="portlet"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<portlet:resourceURL var="deviceStatusURL" id="deviceStatusInfor"></portlet:resourceURL>
<div id="deviceStatusPopUp" style="display:none;">
			      <div class="popUpBody clearBoth">
					   <div id="alertsDevicePopUpStatusDiv" class="deviceStatusSubHeader openSub">
						       <div  onclick="toggleClass('#alertsDevicePopUpStatusDiv','openSub');toggleSlide('#alert-device-status-popup','up')">
							          <span class="mapDeviceStatusHeader">Alert Device</span>
							          <span class="arrow_icon up-down floatR"></span>
						       </div>
						       <div id="alert-device-status-popup" >
					                <div class="subMenuPanel"></div>
						        </div>
					    </div>
					<div id="alertsSuppliesPopUpStatusDiv" class="deviceStatusSubHeader openSub">
						       <div  onclick="toggleClass('#alertsSuppliesPopUpStatusDiv','openSub');toggleSlide('#alert-supplies-status-popup','up')">
							          <span class="mapDeviceStatusHeader">Alert Supplies</span>
							          <span class="arrow_icon up-down floatR"></span>
						       </div>
						       <div id="alert-supplies-status-popup">
					                <div class="subMenuPanel"></div>
						        </div>
					 </div>	
					<div id="utilisationPopUpStatusDiv" class="deviceStatusSubHeader openSub">
						<div  onclick="toggleClass('#utilisationPopUpStatusDiv','openSub');toggleSlide('#utilization-status-popup','up')">
							<span class="mapDeviceStatusHeader">Utilization Monthly Average</span>
							<span class="arrow_icon up-down floatR"></span>
						</div>
						<div id="utilization-status-popup"  >
						    <div class="subMenuPanel">
						    </div>
			            </div>
			        </div>	
					<div id="reportsStatusspopUpStatusDiv" class="deviceStatusSubHeader openSub">
						<div  onclick="toggleClass('#reportsStatusspopUpStatusDiv','openSub');toggleSlide('#report-status-popup','up')">
							<span class="mapDeviceStatusHeader">Reporting Status</span>
							<span class="arrow_icon up-down floatR"></span>
							<br>
						</div>
						<div id="report-status-popup" >
						    <div class="subMenuPanel">
							</div>
						</div>
					</div>	
					<div id="expiredStatusspopUpStatusDiv" class="deviceStatusSubHeader openSub">
						<div  onclick="toggleClass('#expiredStatusspopUpStatusDiv','openSub');toggleSlide('#expired-status-popup','up')">
							<span class="mapDeviceStatusHeader">Terms</span>
							<span class="arrow_icon up-down floatR"></span>
						</div>
						<div id="expired-status-popup" >
						    <div class="subMenuPanel"></div>
						</div>
					</div>	
				
				</div>
				
			</div>
			<script id="expiration-popup-Alert-Details" type="text/x-handlebars-template">
				
				<table><thead><tr><th style="border-left:0">Entitlement Type</th><th>Expiration Date</th></tr></thead>
				{{#if DeviceStatus.EntitlementExpiration}}
				{{#DeviceStatus.EntitlementExpiration}}
				<tr><td>{{helpLocalizeExpiring EntitlementType}}</td>
				<td>{{localizeExpiredDate ExpirationDate}}</td></tr>
				{{/DeviceStatus.EntitlementExpiration}}	
				{{else}}
				<tr><td colspan="2"></td></tr>
				{{/if}}
				</table>
				
			</script>
			<script id="reporting-status-popup-Details" type="text/x-handlebars-template">
				
				<table><thead><tr><th style="border-left:0">Reporting Status</th>
						<th>Last Reported</th>
						</tr></thead>
				{{#if DeviceStatus.Reporting}}
				{{#DeviceStatus.Reporting}}
				<tr><td>{{ReportingStatus}}</td>
				<td>{{localizeExpiredDate LastReportedDateTime}}</td></tr>
				{{/DeviceStatus.Reporting}}	
				{{else}}
				<tr><td colspan="2">Reporting as expected</td></tr>
				{{/if}}
				</table>
				
			</script>
			<script id="utilization-popup-Details" type="text/x-handlebars-template">
				
				<table><thead><tr>
					<th style="border-left:0">Actual</th>
					<th>Target</th>
					<th>Utilization Level</th>
					</tr>
				</thead>
				{{#if DeviceStatus.Utilization}}
				{{#DeviceStatus.Utilization}}
				
				<tr><td>{{MonthlyAvgUsage}}</td>
				<td>{{ExpectedLTPC}}</td>
				<td>{{UtilizationLevel}}</td></tr>
				{{/DeviceStatus.Utilization}}	
				{{else}}
				<tr><td colspan="3">No data available</td></tr>
				{{/if}}
				</table>
				
			</script>
			<script id="alert-popup-Details" type="text/x-handlebars-template">
				
				<table><thead><tr>	<th style="border-left:0">Alert Type</th>
									<th>Date/Time</th>
									<th>LTPC</th>
									<th>Color</th>
								</tr></thead>
				{{#if alert}}
				{{#alert}}
				<tr class="{{{alternate @index}}}"><td>{{helpLocalizeAlerts AlertType}}</td>
				<td>{{localizeExpiredDate AlertDateTime}}</td>
				<td>{{LTPC}}</td>
				<td>{{ColorLTPC}}</td>
				</tr>
				{{/alert}}	
				{{else}}
				<tr><td colspan="4">No alerts detected</td></tr>
				{{/if}}
				</table>
				
			</script>

			
			<script>
			var deviceStatusDialog=$('#deviceStatusPopUp').dialog({
				autoOpen: false,
				title: "Device Status",
				modal: true,
				height: 550,
				width: 800,
				close:function(){
					hideOverlay();
				}
				});
			function showDeviceStatus(id)
			{
				//id="1-4FXU-5740";
				//fire ajax to get data from server
				showOverlay();
				$.getJSON("${deviceStatusURL}&id="+id,function(response){
					if(response != null){	

						$('#expired-status-popup').html(templateDeviceStatusPopup.generateExpiring(response.getDeviceStatusOutput));
						$('#alert-device-status-popup').html(templateDeviceStatusPopup.generateAlert({alert:response.getDeviceStatusOutput.DeviceStatus.DeviceAlert}));
						$('#alert-supplies-status-popup').html(templateDeviceStatusPopup.generateAlert({alert:response.getDeviceStatusOutput.DeviceStatus.SuppliesAlert}));
						$('#utilization-status-popup').html(templateDeviceStatusPopup.generateUtilization(response.getDeviceStatusOutput));
						$('#report-status-popup').html(templateDeviceStatusPopup.generateReporting(response.getDeviceStatusOutput));
					}
				deviceStatusDialog.dialog('open');
					
				});
				
				
				
			}
			
			var templateDeviceStatusPopup={
					initTemplate:function(Y){
						this.expTemplate = Y.Handlebars.compile(Y.one('#expiration-popup-Alert-Details').getHTML());
						this.alertTemplate = Y.Handlebars.compile(Y.one('#alert-popup-Details').getHTML());
						this.utilTemplate = Y.Handlebars.compile(Y.one('#utilization-popup-Details').getHTML());
						this.rprtTemplate = Y.Handlebars.compile(Y.one('#reporting-status-popup-Details').getHTML());
					},
					generateExpiring:function(deviceStatus){
						return this.expTemplate(deviceStatus);
					},
					generateAlert:function(deviceStatus){
						return this.alertTemplate(deviceStatus);
					},
					generateUtilization:function(deviceStatus){
						return this.utilTemplate(deviceStatus);
					},
					generateReporting:function(deviceStatus){
						return this.rprtTemplate(deviceStatus);
					}
					
			}
			
		
			YUI().use('handlebars', 'node-base', function (Y) {
				 Y.on('domready', function () {
					 templateDeviceStatusPopup.initTemplate(Y);    	 	
				 });
				 Y.Handlebars.registerHelper('helpLocalizeAlerts', function (val) {
					 	if(alertTypeCodesLOV[val]){
					 		return alertTypeCodesLOV[val];
					 	}else
					 		return val;
					 
				 });
				 Y.Handlebars.registerHelper('helpLocalizeExpiring', function (val) {
					 	if(expirationArrayCode[val.toLowerCase()]){
					 		return expirationArrayCode[val.toLowerCase()];
					 	}else
					 		return val;
					 
				 });	 
				 Y.Handlebars.registerHelper('localizeDateDevice', function (dateVal) {
					 if(dateVal!=null)
						 return localizeDateTimeFromInputDate(dateVal);
					 else 
						 return "";
					});
				 Y.Handlebars.registerHelper('localizeExpiredDate', function (dateVal) {
					 if(dateVal!=null)
						 return formatDateToDefaultLBS(dateVal);
					 else 
						 return "";
					});
					Y.Handlebars.registerHelper('alternate', function (index) {
						//if(index%2==0)return "greyBg";
						//else return "";
						
					});
			});
			
			</script>