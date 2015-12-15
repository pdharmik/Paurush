<%@ taglib uri="/WEB-INF/tld/liferay-portlet.tld" prefix="portlet"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="html" tagdir="/WEB-INF/tags/html" %>
<%@ taglib uri="/WEB-INF/tld/liferay-portlet.tld" prefix="portlet"%> <!-- Added for CI BRD13-10-02 -->
<style type="text/css"><%@ include file="../css/grid/dhtmlxgrid.css" %></style> 
<style type="text/css"><%@ include file="../css/grid/dhtmlxgrid_dhx_skyblue.css" %></style> 
<style type="text/css"><%@ include file="../css/grid/dhtmlxgrid_pgn_bricks.css" %></style>
<style type="text/css"><%@ include file="../css/grid/dhtmlxgrid_hmenu.css" %></style> 
<style type="text/css"><%@ include file="../css/grid/skins/dhtmlxgrid_dhx_lex.css" %></style> 
<style type="text/css"><%@ include file="../css/jQueryAlert/jquery.alerts.css" %></style> 
<style type="text/css"><%@ include file="../css/tree/dhtmlxtree.css" %></style>
<style type="text/css"><%@ include file="../css/combo/dhtmlxcombo.css" %></style>



 
<script type="text/javascript">
var global_paging_message = {results:"<spring:message code='page.results'/>",
	    records:"<spring:message code='page.records'/>",
	    to:" <spring:message code='page.to'/> ",
	    page:"<spring:message code='page.page'/> ",
	    perpage:"<spring:message code='page.perpage'/>",
	    first:"<spring:message code='page.first'/>",
	    previous:"<spring:message code='page.previous'/>",
	    found:"<spring:message code='page.found'/>",
	    next:"<spring:message code='page.next'/>",
	    last:"<spring:message code='page.last'/>",
	    of:" <spring:message code='page.of'/> ",
	    notfound:"<spring:message code='page.notfound'/>"
};

var  global_button_message = { close: "<spring:message code='button.close'/>",
		ok: "<spring:message code='button.ok'/>",
		cancel: "<spring:message code='button.cancel'/>",
		selectColumns:"<spring:message code='button.selectColumns'/>"
};
</script>



<script type="text/javascript"><%@ include file="../../js/jQueryAlert/jquery.alerts.js"%></script>
<!--<script type="text/javascript" src="<html:rootPath/>js/jQueryAlert/jquery.alerts.js"></script>-->
<script type="text/javascript" src="<html:rootPath/>js/grid/dhtmlxcommon.js"></script>
<script type="text/javascript" src="<html:rootPath/>js/grid/reSkinPagination.js"></script>
<script type="text/javascript" src="<html:rootPath/>js/grid/dhtmlxgrid.js"></script>
<script type="text/javascript" src="<html:rootPath/>js/grid/dhtmlxgridcell.js"></script>
<script type="text/javascript" src="<html:rootPath/>js/grid/ext/dhtmlxgrid_excell_sub_row.js"></script>
<script type="text/javascript" src="<html:rootPath/>js/grid/ext/dhtmlxgrid_filter.js"></script>
<script type="text/javascript" src="<html:rootPath/>js/grid/ext/dhtmlxgrid_srnd.js"></script>
<script type="text/javascript"><%@ include file="../../js/grid/ext/dhtmlxgrid_pgn.js"%></script>
<!--<script type="text/javascript" src="<html:rootPath/>js/grid/ext/dhtmlxgrid_pgn.js"></script>-->
<script type="text/javascript" src="<html:rootPath/>js/grid/ext/dhtmlxgrid_mcol.js"></script>
<script type="text/javascript" src="<html:rootPath/>js/grid/ext/dhtmlxgrid_hmenu.js"></script>
<script type="text/javascript" src="<html:rootPath/>js/combo/dhtmlxcombo.js"></script>
<script type="text/javascript" src="<html:rootPath/>js/combo/dhtmlxcombo_whp.js"></script>
<!-- The below JS files override or extend one or more functions in their non-'prft' counter parts... -->
<script type="text/javascript" src="<html:rootPath/>js/grid/prft/prft_dhtmlxgrid.js"></script>
<script type="text/javascript" src="<html:rootPath/>js/grid/prft/prft_dhtmlxgrid_mcol.js"></script>
<script type="text/javascript" src="<html:rootPath/>js/grid/prft/prft_dhtmlxgrid_ssw.js"></script>
<script type="text/javascript" src="<html:rootPath/>js/grid/prft/prft_dhtmlxgrid_hmenu.js"></script>
<%--<script type="text/javascript" src="<html:rootPath/>js/grid/prft/prft_dhtmlxgrid_excell_sub_row.js"></script> --%>
<%-- Commented out above js to have subrow grid property of original js --%>
<script type="text/javascript" src="<html:rootPath/>js/grid/ext/dhtmlxgrid_excell_sub_row.js"></script>
<script  type="text/javascript" src='<html:rootPath/>js/grid/ext/dhtmlxgrid_excell_link.js'></script>

<script type="text/javascript" src="<html:rootPath/>js/util.js"></script>

<%@ page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>

<style type="text/css">
.hdrcell{
    white-space:nowrap;
	overflow:hidden;
	text-overflow:ellipsis;
	width:90%;
}

<!-- added for CI5 -->
.hdrcell.filter{
	white-space:nowrap;
	overflow:hidden;
	text-overflow:clip;
	width:90%;
}
<!-- end of addition for CI5 -->
<!-- change by MPS team to resolve the calender issue in History page -->
.dhx_header_cmenu{
        background-color:#ffffff;
        border:2px outset silver;
        z-index:2;
        font-family: Helvetica,Arial,sans-serif;
        font-size: 11px;
    }
	.dhx_header_cmenu_item{
    	white-space:nowrap;
	}
</style>
    


<script type="text/javascript">
	var rowCount = 50;
	var pageCount = 10;


</script>
<script type="text/javascript">
    function handleErrorResponse(respone){
	var message = null;
	var title = null;
	if(respone.responseXML){
		try{
			var doXPath = dtmlXMLLoaderObject.prototype.doXPath;
			var root = doXPath("/result/@succeed", respone.responseXML, null, "single");
			if(root){
				message = doXPath("/result/message/text()", respone.responseXML, null, "single").nodeValue;
				title = doXPath("/result/group/text()", respone.responseXML, null, "single").nodeValue;
			}
		}catch(e){}
		if(!message) message = "<spring:message code='exception.generalError.title'/>";
	}else{
		message = respone.responseText;
	}
	if(message && title) {
		popMessage(message,title);
	}
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

    function myErrorHandler(type,name,data){ 
		if(data[0].responseText.indexOf("<spring:message code='exception.notlogin.title'/>")>-1) {
	    	window.location.href="/c/portal/login?";
		} else {
			handleErrorResponse(data[0]);
		}
		return null;
	}


   var errorContent = "";
   dhtmlxError.catchError("LoadXML", myErrorHandler);


	function domText(dom){
		var div = document.createElement("div");
		div.innerHTML = dom; 
		var text = div.innerText||div.textContent;
		div = null;//avoid mem leak
		return text.toLowerCase();
	}
	function domSort(a,b,order){
		a = domText(a);
		b = domText(b);
		return (a>b?1:-1)*(order=="asc"?1:-1);
	}
	function StrSortCI(a,b,order){
		a = a.toLowerCase();
		b = b.toLowerCase();
		return (a>b?1:-1)*(order=="asc"?1:-1);
	}
	function IntSort(a,b,order){
		a = parseInt(a);
		b = parseInt(b);
		isNaN(a)?a=Number.MAX_VALUE:a;
		isNaN(b)?b=Number.MAX_VALUE:b;
		return (a>b?1:-1)*(order=="asc"?1:-1);
	}
</script>


<%-- Portlet resource URL redefined in BaseController as a seperate method for reset grid setting for MPS--%>
<portlet:resourceURL var="resetGridSettingURLForDMCM" id="resetGridSettingForDMCM"/>


<script type="text/javascript">
//------------retrieve User Grid Setting from request
//------------Those variables will be used in the jsp page which contains grid that requires customized setting.
var gridId = new String("${gridSettings.gridId}");
var colsOrder = new String("${gridSettings.colsOrder}");
var colsWidth = new String("${gridSettings.colsWidth}");
var colsSorting = new String("${gridSettings.colsSorting}");
var colsHidden = new String("${gridSettings.colsHidden}");
var dBPersistentFlag = true;
var customizedGridURL;
var gridToBeReset = false;
<portlet:resourceURL var="saveCustomizedGridSettingURL" id="saveCustomizedGridSetting"/>
<portlet:resourceURL var="resetCustomizedGridSettingURL" id="resetCustomizedGridSetting"/>
<portlet:resourceURL var="retrieveGridParamsForCM" id="retrieveGridParamsForCM"/>


function resetGridSetting(gridId, callback){
	jConfirm("<spring:message code='message.resetGridSttingConfirmation'/>", "", function(confirmResult) {
		if(confirmResult){
			doAjaxSyncGet("${resetCustomizedGridSettingURL}&gridId="+gridId,callback);
		}
	});
}

/** This method is to reset grid setting for MPS 
 mapped to a seperate method in basecontroller 
*/
function resetGridSettingForDMCM(gridId, callback)
{
	jConfirm("<spring:message code='message.resetGridSttingConfirmation'/>", "", function(confirmResult) {
		if(confirmResult){
			doAjaxSyncGet("${resetGridSettingURLForDMCM}&gridId="+gridId,callback);
		}
	});
}

var filterTimer = null;
var filterTimerDelayInMilSeconds = 2000;
function setGridFilerTimer(loadFun) {
	if(filterTimer!=null) {
		clearTimeout(filterTimer);
	}
   	if(enterFlag){
		loadFun();
   	} else {
		filterTimer = setTimeout(loadFun, filterTimerDelayInMilSeconds);
   	}
}

var enterFlag = false;
window.document.onkeydown = function(event){
	if(event==null){
		event=window.event;
	}
	if(event.keyCode==13){
		enterFlag = true;
		
	}else{
		enterFlag = false;
	}
};	

/*
 * Since the page will not reload hence colsOrder,colsWidth,colsSorting and colsHidden
 * will not get refreshed if grid is doing grid.clearAndLoad();
 * We will need to refesh the colsOrder,colsWidth,colsSorting and 
 * colsHidden to the current grid setting before clearAndLoad method is called.
 */   
function refreshGridSettingOnPage(obj){
	colsOrder = new String(obj._c_order);
	
	colsSorting = new String(obj.sortIndex+","+obj.a_direction);
	
	if(obj.hiddenCols !="")
	colsHidden =  new String(obj.hiddenCols);
	
	if((obj.hiddenCols =="")&&(obj.databaseStoredNoValue))
	colsHidden = "";
	colsWidth = new String(obj.cellWidthPX);
	
	if(obj.cellWidthType=='px')
		colsWidth = new String(obj.cellWidthPX);
	else
		colsWidth = new String(obj.cellWidthPC);
}

 /*
 * Generates new grid url after the grid is reset, or filter of the grid is changed.
 * gridURL: the original grid url
 * orderIndex: the index of column by which the grid will be sorted (from 0)
 * direction: 'asc' or 'des'
 * filterValues: new values of "filterCriterias"
 */
function updateGridURL(gridURL, orderIndex, direction, filterValues) {
	 //alert('orderIndex'+orderIndex);
    if (direction != 'asc' && direction != 'des') {
        //alert('Invalid direction!');
        return false;
    }
    if (orderIndex < 0) {
        //alert('Invalid orderIndex');
        return false;
    }
    oldOrderBy = getArg(gridURL, 'orderBy');
    oldDirection = getArg(gridURL, 'direction');
    oldFilterValues = getArg(gridURL, 'filterCriterias');
    if (oldOrderBy != null && oldOrderBy != '') {
        gridURL = gridURL.replace('orderBy=' + oldOrderBy, 'orderBy=' + orderIndex);
    } else {
        gridURL = gridURL + '&orderBy=' + orderIndex;
    }

    if (oldDirection != null && oldDirection != '') {
        gridURL = gridURL.replace('direction=' + oldDirection, 'direction=' + direction); 
    } else {
        gridURL = gridURL + '&direction=' + direction;
    }

    // don't need to deal with filterCriterias, if the grid has no filter
    if (filterValues == null) {
        return gridURL;
    }
    
    if (oldFilterValues != null && oldFilterValues != '') {
        gridURL = gridURL.replace('filterCriterias=' + oldFilterValues, 'filterCriterias=' + filterValues);         
    } else {
        gridURL = gridURL + '&filterCriterias=' + filterValues;
    }
    return gridURL;
};

function applyGridCustomization(obj, colsHidden, colsOrder, colsWidth){
//	    dBPersistentFlag = false;
//		if(!obj.loadHiddenColumns(colsHidden) && colsWidth=="")
//				setDefaultHiddenColumns();
//			obj.loadOrder(colsOrder);
//			obj.loadSize(colsWidth);
//		dBPersistentFlag = true;
} 
	
 jQuery(document).ready(function() {
	i = 0;
	a = document.getElementsByTagName("div");
	while (element = a[i]) {
		if (element.className == "hdrcell") {
			element.style.textOverflow= "ellipsis";
			element.style.whiteSpace="nowrap";
			element.style.overflow = "hidden";
			element.style.width = "90%";
			element.title = element.innerHTML;
			} else 
			// Request id:2114   for CI5 START	
			if(element.className == "hdrcell filter") {
				element.style.textOverflow= "clip";
				element.style.whiteSpace="nowrap";
				element.style.overflow = "hidden";
				element.style.width = "90%";
			}
			//Request id:2114   for CI5 END	
		i++;
	}
})

// Changes for JAN release THis function generates grid parameters to be saved
function generateGridQueryParams(gridObj,startDate,endDate,optionChosen){
	 if(gridObj==null)
		 return null;
	 var queryString="";//"{";
	 queryString+="\"filterValues\":\"";
	 queryString+=gridObj.filterValues==null?"":gridObj.filterValues;
	 queryString+="\"";

	 //if its blank then value needs to be sent to clear session values else don't send
	 if(startDate!=null && endDate!=null && startDate!="" && endDate!="" ){
		 	queryString+=",\"startDate\":\""+startDate+"\"";
	 		queryString+=",\"endDate\":\""+endDate+"\"";
	 }

	 if(optionChosen!=null && optionChosen!="")
		 queryString+=",\"optionChosen\":\""+optionChosen+"\"";
	 
	 //queryString+="}";

	 return queryString; 
	 
	 
 }
 //This function sends JSON to the server to save the parameters
function saveGridQueryParams(gridId,saveKeyValues,callBackFunction){
	 jQuery.post("<portlet:resourceURL id='saveGridParamsForCM'></portlet:resourceURL>", 
				{
					queryParams: saveKeyValues, 
					gridId: gridId 
				},
				callBackFunction
			);	 
 }
</script>


