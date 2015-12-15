<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/lexmark.css?version=<html:fileCacheVersion/>" />
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/grid/dhtmlxgrid.css?version=<html:fileCacheVersion/>" /> 
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/grid/dhtmlxgrid_dhx_skyblue.css?version=<html:fileCacheVersion/>" /> 
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/grid/dhtmlxgrid_pgn_bricks.css?version=<html:fileCacheVersion/>" />
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/grid/dhtmlxgrid_hmenu.css?version=<html:fileCacheVersion/>" /> 
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/grid/skins/dhtmlxgrid_dhx_lex.css?version=<html:fileCacheVersion/>" />
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/combo/dhtmlxcombo.css?version=<html:fileCacheVersion/>" />
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/jQueryAlert/jquery.alerts.css?version=<html:fileCacheVersion/>" />
 
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


<script type="text/javascript" src="<html:rootPath/>js/jQueryAlert/jquery.alerts.js?version=<html:fileCacheVersion/>"></script>
<script type="text/javascript" src="<html:rootPath/>js/grid/dhtmlxcommon.js?version=<html:fileCacheVersion/>"></script>
<script type="text/javascript" src="<html:rootPath/>js/grid/dhtmlxgrid.js?version=<html:fileCacheVersion/>"></script>
<script type="text/javascript" src="<html:rootPath/>js/grid/reSkinPagination.js?version=<html:fileCacheVersion/>"></script>
<script type="text/javascript" src="<html:rootPath/>js/grid/dhtmlxgridcell.js?version=<html:fileCacheVersion/>"></script>
<script type="text/javascript" src="<html:rootPath/>js/grid/ext/dhtmlxgrid_excell_sub_row.js?version=<html:fileCacheVersion/>"></script>
<script type="text/javascript" src="<html:rootPath/>js/grid/ext/dhtmlxgrid_filter.js?version=<html:fileCacheVersion/>"></script>
<script type="text/javascript" src="<html:rootPath/>js/grid/ext/dhtmlxgrid_srnd.js?version=<html:fileCacheVersion/>"></script>
<script type="text/javascript" src="<html:rootPath/>js/grid/ext/dhtmlxgrid_pgn.js?version=<html:fileCacheVersion/>"></script>
<script type="text/javascript" src="<html:rootPath/>js/grid/ext/dhtmlxgrid_mcol.js?version=<html:fileCacheVersion/>"></script>
<script type="text/javascript" src="<html:rootPath/>js/grid/ext/dhtmlxgrid_hmenu.js?version=<html:fileCacheVersion/>"></script>
<script type="text/javascript" src="<html:rootPath/>js/combo/dhtmlxcombo.js?version=<html:fileCacheVersion/>"></script>
<script type="text/javascript" src="<html:rootPath/>js/combo/dhtmlxcombo_whp.js?version=<html:fileCacheVersion/>"></script>
<!-- The below JS files override or extend one or more functions in their non-'prft' counter parts... -->
<script type="text/javascript" src="<html:rootPath/>js/grid/prft/prft_dhtmlxgrid.js?version=<html:fileCacheVersion/>"></script>
<script type="text/javascript" src="<html:rootPath/>js/grid/prft/prft_dhtmlxgrid_mcol.js?version=<html:fileCacheVersion/>"></script>
<script type="text/javascript" src="<html:rootPath/>js/grid/prft/prft_dhtmlxgrid_ssw.js?version=<html:fileCacheVersion/>"></script>
<script type="text/javascript" src="<html:rootPath/>js/grid/prft/prft_dhtmlxgrid_hmenu.js?version=<html:fileCacheVersion/>"></script>
<script type="text/javascript" src="<html:rootPath/>js/grid/prft/prft_dhtmlxgrid_excell_sub_row.js?version=<html:fileCacheVersion/>"></script>
<script type="text/javascript"><%@ include file="../../js/util.js"%></script>
    
<%@ page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>

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

function resetGridSetting(gridId, callback){
	jConfirm("<spring:message code='message.resetGridSttingConfirmation'/>", "", function(confirmResult) {
		if(confirmResult){
			doAjaxSyncGet("${resetCustomizedGridSettingURL}&gridId="+gridId,callback);
		}
	});
}

function saveGridQueryParams(gridId,saveKeyValues,callBack){
	jQuery.post("<portlet:resourceURL id='saveGridQueryParams'></portlet:resourceURL>", 
			{
				queryParams: saveKeyValues, 
				gridId: gridId 
			},
			callBack
		);
};

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
	colsHidden =  new String(obj.getColsHidden());
	colsWidth = new String(obj.cellWidthPX);
	if(obj.cellWidthType=='px')
		colsWidth = new String(obj.cellWidthPX);
	else
		colsWidth = new String(obj.cellWidthPC);
}

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
			element.title = element.textContent || element.innerText;
			}
		i++;
	}
})
</script>