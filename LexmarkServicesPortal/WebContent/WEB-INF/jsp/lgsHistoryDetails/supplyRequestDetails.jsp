<%-- Added for CI BRD13-10-02--STARTS --%>
<%@page import=" static com.lexmark.constants.LexmarkConstants.gridConfigurationValues" %>
<%@page import=" static com.lexmark.constants.LexmarkConstants.JSON_COMBO_FILTER"%>
<%@page import=" static com.lexmark.constants.LexmarkConstants.JSON_RESOURCE_URL"%>
<%@page import=" static com.lexmark.constants.LexmarkConstants.gridSavingParams" %>
<%-- Added for CI BRD13-10-02--ENDS --%>
<%@page import="com.lexmark.services.util.LexmarkSPOmnitureConstants"%>
<%@ taglib prefix="util" uri="/WEB-INF/tld/lexmark-util.tld" %>
<!-- CSS include -->

<!--[if IE 7]>
	<style type="text/css"><%@ include file="/WEB-INF/css/mps_ie7.css" %></style>
<![endif]-->
<!-- CSS include -->

<% request.setAttribute("subTabSelected","requestHistory");%>
<%@page import="com.liferay.portal.util.PortalUtil"%>
<%String currURL=PortalUtil.getCurrentCompleteURL(request);
if(currURL.indexOf("dFFlag=true")==-1){
	%>
	<%@ include file="../common/subTab.jsp"%>
<% }%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<jsp:include page="/WEB-INF/jsp/includeGrid.jsp"/>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/mps.css"/>
<%@page import="java.util.HashMap"%>
<%
HashMap<?,?> hMap= (HashMap<?,?>)request.getSession().getAttribute("userAccessMapForSr");
String createServiceReq = "";
String createChangeMgmtReq = "";
if(hMap.size()>0){
	createServiceReq = (String)hMap.get("CREATE SERVICE REQUEST");
	createChangeMgmtReq = (String)hMap.get("CREATE CHANGE MGMT REQUEST");	
}
%>
<script type="text/javascript" src='<html:rootPath/>js/jquery.progressbar.js'></script>
<script type="text/javascript" src='<html:rootPath/>js/portletRedirection.js'></script>
<script type="text/javascript" src='<html:rootPath/>js/expand.js'></script>

<script type="text/javascript">
	barImages = { 0: '<html:imagesPath/>progressBar/progressbg_red.gif',
		45:	'<html:imagesPath/>progressBar/progressbg_half.gif',
   		55: '<html:imagesPath/>progressBar/progressbg_orange.gif',
		70: '<html:imagesPath/>progressBar/progressbg_green.gif'};
	barImagesCancel = {0: '<html:imagesPath/>progressBar/progressbg_black.gif'};
</script>
<body onload="expandActivity();"></body>
<div id="dialogChangeDetails" style="display: none;"></div>
<div id="dialogSupplyDetails" style="display: none;"></div>

 <div id="dialogDetails" style="display: none;"></div>
 
    <div class="journal-content-article">
      <h1><spring:message code="Details.changeRequestDetails.heading.supplyReqDeails"/></h1>
      <h2 class="step">
      	<spring:message code="Details.supplyRequestDetails.label.rquestNumber"/> 
      	<span id="reqNo">${requestForm.serviceRequest.serviceRequestNumber }</span>
      </h2> 
      	<div class="utilities floatR">
		  	<ul>
              <li class="first"><a href="javascript:email();"><img src="<html:imagesPath/>transparent.png" class="ui_icon_sprite email-icon" alt="Email this page" ></a></li>
              <li><a href="javascript:print();"><img src="/LexmarkServicesPortal/images/transparent.png" class="ui_icon_sprite print-icon" alt="Print this page" height="27" width="27"></a></li>
            </ul>
          </div>
     </div>    
    <div class="main-wrap">    
      <div class="content" id="printSupplies"> 
        <!-- MAIN CONTENT BEGIN -->
        <div id="printSuppliesTop">
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
                  <span id ="area">${requestForm.serviceRequest.area.value }</span></li>
                <li>
                  <label><spring:message code="requestInfo.label.subArea"/> </label>
                  <span id ="subArea">${requestForm.serviceRequest.subArea.value }</span></li>
                <c:if test="${userSegment == 'Employee'}">
	                <li>
	                <label> <spring:message code="Details.supplyRequestDetails.label.orderSource"/></label>
	                <span id = "orderSource">${requestForm.serviceRequest.orderSource } </span></li>
                  </c:if>
                

                <li>
                  <label><spring:message code="Details.changeRequestDetails.label.status"/> </label>
                  <span id = "status">${requestForm.serviceRequest.serviceRequestStatus }</span></li>
                  
               <li>
               		<label><spring:message code="Details.changeRequestDetails.label.accountName"/>	</label>
               		<span id = "accountName">${requestForm.serviceRequest.accountName }</span>
               </li>   
               
              </ul>
            </div>
          </div>
          <div class="columnsTwo">
            <div class="columnInner">
              <ul class="form">
                <li>
                  <label><spring:message code="Details.changeRequestDetails.label.dateAndTime"/> </label>
<%--                   <util:dateFormat showTime="true" value="${requestForm.serviceRequest.serviceRequestDate}" timezoneOffset="${timeZoneOffset}"></util:dateFormat> --%>
                  <span id="creationTime">${requestForm.creationDate }</span></li>
                <li>
                  <label><spring:message code="Details.changeRequestDetails.label.requesterName"/> </label>
                  <span id = "reqFnameLname">${fn:trim(requestForm.serviceRequest.requestor.firstName)}&nbsp;${fn:trim(requestForm.serviceRequest.requestor.lastName)}</span></li>
               <%--  Added for Mps Phase 2 start --%>
             
               <c:choose>
               		<c:when test='${requestForm.serviceRequest.billingModel ne "Usage Based Billing"}'>
               		  <c:if test="${requestForm.serviceRequest.itemSubTotalBeforeTax ne ''&& requestForm.serviceRequest.itemSubTotalBeforeTax ne '0' && requestForm.serviceRequest.itemSubTotalBeforeTax ne null}">
               		  <li>
                  		<label><spring:message code="Details.changeRequestDetails.label.itemSubTotalBeforeTax"/> </label>
                  		<span id = "subTotal" class="currencyRight"><fmt:formatNumber type="number" maxFractionDigits="2" minFractionDigits="2" value="${requestForm.serviceRequest.itemSubTotalBeforeTax}" /> (${requestForm.serviceRequest.currency})
                  		</span></li>
                  		</c:if>
                  		 <c:if test="${requestForm.serviceRequest.tax ne '' && requestForm.serviceRequest.tax ne '0' && requestForm.serviceRequest.tax ne null}">
                	  <li>
                  		<label><spring:message code="Details.changeRequestDetails.label.tax"/> </label>
                  		<span id = "tax" class="currencyRight"><fmt:formatNumber type="number" maxFractionDigits="2" minFractionDigits="2" value="${requestForm.serviceRequest.tax}"/> (${requestForm.serviceRequest.currency})
                  		</span></li>
                  		</c:if>
                  		 <c:if test="${requestForm.serviceRequest.totalAmount ne '' && requestForm.serviceRequest.totalAmount ne '0' && requestForm.serviceRequest.totalAmount ne null}">
                	  <li>
                  		<label><spring:message code="Details.changeRequestDetails.label.total"/> </label>
                  		<span id = "totalAmount" class="currencyRight"><fmt:formatNumber type="number" maxFractionDigits="2" minFractionDigits="2" value="${requestForm.serviceRequest.totalAmount}"/> (${requestForm.serviceRequest.currency})
                  		</span>
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
	    				<div class="pagination"><span id="div_statusPagingArea"></span><span id="div_statusinfoArea"></span></div>
					</div>
				</div>
              </div>
            </div>
          </div>
        </div>
        <!-- Request Status History - End -->
        <c:if test="${requestForm.serviceRequest.asset.serialNumber != null &&  requestForm.serviceRequest.asset.serialNumber != ''}">
        <p class="inlineTitle"><spring:message code="requestInfo.heading.selectedAsset"/></p>
        <!-- ASSET INFO BLOCK START -->
        <div class="portletBlock infoBox rounded shadow" id="selectedAsset"> 
          <!-- Outter table: 1 row & 2 colums -->
          <table width="100%" border="0" cellspacing="0" cellpadding="0" class="assetDetail">
            <tr> 
              <!-- Outter Table > column 1: Product image & Name -->
              <td class="pModel"><ul>
                  <li class="center">
                  <img src="${requestForm.serviceRequest.asset.productImageURL}" id="MyPicture" onError="image_error();" width="100" height="100">
                  </li>
                   <!--Changed for CI Defect # 10118 -->
                  <li class="pModelName text-align-center"><%-- ${requestForm.serviceRequest.asset.modelNumber } --%>
                  <span id="imageNo">${requestForm.serviceRequest.descriptionLocalLang}</span>
				  </li>
                </ul></td>
              <!-- Outter Table > Column 2: Inner table for details -->
              <td class="pDetail"><!-- Inner table: 4 row2 & 2 colums -->
                
                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                  <tr>
                    <td class="h4"><spring:message code="requestInfo.heading.assetIdentifiers"/></td>
                    <td class="h4 separatorV"><spring:message code="requestInfo.heading.serviceAddress"/></td>
                  </tr>
                  <tr>
                    <td class="infoDsiplay"><ul class="form">
                        <li>
                          <label><spring:message code="requestInfo.assetInfo.label.serialNumber"/></label>
                          <span id="serialNo"> ${requestForm.serviceRequest.asset.serialNumber }</span></li>
                        <li>
                          <label><spring:message code="requestInfo.assetInfo.label.productName"/></label>
                          <span id="productName">${requestForm.serviceRequest.asset.productTLI } </span></li>
                        <li>
                          <label><spring:message code="requestInfo.assetInfo.label.installDate"/> </label>
                          <span id="installDate"><util:dateFormat value="${requestForm.serviceRequest.asset.installDate }"></util:dateFormat></span></li>
                        <li>
                          <label><spring:message code="requestInfo.assetInfo.label.ipAddress"/></label>
                          <span id="ipAddress">
                          
                          <a href="javascript:gotoControlPanel('${requestForm.serviceRequest.asset.controlPanelURL}')">${requestForm.serviceRequest.asset.ipAddress}</a>
                          </span></li>
                        <li>
                          <label><spring:message code="requestInfo.assetInfo.label.hostName"/></label>
                          <span id="hostName"> ${requestForm.serviceRequest.asset.hostName} </span></li>
                        <li>
                          <label><spring:message code="requestInfo.assetInfo.label.customerAssetTag"/></label>
                          <span id="assetTag">${requestForm.serviceRequest.asset.assetTag}</span></li>
                      </ul></td>
                    <td class="separatorV" id="serviceAddressID"><ul class="roDisplay">
                       <li >
                       <util:addressOutput value="${requestForm.serviceRequest.asset.installAddress}"></util:addressOutput>
                       <%--
                  <div id="servAddr1">${requestForm.serviceRequest.asset.installAddress.addressLine1}</div>
                  
                  <div id="servAddrOffice">${requestForm.serviceRequest.asset.installAddress.officeNumber}</div>
                  <c:if test="${requestForm.serviceRequest.asset.installAddress.addressLine2 != '' && requestForm.serviceRequest.asset.installAddress.addressLine2 != null}">                 
                  <div id="servAddrLine2">${requestForm.serviceRequest.asset.installAddress.addressLine2},</div>
                  </c:if> 
                 
                  <span id="servAddr">${requestForm.serviceRequest.asset.installAddress.city},
                  <c:if test="${requestForm.serviceRequest.asset.installAddress.county != '' && requestForm.serviceRequest.asset.installAddress.county!=null}">
                  ${requestForm.serviceRequest.asset.installAddress.county},
                  </c:if> 
                  <c:if test="${requestForm.serviceRequest.asset.installAddress.state != '' && requestForm.serviceRequest.asset.installAddress.state != null}">
                  ${requestForm.serviceRequest.asset.installAddress.state}
                  </c:if>
                  <c:if test="${requestForm.serviceRequest.asset.installAddress.province !='' && requestForm.serviceRequest.asset.installAddress.province != null}">
                  ,${requestForm.serviceRequest.asset.installAddress.province}
                  </c:if>
                  <c:if test="${requestForm.serviceRequest.asset.installAddress.district !='' && requestForm.serviceRequest.asset.installAddress.district != null}">
                  ,${requestForm.serviceRequest.asset.installAddress.district}
                  </c:if>
                  </span>                 
                  <div id="servAddrPostal">${requestForm.serviceRequest.asset.installAddress.postalCode}</div>
                  <div id="servAddrCountry">${requestForm.serviceRequest.asset.installAddress.country}</div>
                  --%></li>
                        <li id="building"> <label> <spring:message code="requestInfo.addressInfo.label.building"/></label>
                        ${requestForm.serviceRequest.asset.installAddress.physicalLocation1}</li>
                        <li id="floor"> <label><spring:message code="requestInfo.addressInfo.label.floor"/></label>
                        ${requestForm.serviceRequest.asset.installAddress.physicalLocation2} </li>
                        <li id="office"> <label><spring:message code="requestInfo.addressInfo.label.office"/></label>
                        ${requestForm.serviceRequest.asset.installAddress.physicalLocation3}</li>
                      </ul></td>
                  </tr>
                </table></td>
            </tr>
          </table>
        </div>
        </c:if>
       </div>
        <!-- ASSET INFO BLOCK END -->
        <div id="pageCounts">
        <jsp:include page="/WEB-INF/jsp/orderSuppliesAssetOrder/pageCountsForAsset.jsp" />
       </div>
         <div id="printSuppliesBottom">
        <!-- Associated Requests - Start -->
        <div class="portletBlock infoBox rounded shadow visibleContainer">
          <div class="columnsOne">
            <div class="columnInner">
              <h4 class="expand"><spring:message code="Details.supplyRequestDetails.label.associatedRequests"/></h4>
              <div class="collapse" id="associatedExpand"> 
                <!-- GRID 1 BEGIN -->
                <div id="associatedGridID">
                <div class="portlet-wrap rounded">
                  <div class="portlet-wrap-inner">
                    <div id="mygrid_container2" class="gridbox gridbox_light "></div>
                    <div id="loadingNotificationAssociatedRequests" class="gridLoading">
										<!--spring:message code="requestInfo.popup.loading"/--><img src="<html:imagesPath/>gridloading.gif" />
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
        </div>
        <!-- Associated Requests - End --> 
        <!-- Past Requests - Start -->
        <c:if test="${requestForm.serviceRequest.asset.serialNumber != null &&  requestForm.serviceRequest.asset.serialNumber != ''}">
        <div class="portletBlock infoBox rounded shadow visibleContainer">
          <div class="columnsOne">
            <div class="columnInner">
               <h4 class="expand" onclick="show_load();"> <!--show load called for asynshronous-CI7 -->
              <spring:message code="Details.manageAsset.heading.requestHistoryOnThisAsset"/></h4>
              <div  class="collapse" id ="pastReqExpand">
                       <div class="expand-min">
                       <a href="#grid" id='headerMenuButton' style="display: none;"><spring:message code="requestInfo.link.customizeColumns"/></a> </div> <!--DONT REMOVE THIS CI BRD13-10-02--%>
                       
<!--                     Changes for Defect 7490 MPS 2.1 -->
                <div id="gridContainerDiv_all_request_types"  class="gridbox gridbox_light" style="display: none;"></div>
                
				    		<div id="loadingNotification_request_type" class='gridLoading'>
	        					<!--spring:message code='loadingNotification' />&nbsp;&nbsp;--><img src="<html:imagesPath/>gridloading.gif"/>
	    					</div>
				    		<div class="pagination" id="paginationId">
				    		<span id="pagingArea_request_type"></span><span id="infoArea_req_status"></span> 
				    		</div>
<!-- 				   ENDS Changes for Defect 7490 MPS 2.1 -->
					
              </div>
            </div>
          </div>
        </div>
        </c:if>
        <!-- Past Requests - End -->
        <hr class="separator" />
        <div class="portletBlock">
          <div class="columnsTwo">
            <div class="infoBox columnInner rounded shadow">
              <h4><spring:message code="requestInfo.heading.primaryContactForThisRequest"/></h4>
              <ul class="form wordBreak">
                <li>
                  <label><spring:message code="requestInfo.label.name"/></label>
                  <span id="primaryName">${fn:trim(requestForm.serviceRequest.primaryContact.firstName)}&nbsp;${fn:trim(requestForm.serviceRequest.primaryContact.lastName)}</span></li>
                <li>
                  <label><spring:message code="requestInfo.label.phoneNumber"/></label>
                  <span id="primaryPhone">${requestForm.serviceRequest.primaryContact.workPhone }</span></li>
                <li>
                  <label><spring:message code="requestInfo.label.emailAddress"/></label>
                  <span id="primaryEmail">${requestForm.serviceRequest.primaryContact.emailAddress }</span></li>
              </ul>
            </div>
            <c:if test="${requestForm.serviceRequest.secondaryContact.firstName != ''}">
            <div class="infoBox columnInner rounded shadow" id="secondaryContact">
              <h4><spring:message code="requestInfo.heading.secondaryContactForThisRequest"/></h4>
              <ul class="form wordBreak">
                <li>
                  <label><spring:message code="requestInfo.label.name"/></label>
                  <span id="secondaryName">${fn:trim(requestForm.serviceRequest.secondaryContact.firstName)}&nbsp;${fn:trim(requestForm.serviceRequest.secondaryContact.lastName)}</span></li>
                <li>
                  <label><spring:message code="requestInfo.label.phoneNumber"/></label>
                  <span id="secondaryPhone">${requestForm.serviceRequest.secondaryContact.workPhone }</span></li>
                <li>
                  <label><spring:message code="requestInfo.label.emailAddress"/></label>
                  <span id="secondaryEmail">${requestForm.serviceRequest.secondaryContact.emailAddress }</span></li>
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
                  <span id="custRefNo">${requestForm.serviceRequest.customerReferenceNumber }</span></li>
                <li>
                  <label><spring:message code="requestInfo.label.costCentre"/></label>
                  <span id="costCenter">${requestForm.serviceRequest.costCenter }</span></li>
                <li>
                  <label><spring:message code="requestInfo.label.description"/></label>
                  <span class="multiLine" id="description">${requestForm.serviceRequest.addtnlDescription}</span></li>
              </ul>
            </div>
          </div>
        </div>
        <br/>
        <!-- PENDING SHIPMENT BLOCK - START -->
       
      	<c:if test="${requestForm.pendingRequest.shipmentXML != null }">
      	<div class="portletBlock infoBox rounded shadow" style="clear:both">
  	      	<div class="columnsOne">
   	        	<div class="columnInner">
          		  <h4 ><spring:message code="Details.supplyRequestDetails.heading.pendingshipment"/></h4>
            			
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
					</c:choose>
               </h4>
              <div class="icon"><img class="ui_icon_sprite widgets widgets-truck-rt-icon" src="<html:imagesPath/>transparent.png" width="100" height="100" /></div>
              <div class="columnInner separatorV">
               
                <table class="shipment wFull">
                  <tr>
                    <td class="w70p"><div class="status">
<!--                         <div id="div_technicianProgressBar">PROGRESS BAR WILL COME HERE</div> -->
                        <div id="div_shipmentProgressBar${status.count}" class="status-bar"></div><!-- status-bar -->
                        <table border="0" cellspacing="0" cellpadding="0">
                          <tr>
                            <td><spring:message code="serviceRequest.label.inProcess"/></td>
                            <td><spring:message code="serviceRequest.label.shipped"/></td>
                            <td><spring:message code="serviceRequest.label.delivered"/></td>
                          </tr>
                        </table>
                      </div></td>
                    <td>
 						<ul class="form wFull">
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
			if(${shipmentForm.shipmentProgress==0}){
				jQuery('#div_shipmentProgressBar${status.count}').progressBar(100, { width:480,speed:25,increment:1,height:12,showText: false, boxImage: '<html:imagesPath/>progressBar/progressbar.gif', barImage:barImagesCancel} );
			}else{
				jQuery('#div_shipmentProgressBar${status.count}').progressBar(${shipmentForm.shipmentProgress}, { width:480,speed:25,increment:1,height:12,showText: false, boxImage: '<html:imagesPath/>progressBar/progressbar.gif', barImage:barImages} );
			}
			SRShipmentGrid${status.count} = new dhtmlXGridObject('div_shipmentGrid_container${status.count}');
	 		SRShipmentGrid${status.count}.setImagePath("<html:imagesPath/>gridImgs/");
	 		SRShipmentGrid${status.count}.setHeader("<spring:message code='serviceRequest.listHeader.shipment'/>");
	 		SRShipmentGrid${status.count}.setColAlign("right,left,left,left,left,left,right");
	 		SRShipmentGrid${status.count}.setColTypes("sub_row,ro,ro,ro,ro,ro,ro");
	 		//SRShipmentGrid${status.count}.setColSorting("str,str,str,str,str");
	 		SRShipmentGrid${status.count}.setInitWidths("30,150,200,150,140,100,95");
	 		SRShipmentGrid${status.count}.enableAutoWidth(true);
	 		SRShipmentGrid${status.count}.enableMultiline(true);
	 		SRShipmentGrid${status.count}.enableAutoHeight(true);
	 		SRShipmentGrid${status.count}.init(); 
	 		SRShipmentGrid${status.count}.prftInit();
	 		SRShipmentGrid${status.count}.enablePaging(true, 5, 10, "div_shipmentPagingArea${status.count}", true);
	 		SRShipmentGrid${status.count}.setPagingSkin("bricks");
	 		SRShipmentGrid${status.count}.setSkin("light");
	 		<%-- CHanges for MPS 2.1--%>
	 		var priceAvailableFlag${status.count}=false;
	 		<c:if test='${requestForm.serviceRequest.billingModel eq "Ship and Bill"}'>
	 			 priceAvailableFlag${status.count}=true;
	 			SRShipmentGrid${status.count}.attachEvent("onRowCreated",function(rowId){
		 			var cellObj${status.count} = SRShipmentGrid${status.count}.cellById(rowId,6);
	 				var cellvalue${status.count}=cellObj${status.count}.getValue();
	 				if(cellvalue${status.count}==0){
	 	 				cellObj${status.count}.setValue("<spring:message code='requestInfo.error.priceUnavailable'/>");
	 				}
	 				if(cellvalue${status.count}!=0){
	 					cellObj.setValue(cellvalue${status.count} + " "+"("+'${requestForm.serviceRequest.currency}'+")");
	 				}
		 			});
	 		</c:if>
	 		<c:if test='${requestForm.serviceRequest.billingModel eq "Consumable Purchase"}'>
	 				SRShipmentGrid${status.count}.attachEvent("onRowCreated",function(rowId){
		 				var cellObj${status.count} = SRShipmentGrid${status.count}.cellById(rowId,6);
		 				var cellvalue${status.count}=cellObj${status.count}.getValue();
		 				if(cellvalue${status.count}!="" && cellvalue${status.count}!=0)
		 					priceAvailableFlag${status.count}=true;
	 			});
	 		</c:if>
	 		
	 		<%-- CHanges for MPS 2.1 ENDS--%>
	 		
	 		SRShipmentGrid${status.count}.loadXMLString('${shipmentForm.shipmentXML}');
	 		
	 		<%-- CHanges for MPS 2.1--%>
	 		
	 		if(priceAvailableFlag${status.count}==false){
	 			SRShipmentGrid${status.count}.setColumnHidden(6,true);
	 		}
	 		<%-- CHanges for MPS 2.1 ENDS--%>
	 		//SRShipmentGrid${status.count}.sortRows(0,"str","asc");  
	 		//SRShipmentGrid${status.count}.setSortImgState(true, 0, 'asc');	
			</script>
        </c:forEach>
        <!-- SHIPMENT BLOCK - END --> 
        
        
        <!-- ACTIVITY BLOCK - START -->
        <c:if test="${requestForm.serviceRequest.serviceActivityStatus!= null}">
        <div class="portletBlock infoBox rounded shadow">
          <div class="columnsOne">
            <div class="columnInner">
              <h4 class="expand" id="sr_technician_h4"><spring:message code="Details.supplyRequestDetails.heading.activity"/></h4>
              <div class="collapse" id="sr_technician_div">
              <div class="icon"><img class="ui_icon_sprite widgets technician-large-icon" src="<html:imagesPath/>transparent.png" width="100" height="100" /></div>
              <div class="columnInner separatorV">
               
                <table class="shipment wFull">
                  <tr>
                    <td class="w70p"><div class="status">
<!--                         <div id="div_technicianProgressBar">PROGRESS BAR WILL COME HERE</div> -->
                        <div id="div_technicianProgressBar1" class="status-bar"></div><!-- status-bar -->
                        
                        <c:if test="${requestForm.serviceRequestTechnicianProgress gt 0}">
	                        <table border="0" cellspacing="0" cellpadding="0">
	                          <tr>
	                            <td><spring:message code="serviceRequest.label.inProcess"/></td>
	                            <td><spring:message code="serviceRequest.label.assigned"/></td>
	                            <td><spring:message code="reportStatus.PUBLISHOK"/></td>
	                          </tr>
	                        </table>
                        </c:if>
                        <c:if test="${requestForm.serviceRequestTechnicianProgress==0}">
	                        <table border="0" cellspacing="0" cellpadding="0">
	                          <tr>
	                            <td colspan="3"><spring:message code="serviceRequest.label.cancelled" /></td>
	                            
	                          </tr>
	                        </table>
                        </c:if>
                      </div></td>
                    <td>&nbsp;</td>
                  </tr>
                </table>
                <c:if test="${requestForm.serviceRequestTechnicianXML!= null}">
                <div id="div_technicianPrintTable">
							<div id="tab_technicianGrid" style="display:block;">
								<div id="div_technicianContainer" class="div-style48"></div>
    							<div id="div_technicianPagingArea"></div>
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
        <c:if test="${requestForm.cancelledPartsXML != null}">
        <div class="portletBlock infoBox rounded shadow" id = "cancelBlockID">
          <div class="columnsOne">
            <div class="columnInner">
              <h4><spring:message code="Details.supplyRequestDetails.heading.cancelled"/></h4>
<!--               <div class="collapse"> -->
              <div class="icon"><img src="<html:imagesPath/>cancelled.jpg" width="100" height="100" /></div>
              <div class="columnInner separatorV">
               
                <table class="shipment wFull">
                  <tr>
                    <td class="w70p"><div class="status">
<!--                         <div id="div_technicianProgressBar">PROGRESS BAR WILL COME HERE</div> -->
                        <div id="div_cancelProgressBar" class="status-bar"></div><!-- status-bar -->
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
                <div id="div_cancelPrintTable">
							<div id="tab_cancelGrid" style="display:block;">
								<div id="div_cancelContainer" class="div-style48"></div>
    							<div id="div_cancelPagingArea"></div>
    						</div>
    				</div>
              </div>
<!--               </div> -->
            </div>
          </div>
        </div>
        </c:if>
        <!-- CANCEL BLOCK - END -->
        
        <p class="inlineTitle"><spring:message code="requestInfo.heading.shippingAndBillingInformation"/></p>
        <div class="portletBlock">
          <div class="columnsTwo">
            <div class="infoBox columnInner rounded shadow">
              <h4><spring:message code="requestInfo.heading.shipToAddress"/></h4>
              <ul class="roDisplay">
              <c:choose>
              	<c:when test="${showUserMsg == true}">
              		<div><b><spring:message code="requestInfo.label.shipToDefaultNote"></spring:message></b></div>
                </c:when>    
                <c:otherwise>    
                <li id="shipToAddr"><div>${requestForm.serviceRequest.serviceAddress.storeFrontName}</div>
                <util:addressOutput value="${requestForm.serviceRequest.serviceAddress}"></util:addressOutput>
                  <%--<div>${requestForm.serviceRequest.serviceAddress.addressLine1}</div>
                   changes start for MPS 2.1 defect no 8387 
                  <div>${requestForm.serviceRequest.serviceAddress.officeNumber}</div>
                  <c:if test="${requestForm.serviceRequest.serviceAddress.addressLine2 != '' && requestForm.serviceRequest.serviceAddress.addressLine2 != null}">                 
                  <div>${requestForm.serviceRequest.serviceAddress.addressLine2},</div>
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
                  ,${requestForm.serviceRequest.serviceAddress.province}
                  </c:if>
                  <c:if test="${requestForm.serviceRequest.serviceAddress.district !='' && requestForm.serviceRequest.serviceAddress.district != null}">
                  , ${requestForm.serviceRequest.serviceAddress.district}
                  </c:if>
                  </span>                 
                  <div>${requestForm.serviceRequest.serviceAddress.postalCode}</div>
                  <div>${requestForm.serviceRequest.serviceAddress.country}</div>--%>
                  <%-- changes start for MPS 2.1 defect no 8387 --%></li>
                <li><span id="physicalLocation1"><spring:message code="requestInfo.addressInfo.label.building"/>  ${requestForm.serviceRequest.serviceAddress.physicalLocation1} </span></li>
                <li><span id="physicalLocation2"><spring:message code="requestInfo.addressInfo.label.floor"/>  ${requestForm.serviceRequest.serviceAddress.physicalLocation2} </span></li>
                <li><span id="physicalLocation3"><spring:message code="requestInfo.addressInfo.label.office"/>  ${requestForm.serviceRequest.serviceAddress.physicalLocation3} </span></li>
                           	
              	</c:otherwise>
              </c:choose>
              </ul>
            </div>
          </div>
          <div class="columnsTwo">
            <div class="infoBox columnInner rounded shadow">
              <h4><spring:message code="requestInfo.heading.orderInstructions"/></h4>
              <ul class="form wordBreak">
                <li>
                  <label for="desc"><spring:message code="requestInfo.label.specialInstructions"/></label>
                  <span class="multiLine" id="specialInstructions"> ${requestForm.serviceRequest.specialInstructions} </span></li>
                <!--<li>
                  <label for="date1"><spring:message code="requestInfo.label.defaultSpecialInstructions"/></label>
                  <span id="defaultSpecialInstructions"> ${requestForm.serviceRequest.defaultSpecialInstructions}  </span></li>-->
                <li>
                  <label for="expOrder"><spring:message code="requestInfo.label.requestExpediteOrder"/></label>
                  <span id="expediteOrder"> 
	                  <c:if test="${requestForm.serviceRequest.expediteOrder == false}"><spring:message code="requestInfo.option.no"/></c:if>
	                  <c:if test="${requestForm.serviceRequest.expediteOrder == true}"><spring:message code="requestInfo.option.yes"/></c:if>
                  </span>
                  </li>
                <li>
                  <label for="date1"><spring:message code="requestInfo.label.requestedDeliveryDate"/></label>
                  <span id="effectiveDate"><util:dateFormat value="${requestForm.serviceRequest.requestedEffectiveDate}" ></util:dateFormat></span></li>
              </ul>
            </div>
          </div>
        </div>
        <c:if test="${(requestForm.serviceRequest.poNumber ne '') && (requestForm.serviceRequest.creditCardToken ne '' || requestForm.serviceRequest.creditCardToken ne null)}">
        <div class="portletBlock" id="paymentBlock" style="clear:both">
          <div class="columnsOne">
            <div class="infoBox columnInner rounded shadow">
              <h4><spring:message code="orderSupplies.label.paymentDetails"/></h4>
              <ul class="form">
                <c:if test="${fn:trim(requestForm.serviceRequest.poNumber) != '' }">
                <li id="poNumber">
                  <label for="poNo"><spring:message code="Details.supplyRequestDetails.label.poNumber"/></label>
                  <span> ${requestForm.serviceRequest.poNumber } </span></li>               
               </c:if>
               <c:if test="${fn:trim(requestForm.serviceRequest.creditCardToken) != '' }">
			   <li id="creditCardToken">
			      <label for="cct"><spring:message code="requestInfo.label.creditCardNo"/></label>
			      <span>${requestForm.serviceRequest.creditCardToken} </span></li>
		       </c:if>
                
              </ul>
            </div>
          </div>
        </div>
        </c:if>
        <p class="inlineTitle"><spring:message code="requestInfo.heading.notes"/> &amp; <spring:message code="requestInfo.heading.attachments"/></p>
        <div class="portletBlock">
          <div class="columnsOne">
            <div class="infoBox columnInner rounded shadow">
              <h4><spring:message code="requestInfo.heading.notes"/></h4>
              <ul class="roDisplay wordBreak">
              <li><span id="notes">${requestForm.serviceRequest.notes}</span></li>
            </ul>
            </div>
          </div>
        </div>
        <div class="portletBlock">
          <div class="infoBox wHalf columnInner rounded shadow">
            <h4><spring:message code="requestInfo.heading.attachments"/></h4>
            <table id="showAttachment">
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
        
     
      <!-- MAIN CONTENT END --> 

</div>
<div class="buttonContainer">
          <button id="backButton" class="button_cancel" type="button" onclick="backToHistory();"><spring:message code="button.back"/></button>
          <c:if test="${requestForm.serviceRequestShipments == null && requestForm.serviceRequest.serviceRequestStatus != 'Completed'}">
           
	          <c:if test="${sessionScope.isStandardUser eq false}">
	         <button id="cancelSupplyButton" class="button_cancel" type="button" onclick="cancelRequest();"><spring:message code="Details.changeRequestDetails.button.cancelReq"/></button>
	          </c:if>
		  	
		  <%
		  if(createServiceReq!=null && (createServiceReq.equalsIgnoreCase("True") || createServiceReq.equalsIgnoreCase("false")) && createChangeMgmtReq!=null && createChangeMgmtReq.equalsIgnoreCase("True")){
		 %>
		  <button id="updateButton" class="button" onclick="updateRequest();"><spring:message code="Details.changeRequestDetails.button.updateReq"/></button>
		  <%}%> 
		  </c:if>
<!--           <button class="button"><spring:message code="Details.changeRequestDetails.button.updateReq"/></button> -->
<!--           <button class="button"><spring:message code="Details.changeRequestDetails.button.cancelReq"/></button> -->
        </div>
        <jsp:include page="/WEB-INF/jsp/common/dynamicGridInitialize.jsp"></jsp:include> <%--Added for CI-7 Defect #8217--%>
</div>
<div id="serviceRequestDetailDiv"></div>
<script type="text/javascript">
//Added for CI BRD13-10-02--START
var assetRowId1="${requestForm.serviceRequest.asset.assetId}";
var accountId='${requestForm.serviceRequest.asset.account.accountId}';
var linkClicked=1;
var timezoneOffsetServiceRequest="";
//Added for CI BRD13-10-02--END
if (window.PIE) {
    document.getElementById("backButton").fireEvent("onmove");
    
    <c:if test="${requestForm.serviceRequestShipments == null && requestForm.serviceRequest.serviceRequestStatus != 'Completed'}">
   if(document.getElementById("updateButton") != null){
    document.getElementById("updateButton").fireEvent("onmove");
   }
    document.getElementById("cancelSupplyButton").fireEvent("onmove");
    
    </c:if>
}
	/*These are for dropdown filters*/
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
		
	/* Drop down filters ends*/
	dhx_globalImgPath = "<html:imagesPath/>comboImgs/";
	jQuery(function() {
	    jQuery("h4.expand").toggler();
	});
	jQuery(document).ready(function(){
	jQuery('.ev_light img').click();
jQuery('.ev_light img').click();
		loadAssociatedRequestsGrid();
		var currentURL = window.location.href;
		if(currentURL.indexOf('/partner-portal') != -1)
		{	
			jQuery('#cancelSupplyButton').hide();
			jQuery('#topnavigation li a').each(function(){
				  if(jQuery(this).attr('href')=="/group/partner-portal/customer-requests")
				  jQuery(this).parent().addClass('selected');
				  });
		}
		//commented for Request History on this Asset asynchronous requirement --CI7
// 		<c:if test="${requestForm.serviceRequest.asset.serialNumber != null &&  requestForm.serviceRequest.asset.serialNumber != ''}">
// 		</c:if>
		textScroll(document.getElementById('description'));
		textScroll(document.getElementById('specialInstructions'));
		//textScroll(document.getElementById('defaultSpecialInstructions'));
		textScroll(document.getElementById('notes'));
		textScroll(document.getElementById('costCenter'));
		jQuery('#pageCountInfo').hide();
		jQuery('#sr_technician_div').show();
	
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
	SRStatusGrid.loadXMLString('${requestForm.srStausXML}'); 
  	//SRStatusGrid.setSortImgState(true, 0, 'asc');
	
	///////////////////////////////////////////////existing grids////////////
	//var mygrid;
	//var headerString = "Request No.,Date/Time Created,Request Type,Area,Status,&nbsp;";
	//Added for CI -7 Defect #8217 --STARTS
	var counter=0; //counter added for making Request History asynchronous
	//Added for CI BRD13-10-02--START
	function show_load(){
			counter++;
			if(!(counter%2)==0)
				{
			initialiseGrid();
			requestListGrid.detachHeader(1);
			jQuery("#gridContainerDiv_all_request_types").css('height','25px');
			jQuery("#gridContainerDiv_all_request_types").show();
				}
		}
	
	// following variables are declared in dynamicGridInitialize
	// Changes for Defect 7490 MPS 2.1
	pagingArea="pagingArea_request_type";infoArea="infoArea_req_status";headerMenuButton="headerMenuButton";loadingNotification="loadingNotification_request_type";backFilterValues="";
	gridCreationId="gridContainerDiv_all_request_types";
	// ENDS Changes for Defect 7490 MPS 2.1
	pageSize=5,pagesIngrp=10;
	JSON_Param["<%=gridConfigurationValues[0]%>"]="<spring:message code='suppliesmanagement.details.srhistorygrid'/>";
	JSON_Param["<%=gridConfigurationValues[1]%>"]=",,,,";
	JSON_Param["<%=gridConfigurationValues[2]%>"]="left,left,left,left,left";
	JSON_Param["<%=gridConfigurationValues[3]%>"]="130,160,120,120,*";
	JSON_Param["<%=gridConfigurationValues[4]%>"]="ro,ro,ro,ro,ro";
	JSON_Param["<%=gridConfigurationValues[5]%>"]="str,str,str,str,str";
	JSON_Param["<%=gridConfigurationValues[6]%>"]="0,1,2,3,4"; //Changed for CI Defect #8217
	JSON_Param["<%=gridConfigurationValues[7]%>"]="0,des";
	JSON_Param["<%=gridConfigurationValues[8]%>"]=""; //Changed for CI Defect #8217
	JSON_Param["<%=JSON_COMBO_FILTER%>1"]="";
	JSON_Param["<%=JSON_RESOURCE_URL%>"]="<portlet:resourceURL id='retrieveHistoryList'/>";
	JSON_Param["<%=gridSavingParams[0]%>"]="";
	JSON_Param["<%=gridSavingParams[1]%>"]="";
	JSON_Param["<%=gridSavingParams[2]%>"]="";
	JSON_Param["<%=gridSavingParams[3]%>"]="";
	
	reloadGrid=function(){
		clearMessage();
		refreshGridSettingOnPage(requestListGrid);
		xmlURLQueryParams.setLoadXMLUrl();
		requestListGrid.clearAndLoad(xmlURL);
		
	};


	initURLParams = function(){
		xmlURLQueryParams={
				urlParameters:["direction","orderBy","timezoneOffset","accountRowId","srHistoryType","assetRowId"],
				setParameters : function(){
										this["direction"]=requestListGrid.a_direction;
										this["orderBy"]=requestListGrid.getSortColByOffset();
										this["timezoneOffset"]=timezoneOffset;
										this["accountRowId"]=accountId;
										this["srHistoryType"]="ALL_REQUESTS";
										this["assetRowId"]=assetRowId1;
										
										},
				setLoadXMLUrl : function(){
												xmlURL=new String(JSON_Param["<%=JSON_RESOURCE_URL%>"]);
												this.setParameters();
												for(i=0;i<this.urlParameters.length;i++){
													if(this[this.urlParameters[i]]!=null)
														xmlURL+="&"+this.urlParameters[i]+"="+this[this.urlParameters[i]];
														
												}	
										}
				};
		};
	
	//Added for CI -7 Defect #8217 --ENDS
	function loadpastGrid(){
		counter++;
		if(!(counter%2)==0)
		{
		var customizedGridURL="${requestForm.srHistoryListXML}";
		srHistoryListGrid = new dhtmlXGridObject('srHistoryListDiv');
		srHistoryListGrid.setImagePath("<html:imagesPath/>gridImgs/");
		srHistoryListGrid.setHeader("<spring:message code='suppliesmanagement.details.srhistorygrid'/>");
		//srHistoryListGrid.attachHeader("#text_filter,,#combo_filter,#text_filter,#combo_filter");
		srHistoryListGrid.setInitWidths("140,160,160,160,160");
		//srHistoryListGrid.setColumnMinWidth("140,160,160,160,160,1");
		srHistoryListGrid.setColAlign("left,left,left,left,left");
		srHistoryListGrid.setColTypes("ro,ro,co,ro,co");
		srHistoryListGrid.setColSorting("na,str,na,na,na");
		srHistoryListGrid.enableAutoHeight(true);
		srHistoryListGrid.enableMultiline(true);
		srHistoryListGrid.enableColumnMove(true);

		srHistoryListGrid.a_direction = "asc";
		srHistoryListGrid.init();
		srHistoryListGrid.prftInit();
		srHistoryListGrid.sortIndex = 1;
		srHistoryListGrid.setSkin("light");
		srHistoryListGrid.filterValues = ",,,,";
		//srHistoryListGrid.enableAlterCss("even_row","odd_row");
		srHistoryListGrid.enablePaging(true,5, 5, "pagingArea", true, "infoArea");
		//srHistoryListGrid.enablePaging(true, 3, 2, "pagingArea", true);
		srHistoryListGrid.setPagingSkin("bricks");
		
		srHistoryListGrid.attachEvent("onXLS", function() {
			jQuery('#loadingNotificationPastRequests').show();
			jQuery('#pagingArea').hide();
			if(srHistoryListGrid.a_direction=='asc'){
				srHistoryListGrid.setSortImgState(true, srHistoryListGrid.getDefaltSortImagIndex(srHistoryListGrid.sortIndex,0), 'asc');
	        }else{
	        	srHistoryListGrid.setSortImgState(true, srHistoryListGrid.getDefaltSortImagIndex(srHistoryListGrid.sortIndex,0), 'des');
	        }
			});
		srHistoryListGrid.attachEvent("onXLE", function() {
			jQuery('#loadingNotificationPastRequests').hide();
			jQuery('#pagingArea').show();
			setTimeout(function(){
	    		rebrandPagination();
	    	
	    	},100);
			});
		srHistoryListGrid.attachEvent("onBeforeSorting",function(ind){
			var a_state = srHistoryListGrid.getSortingState();
			if(a_state[0] == (ind)){
				srHistoryListGrid.a_direction = ((a_state[1] == "asc") ? "des":"asc" );
			}else {
				srHistoryListGrid.a_direction = "asc" ;
				srHistoryListGrid.columnChanged = true;
			}
			srHistoryListGrid.sortIndex = ind;
			customizedGridURL = updateGridURL(customizedGridURL, srHistoryListGrid.getSortColByOffset(), 
					srHistoryListGrid.a_direction, srHistoryListGrid.filterValues);
			srHistoryListGrid.clearAndLoad(customizedGridURL);
			return true;
		});

		srHistoryListGrid.attachEvent("onFilterStart", function(indexes,values){
	    	srHistoryListGrid.filterValues = values+",";
	    	customizedGridURL = updateGridURL(customizedGridURL, srHistoryListGrid.getSortColByOffset(),
	    			srHistoryListGrid.a_direction, srHistoryListGrid.filterValues);
	    	setGridFilerTimer(function(){
	    		srHistoryListGrid.clearAndLoad(customizedGridURL);
		    	});
	    });
	
		srHistoryListGrid.setCustomizeCombo(requestTypeList,2);
		srHistoryListGrid.setCustomizeCombo(requestStatusList,4);
		customizedGridURL=updateGridURL(customizedGridURL, srHistoryListGrid.getSortColByOffset(),
				srHistoryListGrid.a_direction, srHistoryListGrid.filterValues);
		srHistoryListGrid.loadXML(customizedGridURL);
	 } // end of counter check If
	};
	
	function loadAssociatedRequestsGrid(){
		var associatedRequestHistoryGrid;
		var customizedGridURL="${requestForm.associatedRequestHistoryListXML}";		
		associatedRequestHistoryGrid = new dhtmlXGridObject('mygrid_container2');
		associatedRequestHistoryGrid.setImagePath("<html:imagesPath/>gridImgs/");
		associatedRequestHistoryGrid.setHeader("<spring:message code='suppliesmanagement.details.srhistorygrid'/>");
		//associatedRequestHistoryGrid.attachHeader("#text_filter,,#combo_filter,#text_filter,#combo_filter");
		associatedRequestHistoryGrid.setInitWidths("140,160,160,160,160");
		
		associatedRequestHistoryGrid.setColAlign("left,left,left,left,left");
		associatedRequestHistoryGrid.setColTypes("ro,ro,ro,ro,ro");
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
		associatedRequestHistoryGrid.enablePaging(true,5, 5, "pagingArea2", true, "infoArea2");
		associatedRequestHistoryGrid.setPagingSkin("bricks");
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
			jQuery('#pagingArea2').show();
			setTimeout(function(){
	    		rebrandPagination();
	    	
	    	},100);
			var rowNum=associatedRequestHistoryGrid.getRowsNum();
			if(rowNum !=0){
			jQuery('#associatedGridID').show();
			}
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
		<%--
		associatedRequestHistoryGrid.attachEvent("onFilterStart", function(indexes,values){
	    	associatedRequestHistoryGrid.filterValues = values+",";
	    	customizedGridURL = updateGridURL(customizedGridURL, associatedRequestHistoryGrid.getSortColByOffset(),
	    			associatedRequestHistoryGrid.a_direction, associatedRequestHistoryGrid.filterValues);
	    	setGridFilerTimer(function(){
	    		associatedRequestHistoryGrid.clearAndLoad(customizedGridURL);
		    	});
	    });
		
		/*srHistoryListGrid.attachEvent("onBeforeSorting", function(ind){
			var a_state = srHistoryListGrid.getSortingState();
			if(a_state[0] == (ind)){
				srHistoryListGrid.a_direction = ((a_state[1] == "asc") ? "des":"asc" );
			}else {
				srHistoryListGrid.a_direction = "asc" ;
				srHistoryListGrid.columnChanged = true;
			}
			srHistoryListGrid.sortIndex = ind;
			customizedGridURL = updateGridURL(customizedGridURL, srHistoryListGrid.getSortColByOffset(), srHistoryListGrid.a_direction, srHistoryListGrid.filterValues);
			jQuery.fn.reloadGrid();
			
			return true;
			
			});*/

			associatedRequestHistoryGrid.setCustomizeCombo(requestTypeList,2);
			associatedRequestHistoryGrid.setCustomizeCombo(requestStatusList,4);
		--%>
		
		customizedGridURL=updateGridURL(customizedGridURL, associatedRequestHistoryGrid.getSortColByOffset(),
				associatedRequestHistoryGrid.a_direction, associatedRequestHistoryGrid.filterValues);
	
	
	
	
		
		
		
		//For temp
		associatedRequestHistoryGrid.loadXML(customizedGridURL);
		//associatedRequestHistoryGrid.loadXML('${requestForm.associatedRequestHistoryListXML}');

	
	}
			
	/** 
	  *  Technician Grid Config begin
	  */
	if(${requestForm.serviceRequestTechnicianXML!= null}){
	SRTechnicianGrid = new dhtmlXGridObject('div_technicianContainer');
	SRTechnicianGrid.setImagePath("<html:imagesPath/>gridImgs/");
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
	SRTechnicianGrid.enablePaging(true, 5, 10, "div_technicianPagingArea", true);
	SRTechnicianGrid.setPagingSkin("bricks");
	SRTechnicianGrid.setSkin("light");
	SRTechnicianGrid.loadXMLString('${requestForm.serviceRequestTechnicianXML}'); 
	//SRTechnicianGrid.setSortImgState(true, 0, 'asc');
	}

	/** 
	  *  InProcess Grid config begin
	  */
	if(${requestForm.pendingRequest.shipmentXML!= null}){
	<%-- CHanges for MPS 2.1--%>
	var priceAvailableFlag=false;
	 SRInProgressGrid = new dhtmlXGridObject('div_inprocessGridContainer');
	<c:if test='${requestForm.serviceRequest.billingModel eq "Ship and Bill"}'>
	 priceAvailableFlag${status.count}=true;
	SRInProgressGrid.attachEvent("onRowCreated",function(rowId){
			var cellObj = SRInProgressGrid.cellById(rowId,5);
			var cellvalue=cellObj.getValue();
			if(cellvalue==0){
				cellObj.setValue("<spring:message code='requestInfo.error.priceUnavailable'/>");
			}
			if(cellvalue!="<spring:message code='requestInfo.error.priceUnavailable'/>"){
				cellObj.setValue(cellvalue + " "+"("+'${requestForm.serviceRequest.currency}'+")");
			}
			if(cellvalue=="<spring:message code='requestInfo.error.priceUnavailable'/>"){
				SRInProgressGrid.setColumnHidden(5,true);
				}
		});
	</c:if>
	<%-- CHanges for MPS 2.1 ENDS --%>	
	SRInProgressGrid.setImagePath("<html:imagesPath/>gridImgs/");
	SRInProgressGrid.setHeader("<spring:message code='serviceRequest.listHeader.pendingshipment'/>");
	SRInProgressGrid.setColAlign("left,left,left,left,left,right");
	SRInProgressGrid.setColTypes("ro,ro,ro,ro,ro,ro");
	//SRInProgressGrid.setColSorting("str,str,str,str,str");
	SRInProgressGrid.setInitWidths("180,250,170,110,100,120");
	SRInProgressGrid.enableAutoWidth(true);
	SRInProgressGrid.enableMultiline(true);
	SRInProgressGrid.enableAutoHeight(true);
	SRInProgressGrid.init(); 
	SRInProgressGrid.prftInit();
	SRInProgressGrid.enablePaging(true, 5, 10, "div_inprocessPagingArea", true);
	SRInProgressGrid.setPagingSkin("bricks");
	SRInProgressGrid.setSkin("light");
	//SRInProgressGrid.setColumnHidden(2,true); //defect #10511
	SRInProgressGrid.setColumnHidden(4,true);
	<%-- CHanges for MPS 2.1--%>
	<c:if test='${requestForm.serviceRequest.billingModel eq "Consumable Purchase"}'>
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
	<c:if test='${requestForm.serviceRequest.billingModel eq "Usage Based Billing"}'>
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
<%-- CHanges for MPS 2.1 june release--%>
		<c:if test='${empty requestForm.serviceRequest.billingModel}'>
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
	SRInProgressGrid.loadXMLString('${requestForm.pendingRequest.shipmentXML}'); 
	<%-- CHanges for MPS 2.1--%>
	if(priceAvailableFlag==false)
		SRInProgressGrid.setColumnHidden(5,true);
	<%-- CHanges for MPS 2.1 ENDS--%>
	
	//SRInProgressGrid.setSortImgState(true, 0, 'asc');	
	 }	
	
	/** 
	  *  Cancelled Grid config begin
	  */
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
	//SRInProgressGrid.setSortImgState(true, 0, 'asc');	
	 }
	
	/**
		****************progressBars configration start*******************
	*/
	if(${requestForm.serviceRequest.serviceActivityStatus!= null}){
		if(${requestForm.serviceRequestTechnicianProgress==0}){
			jQuery("#div_technicianProgressBar1").progressBar(100 , { width:480,speed:25,increment:1,height:12,showText: false, boxImage: '<html:imagesPath/>progressBar/progressbar.gif', barImage : barImagesCancel} );
		}else{
	  		jQuery("#div_technicianProgressBar1").progressBar(${requestForm.serviceRequestTechnicianProgress}, { width:480,speed:25,increment:1,height:12,showText: false, boxImage: '<html:imagesPath/>progressBar/progressbar.gif', barImage : barImages} );
		}
	}
	
	if(${requestForm.cancelledPartsXML!= null}){
		//if(${requestForm.serviceRequestTechnicianProgress==0}){
			jQuery("#div_cancelProgressBar").progressBar(100 , { width:480,speed:25,increment:1,height:12,showText: false, boxImage: '<html:imagesPath/>progressBar/progressbar.gif', barImage : barImagesCancel} );
		//}else{
	  	//	jQuery("#div_technicianProgressBar1").progressBar(${requestForm.serviceRequestTechnicianProgress}, { width:480,speed:25,increment:1,height:12,showText: false, boxImage: '<html:imagesPath/>progressBar/progressbar.gif', barImage : barImages} );
		//}
	}
	
	function backToHistory(){
		 callOmnitureAction('<%=LexmarkSPOmnitureConstants.SUPPLYREQUESTDETAILS%>','<%=LexmarkSPOmnitureConstants.SUPPLIESREQUESTDETAILBACK%>');
  		showOverlay();
  		var backUrl =  "<portlet:renderURL><portlet:param name='action' value='backToHistory' />	</portlet:renderURL>" ;
  		backUrl = backUrl+'&requestTypeStr=' + '${requestForm.sourcePage}';
  		var sourcepage="${requestForm.sourcePage}";
  		if(sourcepage=="DM"){
  	  			
  			var currentURL = window.location.href;
	  		if(currentURL.indexOf('GVFlag=true') != -1){
	  			
	  			window.location.href="grid-view?assetRowId=${assetRowId}&isDeviceBookmarked=${isDeviceBookmarked}&backHistory=true&lclick=${linkClicked}";		
	  		}
	  		else{
	  			window.location.href="device-finder?assetRowId=${assetRowId}&isDeviceBookmarked=${isDeviceBookmarked}&backHistory=true&lclick=${linkClicked}";
	  		}
  			
  			
  			//window.location.href="device-finder?assetRowId=${assetRowId}&isDeviceBookmarked=${isDeviceBookmarked}&backHistory=true&lclick=${linkClicked}";
  	  	}else{
  	  		window.location=backUrl;
  	  	  	}
	  		
  		
  	}
	
	function downloadAttachment(attachmentName, dispAttachmentName, fileExtension, identifier){
		
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
	  		window.location = "${downloadAttachment}&attachmentName=" + attachmentNameEncoding + "&fileExtension=" + fileExtension+ "&identifier=" + identifier + "&dispAttachmentName=" + dispAttachmentNameEncoding;
	  	}
	  	
	  	
	}
	

	function cancelRequest(){
		 callOmnitureAction('<%=LexmarkSPOmnitureConstants.SUPPLYREQUESTDETAILS%>','<%=LexmarkSPOmnitureConstants.SUPPLIESREQUESTDETAILCANCELREQUEST%>');
		showOverlay();
		//var forwardURL = populateURL("managechlothers",['forward', 'serviceRequestNumber', 'typeOfFlow', 'requestType','pagetype'],['Others', '${requestForm.serviceRequest.serviceRequestNumber }', 'cancel','Supplies','supplies']);
		 var forwardURL = populateURL("managechlothers",['forward', 'serviceRequestNumber', 'typeOfFlow','area','subArea','pagetype'],['Others', '${requestForm.serviceRequest.serviceRequestNumber }', 'cancel','Cancel Request','Consumables SR','supplies']);
		window.location.href=forwardURL;
	}
	
	function gotoControlPanel(url) {
		 callOmnitureAction('<%=LexmarkSPOmnitureConstants.SUPPLYREQUESTDETAILS%>','<%=LexmarkSPOmnitureConstants.SUPPLIESREQUESTDETAILIPADDRESS%>');
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
            url:"<portlet:renderURL windowState='<%= LiferayWindowState.POP_UP.toString() %>'>"+
                "<portlet:param name='action' value='gotoControlPanel' />"+
                "</portlet:renderURL>&controlPanelURL=" + url
            }); --%>
    };

	image_error= function () {
	
  		document.getElementById("MyPicture").src = '<html:imagesPath/>dummy_noimg.jpg'; 
  
	};
var dialog;
	function srDetailsPopup(ServiceRqstNumber,ServiceRqstType) {
		//ServiceRqstType='.';
	//alert("Here ::: "+ ServiceRqstType);
	ServiceRqstType = ServiceRqstType.replace(" ", "_"); 
		//ServiceRqstType="Fleet Management";
		//url = '${RequestDetailsPopUpUrl}'+"&ServiceRqstNumber="+ServiceRqstNumber+"&ServiceRqstType="+ServiceRqstType;
		//contactListType=contactType;	
		//jQuery('#dialogDetails').load(url,function(){
				
		//showOverlay();
		var url="<portlet:renderURL windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>"><portlet:param name='action' value='showRequestDetailsPopup'/></portlet:renderURL>&requestType="+
			ServiceRqstType+"&serviceRequestNumber="+ServiceRqstNumber+"&timeZoneOffset="+timezoneOffset+"&uniqueTime="+new Date().getTime();
		
	try {
		if(ServiceRqstType=='Consumables_Management') {
			showOverlay();	
				//var url = "${showRequestDetailsPopupUrl}&requestType="+ServiceRqstType+"&serviceRequestNumber="+ServiceRqstNumber;
				
						jQuery('#dialogSupplyDetails').load(url,function(){
						dialog=jQuery('#contentDetailsSupply').dialog({
							autoOpen: false,
							title: '<spring:message code="Details.changeRequestDetails.heading.supplyReqDeails"/>',
							modal: true,
							draggable: false,
							resizable: false,
							position: 'center',
							height: 'auto',
							width: 940,
							open: function(){						
								supplyRqstDetails(ServiceRqstNumber,ServiceRqstType);
							},
							close: function(event,ui){
								hideOverlay();
								dialog.dialog('destroy');					 
								dialog=null;
								jQuery('#contentDetailsSupply').remove();
								},
							error: function (jqXHR, textStatus, errorThrown) {
								hideOverlay();
								dialog.dialog('destroy');					 
								dialog=null;
								jQuery('#contentDetailsSupply').remove();
								}
							});
						dialog.dialog('open');
						});						
			
		} else if(ServiceRqstType=='Fleet_Management') {

			//var url = "${showRequestDetailsPopupUrl}&requestType="+ServiceRqstType+"&serviceRequestNumber="+ServiceRqstNumber;
			showOverlay();
					jQuery('#dialogChangeDetails').load(url,function(){
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
							changeRqstDetails(ServiceRqstNumber,ServiceRqstType);
						},
						close: function(event,ui){
							hideOverlay();
							jQuery('#contentDetailsChange').remove();
							//jQuery('#dialogChangeDetails').remove();
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

		else {
			callOmnitureAction('<%=LexmarkSPOmnitureConstants.SUPPLYREQUESTDETAILS%>', '<%=LexmarkSPOmnitureConstants.SUPPLIESREQUESTHISTORYASSETLISTREQUESTNOCLICK%>'); 
			onSRNmbrClick(ServiceRqstNumber);
		}
	}
	catch (ex) {
		hideOverlay();
		dialog.dialog('destroy');					 
		dialog=null;
		//jQuery('#contentDetailsChange').remove();
		return false;
	}
	return false;
		
	}

	 function onSRNmbrClick(serviceRequestNumber,type){
	    	//closeCustomizedWindow();
			//callOmnitureAction('Service Request', 'Service Request View Detail');
	        var iLeft = (window.screen.availWidth-820)/2; 
	        
	        var url="<portlet:renderURL windowState='<%= LiferayWindowState.EXCLUSIVE.toString() %>'><portlet:param name='action' value='serviceRequestDrillDownLightBox' /></portlet:renderURL>&serviceRequestNumber="+ serviceRequestNumber+"&lightBox=lightBox";
	       
	        showOverlay();
	        $('#serviceRequestDetailDiv').load(url,function(){
	        	dialog=$('#serviceRequestDetailDiv').dialog({
	        		autoOpen: false,
	        		title: "<spring:message code='serviceRequest.title.serviceRequest'/>",
					modal: true,
					height: 900,
					width: 850,
					draggable: false,
					resizable: false,
					position: ['center','top'],
					open: function(){
							
			  			},
					close: function(event,ui){
	   				  	  hideOverlay();
	   				  	  dialog.dialog('destroy');
	   				  	showSelect();
						}
	        	});
	        	dialog.dialog('open');
	        });
	        
	       
	    };
    
    
    
    function updateRequest(){
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
			forwardURL = populateURL("managechlothers",['forward', 'serviceRequestNumber', 'typeOfFlow','area','subArea'],['Others', '${requestForm.serviceRequest.serviceRequestNumber }', 'update','Update Request','Consumables SR']);
		}
		window.location.href=forwardURL;
	}

    function print() {
    	
    	jQuery('#statusExpand').show();
    	jQuery('#associatedExpand').show();
    	jQuery('#pastReqExpand').show();
    	jQuery('#sr_technician_div').show();
    	
		url ="<portlet:renderURL windowState='<%= LiferayWindowState.EXCLUSIVE.toString() %>'>"+
			"<portlet:param name='action' value='printSupplyHistory' />"+
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
	
	jQuery('#statusExpand').show();
	jQuery('#associatedExpand').show();
	jQuery('#pastReqExpand').show();
	jQuery('#sr_technician_div').show();

		url ="<portlet:renderURL windowState='<%= LiferayWindowState.POP_UP.toString() %>'>"+
			"<portlet:param name='action' value='emailSupplyHistory' />"+
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

	function expandActivity(){
		if(${requestForm.serviceRequestTechnicianXML!= null}){
			jQuery('#sr_technician_div').show();
		}	else{
			jQuery('#sr_technician_div').hide();
		}	
	}
</script>
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
