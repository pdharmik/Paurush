<%@page import="com.lexmark.services.util.LexmarkSPOmnitureConstants"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="html" tagdir="/WEB-INF/tags/html" %>
<%@ include file="/WEB-INF/jsp/includeGrid.jsp"%>
<style type="text/css" >
	td {padding:0 5px 0 5px;}
	th {padding:0 5px 0 5px;}
	@media print{
.portletBlock , .infoBox, .columnInner, .rounded, .shadow{background:none!important;background-color:white!important;}
}
</style>
<!-- CSS include -->
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/mps.css"/>
<!--[if IE 7]>
	<style type="text/css"><%@ include file="/WEB-INF/css/mps_ie7.css" %></style>
<![endif]-->
<!-- CSS include -->
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/in-styles.css?version=<html:fileCacheVersion/>" />
	<div id="content-wrapper-inner">
		
       <div class="main-wrap">
	      <div class="content">
		<div>
								<a onClick="javascript:doPrint();" id="topPrintLink">
									<img src="/LexmarkServicesPortal/images/transparent.png" class="ui_icon_sprite print-icon cursor-pointer" title="<spring:message code='requestInfo.link.printThisPage'/>" />
									<span class="cursor-pointer"><spring:message code='requestInfo.link.printThisPage'/></span>
								</a>
			</div>
			  <div id="divPrintBodyHead" class="journal-content-article"> </div>
            <div id="divPrintBodyTop"> </div>
<div>
				<a id="btmPrint" onClick="javascript:doPrint();"> <img
					src="/LexmarkServicesPortal/images/transparent.png" class="ui_icon_sprite print-icon cursor-pointer"
					title="<spring:message code='requestInfo.link.printThisPage'/>" />
					<span class="cursor-pointer"><spring:message
							code='requestInfo.link.printThisPage' /></span>
				</a>
			</div>
		
		
		
	</div>
	</div>
</div>
<script type="text/javascript">
/* if(window.opener.pageCountsGrid.getRowsNum()>0){
var parentgrid = window.opener.pageCountsGrid;

var pageSize = 21;

var start = (Number(parentgrid.currentPage)-1)*pageSize;;
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
} */

</script>
<script type="text/javascript">
window.document.getElementById("divPrintBodyHead").innerHTML = window.opener.window.document.getElementById("emailPrintWraperHead").innerHTML;
window.document.getElementById("divPrintBodyTop").innerHTML = window.opener.window.document.getElementById("emailPrintWraperTop").innerHTML;
//window.document.getElementById("divPrintBodyBottom").innerHTML = window.opener.window.document.getElementById("emailPrintWraperBottom").innerHTML;
 
function doPrint(){
	window.opener.callOmnitureAction('<%=LexmarkSPOmnitureConstants.CHANGEREQUESTDETAILS%>','<%=LexmarkSPOmnitureConstants.CHANGEREQUESTPRINT%>');
    		
    		window.document.getElementById("topPrintLink").style.display='none';
    		//window.document.getElementById("btmPrint").style.display='none';
    		window.print();
    		window.close();
    	};
</script>
