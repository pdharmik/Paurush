<%@ include file="/WEB-INF/jsp/include.jsp"%>
<%@ include file="/WEB-INF/jsp/includeGrid.jsp"%>
<%@ taglib prefix="util" uri="/WEB-INF/tld/lexmark-util.tld"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%-- <%@ include file="/WEB-INF/jsp/includeDatePicker.jsp"%> --%>
<%-- <style type="text/css"><%@ include file="../../css/mps.css" %></style> --%>
<%-- <%@ include file="/WEB-INF/jsp/includeDatePicker.jsp"%> --%>
<%@ page import="com.lexmark.services.util.LexmarkSPOmnitureConstants"%>
<%-- <portlet:renderURL var="updatePageCountURL">
	<portlet:param name="action" value="updatePageCounts"></portlet:param>
</portlet:renderURL> --%>
<script type="text/javascript" src="<html:rootPath/>js/mps.js"></script>
<script type="text/javascript"
	src="<html:rootPath/>js/jQuery/jquery.qtip-1.0.0-rc3.min.js">
	
	</script>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/in-styles.css?version=<html:fileCacheVersion/>" />

<portlet:resourceURL id="updatePageCounts" var="updatePageCounts">
</portlet:resourceURL>

<div id="pageCount" class="min-width-600px" >
<div id="validationErrors" class="error"></div>
 <div id="pageCntUpdtSuccess" class="info banner ok" style="display: none;"></div>
    <input type="hidden" id="assetRowId"/> 
   <div class="portlet-wrap" id="assetDetails">
        <table  width="90%" align="center">
			<tr>
                <td align="left" width="20%" class="blackCopylabel"><spring:message code="deviceDetail.label.serialNumber"/>:&nbsp;</td>
                <td align="left" width="25%" class="blackCopylabel" id="serialNumber"></td>
                
                <td align="right" width="20%" class="blackCopylabel"><spring:message code="deviceDetail.label.ipAddress"/>:&nbsp;</td>
                <td align="right" width="25%" class="blackCopylabel" id="ipAddress"></td>
            </tr>
      
			<tr>
			    <td align="left" width="20%" class="blackCopylabel"><spring:message code="deviceDetail.label.model"/>:&nbsp;</td>
			    <td align="left" width="25%" class="blackCopylabel" id="productLine"></td>
			    
			    <td align="right" width="20%" class="blackCopylabel"><spring:message code="deviceDetail.label.assetTag"/>:&nbsp;</td>
			    <td align="right" width="25%" class="blackCopylabel" id="assetTag"></td>
			   
			</tr>
		
			
			
			
		</table>
   </div>
   <div class="portlet-wrap">
      <div class="portletBlock bgSolid">
       <div class="portlet-wrap rounded popupInner">
          <div class="portlet-wrap-inner"><div id="pagecountGrid" class="bg-white"></div></div>
          <div id="loadingNotification1" class='gridLoading'>
	    			<!--spring:message code='loadingNotification' />&nbsp;&nbsp;--><img src="<html:imagesPath/>gridloading.gif"/>
	   			</div>
          <div class="pagination" id="paginationId"><span id="pagingArea2"></span><span id="infoArea2"></span></div>
        </div>
      </div>
  </div>
  <div class="buttonContainer relativeI">
	   <button class="button_cancel relativeI"  onclick="javascript:clearContentsOfpopup1();" id="btnCancel" type="button"><spring:message code="deviceDetail.button.cancel" /></button>
	    &nbsp;
	   <button class="button relativeI" onclick="javascript:validateCountPattern();" id="btnSubmit" type="button"><spring:message code="deviceDetail.button.submit" /></button>
  </div>
</div>
dialog.dialog('close');
<script type="text/javascript">
   var pageCountGrid;
   
   var currentPageGrid = "";
  
   var assetID;
   
   function initializePageCountGrid(assetRowId, serialNumber, ipAddress, productLine, assetTag){
	 
	   /*var assetRowId = document.getElementById("assetRowId").innerHTML;	
	   var serialNumber = document.getElementById("serialNumber").innerHTML;
	   var ipAddress = document.getElementById("ipAddress").innerHTML;
	   var productLine = document.getElementById("productLine").innerHTML;
	   var assetTag = document.getElementById("assetTag").innerHTML;*/

		//alert(assetRowId);
	   assetID = assetRowId;
		/*alert(serialNumber);
		alert(ipAddress);
		alert(productLine);
		alert(assetTag);*/
		
   pageCountGrid = new dhtmlXGridObject('pagecountGrid');
   pageCountGrid.setImagePath("<html:imagesPath/>gridImgs/");
   //mygrid.setHeader(headerString);
   pageCountGrid.setHeader("<spring:message code='requestInfo.pageCountPopUp.listHeader'/>");
   pageCountGrid.setInitWidths("90,110,110,200,*");
   pageCountGrid.setColAlign("left,left,left,left,left");
   pageCountGrid.setSkin("light");
   pageCountGrid.setColSorting("str,str,str,str,str");
   pageCountGrid.setColTypes("ro,ro,ro,ro,ro");
   pageCountGrid.enableAutoHeight(true);
   pageCountGrid.enableMultiline(true);
   pageCountGrid.enableColumnMove(true);
   pageCountGrid.init();
   pageCountGrid.enablePaging(true, 10, 2, "pagingArea2", true, "infoArea2");
   pageCountGrid.setPagingSkin("bricks"); 
   pageCountGrid.loadXML("<portlet:resourceURL id='getPageCountPopUp'/>" + "&assetRowId=" + assetRowId + "&serialNumber=" + serialNumber + "&ipAddress=" + ipAddress + "&productLine=" + productLine +"&assetTag="+ assetTag);
  
   pageCountGrid.attachEvent("onXLS", function() {
   	dBPersistentFlag = false;
       document.getElementById('loadingNotification1').style.display = 'block';
       document.getElementById('paginationId').style.display = 'none';
       
   });
   
   pageCountGrid.attachEvent("onXLE", function() {
	   //alert("in xle..after xml");
	   var num = pageCountGrid.getRowsNum();
	   //alert("num rows "+num);
	   for(var i=0;i<num;i++){
		   var cellObj = pageCountGrid.cellById(i,1); //fetching the second cell value which is the page count read date
			var pageCountdate=cellObj.getValue();
			//alert("pageCountdate "+pageCountdate);
			if(pageCountdate == ''){
				cellObj.setValue("");
			}
			else{
			var d1 = new Date(pageCountdate+" UTC");
			
			//alert("d1: "+d1.toString());
			var d3 = d1.toString();
			var d2 = new Date(d3);
			var month = d2.getMonth() + 1;
			var localdate = month + "/" + d2.getDate() + "/" + d2.getFullYear() 
							+ " " + d2.getHours() + ":" + d2.getMinutes() + ":" + d2.getSeconds();
			
			cellObj.setValue(localdate);
		   }
	   }
		
	   
       document.getElementById('loadingNotification1').style.display = 'none';
       //alert("wwww");
       document.getElementById('paginationId').style.display = 'block';
       setTimeout(function(){
   		rebrandPagination();
   	
   	},100);
       //alert("qqq");
       /*pageCountGrid.forEachRow(function(id){
           //document.getElementById("localizedReadDate" + id).value = localizeDate(document.getElementById("readDate" + id).value);
           if( document.getElementById("localizedReadDate" + id)!=null){
               document.getElementById("localizedReadDate" + id).value = localizeDate(serverTodayStr);
               reloadDateAction('localizedReadDate' + id, document.getElementById("readDate" + id).value, 'PREV');
               reloadDateAction('localizedReadDate' + id, serverTodayStr, 'NEXT');
           }
	        isColorCapable(id);
	        if(!isColorCapableFlag){ 
	            pageCountGrid.setCellExcellType(id,10,"ro"); 
	        }
       }); // foreach over 
      	localizeSaveButton();*/
   });
   
   } 	 
   //pageCountGrid.parse(ar,"jsarray");
	
    //new variables
	var newPageCountVal;
	var currentPageCountDateTime;
	var newLTPCPgCntVal = 0;
	var currentPageCount;
	var assetRowId1;
	
	var newPgCntValColor = ""; 
	
	var newPgCntValA3Color = "";
	
	var newPgCntValA3LTPC = "";
	
	var newPgCntValA4Color = "";
	
	var newPgCntValA4LTPC = "";
	var newPgCntValA5LTPC = "";
	var newPgCntValA5Color = "";
	var newPgCntValLegalLTPC = "";
	var newPgCntValLegalColor = "";
	var newPgCntValLetterLTPC = "";
	var newPgCntValLetterColor = "";
	var newPgCntValStatementLTPC = "";
	var newPgCntValStatementColor = "";
	var newPgCntValTabloidLTPC = "";
	var newPgCntValTabloidColor = "";
	var newPgCntValLTPC = "";
	

	var dataForColor = new Array();
	var dataForLTPC = new Array();
	var dataForA3Color = new Array();
	var dataForA3LTPC = new Array();
	var dataForA4Color = new Array();
	var dataForA4LTPC = new Array();
	var dataForScan = new Array();
	var dataForFax = new Array();
	var dataForBlack = new Array();
	var dataForCyan = new Array();
	var dataForSoftware = new Array();
	var dataForPgsScanCopy = new Array();
	var dataForPgsScanFax = new Array();
	var dataForPgsScanNetwork = new Array();
	var dataForPgsScanUsb = new Array();
	var dataForTotalScans = new Array();
	var dataForLetterColor = new Array();
	var dataForLetterLTPC = new Array();
	var dataForA5Color = new Array();
	var dataForA5LTPC = new Array();
	var dataForLegalColor = new Array();
	var dataForLegalLTPC = new Array();
	var dataForStatementColor = new Array();
	var dataForStatementLTPC = new Array();
	var dataForTabloidColor = new Array();
	var dataForTabloidLTPC = new Array();
	
	
	//brmandal started
	/*function getPageCountGridCellValue(currentCount,element)
	{
		//alert("Humba");
		//alert("element.. "+element);
		//alert(jQuery(element).val());
		//alert("currentCount"+currentCount);
		newLTPCPgCntVal = jQuery(element).val();
		currentPageCount = currentCount;
		
	}*/
	var oldColorCount="";
	var oldLTPCCount="";
	
	function validateCountPattern() {

		if(document.getElementById("Color")){
			oldColorCount = jQuery("#Color").text();
		}
		if(document.getElementById("LTPC")){
	    	oldLTPCCount = jQuery("#LTPC").text();
		}
		currentPageGrid = pageCountGrid.currentPage;
		//alert(jQuery("#currentPageCount1").val());
		var num = pageCountGrid.getRowsNum();
		//alert("num "+num);
		/*for(var i=1;i<=num;i++)
		{
			alert(jQuery("#currentPageCount"+i).val());
		}*/
		callOmnitureAction('<%=LexmarkSPOmnitureConstants.DEVICEFINDER%>', '<%=LexmarkSPOmnitureConstants.DEVICEFINDERLISTUPDATEPAGECOUNT%>'); 
		jQuery("#validationErrors").html("");
		jQuery("#validationErrors").hide();
		jQuery('.errorColor').removeClass('errorColor');
		var validationFailed;
		var validationFailedonNull;
		var validationSuccess;
		var page=1;
		//This loop does the validations within the same cell, i.e. new page count cell of each row
		pageCountGrid.changePage(page);
		for(var i=0;i<num;i++){
if(i%10==0){
				
				pageCountGrid.changePage(page);
				page++;
			}
			var newLTPCPgCntVal = jQuery("#currentPageCount"+i).val();
			
			//Added for defect id 8198 MPS Phase 2.1
			var k = i+1;
			if(jQuery("#rwid_"+k).val()!="" && jQuery("#rwid_"+k).val() != null){
				var myDate = formatDateToDefault(jQuery("#rwid_"+k).val());
				
				//myDate=myDate.substring(0,myDate.indexOf(" "));
				//alert ("My Date" + myDate);
				
				
				var today = convertDateToStringPageCount(new Date());
				
				//alert("Today" + today);
				
				if(myDate > today){
					//alert("Inside IF ");
					jQuery("#validationErrors").show();
					jQuery("#validationErrors").html("");
					jQuery("#validationErrors").append("<li><spring:message code = 'meterRead.msg.pgDateTime'/></li>");
					return false;
				}
			}
			//End
			
			if((newLTPCPgCntVal.length !=0))
				{
				
				/******Check whether new color field is numeric ********/
				if(!(isDigitForPgCnt(newLTPCPgCntVal)))
					{	
						//jQuery("#validationErrors").show();
						//jQuery("#validationErrors").append("<li><spring:message code='validation.error.notNumber'/></li>");
						validationFailed = "true";
					}
				else{
					validationSuccess = "false";
					//break;
					}
					
			}
			 
			/*********LTPC and COLOR CANT BE NULL***************/
			else {
				//jQuery("#validationErrors").show();
				//jQuery("#validationErrors").html("");			
				if(newLTPCPgCntVal.length == 0){
					validationFailedonNull = "true";
					}
				
			}
		}
		if(validationFailed == "true")
		{
			//alert("in true");
			jQuery("#validationErrors").show();
			jQuery("#validationErrors").append("<li><spring:message code='validation.error.notNumber'/></li>");
			return false;
		}
		if((validationFailedonNull == "true") && (validationSuccess != "false") && (validationFailed != "true"))
		{
			//alert("in not true");
			jQuery("#validationErrors").show();
			jQuery("#validationErrors").html("");			
			jQuery("#validationErrors").append("<li><spring:message code='meterRead.msg.ltpcReadRequired'/></li>");
			return false;
		}
		if(! validateLTPCColorData()){
			return false;
			}
		
		/*If everything is fine, go for submission*/
		fireAjax();/*Go for the data submission using ajax*/
	}

	/************ This is to validate whether the data entered is greater than the existing values********/
	function validateLTPCColorData()
	{	
		
		var numRowsInGrid = pageCountGrid.getRowsNum();
		//alert("numRowsInGrid "+numRowsInGrid);
		var ltpcDiff;
		var colorDiff;
		var no_of_day_diff=30;
		var page=1;
		pageCountGrid.changePage(page);
		/* Added for defect #10673 */
		page++;
		/* End */
		//this loop is for validations betwn last page count and new page count within the same row
		for(var i=0;i<numRowsInGrid;i++){
		if(i%10==0 && i!=0){
				
				pageCountGrid.changePage(page);
				page++;
			}	
			var cellObj = pageCountGrid.cellById(i,0); //fetching the first cell value
			var pageCountType=cellObj.getValue();
			//alert("pageCountType "+pageCountType);
			
			if((pageCountType == "<spring:message code='pageCntUpload.ltpc'/>") || (pageCountType == "<spring:message code='pageCntUpload.color'/>") || 
					(pageCountType == "<spring:message code='pageCntUploadLegend.a3ColorHeader'/>") || (pageCountType == "<spring:message code='pageCntUploadLegend.a3LTPCHeader'/>") || (pageCountType == "<spring:message code='pageCntUploadLegend.a4LTPCHeader'/>") ||
					(pageCountType == "<spring:message code='pageCntUploadLegend.a4ColorHeader'/>") || (pageCountType == "<spring:message code='pageCntUploadLegend.scanHeader'/>") || (pageCountType ==  "<spring:message code='pageCntUploadLegend.faxHeader'/>") || 
					(pageCountType == "<spring:message code='pageCntUploadLegend.blackHeader'/>") || (pageCountType == "<spring:message code='pageCntUploadLegend.cyanHeader'/>") || (pageCountType == "<spring:message code='pageCntUploadLegend.softwareHeader'/>") || 
					(pageCountType == "<spring:message code='pageCntUploadLegend.pgsScanCopyHeader'/>") || (pageCountType == "<spring:message code='pageCntUploadLegend.pgsScanFaxHeader'/>") || (pageCountType == "<spring:message code='pageCntUploadLegend.pgsScanNetworkHeader'/>") ||
					(pageCountType == "<spring:message code='pageCntUploadLegend.pgsScanUsbHeader'/>") || (pageCountType == "<spring:message code='pageCntUploadLegend.totalScansHeader'/>") || (pageCountType == "<spring:message code='pageCntUploadLegend.letterColorHeader'/>") ||
					(pageCountType == "<spring:message code='pageCntUploadLegend.letterLTPCHeader'/>") || (pageCountType == "<spring:message code='pageCntUploadLegend.a5ColorHeader'/>") || (pageCountType == "<spring:message code='pageCntUploadLegend.a5LTPCHeader'/>") ||
					(pageCountType == "<spring:message code='pageCntUploadLegend.legalColorHeader'/>") || (pageCountType == "<spring:message code='pageCntUploadLegend.legalLTPCHeader'/>") || (pageCountType == "<spring:message code='pageCntUploadLegend.statementColorHeader'/>") ||
					(pageCountType == "<spring:message code='pageCntUploadLegend.statementLTPCHeader'/>") || (pageCountType == "<spring:message code='pageCntUploadLegend.tabloidColorHeader'/>") || (pageCountType == "<spring:message code='pageCntUploadLegend.tabloidLTPCHeader'/>")){
				//alert("pageCountType" + pageCountType);
				var newPgCntVal = jQuery("#currentPageCount"+i).val();
				//alert("newPgCntVal "+newPgCntVal);
				var cellObj1 = pageCountGrid.cellById(i,2); //fetching the third cell value
				var lastPgCnt1=cellObj1.getValue(); 
				//alert("lastPgCnt1 "+lastPgCnt1);
				/*if(lastPgCnt1.length<=0)
				{	
					lastPgCnt1=0;
				}
				if(lastPgCnt1==""){
					lastPgCnt1=0;
				}

				if(newPgCntVal.length<=0)
				{	
					newPgCntVal=0;
				}
				if(newPgCntVal==""){
					newPgCntVal=0;
				}*/
				//alert("lastPgCnt1 "+lastPgCnt1);
				
				/*Below if is for checking the whether new ltpc is less than current ltpc*/
				if((parseInt(lastPgCnt1)) > (parseInt(newPgCntVal)))
				{	
					//alert("1");
					jQuery("#validationErrors").show();
					jQuery("#validationErrors").html("");
					if((pageCountType == "<spring:message code='pageCntUpload.ltpc'/>"))
						jQuery("#validationErrors").append("<li><spring:message code='meterRead.msg.LTPCValueLess'/></li>");				
					if((pageCountType == "<spring:message code='pageCntUpload.color'/>"))
						jQuery("#validationErrors").append("<li><spring:message code='meterRead.msg.colorReadLess'/></li>");
					if((pageCountType == "<spring:message code='pageCntUploadLegend.a3ColorHeader'/>"))
						jQuery("#validationErrors").append("<li><spring:message code='meterRead.msg.A3colorReadLess'/></li>");
					if((pageCountType == "<spring:message code='pageCntUploadLegend.a3LTPCHeader'/>"))
						jQuery("#validationErrors").append("<li><spring:message code='meterRead.msg.A3LTPCReadLess'/></li>");
					if((pageCountType == "<spring:message code='pageCntUploadLegend.a4ColorHeader'/>"))
						jQuery("#validationErrors").append("<li><spring:message code='meterRead.msg.A4colorReadLess'/></li>");
					if((pageCountType == "<spring:message code='pageCntUploadLegend.a4LTPCHeader'/>"))
						jQuery("#validationErrors").append("<li><spring:message code='meterRead.msg.A4LTPCReadLess'/></li>");
					if((pageCountType == "<spring:message code='pageCntUploadLegend.scanHeader'/>"))
						jQuery("#validationErrors").append("<li><spring:message code='meterRead.msg.ScanReadLess'/></li>");
					if((pageCountType == "<spring:message code='pageCntUploadLegend.faxHeader'/>"))
						jQuery("#validationErrors").append("<li><spring:message code='meterRead.msg.FaxReadLess'/></li>");
					if((pageCountType == "<spring:message code='pageCntUploadLegend.blackHeader'/>"))
						jQuery("#validationErrors").append("<li><spring:message code='meterRead.msg.BlackReadLess'/></li>");
					if((pageCountType == "<spring:message code='pageCntUploadLegend.cyanHeader'/>"))
						jQuery("#validationErrors").append("<li><spring:message code='meterRead.msg.CyanReadLess'/></li>");
					if((pageCountType == "<spring:message code='pageCntUploadLegend.softwareHeader'/>"))
						jQuery("#validationErrors").append("<li><spring:message code='meterRead.msg.SoftwareReadLess'/></li>");
					if((pageCountType == "<spring:message code='pageCntUploadLegend.pgsScanCopyHeader'/>"))
						jQuery("#validationErrors").append("<li><spring:message code='meterRead.msg.PgsScanCopyReadLess'/></li>");
					if((pageCountType == "<spring:message code='pageCntUploadLegend.pgsScanFaxHeader'/>"))
						jQuery("#validationErrors").append("<li><spring:message code='meterRead.msg.PgsScanFaxReadLess'/></li>");
					if((pageCountType == "<spring:message code='pageCntUploadLegend.pgsScanNetworkHeader'/>"))
						jQuery("#validationErrors").append("<li><spring:message code='meterRead.msg.PgsScanNetworkReadLess'/></li>");
					if((pageCountType == "<spring:message code='pageCntUploadLegend.pgsScanUsbHeader'/>"))
						jQuery("#validationErrors").append("<li><spring:message code='meterRead.msg.PgsScanUsbReadLess'/></li>");
					if((pageCountType == "<spring:message code='pageCntUploadLegend.totalScansHeader'/>"))
						jQuery("#validationErrors").append("<li><spring:message code='meterRead.msg.TotalScansReadLess'/></li>");
					if((pageCountType == "<spring:message code='pageCntUploadLegend.letterColorHeader'/>"))
						jQuery("#validationErrors").append("<li><spring:message code='meterRead.msg.LetterColorReadLess'/></li>");
					if((pageCountType == "<spring:message code='pageCntUploadLegend.letterLTPCHeader'/>"))
						jQuery("#validationErrors").append("<li><spring:message code='meterRead.msg.LetterLTPCReadLess'/></li>");
					if((pageCountType == "<spring:message code='pageCntUploadLegend.a5ColorHeader'/>"))
						jQuery("#validationErrors").append("<li><spring:message code='meterRead.msg.A5ColorReadLess'/></li>");
					if((pageCountType == "<spring:message code='pageCntUploadLegend.a5LTPCHeader'/>"))
						jQuery("#validationErrors").append("<li><spring:message code='meterRead.msg.A5LTPCReadLess'/></li>");
					if((pageCountType == "<spring:message code='pageCntUploadLegend.legalColorHeader'/>"))
						jQuery("#validationErrors").append("<li><spring:message code='meterRead.msg.LegalColorReadLess'/></li>");
					if((pageCountType == "<spring:message code='pageCntUploadLegend.legalLTPCHeader'/>"))
						jQuery("#validationErrors").append("<li><spring:message code='meterRead.msg.LegalLTPCReadLess'/></li>");
					if((pageCountType == "<spring:message code='pageCntUploadLegend.statementColorHeader'/>"))
						jQuery("#validationErrors").append("<li><spring:message code='meterRead.msg.StatementColorReadLess'/></li>");
					if((pageCountType == "<spring:message code='pageCntUploadLegend.statementLTPCHeader'/>"))
						jQuery("#validationErrors").append("<li><spring:message code='meterRead.msg.StatementLTPCReadLess'/></li>");
					if((pageCountType == "<spring:message code='pageCntUploadLegend.tabloidColorHeader'/>"))
						jQuery("#validationErrors").append("<li><spring:message code='meterRead.msg.TabloidColorReadLess'/></li>");
					if((pageCountType == "<spring:message code='pageCntUploadLegend.tabloidLTPCHeader'/>"))
						jQuery("#validationErrors").append("<li><spring:message code='meterRead.msg.TabloidLTPCReadLess'/></li>");
					return false;				
				}
				else {
					//alert("11");
					ltpcDiff=parseInt(newPgCntVal) - parseInt(lastPgCnt1);				
					if(ltpcDiff > 50000)
					{	
						//alert("12");
						jQuery("#validationErrors").show();
						jQuery("#validationErrors").html("");
						if((pageCountType == "<spring:message code='pageCntUpload.ltpc'/>"))
							jQuery("#validationErrors").append("<li><spring:message code='meterRead.msg.unreasonableLTPCHigh'/></li>");					
						if((pageCountType == "<spring:message code='pageCntUpload.color'/>"))
							jQuery("#validationErrors").append("<li><spring:message code='meterRead.msg.unreasonableColorReadHigh'/></li>");					
						if((pageCountType == "<spring:message code='pageCntUploadLegend.a3ColorHeader'/>"))
							jQuery("#validationErrors").append("<li><spring:message code='meterRead.msg.unreasonableA3ColorHigh'/></li>");					
						if((pageCountType == "<spring:message code='pageCntUploadLegend.a3LTPCHeader'/>"))
							jQuery("#validationErrors").append("<li><spring:message code='meterRead.msg.unreasonableA3LTPCHigh'/></li>");					
						if((pageCountType == "<spring:message code='pageCntUploadLegend.a4ColorHeader'/>"))
							jQuery("#validationErrors").append("<li><spring:message code='meterRead.msg.unreasonableA4ColorHigh'/></li>");					
						if((pageCountType == "<spring:message code='pageCntUploadLegend.a4LTPCHeader'/>"))
							jQuery("#validationErrors").append("<li><spring:message code='meterRead.msg.unreasonableA4LTPCHigh'/></li>");					
						if((pageCountType == "<spring:message code='pageCntUploadLegend.scanHeader'/>"))
							jQuery("#validationErrors").append("<li><spring:message code='meterRead.msg.unreasonableScanHigh'/></li>");					
						if((pageCountType == "<spring:message code='pageCntUploadLegend.faxHeader'/>"))
							jQuery("#validationErrors").append("<li><spring:message code='meterRead.msg.unreasonableFaxHigh'/></li>");
						if((pageCountType == "<spring:message code='pageCntUploadLegend.blackHeader'/>"))
							jQuery("#validationErrors").append("<li><spring:message code='meterRead.msg.unreasonableBlackHigh'/></li>");
						if((pageCountType == "<spring:message code='pageCntUploadLegend.cyanHeader'/>"))
							jQuery("#validationErrors").append("<li><spring:message code='meterRead.msg.unreasonableCyanHigh'/></li>");
						if((pageCountType == "<spring:message code='pageCntUploadLegend.softwareHeader'/>"))
							jQuery("#validationErrors").append("<li><spring:message code='meterRead.msg.unreasonableSoftwareHigh'/></li>");
						if((pageCountType == "<spring:message code='pageCntUploadLegend.pgsScanCopyHeader'/>"))
							jQuery("#validationErrors").append("<li><spring:message code='meterRead.msg.unreasonablePgsScanCopyHigh'/></li>");
						if((pageCountType == "<spring:message code='pageCntUploadLegend.pgsScanFaxHeader'/>"))
							jQuery("#validationErrors").append("<li><spring:message code='meterRead.msg.unreasonablePgsScanFaxHigh'/></li>");
						if((pageCountType == "<spring:message code='pageCntUploadLegend.pgsScanNetworkHeader'/>"))
							jQuery("#validationErrors").append("<li><spring:message code='meterRead.msg.unreasonablePgsScanNetworkHigh'/></li>");
						if((pageCountType == "<spring:message code='pageCntUploadLegend.pgsScanUsbHeader'/>"))
							jQuery("#validationErrors").append("<li><spring:message code='meterRead.msg.unreasonablePgsScanUsbHigh'/></li>");
						if((pageCountType == "<spring:message code='pageCntUploadLegend.totalScansHeader'/>"))
							jQuery("#validationErrors").append("<li><spring:message code='meterRead.msg.unreasonableTotalScansHigh'/></li>");
						if((pageCountType == "<spring:message code='pageCntUploadLegend.letterColorHeader'/>"))
							jQuery("#validationErrors").append("<li><spring:message code='meterRead.msg.unreasonableLetterColorHigh'/></li>");
						if((pageCountType == "<spring:message code='pageCntUploadLegend.letterLTPCHeader'/>"))
							jQuery("#validationErrors").append("<li><spring:message code='meterRead.msg.unreasonableLetterLTPCHigh'/></li>");
						if((pageCountType == "<spring:message code='pageCntUploadLegend.a5ColorHeader'/>"))
							jQuery("#validationErrors").append("<li><spring:message code='meterRead.msg.unreasonableA5ColorHigh'/></li>");
						if((pageCountType == "<spring:message code='pageCntUploadLegend.a5LTPCHeader'/>"))
							jQuery("#validationErrors").append("<li><spring:message code='meterRead.msg.unreasonableA5LTPCHigh'/></li>");
						if((pageCountType == "<spring:message code='pageCntUploadLegend.legalColorHeader'/>"))
							jQuery("#validationErrors").append("<li><spring:message code='meterRead.msg.unreasonableLegalColorHigh'/></li>");
						if((pageCountType == "<spring:message code='pageCntUploadLegend.legalLTPCHeader'/>"))
							jQuery("#validationErrors").append("<li><spring:message code='meterRead.msg.unreasonableLegalLTPCHigh'/></li>");
						if((pageCountType == "<spring:message code='pageCntUploadLegend.statementColorHeader'/>"))
							jQuery("#validationErrors").append("<li><spring:message code='meterRead.msg.unreasonableStatementColorHigh'/></li>");
						if((pageCountType == "<spring:message code='pageCntUploadLegend.statementLTPCHeader'/>"))
							jQuery("#validationErrors").append("<li><spring:message code='meterRead.msg.unreasonableStatementLTPCHigh'/></li>");
						if((pageCountType == "<spring:message code='pageCntUploadLegend.tabloidColorHeader'/>"))
							jQuery("#validationErrors").append("<li><spring:message code='meterRead.msg.unreasonableTabloidColorHigh'/></li>");
						if((pageCountType == "<spring:message code='pageCntUploadLegend.tabloidLTPCHeader'/>"))
							jQuery("#validationErrors").append("<li><spring:message code='meterRead.msg.unreasonableTabloidLTPCHigh'/></li>");
						return false;
					}
					/*This section is for ltpc count diff lt than 10, so reject*/
					if(ltpcDiff<10)
					{	
						//alert("13");
						jQuery("#validationErrors").show();
						jQuery("#validationErrors").html("");					
						if((pageCountType == "<spring:message code='pageCntUpload.ltpc'/>"))
							jQuery("#validationErrors").append("<li><spring:message code='meterRead.msg.unreasonableLTPCLow'/></li>");					
						if((pageCountType == "<spring:message code='pageCntUpload.color'/>"))
							jQuery("#validationErrors").append("<li><spring:message code='meterRead.msg.unreasonableColorReadLow'/></li>");					
						if((pageCountType == "<spring:message code='pageCntUploadLegend.a3ColorHeader'/>"))
							jQuery("#validationErrors").append("<li><spring:message code='meterRead.msg.unreasonableA3ColorReadLow'/></li>");					
						if((pageCountType == "<spring:message code='pageCntUploadLegend.a3LTPCHeader'/>"))
							jQuery("#validationErrors").append("<li><spring:message code='meterRead.msg.unreasonableA3LTPCReadLow'/></li>");					
						if((pageCountType == "<spring:message code='pageCntUploadLegend.a4ColorHeader'/>"))
							jQuery("#validationErrors").append("<li><spring:message code='meterRead.msg.unreasonableA4ColorReadLow'/></li>");					
						if((pageCountType == "<spring:message code='pageCntUploadLegend.a4LTPCHeader'/>"))
							jQuery("#validationErrors").append("<li><spring:message code='meterRead.msg.unreasonableA4LTPCReadLow'/></li>");					
						if((pageCountType == "<spring:message code='pageCntUploadLegend.scanHeader'/>"))
							jQuery("#validationErrors").append("<li><spring:message code='meterRead.msg.unreasonableScanReadLow'/></li>");					
						if((pageCountType == "<spring:message code='pageCntUploadLegend.faxHeader'/>"))
							jQuery("#validationErrors").append("<li><spring:message code='meterRead.msg.unreasonableFaxReadLow'/></li>");
						if((pageCountType == "<spring:message code='pageCntUploadLegend.blackHeader'/>"))
							jQuery("#validationErrors").append("<li><spring:message code='meterRead.msg.unreasonableBlackReadLow'/></li>");
						if((pageCountType == "<spring:message code='pageCntUploadLegend.cyanHeader'/>"))
							jQuery("#validationErrors").append("<li><spring:message code='meterRead.msg.unreasonableCyanReadLow'/></li>");
						if((pageCountType == "<spring:message code='pageCntUploadLegend.softwareHeader'/>"))
							jQuery("#validationErrors").append("<li><spring:message code='meterRead.msg.unreasonableSoftwareReadLow'/></li>");
						if((pageCountType == "<spring:message code='pageCntUploadLegend.pgsScanCopyHeader'/>"))
							jQuery("#validationErrors").append("<li><spring:message code='meterRead.msg.unreasonablePgsScanCopyReadLow'/></li>");
						if((pageCountType == "<spring:message code='pageCntUploadLegend.pgsScanFaxHeader'/>"))
							jQuery("#validationErrors").append("<li><spring:message code='meterRead.msg.unreasonablePgsScanFaxReadLow'/></li>");
						if((pageCountType == "<spring:message code='pageCntUploadLegend.pgsScanNetworkHeader'/>"))
							jQuery("#validationErrors").append("<li><spring:message code='meterRead.msg.unreasonablePgsScanNetworkReadLow'/></li>");
						if((pageCountType == "<spring:message code='pageCntUploadLegend.pgsScanUsbHeader'/>"))
							jQuery("#validationErrors").append("<li><spring:message code='meterRead.msg.unreasonablePgsScanUsbReadLow'/></li>");
						if((pageCountType == "<spring:message code='pageCntUploadLegend.totalScansHeader'/>"))
							jQuery("#validationErrors").append("<li><spring:message code='meterRead.msg.unreasonableTotalScansReadLow'/></li>");
						if((pageCountType == "<spring:message code='pageCntUploadLegend.letterColorHeader'/>"))
							jQuery("#validationErrors").append("<li><spring:message code='meterRead.msg.unreasonableLetterColorReadLow'/></li>");
						if((pageCountType == "<spring:message code='pageCntUploadLegend.letterLTPCHeader'/>"))
							jQuery("#validationErrors").append("<li><spring:message code='meterRead.msg.unreasonableLetterLTPCReadLow'/></li>");
						if((pageCountType == "<spring:message code='pageCntUploadLegend.a5ColorHeader'/>"))
							jQuery("#validationErrors").append("<li><spring:message code='meterRead.msg.unreasonableA5ColorReadLow'/></li>");
						if((pageCountType == "<spring:message code='pageCntUploadLegend.a5LTPCHeader'/>"))
							jQuery("#validationErrors").append("<li><spring:message code='meterRead.msg.unreasonableA5LTPCReadLow'/></li>");
						if((pageCountType == "<spring:message code='pageCntUploadLegend.legalColorHeader'/>"))
							jQuery("#validationErrors").append("<li><spring:message code='meterRead.msg.unreasonableLegalColorReadLow'/></li>");
						if((pageCountType == "<spring:message code='pageCntUploadLegend.legalLTPCHeader'/>"))
							jQuery("#validationErrors").append("<li><spring:message code='meterRead.msg.unreasonableLegalLTPCReadLow'/></li>");
						if((pageCountType == "<spring:message code='pageCntUploadLegend.statementColorHeader'/>"))
							jQuery("#validationErrors").append("<li><spring:message code='meterRead.msg.unreasonableStatementColorReadLow'/></li>");
						if((pageCountType == "<spring:message code='pageCntUploadLegend.statementLTPCHeader'/>"))
							jQuery("#validationErrors").append("<li><spring:message code='meterRead.msg.unreasonableStatementLTPCReadLow'/></li>");
						if((pageCountType == "<spring:message code='pageCntUploadLegend.tabloidColorHeader'/>"))
							jQuery("#validationErrors").append("<li><spring:message code='meterRead.msg.unreasonableTabloidColorReadLow'/></li>");
						if((pageCountType == "<spring:message code='pageCntUploadLegend.tabloidLTPCHeader'/>"))
							jQuery("#validationErrors").append("<li><spring:message code='meterRead.msg.unreasonableTabloidLTPCReadLow'/></li>");
						return false;
					}				
					/*This section is for ltpc count diff gt than 50000, so reject*/				
					if(ltpcDiff > (no_of_day_diff*2000))
					{	
						//alert("14");
						jQuery("#validationErrors").show();
						jQuery("#validationErrors").html("");	
						if((pageCountType == "<spring:message code='pageCntUpload.ltpc'/>"))				
							jQuery("#validationErrors").append("<li><spring:message code='meterRead.msg.unreasonableLTPCHigh'/></li>");					
						if((pageCountType == "<spring:message code='pageCntUpload.color'/>"))
							jQuery("#validationErrors").append("<li><spring:message code='meterRead.msg.unreasonableColorReadHigh'/></li>");					
						if((pageCountType == "<spring:message code='pageCntUploadLegend.a3ColorHeader'/>"))
							jQuery("#validationErrors").append("<li><spring:message code='meterRead.msg.unreasonableA3ColorHigh'/></li>");					
						if((pageCountType == "<spring:message code='pageCntUploadLegend.a3LTPCHeader'/>"))
							jQuery("#validationErrors").append("<li><spring:message code='meterRead.msg.unreasonableA3LTPCHigh'/></li>");					
						if((pageCountType == "<spring:message code='pageCntUploadLegend.a4ColorHeader'/>"))
							jQuery("#validationErrors").append("<li><spring:message code='meterRead.msg.unreasonableA4ColorHigh'/></li>");					
						if((pageCountType == "<spring:message code='pageCntUploadLegend.a4LTPCHeader'/>"))
							jQuery("#validationErrors").append("<li><spring:message code='meterRead.msg.unreasonableA4LTPCHigh'/></li>");					
						if((pageCountType == "<spring:message code='pageCntUploadLegend.scanHeader'/>"))
							jQuery("#validationErrors").append("<li><spring:message code='meterRead.msg.unreasonableScanHigh'/></li>");					
						if((pageCountType == "<spring:message code='pageCntUploadLegend.faxHeader'/>"))
							jQuery("#validationErrors").append("<li><spring:message code='meterRead.msg.unreasonableFaxHigh'/></li>");
						if((pageCountType == "<spring:message code='pageCntUploadLegend.blackHeader'/>"))
							jQuery("#validationErrors").append("<li><spring:message code='meterRead.msg.unreasonableBlackHigh'/></li>");
						if((pageCountType == "<spring:message code='pageCntUploadLegend.cyanHeader'/>"))
							jQuery("#validationErrors").append("<li><spring:message code='meterRead.msg.unreasonableCyanHigh'/></li>");
						if((pageCountType == "<spring:message code='pageCntUploadLegend.softwareHeader'/>"))
							jQuery("#validationErrors").append("<li><spring:message code='meterRead.msg.unreasonableSoftwareHigh'/></li>");
						if((pageCountType == "<spring:message code='pageCntUploadLegend.pgsScanCopyHeader'/>"))
							jQuery("#validationErrors").append("<li><spring:message code='meterRead.msg.unreasonablePgsScanCopyHigh'/></li>");
						if((pageCountType == "<spring:message code='pageCntUploadLegend.pgsScanFaxHeader'/>"))
							jQuery("#validationErrors").append("<li><spring:message code='meterRead.msg.unreasonablePgsScanFaxHigh'/></li>");
						if((pageCountType == "<spring:message code='pageCntUploadLegend.pgsScanNetworkHeader'/>"))
							jQuery("#validationErrors").append("<li><spring:message code='meterRead.msg.unreasonablePgsScanNetworkHigh'/></li>");
						if((pageCountType == "<spring:message code='pageCntUploadLegend.pgsScanUsbHeader'/>"))
							jQuery("#validationErrors").append("<li><spring:message code='meterRead.msg.unreasonablePgsScanUsbHigh'/></li>");
						if((pageCountType == "<spring:message code='pageCntUploadLegend.totalScansHeader'/>"))
							jQuery("#validationErrors").append("<li><spring:message code='meterRead.msg.unreasonableTotalScansHigh'/></li>");
						if((pageCountType == "<spring:message code='pageCntUploadLegend.letterColorHeader'/>"))
							jQuery("#validationErrors").append("<li><spring:message code='meterRead.msg.unreasonableLetterColorHigh'/></li>");
						if((pageCountType == "<spring:message code='pageCntUploadLegend.letterLTPCHeader'/>"))
							jQuery("#validationErrors").append("<li><spring:message code='meterRead.msg.unreasonableLetterLTPCHigh'/></li>");
						if((pageCountType == "<spring:message code='pageCntUploadLegend.a5ColorHeader'/>"))
							jQuery("#validationErrors").append("<li><spring:message code='meterRead.msg.unreasonableA5ColorHigh'/></li>");
						if((pageCountType == "<spring:message code='pageCntUploadLegend.a5LTPCHeader'/>"))
							jQuery("#validationErrors").append("<li><spring:message code='meterRead.msg.unreasonableA5LTPCHigh'/></li>");
						if((pageCountType == "<spring:message code='pageCntUploadLegend.legalColorHeader'/>"))
							jQuery("#validationErrors").append("<li><spring:message code='meterRead.msg.unreasonableLegalColorHigh'/></li>");
						if((pageCountType == "<spring:message code='pageCntUploadLegend.statementColorHeader'/>"))
							jQuery("#validationErrors").append("<li><spring:message code='meterRead.msg.unreasonableStatementColorHigh'/></li>");
						if((pageCountType == "<spring:message code='pageCntUploadLegend.statementLTPCHeader'/>"))
							jQuery("#validationErrors").append("<li><spring:message code='meterRead.msg.unreasonableStatementLTPCHigh'/></li>");
						if((pageCountType == "<spring:message code='pageCntUploadLegend.tabloidColorHeader'/>"))
							jQuery("#validationErrors").append("<li><spring:message code='meterRead.msg.unreasonableTabloidColorHigh'/></li>");
						if((pageCountType == "<spring:message code='pageCntUploadLegend.tabloidLTPCHeader'/>"))
							jQuery("#validationErrors").append("<li><spring:message code='meterRead.msg.unreasonableTabloidLTPCHigh'/></li>");
						if((pageCountType == "<spring:message code='pageCntUploadLegend.legalLTPCHeader'/>"))
							jQuery("#validationErrors").append("<li><spring:message code='meterRead.msg.unreasonableLegalLTPCHigh'/></li>");
						return false;
					}
				}
				
			}
		}
		//Checking the values of Color page count and lTPC page count
		//alert("within");
		
		/* Added for defect #10673 */
		page=1;
		pageCountGrid.changePage(page);
		page++;
		/* End */
		for(var i=0;i<numRowsInGrid;i++){
			if(i%10==0 && i!=0){
				
				pageCountGrid.changePage(page);
				page++;
			}
			//alert("in for loop "+numRowsInGrid);
			var cellObj = pageCountGrid.cellById(i,0); //fetching the first cell value
			var pageCountType=cellObj.getValue();
			//alert("pageCountType "+pageCountType);
			var cellObj1 = pageCountGrid.cellById(i,2);
			var prevPageCountVal = cellObj1.getValue();
			//alert("prevPageCountVal "+prevPageCountVal);	
			if((pageCountType == "<spring:message code='pageCntUpload.ltpc'/>")){				
				var newPgCntValLTPC = jQuery("#currentPageCount"+i).val();	
				var k = i+1;
				dataForLTPC[1] = newPgCntValLTPC; // ltpc page count
				dataForLTPC[0] = formatDateToDefault(jQuery("#rwid_"+k).val()); //ltpc date
				//alert("LTPC" + dataForLTPC[0]);
				if(dataForLTPC[0] == ''){
					dataForLTPC[0] = "";
				}else{
				//alert("date: "+dataForLTPC[0]);
				dataForLTPC[0] = convertDateToGMT(dataForLTPC[0]);
				//alert("date in gmt ... "+dataForLTPC[0]);
				}
			}		
			if((pageCountType == "<spring:message code='pageCntUpload.color'/>")){
				var newPgCntValColor = jQuery("#currentPageCount"+i).val();	
				var j = i+1;
				dataForColor[1] = newPgCntValColor; //color page count
				dataForColor[0] = formatDateToDefault(jQuery("#rwid_"+j).val()); //color date
				
				if(dataForColor[0] == ''){
					dataForColor[0] = "";
				}else{
				dataForColor[0] = convertDateToGMT(dataForColor[0]);
				}
			}				
			if((pageCountType == "<spring:message code='pageCntUploadLegend.a3ColorHeader'/>")){
				var newPgCntValA3Color = jQuery("#currentPageCount"+i).val();	
				var l = i+1;
				dataForA3Color[1] = newPgCntValA3Color;
				dataForA3Color[0] = formatDateToDefault(jQuery("#rwid_"+l).val());
				if(dataForA3Color[0] == ''){
					dataForA3Color[0] = "";
				}else{
				dataForA3Color[0] = convertDateToGMT(dataForA3Color[0]);
				}
			}				
			if((pageCountType == "<spring:message code='pageCntUploadLegend.a3LTPCHeader'/>")){
				var newPgCntValA3LTPC = jQuery("#currentPageCount"+i).val();	
				var m = i+1;
				dataForA3LTPC[1] = newPgCntValA3LTPC;
				dataForA3LTPC[0] = formatDateToDefault(jQuery("#rwid_"+m).val());
				if(dataForA3LTPC[0] == ''){
					dataForA3LTPC[0] = "";
				}else{
				dataForA3LTPC[0] = convertDateToGMT(dataForA3LTPC[0]);
				}
			}			
			if((pageCountType == "<spring:message code='pageCntUploadLegend.a4ColorHeader'/>")){
				var newPgCntValA4Color = jQuery("#currentPageCount"+i).val();
				var n = i+1;
				dataForA4Color[1] = newPgCntValA4Color;
				dataForA4Color[0] = formatDateToDefault(jQuery("#rwid_"+n).val());
				
				if(dataForA4Color[0] == ''){
					dataForA4Color[0] = "";
				}else{
				dataForA4Color[0] = convertDateToGMT(dataForA4Color[0]);
				}
			}				
			if((pageCountType == "<spring:message code='pageCntUploadLegend.a4LTPCHeader'/>")){
				var newPgCntValA4LTPC = jQuery("#currentPageCount"+i).val();
				var p = i+1;
				dataForA4LTPC[1] = newPgCntValA4LTPC;
				dataForA4LTPC[0] = formatDateToDefault(jQuery("#rwid_"+p).val());
				
				if(dataForA4LTPC[0] == ''){
					dataForA4LTPC[0] = "";
				}else{
				dataForA4LTPC[0] = convertDateToGMT(dataForA4LTPC[0]);
				}
			}
				
			if((pageCountType == "<spring:message code='pageCntUploadLegend.scanHeader'/>")){
				var newPgCntValScan = jQuery("#currentPageCount"+i).val();
				var q = i+1;
				dataForScan[1] = newPgCntValScan;
				dataForScan[0] = formatDateToDefault(jQuery("#rwid_"+q).val());
				if(dataForScan[0] == ''){
					dataForScan[0] = "";
				}else{
				dataForScan[0] = convertDateToGMT(dataForScan[0]);
				}
			}	
			if((pageCountType == "<spring:message code='pageCntUploadLegend.faxHeader'/>")){
				var newPgCntValFax = jQuery("#currentPageCount"+i).val();
				var r = i+1;
				dataForFax[1] = newPgCntValFax;
				dataForFax[0] = formatDateToDefault(jQuery("#rwid_"+r).val());
				if(dataForFax[0] == ''){
					dataForFax[0] = "";
				}else{
				dataForFax[0] = convertDateToGMT(dataForFax[0]);
				}
			}
			if((pageCountType == "<spring:message code='pageCntUploadLegend.blackHeader'/>")){
				var newPgCntValBlack = jQuery("#currentPageCount"+i).val();
				var o = i+1;
				dataForBlack[1] = newPgCntValBlack;
				dataForBlack[0] = formatDateToDefault(jQuery("#rwid_"+o).val());
				if(dataForBlack[0] == ''){
					dataForBlack[0] = "";
				}else{
				dataForBlack[0] = convertDateToGMT(dataForBlack[0]);
				}
			}
			if((pageCountType == "<spring:message code='pageCntUploadLegend.cyanHeader'/>")){
				var newPgCntValCyan = jQuery("#currentPageCount"+i).val();
				var t = i+1;
				dataForCyan[1] = newPgCntValCyan;
				dataForCyan[0] = formatDateToDefault(jQuery("#rwid_"+t).val());
				if(dataForCyan[0] == ''){
					dataForCyan[0] = "";
				}else{
					dataForCyan[0] = convertDateToGMT(dataForCyan[0]);
				}
			}
			if((pageCountType == "<spring:message code='pageCntUploadLegend.softwareHeader'/>")){
				var newPgCntValSoftware = jQuery("#currentPageCount"+i).val();
				var u = i+1;
				dataForSoftware[1] = newPgCntValSoftware;
				dataForSoftware[0] = formatDateToDefault(jQuery("#rwid_"+u).val());
				if(dataForSoftware[0] == ''){
					dataForSoftware[0] = "";
				}else{
					dataForSoftware[0] = convertDateToGMT(dataForSoftware[0]);
				}
			}
			if((pageCountType == "<spring:message code='pageCntUploadLegend.pgsScanCopyHeader'/>")){
				var newPgCntValPgsScanCopy = jQuery("#currentPageCount"+i).val();
				var v = i+1;
				dataForPgsScanCopy[1] = newPgCntValPgsScanCopy;
				dataForPgsScanCopy[0] = formatDateToDefault(jQuery("#rwid_"+v).val());
				if(dataForPgsScanCopy[0] == ''){
					dataForPgsScanCopy[0] = "";
				}else{
					dataForPgsScanCopy[0] = convertDateToGMT(dataForPgsScanCopy[0]);
				}
			}
			if((pageCountType == "<spring:message code='pageCntUploadLegend.pgsScanFaxHeader'/>")){
				var newPgCntValPgsScanFax = jQuery("#currentPageCount"+i).val();
				var w = i+1;
				dataForPgsScanFax[1] = newPgCntValPgsScanFax;
				dataForPgsScanFax[0] = formatDateToDefault(jQuery("#rwid_"+w).val());
				if(dataForPgsScanFax[0] == ''){
					dataForPgsScanFax[0] = "";
				}else{
					dataForPgsScanFax[0] = convertDateToGMT(dataForPgsScanFax[0]);
				}
			}
			if((pageCountType == "<spring:message code='pageCntUploadLegend.pgsScanNetworkHeader'/>")){
				var newPgCntValPgsScanNetwork = jQuery("#currentPageCount"+i).val();
				var x = i+1;
				dataForPgsScanNetwork[1] = newPgCntValPgsScanNetwork;
				dataForPgsScanNetwork[0] = formatDateToDefault(jQuery("#rwid_"+x).val());
				if(dataForPgsScanNetwork[0] == ''){
					dataForPgsScanNetwork[0] = "";
				}else{
					dataForPgsScanNetwork[0] = convertDateToGMT(dataForPgsScanNetwork[0]);
				}
			}
			if((pageCountType == "<spring:message code='pageCntUploadLegend.pgsScanUsbHeader'/>")){
				var newPgCntValPgsScanUsb = jQuery("#currentPageCount"+i).val();
				var y = i+1;
				dataForPgsScanUsb[1] = newPgCntValPgsScanUsb;
				dataForPgsScanUsb[0] = formatDateToDefault(jQuery("#rwid_"+y).val());
				if(dataForPgsScanUsb[0] == ''){
					dataForPgsScanUsb[0] = "";
				}else{
					dataForPgsScanUsb[0] = convertDateToGMT(dataForPgsScanUsb[0]);
				}
			}
			if((pageCountType == "<spring:message code='pageCntUploadLegend.totalScansHeader'/>")){
				var newPgCntValTotalScans = jQuery("#currentPageCount"+i).val();
				var z = i+1;
				dataForTotalScans[1] = newPgCntValTotalScans;
				dataForTotalScans[0] = formatDateToDefault(jQuery("#rwid_"+z).val());
				if(dataForTotalScans[0] == ''){
					dataForTotalScans[0] = "";
				}else{
					dataForTotalScans[0] = convertDateToGMT(dataForTotalScans[0]);
				}
			}
			if((pageCountType == "<spring:message code='pageCntUploadLegend.letterColorHeader'/>")){
				var newPgCntValLetterColor = jQuery("#currentPageCount"+i).val();
				var a = i+1;
				dataForLetterColor[1] = newPgCntValLetterColor;
				dataForLetterColor[0] = formatDateToDefault(jQuery("#rwid_"+a).val());
				if(dataForLetterColor[0] == ''){
					dataForLetterColor[0] = "";
				}else{
					dataForLetterColor[0] = convertDateToGMT(dataForLetterColor[0]);
				}
			}
			if((pageCountType == "<spring:message code='pageCntUploadLegend.letterLTPCHeader'/>")){
				var newPgCntValLetterLTPC = jQuery("#currentPageCount"+i).val();
				var b = i+1;
				dataForLetterLTPC[1] = newPgCntValLetterLTPC;
				dataForLetterLTPC[0] = formatDateToDefault(jQuery("#rwid_"+b).val());
				if(dataForLetterLTPC[0] == ''){
					dataForLetterLTPC[0] = "";
				}else{
					dataForLetterLTPC[0] = convertDateToGMT(dataForLetterLTPC[0]);
				}
			}
			if((pageCountType == "<spring:message code='pageCntUploadLegend.a5ColorHeader'/>")){
				var newPgCntValA5Color = jQuery("#currentPageCount"+i).val();
				var c = i+1;
				dataForA5Color[1] = newPgCntValA5Color;
				dataForA5Color[0] = formatDateToDefault(jQuery("#rwid_"+c).val());
				if(dataForA5Color[0] == ''){
					dataForA5Color[0] = "";
				}else{
					dataForA5Color[0] = convertDateToGMT(dataForA5Color[0]);
				}
			}
			if((pageCountType == "<spring:message code='pageCntUploadLegend.a5LTPCHeader'/>")){
				var newPgCntValA5LTPC = jQuery("#currentPageCount"+i).val();
				var d = i+1;
				dataForA5LTPC[1] = newPgCntValA5LTPC;
				dataForA5LTPC[0] = formatDateToDefault(jQuery("#rwid_"+d).val());
				if(dataForA5LTPC[0] == ''){
					dataForA5LTPC[0] = "";
				}else{
					dataForA5LTPC[0] = convertDateToGMT(dataForA5LTPC[0]);
				}
			}
			if((pageCountType == "<spring:message code='pageCntUploadLegend.legalColorHeader'/>")){
				var newPgCntValLegalColor = jQuery("#currentPageCount"+i).val();
				var e = i+1;
				dataForLegalColor[1] = newPgCntValLegalColor;
				dataForLegalColor[0] = formatDateToDefault(jQuery("#rwid_"+e).val());
				if(dataForLegalColor[0] == ''){
					dataForLegalColor[0] = "";
				}else{
					dataForLegalColor[0] = convertDateToGMT(dataForLegalColor[0]);
				}
			}
			if((pageCountType == "<spring:message code='pageCntUploadLegend.legalLTPCHeader'/>")){
				var newPgCntValLegalLTPC = jQuery("#currentPageCount"+i).val();
				var f = i+1;
				dataForLegalLTPC[1] = newPgCntValLegalLTPC;
				dataForLegalLTPC[0] = formatDateToDefault(jQuery("#rwid_"+f).val());
				if(dataForLegalLTPC[0] == ''){
					dataForLegalLTPC[0] = "";
				}else{
					dataForLegalLTPC[0] = convertDateToGMT(dataForLegalLTPC[0]);
				}
			}
			if((pageCountType == "<spring:message code='pageCntUploadLegend.statementColorHeader'/>")){
				var newPgCntValStatementColor = jQuery("#currentPageCount"+i).val();
				var g = i+1;
				dataForStatementColor[1] = newPgCntValStatementColor;
				dataForStatementColor[0] = formatDateToDefault(jQuery("#rwid_"+g).val());
				if(dataForStatementColor[0] == ''){
					dataForStatementColor[0] = "";
				}else{
					dataForStatementColor[0] = convertDateToGMT(dataForStatementColor[0]);
				}
			}
			if((pageCountType == "<spring:message code='pageCntUploadLegend.statementLTPCHeader'/>")){
				var newPgCntValStatementLTPC = jQuery("#currentPageCount"+i).val();
				var h = i+1;
				dataForStatementLTPC[1] = newPgCntValStatementLTPC;
				dataForStatementLTPC[0] = formatDateToDefault(jQuery("#rwid_"+h).val());
				if(dataForStatementLTPC[0] == ''){
					dataForStatementLTPC[0] = "";
				}else{
					dataForStatementLTPC[0] = convertDateToGMT(dataForStatementLTPC[0]);
				}
			}
			if((pageCountType == "<spring:message code='pageCntUploadLegend.tabloidColorHeader'/>")){
				var newPgCntValTabloidColor = jQuery("#currentPageCount"+i).val();
				var s = i+1;
				dataForTabloidColor[1] = newPgCntValTabloidColor;
				dataForTabloidColor[0] = formatDateToDefault(jQuery("#rwid_"+s).val());
				if(dataForTabloidColor[0] == ''){
					dataForTabloidColor[0] = "";
				}else{
					dataForTabloidColor[0] = convertDateToGMT(dataForTabloidColor[0]);
				}
			}
			if((pageCountType == "<spring:message code='pageCntUploadLegend.tabloidLTPCHeader'/>")){
				var newPgCntValTabloidLTPC = jQuery("#currentPageCount"+i).val();
				var v1 = i+1;
				dataForTabloidLTPC[1] = newPgCntValTabloidLTPC;
				dataForTabloidLTPC[0] = formatDateToDefault(jQuery("#rwid_"+v1).val());
				if(dataForTabloidLTPC[0] == ''){
					dataForTabloidLTPC[0] = "";
				}else{
					dataForTabloidLTPC[0] = convertDateToGMT(dataForTabloidLTPC[0]);
				}
			}
		}
		
		/*alert("newPgCntValLTPC "+newPgCntValLTPC);
		alert("newPgCntValColor "+newPgCntValColor);
		alert("newPgCntValA3Color "+newPgCntValA3Color);
		alert("newPgCntValA3LTPC "+newPgCntValA3LTPC);
		alert("newPgCntValA4Color "+newPgCntValA4Color);
		alert("newPgCntValA4LTPC "+newPgCntValA4LTPC);*/
		if((newPgCntValLTPC != 'undefined') && (newPgCntValLTPC != null) && (newPgCntValLTPC != 'null') && (newPgCntValLTPC != '')){
			//alert("11144");
			//alert("newPgCntValColor.length "+newPgCntValColor.length);
			if((newPgCntValColor != 'undefined') && (newPgCntValColor != null) && (newPgCntValColor != 'null') && (newPgCntValColor != '')){ 
				//alert("qwert");
			if((parseInt(newPgCntValColor)) > (parseInt(newPgCntValLTPC)))
			{	
					//alert("1234");
				jQuery("#validationErrors").show();
				jQuery("#validationErrors").html("");				
				jQuery("#validationErrors").append("<li><spring:message code='meterRead.msg.colorReadDifference'/></li>");				
				
				return false;
				}
			}
		}
		else
		{ //alert("4567");
		if((newPgCntValColor != 'undefined') && (newPgCntValColor != null) && (newPgCntValColor != 'null') && (newPgCntValColor != '')){ 
			if((parseInt(newPgCntValColor)) < (parseInt(prevPageCountVal)))
			{	
				jQuery("#validationErrors").show();
				jQuery("#validationErrors").html("");				
				jQuery("#validationErrors").append("<li><spring:message code='meterRead.msg.colorReadDifference'/></li>");				
				
				return false;
				}
			}
		}
		if((newPgCntValA3LTPC != 'undefined') && (newPgCntValA3LTPC != null) && (newPgCntValA3LTPC != 'null') || (newPgCntValA3LTPC != '')){ 
			if((newPgCntValA3Color != 'undefined') && (newPgCntValA3Color != null) && (newPgCntValA3Color != 'null') && (newPgCntValA3Color != '')){
				if((parseInt(newPgCntValA3Color)) > (parseInt(newPgCntValA3LTPC)))
				{	
					jQuery("#validationErrors").show();
					jQuery("#validationErrors").html("");				
					jQuery("#validationErrors").append("<li><spring:message code='meterRead.msg.A3colorReadDifference'/></li>");				
				
					return false;
				}
			}
		}
		else
		{ //alert("1111");
			if((newPgCntValA3Color != 'undefined') && (newPgCntValA3Color != null) && (newPgCntValA3Color != 'null') && (newPgCntValA3Color != '')){
				if((parseInt(newPgCntValA3Color)) < (parseInt(prevPageCountVal)))
				{	
					jQuery("#validationErrors").show();
					jQuery("#validationErrors").html("");				
					jQuery("#validationErrors").append("<li><spring:message code='meterRead.msg.colorReadDifference'/></li>");				
					
					return false;
				}
			}
		}

		if((newPgCntValA4LTPC != 'undefined') && (newPgCntValA4LTPC != null) && (newPgCntValA4LTPC != 'null') && (newPgCntValA4LTPC != '')){
			if((newPgCntValA4Color != 'undefined') && (newPgCntValA4Color != null) && (newPgCntValA4Color != 'null') && (newPgCntValA4Color != '')){
				//alert("46456");	
				if((parseInt(newPgCntValA4Color)) > (parseInt(newPgCntValA4LTPC)))
				{	
					jQuery("#validationErrors").show();
					jQuery("#validationErrors").html("");				
					jQuery("#validationErrors").append("<li><spring:message code='meterRead.msg.A4colorReadDifference'/></li>");				
					
					return false;
				}
			}
		}
		else
		{ //alert("tgeryer");	
		if((newPgCntValA4Color != 'undefined') && (newPgCntValA4Color != null) && (newPgCntValA4Color != 'null') && (newPgCntValA4Color != '')){
				if((parseInt(newPgCntValA4Color)) < (parseInt(prevPageCountVal)))
				{	
					jQuery("#validationErrors").show();
					jQuery("#validationErrors").html("");				
					jQuery("#validationErrors").append("<li><spring:message code='meterRead.msg.colorReadDifference'/></li>");				
					
					return false;
				}
			}

		}
		if((newPgCntValA5LTPC != 'undefined') && (newPgCntValA5LTPC != null) && (newPgCntValA5LTPC != 'null') && (newPgCntValA5LTPC != '')){
			if((newPgCntValA5Color != 'undefined') && (newPgCntValA5Color != null) && (newPgCntValA5Color != 'null') && (newPgCntValA5Color != '')){
				//alert("46456");	
				if((parseInt(newPgCntValA5Color)) > (parseInt(newPgCntValA5LTPC)))
				{	
					jQuery("#validationErrors").show();
					jQuery("#validationErrors").html("");				
					jQuery("#validationErrors").append("<li><spring:message code='meterRead.msg.A5colorReadDifference'/></li>");				
					
					return false;
				}
			}
		}
		else
		{ //alert("tgeryer");	
		if((newPgCntValA5Color != 'undefined') && (newPgCntValA5Color != null) && (newPgCntValA5Color != 'null') && (newPgCntValA5Color != '')){
				if((parseInt(newPgCntValA5Color)) < (parseInt(prevPageCountVal)))
				{	
					jQuery("#validationErrors").show();
					jQuery("#validationErrors").html("");				
					jQuery("#validationErrors").append("<li><spring:message code='meterRead.msg.colorReadDifference'/></li>");				
					
					return false;
				}
			}

		}
		if((newPgCntValLetterLTPC != 'undefined') && (newPgCntValLetterLTPC != null) && (newPgCntValLetterLTPC != 'null') && (newPgCntValLetterLTPC != '')){
			if((newPgCntValLetterColor != 'undefined') && (newPgCntValLetterColor != null) && (newPgCntValLetterColor != 'null') && (newPgCntValLetterColor != '')){
				//alert("46456");	
				if((parseInt(newPgCntValLetterColor)) > (parseInt(newPgCntValLetterLTPC)))
				{	
					jQuery("#validationErrors").show();
					jQuery("#validationErrors").html("");				
					jQuery("#validationErrors").append("<li><spring:message code='meterRead.msg.LettercolorReadDifference'/></li>");				
					
					return false;
				}
			}
		}
		else
		{ //alert("tgeryer");	
		if((newPgCntValLetterColor != 'undefined') && (newPgCntValLetterColor != null) && (newPgCntValLetterColor != 'null') && (newPgCntValLetterColor != '')){
				if((parseInt(newPgCntValLetterColor)) < (parseInt(prevPageCountVal)))
				{	
					jQuery("#validationErrors").show();
					jQuery("#validationErrors").html("");				
					jQuery("#validationErrors").append("<li><spring:message code='meterRead.msg.colorReadDifference'/></li>");				
					
					return false;
				}
			}

		}
		if((newPgCntValLegalLTPC != 'undefined') && (newPgCntValLegalLTPC != null) && (newPgCntValLegalLTPC != 'null') && (newPgCntValLegalLTPC != '')){
			if((newPgCntValLegalColor != 'undefined') && (newPgCntValLegalColor != null) && (newPgCntValLegalColor != 'null') && (newPgCntValLegalColor != '')){
				//alert("46456");	
				if((parseInt(newPgCntValLegalColor)) > (parseInt(newPgCntValLegalLTPC)))
				{	
					jQuery("#validationErrors").show();
					jQuery("#validationErrors").html("");				
					jQuery("#validationErrors").append("<li><spring:message code='meterRead.msg.LegalcolorReadDifference'/></li>");				
					
					return false;
				}
			}
		}
		else
		{ //alert("tgeryer");	
		if((newPgCntValLegalColor != 'undefined') && (newPgCntValLegalColor != null) && (newPgCntValLegalColor != 'null') && (newPgCntValLegalColor != '')){
				if((parseInt(newPgCntValLegalColor)) < (parseInt(prevPageCountVal)))
				{	
					jQuery("#validationErrors").show();
					jQuery("#validationErrors").html("");				
					jQuery("#validationErrors").append("<li><spring:message code='meterRead.msg.colorReadDifference'/></li>");				
					
					return false;
				}
			}

		}
		if((newPgCntValStatementLTPC != 'undefined') && (newPgCntValStatementLTPC != null) && (newPgCntValStatementLTPC != 'null') && (newPgCntValStatementLTPC != '')){
			if((newPgCntValStatementColor != 'undefined') && (newPgCntValStatementColor != null) && (newPgCntValStatementColor != 'null') && (newPgCntValStatementColor != '')){
				//alert("46456");	
				if((parseInt(newPgCntValStatementColor)) > (parseInt(newPgCntValStatementLTPC)))
				{	
					jQuery("#validationErrors").show();
					jQuery("#validationErrors").html("");				
					jQuery("#validationErrors").append("<li><spring:message code='meterRead.msg.StatementcolorReadDifference'/></li>");				
					
					return false;
				}
			}
		}
		else
		{ //alert("tgeryer");	
		if((newPgCntValStatementColor != 'undefined') && (newPgCntValStatementColor != null) && (newPgCntValStatementColor != 'null') && (newPgCntValStatementColor != '')){
				if((parseInt(newPgCntValStatementColor)) < (parseInt(prevPageCountVal)))
				{	
					jQuery("#validationErrors").show();
					jQuery("#validationErrors").html("");				
					jQuery("#validationErrors").append("<li><spring:message code='meterRead.msg.colorReadDifference'/></li>");				
					
					return false;
				}
			}

		}
		if((newPgCntValTabloidLTPC != 'undefined') && (newPgCntValTabloidLTPC != null) && (newPgCntValTabloidLTPC != 'null') && (newPgCntValTabloidLTPC != '')){
			if((newPgCntValTabloidColor != 'undefined') && (newPgCntValTabloidColor != null) && (newPgCntValTabloidColor != 'null') && (newPgCntValTabloidColor != '')){
				//alert("46456");	
				if((parseInt(newPgCntValTabloidColor)) > (parseInt(newPgCntValTabloidLTPC)))
				{	
					jQuery("#validationErrors").show();
					jQuery("#validationErrors").html("");				
					jQuery("#validationErrors").append("<li><spring:message code='meterRead.msg.TabloidcolorReadDifference'/></li>");				
					
					return false;
				}
			}
		}
		else
		{ //alert("tgeryer");	
		if((newPgCntValTabloidColor != 'undefined') && (newPgCntValTabloidColor != null) && (newPgCntValTabloidColor != 'null') && (newPgCntValTabloidColor != '')){
				if((parseInt(newPgCntValTabloidColor)) < (parseInt(prevPageCountVal)))
				{	
					jQuery("#validationErrors").show();
					jQuery("#validationErrors").html("");				
					jQuery("#validationErrors").append("<li><spring:message code='meterRead.msg.colorReadDifference'/></li>");				
					
					return false;
				}
			}

		}
		
		return true;
		
	}

	function fireAjax(){
		//alert("in fireajax");
		/*for(var i=0;i<dataForLTPC.length;i++)
		{
			alert("dataForLTPC[0] "+dataForLTPC[i]);
			
		}*/
		/*alert("dataForColor "+dataForColor);
		alert("dataForLTPC "+dataForLTPC);
		alert("dataForA3Color "+dataForA3Color);
		alert("dataForA3LTPC "+dataForA3LTPC);
		alert("dataForA4Color "+dataForA4Color);
		alert("dataForA4LTPC "+dataForA4LTPC);
		alert("dataForScan "+dataForScan);
		alert("dataForFax "+dataForFax);*/

		var dataForColorJson="";
		var dataForLTPCJson="";
		var dataForA3ColorJson="";
		var dataForA3LTPCJson="";
		var dataForA4ColorJson="";
		var dataForA4LTPCJson="";
		var dataForScanJson="";
		var dataForFaxJson="";
		var dataForBlackJson="";
		var dataForCyanJson="";
		var dataForSoftwareJson="";
		var dataForPgsScanCopyJson="";
		var dataForPgsScanFaxJson="";
		var dataForPgsScanNetworkJson="";
		var dataForPgsScanUsbJson="";
		var dataForTotalScansJson="";
		var dataForLetterColorJson="";
		var dataForLetterLTPCJson="";
		var dataForA5ColorJson="";
		var dataForA5LTPCJson="";
		var dataForLegalColorJson="";
		var dataForLegalLTPCJson="";
		var dataForStatementColorJson="";
		var dataForStatementLTPCJson="";
		var dataForTabloidColorJson="";
		var dataForTabloidLTPCJson="";
		

		
		if(dataForColor.length > 0)
			dataForColorJson = getDataForJson(dataForColor[0],dataForColor[1]);
		if(dataForLTPC.length > 0)
			dataForLTPCJson = getDataForJson(dataForLTPC[0],dataForLTPC[1]);
		if(dataForA3Color.length > 0)
			dataForA3ColorJson = getDataForJson(dataForA3Color[0],dataForA3Color[1]);
		if(dataForA3LTPC.length > 0)
			dataForA3LTPCJson = getDataForJson(dataForA3LTPC[0],dataForA3LTPC[1]);
		if(dataForA4Color.length > 0)
			dataForA4ColorJson = getDataForJson(dataForA4Color[0],dataForA4Color[1]);
		if(dataForA4LTPC.length > 0)
			dataForA4LTPCJson = getDataForJson(dataForA4LTPC[0],dataForA4LTPC[1]);
		if(dataForScan.length > 0)
			dataForScanJson = getDataForJson(dataForScan[0],dataForScan[1]);
		if(dataForFax.length > 0)
			dataForFaxJson = getDataForJson(dataForFax[0],dataForFax[1]);
		if(dataForBlack.length > 0)
			dataForBlackJson = getDataForJson(dataForBlack[0],dataForBlack[1]);
		if(dataForCyan.length > 0)
			dataForCyanJson = getDataForJson(dataForCyan[0],dataForCyan[1]);
		if(dataForSoftware.length > 0)
			dataForSoftwareJson = getDataForJson(dataForSoftware[0],dataForSoftware[1]);
		if(dataForPgsScanCopy.length > 0)
			dataForPgsScanCopyJson = getDataForJson(dataForPgsScanCopy[0],dataForPgsScanCopy[1]);
		if(dataForPgsScanFax.length > 0)
			dataForPgsScanFaxJson = getDataForJson(dataForPgsScanFax[0],dataForPgsScanFax[1]);
		if(dataForPgsScanNetwork.length > 0)
			dataForPgsScanNetworkJson = getDataForJson(dataForPgsScanNetwork[0],dataForPgsScanNetwork[1]);
		if(dataForPgsScanUsb.length > 0)
			dataForPgsScanUsbJson = getDataForJson(dataForPgsScanUsb[0],dataForPgsScanUsb[1]);
		if(dataForTotalScans.length > 0)
			dataForTotalScansJson = getDataForJson(dataForTotalScans[0],dataForTotalScans[1]);
		if(dataForLetterColor.length > 0)
			dataForLetterColorJson = getDataForJson(dataForLetterColor[0],dataForLetterColor[1]);
		if(dataForLetterLTPC.length > 0)
			dataForLetterLTPCJson = getDataForJson(dataForLetterLTPC[0],dataForLetterLTPC[1]);
		if(dataForA5Color.length > 0)
			dataForA5ColorJson = getDataForJson(dataForA5Color[0],dataForA5Color[1]);
		if(dataForA5LTPC.length > 0)
			dataForA5LTPCJson = getDataForJson(dataForA5LTPC[0],dataForA5LTPC[1]);
		if(dataForLegalColor.length > 0)
			dataForLegalColorJson = getDataForJson(dataForLegalColor[0],dataForLegalColor[1]);
		if(dataForLegalLTPC.length > 0)
			dataForLegalLTPCJson = getDataForJson(dataForLegalLTPC[0],dataForLegalLTPC[1]);
		if(dataForStatementColor.length > 0)
			dataForStatementColorJson = getDataForJson(dataForStatementColor[0],dataForStatementColor[1]);
		if(dataForStatementLTPC.length > 0)
			dataForStatementLTPCJson = getDataForJson(dataForStatementLTPC[0],dataForStatementLTPC[1]);
		if(dataForTabloidColor.length > 0)
			dataForTabloidColorJson = getDataForJson(dataForTabloidColor[0],dataForTabloidColor[1]);
		if(dataForTabloidLTPC.length > 0)
			dataForTabloidLTPCJson = getDataForJson(dataForTabloidLTPC[0],dataForTabloidLTPC[1]);
		/*alert("dataForColorJson "+dataForColorJson);
		alert("dataForLTPCJson "+dataForLTPCJson);
		alert("dataForA3ColorJson "+dataForA3ColorJson);
		alert("dataForA3LTPCJson "+dataForA3LTPCJson);
		alert("dataForA4ColorJson "+dataForA4ColorJson);
		alert("dataForA4LTPCJson "+dataForA4LTPCJson);
		alert("dataForScanJson "+dataForScanJson);
		alert("dataForFaxJson "+dataForFaxJson);*/
	
		//var assetId1 = jQuery("#assetId324").val();
		var assetRowId = document.getElementById("assetRowId").innerHTML;	
		//alert(jQuery("#assetId324").val());
		//alert("dataForLTPCJson "+dataForLTPCJson);
		//alert("assetRowId "+assetRowId);
		//alert("assetId1 "+$('assetId3241').val());
		//alert("another way "+$('assetId3241').html);
		
		jQuery.ajax({
				url:'${updatePageCounts}',
				beforeSend: function()
				{	
					jQuery("#validationErrors").html("");
					jQuery("#validationErrors").hide();
					showOverlayPopup();
				},
				type:'POST',
				data: {
						ArrdataForLTPC: dataForLTPCJson,
						//newPageReadDate: jQuery('#newPgRdDate').val().trim(),
						ArrdataForColor: dataForColorJson,
						//newColorPageReadDate: jQuery('#newColorLTPCDate').val().trim(),
						ArrdataForA3Color: dataForA3ColorJson,
						ArrdataForA3LTPC: dataForA3LTPCJson,
						ArrdataForA4Color: dataForA4ColorJson,
						ArrdataForA4LTPC: dataForA4LTPCJson,
						ArrdataForScan: dataForScanJson,
						ArrdataForFax: dataForFaxJson,
						ArrdataForBlack: dataForBlackJson,
						ArrdataForCyan: dataForCyanJson,
						ArrdataForSoftware: dataForSoftwareJson,
						ArrdataForPgsScanCopy: dataForPgsScanCopyJson,
						ArrdataForPgsScanFax: dataForPgsScanFaxJson,
						ArrdataForPgsScanNetwork: dataForPgsScanNetworkJson,
						ArrdataForPgsScanUsb: dataForPgsScanUsbJson,
						ArrdataForTotalScans: dataForTotalScansJson,
						ArrdataForLetterColor: dataForLetterColorJson,
						ArrdataForLetterLTPC: dataForLetterLTPCJson,
						ArrdataForA5Color: dataForA5ColorJson,
						ArrdataForA5LTPC: dataForA5LTPCJson,
						ArrdataForLegalColor: dataForLegalColorJson,
						ArrdataForLegalLTPC: dataForLegalLTPCJson,
						ArrdataForStatementColor: dataForStatementColorJson,
						ArrdataForStatementLTPC: dataForStatementLTPCJson,
						ArrdataForTabloidColor: dataForTabloidColorJson,
						ArrdataForTabloidLTPC: dataForTabloidLTPCJson,
						selectedAssetId: assetID,
						Color: oldColorCount,
						LTPC: oldLTPCCount
					},
				success: function(results){
					hideOverlayPopup();
					var obj2=null;
					var numRowsInGrid = pageCountGrid.getRowsNum();
					try{
						 //obj2=jQuery.parseJSON(results);
						 obj2=results;
					}catch(e){
						;
						}					
					if(obj2 !=null){

						if(obj2=="{\"success\":\"<strong>Page Count Data has been successfully updated.</strong>\"}"){
							jQuery("#pageCntUpdtSuccess").show();
							var value = "<strong>Page Count Data has been successfully updated.</strong>";
							jQuery("#pageCntUpdtSuccess").html(value);
							if (window.PIE) {
				                 document.getElementById("btnCancel").fireEvent("onmove");
				             }
							
							window.parent.window.isSuccessfulUpdate=true;
						}
						else{
							jQuery("#validationErrors").show();
							var value="<strong>There has been an error in saving page counts data. Please try again.</strong>";
							jQuery("#validationErrors").html(value);
							window.parent.window.isSuccessfulUpdate=false;
						}
						// below function is not working currently
						jQuery.each(obj2, function(name, value) {							
							if(name == "success")
							{
								jQuery("#pageCntUpdtSuccess").show();
								jQuery("#pageCntUpdtSuccess").html(value);
								if (window.PIE) {
					                 document.getElementById("btnCancel").fireEvent("onmove");
					             }
								
								window.parent.window.isSuccessfulUpdate=true;
							}
							else if(name == "error")
							{	
								jQuery("#validationErrors").show();
								jQuery("#validationErrors").html(value);
								window.parent.window.isSuccessfulUpdate=false;
							}
								
					});
							
						if(window.parent.window.isSuccessfulUpdate==true)
						{
							jQuery("#btnSubmit").hide();
							jQuery("#btnCancel").html("<spring:message code='button.close'/>");
							page=1;
							pageCountGrid.changePage(page);
							page++;
							for(var i=0;i<numRowsInGrid;i++){
								if(i%10==0 && i!=0){
									
									pageCountGrid.changePage(page);
									page++;
								}
								var k = i+1;	
							jQuery('#rwid_img'+ k).hide();
							clearValues();
							
							}
						}
						
						
						pageCountGrid.changePage(currentPageGrid);
						
					}
					}
				});
		 }

	function getDataForJson(dateTime,count){ 
		var arrData = '["' + dateTime + '","' + count + '"]';
		//alert("arrData "+arrData);
		return arrData; 
	}

	function convertDateToGMT(userDate){

		//alert("in convertDateToGMT(dataForLTPC[0])");
		var d = new Date(userDate);
		//alert("userDate "+userDate);
		//alert("d "+d);
		//alert(d.getUTCFullYear());
		//alert(d.getUTCMonth()); 
		month = d.getUTCMonth() + 1;
		//alert("month "+month);
		//alert(d.getUTCDate());
		//alert(d.getUTCHours());
		//alert(d.getUTCMinutes());
		//alert(d.getUTCSeconds());
		var utcDate = month + "/" + d.getUTCDate() + "/" + d.getUTCFullYear() +
						" " + d.getUTCHours() + ":" + d.getUTCMinutes() + ":" + "00";
		//alert(utcDate);
		return utcDate;
		
	}
	
	//brmandal ended	
	

			
	function isDigitForPgCnt(s){ 
		var patrn=/^-{0,1}\d+$/; 
		if (!patrn.exec(s)) 
			return false; 
		return true; 
	}
	function clearContentsOfpopup1(){
		//alert("clearContentsOfpopup1");
		 //alert(document.getElementById("rwid_+ k"));

		 location.reload();
		 
		  
		      
	}
		
	jQuery(document).ready(function() { //alert("onload start");
		jQuery('#validationErrors',window.document).empty();
		window.document.getElementById("validationErrors").style.display = 'none';
		//alert("onload end");
	});

	//brmandal added for Page count changes - MPS phase 2.1
	
	function splitValues(cellvalue){
		//alert("1");
			var dataArr=cellvalue.split("\"");
			//alert(dataArr.length);
							if(dataArr.length == 1)
								return cellvalue;
							else{
								//alert("in else");
								var j=0;
								for(j=0;j<dataArr.length;j++){
									//alert(dataArr[j+1]);
								if(dataArr[j].indexOf('value=')!= -1){
										return dataArr[j+1];
									}
								}

								}
	}	
	
	//Added for defect 9095
	function shwDate(id, id1){
		if(jQuery('#'+ id).val() !=''){
			jQuery('#'+ id1).show();
		}
	}
	
	function removeDate(id, id1){
		jQuery('#'+ id).val('');
		jQuery('#'+ id1).hide();
	}
	//END
	 function convertDateToStringPageCount(date) {
		var fullYear = date.getFullYear();
		var month = date.getMonth() + 1;
    	var day = date.getDate();
    	
    	return (month + "/" + day + "/" + fullYear +" "+date.getHours()+":"+date.getMinutes()+":"+date.getSeconds());
	}
	
	 
		
		function clearValues(){
			jQuery('#pagecountGrid').find('input[type=text]').val('');
			
		}
	
</script>