<%@ include file="/WEB-INF/jsp/include.jsp"%>
<script type="text/javascript" src='<html:rootPath/>js/advancedSearch.js'></script>
<script type="text/javascript" src='<html:rootPath/>js/validation.js'></script>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/in-styles.css?version=<html:fileCacheVersion/>" />
<!-- TODO need to update based on upcoming screen in future   -->
<div id="divAdvancedSearch">
<table width="100%" border="0" height="80%">
	<tr>
		<td colspan="2" align="center" class='orangeSectionTitles'><spring:message code="meterRead.title.advancedFilterOption"/></td>
    </tr>
    <tr>
    	<td colspan="2" align="left"><label id="lbValidationMsg" class="color-red"></label></td>
    </tr>
    <tr>
        <td width="60%" align="left"><spring:message code="meterRead.listHeader.serialNumber" /></td>
        <td width="40%" align="left"><spring:message code="meterRead.label.productModel" /></td>
	</tr>
	<tr>
        <td width="60%" align="left"><input type="text" name="serialNumber" id="txtSerialNumber"></td>
        <td width="40%" align="left"><input type="text" name="modelNumber" id="txtModelNumber"></td>
	</tr>
    <tr>
        <td width="60%" align="left"><spring:message code="meterRead.label.uniqueAssetTag"/></td>
        <td width="40%" align="left"><spring:message code="meterRead.label.productLine"/></td>
	</tr>
	<tr>
        <td width="60%" align="left"><input type="text" name="assetTag" id="txtAssetTag"></td>
        <td width="40%" align="left"><input type="text" name="productLine" id="txtProductLine"></td>
	</tr>
    <tr>
        <td width="60%" align="left"><spring:message code="meterRead.label.customerAssetTag"/></td>
        <td width="40%" align="left"><spring:message code="meterRead.label.color" /></td>
	</tr>
	<tr>
        <td width="60%" align="left"><input type="text" name="deviceTag" id="txtDeviceTag"></td>
        <td width="40%" align="left"><input type="checkbox" name="colorCapableFlag" id="txtColorCapableFlag"></td>
	</tr>
	<tr height="30"></tr>
    <tr>
        <td width="60%" align="left"><spring:message code="meterRead.label.installedAddress"/></td>
        <td width="40%" align="left"><spring:message code="meterRead.label.physicalLocation1"/></td>
	</tr>
	<tr>
        <td width="60%" align="left"><input type="text" name="installAddress.addressName" id="txtInstallAddressAddressName"></td>
        <td width="40%" align="left"><input type="text" name="physicalLocation1" id="txtPhysicalLocation1"></td>
	</tr>
    <tr>
        <td width="60%" align="left"><spring:message code="meterRead.label.installedCity"/></td>
        <td width="40%" align="left"><spring:message code="meterRead.label.physicalLocation2"/></td>
	</tr>
	<tr>
        <td width="60%" align="left"><input type="text" name="installAddress.city" id="txtInstallAddressCity"></td>
        <td width="40%" align="left"><input type="text" name="physicalLocation2" id="txtPhysicalLocation2"></td>
	</tr>
    <tr>
        <td width="60%" align="left"><spring:message code="meterRead.label.installedState"/></td>
        <td width="40%" align="left"><spring:message code="meterRead.label.physicalLocation3"/></td>
	</tr>
	<tr>
        <td width="60%" align="left">
        	<select name="installAddress.state" id="state">
				<option value="" > </option>
				<c:forEach items="${advancedSearchForm.states}" var="state" varStatus="loop">
					<option value="${state}" > ${state} </option>
				</c:forEach>
		 	</select>
		</td>
        <td width="40%" align="left"><input type="text" name="physicalLocation3" id="txtPhysicalLocation3"></td>
	</tr>
	 <tr>
        <td width="40%" align="left"><spring:message code="serviceRequest.listHeader.country"/></td>
        <td width="60%" align="left"></td>
	</tr>
	<tr>
        <td width="40%" align="left">
		 <select name="installAddress.country" id="InstalledCountry">
		 <option value="" > </option>
			<c:forEach items="${advancedSearchForm.countries}" var="installedCountry" varStatus="loop">
				<option value="${installedCountry}" > ${installedCountry} </option>
			</c:forEach>
		</select>
		</td>
		<td width="60%" align="left"></td>
	</tr>
	<tr>
        <td width="40%" align="left"><spring:message code="meterRead.label.hostName"/></td>
        <td width="60%" align="left"></td>
	</tr>
	<tr>
        <td width="40%" align="left"><input type="text" name="hostName" id="txtHostName" /></td>
		<td width="60%" align="left"></td>
	</tr>
	<tr>
        <td width="40%" align="left"><spring:message code="meterRead.listHeader.ipAddress"/></td>
        <td width="60%" align="left"></td>
	</tr>
	<tr>
        <td width="40%" align="left"><input type="text" name="ipAddress" id="txtIpAddress" /></td>
		<td width="60%" align="left"></td>
	</tr>
	<tr>
        <td width="60%" align="left">&nbsp;</td>
        <td width="40%" align="left">&nbsp;</td>
	</tr>
	<tr>
        <td width="60%" align="left">&nbsp;</td>
        <td width="40%" align="left">
        <button name="btnSubmit" class="button" onClick=" if(submitSearch()){Liferay.Popup.close(this)};"><spring:message code="button.search"/></button>&nbsp;
        <button name="btnCancel" class="button_cancel" onClick="Liferay.Popup.close(this);"><spring:message code="button.cancel"/></button></td>
	</tr>
</table>
</div>
<script type="text/javascript">
    function submitSearch(){
  	  callOmnitureAction('Page Counts', 'Page Count Advanced Search');
		clearValidationMsg();
		clearParentSearchCriteria();
		if(validateSearchCriteria()){
			var searchCriteria="";
			searchCriteria = populateSearchCriterias();
			window.parent.window.searchCriteria = searchCriteria;
			window.parent.window.reloadGrid();
			return true;
		}
		return false;
	}
	
    function clearParentSearchCriteria(){
		window.parent.window.document.getElementById("txtSearchValue").value="";
    }
    function clearValidationMsg(){
    	document.getElementById("lbValidationMsg").innerHTML = "";
    }
    
    function validateSearchCriteria(){ 
    	var srNmbr = document.getElementById("txtSerialNumber").value.trim();
    	if(srNmbr != "" && srNmbr.length != 7 && srNmbr.length != 11 && srNmbr.length != 13){
    		document.getElementById("lbValidationMsg").innerHTML = "<spring:message code='validation.error.invalidSerialNumber'/>";
    		return false;
    	}
    	return true;
    }

    
</script>