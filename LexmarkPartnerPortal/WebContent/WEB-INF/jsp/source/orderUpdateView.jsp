
<script>
	var gridProcessedPartsGrid;
	var pendingShipmentRequestGrid;
</script>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<%@ include file="/WEB-INF/jsp/includeGrid.jsp"%>
 <%@ include file="/WEB-INF/jsp/dateUtil.jsp"%>  
<%@ include file="/WEB-INF/jsp/includeDatePicker.jsp"%>
<%@page import="com.lexmark.constants.LexmarkPPOmnitureConstants" %>
<script src='<html:rootPath/>js/trackingNumber.js'></script>
<script src='<html:rootPath/>js/expand.js'></script>
<style>
.columnInner{padding:0px !important}
.dhx_sub_row{overflow:unset !important}
.rowtext{padding:6px 8px 5px 0 !important;}
#pendingShipmentTable th {
    background-color: #e6e6f0;
    font-size:12px;
}
#pendingShipmentTable input{
height:14px !important;font-size: 11px;
}
#pendingShipmentTable tr{
font-size:11px;
}
.displayGrid tr td {border-left: 0 !important;}
.ev_light td{background-color: #f1f1f1 !important;}
</style>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/mps.css?version=<html:fileCacheVersion/>" />
<!--[if IE 7]>
	<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/mps_ie7.css?version=<html:fileCacheVersion/>" />
<![endif]-->
<portlet:actionURL  var="cancelUpdate">
    <portlet:param name="action" value="cancelOrderUpdate" />
</portlet:actionURL>
<portlet:actionURL var="finalUpdate">
	<portlet:param name="action" value="updateOrderUpdateView"></portlet:param>
	<portlet:param name="orderNumber" value="${orderDetailForm.orderDetail.orderNumber}" />
	<portlet:param name="vendorAccountId" value="${orderDetailForm.orderDetail.vendorId}" />
	<portlet:param name="acceptOrder" value="${acceptOrder}" />
	<portlet:param name="orderID" value="${orderDetailForm.orderDetail.id}" />
	<portlet:param name="timezoneOffset" value="${timezoneOffset}" /> 
</portlet:actionURL>
<portlet:actionURL var="submitOrderUpdate">
	<portlet:param name="action" value="submitOrderUpdate"/>
</portlet:actionURL>
<portlet:renderURL var="orderDetailPageURL">
	<portlet:param name="action" value="retrieveOrderRequest"/>
	<portlet:param name="requestNumber" value="${orderDetailForm.orderDetail.serviceRequestNumber}" />
	<portlet:param name="orderNumber" value="${orderDetailForm.orderDetail.orderNumber}" />
	<portlet:param name="vendorAccountId" value="${orderDetailForm.orderDetail.vendorId}" />
	<portlet:param name="timezoneOffset" value="${timezoneOffset}" />
</portlet:renderURL>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/in-styles.css?version=<html:fileCacheVersion/>" />
 
  
  <div id="content-wrapper">
    <div class="journal-content-article">
      <h1><spring:message code="requestInfo.heading.serviceOrder.serviceOrders"/></h1>
    </div>
       
    <form:form id="orderUpdateForm" commandName="orderDetailForm" method="post" >
    
    
    <form:hidden path="quantityList" id="quantityList"/>
    <form:hidden path="lineIdList" id="lineIdList"/>
    <form:hidden path="partNumberList" id="partNumberList"/>
    <form:hidden path="shipQuantityList" id="shipQuantityList"/>
    <form:hidden path="carrierList" id="carrierList"/>
    <form:hidden path="trackingList" id="trackingList"/>
    <form:hidden path="actualShippedDateList" id="actualShippedDateList"/>
    <form:hidden path="backOrderedQtyList" id="backOrderedQtyList"/>
    <form:hidden path="actualBackOrderedQtyList" id="actualBackOrderedQtyList"/>
    <form:hidden path="ETAList" id="ETAList"/>
    <form:hidden path="serialNumberList" id="serialNumberList"/>
    
    
    <form:hidden path="statusList" id="statusList"/>
    <form:hidden path="deliveryDateList" id="deliveryDateList"/>
    <form:hidden path="processedTrackingList" id="processedTrackingList"/>
    <form:hidden path="processedPartList" id="processedPartList"/>
    <form:hidden path="processedLineIdList" id="processedLineIdList"/>
    
     
     <form:hidden path="orderDetail.vendorId" id="vendorAccountId"/>
    
    <!-- This block is for setting values after back end validation  -->
    <c:if test="${orderDetailForm.quantityList != null}">
        <input type="hidden" id="quantityList_updated" value="${orderDetailForm.quantityList}">
    </c:if> 
    <c:if test="${orderDetailForm.lineIdList != null}">
        <input type="hidden" id="lineIdList_updated" value="${orderDetailForm.lineIdList}">
    </c:if>
    <c:if test="${orderDetailForm.partNumberList != null}">
        <input type="hidden" id="partNumberList_updated" value="${orderDetailForm.partNumberList}">
    </c:if> 
    <c:if test="${orderDetailForm.shipQuantityList != null}">
        <input type="hidden" id="shipQuantityList_updated" value="${orderDetailForm.shipQuantityList}">
    </c:if> 
    <c:if test="${orderDetailForm.carrierList != null}">
        <input type="hidden" id="carrierList_updated" value="${orderDetailForm.carrierList}">
    </c:if> 
     <c:if test="${orderDetailForm.trackingList != null}">
        <input type="hidden" id="trackingList_updated" value="${orderDetailForm.trackingList}">
    </c:if> 
    <c:if test="${orderDetailForm.actualShippedDateList != null}">
        <input type="hidden" id="actualShippedDateList_updated" value="${orderDetailForm.actualShippedDateList}">
    </c:if> 
    <c:if test="${orderDetailForm.backOrderedQtyList != null}">
        <input type="hidden" id="backOrderedQtyList_updated" value="${orderDetailForm.backOrderedQtyList}">
    </c:if> 
    <c:if test="${orderDetailForm.actualBackOrderedQtyList != null}">
        <input type="hidden" id="actualBackOrderedQtyList_updated" value="${orderDetailForm.actualBackOrderedQtyList}">
    </c:if>
    <c:if test="${orderDetailForm.ETAList != null}">
        <input type="hidden" id="ETAList_updated" value="${orderDetailForm.ETAList}">
    </c:if>
    <c:if test="${orderDetailForm.serialNumberList != null}">
        <input type="hidden" id="serialNumberList_updated" value="${orderDetailForm.serialNumberList}">
    </c:if> 
     
    
    <c:if test="${orderDetailForm.shipQuantityList != null}">
    	<input type="hidden" id="shipQuantityList_updated" value="${orderDetailForm.shipQuantityList}">
    </c:if> 
    
    <c:if test="${orderDetailForm.statusList != null}">
    	<input type="hidden" id="statusList_updated" value="${orderDetailForm.statusList}">
    </c:if> 
    
    <c:if test="${orderDetailForm.processedTrackingList != null}">
    	<input type="hidden" id="processedTrackingList_updated" value="${orderDetailForm.processedTrackingList}">
    </c:if> 
    
    <c:if test="${orderDetailForm.processedPartList != null}">
    	<input type="hidden" id="processedPartList_updated" value="${orderDetailForm.processedPartList}">
    </c:if> 
    
    <c:if test="${orderDetailForm.processedLineIdList != null}">
    	<input type="hidden" id="processedLineIdList_updated" value="${orderDetailForm.processedLineIdList}">
    </c:if> 
    
     <c:if test="${orderDetailForm.deliveryDateList != null}">
    	<input type="hidden" id="deliveryDateList_updated" value="${orderDetailForm.deliveryDateList}">
    </c:if> 
    
    <!-- end  -->
    
    <div class="main-wrap">
      <div class="content"> 
      
      <!-- This block is to show server side errors  -->
          <spring:hasBindErrors name="orderDetailForm">
          	<div class="error">
				<c:forEach var="error" items="${errors.allErrors}">
			   		<li><strong><spring:message code="requestInfo.validation.source.forPartNumber" arguments="${error.code}"/>&nbsp;<spring:message code="${error.defaultMessage}" /></strong></li>
            	</c:forEach>
            	<c:forEach var="error" items="${requestScope.errorList}">
			   		<li><strong><spring:message code="requestInfo.validation.source.forPartNumber" arguments="${error.partNumber}"/>&nbsp;<spring:message code="${error.errorMessage}" />&nbsp;<spring:message code="requestInfo.validation.source.lineNumber" arguments="${error.lineNumber}"/></strong></li>
            	</c:forEach>
     		</div>	
	      </spring:hasBindErrors>   
	       <!-- end  -->  
      	<div class="grid-controls"> 
           <div class="expand-min">
             <ul>
               <li class="first"><img class="ui-icon bck-arrow-icon" src="<html:imagesPath/>transparent.png" alt="<spring:message code="requestInfo.link.backToServiceOrders"/>" width="24" height="24" title="<spring:message code="requestInfo.link.backToServiceOrders"/>" /><a href="#" onClick="backToOrderList()"><spring:message code="requestInfo.link.backToServiceOrders"/></a> </li>
             </ul>
           </div>
         </div>
        <!-- MAIN CONTENT GOES HERE -->
        <h3 class="pageTitle"><spring:message code="requestInfo.heading.serviceOrder.orderRequest"/> &ndash; <spring:message code="requestInfo.heading.serviceOrder.update"/></h3>
        <!-- ORDER INFORMATION BLOCK - Start -->
        <div class="portletBlock infoBox rounded shadow">
         <h4><spring:message code="requestInfo.heading.serviceOrder.orderInformation"/></h4>
          <div class="columnsTwo w70p">
            <div class="columnInner">
             
              <table width="100%" border="0" cellspacing="0" cellpadding="0" class="dataGrid dataGrid_top">
                <tr>
                  <td width="220" class="label"><spring:message code="requestInfo.label.serviceOrder.requestNumber"/></td>
                  <td>${orderDetailForm.orderDetail.serviceRequestNumber}</td>
                </tr>
                <tr>
                  <td width="150" class="label"><spring:message code="requestInfo.label.serviceOrder.orderNumber"/></td>                  
				<td>${orderDetailForm.orderDetail.orderNumber}</td>
                </tr>
                <!-- Added for Jan release -->
                 <tr>
                  <td class="label"><spring:message code="requestInfo.label.serviceOrder.serviceType"/></td>
                  <td>${orderDetailForm.orderDetail.orderType}</td>
                </tr>
                <!--End Add-->
                <tr>
                  <td class="label"><spring:message code="requestInfo.label.serviceOrder.serviceProviderOrderRefNo"/></td>
                  <td><form:input path="serviceProviderOrderRefNo" id="serviceProviderOrderRefNo"/></td>
                </tr>
                <tr>
                  <td class="label"><spring:message code="requestInfo.label.serviceOrder.openedDateAndTime"/></td>
                  <td><%-- <fmt:formatDate value="${orderDetailForm.orderDetail.orderDate}" pattern="MM-dd-yyyy hh:mm:ss"/> --%>
                  <util:dateFormat showTime="true" timezoneOffset="${timezoneOffset}" value="${orderDetailForm.orderDetail.orderDate}"></util:dateFormat>
                  </td>
                </tr>
                <tr>
                  <td class="label"><spring:message code="requestInfo.label.serviceOrder.requestStatus"/></td>
                  <td>${orderDetailForm.orderDetail.status}</td>
                </tr>
                <!-- Added for Jan release -->
                <tr>
                  <td class="label">*<spring:message code="requestInfo.label.serviceOrder.serialNumber"/>                  
                   </td>
                  <td>${orderDetailForm.orderDetail.asset.serialNumber}       
                  </td>
                </tr>
                <tr>
                  <td colspan="2" class="table-td-style9"><span class="infolabel">*(For Catalog Orders this field will be blank)</span>
                  </td>
                </tr>
                <!-- End Add -->
              </table>
            </div>
          </div>
          <div class="columnsTwo w30p">
            <div class="columnInner">
                         
            </div>
          </div>
        </div>
        <!-- ORDER INFORMATION BLOCK - End -->
        <p class="info inlineTitle"><spring:message code="requestInfo.heading.serviceOrder.fulfillmentInformation"/></p>
        <div class="error" id="errorDiv" style="display: none;">
		</div>
        <c:if test="${showPendingShipmentPartsFlag == 'true'}">
        <!-- PENDING SHIPMENT BLOCK - Start -->
        
		
        <div class="columnInner separatorV">
    		<div id="pendingShipmentDiv" class="width-100per">
        		<h4><spring:message code="requestInfo.heading.serviceOrder.pendingShipment"/></h4>
				<!-- Table Start-->
            		<div id="div_pendingShipment_container"></div>                  
        		<!-- Table End -->  
    		</div>
		</div>
        
        <script>	
        // GRID for PENDING SHIPMENT
			jQuery('#div_pendingShipment_container').append("${orderDetailForm.pendingShipmentPartListXML}");
			
		</script>		
        <!-- PENDING SHIPMENT BLOCK - End --> 
        </c:if>
        
        <c:if test="${showProcessedPartsFlag == 'true'}">
        <!-- PROCESSED PARTS BLOCK - Start -->
        <div class="portletBlock infoBox rounded shadow">
          <div class="columnsOne">
            <div class="columnInner">
              <h4><spring:message code="requestInfo.heading.serviceOrder.processedParts"/></h4>
              	<div class="grid-controls"> 
	              	<div class="expand-min">
		              <ul>
		                <li class="first"> <img src="<html:imagesPath/>transparent.png" class="ui-icon minimize-icon" alt="<spring:message code="requestInfo.label.minimizeAll"/>" title="<spring:message code="requestInfo.label.minimizeAll"/>" /> <a href="javascript:void(0)" onClick="minimizeAll()";><spring:message code="requestInfo.label.minimizeAll"/></a> </li>
		                <li> <img class="ui-icon expand-icon" src="<html:imagesPath/>transparent.png" alt="<spring:message code="requestInfo.label.expandAll"/>" title="<spring:message code="requestInfo.label.expandAll"/>" /> <a href="javascript:void(0)" onClick="expandAll()";><spring:message code="requestInfo.label.expandAll"/></a> </li>
		              </ul>
		            </div>
	            <!-- Expand-min End --> 
	          	</div> 
              <!-- Grid Start -->
              <div class="portlet-wrap rounded marginTopNull">
                <div class="portlet-wrap-inner">
                  <div id="div_processedParts_container" class="gridbox gridbox_light "></div>
                </div>
              </div>
              <div class="pagination"><span id="processedPartPagingArea"></span><span id="processedPartInfoArea"></span></div>
              <!-- Grid End --> 
            </div>
          </div>
        </div>
        <script>
	     // GRID for PROCESSED PARTS	
	    	
	
	    	gridProcessedPartsGrid = new dhtmlXGridObject('div_processedParts_container');
	    	gridProcessedPartsGrid.setImagePath("<html:imagesPath/>gridImgs/");
	    	gridProcessedPartsGrid.setHeader("<spring:message code="requestInfo.serviceOrder.listHeader.processedParts.update"/>");
	    	gridProcessedPartsGrid.setInitWidths("30,*,*,*,*,*");
	    	gridProcessedPartsGrid.enableResizing("false,false,false,false,false,false");
	    	gridProcessedPartsGrid.setColAlign("left,left,left,left,left,left");
	    	gridProcessedPartsGrid.setSkin("light");
	    	gridProcessedPartsGrid.setColSorting("na,str,str,str,str,str");
	    	gridProcessedPartsGrid.setColTypes("sub_row,ro,ro,ro,ro,ro");
	    	gridProcessedPartsGrid.enableAutoHeight(true);
	    	gridProcessedPartsGrid.enableMultiline(true);
	    	gridProcessedPartsGrid.enableColumnMove(true);
	    	gridProcessedPartsGrid.enableAlterCss("even_row","odd_row");
	    	gridProcessedPartsGrid.init();
	     	//gridProcessedPartsGrid.enablePaging(true, 4, 2, "processedPartPagingArea", true, "processedPartInfoArea");
	    	gridProcessedPartsGrid.setPagingSkin("bricks");
	    	gridProcessedPartsGrid.loadXMLString("${orderDetailForm.processedPartListXML}");
	    	
	    	function minimizeAll() {
	    		gridProcessedPartsGrid.forEachRow(function(id) {
	    			var cell = gridProcessedPartsGrid.cellById(id, 0);
	    			cell.close();
	    			subRowOpenFlag = false;
	    		});
	    	};
	    	function expandAll() {
	    		gridProcessedPartsGrid.forEachRow(function(id) {
	    			var cell = gridProcessedPartsGrid.cellById(id, 0);
	    			cell.open();
	    			subRowOpenFlag = true;
	    		});
	    	};
        </script>
        <!-- PROCESSED PARTS BLOCK - End --> 
        </c:if>        
        
        
        <!-- DELIVERY INFORMATION BLOCK - Start -->
        <div class="portletBlock infoBox rounded shadow">
          <div class="columnsOne">
            <div class="columnInner">
              <h4><spring:message code="requestInfo.heading.serviceOrder.deliveryInformation"/></h4>
				<table width="100%" border="0" cellpadding="0" cellspacing="0">
				  <tr>
				    <td width="35%" valign="top">
				    <table class="dataGrid" border="0" cellspacing="3" cellpadding="0">
				  <tr class="inlineTitle">
				  
				    <td colspan="2"><spring:message code="requestInfo.heading.serviceOrder.shipToAddress"/></td>
				    </tr>
				  <tr>
				    <td class="label"><spring:message code="requestInfo.label.serviceOrder.address"/></td>
				    <td>
<%-- 				    	<util:addressOutput value="${orderDetailForm.orderDetail.shippingAddress}"/>	 --%>

						<c:if test="${!empty orderDetailForm.orderDetail.shippingAddress.storeFrontName}">
							${orderDetailForm.orderDetail.shippingAddress.storeFrontName}<br>
						</c:if>
				    	<c:if test="${!empty orderDetailForm.orderDetail.shippingAddress.addressLine1}">
							${orderDetailForm.orderDetail.shippingAddress.addressLine1}<br>
						</c:if>
						<c:if test="${!empty orderDetailForm.orderDetail.shippingAddress.addressLine2}">
							${orderDetailForm.orderDetail.shippingAddress.addressLine2}<br>
						</c:if>
						<c:if test="${!empty orderDetailForm.orderDetail.shippingAddress.city}">
							${orderDetailForm.orderDetail.shippingAddress.city}
							<c:if test="${!empty orderDetailForm.orderDetail.shippingAddress.state}">,</c:if>
						</c:if>	
						<c:if test="${!empty orderDetailForm.orderDetail.shippingAddress.state}">
							${orderDetailForm.orderDetail.shippingAddress.state}
							<c:if test="${!empty orderDetailForm.orderDetail.shippingAddress.province}">,</c:if>
						</c:if>
						<c:if test="${!empty orderDetailForm.orderDetail.shippingAddress.province}">	
							${orderDetailForm.orderDetail.shippingAddress.province}
							<c:if test="${!empty orderDetailForm.orderDetail.shippingAddress.country}">,</c:if>
						</c:if>
						<c:if test="${!empty orderDetailForm.orderDetail.shippingAddress.country}">	
							${orderDetailForm.orderDetail.shippingAddress.country}
							<c:if test="${!empty orderDetailForm.orderDetail.shippingAddress.postalCode}">,</c:if>
						</c:if>
						<c:if test="${!empty orderDetailForm.orderDetail.shippingAddress.postalCode}">	
							${orderDetailForm.orderDetail.shippingAddress.postalCode}&nbsp;
						</c:if>	
					</td>
				  </tr>
				</table>
				    </td>
				        <td width="35%" valign="top" class="separatorV">
				    <table class="dataGrid" border="0" cellspacing="3" cellpadding="0">
				  <tr class="inlineTitle">
				    <td class="leftPadding" colspan="2"><spring:message code="requestInfo.heading.serviceOrder.primaryContact"/></td>
				    </tr>
				  <tr>
				    <td width="100" class="label leftPadding"><spring:message code="requestInfo.label.serviceOrder.name"/></td>
				    <td class="wrapWord">${orderDetailForm.orderDetail.primaryContact.firstName} ${orderDetailForm.orderDetail.primaryContact.lastName}</td>
				  </tr>
				  <tr>
				    <td class="label leftPadding"><spring:message code="requestInfo.label.serviceOrder.phone"/></td>
				    <td class="wrapWord">${orderDetailForm.orderDetail.primaryContact.workPhone}</td>
				  </tr>
				  <tr>
				    <td class="label leftPadding"><spring:message code="requestInfo.label.serviceOrder.email"/></td>
				    <td class="wrapWord">${orderDetailForm.orderDetail.primaryContact.emailAddress}</td>
				  </tr>
				    </table>
				    </td>
				        <td width="30%" valign="top" class="separatorV">
				    <table class="dataGrid" border="0" cellspacing="3" cellpadding="0">
				  <tr class="inlineTitle">
				    <td class="leftPadding" colspan="2"><spring:message code="requestInfo.heading.serviceOrder.secondaryContact"/></td>
				    </tr>
				  <tr>
				    <td width="100" class="label leftPadding"><spring:message code="requestInfo.label.serviceOrder.name"/></td>
				    <td class="wrapWord">${orderDetailForm.orderDetail.secondaryContact.firstName} ${orderDetailForm.orderDetail.secondaryContact.lastName}</td>
				  </tr>
				  <tr>
				    <td class="label leftPadding"><spring:message code="requestInfo.label.serviceOrder.phone"/></td>
				    <td class="wrapWord">${orderDetailForm.orderDetail.secondaryContact.workPhone}</td>
				  </tr>
				  <tr>
				    <td class="label leftPadding"><spring:message code="requestInfo.label.serviceOrder.email"/></td>
				    <td class="wrapWord">${orderDetailForm.orderDetail.secondaryContact.emailAddress}</td>
				  </tr>
				    </table>
				    </td>
				  </tr>
				  <tr>
				    <td colspan="2">
				    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="dataGrid division">
		               <%--  <tr>
		                  <td width="220" class="label"><spring:message code="requestInfo.label.serviceOrder.specialInstructions"/></td>
		                  <td>${orderDetailForm.orderDetail.specialInstructions}</td>
		                </tr> --%>
		                <tr>
		                  <td width="150" class="label"><spring:message code="requestInfo.label.serviceOrder.requestedDeliveryDate"/></td>
		                  <td><util:dateFormat showTime="true" timezoneOffset="${timezoneOffset}" value="${orderDetailForm.orderDetail.requestedDeliveryDate}"></util:dateFormat></td>
		                </tr>
		                <tr>
		                  <td class="label"><spring:message code="requestInfo.label.serviceOrder.mustResolveBy"/></td>
		                  <td>${orderDetailForm.orderDetail.mustResolveBy}</td>
		                </tr>
		                <tr>
		                  <td class="label" for="spclHandling"><spring:message code="requestInfo.label.serviceOrder.specialHandling"/></td>
		                  <td  id="spclHandling">${orderDetailForm.orderDetail.specialHandlingInstructions}</td>
		                </tr>
		                <tr>
		                  <td class="label" for="insideLocation"><spring:message code="requestInfo.label.serviceOrder.insideLocation"/></td>
		                  <td id="spclHandling">${orderDetailForm.orderDetail.insideLocation}</td>
		                </tr>
		                <tr>
		                  <td class="label" for="attentionTo"><spring:message code="requestInfo.label.serviceOrder.attentionTo"/></td>
		                  <td id="spclHandling">${orderDetailForm.orderDetail.attentionTo}</td>
		                </tr>
		                <tr>
		                  <td class="label" for="assetIdentifier"><spring:message code="requestInfo.label.serviceOrder.assetIdentifier"/></td>
		                  <td id="spclHandling">${orderDetailForm.orderDetail.assetIdentifier}</td>
		                </tr>
		              </table>
				    </td>
				  </tr>
				</table>
 
 
            </div>
          </div>
        </div>
        <!-- DELIVERY INFORMATION BLOCK - End --> 
        <!-- NOTES BLOCK - Start -->
        <%--<div class="portletBlock infoBox rounded shadow">
          <div class="columnsOne">
            <div class="columnInner">
              <h4>Notes</h4>
              <!-- Grid Start -->
              <div class="portlet-wrap rounded">
                <div class="portlet-wrap-inner">
                  <div id="notesGrid" class="gridbox gridbox_light "></div>
                </div>
              </div>
              <div class="pagination"><span id="notesPagingArea"></span><span id="gridNotesRecinfoArea"></span></div>
              <!-- Grid End --> 
            </div>
          </div>
        </div> --%>
        <!-- NOTES BLOCK - End -->
        <!-- NOTIFICATIONS BLOCK - Start -->
        <div class="portletBlock infoBox rounded shadow">
          <div class="columnsOne">
            <div class="columnInner">
              <h4 class="expand"><spring:message code="requestInfo.heading.serviceOrder.emailNotifications"/></h4>
              <div class="collapse height-auto" >
               <!-- Grid Start -->
              <div class="portlet-wrap rounded">
                <div class="portlet-wrap-inner">
                  <div id="emailNotificationGrid" class="gridbox gridbox_light "></div>
                </div>
              </div>
              <div class="pagination"><span id="emailNotificationPagingArea"></span><span id="gridEmailNotificationRecinfoArea"></span></div>
              <!-- Grid End --> 
              </div>
            </div>
          </div>
        </div>
        <!-- NOTIFICATIONS BLOCK - End --> 
      </div>
    </div>
  
	
	<div class="buttonContainer">
		<button type="button" class="button" id="btn_update"><span><spring:message code="button.update"/></span></button>&nbsp;&nbsp;
		<button type="button" class="button_cancel" onclick="javascript:cancelUpdateOrder();"><span><spring:message code="button.cancel"/></span></button>
	</div>
	</form:form>
	
  
  </div>


<script>

		jQuery(function() {
		    jQuery("h4.expand").toggler();
		});
		function isDigit(value){ 
			var value1=jQuery.trim(value);
			var patrn=/^[0-9]{1,20}$/; 
			if (!patrn.exec(value1)) 
				return false; 
			return true; 
		} 
		function isVaildDate(value)
		{
			var value1=jQuery.trim(value);
			var patrn=/^(\d{1,2})\/(\d{1,2})\/(\d{4})$/;
			if(!patrn.exec(value1))
				return false;
			return true;
		}

		jQuery(document).ready(function(){
			
			if (jQuery.browser.msie === true) {
				jQuery('input, textarea, select').bind('focus', function () {
					jQuery(this).addClass('ieFocusHack');
				}).bind('blur', function () {
					jQuery(this).removeClass('ieFocusHack');
				});
			}			
			//#div_pendingShipment_container_1
			/*if(pendingShipmentRequestGrid!=null)
			{
				var pendingRowID = pendingShipmentRequestGrid.getAllRowIds();				
				var pendingArray = pendingRowID.split(",");
				
				//validation for processed part
				for(var i=0;i<pendingArray.length;i++)
				{
					pendingShipmentRequestGrid.cellById(pendingArray[i],6).setAttribute("title"," ");
				}
			}*/
			
			jQuery("#serviceProviderOrderRefNo").val('${orderDetailForm.orderDetail.serviceProviderReferenceNumber}');
			//variables for setting data after validation
			 var updatedPartNumberList;
			 var updatedPartNumberListArr;
			 var updatedQuantityList;
			 var updatedQuantityListArr;
			 var updatedShipQuantityList;
			 var updatedShipQuantityListArr;
			 var updatedCarrierList;
			 var updatedCarrierListArr;
			 var updatedTrackingList;
			 var updatedTrackingListArr;
			 var updatedActualShippedDateList;
			 var updatedActualShippedDateListArr;
			 var updatedBackOrderQtyList;
			 var updatedBackOrderQtyListArr;
			 var updatedActualBackOrderQtyList;
			 var updatedActualBackOrderQtyListArr;
			 var updatedETAList;
			 var updatedETAListArr;
			 var updatedSNListArr;
			 var updatedlineIdList;
			 //end
			//getting value after validation
			 if(jQuery.trim(jQuery("#partNumberList_updated").val()).length>0)
			{
				  updatedQuantityList = jQuery("#quantityList_updated").val();
				  updatedQuantityListArr = new Array();
				  updatedQuantityListArr = updatedQuantityList.split(',');
				  updatedPartNumberList = jQuery("#partNumberList_updated").val();
	              updatedPartNumberListArr = new Array();
	              updatedPartNumberListArr = updatedPartNumberList.split(',');
	              updatedShipQuantityList = jQuery("#shipQuantityList_updated").val();
	              updatedShipQuantityListArr = new Array();
	              updatedShipQuantityListArr = updatedShipQuantityList.split(',');
	              updatedCarrierList = jQuery("#carrierList_updated").val();
	              updatedCarrierListArr = new Array();
	              updatedCarrierListArr = updatedCarrierList.split(',');
	              updatedTrackingList = jQuery("#trackingList_updated").val();
	              updatedTrackingListArr = new Array();
	              updatedTrackingListArr = updatedTrackingList.split(',');
	              updatedActualShippedDateList = jQuery("#actualShippedDateList_updated").val();
	              updatedActualShippedDateListArr = new Array();
	              updatedActualShippedDateListArr = updatedActualShippedDateList.split(',');
	              updatedBackOrderQtyList = jQuery("#backOrderedQtyList_updated").val();
	              updatedBackOrderQtyListArr = new Array();
	              updatedBackOrderQtyListArr = updatedBackOrderQtyList.split(',');
	              
	              updatedActualBackOrderQtyList = jQuery("#actualBackOrderedQtyList_updated").val();
	              updatedActualBackOrderQtyListArr = new Array();
	              updatedActualBackOrderQtyListArr = updatedActualBackOrderQtyList.split(',');
	              
	              updatedETAList = jQuery("#ETAList_updated").val();
	              updatedETAListArr = new Array();
	              updatedETAListArr = updatedETAList.split(',');
	              
	              updatedSNList = jQuery("#serialNumberList_updated").val();
	              updatedSNListArr = new Array();	              
	              updatedSNListArr = updatedSNList.split(',');
	              
	              updatedlineIdList = jQuery("#lineIdList_updated").val();
	              updatedlineIdListArr = new Array();	              
	              updatedlineIdListArr = updatedlineIdList.split(',');
	              
	            //end
	          

	              //setting part number into the grid
	              var checked_part_number="";
	              var repeated_part_number_updated=new Array();
	              var count=0;
	              //finding repeated part number
	              for(var i=0;i<updatedPartNumberListArr.length;i++)
	            	  {
	            	  	for(var j=i+1;j<updatedPartNumberListArr.length;j++)
	            	  		{
	            	  			if(checked_part_number!=updatedPartNumberListArr[i])
	            	  				{
	            	  					if(updatedPartNumberListArr[j]==updatedPartNumberListArr[i])
	            	  						{
	            	  						repeated_part_number_updated[count]=updatedPartNumberListArr[i];
	            	  						checked_part_number=updatedPartNumberListArr[i];
	            	  						count++;
	            	  						}
	            	  				}
	            	  		}
	            	  }
//	 			//end
				//adding row to grid
				for(var i=0;i<repeated_part_number_updated.length;i++)
					{
						var repetCount=0;
						for(var j=0;j<updatedPartNumberListArr.length;j++)
							{
								if(repeated_part_number_updated[i]==updatedPartNumberListArr[j])
									{
									repetCount++;
									}
							}
						
						var noOfRows = jQuery('#pendingShipmentTable tbody').children('tr').length;
						var rowCountId=[];
						if(noOfRows>0)
						{
							
							var i=0;
							jQuery('.rowNum').each(function (){
								rowCountId[i]=jQuery(this).val();
								i++;
							});
						}
						
						var partsArray_updatd = rowCountId;
						var startingRowId="";
						var totalRows=((jQuery('#pendingShipmentTable tbody').children('tr').length))/2;
						for(var k=0;k<totalRows;k++)
							{
							rowFlagId=(k+1)*2-1;
							var cell_obj=jQuery('#pendingShipmentTable tr:eq('+rowFlagId+') td:eq(3)').text();	
									if(repeated_part_number_updated[i]==cell_obj)
										{
											
											startingRowId=partsArray_updatd[k];
							
											break;
										}
							}
						
						for(var l=0;l<repetCount-1;l++)
							{
								if(l==0)
									{
					 				var button=document.getElementById("anotherRow_"+startingRowId);
									button.click();
									}
								else
									{
										noOfRows = jQuery('#pendingShipmentTable tbody').children('tr').length;
										rowCountId=[];
										if(noOfRows>0)
											{
												var i=0;
												jQuery('.rowNum').each(function (){
													rowCountId[i]=jQuery(this).val();
													i++;
												});
											}
										var partsArray_updatd1 = rowCountId;
										var nextRowId="";
										
										for(var m=0;m<partsArray_updatd1.length;m++)
											{
												var rowFlagId=0;
												if (partsArray_updatd[i]!=0)
													rowFlagId=partsArray_updatd[i]*2-1;
												else rowFlagId=1;
												var cell_obj1=jQuery('#pendingShipmentTable tr:eq('+rowFlagId+') td:eq(3)').text();
												if(repeated_part_number_updated[i]==cell_obj1)
													{
														nextRowId=partsArray_updatd1[m];
														
													}
											}
						 				var button1=document.getElementById("anotherRow_"+nextRowId);
						 				button1.click();
									}
								
							}
						
					}
					//end
				
				//setting value to grid
					noOfRows = jQuery('#pendingShipmentTable tbody').children('tr').length;
					rowCountId=[];
						if(noOfRows>0)
						{
							
							var i=0;
							jQuery('.rowNum').each(function (){
								rowCountId[i]=jQuery(this).val();
								i++;
							});
						}
						var partsArray_updatd2 = rowCountId;
						//alert('partsArray_updatd2----  '+partsArray_updatd2);
				for(var x=0;x<updatedPartNumberListArr.length;x++)
				{
					if(updatedShipQuantityListArr[x]!="empty")
						jQuery("#ship_quantity_row"+partsArray_updatd2[x]).attr("value",updatedShipQuantityListArr[x]);
					else
						jQuery("#ship_quantity_row"+partsArray_updatd2[x]).attr("value"," ");
					if(updatedCarrierListArr[x]!="empty")
						jQuery("#carrier_row"+partsArray_updatd2[x]).attr("value",updatedCarrierListArr[x]);
					else
						{
							jQuery("#carrier_row"+partsArray_updatd2[x]+" option:selected").text(" ");
						}
						
					if(updatedTrackingListArr[x]!="empty")
						jQuery("#tracking_number_row"+partsArray_updatd2[x]).attr("value",updatedTrackingListArr[x]);
					else
						jQuery("#tracking_number_row"+partsArray_updatd2[x]).attr("value"," ");
					
					if(updatedActualShippedDateListArr[x]!="empty")
						jQuery("#actual_shipped_date_row"+partsArray_updatd2[x]).attr("value",updatedActualShippedDateListArr[x]);
					else
						jQuery("#actual_shipped_date_row"+partsArray_updatd2[x]).attr("value"," ");
					
					if(updatedBackOrderQtyListArr[x]!="empty" && updatedBackOrderQtyListArr[x]!="doesnotExist")
						jQuery("#back_order_quantity_row"+partsArray_updatd2[x]).attr("value",updatedBackOrderQtyListArr[x]);
					else
					{
					if(updatedBackOrderQtyListArr[x]="empty")
						jQuery("#back_order_quantity_row"+partsArray_updatd2[x]).attr("value"," ");
					}
					
					if(updatedActualBackOrderQtyListArr[x]!="empty" && updatedActualBackOrderQtyListArr[x]!="doesnotExist")
						jQuery("#back_order_orig_"+partsArray_updatd2[x]).attr("value",updatedActualBackOrderQtyListArr[x]);
					else
					{
					if(updatedActualBackOrderQtyListArr[x]="empty")
						jQuery("#back_order_orig_"+partsArray_updatd2[x]).attr("value"," ");
					}
					
					if(updatedETAListArr[x]!="empty" && updatedETAListArr[x]!="doesnotExist")
						jQuery("#eta_row"+partsArray_updatd2[x]).attr("value",updatedETAListArr[x]);
					else
					{
					if(updatedETAListArr[x]="empty")
						jQuery("#eta_row"+partsArray_updatd2[x]).attr("value"," ");
					}
					
					if(updatedSNListArr[x]!="empty" && updatedSNListArr[x]!="doesnotExist")
						jQuery("#serialNo_row"+partsArray_updatd2[x]).attr("value",updatedSNListArr[x]);
					else
					{
					if(updatedSNListArr[x]="empty")
						jQuery("#serialNo_row"+partsArray_updatd2[x]).attr("value"," ");
					}
					
					if(updatedlineIdListArr[x]!="empty" && updatedlineIdListArr[x]!="doesnotExist")
						jQuery("#lineId_"+partsArray_updatd2[x]).attr("value",updatedlineIdListArr[x]);
					else
					{
					if(updatedlineIdListArr[x]="empty")
						jQuery("#lineId_"+partsArray_updatd2[x]).attr("value"," ");
					}
					
				}
					//end
			}
			
		var forwardURL="${finalUpdate}";
		

		
		if(jQuery.trim(jQuery("#processedTrackingList_updated").val()).length>0)
		{
			var RowIdList = gridProcessedPartsGrid.getAllRowIds();
			var processedPartsArr = RowIdList.split(",");	
			for(var i=0;i<processedPartsArr.length;i++)
			{
				var updatedTrackingList = jQuery("#processedTrackingList_updated").val();
				var updatedTrackingListArr = updatedTrackingList.split(',');
				
				var updatedStatusList = jQuery("#statusList_updated").val();
				var updatedStatusListArr = updatedStatusList.split(',');
				
				var updatedDeliveryDateList = jQuery("#deliveryDateList_updated").val();
				var updatedDeliveryDateListArr = updatedDeliveryDateList.split(',');
				
				var updatedProcessedLineIdList = jQuery("#processedLineIdList_updated").val();
				var updatedProcessedLineIdListArr = updatedProcessedLineIdList.split(',');
				
				var updatedProcessedPartList = jQuery("#processedPartList_updated").val();
				var updatedProcessedPartListArr = updatedProcessedPartList.split(',');
				
				for(var j=0;j<updatedTrackingListArr.length;j++)
				{
					
					TrackingNumber = jQuery("#trackingNo_"+i).val();				
					if(updatedTrackingListArr[j] == TrackingNumber)	
					{						
						if(updatedStatusListArr[j] != 'empty')
						{
							jQuery("#status_"+i).attr("value",updatedStatusListArr[j]);
						}
						
						if(updatedDeliveryDateListArr[j] != 'empty')
						{
							var dlDate = document.getElementById(processedPartsArray[i]+'_deliveredDateCal');
							if(dlDate!=null){
								jQuery("#"+i+"_deliveredDateCal").attr("value",updatedDeliveryDateListArr[j]);
							}
						}	
						
						if(updatedProcessedLineIdListArr[j] != 'empty')
						{
							jQuery("#processedLineId_"+i).attr("value",updatedProcessedLineIdListArr[j]);
						}
						
						if(updatedProcessedPartListArr[j] != 'empty')
						{
							jQuery("#partNumber_"+i).attr("value",updatedProcessedPartListArr[j]);
						}
					}
				}
			}
		}
		
			jQuery("#btn_update").click(function(){
				callOmnitureAction('<%= LexmarkPPOmnitureConstants.SERVICEORDERDETAIL%>', '<%= LexmarkPPOmnitureConstants.SERVICEORDERDETAILUPDATEORDERBUTTON%>');
				var validation_error_flag=false;
				jQuery("#errorDiv").html("");
				jQuery("#errorDiv").hide();
				
				if(gridProcessedPartsGrid!=null)
				{
					
					processRowID = gridProcessedPartsGrid.getAllRowIds();				
					processedPartsArray = processRowID.split(",");
					
					//validation for processed part
					for(var i=0;i<processedPartsArray.length;i++)
					{
						var dlDate = document.getElementById(processedPartsArray[i]+'_deliveredDateCal');
						var status = document.getElementById('status_'+processedPartsArray[i]);

						if(status!=null)
						{
							var trackingNo = jQuery("#trackingNo_"+processedPartsArray[i]).val();
							if(jQuery.trim(jQuery("#status_"+processedPartsArray[i]).val()) == 'Delivered')
							{
								if(jQuery.trim(jQuery("#"+processedPartsArray[i]+"_deliveredDateCal").val())=='')
								{
									validation_error_flag=true;
									jQuery("#errorDiv").append("<li><strong>"+"<spring:message code="requestInfo.validation.source.forTrackingNumber" arguments="\"+trackingNo+\""/>&nbsp;<spring:message code="requestInfo.validation.source.deliveredDate.format.errorMsg"/>"+"</strong></li>");
								}
							}
						}
						
						if(dlDate!=null){
							/* if(jQuery("#"+processedPartsArray[i]+"_deliveredDateCal").val().length>0)
							{
								if(!isVaildDate(jQuery.trim(jQuery("#"+processedPartsArray[i]+"_deliveredDateCal").val())))
								{
									validation_error_flag=true;
									var cell_obj=gridProcessedPartsGrid.cellById(processedPartsArray[i],1);
									jQuery("#errorDiv").append("<li><strong>"+"<spring:message code="requestInfo.validation.source.forTrackingNumber" arguments="\"+cell_obj.getValue()+\""/>&nbsp;<spring:message code="requestInfo.validation.source.deliveredDate.format.errorMsg"/>"+"</strong></li>");									
								}								
							} */
						}
					}
				
					//end
				}
				
				//For PendingShipmentGrid
				var noOfRows = jQuery('#pendingShipmentTable tbody').children('tr').length;
				if(noOfRows>0)
				{
					var rowCountId=[];
					var i=0;
						jQuery('.rowNum').each(function (){
							rowCountId[i]=jQuery(this).val();
							i++;
						});
					var partsArray = rowCountId;
					var processedPartsArray;
					var processRowID;
					
					var repeatedPartNumber=check_repeated_partNumber(partsArray);
					if(repeatedPartNumber!=false)
						{
						var row_id;
						var totalRows=((jQuery('#pendingShipmentTable tbody').children('tr').length))/2;
						for(var i=0;i<repeatedPartNumber.length;i++)
							{
							row_id=getRowIds(repeatedPartNumber[i],partsArray);	
							rowFlagId=(i+1)*2-1;
								var cellObj_actualShipQty=jQuery('#pendingShipmentTable tr:eq('+rowFlagId+') td:eq(2)').text();
								var totalShipQty=0;
								var repeatedBackOrderQty=0;
								var repeatedBackOrderQtyOrig=0;
								var repeatedTotalQty = 0;
								if(jQuery("#back_order_orig_"+row_id[i]).length!=0&&parseInt(jQuery.trim(jQuery("#back_order_orig_"+row_id[i]).val()))>=0)
								{
									repeatedBackOrderQtyOrig=jQuery.trim(jQuery("#back_order_orig_"+row_id[i]).val());
								}else{
									repeatedBackOrderQtyOrig=0;
								}
								
								if(jQuery("#back_order_quantity_row"+row_id[i]).length!=0&&parseInt(jQuery.trim(jQuery("#back_order_quantity_row"+row_id[i]).val()))>=0)
								{
									repeatedBackOrderQty=jQuery.trim(jQuery("#back_order_quantity_row"+row_id[i]).val());
								}else{
									repeatedBackOrderQty=0;
								}
								

								repeatedTotalQty = cellObj_actualShipQty;
								repeatedTotalQty = parseInt(repeatedTotalQty) + (parseInt(repeatedBackOrderQtyOrig)-parseInt(repeatedBackOrderQty));

								for(var j=0;j<row_id.length;j++)
								{	
									
									var ship_qty=0;
									
									if(parseInt(jQuery.trim(jQuery("#ship_quantity_row"+row_id[j]).val()))>=0)
										ship_qty=jQuery.trim(jQuery("#ship_quantity_row"+row_id[j]).val());
									

									totalShipQty=parseInt(totalShipQty)+parseInt(ship_qty);
									

										
								}
								
	
								
								if(parseInt(totalShipQty) > repeatedTotalQty)
								{
									validation_error_flag=true;
									jQuery("#errorDiv").append("<li><strong>"+"<spring:message code="requestInfo.validation.source.forPartNumber" arguments="\"+repeatedPartNumber[i]+\""/>&nbsp;"+"<spring:message code="requestInfo.validation.source.shipQty.errorMsg"/>"+"</strong></li>");
								}
								for(var j=0;j<row_id.length;j++)
								{
									if(jQuery.trim(jQuery("#tracking_number_row"+row_id[j]).val()).length>0)
										{
											for(var k=j+1;k<row_id.length;k++)
												{
												if(jQuery.trim(jQuery("#tracking_number_row"+row_id[j]).val())==jQuery.trim(jQuery("#tracking_number_row"+row_id[k]).val()))
	 												{
	 												validation_error_flag=true;
	 												jQuery("#errorDiv").append("<li><strong>"+"<spring:message code="requestInfo.validation.source.forPartNumber" arguments="\"+repeatedPartNumber[i]+\""/>&nbsp;"+"<spring:message code="requestInfo.validation.source.trackingNum.errorMsg"/>"+"</strong></li>");
	 												break;
	 												}
												}
										}
						
								}
								for(var k=0;k<row_id.length;k++)
								{
									var lineNumber=k+1;
									var shipQuantity=jQuery.trim(jQuery("#ship_quantity_row"+row_id[k]).val());
									var actualShippedDate1=jQuery.trim(jQuery("#actual_shipped_date_row"+row_id[k]).val());
									if(shipQuantity.length>0)
									{
										if(!isDigit(shipQuantity))
										{
											validation_error_flag=true;
											jQuery("#errorDiv").append("<li><strong> "+"<spring:message code="requestInfo.validation.source.forPartNumber" arguments="\"+repeatedPartNumber[i]+\""/>&nbsp;"+"<spring:message code="requestInfo.validation.source.shipQty.format.errorMsg"/>&nbsp;"+"<spring:message code="requestInfo.validation.source.lineNumber" arguments="\"+lineNumber+\"" />"+"</strong></li>");
										}
									}
									
									
									if(shipQuantity.length>0&&parseInt(shipQuantity)>0)
									{
									var carrier=jQuery.trim(jQuery("#carrier_row"+row_id[k]+" option:selected").text());
									
									var trackingNumber=jQuery.trim(jQuery("#tracking_number_row"+row_id[k]).val());
									var actualShippedDate=jQuery.trim(jQuery("#actual_shipped_date_row"+row_id[k]).val());
									if(carrier.length==0)
										{
										validation_error_flag=true;
										jQuery("#errorDiv").append("<li><strong> "+"<spring:message code="requestInfo.validation.source.forPartNumber" arguments="\"+repeatedPartNumber[i]+\""/>&nbsp;"+"<spring:message code="requestInfo.validation.source.carrierEmpty.errorMsg"/>&nbsp;"+"<spring:message code="requestInfo.validation.source.lineNumber" arguments="\"+lineNumber+\"" />"+"</strong></li>");
									
										}
									if(trackingNumber.length==0)
										{
										validation_error_flag=true;
										jQuery("#errorDiv").append("<li><strong> "+"<spring:message code="requestInfo.validation.source.forPartNumber" arguments="\"+repeatedPartNumber[i]+\""/>&nbsp;"+"<spring:message code="requestInfo.validation.source.trackingNumberEmpty.errorMsg"/>&nbsp;"+"<spring:message code="requestInfo.validation.source.lineNumber" arguments="\"+lineNumber+\""/>"+"</strong></li>");
										
										}
									if(actualShippedDate.length==0)
										{
										validation_error_flag=true;
										jQuery("#errorDiv").append("<li><strong> "+"<spring:message code="requestInfo.validation.source.forPartNumber" arguments="\"+repeatedPartNumber[i]+\""/>&nbsp;"+"<spring:message code="requestInfo.validation.source.actualShippedDateEmpty.errorMsg"/>&nbsp;"+"<spring:message code="requestInfo.validation.source.lineNumber" arguments="\"+lineNumber+\""/>"+"</strong></li>");
										
										}
									}

									
									if(shipQuantity.length==0 || (shipQuantity.length>0 && parseInt(shipQuantity)==0))
									{
										var carrier=jQuery.trim(jQuery("#carrier_row"+row_id[k]+" option:selected").text());									
										var trackingNumber=jQuery.trim(jQuery("#tracking_number_row"+row_id[k]).val());
										var actualShippedDate=jQuery.trim(jQuery("#actual_shipped_date_row"+row_id[k]).val());
										if(carrier.length>0 || trackingNumber.length>0 || actualShippedDate.length>0)
										{
											validation_error_flag=true;
											jQuery("#errorDiv").append("<li><strong> "+"<spring:message code="requestInfo.validation.source.forPartNumber" arguments="\"+repeatedPartNumber[i]+\""/>&nbsp;"+"<spring:message code="requestInfo.validation.source.shipQty.empty.errorMsg"/>"+"</strong></li>");
										
										}
									}
								}
								
							}
						}
					var totalRows=((jQuery('#pendingShipmentTable tbody').children('tr').length))/2;
					for(var i=0;i<totalRows;i++)
					{
						var shipQty=0;				
						var backOrderQtyOrig = 0;					
						var backOrderQty=0;
						
						if(jQuery("#ship_quantity_row"+partsArray[i]).length!=0&&parseInt(jQuery.trim(jQuery("#ship_quantity_row"+partsArray[i]).val()))>=0)
							shipQty = jQuery.trim(jQuery("#ship_quantity_row"+partsArray[i]).val());
						if(jQuery("#back_order_quantity_row"+partsArray[i]).length!=0&&parseInt(jQuery.trim(jQuery("#back_order_quantity_row"+partsArray[i]).val()))>=0)
							backOrderQty = jQuery.trim(jQuery("#back_order_quantity_row"+partsArray[i]).val());
						if(jQuery("#back_order_orig_"+partsArray[i]).length!=0&&parseInt(jQuery.trim(jQuery("#back_order_orig_"+partsArray[i]).val()))>=0)
							backOrderQtyOrig = jQuery.trim(jQuery("#back_order_orig_"+partsArray[i]).val());
						
						
						
						var actualShippedDate=jQuery.trim(jQuery("#actual_shipped_date_row"+partsArray[i]).val());
						
						var eta=jQuery.trim(jQuery("#eta_row"+partsArray[i]).val());
						rowFlagId=(i+1)*2-1;
						var cellObj=jQuery('#pendingShipmentTable tr:eq('+rowFlagId+') td:eq(3)').text();
						var cellObj1=jQuery('#pendingShipmentTable tr:eq('+rowFlagId+') td:eq(2)').text();
						var totalQty = parseInt(cellObj1) + (parseInt(backOrderQtyOrig) - parseInt(backOrderQty));
						
						var repeatedPartNumberFlag=false;
						for(var j=0;j<repeatedPartNumber.length;j++)
						{
							if(cellObj==repeatedPartNumber[j])
							{
								repeatedPartNumberFlag=true;
								break;
							}
								
						}
						if(!repeatedPartNumberFlag){
							if(parseInt(shipQty)>totalQty)
							{
								validation_error_flag=true;
								jQuery("#errorDiv").append("<li><strong>"+"<spring:message code="requestInfo.validation.source.forPartNumber" arguments="\"+cellObj+\""/>&nbsp;"+"<spring:message code="requestInfo.validation.source.shipQty.errorMsg"/>"+"</strong></li>");
							}
						}
						if(!repeatedPartNumberFlag){
							if(shipQty.length > 0 && parseInt(shipQty)>0)
							{	
								var checkError=check1(partsArray[i]);
								if(checkError!=true)
									{
									validation_error_flag=true;
									jQuery("#errorDiv").append("<li><strong>"+"<spring:message code="requestInfo.validation.source.forPartNumber" arguments="\"+cellObj+\""/>&nbsp;"+checkError+"</strong></li>");
									}
							}
							if(shipQty.length==0 || (shipQty.length>0 && parseInt(shipQty)==0))
							{
								var carr=jQuery.trim(jQuery("#carrier_row"+partsArray[i]+" option:selected").text());									
								var tracking=jQuery.trim(jQuery("#tracking_number_row"+partsArray[i]).val());
								var actualShipDate=jQuery.trim(jQuery("#actual_shipped_date_row"+partsArray[i]).val());
								if(carr.length>0 || tracking.length>0 || actualShipDate.length>0)
								{
									validation_error_flag=true;
									jQuery("#errorDiv").append("<li><strong> "+"<spring:message code="requestInfo.validation.source.forPartNumber" arguments="\"+cellObj+\""/>&nbsp;"+"<spring:message code="requestInfo.validation.source.shipQty.empty.errorMsg"/>"+"</strong></li>");
								
								}
							}
						}
						if(!repeatedPartNumberFlag)
							{
								if(shipQty.length>0)
								{
									if(!isDigit(shipQty))
									{
										validation_error_flag=true;
										jQuery("#errorDiv").append("<li><strong>"+"<spring:message code="requestInfo.validation.source.forPartNumber" arguments="\"+cellObj+\""/>&nbsp;"+"<spring:message code="requestInfo.validation.source.shipQty.format.errorMsg"/>"+"</strong></li>");
									}
								}
							}
						if(backOrderQty.length>0)
							{
								if(!isDigit(backOrderQty))
								{
									validation_error_flag=true;
									jQuery("#errorDiv").append("<li><strong>"+"<spring:message code="requestInfo.validation.source.forPartNumber" arguments="\"+cellObj+\""/>&nbsp;"+"<spring:message code="requestInfo.validation.source.backOrderQty.format.errorMsg"/>"+"</strong></li>");
								}
							}
						 
					}
				}
				if(validation_error_flag==false)
					{						
						jQuery("#orderUpdateForm").attr("action",forwardURL);
						var noOfRows = jQuery('#pendingShipmentTable tbody').children('tr').length;
						if(noOfRows>0)
						{
							var rowCountId=[];
							var i=0;
							jQuery('.rowNum').each(function (){
								rowCountId[i]=jQuery(this).val();
								i++;
							});
							
							var partsArray = rowCountId;
							appendingPendingShipmentGridValues(partsArray);
						}

						
						if(gridProcessedPartsGrid!=null)
						{
							var processedPartsArray;
							var processRowID;
							processRowID = gridProcessedPartsGrid.getAllRowIds();				
							processedPartsArray = processRowID.split(",");
							appendingProcessedPartGridValues(processedPartsArray);
						}
						jQuery("#orderUpdateForm").submit();
						
					}
				else
					{
						jQuery("#errorDiv").show();
						xCo= jQuery("#errorDiv").offset().left;
						yCo= jQuery("#errorDiv").offset().top;
						window.scrollTo(xCo,yCo);	
					}
				
			});
		});
		
		function appendingProcessedPartGridValues(processedPartsArray)
		{ 
			
			var status=new Array();
			var deliveryDate=new Array();
			var processedPart=new Array();
			var TrackingNo=new Array();
			var processTempList=new Array();
			var lineTempList=new Array();
			var processedLineIdList =new Array();
			var j=0;
			for(var i=0;i<processedPartsArray.length;i++)
			{		
				
				var partNumber = jQuery("#partNumber_"+processedPartsArray[i]).val();
				
				
				
				if(partNumber.indexOf(',') == -1)
				{
					processedPart[j] = partNumber;
					if(jQuery.trim(jQuery("#processedLineId_"+processedPartsArray[i]).val()).length>0)
					{
						processedLineIdList[j]=jQuery("#processedLineId_"+processedPartsArray[i]).val();
					}
					else
					{
						processedLineIdList[j]="empty";
					}
					
					if(jQuery.trim(jQuery("#status_"+processedPartsArray[i]).val()).length>0)
					{
						status[j]=jQuery("#status_"+processedPartsArray[i]).val();
					}
					else
					{
						status[j]="empty";
					}
					
					if(jQuery.trim(jQuery("#trackingNo_"+processedPartsArray[i]).val()).length>0)
					{
						TrackingNo[j]=jQuery.trim(jQuery("#trackingNo_"+processedPartsArray[i]).val());
					}
					else
					{
						TrackingNo[j]="empty";
					}
					var dlDate = document.getElementById(processedPartsArray[i]+'_deliveredDateCal');
					if(dlDate!=null){
						if(jQuery.trim(jQuery("#"+processedPartsArray[i]+"_deliveredDateCal").val()).length>0)
						{
							deliveryDate[j]=jQuery.trim(jQuery("#"+processedPartsArray[i]+"_deliveredDateCal").val());
						}
						else
						{
							deliveryDate[j]="empty";
						}
					}else
					{
						deliveryDate[j]="empty";
					}
					j++;
				}
				else
				{
					
					processTempList = jQuery("#partNumber_"+processedPartsArray[i]).val().split(',');
					lineTempList = jQuery("#processedLineId_"+processedPartsArray[i]).val().split(',');
					for(var k=0;k<processTempList.length;k++)
					{
						processedPart[j] = 	processTempList[k];
						processedLineIdList[j] = lineTempList[k];
						if(jQuery.trim(jQuery("#trackingNo_"+processedPartsArray[i]).val()).length>0)
						{
							TrackingNo[j]=jQuery.trim(jQuery("#trackingNo_"+processedPartsArray[i]).val());
						}
						else
						{
							TrackingNo[j]="empty";
						}
						
						if(jQuery.trim(jQuery("#status_"+processedPartsArray[i]).val()).length>0)
						{
							status[j]=jQuery.trim(jQuery("#status_"+processedPartsArray[i]).val());
						}
						else
						{
							status[j]="empty";
						}
						
						
						var dlDate = document.getElementById(processedPartsArray[i]+'_deliveredDateCal');
						if(dlDate!=null){
							if(jQuery.trim(jQuery("#"+processedPartsArray[i]+"_deliveredDateCal").val()).length>0)
							{
								deliveryDate[j]=jQuery.trim(jQuery("#"+processedPartsArray[i]+"_deliveredDateCal").val());
							}
							else
							{
								deliveryDate[j]="empty";
							}
						}
						else
						{
							deliveryDate[j]="empty";
						}
						j++;
					}
				}
				
				
			}		
			
			jQuery("#processedLineIdList").attr("value",processedLineIdList);
			jQuery("#statusList").attr("value",status);
			jQuery("#deliveryDateList").attr("value",deliveryDate);
			jQuery("#processedTrackingList").attr("value",TrackingNo);
			jQuery("#processedPartList").attr("value",processedPart);
			
			
		}
		
		function appendingPendingShipmentGridValues(partsArray)
		{ 
			
			var qty=new Array();
			var lineId=new Array();
			var partNum=new Array();
			var carrier=new Array();
			var trackingNumber=new Array();
			var shipQty=new Array();
			var actualShipDate=new Array();
			var backOrder=new Array();
			var actualBackOrder = new Array();
			var eta=new Array();
			var serialNumber=new Array();
			var gridData="";
			var totalRows=((jQuery('#pendingShipmentTable tbody').children('tr').length))/2;
			for(var i=0;i<totalRows;i++)
			{
				rowFlagId=(i+1)*2-1;
				var cellObj_qty=jQuery('#pendingShipmentTable tr:eq('+rowFlagId+') td:eq(2)').text();
				var cellObj_partNumber=jQuery('#pendingShipmentTable tr:eq('+rowFlagId+') td:eq(3)').text();
					qty[i]=cellObj_qty;					
					partNum[i]=cellObj_partNumber;
					
					if(partNum[i] == "" || partNum[i] == null)
					{
						partNum[i] = "empty";
					}
					
					if(jQuery.trim(jQuery("#ship_quantity_row"+partsArray[i]).val()).length>0)
					{
						shipQty[i]=jQuery.trim(jQuery("#ship_quantity_row"+partsArray[i]).val());
					}
					else
					{
						shipQty[i]="empty";
					}
					if(jQuery.trim(jQuery("#carrier_row"+partsArray[i]+" option:selected").text()).length>0)
					{
						carrier[i]=jQuery.trim(jQuery("#carrier_row"+partsArray[i]+" option:selected").text());
					}
					else
					{
						carrier[i]="empty";
					}
					
					if(jQuery.trim(jQuery("#tracking_number_row"+partsArray[i]).val()).length>0)
					{
						trackingNumber[i]=jQuery.trim(jQuery("#tracking_number_row"+partsArray[i]).val());
					}
					else
					{
						trackingNumber[i]="empty";
					}
					
					if(jQuery.trim(jQuery("#actual_shipped_date_row"+partsArray[i]).val()).length>0)
					{
						actualShipDate[i]=jQuery.trim(jQuery("#actual_shipped_date_row"+partsArray[i]).val());
					}
					else
					{
						actualShipDate[i]="empty";
					}
					
					if(jQuery("#back_order_quantity_row"+partsArray[i]).length==0)
					{
						backOrder[i]="doesnotExist";
					}
					else
					{
						if(jQuery.trim(jQuery("#back_order_quantity_row"+partsArray[i]).val()).length>0)
						{
							backOrder[i]=jQuery.trim(jQuery("#back_order_quantity_row"+partsArray[i]).val());
						}
						else
						{
							backOrder[i]="empty";
						}
					}
					
					if(jQuery("#back_order_orig_"+partsArray[i]).length==0)
					{
						actualBackOrder[i]="doesnotExist";
					}
					else
					{
						if(jQuery.trim(jQuery("#back_order_orig_"+partsArray[i]).val()).length>0)
						{
							actualBackOrder[i]=jQuery.trim(jQuery("#back_order_orig_"+partsArray[i]).val());
						}
						else
						{
							actualBackOrder[i]="empty";
						}
					}
					
					if(jQuery("#eta_row"+partsArray[i]).length==0)
					{
						eta[i]="doesnotExist";
					}
					else
					{
						if(jQuery.trim(jQuery("#eta_row"+partsArray[i]).val()).length>0)
						{
							eta[i]=jQuery.trim(jQuery("#eta_row"+partsArray[i]).val());
							
						}
						else
						{
							eta[i]="empty";
						}
					}
					
					
					
					if(jQuery.trim(jQuery("#serialNo_row"+partsArray[i]).val()).length>0)
					{
						serialNumber[i]=jQuery.trim(jQuery("#serialNo_row"+partsArray[i]).val());
						serialNumber[i] = serialNumber[i] + '|';
						
					}
					else{
						serialNumber[i]="empty";
						serialNumber[i] = serialNumber[i] + '|';
					}
					
					
					if(jQuery("#lineId_"+partsArray[i]).length==0)
					{
						lineId[i]="";
					}
					else
					{
						if(jQuery.trim(jQuery("#lineId_"+partsArray[i]).val()).length>0)
						{
							lineId[i]=jQuery.trim(jQuery("#lineId_"+partsArray[i]).val());
						}else{
							lineId[i]="empty";
						}
					}
				
				}
			
				for(var i=0;i<partsArray.length;i++)
				{
// 					
					var partItem=partNum[i];
					var lineItem=lineId[i];
					if(lineItem != ""){
						
						var j=0;
						for(j=0;j<partsArray.length;j++)
						{
							if(partItem == partNum[j])
								lineId[j]=lineItem;
						}	
					}
					
					
				}
			
				jQuery("#quantityList").attr("value",qty);
				jQuery("#partNumberList").attr("value",partNum);
				jQuery("#shipQuantityList").attr("value",shipQty);
				jQuery("#carrierList").attr("value",carrier);
				jQuery("#trackingList").attr("value",trackingNumber);
				jQuery("#actualShippedDateList").attr("value",actualShipDate);
				jQuery("#backOrderedQtyList").attr("value",backOrder);
				jQuery("#actualBackOrderedQtyList").attr("value",actualBackOrder);
				jQuery("#ETAList").attr("value",eta);
				jQuery("#serialNumberList").attr("value",serialNumber);
				jQuery("#lineIdList").attr("value",lineId);
			
		}
		
		function getRowIds(repeatedPartNumber,partsArray)
		{
			
			var rowIds=new Array();
			var j=0;
			var totalRows=((jQuery('#pendingShipmentTable tbody').children('tr').length))/2;
			for(var i=0;i<totalRows;i++)
				{
				rowFlagId=(i+1)*2-1;
				var cellObj_partNumber=jQuery('#pendingShipmentTable tr:eq('+rowFlagId+') td:eq(3)').text();	
					var partNumber=cellObj_partNumber;
					if(repeatedPartNumber==partNumber)
					{
						rowIds[j]=partsArray[i];
						j++;
					}
				}
			return(rowIds);
		}
		
		function check_repeated_partNumber(partsArray)
		{
			
			var checked_partNumber="";
			var repeatedPartNumber=new Array();
			
			var k=0;
			var totalRows=((jQuery('#pendingShipmentTable tbody').children('tr').length))/2;
			for(var i=1;i<=totalRows;i++)
			{
				var rowFlagId=0;
				rowFlagId=i*2-1;
				var cellObj_partNumber1=jQuery('#pendingShipmentTable tr:eq('+rowFlagId+') td:eq(3)').text();
				var partNumber1=cellObj_partNumber1;
				for(var j=i+1;j<=totalRows;j++)
					{
					if(partNumber1!=checked_partNumber){
						rowFlagId=j*2-1;
						var cellObj_partNumber2=jQuery('#pendingShipmentTable tr:eq('+rowFlagId+') td:eq(3)').text();
						var partNumber2=cellObj_partNumber2;
						if(partNumber1==partNumber2)
							{
							repeatedPartNumber[k]=partNumber2;
								checked_partNumber=partNumber2;
								k++;
								break;
							}
						}
					}
			}
			if(repeatedPartNumber.length)
				{	
				return repeatedPartNumber;
				}
			return false;
		}
		
		function check1(rowId)
		{	
			var errorMessage="";
			var errorFlag=false;
			var carrier=jQuery.trim(jQuery("#carrier_row"+rowId+" option:selected").text());
			
			var trackingNumber=jQuery.trim(jQuery("#tracking_number_row"+rowId).val());
			
			var actualShippedDate=jQuery.trim(jQuery("#actual_shipped_date_row"+rowId).val());
			
			if(carrier.length==0)
				{
					errorFlag=true;
					errorMessage+="<spring:message code="requestInfo.validation.source.carrierEmpty.errorMsg"/>&nbsp;";
					
				}
			if(trackingNumber.length==0)
				{
					errorFlag=true;
					errorMessage+="<spring:message code="requestInfo.validation.source.trackingNumberEmpty.errorMsg"/>&nbsp;";
					
				}
			if(actualShippedDate.length==0)
				{
					errorFlag=true;
					errorMessage+="<spring:message code="requestInfo.validation.source.actualShippedDateEmpty.errorMsg"/>&nbsp;";
					
				}
			if(errorFlag)
				{
					return errorMessage;
				}
			return true;
				
		}
		//end-------------------
	
	
	
	
	
	// GRID for NOTIFICATIONS
	var gridEmailNotificationGrid;
	gridEmailNotificationGrid = new dhtmlXGridObject('emailNotificationGrid');
	gridEmailNotificationGrid.setImagePath("../../../images/gridImgs/");
	gridEmailNotificationGrid.setHeader("<spring:message code="requestInfo.serviceOrder.listHeader.emailNotification"/>");
	gridEmailNotificationGrid.setInitWidths("30,100,200,*");
	gridEmailNotificationGrid.enableResizing("false,false,false,false");
	gridEmailNotificationGrid.setColAlign("center,left,left,left");
	gridEmailNotificationGrid.setSkin("light");
	gridEmailNotificationGrid.setColTypes("sub_row,ro,ro,ro");
	gridEmailNotificationGrid.enableAutoHeight(true);
	gridEmailNotificationGrid.enableMultiline(true);
	gridEmailNotificationGrid.enableColumnMove(true);
	gridEmailNotificationGrid.enableAlterCss("even_row","odd_row");
	gridEmailNotificationGrid.init();
	gridEmailNotificationGrid.enablePaging(true, 4, 2, "emailNotificationPagingArea", true, "gridEmailNotificationRecinfoArea");
	gridEmailNotificationGrid.setPagingSkin("bricks");
	gridEmailNotificationGrid.loadXMLString("${orderDetailForm.emailNotificationListXML}");
	
	
	
	function changeCellStatus(){
		
	}
	
	
	var rowsAdded=1;
	function addRowToGrid(rowid,listsize){
		callOmnitureAction('<%= LexmarkPPOmnitureConstants.SERVICEORDERDETAILUPDATE%>', '<%= LexmarkPPOmnitureConstants.SERVICEORDERDETAILUPDATEADDANOTHERLINE%>');
		jQuery('#anotherRow_'+rowid).hide();
		var newRowid=parseInt(listsize)+rowsAdded;
		var getValuesId=0;
		var row_id="#newRowData_"+rowid;
		if (rowid!=0){
		getValuesId=jQuery('#pendingShipmentTable tbody').children('tr').length;
		var cellObj=jQuery('#pendingShipmentTable tr#'+row_id+' td:eq(2)').text();
		var cellvalue1=cellObj;
		cellObj=jQuery('#pendingShipmentTable tr#'+row_id+' td:eq(3)').text();
		var cellvalue2=cellObj;
		cellObj=jQuery('#pendingShipmentTable tr#'+row_id+' td:eq(4)').text();
		var cellvalue3=cellObj;
		}else{
			var cellObj=jQuery('#pendingShipmentTable tr#'+row_id+' td:eq(2)').text();
			var cellvalue1=cellObj;
			cellObj=jQuery('#pendingShipmentTable tr#'+row_id+' td:eq(3)').text();
			var cellvalue2=cellObj;
			cellObj=jQuery('#pendingShipmentTable tr#'+row_id+' td:eq(4)').text();
			var cellvalue3=cellObj;
		}
		
		var dropDownList ="<select class=\"w150\" id=\"carrier_row"+newRowid+"\"><option></option>" ;	
		
		<c:forEach items="${carrierDropdown}" var="requestStatus" varStatus = "status" >
		dropDownList+="<option value=${requestStatus.key}>${requestStatus.value}</option>";
		</c:forEach>
		dropDownList+="</select>";
		while(dropDownList.indexOf(',')!= -1)
			dropDownList=dropDownList.replace(',','&#44;');
		
			var newSubRowData="<tr id=\"newSubRowData_"+newRowid+"\">" +
				"<td colspan=\"5\"><spring:message code='requestInfo.label.serviceOrder.serialNumber'/></td>"+
				"<td colspan=\"3\"><textarea class=\"w200\" id=\"serialNo_row"+newRowid+"\"></textarea><br />"+
				"<p class=\"note\"><spring:message code='requestInfo.message.serviceOrder.enterSerialNumber'/></p></td>"+
				"<td colspan=\"3\" class=\"btnSelectGrid\">"+
				"<button id=\"anotherRow_"+newRowid+"\" type=\"button\" class=\"button\" onclick=\"addRowToGrid('"+newRowid+"'&#44;'"+listsize+"');\">Add Another Line</button></td>"+
				"</tr>";
			var newRowData="<tr id=\"newRowData_"+newRowid+"\">" +
			"<input type=\"hidden\" class=\"rowNum\" id=\"row_ID_"+newRowid+"\" value=\""+newRowid+"\" />"+
			"<td class=\"rowtext\" onClick=\"changeCellStatus("+newRowid+");\" style=\"width:50px;\"><img id=\"subrow_img_"+newRowid+"\" src=\"/LexmarkPartnerPortal/images/gridImgs/minus.gif\" /></td>"+
			"<td class=\"rowtext\">"+"<img class=\"ui-icon trash-icon\" src=\"/LexmarkPartnerPortal/images/transparent.png\" onClick=\"deleteRow('"+newRowid+"')\"/> </td>"+
			"<td class=\"rowtext\">"+cellvalue1+"</td>"+
			"<td class=\"rowtext\">"+cellvalue2+"</td>"+
			"<td class=\"rowtext\">"+cellvalue3+"</td>"+
			"<td class=\"rowtext\">"+"<input type=\"text\" class=\"w50 right\" id=\"ship_quantity_row"+newRowid+"\" value=\"\" /></td>"+
			"<td class=\"rowtext\">"+dropDownList+"</td>"+
			"<td class=\"rowtext\">"+"<input type=\"text\" class=\"w100\"  id=\"tracking_number_row"+newRowid+"\" /> </td>"+
			"<td class=\"rowtext\">"+"<input class=\"w100\" type=\"text\" style=\"float:left;\" id=\"actual_shipped_date_row"+newRowid+"\" />"+ 
			"<img class=\"ui-icon calendar-icon\" src=\"/LexmarkPartnerPortal/images/transparent.png\" title=\"Select a Date\"" +
					"onClick=\"showcal('actual_shipped_date_row"+newRowid+"'&#44;null&#44;null&#44;true)\"/> </td><td colspan=\"2\"></td></tr>";
			jQuery('#pendingShipmentTable tbody').append(newRowData+newSubRowData);
			rowsAdded++;
			addAltClass();
		
		};
		
		var origRowId = 1;
		/*function removeRow(rowid,originalRowId,listsize)
		{
			var nextCount=0;
			var prevCount=0;
			var nextRowId = parseInt(rowid);
			var prevRowId = parseInt(rowid);
			var prevIds=new Array();			
			var i,j=0,k;
			
			for(i=1;i<=rowsAdded;i++)
			{				
				nextRowId = nextRowId + 1;
				prevRowId = prevRowId - 1;
				if(jQuery("#actual_shipped_date_row"+nextRowId).length > 0)
				{
					if(jQuery('#pendingShipmentTable tr:eq('+nextRowId+') td:eq(3)').text() == jQuery('#pendingShipmentTable tr:eq('+rowid+') td:eq(3)').text())
						nextCount++;
				}					
				
				if(jQuery("#actual_shipped_date_row"+prevRowId).length > 0)
				{
					if(jQuery('#pendingShipmentTable tr:eq('+prevRowId+') td:eq(3)').text() == jQuery('#pendingShipmentTable tr:eq('+rowid+') td:eq(3)').text())
					{
						prevIds[j] = prevRowId;
						j++;
						prevCount++;
					}
				}				
			}
			
			for(k=0;k<listsize;k++)
			{
				if(jQuery('#pendingShipmentTable tr:eq('+k+') td:eq(3)').text() == jQuery('#pendingShipmentTable tr:eq('+rowid+') td:eq(3)').text())
				{
					origRowId = k;
					break;
				}
			}
			
			if (nextCount > 0){
			}
			else{
				if (prevCount > 0){
					prevRowId = prevIds[0];
					jQuery('#anotherRow_'+prevRowId).show();
				}
				else{
					jQuery('#anotherRow_'+origRowId).show();
				}
			}
			
			jQuery("#newSubRowData_"+rowid).remove();
			jQuery("#newRowData_"+rowid).remove();
		}; */
		
		function cancelUpdateOrder()
		{
			callOmnitureAction('<%= LexmarkPPOmnitureConstants.SERVICEORDERDETAILUPDATE%>', '<%= LexmarkPPOmnitureConstants.SERVICEORDERDETAILUPDATECANCEL%>');
			jConfirm("<spring:message code="requestInfo.common.cancel.alert"/>", "", function(confirmResult) {
				if(confirmResult){
					showOverlay();
					window.location.href = "${orderDetailPageURL}";
				}else{
					return false;
					}
			});			
			
		}
		
		function backToOrderList()
		{
			callOmnitureAction('<%= LexmarkPPOmnitureConstants.SERVICEORDERDETAILUPDATE%>', '<%= LexmarkPPOmnitureConstants.SERVICEORDERDETAILUPDATERETURNTOSERVICEORDERS%>');
			showOverlay();
			window.location.href="service-orders?back=true";
		}

	
		function changeCellStatus(rowId){
			var hideRow=jQuery("#row_ID_"+rowId).val();
			 if (jQuery('#newSubRowData_'+hideRow).is(':visible')){
				 jQuery('#newSubRowData_'+hideRow).hide();
				 jQuery('#subrow_img_'+rowId).attr('src','/LexmarkPartnerPortal/images/gridImgs/plus.gif');
			 }else {
				 jQuery('#newSubRowData_'+hideRow).show();
				 jQuery('#subrow_img_'+rowId).attr('src','/LexmarkPartnerPortal/images/gridImgs/minus.gif');
			 }
				
		}
		
		function deleteRow(deleteRowId){
			var origRowId=deleteRowId;
			var row_id="#newRowData_"+origRowId;
			
			if (!(jQuery('#newSubRowData_'+deleteRowId).is(':visible'))){
				jQuery('#newSubRowData_'+deleteRowId).show();
			}				
			if (jQuery('#anotherRow_'+deleteRowId).is(':visible')){
				while(deleteRowId>=0){
					deleteRowId--;
					if (jQuery('#newRowData_'+deleteRowId).size()){
						var row_id_del="#newRowData_"+deleteRowId;
						if (jQuery('#pendingShipmentTable tr#'+row_id_del+' td:eq(2)').text()==jQuery('#pendingShipmentTable tr#'+row_id+' td:eq(2)').text() && 
								jQuery('#pendingShipmentTable tr#'+row_id_del+' td:eq(3)').text()==jQuery('#pendingShipmentTable tr#'+row_id+' td:eq(3)').text() && 
								jQuery('#pendingShipmentTable tr#'+row_id_del+' td:eq(4)').text()==jQuery('#pendingShipmentTable tr#'+row_id+' td:eq(4)').text()){
						jQuery('#anotherRow_'+deleteRowId).show();
						break;
						}
					}
				}
			}
			jQuery("#newSubRowData_"+origRowId).remove();
			jQuery("#newRowData_"+origRowId).remove();
			addAltClass();
		}
		
		function addAltClass(){
				var rowCountId=[];
				var i=0;
				jQuery('.rowNum').each(function (){
					
					rowCountId[i]=jQuery(this).val();
					i++;
					
				});
				
				var partsArray = rowCountId;
				for(var i=0;i<partsArray.length;i++){
					if(i%2==0){
						jQuery("#newSubRowData_"+partsArray[i]).removeClass("ev_light");
						jQuery("#newRowData_"+partsArray[i]).removeClass("ev_light");
						jQuery("#newSubRowData_"+partsArray[i]).addClass("odd_light");
						jQuery("#newRowData_"+partsArray[i]).addClass("odd_light");
					}
					else {
						jQuery("#newSubRowData_"+partsArray[i]).removeClass("odd_light");
						jQuery("#newRowData_"+partsArray[i]).removeClass("odd_light");
						jQuery("#newSubRowData_"+partsArray[i]).addClass("ev_light");
						jQuery("#newRowData_"+partsArray[i]).addClass("ev_light");
						}
					}
				}
		
	
</script>

