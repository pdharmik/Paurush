<%@ include file="/WEB-INF/jsp/include.jsp"%>
<%@ include file="/WEB-INF/jsp/includeGrid.jsp"%>

<style type="text/css"><%@ include file="/WEB-INF/css/ci6.css" %></style>

<style type="text/css">
.floatR { float:right!important }
.floatL { float:left!important; }
#custAcctNotify div{
margin-top:20px;
}
</style>

<!--[if IE 7]>
	<style type="text/css"><%@ include file="/WEB-INF/css/mps_ie7.css" %></style>
<![endif]-->
<%@ page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>

<portlet:resourceURL var="deviceListUrl" id="getGlobalPartnerAssetList">
</portlet:resourceURL>
<portlet:actionURL var="selectDeviceURL">
	<portlet:param name="action" value="selectDevice"/>
</portlet:actionURL>
<portlet:renderURL  var="showCreateClaimRequestPageUrl">
    <portlet:param name="action" value="showCreateClaimRequestPage" />
</portlet:renderURL>
<portlet:renderURL  var="requestsListViewUrl">
</portlet:renderURL>
<style>
.ie8 div.gridbox_light table.obj .button{background-image:url(/LexmarkPartnerPortal/images/buttonBg.jpg) #0076a3 repeat-x 0px 0px !important;behavior: url("/LexmarkPartnerPortal/css/PIE.htc")}
</style>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/in-styles.css?version=<html:fileCacheVersion/>" />
<div class="main-wrap">
<!-- <div class="journal-content-article">
      <h2>Requests</h2>
</div> -->


    <div class="content">
    <div class="grid-controls">
		<div class="utilities">
		</div><!-- utilities -->
		<div class="expand-min">
			<A id="headerImageButton" class="button" href="${requestsListViewUrl}">&lt;&lt; <spring:message code="link.return.to.requests"/></A></div>
		<div class="clear-right"></div>
		<br>
	</div>
        <!-- Device Selection begin-->
        <div class="portlet-wrap">
            <div class="portlet-header">
            <table class="displayGrid line-height-20px">
              <thead>
                <tr><th><spring:message code="deviceSelection.label.title" /></th>
                </tr></thead>
              
            </table>
               
            </div>
            <div class="portlet-wrap-inner">
				<br>
				<table width="100%">
					<tbody>
						<tr>
							<td align="left" width="100%">
								<label><spring:message code="deviceSelection.label.EnterSerialNumber" />:</label>
								&nbsp; <input type="text" id="txtSearchValue" onkeypress= "javascript:if(event.keyCode==13){trimSerialNumber();customSearch(document.getElementById('txtSearchValue').value);} " />
								&nbsp; <a href="javascript:trimSerialNumber();customSearch(document.getElementById('txtSearchValue').value);" class="button text-decoration-none">
								<spring:message code="button.search" />
								</a>&nbsp;
								<label id="deviceSearchLoading" style="display: none;">
									<img  src = "<html:imagesPath/>loading-icon.gif"; />&nbsp;<spring:message code="label.searchingNotification" />
								</label>
								<br><br>
							</td>
						</tr>
					</tbody>
				</table>

				<label for="txtSearchValue" id="labErrorMsg" class="color-red"></label>
				<br>
				<br>
				<div id="deviceList" style="visibility: hidden;">
					<HR align=left color=#987cb9 SIZE=1 width="800px">
					<div class="width-100per" id="gridDSDeviceList">
                	</div>
					<div id="pagingAreaDeviceList"></div>
				</div>
                
                <div id="divManualAsset" style="display: none;">
					<table class="margin-left-20px" width="60%">
						<tr>
							<td class="manualSerialTD"><span class="required">*</span><spring:message code="deviceSelection.label.MachineType" />:<br/>
								<span class="ie6_spacer">&nbsp;</span>
								<input type="hidden" id="manualSerialNumber" />
								<input type="text" name="machineType" value="" id="machineType" onblur="validateLength(0,100,this);" onChange="validateManualAsset('machineType');" onkeypress= "javascript:if(event.keyCode==13){this.focus();validateManualAsset('machineType');}"/>
								<label id="machineTypeLoadingIndicator" style="display: none;">
									<img  src = "<html:imagesPath/>loading-icon.gif"; />&nbsp;<spring:message code="deviceSelection.description.validatingMachineType" /></label>
								<br/>
								<label><spring:message code="deviceSelection.description.ExFormat" /></label><br/>
								<label for="machineType" id="machineTypeErrorMsg" class="color-red" />
							</td>
							<td>
								<table>
									<tr>
										<td>
											<div id="add" style="display:none;">
											<a href="javascript:addAsset();" class="button">&nbsp;<spring:message code="deviceSelection.label.add" />&nbsp;</a>
											</div>
										</td>
									</tr>
								</table>
							</td>
						</tr>
						<tr><td><br/></td></tr>
						<tr>
							<td class="manualSerialTD"><spring:message code="deviceSelection.label.productTLI" />: <br/>
								<span class="ie6_spacer">&nbsp;</span>
								<input type="text" name="productTLI" value="" id="productTLI" onblur="validateLength(0,100,this);" onChange="validateManualAsset('productTLI');" onkeypress= "javascript:if(event.keyCode==13){this.focus();validateManualAsset('productTLI');} "/>
								<label id="productTLILoadingIndicator" style="display: none;"><img src = "<html:imagesPath/>loading-icon.gif"; />&nbsp;<spring:message code="deviceSelection.description.validatingProductTLI" /></label>
								<br/>
								<label><spring:message code="deviceSelection.description.ExTLIFormat" /></label><br/>
								<label for="productTLI" id="productTLIErrorMsg" class="color-red"/>
							</td>
						</tr>
						<tr>
							<td class="manualSerialTD">
								<strong>?</strong><a href="${helpURL}" target="_blank"><spring:message code="deviceSelection.description.HelpMeLocateTheseNumbers" /></a>
							</td>
						</tr>
					</table>
				</div>
				
            </div><!-- portlet-wrap-inner -->
            <div class="portlet-footer">
                <div class="portlet-footer-inner">
                </div><!-- portlet-footer-inner -->
            </div>
        </div>
        <!-- Device Selection end-->
		<!-- hidden form deviceSelectionForm-->
		<form:form id="deviceSelectionForm" commandName="deviceSelectionForm" method="post" action="${selectDeviceURL}">
			<form:hidden path="asset.assetId" />
			<form:hidden path="notMyPrinterFlag"/>
			<form:hidden path="asset.productLine"/>
			<form:hidden path="asset.serialNumber" />
			<form:hidden path="asset.modelNumber"/>
			<form:hidden path="asset.productTLI"/>
			<form:hidden path="asset.customerReportingName"/>
			<form:hidden path="asset.duplicateDevice"/>
			<form:hidden path="asset.mki"/>
			<form:hidden path="asset.serviceProvider"/>
			
			
			<form:hidden path="timezoneOffset"/>
		</form:form>
		<div id="custAcctNotify" style="display: none;">
			<div class="floatL w45"><img class="green-check-img" src="<html:imagesPath/>attention.png" style="vertical-align:middle"/></div><div class="floatL width-90per margin-left-10px"><span><spring:message code="claim.notification.custAcc" /></span></div>
			<br/>
			<input type="button" class="button floatR" value="<spring:message code='requestInfo.message.continueWithoutDebrief' />" onclick="submitForm();"/>
		</div>
    </div><!-- content -->
</div><!-- main-wrap -->
    
<script type="text/javascript">
window.onload = function(){
	document.getElementById('txtSearchValue').focus();
}
//js initilization (it will load as soon as the DOM is loaded and before the page contents are loaded)
jQuery(document).ready(function() {		
	document.getElementById("machineType").value = "";
	document.getElementById("productTLI").value = "";
});

var deviceListGrid = new dhtmlXGridObject('gridDSDeviceList');
deviceListGrid.setImagePath("<html:imagesPath/>gridImgs/");
deviceListGrid.setHeader("");
deviceListGrid.setNoHeader(true);
deviceListGrid.setInitWidths("800");
deviceListGrid.enableAutoWidth(true);
deviceListGrid.enableAutoHeight(true);
deviceListGrid.init();
deviceListGrid.enablePaging(true, 5, 10, "pagingAreaDeviceList", true);
deviceListGrid.setPagingSkin("bricks");			 
deviceListGrid.setSkin("light");
deviceListGrid.attachEvent("onXLE", function() {
	if(deviceListGrid.getRowsNum() <= 0){
		document.getElementById("labErrorMsg").innerHTML = "<spring:message code='message.error.noDeviceFound' />";
		document.getElementById('deviceList').style.visibility = 'hidden';
	}else{
		document.getElementById('deviceList').style.visibility = 'visible';
	}
	hideDiv("deviceSearchLoading");
	setTimeout(function(){
		rebrandPagination();
	
	},100);
});
deviceListGrid.attachEvent("onMouseOver", function(id,ind){
		return false;
});

function customSearch(value) { 
	callOmnitureAction('Create Claim Device Selection', 'Device Selection Search');
	deviceListGrid.clearAll();
	document.getElementById('deviceList').style.visibility = 'hidden';
	document.getElementById("deviceSearchLoading").style.display = "inline";
	if(validateSearchValues(value)){
		if(value!="" && value!=null){
			var url = "${deviceListUrl}&serialNumber="+value;
			deviceListGrid.loadXML(url);
		}	        
		showDiv("divManualAsset");
	}else{
		hideDiv("divManualAsset");
	}
}

function validateSearchValues(value){
	var reg = /^[0-9a-zA-Z]+$/;
	/* if(value.length != 7 && value.length != 11 && value.length != 13 && value.length <= 18){
		document.getElementById("labErrorMsg").innerHTML = "<spring:message code='validation.error.invalidSerialNumberLength' />";
		hideDiv("deviceSearchLoading");
		return false;
	} */  //commented as per the BRD #14-07-12
	if(!reg.test(value)){
		document.getElementById("labErrorMsg").innerHTML = "<spring:message code='validation.error.invalidSerialNumberSpecialCharacter' />";
		hideDiv("deviceSearchLoading");
		return false;
	}
	document.getElementById("labErrorMsg").innerHTML ="";
	return true;
}

//trimm spaces and leading 0, s, S
function trimSerialNumber(){
	var trimValue = document.getElementById("txtSearchValue").value.replace(/\s/g,"");
	trimValue = trimValue.replace(/^[sS]*/g,"");
	document.getElementById("txtSearchValue").value = trimValue;		
	}
	
function showDiv(id){		
	var elm = document.getElementById(id);		
	elm.style.display = "block";		
}

function hideDiv(id){		
	var elm = document.getElementById(id);		
	elm.style.display = "none";		
}

function selectDevice(index){
	callOmnitureAction('Create Claim Device Selection', 'Device Selection Select Device');

	var assetId = document.getElementById("assetId"+index).value;
	var assetProductLine = document.getElementById("assetProductLine"+index).value;
	var assetSerialNumber = document.getElementById("txtSearchValue").value;
	var assetModelNumber = document.getElementById("assetModelNumber"+index).value;
	var assetProductTLI = document.getElementById("assetProductTLI"+index).value;
	var customerReportingName = document.getElementById("customerReportingName"+index).value;
	var duplicateDeviceFlag = document.getElementById("duplicateDevice"+index).value;	
	var mki = document.getElementById("maintainanceKitInstall"+index).value
	var serviceProvider = document.getElementById("serviceProvider"+index).value

	document.getElementById("asset.assetId").value = assetId;
	document.getElementById("notMyPrinterFlag").value = "false";
	document.getElementById("asset.productLine").value = assetProductLine;
	document.getElementById("asset.serialNumber").value = assetSerialNumber;
	document.getElementById("asset.modelNumber").value = assetModelNumber;
	document.getElementById("asset.productTLI").value = assetProductTLI;
	document.getElementById("timezoneOffset").value = timezoneOffset;
	document.getElementById("asset.customerReportingName").value = customerReportingName;
	document.getElementById("asset.duplicateDevice").value = duplicateDeviceFlag;
	// ******* added start *******
	document.getElementById("asset.mki").value = mki;
	document.getElementById("asset.serviceProvider").value = serviceProvider;
	// ******* added end *******
	if(customerReportingName == "" || customerReportingName == null){
		//jConfirm("To Notify the Partner to add Proof of Purchase","");
		custAccntNotification.dialog('open');
		$(".ui-dialog-titlebar").hide();
	}
	else{
		document.getElementById("deviceSelectionForm").submit();
	}
}
function addAsset(){
	callOmnitureAction('Create Claim Device Selection', 'Device Selection Add Asset');
	mt = document.getElementById("machineType").value;
	pTLI = document.getElementById("productTLI").value;
	trimSerialNumber();
	serialNumber = document.getElementById("txtSearchValue").value;
	if(validateSearchValues(serialNumber)){
		window.location.href="${showCreateClaimRequestPageUrl}&assetId="
			+ "" + "&notMyPrinterFlag=" + "true" + "&machineType=" + mt + "&productTLI=" + pTLI + "&serialNumber=" + serialNumber + "&timezoneOffset="+timezoneOffset;
	}else{
		scroll(0,0);
	}
}

function validateManualAsset(fieldName){
	var machineType = document.getElementById("machineType").value.trim();
	var productTLI = document.getElementById("productTLI").value.trim();
	document.getElementById("add").style.display = "none";
	if (machineType.trim() == '') {
		document.getElementById("machineTypeErrorMsg").innerHTML = "";
		if (productTLI.trim() == ''){
			document.getElementById("productTLIErrorMsg").innerHTML = "";
		}
		return;
	}
	var url = '<portlet:resourceURL id="validateManualAsset"/>';
	url +="&machineType=" + machineType;
	url +="&productTLI="+productTLI;
	if (fieldName == "machineType") {
		document.getElementById("machineTypeErrorMsg").innerHTML = "";
		document.getElementById("machineTypeLoadingIndicator").style.display = "";
		doAjax(url, callbackGetResult4MT,failBack4MT, null);
	} 
	if (fieldName == "productTLI"){
		if (productTLI.trim() == ''){
			document.getElementById("productTLIErrorMsg").innerHTML = "";
		}
		document.getElementById("productTLIErrorMsg").innerHTML = "";
		document.getElementById("productTLILoadingIndicator").style.display = "";
		doAjax(url, callbackGetResult4PTLI,failBack4PTLI, null);
	}
	//ServiceRequestProxy.setServiceAddressFavouriteFlag("${serviceRequestConfirmationForm.loginAccountContact.contactId}", favoriteAddressId, flagStatus, callbackGetResult);
}
	
function callbackGetResult4MT(result) {
	var machineType = document.getElementById("machineType").value.trim();
	var productTLI = document.getElementById("productTLI").value.trim();
	if(result.data){
		document.getElementById("machineTypeLoadingIndicator").style.display = "none";
		document.getElementById("productTLIErrorMsg").innerHTML = "";
		document.getElementById("machineTypeErrorMsg").innerHTML = "";
		document.getElementById("add").style.display = "block";
		
	}else{
		document.getElementById("machineTypeLoadingIndicator").style.display = "none";
		document.getElementById("machineTypeErrorMsg").innerHTML = "<spring:message code='deviceSelection.errorMsg.machineTypeErrorMsg'/>";
		if(productTLI.trim() != ""){
			document.getElementById("productTLIErrorMsg").innerHTML = "<spring:message code='deviceSelection.errorMsg.productTLIErrorMsg'/>";
		}
		document.getElementById("add").style.display = "none";
	}
	return true;
	
}

function callbackGetResult4PTLI(result) {
	if(result.data){
		document.getElementById("productTLILoadingIndicator").style.display = "none";
		document.getElementById("productTLIErrorMsg").innerHTML = "";
		document.getElementById("machineTypeErrorMsg").innerHTML = "";
		document.getElementById("add").style.display = "block";
		
	}else{
		document.getElementById("productTLILoadingIndicator").style.display = "none";
		if(document.getElementById("productTLI").value.trim() == ""){
			document.getElementById("productTLIErrorMsg").innerHTML = "";
		}else{
			document.getElementById("productTLIErrorMsg").innerHTML = "<spring:message code='deviceSelection.errorMsg.productTLIErrorMsg'/>";
		}
		document.getElementById("add").style.display = "none";
	}
	return true;
}

function failBack4MT(){
	document.getElementById("machineTypeLoadingIndicator").style.display = "none";
	document.getElementById("productTLIErrorMsg").innerHTML = "";
	document.getElementById("machineTypeErrorMsg").innerHTML = "";
	return false;
}

function failBack4PTLI(){
	document.getElementById("productTLILoadingIndicator").style.display = "none";
	document.getElementById("productTLIErrorMsg").innerHTML = "";
	document.getElementById("machineTypeErrorMsg").innerHTML = "";
	return false;
}

var custAccntNotification=$('#custAcctNotify').dialog({
	autoOpen:false,
	resizable:false,
	modal: true,
	width: "600px"
	
});

function submitForm(){
	custAccntNotification.dialog('close');
	document.getElementById("deviceSelectionForm").submit();
}
</script>
<script type="text/javascript">
//---- Ominture script 
     addPortlet("Claim Create Device Selection");
</script>