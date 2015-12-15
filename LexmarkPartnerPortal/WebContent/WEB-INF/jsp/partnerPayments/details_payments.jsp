<% request.setAttribute("subTabSelected","accountsReceivable"); %>
<%@ include file="subTab.jsp"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<%@ include file="/WEB-INF/jsp/includeGrid.jsp"%>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/mps.css?version=<html:fileCacheVersion/>" />
<!--[if IE 7]>
	<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/mps_ie7.css?version=<html:fileCacheVersion/>" />
<![endif]-->

<portlet:renderURL var="showInvoiceListURL">
	<portlet:param name="action" value="showInvoiceList"/>
</portlet:renderURL>

<portlet:resourceURL var="showSRDetails" id="showSRDetails">	
</portlet:resourceURL>


<div id="content-wrapper">
    <div class="journal-content-article">
      <h1><spring:message code="partnerPayments.heading.creditInvoiceDetails"/></h1> 
	</div>
    <div class="main-wrap">
      <div class="content"> 
        <!-- MAIN CONTENT BEGIN -->
		
		<div class="grid-controls"> 
            <div class="expand-min">
              <ul>
                <li class="first"><img class="ui-icon bck-arrow-icon" src="<html:imagesPath/>transparent.png" alt="<spring:message code="partnerPayments.link.returnToInvoices"/>" width="24" height="24" title="<spring:message code="partnerPayments.link.returnToInvoices"/>" /><a href="javascript:void(0)" onClick="backToOrderList()";><spring:message code="partnerPayments.link.returnToInvoices"/></a> </li>
              </ul>
            </div>
          </div>
		
        <div class="portletBlock infoBox rounded shadow split">
		<h4>
			<div class="columnInner"><spring:message code="partnerPayments.label.creditInvoiceInformation"/></div>
		</h4>
          <div class="columnsTwo">
            <div class="columnInner">
              <ul class="form">
                <li>
                  <label><spring:message code="paymentDetail.label.paymentAccount"/></label>
                  <span>Tolt</span></li>
                <li>
                  <label><spring:message code="partnerPayments.label.soldToNo"/></label>
                  <span>123456789</span></li>
                <li>
                  <label><spring:message code="partnerPayments.label.billtoAddress"/> </label>
                  <span>123 Sesame St. Lexington, KY 40205, USA</span></li>
              </ul>
            </div>
          </div>
          <div class="columnsTwo">
            <div class="columnInner">
              <ul class="form">
                <li>
                  <label><spring:message code="partnerPayments.label.creditInvoiceDate"/> </label>
                  <span>06/10/2011</span></li>				
				<li>
                  <label><spring:message code="partnerPayments.label.creditInvoiceAmt"/> </label>
                  <span>15924.65 (${srCurrency})</span></li>
              </ul>
            </div>
          </div>
        </div>

        <!-- Request Status History - End -->
        <div class="portletBlock">
          <div class="columnsThree">
			<div class="infoBox columnInner rounded shadow">
			  <div class="portlet-wrap-inner">
			    <div id="detailsPaymentsGrid_container" class="gridbox gridbox_light"></div>
			  </div>
			</div>
			<div class="pagination"> <span id="pagingArea"></span><span id="infoArea"></span> </div>
          </div>
          
          <div class="infoBox rounded shadow">
            <h4><div class="columnInner"><spring:message code="partnerPayments.label.reqDetail"/></div></h4>
          <div class="portletBlock">
          <div class="columnsTwo">
            <div class="columnInner">
              <ul class="form">
                <li>
                  <label><spring:message code="claim.label.requestNumber"/></label>
                  <span></span></li>
                <li>
                  <label><spring:message code="partnerPayments.label.custAccName"/></label>
                  <span></span></li>
              </ul>
            </div>
          </div>
          <div class="columnsTwo">
            <div class="columnInner">
              <ul class="form">
                <li>
                  <label><spring:message code="partnerPayments.label.partFee"/></label>
                  <span></span></li>
				<li>
                  <label><spring:message code="partnerPayments.label.fullfillmentFee"/></label>
                  <span></span></li>
				<li>
                  <label><spring:message code="partnerPayments.label.labor"/></label>
                  <span></span></li>
				<li>
                  <label><spring:message code="partnerPayments.label.addiPayment"/></label>
                  <span></span></li>
              </ul>
            </div>
          </div>
          </div>
		  
		  <div class="portletBodyWrap" id="srDetailDiv">
		  <table class="displayGrid rounded shadow wFull">
		  	<thead>
		  	  <tr>
		  		<th><spring:message code="partnerPayments.label.part"/></th>
		  		<th><spring:message code="partnerPayments.label.suppliesDesc"/></th>
		  		<th><spring:message code="partnerPayments.label.quantity"/></th>
		  		<th><spring:message code="partnerPayments.label.partFeeHeader"/></th>
		  		<th><spring:message code="partnerPayments.label.fulfillmentFee"/></th>
		  		<th><spring:message code="partnerPayments.label.amount"/></th>
		  	  </tr>
		  	</thead>
		  	<tbody>
		  	  
		  	</tbody>
		  </table>
		  </div>
		  
		  <div class="portletBodyWrap" id="hiddenDataSRDetails"></div>
          </div>
          
        </div>
        <hr class="separator" />
        </div>
        <!-- MAIN CONTENT END --> 
      </div>
  </div>


<script>
   var detailsPaymentsGrid;
   //var headerString = "Request Number,Amount(${srCurrency})";    
   detailsPaymentsGrid = new dhtmlXGridObject('detailsPaymentsGrid_container');
   detailsPaymentsGrid.setImagePath("../../../images/gridImgs/");
   detailsPaymentsGrid.setHeader("<spring:message code="partnerPayments.listHeader.detailsPayment"/>");
   
   detailsPaymentsGrid.setInitWidths("*,150");
   detailsPaymentsGrid.setColAlign("left,left");
   detailsPaymentsGrid.setSkin("light");
   detailsPaymentsGrid.setColSorting("na,na");
   detailsPaymentsGrid.setColTypes("ro,ro");
   detailsPaymentsGrid.enableAutoHeight(true);
   detailsPaymentsGrid.enableMultiline(true);
   detailsPaymentsGrid.enableColumnMove(true);
   detailsPaymentsGrid.init();
   detailsPaymentsGrid.enablePaging(true, 20, 5, "pagingArea", true, "infoArea");
   detailsPaymentsGrid.setPagingSkin("bricks"); 	 
   detailsPaymentsGrid.loadXMLString("${srForm.srListXML}");

   function showSRRequestDetail(serviceReqNo){
	   jQuery.ajax({
			url:'${showSRDetails}',			
			type:'POST',
			data: {
				 	//Ajax call with selected row data
					serviceReqNo :   serviceReqNo					
				},
			success: function(results){
				
				jQuery("#hiddenDataSRDetails").show();
				jQuery("#hiddenDataSRDetails").html(results);
				jQuery("#srDetailDiv").hide();
			}
	   });		
   }
   
   function backToOrderList()
	{
		showOverlay();
		var url = "${showInvoiceListURL}&typeId=AR";
		window.location.href = url;
	}
</script>