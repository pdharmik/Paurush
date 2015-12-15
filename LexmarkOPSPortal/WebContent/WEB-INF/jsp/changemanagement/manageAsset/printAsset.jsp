<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="html" tagdir="/WEB-INF/tags/html" %>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/mps.css"/>
<!--[if IE 7]>
	<style type="text/css"><%@ include file="/WEB-INF/css/mps_ie7.css" %></style>
<![endif]-->
<style type="text/css"><%@ include file="/WEB-INF/css/grid/dhtmlxgrid_pgn_bricks.css" %></style>
<style>
.move_type1{padding-left:0px!important;}
@media print{
.portletBlock , .infoBox, .columnInner, .rounded, .shadow{background:none!important;background-color:white!important;}
}
</style>
<div id="content-wrapper-inner">
	<div class="journal-content-article">
      <h1><spring:message code="requestInfo.cm.heading.changeRequest"/></h1>
      <h2 class="step"><spring:message code="requestInfo.cm.manageAsset.heading.assets"/></h2> 
      </div>
      
      <!-- Added for CI7 BRD14-02-12 -->
      <div id="accNameAgreeName"></div>
      <!-- END -->
      
       <div class="main-wrap">
	      <div class="content">
	      <div>
		<a href="javascript:void(0)" onClick="doPrint();" id="topPrintLink">
						<img src="/LexmarkOPSPortal/images/transparent.png" class="ui_icon_sprite print-icon" style="cursor:pointer" title="<spring:message code='requestInfo.tooltip.printThisPage'/>" />
						<span style="cursor:pointer"><spring:message code='requestInfo.link.printThisPage'/></span>
					</a>
		
	</div>

		
	
      <div id="divPrintBodyTop"> </div>
      <div class="infoBox columnInner rounded shadow">
			<h4><spring:message code="requestInfo.manageAsset.heading.pageCount"/></h4>				
			<div id="divPrintBodyPageCounts" class="portletBlock infoBox columnInner rounded shadow">	
			</div>
			</div>
      <div id="divPrintBodyBottom"></div>

		<div>
		<a id="btmPrint" href="javascript:void(0)" onClick="doPrint();">
				<img src="/LexmarkOPSPortal/images/transparent.png" class="ui_icon_sprite print-icon" style="cursor:pointer" title="<spring:message code='requestInfo.tooltip.printThisPage'/>" />
				<span style="cursor:pointer"><spring:message code='requestInfo.link.printThisPage'/></span>
			</a>
			</div>
</div>
</div>
</div>
<script type="text/javascript">
if(window.opener.pageCountsGrid.getRowsNum()>0){
var pageSize = 21;
var parentgrid = window.opener.pageCountsGrid;
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
var endCol=3;
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
	if((endCol+3)<=parentgrid.getColumnsNum())
		endCol=endCol+3;
	else
		endCol+=remCols;
}
document.getElementById("divPrintBodyPageCounts").innerHTML = gridContent;
}
else{
window.document.getElementById("divPrintBodyPageCounts").innerHTML = window.opener.window.document.getElementById("div_pageCounts").innerHTML;	
}

window.document.getElementById("divPrintBodyTop").innerHTML = window.opener.window.document.getElementById("emailPrintTop").innerHTML;
window.document.getElementById("divPrintBodyBottom").innerHTML = window.opener.window.document.getElementById("emailPrintBottom").innerHTML;
//document.getElementById("divPrintBodyPageCounts").innerHTML = gridContent;
/* Added for CI7 BRD14-02-12 */
window.document.getElementById("accNameAgreeName").innerHTML = window.opener.window.document.getElementById("accNameAgreeName").innerHTML;
/* END */
        function doPrint(){
    		
    		window.document.getElementById("topPrintLink").style.display='none';
    		window.document.getElementById("btmPrint").style.display='none';
    		window.print();
    		window.close();
    	};
</script>
