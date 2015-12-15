<%@ include file="/WEB-INF/jsp/include.jsp"%>
<jsp:include page="/WEB-INF/jsp/includeGrid.jsp"/>
<%@ page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>
<script type="text/javascript"><%@ include file="../../../js/portletRedirection.js"%></script>
<script type="text/javascript"><%@ include file="../../../js/jQuery/jquery.qtip-1.0.0-rc3.min.js"%></script>
<script type="text/javascript"><%@ include file="../../../js/mps.js"%></script>
<script type="text/javascript" src='<html:rootPath/>js/jquery.progressbar.js'></script>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/mps.css"/>
<!--[if IE 7]>
	<style type="text/css"><%@ include file="/WEB-INF/css/mps_ie7.css" %></style>
<![endif]-->
<style>
.ie7 .gridbox {
	width: 1232px !important;	
	
	
}
</style>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/in-styles.css?version=<html:fileCacheVersion/>" />
<div class="portlet-section-header"></div>
<div class="portlet-section-body">
<div class="main-wrap">
	<!--  commented the below div for defect no.#1679 -->
	<!-- <div class="content"> -->
	<div>
			<div class="portlet-wrap">
				<div class="portlet-header rounded shadow" >
		
				<table class="displayGrid div-style94" >
              <thead>
                <tr><th><spring:message code="serviceRequest.title.serviceRequest"/></th>
                </tr></thead>
              
            </table>
				</div><!-- portlet-header -->
				<div class="portlet-wrap-inner">
					<div class="grid-controls">
						<div class="utilities">
							<ul>
								<li class="first"><a id="newest" name="newest" href="#allRequestHistory" onclick="onNewestClick(this)" class="font-weight-bold"><spring:message code="serviceRequest.label.newestRequests"/></a></li>
								<li><a id="oldest" name="oldest" href="#allRequestHistory" onclick="onOldestClick(this)" ><spring:message code="serviceRequest.label.oldestRequests"/></a></li>
								<li><a id="closed" name="closed" href="#allRequestHistory" onclick="onClosedClick(this)" ><spring:message code="serviceRequest.label.closedRequests"/></a></li>
							</ul>
						</div><!-- utilities -->
						
						<div class="expand-min">
							<ul>
				      		<li class="first">
				      		<img src="<html:imagesPath/>transparent.png" class="ui_icon_sprite customize-icon cursor-pointer" id='headerImageButton'/>
							<a href="#allRequestHistory" id='headerMenuButton'>
							<spring:message code="serviceRequest.label.customize"/></a>
							</li>
							<li>
							<img src="<html:imagesPath/>transparent.png" onclick="javascript:doReset();" class="ui_icon_sprite reset-icon cursor-pointer"
							alt="<spring:message code="serviceRequest.label.reset"/>" title="<spring:message code="serviceRequest.label.reset"/>" />
							<a href="javascript:doReset();" id="resetGridSetting">
							<spring:message code="serviceRequest.label.reset"/></a>
							</li>
				   			</ul>
						</div>
			
						<!-- Grid Setting -->  
						<div class="clear"></div><!-- clear -->
					</div><!-- grid-controls -->	
					<div id="allRequestHistory" class="gridbox" style=""></div>
					<div class="gridLoading" id="loadingNotification" >
        				<!--spring:message code='loadingNotification' />&nbsp;&nbsp;--><img src="<html:imagesPath/>gridloading.gif"/>
    				</div><!-- mygrid_container -->
    				<br>
    				<div class="utilities">
						<ul>
							<li class="first"><a id="viewAll" name="viewAll" href="#" onclick="onViewAllClick()" class="color-black"><spring:message code="serviceRequest.label.viewAll"/></a></li>
						</ul>
					</div><!-- utilities -->
				</div><!-- portlet-wrap-inner -->	
				<div class="portlet-footer">
					<div class="portlet-footer-inner">
					</div><!-- portlet-footer-inner -->
				</div><!-- portlet-footer -->
			</div><!-- portlet-wrap -->
		</div><!-- content -->
	</div><!-- main-wrap -->
	<div id="dialogChangeDetails" style="display: none;"></div>
	<div id="dialogSupplyDetails" style="display: none;"></div>
	<div id="dialogServiceDetails" style="display: none;"></div>
</div>
<script type="text/javascript">
<%-- var popUpWindow;


function popupFunction(){
AUI().use('aui-base',
'aui-io-plugin-deprecated',
'liferay-util-window',
function(A) {

	popUpWindow=Liferay.Util.Window.getWindow(
	{
	dialog: {
	centered: true,
	constrain2view: true,
	//cssClass: 'yourCSSclassName',
	modal: true,
	position: [-20,-100],
	resizable: false,
	width: 650,
	height: 850,
	xy: [-20, -100],
	destroyOnClose: true,
	visible: false
	}
	}
	).plug(
	A.Plugin.IO,
	{
	autoLoad: false
	})



});
}
function showPopup(serviceRequestNumber){
	popUpWindow.show();
	jQuery(".aui button.close, .aui button.btn.close").hide();
	popUpWindow.titleNode.html("<spring:message code='serviceRequest.title.serviceRequest'/>");
	popUpWindow.io.set('uri',"<portlet:renderURL windowState='<%= LiferayWindowState.POP_UP.toString() %>'><portlet:param name='action' value='serviceRequestDrillDownLightBox' /></portlet:renderURL>&serviceRequestNumber="+ serviceRequestNumber+"&lightBox=lightBox");
	popUpWindow.io.start();
	
	} --%>

function showPopup(serviceRequestNumber){
	var url="<portlet:renderURL windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>"><portlet:param name='action' value='serviceRequestDrillDownLightBox'/></portlet:renderURL>&serviceRequestNumber="+ serviceRequestNumber+"&lightBox=lightBox";
		showOverlay();	
			jQuery('#dialogServiceDetails').load(url,function(){
				dialog=jQuery('#contentDetailsService').dialog({
				autoOpen: false,
				title: '<spring:message code="serviceRequest.title.serviceRequest"/>',
				modal: true,
				draggable: false,
				resizable: false,
				position: 'center',
				height: 'auto',
				width: 940,
				open: function(){	
					jQuery('#contentDetailsService').show();
				},
				close: function(event,ui){
					hideOverlay();
					dialog.dialog('destroy');					 
					dialog=null;
					jQuery('#contentDetailsService').remove();
					},
				error: function (jqXHR, textStatus, errorThrown) {
					hideOverlay();
					dialog.dialog('destroy');					 
					dialog=null;
					jQuery('#contentDetailsService').remove();
					}
				});
			dialog.dialog('open');
			});						

}
	
	var timezoneOffsetServiceRequest="";
	var browser_ver = jQuery.browser.version.major;
	var dialog;
	if(jQuery.browser.msie && browser_ver == "6" ){
		document.getElementById("allRequestHistory").style.width="100%";
	}
    var isFirstGridLoad = true;
    var viewType = 'NEWEST';
    var url = "<portlet:resourceURL></portlet:resourceURL>";
    jQuery(document).ready(function(){
        //alert('in ready');
    	 var offsetMinuteServiceRequest = new Date().getTimezoneOffset();
    	 timezoneOffsetServiceRequest = (offsetMinuteServiceRequest/60);
        //alert('timezoneOffsetServiceRequest'+timezoneOffsetServiceRequest);
	srPreviewGrid = new dhtmlXGridObject('allRequestHistory');
	srPreviewGrid.setImagePath("<html:imagesPath/>gridImgs/");
//	commented for CI5
	/* srPreviewGrid.setHeader(autoAppendPlaceHolder("<spring:message code='serviceRequest.listHeader.serviceRequestsPreview'/>",16));
	srPreviewGrid.setColAlign("left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left");
	srPreviewGrid.setColTypes("ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro");
	srPreviewGrid.setColSorting("str,str,str,str,str,str,str,str,str,str,str,str,str,str,str,str");
	srPreviewGrid.setInitWidths("140,110,140,140,200,140,80,100,80,80,110,110,110,140,140,140"); */
//	changed for CI5
	//changes
	//srPreviewGrid.setHeader(autoAppendPlaceHolder("<spring:message code='serviceRequest.listHeader.serviceRequestsPreview'/>",17));
	srPreviewGrid.setHeader(autoAppendPlaceHolder("<spring:message code='requestInfo.history.allRequests.fields'/>",16));
	
	
	//srPreviewGrid.setColAlign("left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left");
	srPreviewGrid.setColAlign("left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left");
	//srPreviewGrid.setColTypes("ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro");
	srPreviewGrid.setColTypes("ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro");
	//srPreviewGrid.setColSorting("str,str,str,str,str,str,str,str,str,str,str,str,str,str,str,str,str");
	srPreviewGrid.setColSorting("str,str,str,str,str,str,str,str,str,str,str,str,str,str,str,str");
	
	//srPreviewGrid.setInitWidths("140,110,140,140,200,100,80,140,100,80,80,110,110,110,140,140,140");
	srPreviewGrid.setInitWidths("150,200,150,*,150,120,120,120,120,120,120,120,120,120,120,120");
	//Ends
//	changed for CI5 end
	//srPreviewGrid.enableAutoWidth(true);
	srPreviewGrid.enableAutoHeight(true);
	srPreviewGrid.enableMultiline(true);
	//srPreviewGrid.setSizes();
	//-------------lock columns and enable column movement.
//	srPreviewGrid.setLockColVisibility("true,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false");	commented for CI5
	//srPreviewGrid.setLockColVisibility("true,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false");	// changed for CI5
	srPreviewGrid.setLockColVisibility("true,true,false,false,false,false,false,false,false,false,false,false,false,false,false,false");
	srPreviewGrid.enableColumnMove(true);
	//--------------End
	//Set default sorting to 1,des(Date,Newest)
	srPreviewGrid.a_direction = "des";
	srPreviewGrid.s_col = 1;
	
	srPreviewGrid.attachEvent("onXLS", function() {
        document.getElementById('loadingNotification').style.display = 'block';
		dBPersistentFlag = false;
		if(isFirstGridLoad && !srPreviewGrid.loadHiddenColumns(colsHidden) && colsWidth=="" || gridToBeReset) {
		    setDefaultHiddenColumns();
		    gridToBeReset = false;
		}
		dBPersistentFlag = true;
	});
	
	srPreviewGrid.attachEvent("onXLE", function() {
		document.getElementById('loadingNotification').style.display = 'none';
		dBPersistentFlag = true;
		setTimeout(function(){
    		rebrandPagination();
    	
    	},100);
		//srPreviewGrid.setSortImgState(true,srPreviewGrid.s_col,srPreviewGrid.a_direction); //commented for LEX:AIR00061387
	});
	
	srPreviewGrid.attachEvent("onMouseOver", function(id,ind){
		srPreviewGrid.setRowTextStyle(id, "cursor: pointer;");
		return true;
	});
	
	srPreviewGrid.attachEvent("onRowSelect",function(rowId,cellIndex){
		if(cellIndex!=0){
		   onSRNmbrClick(srPreviewGrid.cellByIndex(rowId,0).cell.firstChild.innerHTML);
		}
	});

	srPreviewGrid.attachEvent("onBeforeSorting", customColumnSort);
	
	function customColumnSort(ind) {
 	   // callOmnitureAction('Service Request', 'Service Request Summary Sort');
		var a_state = srPreviewGrid.getSortingState();
		if(a_state[0]==null||a_state[0] == (ind)){
			srPreviewGrid.a_direction = ((srPreviewGrid.a_direction == "asc") ? "des":"asc" );
		}else {
			srPreviewGrid.a_direction = "asc" ;
		}
		srPreviewGrid.s_col = ind;
		//srPreviewGrid.clearAndLoad(url);
		return true;
	}
	srPreviewGrid.init();
	srPreviewGrid.prftInit();
	srPreviewGrid.enableHeaderMenuForButton('headerMenuButton'); 
	srPreviewGrid.enableHeaderMenuForButton('headerImageButton');
	srPreviewGrid.setSkin("light");
	srPreviewGrid.loadOrder(colsOrder);
	srPreviewGrid.loadSize(colsWidth);
	//turned off first grid load to unsure that we always get newest and oldest based on date
	//otherwise it would bring back sorted by a different column and break the logic on this portlet
	//if(isFirstGridLoad)
	//	srPreviewGrid.loadSorting(colsSorting);
    //Default to Newst open request
    //alert('bfore load xml');
    //alert('in service request preview page'+timezoneOffset);
	//srPreviewGrid.loadXML(populateURLCriterias(viewType)+"&searchCriterias=serviceRequestStatus^Submitted" + "${searchCriteriasSeperator}" + "serviceRequestStatus^Inprocess");
	srPreviewGrid.loadXML(populateURLCriterias(viewType)+"&filterCriterias=,,Inprocess");
	//srPreviewGrid.loadXML(populateURLCriterias(viewType)+"&timezoneOffset=" + timezoneOffset);
	//alert('after load xml');
	srPreviewGrid.enableAutoSavingToDB("${saveCustomizedGridSettingURL}");
    //------------- End
    });
	function populateURLCriterias(type){
    	var url = "<portlet:resourceURL></portlet:resourceURL>&orderBy=1&timezoneOffset="+timezoneOffsetServiceRequest;
        var direction;
        if (type == 'NEWEST') {
        	direction = 'des';
        } else if (type == 'OLDEST') {
            direction = 'asc';
        } else if (type == 'CLOSED') {
            direction = 'des';
        }
        url = url + "&direction=" + direction;
        return url;
    }
	//select newest or oldest 10 item
	function onNewestClick()
	{	
 	    //callOmnitureAction('Service Request', 'Service Request Summary View Newest Requests');
        viewType = 'NEWEST';
 	    loadNewestSR();
	};

	function loadNewestSR() {
        srPreviewGrid.a_direction = "des";
        srPreviewGrid.s_col = 1;
        clearHyperLinkStyle();
        //reloadGrid(populateURLCriterias('NEWEST')+"&searchCriterias=serviceRequestStatus^Submitted" + "${searchCriteriasSeperator}" + "serviceRequestStatus^Inprocess");
        reloadGrid(populateURLCriterias('NEWEST')+"&filterCriterias=,,Inprocess");
        document.getElementById('newest').style.fontWeight="bold";
	};
	
	function onOldestClick()
	{
 	  //  callOmnitureAction('Service Request', 'Service Request Summary View Oldest Requests');
        viewType = 'OLDEST';
 	    loadOldestSR();
	};

	function loadOldestSR() {
        srPreviewGrid.a_direction = "asc";
        srPreviewGrid.s_col = 1;
        clearHyperLinkStyle();
        //reloadGrid(populateURLCriterias('OLDEST')+"&searchCriterias=serviceRequestStatus^Submitted" + "${searchCriteriasSeperator}" + "serviceRequestStatus^Inprocess");
        reloadGrid(populateURLCriterias('OLDEST')+"&filterCriterias=,,Inprocess");
        document.getElementById('oldest').style.fontWeight="bold";
	};
	
	function onClosedClick()
	{
 	    //callOmnitureAction('Service Request', 'Service Request Summary View Closed Requests');
        viewType = 'CLOSED';
 	    loadClosedSR();
	};

	function loadClosedSR() {
        srPreviewGrid.a_direction = "des";
        srPreviewGrid.s_col = 1;
        clearHyperLinkStyle();
       // reloadGrid(populateURLCriterias('CLOSED') + "&searchCriterias=serviceRequestStatus^Completed");
        reloadGrid(populateURLCriterias('CLOSED') + "&filterCriterias=,,completed");
        document.getElementById('closed').style.fontWeight="bold";
	}

	function onViewAllClick()
	{
 	   // callOmnitureAction('Service Request', 'Service Request Summary View All');
		window.location.href="<portlet:actionURL><portlet:param name='action' value='viewAllServiceRequests'/></portlet:actionURL>&friendlyURL="+populateURL("service-requests",[],[]);
	};
	
	//RELOAD
	function reloadGrid(url) {
		isFirstGridLoad = false;
	    srPreviewGrid.clearAndLoad(url);
	};

	function onSRNmbrClick(serviceRequestNumber, serviceRqstType,srStatus,srSubArea,srArea,coveredService){
          closeCustomizedWindow();
 	    //callOmnitureAction('Service Request', 'Service Request Summary View Detail');
 	    if(serviceRqstType=='Consumables Management')
    		serviceRqstType='Consumables_Management';
        if(serviceRqstType=='Fleet Management')
        	serviceRqstType='Fleet_Management';
    	<%--serviceRequestNumber = serviceRequestNumber;
    	var url="";
    	
    	
       	url="<portlet:renderURL windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>"><portlet:param name='action' value='showRequestDetailsPopup'/></portlet:renderURL>&requestType="+
		serviceRqstType+"&serviceRequestNumber="+serviceRequestNumber+"&timeZoneOffset="+timezoneOffsetServiceRequest;

    	
    	if(serviceRqstType!='Consumables_Management'&& serviceRqstType!='Fleet_Management'){
    		 url="<portlet:renderURL windowState='<%= LiferayWindowState.POP_UP.toString() %>'><portlet:param name='action' value='serviceRequestDrillDownLightBox' /></portlet:renderURL>&serviceRequestNumber="+ serviceRequestNumber+"&lightBox=lightBox";
    		}
    	//alert(url);
        var iLeft = (window.screen.availWidth-820)/2; --%>
        <%--
        <portlet:renderURL windowState='<%= LiferayWindowState.POP_UP.toString() %>'><portlet:param name='action' value='serviceRequestDrillDown' /></portlet:renderURL>&serviceRequestNumber="+ serviceRequestNumber+"&lightBox=lightBox
         --%>
       <%-- new Liferay.Popup({
             title: "<spring:message code='serviceRequest.title.serviceRequest'/>",
             position: [iLeft,50], 
             modal: true,
             width: 820, 
             height: 'auto',
             xy: [100, 100],
             onClose: function() {showSelect();},
             url: url
             
             });--%>
             var url="<portlet:renderURL windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>"><portlet:param name='action' value='showRequestDetailsPopup'/></portlet:renderURL>&requestType="+
             serviceRqstType+"&serviceRequestNumber="+serviceRequestNumber+"&timeZoneOffset="+timezoneOffsetServiceRequest+"&uniqueTime="+new Date().getTime()+"&srArea="+encodeURIComponent(srArea);

        if(serviceRqstType=='Consumables_Management') {
			showOverlay();	
			//alert('Consumables='+url);
				//var url = "${showRequestDetailsPopupUrl}&requestType="+ServiceRqstType+"&serviceRequestNumber="+ServiceRqstNumber;
				
						jQuery('#dialogSupplyDetails').load(url,function(){
							//alert('after load');
						dialog=jQuery('#contentDetailsSupply').dialog({
							autoOpen: false,
							title: '<spring:message code="Details.changeRequestDetails.heading.supplyReqDeails"/>',
							modal: true,
							draggable: false,
							resizable: false,
							position: 'center',
							height: 'auto',
							width: 940,
							open: function(){	
								//alert('in Consumables');	
								if(srArea=="Upload"){
									changeRqstDetails(serviceRequestNumber,serviceRqstType);
								}else{
									supplyRqstDetails(serviceRequestNumber,serviceRqstType);	
								}
								
							},
							close: function(event,ui){
								hideOverlay();
								dialog.dialog('destroy');					 
								dialog=null;
								jQuery('#contentDetailsSupply').remove();
								},
							error: function (jqXHR, textStatus, errorThrown) {
								hideOverlay();
								dialog.dialog('destroy');					 
								dialog=null;
								jQuery('#contentDetailsSupply').remove();
								}
							});
						dialog.dialog('open');
						});						
			
		} else if(serviceRqstType=='Fleet_Management') {
var titleLine;
		//alert('before ajax');
		//alert('Fleet='+url);
			//var url = "${showRequestDetailsPopupUrl}&requestType="+ServiceRqstType+"&serviceRequestNumber="+ServiceRqstNumber;
		if((srArea=='HW Order' || srArea=='Hardware Shipment-Install') && srSubArea =='BAU'){
			titleLine="<spring:message code='hardwareDetails.label.hardwareReqDetails'/>";
		var url1="<portlet:renderURL windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>"><portlet:param name='action' value='showHardwareDetailsPopup'/></portlet:renderURL>&requestType="+
             serviceRqstType+"&serviceRequestNumber="+serviceRequestNumber+"&timeZoneOffset="+timezoneOffsetServiceRequest+"&srArea="+srArea+"&srSubArea="+srSubArea;
		}else{
			if(srArea=='Install' && srSubArea =='BAU'){
				titleLine="<spring:message code='requestInfo.heading.installReqDetails'/>";
			}else {
				titleLine="<spring:message code='Details.changeRequestDetails.heading.changeReqDeails'/>";
			}			
			var url1="<portlet:renderURL windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>"><portlet:param name='action' value='showRequestDetailsPopup'/></portlet:renderURL>&requestType="+
            serviceRqstType+"&serviceRequestNumber="+serviceRequestNumber+"&timeZoneOffset="+timezoneOffsetServiceRequest+"&srArea="+srArea+"&srSubArea="+srSubArea+"&coveredService="+coveredService;
			}
		url1= encodeURI(url1);
			showOverlay();
					jQuery('#dialogChangeDetails').load(url1,function(){
					dialog=jQuery('#contentDetailsChange').dialog({
						autoOpen: false,
						title:titleLine,
						modal: true,
						draggable: false,
						resizable: false,
						position: 'center',
						height: 'auto',
						width: 940,
						open: function(){							
							changeRqstDetails(serviceRequestNumber,serviceRqstType);
						},
						close: function(event,ui){
							hideOverlay();
							jQuery('#contentDetailsChange').remove();
							//jQuery('#dialogChangeDetails').remove();
							dialog.dialog('destroy');					 
							dialog=null;
							
							},
						error: function (jqXHR, textStatus, errorThrown) {
							hideOverlay();
							dialog.dialog('destroy');					 
							dialog=null;
							jQuery('#contentDetailsChange').remove();
							}
						});
					dialog.dialog('open');
					});
		}

		else {
			//popupFunction();
			showPopup(serviceRequestNumber);
		}
        






        
    }
	
	
	function setDefaultHiddenColumns(){
		srPreviewGrid.setColumnHidden(5,true);
		srPreviewGrid.setColumnHidden(6,true);
		srPreviewGrid.setColumnHidden(7,true);
		srPreviewGrid.setColumnHidden(8,true);
		srPreviewGrid.setColumnHidden(9,true);
		srPreviewGrid.setColumnHidden(10,true);
		srPreviewGrid.setColumnHidden(11,true);
		srPreviewGrid.setColumnHidden(12,true);
		srPreviewGrid.setColumnHidden(13,true);
		srPreviewGrid.setColumnHidden(14,true);
		srPreviewGrid.setColumnHidden(15,true);
		/*srPreviewGrid.setColumnHidden(4,true);
		srPreviewGrid.setColumnHidden(7,true);
		srPreviewGrid.setColumnHidden(8,true);
		srPreviewGrid.setColumnHidden(9,true);
		srPreviewGrid.setColumnHidden(10,true);
		srPreviewGrid.setColumnHidden(11,true);
		srPreviewGrid.setColumnHidden(12,true);
		srPreviewGrid.setColumnHidden(13,true);
		srPreviewGrid.setColumnHidden(14,true);*/
		//srPreviewGrid.setColumnHidden(15,true);
		//srPreviewGrid.setColumnHidden(16,true);		// added for CI5
	}
    function doReset(){
     //   callOmnitureAction('Service Request', 'Service Request Summary Reset Grid');
        resetGridSetting('allRequestHistory', resetCallback); 
    };

    function resetCallback() {
        clearMessage();
//        commented for CI5
        /* colsOrder = "0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15";
        colsWidth = "px:140,110,140,140,200,140,80,100,80,80,110,110,110,140,140,140"; */
//      changed for CI5
		//colsOrder = "0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16";
		//
        colsOrder = "0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15";
        //colsWidth = "px:140,110,140,140,200,100,80,140,100,80,80,110,110,110,140,140,140";
        colsWidth = "px:150,200,150,*,150,120,120,120,120,120,120,120,120,120,120,120";
        //ends
//      changed for CI5 end
        colsHidden = "";
        gridToBeReset = true;
        dBPersistentFlag = false;
//      srPreviewGrid.setColumnsVisibility("false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false");  		// commented for CI5
        //srPreviewGrid.setColumnsVisibility("false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false");	// changed for CI5
        srPreviewGrid.setColumnsVisibility("true,true,false,false,false,false,false,false,false,false,false,false,false,false,false,false");
        srPreviewGrid.loadOrder(colsOrder);
        srPreviewGrid.loadSize(colsWidth);
        if (viewType == 'NEWEST') {
            loadNewestSR();
        } else if (viewType == 'OLDEST') {
        	loadOldestSR();
        } else if (viewType == 'CLOSED') {
        	loadClosedSR();
        }
    };
    
	function clearHyperLinkStyle(){
		document.getElementById("newest").style.fontWeight="";
		document.getElementById('closed').style.fontWeight="";
		document.getElementById('oldest').style.fontWeight="";
	}
    function popupNotificationDecDetail(selectedRowId){
		var date = SRNotificationsGrid.cellByIndex(selectedRowId,0).cell.innerHTML;
	 	var recipient = SRNotificationsGrid.cellByIndex(selectedRowId,1).cell.innerHTML;
		var description = SRNotificationsGrid.cells(selectedRowId,3).cell.innerHTML;
		
		new Liferay.Popup({
	    title: "<spring:message code='serviceRequest.label.notificationsDetail'/>",
 		position: [450,50],
 		modal: true,
 		width: 550, 
 		height: 'auto',
 		xy: [100, 100],
 		onClose: function() {showSelect();},
 		url:"<portlet:renderURL windowState='<%= LiferayWindowState.POP_UP.toString() %>'>"+
     		"<portlet:param name='action' value='showNotificationDescriptionDetail' />"+
     		"</portlet:renderURL>&date="+date+"&recipient="+recipient+"&description="+description
 	});
	}
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
	function closeCustomizedWindow(){
		if(document.getElementById('grid_menu')!=null)
		{
	   	  document.getElementById('grid_menu').style.display = 'none';
		}
	   }
</script>


