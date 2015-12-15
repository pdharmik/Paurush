<% request.setAttribute("subTabSelected","accountsReceivable"); %>
<%@ include file="subTab.jsp"%>

<%--changed by shilpa for CI7-13.10.06 --%>

 <%@ include file="/WEB-INF/jsp/include.jsp"%>
<%@ include file="/WEB-INF/jsp/includeGrid.jsp"%> 
<%@ include file="/WEB-INF/jsp/dateUtil.jsp"%>
<jsp:include page="/WEB-INF/jsp/includeDatePicker.jsp"></jsp:include>

<portlet:resourceURL var="downloadARInvoiceHistoryList" id="downloadAPARInvoiceHistoryList"></portlet:resourceURL>

<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/mps.css?version=<html:fileCacheVersion/>" />
<!--[if IE 7]>
	<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/mps_ie7.css?version=<html:fileCacheVersion/>" />
	<%--changed by shilpa for CI7-13.10.06 for correct display in IE7 mode --%>
<style>
.shiftGridLeft{width:99.5%!important;}
</style>
<![endif]-->
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/in-styles.css?version=<html:fileCacheVersion/>" />
<portlet:renderURL var="invoicePageURL">
	<portlet:param name="action" value="showInvoicePageAR"/>
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
              <button class="button" id="go_btn" onClick="sendDateValues();"><spring:message code="requestInfo.button.go"/></button>
            </div>
          </div>
          <br/>
          <div class="left-nav-inner"> 
          	<div class="left-nav-header">
              <h3><spring:message code="requestInfo.heading.serviceOrder.filterByStatus"/></h3>
            </div>
            <!-- // 14-02-15 Implementation  -->
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
                  <label><spring:message code="paymentDetail.label.paymentAccount"/></label>
                  <c:if test="${not empty sessionScope.accRecivablDetails}"> 
                  	<span>${sessionScope.accRecivablDetails["payeeAccnt"]}</span>
                  </c:if>
                </li>
              </ul>
            </div>
          </div>
          <div class="columnsTwo">
            <div class="columnInner">
              <ul class="form">
                <li>
                  <label><spring:message code="partnerPayments.label.billtoAddress"/></label>
                  <c:if test="${not empty sessionScope.accRecivablDetails}"> 
                  	<c:if test="${!empty sessionScope.accRecivablDetails['addressLine1']}">
						${sessionScope.accRecivablDetails["addressLine1"]}<br>
					</c:if>
					<c:if test="${!empty sessionScope.accRecivablDetails['addressLine2']}">
						${sessionScope.accRecivablDetails["addressLine2"]}<br>
					</c:if>
					<c:if test="${!empty sessionScope.accRecivablDetails['addressLine3']}">
						${sessionScope.accRecivablDetails["addressLine3"]}<br>
					</c:if>
					<c:if test="${!empty sessionScope.accRecivablDetails['city']}">
						${sessionScope.accRecivablDetails["city"]}
						<c:if test="${!empty sessionScope.accRecivablDetails['state']}">
							,
						</c:if>
					</c:if>	
					<c:if test="${!empty sessionScope.accRecivablDetails['state']}">
						${sessionScope.accRecivablDetails["state"]}
						<c:if test="${!empty sessionScope.accRecivablDetails['province']}">
							,
						</c:if>
					</c:if>
					<c:if test="${!empty sessionScope.accRecivablDetails['province']}">	
						${sessionScope.accRecivablDetails["province"]}
						<c:if test="${!empty sessionScope.accRecivablDetails['country']}">
							,
						</c:if>
					</c:if>
					<c:if test="${!empty sessionScope.accRecivablDetails['country']}">	
						${sessionScope.accRecivablDetails["country"]}
						<c:if test="${!empty sessionScope.accRecivablDetails['postalCode']}">
							,
						</c:if>
					</c:if>
					<c:if test="${!empty sessionScope.accRecivablDetails['postalCode']}">	
						${sessionScope.accRecivablDetails["postalCode"]}&nbsp;
					</c:if>
				</c:if>                  
                </li>
              </ul>
            </div>
          </div>
          <div class="columnsTwo">
            <div class="columnInner">
              <ul class="form">
                <li>
                  <label><spring:message code="partnerPayments.label.soldToNo"/></label>
                  <span>
                  <c:if test="${not empty sessionScope.accRecivablDetails}"> 
                  	<span>${sessionScope.accRecivablDetails["soldToNo"]}</span>
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
                <li class="first"><a href="#"><img height="23px" width="23px" src="<html:imagesPath/>transparent.png" class="ui-icon spreadsheet-icon" alt="<spring:message code="image.title.exportExcel"/>" title="<spring:message code="image.title.exportExcel"/>" onclick="downloadARInvoiceHistory('CSV');"></a></li>
                <li><a href="#"><img height="23px" width="23px" src="<html:imagesPath/>transparent.png" class="ui-icon pdf-icon" alt="<spring:message code="image.title.exportPDF"/>" title="<spring:message code="image.title.exportPDF"/>" onclick="downloadARInvoiceHistory('PDF');" /></a></li>
                <li><a href="#"><img height="23px" width="23px" src="<html:imagesPath/>transparent.png" class="ui-icon print-icon" alt="<spring:message code="image.title.print"/>" title="<spring:message code="image.title.print"/>" onclick="print();"></a></li>
              </ul>
            </div>
            <!-- utilities End --> 
		</div>
		<div class="bottomPush"></div>
          <div class="portlet-wrap rounded columnsTwo">
            <div class="portlet-wrap-inner">
            <%--changed by shilpa for CI7-13.10.06 for correct display in IE7 mode--%>
              <div id="arInvoiceHistoryGrid_container" class="gridbox gridbox_light shiftGridLeft"></div>
               <div id="loadingNotification" class='gridLoading'>
	        		<!--spring:message code='label.loadingNotification' / -->&nbsp;&nbsp;<img src="<html:imagesPath/>gridloading.gif"/><br>
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

	sessionFromDate = "${sessionScope.fromDateAR}";
	sessionToDate = "${sessionScope.toDateAR}";
	//Partha Changes for BRD#13-10-06-01 START
	sessionPaymentStatus = "${sessionScope.paymentStatusAR}";
	//Partha Changes for BRD#13-10-06-01 END

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
			//jQuery("#fromDate").val('05/01/2013'); // Added as part of CI requirement
			jQuery("#toDate").val(localizeDate(todayStr));
		}
		//Changed Partha for CI7 Start
		sessionPaymentStatus = "${sessionScope.paymentStatusAR}";
		
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
	var arInvoiceHistoryGrid;
	var fromDate;
	var endDate;
	var paymentStatus;
	var sortColumnVal;
	var sortColumn;
	var filterColumnVal_0;
	var filterColumnVal_2;
	var statusIndicateFlag = false;
	
	<%--var headerString = "Invoice No., Invoice Date, Amount (${invoiceCurrency})";--%>
	arInvoiceHistoryGrid = new dhtmlXGridObject('arInvoiceHistoryGrid_container');
    invoiceGrid=arInvoiceHistoryGrid;
  //changed by shilpa CI7-13.10.06 for sorting and searching of AR
	arInvoiceHistoryGrid.setImagePath("<html:imagesPath/>gridImgs/");
	arInvoiceHistoryGrid.setHeader("<spring:message code="partnerPayments.listHeader.invoiceLists"/>"); 
	arInvoiceHistoryGrid.attachHeader("#text_filter,,#text_filter");//changed by shilpa CI7-13.10.06 for searchig of AR
	
	arInvoiceHistoryGrid.setInitWidths("100,*,160");
	arInvoiceHistoryGrid.setColAlign("left,left,left");
	arInvoiceHistoryGrid.enableResizing("true,true,true");//changed by shilpa CI7-13.10.06 for sorting and searching of AR
	arInvoiceHistoryGrid.setSkin("light");
	//changed by shilpa CI7-13.10.06 for sorting 
	arInvoiceHistoryGrid.setColSorting("str,date,int");
	arInvoiceHistoryGrid.setColTypes("ro,ro,ro");
	arInvoiceHistoryGrid.setLockColVisibility("false,false,false");//changed by shilpa CI7-13.10.06 for sorting of AR
	
	arInvoiceHistoryGrid.enableAutoHeight(true);
	arInvoiceHistoryGrid.enableMultiline(true);
	arInvoiceHistoryGrid.enableColumnMove(true);
	
	arInvoiceHistoryGrid.sortIndex = null;
	arInvoiceHistoryGrid.a_direction = arInvoiceHistoryGrid.a_direction||"asc";
	arInvoiceHistoryGrid.attachEvent("onXLS", function() {
    	document.getElementById('loadingNotification').style.display = 'block';
    	document.getElementById('paginationId').style.display = 'none'; 
    	});       
	
	arInvoiceHistoryGrid.attachEvent("onXLE", function() {
    	document.getElementById('loadingNotification').style.display = 'none';
    	document.getElementById('paginationId').style.display = 'block';
    	
    	//----Change By partha for 13-10-06: Start------
    	sortColumnVal = "${sessionScope.sortingColumnVal_AR}";
    	sortColumn = "${sessionScope.sortingColumn_AR}";
    	filterColumnVal_0 = "${sessionScope.filterColumn_0_AR}";
    	filterColumnVal_2 = "${sessionScope.filterColumn_2_AR}";
    	
    	var filterValuesCustom = new Array();
    	if(statusIndicateFlag){
    		filterValuesCustom[0] = '';
    		filterValuesCustom[1] = '';
    	}else{
    		filterValuesCustom[0] = filterColumnVal_0;
	    	filterValuesCustom[1] = filterColumnVal_2;
    	}
    	arInvoiceHistoryGrid.setSortImgState(true, sortColumn, sortColumnVal);
    	if(sortColumn=='1'){
    		arInvoiceHistoryGrid.sortRows(sortColumn, "date", sortColumnVal);
    	}else if(sortColumn=='2'){
    		arInvoiceHistoryGrid.sortRows(sortColumn, "int", sortColumnVal);
    	}else{
    		arInvoiceHistoryGrid.sortRows(sortColumn, "str", sortColumnVal);
    	}
    	arInvoiceHistoryGrid.setFiltersValue(filterValuesCustom.toString());
    	arInvoiceHistoryGrid.filterByAll();
    	//------Change By partha for 13-10-06: End------------
    });
	
	//----Change By partha for 13-10-06: Start------
	arInvoiceHistoryGrid.attachEvent("onBeforeSorting", function(index){
		var a_state = arInvoiceHistoryGrid.getSortingState();
			sortColumn = index;
			sortColumnVal = ((a_state[1] == "asc") ? "des":"asc" );
		return true;
	});
	
	arInvoiceHistoryGrid.attachEvent("onFilterStart", function(indexes,values){
		arInvoiceHistoryGrid.filterValues = values;
			filterColumnVal_0 = values[0];
			filterColumnVal_2 = values[1];
		return true;
    });
	//----Change By partha for 13-10-06: End------
	
	arInvoiceHistoryGrid.init();
	arInvoiceHistoryGrid.prftInit();  //changed by shilpa CI7-13.10.06 for sorting and searching of AR
	arInvoiceHistoryGrid.enablePaging(true, 20, 10, "pagingArea", true, "infoArea");
	arInvoiceHistoryGrid.setPagingSkin("bricks");
	//arInvoiceHistoryGrid.loadXMLString("${invoiceAPForm.invoiceListXML}");
	// 14-02-15 Implementation 
	paymentStatus = "${sessionScope.paymentStatusAR}";
	//alert("paymentStatus: "+paymentStatus);
	
	if(sessionFromDate != "" && sessionToDate != "")
	{
		arInvoiceHistoryGrid.loadXML("${showInvoiceListURL}&paymentStatus="+paymentStatus+"&typeId=AR&fromDate="+sessionFromDate+"&endDate="+sessionToDate);
	}else{		
		arInvoiceHistoryGrid.loadXML("${showInvoiceListURL}&paymentStatus="+paymentStatus+"&typeId=AR");//this url is present in the subtab.jsp
	}
	
	function sendDateValues(){
		statusIndicateFlag = true;
		fromDate = jQuery("#fromDate").val();
		endDate = jQuery("#toDate").val();
		
		paymentStatus = jQuery("input:radio[name=paymentStatus]:checked").val();
		
		var deFlag = false;
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
				arInvoiceHistoryGrid.clearAndLoad(url +"&typeId=AR"+"&fromDate="+fromDate+"&endDate="+endDate+"&paymentStatus="+paymentStatus);
			}
		}else{
			jAlert('<spring:message code="requestInfo.invoice.warning.emptydate"/>');
		}
	}
	
	function showInvoiceRequestDetail(invoiceNumber, invoiceDt, amount, currency){
		showOverlay();
		
		window.location.href="${invoicePageURL}&invoiceNumber=" +invoiceNumber+"&invoiceDt=" +invoiceDt +"&reqType=AR&amount="+amount+"&currency="+currency+"&sortColumn="+sortColumn+"&sortColumnVal="+sortColumnVal+"&filterColumnVal_0="+filterColumnVal_0+"&filterColumnVal_2="+filterColumnVal_2;
	};

	 function downloadARInvoiceHistory(type){
		 fromDate = jQuery("#fromDate").val();
		 endDate = jQuery("#toDate").val();
		 paymentStatus = jQuery("input:radio[name=paymentStatus]:checked").val();
		 window.location="${downloadARInvoiceHistoryList}&typeId=AR&downloadType=" + type +"&fromDate="+fromDate+"&endDate="+endDate+"&paymentStatus="+paymentStatus;
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
		
	

</script>