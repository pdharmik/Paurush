<% request.setAttribute("subTabSelected","accountsPayable"); %>
<%@ include file="subTab.jsp"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<%@ include file="/WEB-INF/jsp/includeGrid.jsp"%> 
<%@ include file="/WEB-INF/jsp/dateUtil.jsp"%>
<jsp:include page="/WEB-INF/jsp/includeDatePicker.jsp"></jsp:include>
<script type="text/javascript"><%@ include file="../../../js/jQueryAlert/jquery.alerts.js"%></script>

<portlet:resourceURL var="downloadAPInvoiceHistoryList" id="downloadAPARInvoiceHistoryList"></portlet:resourceURL>

<%-- <portlet:renderURL var="showInvoiceListURL">
	<portlet:param name="action" value="showInvoiceList"/>
</portlet:renderURL> --%>

<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/mps.css?version=<html:fileCacheVersion/>" />
<style type="text/css"><%@ include file="/WEB-INF/css/jQueryAlert/jquery.alerts.css" %></style> 
<!--[if IE 7]>
	<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/mps_ie7.css?version=<html:fileCacheVersion/>" />
	 <%--changed by shilpa for CI7-13.10.06 for correct display in IE7 mode of Ap Invoices--%>
<style>
.shiftGridLeft{width:99.5%!important;}
</style>
<![endif]-->
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/in-styles.css?version=<html:fileCacheVersion/>" />
<portlet:renderURL var="invoicePageURL">
	<portlet:param name="action" value="showInvoicePageAP"/>
	<portlet:param name="invoiceNumber" value="${invoiceNumber}" />	
</portlet:renderURL>

<div id="content-wrapper">
    <div class="journal-content-article">
      <h1><spring:message code="partnerPayments.title.partnerHistoryLexmark"/></h1>
      </div>
    <div class="main-wrap">
      <div class="content"><a name="scrollTo"></a>
        <div class="left-nav">
          <div class="left-nav-inner"> 
            <!-- LEFT COLUMN CONTENT GOES HERE -->
            <div class="left-nav-header">
              <h3><spring:message code="requestInfo.heading.serviceOrder.filterByDateRange"/></h3>
            </div>
			<div class="bottomPush"></div>
			<div id="dateErrorDiv" style="display: none;" class="color-red"></div>
            <ul  class="filters">
              <li>
                <label for="fromDate"><spring:message code="requestInfo.label.serviceOrder.from"/></label>
                <span>
                <input  id="fromDate" class="w100" readonly="readonly"/>
                <img src="<html:imagesPath/>transparent.png" class="ui-icon calendar-icon" title="<spring:message code="partnerPayments.label.selectDate"/>" width="23" height="23"
               onClick="show_cal('fromDate',convertDateToString(new Date().addDays(-180)),convertDateToString(new Date()),null);"
                 /></span></li>
              <li>
                <label for="toDate"><spring:message code="requestInfo.label.serviceOrder.to"/></label>
                <span>
                <input  id="toDate" class="w100" readonly="readonly"/>
                <img src="<html:imagesPath/>transparent.png" class="ui-icon calendar-icon" title="<spring:message code="partnerPayments.label.selectDate"/>" width="23" height="23"
                 onclick="show_cal('toDate',convertDateToString(new Date().addDays(-180)),convertDateToString(new Date()),null);" 
                /></span></li>
            </ul>
            <div class="buttonContainerIn">
              <button class="button" id="go_btn" type="button" onclick="sendDateValues();"><spring:message code="requestInfo.button.go"/></button>
            </div>
          </div>
<!-- 14-02-15 Implementation -->
          <br/>
          <div class="left-nav-inner"> 
            <div class="left-nav-header">
               <h3><spring:message code="requestInfo.heading.serviceOrder.filterByStatus"/></h3>
            </div>
            <!-- LEFT COLUMN CONTENT GOES HERE -->
            <div class="bottomPush"></div>
				<div class="radioCss">
					<input id="filter1UnpaidRequests" type="radio" name="paymentStatus" value="Open" onclick="sendDateValues();">
					<label for="filterOpenRequests">&nbsp;<spring:message code="invoices.items.open" /></label>
					<br>
					<input id="filter1PaidRequests" type="radio" name="paymentStatus" value="Paid" onclick="sendDateValues();">
					<label for="filterPaidRequests">&nbsp;<spring:message code="invoices.items.paid" /></label>
					<br>
					<input id="filter1AllRequests" type="radio" name="paymentStatus" value="All" onclick="sendDateValues();" >
					<label for="filterAllRequests">&nbsp;<spring:message code="invoices.items.all" /></label>
					<br>					
				</div>            
          </div>
          <div class="left-nav-footer"> 
            <!-- NO CONTENT HERE PLS --> 
          </div>
        </div>

        <div class="right-column">
		  <div class="portletBlock infoBox rounded shadow split">
		  <h4>
			<div class="columnInner"><spring:message code="paymentDetail.label.PaymentInformation"/></div>
		  </h4>
          <div class="columnsTwo">
            <div class="columnInner">
              <ul class="form">
                <li>
                  <label><spring:message code="partnerPayments.label.payeeName"/></label>
                  <c:if test="${not empty sessionScope.vendorCurrentDetails}"> 
                  	<span>${sessionScope.vendorCurrentDetails["payeeName"]}</span>
                  </c:if>
                  </li>
              </ul>
            </div>
          </div>
          <div class="columnsTwo">
            <div class="columnInner">
              <ul class="form">
                <li>
                  <label><spring:message code="paymentDetail.label.payableAddress"/></label>
                  <span>
	                  <c:if test="${not empty sessionScope.vendorCurrentDetails}"> 
	                  	<c:if test="${!empty sessionScope.vendorCurrentDetails['addressLine1']}">
							${sessionScope.vendorCurrentDetails["addressLine1"]}<br>
						</c:if>
						<c:if test="${!empty sessionScope.vendorCurrentDetails['addressLine2']}">
							${sessionScope.vendorCurrentDetails["addressLine2"]}<br>
						</c:if>
						<c:if test="${!empty sessionScope.vendorCurrentDetails['addressLine3']}">
							${sessionScope.vendorCurrentDetails["addressLine3"]}<br>
						</c:if>
						<c:if test="${!empty sessionScope.vendorCurrentDetails['city']}">
							${sessionScope.vendorCurrentDetails["city"]}
							<c:if test="${!empty sessionScope.vendorCurrentDetails['state']}">
								,
							</c:if>
						</c:if>	
						<c:if test="${!empty sessionScope.vendorCurrentDetails['state']}">
							${sessionScope.vendorCurrentDetails["state"]}
							<c:if test="${!empty sessionScope.vendorCurrentDetails['province']}">
								,
							</c:if>
						</c:if>
						<c:if test="${!empty sessionScope.vendorCurrentDetails['province']}">	
							${sessionScope.vendorCurrentDetails["province"]}
							<c:if test="${!empty sessionScope.vendorCurrentDetails['country']}">
								,
							</c:if>
						</c:if>
						<c:if test="${!empty sessionScope.vendorCurrentDetails['country']}">	
							${sessionScope.vendorCurrentDetails["country"]}
							<c:if test="${!empty sessionScope.vendorCurrentDetails['postalCode']}">
								,
							</c:if>
						</c:if>
						<c:if test="${!empty sessionScope.vendorCurrentDetails['postalCode']}">	
							${sessionScope.vendorCurrentDetails["postalCode"]}&nbsp;
						</c:if>
					</c:if>
                  </span>
                </li>
              </ul>
            </div>
          </div>
        </div>
		
		<div class="grid-controls"> 
            <!-- Utilities Start -->
            <div class="utilities">
              <ul>
                <li class="first"><a href="#"><img height="23px" width="23px" src="<html:imagesPath/>transparent.png" class="ui-icon spreadsheet-icon" alt="<spring:message code="image.title.exportExcel"/>" title="<spring:message code="image.title.exportExcel"/>" onclick="downloadAPInvoiceHistory('CSV');"></a></li>
                <li><a href="#"><img height="23px" width="23px" src="<html:imagesPath/>transparent.png" class="ui-icon pdf-icon" alt="<spring:message code="image.title.exportPDF"/>" title="<spring:message code="image.title.exportPDF"/>" onclick="downloadAPInvoiceHistory('PDF');" /></a></li>
                <li><a href="#"><img height="23px" width="23px" src="<html:imagesPath/>transparent.png" class="ui-icon print-icon" alt="<spring:message code="image.title.print"/>" title="<spring:message code="image.title.print"/>" onclick="print();"></a></li>
              </ul>
            </div>
            <!-- utilities End --> 
		</div>
		<div class="bottomPush"></div>
          <div class="portlet-wrap rounded">
            <div class="portlet-wrap-inner">
             <%--changed by shilpa for CI7-13.10.06 for correct display in IE7 mode of Ap Invoices --%>
              <div id="apInvoiceHistoryGrid_container" class="gridbox gridbox_light shiftGridLeft"></div>
              <div id="loadingNotification" class='gridLoading'>
	        	<!--spring:message code='label.loadingNotification' /-->&nbsp;&nbsp;<img src="<html:imagesPath/>gridloading.gif"/><br>
	    		</div>
            </div>
          </div>
          <div class="pagination" id="paginationId"><span id="pagingArea"></span><span id="infoArea"></span></div>
          <!-- mygrid_container --> 
        </div>
      </div>
    </div>
  </div>
 


<script>
	var sessionFromDate = "";
	var sessionToDate = "";
	var sessionPaymentStatus = "";
	
	sessionFromDate = "${sessionScope.fromDateAP}";
	sessionToDate = "${sessionScope.toDateAP}";
	sessionPaymentStatus = "${sessionScope.paymentStatusAP}"; //partha added for ci7
	
	jQuery(document).ready(function(){				
		
		/* alert("${sessionScope.fromDate}");
		alert("${sessionScope.toDate}"); */
		
		if(sessionFromDate != "" && sessionToDate != "")
		{
			jQuery("#fromDate").val(sessionFromDate);
			jQuery("#toDate").val(sessionToDate);
			//jQuery("#fromDate").val(localizeDate(sessionFromDate));
			//jQuery("#toDate").val(localizeDate(sessionToDate));
		}else{
			jQuery("#fromDate").val(localizeFormatDate(new Date().addDays(-180)));
		
			jQuery("#toDate").val(localizeDate(todayStr));
		}
		
		sessionPaymentStatus = "${sessionScope.paymentStatusAP}"; //partha added for ci7
		
		if(sessionPaymentStatus == 'All'){
			jQuery("input:radio[name=paymentStatus]:nth(2)").attr("checked",true);
		}else if(sessionPaymentStatus == 'Paid'){
			jQuery("input:radio[name=paymentStatus]:nth(1)").attr("checked",true);
		}else{
			jQuery("input:radio[name=paymentStatus]:nth(0)").attr("checked",true);
		}
		//Changed Partha for CI7 End	
		
	});
	
	var invoiceGrid;
	var  apInvoiceHistoryGrid;
	var fromDate;
	var endDate;
	var paymentStatus;
	var sortColumn;
	var sortColumnVal;
	var filterColumnVal_0;
	var filterColumnVal_3;
	var filterColumnVal_5;
	var statusIndicateFlag = false;
	
	//changed by shilpa CI7-13.10.06 for sorting and searching of AP
	<%--var headerString = "Invoice No., Invoice Date,Due Date,Payment / Cheque No.,Paid Date,Amount (${requestScope.invoiceCurrency})";--%>
	apInvoiceHistoryGrid = new dhtmlXGridObject('apInvoiceHistoryGrid_container');
	invoiceGrid=apInvoiceHistoryGrid;

	apInvoiceHistoryGrid.setImagePath("<html:imagesPath/>gridImgs/");
	apInvoiceHistoryGrid.setHeader("<spring:message code='partnerPayments.listHeader.invoiceFields'/>");
	apInvoiceHistoryGrid.attachHeader("#text_filter,,,#text_filter,,#text_filter");	//changed by shilpa CI7-13.10.06 for sorting and searching of AP
	//changed by shilpa CI7-13.10.06 for sorting and searching of AP
	apInvoiceHistoryGrid.setInitWidths("110,120,110,190,110,120");
	apInvoiceHistoryGrid.setColAlign("left,left,left,left,left,right");
	//changed by shilpa CI7-13.10.06 for sorting and searching of AP
	apInvoiceHistoryGrid.setColSorting("str,date,date,str,date,int");
    apInvoiceHistoryGrid.setColTypes("ro,ro,ro,ro,ro,ro");		
    apInvoiceHistoryGrid.enableResizing("true, true,true,true,true,true")	//changed by shilpa CI7-13.10.06 for sorting and searching of AP
    apInvoiceHistoryGrid.setLockColVisibility("false,false,false,false,false,false");//	//changed by shilpa CI7-13.10.06 for sorting and searching of AP
 	apInvoiceHistoryGrid.a_direction = apInvoiceHistoryGrid.a_direction||"asc";
 	//alert(apInvoiceHistoryGrid.a_direction);
	
	apInvoiceHistoryGrid.enableAutoHeight(true);
	apInvoiceHistoryGrid.enableMultiline(true);
	apInvoiceHistoryGrid.enableColumnMove(true);
	apInvoiceHistoryGrid.attachEvent("onXLS", function() {
    	document.getElementById('loadingNotification').style.display = 'block';
    	document.getElementById('paginationId').style.display = 'none'; 
    	});       
	
	apInvoiceHistoryGrid.attachEvent("onXLE", function() {
		
		headerString = "Invoice No., Invoice Date,Due Date,Payment / Cheque No.,Paid Date,amount &nbsp;(${invoiceCurrency})";
		//changed by shilpa CI7-13.10.06 for sorting and searching of AP
		
		//apInvoiceHistoryGrid.detachHeader(0);
    	//apInvoiceHistoryGrid.attachHeader("Invoice No., Invoice Date,Due Date,Payment / Cheque No.,Paid Date,hamba (${sessionScope.invoiceCurrency})");
    	//apInvoiceHistoryGrid.setSizes(); 
    
    	document.getElementById('loadingNotification').style.display = 'none';
    	document.getElementById('paginationId').style.display = 'block';    
    	
    	//----Change By partha for 13-10-06: Start------
    	sortColumnVal = "${sessionScope.sortingColumnVal_AP}";
    	sortColumn = "${sessionScope.sortingColumn_AP}";
    	filterColumnVal_0 = "${sessionScope.filterColumn_0_AP}";
    	filterColumnVal_3 = "${sessionScope.filterColumn_3_AP}";
    	filterColumnVal_5 = "${sessionScope.filterColumn_5_AP}";
    	
    	var filterValuesCustom = new Array();
	    	if(statusIndicateFlag){
	    		filterValuesCustom[0] = '';
		    	filterValuesCustom[1] = '';
		    	filterValuesCustom[2] = '';
	    	}else{
	    		filterValuesCustom[0] = filterColumnVal_0;
		    	filterValuesCustom[1] = filterColumnVal_3;
		    	filterValuesCustom[2] = filterColumnVal_5;
	    	}
    		
    	apInvoiceHistoryGrid.setSortImgState(true, sortColumn, sortColumnVal);
    	if(sortColumn=='1' || sortColumn=='2' || sortColumn=='4' ){
        	apInvoiceHistoryGrid.sortRows(sortColumn, "date", sortColumnVal);
    	}else if(sortColumn=='5'){
    		apInvoiceHistoryGrid.sortRows(sortColumn, "int", sortColumnVal);
    	}else{
    		apInvoiceHistoryGrid.sortRows(sortColumn, "str", sortColumnVal);
    	} 
    	apInvoiceHistoryGrid.setFiltersValue(filterValuesCustom.toString());
    	apInvoiceHistoryGrid.filterByAll();
    	//apInvoiceHistoryGrid.setSortImgState(true, 3, "asc");	
    	//apInvoiceHistoryGrid.sortRows(3, "str", "asc");
    	//------Change By partha for 13-10-06: End------------
    	setTimeout(function(){
    		rebrandPagination();
    	
    	},100);
    });
	
	//----Change By partha for 13-10-06: Start------
	apInvoiceHistoryGrid.attachEvent("onBeforeSorting", function(index){
		var a_state = apInvoiceHistoryGrid.getSortingState();
			sortColumn = index;
			sortColumnVal = ((a_state[1] == "asc") ? "des":"asc" );
		return true;
	});
	
	apInvoiceHistoryGrid.attachEvent("onFilterStart", function(indexes,values){
		apInvoiceHistoryGrid.filterValues = values;
			filterColumnVal_0 = values[0];
			filterColumnVal_3 = values[1];
			filterColumnVal_5 = values[2];
		return true;
    });
	//----Change By partha for 13-10-06: End------
	
	apInvoiceHistoryGrid.init();
	//changed by shilpa CI7-13.10.06 for sorting and searching of AP
	apInvoiceHistoryGrid.prftInit();
	//apInvoiceHistoryGrid.setSortImgState(true,3,"asc");
	apInvoiceHistoryGrid.enablePaging(true, 20, 10, "pagingArea", true, "infoArea");
	apInvoiceHistoryGrid.setPagingSkin("bricks");
	//apInvoiceHistoryGrid.loadXMLString("${invoiceAPForm.invoiceListXML}");
	//alert("${showInvoiceListURL}");
	
	//BRD: 14-02-15 Implementation
	paymentStatus = "${sessionScope.paymentStatusAP}";
	//alert("paymentStatus: "+paymentStatus);
	
	if(sessionFromDate != "" && sessionToDate != "")
	{
		apInvoiceHistoryGrid.loadXML("${showInvoiceListURL}&paymentStatus="+paymentStatus+"&fromDate="+sessionFromDate+"&endDate="+sessionToDate);
	}else{		
		apInvoiceHistoryGrid.loadXML("${showInvoiceListURL}");//this url is present in the subtab.jsp
	}
	
	
	function showInvoiceRequestDetail(invoiceNumber, invoiceDt, dueDt, paidDt, checkNo, amount,currency){
		showOverlay();
		window.location.href="${invoicePageURL}&invoiceNumber=" +invoiceNumber+"&invoiceDt=" +invoiceDt+"&dueDt=" +dueDt+"&paidDt=" +paidDt+"&checkNo=" +checkNo+"&amount="+amount+"&currency="+currency+"&sortColumn="+sortColumn+"&sortColumnVal="+sortColumnVal+"&filterColumnVal_0="+filterColumnVal_0+"&filterColumnVal_3="+filterColumnVal_3+"&filterColumnVal_5="+filterColumnVal_5;
	};
	
	function sendDateValues(){
	statusIndicateFlag = true;
	
//	alert("send date values");	
	 fromDate = jQuery("#fromDate").val();
	 endDate = jQuery("#toDate").val();
	 paymentStatus = jQuery("input:radio[name=paymentStatus]:checked").val();
	 
	 var deFlag = false;
	/* alert(fromDate);
	alert(endDate); */
	
	if(fromDate!=null && fromDate!="" && endDate!=null && endDate!="")
	{	
		url = "${showInvoiceListURL}&paymentStatus="+paymentStatus;
		var tgtSeperator = dateFormat.substring(2,3);
		if(tgtSeperator=="/"){			
		}else if(tgtSeperator=="."){			
		}else{
			tgtSeperator = dateFormat.substring(4,5);
		}
		today = new Date();
		fromDat_converted = convertStringToDate((convertDateFormat(dateFormat, tgtSeperator, fromDate, "mm/dd/yyyy", "/")));
    	endDat_converted = convertStringToDate((convertDateFormat(dateFormat, tgtSeperator, endDate, "mm/dd/yyyy", "/")));
    	
		var oneDay = 24*60*60*1000; 
		var diffDays = Math.round(Math.abs((endDat_converted.getTime() - fromDat_converted.getTime())/(oneDay)));
		
		/* alert(dateFormat);	
		alert(tgtSeperator);
    	alert("today "+today);
		alert("fromDate "+fromDate);
		alert("endDate "+endDate);
    	alert("fromDat_converted "+fromDat_converted);
		alert("endDat_converted "+endDat_converted);
		alert("diffDays "+diffDays);
		 */
		
    	if(endDat_converted > today){
			 jQuery('#dateErrorDiv').html("<spring:message code='partnerPayments.validation.toDate.invalid'/>");
			 jQuery('#dateErrorDiv').show();
		     return false;
		}
    	else if(diffDays>180){
			jAlert('<spring:message code="requestInfo.invoice.warning.dateRange"/>');
			return false;
		}
		else if(fromDat_converted > endDat_converted)
		{
			jQuery('#dateErrorDiv').html("<spring:message code='partnerPayments.validation.fromDate.invalid'/>");
			jQuery('#dateErrorDiv').show();
			return false;
		}
		else{ 
			jQuery("#dateErrorDiv").hide();
			apInvoiceHistoryGrid.clearAndLoad(url +"&fromDate="+fromDate+"&endDate="+endDate);
		}
	}else{
		
		jAlert('<spring:message code="requestInfo.invoice.warning.emptydate"/>');
	}
}
	
	 function downloadAPInvoiceHistory(type){
		 fromDate = jQuery("#fromDate").val();
		 endDate = jQuery("#toDate").val();
		 paymentStatus = jQuery("input:radio[name=paymentStatus]:checked").val();
			window.location="${downloadAPInvoiceHistoryList}&downloadType=" + type +"&fromDate="+fromDate+"&endDate="+endDate+"&paymentStatus="+paymentStatus;
	}
	function print(){
		url ="<portlet:renderURL windowState='<%= LiferayWindowState.POP_UP.toString() %>'>"+
			"<portlet:param name='action' value='printInvoiceHistoryList' /></portlet:renderURL>";
	
	    var iWidth=1000;
	    var iHeight=600;
	    var iTop = (window.screen.availHeight-30-iHeight)/2;
	    var iLeft = (window.screen.availWidth-10-iWidth)/2;
	    window.open(url,
	    		'historyList',
			    'height='+iHeight+
			    ',innerHeight='+
			    iHeight+',width='+
			    iWidth+',innerWidth='+
			    iWidth+',top='+iTop+
			    ',left='+iLeft+
			    ',status=no,toolbar=yes,menubar=no,location=no,resizable=yes,scrollbars=yes,titlebar=no');
	}
	/*if(savedKeyValues["filterCriterias"] && savedKeyValues["filterCriterias"].length > 0)
		requestListGrid.setFiltersValue(savedKeyValues["filterCriterias"]);*/

</script>