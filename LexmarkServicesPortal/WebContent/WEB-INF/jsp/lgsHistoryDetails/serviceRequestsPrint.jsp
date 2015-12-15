<%@ include file="/WEB-INF/jsp/include.jsp"%>
<%@ include file="/WEB-INF/jsp/includeGrid.jsp"%>
<script type="text/javascript" src='<html:rootPath/>js/grid/ext/dhtmlxgrid_drag.js'></script>
<script type="text/javascript" src='<html:rootPath/>js/grid/ext/dhtmlxgrid_excell_sub_row.js'></script>
<style type="text/css" >
	td {padding:0 5px 0 5px;}
	th {padding:0 5px 0 5px;}
	@media print{
.portletBlock , .infoBox, .columnInner, .rounded, .shadow, .portal-popup{background:none!important;background-color:white!important;}
}
</style>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/in-styles.css?version=<html:fileCacheVersion/>" />
<table width="100%">
	<tr id="topPrint">
		<td align="left">
		&nbsp;&nbsp;<a href="javascript:void(0)" onClick="doPrint();" id="topPrintLink"><img src="/LexmarkServicesPortal/images/transparent.png" class="ui_icon_sprite print-icon cursor-pointer" title="<spring:message code='button.clickToPrint'/>" /><span class="cursor-pointer"><spring:message code='button.clickToPrint'/></span></a>
		</td>
	</tr>
	<tr>
		<td align="center" >
			<div id="divPrintGridbox" class="div-style24"></div><br/>
		</td>
	</tr>
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

	if(window.opener.requestListGrid.getRowId(0)==null){
	var pageSize = 20;
	var parentgrid = window.opener.requestListGrid;
	
	var gridContent = "<br><table border='1px'  cellspacing='0px' width='100%'><tr>";

	for(m=0;m<parentgrid.getColumnsNum();m++){
		if(!(parentgrid.isColumnHidden(m))){
		gridContent = gridContent+"<th align='left'>"+parentgrid.getHeaderCol(m)+"</th>";
		}
		}
		gridContent +="</tr>";
	
	
	gridContent = gridContent+"</table>";
	document.getElementById("divPrintGridbox").innerHTML = gridContent;
}
else{
var pageSize = 20;
	var parentgrid = window.opener.requestListGrid;
	var start = (Number(parentgrid.currentPage)-1)*pageSize;
	var rowNumber = parentgrid.getRowsNum();
	var totalpages = Math.ceil(Number(parentgrid.getRowsNum())/pageSize);
	var a_state = parentgrid.getSortingState();
	var printRecordNumber;
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
	document.getElementById("divPrintGridbox").innerHTML = gridContent;
	
	document.getElementById("currentPage").innerHTML=parentgrid.currentPage;
    document.getElementById("totalPage").innerHTML=totalpages;
}
	function beforePrint(){
		pagesetup_null();
		document.getElementById('btn_print').style.display='none';
		document.getElementById('btn_cancel').style.display='none';
	 }
 	function afterPrint()
	{
 		document.getElementById('btn_print').style.display='';
		document.getElementById('btn_cancel').style.display='';
     }
 	function pagesetup_null(){                
        var     hkey_root,hkey_path,hkey_key;
        hkey_root="HKEY_CURRENT_USER"
        hkey_path="\\Software\\Microsoft\\Internet Explorer\\PageSetup\\";
        try{
                    var RegWsh = new ActiveXObject("WScript.Shell");
                    hkey_key="header";
                    RegWsh.RegWrite(hkey_root+hkey_path+hkey_key,"");
                    hkey_key="footer";
                    RegWsh.RegWrite(hkey_root+hkey_path+hkey_key,"");
            }catch(e){}
    }
 	 function doPrint(){
 		window.opener.callOmnitureAction('Service Request', 'Service Request Print');
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