<%@ include file="/WEB-INF/jsp/include.jsp"%>
<%@ include file="/WEB-INF/jsp/includeGrid.jsp"%>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/in-styles.css?version=<html:fileCacheVersion/>" />
<div id="div_top_print" class="div-btm-print">
	<a id="topPrint" href="javascript:void(0)" onClick="doPrint();">
		<img src="<html:imagesPath/>transparent.png" class="ui-icon print-icon cursor-pointer" height="23px" width="23px" title="<spring:message code='button.clickToPrint'/>" />
		<span class="cursor-pointer"><spring:message code='button.clickToPrint'/></span>
	</a>
</div><br><br><br>
<div id="div_heeadPrint" class="width-600"></div>
<div id="div_claimInformationPrint" class="width-600"></div>
<div id="div_deviceInformationPrint" class="width-600"></div>
<div id="div_customerContactInformationPrint" class="width-600"></div>
<div id="div_problemInformationPrint" class="width-600"></div>
<div id="div_technicianInformationPrint" class="width-600"></div>
<div id="div_debriefInformationPrint" class="width-600"></div>
<div id="div_requestedPartsPrint" class="width-600"></div>
<div id="div_additionalPaymentRequestsPrint" class="width-600"></div>
<div id="div_notesPrint" class="width-600"><div id="notes_Table"></div></div>
<div id="div_btm_print" class="width-600 float-left" >
	<a id="btmPrint" href="javascript:void(0)" onClick="doPrint();">
		<img src="<html:imagesPath/>transparent.png" class="ui-icon print-icon cursor-pointer" height="23px" width="23px" title="<spring:message code='button.clickToPrint'/>" />
		<span class="cursor-pointer"><spring:message code='button.clickToPrint'/></span>
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
//	window.document.getElementById("div_heeadPrint").innerHTML = window.opener.window.document.getElementById("div_dillDownHead").innerHTML;

//--------------------------------div_claimInformationPrint----------------------------------------
	window.document.getElementById("div_claimInformationPrint").innerHTML = window.opener.window.document.getElementById("claim_information").innerHTML;

//--------------------------------div_deviceInformationPrint----------------------------------------
	window.document.getElementById("div_deviceInformationPrint").innerHTML = window.opener.window.document.getElementById("device_information").innerHTML;

//--------------------------------div_customerContactInformationPrint----------------------------------------
	window.document.getElementById("div_customerContactInformationPrint").innerHTML = window.opener.window.document.getElementById("customer_contact_information").innerHTML;

//--------------------------------div_problemInformationPrint----------------------------------------
	window.document.getElementById("div_problemInformationPrint").innerHTML = window.opener.window.document.getElementById("problem_information").innerHTML;

//--------------------------------div_technicianInformationPrint----------------------------------------
	window.document.getElementById("div_technicianInformationPrint").innerHTML = window.opener.window.document.getElementById("technician_information").innerHTML;


	
	var repairCompleteFlag = window.opener.window.document.getElementById("repairCompleteFlag").value;
	if(repairCompleteFlag =="true"){
		//--------------------------------div_debriefInformationPrint----------------------------------------
		window.document.getElementById("div_debriefInformationPrint").innerHTML = window.opener.window.document.getElementById("debrief_information").innerHTML;
		
		//--------------------------------div_requestedPartsPrint----------------------------------------
		gridHeaders=["Date Ordered:", "Qty", "Part Number","Part Name", "Part Status", "Error Code1","Error Code2","Return Required","Return Carrier","Return Tracking Number#"];
		parent_CTYVRequestedParts = window.opener.gridCTYVRequestsPartsListGrid;
		initialPrintSection("div_requestedPartsPrint",gridHeaders,parent_CTYVRequestedParts,null,"gridCTYVRequestedParts","requested_parts",false,5);	

		//--------------------------------div_additionalPaymentRequestsPrint----------------------------------------		
		gridHeaders=["Payment Type", "Qty", "Unit Price","Amount","Currency","Description"];
		parent_CTYVAdditionPaymentRequest = window.opener.gridCTYVAdditionPaymentRequestGrid;
		initialPrintSection("div_additionalPaymentRequestsPrint",gridHeaders,parent_CTYVAdditionPaymentRequest,null,"gridCTYVAdditionPaymentRequest","additional_payment_requests",false,5);
	}else{
		//--------------------------------div_requestedPartsPrint----------------------------------------
		gridHeaders=["Date Ordered:","Part Number","Part Name",  "Qty", "Return Required"];
		parent_CTYVRequestedParts = window.opener.gridCTYVRequestsPartsListGrid;
		initialPrintSection("div_requestedPartsPrint",gridHeaders,parent_CTYVRequestedParts,null,"gridCTYVRequestedParts","requested_parts",false,5);	
	}


//--------------------------------div_notesPrint----------------------------------------
	gridHeaders=["Date","Auther","Note"];
	parent_CTYVNoteListGrid = window.opener.gridCTYVNoteListGrid;
	initialPrintSection("div_notesPrint",gridHeaders,parent_CTYVNoteListGrid,null,"notes_grid","notes",false,5);

	//divPortletPosition : printpage position
	//gridName : grid which need to convert to table
	//print_16x16Table : after convert to table ,the table's id
	//orgDivPosition : opener page div
	//currentFlag : if need to print all the data then currentFlag = false ,just need to print current page the currentFlag = true
	function initialPrintSection(divPortletPosition,gridHeaders,gridName,imageStrs,printTable,orgDivPosition,currentFlag,pageSize,orgGrid){
		try{
			window.document.getElementById(divPortletPosition).innerHTML = window.opener.window.document.getElementById(orgDivPosition).innerHTML;
		}catch(e){return;}
		window.document.getElementById(printTable).style.display = "none";
		window.document.getElementById(printTable).nextSibling.style.display = "none";
		convertGridToTable(gridName,gridHeaders,null,printTable,currentFlag,pageSize) ;	
		
		var d = document.getElementById(printTable).firstChild;
		document.getElementById(divPortletPosition).appendChild(d);
	}

	function convertGridToTable(grid,gridHeaders,idImag,idPosition,currentFlag,pageSize){	//currentFlag for print current page or not
		var rowNumber = grid.getRowsNum();
		tab_Content = "<table border='1px'  cellspacing='3px' width='99%'style='text-align:left;'  align= 'center' ><tr>";
		for(m=0;m<grid.getColumnsNum();m++){
			if(!grid.isColumnHidden(m))
				tab_Content = tab_Content + "<td><h5>"+grid.getHeaderCol(m) + "</h5></td>" ;
			}
		tab_Content = tab_Content + "</tr>";
		
		if(currentFlag==true){
			var start = (Number(grid.currentPage)-1)*pageSize;
			var totalPages = Math.ceil(Number(rowNumber)/pageSize);
			var printRecordNumber;
			if(rowNumber%pageSize > 0 && grid.currentPage==totalPages){
				printRecordNumber = rowNumber%pageSize;
				}else{
					printRecordNumber = pageSize;
				}
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
		}else{
			if(rowNumber!=0){
				for(i=0;i<rowNumber;i++){
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
		}
		tab_Content = tab_Content + "</table><br/>";
		window.document.getElementById(idPosition).innerHTML =  tab_Content;
//		return tab_Content;
	};
	

	function doPrint(){
		window.document.getElementById("topPrint").style.display='none';
 		window.document.getElementById("btmPrint").style.display='none';
 		window.print();
 		window.close();
	}
	
</script>