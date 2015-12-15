
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<%@ include file="/WEB-INF/jsp/includeGrid.jsp"%>
<%@ include file="/WEB-INF/jsp/dateUtil.jsp"%>
<%@ include file="/WEB-INF/jsp/includeDatePicker.jsp"%>
<%@page import="com.lexmark.constants.LexmarkPPOmnitureConstants"%>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/mps.css?version=<html:fileCacheVersion/>" />
<!--[if IE 7]>
	<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/mps_ie7.css?version=<html:fileCacheVersion/>" />
<![endif]-->
<script src="<html:rootPath/>js/expand.js"></script>

<portlet:renderURL var="orderRequestDetailURL">
	<portlet:param name='action' value='retrieveOrderRequest' />
</portlet:renderURL>
<portlet:renderURL  var="updateOrderRequestUrl">
	<portlet:param name="action" value="showUpdateOrderRequestPage" />
	<portlet:param name="requestFromPage" value="orderRequestsListView" />
</portlet:renderURL>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/in-styles.css?version=<html:fileCacheVersion/>" />
  
 
  <div id="content-wrapper">
    <div class="journal-content-article">
      <h1><spring:message code="requestInfo.heading.serviceOrder.serviceOrders"/></h1>
     <%-- <h2 class="step"><spring:message code="requestInfo.heading.serviceOrder.serviceOrders"/></h2> --%>
     
    </div>
    <div class="main-wrap">
      <div class="content">
        <div class="left-nav">
          <div id="filterContainer" class="left-nav-inner"> 
            <!-- LEFT COLUMN CONTENT GOES HERE -->
            <div class="left-nav-header">
              <h3><spring:message code="requestInfo.heading.serviceOrder.filterByStatus"/></h3>
            </div>
            <div class="radioCss">
	            <ul class="filters radio">
	              <li> <span>
	                <input id="filter3Open" type="radio" onClick = "callOmnitureAction('<%=LexmarkPPOmnitureConstants.SERVICEORDERSLIST%>','<%=LexmarkPPOmnitureConstants.SERVICEORDERLISTFILTERBYOPENREQUESTS%>');" name="status" value="Open"  checked="checked">
	                </span>
	                <label for="filter3Open"><spring:message code="request.filter.open" /></label>
	              </li>
	              <li> <span>
	                <input id="filter3Closed" type="radio" onClick = "callOmnitureAction('<%=LexmarkPPOmnitureConstants.SERVICEORDERSLIST%>','<%=LexmarkPPOmnitureConstants.SERVICEORDERLISTFILTERBYCLOSEDREQUESTS%>');" name="status" value="Closed">
	                </span>
	                <label for="filter3Closed"><spring:message code="request.filter.Closed" /></label>
	              </li>
	              <li> <span>
	                <input id="filter3All" type="radio" onClick = "callOmnitureAction('<%=LexmarkPPOmnitureConstants.SERVICEORDERSLIST%>','<%=LexmarkPPOmnitureConstants.SERVICEORDERLISTFILTERBYALLREQUESTS%>');" name="status" value="Show All">
	                </span>
	                <label for="filter3All"><spring:message code="request.filter.all" /></label>
	              </li>
	            </ul>
            </div>
            <div class="left-nav-header inlineTitle">
              <h3><spring:message code="requestInfo.heading.serviceOrder.filterByDateRange"/></h3>
            </div>
            <%--<ul class="filters">
              <li>
                <label for="fromDate">From:</label>
                <span>
                <input type="date" id="fromDate" class="w100"/>
                <img src="<html:imagesPath/>transparent.png" class="ui-icon calendar-icon" title="Select a Date" width="23" height="23"/></span></li>
              <li>
                <label for="toDate">To:</label>
                <span>
                <input type="date" id="toDate" class="w100"/>
                <img src="<html:imagesPath/>transparent.png" class="ui-icon calendar-icon" title="Select a Date" width="23" height="23"/></span></li>
            </ul> --%>
            <div class="radioCss">
					<table id="dateRangeFilterContainer">
						<tr>
							<td class="padding-top-3px"></td>
							<td>
								<div class="text-align-left">
									<div class="div-style19">
										<span class="div-style20">
										<spring:message code="request.filter.from"/></span>
										<input class="localized-begin-date" type="text" name="localizedBeginDate" id="localizedBeginDate" size="5" readOnly="readonly"/>
										<img id="imgLocalizedBeginDate" src="<html:imagesPath/>transparent.png" class="ui-icon calendar-icon imgLocalizedBeginDate"/>
									</div>
									<div class="div-style19">
										<span class="div-style20"><spring:message code="request.filter.to"/></span>
										<input class="localized-end-date" type="text" name="localizedEndDate" id="localizedEndDate" size="5" readOnly="readonly"/>
										<img id="imgLocalizedEndDate"  src="<html:imagesPath/>transparent.png" class="ui-icon calendar-icon imgLocalizedEndDate"/>
									</div>
									<div>
										<div class="buttonContainerIn">
<%-- 							             <a href="javascript:void(0)" class="button"><span><spring:message code="request.button.go"/></span></a> --%>
										 <button class="button" 
										 onclick="callOmnitureAction('<%=LexmarkPPOmnitureConstants.SERVICEORDERSLIST%>','<%=LexmarkPPOmnitureConstants.SERVICEORDERLISTFILTERGO%>');">
										 <spring:message code="request.button.go"/></button>
							            </div>
										<!-- </a> -->
									</div>
								</div>
							</td>
						</tr>
					</table>
				</div>
            
          </div>
          <div class="left-nav-footer"> 
            <!-- NO CONTENT HERE PLS --> 
          </div>
        </div>
        <div class="right-column"> 
          <!-- MAIN CONTENT GOES HERE -->
         <!-- Commented for Defect #1799
           <h3 class="pageTitle">Service Orders</h3> -->
          <div class="grid-controls"> 
            <!-- Utilities Start -->
            <div class="utilities">
              <ul>
                <li class="first"><a href="#"><img src="<html:imagesPath/>transparent.png" class="ui-icon spreadsheet-icon" alt="<spring:message code="requestInfo.tooltip.exportToExcel"/>" title="<spring:message code="requestInfo.tooltip.exportToExcel"/>" onclick="download('csv')"></a></li>
                <li><a href="#"><img src="<html:imagesPath/>transparent.png" class="ui-icon pdf-icon" alt="<spring:message code="requestInfo.tooltip.exportToPDF"/>" title="<spring:message code="requestInfo.tooltip.exportToPDF"/>" onclick="download('pdf')"></a></li>
                <li><a href="#"><img width="23px" height="23px" src="<html:imagesPath/>transparent.png" class="ui-icon print-icon" alt="<spring:message code="requestInfo.tooltip.printThisPage"/>" title="<spring:message code="requestInfo.tooltip.printThisPage"/>" onclick="print()";></a></li>
              </ul>
            </div>
            <!-- utilities End --> 
            <!-- Expand-min Start -->
            <div class="expand-min">
              <ul>
                <li class="first"><img src="<html:imagesPath/>transparent.png" class="ui-icon minimize-icon" alt="<spring:message code="requestInfo.label.minimizeAll"/>" onclick="minimizeAll()" title="<spring:message code="requestInfo.label.minimizeAll"/>" /><a href="javascript:void(0)" onClick="minimizeAll()";><spring:message code="requestInfo.label.minimizeAll"/></a></li>
                <li><img src="<html:imagesPath/>transparent.png" class="ui-icon expand-icon" alt="<spring:message code="requestInfo.label.expandAll"/>" onclick="expandAll()" title="<spring:message code="requestInfo.label.expandAll"/>" /><a href="javascript:void(0)" onClick="expandAll()";><spring:message code="requestInfo.label.expandAll"/></a></li>
                <li><img src="<html:imagesPath/>transparent.png" class="ui-icon customize-icon" alt="<spring:message code="label.customize"/>" title="<spring:message code="label.customize"/>"/><a href="javascript:void(0);" id='headerMenuButton' title="<spring:message code="label.customize"/>"><spring:message code="label.customize"/></a></li>
                <li><img src="<html:imagesPath/>transparent.png" class="ui-icon reset-icon" alt="<spring:message code="requestInfo.label.reset"/>" title="<spring:message code="requestInfo.label.reset"/>" /><a href="javascript:doReset();"><spring:message code="requestInfo.label.reset"/></a> </li>
              </ul>
            </div>
            <!-- Expand-min End --> 
            
          </div>
          <!-- grid-controls -->
          <div id="emailPrint">
	          <div class="portlet-wrap rounded">
	            <div class="portlet-wrap-inner">
	              <div id="gridOrderRequestList" class="gridbox gridbox_light"></div>
	              <div id="loadingNotification" class='gridLoading'>
					<!--spring:message code="label.loadingNotification"/-->&nbsp;<img src="<html:imagesPath/>gridloading.gif" /><br>
				  </div>
				  <div class="pagination" id="paginationId"> <span id="pagingArea"></span><span id="infoArea"></span> </div>
	            </div>
	          </div>
	        </div>
          
          <!-- mygrid_container --> 
        </div>
      </div>
    </div>
  </div>



<script type="text/javascript">
var requestStatusList = [];
<c:forEach items="${requestListForm.orderRequestStatusMap}" var="requestStatus" varStatus = "status" >
	requestStatusList[${status.index}] = ["${requestStatus.key}","${requestStatus.value}"];
</c:forEach>


var requestListURL='<portlet:resourceURL id="retrieveOrderRequestList"></portlet:resourceURL>';
dhx_globalImgPath = "<html:imagesPath/>comboImgs/";
requestListGrid = new dhtmlXGridObject('gridOrderRequestList');
requestListGrid.setImagePath("<html:imagesPath/>gridImgs/");
requestListGrid.setHeader(",<spring:message code='requestInfo.serviceOrder.listHeader.orderList'/>");

//House Number, County and District added for CI BRD 13-10-08--STARTS
var attachHeader=",&nbsp;,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#combo_filter,#text_filter,#text_filter,,#text_filter,,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter";
//alert(attachHeader);
requestListGrid.attachHeader(attachHeader);
requestListGrid.setColAlign("left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left");
requestListGrid.setInitWidths("30,165,165,165,100,150,130,150,140,170,200,200,200,200,100,100,100,80,80,90,180,180");
requestListGrid.setColTypes("sub_row,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro");
<%-- Changes for MPS 2.1 removed sorting from response metric--%>
requestListGrid.setColSorting("na,str,str,str,na,na,na,na,str,na,str,na,na,na,na,na,na,na,na,na,na,na");
<%-- ENDS Changes for MPS 2.1 removed sorting from response metric--%>
requestListGrid.setLockColVisibility("false,false,true,false,false,false,false,false,false,false,false,false,false,false,false,false,false");




requestListGrid.enableAutoHeight(true);
requestListGrid.enableMultiline(true);
requestListGrid.setSkin("light");
requestListGrid.enableColumnMove(true);
requestListGrid.init();
requestListGrid.prftInit();

requestListGrid.enablePaging(true, 20, 10, "pagingArea", true);
requestListGrid.setPagingSkin("bricks");
requestListGrid.sortIndex = null;
requestListGrid.setCustomizeCombo(requestStatusList,7);
if(colsSorting.length==0){
	requestListGrid.a_direction = requestListGrid.a_direction||"des";	
	colsSorting = "1,des";		

	requestListGrid.setSortImgState(true,1,requestListGrid.a_direction);
}else{
	requestListGrid.setSortImgState(true,colsSorting.split(",")[0],colsSorting.split(",")[1]);
}

requestListGrid.columnChanged = true;
requestListGrid.enableHeaderMenuForButton('headerMenuButton','headerMenuButton');

dBPersistentFlag = false; 
requestListGrid.loadOrder(colsOrder);

requestListGrid.loadPagingSorting(colsSorting,1);
requestListGrid.loadSize(colsWidth);
//requestListGrid.setColumnsVisibility("false,false,false,false,true,true,true,false,false,false,true,true,true,true,true,true,true,true,true");
if(!requestListGrid.loadHiddenColumns(colsHidden) && colsWidth=="") {
	setDefaultHiddenColumns();
}
dBPersistentFlag = true;
requestListGrid.enableAutoSavingToDB("${saveCustomizedGridSettingURL}");

requestListGrid.attachEvent("onXLS", function() {

	
	document.getElementById('pagingArea').style.visibility = 'hidden';
	document.getElementById('loadingNotification').style.display = 'block';
	if(requestListGrid.a_direction=='asc'){
		requestListGrid.setSortImgState(true, requestListGrid.getDefaltSortImagIndex(requestListGrid.sortIndex,1), 'asc');
    }else{
    	requestListGrid.setSortImgState(true, requestListGrid.getDefaltSortImagIndex(requestListGrid.sortIndex,1), 'des');
    }
});

requestListGrid.attachEvent("onXLE", function() {
	 document.getElementById('loadingNotification').style.display = 'none';
	    document.getElementById('pagingArea').style.visibility = 'visible';
	    setTimeout(function(){
    		rebrandPagination();
    	
    	},100);
});




var requestsQueryOperator = {
		queryParams:["timezoneOffset","status","beginDate","endDate","filterCriterias","direction","orderBy"],
		needSavedParams:["status","beginDate2","endDate2","filterCriterias","page"],
		page:0,

		initializePage:function(savedKeyValues){
			if(!savedKeyValues){
				//initialize date input with default value
				jQuery("#localizedBeginDate").val(localizeFormatDate(new Date().addDays(-30)));
				jQuery("#localizedEndDate").val(localizeDate(todayStr));
				return;
			}

			
			jQuery("#filterContainer").find("input[type='radio']").each(function(index,element){
				if(savedKeyValues[element.name] == element.value){
					jQuery(element).attr("checked","checked");
				}
			});
			
			
			if(savedKeyValues["beginDate2"] && savedKeyValues["beginDate2"].length > 0)
				jQuery("#localizedBeginDate").val(savedKeyValues["beginDate2"]);
			if(savedKeyValues["endDate2"] && savedKeyValues["endDate2"].length > 0)
				jQuery("#localizedEndDate").val(savedKeyValues["endDate2"]);

			
			var first=true;
			if(savedKeyValues["page"]){
				requestListGrid.attachEvent("onXLE", function() {
				
					if(first){
						requestListGrid.changePage(savedKeyValues["page"]);
						first = false;
					}
					setTimeout(function(){
			    		rebrandPagination();
			    	
			    	},100);
				});
			}
			
			
			if(savedKeyValues["filterCriterias"] && savedKeyValues["filterCriterias"].length > 0)
				<%-- Changes for MPS 2.1 defect 10648 --%>
				requestListGrid.setFiltersValue(decodeURIComponent(savedKeyValues["filterCriterias"]));
				<%--Ends  Changes for MPS 2.1 defect 10648 --%>
		},

		_clear:function(){
			for(var k=0;k<this.needSavedParams.length;k++){
				this[this.needSavedParams[k]] = null;
			}
			for(var k=0;k<this.queryParams.length;k++){
				this[this.queryParams[k]] = null;
			}
		},
		
		_refresh:function(){
			this._clear();
			
			jQuery("#filterContainer").find("input[type='radio']:checked").each(function(index,element){
				requestsQueryOperator[element.name] = element.value;
			});
							
			
			this["beginDate"] = formatDateToDefault(jQuery("#localizedBeginDate").val());
			this["endDate"] = formatDateToDefault(jQuery("#localizedEndDate").val());
			
			this["beginDate2"] = jQuery("#localizedBeginDate").val();
			this["endDate2"] = jQuery("#localizedEndDate").val();
			
			
			this["filterCriterias"] = requestListGrid.filterValues;
			this["direction"] = requestListGrid.a_direction;
			requestListGrid.columnChanged = true;
			this["orderBy"] = requestListGrid.getSortColByOffset();
			this["page"] = requestListGrid.currentPage;

			this["timezoneOffset"] = timezoneOffset;				
		},
		
		
		getNeedSavedKeyValues:function(){
			this._refresh();
			
			var keyValus =	'{' ;
			for(var k=0;k<this.needSavedParams.length;k++){
				if(this[this.needSavedParams[k]]){
					keyValus += "\""+this.needSavedParams[k]+"\"" +':"'+this[this.needSavedParams[k]]+'"';
					//keyValus += this.needSavedParams[k] +':"'+this[this.needSavedParams[k]]+'"';
					if(k != this.needSavedParams.length-1)
						keyValus += ',';
				}
			}
			keyValus += '}';
			return keyValus;
		},
		
		getQueryURL:function(){
			this._refresh();
			
			var queryURL = requestListURL;
			for(var k=0;k<this.queryParams.length;k++){
				if(this[this.queryParams[k]] || this.queryParams[k] == "timezoneOffset"){
					queryURL += "&" + this.queryParams[k] + "=" + this[this.queryParams[k]];
				}
			}
		    return queryURL;
		}
	};

(function(){
	var retrieveQueryParamsUrl = '<portlet:resourceURL id="retrieveQueryParams"></portlet:resourceURL>';
	retrieveQueryParamsUrl += "&gridId=gridOrderRequestList&timestamp=" + (new Date()).valueOf();
	var backFrmDetail="${back}";
	
	if(backFrmDetail=="true"){

		jQuery.getJSON(retrieveQueryParamsUrl,function(result){
			requestsQueryOperator.initializePage(result.data);
			requestListGrid.loadXML(requestsQueryOperator.getQueryURL());
	
		});
	}else{
		jQuery("#localizedBeginDate").val(localizeFormatDate(new Date().addDays(-30)));
		jQuery("#localizedEndDate").val(localizeDate(todayStr));
			requestListGrid.loadXML(requestsQueryOperator.getQueryURL());
		}


	
})();

requestListGrid.attachEvent("onBeforeSorting", function(index){
	callOmnitureAction('Request', 'Request List Sort');
	var a_state = requestListGrid.getSortingState();

	if(a_state[0] == (index)){
		requestListGrid.a_direction = ((a_state[1] == "asc") ? "des":"asc" );
	}else {
		requestListGrid.a_direction = "asc" ;
		requestListGrid.columnChanged = true;
	}
	requestListGrid.sortIndex = index;

	if(requestListGrid.a_direction=='asc'){
		requestListGrid.setSortImgState(true, requestListGrid.getDefaltSortImagIndex(requestListGrid.sortIndex,1), 'asc');
    }else{
    	requestListGrid.setSortImgState(true, requestListGrid.getDefaltSortImagIndex(requestListGrid.sortIndex,1), 'des');
    }		
	reloadGrid();	
	return true;
});

requestListGrid.attachEvent("onRowSelect",function(rowId,cellIndex){
	var cellValue = requestListGrid.cells(rowId,cellIndex).getValue();
	if (cellIndex > 0 && (!cellValue.match("showServiceRequestDetail") && !cellValue.match("showClaimRequestDetail"))) {
		var cell=requestListGrid.cellById(rowId,0);
		var	row = requestListGrid.getRowById(rowId);
		if(!row._expanded){
			cell.open();
		}else{
			cell.close();
		}
	}
});

requestListGrid.attachEvent("onFilterStart", function(indexes,values){
/*	requestListGrid.filterValues = values;  Commented by sankha for LEX:AIR00073687 */
 	<%-- Changes for MPS 2.1 defect 10648 --%>
 	requestListGrid.filterValues = encodeURIComponent(values); /* Added by sankha for LEX:AIR00073687 */
 	<%--Ends  Changes for MPS 2.1 defect 10648 --%>
	setGridFilerTimer(reloadGrid);
});


requestListGrid.attachEvent("onPaging", function(count){
	saveGridQueryParams("gridOrderRequestList",requestsQueryOperator.getNeedSavedKeyValues(),function(){
		refreshGridSettingOnPage(requestListGrid);
	});		
});


requestListGrid.attachEvent("onMouseOver", function(id,ind){
	if(ind!=0){
		var style = requestListGrid.cells(id,ind).cell.style;
	    style.cssText += ";cursor: pointer;";
		return true;
	}
});

function reloadGrid(){
	clearMessage();
	refreshGridSettingOnPage(requestListGrid);
	saveGridQueryParams("gridOrderRequestList",requestsQueryOperator.getNeedSavedKeyValues(),function(){
		requestListGrid.clearAndLoad(requestsQueryOperator.getQueryURL());
	});
};

function minimizeAll() {
	
	callOmnitureAction('<%=LexmarkPPOmnitureConstants.SERVICEORDERSLIST%>','<%=LexmarkPPOmnitureConstants.SERVICEORDERLISTMINIMIZEALL%>');
	var pageSize = requestListGrid.rowsBufferOutSize;
	var begin = (requestListGrid.currentPage-1) * pageSize;
	var end =  requestListGrid.currentPage * pageSize - 1;

	requestListGrid.forEachRow(function(id){
		if(id >= begin && id <= end)
			requestListGrid.cellById(id,0).close();
	});
};

function expandAll() {
	callOmnitureAction('<%=LexmarkPPOmnitureConstants.SERVICEORDERSLIST%>','<%=LexmarkPPOmnitureConstants.SERVICEORDERLISTEXPANDALL%>');
	var pageSize = requestListGrid.rowsBufferOutSize;
	var begin = (requestListGrid.currentPage-1) * pageSize;
	var end =  requestListGrid.currentPage * pageSize - 1;

	requestListGrid.forEachRow(function(id){
		if(id >= begin && id <= end)
			requestListGrid.cellById(id,0).open();
	});
};

function setDefaultHiddenColumns(){
	requestListGrid.setColumnHidden(4,true);
	requestListGrid.setColumnHidden(5,true);
	requestListGrid.setColumnHidden(6,true);
	//requestListGrid.setColumnHidden(9,true); commented for Defect 1396
	requestListGrid.setColumnHidden(10,true);
	requestListGrid.setColumnHidden(11,true);
	requestListGrid.setColumnHidden(12,true);
	requestListGrid.setColumnHidden(13,true);
	requestListGrid.setColumnHidden(14,true);
	requestListGrid.setColumnHidden(15,true);
	requestListGrid.setColumnHidden(16,true);
	requestListGrid.setColumnHidden(17,true);
	//Added for CI BRD 13-10-08
	requestListGrid.setColumnHidden(18,true);
	requestListGrid.setColumnHidden(19,true);
	requestListGrid.setColumnHidden(20,true);
	requestListGrid.setColumnHidden(21,true);
};

function doReset(){
	 callOmnitureAction('<%=LexmarkPPOmnitureConstants.SERVICEORDERSLIST%>','<%=LexmarkPPOmnitureConstants.SERVICEORDERLISTRESET%>');
	resetGridSetting('gridOrderRequestList', resetCallback);  
};
//Changes done for CI BRD 13-10-08
function resetCallback() {
    dBPersistentFlag = false;
    clearMessage();
    colsOrder = "0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18";
    colsWidth = "px:30,165,165,165,100,150,130,150,140,170,200,200,200,200,100,100,100,80,80,90,180,180";
    colsSorting = "1,des";
    colsHidden = "";
    requestListGrid.sortIndex = 1;
    gridToBeReset = true;
    requestListGrid.loadOrder(colsOrder);
    requestListGrid.loadPagingSorting(colsSorting,1);
    requestListGrid.setSortImgState(true,1,requestListGrid.a_direction);
    requestListGrid.loadSize(colsWidth);
    requestListGrid.setColumnsVisibility("false,false,false,false,true,true,true,false,false,false,true,true,true,true,true,true,true,true,true,true,true,true");
	setDefaultHiddenColumns();
	requestListGrid.clearFiltersValue();
	reloadGrid();
	dBPersistentFlag = true;
};
//House Number, County and District added for CI BRD 13-10-08--ENDS

function download(type){
	    if(type=='csv'){
		callOmnitureAction('<%=LexmarkPPOmnitureConstants.SERVICEORDERSLIST%>','<%=LexmarkPPOmnitureConstants.SERVICEORDERLISTEXPORTTOEXCEL%>');
		}
		else if(type=='pdf')
			{
			callOmnitureAction('<%=LexmarkPPOmnitureConstants.SERVICEORDERSLIST%>','<%=LexmarkPPOmnitureConstants.SERVICEORDERLISTEXPORTTOPDF%>');
		    }	
	
	callOmnitureAction('Order Request', 'Request List Download - ' + type);
	if(requestListGrid.getRowId(0)==null){
		jAlert("<spring:message code='download.noDataFound'/>");
		return false;
	}
	var downloadURL='<portlet:resourceURL id="downloadOrderRequestsURL"/>'+ "&timezoneOffset="+timezoneOffset;
	window.location=downloadURL+"&downloadType="+type;
};

function acceptOrderRequest(vendorId,orderId){	
 callOmnitureAction('<%= LexmarkPPOmnitureConstants.SERVICEORDERSLIST%>', '<%= LexmarkPPOmnitureConstants.SERVICEORDERLISTACCEPTREQUEST%>');
	showOverlay();
	var acceptOrderRequestURL = "<portlet:resourceURL id='acceptOrderRequest'></portlet:resourceURL>" + "&orderId=" + orderId + "&vendorId=" + vendorId;
	doAjax(acceptOrderRequestURL, function(loader){
			jQuery("#Accept_" + loader.data).remove();
			jQuery("#Update_" + loader.data).show();
			jQuery('#status_'+loader.data).html('<spring:message code='requestInfo.orderListView.status'/>');
			setTimeout(clearMessage,3000);
			
	}, function(loader){
		var status = parseLoader(loader);	
		showSystemError(status.message);
		return true;
	});
}




jQuery("#filterContainer").find("input[type=radio]").each(function(index,radioElement){
		jQuery(radioElement).click(reloadGrid);
});


jQuery("#dateRangeFilterContainer").find("tr > td:first").click(function(){
	var dateSelectBox=jQuery("#dateRangeFilterContainer").find("tr:last");
	if(dateSelectBox.is(":hidden")){
		jQuery(this).find("img").attr("src","<html:imagesPath/>treeImgs/minus5.gif");
		dateSelectBox.fadeIn();
	}else{
		jQuery(this).find("img").attr("src","<html:imagesPath/>treeImgs/plus5.gif");
		dateSelectBox.fadeOut();
	}
});

jQuery("#dateRangeFilterContainer").find(".button").click(function(){
	callOmnitureAction('<%= LexmarkPPOmnitureConstants.SERVICEORDERSLIST%>', '<%= LexmarkPPOmnitureConstants.SERVICEORDERLISTFILTERGO%>');
	reloadGrid();
});


jQuery("#dateRangeFilterContainer").find("input").mouseup(function(){
	var beginDate=this.id=="localizedBeginDate" ? convertDateToString(new Date().addDays(-180)) : formatDateToDefault(jQuery("#localizedBeginDate").val());
	var endDate=this.id=="localizedEndDate" ? todayStr : formatDateToDefault(jQuery("#localizedEndDate").val());
	show_cal(this.id, beginDate, endDate);
});

jQuery("#dateRangeFilterContainer").find("input ~ img").mouseup(function(){
	jQuery(this).prev().mouseup();
});

function showOrderRequestDetail(vendorId,orderId,srId){
	callOmnitureAction('<%= LexmarkPPOmnitureConstants.SERVICEORDERSLIST%>', '<%= LexmarkPPOmnitureConstants.SERVICEORDERLISTREQUESTNUMBERLINK%>');
	showOverlay();
	window.location.href="${orderRequestDetailURL}&vendorAccountId=" +vendorId+ "&orderNumber=" + orderId + "&requestNumber=" + srId + "&timezoneOffset=" + timezoneOffset;
};

function showUpdateOrderRequestPage(vendorId,orderId,srId){
	callOmnitureAction('<%=LexmarkPPOmnitureConstants.SERVICEORDERSLIST%>','<%=LexmarkPPOmnitureConstants.SERVICEORDERLISTUPDATEORDERREQUEST%>');
	showOverlay();
	window.location.href="${updateOrderRequestUrl}&vendorAccountId=" +vendorId+ "&orderNumber=" + orderId + "&requestNumber=" + srId + "&timezoneOffset=" + timezoneOffset;
};

function print(){
	callOmnitureAction('<%=LexmarkPPOmnitureConstants.SERVICEORDERSLIST%>','<%=LexmarkPPOmnitureConstants.SERVICEORDERLISTPRINTTHISPAGE%>');

		var url="<portlet:renderURL windowState='<%= LiferayWindowState.POP_UP.toString() %>'><portlet:param name='action' value='printOrderListDetail' /></portlet:renderURL>";
		var iWidth=1000;
	    var iHeight=600;
	    var iTop = (window.screen.availHeight-30-iHeight)/2;
	    var iLeft = (window.screen.availWidth-10-iWidth)/2;
	    window.open(url,
	    		'historyList',
			    'height='+iHeight+
			    ',innerHeight='+
			    iHeight+',width='+
			    iWidth+',innerWidth='+
			    iWidth+',top='+iTop+
			    ',left='+iLeft+
			    ',status=no,toolbar=yes,menubar=no,location=no,resizable=yes,scrollbars=yes,titlebar=no');
	};



</script>

