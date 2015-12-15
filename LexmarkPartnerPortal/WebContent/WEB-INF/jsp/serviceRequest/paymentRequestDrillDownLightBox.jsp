<%@ include file="/WEB-INF/jsp/include.jsp"%>
<%@ include file="/WEB-INF/jsp/includeGrid.jsp"%>

<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/mps.css?version=<html:fileCacheVersion/>" />
<!--[if IE 7]>
	<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/mps_ie7.css?version=<html:fileCacheVersion/>" />
<![endif]-->
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/in-styles.css?version=<html:fileCacheVersion/>" />
<div id="contentDetailsPayment" class="div-style28">
<table width="800">
<tr>
<td width="100%">
			<div class="infoBox rounded shadow">
            <h4 class="color-black"><div class="columnInner"><spring:message code="partnerPayments.label.reqDetail"/></div></h4>
          <div class="portletBlock">
          <div class="columnsTwo">
            <div class="columnInner">
              <ul class="form">
                <li>
                  <label><spring:message code="requestInfo.label.serviceOrder.requestNumber"/></label>
                  ${paymentRequestDrillDownForm.requestNumber}
                <li>
                  <label><spring:message code="partnerPayments.label.custAccName"/></label>
                  ${paymentRequestDrillDownForm.customerAccountName}
                </li>
              </ul>
            </div>
          </div>
          <div class="columnsTwo">
            <div class="columnInner">
              <ul class="form">
                <li>
                  <label><spring:message code="partnerPayments.label.partFee"/></label>
                  ${paymentRequestDrillDownForm.totalPartFee}
				<li>
                  <label><spring:message code="partnerPayments.label.fullfillmentFee"/></label>
                  ${paymentRequestDrillDownForm.totalFulfillmentFee}
                </li>
				<li>
                  <label><spring:message code="partnerPayments.label.labor"/></label>
                  ${paymentRequestDrillDownForm.totalLabor}
                </li>
				<li>
                  <label><spring:message code="partnerPayments.label.addiPayment"/></label>
                  ${paymentRequestDrillDownForm.totalAdditionalPayments}
                </li>
              </ul>
            </div>
          </div>
          </div>
		  <style>
		  
		  </style>
		  <div>
		  <table class="displayGrid rounded shadow wFull">
		  	<thead>
		  	  <tr>
		  		<th class="text-align-center"><spring:message code="partnerPayments.label.part"/></th>
		  		<th class="text-align-center"><spring:message code="partnerPayments.label.suppliesDesc"/></th>
		  		<th class="text-align-center"><spring:message code="partnerPayments.label.quantity"/></th>
		  		<th class="text-align-center"><spring:message code="partnerPayments.label.partFee"/></th>
		  		<th class="text-align-center"><spring:message code="partnerPayments.label.fulfillmentFee"/></th>
		  		<th class="text-align-center"><spring:message code="partnerPayments.label.amount"/></th>
		  	  </tr>
		  	</thead>
		  	
		  		<c:forEach items="${paymentRequestDrillDownForm.activities}" var="paymentRequestActivityDetail" varStatus="counter" begin="0">
					<c:choose>
						<c:when test="${counter.count % 2 == 0}">
							<tr class="altRow">
						</c:when>
						<c:otherwise>
							<tr>
						</c:otherwise>
					</c:choose>
					<td class="table-td-style6">${paymentRequestActivityDetail.partNumber}</td>
					<td class="table-td-style6">${paymentRequestActivityDetail.partDescription}</td>
					<td class="table-td-style6">${paymentRequestActivityDetail.quantity}</td>
					<td class="table-td-style6">${paymentRequestActivityDetail.partFee}</td>
					<td class="table-td-style6">${paymentRequestActivityDetail.fulfillmentFee}</td>
					<td class="table-td-style6">${paymentRequestActivityDetail.amount}</td>
					</tr>
				</c:forEach>
		
		  </table>
		  </div>
		  </div>
</td>
</tr>
<tr><td align="center"> <button class="button" onClick="dialog.dialog('close');"><spring:message code="button.close" /></button></td></tr>
</table>
</div>