<%@ taglib uri="/WEB-INF/tld/liferay-portlet.tld" prefix="portlet"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="util" uri="/WEB-INF/tld/lexmark-util.tld" %>
<%@ taglib prefix="html" tagdir="/WEB-INF/tags/html" %>
<jsp:include page="/WEB-INF/jsp/includeGrid.jsp"/>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/mps.css"/>
<!--[if IE 7]>
	<style type="text/css"><%@ include file="/WEB-INF/css/mps_ie7.css" %></style>
<![endif]-->
<!--[if IE 8]>
	<style type="text/css"><%@ include file="/WEB-INF/css/mps_ie8.css" %></style>
<![endif]--> 
<!-- Added for CI 13-10-22 STARTS-->
<style>
.ie .xhdr {position:static !important;}
.status table td {
	width:25% !important;
}
</style>
<portlet:resourceURL var="downloadAttachment" id="downloadAttachment">
</portlet:resourceURL>

<!-- Added for CI 13-10-22 ENDS-->
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/in-styles.css?version=<html:fileCacheVersion/>" />
<div id="contentDetailsChange" class="div-style28">
    <div class="journal-content-article">
      <h1><spring:message code="Details.changeRequestDetails.heading.changeReqDeails"/></h1>
      <h2 class="step">
      	<spring:message code="Details.supplyRequestDetails.label.rquestNumber"/>
      	<span id="ChangeRqstNumber"></span>
      </h2> 
     </div>
   
    <div class="portletBlock infoBox rounded shadow split">
          <div class="columnsTwo">
            <div class="columnInner">
              <ul class="form">
                <li>
                  <label><spring:message code="requestInfo.label.area"/></label>
                  <span>${requestFormPopup.serviceRequest.area.value }</span></li>
                <li>
                  <label><spring:message code="requestInfo.label.subArea"/></label>
                  <span>${requestFormPopup.serviceRequest.subArea.value }</span></li>
                <li>
                  <label><spring:message code="Details.changeRequestDetails.label.status"/></label>
                  <span>${requestFormPopup.serviceRequest.serviceRequestStatus }</span></li>
              </ul>
            </div>
          </div>
          <div class="columnsTwo">
            <div class="columnInner">
              <ul class="form">
                <li>
                  <label><spring:message code="Details.changeRequestDetails.label.dateAndTime"/></label>
<%--                   <span><fmt:formatDate value="${requestFormPopup.serviceRequest.serviceRequestDate }" pattern="dd MMM yyyy"/></span></li> --%>
 					<%-- <span><util:dateFormat value="${requestFormPopup.serviceRequest.serviceRequestDate}" timezoneOffset="${timeZoneOffsetPopup}"></util:dateFormat></span></li>--%>
 					<span>${requestFormPopup.creationDate }</span></li>
                <li>
                  <label><spring:message code="Details.changeRequestDetails.label.requesterName"/></label>
                  <span>${requestFormPopup.serviceRequest.requestor.firstName } &nbsp; ${requestFormPopup.serviceRequest.requestor.lastName }</span></li>
              </ul>
            </div>
          </div>
        </div>
        <!-- Status Bar Block - Start -->
        <div class="portletBlock infoBox rounded shadow">
          <div class="columnsOne">
            <div class="columnInner">
              <div class="icon"><img class="ui_icon_sprite widgets inProcess-icon" src="<html:imagesPath/>transparent.png" width="100" height="100" /></div>
              <div class="status statusPopup">
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
       
        <!-- Status Bar Block - End --> 

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
                  <span>${requestFormPopup.serviceRequest.primaryContact.emailAddress } </span></li>
              </ul>
            </div>
            <c:if test="${requestFormPopup.serviceRequest.secondaryContact.firstName != ''}">
            <div class="infoBox columnInner rounded shadow">
              <h4><spring:message code="Details.changeRequestDetails.heading.secondaryContact"/></h4>
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
                  <span id="costCenter_chngPopup">${requestFormPopup.serviceRequest.costCenter }</span></li>
                <li>
                  <label><spring:message code="requestInfo.label.description"/></label>
                  <span id="description_chngPopup">${requestFormPopup.serviceRequest.addtnlDescription}</span></li>
                <li>
                  <label><spring:message code="requestInfo.label.dateOfChange"/></label>
                  <span>
					<util:dateFormat value="${requestFormPopup.serviceRequest.requestedEffectiveDate }"></util:dateFormat>                  
				  </span></li>
              </ul>
            </div>
          </div>
        </div>
   
     <div class="portletBlock">
<div class="columnsTwo">
  <div class="infoBox rounded shadow"> 
    <!-- Outter table: 1 row & 2 colums -->
    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="assetDetail">
      <tr> 
        <!-- Outter Table > column 1: Product image & Name -->
        <td class="pModel"><ul>
            <li class="center"><img src="${requestFormPopup.serviceRequest.asset.productImageURL}" onError="image_error(this.id);" id="changeImage"/></li>
            <li class="pModelName">${requestFormPopup.serviceRequest.descriptionLocalLang}</li>
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
                    <c:when test="${requestFormPopup.serviceRequest.subArea eq 'HW MADC Install/Decommission'}">
                    <c:forEach items="${requestFormPopup.serviceRequest.parts}" var="deInstalledAsset">
                    <span id="serialNumber">${deInstalledAsset.installSerialNumber}</span></li>
                    </c:forEach>
                    </c:when>
                    <c:otherwise>
                     <span>${requestFormPopup.serviceRequest.asset.serialNumber}</span></li>
                    
                    </c:otherwise>
                    </c:choose>
                  <li>
                    <label><spring:message code="requestInfo.assetInfo.label.productName"/></label>
                      <c:choose>
                    <c:when test="${requestFormPopup.serviceRequest.subArea eq 'HW MADC Install/Decommission'}">
                    <c:forEach items="${requestFormPopup.serviceRequest.parts}" var="deInstalledAsset">
                    <span id="serialNumber">${deInstalledAsset.installProduct}</span></li>
                    </c:forEach>
                    </c:when>
                    <c:otherwise>
                    <span id="productLine">${requestFormPopup.serviceRequest.asset.productTLI}</span></li>
                    
                    </c:otherwise>
                    </c:choose>
                  <li>
                    <label><spring:message code="requestInfo.assetInfo.label.installDate"/></label>
                    <span>${requestFormPopup.serviceRequest.asset.installDate}</span></li>
                  <li>
                    <label><spring:message code="requestInfo.assetInfo.label.ipAddress"/></label>
                    <span>${requestFormPopup.serviceRequest.asset.ipAddress}</span></li>
                  <li>
                    <label><spring:message code="requestInfo.assetInfo.label.hostName"/></label>
                    <span>${requestFormPopup.serviceRequest.asset.hostName}</span></li>
                  <li>
                    <label><spring:message code="requestInfo.assetInfo.label.customerAssetTag"/></label>
                    <span>${requestFormPopup.serviceRequest.asset.assetTag}</span></li>
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
          <span>${requestFormPopup.serviceRequest.asset.assetCostCenter}</span></li>
      </ul>
    </div>
  </div>
  <div class="columnsTwo">
    <div class="infoBox columnInner rounded shadow">
      <h4><spring:message code="requestInfo.cm.manageAsset.heading.customerHierercyLevel"/></h4>
      <ul class="form">
        <li>
          <label><spring:message code="requestInfo.cm.manageAsset.label.assetHierercyLevel"/></label>
          <span>${requestFormPopup.serviceRequest.asset.chlNodeValue}</span></li>
      </ul>
    </div>
  </div>
</div>
<c:if test="${requestFormPopup.serviceRequest.serviceActivityStatus!= null}">
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
                        
                        <c:if test="${requestFormPopup.serviceRequestTechnicianProgress gt 0}">
	                        <table border="0" cellspacing="0" cellpadding="0">
	                          <tr>
	                            <td><spring:message code="serviceRequest.label.inProcess"/></td>
	                            <td><spring:message code="serviceRequest.label.assigned"/></td>
	                            <td><spring:message code="reportStatus.PUBLISHOK"/></td>
	                          </tr>
	                        </table>
                        </c:if>
                        <c:if test="${requestFormPopup.serviceRequestTechnicianProgress==0}">
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
                <c:if test="${requestFormPopup.serviceRequestTechnicianXML!= null}">
                <div id="div_technicianPrintTable">
							<div id="tab_technicianGrid" style="display:block;">
								<div id="div_technicianContainer" class="div-style48;"></div>
    							<div class="pagination" id="activityPaging">
	    				<span id="pagingArea3"></span><span id="infoArea_activity"></span>
	    				</div>
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
<div class="columnsOne">
            <div class="infoBox columnInner rounded shadow">
              <h4><spring:message code="requestInfo.manageAsset.heading.pageCount"/></h4>
              <div  class="collapse" id="statusExpand">
                <div id="div_pageCounts">
					<div id="tab_pageCounts" style="display:block;">
						<div id="pageCounts_container" class="div-style48"></div>
	    				<div class="pagination" id="paginationId">
	    				<span id="pagingAreaPopup"></span><span id="infoArea_pop"></span>
	    				</div>
					</div>
				</div>
              </div>
            </div>
          </div>
          
          
<div class="columnsOne">
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
              <c:when test="${requestFormPopup.serviceRequest.subArea.value eq 'Move Asset' || (requestFormPopup.serviceRequest.asset.moveFromAddress.addressLine1 != null && requestFormPopup.serviceRequest.asset.moveFromAddress.addressLine1 != '')}">
             <li>
						<div>${requestFormPopup.serviceRequest.asset.moveFromAddress.storeFrontName}</div>						
						<util:addressOutput value="${requestFormPopup.serviceRequest.asset.moveFromAddress}"></util:addressOutput>
						</li>					                    
                      
                      <li><label><spring:message code="requestInfo.addressInfo.label.building"/> </label>
						<span>${requestFormPopup.serviceRequest.asset.moveFromAddress.physicalLocation1}</span>
                      </li>
					  <li><label><spring:message code="requestInfo.addressInfo.label.floor"/> </label>
                       <span>${requestFormPopup.serviceRequest.asset.moveFromAddress.physicalLocation2}</span>
                      </li>
					
					  <li><label><spring:message code="requestInfo.addressInfo.label.office"/> </label> 
                    <span>${requestFormPopup.serviceRequest.asset.moveFromAddress.physicalLocation3}</span>
                      </li>
              </c:when>
              <c:otherwise>
	                	<li>
						<div>${requestFormPopup.serviceRequest.asset.installAddress.storeFrontName}</div>						
						<util:addressOutput value="${requestFormPopup.serviceRequest.asset.installAddress}"></util:addressOutput>
						</li>					                    
                      
                      <li><label><spring:message code="requestInfo.addressInfo.label.building"/> </label>
						<span>${requestFormPopup.serviceRequest.asset.installAddress.physicalLocation1}</span>
                      </li>
					  <li><label><spring:message code="requestInfo.addressInfo.label.floor"/> </label>
                       <span>${requestFormPopup.serviceRequest.asset.installAddress.physicalLocation2}</span>
                      </li>
					
					  <li><label><spring:message code="requestInfo.addressInfo.label.office"/> </label> 
                    <span>${requestFormPopup.serviceRequest.asset.installAddress.physicalLocation3}</span>
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
              <c:if test="${requestFormPopup.serviceRequest.subArea.value eq 'Move Asset' ||  (requestFormPopup.serviceRequest.asset.moveFromAddress.addressLine1 != null && requestFormPopup.serviceRequest.asset.moveFromAddress.addressLine1 != '')}">
	                	<li>
						<div>${requestFormPopup.serviceRequest.asset.installAddress.storeFrontName}</div>						
						<util:addressOutput value="${requestFormPopup.serviceRequest.asset.installAddress}"></util:addressOutput>
						</li>					                    
                      
                      <li><label><spring:message code="requestInfo.addressInfo.label.building"/> </label>
						<span>${requestFormPopup.serviceRequest.asset.installAddress.physicalLocation1}</span>
                      </li>
					  <li><label><spring:message code="requestInfo.addressInfo.label.floor"/> </label>
                       <span>${requestFormPopup.serviceRequest.asset.installAddress.physicalLocation2}</span>
                      </li>
					
					  <li><label><spring:message code="requestInfo.addressInfo.label.office"/> </label> 
                    <span>${requestFormPopup.serviceRequest.asset.installAddress.physicalLocation3}</span>
                      </li>					                    
                     </c:if>
					</ul>
            </div>
          </div>
        </div>
		</div>          
          <c:if test="${requestFormPopup.serviceRequest.subArea eq 'HW MADC Install/Decommission'}">
	
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
							 <c:forEach items="${requestFormPopup.serviceRequest.parts}" var="deInstalledAsset">
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
          <c:if test="${requestFormPopup.serviceRequest.subArea.value ne 'Decommission Asset' &&  requestFormPopup.serviceRequest.subArea.value ne 'Deregister Asset'}">
<div class="columnsOne">
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
						
						<c:set var="deviceContactItem" value="${requestFormPopup.serviceRequest.asset.deviceContact}" />
						
						<c:forEach items="${deviceContactItem}" var="deviceContactList" varStatus="counterContact" begin="0">
						<tr class="<c:if test="${counterContact.index % 2 ne 0}">altRow</c:if>">
						<c:forEach items="${deviceContactTypeList}" var="deviceContactTypeList">
							<c:if test="${deviceContactTypeList.key == deviceContactList.deviceContactType}">
							<td class="middle"><div id="deviceContactType${counterContact.index+1}">${deviceContactTypeList.value}</div></td>						
							</c:if>
							</c:forEach>
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
              <p class="w70p multiLine"><span id="notes">${requestFormPopup.serviceRequest.notes}</span></p>
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
              		<c:forEach items="${requestFormPopup.attachList}" var="entry" >
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
          
          
  <!-- END -->
</div>

<script type="text/javascript">
var SRArea='${requestFormPopup.serviceRequest.area.value }';
var SRSubArea='${requestFormPopup.serviceRequest.subArea.value }';
var coveredService='${coveredService}';
if((SRArea=="MADC" && SRSubArea=="Install Asset") || (SRArea=="Data Management" && SRSubArea=="Register Asset") 
		|| (SRArea=="Install" && SRSubArea=="BAU" && (coveredService =="HW BAU Install" || coveredService =="HW BAU Install Change"))){
	subArea="Install Hardware Only";
	jQuery('#installInfo').show();
	jQuery('#installAddress').show();
	jQuery('#moveInfo').hide();
	jQuery('#moveAddress').hide();
	jQuery('#pickUpInfo').hide();
	jQuery('#pickupAddress').hide();
	}
else if((SRArea=="MADC" && SRSubArea=="Move Asset") || (SRArea=="Data Management" && SRSubArea=="Change Asset Data")
		||(SRArea=="Install" && SRSubArea=="BAU" && coveredService == "HW BAU Install Move")){
	if(!(SRArea=="Data Management" && SRSubArea=="Change Asset Data")){
	jQuery('#moveToAddressSplit').show();
	}
	jQuery('#installInfo').hide();
	jQuery('#installAddress').hide();
	jQuery('#moveInfo').show();
	jQuery('#moveAddress').show();
	jQuery('#pickUpInfo').hide();
	jQuery('#pickupAddress').hide();
	}
else if((SRArea=="MADC" && SRSubArea=="Decommission Asset") || (SRArea=="Data Management" && SRSubArea=="Deregister Asset")
		|| (SRArea=="Install" && SRSubArea=="BAU" && coveredService == "HW BAU Install Decommission")){
		subArea="Decommission Request";
		jQuery('#installInfo').hide();
		jQuery('#installAddress').hide();
		jQuery('#moveInfo').hide();
		jQuery('#moveAddress').hide();
		jQuery('#pickUpInfo').show();
		jQuery('#pickupAddress').show();
	
}
<c:if test="${requestFormPopup.serviceRequest.subArea.value eq 'Move Asset' ||  (requestFormPopup.serviceRequest.asset.moveFromAddress.addressLine1 != null && requestFormPopup.serviceRequest.asset.moveFromAddress.addressLine1 != '')}">
jQuery('#moveToAddressSplit').show();
</c:if>
function changeRqstDetails(ServiceRqstNumber,ServiceRqstType) {
	//alert('in changeRequestDetailsPopup ServiceRqstNumber='+ServiceRqstNumber+' ServiceRqstType='+ServiceRqstType);
	jQuery('#ChangeRqstNumber').html(ServiceRqstNumber);
}
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
pageCountsGrid.enablePaging(true, 5, 10, "pagingAreaPopup", true,"infoArea_pop");
pageCountsGrid.setPagingSkin("bricks");
pageCountsGrid.setSkin("light");
//alert('${requestFormPopup.srStausXML}');
pageCountsGrid.setColumnHidden(3,true);
pageCountsGrid.loadXMLString('${pageCountsString}');
//page counts end
var barImages = { 0: '<html:imagesPath/>progressBar/progressbg_red.gif',
		26:	'<html:imagesPath/>progressBar/progressbg_orange.gif',
   		51: '<html:imagesPath/>progressBar/progressbg_yellow.gif',
		76: '<html:imagesPath/>progressBar/progressbg_green.gif'};
	barImagesCancel = {0: '<html:imagesPath/>progressBar/progressbg_black.gif'};
jQuery('#div_progressBar').progressBar('${requestFormPopup.reqProgress}', { width:480,speed:25,increment:1,height:12,showText: false, boxImage: '<html:imagesPath/>progressBar/progressbar.gif', barImage:barImages} );

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
	<c:if test="${requestFormPopup.serviceRequest.asset.productImageURL == '' || requestFormPopup.serviceRequest.asset.productImageURL == null}">
	document.getElementById("changeImage").src = '<html:imagesPath/>dummy_noimg.jpg';
	</c:if>
	image_error= function (){
			document.getElementById("changeImage").src = '<html:imagesPath/>dummy_noimg.jpg'; 
		
	}
	<c:if test="${requestFormPopup.serviceRequestTechnicianXML !=null}">	
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
		SRTechnicianGrid.enablePaging(true, 5, 10, "pagingArea3", true,"infoArea_activity");
		SRTechnicianGrid.setPagingSkin("bricks");
		SRTechnicianGrid.setSkin("light");
		SRTechnicianGrid.loadXMLString('${requestFormPopup.serviceRequestTechnicianXML}'); 
		//SRTechnicianGrid.setSortImgState(true, 0, 'asc');
		</c:if>
		<c:if test="${requestFormPopup.serviceRequest.serviceActivityStatus !=null}">
		if('${requestFormPopup.serviceRequestTechnicianProgress}'==0){
			jQuery("#div_technicianProgressBar1").progressBar(100 , { width:480,speed:25,increment:1,height:12,showText: false, boxImage: '<html:imagesPath/>progressBar/progressbar.gif', barImage : barImagesCancel} );
		}else{
	  		jQuery("#div_technicianProgressBar1").progressBar('${requestFormPopup.serviceRequestTechnicianProgress}', { width:480,speed:25,increment:1,height:12,showText: false, boxImage: '<html:imagesPath/>progressBar/progressbar.gif', barImage : barImages} );
		}
</c:if>
</script>
