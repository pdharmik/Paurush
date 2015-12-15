<%@ include file="/WEB-INF/jsp/include.jsp"%>
<%@ include file="/WEB-INF/jsp/includeGrid.jsp"%>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/in-styles.css?version=<html:fileCacheVersion/>" />
<div id="div_top_print" class="width-600">
	<a id="topPrint" href="javascript:void(0)" onClick="doPrint();">
		<img src="<html:imagesPath/>transparent.png" class="ui-icon print-icon" height="23px" width="23px" class="cursor-pointer" title="<spring:message code='button.clickToPrint'/>" />
		<span class="cursor-pointer"><spring:message code='button.clickToPrint'/></span>
	</a>
</div><br>

<div class="main-wrap main-wrap-ext1" >
	<div class="content">
        <!-- Claim Information Start -->    	 
        <div class = "portlet-wrap" id = "claimInformation">
        	<div class="portlet-header dark-bar">
           		<h3><spring:message code="claim.label.claimInformation"/></h3>
        	</div>
			<div class="portlet-wrap-inner">
				<div class="width-100per">
					<div id="divClaimInformation" class="first-column" >
					</div>
				</div>
			</div>
			<div class="portlet-footer">
				<div class="portlet-footer-inner"></div>
			</div>
		</div>
        <!-- Claim Information End -->    	 
        
        <!-- Device Information Start -->   	 
        <div id="divDeviceInformation" class = "portlet-wrap">
        </div>
        <!-- Device Information End -->  
         	   	 
        <!-- Service History Start -->   	 
		<div id="divCDtlServiceHistory" class="portlet-wrap" style="display:none;">
 			<div class="portlet-header dark-bar">
 			<table class="displayGrid">
              <thead>
                <tr><th><spring:message code="claim.label.serviceHistory"/></th>
                </tr></thead>
              
            </table>
        		
        	</div>
        	<div class="portlet-wrap-inner">
	        	<div class="width-100per">
	       			<div id="divServiceHistory" class="columns two">
					</div>
				</div>
			</div>
       	 	<div class="portlet-footer">
       	 		<div class="portlet-footer-inner"></div>
       	 	</div>
		</div>
        <!-- Service History End --> 
       
        <!-- Customer Information Start -->  	 
		<div id="divCustomerInformation" class = "portlet-wrap">
		</div>
        <!-- Customer Information End --> 
        
        <!-- Problem Information Start -->  	 	 
		<div id ="divProblemInformation" class = "portlet-wrap">
		</div>
        <!-- Problem Information End -->    	 
        
        <!-- Technician Information Start -->       	 
		<div id ="divTechnicianInformation" class = "portlet-wrap">
		</div> 
		<!-- Technician Information End -->  	 
		
		<!-- Debrief Information Start-->    
		<div id="divDebriefInformation" class="portlet-wrap" style="display:none;">
		</div>
        <!-- Debrief Information End-->  
        
        <!-- Parts and Tools Start-->  
        <div class = "portlet-wrap" id="partAndToolPrint" style="display:none;">
			<div class="portlet-header dark-bar">
				<h3><spring:message code="claim.label.partsAndTools"/></h3>
			</div>
       	 	<div class="portlet-wrap-inner">
       	 		<div class="scroll">
       	 			<div id="divGridCDtlVRPRequest" class="status-wrap" style="display:none;">
	         	 		<div class="status-block">
	         	 			<h4><spring:message code="claim.label.requestedParts"/></h4>
							<div id="gridCDtlVRPRequest" class="width-100per"></div>
						</div>
					</div>
					<div id="divGridCDtlVOPRequest" class="status-wrap" style="display:none;">
	         	 		<div class="status-block">
	         	 			<h4><spring:message code="claim.label.ordered.parts"/></h4>
							<div id="gridCDtlVOPRequest" class="width-100per"></div>
						</div>
					</div>
					<div id="divGridCDtlVBRPRequest" class="status-wrap" style="display:none;">
	         	 		<div class="status-block">
	         	 			<h4><spring:message code="claim.label.parts.to.be.returned"/></h4>
							<div id="gridCDtlVBRPRequest"  class="width-100per"></div>
						</div>
					</div>
				</div>
			</div>
			<div class="portlet-footer">
				<div class="portlet-footer-inner"></div>
			</div>
		</div>
        <!-- Parts and Tools End-->  
        
        <!-- Additional Payment Requests Start-->  
		<div id="divGridCDtlAPRequest"  class="portlet-wrap" style="display:none;">
			<div class="portlet-header dark-bar">
				<h3><spring:message code="claim.label.additionalPaymentRequests"/></h3>
			</div>
			<div class="portlet-wrap-inner">
				<div class="scroll">
					<div class="columns two">
						<div id="gridCDtlAPRequest" class="width-100per"></div>
					</div>
				</div>
			</div>
       	 	<div class="portlet-footer">
       	 		<div class="portlet-footer-inner"></div>
       	 	</div>
		</div>
        <!-- Additional Payment Requests End-->            	
        
		<!-- Notes Start-->     	
		<div class = "portlet-wrap" id="notePrint">
			<div class="portlet-header dark-bar">
				<h3><spring:message code="claim.label.notes"/></h3>
			</div>
			<div class="portlet-wrap-inner">
				<div class="width-100per">
					<div class="columns two">
						<div class="width-100per" id="gridCDtlVNRequest"></div>
					</div>
				</div>
			</div>
			<div class="portlet-footer">
				<div class="portlet-footer-inner"></div>
			</div>
		</div>
	 	<!-- Notes Start--> 
	 	
	 	<!--Email Notifications Start-->     	    	
		<div class="portlet-wrap" id="email_Notifications">
			<div id="emailNotificationsContent">
				<div class="portlet-header">
					<h3><spring:message code="claim.label.emailNotifications" /></h3>
				</div><!-- portlet-header -->
				<div class="portlet-wrap-inner">
					<div class="width-100per">
						<div id="gridCDVEmailNotifications" class="width-100per"></div>
					</div>
				</div><!-- portlet-wrap-inner -->
				<div class="portlet-footer">
					<div class="portlet-footer-inner"></div><!-- portlet-footer-inner -->
				</div><!-- portlet-footer -->
			</div>
		</div>		
		<!--Email Notifications End-->     	    	
	</div><!-- content -->
</div>


<div id="div_btm_print" class="div-btm-print">
	<a id="btmPrint" href="javascript:void(0)" onClick="doPrint();">
		<img src="<html:imagesPath/>transparent.png" class="ui-icon print-icon" height="23px" width="23px" class="cursor-pointer" title="<spring:message code='button.clickToPrint'/>" />
		<span class="cursor-pointer"><spring:message code='button.clickToPrint'/></span>
	</a>
</div>
<script type="text/javascript">
	jQuery(document).ready(function() {
		var showButtonsFlag = window.opener.showButtonsFlag;
		var requestLexmarkReviewFlag = window.opener.requestLexmarkReviewFlag;
		var showRequestedPartsFlag = window.opener.showRequestedPartsFlag;
		var showOrderPartsFlag = window.opener.showOrderPartsFlag;
		var showReturnPartsFlag = window.opener.showReturnPartsFlag;
		var showAPRListFlag = window.opener.showAdditionalPaymentRequestListFlag;
		var showSHFlag = window.opener.isCDtlServiceHistoryLoaded;
		var showPartAndTool = true;
		initialPrintSection("divClaimInformation");
		initialPrintSection("divDeviceInformation");

		var grid;
		grid = window.opener.serviceHistoryContainer;
		var contentEle = window.opener.document.getElementById('serviceHistoryContent');
		if(contentEle.style.display != 'none'){
			convertGridToTable(grid,"divServiceHistory",false);
			window.document.getElementById("divCDtlServiceHistory").style.display='';
		}
		
		initialPrintSection("divCustomerInformation");

		initialPrintSection("divProblemInformation");

		initialPrintSection("divTechnicianInformation");
		
		grid = window.opener.gridCDtlVNRequestGrid;		
		convertNoteGridToTable(grid,"gridCDtlVNRequest");
		
		if(showPartAndTool){
			window.document.getElementById("partAndToolPrint").style.display='block';
			if(showRequestedPartsFlag){
				grid = window.opener.gridCDtlVRPRequestGrid;
				if(grid.getRowsNum() > 0){
					showPartAndTool &= false;
				}
				convertGridToTable(grid,"gridCDtlVRPRequest",false);
				window.document.getElementById("divGridCDtlVRPRequest").style.display='block';
			}
			if(showOrderPartsFlag){
				grid = window.opener.gridCDtlVOPRequestGrid;
				if(grid.getRowsNum() > 0){
					showPartAndTool &= false;
				}
				convertGridToTable(grid,"gridCDtlVOPRequest",true);
				window.document.getElementById("divGridCDtlVOPRequest").style.display='block';
			}

			if(showReturnPartsFlag){
				grid = window.opener.gridCDtlVBRPRequestGrid;
				if(grid.getRowsNum() > 0){
					showPartAndTool &= false;
				}
				convertGridToTable(grid,"gridCDtlVBRPRequest",true);
				window.document.getElementById("divGridCDtlVBRPRequest").style.display='block';
			}
		}

		if(!showButtonsFlag){
			initialPrintSection("divDebriefInformation");
			window.document.getElementById("divDebriefInformation").style.display='';
		}
		/* commented for floor support
		if(showRequestedPartsFlag){
			
			grid = window.opener.gridCDtlVRPRequestGrid;
			if(grid.getRowsNum() > 0){
				showPartAndTool &= false;
			}
			convertGridToTable(grid,"gridCDtlVRPRequest",false);
			window.document.getElementById("divGridCDtlVRPRequest").style.display='';
		}

		if(showOrderPartsFlag){
			grid = window.opener.gridCDtlVOPRequestGrid;
			if(grid.getRowsNum() > 0){
				showPartAndTool &= false;
			}
			convertGridToTable(grid,"gridCDtlVOPRequest",true);
			window.document.getElementById("divGridCDtlVOPRequest").style.display='';
		}

		if(showReturnPartsFlag){
			grid = window.opener.gridCDtlVBRPRequestGrid;
			if(grid.getRowsNum() > 0){
				showPartAndTool &= false;
			}
			convertGridToTable(grid,"gridCDtlVBRPRequest",true);
			window.document.getElementById("divGridCDtlVBRPRequest").style.display='';
		} */
		if(!showPartAndTool){
			window.document.getElementById("partAndToolPrint").style.display='';
		}
		
		if(showAPRListFlag){
			grid = window.opener.gridCDtlAPRequestGrid;
			convertGridToTable(grid,"gridCDtlAPRequest",false);
			window.document.getElementById("divGridCDtlAPRequest").style.display='';
		}

		
	

		grid = window.opener.emailNotificationsGrid;
		if(grid.getRowsNum() == 0){
			document.getElementById('email_Notifications').style.display = 'none';
		}
		convertEmailNotificationsGridToTable(grid,"gridCDVEmailNotifications");
		
	});
	
	 function initialPrintSection(divContentPosition){
		 window.document.getElementById(divContentPosition).innerHTML = window.opener.window.document.getElementById(divContentPosition).innerHTML;
	 }

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

	function convertNoteGridToTable(grid,idPosition){
		var pageSize = 5;
		var start = (Number(grid.currentPage)-1)*pageSize;
		var startColumn = 1;
		var rowNumber = grid.getRowsNum();
		if(rowNumber == 0){
			document.getElementById("notePrint").style.display="none";
			return;
		}
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
						if(j==3){
							var rowId = grid.getRowId(i);
							var noteDetail = grid.getUserData("" + rowId,"__sub_row");
							var startIndex = noteDetail.lastIndexOf("<div");
							startIndex=startIndex+47; 
							var endIndex = noteDetail.lastIndexOf("</div>");
							noteDetail = noteDetail.substring(startIndex,endIndex);
							str = str + "<td>"+noteDetail+"</td>";
						}else{
							str = str + "<td>"+grid.cellByIndex(i,j).cell.innerHTML+"</td>";
						}
					}
				}
				str = str + "</tr>";
				tab_Content = tab_Content + str;
			}
		}
		tab_Content = tab_Content + "</table>";
		window.document.getElementById(idPosition).innerHTML =  tab_Content;
	};

	function convertEmailNotificationsGridToTable(grid,idPosition){
		var pageSize = 5;
		var start = (Number(grid.currentPage)-1)*pageSize;
		var startColumn = 1;
		var rowNumber = grid.getRowsNum();
		if(rowNumber == 0){
			document.getElementById("email_Notifications").style.display="none";
			return;
		}
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
						if(j==3){
							var rowId = grid.getRowId(i);
							var noteDetail = grid.getUserData("" + rowId,"__sub_row");
							var startIndex = noteDetail.lastIndexOf("<br>");
							startIndex=startIndex+4; 
							noteDetail = noteDetail.substring(startIndex);
							str = str + "<td>"+noteDetail+"</td>";
						}else{
							str = str + "<td>"+grid.cellByIndex(i,j).cell.innerHTML+"</td>";
						}
					}
				}
				str = str + "</tr>";
				tab_Content = tab_Content + str;
			}
		}
		tab_Content = tab_Content + "</table>";
		window.document.getElementById(idPosition).innerHTML =  tab_Content;
	};


	function doPrint(){
		window.document.getElementById("topPrint").style.display='none';
 		window.document.getElementById("btmPrint").style.display='none';
 		window.print();
 		window.close();
		//document.all.WebBrowser.ExecWB(7,1);
	};
 </script>