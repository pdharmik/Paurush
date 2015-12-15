<%@page import="com.lexmark.services.util.LexmarkSPOmnitureConstants"%>
<%@ page contentType="text/html" isELIgnored="false"%>
<%@ page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>
<%@ taglib uri="/WEB-INF/tld/liferay-portlet.tld" prefix="portlet"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="html" tagdir="/WEB-INF/tags/html"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
		<div class="infoBox columnInner rounded shadow" id="deInstallSection" style="display: none;">
		 <div>
		 <ul class="form installCheck">
							<li>
								 <label><%-- 
								 changed against defect# 1730
								 <spring:message code="requestInfo.tooltip.doYouRequireLexmarkToCollectThisAsset"/> --%>  
								 <spring:message code="requestInfo.info.heading.installDeinstallConfirm"/>								 
								 </label> 
								 <span class="radio radio_confirm">
								 <form:radiobutton name="deInstallAsset" path="deinstallAssetFlag" onclick="showDeinstall('deInstallYes');" value="yes" id="deInstallAssetYes"/>
								 <label>
								 <spring:message code="requestInfo.option.yes"/> </label> 
								 <form:radiobutton name="deInstallAsset" path="deinstallAssetFlag" onclick="showDeinstall('deInstallno');" value="no" id="deInstallAssetNo"/>
								 <label>
								 <spring:message code="requestInfo.option.no"/></label>
								 </span>
							</li>

						</ul>
					</div>
				 
				 </br>
				
				  <div class="portletBlock infoBox clearBoth" id="deInstallInfo" style="display: none;">
				  <h4><spring:message code="requestInfo.info.heading.deinstallAssetInfo"/></h4>
				  <div><span class="margin-left-1per">[<span class="req">*</span> <spring:message code="requestInfo.assetInfo.note"/>]</span></div>
				  <div class="displayGrid columnInnerTable rounded shadow" style="overflow: auto;">
				  <table>
						<thead>
						<tr>
						<th style="width: 12.5%"><spring:message code="requestInfo.assetInfo.label.serialNumber"/></th>
						<th style="width: 12.5%"><spring:message code="serviceRequest.label.productModel"/></th>
						<th style="width: 12.5%"><spring:message code="lbs.label.brand"/></th>
						<th style="width: 12.5%"><spring:message code="requestInfo.label.printerPart"/></th>
						<th style="width: 12.5%"><spring:message code="serviceRequest.listHeader.hostName"/></th>
						<th style="width: 12.5%"><spring:message code="lbs.label.assettag"/></th>
						<th style="width: 12.5%"><spring:message code="lbs.label.ipaddress"/></th>
						<th style="width: 12.5%"><spring:message code="requestInfo.label.comments"/></th>
						
						</tr>
						</thead>
							 <tbody>
							<tr>									
							<td ><form:input id="deInstallSerialNumber"  onchange="getProductModel('normalDeinstallFlow');" path="assetDetail.deinstSerialNumber"/></td>
							<td><span id="showDeinstallProductModel" class="showProductModel" ><select id="deinstallProductType" onchange="showEnterProduct();"><option value=""><spring:message code="requestInfo.option.select"/></option></select></span>
							 <span id="deinstallProductModelLoading" class="treeLoading productModelLoading" ><img src="<html:imagesPath/>loading-icon.gif"/></span>	
							<span><input type="text" id="enterDeinstallProduct" style="display: none;"></input></span>
							
							</td>
							<td ><form:input id="deinstallbrand"  path="assetDetail.deinstBrand"/></td>
							<td ><form:input id="deinstallpartNo"  path="assetDetail.deinstPartNumber"/></td>
							<td ><form:input id="deinstallhostName"  path="assetDetail.deinstHostName"/></td>
							<td ><form:input id="deinstallAssetTag"  path="assetDetail.deinstAssetTag"/></td>						
							<td ><form:input id="deinstallipAddress"  path="assetDetail.deinstIpAddress"/></td>
							<td ><form:textarea id="deinstallcomment"  path="assetDetail.deinstComments"/></td>
							</tr>
							 </tbody>
						</table>
				  </div>
				  </div>
        </div>
        
<script type="text/javascript">
function showDeinstall(flag){
	if(flag=='installYes'){
		jQuery('#deInstallSection').show();
	}else if(flag=='installNo'){
		jQuery('#deInstallAssetNo').attr('checked', false);
		jQuery('#deInstallAssetYes').attr('checked', false);
		jQuery('#deInstallSection').hide();
	}

	if(flag=='deInstallYes'){
		jQuery('#deInstallInfo').show();
	}else if(flag=='deInstallno'){
		jQuery('#deInstallInfo').hide();
	}
}

function decomissionFieldCheck(){
	
	if(jQuery('#deInstallAssetYes').is(':checked')){
		
		if(jQuery('#deInstallSerialNumber').val().trim() == "" && jQuery('#deinstallAssetTag').val().trim() == "" && jQuery('#deinstallipAddress').val().trim() == "" && jQuery('#deinstallbrand').val().trim() == "" && jQuery('#deinstallpartNo').val().trim() == "" && jQuery('#deinstallhostName').val().trim() == "" && jQuery('#deinstallcomment').val().trim() == "" ){
			
			jQuery("#errorDiv").append("<li><strong>"+"<spring:message code='validation.asset.installDeinstall.nodata'/>" 
					+ "</strong></li>");
			jQuery('#errorDiv').show();
			jQuery(document).scrollTop(0);
			return false;
		}else{
			return true;
		}			
	}else {
		return true;
	}
	
	
}
</script>        