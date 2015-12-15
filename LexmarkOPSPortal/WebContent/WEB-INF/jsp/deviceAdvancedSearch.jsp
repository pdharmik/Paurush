<%@ include file="/WEB-INF/jsp/include.jsp"%>
<script type="text/javascript" src='<html:rootPath/>js/advancedSearch.js'></script>
<script type="text/javascript" src='<html:rootPath/>js/validation.js'></script>
<!-- TODO need to update based on upcoming screen in future -->
<div id="divAdvancedSearch">
<table width="100%" border="0" height="80%">
	<tr>
		<td colspan="2" align="center" class='orangeSectionTitles'><spring:message code="serviceRequest.title.advancedFilterOption"/></td>
    </tr>
    <tr>
        <td width="60%" align="left"><spring:message code="serviceRequest.listHeader.serialNumber" /></td>
        <td width="40%" align="left"><spring:message code="serviceRequest.listHeader.productModel" /></td>
	</tr>
	<tr>
        <td width="60%" align="left"><input type="text" name="serialNumber" id="txtSerialNumber"></td>
        <td width="40%" align="left"><input type="text" name="productLine" id="txtModelNumber"></td>
	</tr>
    <tr>
        <td width="60%" align="left"><spring:message code="serviceRequest.listHeader.assetTag"/></td>
        <td width="40%" align="left"><spring:message code="serviceRequest.listHeader.deviceTag"/></td>
	</tr>
	<tr>
        <td width="60%" align="left"><input type="text" name="assetTag" id="txtAssetTag"></td>
        <td width="40%" align="left"><input type="text" name="deviceTag" id="txtDeviceTag"></td>
	</tr>
    <tr>
        <td width="60%" align="left"><spring:message code="serviceRequest.listHeader.ipAddress"/></td>
        <td width="40%" align="left"><spring:message code="serviceRequest.listHeader.productLine" /></td>
	</tr>
	<tr>
        <td width="60%" align="left"><input type="text" name="ipAddress" id="txtIpAddress"></td>
        <td width="40%" align="left"><input type="text" name="productLine" id="txtProductLine"></td>
	</tr>
    <tr>
        <td width="60%" align="left"><spring:message code="serviceRequest.listHeader.ProductTLI"/></td>
        <td width="40%" align="left"><spring:message code="serviceRequest.listHeader.assetContact.emailAddress"/></td>
	</tr>
	<tr>
        <td width="60%" align="left"><input type="text" name="productTLI" id="txtProductTLI"></td>
        <td width="40%" align="left"><input type="text" name="accountContact.emailAddress" id="txtEmailAddress"></td>
	</tr>
    <tr>
        <td width="60%" align="left"><spring:message code="serviceRequest.listHeader.assetContact.firstName"/></td>
        <td width="40%" align="left"><spring:message code="serviceRequest.listHeader.assetContact.lastName"/></td>
	</tr>
	<tr>
        <td width="60%" align="left"><input type="text" name="assetContact.firstName" id="txtFirstName"></td>
        <td width="40%" align="left"><input type="text" name="assetContact.lastName" id="txtLastName"></td>
	</tr>
    <tr>
        <td width="60%" align="left"><spring:message code="serviceRequest.listHeader.assetContact.workPhone"/></td>
        <td width="40%" align="left">&nbsp;</td>
	</tr>
	<tr>
        <td width="60%" align="left"><input type="text" name="accountContact.workPhone" id="txtContactWorkPhone"></td>
        <td width="40%" align="left">&nbsp;</td>
	</tr>
    <tr>
        <td width="60%" align="left"><spring:message code="serviceRequest.listHeader.country"/></td>
        <td width="40%" align="left"><spring:message code="serviceRequest.listHeader.state"/></td>
	</tr>
	<tr>
        <td width="60%" align="left">
        	<select name="installAddress.country" id="InstalledCountry">
        		<option value="" > </option>
				<c:forEach items="${advancedSearchForm.countries}" var="installedCountry" varStatus="loop">
					<option value="${installedCountry}" > ${installedCountry} </option>
				</c:forEach>
			</select>
		</td>
        <td width="40%" align="left">
			<select name="installAddress.state" id="state">
				<option value="" > </option>
				<c:forEach items="${advancedSearchForm.states}" var="state" varStatus="loop">
					<option value="${state}" > ${state} </option>
				</c:forEach>
		 	</select>
		</td>
	</tr>
	 <tr>
        <td width="60%" align="left"><spring:message code="serviceRequest.listHeader.province"/></td>
        <td width="40%" align="left"><spring:message code="serviceRequest.listHeader.city"/></td>
	</tr>
	<tr>
        <td width="60%" align="left">
			<select name="installAddress.province" id="province">
        		<option value="" > </option>
				<c:forEach items="${advancedSearchForm.provinces}" var="province" varStatus="loop">
					<option value="${province}" > ${province} </option>
				</c:forEach>
		 	</select>
		</td>
        <td width="40%" align="left">
        	<input type="text" name="installAddress.city" id="installAddress.city">
		</td>
	</tr>
	<tr>
        <td width="60%" align="left">&nbsp;</td>
        <td width="40%" align="left">&nbsp;</td>
	</tr>
	<tr>
        <td width="60%" align="left">&nbsp;</td>
        <td width="40%" align="left">
        	<button name="btnSubmit" onClick="submitSearch();Liferay.Popup.close(this);"><spring:message code="button.search"/></button>&nbsp;
        	<button name="btnCancel" onClick="Liferay.Popup.close(this);"><spring:message code="button.cancel"/></button> 
        </td>
	</tr>
</table>
</div>
<script type="text/javascript">
	function submitSearch(){
  	    callOmnitureAction('Device Finder', 'Device Finder Advanced Search');
		clearParentSearchCriteria();
		var searchCriteria="";
		populateSearchCriterias(searchCriteria);
		searchCriteria = populateSearchCriterias(searchCriteria);
		window.parent.window.searchCriteria = searchCriteria;
		window.parent.window.reloadData();
		Liferay.Popup.close(this);
	}
	
    function clearParentSearchCriteria(){
		window.parent.window.document.getElementById("txtSearchValue").value="";
    }


</script>