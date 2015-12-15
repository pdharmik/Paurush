<%@ include file="/WEB-INF/jsp/include.jsp"%>
<%@ include file="/WEB-INF/jsp/includeGrid.jsp"%>
<%@ include file="/WEB-INF/jsp/favoriteAsset.js.jsp"%>
<%@ include file="/WEB-INF/jsp/includeDatePicker.jsp"%>
<%@ taglib prefix="util" uri="/WEB-INF/tld/lexmark-util.tld" %>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/jquery/jquery-ui.css"/>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/mps.css"/>
<!--[if IE 7]>
	<style type="text/css"><%@ include file="/WEB-INF/css/mps_ie7.css" %></style>
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
      <h2 class="step"><spring:message code="deviceDetail.label.requestHistory" /></h2> 
      
    </div>
    
       <div class="main-wrap">
	      <div class="content">
		<div>
								<a onClick="javascript:doPrint();" id="topPrintLink">
									<img src="/LexmarkServicesPortal/images/transparent.png" class="ui_icon_sprite print-icon cursor-pointer" title="<spring:message code='requestInfo.link.printThisPage'/>" />
									<span class="cursor-pointer"><spring:message code='requestInfo.link.printThisPage'/></span>
								</a>
			</div>
			
      			


		
		
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

	window.document.getElementById("printHistHeading").innerHTML = window.opener.window.document.getElementById("history_heading").innerHTML;


</script>
<script type="text/javascript">
	
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
var extraCols=parseInt(parentgrid.getColumnsNum()/10);
var remCols=parentgrid.getColumnsNum()%10;

if(remCols>0)
	extraCols++;

var startCol=0;
var endCol=10;
var gridContent = "";
for(k=0;k<extraCols;k++)
{
	
	gridContent += "<br><table border='1px'  cellspacing='0px' width='100%'><tr>";

		for(m=startCol;m<endCol;m++){
			
			gridContent = gridContent+"<th align='left'>"+parentgrid.getHeaderCol(m)+"</th>";
			
		}
		gridContent +="</tr>";
		
		for(n=start;n<(start+printRecordNumber);n++){
		
			gridContent += "<tr>";
			for(i = startCol;i<endCol;i++){
			
				gridContent += "<td>";
				gridContent += parentgrid.cellByIndex(n,i).cell.innerHTML;
				gridContent += "</td>";
			}
			gridContent += "</tr>";
		}
	gridContent = gridContent+"</table>";
	startCol=endCol;
	if((endCol+10)<=parentgrid.getColumnsNum())
		endCol=endCol+10;
	else
		endCol+=remCols;
}


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

function doPrint(){
	window.opener.callOmnitureAction('Device Finder', 'Device Finder Print Window');
	window.document.getElementById("topPrintLink").style.display='none';
	window.document.getElementById("btmPrint").style.display='none';
	window.print();
	window.close();
};
</script>
