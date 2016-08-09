<%@ taglib prefix="html" tagdir="/WEB-INF/tags/html" %>
<%@ taglib uri="/WEB-INF/tld/liferay-portlet.tld" prefix="portlet"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div id="deviceStatusPopUp" style="display:none;">
			      <div class="popUpBody clearBoth">
					   <div id="alertsPopUpStatusSuppliesDiv" class="deviceStatusSubHeader openSub">
						       <div  onclick="toggleClass('#alertsPopUpStatusSuppliesDiv','openSub');toggleSlide('#alert-supplies-status-popup','up')">
							          <span class="mapDeviceStatusHeader"><spring:message code="deviceStatus.header.alertSupplies"/></span>
							          <span class="arrow_icon up-down floatR"></span>
						       </div>
						       <div id="alert-supplies-status-popup">
					                <div class="subMenuPanel"></div>
						        </div>
					    </div>
				<div id="alertsPopUpStatusDeviceDiv" class="deviceStatusSubHeader openSub">
						       <div  onclick="toggleClass('#alertsPopUpStatusDeviceDiv','openSub');toggleSlide('#alert-device-status-popup','up')">
							          <span class="mapDeviceStatusHeader"><spring:message code="deviceStatus.header.alertDevice"/></span>
							          <span class="arrow_icon up-down floatR"></span>
						       </div>
						       <div id="alert-device-status-popup">
					                <div class="subMenuPanel"></div>
						        </div>
					    </div>	
						

					
					<div id="utilisationPopUpStatusDiv" class="deviceStatusSubHeader openSub">
						<div  onclick="toggleClass('#utilisationPopUpStatusDiv','openSub');toggleSlide('#utilization-status-popup','up')">
							<span class="mapDeviceStatusHeader"><spring:message code="fleetmanagement.headers.utilizationmonthlyaverage"/></span>
							<span class="arrow_icon up-down floatR"></span>
						</div>
						<div id="utilization-status-popup" >
						    <div class="subMenuPanel">
						    </div>
			            </div>
			        </div>
			       
					<div id="reportsStatusspopUpStatusDiv" class="deviceStatusSubHeader openSub">
						<div  onclick="toggleClass('#reportsStatusspopUpStatusDiv','openSub');toggleSlide('#report-status-popup','up')">
							<span class="mapDeviceStatusHeader"><spring:message code="fleetmanagement.headers.reportingstatus"/></span>
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
							<span class="mapDeviceStatusHeader"><spring:message code="fleetmanagement.headers.expiringTerms"/></span>
							<span class="arrow_icon up-down floatR"></span>
							<br>
						</div>
						<div id="expired-status-popup" >
						    <div class="subMenuPanel"></div>
						</div>
					</div>	
					
				</div>
				
			</div>
			<script id="expiration-popup-Alert-Details" type="text/x-handlebars-template">
				
				<table><thead><tr><th style="border-left:0"><spring:message code="deviceStatus.tbheader.entitlementType"/></th><th><spring:message code="fleetmanagement.tableheaders.expirationdate"/></th></tr></thead>
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
				
				<table><thead><tr><th style="border-left:0"><spring:message code="fleetmanagement.headers.reportingstatus"/></th>
						<th><spring:message code="fleetmanagement.tableheaders.lastreported"/></th>
						</tr></thead>
				{{#if DeviceStatus.Reporting}}
				{{#DeviceStatus.Reporting}}
				<tr><td>{{localizeReportingStatus ReportingStatus}}</td>
				<td>{{localizeExpiredDate LastReportedDateTime}}</td></tr>
				{{/DeviceStatus.Reporting}}	
				{{else}}
				<tr><td colspan="2"><spring:message code="lbs.devicestatuspopup.alert.reporting"/></td></tr>
				{{/if}}
				</table>
				
			</script>
			<script id="utilization-popup-Details" type="text/x-handlebars-template">
				
				<table><thead><tr>
				
					<th style="border-left:0"><spring:message code="fleetmanagement.tableheaders.expected"/></th>
					<th><spring:message code="fleetmanagement.deviceStatusPopup.headers.actual"/></th>
					<th><spring:message code="deviceStatus.tbheader.utilizationLevel"/></th>
					</tr>
				</thead>
				{{#if DeviceStatus.Utilization}}
				{{#DeviceStatus.Utilization}}
				
				<tr><td>{{ExpectedLTPC}}</td>
				<td>{{MonthlyAvgUsage}}</td>
				<td>{{UtilizationLevel}}</td></tr>
				{{/DeviceStatus.Utilization}}
				{{else}}
				<tr><td colspan="3"><spring:message code="lbs.devicestatuspopup.alert.utilization"/></td></tr>
				{{/if}}	
				</table>
				
			</script>
			<script id="alert-popup-Details" type="text/x-handlebars-template">
				
				<table><thead><tr>	<th style="border-left:0"><spring:message code="fleetmanagement.deviceStatusPopup.headers.alerttype"/></th>
									<th><spring:message code="requestInfo.info.heading.datetime"/></th>
									<th><spring:message code="pageCntUpload.ltpc"/></th>
									<th><spring:message code="deviceStatusPopup.label.color"/></th>
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
				<tr><td colspan="4"><spring:message code="lbs.devicestatuspopup.alert.supplies"/></td></tr>
				{{/if}}
				
				</table>
				
			</script>

			
			<script>
			
			
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
				 Y.Handlebars.registerHelper('localizeReportingStatus', function (val) {
				 	 val = val.replace(/ /g, "");
					 if(reportingCodesLOV[val])
						 return reportingCodesLOV[val];
					 else 
						 return val;
					});
					Y.Handlebars.registerHelper('alternate', function (index) {
						//if(index%2==0)return "greyBg";
						//else return "";
						
					});
			});
			
			</script>