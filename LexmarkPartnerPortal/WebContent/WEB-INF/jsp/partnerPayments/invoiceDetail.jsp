<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/mps.css?version=<html:fileCacheVersion/>" />
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<%@ include file="/WEB-INF/jsp/includeGrid.jsp"%>

<div id="content-wrapper">
    <div class="journal-content-article">
      <h1><spring:message code="paymentDetail.label.PaymentDetails"/></h1> 
	</div>
    <div class="main-wrap">
      <div class="content"> 
        <!-- MAIN CONTENT BEGIN -->
		
		<div class="grid-controls"> 
            <div class="expand-min">
              <ul>
                <li class="first"><img class="ui-icon bck-arrow-icon" src="<html:imagesPath/>transparent.png" alt="<spring:message code="partnerPayments.link.returnToInvoices"/>" width="24" height="24" title="<spring:message code="partnerPayments.link.returnToInvoices"/>" /><a href="javascript:void(0)" onClick="minimizeAll()";><spring:message code="partnerPayments.link.returnToInvoices"/></a> </li>
              </ul>
            </div>
          </div>
		
        <div class="portletBlock infoBox rounded shadow split">
		<h4>
			<div class="columnInner"><spring:message code="partnerPayments.label.invoiceInformation"/></div>
		</h4>
          <div class="columnsTwo">
            <div class="columnInner">
              <ul class="form">
                <li>
                  <label><spring:message code="paymentDetail.label.paymentAccount"/> </label>
                  <span>Partner Account</span></li>
                <li>
                  <label><spring:message code="partnerPayments.label.payeeName/></label>
                  <span>Payee name</span></li>
                <li>
                  <label><spring:message code="paymentDetail.label.payableAddress"/></label>
                  <span>Payable Address</span></li>
              </ul>
            </div>
          </div>
          <div class="columnsTwo">
            <div class="columnInner">
              <ul class="form">
                <li>
                  <label><spring:message code="partnerPayments.label.inviteDate"/> </label>
                  <span>MM/DD/YY</span></li>
				<li>
                  <label><spring:message code="partnerPayments.label.dueDate"/></label>
                  <span>MM/DD/YY</span></li>
				<li>
                  <label><spring:message code="partnerPayments.label.paidDate"/></label>
                  <span>MM/DD/YY</span></li>
                <li>
                  <label><spring:message code="partnerPayments.label.paymentNo"/></label>
                  <span>FirstName LastName</span></li>
				<li>
                  <label><spring:message code="partnerPayments.label.invoiceAmt"/></label>
                  <span>FirstName LastName</span></li>
              </ul>
            </div>
          </div>
        </div>

        <!-- Request Status History - End -->
        <div class="portletBlock">
          <div class="columnsThree">
			<div class="infoBox columnInner rounded shadow">
			  <div class="portlet-wrap-inner">
			    <div id="invoiceDetailGrid_container" class="gridbox gridbox_light"></div>
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
                  <label><spring:message code="requestInfo.label.serviceOrder.requestNumber"/></label>
                  <span>Partner Account</span></li>
                <li>
                  <label><spring:message code="partnerPayments.label.custAccName"/></label>
                  <span>Payee name</span></li>
              </ul>
            </div>
          </div>
          <div class="columnsTwo">
            <div class="columnInner">
              <ul class="form">
                <li>
                  <label><spring:message code="partnerPayments.label.partFee"/></label>
                  <span>353</span></li>
				<li>
                  <label><spring:message code="partnerPayments.label.fullfillmentFee"/></label>
                  <span>353</span></li>
				<li>
                  <label><spring:message code="partnerPayments.label.labor"/></label>
                  <span>353</span></li>
				<li>
                  <label><spring:message code="partnerPayments.label.addiPayment"/></label>
                  <span>353</span></li>
              </ul>
            </div>
          </div>
          </div>
		  
		  <div class="portletBodyWrap">
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
		  	  <tr>
		  	  	<td>C123</td>
		  	  	<td>Cartridge</td>
		  	  	<td>1</td>
		  	  	<td>200</td>
		  	  	<td>300</td>
		  	  	<td>400</td>
		  	  </tr>
			  <tr>
		  	  	<td>C123</td>
		  	  	<td>Cartridge</td>
		  	  	<td>1</td>
		  	  	<td>200</td>
		  	  	<td>300</td>
		  	  	<td>400</td>
		  	  </tr>
			  <tr>
		  	  	<td>C123</td>
		  	  	<td>Cartridge</td>
		  	  	<td>1</td>
		  	  	<td>200</td>
		  	  	<td>300</td>
		  	  	<td>400</td>
		  	  </tr>
		  	</tbody>
		  </table>
		  </div>
		  
          </div>
          
        </div>
        <hr class="separator" />
        </div>
        <!-- MAIN CONTENT END --> 
      </div>
  </div>
  
  <script>
   var invoiceDetailGrid;
   var headerString = "Request Number,Amount(USD)";
   var ar = [["1-12345","2222"],
			 ["1-12345","2222"],
			 ["1-12345","2222"],
			 ["1-12345","2222"],
			 ["1-12345","2222"],
			 ["1-12345","2222"],
			 ["1-12345","2222"],
			 ["1-12345","2222"],
			 ["1-12345","2222"],
			 ["1-12345","2222"],
			 ["1-12345","2222"],
			 ["1-12345","2222"],
			 ["1-12345","2222"],
			 ["1-12345","2222"],
			 ["1-12345","2222"],
			 ["1-12345","2222"],
			 ["1-12345","2222"],
			 ["1-12345","2222"],
			 ["1-12345","2222"],
			 ["1-12345","2222"]]; 
   invoiceDetailGrid = new dhtmlXGridObject('invoiceDetailGrid_container');
   invoiceDetailGrid.setImagePath("../../../images/gridImgs/");
   invoiceDetailGrid.setHeader(headerString);
   //invoiceDetailGrid.attachHeader(",,,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter");
   invoiceDetailGrid.setInitWidths("*,150");
   invoiceDetailGrid.setColAlign("left,left");
   invoiceDetailGrid.setSkin("light");
   invoiceDetailGrid.setColSorting("na,na");
   invoiceDetailGrid.setColTypes("ro,ro");
   invoiceDetailGrid.enableAutoHeight(true);
   invoiceDetailGrid.enableMultiline(true);
   invoiceDetailGrid.enableColumnMove(true);
   invoiceDetailGrid.init();
   invoiceDetailGrid.enablePaging(true, 20, 2, "pagingArea", true, "infoArea");
   invoiceDetailGrid.setPagingSkin("bricks"); 	 
   invoiceDetailGrid.parse(ar,"jsarray");
</script>