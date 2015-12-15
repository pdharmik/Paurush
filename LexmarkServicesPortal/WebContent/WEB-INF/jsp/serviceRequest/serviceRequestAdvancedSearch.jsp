<%@ include file="/WEB-INF/jsp/include.jsp"%>
<script type="text/javascript" src='<html:rootPath/>js/advancedSearch.js'></script>
<script type="text/javascript" src='<html:rootPath/>js/validation.js'></script>
<div id="divAdvancedSearch">
<table width="100%" border="0" height="80%">
	<tr>
		<td colspan="2" align="center" class='orangeSectionTitles'><spring:message code="serviceRequest.title.advancedFilterOption"/></td>
    </tr>
    <tr>
    	<td colspan="2" align="left"><label id="lbValidationMsg" style="color: red"></label></td>
    </tr>
    <tr>
        <td width="60%" align="left"><spring:message code="serviceRequest.label.date"/></td>
        <td width="40%" align="left"><spring:message code="serviceRequest.label.referenceNumber"/></td>
	</tr>
	<tr>
        <td width="60%" align="left"><input type="text" name="serviceRequestDate" id="txtDate"></td>
        <td width="40%" align="left"><input type="text" name="referenceNumber" id="txtReferenceNumber"></td>
	</tr>
    <tr>
        <td width="60%" align="left"><spring:message code="serviceRequest.label.requestNumber"/></td>
        <td width="40%" align="left"><spring:message code="serviceRequest.label.serialNumber"/></td>
	</tr>
	<tr>
        <td width="60%" align="left"><input type="text" name="serviceRequestNumber" id="txtRequestNumber"></td>
        <td width="40%" align="left"><input type="text" name="asset.serialNumber" id="txtSerialNumber"></td>
	</tr>
    <tr>
        <td width="60%" align="left"><spring:message code="serviceRequest.label.contactFirstName"/></td>
        <td width="40%" align="left"><spring:message code="serviceRequest.label.contactLastName"/></td>
	</tr>
	<tr>
        <td width="60%" align="left"><input type="text" name="primaryContact.firstName" id="txtContactFirstName"></td>
        <td width="40%" align="left"><input type="text" name="primaryContact.lastName" id="txtContactLastName"></td>
	</tr>
    <tr>
        <td width="60%" align="left"><spring:message code="serviceRequest.label.uniqueAssetTag"/>?</td>
        <td width="40%" align="left"><spring:message code="serviceRequest.label.customerAssetTag"/></td>
	</tr>
	<tr>
        <td width="60%" align="left"><input type="text" name="asset.assetTag" id="txtUniqueAssetTag"></td>
        <td width="40%" align="left"><input type="text" name="TBD" id="txtCustomerAssetTag"></td>
	</tr>
    <tr>
        <td width="60%" align="left"><spring:message code="serviceRequest.label.contactPhone"/></td>
        <td width="40%" align="left"><spring:message code="serviceRequest.label.contactEmail"/></td>
	</tr>
	<tr>
        <td width="60%" align="left"><input type="text" name="primaryContact.workPhone" id="txtContactPhone"></td>
        <td width="40%" align="left"><input type="text" name="primaryContact.emailAddress" id="txtContactEmail"></td>
	</tr>
    <tr>
        <td width="60%" align="left">&nbsp;</td>
        <td width="40%" align="left">&nbsp;</td>
	</tr>
	<tr>
        <td width="60%" align="left"><spring:message code="serviceRequest.label.productModel"/></td>
        <td width="40%" align="left"><spring:message code="serviceRequest.label.productLine"/>?</td>
	</tr>
	<tr>
        <td width="60%" align="left"><input type="text" name="asset.productLine" id="txtProductModel"></td>
        <td width="40%" align="left"><input type="text" name="" id="txtProductLine"></td>
	</tr>
    <tr>
        <td width="60%" align="left"><spring:message code="serviceRequest.label.addressName"/></td>
        <td width="40%" align="left"><spring:message code="serviceRequest.label.addressLine1"/></td>
	</tr>
	<tr>
        <td width="60%" align="left"><input type="text" name="serviceAddress.AddressName" id="txtAddressName"></td>
        <td width="40%" align="left"><input type="text" name="serviceAddress.AddressLine1" id="txtAddressLine1"></td>
	</tr>
    <tr>
        <td width="60%" align="left"><spring:message code="serviceRequest.label.installedCountry"/></td>
        <td width="40%" align="left"><spring:message code="serviceRequest.label.state"/>?</td>
	</tr>
	<tr>
        <td width="60%" align="left">
        	<select name="asset.installAddress.country" id="InstalledCountry">
		 		<option value="" > </option>
					<c:forEach items="${advancedSearchForm.countries}" var="installedCountry" varStatus="loop">
						<option value="${installedCountry}" > ${installedCountry} </option>
					</c:forEach>
		 	</select>
        </td>
        <td width="40%" align="left">
        	<select name="asset.installAddress.state" id="state">
				<option value="" > </option>
				<c:forEach items="${advancedSearchForm.states}" var="state" varStatus="loop">
					<option value="${state}" > ${state} </option>
				</c:forEach>
		 	</select>
		 </td>	
	</tr>
    <tr>
        <td width="60%" align="left"><spring:message code="serviceRequest.label.province"/></td>
        <td width="40%" align="left"><spring:message code="serviceRequest.label.city"/></td>
	</tr>
	<tr>
        <td width="60%" align="left">
        	<select name="asset.installAddress.province" id="province">
        		<option value="" > </option>
				<c:forEach items="${advancedSearchForm.provinces}" var="province" varStatus="loop">
					<option value="${province}" > ${province} </option>
				</c:forEach>
		 	</select>
        </td>
        <td width="40%" align="left">
        	<input type="text" name="asset.installAddress.city" id="asset.installAddress.city">
		</td>
	</tr>
    <tr>
        <td width="60%" align="left">&nbsp;</td>
        <td width="40%" align="left">&nbsp;</td>
	</tr>
	<tr>
        <td width="60%" align="left">&nbsp;</td>
        <td width="40%" align="left">
        <button name="btnSubmit" class="button" onClick="javascript:if(submitSearch()){Liferay.Popup.close(this)}"><spring:message code="button.search"/></button> &nbsp;
        <button name="btnCancel" class="button" onClick="callOmnitureAction('Service Request', 'Service Request Search - Close');Liferay.Popup.close(this);"><spring:message code="button.cancel"/></button> &nbsp;</td>
	</tr>
</table>
</div>
<script type="text/javascript">
	function submitSearch(){
	 	callOmnitureAction('Service Request', 'Service Request Advanced Search');
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
    	var srNmbr = document.getElementById("txtSerialNumber").value;
    	if(srNmbr != "" && srNmbr.length != 7 && srNmbr.length != 11 && srNmbr.length != 13){
    		document.getElementById("lbValidationMsg").innerHTML = "<spring:message code='validation.error.invalidSerialNumber'/>";
    		return false;
    	}
    	return true;
    }
</script>