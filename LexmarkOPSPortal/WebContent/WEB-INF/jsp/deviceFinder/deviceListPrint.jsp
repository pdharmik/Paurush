<%@ include file="/WEB-INF/jsp/include.jsp"%>
<%@ include file="/WEB-INF/jsp/includeGrid.jsp"%>
<script type="text/javascript"><%@ include file="../../../js/grid/ext/dhtmlxgrid_drag.js"%></script>
<style type="text/css" >
	td {padding:0 5px 0 5px;}
	th {padding:0 5px 0 5px;}
</style>
<table width="100%">
	<tr id="topPrint">
		<td align="left">
		&nbsp;&nbsp;<a href="javascript:void(0)" onClick="doPrint();" id="topPrintLink">
						<img src="/LexmarkOPSPortal/images/transparent.png" class="ui_icon_sprite print-icon" style="cursor:pointer" title="<spring:message code='button.clickToPrint'/>" />
						<span style="cursor:pointer"><spring:message code='button.clickToPrint'/></span>
					</a>
		</td>
	</tr>
	<tr>
		<td align="center" >
			<div id="device_list_print_grid" style="width:100%; height:100%; background-color: white;"></div>
		</td>
	</tr>
	<tr><td><br></td></tr>
	<tr>
		<td align="left">
			<spring:message code='page.currentPage'/>&nbsp;<label id="currentPage"></label>&nbsp;<spring:message code='page.of'/>&nbsp;<spring:message code='page.totalPage'/>&nbsp;<label id="totalPage"></label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br>
			<a id="btmPrint" href="javascript:void(0)" onClick="doPrint();">
				<img src="/LexmarkOPSPortal/images/transparent.png" class="ui_icon_sprite print-icon" style="cursor:pointer" title="<spring:message code='button.clickToPrint'/>" />
				<span style="cursor:pointer"><spring:message code='button.clickToPrint'/></span>
			</a>
		</td>
	</tr>
</table>
<script type="text/javascript">
		var pageSize = 20;
		var parentgrid = window.opener.deviceListGrid;
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
		
		var currURL=window.location.href;
	   
		
		if(remCols>0)
			extraCols++;

		var startCol=1;
		var endCol=10;
		var gridContent = "";
		for(k=0;k<extraCols;k++)
		{
			
			gridContent += "<br><table border='1px'  cellspacing='0px' width='100%'><tr>";
	
				for(m=startCol;m<endCol;m++){
					//if(!parentgrid.isColumnHidden(m)){
						 if((currURL.indexOf('/grid-view')!=-1)||m!=25){
					var gridhead=parentgrid.getHeaderCol(m);
					gridContent = gridContent+"<th align='left'>"+gridhead+"</th>";
					}
				}
				gridContent +="</tr>";
				
				for(n=start;n<(start+printRecordNumber);n++){
				
					gridContent += "<tr>";
					for(i = startCol;i<endCol;i++){
						//if(!parentgrid.isColumnHidden(i)){
							if((currURL.indexOf('/grid-view')!=-1)||i!=25){
						gridContent += "<td>";
						gridContent += parentgrid.cellByIndex(n,i).cell.innerHTML;
						gridContent += "</td>";
						}
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
		/*
        var deviceListPrintGrid = new dhtmlXGridObject('device_list_print_grid');
        deviceListPrintGrid.setImagePath("<html:imagesPath/>/gridImgs/");
        deviceListPrintGrid.setHeader("<spring:message code='deviceFinder.listHeader.deviceFields'/>");
    	deviceListPrintGrid.setInitWidths("20,120,120,120,120,120,120,120,0,0,0,0,0,0");
    	deviceListPrintGrid.setColAlign("left,left,left,left,left,left,left,left,left,left,left,left,left,left");
    	deviceListPrintGrid.setColTypes("sub_row,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro");
    	deviceListPrintGrid.setColSorting("na,str,str,str,str,str,str,str,str,str,str,str,str,str");
    	deviceListPrintGrid.enableAutoWidth(true);
    	deviceListPrintGrid.enableAutoHeight(true);
    	deviceListPrintGrid.enableMultiline(true);
    	deviceListPrintGrid.setSkin("light");
    	deviceListPrintGrid.attachEvent("onSubRowOpen" ,function(id,state){
        	if(state){
        		var cell=deviceListPrintGrid.cellById(id,13).cell.innerHTML;
				 var arrs=document.getElementsByTagName("A");
				 for ( var m=0; m<arrs.length ; m++ ){
				 arrs[m].removeAttribute("href");
				 arrs[m].removeAttribute("target");
				 arrs[m].onclick = null;
				}
				document.getElementById(cell).onclick = null;
				document.getElementById(cell).style.cursor = "none";
			}
    	});
    	deviceListPrintGrid.setLockColVisibility("false,true,true,true,true,true,true,true,true,true,true,true,true,true");
    	deviceListPrintGrid.setColumnHidden(0,true);*/
    	/*
    	for(m=0;m<parentgrid.getColumnsNum();m++){
			if(parentgrid.isColumnHidden(m))
				deviceListPrintGrid.setColumnHidden(m,true);
			}
		*/
		/*
	    deviceListPrintGrid.init();
	    deviceListPrintGrid.prftInit();
	    deviceListPrintGrid.setSortImgState(true, a_state[0], a_state[1]);
	    for(i=1; i<=printRecordNumber; i++){
	    	deviceListPrintGrid.moveRowTo(start + printRecordNumber - i,null,"copy","sibling",parentgrid,deviceListPrintGrid);
		}*/
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
    		window.document.getElementById("topPrint").style.display='none';
    		window.document.getElementById("btmPrint").style.display='none';
    		window.print();
    		window.close();
    	};
</script>
<script type="text/javascript">
//---- Ominture script 
    /* portletName = "Device Finder Print";
     addPortlet(portletName);
     pageTitle="Device list print";*/
</script>