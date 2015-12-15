<%@ taglib uri="/WEB-INF/tld/liferay-portlet.tld" prefix="portlet"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="util" uri="/WEB-INF/tld/lexmark-util.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="html" tagdir="/WEB-INF/tags/html" %>
<%@ page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%> <!-- Added for Defect 3924-Jan Release -->
<!-- Added for CI 13-10-22 STARTS-->
<script type="text/javascript" src='<html:rootPath/>js/jquery.progressbar.js'></script>
<portlet:resourceURL var="downloadAttachment" id="downloadAttachment">
</portlet:resourceURL>
<!-- Added for CI 13-10-22 ENDS-->
<script type="text/javascript">
	barImages = { 0: '<html:imagesPath/>progressBar/progressbg_red.gif',
		45:	'<html:imagesPath/>progressBar/progressbg_half.gif',
   		55: '<html:imagesPath/>progressBar/progressbg_orange.gif',
		70: '<html:imagesPath/>progressBar/progressbg_green.gif'};
	barImagesCancel = {0: '<html:imagesPath/>progressBar/progressbg_black.gif'};
	
	function supplyRqstDetails(ServiceRqstNumber,ServiceRqstType) {
		//alert('in supplyRequestDetailsPopup ServiceRqstNumber='+ServiceRqstNumber+' ServiceRqstType='+ServiceRqstType);
		jQuery('#SupplyRqstNumber').html(ServiceRqstNumber);
		textScroll(document.getElementById('description_Popup'));
		textScroll(document.getElementById('costCenter_Popup'));
		textScroll(document.getElementById('specialInstructions_Popup'));
		//textScroll(document.getElementById('defaultSpecialInstructions_Popup'));
		textScroll(document.getElementById('notes_Popup'));
		//jQuery('#SupplyRqstNumber').html(ServiceRqstNumber);
	}
	/*Method gotoControlPanel added for Defect 3924- Jan Release*/
	function gotoControlPanel(url) {
		controlPanelPopUpWindow.show();
		controlPanelPopUpWindow.io.set('uri',"<portlet:renderURL windowState='<%= LiferayWindowState.POP_UP.toString() %>'><portlet:param name='action' value='gotoControlPanel' /></portlet:renderURL>&controlPanelURL=" + url);
	    controlPanelPopUpWindow.io.start();
		<%--
			 new Liferay.Popup({
		         title: "",
		         position: [400,150], 
		         modal: true,
		         width: 400, 
		         height: 150,
		         xy: [100, 100],
		         onClose: function() {showSelect();},
		         url:"<portlet:renderURL 
		             windowState='<%= LiferayWindowState.POP_UP.toString() %>'>" + 
		            "<portlet:param name='action' value='gotoControlPanel' />" + 
		            "</portlet:renderURL>&controlPanelURL=" + url
		        });  --%>
	};
	var SRInProgressGrid;
	
</script>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/in-styles.css?version=<html:fileCacheVersion/>" />
<div id="contentDetailsSupply" class="div-style28">
    <div class="journal-content-article">
      <h1><spring:message code="Details.changeRequestDetails.heading.supplyReqDeails"/></h1>
      <h2 class="step"><spring:message code="Details.supplyRequestDetails.label.rquestNumber"/><span id="SupplyRqstNumber"></span></h2> </div>
    <div class="main-wrap">
      <div class="content" id="suppliesShipment"> 
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
                  <span>${requestFormPopup.serviceRequest.area.value }</span></li>
                <li>
                  <label><spring:message code="requestInfo.label.subArea"/> </label>
                  <span>${requestFormPopup.serviceRequest.subArea.value }</span></li>
                <c:if test="${userSegment == 'Employee'}">
	                <li>
	                <label> <spring:message code="Details.supplyRequestDetails.label.orderSource"/></label>
	                <span>${requestFormPopup.serviceRequest.orderSource }</span></li>
                  </c:if>
                

                <li>
                  <label><spring:message code="Details.changeRequestDetails.label.status"/> </label>
                  <span>${requestFormPopup.serviceRequest.serviceRequestStatus }</span></li>
                  <li>
               		<label><spring:message code="Details.changeRequestDetails.label.accountName"/>	</label>
               		<span>${requestFormPopup.serviceRequest.accountName }</span>
               </li>
              </ul>
            </div>
          </div>
          <div class="columnsTwo">
            <div class="columnInner">
              <ul class="form">
                <li>
                  <label><spring:message code="Details.changeRequestDetails.label.dateAndTime"/> </label>
                  <%--<span><util:dateFormat value="${requestFormPopup.serviceRequest.serviceRequestDate}" timezoneOffset="${timeZoneOffset}"></util:dateFormat></span></li> --%>
                  <span>${requestFormPopup.creationDate }</span></li>
                <li>
                  <label><spring:message code="Details.changeRequestDetails.label.requesterName"/> </label>
                  <span>${requestFormPopup.serviceRequest.requestor.firstName } &nbsp; ${requestFormPopup.serviceRequest.requestor.lastName }</span></li>
               
                   <%--  Added for Mps Phase 2 start --%>
             
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
              
                  <%--  Added for Mps Phase 2 end --%>
              </ul>
            </div>
          </div>
        </div>

        <c:if test="${requestFormPopup.serviceRequest.asset.serialNumber != null &&  requestFormPopup.serviceRequest.asset.serialNumber != ''}">
        <p class="inlineTitle"><spring:message code="requestInfo.heading.selectedAsset"/></p>
        <!-- ASSET INFO BLOCK START -->
        <div class="portletBlock infoBox rounded shadow">
        <h4><a href="${controlPanelURL}"></a></h4> 
          <!-- Outter table: 1 row & 2 colums -->
          <table width="100%" border="0" cellspacing="0" cellpadding="0" class="assetDetail">
            <tr> 
              <!-- Outter Table > column 1: Product image & Name -->
              <td class="pModel"><ul>
                  <li class="center"><img src="${requestFormPopup.serviceRequest.asset.productImageURL}" id="MyPicture_Popup" onError="image_error();" width="100" height="100">                  
                  <li class="pModelName">${requestFormPopup.serviceRequest.descriptionLocalLang }</li>
                </ul></td>
              <!-- Outter Table > Column 2: Inner table for details -->
              <td class="pDetail"><!-- Inner table: 4 row2 & 2 colums -->
                
                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                  <tr>
                    <td class="h4"><spring:message code="requestInfo.heading.assetInformation"/></td>
                    <td class="h4 separatorV"><spring:message code="requestInfo.heading.serviceAddress"/></td>
                  </tr>
                  <tr>
                    <td class="infoDsiplay"><ul class="form">
                        <li>
                          <label><spring:message code="requestInfo.assetInfo.label.serialNumber"/></label>
                          <span> ${requestFormPopup.serviceRequest.asset.serialNumber }</span></li>
                        <li>
                          <label><spring:message code="requestInfo.assetInfo.label.productName"/></label>
                          <span>${requestFormPopup.serviceRequest.asset.productTLI } </span></li>
                        <li>
                          <label><spring:message code="requestInfo.assetInfo.label.installDate"/> </label>
                          <span><util:dateFormat value="${requestFormPopup.serviceRequest.asset.installDate }"></util:dateFormat></span></li>
                        <li>
                        <!-- Hyperlink for Defect 3924 -->
                          <label><spring:message code="requestInfo.assetInfo.label.ipAddress"/></label>
                          <%--Start: Added for Defect 3924-Jan Release--%>
                          <a href="javascript:gotoControlPanel('${requestFormPopup.serviceRequest.asset.controlPanelURL}')">
                          ${requestFormPopup.serviceRequest.asset.ipAddress}
                          </a>
                          <%--End: Added for Defect 3924-Jan Release--%>
                          </span></li>
                        <li>
                          <label><spring:message code="requestInfo.assetInfo.label.hostName"/></label>
                          <span> ${requestFormPopup.serviceRequest.asset.hostName} </span></li>
                        <li>
                          <label><spring:message code="requestInfo.assetInfo.label.customerAssetTag"/></label>
                          <span>${requestFormPopup.serviceRequest.asset.assetTag}</span></li>
                      </ul></td>
                    <td class="separatorV"><ul class="roDisplay">
                      <li> <%--   <label><spring:message code="orderSuppliesCatalog.shipToAddress.addressLine1"/>:</label> --%>
                         <div>${requestFormPopup.serviceRequest.asset.installAddress.addressLine1}</div>
                         
                            <%--   <label><spring:message code="orderSuppliesCatalog.shipToAddress.addressLine2"/> :</label>--%>
                          <div>${requestFormPopup.serviceRequest.asset.installAddress.addressLine2}</div>
                         <%-- <spring:message code="orderSuppliesCatalog.shipToAddress.addressLine3"/><br /> --%>
                         <%-- <label> <spring:message code="Details.supplyRequestDetails.label.cityStateProvince"/> :</label>--%>
                           <span>
	                        ${requestFormPopup.serviceRequest.asset.installAddress.city}
	                        <c:if test="${requestFormPopup.serviceRequest.asset.installAddress.state != '' || requestFormPopup.serviceRequest.asset.installAddress.province != ''}">
			                  , ${requestFormPopup.serviceRequest.asset.installAddress.state} ${requestFormPopup.serviceRequest.asset.installAddress.province}
			                </c:if>            
                           </span>
                            
                         <%-- <label> <spring:message code="requestInfo.addressInfo.label.country"/></label>--%>
                          <div>${requestFormPopup.serviceRequest.asset.installAddress.country}</div> 
                          <div>${requestFormPopup.serviceRequest.asset.installAddress.postalCode}</div></li>
                          
                        <li> <label> <spring:message code="requestInfo.addressInfo.label.building"/></label>
                        ${requestFormPopup.serviceRequest.asset.installAddress.physicalLocation1}</li>
                        <li> <label><spring:message code="requestInfo.addressInfo.label.floor"/></label>
                        ${requestFormPopup.serviceRequest.asset.installAddress.physicalLocation2} </li>
                        <li> <label><spring:message code="requestInfo.addressInfo.label.office"/></label>
                        ${requestFormPopup.serviceRequest.asset.installAddress.physicalLocation3}</li>
                      </ul></td>
                  </tr>
                </table></td>
            </tr>
          </table>
        </div>
        </c:if>
        <!-- ASSET INFO BLOCK END -->
		 <div class="columnsOne">
            <div class="infoBox columnInner rounded shadow">
              <h4><spring:message code="requestInfo.manageAsset.heading.pageCount"/></h4>
              <div>
              <div class="portletBlock infoBox rounded shadow">
              
             
              
              <div class="portletScroll columnInner separatorV">
              
                         <div id="pageCountsDiv">
								<div id="pageCounts" class="width-100per"></div>	
								
								<div id="div_pageCounts_Popup" class="popup_grid"></div>
	    				</div>		
	    					
	  						
	    				</div><!-- mygrid_container -->
	              	 	</div>
	              	 	</div>
				
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
                  <span>${requestFormPopup.serviceRequest.primaryContact.firstName }&nbsp; ${requestFormPopup.serviceRequest.primaryContact.lastName }</span></li>
                <li>
                  <label><spring:message code="requestInfo.label.phoneNumber"/></label>
                  <span>${requestFormPopup.serviceRequest.primaryContact.workPhone }</span></li>
                <li>
                  <label><spring:message code="requestInfo.label.emailAddress"/></label>
                  <span>${requestFormPopup.serviceRequest.primaryContact.emailAddress }</span></li>
              </ul>
            </div>
            <c:if test="${requestFormPopup.serviceRequest.secondaryContact.firstName != ''}">
            <div class="infoBox columnInner rounded shadow">
              <h4><spring:message code="requestInfo.heading.secondaryContactForThisRequest"/></h4>
              <ul class="form wordBreak">
                <li>
                  <label><spring:message code="requestInfo.label.name"/></label>
                  <span>${requestFormPopup.serviceRequest.secondaryContact.firstName }&nbsp; ${requestFormPopup.serviceRequest.secondaryContact.lastName }</span></li>
                <li>
                  <label><spring:message code="requestInfo.label.phoneNumber"/></label>
                  <span>${requestFormPopup.serviceRequest.secondaryContact.workPhone }</span></li>
                <li>
                  <label><spring:message code="requestInfo.label.emailAddress"/></label>
                  <span>${requestFormPopup.serviceRequest.secondaryContact.emailAddress }</span></li>
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
                  <span>${requestFormPopup.serviceRequest.customerReferenceNumber }</span></li>
                <li>
                  <label><spring:message code="requestInfo.label.costCentre"/></label>
                  <span id="costCenter_Popup">${requestFormPopup.serviceRequest.costCenter }</span></li>
                <li>
                  <label><spring:message code="requestInfo.label.description"/></label>
                  <span id="description_Popup">${requestFormPopup.serviceRequest.addtnlDescription}</span></li>
              </ul>
            </div>
          </div>
        </div>   
        
        <hr class="separator" />
        <!-- PENDING SHIPMENT BLOCK - START -->
       
       
       
      	<c:if test="${requestFormPopup.pendingRequest.shipmentXML != null }">
      	<div class="portletBlock infoBox rounded shadow">
  	      	<div class="columnsOne">
   	        	<div class="columnInner">
          		  <h4><spring:message code="Details.supplyRequestDetails.heading.pendingshipment"/></h4>
            			
            			<div class="icon"><img class="ui_icon_sprite widgets inProcess-icon" src="<html:imagesPath/>transparent.png" width="100" height="100" /></div>
            			 <div class="portlet-wrap-inner separatorV">
                         <div id="div_inprocessPrintTable_Popup">
							<div id="tab_inprocessGrid_Popup"  style="display:block;">
								<div id="div_inprocessGridContainer_Popup" class="width-100per"></div>
								<div class="pagination">
          							<span id="inprocessPagingArea_Popup" class="popup_grid"></span><span id="inprocessInfoArea_Popup"></span>          
         			 			</div>
	  						</div>
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
                          <span>${shipmentForm.ETA}</span></li>
                        <li>
                          <label><spring:message code="Details.supplyRequestDetails.label.actualDeliveryDate"/></label>
                          <span><util:dateFormat value="${shipmentForm.actualDeliveryDate}" ></util:dateFormat></span></li>
                      	</ul>
                      	</c:when>
                      	<c:otherwise>
							<ul class="form wFull">
		                        <li>
		                          <label><spring:message code="serviceRequest.label.carrier"/>:</label>
		                          <span>${shipmentForm.carrier}</span></li>
		                        <li>
		                          <label><spring:message code="serviceRequest.label.eta"/></label>
		                          <span>${shipmentForm.ETA}</span></li>
		                        <li>
		                          <label><spring:message code="Details.supplyRequestDetails.label.actualDeliveryDate"/></label>
		                          <span>
		                          	<util:dateFormat value="${shipmentForm.actualDeliveryDate}" showTime="true"  timezoneOffset="${timeZoneOffset}" ></util:dateFormat>
		                          </span></li>
		                      	</ul>
						</c:otherwise>
					</c:choose>
                    </td>
                  </tr>
                </table>
                <div class="gridPosition" id="div_shipmentPrintTable_Popup${status.count}">
				<div id="tab_shipmentGrid_Popup${status.count}"  style="display:block;">
					<div id="shipmentGrid_container_Popup${status.count}" class="width-100per"></div>
	 				<div id="shipmentPagingArea_Popup${status.count}" class="popup_grid"></div>
 				</div>
    			</div>
                

              </div>
            </div>
          </div>
        </div>
        
        
        
        <script type="text/javascript">
			if(${shipmentForm.shipmentProgress==0}){
				jQuery('#shipmentProgressBar_Popup${status.count}').progressBar(100, { width:480,speed:25,increment:1,height:12,showText: false, boxImage: '/LexmarkServicesPortal/images/progressBar/progressbar.gif', barImage:barImagesCancel} );
			}else{
				//alert('Status:: '+'${shipmentForm.shipmentProgress}');
				jQuery('#shipmentProgressBar_Popup${status.count}').progressBar(${shipmentForm.shipmentProgress}, { width:480,speed:25,increment:1,height:12,showText: false, boxImage: '/LexmarkServicesPortal/images/progressBar/progressbar.gif', barImage:barImages} );
			}
			SRShipmentGrid${status.count} = new dhtmlXGridObject('shipmentGrid_container_Popup${status.count}');
	 		SRShipmentGrid${status.count}.setImagePath("/LexmarkServicesPortal/images/gridImgs/");
	 		SRShipmentGrid${status.count}.setHeader("<spring:message code='serviceRequest.listHeader.shipment'/>");
	 		SRShipmentGrid${status.count}.setColAlign("right,left,left,left,left,left");
	 		SRShipmentGrid${status.count}.setColTypes("sub_row,ro,ro,ro,ro,ro");
	 		//SRShipmentGrid${status.count}.setColSorting("str,str,str,str,str");
	 		SRShipmentGrid${status.count}.setInitWidths("30,150,*,150,100,*");
	 		SRShipmentGrid${status.count}.enableAutoWidth(true);
	 		SRShipmentGrid${status.count}.enableMultiline(true);
	 		SRShipmentGrid${status.count}.enableAutoHeight(true);
	 		SRShipmentGrid${status.count}.init(); 
	 		SRShipmentGrid${status.count}.prftInit();
	 		SRShipmentGrid${status.count}.enablePaging(true, 5, 10, "shipmentPagingArea_Popup${status.count}", true);
	 		SRShipmentGrid${status.count}.setPagingSkin("bricks");
	 		SRShipmentGrid${status.count}.setSkin("light");
	 		<%-- CHanges for MPS 2.1 Starts--%>
	 		var priceAvailableFlag${status.count}=false;
	 		<c:if test='${requestFormPopup.serviceRequest.billingModel eq "Ship and Bill"}'>
	 			 priceAvailableFlag${status.count}=true;
	 		</c:if>
	 		<c:if test='${requestFormPopup.serviceRequest.billingModel eq "Delayed Purchase"}'>
				SRShipmentGrid${status.count}.attachEvent("onRowCreated",function(rowId){
 				var cellObj${status.count} = SRShipmentGrid${status.count}.cellById(rowId,7);
 				var cellvalue${status.count}=cellObj${status.count}.getValue();
 				<%--alert('row getting created'+cellvalue${status.count}.length);--%>
 				if(cellvalue${status.count}!="")
 					priceAvailableFlag${status.count}=true;
			});
		</c:if>
		<%-- CHanges for MPS 2.1 ENDS--%>
	 		SRShipmentGrid${status.count}.loadXMLString('${shipmentForm.shipmentXML}');
	 		<%-- CHanges for MPS 2.1--%>
	 		if(priceAvailableFlag${status.count}==false && SRInProgressGrid!=null){
	 			SRInProgressGrid.setColumnHidden(7,true);
	 		}
	 			
	 		<%-- CHanges for MPS 2.1 ENDS--%>
	 		//SRShipmentGrid${status.count}.sortRows(0,"str","asc");  
	 		//SRShipmentGrid${status.count}.setSortImgState(true, 0, 'asc');	
			</script>
        </c:forEach>
       	<!-- SHIPMENT BLOCK - END --> 
       
       <!-- ACTIVITY BLOCK - START -->
        <c:if test="${requestFormPopup.serviceRequest.serviceActivityStatus!= null}">
        <div class="portletBlock infoBox rounded shadow">
          <div class="columnsOne">
            <div class="columnInner">
              <h4 class="expand"><spring:message code="Details.supplyRequestDetails.heading.activity"/></h4>
              <div class="collapse">
              <div class="icon"><img class="ui_icon_sprite widgets technician-large-icon" src="<html:imagesPath/>transparent.png" width="100" height="100" /></div>
              <div class="columnInner separatorV">
               
                <table class="shipment wFull">
                  <tr>
                    <td class="w70p"><div class="status">
<!--                         <div id="div_technicianProgressBar">PROGRESS BAR WILL COME HERE</div> -->
                        <div id="div_technicianProgressBar1_Popup" class="status-bar"></div><!-- status-bar -->
                        <table border="0" cellspacing="0" cellpadding="0">
                          <tr>
                            <td><spring:message code="serviceRequest.label.inProcess"/></td>
                            <td><spring:message code="serviceRequest.label.assigned"/></td>
                            <td><spring:message code="reportStatus.PUBLISHOK"/></td>
                          </tr>
                        </table>
                      </div></td>
                    <td>&nbsp;</td>
                  </tr>
                </table>
                <c:if test="${requestFormPopup.serviceRequestTechnicianXML!= null}">
                <div id="div_technicianPrintTable_Popup">
							<div id="tab_technicianGrid_Popup" style="display:block;">
								<div id="div_technicianContainer_Popup" class="div-style48"></div>
    							<div id="div_technicianPagingArea_Popup" class="popup_grid"></div>
    						</div>
    				</div>
    			</c:if>
              </div>
              </div>
            </div>
          </div>
        </div>
        </c:if>
        <!-- ACTIVITY BLOCK - END -->
        
        <!-- CANCEL BLOCK - START -->
        <c:if test="${requestFormPopup.cancelledPartsXML != null}">
        <div class="portletBlock infoBox rounded shadow">
          <div class="columnsOne">
            <div class="columnInner">
              <h4><spring:message code="Details.supplyRequestDetails.heading.cancelled"/></h4>
<!--               <div class="collapse"> -->
              <div class="icon"><img src="/LexmarkServicesPortal/images/cancelled.jpg" width="100" height="100" /></div>
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
								<div id="div_cancelContainer_Popup" class="div-style48"></div>
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
               <!--  <li>
                  <label for="date1"><spring:message code="requestInfo.label.defaultSpecialInstructions"/></label>
                  <span id="defaultSpecialInstructions_Popup"> ${requestFormPopup.serviceRequest.defaultSpecialInstructions}  </span></li> -->
                <li>
                  <label for="expOrder"><spring:message code="requestInfo.label.requestExpediteOrder"/></label>
                  <span> 
	                  <c:if test="${requestFormPopup.serviceRequest.expediteOrder == false}">No</c:if>
	                  <c:if test="${requestFormPopup.serviceRequest.expediteOrder == true}">Yes</c:if>
                  </span>
                  </li>
                <li>
                  <label for="date1"><spring:message code="requestInfo.label.requestedDeliveryDate"/></label>
                  <span><util:dateFormat value="${requestFormPopup.serviceRequest.requestedEffectiveDate}"></util:dateFormat></span></li>
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
			      <span> ${requestFormPopup.serviceRequest.creditCardToken }</span></li>
		       </c:if>
                
              </ul>
            </div>
          </div>
        </div>
        <%-- <div class="portletBlock">
          <div class="columnsOne">
            <div class="infoBox columnInner rounded shadow">
              <h4><spring:message code="orderSupplies.label.paymentDetails"/></h4>
              <ul class="form">
                <li>
                  <label for="poNo"><spring:message code="Details.supplyRequestDetails.label.poNumber"/></label>
                  <span> ${requestFormPopup.serviceRequest.poNumber } </span></li>
              </ul>
            </div>
          </div>
        </div> --%>
        
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
								<%-- Changed for CI 13-10-22 --%>
					           		<td><a href="javascript:void(0);" onclick="downloadAttachment('${entry.attachmentName}','${entry.displayAttachmentName}','${entry.extension}', '${entry.activityId}');" name="${entry.attachmentName}" >${entry.displayAttachmentName}.${entry.extension}</a></td>
					           	</c:if>
					           	<c:if test="${entry.extension == 'URL'}">
					           		<td><a href="javascript:void(0);" onclick="downloadAttachment('${entry.attachmentName}','${entry.displayAttachmentName}','${entry.extension}', '${entry.activityId}');" name="${entry.attachmentName}" >${entry.attachmentName}</a></td>
					           	</c:if>
					        </tr>
              			<% }else{ %>
	              			<tr>
					           	<c:if test="${entry.extension != 'URL'}">
								<%-- Changed for CI 13-10-22--%>
					           		<td><a href="javascript:void(0);" onclick="downloadAttachment('${entry.attachmentName}','${entry.displayAttachmentName}','${entry.extension}', '${entry.activityId}');" name="${entry.attachmentName}" >${entry.displayAttachmentName}.${entry.extension}</a></td>
					           	</c:if>
					           	<c:if test="${entry.extension == 'URL'}">
					           		<td><a href="javascript:void(0);" onclick="downloadAttachment('${entry.attachmentName}','${entry.displayAttachmentName}','${entry.extension}', '${entry.activityId}');" name="${entry.attachmentName}" >${entry.attachmentName}</a></td>
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


<script type="text/javascript">
	dhx_globalImgPath = "/LexmarkServicesPortal/images/comboImgs/";

	image_error= function () {
	
  	document.getElementById("MyPicture_Popup").src = '/LexmarkServicesPortal/images/dummy_noimg.jpg'; 
  
	};

	/** 
	  *  Technician Grid Config begin
	  */
	if(${requestFormPopup.serviceRequest.serviceActivityStatus!= null && requestFormPopup.serviceRequestTechnicianXML!= null}){
	SRTechnicianGrid = new dhtmlXGridObject('div_technicianContainer_Popup');
	SRTechnicianGrid.setImagePath("/LexmarkServicesPortal/images/gridImgs/");
	SRTechnicianGrid.setHeader("<spring:message code='serviceRequest.listHeader.srstatus'/>");
	SRTechnicianGrid.setColAlign("left,left,left");
	SRTechnicianGrid.setColTypes("ro,ro,ro");
	//SRTechnicianGrid.setColSorting("str,str,str");
	SRTechnicianGrid.setInitWidths("150,150,*");
	SRTechnicianGrid.enableAutoWidth(true);
	SRTechnicianGrid.enableMultiline(true);
	SRTechnicianGrid.enableAutoHeight(true);
	SRTechnicianGrid.init(); 
	SRTechnicianGrid.prftInit();
	SRTechnicianGrid.enablePaging(true, 5, 10, "div_technicianPagingArea_Popup", true);
	SRTechnicianGrid.setPagingSkin("bricks");
	SRTechnicianGrid.setSkin("light");
	SRTechnicianGrid.loadXMLString('${requestFormPopup.serviceRequestTechnicianXML}'); 
	//SRTechnicianGrid.setSortImgState(true, 0, 'asc');
	}

	/** 
	  *  InProcess Grid config begin
	  */
	if(${requestFormPopup.pendingRequest.shipmentXML!= null}){
		<%-- CHanges for MPS 2.1--%>
	var priceAvailableFlag=false;
	SRInProgressGrid = new dhtmlXGridObject('div_inprocessGridContainer_Popup');
	<c:if test='${requestFormPopup.serviceRequest.billingModel eq "Ship and Bill"}'>
	 priceAvailableFlag${status.count}=true;
	
	 SRInProgressGrid.attachEvent("onRowCreated",function(rowId){
			var cellObj = SRInProgressGrid.cellById(rowId,5);
			var cellvalue=cellObj.getValue();
			<%--alert('row getting created'+cellvalue.length);--%>
			if(cellvalue==0){
				cellObj.setValue("<spring:message code='requestInfo.error.priceUnavailable'/>");
			}
			if(cellvalue!="<spring:message code='requestInfo.error.priceUnavailable'/>"){
				cellObj.setValue(cellvalue + " "+"("+'${requestFormPopup.serviceRequest.currency}'+")");
			}
			if(cellvalue=="<spring:message code='requestInfo.error.priceUnavailable'/>"){
				SRInProgressGrid.setColumnHidden(5,true);
				}
		});
	</c:if>
	<%-- CHanges for MPS 2.1 ENDS --%>	
	
	SRInProgressGrid.setImagePath("/LexmarkServicesPortal/images/gridImgs/");
	SRInProgressGrid.setHeader("<spring:message code='serviceRequest.listHeader.pendingshipment'/>");
	SRInProgressGrid.setColAlign("left,left,left,left,left,left");
	SRInProgressGrid.setColTypes("ro,ro,ro,ro,ro,ro");
	//SRInProgressGrid.setColSorting("str,str,str,str,str");
	SRInProgressGrid.setInitWidths("180,*,180,100,100,100");
	SRInProgressGrid.enableAutoWidth(true);
	SRInProgressGrid.enableMultiline(true);
	SRInProgressGrid.enableAutoHeight(true);
	SRInProgressGrid.init(); 
	SRInProgressGrid.prftInit();
	SRInProgressGrid.enablePaging(true, 5, 10, "inprocessPagingArea_Popup");
	SRInProgressGrid.setPagingSkin("bricks");
	SRInProgressGrid.setSkin("light");
	SRInProgressGrid.setColumnHidden(4,true);
	<%-- CHanges for MPS 2.1--%>
	<c:if test='${requestFormPopup.serviceRequest.billingModel eq "Consumable Purchase"}'>
	SRInProgressGrid.attachEvent("onRowCreated",function(rowId){
		
		var cellObj = SRInProgressGrid.cellById(rowId,5);
		var cellvalue=cellObj.getValue();
		<%--alert('row getting created'+cellvalue.length);--%>
		if(cellvalue=="<spring:message code='requestInfo.error.priceUnavailable'/>"){
			SRInProgressGrid.setColumnHidden(5,true);
			}
		if(cellvalue!="" && cellvalue!=0)
			{
				priceAvailableFlag=true;
			}
			
	});
</c:if>
			<%-- CHanges for MPS 2.1 june release--%>
		<c:if test='${empty requestFormPopup.serviceRequest.billingModel}'>
		SRInProgressGrid.attachEvent("onRowCreated",function(rowId){
			var cellObj = SRInProgressGrid.cellById(rowId,5);
			var cellvalue=cellObj.getValue();
			if(cellvalue=="<spring:message code='requestInfo.error.priceUnavailable'/>"){
				SRInProgressGrid.setColumnHidden(5,true);
				}
			if(cellvalue!="" && cellvalue!=0)
				priceAvailableFlag=true;
		});
		</c:if>
	<%-- CHanges for MPS 2.1 ENDS--%>
	SRInProgressGrid.loadXMLString('${requestFormPopup.pendingRequest.shipmentXML}'); 
	if(priceAvailableFlag==false)
		{
			SRInProgressGrid.setColumnHidden(5,true);
		}
		//SRInProgressGrid.setColumnHidden(5,true);
	 }	

	/** 
	  *  Cancelled Grid config begin
	  */
	if(${requestFormPopup.cancelledPartsXML!= null}){
	SRCancelledGrid = new dhtmlXGridObject('div_cancelContainer_Popup');
	SRCancelledGrid.setImagePath("/LexmarkServicesPortal/images/gridImgs/");
	SRCancelledGrid.setHeader("<spring:message code='serviceRequest.listHeader.cancelled'/>");
	SRCancelledGrid.setColAlign("left,left,left,left");
	SRCancelledGrid.setColTypes("ro,ro,ro,ro,ro");
	//SRCancelledGrid.setColSorting("str,str,str,str,str");
	SRCancelledGrid.setInitWidths("180,*,100,150");
	SRCancelledGrid.enableAutoWidth(true);
	SRCancelledGrid.enableMultiline(true);
	SRCancelledGrid.enableAutoHeight(true);
	SRCancelledGrid.init(); 
	SRCancelledGrid.prftInit();
	SRCancelledGrid.enablePaging(true, 5, 10, "div_cancelPagingArea_Popup", true);
	SRCancelledGrid.setPagingSkin("bricks");
	SRCancelledGrid.setSkin("light");
	SRCancelledGrid.loadXMLString('${requestFormPopup.cancelledPartsXML}'); 
	//SRInProgressGrid.setSortImgState(true, 0, 'asc');	
	 }


	/**
	****************progressBars configration start*******************
*/
if(${requestFormPopup.serviceRequest.serviceActivityStatus!= null}){
	if(${requestFormPopup.serviceRequestTechnicianProgress==0}){
		jQuery("#div_technicianProgressBar1_Popup").progressBar(100 , { width:480,speed:25,increment:1,height:12,showText: false, boxImage: '/LexmarkServicesPortal/images/progressBar/progressbar.gif', barImage : barImagesCancel} );
	}else{
  		jQuery("#div_technicianProgressBar1_Popup").progressBar(${requestFormPopup.serviceRequestTechnicianProgress}, { width:480,speed:25,increment:1,height:12,showText: false, boxImage: '/LexmarkServicesPortal/images/progressBar/progressbar.gif', barImage : barImages} );
	}
}

if(${requestFormPopup.cancelledPartsXML!= null}){
	//if(${requestFormPopup.serviceRequestTechnicianProgress==0}){
		jQuery("#div_cancelProgressBar_Popup").progressBar(100 , { width:480,speed:25,increment:1,height:12,showText: false, boxImage: '/LexmarkServicesPortal/images/progressBar/progressbar.gif', barImage : barImagesCancel} );
	//}else{
  	//	jQuery("#div_technicianProgressBar1_Popup").progressBar(${requestFormPopup.serviceRequestTechnicianProgress}, { width:480,speed:25,increment:1,height:12,showText: false, boxImage: '/LexmarkServicesPortal/images/progressBar/progressbar.gif', barImage : barImages} );
	//}
}

</script>
<!-- Added for CI 13-10-22 STARTS-->
<script type="text/javascript">
function downloadAttachment(attachmentName, dispAttachmentName, fileExtension, identifier){
		
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
 		window.location = "${downloadAttachment}&attachmentName=" + attachmentName + "&fileExtension=" + fileExtension + "&identifier=" + identifier + "&dispAttachmentName=" + dispAttachmentName;
 	}
}
		pageCountsGrid = new dhtmlXGridObject('pageCounts');
		pageCountsGrid.setImagePath("<html:imagesPath/>gridImgs/");
		pageCountsGrid.setHeader("<spring:message code='requestInfo.heading.commonPageCounts'/>");
		pageCountsGrid.setColAlign("left,left,left,left");
		pageCountsGrid.setColTypes("ro,ro,ro,ro");
		pageCountsGrid.setInitWidths("160,220,180,30");
		pageCountsGrid.enableAutoWidth(true);
		pageCountsGrid.enableMultiline(true);
		pageCountsGrid.enableAutoHeight(true);
		pageCountsGrid.init(); 
		pageCountsGrid.prftInit();		
		pageCountsGrid.enablePaging(true, 5, 10, "div_pageCounts_Popup", true);		
		pageCountsGrid.setPagingSkin("bricks");
		pageCountsGrid.setSkin("light");					
		pageCountsGrid.setColumnHidden(3,true);
						
		pageCountsGrid.loadXMLString('${pageCountsString}');
	
</script>
<!-- Added for CI 13-10-22 ENDS-->
<script>
var controlPanelPopUpWindow;
AUI().use('aui-base',
'aui-io-plugin-deprecated',
'liferay-util-window',
function(A) {

	controlPanelPopUpWindow=Liferay.Util.Window.getWindow(
	{
	dialog: {
	centered: true,
	constrain2view: true,
	//cssClass: 'yourCSSclassName',
	modal: true,
	resizable: false,
	width: 400,
	height: 195,
	visible: false
	}
	}
	).plug(
	A.Plugin.IO,
	{
	autoLoad: false
	})



});


</script>

