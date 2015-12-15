
<%@ page import="com.lexmark.services.util.LexmarkSPOmnitureConstants"%>
<%@ page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="html" tagdir="/WEB-INF/tags/html"%>
<%@ page contentType="text/html" isELIgnored="false"%>
<%@ taglib prefix="portlet" uri="/WEB-INF/tld/liferay-portlet.tld"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="html" tagdir="/WEB-INF/tags/html"%>
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
</style>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/mps.css"/>
<script id="list-multiAdd-template" type="text/x-handlebars-template">
      <table class="asset" id="asset_{{index}}">
								<tbody>
									<tr>
										<td><label>Serial #</label></td>
										<td><input type="text" name="assetDetailsList[{{index}}].serialNumber"  onchange="getProductModel('normalFlow','{{index}}');" id="{{index}}_serialNumber"/></td>
										<td><label>IP Address</label></td>
										<td><input type="text" name="assetDetailsList[{{index}}].ipAddress" id="{{index}}_ipAddress"/></td>
										<td  style="text-align: right; padding-right: 20px;"><label>Move to:</label></td>
										<td><label>Building </label><label id="{{index}}_bldngMandat" class="bldngMandat">*</label></td>
										<td colspan="2">
											<select	id="{{index}}_buildingDD" class="buildingDD" astId="{{index}}">
												<option value=""><spring:message code='lbs.label.building'/></option>
											</select>
											<input type="text" id="{{index}}_buildingTT" name="{{index}}_buildingTT" 
												class="buildingTT" maxlength="100"/>
										</td>
									</tr>
									<tr>
										<td>
											<label for="productName">
													<spring:message code="requestInfo.assetInfo.label.productName"/>
												</label>
										</td>
										<td><span id="{{index}}_showProductModel">
													<select id="{{index}}_productType" onchange="showEnterProduct('{{index}}');">
														<option value="">
															<spring:message code="requestInfo.option.select"/>
														</option>
													</select>
												</span>
												<span id="{{index}}_productModelLoading" class="treeLoading" style="padding:0!important;display:none;width:auto!important;float:left;">
													<img src="<html:imagesPath/>loading-icon.gif"/>
												</span>	
												<span style="width:auto!important;padding:0!important;">
													<img class="helpIcon ui_icon_sprite info-icon" src="<html:imagesPath/>transparent.png" title="<spring:message code="requestInfo.title.Product"/>" >
												</span></td>
										<td><label>Host Name</label></td>
										<td><input type="text" name="assetDetailsList[{{index}}].hostName" id="{{index}}_hostName"/></td>
										<td></td>
										<td><label>Floor </label><label id="{{index}}_flrMandat" class="flrMandat">*</label></td>
										<td colspan="2">
											<select	id="{{index}}_floorDD" class="floorDD" astId="{{index}}">
												<option value=""><spring:message code='lbs.label.floor'/></option>
											</select>
											<input type="text" id="{{index}}_floorTT" name="{{index}}_floorTT" 
												class="floorTT" maxlength="100"/>
										</td>
										
									</tr>
									<tr>
										<td>
											<label id="{{index}}_showEnterProductLabel" style="display: none;">
												<spring:message code="requestInfo.heading.enterProductModel"/>
											</label>
										</td>
										<td>
											<span>
												<input type="text" id="{{index}}_enterProduct" style="display: none;"/>
											</span>
										</td>
										<td><label>Customer Tag</label></td>
										<td><input type="text" name="assetDetailsList[{{index}}].deviceTag" id="{{index}}_deviceTag"/></td>
										<td></td>
										<td><label>Zone </label><label id="{{index}}_zoneMandat" class="zoneMandat">*</label></td>
										<td colspan="2">
											<select	id="{{index}}_zoneDD" class="zoneDD" astId="{{index}}">
												<option value=""><spring:message code='lbs.label.zone'/></option>
											</select>
											<input type="text" id="{{index}}_zoneTT" name="{{index}}_zoneTT" 
												class="zoneTT" maxlength="100"/>
										</td>
									   
									</tr>
									<tr>
										<td><label>CHL</label></td>
										<td>
										<span id="chlNodeValueLabel_{{index}}">${assetDetail.value.chlNodeValue}</span>
										<span id="chlNodeValueChangeLbl_{{index}}">
                    						<span><a class="link"id="chlTreeLink" href="" 
												onclick="showOverlay();return popUpChlTree('{{index}}');" title="Customer Hierarchy">
													
												   	<c:choose>
														<c:when test="${assetDetail.value.chlNodeValue!=null}">
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
										<td><span><input id="{{index}}_installDateInput" value="" class="w100" maxlength="10" onchange="shwDateCommon('{{index}}_installDateInput','{{index}}_installDateDelete');" onblur="shwDateCommon('{{index}}_installDateInput','{{index}}_installDateDelete');"/>
								
								 		<img class="ui_icon_sprite calendar-icon" src="<html:imagesPath/>transparent.png" title="<spring:message code="requestInfo.title.date"/>" width="23" height="23"
										onclick="showCal('{{index}}_installDateInput', null, null, false);" />
								
										<img id="{{index}}_installDateDelete" class="ui_icon_sprite ui-icon-closethick" src="<html:imagesPath/>transparent.png" onClick="removeDateCommon('{{index}}_installDateInput', '{{index}}_installDateDelete');" style="display:none;"/>
																
								</span>
										</td>
											
										<td><a href="javascript:addaDevice();">Add one more device</a></td>
										<td><label>Office</label></td>
										<td><input type="text" name="assetDetailsList[{{index}}].installAddress.physicalLocation3"   id="{{index}}_physicalLocation3"  class="assetOfficeName"/></td>
										
									</tr>
									<tr>
										<td colspan="4"></td>
										<td><a href="javascript:removeaDevice('{{index}}');" class="deleteAsset">Remove this asset</a></td>
										<td></td>
										<td colspan="2">
											<span id="{{index}}_gridLi" style="display: none;" class="assetGridLi">
												<a href="#" id="{{index}}_assetGridLink" class="assetGridLink" assetIndicator="{{index}}">View Grid</a>
												<div id="assetXYLblDiv">
													<label id="assetXYLbl">Grid X/Y : </label><label id="{{index}}_assetCoords" class="assetCoords"></label>
												</div>
											</span>
										</td>
									</tr>
								</tbody>
							</table>
	  						<hr id="hrtag_{{index}}"/>
                              <div id="multiAddContent_{{index}}">
							<input type="hidden" name="assetDetailsList[{{index}}].assetId" value="{{index}}"/>
							<input type="hidden" name="assetDetailsList[{{index}}].chlNodeId" id="{{index}}_chlNodeId"/>
							<input type="hidden" name="assetDetailsList[{{index}}].chlNodeValue" id="{{index}}_chlNodeValue"/>
							
							<input type="hidden" name="assetDetailsList[{{index}}].installAddress.physicalLocation1" id="{{index}}_physicalLocation1" cssClass="assetBuildingName"/>
							<input type="hidden" name="assetDetailsList[{{index}}].installAddress.physicalLocation2" id="{{index}}_physicalLocation2" cssClass="assetFloorName"/>
							<input type="hidden" name="assetDetailsList[{{index}}].installAddress.zoneName" id="{{index}}_zoneName" cssClass="assetZoneName"/>
							
							<input type="hidden" name="assetDetailsList[{{index}}].installAddress.buildingId" id="{{index}}_physicalLocation1h" cssClass="assetBuildingId"/>
							<input type="hidden" name="assetDetailsList[{{index}}].installAddress.floorId" id="{{index}}_physicalLocation2h" cssClass="assetFloorId"/>
							<input type="hidden" name="assetDetailsList[{{index}}].installAddress.zoneId" id="{{index}}_zoneh" cssClass="assetZoneId"/>
							
							<input type="hidden" name="assetDetailsList[{{index}}].productLine" id="{{index}}_productModel"/>
							<input type="hidden" name="assetDetailsList[{{index}}].installDate" id="{{index}}_installDate"/>
							<input type="hidden" name="assetDetailsList[{{index}}].deviceName" id="{{index}}_deviceName"/>
							
							<input type="hidden" name="assetDetailsList[{{index}}].installAddress.coordinatesXPreDebriefRFV" id="{{index}}_addresscoordinatesXPreDebriefRFV" cssClass="addresscoordinatesXPreDebriefRFV"/>
							<input type="hidden" name="assetDetailsList[{{index}}].installAddress.coordinatesYPreDebriefRFV" id="{{index}}_addresscoordinatesYPreDebriefRFV" cssClass="addresscoordinatesYPreDebriefRFV"/>		
                            </div>

</script>
<script type="text/javascript">

var multiAddTemplate;
function createMultiAddTemplate()
	{
	
	YUI().use('handlebars','node-base', function (Y) {
		
		if(Y.one('#list-multiAdd-template')!=null){
			
			multiAddTemplate = Y.Handlebars.compile(Y.one('#list-multiAdd-template').getHTML());		
		}
	});
	}
</script>
