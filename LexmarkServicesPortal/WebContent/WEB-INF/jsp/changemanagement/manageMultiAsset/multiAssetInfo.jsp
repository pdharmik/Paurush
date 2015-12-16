<%@ page import="com.lexmark.services.util.LexmarkSPOmnitureConstants"%>
<%@ page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>
<%@ page contentType="text/html" isELIgnored="false"%>
<%@ taglib prefix="portlet" uri="/WEB-INF/tld/liferay-portlet.tld"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="html" tagdir="/WEB-INF/tags/html"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<style type="text/css">	
	.asset-included table {
	    width: 100%;
	}
	.asset-included input[type="text"], .asset-included select {
	    max-width: 140px !important;
	    padding: 0 !important;
	}
	.asset-included select{
		width:95% !important;
	}
	div.multi-assets-block {
		background: white none repeat scroll 0 0 !important;
	}
	.asset-included label{
		font-weight: bold !important;
	}
	.multi-assets hr{
	    border-color: #c9c9c1 !important;
		border-style: solid !important;
		border-width: 1px 0 0 !important;
		clear: both !important;
	}
	.asset-included td{
		padding-bottom: 3px !important;
	}
	.columnsTwo {
		margin-bottom: 10px !important;
	}
	.secondColumnDiv{
		padding-left: 50px !important;
		width: 40% !important;			
	}			
	.secondColumnDiv select, .secondColumnDiv input {
		width: 90% !important;
	}
	.grey{
		background:#eeeeee !important;
	}
</style>
<%-- Below URL opens up the chl tree in popup --%>
<portlet:renderURL var="showCHLTreePopUp" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>">
<portlet:param name="action" value="showCHLTreePopUp"></portlet:param>
</portlet:renderURL>
<portlet:resourceURL var="retrieveDeviceURLVar" id="retrieveDeviceURL"></portlet:resourceURL>
<div class="portletBlock infoBox rounded shadow multi-assets-block">
	<div class="columnsOne">
		<div class="columnInner multi-assets">
			<h4><spring:message code="fleetmanagement.headers.devicesIncluded"/></h4>
			<c:choose>					
				
				<c:when test="${pageFlow eq 'Decommission'}">
					<c:set var="assetMap" value="${manageAssetFormForDecommission.assetDetailsMap}"></c:set>
				</c:when>
				<c:otherwise>
					<c:set var="assetMap" value="${manageAssetFormForChange.assetDetailsMap}"></c:set>
				</c:otherwise>
			</c:choose>
			<c:forEach var="assetDetail" items="${assetMap}">
				<div class="asset-included">
							<table>
								<tbody>
									<tr>
										<td><label><spring:message code="fleetmanagement.headers.serial"/></label></td>
										<td>${assetDetail.value.serialNumber}</td>
										<td><label><spring:message code="serviceRequest.label.ipAddress"/></label></td>
										<td><form:input path="assetDetailsMap['${assetDetail.key}'].ipAddress" id="${assetDetail.key}_ipAddress"/></td>
										<td><label><spring:message code="fleetmanagement.headers.contactDevice"/></label></td>
										<td><form:input path="assetDetailsMap['${assetDetail.key}'].assetContact.firstName" id="${assetDetail.key}_contactName"/></td>
										<c:choose>	
											<c:when test="${pageFlow eq 'Decommission'}">
												<td><label><spring:message code="multiAssetInfo.header.pickupFrom"/>:</label></td>
											</c:when>
											<c:otherwise>
												<td><label><spring:message code="fleetmanagement.headers.moveTo"/>:</label></td>
											</c:otherwise>
									</c:choose>
										<td><label><spring:message code="fleetmanagement.headers.building"/> </label><label id="${assetDetail.key}_bldngMandat" class="bldngMandat">*</label></td>
										<td colspan="2">
											<select	id="${assetDetail.key}_buildingDD" class="buildingDD" astId="${assetDetail.key}">
												<option value=""><spring:message code='lbs.label.building'/></option>
											</select>
											<input type="text" id="${assetDetail.key}_buildingTT" name="${assetDetail.key}_buildingTT" 
												class="buildingTT" maxlength="100"/>
										</td>
									</tr>
									<tr>
										<td><label><spring:message code="serviceRequest.listHeader.Model"/></label></td>
										<td>${assetDetail.value.productTLI}</td>
										<td><label><spring:message code="meterRead.label.hostName"/></label></td>
										<td><form:input path="assetDetailsMap['${assetDetail.key}'].hostName" id="${assetDetail.key}_hostName"/></td>
										<td><label><spring:message code="fleetmanagement.headers.contactEmailAddress"/></label></td>
										<td><form:input path="assetDetailsMap['${assetDetail.key}'].assetContact.emailAddress" id="${assetDetail.key}_contactEmail"/></td>
										<td></td>
										<td><label><spring:message code="fleetmanagement.headers.floor"/> </label><label id="${assetDetail.key}_flrMandat" class="flrMandat">*</label></td>
										<td colspan="2">
											<select	id="${assetDetail.key}_floorDD" class="floorDD" astId="${assetDetail.key}">
												<option value=""><spring:message code='lbs.label.floor'/></option>
											</select>
											<input type="text" id="${assetDetail.key}_floorTT" name="${assetDetail.key}_floorTT" 
												class="floorTT" maxlength="100"/>
										</td>
										
									</tr>
									<tr>
										<td colspan="2" rowspan="3">
											<img src="" id="${assetDetail.key}_assetImg"/>
										</td>
										<td><label><spring:message code="fleetmanagement.headers.customerTag"/></label></td>
										<td><form:input path="assetDetailsMap['${assetDetail.key}'].deviceTag" id="${assetDetail.key}_deviceTag"/></td>
										<td><label><spring:message code="fleetmanagement.headers.contactPhone"/></label></td>
										<td><form:input path="assetDetailsMap['${assetDetail.key}'].assetContact.workPhone" id="${assetDetail.key}_contactPhone"/></td>
										
										<td></td>
										<td><label><spring:message code="lbs.label.zone"/> </label><label id="${assetDetail.key}_zoneMandat" class="zoneMandat">*</label></td>
										<td colspan="2">
											<select	id="${assetDetail.key}_zoneDD" class="zoneDD" astId="${assetDetail.key}">
												<option value=""><spring:message code='lbs.label.zone'/></option>
											</select>
											<input type="text" id="${assetDetail.key}_zoneTT" name="${assetDetail.key}_zoneTT" 
												class="zoneTT" maxlength="100"/>
										</td>
									   
									</tr>
									<tr>
										<td><label><spring:message code="lbs.label.costcentre"/></label></td>
										<td><form:input path="assetDetailsMap['${assetDetail.key}'].assetCostCenter" id="${assetDetail.key}_assetCostCenter"/></td>
										<td><label><spring:message code="changemanagement.common.heading.chl"/></label></td>
										<td>
										<span id="chlNodeValueLabel_${assetDetail.key}">${assetDetail.value.chlNodeValue}</span>
										<span id="chlNodeValueChangeLbl_${assetDetail.key}">
                    						<span><a class="link"id="chlTreeLink" href="<%=showCHLTreePopUp%>" 
												onclick="showOverlay();return popUpChlTree('${assetDetail.key}');" title="<spring:message code='link.customerHierarchy'/>">
													<spring:message code="changemanagement.common.option.select"/>
												    	<br />
												   	<!--<c:choose>
														<c:when test="${assetDetail.value.chlNodeValue!=null}">
												   		<spring:message code="requestInfo.cm.manageAsset.link.changeHeirerchyLevel"/>
												    	<br />
														</c:when>    
													<c:otherwise>
												   		<spring:message code="changemanagement.common.option.select"/>
												    	<br />
													</c:otherwise>
													</c:choose>-->
														
												</a></span>
										</span> 
										</td>
										<td></td>
										<td><label><spring:message code="fleetmanagement.headers.office"/></label></td>
										<td><form:input path="assetDetailsMap['${assetDetail.key}'].installAddress.physicalLocation3" id="${assetDetail.key}_physicalLocation3" class="assetOfficeName"/></td>
										
									</tr>
									<tr>
										<td colspan="2"><a style="cursor: pointer;" onClick="openPopUpPg('${assetDetail.key}', '${assetDetail.value.serialNumber}', '${assetDetail.value.ipAddress}', '${assetDetail.value.productTLI}', '${assetDetail.value.deviceTag}')"><spring:message code="lbs.label.updatepagecount"/></a></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td colspan="2">
											<span id="${assetDetail.key}_gridLi" style="display: none;" class="assetGridLi">
												<a href="#" id="${assetDetail.key}_assetGridLink" class="assetGridLink" assetIndicator="${assetDetail.key}"><spring:message code="fleetmanagement.headers.viewGrid"/></a>
												<div id="assetXYLblDiv">
													<label id="assetXYLbl"><spring:message code="fleetmanagement.headers.grid"/> : </label><label id="${assetDetail.key}_assetCoords" class="assetCoords"></label>
												</div>
											</span>
										</td>
									</tr>
								</tbody>
							</table>
							
							<form:hidden path="assetDetailsMap['${assetDetail.key}'].serialNumber" id="${assetDetail.key}_serialNumber"/>
							<form:hidden path="assetDetailsMap['${assetDetail.key}'].productTLI" id="${assetDetail.key}_productTLI"/>
							
							<form:hidden path="assetDetailsMap['${assetDetail.key}'].chlNodeId" id="${assetDetail.key}_chlNodeId"/>
							<form:hidden path="assetDetailsMap['${assetDetail.key}'].chlNodeValue" id="${assetDetail.key}_chlNodeValue"/>
							
							<form:hidden path="assetDetailsMap['${assetDetail.key}'].installAddress.physicalLocation1" id="${assetDetail.key}_physicalLocation1" cssClass="assetBuildingName"/>
							<form:hidden path="assetDetailsMap['${assetDetail.key}'].installAddress.physicalLocation2" id="${assetDetail.key}_physicalLocation2" cssClass="assetFloorName"/>
							<form:hidden path="assetDetailsMap['${assetDetail.key}'].installAddress.zoneName" id="${assetDetail.key}_zoneName" cssClass="assetZoneName"/>
							
							<form:hidden path="assetDetailsMap['${assetDetail.key}'].installAddress.buildingId" id="${assetDetail.key}_physicalLocation1h" cssClass="assetBuildingId"/>
							<form:hidden path="assetDetailsMap['${assetDetail.key}'].installAddress.floorId" id="${assetDetail.key}_physicalLocation2h" cssClass="assetFloorId"/>
							<form:hidden path="assetDetailsMap['${assetDetail.key}'].installAddress.zoneId" id="${assetDetail.key}_zoneh" cssClass="assetZoneId"/>
							
							
							<form:hidden path="assetDetailsMap['${assetDetail.key}'].deviceName" id="${assetDetail.key}_deviceName"/>
							
							<form:hidden path="assetDetailsMap['${assetDetail.key}'].installAddress.coordinatesXPreDebriefRFV" id="${assetDetail.key}_addresscoordinatesXPreDebriefRFV" cssClass="addresscoordinatesXPreDebriefRFV"/>
							<form:hidden path="assetDetailsMap['${assetDetail.key}'].installAddress.coordinatesYPreDebriefRFV" id="${assetDetail.key}_addresscoordinatesYPreDebriefRFV" cssClass="addresscoordinatesYPreDebriefRFV"/>
							
							<form:hidden path="assetDetailsMap['${assetDetail.key}'].assetContact.middleName" id="${assetDetail.key}_contactMiddleName"/>
							<form:hidden path="assetDetailsMap['${assetDetail.key}'].assetContact.lastName" id="${assetDetail.key}_contactLastName"/>		
						</div>
					<hr/>
			</c:forEach>
		</div>
	</div>
</div>

<script type="text/javascript">
var pageFlow = "${pageFlow}";
var isMultiSelect = true;

var multiAssetList = "${multiAssetList}";
//alert("multiAssetList1111::"+multiAssetList);
var assetArr = multiAssetList.split(",");

var installAddressFlag=false;
	var moveAddressFlag=false;
	var linkId;
		//for common section
		var lat=document.getElementById("installAddresslatitude").value;
		//alert("lat"+lat);
		var lon=document.getElementById("installAddresslongitude").value;
		//alert("lon"+lon);
		
		if(assetArr.length > 0)
		{
			for(var ind=0; ind <assetArr.length; ind++)
			{		
				coordinates($("#"+assetArr[ind]+"_addresscoordinatesXPreDebriefRFV").val(),$("#"+assetArr[ind]+"_addresscoordinatesYPreDebriefRFV").val(),assetArr[ind]);
			}
		}
		
		jQuery("#installStoreFrontNameLbl").html(document.getElementById("installStoreFrontName").value);
		jQuery("#installAddressNameLbl").html(document.getElementById("installAddressName").value);
		document.getElementById("installAddressLine1Lbl").innerHTML=document.getElementById("installAddressLine1").value;
		//For LBS dropdowns
		var curURL = window.location.href;
		if(curURL.indexOf('/fleet-management') != -1)
		{
			jQuery("#selectLocalAddress1").hide();
			jQuery("#selectLocalAddress").hide();

			if(pageFlow.toLowerCase() === "fleetmgmtmove" || pageFlow.toLowerCase() === "decommission")
			{
				$("#diffAddressLink").hide();	
			}	

			showBuildingFloorZone("install");

			if(pageFlow.toLowerCase() === "fleetmgmtchange")
			{
				jQuery("#selectLocalAddress").hide();
			}
				
			/*if(document.getElementById("installLBSAddressFlag").value !="" && document.getElementById("installLBSAddressFlag").value !=null && document.getElementById("installLBSAddressFlag").value =="true"){
				alert("install");
				jQuery("#selectLocalAddress1").hide();
				jQuery("#selectLocalAddress").show();
				showBuildingFloorZone("install");
				
				/*if(pageFlow.toLowerCase() === "fleetmgmtmove" || pageFlow.toLowerCase() === "decommission")
				{
					jQuery("#selectLocalAddress1").hide();
					jQuery("#selectLocalAddress").show();
				}
				if(pageFlow.toLowerCase() === "fleetmgmtchange")
				{
					jQuery("#selectLocalAddress").hide();
				}
				
				//var addressId=document.getElementById("installAddressId").value;				
				//loadCoutrySateCitySiteBuilding(addressId);
			}
			
			if(document.getElementById("installLBSAddressFlag").value =='' || document.getElementById("installLBSAddressFlag").value ==null || document.getElementById("installLBSAddressFlag").value =="false"){
				document.getElementById("installLBSAddressFlag").value="false";
				
				jQuery("#selectLocalAddress1").show();
				jQuery("#selectLocalAddress").hide();
				lbsInstall();
				lbsFloor();
				lbsZone();
				lbsSite();
			}*/

			/*if(pageFlow.toLowerCase() === "fleetmgmtmove" || pageFlow.toLowerCase() === "decommission")
			{*/
				$("#physicalLocation1").attr("disabled", true);
				$("#physicalLocation2").attr("disabled", true);
				$("#physicalLocation3").attr("disabled", true);

				$("#bldng").attr("disabled", true);
				$("#flr").attr("disabled", true);
				$("#zone").attr("disabled", true);
				$("#office").attr("disabled", true);
			//}
		}
		else
		{
			/* Addded for LBS 1.5- LOD Checking for Installed Address */
			//showBuildingFloorZone("install");
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
			
			if(!('${isBackFromConfirm}' != null && '${isBackFromConfirm}' == "true"))
			{
				jQuery(".assetOfficeName").val(document.getElementById("moveTophysicalLocation3h").value);
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

		$("#moveToAddressLink").show();
		showBuildingFloorZone("moveToAddress");
	}

	if(assetArr.length > 0)
	{
		for(var ind=0; ind <assetArr.length; ind++)
		{
			//alert("assetArr[ind]:::"+assetArr[ind]);
			$("#"+assetArr[ind]+"_assetGridLink").click(function(){
				//alert("#"+assetArr[ind]+"_buildingDD");
				//alert($("#"+assetArr[ind]+"_buildingDD").val());
				//alert($("#"+assetArr[ind]+"_floorDD").val());

				var astId = $(this).attr("assetIndicator");
				openPopupMap(astId,'${mdmId}','${mdmLevel}','${formPost}','${emailAddress}',$("#"+astId+"_buildingDD").val(),$("#"+astId+"_floorDD").val());
			});
		}
	}
	
	function popUpChlTree(assetId)
	{
		jQuery('#dialogChlTree').load(jQuery('#chlTreeLink').attr('href'),function(){
			
			var heightDoc=jQuery(window).height()<630?630:jQuery(window).height();		
			var divht=heightDoc-150;		
			jQuery('#chl_node_tree_container').css('width','100%');
			jQuery('#chl_node_tree_container').css('height',divht+'px');	
			dialog=jQuery('#content_chlTree').dialog({
			autoOpen: false,
			title: jQuery('#chlTreeLink').attr('title'),
			modal: true,
			draggable: false,
			resizable: false,		
			width: 400,
			height: heightDoc-80,
			position: 'top',
			close: function(event,ui){
					dialog=null;
					jQuery('#content_chlTree').remove();
					//This section is to hide the select hierarchy level link	
					if(jQuery("#"+assetId+"_chlNodeValue").val()!=""){
						showHiererchy(assetId);
					} 
				}
		});
		jQuery(document).scrollTop(0);
		dialog.dialog('open');
		hideOverlay();

		if (window.PIE) {
			 	document.getElementById("button_cancel_chl").fireEvent("onmove");
			   	document.getElementById("button_chl").fireEvent("onmove");
			}
		
		initialiseCHLTree(assetId);		
	});
		
	return false;
	};

	function closeDialog()
	{
			jQuery("#chlNodeValueLabel_"+dynAssetId).html(cValue);
			dialog.dialog('close');
			dialog=null;
			jQuery('#dialog').html("");
	}

	function showHiererchy(assetId) {
		jQuery('#chlNodeValueChangeLbl_'+assetId).show();
		
	};
	
	
	$('.buildingDD').change(function(){
		
		var assetId = $(this).attr("astId");
		$("#"+assetId+"_floorDD").html('<option value=""><spring:message code="lbs.label.floor"/></option>');

		$("#"+assetId+"_physicalLocation1").val(jQuery("#"+assetId+"_buildingDD option:selected").text());
		$("#"+assetId+"_physicalLocation1h").val(jQuery("#"+assetId+"_buildingDD option:selected").val());

		var addressLevelOfDetails = "";
		if(pageFlow.toLowerCase() === "fleetmgmtmove")
		{
			addressLevelOfDetails = $.trim($("#moveToAddressLevelOfDetails").val());
		}
		else if(pageFlow.toLowerCase() === "fleetmgmtchange" || pageFlow.toLowerCase() === "decommission")
		{	
			addressLevelOfDetails = $.trim($("#installLevelOfDetails").val());
		}
		
		if(jQuery("#"+assetId+"_buildingDD option:selected").val() == ""){
			if(addressLevelOfDetails.toLowerCase() === "mix - see floor")
			{
				$("#"+assetId+"_gridLi").hide();
				$("#"+assetId+"_assetCoords").html("");
				$("#"+assetId+"_addresscoordinatesXPreDebriefRFV").val("");
				$("#"+assetId+"_addresscoordinatesYPreDebriefRFV").val("");
			}
			return;
		}
		//urlParamsObj1.setDropParamsToObj();	
		$("#"+assetId+"_floorDD").attr('disabled',true);
		$('#btnContinue').attr('disabled',true);
		getAssetSite("buildingchange", assetId);
		getAssetFloor("buildingchange", assetId);
		
	});

	$('.floorDD').change(function(){
		
		var assetId = $(this).attr("astId");

		$("#"+assetId+"_physicalLocation2").val(jQuery("#"+assetId+"_floorDD option:selected").text());
		$("#"+assetId+"_physicalLocation2h").val(jQuery("#"+assetId+"_floorDD option:selected").val());
		//alert($("#moveToAddressLevelOfDetails").val());

		var addressLevelOfDetails = "";
		if(pageFlow.toLowerCase() === "fleetmgmtmove")
		{
			addressLevelOfDetails = $.trim($("#moveToAddressLevelOfDetails").val());
		}
		else if(pageFlow.toLowerCase() === "fleetmgmtchange" || pageFlow.toLowerCase() === "decommission")
		{	
			addressLevelOfDetails = $.trim($("#installLevelOfDetails").val());
		}
		
		if(addressLevelOfDetails.toLowerCase() === "mix - see floor")
		{
			$("#"+assetId+"_gridLi").hide();
			$("#"+assetId+"_assetCoords").html("");
			$("#"+assetId+"_addresscoordinatesXPreDebriefRFV").val("");
			$("#"+assetId+"_addresscoordinatesYPreDebriefRFV").val("");
			//alert("lod val::"+$("#flrm option:selected").attr("lod"));
			if($("#"+assetId+"_floorDD option:selected").attr("lod") != undefined &&
				$.trim($("#"+assetId+"_floorDD option:selected").attr("lod").toLowerCase()).indexOf("grid") > -1)
			{
				$("#"+assetId+"_gridLi").show();
			}
		}
		
		
	});
	
	function getImageFromServerForDevice(deviceName)
	{
		$.ajax({
			url:"${retrieveDeviceURLVar}"+"&t="+new Date().getTime(),
			type:'POST',
			dataType:"JSON",
			data:{deviceId:deviceName},
			success: function(array){
						for(var i=0;i<assetArr.length;i++){
							if(array==null)
								{
									$('#'+assetArr[i]+"_assetImg").attr('src','/LexmarkServicesPortal/images/dummy_noimg_2.jpg');
								}
							else
								{
									if(array[i]!="Not found")
									{
										
										$('#'+assetArr[i]+"_assetImg").attr('src',array[i]);
									}
									else
									{
										
										$('#'+assetArr[i]+"_assetImg").attr('src','/LexmarkServicesPortal/images/dummy_noimg_2.jpg').css('border','1px solid #a1a1a1');
									}
								}
							
							
						}
				},
		  failure: function(results){}
		});	
	}
</script>