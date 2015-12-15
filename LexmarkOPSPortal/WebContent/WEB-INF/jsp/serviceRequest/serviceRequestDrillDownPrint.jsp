<%@ include file="/WEB-INF/jsp/include.jsp"%>
<%@ include file="/WEB-INF/jsp/includeGrid.jsp"%>

<div id="div_top_print" style="width:600;float:left">
	<a id="topPrint" href="javascript:void(0)" onClick="doPrint();">
		<img src="/LexmarkOPSPortal/images/transparent.png" class="ui_icon_sprite print-icon" style="cursor:pointer" title="<spring:message code='button.clickToPrint'/>" />
		<span style="cursor:pointer"><spring:message code='button.clickToPrint'/></span>
	</a>
</div><br>
<div id="div_heeadPrint" style="width:600;"></div>
<div id="div_problemInfoPrint" style="width:600;"></div>
<div id="div_deviceInfoPrint" style="width:600;"></div>
<div id="div_statusPrint" style="width:600;"></div>
<div id="div_contactInfoPrint" style="width:600;"></div>
<div id="div_notificationsPrint" style="width:600;"></div>
<div id="div_btm_print" style="width:600;float:left">
	<a id="btmPrint" href="javascript:void(0)" onClick="doPrint();">
		<img src="/LexmarkOPSPortal/images/transparent.png" class="ui_icon_sprite print-icon" style="cursor:pointer" title="<spring:message code='button.clickToPrint'/>" />
		<span style="cursor:pointer"><spring:message code='button.clickToPrint'/></span>
	</a>
</div>
<style type="text/css">
body{
	align: left;
	text-align:left;
}
</style>
<script type="text/javascript">
//--------------------------------Head----------------------------------------
	window.document.getElementById("div_heeadPrint").innerHTML = window.opener.window.document.getElementById("div_dillDownHead").innerHTML;
//-----------------------------SR Associated Table--------------------------------------
//  SR Associated grid
	var gridHeaders = ["Request Number", "Date Opened", "Serial Number", "Machine Type Model", "Address Name" ];
	var imageStrs = ["img_tab_associatedTicketsGrid"];
	printTables = ["div_problemInfoPrintTable"];
	parent_SRAssociatedGrid= window.opener.SRAssociatedGrid;
	if(parent_SRAssociatedGrid.getRowsNum()==0){
		parent_SRAssociatedGrid = null;
	}
	gridNames = [parent_SRAssociatedGrid];
	initialPrintSection("div_problemInfoPrint","tab_problemInfo",gridHeaders,gridNames,imageStrs,printTables);
	
	

	
//------------------------------SR history table config------------------------------
//  SR history grid
	//imageStrs = ["img_tab_historyGrid"];
	printTables = ["div_deviceInfoPrintTable"];
	//Change done for CI-7 Defect#8217
	parent_SRHistoryGrid= window.opener.requestListGrid;
	if(parent_SRHistoryGrid.getRowsNum()==0){
		parent_SRHistoryGrid = null;
	}
	gridNames = [parent_SRHistoryGrid];
	initialPrintSection("div_deviceInfoPrint","tab_deviceInfo",gridHeaders,gridNames,imageStrs,printTables);

	
//------------------------------Status table config-------------------------------------
//  Status grid
//  Technician grid
//  In process orders grid
//  Shiped orders grid
//  In Progcess Return(s) grid
//  Return Shipments grid
//  Return(s) - Tracking Number Not Available grid
	gridHeaders = ["Date","Status","Description"];
	gridNames = new Array();
	parent_SRStatusGrid = window.opener.SRStatusGrid;
	parent_SRTechnicianGrid = window.opener.SRTechnicianGrid;
	parent_SRInProgressGrid = window.opener.SRInProgressGrid;
	gridNames[0]=parent_SRStatusGrid;
	gridNames[1]=parent_SRTechnicianGrid;
	imageStrs = new Array();
	imageStrs[0]="img_tab_statusGrid";
	imageStrs[1]="img_tab_technicianGrid";
	printTables = new Array();
	printTables[0]="div_statusPrintTable";
	printTables[1]="div_technicianPrintTable";
	
	for(i=1;i<window.opener.shipmentLength;i++){
		eval("parent_SRShipmentGrid = window.opener.SRShipmentGrid"+i.toString());  
		gridNames[(i+1)] = parent_SRShipmentGrid;
		imageStrs[(i+1)] =  "img_tab_shipmentGrid"+i;
		printTables[(i+1)] =  "div_shipmentPrintTable"+i;
	}
	gridNames[(i+1)] = parent_SRInProgressGrid;
	imageStrs[(i+1)] = "img_tab_inprocessGrid";
	printTables[(i+1)] = "div_inprocessPrintTable";
	
	gridNames[(i+2)] = window.opener.inProgressReturnNoTrackNumGrid;
	imageStrs[(i+2)] = "img_tab_inProgressReturnNoTrackNumGrid";
	printTables[(i+2)] = "div_inProgressReturnNoTrackNumPrintTable";

	gridNames[(i+3)] = window.opener.deliveredReturnNoTrackNumGrid;
	imageStrs[(i+3)] = "img_tab_deliveredReturnNoTrackNumGrid";
	printTables[(i+3)] = "div_deliveredReturnNoTrackNumPrintTable";
	for(i=1;i<window.opener.returnShipmentLength;i++){
		eval("parent_SRShipmentGrid = window.opener.SRReturnShipmentGrid"+i.toString());  
		gridNames[(i+3+window.opener.shipmentLength)] = parent_SRShipmentGrid;
		imageStrs[(i+3+window.opener.shipmentLength)] = "img_tab_returnShipmentGrid"+i;
		printTables[(i+3+window.opener.shipmentLength)] =  "div_returnShipmentPrintTable"+i;
	}

	initialPrintSection("div_statusPrint","tab_status",gridHeaders,gridNames,imageStrs,printTables);


//------------------------------ContactInfo table config------------------------------
	initialPrintSection("div_contactInfoPrint","tab_contactInfo",null,null,null,null);

//------------------------------ContactInfo table config------------------------------
//  ContactInfo grid	
	gridHeaders = ["Date","Description"];
	parent_SRNotificationsGrid = window.opener.SRNotificationsGrid;
	printTables = ["div_notificationsPrintTable"];
	gridNames = [parent_SRNotificationsGrid];
	initialPrintSection("div_notificationsPrint","tab_notifications",gridHeaders,gridNames,null,printTables);

	function initialPrintSection(divTablePosition,tableId,gridHeaders,gridNames,imageStrs,printTables){
		try{
			window.document.getElementById(divTablePosition).innerHTML = window.opener.window.document.getElementById(divTablePosition).innerHTML;
			window.document.getElementById(tableId).style.width= "99%";
			window.document.getElementById(tableId).style.align= "left";
		}catch(e){return;}
		if(divTablePosition =="div_problemInfoPrint"){
			window.document.getElementById(imageStrs[0]).parentNode.style.display="none";
			window.document.getElementById("tab_associatedTicketsGrid").style.display="none";
		}else if(divTablePosition=="div_deviceInfoPrint"){
			window.document.getElementById(imageStrs[0]).parentNode.style.display="none";
			window.document.getElementById("tab_historyGrid").style.display="none";
		}else if(divTablePosition =="div_statusPrint"){
				for(var m=0;m<imageStrs.length;m++){
					try{
						window.document.getElementById(imageStrs[m]).style.display="none";
					}catch(e){continue;}
				}
		}else{
			if(imageStrs){
				for(var m=0;m<imageStrs.length;m++){
					window.document.getElementById(imageStrs[m]).style.display="none";
				}
			}
		}
		if(gridHeaders!=null){
			for(var i=0;i<printTables.length;i++){
				if(imageStrs==null){
					imageStr = "";
				}else{
					imageStr = imageStrs[i];
				}
				if(divTablePosition=="div_statusPrint"&&i>=2){
					gridHeaders = ["Part Number","Part Description","Serial Number"];
				}
				if(gridNames[i]!=null){
					convertGridToTable(gridNames[i],gridHeaders,imageStr,printTables[i]) ;	
				}
			}
		}
	}
	
	function convertGridToTable(grid,gridHeaders,idImag,idPosition){
		var pageSize = 5;
		var start = (Number(grid.currentPage)-1)*pageSize;
		var rowNumber = grid.getRowsNum();
		var totalPages = Math.ceil(Number(rowNumber)/pageSize);
		var printRecordNumber;
		if(rowNumber%pageSize > 0 && grid.currentPage==totalPages){
			printRecordNumber = rowNumber%pageSize;
			}else{
				printRecordNumber = pageSize;
			}
		tab_Content = "<table border='1px'  cellspacing='0px' width='100%'style='text-align:left;' ><tr>";
		for(m=0;m<grid.getColumnsNum();m++){
			if(!grid.isColumnHidden(m))
				tab_Content = tab_Content + "<td><h5>"+grid.getHeaderCol(m) + "</h5></td>" ;
			}
		tab_Content = tab_Content + "</tr>";
		if(rowNumber!=0){
			for(i=start;i<(start+printRecordNumber);i++){
				str = "<tr>";
				for(j = 0;j<grid.getColumnsNum();j++){
					if(!grid.isColumnHidden(j)){
						str = str + "<td>"+grid.cellByIndex(i,j).cell.innerHTML+"</td>";
					}
				}
				str = str + "</tr>";
				tab_Content = tab_Content + str;
			}
		}
		tab_Content = tab_Content + "</table>";
		window.document.getElementById(idPosition).innerHTML =  tab_Content;
	};
	function beforePrint(){
		pagesetup_null();
		document.getElementById('btn_print').style.display='none';
		document.getElementById('btn_cancel').style.display='none';
	 }
 	function afterPrint()
	{
 		document.getElementById('btn_print').style.display='';
		document.getElementById('btn_cancel').style.display='';
		window.close();
     }
 	function pagesetup_null(){                
        var     hkey_root,hkey_path,hkey_key;
        hkey_root="HKEY_CURRENT_USER";
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
 		window.opener.callOmnitureAction('Service Request', 'Service request Drilldown Print');
  		window.document.getElementById("div_top_print").style.display='none';
  		window.document.getElementById("div_btm_print").style.display='none';
  		window.print();
  		window.close();
  	};
  	var tags = document.getElementsByTagName("A");
	for(var i= 0 ;i <tags.length ; i++){
	 	if(tags[i].id!="topPrint" && tags[i].id!="btmPrint"){
	 		tags[i].removeAttribute("href");
			tags[i].removeAttribute("target");
			tags[i].onclick = null;
	 	 }
	};
</script>
<script type="text/javascript">
//---- Ominture script 

     portletName = "Service Request drilldown print";
     window.opener.addPortlet(portletName);
     pageTitle="Service request drilldown print";

</script>