<%@ include file="/WEB-INF/jsp/include.jsp"%>
<%@ include file="/WEB-INF/jsp/includeGrid.jsp"%>
<script type="text/javascript" src='<html:rootPath/>js/grid/ext/dhtmlxgrid_drag.js'></script>
<style type="text/css" >
	td {padding:0 5px 0 5px;}
	th {padding:0 5px 0 5px;}
</style>
<table width="100%">
	<tr id="topPrint">
		<td align="left">
			&nbsp;&nbsp;<a href="javascript:void(0)" onClick="doPrint();" id="topPrintLink"><img src="/LexmarkOPSPortal/images/transparent.png" class="ui_icon_sprite print-icon" style="cursor:pointer" title="<spring:message code='button.clickToPrint'/>" /><span style="cursor:pointer"><spring:message code='button.clickToPrint'/></span></a>
		</td>
	</tr>
	<tr>
		<td align="center" >
			<div id="divPrintGridbox" style="width: 100%; height: 100%; background-color: white;"></div><br/>
		</td>
	</tr>
	<tr>
		<td align="left">
			<spring:message code='page.currentPage'/>&nbsp;<label id="currentPage"></label>&nbsp;<spring:message code='page.of'/>&nbsp;<spring:message code='page.totalPage'/>&nbsp;<label id="totalPage"></label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br>
		   <a id="btmPrint" href="javascript:void(0)" onClick="doPrint();"><img src="/LexmarkOPSPortal/images/transparent.png" class="ui_icon_sprite print-icon" style="cursor:pointer" title="<spring:message code='button.clickToPrint'/>" /><span style="cursor:pointer"><spring:message code='button.clickToPrint'/></span></a>
		</td>
	</tr>
</table>

<script type="text/javascript">
	var pageSize = 20;
	var parentgrid = window.opener.meterReadGrid;
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

	for(m=1;m<parentgrid.getColumnsNum();m++){
	
		gridContent = gridContent+"<th align='left' noWrap='true'>"+parentgrid.getHeaderCol(m)+"</th>";
		}
		gridContent +="</tr>";
	
	for(n=start;n<(start+printRecordNumber);n++){
	
		gridContent += "<tr>";
		for(i = 1;i<parentgrid.getColumnsNum();i++){
		
			gridContent += "<td>";
			gridContent += parentgrid.cellByIndex(n,i).cell.innerHTML;
			gridContent += "</td>";
		}
		gridContent += "</tr>";
	}
	gridContent = gridContent+"</table>";
	document.getElementById("divPrintGridbox").innerHTML = gridContent;
	/*
    var meterReadPrintGrid = new dhtmlXGridObject('divPrintGridbox');
	meterReadPrintGrid.setImagePath("<html:imagesPath/>/gridImgs/");
	meterReadPrintGrid.setHeader("<spring:message code='meterRead.listHeader.deviceFields'/>");
	meterReadPrintGrid.setColAlign("left,left,left,left,left,left,left,left,left,left,left,left,left");
	meterReadPrintGrid.setInitWidths("20,60,60,80,80,80,110,60,60,60,60,0,40");
	meterReadPrintGrid.setColTypes("ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro");
	meterReadPrintGrid.setColSorting("na,str,str,str,str,str,int,str,na,str,na,na,na");
	meterReadPrintGrid.enableAutoWidth(true);
	meterReadPrintGrid.enableAutoHeight(true);
	meterReadPrintGrid.enableMultiline(true);
	meterReadPrintGrid.setSizes();
	meterReadPrintGrid.init();
	meterReadPrintGrid.setSortImgState(true, a_state[0], a_state[1]);
	meterReadPrintGrid.setSkin("dhx_skyblue");
	meterReadPrintGrid.enableMultiline(true);
	meterReadPrintGrid.setColumnHidden(0,true);
	meterReadPrintGrid.setColumnHidden(8,true);
	meterReadPrintGrid.setColumnHidden(10,true);
	meterReadPrintGrid.setColumnHidden(11,true);
	meterReadPrintGrid.setColumnHidden(12,true);
	for(i=1; i<=printRecordNumber; i++){
	   	meterReadPrintGrid.moveRowTo(start + printRecordNumber - i,null,"copy","sibling",parentgrid,meterReadPrintGrid);
	}*/
	document.getElementById("currentPage").innerHTML=parentgrid.currentPage;
    document.getElementById("totalPage").innerHTML=totalpages;

    function doPrint(){
 	   window.opener.callOmnitureAction('Page Counts', 'Page Count List Print');
		window.document.getElementById("topPrint").style.display='none';
		window.document.getElementById("btmPrint").style.display='none';
		window.print();
		window.close();
	};
	var tags = document.getElementsByTagName("A");
	for(var i= 0 ;i <tags.length ; i++){
	 	if(tags[i].id!="topPrintLink" && tags[i].id!="btmPrint"){
	 		tags[i].removeAttribute("href");
			tags[i].removeAttribute("target");
			tags[i].onclick = null;
	 	 }
	};
</script>