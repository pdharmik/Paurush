<!--Created-->


<%@ taglib prefix="util" uri="/WEB-INF/tld/lexmark-util.tld" %>
<%@ page import = "com.lexmark.services.util.LexmarkSPOmnitureConstants" %>
<!-- CSS include -->

<!--[if IE 7]>
	<style type="text/css"><%@ include file="/WEB-INF/css/mps_ie7.css" %></style>
	<style type="text/css">
.ie7 .button, .ie7 .button_cancel {
behavior: url(/LexmarkServicesPortal/WEB-INF/css/PIE.htc) !important;
}
</style>
<![endif]-->
<!-- CSS include -->
<style>
.ie7 div{position:relative;}
.ie7 .status{position:static;}
.ie7 .status-bar{position:static;}
@media print{
.portletBlock , .infoBox, .columnInner, .rounded, .shadow{background:none!important;background-color:white!important;}
}
</style>
<% request.setAttribute("subTabSelected","requestHistory");%>
<%@ include file="/WEB-INF/jsp/common/subTab.jsp"%>
<jsp:include page="../includeGrid.jsp"></jsp:include>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<jsp:include page="/WEB-INF/jsp/includeGrid.jsp"/>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/mps.css"/>

<script type="text/javascript" src='<html:rootPath/>js/jquery.progressbar.js'></script>
<script type="text/javascript" src='<html:rootPath/>js/portletRedirection.js'></script>
<script type="text/javascript" src='<html:rootPath/>js/expand.js'></script>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/in-styles.css?version=<html:fileCacheVersion/>" />
<!-- Added for MPS 2.1 -->
<div id="dialogHardwareDetails" style="display: none;"></div>
<div id="dialogChangeDetails" style="display: none;"></div>
<!-- END -->
   <div class="journal-content-article">
      <h1><spring:message code="hardwareDetails.label.hardwareReqDetails"/></h1>
      <h2 class="step"><spring:message code="Details.supplyRequestDetails.label.rquestNumber"/><span id="reqNo"> ${requestForm.serviceRequest.serviceRequestNumber }</span></h2> 
      </div>
      <!-- div Added for email and Print Link -->
          <div class="utilities floatR div-style52" >
		  	<ul>
              <li class="first"><a href="javascript:email();"><img src="<html:imagesPath/>transparent.png" class="ui_icon_sprite email-icon" alt="Email this page" ></a></li>
              <li><a href="javascript:print();"><img src="<html:imagesPath/>transparent.png" class="ui_icon_sprite print-icon" alt="Print this page" ></a></li>
            </ul>
          </div>
    <div class="main-wrap">
      <div class="content" id="hardwareDetails"> 
      
      <!-- printHwDetails Added for Print -->
       <div id="printHwDetails">
        <!-- MAIN CONTENT BEGIN -->
        <div class="portletBlock infoBox rounded shadow split">
        <c:if test="${exceptionList != null}">
       			<div class="error" id="dispalyExceptions">
	       			<c:forEach items="${exceptionList}" var="entry">
			       		<li><strong><spring:message code="requestInfo.error.exceptionExist"/> - ${entry}</strong></li>		       		
			     	</c:forEach>
      			</div>
          	</c:if>
          <div class="columnsTwo">
            <div class="columnInner">
              <ul class="form">
                <li>
                  <label><spring:message code="requestInfo.label.area"/> </label>
                  <span id="area">${requestForm.serviceRequest.area.value }</span></li>
                <li>
                  <label><spring:message code="requestInfo.label.subArea"/> </label>
                  <span id="subarea">${requestForm.serviceRequest.subArea.value }</span></li>
				  <c:if test="${userSegment == 'Employee'}">
              	  <li>
	                <label><spring:message code="Details.supplyRequestDetails.label.orderSource"/></label>
	                <span id="orderSource">${requestForm.serviceRequest.orderSource } </span>
                   </li>
				    </c:if>
                   <li>
                  <label><spring:message code="Details.changeRequestDetails.label.status"/></label>
                  <span id="serviceRequestStatus">${requestForm.serviceRequest.serviceRequestStatus }</span>
                  </li>
                  <li>
                  <label><spring:message code="hardwareDetails.label.accName"/></label>
                  <span id="accountName">${requestForm.serviceRequest.accountName }</span>
                  </li>
              </ul>
            </div>
          </div>
          <div class="columnsTwo">
            <div class="columnInner">
              <ul class="form">
              <li>
                  <label><spring:message code="Details.changeRequestDetails.label.dateAndTime"/> </label>
                  <span id="creationTime">${requestForm.creationDate }</span></li>
                <li>
                  <label><spring:message code="Details.changeRequestDetails.label.requesterName"/> </label>
                  <span id="requestorName">${fn:trim(requestForm.serviceRequest.requestor.firstName)}&nbsp;${fn:trim(requestForm.serviceRequest.requestor.lastName)}</span></li>
                  
               <%--  Added for Mps Phase 2 start --%>
             
               <c:choose>
               		<c:when test='${requestForm.serviceRequest.billingModel ne "Usage Based Billing"}'>
               		<c:if test="${requestForm.serviceRequest.itemSubTotalBeforeTax ne ''&& requestForm.serviceRequest.itemSubTotalBeforeTax ne '0' && requestForm.serviceRequest.itemSubTotalBeforeTax ne null}">
               		  <li>
                  		<label><spring:message code="Details.changeRequestDetails.label.itemSubTotalBeforeTax"/> </label>
                  		<span id="beforeTax" class="currencyRight"><fmt:formatNumber type="number" maxFractionDigits="2" minFractionDigits="2" value="${requestForm.serviceRequest.itemSubTotalBeforeTax}" /> (${requestForm.serviceRequest.currency})
                  		</span></li>
                  		</c:if>
                  		<c:if test="${requestForm.serviceRequest.tax ne '' && requestForm.serviceRequest.tax ne '0' && requestForm.serviceRequest.tax ne null}">
                	  <li>
                  		<label><spring:message code="Details.changeRequestDetails.label.tax"/> </label>
                  		<span id="tax" class="currencyRight"><fmt:formatNumber type="number" maxFractionDigits="2" minFractionDigits="2" value="${requestForm.serviceRequest.tax}"/>(${requestForm.serviceRequest.currency})</span> </li>
                  		</c:if>
                  		<c:if test="${requestForm.serviceRequest.totalAmount ne '' && requestForm.serviceRequest.totalAmount ne '0' && requestForm.serviceRequest.totalAmount ne null}">
                	  <li>
                  		<label><spring:message code="Details.changeRequestDetails.label.total"/> </label>
                  		<span id="total" class="currencyRight"><fmt:formatNumber type="number" maxFractionDigits="2" minFractionDigits="2" value="${requestForm.serviceRequest.totalAmount}"/> (${requestForm.serviceRequest.currency})</span>
                  	  </li>
                  	  </c:if>
               		</c:when>               		              		
               </c:choose>
              
              
              
                  <%--  Added for Mps Phase 2 end --%>
              </ul>
            </div>
          </div>
        </div>
        <!-- Request Status History - Start -->
        <div class="portletBlock infoBox rounded shadow">
          <div class="columnsOne">
            <div class="columnInner">
              <h4 class="expand"><spring:message code="Details.supplyRequestDetails.heading.requestStatusHistory"/></h4>
              <div  class="collapse" id="statusExpand">
                <div id="div_statusPrintTable">
					<div id="tab_statusGrid" style="display:block;">
						<div id="div_status_container" class="div-style48"></div>
	    				<div class="pagination"><span id="div_statusPagingArea"></span>
	    				<span id="div_statusinfoArea"></span></div>
					</div>
				</div>
              </div>
            </div>
          </div>
        </div>
        <!-- Request Status History - End -->
		
        <!-- Associated Requests - Start -->
        <div class="portletBlock infoBox rounded shadow">
          <div class="columnsOne">
            <div class="columnInner">
              <h4 class="expand"><spring:message code="Details.supplyRequestDetails.label.associatedRequests"/></h4>
              <div class="collapse" id="associatedGridID" > 
                <!-- GRID 1 BEGIN -->
                 <div class="portlet-wrap rounded">
                  <div class="portlet-wrap-inner">
                    <div id="mygrid_container2" class="gridbox gridbox_light "></div>
                    <div id="loadingNotificationAssociatedRequests" class="gridLoading">
					<!--spring:message code="requestInfo.popup.loading"/ -->
					<img src="<html:imagesPath/>gridloading.gif" />
			  		</div>
                  </div>
                </div>
                <div class="pagination">
                <span id="pagingArea2"></span><span id="infoArea2"></span>
                </div>
                <!-- GRID 1 END --> 
              </div>
            </div>
          </div>
        </div>
        <!-- Associated Requests - End --> 
        
        <hr class="separator" />
        <div class="portletBlock">
          <div class="columnsTwo">
            <div class="infoBox columnInner rounded shadow">
              <h4><spring:message code="requestInfo.heading.primaryContactForThisRequest"/></h4>
              <ul class="form">
                <li>
                  <label><spring:message code="requestInfo.label.name"/></label>
                  <span id="primaryname">${fn:trim(requestForm.serviceRequest.primaryContact.firstName)}&nbsp;${fn:trim(requestForm.serviceRequest.primaryContact.lastName)}</span></li>
                <li>
                  <label><spring:message code="requestInfo.label.phoneNumber"/></label>
                  <span id="primaryphone">${requestForm.serviceRequest.primaryContact.workPhone }</span></li>
                <li>
                  <label><spring:message code="requestInfo.label.emailAddress"/></label>
                  <span id="primaryemail">${requestForm.serviceRequest.primaryContact.emailAddress }</span></li>
              </ul>
            </div>
            <c:if test="${requestForm.serviceRequest.secondaryContact.firstName != ''}">
            <div class="infoBox columnInner rounded shadow" id="secondarySection">
              <h4><spring:message code="requestInfo.heading.secondaryContactForThisRequest"/></h4>
              <ul class="form">
                <li>
                  <label><spring:message code="requestInfo.label.name"/></label>
                  <span id="secondaryname">${fn:trim(requestForm.serviceRequest.secondaryContact.firstName)}&nbsp;${fn:trim(requestForm.serviceRequest.secondaryContact.lastName)}</span></li>
                <li>
                  <label><spring:message code="requestInfo.label.phoneNumber"/></label>
                  <span id="secondaryphone">${requestForm.serviceRequest.secondaryContact.workPhone }</span></li>
                <li>
                  <label><spring:message code="requestInfo.label.emailAddress"/></label>
                  <span id="secondaryemail">${requestForm.serviceRequest.secondaryContact.emailAddress }</span></li>
              </ul>
            </div>
            </c:if>
          </div>
          <div class="columnsTwo">
            <div class="infoBox columnInner rounded shadow">
              <h4><spring:message code="requestInfo.heading.additionalInformationForThisRequest"/></h4>
              <ul class="form">
                <li>
                  <label><spring:message code="requestInfo.label.customerRefereceId"/></label>
                  <span id="customerReferenceNumber">${requestForm.serviceRequest.customerReferenceNumber }</span></li>
                <li>
                  <label><spring:message code="requestInfo.label.costCentre"/></label>
                  <span id="costCenter">${requestForm.serviceRequest.costCenter }</span></li>
                <li>
                  <label><spring:message code="requestInfo.label.description"/></label>
                  <span id="addtnlDescription" class="multiLine">${requestForm.serviceRequest.addtnlDescription}</span></li>
              </ul>
            </div>
          </div>
        </div>
        <hr class="separator" />
        <!-- PENDING SHIPMENT BLOCK - START -->
        <c:if test="${requestForm.pendingRequest.shipmentXML != null }">
        <div class="portletBlock infoBox rounded shadow" id="customerReqItems">
          <div class="columnsOne">
            <div class="columnInner">
              <h4><spring:message code="Details.supplyRequestDetails.heading.pendingshipment"/></h4>
             <div class="icon"><img class="ui_icon_sprite widgets inProcess-icon" src="<html:imagesPath/>transparent.png" width="100" height="100" /></div>
               <div class="columnInner separatorV">
                         <div id="div_inprocessPrintTable">
							<div id="tab_inprocessGrid"  style="display:block;">
								<div id="div_inprocessGridContainer" class="width-100per"></div>
	  							<div id="div_inprocessPagingArea"></div>
	  						</div>
	    				</div><!-- mygrid_container -->
	              	 	</div>
            </div>
          </div>
        </div>
        </c:if>
        <!-- PENDING SHIPMENT BLOCK - END --> 
        <!-- SHIPMENT BLOCK - START -->
      
        <c:forEach var="shipmentForm" items="${requestForm.serviceRequestShipments}" varStatus="status">
        <div class="portletBlock infoBox rounded shadow" id="suppliesShipment">
          <div class="columnsOne">
            <div class="columnInner">
              <h4><spring:message code="Details.supplyRequestDetails.label.shipment"/>&nbsp;
             		<c:choose>
						<c:when test="${shipmentForm.hasProperUrl}">
                    		<a class="shipment_link" href="${shipmentForm.carrierLink}" target="_blank">${shipmentForm.trackingNumber}</a>
                      	</c:when>
                      	<c:otherwise>
							${shipmentForm.trackingNumber}
						</c:otherwise>
					</c:choose></h4>
              <div class="icon"><img class="ui_icon_sprite widgets widgets-truck-rt-icon" src="<html:imagesPath/>transparent.png" width="100" height="100" /></div>
              <div class="columnInner separatorV">
              <table class="shipment wFull">
                  <tr>
                    <td class="w70p"><div class="status">
                        <div id="div_shipmentProgressBar${status.count}" class="status-bar"></div><!-- PROGRESS BAR WILL COME HERE -->
                        <table border="0" cellspacing="0" cellpadding="0">
                          <tr>
                             <td><spring:message code="serviceRequest.label.inProcess"/></td>
                            <td><spring:message code="serviceRequest.label.shipped"/></td>
                            <td><spring:message code="serviceRequest.label.delivered"/></td>
                          </tr>
                        </table>
                     </div></td>
                    <td><ul class="form wFull">
		                        <li>
		                          <label><spring:message code="serviceRequest.label.carrier"/>:</label>
		                          <span>${shipmentForm.carrier}</span></li>
		                        <li>
		                          <label><spring:message code="serviceRequest.label.shippedDate"/>:</label>
		                          <span>
		                          <util:dateFormat value="${shipmentForm.actualShipDate}" showTime="true"  timezoneOffset="${timeZoneOffset}">${shipmentForm.actualShipDate}</util:dateFormat>
		                          </span></li>
		                          <li>
		                          <label><spring:message code="Details.supplyRequestDetails.label.actualDeliveryDate"/></label>
		                          <span>
		                          <util:dateFormat value="${shipmentForm.actualDeliveryDate}" showTime="true"  timezoneOffset="${timeZoneOffset}">${shipmentForm.actualDeliveryDate}</util:dateFormat>
		                          </span></li>
		                </ul>

                    </td>
                  </tr>
                </table>
                <div id="div_shipmentPrintTable${status.count}">
				<div id="tab_shipmentGrid${status.count}"  style="display:block;">
					<div id="div_shipmentGrid_container${status.count}" class="width-100per"></div>
	 				<div id="div_shipmentPagingArea${status.count}"></div>
 				</div>
    			</div>
                </div>
            </div>
          </div>
        </div>
        <script type="text/javascript">
        var barImages = { 0: '<html:imagesPath/>progressBar/progressbg_red.gif',
        		45:	'<html:imagesPath/>progressBar/progressbg_half.gif',
           		55: '<html:imagesPath/>progressBar/progressbg_orange.gif',
        		70: '<html:imagesPath/>progressBar/progressbg_green.gif'};
        	var barImagesCancel = {0: '<html:imagesPath/>progressBar/progressbg_black.gif'};
			if(${shipmentForm.shipmentProgress==0}){
				jQuery('#div_shipmentProgressBar${status.count}').progressBar(100, { width:480,speed:25,increment:1,height:12,showText: false, boxImage: '<html:imagesPath/>progressBar/progressbar.gif', barImage:barImagesCancel} );
			}else{
				//alert('Status:: '+'${shipmentForm.shipmentProgress}');
				jQuery('#div_shipmentProgressBar${status.count}').progressBar(${shipmentForm.shipmentProgress}, { width:480,speed:25,increment:1,height:12,showText: false, boxImage: '<html:imagesPath/>progressBar/progressbar.gif', barImage:barImages} );
			}
			SRShipmentGrid${status.count} = new dhtmlXGridObject('div_shipmentGrid_container${status.count}');
	 		SRShipmentGrid${status.count}.setImagePath("<html:imagesPath/>gridImgs/");
	 		SRShipmentGrid${status.count}.setHeader("<spring:message code='serviceRequest.listHeader.shipment'/>");
	 		SRShipmentGrid${status.count}.setColAlign("right,left,left,left,left,left,right");
	 		SRShipmentGrid${status.count}.setColTypes("sub_row,ro,ro,ro,ro,ro,ro");
	 		//SRShipmentGrid${status.count}.setColSorting("str,str,str,str,str");
	 		SRShipmentGrid${status.count}.setInitWidths("30,150,220,150,120,70,100");
	 		SRShipmentGrid${status.count}.enableAutoWidth(true);
	 		SRShipmentGrid${status.count}.enableMultiline(true);
	 		SRShipmentGrid${status.count}.enableAutoHeight(true);
	 		SRShipmentGrid${status.count}.init(); 
	 		SRShipmentGrid${status.count}.prftInit();
	 		SRShipmentGrid${status.count}.enablePaging(true, 5, 10, "div_shipmentPagingArea${status.count}", true);
	 		SRShipmentGrid${status.count}.setPagingSkin("bricks");
	 		SRShipmentGrid${status.count}.setSkin("light");
	 		SRShipmentGrid${status.count}.setColumnHidden(4,true);
			// CHanges for MPS 2.1
	 		var priceAvailableFlag${status.count}=false;
	 		<c:if test='${requestForm.serviceRequest.billingModel eq "Ship and Bill"}'>
	 			 priceAvailableFlag${status.count}=true;
	 			SRShipmentGrid${status.count}.attachEvent("onRowCreated",function(rowId){
	 			var cellObj${status.count} = SRShipmentGrid${status.count}.cellById(rowId,6);
	 			//alert('row getting created'+cellvalue${status.count}.length);
 				var cellvalue${status.count}=cellObj${status.count}.getValue();
 				if(cellvalue${status.count}==0){
 	 				cellObj${status.count}.setValue("<spring:message code='requestInfo.error.priceUnavailable'/>");
 				}
 				if(cellvalue${status.count}!=0){
 					cellObj.setValue(cellvalue${status.count} + " "+"("+'${requestForm.serviceRequest.currency}'+")");
 				}
	 			});
	 		</c:if>
	 		
	 		<c:if test='${requestForm.serviceRequest.billingModel eq "Delayed Purchase"}'>
	 				SRShipmentGrid${status.count}.attachEvent("onRowCreated",function(rowId){
		 				var cellObj${status.count} = SRShipmentGrid${status.count}.cellById(rowId,6);
		 				var cellvalue${status.count}=cellObj${status.count}.getValue();
		 				//alert('row getting created'+cellvalue${status.count}.length);
		 				if(cellvalue${status.count}!="" && cellvalue${status.count}!=0)
		 					priceAvailableFlag${status.count}=true;
	 			});
	 		</c:if>
	 			//CHanges for MPS 2.1 ENDS
	 		SRShipmentGrid${status.count}.loadXMLString('${shipmentForm.shipmentXML}');
				// CHanges for MPS 2.1
	 		if(priceAvailableFlag${status.count}==false){
	 			SRShipmentGrid${status.count}.setColumnHidden(6,true);
	 		}
	 		// CHanges for MPS 2.1 ENDS			
			</script>
       </c:forEach>
        <!-- SHIPMENT BLOCK - END --> 
        
        <!-- ACTIVITY BLOCK - END -->
        <!-- CANCELLED BLOCK - START -->
        <c:if test="${requestForm.cancelledPartsXML != null}">
        <div class="portletBlock infoBox rounded shadow" id="cancelBlockID">  <%-- id added for email --%>
         <div class="columnsOne">
            <div class="columnInner">
              <h4><spring:message code="Details.supplyRequestDetails.heading.cancelled"/></h4>
              <div class="icon"><img src="<html:imagesPath/>cancelled.jpg" width="100" height="100" /></div>
              <div class="columnInner separatorV">
                <table class="shipment wFull">
                  <tr>
                    <td class="w70p"><div class="status">
                        <div id="div_cancelProgressBar" class="status-bar"><!-- PROGRESS BAR WILL COME HERE --></div>
                        <table border="0" cellspacing="0" cellpadding="0">
                          <tr>
                          
                            <td><spring:message code="Details.supplyRequestDetails.heading.cancelled"/></td>
                          </tr>
                        </table>
                      </div></td>
                    <td>&nbsp;</td>
                  </tr>
                </table>
                 <div id="div_cancelPrintTable">
							<div id="tab_cancelGrid" style="display:block;">
								<div id="div_cancelContainer" class="div-style48"></div>
    							<div id="div_cancelPagingArea"></div>
    						</div>
    				</div>
              </div>
            </div>
          </div>
        </div>
        </c:if>
        <!-- CANCELLED BLOCK - END -->
        <!-- SHIPPING AND BILLING INFO BLOCK START -->
        <p class="inlineTitle"><spring:message code="requestInfo.heading.shippingandBillngInformation"/></p>
        <div class="portletBlock">
          <div class="columnsTwo">
            <div class="infoBox columnInner rounded shadow">
              <h4><spring:message code="requestInfo.heading.shipToAddress"/></h4>
              <ul class="roDisplay">
                <li id="shipToAddress"><div>${requestForm.serviceRequest.serviceAddress.storeFrontName}</div>
                  <util:addressOutput value="${requestForm.serviceRequest.serviceAddress}"></util:addressOutput>
                 <%-- <div>${requestForm.serviceRequest.serviceAddress.addressLine1}</div>
                  <div>${requestForm.serviceRequest.serviceAddress.officeNumber}</div>
                  <c:if test="${requestForm.serviceRequest.serviceAddress.addressLine2 !='' && requestForm.serviceRequest.serviceAddress.addressLine2!=null}">
                  <div>${requestForm.serviceRequest.serviceAddress.addressLine2}, </div>
                  </c:if>
                  <div>${requestForm.serviceRequest.serviceAddress.addressLine3}</div>
                  <span>${requestForm.serviceRequest.serviceAddress.city},
                  <c:if test="${requestForm.serviceRequest.serviceAddress.county != '' && requestForm.serviceRequest.serviceAddress.county!=null}">
                  ${requestForm.serviceRequest.serviceAddress.county},
                  </c:if>
                  <c:if test="${requestForm.serviceRequest.serviceAddress.state != '' && requestForm.serviceRequest.serviceAddress.state != null}">
                  ${requestForm.serviceRequest.serviceAddress.state}
                  </c:if>
                  <c:if test="${requestForm.serviceRequest.serviceAddress.province !='' && requestForm.serviceRequest.serviceAddress.province != null}">
                  , ${requestForm.serviceRequest.serviceAddress.province}
                  </c:if>
                  <c:if test="${requestForm.serviceRequest.serviceAddress.district !='' && requestForm.serviceRequest.serviceAddress.district != null}">
                  , ${requestForm.serviceRequest.serviceAddress.district}
                  </c:if>
                  </span>
                  <div>${requestForm.serviceRequest.serviceAddress.postalCode}</div> 
                  <div>${requestForm.serviceRequest.serviceAddress.country}</div> --%>
                  </li>
                <li><label for="building"><spring:message code="requestInfo.addressInfo.label.building"/> </label><span id="building"> ${requestForm.serviceRequest.serviceAddress.physicalLocation1}</span></li>
                <li><label for="floor"><spring:message code="requestInfo.addressInfo.label.floor"/> </label><span id="floor"> ${requestForm.serviceRequest.serviceAddress.physicalLocation2}</span></li>
                <li><label for="office"><spring:message code="requestInfo.addressInfo.label.office"/>  </label><span id="office"> ${requestForm.serviceRequest.serviceAddress.physicalLocation3}</span></li>
              </ul>
            </div>
          </div>
          <div class="columnsTwo">
            <div class="infoBox columnInner rounded shadow">
              <h4><spring:message code="requestInfo.heading.orderInstructions"/></h4>
              <ul class="form">
                <li>
                  <label for="desc"><spring:message code="requestInfo.label.specialInstructions"/></label>
                  <span class="multiLine instructionsScroll" id="specialInstructions">${requestForm.serviceRequest.specialInstructions}</span></li>
                
              </ul>
            </div>
          </div>
        </div>
        <!-- SHIPPING AND BILLING INFO BLOCK END -->
        <!-- PAYMENT BLOCK START -->
        <c:if test="${(requestForm.serviceRequest.poNumber ne '') || (requestForm.serviceRequest.creditCardToken ne '' || requestForm.serviceRequest.creditCardToken ne null)}">
        <div class="portletBlock" id="paymentBlock">
          <div class="columnsOne">
            <div class="infoBox columnInner rounded shadow">
              <h4><spring:message code="requestInfo.heading.paymentDetails"/></h4>
               <ul class="form">
                <c:if test="${fn:trim(requestForm.serviceRequest.poNumber) != '' }">
                <li>
                  <label for="poNo"><spring:message code="Details.supplyRequestDetails.label.poNumber"/></label>
                  <span id="poNumber"> ${requestForm.serviceRequest.poNumber } </span></li>               
               </c:if>
               <c:if test="${fn:trim(requestForm.serviceRequest.creditCardToken) != '' }">
			   <li>
			      <label for="cct"><spring:message code="requestInfo.label.creditCardNo"/></label>
			      <span id="creditCard"> ${requestForm.serviceRequest.creditCardToken}</span></li>
			   
		       </c:if>
                
              </ul>
            </div>
          </div>
        </div>
        </c:if>
        <!-- PAYMENT BLOCK END -->
        <!-- NOTES AND ATTACHMENT BLOCK START -->
        <p class="inlineTitle"><spring:message code="requestInfo.heading.notes"/> &amp; <spring:message code="requestInfo.heading.attachments"/></p>
        <div class="portletBlock">
          <div class="columnsOne">
            <div class="infoBox columnInner rounded shadow">
              <h4><spring:message code="requestInfo.heading.notes"/></h4>
              <p class="w70p multiLine"><span id="notes">${requestForm.serviceRequest.notes}</span></p>
            </div>
          </div>
        </div>
        <div class="portletBlock">
            <div class="infoBox columnInner rounded shadow">
            <h4><spring:message code="requestInfo.heading.attachments"/></h4>
          <div class="columnInner">
            <table class="displayGrid rounded shadow wFull">
              <thead>
               
              </thead>
              </table>
              <div id ="showAttachment">  <%-- div-id added for email --%>
              <table>
              <tbody>
              		<% int c = 1; %>
              		<c:forEach items="${requestForm.attachList}" var="entry" >
              			<% if(c%2==0){ %>
	              			<tr class="altRow" >
	              				<c:if test="${entry.extension != 'URL'}">
					           		<td><a href="javascript:void(0);" onclick="downloadAttachment('${entry.attachmentName}','${entry.displayAttachmentName}','${entry.extension}', '${entry.activityId}');" >${entry.displayAttachmentName}.${entry.extension}</a></td>
					           	</c:if>
					           	<c:if test="${entry.extension == 'URL'}">
					           		<td><a href="javascript:void(0);" onclick="downloadAttachment('${entry.attachmentName}','${entry.displayAttachmentName}','${entry.extension}', '${entry.activityId}');" >${entry.attachmentName}</a></td>
					           	</c:if>
					        </tr>
              			<% }else{ %>
	              			<tr>
					           		<c:if test="${entry.extension != 'URL'}">
					           		<td><a href="javascript:void(0);" onclick="downloadAttachment('${entry.attachmentName}','${entry.displayAttachmentName}','${entry.extension}', '${entry.activityId}');" >${entry.displayAttachmentName}.${entry.extension}</a></td>
					           	</c:if>
					           	<c:if test="${entry.extension == 'URL'}">
					           		<td><a href="javascript:void(0);" onclick="downloadAttachment('${entry.attachmentName}','${entry.displayAttachmentName}','${entry.extension}', '${entry.activityId}');" >${entry.attachmentName}</a></td>
					           	</c:if>
					        </tr>
              			<% }
              			   c++;
              			%>
						
					</c:forEach>
              	</tbody>
            </table>
            </div>
          </div>
          </div>
        </div>
        </div>
        <!-- NOTES AND ATTACHMENT BLOCK END -->
        <div class="buttonContainer">
          <button id="backButton" class="button_cancel" type="button" onclick="backToHistory(); callOmnitureAction('<%=LexmarkSPOmnitureConstants.HARDWAREREQUESTDETAILS%>','<%=LexmarkSPOmnitureConstants.HARDWAREREQUESTHISTORYDETAILSBACK%>');"><spring:message code="button.back"/></button>
          <button id="updateButton" class="button" onclick="updateRequest();callOmnitureAction('<%=LexmarkSPOmnitureConstants.HARDWAREREQUESTDETAILS%>','<%=LexmarkSPOmnitureConstants.HARDWAREREQUESTHISTORYDETAILSUPDATE%>');"><spring:message code="Details.changeRequestDetails.button.updateReq"/></button>
          <c:if test="${requestForm.serviceRequestShipments == null && requestForm.serviceRequest.serviceRequestStatus != 'Completed'}">
		  	<c:if test="${sessionScope.isStandardUser eq false}">
	         <button id="cancelSupplyButton" class="button_cancel" type="button" onclick="cancelRequest();callOmnitureAction('<%=LexmarkSPOmnitureConstants.HARDWAREREQUESTDETAILS%>','<%=LexmarkSPOmnitureConstants.HARDWAREREQUESTHISTORYDETAILSCANCEL%>');"><spring:message code="Details.changeRequestDetails.button.cancelReq"/></button>
	          </c:if>
		  	
		  </c:if>
        </div>
      </div>
      <!-- MAIN CONTENT END --> 
    </div>
<script type="text/javascript">
if (window.PIE) {
    document.getElementById("backButton").fireEvent("onmove");
    document.getElementById("updateButton").fireEvent("onmove");
    <c:if test="${requestForm.serviceRequestShipments == null && requestForm.serviceRequest.serviceRequestStatus != 'Completed'}">
    document.getElementById("cancelSupplyButton").fireEvent("onmove");
    </c:if>
}

var buttonName="";
var requestTypeList = [];	
<c:forEach items="${requestTypeLOVMap}" var="requestType" varStatus = "status" >
	requestTypeList[${status.index}] = ["${requestType.key}","${requestType.value}"];
</c:forEach>

var requestAreaList = [];
	<c:forEach items="${requestAreaLOVMap}" var="requestArea" varStatus = "status" >
	requestAreaList[${status.index}] = ["${requestArea.key}","${requestArea.value}"];
	</c:forEach>
		
var requestStatusList = [];		
	<c:forEach items="${requestStatusLOVMap}" var="requestStatus" varStatus = "status" >
	requestStatusList[${status.index}] = ["${requestStatus.key}","${requestStatus.value}"];
	</c:forEach>



////Toggle Containers
jQuery(function() {
    jQuery("h4.expand").toggler();
});



jQuery(document).ready(function(){
	//alert('${requestForm.serviceRequest.billingModel}');
	loadAssociatedRequestsGrid();
	//<c:if test="${requestForm.serviceRequest.asset.serialNumber != null &&  requestForm.serviceRequest.asset.serialNumber != ''}">
	//loadpastGrid();
	//</c:if>
	textScroll(document.getElementById('addtnlDescription'));
	textScroll(document.getElementById('specialInstructions'));
	textScroll(document.getElementById('notes'));
	textScroll(document.getElementById('costCenter'));

	//jQuery('#sr_technician_div').show();

});


/** 
	 *  status_container config begin
	 */
	SRStatusGrid = new dhtmlXGridObject('div_status_container');
	SRStatusGrid.setImagePath("<html:imagesPath/>gridImgs/");
	SRStatusGrid.setHeader("<spring:message code='serviceRequest.listHeader.srstatus'/>");
	SRStatusGrid.setColAlign("left,left,left");
	SRStatusGrid.setColTypes("ro,ro,ro");
	//SRStatusGrid.setColSorting("str,str,str");
	SRStatusGrid.setInitWidths("250,250,450");
	SRStatusGrid.enableAutoWidth(true);
	SRStatusGrid.enableMultiline(true);
	SRStatusGrid.enableAutoHeight(true);
	SRStatusGrid.init(); 
	SRStatusGrid.prftInit();
	SRStatusGrid.enablePaging(true, 5, 10, "div_statusPagingArea", true);  	
	SRStatusGrid.setPagingSkin("bricks");
	SRStatusGrid.setSkin("light");
	//alert('${requestForm.srStausXML}');
	SRStatusGrid.loadXMLString('${requestForm.srStausXML}'); 
	
	function loadAssociatedRequestsGrid(){
		var associatedRequestHistoryGrid;
		var customizedGridURL="${requestForm.associatedRequestHistoryListXML}";		
		associatedRequestHistoryGrid = new dhtmlXGridObject('mygrid_container2');
		associatedRequestHistoryGrid.setImagePath("<html:imagesPath/>gridImgs/");
		associatedRequestHistoryGrid.setHeader("<spring:message code='suppliesmanagement.details.srhistorygrid'/>");
		//associatedRequestHistoryGrid.attachHeader("#text_filter,,#combo_filter,#text_filter,#combo_filter");
		associatedRequestHistoryGrid.setInitWidths("140,160,160,160,160");
		
		associatedRequestHistoryGrid.setColAlign("left,left,left,left,left");
		associatedRequestHistoryGrid.setColTypes("ro,ro,co,ro,co");
		associatedRequestHistoryGrid.setColSorting("str,na,na,na,na");
		associatedRequestHistoryGrid.enableAutoHeight(true);
		associatedRequestHistoryGrid.enableMultiline(true);

		associatedRequestHistoryGrid.a_direction = "asc";
		associatedRequestHistoryGrid.enableColumnMove(true);
		
		associatedRequestHistoryGrid.init();
		associatedRequestHistoryGrid.prftInit();
		associatedRequestHistoryGrid.sortIndex = 0;
		associatedRequestHistoryGrid.setSkin("light");
		associatedRequestHistoryGrid.filterValues = ",,,,";
		//alert('b4 paging');
		associatedRequestHistoryGrid.enablePaging(true,5, 5, "pagingArea2", true, "infoArea2");
		//alert('after paging');
		associatedRequestHistoryGrid.setPagingSkin("bricks");
		//alert(" associated history url "+ '${requestForm.associatedRequestHistoryListXML}');
		//alert(" asdf"+ '${requestForm.srHistoryListXML}');
		associatedRequestHistoryGrid.attachEvent("onXLS", function() {
			jQuery('#loadingNotificationAssociatedRequests').show();
			jQuery('#pagingArea2').hide();
			if(associatedRequestHistoryGrid.a_direction=='asc'){
				associatedRequestHistoryGrid.setSortImgState(true, associatedRequestHistoryGrid.getDefaltSortImagIndex(associatedRequestHistoryGrid.sortIndex,0), 'asc');
	        }else{
	        	associatedRequestHistoryGrid.setSortImgState(true, associatedRequestHistoryGrid.getDefaltSortImagIndex(associatedRequestHistoryGrid.sortIndex,0), 'des');
	        }
		});
		associatedRequestHistoryGrid.attachEvent("onXLE", function() {
			jQuery('#loadingNotificationAssociatedRequests').hide();
			var rowNum=associatedRequestHistoryGrid.getRowsNum();
			if(rowNum !=0){
			jQuery('#associatedGridID').show();
			}
			jQuery('#pagingArea2').show();
			//alert('xle');
			setTimeout(function(){
	    		rebrandPagination();
	    	
	    	},100);
			});
		
		associatedRequestHistoryGrid.attachEvent("onBeforeSorting",function(ind){
			var a_state = associatedRequestHistoryGrid.getSortingState();
			if(a_state[0] == (ind)){
				associatedRequestHistoryGrid.a_direction = ((a_state[1] == "asc") ? "des":"asc" );
			}else {
				associatedRequestHistoryGrid.a_direction = "asc" ;
				associatedRequestHistoryGrid.columnChanged = true;
			}
			associatedRequestHistoryGrid.sortIndex = ind;
			customizedGridURL = updateGridURL(customizedGridURL, associatedRequestHistoryGrid.getSortColByOffset(), 
					associatedRequestHistoryGrid.a_direction, associatedRequestHistoryGrid.filterValues);
			associatedRequestHistoryGrid.clearAndLoad(customizedGridURL);
			return true;
		});
		associatedRequestHistoryGrid.attachEvent("onFilterStart", function(indexes,values){
			//alert("onFilterStart");
	    	associatedRequestHistoryGrid.filterValues = values+",";
	    	customizedGridURL = updateGridURL(customizedGridURL, associatedRequestHistoryGrid.getSortColByOffset(),
	    			associatedRequestHistoryGrid.a_direction, associatedRequestHistoryGrid.filterValues);
	    	//alert("customizedGridURL  "+customizedGridURL);
	    	setGridFilerTimer(function(){
	    		associatedRequestHistoryGrid.clearAndLoad(customizedGridURL);
		    	});
	    });

			associatedRequestHistoryGrid.setCustomizeCombo(requestTypeList,2);
			associatedRequestHistoryGrid.setCustomizeCombo(requestStatusList,4);
		
		
		customizedGridURL=updateGridURL(customizedGridURL, associatedRequestHistoryGrid.getSortColByOffset(),
				associatedRequestHistoryGrid.a_direction, associatedRequestHistoryGrid.filterValues);
	
	
		//For temp
		associatedRequestHistoryGrid.loadXML(customizedGridURL);
		//associatedRequestHistoryGrid.loadXML('${requestForm.associatedRequestHistoryListXML}');

	
	}
	if(${requestForm.pendingRequest.shipmentXML!= null}){
		SRInProgressGrid = new dhtmlXGridObject('div_inprocessGridContainer');
			<%-- CHanges for MPS 2.1--%>
			var priceAvailableFlag=false;
			<c:if test='${requestForm.serviceRequest.billingModel eq "Ship and Bill"}'>
			 priceAvailableFlag=true;
			 SRInProgressGrid.attachEvent("onRowCreated",function(rowId){
					
					var cellObj = SRInProgressGrid.cellById(rowId,6);
					var cellvalue=cellObj.getValue();
					<%--alert('row getting created'+cellvalue.length);--%>
					if(cellvalue==0){
						cellObj.setValue("<spring:message code='requestInfo.error.priceUnavailable'/>");
	 				}
				});
			</c:if>
			<%-- CHanges for MPS 2.1 ENDS --%>
		
		SRInProgressGrid.setImagePath("<html:imagesPath/>gridImgs/");
		SRInProgressGrid.setHeader("<spring:message code='serviceRequest.listHeader.pendingshipment2'/>");
		SRInProgressGrid.setColAlign("left,left,left,left,left,left,right");
		SRInProgressGrid.setColTypes("sub_row,ro,ro,ro,ro,ro,ro");
		//SRInProgressGrid.setColSorting("str,str,str,str,str");
		SRInProgressGrid.setInitWidths("30,180,200,180,100,100,*");
		SRInProgressGrid.enableAutoWidth(true);
		SRInProgressGrid.enableMultiline(true);
		SRInProgressGrid.enableAutoHeight(true);
		SRInProgressGrid.enableResizing("false,false,false,false,false,false,false");
		SRInProgressGrid.init(); 
		SRInProgressGrid.prftInit();
		SRInProgressGrid.setColumnHidden(5,true);
		if(${fromSource!="parts"}){
			SRInProgressGrid.setColumnHidden(0,true);
		}
		SRInProgressGrid.enablePaging(true, 5, 10, "div_inprocessPagingArea", true);
		SRInProgressGrid.setPagingSkin("bricks");
		SRInProgressGrid.setSkin("light");
		<%-- CHanges for MPS 2.1--%>
		<c:if test='${requestForm.serviceRequest.billingModel eq "Delayed Purchase"}'>
			SRInProgressGrid.attachEvent("onRowCreated",function(rowId){
				
				var cellObj = SRInProgressGrid.cellById(rowId,6);
				var cellvalue=cellObj.getValue();
				if(cellvalue=="<spring:message code='requestInfo.error.priceUnavailable'/>"){
					SRInProgressGrid.setColumnHidden(6,true);
					}
				<%--alert('row getting created'+cellvalue.length);--%>
				if(cellvalue!=""&&cellvalue!=0)
					priceAvailableFlag=true;
			});
		</c:if>
		
		
		<%-- CHanges for MPS 2.1 ENDS--%>
		<%--SRInProgressGrid.loadXMLString('${requestForm.pendingRequest.shipmentXML}');--%>
		SRInProgressGrid.loadXMLString('${requestForm.pendingRequest.shipmentXML}');
		<%-- CHanges for MPS 2.1--%>
		if(priceAvailableFlag==false)
			SRInProgressGrid.setColumnHidden(6,true);
		<%-- CHanges for MPS 2.1 ENDS--%>
		
		if(SRInProgressGrid.getRowsNum() == 0 ){
			jQuery('#customerReqItems').hide();
			}else{
				jQuery('#customerReqItems').show();
				}
			}
		
	if(${requestForm.cancelledPartsXML!= null}){
		SRCancelledGrid = new dhtmlXGridObject('div_cancelContainer');
		SRCancelledGrid.setImagePath("<html:imagesPath/>gridImgs/");
		SRCancelledGrid.setHeader("<spring:message code='serviceRequest.listHeader.cancelled'/>");
		SRCancelledGrid.setColAlign("left,left,left");
		SRCancelledGrid.setColTypes("ro,ro,ro");
		//SRCancelledGrid.setColSorting("str,str,str,str,str");
		SRCancelledGrid.setInitWidths("180,250,100");
		SRCancelledGrid.enableAutoWidth(true);
		SRCancelledGrid.enableMultiline(true);
		SRCancelledGrid.enableAutoHeight(true);
		SRCancelledGrid.init(); 
		SRCancelledGrid.prftInit();
		SRCancelledGrid.enablePaging(true, 5, 10, "div_cancelPagingArea", true);
		SRCancelledGrid.setPagingSkin("bricks");
		SRCancelledGrid.setSkin("light");
		SRCancelledGrid.loadXMLString('${requestForm.cancelledPartsXML}'); 		
        	var barImagesCancel = {0: '<html:imagesPath/>progressBar/progressbg_black.gif'};
        	jQuery("#div_cancelProgressBar").progressBar(100 , { width:480,speed:25,increment:1,height:12,showText: false, boxImage: '<html:imagesPath/>progressBar/progressbar.gif', barImage : barImagesCancel} );
		 }

	 

	if(${requestForm.serviceRequest.serviceActivityStatus!= null}){
		if(${requestForm.serviceRequestTechnicianProgress==0}){
			jQuery("#div_technicianProgressBar1").progressBar(100 , { width:480,speed:25,increment:1,height:12,showText: false, boxImage: '<html:imagesPath/>progressBar/progressbar.gif', barImage : barImagesCancel} );
		}else{
	  		jQuery("#div_technicianProgressBar1").progressBar(${requestForm.serviceRequestTechnicianProgress}, { width:480,speed:25,increment:1,height:12,showText: false, boxImage: '<html:imagesPath/>progressBar/progressbar.gif', barImage : barImages} );
		}
	}
	


function downloadAttachment(attachmentName, dispAttachmentName, fileExtension, identifier){
	callOmnitureAction('<%=LexmarkSPOmnitureConstants.HARDWAREHIATORYDETAILS%>','<%=LexmarkSPOmnitureConstants.HARDWAREDOWNLOADATTACHMENT%>');
	var attachmentNameEncoding = encodeURIComponent(attachmentName);
	var dispAttachmentNameEncoding =encodeURIComponent(dispAttachmentName);
	  	if(fileExtension == "URL" || fileExtension == "url"){
	  		var url = "http://"+attachmentName;
	  		var iWidth=1000;
		    var iHeight=600;
		    var iTop = (window.screen.availHeight-30-iHeight)/2;
		    var iLeft = (window.screen.availWidth-10-iWidth)/2;
		    window.open(url,
		    		'_blank',
				    'height='+iHeight+
				    ',innerHeight='+
				    iHeight+',width='+
				    iWidth+',innerWidth='+
				    iWidth+',top='+iTop+
				    ',left='+iLeft+
				    ',status=yes,toolbar=yes,menubar=yes,location=yes,resizable=yes,scrollbars=yes,titlebar=no');
	  	}else{
	  		window.location = "${downloadAttachment}&attachmentName=" +attachmentNameEncoding + "&fileExtension=" + fileExtension + "&identifier=" + identifier + "&dispAttachmentName=" + dispAttachmentNameEncoding;
	  	}
	  }

function cancelRequest(){
	callOmnitureAction('<%=LexmarkSPOmnitureConstants.HARDWAREHIATORYDETAILS%>','<%=LexmarkSPOmnitureConstants.HARDWARECANCELREQUEST%>');
	showOverlay();
	// forwardURL = populateURL("managechlothers",['forward', 'serviceRequestNumber', 'typeOfFlow', 'requestType','subArea'],['Others', '${requestForm.serviceRequest.serviceRequestNumber }', 'cancel','Hardware SR','Hardware SR']);
	 var forwardURL = populateURL("managechlothers",['forward', 'serviceRequestNumber', 'typeOfFlow','area','subArea','pagetype'],['Others', '${requestForm.serviceRequest.serviceRequestNumber }', 'cancel','Cancel Request','HW Request','hardware']);
	window.location.href=forwardURL;
}

function backToHistory(){
	callOmnitureAction('<%=LexmarkSPOmnitureConstants.HARDWAREHIATORYDETAILS%>','<%=LexmarkSPOmnitureConstants.HARDWAREBACK%>');
		//alert("Inside backToHistory::" + '${requestForm.sourcePage}');
		showOverlay();
		var backUrl =  "<portlet:renderURL><portlet:param name='action' value='backToHistory' />	</portlet:renderURL>" ;
		backUrl = backUrl+'&requestTypeStr=' + '${requestForm.sourcePage}';
		//alert("backUrl:: " + backUrl);
		var sourcepage="${requestForm.sourcePage}";
		if(sourcepage=="DM"){
	  			window.location.href="device-finder?assetRowId=${assetRowId}&isDeviceBookmarked=${isDeviceBookmarked}&backHistory=true&lclick=${linkClicked}";
	  	}else{
	  		window.location=backUrl;
	  	  	}
  		
		
	}

function updateRequest(){
	callOmnitureAction('<%=LexmarkSPOmnitureConstants.HARDWAREHIATORYDETAILS%>','<%=LexmarkSPOmnitureConstants.HARDWAREUPDATEREQUEST%>');
		showOverlay();
		var area = '${updateTypeFlag}';
		var page ;
		var forward ;
		var forwardURL ;
		
		if(area == 'Asset'){
			forwardURL = populateURL("manageassets",['serviceRequestNumber', 'updateFlag'],['${requestForm.serviceRequest.serviceRequestNumber }', 'true']);
		}
		else if(area == 'Address'){
			forwardURL = populateURL("manageaddresses",['serviceRequestNumber', 'updateFlag'],['${requestForm.serviceRequest.serviceRequestNumber }', 'true']);
		}
		else if(area == 'Contact'){
			forwardURL = populateURL("managecontacts",['serviceRequestNumber', 'updateFlag'],['${requestForm.serviceRequest.serviceRequestNumber }', 'true']);
		}
		else if(area == 'CHL'){
			forwardURL = populateURL("managechlothers",['forward', 'serviceRequestNumber'],['CHL', '${requestForm.serviceRequest.serviceRequestNumber }']);
		}
		else{
			forwardURL = populateURL("managechlothers",['forward', 'serviceRequestNumber', 'typeOfFlow','area','subArea'],['Others', '${requestForm.serviceRequest.serviceRequestNumber }', 'update','Update Request','HW Request']);
		}
		window.location.href=forwardURL;
	}

/* START Added for MPS 2.1 */
var dialog;
function srDetailsPopup(ServiceRqstNumber,ServiceRqstType,ServiceRqstArea,ServiceRqstSubArea) {
	var url1="<portlet:renderURL windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>"><portlet:param name='action' value='showHardwareDetailsPopup'/></portlet:renderURL>&requestType="+
	ServiceRqstType+"&serviceRequestNumber="+ServiceRqstNumber+"&timeZoneOffset="+timezoneOffset+"&srArea="+ServiceRqstArea+"&srSubArea="+ServiceRqstSubArea;
	url1= encodeURI(url1);
	 var url2="<portlet:renderURL windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>"><portlet:param name='action' value='showRequestDetailsPopup'/></portlet:renderURL>&requestType="+
	 ServiceRqstType+"&serviceRequestNumber="+ServiceRqstNumber+"&timeZoneOffset="+timezoneOffset+"&uniqueTime="+new Date().getTime()+"&srArea="+ServiceRqstArea+"&srSubArea="+ServiceRqstSubArea;
	 url2= encodeURI(url2);
	 try {

	if(ServiceRqstType=='Fleet_Management' && (ServiceRqstArea=='Update Request' || ServiceRqstArea=='Cancel Request' || ServiceRqstArea=='MADC' || ServiceRqstArea=='Data Management') && (ServiceRqstSubArea=='Install Hardware Only' || ServiceRqstSubArea=='HW Request' || ServiceRqstSubArea=='Consumables SR' ||
		ServiceRqstSubArea=='Decommission Request' || ServiceRqstSubArea=='Hardware SR' || ServiceRqstSubArea=='Install-Steady State' || ServiceRqstSubArea=='Install Request' || ServiceRqstSubArea=='Move Request' || ServiceRqstSubArea=='Update Request' || ServiceRqstSubArea=='Cancel Request' ||
			ServiceRqstSubArea=='Install Asset' || ServiceRqstSubArea=='Register Asset' || ServiceRqstSubArea=='Move Asset' ||
					ServiceRqstSubArea=='Move Asset' || ServiceRqstSubArea=='Change Asset Data' || ServiceRqstSubArea=='Decommission Asset' || ServiceRqstSubArea=='Deregister Asset')) {
		
		showOverlay();	
					jQuery('#dialogChangeDetails').load(url2,function(){
		
					dialog=jQuery('#contentDetailsChange').dialog({
						autoOpen: false,
						title: '<spring:message code="Details.changeRequestDetails.heading.changeReqDeails"/>',
						modal: true,
						draggable: false,
						resizable: false,
						position: 'center',
						height: 'auto',
						width: 940,
						open: function(){	
							//jQuery(".ui-dialog-titlebar-close").hide();
							//jQuery(".ui-dialog-titlebar").hide();						
							changeRqstDetails(ServiceRqstNumber,ServiceRqstType);
						},
						close: function(event,ui){
							hideOverlay();
							jQuery('#contentDetailsChange').remove();
							dialog.dialog('destroy');					 
							dialog=null;
							
							},
						error: function (jqXHR, textStatus, errorThrown) {
							hideOverlay();
							dialog.dialog('destroy');					 
							dialog=null;
							jQuery('#contentDetailsChange').remove();
							}
						});
					dialog.dialog('open');
					});						
		
	}
	 
	else if(ServiceRqstType=='Fleet_Management' && (ServiceRqstArea=='HW Order' || ServiceRqstArea=='Hardware Shipment-Install') && (ServiceRqstSubArea=='BAU')) {
			
			showOverlay();	
						jQuery('#dialogHardwareDetails').load(url1,function(){
									
						dialog=jQuery('#contentDetailsChange').dialog({
							autoOpen: false,
							title: '<spring:message code="hardwareDetails.label.hardwareReqDetails"/>',
							modal: true,
							draggable: false,
							resizable: false,
							position: 'center',
							height: 'auto',
							width: 940,
							open: function(){	
													
								changeRqstDetails(ServiceRqstNumber,ServiceRqstType);
							},
							close: function(event,ui){
								hideOverlay();
								dialog.dialog('destroy');					 
								dialog=null;
								jQuery('#contentDetailsChange').remove();
								},
							error: function (jqXHR, textStatus, errorThrown) {
								hideOverlay();
								dialog.dialog('destroy');					 
								dialog=null;
								jQuery('#contentDetailsChange').remove();
								}
							});
						dialog.dialog('open');
						});						
			
		}
	else {
		onSRNmbrClick(ServiceRqstNumber,ServiceRqstType);
	}
	
}
catch (ex) {
	
	hideOverlay();
	dialog.dialog('destroy');					 
	dialog=null;
	
	return false;
}
return false;
	
}

function print() {
	expand();
	callOmnitureAction('<%=LexmarkSPOmnitureConstants.HARDWAREHIATORYDETAILS%>','<%=LexmarkSPOmnitureConstants.HARDWAREPRINT%>');
	// added for email start
	jQuery('#statusExpand').show();  
	jQuery('#associatedGridID').show();
	// added for email end
	
	url ="<portlet:renderURL windowState='<%= LiferayWindowState.POP_UP.toString() %>'>" + 
		"<portlet:param name='action' value='printHwDetails' />" + 
		"</portlet:renderURL>";

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
};

function email() {
	expand();
	callOmnitureAction('<%=LexmarkSPOmnitureConstants.HARDWAREHIATORYDETAILS%>','<%=LexmarkSPOmnitureConstants.HARDWAREEMAIL%>');
	// added for email start
	jQuery('#statusExpand').show();  
	jQuery('#associatedGridID').show();
	// added for email end

	url ="<portlet:renderURL windowState='<%= LiferayWindowState.POP_UP.toString() %>'>" + 
		"<portlet:param name='action' value='emailHwDetails' />" + 
		"</portlet:renderURL>";

    var iWidth=1100;
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
};
function expand() {
	if(!${fromSource!="parts"}){
	var pageSize = SRInProgressGrid.rowsBufferOutSize;
	var begin = (SRInProgressGrid.currentPage-1) * pageSize;
	var end =  SRInProgressGrid.currentPage * pageSize - 1;
	var index_sub_row=0;
	SRInProgressGrid.forEachRow(function(id){
		if(id >= begin && id <= end)
			SRInProgressGrid.cellById(id,index_sub_row).open();
	});
	}
};



function onSRNmbrClick(ServiceRqstNumber,ServiceRqstType){
	var urlDetails="<portlet:renderURL windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>"><portlet:param name='action' value='showInstallDetailsPopup'/></portlet:renderURL>&requestType="+
	 ServiceRqstType+"&serviceRequestNumber="+ServiceRqstNumber+"&timeZoneOffset="+timezoneOffset+"&uniqueTime="+new Date().getTime();
	 urlDetails= encodeURI(urlDetails);
	if(ServiceRqstType=='Fleet_Management'){
		showOverlay();
		
		jQuery('#dialogHardwareDetails').load(urlDetails,function(){
		dialog=jQuery('#contentDetailsInstall').dialog({
			autoOpen: false,
			title: '<spring:message code="requestInfo.heading.installReqDetails"/>',
			modal: true,
			draggable: false,
			resizable: false,
			position: 'center',
			height: 'auto',
			width: 940,
			open: function(){	
				jQuery(".ui-dialog-titlebar-close").hide();
				jQuery(".ui-dialog-titlebar").hide();
				installRqstDetails(ServiceRqstNumber,ServiceRqstType);
			},
			close: function(event,ui){
				hideOverlay();
				dialog.dialog('destroy');					 
				dialog=null;
				jQuery('#contentDetailsInstall').remove();
				},
			error: function (jqXHR, textStatus, errorThrown) {
				hideOverlay();
				dialog.dialog('destroy');					 
				dialog=null;
				jQuery('#contentDetailsInstall').remove();
				}
			});
		dialog.dialog('open');
		});
	}
}

</script>