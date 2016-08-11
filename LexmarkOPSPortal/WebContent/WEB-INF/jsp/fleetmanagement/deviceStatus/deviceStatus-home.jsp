<%@ taglib uri="/WEB-INF/tld/liferay-portlet.tld" prefix="portlet"%>
			<div style="position:relative;height: 33px;">
				<div class="mapDeviceStatus wAuto" id="device-status-main">
					<span class="mapDeviceStatusSpan" onclick="openDeviceStatus()">
						<span class="arrow_icon arrow_right"></span>
						<input id="mapDeviceStatusRadioFlag" type="radio" name="mapDeviceStatusRadioFlag" disabled=""/>
						<span class="mapDeviceStatusHeader">Device Status</span>
					</span>
					<span class="clearBoth"></span>
				</div>
				<div id="device-status-ajax-content"></div>
			</div>
			<div style="position:relative;height: 33px;" >
				<div class="mapDeviceStatus wAuto"  id="device-utilization-terms-main">
					<span class="mapDeviceStatusSpan" onclick="openDeviceUT()">
						<span class="arrow_icon arrow_right"></span>
						<input id="mapDeviceUTRadioFlag" type="radio" name="mapDeviceUTRadioFlag" disabled=""/>
						<span class="mapDeviceStatusHeader">Device Utilization & Terms</span>
					</span>
					<span class="clearBoth"></span>
				</div>
				<div id="device-utilizationAndTerms-ajax-content">
					<div class="mapUTStatusBody clearBoth">
					<div id="UtilizationStatusDiv">
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
						</div>
					</div>
					<div id="expiredStatusDiv">
						<div  class="deviceStatusSubHeader" onclick="toggleClass('#expiredStatusDiv','openSub');toggleSlide('#expiredStatusSubMenu','up')">
							<input id="expiredStatusRadioFlag" type="radio" name="expiredStatusRadioFlag" disabled=""/>
							<span class="mapDeviceStatusHeader">Expiring</span>
							<span class="arrow_icon up-down floatR"></span>
						</div>
						<div id="expiredStatusSubMenu" class="deviceStatusSubMenu">
							<a class="floatR" href="javascript:deviceStatus.__expirationStatus.clear(true);">Clear</a>
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
			</script>