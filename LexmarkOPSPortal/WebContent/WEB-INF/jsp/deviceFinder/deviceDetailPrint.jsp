<%@ include file="/WEB-INF/jsp/include.jsp"%>
<%@ include file="/WEB-INF/jsp/includeGrid.jsp"%>
<script type="text/javascript"><%@ include file="../../../js/grid/ext/dhtmlxgrid_drag.js"%></script>
<table width="100%" height="100%" align='left'>
	<tr id="topPrint">
		<td align="left">
			&nbsp;&nbsp;<a href="javascript:void(0)" onClick="doPrint();" id="topPrintLink"><img src="/LexmarkOPSPortal/images/transparent.png" class="ui_icon_sprite print-icon" style="cursor:pointer" title="<spring:message code='button.clickToPrint'/>" /><span style="cursor:pointer"><spring:message code='button.clickToPrint'/></span></a>
		</td>
	</tr>
	<tr><td><br></td></tr>
	<tr>
		<td>
			<div id="divDeviceBody" style="width: 100%" height="100%"></div>
		</td>
	</tr>
	<tr>
		<td align="left" class="pageSectionTitle" width="100%">
			<span style="blackBold1"><spring:message code="deviceDetail.label.serviceRequestHistory"/></span>
		</td>
	</tr>
	<tr>
		<td align="center" >
			<div id="divPrintGridbox" ></div>
		</td>
	</tr>
	<tr>
		<td align="left">
			<div id="divPagination" name="divPagination"></div><br>
			<a id="btmPrint" href="javascript:void(0)" onClick="doPrint();">
				<img src="/LexmarkOPSPortal/images/transparent.png" class="ui_icon_sprite print-icon" style="cursor:pointer" title="<spring:message code='button.clickToPrint'/>" />
				<span style="cursor:pointer"><spring:message code='button.clickToPrint'/></span>
			</a>
		</td>
	</tr>
</table>
<script type="text/javascript">

	window.document.getElementById("divDeviceBody").innerHTML = window.opener.window.document.getElementById("divDetailBody").innerHTML;
	window.document.getElementById("lab_favorite").style.display = "none";
	
	if(window.document.getElementById("linkRS"))
		window.document.getElementById("linkRS").style.display="none";
	if(window.document.getElementById("linkCP"))
		window.document.getElementById("linkCP").style.display="none";
	if(window.document.getElementById("linkSD"))
		window.document.getElementById("linkSD").style.display="none";
    var gridContent = "";
    var pageSize = 10;
	var parentgrid = window.opener.SRHistoryGrid;
	var start = (Number(parentgrid.currentPage)-1)*pageSize;
	var rowNumber = parentgrid.getRowsNum();
	var totalpages = Math.ceil(Number(parentgrid.getRowsNum())/pageSize);
	var printRecordNumber;
	if (rowNumber%pageSize > 0 && parentgrid.currentPage == totalpages) {
		printRecordNumber = rowNumber%pageSize;
	} else {
		printRecordNumber = pageSize;
	}
		gridContent += "<table border='1px'  cellspacing='0px' width='100%'><tr>";

		for(m=0;m<parentgrid.getColumnsNum();m++){
			if(!parentgrid.isColumnHidden(m))
				gridContent = gridContent+"<th>"+parentgrid.getHeaderCol(m)+"</th>";
			}
			gridContent +="</tr>";
		if(rowNumber!=0){
			gridContent +="<tr>";
			for(n=start;n<(start+printRecordNumber);n++){
				gridContent += "<tr>";
				for(i = 0;i<parentgrid.getColumnsNum();i++){
					if(!parentgrid.isColumnHidden(i)){
						gridContent += "<td align='left' height='25px'>";
						gridContent += parentgrid.cellByIndex(n,i).cell.innerHTML;
						gridContent += "</td>";
					}
				}
				gridContent += "</tr>";
			}
	   		document.getElementById("divPagination").innerHTML = "<spring:message code='page.currentPage'/>&nbsp;"+parentgrid.currentPage
	   								+"&nbsp;<spring:message code='page.of'/>&nbsp;<spring:message code='page.totalPage'/>&nbsp;"+totalpages;
		}else{
			document.getElementById("divPagination").innerHTML = "<spring:message code='deviceDetail.description.norecordsfound'/>";
		}
		gridContent = gridContent+"</table>";
		window.document.getElementById("divPrintGridbox").innerHTML=gridContent;
     
	var tags=window.document.getElementsByTagName("A");
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
	var imgTags=document.getElementsByTagName("img");
	for ( var i=0; i<imgTags.length ; i++ ){
		imgTags[i].onclick = null;
	}
</script>
<script type="text/javascript">
//---- Ominture script 
     portletName = "Device Finder Detail Print";
     addPortlet(portletName);
     pageTitle="Device detail print";
</script>