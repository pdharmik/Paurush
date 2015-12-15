
	<style type="text/css">
			<%@ include file="/WEB-INF/css/mps_ie7.css" %>
	</style> 
<!--added for MPS breakfix-->
<% request.setAttribute("subTabSelected","createNewRequest"); %>
<%@ include file="/WEB-INF/jsp/common/subTab.jsp"%>
<!--end of addition-->
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<%@ include file="/WEB-INF/jsp/includeDatePicker.jsp"%>
<jsp:include page="/WEB-INF/jsp/includeGrid.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/mps.css"/>
<%@ page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>
<script type="text/javascript"><%@ include file="../../../js/grid/ext/dhtmlxgrid_excell_sub_row.js"%></script>

<style type="text/css">
<%@ include file="../../css/grid/dhtmlxdataview.css"%>
</style>
<style>
#globalSerachResultContainer img.mt{
	height:	60px !important;
}
div.portlet-header{margin:0px !important}
div.portlet-header h3{margin:0}
div.portlet-footer, div.portlet-footer-inner{background-image:none}
#typeOfService,#primaryContactData,#serviceAddress {
	color: DarkGray
}
div.dark-bar{
 background-color: DarkGraysss
}

/* added for MPS breakfix	*/
#subnavigation {
	border-top: 0;
	padding:5px;
	overflow:hidden;
	clear:both;
	margin-top: 0px;
}

#subnavigation ul {
	margin-left: 0px;
	margin-top: -2px;	
}

#subnavigation ul li {
	display:inline-block;
	float:left;
	margin-right:3px;
}

#subnavigation ul li a {
	display:inline-block;
	padding:3px 15px;
	color:#000;
	font-size:12px;
}

#subnavigation ul li a:hover {
	text-decoration:none;
	background-color:#fff;
	border: #cdcdcd 1px solid;
	border-radius: 3px;
	-moz-border-radius: 3px;
	color:#000;
	padding:2px 14px;
	font-weight:normal;
}

#subnavigation ul li.selected a {
	background-color: #e6e6f0;
    border-bottom: 2px solid #00c425;
    color: #1d1d25;
    font-weight: bold;
    padding: 3px 14px;
    text-decoration: none;
}
#subnavigation ul li.selected a:active { font-weight: bold!important }
/* end of addition for MPS breakfix	*/
.displayGrid{width:100%;float:left;margin:3px;}


</style>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/in-styles.css?version=<html:fileCacheVersion/>" />
<script type="text/javascript"><%@ include file="../../../js/grid/ext/dhtmlxdataview.js"%></script>

<!-- Added for CI-7 Defect #12467 -->
<style type="text/css">
.ie7 .button, .ie7 .btnSearch {
behavior: url(/LexmarkServicesPortal/WEB-INF/css/PIE.htc) !important;
}
</style>

<div class="journal-content-article">
      <h1><spring:message code='requestInfo.requestHistory.heading.serviceRequests'/></h1>
      <h2 class="step" id="historyTypeName"></h2>
</div>

<div class="width-100per">
<div id="deviceInformation" class="portlet-wrap rounded">
<div class="portlet-header dark-bar">
<h3><spring:message	code="serviceRequest.label.deviceInformation" /></h3>
	
	</div>
<div  class="portlet-wrap-inner">

<div id="deviceTableWrapper">
<table id="deviceTable" width="100%" border="0">
	<tr>
		<td colspan="2">
		<table width="100%" border="0">
			<tr height="10">
				<td colspan="2" align="left"><label class='orangeSectionTitles'"> <br>
				<spring:message	code="serviceRequest.description.SearchForThisDevice" /><br>
				<br>
				</label></td>
			</tr>
			<tr>
				<td width="90%" align="left">
					<select id='txtSearchName' onChange="onSelectChange();">
						<option value='serialNumber'><spring:message code="deviceDetail.label.serialNumber" /></option>
						<option value='productLine'><spring:message code="deviceDetail.label.model" /></option>
						<option value='assetTag'><spring:message code="deviceDetail.label.assetTag" /></option>
						<option value='deviceTag'><spring:message code="deviceDetail.label.customerDeviceTag" /></option>
						<option value='hostName'><spring:message code="deviceDetail.label.hostName" /></option>
						<option value='ipAddress'><spring:message code="deviceDetail.label.ipAddress" /></option>
						<option value='installAddress.addressName'><spring:message code="deviceDetail.label.addressName" /></option>
						<option value='installAddress.addressLine1'><spring:message code="serviceRequest.label.addressLine1" /></option>
						<option value='installAddress.city'><spring:message code="serviceRequest.label.city" /></option>
						<option value='installAddress.state'><spring:message code="serviceRequest.label.state" /></option>
						<option value='installAddress.province'><spring:message code="serviceRequest.label.province" /></option>
						<option value='installAddress.country'><spring:message code="serviceRequest.listHeader.country" /></option>
					</select>
				&nbsp;
				<input type="text" name="txtSearchValue" value="" id="txtSearchValue" onPropertyChange="clearMessage();"/>
				&nbsp;
				<button name="btnSearch" class="button SRBtnSearch" type="button" id="btnSearch"
					onclick='trimSerialNumber();copySerialNumber();customColumnSearch(document.getElementById("txtSearchName").value,document.getElementById("txtSearchValue").value);'>
				<spring:message code="button.search" /></button>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="left"><label for="txtSearchValue" id="labErrorMsg" class="color-red"></label></td>
			</tr>			
			<tr>
				<td height="10" colspan="2">&nbsp;</td>
			</tr>
			<tr>
				<td colspan="2">
				</td>
			</tr>
		</table>
		</td>
	</tr>
</table>
    <div id="device_list_container">
    	<div id="resultTitle" style="display: none">
    		<span class="orangeSectionTitles"><spring:message code="serviceRequest.description.pleaseSelectYourPrinterModel" /></span>
    	</div>
	    
	    <div class="expand-min">
			<ul>
				<li class="first">
					<img src="<html:imagesPath/>transparent.png" class="ui_icon_sprite customize-icon cursor-pointer" id='headerImageButton'/>&nbsp;
					<a href="#grid" id='headerMenuButton'>
					<spring:message code="serviceRequest.label.customize"/></a>
				</li>
				<li>
					<img src="<html:imagesPath/>transparent.png"  onclick="javascript:doReset();" class="ui_icon_sprite reset-icon cursor-pointer"
									alt="<spring:message code="serviceRequest.label.reset"/>" title="<spring:message code="serviceRequest.label.reset"/>" />
					<a href="javascript:doReset();" id="resetGridSetting">
					<spring:message code="serviceRequest.label.reset"/></a>
				</li>
			</ul>
		</div>
	    
	    <div id="serviceDeviceListGrid" class="width-100per">
	    </div>
	    <div id="loadingNotification" class='gridLoading'>
	        <!--spring:message code='loadingNotification' />&nbsp;&nbsp;--><img src="<html:imagesPath/>gridloading.gif"/>
	        	    </div>
        <div class="pagination" id="paginationId">
          			<span id="pagingArea"></span><span id="infoArea"></span>          
          		</div>
    </div>
</div>
<div id="noRecordFound" class="width-100per">
<h3><spring:message code="serviceRequest.description.norecordfound" /></h3>
	 <spring:message code="serviceRequest.description.descriptiveBlurb" /><br></br>
	<a href="#" onClick="searchAgain();"> <spring:message	code="serviceRequest.description.searchAgain" /> </a><br></br>
	<h3><spring:message	code="serviceRequest.description.registerYourDevice" /></h3>
<div id="globalSearch" class="div-style95">
<table class="margin-left-20px" >
	<tr>
		<td id="noRecordFoundInputTD">
			<spring:message code="serviceRequest.description.enterYourSerialNumber" />:
			<input type="text" name="serialNumber" value="" id="serialNumber">
			<label for="serialNumber" id="SNlabErrorMsg"></label>
		</td>

		<td valign="top" id="noRecordFoundBtnTD">
			<button class="button" type="button" id="btnGlobalSearch" onClick="performGlobalSearch();"><spring:message	code="button.search" /></button>
		</td>
	</tr>
</table>
</div>

<div id="noResult"><h3><spring:message code="serviceRequest.description.noDeviceFound" /></h3></div>
<div id="searchResult" class="div-style95">
<div id="globalSerachResultContainer" class="div-style96">
<div id="globalDevice">
<table class="margin-left-20px" >
	<tr valign="center">
		<td ><img src="#img#" class='mt' width='60'	height='60' />
		</td >
		<td class="width-30px"></td>
		<td class="width-220px" >
			<spring:message	code="serviceRequest.label.Model" />:#model#<br />
			<span><spring:message code="serviceRequest.label.productTLI" />:</span>#productTLI#
		</td>
		<td>
			<div class='margin-left-50px'>
				<button class="button" type="button" onClick="requestServiceForDeviceNotFound('#select#');">
					<spring:message	code="button.select" />
				</button>
			</div>
		</td>
	</tr>
</table>
</div>
</div>
</div>


<div id="divManualAsset" class="divManualAsset">
<table class="margin-left-20px">
	<tr>
		<td class="manualSerialTD">*<spring:message code="serviceRequest.label.MachineType" />:<br/>
			<span class="ie6_spacer">&nbsp;</span>
		    <input type="hidden" id="manualSerialNumber">
			<input type="text" name="machineType" value="" id="machineType" onChange="validateManualAsset('machineType');">
			<label id="machineTypeLoadingIndicator">
				<spring:message code="serviceRequest.description.validatingMachineType" /><img  src = "<html:imagesPath/>loading-icon.gif"; /></label>
			<br/>
			<label><spring:message code="serviceRequest.description.ExFormat" /></label><br/>
			<label for="machineType" id="machineTypeErrorMsg" class="color-red" />
		</td>

		<td class="manualSerialTD">
			<button id="add" class="button" type="button" onClick="addAsset()";><spring:message code="serviceRequest.label.add" /></button>
		</td>
	</tr>
	<tr><td><br/></td></tr>
	<tr>
		<td class="manualSerialTD"><spring:message code="serviceRequest.label.productTLI" />: <br/>
			<span class="ie6_spacer">&nbsp;</span>
			<input type="text" name="productTLI" value="" id="productTLI" onChange="validateManualAsset('productTLI');">
			<label id="productTLILoadingIndicator" ><spring:message code="serviceRequest.description.validatingProductTLI" /><img src = "<html:imagesPath/>loading-icon.gif"; /></label>
			<br/>
			<label><spring:message code="serviceRequest.description.ExTLIFormat" /></label><br/>
			<label for="productTLI" id="productTLIErrorMsg" class="color-red"/>
		</td>
	</tr>
	<tr>
		<td class="manualSerialTD">
			<strong>?</strong><a href="${helpURL}" target="_blank"><spring:message code="serviceRequest.description.HelpMeLocateTheseNumbers" /></a>
		</td>
	</tr>
</table>
</div>
</div>
</div>
<div class="portlet-footer">
	<div class="portlet-footer-inner"></div>
	</div>
</div>

<div id="primaryContactData" class="portlet-wrap rounded">
	<div class="portlet-header">
	<h3><spring:message code="serviceRequest.label.primaryContactInformation" /></h3>
	</div>
	<div  class="portlet-wrap-inner">
		<p align="center"><spring:message code="serviceRequest.label.primaryContactInformationDesc" /></p>
		<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<spring:message code="serviceRequest.label.primaryContactInformationAdd" /></p>
	</div>
	<div class="portlet-footer">
		<div class="portlet-footer-inner"></div>
	</div>
</div>

<div id="serviceAddress" 	class="portlet-wrap rounded">
	<div class="portlet-header">
	<h3><spring:message code="serviceRequest.label.serviceAddress" /></h3>
	</div>
	<div  class="portlet-wrap-inner">
		<p align="center"><spring:message code="serviceRequest.label.ServiceAddressDesc" />
	</div>
	<div class="portlet-footer">
		<div class="portlet-footer-inner"></div>
	</div>
</div>



<div id="typeOfService" class="portlet-wrap rounded width-100per">
	<div class="portlet-header">
	<h3><spring:message code="serviceRequest.label.problemDescription" /></h3>
	</div>
	<div  class="portlet-wrap-inner">
		<table width="500px" class="div-style97">
			<tr>
				<td valign="top"><b><spring:message code="serviceRequest.label.problemDescription" /></b><br />
				</td>
			</tr>
			<tr>
				<td valign="top" align="left" class="padding-left-1cm"><textarea class="div-style98"></textarea></td>
			</tr>
			<tr>
				<td valign="center" align="left" class="padding-left-1cm"><spring:message 	code="serviceRequest.label.ReferenceNumber" />: <br />
				<input class="div-style99" /></td>
			</tr>
			<tr>
				<td>
					<div>
						<spring:message code="serviceRequest.label.TypeOfServiceNeeded" />
					</div>
					<div align="left" class="div-style100">
						<input	type="checkbox" name="selectedServiceDetails" value="yes" /> 
							<spring:message code="serviceRequest.label.OnsiteRepair" /><br />
						<input type="checkbox" name="selectedServiceDetails" value="yes" /> 
							<spring:message code="serviceRequest.label.OnsiteExchangeOfDevice" /><br />
						<input type="checkbox" name="selectedServiceDetails" value="yes" />
							<spring:message code="serviceRequest.label.ExchangeOfDevice" /><br />
						<input type="checkbox" name="selectedServiceDetails" value="yes" /> 
							<spring:message code="serviceRequest.label.ExchangeOfOption" /><br />
						<input type="checkbox" name="selectedServiceDetails" value="yes" /> 
							<spring:message code="serviceRequest.label.Other" /><br />
					<div id="otherDiv" class="div-style90">
						<textarea	class="textarea1"></textarea>
					</div>
				</td>
			</tr>
		</table>
	</div>
	<div class="portlet-footer">
		<div class="portlet-footer-inner"></div>
	</div>
</div>
<div id="dialog_meterReads" style="display: none">
<jsp:include page="/WEB-INF/jsp/deviceFinder/pageCountPopUp.jsp"/>
</div>
</div>

<script type="text/javascript">
<portlet:resourceURL var="saveCustomizedGridSettingURL" id="saveCustomizedGridSetting"/>
var prevRowId = '';
var assetRowId1='';
	var isFistLoadFlag = true;
   //js initilization (it will load as soon as the DOM is loaded and before the page contents are loaded)
   	/*****************************************************************************************
	 *********device selection list with drill-down block (patience!! it's a big one)*********
     *****************************************************************************************/	
    var dBPersistentFlag = true;
	jQuery(document).ready(function() {		
		el=document.getElementById("typeOfService");
		x=el.getElementsByTagName("INPUT");
		for (i=0;i<x.length;i++)
		{
		  	x[i].disabled = true;
		}
		y=el.getElementsByTagName("TEXTAREA");
		for (i=0;i<y.length;i++)
		{
		  	y[i].disabled = true;
		}
		//dc = document.getElementById("devicesContainer");
		//dc.style.display = "none";
		dt = document.getElementById("deviceTable");
		dt.style.height = "40px";
		document.getElementById("add").style.visibility = "hidden";
		document.getElementById("machineTypeErrorMsg").innerHTML = "";
		document.getElementById("productTLIErrorMsg").innerHTML = "";		
		var elm = document.getElementById("device_list_container");
		elm.style.display = "none";
		document.getElementById('loadingNotification').style.display = "none";
		document.getElementById("device_list_container").style.display = "none";
		document.getElementById("noRecordFound").style.display = "none";
		document.getElementById("divManualAsset").style.display = "none";
		document.getElementById("machineTypeLoadingIndicator").style.display = "none";
		document.getElementById("productTLILoadingIndicator").style.display = "none";


		deviceListGrid = new dhtmlXGridObject('serviceDeviceListGrid');
		deviceListGrid.setImagePath("<html:imagesPath/>gridImgs/");
		//deviceListGrid.setHeader("<spring:message code='deviceFinder.listHeader.deviceFields'/>"+",&nbsp;");
		deviceListGrid.setHeader(autoAppendPlaceHolder("<spring:message code='deviceFinder.listHeader.deviceFields'/>",24));
		deviceListGrid.attachHeader(",#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter");
		deviceListGrid.setInitWidths("30,120,120,120,120,120,120,120,120,120,120,120,120,120,120,120,120,120,120,120,120,120,120,120,120");
		deviceListGrid.setColAlign("left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left");
		deviceListGrid.setColTypes("sub_row,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro");
		deviceListGrid.setColSorting("na,str,str,str,str,str,str,str,str,str,str,str,str,str,str,str,str,str,str,str,str,str,str,str,str");
	    //deviceListGrid.enableAutoWidth(true);
	    deviceListGrid.enableAutoHeight(true);
	    deviceListGrid.enableMultiline(true);
	    deviceListGrid.setSkin("light");
	    deviceListGrid.setLockColVisibility("true,true,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false");
		deviceListGrid.enableColumnMove(true);
	    deviceListGrid.init();
	    deviceListGrid.prftInit();
		deviceListGrid.enableHeaderMenuForButton('headerMenuButton');
		deviceListGrid.enableHeaderMenuForButton('headerImageButton');
		deviceListGrid.enableAutoSavingToDB("${saveCustomizedGridSettingURL}");
		deviceListGrid.enablePaging(true, 5, 10, "pagingArea", true, "infoArea");
		deviceListGrid.setPagingSkin("bricks");
		deviceListGrid.sortRows = function(col, type, order) {}

		deviceListGrid.sortIndex = null;
	    deviceListGrid.columnChanged = true;
	    
		deviceListGrid.attachEvent("onXLS", function() {
			document.getElementById('loadingNotification').style.display = '';
			dBPersistentFlag = false;
			if(!deviceListGrid.loadHiddenColumns(colsHidden) && colsWidth=="" || gridToBeReset) {
				setDefaultHiddenColumns();
				gridToBeReset = false;
			}
	        if(deviceListGrid.a_direction=='asc'){
	            deviceListGrid.setSortImgState(true, deviceListGrid.getDefaltSortImagIndex(deviceListGrid.sortIndex,1), 'asc');
	        }else{
	            deviceListGrid.setSortImgState(true, deviceListGrid.getDefaltSortImagIndex(deviceListGrid.sortIndex,1), 'des');
	        }
			dBPersistentFlag = true;
		});

	    deviceListGrid.attachEvent("onXLE", function() {  
	        document.getElementById('loadingNotification').style.display = 'none';
	        setTimeout(function(){
	    		rebrandPagination();
	    	
	    	},100);
	    	var size =deviceListGrid.getRowsNum();   
	        if(hasOneRecord(size))
	        {
	        	showOverlay();
	        	var assetId = deviceListGrid.getRowId(0);
	        	requestService(assetId);        	
	        }else if(isEmpty(size)){
	            //device not found, performs global search
				document.getElementById("noRecordFound").style.display = "block";
				document.getElementById("searchResult").style.display = "none";
				document.getElementById("noResult").style.display = "none";
				document.getElementById("deviceTableWrapper").style.display = "none";
	         }else{
	        	showDiv("device_list_container");
	      		document.getElementById("resultTitle").style.display = "block";
	    	}
	    });
	    deviceListGrid.loadOrder(colsOrder);
	    deviceListGrid.loadPagingSorting(colsSorting,1);
	    deviceListGrid.loadSize(colsWidth);
		//************************ Grid filer**********************//
		deviceListGrid.attachEvent("onFilterStart", function(indexes,values){
	    	deviceListGrid.filterValues = ","+values;
	    	customizedGridURL = updateGridURL(customizedGridURL, deviceListGrid.getSortColByOffset(), deviceListGrid.a_direction, deviceListGrid.filterValues);
	    	setGridFilerTimer(reloadData);
	    });
		deviceListGrid.attachEvent("onBeforeSorting", customColumnSort);
		deviceListGrid.attachEvent("onPaging", function(count){
			refreshGridSettingOnPage(deviceListGrid);
		});
	});




	
    function setDefaultHiddenColumns(){
		deviceListGrid.setColumnHidden(7,true);
		deviceListGrid.setColumnHidden(8,true);
		deviceListGrid.setColumnHidden(10,true);
		deviceListGrid.setColumnHidden(11,true);
		deviceListGrid.setColumnHidden(12,true);
		deviceListGrid.setColumnHidden(13,true);
		deviceListGrid.setColumnHidden(14,true);
		deviceListGrid.setColumnHidden(15,true);
		deviceListGrid.setColumnHidden(16,true);
		deviceListGrid.setColumnHidden(17,true);
		deviceListGrid.setColumnHidden(18,true);
		deviceListGrid.setColumnHidden(19,true);
	}
	// SORTING
	function customColumnSort(ind) {
		var a_state = deviceListGrid.getSortingState();
		if(a_state[0] == (ind)){
			deviceListGrid.a_direction = ((a_state[1] == "asc") ? "des":"asc" );
		}else {
			deviceListGrid.a_direction = "asc" ;
			deviceListGrid.columnChanged = true;
		}
		deviceListGrid.sortIndex = ind;
		customizedGridURL = updateGridURL(customizedGridURL, deviceListGrid.getSortColByOffset(), deviceListGrid.a_direction, deviceListGrid.filterValues);
	 	reloadData();
		return true;
	}
	//RELOAD
	function reloadData() {
        clearMessage();
        refreshGridSettingOnPage(deviceListGrid);
        deviceListGrid.clearAndLoad(customizedGridURL);
	}
	//SEARCH
	var searchCriteria="";
	function customColumnSearch(columnName,value) {
 	    callOmnitureAction('Service Request', 'Service Request Custom Search');
 		hideDiv("device_list_container");
		if(validateSearchValues(columnName,value)){
			if(value!="" && value!=null)	        
				searchCriteria=columnName+"^"+value;
			customizedGridURL = populateURLCriterias();
			deviceListGrid.clearAndLoad(customizedGridURL);
			showDiv("device_list_container");
			return true;
		}
	}
	
	function populateURLCriterias(){
    	var url = "${retriveDeviceList}";
    	if(searchCriteria != null && searchCriteria != "") {
			url = url + "&searchCriterias=" + searchCriteria;
		}
		if(deviceListGrid.filterValues != null && deviceListGrid.filterValues != "") {
			url = url + "&filterCriterias=" + deviceListGrid.filterValues;
		}
        if(deviceListGrid.a_direction != null && deviceListGrid.a_direction != "") {
            url = url + "&direction=" + deviceListGrid.a_direction;
        }
        url = url + "&orderBy=" + deviceListGrid.getSortColByOffset();
        return url;
    }
    function doReset(){
        callOmnitureAction('Service Request', 'Service Request Select Device Reset Device List');
        resetGridSetting('serviceDeviceListGrid', resetCallback); 
	}

    function resetCallback() {
    	clearMessage();
		colsOrder = "0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24";
		colsWidth = "px:30,120,120,120,120,120,120,120,120,120,120,120,120,120,120,120,120,120,120,120,120,120,120,120,120";
		colsSorting = "1,asc";
		colsHidden = "";
		gridToBeReset = true;
		deviceListGrid.sortIndex = 1;
		dBPersistentFlag = false;
		deviceListGrid.setColumnsVisibility("true,true,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false");
        deviceListGrid.loadOrder(colsOrder);
        deviceListGrid.loadPagingSorting(colsSorting,1);
        deviceListGrid.loadSize(colsWidth);
		customizedGridURL = updateGridURL(customizedGridURL, 1, 'asc', deviceListGrid.filterValues);
		deviceListGrid.clearAndLoad(customizedGridURL);
    };
    //*****************************deviceListGrid definition end******Cheers!!!********************
    
    
   
	function requestService(assetId, flag){
		
		window.location.href="<portlet:renderURL><portlet:param name='action' value='showServiceRequestPage2_brkfix' /></portlet:renderURL>&assetId="
			+assetId + "&notMyPrinterFlag=" + "false" + "&machineType="+ "" + "&productTLI=" + "" + "&serialNumber=";
	}

	//request a service in the global search result list
	function requestServiceForDeviceNotFound(assetId){
		//popupWaitPage();
		window.location.href="<portlet:renderURL><portlet:param name='action' value='showServiceRequestPage2' /></portlet:renderURL>&assetId="
			+ assetId+ "&notMyPrinterFlag=" + "true" + "&machineType="+ "" +"&productTLI=" + ""+ "&serialNumber=";
	}
	

	function addAsset(){
		mt = document.getElementById("machineType").value;
		pTLI = document.getElementById("productTLI").value;
		window.location.href="<portlet:renderURL><portlet:param name='action' value='showServiceRequestPage2_brkfix' /></portlet:renderURL>&assetId="
			+ "" + "&notMyPrinterFlag=" + "true" + "&machineType=" + mt + "&productTLI=" + pTLI + "&serialNumber=" + document.getElementById('manualSerialNumber').value;
	}

	/*********************************************************************************************
	 *********************device not found result data view. (real short)*************************
	 *********************************************************************************************/
	srGlobalDeviceData = new dhtmlXDataView({		
	   	container: "globalSerachResultContainer",
	   	type: {
			template: "html->globalDevice",
			width:500,
	        height:70
		}	        
	});	
	srGlobalDeviceData.attachEvent("onXLE", function() {		
		var count = srGlobalDeviceData.dataCount();		
		if (count>0) {
    		document.getElementById("searchResult").style.display = "block";
    		document.getElementById("noResult").style.display = "none";
		} else {
			document.getElementById("searchResult").style.display = "none";
			document.getElementById("noResult").style.display = "block";
		}
   	});
	//*********************device not found result data view definition end*************************
	
    
	function performGlobalSearch(){
        document.getElementById('divManualAsset').style.display = "block";
        var value = document.getElementById("serialNumber").value;
        value = jQuery.trim(value);
		document.getElementById("manualSerialNumber").value = value;
		if(validateSerialNumber(value)){
			searchCriteria=value;
			reloadData4Global('${showGlobalDeviceResultsURL}');
			document.getElementById("searchResult").style.display = "block";
			return true;
		}
	}

	function validateSerialNumber(value){		
		document.getElementById("SNlabErrorMsg").style.color="RED";		
			/* if(value.length != 7 && value.length != 11){
				document.getElementById("SNlabErrorMsg").innerHTML = "<spring:message code='serviceRequest.errorMsg.serialNumberLength'/>";
				//document.getElementById("device_list_container").style.display = "none";
				return false;
			} */  //Commented as per the BRD #14-07-12
		document.getElementById("SNlabErrorMsg").innerHTML ="";
		return true;
	}

	function validateSearchValues(columnName, value){
		/* if(columnName == "serialNumber")
			if(value.length != 7 && value.length != 11 && value.length != 13){
				document.getElementById("labErrorMsg").innerHTML = "<spring:message code='validation.error.invalidSerialNumber'/>";
				document.getElementById("device_list_container").style.display = "none";
				return false;
			} */  //commented as per the BRD #14-07-12
		document.getElementById("labErrorMsg").innerHTML ="";
		return true;
	}


	//RELOAD for global search
	function reloadData4Global(url) {
		initialFlag = "false";		
		if(searchCriteria != null && searchCriteria != "") {
			url = url + "&searchCriterias=" + searchCriteria;			
			searchCriteria="";
		}
		srGlobalDeviceData.clearAll();
		srGlobalDeviceData.load(url);
	}
	
	function hasOneRecord(size){
		if(size =="1")
			return true;
		return false;
	}
	
	function isEmpty(size){
		if(size =="0")
			return true;
		return false;
	}

	
	//Advanced search
	function popupAdvancedSearchPage(){
		new Liferay.Popup({
			title: "<spring:message code='serviceRequest.title.advancedFilterOption'/>",
			position: [450,50], 
			modal: true,
			width: 500,
			height: 500,
			xy: [100, 100],
			onClose: function() {showSelect();},
			url:"<portlet:renderURL
				windowState='<%= LiferayWindowState.POP_UP.toString() %>'>" + 
				"<portlet:param name='action' value='showDeviceAdvancedSearchPage' />" + 
				"</portlet:renderURL>"
			});
		};

		function hideSelect(){   
			var selects = document.getElementsByTagName('select');
			for (var i=0; i < selects.length; i++){
				var select = selects[i];
				select.style.visibility = "hidden";
			}      
		}  

		function showSelect(){   
			var selects = document.getElementsByTagName('select');
			for (var i=0; i < selects.length; i++){
				var select = selects[i];
				select.style.visibility = "visible";
			} 
		} 

		//popup wait page
		function popupWaitPage(){
			new Liferay.Popup({
				title: "",
				position: [450,50], 
				modal: true,
				width: 400, 
				height: 200,
				xy: [100, 100],
				url:"<portlet:renderURL
					windowState='<%= LiferayWindowState.POP_UP.toString() %>'>" + 
					"<portlet:param name='action' value='showWaitPage' />" + 				
					"</portlet:renderURL>"
				});
			}
		
	window.document.onkeydown=function(event){
		if (event==null) {
		    event=window.event;
		}
		if (event.keyCode==13) {
			var elementId;
			if (jQuery.browser.ie) {
				elementId = event.srcElement.id
			} else {
				elementId = event.target.id;
			}
			if (elementId == 'serialNumber') {
				document.getElementById('btnGlobalSearch').click();
			} else if (elementId == 'machineType') {
				document.getElementById('machineType').blur();
				validateManualAsset('machineType');
			} else if (elementId == 'productTLI') {
				document.getElementById('productTLI').blur();
                validateManualAsset('productTLI');
			} else if (elementId == 'txtSearchValue'){
			    document.getElementById("btnSearch").click();
			}
		}    
	}

	function showDiv(id){		
		var elm = document.getElementById(id);		
		elm.style.display = "block";		
  	}
  	function hideDiv(id){		
		var elm = document.getElementById(id);		
		elm.style.display = "none";		
  	}
    
	function validateManualAsset(fieldName){
		var machineType = document.getElementById("machineType").value;
		var productTLI = document.getElementById("productTLI").value;
		document.getElementById("add").style.visibility = "hidden";
		if (machineType.trim() == '') {
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
	    	document.getElementById("productTLIErrorMsg").innerHTML = "";
	    	document.getElementById("productTLILoadingIndicator").style.display = "";
	    	doAjax(url, callbackGetResult4PTLI,failBack4PTLI, null);
	    }
	 	//ServiceRequestProxy.setServiceAddressFavouriteFlag("${serviceRequestConfirmationForm.loginAccountContact.contactId}", favoriteAddressId, flagStatus, callbackGetResult);
 	}
	
	function callbackGetResult4MT(result) {
		document.getElementById("machineTypeLoadingIndicator").style.display = "none";
		document.getElementById("add").style.visibility = "visible";
	}

	function callbackGetResult4PTLI(result) {
		document.getElementById("productTLILoadingIndicator").style.display = "none";
		document.getElementById("add").style.visibility = "visible";
	}

	function failBack4MT(){
		document.getElementById("machineTypeLoadingIndicator").style.display = "none";
		document.getElementById("machineTypeErrorMsg").innerHTML = "<spring:message code='serviceRequest.errorMsg.machineTypeErrorMsg'/>";
		document.getElementById("add").style.visibility = "hidden";
	}

	function failBack4PTLI(){
		document.getElementById("productTLILoadingIndicator").style.display = "none";
		document.getElementById("productTLIErrorMsg").innerHTML = "<spring:message code='serviceRequest.errorMsg.productTLIErrorMsg'/>";
		document.getElementById("add").style.visibility = "hidden";	
	}	

	function searchAgain(){		
		window.location.href="<portlet:renderURL><portlet:param name='action' value='displayAssetSelection' /></portlet:renderURL>";
	}

	function trimSerialNumber(){
		var trimValue = document.getElementById("txtSearchValue").value.trim();
		document.getElementById("txtSearchValue").value = trimValue;		
	}
	function copySerialNumber(){
		if(document.getElementById("txtSearchName").value == "serialNumber")
			document.getElementById("serialNumber").value = document.getElementById("txtSearchValue").value;
	}

	function onSelectChange(){
		   clearMessage();
		   document.getElementById("txtSearchValue").value="";
	}	
	function closeCustomizedWindow(){
		if(document.getElementById('grid_menu')!=null)
		{
	   	  document.getElementById('grid_menu').style.display = 'none';
		}
	   }
	//Code added
	var lastColorPgCnt1; 
var lastPgCnt1;
var lastPgRdDt1;
var lastColorPgRdDt1;
var isSuccessfulUpdate=false;

function clearContentsOfpopup(){
	jQuery('#newColorPgCnt').val("");
	jQuery('#newColorLTPCDate').val("");
	jQuery('#newLTPCPgCnt').val("");
	jQuery('#newPgRdDate').val("");
	jQuery("#validationErrors").empty();
	jQuery("#validationErrors").hide();
	jQuery("#pageCntUpdtSuccess").empty();
	jQuery("#pageCntUpdtSuccess").hide();
	jQuery("#btnCancel").html("Cancel");
	
	if (window.PIE) {
        document.getElementById("btnCancel").fireEvent("onmove");
            document.getElementById("btnSubmit").fireEvent("onmove");
    	}
	jQuery("#btnSubmit").show();
	
}

function openPopUp(assetRowId)
{
	assetRowId1=assetRowId;
	if(assetRowId != prevRowId)
	{
		prevRowId = assetRowId;	
	}
	var getltpcValuesLink="<portlet:resourceURL id='getLtpcValues'></portlet:resourceURL>" + "&assetRowId="+assetRowId1;
		
	showOverlay();
	
	jQuery.ajax({
		url:getltpcValuesLink,
		type:'POST',
		dataType: 'JSON',		
		success: function(results){
			hideOverlay();
			var obj2;
			try{
				 //obj2=jQuery.parseJSON(results);
				 obj2=results;
			}catch(e){
				
				}
			if(obj2 !=null){
				
				<%-- LTPC Success block starts --%>
				if(obj2.ltpcSuccess=="Success"){
					
				lastColorPgCnt1=obj2.colorvalue;
				
				lastPgCnt1=obj2.ltpcvalue;
				
				if(obj2.ltpcdate != "null")
				{	
					lastPgRdDt1=new Date(parseInt(obj2.ltpcdate));
					
					lastPgRdDt1=convertDateToStringWithTime(lastPgRdDt1);
					
				}else{
				lastPgRdDt1="";
				}
				
				if(obj2.colordate != "null")
				{	
					lastColorPgRdDt1=new Date(parseInt(obj2.colordate));
					lastColorPgRdDt1=convertDateToStringWithTime(lastColorPgRdDt1);
					
				}
				else {
					lastColorPgRdDt1="";
				}
				
				<%-- After parsing ltpc and color values, go for displaying them in popup --%>
				dialog=jQuery('#content').dialog({
					autoOpen: false,
					title: "<spring:message code='requestInfo.title.updateMeterReads'/>",
					open: function(){
						if((window.parent.window.isSuccessfulUpdate==true) && (assetRowId == prevRowId))
						{	
							jQuery("#btnSubmit").attr('disabled',true);
							jQuery("#validationErrors").removeClass("error");
							jQuery("#validationErrors").addClass("info banner");
							jQuery("#validationErrors").html("LTPC Has Been Updated Today Already");
						}
						else{
							jQuery("#btnSubmit").attr('disabled',false);
							jQuery("#validationErrors").empty();
						};
					},
					modal: true,
					draggable: false,
					resizable: false,
					height: 550,
					width: 780,
					close: function(event,ui){
						
						dialog.dialog('destroy');
						dialog=null;
						clearContentsOfpopup();
						}
					});
				
				showToolTip("helpIconPopUp");
				jQuery('#content').show();
				writeData();
				dialog.dialog('open');
				
				
				if (window.PIE) {
		            document.getElementById("btnCancel").fireEvent("onmove");
		                document.getElementById("btnSubmit").fireEvent("onmove");
		        	}
				
				
				<%-- Popup section end --%>
				
				<%-- LTPC Success block ends--%>
				}			
				else {
					
					jQuery("#errorDiv").show();					
					jQuery("#errorDiv").html(obj2.error);
					
				}
			}
			else
			{
				<%-- error in parsing json block starts --%>
				jQuery("#errorDiv").show();					
				jQuery("#errorDiv").html("<spring:message code='meterRead.label.meterReadError'/>");
				<%-- error in parsing json block ends --%>
			}
		}
	});
	return false;
}
function viewDeviceDetail(assetId, serialNumber){
    /*closeCustomizedWindow();*/
    showOverlay();
	<%--window.location.href='<portlet:renderURL><portlet:param name="action" value="viewDeviceDetail" /></portlet:renderURL>&assetRowId=' + assetId;--%>
	window.location.href="<portlet:renderURL><portlet:param name='action' value='showServiceRequestPage2_brkfix' /></portlet:renderURL>&assetId="
		+assetId + "&notMyPrinterFlag=" + "false" + "&machineType="+ "" + "&productTLI=" + "" + "&serialNumber=";
	};

	image_error= function (MyPicture) {
		  
		  document.getElementById(MyPicture).src = '<html:imagesPath/>dummy_noimg.jpg'; 
		  
		};
		function gotoControlPanel(url) {
			callOmnitureAction('Device Finder', 'Device Finder List Goto Control Panel');
			controlPanelPopUpWindow.show();
			controlPanelPopUpWindow.io.set('uri',"<portlet:renderURL windowState='<%= LiferayWindowState.POP_UP.toString() %>'><portlet:param name='action' value='gotoControlPanel' /></portlet:renderURL>&controlPanelURL=" + url);
			controlPanelPopUpWindow.io.start();
	        <%--
	        new Liferay.Popup({
	            title: "",
	            position: [400,150], 
	            modal: true,
	            width: 400, 
	            height: 150,
	            xy: [100, 100],
	            onClose: function() {showSelect();},
	            url:"<portlet:renderURL
	                windowState='<%= LiferayWindowState.POP_UP.toString() %>'>" + 
	                "<portlet:param name='action' value='gotoControlPanel' />" + 
	                "</portlet:renderURL>&controlPanelURL=" + url
	            });  --%>
	    };

	    function showSelect(){
			var selects = document.getElementsByTagName('select');
			for (var i=0; i < selects.length; i++){
				var select = selects[i];
				select.style.visibility = "visible";
			}
		}  
//Ends
</script>
<%@ include file="/WEB-INF/jsp/favoriteAsset.js.jsp"%>
<script>
var controlPanelPopUpWindow;
AUI().use('aui-base',
'aui-io-plugin-deprecated',
'liferay-util-window',
function(A) {

	controlPanelPopUpWindow=Liferay.Util.Window.getWindow(
	{
	dialog: {
	centered: true,
	constrain2view: true,
	//cssClass: 'yourCSSclassName',
	modal: true,
	resizable: false,
	width: 400,
	height: 195,
	visible: false
	}
	}
	).plug(
	A.Plugin.IO,
	{
	autoLoad: false
	})



});


</script>