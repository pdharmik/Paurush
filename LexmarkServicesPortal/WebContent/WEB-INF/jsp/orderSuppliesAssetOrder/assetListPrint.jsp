<%@ include file="/WEB-INF/jsp/include.jsp"%>
<%@ include file="/WEB-INF/jsp/includeGrid.jsp"%>
<script type="text/javascript"><%@ include file="../../../js/grid/ext/dhtmlxgrid_drag.js"%></script>
<style type="text/css" >
	td {padding:0 5px 0 5px;}
	th {padding:0 5px 0 5px;}
</style>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/in-styles.css?version=<html:fileCacheVersion/>" />
<table width="100%">
	<tr id="topPrint">
		<td align="left">
		&nbsp;&nbsp;<a href="javascript:void(0)" onClick="doPrint();" id="topPrintLink">
						<img src="/LexmarkServicesPortal/images/transparent.png" class="ui_icon_sprite print-icon cursor-pointer" title="<spring:message code='button.clickToPrint'/>" />
						<span class="cursor-pointer"><spring:message code='button.clickToPrint'/></span>
					</a>
		</td>
	</tr>
	<tr>
		<td align="center" >
			<div id="device_list_print_grid" class="div-style70"></div>
		</td>
	</tr>
	<tr><td><br></td></tr>
	<tr>
		<td align="left">
			<spring:message code='page.currentPage'/>&nbsp;<label id="currentPage"></label>&nbsp;<spring:message code='page.of'/>&nbsp;<spring:message code='page.totalPage'/>&nbsp;<label id="totalPage"></label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br>
			<a id="btmPrint" href="javascript:void(0)" onClick="doPrint();">
				<img src="/LexmarkServicesPortal/images/transparent.png" class="ui_icon_sprite print-icon cursor-pointer" title="<spring:message code='button.clickToPrint'/>" />
				<span class="cursor-pointer"><spring:message code='button.clickToPrint'/></span>
			</a>
		</td>
	</tr>
</table>
<script type="text/javascript">
		var pageSize = 20;
		var parentgrid = window.opener.assetlistGrid;
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

			for(m=2;m<parentgrid.getColumnsNum();m++){
			
				gridContent = gridContent+"<th align='left' noWrap='true'>"+parentgrid.getHeaderCol(m)+"</th>";
				}
				gridContent +="</tr>";
			
			for(n=start;n<(start+printRecordNumber);n++){
			
				gridContent += "<tr>";
				for(i = 2;i<parentgrid.getColumnsNum();i++){
				
					gridContent += "<td>";
					gridContent += parentgrid.cellByIndex(n,i).cell.innerHTML;
					gridContent += "</td>";
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
		
        function doPrint(){
    		window.document.getElementById("topPrint").style.display='none';
    		window.document.getElementById("btmPrint").style.display='none';
    		window.print();
    		window.close();
    	};
</script>
<script type="text/javascript">
//---- Ominture script 
     portletName = "Device Finder Print";
     addPortlet(portletName);
     pageTitle="Device list print";
</script>