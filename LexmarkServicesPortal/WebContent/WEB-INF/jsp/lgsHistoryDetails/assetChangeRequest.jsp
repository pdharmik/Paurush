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
<!--[if IE 8]>
	<style type="text/css"><%@ include file="/WEB-INF/css/mps_ie8.css" %></style>
<![endif]--> 
<style>
.ie .xhdr {position:static !important;}
.status table td {
	width:25% !important;
}
</style>
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
<script type="text/javascript" src='<html:rootPath/>js/jquery.progressbar.js'></script>
<script type="text/javascript" src='<html:rootPath/>js/portletRedirection.js'></script>
<script type="text/javascript" src='<html:rootPath/>js/expand.js'></script>
<script type="text/javascript">
////Toggle Containers
	jQuery(function() {
	    jQuery("h4.expand").toggler();
	});
</script>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/in-styles.css?version=<html:fileCacheVersion/>" />
<body onload="expandActivity();"></body>
<div class="utilities printPage floatR" id="topEmailPrintDiv">
          <ul>
            <li class="first"><a href="javascript:email();"><img src="<html:imagesPath/>transparent.png" class="ui_icon_sprite email-icon" alt="<spring:message code="button.clickToEmail"/>" title="<spring:message code="button.clickToEmail"/>"></a></li>
            <li><a href="javascript: print();"><img src="/LexmarkServicesPortal/images/transparent.png" class="ui_icon_sprite print-icon" alt="<spring:message code="button.clickToPrint"/>" title="<spring:message code="button.clickToPrint"/>"></a></li>
          </ul>
        </div>
<div id="emailPrintWraper"> 
  <!-- CONTENT WRAPPER START -->
  <div id="content-wrapper">
    <div class="journal-content-article" id="emailPrintWraperHead" class="content" >
      <h1><spring:message code="Details.changeRequestDetails.heading.changeReqDeails"/></h1>
      <h2 class="step">
      	<spring:message code="Details.supplyRequestDetails.label.rquestNumber"/>
      	<span id="reqNo"> ${requestForm.serviceRequest.serviceRequestNumber }</span>
      </h2>  
    </div>
    <!-- MAIN WRAP START -->
    <div class="main-wrap">
      <!-- CONTENT START -->
      <div id="emailPrintWraperTop">
      <div class="content"> 
        <!-- MAIN CONTENT BEGIN -->
        <div class="portletBlock infoBox rounded shadow split">
          <div class="columnsTwo">
            <div class="columnInner">
              <ul class="form">
                <li>
                  <label><spring:message code="requestInfo.label.area"/></label>
                  <span id="area">${requestForm.serviceRequest.area.value }</span></li>
                <li>
                  <label><spring:message code="requestInfo.label.subArea"/></label>
                  <span id="subarea">${requestForm.serviceRequest.subArea.value }</span></li>
			
                <li>
                  <label><spring:message code="Details.changeRequestDetails.label.status"/></label>
                  <span id="serviceRequestStatus">${requestForm.serviceRequest.serviceRequestStatus }</span></li>
              </ul>
            </div>
          </div>
          <div class="columnsTwo">
            <div class="columnInner">
              <ul class="form">
                <li>
                  <label><spring:message code="Details.changeRequestDetails.label.dateAndTime"/></label>
                  <span id="creationTime">${requestForm.creationDate }</span></li>
                <li>
                  <label><spring:message code="Details.changeRequestDetails.label.requesterName"/></label>
                  <span id="requestorName">${fn:trim(requestForm.serviceRequest.requestor.firstName)}&nbsp;
                  ${fn:trim(requestForm.serviceRequest.requestor.lastName)}</span></li>
			
              </ul>
            </div>
          </div>
        </div>
        
		
		
		<div class="portletBlock infoBox rounded shadow">
          <div class="columnsOne">
            <div class="columnInner">
              <div class="icon"><img class="ui_icon_sprite widgets inProcess-icon" src="<html:imagesPath/>transparent.png" width="100" height="100" /></div>
              <div class="status">
                <div id="div_progressBar"><!-- PROGRESS BAR WILL COME HERE --></div>
                <table border="0" cellspacing="0" cellpadding="0">
                  <tr>
                    <td><spring:message code="Details.changeRequestDetails.process.submitted"/></td>
                    <td><spring:message code="Details.changeRequestDetails.process.inProgress"/></td>
                    <td><spring:message code="Details.changeRequestDetails.process.shipped"/></td>
                    <td><spring:message code="Details.changeRequestDetails.process.Complete"/></td>
                  </tr>
                </table>
              </div>
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
	    				<div id="div_statusPagingArea"></div>
					</div>
				</div>
				</div>
            </div>
          </div>
        </div>
        <!-- Request Status History - End -->
				
		<!-- COMMON CONTACT INFO BLOCK START -->
        <div class="portletBlock">
          <div class="columnsTwo">
            <div class="infoBox columnInner rounded shadow">
              <h4><spring:message code="requestInfo.heading.primaryContactForThisRequest"/></h4>
              <ul class="form wordBreak">
                <li>
                  <label><spring:message code="requestInfo.label.name"/></label>
                  <span id="primaryCont_name">${fn:trim(requestForm.serviceRequest.primaryContact.firstName)}&nbsp;${fn:trim(requestForm.serviceRequest.primaryContact.lastName)}</span></li>
                <li>
                  <label><spring:message code="requestInfo.label.phoneNumber"/></label>
                  <span id="primaryCont_phone">${requestForm.serviceRequest.primaryContact.workPhone }</span></li>
                <li>
                  <label><spring:message code="requestInfo.label.emailAddress"/></label>
                  <span id="primaryCont_email">${requestForm.serviceRequest.primaryContact.emailAddress } </span></li>
              </ul>
            </div>
            <c:if test="${requestForm.serviceRequest.secondaryContact.firstName != ''}">
            <div class="infoBox columnInner rounded shadow" id="secondarySection">
              <h4><spring:message code="requestInfo.heading.secondaryContactForThisRequest"/></h4>
              <ul class="form wordBreak">
                <li>
                  <label><spring:message code="requestInfo.label.name"/></label>
                  <span id="secondaryCont_name">${fn:trim(requestForm.serviceRequest.secondaryContact.firstName)}&nbsp;${fn:trim(requestForm.serviceRequest.secondaryContact.lastName)}</span></li>
                <li>
                  <label><spring:message code="requestInfo.label.phoneNumber"/></label>
                  <span id="secondaryCont_phone">${requestForm.serviceRequest.secondaryContact.workPhone }</span></li>
                <li>
                  <label><spring:message code="requestInfo.label.emailAddress"/></label>
                  <span id="secondaryCont_email">${requestForm.serviceRequest.secondaryContact.emailAddress }</span></li>
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
                  <span id="addtnlInfo_customerRefId">${requestForm.serviceRequest.customerReferenceNumber}</span></li>
                <li>
                  <label><spring:message code="requestInfo.label.costCentre"/></label>
                  <span id="costCenter">${requestForm.serviceRequest.costCenter }</span></li>
                <li>
                  <label><spring:message code="requestInfo.label.description"/></label>
                  <span class="multiLine" id="description">${requestForm.serviceRequest.addtnlDescription}</span></li>
                <li>
                  <label><spring:message code="requestInfo.label.dateOfChange"/></label>
                  <span id = "effDateChange">
					<util:dateFormat value="${requestForm.serviceRequest.requestedEffectiveDate }"></util:dateFormat>                  
				  </span></li>
              </ul>
            </div>
          </div>
        </div>
        <!-- COMMON CONTACT INFO BLOCK END -->
       <!-- ASSET INFO BLOCK START -->
        <div class="portletBlock">
<div class="columnsTwo">
  <div class="infoBox rounded shadow"> 
    <!-- Outter table: 1 row & 2 colums -->
    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="assetDetail">
      <tr> 
        <!-- Outter Table > column 1: Product image & Name -->
        <td class="pModel"><ul>
            <li class="center"><img src="${requestForm.serviceRequest.asset.productImageURL}" onError="image_error(this.id);" id="changeImage"/></li>
            <li class="pModelName" id="productTLIold">${requestForm.serviceRequest.descriptionLocalLang}</li>
          </ul></td>
        <!-- Outter Table > Column 2: Inner table for details -->
        <td class="pDetail"><!-- Inner table: 4 row2 & 2 colums -->
          
          <table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td class="h4"><spring:message code="requestInfo.cm.manageAsset.heading.selectedAssetDetails"/></td>
            </tr>
            <tr>
              <td class="infoDsiplay"><ul class="form">
                  <li>
                    <label><spring:message code="requestInfo.assetInfo.label.serialNumber"/></label>
                    <c:choose>
                    <c:when test="${requestForm.serviceRequest.subArea eq 'HW MADC Install/Decommission'}">
                    <c:forEach items="${requestForm.serviceRequest.parts}" var="deInstalledAsset">
                    <span id="serialNumber">${deInstalledAsset.installSerialNumber}</span></li>
                    </c:forEach>
                    </c:when>
                    <c:otherwise>
                     <span>${requestForm.serviceRequest.asset.serialNumber}</span></li>
                    
                    </c:otherwise>
                    </c:choose>
                    
                    
                  <li>
                    <label><spring:message code="requestInfo.assetInfo.label.productName"/></label>
                    
                     <c:choose>
                    <c:when test="${requestForm.serviceRequest.subArea eq 'HW MADC Install/Decommission'}">
                    <c:forEach items="${requestForm.serviceRequest.parts}" var="deInstalledAsset">
                    <span id="serialNumber">${deInstalledAsset.installProduct}</span></li>
                    </c:forEach>
                    </c:when>
                    <c:otherwise>
                    <span id="productLine">${requestForm.serviceRequest.asset.productTLI}</span></li>
                    
                    </c:otherwise>
                    </c:choose>
                   
                  <li>
                    <label><spring:message code="requestInfo.assetInfo.label.installDate"/></label>
                    
                    <span id="installDate">
                    <util:dateFormat value="${requestForm.serviceRequest.asset.installDate }"></util:dateFormat>
                    </span></li>
                  <li>
                    <label><spring:message code="requestInfo.assetInfo.label.ipAddress"/></label>
                    <span id="ipAddress">${requestForm.serviceRequest.asset.ipAddress}</span></li>
                  <li>
                    <label><spring:message code="requestInfo.assetInfo.label.hostName"/></label>
                    <span id="hostName">${requestForm.serviceRequest.asset.hostName}</span></li>
                  <li>
                    <label><spring:message code="requestInfo.assetInfo.label.customerAssetTag"/></label>
                    <span id="deviceTag">${requestForm.serviceRequest.asset.assetTag}</span></li>
                </ul></td>
            </tr>
          </table></td>
      </tr>
    </table>
  </div>
</div>


  <div class="columnsTwo">
    <div class="infoBox columnInner rounded shadow">
      <h4><spring:message code="requestInfo.cm.manageAsset.heading.billingDetails"/></h4>
      <ul class="form">
        <li>
          <label for="assetCost"><spring:message code="requestInfo.assetInfo.label.assetCostCenter"/></label>
          <span id="assetCostCenter">${requestForm.serviceRequest.asset.assetCostCenter}</span></li>
      </ul>
    </div>
  </div>
  <div class="columnsTwo">
    <div class="infoBox columnInner rounded shadow">
      <h4><spring:message code="requestInfo.cm.manageAsset.heading.customerHierercyLevel"/></h4>
      <ul class="form">
        <li>
          <label><spring:message code="requestInfo.cm.manageAsset.label.assetHierercyLevel"/></label>
          <span id="chlNodeValue">${requestForm.serviceRequest.asset.chlNodeValue}</span></li>
      </ul>
    </div>
  </div>
</div>
<!-- ACTIVITY BLOCK - START -->
<c:if test="${requestForm.serviceRequest.serviceActivityStatus!= null}">
        <div class="portletBlock infoBox rounded shadow" id="activityBlock">
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
        <!-- ASSET INFO BLOCK END -->
        <%--Request History for Asset (CI) STARTS--%>
		<%-- <div class="portletBlock infoBox rounded shadow">
           <div class="columnsOne">
            <div class="columnInner">
               <h4 class="expand" onclick="show_load();"> <!--show load called for asynshronous-CI7 -->
              <spring:message code="Details.manageAsset.heading.requestHistoryOnThisAsset"/></h4>
              <div  class="collapse"> --%>
<!--                <div class="grid-controls">  -->
<!--                       Expand-min Start -->
                   <!--   <div class="expand-min"> -->
                      
                   <!--    <a href="#grid" id='headerMenuButton' style="display: none;"><spring:message code="requestInfo.link.customizeColumns"/></a> </div> <!--DONT REMOVE THIS CI BRD13-10-02--%>  -->
                       
<!--                       NO CONTENT HERE PLS  -->
<!--                     </div> -->
<!--                     Changes for Defect 7490 MPS 2.1 -->
             <!--   <div id="gridContainerDiv_all_request_types"  class="gridbox gridbox_light" style="display: none;"></div> -->
                
				<!--    		<div id="loadingNotification_request_type" class='gridLoading'> -->
	        				<!--	<br><!--spring:message code='loadingNotification' />&nbsp;&nbsp;><img src="<html:imagesPath/>gridloading.gif"/><br> -->
	    		<!--			</div> -->
				<!--    		<div class="pagination" id="paginationId"> -->
				<!--    		<span id="pagingArea_request_type"></span><span id="infoArea_req_status"></span> -->
				 <!--   		</div> -->
<!-- 				   ENDS Changes for Defect 7490 MPS 2.1 -->
					
           <!--    </div> -->
          <!--   </div> -->
        <!--   </div> -->
       <!--  </div> -->
       <!--  </div> -->
      <!--   </div> -->
        <%--Request History for Asset (CI) ENDS--%>
         <!-- PAGE COUNT BLOCK START -->
		<div class="columnsOne" id="pageCountsSection">
            <div class="infoBox columnInner rounded shadow">
              <h4><spring:message code="requestInfo.manageAsset.heading.pageCount"/></h4>
              <div  class="collapse" id="statusExpand">
                <div id="div_pageCounts">
					<div id="tab_pageCounts" style="display:block;">
						<div id="pageCounts_container" class="div-style48"></div>
	    				<div class="pagination"><span id="div_pageCountsPagingArea"></span>
	    				<span id="div_pageCountsinfoArea"></span></div>
					</div>
				</div>
              </div>
            </div>
          </div>
		<!-- PAGE COUNT BLOCK END -->
		<div id="emailPrintWraperBottom">
		<div class="columnsOne" id="addressSection">
            <div class="infoBox columnInner rounded shadow">
              <h4 id="installInfo" style="display: none"><spring:message code="requestInfo.heading.installInfo"/></h4>
              <h4 id="moveInfo" style="display: none"><spring:message code="requestInfo.heading.moveInfo"/></h4>
              <h4 id="pickUpInfo" style="display: none"><spring:message code="requestInfo.cm.manageAsset.heading.pickupInformation"/></h4>
          <div class="columnsTwo">
            <div class="infoBox columnInner rounded shadow">
              <h4 id="installAddress" style="display: none"><spring:message code="requestInfo.heading.installAddress"/></h4>
              <h4 id="moveAddress" style="display: none"><spring:message code="requestInfo.heading.currentInstallAdd"/></h4>
              <h4 id="pickupAddress" style="display: none"><spring:message code="requestInfo.cm.manageAsset.heading.pickupAddress"/></h4>
              <ul class="roDisplay">
              <c:choose>
              <c:when test="${requestForm.serviceRequest.subArea.value eq 'Move Asset' || (requestForm.serviceRequest.asset.moveFromAddress.addressLine1 != null && requestForm.serviceRequest.asset.moveFromAddress.addressLine1 != '') }">
              <li>
						<div>${requestForm.serviceRequest.asset.moveFromAddress.storeFrontName}</div>						
						<util:addressOutput value="${requestForm.serviceRequest.asset.moveFromAddress}"></util:addressOutput>
						</li>					                    
                      
                      <li><label><spring:message code="requestInfo.addressInfo.label.building"/> </label>
						<span>${requestForm.serviceRequest.asset.moveFromAddress.physicalLocation1}</span>
                      </li>
					  <li><label><spring:message code="requestInfo.addressInfo.label.floor"/> </label>
                       <span>${requestForm.serviceRequest.asset.moveFromAddress.physicalLocation2}</span>
                      </li>
					
					  <li><label><spring:message code="requestInfo.addressInfo.label.office"/> </label> 
                    <span>${requestForm.serviceRequest.asset.moveFromAddress.physicalLocation3}</span>
                      </li>
				</c:when>
					<c:otherwise>	
	                	<li>
						<div>${requestForm.serviceRequest.asset.installAddress.storeFrontName}</div>						
						<util:addressOutput value="${requestForm.serviceRequest.asset.installAddress}"></util:addressOutput>
						</li>					                    
                      
                      <li><label><spring:message code="requestInfo.addressInfo.label.building"/> </label>
						<span>${requestForm.serviceRequest.asset.installAddress.physicalLocation1}</span>
                      </li>
					  <li><label><spring:message code="requestInfo.addressInfo.label.floor"/> </label>
                       <span>${requestForm.serviceRequest.asset.installAddress.physicalLocation2}</span>
                      </li>
					
					  <li><label><spring:message code="requestInfo.addressInfo.label.office"/> </label> 
                    <span>${requestForm.serviceRequest.asset.installAddress.physicalLocation3}</span>
                      </li>
					 </c:otherwise>
					 </c:choose>
					</ul>
            </div>
          </div>
           <div class="columnsTwo" id="moveToAddressSplit" style="display: none;">
            <div class="infoBox columnInner rounded shadow">
              <h4><spring:message code="requestInfo.manageAsset.heading.moveToAddr"/></h4>
              <ul class="roDisplay">
	                						                    
                     <c:if test="${requestForm.serviceRequest.subArea.value eq 'Move Asset' ||  (requestForm.serviceRequest.asset.moveFromAddress.addressLine1 != null && requestForm.serviceRequest.asset.moveFromAddress.addressLine1 != '')}">
						<li>
						<div>${requestForm.serviceRequest.asset.installAddress.storeFrontName}</div>						
						<util:addressOutput value="${requestForm.serviceRequest.asset.installAddress}"></util:addressOutput>
						</li>
                      <li><label><spring:message code="requestInfo.addressInfo.label.building"/> </label>
						<span>${requestForm.serviceRequest.asset.installAddress.physicalLocation1}</span>
                      </li>
					  <li><label><spring:message code="requestInfo.addressInfo.label.floor"/> </label>
                       <span>${requestForm.serviceRequest.asset.installAddress.physicalLocation2}</span>
                      </li>
					
					  <li><label><spring:message code="requestInfo.addressInfo.label.office"/> </label> 
                    <span>${requestForm.serviceRequest.asset.installAddress.physicalLocation3}</span>
                      </li>	
                      </c:if>
					 
					</ul>
            </div>
          </div>
        </div>
		</div>
		<c:if test="${requestForm.serviceRequest.subArea eq 'HW MADC Install/Decommission'}">
		
				  <div class="infoBox columnInner rounded shadow" id="deInstallInfo">
				  <h4><spring:message code="requestInfo.info.heading.deinstallAssetInfo"/></h4>
				  	 
				  <div class="displayGrid columnInnerTable rounded shadow" style="overflow: auto;">
				  <table>
						<thead>
						<tr>
						<th style="width: 12.5%"><spring:message code="requestInfo.assetInfo.listHeader.serialNumber"/></th>
						<th style="width: 12.5%"><spring:message code="serviceRequest.label.productModel"/></th>
						<th style="width: 12.5%"><spring:message code="lbs.label.brand"/></th>
						<th style="width: 12.5%"><spring:message code="orderSupplies.placeOrderHeader.partNumber"/></th>
						<th style="width: 12.5%"><spring:message code="requestInfo.assetInfo.label.hostName"/></th>
						<th style="width: 12.5%"><spring:message code="lbs.label.assettag"/></th>
						<th style="width: 12.5%"><spring:message code="lbs.label.ipaddress"/></th>
						<th style="width: 12.5%"><spring:message code="requestInfo.label.comments"/></th>
						
						</tr>
						</thead>
							 <tbody>
							 <c:forEach items="${requestForm.serviceRequest.parts}" var="deInstalledAsset">
							<tr>									
							<td id="deInstallSerialNumber">${deInstalledAsset.deinstallSerialNumber}</td>
							<td id="deInstallProductLine">${deInstalledAsset.deinstallModel}</td>
							<td id="deInstallBrand">${deInstalledAsset.deinstallBrand}</td>
							<td id="deInstallPartNo">${deInstalledAsset.deinstallProduct}</td>
							<td id="deInstallHostName">${deInstalledAsset.deinstallHostName}</td>
							<td id="deInstallAssetTag">${deInstalledAsset.deinstallAssetTag}</td>
							<td id="deInstallIpAddress">${deInstalledAsset.deinstallIPAddr}</td>							
							<td id="deInstallComments">${deInstalledAsset.deinstallComments}</td>
							</tr>
							</c:forEach>
							 </tbody>
						</table>
				  </div>
				  </div>
        
		
		</c:if>
		<c:if test="${requestForm.serviceRequest.subArea.value ne 'Decommission Asset' &&  requestForm.serviceRequest.subArea.value ne 'Deregister Asset'}">
		  <div class="columnsOne" id="deviceContactSection">
            <div class="infoBox columnInner rounded shadow">
              <h4><spring:message code="requestInfo.heading.contactInfoForDevice"/></h4>
			  <div>
					<table class="displayGrid rounded shadow" id="deviceTable">
						<thead>
						<tr>
							<th class="w20p"><spring:message code="requestInfo.heading.contactType"/></th>
							<th class="w40p"><spring:message code="requestInfo.manageAsset.info.primaryContact"/></th>
							<th class="w40p"><spring:message code="requestInfo.heading.address"/></th>
						</tr>
						</thead>
						
						<tbody>						
						
						<c:set var="deviceContactItem" value="${requestForm.serviceRequest.asset.deviceContact}" />
						
						<c:forEach items="${deviceContactItem}" var="deviceContactList" varStatus="counterContact" begin="0">
						<tr class="<c:if test="${counterContact.index % 2 ne 0}">altRow</c:if>">
						
							<td class="middle"><div id="deviceContactType${counterContact.index+1}">${deviceContactList.deviceContactType}</div></td>						
							<td class="middle">
								<div>
								<ul class="form">
									<li style="display:none"> 
										<span id="deviceContactId${counterContact.index+1}">${deviceContactList.contactId}</span>
									</li>
								    <li>
								      <label><spring:message code="requestInfo.label.name"/></label>
								      <span id="deviceFName${counterContact.index+1}">${deviceContactList.firstName}</span>
								      <span id="deviceLName${counterContact.index+1}">${deviceContactList.lastName}</span></li>
								    <li>
								      <label><spring:message code="requestInfo.label.phoneNumber"/></label>
								      <span id="devicePhone${counterContact.index+1}">${deviceContactList.workPhone}</span></li>
								    <li style="display:none"> <span id="deviceAltPhone${counterContact.index+1}">${deviceContactList.alternatePhone}</span></li>
								    <li>
								      <label><spring:message code="requestInfo.label.emailAddress"/></label>
								      <span id="deviceEmail${counterContact.index+1}"></span>${deviceContactList.emailAddress}</li>
							  	</ul>
							  	</div>
							
							</td>
							<td class="middle">								
								<ul class="roDisplay">
								    <li>
									    <div id="deviceAddressId${counterContact.index+1}" style="display:none">${deviceContactList.address.addressId}</div>
									    <div id="deviceStoreFrontName${counterContact.index+1}">${deviceContactList.address.storeFrontName}</div>
										<div id="deviceAddressLine1${counterContact.index+1}">${deviceContactList.address.addressLine1}</div>
										<div id="deviceAddressofficeNumber${counterContact.index+1}">${deviceContactList.address.officeNumber}</div>
										<c:if test="${deviceContactList.address.addressLine2} != null || ${deviceContactList.address.addressLine2 != ''} || ${deviceContactList.address.addressLine2} != ' '}">
											<div id="deviceAddressLine2${counterContact.index+1}">${deviceContactList.address.addressLine2},</div>
										</c:if>
										<div id="city_state_zip_popup_span${counterContact.index+1}">
										<c:if test="${deviceContactList.address.city} != null || ${deviceContactList.address.city} != '' || ${deviceContactList.address.city} != ' '}">
											<span id="deviceAddressCity${counterContact.index+1}">${deviceContactList.address.city}, </span>
										</c:if>
										<c:if test="${deviceContactList.address.county} != null || ${deviceContactList.address.county} != '' || ${deviceContactList.address.county} != ' '}">
											<span id="deviceAddresscounty${counterContact.index+1}">${deviceContactList.address.county}, </span>
										</c:if>
										<c:if test="${deviceContactList.address.state} != null || ${deviceContactList.address.state} != '' || ${deviceContactList.address.state} != ' '}">
											<span id="deviceAddressState${counterContact.index+1}">${deviceContactList.address.state}, </span>
										</c:if>
										<c:if test="${deviceContactList.address.province} != null || ${deviceContactList.address.province} != '' || ${deviceContactList.address.province} != ' '}">
											<span id="deviceAddressProvince${counterContact.index+1}">${deviceContactList.address.province}, </span>
										</c:if>
										<span id="deviceAddressDistrict${counterContact.index+1}">${deviceContactList.address.district}</span>
										</div>
										<div id="deviceAddressPostCode${counterContact.index+1}">${deviceContactList.address.postalCode}</div>
										<div id="deviceAddressCountry${counterContact.index+1}">${deviceContactList.address.country}</div>
									</li>           	
								    <li>
								      <label for="building"><spring:message code="requestInfo.addressInfo.label.building" /></label>
								      <span id="physicalLocation1${counterContact.index+1}">${deviceContactList.address.physicalLocation1}</span></li>
								    <li>
								      <label for="floor"><spring:message code="requestInfo.addressInfo.label.floor" /></label>
								      <span id="physicalLocation2${counterContact.index+1}">${deviceContactList.address.physicalLocation2}</span></li>
								    <li>
								      <label for="office"><spring:message code="requestInfo.addressInfo.label.office" /></label>
								      <span id="physicalLocation3${counterContact.index+1}">${deviceContactList.address.physicalLocation3}</span></li>
								</ul>								
							</td>
			
						</tr>
						</c:forEach>
						</tbody>
					</table>
            </div>      
        </div>
        </div>
        </c:if>
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
              <div id ="showAttachment">  <%-- div-id added for email  --%>
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
        <!-- Add Attachments BLOCK - End --> 
		
		
		
		
        <!-- NOTES AND ATTACHMENT BLOCK END -->        
         </div>
      </div>
      <!-- CONTENT END --> 
    </div>
    <!-- MAIN WRAP END -->
  </div>
  <!-- CONTENT WRAPPER END -->
</div>
</div>
<div class="buttonContainer">
              <button id="backButton" class="button_cancel" type="button" onclick="backToHistory();"><spring:message code="button.back"/></button>
         <c:if test="${requestForm.serviceRequest.serviceRequestStatus !=null && requestForm.serviceRequest.serviceRequestStatus != 'Completed' }">
	          <button id="updateButton" class="button" type="button" onclick="updateRequest();"><spring:message code="button.updateRequest"/></button>
	           <c:if test="${sessionScope.isStandardUser eq false}">
	         <button id="CancelButton" class="button_cancel" type="button" onclick="cancelRequest();"><spring:message code="button.cancelRequest"/></button>
	          </c:if>
	          
	      </c:if>
          </div>
<jsp:include page="/WEB-INF/jsp/common/dynamicGridInitialize.jsp"></jsp:include> <%--Added for CI BRD13-10-02--%>
<script type="text/javascript">
//Added for CI BRD13-10-02--START
var assetRowId1="${requestForm.serviceRequest.asset.assetId}";
var accountId='${requestForm.serviceRequest.asset.account.accountId}';
var linkClicked=1;
var timezoneOffsetServiceRequest="";
//Added for CI BRD13-10-02--END
		//page counts start
		pageCountsGrid = new dhtmlXGridObject('pageCounts_container');
		pageCountsGrid.setImagePath("<html:imagesPath/>gridImgs/");
		pageCountsGrid.setHeader("<spring:message code='requestInfo.heading.commonPageCounts'/>");
		pageCountsGrid.setColAlign("left,left,left,left");
		pageCountsGrid.setColTypes("ro,ro,ro,ro");
		pageCountsGrid.setInitWidths("160,200,180,*");
		pageCountsGrid.enableAutoWidth(true);
		pageCountsGrid.enableMultiline(true);
		pageCountsGrid.enableAutoHeight(true);
		pageCountsGrid.init(); 
		pageCountsGrid.prftInit();
		pageCountsGrid.enablePaging(true, 5, 10, "div_pageCountsPagingArea", true);
		pageCountsGrid.setPagingSkin("bricks");
		pageCountsGrid.setSkin("light");
		//alert('${requestForm.srStausXML}');
		pageCountsGrid.loadXMLString('${pageCountsString}');
		pageCountsGrid.setColumnHidden(3,true);
		//page counts end
		var barImages = { 0: '<html:imagesPath/>progressBar/progressbg_red.gif',
		26:	'<html:imagesPath/>progressBar/progressbg_orange.gif',
   		51: '<html:imagesPath/>progressBar/progressbg_yellow.gif',
		76: '<html:imagesPath/>progressBar/progressbg_green.gif'};
	barImagesCancel = {0: '<html:imagesPath/>progressBar/progressbg_black.gif'};
	jQuery('#div_progressBar').progressBar('${requestForm.reqProgress}', { width:480,speed:25,increment:1,height:12,showText: false, boxImage: '<html:imagesPath/>progressBar/progressbar.gif', barImage:barImages} );
	

	SRStatusGrid = new dhtmlXGridObject('div_status_container');
  	SRStatusGrid.setImagePath("<html:imagesPath/>gridImgs/");
  	SRStatusGrid.setHeader("<spring:message code='serviceRequest.listHeader.srstatus'/>");
  	SRStatusGrid.setColAlign("left,left,left");
  	SRStatusGrid.setColTypes("ro,ro,ro");
  	//SRStatusGrid.setColSorting("str,str,str");
  	SRStatusGrid.setInitWidths("200,250,450");
  	SRStatusGrid.enableAutoWidth(true);
  	SRStatusGrid.enableMultiline(true);
  	SRStatusGrid.enableAutoHeight(true);
  	SRStatusGrid.init(); 
  	SRStatusGrid.prftInit();
  	SRStatusGrid.enablePaging(true, 5, 10, "div_statusPagingArea", true);
  	SRStatusGrid.setPagingSkin("bricks");
  	SRStatusGrid.setSkin("light");
	SRStatusGrid.loadXMLString('${requestForm.srStausXML}'); 
	var SRArea='${requestForm.serviceRequest.area.name}';
	var SRSubArea='${requestForm.serviceRequest.subArea.name}';
	var coveredService='${coveredService}';
	
	var subArea;
	if((SRArea=="MADC" && SRSubArea=="Install Asset") || (SRArea=="MADC" && SRSubArea=="HW MADC Install/Decommission") ||(SRArea=="Data Management" && SRSubArea=="Register Asset") 
			|| (SRArea=="Install" && SRSubArea=="BAU" && (coveredService =="HW BAU Install" || coveredService =="HW BAU Install Change"))
			|| (SRArea=="Install" && SRSubArea=="Project Based" && (coveredService =="HW Install" || coveredService =="HW Install Change"))){
		subArea="Install Request";
		jQuery('#installInfo').show();
		jQuery('#installAddress').show();
		jQuery('#moveInfo').hide();
		jQuery('#moveAddress').hide();
		jQuery('#pickUpInfo').hide();
		jQuery('#pickupAddress').hide();
	}
	else if((SRArea=="MADC" && SRSubArea=="Move Asset") || (SRArea=="Data Management" && SRSubArea=="Change Asset Data")
			||(SRArea=="Install" && SRSubArea=="BAU" && coveredService == "HW BAU Install Move")
			||(SRArea=="Install" && SRSubArea=="Project Based" && coveredService == "HW Install Move")){
		subArea="Move Request";
		jQuery('#installInfo').hide();
		jQuery('#installAddress').hide();
		jQuery('#moveInfo').show();
		jQuery('#moveAddress').show();
		jQuery('#pickUpInfo').hide();
		jQuery('#pickupAddress').hide();
		if(!(SRArea=="Data Management" && SRSubArea=="Change Asset Data")){
		jQuery('#moveToAddressSplit').show();
		}
	}else if((SRArea=="MADC" && SRSubArea=="Decommission Asset") || (SRArea=="Data Management" && SRSubArea=="Deregister Asset")
			|| (SRArea=="Install" && SRSubArea=="BAU" && coveredService == "HW BAU Install Decommission")
			|| (SRArea=="Install" && SRSubArea=="Project Based" && coveredService == "HW Install Decommission")){
			subArea="Decommission Request";
			jQuery('#installInfo').hide();
			jQuery('#installAddress').hide();
			jQuery('#moveInfo').hide();
			jQuery('#moveAddress').hide();
			jQuery('#pickUpInfo').show();
			jQuery('#pickupAddress').show();
		}
	<c:if test="${requestForm.serviceRequest.subArea.value eq 'Move Asset' ||  (requestForm.serviceRequest.asset.moveFromAddress.addressLine1 != null && requestForm.serviceRequest.asset.moveFromAddress.addressLine1 != '')}">
	jQuery('#moveToAddressSplit').show();
	</c:if>
	//Script for Request History Grid CI (BRD13-10-02) STARTS
	var counter=0;
	//Added for CI BRD13-10-02--START
	function show_load(){
			counter++;
			if(!(counter%2)==0)
				{
			initialiseGrid();
			jQuery("#gridContainerDiv_all_request_types").css('height','25px');
			jQuery("#gridContainerDiv_all_request_types").show();
				}
		}
	//Added for CI BRD13-10-02--END

	// following variables are declared in dynamicGridInitialize
	// Changes for Defect 7490 MPS 2.1
	pagingArea="pagingArea_request_type";infoArea="infoArea_req_status";headerMenuButton="headerMenuButton";loadingNotification="loadingNotification_request_type";backFilterValues="";
	gridCreationId="gridContainerDiv_all_request_types";
	// ENDS Changes for Defect 7490 MPS 2.1
	pageSize=5,pagesIngrp=10;
	JSON_Param["<%=gridConfigurationValues[0]%>"]="<spring:message code='suppliesmanagement.details.srhistorygrid'/>";
	JSON_Param["<%=gridConfigurationValues[1]%>"]=",,,,";
	JSON_Param["<%=gridConfigurationValues[2]%>"]="left,left,left,left,left";
	JSON_Param["<%=gridConfigurationValues[3]%>"]="130,160,120,120,100";
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

	/*
	 *Added for MPS phase 2 
	 *
	 */

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
	//Script for CI BRD 13-10-02 ENDS
	function cancelRequest(){
 		 callOmnitureAction('<%=LexmarkSPOmnitureConstants.CHANGEREQUESTDETAILS%>','<%=LexmarkSPOmnitureConstants.CHANGEREQUESTCANCELREQUESTBUTTON%>');
 		showOverlay();
 		//var forwardURL = populateURL("managechlothers",['forward', 'serviceRequestNumber', 'typeOfFlow','pagetype'],['Others', '${requestForm.serviceRequest.serviceRequestNumber }', 'cancel','change']);
 		var forwardURL = populateURL("managechlothers",['forward', 'serviceRequestNumber', 'typeOfFlow','area','subArea','pagetype'],['Others', '${requestForm.serviceRequest.serviceRequestNumber }', 'cancel','Cancel Request',subArea,'change']);
 		window.location.href=forwardURL;
 	}
	
 	function updateRequest(){
 		 callOmnitureAction('<%=LexmarkSPOmnitureConstants.CHANGEREQUESTDETAILS%>','<%=LexmarkSPOmnitureConstants.CHANGEREQUESTUPDATEREQUESTBUTTON%>');
 		showOverlay();
 		var area = '${updateTypeFlag}';
 		var page ;
 		var forward ;
 		var forwardURL ;
 		
 			forwardURL = populateURL("managechlothers",['forward', 'serviceRequestNumber', 'typeOfFlow','area','subArea'],['Others', '${requestForm.serviceRequest.serviceRequestNumber }', 'update','Update Request',subArea]);
 	
		window.location.href=forwardURL;
 	}

 	function backToHistory(){
 		 callOmnitureAction('<%=LexmarkSPOmnitureConstants.CHANGEREQUESTDETAILS%>','<%=LexmarkSPOmnitureConstants.CHANGEREQUESTBACKBUTTON%>');
 		showOverlay();
 		//alert("Inside backToHistory::" + '${requestForm.sourcePage}');
 		var backUrl =  "<portlet:renderURL><portlet:param name='action' value='backToHistory' />	</portlet:renderURL>" ;
 		backUrl = backUrl+'&requestTypeStr=' + '${requestForm.sourcePage}';
 		var sourcepage="${requestForm.sourcePage}";
 		if(sourcepage=="DM"){
	  			
 			var currentURL = window.location.href;
	  		if(currentURL.indexOf('/grid-view') != -1){
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



 	//Added for MPS Phase 2.1
  	function print(){
  		jQuery('#statusExpand').show(); 
  		if(jQuery('#sr_technician_h4') != null){
 	 		 jQuery('#sr_technician_h4').show(); 
 	  	}
  	  callOmnitureAction('<%=LexmarkSPOmnitureConstants.CHANGEREQUESTDETAILS%>','<%=LexmarkSPOmnitureConstants.CHANGEREQUESTPRINT%>');
  		 
  	  	var url ="<portlet:renderURL windowState='<%= LiferayWindowState.POP_UP.toString() %>'><portlet:param name='action' value='printAssetRequest' /></portlet:renderURL>";         
  	      var iWidth=900;
  	      var iHeight=5600;
  	      var iTop = (window.screen.availHeight-30-iHeight)/2;        
  	      var iLeft = (window.screen.availWidth-10-iWidth)/2;           
  	      window.open(url,
  	              'ChangeRequestDetails',
  	              'height='+iHeight+
  	              ',innerHeight='+
  	              iHeight+',width='+
  	              iWidth+',innerWidth='+
  	              iWidth+',top='+iTop+
  	              ',left='+iLeft+
  	              ',status=no,toolbar=no,menubar=no,location=no,resizable=yes,scrollbars=yes,titlebar=no');
  	  };


  	  	
  	  	function email() {
  	  	jQuery('#statusExpand').show(); 
  	  	if(jQuery('#sr_technician_h4') != null){
  	 		 jQuery('#sr_technician_h4').show(); 
  	  	}
  	  
  	  		<%-- callOmnitureAction('<%=LexmarkSPOmnitureConstants.CHANGEREQUESTDETAILS%>','<%=LexmarkSPOmnitureConstants.CONFIRMASSETACCEPTANCEEMAIL%>'); --%>
  	  			var url ="<portlet:renderURL windowState='<%= LiferayWindowState.POP_UP.toString() %>'><portlet:param name='action' value='emailAssetRequest' /></portlet:renderURL>" ;

  	  	  		
  	  		
  	  		    var iWidth=1150;
  	  		    var iHeight=600;
  	  		    var iTop = (window.screen.availHeight-30-iHeight)/2;
  	  		    var iLeft = (window.screen.availWidth-10-iWidth)/2;
  	  		    window.open(url,
  	  		    		'asset_details',
  	  				    'height='+iHeight+
  	  				    ',innerHeight='+
  	  				    iHeight+',width='+
  	  				    iWidth+',innerWidth='+
  	  				    iWidth+',top='+iTop+
  	  				    ',left='+iLeft+
  	  				    ',status=no,toolbar=yes,menubar=no,location=no,resizable=yes,scrollbars=yes,titlebar=no');
  	  		};
  	  		


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
  	  	  		window.location = "${downloadAttachment}&attachmentName=" +attachmentNameEncoding + "&fileExtension=" + fileExtension + "&identifier=" + identifier + "&dispAttachmentName=" + dispAttachmentNameEncoding;
  	  	  	}
  	  	  }
  	  <c:if test="${requestForm.serviceRequest.asset.productImageURL == '' || requestForm.serviceRequest.asset.productImageURL == null}">
  	document.getElementById("changeImage").src = '<html:imagesPath/>dummy_noimg.jpg';
  	</c:if>
  	image_error= function (){
  			document.getElementById("changeImage").src = '<html:imagesPath/>dummy_noimg.jpg'; 
  		
  	}
  	if(${requestForm.serviceRequestTechnicianXML!= null}){
  		SRTechnicianGrid = new dhtmlXGridObject('div_technicianContainer');
  		SRTechnicianGrid.setImagePath("<html:imagesPath/>gridImgs/");
  		SRTechnicianGrid.setHeader("<spring:message code='serviceRequest.listHeader.activityNew'/>");
  		SRTechnicianGrid.setColAlign("left,left,left,left");
  		SRTechnicianGrid.setColTypes("ro,ro,ro,ro");
  		//SRTechnicianGrid.setColSorting("str,str,str");
  		SRTechnicianGrid.setInitWidths("150,150,200,150");
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
  	if(${requestForm.serviceRequest.serviceActivityStatus!= null}){  	  	
		if(${requestForm.serviceRequestTechnicianProgress==0}){
			jQuery("#div_technicianProgressBar1").progressBar(100 , { width:480,speed:25,increment:1,height:12,showText: false, boxImage: '<html:imagesPath/>progressBar/progressbar.gif', barImage : barImagesCancel} );
		}else{
	  		jQuery("#div_technicianProgressBar1").progressBar(${requestForm.serviceRequestTechnicianProgress}, { width:480,speed:25,increment:1,height:12,showText: false, boxImage: '<html:imagesPath/>progressBar/progressbar.gif', barImage : barImages} );
		}
	}	

  	function expandActivity(){
  		if(${requestForm.serviceRequestTechnicianXML!= null}){
			jQuery('#sr_technician_div').show();
		}	else{
			jQuery('#sr_technician_div').hide();
		}		
	}
</script>
