<%@page import="com.lexmark.services.util.LexmarkSPOmnitureConstants"%>
<%@ page contentType="text/html" isELIgnored="false"%>
<%@ page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>
<%@ taglib uri="/WEB-INF/tld/liferay-portlet.tld" prefix="portlet"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="html" tagdir="/WEB-INF/tags/html"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
		<div class="infoBox columnInner rounded shadow" id="deInstallSection">
		 <div>
		 <ul class="form installCheck">
					  <li>
                        <label for="installAsset"><spring:message code="requestInfo.info.heading.installDeinstallConfirm"/></label>
						<span class="radio_confirm" id="deInstallAssetFlag">${manageAssetForm.deinstallAssetFlag}</span>
                      </li>
					</ul>
					</div>
				 
				 </br>
				
				  <div class="portletBlock infoBox clearBoth" id="deInstallInfo">
				  <h4><spring:message code="requestInfo.info.heading.deinstallAssetInfo"/></h4>
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
							<td id="deInstallSerialNumber">${manageAssetForm.assetDetail.deinstSerialNumber}</td>
							<td id="deInstallProductLine">${manageAssetForm.assetDetail.deinstModel}</td>
							<td id="deInstallBrand">${manageAssetForm.assetDetail.deinstBrand}</td>
							<td id="deInstallPartNo">${manageAssetForm.assetDetail.deinstPartNumber}</td>
							<td id="deInstallHostName">${manageAssetForm.assetDetail.deinstHostName}</td>
							<td id="deInstallAssetTag">${manageAssetForm.assetDetail.deinstAssetTag}</td>
							<td id="deInstallIpAddress">${manageAssetForm.assetDetail.deinstIpAddress}</td>							
							<td id="deInstallComments">${manageAssetForm.assetDetail.deinstComments}</td>
							</tr>
							 </tbody>
						</table>
				  </div>
				  </div>
        </div>  
<script type="text/javascript">
if('${manageAssetForm.deinstallAssetFlag}' == 'no'){
	jQuery('#deInstallInfo').hide();
}else{
	jQuery('#deInstallInfo').show();
}
if('${manageAssetForm.installAssetFlag}' == 'no'){
	jQuery('#deInstallInfo').hide();
	jQuery('#deInstallSection').hide();
}else{
	jQuery('#deInstallSection').show();
}

</script>           