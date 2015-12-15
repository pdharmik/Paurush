
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<%@ include file="/WEB-INF/jsp/includeGrid.jsp"%>
<%@page import="com.lexmark.constants.LexmarkPPOmnitureConstants" %>
<script src='<html:rootPath/>js/trackingNumber.js'></script>
<script src='<html:rootPath/>js/jquery.progressbar.js'></script>
<script src='<html:rootPath/>js/portletRedirection.js'></script>

<script src='<html:rootPath/>js/expand.js'></script>
<script type="text/javascript" src="<html:rootPath/>js/mps.js"></script>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/mps.css?version=<html:fileCacheVersion/>" />
<!--[if IE 7]>
	<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/mps_ie7.css?version=<html:fileCacheVersion/>" />
<![endif]-->
<%@ page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>

<portlet:renderURL var="orderUpdatePageURL">
	<portlet:param name="action" value="showUpdateOrderRequestPage"/>
	<portlet:param name="requestNumber" value="${orderDetailForm.orderDetail.serviceRequestNumber}" />
	<portlet:param name="orderNumber" value="${orderDetailForm.orderDetail.orderNumber}" />
	<portlet:param name="vendorAccountId" value="${orderDetailForm.orderDetail.vendorId}" />
	<portlet:param name="fromPage" value="orderDetailView" />
	<portlet:param name="timezoneOffset" value="${timezoneOffset}" />
</portlet:renderURL>

<portlet:renderURL var="orderAcceptUpdatePageURL">
	<portlet:param name="action" value="showUpdateOrderRequestPage"/>
	<portlet:param name="requestNumber" value="${orderDetailForm.orderDetail.serviceRequestNumber}" />
	<portlet:param name="orderNumber" value="${orderDetailForm.orderDetail.orderNumber}" />	
	<portlet:param name="acceptFlag" value="accept" />
	<portlet:param name="vendorAccountId" value="${orderDetailForm.orderDetail.vendorId}" />
	<portlet:param name="fromPage" value="orderDetailView" />
	<portlet:param name="timezoneOffset" value="${timezoneOffset}" />
</portlet:renderURL>

<portlet:resourceURL var="downOrderDetailPdfURL" id="downOrderDetailPdfURL">
    <portlet:param name="requestNumber" value="${orderDetailForm.orderDetail.serviceRequestNumber}" />
	<portlet:param name="orderNumber" value="${orderDetailForm.orderDetail.orderNumber}" />
	<portlet:param name="vendorAccountId" value="${orderDetailForm.orderDetail.vendorId}" />
    <portlet:param name="timezoneOffset" value="${timezoneOffset}" />
</portlet:resourceURL>

<script type="text/javascript">
	
	function print(){
		callOmnitureAction('<%= LexmarkPPOmnitureConstants.SERVICEORDERDETAIL%>', '<%= LexmarkPPOmnitureConstants.SERVICEORDERDETAILPRINT%>'); 
			var url="<portlet:renderURL windowState='<%= LiferayWindowState.POP_UP.toString() %>'><portlet:param name='action' value='printOrderRequestDetail' /></portlet:renderURL>";
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
				    ',status=no,toolbar=yes,menubar=yes,location=no,resizable=yes,scrollbars=yes,titlebar=no');
		};
	
	function updateOrderBtnClick(){
		 callOmnitureAction('<%= LexmarkPPOmnitureConstants.SERVICEORDERDETAIL%>', '<%= LexmarkPPOmnitureConstants.SERVICEORDERDETAILACCEPTUPDATEORDER%>'); 
		showOverlay();
		window.location.href = "${orderUpdatePageURL}";
	}
	
	function acceptOrderBtnClick(){
		callOmnitureAction('<%= LexmarkPPOmnitureConstants.SERVICEORDERDETAIL%>', '<%= LexmarkPPOmnitureConstants.SERVICEORDERDETAILACCEPTUPDATEORDER%>'); 
		showOverlay();
		window.location.href = "${orderAcceptUpdatePageURL}";
	}
	
	function downloadPdf(){
		callOmnitureAction('<%= LexmarkPPOmnitureConstants.SERVICEORDERDETAIL%>', '<%= LexmarkPPOmnitureConstants.SERVICEORDERDETAILPRINTPDF%>'); 
		window.location.href = "${downOrderDetailPdfURL}";
	}
	
	function backToOrderList()
	{
		 callOmnitureAction('<%= LexmarkPPOmnitureConstants.SERVICEORDERDETAIL%>', '<%= LexmarkPPOmnitureConstants.SERVICEORDERDETAILRETURNTOSERVICEORDERS%>'); 
		showOverlay();
		window.location.href="service-orders?back=true";
			
	}

	jQuery(function() {
        jQuery("h4.expand").toggler();
    });
	
	var forPrintGridObjects=[];
	
</script>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/in-styles.css?version=<html:fileCacheVersion/>" />
<body>
<div id="wrapper">
	<div id="error" style="display: none;" class="error">
		<ul>
			<li><strong>
					<spring:message code="requestInfo.exception.serviceOrder.viewServiceOrder"></spring:message>
				</strong>
			</li>
		</ul>
	</div>
	<div id="downloadError" style="display: none;" class="error">
		<ul>
			<li><strong>
					<spring:message code="requestInfo.exception.serviceOrder.downloadServiceOrder"></spring:message>
				</strong>
			</li>
		</ul>
	</div>
  <div id="content-wrapper">
    <div class="journal-content-article">
      <h1><spring:message code="requestInfo.heading.serviceOrder.serviceOrders"/></h1>
    </div>
    <div class="main-wrap">
      <div class="content">
      	<div class="info banner err" style="display: none;" id="failureDiv"> 
			<strong><spring:message code="requestInfo.exception.serviceOrder.save"/></strong>
      	</div>
      	<div style="display: none;" id="successDiv"> 
      	<p class="info banner ok"> 
		  <spring:message code="requestInfo.message.serviceOrder.confirmMessage1"/> <span id="reqNumber"> ${orderDetailForm.orderDetail.serviceRequestNumber} </span>
		  <spring:message code="requestInfo.message.serviceOrder.confirmMessage2"/>
		  </p> 
		 </div>
      	<div class="grid-controls"> 
           <!-- Utilities Start -->
           <div class="utilities">
             <ul>
               <li class="first"><img src="<html:imagesPath/>transparent.png" class="ui-icon pdf-icon" alt="<spring:message code="requestInfo.tooltip.exportToPDF"/>" title="<spring:message code="requestInfo.tooltip.exportToPDF"/>" onClick="downloadPdf();"/></a></li>
               <li><a href="#"><img src="<html:imagesPath/>transparent.png" class="ui-icon print-icon" alt="<spring:message code="requestInfo.link.printThisPage"/>" title="<spring:message code="requestInfo.link.printThisPage"/>" onClick="print()"></a></li>
             </ul>
           </div>
           <!-- utilities End --> 
           <div class="expand-min">
             <ul>
               <li class="first"><img class="ui-icon bck-arrow-icon" src="<html:imagesPath/>transparent.png" alt="<spring:message code="requestInfo.link.backToServiceOrders"/>" width="24" height="24" title="<spring:message code="requestInfo.link.backToServiceOrders"/>" /><a href="#" onClick="backToOrderList()"><spring:message code="requestInfo.link.backToServiceOrders"/></a> </li>
             </ul>
           </div>
         </div>
      
        <!-- MAIN CONTENT GOES HERE -->
        
        <h3 class="pageTitle"><spring:message code="requestInfo.heading.serviceOrder.serviceOrderDetail"/></h3>
        <!-- ORDER INFORMATION BLOCK - Start -->
        <div class="portletBlock infoBox rounded shadow">
        <h4><spring:message code="requestInfo.heading.serviceOrder.orderInformation"/></h4>  
          <div class="columnsTwo w70p">
            <div class="columnInner">
                 
              <table width="100%" border="0" cellspacing="0" cellpadding="0" class="dataGrid dataGrid_top">
                <tr>
                  <td class="label"><spring:message code="requestInfo.label.serviceOrder.requestNumber"/></td>
                  <td id="requestNumber">${orderDetailForm.orderDetail.serviceRequestNumber}</td>
                </tr>
                <tr>
                  <td class="label"><spring:message code="requestInfo.label.serviceOrder.orderNumber"/></td>
                  <td id="orderNumber">${orderDetailForm.orderDetail.orderNumber}</td>
                </tr>
				<!-- Added for Jan release -->
                 <tr>
                  <td class="label"><spring:message code="requestInfo.label.serviceOrder.serviceType"/></td>
                  <td id="serviceType">${orderDetailForm.orderDetail.orderType}</td>
                </tr>
                <!--End Add-->
                <tr>
                  <td class="label"><spring:message code="requestInfo.label.serviceOrder.serviceProviderOrderRefNo"/></td>
                  <td id="serviceProviderOrderRefNo">${orderDetailForm.orderDetail.serviceProviderReferenceNumber}</td>
                </tr>
                <tr>
                  <td class="label"><spring:message code="requestInfo.label.serviceOrder.openedDateAndTime"/></td>
                  <td id="openedDateAndTime"><%-- <fmt:formatDate value="${orderDetailForm.orderDetail.orderDate}" pattern="MM-dd-yyyy hh:mm:ss"/> --%>
                  <util:dateFormat showTime="true" timezoneOffset="${timezoneOffset}" value="${orderDetailForm.orderDetail.orderDate}"></util:dateFormat>
                  </td>
                </tr>
                <tr>
                  <td class="label"><spring:message code="requestInfo.label.serviceOrder.requestStatus"/></td>
                  <td id="requestStatus">${orderDetailForm.orderDetail.status}</td>
                </tr>
                <!-- Added for Jan release -->
                <tr>
                  <td class="label">*<spring:message code="requestInfo.label.serviceOrder.serialNumber"/>                  
                  </td>
                  <td id="serialNumber">${orderDetailForm.orderDetail.asset.serialNumber}                  
                  </td>                  
                </tr>
                <tr>
                  <td colspan="2" class="table-td-style9"><span class="infolabel">*<spring:message code="requestInfo.label.serviceOrder.serialNumberNote"/></span>
                  </td>
                </tr>
                <!-- End Add -->
              </table>              
            </div>
          </div>
          <div class="columnsTwo w30p">
            <div class="columnInner">
              
              <div class="leftPadding">          	
				<c:if test="${(orderDetailForm.orderDetail.status == 'Routed') && (showProcessedUpdateFlag == 'true') && (!empty fulfillmentStatus && (fulfillmentStatus == 'Shipped' || fulfillmentStatus == 'Delivered'))}">								
						<button id="buttonType" class="button" onclick="acceptOrderBtnClick();"><spring:message code="requestInfo.button.acceptAndUpdateOrder"/></button>						       
				</c:if>
 				 <c:if test="${(orderDetailForm.orderDetail.status == 'Order Accepted' && showProcessedUpdateFlag == 'true' && (!empty fulfillmentStatus && (fulfillmentStatus == 'Shipped' || fulfillmentStatus == 'Delivered')))
 				 || (orderDetailForm.orderDetail.status == 'Back Ordered' && showProcessedUpdateFlag == 'true' && (!empty fulfillmentStatus && (fulfillmentStatus == 'Shipped' || fulfillmentStatus == 'Delivered')))
 				 || (orderDetailForm.orderDetail.status == 'Shipped' && showProcessedUpdateFlag == 'true' && !empty fulfillmentStatus && fulfillmentStatus == 'Delivered')
 				 || (orderDetailForm.orderDetail.status == 'Ship Pending')}">						
						<button id="buttonType" class="button" onclick="updateOrderBtnClick();"><spring:message code="requestInfo.button.updateOrder"/></button>						       													
 				</c:if>				 
              </div>
            </div>
          </div>
        </div>
        <!-- ORDER INFORMATION BLOCK - End -->
        <div id="fulfillmentInformation"> 
        <p class="info inlineTitle"><spring:message code="requestInfo.heading.serviceOrder.fulfillmentInformation"/></p>
        
        <c:if test="${showPendingShipmentPartsFlag == 'true'}">
        <!-- PENDING SHIPMENT BLOCK - Start -->
        <div class="portletBlock infoBox rounded shadow">
          <div class="columnsOne">
            <div class="columnInner">
              <h4><spring:message code="requestInfo.heading.serviceOrder.pendingShipment"/></h4>
             <!-- Grid Start -->
              <div class="portlet-wrap rounded">
                <div class="portlet-wrap-inner">
                  <div id="div_pendingShipment_container" class="gridbox gridbox_light "></div>
                </div>
              </div>
	               <div class="pagination"><span id="pendingShipmentPagingArea"></span><span id="pendingShipmntInfoArea"></span></div> 
              <!-- Grid End --> 
            </div>
          </div>
        </div>
        </div>
       
        <script>
	     // GRID for PENDING SHIPMENT	
	    	var pendingShipmentRequestGrid;	
	    	pendingShipmentRequestGrid = new dhtmlXGridObject('div_pendingShipment_container');
	    	pendingShipmentRequestGrid.setImagePath("<html:imagesPath/>gridImgs/");							
	    	pendingShipmentRequestGrid.setHeader(autoAppendPlaceHolder("<spring:message code="requestInfo.serviceOrder.listHeader.pendingShipmentList"/>",7));
	    	pendingShipmentRequestGrid.setColAlign("left,left,left,left,left,left,left");
	    	pendingShipmentRequestGrid.setColTypes("ro,ro,ro,ro,ro,ro,ro");
	    	pendingShipmentRequestGrid.setColSorting("str,str,str,str,str,str,str");
	    	pendingShipmentRequestGrid.setInitWidths("130,120,140,140,120,110,*");
	    	pendingShipmentRequestGrid.enableAutoWidth(true);
	    	pendingShipmentRequestGrid.enableAutoHeight(true);
	    	pendingShipmentRequestGrid.enableMultiline(true);
	    	pendingShipmentRequestGrid.init();
	    	pendingShipmentRequestGrid.setSkin("light");
	    	//pendingShipmentRequestGrid.setPagingSkin("bricks");		
	    	pendingShipmentRequestGrid.loadXMLString("${orderDetailForm.pendingShipmentPartListXML}");	
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
		                <li> <img class="ui-icon expand-icon"src="<html:imagesPath/>transparent.png" alt="<spring:message code="requestInfo.label.expandAll"/>" title="<spring:message code="requestInfo.label.expandAll"/>" /> <a href="javascript:void(0)" onClick="expandAll()";><spring:message code="requestInfo.label.expandAll"/></a> </li>
		              </ul>
		            </div>
	            <!-- Expand-min End --> 
	          	</div> 
	          	             	
              <!-- Grid Start -->
              <div id="processedParts"> 
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
        </div>
        <script>
		     // GRID for PROCESSED PARTS	
		    	var gridProcessedPartsGrid;	
		    	gridProcessedPartsGrid = new dhtmlXGridObject('div_processedParts_container');
		    	gridProcessedPartsGrid.setImagePath("<html:imagesPath/>gridImgs/");
		    	gridProcessedPartsGrid.setHeader(autoAppendPlaceHolder("<spring:message code="requestInfo.serviceOrder.listHeader.processedPartList"/>",6));
// 		    	gridProcessedPartsGrid.setInitWidths("30,*,200,130,130,130");.
				gridProcessedPartsGrid.setInitWidths("30,100,*,*,*,*");
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
        <div id="deliveryInformation">
        <div class="portletBlock infoBox rounded shadow" >
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
					    	<%--<util:addressOutput value="${orderDetailForm.orderDetail.shippingAddress}"/>--%>
					    	
					    <c:if test="${!empty orderDetailForm.orderDetail.shippingAddress.storeFrontName}">
							${orderDetailForm.orderDetail.shippingAddress.storeFrontName}<br>
						</c:if>	
				    	<c:if test="${!empty orderDetailForm.orderDetail.shippingAddress.addressLine1}">
							${orderDetailForm.orderDetail.shippingAddress.addressLine1}<br>
						</c:if>
						<c:if test="${!empty orderDetailForm.orderDetail.shippingAddress.addressLine2}">
							${orderDetailForm.orderDetail.shippingAddress.addressLine2}<br>
						</c:if>
						<%--<c:if test="${!empty orderDetailForm.orderDetail.shippingAddress.addressLine3}">
							${orderDetailForm.orderDetail.shippingAddress.addressLine3}<br>
						</c:if>--%>
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
			<%-- 	<td>${orderDetailForm.orderDetail.primaryContact.emailAddress}</td>		Commented by sankha for LEX:AIR00076493	--%>
					<%-- Added by sankha for LEX:AIR00076493 start --%>
					<td class="wrapWord">${orderDetailForm.orderDetail.primaryContact.emailAddress}</td>
					<%-- End --%>	    
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
		<%--        <td>${orderDetailForm.orderDetail.secondaryContact.emailAddress}</td>    Commented by sankha for LEX:AIR00076493 --%>
					<%-- Added by sankha for LEX:AIR00076493 start --%>
					<td class="wrapWord">${orderDetailForm.orderDetail.secondaryContact.emailAddress}</td>
					<%-- End --%>
				  </tr>
				    </table>
				    </td>
				  </tr>
				  <tr>
				    <td colspan="3">
				    <table class="dataGrid division" width="100%" border="0" cellspacing="0" cellpadding="0" >
		                <%-- <tr>
		                  <td class="label w200"><spring:message code="requestInfo.label.serviceOrder.specialInstructions"/></td>
		                  <td>${orderDetailForm.orderDetail.specialInstructions}</td>
		                </tr> --%>
		                <tr>
		                  <td class="label w200"><spring:message code="requestInfo.label.serviceOrder.requestedDeliveryDate"/></td>
		                  <td><util:dateFormat showTime="true" timezoneOffset="${timezoneOffset}" value="${orderDetailForm.orderDetail.requestedDeliveryDate}"></util:dateFormat>
		                  </td>
		                </tr>
		                <tr>
		                  <td class="label"><spring:message code="requestInfo.label.serviceOrder.mustResolveBy"/></td>
		                  <td>${orderDetailForm.orderDetail.mustResolveBy}</td>
		                </tr>
		                <tr>
		                  <td class="label" for="spclHandling"><spring:message code="requestInfo.label.serviceOrder.specialHandling"/></td>
		                  <td id="spclHandling">${orderDetailForm.orderDetail.specialHandlingInstructions}</td>
		                </tr>
		                <tr>
		                  <td class="label" for="insideLocation"><spring:message code="requestInfo.label.serviceOrder.insideLocation"/></td>
		                  <td>${orderDetailForm.orderDetail.insideLocation}</td>
		                </tr>
		                <tr>
		                  <td class="label" for="attentionTo"><spring:message code="requestInfo.label.serviceOrder.attentionTo"/></td>
		                  <td >${orderDetailForm.orderDetail.attentionTo}</td>
		                </tr>
		                <tr>
		                  <td class="label" for="assetIdentifier"><spring:message code="requestInfo.label.serviceOrder.assetIdentifier"/></td>
		                  <td >${orderDetailForm.orderDetail.assetIdentifier}</td>
		                </tr>
		              </table>
				    </td>
				  </tr>
				</table>
 
 
            </div>
          </div>
        </div>
        </div>
        <!-- DELIVERY INFORMATION BLOCK - End --> 
        <!-- NOTES BLOCK - Start -->
        <%-- <div class="portletBlock infoBox rounded shadow">
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
              <div class="collapse" id="emailNote">
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
  </div> 
</div>
</body>
<script> 	
	
	var exceptionOccured="${exceptionOccured}";
	var updateMessage = "${updateMessage}";
	
	//var downloadExceptionOccured = "${downloadExceptionOccured}";
	jQuery(document).ready(function(){
		if(exceptionOccured !=""){
			jQuery('#error').show();
		}
		
		if(updateMessage == "success"){
			jQuery('#successDiv').show();
			
		}else if(updateMessage == "failure"){
			jQuery('#failureDiv').show();
		}
		
		textScroll(document.getElementById('spclHandling'));
	});	
	// GRID for NOTIFICATIONS
	var gridEmailNotificationGrid;
	gridEmailNotificationGrid = new dhtmlXGridObject('emailNotificationGrid');
	gridEmailNotificationGrid.setImagePath("../../../images/gridImgs/");
	gridEmailNotificationGrid.setHeader(autoAppendPlaceHolder("<spring:message code="requestInfo.serviceOrder.listHeader.emailNotification"/>",4));
	gridEmailNotificationGrid.setInitWidths("30,100,200,*");
	gridEmailNotificationGrid.enableResizing("false,false,false,false");
	gridEmailNotificationGrid.setColAlign("center,left,left,left");
	gridEmailNotificationGrid.setSkin("light");
	gridEmailNotificationGrid.setColTypes("sub_row,ro,ro,ro,ro");
	gridEmailNotificationGrid.enableAutoHeight(true);
	gridEmailNotificationGrid.enableMultiline(true);
	gridEmailNotificationGrid.enableColumnMove(true);
	gridEmailNotificationGrid.enableAlterCss("even_row","odd_row");
	gridEmailNotificationGrid.init();
	gridEmailNotificationGrid.enablePaging(true, 4, 2, "emailNotificationPagingArea", true, "gridEmailNotificationRecinfoArea");
	gridEmailNotificationGrid.setPagingSkin("bricks");
	gridEmailNotificationGrid.loadXMLString("${orderDetailForm.emailNotificationListXML}");
	
	
		
	
</script>
