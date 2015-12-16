<style type="text/css"><%@ include file="../../css/jquery/jquery-ui.css" %></style>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<%@ include file="/WEB-INF/jsp/includeGrid.jsp" %>
<%-- <%@ include file="/WEB-INF/jsp/includeGrid.jsp"%> --%>
<%@ include file="/WEB-INF/jsp/includeDatePicker.jsp"%>
<%-- <script type="text/javascript" src="<html:rootPath/>js/mps.js"></script>
<script type="text/javascript" src="<html:rootPath/>js/jQuery/jquery.qtip-1.0.0-rc3.min.js"></script> --%>
<%@ taglib prefix="util" uri="/WEB-INF/tld/lexmark-util.tld" %>
<%@ page import="com.lexmark.services.util.LexmarkSPOmnitureConstants" %>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/mps.css"/>
<style>
.pageCountInput input{width:100px!important;}
</style>
<!--[if IE 7]>
	<style type="text/css"><%@ include file="/WEB-INF/css/mps_ie7.css" %></style>
<![endif]-->
<!--[if IE 8]>	
	<style>.relativeI{position:relative!important;}</style>
<![endif]-->
<%@page import="jsx3.gui.Alerts"%>


<%-- <spring:message var='bookmarked' code='deviceFinder.label.bookmarkedDevices'/>
<div class="portlet-section-header"></div> --%>
<div id="content-wrapper-inner">
	
	<jsp:include page="/WEB-INF/jsp/fleetmanagement/toggleView.jsp"></jsp:include>
	
<div class="journal-content-article">
      <h1><spring:message code="deviceFinder.title.deviceFinder"/></h1>
      <%-- <span class="step"><spring:message code="deviceDetail.label.deviceDetail" /></span> --%>
</div>
<div class="main-wrap">
	<div class="content">
		<jsp:include page="/WEB-INF/jsp/deviceFinder/deviceFinderLeft.jsp"></jsp:include> 
		<div id="errorDiv1" class="error" style="display:none;"></div>
		<div class="right-column">
		<!-- <h3 class="pageTitle" id="gridHeadr">All Devices</h3> -->
		
		<h3 class="pageTitle" id="allAsset">
		<spring:message code="deviceFinder.label.allDevices"/></h3>
		<h3 class="pageTitle" id="bookmarkedAsset" style="display:none;">
		<spring:message code="deviceFinder.label.bookmarkedDevices"/></h3>
		<h3 class="pageTitle" id="deviceLocationAsset" style="display:none;">
		<spring:message code="deviceFinder.label.allDevices.filterByDeviceLoc"/></h3>
		<h3 class="pageTitle" id="customerHierarchyAsset" style="display:none;">
		<spring:message code="deviceFinder.label.allDevices.filterByCHL"/></h3>
		
					<div class="grid-controls">
						<div class="utilities">
							<ul>
								<li class="first" onClick="return download('csv')"; style="cursor:pointer";><img src="<html:imagesPath/>transparent.png" class="ui_icon_sprite spreadsheet-icon"  title="<spring:message code='image.title.downloadCSV'/>"; /><spring:message code='rebranding.label.XLS'/></li>
								<li onClick="return download('pdf')"; style="cursor:pointer";><img src="<html:imagesPath/>transparent.png" class="ui_icon_sprite pdf-icon"  title="<spring:message code='image.title.downloadPDF'/>"; /><spring:message code='rebranding.label.PDF'/></li>
								<li onClick="return print()" style="cursor:pointer";><img src="<html:imagesPath/>transparent.png" class="ui_icon_sprite print-icon"  title="<spring:message code='requestInfo.tooltip.print'/>"; /><spring:message code='requestInfo.tooltip.print'/></li>
							</ul>
						</div><!-- utilities -->
						<div class="expand-min">
							<ul>
								<li class="first">
									<img src="<html:imagesPath/>transparent.png" class="ui_icon_sprite minimize-icon" onClick="minimizeAll()"; style="cursor:pointer;" />
	       		 				&nbsp;<a href="javascript:void(0)" onClick="minimizeAll()"; ><spring:message code="deviceFinder.label.minimizeAll"/></a>
								</li>
								<li>
									<img src="<html:imagesPath/>transparent.png" class="ui_icon_sprite expand-icon" onClick="expandAll()"; style="cursor:pointer;" />
           			 			&nbsp;<a href="javascript:void(0)" onClick="expandAll()"; ><spring:message code="deviceFinder.label.expandAll"/></a>
								</li>

								<li>
									<a href="#grid" id='headerImageButton'><img src="<html:imagesPath/>transparent.png" class="ui_icon_sprite customize-icon" style="cursor:pointer"; /></a>
									&nbsp;<a href="#grid" id='headerMenuButton'><spring:message code="serviceRequest.label.customize"/></a>
								</li>
								
							
								<li>
								<img src="<html:imagesPath/>transparent.png" style="cursor: pointer" onclick="javascript:doReset();" class="ui_icon_sprite reset-icon"
									alt="<spring:message code="serviceRequest.label.reset"/>" title="<spring:message code="serviceRequest.label.reset"/>" />
								<a href="javascript:doReset();" id="resetGridSetting" ><spring:message code="serviceRequest.label.reset"/></a>
								</li>
							</ul>
						</div>
						
						<!-- expand-min End-->  
						<!-- <div style="clear: right"></div>clear -->
					</div>	
					
					
			<div class="portlet-wrap rounded">
				<div class="portlet-wrap-inner">
					<div id="devicelistGrid" class="gridbox gridbox_light"></div>
	        		<div id="loadingNotification" class='gridLoading'>
	        			<img src="<html:imagesPath/>gridloading.gif"/><br>
	    			</div>
	    		</div>	<!-- portlet-wrap-inner end-->
	    			
	    			<!-- <div><span id="pagingArea"></span>&nbsp;<span id="infoArea"></span></div> -->
	    			
	    		<div class="pagination" id="paginationId">
          			<span id="pagingArea"></span><span id="infoArea"></span>          
          		</div>
          			
	    			<!-- mygrid_container -->
				</div>
				<!-- <div class="portlet-footer">
					<div class="portlet-footer-inner">
					</div>portlet-footer-inner
				</div> -->
			</div>	
			<form id="redirectToReturnSupplies" name="redirectToReturnSupplies" method="post" action="returnSupplies">
  				<input type="hidden" id="accnName" name="accnName" value="" /> 
  				<input type="hidden" id="accnId" name="accnId" value="" /> 
  				<input type="hidden" id="pageType" name="pageType" value="deviceFinder" />  	 	
  			</form>
				<!-- portlet-footer -->
			<!-- portlet-wrap -->
		</div><!-- right-column -->
		<!-- <div class="clear"></div>clear -->
	</div><!-- content -->
<!-- </div>main-wrap -->
<div id="dialogControlPanel" style="display:none;"></div>
</div>

<%--Div for CI BRD 13-10-14 Defect #7768 START--%>
		<div id="limitalertbox" style="min-width: 450px; display:none">
    		<div class="columnInner" id="loadingDialog"><div class="columnsTwo w50"><img src="/LexmarkOPSPortal/images/jQueryAlert/important.gif" style="width:50px; height:50px" ></img></div>
    		<div class="columnsTwo w70p">
      			<p><spring:message code="deviceFinder.download.limitResult"/></p>
      		</div>
      		<div class="buttonContainer popupBtnCont">
      		<button id="alertPopupOk" class="button" onclick="dialog.dialog('close');"><spring:message code="button.ok"/></button>
      		</div>
      		</div>
		</div>
		<div id="nodatafoundbox" style="min-width: 450px; display:none">
    		<div class="columnInner" id="loadingDialog"><div class="columnsTwo w50"><img src="/LexmarkOPSPortal/images/jQueryAlert/important.gif" style="width:50px; height:50px" ></img></div>
    		<div class="columnsTwo w70p">
      			<p><spring:message code="deviceFinder.download.noDataFound"/></p>
      		</div>
      		<div class="buttonContainer popupBtnCont">
      		<button id="nodataPopupOk" class="button" onclick="dialog.dialog('close');"><spring:message code="button.ok"/></button>
      		</div>
      		</div>
		</div>
<%--Div for CI BRD 13-10-14 Defect #7768 END--%>

<div id="dialog_createnew" style="display: none;">
<div id="totalContent">
<div id="createNewRequestPageData"></div>
<jsp:include page="/WEB-INF/jsp/common/accountSelection.jsp"/> 
</div>
</div>

<%-- <div id="dialog_createNew" style="display: none"></div> --%>
<div id="dialog_meterReads" style="display: none">
<jsp:include page="/WEB-INF/jsp/common/pageCountPopUp.jsp"/>
</div>

<script type="text/javascript">
var dialog;
var lastColorPgCnt1; 
var lastPgCnt1;
var lastPgRdDt1;
var lastColorPgRdDt1;
var assetRowId1;
var isSuccessfulUpdate=false;
var errorAttr="${errorAttribute}";
var prevRowId = "";
var globalAssetId = null;
var globalIsFavorite = null;
var serialNumber=null;
var assetTag=null;
var ipAddress=null;
var modelNo=null;
var deviceTag=null;
var pageType="";
var accountID="";
var accountName="";

jQuery(document).ready(function() {
	if(errorAttr!="")
	{	
		jQuery("#errorDiv1").show();		
		jQuery("#errorDiv1").append(errorAttr);
	}
	
});

//-----------------------------------Added for CI BRD 13-10-14 Defect #7768 START----------------------------------
function popupDialogLimitExceed(){
	dialog = jQuery("#limitalertbox").dialog({
		autoOpen: false,
		open: function() {jQuery(".ui-dialog-titlebar-close").hide();},
		modal: true,
		closeOnEscape: false,
		title: '<spring:message code="deviceFinder.popUp.heading"/>',
		resizable: false,
		position: "center",
		width: 450,
		height: 400
		
		});
	
	dialog.dialog("open");
	
	if (window.PIE) {
        document.getElementById("alertPopupOk").fireEvent("onmove");
    }
    
	return false;
}

function popupDialogNoData(){
	dialog = jQuery("#nodatafoundbox").dialog({
		autoOpen: false,
		open: function() {jQuery(".ui-dialog-titlebar-close").hide();},
		modal: true,
		closeOnEscape: false,
		title: '<spring:message code="deviceFinder.popUp.noDataAlert"/>',
		resizable: false,
		position: "center",
		width: 450,
		height: 400
		
		});
	
	dialog.dialog("open");
	
	if (window.PIE) {
        document.getElementById("nodataPopupOk").fireEvent("onmove");
    }
    
	return false;
}

function download(type){
	// CI 13-10-14 - get the total row in the all device dhtmlx grid
	var total_Device_Size = deviceListGrid.getRowsNum();	
	if(deviceListGrid.getRowId(0)==null){
		popupDialogNoData(); // CI BRD 13-10-14 Defect #7768
		document.getElementById("nodatafoundbox").style.display = 'block';
			return false;
	}
	// Added for CI 13-10-14 - Check the grid data exceeds 20000 limit or not
	if(total_Device_Size > 10000){
		popupDialogLimitExceed();// CI BRD 13-10-14 Defect #7768
		document.getElementById("limitalertbox").style.display = 'block';
			return false;
	}
	//end
	window.location="${downloadDeviceListURL}&downloadType=" + type;
};
//---------------------------Added for CI BRD 13-10-14 Defect #7768 END----------------------------------


function closeDialog()
{
	dialog.dialog('close');
	dialog=null;	
}

function openPopUp(assetRowId, serialNumber, ipAddress, productLine, assetTag)
{
	//brmandal changed for page count pop up
	callOmnitureAction('<%=LexmarkSPOmnitureConstants.DEVICEFINDER%>', '<%=LexmarkSPOmnitureConstants.DEVICEFINDERLISTUPDATEPAGECOUNT%>'); 
	<%-- After parsing ltpc and color values, go for displaying them in popup --%>
					showOverlay();
					dialog=jQuery('#pageCount').dialog({
						autoOpen: false,
						title: "<spring:message code='requestInfo.title.updatePageCounts'/>",					
						modal: true,
						draggable: false,
						resizable: false,
						height: 550,
						width: 780,
						close: function(event,ui){
							hideOverlay();						
							dialog.dialog('destroy');
							dialog=null;
							clearContentsOfpopup1();
							}
						});				
					jQuery('#pageCount').show();
					dialog.dialog('open');	
					initializePageCountGrid(assetRowId, serialNumber, ipAddress, productLine, assetTag);
					jQuery('#serialNumber').html(serialNumber);
					jQuery('#ipAddress').html(ipAddress);
					jQuery('#productLine').html(productLine);
					jQuery('#assetTag').html(assetTag);
					window.document.getElementById("assetRowId").innerHTML=assetRowId;
						
	return false;
}	
	
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
	var searchCriteria="";
	function getURLWithViewType(type, value) {
		var url = "<portlet:resourceURL id='retrieveDeviceListinDM'></portlet:resourceURL>";
		if (type == 'CATEGORY') {
	        if(value == 'VIEWALL') {
	            url = "<portlet:resourceURL id='retrieveDeviceListinDM'><portlet:param name='view' value='VIEWALL' /></portlet:resourceURL>";
	        }
	        if(value == 'BOOKMARKED') {
	            url = "<portlet:resourceURL id='retrieveDeviceListinDM'><portlet:param name='view' value='BOOKMARKED' /></portlet:resourceURL>";
	        }
		}
		return url;
	};
	
	
	function requestService(assetId, createServiceReqFlag, chkConsElgFlag, serialNumber_org, assetTag_org, ipAddress_org, modelNo_org, deviceTag_org, accountId, accName, aggrementName){
callOmnitureAction('<%=LexmarkSPOmnitureConstants.DEVICEFINDER%>', '<%=LexmarkSPOmnitureConstants.DEVICEFINDERLISTCREATEREQUEST%>'); 
			accountName = accName;
            pageType="deviceFinder";
            accountID = accountId;
			var url="<portlet:renderURL windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>"><portlet:param name='action' value='createNewRequestPopUp'/></portlet:renderURL>&chkConsElgFlag="+chkConsElgFlag
			+"&assetId=" + assetId+"&createServiceRequestFlag="+createServiceReqFlag+"&accountId="+accountId;
			serialNumber =serialNumber_org; 
			assetTag = assetTag_org; 
			ipAddress =ipAddress_org; 
			modelNo = modelNo_org; 
			deviceTag =deviceTag_org;
			showOverlay();
			
			jQuery('#createNewRequestPageData').load(url,function(){
				
				dialog=jQuery('#totalContent').dialog({
					autoOpen: false,
					title: '<spring:message code="button.createNewRequest"/>',
					modal: true,
					height: 460,
					width: 700,
					open: function(){
						jQuery('#totalContent').show();
					},
					position: ['center','top'],
					close: function(event,ui){
						 hideOverlay();
						 dialog.dialog('destroy');
						 
						 dialog=null;
						 jQuery('#createNewRequestPageData').empty();
						 jQuery('#accountInitialize').hide();
						 if(ajaxAccountSelection=="success"){
								showOverlay();
								window.location.href=linkObj_accSelect.href;
							}
						 
						}
					});
				jQuery(document).scrollTop(0);
				dialog.dialog('open');
				});
			
			return false;
	};
	
	
	function viewDeviceDetail(assetId, serialNumber, isDeviceBookmarked){
	callOmnitureAction('<%=LexmarkSPOmnitureConstants.DEVICEFINDER%>', '<%=LexmarkSPOmnitureConstants.DEVICEFINDERVIEWDEVICEDETAIL%>'); 
	if(globalAssetId!=null && globalAssetId==assetId && globalIsFavorite!=null){		
	isDeviceBookmarked = globalIsFavorite;
	globalAssetId = null;
	globalIsFavorite =null;
	}
    showOverlay();
	window.location.href='<portlet:renderURL><portlet:param name="action" value="viewDeviceDetail" /></portlet:renderURL>&assetRowId=' + assetId
	+"&isDeviceBookmarked=" + isDeviceBookmarked;
	};
	
	var deviceListGrid = new dhtmlXGridObject('devicelistGrid');
	deviceListGrid.setImagePath("<html:imagesPath/>/gridImgs/");
	//Modified for MPS
	//House Number, District and County Added for CI BRD 13-10-08--STARTS
	var gridHeaderCode="<spring:message code='deviceFinder.listHeader.deviceFields'/>";
	var gridFilterText=",#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#combo_filter";
	var gridColumnAlignment="left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left";
	var gridWidth="30,120,120,120,120,120,120,120,120,120,120,120,120,120,120,120,120,120,120,120,120,120,120,120,120,80";
	var gridReadOnly="sub_row,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro";
	var gridColumnSort="na,str,str,str,str,str,str,str,str,str,str,na,na,str,str,str,str,str,str,str,na,na,str,na,na,na";
	var gridColumnVisibility="true,true,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false";
	
	var currentURL = window.location.href;
    var isGridView = false;
	if(currentURL.indexOf('/grid-view') >-1)
     {	
    	isGridView = true;
    	 gridHeaderCode="<spring:message code='deviceFinder.listHeader.deviceFields.gridview'/>";
    	 gridFilterText=gridFilterText+",#combo_filter";
    	 gridColumnAlignment=gridColumnAlignment+",left";
    	 gridWidth=gridWidth+",80";
    	 gridReadOnly=gridReadOnly+",ro";
    	 gridColumnSort=gridColumnSort+",na";
    	 gridColumnVisibility=gridColumnVisibility+",false";
    	//Added for addition of two column LBS Flag And lodAddress  Ends
     }
	    deviceListGrid.setHeader(gridHeaderCode);
		
		//Modified for MPS
		deviceListGrid.attachHeader(gridFilterText);
		//Modified for MPS
		deviceListGrid.setInitWidths(gridWidth);
		
		//Modified for MPS	
		deviceListGrid.setColAlign(gridColumnAlignment);
		
		//Modified for MPS
		deviceListGrid.setColTypes(gridReadOnly);
		
	
	//Modified for MPS
	deviceListGrid.setColSorting(gridColumnSort);
    deviceListGrid.enableAutoHeight(true);
    deviceListGrid.enableMultiline(true);
    deviceListGrid.enableHeaderMenuForButton('headerImageButton');
    
    deviceListGrid.setSkin("light");
    deviceListGrid.setLockColVisibility(gridColumnVisibility);
    deviceListGrid.enableColumnMove(true);

    deviceListGrid.sortIndex = 1;
    deviceListGrid.columnChanged = true;
    
    var lbsAddressLODList = [];		
    <c:forEach items="${lbsAddressLOD}" var="lbsAddressLOD" varStatus = "status" >
    lbsAddressLODList[${status.index}] = ["${lbsAddressLOD.key}","${lbsAddressLOD.value}"];
    </c:forEach>
    deviceListGrid.setCustomizeCombo(lbsAddressLODList,25);
    var lbsAddressFlagFilter=[];
    lbsAddressFlagFilter[0]=["Y","Yes"];
    lbsAddressFlagFilter[1]=["N","No"];    
    deviceListGrid.setCustomizeCombo(lbsAddressFlagFilter,26);
    deviceListGrid.attachEvent("onXLS", function() {
    	resetValue = false;
    	if(colsWidth==""){
    		resetValue = true;
    	}
        refreshGridSettingOnPage(deviceListGrid);
        dBPersistentFlag = false;
        document.getElementById('loadingNotification').style.display = 'block';
    	if(!deviceListGrid.loadHiddenColumns(colsHidden)  && resetValue || gridToBeReset) {
            setDefaultHiddenColumns();
            gridToBeReset = false;
            resetValue = false;
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
        
    });

	deviceListGrid.init();
	deviceListGrid.prftInit();
	deviceListGrid.enableHeaderMenuForButton('headerMenuButton');
	
	//************************ Grid filer**********************//
	deviceListGrid.attachEvent("onFilterStart", function(indexes,values){
	// The value of filtering Criterias can be get from the values by the position
	// e.g.if values=" ,,,,," than 7 is the filtering value for second columns
	// Add additional , for the subrow of grid
	
	
		deviceListGrid.filterValues = ","+values;
		customizedGridURL = updateGridURL(customizedGridURL, deviceListGrid.getSortColByOffset(), deviceListGrid.a_direction, deviceListGrid.filterValues);
		//Changes for JAN release
		saveGridQueryParams('devicelistGrid',
    	    	generateGridQueryParams(deviceListGrid,null,null,null),
    	    	setGridFilerTimer(reloadData)
    	    	);
    	 
    	//End Jan release
    	
    
    });
				
	deviceListGrid.enablePaging(true, 20, 10, "pagingArea", true, "infoArea");
	deviceListGrid.setPagingSkin("bricks");
    deviceListGrid.loadOrder(colsOrder);
    deviceListGrid.loadPagingSorting(colsSorting,1);
    deviceListGrid.loadSize(colsWidth);
    //Modified for MPS
      deviceListGrid.filterValues = ",,,,,,,,,,,,,,,,,,,,,,,,,"; //filterValues changed for CI Defect 9714
      //deviceListGrid.filterValues = ",,,,,,,,,,,,,,,,,,,,,,,,"; 	
      if(!"${searchValue}"){
		
		//Changes for JAN release
		var isFromBack="${backToList}";
		if(isFromBack=="true"){
			
			jQuery.getJSON("${retrieveGridParamsForCM}&gridId=devicelistGrid",function(result){
				if(result.status!='blank'){
				      
					
					var filVal=result.filterValues.substring(1,result.filterValues.length);
					deviceListGrid.setFiltersValue(filVal);
					deviceListGrid.filterValues = result.filterValues;
					
		    	}
				customizedGridURL = populateURLCriterias();
				deviceListGrid.loadXML(customizedGridURL);
			});
		}else{
			customizedGridURL = populateURLCriterias();
			
			deviceListGrid.loadXML(customizedGridURL);
			}
    	//End Jan release
	}
	else{
		
		searchCriteria = "${searchName}" + "^" + "${searchValue}";
		customizedGridURL = updateGridURL("<portlet:resourceURL id='retrieveDeviceListinDM'></portlet:resourceURL>&searchCriterias=" + searchCriteria, deviceListGrid.getSortColByOffset(), deviceListGrid.a_direction, deviceListGrid.filterValues);
		deviceListGrid.loadXML(customizedGridURL);
	}
	
	deviceListGrid.enableAutoSavingToDB("${saveCustomizedGridSettingURL}");
	
	deviceListGrid.attachEvent("onRowSelect",function(rowId,cellIndex){
 	closeCustomizedWindow();
		if (cellIndex > 1) {
			var cell=deviceListGrid.cellById(rowId,0);
			var row = deviceListGrid.getRowById(rowId);
			if(!row._expanded){
				cell.open();
			}else{
				cell.close();
			}
		}
    });
	
	
	deviceListGrid.attachEvent("onMouseOver", function(id,ind){
		if(ind!=0&&ind!=1){
			var style = deviceListGrid.cells(id,ind).cell.style;
		    style.cssText += ";cursor: pointer;";
			return true;
		}
    });
    
	deviceListGrid.attachEvent("onBeforeSorting", customColumnSort);
	deviceListGrid.sortRows = function(col, type, order) {}
	
	deviceListGrid.attachEvent("onPaging", function(count){
		refreshGridSettingOnPage(deviceListGrid);
	});
	function customColumnSort(ind) {
	    callOmnitureAction('<%=LexmarkSPOmnitureConstants.DEVICEFINDER%>', '<%=LexmarkSPOmnitureConstants.DEVICEFINDERLISTSORT%>'); 
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
	};
	
	function validateSerchValues(columnName, value){
		if(columnName == "serialNumber")
			if(value.length != 7 && value.length != 11 && value.length != 13){
				showError("<spring:message code='validation.error.invalidSerialNumber'/>");
				return false;
			}
		return true;
	};
	
	function setDefaultHiddenColumns() {
		//alert("set def hid cols");
		deviceListGrid.setColumnHidden(7,true);
		deviceListGrid.setColumnHidden(8,true);
		//deviceListGrid.setColumnHidden(9,true);
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
		deviceListGrid.setColumnHidden(20,true);
		deviceListGrid.setColumnHidden(21,true);
		deviceListGrid.setColumnHidden(22,true);
		deviceListGrid.setColumnHidden(23,true);
		//deviceListGrid.setColumnHidden(24,true);
	};
	
	function onSelectChange(){
		clearMessage();
		document.getElementById("txtSearchValue").value="";
	};
   
	function reloadData() {
		clearMessage();
		refreshGridSettingOnPage(deviceListGrid);
		deviceListGrid.clearAndLoad(customizedGridURL);
	};
	
    function populateURLCriterias(){
    	var url = getURLWithViewType('CATEGORY', viewCategory);
    	
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
    };
    
	function download(type){
		callOmnitureAction('<%=LexmarkSPOmnitureConstants.DEVICEFINDER%>', '<%=LexmarkSPOmnitureConstants.DEVICEFINDERLISTDOWNLOAD%>'); 
		// CI 13-10-14 - get the total row in the all device dhtmlx grid
		var total_Device_Size = deviceListGrid.getRowsNum();	
		if(deviceListGrid.getRowId(0)==null){
			popupDialogNoData(); // CI BRD 13-10-14 Defect #7768
			document.getElementById("nodatafoundbox").style.display = 'block';
 			return false;
		}
		// Added for CI 13-10-14 - Check the grid data exceeds 20000 limit or not
		if(total_Device_Size > 10000){
			popupDialogLimitExceed();// CI BRD 13-10-14 Defect #7768
			document.getElementById("limitalertbox").style.display = 'block';
 			return false;
		}
		//end
		 //For LBS flag
	    var currURL=window.location.href;
	    if(currURL.indexOf('/grid-view')==-1){
	   // alert("detach");
	    	window.location="${downloadDeviceListURL}&downloadType=" + type +"&gVPage=false";
	    }
		
	    else{
	    	window.location="${downloadDeviceListURL}&downloadType=" + type +"&gVPage=true";
	    }
		
	};
	
	function minimizeAll() {
		 callOmnitureAction('<%=LexmarkSPOmnitureConstants.DEVICEFINDER%>', '<%=LexmarkSPOmnitureConstants.DEVICEFINDERLISTMINIMIZEALL%>'); 
		deviceListGrid.forEachRow(function(id){
	   		var cell=deviceListGrid.cellById(id,0);
	   		cell.close();
	   		subRowOpenFlag=false;
	   	});
	};
	
	function expandAll() {
		 callOmnitureAction('<%=LexmarkSPOmnitureConstants.DEVICEFINDER%>', '<%=LexmarkSPOmnitureConstants.DEVICEFINDERLISTEXPANDALL%>'); 
		var state = deviceListGrid.getStateOfView(); //get details about current page
		for (var i = state[1]; i<state[2] ; i++){ //for all rows on the page
		var cell = deviceListGrid.cellByIndex(i,0);
		if (cell.open)
		{
			cell.open(); //open sub
		}
	}
};
	
	function doReset(){
	   callOmnitureAction('<%=LexmarkSPOmnitureConstants.DEVICEFINDER%>', '<%=LexmarkSPOmnitureConstants.DEVICEFINDERLISTRESET%>'); 
		/*resetGridSetting('devicelistGrid', resetCallback);*/
		/* changes for making the filter value clear after reset in device finder*/
		for(var i=0;i<deviceListGrid.getColumnsNum()-1;i++){
			deviceListGrid.hdr.getElementsByTagName("INPUT")[i].value="";
		}
		deviceListGrid.filterValues=",,,,,,,,,,,,,,,,,,,,,,,,"
		resetGridSettingForDMCM('devicelistGrid', resetCallback);
	};

	function resetCallback() {
		clearMessage();
		colsOrder = "0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24";
		colsWidth = "20,120,120,120,120,120,120,120,120,120,120,120,120,120,120,120,120,120,120,120,120,120,120,120,120";
		colsSorting = "1,asc";
		colsHidden = "";
		gridToBeReset = true;
		deviceListGrid.sortIndex = 1;
		dBPersistentFlag = false;
		deviceListGrid.setColumnsVisibility("false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false");
        deviceListGrid.loadOrder(colsOrder);
        deviceListGrid.loadPagingSorting(colsSorting,1);
        deviceListGrid.setInitWidths(colsWidth);
		customizedGridURL = updateGridURL(customizedGridURL, 1, 'asc', deviceListGrid.filterValues);
		deviceListGrid.clearAndLoad(customizedGridURL);
	};
	//House Number, District and County Added for CI BRD 13-10-08 --ENDS
	
	function print() {
		callOmnitureAction('<%=LexmarkSPOmnitureConstants.DEVICEFINDER%>', '<%=LexmarkSPOmnitureConstants.DEVICEFINDERLISTPRINT%>'); 
		url ="<portlet:renderURL windowState='<%= LiferayWindowState.POP_UP.toString() %>'>"+
		"<portlet:param name='action' value='printDeviceList' />"+
		"</portlet:renderURL>";
		url = url + "&pageNumber=" + deviceListGrid.currentPage;
	    var iWidth=1000;
	    var iHeight=600;
	    var iTop = (window.screen.availHeight-30-iHeight)/2;
	    var iLeft = (window.screen.availWidth-10-iWidth)/2;
	    window.open(url,
	    		'DeviceList',
			    'height='+iHeight+
			    ',innerHeight='+
			    iHeight+',width='+
			    iWidth+',innerWidth='+
			    iWidth+',top='+iTop+
			    ',left='+iLeft+
			    ',status=no,toolbar=no,menubar=no,location=no,resizable=yes,scrollbars=yes,titlebar=no');
	};

    <%-- function gotoControlPanel(url) {
		callOmnitureAction('<%=LexmarkSPOmnitureConstants.DEVICEFINDER%>', '<%=LexmarkSPOmnitureConstants.DEVICEFINDERLISTGOTOCONTROLPANEL%>'); 
		controlPanelPopUpWindow.show();
		controlPanelPopUpWindow.io.set('uri',"<portlet:renderURL windowState='<%= LiferayWindowState.POP_UP.toString() %>'><portlet:param name='action' value='gotoControlPanel' /></portlet:renderURL>&controlPanelURL=" + url);
		controlPanelPopUpWindow.io.start(); --%>
        <%--
        new Liferay.Popup({
            title: "",
            position: [400,150], 
            modal: true,
            width: 400, 
            height: 150,
            xy: [100, 100],
            onClose: function() {showSelect();},
            url:"<portlet:renderURL windowState='<%= LiferayWindowState.POP_UP.toString() %>'>"+
			"<portlet:param name='action' value='gotoControlPanel' />"+
			"</portlet:renderURL>&controlPanelURL=" + url});  --%>
   // };
    function hideSelect() {
		var selects = document.getElementsByTagName('select');
		for (var i=0; i < selects.length; i++){
			var select = selects[i];
			select.style.visibility = "hidden";
		}
	};

	function showSelect(){
		var selects = document.getElementsByTagName('select');
		for (var i=0; i < selects.length; i++){
			var select = selects[i];
			select.style.visibility = "visible";
		}
	}  
	function closeCustomizedWindow(){
		if(document.getElementById('grid_menu')!=null)
		{
	   	  document.getElementById('grid_menu').style.display = 'none';
		}
	   }
	image_error= function (MyPicture) {
		  
		  document.getElementById(MyPicture).src = '<html:imagesPath/>/dummy_noimg.jpg'; 
		  
		};
</script>
<%@ include file="/WEB-INF/jsp/deviceFinder/favoriteAsset.js.jsp"%>
<script type="text/javascript">
var portlet_name="<%=LexmarkSPOmnitureConstants.DEVICEFINDER%>";
var action_favoriteAsset="<%=LexmarkSPOmnitureConstants.DEVICEFINDERLISTBOOKMARKUNBOOKMARK%>";
</script>
<script>
/* var controlPanelPopUpWindow;
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



}); */

function gotoControlPanel(url){
	callOmnitureAction('<%=LexmarkSPOmnitureConstants.DEVICEFINDER%>', '<%=LexmarkSPOmnitureConstants.DEVICEFINDERLISTGOTOCONTROLPANEL%>');
	var url="<portlet:renderURL windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>"><portlet:param name='action' value='gotoControlPanel'/></portlet:renderURL>&controlPanelURL=" + url;
		showOverlay();	
			jQuery('#dialogControlPanel').load(url,function(){
				dialog=jQuery('#contentControlPanel').dialog({
				autoOpen: false,
				title: '',
				modal: true,
				draggable: false,
				resizable: false,
				position: 'center',
				height: 'auto',
				width: 400,
				open: function(){	
					jQuery('#contentControlPanel').show();
				},
				close: function(event,ui){
					hideOverlay();
					dialog.dialog('destroy');					 
					dialog=null;
					jQuery('#contentControlPanel').remove();
					},
				error: function (jqXHR, textStatus, errorThrown) {
					hideOverlay();
					dialog.dialog('destroy');					 
					dialog=null;
					jQuery('#contentControlPanel').remove();
					}
				});
			dialog.dialog('open');
			});						

}

</script>