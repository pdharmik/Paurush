<%@ include file="/WEB-INF/jsp/include.jsp"%>
<script type="text/javascript"><%@ include file="../../js/portletRedirection.js"%></script>

<div class="portlet-section-body">
<portlet:actionURL var="doSearchUrl">
		<portlet:param name="action" value="doSearch"/>
</portlet:actionURL>

<table width= "100%" border="0">
	<tr>
		<td align="left" class="pageDescription"><spring:message code="deviceFinder.label.searchforThisDevice" /></td>
	</tr>
	<tr class="simpleDeviceFinderTableRow">
        <td width="100%" align="left">
     		<select id='ddl_search_field'  onChange="onSelectChange();">
				<option value='serialNumber'><spring:message code="deviceDetail.label.serialNumber" /></option>
				<option value='productLine'><spring:message code="deviceDetail.label.model" /></option>
				<option value='assetTag'><spring:message code="deviceDetail.label.assetTag" /></option>
				<option value='deviceTag'><spring:message code="deviceDetail.label.customerDeviceTag" /></option>
				<option value='hostName'><spring:message code="deviceDetail.label.hostName" /></option>
				<option value='ipAddress'><spring:message code="deviceDetail.label.ipAddress" /></option>
				<option value='installAddress.addressName'><spring:message code="deviceDetail.label.addressName" /></option>
			</select>
			&nbsp;
			<input type="text" class="deviceFinderInput" name="txtSearchValue" value="" id="txtSearchValue" onPropertyChange="clearMessage();" onKeyPress="if (event.keyCode == 13) document.getElementById('btnSearch').click()"/>
			&nbsp;
			<button name="btnSearch" class="button" id="btnSearch"
			 	onclick='trimSerialNumber();customColumnSearch(document.getElementById("ddl_search_field").value,document.getElementById("txtSearchValue").value);'>
				<spring:message code="button.search" />
			</button>		
		</td>
	</tr>
</table>
</div>
<script type="text/javascript">
	function customColumnSearch(columnName,value) {
		   callOmnitureAction('Device Finder', 'Simple Device Finder Custom Search');
		if(validateSerchValues(columnName,value)){
			if(${simpleDeviceFinderFlag}){
				window.location.href="<portlet:actionURL><portlet:param name='action' value='doSearch'/></portlet:actionURL>&searchName="+columnName+"&searchValue="+value+"&deviceURL="+populateURL("grid-view",[],[]);
			}else{
				window.location.href="<portlet:actionURL><portlet:param name='action' value='doSearch'/></portlet:actionURL>&searchName="+columnName+"&searchValue="+value+"&deviceURL="+populateURL("device-finder",[],[]);	
			}
			
		}
	};

	function validateSerchValues(columnName, value){
		/* if(columnName == "serialNumber")
			if(value.length != 7 && value.length != 11 && value.length != 13){
				showError("<spring:message code='validation.error.invalidSerialNumber'/>");
				return false;
			} //BRD #14-07-12 */  
		return true;
	}

	function trimSerialNumber(){
		var trimValue = document.getElementById("txtSearchValue").value.trim();
		document.getElementById("txtSearchValue").value = trimValue;		
	}

	function onSelectChange(){		   
		   clearMessage();
		   document.getElementById("txtSearchValue").value="";
		   callOmnitureAction("Device Finder", "Simple Device Finder Select Change");
	}
 </script>
 <script type="text/javascript">
//---- Ominture script 
     portletName = "Simple Device Finder";
     addPortlet(portletName);
</script>