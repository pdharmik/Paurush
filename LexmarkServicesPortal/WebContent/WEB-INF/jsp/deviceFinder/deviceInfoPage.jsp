<%@ include file="/WEB-INF/jsp/include.jsp"%>

<jsp:include page="/WEB-INF/jsp/includeDatePicker.jsp"></jsp:include>

<%@ include file="/WEB-INF/jsp/includeGrid.jsp"%>

<%@ taglib prefix="util" uri="/WEB-INF/tld/lexmark-util.tld" %>
<%@ page import="com.lexmark.services.util.LexmarkSPOmnitureConstants" %>
<portlet:resourceURL var="updateURL" id="updateUserFavoriteAsset"/>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/mps.css" />
<style type="text/css"><jsp:include page="/WEB-INF/css/jquery/jquery-ui.css" /></style>

<!--[if IE 7]>
	<style type="text/css"><jsp:include page="/WEB-INF/css/mps_ie7.css" /></style>
<![endif]-->

<style>


 .ui-tabs .ui-tabs-nav li{
 height:26px !important
 }
  .ui-tabs .ui-tabs-nav li a {
  min-width:109px;
  padding-top: 2px !important;
   width: auto!important;
}

.ui-tabs li a, .ui-tabs li a:hover, .ui-tabs li span, .ui-tabs li span:hover {
	height: auto !important;
}

.ie7 .button, .ie7 .button_cancel {
behavior: url(/LexmarkServicesPortal/WEB-INF/css/PIE.htc) !important;
}
</style>

<script type="text/javascript">
var headers_allRequest="";
var filter_allRequest="";
var width_allRequest="";
var colalign_allRequest="";
var coltype_allRequest="";
var colsort_allRequest="";
var colVisibility_allRequest="";
var filterValue_allRequest="";
var defaulthiddenCols_allRequest=new Array();
var serialNumber=null;
var assetTag=null;
var ipAddress=null;
var modelNo=null;
var deviceTag=null;

var dhtmlxGridId;
var gridObj;
var assetRowId1="${deviceDetailForm.device.assetId}";
var lastColorPgCnt1='${deviceDetailForm.device.lastColorPageCount}';
if(lastColorPgCnt1.length<=0)
{

lastColorPgCnt1 = ""; 
}


var lastPgCnt1='${deviceDetailForm.device.lastPageCount}';

var lastPgRdDt1='<util:dateFormat value="${deviceDetailForm.device.lastPageReadDate}" showTime="true" timezoneOffset="${timezoneOffset}"></util:dateFormat>';

var lastColorPgRdDt1='<util:dateFormat value="${deviceDetailForm.device.lastColorPageReadDate}" showTime="true" timezoneOffset="${timezoneOffset}"></util:dateFormat>';

var accountId='${deviceDetailForm.device.account.accountId}';

var dialog;
var changeRequestUrl = '';

var linkClicked='';

var isSuccessfulUpdate=false;

	function show_load(li_id)
	{
	
	jQuery("#gridContainerDiv_supplies_requests").css("display","none");
	jQuery("#gridContainerDiv_supplies_requests").empty();
	
	jQuery("#gridContainerDiv_change_requests").css("display","none");
	jQuery("#gridContainerDiv_change_requests").empty();
	
	jQuery("#gridContainerDiv_service_requests").css("display","none");
	jQuery("#gridContainerDiv_service_requests").empty();

	jQuery("#gridContainerDiv_all_request_types").css("display","none");
	jQuery("#gridContainerDiv_all_request_types").empty();
		switch(li_id)
		{
			case "li_all_request_types":
				callOmnitureAction('<%=LexmarkSPOmnitureConstants.DEVICEFINDERDETAIL%>', '<%=LexmarkSPOmnitureConstants.DEVICEFINDERDETAILREQUESTHISTORYALLREQUESTTYPE%>'); 
				linkClicked=1;
				jQuery("#gridContainerDiv_all_request_types").css('height','25px');
				jQuery('#history_heading').html('<h3 class="pageTitle"><spring:message code="requestInfo.requestHistory.heading.allRequests"/></h3>');
				colsOrder = new String("${gridSettings_gridContainerDiv_all_request_types.colsOrder}");
								
				colsWidth = new String("${gridSettings_gridContainerDiv_all_request_types.colsWidth}");
				
				colsSorting = new String("${gridSettings_gridContainerDiv_all_request_types.colsSorting}");
				
				colsHidden = new String("${gridSettings_gridContainerDiv_all_request_types.colsHidden}");
				
				
				jQuery("#gridContainerDiv_all_request_types").show();
				
				document.getElementById(li_id).className="selected";
				document.getElementById('li_supplies_requests').className="";
				 <%--Commented as of now MPS 2.1 will uncomment after jan release 
				 document.getElementById('li_change_requests').className="";--%>
				document.getElementById('li_service_requests').className="";
				
				checkGridTypeAndLoad('ALL_REQUESTS');
				
			break;
			 case "li_supplies_requests":
				 callOmnitureAction('<%=LexmarkSPOmnitureConstants.DEVICEFINDERDETAIL%>', '<%=LexmarkSPOmnitureConstants.DEVICEFINDERDETAILREQUESTHISTORYSUPPLIESREQUEST%>'); 
				 linkClicked=2;
				 jQuery("#gridContainerDiv_supplies_requests").css('height','25px');
				 jQuery('#history_heading').html('<h3 class="pageTitle"><spring:message code="requestInfo.requestHistory.heading.supplyRequests"/></h3>');
				colsOrder = new String("${gridSettings_gridContainerDiv_supplies_requests.colsOrder}");
				
				colsWidth = new String("${gridSettings_gridContainerDiv_supplies_requests.colsWidth}");
				
				colsSorting = new String("${gridSettings_gridContainerDiv_supplies_requests.colsSorting}");
				
				colsHidden = new String("${gridSettings_gridContainerDiv_supplies_requests.colsHidden}");
							
				jQuery("#gridContainerDiv_supplies_requests").show();
				
				document.getElementById(li_id).className="selected";
				document.getElementById('li_all_request_types').className="";
				 <%--Commented as of now MPS 2.1 will uncomment after jan release 
				 document.getElementById('li_change_requests').className="";--%>
				document.getElementById('li_service_requests').className="";
				
				checkGridTypeAndLoad('SUPPLIES_REQUESTS');
				
			break;
			case "li_change_requests":
				callOmnitureAction('<%=LexmarkSPOmnitureConstants.DEVICEFINDERDETAIL%>', '<%=LexmarkSPOmnitureConstants.DEVICEFINDERDETAILREQUESTHISTORYCHANGEREQUEST%>');
				linkClicked=3;
				jQuery("#gridContainerDiv_change_requests").css('height','25px');
				jQuery('#history_heading').html('<h3 class="pageTitle"><spring:message code="requestInfo.requestHistory.heading.changeRequests"/></h3>');
				colsOrder = new String("${gridSettings_gridContainerDiv_change_requests.colsOrder}");
				colsWidth = new String("${gridSettings_gridContainerDiv_change_requests.colsWidth}");
				colsSorting = new String("${gridSettings_gridContainerDiv_change_requests.colsSorting}");
				colsHidden = new String("${gridSettings_gridContainerDiv_change_requests.colsHidden}");
				
				jQuery("#gridContainerDiv_change_requests").show();
				
				document.getElementById(li_id).className="selected";
				document.getElementById('li_supplies_requests').className="";
				document.getElementById('li_all_request_types').className="";
				document.getElementById('li_service_requests').className="";
				
				checkGridTypeAndLoad('CHANGE_REQUESTS');
				
			break;
			case "li_service_requests":
				callOmnitureAction('<%=LexmarkSPOmnitureConstants.DEVICEFINDERDETAIL%>', '<%=LexmarkSPOmnitureConstants.DEVICEFINDERDETAILREQUESTHISTORYSERVICEREQUEST%>'); 
				linkClicked=4;
				jQuery("#gridContainerDiv_service_requests").css('height','25px');
				jQuery('#history_heading').html('<h3 class="pageTitle"><spring:message code="requestInfo.requestHistory.heading.serviceRequests"/></h3>');
				colsOrder = new String("${gridSettings_gridContainerDiv_service_requests.colsOrder}");
				colsWidth = new String("${gridSettings_gridContainerDiv_service_requests.colsWidth}");
				colsSorting = new String("${gridSettings_gridContainerDiv_service_requests.colsSorting}");
				colsHidden = new String("${gridSettings_gridContainerDiv_service_requests.colsHidden}");
				

				jQuery("#gridContainerDiv_service_requests").show();
				
				document.getElementById(li_id).className="selected";
				document.getElementById('li_supplies_requests').className="";
				 <%--Commented as of now MPS 2.1 will uncomment after jan release 
				 document.getElementById('li_change_requests').className="";--%>
				document.getElementById('li_all_request_types').className="";
				
				checkGridTypeAndLoad('SERVICE_REQUESTS');
				
			break; 
		}
	}
	
	function closeDialog()
	{
		dialog.dialog('close');
		dialog=null;
		
		
	}
	
	function openPopUp(assetRowId, serialNumber, ipAddress, productLine, assetTag){
		//brmandal changed for page count changes - MPS phase 2.1
		callOmnitureAction('<%=LexmarkSPOmnitureConstants.DEVICEFINDERDETAIL%>', '<%=LexmarkSPOmnitureConstants.DEVICEFINDERDETAILUPDATEPAGECOUNT%>'); 
		//alert("in openpop up");
		showOverlay();
		//alert("in openpop up1");
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
		/*alert("in openpop up2");
		alert(assetRowId);
		alert(serialNumber);
		alert(ipAddress);
		alert(productLine);
		alert(assetTag);*/
		initializePageCountGrid(assetRowId, serialNumber, ipAddress, productLine, assetTag);
		jQuery('#serialNumber').html(serialNumber);
		jQuery('#ipAddress').html(ipAddress);
		jQuery('#productLine').html(productLine);
		jQuery('#assetTag').html(assetTag);
		//alert("assetRowId "+assetRowId);
		//jQuery('#assetIdForPageCounts').html(assetRowId);
		window.document.getElementById("assetRowId").innerHTML=assetRowId;
		//pageCountGrid.loadXML("<portlet:resourceURL id='getPageCountPopUp'/>" + "&assetRowId=" + assetRowId + "&serialNumber=" + serialNumber + "&ipAddress=" + ipAddress + "&productLine=" + productLine +"&assetTag="+ assetTag);
		//hideOverlayPopup();
	
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
	
	<%-- function gotoControlPanel(url) {
		 callOmnitureAction('<%=LexmarkSPOmnitureConstants.DEVICEFINDERDETAIL%>', '<%=LexmarkSPOmnitureConstants.DEVICEFINDERDETAILIPADDRESS%>'); 
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
                "</portlet:renderURL>&controlPanelURL=" + url
            }); --%>
    //};

    function requestService(assetId, createServiceRequestFlag, chkConsElgFlag, serialNumber_org, assetTag_org, ipAddress_org, modelNo_org, deviceTag_org){
	 callOmnitureAction('<%=LexmarkSPOmnitureConstants.DEVICEFINDERDETAIL%>', '<%=LexmarkSPOmnitureConstants.DEVICEFINDERLISTCREATEREQUEST%>'); 
    	
var url= "<portlet:renderURL windowState='<%= LiferayWindowState.EXCLUSIVE.toString() %>'><portlet:param name='action' value='createNewRequestPopUp'/>"
+"</portlet:renderURL>&createServiceRequestFlag="+createServiceRequestFlag+"&chkConsElgFlag="+chkConsElgFlag+"&assetId="+assetId;
		//alert("serialNumber "+serialNumber+" deviceTag: "+deviceTag);
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
			
			dialog.dialog('open');
			});
		
		return false;
	};

	
	function checkGridTypeAndLoad(gridType) {

		var srGridUrl = '';
		
		switch(gridType)
		{
			case "ALL_REQUESTS":
			
				changeRequestUrl = "<portlet:resourceURL id='retrieveHistoryListByAssetId'></portlet:resourceURL>&timezoneOffset=" + timezoneOffset +"&accountRowId=" + accountId
				+ "&srHistoryType=ALL_REQUESTS&assetRowId="+assetRowId1;/*+assetRowId1;*/

				initialiseGrid("ALL_REQUESTS");
				
			break;
			case "SUPPLIES_REQUESTS":
			
				changeRequestUrl = "<portlet:resourceURL id='retrieveHistoryListByAssetId'></portlet:resourceURL>&timezoneOffset=" + timezoneOffset +"&accountRowId=" + accountId
				+ "&srHistoryType=SUPPLY_REQUESTS&assetRowId="+assetRowId1;

				initialiseGrid("SUPPLY_REQUESTS");
				
			break;
			case "CHANGE_REQUESTS":
				
				changeRequestUrl = "<portlet:resourceURL id='retrieveHistoryListByAssetId'></portlet:resourceURL>&timezoneOffset=" + timezoneOffset +"&accountRowId=" + accountId
				+ "&srHistoryType=CHANGE_REQUESTS&assetRowId="+assetRowId1;

				initialiseGrid("CHANGE_REQUESTS");
				
			break;
			case "SERVICE_REQUESTS":
							
				changeRequestUrl = "<portlet:resourceURL id='retrieveHistoryListByAssetId'></portlet:resourceURL>&timezoneOffset=" + timezoneOffset +"&accountRowId=" + accountId
				+ "&srHistoryType=ServiceRequestList&assetRowId="+assetRowId1;

				initialiseGrid("SERVICE_REQUESTS");
				
				
			break;
			
		}
	}; 
	
	function initialiseGrid(nameOfTheGrid){
	
		if(nameOfTheGrid=="ALL_REQUESTS")
		{
			// *coveredService has been added for CI Defect 11270
			
			headers_allRequest="<spring:message code='requestInfo.history.allRequests.fields'/>";
			filter_allRequest="#text_filter,,,#text_filter,#combo_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter";
			width_allRequest="120,120,120,120,120,120,120,120,120,120,120,120,120,120,120,120";
			
			colalign_allRequest="left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left";
			
			coltype_allRequest="ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro";
			colsSorting = "0,des";			//Added by Adraj for 15.3 release. 
			
			colsort_allRequest="str,str,str,str,str,str,str,str,na,str,na,na,na,na,na,na";
			colVisibility_allRequest="true,true,false,false,false,false,false,false,false,false,false,false,false,false,false,false";
			filterValue_allRequest=",,,,,,,,,,,,,,,";
			defaulthiddenCols_allRequest=["5","6","7","8","9","10","11","12","13","14","15"];
			initialiseGridSettingsForAllHistory('gridContainerDiv_all_request_types');
				
		}
		
		if(nameOfTheGrid=="SUPPLY_REQUESTS")
		{
			// *office number , county, district added for MPS Phase 2
			// *coveredService has been added for CI Defect 11270
			
			headers_allRequest="<spring:message code='requestInfo.history.supplyRequestHistory.fields'/>";
			filter_allRequest=",,#text_filter,,#text_filter,#text_filter,#text_filter,#text_filter,#combo_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter";
		
			width_allRequest="80,30,120,120,120,120,120,120,120,120,120,120,120,120,120,120,120,120,120,120,120,120,120,120,120,120,120,120,120,120,120,120";
			colalign_allRequest="left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left";
			
			
			
			coltype_allRequest="ro,sub_row,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro";
			
			
			colsort_allRequest="na,na,str,int,str,str,str,str,str,str,str,str,str,na,str,na,na,str,str,str,str,str,str,na,na,str,na,na,na,str,na,na";
			
			colVisibility_allRequest="true,true,true,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false";
			filterValue_allRequest=",,,,,,,,,,,,,,,,,,,,,,,,,,,,,";
			defaulthiddenCols_allRequest=["0","6","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29"];
			initialiseGridSettingsForAllHistory('gridContainerDiv_supplies_requests');
				
		}
		
		
		if(nameOfTheGrid=="CHANGE_REQUESTS")
		{
			// *coveredService has been added for CI Defect 11270
			
			headers_allRequest="<spring:message code='requestInfo.history.changeRequestHistory.fields'/>";
			
			
			filter_allRequest="#text_filter,,#text_filter,#text_filter,#text_filter,#combo_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter";
			
			width_allRequest="120,120,120,120,120,120,120,120,120,120,120,120,120,120,120,120,120,120,120,120,120,120,120,120,120,120,120,120,120,120,120";
			colalign_allRequest="left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left";
			
			coltype_allRequest="ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro";
			
			
			colsort_allRequest="str,str,str,str,str,str,str,str,na,str,str,na,str,na,na,str,str,str,str,str,str,na,na,str,na,na,na,na,na,na,na";
			
			colVisibility_allRequest="true,true,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false";
			filterValue_allRequest=",,,,,,,,,,,,,,,,,,,,,,,,,,,,,";
			defaulthiddenCols_allRequest=["5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26"];
			
			initialiseGridSettingsForAllHistory('gridContainerDiv_change_requests');
				
		}
		
		
		if(nameOfTheGrid=="SERVICE_REQUESTS")
		{
			
			
			headers_allRequest="<spring:message code='serviceRequest.listHeader.serviceRequests'/>";
			
		
			filter_allRequest=",#text_filter,,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter";
			
		
			width_allRequest="25,120,80,120,140,200,100,100,140,110,110,100,100,100,110,110,110,100,100,100,100,100,100,100,100,100,100";
			
		
			colalign_allRequest="left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left";
			
		
			coltype_allRequest="sub_row,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro";
			
		
			colsort_allRequest="na,str,int,str,str,na,str,str,str,na,na,str,str,str,str,str,str,str,na,na,str,na,na,na,na,na,str";
			
		
			colVisibility_allRequest="true,true,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false";
			
		
			//filterValue_allRequest=",,,,,,,,,,,,,,,,,,,,,";21
			filterValue_allRequest=",,,,,,,,,,,,,,,,,,,,,,,,,";//22
			
			
			defaulthiddenCols_allRequest=["5","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23"];//Its not yet done
			
			initialiseGridSettingsForAllHistory('gridContainerDiv_service_requests');
				
		}
	}
	
	function initialiseGridSettingsForAllHistory(idofGrid)
	{
	   	
	   	dhtmlxGridId=idofGrid;
	   	
		
		cmHistoryListGrid = new dhtmlXGridObject(idofGrid);
		gridObj=cmHistoryListGrid;
		cmHistoryListGrid.setImagePath("<html:imagesPath/>gridImgs/");
		
		cmHistoryListGrid.setHeader(headers_allRequest);
		
		cmHistoryListGrid.attachHeader(filter_allRequest);
		dhx_globalImgPath = "<html:imagesPath/>comboImgs/";
		
		cmHistoryListGrid.setInitWidths(width_allRequest);
		
		cmHistoryListGrid.setColAlign(colalign_allRequest);
		
		cmHistoryListGrid.setColTypes(coltype_allRequest);
				
		cmHistoryListGrid.setColSorting(colsort_allRequest);
		
		cmHistoryListGrid.enableAutoHeight(true);
		cmHistoryListGrid.enableMultiline(true);
		cmHistoryListGrid.setSkin("light");
		
		cmHistoryListGrid.setLockColVisibility(colVisibility_allRequest);
		
		cmHistoryListGrid.enableColumnMove(true);
		
		cmHistoryListGrid.attachEvent("onXLS", function() {
		
			document.getElementById('loadingNotification').style.display = 'block'; 
			document.getElementById('paginationId').style.display = 'none';
		
			resetValue = false;
			if(colsWidth==""){
				resetValue = true;
			}
			refreshGridSettingOnPage(cmHistoryListGrid);
			dBPersistentFlag = false;
			document.getElementById('loadingNotification').style.display = 'block';
			if(!cmHistoryListGrid.loadHiddenColumns(colsHidden) && resetValue || gridToBeReset) {
				
				setDefaultHiddenColumns(defaulthiddenCols_allRequest);
				gridToBeReset = false;
				resetValue = false;
			}
	        if(cmHistoryListGrid.a_direction=='asc'){
	        	cmHistoryListGrid.setSortImgState(true, cmHistoryListGrid.getDefaltSortImagIndex(cmHistoryListGrid.sortIndex,1), 'asc');
	        }else{
	        	cmHistoryListGrid.setSortImgState(true, cmHistoryListGrid.getDefaltSortImagIndex(cmHistoryListGrid.sortIndex,1), 'des');
	        }
			dBPersistentFlag = true;
				
		
        	cmHistoryListGrid.clearSelection(); 
		});
		
		
		
		cmHistoryListGrid.attachEvent("onXLE", function() {
		
			document.getElementById('loadingNotification').style.display = 'none';
        	document.getElementById('paginationId').style.display = 'block';
        	cmHistoryListGrid.clearSelection();
        	setTimeout(function(){
        		rebrandPagination();
        	
        	},100);
			
        
		    
		});
		
		cmHistoryListGrid.init();
		cmHistoryListGrid.prftInit();
		
		
		jQuery('#grid_menu').remove();
		cmHistoryListGrid.enableHeaderMenuForButton('headerMenuButton');
		
		cmHistoryListGrid.enablePaging(true, 20, 10, "pagingArea_all_request_types" , true,"infoArea_all_request_types");
		cmHistoryListGrid.setPagingSkin("bricks");
		cmHistoryListGrid.sortRows = function(col, type, order) {};
		
		cmHistoryListGrid.sortIndex = null;
		cmHistoryListGrid.columnChanged = true;
		
		
		cmHistoryListGrid.loadOrder(colsOrder);
		cmHistoryListGrid.loadPagingSorting(colsSorting,0);
		cmHistoryListGrid.loadSize(colsWidth);
		
		
		<%--/********** Below section is for lov in device mgmt history *********/--%>
		
		if(idofGrid !="gridContainerDiv_service_requests")
		{

			var requestTypeList = [];	
			<c:forEach items="${requestTypeLOVMap}" var="requestType" varStatus = "status" >
			requestTypeList[${status.index}] = ["${requestType.key}","${requestType.value}"];			
			</c:forEach>
			
			if(idofGrid=="gridContainerDiv_all_request_types")
			{				
			cmHistoryListGrid.setCustomizeCombo(requestTypeList,2);
			}
			
			var requestStatusList = [];		
			<c:forEach items="${requestStatusLOVMap}" var="requestStatus" varStatus = "status" >
			requestStatusList[${status.index}] = ["${requestStatus.key}","${requestStatus.value}"];
			</c:forEach>
			
			if(idofGrid=="gridContainerDiv_change_requests")
			{
			cmHistoryListGrid.setCustomizeCombo(requestStatusList,5);				
			}
		
			else if(idofGrid=="gridContainerDiv_supplies_requests")
			{
			cmHistoryListGrid.setCustomizeCombo(requestStatusList,7);
			}
			else
			{
				cmHistoryListGrid.setCustomizeCombo(requestStatusList,4);
			}
		}		
		<%--/**************End of the section ************/--%>
		
		cmHistoryListGrid.enableAutoSavingToDB("${saveCustomizedGridSettingURL}");
		
		cmHistoryListGrid.attachEvent("onFilterStart", function(indexes,values){
		
		cmHistoryListGrid.filterValues = values;
    	customizedGridURL = updateGridURL(customizedGridURL, cmHistoryListGrid.getSortColByOffset(), cmHistoryListGrid.a_direction, cmHistoryListGrid.filterValues);
    	
		//Defect 3808 fixes
    	saveGridQueryParams(idofGrid,
    	    	generateGridQueryParams(cmHistoryListGrid,null,null,null),
    	    	setGridFilerTimer(reloadGrid)
    	    	);
		//End Changes

    	
    	});
		
		cmHistoryListGrid.attachEvent("onMouseOver", function(id,ind){
			if(ind!=0&&ind!=1){
				var style = cmHistoryListGrid.cells(id,ind).cell.style;
			    style.cssText += ";cursor: pointer;";
				return true;
			}
	    });
		
		cmHistoryListGrid.attachEvent("onBeforeSorting", customColumnSort);

		//Changes for defect 3808
		var backHistory="${backHistory}";
		
		if(backHistory!="" && backHistory=="true"){
			jQuery.getJSON("${retrieveGridParamsForCM}&gridId="+idofGrid,function(result){
				if(result.status!='blank'){
					cmHistoryListGrid.setFiltersValue(result.filterValues);
				    cmHistoryListGrid.filterValues = result.filterValues;
					
		    	}
				customizedGridURL = populateURLCriterias();
				cmHistoryListGrid.loadXML(customizedGridURL);
			});
		}else{
			cmHistoryListGrid.filterValues =filterValue_allRequest;
			 customizedGridURL = populateURLCriterias();
			 cmHistoryListGrid.loadXML(customizedGridURL);
			}
		//Ends 3808

		
		 cmHistoryListGrid.attachEvent("onRowSelect",function(rowId,cellIndex){
				if (cellIndex > 1) {
				var cell=cmHistoryListGrid.cellById(rowId,0);
				var row = cmHistoryListGrid.getRowById(rowId);
				if(!row._expanded){
					cell.open();
				}else{
					cell.close();
				}
				}
			});
		 
		 cmHistoryListGrid.attachEvent("onPaging", function(count){
			 refreshGridSettingOnPage(cmHistoryListGrid);
			});
}

function doReset(){
	

	
	resetGridSettingForDMCM(dhtmlxGridId, resetCallback);
};


function resetCallback() {
   clearMessage();
   

   
   if(dhtmlxGridId == "gridContainerDiv_change_requests")
   {   
	   colsOrder = "0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30";
	   colsWidth = "px:120,120,120,120,120,120,120,120,120,120,120,120,120,120,120,120,120,120,120,120,120,120,120,120,120,120,120,120,120,120,120";
	   cmHistoryListGrid.setColumnsVisibility("true,true,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false");
	   colsSorting = "0,asc";
   }
   else if(dhtmlxGridId == "gridContainerDiv_supplies_requests")
   {
	   
	   colsOrder = "0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31";
	   colsWidth = "px:80,30,120,120,120,120,120,120,120,120,120,120,120,120,120,120,120,120,120,120,120,120,120,120,120,120,120,120,120,120,120,120";
	   cmHistoryListGrid.setColumnsVisibility("true,true,true,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false");
	   colsSorting = "0,asc";
	      
   }else if(dhtmlxGridId == "gridContainerDiv_service_requests")
   {
	   
	  
	   colsOrder="0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26";
	   

       colsWidth = "px:25,120,80,120,200,140,100,100,140,110,110,100,100,100,110,110,110,100,100,100,100,100,100,100,100,100,100";
       
       
       cmHistoryListGrid.setColumnsVisibility("false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false");
       colsSorting = "1,asc";
   }
   else {
	   
	   colsOrder = "0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16";
       colsWidth = "px:120,120,120,120,120,120,120,120,120,120,120,120,120,120,120,120,120";
   	   cmHistoryListGrid.setColumnsVisibility("true,true,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false");
   }   
   
   
   
   colsHidden = "";
   cmHistoryListGrid.sortIndex = 1;
   dBPersistentFlag = false;
   gridToBeReset = true;
   cmHistoryListGrid.loadOrder(colsOrder);
   cmHistoryListGrid.loadPagingSorting(colsSorting,0);
   cmHistoryListGrid.loadSize(colsWidth);
   
   
   
   customizedGridURL = updateGridURL(customizedGridURL, 0, 'asc', cmHistoryListGrid.filterValues);
   cmHistoryListGrid.clearAndLoad(customizedGridURL);
};

function onSRNmbrClick(serviceRequestNumber, requestType){
	 callOmnitureAction('<%=LexmarkSPOmnitureConstants.DEVICEFINDERDETAIL%>', '<%=LexmarkSPOmnitureConstants.DEVICEFINDERDETAILSERAILNUMBERLINK%>'); 
	var srDetailsLink; 
	
	if(requestType=="Fleet Management" || requestType=="Consumables Management"){		
	
		var currentURL = window.location.href;
		if(currentURL.indexOf('/grid-view') != -1){
			srDetailsLink="service-requests?serviceRequestNumber="+ serviceRequestNumber + "&timeZoneOffset=" + timezoneOffset 
			+"&requestType="+requestType +"&sourcePage=DM" + "&assetRowId="+assetRowId1+"&isDeviceBookmarked="+${deviceDetailForm.device.userFavoriteFlag}+"&lclick="+linkClicked+"&dFFlag=true"+"&GVFlag=true";
		
		}
		else{
		srDetailsLink="service-requests?serviceRequestNumber="+ serviceRequestNumber + "&timeZoneOffset=" + timezoneOffset 
		+"&requestType="+requestType +"&sourcePage=DM" + "&assetRowId="+assetRowId1+"&isDeviceBookmarked="+${deviceDetailForm.device.userFavoriteFlag}+"&lclick="+linkClicked+"&dFFlag=true";
		}
		}
	else
	{
	srDetailsLink='<portlet:renderURL windowState="<%= LiferayWindowState.NORMAL.toString() %>"><portlet:param name='action' value='showServiceRequestDrillDownPage' /></portlet:renderURL>'+
	'&serviceRequestNumber=' + serviceRequestNumber+"&sourcePage=DM"+"&assetRowId="+assetRowId1+"&isDeviceBookmarked="+${deviceDetailForm.device.userFavoriteFlag}+"&lclick="+linkClicked+"&dFFlag=true";
	}
	showOverlay(); 
	window.location.href = srDetailsLink;
	
};

	populateURLCriterias = function(){
	  
	var url = changeRequestUrl;
	
	if(cmHistoryListGrid.filterValues != null && cmHistoryListGrid.filterValues != "") {
		url = url + "&filterCriterias=" + cmHistoryListGrid.filterValues;
	}
   if(cmHistoryListGrid.a_direction != null && cmHistoryListGrid.a_direction != "") {
       url = url + "&direction=" + cmHistoryListGrid.a_direction;
   }
   url = url + "&orderBy=" + cmHistoryListGrid.getSortColByOffset();
  
  
   return url;
	};
	
	
	customColumnSort=function(ind){
		 callOmnitureAction('<%=LexmarkSPOmnitureConstants.DEVICEFINDERDETAIL%>', '<%=LexmarkSPOmnitureConstants.DEVICEFINDERDETAILREQUESTHISTORYSORT%>');
	
		
		var a_state = cmHistoryListGrid.getSortingState();
		if(a_state[0] == (ind)){
			cmHistoryListGrid.a_direction = ((a_state[1] == "asc") ? "des":"asc" );
		}else {
			cmHistoryListGrid.a_direction = "asc" ;
			cmHistoryListGrid.columnChanged = true;
		}
		cmHistoryListGrid.sortIndex = ind;
		customizedGridURL = updateGridURL(customizedGridURL, cmHistoryListGrid.getSortColByOffset(), cmHistoryListGrid.a_direction, cmHistoryListGrid.filterValues);
		reloadGrid();
		
		return true;
	};

	


	 setDefaultHiddenColumns = function(defaulthiddenCols_allRequest){
		  var i=0;
		 for(i=0;i<defaulthiddenCols_allRequest.length;i++)
			 {
			 
			 
			 	cmHistoryListGrid.setColumnHidden(defaulthiddenCols_allRequest[i],true);
			 }
	 };

    
	
	function reloadGrid() {
		clearMessage();
		refreshGridSettingOnPage(cmHistoryListGrid);
		cmHistoryListGrid.clearAndLoad(customizedGridURL);
	};
	

	
	jQuery(document).ready(function(){

		var backHistory="${backHistory}";
		var linkclick="${lclick}";
		if(backHistory!="" && backHistory=="true"){
			
			jQuery("#tabs .ui-tabs-nav li").removeClass("ui-tabs-selected");
			jQuery("#tabs .ui-tabs-nav li").removeClass("ui-state-active");
			jQuery('#tabLi2').addClass("ui-tabs-selected");
			jQuery('#tabLi2').addClass("ui-state-active");

			jQuery("#tabs .tabContent .ui-tabs-panel").addClass("ui-tabs-hiden");
			jQuery("#tabs .tabContent .ui-tabs-panel[0]").removeClass("ui-tabs-hiden");
			var Objid = jQuery('#tabLi2').children().attr("href");
			jQuery("#tabs .tabContent "+Objid).removeClass("ui-tabs-hiden");

			if(linkclick=="1")
				jQuery('#li_all_request_types').click();
			else if(linkclick=="2")
				jQuery('#li_supplies_requests').click();
			else if(linkclick=="3")
				jQuery('#li_change_requests').click();
			else if(linkclick=="4")
				jQuery('#li_service_requests').click();
			else
				jQuery('#li_all_request_types').click();
				
			}
		
		jQuery("#tabs .ui-tabs-nav li").hover(function(){
			jQuery(this).addClass("ui-state-hover");
		},function() {
			jQuery(this).removeClass("ui-state-hover");
		});

		jQuery("#tabs .ui-tabs-nav li").focus(function(){
			jQuery(this).addClass("ui-state-focus");
		},function() {
			jQuery(this).removeClass("ui-state-focus");
		});

		jQuery("#tabs .ui-tabs-nav li").click(function(){
			jQuery("#tabs .ui-tabs-nav li").removeClass("ui-tabs-selected");
			jQuery("#tabs .ui-tabs-nav li").removeClass("ui-state-active");
			jQuery(this).addClass("ui-tabs-selected");
			jQuery(this).addClass("ui-state-active");

			jQuery("#tabs .tabContent .ui-tabs-panel").addClass("ui-tabs-hiden");
			jQuery("#tabs .tabContent .ui-tabs-panel[0]").removeClass("ui-tabs-hiden");
			var Objid = jQuery(this).children().attr("href");
			jQuery("#tabs .tabContent "+Objid).removeClass("ui-tabs-hiden");
			
		});
	
	});
	image_error= function () {
		
		  document.getElementById("MyPicture").src = '<html:imagesPath/>dummy_noimg.jpg'; 
		  
		};
</script>

<c:set var="hMapForAccess" value="${sessionScope.userAccessMapForSr}"></c:set>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/in-styles.css?version=<html:fileCacheVersion/>" />

<div id="content-wrapper-inner">
    <div class="journal-content-article">
      <h1><spring:message code="deviceFinder.title.deviceFinder"/></h1>
      <h2 class="step"><spring:message code="deviceDetail.label.deviceDetail" /></h2>
     </div>
    <div class="main-wrap">
      <div class="content"> 
        <!-- MAIN CONTENT BEGIN -->
        <%-- <spring:message code="deviceFinder.listHeader.model"></spring:message> --%>
        <h3 class="pageTitle">${deviceDetailForm.device.descriptionLocalLang}</h3>
        <div class="utilities printPage1 floatR">
          <ul>
            <li class="first"><a href="javascript:print();"><img src="/LexmarkServicesPortal/images/transparent.png" class="ui_icon_sprite print-icon"
			alt="<spring:message code='requestInfo.link.printThisPage'/>" title="<spring:message code='requestInfo.link.printThisPage'/>"></a>
			</li>
          </ul>
        </div>
        <div class="portletBlock" id="productModelPanel">
          <div class="columnsOne">
            <div class="columnInner infoBox rounded shadow">
              <table width="100%" border="0" cellspacing="0" cellpadding="0" class="assetDetail">
                <tr>
                  <td class="pModel"><ul>
                      <li class="center"><img src="${deviceDetailForm.device.productImageURL}" id="MyPicture" onError="image_error();" width="100" height="100">
                      </li>
                    </ul></td>
                  <td class="pad_010" id="modelPrint">
                  
                  <ul class="form">
                      <li>
                        <label><spring:message code="deviceDetail.label.serialNumber"/></label>
						<span>${deviceDetailForm.device.serialNumber}</span></li>
                      <li>
                        <label><spring:message code="deviceDetail.label.assetTag"/></label>
                        <span>${deviceDetailForm.device.assetTag}</span></li>
                            <!--account name  -->
                      <li>
                        <label><spring:message code="requestInfo.accountInfo.listHeader.accountName"/></label> 
                        <span>${deviceDetailForm.device.account.accountName}</span></li>
                    
                        <c:choose>
	                	<c:when test="${deviceDetailForm.device.userFavoriteFlag}">
	                	<li class="fav">
	                	
							<img id="starIMG_${deviceDetailForm.device.assetId}" class='ui_icon_sprite bookmark-star-icon cursor-pointer' src='<html:imagesPath/>transparent.png' 
							name='favorite.png'	onClick="updateUserFavoriteAsset('${deviceDetailForm.device.assetId}');" 
							 width="15" height="15" />
							<a href="javascript:updateUserFavoriteAsset('${deviceDetailForm.device.assetId}');" 
							id="starTXT_${deviceDetailForm.device.assetId}">
							<spring:message code="orderSupplies.label.unbookmarkThisDevice"/></a>
						
						</li>
	                	</c:when>
	                	<c:otherwise>
	                	<li>
							<img id="starIMG_${deviceDetailForm.device.assetId}" class="ui_icon_sprite removebookmark-icon cursor-pointer" src="<html:imagesPath/>transparent.png" 
							name='favorite.png' onClick="updateUserFavoriteAsset('${deviceDetailForm.device.assetId}');" 
							width="15" height="15" />
							<a href="javascript:updateUserFavoriteAsset('${deviceDetailForm.device.assetId}');" 
							id="starTXT_${deviceDetailForm.device.assetId}">
							<spring:message code="deviceFinder.label.bookmarkThisDevice"/></a>
						</li>
	                	</c:otherwise>
	                	</c:choose> 
                    </ul>
                  </td>
                  <td class="actionLinks separatorV">
                  
                  <ul class="link">
                  
                  <c:if test="${not empty deviceDetailForm.device.hostName || not empty deviceDetailForm.device.ipAddress}"> 
					<li>
					<a id="linkCP" onclick="gotoControlPanel('${deviceDetailForm.device.controlPanelURL}'); 
					hideSelect();"  href="#">
					<spring:message code="deviceDetail.label.controlPanel" /></a>
					</li>
				  </c:if>
				  
				  <li>
				  <a id="linkPC" href="${deviceDetailForm.device.supportUrl}" target="_blank"
				  onclick="callOmnitureAction('<%=LexmarkSPOmnitureConstants.DEVICEFINDERDETAIL%>', '<%=LexmarkSPOmnitureConstants.DEVICEFINDERDETAILSUPPORTSDOWNLOAD%>');">
				  <spring:message code="deviceDetail.label.supportAndDownloads" /></a>
				  </li>
				  
		<c:if test="${(hMapForAccess['CREATE SERVICE REQUEST'] eq true) or 
		(hMapForAccess['CREATE SUPPLIES REQUEST'] eq true)
		or (hMapForAccess['CREATE CHANGE MGMT REQUEST'] eq true)}" >
				  <li>
				  <a id="linkSR" onclick="requestService('${deviceDetailForm.device.assetId}','${deviceDetailForm.createServiceRequestFlag}','${deviceDetailForm.consumableAssetFlag}')" href="#">
				  <spring:message code="deviceDetail.link.createARequest" /></a>
				  </li>
		</c:if>		 
				<%-- <c:if test="${hidePgCnts eq 'false'}"> --%> <!-- Changes for CRM-JFoster201503271546 update page count for employee -->
				<li>
				  <a id="updatePgCntsLnk" href="#" onclick="return openPopUp('${deviceDetailForm.device.assetId}', '${deviceDetailForm.device.serialNumber}', '${deviceDetailForm.device.ipAddress}', '${deviceDetailForm.device.productLine}', '${deviceDetailForm.device.assetTag}');">
				  <spring:message code="deviceDetail.link.updatePageCounts" /></a>
				</li>
				<%-- </c:if> --%>       <!-- Changes for CRM-JFoster201503271546 update page count for employee -->
				  
				 </ul></td>
                </tr>
              </table>
            </div>
          </div>
        </div>
        <%--TABBED SECTION - START --%>  
		<div id="tabs" class="nestedTabs ui-tabs ui-widget ui-widget-content ui-corner-all">
          <ul class="ui-tabs-nav ui-helper-reset ui-helper-clearfix ui-widget-header ui-corner-al">
            <li class="ui-state-default ui-corner-top ui-tabs-selected ui-state-active" id="tabLi1"><a href="#tabs-1"><spring:message code="deviceDetail.label.deviceInformation" /></a></li>
            <li class="ui-state-default ui-corner-top" id="tabLi2" ><a id="tab2anchor" onclick="show_load('li_all_request_types');"	href="#tabs-2"><spring:message code="deviceDetail.label.requestHistory" /></a></li>
          </ul>
          <div class="tabContent">
            <div id="tabs-1" class="ui-tabs-panel ui-widget-content ui-corner-bottom">
              <div class="portletBodyWrap">
                <div class="columnsTwo">
                  <div class="columnInner infoBox rounded shadow">
                    <h4><spring:message code="requestInfo.heading.Identifiers"/></h4>
                    <ul class="form">
                      <li>
                        <label><spring:message code="deviceFinder.listHeader.customerDeviceTag"/></label>
                        <span>${deviceDetailForm.device.deviceTag}</span></li>
                      <li>
                        <label><spring:message code="deviceDetail.label.hostName"/></label>
                        <span>${deviceDetailForm.device.hostName}</span></li>
                        <!-- installDate Added for CI BRD14-02-06-->
                      <li>
                        <label><spring:message code="deviceDetail.label.installDate"/></label>
                        <span>${deviceDetailForm.installDate}</span></li>
                      <li>
                        <label><spring:message code="deviceDetail.label.ipAddress"/></label>
                        <span><a href="javascript:gotoControlPanel('${deviceDetailForm.device.controlPanelURL}')">${deviceDetailForm.device.ipAddress}</a></span></li>
                      <li>
                        <label><spring:message code="deviceDetail.label.devicePhase"/></label>
                        <span>${deviceDetailForm.device.devicePhase}</span>
                        </li>
                        <li>
                        <label><spring:message code="deviceFinder.listHeader.assetCostCenter"/></label>
                        <span>${deviceDetailForm.device.assetCostCenter}</span>
                        </li>
                    </ul>
                  </div>
                </div>
              </div>
			  <!-- Changes for Potential CR-9938 -->
              <!-- Page Counts Start -->
								
									<jsp:include page="/WEB-INF/jsp/orderSuppliesAssetOrder/pageCountsForAsset.jsp" />
									<!-- portletBlock infoBox rounded shadow split -->
								<!-- Page Counts End -->
								<!-- END -->
              <div class="portletBodyWrap min-width-700px">
                <div class="columnsTwo">
                  <div class="columnInner infoBox rounded shadow">
                    <h4><spring:message code="deviceDetail.label.installAddress"/></h4>
                    <ul class="roDisplay">
                      <li>
                      <util:addressOutput value="${deviceDetailForm.device.installAddress}"></util:addressOutput>
                      
					  </li>
                    </ul>
                    <ul class="form">
                      <li><label><spring:message code="requestInfo.addressInfo.label.building"/></label><span> ${deviceDetailForm.device.physicalLocationAddress.physicalLocation1}</span></li>
                      <li><label><spring:message code="requestInfo.addressInfo.label.floor"/></label><span> ${deviceDetailForm.device.physicalLocationAddress.physicalLocation2}</span></li>
                      <li><label><spring:message code="requestInfo.addressInfo.label.office"/></label><span> ${deviceDetailForm.device.physicalLocationAddress.physicalLocation3}</span></li>
                    </ul>
                  </div>
                </div>
                <div class="columnsTwo">
                  <div class="columnInner infoBox rounded shadow">
                    <h4><spring:message code="deviceDetail.header.label.primaryContact"/></h4>
                    <ul class="form">
                      <li>
                      <label><spring:message code="requestInfo.contactInfo.label.firstName" /></label>
                        <span>${deviceDetailForm.device.assetContact.firstName}</span></li>
                      <li>
                      <label><spring:message code="requestInfo.contactInfo.label.middleName" /></label>
                        <span>${deviceDetailForm.device.assetContact.middleName}</span></li>
                      <li>
                      <label><spring:message code="requestInfo.contactInfo.label.lastName" /></label>
                        <span>${deviceDetailForm.device.assetContact.lastName}</span></li>
                      <li>
                      <label><spring:message code="requestInfo.contactInfo.label.workPhone" /></label>
                        <span> ${deviceDetailForm.device.assetContact.workPhone}</span></li>
                      <li>
                        <label><spring:message code="requestInfo.contactInfo.label.alternatePhone" /></label>
                        <span>${deviceDetailForm.device.assetContact.alternatePhone}</span></li>
                      <li>
                        <label><spring:message code="requestInfo.contactInfo.label.emailAddress" /></label>
                        <span class="height-auto">${deviceDetailForm.device.assetContact.emailAddress}</span></li>
                    </ul>
                  </div>
                </div>
              </div>
            </div>
            <div id="tabs-2" class="ui-tabs-panel ui-widget-content ui-corner-bottom ui-tabs-hiden">
              <div class="columnInner">
                <div class="left-nav">
                  <div class="left-nav-inner"> 
                    <!-- LEFT COLUMN CONTENT GOES HERE -->
                    <div class="left-nav-header">
                      <h3><spring:message code="deviceDetail.header.label.requestType"/></h3>
                    </div>
                    <ul class="filters">
                      <li id="li_all_request_types" class="selected li-style1" onclick="javascript:show_load(this.id);" >
                      <a class="anchor-style5">
                      <spring:message code="deviceDetail.label.allRequestTypes"/></a></li>
                      <li id="li_supplies_requests" onclick="javascript:show_load(this.id);" class="li-style1">
                      <a class="anchor-style5">
                      <spring:message code="deviceDetail.label.suppliesRequests"/></a></li>
                      <%--Commented as of now MPS 2.1 will uncomment after jan release 
                      <li id="li_change_requests" onclick="javascript:show_load(this.id);" class="cursor-pointer">
                      <a>
                      <spring:message code="deviceDetail.label.changeRequests"/></a></li>
                      --%>
                      <li id="li_service_requests" onclick="javascript:show_load(this.id);" class="li-style1">
                      <a class="anchor-style5">
                      <spring:message code="deviceDetail.label.serviceRequests"/></a></li>
                    </ul>
                  </div>
                  <div class="left-nav-footer"> 
                    <%-- NO CONTENT HERE PLS --%> 
					</div>
                </div>
                <div id="all_request_types">
                  <div class="right-column">
                    <span id="history_heading"><h3 class="pageTitle"><spring:message code="deviceDetail.label.allRequestTypes"/></h3></span>
                    <div class="grid-controls">
                    <!--added for BRD 14-02-01-START--->
                    <div class="export">
                   		<ul>
                    		<li class="first1"><a href="#"><img src="/LexmarkServicesPortal/images/transparent.png" class="ui_icon_sprite spreadsheet-icon" onclick="return  download('csv');" alt="Export to Excel" title="<spring:message code="requestInfo.tooltip.exporttoexcel"/>"/></a></li>
              				<li><img src="/LexmarkServicesPortal/images/transparent.png" class="ui_icon_sprite pdf-icon cursor-pointer" onClick="return download('pdf')"; title="<spring:message code='requestInfo.tooltip.exporttopdf'/>"; /></li>
              				<li><a href="#"><img src="/LexmarkServicesPortal/images/transparent.png" class="ui_icon_sprite print-icon" onclick="return print_list();" alt="Print" title="<spring:message code="requestInfo.tooltip.print"/>"/></a></li>
                    	</ul>
                    </div>
                    <!--added for BRD 14-02-01-ENDS----> 
                      <!-- Expand-min Start -->
                      <div class="expand-min">
                        <ul>
                          <li class="first"><img src="<html:imagesPath/>transparent.png" class="ui_icon_sprite customize-icon cursor-pointer" alt="Customize Grid" title="Customize Grid" />
                          <a href="#grid" id='headerMenuButton'><spring:message code="requestInfo.link.customizeColumns"/></a></li>
                          <li><img src="<html:imagesPath/>transparent.png" class="ui_icon_sprite reset-icon" alt="Reset Grid" title="Reset Grid" />
                          <a href="javascript:doReset();"><spring:message code="requestInfo.link.reset"/></a></li>
                        </ul>
                      </div>
                      <%-- NO CONTENT HERE PLS --%> 
                    </div>
                    <div class="portlet-wrap rounded">
                      <div class="portlet-wrap-inner">
                       
                        <div id="gridContainerDiv_all_request_types"  class="gridbox gridbox_light" style="display: none;"></div>
                        <div id="gridContainerDiv_change_requests" class="gridbox gridbox_light" style="display: none;"></div>
                        <div id="gridContainerDiv_supplies_requests" class="gridbox gridbox_light" style="display: none;"></div>
                        <div id="gridContainerDiv_service_requests" class="gridbox gridbox_light" style="display: none;"></div>
                        <div id="loadingNotification" class='gridLoading' style="display: none;" >
                        	<!-- spring:message code='loadingNotification' />&nbsp;&nbsp; -->
							<img src="<html:imagesPath/>gridloading.gif" /><br>
			  			</div>
                      
				  		<div class="pagination" id="paginationId"><span id="pagingArea_all_request_types"></span>
	                    <span id="infoArea_all_request_types"></span> 
	                    </div>
                      </div>
                    </div>
                    
                    
                  </div>
                </div>
               
              </div>
         </div>
        
        <%--TABBED SECTION - END  --%>
        <div class="buttonContainer">
          <button class="button" type="button" onclick="goToDeviceFinderPage();"><spring:message code="button.back"/></button>
        </div>   
          </div>
        </div>
      </div>
      	
      <!-- MAIN CONTENT END --> 
    </div>
    <div id="dialogControlPanel" style="display:none;"></div>
  </div>

<div id="dialog_meterReads_inInfoPg" style="display: none;">
<jsp:include page="/WEB-INF/jsp/common/pageCountPopUp.jsp"/>
</div>
<div id="dialog_createNew" style="display:none;">
<div id="totalContent">

<div id="createNewRequestPageData"></div>
<jsp:include page="/WEB-INF/jsp/common/accountSelection.jsp"/> 
</div>
</div>



<script type="text/javascript">
//Added for CI BRD 14-02-01 Start
function download(type){
	
	var requestType=dhtmlxGridId;
	if(cmHistoryListGrid.getRowId(0)==null){
		alert("<spring:message code='serviceRequest.download.noDataFound'/>");
		return false;
	}
	//callOmnitureAction('<%=LexmarkSPOmnitureConstants.DEVICEFINDER%>', '<%=LexmarkSPOmnitureConstants.DEVICEFINDERLISTDOWNLOAD%>');
	
	var temp = "${downloadRequestListURL}&downloadType=" + type;
	window.location="${downloadRequestListURL}&downloadType=" + type+"&pageType="+requestType;
}

	 function goToDeviceFinderPage() {
		 callOmnitureAction('<%=LexmarkSPOmnitureConstants.DEVICEFINDERDETAIL%>', '<%=LexmarkSPOmnitureConstants.DEVICEFINDERDETAILBACKBUTTON%>'); 
		 showOverlay();
		window.location.href="<portlet:renderURL></portlet:renderURL>&backToList=true";
	};
	
	
	function printList() {
		
		url ="<portlet:renderURL windowState='<%= LiferayWindowState.POP_UP.toString() %>'>"+
		"<portlet:param name='action' value='printDeviceDetailList' />"+
		"</portlet:renderURL>";
	
		url=url+"&pageNumber="+gridObj.currentPage;
		
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
		    ',status=no,toolbar=yes,menubar=yes,location=no,resizable=yes,scrollbars=yes,titlebar=no');
};
function print() {
	callOmnitureAction('<%=LexmarkSPOmnitureConstants.DEVICEFINDERDETAIL%>', '<%=LexmarkSPOmnitureConstants.DEVICEFINDERDETAILPRINT%>'); 
	if(gridObj){
		printList();
		}
	else{
	url ="<portlet:renderURL windowState='<%= LiferayWindowState.EXCLUSIVE.toString() %>'>"+
	"<portlet:param name='action' value='printDeviceDetailList' />"+
	"</portlet:renderURL>";

var iWidth=1000;
var iHeight=600;
var iTop = (window.screen.availHeight-30-iHeight)/2;
var iLeft = (window.screen.availWidth-10-iWidth)/2;
window.open(url,
		'DevicePage',
	    'height='+iHeight+
	    ',innerHeight='+
	    iHeight+',width='+
	    iWidth+',innerWidth='+
	    iWidth+',top='+iTop+
	    ',left='+iLeft+
	    ',status=no,toolbar=yes,menubar=yes,location=no,resizable=yes,scrollbars=yes,titlebar=no');
	}};	
	
	var portlet_name="<%=LexmarkSPOmnitureConstants.DEVICEFINDERDETAIL%>";
	var action_favoriteAsset="<%=LexmarkSPOmnitureConstants.DEVICEFINDERDETAILBOOKMARKUNBOOKMARK%>";
</script>
<script type="text/javascript">
// Added for CI 14-02-01 STARTS
function print_reqList() {
	
	url ="<portlet:renderURL windowState='<%= LiferayWindowState.POP_UP.toString() %>'>"+
	"<portlet:param name='action' value='printDeviceDetailReqList' />"+
	"</portlet:renderURL>";

	url=url+"&pageNumber="+gridObj.currentPage;
	
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
	    ',status=no,toolbar=yes,menubar=yes,location=no,resizable=yes,scrollbars=yes,titlebar=no');
};

function print_list() {
	if(cmHistoryListGrid.getRowId(0)==null){
		alert("<spring:message code='serviceRequest.download.noDataFound'/>");
		return false;
	}
	callOmnitureAction('<%=LexmarkSPOmnitureConstants.DEVICEFINDERDETAIL%>', '<%=LexmarkSPOmnitureConstants.DEVICEFINDERDETAILPRINT%>'); 
	if(gridObj){
		print_reqList();
		}
	else{
	url ="<portlet:renderURL windowState='<%= LiferayWindowState.POP_UP.toString() %>'>"+
	"<portlet:param name='action' value='printDeviceDetailReqList' />"+
	"</portlet:renderURL>";

var iWidth=1000;
var iHeight=600;
var iTop = (window.screen.availHeight-30-iHeight)/2;
var iLeft = (window.screen.availWidth-10-iWidth)/2;
window.open(url,
		'DevicePage',
	    'height='+iHeight+
	    ',innerHeight='+
	    iHeight+',width='+
	    iWidth+',innerWidth='+
	    iWidth+',top='+iTop+
	    ',left='+iLeft+
	    ',status=no,toolbar=yes,menubar=yes,location=no,resizable=yes,scrollbars=yes,titlebar=no');
	}
	
	};
// Added for CI 14-02-01 ENDS
</script>
<style>
.tabContent ul.filters li a {
	padding: 5px 5px 5px 15px !important;
}
</style>
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
	position: [-80,20],
	resizable: false,
	width: 400,
	height: 195,
	xy: [-80, 20],
	visible: false,
	destroyOnClose: true
	}
	}
	).plug(
	A.Plugin.IO,
	{
	autoLoad: false
	})



}); */

function gotoControlPanel(url){
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
<script type="text/javascript">
var assetStatus = {};
function updateUserFavoriteAsset(assetId) {
	
	//callOmnitureAction('Favorite Asset', 'Update Favorite Asset');
	callOmnitureAction(portlet_name, action_favoriteAsset);
	
	if(assetStatus[assetId]&&assetStatus[assetId].isLoading){
		return;
	}
	var starImg = window.document.getElementById("starIMG_"+assetId);
	var isFavorite=(jQuery("#starIMG_"+assetId).attr('class').indexOf('bookmark-star-icon')!=-1?true:false);
	
	//var isFavorite = starImg.name == "favorite.png";
	assetStatus[assetId] = {isFavorite:isFavorite,isLoading:true};
	newFlagStatus = isFavorite? "false" : "true";
	starImg.src = "<html:imagesPath/>loading-icon.gif";
	var url = "${updateURL}"+"&favoriteAssetId="+assetId+"&favoriteFlag="+newFlagStatus;
	doAjax(url, updateFavoriteSuccessCallBack, updateFavoriteFailCallBack);
	
	/*var isFavorite = starImg.name == "favorite.png";
	assetStatus[assetId] = {isFavorite:isFavorite,isLoading:true};
	newFlagStatus = isFavorite? "false" : "true";
	starImg.src = "<html:imagesPath/>loading-icon.gif";
	var url = "${updateURL}"+"&favoriteAssetId="+assetId+"&favoriteFlag="+newFlagStatus;
	doAjax(url, updateFavoriteSuccessCallBack, updateFavoriteFailCallBack);*/
};


function updateFavoriteSuccessCallBack(loader) {
	var assetId = loader.data;
	var starImg = window.document.getElementById("starIMG_"+assetId);

	//just change the star image
	if(assetStatus[assetId].isFavorite){
		
		jQuery('#starIMG_'+assetId).removeClass('bookmark-star-icon');
		jQuery('#starIMG_'+assetId).addClass('removebookmark-icon');
		starImg.src = "<html:imagesPath/>transparent.png";
		//starImg.name = "unfavorite.png";
		assetStatus[assetId].isFavorite = false;
		document.getElementById("starTXT_"+assetId).innerHTML = "<spring:message code='deviceFinder.label.bookmarkThisDevice'/>";
	}
	else{
		
		jQuery('#starIMG_'+assetId).removeClass('removebookmark-icon');
		jQuery('#starIMG_'+assetId).addClass('bookmark-star-icon');
		starImg.src = "<html:imagesPath/>transparent.png";
		//starImg.name = "favorite.png";
		assetStatus[assetId].isFavorite = true;
		document.getElementById("starTXT_"+assetId).innerHTML = "<spring:message code='orderSupplies.label.unbookmarkThisDevice'/>";
	}
	assetStatus[assetId].isLoading = false;
	globalAssetId = assetId;	
	globalIsFavorite = assetStatus[assetId].isFavorite;
	return true;
};

function updateFavoriteFailCallBack(loader) {
    var assetId = loader.data;
    var starImg = window.document.getElementById("starIMG_"+assetId);
    
    //just change the star image
    if(!assetStatus[assetId].isFavorite){
    	starImg.src = "<html:imagesPath/>transparent.png";
        document.getElementById("starTXT_"+assetId).innerHTML = "<spring:message code='deviceFinder.label.bookmarkThisDevice'/>";
    }
    else{
        starImg.src = "<html:imagesPath/>transparent.png";
        document.getElementById("starTXT_"+assetId).innerHTML = "<spring:message code='orderSupplies.label.unbookmarkThisDevice'/>";
    }
    assetStatus[assetId].isLoading = false;
    return true;
}
</script>