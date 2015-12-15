<%@ include file="/WEB-INF/jsp/include.jsp"%>
<%@ include file="/WEB-INF/jsp/includeGrid.jsp"%>
<style>
/*
added for blue bars
*/
div.portlet-header {
	height: 30px;
	margin: 0;
	padding: 0;
	}


div.portlet-header h3 {
	height: 30px;
	width: 100%;
	margin: 0;
	padding: 0;
	font-size: 16px;
	line-height: 37px;
	text-indent: 15px;
	color: #1d1d25;
	font-weight: normal;
	}	
	
* html div.portlet-header h3 {	
	line-height: 30px;
}
</style>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/in-styles.css?version=<html:fileCacheVersion/>" />
<div id="div_top_print" class="width-600">
	<a id="topPrint" href="javascript:void(0)" onClick="doPrint();">
		<img src="<html:imagesPath/>transparent.png" class="ui-icon print-icon cursor-pointer" height="23px" width="23px" title="<spring:message code='button.clickToPrint'/>" />
		<span class="cursor-pointer"><spring:message code='button.clickToPrint'/></span>
	</a>
</div><br>

<div class="main-wrap width100per-height100per" id = "PrintRequest">
	<div class="content">
	
		<div id="requestInformation" class="portlet-wrap"></div>
	
		<div id="deviceInformation" class="portlet-wrap"></div>
           	 
     	<div class="portlet-wrap" id="service_history" style="display:none">
        	<div class="portlet-header dark-bar">
        	<table class="displayGrid">
              <thead>
                <tr><th><spring:message code="claim.label.serviceHistory" /></th>
                </tr></thead>
              
            </table>
         		
			</div>
			<div class="portlet-wrap-inner">
				<div class="width-100per">
					<div class="columns two">
						<div id="service_history_container" class="width-100per"></div>
					</div>
				</div>
			</div>
        	<div class="portlet-footer">	
				<div class="portlet-footer-inner">
				</div>
			</div>
    	</div>
    	<div></div>
	
		<div id="customerInformation" class="portlet-wrap"></div>

		<div id="problemInformation" class="portlet-wrap"></div>
	
		<div id="serviceInformation" class="portlet-wrap"></div>

		<div id="closeOutActivity" class="portlet-wrap"></div>
		
		<div id="technicianInformation" class="portlet-wrap"></div>
		
		<!-- Technician Instructions Start -->  
		<div id="technicianInstructions" class = "portlet-wrap" style="display:none">
			<div class="portlet-header dark-bar">
				<h3><spring:message code="claim.label.technicianInstructions"/></h3>
			</div>
			<div class="portlet-wrap-inner">
				<div class="width-100per">
					<div class="columns two">
						<div id="gridRDVTechnicianInstructions" class="width-100per"></div>
						<div><span id="gridRDVTechnicianInstructionsPagingArea"></span>&nbsp;<span id="gridRDVTechnicianInstructionsInfoArea"></span></div><!-- mygrid_container -->
					</div>
				</div>	
			</div>
			<div class="portlet-footer">	
				<div class="portlet-footer-inner"></div>
			</div>
        </div>
		<!-- Technician Instructions End -->
		
		<!-- Parts and Tools Start -->
		<div id="partsAndTools" class="portlet-wrap" style="display:none">
         	<div class="portlet-header dark-bar">
         	 	<h3><spring:message code="claim.label.partsAndTools"/></h3>
         	</div>

			<!-- Order Parts Start -->
			<div class="status-wrap" style="display:none">
	         	<div class="status-block">
	         	 	<h4><spring:message code="claim.label.ordered.parts"/></h4>
						<div id="gridRDVOrderedPartsTable" class="width-100per"></div>
						<div><span id="gridRDVOrderedPartsTablePagingArea"></span>&nbsp;<span id="gridRDVOrderedPartsTableInfoArea"></span></div><!-- mygrid_container -->
				</div>
			</div>
			<!-- Order Parts End -->
			
			<!-- Parts to be returned Start -->
			<div class="status-wrap" style="display:none">
				<div class="status-block">
					<h4><spring:message code="claim.label.parts.to.be.returned"/></h4>
					<div id="gridRDVbeReturnedParts"  class="width-100per"></div>
					<div><span id="gridRDVbeReturnedPartsPagingArea"></span>&nbsp;<span id="gridRDVbeReturnedPartsRecinfoArea"></span></div><!-- mygrid_container -->
				</div>
			</div>
			<!-- Parts to be returned End -->
			
          	<div class="portlet-footer">	
       	 		<div class="portlet-footer-inner"></div>
       	 	</div>       	
        </div>
		<!-- Parts and Tools End -->

		
		<div id="recommendedParts" class = "portlet-wrap" style="display:none">
			<div class="portlet-header dark-bar">
				<h3><spring:message code="claim.label.recommendedParts"/></h3>
			</div>
			
			<div class="portlet-wrap-inner">
				<div class="width-100per">
					<div class="columns two">
						<div id="gridRDVRecommendedParts" class="width-100per"></div>
						<div><span id="gridRDVRecommendedPartsPagingArea"></span>&nbsp;<span id="gridRDVRecommendedPartsInfoArea"></span></div><!-- mygrid_container -->
					</div>
				</div>
			</div>

			<div class="portlet-footer">	
				<div class="portlet-footer-inner"></div>
			</div>
        </div>
	
		<!-- Additional Payment Requests Start--> 
		<div id="additionalPaymentRequests" class = "portlet-wrap" style="display:none">
			<div class="portlet-header dark-bar">
				<h3><spring:message code="claim.label.additionalPaymentRequests"/></h3>
			</div>
			
			<div class="portlet-wrap-inner">
				<div class="width-100per">
					<div class="columns">
						<div id="gridRDVpaymentTable" class="width-100per"></div>
						<div><span id="gridRDVpaymentTablePagingArea"></span>&nbsp;<span id="gridRDVpaymentTableInfoArea"></span></div><!-- mygrid_container -->
					</div>
				</div>
			</div>
			
			<div class="portlet-footer">	
				<div class="portlet-footer-inner"></div>
			</div>
        </div>
        <!-- Additional Payment Requests end-->  	 
         <!--/*changes for LEX:AIR00070474 Start*/-->	 
		<!-- Notes Start-->     	
		<!--<div id="serviceRequestNotes" class = "portlet-wrap" style="display:none">
			<div class="portlet-header dark-bar">
				<h3><spring:message code="claim.label.notes"/></h3>
			</div>
			<div class="portlet-wrap-inner">
				<div style="width:100%;">
					<div class="columns two">
						<div style="width:100%;" id="gridRDVnotes"></div>
						<div><span id="gridRDVnotesPagingArea"></span>&nbsp;<span id="gridRDVnotesInfoArea"></span></div><!-- mygrid_container -->
			<!--		</div>
				</div>
			</div>
			<div class="portlet-footer">
				<div class="portlet-footer-inner"></div>
			</div>
		</div>-->
		
		<div id="show_print_notes"></div>
		
		<!--/*changes for LEX:AIR00070474 End*/-->
		<!-- Notes End-->   


	<div class="portlet-wrap" id="email_Notifications" style="display:none">
			<div class="portlet-header">
				<h3><spring:message code="claim.label.emailNotifications" /></h3>
			</div><!-- portlet-header -->
			<div class="portlet-wrap-inner">
				<div class="width-100per">
					<div class="columns">
						<div id="gridCDVEmailNotifications" class="width-100per"></div>
					</div>	
				</div>
			</div><!-- portlet-wrap-inner -->
			<div class="portlet-footer">
				<div class="portlet-footer-inner"></div><!-- portlet-footer-inner -->
			</div><!-- portlet-footer -->
	</div>
		
	</div>
</div>



<div id="div_btm_print" class="div-btm-print">
	<a id="btmPrint" href="javascript:void(0)" onClick="doPrint();">
		<img src="<html:imagesPath/>transparent.png" class="ui-icon print-icon cursor-pointer" height="23px" width="23px" title="<spring:message code='button.clickToPrint'/>" />
		<span class="cursor-pointer"><spring:message code='button.clickToPrint'/></span>
	</a>
</div>

<script type="text/javascript">
/*changes for LEX:AIR00070474 End*/   
	window.document.getElementById("show_print_notes").innerHTML = window.opener.window.document.getElementById("serviceRequestNotes").innerHTML;
/*changes for LEX:AIR00070474 End*/
	jQuery(".portlet-wrap").each(function(index,element){
		var elementOfOpenerWindow = window.opener.window.document.getElementById(element.id);
		if(!elementOfOpenerWindow){
			jQuery(element).remove();
		}else if(!element.innerHTML||jQuery.trim(element.innerHTML)==""){
			element.innerHTML = elementOfOpenerWindow.innerHTML;
			jQuery(element).find("button").remove();	
		}
	});	
	
	function convertGridToTable(grid,idPosition,isSubrowIncluded){
		var pageSize = 5;
		var start = (Number(grid.currentPage)-1)*pageSize;
		var startColumn = 0;
		if(isSubrowIncluded){
			startColumn = 1;
		}
		var rowNumber = grid.getRowsNum();
		var totalPages = Math.ceil(Number(rowNumber)/pageSize);
		var printRecordNumber;
		if(rowNumber%pageSize > 0 && grid.currentPage==totalPages){
			printRecordNumber = rowNumber%pageSize;
			}else{
				printRecordNumber = pageSize;
			}
		tab_Content = "<table border='1px'  cellspacing='0px' width='100%'style='text-align:left;' ><tr>";
		for(m=startColumn;m<grid.getColumnsNum();m++){
			if(!grid.isColumnHidden(m))
				tab_Content = tab_Content + "<td><h5>"+grid.getHeaderCol(m) + "</h5></td>" ;
			}
		tab_Content = tab_Content + "</tr>";
		if(rowNumber!=0){
			for(i=start;i<(start+printRecordNumber);i++){
				str = "<tr>";
				for(j = startColumn;j<grid.getColumnsNum();j++){
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
	
	
	var forPrintGridObjects=window.opener.window.forPrintGridObjects;
	for(var k=0;k<forPrintGridObjects.length;k++){
		var grid=forPrintGridObjects[k];
		var divID=grid.entBox.id;
		var hasSubRow = false;
		for (var kk = 0;kk < grid.cellType.length;kk++){
			if("sub_row" == grid.cellType[kk]){
				hasSubRow = true;
			}
		}
		if(grid.getRowsNum() > 0){
			jQuery("#"+divID).parents(":hidden").show();
			convertGridToTable(grid,divID,hasSubRow);
		}
		
	}
	var contentEle = window.opener.document.getElementById('serviceHistoryContent');
	if(contentEle.style.display != 'none'){
		convertGridToTable(window.opener.serviceHistoryContainer,"service_history_container",false);
		jQuery("#service_history").show();
	}
	
	function doPrint(){
		window.document.getElementById("topPrint").style.display='none';
 		window.document.getElementById("btmPrint").style.display='none';
 		window.print();
 		window.close();
		//document.all.WebBrowser.ExecWB(7,1);
	};
</script>