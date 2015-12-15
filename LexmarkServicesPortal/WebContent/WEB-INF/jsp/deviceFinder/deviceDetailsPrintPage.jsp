<%@ include file="/WEB-INF/jsp/include.jsp"%>
<jsp:include page="/WEB-INF/jsp/includeGrid.jsp"/>
<%@ include file="/WEB-INF/jsp/favoriteAsset.js.jsp"%>
<%@ include file="/WEB-INF/jsp/includeDatePicker.jsp"%>
<%@ taglib prefix="util" uri="/WEB-INF/tld/lexmark-util.tld" %>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/jquery/jquery-ui.css"/>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/mps.css"/>
<!--[if IE 7]>
	<style type="text/css"><%@ include file="/WEB-INF/css/mps_ie7.css" %></style>
<![endif]-->

<!--[if lt IE 9]>
	<style type="text/css">
		li{
		list-style-type:none;
		}
		.dhx_page{
		width:24px!important;
		}
		ul.form li{
		padding-left:0 !important;
		list-style-type:none!important;
		}
		ul.form li.fav{
		padding-left:200px !important;
		}
		.columnsTwo{
		width:40% !important;
		}
		#pageCountsDiv table .obj{
		margin:50px 0 0 0;
		}
		ul.form li label{
		width:200px!important;
		margin-left:auto!important;
		}
		.columnsOne,#printHistHeading{
		clear:both!important;
		}
		#divDeviceBody{
		display:block !important;
		width:100% !important;
		}
	</style>

	<![endif]-->

<style type="text/css"><%@ include file="/WEB-INF/css/jquery/jquery-ui.css" %></style>
<script type="text/javascript"><%@ include file="../../../js/grid/ext/dhtmlxgrid_drag.js"%></script>
<style type="text/css" >
	td {padding:0 5px 0 5px;}
	th {padding:0 5px 0 5px;}
</style>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/in-styles.css?version=<html:fileCacheVersion/>" />
<div id="content-wrapper-inner">
		<div class="journal-content-article" >
      <h1><span id="changeRequest"><spring:message code="deviceFinder.title.deviceFinder"/></span></h1>
      <h2 class="step"><spring:message code="deviceDetail.label.deviceDetail" /></h2> 
      
    </div>
    
       <div class="main-wrap">
	      <div class="content">
		<div>
								<a onClick="javascript:doPrint();" id="topPrintLink">
									<img src="/LexmarkServicesPortal/images/transparent.png" class="ui_icon_sprite print-icon cursor-pointer" title="<spring:message code='requestInfo.link.printThisPage'/>" />
									<span class="cursor-pointer"><spring:message code='requestInfo.link.printThisPage'/></span>
								</a>
			</div>
			<h3 class="pageTitle"><spring:message code="deviceFinder.listHeader.model"></spring:message></h3>
				<div id="modelPrint"> </div>
			<h3 class="pageTitle"><spring:message code="deviceDetail.label.deviceInformation" /></h3>
				<div id="divDeviceBody"> </div>
      			


		
		
<%-- <spring:message code="deviceDetail.label.requestHistory" /> --%>
<span id="printHistHeading"></span>
<table width="100%" >

	<tr>
		<td align="center" >
			<div id="device_list_print_grid" class="div-style24"></div>
		</td>
	</tr>
	
</table>
<br/>
<div>
						<a id="btmPrint" onClick="javascript:doPrint();">
							<img src="/LexmarkServicesPortal/images/transparent.png" class="ui_icon_sprite print-icon cursor-pointer" title="<spring:message code='requestInfo.link.printThisPage'/>" />
							<span class="cursor-pointer"><spring:message code='requestInfo.link.printThisPage'/></span>
						</a>
				</div>
	</div>
	</div>
</div>

<script type="text/javascript">

	window.document.getElementById("divDeviceBody").innerHTML = window.opener.window.document.getElementById("tabs-1").innerHTML;
	window.document.getElementById("modelPrint").innerHTML = window.opener.window.document.getElementById("modelPrint").innerHTML;

	window.document.getElementById("printHistHeading").innerHTML = window.opener.window.document.getElementById("history_heading").innerHTML;


</script>
<script type="text/javascript">
if(window.opener.gridObj){
if(window.opener.gridObj.getRowId(0)==null){
		var pageSize = 20;
		var parentgrid = window.opener.gridObj;
	
		var gridContent = "<br><table border='1px'  cellspacing='0px' width='100%'><tr>";
			
			for(m=0;m<parentgrid.getColumnsNum();m++){
				if(!(parentgrid.isColumnHidden(m))){
				gridContent = gridContent+"<th align='left'>"+parentgrid.getHeaderCol(m)+"</th>";
				}
			}
				gridContent +="</tr>";
			
		
		gridContent = gridContent+"</table>";
		document.getElementById("device_list_print_grid").innerHTML = gridContent;
	
	
        
        var tags=document.getElementsByTagName("A");
		for ( var i=0; i<tags.length ; i++ ){
			if(tags[i].id!="topPrintLink" && tags[i].id!="btmPrint"){
				tags[i].removeAttribute("href");
				tags[i].removeAttribute("target");
				tags[i].onclick = null;
			}
		}
}
else{
	var pageSize = 20;
	var parentgrid = window.opener.gridObj;
	
	var start = (Number(parentgrid.currentPage)-1)*pageSize;
	var rowNumber = parentgrid.getRowsNum();
	var totalpages = Math.ceil(Number(parentgrid.getRowsNum())/pageSize);
	var printRecordNumber;
	var a_state = parentgrid.getSortingState();
	
	if (rowNumber%pageSize > 0 && parentgrid.currentPage == totalpages) {
		printRecordNumber = rowNumber%pageSize;
	} else {
		printRecordNumber = pageSize;
	}
	var gridContent = "<br><table border='1px'  cellspacing='0px' width='100%'><tr>";

		for(m=0;m<parentgrid.getColumnsNum();m++){
			if(!(parentgrid.isColumnHidden(m))){
			gridContent = gridContent+"<th align='left'>"+parentgrid.getHeaderCol(m)+"</th>";
			}
		}
			gridContent +="</tr>";
		
		for(n=start;n<(start+printRecordNumber);n++){
		
			gridContent += "<tr>";
			for(i = 0;i<parentgrid.getColumnsNum();i++){
				if(!(parentgrid.isColumnHidden(i))){
				gridContent += "<td>";
				gridContent += parentgrid.cellByIndex(n,i).cell.innerHTML;
				gridContent += "</td>";
				}
			}
			gridContent += "</tr>";
		}
	gridContent = gridContent+"</table>";
	document.getElementById("device_list_print_grid").innerHTML = gridContent;
	
    document.getElementById("currentPage").innerHTML=parentgrid.currentPage;
    document.getElementById("totalPage").innerHTML=totalpages;
    
    var tags=document.getElementsByTagName("A");
	for ( var i=0; i<tags.length ; i++ ){
		if(tags[i].id!="topPrintLink" && tags[i].id!="btmPrint"){
			tags[i].removeAttribute("href");
			tags[i].removeAttribute("target");
			tags[i].onclick = null;
		}
	}
	
}
}
function doPrint(){
    		//window.opener.callOmnitureAction('Device Finder', 'Device Finder Print Window');
    		window.document.getElementById("topPrintLink").style.display='none';
    		window.document.getElementById("btmPrint").style.display='none';
    		window.print();
    		window.close();
    	};
</script>
