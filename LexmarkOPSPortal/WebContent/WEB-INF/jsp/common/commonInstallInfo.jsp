<%@page import="com.lexmark.services.util.LexmarkSPOmnitureConstants"%>
<%@ page contentType="text/html" isELIgnored="false"%>
<%@ page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>
<%@ taglib uri="/WEB-INF/tld/liferay-portlet.tld" prefix="portlet"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="html" tagdir="/WEB-INF/tags/html"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page
	import="static com.lexmark.services.util.ChangeMgmtConstant.ADDRESSCLEANSEFIELDS_OUTPUT"%>
	<portlet:renderURL var="addressListPopUpUrl"
		windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>">
		<portlet:param name="action" value="showAddressListPopUp"></portlet:param>
	</portlet:renderURL>
	
<portlet:resourceURL var="buildingListPopulateURL" id="getBuilding"/>
<portlet:resourceURL var="floorURL" id="getFloor"/>
<portlet:resourceURL var="siteURL" id="getSite"/>
<portlet:resourceURL var="zoneURL" id="getZone"/>
<portlet:resourceURL var="getAllLocationURL" id="getAllLocation"/>
<portlet:resourceURL var="siteBuildingListPopulateURL" id="getSiteBuildingZone"/>
	
	
<script type="text/javascript">	
	<%@ include file="../../../../js/commonAddress.js"%>	
</script>

<!-- Javascript added for LBS 1.5- Level of Details checking -->
<script type="text/javascript">	
	<%@ include file="../../../../js/assetLodCheck.js"%>	
</script>

<div class="columnsOne">
	<div class="infoBox columnInner rounded shadow">
			<c:choose>
				<c:when test="${pageFlow eq 'addone'}">
				<h4><spring:message code="requestInfo.heading.installInfo"/></h4>
				</c:when>
				<c:when test="${pageFlow eq 'Change'}">
				<h4><spring:message code="requestInfo.heading.moveInfo"/></h4>
				<div class="portletBlock" style="margin-left:1%">
				<ul class="form  installCheck" style="width: 90%">
								  <div id="movetype_radio" class="">
								  <li class="move_type1"><%--Changed for CI Defect #7853 --%>
									<div style="">
			                        <%-- <label><spring:message code="requestInfo.cm.manageAsset.label.doYouRequireLexmarkToInstallThisDevice"/></label> --%>
									<div id="" class="move_type_confirmation" style="width:auto;;float:left;"><spring:message code="requestInfo.cm.manageAsset.label.doYouRequireLexmarkToPhysicallyMoveThisDevice"/>&nbsp;<span class="req">*</span></div>
									<div class="radio radio_confirm" style="width:auto;float:left;">
									<div style="width:auto;float:left;"><form:radiobutton name="installAsset" path="installAssetFlag" value="Yes" 
									id="installAssetYes" onclick="showHideMoveType('installAssetYes');"/>	
									<spring:message code="requestInfo.option.yes"/>
									</div>
									<div style="width:auto;float:left;margin-left:5px;">
									<form:radiobutton name="installAsset" path="installAssetFlag" value="No"
									id="installAssetNo" onclick="showHideMoveType('installAssetNo');"/>
									<spring:message code="requestInfo.option.no"/>
									</div>
									</div>
									<div style="clear:both"></div>
									</div>
			                      </li>
			                      </div>
								</ul>
				</div>
				</c:when>
				
				<c:when test="${pageFlow eq 'fleetMgmtChange'}">
				<div style="display: none;">
				<div class="portletBlock" style="margin-left:1%">
				<ul class="form  installCheck" style="width: 90%">
								  <div id="movetype_radio" class="">
								  <li class="move_type1"><%--Changed for CI Defect #7853 --%>
									<div style="">
			                        <%-- <label><spring:message code="requestInfo.cm.manageAsset.label.doYouRequireLexmarkToInstallThisDevice"/></label> --%>
									<div id="" class="move_type_confirmation" style="width:auto;;float:left;"><spring:message code="requestInfo.cm.manageAsset.label.doYouRequireLexmarkToPhysicallyMoveThisDevice"/>&nbsp;<span class="req">*</span></div>
									<div class="radio radio_confirm" style="width:auto;float:left;">
									<div style="width:auto;float:left;"><form:radiobutton name="installAsset" path="installAssetFlag" value="Yes" 
									id="installAssetYes" onclick="showHideMoveType('installAssetYes');"/>	
									<spring:message code="requestInfo.option.yes"/>
									</div>
									<div style="width:auto;float:left;margin-left:5px;">
									<form:radiobutton name="installAsset" path="installAssetFlag" value="No"
									id="installAssetNo" onclick="showHideMoveType('installAssetNo');"/>
									<spring:message code="requestInfo.option.no"/>
									</div>
									</div>
									<div style="clear:both"></div>
									</div>
			                      </li>
			                      </div>
								</ul>
				</div></div>
				</c:when>
				
				<c:when test="${pageFlow eq 'fleetMgmtMove'}">
				
				<div class="portletBlock" style="margin-left:1%">
				<ul class="form  installCheck" style="width: 90%">
								  <div id="movetype_radio" class="">
								  <li class="move_type1"><%--Changed for CI Defect #7853 --%>
									<div style="">
			                        <%-- <label><spring:message code="requestInfo.cm.manageAsset.label.doYouRequireLexmarkToInstallThisDevice"/></label> --%>
									<div id="" class="move_type_confirmation" style="width:auto;;float:left;"><spring:message code="requestInfo.cm.manageAsset.label.doYouRequireLexmarkToPhysicallyMoveThisDevice"/>&nbsp;<span class="req">*</span></div>
									<div class="radio radio_confirm" style="width:auto;float:left;">
									<div style="width:auto;float:left;"><form:radiobutton name="installAsset" path="installAssetFlag" value="Yes" 
									id="installAssetYes" onclick="showHideFleetMoveType('installAssetYes');"/>	
									<spring:message code="requestInfo.option.yes"/>
									</div>
									<div style="width:auto;float:left;margin-left:5px;">
									<form:radiobutton name="installAsset" path="installAssetFlag" value="No"
									id="installAssetNo" onclick="showHideFleetMoveType('installAssetNo');" />
									<spring:message code="requestInfo.option.no"/>
									</div>
									</div>
									<div style="clear:both"></div>
									</div>
			                      </li>
			                      </div>
								</ul>
				</div>
				</c:when>
				<c:when test="${pageFlow eq 'Decommission'}">
				<h4><spring:message code="requestInfo.cm.manageAsset.heading.pickupInformation"/></h4>
				</c:when>
			</c:choose>
		
		
		<script type="text/javascript">
		<%--for address section end--%>
		var currentURL = window.location.href;
		jQuery(document).ready(function() {
			//Added for lbs 
			
			var pageFlow = "${pageFlow}";
			
			
			if(currentURL.indexOf('/fleet-management') == -1){
				jQuery('#diffAddressLink').attr("fleet-management", "false");
				jQuery('#diffAddressLink').show();
				}
			else{
				jQuery('#diffAddressLink').attr("fleet-management", "true");
				jQuery('#diffAddressLink').hide();
			}
			
		});
		</script>
	
		<div class="columnsTwo installSection">
			<div class="infoBox columnInner rounded shadow">
				<h4><a href="${addressListPopUpUrl}"
					title="<spring:message code="requestInfo.title.selectAddress"/>"
					id="diffAddressLink" class="lightboxLarge"
					onclick="return popupAddress('diffAddressLink');"><spring:message code="requestInfo.link.selectAnAddress"/></a>
					<c:choose>
	<c:when test="${pageFlow eq 'addone'}">
	<spring:message code="requestInfo.heading.installAddress"/>
	</c:when>
	<c:when test="${pageFlow eq 'Change'}">
	<spring:message code="requestInfo.heading.currentInstallAdd"/>
	</c:when>
	<c:when test="${pageFlow eq 'fleetMgmtChange'}">
	<spring:message code="requestInfo.heading.currentInstallAdd"/>
	</c:when>
	<c:when test="${pageFlow eq 'fleetMgmtMove'}">
	<spring:message code="requestInfo.heading.currentInstallAdd"/>
	</c:when>
	<c:when test="${pageFlow eq 'Decommission'}">
	<spring:message code="requestInfo.cm.manageAsset.heading.pickupAddress"/>
	</c:when>
	</c:choose>
					</h4>
				<ul class="roDisplay">
					<li>
					<div id="installAddressNameLbl"></div>
					<div id="installStoreFrontNameLbl"></div>
					<div id="installAddressLine1Lbl"></div>
					<div id="installAddressofficeNumberLbl"></div>
					<div id="installAddressLine2Lbl"></div>
					<div id="city_state_zip_popup_span">
					<div style="display: inline;" id="installAddressCityLbl"></div>
					<div style="display: inline;" id="installAddresscountyLbl"></div>
					<div style="display: inline;" id="installAddressStateLbl"></div>
					<div style="display: inline;" id="installAddressProvinceLbl"></div>
					<div style="display: inline;" id="installAddressRegionLB"></div>
					</div>
					<div id="installAddressPostCodeLbl"></div>
					<div id="installAddressCountryLbl"></div>
					</li>
				</ul>
				<%--For Non LBS Adresses --%>
				<div id="selectLocalAddress1" style="display: none;">
				<ul class="form division">
					<li><label for="building"><spring:message
						code="requestInfo.addressInfo.label.building" /> </label> <span><input
						id="physicalLocation1" class="w100"
						 maxlength="100" /></span></li>
					<li><label for="floor"><spring:message
						code="requestInfo.addressInfo.label.floor" /> </label> <span><input
						id="physicalLocation2" class="w100"
						 maxlength="100" /></span></li>
					<li><label for="office"><spring:message
						code="requestInfo.addressInfo.label.office" /> </label> <span><input
						id="physicalLocation3" class="w100"
						 maxlength="100" /></span></li>
				</ul>
				</div>
				<%--For LBS Adresses --%>
				<div id="selectLocalAddress" style="display: none;">
				
						<ul class="form division1">
					<li><label for="building"><spring:message
						code="requestInfo.addressInfo.label.building" /> *</label> <span><select
						id="bldng"  
						  ><option value=""><spring:message code='lbs.label.building'/></option></select></span></li>
					<li><label for="floor"><spring:message
						code="requestInfo.addressInfo.label.floor" /> *</label> <span><select
						id="flr"  ><option value=""><spring:message code='lbs.label.floor'/></option></select></span></li>
					<li><label for="zone"><spring:message code='lbs.label.zone'/>: </label> <span><select
						id="zone"  ><option value=""><spring:message code='lbs.label.zone'/></option></select></span></li>
					<li><label for="office"><spring:message
						code="requestInfo.addressInfo.label.office" /> </label> <span><input
						id="office"  value=""
						 /></span></li>
						 <li><span style="display: none;"><select
						id="campus"  
						></select></span></li>
						
					<li id="gridLi" style="display: none;">
						<a href="#" id="installedGridLink">View Grid</a>
						<div id="installedXYLblDiv">
							<label id="installedXYLbl">Grid X/Y : </label><label id="installedCoords"></label>
						</div>
					</li>
						 
						<%--For LBS Adresses display names--%>
				</ul>
						<span style="display: none;">
						<form:input path="assetDetail.installAddress.physicalLocation1" id="bldng1"/>
						<form:input path="assetDetail.installAddress.physicalLocation2" id="flr1"/>
						<form:input path="assetDetail.installAddress.zoneName" id="zone1"/>
						<form:input	id="campus1" path="assetDetail.installAddress.campusName"   />
						</span>			
						
					</div>
					
					
	</div>
					<!-- infoBox columnInner rounded shadow --></div>					
		
		
		<span style="display: none"> <form:input id="installAddressId"
						path="assetDetail.installAddress.addressId" /> <form:input
						id="installStoreFrontName" path="assetDetail.installAddress.storeFrontName" />
						<form:input
						id="installAddressName" path="assetDetail.installAddress.addressName" />
						 <form:input
						id="installAddressLine1" path="assetDetail.installAddress.addressLine1" /> <form:input
						id="installAddressLine2" path="assetDetail.installAddress.addressLine2" /> <form:input
						id="installAddressCity" path="assetDetail.installAddress.city" /> <form:input
						id="installAddressState" path="assetDetail.installAddress.state" /> <form:input
						id="installAddressProvince" path="assetDetail.installAddress.province" /> <form:input
						id="installAddressPostCode" path="assetDetail.installAddress.postalCode" /> <form:input
						id="installAddressCountry" path="assetDetail.installAddress.country" /> 
						
						<form:input	id="physicalLocation1h" path="assetDetail.installAddress.buildingId"  />
						<form:input	id="physicalLocation2h" path="assetDetail.installAddress.floorId"  />
						<form:input	id="physicalLocation3h" path="assetDetail.installAddress.physicalLocation3"  />
						<form:input	id="zoneh" path="assetDetail.installAddress.zoneId"   />
						<form:input	id="campush" path="assetDetail.installAddress.campusId"   />
						<!-- Below Fields for Cleansing address Don't change the
						input id's as it is related to ChangeMgmtConstant.ADDRESSCLEANSEFIELDS_OUTPUT 
						--> 
						<form:input id="installAddresscounty"
						path="assetDetail.installAddress.county" /> <form:input
						id="installAddressofficeNumber" path="assetDetail.installAddress.officeNumber" />
						<form:input id="installAddressstateCode" path="assetDetail.installAddress.stateCode" />
						<form:input id="installAddressstateFullName"
						path="assetDetail.installAddress.stateFullName" /> <form:input
						id="installAddressdistrict" path="assetDetail.installAddress.district" /> <form:input
						id="installAddressregion" path="assetDetail.installAddress.region" /> <form:input
						id="installAddresslatitude" path="assetDetail.installAddress.latitude" /> <form:input
						id="installAddresslongitude" path="assetDetail.installAddress.longitude" /> <form:input
						id="installAddresscountryISOCode" path="assetDetail.installAddress.countryISOCode" />
						<form:input id="installAddresssavedErrorMessage"
						path="assetDetail.installAddress.savedErrorMessage" /> <form:input
						id="installAddressisAddressCleansed"
						path="assetDetail.installAddress.isAddressCleansed" /> <form:input
						id="installLBSAddressFlag" path="assetDetail.installAddress.lbsAddressFlag" /> 
						
						<form:input
						id="installcoordinatesXPreDebriefRFV" path="assetDetail.installAddress.coordinatesXPreDebriefRFV" />
						<form:input
						id="installcoordinatesYPreDebriefRFV" path="assetDetail.installAddress.coordinatesYPreDebriefRFV" /> 
		
						
						<!-- LOD fields added for LBS 1.5- LOD logic -->
						<form:input	id="installLevelOfDetails" path="assetDetail.installAddress.levelOfDetails"  />
						<form:input	id="installFloorLevelOfDetails" path="assetDetail.installAddress.floorLevelOfDetails"  /> 
		</span>
	
	<c:choose>
	
	<c:when test="${pageFlow eq 'Change'}">

			<!-- Move to Address -->
			<div class="columnsTwo moveToAddressSection" style="display: none;" id="hideMoveToAddress">
			<div class="infoBox columnInner rounded shadow">
				<h4><a href="${addressListPopUpUrl}"
					title="<spring:message code="requestInfo.title.selectAddress"/>"
					id="moveToAddressLink" class="lightboxLarge"
					onclick="return popupAddress('moveToAddressLink');"><spring:message code="requestInfo.link.selectAnAddress"/></a> 
					<spring:message code="requestInfo.manageAsset.heading.moveToAddr"/>
					</h4>
				<ul class="roDisplay">
					<li><div id="moveToAddressNameLbl"></div>
					<div id="moveToStoreFrontNameLbl"></div>
					<div id="moveToAddressLine1Lbl"></div>
					<div id="moveToAddressofficeNumberLbl"></div>
					<div id="moveToAddressLine2Lbl"></div>
					<div id="city_state_zip_popup_span_moveTo">
					<div style="display: inline;" id="moveToAddressCityLbl"></div>
					<div style="display: inline;" id="moveToAddresscountyLbl"></div>
					<div style="display: inline;" id="moveToAddressStateLbl"></div>
					<div style="display: inline;" id="moveToAddressProvinceLbl"></div>
					<div style="display: inline;" id="moveToAddressRegionLB"></div>
					</div>
					<div id="moveToAddressPostCodeLbl"></div>
					<div id="moveToAddressCountryLbl"></div>
					</li>
					</ul>
					<%--For non LBS Adresses --%>
					<div id="selectMoveToAddress1">
					<ul class="form division">
					<li><label for="building"><spring:message
						code="requestInfo.addressInfo.label.building" /> </label> <span><input
						id="moveTophysicalLocation1" class="w100"
						 maxlength="100" /></span></li>
					<li><label for="floor"><spring:message
						code="requestInfo.addressInfo.label.floor" /> </label> <span><input
						id="moveTophysicalLocation2" class="w100"
						 maxlength="100" /></span></li>
					<li><label for="office"><spring:message
						code="requestInfo.addressInfo.label.office" /> </label> <span><input
						id="moveTophysicalLocation3" class="w100"
						 maxlength="100" /></span></li>
						</ul>
						</div>
						<%--For LBS Adresses --%>
						<div id="selectMoveToAddress" style="display: none;">
					<ul class="form division1">
					<li><label for="building"><spring:message
						code="requestInfo.addressInfo.label.building" /> *</label> <span><select
						id="bldngm" 
						></select></span></li>
					<li><label for="floor"><spring:message
						code="requestInfo.addressInfo.label.floor" /> *</label> <span><select
						id="flrm" 
						></select></span></li>
					<li><label for="zone"><spring:message code='lbs.label.zone'/>: </label> <span><select
						id="zonem"  
						></select></span></li>
					<li><label for="office"><spring:message
						code="requestInfo.addressInfo.label.office" /> </label> <span><input
						id="moveTophysicalLocation3m" 
						 maxlength="100" /></span></li>
						  <li><span style="display: none;"><select
						id="campusm"  
						></select></span></li>
						
						<li id="gridLi" style="display: none;">
							<a href="#" id="moveToGridLink">View Grid</a>
							<div id="moveToXYLblDiv">
								<label id="moveToXYLbl">Grid X/Y : </label><label id="moveToCoords"></label>
							</div>
						</li>
						
						</ul>
						<%--For LBS Adresses display names--%>
						<span style="display: none;">
						<form:input path="assetDetail.moveToAddress.physicalLocation1" id="bldngm1"/>
						<form:input path="assetDetail.moveToAddress.physicalLocation2" id="flrm1"/>
						<form:input path="assetDetail.moveToAddress.zoneName" id="zonem1"/>
						<form:input	id="campusm1" path="assetDetail.moveToAddress.campusName"   />
						</span>
						
						</div> 
				</div>
				
			
					<!-- infoBox columnInner rounded shadow --></div>															
					
					<span style="display: none"> <form:input id="moveToAddressId"
						path="assetDetail.moveToAddress.addressId" /> <form:input
						id="moveToStoreFrontName" path="assetDetail.moveToAddress.storeFrontName" />
						 <form:input
						id="moveToAddressName" path="assetDetail.moveToAddress.addressName" /> <form:input
						id="moveToAddressLine1" path="assetDetail.moveToAddress.addressLine1" /> <form:input
						id="moveToAddressLine2" path="assetDetail.moveToAddress.addressLine2" /> <form:input
						id="moveToAddressCity" path="assetDetail.moveToAddress.city" /> <form:input
						id="moveToAddressState" path="assetDetail.moveToAddress.state" /> <form:input
						id="moveToAddressProvince" path="assetDetail.moveToAddress.province" /> <form:input
						id="moveToAddressPostCode" path="assetDetail.moveToAddress.postalCode" /> <form:input
						id="moveToAddressCountry" path="assetDetail.moveToAddress.country" /> 
						
						<form:input	id="moveTophysicalLocation1h" path="assetDetail.moveToAddress.buildingId"  />
						<form:input	id="moveTophysicalLocation2h" path="assetDetail.moveToAddress.floorId"  />
						<form:input	id="moveTophysicalLocation3h" path="assetDetail.moveToAddress.physicalLocation3"  />
						<form:input	id="zonemh" path="assetDetail.moveToAddress.zoneId"   />
						<form:input	id="campusmh" path="assetDetail.moveToAddress.campusId"   />
						
						<!-- Below Fields for Cleansing address Don't change the
						input id's as it is related to ChangeMgmtConstant.ADDRESSCLEANSEFIELDS_OUTPUT 
						--> 
						<form:input id="moveToAddresscounty"
						path="assetDetail.moveToAddress.county" /> <form:input
						id="moveToAddressofficeNumber" path="assetDetail.moveToAddress.officeNumber" />
						<form:input id="moveToAddressstateCode" path="assetDetail.moveToAddress.stateCode" />
						<form:input id="moveToAddressstateFullName"
						path="assetDetail.moveToAddress.stateFullName" /> <form:input
						id="moveToAddressdistrict" path="assetDetail.moveToAddress.district" /> <form:input
						id="moveToAddressregion" path="assetDetail.moveToAddress.region" /> <form:input
						id="moveToAddresslatitude" path="assetDetail.moveToAddress.latitude" /> <form:input
						id="moveToAddresslongitude" path="assetDetail.moveToAddress.longitude" /> <form:input
						id="moveToAddresscountryISOCode" path="assetDetail.moveToAddress.countryISOCode" />
						<form:input id="moveToAddresssavedErrorMessage"
						path="assetDetail.moveToAddress.savedErrorMessage" /> <form:input
						id="moveToAddressisAddressCleansed"
						path="assetDetail.moveToAddress.isAddressCleansed" /> <form:input
						id="moveToAddressLBSAddressFlag" path="assetDetail.moveToAddress.lbsAddressFlag" /> 
						
						<form:input
						id="moveToAddresscoordinatesXPreDebriefRFV" path="assetDetail.moveToAddress.coordinatesXPreDebriefRFV" />
						<form:input
						id="moveToAddresscoordinatesYPreDebriefRFV" path="assetDetail.moveToAddress.coordinatesYPreDebriefRFV" /> 
		
						<form:input	id="moveToAddressLevelOfDetails" path="assetDetail.moveToAddress.levelOfDetails"  />
						<form:input	id="moveToFloorLevelOfDetails" path="assetDetail.moveToAddress.floorLevelOfDetails"  />
					</span>
					
			
		
	</c:when>
	<c:when test="${pageFlow eq 'fleetMgmtMove'}">

			<!-- Move to Address -->
			<div class="columnsTwo moveToAddressSection" id="hideMoveToAddress">
			<div class="infoBox columnInner rounded shadow">
				<h4><a href="${addressListPopUpUrl}"
					title="<spring:message code="requestInfo.title.selectAddress"/>"
					id="moveToAddressLink" class="lightboxLarge" style="display:none;"
					onclick="return popupAddress('moveToAddressLink');"><spring:message code="requestInfo.link.selectAnAddress"/></a>
					<spring:message code="requestInfo.manageAsset.heading.moveToAddr"/>
					</h4>
				<ul class="roDisplay">
					<li>
					<div id="moveToAddressNameLbl"></div>
					<div id="moveToStoreFrontNameLbl"></div>
					<div id="moveToAddressLine1Lbl"></div>
					<div id="moveToAddressofficeNumberLbl"></div>
					<div id="moveToAddressLine2Lbl"></div>
					<div id="city_state_zip_popup_span_moveTo">
					<div style="display: inline;" id="moveToAddressCityLbl"></div>
					<div style="display: inline;" id="moveToAddresscountyLbl"></div>
					<div style="display: inline;" id="moveToAddressStateLbl"></div>
					<div style="display: inline;" id="moveToAddressProvinceLbl"></div>
					<div style="display: inline;" id="moveToAddressRegionLB"></div>
					</div>
					<div id="moveToAddressPostCodeLbl"></div>
					<div id="moveToAddressCountryLbl"></div>
					</li>
					</ul>
				<%--For non LBS Adresses --%>
					<div id="selectMoveToAddress1" style="display: none;">
					<ul class="form division">
					<li><label for="building"><spring:message
						code="requestInfo.addressInfo.label.building" /> </label> <span><input
						id="moveTophysicalLocation1" class="w100"
						  maxlength="100" /></span></li>
					<li><label for="floor"><spring:message
						code="requestInfo.addressInfo.label.floor" /> </label> <span><input
						id="moveTophysicalLocation2" class="w100"
						  maxlength="100" /></span></li>
					
					<li><label for="zone"><spring:message code='lbs.label.zone'/>: </label> <span><input
						id="zonemove"  value="" class="w100" type="text"
						 /></span></li>
						
					<li><label for="office"><spring:message
						code="requestInfo.addressInfo.label.office" /> </label> <span><input
						id="moveTophysicalLocation3" class="w100" type="text"
						 maxlength="100" /></span></li>
						</ul>
						</div>
												
						<%--For LBS Adresses --%>
						<div id="selectMoveToAddress" style="display: none;">
					<ul class="form division1">
					<li><label for="building"><spring:message
						code="requestInfo.addressInfo.label.building" /> *</label> <span><select
						id="bldngm" 
						></select></span></li>
					<li><label for="floor"><spring:message
						code="requestInfo.addressInfo.label.floor" /> *</label> <span><select
						id="flrm" 
						></select></span></li>
					<li><label for="zone"><spring:message code='lbs.label.zone'/>: </label> <span><select
						id="zonem"  
						></select></span></li>
					<li><label for="office"><spring:message
						code="requestInfo.addressInfo.label.office" /> </label> <span><input
						id="moveTophysicalLocation3m" 
						 maxlength="100" /></span></li>
						 <li><span style="display: none;"><select
						id="campusm"  
						></select></span></li>
						
					<li id="gridLi" style="display: none;">
						<a href="#" id="moveToGridLink">View Grid</a>
						<div id="moveToXYLblDiv">
							<label id="moveToXYLbl">Grid X/Y : </label><label id="moveToCoords"></label>
						</div>
					</li>
					</ul>
						
						
						
						<span style="display: none;">
						<form:input path="assetDetail.moveToAddress.physicalLocation1" id="bldngm1"/>
						<form:input path="assetDetail.moveToAddress.physicalLocation2" id="flrm1"/>
						<form:input path="assetDetail.moveToAddress.zoneName" id="zonem1"/>
						<form:input	id="campusm1" path="assetDetail.moveToAddress.campusName"   />
						</span>
						
						</div>
				
			</div>
					<!-- infoBox columnInner rounded shadow --></div>															
					
					<span style="display: none"> <form:input id="moveToAddressId"
						path="assetDetail.moveToAddress.addressId" />
						<form:input
						id="moveToAddressName" path="assetDetail.moveToAddress.addressName" /> <form:input
						id="moveToStoreFrontName" path="assetDetail.moveToAddress.storeFrontName" /> <form:input
						id="moveToAddressLine1" path="assetDetail.moveToAddress.addressLine1" /> <form:input
						id="moveToAddressLine2" path="assetDetail.moveToAddress.addressLine2" /> <form:input
						id="moveToAddressCity" path="assetDetail.moveToAddress.city" /> <form:input
						id="moveToAddressState" path="assetDetail.moveToAddress.state" /> <form:input
						id="moveToAddressProvince" path="assetDetail.moveToAddress.province" /> <form:input
						id="moveToAddressPostCode" path="assetDetail.moveToAddress.postalCode" /> <form:input
						id="moveToAddressCountry" path="assetDetail.moveToAddress.country" /> 
						
						<form:input	id="moveTophysicalLocation1h" path="assetDetail.moveToAddress.buildingId"  />
						<form:input	id="moveTophysicalLocation2h" path="assetDetail.moveToAddress.floorId"  />
						<form:input	id="moveTophysicalLocation3h" path="assetDetail.moveToAddress.physicalLocation3"  />
						<form:input	id="zonemh" path="assetDetail.moveToAddress.zoneId"   />
						<form:input	id="campusmh" path="assetDetail.moveToAddress.campusId"   />
						
						<!-- Below Fields for Cleansing address Don't change the
						input id's as it is related to ChangeMgmtConstant.ADDRESSCLEANSEFIELDS_OUTPUT 
						--> 
						<form:input id="moveToAddresscounty"
						path="assetDetail.moveToAddress.county" /> <form:input
						id="moveToAddressofficeNumber" path="assetDetail.moveToAddress.officeNumber" />
						<form:input id="moveToAddressstateCode" path="assetDetail.moveToAddress.stateCode" />
						<form:input id="moveToAddressstateFullName"
						path="assetDetail.moveToAddress.stateFullName" /> <form:input
						id="moveToAddressdistrict" path="assetDetail.moveToAddress.district" /> <form:input
						id="moveToAddressregion" path="assetDetail.moveToAddress.region" /> <form:input
						id="moveToAddresslatitude" path="assetDetail.moveToAddress.latitude" /> <form:input
						id="moveToAddresslongitude" path="assetDetail.moveToAddress.longitude" /> <form:input
						id="moveToAddresscountryISOCode" path="assetDetail.moveToAddress.countryISOCode" />
						<form:input id="moveToAddresssavedErrorMessage"
						path="assetDetail.moveToAddress.savedErrorMessage" /> <form:input
						id="moveToAddressisAddressCleansed"
						path="assetDetail.moveToAddress.isAddressCleansed" /> <form:input
						id="moveToAddressLBSAddressFlag" path="assetDetail.moveToAddress.lbsAddressFlag" /> 
						
						<form:input
						id="moveToAddresscoordinatesXPreDebriefRFV" path="assetDetail.moveToAddress.coordinatesXPreDebriefRFV" />
						<form:input
						id="moveToAddresscoordinatesYPreDebriefRFV" path="assetDetail.moveToAddress.coordinatesYPreDebriefRFV" /> 
		
						<form:input	id="moveToAddressLevelOfDetails" path="assetDetail.moveToAddress.levelOfDetails"  />
						<form:input	id="moveToFloorLevelOfDetails" path="assetDetail.moveToAddress.floorLevelOfDetails"  />
					</span>
					
			
		
	</c:when>
	
	</c:choose>
		</div>
	</div>

<script type="text/javascript">
//For LBS dropdowns install
var pageFlow = "${pageFlow}";

function urlParams(){
	
		this.htmlInputIds=["bldng","zone","flr","campus"];
		this.defaultMessages=["<spring:message code='lbs.label.building'/>","<spring:message code='lbs.label.zone'/>","<spring:message code='lbs.label.floor'/>",""];//replace with spring messages
		this.paramNames=["ctry","state","cty","bldng","","flr","extra"];
		this.paramNames1=["bldng","","flr"];
		
	this.setDefaultHTMl=function(){
		for(var i=0;i<this.htmlInputIds;i++){
			$('#'+this.htmlInputIds[i]).html('<option value="">'+this.defaultMessages[i]+'</option>');
		}
	};
	this.setDefaultHTMlAt=function(id){
		
		var index=$.inArray(id,this.htmlInputIds);
		$('#'+id).html('<option value="">'+this.defaultMessages[index]+'</option>');
	};
	this.clearHtmlAfter=function(htmlId){
		
		var index=$.inArray(htmlId,this.htmlInputIds);
		if(index!=-1){
			for(var i=(index+1);i<this.htmlInputIds.length;i++){
				$('#'+this.htmlInputIds[i]).html('<option value="">'+this.defaultMessages[i]+'</option>');
			}
		}
	};
	this.setDropParamsToObj=function(){
		for(var i=0;i<this.paramNames1.length;i++){
			if(this.paramNames1[i]!=""){
				this[this.paramNames1[i]]=$('#'+this.htmlInputIds[i]).val();				
			}
			
		}
	};
	this.disableAll=function(){
		for(var i=0;i<this.htmlInputIds.length;i++){
			$('#'+this.htmlInputIds[i]).attr('disabled','disabled');
		}
	};
	this.enableAll=function(){
		for(var i=0;i<this.htmlInputIds.length;i++){
			$('#'+this.htmlInputIds[i]).removeAttr('disabled');
		}
	};
}
var urlParamsObj=new urlParams();
//For LBS dropdowns move
function urlParams1(){
		this.htmlInputIds=["bldngm","zonem","flrm","campusm"];
		this.defaultMessages=["<spring:message code='lbs.label.building'/>","<spring:message code='lbs.label.zone'/>","<spring:message code='lbs.label.floor'/>",""];//replace with spring messages
		this.paramNames=["ctry","state","cty","bldng","","flr","extra"];
		this.paramNames1=["bldngm","","flrm"];
	
	this.setDefaultHTMl=function(){
		for(var i=0;i<this.htmlInputIds;i++){
			$('#'+this.htmlInputIds[i]).html('<option value="">'+this.defaultMessages[i]+'</option>');
		}
	};
	this.setDefaultHTMlAt=function(id){
		
		var index=$.inArray(id,this.htmlInputIds);
		$('#'+id).html('<option value="">'+this.defaultMessages[index]+'</option>');
	};
	this.clearHtmlAfter=function(htmlId){
		
		var index=$.inArray(htmlId,this.htmlInputIds);
		if(index!=-1){
			for(var i=(index+1);i<this.htmlInputIds.length;i++){
				$('#'+this.htmlInputIds[i]).html('<option value="">'+this.defaultMessages[i]+'</option>');
			}
		}
	};
	this.setDropParamsToObj=function(){
		for(var i=0;i<this.paramNames1.length;i++){
			if(this.paramNames1[i]!=""){
				this[this.paramNames1[i]]=$('#'+this.htmlInputIds[i]).val();				
			}
			
		}
	};
	this.disableAll=function(){
		for(var i=0;i<this.htmlInputIds.length;i++){
			$('#'+this.htmlInputIds[i]).attr('disabled','disabled');
		}
	};
	this.enableAll=function(){
		for(var i=0;i<this.htmlInputIds.length;i++){
			$('#'+this.htmlInputIds[i]).removeAttr('disabled');
		}
	};
}

var urlParamsObj1=new urlParams1();


$('#bldng').change(function(){
	urlParamsObj.setDefaultHTMlAt('flr');
	urlParamsObj.setDefaultHTMlAt('campus');
	
	jQuery('#bldng1').val(jQuery("#bldng option:selected").text());
	jQuery('#physicalLocation1h').val(jQuery("#bldng option:selected").val());
	
	if($(this).val()==""){
		if($.trim($("#installLevelOfDetails").val()).toLowerCase() === "mix - see floor")
		{
			$(".installSection").find("#gridLi").hide();
			clearGridValues("installed");
		}
		return;
	}
	urlParamsObj.setDropParamsToObj();	
	$('#flr').attr('disabled',true);
	$('#btnContinue').attr('disabled',true);
	getSite("buildingchange");
	getFloor("buildingchange");
	
});
$('#bldngm').change(function(){
	

	urlParamsObj1.setDefaultHTMlAt('flrm');
	urlParamsObj1.setDefaultHTMlAt('campusm');
	
		jQuery('#bldngm1').val(jQuery("#bldngm option:selected").text());
		jQuery('#moveTophysicalLocation1h').val(jQuery("#bldngm option:selected").val());
		
	
	if($(this).val()==""){
		var moveToAddressLevelOfDetails = $.trim($("#moveToAddressLevelOfDetails").val());
		if(moveToAddressLevelOfDetails.toLowerCase() === "mix - see floor")
		{
			$(".moveToAddressSection").find("#gridLi").hide();
			clearGridValues("moveTo");
		}
		return;
	}
	urlParamsObj1.setDropParamsToObj();	
	$('#flrm').attr('disabled',true);
	$('#btnContinue').attr('disabled',true);
	getSitem("buildingchange");
	getFloorm("buildingchange");
	
});
function getFloorm(p){
		var ctry=$('#moveToAddressCountryLbl').text();
		urlParamsObj1["ctry"]=ctry;
		
		var state=$('#moveToAddressStateLbl').text().slice(2);
		
		var county=$('#moveToAddresscountyLbl').text().slice(2);
		
		var province=$('#moveToAddressProvinceLbl').text().slice(2);
		
		var district=$('#moveToAddressRegionLB').text().slice(2);
		
		if(state!=""){
		urlParamsObj1["state"]=state+"^s";
		}
		else if(county!=""){
			urlParamsObj1["state"]=county+"^c";	
		}
else if(province!=""){
	urlParamsObj1["state"]=province+"^p";	
		}
else if(district!=""){
	urlParamsObj1["state"]=district+"^d";
}
		
		var cty=$('#moveToAddressCityLbl').text();
		urlParamsObj1["cty"]=cty;
		var aid=$('#moveToAddressId').val();
		
		var b=jQuery("#bldngm option:selected").val();
		urlParamsObj1["bldng"]=b;
		urlParamsObj1["extra"]="blank"
		var url=appendURLPrams1("${floorURL}",urlParamsObj1);

		var moveToAddressLevelOfDetails = $.trim($("#moveToAddressLevelOfDetails").val());
		if(moveToAddressLevelOfDetails.toLowerCase() === "mix - see floor")
		{
			$(".moveToAddressSection").find("#gridLi").hide();
			if(p == "buildingchange")
				clearGridValues("moveTo");
		}
		$.get(url,function(response){
			$('#flrm').attr('disabled',false);
			$('#btnContinue').attr('disabled',false);	
			$('#flrm').append(response);

			if(p == "buildingselect" || p == "buildingchange")
				selectFloorMove(p);
			else
				lbsFloorm(p);
		});	
	
}

function getSitem(p){
	var ctry=$('#moveToAddressCountryLbl').text();
	urlParamsObj1["ctry"]=ctry;
	
	var state=$('#moveToAddressStateLbl').text().slice(2);
	
	var county=$('#moveToAddresscountyLbl').text().slice(2);
	
	var province=$('#moveToAddressProvinceLbl').text().slice(2);
	
	var district=$('#moveToAddressRegionLB').text().slice(2);
	
	if(state!=""){
	urlParamsObj1["state"]=state+"^s";
	}
	else if(county!=""){
		urlParamsObj1["state"]=county+"^c";	
	}
else if(province!=""){
urlParamsObj1["state"]=province+"^p";	
	}
else if(district!=""){
urlParamsObj1["state"]=district+"^d";
}
	
	var cty=$('#moveToAddressCityLbl').text();
	urlParamsObj1["cty"]=cty;
	var aid=$('#moveToAddressId').val();
	
	var b=jQuery("#bldngm option:selected").val();
	urlParamsObj1["bldng"]=b;
	urlParamsObj1["extra"]="site";
	
	var url=appendURLPrams1("${siteURL}",urlParamsObj1);
	
	$.get(url,function(response){
			
		
		$('#campusm').append(response);

		if(p == "buildingselect" || p == "buildingchange")
			selectSiteMove(p);
		else
			lbsSitem(p);
		
	});	

}
function getFloor(p){
	
		var ctry=$('#installAddressCountryLbl').text();
		urlParamsObj["ctry"]=ctry;
		
		var state=$('#installAddressStateLbl').text().slice(2);
		
		var county=$('#installAddresscountyLbl').text().slice(2);
		
		var province=$('#installAddressProvinceLbl').text().slice(2);
		
		var district=$('#installAddressRegionLB').text().slice(2);
		
		if(state!=""){
		urlParamsObj["state"]=state+"^s";
		}
		else if(county!=""){
			urlParamsObj["state"]=county+"^c";	
		}
else if(province!=""){
	urlParamsObj["state"]=province+"^p";	
		}
else if(district!=""){
	urlParamsObj["state"]=district+"^d";
}
		
		
		
		var cty=$('#installAddressCityLbl').text();
		urlParamsObj["cty"]=cty;
		
		var bId=jQuery("#bldng option:selected").val();
		urlParamsObj["bldng"]=bId;
		urlParamsObj["extra"]="blank"
		var url=appendURLPrams("${floorURL}",urlParamsObj);

		if($.trim($("#installLevelOfDetails").val()).toLowerCase() === "mix - see floor")
		{
			$(".installSection").find("#gridLi").hide();
			if(p == "buildingchange")
				clearGridValues("installed");
		}
		$.get(url,function(response){
			$('#flr').attr('disabled',false);
			$('#btnContinue').attr('disabled',false);
			$('#flr').append(response);
			
			lbsFloor(p);
		});	
}

function getSite(p){
	
	var ctry=$('#installAddressCountryLbl').text();
	urlParamsObj["ctry"]=ctry;
	
	var state=$('#installAddressStateLbl').text().slice(2);
	
	var county=$('#installAddresscountyLbl').text().slice(2);
	
	var province=$('#installAddressProvinceLbl').text().slice(2);
	
	var district=$('#installAddressRegionLB').text().slice(2);
	
	if(state!=""){
	urlParamsObj["state"]=state+"^s";
	}
	else if(county!=""){
		urlParamsObj["state"]=county+"^c";	
	}
else if(province!=""){
urlParamsObj["state"]=province+"^p";	
	}
else if(district!=""){
urlParamsObj["state"]=district+"^d";
}
	
	
	
	var cty=$('#installAddressCityLbl').text();
	urlParamsObj["cty"]=cty;
	
	var bId=jQuery("#bldng option:selected").val();
	urlParamsObj["bldng"]=bId;
	urlParamsObj["extra"]="site";
	
	var url=appendURLPrams("${siteURL}",urlParamsObj);

	$.get(url,function(response){
		
		
		$('#campus').append(response);
		lbsSite(p);
		
	});	

}

$('#flr').change(function(){
	
		jQuery('#flr1').val(jQuery("#flr option:selected").text());
		jQuery('#physicalLocation2h').val(jQuery("#flr option:selected").val());
		
		$("#campus option").each(function() {
            
       		if( $(this).prop('text') != "" ) { 
        		$(this).attr('selected','selected');
        	}
    	});
		
		jQuery('#campus1').val(jQuery("#campus option:selected").text());
		jQuery('#campush').val(jQuery("#campus option:selected").val());

		if($("#installLevelOfDetails").val().toLowerCase() === "mix - see floor")
		{ 
			$(".installSection").find("#gridLi").hide();
			clearGridValues("installed");
			
			if($("#flr option:selected").attr("lod") != undefined &&
				$.trim($("#flr option:selected").attr("lod").toLowerCase()).indexOf("grid") > -1)
			{
				if(curURL.indexOf('/fleet-management') != -1)
				{
					$(".installSection").find("#gridLi").hide();

					if(pageFlow.toLowerCase() === "addone")
					{
						$(".installSection").find("#gridLi").show();
					}
					else
					{
						$(".installSection").find("#installedGridLink").show();
					}
				}
				else
					$(".installSection").find("#gridLi").show();
			}
		}
});
$('#flrm').change(function(){
	
		jQuery('#flrm1').val(jQuery("#flrm option:selected").text());
		jQuery('#moveTophysicalLocation2h').val(jQuery("#flrm option:selected").val());
		
		$("#campusm option").each(function() {
            
        	if( $(this).prop('text') != "" ) { 
        		$(this).attr('selected','selected');
        	}
    	});
		
		jQuery('#campusm1').val(jQuery("#campusm option:selected").text());
		jQuery('#campusmh').val(jQuery("#campusm option:selected").val());

		if($("#moveToAddressLevelOfDetails").val().toLowerCase() === "mix - see floor")
		{
			$(".moveToAddressSection").find("#gridLi").hide();
			clearGridValues("moveTo");
			
			if($("#flrm option:selected").attr("lod") != undefined &&
				$.trim($("#flrm option:selected").attr("lod").toLowerCase()).indexOf("grid") > -1)
			{
				$(".moveToAddressSection").find("#gridLi").show();
			}
		}
});
function getZone(p){
	var url=appendURLPrams("${zoneURL}",urlParamsObj);
	
		$.get(url,function(response){		
			$('#zone').append(response);
		
			
			if(p!=null){
				
				$('#zone').val(p);
			}
		});	
	
}
$('#zone').change(function(){
	
		jQuery('#zone1').val(jQuery("#zone option:selected").text());
		jQuery('#zoneh').val(jQuery("#zone option:selected").val());
		
});
$('#zonem').change(function(){
	
	
		jQuery('#zonem1').val(jQuery("#zonem option:selected").text());
		jQuery('#zonemh').val(jQuery("#zonem option:selected").val());
		
});
function appendURLPrams(url,paramsObj){
	var urlT=url;
	for(var i=0;i<paramsObj.paramNames.length;i++){
		if(paramsObj[paramsObj.paramNames[i]]!=null && paramsObj[paramsObj.paramNames[i]]!=""){
			if(paramsObj.paramNames[i] != "state"){
			urlT+="&"+paramsObj.paramNames[i]+"="+encodeURI(paramsObj[paramsObj.paramNames[i]]);
			}
		}
	}
	
	return urlT;
}
//move
function appendURLPrams1(url,paramsObj1){
	var urlT=url;
	for(var i=0;i<paramsObj1.paramNames.length;i++){
		if(paramsObj1[paramsObj1.paramNames[i]]!=null && paramsObj1[paramsObj1.paramNames[i]]!=""){
			if(paramsObj1.paramNames[i] != "state"){
			urlT+="&"+paramsObj1.paramNames[i]+"="+encodeURI(paramsObj1[paramsObj1.paramNames[i]]);
		}
		}
	}
	
	return urlT;
}

	function loadCoutrySateCitySiteBuilding(addressId){
		

		urlParamsObj.clearHtmlAfter('bldng');
		
		urlParamsObj.setDefaultHTMlAt('bldng');
		
	
	urlParamsObj=new urlParams();
	var url=appendURLPrams("${getAllLocationURL}&frCal=true&aid="+addressId,urlParamsObj);
	urlParamsObj.disableAll();
	
		$.getJSON(url,function(response){
			urlParamsObj.enableAll();	
			
			$('#bldng').append(response.building);
			$('#zone').append(response.zone);
			lbsInstall();
			lbsZone();
		});
	
}
//move
	function loadCoutrySateCitySiteBuildingm(addressId){
		urlParamsObj1.clearHtmlAfter('bldngm');
		
		urlParamsObj1.setDefaultHTMlAt('bldngm');
		
		urlParamsObj1=new urlParams1();
		var url=appendURLPrams1("${getAllLocationURL}&frCal=true&aid="+addressId,urlParamsObj1);
		urlParamsObj1.disableAll();
		$.getJSON(url,function(response){
			urlParamsObj1.enableAll();	
			
			$('#bldngm').append(response.building);
			$('#zonem').append(response.zone);
			lbsInstallm();
			lbsZonem();
			
		});
		
	}
	
	
var installAddressFlag=false;
var moveAddressFlag=false;
var linkId;
	//for common section
	var lat=document.getElementById("installAddresslatitude").value;
	//alert("lat"+lat);
	var lon=document.getElementById("installAddresslongitude").value;
	//alert("lon"+lon);
	
	coordinates($('#moveToAddresscoordinatesXPreDebriefRFV').val(),$('#moveToAddresscoordinatesYPreDebriefRFV').val(),"moveTo");
	coordinates($('#installcoordinatesXPreDebriefRFV').val(),$('#installcoordinatesYPreDebriefRFV').val(),"installed");
	
	jQuery("#installStoreFrontNameLbl").html(document.getElementById("installStoreFrontName").value);
	jQuery("#installAddressNameLbl").html(document.getElementById("installAddressName").value);
	document.getElementById("installAddressLine1Lbl").innerHTML=document.getElementById("installAddressLine1").value;
	//For LBS dropdowns
	var curURL = window.location.href;
	if(curURL.indexOf('/fleet-management') != -1)
	{
		if(document.getElementById("installLBSAddressFlag").value !="" && document.getElementById("installLBSAddressFlag").value !=null && document.getElementById("installLBSAddressFlag").value =="true"){

			//alert(document.getElementById("installLBSAddressFlag").value);
			/*jQuery("#selectLocalAddress1").hide();
			jQuery("#selectLocalAddress").show();
			var addressId=document.getElementById("installAddressId").value;
			
			loadCoutrySateCitySiteBuilding(addressId);*/
			showBuildingFloorZone("install");
		}
	
		if(document.getElementById("installLBSAddressFlag").value =='' || document.getElementById("installLBSAddressFlag").value ==null || document.getElementById("installLBSAddressFlag").value =="false"){
			document.getElementById("installLBSAddressFlag").value="false";
			
			jQuery("#selectLocalAddress1").show();
			jQuery("#selectLocalAddress").hide();
			lbsInstall();
			lbsFloor();
			lbsZone();
			lbsSite();
		}
	}
	else
	{
		/* Addded for LBS 1.5- LOD Checking for Installed Address */
		showBuildingFloorZone("install");
	}
	
	if(document.getElementById("installAddressLine2").value !="" && document.getElementById("installAddressLine2").value !=null){
		jQuery("#installAddressLine2Lbl").html(document.getElementById("installAddressLine2").value + ',');
		jQuery("#installAddressLine2Lbl").show();
		installAddressFlag=true;
		}
	if(document.getElementById("installAddressCity").value!='' && document.getElementById("installAddressCity").value != null){
		jQuery("#installAddressCityLbl").html(document.getElementById("installAddressCity").value);
		installAddressFlag=true;
		}
	if(document.getElementById("installAddresscounty").value!='' && document.getElementById("installAddresscounty").value != null){
		jQuery("#installAddresscountyLbl").html(',&nbsp;'+document.getElementById("installAddresscounty").value);
		jQuery("#installAddresscountyLbl").show();
		installAddressFlag=true;
	}
	if(document.getElementById("installAddressState").value!='' && document.getElementById("installAddressState").value != null){
		jQuery("#installAddressStateLbl").html(',&nbsp;'+document.getElementById("installAddressState").value);
		installAddressFlag=true;
	}
	if((document.getElementById("installAddressProvince").value!='' && document.getElementById("installAddressProvince").value!=' ') && document.getElementById("installAddressProvince").value != null){
		jQuery("#installAddressProvinceLbl").html(',&nbsp;'+document.getElementById("installAddressProvince").value);
		jQuery("#installAddressProvinceLbl").show();
		installAddressFlag=true;
	}
	// region changed to district for MPS 2.1
	if((document.getElementById("installAddressdistrict").value!=' ' && document.getElementById("installAddressdistrict").value!='') && document.getElementById("installAddressdistrict").value != null){
		jQuery("#installAddressRegionLB").html(',&nbsp;'+document.getElementById("installAddressdistrict").value);
		jQuery("#installAddressRegionLB").show();
		installAddressFlag=true;
	}
	jQuery("#installAddressPostCodeLbl").html(document.getElementById("installAddressPostCode").value);
	jQuery("#installAddressCountryLbl").html(document.getElementById("installAddressCountry").value);
	if((document.getElementById("installAddressProvince").value=='' || document.getElementById("installAddressProvince").value==' ') || document.getElementById("installAddressProvince").value == null){
		jQuery("#installAddressProvinceLbl").hide();
	}
	if(document.getElementById("installAddresscounty").value=='' || document.getElementById("installAddresscounty").value == null){
		jQuery("#installAddresscountyLbl").hide();
	}
	// region changed to district for MPS 2.1
	if((document.getElementById("installAddressdistrict").value=='' || document.getElementById("installAddressdistrict").value==' ') || document.getElementById("installAddressdistrict").value == null){
		jQuery("#installAddressRegionLB").hide();
	}
	if(document.getElementById("installAddressLine2").value=='' || document.getElementById("installAddressLine2").value == null){
		jQuery("#installAddressLine2Lbl").hide();
	}
	//Added for MPS 2.1
	jQuery('#installAddressofficeNumberLbl').html(jQuery('#installAddressofficeNumber').val());
	//Ends
	if(installAddressFlag){
		jQuery('#diffAddressLink').html("<spring:message code="requestInfo.link.selectADifferentAddress"/>").attr('title',"<spring:message code="requestInfo.link.selectADifferentAddress"/>");
	}
	
	//common section end


//for common section 2 
//For LBS dropdowns

if(pageFlow=="fleetMgmtMove"||pageFlow=="Change"){
	jQuery("#moveToStoreFrontNameLbl").html(document.getElementById("moveToStoreFrontName").value);
	jQuery("#moveToAddressNameLbl").html(document.getElementById("moveToAddressName").value);
	document.getElementById("moveToAddressLine1Lbl").innerHTML=document.getElementById("moveToAddressLine1").value;
	
	
	if(document.getElementById("moveTophysicalLocation3h").value !="" && document.getElementById("moveTophysicalLocation3h").value !=null){
		
		if(jQuery("#moveToAddressLBSAddressFlag").val()=="true"|| pageFlow=="fleetMgmtMove"){
			jQuery('#moveTophysicalLocation3m').val(document.getElementById("moveTophysicalLocation3h").value);
			moveAddressFlag=true;
		}else{
			
		jQuery("#moveTophysicalLocation3").val(document.getElementById("moveTophysicalLocation3h").value);
		
		moveAddressFlag=true;
		}
		}
	
	if(document.getElementById("moveToAddressLine2").value !="" && document.getElementById("moveToAddressLine2").value !=null){
		jQuery("#moveToAddressLine2Lbl").html(document.getElementById("moveToAddressLine2").value + ',');
		jQuery("#moveToAddressLine2Lbl").show();
		moveAddressFlag=true;
		}
	if(document.getElementById("moveToAddressCity").value!='' && document.getElementById("moveToAddressCity").value != null){
		jQuery("#moveToAddressCityLbl").html(document.getElementById("moveToAddressCity").value);
		moveAddressFlag=true;
		}
	if(document.getElementById("moveToAddresscounty").value!='' && document.getElementById("moveToAddresscounty").value != null){
		jQuery("#moveToAddresscountyLbl").html(',&nbsp;'+document.getElementById("moveToAddresscounty").value);
		jQuery("#moveToAddresscountyLbl").show();
		moveAddressFlag=true;
	}
	if(document.getElementById("moveToAddressState").value!='' && document.getElementById("moveToAddressState").value != null){
		jQuery("#moveToAddressStateLbl").html(',&nbsp;'+document.getElementById("moveToAddressState").value);
		moveAddressFlag=true;
	}
	if((document.getElementById("moveToAddressProvince").value!='' && document.getElementById("moveToAddressProvince").value!=' ') && document.getElementById("moveToAddressProvince").value != null){
		jQuery("#moveToAddressProvinceLbl").html(',&nbsp;'+document.getElementById("moveToAddressProvince").value);
		jQuery("#moveToAddressProvinceLbl").show();
		moveAddressFlag=true;
	}
	// region changed to district for MPS 2.1
	if((document.getElementById("moveToAddressdistrict").value!=' ' && document.getElementById("moveToAddressdistrict").value!='') && document.getElementById("moveToAddressdistrict").value != null){
		jQuery("#moveToAddressRegionLB").html(',&nbsp;'+document.getElementById("moveToAddressdistrict").value);
		jQuery("#moveToAddressRegionLB").show();
		moveAddressFlag=true;
	}
	
	jQuery("#moveToAddressPostCodeLbl").html(document.getElementById("moveToAddressPostCode").value);
	jQuery("#moveToAddressCountryLbl").html(document.getElementById("moveToAddressCountry").value);
	if((document.getElementById("moveToAddressProvince").value=='' || document.getElementById("moveToAddressProvince").value==' ') || document.getElementById("moveToAddressProvince").value == null){
		jQuery("#moveToAddressProvinceLbl").hide();
	}
	if(document.getElementById("moveToAddresscounty").value=='' || document.getElementById("moveToAddresscounty").value == null){
		jQuery("#moveToAddresscountyLbl").hide();
	}
	// region changed to district for MPS 2.1
	if((document.getElementById("moveToAddressdistrict").value=='' || document.getElementById("moveToAddressdistrict").value==' ') || document.getElementById("moveToAddressdistrict").value == null){
		jQuery("#moveToAddressRegionLB").hide();
	}
	if(document.getElementById("moveToAddressLine2").value=='' || document.getElementById("moveToAddressLine2").value == null){
		jQuery("#moveToAddressLine2Lbl").hide();
	}
	//Added for MPS 2.1
	jQuery('#moveToAddressofficeNumberLbl').html(jQuery('#moveToAddressofficeNumber').val());
	//Ends
	if(moveAddressFlag){
		jQuery('#moveToAddressLink').html("<spring:message code="requestInfo.link.selectADifferentAddress"/>").attr('title',"<spring:message code="requestInfo.link.selectADifferentAddress"/>");
	}

	showBuildingFloorZone("moveToAddress");
}
	//common section end 2
	/*if(pageFlow=="Change"){
		alert('change::document.getElementById("moveToAddressLBSAddressFlag").value::'+document.getElementById("moveToAddressLBSAddressFlag").value);
		if(document.getElementById("moveToAddressLBSAddressFlag").value !="" && document.getElementById("moveToAddressLBSAddressFlag").value !=null && document.getElementById("moveToAddressLBSAddressFlag").value =="true"){
			var addressId=document.getElementById("moveToAddressId").value;
			if(addressId!=''&&addressId!=null){
			jQuery("#selectMoveToAddress1").hide();
			jQuery("#selectMoveToAddress").show();
			
			loadCoutrySateCitySiteBuildingm(addressId);
						
		}else{
			
			jQuery("#selectMoveToAddress1").show();
			jQuery("#selectMoveToAddress").hide();
			jQuery("#moveToAddressLBSAddressFlag").val("false");
			
			lbsInstallm();
			lbsFloorm();
			lbsZonem();
			lbsSitem();
			}
			
		}
		else{
			
			jQuery("#selectMoveToAddress1").show();
			jQuery("#selectMoveToAddress").hide();
			jQuery("#moveToAddressLBSAddressFlag").val("false");
			
			lbsInstallm();
			lbsFloorm();
			lbsZonem();
			lbsSitem();
			}
	}
	
if(pageFlow=="fleetMgmtMove"){
		
	if(document.getElementById("moveToAddressLBSAddressFlag").value !="" && document.getElementById("moveToAddressLBSAddressFlag").value !=null && document.getElementById("moveToAddressLBSAddressFlag").value =="true"){
		
			var addressId=document.getElementById("moveToAddressId").value;
			if(addressId!=''&&addressId!=null){
			jQuery("#selectMoveToAddress1").hide();
			jQuery("#selectMoveToAddress").show();
			
			loadCoutrySateCitySiteBuildingm(addressId);
						
		}else{
			jQuery("#selectMoveToAddress1").show();
			jQuery("#selectMoveToAddress").hide();
			jQuery("#moveToAddressLBSAddressFlag").val("false");
			lbsInstallm();
			lbsFloorm();
			lbsZonem();
			lbsSitem();
		}
				
		}
	
	else{
		
			var addressId=document.getElementById("moveToAddressId").value;
			
				if(jQuery("#bldngm1").val()!=""&&jQuery("#flrm1").val()!=""){
		
		
		jQuery("#selectMoveToAddress1").hide();
		jQuery("#selectMoveToAddress").show();
		lbsInstallm();
		lbsFloorm();
		lbsZonem();
		lbsSitem();
		
		
		}else if(addressId!=''&&addressId!=null){
			
				jQuery("#selectMoveToAddress1").hide();
				jQuery("#selectMoveToAddress").show();
				
				loadCoutrySateCitySiteBuildingm(addressId);
							
			
		}
		else{
			jQuery("#selectMoveToAddress1").show();
			jQuery("#selectMoveToAddress").hide();
			jQuery("#moveToAddressLBSAddressFlag").val("false");
			lbsInstallm();
			lbsFloorm();
			lbsZonem();lbsSitem();
		}
	
		}
	
	}*/

//For LBS dropdowns
	function lbsInstall(){
	if(document.getElementById("bldng1").value !="" && document.getElementById("bldng1").value !=null){
		if(jQuery("#installLBSAddressFlag").val()=="true"){
			var bldDrop=jQuery("#bldng1").val();
			
			var flrDrop=jQuery("#flr1").val();
			var insflg=false;
			
                       $(function() {
                           $("#bldng option").each(function() {
                               
                               if( $(this).prop('text') == bldDrop ) { $(this).attr('selected','selected');insflg=true; }
                           });
                          
                        	   if(insflg){
			                        		 $('#bldng').attr('disabled', true);
			                        		 $('#zone').attr('disabled', true);
			                        	 }
                           jQuery('#bldng1').val(jQuery("#bldng option:selected").text());
                           jQuery('#physicalLocation1h').val(jQuery("#bldng option:selected").val());
                           $('#flr').attr('disabled',true);
                           $('#btnContinue').attr('disabled',true);
                           getSite(null);
                           getFloor(null);
                           
                                                                      
                       });
                    
                     
                     installAddressFlag=true;
		}else{
			
		jQuery("#physicalLocation1").val(document.getElementById("bldng1").value);
		installAddressFlag=true;
		}
	}
	}
	
	function lbsFloor(p){
		
	if(document.getElementById("flr1").value !="" && document.getElementById("flr1").value !=null){
		if(jQuery("#installLBSAddressFlag").val()=="true"){
			
			var flrflg=false;
			var installAddressLevelOfDetails = $("#installLevelOfDetails").val();
			
			 $(function() {
				 
				 if(p!="buildingchange"){
				
					 $("#flr option").each(function() {
	                      
	                     if( $(this).prop('text') == jQuery("#flr1").val() ) { $(this).attr('selected','selected');flrflg=true; }
	                 });
					 }
				 if(flrflg){
			                        		 $('#flr').attr('disabled', true);
			                        		 $('#zone').attr('disabled', true);

			                        		 if(installAddressLevelOfDetails.toLowerCase() === "mix - see floor" 
			     					    		&& $("#flr option:selected").attr("lod") != undefined
			     					    		&& $("#flr option:selected").attr("lod").toLowerCase() === "grid level")
			     						 	{
			     					    		if(curURL.indexOf('/fleet-management') != -1)
			     								{
			     									//$(".installSection").find("#gridLi").hide();
			     									
			     									if(pageFlow.toLowerCase() === "addone")
			     									{
			     										$(".installSection").find("#gridLi").show();
			     									}
			     									else
			     									{
			     										$(".installSection").find("#gridLi").show();
			     										$(".installSection").find("#installedXYLblDiv").hide();
			     									}
			     								}
			     					    		else
			     					    		{
			     					    			$(".installSection").find("#gridLi").show();
			     					    		}			     										     					    		
			     						 	}
			                        	 }
				 
                 jQuery('#flr1').val(jQuery("#flr option:selected").text());
                 jQuery('#physicalLocation2h').val(jQuery("#flr option:selected").val());
                
             });
			
			installAddressFlag=true;
		}else{
		jQuery("#physicalLocation2").val(document.getElementById("flr1").value);
		
		installAddressFlag=true;
		}
		}
	}
	
	function lbsSite(p){
		
		if(document.getElementById("campus1").value !="" && document.getElementById("campus1").value !=null){
			if(jQuery("#installLBSAddressFlag").val()=="true"){
				
				var siteflg=false;
				 $(function() {
					 if(p!="buildingchange"){
					 $("#campus option").each(function() {
	                    
	                 if( $(this).prop('text') == jQuery("#campus1").val() ) { $(this).attr('selected','selected'); }
	             });
	                		 }
	                  jQuery('#campus1').val(jQuery("#campus option:selected").text());
		         	 jQuery('#campush').val(jQuery("#campus option:selected").val());
	                
	             });
				
				installAddressFlag=true;
			}
			}
		}
	
	if(document.getElementById("physicalLocation3h").value !="" && document.getElementById("physicalLocation3h").value !=null){
		if(jQuery("#installLBSAddressFlag").val()=="true"){
			jQuery("#office").val(document.getElementById("physicalLocation3h").value);
			installAddressFlag=true;
		}else{
			jQuery("#physicalLocation3").val(document.getElementById("physicalLocation3h").value);
			
			installAddressFlag=true;
		}
		
		}
		function lbsZone(){
		if(document.getElementById("zone1").value !="" && document.getElementById("zone1").value !=null){
			var zoneDrop=jQuery("#zone1").val();
			var zonflg=false;
                       $(function() {
                           $("#zone option").each(function() {
                                 
                               if( $(this).prop('text') == zoneDrop ) { $(this).attr('selected','selected');zonflg=true; }
                           });
                      
                      
                           if(zonflg==true || ( ($('#bldng').prop('disabled')==true) && ($('#flr').prop('disabled')==true) )){
			                   $('#zone').attr('disabled', true);
			              }
                    	  
                       jQuery('#zone1').val(jQuery("#zone option:selected").text());
                       jQuery('#zoneh').val(jQuery("#zone option:selected").val());
                     
                       });
				installAddressFlag=true;
				
			}
	}

		
		//for drop-downs without address id
		/*function getSiteBuildingZone(site,building,zone){
		urlParamsObj1.clearHtmlAfter('bldngm');
			
			urlParamsObj1.setDefaultHTMlAt('bldngm');
			
			urlParamsObj1=new urlParams1();
			
			var ctry=$('#moveToAddressCountryLbl').text();
			
			
			urlParamsObj1["ctry"]=ctry;
			
			var state=$('#moveToAddressStateLbl').text().slice(2);
			
			urlParamsObj1["state"]=state;
			
			var cty=$('#moveToAddressCityLbl').text();
			urlParamsObj1["cty"]=cty;
			
			var url=appendURLPrams1("${siteBuildingListPopulateURL}&frCal=true",urlParamsObj1);
			urlParamsObj1.disableAll();
			$.getJSON(url,function(response){
				urlParamsObj1.enableAll();	
				$('#bldngm').append(response.building);
				$('#zonem').append(response.zone);
				lbsInstallm();
				lbsZonem();
				
			});
		
		}*/
		
		function lbsFloorm(p){
			
			if(document.getElementById("flrm1").value !="" && document.getElementById("flrm1").value !=null){
					if(jQuery("#moveToAddressLBSAddressFlag").val()=="true"){
						
					var flrflgm=false;
					 $(function() {
		                 if(p!="buildingchange"){
		                	 
		                	
							 $("#flrm option").each(function() {
			                       
			                     if( $(this).prop('text') == jQuery("#flrm1").val() ) { $(this).attr('selected','selected');flrflgm=true; }
			                	
			                	 });
			                 }
		                	  if(flrflgm){
			                        		 $('#flrm').attr('disabled', true);
			                        	 }
		                 
		                 jQuery('#flrm1').val(jQuery("#flrm option:selected").text());
		                 jQuery('#moveTophysicalLocation2h').val(jQuery("#flrm option:selected").val());
		                  
		             });
					
					installAddressFlag=true;
				}else{
					if(pageFlow=="fleetMgmtMove"&&jQuery("#flrm1").val()!=""&&jQuery("#flrm1").val()!=null){
						var flrmflg=false;
						 $('<option>').val(jQuery("#moveTophysicalLocation2h").val()).text(jQuery("#flrm1").val()).appendTo('#flrm');
						
						
						$(function() {
			                 $("#flrm option").each(function() {
			                   
			                	 if( $(this).prop('text') == jQuery("#flrm1").val() ) { $(this).attr('selected','selected'); flrmflg=true; }
			                   
			                	 });
			                 if(flrmflg){
                        		 $('#flrm').attr('disabled', true);
                        	 }
			              
			                 jQuery('#flrm1').val(jQuery("#flrm option:selected").text());
			                
			             });
						installAddressFlag=true;
					}
					else{	
				jQuery("#moveTophysicalLocation2").val(document.getElementById("flrm1").value);
				
				installAddressFlag=true;
					}
					
				}
				}
			
		}
		
		
function lbsSitem(p){
			
			if(document.getElementById("campusm1").value !="" && document.getElementById("campusm1").value !=null){
					if(jQuery("#moveToAddressLBSAddressFlag").val()=="true"){
						
					var flrflgm=false;
					 $(function() {
		                 if(p!="buildingchange"){
		                	 
		                	 $("#campusm option").each(function() {
		                       
		                     if( $(this).prop('text') == jQuery("#campusm1").val() ) { $(this).attr('selected','selected'); }
		                	
		                	 });
		                 } 
		                 jQuery('#campusm1').val(jQuery("#campusm option:selected").text());
		         		jQuery('#campusmh').val(jQuery("#campusm option:selected").val());
		               
		             });
					
					installAddressFlag=true;
				}else{
					if(pageFlow=="fleetMgmtMove"&&jQuery("#campusm1").val()!=""&&jQuery("#campusm1").val()!=null){
						var flrmflg=false;
						
						 $('<option>').val(jQuery("#campusmh").val()).text(jQuery("#campusm1").val()).appendTo('#campusm');
						
						$(function() {
			                 $("#campusm option").each(function() {
		                       
		                	 if( $(this).prop('text') == jQuery("#campusm1").val() ) { $(this).attr('selected','selected');  }
		                   
		                	 });
			                   jQuery('#campusm1').val(jQuery("#campusm option:selected").text());
			         		jQuery('#campusmh').val(jQuery("#campusm option:selected").val());
			              
			             });
						installAddressFlag=true;
					}
					
					
				}
				}
			
		}
		
		function lbsInstallm(){
			
			if(document.getElementById("bldngm1").value !="" && document.getElementById("bldngm1").value !=null){
			
					if(jQuery("#moveToAddressLBSAddressFlag").val()=="true"){
					var bldDrop=jQuery("#bldngm1").val();
					var insflgm=false;
					
					           $(function() {
		                           $("#bldngm option").each(function() {
		                                
		                                	 if( $(this).prop('text') == bldDrop ) { $(this).attr('selected','selected');insflgm=true; }	 
		                               
		                                 });
		                          
		                        	  
		                        	  if(insflgm){
                        		 $('#bldngm').attr('disabled', true);
                        	 }
		                           jQuery('#bldngm1').val(jQuery("#bldngm option:selected").text());
		                           jQuery('#moveTophysicalLocation1h').val(jQuery("#bldngm option:selected").val());
		                           $('#flrm').attr('disabled',true);
		                           $('#btnContinue').attr('disabled',true);
		                           getSitem(null);
		                           getFloorm(null);
		                           
		                                                
		                           
		                       });
		                     
		                     
		                     installAddressFlag=true;
				}else{
					
					if(pageFlow=="fleetMgmtMove"&&jQuery("#bldngm1").val()!=""&&jQuery("#bldngm1").val()!=null){
						var bldDrop=jQuery("#bldngm1").val();
						var bldDropval=jQuery("#moveTophysicalLocation1h").val();
						var bldmflag=false;
						 $('<option>').val(bldDropval).text(bldDrop).appendTo('#bldngm');
						          $(function() {
			                           $("#bldngm option").each(function() {
			                                  
			                                 if( $(this).prop('text') == bldDrop ) { $(this).attr('selected','selected');bldmflag=true; }
			                                 });
			                           
			                        	 
			                        	 
			                        	 if(bldmflag){
			                        		 $('#bldngm').attr('disabled', true);
			                        	 }
			                           jQuery('#bldngm1').val(jQuery("#bldngm option:selected").text());
			                           jQuery('#moveTophysicalLocation1h').val(jQuery("#bldngm option:selected").val());
			                        
			                                                
			                           
			                       });
			                    
			                     
			                     installAddressFlag=true;
					}
					else{
						
					
				jQuery("#moveTophysicalLocation1").val(document.getElementById("bldngm1").value);
				installAddressFlag=true;
					}
				}
			}
			
		}
		function lbsZonem(){
			
			
			if(document.getElementById("zonem1").value !="" && document.getElementById("zonem1").value !=null){
				var zoneDrop=jQuery("#zonem1").val();
				var zoneDropval=jQuery("#zonemh").val();
				var zonemflg=false;
				if(jQuery("#moveToAddressLBSAddressFlag").val()!="true"){
				 $('<option>').val(zoneDropval).text(zoneDrop).appendTo('#zonem');
				}
	                       $(function() {
	                           $("#zonem option").each(function() {
	                                  
	                               if( $(this).prop('text') == zoneDrop ) { $(this).attr('selected','selected');zonemflg=true; }
	                           });
	                           if(zonemflg){
	                        		 $('#zonem').attr('disabled', true);
	                        	 }
	                       });
	                     
	                       jQuery('#zonem1').val(jQuery("#zonem option:selected").text());
	                       jQuery('#zonemh').val(jQuery("#zonem option:selected").val());
	                    
	                      
					installAddressFlag=true;
					
				}
			if(pageFlow=="fleetMgmtMove"&&jQuery("#zonem1").val()==""&&jQuery("#moveToAddressLBSAddressFlag").val()!="true"){
				$('<option>').val('').text('').appendTo('#zonem');
				
				$(function() {
                    $("#zonem option").each(function() {
                          
                        if( $(this).prop('text') == '' ) { $(this).attr('selected','selected'); }
                    });
                    
                 		 $('#zonem').attr('disabled', true);
                 	 
                });
              
                jQuery('#zonem1').val(jQuery("#zonem option:selected").text());
                jQuery('#zonemh').val(jQuery("#zonem option:selected").val());
              
               
			installAddressFlag=true;
				
				
			}
			
		}
		
	
		
<%--for address section start--%>
function popupAddress(hyperlinkId){
	
	jQuery('#buttonContact_popup').show();
	linkId=hyperlinkId;//This link id is for displaying inner htmls in address
	
	var addressFlag;
	showOverlay();
	
	var url=jQuery('#'+hyperlinkId).attr('href');
	url+="&addressFlag="+addressFlag;
	jQuery('#dialogAddress').load(url,function(){
		
		dialog=jQuery('#content_addr').dialog({
			autoOpen: false,
			title: jQuery('#'+hyperlinkId).attr('title'),
			modal: true,
			draggable: false,
			resizable: false,
			height: 500,
			width: 950,
			close: function(event,ui){
				hideOverlay();
				dialog=null;
				jQuery('#content_addr').remove();
				}
			});
		jQuery(document).scrollTop(0);
		dialog.dialog('open');
		initializeAddressGrid();
		if (window.PIE) {
            document.getElementById("createNewAddressButton").fireEvent("onmove");
            document.getElementById("cancelAddressButton").fireEvent("onmove");
        }
		});
	return false;
}

//Changes for MPS 2.1
function addRestFieldsOfCleanseAddress(){

	if(linkId=="deviceAddressLink")
	{
		addRestFieldsOfCleanseDeviceAddr();
	} else {
	
	jQuery('#installAddressisAddressCleansed').val("true");
	if(linkId=="diffAddressLink"){
	<%for(String cleanseOutputFields:ADDRESSCLEANSEFIELDS_OUTPUT){%>
		jQuery("#installAddress<%=cleanseOutputFields%>").val(cleaseAddressFields["<%=cleanseOutputFields%>"]);
	<%}%>
	}else if(linkId=="moveToAddressLink"){
		jQuery('#moveToAddressisAddressCleansed').val("true");
		<%for(String cleanseOutputFields:ADDRESSCLEANSEFIELDS_OUTPUT){%>
		jQuery("#moveToAddress<%=cleanseOutputFields%>").val(cleaseAddressFields["<%=cleanseOutputFields%>"]);
		<%}%>
		}
	jQuery('#installAddressofficeNumberLbl').html(jQuery('#installAddressofficeNumber').val());
	//Start Added for MPS 2.1
	jQuery("#installAddresscountyLbl").html(',&nbsp;'+jQuery('#installAddresscounty').val());
	jQuery("#installAddresscountyLbl").show();
	if(jQuery('#installAddresscounty').val() =='' || jQuery('#installAddresscounty').val() == null){
		jQuery("#installAddresscountyLbl").hide();
		}
	
	//END
	}
	}
<%--Changes for Phase 2.1--%>
//Changed for LBS dropdowns
function addServiceAddressElement(addressId,addressName,addressLine1,addressLine2, 
		addressCity,addressState,addressProvince,addressCountry,addressPostCode,storefrontName,physicalLoc1,physicalLoc2,physicalLoc3,favorite,officeNumber,district,county,countryISO,region,stateCode,lbsAddressFlag,levelOfDetails)
<%--Ends--%>
{
	jQuery('#errorDiv').hide();
	<%--Changes for MPS 2.1 
	if(addressId==null || addressId=="")
		jQuery('#installAddressisAddressCleansed').val("false");
	else
		jQuery('#installAddressisAddressCleansed').val("true");
	
	ENDS Changes for MPS 2.1 --%>
	
	addressChangeReq=true;
	if(linkId=="diffAddressLink")
	{
		
		jQuery('#diffAddressLink').html("<spring:message code="requestInfo.link.selectADifferentAddress"/>").attr('title',"<spring:message code="requestInfo.link.selectADifferentAddress"/>");
		<%--Changes for Phase 2.1--%>
		//call to addConsumablesAddress in the commonAsset.js
		addPickupAddress(addressId,addressName,addressLine1,addressLine2,addressCity,addressState,addressProvince,
				addressCountry,addressPostCode,storefrontName,favorite,officeNumber,district,county,countryISO,region,stateCode,lbsAddressFlag,levelOfDetails);
		//added for LBS
		if(lbsAddressFlag=="true"){
			//For LOD -- jQuery("#selectLocalAddress1").hide();
			//For LOD -- jQuery("#selectLocalAddress").show();
			
			//setting values to ""
			jQuery("#bldng1").val("");
			jQuery("#flr1").val("");
			jQuery("#zone1").val("");
			jQuery('#campus1').val("");
			jQuery('#campush').val("");
			jQuery("#physicalLocation1h").val("");
			jQuery("#physicalLocation2h").val("");
			jQuery("#physicalLocation3h").val("");
			jQuery("#zoneh").val("");
			jQuery("#physicalLocation1").val("");
			jQuery("#physicalLocation2").val("");
			jQuery("#physicalLocation3").val("");
			jQuery("#office").val("");
			
			//For LOD -- loadCoutrySateCitySiteBuilding(addressId);
			showBuildingFloorDropDOwns(addressId, "install", "selectLocalAddress");
			clearGridValues("installed");
		}
		else{
			//setting values to ""
			jQuery("#bldng1").val("");
			jQuery("#flr1").val("");
			jQuery("#zone1").val("");
			jQuery('#campus1').val("");
			jQuery('#campush').val("");
			jQuery("#physicalLocation1h").val("");
			jQuery("#physicalLocation2h").val("");
			jQuery("#physicalLocation3h").val("");
			jQuery("#zoneh").val("");
			jQuery("#physicalLocation1").val("");
			jQuery("#physicalLocation2").val("");
			jQuery("#physicalLocation3").val("");
			jQuery("#office").val("");
			jQuery("#selectLocalAddress1").show();
			jQuery("#selectLocalAddress").hide();
			clearGridValues("installed");
		}
		<%--Ends--%>
	}
	if(linkId=="moveToAddressLink")
	{
		jQuery('#moveToAddressLink').html("<spring:message code="requestInfo.link.selectADifferentAddress"/>").attr('title',"<spring:message code="requestInfo.link.selectADifferentAddress"/>");
		<%--Changes for Phase 2.1--%>
		//call to addConsumablesAddress in the commonAsset.js
		addMoveToAddress(addressId,addressName,addressLine1,addressLine2,addressCity,addressState,addressProvince,
				addressCountry,addressPostCode,storefrontName,favorite,officeNumber,district,county,countryISO,region,stateCode,lbsAddressFlag,levelOfDetails);
		//added for LBS
		
		if(lbsAddressFlag=="true"){
			var addressId=document.getElementById("moveToAddressId").value;
			
			//For LOD jQuery("#selectMoveToAddress1").hide();
			//For LOD jQuery("#selectMoveToAddress").show();
			//setting values to ""
			jQuery("#bldngm1").val("");
			jQuery("#flrm1").val("");
			jQuery("#zonem1").val("");
			jQuery('#campusm1').val("");
			jQuery('#campusmh').val("");
			jQuery("#moveTophysicalLocation1h").val("");
			jQuery("#moveTophysicalLocation2h").val("");
			jQuery("#moveTophysicalLocation3h").val("");
			jQuery("#zonemh").val("");
			jQuery("#moveTophysicalLocation1").val("");
			jQuery("#moveTophysicalLocation2").val("");
			jQuery("#moveTophysicalLocation3").val("");
			jQuery("#moveTophysicalLocation3m").val("");
			
			//For LOD loadCoutrySateCitySiteBuildingm(addressId);
			showBuildingFloorDropDOwns(addressId, "moveToAddress", "selectMoveToAddress");
			clearGridValues("moveTo");
						
		}else{
			//setting values to ""
			jQuery("#bldngm1").val("");
			jQuery("#flrm1").val("");
			jQuery("#zonem1").val("");
			jQuery('#campusm1').val("");
			jQuery('#campusmh').val("");
			jQuery("#moveTophysicalLocation1h").val("");
			jQuery("#moveTophysicalLocation2h").val("");
			jQuery("#moveTophysicalLocation3h").val("");
			jQuery("#zonemh").val("");
			jQuery("#moveTophysicalLocation1").val("");
			jQuery("#moveTophysicalLocation2").val("");
			jQuery("#moveTophysicalLocation3").val("");
			jQuery("#moveTophysicalLocation3m").val("");
			
			jQuery("#selectMoveToAddress1").show();
			jQuery("#selectMoveToAddress").hide();
			clearGridValues("moveTo");
		}
		
			<%--Ends--%>
	}
	if(linkId=="deviceAddressLink")
	{
		<%--Changes for Phase 2.1--%>
		
		//call to addConsumablesAddress in the commonAsset.js
		addDeviceAddress(addressId,addressName,storefrontName,addressLine1,addressLine2,addressCity,addressState,addressProvince,
				addressCountry,addressPostCode,officeNumber,district,county,countryISO,region,stateCode,physicalLoc1,physicalLoc2,physicalLoc3,false);
		<%--Ends--%>
	}
	closeDialog();
}
function addPickupAddress(addressId,addressName,addressLine1,addressLine2,addressCity,addressState,addressProvince,
		addressCountry,addressPostCode,storefrontName,favorite,officeNumber,district,county,countryISO,region,stateCode,lbsAddressFlag,levelOfDetails)

{	
	if(addressProvince == null){
		addressProvince = '';
	}
	if(addressId==null){
		addressId = '';
	}
	jQuery("#installAddressLine2").val("");
	jQuery("#installAddressProvince").val("");
	jQuery("#installAddressPostCode").val("");
	jQuery("#installAddressofficeNumber").val("");
	jQuery("#installAddressdistrict").val("");
	jQuery("#installAddressstateCode").val("");
	jQuery("#installAddresscountryISOCode").val("");
	
	jQuery("#installAddressId").val(addressId);
	jQuery("#installStoreFrontName").val(storefrontName);
	jQuery("#installAddressName").val(addressName);
	jQuery("#installAddressLine1").val(addressLine1);	
	jQuery("#installAddressofficeNumberLbl").val(addressLine1);	
	jQuery("#installAddressLine2").val(addressLine2);
	if(lbsAddressFlag=='null'||lbsAddressFlag==''){
		lbsAddressFlag="false";
		
	}
	jQuery("#installLBSAddressFlag").val(lbsAddressFlag);
	
			
	jQuery("#installAddressCity").val(addressCity);
	jQuery("#installAddressState").val(addressState);
	jQuery("#installAddressProvince").val(addressProvince);
	jQuery("#installAddressPostCode").val(addressPostCode);
	jQuery("#installAddressCountry").val(addressCountry);
	<%--Changes for Phase 2.1--%>
	jQuery("#installAddressofficeNumber").val(officeNumber);
	jQuery("#installAddressdistrict").val(district);
	jQuery("#installAddresscounty").val(county);
	//added
	jQuery("#installAddressregion").val(region);
	jQuery("#installAddresscountryISOCode").val(countryISO);
	jQuery("#installAddressstateCode").val(stateCode);
	<%--Ends--%>
	//changes for MPS 2.1
	//This is for display purpose install address
	jQuery("#installStoreFrontNameLbl").html(storefrontName);
	jQuery("#installAddressNameLbl").html(addressName);
	jQuery("#installAddressLine1Lbl").html(addressLine1);
	jQuery("#installAddressofficeNumberLbl").html(officeNumber)		
	if(addressLine2 !='' && addressLine2 !=' ' && addressLine2 != null){
		jQuery("#installAddressLine2Lbl").html(addressLine2+",");
		jQuery("#installAddressLine2Lbl").show();
		}

	if(addressCity !='' && addressCity !=' ' && addressCity != null){
		jQuery("#installAddressCityLbl").html(addressCity);
		}
	
	if(county !='' && county !=' ' && county != null){
		jQuery("#installAddresscountyLbl").html(', '+county);
		jQuery("#installAddresscountyLbl").show();
		}
	if(addressState !='' && addressState !=' ' && addressState != null){
		jQuery("#installAddressStateLbl").html(", "+addressState);
		jQuery("#installAddressStateLbl").show();
		}
	if(addressProvince !='' && addressProvince !=' ' && addressProvince != null){
		jQuery("#installAddressProvinceLbl").html(', '+addressProvince);
		jQuery("#installAddressProvinceLbl").show();
		}
	// region changed to district fot MPS 2.1
	if(district !=' ' && district !=' ' && district != null){
		jQuery("#installAddressRegionLB").html(', '+district);
		jQuery("#installAddressRegionLB").show();
		}
	jQuery("#installAddressPostCodeLbl").html(addressPostCode);		
	jQuery("#installAddressCountryLbl").html(addressCountry);
	if(addressProvince =='' || addressProvince ==' ' || addressProvince == null){
		jQuery("#installAddressProvinceLbl").hide();
		jQuery("#installAddressProvinceLbl").text("");
		}
	if(county =='' || county ==' ' || county == null){
		jQuery("#installAddresscountyLbl").hide();
		jQuery("#installAddresscountyLbl").text('');
		}
	// region changed to district for MPS 2.1
	if(district ==' '||district =='' || district == null){
		jQuery("#installAddressRegionLB").hide();
		jQuery("#installAddressregionLbl").text('');
		}
	if(addressLine2 =='' || addressLine2 ==' ' || addressLine2 == null){
		jQuery("#installAddressLine2Lbl").hide();
		jQuery("#installAddressLine2Lbl").text("");
		}
	if(addressState =='' || addressState ==' ' || addressState == null){
		jQuery("#installAddressStateLbl").hide();
		jQuery("#installAddressStateLbl").text("");
		}
	if(addressCity =='' || addressCity ==' ' || addressCity == null){
		jQuery("#installAddressCityLbl").hide();
		jQuery("#installAddressCityLbl").text("");
		}
	//changes for MPS 2.1 end

	//Change for LBS 1.5- LOD checking
	$("#installLevelOfDetails").val(levelOfDetails);
}
function addMoveToAddress(addressId,addressName,addressLine1,addressLine2,addressCity,addressState,addressProvince,
		addressCountry,addressPostCode,storefrontName,favorite,officeNumber,district,county,countryISO,region,stateCode,lbsAddressFlag,levelOfDetails)

{	
	if(addressProvince == null){
		addressProvince = '';
	}
	if(addressId==null){
		addressId = '';
	}

	jQuery("#moveToAddressLine2").val("");
	jQuery("#moveToAddressProvince").val("");
	jQuery("#moveToAddressPostCode").val("");
	jQuery("#moveToAddressofficeNumber").val("");
	jQuery("#moveToAddressdistrict").val("");
	jQuery("#moveToAddressstateCode").val("");
	jQuery("#moveToAddresscountryISOCode").val("");
	
	
	jQuery("#moveToAddressId").val(addressId);
	jQuery("#moveToAddressName").val(addressName);
	jQuery("#moveToStoreFrontName").val(storefrontName);
	jQuery("#moveToAddressLine1").val(addressLine1);	
	jQuery("#moveToAddressofficeNumberLbl").val(addressLine1);	
	jQuery("#moveToAddressLine2").val(addressLine2);
	if(lbsAddressFlag=='null'||lbsAddressFlag==''){
		lbsAddressFlag="false";
	}
	jQuery("#moveToAddressLBSAddressFlag").val(lbsAddressFlag);
	
	
			
	jQuery("#moveToAddressCity").val(addressCity);
	jQuery("#moveToAddressState").val(addressState);
	jQuery("#moveToAddressProvince").val(addressProvince);
	jQuery("#moveToAddressPostCode").val(addressPostCode);
	jQuery("#moveToAddressCountry").val(addressCountry);
	<%--Changes for Phase 2.1--%>
	jQuery("#moveToAddressofficeNumber").val(officeNumber);
	jQuery("#moveToAddressdistrict").val(district);
	jQuery("#moveToAddresscounty").val(county);
	//added
	jQuery("#moveToAddressregion").val(region);
	jQuery("#moveToAddresscountryISOCode").val(countryISO);
	jQuery("#moveToAddressstateCode").val(stateCode);
	<%--Ends--%>
	//changes for MPS 2.1
	//This is for display purpose moveTo address
	jQuery("#moveToStoreFrontNameLbl").html(storefrontName);
	jQuery("#moveToAddressNameLbl").html(addressName);
	jQuery("#moveToAddressLine1Lbl").html(addressLine1);
	jQuery("#moveToAddressofficeNumberLbl").html(officeNumber)		
	if(addressLine2 !='' && addressLine2 !=' ' && addressLine2 != null){
		jQuery("#moveToAddressLine2Lbl").html(addressLine2+",");
		jQuery("#moveToAddressLine2Lbl").show();
		}

	if(addressCity !='' && addressCity !=' ' && addressCity != null){
		jQuery("#moveToAddressCityLbl").html(addressCity);
		}
	
	if(county !='' && county !=' ' && county != null){
		jQuery("#moveToAddresscountyLbl").html(', '+county);
		jQuery("#moveToAddresscountyLbl").show();
		}
	if(stateCode !='' && stateCode !=' ' && stateCode != null){
		jQuery("#moveToAddressStateLbl").html(", "+stateCode);
		jQuery("#moveToAddressStateLbl").show();
		}
	if(addressProvince !='' && addressProvince !=' ' && addressProvince != null){
		jQuery("#moveToAddressProvinceLbl").html(', '+addressProvince);
		jQuery("#moveToAddressProvinceLbl").show();
		}
	// region changed to district fot MPS 2.1
	if(district !=' ' && district !=' ' && district != null){
		
		
		jQuery("#moveToAddressRegionLB").html(', '+district);
		jQuery("#moveToAddressRegionLB").show();
		}
	jQuery("#moveToAddressPostCodeLbl").html(addressPostCode);		
	jQuery("#moveToAddressCountryLbl").html(addressCountry);
	if(addressProvince =='' || addressProvince ==' ' || addressProvince == null){
		jQuery("#moveToAddressProvinceLbl").hide();
		jQuery("#moveToAddressProvinceLbl").text("");
		}
	if(county =='' || county ==' ' || county == null){
		jQuery("#moveToAddresscountyLbl").hide();
		jQuery("#moveToAddresscountyLbl").text("");
		}
	// region changed to district for MPS 2.1
	if(district ==' '||district =='' || district == null){
		jQuery("#moveToAddressRegionLB").hide();
		jQuery("#moveToAddressRegionLB").text("");
		}
	if(addressLine2 =='' || addressLine2 ==' ' || addressLine2 == null){
		jQuery("#moveToAddressLine2Lbl").hide();
		jQuery("#moveToAddressLine2Lbl").text("");
		}
	if(stateCode =='' || stateCode ==' ' || stateCode == null){
		jQuery("#moveToAddressStateLbl").hide();
		jQuery("#moveToAddressStateLbl").text("");
		}
	if(addressCity =='' || addressCity ==' ' || addressCity == null){
		jQuery("#moveToAddressCityLbl").hide();
		jQuery("#moveToAddressCityLbl").text("");
		}
	
	//changes for MPS 2.1 end

	//Change for LBS 1.5- LOD checking
	$("#moveToAddressLevelOfDetails").val(levelOfDetails);
}

$('#moveToGridLink').click(function(){openPopupMap("moveTo",'${mdmId}','${mdmLevel}','${formPost}','${emailId}',$("#moveTophysicalLocation1h").val(),$("#moveTophysicalLocation2h").val());});
$('#installedGridLink').click(function(){openPopupMap("installed",'${mdmId}','${mdmLevel}','${formPost}','${emailId}',$("#physicalLocation1h").val(),$("#physicalLocation2h").val());});

function coordinates(xCo,yCo,flag){
	var xCoordinate="";
	var yCoordinate="";
	var seperator="/";
	if(!(xCo && yCo))
	{
			seperator="";
	}
	if(xCo){xCoordinate=xCo;}
	if(yCo){yCoordinate=yCo;}
	if(flag=="moveTo"){
		$('#moveToCoords').html(xCoordinate+seperator+yCoordinate);
		$('#moveToAddresscoordinatesXPreDebriefRFV').val(xCoordinate);
		$('#moveToAddresscoordinatesYPreDebriefRFV').val(yCoordinate);
	}
	else if(flag=="installed"){
		$('#installedCoords').html(xCoordinate+seperator+yCoordinate);
		$('#installcoordinatesXPreDebriefRFV').val(xCoordinate);
		$('#installcoordinatesYPreDebriefRFV').val(yCoordinate);
	}	
}

function clearGridValues(action)
{
	if(action === "moveTo")
	{
		$('#moveToCoords').html("");
		$('#moveToAddresscoordinatesXPreDebriefRFV').val("");
		$('#moveToAddresscoordinatesYPreDebriefRFV').val("");
	}
	else if(action === "installed")
	{
		$('#installedCoords').html("");
		$('#installcoordinatesXPreDebriefRFV').val("");
		$('#installcoordinatesYPreDebriefRFV').val("");
	}
}

</script>
