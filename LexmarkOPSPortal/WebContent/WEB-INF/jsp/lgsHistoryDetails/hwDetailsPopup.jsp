<%@ taglib uri="/WEB-INF/tld/liferay-portlet.tld" prefix="portlet"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="util" uri="/WEB-INF/tld/lexmark-util.tld" %>
<%@ taglib prefix="html" tagdir="/WEB-INF/tags/html" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/mps.css"/>
<!--[if IE 7]>
	<style type="text/css"><%@ include file="/WEB-INF/css/mps_ie7.css" %></style>
<![endif]-->
<!--[if IE 8]>
	<style type="text/css"><%@ include file="/WEB-INF/css/mps_ie8.css" %></style>
<![endif]--> 
<!-- Added for CI 13-10-22 STARTS-->
<portlet:resourceURL var="downloadAttachment" id="downloadAttachment">
</portlet:resourceURL>
<script type="text/javascript">
	barImages = { 0: '<html:imagesPath/>progressBar/progressbg_red.gif',
		45:	'<html:imagesPath/>progressBar/progressbg_half.gif',
   		55: '<html:imagesPath/>progressBar/progressbg_orange.gif',
		70: '<html:imagesPath/>progressBar/progressbg_green.gif'};
	barImagesCancel = {0: '<html:imagesPath/>progressBar/progressbg_black.gif'};
	
	function changeRqstDetails(ServiceRqstNumber,ServiceRqstType) {
		
		jQuery('#HardwareRqstNumber').html(ServiceRqstNumber);
	}
	
	
</script>

<div id="contentDetailsChange" style="max-height: 500px; overflow-y: auto">
    <div class="journal-content-article">
      <h1><spring:message code="hardwareDetails.label.hardwareReqDetails"/></h1>
      <h2 class="step"><spring:message code="Details.supplyRequestDetails.label.rquestNumber"/><span id="HardwareRqstNumber">${requestFormPopup.srNumber}</span></h2> </div>
    <div class="main-wrap">
      <div class="content" id="hardwareDetails"> 
        <!-- MAIN CONTENT BEGIN -->
        <div class="portletBlock infoBox rounded shadow split">
         <c:if test="${exceptionList != null}">
       			<div class="error" id="dispalyExceptions_Popup">
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
                  <span id="area_Popup">${requestFormPopup.serviceRequest.area.value }</span></li>
                <li>
                  <label><spring:message code="requestInfo.label.subArea"/> </label>
                  <span id="subarea_Popup">${requestFormPopup.serviceRequest.subArea.value }</span></li>
                <c:if test="${userSegment == 'Employee'}">
	                <li>
	                <label> <spring:message code="Details.supplyRequestDetails.label.orderSource"/></label>
	                <span id="orderSource_Popup">${requestFormPopup.serviceRequest.orderSource }</span></li>
                  </c:if>
                

                <li>
                  <label><spring:message code="Details.changeRequestDetails.label.status"/> </label>
                  <span id="serviceRequestStatus_Popup">${requestFormPopup.serviceRequest.serviceRequestStatus }</span></li>
                  <li>
               		<label><spring:message code="hardwareDetails.label.accName"/>	</label>
               		<span id="serviceRequestStatus_Popup">${requestFormPopup.serviceRequest.accountName }</span>
               </li>
               <li>
                  <label><spring:message code="requestInfo.label.projectName"/> </label>
                  <span id="projectName">${requestFormPopup.serviceRequest.projectName }</span>
                  </li>
                  <li>
                  <label><spring:message code="requestInfo.label.projectPhase"/> </label>
                  <span id="projectPhase">${requestFormPopup.serviceRequest.projectPhase }</span>
                  </li>
              </ul>
            </div>
          </div>
          <div class="columnsTwo">
            <div class="columnInner">
              <ul class="form">
                <li>
                  <label><spring:message code="Details.changeRequestDetails.label.dateAndTime"/> </label>
                  
                  <span>${requestFormPopup.creationDate }</span></li>
                <li>
                  <label><spring:message code="Details.changeRequestDetails.label.requesterName"/> </label>
                  <span>${requestFormPopup.serviceRequest.requestor.firstName } &nbsp; ${requestFormPopup.serviceRequest.requestor.lastName }</span></li>
               
                   
             
               <c:choose>
               		<c:when test='${requestFormPopup.serviceRequest.billingModel eq "Ship and Bill"}'>
               		  <li>
                  		<label><spring:message code="Details.changeRequestDetails.label.itemSubTotalBeforeTax"/> </label>
                  		<span>${requestFormPopup.serviceRequest.itemSubTotalBeforeTax }</span></li>
                	  <li>
                  		<label><spring:message code="Details.changeRequestDetails.label.tax"/> </label>
                  		<span>${requestFormPopup.serviceRequest.tax }</span></li>
                	  <li>
                  		<label><spring:message code="Details.changeRequestDetails.label.total"/> </label>
                  		<span>${requestFormPopup.serviceRequest.totalAmount }</span>
                  	  </li>
               		</c:when>               		              		
               </c:choose>
              
                  
              </ul>
            </div>
          </div>
        </div>

        

        <hr class="separator" />
        <div class="portletBlock">
          <div class="columnsTwo">
            <div class="infoBox columnInner rounded shadow">
              <h4><spring:message code="requestInfo.heading.primaryContactForThisRequest"/></h4>
              <ul class="form wordBreak">
                <li>
                  <label><spring:message code="requestInfo.label.name"/></label>
                  <span id="primaryname_Popup">${requestFormPopup.serviceRequest.primaryContact.firstName }&nbsp; ${requestFormPopup.serviceRequest.primaryContact.lastName }</span></li>
                <li>
                  <label><spring:message code="requestInfo.label.phoneNumber"/></label>
                  <span id="primaryphone_Popup">${requestFormPopup.serviceRequest.primaryContact.workPhone }</span></li>
                <li>
                  <label><spring:message code="requestInfo.label.emailAddress"/></label>
                  <span id="primaryemail_Popup">${requestFormPopup.serviceRequest.primaryContact.emailAddress }</span></li>
              </ul>
            </div>
            <c:if test="${requestFormPopup.serviceRequest.secondaryContact.firstName != ''}">
            <div class="infoBox columnInner rounded shadow">
              <h4><spring:message code="requestInfo.heading.secondaryContactForThisRequest"/></h4>
              <ul class="form wordBreak">
                <li>
                  <label><spring:message code="requestInfo.label.name"/></label>
                  <span id="secondaryname_Popup">${requestFormPopup.serviceRequest.secondaryContact.firstName }&nbsp; ${requestFormPopup.serviceRequest.secondaryContact.lastName }</span></li>
                <li>
                  <label><spring:message code="requestInfo.label.phoneNumber"/></label>
                  <span id="secondaryphone_Popup">${requestFormPopup.serviceRequest.secondaryContact.workPhone }</span></li>
                <li>
                  <label><spring:message code="requestInfo.label.emailAddress"/></label>
                  <span id="secondaryemail_Popup">${requestFormPopup.serviceRequest.secondaryContact.emailAddress }</span></li>
              </ul>
            </div>
            </c:if>
          </div>
          <div class="columnsTwo">
            <div class="infoBox columnInner rounded shadow">
              <h4><spring:message code="requestInfo.heading.additionalInformationForThisRequest"/></h4>
              <ul class="form wordBreak">
                <li>
                  <label><spring:message code="requestInfo.label.customerRefereceId"/></label>
                  <span id="customerReferenceNumber_Popup">${requestFormPopup.serviceRequest.customerReferenceNumber }</span></li>
                <li>
                  <label><spring:message code="requestInfo.label.costCentre"/></label>
                  <span id="costCenter_Popup">${requestFormPopup.serviceRequest.costCenter }</span></li>
                <li>
                  <label><spring:message code="requestInfo.label.description"/></label>
                  <span id="addtnlDescription_Popup">${requestFormPopup.serviceRequest.addtnlDescription}</span></li>
              </ul>
            </div>
          </div>
        </div>   
        
        <hr class="separator" />
        <!-- PENDING SHIPMENT BLOCK - START -->
       
       
       
      	<c:if test="${requestFormPopup.pendingRequest.shipmentXML != null }">
      	<div class="portletBlock infoBox rounded shadow" id="customerReqItems">
  	      	<div class="columnsOne">
   	        	<div class="columnInner">
          		  <h4><spring:message code="Details.supplyRequestDetails.heading.pendingshipment"/></h4>
            			
            			<div class="icon"><img class="ui_icon_sprite widgets inProcess-icon" src="<html:imagesPath/>transparent.png" width="100" height="100" /></div>
            			 <div class="portlet-wrap-inner separatorV">
                         <div id="div_inprocessPrintTable_Popup">
							<div id="tab_inprocessGrid_Popup"  style="display:block;">
								<div id="div_inprocessGridContainer_Popup" style="width:100%;"></div>
								
	  						</div>
	  						
								
    							<div id="div_inprocessPagingArea_Popup" class="popup_grid"></div>
    						
	    				</div><!-- mygrid_container -->
	              	 	</div>
	              	 	
	              </div>
	         </div>
	     </div>
	     </c:if>
       <!-- PENDING SHIPMENT BLOCK - END -->
       
       <!-- SHIPMENT BLOCK - START -->
        <c:forEach var="shipmentForm" items="${requestFormPopup.serviceRequestShipments}" varStatus="status">
        <div class="portletBlock infoBox rounded shadow" id="suppliesShipment">
          <div class="columnsOne">
            <div class="columnInner">
              <h4><spring:message code="Details.supplyRequestDetails.label.shipment"/> <spring:message code="Details.supplyRequestDetails.label.tracking"/> &nbsp ${shipmentForm.trackingNumber}</h4>
              <div class="icon"><img class="ui_icon_sprite widgets widgets-truck-rt-icon" src="<html:imagesPath/>transparent.png" width="100" height="100" /></div>
              <div class="columnInner separatorV">
               
                <table class="shipment wFull">
                  <tr>
                    <td class="w70p"><div class="status">
<!--                         <div id="div_technicianProgressBar">PROGRESS BAR WILL COME HERE</div> -->
                        <div id="shipmentProgressBar_Popup${status.count}" class="status-bar"></div><!-- status-bar -->
                        <table border="0" cellspacing="0" cellpadding="0">
                          <tr>
                            <td><spring:message code="serviceRequest.label.inProcess"/></td>
                            <td><spring:message code="serviceRequest.label.shipped"/></td>
                            <td><spring:message code="serviceRequest.label.delivered"/></td>
                          </tr>
                        </table>
                      </div></td>
                    <td>
                    
                   <c:choose>
						<c:when test="${shipmentForm.hasProperUrl}">
                    	<ul class="form wFull">
                        <li>
                          <label><spring:message code="serviceRequest.label.carrier"/>:</label>
                          <span>${shipmentForm.carrier}</span></li>
                        <li>
                          <label><spring:message code="serviceRequest.label.eta"/></label>
                          <span>
		                  <util:dateFormat value="${shipmentForm.actualDeliveryDate}"></util:dateFormat>
		                  </span></li>
                      	</ul>
                      	</c:when>
                      	<c:otherwise>
							<ul class="form wFull">
		                        <li>
		                          <label><spring:message code="serviceRequest.label.carrier"/>:</label>
		                          <span>${shipmentForm.carrier}</span></li>
		                        <li>
		                          <label><spring:message code="serviceRequest.label.eta"/></label>
		                          <span>
		                  		  <util:dateFormat value="${shipmentForm.actualDeliveryDate}" showTime="true"  timezoneOffset="${timeZoneOffset}"></util:dateFormat>
		                  		  </span></li>
		                      	</ul>
						</c:otherwise>
					</c:choose>
                    </td>
                  </tr>
                </table>
                <div class="gridPosition" id="div_shipmentPrintTable_Popup${status.count}">
				<div id="tab_shipmentGrid_Popup${status.count}"  style="display:block;">
					<div id="shipmentGrid_container_Popup${status.count}" style="width:100%;"></div>
	 				<div id="shipmentPagingArea_Popup${status.count}" class="popup_grid"></div>
 				</div>
    			</div>
                

              </div>
            </div>
          </div>
        </div>
        
        <script type="text/javascript">
			if(${shipmentForm.shipmentProgress==0}){
				jQuery('#shipmentProgressBar_Popup${status.count}').progressBar(100, { width:480,speed:25,increment:1,height:12,showText: false, boxImage: '/LexmarkOPSPortal/images/progressBar/progressbar.gif', barImage:barImagesCancel} );
			}else{
				
				jQuery('#shipmentProgressBar_Popup${status.count}').progressBar(${shipmentForm.shipmentProgress}, { width:480,speed:25,increment:1,height:12,showText: false, boxImage: '/LexmarkOPSPortal/images/progressBar/progressbar.gif', barImage:barImages} );
			}
			SRShipmentGrid${status.count} = new dhtmlXGridObject('shipmentGrid_container_Popup${status.count}');
	 		SRShipmentGrid${status.count}.setImagePath("/LexmarkOPSPortal/images/gridImgs/");
	 		SRShipmentGrid${status.count}.setHeader("<spring:message code='serviceRequest.listHeader.shipment'/>");
	 		SRShipmentGrid${status.count}.setColAlign("right,left,left,left,left,left");
	 		SRShipmentGrid${status.count}.setColTypes("sub_row,ro,ro,ro,ro,ro");
	 		
	 		SRShipmentGrid${status.count}.setInitWidths("30,150,*,150,100,*");
	 		SRShipmentGrid${status.count}.enableAutoWidth(true);
	 		SRShipmentGrid${status.count}.enableMultiline(true);
	 		SRShipmentGrid${status.count}.enableAutoHeight(true);
	 		SRShipmentGrid${status.count}.init(); 
	 		SRShipmentGrid${status.count}.prftInit();
	 		SRShipmentGrid${status.count}.enablePaging(true, 5, 10, "shipmentPagingArea_Popup${status.count}", true);
	 		SRShipmentGrid${status.count}.setPagingSkin("bricks");
	 		SRShipmentGrid${status.count}.setSkin("light");
	 		
	 		var priceAvailableFlag${status.count}=false;
	 		<c:if test='${requestFormPopup.serviceRequest.billingModel eq "Ship and Bill"}'>
	 			 priceAvailableFlag${status.count}=true;
	 		</c:if>
	 		<c:if test='${requestFormPopup.serviceRequest.billingModel eq "Delayed Purchase"}'>
				SRShipmentGrid${status.count}.attachEvent("onRowCreated",function(rowId){
 				var cellObj${status.count} = SRShipmentGrid${status.count}.cellById(rowId,7);
 				var cellvalue${status.count}=cellObj${status.count}.getValue();
 				
 				if(cellvalue${status.count}!="")
 					priceAvailableFlag${status.count}=true;
			});
		</c:if>
		
	 		SRShipmentGrid${status.count}.loadXMLString('${shipmentForm.shipmentXML}');
	 		
	 		if(priceAvailableFlag${status.count}==false)
	 			SRInProgressGrid${status.count}.setColumnHidden(7,true);
	 			
			</script>
        
        
        </c:forEach>
       	<!-- SHIPMENT BLOCK - END -->  
       
       
        
        <!-- CANCEL BLOCK - START -->
        <c:if test="${requestFormPopup.cancelledPartsXML != null}">
        <div class="portletBlock infoBox rounded shadow">
          <div class="columnsOne">
            <div class="columnInner">
              <h4><spring:message code="Details.supplyRequestDetails.heading.cancelled"/></h4>
<!--               <div class="collapse"> -->
              <div class="icon"><img src="/LexmarkOPSPortal/images/cancelled.jpg" width="100" height="100" /></div>
              <div class="columnInner separatorV">
               
                <table class="shipment wFull">
                  <tr>
                    <td class="w70p"><div class="status">
<!--                         <div id="div_technicianProgressBar">PROGRESS BAR WILL COME HERE</div> -->
                        <div id="div_cancelProgressBar_Popup" class="status-bar"></div><!-- status-bar -->
                        <table border="0" cellspacing="0" cellpadding="0">
                          <tr>
                            <td></td>
                            <td><spring:message code="serviceRequest.label.cancelled" /></td>
                            <td></td>

                          </tr>
                        </table>
                      </div></td>
                    <td>&nbsp;</td>
                  </tr>
                </table>
                <div id="div_cancelPrintTable" class="gridPosition">
							<div id="tab_cancelGrid_Popup" style="display:block;">
								<div id="div_cancelContainer_Popup" style="background-color: white;width:100%;"></div>
    							<div id="div_cancelPagingArea_Popup" class="popup_grid"></div>
    						</div>
    				</div>
              </div>
<!--               </div> -->
            </div>
          </div>
        </div>
        </c:if>
        <!-- CANCEL BLOCK - END -->
        
        <p class="inlineTitle"><spring:message code="requestInfo.heading.shippingInformation"/></p>
        <div class="portletBlock">
          <div class="columnsTwo">
            <div class="infoBox columnInner rounded shadow">
              <h4><spring:message code="requestInfo.heading.shipToAddress"/></h4>
              <ul class="roDisplay">
                <li><div>${requestFormPopup.serviceRequest.serviceAddress.storeFrontName}</div>
                <div>${requestFormPopup.serviceRequest.serviceAddress.addressLine1}</div>
                  <div>${requestFormPopup.serviceRequest.serviceAddress.addressLine2}</div>
                  <div>${requestFormPopup.serviceRequest.serviceAddress.addressLine3}</div>
                  <span>${requestFormPopup.serviceRequest.serviceAddress.city} 
                  <c:if test="${requestFormPopup.serviceRequest.serviceAddress.state != '' || requestFormPopup.serviceRequest.serviceAddress.province != ''}">
                  , ${requestFormPopup.serviceRequest.serviceAddress.state} ${requestFormPopup.serviceRequest.serviceAddress.province}
                  </c:if>
                  </span>
                  <div>${requestFormPopup.serviceRequest.serviceAddress.country}</div>
                  <div>${requestFormPopup.serviceRequest.serviceAddress.postalCode}</div></li>
                <li><span><spring:message code="requestInfo.addressInfo.label.building"/>  ${requestFormPopup.serviceRequest.serviceAddress.physicalLocation1} </span></li>
                <li><span><spring:message code="requestInfo.addressInfo.label.floor"/>  ${requestFormPopup.serviceRequest.serviceAddress.physicalLocation2} </span></li>
                <li><span><spring:message code="requestInfo.addressInfo.label.office"/>  ${requestFormPopup.serviceRequest.serviceAddress.physicalLocation3} </span></li>
              </ul>
            </div>
          </div>
          <div class="columnsTwo">
            <div class="infoBox columnInner rounded shadow">
              <h4><spring:message code="requestInfo.heading.orderInstructions"/></h4>
              <ul class="form wordBreak">
                <li>
                  <label for="desc"><spring:message code="requestInfo.label.specialInstructions"/></label>
                  <span id="specialInstructions_Popup"> ${requestFormPopup.serviceRequest.specialInstructions} </span></li>
               
              </ul>
            </div>
          </div>
        </div>       
         <div class="portletBlock">
          <div class="columnsOne">
            <div class="infoBox columnInner rounded shadow">
              <h4><spring:message code="orderSupplies.label.paymentDetails"/></h4>
              <ul class="form">
                <c:if test="${fn:trim(requestFormPopup.serviceRequest.poNumber) != '' }">
                <li>
                  <label for="poNo"><spring:message code="Details.supplyRequestDetails.label.poNumber"/></label>
                  <span> ${requestFormPopup.serviceRequest.poNumber } </span></li>               
               </c:if>
               <c:if test="${fn:trim(requestFormPopup.serviceRequest.creditCardToken) != '' }">
			   <li>
			      <label for="cct"><spring:message code="requestInfo.label.creditCardToken"/></label>
			      <span>${requestFormPopup.serviceRequest.creditCardToken } </span></li>
		       </c:if>
                
              </ul>
            </div>
          </div>
        </div>
       
        
        <p class="inlineTitle"><spring:message code="requestInfo.heading.notes"/> &amp; <spring:message code="requestInfo.heading.attachments"/></p>
        <div class="portletBlock">
          <div class="columnsOne">
            <div class="infoBox columnInner rounded shadow">
              <h4><spring:message code="orderSupplies.notes.notes"/></h4>
              <ul class="roDisplay wordBreak">
              <li><span id="notes_Popup">${requestFormPopup.serviceRequest.notes}</span></li>
            </ul>
            </div>
          </div>
        </div>
        
        <div class="portletBlock">
          <div class="columnInner">
          <div class="wHalf displayGrid columnInnerTable rounded shadow">
            <table>
              <thead>
                <tr>
                  <th><spring:message code="requestInfo.heading.attachments"/></th>
                </tr>
              </thead>
              		<% int c = 1; %>
              		<c:forEach items="${requestFormPopup.attachList}" var="entry" >
              			<% if(c%2==0){ %>
	              			<tr class="altRow" >
	              				<c:if test="${entry.extension != 'URL'}">
					           		<td>${entry.displayAttachmentName}.${entry.extension}</td>
					           	</c:if>
					           	<c:if test="${entry.extension == 'URL'}">
					           		<td>${entry.attachmentName}</td>
					           	</c:if>
					        </tr>
              			<% }else{ %>
	              			<tr>
					           	<c:if test="${entry.extension != 'URL'}">
					           		<td>${entry.displayAttachmentName}.${entry.extension}</td>
					           	</c:if>
					           	<c:if test="${entry.extension == 'URL'}">
					           		<td>${entry.attachmentName}</td>
					           	</c:if>
					        </tr>
              			<% }
              			   c++;
              			%>
						
					</c:forEach>
              
            </table>
            </div>
          </div>
        </div>    

      </div>
      <!-- MAIN CONTENT END --> 

	</div>
</div>

<script type="text/javascript">
	
dhx_globalImgPath = "/LexmarkOPSPortal/images/comboImgs/";

image_error= function () {

	document.getElementById("MyPicture_Popup").src = '/LexmarkOPSPortal/images/dummy_noimg.jpg'; 

};

/** 
  *  InProcess Grid config begin
  */
  if(${requestFormPopup.pendingRequest.shipmentXML!= null}){
		SRInProgressGrid = new dhtmlXGridObject('div_inprocessGridContainer_Popup');
			<%-- CHanges for MPS 2.1--%>
			var priceAvailableFlag=false;
			<c:if test='${requestFormPopup.serviceRequest.billingModel eq "Ship and Bill"}'>
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
		SRInProgressGrid.setInitWidths("30,180,*,180,100,100,95");
		SRInProgressGrid.enableAutoWidth(true);
		SRInProgressGrid.enableMultiline(true);
		SRInProgressGrid.enableAutoHeight(true);
		SRInProgressGrid.init(); 
		SRInProgressGrid.prftInit();
		SRInProgressGrid.setColumnHidden(5,true);
		if(${fromSource!="parts"}){
			SRInProgressGrid.setColumnHidden(0,true);			
			}
		SRInProgressGrid.enablePaging(true, 5, 10, "div_inprocessPagingArea_Popup", true);
		SRInProgressGrid.setPagingSkin("bricks");
		SRInProgressGrid.setSkin("light");
		<%-- CHanges for MPS 2.1--%>
		
		<c:if test='${requestFormPopup.serviceRequest.billingModel eq "Delayed Purchase"}'>
			SRInProgressGrid.attachEvent("onRowCreated",function(rowId){
				
				var cellObj = SRInProgressGrid.cellById(rowId,6);
				var cellvalue=cellObj.getValue();
				<%--alert('row getting created'+cellvalue.length);--%>
				if(cellvalue!=""&&cellvalue!=0)
					priceAvailableFlag=true;
			});
		</c:if>
		
		
		<%-- CHanges for MPS 2.1 ENDS--%>
		<%--SRInProgressGrid.loadXMLString('${requestFormPopup.pendingRequest.shipmentXML}');--%>
		SRInProgressGrid.loadXMLString('${requestFormPopup.pendingRequest.shipmentXML}');
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

/** 
  *  Cancelled Grid config begin
  */
if(${requestFormPopup.cancelledPartsXML!= null}){
SRCancelledGrid = new dhtmlXGridObject('div_cancelContainer_Popup');
SRCancelledGrid.setImagePath("/LexmarkOPSPortal/images/gridImgs/");
SRCancelledGrid.setHeader("<spring:message code='serviceRequest.listHeader.cancelled'/>");
SRCancelledGrid.setColAlign("left,left,left,left");
SRCancelledGrid.setColTypes("ro,ro,ro,ro");

SRCancelledGrid.setInitWidths("180,*,100,100");
SRCancelledGrid.enableAutoWidth(true);
SRCancelledGrid.enableMultiline(true);
SRCancelledGrid.enableAutoHeight(true);
SRCancelledGrid.init(); 
SRCancelledGrid.prftInit();
SRCancelledGrid.enablePaging(true, 5, 10, "div_cancelPagingArea_Popup", true);
SRCancelledGrid.setPagingSkin("bricks");
SRCancelledGrid.setSkin("light");
SRCancelledGrid.loadXMLString('${requestFormPopup.cancelledPartsXML}'); 
	
 }


/**
****************progressBars configration start*******************
*/
if(${requestFormPopup.serviceRequest.serviceActivityStatus!= null}){
if(${requestFormPopup.serviceRequestTechnicianProgress==0}){
	jQuery("#div_technicianProgressBar1_Popup").progressBar(100 , { width:480,speed:25,increment:1,height:12,showText: false, boxImage: '/LexmarkOPSPortal/images/progressBar/progressbar.gif', barImage : barImagesCancel} );
}else{
		jQuery("#div_technicianProgressBar1_Popup").progressBar(${requestFormPopup.serviceRequestTechnicianProgress}, { width:480,speed:25,increment:1,height:12,showText: false, boxImage: '/LexmarkOPSPortal/images/progressBar/progressbar.gif', barImage : barImages} );
}
}

if(${requestFormPopup.cancelledPartsXML!= null}){

	jQuery("#div_cancelProgressBar").progressBar(100 , { width:480,speed:25,increment:1,height:12,showText: false, boxImage: '<html:imagesPath/>progressBar/progressbar.gif', barImage : barImagesCancel} );

}
	

</script>

