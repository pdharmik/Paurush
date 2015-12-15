<%@ page import="com.lexmark.services.util.LexmarkSPOmnitureConstants"%>
<%@ page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>
<%@ page contentType="text/html" isELIgnored="false"%>
<%@ taglib prefix="portlet" uri="/WEB-INF/tld/liferay-portlet.tld"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="html" tagdir="/WEB-INF/tags/html"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/jsp/includeDatePicker.jsp"%>
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
	#productType{
		width:auto !important;
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
			<h4>Device Information to be added</h4>

			<c:forEach items="${manageAssetForm.assetDetailsList}" var="assetInfo" varStatus="assetDetail">
				<c:if test="${assetInfo.assetId ne null}">
				<c:set var="astNbr" value="${assetDetail.index}" />
				<div class="asset-included">
							<table class="asset" id="asset_${assetDetail.index}">
								<tbody>
									<tr>
										<td><label>Serial #</label></td>
										<td><form:input path="assetDetailsList[${assetDetail.index}].serialNumber" 
											id="${assetDetail.index}_serialNumber" onchange="getProductModel('normalFlow', ${assetDetail.index});"/></td>
										<td><label>IP Address</label></td>
										<td><form:input path="assetDetailsList[${assetDetail.index}].ipAddress" id="${assetDetail.index}_ipAddress"/></td>
										<td style="text-align: right; padding-right: 20px;"><label>Move to:</label></td>
										<td><label>Building </label><label id="${assetDetail.index}_bldngMandat" class="bldngMandat">*</label></td>
										<td colspan="2">
											<select	id="${assetDetail.index}_buildingDD" class="buildingDD" astId="${assetDetail.index}">
												<option value=""><spring:message code='lbs.label.building'/></option>
											</select>
											<input type="text" id="${assetDetail.index}_buildingTT" name="${assetDetail.index}_buildingTT" 
												class="buildingTT" maxlength="100"/>
										</td>
									</tr>
									<tr>
											<td>
												<label for="productName">
													<spring:message code="requestInfo.assetInfo.label.productName"/>
												</label>
											</td>
											<td>
												<span id="${assetDetail.index}_showProductModel">
													<select id="${assetDetail.index}_productType" onchange="showEnterProduct('${assetDetail.index}');">
														<option value="">
															<spring:message code="requestInfo.option.select"/>
														</option>
													</select>
												</span>
												<span id="${assetDetail.index}_productModelLoading" class="treeLoading" style="padding:0!important;display:none;width:auto!important;float:left;">
													<img src="<html:imagesPath/>loading-icon.gif"/>
												</span>	
												<span style="width:auto!important;padding:0!important;">
													<img class="helpIcon ui_icon_sprite info-icon" src="<html:imagesPath/>transparent.png" title="<spring:message code="requestInfo.title.Product"/>" >
												</span>
											</td>
										<td><label>Host Name</label></td>
										<td><form:input path="assetDetailsList[${assetDetail.index}].hostName" id="${assetDetail.index}_hostName"/></td>
										
										<td></td>
										<td><label>Floor </label><label id="${assetDetail.index}_flrMandat" class="flrMandat">*</label></td>
										<td colspan="2">
											<select	id="${assetDetail.index}_floorDD" class="floorDD" astId="${assetDetail.index}">
												<option value=""><spring:message code='lbs.label.floor'/></option>
											</select>
											<input type="text" id="${assetDetail.index}_floorTT" name="${assetDetail.index}_floorTT" 
												class="floorTT" maxlength="100"/>
										</td>
										
									</tr>
									<tr>
										<td>
											<label id="${assetDetail.index}_showEnterProductLabel" style="display: none;">
												<spring:message code="requestInfo.heading.enterProductModel"/>
											</label>
										</td>
										<td>
											<span>
												<input type="text" id="${assetDetail.index}_enterProduct" style="display: none;"/>
											</span>
										</td>
										<td><label>Customer Tag</label></td>
										<td><form:input path="assetDetailsList[${assetDetail.index}].deviceTag" id="${assetDetail.index}_deviceTag"/></td>
										<td></td>
										<td><label>Zone </label><label id="${assetDetail.index}_zoneMandat" class="zoneMandat">*</label></td>
										<td colspan="2">
											<select	id="${assetDetail.index}_zoneDD" class="zoneDD" astId="${assetDetail.index}">
												<option value=""><spring:message code='lbs.label.zone'/></option>
											</select>
											<input type="text" id="${assetDetail.index}_zoneTT" name="${assetDetail.index}_zoneTT" 
												class="zoneTT" maxlength="100"/>
										</td>
									   
									</tr>
									<tr>
										<td>
											<label>CHL</label>
										</td>
										<td>
											<span id="chlNodeValueLabel_${assetDetail.index}">${assetInfo.chlNodeValue}</span>
											<span id="chlNodeValueChangeLbl_${assetDetail.index}">
	                    						<span><a class="link"id="chlTreeLink" href="<%=showCHLTreePopUp%>" 
													onclick="showOverlay();return popUpChlTree(${assetDetail.index});" title="Customer Hierarchy">
														
													   	<c:choose>
															<c:when test="${assetInfo.chlNodeValue!=null}">
													   		<spring:message code="requestInfo.cm.manageAsset.link.changeHeirerchyLevel"/>
													    	<br />
															</c:when>    
														<c:otherwise>
													   		<spring:message code="changemanagement.common.option.select"/>
													    	<br />
														</c:otherwise>
														</c:choose>
															
													</a></span>
											</span> 
										</td>
										<td>
											<label for="installDate"><spring:message code="requestInfo.assetInfo.label.installDate"/></label>
										</td>
										<td>
											
							  			<span><input id="${assetDetail.index}_installDateInput" value="<util:dateFormat  value="${assetInfo.installDate}" timezoneOffset="${timezoneOffset}">
                						</util:dateFormat>" class="w100" maxlength="10" onchange="shwDateCommon('${assetDetail.index}_installDateInput','${assetDetail.index}_installDateDelete');" onblur="shwDateCommon('${assetDetail.index}_installDateInput','${assetDetail.index}_installDateDelete');"/>
								
								 		<img class="ui_icon_sprite calendar-icon" src="<html:imagesPath/>transparent.png" title="<spring:message code="requestInfo.title.date"/>" width="23" height="23"
										onclick="showCal('${assetDetail.index}_installDateInput', null, null, false);" />
								
										<img id="${assetDetail.index}_installDateDelete" class="ui_icon_sprite ui-icon-closethick" src="<html:imagesPath/>transparent.png" onClick="removeDateCommon('${assetDetail.index}_installDateInput', '${assetDetail.index}_installDateDelete');" style="display:none;"/>					
										</span>
										</td>
										<td><a href="javascript:addaDevice();">Add one more device</a></td>
										<td><label>Office</label></td>
										<td><form:input path="assetDetailsList['${assetDetail.index}'].installAddress.physicalLocation3" id="${assetDetail.index}_physicalLocation3" class="assetOfficeName"/></td>
										
									</tr>
									<tr>
										<td colspan="4"></td>
										
										<td><a href="javascript:removeaDevice('${assetDetail.index}');" class="deleteAsset">Remove this asset</a></td>
										<td></td>
										<td colspan="2">
											<span id="${assetDetail.index}_gridLi" style="display: none;" class="assetGridLi">
												<a href="#" id="${assetDetail.index}_assetGridLink" class="assetGridLink" assetIndicator="${assetDetail.index}">View Grid</a>
												<div id="assetXYLblDiv">
													<label id="assetXYLbl">Grid X/Y : </label><label id="${assetDetail.index}_assetCoords" class="assetCoords"></label>
												</div>
											</span>
										</td>
									</tr>
								</tbody>
							</table>
							<hr id="hrtag_${assetDetail.index}"/>
							<input type="hidden" name="assetDetailsList[${assetDetail.index}].assetId" id="assetId_${assetDetail.index}" value="${assetDetail.index}"/>
							<div id="multiAddContent_${assetDetail.index}">
							
							<form:hidden path="assetDetailsList[${assetDetail.index}].chlNodeId" id="${assetDetail.index}_chlNodeId"/>
							<form:hidden path="assetDetailsList[${assetDetail.index}].chlNodeValue" id="${assetDetail.index}_chlNodeValue"/>
							
							<form:hidden path="assetDetailsList[${assetDetail.index}].installAddress.physicalLocation1" id="${assetDetail.index}_physicalLocation1" cssClass="assetBuildingName"/>
							<form:hidden path="assetDetailsList[${assetDetail.index}].installAddress.physicalLocation2" id="${assetDetail.index}_physicalLocation2" cssClass="assetFloorName"/>
							<form:hidden path="assetDetailsList[${assetDetail.index}].installAddress.zoneName" id="${assetDetail.index}_zoneName" cssClass="assetZoneName"/>
							
							<form:hidden path="assetDetailsList[${assetDetail.index}].installAddress.buildingId" id="${assetDetail.index}_physicalLocation1h" cssClass="assetBuildingId"/>
							<form:hidden path="assetDetailsList[${assetDetail.index}].installAddress.floorId" id="${assetDetail.index}_physicalLocation2h" cssClass="assetFloorId"/>
							<form:hidden path="assetDetailsList[${assetDetail.index}].installAddress.zoneId" id="${assetDetail.index}_zoneh" cssClass="assetZoneId"/>
							
							
							<form:hidden path="assetDetailsList[${assetDetail.index}].productLine" id="${assetDetail.index}_productModel"/>
							<form:hidden path="assetDetailsList[${assetDetail.index}].installDate" id="${assetDetail.index}_installDate"/>
							<form:hidden path="assetDetailsList[${assetDetail.index}].deviceName" id="${assetDetail.index}_deviceName"/>
							
							<form:hidden path="assetDetailsList[${assetDetail.index}].installAddress.coordinatesXPreDebriefRFV" id="${assetDetail.index}_addresscoordinatesXPreDebriefRFV" cssClass="addresscoordinatesXPreDebriefRFV"/>
							<form:hidden path="assetDetailsList[${assetDetail.index}].installAddress.coordinatesYPreDebriefRFV" id="${assetDetail.index}_addresscoordinatesYPreDebriefRFV" cssClass="addresscoordinatesYPreDebriefRFV"/>
							</div>
									
						</div>
						</c:if>
			</c:forEach>
			<div id="multiAddContent" class="asset-included"></div>
			
			
		</div>
	</div>
</div>

<script type="text/javascript">
function hideShowDelete()
{
var count=$('.asset').length;
if(count==1)
	{
	$('.deleteAsset').hide();
	}
else
	{
	$('.deleteAsset').show();
	}
}
YUI().use('node-base', function (Y) {
	Y.on('domready', function () {
		createMultiAddTemplate();
	});
});
var assetIndex=${astNbr};
function addaDevice()
{
	assetIndex+=1;
	
	$('#multiAddContent').append(multiAddTemplate({index:assetIndex}));
	showBuildingFloorZoneForMultiAdd(assetIndex);
	$("#chlTreeLink").href="${showCHLTreePopUp}";
	/* var deviceNameList= "";
	deviceNameList=$("#"+assetIndex+"_deviceName").val(); 
	getImageFromServerForDevice(deviceNameList,assetIndex); */
	hideShowDelete();
	
	if(document.getElementById("physicalLocation3h").value !="" && document.getElementById("physicalLocation3h").value !=null){
		
		if(jQuery("#installLBSAddressFlag").val()=="true"  ||
				($("#installAddressId").val() != null && $.trim($("#installAddressId").val()).length > 0)){
			
			jQuery("#office").val(document.getElementById("physicalLocation3h").value);
			installAddressFlag=true;
		}else{
			
			jQuery("#physicalLocation3").val(document.getElementById("physicalLocation3h").value);
			
			installAddressFlag=true;
		}
		
		
		if(!('${isBackFromConfirm}' != null && '${isBackFromConfirm}' == "true"))
		{
			jQuery(".assetOfficeName").val(document.getElementById("physicalLocation3h").value);
		}
		}
}

function removeaDevice(assetNo)
{

	$("#asset_"+assetNo).remove();
	$("#hrtag_"+assetNo).remove();
	$('#multiAddContent_'+assetNo).remove();
	$('#assetId_'+assetNo).val('');
	hideShowDelete();
}

var pageFlow = "${pageFlow}";
var isMultiSelect = true;

var multiAssetList = "${multiAssetList}";
var astCounter = ${astNbr};
var assetArr = [];
for(var cr = 0; cr <= astCounter; cr++)
{
	assetArr.push(cr);
}
//alert("multiAssetList1111::"+assetArr);
//var assetArr = multiAssetList.split(",");

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
				coordinates($("#"+assetArr[ind]+"_addresscoordinatesXPreDebriefRFV").val(),$("#"+assetArr[ind]+"_addresscoordinatesYPreDebriefRFV").val());
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

			if(pageFlow.toLowerCase() === "fleetmgmtmove" || pageFlow.toLowerCase() === "addmultiple"|| pageFlow.toLowerCase() === "decommission")
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
		
		if(document.getElementById("physicalLocation3h").value !="" && document.getElementById("physicalLocation3h").value !=null){
			
			if(jQuery("#installLBSAddressFlag").val()=="true"  ||
					($("#installAddressId").val() != null && $.trim($("#installAddressId").val()).length > 0)){
				
				jQuery("#office").val(document.getElementById("physicalLocation3h").value);
				installAddressFlag=true;
			}else{
			
				jQuery("#physicalLocation3").val(document.getElementById("physicalLocation3h").value);
				
				installAddressFlag=true;
			}
		
			
			if(!('${isBackFromConfirm}' != null && '${isBackFromConfirm}' == "true"))
			{
				jQuery(".assetOfficeName").val(document.getElementById("physicalLocation3h").value);
			}
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
	
	
	$('.buildingDD').live("change", function(){
		
		var assetId = $(this).attr("astId");//alert("test::"+assetId);
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

	$('.floorDD').live("change", function(){
		
		var assetId = $(this).attr("astId");//alert("test::"+assetId);

		$("#"+assetId+"_physicalLocation2").val(jQuery("#"+assetId+"_floorDD option:selected").text());//alert("111");
		$("#"+assetId+"_physicalLocation2h").val(jQuery("#"+assetId+"_floorDD option:selected").val());//alert("333");
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
	
	
	function getProductModel(flowType,index){
		 jQuery('#'+index+'_enterProduct').val('');
		 jQuery('#'+index+'_productModelLoading').show();
		 jQuery('#'+index+'_showEnterProductLabel,#'+index+'_enterProduct').hide();
		var paymentTypeURL="<portlet:resourceURL id='getProductTypesMulti'/>";
				jQuery.ajax({
				url:paymentTypeURL,
				data:{
					serialNumber:jQuery('#'+index+'_serialNumber').val()
					 },
				type:'POST',
				dataType: 'html',
				success: function(results){
						 jQuery('#'+index+'_productModelLoading').hide();
					try{
						if(results !=null){
							
							jQuery('#'+index+'_productType').html(results);
							if(jQuery('#'+index+'_productType :selected').val()=="other"){
								jQuery('#'+index+'_showEnterProductLabel,#'+index+'_enterProduct').show();
								if(flowType=="backFlow"){
								 if(document.getElementById(index+'_productModel').value !='')
									{
										jQuery('#'+index+'_enterProduct').val(document.getElementById(index+'_productModel').value);
									}
								 
								}
							}else{
								jQuery('#'+index+'_showEnterProductLabel,#'+index+'_enterProduct').hide();
								if(flowType=="backFlow"||window.location.href.indexOf('/fleet-management')!= -1){
								
								if(document.getElementById(index+'_productModel').value !='')
									{
								
									jQuery('#'+index+'_productType').val(document.getElementById(index+'_productModel').value);
									}
								}
								}
						
					}
					}catch(e){
						//alert("in else");
						}
					
				}
			});	
	 }
	 function showEnterProduct(assetNumber){
		 
		 if(jQuery('#'+assetNumber+'_productType :selected').val()=="other"){
			 jQuery('#'+assetNumber+'_showEnterProductLabel,#'+assetNumber+'_enterProduct').show();
		}else{
			jQuery('#'+assetNumber+'_showEnterProductLabel,#'+assetNumber+'_enterProduct').hide();
			}

		 }
</script>