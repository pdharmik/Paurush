<%-- Added for CI BRD13-10-02--STARTS --%>
<%@page import=" static com.lexmark.constants.LexmarkConstants.gridConfigurationValues" %>
<%@page import=" static com.lexmark.constants.LexmarkConstants.JSON_COMBO_FILTER"%>
<%@page import=" static com.lexmark.constants.LexmarkConstants.JSON_RESOURCE_URL"%>
<%@page import=" static com.lexmark.constants.LexmarkConstants.gridSavingParams" %>
<%-- Added for CI BRD13-10-02--ENDS --%>
<%@ page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>
<%--MPS 2.1 Changes Added for address cleansing --%>
<%@page import ="static com.lexmark.services.util.ChangeMgmtConstant.ADDRESSCLEANSEFIELDS_OUTPUT"%>
<%--ends --%>
<%@ include file="/WEB-INF/jsp/include.jsp" %>

<%-- Added for CI BRD13-10-02 --%>
<jsp:include page="/WEB-INF/jsp/includeGrid.jsp"></jsp:include>

<%@ page import = "com.lexmark.services.util.LexmarkSPOmnitureConstants" %>
<%@ include file="/WEB-INF/jsp/includeDatePicker.jsp"%>
<%-- Below jsp added for CI BRD 13-10-02--%>
<%@ include file="/WEB-INF/jsp/common/reqDetailsPopUp.jsp" %>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/mps.css"/>
<!--[if IE 7]>
	<style type="text/css"><%@ include file="/WEB-INF/css/mps_ie7.css" %></style>
	<style>.columnsTwoIe{width:49%!important;}</style>
<![endif]-->
<script type="text/javascript">	
	<%@ include file="../../../js/commonAddress.js"%>	
</script>
<style>
.reqHeight{height:25px;}
</style>
<style type="text/css">
#pageCountsTable th {
    background-color: #e6e6f0;
    padding: 10px;
}
.rowspace td{
padding: 10px 7px 9px 0;
}
</style>

<script type="text/javascript" src="<html:rootPath/>js/expand.js"></script>
<portlet:actionURL var="submitAssetOrderURL">
	<portlet:param name="action" value="submitAssetOrder"/>
</portlet:actionURL>
<portlet:resourceURL var="storeBillTo" id="storeBillTo"></portlet:resourceURL>
<portlet:resourceURL var="getBillToAddressList" id="getBillToAddressList"></portlet:resourceURL>

<portlet:renderURL var="addressListPopUpUrl" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>">
	<portlet:param name="action" value="showAddressListPopUp"></portlet:param>
</portlet:renderURL>

<%--Added for CI--%>
<%-- Below div opens up as popup with request detail--%>
	<div id="dialogChangeDetails" style="display: none;"></div>
	<div id="dialogSupplyDetails" style="display: none;"></div>
	<div id="serviceRequestDetailDiv" style="display: none;"></div>
	

<% request.setAttribute("subTabSelected","createNewRequest"); %>
<jsp:include page="../common/subTab.jsp"></jsp:include> 
<jsp:include page="../common/validationMPS.jsp" />
<div id="dialogAddress" style="display: none;"></div>
<div class="main-wrap">
	<div class="content">
		<div id="content-wrapper">
			<div class="journal-content-article">
				<h1><spring:message code="requestInfo.heading.suppliesRequests"/></h1>
				<h2 class="step"><spring:message code="requestInfo.heading.orderSupplies"/></h2>
			</div>
			<!-- Validation Errors displayed in the below div -->
           <spring:hasBindErrors name="assetDetailPageForm">
				<div class="error" id="assetDetailErrors">
					<c:forEach var="assetDetailError" items="${errors.allErrors}">
						<li><strong><spring:message code="${assetDetailError.code}"/></strong></li>
					</c:forEach>
				</div>	
			</spring:hasBindErrors>
			<spring:hasBindErrors name="fileUploadForm">
				<div class="error" id="errors">
					<c:forEach var="error" items="${errors.allErrors}">
						<li><strong><spring:message code="${error.code}"/></strong></li>
					</c:forEach>
				</div>	
			</spring:hasBindErrors>
			<div id="jsValidationErrors" class="error" style="display: none;"></div>
			
			<div id="taxValidationErrors" class="error" style="display: none;">${Error}</div>
			<div class="error" id="errorMsgPopup_CatalogDtl" style="display:none"></div>
			<div id="validationErrors" class="error" style="display: none;"></div>
			<div class="error" id="errorMsg" style="display:none"></div>
			
			<div class="portletBlock">
				<div class="portletBlock center">
					<ul class="processSteps shadow">
						<li class="active"><a href="javascript:onCancelClick();" title="<spring:message code="requestInfo.tooltip.step1SelectAsset"/>"><span><spring:message code="requestInfo.label.one"/></span><spring:message code="requestInfo.link.selectAsset"/></a></li>
						<li class="selected"><a href="#" title="<spring:message code="requestInfo.tooltip.step2OrderDetails"/>"><span><spring:message code="requestInfo.label.two"/></span><spring:message code="requestInfo.link.orderDetails"/></a></li>
						<li><a href="#" title="<spring:message code="requestInfo.tooltip.step3Review"/>"><span><spring:message code="requestInfo.label.three"/></span><spring:message code="requestInfo.link.review"/></a></li>
						<li class="last"><a href="#" title="<spring:message code="requestInfo.tooltip.stepConfirmation"/>"><span><spring:message code="requestInfo.label.four"/></span><spring:message code="requestInfo.link.confirmation"/></a></li>
					</ul>
				</div>
				
			</div>
			<form:form id="assetDetailPageForm" method="post" commandName="assetDetailPageForm" modelAttribute="assetDetailPageForm" action="${submitAssetOrderURL}">
			<form:hidden path="pageSubmitType" id="pageSubmitType"/>
			 
			<div class="portletBodyWrap">
					<h3 class="pageTitle"><spring:message code="requestInfo.heading.orderDetails"/></h3>
       				<p><spring:message code="requestInfo.label.pageHeader"/></p>
					<%@ include file="/WEB-INF/jsp/common/commonContactInfo.jsp"%>
							<hr class="separator" />
							<p class="inlineTitle"><spring:message code="requestInfo.heading.selectedAsset"/></p>
							<!-- Start Selected Asset -->
							<div class="portletBlock infoBox rounded shadow">
								 <!-- Outter table: 1 row & 2 colums -->
          						<table width="100%" border="0" cellspacing="0" cellpadding="0" class="assetDetail detailInfoList">
            						<tr> 
              							<!-- Outter Table > column 1: Product image & Name -->
              							<td class="pModel">
              								<ul>
              									<li class="center"><img src='${assetDetailPageForm.asset.productImageURL}' class='printer'
              									 id="MyPicture" onError="image_error();" width="100" height="100"/></li>
              									 <li class="pModelName">${assetDetailPageForm.asset.descriptionLocalLang}</li>
              									 <li><a href="javascript:onCancelClick();"><spring:message code="requestInfo.link.chooseAdifferentAsset"/></a></li>
              								</ul>
              							</td>
              							<!-- Outter Table > Column 2: Inner table for details -->
             							<td class="pDetail"><!-- Inner table: 4 row2 & 2 colums -->
                							<table width="100%" border="0" cellspacing="0" cellpadding="0">
                  								<tr>
                  									<td class="h4"><spring:message code="requestInfo.heading.assetInformation"/></td>
                  									<td class="h4 separatorV"><spring:message code="requestInfo.heading.serviceAddress"/></td>
                  								</tr>
                  								 <tr>
                    								<td class="infoDsiplay">
                    									<ul class="form roDisplay">
	                    									<li><label><spring:message code="requestInfo.assetInfo.label.serialNumber"/></label><span>${assetDetailPageForm.asset.serialNumber}</span></li>
															<li><label><spring:message code="requestInfo.assetInfo.label.productName"/></label><span>${assetDetailPageForm.asset.productTLI}</span></li>
															<li><label><spring:message code="requestInfo.assetInfo.label.installDate"/></label><span>${assetDetailPageForm.installDate}</span></li>
															<li><label><spring:message code="requestInfo.assetInfo.label.ipAddress"/></label>
															<!-- Hyperlink for Defect 3924 -->
															<span>
															<%--Start: Added for Defect 3924-Jan Release--%>
															<a href="javascript:gotoControlPanel('${assetDetailPageForm.asset.controlPanelURL}')">${assetDetailPageForm.asset.ipAddress}</a>
															<%--End: Added for Defect 3924-Jan Release--%>
															</span></li>
															<li><label><spring:message code="requestInfo.assetInfo.label.hostName"/></label><span>${assetDetailPageForm.asset.hostName}</span></li>
															<li><label><spring:message code="requestInfo.assetInfo.label.customerAssetTag"/></label><span>${assetDetailPageForm.asset.deviceTag}</span></li>
	                    								</ul>
                    								</td>
                    								<td class="separatorV">
                    									<ul class="roDisplay">
                    										 <li><div>${assetDetailPageForm.serviceAddress.storeFrontName}</div>
                    										 <util:addressOutput value="${assetDetailPageForm.serviceAddress}"></util:addressOutput>
													                  
															</li>
                        									<li><span><spring:message code="requestInfo.addressInfo.label.building"/>${assetDetailPageForm.physicalLocationAddress.physicalLocation1}</span></li>
                        									<li><span><spring:message code="requestInfo.addressInfo.label.floor"/>${assetDetailPageForm.physicalLocationAddress.physicalLocation2}</span></li>
                        									<li><span><spring:message code="requestInfo.addressInfo.label.office"/>${assetDetailPageForm.physicalLocationAddress.physicalLocation3}</span></li>
                    									</ul>
                    								</td>
                    							</tr>
                  							</table>
                  						</td>
              						</tr>
              					</table>
              					</div><!-- portletBlock infoBox rounded shadow -->
								<!-- End Selected Asset -->
								
			<%--Request History for Asset (CI) STARTS--%>
		 <div class="portletBlock infoBox rounded shadow">
          <div class="columnsOne">
            <div class="columnInner">
               <h4 class="expand" onclick="show_load();"> <!--show load called for asynshronous-CI7 -->
              <spring:message code="Details.manageAsset.heading.requestHistoryOnThisAsset"/></h4>
              <div  class="collapse">
                       <div class="expand-min">
                      
                       <a href="#grid" id='headerMenuButton' style="display: none;"><spring:message code="requestInfo.link.customizeColumns"/></a> </div>
                       

               			 <div id="gridContainerDiv_all_request_types"  class="gridbox gridbox_light" style="display: none;"></div>
                
				    		<div id="loadingNotification_request_type" class='gridLoading'>
	        					<!--spring:message code='loadingNotification' />&nbsp;&nbsp;--><img src="<html:imagesPath/>gridloading.gif"/>
	    					</div>
				    		<div class="pagination" id="paginationId">
				    		<span id="pagingArea_request_type"></span><span id="infoArea_req_status"></span> 
				    		</div>

					
              </div>
            </div>
          </div>
        </div>
        </div>
        <%--Request History for Asset (CI) ENDS--%>
        
								<!-- Page Counts Start -->
								<jsp:include page="/WEB-INF/jsp/orderSuppliesAssetOrder/pageCountsForAsset.jsp" />
								<!-- Page Counts End -->
								
								<!-- Parts Detail Start -->
								   	<c:if test="${assetDetailPageForm.asset.installationOnlyFlag == true}">
											<p><form:checkbox id="installationOnlyCheck" onclick="hideShowShippingAddress();" path="asset.installationOnlyFlag" />&nbsp;						
										 		<b><spring:message code="requestInfo.label.InstallationOnly"/></b>
											 	<spring:message code="requestInfo.label.InstallationOnlyDescription"/>
											</p>			
									</c:if>	
								<div id="partsToBeInstalled" style="display: none">
								<div class="portletBlock" style="min-width:900px;">
								<div class="columnsOne">
								<div class="infoBox columnInner rounded shadow">
									<h4><spring:message code="requestInfo.label.partsToBeInstalled"/> </h4>
									<ul class="form">
									<li>
										<label><spring:message code="requestInfo.label.partsToBeInstalledDescription"/> </label>
										<span>
											<form:textarea id="partsToBeInstalled1" path="asset.partsToBeInstalled" rows="3" maxlength="2000"/>
										</span>
									</li>
									</ul>
								</div>
								</div>
								</div>
								</div>
								
								
								<c:if test="${assetDetailPageForm.splitterFlag == true}">
								<span style="display: none"><form:input path="dsplPaymentTypeFlag" id="dsplPaymentTypeFlag"/></span>
								<c:if test='${assetDetailPageForm.dsplPaymentTypeFlag == true}' >
								<!-- <div class="portletBodyWrap" > -->
							  	<div class="portletBlock infoBox rounded shadow columnsOne" style="min-width:900px;">
							  		<div class="columnsOne">
							  		<div class="columnInner">
							  		 <c:if test="${assetDetailPageForm.paymentTypes != null}">
									 <div id="paymentTypeAlert">
									 <div style="width:32px;height:auto;float:left"><img src="<html:imagesPath/>attention.png" alt="alert" height="24" width="24"></div>
									 <div style="width:300px;float:left;" class="strong"><spring:message code="requestInfo.alert.enterPaymentType"/></div>
									 <div style="clear:both;"></div>
									 </div>
							  		</c:if>
							  		</div>
									<div class="columnInner">
									
							  		<div><ul class="form">
												<li>
													<label for="paymentType"><spring:message code="requestInfo.label.paymentType"/></label> 
													<span>
													
														<c:choose>
												          <c:when test="${assetDetailPageForm.paymentTypes == null}">
												          		${assetDetailPageForm.selectedPaymentType}
												          		 <span style="display: none"><form:input path="selectedPaymentType" id="selectedPaymentType"/>
												          		 <form:input path="selectedPaymentTypeId" id="selectedPaymentTypeId"/>
												          		 </span>
														  </c:when>	
														  <c:otherwise>
														  		<form:select path="selectedPaymentTypeId" id="selectedPaymentTypeId" onchange="removeErrorMessageForPaymentType(); decideShowPrice(true); ">
											                   		 <form:option value="0" >-<spring:message code="requestInfo.option.select"/>-</form:option>
											                   		 <%-- <form:options items="${assetDetailPageForm.paymentTypes}" /> --%>
											                   		 
											                   		 <c:forEach items="${assetDetailPageForm.paymentTypes}" var="entry">
	                    	 											<form:option value="${entry.key}">${entry.value}</form:option>
															     	 </c:forEach>
											                    </form:select>
											                    <span style="display: none"><form:input path="selectedPaymentType" id="selectedPaymentType"/>
												          		</span>
														  </c:otherwise>
														</c:choose>
													</span>
												</li>
												<li id="billToAddress" style="display:none">
												   <label><spring:message code="requestInfo.heading.billToAddress"/>:</label>
													<span>
														<span id="combo_zone1" name="combo_zone1"></span>
                  										<span id="helpBillToAddress"><img class="helpIcon ui_icon_sprite info-icon"  src="<html:imagesPath/>transparent.png" title="<spring:message code="requestInfo.label.productModel"/>" ></span>
                  										<span id="billToAddressLoading" class="treeLoading" style="display:none;width:auto!important;float:left!important;"><img src="<html:imagesPath/>loading-icon.gif"/></span>
													</span>
												</li>
									</ul></div>
									</div>
									</div>
							  	</div>
							    <!-- </div> -->
							    </c:if>
								</c:if>
								
								<div id="shippingAddressDetails_PartsDetails">
								<p class="inlineTitle"><spring:message code="requestInfo.heading.listOfParts"/></p>
								<p><spring:message code="requestInfo.label.line1a"/> ${assetDetailPageForm.maxPartsToBeOrdered} <spring:message code="requestInfo.label.line1b"/></p>
								<p><spring:message code="requestInfo.label.line2"/></p>
								<%-- <p><em class="note"><spring:message code="requestInfo.label.preferredNote"/></em></p> --%>
									<div class="columnInner">
									<div class="wFull displayGrid columnInnerTable rounded shadow">
										<div id="partListTable"></div>
										<table id="hidePartListTable">
											<thead>
												<tr>
													<th class="w150"><spring:message code="requestInfo.heading.partNumber"/></th>
													<%-- <th class="w10"><spring:message code="requestInfo.heading.preferredPart"/></th> --%>
													<th><spring:message code="requestInfo.heading.description"/></th>													
													<th class="w150"><spring:message code="requestInfo.heading.partType"/></th>
													<!-- MPS Phase 2.1 changes start -->
													<th class="w80 right"><spring:message code="requestInfo.heading.unitPrice"/></th>
													<!-- MPS Phase 2.1 changes end -->																										
													<th class="w80 right"><spring:message code="requestInfo.heading.orderQuantity"/></th>
												</tr>
											</thead>
											<tbody>
												<c:if test="${AssetNotValid == null}">
													<tr><td colspan="5"><spring:message code="requestInfo.message.selectPaymentType"/></td></tr>
												</c:if>
												<c:if test="${AssetNotValid != null}">
													<tr><td colspan="5"><spring:message code="requestInfo.message.noPartsFound"/></td></tr>
												</c:if>
											</tbody>
	            	   					</table>
	            	   					</div>
									</div><!-- columnInner -->
								<!-- Parts Detail End -->
								<!-- Shipping & Billing Information Start -->
								<!-- shipping address div used for hiding or unhiding on choosing the checkbox -->
								
								<p class="inlineTitle"><spring:message code="requestInfo.heading.shippingInformation"/></p>
								<div class="portletBlock">
          							<div class="columnsTwo">
            							<div class="infoBox columnInner rounded shadow">	
            							<h4>           							
            								<a href="${addressListPopUpUrl}" title="<spring:message code="requestInfo.title.selectAddress"/>" id="diffAddressLink" class="lightboxLarge"
            								onclick="return popupAddress(id);"><spring:message code="requestInfo.link.selectADifferentAddress"/></a>
										    <spring:message code="requestInfo.heading.shipToAddress"/>
										</h4>
            								<ul class="roDisplay">
            						<li><div id="pickupAddressNameLbl"></div>  
            						<div id="pickupStoreFrontNameLbl"></div> 
									<div id="pickupAddressLine1Lbl"></div>
									<div id="pickupAddressofficeNumberLbl"></div>
									<div id="pickupAddressLine2Lbl"></div>
									<div id="city_state_zip_popup_span">
									<div style="display:inline;" id="pickupAddressCityLbl"></div>
									<div  style="display:inline;" id="pickupAddresscountyLbl"></div>
									<div style="display:inline;" id="pickupAddressStateLbl"></div>
									<div style="display:inline;" id="pickupAddressProvinceLbl"></div>
									<div style="display:inline;" id="pickupAddressRegionLB"></div>
									</div>
									<div id="pickupAddressPostCodeLbl"></div>
									<div id="pickupAddressCountryLbl"></div></li>
            								</ul>
            								<ul class="form division">
            									 <li>
            									 	<label for="building"><spring:message code="requestInfo.addressInfo.label.building"/> </label>
            									 	<span><form:input id="physicalLocation1" class="w100" path="shipToAddress.physicalLocation1" maxlength="100" /></span>
            									 </li>
            									 <li>
            									 	<label for="floor"><spring:message code="requestInfo.addressInfo.label.floor"/> </label> 
													<span><form:input id="physicalLocation2" class="w100" path="shipToAddress.physicalLocation2" maxlength="100" /></span>
            									 </li>
            									 <li>
            									 	<label for="office"><spring:message code="requestInfo.addressInfo.label.office"/> </label> 
													<span><form:input id="physicalLocation3" class="w100" path="shipToAddress.physicalLocation3" maxlength="100" /></span>
            									 </li>
            								</ul>
            							</div><!-- infoBox columnInner rounded shadow -->
            						</div><!-- columnsTwo -->
	            					<div class="columnsTwo">
	            						<div class="infoBox columnInner rounded shadow">
	            							<h4><spring:message code="requestInfo.heading.orderInstructions"/></h4>
	            							<ul class="form">
	               								<li>
	               									<label for="desc"><spring:message code="requestInfo.label.specialInstructions"/></label> 
	               									<span class="multiLine">
	               									
	               									<!-- Added for "Special Instruction" Textarea -- CI17_BRD_18 -- Start-->
													
													<form:textarea id="specialInstruction" rows="3" path="specialInstruction" maxlength="255"/>
													
													<!-- Added for "Special Instruction" Textarea -- CI17_BRD_18 -- End-->
													
													<img class="helpIcon ui_icon_sprite info-icon"  src="<html:imagesPath/>transparent.png"
													title="<spring:message code="requestInfo.tooltip.specialInstruction"/>" 
													></span>
													<em class="note"><spring:message code="requestInfo.label.note1"/></em>
	               								</li>
	               																			
												
												<c:if test="${assetDetailPageForm.expediteOrderAllowed == 'true'}">
												<li>
													<label for="expOrder"><spring:message code="requestInfo.label.requestExpediteOrder"/></label> 
													<span><form:checkbox id="requestExpediteOrder" path="requestExpediteOrder"/></span>
													<em class="note"><spring:message code="requestInfo.label.expediteShippingNote"/></em>
												</li>
												</c:if>
																							
												
												<li>
													<label for="date1"><spring:message code="requestInfo.label.requestedDeliveryDate"/></label> 
													<span>
													<form:input id="requestedDeliveryDate" class="w100"  path="requestedDeliveryDate" maxlength="22" readonly="true"  onchange="shwDate1();" onblur="shwDate1();"/>
													 
													<img class="ui_icon_sprite calendar-icon" src="<html:imagesPath/>transparent.png" onclick="showCal('requestedDeliveryDate', convertDateToString(new Date().addDays(null)), null); " />
													
													&nbsp;&nbsp;<img id="imgDate2" class="ui_icon_sprite trash-icon" src="<html:imagesPath/>transparent.png"  height="17px" width="17px" onClick="removeDate1();" />
													
													&nbsp;&nbsp;<img class="helpIcon ui_icon_sprite info-icon"  src="<html:imagesPath/>transparent.png" title="<spring:message code="requestInfo.tooltip.requestedDeliveryDate"/>" >
													</span>
												</li>
	               							</ul>	
	            						</div><!-- infoBox columnInner rounded shadow -->
	            					</div><!-- columnsTwo -->
            					</div><!-- portletBlock -->
            					</div><!-- End of Shipping address div  -->
            					<!-- Hidden fields to bind the address data with form -->
						         <span style="display: none">
						         	<form:input id="pickupAddressId" path="shipToAddress.addressId" />
						         	<form:input id="pickupStoreFrontName" path="shipToAddress.storeFrontName" />
						         	<form:input id="pickupAddressName" path="shipToAddress.addressName" />
							     	<form:input id="pickupAddressLine1" path="shipToAddress.addressLine1" /> 
							        <form:input id="pickupAddressLine2" path="shipToAddress.addressLine2" />
							        <form:input id="pickupAddressCity" path="shipToAddress.city" />
							        <form:input id="pickupAddressState" path="shipToAddress.state" />
							        <form:input id="pickupAddressProvince" path="shipToAddress.province" />
							        <form:input id="pickupAddressPostCode" path="shipToAddress.postalCode" />
							        <form:input id="pickupAddressCountry" path="shipToAddress.country" />
							        <form:input path="listOfFileTypes" id="listOfFileTypes"/>
									<!-- Below Fields for Cleansing address Don't change the
									input id's as it is related to ChangeMgmtConstant.ADDRESSCLEANSEFIELDS_OUTPUT 
									 -->
									
									<form:input id="pickupAddresscounty" path="shipToAddress.county" />
							        <form:input id="pickupAddressofficeNumber" path="shipToAddress.officeNumber" />
							        <form:input id="pickupAddressstateCode" path="shipToAddress.stateCode" />
							        <form:input id="pickupAddressstateFullName" path="shipToAddress.stateFullName" />
							        <form:input id="pickupAddressdistrict" path="shipToAddress.district" />
							        <form:input id="pickupAddressregion" path="shipToAddress.region" />
							        <form:input id="pickupAddresslatitude" path="shipToAddress.latitude" />
							        <form:input id="pickupAddresslongitude" path="shipToAddress.longitude" />
							        <form:input id="pickupAddresscountryISOCode" path="shipToAddress.countryISOCode" />
							        <form:input id="pickupAddresssavedErrorMessage" path="shipToAddress.savedErrorMessage" />
							        <form:input id="pickupAddressisAddressCleansed" path="shipToAddress.isAddressCleansed"/>
							        
									<!-- Ends Cleansing address -->
									<form:input path="attMaxCount" id="attMaxCount"/> 
									
									<form:input id="relatedServiceRequestedNumber" path="relatedServiceRequestedNumber" />
									<form:input id="relatedServiceRequestedRowId" path="relatedServiceRequestedRowId" />
									
									<!-- MPS 2.1 fields added -->
									<form:input path="noOfPart" id="noOfPart"/> 
									<form:input path="showPriceFlag" id="showPriceFlag"/>
									<form:input path="poNumberFlag" id="poNumberFlag"/>
									<form:input path="creditNumberFlag" id="creditNumberFlag"/>
									<form:input path="finalShowPriceFlag" id="finalShowPriceFlag"/>  
									<form:input path="finalCreditFlag" id="finalCreditFlag"/>
									<form:input path="finalPOFlag" id="finalPOFlag"/>
									<form:input path="finalTaxCalcFlag" id="finalTaxCalcFlag"/>
									<form:input path="splitterFlag" id="splitterFlag"/>
									<form:input path="salesOrganisation" id="salesOrganisation"/>	
									<form:input path="contractNumber" id="contractNumber"/>	
									
									<input type="text" name="pageCountTypeID" id="pageCountTypeID" />
       								<input type="text" name="pageCountsDateID" id="pageCountsDateID" />
     							    <input type="text" name="pageCountValueID" id="pageCountValueID" />
     							    <input type="text" name="pageCountValue" id="pageCountValue" value="${pageCountsOriginalval}" />
     							    <input type="text" name="pageCountDateValid" id="pageCountDateValid" value="${pageCountsOriginaldate}" />												
							     </span>
            					
            					
								<!-- Shipping & Billing Information End -->
								<!-- Payment Details Start -->
								<div class="portletBlock" style="display: none;" id="paymentBlock">
									<div class="columnsOne">
										<div class="infoBox columnInner rounded shadow">
											<h4><spring:message code="requestInfo.heading.paymentDetails"/></h4>
											<ul class="form">
                								<li>
												 	<label for="poNo"><spring:message code="requestInfo.label.poNumber"/></label> 
													<span><form:input size="10" id="poNumber" path="asset.poNumber" maxlength="100"/> 
														<img class="helpIcon ui_icon_sprite info-icon"  src="<html:imagesPath/>transparent.png" 
														title="<spring:message code="requestInfo.tooltip.ponumber"/>" >
													</span>		
												</li>
											</ul>
										</div><!-- infoBox columnInner rounded shadow -->
									</div><!-- columnsOne -->
								</div><!-- portletBlock -->
								<!-- Payment Details End -->
								<!-- Add Notes Start -->
													<span style="display: none;">
													<input type="text" name="attachmentDescriptionID" id="attachmentDescriptionID" />
													</span>
													</form:form>
													<p class="inlineTitle"><spring:message code="requestInfo.label.attachFiles"/></p>
		
        											<jsp:include page="/WEB-INF/jsp/common/commonAttachment.jsp" /><!-- infoBox -->
												
								<!-- Add Notes End -->
								<form:form id="dummyForm" name="dummyForm" method="post">
								<div class="buttonContainer">
								<c:if test="${AssetNotValid == null}">
									<button class="button_cancel" type="button" onclick="onCancelClick()"><spring:message code="button.back"/></button>
									&nbsp;
									<button class="button_cancel" type="button" onclick="javascript:draftAssetOrder();"><spring:message code="button.saveAsDraft"/></button>
									&nbsp;
									<c:if test="${fleetManagementFlag != true }">
									<button class="button_cancel" type="button" onclick="javascript:redirectToHistory('others');"><spring:message code="button.cancel"/></button>
									</c:if>
									<c:if test="${fleetManagementFlag == true }">
									<button class="button_cancel" type="button" onclick="onCancelClick();"><spring:message code="button.cancel"/></button>
									</c:if>
									&nbsp;
									<button class="button" type="button" onclick="javascript:submitAssetOrder();"><spring:message code="button.continue"/></button>
								</c:if>
								<c:if test="${AssetNotValid != null}">
									<button class="button_cancel" type="button" onclick="onCancelClick()"><spring:message code="button.back"/></button>
								</c:if>
								</div><!-- buttonContainer -->
								</form:form>
								<input type="hidden" id="fileCount" />
								<div id="modaldialogbox" style="min-width: 450px;" >
    								<div class="columnInner" id="loadingDialog"><div class="columnsTwo w50"><img src="/LexmarkOPSPortal/images/green-check.png" style="width:50px; height:50px" ></img></div>
    								<div class="columnsTwo w70p">
      									<p><spring:message code="requestInfo.message.draftMessage1"/> ${srnumber}<br/>
      									<spring:message code="requestInfo.message.draftMessage2"/></p>
      								</div>
      								<div class="buttonContainer popupBtnCont">
      								<button id="draftPopupOk" class="button" onclick="dialog.dialog('close');"><spring:message code="button.ok"/></button>
      								</div>
      								</div>
								</div>
				</div><!-- portletBodyWrap -->
			</div><!-- content-wrapper -->
	</div><!-- content -->
<!-- main-wrap -->
<jsp:include page="/WEB-INF/jsp/common/dynamicGridInitialize.jsp"></jsp:include> <%--Added for CI BRD13-10-02--%>

<form action="fleet-management" id="backFormMap" method="post">
	<input type="hidden" name="backJson" id="backJson" value="${assetDetailPageForm.backToMap}"/>
</form>
<script type="text/javascript">
var pageType = "assetDetails";
var portlet_name="<%=LexmarkSPOmnitureConstants.ORDERSUPPLIESASSETDETAIL %>";
var action_primary="<%=LexmarkSPOmnitureConstants. ORDERDETAILSELECTDIFFERENTCONTACT%>";
var action_secondary="<%=LexmarkSPOmnitureConstants.ORDERDETAILADDADDITIONALCONTACT %>";
	//added by Sagar
	<c:if test="${assetDetailPageForm.serviceRequest.expediteOrder == true}">
		jQuery('#requestExpediteOrder').attr('checked','checked');
	</c:if>


		
var dialog;
var linkId;
var draftConfirmation = "${draftConfirmation}";
var dialog;
var installationOnlyIsChecked;
var validationFlag;
var orderQtyFlag = true;

//Added for CI BRD13-10-02--START
var assetRowId1="${assetDetailPageForm.asset.assetId}";
var accountId='${assetDetailPageForm.asset.account.accountId}';
var linkClicked=1;
var timezoneOffsetServiceRequest="";
//Added for CI BRD13-10-02--END
var combo = "";


if(draftConfirmation == "draftConfirmation"){
	popupDialogDraft();
	
}else{
	document.getElementById("modaldialogbox").style.display = 'none';
}

function popupDialogDraft(){
	dialog = jQuery("#modaldialogbox").dialog({
		autoOpen: false,
		open: function() {},
		modal: true,
		closeOnEscape: false,
		title: '<spring:message code="requestInfo.message.draftSaved"/>',
		resizable: false,
		position: "center",
		width: 450,
		height: 400
		
		});
	
	dialog.dialog("open");
	
	if (window.PIE) {
        document.getElementById("draftPopupOk").fireEvent("onmove");
    }
    
	return false;
}
var pageCountTypeListArray= new Array(); 
var pageCountsDateListArray= new Array(); 
var pageCountValueListArray= new Array(); 
jQuery(document).ready( function() {
	
	//for page counts start
	jQuery('#pageCountValue').val('${pageCountsOriginalval}');
	jQuery('#pageCountDateValid').val('${pageCountsOriginaldate}');
	
	  <c:if test="${assetDetailPageForm.pageCountsList != null && assetDetailPageForm.pageCountsList !=''}">
   	 var i=0;   	 
	  <c:forEach items="${assetDetailPageForm.pageCountsList}" var="item">
	  pageCountTypeListArray[i]= '${item.name}';
	  pageCountsDateListArray[i]= '${item.date}';
	  pageCountValueListArray[i]= '${item.count}';
	  i++;
	  </c:forEach>
	  
		for(var j=0;j<pageCountTypeListArray.length;j++){
			var firstRow='<label id=\"select_id_'+j +'" readonly=\"readonly\">'+pageCountTypeListArray[j]+'</label>';
			if (j%2==0){
				  altclass="ev_light";
			  }else{
				  altclass="odd_light";
			  }
		//var secondRow="<div class=\"w160 floatL\"><input type=\"text\" value=\""+ pageCountsDateListArray[j] +"\" readonly=\"true\" style=\"float:left;\" id=\"rwid_"+ j +"\" class=\"w150\" /><img id=\"img\" class=\"cal_date\" src=\"" + "/LexmarkServicesPortal/images/ic_cal.png" + "\" onClick=\"showCal('rwid_"+ j +"' , 'Date', 'Date', true);\" onFocus=\"this.className='';\"></div>"
		var secondRow="<div class=\"w160 floatL\"><input type=\"text\" value=\""+ pageCountsDateListArray[j] +"\" readonly=\"true\" style=\"float:left;\" id=\"rwid_"+ j +"\" class=\"w150\" onchange=\"shwDateCommon('rwid_"+ j +"', 'pageCountsDateDelete"+ j +"');\" onblur=\"shwDateCommon('rwid_"+ j +"', 'pageCountsDateDelete"+ j +"');\" /><img id=\"img\" class=\"cal_date ui_icon_sprite calendar-icon\" src=\"" + "/LexmarkServicesPortal/images/transparent.png" + "\" onClick=\"showCal('rwid_"+ j +"' , 'Date', 'Date', true);\" onFocus=\"this.className='';\"><img id=\"pageCountsDateDelete"+ j +"\" class=\"ui_icon_sprite ui-icon-closethick\" src=\"<html:imagesPath/>transparent.png\" height=\"17px\" width=\"17px\" onClick=\"removeDateCommon('rwid_"+ j +"', 'pageCountsDateDelete"+ j +"');\" /></div>";
		var thirdRow="<input type=\"text\" value=\""+ pageCountValueListArray[j] +"\" id='newCount_"+j+"' class=\"w150\"/>";
		var forthRow="<a href=\"javascript:deleteGridRow();\"><img class=\"ui_icon_sprite trash-icon\" style=\"float:none;\" src=\'"+"/LexmarkServicesPortal/images/transparent.png"+"'  width=\"15\" height=\"15\"/>";
		//pageCountsGrid.addRow(pageCountsGrid.uid(), [firstRow,secondRow,thirdRow,forthRow],pageCountsGrid.getRowsNum());
		jQuery('#pageCountsTable tbody').append("<tr id=\"rowCount_"+j+"\""+"class=\"rowspace "+altclass+"\" >"+
							"<td>" +firstRow +"</td>"+"<td>"+secondRow +"</td>"+
							"<td colspan=\"2\">"+thirdRow +"</td>"+"</tr>");
		if(jQuery("#rwid_"+ j).val()=='') {
			jQuery("#pageCountsDateDelete"+ j).hide();
			}
		}
		 //id=pageCountsGrid.getRowsNum();
		 id = jQuery('#pageCountsTable tbody').children('tr').length;
	  </c:if>
	//for page counts end
	
	var primaryContactArray= ["primaryFirstName","primaryLastName","primaryWorkPhone","primaryEmailAddress"]; 
	var count=0;
	for(i=0;i<primaryContactArray.length;i++){
			if(jQuery("#"+primaryContactArray[i]).val()==null || jQuery("#"+primaryContactArray[i]).val()==''){
				count++;
				}
		}
	if(count==4){
	useMyInfoAsPrimaryContact();
	}
	//jQuery("h4.expand").toggler();
	if(jQuery('#attachmentDescription').val() != '' && jQuery('#attachmentDescription').val() != null){
		jQuery('#showAttachment').show();
	}
	//Show the primary address
	var currentURL = window.location.href;
	
	
	
	if(currentURL.indexOf('/partner-portal') != -1)
	{	
		jQuery('#topnavigation li a').each(function(){
			  if(jQuery(this).attr('href')=="/group/partner-portal/customer-requests")
			  jQuery(this).parent().addClass('selected');
			  });
	}
	jQuery("#pickupStoreFrontNameLbl").html(document.getElementById("pickupStoreFrontName").value);
	document.getElementById("pickupAddressLine1Lbl").innerHTML=document.getElementById("pickupAddressLine1").value;
	jQuery("#pickupAddressofficeNumberLbl").html(document.getElementById("pickupAddressofficeNumber").value);
	if(document.getElementById("pickupAddressLine2").value !="" && document.getElementById("pickupAddressLine2").value !=null){
		jQuery("#pickupAddressLine2Lbl").html(document.getElementById("pickupAddressLine2").value + ',');
		jQuery("#pickupAddressLine2Lbl").show();
		}
	if(document.getElementById("pickupAddressCity").value!='' && document.getElementById("pickupAddressCity").value != null){
		jQuery("#pickupAddressCityLbl").html(document.getElementById("pickupAddressCity").value);
		}
	if(document.getElementById("pickupAddresscounty").value!='' && document.getElementById("pickupAddresscounty").value != null){
		jQuery("#pickupAddresscountyLbl").html(',&nbsp;'+document.getElementById("pickupAddresscounty").value);
		jQuery("#pickupAddresscountyLbl").show();
	}
	if(document.getElementById("pickupAddressState").value!='' && document.getElementById("pickupAddressState").value != null){
		jQuery("#pickupAddressStateLbl").html(',&nbsp;'+document.getElementById("pickupAddressState").value);
	}
	if((document.getElementById("pickupAddressProvince").value!='' && document.getElementById("pickupAddressProvince").value!=' ') && document.getElementById("pickupAddressProvince").value != null){
		jQuery("#pickupAddressProvinceLbl").html(',&nbsp;'+document.getElementById("pickupAddressProvince").value);
		jQuery("#pickupAddressProvinceLbl").show();
	}
	// region changed to district for MPS 2.1
	if((document.getElementById("pickupAddressdistrict").value!=' ' && document.getElementById("pickupAddressdistrict").value!='') && document.getElementById("pickupAddressdistrict").value != null){
		jQuery("#pickupAddressRegionLB").html(',&nbsp;'+document.getElementById("pickupAddressdistrict").value);
		jQuery("#pickupAddressRegionLB").show();
	}
	jQuery("#pickupAddressPostCodeLbl").html(document.getElementById("pickupAddressPostCode").value);
	jQuery("#pickupAddressCountryLbl").html(document.getElementById("pickupAddressCountry").value);
	if((document.getElementById("pickupAddressProvince").value=='' || document.getElementById("pickupAddressProvince").value==' ') || document.getElementById("pickupAddressProvince").value == null){
		jQuery("#pickupAddressProvinceLbl").hide();
	}
	if(document.getElementById("pickupAddresscounty").value=='' || document.getElementById("pickupAddresscounty").value == null){
		jQuery("#pickupAddresscountyLbl").hide();
	}
	// region changed to district for MPS 2.1
	if((document.getElementById("pickupAddressdistrict").value=='' || document.getElementById("pickupAddressdistrict").value==' ') || document.getElementById("pickupAddressdistrict").value == null){
		jQuery("#pickupAddressRegionLB").hide();
	}
	if(document.getElementById("pickupAddressLine2").value=='' || document.getElementById("pickupAddressLine2").value == null){
		jQuery("#pickupAddressLine2Lbl").hide();
	}
	//Added for MPS 2.1
	jQuery('#pickupAddressofficeNumberLbl').html(jQuery('#pickupAddressofficeNumber').val());
	//Ends
	//Changes for JAN release
	<c:if test="${assetDetailPageForm.asset.installationOnlyFlag == true}">
		jQuery('#installationOnlyCheck').attr('checked',false);
		hideShowShippingAddress();
	</c:if>
	//Ends
});

//Script for Request History Grid CI (BRD13-10-02) STARTS
var counter=0;
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

function onSRNmbrClick(serviceRequestNumber, requestType){
	var serviceRqstType=requestType;
	if(serviceRqstType=='Consumables Management')
		serviceRqstType='Consumables_Management';
    if(serviceRqstType=='Fleet Management')
    	serviceRqstType='Fleet_Management';
	
	goToDetailPage(serviceRequestNumber,serviceRqstType);
}


//Script for Request History Grid CI (BRD13-10-02) ENDS 

/*
 * Open the select address popup
 */
function popupAddress(hyperlinkId){
	linkId=hyperlinkId;//This link id is for displaying inner htmls in address
	var addressFlag = "${assetDetailPageForm.addressFlag}";
	showOverlay();
	var url=jQuery('#'+hyperlinkId).attr('href');
	url+="&addressFlag="+addressFlag;
	jQuery('#dialogAddress').load(url,function(){
		dialog=jQuery('#content_addr').dialog({
			autoOpen: false,
			title: jQuery('#'+hyperlinkId).attr('title'),
			modal: true,
			draggable: false,
			resizable: false,
			height: 500,
			width: 950,
			close: function(event,ui){
				hideOverlay();
				dialog=null;
				jQuery('#content_addr').remove();
				}
			});
		jQuery(document).scrollTop(0);
		dialog.dialog('open');
		initializeAddressGrid();
		if (window.PIE) {
            document.getElementById("createNewAddressButton").fireEvent("onmove");
            document.getElementById("cancelAddressButton").fireEvent("onmove");
        }
		});
	return false;
}

if(document.getElementById("errors")!= null){
	var divText = document.getElementById("errors").innerHTML;
	jQuery('#validationErrors',window.parent.document).append(divText);
	window.parent.document.getElementById("validationErrors").style.display = 'block';
	window.parent.scrollTo(0,0);
}

<c:if test="${Error != null}">
	jQuery('#taxValidationErrors').show();	
</c:if>

<c:if test="${AssetNotValid != null}">
	jQuery('#validationErrors').show();	
	jQuery('#validationErrors').append('${AssetNotValid}');
</c:if>
var pageCountType = new Array();
var pageCountsDate = new Array();
var pageCountValue = new Array();
var dateFlag=new Array();
function submitAssetOrder(){
	var k=0;	
	for(i=0;i<=id;i++){
		var idGenerateD="rwid_"+i;
		var idGenerateV="newCount_"+i;
		if(document.getElementById(idGenerateD)!=null &&  document.getElementById(idGenerateV)!=null){
		pageCountsDate[k]=jQuery("#"+idGenerateD).val();
		pageCountValue[k]=jQuery("#"+idGenerateV).val();
		k++;
		}
		
	}
	pageCountType=pageCountTypeListArray;
	jQuery('#attachmentDescriptionID').val(jQuery('#attachmentDescription').val());
	
	validationsBeforeSubmit();
	pageCountValidate(jQuery('#pageCountsTable tbody').children('tr').length);
	if (dateFlag!=''){
		for (var i=0;i<dateFlag.length;i++){
			jQuery('#rwid_'+dateFlag[i]).addClass('errorColor');
		}
	}
	if (validationFlag && orderQtyFlag)
	{
			jQuery('#pageCountTypeID').val(pageCountType);
			jQuery('#pageCountsDateID').val(pageCountsDate);
			jQuery('#pageCountValueID').val(pageCountValue);
			jQuery('#pageCountValue').val(pageCountValueListArray);
			jQuery('#pageCountDateValid').val(pageCountsDateListArray);
		document.getElementById("pageSubmitType").value = 'confirmOrderRequest';
		jQuery("#assetDetailPageForm").submit();
	}
	else {
		document.getElementById('errorMsg').style.display = "block";
		jQuery(document).scrollTop(0);
	}
}

function validationsBeforeSubmit(){
	var text_specialInstruction;
	var length_specialInstruction;
	text_specialInstruction=jQuery('#specialInstruction').val();
	length_specialInstruction=text_specialInstruction.length;
	var text_addtnlDescription;
	var length_addtnlDescription;
	text_addtnlDescription=jQuery('#addtnlDescription').val();
	length_addtnlDescription=text_addtnlDescription.length;
	var noOfPart = document.getElementById('noOfPart').value;//"${assetDetailPageForm.noOfPart}";
	document.getElementById("jsValidationErrors").innerHTML = '';
	document.getElementById("jsValidationErrors").style.display = 'none';
	document.getElementById("validationErrors").innerHTML = '';
	document.getElementById("validationErrors").style.display = 'none';
	jQuery('#errorMsg').html('');
	validationFlag = true;
	if(!descValidate(jQuery('#partsToBeInstalled1').val(),"errorMsg")) {
		validationFlag = false;
		jQuery('#partsToBeInstalled1').addClass('errorColor');
	}
	//Addition for CI7 BRD 13-10-18 START
	if(length_specialInstruction>255){
		validationFlag = false;
		jQuery('#errorMsg').append('<li><strong><spring:message code="validation.maxLength.errorMsg"/></strong></li>');
		jQuery('#specialInstruction').addClass('errorColor');
	}
	if(length_addtnlDescription>255){
		validationFlag = false;
		jQuery('#errorMsg').append('<li><strong><spring:message code="validation.maxLength.errorMsg"/></strong></li>');
		jQuery('#addtnlDescription').addClass('errorColor');
	}
	if(jQuery('#attachmentDescription').val().length > 2000){
		validationFlag = false;
		jQuery('#errorMsg').append('<li><strong><spring:message code="requestInfo.validation.attachmentNotes"/></strong></li>');
		jQuery('#attachmentDescription').addClass('errorColor');
	}
	if(orderQtyFlag==false){
		jQuery('#errorMsg').append('<li><strong><spring:message code="requestInfo.label.validation.extra"/></strong></li>');
	}
	//Addition for CI7 BRD 13-10-18 END
	
	if(!installationOnlyIsChecked){
		var selectedPaymentType = jQuery('#selectedPaymentType').val();
		if(selectedPaymentType == "" || selectedPaymentType == "0"){//Validation added for payment type MPS 2.1 changes
			validationFlag = false;
			jQuery('#errorMsg').append('<li><strong><spring:message code="requestInfo.label.validation.mandatorycheck.paymentType"/></strong></li>');
			jQuery('#selectedPaymentType').addClass('errorColor');
			
		}else if(!quantityBlankValidation()){
			validationFlag = false;
			jQuery('#errorMsg').append('<li><strong><spring:message code="requestInfo.label.validation.empty"/></strong></li>');
			for(var i=0;i<noOfPart;i++){
				jQuery('#assetPartListDiv'+i).addClass('errorColor');
			}
		}
		else if(!quantityFormatValidation()){//validateFormat(quantity,'quantity','errorMsg')
			validationFlag = false;
			jQuery('#errorMsg').append('<li><strong><spring:message code="requestInfo.label.validation.number"/></strong></li>');
			//jQuery('#'+queryParam).addClass('errorColor');
		}
		//else if(!maxQuantityValidation()){
			//validationFlag = false;
		//	jQuery('#errorMsg').append('<li><strong><spring:message code="requestInfo.label.validation.extra"/></strong></li>');
		//}

		else if(jQuery('#finalCreditFlag').val()=="false"){
			
			//if(jQuery("#poNumber").val()==null || jQuery("#poNumber").val().trim()==""){
				//validationFlag = false;
				//jQuery("#errorMsg").append('<li><strong><spring:message code="requestInfo.label.enterPO"/></strong></li>');
				//jQuery('#poNumber').addClass('errorColor');
				//}
			//else 
				if(jQuery('#poNumber').val().length > 100){
				jQuery('#errorMsg').append('<li><strong><spring:message code="requestInfo.validation.poNumber"/></strong></li>');
				jQuery('#poNumber').addClass('errorColor');
				validationFlag = false;
				}
		}
		
		
	}


	else if(!quantityFormatValidation()){//validateFormat(quantity,'quantity','errorMsg')
		validationFlag = false;
		jQuery('#errorMsg').append('<li><strong><spring:message code="requestInfo.label.validation.number"/></strong></li>');
		//jQuery('#'+queryParam).addClass('errorColor');
	}
	//else if(!maxQuantityValidation()){
	//	validationFlag = false;
	//	jQuery('#errorMsg').append('<li><strong><spring:message code="requestInfo.label.validation.extra"/></strong></li>');
	//}
	else if(jQuery('#finalCreditFlag').val()=="false"){
		
		//if(jQuery("#poNumber").val()==null || jQuery("#poNumber").val().trim()==""){
			//validationFlag = false;
			//jQuery("#errorMsg").append('<li><strong><spring:message code="requestInfo.label.enterPO"/></strong></li>');
			//jQuery('#poNumber').addClass('errorColor');
			//}
		//else 
			if(jQuery('#poNumber').val().length > 100){
			jQuery('#errorMsg').append('<li><strong><spring:message code="requestInfo.validation.poNumber"/></strong></li>');
			jQuery('#poNumber').addClass('errorColor');
			validationFlag = false;
			}
	}
	//added by partha for address validation
	if(!validateShippingAddress()){ //validate shipping address information
		validationFlag = false;
		jQuery('#errorMsg').append('<li><strong><spring:message code="requestInfo.label.validation.shipAddress"/></strong></li>');
	}
	<%-- Defect 10776  as per Siebel XSD--%>
	
	
		
		if(jQuery('#physicalLocation1').val().length > 100){
			validationFlag = false;
			jQuery('#errorMsg').append('<li><strong><spring:message code="requestInfo.validation.buiding"/></strong></li>');
			jQuery('#physicalLocation1').addClass('errorColor');
			
			}
	
		if(jQuery('#physicalLocation2').val().length > 100){
			jQuery('#errorMsg').append('<li><strong><spring:message code="requestInfo.validation.floor"/></strong></li>');
			jQuery('#physicalLocation2').addClass('errorColor');
			validationFlag = false;
			}
	
		if(jQuery('#physicalLocation3').val().length > 100){
			jQuery('#errorMsg').append('<li><strong><spring:message code="requestInfo.validation.office"/></strong></li>');
			jQuery('#physicalLocation3').addClass('errorColor');
			validationFlag = false;
			}
		
		if(jQuery('#custReferenceId').val().length > 64){
			validationFlag = false;
			jQuery('#errorMsg').append('<li><strong><spring:message code="validation.custReference.length.errorMsg"/></strong></li>');
			jQuery('#custReferenceId').addClass('errorColor');
			}
	
		if(jQuery('#costCenter').val().length > 200){
			validationFlag = false;
			jQuery('#errorMsg').append('<li><strong><spring:message code="validation.costCenter.length.errorMsg"/></strong></li>');
			jQuery('#costCenter').addClass('errorColor');
			
			}
		if(jQuery('#pageCountValue').val() != null && jQuery('#pageCountValue').val() != "" ){
			pageCountValueListArray=jQuery('#pageCountValue').val().split(",");
		}
		if(jQuery('#pageCountDateValid').val() != null && jQuery('#pageCountDateValid').val() != "" ){
			pageCountsDateListArray=jQuery('#pageCountDateValid').val().split(",");
		}
		
		for(var j=0; j<pageCountsDate.length; j++){	
			if(pageCountsDate != null && pageCountsDate !=""){
				if(pageCountsDate != null && pageCountsDate[j] != ""){
					var currentDate=new Date(formatDateToDefault(pageCountsDate[j])).toUTCString();	
					if(new Date(currentDate)>new Date(new Date().toUTCString())){					
						jQuery("#errorMsg").append("<li><strong>"+"<spring:message code='requestInfo.validation.currentDateExceed'/> " 
								+(j+1)+ "</strong></li>");
						//jQuery('#errorMsg').show();
						//jQuery(document).scrollTop(0);
						dateFlag=dateFlag+j;
						validationFlag=false;
					}
					}
			}
			
			if(pageCountValueListArray[j] != null && pageCountValueListArray[j] != ""){
			if(parseInt(pageCountValueListArray[j])>parseInt(pageCountValue[j])){						
				jQuery("#errorMsg").append("<li><strong>"+"<spring:message code='requestInfo.validation.newPageCountValue'/> "+(j+1)+ "</strong></li>");
				
				//jQuery(document).scrollTop(0);
				validationFlag = false;
		}
			if((pageCountValue[j]-pageCountValueListArray[j])>500000){
				jQuery("#errorMsg").append("<li><strong>"+"<spring:message code='requestInfo.validation.newPageCountValue1'/> " 
						+(j+1)+ "</strong></li>");
				
				//jQuery(document).scrollTop(0);
				validationFlag = false;
		}
			}
		}
		//pageCountValidation();
		
	<%-- Ends Defect 10776 --%>
	
}
//--------- ADDED FOR 7945 ------------- START
function validateShippingAddress(){
	var addrLine1 = document.getElementById("pickupAddressLine1Lbl").innerHTML;
	var addrLine2 = document.getElementById("pickupAddressLine2Lbl").innerHTML;
	var addrCity = document.getElementById("pickupAddressCityLbl").innerHTML;
	var addrState = document.getElementById("pickupAddressStateLbl").innerHTML;
	var addrProb  = document.getElementById("pickupAddressProvinceLbl").innerHTML;
	var addrPostal= document.getElementById("pickupAddressPostCodeLbl").innerHTML;
	var addrCntry = document.getElementById("pickupAddressCountryLbl").innerHTML;

	if(addrLine1 == '' && addrLine2 == '' && addrCity == '' && addrState == '' && addrProb == '' && addrPostal == '' && addrCntry == ''){
		return false;
	}else{
		return true;
	}
}
//--------- ADDED FOR 7945 ------------- END

function quantityBlankValidation(){
	var noOfPart = document.getElementById('noOfPart').value;
	var quantityBlank = false;
	for(var i=0;i<noOfPart;i++){
		if(document.getElementById('assetPartListDiv['+i+'].orderQuantity')!= null){
			var quantity = document.getElementById('assetPartListDiv['+i+'].orderQuantity').value;
			if (quantity==null||quantity==''||quantity==0){
			}else{
				return true;
			}
		}
		if(document.getElementById('assetPartList['+i+'].orderQuantity')!= null){
			var quantity = document.getElementById('assetPartList['+i+'].orderQuantity').value;
			if (quantity==null||quantity==''||quantity==0){
			}else{
				return true;
			}
		}
	}
	return false;
}
function quantityFormatValidation(){
	var noOfPart = document.getElementById('noOfPart').value;//"${assetDetailPageForm.noOfPart}";
	var needReturnAsFalse = false;
	for(var i=0;i<noOfPart;i++){
		if(document.getElementById('assetPartList['+i+'].orderQuantity') != null){
			var quantity = document.getElementById('assetPartList['+i+'].orderQuantity').value;
			if (quantity==null||quantity==''||quantity==0){
			}else{
				if(!validateFormat(quantity,'quantity','errorMsgPopup')){
					jQuery('#assetPartListDiv'+i).addClass('errorColor');
					needReturnAsFalse = true;
				}else if(isNaN(quantity)){
					jQuery('#assetPartListDiv'+i).addClass('errorColor');
					needReturnAsFalse = true;
				}
			}
		}
	}
	if(needReturnAsFalse){
		return false
	}else{
		return true;
	}
}
function maxQuantityValidation(){
	var maxPartToBeOrdered = "${assetDetailPageForm.maxPartsToBeOrdered}";
	var noOfPart = document.getElementById('noOfPart').value;//"${assetDetailPageForm.noOfPart}";
	var needReturnAsFalse = false;
	for(var i=0;i<noOfPart;i++){
		if(document.getElementById('assetPartList['+i+'].orderQuantity') != null){
			var quantity = document.getElementById('assetPartList['+i+'].orderQuantity').value;
			if (quantity==null||quantity==''||quantity==0){
			}else{
				if(quantity>maxPartToBeOrdered){
					jQuery('#assetPartListDiv'+i).addClass('errorColor');
					needReturnAsFalse = true;
				}
			}
		}
	}
	if(needReturnAsFalse){
		return false
	}else{
		return true;
	}
	
}
function removeErrorMessage(){
	document.getElementById('errorMsg').style.display = "none";
	document.getElementById('errorMsg').innerHTML = "";
	var noOfPart = document.getElementById('noOfPart').value;//"${assetDetailPageForm.noOfPart}";
	for(var i=0;i<noOfPart;i++){
		jQuery('#assetPartListDiv'+i).removeClass('errorColor');
	}
}

//Added for MPS 2.1
function removeErrorMessageForPaymentType(){
	document.getElementById('errorMsg').style.display = "none";
	document.getElementById('errorMsg').innerHTML = "";
	jQuery('#selectedPaymentType').removeClass('errorColor');
}

function draftAssetOrder(){
	jQuery('#attachmentDescriptionID').val(jQuery('#attachmentDescription').val());
	var k=0;	
	for(i=0;i<=id;i++){
		var idGenerateD="rwid_"+i;
		var idGenerateV="newCount_"+i;
		if(document.getElementById(idGenerateD)!=null &&  document.getElementById(idGenerateV)!=null){
		pageCountsDate[k]=jQuery("#"+idGenerateD).val();
		pageCountValue[k]=jQuery("#"+idGenerateV).val();
		k++;
		}
		
	}
	pageCountType=pageCountTypeListArray;
	validationsBeforeSubmit();
	pageCountValidate(jQuery('#pageCountsTable tbody').children('tr').length);
	if (validationFlag && orderQtyFlag)
	{
		jQuery('#pageCountTypeID').val(pageCountType);
		jQuery('#pageCountsDateID').val(pageCountsDate);
		jQuery('#pageCountValueID').val(pageCountValue);
		jQuery('#pageCountValue').val(pageCountValueListArray);
		jQuery('#pageCountDateValid').val(pageCountsDateListArray);
		document.getElementById("pageSubmitType").value = 'draftOrderRequest';
		jQuery("#assetDetailPageForm").submit();
	}
	else {
		document.getElementById('errorMsg').style.display = "block";
		jQuery(document).scrollTop(0);
	}
	
}


jQuery(document).ready(function() {
	
	jQuery("#fileInput").css({"cursor":"pointer"});
	jQuery("#processingHint",window.parent.document).css({
	           display:"none"
	   });
	   jQuery("#overlay",window.parent.document).css({
	           display:"none"
	   });
	   
	   //Within jQuery(document).ready Code for MPS 2.1 started
	   	var splitterFlag = "${assetDetailPageForm.splitterFlag}";
	   	if(splitterFlag == "true"){
	   		var paymentType = "${assetDetailPageForm.selectedPaymentTypeId}";
	   	
			if(paymentType != ""){
				
				if(document.getElementById('paymentTypeAlert') != null){
					document.getElementById('paymentTypeAlert').style.display = "none";
				}
				var flowType = '${flowType}';
				if(flowType == ''){
					if(paymentType == "Ship and Bill"){
						getBillToAddressList();						
					}
					doAjaxCallForPrice(paymentType);
				}else if(flowType !="afterSaveAsDraft" || flowType!="fromReviewPage"){
					if(paymentType == "Ship and Bill"){
						getBillToAddressList();		
						doAjaxCallForPrice(paymentType);				
					}else{
						repopulatePartListTable(true);
					}
					
				}else{
					repopulatePartListTable(true);
				}
			}else{
				repopulatePartListTable(false);
			}
	   	}else{
	   		repopulatePartListTable(false);
	   	}
		
	});
	
	<c:if test="${source != null}"> 
			jQuery('#txtFilePath',window.parent.document).val('');
			jQuery('#validationErrors',window.parent.document).empty();
			
			jQuery('#validationErrors',window.parent.document).empty();
			window.parent.document.getElementById("validationErrors").style.display = 'none';
			window.parent.document.getElementById("jsValidationErrors").style.display = 'none';
			
			if(document.getElementById("errors")!= null){
				//jQuery('#error_banner_jsValidationErrors',window.parent.document).empty();
				
				var divText = document.getElementById("errors").innerHTML;
				jQuery('#validationErrors',window.parent.document).append(divText);
				window.parent.document.getElementById("validationErrors").style.display = 'block';
				window.parent.scrollTo(0,0);
			}

	</c:if>


	

	function getFullPath(obj) 
	{ 
	    if(obj) 
	    { 
	        //ie 
	        if (window.navigator.userAgent.indexOf("MSIE")>=1) 
	        { 
	            obj.select(); 
	           var path = document.selection.createRange().text;
			   return path ; 
	        } 
	        else
	        {  
	            if(obj.files) 
	            { 
					return obj.files.item(0).fileName; 
	            } 
	            return obj.value;
	        } 
	    } 
	};
	function move(thisObject){
	    var a= document.getElementById('fileContent');
	    var offset = jQuery('#btnUpload').offset();
	    a.style.left = offset.left + 'px';
	    a.style.top = offset.top + 'px';
	};

	function onCancelClick(){
		 var currentURL = window.location.href;
		 if(currentURL.indexOf('/fleet-management') != -1)
			{	
			 jConfirm('<spring:message code="common.country.alert"/>', "", function(confirmResult) {
					if(confirmResult){
						showOverlay();
						 //window.location.href="<portlet:renderURL><portlet:param name='action' value='default' /></portlet:renderURL>"
						//var lat=document.getElementById("pickupAddresslatitude").value;
						
						//var lon=document.getElementById("pickupAddresslongitude").value;
						var lat="${assetDetailPageForm.shipToAddress.lbsLatitude}";
						
						var lon="${assetDetailPageForm.shipToAddress.lbsLongitude}";
						//var lat=49.474685633790784;
						//var lon=1.112907825559887;
						
										var defaultArea={											
									                lat: lat,
									                lng: lon,							                		           
									               

												
												};
										
						$('#backJson').val(JSON.stringify(defaultArea));
						$('#backFormMap').submit();
					}else{
						return false;
						}
				});
			
			}else{
				 window.location.href="<portlet:renderURL><portlet:param name='action' value='default' /></portlet:renderURL>"
			}
			
		}

	<%--Changes for Phase 2.1--%>
	function addPickupAddress(addressId,addressName,addressLine1,addressLine2,addressCity,addressState,addressProvince,
			addressCountry,addressPostCode,storefrontName,favorite,officeNumber,district,county,countryISO,region,stateCode)
	<%--Ends--%>
	{	
		if(addressProvince == null){
			addressProvince = '';
		}
		if(addressId==null){
			addressId = '-1';
		}
		jQuery("#pickupAddressId").val(addressId);
		jQuery("#pickupStoreFrontName").val(storefrontName);
		jQuery("#pickupAddressName").val(addressName);
		jQuery("#pickupAddressLine1").val(addressLine1);		
		jQuery("#pickupAddressLine2").val(addressLine2);
				
		jQuery("#pickupAddressCity").val(addressCity);
		jQuery("#pickupAddressState").val(addressState);
		jQuery("#pickupAddressProvince").val(addressProvince);
		jQuery("#pickupAddressPostCode").val(addressPostCode);
		jQuery("#pickupAddressCountry").val(addressCountry);
		<%--Changes for Phase 2.1--%>
		jQuery("#pickupAddressofficeNumber").val(officeNumber);
		jQuery("#pickupAddressdistrict").val(district);
		jQuery("#pickupAddresscounty").val(county);
		//added
		jQuery("#pickupAddressregion").val(region);
		jQuery("#pickupAddresscountryISOCode").val(countryISO);
		jQuery("#pickupAddressstateCode").val(stateCode);
		<%--Ends--%>
		//changes for MPS 2.1
		//This is for display purpose pickup address
		jQuery("#pickupStoreFrontNameLbl").html(storefrontName);
		jQuery("#pickupStoreAddressNameLbl").html(addressName);
		jQuery("#pickupAddressLine1Lbl").html(addressLine1);
		jQuery("#pickupAddressofficeNumberLbl").html(officeNumber)		
		if(addressLine2 !='' && addressLine2 !=' ' && addressLine2 != null){
			jQuery("#pickupAddressLine2Lbl").html(addressLine2+",");
			jQuery("#pickupAddressLine2Lbl").show();
			}

		if(addressCity !='' && addressCity !=' ' && addressCity != null){
			jQuery("#pickupAddressCityLbl").html(addressCity);
			}
		
		if(county !='' && county !=' ' && county != null){
			jQuery("#pickupAddresscountyLbl").html(', '+county);
			jQuery("#pickupAddresscountyLbl").show();
			}
		if(addressState !='' && addressState !=' ' && addressState != null){
			jQuery("#pickupAddressStateLbl").html(", "+addressState);
			jQuery("#pickupAddressStateLbl").show();
			}
		if(addressProvince !='' && addressProvince !=' ' && addressProvince != null){
			jQuery("#pickupAddressProvinceLbl").html(', '+addressProvince);
			jQuery("#pickupAddressProvinceLbl").show();
			}
		// region changed to district fot MPS 2.1
		if(district !=' ' && district !=' ' && district != null){
			jQuery("#pickupAddressRegionLB").html(', '+district);
			jQuery("#pickupAddressRegionLB").show();
			}
		jQuery("#pickupAddressPostCodeLbl").html(addressPostCode);		
		jQuery("#pickupAddressCountryLbl").html(addressCountry);
		if(addressProvince =='' || addressProvince ==' ' || addressProvince == null){
			jQuery("#pickupAddressProvinceLbl").hide();
			}
		if(county =='' || county ==' ' || county == null){
			jQuery("#pickupAddresscountyLbl").hide();
			}
		// region changed to district for MPS 2.1
		if(district ==' '||district =='' || district == null){
			jQuery("#pickupAddressRegionLB").hide();
			}
		if(addressLine2 =='' || addressLine2 ==' ' || addressLine2 == null){
			jQuery("#pickupAddressLine2Lbl").hide();
			}
		if(addressState =='' || addressState ==' ' || addressState == null){
			jQuery("#pickupAddressStateLbl").hide();
			}
		//changes for MPS 2.1 end
		
		//BRD 14-06-08 Displaying Physical Location1,2,3
		jQuery("#physicalLocation1").val("");
		jQuery("#physicalLocation2").val("");
		jQuery("#physicalLocation3").val("");		
		//end of BRD 14-06-08
		
		
	}
	<%--Changes for Phase 2.1--%>
	function addServiceAddressElement(addressId,addressName,addressLine1,addressLine2, 
			addressCity,addressState,addressProvince,addressCountry,addressPostCode,storefrontName,physicalLoc1,physicalLoc2,physicalLoc3,favorite,officeNumber,district,county,countryISO,region,stateCode)
	<%--Ends--%>
	{
		<%--Changes for MPS 2.1 --%>
		if(addressId==null || addressId=="")
			jQuery('#pickupAddressisAddressCleansed').val("false");
		else
			jQuery('#pickupAddressisAddressCleansed').val("true");
		
		<%--ENDS Changes for MPS 2.1 --%>
		addressChangeReq=true;
		if(linkId=="diffAddressLink")
		{
			<%--Changes for Phase 2.1--%>
			//call to addConsumablesAddress in the commonAsset.js
			addPickupAddress(addressId,addressName,addressLine1,addressLine2,addressCity,addressState,addressProvince,
					addressCountry,addressPostCode,storefrontName,favorite,officeNumber,district,county,countryISO,region,stateCode);
			<%--Ends--%>
		}
		closeDialog();
	}
	
	
	image_error= function () {
		
		  document.getElementById("MyPicture").src = '<html:imagesPath/>dummy_noimg.jpg'; 
		  
		};
		/*Method gotoControlPanel added for Defect 3924- Jan Release*/
		function gotoControlPanel(url) {
	    	new Liferay.Popup({
	            title: "",
	            position: [400,150], 
	            modal: true,
	            width: 400, 
	            height: 150,
	            xy: [100, 100],
	            onClose: function() {showSelect();},
	        	
	        	url:"<portlet:renderURL windowState='<%= LiferayWindowState.POP_UP.toString() %>'>"+
	               " <portlet:param name='action' value='gotoControlPanel' />"+
	               " </portlet:renderURL>&controlPanelURL=" + url
	            });
	    };
		//Chages for JAN Release Defect 1566	
		<c:if test="${assetDetailPageForm.asset.installationOnlyFlag == true}">
			function hideShowShippingAddress(){
				if(jQuery('#installationOnlyCheck').attr('checked')){
					jQuery('#shippingAddressDetails_PartsDetails').slideUp();
					jQuery('#partsToBeInstalled').slideDown();
					installationOnlyIsChecked=true;
				}
				else{
					jQuery('#shippingAddressDetails_PartsDetails').slideDown();
					jQuery('#partsToBeInstalled').slideUp();
					installationOnlyIsChecked=false;
				}
			}
		</c:if>	
	//Ends JAN release

	//MPS 2.1 code started

	function decideShowPrice(check1){
		var selectedPaymentType = jQuery('#selectedPaymentTypeId').val(); 

		
		if(selectedPaymentType == "0"){
			document.getElementById('paymentTypeAlert').style.display = "block";			
			jQuery('#partListTable').empty();
			jQuery('#hidePartListTable').show();
			jQuery("#billToAddress").hide();
			if(combo != null && combo != ""){
				combo.destructor();	
				combo = "";
			}			
		}else{
			document.getElementById('paymentTypeAlert').style.display = "none";			
			jQuery('#selectedPaymentType').val(jQuery("#selectedPaymentTypeId option:selected").text());
			if(selectedPaymentType == "Ship and Bill"){
				getBillToAddressList();				
			}else{
				jQuery("#billToAddress").hide();
				if(combo != null && combo != ""){
					combo.destructor();	
					combo = "";
				}
					
			}
			doAjaxCallForPrice(selectedPaymentType);			
		}
	}
	
	/*The following function is used for getting the list of Billto Addresses if the Billing model is Ship and Bill.*/
	function getBillToAddressList(){
		var contractNumber = "${assetDetailPageForm.contractNumber}";
		var agreementId = "${assetDetailPageForm.agreementId}";		
		jQuery("#billToAddress").show();
		jQuery("#billToAddressLoading").show();
		jQuery("#combo_zone1").attr('disabled','disabled');		
		
		var billToAddressURL="<portlet:resourceURL id="getBillToAddressList"/>&contractNumber="+contractNumber+"&agreementId="+agreementId+"&random="+Math.random()*99999;		
		window.dhx_globalImgPath = "<html:imagesPath/>comboImgs/";
		combo = new dhtmlXCombo("combo_zone1","billToCombo",218);//changed on june 11,2013 for better look n feel
		combo.setOptionWidth(212);
		/*combo.setOptionHeight(50);*/
		combo.enableOptionAutoHeight(false); //To enable scrollbar on 11June
		combo.loadXML(billToAddressURL,function(){
			jQuery("#combo_zone1").removeAttr('disabled');
			jQuery("#billToAddressLoading").hide();
			jQuery("#helpBillToAddress").show();
		});
		index=combo.getSelectedValue();
		combo.readonly(true);
		
		combo.attachEvent("onChange", function(){			
			index = combo.getSelectedValue();
			if(index!=null && index!=""){
				jQuery.ajax({
					url:'${storeBillTo}',			
					type:'POST',
					data: {index:index},
					success: function(results){
						
					}
				});
				
			}
		});
			
			
	}

	function doAjaxCallForPrice(selPaymentType){
		showOverlay();
		jQuery.ajax({
			url:"<portlet:resourceURL id='retriveretrievePriceURL'></portlet:resourceURL>",
			data:{
					paymentType : selPaymentType,
					showPriceFlag : jQuery('#showPriceFlag').val(),
					creditFlag : jQuery('#creditNumberFlag').val(),
					poFlag : jQuery('#poNumberFlag').val()
				 },
			type:'POST',
			dataType: 'JSON',		
			success: function(results){
				var resultJSON=results;
				
				if(resultJSON !=null){
						if(resultJSON.partListTableContent != null){
							jQuery('#partListTable').empty();
							jQuery('#partListTable').html(resultJSON.partListTableContent);
							jQuery('#noOfPart').val(resultJSON.noOfPart);
							jQuery('#finalCreditFlag').val(resultJSON.finalCreditFlag);
							jQuery('#finalPOFlag').val(resultJSON.finalPOFlag);
							jQuery('#finalTaxCalcFlag').val(resultJSON.finalTaxCalcFlag);
							jQuery('#finalShowPriceFlag').val(resultJSON.finalShowPriceFlag);
							decidePaymentTypeDisplay();
							jQuery('#hidePartListTable').hide();
							hideOverlay();
						}
					}			
				}
			});
		
	}
	
	function repopulatePartListTable(splitterFlag){
		var paymentType = "${assetDetailPageForm.selectedPaymentTypeId}";
		jQuery.ajax({
			url:"<portlet:resourceURL id='populatePartTable'></portlet:resourceURL>",
			data:{
				finalShowPriceFlag :jQuery('#finalShowPriceFlag').val(),
				selectedPaymentType :paymentType
			},
			type:'POST',
			dataType: 'JSON',		
			success: function(results){
				var resultJSON=results;
				
				if(resultJSON !=null){
					var flowType='${flowType}';
						if(resultJSON.partListTableContent != null){
							if(paymentType==null){
								jQuery('#partListTable').empty();
							}else{
								if(${assetDetailPageForm.splitterFlag} && flowType !="afterSaveAsDraft" && flowType!="fromReviewPage"){
									if(${not empty assetDetailPageForm.paymentTypes}){
										jQuery('#partListTable').empty();
										}
								}else{							
							jQuery('#partListTable').empty();
							jQuery('#partListTable').html(resultJSON.partListTableContent);
							jQuery('#noOfPart').val(resultJSON.noOfPart);
							jQuery('#hidePartListTable').hide();
							if(splitterFlag){
								decidePaymentTypeDisplay();
							}else{
								jQuery('#paymentBlock').show();
							}
						}
						}
						}
					}			
				}
			});
		
	}
	function decidePaymentTypeDisplay(){
		if(jQuery('#finalCreditFlag').val()=="true" && jQuery('#finalPOFlag').val()=="true"){
			jQuery('#paymentBlock').hide();
		}else if(jQuery('#finalCreditFlag').val()!="true"){
			jQuery('#paymentBlock').show();
		}
	
	}
	//Changes for MPS 2.1
	function addRestFieldsOfCleanseAddress(){
		jQuery('#pickupAddressisAddressCleansed').val("true");
		<%for(String cleanseOutputFields:ADDRESSCLEANSEFIELDS_OUTPUT){%>
			jQuery("#pickupAddress<%=cleanseOutputFields%>").val(cleaseAddressFields["<%=cleanseOutputFields%>"]);
		<%}%>
		jQuery('#pickupAddressofficeNumberLbl').html(jQuery('#pickupAddressofficeNumber').val());
		jQuery("#pickupAddresscountyLbl").html(',&nbsp;'+jQuery('#pickupAddresscounty').val());
		jQuery("#pickupAddresscountyLbl").show();
		if(jQuery('#pickupAddresscounty').val() =='' || jQuery('#pickupAddresscounty').val() == null){
			jQuery("#pickupAddresscountyLbl").hide();
			}
		//END
		}
	//ends
	
	//Added for MPS 2.1
	function removeDate(id, id1){
			
			jQuery('#' + id).val('');
			jQuery('#' +id1).hide();
			
	}

	
	jQuery(document).ready (function (){
	
		jQuery('#imgDate2').hide();
			
	});
	 jQuery(document).ready(function(id1){
		
		jQuery('#' +id1).hide();
	});
	
	
	 function shwDate(id, id1){
		
	if(jQuery('#' +id).val() != ''){
			jQuery('#' +id1).show();
		}	
	}
	 
	 function removeDate1(){
		 jQuery('#requestedDeliveryDate').val('');
		 jQuery('#imgDate2').hide();
	 }
	 
	 function shwDate1(){
		 if(jQuery('#requestedDeliveryDate').val() != ''){
			 jQuery('#imgDate2').show();
		 }
	 }

	 function checkParts(Id,maxOrderQty){
		var ordQty = document.getElementById(Id).value;	
		var newID = Id;
		newID = newID.substring(14, 15);
			 if(ordQty>maxOrderQty){			 
				 orderQtyFlag = false;
				 jQuery('#assetPartListDiv'+newID).addClass('errorColor');
				 document.getElementById("Msg"+Id).style.display = 'block';
				// var nameElem = document.getElementById(Id+"Error");
				// nameElem.classList.add("error");
				
			 }
			 else{
				 jQuery('#assetPartListDiv'+newID).removeClass('errorColor');
				 orderQtyFlag = true;
			// var nameElem = document.getElementById(Id+"Error");
			// nameElem.classList.remove("error");
			 document.getElementById("Msg"+Id).style.display = 'none';
			 }
		 
		 
	 }
		/* End */
	function removeValidation(){
		
		jQuery('#poNumber').removeClass('errorColor');
		jQuery('#errorMsg').html("");
		jQuery('#errorMsg').hide();
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