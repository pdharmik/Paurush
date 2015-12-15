<% request.setAttribute("subTabSelected","accountsReceivable"); %>
<%@ include file="subTab.jsp"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<%@ include file="/WEB-INF/jsp/includeGrid.jsp"%>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/mps.css?version=<html:fileCacheVersion/>" />
<!--[if IE 7]>
	<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/mps_ie7.css?version=<html:fileCacheVersion/>" />
<![endif]-->

<portlet:renderURL var="redirectToInvoicePage">
	<portlet:param name="action" value="redirectToInvoicePage"/>
</portlet:renderURL>

<portlet:resourceURL var="showSRDetails" id="showSRDetails">	
</portlet:resourceURL>

<portlet:resourceURL var="downloadARRequestHistoryList" id="downloadAPARRequestHistoryList">
</portlet:resourceURL>


<div id="content-wrapper">
    <div class="journal-content-article">
      <h1><spring:message code="partnerPayments.heading.receivableDetails"/></h1> 
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
                  <c:if test="${not empty sessionScope.accRecivablDetails}"> 
                  	<span id="partnerAcc">${sessionScope.accRecivablDetails["payeeAccnt"]}</span>
                  </c:if>
                <li>
                  <label><spring:message code="partnerPayments.label.soldToNo"/></label>
                  <!-- <span id="soldToNo">123456789</span> -->
                  <c:if test="${not empty sessionScope.accRecivablDetails}"> 
                  	<span id="soldToNo">${sessionScope.accRecivablDetails["soldToNo"]}</span>
                  </c:if>
                  </li>
                <li>
                  <label><spring:message code="partnerPayments.label.billtoAddress"/></label>
                  <c:if test="${not empty sessionScope.accRecivablDetails}"> 
                  	<c:if test="${!empty sessionScope.accRecivablDetails['addressLine1']}">
						${sessionScope.accRecivablDetails["addressLine1"]}<br>
					</c:if>
					<c:if test="${!empty sessionScope.accRecivablDetails['addressLine2']}">
						${sessionScope.accRecivablDetails["addressLine2"]}<br>
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
                  <label><spring:message code="partnerPayments.label.creditInvoiceDate"/></label>
                  <span>${sessionScope.accRecivablDetails['invoiceDt']}</span></li>				
				<li>
                  <label><spring:message code="partnerPayments.label.creditInvoiceAmt"/></label>
                  <span>${sessionScope.accRecivablDetails['amount']}</span> 
                  	<%--<c:if test="${not empty sessionScope.currencyAR}"> 
                  		(${currencyAR})
                  	</c:if>--%>
                  	<span id="currencyAR"></span>
                  
                 </li>
              </ul>
            </div>
          </div>
        </div>

        <!-- Request Status History - End -->
        <div class="portletBlock">
          <div class="columnsThree">
			<div class="infoBox columnInner rounded shadow">
			<!-- New adition to add xls -->
				<div class="utilities">
	              <ul>
	                <li class="first"><a href="#"><img height="23px" width="23px" src="<html:imagesPath/>transparent.png" class="ui-icon spreadsheet-icon" alt="<spring:message code="image.title.exportExcel"/>" title="<spring:message code="image.title.exportExcel"/>" onclick="downloadARInvoiceHistory('CSV');"></a></li>
	                <!--  <li><a href="#"><img height="23px" width="23px" src="<html:imagesPath/>transparent.png" class="ui-icon pdf-icon" alt="<spring:message code="image.title.exportPDF"/>" title="<spring:message code="image.title.exportPDF"/>" onclick="downloadAPInvoiceHistory('PDF');" /></a></li>
	                <li><a href="#"><img height="23px" width="23px" src="<html:imagesPath/>transparent.png" class="ui-icon print-icon" alt="<spring:message code="image.title.print"/>" title="<spring:message code="image.title.print"/>" onclick="print();"></a></li> -->
	              </ul>
	            </div>	
            <!-- End -->
			  <div class="portlet-wrap-inner">
			    <div id="detailsPaymentsGrid_container" class="gridbox gridbox_light"></div>
			  </div>
			  <div id="loadingNotification" class='gridLoading'>
							<br>Loading&nbsp;&nbsp;<img src="<html:imagesPath/>gridloading.gif" /><br>
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
                  <span id="requestNo"></span></li>
                <li>
                  <label><spring:message code="partnerPayments.label.custAccName"/></label>
                  <span id="customerAcct"></span></li>
              </ul>
            </div>
          </div>
          <div class="columnsTwo">
            <div class="columnInner">
              <ul class="form">
                <li>
                  <label><spring:message code="partnerPayments.label.partFee"/></label>
                  <span id="partFee"></span></li>
				<li>
                  <label><spring:message code="partnerPayments.label.fullfillmentFee"/></label>
                  <span id="fullfillFee"></span></li>
				<li>
                  <label><spring:message code="partnerPayments.label.labor"/></label>
                  <span id="labor"></span></li>
				<li>
                  <label><spring:message code="partnerPayments.label.addiPayment"/></label>
                  <span id="addPayment"></span></li>
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
		  <div id="loadingNotification_sr" class='gridLoading' style="display: none;">
							<br>Loading&nbsp;&nbsp;<img src="<html:imagesPath/>gridloading.gif" /><br>
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
   var srListURL='<portlet:resourceURL id="retrieveSRListXMLAR"></portlet:resourceURL>';
   var detailsPaymentsGrid;
   //var headerString = "Request Number,Amount(${currencyAR})";   
//    var headerString = "Request Number,Amount(USD)"; 
   detailsPaymentsGrid = new dhtmlXGridObject('detailsPaymentsGrid_container');
   detailsPaymentsGrid.setImagePath("<html:imagesPath/>gridImgs/");
   detailsPaymentsGrid.setHeader("<spring:message code="partnerPayments.listHeader.srListAR"/> (${sessionScope.accRecivablDetails['currency']})");
   
   detailsPaymentsGrid.setInitWidths("*,150");
   detailsPaymentsGrid.setColAlign("left,left");
   detailsPaymentsGrid.setSkin("light");
   detailsPaymentsGrid.setColSorting("na,na");
   detailsPaymentsGrid.setColTypes("ro,ro");
   detailsPaymentsGrid.enableAutoHeight(true);
   detailsPaymentsGrid.enableMultiline(true);
   detailsPaymentsGrid.enableColumnMove(true);
   detailsPaymentsGrid.init();
   detailsPaymentsGrid.i18n.paging.notfound = "<spring:message code='partnerPayments.srList.notFound'/>";
   detailsPaymentsGrid.enablePaging(true, 20, 5, "pagingArea", true, "infoArea");
   detailsPaymentsGrid.setPagingSkin("bricks"); 	
   detailsPaymentsGrid.attachEvent("onXLS",function(){
	   	jQuery('#loadingNotification').show();
	   });
   detailsPaymentsGrid.attachEvent("onXLE",function(){
	   	jQuery('#loadingNotification').hide();
		/*if(detailsPaymentsGrid.getAllRowIds() != "" ){
	    	var colLabel=detailsPaymentsGrid.getColLabel(1);
	    	var ind=colLabel.indexOf("(");
	    	jQuery('#currencyAR').html(colLabel.substring(ind+1,colLabel.length-1));
		}*/
	    
	   }); 
//    detailsPaymentsGrid.loadXMLString("${srForm.srListXML}");
   detailsPaymentsGrid.loadXML(srListURL);

   function showSRRequestDetail(serviceReqNo){
	   jQuery.ajax({
			url:'${showSRDetails}',			
			type:'POST',
			beforeSend: function()
			{
				jQuery('#loadingNotification_sr').show();
			},
			data: {
				 	//Ajax call with selected row data
					serviceReqNo :   serviceReqNo					
				},
			success: function(results){
				jQuery('#loadingNotification_sr').hide();	
				jQuery("#hiddenDataSRDetails").show();
				jQuery("#hiddenDataSRDetails").html(results);
				jQuery("#srDetailDiv").hide();
				jQuery('#partFee').html(jQuery('#partFeeHidden').val());
				jQuery("#requestNo").html(serviceReqNo);
				jQuery("#customerAcct").html(jQuery('#custAccName').val());
				jQuery('#fullfillFee').html(jQuery('#FullfillmentFee').val());
				jQuery('#labor').html(jQuery('#laborFee').val());
				jQuery('#addPayment').html(jQuery('#additionalPayments').val());
			}
	   });		
   }
   
   /* function showValues(serviceReqNo)
   {	
	   /* var paymentRowID = detailsPaymentsGrid.getAllRowIds();	
	   alert(paymentRowID);
	   var paymentArray = paymentRowID.split(",");
	   var partFee = 0.0;
	   var fullfillFee = 0.0;
		for(var i=0;i<paymentArray.length;i++)
		{
			alert("paymentArray[i] "+paymentArray[i]);
			partFee = partFee + parseFloat(detailsPaymentsGrid.cellById(paymentArray[i],3).getValue());
			fullfillFee = fullfillFee + parseFloat(detailsPaymentsGrid.cellById(paymentArray[i],4).getValue());
			alert("part Fee "+ partFee);
			alert("fullfill Fee "+ fullfillFee);
		} */	
		/* jQuery("#partFee").html('${partFee}');
		jQuery("#fullfillFee").html('${fulfillFee}'); */
		
		/*jQuery("#partFee").html(jQuery("#partFeeHidden").val());
		jQuery("#fullfillFee").html(jQuery("#FullfillmentFee").val());
		jQuery("#requestNo").html(serviceReqNo);
   } */
   
   function backToOrderList()
	{
		showOverlay();
		var url = "${redirectToInvoicePage}&typeId=AR&navPage=DETAIL";
		window.location.href = url;
	}

// new add dwijen
	function downloadARInvoiceHistory(type){
		//alert("Inside the download");
		 fromDate = jQuery("#fromDate").val();
		 endDate = jQuery("#toDate").val();
			//window.location="${downloadAPInvoiceHistoryList}&downloadType=" + type ;
			window.location="${downloadARRequestHistoryList}&downloadType=" + type +"&typeId=AR";
		}
</script>