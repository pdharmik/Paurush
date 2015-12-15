<%@ include file="/WEB-INF/jsp/include.jsp"%>
<%@ include file="/WEB-INF/jsp/includeGrid.jsp"%>
<script type="text/javascript" src='<html:rootPath/>js/grid/ext/dhtmlxgrid_excell_sub_row.js'></script>
<%@ page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>
<%@page import="com.lexmark.services.util.LexmarkSPOmnitureConstants"%>
<style type="text/css"><%@ include file="/WEB-INF/css/ci6.css" %></style>
<!--[if IE 7]>
	<style type="text/css"><%@ include file="/WEB-INF/css/mps_ie7.css" %></style>
<![endif]-->
<%--
<div class="main-wrap">
	<div class="content">
		 <%@ include file="/WEB-INF/jsp/serviceRequest/serviceRequestsCategories.jsp"%>	
		ServiceRequestListPage
		<div class="right-column"> --%>
		 <div style="margin-left: 10px; margin-right: 10px; margin-top: 10px;"> 
		 <h3 class="pageTitle"><spring:message code='serviceRequest.title.serviceRequest'/></h3>
		 
					<div class="grid-controls">
						<div class="utilities">
							<ul>
								<li class="first"><img src="/LexmarkOPSPortal/images/transparent.png" class="ui_icon_sprite spreadsheet-icon" onClick="return download('csv')"; style="cursor:pointer"; title="<spring:message code='image.title.downloadCSV'/>"; /></li>
								<li><img src="/LexmarkOPSPortal/images/transparent.png" class="ui_icon_sprite pdf-icon" onClick="return download('pdf')"; style="cursor:pointer"; title="<spring:message code='image.title.downloadPDF'/>"; /></li>
								<li><img src="/LexmarkOPSPortal/images/transparent.png" class="ui_icon_sprite print-icon" onClick="return print()"; style="cursor:pointer"; title="<spring:message code='image.title.print'/>"; /></li>
							</ul>
						</div><!-- utilities -->
						<div class="expand-min">
							<ul>
								<li class="first">
									<img class="ui_icon_sprite minimize-icon" src="<html:imagesPath/>/transparent.png" onClick="minimizeAll()"; style="cursor:pointer;" />
	       		 				&nbsp;<a href="javascript:void(0)" onClick="minimizeAll()"; ><spring:message code="deviceFinder.label.minimizeAll"/></a>
								</li>
								<li>
									<img class="ui_icon_sprite expand-icon" src="<html:imagesPath/>/transparent.png" onClick="expandAll()"; style="cursor:pointer;" />
           			 			&nbsp;<a href="javascript:void(0)" onClick="expandAll()"; ><spring:message code="deviceFinder.label.expandAll"/></a>
								</li>
								
																
								<li>
									<a href="#grid" id='headerImageButton'><img class="ui_icon_sprite customize-icon" src="<html:imagesPath/>transparent.png"  style="cursor:pointer"; /></a>
									&nbsp;<a href="#grid" id='headerMenuButton'><spring:message code="serviceRequest.label.customize"/></a>
								</li>
								
								<li>
								<img style="cursor: pointer" onclick="javascript:doReset();" src="<html:imagesPath/>/transparent.png" class="ui_icon_sprite reset-icon"
									alt="<spring:message code="serviceRequest.label.reset"/>" title="<spring:message code="serviceRequest.label.reset"/>" />
									<a href="javascript:doReset();" id="resetGridSetting" ><spring:message code="serviceRequest.label.reset"/></a>
								</li>
							</ul>
						</div><!-- expand-min -->  
						<div style="clear: right"></div><!-- clear -->
					</div><!-- grid-controls -->	
			<div class="portlet-wrap rounded">
				<div class="portlet-wrap-inner">
					<div id="serviceRequestListGrid"  class="gridbox gridbox_light"></div>
	        		<div id="loadingNotification" class='gridLoading'>
	        			<!--spring:message code='loadingNotification' />&nbsp;&nbsp;--><img src="<html:imagesPath/>gridloading.gif"/>
	    			</div>
	    			<div><span id="pagingArea"></span>&nbsp;<span id="infoArea"></span></div><!-- mygrid_container -->
				</div><!-- portlet-wrap-inner -->	
				<div class="portlet-footer">
					<div class="portlet-footer-inner">
					</div><!-- portlet-footer-inner -->
				</div><!-- portlet-footer -->
			</div><!-- portlet-wrap -->
		<%-- </div> --%><!-- right-column -->
		<div class="clear"></div><!-- clear -->
		<%--
	</div><!-- content -->
</div><!-- main-wrap -->
--%>
</div>
<script type="text/javascript">

jQuery('#historyTypeName').html("<spring:message code='requestInfo.requestHistory.heading.serviceRequests'/>");

	var viewType="RecentSRList";
	var searchCriteria="";
	function getURLWithViewType(type) {
	   var url =  serviceResourceURL;
	   if(type=='RecentSRList'){
		   //url = "<portlet:resourceURL><portlet:param name='pageName' value='RecentSRList' /></portlet:resourceURL>";
		   url = url+"&pageName=RecentSRList";
		} else if(type == 'DFM') {
		   // url = "<portlet:resourceURL><portlet:param name='pageName' value='DFM' /></portlet:resourceURL>";
			url = url+"&pageName=DFM";
		} else if(type == 'CSS') {
		    //url = "<portlet:resourceURL><portlet:param name='pageName' value='CSS' /></portlet:resourceURL>";
			 url = url+"&pageName=CSS";
		} else{
		   //url = "<portlet:resourceURL><portlet:param name='pageName' value='MySRList' /></portlet:resourceURL>";
			 url = url+"&pageName=MySRList";
	    }
		return url;
		url = url + '&orderBy=' + cmHistoryListGrid.sortIndex + '&direction=' + cmHistoryListGrid.a_direction;
	};
	
	cmHistoryListGrid = new dhtmlXGridObject('serviceRequestListGrid');
	cmHistoryListGrid.setImagePath("<html:imagesPath/>gridImgs/");
//	commented for CI5
	/* cmHistoryListGrid.setHeader(autoAppendPlaceHolder("<spring:message code='serviceRequest.listHeader.serviceRequests'/>",18));
	cmHistoryListGrid.attachHeader(",#text_filter,,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter");
	cmHistoryListGrid.setColAlign("left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left");
	cmHistoryListGrid.setInitWidths("25,120,80,120,140,200,140,100,110,110,100,100,100,110,110,110,100,100");
	cmHistoryListGrid.setColTypes("sub_row,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro");
	cmHistoryListGrid.setColSorting("na,str,int,str,str,str,str,str,str,str,str,str,str,str,str,str,str,str"); */
//	changed for CI5
	////CI-6 Changes Start
	cmHistoryListGrid.setHeader(autoAppendPlaceHolder("<spring:message code='serviceRequest.listHeader.serviceRequests'/>",23));
	cmHistoryListGrid.attachHeader(",#text_filter,,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter");
	cmHistoryListGrid.setColAlign("left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left");
	cmHistoryListGrid.setInitWidths("25,120,80,120,140,200,100,100,140,110,110,100,100,100,110,110,110,100,100,100,100,100,100");
	cmHistoryListGrid.setColTypes("sub_row,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro");
	cmHistoryListGrid.setColSorting("na,str,int,str,str,str,str,str,str,str,str,str,str,str,str,str,str,str,str,na,str,str,str");
	//CI-6 Changes END PARTHA
	//	changed by for CI5 end
	//cmHistoryListGrid.enableAutoWidth(true);
	cmHistoryListGrid.enableAutoHeight(true);
  	cmHistoryListGrid.enableMultiline(true);
  	cmHistoryListGrid.setSkin("light");
//  cmHistoryListGrid.setLockColVisibility("true,true,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false");  // commented for CI5
	cmHistoryListGrid.setLockColVisibility("true,true,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false");		// changed for CI5
  	cmHistoryListGrid.enableColumnMove(true);
	cmHistoryListGrid.init();
	cmHistoryListGrid.prftInit();
	cmHistoryListGrid.enableHeaderMenuForButton('headerMenuButton');
	cmHistoryListGrid.enableHeaderMenuForButton('headerImageButton');
	//cmHistoryListGrid.loadXML("<portlet:resourceURL></portlet:resourceURL>&orderBy=1&direction=DESCENDING");
	
	cmHistoryListGrid.enablePaging(true, 20, 10, "pagingArea", true);
	cmHistoryListGrid.setPagingSkin("bricks");

	cmHistoryListGrid.sortIndex = null;
	cmHistoryListGrid.columnChanged = true;
	
	cmHistoryListGrid.attachEvent("onXLS", function() {
		isGridLoading=true;
		resetValue = false;
		if(colsWidth==""){
			resetValue = true;
		}
		refreshGridSettingOnPage(cmHistoryListGrid);
		dBPersistentFlag = false;
		document.getElementById('loadingNotification').style.display = 'block';
		if(!cmHistoryListGrid.loadHiddenColumns(colsHidden) && resetValue || gridToBeReset) {
			setDefaultHiddenColumns();
			gridToBeReset = false;
			resetValue = false;
		}
        if(cmHistoryListGrid.a_direction=='asc'){
            cmHistoryListGrid.setSortImgState(true, cmHistoryListGrid.getDefaltSortImagIndex(cmHistoryListGrid.sortIndex,1), 'asc');
        }else{
            cmHistoryListGrid.setSortImgState(true, cmHistoryListGrid.getDefaltSortImagIndex(cmHistoryListGrid.sortIndex,1), 'des');
        }
		dBPersistentFlag = true;
		
	});
	cmHistoryListGrid.attachEvent("onXLE", function() {
		isGridLoading=false;
	    document.getElementById('loadingNotification').style.display = 'none';
	    setTimeout(function(){
    		rebrandPagination();
    	
    	},100);
	});

	cmHistoryListGrid.attachEvent("onMouseOver", function(id,ind){
		if(ind!=0&&ind!=1){
			var style = cmHistoryListGrid.cells(id,ind).cell.style;
		    style.cssText += ";cursor: pointer;";
			return true;
		}
    });
	/********** Added by Sourav to fix defect #2084 *********/
	if(colsSorting.length==0){
		cmHistoryListGrid.a_direction = cmHistoryListGrid.a_direction||"des";
	    colsSorting = "1,des";
	}
	
    cmHistoryListGrid.loadOrder(colsOrder);
    cmHistoryListGrid.loadPagingSorting(colsSorting,1);
    cmHistoryListGrid.loadSize(colsWidth);
//  cmHistoryListGrid.filterValues = ",,,,,,,,,,,,,,,,,";	// commented for CI5  
	
	//CI-6 Changes Start PARTHA
    cmHistoryListGrid.filterValues = ",,,,,,,,,,,,,,,,,,,,,";	// changed for CI5
  	//CI-6 Changes Start PARTHA
  	
  	//changes for JAN Release MPS
  	if(requestTypeSTR=="SERVICE_REQUESTS"){
	  	jQuery.getJSON(retrieveGridParams+"&gridId=serviceRequestListGrid",function(result){
					if(result.status!='blank'){
	
						var filVal=result.filterValues.substring(1,result.filterValues.length);
						cmHistoryListGrid.setFiltersValue(filVal);
						cmHistoryListGrid.filterValues = result.filterValues;
						
						
						
			    	}
			    	if(result.optionChosen!=null && result.optionChosen!=""){
				    	//This sets the radio button selected for this grid
				    	jQuery('#'+result.optionChosen).attr('checked',true);
				    	//setting the values in the hidden textbox
						searchFilterCriteria = result.optionChosen;
						jQuery('#searchFilterCriteria').val(result.optionChosen);
			    	}
			    	else{
				    	//by default showall will be checked.
			    		jQuery('#showAll').attr('checked',true);
			    		jQuery('#searchFilterCriteria').val('showAll');
			    		searchFilterCriteria='showAll';
				    	}
			    	customizedGridURL = populateURLCriterias();
			    					 
					 //alert("B4 lloadxml#### cmHistoryListGrid.filterValues--"+cmHistoryListGrid.filterValues);
					 if(result.startDate!=null && result.endDate!=null && result.startDate!="" && result.endDate!=""){
						//setting the start date and end date to the text box
						jQuery('#from').val(result.startDate);
		    			jQuery('#to').val(result.endDate); 
		    			
						cmHistoryListGrid.loadXML(customizedGridURL+"&startDate="+result.startDate+"&endDate="+result.endDate);
					 }
					 else	
						cmHistoryListGrid.loadXML(customizedGridURL); 
				});
  	}else{
  		customizedGridURL = populateURLCriterias();
  		cmHistoryListGrid.loadXML(customizedGridURL); 

  	  	}
	//End Jan release

	
    
	


	
	cmHistoryListGrid.enableAutoSavingToDB("${saveCustomizedGridSettingURL}");
	cmHistoryListGrid.attachEvent("onBeforeSorting", customColumnSort);
	cmHistoryListGrid.sortRows = function(col, type, order) {};
	cmHistoryListGrid.attachEvent("onRowSelect",function(rowId,cellIndex){
               	closeCustomizedWindow();
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
	// SORTING
	function customColumnSort(ind) {
		callOmnitureAction('Service Request', 'Service Request List Sort');
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
	}
	//FILTER
	cmHistoryListGrid.attachEvent("onFilterStart", function(indexes,values){
    	cmHistoryListGrid.filterValues = ","+values;
    	customizedGridURL = updateGridURL(customizedGridURL, cmHistoryListGrid.getSortColByOffset(), cmHistoryListGrid.a_direction, cmHistoryListGrid.filterValues);
    	//changes for JAN Release MPS
    	saveGridQueryParams('serviceRequestListGrid',
    	    	generateGridQueryParams(cmHistoryListGrid,getArg(customizedGridURL, 'startDate'),getArg(customizedGridURL, 'endDate'),getArg(customizedGridURL, 'searchFilterCriteria')),
    	    	setGridFilerTimer(reloadGrid));
    	//Ends JAN Release
    });
	cmHistoryListGrid.attachEvent("onPaging", function(count){
		refreshGridSettingOnPage(cmHistoryListGrid);
	});
    function setDefaultHiddenColumns(){
    	cmHistoryListGrid.setColumnHidden(5,true);
    	cmHistoryListGrid.setColumnHidden(8,true);
    	cmHistoryListGrid.setColumnHidden(9,true);
    	cmHistoryListGrid.setColumnHidden(10,true);
    	cmHistoryListGrid.setColumnHidden(11,true);
    	cmHistoryListGrid.setColumnHidden(12,true);
    	cmHistoryListGrid.setColumnHidden(13,true);
    	cmHistoryListGrid.setColumnHidden(14,true);
    	cmHistoryListGrid.setColumnHidden(15,true);
    	cmHistoryListGrid.setColumnHidden(16,true);
    	cmHistoryListGrid.setColumnHidden(17,true);
    	cmHistoryListGrid.setColumnHidden(18,true);	// added for CI5
    	cmHistoryListGrid.setColumnHidden(19,true);
    	//CI-6 changes Start PARTHA
    	cmHistoryListGrid.setColumnHidden(20,true);
    	cmHistoryListGrid.setColumnHidden(21,true);
    	cmHistoryListGrid.setColumnHidden(22,true);
    	//CI-6 changes End PARTHA
    }
	//RELOAD
	function reloadGrid() {
		clearMessage();
		refreshGridSettingOnPage(cmHistoryListGrid);
	    cmHistoryListGrid.clearAndLoad(customizedGridURL);
	}
	
	function populateURLCriterias(){
		var url = getURLWithViewType(viewType);
		if(searchCriteria != null && searchCriteria != "") {
			url = url + "&searchCriterias=" + searchCriteria;
		}
		if(cmHistoryListGrid.filterValues != null && cmHistoryListGrid.filterValues != "") {
			url = url + "&filterCriterias=" + cmHistoryListGrid.filterValues;
		}
        if(cmHistoryListGrid.a_direction != null && cmHistoryListGrid.a_direction != "") {
            url = url + "&direction=" + cmHistoryListGrid.a_direction;
        }
        url = url + "&orderBy=" + cmHistoryListGrid.getSortColByOffset();
        return url;
    }	

    /**
	function onSRNmbrClick(serviceRequestNumber){
                	closeCustomizedWindow();
		callOmnitureAction('Service Request', 'Service Request List View Detail');
		window.location.href="<portlet:renderURL windowState='<%= LiferayWindowState.NORMAL.toString() %>'><portlet:param name='action' value='serviceRequestDrillDown' /><portlet:param name='pageType' value='${serviceRequestListForm.breadCrumb}' /></portlet:renderURL>&serviceRequestNumber="+serviceRequestNumber;
	};
	**/

	function onSRNmbrClick(serviceRequestNumber, requestType){
		//alert("serviceRequestNumber-->["+serviceRequestNumber+"] and requestType["+requestType+"]");
		//Changed
		//alert("serviceRequestNumber :: "+ serviceRequestNumber);
		//alert("requestType :: "+ requestType);
		showOverlay();
		var link = "<portlet:renderURL windowState='<%= LiferayWindowState.NORMAL.toString() %>'><portlet:param name='action' value='showServiceRequestDrillDownPage' /></portlet:renderURL>&serviceRequestNumber=" + serviceRequestNumber;
		window.location.href= link;
	};	
	 
	
	function printBrkFixSrs(){  
		//alert("printBrkFixSrs"); 
		callOmnitureAction('Service Request', 'Service Request List Print');
		if(cmHistoryListGrid.getRowId(0)==null){
			alert("<spring:message code='serviceRequest.download.noDataFound'/>");
			return false;
		}
		url ="<portlet:renderURL windowState='<%= LiferayWindowState.POP_UP.toString() %>'>" +
			"<portlet:param name='action' value='printServiceRequests' />" + 
			"</portlet:renderURL>";
		    var iWidth=900;
		    var iHeight=600;
		    var iTop = (window.screen.availHeight-30-iHeight)/2;        
		    var iLeft = (window.screen.availWidth-10-iWidth)/2;           
		    window.open(url,
		    		'ServiceRequestList',
				    'height='+iHeight+
				    ',innerHeight='+
				    iHeight+',width='+
				    iWidth+',top='+iTop+
				    ',left='+iLeft+
				    ',status=no,toolbar=no,menubar=no,location=no,resizable=yes,scrollbars=yes,titlebar=no');
	};

	function download(type){
		callOmnitureAction('Service Request', 'Service Request List Download - ' + type);
		if(cmHistoryListGrid.getRowId(0)==null){
			alert("<spring:message code='serviceRequest.download.noDataFound'/>");
			return false;
		}
		window.location="${downloadServiceRequestsURL}&downloadType=" + type;
	};

 
	function minimizeAll() {
		callOmnitureAction('Service Request', 'Service Request List Minimize All');
		cmHistoryListGrid.forEachRow(function(id){
	   		var cell=cmHistoryListGrid.cellById(id,0);
	   		cell.close();
	   		subRowOpenFlag=false;
	   	});
	};
	
	function expandAll() {
		callOmnitureAction('Service Request', 'Service Request List Expand All');
		cmHistoryListGrid.forEachRow(function(id){
	   		var cell=cmHistoryListGrid.cellById(id,0);
	   		cell.open();
	   		subRowOpenFlag=true;
	   	});
	};
	
	function doReset(){
		callOmnitureAction('Service Request', 'Service Request List Reset');
		resetGridSetting('serviceRequestListGrid', resetCallback);  
	};

    function resetCallback() {
        clearMessage();
//      commented for CI5
        /* colsOrder = "0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17";
        colsWidth = "px:25,120,80,120,200,140,140,100,110,110,100,100,100,110,110,110,100,100"; */
//      changed for CI5
        //colsOrder = "0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19";
        //colsWidth = "px:25,120,80,120,200,140,100,100,140,110,110,100,100,100,110,110,110,100,100,100";
       //CI-6 Changes Start PARTHA
        colsOrder = "0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22";
        colsWidth = "px:25,120,80,120,200,140,100,100,140,110,110,100,100,100,110,110,110,100,100,100,100,100,100,100,100";
      	//CI-6 Changes End PARTHA
//      changed for CI5 end
        colsSorting = "1,des";//Order changed to des by Sourav for defect 2084
        colsHidden = "";
        cmHistoryListGrid.sortIndex = 1;
        dBPersistentFlag = false;
        gridToBeReset = true;
        cmHistoryListGrid.loadOrder(colsOrder);
        cmHistoryListGrid.loadPagingSorting(colsSorting,1);
        cmHistoryListGrid.loadSize(colsWidth);
		//cmHistoryListGrid.setColumnsVisibility("false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false");		// commented for CI5
		//CI-6 Changes Start PARTHA
		//cmHistoryListGrid.setColumnsVisibility("false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false");  // changed for CI5
		cmHistoryListGrid.setColumnsVisibility("false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false");  
		//CI-6 Changes End PARTHA
        customizedGridURL = updateGridURL(customizedGridURL, 1, 'des', cmHistoryListGrid.filterValues);//Order changed to des by Sourav for defect 2084
        cmHistoryListGrid.clearAndLoad(customizedGridURL);
        setTimeout(clearMessage,3000);
    };
	function print(){  
		//alert("In Print..");
	
		url ="<portlet:renderURL windowState='<%= LiferayWindowState.POP_UP.toString() %>'>" + 
			"<portlet:param name='action' value='serviceHistoryList' />" + 
			"</portlet:renderURL>";
		    var iWidth=900;
		    var iHeight=600;
		    var iTop = (window.screen.availHeight-30-iHeight)/2;        
		    var iLeft = (window.screen.availWidth-10-iWidth)/2;           
		    window.open(url,
		    		'ServiceRequestList',
				    'height='+iHeight+
				    ',innerHeight='+
				    iHeight+',width='+
				    iWidth+',top='+iTop+
				    ',left='+iLeft+
				    ',status=no,toolbar=no,menubar=no,location=no,resizable=yes,scrollbars=yes,titlebar=no');
	};
   function closeCustomizedWindow(){
	if(document.getElementById('grid_menu')!=null)
	{
   	  document.getElementById('grid_menu').style.display = 'none';
	}
   }
</script>
<script type="text/javascript">
//---- Ominture script 
     addPortlet("Service Request List");
</script>